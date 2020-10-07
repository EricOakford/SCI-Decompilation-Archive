;;; Sierra Script 1.0 - (do not remove this comment)
(script# JUMP)
(include game.sh)
(use Main)
(use Motion)

(define	UNDEF		20000)

(class Jump of Motion
	(properties
		x UNDEF
		y UNDEF
		gx 0
		gy 3
		xStep UNDEF
		yStep 0
		signal 0
		illegalBits 0
		waitApogeeX TRUE
		waitApogeeY TRUE
	)
	
	(method (init actor whom &tmp dir)
		(= client actor)
		(if (== argc 2) (= caller whom))
		(= illegalBits (client illegalBits?))
		(= signal (client signal?))
		(client illegalBits: 0 setPri:)
		(if (== xStep UNDEF)
			(= xStep
				(cond 
					(
						(or
							(> (= dir (client heading?)) 330)
							(< dir 30)
							(and (< 150 dir) (< dir 210))
						)
						0
					)
					((< dir 180) (client xStep?))
					(else (- (client xStep?)))
				)
			)
		)
		(if (not (if waitApogeeX (< (* xStep gx) 0)))
			(= waitApogeeX 0)
		)
		(if (not (if waitApogeeY (< (* yStep gy) 0)))
			(= waitApogeeY 0)
		)
		(self setTest:)
	)
	
	(method (doit &tmp theXStep theYStep)
		(= xLast (client x?))
		(= yLast (client y?))
		(client x: (+ xLast xStep) y: (+ yLast yStep))
		(= theXStep xStep)
		(= theYStep yStep)
		(= xStep (+ xStep gx))
		(= yStep (+ yStep gy))
		(if
			(and
				(not waitApogeeX)
				(!= x UNDEF)
				(<= 0 (* dx (- (client x?) x)))
			)
			(client x: x)
			(self moveDone:)
			(return)
		)
		(if
			(and
				(not waitApogeeY)
				(!= y UNDEF)
				(<= 0 (* dy (- (client y?) y)))
			)
			(client y: y)
			(self moveDone:)
			(return)
		)
		(if (<= (* theXStep xStep) 0)
			(= waitApogeeX FALSE)
			(self setTest:)
		)
		(if (<= (* theYStep yStep) 0)
			(= waitApogeeY FALSE)
			(self setTest:)
		)
	)
	
	(method (moveDone)
		(client illegalBits: illegalBits signal: signal)
		(if caller
			(= doMotionCue TRUE)
			(= completed TRUE)
		)
	)
	
	(method (motionCue)
		(client mover: 0)
		(if (and completed (IsObject caller))
			(caller cue:)
		)
		(self dispose:)
	)
	
	(method (setTest)
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
)

(class JumpTo of Jump
	(properties)
	
	(method (init actor xTo yTo toCall &tmp DX DY n denom [str 50])
		(= client actor)
		(= x xTo)
		(= y yTo)
		(if (and (== x (actor x?)) (== y (actor y?)))
			(= illegalBits (client illegalBits?))
			(= signal (client signal?))
			(self moveDone:)
			(return)
		)
		(= DX (- x (actor x?)))
		(= DY (- y (actor y?)))
		(SetJump self DX DY gy)
		(if (not DX) (= x UNDEF))
		(if (not DY) (= y UNDEF))
		(switch argc
			(3 (super init: actor))
			(4
				(super init: actor toCall)
			)
		)
	)
	
	(method (moveDone)
		(if (!= x UNDEF)
			(client x: x)
		)
		(if (!= y UNDEF)
			(client y: y)
		)
		(super moveDone:)
	)
)
