;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm400 0
)

(instance rm400 of Room
	(properties
		picture 400
		horizon 83
		north 410
		east 460
		south 250
		west 250
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(switch prevRoomNum
			(460
				(ego posn: 318 162)
			)
			(410
				(if (< (ego x?) 111)
					(ego x: 111)
				)
				(if (> (ego x?) 210)
					(ego x: 210)
				)
			)
			(else
				(ego posn: 159 188)
			)
		)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/burn')
					(Printf 400 0 currentEgo)
				)
				((Said '[/area]')
					(Print 400 1)
				)
			)
		)
	)
)
