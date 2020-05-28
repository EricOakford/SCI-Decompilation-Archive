;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED)
(include game.sh)
(use Main)
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
		picture 888
		style IRISIN
	)
	
	(method (init)
		(super init:)
		(theGame handsOff:)
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
			(0
				(= cycles 1)
			)
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
	
	(method (doit)
		(= howFast
			(cond 
				((> machineSpeed 60) slow)
				((> machineSpeed 45) medium)
				((> machineSpeed 30) fast)
				(else fastest)
			)
		)
		(theGame detailLevel:
			(switch howFast
				(slow 3)
				(medium 4)
				(else  5)
			)
		)
		(ego setSpeed: (= egoSpeed 6))
		(curRoom newRoom: transferRoom)
	)
)
