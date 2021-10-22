;;; Sierra Script 1.0 - (do not remove this comment)
;;;;	DPATH.SC		(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: Pablo Ghenis
;;;;
;;;;	D(ynamic)PATH uses a dynamically created list to keep path
;;;;	points.
;;;;	
;;;;	Usage is like other motion classes:
;;;;	
;;;;		(anActor setMotion DPath x1 y1 x2 y2 ...  anOptionalCaller)
;;;;	
;;;;	(also see PATH.SC for static path class)
;;;;

(script#	DPATH)
(include game.sh)
(use Motion)
(use System)

(class DPath of Motion
	
	(properties
		points			NULL	;dynamic list to hold path points
		value				0		;index into path array
	)
	
	(method (init theClient thePoints &tmp i)
		
		(= points (if points else (List new:)))
		;EO: The following line of code causes "Not an Object" errors
;		(= points (or points (List new:)))	;create only once
		
		(if argc	
			
			(= client theClient)
			
			(for
				((= i 0))
				(<= i (- argc 3)) 
				((++ i))
				
				(points add: [thePoints i] [thePoints (++ i)])
			)
			
			
			;final odd argument, if present, is caller
			(if (<= i (- argc 2))
				(= caller [thePoints i])		
			)
		)
		
		(or 
			(points contains: PATHEND)
			(points add: PATHEND)			;terminate only once
		)
		
		(self setTarget:)
		(super init:)
	)
	
	(method (dispose)
		(if (IsObject points) (points dispose:))
		(super dispose:)
	)
	
	(method (setTarget)
		(if (!= (points at: value) PATHEND)
			(= x (points at: value))
			(= y (points at: (++ value)))
			(++ value)
		)
	)
	
	(method (moveDone)
		(if (== (points at: value) PATHEND)
			(super moveDone:)
		else
			(self init:)
		)
	)
);DPath


