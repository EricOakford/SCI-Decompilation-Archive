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

;header file is implicitly included through system.sh
;(include "extra.sh")


(enum
	waitAsec
	startAction
	hesitate
	continueAction
	endAction
)

;;;(procedure
;;;	InitialCel
;;;)



(class Extra of Prop
	;;; The behavior of an Extra is to wait for a random interval of time,
	;;; cycle for a while, then wait for another random period before doing
	;;; it over again.
	;;;
	;;; Pablo's extensions:
	;;; The above is true if cycleType is ExtraForward. If it is ExtraEndLoop 
	;;; then the pauseCel is a cel where we pause for "hesitation" cycles. If 
	;;; it is ExtraEndAndBeginLoop then the pause is between the end and begin 
	;;; loops.
	
	(properties
		cycleSpeed	1
		cycleType	0			;0=Forward, 1=EndLoop, 2=EndLoop+BegLoop
		hesitation	0			;if above is 2, delay between loops
		pauseCel		0			;cel to pause on; -1 for random cel, -2 for last cel
		minPause		10			;cycles of no action
		maxPause		30
		minCycles	8 			;cycles of action if cycleType=ExtraForward
		maxCycles	20			;else number of waves in a wave train
		
		counter		0			;private -- counter for waves left
		
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
		;; See if it's time to change states. Detect transition in case we're
		;; relying on different cue'ing mechanism
		
		
		;For EndLoops, pauseCel is a cel to pause on for a while.
		(if (and (== cycleType ExtraEndLoop) (== cel pauseCel) (!= pauseCel 0))
			(= cycles (+ hesitation 1))
		)
		
		;transition detection
		(if (and cycles (not (-- cycles)))
			(self cue:)
		)
		(super doit:)
	)
	
	
	(method (stopExtra)
		;; stopUpd: the Extra now, setting it to its initial cel.
		
		(self 
			setCel: (InitialCel), 
			stopUpd:
		)
	)
	
	
	(method (startExtra)
		;; Start the Extra up again.
		
		(self changeState: startAction)
	)
	
	
	(method (cue)
		(or
			(& signal (| notUpd stopUpdOn))
			(self changeState: (+ state 1))
		)
	)
	
	
	(method (changeState newState)
		;; This is a small script which runs the Extra.
		
		(switch (= state newState)
			(waitAsec
				;Wait for a while before animating another set of waves.
				(if (== counter 0)
					(= cycles (Random minPause maxPause))
					(if (!= cycleType ExtraForward)
						(= counter (- (Random minCycles maxCycles) 1))
					)
				else
					(-- counter)
					(self cue:)
				)
			)
			(startAction
				;Start animation for a random period of time.
				(if (== cycleType ExtraForward)
					(self setCycle:Forward) 
					(= cycles (Random minCycles maxCycles))
				else 
					(self setCycle:EndLoop self)
				)
			)
			(hesitate
				(if (== cycleType ExtraEndAndBeginLoop)
					(= cycles hesitation)
				else
					(self cue:)
				)
			)
			(continueAction
				(if (== cycleType ExtraEndAndBeginLoop)
					(self setCycle:BegLoop self)
				else
					(self cue:)
				)
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
				(ExtraRandomCel
					(Random 0 (self lastCel:))
				)
				(ExtraLastCel
					(self lastCel:)
				)
				((== cycleType ExtraForward)
					pauseCel
				)
				;(else 0)
			)
		)
	)
)
