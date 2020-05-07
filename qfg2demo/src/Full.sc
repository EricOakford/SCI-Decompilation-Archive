;;; Sierra Script 1.0 - (do not remove this comment)
(script# FULL)
(include game.sh)
(use Motion)


(class Full of Cycle
	(properties)
	
	(method (init param1 theCaller)
		(super init: param1 theCaller)
		(= cycleDir 1)
		(client cel: 0)
		(if (>= argc 2) (= caller theCaller))
	)
	
	(method (doit &tmp fullNextCel)
		(cond 
			(
			(> (= fullNextCel (self nextCel:)) (client lastCel:)) (= cycleDir -1) (client cel: (- (client lastCel:) 1)))
			((< fullNextCel 0) (= completed 1) (self motionCue:))
			(else (client cel: fullNextCel))
		)
	)
)
