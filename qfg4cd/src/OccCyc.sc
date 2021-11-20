;;; Sierra Script 1.0 - (do not remove this comment)
(script# 33)
(include sci.sh)
(use Motion)


(class OccCyc of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		lowerEnd 5
		upperEnd 6
		waitCycles 0
		cycleCount 0
		numOfLoops 0
	)
	
	(method (init theClient param2 theCycleDir theLowerEnd theUpperEnd theNumOfLoops theCaller)
		(if (>= argc 2)
			(= client theClient)
			(if (>= argc 3)
				(= cycleDir theCycleDir)
				(if (>= argc 4)
					(= lowerEnd theLowerEnd)
					(if (>= argc 5)
						(= upperEnd theUpperEnd)
						(if (>= argc 6)
							(= numOfLoops theNumOfLoops)
							(if (>= argc 7) (= caller theCaller))
						)
					)
				)
			)
		)
		(= completed (= cycleCnt 0))
		(self cycleDone:)
	)
	
	(method (doit &tmp occCycNextCel)
		(= occCycNextCel (self nextCel:))
		(cond 
			(waitCycles (-- waitCycles))
			(
				(and
					(or
						(> occCycNextCel (client lastCel:))
						(< occCycNextCel 0)
					)
					(< occCycNextCel 0)
				)
				(self cycleDone:)
			)
			(else (client cel: occCycNextCel))
		)
	)
	
	(method (cycleDone)
		(if (and numOfLoops (== cycleCount numOfLoops))
			(client
				cel: (if (= cycleDir 1) 0 else (client lastCel:))
				cycler: 0
			)
			(if caller (caller cue:))
			(self dispose:)
		else
			(++ cycleCount)
			(= waitCycles (Random lowerEnd upperEnd))
			(client
				cel: (if (= cycleDir 1) 0 else (client lastCel:))
			)
			(= cycleCnt (GetTime))
		)
	)
)
