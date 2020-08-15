;;; Sierra Script 1.0 - (do not remove this comment)
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
;;;;
;;;;	Procedures:
;;;;		GetDirectory
;;;;		HaveSpace
;;;;		GetStatus
;;;;		NeedDescription


(script# SAVE)
(include game.sh) (include "64990.shm")
(use Main)
(use DButton)
(use DSelector)
(use DEdit)
(use DText)
(use String)
(use Array)
(use Print)
(use Dialog)
(use File)
(use System)

(define	GAMESSHOWN   8)	;the number of games displayed in the selector
(define	MAXGAMES    20)	;maximum number of games in a save directory
(define	COMMENTSIZE 36)	;size of user's description of the game
(define  DISPLAYSIZE 30)   ;how many characters displayed of description

(define	DIRECTORYSIZE 29) ;size of the save directory name
(define	DIRECTORYSIZE_MAX 45) ;maximum size of the save directory name

(define	BUFFERSIZE 720) ;(define	BUFFERSIZE (* MAXGAMES COMMENTSIZE))

(define  SELECTOR_WIDTH 158) 
(define	EDIT_Y			 30)

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
	butbuf1
	butbuf2
	butbuf3
	butbuf4
	SRbuf
	saveColGray
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

;;;	(methods 
;;;		update
;;;	)

	(method (init theComment names nums &tmp db)
		;
		; names is a String object
		; nums is an IntArray object

		(self add:)

		;; Initialize the strings
		(= butbuf1 	(String new:))
		(= butbuf2 	(String new:))
		(= butbuf3 	(String new:))
		(= butbuf4 	(String new:))

		; Give ourself the system plane as our plane
		((= plane (systemPlane new:))
			back:		saveColGray
		)

		(if (not (self update: theComment names nums))
			(return FALSE)
		)

		(Message MsgGet SAVE N_BUTTON_DELETE NULL NULL 1 (butbuf2 data?))
		(deleteI
			text:			(String StrDup (butbuf2 data?)), ;EO: Was "KString"
			setSize:		,
			moveTo:		i (+ (okI nsBottom?) 2)
		)

		(Message MsgGet SAVE N_BUTTON_CHANGE NULL NULL 1 (butbuf3 data?))
		(changeDirI
			text: 		(String StrDup (butbuf3 data?)),;EO: Was "KString"
			setSize:		,
			moveTo: 		i (+ (deleteI nsBottom?) 2),
			state:		(& (changeDirI state?) (~ dSelected))
		)

		(Message MsgGet SAVE N_BUTTON_CANCEL NULL NULL 1 (butbuf4 data?))
		(cancelI
			text: 		(String StrDup (butbuf4 data?)), ;EO: Was "KString"
			setSize:		,
			moveTo: 		i (+ (changeDirI nsBottom?) 2),
			state: 		(& (cancelI state?) (~ dSelected))
		)

		;;; Put these elements into the dialog 
		(self add: selectorI okI deleteI changeDirI cancelI textI)

		(super init:)

		(self
			setSize:			,
			center:			,
			eachElementDo: #updatePlane:,
			setSize:
		)

		(return TRUE)
	)

	(method (update theComment names nums &tmp db buf)
		;;;
		;;; Go through and update the textI, deleteI, editI, and selectorI
		;;; items to their appropriate value.
		;;;

		(= numGames (Save SGGetSaveFiles (theGame name?) (names data?) (nums data?))) ;EO: "Save" was "SaveGame"
		(if (== numGames -1)
			(return FALSE)
		)

		(= theStatus (GetStatus))
		; Use the width of the dialog to size the text which goes into it.
		(= buf (String new:))
		(switch theStatus
			(RESTORE
				(Message MsgGet SAVE N_SELECT_GAME NULL NULL 1 (buf data?))
			)
			(HAVESPACE
				(Message MsgGet SAVE N_TYPE_DESC NULL NULL 1 (buf data?))
			)
			(else
				(Message MsgGet SAVE N_DIR_NOROOM NULL NULL 1 (buf data?))
			)
		)
		(textI
			text:		(String StrDup (buf data?)), ;EO: Was "KString"
			x:			0,
			y:			0,
			setSize: 240,  
			moveTo: 	MARGIN MARGIN
		)
		(buf dispose:)

		(deleteI
			state:		(if (not numGames)
								0
							else
								(| dActive dExit)
							)
		)

		;Set up the edit item for saved games.
		(if (== theStatus HAVESPACE)
			;;; Add the editI to this dialog
			(editI
				x: 0, 
				y: 0,
				back:		saveColGray,
				font:	 	smallFont,
				text: 	((theComment copy: {}) data?),	;names,
				width:   (- DISPLAYSIZE 4), ;;; fudge factor
				setSize:	,
				moveTo: 	MARGIN EDIT_Y
			)
			(if (not (self contains: editI))
				(self add: editI)
				(if (self contains: okI)
					(editI plane: (self plane?))
					(editI draw:)
					(AddScreenItem editI)
				)
			)
		else
			(editI text: 0)
			(if (self contains: editI)
				(self delete: editI)
				(DeleteScreenItem editI)
				(if (editI bitmap?)
					(Bitmap MapDispose (editI bitmap?))
					(editI bitmap: 0)
				)
			)
		)

		;;; Get rid of the text items if they exist
		(if (and (selectorI textList?) ((selectorI textList?) size:))
			(selectorI setText: 0)
		)

		;Set up the selectorI box.
		(selectorI
			current:	0,
			font: 	smallFont,
			back:		255,
			width:	SELECTOR_WIDTH,
			setText:	names,
			setSize:	,
			moveTo: 	MARGIN (+ EDIT_Y 10 MARGIN),
			state: 	dExit
		)

		;;; Figure out what the okI button is -- Restore/Save/Replace
		(= db (butbuf1 data?))
		(switch theStatus
			(RESTORE
				(Message MsgGet SAVE N_BUTTON_RESTORE NULL NULL 1 db)
			)
			(HAVESPACE
				(Message MsgGet SAVE N_BUTTON_SAVE NULL NULL 1 db)
			)
			(else
				(Message MsgGet SAVE N_BUTTON_REPLACE NULL NULL 1 db)
			)
		)
		(= i (+ (selectorI nsRight?) 35))
		(okI
			x:				0,
			y:				0,
			text: 		(String StrDup db), ;EO: Was "KString"
			setSize:		,
			moveTo: 		i (selectorI nsTop?),
			state:		(if (or	(and	(== theStatus RESTORE)
												(not numGames)
										)
										(== theStatus NOREPLACE)
								)
								0
							else
								(| dActive dExit)
							)
		)
		;;;
		;;; If have displayed items once, do actual update here
		;;;
		(if (self contains: okI)
			(textI     draw:)
			(okI       draw:)
			(selectorI draw:)
			(deleteI   draw:)
			(UpdateScreenItem okI)
			(UpdateScreenItem textI)
			(UpdateScreenItem selectorI)
			(UpdateScreenItem deleteI)

			;;; Make the updates seen
			(FrameOut)
		)

		(return TRUE)
	)

	(method (doit theComment &tmp fd ret names nums str dir p tmpStr)
		(= names (String new: BUFFERSIZE))
		(= str 	(String new:))
		(= dir 	(String new:))
		(= nums 	(IntArray new: (+ MAXGAMES 1)))

		(= saveColGray (Palette PalMatch 127 127 127))

		(= ret 0)

		; If restore: is called with a TRUE parameter, do nothing if there
		; are no saved games.  This allows optionally presenting the user
		; with his saved games at the start of the game.
		(if (and	(== self Restore)
					argc
					theComment
			)
			(str format: {%ssg.cat} (theGame name?))
			(= fd (FileIO FileOpen (str data?)))
			(if (== fd -1)
				; No directory = no saved games
				(return)
			)
			(FileIO FileClose fd)
		)

		(if (not (self init: theComment names nums))
			(= ret RET_NO_ACTIVE)
		)

		(if (not ret)
			(repeat
				(= default
					(switch theStatus
						(RESTORE
							(if numGames okI else changeDirI)
						)
						(HAVESPACE
							; Edit item of save games is active if present
							editI
						)
						(NOSPACE
							; If there are save-games to replace, 'Replace'
							;	button is active.
							okI
						)
						(else
							; Otherwise 'Change Directory' button is active.
							changeDirI
						)
					)
				)

				(= i (super doit: default))

				(= selected (selectorI current?))
				(cond

					; Change directory

					((== i changeDirI)
						(if (GetDirectory curSaveDir)
							(if (not (self update: theComment names nums))
								(= ret RET_NO_ACTIVE)
								(break)
							)
						)
					)

					; Save with no space (replace)

					((and (== theStatus NOSPACE)
							(== i okI)
						)
						(theComment copy: (((selectorI textList?) at: (selectorI current?)) text?))
						(if
							((Print new:)
								font:				999,
								fore:				0,
								back:				saveColGray,
								addText:			N_DTEXT_REPLACE NULL NULL 1 0 0 SAVE,
								addText:			N_DTEXT_WITH NULL NULL 1 0 30 SAVE,
								classText:		oldName,
								addText:			theComment 0 15,
								addEdit:			str DISPLAYSIZE 0 45 theComment,
								classButton:	SRDButton,
								addButton:		TRUE N_DTEXT_REPLACE NULL NULL 1 0 60 SAVE,
								addButton:		FALSE N_BUTTON_NODIRCHG NULL NULL 1 55 60 SAVE,
								init:
							)

							(if (not (str size:))
								(NeedDescription)
								(continue)
							else
								(theComment copy: str)
								(= ret (nums at: selected))
								(break)
							)
						)
					)

					; Save with space

					((and (== theStatus HAVESPACE)
							(OneOf i okI editI)
						)
						(theComment copy: (editI text?))
						(if (not (theComment size:))
							(NeedDescription)
							(continue)
						)

						; Look for game with same name
						(= ret -1)
						(for	((= i 0))
								(< i numGames)
								((++ i))

							(= tmpStr (String new: COMMENTSIZE))
							(tmpStr copyToFrom: 0 names (* i COMMENTSIZE) COMMENTSIZE)
							(= ret (theComment compare: tmpStr))
							(tmpStr dispose:)
							(breakif ret)
						)

						(cond
							(ret
								(= ret (nums at: i))
							)
							((== numGames MAXGAMES)
								;;; SHOULD NEVER GET HERE
								(= ret (nums at: selected))
							)
							(else
								; Find the lowest unused game number
								(for ((= ret 0)) TRUE ((++ ret))
									(for ((= i 0)) (< i numGames) ((++ i))
										(breakif (== ret (nums at: i))) ; this number is used
									)
									(if (== i numGames)	; checked all entries in nums
										(break)				; and none matched
									)
								)
							)
						)
						(break)
					)
				
					; Delete a saved game

					((== i deleteI)
						(if (not
							((Print new:)
								font:				999,
								fore:				0,
								back:				saveColGray,
								addText:			N_DEL_TEXT NULL NULL 1 0 0 SAVE,
								classButton:	SRDButton,
								addButton:		FALSE N_BUTTON_NO  NULL NULL 1  0 35 SAVE,
								addButton:		TRUE	N_BUTTON_YES NULL NULL 1 50 35 SAVE,
								init:
						 	))
							(continue)
						)
					
						; Open the saved game directory file
						(Save SGMakeSaveCatName (str data?) (theGame name?)) ;EO: "Save" was "SaveGame"
						((= fd (File new:))
							name: (str data?),
							open:	fTrunc
						)
					
						; (File write:) requires a pointer to the data it is to write,
						; so need to put values into variables, rather than just 
						; passing them as immediates

						; Write the number and name of each saved game, except the
						; one that was selected for deletion
						(for ((= i 0)) (< i numGames) ((++ i))
							(if (!= i selected)
								(= ret (nums at: i))
								(str
									at: 0 (& ret $00ff),
									at: 1 (& (>> ret 8) $00ff),
									at: 2 0
								)
								(fd write: (str data?) 2)

								(= tmpStr (String new: COMMENTSIZE))
								(tmpStr copyToFrom: 0 names (* i COMMENTSIZE) COMMENTSIZE)
								(fd write: (tmpStr data?) COMMENTSIZE)
								(tmpStr dispose:)
							)
						)

						; Terminate saved game directory with a word of f's
						(str
							at: 0 $ff,
							at: 1 $ff
						)
						(fd
							write: 	(str data?) 2,
							close:	,
							dispose:
						)

						; Delete the save game file itself
						(Save SGMakeSaveFileName ;EO: "Save" was "SaveGame"
								(str data?) (theGame name?) (nums at: selected)
						)
						(FileIO FileUnlink (str data?))

						; Refresh the list box
						(self update: theComment names nums)
					)

					; Restore a saved game

					((== i okI)
						(= ret (nums at: selected))
						(break)
					)

					; Cancel

					((or (== i RET_ESC) (== i cancelI))
						(= ret RET_ESC)
						(break)
					)

					; Update the edit item's default text

					((== theStatus HAVESPACE)
					)
				)
			)
		)

		(butbuf1	dispose:)
		(butbuf2	dispose:)
		(butbuf3	dispose:)
		(butbuf4	dispose:)
		(SRbuf 	dispose:)

		(names 	dispose:)
		(str 		dispose:)
		(dir 		dispose:)
		(nums 	dispose:)

		(DisposeScript FILE)
		(= p plane)
		(self dispose:)
;**		(FrameOut)
		(p dispose:)
		(FrameOut)
		(DisposeScript SAVE ret)
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
		(= SRbuf (String new:))
 		(Message MsgGet SAVE N_DIALOG_RESTORE NULL NULL 1 (SRbuf data?))
		(= text (SRbuf data?))
		(super init: &rest)
	)
)

