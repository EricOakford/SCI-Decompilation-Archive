;;; Sierra Script 1.0 - (do not remove this comment)
(script# 67)
(include game.sh)
(use Main)
(use Game)

(public
	inCave5Rm 0
)

(instance inCave5Rm of Room
	(properties
		picture 67
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
	)
)
