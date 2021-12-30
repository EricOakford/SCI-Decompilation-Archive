;;; Sierra Script 1.0 - (do not remove this comment)
(script# 15500)
(include sci.sh)
(use Main)
(use TPRoom)
(use CueMe)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	roPickAShard 0
)

(instance foShards of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						309
						191
						266
						119
						287
						83
						295
						89
						318
						57
						343
						86
						354
						34
						401
						1
						419
						2
						431
						44
						398
						95
						418
						92
						429
						141
						459
						149
						439
						194
						339
						231
						339
						201
						351
						194
					yourself:
				)
		)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego get: ((ScriptID 64001 0) get: 12))
		((ScriptID 64017 0) set: 33)
		(messager sayRange: 0 0 4 4 6 oPickedShard 15200)
	)
)

(instance oPickedShard of CueMe
	(properties)
	
	(method (cue)
		(curRoom newRoom: 15200)
	)
)

(instance poPickAShard of Prop
	(properties
		x 391
		y 316
		view 15107
	)
)

(instance roPickAShard of TPRoom
	(properties
		picture 15102
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOff:)
		(theMusic pageSize: 15100)
		(poPickAShard init: setCycle: End)
		(foShards init:)
		(messager sayRange: 0 0 4 2 3 (ScriptID 64020 0) 15200)
	)
)
