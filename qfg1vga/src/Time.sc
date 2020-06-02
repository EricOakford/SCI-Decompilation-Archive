;;; Sierra Script 1.0 - (do not remove this comment)
(script# TIME) ;813
(include game.sh)
(use Main)
(use Procs)

(public
	AdvanceTime 0
)

(procedure (AdvanceTime addHours addMinutes &tmp temp0 newTime)
	(= temp0 (if (<= Clock 2850) (<= 2850 900) else 0))
	(switch argc
		(1
			(= newTime (+ Clock (* GAMEHOUR addHours)))
		)
		(2
			(= newTime
				(+ Clock (* GAMEHOUR addHours) (/ (* GAMEHOUR addMinutes) 60))
			)
		)
	)
	(= newTime (^ newTime 1))
	(if
		(or
			(and (< Clock 1050) (>= newTime 1050))
			(and
				(< Clock 2400)
				(or (>= newTime 2400) (< newTime Clock))
			)
		)
		(EatMeal)
	)
	(while (>= newTime GAMEDAY)
		(= newTime (- newTime GAMEDAY))
		(NextDay)
	)
	(FixTime (/ newTime GAMEHOUR) (/ (* (mod newTime GAMEHOUR) 60) GAMEHOUR))
)
