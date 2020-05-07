;;; Sierra Script 1.0 - (do not remove this comment)
(script# REST)
(include game.sh)
(use Main)
(use Intrface)
(use Time)

(public
	EgoRests 0
)

(procedure (EgoRests restTime mess)
	(if
		(and
			(<= Day lastRestDay)
			(<= Clock (+ lastRestTime 150))
			(> [egoStats STAMINA] (/ (MaxStamina) 2))
		)
		(HighPrint REST 0)
		;You're too impatient to rest right now.
	else
		(= lastRestDay Day)
		(= lastRestTime Clock)
		(UseStamina (- restTime))
		(UseMana (- (/ restTime 5)))
		(TakeDamage (- (/ (+ restTime 5) 15)))
		(if mess
			(Printf REST 1 restTime)
		)
		;After %d minutes rest, you feel better.
		(AdvanceTime 0 restTime)
	)
	(DisposeScript REST)
)
