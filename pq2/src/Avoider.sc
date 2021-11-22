;;; Sierra Script 1.0 - (do not remove this comment)
(script# AVOIDER)
(include game.sh)
(use System)


(define	escapeMax		80)
(define	escapeMin		40)
(define	noProgressMax	50)

(class Avoider kindof Object
	;;; Normally, when an Actor encounters a control line or other object
	;;; which blocks its normal motion, it simply stops right there.
	;;; If, however, an Avoider is attached to such an Actor, it will take
	;;; over and attempt to direct the Actor around whatever obstruction
	;;; it has encountered.
	
;;;	(methods
;;;		incClientPos
;;;		pickLoop
;;;		canBeHere
;;;	)
	
	(properties
		name				"Avoid"
		client			0
		heading			-1000
		
		bumpTurn			0
		lastBumped		0
		thisTurn			1
		escaping			FALSE
		escapes			0
		escapeTurn		1
		nearestDist		32000
		counter			0
		nonBumps			10
		targetX			0
		targetY			0
		motionInited	FALSE
		outOfTouch		FALSE
		offScreenOK		FALSE
	)
	
	(method (init aClient allowOff)
		(if (>= argc 1)	(= client aClient))
		(if (>= argc 2)	(= offScreenOK allowOff))
		
		(= heading (client heading?)) 		;in sync with client
		(= counter 0)
		(= nonBumps 10)
		(= escaping FALSE)
		;(client view:1)
	)

	(method (incClientPos)
		
		(client
			x: (+ (client x?) (* (sign (TimesSin heading 100)) (client xStep?))),
			y: (- (client y?) (* (sign (TimesCos heading 100)) (client yStep?))),
			heading: heading
		)
		;(return (self canBeHere:))
		
	);incClientPos

	
	(method (pickLoop angle)
		(client heading: angle)
		(if (client looper?)
			((client looper?) doit: client angle) ;just set loop
		else
			(DirLoop client angle)
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

	
	(method (doit 
			&tmp
			h					;initial heading
			d					;distance to target
			cx		cy			;client x/y
			mx		my			;motion x/y
			motion			;client's mover
			ang				;angle to target, then deviation from heading
			absDiff			;absolute angular diff in -180/+180
			i	j				;loop variables
			facing			;is client facing target?
			bumping			;is client having a hard time?
			firstBump
		)
		
		(cond 
			((not (= motion (client mover?))) (= heading -1000) (return))
			((motion onTarget:)
				(if motionInited else (InitBresen motion))
				(motion doit:)
				(return)
			)
			((== heading -1000) (self init:))
		)
		(= firstBump FALSE)
		(= bumping (<= nonBumps 3))
		(motion setTarget:)
		(= mx (motion x?))
		(= my (motion y?))
		(= cx (client x?))
		(= cy (client y?))
		(= ang
			(GetAngle cx cy mx my)
		)
		(= d
			(GetDistance cx cy mx my)
		)
		(= h
			(= heading (umod (* 45 (/ heading 45)) 360))
		)
		(cond 
			(
			(not (if escaping else (= bumping (<= nonBumps 2))))
				(if (not motionInited)
					(= motionInited TRUE)
					(InitBresen motion)
				)
				(motion doit:)
				(if
				(or (!= cx (client x?)) (!= cy (client y?)))
					(self pickLoop: ang)
					(++ nonBumps)
					(return)
				else
					(= nonBumps 0)
					(= firstBump TRUE)
					(= motionInited FALSE)
				)
			)
			(
				(<
					(= i (motion b-moveCnt?))
					(client moveSpeed?)
				)
				(motion b-moveCnt: (++ i))
				(return)
			)
			(else (motion b-moveCnt: 0))
		)
		(cond 
			(
				(or
					(!= targetX (= targetX mx))
					(!= targetY (= targetY my))
					(and
						escaping
						(or
							(<= (-- counter) 0)
							(and (< d nearestDist) (< counter 40))
						)
					)
				)
				(= motionInited (= escaping 0))
				(= nearestDist 32000)
				(= counter 0)
			)
			(escaping 1)
			((< d nearestDist) (= nearestDist d) (= counter 0))
			((= escaping (>= (++ counter) 50))
				(= counter (Random 40 80))
				(= escapeTurn (- escapeTurn))
			)
		)
		(cond 
			((<= (= ang (- ang h)) -180) (= ang (+ ang 360)))
			((> ang 180) (= ang (- ang 360)))
		)
		(= facing (<= (= absDiff (Abs ang)) 90))
		(= thisTurn
			(if
				(cond 
					(escaping (sign ang))
					(facing (sign ang))
					(bumpTurn)
					(lastBumped)
					(else (sign ang))
				)
			else
				escapeTurn
			)
		)
		(if (and (> absDiff 22) (or 0 (not escaping)))
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
			(= h (* 45 (/ heading 45)))
			(= j 0)
			(while (and (< j 8) (self canBeHere:))
				(= heading
					(umod (+ h (* 45 j escapeTurn)) 360)
				)
				(client x: cx y: cy)
				(self incClientPos:)
				(++ j)
			)
			(if (== j 8)
				(= heading (+ heading (* escapeTurn 45)))
			)
			(= h heading)
		)
		(= j (= i 1))
		(while
			(and
				(not (self canBeHere:))
				(< i 8)
			)
			(client x: cx y: cy)
			(= heading (- h (* 45 j thisTurn)))
			(self incClientPos:)
			(= j
				(cond 
					(escaping (++ i))
					(firstBump (- (++ i)))
					((> j 0) (- j))
					(else (++ i))
				)
			)
		)
		(if (not (self canBeHere:)) (client findPosn:))
		(self pickLoop: heading)
	)
)