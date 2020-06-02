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
	oldSignal
	oldPriority
	oldIllBits
	oldCycleSpeed
	calmSound
	wasHandsOn
)
(procedure (CastCalm param1 param2)
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(switch argc
		(0
			(ego setScript: castCalm)
		)
		(1
			(param1 setScript: castCalm)
		)
		(else 
			(param1 setScript: castCalm param2)
		)
	)
)

(instance castCalm of Script
	(properties)
	
	(method (dispose)
		(calmSound stop: dispose:)
		(NormalEgo)
		(if wasHandsOn (HandsOn))
		(ego
			loop: (if (== (ego loop?) 2) 5 else 4)
			priority: oldPriority
			illegalBits: oldIllBits
			cycleSpeed: oldCycleSpeed
			signal: oldSignal
		)
		(super dispose:)
		(DisposeScript CASTCALM)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 10))
			(1
				((= calmSound (Sound new:))
					number: (SoundFX 39)
					priority: 6
					init:
				)
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldCycleSpeed (ego cycleSpeed?))
				(HandsOff)
				(NormalEgo)
				(theGame setCursor: waitCursor TRUE)
				(ego
					setMotion: 0
					setHeading: (if (OneOf (ego loop?) 2 4 0 6) 135 else 225) self
				)
			)
			(2
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: 521
					setLoop: (if (== (ego loop?) 5) 2 else 3)
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 12
					setCycle: EndLoop self
				)
				(calmSound play:)
			)
			(3
				(messager say: N_CASTCALM NULL NULL 0 0 SPELLS)
				(= ticks 12)
			)
			(4
				(self dispose:)
			)
		)
	)
)
