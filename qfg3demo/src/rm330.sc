;;; Sierra Script 1.0 - (do not remove this comment)
(script# 330)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm330 0
)

(instance rm330 of Room
	(properties
		picture 330
		vanishingY 1
	)
	
	(method (init)
		((Prop new:)
			view: 330
			loop: 0
			x: 45
			y: 21
			priority: 14
			signal: 16
			init:
			setCycle: Forward
		)
		(cheesecakeFan init: cycleSpeed: 20 setCycle: Forward)
		(rajahTail init: setCycle: OccasionalCycle self 5 70 180)
		(rajahFace init: setCycle: Forward)
		(rajahArm init: setCycle: OccasionalCycle self 5 70 180)
		(super init:)
		(self setScript: seeMeGo)
	)
)

(instance seeMeGo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Display {TO THE THRONE ROOM OF}
					p_font 2510
					p_at 56 156
					p_color 0
				)
				(Display {THE LIONTAUR KING OF TARNA}
					p_font 2510
					p_at 31 173
					p_color 0
				)
				(Display {TO THE THRONE ROOM OF}
					p_font 2510
					p_at 55 155
					p_color 42
				)
				(Display {THE LIONTAUR KING OF TARNA}
					p_font 2510
					p_at 30 172
					p_color 42
				)
				(= seconds 2)
			)
			(1 (= seconds 7))
			(2
				(cast eachElementDo: #dispose)
				(curRoom newRoom: 740)
			)
		)
	)
)

(instance cheesecakeFan of Prop
	(properties
		x 150
		y 102
		view 331
		loop 1
		priority 5
		signal fixPriOn
	)
)

(instance rajahFace of Prop
	(properties
		x 219
		y 13
		view 332
		cycleSpeed 10
	)
)

(instance rajahArm of Prop
	(properties
		x 202
		y 78
		view 332
		loop 1
		cycleSpeed 10
	)
)

(instance rajahTail of Prop
	(properties
		x 164
		y 104
		view 332
		loop 2
		priority 9
		signal fixPriOn
	)
)

(class OccasionalCycle of Cycle
	;EO: This class is custom to QFG3, so these were identified only as "BAD_SELECTOR".
	; Referring to the equivalent class in the full game, they have been identified.
	(properties
		lowerEnd 5
		upperEnd 6
		waitCycles 0
	)
	
	(method (init theClient param2 theCycleDir theLowerEnd theUpperEnd)
		(if (>= argc 2)
			(= client theClient)
			(if (>= argc 3)
				(= cycleDir theCycleDir)
				(if (>= argc 4)
					(= lowerEnd theLowerEnd)
					(if (>= argc 5) (= upperEnd theUpperEnd))
				)
			)
		)
		(= completed (= cycleCnt 0))
		(self cycleDone:)
	)
	
	(method (doit &tmp temp0)
		(asm
			pushi    #nextCel
			pushi    0
			self     4
			sat      temp0
			pToa     waitCycles
			bnt      code_01c8
			dpToa    waitCycles
			jmp      code_01f1
code_01c8:
			lst      temp0
			pushi    #lastCel
			pushi    0
			pToa     client
			send     4
			gt?     
			bt       code_01dc
			lst      temp0
			ldi      0
			lt?     
			bnt      code_01e8
code_01dc:
			jmp      code_01de
code_01de:
			bnt      code_01e8
			pushi    #cycleDone
			pushi    0
			self     4
			jmp      code_01f1
code_01e8:
			pushi    #cel
			pushi    1
			lst      temp0
			pToa     client
			send     6
code_01f1:
			ret     
		)
	)
	
	(method (cycleDone)
		(= waitCycles (Random lowerEnd upperEnd))
		(client
			cel: (if (= cycleDir 1) 0 else (client lastCel:))
		)
		(= cycleCnt (GetTime))
	)
)
