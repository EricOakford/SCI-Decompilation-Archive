;;; Sierra Script 1.0 - (do not remove this comment)
(script# SMOOPER)
(include game.sh)
(use Motion)
(use System)

(public
	SmoothLooper	0
)

;;; SmoothLooper Class
;;; Author J.Mark Hood 3/27/89
;;; Update 6/26/89 queue's any new setMotion request.
;;; loops ego smoothly through transition cels ( vChangeDir)
;;; on a direction change
;;; Usage : set property vChangeDir to view containing transition loops
;;; in the order listed in enum below	and set clients looper property
;;; i.e. 
;;;	(instance mySmoothLooper of SmoothLooper
;;; 		(properties
;;;				vChangeDir		myTransitionLoops
;;;		)
;;;	)
;;;	(Actor looper:mySmoothLooper)


(enum
	lEastToSouth	
	lWestToSouth	
	lSouthToEast
	lSouthToWest
	lNorthToEast	
	lNorthToWest	
	lEastToNorth
	lWestToNorth
)

(define TwoStep	16)

;;;(define HEADINGNORTH 	(or (<= theHeading 45	) (> theHeading 315)))
;;;(define HEADINGEAST 	(<= theHeading 135	))
;;;(define HEADINGSOUTH 	(<= theHeading 225	))
;;;(define HEADINGWEST 	(<= theHeading 315	))

(class SmoothLooper of Code
	(properties
		nextLoop	 		0
		client			0
		oldCycler		0
		oldMover			0
		newMover			0
		oldCycleSpeed	0
		inProgress		0
		vNormal			0
		vChangeDir		0
	)

;;;	(methods
;;;		cue
;;;	)

	(method (doit cl theHeading &tmp theLoop changedTheLoop)
		(if (& (cl signal?) fixedLoop) (return))
		(= changedTheLoop FALSE)
		(if inProgress
			(if newMover 	(newMover dispose:))
			(= newMover 	(cl mover?))
			(cl mover:0)
			(return)
		else
			(if (not vNormal)
				(= vNormal (cl view?))
			)
			(= client 	cl)
			(= inProgress	TRUE)
		)
		(if (and 
				(>  (client loop?)	3) 
				(== (client view?) vNormal)
			) ;abnormal loops
			;;ie hero's quest cat
			(if inProgress
				(if (IsObject oldMover) (oldMover dispose:))
			else
				(client 
					view:vNormal
				)
				(DirLoop client theHeading)
			)
		)
		(= theLoop (client loop?))
		(switch theLoop  ; clients Loop
			;NOTE: SQ1-specific changes
			(facingNorth
				(cond 
					((and (<= 271 theHeading) (<= theHeading 359)))
					((and (<= 0 theHeading) (<= theHeading 90))
						(= theLoop lNorthToEast)
						(= nextLoop 0)
						(= changedTheLoop TRUE)
					)
					((and (<= 91 theHeading) (<= theHeading 180))
						(= theLoop 4)
						(= nextLoop 16)
						(= changedTheLoop TRUE)
					)
					((and (<= 181 theHeading) (<= theHeading 270))
						(= theLoop 5)
						(= nextLoop facingWest)
						(= changedTheLoop TRUE)
					)
				)
			)
			(facingEast
				(cond 
					((and (<= 271 theHeading) (<= theHeading 359))
						(= theLoop 6)
						(= nextLoop 3)
						(= changedTheLoop TRUE)
					)
					((and (<= 0 theHeading) (<= theHeading 90)))
					((and (<= 91 theHeading) (<= theHeading 180))
						(= theLoop 0)
						(= nextLoop 2)
						(= changedTheLoop TRUE)
					)
					((and (<= 181 theHeading) (<= theHeading 270))
						(= theLoop 6)
						(= nextLoop 21)
						(= changedTheLoop TRUE)
					)
				)
			)
			(facingWest
				(cond 
					((and (<= 271 theHeading) (<= theHeading 359))
						(= theLoop 7)
						(= nextLoop 3)
						(= changedTheLoop TRUE)
					)
					((and (<= 0 theHeading) (<= theHeading 90))
						(= theLoop 1)
						(= nextLoop 18)
						(= changedTheLoop TRUE)
					)
					((and (<= 91 theHeading) (<= theHeading 180))
						(= theLoop 1)
						(= nextLoop 2)
						(= changedTheLoop TRUE)
					)
					((and (<= 181 theHeading) (<= theHeading 270)))
				)
			)
			(facingSouth
				(cond 
					((and (<= 271 theHeading) (<= theHeading 359))
						(= theLoop 3)
						(= nextLoop 23)
						(= changedTheLoop TRUE)
					)
					((and (<= 0 theHeading) (<= theHeading 90))
						(= theLoop 2)
						(= nextLoop 0)
						(= changedTheLoop TRUE)
					)
					((and (<= 91 theHeading) (<= theHeading 180)))
					((and (<= 181 theHeading) (<= theHeading 270))
						(= theLoop 3)
						(= changedTheLoop TRUE)
						(= nextLoop 1)
					)
				)
			)
		)
		(if changedTheLoop
			(= oldCycler 		(client cycler?))
			(= oldMover 		(client mover?))
			(= oldCycleSpeed 	(client cycleSpeed?))
			(client 
				view:			vChangeDir,
				cycleSpeed:	0,
				mover:		0,
				cycler:		0,
				loop:			theLoop,
				cel:			0,
				setCycle:	EndLoop self
			)
		else
			(= inProgress FALSE)
		)
	); doit

	(method (cue &tmp om oc)
		(if (< nextLoop 15) ; single step transition
			(client 
				view:			vNormal,
				loop:			nextLoop,
				mover:		oldMover,			; setMotion would be recursive
				cycler:		oldCycler,
				cycleSpeed:	oldCycleSpeed
			)
			(= oldMover 	0)
			(= oldCycler 	0)
			(= inProgress FALSE)
			(if newMover
				(client setMotion:newMover)
				(= newMover 0)
			)
		else					  ; two step transition
			(-= nextLoop 16)
			(client
				loop:			nextLoop,
				cel:			0,
				setCycle:	EndLoop self
			)
			(= nextLoop
				(switch nextLoop
					(lEastToSouth
						facingSouth
					)
					(lNorthToWest
						facingWest
					)
					(lSouthToEast
						facingEast
					)
					(lWestToNorth
						facingNorth
					)
				)
			)
		); if
	);cue

	(method (dispose)
		(if oldMover	(oldMover 	dispose:))
		(if newMover	(newMover 	dispose:))
		(if oldCycler	(oldCycler	dispose:))
		(client			view:vNormal, looper:0)
		(DirLoop client (client heading?))
		(super 			dispose:)
	); dispose

); SmoothLooper
