;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include game.sh)
(use Main)
(use Intrface)
(use n802)
(use GoToSaid)
(use RFeature)
(use Extra)
(use Game)
(use Actor)

(public
	tunaBeachRm2 0
	stupidAvoider 1
	stupidAvoider2 2
)

(local
	local0 =  1
)
(instance tunaBeachRm2 of Room
	(properties
		picture 72
		south 47
		west 71
	)
	
	(method (init)
		(super init:)
		(self
			setRegions: 310 306
			setFeatures:
				building
				((Clone building)
					x: 158
					y: 10
					z: 0
					nsLeft: 140
					nsTop: 1
					nsRight: 177
					nsBottom: 18
					yourself:
				)
				((Clone building)
					x: 240
					y: 25
					z: 0
					nsLeft: 220
					nsTop: 17
					nsRight: 261
					nsBottom: 33
					yourself:
				)
		)
		(if (== prevRoomNum 45)
			(globalSound stop:)
		)
		(switch prevRoomNum
			(west
				(ego posn: 10 (ego y?) loop: 0)
			)
			(else 
				(ego posn: 70 105 view: 71 loop: 0)
			)
		)
		(ego init: observeControl: cBROWN cycleSpeed: 0)
		(if (== (ego view?) 54)
			(ego view: 71)
		)
		(wave init: isExtra: TRUE)
		((Clone wave)
			x: 288
			y: 157
			loop: 0
			priority: 11
			isExtra: TRUE
			init:
		)
		((Clone wave)
			x: 46
			y: 155
			loop: 1
			priority: 11
			isExtra: TRUE
			init:
		)
		((Clone wave)
			x: 94
			y: 133
			loop: 1
			priority: 9
			isExtra: TRUE
			init:
		)
		((Clone wave)
			x: 150
			y: 165
			loop: 1
			priority: 12
			isExtra: TRUE
			init:
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and local0 (& (proc802_0 ego 3) $0040))
			(Print 72 0)
			(= local0 0)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]')
				(Print 72 1)
			)
		)
	)
)

(instance wave of Extra
	(properties
		y 150
		x 233
		view 72
		priority 10
		cycleSpeed 3
		cycleType ExtraEndLoop
		minPause 5
		maxPause 5
		minCycles 2
		maxCycles 3
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/wave]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 72 2)
					)
				)
			)
		)
	)
)

(instance building of RFeature
	(properties
		y 20
		x 115
		nsTop 13
		nsLeft 92
		nsBottom 27
		nsRight 139
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/building]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 72 3)
					)
				)
			)
		)
	)
)

(instance stupidAvoider of Feature
	(properties
		y 75
		x -15
	)
)

(instance stupidAvoider2 of Feature
	(properties
		y 106
		x -15
	)
)
