;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64033)
(include sci.sh)
(use Main)
(use Script)
(use TiledBitmap)
(use ModalPlane)
(use Button)
(use String)
(use Array)
(use Actor)
(use System)

(public
	TextDialog 0
	MakeMessageText 1
	SetDialogFont 2
	SetButtonFont 3
	SetDialogColors 4
	proc64033_5 5
	proc64033_6 6
	GetTextWidth 7
	proc64033_8 8
)

(procedure (TextDialog param1 param2 param3 param4 param5 &tmp temp0 temp1 temp2 newTextButtonOMyNotifySelectors newTextButtonAddScrollView newLOOKUP_ERROR_2OMyNotifySelectors newLOOKUP_ERROR_2AddScrollView temp7 temp8 newLOOKUP_ERROR newTiledView newLOOKUP_ERROR_2 newTiledViewOMyNotifySelectors newTiledViewAddScrollView newTiledViewKillPan newTiledViewAddToNotifyList temp16 temp17 newTextButton)
	(if (< argc 2) (return (MonoOut LOOKUP_ERROR)))
	(if (< argc 3) (= temp1 -2) else (= temp1 param3))
	(if (< argc 4) (= temp2 -2) else (= temp2 param4))
	(if (< argc 5) (= temp0 300) else (= temp0 param5))
	(if (or (not param1) (not param2)) (return 0))
	((= newLOOKUP_ERROR (LOOKUP_ERROR new:)) init: 0 0 1 1)
	(= newTextButton (TextButton new:))
	(newTextButton
		font: global268
		maxCycle: global269
		bScrollable: temp0
		fore: global274
		back: 255
		skip: 255
		text: (KString 8 (KString 9 param1))
		curULY: 0
		minCycle: 0
		init: newLOOKUP_ERROR
		disable:
	)
	(= newTextButtonOMyNotifySelectors
		(newTextButton oMyNotifySelectors?)
	)
	(= newTextButtonAddScrollView
		(newTextButton addScrollView?)
	)
	(= newLOOKUP_ERROR_2 (LOOKUP_ERROR new:))
	(newLOOKUP_ERROR_2
		font: global270
		maxCycle: global271
		fore: gFore
		back: gBack
		text: (KString 8 (KString 9 param2))
		minCycle: 1
		findNearestOpen: global276
		updateItemSlot: global277
		setPos: 1
		init: newLOOKUP_ERROR
	)
	(= newLOOKUP_ERROR_2OMyNotifySelectors
		(newLOOKUP_ERROR_2 oMyNotifySelectors?)
	)
	(= newLOOKUP_ERROR_2AddScrollView
		(newLOOKUP_ERROR_2 addScrollView?)
	)
	(= temp7
		(+
			(Max
				newTextButtonOMyNotifySelectors
				newLOOKUP_ERROR_2OMyNotifySelectors
			)
			10
		)
	)
	(= temp8
		(+
			newTextButtonAddScrollView
			newLOOKUP_ERROR_2AddScrollView
			15
		)
	)
	((= newTiledView (TiledView new:))
		view: global275
		init: temp7 temp8 0 1 newLOOKUP_ERROR
	)
	(= newTiledViewOMyNotifySelectors
		(newTiledView oMyNotifySelectors?)
	)
	(= newTiledViewAddScrollView
		(newTiledView addScrollView?)
	)
	(switch temp1
		(-1
			(= temp1
				(/ (- screenWidth newTiledViewOMyNotifySelectors) 2)
			)
		)
		(-2
			(= temp1
				(+
					(thePlane left:)
					(/
						(- (thePlane findData:) newTiledViewOMyNotifySelectors)
						2
					)
				)
			)
		)
	)
	(switch temp2
		(-1
			(= temp2
				(/ (- screenHeight newTiledViewAddScrollView) 2)
			)
		)
		(-2
			(= temp2
				(+
					(thePlane top?)
					(/ (- (thePlane doDouble:) newTiledViewAddScrollView) 2)
				)
			)
		)
	)
	(newLOOKUP_ERROR
		setRect:
			temp1
			temp2
			(- (+ temp1 newTiledViewOMyNotifySelectors) 1)
			(- (+ temp2 newTiledViewAddScrollView) 1)
	)
	(UpdatePlane newLOOKUP_ERROR)
	(= newTiledViewKillPan (newTiledView killPan?))
	(= newTiledViewAddToNotifyList
		(newTiledView addToNotifyList?)
	)
	(newTextButton
		posn: (+ newTiledViewKillPan 5) (+ newTiledViewAddToNotifyList 5)
	)
	(= temp16
		(/
			(-
				newTiledViewOMyNotifySelectors
				newLOOKUP_ERROR_2OMyNotifySelectors
			)
			2
		)
	)
	(= temp17
		(+
			newTiledViewAddToNotifyList
			10
			newTextButtonAddScrollView
		)
	)
	(newLOOKUP_ERROR_2 posn: temp16 temp17)
	(return (newLOOKUP_ERROR setMinMax:))
)

