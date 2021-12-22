;;; Sierra Script 1.0 - (do not remove this comment)
(script# 660)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use PolyFeature)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm660 0
	rideTram 1
)

(instance rm660 of LarryRoom
	(properties
		noun 1
		picture 660
		horizon 0
		east 690
		west 505
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						138
						319
						138
						319
						101
						271
						101
						271
						96
						245
						95
						245
						101
						172
						102
						172
						97
						140
						95
						140
						101
						131
						107
						97
						107
						97
						101
						60
						101
						58
						97
						27
						96
						24
						101
						0
						101
					yourself:
				)
		)
		(super init: &rest)
		(if (not (Btst 35))
			(ego normalize: ignoreActors: 0 init:)
		)
		(if (not (OneOf prevRoomNum 690 505))
			(theMusic2 number: 200 loop: -1 play:)
		)
		(cond 
			((Btst 35)
				(if (Btst 36)
					((ScriptID 825 1) z: 0 view: 90 posn: 0 126 init:)
				else
					((ScriptID 825 1) z: 0 view: 90 posn: 319 126 init:)
				)
				((ScriptID 825 2) play: setLoop: -1)
				(self setScript: rideTram)
			)
			((== prevRoomNum 690) (ego x: 310 loop: 8 cel: 1) (theGame handsOn:))
			(else (ego x: 10 loop: 8 cel: 0) (theGame handsOn:))
		)
		(leftDoor init: approachVerbs: 4)
		(rightDoor init: approachVerbs: 4)
		(middleDoor init: approachVerbs: 4)
		(carpet init:)
		(frontSculpture init:)
		(hiddenSculpture init:)
		(rearSculpture init:)
		(windowFrame init:)
		(if
			(or
				(and (Btst 186) (== ((inventory at: 26) owner?) 660))
				(== global100 660)
			)
			(tray init: approachVerbs: 5 4)
		)
	)
	
	(method (newRoom n)
		(if (not (OneOf n 505 690)) (theMusic2 fade:))
		(super newRoom: n)
	)
	
	(method (edgeToRoom param1)
		(switch param1
			(2 (self setScript: toExitScr))
			(4
				(self setScript: to3DoorHallScr)
			)
		)
		(return 0)
	)
)

(instance rideTram of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar
					disableIcon:
						(ScriptID 0 3)
						(ScriptID 0 5)
						(ScriptID 0 6)
						(ScriptID 0 9)
					enableIcon: (ScriptID 0 4) (ScriptID 0 7)
					show:
				)
				(if
				(OneOf gGButtonBarCurIcon (ScriptID 0 4) (ScriptID 0 7))
					(theIconBar curIcon: gGButtonBarCurIcon)
				else
					(theIconBar curIcon: (ScriptID 0 7))
				)
				(theGame setCursor: ((theIconBar curIcon?) getCursor:))
				(User canControl: 1 canInput: 1)
				(cond 
					(
					(and (Btst 36) (> ((ScriptID 825 1) x?) 319)) (self cue:))
					((Btst 36)
						((ScriptID 825 1)
							setCycle: Walk
							setMotion: MoveTo 319 126 self
						)
					)
					((< ((ScriptID 825 1) x?) 0) (self cue:))
					(else
						((ScriptID 825 1)
							setCycle: Walk
							setMotion: MoveTo 0 126 self
						)
					)
				)
			)
			(1
				(DisposeScript 826)
				(if (Btst 36)
					(curRoom newRoom: 690)
				else
					(curRoom newRoom: 505)
				)
			)
		)
	)
)

(instance frontSculpture of Feature
	(properties
		noun 4
		sightAngle 40
		x 101
		y 97
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 124 15 122 71 82 72 79 16
				yourself:
			)
		)
	)
)

(instance hiddenSculpture of Feature
	(properties
		noun 7
		sightAngle 40
		x 209
		y 97
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 191 19 231 19 227 74 191 74
				yourself:
			)
		)
	)
)

(instance rearSculpture of Feature
	(properties
		noun 3
		sightAngle 40
		x 311
		y 98
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 319 18 319 73 293 73 291 18
				yourself:
			)
		)
	)
)

(instance toExitScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 330 (ego y?) self)
			)
			(1 (curRoom newRoom: 690))
		)
	)
)

(instance to3DoorHallScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo -15 (ego y?) self)
			)
			(1 (curRoom newRoom: 505))
		)
	)
)

(instance tray of View
	(properties
		noun 5
		sightAngle 40
		approachX 90
		approachY 105
		x 98
		y 96
		view 660
		loop 1
		signal $4000
	)
	
	(method (init)
		(if (!= ((inventory at: 26) owner?) curRoomNum)
			(= cel 1)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(= temp0 (== ((inventory at: 26) owner?) 660))
		(cond 
			((and (== theVerb 5) temp0) (curRoom setScript: takeWaterScr))
			((== theVerb 5) (messager say: noun theVerb 1))
			((== theVerb 1) (messager say: noun theVerb (if (not temp0) 1 else 0)))
			(else (super doVerb: theVerb))
		)
	)
)

(instance leftDoor of Feature
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 42
		approachY 98
		x 42
		y 94
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:) init: 65 44 69 99 17 99 21 43 yourself:)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4) (curRoom setScript: (ScriptID 96 0)))
			((OneOf theVerb 1 5 2) (messager say: 2 theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance middleDoor of View
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 156
		approachY 98
		x 155
		y 94
		view 660
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: (ScriptID 96 0))
			)
			(1 (messager say: 8 theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rightDoor of Feature
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 257
		approachY 98
		x 257
		y 94
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 283 42 278 97 238 99 236 41
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: (ScriptID 96 0))
			)
			(1 (messager say: 6 theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance carpet of PolyFeature
	(properties
		noun 1
		modNum 205
		sightAngle 40
		variableX 1
		variableY 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					init:
						0
						99
						16
						99
						23
						95
						63
						96
						71
						100
						95
						100
						98
						107
						128
						107
						133
						96
						137
						93
						173
						95
						177
						99
						191
						98
						187
						121
						150
						120
						63
						130
						43
						130
						0
						125
					yourself:
				)
				((Polygon new:)
					init: 269 99 277 105 292 105 316 120 316 128 291 125 254 125 257 99
					yourself:
				)
		)
	)
)

(instance windowFrame of PolyFeature
	(properties
		noun 5
		modNum 205
		sightAngle 40
		y 200
		variableX 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init:
						204
						0
						224
						0
						208
						99
						204
						121
						248
						125
						319
						135
						319
						138
						0
						139
						0
						126
						48
						131
						68
						131
						87
						128
						155
						121
						187
						120
						200
						55
					yourself:
				)
		)
	)
)

(instance takeWaterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 901
					setLoop: 4 1
					cel: 0
					setSpeed: 7
					setCycle: CT 3 1 self
				)
			)
			(1 (tray cel: 1) (= cycles 2))
			(2
				(narrator x: 170 y: 7 talkWidth: 110)
				(messager say: 5 5 0 0 self)
			)
			(3
				(narrator x: -1 y: -1 talkWidth: 0)
				(ego get: 26)
				(ego setCycle: End self)
			)
			(4
				(ego normalize: 900 8 1 cel: 0)
				(theGame changeScore: 6 255)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
