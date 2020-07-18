;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm25 0
)

(local
	egoCarDoor
	hood
	lightPosn
	[local3 3]
	motelDoor1
	local7
	motelDoor2
	local9
	local10
	local11
	local12
	[hoodPosn 10]
)
(instance keithAct of Actor
	(properties)
)

(instance gasBomb of Actor
	(properties)
)

(instance rm25 of Room
	(properties
		picture 25
		style HSHUTTER
	)
	
	(method (init)
		(super init:)
		((= hood (Prop new:))
			view: 51
			loop: 5
			cel: 2
			posn: [hoodPosn 0] [hoodPosn 1]
			setPri: 14
			init:
		)
		(Load VIEW 54)
		(Load VIEW 51)
		(Load VIEW 251)
		(Load VIEW 30)
		(Load VIEW 268)
		(Load VIEW 50)
		(Load VIEW 53)
		((= egoCarDoor (Actor new:))
			view: 54
			setLoop: 1
			setCel: 1
			posn: 160 180
			init:
			addToPic:
		)
		((= motelDoor1 (Prop new:))
			view: 251
			setLoop: 1
			setCel: 0
			setPri: 9
			posn: 261 155
			init:
			ignoreActors:
			addToPic:
		)
		((= motelDoor2 (Prop new:))
			view: 251
			setLoop: 2
			setCel: 0
			setCycle: 0
			setPri: 7
			posn: 104 72
			init:
			addToPic:
		)
		((View new:)
			view: 251
			setLoop: 0
			setCel: 2
			setPri: 10
			posn: 167 138
			init:
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 251
			setLoop: 0
			setCel: 0
			setPri: 0
			posn: 67 7
			init:
			ignoreActors: 0
			addToPic:
		)
		((View new:)
			view: 251
			loop: 6
			cel: 0
			setPri: 3
			posn: 104 110
			ignoreActors:
			init:
			addToPic:
		)
		(ego
			view: 6
			loop: 1
			cel: 1
			posn: 281 172
			priority: 13
			init:
			stopUpd:
		)
		((= keith keithAct)
			view: 53
			loop: 0
			cel: 6
			posn: 120 135
			priority: 9
			init:
			stopUpd:
		)
		(self setScript: swatArrives)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance pLight of Prop
	(properties)
	
	(method (doit)
		(self posn: (+ (lightPosn x?) 4) (- (lightPosn y?) 29))
		(super doit:)
	)
)

(instance swatArrives of Script
	(properties)
	
	(method (changeState newState)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_04ed
			pushi    #view
			pushi    1
			pushi    54
			pushi    196
			pushi    1
			pushi    1
			pushi    157
			pushi    1
			pushi    3
			pushi    193
			pushi    2
			pushi    64
			pushi    156
			pushi    66
			pushi    1
			pushi    11
			pushi    212
			pushi    1
			pushi    65535
			pushi    85
			pushi    0
			pushi    #new
			pushi    0
			class    Actor
			send     4
			sal      lightPosn
			send     42
			pushi    #view
			pushi    1
			pushi    54
			pushi    196
			pushi    1
			pushi    3
			pushi    157
			pushi    1
			pushi    0
			pushi    66
			pushi    1
			pushi    11
			pushi    121
			pushi    1
			class    Forward
			push    
			pushi    197
			pushi    0
			pushi    60
			pushi    0
			pushi    85
			pushi    0
			lofsa    pLight
			send     42
			pushi    #view
			pushi    1
			pushi    268
			pushi    6
			pushi    1
			pushi    0
			pushi    7
			pushi    1
			pushi    2
			pushi    193
			pushi    2
			pushi    48
			pushi    144
			pushi    66
			pushi    1
			pushi    10
			pushi    197
			pushi    1
			pushi    0
			pushi    85
			pushi    0
			pushi    132
			pushi    0
			pushi    #new
			pushi    0
			class    Prop
			send     4
			sal      local10
			send     46
			pushi    #view
			pushi    1
			pushi    30
			pushi    193
			pushi    2
			pushi    73
			pushi    164
			pushi    66
			pushi    1
			pushi    12
			pushi    121
			pushi    1
			class    Walk
			push    
			pushi    85
			pushi    0
			pushi    132
			pushi    0
			pushi    #new
			pushi    0
			class    Actor
			send     4
			sal      local11
			send     34
			pushi    #view
			pushi    1
			pushi    51
			pushi    196
			pushi    1
			pushi    8
			pushi    157
			pushi    1
			pushi    255
			pushi    193
			pushi    2
			pushi    82
			pushi    151
			pushi    66
			pushi    1
			pushi    12
			pushi    197
			pushi    1
			pushi    0
			pushi    85
			pushi    0
			pushi    132
			pushi    0
			pushi    #new
			pushi    0
			class    Prop
			send     4
			sal      local12
			send     46
			pushi    1
			pushi    14
			callb    Btst,  2
			bnt      code_04d8
			pushi    #stopUpd
			pushi    0
			lal      lightPosn
			send     4
			pushi    #stopUpd
			pushi    0
			pushi    198
			pushi    0
			lal      local12
			send     8
			pushi    #stopUpd
			pushi    0
			pushi    198
			pushi    0
			lal      local10
			send     8
			pushi    #stopUpd
			pushi    0
			pushi    198
			pushi    0
			lal      local11
			send     8
			pushi    #setScript
			pushi    1
			pushi    0
			pToa     client
			send     6
			ret     
code_04d8:
			pushi    #posn
			pushi    2
			pushi    65476
			pushi    157
			lal      lightPosn
			send     8
			pushi    #cue
			pushi    0
			self     4
			jmp      code_06c3
code_04ed:
			dup     
			ldi      1
			eq?     
			bnt      code_0512
			pushi    #posn
			pushi    2
			pushi    65476
			pushi    157
			pushi    208
			pushi    4
			class    MoveTo
			push    
			pushi    64
			pushi    157
			pushSelf
			lal      lightPosn
			send     20
			jmp      code_06c3
code_0512:
			dup     
			ldi      2
			eq?     
			bnt      code_0530
			pushi    #show
			pushi    0
			pushi    157
			pushi    1
			pushi    0
			pushi    121
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lal      local12
			send     18
			jmp      code_06c3
code_0530:
			dup     
			ldi      3
			eq?     
			bnt      code_0556
			pushi    #stopUpd
			pushi    0
			lal      local12
			send     4
			pushi    #show
			pushi    0
			lal      local11
			send     4
			pushi    #show
			pushi    0
			lal      local10
			send     4
			ldi      1
			aTop     cycles
			jmp      code_06c3
code_0556:
			dup     
			ldi      4
			eq?     
			bnt      code_0574
			pushi    #stopUpd
			pushi    0
			lal      local10
			send     4
			pushi    #stopUpd
			pushi    0
			lal      lightPosn
			send     4
			ldi      3
			aTop     seconds
			jmp      code_06c3
code_0574:
			dup     
			ldi      5
			eq?     
			bnt      code_05a7
			pushi    #view
			pushi    1
			pushi    268
			pushi    196
			pushi    1
			pushi    2
			pushi    193
			pushi    2
			pushi    73
			pushi    164
			pushi    159
			pushi    1
			pushi    1
			pushi    157
			pushi    1
			pushi    0
			pushi    121
			pushi    2
			class    EndLoop
			push    
			pushSelf
			lal      local11
			send     40
			jmp      code_06c3
code_05a7:
			dup     
			ldi      6
			eq?     
			bnt      code_0618
			pushi    #view
			pushi    1
			pushi    268
			pushi    196
			pushi    1
			pushi    3
			pushi    159
			pushi    1
			pushi    1
			pushi    157
			pushi    1
			pushi    0
			pushi    121
			pushi    1
			class    EndLoop
			push    
			lal      local11
			send     30
			pushi    #view
			pushi    1
			pushi    268
			pushi    196
			pushi    1
			pushi    4
			pushi    66
			pushi    1
			pushi    14
			pushi    217
			pushi    2
			pushi    10
			dup     
			pushi    121
			pushi    1
			class    Forward
			push    
			pushi    197
			pushi    0
			pushi    212
			pushi    1
			pushi    65535
			pushi    193
			pushi    2
			pushi    75
			pushi    140
			pushi    208
			pushi    4
			class    MoveTo
			push    
			pushi    225
			pushi    111
			pushSelf
			pushi    85
			pushi    0
			lofsa    gasBomb
			send     66
			jmp      code_06c3
code_0618:
			dup     
			ldi      7
			eq?     
			bnt      code_06b4
			pushi    #dispose
			pushi    0
			lofsa    gasBomb
			send     4
			pushi    #view
			pushi    1
			pushi    30
			pushi    193
			pushi    2
			pushi    73
			pushi    164
			pushi    196
			pushi    1
			pushi    0
			pushi    157
			pushi    1
			pushi    0
			pushi    159
			pushi    1
			pushi    0
			lal      local11
			send     32
			pushi    #view
			pushi    1
			pushi    251
			pushi    196
			pushi    1
			pushi    0
			pushi    157
			pushi    1
			pushi    1
			pushi    66
			pushi    1
			pushi    9
			pushi    193
			pushi    2
			pushi    229
			pushi    121
			pushi    85
			pushi    0
			pushi    194
			pushi    0
			pushi    199
			pushi    0
			pushi    #new
			pushi    0
			class    View
			send     4
			send     44
			pushi    #view
			pushi    1
			pushi    251
			pushi    196
			pushi    1
			pushi    8
			pushi    157
			pushi    1
			pushi    0
			pushi    193
			pushi    2
			pushi    229
			pushi    115
			pushi    66
			pushi    1
			pushi    10
			pushi    85
			pushi    0
			pushi    121
			pushi    1
			class    Forward
			push    
			pushi    #new
			pushi    0
			class    Prop
			send     4
			sal      local9
			send     42
			ldi      3
			aTop     seconds
			jmp      code_06c3
code_06b4:
			dup     
			pushi    #newRoom
			pushi    1
			pushi    22
			lag      curRoom
			send     6
			eq?     
			bnt      code_06c3
code_06c3:
			toss    
			ret     
		)
	)
)
