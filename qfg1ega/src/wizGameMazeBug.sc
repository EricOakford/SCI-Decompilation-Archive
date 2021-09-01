;;; Sierra Script 1.0 - (do not remove this comment)
; MazeBug class to handle behavior of bugs in wizard's game (Mages' Mazes)

(script# MAZEBUG) ;MAZEBUG = 238
(include game.sh) (include maze.sh)
(use Motion)
(use Actor)


(procedure (setDeltaX theDir DX)
	(switch theDir
		(mazeN 	(= DX 0))
		(mazeS 	(= DX 0))
		(mazeSW (= DX (- DX)))
		(mazeW 	(= DX (- DX)))
		(mazeNW (= DX (- DX)))
	)
	(return DX)
)

(procedure (setDeltaY theDir DY)
	(switch theDir
		(mazeN 	(= DY (- DY)))
		(mazeNE (= DY (- DY)))
		(mazeE 	(= DY 0))
		(mazeW 	(= DY 0))
		(mazeNW (= DY (- DY)))
	)
	(return DY)
)

(class MazeBug of Actor
	(properties
		yStep 		2	;set explicitly for mazeBug
		xStep 		2	;set explicitly for mazeBug
		startX 		0			; Where to come back after dying
		startY 		0
		deadTime 	0			; How long before we resurrect
		otherBug 	0			; I.D. of opponent
		form 		smallBug	; Which type (size) of creature
		smarts 		5			; How far bug thinks/looks ahead
		path 		0			; Which path we're following
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: LowPri fixState:)
	)
	
	(method (doit &tmp whichControl)
		(if deadTime
			(if (not (-- deadTime))	; Time to be resurrected
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
	
	(method (canBeHere &tmp canWe ctrls)
		(if
			(and
				(= canWe (super canBeHere:))
				(or
					(and (& (= ctrls (self onControl: 0)) cLRED) (self nearRock: 25))
					(and (& ctrls cLCYAN) (not (self nearBridge: 25)))
					(and (& ctrls cGREEN) (not (self nearLadder: 25)))
				)
			)
			(= canWe FALSE)
		)
		(return canWe)
	)
	
	(method (highPri)
		; Switch to higher priority
		
		(self setPri: HighPri fixState:)
	)
	
	(method (lowPri)
		; Switch to lower priority
		
		(self setPri: LowPri fixState:)
	)
	
	(method (changeForm &tmp newLoop)
		; Trigger changes to next size/shape
		
		(= newLoop (+ loop 1))
		(if (> (++ form) largeBug)
			(= form smallBug)
			(= newLoop (- newLoop 3))
		)
		(self setLoop: newLoop fixState:)
		(if (and (!= form mediumBug) (== (self onControl: origin) cGREEN))
			(self die:)	; Changing form while on a ladder
		)
	)
	
	(method (fixState)
		; Set illegalBits and such based on priority/size
		
		; Account for current creature size
		(= illegalBits cWHITE)
		(if (<= priority LowPri)
			(= illegalBits (| illegalBits cYELLOW))		; On lower path
		else
			(= illegalBits (| illegalBits cLMAGENTA))	; On upper path
		)
		(switch form
			(smallBug
				; Too small for ladders
				(|= illegalBits cGREEN)
				(= moveSpeed (= cycleSpeed 0))
			)
			(mediumBug
				; Too big to enter tunnels
				(|= illegalBits cLMAGENTA)
				(= moveSpeed (= cycleSpeed 1))
			)
			(largeBug
				; Too big to enter tunnels or climb ladders (clumsy)
				(= illegalBits (| illegalBits cLMAGENTA cGREEN))
				(= moveSpeed (= cycleSpeed 2))
			)
		)
	)
	
	(method (die)
		; Fall into the chasm and start game over
		
		(= deadTime 20)		; Disappear for 20 cycles
	)
	
	(method (nearRock)
		; Are we close to a rock?
		
		(return FALSE)		; Implemented in instance
	)
	
	(method (nearBridge)
		; Are we close to a bridge?
		
		(return TRUE)		; Implemented in instance
	)
	
	(method (nearLadder)
		; Are we close to a ladder?
		
		(return TRUE)		; Implemented in instance
	)
)

(class MazeMove of Motion
	; Motion class for bugs in maze
	
	(properties
		client 		0		; Actor we are controlling
		caller 		0		; Object to cue when complete/blocked
		x 			0		; Eventual destination
		y 			0
		dx 			0
		dy 			0
		b-moveCnt 	0		; Iterations of doit to skip
		b-i1 		0
		b-i2 		0
		b-di 		0
		b-xAxis 	0
		b-incr 		0
		completed 	0		; Set to TRUE when move is complete
		;new properties for MazeMove
		curDir 		mazeSW	; Which way we're going (compass direction)
		prevDir 	mazeSW	; Which way we last went (compass direction)
		ditherTime 	0		; How long we should stand and dither
		altMove 	0		; Kludge to make diagonal moves close to correct
		prevControl 0		; Which control color we were on last doit cycle
	)
	
	;; Initialize mover -- do *not* do a (super init:)
	(method (init actor xTo yTo toCall &tmp cy theAngle)
		(if (>= argc 1)				(= client actor)
			(if (>= argc 2)			(= x xTo)
				(if (>= argc 3)		(= y yTo)
					(if (>= argc 4) (= caller toCall))
				)
			)
		)
		
		;Set actor's heading.
		(client heading: (= theAngle (GetAngle (client x?) (client y?) x y)))
		(= prevDir (= curDir (self calcDir: theAngle)))
		
		(= b-moveCnt 0)
		(if (= cy (client cycler?))		; Keep cycler in synch with mover
			(cy cycleCnt: 0)
		)
	)
	
	
	;; Do next step -- do *not* do a (super doit:)
	(method (doit &tmp clForm thisControl)
		(if ditherTime 
			(-- ditherTime)
			(return)
		)
		
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
				(client setPri: LowPri)	; (Maybe) just left a tunnel
			)
			
			(switch thisControl
				(cGREY
					(client lowPri:)
				)
				
				(cLBLUE
					(client highPri:)
				)
				
				(cGREEN			; On a ladder -- get off quickly
					(if (not (client nearLadder: 25))
						(client die:)	; Somebody yanked away our ladder
					)
				)
				
				(cLCYAN			; On a bridge, or where one should be
					(if (not (client nearBridge: 25))
						(client die:)	; Fall into the chasm
					)
				)
				
				(cBROWN 		; At an intersection -- choose best path
					(self chooseRoute: FALSE)
				)
				
				(cLMAGENTA
					(if (== (client priority?) LowPri)
						(client setPri: (- LowPri 2))
					)
				)
				(cMAGENTA
					(if (== (client priority?) LowPri)
						(client setPri: (- LowPri 2))
					)
				)
			)
			
			(= prevControl thisControl)
			(self doMove:)		; Make next move (if we can)
		)
		
		;If our client is where he should be, we terminate.
		(if
			(and
				(<= (Abs (- x (client x?))) 2)
				(<= (Abs (- y (client y?))) 2)
			)
			(self moveDone:)
			(return)
		)
	)
	
	
	(method (calcDir theHeading)
		(return
			(cond 
				((or (<= theHeading 22) (> theHeading 337)) mazeN) ;HEADING_N
				((<= theHeading 67)  mazeNE)	;HEADING_NE
				((<= theHeading 112) mazeE)		;HEADING_E
				((<= theHeading 157) mazeSE)	;HEADING_SE
				((<= theHeading 202) mazeS)		;HEADING_S
				((<= theHeading 247) mazeSW)	;HEADING_SW
				((<= theHeading 292) mazeW)		;HEADING_W
				((<= theHeading 337) mazeNW)	;HEADING_NW
			)
		)
	)
	
	; Actually do next step of motion
	(method (doMove &tmp oldX oldY DX DY newDir newAngle mSpeed) ;[temp2 2] was where DX and DY are.
		(if (== (Random 1 10) 7)
			(self setHeading: (= newDir (Random 0 7))) ; Monkey wrench
		)
		
		(= oldX (client x?))
		(= oldY (client y?))
		
		(if (self tryStep: oldX oldY curDir)
			(= prevDir curDir)	; Don't care about previous bump anymore
		else
			(self chooseRoute: TRUE)	; Blocked -- get a new route
			(= ditherTime				; Dither for a bit
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
			(if (& curDir $0001) ; Moving at a diagonal
				(= DX (<< DX $0001))
				(= DY (<< DY $0001))
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
				(if (== (++ altMove) 2)
					(= altMove 0)
				)
				
				(client 
					x: oldX 
					y: oldY
				)
				
				(BaseSetter client)
				(client signal: (| (client signal?) blocked) forceUpd:)
				(return FALSE)
			)
		)
	)
	
	(method (chooseRoute wasBlocked 
			&tmp theAngle newDir incr aDir sm best cur bestDir other forms dist)

		(= newDir curDir)
		(if (== (Random 1 3) 2)
			(= theAngle (GetAngle (client x?) (client y?) x y))
			(= newDir (self calcDir: theAngle))
		)
		
		(= other (client otherBug?))
		
		(if
		(< (= dist (client distanceTo: other)) ChaseRange)
			(cond 
				(
					(==	; Other bug wants to eat us -- run away!
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
				
				((and 	(< dist 10)
					  	(== forms mediumBug)
					)
					; Yum -- we caught dinner!
					(other die:)
				)
				
				; Oh, what a cute/tasty little bug -- let's chase it!
				; (if merely "cute", only a 1 in 4 of chasing it)
				;EO: By 1.200, the random "cute" factor was implemented.
				((or (== forms mediumBug) (and (== forms smallBug) (not (Random 0 3)))
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

		;; Check routes, preferring one selected above.  If none of them
		;;    work, stick with the pre-chosen one.
		(= bestDir newDir)
		(= incr (= best 0))
		(while (<= incr 4)
			(= cur
				(self
					checkRoute: (= aDir (mod (+ newDir incr) 8))
				)
			)
			(if (== aDir (mod (+ curDir 4) 8))
				(= cur (- cur 4))	; Try not to reverse directions
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
				(= cur (- cur 4))	; Try not to reverse directions
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
	
	;; See if endpoint of potential route is legal place for creature to be
	;;    (look-ahead distance determined by creature's smarts).
	(method (checkRoute theDir &tmp oldX oldY curX curY sm index)
		(= curX (= oldX (client x?)))
		(= curY (= oldY (client y?)))
		(= sm (/ (client smarts?) 2))
		
		(= index 0)
		(while (< index sm)
			(if (not (self tryStep: curX curY theDir))
				(break)
			)
			(++ index)
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
				(mazeN  0)
				(mazeNE 45)
				(mazeE  90)
				(mazeSE 135)
				(mazeS  180)
				(mazeSW 225)
				(mazeW  270)
				(mazeNW 315)
			)
		)
	)
)