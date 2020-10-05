;;; Sierra Script 1.0 - (do not remove this comment)
(script# 941)
(include sci.sh)
(use Main)
(use Motion)


(class RandCycle of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		count -1
	)
	
	(method (init param1 theCount theCaller)
		(super init: param1)
		(if (>= argc 2)
			(= count theCount)
			(if (>= argc 3) (= caller theCaller))
		else
			(= count -1)
		)
	)
	
	(method (doit)
		(if
		(>= (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
			(if count
				(if (> count 0) (-- count))
				(client cel: (self nextCel:))
				(= cycleCnt gameTime)
			else
				(self cycleDone:)
			)
		)
	)
	
	(method (nextCel &tmp temp0)
		(return
			(if (!= (NumCels client) 1)
				(while
					(==
						(= temp0 (Random 0 (client lastCel:)))
						(client cel?)
					)
				)
				temp0
			else
				0
			)
		)
	)
	
	(method (cycleDone)
		(= completed 1)
		(if caller (= doMotionCue 1) else (self motionCue:))
	)
)
