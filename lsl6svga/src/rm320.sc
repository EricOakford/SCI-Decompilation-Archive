;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use rm740)
(use Array)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Reverse)
(use Timer)
(use Grooper)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm320 0
)

(local
	local0 =  900
	local1
	local2
	local3
	[local4 9] = [-1 41 63 83 188 246 46 302 241]
	[local13 9] = [-1 122 88 88 99 111 134 134 108]
	local22
)
(procedure (localproc_00f6)
	(rose
		view: 322
		ignoreActors: 0
		setLoop: roseGrooper
		setLoop: -1
		setCycle: Walk
		setStep: 4 2
		cycleSpeed: 6
		moveSpeed: 6
	)
)

(class cObj of Obj
	(properties
		scratch 0
	)
)

(instance rm320 of LarryRoom
	(properties
		noun 11
		picture 320
		horizon 0
		autoLoad 0
	)
	
	(method (init &tmp temp0)
		(if global100 (= local22 2) else (= local22 4))
		(theMusic stop:)
		(if (!= prevRoomNum 390)
			(theMusic2 number: 320 loop: -1 play:)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						31
						122
						17
						134
						132
						134
						143
						139
						189
						139
						196
						135
						302
						134
						288
						116
						273
						121
						241
						108
						217
						117
						190
						117
						183
						108
						188
						99
						179
						105
						144
						105
						135
						90
						113
						104
						101
						88
						63
						88
						63
						92
						95
						92
						106
						102
						99
						114
						85
						122
					yourself:
				)
		)
		(tank init:)
		(motor init:)
		(motorClock init: setPri: 30)
		(bag init:)
		(vacuum init:)
		(motorRope init:)
		(grease init: approachVerbs: 4 2 5 6 39)
		(hose init:)
		(fan init: approachVerbs: 4 2 5 6 setPri: 30)
		(cond 
			((and (== prevRoomNum 390) (Btst 67))
				(rose init:)
				(ego
					init:
					normalize: local0 1
					ignoreActors: 0
					x: 183
					y: 120
				)
				(self setScript: getEnemaScr)
			)
			((== prevRoomNum 390)
				(rose init: setScript: roseScr ignoreActors: 0)
				(ego
					init:
					normalize: local0 1
					ignoreActors: 0
					x: 183
					y: 120
				)
				(theGame handsOn:)
			)
			(else
				(= temp0 (Random 1 8))
				(rose
					init:
					x: [local4 temp0]
					y: [local13 temp0]
					setScript: roseScr
					ignoreActors: 0
				)
				(ego
					init:
					normalize: local0 3
					ignoreActors: 0
					x: 183
					y: 200
				)
				(self setScript: enterRoomScr)
			)
		)
		(stirrups init:)
		(fBedPan init:)
		(leftGrip init:)
		(rightGrip init:)
		(flowers1 init:)
		(flowers2 init:)
		(flowers3 init:)
		(rightPipe init:)
		(leftPipe init:)
		(painting init:)
		(smallGauge1 init:)
		(smallGauge2 init:)
		(theWindow init:)
		(super init: &rest)
	)
	
	(method (doit)
		(cond 
			(script)
			((== (ego edgeHit?) 3) (self setScript: exitRoomScr))
		)
		(super doit: &rest)
	)
	
	(method (cue)
		(if (talkers size:) (messager cue: 1))
		(= gNewStr_4 (ByteArray new: 40))
		(= gNewStr_3 (ByteArray new: 40))
		(Message 0 861 0 8 0 1 (gNewStr_4 data?))
		(Message 0 861 0 8 0 2 (gNewStr_3 data?))
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 170 65)
		(if
			(= local3
				(Print
					width: 150
					font: userFont
					addTitle: (gNewStr_3 data?)
					addText: (gNewStr_4 data?) 50 0
					x: 30
					y: 20
					addIcon: 1911 0 0 0 0
					addButton: 1 1 8 0 1 125 33 861
					addButton: 0 2 8 0 1 50 33 861
					init:
				)
			)
			(self setScript: 0)
			(= gLarryRoom 0)
			(theIconBar disableIcon: (ScriptID 0 8))
			(ego put: 16)
			(ego get: 28)
			(theGame changeScore: 15 176)
			(Bclr 99)
			(Bclr 74)
			(UpdateScreenItem
				((ScriptID 92 3) view: 1900 loop: 1 cel: 0)
			)
			(curRoom newRoom: 310)
		else
			(= gLarryRoom curRoom)
			(theIconBar enableIcon: (ScriptID 0 8) show:)
		)
		(gNewStr_4 dispose:)
		(= gNewStr_4 0)
		(gNewStr_3 dispose:)
		(= gNewStr_3 0)
		(theGame setCursor: gGButtonBarGetCursor)
	)
	
	(method (newRoom n)
		(stenchTimer dispose: delete:)
		(if (!= n 390)
			(theMusic number: 0 stop:)
			(theMusic2 number: 0 stop:)
		)
		(Bclr 86)
		(super newRoom: n)
	)
)

