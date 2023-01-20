;;; Sierra Script 1.0 - (do not remove this comment)
(script# RANGEOSC) ;819
(include game.sh)
(use Motion)

(public
	RangeOsc 0
)

(class RangeOsc of Cycle
	(properties
		howManyCycles -1
		firstC 0
		lastC 0
	)
	
	(method (init who howMany fCel lCel whoCares)
		(if (>= argc 2)
			(= howManyCycles howMany)
		)
		(if (>= argc 5)
			(= caller whoCares)
		)
		(super init: who)
		(if (>= argc 3)
			(= firstC fCel)
			(if (>= argc 4)
				(if lCel
					(= lastC lCel)
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
			(if (> howManyCycles 0)
				(-- howManyCycles)
			)
		else
			(= completed TRUE)
			(self motionCue:)
		)
	)
)
