;;; Sierra Script 1.0 - (do not remove this comment)
(script# 53200)
(include sci.sh)
(use Main)
(use TPRoom)
(use ExitFeature)

(public
	roArchivistCu 0
)

(instance foExit of CUExitFeature
	(properties
		unregisterDefaultHandler -14636
	)
)

(instance roArchivistCu of TPRoom
	(properties
		picture -12336
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -12336)
		(foExit init:)
		(theGame handsOn:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
