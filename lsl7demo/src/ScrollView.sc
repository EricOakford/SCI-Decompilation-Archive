;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64891)
(include sci.sh)
(use Main)
(use Plane)
(use Print)
(use Actor)
(use System)


(class ScrollView of View
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
		nScrollSpeedX 0
		nScrollSpeedY 0
		nScrollDenomX 0
		nScrollDenomY 0
		nOriginX 0
		nOriginY 0
		oMyPlane 0
	)
	
	(method (setScreenSpeed param1 param2 &tmp temp0 temp1 oMyPlaneNScrollMaxX oMyPlaneNScrollMaxY)
		(if oMyPlane
			(if argc (= temp0 param1) else (= temp0 0))
			(if (> argc 1) (= temp1 param2) else (= temp1 0))
			(= oMyPlaneNScrollMaxX (oMyPlane nScrollMaxX?))
			(= oMyPlaneNScrollMaxY (oMyPlane nScrollMaxY?))
			(= nScrollSpeedX (- oMyPlaneNScrollMaxX temp0))
			(= nScrollSpeedY (- oMyPlaneNScrollMaxY temp1))
			(if (== temp0 0) (= nScrollDenomX (= nScrollSpeedX 1)))
			(if (== temp1 0) (= nScrollDenomY (= nScrollSpeedY 1)))
		)
		(= nOriginX x)
		(= nOriginY y)
	)
	
	(method (setSpeedFraction param1 param2 param3 &tmp oMyPlaneNScrollMaxX oMyPlaneNScrollMaxY)
		(if (and oMyPlane (> argc 2))
			(cond 
				((== param1 0)
					(= nScrollSpeedX
						(-
							(= oMyPlaneNScrollMaxX (oMyPlane nScrollMaxX?))
							(/ (* oMyPlaneNScrollMaxX param2) param3)
						)
					)
				)
				((== param1 1)
					(= nScrollSpeedY
						(-
							(= oMyPlaneNScrollMaxY (oMyPlane nScrollMaxY?))
							(/ (* oMyPlaneNScrollMaxY param2) param3)
						)
					)
				)
			)
		)
		(= nOriginX x)
		(= nOriginY y)
	)
	
	(method (setVisibleRange param1 param2 param3 &tmp temp0 oMyPlaneNScrollMaxX oMyPlaneNScrollMaxY oMyPlaneNScreenSizeX oMyPlaneNScreenSizeY temp5 temp6 temp7 temp8)
		(if (> param2 param3)
			(= temp0 param2)
			(= param2 param3)
			(= param3 temp0)
		)
		(if (and oMyPlane (> argc 1))
			(cond 
				((== param1 0)
					(= temp5 (CelWide view loop cel))
					(= oMyPlaneNScrollMaxX (oMyPlane nScrollMaxX?))
					(= oMyPlaneNScreenSizeX (oMyPlane nScreenSizeX?))
					(= temp7 (- param3 param2))
					(= nScrollSpeedX
						(/
							(*
								oMyPlaneNScrollMaxX
								(= temp8
									(- (- param3 temp5) (+ param2 oMyPlaneNScreenSizeX))
								)
							)
							temp7
						)
					)
					(= nScrollSpeedX (>> nScrollSpeedX $0004))
					(= nScrollDenomX (>> oMyPlaneNScrollMaxX $0004))
					(= x
						(- param3 (/ (* nScrollSpeedX param3) nScrollDenomX))
					)
					(UpdateScreenItem self)
					(SetNowSeen self)
				)
				((== param1 1)
					(= temp6 (CelHigh view loop cel))
					(= oMyPlaneNScrollMaxY (oMyPlane nScrollMaxY?))
					(= oMyPlaneNScreenSizeY (oMyPlane nScreenSizeY?))
					(= temp7 (- param3 param2))
					(= temp8
						(- (- param3 temp6) (+ param2 oMyPlaneNScreenSizeY))
					)
					(= nScrollSpeedY
						(- 0 (/ (* oMyPlaneNScrollMaxY temp8) temp7))
					)
					(= nScrollSpeedY (>> nScrollSpeedY $0004))
					(= nScrollDenomY (>> oMyPlaneNScrollMaxY $0004))
					(= y
						(- param3 (/ (* nScrollSpeedY param3) nScrollDenomY))
					)
					(UpdateScreenItem self)
					(SetNowSeen self)
				)
			)
		)
		(= nOriginX x)
		(= nOriginY y)
	)
	
	(method (setTotalWidth param1 param2 &tmp oMyPlaneNScrollMaxX oMyPlaneNScrollMaxY oMyPlaneNScreenSizeX oMyPlaneNScreenSizeY)
		(if (and oMyPlane (> argc 1))
			(cond 
				((== param1 0)
					(= oMyPlaneNScrollMaxX (oMyPlane nScrollMaxX?))
					(= oMyPlaneNScreenSizeX (oMyPlane nScreenSizeX?))
					(= nScrollSpeedX
						(- oMyPlaneNScrollMaxX (- param2 oMyPlaneNScreenSizeX))
					)
				)
				((== param1 1)
					(= oMyPlaneNScrollMaxY (oMyPlane nScrollMaxY?))
					(= oMyPlaneNScreenSizeY (oMyPlane nScreenSizeY?))
					(= nScrollSpeedY
						(- oMyPlaneNScrollMaxY (- param2 oMyPlaneNScreenSizeY))
					)
				)
			)
		)
		(= nOriginX x)
		(= nOriginY y)
	)
	
	(method (setSpeedDirect theNScrollSpeedX theNScrollSpeedY)
		(= nScrollSpeedX theNScrollSpeedX)
		(= nScrollSpeedY theNScrollSpeedY)
		(= nOriginX x)
		(= nOriginY y)
	)
	
	(method (reposition &tmp oMyPlaneNCurPosX oMyPlaneNScrollMaxY oMyPlaneNScrollMaxX)
		(if oMyPlane
			(if nScrollSpeedX
				(= oMyPlaneNCurPosX (oMyPlane nCurPosX?))
				(= oMyPlaneNScrollMaxX (oMyPlane nScrollMaxX?))
				(if (not nScrollDenomX)
					(= nScrollDenomX oMyPlaneNScrollMaxX)
				)
				(= x
					(+
						nOriginX
						(MulDiv nScrollSpeedX oMyPlaneNCurPosX nScrollDenomX)
					)
				)
				(UpdateScreenItem self)
				(SetNowSeen self)
			)
			(if nScrollSpeedY
				(= oMyPlaneNCurPosX (oMyPlane nCurPosY?))
				(= oMyPlaneNScrollMaxY (oMyPlane nScrollMaxY?))
				(if (not nScrollDenomY)
					(= nScrollDenomY oMyPlaneNScrollMaxY)
				)
				(= y
					(+
						nOriginY
						(MulDiv nScrollSpeedY oMyPlaneNCurPosX nScrollDenomY)
					)
				)
				(UpdateScreenItem self)
				(SetNowSeen self)
			)
		)
	)
)

