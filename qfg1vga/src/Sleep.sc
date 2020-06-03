;;; Sierra Script 1.0 - (do not remove this comment)
(script# SLEEP)
(include game.sh) (include "7.shm")
(use Main)
(use Rest)
(use Procs)
(use Print)
(use System)

(public
	EgoSleeps 0
	NeedSleep 1
	SleepChoice 2
)

(enum 1
	rest10Minutes
	rest30Minutes
	rest60Minutes
	sleepUntilMorning
	stayAwake
)

(procedure (EgoSleeps theHour theMin &tmp sleptHours oldTime)
	(if (not (NeedSleep))
		(messager say: N_HOWLONG NULL NULL 6 0 SLEEP)
	else
		(= lostSleep 0)
		(= oldTime Clock)
		(switch argc
			(0
				(FixTime 5)
			)
			(1
				(FixTime theHour)
			)
			(else
				(FixTime theHour theMin)
			)
		)
		(if
		(OneOf curRoomNum 10 40 141 76 83 302 300 310 320 330)	;safe sleep rooms
			(= sleptHours
				(/ (mod (- (+ Clock GAMEDAY) oldTime) GAMEDAY) GAMEHOUR)
			)
			(= [egoStats STAMINA] (MaxStamina))
			(if (== curRoomNum 10)
				(= [egoStats HEALTH] (MaxHealth))
				(= [egoStats MANA] (MaxMana))
			else
				(TakeDamage (- (* sleptHours 2)))
				(UseMana (- (* sleptHours 2)))
			)
			(if (> oldTime Clock) (NextDay))
			(if (not (OneOf curRoomNum 300 302 310 320 330))	;town rooms
				(messager say: N_HOWLONG NULL NULL 7 0 SLEEP)
			)
		else
			(EgoDead C_DIE_NIGHT_GAUNT C_DIE_NIGHT_GAUNT_TITLE)
		)
	)
)

(procedure (NeedSleep)
	(return (if (>= timeODay TIME_SUNSET) else lostSleep))
)

(procedure (SleepChoice &tmp evt)
	(return
		(switch
			(Print
				addText: N_HOWLONG NULL C_REST 1 0 0 SLEEP
				addButton: rest10Minutes N_HOWLONG NULL NULL 1 0 20 SLEEP
				addButton: rest30Minutes N_HOWLONG NULL NULL 2 0 40 SLEEP
				addButton: rest60Minutes N_HOWLONG NULL NULL 3 0 60 SLEEP
				addButton: sleepUntilMorning N_HOWLONG NULL NULL 4 0 80 SLEEP
				addButton: stayAwake N_HOWLONG NULL NULL 5 0 100 SLEEP
				init:
			)
			(rest10Minutes
				(EgoRests 10 1)
			)
			(rest30Minutes
				(EgoRests 30 1)
			)
			(rest60Minutes
				(EgoRests 60 1)
			)
			(sleepUntilMorning
				(cond 
					((not (NeedSleep)) (messager say: N_HOWLONG NULL NULL 6 0 SLEEP))
					(
						(OneOf curRoomNum
							10 13 29 30 31 38 39 40 41 76 83
							93 94 141 300 301 310 311 320 21 330 332
						)
						((= evt (Event new:)) type: mouseDown message: V_SLEEP)
						(if (not (mouseDownHandler handleEvent: evt))
							(regions handleEvent: evt)
						)
						(evt dispose:)
						(return TRUE)
					)
					(else
						(EgoSleeps 5 0)
					)
				)
			)
			(stayAwake
				(return TRUE)
			)
		)
	)
)
