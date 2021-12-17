;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	MOTION.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated:	Martin Peters, Brian K. Hughes, Greg Tomko-Pavia
;;;;
;;;;	Classes for moving and cycling animated objects.
;;;;	Cycling classes change the cel number of their client in a consistent
;;;; 	manner in response to the doit: message.  This makes the object seem
;;;;	to animate.
;;;;	Motion classes change the x,y position of their client in response
;;;;	to the doit: message.  Each class implements a different way of
;;;;	moving an object.
;;;;
;;;;	Classes:
;;;;		Cycle
;;;;		Forward
;;;;		Walk
;;;;		CycleTo
;;;;		EndLoop
;;;;		BegLoop
;;;;		SyncWalk
;;;;		Motion
;;;;		MoveTo


(script# MOTION)
(include game.sh)
(use Main)
(use System)


;CYCLING CLASSES ---------------------------------------------------------------


(class Cycle kindof Object
	(properties
		client 		0		;object whose cycling the class is controlling
		caller 		0		;object to notify when cycling is completed
		cycleDir 	1		;cycle direction (1 == forward, -1 == backward)
		cycleCnt 	0		;"speed" related property
		completed 	0
		clientLastCel 0
	)
;;;	(methods
;;;		nextCel			;return value of next cel in the current cycling direction
;;;		cycleDone		;method invoked when the cycling finishes
;;;		motionCue
;;;	)
	
	(method (init theObj)
		;Make theObj our client.
		(if argc (= client theObj))
		
		;Reset cycle counter.
		(= cycleCnt	gameTime)
		(= completed FALSE)
		(= clientLastCel (client lastCel:))
	)

	(method (nextCel)
		;; Return client's next logical cel.
		
		(return
			(if (< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
				;Not yet time to change the client's cel.
				(client cel?)
			else
				;Reset counter.
				(= cycleCnt gameTime)
				
				;Change the cel number in the appropriate fashion.
				(+ (client cel?) cycleDir)
			)
		)
	)
	
	(method (cycleDone)
	)
	
	(method (motionCue)
		;Detach from client.
		(client cycler: 0)
		
		(if (and completed caller)
			(if (not cuees) (= cuees (Set new:) )  )
			(cuees add:	((Cue new:) cuee: caller, yourself:) )
		)
		
		(self dispose:)
	)
)



(class Forward kindof Cycle
	;;; Cycles client's cel constantly forward, wrapping to 0 when it exceeds
	;;; the number of cels in the client's loop.
	
	(properties
		name 			"Fwd"
		cycleDir 	1
	)
	
	(method (doit &tmp newCel)
		(if (!= (client cel?) (= newCel (self nextCel:)))
			(if (> newCel clientLastCel)
				(self cycleDone:)
			else
				(client cel: newCel)
			)
		)
	)
	
	(method (cycleDone)
		;; When 'done', reset to first cel and keep going.
		(client cel: 0)
	)
)



(class Walk kindof Forward
	;;; Do a forward cycle of an object only if it has moved.  Otherwise,
	;;; remain motionless.

	(method (doit &tmp newCel)
		;; Goes to next cel if client has moved.
		
		(if (not (client isStopped:))
			(super doit:)
		)
	)
)



(class CycleTo kindof Cycle
	;;; Cycle from the current cel of the client to a specified cel,
	;;; cue:ing the caller when the specified cel is reached.
	
	(properties
		name 		"CT"
		endCel 	0			;cel to cycle to
	)
	
	(method (init actor toCel dir whoCares &tmp last)
		;; Set up endCel and caller.
		
		(super init: actor)
		
		(= cycleDir dir)
		(if (== argc 4)
			(= caller whoCares)
		)
		
		;Set endCel to the value passed, or to the last cel of the
		;current loop if the specified cel is too big.
		(= endCel (if (> toCel clientLastCel) clientLastCel else toCel))
	)
	
	(method (doit &tmp newCel currentCel)

		(if	;Do nothing if cel hasn't changed
				(!=
					(= currentCel (client cel?) )
					(= newCel (self nextCel:) )
				)
			(if (== currentCel endCel)
				(self cycleDone:) ; we have waited cycleSpeed before cueing
			else
				(client cel: newCel) ;Sorry, but the cases in the cond below
				;should never happen. If they do, they're bugs and should
				;be caught. No need to waste time with two checks each update.
			)
		)

	)

	(method (cycleDone)
		(= completed TRUE)
		(self motionCue:) ;doMotionCue route is inefficient
	)
)



(class EndLoop kindof CycleTo
	;;; Cycles forward until the last cel of the loop is reached.
	;;; Leaves cel at last cel in loop.
	
	(properties
		name "End"
	)
	
	(method (init actor whoCares)
		(super
			init:
			actor
			(actor lastCel:)
			1
			(if (== argc 2) whoCares else 0)
		)
	)
)



(class BegLoop kindof CycleTo
	;;; Cycles backward until it reaches cel 0.
	;;; Leaves cel at 0.
	
	(properties
		name "Beg"
	)
	
	(method (init actor whoCares)
		(super
			init:
			actor 
			0
			-1
			(if (== argc 2) whoCares else 0)
		)
	)
)



(class SyncWalk kindof Forward
	(properties
		xLast		0
		yLast		0
	)

	(method (doit &tmp theMover)
		(if (or 
				(!= (client x?) xLast)
				(!= (client y?) yLast)
			)
			(= xLast (client x?))
			(= yLast (client y?))
			(super doit:)
		)
	)

	(method (nextCel)
		(= cycleCnt (+ gameTime (client cycleSpeed?)))
		(super nextCel:)
	)
)



;;;; MOTION CLASSES ------------------------------------------------------------

;;;; All Actor motion attempts to get from point A to point B in a straight
;;;; line.  This is done using a modified Bresenham algorithm which deals
;;;; with non-unit steps.  See "bresen.doc" for a derivation of the algorithm.
;;;; The bulk of Motion code (particularly the Bresenham algorithm) has been
;;;; moved into the kernel for both speed and heap space savings.
;;;; (See motion.c).



(class Motion kindof Object
	;;; Move an Actor from its current position to a destination position.
	
	(properties
		client 		0		;actor we are controlling
		caller 		0		;cue this object when complete (or blocked)
		x 				0		;destination of the move
		y 				0
		dx 			0		;basic step size
		dy 			0
		b-moveCnt 	0		;iterations of doit to skip
		b-i1 			0		;increment values
		b-i2 			0
		b-di 			0		;decision variable
		b-xAxis 		0		;is motion along x-axis or y-axis?
		b-incr 		0		;the Bresenham adjustment to an integral slope line
		completed 	0
		xLast 		0
		yLast 		0
	)
;;;	(methods
;;;		moveDone			;invoked when Actor reaches its destination
;;;		setTarget		;resets x and y
;;;		onTarget			;TRUE/FALSE
;;;		motionCue
;;;	)

	(method (init actor xTo yTo toCall &tmp cx cy)
		(if (>= argc 1)				(= client actor)
			(if (>= argc 2)			(= x xTo)
				(if (>= argc 3)		(= y yTo)
					(if (>= argc 4)	(= caller toCall)
					)
				)
			)
		)
		
		(= completed FALSE)
		(= xLast (client x?))
		(= yLast (client y?))
		;; get it to move for sure first ani-cycle
		(= b-moveCnt (+ 1 (client moveSpeed?) gameTime))
		(if (= cy (client cycler?))		; Keep cycler in synch with mover
			(cy cycleCnt: b-moveCnt)
		)
		
		;Set actor's heading.
		(if (GetDistance (= cx (client x?)) (= cy (client y?)) x y)
;¯gtp¯	Correct for pixel aspect ratio:
			(client setHeading: (GetAngle (* cx 4) (* cy 5) (* x 4) (* y 5)))
		)
		;Set up for the Bresenham algorithm.
		(InitBresen self)
	)
	
	(method (onTarget)
		(return (and (== (client x?) x) (== (client y?) y)))
	)
	
	(method (setTarget xTo yTo)
		(if argc (= x xTo) (= y yTo))
	)
	
	(method (doit &tmp aState xd yd si1 si2 sdi)
		;; Move the actor one step.
		(return
			(if (>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
				(= b-moveCnt gameTime)
				(DoBresen self)
			)
		)
	)		
	
	(method (moveDone)
		(= completed TRUE)
		(self motionCue:) ;the doMotionCue stuff is too inefficient
	)
	
	(method (motionCue)
		;Detach from client.
		(client mover: 0)
		
		(if (and completed caller)
			(if (not cuees) (= cuees (Set new:) )  ) ;now no need to doMotionCue
			(cuees add:	((Cue new:) cuee: caller, yourself:) )
		)
		(self dispose:)
	)
)



(class MoveTo kindof Motion
	;;; Move client to a particular point and signal completion to caller.
	
	(method (onTarget)
		(return
			(and
				(<= (Abs (- (client x?) x)) (client xStep?))
				(<= (Abs (- (client y?) y)) (client yStep?))
			)
		)
	)
);MoveTo