;;; Sierra Script 1.0 - (do not remove this comment)
(script# 113)
(include game.sh)
(use Main)
(use KoboldCave)
(use Motion)
(use User)

(public
	castDazzle 0
	castDet 1
)

(instance castDazzle of KScript
	(properties)
	
	(method (dispose)
		(KoboldFight TRUE)
		(Face ego theKobold)
		(super dispose:)
		(DisposeScript 113)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (User canControl:))
				(HandsOff)
				(Face ego theKobold)
				(ego
					view: vEgoMagicDetect
					setLoop: (- 3 egoKoboldBattleLoop)
					cel: 0
					cycleSpeed: 2
					setCycle: CycleTo 4 1 self
				)
			)
			(1
				(theKobold setScript: (ScriptID 15 4))
				(ego setCycle: EndLoop)
				(= cycles 6)
			)
			(2
				(ego cycleSpeed: 0)
				(self dispose:)
			)
		)
	)
)

(instance castDet of KScript
	(properties)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
		(DisposeScript 113)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register 0)
				(= cycles 1)
				(if
					(or
						(ego has: iBrassKey)
						[invDropped iBrassKey]
						(cast contains: (ScriptID 15 3))
					)
					(CenterPrint 113 0)
					;A strong magical aura radiates from the Kobold's key.
					(= register 1)
					(if (and (not (Btst DEFEATED_KOBOLD)) (not (Btst OBTAINED_KOBOLD_KEY)))
						(= cycles 0)
						((ScriptID 15 3)
							show:
							view: vKoboldSitting
							setLoop: 8
							cel: 0
							setPri: (theKobold priority?)
							z: 13
							cycleSpeed: 1
							setCycle: EndLoop self
						)
					)
				)
			)
			(1
				(if (not (Btst OBTAINED_KOBOLD_KEY))
					(if (not (Btst DEFEATED_KOBOLD)) ((ScriptID 15 3) hide:))
					((ScriptID 15 3)
						view: vKoboldDead
						setLoop: 6
						setCel: 12
						setPri: (if egoKoboldBattleLoop 5 else -1)
						z: 0
					)
				)
				(= cycles 1)
				(cond 
					((cast contains: (ScriptID 15 2))
						(CenterPrint 113 1)
						;You see the outline of a chest glowing in the south end of the cavern.
						(= register 1)
						(= cycles 0)
						((ScriptID 15 2) cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
					)
					((not (Btst KOBOLD_CHEST_SEARCHED))
						(CenterPrint 113 2)
						;You can detect a faint glimmering of magic from the shattered remains of the chest.
						(= register TRUE))
				)
			)
			(2
				(if (not register)
					(CenterPrint 113 3)
					;There no longer seem to be any magical effects or spells active in this part of the cavern.
					)
				(self dispose:)
			)
		)
	)
)
