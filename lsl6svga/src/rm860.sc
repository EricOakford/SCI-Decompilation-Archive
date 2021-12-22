;;; Sierra Script 1.0 - (do not remove this comment)
(script# 860)
(include sci.sh)
(use Main)
(use fileScr)
(use EgoDead)
(use LarryRoom)
(use Array)
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
	rm860 0
)

(local
	local0
	local1 =  4
	local2
	local3
	local4
	local5
	local6
	local7
	local8
	local9
	local10
	local11
	gEgoCycleSpeed
	gEgoCycleSpeed_2
	local14
	local15
)
(instance rm860 of LarryRoom
	(properties
		noun 6
		picture 860
		horizon 0
		autoLoad 0
	)
	
	(method (init)
		(if (== global100 860)
			(= local1 3)
			(= local7 3)
			(= prevRoomNum 590)
			(ego get: 8)
			(ego put: 17 590)
			(= global100 1)
		)
		(if (OneOf prevRoomNum 590 620) (= style 9))
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						253
						145
						319
						111
						319
						105
						295
						115
						262
						108
						243
						108
						243
						100
						226
						100
						226
						90
						252
						90
						252
						79
						225
						65
						107
						65
						0
						65
						0
						145
					yourself:
				)
		)
		(cond 
			((== prevRoomNum 230)
				(ego x: 343 y: 106 setHeading: 225)
				(curRoom setScript: enterFrom230Scr)
			)
			((OneOf prevRoomNum 590 620)
				(Bset 24)
				(Bset 110)
				(= local4 1)
				(theMusic number: 861 loop: -1 play:)
				(theMusic2 number: 0 stop:)
				(curRoom setScript: shabCartoonScr)
			)
			(else (ego x: 200 y: 200) (curRoom setScript: enterFrom810Scr))
		)
		(if (and (Btst 24) (not (Btst 275)))
			(theChampagne init: approachVerbs: 4 2 5 6)
			(curRoom
				addObstacle:
					(= local9
						((Polygon new:)
							type: 2
							init: 240 125 240 136 214 136 214 125
							yourself:
						)
					)
			)
		)
		(if (not local4)
			(path1 init:)
			(path2 init:)
			(path3 init:)
			(shore init:)
			(sky init:)
			(shallowWater init:)
			(deepWater init:)
			(jungle init:)
			(if
				(or
					(OneOf prevRoomNum 810 230)
					(not (theMusic handle?))
				)
				(theMusic number: 860 loop: -1 play:)
			)
			(ego init: normalize: 900 setScaler: Scaler 100 23 141 66)
			(bird init: hide: setScript: birdScr)
			(wave init: setScript: waveScr)
			(birdSfx number: 812 loop: -1 play:)
		)
		(Load rsVIEW 865)
	)
	
	(method (doit)
		(cond 
			(script)
			(local4)
			(
				(and
					(== (ego view?) 900)
					(shallowWater onMe: (ego x?) (ego y?))
				)
				(ego view: 865)
			)
			(
				(and
					(== (ego view?) 865)
					(not (shallowWater onMe: (ego x?) (ego y?)))
				)
				(ego view: 900)
			)
			((deepWater onMe: (ego x?) (ego y?)) (curRoom setScript: drownScr))
			((== (ego edgeHit?) 2) (curRoom setScript: exitEastScr))
			((> (ego y?) 143) (curRoom setScript: exitSouthScr))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: curRoom)
		(Bclr 110)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				(
					(and
						(or (talkers size:) (Print dialog?))
						(Print dialog?)
					)
				)
				((and (OneOf theVerb 4 1 2 5 6) local4) (messager say: 6 theVerb 2) (return 1))
				(else (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(if (talkers size:) (messager cue: 1))
		(= local10 1)
		(= gNewStr_4 (ByteArray new: 40))
		(= gNewStr_3 (ByteArray new: 40))
		(Message 0 861 0 8 0 1 (gNewStr_4 data?))
		(Message 0 861 0 8 0 2 (gNewStr_3 data?))
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 170 65)
		(if
			(Print
				width: 150
				font: userFont
				addTitle: (gNewStr_3 data?)
				addText: (gNewStr_4 data?) 50 0
				addIcon: 1911 0 0 0 0
				addButton: 0 2 8 0 1 50 33 861
				addButton: 1 1 8 0 1 125 33 861
				init:
			)
			(curRoom setScript: 0)
			(ego put: 8 setScript: 0)
			(theGame
				handsOff:
				changeScore: 13 274
				setCursor: waitCursor 1
			)
			(curRoom setScript: exitScr)
		else
			(= local10 0)
			(= gLarryRoom curRoom)
			(theIconBar enableIcon: (ScriptID 0 8) show:)
			(theGame setCursor: gGButtonBarGetCursor)
		)
		(gNewStr_3 dispose:)
		(= gNewStr_3 0)
		(gNewStr_4 dispose:)
		(= gNewStr_4 0)
	)
	
	(method (notify param1 &tmp temp0)
		(if argc (= temp0 param1) else (= temp0 0))
		(if (or temp0 (and (not local15) (not script)))
			(ego put: 25)
			((inventory at: 25)
				message: 42
				noun: 22
				owner: 510
				view: 40
				setCursor: 40 0 0
				yourself:
			)
			(messager say: 13 0 0 0 param1)
		else
			(= local15 1)
		)
	)
)

