;;; Sierra Script 1.0 - (do not remove this comment)
(script# TIME)
(include game.sh)
(use Main)

(public
	AdvanceTime 0
)

(procedure (AdvanceTime addHours addMinutes &tmp newTime)
	(switch argc
		(1
			(= newTime (+ Clock (* 150 addHours)))
		)
		(2
			(= newTime
				(+ Clock (* 150 addHours) (/ (* 150 addMinutes) 60))
			)
		)
	)
	(^= newTime 1)
	(if
		(or
			(and (< Clock 1100) (>= newTime 1100))
			(and
				(< Clock 2500)
				(or (>= newTime 2500) (< newTime Clock))
			)
		)
		(EatMeal)
	)
	(while (>= newTime GAMEDAY)
		(-= newTime GAMEDAY)
		(NextDay)
	)
	(FixTime
		(/ newTime GAMEHOUR)
		(/ (* (mod newTime GAMEHOUR) 60) GAMEHOUR)
	)
	(DisposeScript TIME)
)
