;;; Sierra Script 1.0 - (do not remove this comment)
(script# SLEEP)
(include game.sh)
(use Main)

(public
	EgoSleeps 0
	NeedSleep 1
)

(procedure (EgoSleeps theHour theMin &tmp sleptHours oldTime)
	(if (not (NeedSleep))
		(HighPrint SLEEP 0)
		;You just can't sleep during the daytime.
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
			(or
				;safe places to sleep
				(== curRoomNum 10)
				(== curRoomNum 40)
				(== curRoomNum 141)
				(== curRoomNum 76)
				(== curRoomNum 83)
				(== curRoomNum 302)
				(== curRoomNum 300)
				(== curRoomNum 310)
				(== curRoomNum 320)
				(== curRoomNum 330)
			)
			(= sleptHours
				(/
					(mod (- (+ Clock GAMEDAY) oldTime) GAMEDAY)
					GAMEHOUR
				)
			)
			(= [egoStats STAMINA] (MaxStamina))
			(if (== curRoomNum 10)
				(= [egoStats HEALTH] (MaxHealth))
				(= [egoStats MANA] (MaxMana))
			else
				(TakeDamage (- (* sleptHours 2)))
				(UseMana (- (* sleptHours 2)))
			)
			(if (> oldTime Clock)
				(NextDay)
			)
			(if
				(not
					(if
						(or
							(== curRoomNum 300)
							(== curRoomNum 302)
							(== curRoomNum 310)
							(== curRoomNum 320)
						)
					else
						(== curRoomNum 330)
					)
				)
				(HighPrint SLEEP 1)
				;You awake as the sun begins to rise.
			)
		else
			(EgoDead SLEEP 2
				#icon vIcons 1 0
				#title {Night Gaunt Got ya.}
				;While you were asleep, something decided to make a meal of you.
				;You're not sure what it was, but you don't really care at this point.
				;You shouldn't go to sleep where the creatures of the night can get you.
			)
		)
	)
	(DisposeScript SLEEP)
)

(procedure (NeedSleep)
	(return (if (>= timeODay TIME_SUNSET) else lostSleep))
)
