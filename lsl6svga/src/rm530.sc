;;; Sierra Script 1.0 - (do not remove this comment)
(script# 530)
(include sci.sh)
(use Main)
(use LarryRoom)
(use PolyFeature)
(use Scaler)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm530 0
)

(local
	local0
	local1
	local2
)
(instance rm530 of LarryRoom
	(properties
		noun 1
		picture 530
		horizon 0
		east 500
		south 500
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						71
						129
						166
						130
						273
						130
						280
						139
						319
						139
						319
						109
						256
						88
						263
						74
						247
						71
						237
						81
						218
						86
						144
						81
						54
						90
						6
						103
						23
						116
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 103 89 150 85 182 89 182 105 74 105 74 94
					yourself:
				)
		)
		(super init: &rest)
		(ego normalize: 900 8 init:)
		(if (or (== prevRoomNum 560) (!= prevRoomNum 500))
			(theMusic2 number: 200 loop: -1 play: 0 fade: 75 25 10 0)
		)
		(if (== prevRoomNum 500)
			(curRoom setScript: enterFromLobbyScr)
		else
			(leftDoor setScript: fromKitchenScr)
		)
		(doorFtr init:)
		(weenie init:)
		(burger init:)
		(screen init:)
		(pie init:)
		(umbrella init:)
		(saladBar init:)
		(flowers init:)
		(napkins init:)
		(rightDoor init:)
		(leftDoor init:)
		(cook1 init:)
		(cook2 init:)
		(cook3 init:)
		(if
		(== ((= local0 (inventory at: 27)) owner?) 5301)
			(theOrange init:)
		)
	)
	
	(method (newRoom n)
		(if (== n 560)
			(theMusic2 fade:)
		else
			(theMusic2 fade: 127 25 10 0)
		)
		(super newRoom: n)
	)
	
	(method (edgeToRoom param1)
		(if (OneOf param1 2 3)
			(curRoom setScript: toHallScr 0 param1)
		)
		(return 0)
	)
)

(instance enterFromLobbyScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 315 160 setMotion: MoveTo 305 136 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toHallScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 2)
					(ego
						setMotion: MoveTo (+ (ego x?) 10) (+ (ego y?) 10) self
					)
				else
					(ego
						setMotion: MoveTo (+ (ego x?) 20) (+ (ego y?) 50) self
					)
				)
			)
			(1 (curRoom newRoom: 500))
		)
	)
)

(instance getOrangeScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 531
					loop: 3
					cel: 0
					posn: 124 103
					setSpeed: 10
					setCycle: 0
				)
				(theOrange dispose:)
				(= cycles 2)
			)
			(1
				(theGame changeScore: 8 218)
				(ego setCycle: End self)
			)
			(2
				(ego get: 27 normalize: 900 8 cel: 3 x: (+ (ego x?) 6))
				(= cycles 2)
			)
			(3
				(narrator y: 10)
				(messager say: 6 5 1 0 self)
			)
			(4
				(narrator y: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance chillClothScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 531
					loop: 0
					cel: 0
					posn: 124 103
					setSpeed: 10
					setCycle: End self
				)
			)
			(1
				(ego loop: 1 cel: 0 setCycle: End self)
				(= ticks 10)
			)
			(2 (iceFX play:))
			(3
				(messager say: 6 36 0 1 self)
			)
			(4 (= ticks 200))
			(5
				(messager say: 6 36 0 2 self)
			)
			(6
				((ScriptID 85 4)
					setReal: ((inventory at: 39) noun: 52 message: 37 yourself:) 0 15
				)
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(7
				(ego normalize: 900 8 cel: 3)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance saladBar of Feature
	(properties
		noun 6
		sightAngle 360
		approachX 124
		approachY 103
		x 127
		y 96
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						77
						77
						96
						68
						99
						59
						110
						59
						114
						67
						139
						67
						142
						57
						154
						60
						155
						68
						174
						73
						176
						100
						124
						103
						81
						100
					yourself:
				)
			approachVerbs: 4 5 36
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (OneOf (local0 owner?) 530 5301)
					(curRoom setScript: searchIceScr)
				else
					(messager say: noun theVerb 3)
				)
			)
			(5
				(cond 
					((== (local0 owner?) 530) (messager say: noun theVerb 2))
					((== (local0 owner?) 5301) (ego setScript: getOrangeScr))
					(else (messager say: noun theVerb 3))
				)
			)
			(1
				(cond 
					((== (local0 owner?) 530) (messager say: noun theVerb 2))
					((== (local0 owner?) 5301) (messager say: noun theVerb 1))
					(else (messager sayRange: noun theVerb 3 1 2))
				)
			)
			(36
				(curRoom setScript: chillClothScr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance umbrella of PolyFeature
	(properties
		noun 7
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init: 110 27 127 37 125 44 105 54 103 61 99 55 84 58 76 54 80 35 92 26
					yourself:
				)
				((Polygon new:)
					type: 0
					init:
						147
						55
						131
						48
						127
						43
						140
						31
						156
						28
						169
						35
						174
						42
						176
						56
						151
						56
						149
						61
					yourself:
				)
		)
	)
)

