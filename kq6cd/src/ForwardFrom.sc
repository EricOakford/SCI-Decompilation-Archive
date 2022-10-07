;;; Sierra Script 1.0 - (do not remove this comment)
(script# 231)
(include sci.sh)
(use Motion)

(public
	ForwardFrom 0
)

(class ForwardFrom of Forward
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		startAt 0
		cycleCount -1
	)
	
	(method (init theClient theStartAt theCycleCount theCaller)
		(= client theClient)
		(client cel: (= startAt theStartAt))
		(if (> argc 2)
			(= cycleCount theCycleCount)
			(if (> argc 3) (= caller theCaller))
		)
	)
	
	(method (cycleDone)
		(client cel: startAt)
		(if (not (-- cycleCount))
			(= completed 1)
			(self motionCue:)
		)
	)
)
