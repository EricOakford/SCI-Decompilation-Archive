;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	SAVE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated: Brian K. Hughes
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
(include game.sh) (include "990.shm")
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use File)
(use System)

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

(local
	default
	i
	numGames
	selected
	theStatus
	[butbuf1 15]
	[butbuf2 15]
	[butbuf3 15]
	[butbuf4 15]
	[SRbuf 25]
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
		(super dispose: &rest)
	)

	(method (init theComment names nums
			&tmp [buf 250] 
		)

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
		(switch theStatus
			(0
				(Message MsgGet SAVE N_BUTTON_RESTORE NULL NULL 1 @butbuf1)
			)
			(1
				(Message MsgGet SAVE N_BUTTON_SAVE NULL NULL 1 @butbuf1)
			)
			(else
				(Message MsgGet SAVE N_BUTTON_REPLACE NULL NULL 1 @butbuf1)
			)
		)
		(= i (+ (selectorI nsRight?) MARGIN))
		(okI
			text: @butbuf1,
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
		(Message MsgGet SAVE N_BUTTON_DELETE NULL NULL 1 @butbuf2)
		(deleteI
			text:			@butbuf2,
			setSize:,
			moveTo:		i (+ (okI nsBottom?) MARGIN),
			state:		(if (not numGames)
								0
							else
								(| dActive dExit)
							)
		)
		(Message MsgGet SAVE N_BUTTON_CHANGE NULL NULL 1 @butbuf3)
		(changeDirI
			text: 	@butbuf3,
			setSize:,
			moveTo: 	i (+ (deleteI nsBottom?) MARGIN),
			state:	(& (changeDirI state?) (~ dSelected))
		)
		(Message MsgGet SAVE N_BUTTON_CANCEL NULL NULL 1 @butbuf4)
		(cancelI
			text: 		@butbuf4,
			setSize:,
			moveTo: 		i (+ (changeDirI nsBottom?) MARGIN),
			state: 		(& (cancelI state?) (~ dSelected))
		)

		;Put these elements into the dialog and size it.
		(self
			add: selectorI okI deleteI changeDirI cancelI,
			setSize:
		)

		;Use the width of the dialog to size the text which goes into it.
		(switch theStatus
			(0
				(Message MsgGet SAVE N_SELECT_GAME NULL NULL 1 @buf)
			)
			(1
				(Message MsgGet SAVE N_TYPE_DESC NULL NULL 1 @buf)
			)
			(else
				(Message MsgGet SAVE N_DIR_NOROOM NULL NULL 1 @buf)
			)
		)
		(textI
			text: @buf,
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
								[names BUFFERSIZE] [nums 21] ;(+ MAXGAMES 1)
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

			(= fd (FileIO fileOpen (Format @str {%ssg.dir} (theGame name?))))
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
					(if (not
						((Print new:)
							addText:		N_DEL_TEXT NULL NULL 1 0 0 SAVE,
							addButton:	FALSE N_BUTTON_NO NULL NULL 1 0 35 SAVE,
							addButton:	TRUE	N_BUTTON_YES NULL NULL 1 50 35 SAVE,
							init:
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

				((or (== i -1) (== i cancelI))
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
	(method (init)
 		(Message MsgGet SAVE N_DIALOG_RESTORE NULL NULL 1 @SRbuf)
		(= text @SRbuf)
		(super init: &rest)
	)
)

(class Save of SRDialog
	(method (init)
 		(Message MsgGet SAVE N_DIALOG_SAVE NULL NULL 1 @SRbuf)
		(= text @SRbuf)
		(super init: &rest)
	)
)

(instance GetReplaceName of Dialog

	(method (doit theComment
			&tmp ret [buf1 15] [buf2 15] [buf3 15] [buf4 15]
		)

		; give ourself the system window as our window
		(= window systemWindow)

		(Message MsgGet SAVE N_DTEXT_REPLACE NULL NULL 1 @buf1)
		(text1
			text:		@buf1,
			setSize:,
			moveTo:	MARGIN MARGIN
		)
		(self add:text1, setSize:)
		(oldName
			text: theComment,
			font: smallFont,
			setSize:,
			moveTo:MARGIN nsBottom
		)
		(self add:oldName, setSize:)
		(Message MsgGet SAVE N_DTEXT_WITH NULL NULL 1 @buf2)
		(text2
			text:		@buf2,
			setSize:,
			moveTo:	MARGIN nsBottom
		)
		(self add:text2, setSize:)
		(newName
			text: theComment,
			font: smallFont,
			setSize:,
			moveTo: MARGIN nsBottom
		)
		(self add:newName, setSize:)

		(Message MsgGet SAVE N_DTEXT_REPLACE NULL NULL 1 @buf3)
		(button1
			text:			@buf3,
			nsLeft:		0,
			nsTop:		0,
			setSize:
		)
		(Message MsgGet SAVE N_BUTTON_NODIRCHG NULL NULL 1 @buf4)
		(button2
			text:			@buf4,
			nsLeft:		0,
			nsTop:		0,
			setSize:
		)
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

		(return (or (== ret newName) (== ret button1)))
	)
)
(procedure (GetDirectory where &tmp result 
				[newDir 40] [str 100] [buf1 50] len
				)
	(repeat
		(= len (+ (Max DIRECTORYSIZE (+ (StrLen where) 6)) 11))
		(= result
			((Print new:)
				font: 		SYSFONT,
				addText:		N_DIR_PROMPT NULL NULL 1 0 0 SAVE,
				addEdit: 	(StrCpy @newDir where) len 0 20 where,
				addButton:	TRUE N_BUTTON_OK NULL NULL 1 0 34 SAVE,
				addButton:	FALSE	N_BUTTON_NODIRCHG NULL NULL 1 50 34 SAVE,
				init:
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
			(Message MsgGet SAVE N_DIR_INVALID NULL NULL 1 @buf1)
			(Format @str @buf1 @newDir)
			((Print new:)
				font: 	SYSFONT,
				addText:	@str,
				init:
			)
		)
	)
)

(procedure (HaveSpace)
	(return (and (< numGames MAXGAMES) (CheckFreeSpace curSaveDir)))
)

(procedure (NeedDescription)
	((Print new:)
		font: 	SYSFONT,
		addText:	N_NO_DESC NULL NULL 1 0 0 SAVE,
		init:
	)
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
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance cancelI of DButton
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance changeDirI of DButton
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance deleteI of DButton
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance textI of DText
	(properties
		font SYSFONT
	)
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance text1 of DText
	(properties
		font SYSFONT
	)
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance text2 of DText
	(properties
		font SYSFONT
	)
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance oldName of DText
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance newName of DEdit
	(properties
		max (- COMMENTSIZE 1)
	)
)

(instance button1 of DButton
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance button2 of DButton
	(method (dispose)
		(super dispose: TRUE)
	)
)
