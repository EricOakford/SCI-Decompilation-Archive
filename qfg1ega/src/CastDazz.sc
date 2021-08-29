;;; Sierra Script 1.0 - (do not remove this comment)
(script# 106)
(include game.sh)
(use Main)
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
	soundObj
)
(procedure (CastDazz onWhat whoCares)
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
		(HandsOn)
		(super dispose:)
		(DisposeScript 106)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoMagicDetect
					setLoop: (if (or (== (ego loop?) 1) (== (ego loop?) 3))
						2
					else
						3
					)
				)
				((= soundObj (Sound new:))
					number: (SoundFX 17)
					priority: 6
					init:
					play:
				)
				(ego cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1
				(= cycles 2)
			)
			(2
				(soundObj stop: dispose:)
				(NormalEgo)
				(ego
					loop: 2
					priority: savPriority
					illegalBits: savIllegalBits
					signal: savSignal
				)
				(self dispose:)
			)
		)
	)
)
