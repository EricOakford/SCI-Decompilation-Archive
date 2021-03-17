;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	WALKTALK.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1994
;;;;
;;;;	Author: 	Greg Tomko-Pavia
;;;;  Updated: Chris Carr
;;;;
;;;;	The WalkieTalkie class allows an actor to use a 
;;;;	view stream with interleaved audio (robot).
;;;;
;;;;
;;;;	Classes:
;;;;		WalkieTalkie
;;;;		
;;;;		


(script# WALKTALK) ;64960
(include game.sh)
(use Main)
(use System)


(class WalkieTalkie of Object
	(properties
		robot		0
		x			0
		y			0
		priority -1
		plane		NULL
		mode		FALSE ;TRUE means modal (while loop lock)
		client	NULL	;who to hide and show, or NULL
		caller	NULL  ;who to cue
		started	FALSE
		completed FALSE
		disposeWhenDone TRUE
		hideClient TRUE
	)

;;;	(methods
;;;		init
;;;		start
;;;		terminate
;;;		doneYet
;;;		robotDone
;;;	)

	(method (init cl rb c)
		(if (and argc cl) (= client cl) )
		(if (> argc 1)
			(= robot rb)
			(if (> argc 2)	(= caller c) )
		)
		(if (not plane)
			(= plane (if client (client plane?) else thePlane) )
		)
		(= started (= completed FALSE))
	)

	(method (dispose doDone)
		(if (and
				started
				(not completed)
				argc
				doDone
			)
			(self robotDone:)
		)
		(= started (= completed FALSE) )
		(if client (client robot: 0) )
		(if (== autoRobot self) (= autoRobot 0) )
		(if caller (caller cue: robot) (= caller 0) )
		(super dispose:)
	)

	(method (doit)
		(if (and started (Robot RobotWaiting))
			(self robotDone: disposeWhenDone)
		)
	)

	(method (doneYet) 
		(return (Robot RobotWaiting) )
	)

	(method (start m)
		(if argc (= mode m) )
		(if (and hideClient client)
			(client hide:)
		)
		(Robot RobotOpen robot plane priority x y)
;¯gtp¯		(Robot RobotDisplayFrame 0)
		(Robot RobotPlay)
		(= started TRUE)
		(if mode
			(while (not (Robot RobotWaiting)) (FrameOut) )
			(self robotDone: disposeWhenDone)
		)
	)

	(method (terminate) ;just calls the kernel
		(Robot RobotTerminate)
	)
	
	(method (robotDone doDispose)
		(= completed TRUE)
		(Robot RobotTerminate)
		(if (and hideClient client) (client show:) )
		(if (and argc doDispose) 
			(self dispose:) 
		else
			(if caller (caller cue: robot) (= caller 0) )
		)
	)

)