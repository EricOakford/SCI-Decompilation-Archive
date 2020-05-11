;;; Sierra Script 1.0 - (do not remove this comment)
(script# WOODS)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	woodsReg 0
)

(local
	local0
)
(instance woodsReg of Region
	(properties
	;	name "Woods Region"
	)
	
	(method (init)
		(if (== (Random 1 5) 4)
			(= crow (Actor new:))
			(crow
				view: 356
				ignoreHorizon:
				illegalBits: 0
				ignoreActors:
				setPri: 14
				setScript: ravenActions
			)
			(if (== (Random 1 2) 1)
				(crow
					posn: 5 30
					xStep: 5
					yStep: 4
					setCycle: Forward
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
							((Said '/boulder') (Print 507 0))
							((Said '/flora') (Print 507 1))
							((Said '/dirt,down') (Print 507 2))
							((Said '/bush') (Print 507 3))
							((Said '/grass') (Print 507 4))
							((Said '/blossom') (Print 507 5))
							((Said '/forest') (Print 507 6))
							((Said '/crow,bird,crow')
								(if (cast contains: crow)
									(Print 507 7)
								else
									(Print 507 8)
								)
							)
						)
					)
					((Said 'climb/boulder') (Print 507 9))
					((Said 'get/blossom') (Print 507 10))
					((Said 'climb/forest') (Print 507 11))
					((Said 'converse/crow,bird,crow')
						(if (cast contains: crow)
							(Print 507 12)
						else
							(Print 507 13)
						)
					)
					((Said 'get,capture/crow,bird,crow')
						(if (cast contains: crow)
							(Print 507 14)
						else
							(Print 507 15)
						)
					)
					((Said 'kiss/crow,bird,crow')
						(if (cast contains: crow)
							(Print 507 16)
						else
							(Print 507 15)
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
	(properties)
	
	(method (cue)
		(crow dispose:)
	)
)
