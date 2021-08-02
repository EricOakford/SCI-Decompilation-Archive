;;; Sierra Script 1.0 - (do not remove this comment)
(script# 770)
(include game.sh)
(use System)

(public
	proc770_0 0
)

(procedure (proc770_0 param1 obj param3 &tmp temp0 temp1 temp2)
	(if (> argc 1)
		(= temp0 (= temp1 0))
		(if (= temp2 (IsObject obj))
			(if (RespondsTo obj 4) (= temp0 (obj x?)))
			(if (RespondsTo obj 3)
				(if (> argc 2)
					(if (!= param3 0) (= temp1 (+ (obj y?) param3 32)))
				else
					(= temp1
						(-
							(obj y?)
							(+
								33
								(if (RespondsTo obj 7)
									(/
										(CelHigh (obj view?) (obj loop?) (obj cel?))
										2
									)
								else
									10
								)
							)
						)
					)
				)
			)
		else
			(= temp0 obj)
			(if (> argc 2) (= temp1 param3))
		)
		(Memory MWriteWord (+ param1 2) (Min (Max 0 (- temp0 40)) 239))
		(if
			(or
				(and (< argc 3) temp2)
				(and
					(> argc 2)
					(or (not temp2) (and temp2 (!= param3 0)))
				)
			)
			(Memory MWriteWord
				(+ param1 4)
				(Min (Max 40 (- temp1 33)) 123)
			)
		)
	)
	(DisposeScript 770)
)
