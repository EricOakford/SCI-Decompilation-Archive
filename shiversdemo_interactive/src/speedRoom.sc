;;; Sierra Script 1.0 - (do not remove this comment)
(script# 942)
(include sci.sh)
(use Main)
(use SpeedTst)
(use LoadMany)
(use Game)

(public
	speedRoom 0
)

(instance speedRoom of Room
	(properties)
	
	(method (init)
		(LoadMany 128 -556)
		(super init:)
		(= howFast (SpeedTest))
		(MonoOut {howFast = %d} howFast)
		(theGame detailLevel: howFast)
		(curRoom newRoom: global101)
	)
)
