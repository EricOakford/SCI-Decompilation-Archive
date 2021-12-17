;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PCHASE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	This is a PolyPath-based chase mover
;;;;
;;;;	Classes:
;;;;		PChase


(script# PCHASE)
(include game.sh)
(use Main)
(use PolyPath)


(class PChase kindof PolyPath
	(properties
		who	  	NULL
		distance	0
		targetX	0
		targetY	0
	)

	(method (init actor whom howClose whoCares theObst)
		(if argc
			(cond
				((>= argc 5)  
					(= obstacles theObst)
				)
				((not obstacles)
					(=	obstacles (curRoom obstacles?))
				)
			)
			(if (>= argc 1)	(= client actor)
				(if (>= argc 2)	(= who whom) (= targetX (who x?)) (= targetY (who y?))
					(if (>= argc 3) 	(= distance howClose)
						(if (>= argc 4)	(= caller whoCares)
						)
					)
				)
			)
			(super init: client targetX targetY caller TRUE obstacles)
		else
			(super init:)
		)
	)

	(method (doit &tmp theDistance)
		(cond
			((> (GetDistance targetX targetY (who x?) (who y?)) distance)
				;; start again
				(if points
					(points dispose:)
				)
				(= points 0)
				(= value 2)
				(self init: client who)
			)
			((<= (= theDistance (client distanceTo: who)) distance)
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
			((<= (= theDistance (client distanceTo: who)) distance)
				;; really done
				(super moveDone:)
			)
			((== (points at: value) $7777) 
				;; at the end of a path so start over
			 	(if points
					(points dispose:)
				)
				(= points 0)
				(= value 2)
			 	(self init: client who)
			)
			(else
				;; just at a node, so keep going
				(self
					setTarget:,
					init:
				)
			)
 		)
	)
)