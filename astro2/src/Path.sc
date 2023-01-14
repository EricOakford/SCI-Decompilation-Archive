;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PATH.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	Motion classes for a path -- i.e. moving to a series of pre-defined
;;;;	points.
;;;;
;;;;	Classes:
;;;;		Path




(script#	PATH)
(include game.sh)
(use Intrface)
(use Motion)


(class Path of MoveTo
	(properties
		intermediate	0		;object to cue at intermediate endpoints
		value				0		;index into path array
	)

;;;	(methods
;;;		at							;return nth element of control array
;;;		next						;move to next point in path
;;;		atEnd						;are we at the end of the path?
;;;	)



	(method (init actor toCall inter)
		(= client actor)
		(= caller (if (>= argc 2) toCall else 0))
		(= intermediate (if (== argc 3) inter else 0))
		(= value -1)
		(= x (client x?))
		(= y (client y?))

		(if (self atEnd:)
			(self moveDone:)
		else
			(self next:)
			(super init: client x y)
		)
	)



	(method (moveDone)
		(if (self atEnd:)
			(super moveDone:)
		else
			(if intermediate
				(intermediate cue: (/ value 2))
			)
			(self next:)
			(super init: client x y)
		)
	)


	(method (next)
		(= x (self at: (++ value)))
		(= y (self at: (++ value)))
	)


	(method (atEnd)
		(return
			(or
				(== (self at: (+ value 1)) PATHEND)
				(== (self at: (+ value 2)) PATHEND)
			)
		)
	)


	(method (at n)
		(Printf PATH 0 name)
		(return 0)
	)
)




(class RelPath of Path
	(method (next)
		(+= x (self at: (++ value)))
		(+= y (self at: (++ value)))
	)
)