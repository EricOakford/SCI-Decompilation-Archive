;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTDAZZ)
(include game.sh)
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use System)

(public
	CastDazzle 0
)

(local
	oldSignal
	oldPriority
	oldIllBits
	oldCycleSpeed
	dazzSound
	wasHandsOn
)
(procedure (CastDazzle theClient param2)
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(cond 
		((< 1 argc)
			(theClient setScript: clientCastDazz param2)
		)
		(argc
			(theClient setScript: clientCastDazz)
		)
		(else
			(ego setScript: clientCastDazz)
		)
	)
)

(instance clientCastDazz of Script
	
	(method (dispose)
		(dazzSound stop: dispose:)
		(NormalEgo)
		(if wasHandsOn (HandsOn))
		(ego
			loop: (if (== (ego loop?) 4) 5 else 4)
			priority: oldPriority
			illegalBits: oldIllBits
			signal: oldSignal
			cycleSpeed: oldCycleSpeed
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
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldCycleSpeed (ego cycleSpeed?))
				(HandsOff)
				(ego
					setMotion: 0
					setHeading: (if (OneOf (ego loop?) 2 4 0 6) 135 else 225) self
				)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: 521
					setLoop: (if (== (ego loop?) 5) 4 else 5)
					cel: 0
					cycleSpeed: 4
					setCycle: EndLoop self
				)
				((= dazzSound (Sound new:))
					number: (SoundFX 17)
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
