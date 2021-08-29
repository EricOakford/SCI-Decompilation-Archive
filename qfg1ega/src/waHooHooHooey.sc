;;; Sierra Script 1.0 - (do not remove this comment)
(script# 138)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	waHooHooHooey 0
)

(instance waHooHooHooey of Script
	(method (dispose)
		(super dispose:)
		(DisposeScript 138)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: vEgoClimbing
					illegalBits: 0
					setLoop: 2
					cel: 0
					x: (- (ego x?) 12)
					setStep: 0 4
					cycleSpeed: 1
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) 106 self
				)
				((ScriptID 82 4)
					number: (SoundFX 9)
					loop: 1
					play:
				)
			)
			(1
				((ScriptID 82 4)
					number: (SoundFX 10)
					loop: 1
					play:
				)
				(if (not (TakeDamage 10))
					(EgoDead 138 0
						#icon vEgoClimbing 2 5
						#title {Your figure remains still and silent.}
					)
					;Had you been healthier, you probably could have survived that fall. 
					; In your weakened condition, however, you succumbed to your injuries.
				else
					(ego
						view: vEgoDefeated
						setLoop: 4
						cel: 0
						x: (+ (ego x?) 11)
						y: (+ (ego y?) 36)
					)
					(self cue:)
				)
			)
			(2
				(ego cycleSpeed: 1 setCycle: EndLoop self)
			)
			(3
				(Bclr fClimbedHenryCliff)
				(if (Btst fEgoKnockedOffCliff)
					(Bclr fEgoKnockedOffCliff)
				else
					(HighPrint 138 1)
					;Man, that's a narrow ledge up there!
				)
				(HandsOn)
				(ego setLoop: 2)
				(NormalEgo)
				(client setScript: 0)
			)
		)
	)
)
