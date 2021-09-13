;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm12 0
)

(instance rm12 of Room
	(properties
		picture 12
		horizon 5
		east 13
		south 16
		west 11
	)
	
	(method (init)
		(Load VIEW 247)
		(super init:)
		(aView1
			loop: 0
			cel: 0
			setPri: 0
			ignoreActors:
			addToPic:
		)
		(aStreet
			loop: 1
			cel: 0
			setCycle: Forward
			cycleSpeed: 4
			isExtra: TRUE
			init:
		)
		(aFreeway1
			loop: 2
			cel: 1
			setCycle: Forward
			cycleSpeed: 1
			isExtra: TRUE
			init:
		)
		(aFreeway2
			loop: 3
			setCycle: Forward
			cycleSpeed: 2
			isExtra: TRUE
			init:
		)
		(cond 
			((== prevRoomNum 16)
				(ego posn: 230 187)
			)
			((== prevRoomNum 0)
				(ego posn: 230 187)
			)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm12Script)
	)
)

(instance rm12Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/hill')	;EO: fixed said spec
				(Print 12 0)
			)
			(if (Said '/freeway,auto')
				(Print 12 1)
			)
			(if (Said '/building')
				(Print 12 2)
			)
			(if (Said '[/angeles,sign,brick,fence,airport]')
				(Print 12 3)
			)
		)
	)
)

(instance aView1 of View
	(properties
		y 29
		x 152
		view 247
	)
)

(instance aStreet of Prop
	(properties
		y 107
		x 123
		view 247
	)
)

(instance aFreeway1 of Prop
	(properties
		y 93
		x 156
		view 247
	)
)

(instance aFreeway2 of Prop
	(properties
		y 73
		x 220
		view 247
	)
)
