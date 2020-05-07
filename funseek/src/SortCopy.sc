;;; Sierra Script 1.0 - (do not remove this comment)
(script# 984)
(include game.sh)
(use System)

(public
	SortedAdd 0
)

(procedure (SortedAdd param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 newEventHandler temp11 temp12 newEventHandler_2 temp14 temp15 newEventHandler_3)
	((= newEventHandler (EventHandler new:))
		add:
		name: {fl}
	)
	((= newEventHandler_2 (EventHandler new:))
		add:
		name: {ol}
	)
	((= newEventHandler_3 (EventHandler new:))
		add:
		name: {bl}
	)
	((= temp1 [param2 0])
		add: newEventHandler newEventHandler_2 newEventHandler_3
	)
	(-- argc)
	(repeat
		(= temp8 (= temp11 (= temp14 0)))
		(= temp9 (= temp12 (= temp15 32767)))
		(= temp0 1)
		(while (< temp0 argc)
			(= temp2 [param2 temp0])
			(= temp3 (FirstNode (temp2 elements?)))
			(while
			(and temp3 (IsObject (= temp5 (NodeValue temp3))))
				(if
					(or
						(and
							(temp5 respondsTo: #signal)
							(& (temp5 signal?) $0088)
						)
						(temp1 firstTrue: #contains temp5)
					)
				else
					(= temp4 (param1 distanceTo: temp5))
					(= temp6
						(__proc982_2
							(param1 heading?)
							(GetAngle
								(param1 x?)
								(param1 y?)
								(temp5 x?)
								(temp5 y?)
							)
						)
					)
					(if (== (umod temp6 90) 0) (-- temp6))
					(if (= temp7 (__proc982_1 temp5 param1))
						(= temp4 (TimesTan temp6 temp4))
					else
						(if (> (Abs temp6) 90)
							(= temp6 89)
							(= temp4 (* temp4 10))
						)
						(= temp4 (Abs (TimesCot temp6 temp4)))
					)
					(if (< temp4 0) (= temp4 32767))
					(cond 
						(temp7
							(if (<= temp4 temp15)
								(= temp15 temp4)
								(= temp14 temp5)
							)
						)
						((__proc982_0 temp5)
							(if (<= temp4 temp12)
								(= temp12 temp4)
								(= temp11 temp5)
							)
						)
						((<= temp4 temp9) (= temp9 temp4) (= temp8 temp5))
					)
				)
				(= temp3 (NextNode temp3))
			)
			(++ temp0)
		)
		(if temp8 (newEventHandler addToEnd: temp8))
		(if temp11 (newEventHandler_2 addToEnd: temp11))
		(if temp14 (newEventHandler_3 addToEnd: temp14))
		(if (or temp8 temp11) else (if temp14 else (break)))
	)
)
