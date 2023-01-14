;;; Sierra Script 1.0 - (do not remove this comment)
(script# TIMEDCUE)
(include game.sh)
(use System)


(class TimedCue kindof Script

	;;cue an object after a number of seconds or cycles have passed
	;;Usage:
	;;			(someObj setScript TimedCue 4)		;cue someObj after 4 seconds
	;;			(someObj setScript TimedCue 0 4)		;cue someObj after 4 CYCLES

	(method (init who theSeconds theCycles)	;one mandatory arg
		(super init: who who)							;client=caller=who
		(if (>= argc 2)		(= seconds theSeconds)
			(if (>= argc 3)	(= cycles theCycles)
			)
		)
	)
	(method (cue)
		(self dispose:)
	)
)