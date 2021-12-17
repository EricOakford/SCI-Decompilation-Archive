;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64867)
(include sci.sh)
(use Main)
(use System)


(class MotionNoLoop of Obj
	(properties
		scratch 0
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
	)
	
	(method (init theClient theX theY theCaller &tmp temp0 clientCycler)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= x theX)
				(if (>= argc 3)
					(= y theY)
					(if (>= argc 4) (= caller theCaller))
				)
			)
		)
		(= completed 0)
		(= xLast (client x?))
		(= yLast (client y?))
		(= b-moveCnt (+ 1 (client moveSpeed?) gameTime))
		(if (= clientCycler (client cycler?))
			(clientCycler cycleCnt: b-moveCnt)
		)
		(InitBresen self)
	)
	
	(method (doit &tmp [temp0 6])
		(if
		(>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
			(= b-moveCnt gameTime)
			(DoBresen self)
		)
	)
	
	(method (moveDone)
		(= completed 1)
		(self motionCue:)
	)
	
	(method (setTarget theX theY)
		(if argc (= x theX) (= y theY))
	)
	
	(method (onTarget)
		(return (if (== (client x?) x) (== (client y?) y) else 0))
	)
	
	(method (motionCue)
		(client mover: 0)
		(if (and completed caller)
			(if (not cuees) (= cuees (Set new:)))
			(cuees add: ((Cue new:) cuee: caller yourself:))
		)
		(self dispose:)
	)
)

(class MoveToNoLoop of MotionNoLoop
	(properties
		scratch 0
		client 0
		caller 0
		x 0
		y 0
		dx 0
		dy 0
		b-moveCnt 0
		b-i1 0
		b-i2 0
		b-di 0
		b-xAxis 0
		b-incr 0
		completed 0
		xLast 0
		yLast 0
	)
	
	(method (onTarget)
		(return
			(if (<= (Abs (- (client x?) x)) (client xStep?))
				(<= (Abs (- (client y?) y)) (client yStep?))
			else
				0
			)
		)
	)
)
