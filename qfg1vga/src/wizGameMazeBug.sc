;;; Sierra Script 1.0 - (do not remove this comment)
(script# MAZEBUG)
(include game.sh)
(use Motion)
(use Actor)


(procedure (setDeltaX theDir DX)
	(switch theDir
		(mazeN (= DX 0))
		(mazeS (= DX 0))
		(mazeSW (= DX (- DX)))
		(mazeW (= DX (- DX)))
		(mazeNW (= DX (- DX)))
	)
	(return DX)
)

(procedure (setDeltaY theDir DY)
	(switch theDir
		(mazeN (= DY (- DY)))
		(mazeNE (= DY (- DY)))
		(mazeE (= DY 0))
		(mazeW (= DY 0))
		(mazeNW (= DY (- DY)))
	)
	(return DY)
)

(class MazeBug of Actor
	(properties
		xStep 		2	;set explicitly for mazeBug
		yStep 		2	;set explicitly for mazeBug
		startX 0
		startY 0
		deadTime 0
		otherBug 0
		form 0
		smarts 5
		path 0
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: LowPri fixState:)
	)
	
	(method (doit &tmp temp0)
		(if deadTime
			(if (not (-- deadTime))
				(self
					posn: startX startY
					setLoop: (- loop (- form smallBug))
					form: smallBug
					lowPri:
					fixState:
				)
			)
		else
			(super doit:)
		)
	)
	
	(method (highPri)
		(self setPri: HighPri fixState:)
	)
	
	(method (lowPri)
		(self setPri: LowPri fixState:)
	)
	
	(method (changeForm &tmp newLoop)
		(= newLoop (+ loop 1))
		(if (> (++ form) largeBug)
			(= form smallBug)
			(-= newLoop 3)
		)
		(self setLoop: newLoop fixState:)
		(if (and (!= form medium) (== (self onControl: origin) cGREEN))
			(self die:)
		)
		(if
			(and
				(<= priority LowPri)
				(== form 0)
				(== (self onControl: origin) cMAGENTA)
			)
			(self die:)
		)
	)
	
	(method (fixState)
		(= illegalBits cWHITE)
		(if (<= priority LowPri)
			(|= illegalBits cYELLOW)
		else
			(|= illegalBits cLMAGENTA)
		)
		(switch form
			;EO: move speeds changed to fix speed bug
			(smallBug
				(|= illegalBits cGREEN)
				(= moveSpeed 128)
				(= cycleSpeed 6)
			)
			(mediumBug
				(|= illegalBits cLMAGENTA)
				(= moveSpeed 256)
				(= cycleSpeed 12)
			)
			(largeBug
				(|= illegalBits (| cLMAGENTA cGREEN))
				(= moveSpeed 512)
				(= cycleSpeed 18)
			)
		)
	)
	
	(method (die)
		(= deadTime 20)
	)
	
	(method (nearRock)
		(return FALSE)
	)
	
	(method (nearBridge)
		(return TRUE)
	)
	
	(method (nearLadder)
		(return TRUE)
	)
	
	(method (canBeHere &tmp canWe ctrls)
		(if (not (self cantBeHere:))
			(= canWe TRUE)
			(if
				(or
					(and
						(& (= ctrls (self onControl: 0)) cLRED)
						(self nearRock: 20)
					)
					(and (& ctrls cLCYAN) (not (self nearBridge: 20)))
					(and (& ctrls cGREEN) (not (self nearLadder: 20)))
				)
				(= canWe FALSE)
			)
		else
			(= canWe FALSE)
		)
		(return canWe)
	)
)

