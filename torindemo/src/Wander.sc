;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	WANDER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	
;;;;
;;;;	This class causes its client to wander randomly about the screen.
;;;;
;;;;	Classes:
;;;;		Wander


(script# WANDER)
(include game.sh)
(use Motion)


(class Wander kindof Motion
	(properties
		distance 30		;the max distance to move on any one leg of the wander
	)
	
	(method (init theObj dist)
		(if (>= argc 1)		(= client theObj)
			(if (>= argc 2)	(= distance dist)
			)
		)
		(self	setTarget:)
		(super init: client)
;		(super doit:)
	)
	
	(method (setTarget &tmp diam)
		
		;Pick a random position to move to, constrained by 'distance'.
		(= x (+ (client x?) (- distance (Random 0 (= diam (* distance 2))))))
		(= y (+ (client y?) (- distance (Random 0 diam))))
	)
	
	(method (onTarget)
		(return FALSE)	;we're never done wandering
	)
	
	(method (doit)
		;Take the next step.
		(super doit:)
		
		;;If the motion is complete or the client is blocked,
		;;we're finished with this leg.
		(if (client isStopped:)
			(self moveDone:)
		)
	)
	
	(method (moveDone)
		;;; When done with the current leg of wandering, re-init: the motion
		;;; to continue wandering.
		(self init:)
	)
);Wander
