;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room64 0
)

(local
	[local0 3]
)
(instance Room64 of Room
	(properties
		picture 64
		style (| BLACKOUT IRISOUT)
	)
	
	(method (init)
		(self setRegions: HAUNTED_HOUSE)
		(super init:)
		((View new:)
			view: 536
			loop: 3
			cel: 0
			posn: 153 81
			setPri: 15
			addToPic:
		)
		((View new:)
			view: 536
			loop: 3
			cel: 0
			posn: 166 81
			setPri: 15
			addToPic:
		)
		((View new:)
			view: 536
			loop: 3
			cel: 0
			posn: 178 81
			setPri: 15
			addToPic:
		)
		(if isNightTime
			((View new:)
				view: 648
				loop: 3
				cel: 0
				posn: 219 88
				setPri: 5
				addToPic:
			)
			((View new:)
				view: 648
				loop: 4
				cel: 0
				posn: 266 104
				setPri: 6
				addToPic:
			)
			((Prop new:)
				view: 536
				loop: 2
				cel: 2
				posn: 152 76
				setPri: 15
				init:
				setCycle: Forward
			)
			((Prop new:)
				view: 536
				loop: 1
				cel: 1
				posn: 166 76
				setPri: 15
				init:
				setCycle: Forward
			)
			((Prop new:)
				view: 536
				loop: 2
				cel: 4
				posn: 177 76
				setPri: 15
				init:
				setCycle: Forward
			)
		)
		(if (== prevRoomNum 68)
			(ego posn: 51 130 view: 4 xStep: 4 yStep: 2 init:)
		)
		(if (== prevRoomNum 65)
			(ego posn: 210 117 view: 4 xStep: 4 yStep: 2 init:)
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
		(if (& (ego onControl: FALSE) $0040) (curRoom newRoom: 68))
		(if (& (ego onControl: FALSE) $0020) (curRoom newRoom: 65))
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((== (event type?) saidEvent)
					(cond 
						(
						(or (Said 'look[<around][/!*]') (Said 'look/room')) (Print 64 0))
						((Said 'look>')
							(cond 
								((Said '<under/table') (Print 64 1))
								((Said '/table') (Print 64 2))
								((Said '/chair') (Print 64 3))
								((Said '/window')
									(if (ego inRect: 231 118 278 139)
										(Print 64 4)
									else
										(Print 800 1)
									)
								)
								((Said '/cabinet') (Print 64 5))
								((Said '/chandelier') (Print 64 6))
								((Said '/wall') (Print 64 7))
								((or (Said '/dirt') (Said '<down')) (Print 64 8))
								((Said '/crumbs') (Print 64 9))
								(else (event claimed: FALSE))
							)
						)
						((Said 'sit>') (Print 64 10) (event claimed: TRUE))
						((Said 'get>')
							(if (Said '/crumbs')
								(Print 64 11)
							else
								(event claimed: FALSE)
							)
						)
					)
				)
			)
		)
	)
)
