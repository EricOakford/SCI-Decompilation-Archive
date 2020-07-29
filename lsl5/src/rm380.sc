;;; Sierra Script 1.0 - (do not remove this comment)
(script# 380)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use LoadMany)
(use Actor)
(use System)

(public
	rm380 0
)

(instance rm380 of LLRoom
	(properties
		picture 415
		style PIXELDISSOLVE
	)
	
	(method (init &tmp temp0)
		(LoadMany VIEW 1415 1416)
		(super init:)
		(SetFFRoom 385)
		(curRoom setScript: sCartoon)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0099
			pushi    0
			callb    HandsOff,  0
			ldi      4
			aTop     seconds
			jmp      code_07eb
code_0099:
			dup     
			ldi      1
			eq?     
			bnt      code_00b6
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    0
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_00b6:
			dup     
			ldi      2
			eq?     
			bnt      code_00c4
			ldi      30
			aTop     ticks
			jmp      code_07eb
code_00c4:
			dup     
			ldi      3
			eq?     
			bnt      code_00e1
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    1
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_00e1:
			dup     
			ldi      4
			eq?     
			bnt      code_00ef
			ldi      30
			aTop     ticks
			jmp      code_07eb
code_00ef:
			dup     
			ldi      5
			eq?     
			bnt      code_010c
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    2
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_010c:
			dup     
			ldi      6
			eq?     
			bnt      code_011a
			ldi      30
			aTop     ticks
			jmp      code_07eb
code_011a:
			dup     
			ldi      7
			eq?     
			bnt      code_0138
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    3
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0138:
			dup     
			ldi      8
			eq?     
			bnt      code_0146
			ldi      30
			aTop     ticks
			jmp      code_07eb
code_0146:
			dup     
			ldi      9
			eq?     
			bnt      code_01c4
			pushi    1
			pushi    118
			callb    Btst,  2
			bnt      code_016d
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    4
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_016d:
			pushi    1
			pushi    119
			callb    Btst,  2
			bnt      code_018d
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    5
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_018d:
			pushi    1
			pushi    48
			callb    Btst,  2
			bnt      code_01ad
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    6
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_01ad:
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    7
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_01c4:
			dup     
			ldi      10
			eq?     
			bnt      code_01d2
			ldi      60
			aTop     ticks
			jmp      code_07eb
code_01d2:
			dup     
			ldi      11
			eq?     
			bnt      code_0226
			pushi    1
			pushi    118
			callb    Btst,  2
			bt       code_01f4
			pushi    1
			pushi    119
			callb    Btst,  2
			bt       code_01f4
			pushi    1
			pushi    48
			callb    Btst,  2
			bnt      code_020b
code_01f4:
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    8
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_020b:
			ldi      24
			aTop     state
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    9
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0226:
			dup     
			ldi      12
			eq?     
			bnt      code_0234
			ldi      120
			aTop     ticks
			jmp      code_07eb
code_0234:
			dup     
			ldi      13
			eq?     
			bnt      code_0280
			pushi    #has
			pushi    1
			pushi    5
			lag      ego
			send     6
			bnt      code_0269
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    10
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			pushi    #put
			pushi    1
			pushi    5
			lag      ego
			send     6
			jmp      code_07eb
code_0269:
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    11
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0280:
			dup     
			ldi      14
			eq?     
			bnt      code_028e
			ldi      60
			aTop     ticks
			jmp      code_07eb
code_028e:
			dup     
			ldi      15
			eq?     
			bnt      code_02bc
			pushi    1
			pushi    122
			callb    Btst,  2
			bnt      code_02b5
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    12
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_02b5:
			ldi      2
			aTop     cycles
			jmp      code_07eb
code_02bc:
			dup     
			ldi      16
			eq?     
			bnt      code_02ea
			pushi    1
			pushi    123
			callb    Btst,  2
			bnt      code_02e3
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    13
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_02e3:
			ldi      2
			aTop     cycles
			jmp      code_07eb
code_02ea:
			dup     
			ldi      17
			eq?     
			bnt      code_02f8
			ldi      60
			aTop     ticks
			jmp      code_07eb
code_02f8:
			dup     
			ldi      18
			eq?     
			bnt      code_0316
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    14
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0316:
			dup     
			ldi      19
			eq?     
			bnt      code_0354
			pushi    1
			pushi    126
			callb    Btst,  2
			bnt      code_033d
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    15
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_033d:
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    16
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0354:
			dup     
			ldi      20
			eq?     
			bnt      code_0362
			ldi      60
			aTop     ticks
			jmp      code_07eb
code_0362:
			dup     
			ldi      21
			eq?     
			bnt      code_0404
			pushi    1
			pushi    127
			callb    Btst,  2
			bnt      code_0393
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    17
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			pushi    #put
			pushi    1
			pushi    6
			lag      ego
			send     6
			jmp      code_07eb
code_0393:
			pushi    1
			pushi    128
			callb    Btst,  2
			bnt      code_03be
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    18
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			pushi    #put
			pushi    1
			pushi    6
			lag      ego
			send     6
			jmp      code_07eb
code_03be:
			pushi    1
			pushi    129
			callb    Btst,  2
			bnt      code_03e9
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    19
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			pushi    #put
			pushi    1
			pushi    6
			lag      ego
			send     6
			jmp      code_07eb
code_03e9:
			ldi      24
			aTop     state
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    20
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0404:
			dup     
			ldi      22
			eq?     
			bnt      code_0412
			ldi      60
			aTop     ticks
			jmp      code_07eb
code_0412:
			dup     
			ldi      23
			eq?     
			bnt      code_0430
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    21
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0430:
			dup     
			ldi      24
			eq?     
			bnt      code_043e
			ldi      120
			aTop     ticks
			jmp      code_07eb
code_043e:
			dup     
			ldi      25
			eq?     
			bnt      code_045c
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    22
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_045c:
			dup     
			ldi      26
			eq?     
			bnt      code_049b
			pushi    1
			pushi    150
			callb    Btst,  2
			bnt      code_0484
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    23
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0484:
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    24
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_049b:
			dup     
			ldi      27
			eq?     
			bnt      code_04a9
			ldi      30
			aTop     ticks
			jmp      code_07eb
code_04a9:
			dup     
			ldi      28
			eq?     
			bnt      code_04ec
			pushi    1
			pushi    150
			callb    Btst,  2
			bnt      code_04d1
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    25
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_04d1:
			ldi      38
			aTop     state
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    26
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_04ec:
			dup     
			ldi      29
			eq?     
			bnt      code_04fa
			ldi      60
			aTop     ticks
			jmp      code_07eb
code_04fa:
			dup     
			ldi      30
			eq?     
			bnt      code_0518
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    27
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0518:
			dup     
			ldi      31
			eq?     
			bnt      code_0540
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    28
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			pushi    #put
			pushi    1
			pushi    11
			lag      ego
			send     6
			jmp      code_07eb
code_0540:
			dup     
			ldi      32
			eq?     
			bnt      code_055e
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    29
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_055e:
			dup     
			ldi      33
			eq?     
			bnt      code_056c
			ldi      60
			aTop     ticks
			jmp      code_07eb
code_056c:
			dup     
			ldi      34
			eq?     
			bnt      code_058a
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    30
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_058a:
			dup     
			ldi      35
			eq?     
			bnt      code_05a8
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    31
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_05a8:
			dup     
			ldi      36
			eq?     
			bnt      code_05f1
			pushi    1
			pushi    156
			callb    Btst,  2
			bnt      code_05da
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    32
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			pushi    #put
			pushi    1
			pushi    12
			lag      ego
			send     6
			jmp      code_07eb
code_05da:
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    33
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_05f1:
			dup     
			ldi      37
			eq?     
			bnt      code_0621
			pushi    1
			pushi    156
			callb    Btst,  2
			not     
			bnt      code_061a
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    34
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_061a:
			ldi      2
			aTop     cycles
			jmp      code_07eb
code_0621:
			dup     
			ldi      38
			eq?     
			bnt      code_0651
			pushi    1
			pushi    156
			callb    Btst,  2
			not     
			bnt      code_064a
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    35
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_064a:
			ldi      2
			aTop     cycles
			jmp      code_07eb
code_0651:
			dup     
			ldi      39
			eq?     
			bnt      code_065f
			ldi      30
			aTop     ticks
			jmp      code_07eb
code_065f:
			dup     
			ldi      40
			eq?     
			bnt      code_06bd
			pushi    1
			pushi    150
			callb    Btst,  2
			not     
			bnt      code_06a4
			pushi    1
			pushi    118
			callb    Btst,  2
			bt       code_0689
			pushi    1
			pushi    119
			callb    Btst,  2
			bt       code_0689
			pushi    1
			pushi    48
			callb    Btst,  2
code_0689:
			not     
			bnt      code_06a4
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    36
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_06a4:
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    37
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			ipToa    state
			jmp      code_07eb
code_06bd:
			dup     
			ldi      41
			eq?     
			bnt      code_06db
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    38
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_06db:
			dup     
			ldi      42
			eq?     
			bnt      code_06e9
			ldi      30
			aTop     ticks
			jmp      code_07eb
code_06e9:
			dup     
			ldi      43
			eq?     
			bnt      code_071b
			pushi    1
			pushi    100
			callb    Btst,  2
			bnt      code_0704
			ldi      46
			aTop     state
			ldi      2
			aTop     cycles
			jmp      code_07eb
code_0704:
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    39
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_071b:
			dup     
			ldi      44
			eq?     
			bnt      code_0743
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    40
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			pushi    #get
			pushi    1
			pushi    13
			lag      ego
			send     6
			jmp      code_07eb
code_0743:
			dup     
			ldi      45
			eq?     
			bnt      code_0761
			pushi    6
			lofsa    You
			push    
			pushi    380
			pushi    41
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_0761:
			dup     
			ldi      46
			eq?     
			bnt      code_077f
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    42
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_077f:
			dup     
			ldi      47
			eq?     
			bnt      code_079d
			pushi    6
			lofsa    Desmond
			push    
			pushi    380
			pushi    43
			pushi    108
			pushi    139
			pushSelf
			callb    Say,  12
			jmp      code_07eb
code_079d:
			dup     
			ldi      48
			eq?     
			bnt      code_07d9
			pushi    2
			pushi    1
			pushi    100
			callk    DrawPic,  4
			pushi    #stop
			pushi    0
			lag      theMusic2
			send     4
			pushi    13
			pushi    380
			pushi    44
			pushi    101
			pushi    1
			pushi    102
			lsg      global131
			pushi    105
			lsg      giantFont2
			pushi    106
			pushi    318
			pushi    100
			pushi    1
			pushi    82
			callk    Display,  26
			ldi      2
			aTop     cycles
			jmp      code_07eb
code_07d9:
			dup     
			ldi      49
			eq?     
			bnt      code_07eb
			pushi    #newRoom
			pushi    1
			pushi    385
			lag      curRoom
			send     6
code_07eb:
			toss    
			ret     
		)
	)
)

(instance You of Talker
	(properties
		x 140
		y 180
		nsTop 76
		nsLeft 168
		view 1416
		loop 3
	)
	
	(method (init)
		(= mouth pattiMouth)
		(super init: &rest)
	)
)

(instance pattiMouth of Prop
	(properties
		nsLeft 1
		view 1416
		cycleSpeed 5
	)
)

(instance Desmond of Talker
	(properties
		x 1
		y 180
		nsTop 73
		nsLeft 120
		view 1415
		loop 3
		talkWidth 180
	)
	
	(method (init)
		(= mouth desmondMouth)
		(super init: &rest)
	)
)

(instance desmondMouth of Prop
	(properties
		view 1415
		cycleSpeed 5
	)
)
