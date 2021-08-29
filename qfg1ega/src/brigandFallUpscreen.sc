;;; Sierra Script 1.0 - (do not remove this comment)
(script# 225)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	fallUpscreen 0
	fallDownscreen 1
)

(instance fallUpscreen of Script
	(method (dispose)
		(Bset fNoMoreTalking)
		(super dispose:)
		(DisposeScript 225)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fPulledChain))
					((ScriptID 96 6) setCel: 3)
					((ScriptID 96 5)
						setLoop: 4
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
					)
				)
				(ego view: vEgoFall2 setLoop: 0 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(1
				((ScriptID 96 16)
					number: (SoundFX 9)
					loop: 1
					priority: 2
					play:
				)
				(ego
					setPri: (- (ego priority?) 1)
					yStep: 6
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) 220 self
				)
			)
			(2
				(if (not (TakeDamage 5))
					(EgoDead 225 0
						#title {You're the Fall Guy again.}
						#icon vEgoClimbing 2 5
					)
						;You're mad as heck, and you're not going to take it anymore.
						; As a matter of fact, you CAN'T take it anymore.
						;Restore your strength and health and try again.
				else
					(Bclr fFallingOffLedge)
					(Bclr fOpeningDoor)
					(Bset fRollingOut)
					(if (Btst fFallTrapdoor)
						(Bclr fFallTrapdoor)
						(ego setScript: (ScriptID 226 0))
						(if (== client (ScriptID 96 4))
							(client setPri: (+ (client priority?) 1))
						)
						(client setCel: 0 setScript: 0)
					else
						(ego setScript: (ScriptID 226 0))
					)
				)
			)
		)
	)
)

(instance fallDownscreen of Script
	(method (dispose)
		(Bset fNoMoreTalking)
		(super dispose:)
		(DisposeScript 225)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (not (Btst fPulledChain))
					((ScriptID 96 6) setCel: 3)
					((ScriptID 96 5)
						setLoop: 4
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
					)
				)
				(ego view: vEgoShock setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(1
				((ScriptID 96 16)
					number: (SoundFX 9)
					loop: 1
					priority: 2
					play:
				)
				(ego
					setPri: (+ (ego priority?) 1)
					yStep: 12
					illegalBits: 0
					setMotion: MoveTo (ego x?) 220 self
				)
			)
			(2
				(if (not (TakeDamage 5))
					(EgoDead 225 1
						#title {You're the Fall Guy again.}
						#icon vEgoClimbing 2 5)
						;You're mad as heck, and you just won't take it anymore. 
						; As a matter of fact, you CAN'T take it anymore.
						;Start over, and remember to keep up your strength and health.
				else
					(Bclr fFallingOffLedge)
					(Bclr fOpeningDoor)
					(Bset fRollingOut)
					(if (Btst fFallTrapdoor)
						(Bclr fFallTrapdoor)
						(ego setScript: (ScriptID 226 0))
						(client setCel: 0 setScript: 0)
					else
						(ego setScript: (ScriptID 226 0))
					)
				)
			)
		)
	)
)
