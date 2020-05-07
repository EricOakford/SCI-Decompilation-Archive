;;; Sierra Script 1.0 - (do not remove this comment)
(script# 985)
(include game.sh)
(use System)


(class Avoider of Object
	(properties
		name "Avoid"
		client 0
		heading -1000
		bumpTurn 0
		lastBumped 0
		thisTurn 1
		escaping 0
		escapes 0
		escapeTurn 1
		nearestDist 32000
		counter 0
		nonBumps 10
		targetX 0
		targetY 0
		motionInited 0
		outOfTouch 0
		offScreenOK 0
	)
	
	(method (init theClient theOffScreenOK)
		(if (>= argc 1) (= client theClient))
		(if (>= argc 2) (= offScreenOK theOffScreenOK))
		(= heading (client heading?))
		(= counter 0)
		(= nonBumps 10)
		(= escaping 0)
	)
	
	(method (doit &tmp theHeading theNearestDist clientX clientY theTargetX theTargetY clientMover temp7 temp8 clientMoverB_moveCnt temp10 temp11 temp12 temp13)
		(cond 
			((not (= clientMover (client mover?))) (= heading -1000) (return))
			((clientMover onTarget:)
				(if motionInited else (InitBresen clientMover))
				(clientMover doit:)
				(return)
			)
			((== heading -1000) (self init:))
		)
		(= temp13 0)
		(= temp12 (<= nonBumps 3))
		(clientMover setTarget:)
		(= theTargetX (clientMover x?))
		(= theTargetY (clientMover y?))
		(= clientX (client x?))
		(= clientY (client y?))
		(= temp7
			(GetAngle clientX clientY theTargetX theTargetY)
		)
		(= theNearestDist
			(GetDistance clientX clientY theTargetX theTargetY)
		)
		(= theHeading
			(= heading (umod (* 45 (/ heading 45)) 360))
		)
		(cond 
			(
			(not (if escaping else (= temp12 (<= nonBumps 2))))
				(if (not motionInited)
					(= motionInited 1)
					(InitBresen clientMover)
				)
				(clientMover doit:)
				(if
				(or (!= clientX (client x?)) (!= clientY (client y?)))
					(self pickLoop: temp7)
					(++ nonBumps)
					(return)
				else
					(= nonBumps 0)
					(= temp13 1)
					(= motionInited 0)
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
					(!= targetX (= targetX theTargetX))
					(!= targetY (= targetY theTargetY))
					(and
						escaping
						(or
							(<= (-- counter) 0)
							(and (< theNearestDist nearestDist) (< counter 40))
						)
					)
				)
				(= motionInited (= escaping 0))
				(= nearestDist 32000)
				(= counter 0)
			)
			(escaping 1)
			((< theNearestDist nearestDist) (= nearestDist theNearestDist) (= counter 0))
			((= escaping (>= (++ counter) 50))
				(= counter (Random 40 80))
				(= escapeTurn (- escapeTurn))
			)
		)
		(cond 
			((<= (= temp7 (- temp7 theHeading)) -180) (= temp7 (+ temp7 360)))
			((> temp7 180) (= temp7 (- temp7 360)))
		)
		(= temp11 (<= (= temp8 (Abs temp7)) 90))
		(= thisTurn
			(if
				(cond 
					(escaping (sign temp7))
					(temp11 (sign temp7))
					(bumpTurn)
					(lastBumped)
					(else (sign temp7))
				)
			else
				escapeTurn
			)
		)
		(if (and (> temp8 22) (or 0 (not escaping)))
			(= heading (+ heading (* thisTurn 45)))
		)
		(if escaping (= thisTurn escapeTurn))
		(self incClientPos:)
		(if (self canBeHere:)
			(++ nonBumps)
			(= bumpTurn 0)
			(if (not escaping) (return))
		else
			(= lastBumped bumpTurn)
			(= bumpTurn thisTurn)
			(= nonBumps 0)
		)
		(if escaping
			(= theHeading (* 45 (/ heading 45)))
			(= temp10 0)
			(while (and (< temp10 8) (self canBeHere:))
				(= heading
					(umod (+ theHeading (* 45 temp10 escapeTurn)) 360)
				)
				(client x: clientX y: clientY)
				(self incClientPos:)
				(++ temp10)
			)
			(if (== temp10 8)
				(= heading (+ heading (* escapeTurn 45)))
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
			(= heading (- theHeading (* 45 temp10 thisTurn)))
			(self incClientPos:)
			(= temp10
				(cond 
					(escaping (++ clientMoverB_moveCnt))
					(temp13 (- (++ clientMoverB_moveCnt)))
					((> temp10 0) (- temp10))
					(else (++ clientMoverB_moveCnt))
				)
			)
		)
		(if (not (self canBeHere:)) (client findPosn:))
		(self pickLoop: heading)
	)
	
	(method (incClientPos)
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
	
	(method (pickLoop param1)
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
					(offScreenOK)
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
