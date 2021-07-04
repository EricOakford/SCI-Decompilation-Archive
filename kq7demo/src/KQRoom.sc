;;; Sierra Script 1.0 - (do not remove this comment)
(script# KQROOM)
(include game.sh)
(use Main)
(use Procs)
(use Print)
(use Feature)
(use Game)
(use Actor)
(use System)


(local
	local0
)
(class KQRoom of Room
	(properties
		eastSide 319
	)
	
	(method (init)
		(= eastSide 319)
		(super init: &rest)
		(if (OneOf curRoomNum 2600 3150 5300 4100 4550)
			(curRoom eastSide: 639)
		else
			(curRoom eastSide: 959)
		)
		(if
			(or
				east
				(OneOf curRoomNum 9998 1100 2600 1250 3150 5300 4100 4550)
			)
			((ScriptID 19 0) init:)
		)
		(if
			(or
				west
				(OneOf curRoomNum 9998 1100 2600 1250 3150 5300 4100 4550)
			)
			((ScriptID 19 1) init:)
		)
		(if north ((ScriptID 19 2) init:))
		(if south ((ScriptID 19 3) init:))
	)
	
	(method (dispose &tmp [temp0 2])
		(if hotCast
			(hotCast release:)
		)
		(if interfaceHotCast
			(interfaceHotCast release:)
		)
		((ScriptID 0 4) release:)
		(super dispose:)
	)
	
	(method (doVerb)
		(return TRUE)
	)
	
	(method (drawPic pic)
		(super drawPic: pic &rest)
		(self overlay:)
	)
	
	(method (overlay)
		(if (and (Btst 21) (not (Btst 1)))
			(Bset 1)
			(thePlane setRect: 0 0 319 136 priority: 4)
			(UpdatePlane thePlane)
			(theEye init: theInterfaceCast)
			(interface1 init: theInterfaceCast)
			(control init: theInterfaceCast)
			(help init: theInterfaceCast)
			(scroller init: theInterfaceCast)
			(UpdatePlane theInterfacePlane)
		)
		(if
			(OneOf prevRoomNum
				9998 1100 2600 1250
				3150 5300 4100 4550
			)
			(thePlane setRect: 0 0 319 136 priority: 4)
			(UpdatePlane thePlane)
		)
		(if (and (not (Btst 21)) (Btst 1))
			(Bclr 1)
			(theEye dispose:)
			(interface1 dispose:)
			(control dispose:)
			(help dispose:)
			(scroller dispose:)
			(UpdatePlane theInterfacePlane)
		)
	)
	
	(method (reflectPosn theActor theEdge)
		(switch theEdge
			(NORTH
				(theActor y: 135)
			)
			(WEST
				(theActor x: (- (curRoom eastSide?) 20))
			)
			(SOUTH
				(theActor y: (+ (curRoom horizon?) 4))
			)
			(EAST
				(theActor x: 20)
			)
		)
	)
	
	(method (setRect param1 param2 param3 param4)
		(thePlane setRect: param1 param2 param3 param4)
		(cond 
			((> (- param4 param2) 201))
			((> (- param3 param1) 641)
				(switch param1
					(0
						((ScriptID 19 0) nsLeft: 300 nsRight: 320)
						((ScriptID 19 1) nsLeft: 0 nsRight: 20)
					)
					(-319
						((ScriptID 19 0) nsLeft: 619 nsRight: 639)
						((ScriptID 19 1) nsLeft: 319 nsRight: 340)
					)
					(-639
						((ScriptID 19 0) nsLeft: 940 nsRight: 959)
						((ScriptID 19 1) nsLeft: 639 nsRight: 660)
					)
				)
			)
			(else
				(switch param1
					(0
						((ScriptID 19 0) nsLeft: 300 nsRight: 320)
						((ScriptID 19 1) nsLeft: 0 nsRight: 20)
					)
					(-319
						((ScriptID 19 0) nsLeft: 619 nsRight: 639)
						((ScriptID 19 1) nsLeft: 320 nsRight: 340)
					)
				)
			)
		)
		(UpdatePlane thePlane)
	)
	
	(method (updateExits param1)
		((ScriptID 19 0)
			nsLeft: (- ((ScriptID 19 0) nsLeft?) param1)
			nsRight: (- ((ScriptID 19 0) nsRight?) param1)
		)
		((ScriptID 19 1)
			nsLeft: (- ((ScriptID 19 1) nsLeft?) param1)
			nsRight: (- ((ScriptID 19 1) nsRight?) param1)
		)
		(if
			(and
				(not east)
				(< (thePlane right:) 325)
				((ScriptID 19 0) scratch?)
			)
			((ScriptID 19 0) setHotspot: 0)
		)
		(if
			(and
				(not ((ScriptID 19 0) scratch?))
				(> (thePlane right:) 325)
			)
			((ScriptID 19 0) setHotspot: 10 10)
		)
		(if
			(and
				(not west)
				(> (thePlane left:) -5)
				((ScriptID 19 1) scratch?)
			)
			((ScriptID 19 1) setHotspot: 0)
		)
		(if
			(and
				(not ((ScriptID 19 1) scratch?))
				(< (thePlane left:) -5)
			)
			((ScriptID 19 1) setHotspot: 10 10)
		)
	)
)

(instance interface1 of View
	(properties
		view 950
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(cond 
				((== theVerb 10)
					(return TRUE)
				)
				((= temp0 (inventory firstTrue: #isMyVerb theVerb))
					(user message: 10)
					(temp0 show:)
					(theGame setCursor: normalCursor)
					(= curInvItem 0)
					(return TRUE)
				)
				(else
					(return TRUE)
				)
			)
		)
	)
)

(instance theEye of Feature
	(properties
		nsLeft 231
		nsTop 22
		nsRight 272
		nsBottom 52
	)
	
	(method (init)
		(theInterfaceCast addToFront: self)
		(= plane theInterfacePlane)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(if
				(and
					(!= theVerb 10)
					(= temp0 (inventory firstTrue: #isMyVerb theVerb))
				)
				((ScriptID 24) doit: temp0)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance control of Feature
	(properties
		nsLeft 277
		nsTop 18
		nsRight 309
		nsBottom 47
	)
	
	(method (init)
		(theInterfaceCast addToFront: self)
		(= plane theInterfacePlane)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(return
			(if (== theVerb 10)
				((ScriptID 25) init: 0 curRoom 1)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance help of Feature
	(properties
		nsLeft 15
		nsTop 12
		nsRight 48
		nsBottom 42
	)
	
	(method (init)
		(theInterfaceCast addToFront: self)
		(= plane theInterfacePlane)
	)
	
	(method (doVerb theVerb)
		(return (if (== theVerb 10) (Prints {HELP}) (return 1) else 0))
	)
)

(class ScrollFeat of Feature
	(properties
		activated 0
		theInc 2
		theLeft -320
		canControl 0
		egoDistLeft 0
		egoDistRight 0
		scrollDir 0
		maxDelta 640
	)
	
	(method (init)
		(theInterfaceCast addToFront: self)
		(= plane theInterfacePlane)
		(if (OneOf curRoomNum 2600 3150 5300 4100 4550)
			(curRoom eastSide: 639)
			(= maxDelta 320)
		else
			(curRoom eastSide: 959)
			(= maxDelta 640)
		)
		(if (== curRoomNum 1400)
			(= scrollDir 1)
		else
			(= scrollDir 0)
		)
		(= canControl (= activated FALSE))
	)
	
	(method (doit &tmp [temp0 2])
		(if activated
			(if (== scrollDir 0)
				(= egoDistLeft (- (ego x?) (Abs (thePlane left:))))
				(= egoDistRight (- 319 egoDistLeft))
				(if
					(or
						(and (> theInc 0) (< egoDistRight 10))
						(and (< theInc 0) (< egoDistLeft 10))
						(and
							(> (+ theLeft 4) (thePlane left:))
							(> (thePlane left:) (- theLeft 4))
						)
					)
					(= activated 0)
				else
					(thePlane
						setRect:
							(+ (thePlane left:) theInc)
							0
							(+ (thePlane right:) theInc)
							136
					)
					(UpdatePlane thePlane)
					(FrameOut)
				)
			else
				(= egoDistLeft (- (ego y?) (Abs (thePlane top?))))
				(= egoDistRight (- 136 egoDistLeft))
				(if
					(and
						(> (+ theLeft 4) (thePlane top?))
						(> (thePlane top?) (- theLeft 4))
					)
					(= activated 0)
				else
					(thePlane
						setRect:
							0
							(+ (thePlane top?) theInc)
							320
							(+ (thePlane bottom?) theInc)
					)
					(UpdatePlane thePlane)
					(FrameOut)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp [temp0 2] evt evtX)
		(event localize: plane)
		(cond 
			((event claimed?) (return TRUE))
			((and (& (event type?) userEvent) (self onMe: event))
				(event claimed: TRUE)
				(= activated (= canControl TRUE))
				(while canControl
					((= evt (Event new:)) localize: plane)
					(if (and (!= (evt type?) mouseUp) (self onMe: evt))
						(= activated TRUE)
						(= evtX (evt x?))
						(scroller x: evtX)
						(UpdateScreenItem scroller)
						(= evtX (/ (- evtX 229) 2))
						(if (== scrollDir 0)
							(= theLeft (/ (* maxDelta evtX) 37))
							(= theLeft (* (- maxDelta theLeft) -1))
							(= theInc (if (< theLeft (thePlane left:)) -3 else 3))
						else
							(= theLeft (/ (* 270 evtX) 37))
							(= theLeft (* (- 270 theLeft) -1))
							(= theInc (if (< theLeft (thePlane top?)) -2 else 2))
						)
					else
						(= canControl FALSE)
					)
					(= gameTime (+ tickOffset (GetTime)))
					(cast doit:)
					(if doMotionCue
						(= doMotionCue FALSE)
						(cast eachElementDo: #motionCue)
					)
					(if (theGame script?)
						((theGame script?) doit:)
					)
					(regions eachElementDo: #doit)
					(evt dispose:)
				)
				(return TRUE)
			)
		)
		(return FALSE)
	)
	
	(method (doVerb)
		(return TRUE)
	)
	
	(method (mask)
	)
)

(instance scroller of View
	(properties
		x 269
		y 10
		view 950
		loop 1
	)
	
	(method (handleEvent)
		(return FALSE)
	)
)
