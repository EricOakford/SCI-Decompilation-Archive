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
		(if (> (++ form) largeBug) (= form smallBug) (= newLoop (- newLoop 3)))
		(self setLoop: newLoop fixState:)
		(if (and (!= form medium) (== (self onControl: origin) cGREEN))
			(self die:)
		)
		(if
			(and
				(<= priority 4)
				(== form 0)
				(== (self onControl: origin) 32)
			)
			(self die:)
		)
	)
	
	(method (fixState)
		(= illegalBits cWHITE)
		(if (<= priority 4)
			(= illegalBits (| illegalBits $4000))
		else
			(= illegalBits (| illegalBits $2000))
		)
		(switch form
			(0
				(= illegalBits (| illegalBits $0004))
				(= moveSpeed (= cycleSpeed 6))
			)
			(1
				(= illegalBits (| illegalBits $2000))
				(= moveSpeed (= cycleSpeed 12))
			)
			(2
				(= illegalBits (| illegalBits $2004))
				(= moveSpeed (= cycleSpeed 18))
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
	
	(method (canBeHere &tmp temp0 temp1)
		(if (not (self cantBeHere:))
			(= temp0 1)
			(if
				(or
					(and
						(& (= temp1 (self onControl: 0)) $1000)
						(self nearRock: 20)
					)
					(and (& temp1 $0800) (not (self nearBridge: 20)))
					(and (& temp1 $0004) (not (self nearLadder: 20)))
				)
				(= temp0 0)
			)
		else
			(= temp0 0)
		)
		(return temp0)
	)
)

(class MazeMove of Motion
	(properties
		curDir 5
		prevDir 5
		ditherTime 0
		altMove 0
		prevControl 0
	)
	
	(method (init theClient theX theY theCaller &tmp clientCycler temp1)
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
		(client
			heading: (= temp1 (GetAngle (client x?) (client y?) x y))
		)
		(= prevDir (= curDir (self calcDir: temp1)))
		(= b-moveCnt 0)
		(if (= clientCycler (client cycler?))
			(clientCycler cycleCnt: 0)
		)
	)
	
	(method (doit &tmp clientForm thePrevControl temp2)
		(if ditherTime (-- ditherTime) (return))
		(if (> (++ b-moveCnt) (client moveSpeed?))
			(= b-moveCnt 0)
			(= clientForm (client form?))
			(if
				(and
					(!=
						(= thePrevControl (client onControl: 1))
						prevControl
					)
					(< (client priority?) 4)
				)
				(client setPri: 4)
			)
			(switch thePrevControl
				(256 (client lowPri:))
				(512 (client highPri:))
				(4
					(if (not (client nearLadder: 20)) (client die:))
				)
				(2048
					(if (not (client nearBridge: 20)) (client die:))
				)
				(64 (self chooseRoute: 0))
				(8192
					(if (== (client priority?) 4) (client setPri: 2))
				)
				(32
					(if (== (client priority?) 4) (client setPri: 2))
				)
			)
			(= prevControl thePrevControl)
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
	
	(method (calcDir param1)
		(return
			(cond 
				((or (<= param1 22) (> param1 337)) 0)
				((<= param1 67) 1)
				((<= param1 112) 2)
				((<= param1 157) 3)
				((<= param1 202) 4)
				((<= param1 247) 5)
				((<= param1 292) 6)
				((<= param1 337) 7)
			)
		)
	)
	
	(method (doMove &tmp clientX clientY [temp2 2] temp4 temp5 clientMoveSpeed)
		(if
		(and (== self (ScriptID 32 2)) (== (Random 1 10) 7))
			(self setHeading: (= temp4 (Random 0 7)))
		)
		(= clientX (client x?))
		(= clientY (client y?))
		(if (self tryStep: clientX clientY curDir)
			(= prevDir curDir)
		else
			(self chooseRoute: 1)
			(= ditherTime
				(+
					1
					(= clientMoveSpeed (client moveSpeed?))
					clientMoveSpeed
				)
			)
			(client signal: (| (client signal?) $0400) forceUpd:)
			(return 0)
		)
		(client forceUpd:)
		(return 1)
	)
	
	(method (tryStep param1 param2 param3 &tmp clientXStep clientYStep)
		(= clientXStep (client xStep?))
		(= clientYStep (client yStep?))
		(if (== (++ altMove) 2)
			(= altMove 0)
			(if (& curDir $0001)
				(= clientXStep (<< clientXStep $0001))
				(= clientYStep (<< clientYStep $0001))
			)
		)
		(= clientXStep (setDeltaX param3 clientXStep))
		(= clientYStep (setDeltaY param3 clientYStep))
		(client
			x: (+ param1 clientXStep)
			y: (+ param2 clientYStep)
		)
		(BaseSetter client)
		(return
			(if (client canBeHere:)
				(return 1)
			else
				(if (== (++ altMove) 2) (= altMove 0))
				(client x: param1 y: param2)
				(BaseSetter client)
				(client signal: (| (client signal?) $0400) forceUpd:)
				(return 0)
			)
		)
	)
	
	(method (chooseRoute param1 &tmp temp0 theCurDir temp2 theTheTheCurDir temp4 temp5 temp6 theTheCurDir clientOtherBug temp9 temp10)
		(= theCurDir curDir)
		(if
		(or (== (Random 1 3) 2) (!= self (ScriptID 32 2)))
			(= temp0 (GetAngle (client x?) (client y?) x y))
			(= theCurDir (self calcDir: temp0))
		)
		(= clientOtherBug (client otherBug?))
		(if
		(< (= temp10 (client distanceTo: clientOtherBug)) 30)
			(cond 
				(
					(==
						(= temp9 (- (client form?) (clientOtherBug form?)))
						-1
					)
					(= temp0
						(GetAngle
							(clientOtherBug x?)
							(clientOtherBug y?)
							(client x?)
							(client y?)
						)
					)
					(= theCurDir (self calcDir: temp0))
				)
				(
					(and
						(< temp10 20)
						(== temp9 1)
						(<=
							(Abs (- (client priority?) (clientOtherBug priority?)))
							2
						)
					)
					(clientOtherBug die:)
				)
				(
					(or
						(== temp9 1)
						(and (== temp9 0) (not (Random 0 3)))
					)
					(= temp0
						(GetAngle
							(client x?)
							(client y?)
							(clientOtherBug x?)
							(clientOtherBug y?)
						)
					)
					(= theCurDir (self calcDir: temp0))
				)
			)
		)
		(= theTheCurDir theCurDir)
		(= temp2 (= temp5 0))
		(while (<= temp2 4)
			(= temp6
				(self
					checkRoute: (= theTheTheCurDir (mod (+ theCurDir temp2) 8))
				)
			)
			(if (== theTheTheCurDir (mod (+ curDir 4) 8))
				(= temp6 (- temp6 4))
			)
			(if (> temp6 temp5)
				(= temp5 temp6)
				(= theTheCurDir theTheTheCurDir)
			)
			(= temp6
				(self
					checkRoute: (= theTheTheCurDir (mod (- (+ theCurDir 8) temp2) 8))
				)
			)
			(if (== theTheTheCurDir (mod (+ curDir 4) 8))
				(= temp6 (- temp6 4))
			)
			(if (> temp6 temp5)
				(= temp5 temp6)
				(= theTheCurDir theTheTheCurDir)
			)
			(++ temp2)
		)
		(= theCurDir theTheCurDir)
		(while (and param1 (== curDir theCurDir))
			(= theCurDir (Random 0 7))
		)
		(self setHeading: theCurDir)
		(client forceUpd:)
		(return curDir)
	)
	
	(method (checkRoute param1 &tmp clientX clientY temp2 temp3 temp4 temp5)
		(= temp2 (= clientX (client x?)))
		(= temp3 (= clientY (client y?)))
		(= temp4 (/ (client smarts?) 2))
		(= temp5 0)
		(while (< temp5 temp4)
			(if (not (self tryStep: temp2 temp3 param1)) (break))
			(++ temp5)
		)
		(client x: clientX y: clientY)
		(BaseSetter client)
		(return temp5)
	)
	
	(method (setHeading h)
		(= prevDir curDir)
		(= curDir h)
		(client
			heading:
				(switch (= curDir h)
					(0 0)
					(1 45)
					(2 90)
					(3 135)
					(4 180)
					(5 225)
					(6 270)
					(7 315)
				)
		)
	)
)
