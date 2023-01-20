;;; Sierra Script 1.0 - (do not remove this comment)
(script# OSC)
(include game.sh)
(use Motion)

(class Oscillate kindof Cycle
	;;; Author J.Mark Hood 03/24/89
	;;; Cycles client' cel forward and then backwards then forward again
	;;; continuously without stopping by default else it cycles howMany 
	;;; times and then cues caller

	(properties
		name "Osc"
		howManyCycles -1
		cycleDir	1
	)

	(method (init who howMany whoCares)
		(if (>= argc 2)
			(= howManyCycles howMany)
			(if (>= argc 3)
				(= caller whoCares)
			)
		)
		(super init:who)
	)

	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(if
			(or 
				(> newCel (client lastCel:))
				(< newCel 0)
			)
			(= cycleDir (- cycleDir))
			(self cycleDone:)
		else
			(client cel:newCel)
		)
	)

	(method (cycleDone)
		(if howManyCycles
			(client cel:(self nextCel:))
			(if (> howManyCycles 0)
				(-- howManyCycles)
			)
		else
			(= completed TRUE)
			(self motionCue:)
		)
	)	
)
