;;; Sierra Script 1.0 - (do not remove this comment)
(script# FOREST)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	forestReg 0
)

(instance forestReg of Region
	(properties
		name "Forest Region"
	)
	
	(method (init)
		(if (== (Random 1 5) 4)
			(= crow (Actor new:))
			(crow
				view: 356
				illegalBits: 0
				ignoreActors:
				setPri: 14
				ignoreHorizon:
				setScript: ravenActions
			)
			(if (== (Random 1 2) 1)
				(crow
					posn: 5 30
					setCycle: Forward
					xStep: 5
					yStep: 4
					setMotion: MoveTo 339 20 crow
					init:
				)
			else
				(crow
					posn: 314 20
					xStep: 5
					yStep: 4
					setCycle: Forward
					setMotion: MoveTo -20 40 crow
					init:
				)
			)
		)
		(super init:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/boulder')
								(Print 508 0)
							)
							((or (Said '/dirt') (Said '/down'))
								(Print 508 1)
							)
							((Said '/grass')
								(Print 508 2)
							)
							((Said '/bush')
								(Print 508 3)
							)
							((Said '/flora')
								(Print 508 4))
							((Said '/blossom')
								(Print 508 5)
							)
							((Said '/forest')
								(Print 508 6)
							)
							((Said '/crow,bird,crow')
								(if (cast contains: crow)
									(Print 508 7)
								else
									(Print 508 8)
								)
							)
						)
					)
					((Said 'climb/boulder')
						(Print 508 9)
					)
					((Said 'get/blossom')
						(Print 508 10)
					)
					((Said 'climb/forest')
						(Print 508 11)
					)
					((Said 'converse/crow,bird,crow')
						(if (cast contains: crow)
							(Print 508 12)
						else
							(Print 508 13)
						)
					)
					((Said 'get,capture/crow,bird,crow')
						(if (cast contains: crow)
							(Print 508 14)
						else
							(Print 508 15)
						)
					)
					((Said 'kiss/crow,bird,crow')
						(if (cast contains: crow)
							(Print 508 16)
						else
							(Print 508 15)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance ravenActions of Script
	(method (cue)
		(crow dispose:)
	)
)
