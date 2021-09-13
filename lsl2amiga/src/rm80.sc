;;; Sierra Script 1.0 - (do not remove this comment)
(script# 80)
(include sci.sh)
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
	triedToTurnBack
)
(instance rm80 of Rm
	(properties
		picture 80
		horizon 5
	)
	
	(method (init)
		(Load rsVIEW 728)
		(super init:)
		(addToPics add: aPlant1 aPlant2 doit:)
		(aWaterfall setCycle: Fwd cycleSpeed: 2 init:)
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
			((& (ego onControl:) $0004)
				(if (== triedToTurnBack 0)
					(= triedToTurnBack 1)
					(Print 80 0)
				)
			)
			((& (ego onControl:) $0002) (curRoom newRoom: 81))
			(else (= triedToTurnBack 0))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/fern,leaf,bush') (Print 80 1))
			(if (Said '[/airport,island,/]') (Print 80 2))
		)
	)
)

(instance aPlant1 of PV
	(properties
		y 153
		x 175
		view 728
		loop 1
		priority 11
	)
)

(instance aPlant2 of PV
	(properties
		y 138
		x 154
		view 728
		loop 1
		cel 1
		priority 10
	)
)

(instance aWaterfall of Prop
	(properties
		y 76
		x 127
		view 728
		signal $4000
	)
)
