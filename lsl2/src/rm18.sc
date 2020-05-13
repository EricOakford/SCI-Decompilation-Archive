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

(local
	aDoor
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
		((View new:)
			view: 240
			loop: 0
			cel: 0
			posn: 60 31
			setPri: 1
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 240
			loop: 1
			cel: 0
			posn: 196 31
			setPri: 1
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 227
			loop: 1
			cel: 2
			posn: 170 82
			setPri: 4
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 227
			loop: 1
			cel: 1
			posn: 233 124
			setPri: 11
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 227
			loop: 1
			cel: 0
			posn: 98 137
			setPri: 11
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 227
			loop: 1
			cel: 3
			posn: 20 136
			setPri: 11
			ignoreActors:
			addToPic:
		)
		(cond 
			((== prevRoomNum 0) (ego posn: 253 119))
			((== prevRoomNum 14) (ego posn: 253 119))
			((== prevRoomNum 118) (ego posn: 172 146))
		)
		(NormalEgo)
		((= aDoor (AutoDoor new:))
			view: 227
			setLoop: 0
			posn: 172 150
			setPri: 11
			entranceTo: 118
			locked: FALSE
			msgLook: {Through this door, you see a store.}
			init:
		)
		(ego init:)
		(self setRegions: 200 setScript: rm18Script)
	)
)

(instance rm18Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/sign,cup') (Print 18 0))
			(if (Said '[/building,building,airport]') (Print 18 1))
		)
	)
)
