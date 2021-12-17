;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Print)

(public
	roClosing 0
)

(instance soPlayMovie of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {The End})
				(= cycles 1)
			)
			(1 (curRoom newRoom: -4436))
		)
	)
)

(instance roClosing of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: 0)
		(curRoom setScript: soPlayMovie)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
