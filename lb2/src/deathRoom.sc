;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include sci.sh)
(use Main)
(use LBRoom)
(use Print)

(public
	deathRoom 0
)

(instance deathRoom of LBRoom
	(properties
		picture 780
		horizon 120
		north 350
	)
	
	(method (init &tmp temp0 temp1)
		(asm
			pushi    #init
			pushi    0
			super    LBRoom,  4
			pushi    #number
			pushi    1
			pushi    99
			dup     
			pushi    1
			pushi    1
			pushi    3
			pushi    1
			pushi    1
			pushi    39
			pushi    0
			lag      theMusic
			send     22
			lsg      deathReason
			ldi      15
			eq?     
			bnt      code_0038
			ldi      86
			sat      temp1
			jmp      code_003c
code_0038:
			ldi      62
			sat      temp1
code_003c:
			lsg      deathReason
			ldi      1
			add     
			sat      temp0
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    0
			callk    Animate,  4
code_0050:
			pushi    #addText
			pushi    6
			pushi    1
			pushi    45
			lst      temp0
			pushi    0
			pushi    100
			pushi    0
			pushi    213
			pushi    5
			pushi    99
			pushi    0
			lsg      deathReason
			pushi    0
			pushi    0
			pushi    212
			pushi    8
			pushi    1
			pushi    2
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			lst      temp1
			pushi    99
			pushi    212
			pushi    8
			pushi    2
			pushi    2
			pushi    0
			pushi    0
			pushi    2
			pushi    5
			pushi    70
			dup     
			pushi    62
			pushi    70
			dup     
			callb    WhichLanguage,  10
			push    
			lst      temp1
			pushi    99
			pushi    212
			pushi    8
			pushi    3
			pushi    2
			pushi    0
			pushi    0
			pushi    3
			pushi    140
			lst      temp1
			pushi    99
			pushi    110
			pushi    0
			class    Print
			send     94
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_00bf
			pushi    #restore
			pushi    0
			lag      theGame
			send     4
			jmp      code_00da
code_00bf:
			dup     
			ldi      2
			eq?     
			bnt      code_00ce
			pushi    #restart
			pushi    0
			lag      theGame
			send     4
			jmp      code_00da
code_00ce:
			dup     
			ldi      3
			eq?     
			bnt      code_00da
			ldi      1
			sag      quit
			jmp      code_00de
code_00da:
			toss    
			jmp      code_0050
code_00de:
			ret     
		)
	)
)
