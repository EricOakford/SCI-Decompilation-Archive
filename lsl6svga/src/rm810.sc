;;; Sierra Script 1.0 - (do not remove this comment)
(script# 810)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use System)

(public
	rm810 0
)

(local
	local0
	local1 =  -1
)
(instance rm810 of LarryRoom
	(properties
		noun 1
		picture 810
		horizon 0
	)
	
	(method (init)
		(self
			addObstacle:
				((Polygon new:)
					type: 3
					init: 0 113 319 113 319 77 194 66 137 66 137 46 135 46 135 66 85 66 0 72
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 138 94 138 108 78 108 78 94
					yourself:
				)
		)
		(if global100 (= local0 2) else (= local0 4))
		(super init: &rest)
		(ego normalize: 900 init:)
		(if
		(or (== prevRoomNum 860) (not (theMusic handle?)))
			(theMusic number: 820 loop: -1 play:)
		)
		(theMusic2 number: 0 stop:)
		(switch prevRoomNum
			(860
				(ego
					x: 135
					y: 54
					setLoop: 2
					setCycle: Fwd
					scaleSignal: 1
					scaleX: 28
					scaleY: 28
					cycleSpeed: 6
					moveSpeed: 6
					setPri: 45
				)
				(self setScript: from860Scr)
			)
			(680
				(ego posn: 340 100)
				(self setScript: from680Scr)
			)
			(else 
				(ego posn: -15 94)
				(self setScript: from800Scr)
			)
		)
		(lRails init: approachVerbs: 4 1 2 5 6)
		(stairs init: approachVerbs: 4 1 2 5 6)
		(rRails init: approachVerbs: 4 1 2 5 6)
		(leftBackBushes init: approachVerbs: 4 1 2 5 6)
		(rightBackBushes init: approachVerbs: 4 1 2 5 6)
		(tree init:)
		(lWater init:)
		(rWater init:)
		(mWater init:)
		(frontBushes init: approachVerbs: 4 1 2 5 6)
		(rightLawn init:)
		(leftLawn init:)
		(rightTree init: approachVerbs: 4 1 2 5 6)
	)
	
	(method (doit)
		(cond 
			(script)
			((< (ego y?) 55) (self setScript: exitNorth))
			((== (ego edgeHit?) 2) (self setScript: exitEastScr))
			((== (ego edgeHit?) 4) (self setScript: exitWestScr))
		)
		(super doit: &rest)
	)
	
	(method (newRoom n)
		(if (and (== global100 860) (!= n 860))
			(= global100 0)
		)
		(super newRoom: n)
	)
)

(instance from680Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(ego setMotion: MoveTo 310 (ego y?) self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance from800Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(ego setMotion: MoveTo 15 94 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitWestScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo -20 (ego y?) self)
			)
			(1 (curRoom newRoom: 800))
		)
	)
)

(instance exitEastScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 340 (ego y?) self)
				(theMusic fade: 0 10 10 1)
			)
			(1 (curRoom newRoom: 680))
		)
	)
)

(instance tree of Feature
	(properties
		noun 2
		approachX 73
		approachY 113
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						206
						0
						208
						8
						198
						19
						200
						22
						193
						29
						187
						27
						184
						21
						180
						24
						178
						22
						175
						25
						168
						26
						156
						26
						151
						23
						147
						19
						146
						23
						135
						18
						114
						41
						116
						65
						120
						75
						130
						102
						129
						109
						118
						117
						98
						112
						88
						100
						82
						28
						41
						18
						0
						14
						0
						0
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (and (== theVerb 4) (not (-- local0)))
			(= global100 860)
			(Bset 226)
			(while (== (= temp0 (Random 1 61)) 9)
			)
			(DoAudio 2 611 1 4 temp0 1)
			(Print
				width: 160
				font: userFont
				addTitle: {Carlos, are you cheating again?}
				addText: 1 4 temp0 1 50 1 611
				fore: 67
				addText: {Shablee's waiting for you on the beach.} 50 17
				fore: 0
				addIcon: 1592 1 0 0 0
				init:
			)
			(theGame handsOff:)
			(ego setMotion: PolyPath 137 53)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance lRails of Feature
	(properties
		noun 3
		approachX 66
		approachY 68
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 84 61 52 62 51 45 84 41
					yourself:
				)
		)
		(super init:)
	)
)

(instance stairs of Feature
	(properties
		noun 4
		approachX 173
		approachY 73
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 117 43 128 55 146 54 159 39 155 63 116 63
					yourself:
				)
		)
		(super init:)
	)
)

(instance rRails of Feature
	(properties
		noun 5
		approachX 173
		approachY 73
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 161 39 181 39 180 63 155 62
					yourself:
				)
		)
		(super init:)
	)
)

(instance doDownStairsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom newRoom: 860)
			)
		)
	)
)

