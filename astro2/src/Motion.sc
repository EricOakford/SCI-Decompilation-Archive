;;; Sierra Script 1.0 - (do not remove this comment)
(script# MOTION)
(include game.sh)
(use Main)
(use System)


(class Cycle of Object
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
	)
	
	(method (init theObj)
		(if argc (= client theObj))
		(= cycleCnt 0)
		(= completed FALSE)
	)
	
	(method (nextCel)
		(++ cycleCnt)
		(return
			(if (<= cycleCnt (client cycleSpeed?))
				(client cel?)
			else
				(= cycleCnt 0)
				(if (& (client signal?) fixedCel)
					(client cel?)
				else
					(+ (client cel?) cycleDir)
				)
			)
		)
	)
	
	(method (cycleDone)
	)
	
	(method (motionCue)
		(client cycler: 0)
		(if (and completed (IsObject caller)) (caller cue:))
		(self dispose:)
	)
)

(class Forward of Cycle
	(properties
		name "Fwd"
	)
	
	(method (doit &tmp newCel)
		(if
		(> (= newCel (self nextCel:)) (client lastCel:))
			(self cycleDone:)
		else
			(client cel: newCel)
		)
	)
	
	(method (cycleDone)
		(client cel: 0)
	)
)

(class Walk of Forward
	(properties)
	
	(method (doit &tmp newCel)
		(if (not (client isStopped:)) (super doit:))
	)
)

(class CycleTo of Cycle
	(properties
		name "CT"
		endCel 0
	)
	
	(method (init actor toCel dir whoCares &tmp last)
		(super init: actor)
		(= cycleDir dir)
		(if (== argc 4) (= caller whoCares))
		(= endCel
			(if (> toCel (= last (client lastCel:)))
				last
			else
				toCel
			)
		)
	)
	
	(method (doit &tmp newCel last)
		(if (> endCel (= last (client lastCel:)))
			(= endCel last)
		)
		(= newCel (self nextCel:))
		(client
			cel:
				(cond 
					((> newCel last) 0)
					((< newCel 0) last)
					(else newCel)
				)
		)
		(if (and (== cycleCnt 0) (== endCel (client cel?)))
			(self cycleDone:)
		)
	)
	
	(method (cycleDone)
		(= completed TRUE)
		(if caller
			(= doMotionCue TRUE)
		else
			(self motionCue:)
		)
	)
)

(class EndLoop of CycleTo
	(properties
		name "End"
	)
	
	(method (init actor whoCares)
		(super
			init: actor (actor lastCel:) 1 (if (== argc 2) whoCares else 0)
		)
	)
)

(class BegLoop of CycleTo
	(properties
		name "Beg"
	)
	
	(method (init actor whoCares)
		(super init: actor 0 -1 (if (== argc 2) whoCares else 0))
	)
)

(class SyncWalk of Forward
	(properties
		xLast 0
		yLast 0
	)
	
	(method (doit &tmp theMover)
		(if
			(and
				(IsObject (= theMover (client mover?)))
				(or (!= (client x?) xLast) (!= (client y?) yLast))
			)
			(= xLast (client x?))
			(= yLast (client y?))
			(super doit:)
		)
	)
	
	(method (nextCel)
		(= cycleCnt (client cycleSpeed?))
		(super nextCel:)
	)
)

(class Motion of Object
	(properties
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
	
	(method (init actor xTo yTo toCall &tmp DX DY cx cy)
		(if (>= argc 1)
			(= client actor)
			(if (>= argc 2)
				(= x xTo)
				(if (>= argc 3)
					(= y yTo)
					(if (>= argc 4) (= caller toCall))
				)
			)
		)
		(= yLast (= xLast (= b-moveCnt (= completed 0))))
		(if (= cy (client cycler?))
			(cy cycleCnt: 0)
		)
		(if
			(GetDistance
				(= cx (client x?))
				(= cy (client y?))
				x
				y
			)
			(client setHeading: (GetAngle cx cy x y))
		)
		(InitBresen self)
	)
	
	(method (doit &tmp aState xd yd si1 si2 sdi)
		(DoBresen self)
	)
	
	(method (moveDone)
		(= completed TRUE)
		(if caller
			(= doMotionCue TRUE)
		else
			(self motionCue:)
		)
	)
	
	(method (setTarget xTo yTo)
		(if argc (= x xTo) (= y yTo))
	)
	
	(method (onTarget)
		(return (if (== (client x?) x) (== (client y?) y) else 0))
	)
	
	(method (motionCue)
		(client mover: 0)
		(if (and completed (IsObject caller))
			(caller cue:)
		)
		(self dispose:)
	)
)

(class MoveTo of Motion
	(properties)
	
	(method (init)
		(super init: &rest)
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
