;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQ5EGO)
(include game.sh) (include "0.shm")
(use Main)
(use Smopper)
(use Ego)
(use Motion)
(use System)


(class SQEgo of Ego
	(properties
		noun N_EGO
		modNum 0
	)
	
	(method (handleEvent event &tmp temp0 eType eMsg)
		(= eType (event type?))
		(= eMsg (event message?))
		(return
			(cond 
				((and script (script handleEvent: event))
					TRUE
				)
				((& eType direction)
					(return FALSE)
				)
				(else
					(super handleEvent: event &rest)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (Btst fEgoIsFly)
					(messager say: N_EGO_FLY V_LOOK NULL (Random 1 2) 0 0)
				else
					(messager say: N_EGO V_LOOK NULL (Random 1 2) 0 0)
				)
			)
			(V_DO
				(messager say: N_EGO V_DO NULL (Random 1 2) 0 0)
			)
			(V_TALK
				(messager say: N_EGO V_TALK NULL (Random 1 2) 0 0)
			)
			(V_COMMUNICATOR
				(cond 
					((curRoom script?)
						(super doVerb: theVerb &rest)
					)
					((OneOf curRoomNum 730 740 760 790 620 640 400 410 420)
						(curRoom setScript: (ScriptID curRoomNum 20))
					)
					(else
						(super doVerb: theVerb &rest)
					)
				)
			)
			(V_REBREATHER
				(if (and (== curRoomNum 240) (== eurekaCurLocation 6))
					(curRoom setScript: (ScriptID 243 22))
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setSpeed newSpeed)
		(= egoSpeed (super setSpeed: newSpeed))
	)
)

(class FiddleStopWalk of SmoothStopper
	(properties
		lCel 0
		ticks 1200
		curTicks -1
		lastTicks 0
		oldControl 0
		oldCycSpeed 0
	)
	
	(method (doit &tmp [temp0 2])
		(if (client isStopped:)
			(cond 
				((!= (ego loop?) 8)
					(if (OneOf vInMotion 0 1)
						(= oldCycSpeed egoSpeed)
						(cond 
							((== vInMotion 1) (= vSlow 3))
							((OneOf (ego loop?) 2 4 5) (= vSlow (if (Random 0 1) 12 else 2)))
							(else (= vSlow 12))
						)
						(= curTicks ticks)
					else
						(= vSlow 0)
					)
					(super doit: &rest)
				)
				(
					(and
						(== (ego loop?) 8)
						(!= curTicks -1)
						(<=
							(= curTicks (- curTicks (Abs (- gameTime lastTicks))))
							0
						)
					)
					(= curTicks -1)
					(super doit: &rest)
				)
				(
					(and
						(== curTicks -1)
						(not (curRoom script?))
						(not (ego script?))
						(user canControl:)
						(== (ego view?) 0)
						(== (ego loop?) (- (NumLoops ego) 1))
					)
					(= curTicks ticks)
					(= lCel (ego cel?))
					(= oldCycSpeed (ego cycleSpeed?))
					(if (OneOf (ego cel?) 2 4 5)
						(ego view: (if (Random 0 1) 10 else 2))
					else
						(ego view: 10)
					)
					(ego loop: lCel cel: 0 cycleSpeed: 15 setCycle: EndLoop self)
				)
				(else (super doit: &rest))
			)
		else
			(super doit: &rest)
		)
		(= lastTicks gameTime)
	)
	
	(method (cue)
		(ego
			view: 0
			cel: lCel
			cycleSpeed: oldCycSpeed
			cycler: self
		)
		(ego loop: (- (NumLoops ego) 1))
		(self client: ego)
	)
)
