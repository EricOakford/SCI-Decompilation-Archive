;;; Sierra Script 1.0 - (do not remove this comment)
(script# 234)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	fallSideways 0
	trapFall 1
)

(instance fallSideways of Script
	(method (dispose)
		(Bset fNoMoreTalking)
		(super dispose:)
		(DisposeScript 234)
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
				(ego
					view: vEgoClimbing
					setLoop: (if (Btst fFallingOffLedge) 3 else 2)
					x: (if (Btst fFallingOffLedge) (- (ego x?) 10) else (+ (ego x?) 10))
					cel: 0
					cycleSpeed: 1
					setCycle: CycleTo 2 1 self
				)
				(if (== client (ScriptID 96 3))
					(ego setMotion: MoveTo (+ (ego x?) 12) (ego y?))
				)
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
					yStep: 10
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo (ego x?) 220 self
				)
			)
			(2
				(if (not (TakeDamage 5))
					(EgoDead 234 0
						#title {You're the Fall Guy again}
						#icon vEgoClimbing 2 5)
					;You're mad as heck, and you just won't take it anymore. 
					; As a matter of fact, you CAN'T take it anymore.
					;Keep up your strength and health and try again.
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

(instance trapFall of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(client setCel: 1)
				(if (!= client (ScriptID 96 3))
					(client setPri: (- (client priority?) 1))
				)
				(= cycles 1)
			)
			(1
				(if (== client (ScriptID 96 4))
					(Bset fFallTrap4)
					(self dispose:)
				else
					(client setScript: fallSideways)
				)
			)
		)
	)
)
