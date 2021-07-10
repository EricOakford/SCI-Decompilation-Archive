;;; Sierra Script 1.0 - (do not remove this comment)
(script# 331)
(include game.sh)
(use Class_339_18)
(use n396)
(use Submarine_806)

(public
	proc331_0 0
)

(procedure (proc331_0 param1 param2 &tmp i)
	(= i 0)
	(while (and (Class_339_16 at: (+ i 0)) (< i 6))
		(++ i)
	)
	(if
		(and
			(< i 6)
			(< 50 param1)
			(or
				(& (Submarine flags?) $0080)
				(< (Random 0 99) 40)
				(not param2)
			)
		)
		(if param2
			(switch (Random 0 2)
				(0 (SubPrint 2 331 0))
				(1 (SubPrint 2 331 1))
				(2 (SubPrint 2 331 2))
			)
		)
		(Class_339_16 at: (+ i 0) param1)
		(Class_339_16
			at: (+ i 6) (Class_339_16 at: (+ i 27))
		)
	)
)
