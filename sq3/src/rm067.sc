;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include game.sh)
(use Main)
(use Game)
(use Menu)

(public
	rm067 0
)

(instance rm067 of Room
	(properties
		picture 67
		horizon 77
		north 62
		east 68
		south 70
		west 66
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(super init:)
		(switch prevRoomNum
			(61
				(ego posn: (+ (curRoom horizon?) 2) 80 init:)
			)
			(62
				(ego view: 0 cel: (ego cel?) loop: (ego loop?) init:)
			)
			(63 (ego posn: 240 80 init:))
			(66
				(ego posn: 2 (ego y?) init:)
			)
			(68
				(ego posn: 317 (ego y?) init:)
			)
			(70
				(ego posn: (ego x?) 187 init:)
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
						(== egoOnControl 2)
					)
					(ego setPri: 7 posn: (ego x?) (+ (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 9)
					(ego setPri: 7 posn: (ego x?) (- (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 5) (== egoOnControl 7))
					(ego setPri: 5 posn: (ego x?) (+ (ego y?) 2))
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
	)
	
	(method (newRoom newRoomNumber)
		(if (== script 0)
			(if (== 1 (ego edgeHit?))
				(if (< (ego x?) 105) (= newRoomNumber 61))
				(if (> (ego x?) 210) (= newRoomNumber 63))
			)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)
