;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	TRACK.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: 	Pablo Ghenis
;;;;	Updated:	
;;;;
;;;;	Keeps its client at a certain x and y offset relative to the position
;;;;	of the target object.
;;;;
;;;;	Usage: (theTracker setMotion: Track theTrackee xOffset yOffset zOffset theCaller)
;;;;
;;;;	Classes:
;;;;		Track


(script# TRACK)
(include game.sh)
(use Motion)


(class Track  of Motion
	(properties
		who 0
		xOffset 0
		yOffset 0
		zOffset 0
	)
	
	(method (init actor w xo yo zo whoCares)
		(= client actor)
		(= who w)
		(if (>= argc 3)				(= xOffset xo)
			(if (>= argc 4)			(= yOffset yo)
				(if (>= argc 5)		(= zOffset zo)
					(if (>= argc 6)	(= caller whoCares)
					)
				)
			)
		)
		(client 
			ignoreActors:
			illegalBits:	0
		)
		(self doit:)
	)
	
	(method (doit &tmp angle)
		(= angle (who heading?))
		(client
			heading: angle,
			x: (+ (who	x?) xOffset),
			y: (+ (who	y?) yOffset),
			z: (+ (who	z?) zOffset)
		)
		(if (client looper?)
			((client looper?) doit: client angle)
		else
			(DirLoop client angle)
		)
	)
)
