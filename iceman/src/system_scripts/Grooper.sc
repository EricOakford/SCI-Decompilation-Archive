;;; Sierra Script 1.0 - (do not remove this comment)
(script# GROOPER)
(include game.sh)
(use Intrface)
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

(local
	trans1 =	[ 2 6	4 0 3	5 1 7]
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

	(procedure (FindTrans1 ind)
		(return [trans1 ind])	; needed to keep scope in this module
	)
	(procedure (FindTrans2 ind)
		(return [trans2 ind])	; needed to keep scope in this module
	)

	(method (doit c h whoCares dontGroop &tmp lastDir numLoops)
		(if (not (IsObject c)) (Print "NOA"))
		(if (not client) (= client c))
		(if (& (client signal?) fixedLoop) (return))
		(if (>= argc 3) (= caller whoCares))
		(if (and (>= argc 4) dontGroop)	; modification to help avoider out
			(client 							; just go to proper loop without
				loop:(FindTrans2 (/ (client heading?) (/ 360 (NumLoops client))))
			)									; gradual turn (i.e. an 8 loop DirLoop)
			(return)
		)
		(= lastDir ; instead of a switch
			(FindTrans1 (client loop?))
		)
		(= numLoops	; for now only allow 4 or 8 loop actors
			(if (< (NumLoops client) 8)
				4
			else
				8
			)
		)
		(if oldMover 
			(oldMover dispose:) 
			(= oldMover 0)
		)
		(if (< (Abs (- (* 45 lastDir)	h)) (+ (/ 180 numLoops) 1)) 	
			(return)	; don't need to loop
		)
		(if (not oldCycler) ; could be Grycler
			(= oldCycler	(client cycler?))
		)
		(if (client cycler?)
			(if ((client cycler?) isMemberOf:GradualCycler)
				((client cycler?) dispose:)
			)
		)
		(= oldMover		(client mover?))
		(client 
			cycler:		0,
			mover:		0,
			setMotion:	0,
			setCycle:	GradualCycler self lastDir
		)	
	); doit
	
	(method (cue)
		((client cycler?) dispose:)
		(if (not (IsObject (client mover?)))
			(client mover:oldMover)	; setMotion would be recursive
		)
		(client 	
			cycler:		oldCycler
		)
		(= oldMover (= oldCycler	0))
		(if caller (caller cue: &rest))
	); cue
	
	(method (dispose)
		(if (IsObject oldCycler) 	(oldCycler dispose:	))	
		(if (IsObject oldMover) 	(oldMover dispose:	))	
		(client looper:0)
		(super dispose:)
	); dispose
	
); GradualLooper

(class 	GradualCycler of Cycle
	
	(properties
		name			"Grycler"
		loopIndex	0
		numOfLoops	0
	)
	(method (init act whoCares oldDir)
		(super 	init:act)
		(= caller whoCares)
		(= numOfLoops (NumLoops client))
		(= cycleDir 
			(- (sign (AngleDiff (* oldDir 45) (act	heading?))))
		)
		(= loopIndex oldDir)
	); init
	
	(method (doit)
		(if 	
			(>= 
				(Abs (AngleDiff (* loopIndex 45) (client	heading?)))
				(+ (/ 180 numOfLoops) 1) ; round off error
			)
			(client loop:(self nextCel:))	; sorry but this class is a hybrid
		else
			(self cycleDone:)
		)
	);doit
	
	(method (cycleDone)
		(if caller (caller cue:))
	);cycleDone
	
	(method (nextCel)
		;; Return client's next logical cel.
		;Increment the number of animation cycles since the client last cycled.
		(++ cycleCnt)
		
		(cond
			((<= cycleCnt (client cycleSpeed?))
				;Not yet time to change the client's cel.
				(return
					(client loop?)
				)
			)
			(else
				;Reset counter.
				(= cycleCnt 0)
				(+= loopIndex (* cycleDir (/ 8 numOfLoops)))
				(= loopIndex (umod loopIndex 8))
				(return (FindTrans2 loopIndex)) ; instead of switch
			)
		); cond
	);nextCel
); GradualCycler