(class Panner of Code
	(properties
		scratch 0
		nSpeed 0
		nStepX 0
		nStepY 0
		nXTarget 0
		nYTarget 0
		oPlane 0
		nSaveTime 0
		oCuee 0
	)
	
	(method (init theNXTarget theNYTarget theOCuee theNSpeed theNStepX theNStepY theOPlane)
		(if (or (< argc 4) (not theNSpeed))
			(= nSpeed (ego moveSpeed?))
		else
			(= nSpeed theNSpeed)
		)
		(if (or (< argc 5) (not theNStepX))
			(= nStepX (ego xStep?))
		else
			(= nStepX theNStepX)
		)
		(if (or (< argc 6) (not theNStepY))
			(= nStepY (ego yStep?))
		else
			(= nStepY theNStepY)
		)
		(if (or (< argc 7) (not theOPlane))
			(= oPlane (curRoom plane?))
		else
			(= oPlane theOPlane)
		)
		(if (< argc 3) (= oCuee 0) else (= oCuee theOCuee))
		(if
			(or
				(< argc 2)
				(not oPlane)
				(not (oPlane isKindOf: ScrollPlane))
			)
			(Prints {Improper init of Panner. Scrlplan.sc djm})
		)
		(= nXTarget theNXTarget)
		(= nYTarget theNYTarget)
		(if (== nXTarget -32000)
			(= nXTarget (oPlane nCurPosX?))
		)
		(if (< nXTarget 0) (= nXTarget 0))
		(if (> nXTarget (oPlane nScrollMaxX?))
			(= nXTarget (oPlane nScrollMaxX?))
		)
		(if (== nYTarget -32000)
			(= nYTarget (oPlane nCurPosY?))
		)
		(if (< nYTarget 0) (= nYTarget 0))
		(if (> nYTarget (oPlane nScrollMaxY?))
			(= nYTarget (oPlane nScrollMaxY?))
		)
		(= nSaveTime gameTime)
		(theDoits add: self)
	)
	
	(method (doit &tmp oPlaneNCurPosX oPlaneNCurPosY temp2 temp3 temp4 temp5 temp6 [temp7 2] temp9 temp10 theOCuee)
		(if (== nSpeed 0) (= nSpeed 1))
		(if (>= (= temp6 (- gameTime nSaveTime)) nSpeed)
			(= temp9 (/ (* nStepX temp6) nSpeed))
			(= temp10 (/ (* nStepY temp6) nSpeed))
			(= oPlaneNCurPosX (oPlane nCurPosX?))
			(= oPlaneNCurPosY (oPlane nCurPosY?))
			(if
				(and
					(== oPlaneNCurPosX nXTarget)
					(== oPlaneNCurPosY nYTarget)
				)
				(= theOCuee oCuee)
				(oPlane oPanner: 0)
				(self dispose:)
				(if theOCuee (theOCuee cue:))
				(return)
			)
			(= temp2 (- nXTarget oPlaneNCurPosX))
			(if (< (= temp3 (- nYTarget oPlaneNCurPosY)) 0)
				(= temp5 -1)
			else
				(= temp5 1)
			)
			(if (< temp2 0) (= temp4 -1) else (= temp4 1))
			(= temp2 (Abs temp2))
			(= temp3 (Abs temp3))
			(if (< temp2 temp9)
				(= oPlaneNCurPosX (+ oPlaneNCurPosX (* temp2 temp4)))
			else
				(= oPlaneNCurPosX (+ oPlaneNCurPosX (* temp9 temp4)))
			)
			(if (< temp3 temp10)
				(= oPlaneNCurPosY (+ oPlaneNCurPosY (* temp3 temp5)))
			else
				(= oPlaneNCurPosY (+ oPlaneNCurPosY (* temp10 temp5)))
			)
			(oPlane scrollTo: oPlaneNCurPosX oPlaneNCurPosY)
			(= nSaveTime gameTime)
		)
	)
	
	(method (dispose)
		(theDoits delete: self)
		(super dispose: &rest)
	)
)

