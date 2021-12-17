;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64023)
(include sci.sh)
(use Main)
(use TPPolyPath)
(use System)


(class TPOrbit of TPPolyPath
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
		centerObj 0
		angleStep 10
		radius 50
		curAngle 0
		xTilt 0
		yTilt 0
	)
	
	(method (moveDone)
		(if (== (points at: value) 30583)
			(self init: client)
		else
			(self setTarget: init:)
		)
	)
	
	(method (nBaseTilesX)
		(super nBaseTilesX:)
		(= centerObj 0)
		(= angleStep 10)
		(= radius 50)
		(= yTilt (= xTilt (= curAngle 0)))
	)
	
	(method (mn &tmp centerObjX centerObjY temp2 temp3)
		(if centerObj
			(= centerObjX (centerObj x?))
			(= centerObjY (centerObj y?))
		else
			(= centerObjX (/ screenWidth 2))
			(= centerObjY (/ screenHeight 2))
		)
		(= temp2 (SinMult curAngle radius))
		(= temp3
			(CosMult
				(+ yTilt perspective)
				(CosMult curAngle radius)
			)
		)
		(if xTilt
			(= temp2 (CosMult xTilt temp2))
			(= temp3 (+ temp3 (SinMult xTilt temp2)))
		)
		(= finalX (+ centerObjX temp2))
		(= finalY (- centerObjY temp3))
		(= curAngle (umod (+ curAngle angleStep) 360))
	)
	
	(method (md theCenterObj)
		(if (>= argc 2)
			(= centerObj [theCenterObj 1])
			(if (>= argc 3)
				(= radius [theCenterObj 2])
				(if (>= argc 4)
					(= curAngle [theCenterObj 3])
					(if (>= argc 5)
						(= angleStep [theCenterObj 4])
						(if (>= argc 6)
							(= xTilt [theCenterObj 5])
							(if (>= argc 7)
								(= yTilt [theCenterObj 6])
								(if (>= argc 8) (= caller [theCenterObj 7]))
							)
						)
					)
				)
			)
		)
	)
)
