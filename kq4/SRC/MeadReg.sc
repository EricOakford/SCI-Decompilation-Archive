;;; Sierra Script 1.0 - (do not remove this comment)
(script# MEADOW)
(include game.sh)
(use Intrface)
(use Game)

(public
	meadReg 0
)
(synonyms
	(flora flora flora blossom flora flora)
)

(local
	[local0 7]
)
(instance meadReg of Region
	(properties
	;	name "Meadow Region"
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/bush') (Print 506 0))
							((Said '/boulder') (Print 506 1))
							((or (Said '<down') (Said '/dirt,down')) (Print 506 2))
							((Said '/grass') (Print 506 3))
							((Said '/flora') (Print 506 4))
							((Said '/forest') (Print 506 5))
						)
					)
					((Said 'climb>')
						(cond 
							((Said '/forest') (Print 506 6))
							((Said '/boulder') (Print 506 7))
						)
					)
					((Said 'get/flora,flora') (Print 506 8))
				)
			else
				FALSE
			)
		)
	)
)
