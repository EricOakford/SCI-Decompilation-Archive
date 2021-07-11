;;; Sierra Script 1.0 - (do not remove this comment)
(script# 24)
(include game.sh)
(use Main)
(use Intrface)
(use SoundSyncWave)
(use GoToSaid)
(use LoadMany)
(use RFeature)
(use Game)
(use Actor)

(public
	eastBeachRm 0
)

(instance eastBeachRm of Room
	(properties
		picture 24
		east 16
		south 16
		west 13
	)
	
	(method (init)
		(super init:)
		(LoadMany VIEW 24 703)
		(ego init:)
		(self setFeatures: rocks building setRegions: 301 300)
		(wave init: globalSound setPri: 3)
		((ScriptID 301) notify: 0 wave 189 139 4 45 145 4)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene]')
				(Print 24 0)
			)
		)
	)
)

(instance rocks of Feature
	(properties
		y 70
		x 240
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/rock]>')
				(cond 
					((Said 'look[<at]')
						(Print 24 1)
					)
					((Said 'climb')
						(DontNeedTo)
					)
				)
			)
		)
	)
)

(instance wave of SoundSyncWave
	(properties
		y 149
		x 117
		view 703
		loop 2
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/wave]>')
				(cond 
					((Said 'look[<at]')
						(Print 24 2)
					)
					((Said 'swim')
						(DontNeedTo)
					)
				)
			)
		)
	)
)

(instance wave2 of SoundSyncWave
	(properties
		y 120
		x 275
		view 24
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/wave]>')
				(cond 
					((Said 'look[<at]')
						(Print 24 2)
					)
					((Said 'swim')
						(DontNeedTo)
					)
				)
			)
		)
	)
)

(instance building of RFeature
	(properties
		y 20
		x 28
		nsBottom 41
		nsRight 57
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building,room]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 24 3)
					)
					((Said 'enter')
						(DontNeedTo)
					)
				)
			)
			((Said 'look<in/shutter')
				(Print 24 4)
			)
		)
	)
)