(class Save of SRDialog
	(method (init)
		(= SRbuf (String new:))
 		(Message MsgGet SAVE N_DIALOG_SAVE NULL NULL 1 (SRbuf data?))
		(= text (SRbuf data?))
		(super init: &rest)
	)
)

(procedure (GetDirectory where &tmp result newDir str buf1 len p theText ret)
	(= newDir (String new:))
	(= str (String new:))
	(= buf1 (String new:))

	(repeat
		(= len (Min DIRECTORYSIZE_MAX (+ (Max DIRECTORYSIZE (+ (where size:) 6)) 11)))
		((= p (Print new:))
			font: 			999,
			fore:				0,
			back:				saveColGray,
			addEdit: 		(newDir copy: where) len 0 20 where,
			classButton:	SRDButton,
			addButton:		TRUE N_BUTTON_OK NULL NULL 1 0 34 SAVE,
			addButton:		FALSE N_BUTTON_NODIRCHG NULL NULL 1 50 34 SAVE
		)
		(= theText (String new:))
		(Message MsgGet SAVE N_DIR_PROMPT NULL NULL 1 (theText data?))
		((p dialog?)
			add:
 				((DText new:)
					text:			(String StrDup (theText data?)), ;EO: Was "KString"
 					font: 		999,
					fore:			0,
					back:			saveColGray,
					skip:			254,
 					setSize: 	,
					moveTo:		MARGIN MARGIN,
					yourself:
 				)
		)
		(= result (p init:))
		(theText dispose:)

		; Pressed ESC -- return FALSE
		(if (not result)
			(= ret FALSE)
			(break)
		)

		; No string defaults to current drive
		(if (not (newDir size:))
			(FileIO FileGetCWD (newDir data?))
		)

		; If drive is valid, return TRUE, otherwise complain
		(if (FileIO FileValidPath (newDir data?))
			(where copy: newDir)
			(= ret TRUE)
			(break)
		else
			(Message MsgGet SAVE N_DIR_INVALID NULL NULL 1 (buf1 data?))
			(str format: (buf1 data?) newDir)
			((Print new:)
				font: 	999,
				fore:		0,
				back:		saveColGray,
				addText:	(str data?),
				init:
			)
		)
	)
	(newDir 	dispose:)
	(str 		dispose:)
	(buf1 	dispose:)
	(return ret)
)

