;;; Sierra Script 1.0 - (do not remove this comment)
(script# 986)
(include game.sh)
(use Main)
(use Motion)


(class Orbit of BegLoop
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
		nextCel 0
		xTilt 0
		yTilt 50
		angleStep 0
		winding 0
		curAngle 10
		intermediate 1
		atEnd 0
	)
	
	(method (init theClient theXTilt theYTilt theAngleStep theWinding theCurAngle theIntermediate theAtEnd &tmp xTiltX xTiltY temp2 temp3)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= xTilt theXTilt)
				(if (>= argc 3)
					(= yTilt theYTilt)
					(if (>= argc 4)
						(= angleStep theAngleStep)
						(if (>= argc 5)
							(= winding theWinding)
							(if (>= argc 6)
								(= curAngle theCurAngle)
								(if (>= argc 7)
									(= intermediate theIntermediate)
									(if (>= argc 8) (= atEnd theAtEnd))
								)
							)
						)
					)
				)
			)
		)
		(if xTilt
			(= xTiltX (xTilt x?))
			(= xTiltY (xTilt y?))
		else
			(= xTiltX 160)
			(= xTiltY 100)
		)
		(= temp2 (TimesSin atEnd yTilt))
		(= temp3
			(TimesCos
				(+ winding perspective)
				(TimesCos atEnd yTilt)
			)
		)
		(if angleStep
			(= temp2 (TimesCos angleStep temp2))
			(= temp3 (+ temp3 (TimesSin angleStep temp2)))
		)
		(= x (+ xTiltX temp2))
		(= y (- xTiltY temp3))
		(= atEnd (mod (+ atEnd (* intermediate curAngle)) 360))
		(super init: client x y)
	)
	
	(method (endCel)
		(self init:)
	)
)
