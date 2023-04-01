;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTDAZZ)
(include game.sh)
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use System)

(public
	CastDazz 0
)

(local
	savSignal
	savPriority
	savIllegalBits
	savSpeed
	soundObj
	wasHandsOn
)
(procedure (CastDazz onWhat whoCares)
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(cond 
		((< 1 argc)
			(onWhat setScript: clientCastDazz whoCares)
		)
		(argc
			(onWhat setScript: clientCastDazz)
		)
		(else
			(ego setScript: clientCastDazz)
		)
	)
)

(instance clientCastDazz of Script
	(method (dispose)
		(soundObj stop: dispose:)
		(NormalEgo)
		(if wasHandsOn
			(HandsOn)
		)
		(ego
			loop: (if (== (ego loop?) facingSE) 5 else 4)
			priority: savPriority
			illegalBits: savIllegalBits
			signal: savSignal
			cycleSpeed: savSpeed
		)
		(super dispose:)
		(DisposeScript CASTDAZZ)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= ticks 10)
			)
			(1
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(= savSpeed (ego cycleSpeed?))
				(HandsOff)
				(ego
					setMotion: 0
					setHeading: (if (OneOf (ego loop?) facingSouth facingSE facingEast facingNE) 135 else 225) self
				)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: vEgoCastSpell
					setLoop: (if (== (ego loop?) facingSW) 4 else 5)
					cel: 0
					cycleSpeed: 4
					setCycle: EndLoop self
				)
				((= soundObj (Sound new:))
					number: (SoundFX sDazzle)
					priority: 6
					init:
					play:
				)
			)
			(3
				(self dispose:)
			)
		)
	)
)
