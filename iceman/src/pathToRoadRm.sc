;;; Sierra Script 1.0 - (do not remove this comment)
(script# 74)
(include game.sh)
(use Main)
(use Intrface)
(use GoToSaid)
(use RFeature)
(use Game)
(use Actor)

(public
	pathToRoadRm 0
	pathFeat 1
)

(instance pathToRoadRm of Room
	(properties
		picture 74
		north 77
		east 73
		west 75
	)
	
	(method (init)
		(super init:)
		(switch prevRoomNum
			(west
				(ego posn: 10 (ego y?) loop: 0)
			)
			(east
				(ego posn: 310 (ego y?) loop: 1)
			)
			(else 
				(ego posn: 123 61 loop: 2)
			)
		)
		(ego init:)
		(addToPics add: rockPic doit:)
		(self setRegions: 310 311 setFeatures: building pathFeat)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (== (ego onControl: origin) cBLUE)
			(self newRoom: north)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look>')
				(cond 
					((Said '[<at,around][/room,scene]')
						(Print 74 0)
					)
					((Said '<down')
						(Print 74 1)
					)
					((Said '<up')
						(SeeNothing)
					)
					((Said '/building')
						(Print 74 2)
					)
					((Said '/pathway')
						(SeeNothing)
					)
				)
			)
		)
	)
)

(instance rockPic of PicView
	(properties
		y 154
		x 168
		view 576
		loop 2
		cel 1
	)
)

(instance building of RFeature
	(properties
		y 54
		x 159
		nsBottom 108
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 74 2)
					)
				)
			)
		)
	)
)

(instance pathFeat of RFeature
	(properties
		y 126
		x -13
		nsTop 125
		nsLeft -15
		nsBottom 127
		nsRight -10
	)
)
