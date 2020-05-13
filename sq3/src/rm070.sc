;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Menu)
(use Actor)

(public
	rm070 0
)

(instance rm070 of Room
	(properties
		picture 70
		horizon 70
		north 67
		east 71
		west 69
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(Load VIEW 98)
		(super init:)
		(if (not ortegaWorkersLeft)
			(addToPics add: ship)
			(addToPics doit:)
		)
		(switch prevRoomNum
			(67
				(ego
					posn:
						(if (> (ego x?) 115) 115 else (ego x?))
						(+ (curRoom horizon?) 2)
					init:
				)
			)
			(69
				(ego
					init:
					posn:
						2
						(cond 
							((< (ego y?) 85) 72)
							((== (ego y?) 86) 88)
							((> (ego y?) 166) 166)
							(else (ego y?))
						)
				)
			)
			(71
				(ego
					init:
					posn:
						317
						(cond 
							((== (ego y?) 84) 83)
							((and (< (ego y?) 127) (> (ego y?) 115)) 114)
							((> (ego y?) 142) 142)
							(else (ego y?))
						)
				)
			)
		)
		(TheMenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (doit &tmp egoOnControl)
		(super doit:)
		(if (== (curRoom script?) 0)
			(cond 
				(
					(or
						(== (= egoOnControl (ego onControl:)) 3)
						(== egoOnControl 7)
					)
					(ego
						setPri: 8
						posn: (+ (ego x?) 2) (ego y?)
						illegalBits: 0
					)
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 17)
					(ego
						setPri: 13
						posn: (+ (ego x?) 2) (ego y?)
						illegalBits: 0
					)
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 9)
					(ego
						setPri: 11
						posn: (+ (ego x?) 2) (ego y?)
						illegalBits: 0
					)
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 5)
					(ego
						setPri: 14
						posn: (ego x?) (+ (ego y?) 2)
						illegalBits: 0
					)
					(= fallingIntoLava TRUE)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'look/rock,boulder') (Print 70 0))
			((Said 'look/scout,craft,skull,bow') (if ortegaWorkersLeft (Print 70 1) else (Print 70 2)))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not script)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance ship of PicView
	(properties
		y 135
		x 66
		view 98
		priority 9
		signal ignrAct
	)
)
