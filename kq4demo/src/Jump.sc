;;; Sierra Script 1.0 - (do not remove this comment)
(script# 991)
(include game.sh)
(use Main)
(use Motion)


(class Jump of BegLoop
	(properties
		client 0
		caller 0
		x 20000
		y 20000
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		nextCel 0
		findPosn 0
		gx 3
		xStep 20000
		yStep 0
		signal $0000
		illegalBits $0000
		gy 1
		waitApogeeX 1
	)
	
	(method (init theClient theCaller &tmp clientHeading)
		(= client theClient)
		(if (== argc 2) (= caller theCaller))
		(= illegalBits (client illegalBits?))
		(= signal (client signal?))
		(client illegalBits: 0 setPri:)
		(if (== xStep 20000)
			(= xStep
				(cond 
					(
						(or
							(> (= clientHeading (client heading?)) 330)
							(< clientHeading 30)
							(and (< 150 clientHeading) (< clientHeading 210))
						)
						0
					)
					((< clientHeading 180) (client xStep?))
					(else (- (client xStep?)))
				)
			)
		)
		(if (not (if gy (< (* xStep findPosn) 0))) (= gy 0))
		(if (not (if waitApogeeX (< (* yStep gx) 0)))
			(= waitApogeeX 0)
		)
		(self waitApogeeY:)
	)
	
	(method (doit &tmp theXStep theYStep)
		(client
			x: (+ (client x?) xStep)
			y: (+ (client y?) yStep)
		)
		(= theXStep xStep)
		(= theYStep yStep)
		(= xStep (+ xStep findPosn))
		(= yStep (+ yStep gx))
		(if
			(and
				(not gy)
				(!= x 20000)
				(<= 0 (* dx (- (client x?) x)))
			)
			(client x: x)
			(self endCel:)
			(return)
		)
		(if
			(and
				(not waitApogeeX)
				(!= y 20000)
				(<= 0 (* dy (- (client y?) y)))
			)
			(client y: y)
			(self endCel:)
			(return)
		)
		(if (<= (* theXStep xStep) 0)
			(= gy 0)
			(self waitApogeeY:)
		)
		(if (<= (* theYStep yStep) 0)
			(= waitApogeeX 0)
			(self waitApogeeY:)
		)
	)
	
	(method (endCel)
		(client illegalBits: illegalBits signal: signal)
		(if caller (= doMotionCue 1) (= nextCel 1))
	)
	
	(method (moveDone)
		(return 1)
	)
	
	(method (setCel)
		(client mover: 0)
		(if (and nextCel (IsObject caller)) (caller cue:))
		(self dispose:)
	)
	
	(method (waitApogeeY)
		(= dx
			(if
				(or
					(> (client x?) x)
					(and (== (client x?) x) (> xStep 0))
				)
				-1
			else
				1
			)
		)
		(= dy
			(if
				(or
					(> (client y?) y)
					(and (== (client y?) y) (> yStep 0))
				)
				-1
			else
				1
			)
		)
	)
)

(class JumpTo of Jump
	(properties
		client 0
		caller 0
		x 20000
		y 20000
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		nextCel 0
		findPosn 0
		gx 3
		xStep 20000
		yStep 0
		signal $0000
		illegalBits $0000
		gy 1
		waitApogeeX 1
	)
	
	(method (init theClient theX theY param4 &tmp temp0 temp1 [temp2 52])
		(= client theClient)
		(= x theX)
		(= y theY)
		(if
		(and (== x (theClient x?)) (== y (theClient y?)))
			(= illegalBits (client illegalBits?))
			(= signal (client signal?))
			(self endCel:)
			(return)
		)
		(= temp0 (- x (theClient x?)))
		(= temp1 (- y (theClient y?)))
		(SetJump self temp0 temp1 gx)
		(if (not temp0) (= x 20000))
		(if (not temp1) (= y 20000))
		(switch argc
			(3 (super init: theClient))
			(4
				(super init: theClient param4)
			)
		)
	)
	
	(method (endCel)
		(if (!= x 20000) (client x: x))
		(if (!= y 20000) (client y: y))
		(super endCel:)
	)
)
