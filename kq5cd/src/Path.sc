;;; Sierra Script 1.0 - (do not remove this comment)
(script# 983)
(include sci.sh)
(use Intrface)
(use Motion)


(class Path of MoveTo
	(properties
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
		intermediate 0
		value 0
	)
	
	(method (init theClient param2 param3)
		(= client theClient)
		(= caller (if (>= argc 2) param2 else 0))
		(= intermediate (if (== argc 3) param3 else 0))
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
			(if intermediate (intermediate cue: (/ value 2)))
			(self next:)
			(super init: client x y)
		)
	)
	
	(method (at)
		(Printf 983 0 name)
		(return 0)
	)
	
	(method (next)
		(= x (self at: (++ value)))
		(= y (self at: (++ value)))
	)
	
	(method (atEnd)
		(return
			(if (== (self at: (+ value 1)) -32768)
			else
				(== (self at: (+ value 2)) -32768)
			)
		)
	)
)

(class RelPath of Path
	(properties
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
		intermediate 0
		value 0
	)
	
	(method (next)
		(= x (+ x (self at: (++ value))))
		(= y (+ y (self at: (++ value))))
	)
)
