;;; Sierra Script 1.0 - (do not remove this comment)
(script# 323)
(include game.sh)
(use n396)
(use Class_399_3)

(public
	proc323_0 0
)

(procedure (proc323_0 &tmp i temp1)
	(= i (= temp1 0))
	(while (< i 5)
		(if
			(and
				(Class_399_3 at: i)
				(or
					(== temp1 0)
					(< (Class_399_3 at: i) (Class_399_3 at: temp1))
				)
			)
			(= temp1 i)
		)
		(++ i)
	)
	(return
		(if (Class_399_3 at: i)
			(Class_399_3 at: temp1 0)
			((Class_399_3 at: (+ temp1 5)) dispose:)
			(Class_399_3 at: (+ temp1 5) 0)
			(switch (Random 0 1)
				(0 (SubPrint 5 323 0))
				(1 (SubPrint 5 323 1))
			)
			(return 6)
		else
			(return 1)
		)
	)
)
