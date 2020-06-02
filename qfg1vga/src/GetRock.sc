;;; Sierra Script 1.0 - (do not remove this comment)
(script# GETROCK)
(include game.sh) (include "103.shm")
(use Main)
(use Procs)
(use Print)
(use Motion)
(use System)

(public
	getRock 0
)

(local
	oldCycleSpeed
	oldSignal
	oldPriority
	oldIllBits
	wasHandsOn
	[str 40]
)
(instance getRock of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript GETROCK)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= oldCycleSpeed (ego cycleSpeed?))
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= wasHandsOn (not isHandsOff))
				(HandsOff)
				(ego
					view: 510
					setLoop: (if (OneOf (ego loop?) 2 4 0 6) 0 else 1)
					cel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(= ticks 18)
			)
			(2
				(ego cel: (- (ego cel?) 1))
				(= ticks 30)
			)
			(3
				(ego cel: (+ (ego cel?) 1))
				(= ticks 18)
			)
			(4
				(ego cel: (- (ego cel?) 1))
				(= ticks 30)
			)
			(5
				(ego cel: (+ (ego cel?) 1))
				(= ticks 18)
			)
			(6
				(ego cel: (- (ego cel?) 1))
				(= ticks 30)
			)
			(7
				(Message MsgGet GETROCK N_GETROCK NULL NULL 1 @str)
				(Print addText: @str init:)
				(ego setCycle: BegLoop self)
			)
			(8
				(NormalEgo)
				(if wasHandsOn
					(HandsOn)
				)
				(ego
					get: 10 10
					cycleSpeed: oldCycleSpeed
					priority: oldPriority
					illegalBits: oldIllBits
					signal: oldSignal
				)
				(client setScript: 0)
			)
		)
	)
)
