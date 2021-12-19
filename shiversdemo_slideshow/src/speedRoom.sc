;;; Sierra Script 1.0 - (do not remove this comment)
(script# 942)
(include game.sh)
(use Main)
(use SpeedTst)
(use LoadMany)
(use Game)

(public
	speedRoom 0
)

(instance speedRoom of Room
	(method (init)
		(LoadMany RES_VIEW 64980)
		(super init:)
		(= howFast (SpeedTest))
		(MonoOut {howFast = %d} howFast)
		(theGame detailLevel: howFast)
		(curRoom newRoom: transferRoom)
	)
)
