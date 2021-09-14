;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include game.sh)
(use Main)
(use Door)
(use Intrface)
(use Game)
(use Actor)
(use System)

(public
	rm18 0
)

(instance rm18 of Room
	(properties
		picture 18
		horizon 113
		north 14
		east 22
		south 22
		west 17
	)
	
	(method (init)
		(Load VIEW 227)
		(Load VIEW 240)
		(super init:)
		(aView1
			loop: 0
			cel: 0
			setPri: 1
			ignoreActors:
			addToPic:
		)
		(aView2
			loop: 1
			cel: 0
			setPri: 1
			ignoreActors:
			addToPic:
		)
		(aView3
			loop: 1
			cel: 2
			setPri: 4
			ignoreActors:
			addToPic:
		)
		(aView4
			loop: 1
			cel: 1
			setPri: 11
			ignoreActors:
			addToPic:
		)
		(aView5
			loop: 1
			cel: 0
			setPri: 11
			ignoreActors:
			addToPic:
		)
		(aView6
			loop: 1
			cel: 3
			setPri: 11
			ignoreActors:
			addToPic:
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 253 119)
			)
			((== prevRoomNum 14)
				(ego posn: 253 119)
			)
			((== prevRoomNum 118)
				(ego posn: 172 146)
			)
		)
		(NormalEgo)
		(aDoor
			setLoop: 0
			setPri: 11
			entranceTo: 118
			locked: FALSE
			msgLook: {Through this door, you see a store.}
			init:
		)
		(ego init:)
		(self setRegions: CITY setScript: rm18Script)
	)
)

(instance rm18Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/sign,cup')
				(Print 18 0)
			)
			(if (Said '[/building,building,airport]')
				(Print 18 1)
			)
		)
	)
)

(instance aView1 of View
	(properties
		y 31
		x 60
		view 240
	)
)

(instance aView2 of View
	(properties
		y 31
		x 196
		view 240
	)
)

(instance aView3 of View
	(properties
		y 82
		x 170
		view 227
	)
)

(instance aView4 of View
	(properties
		y 124
		x 233
		view 227
	)
)

(instance aView5 of View
	(properties
		y 137
		x 98
		view 227
	)
)

(instance aView6 of View
	(properties
		y 136
		x 20
		view 227
	)
)

(instance aDoor of AutoDoor
	(properties
		y 150
		x 172
		view 227
		msgLook {Through this door, you see a store.}
	)
)
