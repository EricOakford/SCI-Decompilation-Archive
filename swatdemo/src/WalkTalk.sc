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
		signal	0		;This will pick up cues
		caller	NULL  ;who to cue
		started	FALSE
		completed FALSE
		holdOnLastCel FALSE
		disposeWhenDone TRUE
		hideClient TRUE
	)

;;;	(methods
;;;		init
;;;		start
;;;		terminate
;;;		doneYet
;;;		robotDone
;;;		killRobot  ; kills robot, sets client's robot to 0.  Cues if asked.
;;;		showFrame  ; displays single frame of given robot. 
;;;		doRobot	  ; set up robot and play.
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
		(= started (= completed (= signal FALSE)))
		(return self)
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
		(= started (= completed (= signal FALSE) ) )
		(if client (client robot: 0) )
		(if (== autoRobot self) (= autoRobot 0) )
		(if caller (caller cue: -1) (= caller 0) )
		(super dispose:)
	)

	(method (doit &tmp cueValue)
		(if started
			(Robot RobotGetCue self)
			(if signal
				(= cueValue signal)
				(= signal 0)
				(if (== -1 cueValue)
					(self robotDone: disposeWhenDone)
				else
					(if caller (caller cue: cueValue) )
				)
			)
		)
	)

	(method (doneYet) 
		(return (Robot RobotWaiting) )
	)

	(method (doRobot which anX anY whoToCue who pri hLC mdl 
					&tmp theRobot myX myY myCaller myObj myPri myHold alaMode i)
		;
		;This method is a one-line way of firing up a robot
		;All parameters except the first are optional.
		;If this method is invoked of a Class, a dynamic instance is created
		;otherwise the object itself is used.
		;If "who" is NULL the robot will be an autoRobot if
		;modeless ("mdl" 0 or not passed) or free floating if modal.
		;

		;Examples:
		;(WalkieTalkie	doRobot: 123) ;modeless, client:ego
		;(WalkieTalkie doRobot: 123 0 0 NULL myAct) ;modeless, client:myAct
		;(WalkieTalkie doRobot: 123 0 0 aScript ego -1 FALSE TRUE)
												;modal, client:ego, cues aScript
		;(myRobot doRobot: 123) ;uses myRobot, not a clone



		; Set defaults
		(= myX 0)
		(= myY 0)
		(= myCaller	NULL)
		(= myObj	ego)
		(= myPri	-1)
		(= myHold FALSE)
		(= alaMode FALSE)

		; Process parameters
		(for ((= i 1)) (< i argc) ((++ i))
			(= [theRobot i] [which i])
		)

		; Init and start robot
		(= theRobot
			(if myObj
				(myObj setRobot: self TRUE which)
			else
				((if (& -info- CLASS) (self new:) else self)
					init: NULL which
				)
			)
		)
		(if (not (or alaMode myObj))
			(= autoRobot theRobot)
		)
		(theRobot
			caller:			myCaller,
			priority: 		myPri,
			holdOnLastCel: myHold,
			x:					myX,
			y:					myY,
			mode:				alaMode,
			start:
		)
	)

	(method (killRobot doICue who &tmp myObj theRobot)
		;
		;This method is designed to kill *any* robot, not
		;just self. In fact it would normally be invoked 
		;from the class.
		;The method first looks for a robot owned by "who".
		;If "who" is not passed it checks for an autoRobot.
		;If no robot is found it simply issues a RobotTerminate
		;(in case a showFrame was active)
		;

		(= myObj 
			(cond
				((> argc 1) who)
				(autoRobot NULL)
				(else ego)
			)
		)
		(= theRobot
			(if myObj (myObj robot?) else autoRobot)
		)
		(if theRobot
			(theRobot holdOnLastCel: FALSE)
			(if (not (and argc doICue)) (theRobot caller: NULL) )
			(if myObj
				(myObj setRobot: 0)
			else
				(theRobot dispose: TRUE)
			)
		else
			(Robot RobotTerminate)
		)
	)

	(method (robotDone doDispose)
		(= completed TRUE)
		(if (not holdOnLastCel)
			(Robot RobotTerminate)
		)
		(if (and hideClient client) (client show:) )
		(if (and argc doDispose) 
			(self dispose:) 
		else
			(if caller (caller cue: -1) (= caller 0) )
		)
	)

	(method (showFrame theNum theFrame theX theY pri whichPlane 
							&tmp showX showY thatOldPlane thatOldPri)
		;
		;This method displays a specified frame of a robot.
		;It is usually invoked of the class, as no object is needed.
		;The killRobot method (of the class) may be used to terminate.

		;Example:
		;(WalkieTalkie showFrame: 123 frameNum 0 0 -1 myPlane) ;displays
		;...
		;(WalkieTalkie killRobot:) ;closes robot
		; 

		(= showX
			(if (> argc 2) theX else 0)
		)

		(= showY
			(if (> argc 3) theY else 0)
		)

		(= thatOldPri
			(if (> argc 4) pri else -1)	
		)
		(= thatOldPlane 
			(if (> argc 5) whichPlane else thePlane)
		)
		(Robot RobotOpen theNum thatOldPlane thatOldPri showX showY)
		(Robot RobotDisplayFrame theFrame)
	)

	(method (start m)
		(if argc (= mode m) )
		(if (and hideClient client) 
			(if (client isNotHidden:)
				(client hide:)
 			else
				(= hideClient FALSE)
			)
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

)