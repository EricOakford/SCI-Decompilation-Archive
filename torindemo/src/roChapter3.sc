;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Print)

(public
	roChapter3 0
)

(instance soPlayChapter of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {Chapter 3})
				(= ticks 1)
			)
			(1 (curRoom newRoom: 30100))
		)
	)
)

(instance roChapter3 of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: 0)
		(= global202 3)
		(Palette palSET_FROM_RESOURCE 30000)
		(curRoom setScript: soPlayChapter)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
