;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED)
(include game.sh)
(use Main)
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
		picture 98
	)
	
	(method (init &tmp versionFile)
		(super init:)
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 10 versionFile)
		(FileIO fileClose versionFile)
		(self setScript: speedTest)
	)
	
	(method (doit &tmp i)
		(super doit:)
		(for ((= i 0)) (< i 500) ((++ i))
		)
	)
)

(instance fred of Actor)

(instance speedTest of Script
	
	(method (changeState newState &tmp [str 200])
		(switch (= state newState)
			(0
				(Load RES_VIEW 99)
				(= cycles 1)
			)
			(1
				(= doneTime (GetTime))
				(fred
					view: 99
					setLoop: 0
					illegalBits: 0
					posn: 20 99
					setStep: 1 1
					setSpeed: 0
					setCycle: Forward
					init:
					setMotion: MoveTo 100 100 self
				)
			)
			(2
				(= machineSpeed (- (GetTime) doneTime))
				(= cycles 1)
			)
			(3
				(Message MsgGet NARRATOR NULL NULL NULL 1 @str)
				(Display @str
					p_at 10 80
					p_width 300
					p_color 6
					p_font 3110
					p_mode teJustCenter
				)
				(= seconds 5)
			)
			(4
				(startGame doit:)
			)
		)
	)
)

(instance startGame of Code

	(method (doit &tmp [str 40])
		(= howFast
			(cond 
				(
					(>
						(= howFast
							(cond 
								((> machineSpeed 600) 0)
								((> machineSpeed 550) 1)
								((> machineSpeed 500) 2)
								((> machineSpeed 450) 3)
								((> machineSpeed 400) 4)
								((> machineSpeed 350) 5)
								((> machineSpeed 300) 6)
								((> machineSpeed 275) 7)
								((> machineSpeed 250) 8)
								((> machineSpeed 225) 9)
								((> machineSpeed 200) 10)
								((> machineSpeed 100) 11)
								((> machineSpeed 60) 12)
								((> machineSpeed 40) 13)
								((> machineSpeed 20) 14)
								(else 15)
							)
						)
						11
					)
					2
				)
				((< howFast 8) 0)
				(else 1)
			)
		)
		(theGame
			detailLevel:
			(switch howFast
				(0 0)
				(1 2)
				(else  3)
			)
		)
		(curRoom newRoom: 100)
	)
)