(class ScrollPlane of Plane
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
	)
	
	(method (init param1 param2 &tmp temp0)
		(super init: &rest)
		(if (not nScreenSizeX)
			(= nScreenSizeX (thePlane getWidth:))
			(= nScreenSizeY (thePlane getHeight:))
			(= nScreenOrgX (thePlane left:))
			(= nScreenOrgY (thePlane top?))
		)
		(self
			setRect:
				nScreenOrgX
				nScreenOrgY
				(- (+ nScreenOrgX param1) 1)
				(- (+ nScreenOrgY param2) 1)
		)
		(= nScrollMaxX (- param1 nScreenSizeX))
		(= nScrollMaxY (- param2 nScreenSizeY))
		(self addPics: addViews:)
		(if oMyScrollViews
			(oMyScrollViews eachElementDo: #init (self getMainCast:))
		)
		(= nCurPosX -1)
		(self scrollTo: 0 0)
		(= gOScrollPlane self)
	)
	
	(method (dispose)
		(= gOScrollPlane 0)
		(if oMyScrollViews
			(oMyScrollViews release:)
			(oMyScrollViews dispose:)
			(= oMyScrollViews 0)
		)
		(if oMyNotifyObjs
			(oMyNotifyObjs release:)
			(oMyNotifyObjs dispose:)
			(= oMyNotifyObjs 0)
		)
		(if oMyNotifySelectors
			(oMyNotifySelectors release:)
			(oMyNotifySelectors dispose:)
			(= oMyNotifySelectors 0)
		)
		(self killPan:)
		(super dispose: &rest)
	)
	
	(method (replay)
		(self addPics:)
	)
	
	(method (addScrollView param1)
		(if (not oMyScrollViews) (= oMyScrollViews (Set new:)))
		(oMyScrollViews add: (param1 oMyPlane: self yourself:))
	)
	
	(method (scroll param1 param2 &tmp temp0 temp1)
		(if argc (= temp0 param1) else (= temp0 0))
		(if (> argc 1) (= temp1 param2) else (= temp1 0))
		(self scrollTo: (+ temp0 nCurPosX) (+ temp1 nCurPosY))
	)
	
	(method (scrollTo theTheNScrollMaxX theTheNScrollMaxY &tmp theNScrollMaxX theNScrollMaxY oMyNotifyObjsFirst oMyNotifySelectorsFirst temp4 temp5 scrollPlaneGetWidth scrollPlaneGetHeight)
		(if argc
			(= theNScrollMaxX theTheNScrollMaxX)
		else
			(= theNScrollMaxX 0)
		)
		(if (> argc 1)
			(= theNScrollMaxY theTheNScrollMaxY)
		else
			(= theNScrollMaxY 0)
		)
		(if (> theNScrollMaxX nScrollMaxX)
			(= theNScrollMaxX nScrollMaxX)
		)
		(if (< theNScrollMaxX 0) (= theNScrollMaxX 0))
		(if (> theNScrollMaxY nScrollMaxY)
			(= theNScrollMaxY nScrollMaxY)
		)
		(if (< theNScrollMaxY 0) (= theNScrollMaxY 0))
		(if
			(and
				(== nCurPosX theNScrollMaxX)
				(== nCurPosY theNScrollMaxY)
			)
			(return)
		)
		(= nCurPosX theNScrollMaxX)
		(= nCurPosY theNScrollMaxY)
		(= scrollPlaneGetWidth (self getWidth:))
		(= scrollPlaneGetHeight (self getHeight:))
		(= top (- nScreenOrgY nCurPosY))
		(= left (- nScreenOrgX nCurPosX))
		(self
			setRect:
				left
				top
				(- (+ left scrollPlaneGetWidth) 1)
				(- (+ top scrollPlaneGetHeight) 1)
		)
		(if oMyScrollViews
			(oMyScrollViews eachElementDo: #reposition)
		)
		(UpdatePlane self)
		(if
		(or (not oMyNotifyObjs) (not oMyNotifySelectors))
			(return)
		)
		(= oMyNotifyObjsFirst (oMyNotifyObjs first:))
		(= oMyNotifySelectorsFirst (oMyNotifySelectors first:))
		(while (and oMyNotifyObjsFirst oMyNotifySelectorsFirst)
			(= temp4 (KList 8 oMyNotifyObjsFirst))
			(= temp5 (KList 8 oMyNotifySelectorsFirst))
			(if (and temp4 temp5) (Eval temp4 temp5))
			(= oMyNotifyObjsFirst
				(oMyNotifyObjs next: oMyNotifyObjsFirst)
			)
			(= oMyNotifySelectorsFirst
				(oMyNotifyObjs next: oMyNotifySelectorsFirst)
			)
		)
	)
	
	(method (scrollToObj param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
		(if (!= (param1 plane?) self)
			(Prints
				{Attempt to scroll to object not on our plane. DJM scrlplan.sc}
			)
		)
		(if (< argc 2) (= temp0 0) else (= temp0 param2))
		(if (and (self isObjVisible: param1) (not temp0))
			(return)
		)
		(= temp1 (/ nScreenSizeX 2))
		(= temp2 (/ nScreenSizeY 2))
		(param1 normalizeRect:)
		(= temp3
			(+
				(param1 nsLeft?)
				(/ (- (param1 nsRight?) (param1 nsLeft?)) 2)
			)
		)
		(= temp4
			(+
				(param1 nsTop?)
				(/ (- (param1 nsBottom?) (param1 nsTop?)) 2)
			)
		)
		(= temp3 (- temp3 nCurPosX))
		(= temp4 (- temp4 nCurPosY))
		(= temp5 (- temp3 temp1))
		(= temp6 (- temp4 temp2))
		(self scroll: temp5 temp6)
	)
	
	(method (scrollToLoc param1 param2 param3 &tmp temp0 temp1 temp2 temp3 temp4)
		(if (< argc 2) (= temp0 0) else (= temp0 param3))
		(if
		(and (self isLocVisible: param1 param2) (not temp0))
			(return)
		)
		(= temp1 (/ nScreenSizeX 2))
		(= temp2 (/ nScreenSizeY 2))
		(= param1 (- param1 nCurPosX))
		(= param2 (- param2 nCurPosY))
		(= temp3 (- param1 temp1))
		(= temp4 (- param2 temp2))
		(self scroll: temp3 temp4)
	)
	
	(method (panTo param1 param2 param3 theEgoMoveSpeed theEgoXStep theEgoYStep &tmp egoMoveSpeed egoXStep egoYStep temp3)
		(if (or (< argc 4) (not theEgoMoveSpeed))
			(= egoMoveSpeed (ego moveSpeed?))
		else
			(= egoMoveSpeed theEgoMoveSpeed)
		)
		(if (or (< argc 5) (not theEgoXStep))
			(= egoXStep (ego xStep?))
		else
			(= egoXStep theEgoXStep)
		)
		(if (or (< argc 6) (not theEgoYStep))
			(= egoYStep (ego yStep?))
		else
			(= egoYStep theEgoYStep)
		)
		(if (< argc 3) (= temp3 0) else (= temp3 param3))
		(if (< argc 2)
			(Prints {Improper call to panTo. Scrlplan.sc djm})
		)
		(self killPan:)
		(= oPanner
			((Panner new:)
				init: param1 param2 temp3 egoMoveSpeed egoXStep egoYStep self
				yourself:
			)
		)
	)
	
	(method (killPan)
		(if oPanner (oPanner dispose:) (= oPanner 0))
	)
	
	(method (addToNotifyList param1 param2)
		(if (not oMyNotifyObjs)
			(= oMyNotifyObjs (List new:))
			(= oMyNotifySelectors (List new:))
		)
		(oMyNotifyObjs add: param1)
		(oMyNotifySelectors add: param2)
	)
	
	(method (canScrollUp)
		(return (if (> nCurPosY 0) (return 1) else (return 0)))
	)
	
	(method (canScrollDown)
		(return (if (< nCurPosY nScrollMaxY) (return 1) else (return 0)))
	)
	
	(method (canScrollLeft)
		(return (if (> nCurPosX 0) (return 1) else (return 0)))
	)
	
	(method (canScrollRight)
		(return (if (< nCurPosX nScrollMaxX) (return 1) else (return 0)))
	)
	
	(method (getLocalX param1)
		(return (- (param1 x?) nCurPosX))
	)
	
	(method (getLocalY param1)
		(return (- (param1 y?) nCurPosY))
	)
	
	(method (isObjVisible param1)
		(if (< (- (param1 nsLeft?) nCurPosX) 0) (return 0))
		(if (< (- (param1 nsTop?) nCurPosY) 0) (return 0))
		(if
		(> (- (param1 nsRight?) nCurPosX) nScreenSizeX)
			(return 0)
		)
		(if
		(> (- (param1 nsBottom?) nCurPosY) nScreenSizeY)
			(return 0)
		)
		(return 1)
	)
	
	(method (isLocVisible param1 param2)
		(if (< (- param1 nCurPosX) 0) (return 0))
		(if (< (- param2 nCurPosY) 0) (return 0))
		(if (> (- param1 nCurPosX) nScreenSizeX) (return 0))
		(if (> (- param2 nCurPosY) nScreenSizeY) (return 0))
		(return 1)
	)
	
	(method (addPics)
	)
	
	(method (addViews)
	)
)
