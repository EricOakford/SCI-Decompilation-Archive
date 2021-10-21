;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	MOTION.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
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
;;;;		Reverse
;;;;		CycleTo
;;;;		EndLoop
;;;;		BegLoop
;;;;		Motion
;;;;		MoveTo


(script# MOTION)
(include game.sh)
(use Main)
(use System)


;CYCLING CLASSES ---------------------------------------------------------------


(class Cycle kindof Object
	;;; Superclass for all cycling classes.  Not used in itself.
	
	(properties
		client 0			;object whose cycling the class is controlling
		caller 0			;object to notify when cycling is completed
		cycleDir 1		;cycle direction (1 == forward, -1 == backward)
		cycleCnt 0		;"speed" related property
		completed 0
	)
	
;;;	(methods
;;;		nextCel			;return value of next cel in the current cycling direction
;;;		cycleDone		;method invoked when the cycling finishes
;;;		motionCue
;;;	)
	
	
	(method (init theObj)
		;Make theObj our client.
		(if argc
			(= client theObj)
		)
		
		;Reset cycle counter.
		(= cycleCnt 0)
		(client setCel: -1)
		(client startUpd:)

	)
	
	
	(method (nextCel)
		;; Return client's next logical cel.
		
		;Increment the number of animation cycles since the client last cycled.
		(++ cycleCnt)
		
		(return
			(if (<= cycleCnt (client cycleSpeed?))
				;Not yet time to change the client's cel.
				(client cel?)
			else
				;Reset counter.
				(= cycleCnt 0)
				
				(if (& (client signal?) fixedCel)
					;Client's cel is fixed -- just return current cel.
					(client cel?)
				else
					;Change the cel number in the appropriate fashion.
					(+ (client cel?) cycleDir)
				)
			)
		)
	)
	
	
	(method (cycleDone)
	)
	
	
	(method (motionCue)
		;Detach from client.
		(client cycler:0)
		
		(if (and completed (IsObject caller))
			(caller cue:)
		)
		
		(self dispose:)
	)
)




(class Forward kindof Cycle
	;;; Cycles client's cel constantly forward, wrapping to 0 when it exceeds
	;;; the number of cels in the client's loop.
	
	(properties
		name "Fwd"
		cycleDir 1
	)
	
	
	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(if (> newCel (client lastCel:))
			(self cycleDone:)
		else
			(client cel:newCel)
		)
	)
	
	
	(method (cycleDone)
		;; When 'done', reset to first cel and keep going.
		(client cel:0)
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

(class Reverse kindof Cycle
	;;; Cycles client's cel constantly in reverse, wrapping to the last cel
	;;; in the client's loop when the cel goes below 0.

	(properties
		name "Rev"
		cycleDir -1
	)

	(method (doit &tmp newCel)
		(= newCel (self nextCel:))
		(if (< newCel 0)
			(self cycleDone:)
		else
			(client cel:newCel)
		)
	)


	(method (cycleDone)
		;; When 'done', reset to last cel and keep going.
		(client cel: (client lastCel:))
	)
)


(class CycleTo kindof Cycle
	;;; Cycle from the current cel of the client to a specified cel,
	;;; cue:ing the caller when the specified cel is reached.
	
	(properties
		name "CT"
		endCel 0			;cel to cycle to
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
		(= last (client lastCel:))
		(= endCel (if (> toCel last) last else toCel))
		
		;If the current cel is the one which we're supposed to
		;quit at, cycle once to get off it.
;		(if (== endCel (client cel?))
;			(= toCel (+ cycleDir (client cel?)))
;			(client cel:
;				(cond
;					((> toCel last)
;						0
;					)
;					((< toCel 0)
;						last
;					)
;					(else
;						toCel
;					)
;				)
;			)
;		)
	)
	
	
	(method (doit &tmp newCel last)
		(= last (client lastCel:))
		
		;Check to see if the current loop of the animated object has fewer
		;cels than our ending cel.  If so, set the last cel of the current
		;loop as the ending cel.
		(if (> endCel last)
			(= endCel last)
		)
		
		;Move to next cel.
		(= newCel (self nextCel:))
		(client cel:
			(cond
				((> newCel last)
					0
				)
				((< newCel 0)
					last
				)
				(else
					newCel
				)
			)
		)
		
		;If at final cel, signal that we're done.
		(if
			(and
				(== cycleCnt 0)
				(== endCel (client cel?))
			)
			(self cycleDone:)
		)
	)
	
	
	(method (cycleDone)
		(= completed TRUE)
		
		;If we have a caller which needs cue:ing, set the flag for a delayed cue:.
		;Otherwise, just cue: ourselves to complete the motion.
		(if caller
			(= doMotionCue TRUE)
		else
			(self motionCue:)
		)
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
		client 0			;actor we are controlling
		caller 0			;cue this object when complete (or blocked)
		x 0				;destination of the move
		y 0
		dx 0				;basic step size
		dy 0
		b-moveCnt 0		;iterations of doit to skip
		b-i1 0			;increment values
		b-i2 0
		b-di 0			;decision variable
		b-xAxis 0		;is motion along x-axis or y-axis?
		b-incr 0			;the Bresenham adjustment to an integral slope line
		completed 0
	)
	
;;;	(methods
;;;		moveDone			;invoked when Actor reaches its destination
;;;		setTarget		;resets x and y
;;;		onTarget			;TRUE/FALSE
;;;		motionCue
;;;	)
	
	
	(method (init actor xTo yTo toCall &tmp DX DY cy)
		
		(if (>= argc 1)				(= client actor)
			(if (>= argc 2)			(= x xTo)
				(if (>= argc 3)		(= y yTo)
					(if (>= argc 4)	(= caller toCall)
					)
				)
			)
		)
		
		(= b-moveCnt 0)
		
		;Set actor's heading.
		(client heading: (GetAngle (client x?) (client y?) xTo yTo))
		
		;Pick an appropriate loop for this direction in which the Actor
		;will move.
		(if (client looper?)
			((client looper?) doit: client (client heading?))
		else
			(DirLoop client (client heading?))
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

		;If time to move, take a step.
		(DoBresen self)
		
		;If our client is where he should be we terminate.
		(if (and (== x (client x?)) (== y (client y?)))
			(self moveDone:)
			(return)
		)
	)		
	
	
	(method (moveDone)
		(= completed TRUE)
		
		;If we have a caller which needs cue:ing, set the flag for a delayed cue:.
		;Otherwise, just cue: ourselves to complete the motion.
		(if caller
			(= doMotionCue TRUE)
		else
			(self motionCue:)
		)
	)
	
	(method (triedToMove)
		(return (== b-moveCnt 0))
	)
	
	(method (motionCue)
		;Detach from client.
		(client mover:0)
		
		(if (and completed (IsObject caller))
			(caller cue:)
		)
		
		(self dispose:)
	)
)



(class MoveTo kindof Motion
	;;; Move client to a particular point and signal completion to caller.
	
	(method (init)
		(super init: &rest)		;set up to move
;		(super doit:)				;take the first step
	)
	
	(method (onTarget)
		(return
			(and
				(<= (Abs (- (client x?) x)) (client xStep?))
				(<= (Abs (- (client y?) y)) (client yStep?))
			)
		)
	)
);MoveTo

(class Follow kindof Motion
	;;; This class moves its client in such a way as to try and stay within
	;;; a certain distance to another object.
	
	(properties
		who 0					;who to follow
		distance 20			;try to stay at least this close to 'who'
	)
	
	
	(method (init theObj whom dist)
		
		(if (>= argc 1)			(= client theObj)
			(if (>= argc 2)		(= who whom)
				(if (>= argc 3)	(= distance dist)
				)
			)
		)
		
		
		;If the client is too far from the object being followed, start
		;moving toward it.
		(if (> (client distanceTo: who) distance)
			(super init: client (who x?) (who y?))
;			(super doit:)
		)
	)
	
	(method (onTarget)
		(<= (client distanceTo: who) distance)
	)
	
	(method (setTarget)
		(cond
			(argc	
				(super setTarget: &rest)
			)
			((not (self onTarget:))
				(super setTarget: (who x?) (who y?))
			)
		)
	)

	
	(method (doit &tmp angle)
		(if (> (client distanceTo: who) distance)
			;If too far from the object being followed, move toward it.
			(super doit:)
			(if (== b-moveCnt 0)
				(super init: client (who x?) (who y?))
			)
		else
			;The client is just standing around near its destination.  Pick
			;the loop which faces in the destination's direction.
			(= angle (GetAngle (client x?) (client y?) (who x?) (who y?)))
			(if (client looper?)
				((client looper?) doit: client angle)
			else
				(DirLoop client angle)
			)
		)
	)
	(method (moveDone)
		;;; When done with the current leg of wandering, re-init: the motion
		;;; to continue wandering.
	;		(self init: client distance)
	)	
)
