;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	rm410 0
)

(local
	local0
)
(instance rm410 of Room
	(properties
		picture 410
		south 400
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(cond 
			((== prevRoomNum 415)
				(ego posn: 59 45)
			)
			((== prevRoomNum 416)
				(ego posn: 263 45)
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
		(cond 
			((& (ego onControl: origin) cBLUE)
				(curRoom newRoom: 415)
			)
			((& (ego onControl: origin) cGREEN)
				(curRoom newRoom: 416)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/art,body,naked,babe,art')
					(Print 410 0)
				)
				((Said '/column')
					(Print 410 1)
				)
				((Said '/rail')
					(Print 410 2)
				)
				((Said '/stair')
					(Print 410 3)
				)
				((Said '/curtain')
					(Print 410 4)
				)
				((Said '[/area]')
					(Print 410 5)
				)
			)
		)
	)
)
