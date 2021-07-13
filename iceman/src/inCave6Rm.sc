;;; Sierra Script 1.0 - (do not remove this comment)
(script# 62)
(include game.sh)
(use Main)
(use Game)

(public
	inCave6Rm 0
)

(instance inCave6Rm of Room
	(properties
		picture 62
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
	)
)
