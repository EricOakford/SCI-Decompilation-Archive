;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	PFOLLOW.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	This is a PolyPath-based follow mover
;;;;
;;;;	Classes:
;;;;		PFollow


(script# PFOLLOW)
(include game.sh)
(use Main)
(use PolyPath)


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
					(if points
						(points dispose:)
					)
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