(instance sfx of Sound
	(properties
		flags $0005
	)
)

(instance sfx2 of Sound
	(properties
		flags $0005
		loop -1
	)
)

(instance motorSfx of Sound
	(properties
		flags $0005
		loop -1
	)
)

(instance scream of Sound
	(properties
		flags $0005
		loop -1
	)
)

(instance rndScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					cycleSpeed: (Random 2 8)
					cel: 0
					setCycle: End self
				)
			)
			(1
				(client cycleSpeed: (Random 2 8) setCycle: Beg self)
			)
			(2
				(switch (Random 1 4)
					(1
						(client cycleSpeed: (Random 2 8) setCycle: Rev)
						(= ticks (Random 120 240))
					)
					(2
						(client cycleSpeed: (Random 2 8) setCycle: Fwd)
						(= ticks (Random 120 240))
					)
					(else  (self init:))
				)
			)
			(3 (self init:))
		)
	)
)

(instance rose of Actor
	(properties
		heading 45
		noun 12
		sightAngle 10
		approachX 183
		approachY 120
		x 154
		y 120
		view 322
		loop 8
	)
	
	(method (init)
		(super init:)
		(localproc_00f6)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			((OneOf theVerb 1 4 2)
				(theGame handsOff:)
				(rose
					setScript: 0
					ignoreActors: 1
					setLoop: -1
					setCycle: Walk
					setMotion: PolyPath 154 120 self
				)
				(curRoom setScript: talkToRoseScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(super cue:)
		(= cel 0)
		(= loop 8)
		(talkToRoseScr cue:)
	)
)

(instance talkToRoseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (theGame handsOff:))
			(1
				(ego setMotion: PolyPath 183 120 self)
			)
			(2
				(ego setHeading: 270)
				(if (and (!= (rose loop?) 8) (!= (rose cel?) 0))
					(rose loop: 8 cel: 0)
				)
				(= ticks 60)
			)
			(3
				(if (not (Btst 89))
					(Bset 89)
					(messager say: 12 2 0 0 self)
				else
					(= cycles 2)
				)
				(theMusic2 fade: 90 10 10 0)
			)
			(4 (= ticks 60))
			(5 (curRoom newRoom: 390))
		)
	)
)

(instance exitRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 fade: 0 10 10 1)
				(ego setMotion: MoveTo (ego x?) 200 self)
			)
			(1 (= ticks 90))
			(2 (curRoom newRoom: 310))
		)
	)
)

(instance wirlpool of Actor
	(properties
		x 63
		y 93
		priority 110
		fixPriority 1
		view 320
		loop 6
		cel 2
		signal $4821
	)
)

(instance tank of Prop
	(properties
		noun 14
		x 255
		y 44
		priority 44
		fixPriority 1
		view 320
		loop 4
		signal $4821
	)
)

(instance motor of Prop
	(properties
		noun 8
		sightAngle 10
		x 276
		y 40
		priority 37
		fixPriority 1
		view 320
		cel 4
		signal $4821
	)
)

(instance motorClock of Prop
	(properties
		noun 4
		sightAngle 10
		x 264
		y 22
		priority 19
		fixPriority 1
		view 320
		loop 1
		cel 2
		signal $4821
	)
)