(procedure (MakeMessageText param1 param2 param3 param4 theTheCurRoomNum &tmp temp0 temp1 temp2 temp3 theCurRoomNum temp5)
	(if (< argc 1) (= temp0 0) else (= temp0 param1))
	(if (< argc 2) (= temp1 0) else (= temp1 param2))
	(if (< argc 3) (= temp2 0) else (= temp2 param3))
	(if (< argc 4) (= temp3 1) else (= temp3 param4))
	(if (< argc 5)
		(= theCurRoomNum curRoomNum)
	else
		(= theCurRoomNum theTheCurRoomNum)
	)
	(= temp5 (Str newWith: 4000 LOOKUP_ERROR))
	(if
	(not (Message 0 theCurRoomNum temp0 temp1 temp2 temp3))
		(MonoOut
			LOOKUP_ERROR
			theCurRoomNum
			temp0
			temp1
			temp2
			temp3
		)
		(return 0)
	)
	(Message
		0
		theCurRoomNum
		temp0
		temp1
		temp2
		temp3
		(temp5 data?)
	)
	(return temp5)
)

(procedure (SetDialogFont param1 param2)
	(if (< argc 2) (MonoOut LOOKUP_ERROR) (return))
	(= global268 param1)
	(= global269 param2)
)

(procedure (SetButtonFont param1 param2)
	(if (< argc 2) (MonoOut LOOKUP_ERROR) (return))
	(= global270 param1)
	(= global271 param2)
)

(procedure (SetDialogColors param1 theGFore theGBack)
	(if (< argc 3) (MonoOut LOOKUP_ERROR) (return))
	(= global274 param1)
	(= gFore theGFore)
	(= gBack theGBack)
)

(procedure (proc64033_5 param1 param2 param3)
	(if (< argc 3) (MonoOut LOOKUP_ERROR) (return))
	(= global275 param1)
	(= global276 param2)
	(= global277 param3)
)

