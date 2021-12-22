;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
(include sci.sh)
(use Main)
(use fileScr)
(use EgoDead)
(use OccCyc)
(use LarryRoom)
(use CycleBet)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm420 0
)

(local
	local0
)
(instance rm420 of LarryRoom
	(properties
		noun 1
		picture 420
		horizon 0
	)
	
	(method (init param1)
		(asm
			pushi    #addObstacle
			pushi    2
			pushi    #type
			pushi    1
			pushi    3
			pushi    147
			pushi    36
			pushi    123
			pushi    92
			pushi    74
			pushi    92
			pushi    60
			pushi    103
			pushi    132
			pushi    103
			pushi    131
			pushi    116
			pushi    64
			pushi    116
			pushi    52
			pushi    113
			pushi    31
			pushi    127
			pushi    92
			pushi    127
			pushi    111
			pushi    138
			pushi    294
			pushi    139
			pushi    268
			pushi    124
			pushi    243
			pushi    124
			pushi    222
			pushi    109
			pushi    275
			pushi    108
			pushi    257
			pushi    102
			pushi    173
			pushi    105
			pushi    155
			pushi    91
			pushi    153
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     86
			push    
			pushi    #type
			pushi    1
			pushi    2
			pushi    147
			pushi    8
			pushi    227
			pushi    115
			pushi    239
			pushi    128
			pushi    152
			pushi    128
			pushi    142
			pushi    115
			pushi    153
			pushi    0
			pushi    #new
			pushi    0
			class    Polygon
			send     4
			send     30
			push    
			self     8
			pushi    #init
			pushi    0
			&rest    param1
			super    LarryRoom,  4
			lsg      prevRoomNum
			ldi      430
			eq?     
			bnt      code_017b
			pushi    1
			pushi    208
			calle    Btst,  2
			bnt      code_017b
			ldi      1
			sal      local0
code_017b:
			pushi    #init
			pushi    0
			pushi    14
			pushi    1
			pushi    900
			pushi    793
			pushi    0
			pushi    335
			pushi    5
			class    Scaler
			push    
			pushi    100
			pushi    84
			pushi    108
			pushi    87
			pushi    67
			pushi    1
			pushi    180
			pushi    15
			pushi    1
			pushi    8
			pushi    16
			pushi    1
			pushi    2
			lag      ego
			send     46
			pushi    1
			pushi    17
			calle    Btst,  2
			not     
			bnt      code_01d8
			lal      local0
			not     
			bnt      code_01d8
			pushi    #number
			pushi    1
			pushi    420
			pushi    51
			pushi    0
			pushi    253
			pushi    1
			pushi    127
			pushi    254
			pushi    1
			pushi    65535
			lag      theMusic
			send     22
code_01d8:
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    30
			pushi    328
			pushi    1
			pushi    1
			lofsa    stairs
			send     16
			pushi    1
			pushi    17
			calle    Btst,  2
			bnt      code_0201
			pushi    #stop
			pushi    0
			lag      theMusic
			send     4
			jmp      code_0255
code_0201:
			lal      local0
			bnt      code_0207
			jmp      code_0255
code_0207:
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_0233
			pushi    #init
			pushi    0
			pushi    65
			pushi    1
			pushi    9
			pushi    254
			pushi    1
			pushi    0
			pushi    311
			pushi    1
			pushi    2
			pushi    184
			pushi    1
			lofsa    climbStairs
			push    
			lofsa    stairGirl
			send     28
			jmp      code_0255
code_0233:
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_0255
			pushi    #init
			pushi    0
			pushi    236
			pushi    5
			class    OccCyc
			push    
			pushSelf
			pushi    65535
			pushi    35
			dup     
			lofsa    liftingDude
			send     18
code_0255:
			lal      local0
			not     
			bnt      code_028e
			pushi    1
			pushi    17
			calle    Btst,  2
			not     
			bnt      code_028e
			pushi    2
			pushi    0
			pushi    1
			callk    Random,  4
			bnt      code_028e
			pushi    #init
			pushi    0
			pushi    266
			pushi    1
			pushi    8
			pushi    74
			pushi    1
			pushi    199
			pushi    184
			pushi    1
			lofsa    benchPress
			push    
			lofsa    benchPresser
			send     22
			jmp      code_029e
code_028e:
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    199
			lofsa    weightView
			send     10
code_029e:
			lal      local0
			not     
			bnt      code_02e3
			pushi    1
			pushi    17
			calle    Btst,  2
			not     
			bnt      code_02e3
			pushi    1
			pushi    15
			calle    Btst,  2
			not     
			bnt      code_02c1
			pushi    1
			pushi    16
			calle    Btst,  2
code_02c1:
			not     
			bnt      code_02e3
			pushi    #init
			pushi    0
			pushi    311
			pushi    1
			pushi    4
			pushi    74
			pushi    1
			pushi    140
			pushi    236
			pushi    1
			class    Fwd
			push    
			lofsa    shakerDude
			send     22
			jmp      code_0313
code_02e3:
			pushi    1
			pushi    206
			calle    Btst,  2
			not     
			bnt      code_0301
			pushi    #init
			pushi    0
			pushi    311
			pushi    2
			pushi    4
			pushi    5
			lofsa    theBelt
			send     12
code_0301:
			pushi    #init
			pushi    0
			pushi    311
			pushi    2
			pushi    4
			pushi    5
			lofsa    shaker
			send     12
code_0313:
			lsg      prevRoomNum
			ldi      430
			eq?     
			bnt      code_031f
			ldi      0
			jmp      code_037a
code_031f:
			pushi    1
			pushi    15
			calle    Btst,  2
			not     
			bnt      code_033f
			pushi    1
			pushi    16
			calle    Btst,  2
			not     
			bnt      code_033f
			pushi    1
			pushi    15
			calle    Bset,  2
			jmp      code_037a
code_033f:
			pushi    1
			pushi    15
			calle    Btst,  2
			bnt      code_035e
			pushi    1
			pushi    16
			calle    Btst,  2
			not     
			bnt      code_035e
			pushi    1
			pushi    16
			calle    Bset,  2
			jmp      code_037a
code_035e:
			pushi    1
			pushi    15
			calle    Btst,  2
			bnt      code_037a
			pushi    1
			pushi    16
			calle    Btst,  2
			bnt      code_037a
			pushi    1
			pushi    15
			calle    Bclr,  2
code_037a:
			pushi    1
			pushi    17
			calle    Btst,  2
			not     
			bnt      code_03bb
			pushi    1
			pushi    254
			calle    Btst,  2
			not     
			bnt      code_03bb
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    117
			pushi    266
			pushi    1
			pushi    8
			pushi    328
			pushi    1
			pushi    1
			pushi    236
			pushi    5
			class    OccCyc
			push    
			pushSelf
			pushi    1
			pushi    30
			dup     
			lofsa    thunder
			send     36
			jmp      code_03cf
code_03bb:
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    116
			pushi    328
			pushi    1
			pushi    1
			lofsa    legSpreader
			send     16
code_03cf:
			lsg      prevRoomNum
			dup     
			ldi      440
			eq?     
			bnt      code_0401
			pushi    #posn
			pushi    2
			pushi    270
			pushi    103
			lag      ego
			send     8
			pushi    #number
			pushi    1
			pushi    0
			pushi    248
			pushi    0
			lag      theMusic2
			send     10
			pushi    #setScript
			pushi    1
			lofsa    enterThruDoor
			push    
			self     6
			jmp      code_0460
code_0401:
			dup     
			ldi      430
			eq?     
			bnt      code_0437
			pushi    #posn
			pushi    2
			pushi    190
			pushi    132
			pushi    14
			pushi    1
			pushi    423
			pushi    254
			pushi    1
			pushi    1
			pushi    326
			pushi    1
			pushi    9
			lag      ego
			send     26
			pushi    #setScript
			pushi    1
			lofsa    getOffBench
			push    
			lag      curRoom
			send     6
			jmp      code_0460
code_0437:
			pushi    #number
			pushi    1
			pushi    0
			pushi    248
			pushi    0
			lag      theMusic2
			send     10
			pushi    #setPri
			pushi    1
			pushi    1
			pushi    325
			pushi    2
			pushi    26
			pushi    115
			lag      ego
			send     14
			pushi    #setScript
			pushi    1
			lofsa    enterFromBath
			push    
			self     6
code_0460:
			toss    
			pushi    #init
			pushi    0
			pushi    74
			pushi    1
			pushi    110
			pushi    311
			pushi    1
			pushi    4
			lofsa    door
			send     16
			pushi    #init
			pushi    0
			pushi    328
			pushi    1
			pushi    1
			pushi    311
			pushi    1
			pushi    4
			lofsa    door2
			send     16
			pushi    #init
			pushi    0
			lofsa    bench
			send     4
			pushi    #init
			pushi    0
			lofsa    weights
			send     4
			pushi    #init
			pushi    0
			lofsa    weightBench
			send     4
			pushi    #init
			pushi    0
			lofsa    rubbingBench
			send     4
			pushi    5
			pushi    32
			pushi    33
			pushi    444
			pushi    385
			pushi    421
			calle    proc79_11,  10
			ret     
		)
	)
	
	(method (dispose)
		(theMusic number: 0 stop:)
		(sFx number: 0 dispose:)
		(proc79_12 32 33 444 385 421)
		(super dispose:)
	)
)

