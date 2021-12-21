;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15200)
(include sci.sh)
(use Main)
(use TorinEgo)
(use TPRoom)
(use TPScript)
(use Plane)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roPortalChamber 0
)

(instance foPlatform of Feature
	(properties
		approachX 219
		approachY 172
		x 219
		y 172
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 13)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 156 204 146 172 206 150 266 150 294 183 241 206 155 205
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom setScript: LOOKUP_ERROR)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foPortal of Feature
	(properties
		nsRight 85
		nsBottom 134
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 13)
	)
	
	(method (doVerb)
		(LOOKUP_ERROR doVerb: &rest)
	)
)

(instance oRSDHandler of VerbHandler
	(properties)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 13)
				(curRoom setScript: 'LOOKUP_ERROR')
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance foFont of Feature
	(properties
		nsLeft 309
		nsTop 122
		nsRight 336
		nsBottom 187
		approachX 322
		approachY 173
		x 322
		y 175
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 3 1)
		(self approachVerbs: 1 2)
	)
	
	(method (doVerb)
		(curRoom newRoom: 15700)
	)
)

(instance foConsole of Feature
	(properties
		nsLeft 251
		nsTop 63
		nsRight 295
		nsBottom 110
		approachX 277
		approachY 103
		x 276
		y 104
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 22)
		(self approachVerbs: 1 2)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(22 (curRoom newRoom: 15600))
			(1 (curRoom newRoom: 15600))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soTransport of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego nSaveTime: LOOKUP_ERROR self)
			)
			(1
				(self setScript: (ScriptID 64018 1) self)
			)
			(2
				(ego
					setMotion: MoveTo (LOOKUP_ERROR x?) (LOOKUP_ERROR y?) self
				)
			)
			(3 (ego setHeading: 135 self))
			(4
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR setCel: 0 init:)
				(= seconds 2)
			)
			(5
				(LOOKUP_ERROR setCycle: End self)
			)
			(6 (= seconds 2))
			(7 (curRoom newRoom: 20000))
		)
	)
)

(instance poTransport of Prop
	(properties
		x 161
		y 296
		view 15200
	)
)

(instance oCrystalCU of Plane
	(properties
		picture 15201
		priority 20
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
	)
)

(instance roPortalChamber of TPRoom
	(properties
		picture 15200
		south 15100
	)
	
	(method (init)
		(if ((ScriptID 64017 0) test: 40) (= picture 15202))
		(super init: &rest)
		(theGame handsOn:)
		(ego init: oPanner: setScaler: Scaler 115 50 281 103)
		(ego oFlagValues: LOOKUP_ERROR)
		((ScriptID 64018 0)
			init:
			oPanner:
			setScaler: Scaler 115 50 281 103
		)
		(switch prevRoomNum
			(15700
				(ego
					posn: (LOOKUP_ERROR approachX?) (LOOKUP_ERROR approachY?)
					loop: 2
				)
			)
			(15600
				(ego
					posn: (LOOKUP_ERROR approachX?) (LOOKUP_ERROR approachY?)
					loop: 2
				)
			)
			(else 
				(ego posn: 300 316 loop: 3)
				((ScriptID 64018 0) posn: 195 312 loop: 3)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						633
						318
						614
						245
						542
						183
						398
						130
						319
						120
						317
						103
						273
						103
						310
						109
						294
						117
						245
						113
						245
						105
						169
						116
						135
						120
						107
						122
						0
						140
						0
						322
					yourself:
				)
		)
		(if
			(and
				(not (ego has: ((ScriptID 64001 0) get: 9)))
				((ScriptID 64017 0) test: 40)
			)
			(LOOKUP_ERROR init:)
		)
		(if ((ScriptID 64017 0) test: 40)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							148
							210
							136
							170
							205
							146
							269
							146
							296
							168
							354
							181
							354
							189
							315
							194
							297
							185
							243
							212
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 136 170 204 147 273 147 304 180 244 215 147 215
						yourself:
					)
			)
		)
		(if (not ((ScriptID 64017 0) test: 40))
			(LOOKUP_ERROR init:)
		)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		((ScriptID 64899 3) init:)
		(theMusic pageSize: 15200)
	)
)
