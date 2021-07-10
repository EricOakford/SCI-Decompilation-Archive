;;; Sierra Script 1.0 - (do not remove this comment)
(script# 322)
(include game.sh)
(use Class_399_3)

(public
	proc322_0 0
)

(procedure (proc322_0 param1 &tmp i temp1)
	(= i (= temp1 0))
	(while (< i 5)
		(if (not (Class_399_3 at: (+ i 10)))
			(Class_399_3 at: (+ i 10) (Abs param1))
			(Class_399_3
				at:
					(+ i 15)
					(((Class_399_3 at: 20) new:)
						init:
						loop: 0
						cel: 10
						x: 119
						y: (+ 66 (* i 2))
						yourself:
					)
			)
			(= i 5)
		)
		(++ i)
	)
)