(instance enterFrom230Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 10))
			(1
				(ego setMotion: MoveTo 280 122 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFrom810Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 10))
			(1
				(ego setMotion: MoveTo 200 139 self)
			)
			(2
				(ego setMotion: PolyPath 200 135 self)
			)
			(3
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
				(ego setMotion: MoveTo (ego x?) 200 self)
			)
			(1
				(theMusic fade:)
				(curRoom newRoom: 810)
			)
		)
	)
)

(instance exitEastScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 325 (ego y?) self)
			)
			(1
				(theMusic fade:)
				(curRoom newRoom: 230)
			)
		)
	)
)

(instance oceanSfx of Sound
	(properties
		flags $0001
	)
)

(instance sfx of Sound
	(properties
		flags $0001
	)
)

(instance fireFx of Sound
	(properties
		flags $0001
		loop -1
	)
)

(instance birdSfx of Sound
	(properties
		flags $0001
	)
)

(instance hole of View
	(properties
		x 130
		y 99
		scaleX 69
		scaleY 69
		fixPriority 1
		view 860
		loop 2
		scaleSignal $0001
	)
)

(instance jungle of Feature
	(properties
		noun 3
		approachX 211
		approachY 134
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						114
						53
						124
						44
						143
						39
						152
						42
						158
						33
						165
						34
						163
						39
						174
						40
						181
						35
						181
						30
						193
						32
						191
						38
						207
						40
						218
						36
						215
						32
						230
						28
						252
						43
						234
						22
						211
						14
						214
						3
						226
						1
						245
						11
						250
						5
						230
						0
						319
						0
						319
						94
						306
						106
						289
						110
						266
						102
						252
						103
						248
						97
						232
						95
						253
						72
						231
						62
						145
						62
						94
						65
						65
						66
						64
						57
						71
						54
					yourself:
				)
		)
		(super init:)
	)
)

(instance path1 of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 261 101 289 109 287 129 271 140 261 140
					yourself:
				)
		)
		(super init:)
	)
)

(instance path2 of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 295 108 312 101 309 116 295 128
					yourself:
				)
		)
		(super init:)
	)
)

(instance path3 of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 316 115 316 99 319 98 319 112
					yourself:
				)
		)
		(super init:)
	)
)

(instance shore of Feature
	(properties
		noun 8
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						140
						110
						139
						104
						53
						105
						38
						98
						64
						89
						207
						87
						216
						84
						130
						81
						202
						77
						186
						71
						198
						68
						92
						66
						116
						63
						226
						64
						248
						70
						247
						77
						227
						98
						246
						98
						250
						103
						262
						103
						262
						139
						0
						139
						0
						129
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(= local5 ((user curEvent?) x?))
				(= local6 ((user curEvent?) y?))
				(if (== ((inventory at: 23) owner?) 0)
					(curRoom setScript: getLamp)
				else
					(curRoom setScript: digScr)
				)
			)
			((OneOf theVerb 4 1)
				(messager
					say: noun theVerb (if (== ((inventory at: 23) owner?) 0) 8 else 7)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance sky of Feature
	(properties
		noun 9
		approachX 211
		approachY 134
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						234
						0
						249
						4
						248
						8
						241
						9
						225
						2
						214
						4
						211
						9
						211
						11
						217
						17
						232
						19
						231
						27
						212
						31
						217
						36
						195
						37
						185
						26
						170
						39
						161
						30
						152
						41
						146
						38
						115
						44
						113
						52
						70
						54
						64
						57
						0
						57
						0
						0
					yourself:
				)
		)
		(super init:)
	)
)

