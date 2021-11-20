;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64945)
(include sci.sh)
(use Main)
(use Array)
(use Motion)


(class PolyPath of Motion
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
	)
	
	(method (init theClient theFinalX theFinalY theCaller param5 theObstacles &tmp [temp0 30] temp30 temp31 clientPlane temp33)
		(if argc
			(= client theClient)
			(if (> argc 1)
				(cond 
					((>= argc 6) (= obstacles theObstacles))
					((not obstacles) (= obstacles (curRoom obstacles?)))
				)
				(if points (points dispose:))
				(= temp30
					(+
						(-
							((= clientPlane (client plane?)) right:)
							(clientPlane left:)
						)
						1
					)
				)
				(= temp31
					(+ (- (clientPlane bottom?) (clientPlane top?)) 1)
				)
				(= temp33
					(AvoidPath
						(theClient x?)
						(theClient y?)
						(= finalX theFinalX)
						(= finalY theFinalY)
						obstacles
						temp30
						temp31
						(if (>= argc 5) param5 else 1)
					)
				)
				((= points (IntArray new:)) copy: temp33)
				(Array 4 temp33)
				(if (> argc 3) (= caller theCaller))
			)
			(self setTarget:)
		)
		(super init:)
	)
	
	(method (dispose)
		(if points (points dispose:))
		(= points 0)
		(super dispose:)
	)
	
	(method (moveDone)
		(if (== (points at: value) 30583)
			(super moveDone:)
		else
			(self setTarget: init:)
		)
	)
	
	(method (setTarget &tmp newIntArray theX theY temp3 temp4 temp5 clientPlane)
		(if (!= (points at: value) 30583)
			(= x (points at: value))
			(= y (points at: (++ value)))
			(++ value)
			(= temp4
				(+
					(-
						((= clientPlane (client plane?)) right:)
						(clientPlane left:)
					)
					1
				)
			)
			(= temp5
				(+ (- (clientPlane bottom?) (clientPlane top?)) 1)
			)
			(if (and altPolyList (altPolyList size:))
				(= newIntArray (IntArray new:))
				(newIntArray
					copy: (AvoidPath
						(client x?)
						(client y?)
						x
						y
						altPolyList
						temp4
						temp5
						0
					)
				)
				(= theX (newIntArray at: 2))
				(= theY (newIntArray at: 3))
				(if (or (!= x theX) (!= y theY))
					(= x theX)
					(= y theY)
					(points at: (+ value 2) 30583)
				)
				(newIntArray dispose:)
			)
		)
	)
)
