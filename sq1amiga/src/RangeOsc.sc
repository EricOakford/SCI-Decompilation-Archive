;;; Sierra Script 1.0 - (do not remove this comment)
(script# 819)
(include sci.sh)
(use Motion)

(public
	RangeOsc 0
)

(class RangeOsc of Cycle
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		howManyCycles -1
		firstC 0
		lastC 0
	)
	
	(method (init param1 theHowManyCycles theFirstC theLastC theCaller)
		(if (>= argc 2) (= howManyCycles theHowManyCycles))
		(if (>= argc 5) (= caller theCaller))
		(super init: param1)
		(if (>= argc 3)
			(= firstC theFirstC)
			(if (>= argc 4)
				(if theLastC
					(= lastC theLastC)
				else
					(= lastC (client lastCel:))
				)
			else
				(= lastC (client lastCel:))
			)
		)
		(client cel: firstC)
	)
	
	(method (doit &tmp rangeOscNextCel)
		(if
			(or
				(> (= rangeOscNextCel (self nextCel:)) lastC)
				(< rangeOscNextCel firstC)
			)
			(= cycleDir (- cycleDir))
			(self cycleDone:)
		else
			(client cel: rangeOscNextCel)
		)
	)
	
	(method (cycleDone)
		(if howManyCycles
			(client cel: (self nextCel:))
			(if (> howManyCycles 0) (-- howManyCycles))
		else
			(= completed 1)
			(self motionCue:)
		)
	)
)