(instance deepWater of Feature
	(properties
		noun 10
		approachX 78
		approachY 92
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 59 63 59 63 68 0 87
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(35
				(curRoom setScript: wetCloth)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shallowWater of Feature
	(properties
		noun 10
		approachX 78
		approachY 92
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 87 57 69 142 69 136 74 62 82 24 98 60 116 0 125
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(35
				(curRoom setScript: wetCloth)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bird of Actor
	(properties
		noun 11
		x -10
		y 11
		view 867
		loop 1
	)
)

(instance fire of Prop
	(properties
		noun 4
		x 136
		y 124
		view 863
		cel 3
		detailLevel 2
	)
)

(instance getLamp of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(ego setMotion: PolyPath local5 local6 self)
			)
			(1 (ego setHeading: 270 self))
			(2 (= cycles 2))
			(3 (messager say: 8 4 8 1 self))
			(4
				(if (> howFast 3) (= temp0 8) else (= temp0 4))
				(ego
					view: 864
					loop: 0
					cel: 0
					cycleSpeed: temp0
					setCycle: End self
				)
			)
			(5
				(ego setLoop: 1 1 cel: 0 setCycle: End self)
			)
			(6
				(= ticks 30)
				(hole
					init:
					x: (- (ego x?) 25)
					y: (ego y?)
					ignoreActors: 1
					setPri: (- (ego priority?) 1)
				)
			)
			(7 (messager say: 8 4 8 2 self))
			(8 (messager say: 8 4 8 3 self))
			(9
				(ego setLoop: 2 1 cel: 0 get: 23)
				(theGame changeScore: 14 276)
				(= ticks 60)
			)
			(10 (ego setCycle: End self))
			(11 (= ticks 60))
			(12
				(ego setLoop: 0 1 cel: 0)
				(= ticks 30)
			)
			(13 (ego setCycle: End self))
			(14
				(messager say: 8 4 8 4 self)
			)
			(15
				(hole dispose:)
				(ego setLoop: 1 1 cel: 15 setCycle: Beg self)
			)
			(16
				(ego setLoop: 0 1 cel: 3 setCycle: Beg self)
			)
			(17
				(ego cycleSpeed: gEgoCycleSpeed_2 normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance theChampagne of View
	(properties
		noun 2
		sightAngle 10
		approachX 211
		approachY 134
		x 226
		y 127
		view 860
	)
	
	(method (doVerb theVerb)
		(cond 
			(
			(and (OneOf theVerb 5 1 4) (cast contains: shablee)) (messager say: 2 0 1))
			((== theVerb 5) (curRoom setScript: takeChampagneScr))
			(else (super doVerb: theVerb))
		)
	)
)

(instance birdScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 10 20)))
			(1
				(= register (Random 8 40))
				(switch (Random 1 2)
					(1
						(bird
							show:
							setLoop: 1 1
							x: 0
							y: register
							setCycle: Fwd
							setMotion: MoveTo 275 register self
						)
					)
					(2
						(bird
							show:
							setLoop: 5 1
							x: 0
							y: register
							setCycle: Fwd
							setMotion: MoveTo 275 register self
						)
					)
					(3
						(bird
							show:
							setLoop: 2 1
							setCycle: Fwd
							x: 297
							y: register
							setMotion: MoveTo -10 register self
						)
					)
					(4
						(bird
							show:
							setLoop: 6 1
							setCycle: Fwd
							x: 297
							y: register
							setMotion: MoveTo -10 register self
						)
					)
				)
			)
			(2
				(bird hide:)
				(= seconds (Random 10 20))
			)
			(3 (self init:))
		)
	)
)

