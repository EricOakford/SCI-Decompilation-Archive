;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Print)

(public
	roChapter1 0
)

(instance soPlayChapter of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints LOOKUP_ERROR)
				(Prints LOOKUP_ERROR)
				(Prints LOOKUP_ERROR)
				(Prints LOOKUP_ERROR)
				(Prints LOOKUP_ERROR)
				(Prints LOOKUP_ERROR)
				(= ticks 1)
			)
			(1 (curRoom newRoom: 11000))
		)
	)
)

(instance roChapter1 of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 0)
		(= global202 1)
		(Palette 1 10000)
		(curRoom setScript: LOOKUP_ERROR)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
