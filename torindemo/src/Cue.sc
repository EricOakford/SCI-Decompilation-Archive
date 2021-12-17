;;; Sierra Script 1.0 - (do not remove this comment)
(script# CUE)
(include game.sh)
(use Main)
(use System)


(class Cue kindof Object
	;;; This class provides a way of delaying a cue until the beginning of the
	;;;	next cycle.  Dynamic instances of Cue can be put on the cuees list

	(properties
		cuee		0		; who to cue
		cuer		0		; who's doin' the cuein'
		register	0		; value to pass to cue
	)
	(method (doit)
		(cuees delete: self)
		(cuee cue: register cuer)
		(self dispose:)
	)
)