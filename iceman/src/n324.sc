;;; Sierra Script 1.0 - (do not remove this comment)
(script# 324)
(include game.sh)
(use n396)
(use Class_399_3)

(public
	proc324_0 0
	proc324_1 1
)

(procedure (proc324_0 &tmp i [temp1 25])
	(= i 0)
	(Class_399_3 at: 21 0)
	(Class_399_3 at: 22 0)
	(while (< i 5)
		(if (Class_399_3 at: i)
			(Class_399_3 at: 21 (+ (Class_399_3 at: 21) 1))
			(if
				(or
					(== (Class_399_3 at: 22) 0)
					(< (Class_399_3 at: i) (Class_399_3 at: 22))
				)
				(Class_399_3 at: 22 (Class_399_3 at: i))
			)
		)
		(++ i)
	)
	(return
		(cond 
			((< 1 (Class_399_3 at: 21))
				(switch (Random 0 2)
					(0
						(Format @temp1 324 0 (Class_399_3 at: 21))
					)
					(1
						(Format @temp1 324 1 (Class_399_3 at: 21))
					)
					(2
						(Format @temp1 324 2 (Class_399_3 at: 21))
					)
				)
				(SubPrint 4 @temp1)
				(return 5)
			)
			((Class_399_3 at: 21)
				(switch (Random 0 2)
					(0 (SubPrint 4 324 3))
					(1 (SubPrint 4 324 4))
					(2 (SubPrint 4 324 5))
				)
				(return 5)
			)
			(else (return 1))
		)
	)
)

(procedure (proc324_1 &tmp [temp0 25])
	(return
		(if (Class_399_3 at: 21)
			(cond 
				((< (Class_399_3 at: 22) 100) (Format @temp0 324 6 (Random 5 15)) (SubPrint 3 @temp0))
				((< (Class_399_3 at: 21) 2)
					(Format @temp0 324 7 (* (Class_399_3 at: 22) 7))
					(SubPrint 3 @temp0)
				)
				(else
					(Format @temp0 324 8 (* (Class_399_3 at: 22) 7))
					(SubPrint 3 @temp0)
				)
			)
			(return 4)
		else
			(return 1)
		)
	)
)
