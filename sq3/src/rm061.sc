;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)
(use Actor)

(public
	rm061 0
)

(instance rm061 of Room
	(properties
		picture 61
		horizon 77
		east 62
		south 67
		west 60
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(Load VIEW 90)
		(bloop init:)
		(super init:)
		(switch prevRoomNum
			(60
				(ego posn: 2 (ego y?) init:)
			)
			(62
				(ego
					view: 0
					cel: (ego cel?)
					loop: (ego loop?)
					posn: 317 (ego y?)
					init:
				)
			)
			(66 (ego posn: 80 187 init:))
			(67 (ego posn: 240 187 init:))
		)
		(TheMenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (doit &tmp egoOnControl)
		(super doit:)
		(if
			(and
				(== (curRoom script?) 0)
				(or
					(== (= egoOnControl (ego onControl:)) 5)
					(== egoOnControl 4)
				)
			)
			(ego setPri: 4 posn: (ego x?) (- (ego y?) 6))
			(= fallingIntoLava TRUE)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== script 0)
			(if (== 3 (ego edgeHit?))
				(if (< (ego x?) 159)
					(= newRoomNumber 66)
				else
					(= newRoomNumber 67)
				)
			)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance bloop of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 90
			setLoop: 0
			setCel: 0
			cycleSpeed: 3
			posn: 303 14
			setCycle: Forward
			ignoreActors:
		)
	)
)
