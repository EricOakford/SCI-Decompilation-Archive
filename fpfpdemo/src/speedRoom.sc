;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED) ;28
(include game.sh)
(use Main)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
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
		picture 780
	)
	
	(method (init)
		(LoadMany RES_VIEW 902)
		(super init:)
		(theGame handsOff:)
		(ego setSpeed: 0)
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

(instance fred of Actor
	(properties
		view 902
	)
)

(instance speedTest of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fred
					setLoop: 0
					illegalBits: 0
					posn: 0 0
					setStep: 1 1
					setCycle: Forward
					init:
				)
				(= cycles 1)
			)
			(1
				(= doneTime (GetTime))
				(fred setMotion: MoveTo 320 190)
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
			((> machineSpeed 160)
				(= howFast 0)
			)
			((> machineSpeed 150)
				(= howFast 1)
			)
			((> machineSpeed 140)
				(= howFast 2)
			)
			((> machineSpeed 130)
				(= howFast 3)
			)
			((> machineSpeed 120)
				(= howFast 4)
			)
			((> machineSpeed 110)
				(= howFast 5)
			)
			((> machineSpeed 100)
				(= howFast 6)
			)
			((> machineSpeed 90)
				(= howFast 7)
			)
			((> machineSpeed 80)
				(= howFast 8)
			)
			((> machineSpeed 70)
				(= howFast 9)
			)
			((> machineSpeed 60)
				(= howFast 10)
			)
			((> machineSpeed 50)
				(= howFast 11)
			)
			((> machineSpeed 40)
				(= howFast 12)
			)
			((> machineSpeed 30)
				(= howFast 13)
			)
			((> machineSpeed 20)
				(= howFast 14)
			)
			(else
				(= howFast 15)
			)
		)
		(theGame detailLevel:
			(cond 
				((<= howFast 3) 1)
				((<= howFast 10) 2)
				(else 3)
			)
		)
		(ego setSpeed: 7)
		(curRoom newRoom: (if debugging 29 else 100))
	)
)
