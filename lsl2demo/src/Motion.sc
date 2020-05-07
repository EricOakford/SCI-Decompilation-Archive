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
		(if argc (= client theClient))
		(= cycleCnt 0)
		(client setCel: -1)
		(client startUpd:)
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

(class Reverse of Cycle
	(properties
		name "Rev"
		client 0
		caller 0
		cycleDir -1
		cycleCnt 0
		completed 0
	)
	
	(method (doit &tmp revNextCel)
		(if (< (= revNextCel (self nextCel:)) 0)
			(self cycleDone:)
		else
			(client cel: revNextCel)
		)
	)
	
	(method (cycleDone)
		(client cel: (client lastCel:))
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
			heading: (GetAngle (client x?) (client y?) theX theY)
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

(class Wander of Motion
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
		distance 30
	)
	
	(method (init theClient theDistance)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2) (= distance theDistance))
		)
		(self setTarget:)
		(super init: client)
	)
	
	(method (doit)
		(super doit:)
		(if (client isStopped:) (self moveDone:))
	)
	
	(method (moveDone)
		(self init:)
	)
	
	(method (setTarget &tmp temp0)
		(= x
			(+
				(client x?)
				(- distance (Random 0 (= temp0 (* distance 2))))
			)
		)
		(= y (+ (client y?) (- distance (Random 0 temp0))))
	)
	
	(method (onTarget)
		(return 0)
	)
)

(class Follow of Motion
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
		who 0
		distance 20
	)
	
	(method (init theClient theWho theDistance)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= who theWho)
				(if (>= argc 3) (= distance theDistance))
			)
		)
		(if (> (client distanceTo: who) distance)
			(super init: client (who x?) (who y?))
		)
	)
	
	(method (doit &tmp temp0)
		(if (> (client distanceTo: who) distance)
			(super doit:)
			(if (== b-moveCnt 0)
				(super init: client (who x?) (who y?))
			)
		else
			(= temp0
				(GetAngle (client x?) (client y?) (who x?) (who y?))
			)
			(if (client looper?)
				((client looper?) doit: client temp0)
			else
				(DirLoop client temp0)
			)
		)
	)
	
	(method (moveDone)
	)
	
	(method (setTarget)
		(cond 
			(argc (super setTarget: &rest))
			((not (self onTarget:)) (super setTarget: (who x?) (who y?)))
		)
	)
	
	(method (onTarget)
		(return (<= (client distanceTo: who) distance))
	)
)

(class Chase of Motion
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
		who 0
		distance 0
	)
	
	(method (init theClient theWho theDistance theCaller)
		(if (>= argc 1)
			(= client theClient)
			(if (>= argc 2)
				(= who theWho)
				(if (>= argc 3)
					(= distance theDistance)
					(if (>= argc 4) (= caller theCaller))
				)
			)
		)
		(super init: client (who x?) (who y?) caller)
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
	
	(method (setTarget)
		(cond 
			(argc (super setTarget: &rest))
			((not (self onTarget:)) (super setTarget: (who x?) (who y?)))
		)
	)
	
	(method (onTarget)
		(return (<= (client distanceTo: who) distance))
	)
)