(procedure (proc64033_6 param1 param2 param3 param4 param5 param6 &tmp temp0 temp1 temp2 newTextButtonOMyNotifySelectors newTextButtonAddScrollView newLOOKUP_ERROR_3OMyNotifySelectors newLOOKUP_ERROR_3AddScrollView temp7 temp8 newLOOKUP_ERROR newTiledView newLOOKUP_ERROR_2 newLOOKUP_ERROR_3 newTiledViewOMyNotifySelectors newTiledViewAddScrollView newTiledViewKillPan newTiledViewAddToNotifyList temp17 temp18 newTextButton temp20)
	(if (< argc 3) (return (MonoOut LOOKUP_ERROR)))
	(if (< argc 4) (= temp1 -2) else (= temp1 param4))
	(if (< argc 5) (= temp2 -2) else (= temp2 param5))
	(if (< argc 6) (= temp0 300) else (= temp0 param6))
	(if (or (not param1) (not param2) (not param3))
		(return 0)
	)
	((= newLOOKUP_ERROR (LOOKUP_ERROR new:)) init: 0 0 1 1)
	(= newTextButton (TextButton new:))
	(newTextButton
		font: global268
		maxCycle: global269
		bScrollable: temp0
		fore: global274
		back: 255
		skip: 255
		text: (KString 8 (KString 9 param1))
		curULY: 0
		minCycle: 0
		init: newLOOKUP_ERROR
		disable:
	)
	(= newTextButtonOMyNotifySelectors
		(newTextButton oMyNotifySelectors?)
	)
	(= newTextButtonAddScrollView
		(newTextButton addScrollView?)
	)
	(= temp20
		(+
			(= temp20
				(Max
					(GetTextWidth param2 global270 0)
					(GetTextWidth param3 global270 0)
				)
			)
			10
		)
	)
	(= newLOOKUP_ERROR_2 (LOOKUP_ERROR new:))
	(newLOOKUP_ERROR_2
		font: global270
		maxCycle: global271
		fore: gFore
		back: gBack
		text: (KString 8 (KString 9 param2))
		minCycle: 1
		findNearestOpen: global276
		updateItemSlot: global277
		compress: temp20
		value: 1
		setPos: 1
		init: newLOOKUP_ERROR
	)
	(= newLOOKUP_ERROR_3 (LOOKUP_ERROR new:))
	(newLOOKUP_ERROR_3
		font: global270
		maxCycle: global271
		fore: gFore
		back: gBack
		text: (KString 8 (KString 9 param3))
		minCycle: 1
		findNearestOpen: global276
		updateItemSlot: global277
		compress: temp20
		value: 0
		init: newLOOKUP_ERROR
	)
	(= newLOOKUP_ERROR_3OMyNotifySelectors
		(newLOOKUP_ERROR_3 oMyNotifySelectors?)
	)
	(= newLOOKUP_ERROR_3AddScrollView
		(newLOOKUP_ERROR_3 addScrollView?)
	)
	(= temp7
		(+
			(Max
				newTextButtonOMyNotifySelectors
				(+
					newLOOKUP_ERROR_3OMyNotifySelectors
					5
					newLOOKUP_ERROR_3OMyNotifySelectors
				)
			)
			10
		)
	)
	(= temp8
		(+
			newTextButtonAddScrollView
			newLOOKUP_ERROR_3AddScrollView
			15
		)
	)
	((= newTiledView (TiledView new:))
		view: global275
		init: temp7 temp8 0 1 newLOOKUP_ERROR
	)
	(= newTiledViewOMyNotifySelectors
		(newTiledView oMyNotifySelectors?)
	)
	(= newTiledViewAddScrollView
		(newTiledView addScrollView?)
	)
	(switch temp1
		(-1
			(= temp1
				(/ (- screenWidth newTiledViewOMyNotifySelectors) 2)
			)
		)
		(-2
			(= temp1
				(+
					(thePlane left:)
					(/
						(- (thePlane findData:) newTiledViewOMyNotifySelectors)
						2
					)
				)
			)
		)
	)
	(switch temp2
		(-1
			(= temp2
				(/ (- screenHeight newTiledViewAddScrollView) 2)
			)
		)
		(-2
			(= temp2
				(+
					(thePlane top?)
					(/ (- (thePlane doDouble:) newTiledViewAddScrollView) 2)
				)
			)
		)
	)
	(newLOOKUP_ERROR
		setRect:
			temp1
			temp2
			(- (+ temp1 newTiledViewOMyNotifySelectors) 1)
			(- (+ temp2 newTiledViewAddScrollView) 1)
	)
	(UpdatePlane newLOOKUP_ERROR)
	(= newTiledViewKillPan (newTiledView killPan?))
	(= newTiledViewAddToNotifyList
		(newTiledView addToNotifyList?)
	)
	(newTextButton
		posn: (+ newTiledViewKillPan 5) (+ newTiledViewAddToNotifyList 5)
	)
	(= temp17
		(/
			(-
				newTiledViewOMyNotifySelectors
				(+
					newLOOKUP_ERROR_3OMyNotifySelectors
					newLOOKUP_ERROR_3OMyNotifySelectors
					5
				)
			)
			2
		)
	)
	(= temp18
		(+
			newTiledViewAddToNotifyList
			10
			newTextButtonAddScrollView
		)
	)
	(newLOOKUP_ERROR_2 posn: temp17 temp18)
	(newLOOKUP_ERROR_3
		posn: (+ temp17 newLOOKUP_ERROR_3OMyNotifySelectors 5) temp18
	)
	(return (newLOOKUP_ERROR setMinMax:))
)

