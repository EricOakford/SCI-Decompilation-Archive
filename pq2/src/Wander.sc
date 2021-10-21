;;; Sierra Script 1.0 - (do not remove this comment)
(script# 976)
(include game.sh)
(use Motion)


(include game.sh)
(use Motion)

(class Wander kindof Motion
	;;; Wander about the screen.  This motion never terminates.
	
	(properties
		distance 30		;the max distance to move on any one leg of the wander
	)
	
	(method (init theObj dist)
		(if (>= argc 1)		(= client theObj)
			(if (>= argc 2)	(= distance dist)
			)
		)
		(self	setTarget:)
		(super init: client)
;		(super doit:)
	)
	
	
	(method (setTarget &tmp diam)
		
		;Pick a random position to move to, constrained by 'distance'.
		(= x (+ (client x?) (- distance (Random 0 (= diam (* distance 2))))))
		(= y (+ (client y?) (- distance (Random 0 diam))))
	)
	
	(method (onTarget)
		(return FALSE)	;we're never done wandering
	)
	
	(method (doit)
		;Take the next step.
		(super doit:)
		
		;;If the motion is complete or the client is blocked,
		;;we're finished with this leg.
		(if (client isStopped:)
			(self moveDone:)
		)
	)
	
	(method (moveDone)
		;;; When done with the current leg of wandering, re-init: the motion
		;;; to continue wandering.
		(self init:)
	)
);Wander

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