(instance benchPress of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1 (client setCycle: End self))
			(2 (= seconds (Random 1 3)))
			(3 (client setCycle: Beg self))
			(4 (self init:))
		)
	)
)

(instance toAerobics of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 901
					loop: 0
					setSpeed: 6
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(sFx number: 32 loop: 1 play: setVol: 127)
				(door2 view: 428 setCycle: End self)
			)
			(2
				(door2 setPri: 110)
				(thunder setPri: 140)
				(ego
					view: 900
					setCycle: Walk
					setMotion: MoveTo 270 103 self
				)
			)
			(3
				(theMusic fade:)
				(curRoom newRoom: 440)
			)
		)
	)
)

(instance toMudBath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 46 118 self)
			)
			(1
				(ego
					view: 901
					setSpeed: 6
					loop: 1
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(sFx number: 32 loop: 1 play: setVol: 127)
				(door view: 428 setCycle: End self)
			)
			(3
				(door setPri: -1)
				(ego
					view: 900
					setCycle: Walk
					setMotion: MoveTo 26 113 self
				)
			)
			(4 (door setCycle: Beg self))
			(5
				(sFx number: 33 loop: 1 play: setVol: 127)
				(= cycles 4)
			)
			(6
				(theMusic fade:)
				(curRoom newRoom: 400)
			)
		)
	)
)