(procedure (HaveSpace)
	(return 	(and 	(< numGames MAXGAMES)
						(FileIO FileCheckFreeSpace CFSEnoughSpaceToSave (String StrGetData curSaveDir)) ;EO: Was "KString"
				)
	)
)

(procedure (NeedDescription)
	((Print new:)
		font: 			999,
		fore:				0,
		back:				saveColGray,
		addText:			N_NO_DESC NULL NULL 1 0 0 SAVE,
		classButton:	SRDButton,
		addButton:		TRUE N_BUTTON_OK NULL NULL 1 0 15 SAVE,
		init:
	)
)



;ษออออออออออออออป
;บ              บ
;บ Dialog Items บ
;บ              บ
;ศออออออออออออออผ


(instance selectorI of DSelector
	(properties
		length 	GAMESSHOWN
	)
	(method (setText theText &tmp idx str bm oldData)
		;
		; Overridden to handle breaking up a single text item with
		;	all the names spaced evenly every 36 characters

		(if (and theText
					(theText size:)
			)
			(for	((= idx 0))
					(theText at: idx)
					((+= idx 36))
				(super setText: (= str (theText subStr: idx 36)))
				(str dispose:)
			)
		else
			(super setText: 0)
		)

		; Make sure the edit box won't display garbage if no games
		(if (and theText (editI text?) (editI bitmap?))
			;;; This kludgy code is used to save the old (editI text?) datablock
			;;; when it gets reformatted.
			(= str (String new:))
			(= oldData (str data?))
			(str data: (editI text?))
			(if (not (textList size?))
				(str format: {})
			else
				(str format: {%s} ((textList at: current) text?))
			)
			(editI
				text: (str data?),
				draw:
			)
			(str data: oldData, dispose:)
		)
	)
	(method (draw &tmp oldData str)
		(super draw: &rest)

		(if (and 
				(editI text?)
				(textList size?)
				(editI bitmap?)
			)
			(= str (String new:))
			(= oldData (str data?))
			(str data: (editI text?))
			(str format: {%s} ((textList at: current) text?))
			(editI 
				text: (str data?),
				draw:
			)
			(str data: oldData, dispose:)
		)
	)
)

(instance editI of DEdit
	(properties
		fore			0
		width			(- DISPLAYSIZE 1)
	)
)

(class SRDButton kindof DButton
	(properties
		view				SAVE
		loop				0
		cel				0
		fore				0
		font				999
;		borderColor		255
	)
)

(instance okI of SRDButton)
(instance cancelI of SRDButton)
(instance deleteI of SRDButton)
(instance changeDirI of SRDButton
	(properties
		loop		1
	)
)

(instance textI of DText
	(properties
		fore	0
		font	999
	)
)

(instance oldName of DText
	(properties
		borderColor	0
	)
)