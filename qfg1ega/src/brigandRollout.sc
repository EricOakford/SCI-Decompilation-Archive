;;; Sierra Script 1.0 - (do not remove this comment)
(script# 226)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use System)

(public
	rollout 0
)

(instance rollout of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 226)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (Btst FLAG_264) ((ScriptID 96 8) setCycle: BegLoop))
				(if (not (Btst PULLED_CHAIN))
					((ScriptID 96 5) setLoop: 4 setCycle: Forward)
				)
				((ScriptID 96 9) setCycle: EndLoop self)
			)
			(1
				((ScriptID 96 16)
					number: (SoundFX 83)
					loop: 1
					priority: 2
					play:
				)
				(ego edgeHit: 0)
				(ego
					view: vEgoJesterRoom
					setLoop: 4
					setPri: 3
					illegalBits: 0
					posn: 190 63
					cycleSpeed: 0
					moveSpeed: 0
					setStep: 6 4
					setCycle: Forward
					setMotion: MoveTo 213 78 self
				)
			)
			(2
				(if (Btst FLAG_264)
					(Bclr FLAG_264)
					((ScriptID 96 8) stopUpd:)
					((ScriptID 96 12) dispose:)
				)
				(User canInput: TRUE)
				(if (not (Btst PULLED_CHAIN)) ((ScriptID 96 5) setLoop: 3))
				(ego setPri: 11 setMotion: MoveTo 268 114 self)
			)
			(3
				(User canInput: FALSE)
				((ScriptID 96 9) setCycle: BegLoop)
				(ego setMotion: MoveTo 294 129 self)
			)
			(4
				((ScriptID 96 16)
					number: (SoundFX 9)
					loop: 1
					priority: 2
					play:
				)
				((ScriptID 96 2) setCel: 1)
				(if (not (Btst PULLED_CHAIN)) ((ScriptID 96 5) setLoop: 4))
				(ego yStep: 20 setMotion: MoveTo 325 200)
				(= cycles 20)
			)
			(5
				(if (not (TakeDamage 5))
					(EgoDead 226 1
						#title {You're the Fall Guy again}
						#icon vEgoClimbing 2 5)
						;You're mad as heck, and you just won't take it anymore.  As a matter of fact, you CAN'T take it anymore.
						;Keep up your strength and health and try again.
				else
					((ScriptID 96 2) setCel: 0)
					(self changeState: 0)
				)
			)
			(6
				((ScriptID 96 16)
					number: (SoundFX 87)
					loop: -1
					priority: 2
					play:
				)
				(ego
					setLoop: 5
					cel: 0
					setStep: 3 2
					setMotion: MoveTo 278 119 self
					cycleSpeed: 2
					setCycle: CycleTo 2 1
				)
				((ScriptID 96 9) setCycle: BegLoop)
			)
			(7
				((ScriptID 96 16) stop:)
				(ego y: (+ (ego y?) 14) setCel: 3)
				(= cycles 15)
			)
			(8
				(ego setCel: 4)
				((ScriptID 96 9) stopUpd:)
				(if (not (Btst PULLED_CHAIN))
					((ScriptID 96 5) setLoop: 0 cel: 0 setCycle: 0 stopUpd:)
					((ScriptID 96 6) setCel: 1)
				)
				(= cycles 4)
			)
			(9
				(HandsOn)
				(ego setLoop: 1)
				(NormalEgo)
				(Bclr FLAG_260)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent pEvent)
		(switch (pEvent type?)
			(saidEvent
				((ScriptID 96 16) stop:)
				(if (Said 'cease,grab,stand,halt,n')
					(if (< state 6)
						(= cycles 0)
						(self changeState: 6)
					else
						(pEvent claimed: 1)
					)
				else
					((ScriptID 96 16) play:)
				)
				(if (Said 'cast')
					(HighPrint 226 0)
					;NOT NOW!!
					)
			)
		)
	)
)
