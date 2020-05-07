;;; Sierra Script 1.0 - (do not remove this comment)
(script# 985)
(include game.sh)
(use System)


(class Avoid of Object
	(properties
		client 0
		heading -1000
		pickLoop 0
		bumpTurn 0
		lastBumped 1
		thisTurn 0
		escaping 0
		escapes 1
		escapeTurn 32000
		nearestDist 0
		counter 10
		nonBumps 0
		targetX 0
		targetY 0
		motionInited 0
		outOfTouch 0
	)
	
	(method (init theClient theOutOfTouch)
		(if (>= argc 1) (= client theClient))
		(if (>= argc 2) (= outOfTouch theOutOfTouch))
		(= heading (client heading?))
		(= nearestDist 0)
		(= counter 10)
		(= thisTurn 0)
	)
	
	(method (doit &tmp theHeading theEscapeTurn clientX clientY theNonBumps theTargetX clientMover temp7 temp8 clientMoverB_moveCnt temp10 temp11 temp12 temp13)
		(cond 
			((not (= clientMover (client mover?))) (= heading -1000) (return))
			((clientMover setTarget:)
				(if targetY else (InitBresen clientMover))
				(clientMover doit:)
				(return)
			)
			((== heading -1000) (self init:))
		)
		(= temp13 0)
		(= temp12 (<= counter 3))
		(clientMover triedToMove:)
		(= theNonBumps (clientMover x?))
		(= theTargetX (clientMover y?))
		(= clientX (client x?))
		(= clientY (client y?))
		(= temp7
			(GetAngle clientX clientY theNonBumps theTargetX)
		)
		(= theEscapeTurn
			(GetDistance clientX clientY theNonBumps theTargetX)
		)
		(= theHeading
			(= heading (umod (* 45 (/ heading 45)) 360))
		)
		(cond 
			(
			(not (if thisTurn else (= temp12 (<= counter 2))))
				(if (not targetY)
					(= targetY 1)
					(InitBresen clientMover)
				)
				(clientMover doit:)
				(if
				(or (!= clientX (client x?)) (!= clientY (client y?)))
					(self incClientPos: temp7)
					(++ counter)
					(return)
				else
					(= counter 0)
					(= temp13 1)
					(= targetY 0)
				)
			)
			(
				(<
					(= clientMoverB_moveCnt (clientMover b-moveCnt?))
					(client moveSpeed?)
				)
				(clientMover b-moveCnt: (++ clientMoverB_moveCnt))
				(return)
			)
			(else (clientMover b-moveCnt: 0))
		)
		(cond 
			(
				(or
					(!= nonBumps (= nonBumps theNonBumps))
					(!= targetX (= targetX theTargetX))
					(and
						thisTurn
						(or
							(<= (-- nearestDist) 0)
							(and (< theEscapeTurn escapeTurn) (< nearestDist 40))
						)
					)
				)
				(= targetY (= thisTurn 0))
				(= escapeTurn 32000)
				(= nearestDist 0)
			)
			(thisTurn 1)
			((< theEscapeTurn escapeTurn) (= escapeTurn theEscapeTurn) (= nearestDist 0))
			((= thisTurn (>= (++ nearestDist) 50)) (= nearestDist (Random 40 80)) (= escapes (- escapes)))
		)
		(cond 
			((<= (= temp7 (- temp7 theHeading)) -180) (= temp7 (+ temp7 360)))
			((> temp7 180) (= temp7 (- temp7 360)))
		)
		(= temp11 (<= (= temp8 (Abs temp7)) 90))
		(= lastBumped
			(if
				(cond 
					(thisTurn (sign temp7))
					(temp11 (sign temp7))
					(pickLoop)
					(bumpTurn)
					(else (sign temp7))
				)
			else
				escapes
			)
		)
		(if (and (> temp8 22) (or 0 (not thisTurn)))
			(= heading (+ heading (* lastBumped 45)))
		)
		(if thisTurn (= lastBumped escapes))
		(self distanceTo:)
		(if (self canBeHere:)
			(++ counter)
			(= pickLoop 0)
			(if (not thisTurn) (return))
		else
			(= bumpTurn pickLoop)
			(= pickLoop lastBumped)
			(= counter 0)
		)
		(if thisTurn
			(= theHeading (* 45 (/ heading 45)))
			(= temp10 0)
			(while (and (< temp10 8) (self canBeHere:))
				(= heading
					(umod (+ theHeading (* 45 temp10 escapes)) 360)
				)
				(client x: clientX y: clientY)
				(self distanceTo:)
				(++ temp10)
			)
			(if (== temp10 8)
				(= heading (+ heading (* escapes 45)))
			)
			(= theHeading heading)
		)
		(= temp10 (= clientMoverB_moveCnt 1))
		(while
			(and
				(not (self canBeHere:))
				(< clientMoverB_moveCnt 8)
			)
			(client x: clientX y: clientY)
			(= heading (- theHeading (* 45 temp10 lastBumped)))
			(self distanceTo:)
			(= temp10
				(cond 
					(thisTurn (++ clientMoverB_moveCnt))
					(temp13 (- (++ clientMoverB_moveCnt)))
					((> temp10 0) (- temp10))
					(else (++ clientMoverB_moveCnt))
				)
			)
		)
		(if (not (self canBeHere:)) (client offScreenOK:))
		(self incClientPos: heading)
	)
	
	(method (distanceTo)
		(client
			x: (+
				(client x?)
				(* (sign (TimesSin heading 100)) (client xStep?))
			)
			y: (-
				(client y?)
				(* (sign (TimesCos heading 100)) (client yStep?))
			)
			heading: heading
		)
	)
	
	(method (incClientPos param1)
		(client heading: param1)
		(if (client looper?)
			((client looper?) doit: client param1)
		else
			(DirLoop client param1)
		)
	)
	
	(method (canBeHere)
		(return
			(if (client canBeHere:)
				(cond 
					(outOfTouch)
					((<= 0 (client x?))
						(if (<= (client x?) 320)
							(if (<= 0 (client y?)) (<= (client y?) 200))
						)
					)
				)
			else
				0
			)
		)
	)
)
