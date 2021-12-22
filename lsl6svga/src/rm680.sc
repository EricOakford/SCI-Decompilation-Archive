;;; Sierra Script 1.0 - (do not remove this comment)
(script# 680)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use PolyFeature)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use System)

(public
	rm680 0
	rideTram 1
)

(instance rm680 of LarryRoom
	(properties
		modNum 690
		noun 1
		picture 690
		style $0400
		horizon 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						320
						101
						283
						101
						283
						95
						268
						95
						265
						100
						184
						100
						184
						95
						161
						95
						159
						101
						56
						100
						43
						106
						0
						106
						0
						116
						16
						116
						8
						138
						320
						138
					yourself:
				)
		)
		(if (not (Btst 35)) (ego normalize: init:))
		(switch prevRoomNum
			(810
				(self style: 1024)
				(if (not (Btst 35)) (self setScript: enterFromWestScr))
			)
		)
		(if (Btst 35)
			(if (not (Btst 36))
				((ScriptID 825 1) view: 90 z: 0 posn: 319 127 init:)
			)
			((ScriptID 825 2) play: setLoop: -1)
			((ScriptID 825 1) setScript: rideTram)
		else
			(theGame handsOn:)
		)
		(if (not (theMusic2 handle?))
			(theMusic2 number: 200 play: setLoop: -1)
		)
		(super init: &rest)
		(switch prevRoomNum
			(810 (ego posn: -10 113))
			(640
				(ego loop: 8 x: 310 cel: 1)
			)
			(else  (ego posn: 310 118))
		)
		(leftDoorFtr init:)
		(rightDoorFtr init:)
		(panes init:)
		(carpet init:)
		(windowFrame init:)
		(sculpture1 init:)
		(sculpture2 init:)
		(doorway init:)
	)
	
	(method (doit)
		(cond 
			(script)
			((== (ego edgeHit?) 2) (self setScript: exitEastScr))
			((== (ego edgeHit?) 4) (self setScript: exitWestScr))
		)
		(super doit: &rest)
	)
	
	(method (newRoom n)
		(if (!= n 640) (theMusic2 fade:))
		(super newRoom: n &rest)
	)
)

(instance rideTram of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
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
				(DisposeScript 826)
				(cond 
					(
					(and (Btst 36) (> ((ScriptID 825 1) x?) 319)) (self cue:))
					((Btst 36)
						((ScriptID 825 1)
							setCycle: Walk
							setMotion: MoveTo 319 127 self
						)
					)
					(else
						((ScriptID 825 1)
							setCycle: Walk
							setMotion: MoveTo 90 127 self
						)
					)
				)
			)
			(1
				(if (Btst 36)
					(curRoom newRoom: 640)
				else
					(Bset 70)
					(self setScript: (ScriptID 826 1) self)
				)
			)
			(2
				(Bset 36)
				(self setScript: (ScriptID 826 2) self)
			)
			(3 (= cycles 2))
			(4
				(DisposeScript 826)
				(self dispose:)
			)
		)
	)
)

(instance enterFromWestScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(ego setMotion: MoveTo 33 113 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance enterFromEastScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 30))
			(1
				(ego setMotion: MoveTo 310 118 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
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
				(ego setMotion: MoveTo 340 (ego y?) self)
			)
			(1 (curRoom newRoom: 640))
		)
	)
)

(instance exitWestScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo -6 113 self)
				(theMusic2 fade:)
			)
			(1 (curRoom newRoom: 810))
		)
	)
)

(instance leftDoorFtr of Feature
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 169
		approachY 98
		x 169
		y 96
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 190 46 154 46 150 94 195 94
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
		approachX 272
		approachY 96
		x 272
		y 93
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 294 47 258 46 256 94 290 94
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
				((Polygon new:) init: 101 0 99 67 118 67 120 0 yourself:)
				((Polygon new:)
					init: 79 0 47 0 49 77 66 70 67 29
					yourself:
				)
				((Polygon new:) init: 1 0 1 49 41 40 40 1 yourself:)
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
					type: 3
					init:
						320
						98
						295
						99
						291
						94
						257
						94
						252
						97
						200
						97
						195
						94
						151
						95
						143
						99
						97
						99
						90
						126
						320
						135
					yourself:
				)
				((Polygon new:)
					type: 3
					init: 51 99 43 105 28 105 4 120 4 128 29 125 66 125 63 99
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
					init:
						320
						140
						1
						139
						1
						128
						24
						125
						66
						125
						63
						96
						65
						71
						70
						25
						80
						0
						100
						0
						100
						58
						97
						95
						89
						126
						320
						135
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
		x 224
		y 96
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 241 66 209 68 200 17 242 17
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 4)
			(messager say: 8 theVerb)
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
		x 318
		y 97
	)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon: ((Polygon new:)
				init: 320 73 308 73 305 18 320 18
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

(instance doorway of Feature
	(properties
		noun 5
	)
	
	(method (init)
		(self
			setPolygon: ((Polygon new:) init: 42 105 4 135 6 65 36 52 yourself:)
		)
		(super init: &rest)
	)
)
