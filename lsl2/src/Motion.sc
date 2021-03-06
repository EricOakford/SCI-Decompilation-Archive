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
;;;;		Wander
;;;;		Follow
;;;;		Chase
;;;;		Avoider


(script# MOTION)
(include game.sh)
(use System)

;CYCLING CLASSES ---------------------------------------------------------------


(class Cycle kindof Object
	;;; Superclass for all cycling classes.  Not used in itself.

	(properties
		client 0			;object whose cycling the class is controlling
		caller 0			;object to notify when cycling is completed
		cycleDir 1		;cycle direction (1 == forward, -1 == backward)
		cycleCnt 0		;"speed" related property
	)

;;;	(methods
;;;		nextCel			;return value of next cel in the current cycling direction
;;;		cycleDone		;method invoked when the cycling finishes
;;;	)


	(method (init theObj)
		;Make theObj our client.
		(= client theObj)

		;Reset cycle counter.
		(= cycleCnt 0)

		;Release our client's cel and make sure it's updating on the screen.
		(client setCel:-1)
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
		(if (== endCel (client cel?))
			(= toCel (+ cycleDir (client cel?)))
			(client cel:
				(cond
					((> toCel last)
						0
					)
					((< toCel 0)
						last
					)
					(else
						toCel
					)
				)
			)
		)
	)


	(method (doit &tmp newCel last)
		(= last (client lastCel:))

		;Check to see if the current loop of the animated object has fewer
		;cels than our ending cel.  If so, set the last cel of the current
		;loop as the ending cel.
		(if (> endCel last)
			(= endCel last)
		)

		(if (== endCel (client cel?))
			(self cycleDone:)
		else
			(= newCel (self nextCel:))

			;Wrap around to next cel.
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
		)
	)


	(method (cycleDone)
		;Detach from client.
		(client cycler:0)

		;Cue the caller.
		(if caller
			(caller cue:)
		)

		(self dispose:)
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
	)

;;;	(methods
;;;		moveDone			;invoked when Actor reaches its destination
;;;		triedToMove		;returns TRUE if Actor tried to move (but was perhaps blocked)
;;;	)


 	(method (init actor xTo yTo toCall &tmp DX DY)
		;Our client is the actor we are moving
		(= client actor)

		;Set our destination
		(= x xTo)
		(= y yTo)

		;toCall is the ID of the object to cue: when when done.
		(if (== argc 4)
			(= caller toCall)
		)

		(= b-moveCnt 0)

		;Set actor's heading.
		(actor heading: (GetAngle (actor x?) (actor y?) xTo yTo))

		;Pick an appropriate loop for this direction in which the Actor
		;will move.
		(if (actor looper?)
			((actor looper?) doit: actor (actor heading?))
		else
			(DirLoop actor (actor heading?))
		)

		;Set up for the Bresenham algorithm.
		(InitBresen self)
	)


	(method (doit &tmp aState xd yd si1 si2 sdi)
		;; Move the actor one step.

		;If our client is where he should be we terminate.
		(if (and (== x (client x?)) (== y (client y?)))
			(self moveDone:)
			(return)
		)

		;If time to move, take a step.
		(DoBresen self)
	)		
	
	
	(method (triedToMove)
		;; Return TRUE if the Actor tried to move in this animation cycle.

		(return (== b-moveCnt 0))
	)


	(method (moveDone)
		;Detach ourselves from client.
		(client mover:0)

		;Cue our caller (if we have one).
		(if caller
			(caller cue:)
		)

		(self dispose:)
	)
)



(class MoveTo kindof Motion
	;;; Move client to a particular point and signal completion to caller.

	(method (init)
		(super init: &rest)		;set up to move
		(super doit:)				;take the first step
	)
)



(class Wander kindof Motion
	;;; Wander about the screen.  This motion never terminates.

	(properties
		distance 0		;the maximum distance to move on any one leg of the wander
	)

	(method (init theObj dist &tmp nx ny diam)
		;The maximum distance to move on any one leg of the wander is 'dist'
		;if present, 30 pixels otherwise.
		(= distance (if (== argc 2) dist else 30))
		(= diam (* distance 2))

		;Pick a random position to move to, constrained by 'distance'.
		(= nx (+ (theObj x?) (- distance (Random 1 diam))))
		(= ny (+ (theObj y?) (- distance (Random 1 diam))))

		;Initialize the motion and take the first step.
		(super init: theObj nx ny)
		(super doit:)
	)


	(method (doit)
		;Take the next step.
		(super doit:)

		;If the motion is complete or the client is blocked, we're finished.
		(if (client isStopped:)
			(self moveDone:)
		)
	)


	(method (moveDone)
		;;; When done with the current leg of wandering, re-init: the motion
		;;; to continue wandering.
		(self init: client distance)
	)
)



(class Follow kindof Motion
	;;; This class moves its client in such a way as to try and stay within
	;;; a certain distance to another object.

	(properties
		who 0					;who to follow
		distance 0			;try to stay at least this close to 'who'
	)


	(method (init theObj whom dist)
		(= who whom)
		(= client theObj)

		;If distance is not specified, default to 20 pixels.
		(= distance
			(if (< argc 3)
				20
			else 
				dist
			)
		)

		;If the client is too far from the object being followed, start
		;moving toward it.
		(if (> (client distanceTo: who) distance)
			(super init: theObj (who x?) (who y?))
			(super doit:)
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
)



(class Chase kindof Motion
	;;; Try to catch a particular Actor.

	(properties
		who 0					;who to chase
		distance 0			;how close to 'who' is considered a 'catch'
	)


	(method (init actor whom howClose whoCares)
		(= client actor)
		(= who whom)
		(= distance howClose)
		(if (== argc 4)
			(= caller whoCares)
		)
		(super init: client (who x?) (who y?) caller)
		(super doit:)
 	)


	(method (doit)
		(if (<= (client distanceTo: who) distance)
			(self moveDone:)
		else
			(super doit:)
			(if (== b-moveCnt 0)
				(super init: client (who x?) (who y?) caller)
			)
		)
	)
)




(class Avoider kindof Object
	;;; Normally, when an Actor encounters a control line or other object
	;;; which blocks its normal motion, it simply stops right there.  The
	;;; If, however, an Avoider is attached to such an Actor, it will take
	;;; over and attempt to direct the Actor around whatever obstruction
	;;; it has encountered.  As with class Motion, much of the behavior of
	;;; Avoider has been downcoded to the kernel for space efficiency.
	;;; (See motion.c).

	(properties
		name "Avoid"
		client 0
		heading -1
		oldDir 0			;previous direction
		olderDir 0		;before previous direction
		impulse 0		;AKA momentum
	)


	(method (init)
		(= heading -1)
		(= oldDir -2)		;bogus and unique value
		(= olderDir -3)	;bogus and unique value
		(= impulse 0)
	)


	(method (doit &tmp [str 40] motion curDir tries)

		(= curDir (DoAvoider self))			;sets heading
	
		(if (and
				(!= curDir -1)
				(client mover?)						;if we are still around, go to work
			)
			;Anti flip-flop code - Pablo Ghenis
			(= tries 0)
			(while
				(and
					(== curDir olderDir)			;no flip-flopping allowed
					(<= (++ tries) 1)				;dont hang: times I'm willing to retry 
				)
				(= heading -1)						;go random?
				(= curDir (DoAvoider self))	;try again, sets heading
			)
			
			;Impulse-related code - Pablo Ghenis
			(cond
				((or
					(== curDir olderDir)
					(== curDir oldDir)			;if same direction
				 )
					(+= impulse 1)					;gain some impulse (adjustable)
				)
				((and 
					(> impulse 0)					;if still have impulse
					(not (client isBlocked:))	;and not blocked
				 )
					(= curDir oldDir)				;prevent change
					(-- impulse)					;lose some impulse
				)
				(else
					(= olderDir oldDir)			;remember where you were headed
					(= oldDir curDir)
					(= impulse 1)					;initial impulse (adjustable)
				)
			)

		);end if (client mover?)

	
		(if (!= curDir -1)
			
			(if (= motion (client mover?))
				(if (motion isMemberOf: MoveTo)
					(client setMotion:MoveTo 
									(motion x?) (motion y?) (motion caller?))
				)
			)
		
			(if (client looper?)
				((client looper?) doit: client curDir)
			else
				(DirLoop client curDir)
			)
			(if (not (client canBeHere:))
				(client findPosn:)
			)
		)
	)
)