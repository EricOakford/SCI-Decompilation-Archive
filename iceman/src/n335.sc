;;; Sierra Script 1.0 - (do not remove this comment)
(script# 335)
(include game.sh)
(use Class_339_18)
(use n396)

(public
	proc335_0 0
)

(procedure (proc335_0 &tmp i temp1)
	(= i (= temp1 0))
	(while (< i 6)
		(if
			(and
				(Class_339_16 at: (+ i 0))
				(or
					(== temp1 0)
					(<
						(Class_339_16 at: (+ i 0))
						(Class_339_16 at: (+ temp1 0))
					)
				)
			)
			(= temp1 i)
		)
		(++ i)
	)
	(return
		(if (Class_339_16 at: (+ temp1 0))
			(Class_339_16 at: (+ temp1 0) 0)
			((Class_339_16 at: (+ temp1 6)) setCel: 10)
			(Class_339_16 at: (+ temp1 6) 0)
			(switch (Random 0 1)
				(0 (SubPrint 3 335 0))
				(1 (SubPrint 3 335 1))
			)
			(return 1)
		else
			(return 0)
		)
	)
)
