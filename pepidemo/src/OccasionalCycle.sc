;;; Sierra Script 1.0 - (do not remove this comment)
(script# 876)
(include game.sh)
(use Motion)


(class OccasionalCycle of Cycle
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
			bnt      code_0083
			dpToa    waitCycles
			jmp      code_00ac
code_0083:
			lst      temp0
			pushi    #lastCel
			pushi    0
			pToa     client
			send     4
			gt?     
			bt       code_0097
			lst      temp0
			ldi      0
			lt?     
			bnt      code_00a3
code_0097:
			jmp      code_0099
code_0099:
			bnt      code_00a3
			pushi    #cycleDone
			pushi    0
			self     4
			jmp      code_00ac
code_00a3:
			pushi    #cel
			pushi    1
			lst      temp0
			pToa     client
			send     6
code_00ac:
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
