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
	egoSignal
	egoPriority
	egoIllegalBits
	newSound
)
(procedure (CastDazz param1 param2)
	(cond 
		((< 1 argc) (param1 setScript: clientCastDazz param2))
		(argc (param1 setScript: clientCastDazz))
		(else (ego setScript: clientCastDazz))
	)
)

(instance clientCastDazz of Script
	(properties)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
		(DisposeScript 106)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoSignal (ego signal?))
				(= egoPriority (ego priority?))
				(= egoIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoMagicDetect
					setLoop: (if (or (== (ego loop?) 1) (== (ego loop?) 3))
						2
					else
						3
					)
				)
				((= newSound (Sound new:))
					number: (SoundFX 17)
					priority: 6
					init:
					play:
				)
				(ego cel: 0 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(1 (= cycles 2))
			(2
				(newSound stop: dispose:)
				(NormalEgo)
				(ego
					loop: 2
					priority: egoPriority
					illegalBits: egoIllegalBits
					signal: egoSignal
				)
				(self dispose:)
			)
		)
	)
)
