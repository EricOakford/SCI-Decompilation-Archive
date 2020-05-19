;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
(use Intrface)
(use NewFeature)
(use Motion)
(use Game)
(use User)
(use Actor)

(public
	rm7 0
)

(local
	i
	[ripple 2]
	[rippleX 2] = [29 40]
	[rippleY 2] = [40 84]
)
(instance rm7 of Room
	(properties
		picture 7
		horizon 65
		north 10
		east 6
		south 42
		west 8
	)
	
	(method (init)
		(asm
			pushi    2
			pushi    128
			pushi    207
			callk    Load,  4
			pushi    31
			pushi    1
			lsg      prevRoomNum
			dup     
			pToa     north
			eq?     
			bnt      code_0027
			ldi      5
			jmp      code_0048
code_0027:
			dup     
			pToa     west
			eq?     
			bnt      code_0033
			ldi      3
			jmp      code_0048
code_0033:
			dup     
			pToa     east
			eq?     
			bnt      code_003f
			ldi      2
			jmp      code_0048
code_003f:
			dup     
			pToa     south
			eq?     
			bnt      code_0048
			ldi      4
code_0048:
			toss    
			push    
			self     6
			pushi    #init
			pushi    0
			super    Room,  4
			lsg      prevRoomNum
			dup     
			pToa     north
			eq?     
			bnt      code_007c
			pushi    #posn
			pushi    2
			pushi    3
			pushi    310
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			pushi    194
			callb    proc0_17,  6
			push    
			pushi    75
			lag      ego
			send     8
			jmp      code_0153
code_007c:
			dup     
			pToa     south
			eq?     
			bnt      code_0094
			pushi    #posn
			pushi    2
			pushi    168
			pushi    187
			lag      ego
			send     8
			jmp      code_0153
code_0094:
			dup     
			pToa     west
			eq?     
			bnt      code_0112
			lsg      egoInWater
			dup     
			ldi      0
			eq?     
			bnt      code_00df
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			ldi      95
			lt?     
			bnt      code_00c1
			pushi    #posn
			pushi    2
			pushi    6
			pushi    73
			lag      ego
			send     8
			jmp      code_00f8
code_00c1:
			pushi    3
			pushi    147
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    72
			callb    proc0_18,  6
			pushi    #x
			pushi    1
			pushi    4
			lag      ego
			send     6
			jmp      code_00f8
code_00df:
			dup     
			ldi      4
			eq?     
			bnt      code_00f8
			pushi    #y
			pushi    1
			pushi    100
			lag      ego
			send     6
			pushi    #x
			pushi    1
			pushi    4
			lag      ego
			send     6
code_00f8:
			toss    
			pushi    3
			pushi    198
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pTos     horizon
			ldi      4
			add     
			push    
			callb    proc0_17,  6
			jmp      code_0153
code_0112:
			dup     
			pToa     east
			eq?     
			bnt      code_0145
			pushi    #posn
			pushi    2
			pushi    314
			pushi    3
			pushi    187
			pushi    3
			pushi    143
			pushi    #y
			pushi    0
			lag      ego
			send     4
			push    
			pushi    118
			callb    proc0_18,  6
			push    
			pushi    67
			callb    proc0_17,  6
			push    
			lag      ego
			send     8
			jmp      code_0153
code_0145:
			pushi    #posn
			pushi    2
			pushi    314
			pushi    157
			lag      ego
			send     8
code_0153:
			toss    
			pushi    #init
			pushi    0
			lag      ego
			send     4
			lag      egoInWater
			not     
			bnt      code_0165
			pushi    0
			callb    NormalEgo,  0
code_0165:
			pushi    #setRegions
			pushi    1
			pushi    603
			self     6
			ldi      0
			sal      i
code_0172:
			lsl      i
			lsg      howFast
			ldi      1
			ge?     
			bnt      code_0181
			ldi      2
			jmp      code_0183
code_0181:
			ldi      1
code_0183:
			lt?     
			bnt      code_01e4
			pushi    5
			pushi    #superClass
			pushi    207
			pushi    168
			pushi    1
			pushi    1
			pushi    66
			pushi    1
			pushi    0
			pushi    4
			pushi    1
			lal      i
			lsli     rippleX
			pushi    3
			pushi    1
			lsli     rippleY
			pushi    229
			pushi    1
			push    
			pushi    231
			pushi    1
			pushi    1
			pushi    203
			pushi    1
			lofsa    {waterfall}
			push    
			pushi    93
			pushi    0
			pushi    226
			pushi    0
			pushi    1
			lofsa    Ripple
			push    
			callk    Clone,  2
			push    
			lal      i
			sali     ripple
			send     56
			lsg      howFast
			ldi      1
			ge?     
			bnt      code_01df
			pushi    #setCycle
			pushi    1
			class    Forward
			push    
			lal      i
			lali     ripple
			send     6
code_01df:
			+al      i
			jmp      code_0172
code_01e4:
			pushi    #init
			pushi    0
			lofsa    waterfall1
			send     4
			pushi    #init
			pushi    0
			lofsa    waterfall
			send     4
			pushi    #init
			pushi    0
			lofsa    smalltree1
			send     4
			pushi    #init
			pushi    0
			lofsa    smalltree2
			send     4
			pushi    #init
			pushi    0
			lofsa    lake1
			send     4
			pushi    #init
			pushi    0
			lofsa    lake2
			send     4
			pushi    #init
			pushi    0
			lofsa    tree1
			send     4
			pushi    #init
			pushi    0
			lofsa    tree2
			send     4
			pushi    #init
			pushi    0
			lofsa    tree3
			send     4
			pushi    #init
			pushi    0
			lofsa    bush
			send     4
			pushi    #init
			pushi    0
			lofsa    rock1
			send     4
			pushi    #init
			pushi    0
			lofsa    rock2
			send     4
			pushi    #init
			pushi    0
			lofsa    rock3
			send     4
			pushi    #init
			pushi    0
			lofsa    rock4
			send     4
			pushi    #init
			pushi    0
			lofsa    rock5
			send     4
			ret     
		)
	)
	
	(method (doit &tmp nRoom)
		(cond 
			((and script (not egoInWater))
				(script doit:)
			)
			(
				(= nRoom
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self newRoom: nRoom)
			)
			((and script egoInWater)
				(script doit:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((super handleEvent: event)
				(return)
			)
			((and (Said 'look,check>') (Said '[<at,around][/room,ceder]'))
				(Print 7 0)
			)
		)
	)
)

(instance tree1 of NewFeature
	(properties
		x 257
		y 11
		noun '/ceder'
		nsLeft 195
		nsBottom 23
		nsRight 320
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A majestic old tree stands nearby.}
	)
)

(instance tree2 of NewFeature
	(properties
		x 270
		y 34
		noun '/ceder'
		nsTop 23
		nsLeft 221
		nsBottom 45
		nsRight 319
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A majestic old tree stands nearby.}
	)
)

