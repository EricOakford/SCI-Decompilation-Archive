;;; Sierra Script 1.0 - (do not remove this comment)
(script# rInputMessage)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm8 0
)

(instance rm8 of Room
	(properties
		picture rOpening
	)
	
	(method (init)
		(Load VIEW rOpening)
		((View new:)
			view: rOpening
			loop: 0
			cel: 0
			ignoreActors:
			setPri: 14
			posn: 249 102
			addToPic:
		)
		((View new:)
			view: rOpening
			loop: 1
			cel: 0
			ignoreActors:
			setPri: 13
			posn: 169 94
			addToPic:
		)
		(super init:)
		(self setScript: rm8Script)
		(= christmasBuf {Merry Christmas})
		(= newYearBuf {Happy New Year})
		(= seasonsBuf {Season's Greetings})
		(= holidayBuf {Happy Holiday})
	)
)

(instance rm8Script of Script
	;EO: This has been successfully decompiled!
	; The selectors were wrong, but I manually corrected them.
	(method (doit &tmp [temp0 30] i temp31 theMacro temp33 numLetters temp35 customLen)
		(User canInput: TRUE)
		(Print 8 0
			#title {Enter your own message}
			#edit @customBuf 50
			#font 999
			#width 250
		)
		(User canInput: FALSE)
		(= numLetters 0)
		(= i 0)
		(= customLen (StrLen @customBuf))
		(if (<= i customLen)
			(if (!= (= temp31 (StrAt @customBuf i)) 37)
				(StrAt @theLen numLetters temp31)
				(++ numLetters)
				(++ i)
			)
			(= temp35 0)
			(= temp33 i)
			(++ temp33)
			(+= i 2)
			;this was the undecompilable result.
			; It may need corrections.
			(cond
				((== (= theMacro (StrAt @customBuf temp33)) `m)
					(StrCat @theLen christmasBuf)
					(+= numLetters 15)
					(= temp35 1)
				)
				((== theMacro `n)
					(StrCat @theLen newYearBuf)
					(+= numLetters 14)
					(= temp35 1)
				)
				((== theMacro `s)
					(StrCat @theLen seasonsBuf)
					(+= numLetters 18)
					(= temp35 1)
				)
				((== theMacro `h)
					(StrCat @theLen holidayBuf)
					(+= numLetters 13)
					(= temp35 1)
				)
			)
			(== temp35 0)
			(StrAt @theLen numLetters temp31)
			(++ numLetters)
			(StrAt @theLen numLetters theMacro)
			(++ numLetters)
		)
		(= global310 {_____})
		(StrCat @theLen global310)
		(curRoom newRoom: 1)
	)
	
;;;	(method (doit &tmp [temp0 30] i temp31 theMacro temp33 numLetters temp35 customLen)
;;;		;EO: Keeping this here to allow for corrections to the above code.
;;;		(asm
;;;			pushi    #canInput
;;;			pushi    TRUE
;;;			pushi    1
;;;			class    User
;;;			send     6
;;;			pushi    11
;;;			pushi    8
;;;			pushi    0
;;;			pushi    #title
;;;			lofsa    {Enter your own message}
;;;			push    
;;;			pushi    #edit
;;;			lea      @customBuf
;;;			push    
;;;			pushi    50
;;;			pushi    #font
;;;			pushi    999
;;;			pushi    #width
;;;			pushi    250
;;;			calle    Print,  22
;;;			pushi    #canInput
;;;			pushi    TRUE
;;;			pushi    0
;;;			class    User
;;;			send     6
;;;			ldi      0
;;;			sat      numLetters
;;;			ldi      0
;;;			sat      i
;;;			pushi    1
;;;			lea      @customBuf
;;;			push    
;;;			callk    StrLen,  2
;;;			sat      customLen
;;;	;EO: Calls to code_0125 prevented decompilation.
;;;code_0125:
;;;			lst      i
;;;			lat      customLen
;;;			le?     
;;;			bnt      code_0223
;;;			pushi    2
;;;			lea      @customBuf
;;;			push    
;;;			lst      i
;;;			callk    StrAt,  4
;;;			sat      temp31
;;;			push    
;;;			ldi      37
;;;			ne?     
;;;			bnt      code_0158
;;;			pushi    3
;;;			lea      @theLen
;;;			push    
;;;			lst      numLetters
;;;			lst      temp31
;;;			callk    StrAt,  6
;;;			+at      numLetters
;;;			+at      i
;;;			;jmp      code_0125
;;;code_0158:
;;;			ldi      0
;;;			sat      temp35
;;;			lat      i
;;;			sat      temp33
;;;			+at      temp33
;;;			lst      i
;;;			ldi      2
;;;			add     
;;;			sat      i
;;;			pushi    2
;;;			lea      @customBuf
;;;			push    
;;;			lst      temp33
;;;			callk    StrAt,  4
;;;			sat      theMacro
;;;			push    
;;;			ldi      109
;;;			eq?     
;;;			bnt      code_0196
;;;			pushi    2
;;;			lea      @theLen
;;;			push    
;;;			lsg      christmasBuf
;;;			callk    StrCat,  4
;;;			lst      numLetters
;;;			ldi      15
;;;			add     
;;;			sat      numLetters
;;;			ldi      1
;;;			sat      temp35
;;;code_0196:
;;;			lst      theMacro
;;;			ldi      #yStep
;;;			eq?     
;;;			bnt      code_01b6
;;;			pushi    2
;;;			lea      @theLen
;;;			push    
;;;			lsg      newYearBuf
;;;			callk    StrCat,  4
;;;			lst      numLetters
;;;			ldi      #cel
;;;			add     
;;;			sat      numLetters
;;;			ldi      1
;;;			sat      temp35
;;;code_01b6:
;;;			lst      theMacro
;;;			ldi      115
;;;			eq?     
;;;			bnt      code_01d6
;;;			pushi    2
;;;			lea      @theLen
;;;			push    
;;;			lsg      seasonsBuf
;;;			callk    StrCat,  4
;;;			lst      numLetters
;;;			ldi      #nsTop
;;;			add     
;;;			sat      numLetters
;;;			ldi      1
;;;			sat      temp35
;;;code_01d6:
;;;			lst      theMacro
;;;			ldi      #b-xAxis
;;;			eq?     
;;;			bnt      code_01f6
;;;			pushi    2
;;;			lea      @theLen
;;;			push    
;;;			lsg      holidayBuf
;;;			callk    StrCat,  4
;;;			lst      numLetters
;;;			ldi      13
;;;			add     
;;;			sat      numLetters
;;;			ldi      1
;;;			sat      temp35
;;;code_01f6:
;;;			lst      temp35
;;;			ldi      0
;;;			eq?     
;;;			;bnt      code_0125
;;;			pushi    3
;;;			lea      @theLen
;;;			push    
;;;			lst      numLetters
;;;			lst      temp31
;;;			callk    StrAt,  6
;;;			+at      numLetters
;;;			pushi    3
;;;			lea      @theLen
;;;			push    
;;;			lst      numLetters
;;;			lst      theMacro
;;;			callk    StrAt,  6
;;;			+at      numLetters
;;;			;jmp      code_0125
;;;code_0223:
;;;			lofsa    {_____}
;;;			sag      global310
;;;			pushi    2
;;;			lea      @theLen
;;;			push    
;;;			lsg      global310
;;;			callk    StrCat,  4
;;;			pushi    #newRoom
;;;			pushi    1
;;;			pushi    1
;;;			lag      curRoom
;;;			send     6
;;;			ret     
;;;		)
;;;	)
)
