;;; Sierra Script 1.0 - (do not remove this comment)
(script# FULLLOOP)
(include game.sh)
(use Motion)


(class FullLoop of Cycle
	(properties
		name "Full")
	
	(method (init theObj whoCares)
		(super init: theObj whoCares)
		(= cycleDir dirN)
		(client cel: 0)
		(if (>= argc 2)
			(= caller whoCares)
		)
	)
	
	(method (doit &tmp newCel)
		(cond 
			((> (= newCel (self nextCel:)) (client lastCel:))
				(= cycleDir -1)
				(client cel: (- (client lastCel:) 1))
			)
			((< newCel 0)
				(= completed TRUE)
				(self motionCue:)
			)
			(else (client cel: newCel))
		)
	)
)
