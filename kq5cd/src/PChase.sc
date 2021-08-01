;;; Sierra Script 1.0 - (do not remove this comment)
(script# 930)
(include sci.sh)
(use Main)
(use PolyPath)
(use System)


(class PChase of PolyPath
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
		value 2
		points 0
		finalX 0
		finalY 0
		obstacles 0
		who 0
		distance 0
		targetX 0
		targetY 0
	)
	
	(method (init theClient theWho theDistance theCaller theObstacles)
		(cond 
			((>= argc 5) (= obstacles theObstacles))
			((not (IsObject obstacles)) (= obstacles (curRoom obstacles?)))
		)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= who theWho)
				(= targetX (who x?))
				(= targetY (who y?))
				(if (>= argc 3)
					(= distance theDistance)
					(if (>= argc 4) (= caller theCaller))
				)
			)
		)
		(super init: client targetX targetY caller 1 obstacles)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(
				(>
					(GetDistance targetX targetY (who x?) (who y?))
					distance
				)
				(if points (Memory 3 points))
				(= points 0)
				(= value 2)
				(self init: client who)
			)
			(
			(<= (= temp0 (client distanceTo: who)) distance) (self moveDone:))
			(else (super doit:))
		)
	)
	
	(method (moveDone &tmp temp0)
		(cond 
			(
			(<= (= temp0 (client distanceTo: who)) distance) (super moveDone:))
			((== (WordAt points value) 30583)
				(if points (Memory 3 points))
				(= points 0)
				(= value 2)
				(self init: client who)
			)
			(else (self init:))
		)
	)
)

(class PFollow of PolyPath
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
		value 2
		points 0
		finalX 0
		finalY 0
		obstacles 0
		who 0
		distance 0
		targetX 0
		targetY 0
	)
	
	(method (init theClient theWho theDistance param4 &tmp temp0)
		(= temp0
			(if (>= argc 4) param4 else (curRoom obstacles?))
		)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= who theWho)
				(= targetX (who x?))
				(= targetY (who y?))
				(if (>= argc 3) (= distance theDistance))
			)
		)
		(super init: client targetX targetY 0 1 temp0)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(
				(>
					(GetDistance targetX targetY (who x?) (who y?))
					distance
				)
				(if points (Memory 3 points))
				(= points 0)
				(= value 2)
				(self init: client who)
			)
			(
			(<= (= temp0 (client distanceTo: who)) distance)
				(client
					setHeading: (GetAngle (client x?) (client y?) (who x?) (who y?))
				)
				(= xLast (client x?))
				(= yLast (client y?))
				(return)
			)
			(else (super doit:))
		)
	)
	
	(method (moveDone)
		(self init:)
	)
)
