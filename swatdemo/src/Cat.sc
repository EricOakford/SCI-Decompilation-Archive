;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	CAT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	This class will track the mouse until the next mouseUp.  A Cat can
;;;;	be confined to a rectangular cage by setting the rectangle properties,
;;;;	and even to movement along one of its diagonals by setting the diagonal
;;;;	property to one of four non-null values.
;;;;
;;;;	Classes:
;;;;		Cat


(script# CAT)
(include game.sh) (include cat.sh)
(use Main)
(use Intrface)
(use Actor)
(use System)


(class Cat kindof Actor
	(properties
		left			-1
		top			-1			;coordinates of cage edges, -1 means no cage
		right			-1
		bottom		-1
		diagonal		NODIAG	;see enum in header file
		doCast		FALSE		;1=execute cast's doits 
									;0=only animate yourself
		outOfTouch	TRUE		;keep tracking even if cursor is out of touch
		caller		NULL
		active		FALSE		;internal properties
		dx				0
		dy				0
	)
;;;	(methods
;;;		canBeHere
;;;		findPosn
;;;		track
;;;	)
	
	(method (canBeHere)
		;;bogus, to avoid infinite recursion with findPosn --Pablo
		TRUE
	)
	
	(method (findPosn)
		;;bogus, to avoid infinite recursion with findPosn --Pablo
		TRUE
	)
	
	(method (handleEvent event)
		(cond
			((super handleEvent: event))
			((and active (== (event type?) mouseUp))	; Mouse released -- finish
				(= active FALSE)
				(event claimed: TRUE)
				(LocalToGlobal event)	;compensate for user
				(self posn: (+ (event x?) dx) (+ (event y?) dy) z)
				(GlobalToLocal event)	;Return event to user's preferred form
				(if caller
					(caller cue: self)
				)
			)
			((MousedOn self event)
				(event claimed: TRUE)
				(self  track: event)
			)
		)
	);handleEvent

	(method (track event  &tmp castOfOne)
		
		(LocalToGlobal event)	;compensate for user
		(= dx (- x (event x?)))
		(= dy (- y (event y?)))

		(if doCast
			(= active TRUE)	;doit method will do mouse tracking
			;; Note -- We leave the event in it's global form so that next
			;;		animation cycle's doit will work correctly.  This should
			;;		be o.k., as we've claimed the event.
		else
			((= castOfOne (Collection new:)) add: self)
			(while
				(and
					(!= mouseUp (event type?))
					(or outOfTouch 
						(MousedOn self (event type: mouseDown yourself:))
					)
				)
				
				(self posn: (+ (event x?) dx) (+ (event y?) dy) z)
				(FrameOut)
				
				;;prepare for next loop:
				(GetEvent allEvents event)
			)
			
			(castOfOne release:, dispose:)
			(if caller
				(caller cue: self)
			)
			(GlobalToLocal event)	;Return event to "normal"
		);if
	)

	(method (doit &tmp event)
		(cond
			((not doCast)
				(= active FALSE)
			)
			(active
				(= event (user curEvent?))
				(self posn: (+ (event x?) dx) (+ (event y?) dy) z)
			)
		)
		(super doit:)
	)
	
	(method (posn theX theY theZ &tmp s)
		(if argc						(= x theX)
			(if (>= argc 2)		(= y theY)
				(if (>= argc 3)	(= z theZ)
				)
			)
		)
		(= s (sign diagonal))
		
		(if (not (== -1 left top right bottom))	;have confining rectangle
			(switch (Abs diagonal)
				(1								;x controls y
					(= y
						(+ (if (> s 0) top else bottom)
							(/
								(* (- right x) (- bottom top) s)
								(- right left)
							)
						)
					)
				)
				(2								;y controls x
					(= x
						(+ (if (> s 0) left else right)
							(/
								(* (- bottom y) (- right left) s)
								(- bottom top)
							)
						)
					)
				)
			)
			(= x (Max left (Min right x)))
			(= y (Max top (Min bottom y)))
		)
		
		(super posn: x y z)
		
		;; code for demons to affect other objects or variables
		;; goes after instance's (super posn &rest) based on final x,y,z
	)
)
