;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	MOVEFWD.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated: J. Mark Hood
;;;;
;;;;	Moves an Actor forward depending on his heading.
;;;;	Cues caller when completed as usual for movers.
;;;;
;;;;	Usage:  theActor setMotion: MoveFwd distance whoToCue
;;;;
;;;;	Modified 4/24/91 to be a kind of PolyPath (JMH)
;;;;
;;;;	Classes:
;;;;		MoveFwd
;;;;


(script# MOVEFWD)
(include game.sh)
(use PolyPath)


(class MoveFwd kindof PolyPath
;;;	(methods
;;;		init
;;;	)

	(method (init actor dist toCall)
		(if argc
			(super init:
				actor 
				(+ (actor x?) (SinMult (actor heading?) dist))
				(- (actor y?) (CosMult (actor heading?) dist))
				(if (>= argc 3) toCall)
			) 
		else
			(super init:)
		)
	)
)