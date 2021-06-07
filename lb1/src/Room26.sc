;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh)
(use Main)
(use Intrface)
(use RFeature)
(use Game)

(public
	Room26 0
)

(instance Room26 of Room
	(properties
		picture 26
	)
	
	(method (init)
		(= horizon 122)
		(= east 27)
		(= west 25)
		(= north 20)
		(super init:)
		(self setRegions: 207 405 setFeatures: House)
		(if (and (>= currentAct 2) (< currentAct 4))
			(self setRegions: 202)
		)
		(if
			(or
				(and (== currentAct 3) (!= global114 10))
				(and (== currentAct 6) (not (& global118 $0002)))
			)
			(self setRegions: 281)
		)
		(if (== prevRoomNum 20)
			(ego posn: 180 125)
		)
		(if (and (== prevRoomNum 25) (< (ego y?) 157))
			(ego posn: 1 145)
		)
		(ego view: 0 init:)
		(HandsOn)
	)
	
	(method (doit)
		(if (FirstEntry)
			(Print 26 0)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room]')
								(Print 26 0)
							)
							((Said '/drive')
								(Print 26 1)
							)
							((Said '/archway')
								(Print 26 2)
							)
							((Said '/box')
								(Print 26 3)
							)
						)
					)
					((Said 'get/box')
						(Print 26 4)
					)
					((Said 'close,open/archway')
						(Print 26 5)
					)
					((Said 'oil/archway')
						(if (ego has: iOilcan)
							(Print 26 6)
						else
							(Print 26 7)
						)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance House of RFeature
	(properties
		nsTop 59
		nsLeft 38
		nsBottom 97
		nsRight 112
	)
	
	(method (handleEvent event)
		(if (or (MousedOn self event shiftDown) (Said 'examine/cabin'))
			(event claimed: TRUE)
			(Print 26 8)
		)
	)
)
