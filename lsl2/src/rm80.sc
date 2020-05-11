;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm80 0
)

(local
	cascade
	triedToTurnBack
)
(instance rm80 of Room
	(properties
		picture 80
		horizon 5
	)
	
	(method (init)
		(Load VIEW 728)
		(super init:)
		((View new:)
			view: 728
			loop: 1
			cel: 0
			posn: 175 153
			setPri: 11
			addToPic:
		)
		((View new:)
			view: 728
			loop: 1
			cel: 1
			posn: 154 138
			setPri: 10
			addToPic:
		)
		((= cascade (Prop new:))
			view: 728
			ignoreActors:
			setLoop: 0
			posn: 127 76
			setCycle: Forward
			cycleSpeed: 2
			init:
		)
		(self setRegions: 700 setScript: rm80Script)
		(NormalEgo)
		(if (== prevRoomNum 81)
			(ego posn: 114 124 loop: 1)
		else
			(ego posn: 265 184 loop: 3)
		)
		(ego init:)
	)
)

(instance rm80Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(cond 
			((& (ego onControl:) $0004) (if (== triedToTurnBack FALSE) (= triedToTurnBack TRUE) (Print 80 0)))
			((& (ego onControl:) $0002) (curRoom newRoom: 81))
			(else (= triedToTurnBack FALSE))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/fern,leaf,bush') (Print 80 1))
			(if (Said '[/airport,island,/]') (Print 80 2))
		)
	)
)
