;;; Sierra Script 1.0 - (do not remove this comment)
(script# 8)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	Room8 0
)

(instance Room8 of Room
	(properties
		picture 8
	)
	
	(method (init)
		(= north 2)
		(= south 14)
		(= east 9)
		(= west 7)
		(= horizon 68)
		(= isIndoors FALSE)
		(super init:)
		(if isNightTime (curRoom overlay: 108))
		(ego view: 2 init:)
		(self setRegions: MEADOW PAN)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said '/door') (Print 8 0))
					((Said '/window>')
						(cond 
							((Said 'open') (Print 8 1))
							((Said 'break') (Print 8 2))
							((Said 'look')
								(if (ego inRect: 10 134 57 140)
									(Print 8 3)
								else
									(Print 800 1)
								)
							)
						)
					)
				)
				(if (Said 'look>')
					(cond 
						((Said '/cottage') (Print 8 4))
						((Said '[<around][/room]') (Print 8 5))
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (cast contains: pan)
			(= hourLastMetPan gameHours)
			(= minutesLastMetPan gameMinutes)
		)
		(super newRoom: newRoomNumber)
	)
)
