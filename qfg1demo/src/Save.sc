;;; Sierra Script 1.0 - (do not remove this comment)
(script# SAVE)
(include game.sh) (include "990.shm")
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use Language)
(use File)

(public
	GetDirectory 0
)

(local
	oldLang
	default
	i
	numGames
	selected
	theStatus
	[butbuf1 4] = [{Restore} {__Save__} {Replace} {Replace}]
	[butbuf2 4] = [{Select the game that you would like to restore.} {Type the description of this saved game.} {This directory/disk can hold no more saved games. You must replace one of your saved games or use Change Directory to save on a different directory/disk.} {This directory/disk can hold no more saved games. You must replace one of your saved games or use Change Directory to save on a different directory/disk.}]
)

(define  GAMESSHOWN 8)     ;the number of games displayed in the selector
(define  MAXGAMES 20)      ;maximum number of games in a save directory
(define  COMMENTSIZE 36)   ;size of user's description of the game
;SCICompanion doesn't support equation defines
;(define  COMMENTBUFF (/ (+ 1 COMMENTSIZE) 2))

(define  DIRECTORYSIZE 29) ;size of the save directory name
;(define  DIRECTORYBUFF (/ (+ 1 DIRECTORYSIZE) 2))

;(define  BUFFERSIZE (+ (* MAXGAMES (/ (+ 1 COMMENTSIZE) 2)) 1))


(enum
   RESTORE        ;Restore games
   HAVESPACE      ;Save, with space on disk
   NOSPACE        ;Save, no space on disk but games to replace
   NOREPLACE      ;Save, no space on disk, no games to replace
)

(procedure (GetDirectory where &tmp result [newDir 33] [str 100] temp134 [temp135 100] [temp235 5] [temp240 5])
	(asm
code_07f0:
		pushi    #parseLang
		pushi    0
		lag      theGame
		send     4
		sat      temp134
		pushi    #parseLang
		pushi    1
		pushi    1
		lag      theGame
		send     6
		pushi    7
		pushi    0
		pushi    SAVE
		pushi    1
		pushi    0
		pushi    0
		pushi    1
		lea      @temp135
		push    
		callk    Message,  14
		pushi    7
		pushi    0
		pushi    SAVE
		pushi    4
		pushi    0
		pushi    0
		pushi    1
		lea      @temp235
		push    
		callk    Message,  14
		pushi    7
		pushi    0
		pushi    SAVE
		pushi    5
		pushi    0
		pushi    0
		pushi    1
		lea      @temp240
		push    
		callk    Message,  14
		pushi    #font
		pushi    1
		pushi    0
		pushi    198
		pushi    1
		lea      @temp135
		push    
		pushi    200
		pushi    5
		pushi    2
		lea      @newDir
		push    
		lsp      where
		callk    StrCpy,  4
		push    
		pushi    29
		pushi    0
		pushi    12
		lsp      where
		pushi    205
		pushi    4
		pushi    1
		lea      @temp235
		push    
		pushi    0
		pushi    26
		pushi    205
		pushi    4
		pushi    0
		lea      @temp240
		push    
		pushi    50
		pushi    26
		pushi    110
		pushi    0
		class    Print
		send     54
		sat      result
		pushi    #parseLang
		pushi    1
		lst      temp134
		lag      theGame
		send     6
		lat      result
		not     
		bnt      code_088e
		ldi      0
		ret     
code_088e:
		pushi    1
		lea      @newDir
		push    
		callk    StrLen,  2
		not     
		bnt      code_08a1
		pushi    1
		lea      @newDir
		push    
		callk    GetCWD,  2
code_08a1:
		pushi    1
		lea      @newDir
		push    
		callk    ValidPath,  2
		bnt      code_08bb
		pushi    2
		lsp      where
		lea      @newDir
		push    
		callk    StrCpy,  4
		ldi      1
		ret     
		jmp      code_07f0
code_08bb:
		pushi    7
		pushi    0
		pushi    SAVE
		pushi    2
		pushi    0
		pushi    0
		pushi    1
		lea      @temp135
		push    
		callk    Message,  14
		pushi    3
		lea      @str
		push    
		lea      @temp135
		push    
		lea      @newDir
		push    
		callk    Format,  6
		pushi    #font
		pushi    1
		pushi    0
		pushi    198
		pushi    1
		lea      @str
		push    
		pushi    110
		pushi    0
		class    Print
		send     16
		jmp      code_07f0
		ret     
	)
)

