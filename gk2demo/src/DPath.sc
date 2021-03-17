;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DPATH.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1989
;;;;
;;;;	Author: 	Pablo Ghenis
;;;;	Updated:	Steve Conrad
;;;;
;;;;	D(ynamic)PATH uses a dynamically created list to keep path
;;;;	points.
;;;;	
;;;;	Usage is like other motion classes:
;;;;	
;;;;		(anActor setMotion: DPath x1 y1 x2 y2 ...  anOptionalCaller)
;;;;	
;;;;	(also see PATH.SC for static path class)
;;;;
;;;;	Classes:
;;;;		DPath


(script#	DPATH)
(include game.sh)
(use Array)
(use Motion)


(class DPath of Motion
	
	(properties
		points			NULL	;dynamic array to hold path points
		value				0		;index into path array
	)
	
	(method (init theClient thePoints &tmp i j)

		(if (not points)	;create only once
			(= points (IntArray newWith: (- argc 1) PATHEND))
		)
		
		(if argc	
			
			(= client theClient)

			(= j (- (points indexOf: PATHEND) 1))
			(for
				((= i 0))
				(<= i (- argc 3)) 
				((++ i))
				
				(points at: (++ j) [thePoints i])
				(points at: (++ j) [thePoints (++ i)])
			)
			
			
			;final odd argument, if present, is caller
			(if (<= i (- argc 2))
				(= caller [thePoints i])		
			)
		)
		
		(points at: (++ j) PATHEND)	; terminate array
		
		(self setTarget:)
		(super init:)

		;; eliminate pause at each node of path.
		(if (not argc)
			(self doit:)
		)
	)
	
	(method (dispose)
		(if points (points dispose:))
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
