;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64009)
(include sci.sh)
(use Main)
(use ScrollView)
(use Plane)
(use Print)
(use PolyPath)
(use CueObj)
(use Actor)


(class ScrollExit of Feature
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
		nScrollDir 1
		nExitDist 30
		nBorderDist 40
		oScrollPlane 0
		oCursor 0
		nOffX 0
		nOffY 0
	)
	
	(method (init)
		(super init: &rest)
		(= oScrollPlane gOScrollPlane)
		(switch nScrollDir
			(3
				(= oCursor (ScriptID 64006 2))
				(= nsTop (- (plane bottom?) nExitDist))
				(= nsBottom (plane bottom?))
				(= nsRight (plane right:))
				(= nsLeft 0)
				(= nOffX (oScrollPlane left:))
				(= nOffY (- (oScrollPlane getHeight:) nBorderDist))
			)
			(2
				(= oCursor (ScriptID 64006 3))
				(= nsTop 0)
				(= nsBottom (plane bottom?))
				(= nsRight (plane right:))
				(= nsLeft (- (plane right:) nExitDist))
				(= nOffX (- (oScrollPlane getWidth:) nBorderDist))
				(= nOffY (oScrollPlane top?))
			)
			(4
				(= oCursor (ScriptID 64006 4))
				(= nsTop 0)
				(= nsBottom (plane bottom?))
				(= nsRight nExitDist)
				(= nsLeft 0)
				(= nOffX nBorderDist)
				(= nOffY (oScrollPlane top?))
			)
			(else 
				(= oCursor (ScriptID 64006 1))
				(= nsTop 0)
				(= nsBottom nExitDist)
				(= nsRight (plane right:))
				(= nsLeft 0)
				(= nOffX (oScrollPlane left:))
				(= nOffY nBorderDist)
			)
		)
		(self forceCursor: oCursor)
	)
	
	(method (show)
		(self forceCursor: oCursor)
	)
	
	(method (handleEvent event)
		(return
			(if
				(or
					(not ego)
					(not (ego scratch?))
					(not (ego isNotHidden:))
					(and
						(== nScrollDir 1)
						(not (oScrollPlane canScrollUp:))
					)
					(and
						(== nScrollDir 3)
						(not (oScrollPlane canScrollDown:))
					)
					(and
						(== nScrollDir 4)
						(not (oScrollPlane canScrollLeft:))
					)
					(and
						(== nScrollDir 2)
						(not (oScrollPlane canScrollRight:))
					)
				)
				(return 0)
			else
				(return (super handleEvent: event &rest))
			)
		)
	)
	
	(method (doVerb &tmp theNOffX theNOffY)
		(if
			(or
				(not ego)
				(not (ego scratch?))
				(not (ego isNotHidden:))
			)
			(return)
		)
		(switch nScrollDir
			(3
				(= theNOffX (- mouseX nOffX))
				(= theNOffY nOffY)
			)
			(2
				(= theNOffX nOffX)
				(= theNOffY (- mouseY nOffY))
			)
			(4
				(= theNOffX nOffX)
				(= theNOffY (- mouseY nOffY))
			)
			(else 
				(= theNOffX (- mouseX nOffX))
				(= theNOffY nOffY)
			)
		)
		(if (ego oWalkHandler?)
			((ego oWalkHandler?) doit: theNOffX theNOffY)
		else
			(ego setMotion: PolyPath theNOffX theNOffY)
		)
	)
	
	(method (hide)
		(self forceCursor: 0)
	)
)

(instance foNScroll of ScrollExit
	(properties)
)

(instance foSScroll of ScrollExit
	(properties
		nScrollDir 3
	)
)

(instance foWScroll of ScrollExit
	(properties
		nScrollDir 4
	)
)

(instance foEScroll of ScrollExit
	(properties
		nScrollDir 2
	)
)

