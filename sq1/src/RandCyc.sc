;;; Sierra Script 1.0 - (do not remove this comment)
(script# RANDCYC)
(include game.sh)
(use Main)
(use Motion)


(class RandCycle of Cycle
	(properties
		count -1
	)
	
	(method (init obj theTime whoCares)
		(super init: obj)
		(if (>= argc 2)
			(= count theTime)
			(if (>= argc 3) (= caller whoCares))
		else
			(= count -1)
		)
	)
	
	(method (doit)
		(++ cycleCnt)
		(if (> cycleCnt (client cycleSpeed?))
			(if count
				(if (> count 0) (-- count))
				(client cel: (self nextCel:))
				(= cycleCnt 0)
			else
				(self cycleDone:)
			)
		)
	)
	
	(method (nextCel &tmp newCel)
		(return
			(if (!= (NumCels client) 1)
				(while (== (= newCel (Random 0 (client lastCel:))) (client cel?)))
				newCel
			else
				FALSE
			)
		)
	)
	
	(method (cycleDone)
		(= completed TRUE)
		(if caller
			(= doMotionCue TRUE)
		else
			(self motionCue:)
		)
	)
)