(procedure (GetStatus)
	(return
		(cond 
			((== self Restore) RESTORE)
			((HaveSpace) HAVESPACE)
			(numGames NOSPACE)
			(else NOREPLACE)
		)
	)
)

(procedure (HaveSpace)
	(if (< numGames MAXGAMES) (CheckFreeSpace curSaveDir))
)

(procedure (NeedDescription &tmp [str 100])
	(Message MsgGet SAVE N_NO_DESC NULL NULL 1 @str)
	(Print font: SYSFONT addText: @str init:)
)

(class SRDialog of Dialog
	(properties)
	
	(method (init theComment names nums)
		(proc932_3)
		(= oldLang (theGame parseLang?))
		(theGame parseLang: ENGLISH)
		(= window systemWindow)
		(= nsBottom 0)
		(if
			(==
				(= numGames
					(GetSaveFiles (theGame name?) names nums)
				)
				-1
			)
			(return 0)
		)
		(if (== (= theStatus (GetStatus)) 1)
			(editI
				text: (StrCpy theComment names)
				font: smallFont
				setSize:
				moveTo: MARGIN MARGIN
			)
			(self add: editI setSize:)
		)
		(selectorI
			text: names
			font: smallFont
			setSize:
			moveTo: 4 (+ nsBottom 4)
			state: 2
		)
		(= i (+ (selectorI nsRight?) 4))
		(okI
			text: [butbuf1 theStatus]
			setSize:
			moveTo: i (selectorI nsTop?)
			state:
				(if
					(or
						(and (== theStatus 0) (not numGames))
						(== theStatus 3)
					)
					0
				else
					3
				)
		)
		(deleteI
			setSize:
			moveTo: i (+ (okI nsBottom?) 4)
			state: (if (not numGames) 0 else 3)
		)
		(changeDirI
			setSize:
			moveTo: i (+ (deleteI nsBottom?) 4)
			state: (& (changeDirI state?) $fff7)
		)
		(cancelI
			setSize:
			moveTo: i (+ (changeDirI nsBottom?) 4)
			state: (& (cancelI state?) $fff7)
		)
		(self
			add: selectorI okI deleteI changeDirI cancelI
			setSize:
		)
		(textI
			text: [butbuf2 theStatus]
			setSize: (- (- nsRight nsLeft) 8)
			moveTo: 4 4
		)
		(= i (+ (textI nsBottom?) 4))
		(self eachElementDo: #move 0 i)
		(self add: textI setSize: center: open: 4 -1)
		(return 1)
	)
	
   (method  (doit theComment
                  &tmp  fd ret offset 
                        [names 361] [nums 21]
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
         (= offset (* selected (/ (+ 1 COMMENTSIZE) 2)))
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
               (self init: theComment @names @nums)
            )

            ((and (== theStatus NOSPACE) (== i okI))
               (self dispose:)
               (if (GetReplaceName doit: (StrCpy theComment @[names offset]))
                  (= ret [nums selected])
                  (break)
               )
               (self init: theComment @names @nums)
            )

            ((and (== theStatus HAVESPACE) (or (== i okI) (== i editI)))
               (if (== (StrLen theComment) 0)
                  (self dispose:)
                  (NeedDescription)
                  (self init: theComment @names @nums)
                  (continue)
               )

               (= ret -1)
               (for  ((= i 0))
                     (< i numGames)
                     ((++ i))

                  (= ret (StrCmp theComment @[names (* i (/ (+ 1 COMMENTSIZE) 2))]))
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
                        (if (== i numGames)  ; checked all entries in nums
                           (break)           ; and none matched
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
                     addText: {Are you sure you want to\0D\ndelete this saved game?}
                     addButton: FALSE {No_} 15 27
                     addButton: TRUE  {Yes} 70 27
                     init:
                   ))
                  (self init: theComment @names @nums)
                  (continue)
               )
               
               ; open the saved game directory file
               ((= fd (File new:))
                  name: (DeviceInfo MakeSaveDirName @str (theGame name?)),
                  open: fTrunc
               )
               
               ; (File write:) requires a pointer to the data it is to write,
               ; so need to put values into variables, rather than just 
               ; passing them as immediates
               (= ret $0a0a)  ; so both high and low byte is correct

               ; write the number and name of each saved game, except the
               ; one that was selected for deletion
               (for ((= i 0)) (< i numGames) ((++ i))
                  (if (!= i selected)
                     (fd write: @[nums i] 2)
                     (fd writeString: @[names (* i (/ (+ 1 COMMENTSIZE) 2))])
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
               (self init: theComment @names @nums)
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

	
	(method (dispose)
		(proc932_4)
		(theGame parseLang: oldLang)
		(super dispose: &rest)
	)
)

(class Restore of SRDialog
	(properties
		text {Restore a Game}
	)
)

(class Save of SRDialog
	(properties
		text {Save a Game}
	)
)

(instance GetReplaceName of Dialog
	(properties)
	
	(method (doit theComment &tmp ret language)
		(= language (theGame parseLang?))
		(theGame parseLang: ENGLISH)
		(= window systemWindow)
		(text1 setSize: moveTo: MARGIN MARGIN)
		(self add: text1 setSize:)
		(oldName
			text: theComment
			font: smallFont
			setSize:
			moveTo: MARGIN nsBottom
		)
		(self add: oldName setSize:)
		(text2 setSize: moveTo: 4 nsBottom)
		(self add: text2 setSize:)
		(newName
			text: theComment
			font: smallFont
			setSize:
			moveTo: MARGIN nsBottom
		)
		(self add: newName setSize:)
		(button1 nsLeft: 0 nsTop: 0 setSize:)
		(button2 nsLeft: 0 nsTop: 0 setSize:)
		(button2
			moveTo: (- nsRight (+ (button2 nsRight?) 4)) nsBottom
		)
		(button1
			moveTo: (- (button2 nsLeft?) (+ (button1 nsRight?) 4)) nsBottom
		)
		(self add: button1 button2 setSize: center: open: 0 -1)
		(= ret (super doit: newName))
		(self dispose:)
		(if (not (StrLen theComment))
			(NeedDescription)
			(= ret 0)
		)
		(theGame parseLang: language)
		(return (if (== ret newName) else (== ret button1)))
	)
)

(instance selectorI of DSelector
	(properties
		x 36
		y 8
	)
)

(instance editI of DEdit
	(properties
		max (- COMMENTSIZE 1)
	)
)

(instance okI of DButton
	(properties)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance cancelI of DButton
	(properties
		text { Cancel_}
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance changeDirI of DButton
	(properties
		text {Change\0D\nDirectory}
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance deleteI of DButton
	(properties
		text { Delete_}
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance textI of DText
	(properties
		font 0
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance text1 of DText
	(properties
		text {Replace}
		font 0
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance text2 of DText
	(properties
		text {with:}
		font 0
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance oldName of DText
	(properties)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance newName of DEdit
	(properties
		max 35
	)
)

(instance button1 of DButton
	(properties
		text {Replace}
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)

(instance button2 of DButton
	(properties
		text {Cancel}
	)
	
	(method (dispose)
		(super dispose: TRUE)
	)
)