(instance waveScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 5))
			(1
				(oceanSfx number: 867 loop: 1 play:)
				(if (> howFast 3) (= temp0 15) else (= temp0 10))
				(wave setCel: 0 cycleSpeed: temp0 setCycle: End self)
			)
			(2 (= seconds 3))
			(3 (wave setCycle: Beg self))
			(4
				(wave setCel: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance wave of Prop
	(properties
		y 68
		view 867
	)
)

(instance digScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath local5 local6 self)
			)
			(1 (ego setHeading: 270 self))
			(2 (= cycles 2))
			(3
				(if (> howFast 3) (= temp0 8) else (= temp0 4))
				(ego
					view: 864
					loop: 0
					cel: 0
					cycleSpeed: temp0
					setCycle: End self
				)
			)
			(4
				(ego setLoop: 1 1 cel: 0 setCycle: End self)
			)
			(5 (messager say: 8 4 7 1 self))
			(6 (= ticks 60))
			(7 (messager say: 8 4 7 2 self))
			(8 (ego setCycle: Beg self))
			(9
				(ego setLoop: 0 1 cel: 3 setCycle: Beg self)
			)
			(10
				(UnLoad 128 864)
				(ego cycleSpeed: gEgoCycleSpeed_2 normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance takeChampagneScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoCycleSpeed_2 (ego cycleSpeed?))
				(if (!= (ego heading?) 90)
					(ego setHeading: 90 self)
				else
					(= cycles 2)
				)
			)
			(1 (= cycles 2))
			(2
				(if (> howFast 3) (= temp0 8) else (= temp0 4))
				(ego
					view: 901
					setLoop: 4 1
					cel: 0
					cycleSpeed: temp0
					setCycle: CT 2 1 self
				)
			)
			(3 (messager say: 2 5 0 0 self))
			(4
				(theGame changeScore: 6 275)
				(ego get: 7)
				(theChampagne dispose:)
				((curRoom obstacles?) delete: local9)
				(local9 dispose:)
				(= cycles 2)
			)
			(5 (ego setCycle: Beg self))
			(6
				(ego cycleSpeed: gEgoCycleSpeed_2 normalize: 900 0 1)
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
				(ego setMotion: PolyPath 50 92 self)
			)
			(1 (ego setHeading: 270 self))
			(2 (= cycles 2))
			(3
				(ego view: 901 setLoop: 5 1 cel: 0 setCycle: CT 5 1 self)
			)
			(4 (= ticks 60))
			(5
				(theGame changeScore: 6 250)
				(= global185 1)
				((inventory at: 39) cue:)
				(messager say: 2 35 0 0 self 85)
			)
			(6 (= ticks 60))
			(7 (ego setCycle: End self))
			(8
				(ego normalize: 900 1 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance talkShabScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(if (OneOf local1 6 3)
					(self setScript: makeOutScr self)
				else
					(messager say: 7 2 local1 0 self)
				)
				(if local3
					(= local1
						(switch local1
							(4 5)
							(5 6)
							(6 3)
							(else  local1)
						)
					)
					(= local3 (= local2 0))
				else
					(= local2 1)
				)
			)
			(2
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(self dispose:)
			)
		)
	)
)

(instance larry of Feature
	(properties
		y 136
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						173
						116
						166
						111
						165
						106
						171
						98
						181
						99
						184
						107
						182
						110
						186
						113
						181
						119
						181
						123
						191
						122
						193
						130
						197
						136
						185
						138
						170
						138
						165
						134
					yourself:
				)
		)
		(super init:)
	)
	
	(method (doVerb)
		(messager say: 6 5 2)
		(return 1)
	)
)

