;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room65 0
)
(synonyms
	(caldron caldron)
	(shelf cabinet)
)

(instance Room65 of Room
	(properties
		picture 65
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(Load VIEW 648)
		(Load VIEW 536)
		(self setRegions: HAUNTED_HOUSE)
		(super init:)
		((View new:)
			view: 536
			loop: 5
			cel: 0
			posn: 238 80
			setPri: 4
			addToPic:
		)
		(if isNightTime
			((View new:)
				view: 648
				loop: 5
				cel: 0
				posn: 280 104
				setPri: 6
				addToPic:
			)
			((Prop new:)
				view: 536
				loop: 1
				posn: 237 73
				setPri: 4
				init:
				setCycle: Forward
			)
		)
		(if (or (== prevRoomNum 64) (== prevRoomNum 0))
			(ego posn: 245 162 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if
			(and
				(< 0 mansionPhase)
				(< mansionPhase 255)
				(== ghostRoomNum curRoomNum)
			)
			(NotifyScript HAUNTED_HOUSE -1)
		)
	)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 64))
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
							(or
								(Said 'look[<around][/!*]')
								(Said 'look/room,kitchen')
							)
							(Print 65 0)
						)
						((Said 'look>')
							(cond 
								((Said '/pantry') (Print 65 1))
								((Said '/fireplace') (Print 65 2))
								((Said '/caldron') (Print 65 3))
								((Said '/butterchurn') (Print 65 4))
								((Said '/window')
									(if (ego inRect: 229 124 277 146)
										(Print 65 5)
									else
										(Print 65 6)
									)
								)
								((Said '/barrel') (Print 65 7))
								((Said '/shelf') (Print 65 8))
								((Said '/chandelier') (Print 65 9))
								((Said '/wall') (Print 65 10))
								((or (Said '/dirt') (Said '<down')) (Print 65 11))
								((Said '/ladder') (Print 65 12))
								((Said '/crumbs') (Print 65 13))
								((Said '/bottle')
									(if (ego has: iGlassBottle)
										(event claimed: FALSE)
									else
										(Print 800 2)
									)
								)
								(else (event claimed: FALSE))
							)
						)
						((Said 'get>')
							(cond 
								((Said '/caldron') (Print 65 14))
								((Said '/butterchurn') (Print 65 4))
								((Said '/barrel') (Print 65 7))
								((Said '/crumbs') (Print 65 15))
								(else (event claimed: FALSE))
							)
						)
					)
				)
			)
		)
	)
)
