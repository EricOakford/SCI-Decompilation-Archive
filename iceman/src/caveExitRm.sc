;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include game.sh)
(use Main)
(use Motion)
(use Game)

(public
	caveExitRm 0
)

(instance caveExitRm of Room
	(properties
		picture 68
	)
	
	(method (init)
		(super init:)
		(self setRegions: 304)
		(ego init:)
		(if (== prevRoomNum 53)
			(ego posn: 298 110 setMotion: MoveTo 139 110)
		)
	)
)
