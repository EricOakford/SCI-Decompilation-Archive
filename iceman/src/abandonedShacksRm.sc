;;; Sierra Script 1.0 - (do not remove this comment)
(script# 75)
(include sci.sh)
(use Main)
(use Intrface)
(use GoToSaid)
(use RFeature)
(use Game)
(use Actor)

(public
	abandonedShacksRm 0
	shackFeat 1
)

(local
	local0
)
(procedure (localproc_000e)
	(return
		(if
		(and (== (ego onControl: 1) 2) (< (ego x?) 115))
			1
		else
			0
		)
	)
)

(instance abandonedShacksRm of Rm
	(properties
		picture 75
		east 74
		south 70
	)
	
	(method (init)
		(super init:)
		(addToPics add: shackRock eachElementDo: #init doit:)
		(switch prevRoomNum
			(south
				(ego posn: 100 188 loop: 3)
			)
		)
		(ego init:)
		(self
			setRegions: 310 311
			setFeatures:
				lDoor
				rDoor
				windo
				((Clone windo)
					x: 231
					y: 49
					z: 0
					nsLeft: 219
					nsTop: 37
					nsRight: 244
					nsBottom: 62
					yourself:
				)
				((Clone windo)
					x: 290
					y: 50
					z: 0
					nsLeft: 277
					nsTop: 37
					nsRight: 304
					nsBottom: 63
					yourself:
				)
				building
				shackFeat
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]') (if (localproc_000e) (Print 75 0) else (Print 75 1)))
			((Said 'move,get/box') (if (localproc_000e) (Print 75 2) else (CantSee)))
			((Said 'look<in/box')
				(cond 
					((!= local0 1) (Print 75 3))
					((== (ego view?) 84) (Print 75 4))
					(else (Print 75 5))
				)
			)
			((Said 'examine,look,open[<at]/box')
				(cond 
					((not (localproc_000e)) (CantSee))
					((or (== local0 1) (== (ego view?) 84)) (Print 75 6))
					((== (ego onControl: 1) 2) (Print 75 7) (= local0 1))
				)
			)
			((Said 'look[<at]/clothes')
				(cond 
					((or (not (localproc_000e)) (== local0 0)) (CantSee))
					((== (ego view?) 84) (Print 75 8))
					(else (Print 75 9))
				)
			)
			(
			(Said 'get,change,wear,(adjust<on)/clothes,disguise')
				(cond 
					((== (ego view?) 84) (DontNeedTo))
					((or (not (localproc_000e)) (== local0 0)) (CantSee))
					(else
						(Print 75 10)
						(if (ego has: 3) (Print 75 11))
						(ego put: 3 put: 4 put: 5 view: 84)
						(theGame changeScore: 2)
					)
				)
			)
			((Said 'conceal/gear[<scuba]')
				(if (== (ego view?) 84)
					(DontNeedTo)
				else
					(Print 75 12)
				)
			)
			((Said 'rip,destroy/map') (DontNeedTo))
			((Said 'get/map')
				(cond 
					((ego has: 5) (Print 75 13))
					((localproc_000e) (Print 75 14))
					(else (CantSee))
				)
			)
			((Said 'get/fish')
				(cond 
					((ego has: 3) (Print 75 15))
					((localproc_000e) (Print 75 14))
					(else (CantSee))
				)
			)
		)
	)
)

(instance shackRock of PV
	(properties
		y 135
		x 233
		view 75
		priority 9
		signal $4000
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
					((Said 'look[<at]') (Print 75 16))
				)
			)
		)
	)
)

(instance windo of RFeature
	(properties
		y 56
		x 81
		nsTop 44
		nsLeft 68
		nsBottom 68
		nsRight 94
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/shutter]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 75 17))
					((Said '(climb,look<(in,through)),open') (BadIdea))
				)
			)
		)
	)
)

(instance lDoor of RFeature
	(properties
		y 87
		x 39
		nsTop 63
		nsLeft 22
		nsBottom 111
		nsRight 57
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 75 18))
					((Said 'open') (Print 75 19))
					((Said 'close') (Print 75 20))
				)
			)
		)
	)
)

(instance rDoor of RFeature
	(properties
		y 80
		x 180
		nsTop 53
		nsLeft 161
		nsBottom 108
		nsRight 199
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/door]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]') (Print 75 21))
					((Said 'open') (Print 75 22))
					((Said 'close') (Print 75 23))
				)
			)
		)
	)
)

(instance shackFeat of RFeature
	(properties
		y 126
		x 331
		nsTop 125
		nsLeft 330
		nsBottom 127
		nsRight 332
	)
)
