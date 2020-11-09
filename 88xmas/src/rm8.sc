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
	
	(method (doit &tmp [temp0 30] temp30 temp31 temp32 temp33 temp34 temp35 temp36)
		;EO: Maybe someone can figure this one out.
		(asm
			pushi    #canInput
			pushi    TRUE
			pushi    1
			class    User
			send     6
			pushi    11
			pushi    8
			pushi    0
			pushi    #title
			lofsa    {Enter your own message}
			push    
			pushi    #edit
			lea      @customBuf
			push    
			pushi    #time
			pushi    #font
			pushi    999
			pushi    #width
			pushi    250
			calle    Print,  22
			pushi    #canInput
			pushi    1
			pushi    0
			class    User
			send     6
			ldi      0
			sat      temp34
			ldi      0
			sat      temp30
			pushi    1
			lea      @customBuf
			push    
			callk    StrLen,  2
			sat      temp36
code_0125:
			lst      temp30
			lat      temp36
			le?     
			bnt      code_0223
			pushi    2
			lea      @customBuf
			push    
			lst      temp30
			callk    StrAt,  4
			sat      temp31
			push    
			ldi      37
			ne?     
			bnt      code_0158
			pushi    3
			lea      @theLen
			push    
			lst      temp34
			lst      temp31
			callk    StrAt,  6
			+at      temp34
			+at      temp30
			jmp      code_0125
code_0158:
			ldi      0
			sat      temp35
			lat      temp30
			sat      temp33
			+at      temp33
			lst      temp30
			ldi      2
			add     
			sat      temp30
			pushi    2
			lea      @customBuf
			push    
			lst      temp33
			callk    StrAt,  4
			sat      temp32
			push    
			ldi      109
			eq?     
			bnt      code_0196
			pushi    2
			lea      @theLen
			push    
			lsg      christmasBuf
			callk    StrCat,  4
			lst      temp34
			ldi      15
			add     
			sat      temp34
			ldi      1
			sat      temp35
code_0196:
			lst      temp32
			ldi      #yStep
			eq?     
			bnt      code_01b6
			pushi    2
			lea      @theLen
			push    
			lsg      newYearBuf
			callk    StrCat,  4
			lst      temp34
			ldi      #cel
			add     
			sat      temp34
			ldi      1
			sat      temp35
code_01b6:
			lst      temp32
			ldi      115
			eq?     
			bnt      code_01d6
			pushi    2
			lea      @theLen
			push    
			lsg      seasonsBuf
			callk    StrCat,  4
			lst      temp34
			ldi      #nsTop
			add     
			sat      temp34
			ldi      1
			sat      temp35
code_01d6:
			lst      temp32
			ldi      #b-xAxis
			eq?     
			bnt      code_01f6
			pushi    2
			lea      @theLen
			push    
			lsg      holidayBuf
			callk    StrCat,  4
			lst      temp34
			ldi      13
			add     
			sat      temp34
			ldi      1
			sat      temp35
code_01f6:
			lst      temp35
			ldi      0
			eq?     
			bnt      code_0125
			pushi    3
			lea      @theLen
			push    
			lst      temp34
			lst      temp31
			callk    StrAt,  6
			+at      temp34
			pushi    3
			lea      @theLen
			push    
			lst      temp34
			lst      temp32
			callk    StrAt,  6
			+at      temp34
			jmp      code_0125
code_0223:
			lofsa    {_____}
			sag      global310
			pushi    2
			lea      @theLen
			push    
			lsg      global310
			callk    StrCat,  4
			pushi    #newRoom
			pushi    1
			pushi    1
			lag      curRoom
			send     6
			ret     
		)
	)
)
