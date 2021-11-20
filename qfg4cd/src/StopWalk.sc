;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64961)
(include sci.sh)
(use Motion)

(public
	StopWalk 0
)

(class StopWalk of Fwd
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		vWalking 0
		vStopped 0
	)
	
	(method (init theClient theVStopped)
		(if argc
			(= vWalking ((= client theClient) view?))
			(if (>= argc 2) (= vStopped theVStopped))
		)
		(super init: client)
		(self doit:)
	)
	
	(method (doit &tmp clientLoop clientMover)
		(if (client isStopped:)
			(cond 
				(
					(and
						(== vStopped -1)
						(!= (client loop?) (- (NumLoops client) 1))
					)
					(= clientLoop (client loop?))
					(client loop: (- (NumLoops client) 1) setCel: 0)
				)
				(
				(and (!= vStopped -1) (== (client view?) vWalking))
					(client setCel: 0 view: vStopped)
					(= clientMover (client mover?))
				)
				((!= vStopped -1) (super doit:))
			)
		else
			(= clientLastCel (client lastCel:))
			(switch vStopped
				((client view?)
					(client view: vWalking)
				)
				(-1
					(client setLoop: -1 setCel: 0)
					(if (== (client loop?) (- (NumLoops client) 1))
						(client loop: (client cel?) setCel: 0)
					)
				)
			)
			(super doit:)
		)
	)
	
	(method (dispose)
		(if (== (client view?) vStopped)
			(client view: vWalking)
		)
		(super dispose:)
	)
)
