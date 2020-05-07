;;; Sierra Script 1.0 - (do not remove this comment)
(script# AVOIDER)
(include game.sh)
(use Sight)
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
			x: (+ (client x?) (* (sign (SinMult heading 100)) (client xStep?))),
			y: (- (client y?) (* (sign (CosMult heading 100)) (client yStep?))),
			heading: heading
		)
		;(return (self canBeHere:))
		
	);incClientPos
	
	(method (pickLoop angle)
		(client heading: angle)
		(if (client looper?)
			((client looper?) doit: client angle NULL TRUE) ;just set loop
		else
			(DirLoop client angle)
		)
	)
	
	(method (canBeHere theX theY &tmp oldX oldY result)
		(= oldX (client x?))
		(= oldY (client y?))
		(if argc
			(client x:theX, y:theY)
		)
		(= result
			(and
				(client canBeHere:)
				(or offScreenOK (not (IsOffScreen client)))
			)
		)
		(client x:oldX, y:oldY)
		(return result)
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
			
			angleDelta		;45 for 4-loop, 22 for 8-loop
			numLoops			;usually 4 or 8
		)
		
		(= numLoops (Max 4 (* (/ (NumLoops client) 4) 4)))
		
		(if (= motion (client mover?))
			(motion setTarget:)
		)
		
		(cond
			((not motion)	;we have no purpose
				(= heading -1000)
				(return)
			)
			((not 
					(or
						(self canBeHere: (= mx (motion x?))(= my (motion y?)))
						(motion respondsTo: #distance)
					)
				)
				(motion doit:)
				(return)
				
			)
			((motion onTarget:)						;if finished
				(or motionInited 
					(InitBresen motion))
				(motion doit:)							;let motion do wrap-up
				(return)
			)
			((== heading -1000)						;we must re-initialize
				(self init:)
			)
		)
		
		;Init variables
		;**************
		
		(= firstBump FALSE)
		;;(= bumping (<= nonBumps 3))
		
		(= cx (client x?))							;remember where you started
		(= cy (client y?))
		
		(= ang (umod (GetAngle cx cy mx my) 360))	;client-to-target angle
		(= d (GetDistance cx cy mx my))
		
		(= angleDelta (/ 180 numLoops))
		(= h (= heading (umod (* angleDelta (/ heading angleDelta)) 360)))
		
		
		(cond
			((not (or escaping (= bumping (<= nonBumps 2))))	;straight shot?
				
				;;IF NOT CURRENTLY ESCAPING OR BUMPING...
				
				;Try for a straight shot
				;***********************
		
				(if (not motionInited)
					(= motionInited TRUE)
					(InitBresen motion)
				)
				(motion doit:)
				
				
				(if (or (!= cx (client x?)) (!= cy (client y?)))	;we moved
					(self pickLoop: ang)
					(++ nonBumps)
					(return)
				)
				;;else...
				(= nonBumps 0)
				(= firstBump TRUE)
				(= motionInited FALSE)				;since we will take control
			)
			
			((<	(= i (motion b-moveCnt?))	(client moveSpeed?))
				;Skip a move
				;***********
				(motion b-moveCnt: (++ i))
				(return)
			)
			(else
				;We are about to move so reset moveCnt if we made it this far
				(motion b-moveCnt: 0)
			)
		);cond
		
		;ESCAPE-mode toggling & bookkeeping
		;**********************************
		
		(cond
			(	;;TURN ESCAPE OFF IF TARGET HAS CHANGED
				;;OR WE'VE "ESCAPED" LONG ENOUGH
				(or
					(!= targetX (= targetX mx))	;new target
					(!= targetY (= targetY my))
					(and escaping
						(or
							(<= (-- counter) 0) 		;end of escape countdown
							(and
								(< d nearestDist)			;escaping paid off
								(< counter escapeMin)
							)
						)
					)
				)
				(= escaping FALSE)
				(= motionInited FALSE)
				(= nearestDist 32000)
				;(client view: 1)
				(= counter 0)
			)
			
			(escaping		;DO NOTHING, KEEP ESCAPING
			)
			
			((< d nearestDist)
				
				;;WE GOT CLOSER, not escaping, ZERO THE "NO PROGRESS" COUNTER
				
				(= nearestDist d)
				(= counter 0)
			)
			((= escaping (>= (++ counter) noProgressMax))
				
				;;WE'VE GONE TOO LONG WITHOUT GETTING ANY CLOSER
				;;turn escape ON!
				
				(=  counter												;how long to escape
					;escapeMax
					(Random escapeMin escapeMax)
				)
				;(client view: 2)
				(= escapeTurn (- escapeTurn))						;try opposite turn
			)
		);cond
		
		
		
		(= absDiff (Abs (= ang (AngleDiff ang h))))

		(= facing (<= absDiff 90))						;can we "see" target?

		(= thisTurn 										;how to turn towards target
			(or 
				;;(cond
				;;	(escaping		(sign ang))
				;;	(facing			(sign ang))	
				;;	(bumpTurn 		bumpTurn)			;avoid obstacle
				;;	(lastBumped		lastBumped)			;or towards last obstacle
				;;	(else				(sign ang))	
				;;)
				(sign ang)
				escapeTurn									;because zero doesn't cut it
			)
		)
		
		
		(if (and	(> absDiff (/ angleDelta 2))
				;(not bumping)
				;;(or 
					(not escaping)
				;;	(== escapeTurn thisTurn)
				;;)
			)
			(+= heading (* thisTurn angleDelta))			;if it helps, turn to target
		)
		
		(if escaping (= thisTurn escapeTurn))	;escape single-mindedly
		
		(self incClientPos:)							;try to move
		
		;if and which way we bumped
		(cond
			((self canBeHere:) 					;no bump
				(++ nonBumps)
				(= bumpTurn 0)
				(if (not escaping) 
					(return)
				)
			)
			(else	
				(= lastBumped bumpTurn)			;save one-bump history
				(= bumpTurn thisTurn)			;we bumped the way we turned
				(= nonBumps 0)
			)
		)
		
		
		
		;;start using differently, we will try twice as many angles of 
		;;movement as there are loops
		(*= numLoops 2)	
		
		
		(cond
			(escaping
				;OBSTACLE-HUGGING LOOP
				;*********************
				(= h (* angleDelta (/ heading angleDelta)))	;start with current heading
				(for
					(	(= j 0)
					)
					(and	(< j numLoops)	(self canBeHere:))
					(	(++ j)
					)
					
					(= heading 
						(umod (+ h (* angleDelta j escapeTurn))
							360))									;turn a bit
					(client x: cx,	y: cy)					;restore client's position
					(self incClientPos:)						;try to move
					
				)
				(if (== j numLoops)	
					(+= heading (* escapeTurn angleDelta))		;stay with obstacle
				)
				(= h heading)									;keep bum heading
			)	
			
		);cond
		
		
		;HEADING-SEEKING LOOP
		;********************
		
		(for 
			(	(= j (= i 1))
			)
			(and
				(not (self canBeHere:))				;until a move is possible
				(< i numLoops)							;no infinite loops please
			)
			(	(= j 		
					(cond
						(escaping	(++ i))			;non-fanning escape
						;(firstBump	(- (++ i)))		;non-fanning non-stray
						((> j 0) 	(- j) )			;fanning iterator j: +1,-1,+2,-2,...
						(else			(++ i))
					)
				)
			)
			
			(client x: cx,	y: cy)								;restore client's position
			(= heading (- h (* angleDelta j thisTurn)))	;back off angle a bit
			(self incClientPos:)									;try to move
		);for
		
		
		
		;CLEANUP
		;*******
		
		;;(if (not (self canBeHere:))
		;;	(client findPosn:)
		;;)
		(self pickLoop: heading)
		
	);doit
	
)