(instance shablee of Prop
	(properties
		noun 7
		x 183
		y 135
		view 862
		cel 1
	)
	
	(method (doit &tmp theMusicPrevSignal)
		(= theMusicPrevSignal (theMusic prevSignal?))
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 2) (curRoom setScript: talkShabScr))
			((OneOf theVerb 6 1 4 65) (curRoom setScript: doVerbScr 0 theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance drownScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gEgoCycleSpeed_2 (ego cycleSpeed?))
				(if (> howFast 3) (= temp0 12) else (= temp0 10))
				(ego
					view: 864
					setLoop: 3 1
					cel: 0
					cycleSpeed: temp0
					setCycle: End self
				)
				(theMusic fade:)
			)
			(1
				(ego view: 98)
				(= ticks 180)
			)
			(2
				(UnLoad 128 82)
				(UnLoad 128 900)
				(UnLoad 128 867)
				(UnLoad 128 860)
				(UnLoad 128 971)
				(UnLoad 128 864)
				(wave view: 98)
				(= cycles 1)
			)
			(3
				(EgoDead 12 self)
				(ego
					show:
					cycleSpeed: gEgoCycleSpeed
					normalize: 900 4 1
					x: 68
					y: 99
				)
				(wave view: 867)
			)
			(4
				(theMusic play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance makeOutScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(++ local7)
				(if (== local1 3)
					(messager say: 7 2 local1 1 self)
				else
					(messager say: 7 2 local1 0 self)
				)
			)
			(1
				(if (> howFast 3) (= temp0 8) else (= temp0 6))
				(if (== local7 1)
					(shablee setLoop: 2 1 cel: 0 cycleSpeed: temp0)
					(= ticks 30)
				else
					(= state (+ state 2))
					(self cue:)
				)
			)
			(2
				(shablee cel: 1)
				(= ticks 90)
			)
			(3
				(sfx number: 124 loop: 1 play:)
				(= ticks 20)
			)
			(4
				(if (== local7 1)
					(shablee cel: 0)
					(= ticks 20)
				else
					(shablee setLoop: 3 1 cel: 0 setCycle: End self)
					(++ state)
				)
			)
			(5
				(shablee setLoop: 1 1 cel: 1)
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(self dispose:)
			)
			(6
				(if (and (> local7 1) (== local1 3))
					(if (> howFast 3) (= temp0 15) else (= temp0 10))
					(shablee setLoop: 4 1 cycleSpeed: temp0 setCycle: Fwd)
					(= seconds 8)
				else
					(= ticks 30)
				)
			)
			(7
				(if (> howFast 3) (= temp0 8) else (= temp0 6))
				(shablee
					setLoop: 3 1
					cel: 4
					cycleSpeed: temp0
					setCycle: CT 1 -1 self
				)
			)
			(8
				(sfx number: 124 loop: 1 play:)
				(= ticks 10)
			)
			(9
				(shablee cel: 0)
				(= ticks 30)
			)
			(10
				(shablee setLoop: 1 1 cel: 1)
				(= ticks 60)
			)
			(11
				(if (and (> local7 1) (== local1 3))
					(messager sayRange: 7 2 local1 2 3 self)
				else
					(= cycles 2)
				)
			)
			(12
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(self dispose:)
			)
		)
	)
)

(instance doVerbScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(switch register
					(6
						(cond 
							((!= local1 3) (messager say: 7 6 local1 0 self))
							((and (== local1 3) (> local7 2)) (messager say: 7 6 3 0 self))
							(else (messager say: 7 6 6 0 self))
						)
					)
					(1
						(messager say: 7 1 local1 0 self)
					)
					(4
						(= local3 1)
						(messager say: 7 4 local1 0 self)
						(if local2
							(= local1
								(switch local1
									(4 5)
									(5 6)
									(6 3)
									(else  local1)
								)
							)
							(= local3 (= local2 0))
						else
							(= local3 1)
						)
					)
					(65
						(if (and (> local7 2) (== local1 3))
							(= next applyCondomScr)
							(self dispose:)
						else
							(messager say: 7 65 0 0 self)
						)
					)
				)
			)
			(2
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(self dispose:)
			)
		)
	)
)

