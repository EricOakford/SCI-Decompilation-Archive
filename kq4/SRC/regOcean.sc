;;; Sierra Script 1.0 - (do not remove this comment)
(script# OCEAN)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Invent)

(public
	regOcean 0
)
(synonyms
	(flora flora flora blossom flora flora)
	(ocean seawater ocean)
)

(instance regOcean of Region
	(properties
		name "Ocean Region"
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					(
						(or
							(Said 'look<in/ocean,water')
							(Said 'look<under/ocean,water')
							(Said 'look<in/water')
							(Said 'look<under/water')
						)
						(Print 519 0)
					)
					((Said 'look/fish')
						(cond 
							((ego has: iFish)
								((Inventory at: iFish) showSelf:)
							)
							((not isNightTime)
								(Print 519 1)
							)
							(else
								(Print 519 2)
							)
						)
					)
					((Said 'converse/fish')
						(if (not isNightTime)
							(Print 519 3)
						else
							(Print 519 4)
						)
					)
					((Said 'eat,kill,kiss/fish')
						(if (not isNightTime)
							(Print 519 5)
						else
							(Print 519 4)
						)
					)
					((or (Said 'get,capture/fish') (Said 'fish[/noword]'))
						(Print 519 6)
					)
					((Said 'look/ocean,water')
						(Print 519 7)
					)
					((Said 'look/sky')
						(if (not isNightTime)
							(Print 519 8)
						else
							(Print 519 9)
						)
					)
					((Said 'bathe[/noword]')
						(Print 519 10)
					)
					(
						(or
							(Said 'dive/ocean,water')
							(Said 'dive[/noword]')
							(Said 'bathe<under/ocean,water')
						)
						(Print 519 11)
					)
					((or (Said 'get/drink') (Said 'drink'))
						(Print 519 12)
					)
				)
			else
				FALSE
			)
		)
	)
)
