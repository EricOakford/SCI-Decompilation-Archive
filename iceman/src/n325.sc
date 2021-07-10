;;; Sierra Script 1.0 - (do not remove this comment)
(script# 325)
(include game.sh)
(use Class_399_3)

(public
	proc325_0 0
)

(procedure (proc325_0 &tmp i temp1)
	(= i 0)
	(while (< i 5)
		(if (Class_399_3 at: (+ i 10))
			((Class_399_3 at: (+ i 15)) dispose:)
			(Class_399_3 at: (+ i 15) 0)
		)
		(++ i)
	)
)
