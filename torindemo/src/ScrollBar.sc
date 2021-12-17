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
		type $0000
		object 0
		selector 0
		nBorderDist 0
		oScrollPlane 0
		oCursor 0
		oNScrollExit 1
		oSScrollExit 3
		oEScrollExit -1
		oWScrollExit 0
		oHorizHandle 0
		oVertHandle -1
		oMyScrollPlane 0
		nLowBound 0
		nHighBound -1
		bXAxis 0
		nMouseStartX 0
		top 0
		left 0
		width 0
		height 0
		nMouseStartY 0
		nHandleStartX 0
		nHandleStartY 0
		reSynch 0
		nMsgFile 0
		oFlagArray 0
		oMsgArray 0
	)
	
	(method (init param1 &tmp temp0 temp1 temp2)
		(= temp2 (if (and argc param1) param1 else cast))
		(super init: temp2)
		(self setSpeedFraction: (ScriptID 64006 0))
		(= left x)
		(= top y)
		(= x (= y 0))
		(if (!= oVertHandle -1)
			((= nHandleStartX (PushButton new:))
				view: oVertHandle
				loop: oMyScrollPlane
				cel: nLowBound
				priority: (self priority?)
				fixPriority: 1
				setCurItem: self
				getVerbItem: 1078
				blankID: self
				viewSlotsX: 1082
				init: temp2
			)
		)
		(if (!= nHighBound -1)
			((= nHandleStartY (PushButton new:))
				view: nHighBound
				loop: bXAxis
				cel: nMouseStartX
				priority: (self priority?)
				fixPriority: 1
				setCurItem: self
				getVerbItem: 1077
				blankID: self
				viewSlotsX: 1083
				init: temp2
			)
		)
		((= nMouseStartY (DragButton new:))
			view: oEScrollExit
			loop: oWScrollExit
			cel: oHorizHandle
			priority: (+ 1 (self priority?))
			fixPriority: 1
			setCurItem: self
			getVerbItem: 1079
			oKeyHandlers: self
			oDefaultHandlers: 1080
			init: temp2
		)
		(cond 
			((== type 0)
				(= oFlagArray
					(-
						(CelHigh view loop cel)
						(CelHigh oEScrollExit oWScrollExit oHorizHandle)
					)
				)
			)
			((== type 1)
				(= oFlagArray
					(-
						(CelWide view loop cel)
						(CelWide oEScrollExit oWScrollExit oHorizHandle)
					)
				)
			)
		)
		(if
		(and (== 0 nBorderDist) (== nBorderDist oScrollPlane))
			(= oScrollPlane oFlagArray)
		)
		(= oMsgArray (- oScrollPlane nBorderDist))
		(cond 
			((== type 0)
				(= width (CelWide view loop cel))
				(= height 0)
				(if nHandleStartY
					(nHandleStartY x: 0 y: 0)
					(= height
						(+
							height
							(= temp1
								(CelHigh
									(nHandleStartY view?)
									(nHandleStartY loop?)
									(nHandleStartY cel:)
								)
							)
						)
					)
				)
				(self x: 0 y: height)
				(nMouseStartY
					x: 0
					y: (+ height (self oEventHogList: oCursor))
				)
				(= height (+ height (= temp1 (CelHigh view loop cel))))
				(if nHandleStartX
					(nHandleStartX x: 0 y: height)
					(= height
						(+
							height
							(= temp1
								(CelHigh
									(nHandleStartX view?)
									(nHandleStartX loop?)
									(nHandleStartX cel:)
								)
							)
						)
					)
				)
			)
			((== type 1)
				(= width 0)
				(= height (CelHigh view loop cel))
				(if nHandleStartY
					(nHandleStartY x: 0 y: 0)
					(= width
						(+
							width
							(= temp1
								(CelWide
									(nHandleStartY view?)
									(nHandleStartY loop?)
									(nHandleStartY cel:)
								)
							)
						)
					)
				)
				(self x: width y: 0)
				(nMouseStartY
					x: (+ width (self oEventHogList: oCursor))
					y: 0
				)
				(= width (+ width (= temp1 (CelHigh view loop cel))))
				(if nHandleStartX
					(nHandleStartX x: width y: 0)
					(= width
						(+
							width
							(= temp1
								(CelWide
									(nHandleStartX view?)
									(nHandleStartX loop?)
									(nHandleStartX cel:)
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
		(if nHandleStartX (nHandleStartX dispose:))
		(if nHandleStartY (nHandleStartY dispose:))
		(if nMouseStartY (nMouseStartY dispose:))
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp scrollBarOCurHelpItem)
		(if
			(and
				(self onMe: event)
				(not (nMouseStartY onMe: event))
			)
			(event claimed: 1)
			(if (& (event type?) evMOUSEBUTTON)
				(= scrollBarOCurHelpItem (self oCurHelpItem:))
				(if
					(>
						(Eval event scrollBarOCurHelpItem)
						(Eval nMouseStartY scrollBarOCurHelpItem)
					)
					(self changeCursor:)
				else
					(self doToolHelp:)
				)
			)
		)
		(event claimed?)
	)
	
	(method (posn theLeft theTop &tmp temp0 temp1)
		(= temp0 (- theLeft x))
		(= temp1 (- theTop y))
		(if (!= theLeft 0)
			(if nHandleStartY
				(nHandleStartY x: (+ (nHandleStartY x?) temp0))
			)
			(self x: (+ x temp0))
			(if nHandleStartX
				(nHandleStartX x: (+ (nHandleStartX x?) temp0))
			)
			(if nMouseStartY
				(nMouseStartY x: (+ (nMouseStartY x?) temp0))
			)
		)
		(if (!= theTop 0)
			(if nHandleStartY
				(nHandleStartY y: (+ (nHandleStartY y?) temp1))
			)
			(self y: (+ y temp1))
			(if nHandleStartX
				(nHandleStartX y: (+ (nHandleStartX y?) temp1))
			)
			(if nMouseStartY
				(nMouseStartY y: (+ (nMouseStartY y?) temp1))
			)
		)
		(= left theLeft)
		(= top theTop)
	)
	
	(method (solvedThrough theOCursor)
		(= oCursor theOCursor)
		(self oGlobalHandlers: oCursor)
	)
	
	(method (setAllFlagsUpTo theNBorderDist theOScrollPlane)
		(= nBorderDist theNBorderDist)
		(= oMsgArray
			(- (= oScrollPlane theOScrollPlane) theNBorderDist)
		)
		(self oGlobalHandlers: oCursor)
	)
	
	(method (getPenalty)
		(return oCursor)
	)
	
	(method (getScore theONScrollExit theOSScrollExit)
		(= oNScrollExit theONScrollExit)
		(= oSScrollExit theOSScrollExit)
	)
	
	(method (setPenalty)
		(if object (Eval object selector))
	)
	
	(method (doToolHelp)
		(= reSynch (+ gameTime 20))
		(self move: (- oSScrollExit) 1)
	)
	
	(method (changeCursor)
		(= reSynch (+ gameTime 20))
		(self move: oSScrollExit 1)
	)
	
	(method (nLastCursX)
		(= reSynch (+ gameTime 20))
		(self move: (- oNScrollExit) 1)
	)
	
	(method (nLastCursY)
		(= reSynch (+ gameTime 20))
		(self move: oNScrollExit 1)
	)
	
	(method (move param1 param2 &tmp scrollBarOCurHelpItem temp1 theOScrollPlane)
		(= scrollBarOCurHelpItem (self oCurHelpItem:))
		(= temp1 (Eval self scrollBarOCurHelpItem))
		(cond 
			(
			(> (= theOScrollPlane (+ oCursor param1)) oScrollPlane) (= theOScrollPlane oScrollPlane))
			((< theOScrollPlane nBorderDist) (= theOScrollPlane nBorderDist))
		)
		(if (== theOScrollPlane oCursor) (return 0))
		(= oCursor theOScrollPlane)
		(Eval
			nMouseStartY
			scrollBarOCurHelpItem
			(+ temp1 (self oEventHogList: oCursor))
		)
		(if (and (> argc 1) param2)
			(UpdateScreenItem nMouseStartY)
		)
		(self setPenalty:)
		(return 1)
	)
	
	(method (nToolHelpTime &tmp scrollBarOCurHelpItem userCurEvent)
		(= scrollBarOCurHelpItem (self oCurHelpItem:))
		(= userCurEvent (user curEvent?))
		(= nMsgFile
			(-
				(Eval userCurEvent scrollBarOCurHelpItem)
				(self oEventHogList: oCursor)
			)
		)
	)
	
	(method (oCurToolHelp &tmp scrollBarOCurHelpItem userCurEvent temp2 temp3)
		(= scrollBarOCurHelpItem (self oCurHelpItem:))
		(= userCurEvent (user curEvent?))
		(if (not (self onMe: userCurEvent)) (return))
		(if
			(!=
				(= temp2
					(self
						oHandsOffList: (- (Eval userCurEvent scrollBarOCurHelpItem) nMsgFile)
					)
				)
				oCursor
			)
			(= temp3 (- temp2 oCursor))
			(self move: temp3)
		)
	)
	
	(method (oCurHelpItem)
		(return
			(cond 
				((== type 0) 2)
				((== type 1) 1)
			)
		)
	)
	
	(method (bTimerStarted)
		(if (> gameTime reSynch)
			(= reSynch (+ gameTime 5))
			(self move: oNScrollExit 1)
		)
	)
	
	(method (bSortedFeatures)
		(if (> gameTime reSynch)
			(= reSynch (+ gameTime 5))
			(self move: (- oNScrollExit) 1)
		)
	)
	
	(method (oHandsOffList param1)
		(if (not oFlagArray)
			(MonoOut {physical range not inited. DJM. scrllbar.sc})
			(return 0)
		)
		(return (+ (MulDiv param1 oMsgArray oFlagArray) nBorderDist))
	)
	
	(method (oEventHogList param1)
		(if (not oMsgArray) (return 0))
		(return (MulDiv (- param1 nBorderDist) oFlagArray oMsgArray))
	)
	
	(method (oGlobalHandlers theOCursor param2 &tmp scrollBarOCurHelpItem temp1)
		(= scrollBarOCurHelpItem (self oCurHelpItem:))
		(= oCursor theOCursor)
		(if (< oCursor nBorderDist) (= oCursor nBorderDist))
		(if (> oCursor oScrollPlane) (= oCursor oScrollPlane))
		(= temp1 (Eval self scrollBarOCurHelpItem))
		(Eval
			nMouseStartY
			scrollBarOCurHelpItem
			(+ temp1 (self oEventHogList: oCursor))
		)
		(if (and (> argc 1) param2)
			(UpdateScreenItem nMouseStartY)
		)
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
		setCurItem 0
		getVerbItem 0
		oKeyHandlers 0
		oDefaultHandlers 0
		invSlotsX 0
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 0))
	)
	
	(method (handleEvent event)
		(return
			(cond 
				(invSlotsX
					(event claimed: 1)
					(if (== (event type?) evMOUSERELEASE)
						(gOEventHandler screenLocX: self)
						(= invSlotsX 0)
					)
					(if oKeyHandlers (Eval oKeyHandlers oDefaultHandlers))
					(return 1)
				)
				(
					(and
						(== (event type?) evMOUSEBUTTON)
						(self onMe: event)
					)
					(event claimed: 1)
					(gOEventHandler screenLocY: self)
					(= invSlotsX 1)
					(if setCurItem (Eval setCurItem getVerbItem))
					(return 1)
				)
				(else (super handleEvent: event &rest))
			)
		)
	)
)
