;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PCHASE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;
;;;;	Classes:
;;;;		PChase


(script# PCHASE)
(include game.sh)
(use Main)
(use PolyPath)
(use System)


(class PChase kindof PolyPath
	(properties
		who	  	NULL
		distance	0
		targetX	0
		targetY	0
	)

	(method (init actor whom howClose whoCares theObst &tmp [buffer 20])
		(if argc
			(cond
				((>= argc 5)  
					(= obstacles theObst)
				)
				((not (IsObject obstacles))
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
				(if points (Memory MDisposePtr points))
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

	(method (moveDone &tmp theDistance [buffer 20])
		(cond
			((<= (= theDistance (client distanceTo: who)) distance)
				;; really done
				(super moveDone:)
			)
			((== (WordAt points value) $7777) 
				;; at the end of a path so start over
			 	(if points (Memory MDisposePtr points))
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

(class PFollow kindof PolyPath
	(properties
		who	  	NULL
		distance	0
		targetX	0
		targetY	0
	)

	(method (init actor whom howClose theObst &tmp obstList)
		(if argc
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
		else
			(super init:)
		)
	)

	(method (doit &tmp theDistance angle)
		(return
			(cond
				((> (GetDistance targetX targetY (who x?) (who y?)) distance)
					;; start again
					(if points (Memory MDisposePtr points))
					(= points 0)
					(= value 2)
					(self init: client who)
					FALSE
				)
				((<= (= theDistance (client distanceTo:who)) distance)
					;; close enough, so don't move, just face.
					(= angle (GetAngle (client x?) (client y?) (who x?) (who y?)))
					(if (!= (client heading?) angle)
						(client setHeading: angle)
					)
					(= xLast (client x?))
					(= yLast (client y?))
					(= b-moveCnt gameTime)
					FALSE
				)
				(else
					(super doit:)
				)
			)
		)
	)

	(method (moveDone)
		;; start again.. this mover never dies	by itself
		(self
			setTarget:,
			init:
		)
	)
)
