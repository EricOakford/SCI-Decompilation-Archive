;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64894)
(include sci.sh)
(use Main)
(use List)
(use TiledBitmap)
(use String)
(use Array)
(use Actor)
(use System)


(class PushButton of View
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
	)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 0))
		(= invSlotsX 0)
		(self invSlotsTot: 0 0)
	)
	
	(method (dispose)
		(if invSlotsX (gOEventHandler screenLocX: self))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (not invSlotsY) (return 0))
		(= temp0 (self onMe: event))
		(cond 
			((== invSlotsX 1)
				(if (!= viewSlotsY temp0) (self invSlotsTot: temp0 1))
				(if viewSlotsY (self addSelfToCursorList:))
				(if (== (event type?) evMOUSERELEASE)
					(gOEventHandler screenLocX: self)
					(= invSlotsX 0)
					(if viewSlotsY (self findFirstOpen:))
				)
				(event claimed: 1)
			)
			((and (== (event type?) evMOUSEBUTTON) temp0)
				(= invSlotsX 1)
				(gOEventHandler screenLocY: self)
				(self invSlotsTot: 1 1)
				(self oMyHandler:)
				(event claimed: 1)
			)
			(else (super handleEvent: event &rest))
		)
		(return (event claimed?))
	)
	
	(method (enable)
		(= invSlotsY 1)
		(self setSpeedFraction: (ScriptID 64006 0))
		(self invSlotsTot: 0 0)
	)
	
	(method (disable)
		(= invSlotsY 0)
		(self setSpeedFraction: 0)
		(self invSlotsTot: 0 0)
		(gOEventHandler screenLocX: self)
	)
	
	(method (oVerbs)
		(if removeItem (Eval removeItem ioMine curSlot))
	)
	
	(method (oMyHandler)
		(if setCurItem (Eval setCurItem getVerbItem))
	)
	
	(method (addSelfToCursorList)
		(if blankID (Eval blankID viewSlotsX))
	)
	
	(method (invSlotsTot theViewSlotsY param2)
		(if theViewSlotsY (= cel 1) else (= cel 0))
		(= viewSlotsY theViewSlotsY)
		(if (not invSlotsY) (= cel 2))
		(if (or (== 1 argc) (and (> 1 argc) param2))
			(UpdateScreenItem self)
			(FrameOut)
		)
	)
	
	(method (findFirstOpen)
		(self invSlotsTot: 0 1)
		(self oVerbs:)
	)
)

(class ToggleButton of PushButton
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
		slotIncX 0
	)
	
	(method (invSlotsTot theViewSlotsY param2)
		(if theViewSlotsY (= cel 1) else (= cel 0))
		(if slotIncX (= cel (+ cel 2)))
		(if (not invSlotsY) (= cel 4))
		(= viewSlotsY theViewSlotsY)
		(if (or (== 1 argc) (and (> 1 argc) param2))
			(UpdateScreenItem self)
			(FrameOut)
		)
	)
	
	(method (findFirstOpen)
		(= slotIncX (not slotIncX))
		(super findFirstOpen: &rest)
	)
	
	(method (slotIncY theSlotIncX)
		(= slotIncX theSlotIncX)
		(self invSlotsTot: viewSlotsY 0)
	)
	
	(method (oAllHandledItems)
		(return slotIncX)
	)
)

(class RadioButton of ToggleButton
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
		slotIncX 0
		oInventItemSlots 0
	)
	
	(method (findFirstOpen)
		(if (not slotIncX)
			(if oInventItemSlots
				(oInventItemSlots curULX: self 885 0)
			)
			(super findFirstOpen: &rest)
		else
			(self invSlotsTot: 0 1)
		)
	)
	
	(method (slotIncY param1)
		(if (and (not slotIncX) oInventItemSlots)
			(oInventItemSlots curULX: self 889 0)
		)
		(self viewSlotsTot: param1)
	)
	
	(method (oScreenSlots theOInventItemSlots)
		(= oInventItemSlots theOInventItemSlots)
	)
	
	(method (viewSlotsTot theSlotIncX)
		(= slotIncX theSlotIncX)
		(self invSlotsTot: viewSlotsY 0)
	)
)

(class RadioGroup of List
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
	)
	
	(method (add)
		(super add: &rest)
		(self eachElementDo: #oScreenSlots self)
	)
	
	(method (curULX param1 param2 &tmp radioGroupFirst temp1)
		(= radioGroupFirst (self first:))
		(while radioGroupFirst
			(if
			(!= (= temp1 (KList 8 radioGroupFirst)) param1)
				(temp1 param2: &rest)
			)
			(= radioGroupFirst (self next: radioGroupFirst))
		)
	)
)

