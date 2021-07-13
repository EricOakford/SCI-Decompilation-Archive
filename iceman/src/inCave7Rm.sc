;;; Sierra Script 1.0 - (do not remove this comment)
(script# 43)
(include game.sh)
(use Main)
(use Game)

(public
	inCave7Rm 0
)

(instance inCave7Rm of Room
	(properties
		picture 43
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
	)
)
