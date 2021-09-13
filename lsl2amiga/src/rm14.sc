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
		(aView1
			loop: 0
			cel: 1
			setPri: 2
			ignoreActors:
			addToPic:
		)
		(aView2
			loop: 0
			cel: 0
			setPri: 2
			addToPic:
		)
		(aView3
			loop: 0
			cel: 0
			setPri: 2
			addToPic:
		)
		(aView4
			loop: 1
			cel: 1
			setPri: 12
			addToPic:
		)
		(aView5
			loop: 1
			cel: 2
			setPri: 12
			addToPic:
		)
		(aView6
			loop: 1
			cel: 0
			setPri: 9
			addToPic:
		)
		(aSign
			loop: 2
			cel: 0
			setPri: 12
			setCycle: Forward
			cycleSpeed: 10
			isExtra: TRUE
			init:
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 312 187)
			)
			((== prevRoomNum 18)
				(ego posn: 312 187)
			)
			((== prevRoomNum 114)
				(ego posn: 36 152)
			)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm14Script)
	)
)

(instance rm14Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl: origin) cBLUE)
			(curRoom newRoom: 114)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/carpet')
				(Print 14 0)
			)
			(if (Said '/cup,sign')
				(Print 14 1)
			)
			(if (Said '[/building,convenience,building,airport]')
				(Print 14 2)
			)
		)
		(if (Said 'open/door')
			(Print 14 3)
		)
	)
)

(instance aView1 of View
	(properties
		y 52
		x 96
		view 243
	)
)

(instance aView2 of View
	(properties
		y 57
		x 17
		view 243
	)
)

(instance aView3 of View
	(properties
		y 57
		x 175
		view 243
	)
)

(instance aView4 of View
	(properties
		y 73
		x 99
		view 243
	)
)

(instance aView5 of View
	(properties
		y 81
		x 166
		view 243
	)
)

(instance aView6 of View
	(properties
		y 134
		x 105
		view 243
	)
)

(instance aSign of Prop
	(properties
		y 104
		x 34
		view 243
	)
)
