;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53000)
(include sci.sh)
(use Main)
(use TPRoom)
(use ExitFeature)

(public
	roMagicianCu 0
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler -14636
	)
)

(instance roMagicianCu of TPRoom
	(properties
		picture -12536
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -12536)
		(foExit init:)
		(theGame handsOn:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
