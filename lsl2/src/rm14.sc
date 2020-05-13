;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm14 0
)

(local
	aSign
)
(instance rm14 of Room
	(properties
		picture 14
		horizon 50
		south 18
		west 13
	)
	
	(method (init)
		(Load VIEW 243)
		(super init:)
		((View new:)
			view: 243
			loop: 0
			cel: 1
			posn: 96 52
			setPri: 2
			ignoreActors:
			addToPic:
		)
		((View new:)
			view: 243
			loop: 0
			cel: 0
			posn: 17 57
			setPri: 2
			addToPic:
		)
		((View new:)
			view: 243
			loop: 0
			cel: 0
			posn: 175 57
			setPri: 2
			addToPic:
		)
		((View new:)
			view: 243
			loop: 1
			cel: 1
			posn: 99 73
			setPri: 12
			addToPic:
		)
		((View new:)
			view: 243
			loop: 1
			cel: 2
			posn: 166 81
			setPri: 12
			addToPic:
		)
		((View new:)
			view: 243
			loop: 1
			cel: 0
			posn: 105 134
			setPri: 9
			addToPic:
		)
		((= aSign (Prop new:))
			view: 243
			loop: 2
			cel: 0
			posn: 34 104
			setPri: 12
			setCycle: Forward
			cycleSpeed: 10
			isExtra: 1
			init:
		)
		(cond 
			((== prevRoomNum 0) (ego posn: 312 187))
			((== prevRoomNum 18) (ego posn: 312 187))
			((== prevRoomNum 114) (ego posn: 36 152))
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: 200 setScript: rm14Script)
	)
)

(instance rm14Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl: origin) $0002)
			(curRoom newRoom: 114)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/carpet') (Print 14 0))
			(if (Said '/cup,sign') (Print 14 1))
			(if
			(Said '[/building,convenience,building,airport]')
				(Print 14 2)
			)
		)
		(if (Said 'open/door') (Print 14 3))
	)
)
