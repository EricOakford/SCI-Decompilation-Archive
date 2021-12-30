;;; Sierra Script 1.0 - (do not remove this comment)
(script# 11300)
(include sci.sh)
(use Main)
(use TPRoom)
(use CueMe)
(use ExitFeature)
(use Polygon)
(use Feature)

(public
	roAshtray 0
)

(instance coExitRoom of CueMe
	(properties)
	
	(method (cue)
		(curRoom newRoom: 11100)
	)
)

(instance foPouch of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						420
						32
						438
						60
						498
						97
						514
						99
						519
						130
						525
						136
						560
						123
						562
						109
						555
						93
						541
						83
						547
						55
						521
						26
						479
						5
						424
						15
					yourself:
				)
		)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 1)
				(ego get: ((ScriptID 64001 0) get: 2))
				((ScriptID 64017 0) set: 2)
				(if ((ScriptID 64017 0) test: 34)
					(messager say: noun theVerb 1 0 coExitRoom)
				else
					(messager say: noun theVerb 0 0 coExitRoom)
				)
				(self dispose:)
				(return 1)
			else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler 11100
	)
)

(instance roAshtray of TPRoom
	(properties
		picture 11300
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 11100)
		(if (not ((ScriptID 64017 0) test: 2))
			(foPouch init:)
		)
		(foExit init:)
		(theGame handsOn:)
	)
)
