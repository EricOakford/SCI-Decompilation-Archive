;;; Sierra Script 1.0 - (do not remove this comment)
(script# 985)
(include sci.sh)
(use Sight)
(use System)


(class Avoid of Obj
	(properties
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
	
	(method (doit &tmp theHeading theNearestDist clientX clientY theTargetX theTargetY clientMover temp7 temp8 clientMoverB_moveCnt temp10 temp11 temp12 temp13 temp14 temp15)
		(= temp15 (Max 4 (* (/ (NumLoops client) 4) 4)))
		(if (= clientMover (client mover?))
			(clientMover setTarget:)
		)
		(cond 
			((not clientMover) (= heading -1000) (return))
			(
				(not
					(if
						(self
							canBeHere:
								(= theTargetX (clientMover x?))
								(= theTargetY (clientMover y?))
						)
					else
						(clientMover respondsTo: #distance)
					)
				)
				(clientMover doit:)
				(return)
			)
			((clientMover onTarget:)
				(if motionInited else (InitBresen clientMover))
				(clientMover doit:)
				(return)
			)
			((== heading -1000) (self init:))
		)
		(= temp13 0)
		(= clientX (client x?))
		(= clientY (client y?))
		(= temp7
			(umod
				(GetAngle clientX clientY theTargetX theTargetY)
				360
			)
		)
		(= theNearestDist
			(GetDistance clientX clientY theTargetX theTargetY)
		)
		(= temp14 (/ 180 temp15))
		(= theHeading
			(= heading (umod (* temp14 (/ heading temp14)) 360))
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
				)
				(= nonBumps 0)
				(= temp13 1)
				(= motionInited 0)
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
			(escaping)
			((< theNearestDist nearestDist) (= nearestDist theNearestDist) (= counter 0))
			((= escaping (>= (++ counter) 50))
				(= counter (Random 40 80))
				(= escapeTurn (- escapeTurn))
			)
		)
		(= temp11
			(<=
				(= temp8 (Abs (= temp7 (AngleDiff temp7 theHeading))))
				90
			)
		)
		(= thisTurn (if (sign temp7) else escapeTurn))
		(if (and (> temp8 (/ temp14 2)) (not escaping))
			(= heading (+ heading (* thisTurn temp14)))
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
		(= temp15 (* temp15 2))
		(if escaping
			(= theHeading (* temp14 (/ heading temp14)))
			(= temp10 0)
			(while (and (< temp10 temp15) (self canBeHere:))
				(= heading
					(umod (+ theHeading (* temp14 temp10 escapeTurn)) 360)
				)
				(client x: clientX y: clientY)
				(self incClientPos:)
				(++ temp10)
			)
			(if (== temp10 temp15)
				(= heading (+ heading (* escapeTurn temp14)))
			)
			(= theHeading heading)
		)
		(= temp10 (= clientMoverB_moveCnt 1))
		(while
			(and
				(not (self canBeHere:))
				(< clientMoverB_moveCnt temp15)
			)
			(client x: clientX y: clientY)
			(= heading (- theHeading (* temp14 temp10 thisTurn)))
			(self incClientPos:)
			(= temp10
				(cond 
					(escaping (++ clientMoverB_moveCnt))
					((> temp10 0) (- temp10))
					(else (++ clientMoverB_moveCnt))
				)
			)
		)
		(self pickLoop: heading)
	)
	
	(method (incClientPos)
		(client
			x: (+
				(client x?)
				(* (sign (SinMult heading 100)) (client xStep?))
			)
			y: (-
				(client y?)
				(* (sign (CosMult heading 100)) (client yStep?))
			)
			heading: heading
		)
	)
	
	(method (pickLoop param1)
		(client heading: param1)
		(if (client looper?)
			((client looper?) doit: client param1 0 1)
		else
			(DirLoop client param1)
		)
	)
	
	(method (canBeHere param1 param2 &tmp clientX clientY temp2)
		(= clientX (client x?))
		(= clientY (client y?))
		(if argc (client x: param1 y: param2))
		(= temp2
			(if (not (client cantBeHere:))
				(if offScreenOK else (not (IsOffScreen client)))
			else
				0
			)
		)
		(client x: clientX y: clientY)
		(return temp2)
	)
)
