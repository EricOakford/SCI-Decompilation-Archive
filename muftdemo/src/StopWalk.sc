;;; Sierra Script 1.0 - (do not remove this comment)
(script# STOPWALK)
(include game.sh)
(use PChase)
(use Motion)

(public
	StopWalk 0
)

(class StopWalk of Forward
	(properties
		vWalking 0
		vStopped 0
	)
	
	(method (init who stopView)
		(if argc
			(= vWalking ((= client who) view?))
			(if (>= argc 2) (= vStopped stopView))
		)
		(super init: client)
	)
	
	(method (doit &tmp st mv curLoop theMover)
		(= curLoop (client loop?))
		(= theMover (- (NumLoops client) 1))
		(if (client isStopped:)
			(if (!= curLoop theMover)
				(client loop: theMover cel: curLoop)
				(if
					(and
						(= mv (client mover?))
						(not (mv completed?))
						(not (mv isKindOf: PFollow))
					)
					(client setMotion: 0)
				)
			)
		else
			(if (== curLoop theMover)
				(client loop: (client cel?) cel: 0)
			)
			(super doit:)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
)
