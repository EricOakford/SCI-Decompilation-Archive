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
	egoSignal
	egoPriority
	egoIllegalBits
	newSound
)
(procedure (CastOpen theTheEgo param2 &tmp theEgo temp1)
	(= theEgo ego)
	(= temp1 0)
	(if argc
		(if (> argc 1) (= temp1 param2))
		(= theEgo theTheEgo)
	)
	(theEgo setScript: clientCastOpen temp1)
)

(instance clientCastOpen of Script
	(properties)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
		(DisposeScript 105)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoSignal (ego signal?))
				(= egoPriority (ego priority?))
				(= egoIllegalBits (ego illegalBits?))
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
				((= newSound (Sound new:))
					number: (SoundFX 35)
					priority: 6
					init:
					play:
				)
				(= cycles 3)
			)
			(3
				(newSound stop: dispose:)
				(NormalEgo)
				(ego
					loop: loopS
					priority: egoPriority
					illegalBits: egoIllegalBits
					signal: egoSignal
				)
				(self dispose:)
			)
		)
	)
)
