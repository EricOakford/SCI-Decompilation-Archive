;;; Sierra Script 1.0 - (do not remove this comment)
(script# WATER)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use System)

(public
	waterReg 0
)

(local
	waterDepth
	thisControl
)
(instance waterReg of Region
	(properties
		name "Water Region"
	)
	
	(method (init)
		(super init:)
		(Load VIEW 5)
		(Load VIEW 6)
		(Load VIEW 7)
		(Load VIEW 8)
		(ego viewer: water)
	)
)

(instance water of Script
	(method (doit)
		(if (!= (= waterDepth (ego onControl: origin)) thisControl)
			(= thisControl waterDepth)
			(if (!= currentStatus egoRidingDolphin)
				(ego setCycle: Walk)
				(switch waterDepth
					(cBLACK
						(= currentStatus egoNormal)
						(ego view: 2 setStep: 3 2)
					)
					(cLCYAN
						(= currentStatus egoInShallowWater)
						(ego view: 5)
					)
					(cLBLUE
						(= currentStatus egoInKneeDeepWater)
						(ego view: 6)
					)
					(cCYAN
						(ego view: 7)
						(= currentStatus egoInWaistDeepWater)
					)
					(cBLUE
						(ego view: 8)
						(= currentStatus egoSwimming)
						(ego setCycle: Forward)
					)
				)
			)
		)
	)
)
