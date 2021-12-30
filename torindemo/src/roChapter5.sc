;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Print)

(public
	roChapter5 0
)

(instance soPlayChapter of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {Chapter 5})
				(= cycles 1)
			)
			(1 (curRoom newRoom: -15436))
		)
	)
)

(instance roChapter5 of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 0)
		(= global202 5)
		(Palette 1 -15536)
		(curRoom setScript: soPlayChapter)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