(class TorScrollPlane of ScrollPlane
	(properties
		scratch 0
		resX -1
		resY -1
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -1
		style $0000
		priority -1
		back 0
		bitmap 0
		casts 0
		mirrored 0
		oMyFeatures 0
		nScreenSizeX 0
		nScreenSizeY 0
		nScreenOrgX 0
		nScreenOrgY 0
		nCurPosX 0
		nCurPosY 0
		nScrollMaxX 0
		nScrollMaxY 0
		oMyScrollViews 0
		oMyNotifyObjs 0
		oMyNotifySelectors 0
		oPanner 0
		oNScrollExit -1
		oSScrollExit -1
		oEScrollExit -1
		oWScrollExit -1
		oHorizHandle -1
		oVertHandle -1
	)
	
	(method (init)
		(super init: &rest)
		(if nScrollMaxX
			(if (== -1 oEScrollExit)
				(= oEScrollExit
					(foEScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 oWScrollExit)
				(= oWScrollExit
					(foWScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 oHorizHandle)
				(= oHorizHandle
					(oHHandle oMyScrollPlane: self init: yourself:)
				)
			)
		)
		(if nScrollMaxY
			(if (== -1 oNScrollExit)
				(= oNScrollExit
					(foNScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 oSScrollExit)
				(= oSScrollExit
					(foSScroll init: (ScriptID 0 1) yourself:)
				)
			)
			(if (== -1 oVertHandle)
				(= oVertHandle
					(oVHandle oMyScrollPlane: self init: yourself:)
				)
			)
		)
	)
	
	(method (dispose)
		(if (and oHorizHandle (!= -1 oHorizHandle))
			(oHorizHandle dispose:)
		)
		(if (and oVertHandle (!= -1 oVertHandle))
			(oVertHandle dispose:)
		)
		(if (and oNScrollExit (!= -1 oNScrollExit))
			(oNScrollExit dispose:)
		)
		(if (and oSScrollExit (!= -1 oSScrollExit))
			(oSScrollExit dispose:)
		)
		(if (and oEScrollExit (!= -1 oEScrollExit))
			(oEScrollExit dispose:)
		)
		(if (and oWScrollExit (!= -1 oWScrollExit))
			(oWScrollExit dispose:)
		)
		(= oHorizHandle
			(= oVertHandle
				(= oNScrollExit
					(= oSScrollExit (= oEScrollExit (= oWScrollExit -1)))
				)
			)
		)
		(super dispose: &rest)
	)
	
	(method (enable)
		(super enable: &rest)
		(if (and oHorizHandle (!= -1 oHorizHandle))
			(oHorizHandle show:)
		)
		(if (and oVertHandle (!= -1 oVertHandle))
			(oVertHandle show:)
		)
		(if (and oNScrollExit (!= -1 oNScrollExit))
			(oNScrollExit show:)
		)
		(if (and oSScrollExit (!= -1 oSScrollExit))
			(oSScrollExit show:)
		)
		(if (and oEScrollExit (!= -1 oEScrollExit))
			(oEScrollExit show:)
		)
		(if (and oWScrollExit (!= -1 oWScrollExit))
			(oWScrollExit show:)
		)
	)
	
	(method (disable)
		(if (and oHorizHandle (!= -1 oHorizHandle))
			(oHorizHandle hide:)
		)
		(if (and oVertHandle (!= -1 oVertHandle))
			(oVertHandle hide:)
		)
		(if (and oNScrollExit (!= -1 oNScrollExit))
			(oNScrollExit hide:)
		)
		(if (and oSScrollExit (!= -1 oSScrollExit))
			(oSScrollExit hide:)
		)
		(if (and oEScrollExit (!= -1 oEScrollExit))
			(oEScrollExit hide:)
		)
		(if (and oWScrollExit (!= -1 oWScrollExit))
			(oWScrollExit hide:)
		)
		(super disable: &rest)
	)
	
	(method (panTo)
		(ego bAutoScroll: 0)
		(super panTo: &rest)
	)
)

(class TorScrollHandle of View
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
		oMyScrollPlane 0
		nLowBound 0
		nHighBound 0
		bXAxis 1
		bImAHog 0
		nMouseStartX 0
		nMouseStartY 0
		nHandleStartX 0
		nHandleStartY 0
	)
	
	(method (init &tmp newPlane)
		((= newPlane (Plane new:))
			picture: -2
			priority: 420
			init:
		)
		(super init: (newPlane getMainCast:) &rest)
		(self forceCursor: (ScriptID 64006 0))
		(newPlane setSize:)
		(newPlane
			moveTo: (- (thePlane right:) 6) (- (thePlane bottom?) 6)
		)
		(oMyScrollPlane addToNotifyList: self 1081)
		(self reSynch:)
	)
	
	(method (dispose &tmp thePlane_2)
		(= thePlane_2 plane)
		(super dispose: &rest)
		(thePlane_2 dispose:)
		(= oMyScrollPlane (= plane 0))
	)
	
	(method (handleEvent event &tmp theNLowBound oMyScrollPlaneNScrollMaxX temp2 temp3)
		(if (not oMyScrollPlane)
			(return
				(Prints
					{Attempt to handleEvent scroll handle with no scroll plane. DJM torscrol.sc}
				)
			)
		)
		(if
			(and
				(self onMe: event)
				(== (event type?) evMOUSEBUTTON)
			)
			(= bImAHog 1)
			(gOEventHandler registerEventHog: self)
			(event globalize:)
			(ego bAutoScroll: 0)
			(= nMouseStartX (event x?))
			(= nMouseStartY (event y?))
			(= nHandleStartX (plane left:))
			(= nHandleStartY (plane top?))
		)
		(if bImAHog
			(if (== (event type?) evMOUSERELEASE)
				(self stopHogging:)
			)
			(event globalize:)
			(if bXAxis
				(if
					(<
						(= theNLowBound
							(+ (- (event x?) nMouseStartX) nHandleStartX)
						)
						nLowBound
					)
					(= theNLowBound nLowBound)
				)
				(if (> theNLowBound nHighBound)
					(= theNLowBound nHighBound)
				)
				(plane moveTo: theNLowBound nHandleStartY)
				(UpdatePlane plane)
				(= temp2 (- nHighBound nLowBound))
				(= theNLowBound (- theNLowBound nLowBound))
				(= oMyScrollPlaneNScrollMaxX
					(oMyScrollPlane nScrollMaxX?)
				)
				(= temp3
					(MulDiv theNLowBound oMyScrollPlaneNScrollMaxX temp2)
				)
				(oMyScrollPlane
					scrollTo:
						(MulDiv theNLowBound oMyScrollPlaneNScrollMaxX temp2)
						(oMyScrollPlane nCurPosY?)
				)
			else
				(if
					(<
						(= theNLowBound
							(+ (- (event y?) nMouseStartY) nHandleStartY)
						)
						nLowBound
					)
					(= theNLowBound nLowBound)
				)
				(if (> theNLowBound nHighBound)
					(= theNLowBound nHighBound)
				)
				(plane moveTo: nHandleStartX theNLowBound)
				(UpdatePlane plane)
				(= temp2 (- nHighBound nLowBound))
				(= theNLowBound (- theNLowBound nLowBound))
				(= oMyScrollPlaneNScrollMaxX
					(oMyScrollPlane nScrollMaxY?)
				)
				(oMyScrollPlane
					scrollTo:
						(oMyScrollPlane nCurPosX?)
						(MulDiv theNLowBound oMyScrollPlaneNScrollMaxX temp2)
				)
			)
			(event claimed: 1)
			(return 1)
		)
		(return (super handleEvent: event &rest))
	)
	
	(method (stopHogging)
		(= bImAHog 0)
		(gOEventHandler unregisterEventHog: self)
	)
	
	(method (reSynch &tmp oMyScrollPlaneNCurPosX oMyScrollPlaneNScrollMaxX temp2)
		(if (not oMyScrollPlane)
			(Prints
				{Attempt to resynch scroll handle with no scroll plane. DJM torscrol.sc}
			)
			(return)
		)
		(= temp2 (- nHighBound nLowBound))
		(if bXAxis
			(= oMyScrollPlaneNCurPosX (oMyScrollPlane nCurPosX?))
			(= oMyScrollPlaneNScrollMaxX
				(oMyScrollPlane nScrollMaxX?)
			)
			(plane
				moveTo:
					(+
						(MulDiv
							oMyScrollPlaneNCurPosX
							temp2
							oMyScrollPlaneNScrollMaxX
						)
						nLowBound
					)
					(plane top?)
			)
			(UpdatePlane plane)
		else
			(= oMyScrollPlaneNCurPosX (oMyScrollPlane nCurPosY?))
			(= oMyScrollPlaneNScrollMaxX
				(oMyScrollPlane nScrollMaxY?)
			)
			(plane
				moveTo:
					(plane left:)
					(+
						(MulDiv
							oMyScrollPlaneNCurPosX
							temp2
							oMyScrollPlaneNScrollMaxX
						)
						nLowBound
					)
			)
			(UpdatePlane plane)
		)
	)
)

(instance oHHandle of TorScrollHandle
	(properties
		view -5532
		loop 18
		nLowBound 20
		nHighBound 620
	)
	
	(method (init)
		(super init: &rest)
		(= nLowBound (+ (thePlane left:) 10))
		(= nHighBound
			(- (thePlane right:) (+ 10 (CelWide view loop cel)))
		)
	)
)

(instance oVHandle of TorScrollHandle
	(properties
		view -5532
		loop 19
		nLowBound 24
		nHighBound 297
		bXAxis 0
	)
	
	(method (init)
		(super init: &rest)
		(= nLowBound (+ (thePlane top?) 12))
		(= nHighBound
			(- (thePlane bottom?) (+ 12 (CelHigh view loop cel)))
		)
	)
)
