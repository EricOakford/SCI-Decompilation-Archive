;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40600)
(include sci.sh)
(use Main)
(use TPRoom)
(use CueMe)
(use ExitFeature)
(use Feature)

(public
	roWaterCannon 0
)

(instance foCannonExit of CUExitFeature
	(properties
		unregisterDefaultHandler -25036
	)
)

(instance foControls of Feature
	(properties
		noun 1
		nsLeft 218
		nsTop 99
		nsRight 246
		nsBottom 202
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 74)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: noun theVerb 0 0)
			)
			(74
				(messager say: noun theVerb 0 0 LOOKUP_ERROR)
			)
		)
	)
)

(instance coLeaveCU of CueMe
	(properties)
	
	(method (cue)
		((ScriptID 64017 0) set: 107)
		(curRoom newRoom: -25036)
	)
)

(instance roWaterCannon of TPRoom
	(properties
		picture -24936
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -25236)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(theGame handsOn:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
