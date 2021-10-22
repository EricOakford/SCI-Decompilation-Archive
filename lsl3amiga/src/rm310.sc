;;; Sierra Script 1.0 - (do not remove this comment)
(script# 310)
(include game.sh)
(use Main)
(use AutoDoor)
(use Intrface)
(use Game)
(use Actor)
(use System)

(public
	rm310 0
)

(local
	[local0 312]
)
(instance rm310 of Room
	(properties
		picture 310
		south 220
	)
	
	(method (init)
		(super init:)
		(addToPics add: atpSign doit:)
		(if (< lawyerState LSdone)
			(addToPics add: atpRoger doit:)
		else
			(addToPics add: atpNoRoger doit:)
		)
		(aDoor init:)
		(self setScript: RoomScript)
		(if (== prevRoomNum 320)
			(ego posn: 220 88)
		else
			(ego posn: 84 188)
		)
		(NormalEgo)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look<down')
				(Print 310 0)
			)
			(
				(and
					(ego has: iKnife)
					(!= ((inventory at: iKnife) view?) 21)
					(Said 'sharpen/ginsu')
				)
				(Print 310 1)
			)
			((Said 'look>')
				(cond 
					((Said '/camp,beach,bay,water')
						(Print 310 2)
					)
					((Said '/awning')
						(Print 310 3)
					)
					((Said '/bush')
						(Print 310 4)
					)
					((Said '/flower,boulder,wall')
						(Print 310 5)
					)
					((Said '[/area]')
						(Print 310 6)
						(Print 310 7 #at -1 144)
					)
				)
			)
			((Said 'get/bush')
				(Print 310 8)
			)
			((Said 'get,pick/flower')
				(Print 310 9)
			)
		)
	)
)

(instance atpSign of PicView
	(properties
		y 189
		x 192
		view 310
		priority 14
	)
)

(instance aDoor of AutoDoor
	(properties
		y 87
		x 220
		view 310
		loop 1
		cycleSpeed 2
		entranceTo 320
	)
	
	(method (init)
		(super init:)
		(self setPri: 4)
	)
)

(instance atpRoger of PicView
	(properties
		y 45
		x 219
		view 310
		loop 2
		priority 2
	)
)

(instance atpNoRoger of PicView
	(properties
		y 51
		x 219
		view 310
		loop 2
		cel 1
		priority 2
	)
)
