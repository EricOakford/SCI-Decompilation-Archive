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

(local
	traffic
	freeway
	traffic2
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
		((View new:)
			view: 247
			loop: 0
			cel: 0
			posn: 152 29
			setPri: 0
			ignoreActors:
			addToPic:
		)
		((= traffic (Prop new:))
			view: 247
			loop: 1
			cel: 0
			posn: 123 107
			setCycle: Forward
			cycleSpeed: 4
			isExtra: TRUE
			init:
		)
		((= freeway (Prop new:))
			view: 247
			loop: 2
			cel: 1
			posn: 156 93
			setCycle: Forward
			cycleSpeed: 1
			isExtra: TRUE
			init:
		)
		((= traffic2 (Prop new:))
			view: 247
			loop: 3
			posn: 220 73
			setCycle: Forward
			cycleSpeed: 2
			isExtra: TRUE
			init:
		)
		(cond 
			((== prevRoomNum 16) (ego posn: 230 187))
			((== prevRoomNum 0) (ego posn: 230 187))
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm12Script)
	)
)

(instance rm12Script of Script
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
			(if (Said '//') (Print 12 0))
			(if (Said '/freeway,auto') (Print 12 1))
			(if (Said '/building') (Print 12 2))
			(if (Said '[/angeles,sign,brick,fence,airport]')
				(Print 12 3)
			)
		)
	)
)
