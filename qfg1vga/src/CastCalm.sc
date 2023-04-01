;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTCALM)
(include game.sh) (include "558.shm")
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use System)

(public
	CastCalm 0
)

(local
	savSignal
	savPriority
	savIllegalBits
	savSpeed
	soundObj
	wasHandsOn
)
(procedure (CastCalm onWhat whoCares)
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(switch argc
		(0
			(ego setScript: castCalm)
		)
		(1
			(onWhat setScript: castCalm)
		)
		(else 
			(onWhat setScript: castCalm whoCares)
		)
	)
)

(instance castCalm of Script
	(method (dispose)
		(soundObj stop: dispose:)
		(NormalEgo)
		(if wasHandsOn (HandsOn))
		(ego
			loop: (if (== (ego loop?) facingSouth) 5 else 4)
			priority: savPriority
			illegalBits: savIllegalBits
			cycleSpeed: savSpeed
			signal: savSignal
		)
		(super dispose:)
		(DisposeScript CASTCALM)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 10))
			(1
				((= soundObj (Sound new:))
					number: (SoundFX sCalm)
					priority: 6
					init:
				)
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(= savSpeed (ego cycleSpeed?))
				(HandsOff)
				(NormalEgo)
				(theGame setCursor: waitCursor TRUE)
				(ego
					setMotion: 0
					setHeading: (if (OneOf (ego loop?) facingSouth facingSE facingEast facingNE) 135 else 225) self
				)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: vEgoCastSpell
					setLoop: (if (== (ego loop?) loopSW) loopS else loopN)
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(soundObj play:)
			)
			(3
				(messager say: N_CALM NULL NULL 0 0 SPELLS)
				(= ticks 12)
			)
			(4
				(self dispose:)
			)
		)
	)
)