(instance leftBackBushes of Feature
	(properties
		noun 9
		approachX 28
		approachY 71
		x 28
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 16 24 15 49 22 63 31 84 31 85 41 61 40 50 45 52 62 10 62 0 66
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightBackBushes of Feature
	(properties
		noun 9
		approachX 247
		approachY 71
		x 247
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						159
						40
						157
						38
						171
						37
						176
						30
						189
						33
						198
						27
						203
						30
						207
						24
						213
						27
						215
						30
						229
						31
						235
						35
						236
						30
						240
						33
						244
						27
						248
						31
						258
						25
						264
						31
						270
						30
						269
						39
						266
						41
						271
						53
						284
						52
						289
						44
						294
						40
						295
						36
						298
						32
						319
						28
						319
						69
						240
						66
						233
						62
						183
						64
						178
						60
						179
						44
						184
						44
						186
						38
					yourself:
				)
		)
		(super init:)
	)
)

(instance frontBushes of Feature
	(properties
		noun 6
		approachX 159
		approachY 113
		y 189
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						0
						104
						19
						104
						17
						100
						22
						99
						25
						104
						52
						103
						50
						100
						59
						98
						62
						95
						67
						95
						66
						99
						77
						96
						89
						95
						87
						101
						91
						101
						95
						105
						98
						113
						104
						113
						106
						116
						119
						116
						128
						108
						129
						104
						135
						100
						137
						102
						140
						102
						139
						98
						136
						97
						137
						96
						149
						98
						149
						102
						159
						102
						156
						104
						168
						106
						192
						106
						191
						108
						207
						111
						208
						106
						218
						103
						215
						110
						229
						114
						227
						116
						236
						115
						233
						110
						243
						112
						243
						116
						249
						114
						249
						118
						277
						111
						292
						116
						293
						111
						308
						110
						313
						108
						319
						111
						319
						139
						0
						139
					yourself:
				)
		)
		(super init:)
	)
)

(instance leftLawn of Feature
	(properties
		noun 8
		x 39
		y 94
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 66 12 63 52 61 44 71 88 75 86 95 39 105 0 104
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightLawn of Feature
	(properties
		noun 8
		x 240
		y 98
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						195
						74
						182
						63
						228
						63
						319
						70
						319
						108
						293
						111
						292
						116
						279
						111
						253
						117
						233
						110
						235
						114
						229
						116
						216
						110
						219
						106
						212
						104
						205
						110
						151
						102
						150
						99
						138
						97
						140
						103
						134
						101
						119
						75
					yourself:
				)
		)
		(super init:)
	)
)

(instance rightTree of Feature
	(properties
		noun 7
		approachX 247
		approachY 71
		x 247
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						319
						0
						319
						26
						303
						24
						281
						57
						270
						54
						264
						45
						269
						37
						271
						30
						245
						22
						225
						22
						227
						16
						212
						16
						194
						0
					yourself:
				)
		)
		(super init:)
	)
)

(instance lWater of Feature
	(properties
		noun 10
		y 1
	)
	
	(method (init)
		(self
			setPolygon: ((Polygon new:)
				type: 0
				init: 63 33 43 18 84 29
				yourself:
			)
		)
		(super init:)
	)
)

(instance rWater of Feature
	(properties
		noun 10
		y 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						114
						41
						135
						18
						168
						27
						181
						22
						192
						28
						199
						21
						198
						18
						201
						15
						227
						15
						224
						20
						250
						22
						257
						25
						254
						28
						240
						30
						240
						32
						232
						32
						218
						30
						216
						32
						208
						25
						205
						26
						205
						30
						201
						31
						196
						27
						192
						36
						188
						33
						183
						33
						178
						30
						174
						33
						175
						36
						173
						38
						159
						38
						145
						48
						140
						56
						129
						56
					yourself:
				)
		)
		(super init:)
	)
)

(instance mWater of Feature
	(properties
		noun 10
		y 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 121 20 121 23 111 31 110 18
					yourself:
				)
		)
		(super init:)
	)
)

(instance from860Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 28)
				(= ticks 60)
			)
			(1
				(if (< (++ register) 110)
					(ego scaleX: register scaleY: register)
					(-- state)
					(= ticks 2)
				else
					(ego
						setPri: -1
						setScaler: Scaler 100 86 58 53
						setMotion: MoveTo (ego x?) 63 self
					)
				)
			)
			(2
				(ego normalize: 900 2 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance exitNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: 3
					setCycle: Fwd
					cycleSpeed: 6
					moveSpeed: 6
					setScale: 0
					setPri: 45
				)
				(= register 110)
				(= cycles 2)
			)
			(1
				(-- register)
				(= local1 (+ (ego y?) 1))
				(cond 
					((OneOf register 30 40 50 60 70 80) (-- state) (ego y: local1) (self cue:))
					((> register 28)
						(ego scaleSignal: 1 scaleX: register scaleY: register)
						(-- state)
						(= ticks 2)
					)
					(else (ego hide:) (= cycles 2))
				)
			)
			(2 (curRoom newRoom: 860))
		)
	)
)

(instance sfx of Sound
	(properties
		flags $0005
	)
)
