;;; Sierra Script 1.0 - (do not remove this comment)
(script# 223)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	tooGood 0
	tooTired 1
)

(instance tooGood of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 223)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset STOP_FIGHTING_WEAPONMASTER)
				((ScriptID 39 1) stop:)
				(= cycles 2)
			)
			(1
				(HighPrint 223 0)
				;"Enough!" cries the Weapon Master.
				((ScriptID 221 0)
					view: vWeaponMasterTalk
					setLoop: (if (client fightLeft?) 5 else 4)
					setCel: 0
				)
				(= cycles 6)
			)
			(2
				(SkillUsed LUCK tryStatWeaponMaster)
				(if (== heroType FIGHTER) (SolvePuzzle POINTS_DEFEATWEAPONMASTER 10 FIGHTER))
				(HighPrint 223 1)
				;"I can see now that my career must end.
				;Anytime that I, the Weapon Master, can be beaten by a mindless, nasty little twerp such as you, it is time to take an interminable vacation."
				(HighPrint 223 2)
				;"By your leave, I hope your sword rusts and your cape fragments in the most awful manner!"
				((ScriptID 221 0)
					setLoop: (if (== ((ScriptID 221 0) loop?) 4) 1 else 0)
					cycleSpeed: 0
					moveSpeed: 0
					setCycle: Walk
					setMotion:
						MoveTo
						(if ((ScriptID 221 0) fightLeft?) 340 else -20)
						((ScriptID 221 0) y?)
						self
				)
				(Bclr FLAG_239)
				(Bset DEFEATED_WEAPON_MASTER)
			)
			(3
				(Bclr STOP_FIGHTING_WEAPONMASTER)
				(NormalEgo)
				(ego
					posn: ((client opponent?) x?) ((client opponent?) y?)
					loop: (if ((ScriptID 221 0) fightLeft?) 0 else 1)
					cel: 0
					show:
				)
				(HandsOn)
				((client opponent?) dispose:)
				(client dispose:)
			)
		)
	)
)

(instance tooTired of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 223)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset STOP_FIGHTING_WEAPONMASTER)
				((ScriptID 39 1) stop:)
				(= cycles 2)
			)
			(1
				(client
					view: vEgoSwordDefeated
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: Forward
					startUpd:
				)
				(= cycles 15)
			)
			(2
				(if (< [egoStats STAMINA] 2)
					(HighPrint 223 3)
					;The lesson wears you down, and you become exhausted.
				else
					(HighPrint 223 4)
					;You are forced to admit that the Weapon Master has gotten the better of you.
				)
				(= cycles 15)
			)
			(3
				(client setLoop: 1 cel: 0 setCycle: EndLoop)
				(= cycles 20)
			)
			(4
				(client view: vEgoDefeated setLoop: 4 cel: 0 setCycle: EndLoop self)
			)
			(5
				(if (client endFight?)
					((client opponent?) setScript: (ScriptID 224 0))
				)
				(ego
					loop: (if (client fightLeft?) 1 else 0)
					posn: (client x?) (client y?)
					show:
				)
				(NormalEgo)
				(Bclr STOP_FIGHTING_WEAPONMASTER)
				(client dispose:)
			)
		)
	)
)
