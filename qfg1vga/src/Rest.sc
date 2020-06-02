;;; Sierra Script 1.0 - (do not remove this comment)
(script# REST)
(include game.sh) (include "8.shm")
(use Main)
(use Procs)
(use Print)

(public
	EgoRests 0
)

(procedure (EgoRests restTime mess &tmp [str 25] [minBuf 30] newHour newMins temp57)
	(if
		(and
			(<= Day lastRestDay)
			(<= Clock (+ lastRestTime 75))
			(> [egoStats STAMINA] (/ (MaxStamina) 2))
		)
		(messager say: N_RESTING NULL NULL 1 0 REST)
	else
		(UseStamina (- restTime))
		(UseMana (- (/ restTime 5)))
		(TakeDamage (- (/ (+ restTime 5) 15)))
		(if mess
			(Message MsgGet REST N_RESTING NULL NULL 2 @str)
			(Print
				font: userFont
				mode: teJustCenter
				addTextF: @minBuf @str restTime
				init:
			)
		)
		(= newHour
			(/ (= temp57 (+ Clock (/ (* 15 restTime) 6))) GAMEHOUR)
		)
		(= newMins (mod temp57 GAMEHOUR))
		(FixTime newHour newMins)
		(= lastRestDay Day)
		(= lastRestTime Clock)
	)
)
