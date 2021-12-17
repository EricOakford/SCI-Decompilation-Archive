;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64024)
(include sci.sh)
(use TPPolyPath)


(class TPWander of TPPolyPath
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
		distance 30
	)
	
	(method (nBaseTilesX)
		(super nBaseTilesX:)
		(= distance 30)
	)
	
	(method (mn &tmp temp0)
		(= finalX
			(+
				(client x?)
				(- distance (Random 0 (= temp0 (* distance 2))))
			)
		)
		(= finalY
			(+ (client y?) (- distance (Random 0 temp0)))
		)
	)
	
	(method (md theDistance)
		(if (> argc 2) (= distance [theDistance 1]))
		(if (> argc 3) (= caller [theDistance 2]))
	)
)
