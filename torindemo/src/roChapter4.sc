;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Print)

(public
	roChapter4 0
)

(instance soPlayChapter of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints {Chapter 4})
				(= ticks 1)
			)
			(1 (curRoom newRoom: -25436))
		)
	)
)

(instance roChapter4 of TPRoom
	(properties)
	
	(method (init)
		(super init: &rest)
		(theMusic pageSize: 0)
		(= global202 4)
		(Palette 1 -25536)
		(curRoom setScript: soPlayChapter)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
