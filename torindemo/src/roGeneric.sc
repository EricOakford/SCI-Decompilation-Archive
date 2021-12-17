;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12345)
(include sci.sh)
(use Main)
(use TPRoom)

(public
	roGeneric 0
)

(instance roGeneric of TPRoom
	(properties
		picture 0
	)
	
	(method (init)
		(super init: &rest)
		(theGame handsOn:)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
