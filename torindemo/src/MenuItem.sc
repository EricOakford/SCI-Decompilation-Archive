;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64893)
(include sci.sh)
(use Main)
(use List)
(use Cast)
(use Plane)
(use Actor)
(use System)


(class MenuItem of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		scrollThumb 0
		stopThumb 0
		movementDirection 0
		heldArrowDown 0
		ioMine 867
		heldArrowUp 0
		physToUser 0
		userToPhys 0
		userMovedSlider 0
	)
	
	(method (init theStopThumb theMovementDirection theHeldArrowUp)
		(super init: &rest)
		(= stopThumb theStopThumb)
		(= movementDirection theMovementDirection)
		(= scrollThumb (List new:))
		(= heldArrowUp theHeldArrowUp)
		(= userToPhys (= physToUser 0))
		(self setSpeedFraction: (ScriptID 64006 0))
		(self oDragNotify:)
		(scrollThumb
			eachElementDo: #init self movementDirection (+ heldArrowUp 1) &rest
		)
	)
	
	(method (dispose)
		(if scrollThumb
			(scrollThumb eachElementDo: #dispose)
			(scrollThumb dispose:)
		)
		(super dispose: &rest)
	)
	
	(method (show)
		(super show: &rest)
		(if userMovedSlider (userMovedSlider show:))
	)
	
	(method (handleEvent event)
		(return
			(if (self onMe: event)
				(if (== (event type?) evMOUSEBUTTON)
					(gOEventHandler screenLocY: movementDirection)
					(movementDirection currentValue: 1)
				)
				(cond 
					((movementDirection currentValue?) (self hilite:))
					(
						(and
							(== (event type?) evMOUSERELEASE)
							(== (scrollThumb size:) 0)
						)
						(self nDragMethod:)
						(movementDirection hide:)
						(self oVerbs:)
					)
					(else (super handleEvent: event &rest))
				)
				(event claimed: 1)
				(return 1)
			else
				(if (== (scrollThumb size:) 0) (self nDragMethod:))
				(if physToUser
					(scrollThumb eachElementDo: #handleEvent event)
				)
				(return (event claimed?))
			)
		)
	)
	
	(method (posn param1 param2)
		(super posn: param1 param2 &rest)
		(if userMovedSlider
			(userMovedSlider posn: param1 param2)
			(if (userMovedSlider isNotHidden:)
				(UpdateScreenItem userMovedSlider)
			)
			(SetNowSeen userMovedSlider)
		)
		(UpdateScreenItem self)
		(SetNowSeen self)
	)
	
	(method (setCel &tmp theCel)
		(= theCel 0)
		(if physToUser (= theCel (+ theCel 1)))
		(cond 
			(userToPhys
				(if (not userMovedSlider)
					(= userMovedSlider (movementDirection dir: self))
				)
			)
			(userMovedSlider (userMovedSlider dispose:) (= userMovedSlider 0))
		)
		(if (!= cel theCel)
			(= cel theCel)
			(if (self isNotHidden:) (UpdateScreenItem self))
		)
	)
	
	(method (hide)
		(self nDragMethod:)
		(scrollThumb eachElementDo: #hide)
		(super hide: &rest)
		(if userMovedSlider (userMovedSlider hide:))
	)
	
	(method (oDragNotify)
	)
	
	(method (oVerbs)
		(if heldArrowDown (Eval heldArrowDown ioMine))
	)
	
	(method (hilite)
		(= physToUser 1)
		(self setCel:)
		(scrollThumb eachElementDo: #show)
		(scrollThumb eachElementDo: #nDragMethod)
		(stopThumb curULX: self 1038)
	)
	
	(method (nDragMethod)
		(= physToUser 0)
		(self setCel:)
		(scrollThumb eachElementDo: #hide)
	)
	
	(method (check)
		(= userToPhys 1)
		(self setCel:)
	)
	
	(method (oStopNotify)
		(= userToPhys 0)
		(self setCel:)
	)
	
	(method (curULX param1 param2 &tmp scrollThumbFirst temp1)
		(= scrollThumbFirst (scrollThumb first:))
		(while scrollThumbFirst
			(if
			(!= (= temp1 (KList 8 scrollThumbFirst)) param1)
				(temp1 param2: &rest)
			)
			(= scrollThumbFirst (scrollThumb next: scrollThumbFirst))
		)
	)
	
	(method (nStopMethod param1 param2 &tmp scrollThumbFirst temp1)
		(= scrollThumbFirst (scrollThumb first:))
		(while scrollThumbFirst
			((= temp1 (KList 8 scrollThumbFirst))
				posn: param1 param2
			)
			(temp1
				nStopMethod:
					(+
						param1
						(CelWide (temp1 view?) (temp1 loop?) (temp1 cel:))
					)
					param2
			)
			(= param2
				(+
					param2
					(CelHigh (temp1 view?) (temp1 loop?) (temp1 cel:))
				)
			)
			(= scrollThumbFirst (scrollThumb next: scrollThumbFirst))
		)
	)
)

(class MenuHandler of Obj
	(properties
		scratch 0
		scrollThumb 0
		plane 0
		running 0
		currentValue 0
		sound 0
		addScore 0
		rExitTo 0
	)
	
	(method (init theRunning)
		(super init: &rest)
		(if (or (== argc 0) (not theRunning))
			(= running (Cast new:))
		else
			(= running theRunning)
		)
		(= plane (running plane?))
		(if (not plane)
			(= plane ((Plane new:) init: yourself:))
		)
		(plane addCast: running)
		(= currentValue 0)
		(plane setPri: -1)
		(plane picture: -2)
		(UpdatePlane plane)
		(= scrollThumb (List new:))
		(self oDragNotify:)
		(scrollThumb eachElementDo: #init self self 1 running)
		(self nStopMethod:)
		(plane setSize:)
		(self hide:)
	)
	
	(method (dispose)
		(gOEventHandler screenLocX: self)
		(if plane (plane dispose:))
		(= plane (= running 0))
		(super dispose: &rest)
	)
	
	(method (show)
		(plane setPri: 500)
		(UpdatePlane plane)
		(= currentValue 0)
		(scrollThumb eachElementDo: #nDragMethod)
		(scrollThumb eachElementDo: #show)
	)
	
	(method (oDragNotify)
	)
	
	(method (hide)
		(if gOEventHandler (gOEventHandler screenLocX: self))
		(plane setPri: -1)
		(UpdatePlane plane)
		(scrollThumb eachElementDo: #hide)
	)
	
	(method (nScrollDir)
		(if (not scrollThumb) (return 0))
		(if (scrollThumb firstTrue: #physToUser) (return 1))
		(return 0)
	)
	
	(method (nExitDist)
		(return (== (plane priority?) 500))
	)
	
	(method (findData)
		(return
			(if plane
				(return (- (plane right:) (plane left:)))
			else
				(return 0)
			)
		)
	)
	
	(method (doDouble &tmp temp0)
		(return
			(if scrollThumb
				(return
					(-
						((= temp0 (scrollThumb at: 0)) nsBottom?)
						(temp0 nsTop?)
					)
				)
			else
				(return 0)
			)
		)
	)
	
	(method (curULX param1 param2 &tmp scrollThumbFirst temp1)
		(= scrollThumbFirst (scrollThumb first:))
		(while scrollThumbFirst
			(if
			(!= (= temp1 (KList 8 scrollThumbFirst)) param1)
				(temp1 param2: &rest)
			)
			(= scrollThumbFirst (scrollThumb next: scrollThumbFirst))
		)
	)
	
	(method (handleEvent event)
		(if (== (event type?) evMOUSEBUTTON)
			(= currentValue 1)
		)
		(if (== (event type?) evMOUSERELEASE)
			(= currentValue 0)
		)
		(scrollThumb eachElementDo: #handleEvent event &rest)
		(if
			(and
				(not (event claimed?))
				(== (event type?) evMOUSEBUTTON)
			)
			(scrollThumb eachElementDo: #nDragMethod)
			(gOEventHandler screenLocX: self)
		)
		(event claimed: 1)
		(return 1)
	)
	
	(method (nStopMethod &tmp scrollThumbFirst temp1 temp2 temp3)
		(if
		(not (= scrollThumbFirst (scrollThumb first:)))
			(return)
		)
		(= temp2 ((= temp1 (KList 8 scrollThumbFirst)) x?))
		(= temp3 (temp1 y?))
		(while scrollThumbFirst
			((= temp1 (KList 8 scrollThumbFirst)) posn: temp2 temp3)
			(temp1
				nStopMethod:
					temp2
					(+
						temp3
						(CelHigh (temp1 view?) (temp1 loop?) (temp1 cel:))
					)
			)
			(= temp2
				(+
					temp2
					(CelWide (temp1 view?) (temp1 loop?) (temp1 cel:))
				)
			)
			(= scrollThumbFirst (scrollThumb next: scrollThumbFirst))
		)
	)
	
	(method (dir param1 &tmp temp0)
		(= temp0
			((View new:)
				view: sound
				loop: addScore
				cel: rExitTo
				setPri: 1000
				x: (param1 x?)
				y: (param1 y?)
				init: (param1 plane?)
				yourself:
			)
		)
		(if (not (param1 isNotHidden:)) (temp0 hide:))
		(return temp0)
	)
)
