;;; Sierra Script 1.0 - (do not remove this comment)
(script# 343)
(include game.sh)
(use n396)
(use Submarine_806)

(public
	proc343_0 0
)

(procedure (proc343_0 param1 param2 param3)
	(return
		(switch param1
			(0
				(if (and (< param2 0) (< 1100 (Submarine depth:)))
					(SubPrint 4 343 0)
					(return 1)
				else
					(return 0)
				)
			)
			(1
				(if
					(and
						(<= 2282 (Submarine depth:))
						(< (Submarine hSpeed?) 5)
						(not (& (Submarine flags?) $0080))
						(& (Submarine flags?) $0040)
					)
					(SubPrint 4 343 1)
					(return 1)
				else
					(return 0)
				)
			)
			(2
				(if
					(and
						(<= 2282 (Submarine depth:))
						(< (Submarine hSpeed?) 5)
						(not (& (Submarine flags?) $0080))
						(& (Submarine flags?) $0040)
						(< param3 -10)
					)
					(SubPrint 4 343 2)
					(return 1)
				else
					(return 0)
				)
			)
			(3
				(if
					(and
						(<= 2282 (Submarine depth:))
						(< (Submarine hSpeed?) 5)
						(not (& (Submarine flags?) $0080))
						(& (Submarine flags?) $0040)
						(< param3 -400)
					)
					(SubPrint 4 343 3)
					(return 1)
				else
					(return 0)
				)
			)
			(else  (return 0))
		)
	)
)
