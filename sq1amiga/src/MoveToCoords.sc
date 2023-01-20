;;; Sierra Script 1.0 - (do not remove this comment)
(script# 807)
(include sci.sh)
(use Motion)


(class MoveToY of MoveTo
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
	)
	
	(method (init param1 param2 &tmp temp0 temp1)
		(cond 
			(
				(>
					(= temp0
						(/
							(SinMult
								(param1 heading?)
								(* (= temp1 (Abs (- (param1 y?) param2))) 100)
							)
							(if (Abs (- (CosMult (param1 heading?) 100))) else 1)
						)
					)
					temp1
				)
				(= temp0 temp1)
			)
			((> (Abs temp0) temp1) (= temp0 (- temp1)))
		)
		(super init: param1 (+ temp0 (param1 x?)) param2 &rest)
	)
)

(class MoveToX of MoveTo
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
	)
	
	(method (init param1 param2 &tmp temp0 temp1)
		(cond 
			(
				(>
					(= temp1
						(/
							(-
								(CosMult
									(-
										(param1 heading?)
										(* 180 (> (param1 heading?) 180))
									)
									(* (= temp0 (Abs (- (param1 x?) param2))) 100)
								)
							)
							(if (SinMult (param1 heading?) 100) else 1)
						)
					)
					temp0
				)
				(= temp1 temp0)
			)
			((> (Abs temp1) temp0) (= temp1 (- temp0)))
		)
		(super init: param1 param2 (+ temp1 (param1 y?)) &rest)
	)
)
