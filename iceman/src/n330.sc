;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include game.sh)
(use Class_339_18)
(use n396)

(public
	proc330_0 0
)

(procedure (proc330_0 &tmp [str 25])
	(if (Class_339_16 at: 25)
		(cond 
			((< (Class_339_16 at: 26) 50) (Format @str 330 0 (Random 100 300)))
			((< (Class_339_16 at: 25) 2) (Format @str 330 1 (* (Abs (Class_339_16 at: 26)) 7)))
			(else (Format @str 330 2 (* (Abs (Class_339_16 at: 26)) 7)))
		)
		(SubPrint 3 @str)
	)
	(return 4)
)
