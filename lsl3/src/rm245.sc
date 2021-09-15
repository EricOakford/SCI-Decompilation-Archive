;;; Sierra Script 1.0 - (do not remove this comment)
(script# 245)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm245 0
)

(local
	noEntryMsg
)
(instance rm245 of Room
	(properties
		picture 245
		west 240
	)
	
	(method (init)
		(super init:)
		(self setRegions: FALLING setScript: RoomScript)
		(if (== prevRoomNum 500)
			(ego posn: 126 73 loop: 2)
		else
			(ego posn: 5 172 loop: 0)
		)
		(NormalEgo)
		(ego init:)
		(if currentStatus
			(ego observeControl: cGREEN cCYAN cRED)
		)
	)
	
	(method (newRoom n)
		(cls)
		(super newRoom: n)
	)
)

(instance RoomScript of Script	
	(method (doit)
		(if (not playingAsPatti)
			(ego observeControl: cYELLOW)
			(if (& (ego onControl:) cMAGENTA)
				(if (not noEntryMsg)
					(= noEntryMsg TRUE)
					(Print 245 0)
				)
			else
				(= noEntryMsg FALSE)
			)
		)
		(if (== currentStatus egoNORMAL)
			(cond 
				((& (ego onControl:) cBLUE)
					(curRoom newRoom: 500)
				)
				((& (ego onControl:) cRED)
					(NotifyScript FALLING 9 300)
				)
				((& (ego onControl:) cGREEN)
					(NotifyScript FALLING 0 300)
				)
				((or (== EAST (ego edgeHit?)) (& (ego onControl:) cCYAN))
					(ego x: (+ (ego x?) 8))
					(NotifyScript FALLING 8 300)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/ball,boulder,boob')
					(Print 245 1)
				)
				((Said '/bamboo')
					(Print 245 2)
				)
				((Said '[/area]')
					(Print 245 3)
				)
			)
		)
	)
)
