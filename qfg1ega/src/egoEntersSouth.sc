;;; Sierra Script 1.0 - (do not remove this comment)
(script# 273)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoEntersSouth 0
)

(instance egoEntersSouth of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 273)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 235 176 self)
			)
			(1
				(if (Btst fSavedElsa)
					(EgoDead 273 0
						#icon vEgoDefeatedMagic 0 9
						#title {Greedy Greedy Greedy .})
						;The brigands were waiting for you to come back.  They expected you to make another try for some of their treasure.
				else
					((ScriptID 93 0) notify: 0)
					(if (Btst fBrigGateOpen)
						(ego illegalBits: cWHITE)
					else
						(ego illegalBits: (| cWHITE cLRED))
					)
					(if (not (if (Btst fBeenIn93) else (Btst fMinotaurDead)))
						(HighPrint 273 1)
						;"Grumble Grumble Grumble.  Stupid guard duty, third time this week.  Why always me?  Grumble Grumble Grumble."
					)
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)
