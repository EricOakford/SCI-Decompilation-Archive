;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use PolyFeature)
(use Scaler)
(use Polygon)
(use Feature)
(use StopWalk)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm300 0
	rideTram 1
)

(local
	local0
	local1
	local2
)
(instance rm300 of LarryRoom
	(properties
		noun 1
		picture 300
		horizon 0
	)
	
	(method (init &tmp temp0 temp1)
		(if
			(= temp1
				(if (OneOf global171 3 2) else (== global100 300))
			)
			(= global171 2)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							259
							138
							268
							120
							320
							120
							320
							110
							137
							110
							129
							115
							97
							115
							45
							118
							13
							118
							13
							111
							21
							111
							17
							102
							0
							95
							0
							138
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 3
						init:
							263
							138
							269
							120
							320
							120
							320
							110
							123
							110
							123
							108
							105
							108
							105
							114
							45
							118
							13
							118
							13
							111
							21
							111
							17
							102
							0
							95
							0
							138
						yourself:
					)
			)
		)
		(if (not (Btst 35))
			(ego normalize: 900 8 edgeHit: 0 ignoreActors: 0 init:)
		)
		(if (not (theMusic2 handle?))
			(theMusic2 number: 200 play: setLoop: -1)
		)
		(switch prevRoomNum
			(305
				(if (< (ego y?) 106)
					(ego x: 10 y: 109 cel: 0)
				else
					(ego x: 10 y: (ego y?) edgeHit: 0 cel: 0)
				)
				(theGame handsOn:)
			)
			(310
				(self setScript: fromHealthSpaScr)
			)
			(else 
				(ego posn: 300 116 cel: 1)
				(if (not (Btst 35)) (self setScript: enterFromEastScr))
			)
		)
		(if temp1 (gammie init:))
		(if (Btst 35)
			(if (Btst 36)
				((ScriptID 825 1) z: 0 view: 90 posn: 0 126 init:)
			else
				((ScriptID 825 1) z: 0 view: 90 posn: 319 126 init:)
			)
			((ScriptID 825 2) play: setLoop: -1)
			(self setScript: rideTram)
		)
		(visibleSculpture init:)
		(hiddenSculpture init:)
		(flowers init:)
		(windowFrame init:)
		(carpet init:)
		(door init: approachVerbs: 4 ignoreActors: 1)
		(super init: &rest)
	)
	
	(method (dispose)
		(if
		(and (!= newRoomNum 310) (OneOf global171 3 4))
			(= global171 7)
		)
		(DisposeScript -572)
		(super dispose:)
	)
	
	(method (edgeToRoom param1)
		(switch param1
			(4
				(self setScript: exitWestScr)
			)
			(2
				(self setScript: exitEastScr)
			)
		)
		(return 0)
	)
)

(instance fromHealthSpaScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					posn: 105 103
					loop: 8
					setScaler: Scaler 100 80 107 99
					cel: 2
				)
				(door cel: (door lastCel:))
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo 115 112 self)
			)
			(2
				(ego setHeading: 180)
				(door setCycle: Beg self)
			)
			(3
				(theMusic number: 33 loop: 1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance toHealthSpaScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (cast contains: gammie)
					(= local0 1)
					(ego
						setMotion: MoveTo (door approachX?) (door approachY?) self
					)
				else
					(= ticks 20)
				)
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
				(doorSfx number: 32 play:)
				(ego setCycle: Beg self)
				(door setCycle: End self)
			)
			(4 0)
			(5
				(theMusic2 fade:)
				(ego
					normalize: 900 8 1
					cel: 7
					setScaler: Scaler 100 80 107 99
					setMotion: MoveTo 105 103 self
				)
			)
			(6 (= cycles 2))
			(7 (curRoom newRoom: 310))
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
						(ScriptID 0 9)
						(ScriptID 0 6)
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
					(curRoom newRoom: 200)
				else
					(curRoom newRoom: 305)
				)
			)
		)
	)
)

(instance enterFromEastScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 265 116 self)
			)
			(1
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
			(1 (curRoom newRoom: 305))
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
			(1 (curRoom newRoom: 200))
		)
	)
)

(instance egoOpenDoorScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= local1 1) (= cycles 2))
			(1 (messager say: 6 4 1 1 self))
			(2
				(if
					(or
						(!= (ego x?) (door approachX?))
						(!= (ego y?) (door approachY?))
					)
					(ego
						ignoreActors: 1
						setMotion: MoveTo (door approachX?) (door approachY?) self
					)
				else
					(= cycles 2)
				)
			)
			(3
				(ego
					view: 901
					loop: 6
					cel: 0
					setSpeed: 6
					setCycle: End self
				)
			)
			(4 (= cycles 2))
			(5
				(doorSfx number: 32 play:)
				(ego setCycle: Beg self)
				(door setCycle: End self)
			)
			(6)
			(7
				(ego
					normalize: 900 8
					setCycle: StopWalk -1
					setMotion: MoveTo 104 121 self
				)
			)
			(8
				(ego ignoreActors: 1 setHeading: 0 self)
			)
			(9 (= cycles 2))
			(10
				(messager say: 6 4 1 2 self)
			)
			(11
				(ego normalize: 900)
				(self dispose:)
			)
		)
	)
)

