;;; Sierra Script 1.0 - (do not remove this comment)
(script# 807)
(include game.sh)
(use Motion)
(use Actor)


(class SoundSyncWave of Prop
	(properties
		signal (| ignrAct anExtra)
		sound 0
		lastPrevSignal 0
	)
	
	(method (init theSound)
		(super init:)
		(if argc (= sound theSound))
	)
	
	(method (doit)
		(super doit:)
		(if (!= (sound prevSignal?) lastPrevSignal)
			(self
				setCycle: EndLoop self
				lastPrevSignal: (sound prevSignal?)
			)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)
