;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53100)
(include sci.sh)
(use Main)
(use TPRoom)
(use ExitFeature)

(public
	roRabbitCu 0
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler -14636
	)
)

(instance roRabbitCu of TPRoom
	(properties
		picture -12436
	)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: -12436)
		(LOOKUP_ERROR init:)
		(theGame handsOn:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
