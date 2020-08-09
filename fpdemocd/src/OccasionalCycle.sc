;;; Sierra Script 1.0 - (do not remove this comment)
(script# OCC_CYC)
(include game.sh)
(use Motion)


(class OccasionalCycle of Cycle
	(properties
		lowerEnd 5
		upperEnd 6
		lowCycles 1
		upperCycles 1
		numCycles 1
		waitCycles 0
	)
	
	(method (init actor dir theLowerEnd theUpperEnd theLowCycles theUpperCycles)
		(if argc
			(= client actor)
			(if (>= argc 2)
				(= cycleDir dir)
				(if (>= argc 3)
					(= lowerEnd theLowerEnd)
					(if (>= argc 4)
						(= upperEnd theUpperEnd)
						(if (>= argc 5)
							(= lowCycles theLowCycles)
							(if (>= argc 6)
								(= upperCycles theUpperCycles)
							)
						)
					)
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
			bnt      code_009c
			dpToa    waitCycles
			jmp      code_00d6
code_009c:
			lst      temp0
			pushi    #lastCel
			pushi    0
			pToa     client
			send     4
			gt?     
			bt       code_00b0
			lst      temp0
			ldi      0
			lt?     
			bnt      code_00cd
code_00b0:
			jmp      code_00b2
code_00b2:
			bnt      code_00cd
			pToa     numCycles
			bnt      code_00c5
			dpToa    numCycles
			pushi    #cel
			pushi    1
			lst      temp0
			pToa     client
			send     6
			jmp      code_00d6
code_00c5:
			pushi    #cycleDone
			pushi    0
			self     4
			jmp      code_00d6
code_00cd:
			pushi    #cel
			pushi    1
			lst      temp0
			pToa     client
			send     6
code_00d6:
			ret     
		)
	)
	
	(method (cycleDone)
		(= waitCycles (Random lowerEnd upperEnd))
		(= numCycles (Random lowCycles upperCycles))
		(client
			cel: (if (= cycleDir 1) 0 else (client lastCel:))
		)
		(= cycleCnt (GetTime))
	)
)
