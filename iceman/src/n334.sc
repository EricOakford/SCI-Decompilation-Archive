;;; Sierra Script 1.0 - (do not remove this comment)
(script# 334)
(include game.sh)
(use Class_339_18)

(public
	proc334_0 0
)

(procedure (proc334_0 param1 &tmp i temp1)
	(= i (= temp1 0))
	(while (< i 6)
		(if (not (Class_339_16 at: (+ i 12)))
			(Class_339_16 at: (+ i 12) param1)
			(Class_339_16
				at: (+ i 18) (Class_339_16 at: (+ i 33))
			)
			(= i 6)
		)
		(++ i)
	)
)
