;;; Sierra Script 1.0 - (do not remove this comment)
(script# FOLLOW)
(include game.sh)
(use Motion)


(class Follow kindof Motion
	;;; This class moves its client in such a way as to try and stay within
	;;; a certain distance to another object.

	(properties
		who 0					;who to follow
		distance 0			;try to stay at least this close to 'who'
	)


	(method (init theObj whom dist)
		(= who whom)
		(= client theObj)

		;If distance is not specified, default to 20 pixels.
		(= distance
			(if (< argc 3)
				20
			else 
				dist
			)
		)

		;If the client is too far from the object being followed, start
		;moving toward it.
		(if (> (client distanceTo: who) distance)
			(super init: theObj (who x?) (who y?))
			(super doit:)
		)
	)


	(method (doit &tmp angle)
		(if (> (client distanceTo: who) distance)
			;If too far from the object being followed, move toward it.
			(super doit:)
			(if (== b-moveCnt 0)
				(super init: client (who x?) (who y?))
			)
		else
			;The client is just standing around near its destination.  Pick
			;the loop which faces in the destination's direction.
			(= angle (GetAngle (client x?) (client y?) (who x?) (who y?)))
			(if (client looper?)
				((client looper?) doit: client angle)
			else
				(DirLoop client angle)
			)
		)
	)
)
