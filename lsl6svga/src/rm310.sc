;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include sci.sh)
(use Main)
(use n078)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use CycleBet)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Timer)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	local0
	local1
	[local2 2]
	local4
	local5
	local6
	local7
	gEgoCycleSpeed
)
(instance rm310 of LarryRoom
	(properties
		noun 15
		picture 310
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(fountainWaterSfx number: 311 loop: -1 play:)
		(super init:)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						14
						132
						14
						155
						90
						168
						99
						136
						134
						130
						175
						130
						202
						137
						211
						148
						283
						156
						283
						139
						312
						126
						304
						105
						283
						106
						235
						92
						224
						98
						224
						109
						99
						110
						99
						99
						84
						93
						53
						103
						20
						99
						6
						109
					yourself:
				)
		)
		(ego init: normalize: 900 setScaler: Scaler 100 69 93 82)
		(if (!= prevRoomNum 340)
			(theMusic number: 310 loop: -1 play: setVol: 127)
		)
		(enemaDoor init: approachVerbs: 4)
		(mensLockerDoor init: approachVerbs: 4)
		(girlsLockerDoor init: approachVerbs: 4)
		(lipoDoor init: approachVerbs: 4)
		(gary init: approachVerbs: 2 4 5 setScript: garyScr)
		(switch prevRoomNum
			(320
				(ego x: 15 y: 94 loop: 8 cel: 4 heading: 135)
				(enemaDoor view: 312 cel: 5)
				(curRoom setScript: exitRm320)
			)
			(340
				(ego x: 83 y: 91 loop: 8 cel: 2 heading: 180)
				(mensLockerDoor view: 312 cel: 4)
				(curRoom setScript: mensLockerRoomDoorScr 0 1)
			)
			(330
				(ego x: 310 y: 101 loop: 8 cel: 5 heading: 225)
				(lipoDoor view: 316 cel: 5)
				(curRoom setScript: exitLipoRoom)
			)
			(else 
				(ego x: 213 y: 190 loop: 8 cel: 3 heading: 360)
				(curRoom setScript: enterFromSouthScr)
			)
		)
		(switch global171
			(5
				(gammie init: posn: 241 170 setScript: gammieScr 0 0)
			)
			(6
				(gammie init: posn: 265 115 setScript: gammieScr 0 2)
			)
			(3
				(gammie init: posn: 241 200 setScript: gammieScr 0 3)
				(= global171 5)
			)
			(4
				(gammie init: posn: 241 200 setScript: gammieScr 0 1)
				(= global171 5)
			)
		)
		(brochure init: approachVerbs: 4 5)
		(fountainWater init: setCycle: Fwd)
		(theSign init:)
		(desk init: approachVerbs: 4 5)
		(towelRack init: approachVerbs: 4 2 5 6)
		(carpet init:)
		(rightLamp init:)
		(leftLamp init:)
		(upperLeftPlant init:)
		(lowerLeftPlant init:)
		(upperRightPlant init:)
		(lowerRightPlant init:)
		(statue init:)
		(fountain init: approachVerbs: 4 2 5 35)
		(rightPen init: approachVerbs: 4)
		(rightRegister init: approachVerbs: 4 2 5 6)
		(if (Btst 61)
			(= local0 1)
		else
			(helloTimer setReal: helloTimer (Random 20 40))
		)
	)
	
	(method (doit)
		(cond 
			(script 0)
			((> (ego y?) 139) (self setScript: exitSouthScr))
		)
		(super doit: &rest)
	)
	
	(method (newRoom n)
		(stenchTimer dispose: delete:)
		(helloTimer dispose: delete:)
		(flirtTimer dispose: delete:)
		(if (not (OneOf n 300 330 320 340)) (theMusic stop:))
		(if (OneOf n 330 320 340) (theMusic2 number: 0 stop:))
		(super newRoom: n)
	)
)

(instance gammie of Actor
	(properties
		sightAngle 10
		x 239
		y 200
		view 203
		signal $6021
	)
)

