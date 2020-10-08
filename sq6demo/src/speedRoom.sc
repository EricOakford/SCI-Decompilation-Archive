;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include game.sh)
(use Main)
(use SpeedTst)
(use LoadMany)
(use Game)
(use System)

(public
	speedRoom 0
)

(instance speedRoom of Room
	
	(method (init)
		(LoadMany RES_VIEW -556)
		(super init:)
		(cond 
			((<= (= howFast (SpeedTest)) 3) (= egoSpeed 3))
			((<= howFast 5) (= egoSpeed 4))
			((<= howFast 6) (= egoSpeed 3))
			((<= howFast 7) (= egoSpeed 2))
			((<= howFast 8) (= egoSpeed 1))
			(else (= egoSpeed 0))
		)
		(theGame
			detailLevel: (cond 
				((> howFast 7) 3)
				((> howFast 5) 7)
				(else 14)
			)
		)
		(if (not (OneOf startingRoom 24 100))
			(theIconBar show:)
			(theGame handsOff:)
		)
		(curRoom newRoom: startingRoom)
	)
)
