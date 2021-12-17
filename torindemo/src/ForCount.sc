;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	FORCOUNT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc., 1993
;;;;
;;;;	Author:	J. Mark Hood
;;;;	Updated:	
;;;;
;;;;	Cycles its client a specified number of times.
;;;;	Usage is:
;;;;
;;;;		(theProp setCycle: ForwardCounter numOfCycles whoCares)
;;;;
;;;;	Classes:
;;;;		ForwardCounter


(script# FORCOUNT)
(include game.sh)
(use Motion)


(class ForwardCounter kindof Forward
	(properties
		count		0
	)

	(method (init theObj num whoCares)
		(super init: theObj)
		(if (>= argc 2)			(= count num)
			(if (>= argc 3)		(= caller whoCares)
			)
		)
	); init

	(method (cycleDone)
		(if (-- count)
			(super cycleDone:)
		else
			(= completed TRUE)
			(self motionCue:)
		)
	); cycleDone

); ForwardCounter