(instance brochure of View
	(properties
		noun 1
		sightAngle 10
		approachX 226
		approachY 113
		x 207
		y 82
		priority 82
		fixPriority 1
		view 310
		signal $6021
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 200 71 214 71 214 85 200 85
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (ego has: 6)
					(messager say: 1 5 4)
				else
					(curRoom setScript: getBrochureScr)
				)
			)
			(4
				(curRoom setScript: feelBrochureScr)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance enemaDoor of Prop
	(properties
		noun 4
		sightAngle 10
		approachX 31
		approachY 99
		x 2
		y 96
		priority 96
		fixPriority 1
		view 313
		signal $6821
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (Btst 176)
				(curRoom setScript: enterRm320 0 1)
			else
				(curRoom setScript: enterRm320 0 0)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance mensLockerDoor of Prop
	(properties
		noun 10
		sightAngle 10
		approachX 81
		approachY 94
		x 75
		y 90
		priority 90
		fixPriority 1
		view 313
		loop 1
		signal $6821
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: mensLockerRoomDoorScr 0 0)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance girlsLockerDoor of Prop
	(properties
		noun 7
		sightAngle 45
		approachX 232
		approachY 95
		x 222
		y 90
		priority 90
		fixPriority 1
		view 313
		loop 2
		signal $6821
		cycleSpeed 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: tryGirlsLockerDoor)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lipoDoor of Prop
	(properties
		noun 8
		sightAngle 10
		approachX 295
		approachY 106
		x 320
		y 109
		priority 100
		fixPriority 1
		view 313
		loop 3
		signal $6821
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom setScript: enterLipoRoom)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fountainWater of Prop
	(properties
		noun 5
		sightAngle 10
		x 151
		y 140
		priority 200
		fixPriority 1
		view 313
		loop 4
		signal $6821
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 6)
			(fountain doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance desk of Feature
	(properties
		noun 3
		sightAngle 10
		approachX 168
		approachY 110
		x 158
		y 74
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						113
						106
						100
						78
						111
						71
						132
						72
						133
						68
						137
						66
						141
						70
						143
						73
						204
						70
						216
						82
						212
						88
						199
						107
						114
						106
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance towelRack of Feature
	(properties
		noun 18
		sightAngle 10
		approachX 168
		approachY 110
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 117 69 108 27 162 30 209 30 200 71 118 69
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(= local6 4)
		(if (== theVerb 5)
			(cond 
				((not (ego has: 22)) (messager say: 18 5 15))
				((and (ego has: 38) (Btst 96)) (messager say: 18 5 10))
				((ego has: 38) (messager say: 18 5 14))
				(else (curRoom setScript: getTowel))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance carpet of Feature
	(properties
		noun 2
		sightAngle 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						39
						95
						73
						91
						107
						92
						112
						104
						198
						105
						207
						89
						253
						91
						298
						104
						302
						108
						273
						102
						265
						104
						268
						107
						290
						111
						269
						111
						272
						116
						285
						114
						283
						121
						296
						117
						283
						128
						308
						123
						296
						138
						194
						138
						194
						129
						169
						120
						166
						109
						142
						120
						122
						123
						106
						131
						104
						139
						10
						139
						0
						124
						0
						120
						22
						126
						9
						115
						33
						118
						22
						111
						43
						115
						36
						108
						22
						106
						45
						102
						39
						99
						11
						103
						15
						97
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 5)
			(curRoom setScript: carpetScr 0 theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fountain of Feature
	(properties
		noun 5
		sightAngle 10
		approachX 184
		approachY 132
		approachDist 10
		x 148
		y 128
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						104
						139
						105
						131
						118
						125
						144
						120
						143
						131
						150
						135
						155
						132
						152
						130
						153
						120
						184
						126
						195
						133
						195
						139
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: fountainScr 0 theVerb)
			)
			(5
				(curRoom setScript: fountainScr 0 theVerb)
			)
			(6
				(curRoom setScript: pissInFountain)
			)
			(35
				(curRoom setScript: wetCloth)
			)
			(37
				(messager say: 2 37 0 0 0 85)
			)
			(36
				(messager say: 2 36 0 0 0 85)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leftLamp of Feature
	(properties
		noun 9
		sightAngle 10
		x 44
		y 31
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 30 14 41 7 54 9 56 35 48 56 31 31
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightLamp of Feature
	(properties
		noun 14
		sightAngle 10
		x 292
		y 31
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 279 15 298 7 308 17 296 43 281 62 278 38
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightPen of View
	(properties
		noun 11
		sightAngle 10
		x 172
		y 72
		priority 71
		fixPriority 1
		view 310
		loop 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 168 67 176 67 183 82 170 82
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance rightRegister of Feature
	(properties
		noun 13
		sightAngle 10
		approachX 184
		approachY 108
		x 188
		y 79
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 183 83 181 74 197 74 199 83
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (Btst 43) (== theVerb 4)) (= local6 4) (messager say: 13 4 10))
			((== theVerb 4) (curRoom setScript: signRegister 0 1))
			(else (super doVerb: theVerb))
		)
	)
)

(instance upperLeftPlant of Feature
	(properties
		noun 12
		sightAngle 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						10
						13
						0
						54
						0
						55
						7
						29
						8
						18
						16
						55
						10
						15
						20
						47
						18
						17
						25
						44
						42
						13
						31
						30
						56
						5
						40
						2
						46
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance lowerLeftPlant of Feature
	(properties
		noun 12
		sightAngle 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						53
						14
						50
						16
						55
						6
						57
						17
						60
						10
						66
						6
						63
						2
						94
						23
						87
						8
						96
						30
						90
						40
						91
						38
						95
						12
						99
						7
						102
						40
						98
						38
						103
						24
						106
						42
						112
						38
						114
						23
						111
						25
						117
						14
						113
						8
						114
						19
						124
						4
						121
						4
						127
						10
						139
						4
						138
						0
						135
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance upperRightPlant of Feature
	(properties
		noun 12
		sightAngle 10
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						296
						0
						306
						11
						285
						6
						297
						13
						288
						21
						279
						16
						281
						25
						274
						25
						276
						31
						270
						37
						264
						28
						264
						35
						259
						35
						258
						14
						256
						37
						252
						39
						243
						24
						249
						6
						242
						18
						239
						15
						244
						5
						223
						11
						220
						8
						245
						0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance lowerRightPlant of Feature
	(properties
		noun 12
		sightAngle 10
		x 309
		y 120
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						319
						133
						314
						132
						306
						140
						294
						137
						308
						123
						290
						131
						285
						125
						298
						118
						271
						118
						266
						113
						281
						110
						264
						105
						265
						101
						298
						104
						266
						100
						262
						97
						281
						90
						302
						95
						286
						84
						302
						88
						293
						63
						311
						96
						319
						98
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance theSign of Feature
	(properties
		noun 16
		sightAngle 10
		x 153
		y 15
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						73
						20
						79
						12
						83
						13
						100
						0
						212
						0
						227
						11
						239
						12
						245
						21
						240
						33
						235
						33
						233
						42
						220
						47
						204
						39
						206
						34
						180
						27
						172
						32
						160
						28
						150
						32
						142
						27
						120
						28
						111
						30
						108
						41
						95
						46
						86
						41
						87
						37
						76
						35
						71
						30
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance statue of Feature
	(properties
		noun 17
		sightAngle 10
		x 148
		y 135
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						146
						87
						152
						84
						153
						79
						162
						79
						165
						87
						161
						91
						162
						95
						158
						98
						161
						106
						158
						108
						158
						114
						165
						111
						167
						118
						161
						118
						156
						121
						154
						119
						152
						130
						154
						133
						143
						133
						140
						126
						143
						119
						143
						103
						147
						98
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4) (curRoom setScript: fountainScr 0 theVerb))
			((OneOf theVerb 5 2) (curRoom setScript: statueScr 0 theVerb))
			((== theVerb 1) (curRoom setScript: lookStatueScr))
			(else (super doVerb: theVerb))
		)
	)
)

(instance fountainScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 270 self)
			)
			(1 (= cycles 2))
			(2
				(ego view: 901 setLoop: 5 cel: 0 setCycle: CT 2 1 self)
			)
			(3 (= ticks 30))
			(4
				(messager say: 5 register 0 0 self)
			)
			(5 (ego setCycle: Beg self))
			(6
				(ego normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance feelBrochureScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(= cycles 2)
			)
			(1
				(ego setMotion: PolyPath 226 113 self)
			)
			(2 (ego setHeading: 270 self))
			(3 (= cycles 2))
			(4
				(messager sayRange: 1 4 0 1 2 self)
			)
			(5
				(ego view: 901 setLoop: 3 cel: 0 setCycle: CT 3 1 self)
			)
			(6 (= cycles 2))
			(7 (messager say: 1 4 0 6 self))
			(8 (messager say: 1 4 0 7 self))
			(9 (ego setCycle: Beg self))
			(10 (= cycles 2))
			(11
				(messager say: 1 4 0 8 self)
			)
			(12
				(ego normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance getBrochureScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 226 113 self)
			)
			(1 (ego setHeading: 270 self))
			(2 (= cycles 2))
			(3
				(ego view: 901 setLoop: 3 cel: 0 setCycle: CT 3 1 self)
			)
			(4 (messager say: 1 5 0 0 self))
			(5 (= ticks 60))
			(6 (ego setCycle: Beg self))
			(7
				(ego normalize: 900 1 1 get: 6)
				(theGame changeScore: 2 175 handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance signRegister of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(Bset 43)
				(= local1 2)
				(gary cel: 0 setCycle: 0 setScript: 0)
				(ego setMotion: PolyPath 184 108 self)
			)
			(1 (ego setHeading: 310 self))
			(2 (= cycles 2))
			(3
				(if register
					(messager say: 13 4 0 0 self)
				else
					(= cycles 2)
				)
			)
			(4
				(ego
					view: 314
					setLoop: 0
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(5
				(rightPen hide:)
				(ego setCycle: End self)
			)
			(6
				(if (not (-- local1))
					(ego setCycle: CT 3 -1 self)
				else
					(-- state)
					(ego setCycle: CycleBet 7 10 3 self)
				)
			)
			(7
				(rightPen show:)
				(ego setCycle: Beg self)
			)
			(8
				(ego normalize: 900 7 1)
				(self setScript: getTowel self)
			)
			(9
				(theGame handsOn:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance statueScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 175 129 self)
			)
			(1 (= ticks 10))
			(2
				(if (!= (ego heading?) 270)
					(ego setHeading: 270 self)
				else
					(= cycles 2)
				)
			)
			(3 (= cycles 2))
			(4
				(ego view: 901 setLoop: 3 cel: 0 setCycle: CT 3 1 self)
			)
			(5 (= ticks 30))
			(6
				(if (== register 5)
					(messager say: 17 5 0 1 self)
				else
					(messager say: 17 register 0 0 self)
				)
			)
			(7 (ego setCycle: Beg self))
			(8
				(if (OneOf register 4 2)
					(= state 11)
					(= ticks 30)
				else
					(ego view: 901 setLoop: 1 cel: 0 setCycle: CT 3 1 self)
				)
			)
			(9 (= cycles 2))
			(10
				(messager say: 17 5 0 2 3 self)
			)
			(11 (ego setCycle: Beg self))
			(12
				(ego normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance fountainWaterSfx of Sound
	(properties
		flags $0001
		loop -1
	)
)

(instance sfx of Sound
	(properties
		flags $0001
	)
)

(instance sfx2 of Sound
	(properties
		flags $0001
	)
)

(instance pissingSfx of Sound
	(properties
		flags $0001
		loop -1
	)
)

(instance doGaryScr of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 152])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 173 110 self)
			)
			(1
				(if (!= (ego heading?) 310)
					(ego setHeading: 310 self)
				else
					(= cycles 2)
				)
			)
			(2 (= cycles 2))
			(3
				(ego
					view: 908
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(4
				(sfx number: 35 loop: 1 play:)
				(ego setCycle: CT 6 1 self)
			)
			(5
				(sfx number: 312 loop: 1 play: self)
			)
			(6 (messager say: 6 6 0 0 self))
			(7
				(gary setScript: 0 view: 310 setLoop: 2)
				(theMusic number: 0 stop:)
				(theMusic2 number: 0 stop:)
				(EgoDead 16 self)
			)
			(8
				(theMusic number: 310 loop: -1 play: setVol: 127)
				(gary setScript: garyScr)
				(ego normalize: 900 7 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance lookStatueScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(messager say: 17 1 0 1 self)
			)
			(2 (= seconds 3))
			(3
				(messager say: 17 1 0 2 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance mensLockerRoomDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register (= state 5) else (ego loop: 8 cel: 3))
				(= cycles 2)
			)
			(1
				(ego view: 901 setLoop: 6 cel: 0 setCycle: End self)
			)
			(2
				(sfx number: 32 loop: 1 play: self)
			)
			(3
				(mensLockerDoor view: 312 setCycle: End self)
			)
			(4
				(ego
					view: 900
					setLoop: 3
					setCycle: Fwd
					setMotion: MoveTo 83 87 self
				)
			)
			(5
				(if (and (not (Btst 48)) local0)
					(-- state)
					(Bset 48)
					(ego setCycle: 0 cel: 4 setLoop: 3)
					(messager say: 6 2 10 0 self)
					(theMusic fade: 90 10 10 0)
				else
					(theMusic setVol: 90)
					(curRoom newRoom: 340)
				)
			)
			(6
				(theMusic fade: 127 10 10 0)
				(ego
					setLoop: 2
					setCycle: Fwd
					setMotion: MoveTo 83 105 self
				)
			)
			(7
				(ego normalize: 900 2 1)
				(mensLockerDoor setCycle: Beg self)
			)
			(8
				(sfx number: 33 loop: 1 play:)
				(mensLockerDoor view: 313)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitSouthScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo (ego x?) 190 self)
				(theMusic fade: 0 10 10 1)
			)
			(1 (= ticks 120))
			(2
				(= gGEgoX (ego x?))
				(theMusic number: 0 stop:)
				(curRoom newRoom: 300)
			)
		)
	)
)

(instance garyScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(if (not (Random 0 1))
					(gary view: 311 setLoop: 1 cel: 0 setCycle: End self)
					(= register (Random 2 5))
				else
					(= state 3)
					(gary view: 311 setLoop: 2 cel: 0 setCycle: End self)
				)
			)
			(2 (= ticks (Random 5 10)))
			(3
				(if (not (-- register))
					(= cycles 2)
				else
					(= state 1)
					(gary cel: 2 setCycle: End self)
				)
			)
			(4 (gary cel: 0) (self init:))
		)
	)
)

(instance carpetScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (< (ego heading?) 181)
					(ego setHeading: 45 self)
				else
					(ego setHeading: 270 self)
				)
			)
			(1 (= cycles 2))
			(2
				(ego
					view: 901
					setLoop: (if (== (ego heading?) 45) 4 else 5)
					cel: 0
					setCycle: 0
				)
				(= cycles 2)
			)
			(3 (ego setCycle: CT 3 1 self))
			(4 (= ticks 30))
			(5
				(messager say: 2 register 0 0 self)
			)
			(6 (ego setCycle: Beg self))
			(7
				(ego normalize: 900 1 1 ignoreActors: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance wetCloth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 270 self)
			)
			(1 (= cycles 2))
			(2
				(ego view: 901 setLoop: 5 cel: 0 setCycle: CT 2 1 self)
			)
			(3 (= ticks 60))
			(4
				(theGame changeScore: 6 250)
				(= global185 6)
				((inventory at: 39) cue:)
				(messager say: 2 35 0 0 self 85)
			)
			(5 (= ticks 60))
			(6 (ego setCycle: Beg self))
			(7
				(ego normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterRm320 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					(register (ego view: 901 setLoop: 1 cel: 0 setCycle: CT 3 1 self))
					(
					(and (> (ego heading?) 225) (< (ego heading?) 290)) (= state 4) (= cycles 2))
					(else (= state 4) (ego setHeading: 270 self))
				)
			)
			(1
				(sfx number: 34 loop: 1 play:)
				(= ticks 120)
			)
			(2
				(messager say: 4 4 11 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
			(5
				(gary setScript: 0 view: 310 setLoop: 2)
				(= cycles 2)
			)
			(6
				(ego view: 901 setLoop: 1 cel: 0 setCycle: CT 3 1 self)
			)
			(7
				(sfx number: 32 loop: 1 play: self)
			)
			(8
				(enemaDoor view: 312 setCycle: End self)
			)
			(9
				(ego
					normalize: 900 1 1
					setLoop: 7
					setCycle: Fwd
					setMotion: MoveTo 16 96 self
				)
				(theMusic fade: 30 10 10 1)
			)
			(10
				(ego setCycle: 0)
				(theMusic number: 0 stop:)
				(curRoom newRoom: 320)
			)
		)
	)
)

(instance tryGirlsLockerDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(ego setHeading: 270)
				(= ticks 30)
			)
			(1 (messager say: 7 4 0 1 self))
			(2 (= ticks 30))
			(3 (messager say: 7 4 0 2 self))
			(4 (= ticks 30))
			(5
				(ego normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitLipoRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(ego
					setLoop: 5
					setCycle: Fwd
					setMotion: MoveTo 293 113 self
				)
			)
			(1
				(ego normalize: 900 5 1)
				(lipoDoor setCycle: Beg self)
			)
			(2
				(sfx number: 33 loop: 1 play:)
				(lipoDoor view: 313)
				(if (and (not (Btst 42)) (Btst 188)) (Bset 42))
				(if (and (== global171 9) (not (Btst 94)))
					(Bset 94)
					(ego setHeading: 270 self)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(3 (= cycles 2))
			(4
				(= local6 4)
				(messager say: 0 0 2 0 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance giveGaryKey of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 152])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(ego setMotion: PolyPath 173 110 self)
			)
			(1 (= cycles 3))
			(2
				(if (!= (ego heading?) 310)
					(ego setHeading: 310 self)
				else
					(= cycles 2)
				)
			)
			(3 (= ticks 90))
			(4 (messager say: 6 7 0 0 self))
			(5 (EgoDead 16 self))
			(6
				(theMusic number: 310 loop: -1 play: setVol: 127)
				(ego normalize: 900 7 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance gary of Prop
	(properties
		noun 6
		sightAngle 10
		approachX 168
		approachY 110
		x 148
		y 74
		priority 74
		fixPriority 1
		view 311
		loop 1
		signal $6821
		cycleSpeed 8
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (not (OneOf local6 3 1)) (== (ego view?) 909))
			(= local6 2)
			(flirtTimer setReal: flirtTimer 2)
		)
	)
	
	(method (doVerb theVerb)
		(= local6 4)
		(switch theVerb
			(2
				(curRoom setScript: talkToGary)
			)
			(6
				(curRoom setScript: doGaryScr)
			)
			(7
				(curRoom setScript: giveGaryKey)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance gammieScr of Script
	(properties)
	
	(method (dispose)
		(if global205 (proc79_7))
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch register
					(0
						(gammie
							setCycle: Walk
							ignoreActors: 0
							setMotion: MoveTo 265 115 self
						)
					)
					(3
						(= register 0)
						(= seconds 6)
						(-- state)
					)
					(else 
						(= register 0)
						(= seconds 3)
						(-- state)
					)
				)
				(if (not global205) (proc79_8 2))
			)
			(1
				(gammie setMotion: MoveTo 307 109 self)
				(if (!= (ego heading?) 90) (ego setHeading: 90))
			)
			(2
				(sfx number: 32 loop: 1 play:)
				(gammie
					setLoop: 4
					cel: 3
					setCycle: 0
					setPri: (+ (lipoDoor priority?) 1)
					ignoreActors: 1
				)
				(lipoDoor view: 316 setCycle: End self)
				(= global171 6)
			)
			(3
				(gammie
					x: 308
					y: 104
					setPri: (- (lipoDoor priority?) 1)
				)
				(= ticks 10)
				(ego setHeading: 45)
			)
			(4
				(lipoDoor setCycle: Beg self)
			)
			(5
				(sfx number: 33 loop: 1 play:)
				(lipoDoor view: 313)
				(= global171 7)
				(theGame handsOn:)
				(gammie dispose:)
			)
		)
	)
)

(instance enterFromSouthScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 30)
			)
			(1
				(ego setMotion: MoveTo 213 135 self)
			)
			(2
				(if (not (gammie script?))
					(theGame handsOn:)
					(ego normalize: 900 3 1)
				else
					(ego setHeading: 90)
				)
				(= gGEgoX 0)
				(if (and (Btst 55) (not (Btst 107)))
					(stenchTimer setReal: stenchTimer 5)
				)
				(self dispose:)
			)
		)
	)
)

(instance stenchTimer of Timer
	(properties)
	
	(method (cue)
		(cond 
			((OneOf local6 4 3 1))
			((not (proc79_14)) (= local6 4) (messager say: 0 0 16) (Bset 107))
			(else (self setReal: self 3))
		)
	)
)

(instance helloTimer of Timer
	(properties)
	
	(method (cue)
		(cond 
			((OneOf local6 4 3))
			((== local6 1) (theGame handsOn:) (= local6 4))
			((not (proc79_14))
				(theGame handsOff:)
				(cond 
					((< (ego x?) 132) (ego setHeading: 45))
					((> (ego x?) 173) (ego setHeading: 310))
					(else (ego setHeading: 360))
				)
				(messager sayRange: 1 4 0 3 5 self)
				(= local6 1)
			)
			(else (self setReal: self 3))
		)
	)
)

(instance getTowel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(= cycles 2)
			)
			(1
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(if (not (Btst 43))
					(messager say: 18 5 0 0 self)
				else
					(ego normalize: 900 7 1 loop: 8 cel: 7)
					(++ state)
					(= cycles 2)
				)
			)
			(2
				(= next signRegister)
				(self dispose:)
			)
			(3
				(gary
					setLoop: 3
					setCel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(4
				(ego setMotion: PolyPath 148 110 self)
			)
			(5 (ego setHeading: 360 self))
			(6 (= cycles 2))
			(7
				(ego
					view: 314
					setLoop: 1
					setCel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(8
				(gary cel: 0 setLoop: 1 setScript: garyScr)
				(= ticks 90)
			)
			(9 (ego setCycle: Beg self))
			(10
				(ego
					cycleSpeed: gEgoCycleSpeed
					get: 38
					normalize: 900 3 1
				)
				(theGame handsOn: changeScore: 3 233)
				(if (not local0) (= local0 1) (Bset 61))
				(self dispose:)
			)
		)
	)
)

(instance talkToGary of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(= cycles 2)
			)
			(1
				(if (not (ego has: 22))
					(if (not local0)
						(= local0 1)
						(messager say: 6 2 12 0 self)
					else
						(messager say: 6 2 13 0 self)
					)
				else
					(switch (++ local0)
						(1
							(Bset 61)
							(messager say: 6 2 6 0 self)
						)
						(2 (messager say: 6 2 7 0 self))
						(else 
							(messager say: 6 2 8 0 self)
						)
					)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance flirtTimer of Timer
	(properties)
	
	(method (cue)
		(cond 
			((OneOf local6 4 1))
			((== local6 3) (= local6 4) (theGame handsOn:))
			((not (proc79_14))
				(theGame handsOff:)
				(messager say: 6 2 5 (Random 1 2) self)
				(= local6 3)
			)
			(else (self setReal: self 2))
		)
	)
)

(instance exitRm320 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(= cycles 2)
			)
			(1
				(ego
					setLoop: 4
					setCycle: Fwd
					setMotion: MoveTo 28 109 self
				)
			)
			(2
				(ego normalize: 900 4 1)
				(enemaDoor setCycle: Beg self)
			)
			(3
				(sfx number: 33 loop: 1 play:)
				(enemaDoor view: 313)
				(if (and (not (Btst 44)) (Btst 176))
					(Bset 44)
					(ego setHeading: 90 self)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(4 (= ticks 90))
			(5
				(= local6 4)
				(messager say: 0 0 1 0 self)
			)
			(6 (= cycles 2))
			(7
				(theGame handsOn:)
				(= cycles 2)
				(proc78_0)
			)
			(8 (self dispose:))
		)
	)
)

(instance enterLipoRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (== global100 2) (Btst 42))
					(ego view: 901 setLoop: 0 cel: 0 setCycle: CT 3 1 self)
				else
					(= state 4)
					(= cycles 2)
				)
			)
			(1
				(sfx number: 34 loop: 1 play:)
				(= ticks 120)
			)
			(2 (messager say: 8 4 9 0 self))
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize: 900 0 1)
				(theGame handsOn:)
				(self dispose:)
			)
			(5
				(if (== global171 7)
					(ego setHeading: 270)
					(messager say: 7 4 0 1 self)
				else
					(= state 9)
					(= cycles 2)
				)
			)
			(6 (messager say: 0 0 3 1 self))
			(7 (messager say: 0 0 3 2 self))
			(8 (ego setHeading: 90 self))
			(9 (= cycles 2))
			(10
				(ego view: 901 setLoop: 0 cel: 0 setCycle: CT 3 1 self)
			)
			(11
				(sfx number: 32 loop: 1 play: self)
				(lipoDoor view: 316 setCycle: End self)
			)
			(12)
			(13
				(ego
					view: 900
					setLoop: 6
					setCycle: Fwd
					setMotion: MoveTo 309 106 self
				)
			)
			(14
				(ego
					setPri: (- (lipoDoor priority?) 1)
					setMotion: MoveTo 319 99 self
				)
				(theMusic fade: 30 10 10 1)
			)
			(15
				(ego hide:)
				(theMusic number: 0 loop: 1 stop:)
				(= cycles 2)
			)
			(16 (curRoom newRoom: 330))
		)
	)
)

