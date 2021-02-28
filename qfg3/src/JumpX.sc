;;; Sierra Script 1.0 - (do not remove this comment)
(script# JUMPX) ;57
(include game.sh)
(use Main)
(use Motion)


(class JumpX of Motion
	(properties
		desX 0
		desY 0
		xInc 0
		yInc 0
		zStep 0
		level 0
		velocity 0
		gravity 10
		height 0
		peak 0
	)
	
	(method (init actor param2 whoCares theCaller_2 theCaller_3 param6 &tmp temp0 temp1 temp2 temp3)
		(= client actor)
		(= temp0 0)
		(= temp1 0)
		(= temp2 0)
		(switch argc
			(2 (self height: param2))
			(3
				(self height: param2)
				(= caller whoCares)
			)
			(4
				(self desX: param2)
				(self height: whoCares)
				(= temp0 (Abs (- param2 (client x?))))
				(= caller theCaller_2)
			)
			(5
				(self desX: param2)
				(self desY: whoCares)
				(= temp0 (Abs (- param2 (client x?))))
				(= temp1 (Abs (- whoCares (client y?))))
				(self height: theCaller_2)
				(= caller theCaller_3)
			)
			(else 
				(= caller theCaller_3)
				(self height: theCaller_2)
			)
		)
		(if (== argc 7)
			(self level: param6)
		else
			(self level: (client z?))
		)
		(= temp3 (/ gravity 2))
		(if
		(> (= temp2 (- (self height?) (client z?))) 0)
			(self velocity: (+ (Sqrt (* 2 temp2 temp3)) 1))
			(self zStep: (- 0 temp3))
		else
			(self peak: 1)
			(self zStep: temp3)
			(self level: height)
		)
		(if
			(<
				(self xInc: (/ temp0 (* (+ (/ velocity temp3) 1) 2)))
				0
			)
			(self xInc: 1)
		)
		(if
			(<
				(self yInc: (/ temp1 (* (+ (/ velocity temp3) 1) 2)))
				0
			)
			(self yInc: 1)
		)
		(= b-moveCnt gameTime)
	)
	
	(method (doit)
		(if (>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
			(= b-moveCnt gameTime)
			(if
				(and
					(client cycler?)
					((client cycler?) isKindOf: JumpCycler)
					(not ((client cycler?) clear?))
				)
				(return)
			)
			(if (self peak?)
				(if (< (- (client z?) velocity) level)
					(client z: level)
				else
					(client z: (- (client z?) velocity))
				)
			else
				(client z: (+ (client z?) velocity))
			)
			(if (!= xInc 0) (self moveXPlane:))
			(if (!= yInc 0) (self moveYPlane:))
			(= velocity (+ velocity zStep))
			(if (or (<= velocity 0) (<= (client z?) level))
				(if (self peak?)
					(self moveDone:)
				else
					(self peak: 1)
					(self zStep: (- 0 zStep))
					(self chkTarget:)
				)
				(return)
			)
		)
	)
	
	(method (moveDone)
		(if caller
			(= completed TRUE)
		)
		(if (client cycler?)
			(if ((client cycler?) isKindOf: JumpCycler)
				(if ((client cycler?) clear?) (return))
			else
				(= doMotionCue TRUE)
			)
		else
			(= doMotionCue TRUE)
		)
	)
	
	(method (motionCue)
		(client mover: 0)
		(if (and completed (IsObject caller))
			(caller cue:)
		)
		(self dispose:)
	)
	
	(method (moveXPlane)
		(cond 
			((< xInc 0)
				(if (< (+ (client x?) xInc) desX)
					(client x: desX)
					(= xInc 0)
				else
					(client x: (+ (client x?) xInc))
				)
			)
			((> (+ (client x?) xInc) desX) (client x: desX)
				(= xInc 0)
			)
			(else
				(client x: (+ (client x?) xInc))
			)
		)
	)
	
	(method (moveYPlane)
		(cond 
			((< yInc 0)
				(if (< (+ (client y?) yInc) desY)
					(client y: desY)
					(= yInc 0)
				else
					(client y: (+ (client y?) yInc))
				)
			)
			((> (+ (client y?) yInc) desY) (client y: desY) (= yInc 0))
			(else (client y: (+ (client y?) yInc)))
		)
	)
	
	(method (chkTarget)
	)
)

(class JumpCycler of Cycle
	(properties
		clear 0
		start 0
		mid 0
		changed TRUE
	)
	
	(method (init param1 theStart theMid)
		(= start theStart)
		(= mid theMid)
		(super init: param1)
		(client cel: 0)
	)
	
	(method (doit &tmp jumpCyclerNextCel)
		(cond 
			((not (self clear?))
				(= jumpCyclerNextCel (self nextCel:))
				(client cel: jumpCyclerNextCel)
				(if (!= jumpCyclerNextCel (client lastCel:))
					(if
						(or
							(== jumpCyclerNextCel (self start:))
							(== jumpCyclerNextCel (self mid?))
						)
						(self clear: 1)
					)
				else
					(self clear: 1)
				)
			)
			((and ((client mover?) peak?) (self changed?))
				(self clear: 0)
				(self changed: 0)
			)
			((== (client cel?) (client lastCel:))
				(client cel: 0)
				(self cycleDone:)
			)
			(((client mover?) completed?)
				(self clear: 0)
			)
		)
	)
	
	(method (cycleDone)
		(= completed TRUE)
		(client cycler: 0)
		(self dispose:)
		((client mover?) motionCue:)
	)
)
