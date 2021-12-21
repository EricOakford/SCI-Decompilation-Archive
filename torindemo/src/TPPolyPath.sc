;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64026)
(include sci.sh)
(use Main)
(use Array)
(use Motion)


(class TPPolyPath of Motion
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
	
	(method (init theClient &tmp temp0 temp1 clientPlane temp3)
		(if argc
			(= client theClient)
			(if (>= argc 2)
				(self nBaseTilesX:)
				(self md: theClient &rest)
			)
			(self mn:)
			(= temp0
				(+
					(-
						((= clientPlane (client plane?)) right:)
						(clientPlane left:)
					)
					1
				)
			)
			(= temp1
				(+ (- (clientPlane bottom?) (clientPlane top?)) 1)
			)
			(= temp3
				(AvoidPath
					(client x?)
					(client y?)
					finalX
					finalY
					(if obstacles else (curRoom obstacles?))
					temp0
					temp1
					1
				)
			)
			(= value 2)
			(if points (points dispose:))
			((= points (IntArray new:))
				name: LOOKUP_ERROR
				copy: temp3
			)
			(KArray 4 temp3)
			(self setTarget:)
		)
		(super init:)
	)
	
	(method (doit)
		(if (self onTarget:)
			(self moveDone:)
		else
			(super doit:)
		)
	)
	
	(method (dispose)
		(if points (points dispose:) (= points 0))
		(if obstacles (= obstacles 0))
		(super dispose:)
	)
	
	(method (moveDone)
		(if (!= (points at: value) 30583)
			(self setTarget: init:)
		else
			(super moveDone:)
		)
	)
	
	(method (setTarget &tmp newIntArray theX theY temp3 temp4 temp5 clientPlane)
		(if (!= (points at: value) 30583)
			(= x (points at: value))
			(= y (points at: (++ value)))
			(++ value)
			(if (and altPolyList (altPolyList size:))
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
				(= newIntArray (IntArray new:))
				(newIntArray name: LOOKUP_ERROR)
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
	
	(method (nBaseTilesX)
		(if points (points dispose:))
		(= points 0)
		(= value 2)
		(= finalY (= finalX 0))
	)
	
	(method (mn)
		(MonoOut LOOKUP_ERROR)
	)
	
	(method (md)
		(MonoOut LOOKUP_ERROR)
	)
)