(instance doorFtr of Feature
	(properties
		approachX 257
		approachY 78
	)
	
	(method (init)
		(super init: &rest)
		(= sightAngle 26505)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 283 15 274 74 246 71 239 22
					yourself:
				)
			approachVerbs: 4
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom newRoom: 560)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance weenie of Feature
	(properties
		noun 3
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 88 27 81 11 82 5 81 0 89 0 101 1 145 18 145 23 137 30 125 35 110 29
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance pie of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 167 26 178 13 178 4 214 14 204 31 180 38 171 37 167 27
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance burger of Feature
	(properties
		noun 4
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 319 50 303 48 297 27 305 10 319 8 319 51
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance screen of Feature
	(properties
		noun 2
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 28 0 69 11 71 45 13 52 0 18 0 0
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance flowers of PolyFeature
	(properties
		noun 11
		variableX 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					init: 28 106 50 95 67 96 69 109 53 128 53 135 39 135 39 128
					yourself:
				)
				((Polygon new:)
					init:
						222
						107
						225
						97
						241
						94
						259
						107
						249
						123
						250
						133
						236
						135
						235
						127
						236
						123
					yourself:
				)
				((Polygon new:)
					init: 48 35 53 39 52 56 48 57 45 54 43 39
					yourself:
				)
				((Polygon new:)
					init: 217 29 228 37 219 44 219 53 215 53 209 44 208 36
					yourself:
				)
		)
	)
)

(instance napkins of PolyFeature
	(properties
		noun 10
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:) init: 35 47 42 47 43 57 34 57 yourself:)
				((Polygon new:)
					init: 230 44 229 53 224 53 221 53 221 44
					yourself:
				)
				((Polygon new:)
					init: 227 121 229 123 229 135 221 135 216 133 216 121
					yourself:
				)
		)
	)
)

(instance cook1 of Actor
	(properties
		noun 2
		x 37
		y 39
		view 530
		cel 10
		cycleSpeed 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			setLoop: 3 1
			hide:
			ignoreActors: 1
			setScript: cook1Scr
		)
	)
)

(instance cook3 of Actor
	(properties
		noun 2
		x 21
		y 30
		view 533
	)
	
	(method (init)
		(super init: &rest)
		(self
			setLoop: 2 1
			hide:
			ignoreActors: 1
			setScript: cook2Scr
		)
	)
)

(instance cook2 of Actor
	(properties
		noun 2
		x 53
		y 40
		view 534
		cel 10
	)
	
	(method (init)
		(super init: &rest)
		(self
			setLoop: 5 1
			hide:
			ignoreActors:
			setScript: cook3Scr
		)
	)
)

(instance cook1Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (* (Random 10 30) 60))
			)
			(1
				(if (= register (Random 0 1))
					(client posn: 3 44 setLoop: 0 1)
				else
					(client posn: 70 41 setLoop: 1 1)
				)
				(client show: setCycle: Walk setMotion: MoveTo 39 40 self)
				(= register (Random 2 10))
			)
			(2
				(client
					setLoop: (if (not (Random 0 3)) 2 else 3) 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(if (-- register) (= state (- state 2)))
				(= cycles 2)
			)
			(4
				(client setCycle: Walk)
				(if register
					(client setLoop: 0 1 setMotion: MoveTo 70 40 self)
				else
					(client setLoop: 1 1 setMotion: MoveTo 3 43 self)
				)
			)
			(5
				(client hide:)
				(= state -1)
				(= cycles 2)
			)
		)
	)
)

(instance cook2Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (* (Random 10 30) 60))
			)
			(1
				(if (Random 0 1)
					(client posn: 3 29 setLoop: 0 1)
				else
					(client posn: 72 26 setLoop: 1 1)
				)
				(client show: setCycle: Walk setMotion: MoveTo 23 28 self)
				(= register (Random 2 10))
			)
			(2
				(client
					setLoop: (Random 2 3) 1
					cel: 0
					setCycle: End self
				)
			)
			(3
				(if (-- register) (= state (- state 2)))
				(= cycles 2)
			)
			(4
				(client setCycle: Walk)
				(if (Random 0 1)
					(client setLoop: 0 1 setMotion: MoveTo 72 26 self)
				else
					(client setLoop: 1 1 setMotion: MoveTo 3 29 self)
				)
			)
			(5
				(client hide:)
				(= cycles 2)
				(= state -1)
			)
		)
	)
)

