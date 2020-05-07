;;; Sierra Script 1.0 - (do not remove this comment)
(script# 335)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	breathScript 0
)

(instance breathScript of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 335)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 331 2)
					setLoop: 6
					setMotion: MoveTo 171 78 self
				)
			)
			(1
				((ScriptID 331 2)
					view: vBartenderPouring
					setLoop: 0
					cel: 0
					posn: 169 78
					setCycle: CycleTo 5 1 self
				)
			)
			(2
				((ScriptID 331 3) hide:)
				((ScriptID 331 4) number: (SoundFX 44) play:)
				((ScriptID 331 1) loop: 2 cel: 0 setCycle: CycleTo 6 1 self)
			)
			(3
				((ScriptID 331 1) setCycle: CycleTo 9 1)
				((ScriptID 331 2) setCycle: EndLoop self)
			)
			(4
				((ScriptID 331 1) setCycle: EndLoop self)
			)
			(5
				((ScriptID 331 1) loop: 3 cel: 0 posn: 159 47)
				(self cue:)
			)
			(6
				((ScriptID 331 4) number: (SoundFX 43) play:)
				((ScriptID 331 1)
					posn: ((ScriptID 331 1) x?) (- ((ScriptID 331 1) y?) 3)
					setCycle: CycleTo (+ ((ScriptID 331 1) cel?) 1) 1 self
				)
			)
			(7
				(if (!= ((ScriptID 331 1) cel?) 12)
					(self changeState: 6)
				else
					(self cue:)
				)
			)
			(8
				((ScriptID 331 1) posn: 159 68 stopUpd:)
				((ScriptID 331 3) show:)
				((ScriptID 331 2) cel: 2 setCycle: BegLoop self)
			)
			(9
				((ScriptID 331 2) view: vBartender setLoop: 1 cel: 0 stopUpd:)
				(if (Btst HERO_SITTING)
					(ego loop: 3 cel: 0)
					((ScriptID 331 5) hide:)
				)
				(= drinkOrdered drinkNothing)
				(= drinkInHand drinkBreath)
				(HighPrint 335 0 83)
				;"There ya go!"
				(self dispose:)
			)
		)
	)
)
