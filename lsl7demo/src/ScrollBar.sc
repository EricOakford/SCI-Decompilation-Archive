;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64005)
(include sci.sh)
(use Main)
(use PushButton)
(use Actor)
(use System)


(class ScrollBar of View
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
		signal $5021
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
		type $0000
		object 0
		selector 0
		minPosn 0
		maxPosn 0
		curPosn 0
		incSize 1
		pageSize 3
		vThumbView -1
		lThumbLoop 0
		cThumbCel 0
		vDownArrowView -1
		lDownArrowLoop 0
		cDownArrowCel 0
		vUpArrowView -1
		lUpArrowLoop 0
		cUpArrowCel 0
		top 0
		left 0
		width 0
		height 0
		oThumb 0
		oDownArrow 0
		oUpArrow 0
		nScrollTime 0
		nThumbOffset 0
		nPhysRange 0
		nUserRange 0
	)
	
	(method (init param1 &tmp temp0 temp1 temp2)
		(= temp2 (if (and argc param1) param1 else cast))
		(super init: temp2)
		(self forceCursor: (ScriptID 64006 0))
		(= left x)
		(= top y)
		(= x (= y 0))
		(if (!= vDownArrowView -1)
			((= oDownArrow (PushButton new:))
				view: vDownArrowView
				loop: lDownArrowLoop
				cel: cDownArrowCel
				priority: (self priority?)
				fixPriority: 1
				oClickNotify: self
				nClickMethod: 1042
				oHeldNotify: self
				nHeldMethod: 1047
				init: temp2
			)
		)
		(if (!= vUpArrowView -1)
			((= oUpArrow (PushButton new:))
				view: vUpArrowView
				loop: lUpArrowLoop
				cel: cUpArrowCel
				priority: (self priority?)
				fixPriority: 1
				oClickNotify: self
				nClickMethod: 1041
				oHeldNotify: self
				nHeldMethod: 1048
				init: temp2
			)
		)
		((= oThumb (DragButton new:))
			view: vThumbView
			loop: lThumbLoop
			cel: cThumbCel
			priority: (+ 1 (self priority?))
			fixPriority: 1
			oClickNotify: self
			nClickMethod: 1043
			oDragNotify: self
			nDragMethod: 1044
			oStopNotify: self
			nStopMethod: 1045
			init: temp2
		)
		(cond 
			((== type 0)
				(= nPhysRange
					(-
						(CelHigh view loop cel)
						(CelHigh vThumbView lThumbLoop cThumbCel)
					)
				)
			)
			((== type 1)
				(= nPhysRange
					(-
						(CelWide view loop cel)
						(CelWide vThumbView lThumbLoop cThumbCel)
					)
				)
			)
		)
		(if (and (== 0 minPosn) (== minPosn maxPosn))
			(= maxPosn nPhysRange)
		)
		(= nUserRange (- maxPosn minPosn))
		(cond 
			((== type 0)
				(= width (CelWide view loop cel))
				(= height 0)
				(if oUpArrow
					(oUpArrow x: 0 y: 0)
					(= height
						(+
							height
							(= temp1
								(CelHigh
									(oUpArrow view?)
									(oUpArrow loop?)
									(oUpArrow cel?)
								)
							)
						)
					)
				)
				(self x: 0 y: height)
				(oThumb x: 0 y: (+ height (self userToPhys: curPosn)))
				(= height (+ height (= temp1 (CelHigh view loop cel))))
				(if oDownArrow
					(oDownArrow x: 0 y: height)
					(= height
						(+
							height
							(= temp1
								(CelHigh
									(oDownArrow view?)
									(oDownArrow loop?)
									(oDownArrow cel?)
								)
							)
						)
					)
				)
			)
			((== type 1)
				(= width 0)
				(= height (CelHigh view loop cel))
				(if oUpArrow
					(oUpArrow x: 0 y: 0)
					(= width
						(+
							width
							(= temp1
								(CelWide
									(oUpArrow view?)
									(oUpArrow loop?)
									(oUpArrow cel?)
								)
							)
						)
					)
				)
				(self x: width y: 0)
				(oThumb x: (+ width (self userToPhys: curPosn)) y: 0)
				(= width (+ width (= temp1 (CelHigh view loop cel))))
				(if oDownArrow
					(oDownArrow x: width y: 0)
					(= width
						(+
							width
							(= temp1
								(CelWide
									(oDownArrow view?)
									(oDownArrow loop?)
									(oDownArrow cel?)
								)
							)
						)
					)
				)
			)
		)
		(self posn: left top)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp scrollBarMovementDirection)
		(if (self onMe: event)
			(event claimed: 1)
			(if (& (event type?) evMOUSEBUTTON)
				(= scrollBarMovementDirection (self movementDirection:))
				(if
					(>
						(Eval event scrollBarMovementDirection)
						(Eval oThumb scrollBarMovementDirection)
					)
					(self pageDown:)
				else
					(self pageUp:)
				)
			)
		)
		(event claimed?)
	)
	
	(method (posn theLeft theTop &tmp temp0 temp1)
		(= temp0 (- theLeft x))
		(= temp1 (- theTop y))
		(if (!= theLeft 0)
			(if oUpArrow (oUpArrow x: (+ (oUpArrow x?) temp0)))
			(self x: (+ x temp0))
			(if oDownArrow
				(oDownArrow x: (+ (oDownArrow x?) temp0))
			)
			(if oThumb (oThumb x: (+ (oThumb x?) temp0)))
		)
		(if (!= theTop 0)
			(if oUpArrow (oUpArrow y: (+ (oUpArrow y?) temp1)))
			(self y: (+ y temp1))
			(if oDownArrow
				(oDownArrow y: (+ (oDownArrow y?) temp1))
			)
			(if oThumb (oThumb y: (+ (oThumb y?) temp1)))
		)
		(= left theLeft)
		(= top theTop)
	)
	
	(method (setPos theCurPosn)
		(= curPosn theCurPosn)
		(self userMovedSlider: curPosn)
	)
	
	(method (setMinMax theMinPosn theMaxPosn)
		(= minPosn theMinPosn)
		(= nUserRange (- (= maxPosn theMaxPosn) theMinPosn))
		(self userMovedSlider: curPosn)
	)
	
	(method (getPos)
		(return curPosn)
	)
	
	(method (setIncrement theIncSize thePageSize)
		(= incSize theIncSize)
		(= pageSize thePageSize)
	)
	
	(method (scrolled)
		(if object (Eval object selector))
	)
	
	(method (pageUp)
		(= nScrollTime (+ gameTime 20))
		(self move: (- pageSize) 1)
	)
	
	(method (pageDown)
		(= nScrollTime (+ gameTime 20))
		(self move: pageSize 1)
	)
	
	(method (arrowUp)
		(= nScrollTime (+ gameTime 20))
		(self move: (- incSize) 1)
	)
	
	(method (arrowDown)
		(= nScrollTime (+ gameTime 20))
		(self move: incSize 1)
	)
	
	(method (move param1 param2 &tmp scrollBarMovementDirection temp1 theMaxPosn)
		(= scrollBarMovementDirection (self movementDirection:))
		(= temp1 (Eval self scrollBarMovementDirection))
		(cond 
			((> (= theMaxPosn (+ curPosn param1)) maxPosn) (= theMaxPosn maxPosn))
			((< theMaxPosn minPosn) (= theMaxPosn minPosn))
		)
		(if (== theMaxPosn curPosn) (return 0))
		(= curPosn theMaxPosn)
		(Eval
			oThumb
			scrollBarMovementDirection
			(+ temp1 (self userToPhys: curPosn))
		)
		(if (and (> argc 1) param2) (UpdateScreenItem oThumb))
		(self scrolled:)
		(return 1)
	)
	
	(method (initThumb &tmp scrollBarMovementDirection userCurEvent)
		(= scrollBarMovementDirection (self movementDirection:))
		(= userCurEvent (user curEvent?))
		(= nThumbOffset
			(-
				(Eval userCurEvent scrollBarMovementDirection)
				(self userToPhys: curPosn)
			)
		)
	)
	
	(method (scrollThumb &tmp scrollBarMovementDirection userCurEvent temp2 temp3)
		(= scrollBarMovementDirection (self movementDirection:))
		(= userCurEvent (user curEvent?))
		(if
			(!=
				(= temp2
					(self
						physToUser:
							(-
								(Eval userCurEvent scrollBarMovementDirection)
								nThumbOffset
							)
					)
				)
				curPosn
			)
			(= temp3 (- temp2 curPosn))
			(self move: temp3)
		)
	)
	
	(method (stopThumb)
	)
	
	(method (movementDirection)
		(return
			(cond 
				((== type 0) 2)
				((== type 1) 1)
			)
		)
	)
	
	(method (heldArrowDown)
		(if (> gameTime nScrollTime)
			(= nScrollTime (+ gameTime 5))
			(self move: incSize 1)
		)
	)
	
	(method (heldArrowUp)
		(if (> gameTime nScrollTime)
			(= nScrollTime (+ gameTime 5))
			(self move: (- incSize) 1)
		)
	)
	
	(method (physToUser param1 &tmp temp0)
		(if (not nPhysRange)
			(MonoOut {physical range not inited. DJM. scrllbar.sc})
			(return 0)
		)
		(= param1 (Max 0 (Min nPhysRange param1)))
		(= temp0
			(+ (MulDiv param1 nUserRange nPhysRange) minPosn)
		)
		(return (= temp0 (Max minPosn (Min maxPosn temp0))))
	)
	
	(method (userToPhys param1)
		(if (not nUserRange) (return 0))
		(return (MulDiv (- param1 minPosn) nPhysRange nUserRange))
	)
	
	(method (userMovedSlider theCurPosn param2 &tmp scrollBarMovementDirection temp1)
		(= scrollBarMovementDirection (self movementDirection:))
		(= curPosn theCurPosn)
		(if (< curPosn minPosn) (= curPosn minPosn))
		(if (> curPosn maxPosn) (= curPosn maxPosn))
		(= temp1 (Eval self scrollBarMovementDirection))
		(Eval
			oThumb
			scrollBarMovementDirection
			(+ temp1 (self userToPhys: curPosn))
		)
		(if (and (> argc 1) param2) (UpdateScreenItem oThumb))
	)
)

(class DragButton of View
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
		signal $5021
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
		oClickNotify 0
		nClickMethod 0
		oDragNotify 0
		nDragMethod 0
		oStopNotify 0
		nStopMethod 0
		bImAHog 0
	)
	
	(method (init)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (handleEvent event)
		(return
			(cond 
				(bImAHog
					(event claimed: 1)
					(if (== (event type?) evMOUSERELEASE)
						(Eval oStopNotify nStopMethod)
						(self stopHogging:)
					)
					(if oDragNotify (Eval oDragNotify nDragMethod))
					(return 1)
				)
				(
					(and
						(== (event type?) evMOUSEBUTTON)
						(self onMe: event)
					)
					(event claimed: 1)
					(gOEventHandler registerEventHog: self)
					(= bImAHog 1)
					(if oClickNotify (Eval oClickNotify nClickMethod))
					(return 1)
				)
				(else (super handleEvent: event &rest))
			)
		)
	)
	
	(method (stopHogging)
		(gOEventHandler unregisterEventHog: self)
		(= bImAHog 0)
	)
)