(instance bag of Prop
	(properties
		noun 15
		sightAngle 10
		x 212
		y 88
		priority 88
		fixPriority 1
		view 320
		loop 2
		cel 1
		signal $5821
	)
)

(instance vacuum of Prop
	(properties
		noun 15
		sightAngle 10
		x 206
		y 100
		priority 100
		fixPriority 1
		view 320
		loop 5
		signal $5821
	)
)

(instance fan of Prop
	(properties
		noun 2
		sightAngle 10
		approachX 135
		approachY 91
		x 150
		y 16
		priority 16
		fixPriority 1
		view 320
		loop 3
		cel 1
		signal $4821
		cycleSpeed 1
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 (ByteArray new: 40))
		(if (Message 0 320 2 theVerb 0 1 (temp0 data?))
			(messager say: 2 theVerb)
		else
			(super doVerb: theVerb)
		)
		(temp0 dispose:)
	)
)

(instance motorRope of View
	(properties
		x 292
		y 47
		priority 47
		fixPriority 1
		view 320
		loop 9
		signal $4821
	)
)

(instance larryClothes of View
	(properties
		priority 35
		fixPriority 1
		view 903
		loop 3
		signal $4821
	)
)

(instance grease of View
	(properties
		noun 7
		sightAngle 10
		approachX 101
		approachY 88
		x 116
		y 40
		priority 40
		fixPriority 1
		view 320
		loop 8
		signal $4821
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(39
				(curRoom setScript: fillLampScr)
			)
			(4
				(if (not (-- local22))
					(Bset 99)
					(ego get: 16)
					(while (== (= temp0 (Random 1 61)) 9)
					)
					(DoAudio 2 611 1 4 temp0 1)
					(Print
						width: 160
						font: userFont
						addTitle: {Carlos, are you cheating again?}
						addText: 1 4 temp0 1 50 1 611
						fore: 67
						addText: {You have the flowers for Rose.} 50 18
						fore: 0
						addIcon: 1592 1 0 0 0
						init:
					)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance hose of View
	(properties
		noun 6
		sightAngle 10
		x 104
		y 91
		priority 90
		fixPriority 1
		view 320
		loop 7
		signal $4821
	)
)

(instance stirrups of Feature
	(properties
		noun 13
		sightAngle 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						64
						71
						70
						71
						72
						74
						78
						70
						85
						71
						87
						74
						91
						70
						99
						71
						89
						81
						81
						90
						73
						90
						67
						80
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance fBedPan of Feature
	(properties
		noun 1
		sightAngle 10
		x 74
		y 102
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						72
						91
						80
						91
						83
						88
						96
						94
						101
						99
						98
						105
						92
						105
						88
						108
						80
						108
						78
						113
						70
						112
						66
						106
						64
						101
						53
						101
						54
						105
						52
						110
						44
						104
						45
						97
						49
						94
						64
						89
						70
						85
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance leftGrip of Feature
	(properties
		noun 5
		sightAngle 10
		x 71
		y 60
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 64 50 78 50 78 70 67 69
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightGrip of Feature
	(properties
		noun 5
		sightAngle 10
		x 92
		y 60
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 86 48 99 49 97 71 85 69
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightPipe of Feature
	(properties
		noun 10
		sightAngle 10
		x 243
		y 83
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						221
						90
						228
						90
						233
						82
						240
						79
						239
						76
						245
						74
						249
						77
						257
						77
						258
						83
						250
						83
						249
						88
						245
						90
						242
						86
						238
						86
						236
						91
						232
						97
						219
						98
						217
						95
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance leftPipe of Feature
	(properties
		noun 10
		sightAngle 10
		x 168
		y 35
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						151
						0
						191
						0
						185
						16
						188
						18
						193
						12
						203
						13
						211
						21
						199
						21
						196
						20
						193
						29
						187
						28
						192
						40
						204
						46
						213
						58
						211
						71
						206
						77
						209
						87
						204
						95
						195
						89
						194
						76
						200
						61
						186
						61
						181
						73
						186
						88
						184
						98
						177
						101
						146
						100
						141
						93
						151
						80
						153
						69
						146
						67
						147
						58
						141
						59
						141
						51
						147
						51
						149
						45
						145
						44
						148
						32
						156
						32
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance flowers1 of Feature
	(properties
		noun 3
		sightAngle 10
		x 22
		y 81
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						74
						2
						67
						8
						68
						8
						64
						17
						63
						23
						68
						26
						59
						35
						62
						37
						59
						32
						54
						36
						51
						45
						52
						48
						59
						56
						56
						55
						62
						58
						63
						59
						68
						56
						68
						54
						73
						46
						76
						47
						81
						6
						103
						0
						101
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance flowers2 of Feature
	(properties
		noun 3
		sightAngle 10
		x 65
		y 145
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						1
						111
						5
						107
						17
						109
						23
						112
						25
						111
						26
						102
						38
						103
						42
						114
						46
						109
						55
						112
						54
						100
						66
						102
						67
						109
						72
						113
						76
						115
						81
						108
						89
						109
						93
						105
						101
						106
						102
						110
						100
						117
						102
						122
						108
						125
						117
						118
						130
						121
						145
						131
						141
						136
						134
						138
						0
						138
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance flowers3 of Feature
	(properties
		noun 3
		sightAngle 10
		x 274
		y 145
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						198
						138
						189
						137
						191
						131
						190
						126
						196
						121
						205
						119
						211
						115
						219
						116
						220
						123
						225
						125
						226
						122
						239
						120
						246
						126
						247
						121
						241
						115
						258
						105
						268
						117
						270
						113
						277
						116
						282
						120
						291
						109
						304
						108
						303
						97
						311
						87
						319
						93
						319
						138
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance smallGauge2 of Feature
	(properties
		noun 4
		sightAngle 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 216 43 211 47 202 44 202 38 210 36
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance smallGauge1 of Feature
	(properties
		noun 4
		sightAngle 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 210 25 206 32 201 32 198 27 200 21 206 20
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance painting of Feature
	(properties
		noun 9
		sightAngle 10
		x 80
		y 33
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 109 0 103 48 62 48 56 6
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance theWindow of Feature
	(properties
		noun 16
		sightAngle 10
		x 10
		y 53
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 21 30 16 38 59 0 68
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance roseGrooper of Grooper
	(properties)
	
	(method (doit param1 param2 param3 param4)
		(if (OneOf (ego view?) 900 800)
			(super
				doit:
					param1
					param2
					(if (> argc 2) param3 else 0)
					(if (> argc 3) param4 else 0)
					&rest
			)
		else
			(super
				doit: param1 param2 (if (> argc 2) param3 else 0) 1
			)
		)
	)
)

(instance egoCue of cObj
	(properties)
	
	(method (cue)
		(ego view: 324 setLoop: 0 setCycle: 0 cel: 1)
	)
)

(instance musicCue of cObj
	(properties)
	
	(method (cue)
		(theMusic
			number: 337
			loop: -1
			play:
			mute: 1 4
			mute: 1 5
			mute: 1 6
			mute: 1 7
			mute: 1 8
			mute: 1 9
		)
		(getEnemaScr cue:)
	)
)

(instance roseScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 20)))
			(1
				(switch (Random 1 8)
					(1
						(rose setMotion: PolyPath 41 122 self)
						(if (not (Random 0 1))
							(= register 7)
						else
							(= register 1)
						)
					)
					(2
						(rose setMotion: PolyPath 63 88 self)
						(= register 1)
					)
					(3
						(rose setMotion: PolyPath 83 88 self)
						(= register 3)
					)
					(4
						(rose setMotion: PolyPath 188 99 self)
						(switch (Random 1 3)
							(1 (= register 3))
							(2 (= register 6))
							(3 (= register 7))
						)
					)
					(5
						(rose setMotion: PolyPath 246 111 self)
						(= register 6)
					)
					(6
						(rose setMotion: PolyPath 135 90 self)
						(switch (Random 1 3)
							(1 (= register 3))
							(2 (= register 6))
							(3 (= register 7))
						)
					)
					(7
						(rose setMotion: PolyPath 46 134 self)
						(switch (Random 1 3)
							(1 (= register 2))
							(2 (= register 4))
							(3 (= register 5))
						)
					)
					(8
						(rose setMotion: PolyPath 302 134 self)
						(if (not (Random 0 1))
							(= register 0)
						else
							(= register 4)
						)
					)
				)
			)
			(2
				(rose loop: 8 cel: register)
				(self init:)
			)
		)
	)
)

(instance fillLampScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (rose mover?))
					(rose setScript: 0)
					(= register 1)
				else
					(= register 0)
				)
				(ego setMotion: MoveTo 105 91 self)
			)
			(1
				(ego cycleSpeed: 8 moveSpeed: 8 setHeading: 45 self)
			)
			(2 (= cycles 2))
			(3
				(ego
					view: 338
					setLoop: 0 1
					cel: 0
					x: (+ (ego x?) 12)
					setCycle: End self
				)
				(= register 3)
			)
			(4
				(ego setLoop: 1 1 cel: 0 setCycle: End self)
			)
			(5
				(messager say: 7 39 0 0 self)
			)
			(6
				((inventory at: 23)
					message: 17
					noun: 56
					setCursor: 39 0 0
					view: 39
				)
				(inventory show:)
				(ego x: (- (ego x?) 12) normalize: 900 6)
				(= ticks 60)
			)
			(7 (ego setHeading: 225 self))
			(8 (= cycles 2))
			(9
				(ego
					setMotion: MoveTo (grease approachX?) (grease approachY?) self
				)
			)
			(10
				(ego normalize: 900 5)
				(if register (rose setScript: roseScr))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance stenchTimer of Timer
	(properties)
	
	(method (cue)
		(if (not (proc79_14))
			(if (not (Random 0 1))
				(messager say: 0 0 2 0 0 320)
			else
				(messager say: 0 0 10 0 0 390)
			)
			(Bset 108)
		else
			(self setReal: self 3)
		)
	)
)

(instance enterRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(ego setMotion: MoveTo (ego x?) 120 self)
			)
			(2
				(ego normalize: 900 3 ignoreActors: 0)
				(if (and (Btst 55) (not (Btst 108)))
					(stenchTimer setReal: stenchTimer 3)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getEnemaScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc79_8 21)
				(= gLarryRoom curRoom)
				(theIconBar enableIcon: (ScriptID 0 8) show:)
				(= ticks 240)
			)
			(1
				(rose view: 329 setLoop: 1 1 cel: 0)
				(ego cycleSpeed: 6 moveSpeed: 6 view: 98)
				(= ticks 20)
			)
			(2 (rose cel: 1) (= ticks 120))
			(3
				(messager say: 12 0 1 1 self)
			)
			(4 (= ticks 60))
			(5 (rose setCycle: CT 4 1 self))
			(6 (ego put: 16) (= ticks 120))
			(7
				(messager say: 12 0 1 2 self)
			)
			(8 (rose setCycle: End self))
			(9 (= ticks 30))
			(10
				(ego normalize: 900 1)
				(localproc_00f6)
				(rose loop: 8 setCel: 0)
				(= cycles 2)
			)
			(11
				(UnLoad 128 329)
				(ego setHeading: 180 egoCue)
				(rose setMotion: MoveTo (rose x?) 134 self)
			)
			(12 (rose setHeading: 90 self))
			(13 (= cycles 2))
			(14
				(rose setMotion: MoveTo (ego x?) 134 self)
			)
			(15
				(ego setCel: 2)
				(rose setMotion: MoveTo 211 134 self)
			)
			(16
				(ego setCel: 3)
				(rose setMotion: MoveTo 277 134 self)
			)
			(17
				(rose setMotion: MoveTo 277 156 self)
			)
			(18
				(theMusic2 fade: 0 30 10 1 musicCue)
				(ego
					normalize: 900 2
					cycleSpeed: 6
					moveSpeed: 6
					loop: 8
					setCel: 4
					heading: 125
				)
				(= ticks 90)
			)
			(19
				(ego view: 324 setLoop: 1 1 setCel: 0)
				(UnLoad 128 900)
				(rose setMotion: MoveTo 281 134 self)
			)
			(20
				(UnLoad 128 324)
				(ego view: 900 setLoop: -1 loop: 8 setCel: 0)
				(rose setMotion: MoveTo 285 113 self)
			)
			(21
				(ego setCel: 6)
				(rose setHeading: 225 self)
			)
			(22
				(rose loop: 8 setCel: 5)
				(= ticks 60)
			)
			(23
				(messager say: 12 0 1 3 self)
			)
			(24)
			(25
				(UnLoad 132 320)
				(messager say: 12 0 1 4 self)
			)
			(26
				(messager say: 12 0 1 5 self)
			)
			(27 (= ticks 60))
			(28
				(ego setMotion: PolyPath 81 88 self)
			)
			(29
				(ego setMotion: MoveTo 78 89 self)
			)
			(30 (ego setHeading: 360 self))
			(31
				(ego view: 328 setLoop: 0 1 setCel: 0 setCycle: 0)
				(UnLoad 128 900)
				(= cycles 2)
			)
			(32
				(theMusic mute: 0 8)
				(= cycles 2)
			)
			(33
				(messager sayRange: 12 0 1 7 8 self)
			)
			(34 (rose setHeading: 360 self))
			(35
				(Bset 86)
				(= cycles 2)
				(= register 3)
			)
			(36
				(rose
					view: 323
					setLoop: 0 1
					setCel: 0
					setCycle: CT 1 1 self
				)
			)
			(37
				(motorRope hide:)
				(sfx number: 324 loop: 1 play:)
				(motorClock setCycle: End)
				(motor setCycle: End)
				(tank setCycle: End)
				(rose setCycle: End self)
			)
			(38
				(motorRope show:)
				(rose view: 322 loop: 8 setCel: 3)
				(switch register
					(1
						(fan setCycle: Fwd)
						(wirlpool
							init:
							setPri: 106
							cycleSpeed: 1
							setScript: (rndScr new:)
						)
						(motorClock setCycle: Fwd)
						(motor setCycle: Fwd)
						(tank cycleSpeed: 1 setCycle: Fwd)
						(motorSfx number: 3250 loop: -1 play:)
						(= local1 1)
					)
				)
				(= ticks 90)
			)
			(39
				(if (-- register) (= state (- state 4)))
				(= cycles 2)
			)
			(40 (rose setHeading: 270 self))
			(41 (= ticks 180))
			(42
				(theMusic mute: 0 4 mute: 0 9)
				(= cycles 2)
			)
			(43
				(messager sayRange: 12 0 1 9 10 self)
			)
			(44
				(rose
					view: 322
					setLoop: -1
					setCycle: Walk
					setHeading: 225 self
				)
			)
			(45 (= ticks 30))
			(46
				(rose setMotion: MoveTo 280 119 self)
			)
			(47
				(rose setMotion: PolyPath 156 120 self)
			)
			(48
				(messager say: 12 0 1 6 self)
				(rose setMotion: PolyPath 106 89 self)
			)
			(49)
			(50
				(rose setPri: 150 setMotion: MoveTo 106 89 self)
			)
			(51 (rose setHeading: 45 self))
			(52 (= cycles 2))
			(53
				(hose dispose:)
				(UnLoad 128 900)
				(UnLoad 128 322)
				(rose view: 321 setLoop: 1 1 setCel: 0)
				(= cycles 2)
			)
			(54
				(ego setCel: 1)
				(rose setCycle: End self)
			)
			(55
				(= register 4)
				(= ticks 30)
			)
			(56
				(rose view: 321 setLoop: 2 1 setCel: 0)
				(= cycles 2)
			)
			(57
				(rose cel: 0 setCycle: CT 1 1 self)
			)
			(58
				(sfx number: 328 loop: 1 play:)
				(rose setCycle: End self)
			)
			(59
				(if (-- register) (= state (- state 3)))
				(= ticks 30)
			)
			(60
				(rose setLoop: 3 1 setCel: 0 setCycle: CT 3 1 self)
			)
			(61
				(sfx number: 329 loop: 1 play:)
				(rose setCycle: End self)
			)
			(62 (= ticks 30))
			(63
				(rose setLoop: 4 1 setCel: 0)
				(= register 6)
				(= cycles 2)
			)
			(64
				(rose setCel: 0 setCycle: CT 1 1 self)
			)
			(65
				(sfx number: 328 loop: 1 play:)
				(rose setCycle: End self)
			)
			(66
				(if (-- register) (= state (- state 3)))
				(self cue:)
			)
			(67 (= ticks 30))
			(68
				(theMusic mute: 0 7 mute: 0 6)
				(= cycles 2)
			)
			(69
				(messager sayRange: 12 0 1 11 13 self)
			)
			(70 (= ticks 120))
			(71
				(rose setCycle: 0 setLoop: 5 1 setCel: 0)
				(= cycles 2)
			)
			(72
				(ego setCel: 0)
				(rose setCycle: CT 10 1 self)
			)
			(73 (rose setCycle: End self))
			(74
				(theMusic mute: 0 5)
				(= cycles 2)
			)
			(75
				(vacuum cycleSpeed: 1 setCycle: Fwd)
				(bag setCycle: Fwd)
				(rose view: 325 setLoop: 0 1 setCel: 0)
				(messager say: 12 0 1 14 self)
			)
			(76
				(UnLoad 128 323)
				(ego view: 903 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(77
				(Bset 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1901))
				(sfx number: 234 loop: 1 play:)
				(ego setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(78
				(larryClothes
					init:
					x: (+ (ego x?) 26)
					y: (ego y?)
					setPri: 35
				)
				(UnLoad 128 903)
				(ego
					view: 321
					setLoop: 0 1
					setCel: 0
					setCycle: CT 5 1 self
				)
			)
			(79
				(ego setPri: 120 setCycle: End self y: 88)
				(sfx number: 814 loop: 1 play:)
			)
			(80 (= ticks 90))
			(81
				(messager sayRange: 12 0 1 15 16 self)
			)
			(82
				(ego
					view: 326
					setLoop: 0 1
					setCel: 0
					setCycle: CT 3 1 self
				)
			)
			(83 (ego setCycle: CT 9 1 self))
			(84
				(theMusic number: 338 loop: 1 play:)
				(= cycles 2)
			)
			(85 (ego setCycle: End self))
			(86
				(messager say: 12 0 1 17 self)
			)
			(87
				(ego setCel: 2 setCycle: Beg self)
			)
			(88 (= ticks 30))
			(89
				(sfx number: 326 loop: 1 play: self)
				(rose cycleSpeed: 10 setCycle: End self)
			)
			(90)
			(91
				(sfx number: 385 loop: 1 play:)
				(messager say: 12 0 1 18 self)
				(= register 5)
				(= ticks 60)
			)
			(92
				(UpdateScreenItem
					((ScriptID 92 3) view: 1903 loop: 0 cel: 1)
				)
				(UnLoad 128 326)
				(ego
					view: 327
					setLoop: 0 1
					cycleSpeed: 6
					setCel: 0
					setCycle: End self
				)
				(switch (++ local2)
					(1
						(sfx number: 327 loop: 1 play:)
					)
					(3
						(sfx number: 385 loop: 1 play:)
					)
					(5
						(sfx number: 327 loop: 1 play:)
					)
					(7
						(sfx number: 385 loop: 1 play:)
					)
				)
			)
			(93 (= ticks 30))
			(94
				(if (-- register) (= state (- state 3)))
				(self cue:)
			)
			(95
				(ego cycleSpeed: 8 setCycle: Fwd)
				(if (talkers size:) (messager cue: 1))
				(= ticks 30)
			)
			(96
				(sfx number: 327 loop: 1 play:)
				(tank setCycle: 0)
				(motor setCycle: 0)
				(motorClock setCycle: 0)
				(bag setCycle: 0)
				(vacuum setCycle: 0)
				(= cycles 2)
			)
			(97
				(messager say: 12 0 1 19 self)
			)
			(98
				(tank setCycle: Fwd)
				(motor setCycle: Fwd)
				(motorClock setCycle: Fwd)
				(bag setCycle: Fwd)
				(vacuum setCycle: Fwd)
				(sfx number: 385 loop: 1 play:)
				(= ticks 90)
			)
			(99
				(if (Btst 99) (sfx number: 631 loop: 1 play:))
				(= ticks 30)
			)
			(100
				(sfx stop:)
				(if (Btst 99)
					(sfx2 number: 30 loop: -1 play:)
					(wirlpool
						view: 320
						setScript: 0
						setCycle: 0
						setLoop: 10 1
						cel: 0
						setPri: 110
						x: 61
						y: 91
					)
				)
				(= ticks 30)
			)
			(101
				(sfx2 stop:)
				(sfx number: 327 loop: 1 play:)
				(= ticks 90)
			)
			(102
				(= local1 0)
				(motorSfx number: 0 stop:)
				(fan cel: 0 setCycle: 0)
				(wirlpool setScript: 0 setCycle: 0)
				(motorClock setScript: 0 cel: 0 setCycle: 0)
				(motor setScript: 0 cel: 0 setCycle: 0)
				(tank setScript: 0 cel: 0 setCycle: 0)
				(vacuum setScript: 0 setCycle: 0)
				(bag setScript: 0 setCycle: 0)
				(= gLarryRoom 0)
				(theIconBar disableIcon: (ScriptID 0 8))
				(= cycles 2)
			)
			(103
				(cast eachElementDo: #hide)
				(thePlane drawPic: -1)
				(= cycles 2)
			)
			(104
				(motorSfx number: 0 stop:)
				(Print
					font: userFont
					addText: 12 0 1 20 5 1 320
					modeless: 2
					init:
				)
				(= ticks 300)
			)
			(105
				(if (Print dialog?) ((Print dialog?) dispose:))
				(Print modeless: 0)
				(theMusic number: 320 loop: -1 play:)
				(hose init:)
				(rose
					view: 329
					setLoop: 0
					setCel: 0
					x: 154
					y: 120
					setCycle: 0
				)
				(wirlpool
					view: 320
					setLoop: 10
					setCel: 0
					setPri: 110
					x: 61
					y: 91
				)
				(cast eachElementDo: #show)
				(ego view: 98 setCycle: 0)
				(larryClothes dispose:)
				(curRoom drawPic: 320 9)
				(= cycles 2)
			)
			(106
				(curRoom showControls: 1)
				(theGame controlsVisible: 0 drawControls:)
				(Bclr 74)
				(UpdateScreenItem
					((ScriptID 92 3) view: 1900 loop: 1 cel: 0)
				)
				(= ticks 120)
			)
			(107
				(messager sayRange: 12 0 1 21 24 self)
			)
			(108
				(rose setCycle: CT 2 1 self)
			)
			(109
				(messager say: 12 0 1 25 self)
			)
			(110 (rose setCycle: End self))
			(111
				(ego get: 28)
				(theGame changeScore: 15 176)
				(messager say: 12 0 1 26 self)
			)
			(112
				(rose view: 322 setLoop: 8 1 setCel: 0 setCycle: 0)
				(ego x: 183 y: 120 normalize: 900 1)
				(= cycles 2)
			)
			(113
				(ego cycleSpeed: 6 moveSpeed: 6 setHeading: 180 self)
			)
			(114
				(rose setCel: 4)
				(ego setMotion: MoveTo (ego x?) 150 self)
				(= cycles 2)
			)
			(115 (rose setCel: 2))
			(116
				(theMusic fade: 0 30 5 1)
				(messager say: 12 0 1 27 self)
				(ego setMotion: MoveTo (ego x?) 200 self)
			)
			(117)
			(118
				(Bclr 99)
				(messager say: 12 0 1 28 self)
			)
			(119 (= ticks 30))
			(120 (curRoom newRoom: 310))
		)
	)
)
