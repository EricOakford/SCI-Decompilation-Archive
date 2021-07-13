;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use Game)

(public
	nwDeadEndRm 0
)

(instance nwDeadEndRm of Room
	(properties
		picture 65
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
	)
)