(class TextButton of PushButton
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
	)
	
	(method (init &tmp temp0)
		(self minY: text)
		(super init: &rest)
		(self invSlotsTot: viewSlotsY 0)
	)
	
	(method (dispose)
		(if minDelay (minDelay dispose:) (= minDelay 0))
		(if text
			(if (== text (KString 9 text))
				(KString 4 text)
			else
				(text dispose:)
			)
			(= text 0)
		)
		(if bitmap
			(if maxDelay
				(= bitmap 0)
				(Bitmap 1 maxDelay)
				(= maxDelay 0)
				(Bitmap 1 minX)
				(= minX 0)
			else
				(Bitmap 1 bitmap)
				(= bitmap 0)
			)
		)
		(super dispose: &rest)
	)
	
	(method (invSlotsTot theViewSlotsY param2 &tmp [temp0 3])
		(= viewSlotsY theViewSlotsY)
		(if viewSlotsY (= bitmap minX) else (= bitmap maxDelay))
		(if (or (== 1 argc) (and (> argc 1) param2))
			(UpdateScreenItem self)
			(FrameOut)
		)
	)
	
	(method (maxX &tmp temp0 theBack_2 theFore_2 theFore_3 theBack theFore theSkip tiledBitmapOMyNotifySelectors tiledBitmapAddScrollView temp9 temp10 theCurULY theCurULY_2 temp13)
		(if bitmap
			(if maxDelay
				(= bitmap 0)
				(Bitmap 1 maxDelay)
				(= maxDelay 0)
				(Bitmap 1 minX)
				(= minX 0)
			else
				(Bitmap 1 bitmap)
				(= bitmap 0)
			)
		)
		(= temp0 (IntArray new: 4))
		(KText
			0
			(temp0 data?)
			(minDelay data?)
			font
			bScrollable
			1
		)
		(= temp9 (+ (temp0 at: 2) 1))
		(= temp10 (+ (temp0 at: 3) 1))
		(= oMyNotifySelectors (+ temp9 curULY curULY))
		(= addScrollView (- (+ temp10 curULY curULY) maxCycle))
		(if (> compress oMyNotifySelectors)
			(= temp13 (/ (- compress oMyNotifySelectors) 2))
			(= oMyNotifySelectors compress)
		else
			(= temp13 0)
		)
		(temp0 dispose:)
		(= theFore fore)
		(= theBack back)
		(= theSkip skip)
		(= theBack_2 back)
		(= theFore_2 fore)
		(cond 
			((== skip back) (= theFore_3 fore))
			((== skip fore) (= theFore_3 back))
			(else (= theFore_3 skip))
		)
		(if (!= findNearestOpen -1)
			(if (== back 255)
				(= theBack 254)
				(= theSkip 254)
			else
				(= theBack 255)
				(= theSkip 255)
			)
			(if (== theFore_2 255)
				(= theFore_2 254)
				(= theFore_3 254)
			else
				(= theFore_2 254)
				(= theFore_3 254)
			)
			(if (== updateItemSlot -1)
				(MonoOut
					{both vTile properties must be filled. button.sc djm}
				)
			)
			(TiledBitmap
				init: findNearestOpen oMyNotifySelectors addScrollView 0 minCycle
			)
			(= maxDelay (TiledBitmap cInventory: 1))
			(= tiledBitmapOMyNotifySelectors
				(TiledBitmap oMyNotifySelectors?)
			)
			(= tiledBitmapAddScrollView (TiledBitmap addScrollView?))
			(TiledBitmap
				init: updateItemSlot oMyNotifySelectors addScrollView 0 minCycle
			)
			(= minX (TiledBitmap cInventory: 1))
			(= theCurULY (+ (TiledBitmap killPan?) curULY))
			(= theCurULY_2
				(+ (TiledBitmap addToNotifyList?) curULY)
			)
			(= oMyNotifySelectors (TiledBitmap oMyNotifySelectors?))
			(= addScrollView (TiledBitmap addScrollView?))
			(if
				(or
					(!= oMyNotifySelectors tiledBitmapOMyNotifySelectors)
					(!= addScrollView tiledBitmapAddScrollView)
				)
				(MonoOut
					{the on and off tiles of TextButton are not the same size}
				)
			)
		else
			(= maxDelay
				(Bitmap
					0
					oMyNotifySelectors
					addScrollView
					theSkip
					theBack
					640
					480
				)
			)
			(Bitmap
				5
				maxDelay
				0
				0
				(- oMyNotifySelectors 1)
				(- addScrollView 1)
				theBack
			)
			(= minX
				(Bitmap
					0
					oMyNotifySelectors
					addScrollView
					theFore_3
					theFore_2
					640
					480
				)
			)
			(Bitmap
				5
				minX
				0
				0
				(- oMyNotifySelectors 1)
				(- addScrollView 1)
				theFore_2
			)
			(= theCurULY curULY)
			(= theCurULY_2 curULY)
		)
		(= theCurULY (+ theCurULY temp13))
		(Bitmap
			4
			maxDelay
			(minDelay data?)
			theCurULY
			theCurULY_2
			(- (+ theCurULY temp9) 1)
			(- (+ theCurULY_2 temp10) 1)
			theFore
			theBack
			theSkip
			font
			mode
			theBack
			dimmed
		)
		(Bitmap
			4
			minX
			(minDelay data?)
			theCurULY
			theCurULY_2
			(- (+ theCurULY temp9) 1)
			(- (+ theCurULY_2 temp10) 1)
			theBack_2
			theFore_2
			theFore_3
			font
			mode
			theFore_2
			dimmed
		)
		(= bitmap maxDelay)
	)
	
	(method (minY param1)
		(if minDelay (minDelay dispose:))
		(if (and argc param1)
			(= minDelay (Str with: (KString 9 param1)))
		else
			(= minDelay (Str with: {}))
		)
		(self maxX:)
	)
)

(class MessageButton of TextButton
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
		verb 0
		seq 1
		testHotspotVerb -1
	)
	
	(method (init)
		(if (== testHotspotVerb -1)
			(= testHotspotVerb curRoomNum)
		)
		(if text (text dispose:))
		(= text (Str newWith: 100 {}))
		(if
			(not
				(Message
					0
					testHotspotVerb
					noun
					verb
					case
					seq
					(text data?)
				)
			)
			(MonoOut
				{cannot find message: %d %d %d %d %d}
				testHotspotVerb
				noun
				verb
				case
				seq
			)
			(text
				format: {msg: %d %d %d %d %d} testHotspotVerb noun verb case seq
			)
		)
		(super init: &rest)
	)
)
