;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	EXTRA.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	The Extra class (initially developed by Al Lowe) modified and expanded
;;;;	to have extras add themselves to the picture if animation speed is too
;;;;	slow to support them.
;;;;
;;;;	Classes:
;;;;		Extra


(script#	EXTRA)
(include game.sh)
(use Motion)
(use Actor)


(enum
	waitAsec
	startAction
	endAction
)

;;;(procedure
;;;	InitialCel
;;;)



(class Extra of Prop
	;;; The behavior of an Extra is to wait for a random interval of time,
	;;; cycle for a while, then wait for another random period before doing
	;;; it over again.

	(properties
		cycleSpeed	1
		pauseCel		0			;cel to pause on; -1 for random cel, -2 for last cel
		minPause		10			;cycles of no action
		maxPause		30
		minCycles	8 			;cycles of action
		maxCycles	20			

		;'Multiple inheritance' from Script.
		state			-1			;private -- current state of the Extra
		cycles		0			;private -- cycles left before changing state
	)

;;;	(methods
;;;		stopExtra				;stopUpd: this extra
;;;		startExtra				;startUpd: this extra
;;;		changeState				;private -- used to control Extra's action
;;;	)


	(method (init)
		;; Set the Extra to its initial cel and put it in the initial wait state.

		(= cel (InitialCel))
		(self changeState: waitAsec)
		(super init:)
	)


	(method (doit)
		;; See if it's time to change states.

		(if (not (-- cycles))
			(self cue:)
		)
		(super doit:)
	)


	(method (stopExtra)
		;; stopUpd: the Extra now, setting it to its initial cel.

		(self setCel: (InitialCel), stopUpd:)
	)


	(method (startExtra)
		;; Start the Extra up again.

		(self changeState: startAction)
	)


	(method (cue)
		(self changeState: (+ state 1))
	)


	(method (changeState newState)
		;; This is a small script which runs the Extra.

		(switch (= state newState)
			(waitAsec
				;Wait for a while before animating.
				(= cycles (Random minPause maxPause))
			)
			(startAction
				;Start animation for a random period of time.
				(self setCycle: Forward)
				(= cycles (Random minCycles maxCycles))
			)
			(endAction
				;We're done animating -- set the Extra back to its initial cel
				;and wait before doing it over again.
				(self setCel: (InitialCel))
				(self changeState: waitAsec)
			)
		)
	)


	(procedure (InitialCel)
		;; Return the initial cel of this Extra based on pauseCel
		;;		pauseCel =	-1		random cel
		;;					=	-2		last cel of loop
		;;					else		use the value of pauseCel

		(return
			(switch pauseCel
				(-1
					(Random 0 (self lastCel:))
				)
				(-2
					(self lastCel:)
				)
				(else
					pauseCel
				)
			)
		)
	)
)