(instance enterFromBath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego normalize: 900 0 1 setPri: 100)
				(sFx number: 32 loop: 1 play: setVol: 127)
				(door view: 428 setCycle: End self)
			)
			(1
				(ego setMotion: MoveTo 46 118 self)
			)
			(2
				(ego setPri: -1)
				(door setCycle: Beg self)
			)
			(3
				(sFx number: 33 loop: 1 play: setVol: 127)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance climbStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stairGirl
					setLoop: 0
					setCycle: Fwd
					moveSpeed: 9
					setMotion: MoveTo 187 68 self
				)
			)
			(1
				(stairGirl setLoop: 1 setCycle: 0 setCel: 0)
				(= seconds 3)
			)
			(2
				(stairs setCel: 1)
				(stairGirl
					setCel: 1
					moveSpeed: 4
					setMotion: MoveTo 165 88 self
				)
			)
			(3 (= seconds 1))
			(4
				(stairs setCel: 0)
				(self init:)
			)
		)
	)
)

(instance thunderLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(door setPri: 110)
				(thunder
					view: 422
					setLoop: 2 1
					setCel: 0
					setCycle: End self
				)
				(legSpreader init: setPri: 117 ignoreActors: 1)
			)
			(1
				(thunder
					setLoop: 3 1
					posn: 251 117
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 6
					setStep: 3 2
					setMotion: MoveTo 200 118 self
				)
			)
			(2
				(proc79_3 ego thunder)
				(thunder setMotion: MoveTo 150 118 self)
			)
			(3
				(proc79_3 ego thunder)
				(thunder setMotion: MoveTo 40 118 self)
			)
			(4
				(thunder setLoop: 4 1 setCel: 0 setCycle: End self)
			)
			(5
				(sFx number: 32 loop: 1 play: setVol: 127)
				(door view: 428 setCycle: End self)
			)
			(6
				(door setPri: 120)
				(thunder
					setLoop: 3 1
					setCycle: Walk
					setMotion: MoveTo 20 118 self
				)
			)
			(7
				(messager say: 15 0 2 0 self)
			)
			(8 (door setCycle: Beg self))
			(9
				(sFx number: 33 loop: 1 play: setVol: 127)
				(door view: 420 setPri: 110)
				(proc79_7)
				(theMusic2 fade:)
				(Bset 17)
				(thunder dispose:)
				(= local0 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance liftWeights of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 105 92 self)
			)
			(1
				(ego
					view: 423
					setSpeed: 6
					setLoop: 0
					setCel: 0
					cycleSpeed: 9
					setCycle: CT 12 1 self
				)
			)
			(2
				(sFx number: 444 loop: 1 play:)
				(ego setCycle: End self)
			)
			(3
				(sFx number: 385 loop: 1 play:)
				(ego setCycle: CycleBet 12 15 -1)
				(= seconds 3)
			)
			(4 (EgoDead 4 self))
			(5
				(ego posn: 105 92 normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance doLegBench of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar
					enableIcon: (ScriptID 0 3)
					curIcon: (ScriptID 0 3)
				)
				(theGame setCursor: (theIconBar getCursor:))
				(mouseDownHandler addToFront: self)
				(keyDownHandler addToFront: self)
				(ego setMotion: PolyPath 190 132 self)
			)
			(1
				(ego
					view: 423
					setSpeed: 6
					cel: 0
					setLoop: 1
					setCycle: End self
				)
			)
			(2
				(ego setLoop: 2 setCycle: Fwd)
			)
			(3
				(theIconBar enable:)
				(mouseDownHandler delete: self)
				(keyDownHandler delete: self)
				(curRoom setScript: getOffBench)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and (< (event y?) 140) (== state 2))
			(self cue:)
			(super handleEvent: event)
		else
			(super handleEvent: event)
		)
	)
)