(instance followGammieInScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(!= (ego x?) (door approachX?))
						(!= (ego y?) (door approachY?))
					)
					(ego
						setMotion: MoveTo (door approachX?) (door approachY?) self
					)
				else
					(= cycles 2)
				)
			)
			(1
				(if (and (door cel?) (not local1) (not local2))
					(= local2 1)
					(messager say: 6 4 2 1 self)
				else
					(self cue:)
				)
			)
			(2
				(ego
					setScaler: Scaler 100 80 107 99
					setMotion: MoveTo 105 103 self
				)
			)
			(3
				(= global171 5)
				(curRoom newRoom: 310)
			)
		)
	)
)

(instance gammie of Actor
	(properties
		heading 270
		x 231
		y 116
		view 203
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: StopWalk -1 setScript: gammieScr)
	)
	
	(method (doVerb theVerb)
		(cond 
			((and (not local2) (== theVerb 1) (door cel?)) (= local2 1) (messager say: 6 4 2 1))
			((== theVerb 2) (messager say: 3 2 2 1 0 200))
			(else (super doVerb: theVerb))
		)
	)
)

(instance door of Prop
	(properties
		noun 2
		modNum 205
		sightAngle 40
		approachX 112
		approachY 108
		x 112
		y 102
		view 300
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(cond 
					((not local0) (curRoom setScript: toHealthSpaScr))
					((not local1)
						(= local1 1)
						(theGame handsOff:)
						(messager say: 6 4 1 0)
						(gammieScr register: 1)
					)
					(else (theGame handsOff:) (gammieScr register: 1))
				)
			)
			((and cel (not local2) (== theVerb 1)) (= local2 1) (messager say: 6 4 2 1))
			((OneOf theVerb 1 2) (messager say: 6 theVerb))
			(else (super doVerb: theVerb))
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
		(self
			addPolygon:
				((Polygon new:)
					type: 0
					init:
						275
						108
						272
						123
						300
						131
						320
						130
						320
						139
						1
						139
						1
						93
						20
						101
						39
						115
						56
						116
						60
						112
						96
						112
						96
						106
						133
						107
						133
						109
					yourself:
				)
		)
		(super init: &rest)
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
						199
						41
						209
						82
						215
						121
						264
						127
						315
						139
						242
						139
						215
						133
						216
						139
						201
						139
						201
						131
						122
						124
						69
						124
						1
						130
						1
						120
						65
						114
						145
						115
						200
						118
						198
						94
						187
						54
						180
						34
						159
						0
						178
						0
					yourself:
				)
		)
		(super init: &rest)
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

(instance visibleSculpture of Feature
	(properties
		noun 4
		modNum 205
		sightAngle 40
		x 275
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

(instance hiddenSculpture of Feature
	(properties
		noun 4
		modNum 205
		sightAngle 40
		x 191
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

(instance flowerPoly1 of Polygon
	(properties)
	
	(method (init)
		(super
			init: 58 112 41 114 23 105 20 64 35 56 65 53 78 66 77 91 59 112
		)
	)
)

(instance flowerPoly2 of Polygon
	(properties)
	
	(method (init)
		(super init: 320 130 299 129 307 94 320 84)
	)
)

(instance picturePoly1 of Polygon
	(properties)
	
	(method (init)
		(super init: 178 89 168 37 208 37 208 84)
	)
)

(instance picturePoly2 of Polygon
	(properties)
	
	(method (init)
		(super init: 292 85 264 88 255 37 296 36)
	)
)

(instance doorSfx of Sound
	(properties
		flags $0001
		number 32
	)
)

(instance gammieScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not global205) (proc79_8 2))
				(= cycles 2)
			)
			(1
				(if (!= global171 3)
					(gammie posn: 227 116)
					(self cue:)
				else
					(gammie ignoreActors: 0 setMotion: MoveTo 227 116 self)
				)
			)
			(2
				(= global171 4)
				(gammie setMotion: MoveTo 150 113 self)
			)
			(3
				(cond 
					(local0 (self dispose:))
					((ego inRect: 85 99 139 119)
						(theGame handsOff:)
						(self setScript: egoOpenDoorScr self)
						(= register 1)
					)
					(else (= local0 1) (self cue:))
				)
			)
			(4
				(gammie setMotion: MoveTo 113 112 self)
			)
			(5 (gammie setHeading: 0 self))
			(6 (= ticks 40))
			(7
				(if (or (not (door cel?)) (not register))
					(doorSfx number: 32 play:)
					(door setCycle: End self)
				else
					(= cycles 2)
				)
			)
			(8
				(gammie
					ignoreActors: 1
					setScaler: Scaler 100 80 107 99
					setMotion: MoveTo 107 100 self
				)
			)
			(9
				(if (not register)
					(door setCycle: Beg self)
				else
					(client setScript: followGammieInScr)
				)
			)
			(10
				(if global205 (proc79_7))
				(doorSfx number: 33 play:)
				(gammie hide: ignoreActors: 1)
				(= cycles 2)
			)
			(11
				(if register (ego setScript: toHealthSpaScr))
				(= global171 5)
				(= local0 0)
				(= seconds 5)
			)
			(12
				(= global171 7)
				(gammie dispose:)
			)
		)
	)
)
