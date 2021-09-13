;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include sci.sh)
(use Main)
(use AirplaneActor)
(use Intrface)
(use Game)
(use Actor)
(use System)

(public
	rm58 0
)

(local
	local0
	triedToLeave
)
(instance blockWest of Blk
	(properties
		top 129
		left -20
		bottom 136
		right -10
	)
)

(instance rm58 of Rm
	(properties
		picture 58
		horizon 1
	)
	
	(method (init)
		(Load rsVIEW 511)
		(Load rsVIEW 600)
		(super init:)
		(addToPics add: aStewardess doit:)
		(aPlane startY: 7 endY: 0 delayMin: 2 delayMax: 3 init:)
		(self setRegions: 500 setScript: rm58Script)
		(blockWest init:)
		(NormalEgo 0)
		(ego posn: 10 132 observeBlocks: blockWest init:)
	)
)

(instance rm58Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) $0002) (curRoom newRoom: 61))
		(if (& (ego onControl:) $0004)
			(if (not triedToLeave) (= triedToLeave 1) (Print 58 0))
		else
			(= triedToLeave 0)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if
		(and (Said 'look>') (Said '[/airport,airline]'))
			(Print 58 1)
			(Print 58 2 #at -1 130)
		)
	)
)

(instance aStewardess of PV
	(properties
		y 132
		x 311
		view 600
		loop 1
		cel 4
	)
)

(instance aPlane of Airplane
	(properties)
)
