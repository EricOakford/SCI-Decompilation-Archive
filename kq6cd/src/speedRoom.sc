;;; Sierra Script 1.0 - (do not remove this comment)
(script# 99)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)
(use DebugHandler)

(public
	speedRoom 0
)

(local
	local0
	local1
)
(instance speedRoom of Room
	(properties
		picture 98
	)
	
	(method (init &tmp temp0)
		(super init:)
		(= temp0 (FileIO fiOPEN {version} 1))
		(FileIO fiREAD_STRING version 10 temp0)
		(FileIO fiCLOSE temp0)
		(if debugging
			(theIconBar enable:)
			(theGame handsOn:)
			(proc911_1)
			(theGame handsOff:)
		else
			(self setScript: speedTest)
		)
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
	
	(method (changeState newState &tmp [temp0 200])
		(switch (= state newState)
			(0
				(Load rsVIEW 99)
				(= cycles 1)
			)
			(1
				(= local1 (GetTime))
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
				(= local0 (- (GetTime) local1))
				(= cycles 1)
			)
			(3
				(Message msgGET 99 0 0 0 1 @temp0)
				(Display
					@temp0
					dsCOORD
					10
					80
					dsWIDTH
					300
					dsCOLOR
					6
					dsFONT
					3110
					dsALIGN
					1
				)
				(= seconds 5)
			)
			(4 (startGame doit:))
		)
	)
)

(instance startGame of Code
	(properties)
	
	(method (doit &tmp [temp0 40])
		(= howFast
			(cond 
				(
					(>
						(= howFast
							(cond 
								((> local0 600) 0)
								((> local0 550) 1)
								((> local0 500) 2)
								((> local0 450) 3)
								((> local0 400) 4)
								((> local0 350) 5)
								((> local0 300) 6)
								((> local0 275) 7)
								((> local0 250) 8)
								((> local0 225) 9)
								((> local0 200) 10)
								((> local0 100) 11)
								((> local0 60) 12)
								((> local0 40) 13)
								((> local0 20) 14)
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
