;;; Sierra Script 1.0 - (do not remove this comment)
(script# PCHASE)
(include game.sh)
(use Main)
(use PolyPath)
(use System)
;;;;
;;;;	PCHASE.SC
;;;;	(c) Sierra On-Line, Inc, 1991
;;;;
;;;;	Author: J. Mark Hood
;;;;
;;;;	PolyPath based PChase and PFollow movers.
;;;;	The trick, of course, was to avoid calling AvoidPath
;;;; any more than necessary. The closer you wish to follow or 
;;;;	chase an actor, the slower thing get. If you make an instance
;;;;	and redefine any of the methods, be very careful about parameters passed.
;;;;




(class PChase kindof PolyPath
	(properties
		who	  	NULL
		distance	0
		targetX	0
		targetY	0
	)

	(method (init actor whom howClose whoCares theObst)
		(cond
			((>= argc 5)  
				(= obstacles theObst)
			)
			((not (IsObject obstacles))
				(=	obstacles (curRoom obstacles?))
			)
		)
		(if (>= argc 1)	(= client actor)
			(if (>= argc 2)	(= who whom) (= targetX (who x?))(= targetY (who y?))
				(if (>= argc 3) 	(= distance howClose)
					(if (>= argc 4)	(= caller whoCares)
					)
				)
			)
		)
		(super init: client targetX targetY caller TRUE obstacles)
	)

	(method (doit &tmp theDistance)
		(cond
			((> (GetDistance targetX targetY (who x?) (who y?)) distance)
				;; start again
				(if points (Memory MDisposePtr points))
				(= points 0)
				(= value 2)
				(self 	init: client who)
			)
			((<= (= theDistance (client distanceTo:who)) distance)
				;; got close enough...
				(self moveDone:)
			)
			(else
				(super doit:)
			)
		)
	)

	(method (moveDone &tmp theDistance)
		(cond
			((<= (= theDistance (client distanceTo:who)) distance)
				;; really done
				(super moveDone:)
			)
			((== (WordAt points value) $7777) 
				;; at the end of a path so start over
			 	(if points (Memory MDisposePtr points))
				(= points 0)
				(= value 2)
			 	(self 	init: client who)
			)
			(else
				;; just at a node, so keep going
				(self init:)
			)
 		)
	)

)

(class PFollow kindof PolyPath
	(properties
		who	  	NULL
		distance	0
		targetX	0
		targetY	0
	)

	(method (init actor whom howClose theObst &tmp obstList)
		(= obstList
			(if (>= argc 4)  
				theObst
			else
				(curRoom obstacles?)
			)
		)
		(if (>= argc 1)	(= client actor)
			(if (>= argc 2)	(= who whom) (= targetX (who x?))(= targetY (who y?))
				(if (>= argc 3) 	(= distance howClose))
			)
		)
		(super init: client targetX targetY 0 TRUE obstList)
	)

	(method (doit &tmp theDistance)
		(cond
			((> (GetDistance targetX targetY (who x?) (who y?)) distance)
				;; start again
				(if points (Memory MDisposePtr points))
				(= points 0)
				(= value 2)
				(self 	init: client who)
			)
			((<= (= theDistance (client distanceTo:who)) distance)
				;; close enough, so don't move, just face.
				(client setHeading: 
					(GetAngle (client x?) (client y?) (who x?) (who y?))
				)
				(= xLast (client x?))
				(= yLast (client y?))
				(return)
			)
			(else
				(super doit:)
			)
		)
	)

	(method (moveDone)
		;; start again.. this mover never dies	by itself
		(self init:)
	)

)