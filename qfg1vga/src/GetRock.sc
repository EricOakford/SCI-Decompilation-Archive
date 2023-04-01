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
	savSpeed
	savSignal
	savPriority
	savIllegalBits
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
				(= savSpeed (ego cycleSpeed?))
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(= wasHandsOn (not isHandsOff))
				(HandsOff)
				(ego
					view: vEgoThrowing
					setLoop: (if (OneOf (ego loop?) facingSouth facingSE facingEast facingNE) 0 else 1)
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
				;(messager say: N_GETROCK NULL NULL 1 0 GETROCK)
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
					get: iRock 10
					cycleSpeed: savSpeed
					priority: savPriority
					illegalBits: savIllegalBits
					signal: savSignal
				)
				(client setScript: 0)
			)
		)
	)
)