(instance cook3Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks (* (Random 10 30) 60))
			)
			(1
				(if (= register (Random 0 1))
					(client posn: 4 43 setLoop: 0 1)
				else
					(client posn: 72 40 setLoop: 1 1)
				)
				(client show: setCycle: Walk setMotion: MoveTo 55 40 self)
				(= register (Random 2 8))
			)
			(2
				(client setLoop: 2 1 cel: 0 setCycle: End self)
			)
			(3
				(if (-- register) (= state (- state 2)))
				(= cycles 2)
			)
			(4
				(client setCycle: Walk)
				(if register
					(client setLoop: 0 1 setMotion: MoveTo 72 40 self)
				else
					(client setLoop: 1 1 setMotion: MoveTo 4 43 self)
				)
			)
			(5
				(client hide:)
				(= state -1)
				(= cycles 2)
			)
		)
	)
)

(instance searchIceScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 531
					loop: 0
					cel: 0
					posn: 124 103
					setSpeed: 10
					setCycle: End self
				)
			)
			(1
				(ego loop: 1 cel: 0 setCycle: End self)
				(= ticks 10)
			)
			(2 (iceFX play:))
			(3 (= ticks 20))
			(4
				(ego loop: 1 cel: 0 setCycle: End self)
				(= ticks 10)
			)
			(5 (iceFX play:))
			(6 (= ticks 20))
			(7
				(ego cel: 0 setCycle: End self)
				(= ticks 10)
			)
			(8 (iceFX play:))
			(9
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(10
				(theOrange init:)
				(= cycles 2)
			)
			(11
				(ego normalize: 900 8 cel: 3)
				(= cycles 2)
			)
			(12 (= cycles 2))
			(13
				(narrator y: 10 x: 173 talkWidth: 115)
				(messager
					say:
						6
						4
						(if (== (local0 owner?) 530)
							(local0 owner: 5301)
							2
						else
							1
						)
						0
						self
				)
			)
			(14
				(narrator y: -1 x: -1 talkWidth: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toKitchenScr of Script
	(properties)
	
	(method (doit)
		(if
		(and (not (doorFX handle?)) (== (rightDoor cel?) 5))
			(doorFX number: 531 loop: 1 play:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(1 (= ticks 20))
			(2
				(= local1 1)
				(rightDoor setCycle: End self)
				(leftDoor setCycle: End self)
			)
			(3 0)
			(4
				(ego
					setScaler: Scaler 100 76 78 61
					setMotion: MoveTo 267 67 self
				)
			)
			(5
				(rightDoor setCycle: Beg self)
				(leftDoor setCycle: Beg self)
			)
			(6 0)
			(7
				(rightDoor setCycle: CT 8 1 self)
				(leftDoor setCycle: CT 8 1 self)
			)
			(8 0)
			(9
				(rightDoor setCycle: CT 2 -1 self)
				(leftDoor setCycle: CT 2 -1 self)
			)
			(10 0)
			(11 (curRoom newRoom: 560))
		)
	)
)

(instance fromKitchenScr of Script
	(properties)
	
	(method (doit)
		(if
			(and
				local1
				(== (leftDoor cel?) 5)
				(== (rightDoor cel?) 5)
			)
			(if (not local2)
				(theMusic number: 531 loop: 1 play:)
				(= local2 1)
			)
		else
			(= local2 0)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setScaler: Scaler 100 76 78 61 posn: 267 67 cel: 5)
				(= cycles 2)
			)
			(1
				(= local1 1)
				(rightDoor setCycle: Beg self)
				(leftDoor setCycle: Beg self)
			)
			(2 0)
			(3
				(ego setMotion: MoveTo 258 77 self)
			)
			(4
				(ego normalize:)
				(theGame handsOn:)
				(rightDoor setCycle: End self)
				(leftDoor setCycle: End self)
			)
			(5 0)
			(6
				(rightDoor setCycle: CT 2 -1 self)
				(leftDoor setCycle: CT 2 -1 self)
			)
			(7 0)
			(8
				(rightDoor setCycle: CT 7 1 self)
				(leftDoor setCycle: CT 7 1 self)
			)
			(9 0)
			(10
				(rightDoor setCycle: CT 4 -1 self)
				(leftDoor setCycle: CT 4 -1 self)
			)
			(11 0)
			(12
				(rightDoor setCycle: CT 5 1 self)
				(leftDoor setCycle: CT 5 1 self)
			)
			(13 0)
			(14
				(= local1 0)
				(self dispose:)
			)
		)
	)
)

(instance theOrange of View
	(properties
		x 146
		y 74
		priority 100
		fixPriority 1
		view 531
		loop 4
	)
)

(instance rightDoor of Prop
	(properties
		noun 8
		sightAngle 40
		approachX 258
		approachY 77
		x 261
		y 71
		view 532
		cel 5
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4 ignoreActors: 1)
	)
	
	(method (doVerb theVerb)
		(leftDoor doVerb: theVerb)
	)
)

(instance leftDoor of Prop
	(properties
		noun 8
		sightAngle 40
		approachX 258
		approachY 77
		x 260
		y 70
		view 532
		loop 1
		cel 5
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 4 ignoreActors: 1)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(self setScript: toKitchenScr)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance iceFX of Sound
	(properties
		flags $0001
		number 532
	)
)

(instance doorFX of Sound
	(properties
		flags $0004
		number 531
	)
)
