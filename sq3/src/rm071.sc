;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Menu)

(public
	rm071 0
)

(instance rm071 of Room
	(properties
		picture 71
		north 72
		west 70
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(super init:)
		(switch prevRoomNum
			(70
				(ego
					init:
					posn:
						2
						(cond 
							((== (ego y?) 74) 75)
							((and (> (ego y?) 87) (< (ego y?) 99)) 99)
							((and (> (ego y?) 118) (< (ego y?) 137)) 137)
							(else (ego y?))
						)
				)
			)
			(72
				(ego
					posn:
						(cond 
							((< (ego x?) 87) 88)
							((> (ego x?) 156) 155)
							(else (ego x?))
						)
						(+ (curRoom horizon?) 10)
					init:
				)
				(theMusic number: 71 loop: -1 priority: 0 play:)
			)
		)
		(TheMenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (doit &tmp egoOnControl)
		(super doit:)
		(if (== (curRoom script?) 0)
			(cond 
				((== (= egoOnControl (ego onControl:)) 3)
					(ego
						setPri: 14
						posn: (ego x?) (+ (ego y?) 2)
						illegalBits: 0
					)
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 5)
					(ego setPri: 9 posn: (ego x?) (ego y?) illegalBits: 0)
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
		(if (Said 'look[/area,around]')
			(if (== prevRoomNum 72) (Print 71 0) else (Print 71 1))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (not script)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)
