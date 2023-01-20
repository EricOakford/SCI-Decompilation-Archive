;;; Sierra Script 1.0 - (do not remove this comment)
(script# FORCOUNT)
(include game.sh)
(use Motion)

;;; ForwardCounter Cycle Class
;;; Author J.Mark Hood 
;;; Saves states in scripts by cycleing a given number of times
;;; then cueing on completion.
;;; Usage : propName setCycle:ForwardCounter numOfCycles whoCares

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



