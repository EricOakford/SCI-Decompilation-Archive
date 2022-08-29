;;; Sierra Script 1.0 - (do not remove this comment)
(script# SPEED)
(include game.sh)
(use Main)
(use Print)
(use Game)
(use System)

(public
	speedRoom 0
)

(local
	local0
)
(instance speedRoom of Room
	
	(method (init)
		(super init:)
		(self setScript: speedTest)
	)
	
	(method (doit &tmp i)
		(super doit:)
		(for ((= i 0)) (< i 900) ((++ i))
		)
	)
)

(instance speedTest of Script
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(while (u> (GetTime) -192))
				(= cycles 1)
			)
			(1
				(= register (GetTime))
				(= cycles 50)
			)
			(2
				(= temp0 (- (GetTime) register))
				(startGame doit: temp0)
			)
		)
	)
)

(instance startGame of Code
	(properties)
	
	(method (doit machineSpeed &tmp [str 8] roomNum)
		(cond 
			((> machineSpeed 160) (= howFast 0))
			((> machineSpeed 150) (= howFast 1))
			((> machineSpeed 140) (= howFast 2))
			((> machineSpeed 130) (= howFast 3))
			((> machineSpeed 120) (= howFast 4))
			((> machineSpeed 110) (= howFast 5))
			((> machineSpeed 100) (= howFast 6))
			((> machineSpeed 90) (= howFast 7))
			((> machineSpeed 80) (= howFast 8))
			((> machineSpeed 70) (= howFast 9))
			((> machineSpeed 60) (= howFast 10))
			((> machineSpeed 50) (= howFast 11))
			((> machineSpeed 40) (= howFast 12))
			((> machineSpeed 30) (= howFast 13))
			((> machineSpeed 20) (= howFast 14))
			(else (= howFast 15))
		)
		(theGame
			detailLevel: (cond 
				((<= howFast 3) 1)
				((<= howFast 10) 2)
				(else 3)
			)
		)
		(= str 0)
		(= roomNum
			(Print
				font: smallFont
				addText: {Where to, Gabriel?}
				addEdit: @str 5 85
				addButton: 210 {_____Book Shop_____} 0 20
				addButton: 230 {____Police Lobby___} 101 20
				addButton: 240 {__Mosleys' Office__} 0 34
				addButton: 250 {____Voodoo Shop____} 101 34
				addButton: 430 {__Jackson Square___} 0 48
				addButton: 470 {_____Lake Pont.____} 101 48
				addButton: 999 {____Day One Demo___} 0 62
				addButton: 260 {__Voodoo Museum__} 101 62
				addButton: 380 { Grandma's Parlor_} 0 76
				addButton: 390 {__Grandma's Attic__} 101 76
				addButton: 490 {_____Bayou Maze____} 0 90
				addButton: 280 {Cazaunoux Interior} 101 90
				addButton: 330 {___Tulane Lect.___} 0 104
				addButton: -100 {_____< Restore >_____} 0 118
				init:
			)
		)
		(if str
			(= roomNum (ReadNumber @str))
		)
		(= isDemo FALSE)
		(cond 
			((== roomNum -100)
				(theGame restore:)
			)
			((== roomNum 999)
				(= roomNum 375)
				(= currentDay 1)
				(= isDemo TRUE)
			)
			(else
				(= currentDay
					(Print
						addText: {Which day?}
						addButton: 1 {Day 1} 0 12
						addButton: 2 {Day 2} 0 26
						addButton: 3 {Day 3} 0 40
						addButton: 4 {Day 4} 0 54
						addButton: 5 {Day 5} 0 68
						addButton: 6 {Day 6} 0 82
						addButton: 7 {Day 7} 50 12
						addButton: 8 {Day 8} 50 26
						addButton: 9 {Day 9} 50 40
						addButton: 10 {Day 10} 50 54
						addButton: 11 {Day 11} 50 68
						init:
					)
				)
			)
		)
		(curRoom newRoom: roomNum)
		(theIconBar enable:)
	)
)
