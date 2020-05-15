;;; Sierra Script 1.0 - (do not remove this comment)
(script# SAVE)
(include game.sh)
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


(public
	GetDirectory 0
)

(local
	theLanguage
	default
	i
	numGames
	selected
	theStatus
	[butbuf1 4] = [{Restore} {__Save__} {Replace} {Replace}]
	[butbuf2 4] = [{Select the game that you would like to restore.} {Type the description of this saved game.} {This directory/disk can hold no more saved games. You must replace one of your saved games or use Change Directory to save on a different directory/disk.} {This directory/disk can hold no more saved games. You must replace one of your saved games or use Change Directory to save on a different directory/disk.}]
)

(enum
	RESTORE			;Restore games
	HAVESPACE		;Save, with space on disk
	NOSPACE			;Save, no space on disk but games to replace
	NOREPLACE		;Save, no space on disk, no games to replace
)

(class SRDialog of Dialog
	(properties)
	
	(method (init theComment names nums)
		(proc932_3)
		(= theLanguage (theGame parseLang?))
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
			(return FALSE)
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
			moveTo: MARGIN (+ nsBottom MARGIN)
			state: 2
		)
		(= i (+ (selectorI nsRight?) MARGIN))
		(okI
			text: [butbuf1 theStatus]
			setSize:
			moveTo: i (selectorI nsTop?)
			state:
				(if
					(or
						(and (== theStatus RESTORE) (not numGames))
						(== theStatus NOREPLACE)
					)
					0
				else
					3
				)
		)
		(deleteI
			setSize:
			moveTo: i (+ (okI nsBottom?) MARGIN)
			state: (if (not numGames) 0 else (| dActive dExit))
		)
		(changeDirI
			setSize:
			moveTo: i (+ (deleteI nsBottom?) MARGIN)
			state: (& (changeDirI state?) (~ dSelected))
		)
		(cancelI
			setSize:
			moveTo: i (+ (changeDirI nsBottom?) MARGIN)
			state: (& (cancelI state?) (~ dSelected))
		)
		(self
			add: selectorI okI deleteI changeDirI cancelI
			setSize:
		)
		(textI
			text: [butbuf2 theStatus]
			setSize: (- (- nsRight nsLeft) (* 2 MARGIN))
			moveTo: MARGIN MARGIN
		)
		(= i (+ (textI nsBottom?) MARGIN))
		(self eachElementDo: #move 0 i)
		(self add: textI setSize: center: open: 4 15)
		(return TRUE)
	)

	(method (doit theComment &tmp fd theRet offset [names 361] [nums 21] [str 140])
		(asm
			pushSelf
			lofsa    Restore
			eq?     
			bnt      code_024d
			lap      argc
			bnt      code_024d
			lap      theComment
			bnt      code_024d
			pushi    2
			pushi    0
			pushi    4
			lea      @str
			push    
			pushi    SAVE
			pushi    0
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			callk    Format,  8
			push    
			callk    FileIO,  4
			sat      fd
			push    
			ldi      65535
			eq?     
			bnt      code_0246
			ret     
code_0246:
			pushi    2
			pushi    1
			lst      fd
			callk    FileIO,  4
code_024d:
			pushi    #init
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     10
			not     
			bnt      code_0266
			ldi      65535
			ret     
code_0266:
			lsl      theStatus
			dup     
			ldi      0
			eq?     
			bnt      code_0280
			lal      numGames
			bnt      code_029d
			lofsa    okI
			jmp      code_029d
			lofsa    changeDirI
			jmp      code_029d
code_0280:
			dup     
			ldi      1
			eq?     
			bnt      code_028d
			lofsa    editI
			jmp      code_029d
code_028d:
			dup     
			ldi      2
			eq?     
			bnt      code_029a
			lofsa    okI
			jmp      code_029d
code_029a:
			lofsa    changeDirI
code_029d:
			toss    
			sal      default
			pushi    #doit
			pushi    1
			push    
			super    Dialog,  6
			sal      i
			pushi    #indexOf
			pushi    1
			pushi    #cursor
			pushi    0
			lofsa    selectorI
			send     4
			push    
			lofsa    selectorI
			send     6
			sal      selected
			push    
			ldi      18
			mul     
			sat      offset
			lsl      i
			lofsa    changeDirI
			eq?     
			bnt      code_0316
			pushi    #dispose
			pushi    0
			self     4
			pushi    1
			lsg      curSaveDir
			call     GetDirectory,  2
			bnt      code_0301
			pushi    3
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			lea      @names
			push    
			lea      @nums
			push    
			callk    GetSaveFiles,  6
			sal      numGames
			push    
			ldi      65535
			eq?     
			bnt      code_0301
			ldi      65535
			sat      theRet
			jmp      code_057e
code_0301:
			pushi    #init
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     10
			jmp      code_0266
code_0316:
			lsl      theStatus
			ldi      2
			eq?     
			bnt      code_0363
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_0363
			pushi    #dispose
			pushi    0
			self     4
			pushi    #doit
			pushi    1
			pushi    2
			lsp      theComment
			lat      offset
			leai     @names
			push    
			callk    StrCpy,  4
			push    
			lofsa    GetReplaceName
			send     6
			bnt      code_034e
			lal      selected
			lati     nums
			sat      theRet
			jmp      code_057e
code_034e:
			pushi    #init
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     10
			jmp      code_0266
code_0363:
			lsl      theStatus
			ldi      1
			eq?     
			bnt      code_0419
			lsl      i
			lofsa    okI
			eq?     
			bt       code_037d
			lsl      i
			lofsa    editI
			eq?     
			bnt      code_0419
code_037d:
			pushi    1
			lsp      theComment
			callk    StrLen,  2
			push    
			ldi      0
			eq?     
			bnt      code_0392
			pushi    0
			call     NeedDescription,  0
			jmp      code_0266
code_0392:
			ldi      65535
			sat      theRet
			ldi      0
			sal      i
code_039a:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_03bc
			pushi    2
			lsp      theComment
			lsl      i
			ldi      18
			mul     
			leai     @names
			push    
			callk    StrCmp,  4
			sat      theRet
			not     
			bnt      code_03b7
code_03b7:
			+al      i
			jmp      code_039a
code_03bc:
			lat      theRet
			not     
			bnt      code_03cc
			lal      i
			lati     nums
			sat      theRet
			jmp      code_057e
code_03cc:
			lsl      numGames
			ldi      20
			eq?     
			bnt      code_03de
			lal      selected
			lati     nums
			sat      theRet
			jmp      code_057e
code_03de:
			ldi      0
			sat      theRet
code_03e2:
			ldi      1
			bnt      code_057e
			ldi      0
			sal      i
code_03eb:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_0403
			lst      theRet
			lal      i
			lati     nums
			eq?     
			bnt      code_03fe
code_03fe:
			+al      i
			jmp      code_03eb
code_0403:
			lsl      i
			lal      numGames
			eq?     
			bnt      code_040e
			jmp      code_057e
code_040e:
			+at      theRet
			jmp      code_03e2
			jmp      code_057e
			jmp      code_0266
code_0419:
			lsl      i
			lofsa    deleteI
			eq?     
			bnt      code_0525
			pushi    #dispose
			pushi    0
			self     4
			pushi    8
			lofsa    {Are you sure you want to\0D\ndelete this saved game?}
			push    
			pushi    103
			pushi    81
			lofsa    { No_}
			push    
			pushi    0
			pushi    81
			lofsa    {Yes}
			push    
			pushi    1
			calle    PrintD,  16
			not     
			bnt      code_045c
			pushi    #init
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     10
			jmp      code_0266
code_045c:
			pushi    #name
			pushi    1
			pushi    3
			pushi    7
			lea      @str
			push    
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			callk    DeviceInfo,  6
			push    
			pushi    177
			pushi    1
			pushi    2
			pushi    #new
			pushi    0
			class    File
			send     4
			sat      fd
			send     12
			ldi      2570
			sat      theRet
			ldi      0
			sal      i
code_048e:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_04d2
			lsl      i
			lal      selected
			ne?     
			bnt      code_04cd
			pushi    #write
			pushi    2
			lal      i
			leai     @nums
			push    
			pushi    2
			lat      fd
			send     8
			pushi    327
			pushi    #superClass
			lsl      i
			ldi      18
			mul     
			leai     @names
			push    
			lat      fd
			send     6
			pushi    #write
			pushi    2
			lea      @theRet
			push    
			pushi    1
			lat      fd
			send     8
code_04cd:
			+al      i
			jmp      code_048e
code_04d2:
			ldi      65535
			sat      theRet
			pushi    #write
			pushi    2
			lea      @theRet
			push    
			pushi    2
			pushi    331
			pushi    0
			pushi    105
			pushi    0
			lat      fd
			send     16
			pushi    4
			pushi    8
			lea      @str
			push    
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			lal      selected
			lsti     nums
			callk    DeviceInfo,  8
			pushi    2
			pushi    4
			lea      @str
			push    
			callk    FileIO,  4
			pushi    #init
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     10
			jmp      code_0266
code_0525:
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_053b
			lal      selected
			lati     nums
			sat      theRet
			jmp      code_057e
			jmp      code_0266
code_053b:
			lsl      i
			ldi      0
			eq?     
			bt       code_054c
			lsl      i
			lofsa    cancelI
			eq?     
			bnt      code_0556
code_054c:
			ldi      65535
			sat      theRet
			jmp      code_057e
			jmp      code_0266
code_0556:
			lsl      theStatus
			ldi      1
			eq?     
			bnt      code_0266
			pushi    #cursor
			pushi    1
			pushi    1
			pushi    2
			lsp      theComment
			lat      offset
			leai     @names
			push    
			callk    StrCpy,  4
			push    
			callk    StrLen,  2
			push    
			pushi    83
			pushi    0
			lofsa    editI
			send     10
			jmp      code_0266
code_057e:
			pushi    1
			pushi    FILE
			callk    DisposeScript,  2
			pushi    1
			pushi    PRINTD
			callk    DisposeScript,  2
			pushi    #dispose
			pushi    0
			self     4
			pushi    1
			pushi    SAVE
			callk    DisposeScript,  2
			lat      theRet
			ret     
		)
	)
	
	(method (dispose)
		(proc932_4)
		(theGame parseLang: theLanguage)
		(super dispose: &rest)
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
	
	(method (doit theComment &tmp ret oldLang)
		(= oldLang (theGame parseLang?))
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
		(text2 setSize: moveTo: MARGIN nsBottom)
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
			moveTo: (- nsRight (+ (button2 nsRight?) MARGIN)) nsBottom
		)
		(button1
			moveTo: (- (button2 nsLeft?) (+ (button1 nsRight?) MARGIN)) nsBottom
		)
		(self add: button1 button2 setSize: center: open: 0 15)
		(= ret (super doit: newName))
		(self dispose:)
		(if (not (StrLen theComment))
			(NeedDescription)
			(= ret 0)
		)
		(theGame parseLang: oldLang)
		(return (if (== ret newName) else (== ret button1)))
	)
)

(procedure (GetDirectory where &tmp result [newDir 33] [str 100] oldLang)
	(repeat
		(= oldLang (theGame parseLang?))
		(theGame parseLang: ENGLISH)		
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
			(theGame parseLang: oldLang)
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
	(if (< numGames MAXGAMES) (CheckFreeSpace curSaveDir))
)

(procedure (NeedDescription)
	(Print SAVE 5 #font SYSFONT)
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
	(properties)
)

(instance cancelI of DButton
	(properties
		text { Cancel_}
	)
)

(instance changeDirI of DButton
	(properties
		text {Change\0D\nDirectory}
	)
)

(instance deleteI of DButton
	(properties
		text { Delete_}
	)
)

(instance textI of DText
	(properties
		font SYSFONT
	)
)

(instance text1 of DText
	(properties
		text {Replace}
		font SYSFONT
	)
)

(instance text2 of DText
	(properties
		text {with:}
		font SYSFONT
	)
)

(instance oldName of DText
	(properties)
)

(instance newName of DEdit
	(properties
		max (- COMMENTSIZE 1)
	)
)

(instance button1 of DButton
	(properties
		text {Replace}
	)
)

(instance button2 of DButton
	(properties
		text {Cancel}
	)
)