(class MazeMove of Motion
	(properties
		curDir mazeSW
		prevDir mazeSW
		ditherTime 0
		altMove 0
		prevControl 0
	)
	
	(method (init actor xTo yTo whoCares &tmp cy theAngle)
		(if (>= argc 1)
			(= client actor)
			(if (>= argc 2)
				(= x xTo)
				(if (>= argc 3)
					(= y yTo)
					(if (>= argc 4)
						(= caller whoCares)
					)
				)
			)
		)
		(client heading: (= theAngle (GetAngle (client x?) (client y?) x y)))
		(= prevDir (= curDir (self calcDir: theAngle)))
		(= b-moveCnt 0)
		(if (= cy (client cycler?))
			(cy cycleCnt: 0)
		)
	)
	
	(method (doit &tmp clForm thisControl temp2)
		(if ditherTime (-- ditherTime) (return))
		(if (> (++ b-moveCnt) (client moveSpeed?))
			(= b-moveCnt 0)
			(= clForm (client form?))
			(if
				(and
					(!=
						(= thisControl (client onControl: origin))
						prevControl
					)
					(< (client priority?) LowPri)
				)
				(client setPri: LowPri)
			)
			(switch thisControl
				(cGREY
					(client lowPri:)
				)
				(cLBLUE
					(client highPri:)
				)
				(cGREEN
					(if (not (client nearLadder: 20))
						(client die:)
					)
				)
				(cLCYAN
					(if (not (client nearBridge: 20))
						(client die:)
					)
				)
				(cBROWN
					(self chooseRoute: 0)
				)
				(cLMAGENTA
					(if (== (client priority?) 4)
						(client setPri: 2)
					)
				)
				(cMAGENTA
					(if (== (client priority?) 4)
						(client setPri: 2)
					)
				)
			)
			(= prevControl thisControl)
			(self doMove:)
		)
		(if
			(and
				(<= (Abs (- x (client x?))) 2)
				(<= (Abs (- y (client y?))) 2)
			)
			(client posn: x y)
			(self moveDone:)
			(return)
		)
	)
	
	(method (calcDir theHeading)
		(return
			(cond 
				((or (<= theHeading 22) (> theHeading 337)) mazeN)
				((<= theHeading 67) mazeNE)
				((<= theHeading 112) mazeE)
				((<= theHeading 157) mazeSE)
				((<= theHeading 202) mazeS)
				((<= theHeading 247) mazeSW)
				((<= theHeading 292) mazeW)
				((<= theHeading 337) mazeNW)
			)
		)
	)
	
	(method (doMove &tmp oldX oldY DX DY newDir newAngle mSpeed)
		(if (and (== self (ScriptID 32 2)) (== (Random 1 10) 7))
			(self setHeading: (= newDir (Random 0 7)))
		)
		(= oldX (client x?))
		(= oldY (client y?))
		(if (self tryStep: oldX oldY curDir)
			(= prevDir curDir)
		else
			(self chooseRoute: TRUE)
			(= ditherTime
				(+
					1
					(= mSpeed (client moveSpeed?))
					mSpeed
				)
			)
			(client signal: (| (client signal?) blocked) forceUpd:)
			(return FALSE)
		)
		(client forceUpd:)
		(return TRUE)
	)
	
	(method (tryStep oldX oldY theDir &tmp DX DY)
		(= DX (client xStep?))
		(= DY (client yStep?))
		(if (== (++ altMove) 2)
			(= altMove 0)
			(if (& curDir 1)
				(<<= DX 1)
				(<<= DY 1)
			)
		)
		(= DX (setDeltaX theDir DX))
		(= DY (setDeltaY theDir DY))
		(client
			x: (+ oldX DX)
			y: (+ oldY DY)
		)
		(BaseSetter client)
		(return
			(if (client canBeHere:)
				(return TRUE)
			else
				(if (== (++ altMove) 2) (= altMove 0))
				(client x: oldX y: oldY)
				(BaseSetter client)
				(client signal: (| (client signal?) blocked) forceUpd:)
				(return FALSE)
			)
		)
	)
	
	(method (chooseRoute wasBlocked
			&tmp theAngle newDir incr aDir sm best cur bestDir other forms dist)
		(= newDir curDir)
		(if (or (== (Random 1 3) 2) (!= self (ScriptID 32 2)))
			(= theAngle (GetAngle (client x?) (client y?) x y))
			(= newDir (self calcDir: theAngle))
		)
		(= other (client otherBug?))
		(if (< (= dist (client distanceTo: other)) ChaseRange)
			(cond 
				(
					(==
						(= forms (- (client form?) (other form?)))
						-1
					)
					(= theAngle
						(GetAngle
							(other x?)
							(other y?)
							(client x?)
							(client y?)
						)
					)
					(= newDir (self calcDir: theAngle))
				)
				(
					(and
						(< dist 20)
						(== forms 1)
						(<=
							(Abs (- (client priority?) (other priority?)))
							2
						)
					)
					(other die:)
				)
				(
					(or
						(== forms 1)
						(and (== forms 0) (not (Random 0 3)))
					)
					(= theAngle
						(GetAngle
							(client x?)
							(client y?)
							(other x?)
							(other y?)
						)
					)
					(= newDir (self calcDir: theAngle))
				)
			)
		)
		(= bestDir newDir)
		(= incr (= best 0))
		(while (<= incr 4)
			(= cur
				(self
					checkRoute: (= aDir (mod (+ newDir incr) 8))
				)
			)
			(if (== aDir (mod (+ curDir 4) 8))
				(-= cur 4)
			)
			(if (> cur best)
				(= best cur)
				(= bestDir aDir)
			)
			(= cur
				(self
					checkRoute: (= aDir (mod (- (+ newDir 8) incr) 8))
				)
			)
			(if (== aDir (mod (+ curDir 4) 8))
				(-= cur 4)
			)
			(if (> cur best)
				(= best cur)
				(= bestDir aDir)
			)
			(++ incr)
		)
		(= newDir bestDir)
		(while (and wasBlocked (== curDir newDir))
			(= newDir (Random 0 7))
		)
		(self setHeading: newDir)
		(client forceUpd:)
		(return curDir)
	)
	
	(method (checkRoute theDir &tmp oldX oldY curX curY sm index)
		(= curX (= oldX (client x?)))
		(= curY (= oldY (client y?)))
		(= sm (/ (client smarts?) 2))
		(for ((= index 0)) (< index sm) ((++ index))
			(if (not (self tryStep: curX curY theDir))
				(break)
			)
		)
		(client x: oldX y: oldY)
		(BaseSetter client)
		(return index)
	)
	
	(method (setHeading newHeading)
		(= prevDir curDir)
		(= curDir newHeading)
		(client heading:
			(switch (= curDir newHeading)
				(mazeN	0)
				(mazeNE	45)
				(mazeE	90)
				(mazeSE	135)
				(mazeS	180)
				(mazeSW	225)
				(mazeW	270)
				(mazeNW	315)
			)
		)
	)
)
