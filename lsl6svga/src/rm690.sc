;;; Sierra Script 1.0 - (do not remove this comment)
(script# 690)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use PolyFeature)
(use Print)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use System)

(public
	rm690 0
	rideTram 1
)

(local
	local0
)
(instance rm690 of LarryRoom
	(properties
		noun 1
		picture 690
		east 820
		west 660
	)
	
	(method (init)
		(if global100 (= local0 2) else (= local0 4))
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						138
						312
						138
						304
						116
						320
						116
						319
						108
						277
						108
						264
						100
						161
						101
						159
						95
						136
						95
						136
						100
						55
						100
						52
						95
						37
						95
						37
						101
						0
						101
					yourself:
				)
		)
		(super init: &rest)
		(if (!= prevRoomNum 660)
			(theMusic2 number: 200 loop: -1 play:)
		)
		(if (not (Btst 35))
			(ego init: ignoreActors: 0 normalize:)
		)
		(switch prevRoomNum
			(660
				(ego x: 10 loop: 8 cel: 0)
				(theGame handsOn:)
			)
			(else 
				(if (not (Btst 35)) (self setScript: fromWoodsScr))
			)
		)
		(leftDoorFtr init:)
		(rightDoorFtr init:)
		(panes init:)
		(carpet init:)
		(windowFrame init:)
		(sculpture1 init:)
		(sculpture2 init:)
		(if (Btst 35)
			(if (Btst 36)
				((ScriptID 825 1) z: 0 view: 90 posn: 0 126 init:)
			else
				((ScriptID 825 1) z: 0 view: 90 posn: 319 126 init:)
			)
			((ScriptID 825 2) play: setLoop: -1)
			(self setScript: rideTram)
		)
	)
	
	(method (newRoom n)
		(if (!= n 660) (theMusic2 fade:))
		(super newRoom: n)
	)
	
	(method (edgeToRoom param1)
		(switch param1
			(4
				(self setScript: toEastHallScr)
			)
			(2 (return east))
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
					(curRoom newRoom: 820)
				else
					(curRoom newRoom: 660)
				)
			)
		)
	)
)

(instance toEastHallScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo -15 (ego y?) self)
			)
			(1 (curRoom newRoom: 660))
		)
	)
)

(instance fromWoodsScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 320 110 setMotion: MoveTo 290 110 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance leftDoorFtr of Feature
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 45
		approachY 98
		x 45
		y 96
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:) init: 26 47 62 46 64 94 30 94 yourself:)
			approachVerbs: 4
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

(instance rightDoorFtr of Feature
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 148
		approachY 98
		x 148
		y 96
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 130 46 166 46 170 94 125 94
				yourself:
			)
			approachVerbs: 4
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: (ScriptID 96 0))
			)
			(1 (messager say: 11 theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance panes of PolyFeature
	(properties
		noun 10
		sightAngle 40
		y 92
		variableX 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			addPolygon:
				((Polygon new:)
					init: 219 0 221 67 202 67 200 0
					yourself:
				)
				((Polygon new:)
					init: 241 0 273 0 271 77 254 70 253 29
					yourself:
				)
				((Polygon new:)
					init: 319 0 319 49 279 40 280 1
					yourself:
				)
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
						98
						25
						99
						29
						94
						63
						94
						68
						97
						120
						97
						125
						94
						169
						95
						177
						99
						223
						99
						230
						126
						0
						135
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
						0
						135
						231
						126
						223
						95
						220
						58
						220
						0
						240
						0
						250
						25
						255
						71
						257
						96
						254
						125
						296
						125
						319
						128
						319
						139
						0
						140
					yourself:
				)
		)
	)
)

(instance sculpture1 of Feature
	(properties
		noun 4
		modNum 205
		sightAngle 40
		x 1
		y 97
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 0 18 15 18 12 73 0 73
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 4)
			(messager say: 9 theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance sculpture2 of Feature
	(properties
		noun 4
		modNum 205
		sightAngle 40
		x 96
		y 96
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 78 17 120 17 111 68 79 66
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			((and (not (-- local0)) (== theVerb 4))
				(Bclr 160)
				(Bset 205)
				(Bset 64)
				(ego get: 25 41)
				(while (== (= temp0 (Random 1 61)) 9)
				)
				(DoAudio 2 611 1 4 temp0 1)
				(Print
					width: 160
					font: userFont
					addTitle: {Carlos, are you cheating again?}
					addText: 1 4 temp0 1 50 1 611
					fore: 67
					addText: {Char wants batteries, you have matches for Art.} 50 18
					fore: 0
					addIcon: 1592 1 0 0 0
					init:
				)
			)
			((OneOf theVerb 1 4) (messager say: 8 theVerb))
			(else (super doVerb: theVerb))
		)
	)
)
