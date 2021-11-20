;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64939)
(include sci.sh)
(use Motion)


(class Osc of Cycle
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		howManyCycles -1
	)
	
	(method (init param1 theHowManyCycles theCaller)
		(if (>= argc 2)
			(= howManyCycles theHowManyCycles)
			(if (>= argc 3) (= caller theCaller))
		)
		(super init: param1)
	)
	
	(method (doit &tmp oscNextCel)
		(if
		(!= (= oscNextCel (self nextCel:)) (client cel?))
			(if
			(or (> oscNextCel clientLastCel) (< oscNextCel 0))
				(= cycleDir (- cycleDir))
				(self cycleDone:)
			else
				(client cel: oscNextCel)
			)
		)
	)
	
	(method (cycleDone)
		(if howManyCycles
			(if (> howManyCycles 0) (-- howManyCycles))
		else
			(= completed 1)
			(self motionCue:)
		)
	)
)
