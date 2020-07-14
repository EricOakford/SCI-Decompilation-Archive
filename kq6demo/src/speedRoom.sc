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
	speedCount
	endTime
)
(instance speedRoom of Room
	(properties
		picture 98
	)
	
	(method (init &tmp temp0)
		(super init:)
		(= temp0 (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 10 temp0)
		(FileIO fileClose temp0)
		(self setScript: speedTest)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(= temp0 0)
		(while (< temp0 500)
			(++ temp0)
		)
	)
)

(instance fred of Actor
	(properties)
)

(instance speedTest of Script
	(properties)
	
	(method (changeState newState &tmp [str 200])
		(switch (= state newState)
			(0
				(Load RES_VIEW 99)
				(= cycles 1)
			)
			(1
				(= endTime (GetTime))
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
				(= speedCount (- (GetTime) endTime))
				(= cycles 1)
			)
			(3
				(Message MsgGet NARRATOR 0 0 0 1 @str)
				(Display @str
					p_at 10 80
					p_width 300
					p_color 6
					p_font 3110
					p_mode teJustCenter
				)
				(= seconds 5)
			)
			(4 (startGame doit:))
		)
	)
)

(instance startGame of Code
	(properties)
	
	(method (doit &tmp [str 40])
		(= howFast
			(cond 
				(
					(>
						(= howFast
							(cond 
								((> speedCount 600) 0)
								((> speedCount 550) 1)
								((> speedCount 500) 2)
								((> speedCount 450) 3)
								((> speedCount 400) 4)
								((> speedCount 350) 5)
								((> speedCount 300) 6)
								((> speedCount 275) 7)
								((> speedCount 250) 8)
								((> speedCount 225) 9)
								((> speedCount 200) 10)
								((> speedCount 100) 11)
								((> speedCount 60) 12)
								((> speedCount 40) 13)
								((> speedCount 20) 14)
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
