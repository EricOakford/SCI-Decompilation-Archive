;;; Sierra Script 1.0 - (do not remove this comment)
(script# SAVE)
(include game.sh)
(use Main)
(use Intrface)
(use Language)
(use PrintD)

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

	(method (doit theComment &tmp oldStatus fd theRet [names 361] [nums 21] [str 140])
		(asm
			pushSelf
			class    Restore
			eq?     
			bnt      code_024c
			lap      argc
			bnt      code_024c
			lap      theComment
			bnt      code_024c
			pushi    2
			pushi    0
			pushi    4
			lea      @str
			push    
			pushi    990
			pushi    0
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			callk    Format,  8
			push    
			callk    FileIO,  4
			sat      oldStatus
			push    
			ldi      65535
			eq?     
			bnt      code_0245
			ret     
code_0245:
			pushi    2
			pushi    1
			lst      oldStatus
			callk    FileIO,  4
code_024c:
			pushi    #init
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     10
			not     
			bnt      code_0265
			ldi      65535
			ret     
code_0265:
			lsl      theStatus
			dup     
			ldi      0
			eq?     
			bnt      code_027f
			lal      numGames
			bnt      code_029c
			lofsa    okI
			jmp      code_029c
			lofsa    changeDirI
			jmp      code_029c
code_027f:
			dup     
			ldi      1
			eq?     
			bnt      code_028c
			lofsa    editI
			jmp      code_029c
code_028c:
			dup     
			ldi      2
			eq?     
			bnt      code_0299
			lofsa    okI
			jmp      code_029c
code_0299:
			lofsa    changeDirI
code_029c:
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
			sat      theRet
			lsl      i
			lofsa    changeDirI
			eq?     
			bnt      code_0313
			pushi    1
			lsg      curSaveDir
			call     GetDirectory,  2
			bnt      code_0265
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
			bnt      code_02fb
			ldi      65535
			sat      fd
			jmp      code_0558
code_02fb:
			pushi    #dispose
			pushi    0
			pushi    103
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     14
			jmp      code_0265
code_0313:
			lsl      theStatus
			ldi      2
			eq?     
			bnt      code_0349
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_0349
			pushi    #doit
			pushi    1
			pushi    2
			lsp      theComment
			lat      theRet
			leai     @names
			push    
			callk    StrCpy,  4
			push    
			lofsa    GetReplaceName
			send     6
			bnt      code_0265
			lal      selected
			lati     nums
			sat      fd
			jmp      code_0558
			jmp      code_0265
code_0349:
			lsl      theStatus
			ldi      1
			eq?     
			bnt      code_03ff
			lsl      i
			lofsa    okI
			eq?     
			bt       code_0363
			lsl      i
			lofsa    editI
			eq?     
			bnt      code_03ff
code_0363:
			pushi    1
			lsp      theComment
			callk    StrLen,  2
			push    
			ldi      0
			eq?     
			bnt      code_0378
			pushi    0
			call     NeedDescription,  0
			jmp      code_0265
code_0378:
			ldi      65535
			sat      fd
			ldi      0
			sal      i
code_0380:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_03a2
			pushi    2
			lsp      theComment
			lsl      i
			ldi      18
			mul     
			leai     @names
			push    
			callk    StrCmp,  4
			sat      fd
			not     
			bnt      code_039d
code_039d:
			+al      i
			jmp      code_0380
code_03a2:
			lat      fd
			not     
			bnt      code_03b2
			lal      i
			lati     nums
			sat      fd
			jmp      code_0558
code_03b2:
			lsl      numGames
			ldi      20
			eq?     
			bnt      code_03c4
			lal      selected
			lati     nums
			sat      fd
			jmp      code_0558
code_03c4:
			ldi      0
			sat      fd
code_03c8:
			ldi      1
			bnt      code_0558
			ldi      0
			sal      i
code_03d1:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_03e9
			lst      fd
			lal      i
			lati     nums
			eq?     
			bnt      code_03e4
code_03e4:
			+al      i
			jmp      code_03d1
code_03e9:
			lsl      i
			lal      numGames
			eq?     
			bnt      code_03f4
			jmp      code_0558
code_03f4:
			+at      fd
			jmp      code_03c8
			jmp      code_0558
			jmp      code_0265
code_03ff:
			lsl      i
			lofsa    deleteI
			eq?     
			bnt      code_04ff
			pushi    8
			lofsa    {Are you sure you want to\0D\ndelete this saved game?}
			push    
			pushi    102
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
			bnt      code_042b
			jmp      code_0265
code_042b:
			pushi    #name
			pushi    1
			pushi    5
			lea      @str
			push    
			pushi    990
			pushi    1
			lsg      curSaveDir
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			callk    Format,  10
			push    
			pushi    174
			pushi    1
			pushi    2
			pushi    #new
			pushi    0
			class    51
			send     4
			sat      oldStatus
			send     12
			ldi      2570
			sat      fd
			ldi      0
			sal      i
code_0461:
			lsl      i
			lal      numGames
			lt?     
			bnt      code_04a5
			lsl      i
			lal      selected
			ne?     
			bnt      code_04a0
			pushi    #write
			pushi    2
			lal      i
			leai     @nums
			push    
			pushi    2
			lat      oldStatus
			send     8
			pushi    314
			pushi    #superClass
			lsl      i
			ldi      18
			mul     
			leai     @names
			push    
			lat      oldStatus
			send     6
			pushi    #write
			pushi    2
			lea      @fd
			push    
			pushi    1
			lat      oldStatus
			send     8
code_04a0:
			+al      i
			jmp      code_0461
code_04a5:
			ldi      65535
			sat      fd
			pushi    #write
			pushi    2
			lea      @fd
			push    
			pushi    2
			pushi    318
			pushi    0
			pushi    104
			pushi    0
			lat      oldStatus
			send     16
			pushi    6
			lea      @str
			push    
			pushi    990
			pushi    2
			lsg      curSaveDir
			pushi    #name
			pushi    0
			lag      theGame
			send     4
			push    
			lal      selected
			lsti     nums
			callk    Format,  12
			pushi    2
			pushi    4
			lea      @str
			push    
			callk    FileIO,  4
			pushi    #dispose
			pushi    0
			pushi    103
			pushi    3
			lsp      theComment
			lea      @names
			push    
			lea      @nums
			push    
			self     14
			jmp      code_0265
code_04ff:
			lsl      i
			lofsa    okI
			eq?     
			bnt      code_0515
			lal      selected
			lati     nums
			sat      fd
			jmp      code_0558
			jmp      code_0265
code_0515:
			lsl      i
			ldi      0
			eq?     
			bt       code_0526
			lsl      i
			lofsa    cancelI
			eq?     
			bnt      code_0530
code_0526:
			ldi      65535
			sat      fd
			jmp      code_0558
			jmp      code_0265
code_0530:
			lsl      theStatus
			ldi      1
			eq?     
			bnt      code_0265
			pushi    #cursor
			pushi    1
			pushi    1
			pushi    2
			lsp      theComment
			lat      theRet
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
			jmp      code_0265
code_0558:
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
			lat      fd
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
				SAVE 3
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
				(Format @str SAVE 4 @newDir)
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
		type dSelector
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