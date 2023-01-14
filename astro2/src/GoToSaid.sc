;;; Sierra Script 1.0 - (do not remove this comment)
(script# GOTOSAID)
(include game.sh)
(use Main)
(use Intrface)
(use Approach)
(use Grooper)
(use Sight)
(use Avoider)
(use Motion)
(use User)
(use System)

;;;(procedure
;;;	GoToIfSaid
;;;	TurnIfSaid
;;;)

(public
	GoToIfSaid	0
	TurnIfSaid	1
)

(local
	turnToWhom
	goToWhom
	oldLooper
	oldAvoider
)

(enum
	NO_TRIGGER
	TRIGGER_AND_DOIT
	TRIGGER_AND_NOOP
)

(enum
	NOT_TURNING
	TURNING
)

(procedure (GoToIfSaid obj event theTargetOrX theDistanceOrY optSpec 
		;;optional final string arg to print if conds met but no control.
		&tmp motion returnValue)
	;;(Printf "GoToIfSaid %s?" (obj name?))
	(switch 
		(= returnValue
			(IfSaidScript init: 
				obj
				(if (and (>= argc 5) optSpec) optSpec else '*/*') 
				
				;;need to move?
				TRUE
				NOT_TURNING
			)
		)
		
		(TRIGGER_AND_DOIT
			;;(Printf "GoToIfSaid -> %d!" returnValue)
			(ego 
				setMotion: 
					(if (IsObject theTargetOrX) Approach else MoveTo)
					theTargetOrX theDistanceOrY 
					IfSaidScript,
				setAvoider: Avoider
			)
		)
		(TRIGGER_AND_NOOP
			(if (>= argc 6)
				(Print &rest)
			)
		)
	)
	(return returnValue)
); GoToIfSaid

(procedure (TurnIfSaid obj event optSpec &tmp theAngle)
	;;(Printf "TurnIfSaid %s?" (obj name?))
	(= theAngle (GetAngle (ego x?) (ego y?) (obj x?) (obj y?)))
	(if (== TRIGGER_AND_DOIT
			(IfSaidScript init: 
				obj 
				(if (>= argc  3) optSpec else '*/*')
				;;need to turn?'
				(CantBeSeen obj ego (/ 360 (Max 4 (* (/ (NumLoops ego) 4) 4))))
				TURNING
			)
		)
		;;(Printf "TurnIfSaid!")
		(if (IsObject oldLooper) (oldLooper dispose:))
		(= oldLooper (ego looper?))
		(ego 
			looper:		NULL,
			heading:		theAngle,
			setMotion:	NULL,
			setLoop:		GradualLooper
		)
		((ego looper?) doit:ego theAngle IfSaidScript)
		TRUE
		;;else
		;;	FALSE
	)
); TurnIfSaid


(instance IfSaidScript of Script
	(properties
		name "ISSc"
	)
	(method (init obj spec otherTest turning)
		
		(if (and 													;INIT STUFF
				otherTest	;should not be TRUE in second pass but MIGHT!
				(not (if turning turnToWhom else goToWhom))
				(Said spec)
			)
			(if (User canControl?)
				(if (IsObject oldAvoider) (oldAvoider dispose:))
				(= oldAvoider (ego avoider?))
				(ego	avoider:		NULL)
				(if turning 
					(= turnToWhom obj)
				else
					(= goToWhom obj)
				)
				(User 
					canControl:	FALSE,
					canInput:	FALSE
				)
				;;(theGame setCursor: waitCursor TRUE)
				TRIGGER_AND_DOIT
			else
				((User curEvent?) claimed:FALSE)
				TRIGGER_AND_NOOP
			)
			;;else NO_TRIGGER
		)
	)
	(method (cue &tmp evt)
		(User 
			canControl:	TRUE,		;both HAD to be TRUE to pass IfSaidScript init
			canInput:	TRUE		;so we make them TRUE again
		)
		;;(theGame setCursor:normalCursor (HaveMouse))
		((= evt (Event new:)) type:saidEvent)
		(Parse (User inputLineAddr?) evt)
		(ego setAvoider: oldAvoider)
		(= oldAvoider NULL)
		(if  turnToWhom
			((ego looper?) dispose:)
			(ego looper: oldLooper)
			(= oldLooper NULL)
			(turnToWhom handleEvent:evt)
			(= turnToWhom NULL) 
		else 
			(goToWhom handleEvent:evt)
			(= goToWhom NULL)
		)
		(if (not (evt claimed?))
			(regions	eachElementDo:#handleEvent:evt TRUE)
			(theGame	handleEvent:evt TRUE)
		)
		(evt dispose:)
	)
)