(instance tree3 of NewFeature
	(properties
		x 282
		y 90
		noun '/ceder'
		nsTop 46
		nsLeft 268
		nsBottom 135
		nsRight 297
		description {tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A majestic old tree stands nearby.}
	)
)

(instance waterfall1 of NewFeature
	(properties
		x 44
		y 22
		noun '/waterfall'
		nsTop 3
		nsLeft 16
		nsBottom 41
		nsRight 73
		description {waterfall}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A small spring bubbles up from the rocks here and splashes into the lake.}
	)
)

(instance waterfall of NewFeature
	(properties
		x 58
		y 59
		noun '/waterfall'
		nsTop 41
		nsLeft 25
		nsBottom 77
		nsRight 91
		description {waterfall}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {A small spring bubbles up from the rocks here and splashes into the lake.}
	)
)

(instance lake1 of NewFeature
	(properties
		x 47
		y 95
		noun '/lake,lake,water'
		nsTop 79
		nsBottom 111
		nsRight 95
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a beautiful little lake.}
	)
)

(instance lake2 of NewFeature
	(properties
		x 32
		y 126
		noun '/lake,lake,water'
		nsTop 113
		nsBottom 139
		nsRight 65
		description {lake}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is a beautiful little lake.}
	)
)

(instance smalltree1 of NewFeature
	(properties
		x 52
		y 137
		noun '/ceder<little'
		nsTop 122
		nsLeft 36
		nsBottom 152
		nsRight 69
		description {small tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is just one of the many small trees adorning Daventry's countryside.}
	)
)

(instance smalltree2 of NewFeature
	(properties
		x 83
		y 147
		noun '/ceder<little'
		nsTop 135
		nsLeft 69
		nsBottom 159
		nsRight 97
		description {small tree}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {This is just one of the many small trees adorning Daventry's countryside.}
	)
)

(instance Ripple of Prop
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((Said 'look,check/waterfall')
				(Print 7 1)
			)
			(else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 7 1)
			)
		)
	)
)

(instance bush of NewFeature
	(properties
		x 258
		y 180
		noun '/bush'
		nsTop 172
		nsLeft 196
		nsBottom 189
		nsRight 320
		description {bush}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {Pleasant little bushes grow around the spring-fed lake.}
	)
)

(instance rock1 of NewFeature
	(properties
		x 59
		y 36
		noun '/boulder'
		nsTop 8
		nsBottom 64
		nsRight 119
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock2 of NewFeature
	(properties
		x 148
		y 43
		noun '/boulder'
		nsTop 23
		nsLeft 119
		nsBottom 64
		nsRight 178
		description {rock2}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock3 of NewFeature
	(properties
		x 92
		y 121
		noun '/boulder'
		nsTop 113
		nsLeft 67
		nsBottom 129
		nsRight 118
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock4 of NewFeature
	(properties
		x 110
		y 74
		noun '/boulder'
		nsTop 65
		nsLeft 93
		nsBottom 83
		nsRight 128
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)

(instance rock5 of NewFeature
	(properties
		x 141
		y 96
		noun '/boulder'
		nsTop 92
		nsLeft 124
		nsBottom 101
		nsRight 158
		description {rock}
		sightAngle 360
		getableDist 320
		seeableDist 320
		shiftClick 369
		lookStr {These rocks must lead to some sort of underground spring.__You remind yourself to explore their nooks and crannies sometime...when you don't have so much work to do!}
	)
)
