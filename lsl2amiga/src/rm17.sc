;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use Actor)
(use System)

(public
	rm17 0
)

(instance rm17 of Room
	(properties
		picture 17
		horizon 5
		north 13
		east 18
		south 21
		west 16
	)
	
	(method (init)
		(Load VIEW 250)
		(super init:)
		(aView1
			loop: 0
			cel: 0
			setPri: 9
			ignoreActors:
			addToPic:
		)
		(aView2
			loop: 0
			cel: 1
			setPri: 7
			ignoreActors:
			addToPic:
		)
		(aView3
			loop: 0
			cel: 2
			setPri: 9
			ignoreActors:
			addToPic:
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 260 187)
			)
			((== prevRoomNum 13)
				(ego posn: 194 76)
			)
			((== prevRoomNum 21)
				(ego posn: 260 187)
			)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm17Script)
	)
)

(instance rm17Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 13)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (or (Said 'look<in') (Said 'get,look/all'))
			(Print 17 0)
		)
		(if (Said 'open/door')
			(Print 17 1)
		)
		(if (Said 'bang')
			(Print 17 2)
		)
		(if (Said 'look>')
			(if (Said '/cup')
				(Print 17 3)
			)
			(if (Said '/sign')
				(Print 17 4)
				(Print 17 5 #font smallFont)
			)
			(if (Said '/door')
				(Print 17 6)
			)
			(if (Said '/building<back')
				(Print 17 7)
			)
			(if (Said '[/cafe,building,building,airport]')
				(Print 17 8)
				(Print 17 9)
			)
		)
	)
)

(instance aView1 of View
	(properties
		y 128
		x 311
		view 250
	)
)

(instance aView2 of View
	(properties
		y 110
		x 18
		view 250
	)
)

(instance aView3 of View
	(properties
		y 72
		x 92
		view 250
	)
)
