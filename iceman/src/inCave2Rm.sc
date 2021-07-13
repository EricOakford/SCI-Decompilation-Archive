;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include game.sh)
(use Main)
(use Game)

(public
	inCave2Rm 0
)

(instance inCave2Rm of Room
	(properties
		picture 60
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
	)
)
