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
(include language.sh)
(use Main)
(use Intrface)
(use Language)
(use PrintD)
(use File)

(define	GAMESSHOWN 8)		;the number of games displayed in the selector
(define	MAXGAMES 20)		;maximum number of games in a save directory
(define	COMMENTSIZE 36)	;size of user's description of the game
(define COMMENTBUFF 18) ;(define	COMMENTBUFF (/ (+ 1 COMMENTSIZE) 2))

(define	DIRECTORYSIZE 29) ;size of the save directory name
(define DIRECTORYBUFF 15) ;(define	DIRECTORYBUFF (/ (+ 1 DIRECTORYSIZE) 2))

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
	saveParse
	default
	i
	numGames
	selected
	theStatus
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

(class SRDialog kindof Dialog
	;;; The SRDialog class implements the user interface for save/restore.
	;;; Its subclasses are the specific save and restore game dialogs,
	;;; Save and Restore.

	(method (dispose)
		(RestoreSubLang)
		(theGame parseLang: saveParse)
		(super dispose: &rest)
	)

	(method (init theComment names nums)
		(SaveSubLang)
		(= saveParse (theGame parseLang?))
		(theGame parseLang: ENGLISH)
		;; Initialize the dialog.


		; give ourself the system window as our window
		(= window systemWindow)

		;Re-init our size, with no elements.
		(= nsBottom 0)

		;Get some files for this directory.
		(= numGames (GetSaveFiles (theGame name?) names nums))
		(if (== numGames -1)
			(return FALSE)
		)

		(= theStatus (GetStatus))

		;Set up the edit item for saved games.
		(if (== theStatus HAVESPACE)
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

		;Add four buttons down the side.
		(= i (+ (selectorI nsRight?) MARGIN))
		(okI
			text: [okIText theStatus],
			setSize:,
			moveTo: i (selectorI nsTop?),
			state:
				(if (or
						(and (== theStatus RESTORE) (not numGames))
						(== theStatus NOREPLACE)
					 )
						0
				else
					(| dActive dExit)
				)
		)
		(deleteI
			setSize:,
			moveTo: i (+ (okI nsBottom?) MARGIN),
			state:
				(if (not numGames)
					0
				else
					(| dActive dExit)
				)
		)
		(changeDirI
			setSize:,
			moveTo: i (+ (deleteI nsBottom?) MARGIN),
			state: (& (changeDirI state?) (~ dSelected))
		)
		(cancelI
			setSize:,
			moveTo: i (+ (changeDirI nsBottom?) MARGIN),
			state: (& (cancelI state?) (~ dSelected))
		)

		;Put these elements into the dialog and size it.
		(self
			add: selectorI okI deleteI changeDirI cancelI,
			setSize:
		)

		;Use the width of the dialog to size the text which goes into it.
		(textI
			text: [textIText theStatus],
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
			open: wTitled -1			;don't save PMAP
;***			open: wTitled 15
		)

		(return TRUE)
	)




	(method	(doit theComment
						&tmp 	fd ret offset 
								[names BUFFERSIZE] [nums 21] ;(+ MAXGAMES 1)]
								[str 100] [dir 40]
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

			(= fd (FileIO fileOpen (Format @str SAVE 0 (theGame name?))))
			(if (== fd -1)
				;no directory -> no saved games
				(return)
			)
			(FileIO fileClose fd)
		)

		(if (not (self init: theComment @names @nums))
			(return -1)
		)

		(repeat
			(= default
				(switch theStatus
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
					;; kill save window to save hunk
					(self dispose:)
					(if (GetDirectory curSaveDir)
						(= numGames
							(GetSaveFiles (theGame name?) @names @nums)
						)
						(if (== numGames -1)
							(= ret -1)
							(break)
						)
					)
					;; open save back up with new directory
					(self	init: theComment @names @nums)
				)

				((and (== theStatus NOSPACE) (== i okI))
					(self dispose:)
					(if (GetReplaceName doit: (StrCpy theComment @[names offset]))
						(= ret [nums selected])
						(break)
					)
					(self	init: theComment @names @nums)
				)

				((and (== theStatus HAVESPACE) (or (== i okI) (== i editI)))
					(if (== (StrLen theComment) 0)
						(self dispose:)
						(NeedDescription)
						(self	init: theComment @names @nums)
						(continue)
					)

					(= ret -1)
					(for	((= i 0))
							(< i numGames)
							((++ i))

						(= ret (StrCmp theComment @[names (* i COMMENTBUFF)]))
						(breakif (not ret))
					)

					(cond
						((not ret)
							(= ret [nums i])
						)
						((== numGames MAXGAMES)
							(= ret [nums selected])
						)
						(else
							; find the lowest unused game number
							(for ((= ret 0)) TRUE ((++ ret))
								(for ((= i 0)) (< i numGames) ((++ i))
									(breakif (== ret [nums i])) ; this number is used
								)
								(if (== i numGames)	; checked all entries in nums
									(break)				; and none matched
								)
							)
						)
					)
					(break)
				)
				
				((== i deleteI)
					;; kill save window to save hunk
					(self dispose:)
					; confirm deletion
					(if (not (PrintD
							{Are you sure you want to\ndelete this saved game?}
							#new:
							#button: {_No_} 0
							#button:	{Yes} 1
						 ))
						(self	init: theComment @names @nums)
						(continue)
					)
					
					; open the saved game directory file
					((= fd (File new:))
						name: (DeviceInfo MakeSaveDirName @str (theGame name?)),
						open:	fTrunc
					)
					
					; (File write:) requires a pointer to the data it is to write,
					; so need to put values into variables, rather than just 
					; passing them as immediates
					(= ret $0a0a)	; so both high and low byte is correct

					; write the number and name of each saved game, except the
					; one that was selected for deletion
					(for ((= i 0)) (< i numGames) ((++ i))
						(if (!= i selected)
							(fd write: @[nums i] 2)
							(fd writeString: @[names (* i COMMENTBUFF)])
							(fd write: @ret 1)
						)
					)

					; terminate saved game directory with a word of f's
					(= ret -1)
					(fd
						write: @ret 2,
						close:,
						dispose:
					)

					; delete the save game file itself
					(DeviceInfo MakeSaveFileName 
							@str (theGame name?) [nums selected]
					)
					(FileIO fileUnlink @str)

					; reinit to display updated dialog
					(self	init: theComment @names @nums)
				)

				((== i okI)
					(= ret [nums selected])
					(break)
				)

				((or (== i 0) (== i cancelI))
					(= ret -1)
					(break)
				)

				((== theStatus HAVESPACE)
					(editI
						cursor:
							(StrLen (StrCpy theComment @[names offset])),
						draw:
					)
				)
			)
		)
		(DisposeScript FILE)
		(DisposeScript PRINTD)
		(self dispose:)
		(DisposeScript SAVE)
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

	(method (doit theComment &tmp ret saveParseLang)
		(= saveParseLang (theGame parseLang?))
		(theGame parseLang: ENGLISH)

		; give ourself the system window as our window
		(= window systemWindow)

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
			open:stdWindow -1			;don't save PMAP
;***			open:stdWindow 15
		)

		(= ret (super doit:newName))
		(self dispose:)
		(if (not (StrLen theComment))
			(NeedDescription)
			(= ret 0)
		)

		(theGame parseLang: saveParseLang) ;;added for Japanese version
		(return (or (== ret newName) (== ret button1)))
	)
)
(procedure (GetDirectory where &tmp result 
				[newDir 33] [str 100] saveParseLang) ;;;changed for Japanese version
	(repeat
		(= saveParseLang (theGame parseLang?))	 ;;added for Japanese version
		(theGame parseLang: ENGLISH)				 ;;added for Japanese version
		(= result
			(Print 
				SAVE 1
				#font: SYSFONT
				#edit: (StrCpy @newDir where) DIRECTORYSIZE
				#button: {OK} 1
				#button: {Cancel} 0
			)
		)
		(theGame parseLang: saveParseLang) ;;added for Japanese version

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

(instance deleteI of DButton
	(properties
		text {_Delete_}
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
