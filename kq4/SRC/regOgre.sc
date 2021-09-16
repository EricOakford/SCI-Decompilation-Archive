;;; Sierra Script 1.0 - (do not remove this comment)
(script# OGRE)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	regOgre 0
)
(synonyms
	(kiss kiss embrace)
	(giant giant giant giant man giant)
)

(instance regOgre of Region
	(properties
		name "Ogre's Region"
	)
	
	(method (handleEvent event &tmp index)
		(if (event claimed?) (return TRUE))
		(return
			(if (and (== (event type?) saidEvent) (cast contains: ogre))
				(cond 
					((Said 'look/giant')
						(Print 517 0)
					)
					((Said 'converse')
						(Print 517 1)
					)
					((Said 'kill/giant')
						(Print 517 2)
					)
					((Said 'get,capture/giant')
						(Print 517 3)
					)
					((Said 'kiss')
						(Print 517 4)
					)
					((Said 'deliver>')
						(if
							(and
								(= index (inventory saidMe:))
								(ego has: (inventory indexOf: index))
							)
							(Print 517 5)
						else
							(Print 517 6)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)
