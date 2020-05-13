;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include game.sh)
(use Main)
(use Game)
(use Menu)

(public
	rm063 0
)

(instance rm063 of Room
	(properties
		picture 63
		east 64
		south 67
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(super init:)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(switch prevRoomNum
			(64
				(ego posn: 317 (ego y?) init:)
			)
			(67 (ego posn: 80 187 init:))
		)
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
					(ego setPri: 3 posn: (ego x?) (- (ego y?) 6))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 5) (== egoOnControl 7))
					(ego setPri: 5 posn: (- (ego x?) 6) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 9) (== egoOnControl 11))
					(ego setPri: 1 posn: (ego x?) (- (ego y?) 6))
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
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)
