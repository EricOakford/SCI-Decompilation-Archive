;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
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
	rm500 0
	rideTram 1
)

(instance rm500 of LarryRoom
	(properties
		noun 1
		picture 500
		horizon 0
		east 505
		west 200
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						319
						138
						319
						94
						303
						102
						299
						111
						307
						111
						307
						118
						275
						118
						215
						114
						215
						108
						197
						108
						197
						110
						0
						110
						0
						120
						52
						120
						57
						138
					yourself:
				)
		)
		(super init: &rest)
		(if (not (Btst 35))
			(ego normalize: 900 8 ignoreActors: 0 init:)
			(switch prevRoomNum
				(505
					(ego x: 313 cel: 1)
					(theGame handsOn:)
				)
				(200
					(self setScript: fromLobbyScr)
				)
				(else 
					(self setScript: fromDiningRoomScr)
				)
			)
		)
		(if (not (OneOf prevRoomNum 505 200 530))
			(theMusic2 number: 200 loop: -1 play:)
		)
		(door init: approachVerbs: 4)
		(visibleSculpture init:)
		(hiddenSculpture init:)
		(flowers init:)
		(windowFrame init:)
		(carpet init:)
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
		(cond 
			((not (OneOf n 505 200 530)) (theMusic2 fade:))
			((== n 530) (theMusic2 fade: 75 25 10 0))
		)
		(super newRoom: n)
	)
	
	(method (edgeToRoom param1)
		(switch param1
			(2
				(self setScript: to3DoorHallScr)
			)
			(4 (self setScript: toLobbyScr))
		)
		(return 0)
	)
)

(instance fromDiningRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(door cel: (door lastCel:))
				(ego posn: 208 103 cel: 5)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 203 110 self)
			)
			(2
				(ego loop: 8 cel: 2)
				(= cycles 2)
			)
			(3 (door setCycle: Beg self))
			(4
				(theMusic number: 33 loop: 1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
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
					(curRoom newRoom: 505)
				else
					(curRoom newRoom: 200)
				)
			)
		)
	)
)

(instance fromLobbyScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 24 119 setMotion: MoveTo 55 119 self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance to3DoorHallScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 330 (ego y?) self)
			)
			(1 (curRoom newRoom: 505))
		)
	)
)

(instance toLobbyScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo -10 (ego y?) self)
			)
			(1 (curRoom newRoom: 200))
		)
	)
)

(instance toDiningRoomScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 20)
			)
			(1
				(ego
					view: 901
					loop: 6
					cel: 0
					setSpeed: 6
					setCycle: End self
				)
			)
			(2 (= cycles 2))
			(3
				(theMusic number: 32 loop: 1 play:)
				(ego setCycle: Beg self)
				(door setCycle: End self)
			)
			(4 0)
			(5
				(ego
					normalize: 900 8 1
					cel: 3
					setMotion: MoveTo 208 103 self
				)
			)
			(6 (= cycles 2))
			(7 (curRoom newRoom: 530))
		)
	)
)

(instance flowers of PolyFeature
	(properties
		noun 3
		modNum 205
		sightAngle 40
		variableX 1
		variableY 1
	)
	
	(method (init)
		(super init: &rest)
		(self addPolygon: flowerPoly1 flowerPoly2)
	)
)

(instance hiddenSculpture of Feature
	(properties
		noun 4
		modNum 205
		sightAngle 40
		x 128
		y 106
	)
	
	(method (init)
		(super init: &rest)
		(self setPolygon: (picturePoly1 init: yourself:))
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 3 theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance visibleSculpture of Feature
	(properties
		noun 4
		modNum 205
		sightAngle 40
		x 43
		y 106
	)
	
	(method (init)
		(super init: &rest)
		(self setPolygon: (picturePoly2 init: yourself:))
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager say: 2 theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance door of Prop
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 200
		approachY 108
		x 201
		y 101
		view 500
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 219 60 221 107 187 107 189 60
					yourself:
				)
			ignoreActors: 1
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4) (curRoom setScript: toDiningRoomScr))
			((OneOf theVerb 1 2) (messager say: 6 theVerb))
			(else (super doVerb: theVerb))
		)
	)
)

(instance windowFrame of PolyFeature
	(properties
		noun 5
		modNum 205
		sightAngle 40
		y 205
		variableX 1
	)
	
	(method (init)
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init:
						142
						0
						161
						0
						140
						34
						133
						54
						122
						94
						120
						118
						175
						115
						255
						114
						319
						120
						319
						130
						251
						124
						198
						124
						119
						131
						119
						139
						104
						139
						105
						133
						78
						139
						5
						139
						56
						127
						105
						121
						111
						82
						121
						41
					yourself:
				)
		)
		(super init: &rest)
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
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init:
						187
						109
						187
						107
						224
						106
						224
						112
						260
						112
						264
						116
						281
						115
						300
						101
						319
						93
						319
						139
						0
						139
						0
						130
						20
						131
						48
						123
						45
						108
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance flowerPoly1 of Polygon
	(properties)
	
	(method (init)
		(super
			init: 261 112 243 91 242 66 255 53 285 56 300 64 297 105 279 114 262 112
		)
	)
)

(instance flowerPoly2 of Polygon
	(properties)
	
	(method (init)
		(super init: 0 84 13 94 21 129 0 130)
	)
)

(instance picturePoly1 of Polygon
	(properties)
	
	(method (init)
		(super init: 112 84 112 37 152 37 142 89)
	)
)

(instance picturePoly2 of Polygon
	(properties)
	
	(method (init)
		(super init: 24 36 65 37 56 88 28 85)
	)
)
