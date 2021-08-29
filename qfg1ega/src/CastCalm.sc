;;; Sierra Script 1.0 - (do not remove this comment)
(script# 104)
(include game.sh)
(use Main)
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
	soundObj
)
(procedure (CastCalm onWhat whoCares)
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
		(HandsOn)
		(ego
			loop: 2
			priority: savPriority
			illegalBits: savIllegalBits
			signal: savSignal
		)
		(super dispose:)
		(DisposeScript 104)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((= soundObj (Sound new:))
					number: (SoundFX 39)
					priority: 6
					init:
				)
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(HandsOff)
				(NormalEgo)
				(ego setLoop: 2)
				(= cycles 1)
			)
			(1
				(ego
					view: vEgoMagicDetect
					setLoop: 1
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 2
					setCycle: EndLoop self
				)
				(soundObj play:)
			)
			(2
				(TimePrint 6 104 0)
				;Suddenly a feeling of peace and tranquility permeates the area.
				(= seconds 6)
			)
			(3
				(ego setCycle: BegLoop self)
			)
			(4
				(self dispose:)
			)
		)
	)
)