(instance applyCondomScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc79_11 326 327 871 870 869)
				(= cycles 2)
			)
			(1
				(theGame changeScore: 13 274)
				(= ticks 30)
			)
			(2
				(messager say: 7 65 3 1 self)
			)
			(3
				(messager say: 7 65 3 2 self)
			)
			(4
				(ego put: 8)
				(if (> howFast 3) (= temp0 8) else (= temp0 4))
				(shablee
					setLoop: 5 1
					cycleSpeed: temp0
					setCel: 0
					setCycle: End self
				)
			)
			(5
				(shablee setLoop: 6 1 setCycle: Fwd)
				(= seconds 6)
			)
			(6
				(shablee setLoop: 7 1 cel: 0 setCycle: 0)
				(= ticks 30)
			)
			(7
				(messager say: 7 65 3 3 self)
			)
			(8
				(theMusic stop:)
				(if (< howFast 4) (wave dispose:))
				(shablee setCycle: CT 3 1 self)
			)
			(9
				(sfx number: 869 loop: 1 play:)
				(shablee setCycle: End self)
			)
			(10
				(UpdateScreenItem
					((ScriptID 92 3) view: 1903 loop: 0 cel: 0)
				)
				(= ticks 180)
			)
			(11
				(shablee setLoop: 0 1 cel: 0)
				(if (> howFast 3) (= temp1 8) else (= temp1 4))
				(ego
					show:
					view: 862
					x: (shablee x?)
					y: (shablee y?)
					setLoop: 8 1
					setCycle: Fwd
					moveSpeed: temp1
					cycleSpeed: temp1
					setStep: 1 1
					setMotion: MoveTo 155 (ego y?) self
				)
			)
			(12
				(ego setCycle: 0 cel: 0 setLoop: 9 1 setCycle: End self)
			)
			(13
				(wave setScript: 0)
				(ego setScript: spitScr self)
			)
			(14 (= local14 1) (= ticks 30))
			(15
				(messager say: 7 65 3 5 self)
			)
			(16
				(ego setScript: 0)
				(shablee
					view: 861
					cel: 0
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(17
				(= next exitScr)
				(self dispose:)
			)
		)
	)
)

(instance spitScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(asm
			lap      newState
			aTop     state
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_1f01
			lsg      howFast
			ldi      3
			gt?     
			bnt      code_1ee0
			ldi      6
			sat      temp0
			jmp      code_1ee4
code_1ee0:
			ldi      4
			sat      temp0
code_1ee4:
			pushi    #setLoop
			pushi    2
			pushi    10
			pushi    1
			pushi    266
			pushi    1
			lst      temp0
			pushi    16
			pushi    1
			pushi    0
			lag      ego
			send     20
			ldi      30
			aTop     ticks
			jmp      code_203a
code_1f01:
			dup     
			ldi      1
			eq?     
			bnt      code_1f20
			pushi    #cel
			pushi    1
			pushi    0
			pushi    236
			pushi    4
			class    CT
			push    
			pushi    1
			pushi    1
			pushSelf
			lag      ego
			send     18
			jmp      code_203a
code_1f20:
			dup     
			ldi      2
			eq?     
			bnt      code_1faa
			pToa     register
			not     
			bnt      code_1f58
			ldi      1
			aTop     register
			pushi    #setCycle
			pushi    1
			class    End
			push    
			lag      ego
			send     6
			pushi    #say
			pushi    4
			pushi    7
			pushi    65
			pushi    3
			pushi    4
			lag      messager
			send     12
			ldi      300
			aTop     ticks
			jmp      code_203a
code_1f58:
			lal      local14
			not     
			bnt      code_1fa3
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_1f74
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_1f74
code_1f74:
			not     
			bnt      code_1fa3
			pushi    1
			pushi    #view
			pushi    1
			pushi    1903
			pushi    2
			pushi    92
			pushi    3
			callk    ScriptID,  4
			send     6
			push    
			callk    UpdateScreenItem,  2
			pushi    #number
			pushi    1
			pushi    871
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
code_1fa3:
			ldi      120
			aTop     ticks
			jmp      code_203a
code_1faa:
			dup     
			ldi      3
			eq?     
			bnt      code_1fc5
			pushi    #cel
			pushi    1
			pushi    1
			pushi    236
			pushi    2
			class    End
			push    
			pushSelf
			lag      ego
			send     14
			jmp      code_203a
code_1fc5:
			dup     
			ldi      4
			eq?     
			bnt      code_203a
			lal      local14
			not     
			bnt      code_201f
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_1fe8
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_1fe8
code_1fe8:
			not     
			bnt      code_201f
			pushi    1
			pushi    #view
			pushi    1
			pushi    1906
			pushi    15
			pushi    1
			pushi    0
			pushi    16
			pushi    1
			pushi    0
			pushi    2
			pushi    92
			pushi    3
			callk    ScriptID,  4
			send     18
			push    
			callk    UpdateScreenItem,  2
			pushi    #number
			pushi    1
			pushi    870
			pushi    15
			pushi    1
			pushi    1
			pushi    51
			pushi    0
			lofsa    sfx
			send     16
code_201f:
			+al      local0
			push    
			ldi      3
			gt?     
			bnt      code_2032
			pushi    #dispose
			pushi    0
			self     4
			jmp      code_203a
code_2032:
			ldi      0
			aTop     state
			ldi      30
			aTop     ticks
code_203a:
			toss    
			ret     
		)
	)
)

