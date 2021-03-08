;;; Sierra Script 1.0 - (do not remove this comment)
(script# GRAVMOVE)
(include game.sh)
(use Main)
(use Motion)
(use System)


(local
	[local0 8] = [7 9 11 13 0 2 3 5]
)
(class GravMover of Motion
	(properties
		client 0
		curHeading 0
		b-moveCnt2 0
		theSpeed 0
	)
	
	(method (init c h theY)
		(if argc
			(= client c)
			(if (> argc 1)
				(if (== argc 2)
					(= curHeading h)
					(self setTarget:)
					(= theSpeed
						(/ (GetDistance x y (client x?) (client y?)) 10)
					)
				else
					(= curHeading
						(GetAngle (client x?) (client y?) h theY)
					)
					(= x h)
					(= y theY)
					(= theSpeed (GetDistance x y (client x?) (client y?)))
					(self setTarget:)
				)
			else
				(= curHeading (client heading?))
				(self setTarget:)
				(= theSpeed
					(/ (GetDistance x y (client x?) (client y?)) 10)
				)
			)
		)
		(= b-moveCnt2 (+ 1 theSpeed gameTime))
		(super init:)
	)
	
	(method (doit &tmp [temp0 8])
		(if (>= (Abs (- gameTime b-moveCnt2)) theSpeed)
			(= b-moveCnt2 gameTime)
			(if dx
				(if (> dx 0) (-- dx) else (++ dx))
			else
				(= dx (- (Random 1 3) 2))
			)
			(if dy
				(if (> dy 0) (-- dy) else (++ dy))
			else
				(= dy (- (Random 1 3) 2))
			)
		)
		(if
		(>= (Abs (- gameTime b-moveCnt)) (client moveSpeed?))
			(= b-moveCnt gameTime)
			(DoBresen self)
		)
	)
	
	(method (setTarget)
		(= x (+ (client x?) (SinMult curHeading 500)))
		(= y (- (client y?) (CosMult curHeading 500)))
	)
	
	(method (onTarget)
		(return FALSE)
	)
)

(class SpecialLooper of Code
	(properties
		oldDir -1
		client 0
		oldMover 0
	)
	
	(method (init c)
		(= client c)
		(client looper: self)
	)
	
	(method (doit c dir)
		(= client c)
		(if (!= dir oldDir)
			(client heading: dir)
			(if
			(< [local0 (/ dir 45)] [local0 (/ oldDir 45)])
				(client setCycle: CycleTo [local0 (/ dir 45)] -1)
			else
				(client setCycle: CycleTo [local0 (/ dir 45)] 1)
			)
			(= oldDir dir)
		)
	)
	
	(method (dispose)
		(if client (client looper: 0))
		(super dispose:)
	)
)

(class SpecialCycler of Cycle
	(properties
		theCycleSpeed 6
		isMover 0
	)
	
	(method (doit &tmp specialCyclerNextCel)
		(cond 
			(
				(and
					(not isMover)
					(client mover?)
					(< (+ ((client mover?) dx?) ((client mover?) dy?)) 3)
				)
				(= isMover 1)
				(= cycleDir (if (Random 0 1) 1 else -1))
				(= theCycleSpeed (Random 3 15))
			)
			(
				(and
					(client mover?)
					(>=
						(+ ((client mover?) dx?) ((client mover?) dy?))
						3
					)
				)
				(= theCycleSpeed
					(-
						15
						(+ ((client mover?) dx?) ((client mover?) dy?))
					)
				)
				(= isMover 0)
			)
		)
		(if
			(or
				(>
					(= specialCyclerNextCel (self nextCel:))
					(client lastCel:)
				)
				(< specialCyclerNextCel 0)
			)
			(self cycleDone: specialCyclerNextCel)
		else
			(client cel: specialCyclerNextCel)
		)
	)
	
	(method (nextCel)
		(return
			(if (< (Abs (- gameTime cycleCnt)) theCycleSpeed)
				(client cel?)
			else
				(= cycleCnt gameTime)
				(+ (client cel?) cycleDir)
			)
		)
	)
	
	(method (cycleDone param1)
		(if (> param1 (client lastCel:))
			(client cel: 0)
		else
			(client cel: (client lastCel:))
		)
	)
)
