;;; Sierra Script 1.0 - (do not remove this comment)
(script# MOVEFWD)
;;;;
;;;;	MOVEFWD.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: J.Mark Hood
;;;;
;;;;	Moves an Actor forward depending on his heading.
;;;; Cues caller when completed as usual for movers.
;;;;	Size:
;;;;		194 bytes for initial module.
;;;;
;;;;	Classes:
;;;;		MoveFwd
;;;;
;;;;	Usage:
;;;;		anActor setMotion: MoveFwd 20 whoToCue
;;;;

(include game.sh)
(use Motion)


(class MoveFwd kindof MoveTo
;;;	(methods
;;;		init
;;;	)

	(method (init actor dist toCall)
		(super init:
			actor 
			(+ (actor x?) (SinMult (actor heading?) dist))
			(- (actor y?) (CosMult (actor heading?) dist))
			(if (>= argc 3) toCall)
		) 
	)
)
