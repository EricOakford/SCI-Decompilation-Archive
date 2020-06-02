;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED)
(include game.sh)
(use Main)
(use Procs)
(use Game)
(use System)

(public
	speedRoom 0
)

(local
	machineSpeed
	doneTime
)
(instance speedRoom of Room
	(properties
		picture 400
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(theGame setSpeed: 0)
		(self setScript: speedTest)
	)
	
	(method (doit &tmp i)
		(super doit:)
		(= i 0)
		(while (< i 500)
			(++ i)
		)
	)
)

(instance speedTest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(= doneTime (GetTime))
				(= cycles 50)
			)
			(2
				(= machineSpeed (- (GetTime) doneTime))
				(startGame doit:)
			)
		)
	)
)

(instance startGame of Code
	(properties)
	
	(method (doit &tmp [str 100])
		(cond 
			((> machineSpeed 60) (= howFast slow))
			((> machineSpeed 45) (= howFast medium))
			((> machineSpeed 30) (= howFast fast))
			(else (= howFast fastest))
		)
		(theGame
			detailLevel: (cond 
				((== howFast slow) 1)
				((== howFast medium) 2)
				(else 3)
			)
		)
		(theGame setSpeed: 6)
		(= egoSpeed speed)
		(RedrawCast)
		(curRoom newRoom: startingRoom)
	)
)
