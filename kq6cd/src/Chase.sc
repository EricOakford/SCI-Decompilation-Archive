;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHASE)
(include game.sh)
(use Motion)


(class Chase kindof Motion
	;;; Try to catch a particular Actor.
	
	(properties
		who 0					;who to chase
		distance 0			;how close to 'who' is considered a 'catch'
	)
	
	
	(method (init actor whom howClose whoCares)
		(if (>= argc 1)				(= client actor)
			(if (>= argc 2)			(= who whom)
				(if (>= argc 3)		(= distance howClose)
					(if (>= argc 4)	(= caller whoCares)
					)
				)
			)
		)
		(super init: client (who x?) (who y?) caller)
;		(super doit:)
	)
	
	
	(method (onTarget)
		(<= (client distanceTo: who) distance)
	)
	
	(method (setTarget)
		(cond
			(argc	
				(super setTarget: &rest)
			)
			((not (self onTarget:))
				(super setTarget: (who x?) (who y?))
				
			)
		)
	)
	
	(method (doit)
		(if (self onTarget?)
			(self moveDone:)
		else
			(super doit:)
			(if (== b-moveCnt 0)
				(super init: client (who x?) (who y?) caller)
			)
		)
	)
)
