;;; Sierra Script 1.0 - (do not remove this comment)
(script# 332)
(include game.sh)
(use Class_339_18)
(use n396)

(public
	proc332_0 0
)

(procedure (proc332_0 &tmp temp0 [temp1 25])
	(= temp0 0)
	(Class_339_16 at: 25 0)
	(Class_339_16 at: 26 0)
	(while (< temp0 6)
		(if (Class_339_16 at: (+ temp0 0))
			(Class_339_16 at: 25 (+ (Class_339_16 at: 25) 1))
			(if
				(or
					(== (Class_339_16 at: 26) 0)
					(<
						(Class_339_16 at: (+ temp0 0))
						(Class_339_16 at: 26)
					)
				)
				(Class_339_16 at: 26 (Class_339_16 at: (+ temp0 0)))
			)
		)
		(++ temp0)
	)
	(return
		(cond 
			((< 1 (Class_339_16 at: 25))
				(switch (Random 0 2)
					(0
						(Format @temp1 332 0 (Class_339_16 at: 25))
					)
					(1
						(Format @temp1 332 1 (Class_339_16 at: 25))
					)
					(2
						(Format @temp1 332 2 (Class_339_16 at: 25))
					)
				)
				(SubPrint 3 @temp1)
				(return 4)
			)
			((Class_339_16 at: 25)
				(switch (Random 0 2)
					(0 (SubPrint 3 332 3))
					(1 (SubPrint 3 332 4))
					(2 (SubPrint 3 332 5))
				)
				(return 4)
			)
			(else (return 1))
		)
	)
)
