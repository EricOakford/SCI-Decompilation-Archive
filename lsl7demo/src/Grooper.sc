;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	GROOPER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	This class is the Gradual Looper, which turns an actor from his current
;;;;	loop through all intermediate loops to his destination loop.
;;;;
;;;;	Classes:
;;;;		Grooper
;;;;		Grycler


(script#	GROOPER)
(include game.sh)
(use Main)
(use StopWalk)
(use Sight)
(use Motion)
(use System)


;;; GradualLooper Class
;;; Author J.Mark Hood 4/20/89
;;; Loops actors smoothly through normal loops
;;; on a direction change.
;;; Works for	4 loop or 8 loop actors
;;; Note: looper checks number of loops in clients view,
;;; so don't put	8 loops in the view and expect client to
;;; only use the first four.	
;;; Also note that Grooper uses AngleDiff from sight.sc.
;;; If you do not use sight.sc but wish to use the Grooper
;;; Compile Anglediff yourself or make use (extern AngleDiff SIGHT publicNum).
;;; 640 bytes alone or 590 + sight.
;;; Usage : (actor setLoop:GradualLooper)


;;; Changes to accomodate single stopwalk views (views in which the last loop
;;; 	contains stopped cels, in loop order):
;;;
;;;	1) when calculating lastDir (Grooper doit:), use ego's cel number instead
;;;		of his loop number, since his loop will always be the last one
;;;
;;;	2) if loop is already correct (Grycler init:), we set ego's loop from
;;;		the last one (stopped cels) to the appropriate walking loop before
;;;		invoking cycleDone.


(local
	trans1 =	[ 2 6	4 0 3	5 1 7]	; loop #s for E W S N SE SW NE NW
	trans2 = [ loopN	loopNE loopE loopSE loopS loopSW	loopW	loopNW ]
); Saves on heap consumption over using a switch

(class	GradualLooper of Code	
	
	(properties
		name			"Grooper"
		client	  	0
		oldCycler	0
		oldMover		0
		caller		0
	)
	
;;;	(methods
;;;		cue
;;;	)

	(method (doit c h whoCares dontGroop &tmp lastDir numLoops oldL newL cc)
		
		(if (not client)
			(= client c)
		)

		(= cc (client cycler?) )

		(= oldL (client loop?) ) ;-gtp for use below

		(if (>= argc 3)
			(= caller whoCares)
		)

		; Bail out if the client has his fixedLoop bit set
		(if (& (client signal?) fixedLoop)
			(if caller
				(caller cue:)
			)
			(= caller 0)
			(return)
		)

		(= numLoops	; for now only allow 4 or 8 loop actors
			(if (< (NumLoops client) 8)
				4
			else
				8
			)
		)
		(if (or 
				(not (cast contains: client))	; just go to loop if not in cast
				(and (>= argc 4) dontGroop)	; modification to help avoider out
			)
			(= newL
				[trans2 
					(*
						(if (== numLoops 4) 2 else 1)
						(/ 
							(umod (+ (client heading?) (/ 180 numLoops)) 360)
							(/ 360 numLoops)
						)
					)
				]
			)
;¯gtp¯
;¯gtp¯	code modified to update cycler
;¯gtp¯
			(if (!= newL oldL)
				(client setLoop: newL)
				(if cc  
					(cc clientLastCel: (client lastCel:))
				)
			)
			(if caller (caller cue:))	;PG
			(= caller 0)
			(return)
		)
		; finally, we need to groop
		(= lastDir ; instead of a switch
			(if (and	(== (client loop?) (- (NumLoops client) 1))
						cc
						(cc isKindOf: StopWalk)
						(== (cc vStopped?) SAMEVIEW)
					)
				[trans1 (client cel?)]
			else
				[trans1 (client loop?)]
			)
		)
		
		(if oldMover 
			(oldMover dispose:) 
			(= oldMover 0)
		)

		; could be Grycler
		(if (and 
				oldCycler 
				(or
					(oldCycler isMemberOf: GradualCycler)
					(not (cc isMemberOf: GradualCycler))
				)
			)
			(oldCycler dispose:)
			(= oldCycler 0)
		)
		(if (not oldCycler)
			(= oldCycler	cc )
		)

		(if (and cc	(cc isMemberOf: GradualCycler))
			(cc dispose:)
		)
		(= oldMover		(client mover?))
		(client 
			cycler:		0,
			mover:		0,
			setMotion:	0,
			setCycle:	GradualCycler self lastDir
		)
	); doit
	
	(method (cue &tmp oldCaller)
		(if (not (client mover?))
			(client mover:oldMover)	; setMotion would be recursive
		)
		(if oldCycler
 			(client 	cycler:oldCycler)
		)
		(= oldCaller caller)
		(= caller (= oldMover (= oldCycler	0)))
		(if oldCaller (oldCaller cue:&rest))
	); cue
	
	(method (dispose)
		(if oldCycler 
			(oldCycler dispose:)
			(= oldCycler NULL)
		)	
		(if oldMover 
			(oldMover dispose:)
			(= oldMover NULL)
		)
		(if client (client looper:0))
		(super dispose:)
	); dispose
	
); GradualLooper

(class 	GradualCycler of Cycle
	
	(properties
		name			"Grycler"
		loopIndex	0
		numOfLoops	0
	)
;;;	(methods 
;;;		loopIsCorrect
;;;	)
	(method (init act whoCares oldDir)
		(super 	init:act)
		(= caller whoCares)
		(= numOfLoops	; for now only allow 4 or 8 loop actors
			(if (< (NumLoops client) 8)
				4
			else
				8
			)
		)
		(= cycleDir 
			(- (sign (AngleDiff (* oldDir 45) (act	heading?))))
		)
		(= loopIndex oldDir)

		(if (self loopIsCorrect:)
			(if (and	(((client looper?) oldCycler?) isKindOf: StopWalk)
						(== (((client looper?) oldCycler?) vStopped?) SAMEVIEW)
					)
				(client setLoop: [trans2 loopIndex])
				(= clientLastCel (client lastCel:) )
			)
			(self cycleDone:)
		)
	); init
	
	(method (doit &tmp newLoop)
		(if (!= (= newLoop (self nextCel:)) (client loop?))
			(client setLoop: newLoop)
			(= clientLastCel (client lastCel:) )
	  		(if (self loopIsCorrect:)
	  			(self cycleDone:)
			)
		)
	);doit
	
	(method (cycleDone)
		(= completed TRUE)
		(self motionCue:)
	);cycleDone

	(method (loopIsCorrect)
		(return
			(<
				(Abs (AngleDiff (* loopIndex 45) (client	heading?)))
				(+ (/ 180 numOfLoops) 1) ; round off error
			)
		)
	);loopisCorrect

	(method (nextCel)
		;; Return client's next logical cel.
		;Increment the number of animation cycles since the client last cycled.
		(return
			(if (or (< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))(self loopIsCorrect:))
				;Not yet time to change the client's cel.
				(client loop?) 										;return value
			else
				(= cycleCnt gameTime)
				(+= loopIndex (* cycleDir (/ 8 numOfLoops)))
				(= loopIndex (umod loopIndex 8))
				[trans2 loopIndex]	;return value
			)
		)
	);nextCel

); GradualCycler
