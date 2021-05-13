;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include sci.sh)
(use Main)
(use LBRoom)
(use LBEgo)
(use Print)

(public
	whereTo 0
)

(instance whereTo of LBRoom
	(properties
		picture 780
	)
	
	(method (init &tmp [temp0 10] temp10 temp11)
		(asm
			pushi    #init
			pushi    0
			super    LBRoom,  4
			ldi      0
			sat      temp0
			ldi      0
			sat      temp10
			pushi    #addText
			pushi    7
			pushi    2
			pushi    0
			pushi    4
			pushi    1
			pushi    0
			pushi    0
			pushi    10
			pushi    207
			pushi    4
			lea      @temp0
			push    
			pushi    5
			pushi    115
			pushi    0
			pushi    212
			pushi    8
			pushi    105
			pushi    2
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    18
			pushi    10
			pushi    212
			pushi    8
			pushi    140
			pushi    2
			pushi    0
			pushi    0
			pushi    2
			pushi    100
			pushi    18
			pushi    10
			pushi    212
			pushi    8
			pushi    210
			pushi    2
			pushi    0
			pushi    0
			pushi    3
			pushi    0
			pushi    36
			pushi    10
			pushi    212
			pushi    8
			pushi    260
			pushi    2
			pushi    0
			pushi    0
			pushi    4
			pushi    100
			pushi    36
			pushi    10
			pushi    212
			pushi    8
			pushi    280
			pushi    2
			pushi    0
			pushi    0
			pushi    5
			pushi    0
			pushi    54
			pushi    10
			pushi    212
			pushi    8
			pushi    300
			pushi    2
			pushi    0
			pushi    0
			pushi    6
			pushi    100
			pushi    54
			pushi    10
			pushi    212
			pushi    8
			pushi    65336
			pushi    2
			pushi    0
			pushi    0
			pushi    7
			pushi    0
			pushi    72
			pushi    10
			pushi    212
			pushi    8
			pushi    250
			pushi    2
			pushi    0
			pushi    0
			pushi    8
			pushi    100
			pushi    72
			pushi    10
			pushi    212
			pushi    8
			pushi    65236
			pushi    2
			pushi    0
			pushi    0
			pushi    33
			pushi    0
			pushi    90
			pushi    10
			pushi    212
			pushi    8
			pushi    65436
			pushi    2
			pushi    0
			pushi    0
			pushi    9
			pushi    100
			pushi    90
			pushi    10
			pushi    110
			pushi    0
			class    Print
			send     234
			sat      temp10
			lat      temp0
			bnt      code_0108
			pushi    1
			lea      @temp0
			push    
			callk    ReadNumber,  2
			sat      temp10
code_0108:
			lst      temp10
			ldi      65436
			eq?     
			bnt      code_011a
			pushi    #restore
			pushi    0
			lag      theGame
			send     4
			ldi      0
			sat      temp10
code_011a:
			lst      temp10
			ldi      65236
			eq?     
			bnt      code_0225
			pushi    #addText
			pushi    7
			pushi    2
			pushi    0
			pushi    4
			pushi    3
			pushi    0
			pushi    0
			pushi    10
			pushi    212
			pushi    8
			pushi    1
			pushi    2
			pushi    0
			pushi    3
			pushi    1
			pushi    0
			pushi    20
			pushi    10
			pushi    212
			pushi    8
			pushi    2
			pushi    2
			pushi    0
			pushi    3
			pushi    2
			pushi    0
			pushi    40
			pushi    10
			pushi    212
			pushi    8
			pushi    3
			pushi    2
			pushi    0
			pushi    3
			dup     
			pushi    0
			pushi    60
			pushi    10
			pushi    212
			pushi    8
			pushi    4
			pushi    2
			pushi    0
			pushi    3
			pushi    4
			pushi    0
			pushi    80
			pushi    10
			pushi    212
			pushi    8
			pushi    5
			pushi    2
			pushi    0
			pushi    3
			pushi    5
			pushi    0
			pushi    100
			pushi    10
			pushi    212
			pushi    8
			pushi    6
			pushi    2
			pushi    0
			pushi    3
			pushi    6
			pushi    0
			pushi    120
			pushi    10
			pushi    110
			pushi    0
			class    Print
			send     142
			sat      temp10
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_01b3
			ldi      0
			sag      currentAct
			ldi      0
			sag      score
			jmp      code_0215
code_01b3:
			dup     
			ldi      2
			eq?     
			bnt      code_01c7
			pushi    0
			callb    StartAct2,  0
			ldi      1
			sag      currentAct
			ldi      5
			sag      score
			jmp      code_0215
code_01c7:
			dup     
			ldi      3
			eq?     
			bnt      code_01db
			pushi    0
			callb    StartAct3,  0
			ldi      2
			sag      currentAct
			ldi      12
			sag      score
			jmp      code_0215
code_01db:
			dup     
			ldi      4
			eq?     
			bnt      code_01ef
			pushi    0
			callb    StartAct4,  0
			ldi      3
			sag      currentAct
			ldi      43
			sag      score
			jmp      code_0215
code_01ef:
			dup     
			ldi      5
			eq?     
			bnt      code_0203
			pushi    0
			callb    StartAct5,  0
			ldi      4
			sag      currentAct
			ldi      58
			sag      score
			jmp      code_0215
code_0203:
			dup     
			ldi      6
			eq?     
			bnt      code_0215
			pushi    0
			callb    StartAct6,  0
			ldi      5
			sag      currentAct
			ldi      61
			sag      score
code_0215:
			toss    
			ldi      26
			sat      temp10
			pushi    #get
			pushi    2
			pushi    65535
			pushi    2
			class    ego
			send     8
code_0225:
			lst      temp10
			ldi      65336
			eq?     
			bnt      code_0455
			ldi      65535
			sat      temp10
			ldi      0
			sat      temp11
code_0236:
			lst      temp10
			ldi      65535
			eq?     
			bnt      code_0455
			lat      temp11
			not     
			bnt      code_0353
			pushi    #addText
			pushi    7
			pushi    2
			pushi    0
			pushi    4
			pushi    2
			pushi    0
			pushi    0
			pushi    10
			pushi    207
			pushi    4
			lea      @temp0
			push    
			pushi    5
			pushi    165
			pushi    0
			pushi    212
			pushi    8
			pushi    350
			pushi    2
			pushi    0
			pushi    0
			pushi    10
			pushi    0
			pushi    18
			pushi    10
			pushi    212
			pushi    8
			pushi    420
			pushi    2
			pushi    0
			pushi    0
			pushi    12
			pushi    140
			pushi    18
			pushi    10
			pushi    212
			pushi    8
			pushi    430
			pushi    2
			pushi    0
			pushi    0
			pushi    13
			pushi    0
			pushi    36
			pushi    10
			pushi    212
			pushi    8
			pushi    440
			pushi    2
			pushi    0
			pushi    0
			pushi    14
			pushi    140
			pushi    36
			pushi    10
			pushi    212
			pushi    8
			pushi    450
			pushi    2
			pushi    0
			pushi    0
			pushi    15
			pushi    0
			pushi    54
			pushi    10
			pushi    212
			pushi    8
			pushi    480
			pushi    2
			pushi    0
			pushi    0
			pushi    16
			pushi    140
			pushi    54
			pushi    0
			class    Print
			send     150
			pushi    #addButton
			pushi    8
			pushi    490
			pushi    2
			pushi    0
			pushi    0
			pushi    17
			pushi    0
			pushi    72
			pushi    10
			pushi    212
			pushi    8
			pushi    500
			pushi    2
			pushi    0
			pushi    0
			pushi    18
			pushi    140
			pushi    72
			pushi    10
			pushi    212
			pushi    8
			pushi    560
			pushi    2
			pushi    0
			pushi    0
			pushi    19
			pushi    0
			pushi    90
			pushi    10
			pushi    212
			pushi    8
			pushi    520
			pushi    2
			pushi    0
			pushi    0
			pushi    20
			pushi    140
			pushi    90
			pushi    10
			pushi    212
			pushi    8
			pushi    550
			pushi    2
			pushi    0
			pushi    0
			pushi    21
			pushi    0
			pushi    108
			pushi    10
			pushi    212
			pushi    8
			pushi    65535
			pushi    2
			pushi    0
			pushi    0
			pushi    23
			pushi    140
			pushi    108
			pushi    10
			pushi    110
			pushi    0
			class    Print
			send     124
			sat      temp10
			jmp      code_0436
code_0353:
			pushi    #addText
			pushi    7
			pushi    2
			pushi    0
			pushi    4
			pushi    2
			pushi    0
			pushi    0
			pushi    10
			pushi    207
			pushi    4
			lea      @temp0
			push    
			pushi    5
			pushi    165
			pushi    0
			pushi    212
			pushi    8
			pushi    600
			pushi    2
			pushi    0
			pushi    0
			pushi    24
			pushi    0
			pushi    18
			pushi    10
			pushi    212
			pushi    8
			pushi    650
			pushi    2
			pushi    0
			pushi    0
			pushi    25
			pushi    140
			pushi    18
			pushi    10
			pushi    212
			pushi    8
			pushi    610
			pushi    2
			pushi    0
			pushi    0
			pushi    26
			pushi    0
			pushi    36
			pushi    10
			pushi    212
			pushi    8
			pushi    630
			pushi    2
			pushi    0
			pushi    0
			pushi    27
			pushi    140
			pushi    36
			pushi    10
			pushi    212
			pushi    8
			pushi    640
			pushi    2
			pushi    0
			pushi    0
			pushi    32
			pushi    0
			pushi    54
			pushi    10
			pushi    212
			pushi    8
			pushi    730
			pushi    2
			pushi    0
			pushi    0
			pushi    28
			pushi    140
			pushi    54
			pushi    10
			pushi    212
			pushi    8
			pushi    720
			pushi    2
			pushi    0
			pushi    0
			pushi    29
			pushi    0
			pushi    72
			pushi    10
			pushi    212
			pushi    8
			pushi    710
			pushi    2
			pushi    0
			pushi    0
			pushi    30
			pushi    140
			pushi    72
			pushi    10
			pushi    212
			pushi    8
			pushi    700
			pushi    2
			pushi    0
			pushi    0
			pushi    31
			pushi    0
			pushi    90
			pushi    10
			pushi    212
			pushi    8
			pushi    65535
			pushi    2
			pushi    0
			pushi    0
			pushi    23
			pushi    140
			pushi    90
			pushi    10
			pushi    110
			pushi    0
			class    Print
			send     234
			sat      temp10
code_0436:
			lat      temp0
			bnt      code_0444
			pushi    1
			lea      @temp0
			push    
			callk    ReadNumber,  2
			sat      temp10
code_0444:
			lst      temp10
			ldi      65535
			eq?     
			bnt      code_0236
			pushi    1
			lat      temp11
			sub     
			sat      temp11
			jmp      code_0236
code_0455:
			lat      temp10
			not     
			bnt      code_045e
			ldi      105
			sat      temp10
code_045e:
			pushi    #newRoom
			pushi    1
			lst      temp10
			self     6
			ret     
		)
	)
)
