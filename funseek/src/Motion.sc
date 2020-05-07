;;; Sierra Script 1.0 - (do not remove this comment)
(script# 992)
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
	
	(method (init theClient)
		(= client theClient)
		(= cycleCnt 0)
	)
	
	(method (nextCel)
		(++ cycleCnt)
		(return
			(if (<= cycleCnt (client cycleSpeed?))
				(client cel?)
			else
				(= cycleCnt 0)
				(if (& (client signal?) $1000)
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
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
	)
	
	(method (doit &tmp fwdNextCel)
		(if
		(> (= fwdNextCel (self nextCel:)) (client lastCel:))
			(self cycleDone:)
		else
			(client cel: fwdNextCel)
		)
	)
	
	(method (cycleDone)
		(client cel: 0)
	)
)

(class Walk of Forward
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
	)
	
	(method (doit &tmp temp0)
		(if (not (client isStopped:)) (super doit:))
	)
)

(class CycleTo of Cycle
	(properties
		name "CT"
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		endCel 0
	)
	
	(method (init param1 param2 theCycleDir theCaller &tmp clientLastCel)
		(super init: param1)
		(= cycleDir theCycleDir)
		(if (== argc 4) (= caller theCaller))
		(= endCel
			(if (> param2 (= clientLastCel (client lastCel:)))
				clientLastCel
			else
				param2
			)
		)
	)
	
	(method (doit &tmp cTNextCel clientLastCel)
		(if (> endCel (= clientLastCel (client lastCel:)))
			(= endCel clientLastCel)
		)
		(= cTNextCel (self nextCel:))
		(client
			cel:
				(cond 
					((> cTNextCel clientLastCel) 0)
					((< cTNextCel 0) clientLastCel)
					(else cTNextCel)
				)
		)
		(if
		(and (== cycleCnt 0) (== endCel (client cel?)))
			(self cycleDone:)
		)
	)
	
	(method (cycleDone)
		(= completed 1)
		(if caller (= doMotionCue 1) else (self motionCue:))
	)
)

(class EndLoop of CycleTo
	(properties
		name "End"
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		endCel 0
	)
	
	(method (init param1 param2)
		(super
			init: param1 (param1 lastCel:) 1 (if (== argc 2) param2 else 0)
		)
	)
)

(class BegLoop of CycleTo
	(properties
		name "Beg"
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		endCel 0
	)
	
	(method (init param1 param2)
		(super init: param1 0 -1 (if (== argc 2) param2 else 0))
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
	)
	
	(method (init theClient theX theY theCaller &tmp [temp0 2])
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
		(= b-moveCnt 0)
		(client
			heading: (GetAngle (client x?) (client y?) x y)
		)
		(if (client looper?)
			((client looper?) doit: client (client heading?))
		else
			(DirLoop client (client heading?))
		)
		(InitBresen self)
	)
	
	(method (doit &tmp [temp0 6])
		(DoBresen self)
		(if (and (== x (client x?)) (== y (client y?)))
			(self moveDone:)
			(return)
		)
	)
	
	(method (moveDone)
		(= completed 1)
		(if caller (= doMotionCue 1) else (self motionCue:))
	)
	
	(method (triedToMove)
		(return (== b-moveCnt 0))
	)
	
	(method (setTarget theX theY)
		(if argc (= x theX) (= y theY))
	)
	
	(method (onTarget)
		(return (if (== (client x?) x) (== (client y?) y) else 0))
	)
	
	(method (motionCue)
		(client mover: 0)
		(if (and completed (IsObject caller)) (caller cue:))
		(self dispose:)
	)
)

(class MoveTo of Motion
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
	)
	
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
