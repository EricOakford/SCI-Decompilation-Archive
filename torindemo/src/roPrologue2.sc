;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Print)

(public
	roPrologue2 0
)

(instance soPlayPrologue of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints LOOKUP_ERROR)
				(= cycles 1)
			)
			(1 (curRoom newRoom: 10000))
		)
	)
)

(instance roPrologue2 of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 0)
		(= global202 0)
		(curRoom setScript: {roPrologue2})
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