(instance pissInFountain of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local6 4)
				(proc79_11 351 30 35)
				(if (and (!= (ego x?) 203) (!= (ego y?) 138))
					(ego setMotion: PolyPath 203 138 self)
				else
					(= cycles 2)
				)
			)
			(1
				(if (!= (ego heading?) 310)
					(ego setHeading: 310 self)
				else
					(= cycles 2)
				)
			)
			(2 (= cycles 2))
			(3
				(if (not global205) (proc79_8 18))
				(ego
					view: 908
					setLoop: 1 1
					cel: 0
					cycleSpeed: 4
					setCycle: CT 3 1 self
				)
			)
			(4
				(ego setCycle: CT 6 1 self)
				(sfx number: 35 loop: 1 play:)
			)
			(5
				(if (== (++ local4) 1)
					(messager say: 5 6 0 0 self)
				else
					(self cue:)
				)
				(= local5 3)
			)
			(6
				(if (== local4 1)
					(ego setLoop: 3 1 cycleSpeed: 6 setCycle: Fwd)
					(pissingSfx number: 30 loop: -1 play:)
					(= seconds 8)
				else
					(self cue:)
				)
			)
			(7
				(pissingSfx number: 0 stop:)
				(ego setLoop: 5 1 setCycle: 0 cel: 0)
				(= ticks 5)
			)
			(8
				(sfx number: 351 loop: 1 play:)
				(ego cel: 1)
				(= ticks 5)
			)
			(9 (ego cel: 2) (= ticks 5))
			(10
				(ego cel: 3)
				(if (-- local5) (= state 6))
				(= ticks 60)
			)
			(11
				(if (> local4 1)
					(messager say: 12 6 25 1 self 630)
				else
					(self cue:)
				)
			)
			(12
				(ego setLoop: 5 1 cel: 2 setCycle: End self)
				(sfx number: 35 loop: 1 play:)
			)
			(13
				(messager say: 6 2 5 (Random 1 2) self)
			)
			(14
				(proc79_12 351 30 35)
				(theGame handsOn:)
				(ego normalize: 900 7 1 edgeHit: 0)
				(self dispose:)
			)
		)
	)
)
