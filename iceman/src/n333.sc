;;; Sierra Script 1.0 - (do not remove this comment)
(script# 333)
(include game.sh)
(use Class_339_18)

(public
	proc333_0 0
)

(procedure (proc333_0 &tmp i temp1)
	(for ((= i 0)) (< i 6) ((++ i))
		(if (Class_339_16 at: (+ i 12))
			((Class_339_16 at: (+ i 18)) setCel: 10)
			(Class_339_16 at: (+ i 18) 0)
		)
	)
)
