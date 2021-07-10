;;; Sierra Script 1.0 - (do not remove this comment)
(script# 7)
(include game.sh)
(use Main)
(use Intrface)
(use GoToSaid)
(use RFeature)
(use Game)

(public
	endBeach 0
)

(instance endBeach of Room
	(properties
		picture 7
		north 15
		south 5
		west 8
	)
	
	(method (init)
		(super init:)
		(Load VIEW 200)
		(ego init: observeControl: cLGREEN)
		(switch prevRoomNum
			(south (ego y: 187))
			(west
				(ego view: 200 loop: 0 posn: 5 180)
			)
		)
		(self setFeatures: water jungle setRegions: 301 300)
	)
	
	(method (dispose)
		(ego ignoreControl: cLGREEN)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene,beach]')
				(Print 7 0)
			)
			((Said 'look<up')
				(Print 7 1)
			)
			((Said 'look[<at]/building,hotel,building,building')
				(Print 7 2)
			)
			((Said 'look/cloud')
				(Print 7 3)
			)
		)
	)
)

(instance jungle of RFeature
	(properties
		y 150
		x 294
		nsTop 112
		nsLeft 270
		nsBottom 189
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/bush,bush]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 7 4)
					)
					((Said 'enter')
						(DontNeedTo)
					)
				)
			)
		)
	)
)

(instance water of RFeature
	(properties
		y 82
		x 137
		nsTop 66
		nsBottom 99
		nsRight 274
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[bay,water]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 7 5)
					)
					((Said 'enter')
						(Print 7 6)
					)
				)
			)
		)
	)
)
