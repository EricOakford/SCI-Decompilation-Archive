;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64996)
(include sci.sh)
(use Main)
(use System)


(instance uEvt of Event
	(method (new)
		(= type
			(= message
				(= modifiers (= y (= x (= claimed (= plane 0)))))
			)
		)
		(GetEvent eventMask self)
		(return self)
	)
)

(class User of Object
	(properties
		alterEgo 0
		input 0
		controls 0
		prevDir 0
		x -1
		y -1
		mapKeyToDir 1
		curEvent 0
	)
	
	(method (init)
		(= curEvent uEvt)
	)
	
	(method (doit)
		(curEvent new:)
		(self handleEvent: curEvent)
	)
	
	(method (canControl value)
		(if argc (= controls value) (= prevDir 0))
		(return controls)
	)
	
	(method (handleEvent event &tmp eventType eventMessage eventModifiers [temp3 2] onPlaneAndHighPriTheObj onPlaneAndHighPriTheObjCasts onPlaneAndHighPriTheObjTheFtrs)
		(= mouseX (event x?))
		(= mouseY (event y?))
		(= eventType (event type?))
		(= eventModifiers (event modifiers?))
		(if (not (talkers isEmpty:))
			(self processDialog: talkers event)
		)
		(if (not (prints isEmpty:))
			(self processDialog: prints event)
		)
		(if eventType
			(= lastEvent event)
			(if (and mapKeyToDir (!= eventType evKEYUP))
				(MapKeyToDir event)
			)
			(if (== eventType evMENUHIT)
				(= eventType 4)
				(= eventMessage
					(if (& eventModifiers emSHIFT) 27 else 13)
				)
				(= eventModifiers 0)
				(event
					type: eventType
					message: eventMessage
					modifiers: eventModifiers
				)
			)
			(event localize: (cast plane?))
			(= eventType (event type?))
			(= eventMessage (event message?))
			(cond 
				((& eventType evMENUSTART)
					(cond 
						(
							(and
								directionHandler
								(directionHandler handleEvent: event)
							)
							(return 1)
						)
						(
							(and
								pMouse
								(or
									(not (& eventType evKEYBOARD))
									(!= eventMessage JOY_NULL)
								)
								(pMouse handleEvent: event)
							)
							(return 1)
						)
					)
				)
				(
					(and
						(& eventType evKEYBOARD)
						keyDownHandler
						(keyDownHandler handleEvent: event)
					)
					(return 1)
				)
				(
					(and
						(& eventType evMOUSE)
						mouseDownHandler
						(mouseDownHandler handleEvent: event)
					)
					(return 1)
				)
			)
		)
		(if gRamaInterface (gRamaInterface handleEvent: event))
		(if (event claimed?) (return 1))
		(if global137
			(event globalize:)
			(OnPlaneAndHighPri init:)
			(KList 19 (planes elements?) 99 OnPlaneAndHighPri event)
			(if
				(not
					(= onPlaneAndHighPriTheObj (OnPlaneAndHighPri theObj?))
				)
				(= onPlaneAndHighPriTheObj thePlane)
			)
			(= onPlaneAndHighPriTheObjCasts
				(onPlaneAndHighPriTheObj casts?)
			)
			(= onPlaneAndHighPriTheObjTheFtrs
				(onPlaneAndHighPriTheObj theFtrs?)
			)
		)
		(if
			(and
				extMouseHandler
				(extMouseHandler handleEvent: event)
			)
			(return 1)
		)
		(if
			(and
				onPlaneAndHighPriTheObj
				(onPlaneAndHighPriTheObj handleEvent: event)
			)
			(return 1)
		)
		(= eventType (event type?))
		(= eventMessage (event message?))
		(if (and eventType (theGame handleEvent: event))
			(return 1)
		)
		(theCursor handleEvent: event)
		(return 0)
	)
	
	(method (canInput n)
		(if argc (= input n))
		(return input)
	)
	
	(method (processDialog param1 param2)
		(repeat
			(= gameTime (+ tickOffset (GetTime)))
			(param1
				eachElementDo: #doit
				firstTrue: #handleEvent param2
			)
			(sounds eachElementDo: #check)
			(FrameOut)
			(breakif (param1 allTrue: #isModeless 2))
			(curEvent new:)
		)
	)
)

(instance findNoun of Code
	(properties)
	
	(method (doit param1 param2)
		(return (== (param1 noun?) param2))
	)
)

(class OnMeAndLowY of Code
	(properties
		scratch 0
		theObj 0
		lastY -1
	)
	
	(method (init)
		(= theObj 0)
		(= lastY -1)
	)
	
	(method (doit theTheObj param2)
		(if
		(and (theTheObj onMe: param2) (> (theTheObj y?) lastY))
			(= lastY ((= theObj theTheObj) y?))
		)
	)
)

(class OnPlaneAndHighPri of Code
	(properties
		scratch 0
		theObj 0
		lastPri -1
	)
	
	(method (init)
		(= theObj thePlane)
		(= lastPri 0)
	)
	
	(method (doit theTheObj param2)
		(if
			(and
				(!= theTheObj gPlane_2)
				(theTheObj onMe: param2)
				(> (theTheObj priority?) lastPri)
			)
			(= lastPri ((= theObj theTheObj) priority?))
		)
	)
)
