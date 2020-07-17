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

(public
	GoToIfSaid 0
	TurnIfSaid 1
)

(local
	turnToWhom
	goToWhom
	oldLooper
	oldAvoider
)
(procedure (GoToIfSaid obj event theTargetOrX theDistanceOrY optSpec &tmp motion ret)
	(switch
		(= ret
			(ISSc
				init: obj (if (and (>= argc 5) optSpec) optSpec else '*/*') 1 0
			)
		)
		(1
			(ego
				setMotion:
					(if (IsObject theTargetOrX) Approach else MoveTo)
					theTargetOrX
					theDistanceOrY
					ISSc
				setAvoider: Avoider
			)
		)
		(2
			(if (>= argc 6) (Print &rest))
		)
	)
	(return ret)
)

(procedure (TurnIfSaid obj event optSpec &tmp theAngle)
	(= theAngle
		(GetAngle (ego x?) (ego y?) (obj x?) (obj y?))
	)
	(return
		(if
			(==
				1
				(ISSc
					init:
						obj
						(if (>= argc 3) optSpec else '*/*')
						(CantBeSeen
							obj
							ego
							(/ 360 (Max 4 (* (/ (NumLoops ego) 4) 4)))
						)
						1
				)
			)
			(if (IsObject oldLooper) (oldLooper dispose:))
			(= oldLooper (ego looper?))
			(ego
				looper: 0
				heading: theAngle
				setMotion: 0
				setLoop: GradualLooper
			)
			((ego looper?) doit: ego theAngle ISSc)
			1
		else
			0
		)
	)
)

(instance ISSc of Script
	(properties)
	
	(method (init obj spec otherTest turning)
		(return
			(if otherTest
				(if
					(and
						(not (if turning turnToWhom else goToWhom))
						(Said spec)
					)
					(if (User canControl:)
						(if (IsObject oldAvoider) (oldAvoider dispose:))
						(= oldAvoider (ego avoider?))
						(ego avoider: 0)
						(if turning
							(= turnToWhom obj)
						else
							(= goToWhom obj)
						)
						(User canControl: FALSE canInput: FALSE)
						1
					else
						((User curEvent?) claimed: FALSE)
						2
					)
				)
			else
				0
			)
		)
	)
	
	(method (cue &tmp evt)
		(User canControl: TRUE canInput: TRUE)
		((= evt (Event new:)) type: saidEvent)
		(Parse (User inputLineAddr?) evt)
		(ego setAvoider: oldAvoider)
		(= oldAvoider NULL)
		(if turnToWhom
			((ego looper?) dispose:)
			(ego looper: oldLooper)
			(= oldLooper NULL)
			(turnToWhom handleEvent: evt)
			(= turnToWhom NULL)
		else
			(goToWhom handleEvent: evt)
			(= goToWhom NULL)
		)
		(if (not (evt claimed?))
			(regions eachElementDo: #handleEvent evt 1)
			(theGame handleEvent: evt 1)
		)
		(evt dispose:)
	)
)
