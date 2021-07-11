;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh)
(use Main)
(use Intrface)
(use SoundSyncWave)
(use InitAllFeatures)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Game)
(use Actor)

(public
	beachHuts3 0
)

(instance beachHuts3 of Room
	(properties
		picture 12
		east 13
		south 16
		west 2
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 12 112)
		(addToPics
			add: beachLayer1 beachLayer2
			eachElementDo: #init
			doit:
		)
		(self
			setRegions: 301 300
			setFeatures:
				jungleFeat
				palmTree
				hutDoor
				eastWindowFeat
				middleWindowFeat
				westWindowFeat
		)
		((Clone wave) x: 277 y: 177 init:)
		((Clone wave) x: 231 y: 182 loop: 1 init:)
		((Clone wave) x: 290 y: 151 loop: 2 init:)
		((Clone wave) x: 205 y: 168 loop: 2 init:)
		((Clone wave) x: 107 y: 189 loop: 2 init:)
		(wave init:)
		(ego init:)
		(switch prevRoomNum
			(west (ego x: 10))
			(east (ego x: 310))
			(9 (ego x: 10 y: 105))
			(south (ego y: 187))
			(else  (ego posn: 160 100))
		)
		(InitAllFeatures)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<around,at][/room,beach]')
				(Print 12 0)
			)
			((Said 'look[<at]/building')
				(Print 12 1)
			)
			((Said 'look[<at]/people')
				(Print 12 2)
			)
		)
	)
)

(instance hutDoor of RFeature
	(properties
		y 52
		x 150
		nsTop 29
		nsLeft 139
		nsBottom 75
		nsRight 162
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 12 3)
					)
					((GoToIfSaid self event 148 82 0 12 4))
					((Said 'open')
						(Print 12 5)
					)
					((Said 'unlock')
						(if (ego has: iTahitiKey)
							(Print 12 6)
						else
							(Print 12 7)
						)
					)
					((Said 'knock')
						(Print 12 8)
					)
				)
			)
		)
	)
)

(instance beachLayer1 of RPicView
	(properties
		y 147
		x 138
		view 12
		loop 1
		cel 2
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/babe]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 12 9)
					)
					((Said 'address')
						(Print 12 10)
					)
					((Said 'wake,kick')
						(Print 12 11)
					)
					((Said 'kiss')
						(Print 12 12)
					)
				)
			)
			((Said 'look/towel')
				(Print 12 13)
			)
			((Said 'kick/sand')
				(Print 12 14)
			)
		)
	)
)

(instance beachLayer2 of RPicView
	(properties
		y 147
		x 114
		view 12
		loop 1
		cel 1
		signal ignrAct
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/man,people]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 12 15)
					)
					((Said 'address')
						(Print 12 16)
					)
					((Said 'wake,kick')
						(Print 12 17)
					)
					((Said 'kiss')
						(Print 12 12)
					)
				)
			)
		)
	)
)

(instance wave of SoundSyncWave
	(properties
		y 167
		x 315
		view 112
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self sound: globalSound)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/wave]')
				(Print 12 18)
			)
		)
	)
)

(instance westWindowFeat of RFeature
	(properties
		y 34
		x 180
		z 21
		heading 240
		nsTop 33
		nsLeft 170
		nsBottom 54
		nsRight 190
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 12 19)
					)
					((Said 'look<in')
						(Print 12 20)
					)
				)
			)
		)
	)
)

(instance middleWindowFeat of RFeature
	(properties
		y 65
		x 220
		z 21
		heading 150
		nsTop 34
		nsLeft 205
		nsBottom 55
		nsRight 235
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 12 19)
					)
					((Said 'look<in')
						(Print 12 20)
					)
				)
			)
		)
	)
)

(instance eastWindowFeat of RFeature
	(properties
		y 65
		x 268
		z 27
		heading 150
		nsTop 34
		nsLeft 250
		nsBottom 55
		nsRight 285
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 12 19)
					)
					((Said 'look<in')
						(Print 12 20)
					)
				)
			)
		)
	)
)

(instance jungleFeat of Feature
	(properties
		y 70
		x 70
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/bush,palm]')
				(Print 12 21)
			)
		)
	)
)

(instance palmTree of RFeature
	(properties
		y 123
		x 13
		nsTop 10
		nsLeft 7
		nsBottom 125
		nsRight 21
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at][/palm]')
				(Print 12 22)
			)
		)
	)
)
