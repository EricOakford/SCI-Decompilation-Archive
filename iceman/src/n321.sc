;;; Sierra Script 1.0 - (do not remove this comment)
(script# 321)
(include game.sh)
(use n396)
(use Class_399_3)

(public
	proc321_0 0
)

(procedure (proc321_0 param1 param2 param3 &tmp i)
	(= i 0)
	(while (and (Class_399_3 at: i) (< i 5))
		(++ i)
	)
	(if
		(and
			(< i 5)
			(or
				(== param2 100)
				(and (< (Random 0 99) param2) (< 0 param3))
			)
		)
		(switch (Random 0 2)
			(0 (SubPrint 3 321 0))
			(1 (SubPrint 3 321 1))
			(2 (SubPrint 3 321 2))
		)
		(Class_399_3 at: i (Abs param1))
		(Class_399_3
			at:
				(+ i 5)
				(((Class_399_3 at: 20) new:)
					init:
					loop: 1
					cel: 10
					x: 195
					y: (+ 43 (* i 2))
					yourself:
				)
		)
	)
)
