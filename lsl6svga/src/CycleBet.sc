;;; Sierra Script 1.0 - (do not remove this comment)
(script# 89)
(include sci.sh)
(use Main)
(use Motion)


(class CycleBet of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		clientSaveLoop 0
		startCel 0
		stopCel 1
		howManyTimes 1
	)
	
	(method (init param1 param2 param3 param4 theCaller &tmp clientLastCel)
		(super init: param1)
		(if (== param4 -1)
			(= howManyTimes -1)
		else
			(= howManyTimes (* param4 2))
		)
		(if (> argc 4) (= caller theCaller))
		(= clientLastCel (client lastCel:))
		(= startCel (if (< param2 0) 0 else param2))
		(= stopCel
			(if (> param3 clientLastCel) clientLastCel else param3)
		)
		(= cycleDir (if (> param3 param2) 1 else -1))
		(client cel: startCel)
	)
	
	(method (doit &tmp temp0)
		(if
		(>= (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
			(client cel: (+ (client cel?) cycleDir))
			(= cycleCnt gameTime)
			(if
				(or
					(== (client cel?) stopCel)
					(== (client cel?) startCel)
				)
				(self cycleDone:)
			)
		)
	)
	
	(method (cycleDone)
		(cond 
			((== howManyTimes -1)
				(if (== cycleDir 1)
					(= cycleDir -1)
				else
					(= cycleDir 1)
				)
			)
			(howManyTimes
				(if (== cycleDir 1)
					(= cycleDir -1)
				else
					(= cycleDir 1)
				)
				(= howManyTimes (- howManyTimes 1))
			)
			(else (= completed 1) (self motionCue:))
		)
	)
)
