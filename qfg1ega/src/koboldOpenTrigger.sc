;;; Sierra Script 1.0 - (do not remove this comment)
(script# 115)
(include game.sh)
(use Main)
(use KoboldCave)
(use Motion)

(public
	castOpen 0
	castTrig 1
)

(instance castOpen of KScript
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 115)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoMagicDetect
					setLoop: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(Bset KOBOLD_CHEST_KNOWN)
				(if (TrySkill OPEN tryCastOpenKoboldChest 0)
					(self cue:)
				else
					((ScriptID 15 2) setScript: (ScriptID 15 5))
					(self dispose:)
				)
			)
			(2
				(Bset KOBOLD_CHEST_UNLOCKED)
				(CenterPrint 115 0)
				;The invisible lid of the invisible chest lifts.
				;A ripple of magical heat in the air informs you of a trap narrowly avoided by your skillful use of the Open spell.
				(KoboldFight TRUE)
				(Face ego (ScriptID 15 2))
				(self dispose:)
			)
		)
	)
)

(instance castTrig of KScript
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 115)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoMagicDetect
					setLoop: 1
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(Bset KOBOLD_CHEST_KNOWN)
				((ScriptID KOBOLD 2) setScript: (ScriptID KOBOLD 5))
				(self dispose:)
			)
		)
	)
)