(instance shabCartoonScr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= gEgoCycleSpeed_2 gGEgoCycleSpeed_2)
				(PalVary 8 862)
				(shablee init: setLoop: 0 1 ignoreActors: 1)
				(theChampagne init:)
				(fire init: setCycle: Fwd setPri: 50)
				(fireFx number: 868 loop: -1 play:)
				(wave init: setScript: waveScr)
				(if (> howFast 3) (= temp0 4) else (= temp0 6))
				(ego
					normalize: 900 6
					init:
					setScale: 0
					x: 86
					y: 189
					setLoop: 6 1
					setCycle: Fwd
					setSpeed: temp0
				)
				(PalVary 0 862 0 64 1)
				(proc79_8 10)
				(= seconds 3)
			)
			(1
				(ego
					setPri: (- (shablee priority?) 10)
					fixPriority: 1
					setMotion: MoveTo 138 135 self
				)
			)
			(2
				(ego normalize: 900 loop: 8 cel: 6 heading: 45)
				(= cycles 2)
			)
			(3 (ego setHeading: 90 self))
			(4 (= cycles 2))
			(5 (messager say: 7 1 4 0 self))
			(6
				(ego
					setPri: (+ (shablee priority?) 1)
					setMotion: MoveTo 184 135 self
				)
			)
			(7 (= ticks 90))
			(8
				(ego view: 98)
				(larry init:)
				(shablee setLoop: 1 1 cel: 0)
				(= ticks 30)
			)
			(9
				(shablee cel: 1)
				(if local15
					(= local15 0)
					(curRoom notify: self)
				else
					(= ticks 30)
				)
			)
			(10
				(= gLarryRoom curRoom)
				(theIconBar enableIcon: (ScriptID 0 8) show:)
				(keyDownHandler add: curRoom)
				(ego setSpeed: gEgoCycleSpeed_2)
				(= gGButtonBarCurIcon (ScriptID 0 4))
				(theGame handsOn:)
				(theIconBar disableIcon: (ScriptID 0 3) show:)
				(self dispose:)
			)
		)
	)
)

(instance exitScr of Script
	(properties)
	
	(method (dispose)
		(sfx number: 0 dispose:)
		(proc79_12 326 327 871 870 869)
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (theMusic handle?) (theMusic fade:))
				(fireFx stop:)
				(= temp0 (& (= temp0 10) $00ff))
				(SetShowStyle temp0 (curRoom plane?) 1 0 200 0 0)
				(= gLarryRoom 0)
				(theIconBar disableIcon: (ScriptID 0 8) show:)
				(cast eachElementDo: #hide)
				(= ticks 90)
			)
			(1
				(sfx number: 326 loop: 1 play:)
				(= ticks 60)
			)
			(2
				(UpdateScreenItem ((ScriptID 92 3) view: 1903))
				(= ticks 20)
			)
			(3
				(sfx number: 327 loop: 1 play:)
				(= ticks 180)
			)
			(4
				(keyDownHandler delete: curRoom)
				(theGame hideControls:)
				(= ticks 90)
			)
			(5
				(Print font: userFont addText: 7 65 3 6 modeless: 2 init:)
				(= seconds 5)
			)
			(6
				(if (Print dialog?) ((Print dialog?) dispose:))
				(Print modeless: 0)
				(ego edgeHit: 0)
				(PalVary 3)
				(Palette 1 999 2)
				(curRoom newRoom: 630)
			)
		)
	)
)