(procedure (GetTextWidth param1 param2 param3 &tmp temp0 temp1)
	(if (< argc 3) (MonoOut LOOKUP_ERROR) (return 0))
	(= temp0 (IntArray new: 4))
	(KText
		0
		(temp0 data?)
		(KString 9 param1)
		param2
		param3
		1
	)
	(= temp1 (+ (temp0 at: 2) 1))
	(temp0 dispose:)
	(return temp1)
)

(procedure (proc64033_8 param1 param2 param3 &tmp temp0 temp1)
	(if (< argc 3) (MonoOut LOOKUP_ERROR) (return 0))
	(= temp0 (IntArray new: 4))
	(KText
		0
		(temp0 data?)
		(KString 9 param1)
		param2
		param3
		1
	)
	(= temp1 (+ (temp0 at: 3) 1))
	(temp0 dispose:)
	(return temp1)
)

(instance poNull of Prop
	(properties)
)

(class DismissButton of TextButton
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
		removeItem 0
		ioMine 867
		curSlot 0
		setCurItem 0
		getVerbItem 868
		blankID 0
		viewSlotsX 869
		viewSlotsY 0
		invSlotsX 0
		invSlotsY 1
		text 0
		fore 0
		back 254
		skip 254
		dimmed 0
		font 0
		mode 0
		curULY 3
		bScrollable 0
		compress 0
		findNearestOpen -1
		updateItemSlot -1
		minCycle 1
		maxCycle 2
		oMyNotifySelectors 0
		addScrollView 0
		minDelay 0
		maxDelay 0
		minX 0
		value 0
		setPos 0
		getPos 1
	)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler setIncrement: self)
		(if (or setPos getPos) (gOEventHandler scrolled: self))
	)
	
	(method (dispose)
		(gOEventHandler pageUp: self)
		(gOEventHandler pageDown: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_ESCAPE)
				getPos
			)
			(event claimed: 1)
			(self oVerbs:)
			(return 1)
		)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_RETURN)
				setPos
			)
			(event claimed: 1)
			(self oVerbs:)
			(return 1)
		)
		(return (super handleEvent: event &rest))
	)
	
	(method (oVerbs)
		(plane value: value)
		(LOOKUP_ERROR setScript: (LOOKUP_ERROR new:) 0 plane)
	)
)

(instance soDelayDispose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(register dispose:)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(class DialogPlane of ModalPlane
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
		picture -2
		style $0000
		priority 610
		back 0
		bitmap 0
		casts 0
		mirrored 0
		nScreenSizeX 0
		arrowUp 0
		value 0
	)
	
	(method (dispose)
		(if arrowUp (= arrowUp 0) (return))
		(super dispose: &rest)
	)
	
	(method (setMinMax &tmp temp0 theValue)
		(= arrowUp 1)
		(while arrowUp
			(= temp0 ((user curEvent?) new:))
			(gOEventHandler handleEvent: temp0)
			(temp0 dispose:)
			((self nSpeed:) doit:)
			(if (LOOKUP_ERROR script?)
				((LOOKUP_ERROR script?) doit:)
			)
			(FrameOut)
		)
		(= theValue value)
		(self dispose:)
		(return theValue)
	)
)