(instance getOffBench of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setSpeed: 6 setLoop: 1 setCel: 9 setCycle: Beg self)
			)
			(1
				(ego normalize: 900 0 1)
				(if local0
					(curRoom setScript: thunderLeaves)
				else
					(theMusic2 number: 0 stop:)
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance lookAtThunder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 190 132 self)
			)
			(1 (proc79_3 ego thunder self))
			(2 (= cycles 2))
			(3
				(if (Btst 103)
					(self cue:)
				else
					(messager say: 5 1 4 0 self)
				)
			)
			(4
				(ego
					view: 423
					setSpeed: 6
					cel: 0
					setLoop: 1
					setCycle: End self
				)
			)
			(5
				(ego setLoop: 2 setCycle: Fwd)
				(= ticks 180)
			)
			(6
				(theMusic fade: 0 10 10 0)
				(= ticks 90)
			)
			(7 (curRoom newRoom: 430))
		)
	)
)

(instance enterThruDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(thunder setPri: 120)
				(door2 view: 428 setPri: 110 setCel: 6)
				(ego setPri: 100 setMotion: MoveTo 246 106 self)
			)
			(1
				(door2 setPri: -1 setCycle: Beg self)
			)
			(2
				(sFx number: 33 loop: 1 play: setVol: 127)
				(door2 view: 420)
				(thunder setPri: 117)
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance liftingDude of Prop
	(properties
		noun 2
		x 82
		y 136
		view 425
		cel 8
	)
)

