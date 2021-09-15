;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include game.sh)
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
	leaveMsg
)
(instance blockWest of Block
	(properties
		top 129
		left -20
		bottom 136
		right -10
	)
)

(instance rm58 of Room
	(properties
		picture 58
		horizon 1
	)
	
	(method (init)
		(Load VIEW 511)
		(Load VIEW 600)
		(super init:)
		(addToPics add: aStewardess doit:)
		(aPlane
			startY: 7
			endY: 0
			delayMin: 2
			delayMax: 3
			init:
		)
		(self setRegions: AIRPORT setScript: rm58Script)
		(blockWest init:)
		(NormalEgo loopE)
		(ego posn: 10 132 observeBlocks: blockWest init:)
	)
)

(instance rm58Script of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cBLUE)
			(curRoom newRoom: 61)
		)
		(if (& (ego onControl:) cGREEN)
			(if (not leaveMsg)
				(= leaveMsg TRUE)
				(Print 58 0)
			)
		else
			(= leaveMsg FALSE)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (and (Said 'look>') (Said '[/airport,airline]'))
			(Print 58 1)
			(Print 58 2 #at -1 130)
		)
	)
)

(instance aStewardess of PicView
	(properties
		y 132
		x 311
		view 600
		loop 1
		cel 4
	)
)

(instance aPlane of Airplane)
