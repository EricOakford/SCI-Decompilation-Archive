;;; Sierra Script 1.0 - (do not remove this comment)
(script# PCYCLE)
(include game.sh)
(use Motion)


(class PCycle of CycleTo
	(properties
		startPal 0
		endPal 0
		howMany 1
	)
	
	(method (init theClient theEndPal theCaller theCaller_2 theCaller_3)
		(if argc
			(= client theClient)
			(cond 
				(
					(or
						(== argc 2)
						(and (== argc 3) (IsObject theCaller))
					)
					(= startPal (client palette?))
					(= endPal theEndPal)
					(if (== argc 3) (= caller theCaller))
				)
				(
					(or
						(== argc 3)
						(and (== argc 4) (IsObject theCaller_2))
					)
					(= startPal theEndPal)
					(= endPal theCaller)
					(if (== argc 4) (= caller theCaller_2))
				)
				((>= argc 4)
					(= startPal theEndPal)
					(= endPal theCaller)
					(= howMany theCaller_2)
					(if (== argc 5) (= caller theCaller_3))
				)
			)
		)
		(= cycleDir (if (< endPal startPal) -1 else 1))
		(= completed (= cycleCnt 0))
	)
	
	(method (doit)
		(if (>= (++ cycleCnt) (client cycleSpeed?))
			(= cycleCnt 0)
			(client palette: (self nextCel:) forceUpd:)
			(if (not howMany) (= completed 1) (self cycleDone:))
		)
	)
	
	(method (nextCel &tmp theStartPal)
		(cond 
			(
				(>
					(= theStartPal (+ (client palette?) cycleDir))
					endPal
				)
				(= theStartPal startPal)
				(-- howMany)
			)
			((< theStartPal startPal) (= theStartPal endPal) (-- howMany))
		)
		(if (not howMany) (= theStartPal (client palette?)))
		(return theStartPal)
	)
)
