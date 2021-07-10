;;; Sierra Script 1.0 - (do not remove this comment)
(script# 77)
(include sci.sh)
(use Main)
(use Intrface)
(use GoToSaid)
(use RFeature)
(use Game)

(public
	roadToTownRm 0
)

(instance roadToTownRm of Rm
	(properties
		picture 77
		east 78
		south 74
		west 76
	)
	
	(method (init)
		(super init:)
		(self setRegions: 310 312 setFeatures: windo building)
		(switch prevRoomNum
			(east
				(ego posn: 310 (ego y?) loop: 1)
			)
			(west
				(globalSound
					number: 74
					owner: theGame
					priority: 1
					loop: -1
					play:
				)
				(ego posn: 10 (ego y?) loop: 0)
			)
			(else 
				(globalSound
					number: 74
					owner: theGame
					priority: 1
					loop: -1
					play:
				)
				(ego posn: 180 179 loop: 3)
			)
		)
		(ego init:)
		(HandsOn)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/town,room,scene]')
				(switch (Random 0 1)
					(0 (Print 77 0))
					(1 (Print 77 1))
				)
			)
			((Said 'look/road') (Print 77 2))
		)
	)
)

(instance building of RFeature
	(properties
		y 52
		x 286
		nsTop 13
		nsLeft 254
		nsBottom 92
		nsRight 319
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]') (Print 77 3))
				)
			)
		)
	)
)

(instance windo of RFeature
	(properties
		y 51
		x 300
		nsTop 36
		nsLeft 285
		nsBottom 67
		nsRight 316
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]') (Print 77 4))
					((Said 'open,(look,climb<(in,through))') (BadIdea))
				)
			)
		)
	)
)
