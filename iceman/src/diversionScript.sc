;;; Sierra Script 1.0 - (do not remove this comment)
(script# 337)
(include game.sh)
(use Intrface)
(use System)

(public
	diversionScript 0
)

(instance diversionScript of Script

	(method (dispose)
		(super dispose:)
		(DisposeScript 337)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'address')
				(switch state
					(0 (Print 337 0))
					(1 (Print 337 1))
					(2 (Print 337 2))
					(else  (Print 337 3))
				)
				(= cycles 1)
			)
		)
	)
)
