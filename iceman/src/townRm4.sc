;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include game.sh)
(use Main)
(use Intrface)
(use GoToSaid)
(use RFeature)
(use Game)
(use Actor)

(public
	townRm4 0
)

(instance townRm4 of Room
	(properties
		picture 83
		south 79
		west 82
	)
	
	(method (init)
		(Load VIEW 82)
		(super init:)
		(switch prevRoomNum
			(south
				(ego posn: (ego x?) 187 loop: 3)
			)
			(else 
				(ego posn: 10 (ego y?) loop: 0)
			)
		)
		(ego init:)
		(self
			setRegions: 310 312
			setFeatures:
				windo
				door
				building
				arch
				roof
				((Clone windo)
					x: 334
					y: 61
					z: 0
					nsLeft: 375
					nsTop: 38
					nsRight: 292
					nsBottom: 85
					yourself:
				)
				((Clone building)
					x: 292
					y: 105
					z: 0
					nsLeft: 266
					nsTop: 70
					nsRight: 319
					nsBottom: 141
					yourself:
				)
		)
		(addToPics add: wall1 wall2 wall3 wall4 doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[<around][/room,town,scene]')
				(Print 83 0)
			)
		)
	)
)

(instance wall1 of PicView
	(properties
		y 69
		x 21
		view 82
		priority 3
		signal (| ignrAct ignrHrz)
	)
)

(instance wall2 of PicView
	(properties
		y 69
		x 87
		view 82
		cel 1
		priority 3
		signal (| ignrAct ignrHrz)
	)
)

(instance wall3 of PicView
	(properties
		y 68
		x 85
		view 82
		loop 1
		cel 1
		priority 0
		signal (| ignrAct ignrHrz)
	)
)

(instance wall4 of PicView
	(properties
		y 67
		x 7
		view 82
		loop 1
		priority 0
		signal (| ignrAct ignrHrz)
	)
)

(instance building of RFeature
	(properties
		y 34
		x 229
		nsLeft 139
		nsBottom 69
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 83 1)
					)
				)
			)
		)
	)
)

(instance windo of RFeature
	(properties
		y 29
		x 163
		nsTop 15
		nsLeft 151
		nsBottom 43
		nsRight 176
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 83 2)
					)
					((Said 'open,(look,climb<(in,through))')
						(BadIdea)
					)
				)
			)
		)
	)
)

(instance arch of RFeature
	(properties
		y 34
		x 69
		nsBottom 69
		nsRight 138
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/arch]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 83 3)
					)
				)
			)
		)
	)
)

(instance roof of RFeature
	(properties
		y 172
		x 69
		nsTop 155
		nsBottom 189
		nsRight 138
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/roof]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 83 4)
					)
				)
			)
		)
	)
)

(instance door of RFeature
	(properties
		y 128
		x 303
		nsTop 90
		nsLeft 292
		nsBottom 166
		nsRight 315
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 83 5)
					)
					((Said 'open,close,knock')
						(DontNeedTo)
					)
				)
			)
		)
	)
)
