;;; Sierra Script 1.0 - (do not remove this comment)
(script# MOUNTAIN)
(include game.sh)
(use Intrface)
(use Game)

(public
	mountReg 0
)

(instance mountReg of Region
	(properties
	;	name "Mountain Region"
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look/cliff') (Print 511 0))
					((Said 'climb,cross/cliff') (Print 511 1))
				)
			else
				FALSE
			)
		)
	)
)
