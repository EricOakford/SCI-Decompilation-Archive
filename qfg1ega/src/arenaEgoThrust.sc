;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_THRUST) ;151
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoThrust 0
)

(local
	theWarrior
	hitOpponent
)
(instance egoThrust of Script
	(method (init)
		(= theWarrior (ScriptID WARRIOR 0))
		(super init: &rest)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript ARENA_THRUST)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoCanFight FALSE)
				(TrySkill WEAPON 0 0)
				(= hitOpponent (theWarrior tryAttack: (theWarrior opponent?)))
				(if
					(and
						(or (== monsterNum vMinotaur) (== monsterNum vBrigand))
						(== ((theWarrior opponent?) action?) ActParryUp)
					)
					(= hitOpponent FALSE)
				)
				(if hitOpponent
					(if
						(or
							(== monsterNum vCheetaur)
							(== monsterNum vBear)
							(== monsterNum vSaurus)
							(== monsterNum vMinotaur)
							(== monsterNum vBrigand)
						)
						(= register 3)
					)
					(theWarrior
						getTired: 4
						canFight: FALSE
						action: ActThrust
						setLoop: register
						setPri: (if (== monsterNum vMantray) 1 else 9)
						setCel: 1
					)
				)
				(= cycles 8)
			)
			(1
				(if hitOpponent
					(= hitOpponent FALSE)
					(theWarrior doDamage: (theWarrior opponent?) zapPower)
					(= zapPower 0)
				)
				(theWarrior
					setPri: 11
					setLoop: register
					setCycle: CycleTo 0 1 self
				)
			)
			(2
				(= egoCanFight TRUE)
				(self dispose:)
			)
		)
	)
)
