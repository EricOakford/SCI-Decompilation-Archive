;;; Sierra Script 1.0 - (do not remove this comment)
(script# 105)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use System)

(public
	CastOpen 0
)

(local
	savSignal
	savPriority
	savIllegalBits
	soundObj
)
(procedure (CastOpen onWhat whoCares &tmp obj prScript)
	(= obj ego)
	(= prScript 0)
	(if argc
		(if (> argc 1)
			(= prScript whoCares)
		)
		(= obj onWhat)
	)
	(obj setScript: clientCastOpen prScript)
)

(instance clientCastOpen of Script
	(method (dispose)
		(HandsOn)
		(super dispose:)
		(DisposeScript 105)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(HandsOff)
				(NormalEgo)
				(ego setLoop: loopS)
				(= cycles 1)
			)
			(1
				(ego
					view: vEgoMagicDetect
					setLoop: loopE
					setCel: 0
					setPri: (ego priority?)
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(2
				((= soundObj (Sound new:))
					number: (SoundFX 35)
					priority: 6
					init:
					play:
				)
				(= cycles 3)
			)
			(3
				(soundObj stop: dispose:)
				(NormalEgo)
				(ego
					loop: loopS
					priority: savPriority
					illegalBits: savIllegalBits
					signal: savSignal
				)
				(self dispose:)
			)
		)
	)
)
