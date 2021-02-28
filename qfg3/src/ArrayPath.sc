;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARRAY_PATH) ;38
(include game.sh)
(use Motion)
(use System)


(class ArrayPath of MoveTo
	(properties
		value 0
		thePoints 0
	)
	
	(method (init actor pts val toCall)
		(if (>= argc 1)
			(= client actor)
			(= thePoints pts)
			(if (>= argc 2)
				(= value val)
				(if (>= argc 3)
					(= caller toCall)
				)
			)
		)
		(if argc
			(self setTarget:)
		)
		(super init:)
	)
	
	(method (moveDone)
		(if (== (WordAt thePoints value) PATHEND)
			(super moveDone:)
		else
			(self setTarget: init:)
		)
	)
	
	(method (setTarget)
		(if (!= (WordAt thePoints value) PATHEND)
			(= x (WordAt thePoints value))
			(= y (WordAt thePoints (++ value)))
			(++ value)
		)
	)
	
	(method (onTarget)
		(return (if (== (client x?) x) (== (client y?) y) else 0))
	)
)
