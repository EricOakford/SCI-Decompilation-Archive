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
	local0
	local1
)
(instance egoThrust of Script
	(properties)
	
	(method (init)
		(= local0 (ScriptID WARRIOR 0))
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
				(= local1 (local0 tryAttack: (local0 opponent?)))
				(if
					(and
						(or (== monsterNum vMinotaur) (== monsterNum vBrigand))
						(== ((local0 opponent?) action?) 3)
					)
					(= local1 0)
				)
				(if local1
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
					(local0
						getTired: 4
						canFight: 0
						action: 1
						setLoop: register
						setPri: (if (== monsterNum 435) 1 else 9)
						setCel: 1
					)
				)
				(= cycles 8)
			)
			(1
				(if local1
					(= local1 0)
					(local0 doDamage: (local0 opponent?) zapPower)
					(= zapPower 0)
				)
				(local0
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
