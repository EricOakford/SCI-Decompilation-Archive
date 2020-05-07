;;; Sierra Script 1.0 - (do not remove this comment)
(script# 117)
(include game.sh)
(use Main)
(use KoboldCave)
(use Motion)

(public
	startFight 0
	stopFight 1
)

(instance startFight of KScript
	(properties)
	
	(method (dispose)
		(= fightingKoboldStart FALSE)
		(super dispose:)
		(DisposeScript 117)
	)
	
	(method (changeState newState &tmp theEgoKoboldBattleLoop)
		(switch (= state newState)
			(0
				(= fightingKoboldStart TRUE)
				(= fightingKobold 0)
				(HandsOff)
				(ChangeGait MOVE_WALK 0)
				(ego illegalBits: 0)
				(if
					;chance of hitting kobold is minimum 5, maximum 80 (assuming all skills are maxed at 100)
					;set koboldEvade to minimum of 5, or (8*weapon use + 4*agility + 2*str + intelligence + luck)/16 - 20
					(<
						(= koboldEvade
							(-
								(/
									(+
										(* [egoStats WEAPON] 8)
										(* [egoStats AGIL] 4)
										(* [egoStats STR] 2)
										[egoStats INT]
										[egoStats LUCK]
									)
									16
								)
								20
							)
						)
						5
					)
					(= koboldEvade 5)
				)
				(= damageToKobold (+ 9 (/ [egoStats STR] 10)))
				(if (ego has: iSword) (= damageToKobold (+ damageToKobold 3)))
				(if (< (ego y?) 105)
					(self cue:)
				else
					(ego setMotion: MoveTo 160 (ego y?) self)
				)
			)
			(1
				(if egoKoboldBattleLoop
					(ego setMotion: MoveTo 111 96 self)
				else
					(ego setMotion: MoveTo 172 94 self)
				)
			)
			(2
				(= theEgoKoboldBattleLoop egoKoboldBattleLoop)
				(if (not (ego has: iSword))
					(= theEgoKoboldBattleLoop (+ theEgoKoboldBattleLoop 2))
				)
				(ego
					view: vEgoBeginFight
					setLoop: theEgoKoboldBattleLoop
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(3
				(client setScript: (ScriptID 15 8))
			)
		)
	)
)

(instance stopFight of KScript
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 117)
	)
	
	(method (changeState newState &tmp theEgoKoboldBattleLoop)
		(switch (= state newState)
			(0
				(HandsOff)
				(= fightingKobold FALSE)
				(= theEgoKoboldBattleLoop egoKoboldBattleLoop)
				(if (not (ego has: iSword))
					(= theEgoKoboldBattleLoop (+ theEgoKoboldBattleLoop 2))
				)
				(ego
					view: vEgoBeginFight
					setLoop: theEgoKoboldBattleLoop
					cel: (ego lastCel:)
					setCycle: BegLoop self
				)
			)
			(1
				(NormalEgo)
				(ego loop: egoKoboldBattleLoop)
				(if (not (Btst DEFEATED_KOBOLD)) (ego illegalBits: koboldIllBits))
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
