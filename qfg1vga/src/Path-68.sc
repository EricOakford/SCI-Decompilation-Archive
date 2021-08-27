;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include game.sh) (include "68.shm")
(use Main)
(use Procs)
(use Feature)
(use Game)

(public
	rm68 0
)

(instance rm68 of Room
	(properties
		picture 68
		style DISSOLVE
	)
	
	(method (init)
		(self setRegions: FOREST)
		(super init:)
		(StatusLine enable:)
		(features add: avalanche eachElementDo: #init doit:)
		;UPGRADE
;;;		(avalanche init:)
		
		(NormalEgo)
	)
	
	(method (dispose)
		(Bset fBeenIn68)
		(super dispose:)
	)
)

(instance avalanche of Feature
	(properties
		x 290
		y 100
		noun N_AVALANCHE
	)
	
	(method (init)
		(= onMeCheck cLBLUE)
		(super init:)
	)
	
	(method (dispose)
		(super dispose:)
	)
)
