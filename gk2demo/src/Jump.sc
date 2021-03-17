;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	JUMP.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated:
;;;;
;;;;	Classes which implement free motion under the influence of gravity.
;;;;
;;;;	Classes:
;;;;		Jump
;;;;		JumpTo


(script#	JUMP)
(include game.sh)
(use Main)
(use Motion)
(use System)


(define	UNDEF		20000)


(class Jump kindof Motion
	;;; Implementation of motion in a gravitational field.

	(properties
		x UNDEF			;x coord of finish
		y UNDEF			;y coord of finish
		gx 0				;gravitational acceleration in pixels/(animation cycle)**2
		gy 3				;gravitational acceleration in pixels/(animation cycle)**2
		xStep UNDEF		;horizontal step size
		yStep 0			;vertical step size
		signal 0			;save are for client's signal bits
		illegalBits 0	;save area for illegal bits of client

		;The following two properties, when true, indicate that we are to check
		;for motion completion only after the apogee of the motion.  This is
		;necessary if we are to be able to jump onto things.  Set these to
		;FALSE if you do NOT want this behavior.
		waitApogeeX TRUE
		waitApogeeY TRUE
	)

;;;	(methods
;;;		setTest			;private -- set up the jump completion test
;;;	)


	(method (init actor whom &tmp dir)
		(= client actor)
		(if (== argc 2)
			(= caller whom)
		)

		;Save the signal and illegalBits of the client, then set them to
		;values which allow the client to move anywhere.  Freeze the client's
		;priority, since the motion is to be vertical.
		(= illegalBits (client illegalBits?))
		(= signal (client signal?))
		(client illegalBits:0, setPri:)
		
		;If no xStep was specified, set a default.
		(if (== xStep UNDEF)
			(= dir (client heading?))
			(= xStep
				(cond
					((or (> dir 330) (< dir 30) (< 150 dir 210))
						0
					)
					((< dir 180)
						(client xStep?)
					)
					(else
						(- (client xStep?))
					)
				)
			)
		)
		
		(if (not (and waitApogeeX (< (* xStep gx) 0)))
			(= waitApogeeX FALSE)
		)
		(if (not (and waitApogeeY (< (* yStep gy) 0)))
			(= waitApogeeY FALSE)
		)
		(= b-moveCnt gameTime)
		(self setTest:)
	)


	(method (doit &tmp lxs lys)
	    	(if (>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
			(= b-moveCnt gameTime)
			(= xLast (client x?))
			(= yLast (client y?))

			;Move the client.
			(client
				x:(+ xLast xStep),
				y:(+ yLast yStep)
			)

			;Accelerate the motion.
			(= lxs xStep)
			(= lys yStep)
			(+= xStep gx)
			(+= yStep gy)

			;Check to see if the move is completed.
			(if
				(and
					(not waitApogeeX)
					(!= x UNDEF)
					(<= 0 (* dx (- (client x?) x)))
				)

				(client x:x)
				(self moveDone:)
				(return)
			)
			(if
				(and
					(not waitApogeeY)
					(!= y UNDEF)
					(<= 0 (* dy (- (client y?) y)))
				)

				(client y:y)
				(self moveDone:)
				(return)
 			)

			;If a velocity has changed sign, its apogee has been reached.
			(if (<= (* lxs xStep) 0)
				(= waitApogeeX FALSE)
				(self setTest:)
			)
			(if (<= (* lys yStep) 0)
				(= waitApogeeY FALSE)
				(self setTest:)
			)
		)
	)


	(method (moveDone)
		(client illegalBits:illegalBits, signal:signal)
		(if caller
;¯gtp¯			(= doMotionCue TRUE)
			(= completed TRUE)
			(self motionCue:)
		)
	)


	(method (setTest)
		;; Set the properties which determine whether to check for greater than
		;; or less than ending values.

		(= dx
			(if
				(or
					(> (client x?) x)
					(and (== (client x?) x) (> xStep 0))
				)
				-1 else 1
			)
		)
		(= dy
			(if
				(or
					(> (client y?) y)
					(and (== (client y?) y) (> yStep 0))
				)
				-1 else 1
			)
		)
	)


	(method (motionCue)
		;Detach from client.
		(client mover:0)

		(if (and completed caller)
			(if (not cuees) (= cuees (Set new:) )  ) ;now no need to doMotionCue
			(cuees add:	((Cue new:) cuee: caller, yourself:) )
;¯gtp¯			(caller cue:)
		)

		(self dispose:)
	)
)




(class JumpTo kindof Jump
	;;; This class sets up a Jump which will reach a certain x,y position.
	;;; The kernel call (SetJump) does this in order to use long integer
	;;; arithmetic.

	(method (init actor xTo yTo toCall &tmp DX DY n denom [str 50])
		(= client actor)

		;Set the endpoints of the motion
		(= x xTo)
		(= y yTo)

		;Check for jumping to actor's current position.
		(if (and (== x (actor x?)) (== y (actor y?)))
			(= illegalBits (client illegalBits?))
			(= signal (client signal?))
			(self moveDone:)
			(return)
		)

		;Compute the distances to travel.
		(= DX (- x (actor x?)))
		(= DY (- y (actor y?)))

		(SetJump self DX DY gy)

 		(if (not DX)
			(= x UNDEF)
		)
 		(if (not DY)
			(= y UNDEF)
		)

		(switch argc
			(3
				(super init: actor)
			)
			(4
				(super init: actor toCall)
			)
		)
	)


	(method (moveDone)
		(if (!= x UNDEF)
			(client x:x)
		)
		(if (!= y UNDEF)
			(client y:y)
		)
		(super moveDone:)
	)
)