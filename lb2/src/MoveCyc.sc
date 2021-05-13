;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	MOVECYC.SC
;;;;	(c) Sierra On-Line, Inc, 1990
;;;;
;;;;	Author: Doug Oldfield
;;;;	August 4, 1990
;;;;
;;;;	Cycles thru and repositions cels of a prop or actor
;;;;
;;;;	Our artists gave us many cast lists that had each point of an
;;;;	animation sequence mapped out exactly.  This just makes it easier
;;;;	to handle the array of points to 'move' to.
;;;;
;;;;	Usage:
;;;;		(aProp setCycle:	MoveCycle APATH optionalCaller)
;;;;
;;;;		aProp can also be an actor if you're so inclined.
;;;;		APATH is the address of a local array that hold the points.
;;;;		optionalCaller is, as usual, an optional caller.
;;;;
;;;;	Setting up points:
;;;;		(define APATH loop cel x y loop cel x y ...)
;;;;
;;;;		having loop & cel in list allows more flexibility in case cels of
;;;;		movement are in more than one loop.
;;;;
;;;;	Modified 10/20/90 so as to use clients cycleSpeed.
;;;;    Added size property to allow use of its own cycleDir.
;;;;		J.M.H.
;;;;
;;;;	Classes:
;;;;	  MoveCycle	

(script# MOVECYC)
(include game.sh)
(use Main)
(use Motion)
(use System)


(class MoveCycle kindof Cycle
	(properties
		name		"MCyc"
		value		0		; index into array
		points	0		; address of path/xy points
		cycleDir 1		; direction through the array
		size		0		; size of points array
	)
	
	(method (init who thePoints whoCares whichWay &tmp i node)
		; get our client who's gonna be moving
		(= client who)
		(= points thePoints)
		(if (>= argc 3)
			(cond
				((>= argc 4)
					(= cycleDir whichWay)
					(= caller whoCares)
				)
				((IsObject whoCares)
					(= caller whoCares)		
				)
				(else
					(= cycleDir whoCares)
				)
			)
		)
		;; size finding loop
		(for ((= size 0)) (!= (WordAt points size) PATHEND) ((++ size)))
		(if (== cycleDir 1)
			(= value 0)
		else
			(= value	(- size 4))
		)
		(super init:)
	)

	(method (doit)
		(if (>= (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
			(= cycleCnt gameTime)
			(self nextCel:)
		)
	)

	(method (nextCel)
		(client
			loop:	(WordAt points value),
			cel:	(WordAt points (+ value 1)),
			x:		(WordAt points (+ value 2)),
			y:		(WordAt points (+ value 3))
		)
		(+= value (* cycleDir 4))
		(if (or 
				(and (== cycleDir 1) 	(>= value size)) 
				(and (== cycleDir -1)	(< value 0))
			)
			(self cycleDone:)
		)
	)

	(method (cycleDone)
		(= completed TRUE)
		(= value 0)
		;If we have a caller which needs cue:ing, set the flag for a delayed cue:.
		;Otherwise, just cue: ourselves to complete the motion.
		(if caller
			(= doMotionCue TRUE)
		else
			(self motionCue:)
		)
	)

)
