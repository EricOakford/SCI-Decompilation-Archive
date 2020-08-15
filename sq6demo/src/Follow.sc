;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FOLLOW.SC
;;;;
;;;;	(c) Sierra On-Line, Inc., 1993
;;;;
;;;;	Author:	Unknown
;;;;	Updated:	
;;;;
;;;;	This motion moves its client in such a way as to try to stay within
;;;;	a certain distance of another object.
;;;;
;;;;	Classes:
;;;;		Follow


(script# FOLLOW)
(include game.sh)
(use Motion)


(class Follow kindof Motion
	(properties
		who 0					;who to follow
		distance 20			;try to stay at least this close to 'who'
	)
	
	(method (init theObj whom dist)
		
		(if (>= argc 1)			(= client theObj)
			(if (>= argc 2)		(= who whom)
				(if (>= argc 3)	(= distance dist)
				)
			)
		)
		
		
		;If the client is too far from the object being followed, start
		;moving toward it.
		(if (> (client distanceTo: who) distance)
			(super init: client (who x?) (who y?))
;			(super doit:)
		)
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
	
	(method (doit &tmp angle)
		(if (> (client distanceTo: who) distance)
			(if (== b-moveCnt 0)
				(super init: client (who x?) (who y?))
			)
			;If too far from the object being followed, move toward it.
			(super doit:)
		else
			;The client is just standing around near its destination.  Pick
			;the loop which faces in the destination's direction.
			(= xLast (client x?))
			(= yLast (client y?))
			(= angle (GetAngle xLast yLast (who x?) (who y?)))
			(if (!= angle (client heading?))
				(client heading: angle)
				(if (client looper?)
					((client looper?) doit: client angle)
				else
					(DirLoop client angle)
				)
			)
		)
	)

	(method (moveDone)
		;;; When done with the current leg of wandering, re-init: the motion
		;;; to continue wandering.
	;		(self init: client distance)
	)
)
