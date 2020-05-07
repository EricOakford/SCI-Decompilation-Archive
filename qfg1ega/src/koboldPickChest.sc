;;; Sierra Script 1.0 - (do not remove this comment)
(script# 116)
(include game.sh)
(use Main)
(use KoboldCave)
(use Motion)

(public
	pickChest 0
)

(instance pickChest of KScript
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 116)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset KOBOLD_CHEST_KNOWN)
				(ego
					view: vEgoThrowing
					setLoop: (if (< ((ScriptID 15 2) x?) (ego x?)) 1 else 0)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 8)
			)
			(1
				(= temp0 (= temp1 1))
				(cond 
					((and register (TrySkill STR tryForceOpenKoboldChest 0))
						(Bset KOBOLD_CHEST_EXPLODED)
						(CenterPrint 116 0)
						;You pry at the sealed lid of the chest, and...
					)
					(register
						(CenterPrint 116 1)
						;You hack and pry at the chest, but you are not strong enough to force its lid.
						(= temp1 0)
					)
					((TrySkill PICK tryPickKoboldChest lockPickBonus)
						(CenterPrint 116 2)
						;You hear a very satisfying "Snick" as your pick catches the lock just right.
						;Then you sense powerful magics dissipating around the lock, and your hands shake as
						;you realize you have narrowly avoided some sort of Magical Doom.
						(= temp0 (= temp1 0)) (Bset KOBOLD_CHEST_UNLOCKED)
					)
					(else
						(CenterPrint 116 3)
						;Your fingers shift slightly, and you feel an odd prickling sensation coming from the lock.  You have a bad feeling about this.
					)
				)
				(if temp0 (AwakenKobold))
				(if temp1
					((ScriptID 15 2) setScript: (ScriptID 15 5))
				else
					(KoboldFight TRUE)
				)
				(self dispose:)
			)
		)
	)
)
