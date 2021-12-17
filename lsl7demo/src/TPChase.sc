;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64027)
(include sci.sh)
(use TPPolyPath)


(class TPChase of TPPolyPath
	(properties
		scratch 0
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
		distance 15
	)
	
	(method (onTarget)
		(<= (client distanceTo: who) distance)
	)
	
	(method (setDefault)
		(super setDefault:)
		(= who 0)
		(= distance 15)
	)
	
	(method (newTarget)
		(= finalX (who x?))
		(= finalY (who y?))
	)
	
	(method (setArgs theWho)
		(if (>= argc 2)
			(= who [theWho 1])
			(if (>= argc 3)
				(= distance [theWho 2])
				(if (>= argc 3) (= caller [theWho 3]))
			)
		)
	)
)
