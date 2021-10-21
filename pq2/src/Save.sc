;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SAVE.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	Classes which create the save/restore game user interface.  Also
;;;;	contains a number of instances of Dialogs and associated DItems
;;;;	used in the interface.
;;;;
;;;;	Classes:
;;;;		SRDialog
;;;;		Save
;;;;		Restore



(script# SAVE)
(include game.sh)
(use Main)
(use Intrface)
(use Dselector)
(use System)

(define	GAMESSHOWN 8)		;the number of games displayed in the selector
(define	MAXGAMES 20)		;maximum number of games in a save directory
(define	COMMENTSIZE 36)		;size of user's description of the game
(define COMMENTBUFF 18) 	;(define	COMMENTBUFF (/ (+ 1 COMMENTSIZE) 2))

(define	DIRECTORYSIZE 29) ;size of the save directory name
 ;(define	DIRECTORYBUFF (/ (+ 1 DIRECTORYSIZE) 2))

(define BUFFERSIZE 361) ;(define	BUFFERSIZE (+ (* MAXGAMES COMMENTBUFF) 1))


;;;(procedure
;;;	GetDirectory
;;;	HaveSpace
;;;	GetStatus
;;;	NeedDescription
;;;)


(public
	GetDirectory	0
)

;;;(define noRoomMsg
;;;	{This directory/disk can hold no more saved games. 
;;;	You must replace one of your saved games or use
;;;	Change Directory to save on a different directory/disk.}
;;;)


(local
	default
	i
	numGames
	selected
	status
	okIText = [{Restore} {__Save__} {Replace} {Replace}]
	textIText = [
					{Select the game that you would like to restore.}
					{Type the description of this saved game.}
					{This directory/disk can hold no more saved games. 
	You must replace one of your saved games or use
	Change Directory to save on a different directory/disk.}
					{This directory/disk can hold no more saved games. 
	You must replace one of your saved games or use
	Change Directory to save on a different directory/disk.}
					]
)



(enum
	RESTORE			;Restore games
	HAVESPACE		;Save, with space on disk
	NOSPACE			;Save, no space on disk but games to replace
	NOREPLACE		;Save, no space on disk, no games to replace
)


;;;(class SysWindow kindof	Object
;;;	(properties
;;;		top		0
;;;		left		0
;;;		bottom	0
;;;		right		0
;;;		color		0			; foreground color
;;;		back		15			; background color
;;;		priority	-1			; priority
;;;		window	0			; handle/pointer to system window
;;;		type	0				; generally	corresponds to system window types
;;;		title		0			; text appearing in title bar if present
;;;
;;;		;; this rectangle is the working area for X/Y centering
;;;		;; these coordinates can define a subsection of the picture
;;;		;; in which a window will be centered
;;;		brTop		0
;;;		brLeft	0
;;;		brBottom	190
;;;		brRight	320
;;;	)
;;;
;;;;;;	(methods
;;;;;;		open
;;;;;;		dispose
;;;;;;	)
;;;
;;;	;; Open corresponding system window structure
;;;	;; Custom window type 0x81 indicates that system
;;;	;; will NOT draw the window, only get a port and link into list
;;;	(method (open)
;;;		(= window 
;;;			(NewWindow 
;;;				top 
;;;				left 
;;;				bottom 
;;;				right 
;;;				title 
;;;				type
;;;				priority 
;;;				color
;;;				back
;;;			)
;;;		)
;;;	)
;;;	(method (dispose)
;;;		(if window
;;;			(DisposeWindow window)
;;;			(= window 0)
;;;		)
;;;		(super dispose:)
;;;	)
;;;)



(class SRDialog kindof Dialog
	;;; The SRDialog class implements the user interface for save/restore.
	;;; Its subclasses are the specific save and restore game dialogs,
	;;; Save and Restore.

	(method (init theComment names nums)
		;; Initialize the dialog.


		; give ourself the class SysWindow as our window
		(= window SysWindow)

		;Re-init our size, with no elements.
		(= nsBottom 0)

		;Get some files for this directory.
		(= numGames (GetSaveFiles (theGame name?) names nums))
		(if (== numGames -1)
			(return FALSE)
		)

		(= status (GetStatus))

		;Set up the edit item for saved games.
		(if (== status HAVESPACE)
			(editI
				text: (StrCpy theComment names),
				font: smallFont,
				setSize:,
				moveTo: MARGIN MARGIN
			)
			(self add: editI, setSize:)
		)

		;Set up the selectorI box.
		(selectorI
			text: names,
			font: smallFont,
			setSize:,
			moveTo: MARGIN (+ nsBottom MARGIN),
			state: dExit
		)

		;Add three buttons down the side.
		(= i (+ (selectorI nsRight?) MARGIN))
		(okI
			text: [okIText status],
			setSize:,
			moveTo: i (selectorI nsTop?),
			state:(if (== status NOREPLACE) 0 else (| dActive dExit))
		)
		(cancelI
			setSize:,
			moveTo: i (+ (okI nsBottom?) MARGIN),
			state: (& (cancelI state?) (~ dSelected))
		)
		(changeDirI
			setSize:,
			moveTo: i (+ (cancelI nsBottom?) MARGIN),
			state: (& (changeDirI state?) (~ dSelected))
		)

		;Put these elements into the dialog and size it.
		(self
			add: selectorI okI cancelI changeDirI,
			setSize:
		)

		;Use the width of the dialog to size the text which goes into it.
		(textI
			text: [textIText status],
			setSize: (- (- nsRight nsLeft) (* 2 MARGIN)),
			moveTo: MARGIN MARGIN
		)

		;Now move all elements down by the height of the text.
		(= i (+ (textI nsBottom?) MARGIN))
		(self eachElementDo: #move: 0 i)

		;Add the text to the dialog, and resize.
		(self
			add: textI,
			setSize:, 
			center:,
			open: wTitled 15
		)

		(return TRUE)
	)




	(method	(doit theComment
						&tmp 	oldStatus fd ret offset
								[names BUFFERSIZE] [nums 21]
								[str 40]
				)

		;If restore: is called with a TRUE parameter, do nothing if there
		;are no saved games.  This allows optionally presenting the user
		;with his saved games at the start of the game.
		(if
			(and
				(== self Restore)
				argc
				theComment
			)

			(= fd (FOpen (Format @str SAVE 0 (theGame name?))))
			(if (== fd -1)
				;no directory -> no saved games
				(return)
			)
			(FClose fd)
		)

		(if (not (self init: theComment @names @nums))
			(return -1)
		)

		(repeat
			(= default
				(switch status
					(RESTORE
						(if numGames okI else changeDirI)
					)
					(HAVESPACE
						;Edit item of save games is active if present
						editI
					)
					(NOSPACE
						;If there are save-games to replace, 'Replace'
						;button is active.
						okI
					)
					(else
						;Otherwise 'Change Directory' button is active.
						changeDirI
					)
				)
			)

			(= i (super doit: default))

			(= selected (selectorI indexOf: (selectorI cursor?)))
			(= offset (* selected COMMENTBUFF))
			(cond
				((== i changeDirI)
					(if (GetDirectory curSaveDir)
						(= numGames
							(GetSaveFiles (theGame name?) @names @nums)
						)
						(if (== numGames -1)
							(= ret -1)
							(break)
						)

						(= oldStatus status)
						(= status (GetStatus))
						(switch status
							(RESTORE
							)
							(oldStatus
								(if (self contains: editI)
									(editI
										cursor: (StrLen (StrCpy theComment @names)),
										draw:
									)
								)
							)
							(else
								(self
									dispose:,
									init: theComment @names @nums
								)
							)
						)

						(selectorI draw:)
					)
				)

				((and (== status NOSPACE) (== i okI))
					(if (GetReplaceName doit: (StrCpy theComment @[names offset]))
						(= ret [nums selected])
						(break)
					)
				)

				((and (== status HAVESPACE) (or (== i okI) (== i editI)))
					(if (== (StrLen theComment) 0)
						(NeedDescription)
						(continue)
					)

					(= ret -1)
					(for	((= i 0))
							(< i numGames)
							((++ i))

						(= ret (StrCmp theComment @[names (* i COMMENTBUFF)]))
						(breakif (not ret))
					)

					(= ret
						(cond
							((not ret)
								[nums i]
							)
							((== numGames MAXGAMES)
								[nums selected]
							)
							(else
								numGames
							)
						)
					)
					(break)
				)

				((== i okI)
					(= ret [nums selected])
					(break)
				)

				((or (== i 0) (== i cancelI))
					(= ret -1)
					(break)
				)

				((== status HAVESPACE)
					(editI
						cursor:
							(StrLen (StrCpy theComment @[names offset])),
						draw:
					)
				)
			)
		)

		(self dispose:)
		(return ret)
	)



	(procedure (GetStatus)
		(return
			(cond
				((== self Restore)
					RESTORE
				)
				((HaveSpace)
					HAVESPACE
				)
				(numGames
					NOSPACE
				)
				(else
					NOREPLACE
				)
			)
		)
	)
)



(class Restore of SRDialog
	(properties
		text "Restore a Game"
	)
)


(class Save of SRDialog
	(properties
		text "Save a Game"
	)
)



(instance GetReplaceName of Dialog

	(method (doit theComment &tmp ret)
		; give ourself the class SysWindow as our window
		(= window SysWindow)

		(text1
			setSize:,
			moveTo:MARGIN MARGIN
		)
		(self add:text1, setSize:)
		(oldName
			text: theComment,
			font: smallFont,
			setSize:,
			moveTo:MARGIN nsBottom
		)
		(self add:oldName, setSize:)
		(text2
			setSize:,
			moveTo:MARGIN nsBottom
		)
		(self add:text2, setSize:)
		(newName
			text: theComment,
			font: smallFont,
			setSize:,
			moveTo: MARGIN nsBottom
		)
		(self add:newName, setSize:)

		(button1 nsLeft:0, nsTop:0, setSize:)
		(button2 nsLeft:0, nsTop:0, setSize:)
		(button2
			moveTo:
				(-	nsRight (+ (button2 nsRight?) MARGIN))
				nsBottom
		)
		(button1
			moveTo:
				(- (button2 nsLeft?) (+ (button1 nsRight?) MARGIN))
				nsBottom
		)

		(self
			add:button1 button2,
			setSize:,
			center:, 
			open:stdWindow 15
		)

		(= ret (super doit:newName))
		(self dispose:)
		(if (not (StrLen theComment))
			(NeedDescription)
			(= ret 0)
		)

		(return (or (== ret newName) (== ret button1)))
	)
)



(procedure (GetDirectory where &tmp result [newDir 33] [str 40])
	(repeat
		(= result
			(Print 
				SAVE 1
				#font: SYSFONT
				#edit: (StrCpy @newDir where) DIRECTORYSIZE
				#button: {OK} 1
				#button: {Cancel} 0
			)
		)

		;Pressed ESC -- return FALSE.
		(if (not result)
			(return FALSE)
		)

		;No string defaults to current drive.
		(if (not (StrLen @newDir))
			(GetCWD @newDir)
		)

		;If drive is valid, return TRUE, otherwise complain.
		(if (ValidPath @newDir)
			(StrCpy where @newDir)
			(return TRUE)
		else
			(Print
				(Format @str SAVE 2 @newDir)
				#font:SYSFONT
			)
		)
	)
)



(procedure (HaveSpace)
	(return (and (< numGames MAXGAMES) (CheckFreeSpace curSaveDir)))
)



(procedure (NeedDescription)
	(Print SAVE 3 #font:SYSFONT)
)



(instance selectorI of DSelector
	(properties
		x COMMENTSIZE 
		y GAMESSHOWN
	)
)
(instance editI of DEdit
	(properties
		max (- COMMENTSIZE 1)
	)
)
(instance okI of DButton
)
(instance cancelI of DButton
	(properties
		text "_Cancel_"
	)
)
(instance changeDirI of DButton
	(properties
		text {Change\nDirectory}
	)
)
(instance textI of DText
	(properties
		font SYSFONT
	)
)


(instance text1 of DText
	(properties
		font SYSFONT
		text "Replace"
	)
)
(instance text2 of DText
	(properties
		font SYSFONT
		text "with:"
	)
)
(instance oldName of DText
)
(instance newName of DEdit
	(properties
		max (- COMMENTSIZE 1)
	)
)
(instance button1 of DButton
	(properties
		text "Replace"
	)
)
(instance button2 of DButton
	(properties
		text "Cancel"
	)
)