(instance stairGirl of Actor
	(properties
		noun 3
		approachX 150
		approachY 102
		x 165
		y 88
		view 421
		loop 1
		cel 6
	)
)

(instance shakerDude of Prop
	(properties
		noun 4
		approachX 270
		approachY 130
		x 295
		y 127
		view 426
		cycleSpeed 3
	)
)

(instance thunder of Actor
	(properties
		noun 5
		x 271
		y 74
		view 420
		loop 6
		signal $1021
		cycleSpeed 10
	)
	
	(method (doit param1)
		(asm
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_0f25
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_0f25
code_0f25:
			not     
			bnt      code_0f56
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_0f56
			lal      local0
			not     
			bnt      code_0f56
			pTos     cel
			ldi      1
			eq?     
			bnt      code_0f56
			pushi    #number
			pushi    1
			pushi    421
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sFx
			send     16
code_0f56:
			pushi    #doit
			pushi    0
			&rest    param1
			super    Actor,  4
			ret     
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb 1 2)
				(curRoom setScript: lookAtThunder)
			)
			(12
				(theGame changeScore: 15 208)
				(curRoom setScript: lookAtThunder)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance door2 of Prop
	(properties
		noun 16
		approachX 254
		approachY 109
		x 286
		y 52
		view 428
		loop 1
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: toAerobics)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance door of Prop
	(properties
		noun 9
		approachX 46
		approachY 118
		x 22
		y 122
		view 428
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: toMudBath)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance benchPresser of Prop
	(properties
		noun 12
		x 55
		y 139
		view 427
	)
)

(instance stairs of View
	(properties
		x 173
		y 95
		view 420
		loop 3
	)
)

(instance theBelt of View
	(properties
		approachX 263
		approachY 130
		x 307
		y 123
		view 420
		loop 4
	)
	
	(method (doVerb theVerb)
		(shaker doVerb: theVerb)
	)
)

(instance legSpreader of View
	(properties
		noun 11
		x 293
		y 94
		view 420
		loop 5
	)
)

(instance weightView of View
	(properties
		x 60
		y 122
		view 420
		loop 2
	)
)

(instance shaker of Feature
	(properties
		noun 6
		approachX 263
		approachY 130
		x 270
		y 130
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 278 127 319 78 319 129 293 138
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (not (Btst 206))
					(theGame changeScore: 8 206)
					(ego get: 4)
					(theBelt dispose:)
					(super doVerb: theVerb)
				else
					(messager say: 6 5 1)
				)
			)
			(1
				(if (Btst 206)
					(messager say: 6 1 1)
				else
					(super doVerb: theVerb)
				)
			)
			(4
				(if (Btst 206)
					(messager say: 6 4 1)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bench of Feature
	(properties
		noun 7
		nsLeft 153
		nsTop 108
		nsRight 225
		nsBottom 131
		x 180
		y 131
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: doLegBench)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance weights of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 82 87 84 73 127 73 127 89
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 5)
			(curRoom setScript: liftWeights)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance barBell of Feature
	(properties
		noun 12
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 22 130 66 105 72 128 69 139 17 138 24 129
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance weightBench of Feature
	(properties
		noun 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 179 78 198 59 237 59 243 67 222 98 176 101
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rubbingBench of Feature
	(properties
		noun 13
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 83 100 86 96 121 95 115 102 83 101
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance sFx of Sound
	(properties)
)
