;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Print)

(public
	roChapter2 0
)

(instance soPlayChapter of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {Chapter 2})
				(= ticks 1)
			)
			(1 (curRoom newRoom: 20100))
		)
	)
)

(instance roChapter2 of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: 0)
		(= global202 2)
		(Palette palSET_FROM_RESOURCE 20000)
		(curRoom setScript: soPlayChapter)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
