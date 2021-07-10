;;; Sierra Script 1.0 - (do not remove this comment)
(script# GOTOSAID)
(include game.sh)
(use Main)
(use Avoider)
(use Sight)
(use Grooper)
(use Chase)
(use Motion)
(use User)
(use System)

;;;(procedure
;;;	GoToIfSaid
;;;	TurnIfSaid
;;;	CommonInit
;;;	CommonCue
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

(procedure (GoToIfSaid obj event theTargetOrX theDistanceOrY optSpec 
		&tmp motion)
	;;(Print "GoToIfSaid?")
	(return
		(if (CommonInit 
				obj
				(if (>= argc 5) optSpec else '*/*') 
				
				;;need to move?
				(or 
					(not (= motion (ego mover?)))
					(motion onTarget?)
				)
				FALSE
			)
			;;(Print "GoToIfSaid!")
			(ego 
				setAvoider: Avoider,
				setMotion: 
				(if (IsObject theTargetOrX) cueingChase else cueingMoveTo)
				theTargetOrX theDistanceOrY
			)
			TRUE
		;else FALSE
		)
	)
); GoToIfSaid

(procedure (TurnIfSaid obj event optSpec &tmp theAngle)
	;;(Print "TurnIfSaid?")
	(= theAngle (GetAngle (ego x?) (ego y?) (obj x?) (obj y?)))
	(return
		(if (CommonInit
				obj 
				(if (>= argc  3) optSpec else '*/*')
				;;need to turn?'
				(CantBeSeen obj ego (/ 360 (Max 4 (* (/ (NumLoops ego) 4) 4))))
				TRUE
			)
			;;(Print "TurnIfSaid!")
			(if (IsObject oldLooper) (oldLooper dispose:))
			(= oldLooper (ego looper?))
			(ego 
				looper:		NULL,
				heading:		theAngle,
				setMotion:	NULL,
				setLoop:		lookGrooper
			)
			(lookGrooper doit:ego theAngle)
			TRUE
			;;else FALSE
		)
	)
); TurnIfSaid

(procedure (CommonInit obj spec otherTest turning)
	
	;;(Print "CommonInit")
	(return
		(if (and 													;INIT STUFF
				otherTest	;should not be TRUE in second pass but MIGHT!
				(not (if turning turnToWhom else goToWhom))
				(Said spec)
			)
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
			TRUE
			;;else FALSE
		)
	)
)

(instance lookGrooper of GradualLooper
	(method (cue)
		(super cue:)
		(self dispose:)
		(CommonCue TRUE)
	)
)

(instance cueingChase of Chase
	(method (motionCue)
		(super motionCue:)
		(if completed (CommonCue FALSE))
	)
)
(instance cueingMoveTo of MoveTo
	(method (motionCue)
		(super motionCue:)
		(if completed (CommonCue FALSE))
	)
)

(procedure (CommonCue turning &tmp evt)
	(User 
		canControl:	TRUE,		;both HAD to be TRUE to pass CommonInit
		canInput:	TRUE		;so we make them TRUE again
	)
	;;(theGame setCursor:normalCursor (HaveMouse))
	((= evt (Event new:)) type:saidEvent)
	(Parse (User inputLineAddr?) evt)
	(ego setAvoider: oldAvoider)
	(= oldAvoider NULL)
	(if turning 
		(ego looper: oldLooper)
		(= oldLooper NULL)
		(turnToWhom handleEvent:evt)
		(= turnToWhom NULL) 
	else 
		(goToWhom handleEvent:evt)
		(= goToWhom NULL)
	)
	(evt dispose:)
)


