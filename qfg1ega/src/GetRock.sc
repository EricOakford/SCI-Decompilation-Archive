;;; Sierra Script 1.0 - (do not remove this comment)
(script# 103)
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	getRock 0
)

(local
	egoSignal
	egoPriority
	egoIllegalBits
)
(instance getRock of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 103)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoSignal (ego signal?))
				(= egoPriority (ego priority?))
				(= egoIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoThrowing
					setLoop: (if (== (ego loop?) 0) 0 else 1)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(1
				(TimePrint 4 103 0)
				;You pick up a few small rocks.
				(ego get: iRock 10 setCycle: BegLoop self)
			)
			(2
				(NormalEgo)
				(HandsOn)
				(ego
					priority: egoPriority
					illegalBits: egoIllegalBits
					signal: egoSignal
				)
				(client setScript: 0)
			)
		)
	)
)
