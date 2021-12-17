;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64894)
(include sci.sh)
(use Main)
(use TiledBitmap)
(use GenDialog)
(use DEdit)
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
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oClickNotify 0
		nClickMethod 756
		oHeldNotify 0
		nHeldMethod 757
		bIsHighlighted 0
		bImAHog 0
		bEnabled 1
	)
	
	(method (init)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
		(= bImAHog 0)
		(self drawButton: 0 0)
	)
	
	(method (dispose)
		(if bImAHog (gOEventHandler unregisterEventHog: self))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (not bEnabled) (return 0))
		(= temp0 (self onMe: event))
		(cond 
			((== bImAHog 1)
				(if (!= bIsHighlighted temp0)
					(self drawButton: temp0 1)
				)
				(if bIsHighlighted (self doHeld:))
				(if (== (event type?) evMOUSERELEASE)
					(gOEventHandler unregisterEventHog: self)
					(= bImAHog 0)
					(if bIsHighlighted (self internalDoSelect:))
				)
				(event claimed: 1)
			)
			((and (== (event type?) evMOUSEBUTTON) temp0)
				(= bImAHog 1)
				(gOEventHandler registerEventHog: self)
				(self drawButton: 1 1)
				(self doClick:)
				(event claimed: 1)
			)
			(temp0 (event claimed: 1))
		)
		(return (event claimed?))
	)
	
	(method (stopHogging)
		(gOEventHandler unregisterEventHog: self)
		(= bImAHog 0)
		(self drawButton: 0 1)
	)
	
	(method (enable)
		(= bEnabled 1)
		(self forceCursor: (ScriptID 64006 0))
		(self drawButton: 0 0)
	)
	
	(method (disable)
		(= bEnabled 0)
		(self forceCursor: 0)
		(self drawButton: 0 0)
		(gOEventHandler unregisterEventHog: self)
	)
	
	(method (doSelect)
		(if oSelectNotify
			(Eval oSelectNotify nSelectMethod nSelectValue)
		)
	)
	
	(method (doClick)
		(if oClickNotify
			(Eval oClickNotify nClickMethod nSelectValue)
		)
	)
	
	(method (doHeld)
		(if oHeldNotify
			(Eval oHeldNotify nHeldMethod nSelectValue)
		)
	)
	
	(method (drawButton theBIsHighlighted param2)
		(if theBIsHighlighted (= cel 1) else (= cel 0))
		(= bIsHighlighted theBIsHighlighted)
		(if (not bEnabled) (= cel 2))
		(if (or (== 1 argc) (and (> 1 argc) param2))
			(UpdateScreenItem self)
			(FrameOut)
		)
	)
	
	(method (internalDoSelect)
		(self drawButton: 0 1)
		(self doSelect:)
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
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oClickNotify 0
		nClickMethod 756
		oHeldNotify 0
		nHeldMethod 757
		bIsHighlighted 0
		bImAHog 0
		bEnabled 1
		bButtonDown 0
	)
	
	(method (drawButton theBIsHighlighted param2)
		(if theBIsHighlighted (= cel 1) else (= cel 0))
		(if bButtonDown (= cel (+ cel 2)))
		(if (not bEnabled) (= cel 4))
		(= bIsHighlighted theBIsHighlighted)
		(if (or (== 1 argc) (and (> 1 argc) param2))
			(UpdateScreenItem self)
			(FrameOut)
		)
	)
	
	(method (internalDoSelect)
		(= bButtonDown (not bButtonDown))
		(super internalDoSelect: &rest)
	)
	
	(method (setButton theBButtonDown)
		(= bButtonDown theBButtonDown)
		(self drawButton: bIsHighlighted 0)
	)
	
	(method (isButtonDown)
		(return bButtonDown)
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
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oClickNotify 0
		nClickMethod 756
		oHeldNotify 0
		nHeldMethod 757
		bIsHighlighted 0
		bImAHog 0
		bEnabled 1
		bButtonDown 0
		oMyGroup 0
	)
	
	(method (internalDoSelect)
		(if (not bButtonDown)
			(if oMyGroup (oMyGroup doAllButMe: self 771 0))
			(super internalDoSelect: &rest)
		else
			(self drawButton: 0 1)
		)
	)
	
	(method (setButton param1)
		(if (and (not bButtonDown) oMyGroup)
			(oMyGroup doAllButMe: self 775 0)
		)
		(self realSetButton: param1)
	)
	
	(method (setGroup theOMyGroup)
		(= oMyGroup theOMyGroup)
	)
	
	(method (realSetButton theBButtonDown)
		(= bButtonDown theBButtonDown)
		(self drawButton: bIsHighlighted 0)
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
		(self eachElementDo: #setGroup self)
	)
	
	(method (doAllButMe param1 param2 &tmp radioGroupFirst temp1)
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
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oClickNotify 0
		nClickMethod 756
		oHeldNotify 0
		nHeldMethod 757
		bIsHighlighted 0
		bImAHog 0
		bEnabled 1
		text 0
		fore 0
		back 254
		skip 254
		dimmed 0
		font 0
		mode 1
		border 3
		maxWidth 0
		nMinWidth 0
		vTileOff -1
		vTileOn -1
		bTileBorder 1
		nLeading 2
		nWidth 0
		nHeight 0
		oPrivateText 0
		bmpOff 0
		bmpOn 0
	)
	
	(method (init &tmp temp0)
		(self setString: text)
		(super init: &rest)
		(self drawButton: bIsHighlighted 0)
	)
	
	(method (dispose &tmp theBitmap theBmpOff theBmpOn)
		(if oPrivateText
			(oPrivateText dispose:)
			(= oPrivateText 0)
		)
		(if text (proc64896_7 text) (= text 0))
		(= theBitmap 0)
		(if bitmap
			(if (or (== bitmap bmpOff) (== bitmap bmpOn))
				(= bitmap 0)
			else
				(= theBitmap bitmap)
				(= bitmap 0)
			)
		)
		(= theBmpOn bmpOn)
		(= theBmpOff bmpOff)
		(super dispose: &rest)
		(if theBmpOff (Bitmap 1 theBmpOff) (= theBmpOff 0))
		(if theBmpOn (Bitmap 1 theBmpOn) (= theBmpOn 0))
		(if theBitmap (Bitmap 1 theBitmap) (= theBitmap 0))
	)
	
	(method (show)
		(if (not bitmap) (self createBitmap:))
		(super show: &rest)
	)
	
	(method (hide)
		(super hide: &rest)
		(if bitmap
			(if bmpOff
				(= bitmap 0)
				(Bitmap 1 bmpOff)
				(= bmpOff 0)
				(if bmpOn (Bitmap 1 bmpOn) (= bmpOn 0))
			else
				(Bitmap 1 bitmap)
				(= bitmap 0)
			)
		)
	)
	
	(method (enable)
		(= bEnabled 1)
		(self createBitmap:)
		(super enable: &rest)
	)
	
	(method (disable)
		(= bEnabled 0)
		(self createBitmap:)
		(super disable: &rest)
	)
	
	(method (drawButton theBIsHighlighted param2 &tmp [temp0 3])
		(= bIsHighlighted theBIsHighlighted)
		(if bIsHighlighted
			(= bitmap bmpOn)
		else
			(= bitmap bmpOff)
		)
		(if (or (== 1 argc) (and (> argc 1) param2))
			(UpdateScreenItem self)
			(FrameOut)
		)
	)
	
	(method (createBitmap &tmp temp0 theBack_2 theFore_2 theFore_3 theBack theFore theSkip theBorder_3 theBorder_4 tiledBitmapNWidth tiledBitmapNHeight temp11 temp12 theBorder theBorder_2 temp15 temp16)
		(= temp16 0)
		(if bitmap
			(if (self isNotHidden:)
				(= temp16 1)
				(DeleteScreenItem self)
			)
			(if bmpOff
				(= bitmap 0)
				(Bitmap 1 bmpOff)
				(= bmpOff 0)
				(Bitmap 1 bmpOn)
				(= bmpOn 0)
			else
				(Bitmap 1 bitmap)
				(= bitmap 0)
			)
		)
		(if (not bEnabled) (= dimmed 1) else (= dimmed 0))
		(= temp0 (IntArray new: 4))
		(KText
			0
			(temp0 data?)
			(oPrivateText data?)
			font
			maxWidth
			1
		)
		(= temp11 (+ (temp0 at: 2) 1))
		(= temp12 (+ (temp0 at: 3) 1))
		(= nWidth (+ temp11 border border))
		(= nHeight (- (+ temp12 border border) nLeading))
		(if (> nMinWidth nWidth)
			(= temp15 (/ (- nMinWidth nWidth) 2))
			(= nWidth nMinWidth)
		else
			(= temp15 0)
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
		(if (!= vTileOff -1)
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
			(if (== vTileOn -1)
				(MonoOut
					{both vTile properties must be filled. button.sc djm}
				)
			)
			(TiledBitmap init: vTileOff nWidth nHeight 0 bTileBorder)
			(= bmpOff (TiledBitmap getBitmap: 1))
			(= tiledBitmapNWidth (TiledBitmap nWidth?))
			(= tiledBitmapNHeight (TiledBitmap nHeight?))
			(TiledBitmap init: vTileOn nWidth nHeight 0 bTileBorder)
			(= bmpOn (TiledBitmap getBitmap: 1))
			(= theBorder (+ (TiledBitmap nOffsetX?) border))
			(= theBorder_2 (+ (TiledBitmap nOffsetY?) border))
			(= theBorder_3
				(+ (TiledBitmap nLeftBorderWidth?) border)
			)
			(= theBorder_4
				(+ (TiledBitmap nRightBorderWidth?) border)
			)
			(= nWidth (TiledBitmap nWidth?))
			(= nHeight (TiledBitmap nHeight?))
			(if
				(or
					(!= nWidth tiledBitmapNWidth)
					(!= nHeight tiledBitmapNHeight)
				)
				(MonoOut
					{the on and off tiles of TextButton are not the same size}
				)
			)
		else
			(= bmpOff
				(Bitmap 0 nWidth nHeight theSkip theBack 640 480 1)
			)
			(Bitmap 5 bmpOff 0 0 (- nWidth 1) (- nHeight 1) theBack)
			(= bmpOn
				(Bitmap 0 nWidth nHeight theFore_3 theFore_2 640 480 1)
			)
			(Bitmap
				5
				bmpOn
				0
				0
				(- nWidth 1)
				(- nHeight 1)
				theFore_2
			)
			(= theBorder border)
			(= theBorder_2 border)
			(= theBorder_3 border)
			(= theBorder_4 border)
		)
		(cond 
			((== mode 1) (= theBorder (+ theBorder temp15)))
			((== mode 0) (= theBorder theBorder_3))
			((== mode 2) (= theBorder (- nWidth (+ theBorder_4 temp11))))
		)
		(Bitmap
			4
			bmpOff
			(oPrivateText data?)
			theBorder
			theBorder_2
			(- (+ theBorder temp11) 1)
			(- (+ theBorder_2 temp12) 1)
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
			bmpOn
			(oPrivateText data?)
			theBorder
			theBorder_2
			(- (+ theBorder temp11) 1)
			(- (+ theBorder_2 temp12) 1)
			theBack_2
			theFore_2
			theFore_3
			font
			mode
			theFore_2
			dimmed
		)
		(= bitmap bmpOff)
		(if temp16 (AddScreenItem self))
	)
	
	(method (setString param1)
		(if oPrivateText (oPrivateText dispose:))
		(if (and argc param1)
			(= oPrivateText (Str with: (KArray 9 param1)))
		else
			(= oPrivateText (Str with: {_}))
		)
		(self createBitmap:)
	)
)

(class TextItem of View
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
		text 0
		fore 0
		back 254
		skip 254
		dimmed 0
		font 0
		mode 1
		border 3
		maxWidth 0
		nMinWidth 0
		vTile -1
		bTileBorder 1
		nLeading 2
		nWidth 0
		nHeight 0
	)
	
	(method (init)
		(self createBitmap:)
		(super init: &rest)
	)
	
	(method (dispose &tmp theBitmap)
		(if text (proc64896_7 text) (= text 0))
		(= theBitmap 0)
		(if bitmap (= theBitmap bitmap) (= bitmap 0))
		(super dispose:)
		(if theBitmap (Bitmap 1 theBitmap))
	)
	
	(method (doVerb)
	)
	
	(method (createBitmap &tmp temp0 [temp1 3] theBack theFore theSkip theBorder_3 theBorder_4 [temp9 2] temp11 temp12 theBorder theBorder_2 temp15 theBitmap)
		(if (= theBitmap bitmap)
			(DeleteScreenItem self)
			(Bitmap 1 bitmap)
		)
		(= temp0 (IntArray new: 4))
		(KText 0 (temp0 data?) (KArray 9 text) font maxWidth 1)
		(= temp11 (+ (temp0 at: 2) 1))
		(= temp12 (+ (temp0 at: 3) 1))
		(= nWidth (+ temp11 border border))
		(= nHeight (- (+ temp12 border border) nLeading))
		(if (> nMinWidth nWidth)
			(= temp15 (/ (- nMinWidth nWidth) 2))
			(= nWidth nMinWidth)
		else
			(= temp15 0)
		)
		(temp0 dispose:)
		(= theFore fore)
		(= theBack back)
		(= theSkip skip)
		(if (!= vTile -1)
			(TiledBitmap init: vTile nWidth nHeight 0 bTileBorder)
			(= bitmap (TiledBitmap getBitmap: 1))
			(= theBorder (+ (TiledBitmap nOffsetX?) border))
			(= theBorder_2 (+ (TiledBitmap nOffsetY?) border))
			(= theBorder_3
				(+ (TiledBitmap nLeftBorderWidth?) border)
			)
			(= theBorder_4
				(+ (TiledBitmap nRightBorderWidth?) border)
			)
			(= nWidth (TiledBitmap nWidth?))
			(= nHeight (TiledBitmap nHeight?))
		else
			(= bitmap
				(Bitmap 0 nWidth nHeight theSkip theBack 640 480 1)
			)
			(Bitmap 5 bitmap 0 0 (- nWidth 1) (- nHeight 1) theBack)
			(= theBorder border)
			(= theBorder_2 border)
			(= theBorder_3 border)
			(= theBorder_4 border)
		)
		(cond 
			((== mode 1) (= theBorder (+ theBorder temp15)))
			((== mode 0) (= theBorder theBorder_3))
			((== mode 2) (= theBorder (- nWidth (+ theBorder_4 temp11))))
		)
		(Bitmap
			4
			bitmap
			(KArray 9 text)
			theBorder
			theBorder_2
			(- (+ theBorder temp11) 1)
			(- (+ theBorder_2 temp12) 1)
			theFore
			theBack
			theSkip
			font
			mode
			theBack
			dimmed
		)
		(if theBitmap (AddScreenItem self))
	)
	
	(method (setString param1)
		(if text (proc64896_7 text))
		(if (and argc param1)
			(= text (Str with: (KArray 9 param1)))
		else
			(= text (Str with: {_}))
		)
		(self createBitmap:)
	)
	
	(method (draw)
		(self createBitmap:)
	)
	
	(method (getText)
		(return (if text (return (Str with: text)) else (return 0)))
	)
)

(class EditItem of View
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
		text 0
		fore 0
		back 254
		skip 254
		dimmed 0
		font 0
		mode 0
		border 3
		maxWidth 0
		nMinWidth 0
		vTile -1
		bTileBorder 1
		nLeading 2
		nMaxChars 0
		nWidth 0
		nHeight 0
		oDEdit 0
		nOffX 0
		nOffY 0
	)
	
	(method (init)
		(self createBitmap:)
		(super init: &rest)
		(if oDEdit
			(oDEdit init: (plane getMainCast:))
			(self forceCursor: (ScriptID 64006 0))
		)
	)
	
	(method (dispose &tmp theBitmap)
		(if text (proc64896_7 text) (= text 0))
		(= theBitmap 0)
		(if bitmap (= theBitmap bitmap) (= bitmap 0))
		(super dispose:)
		(if theBitmap (Bitmap 1 theBitmap))
	)
	
	(method (doVerb)
		(if oDEdit (oDEdit hilite: 1))
	)
	
	(method (posn)
		(super posn: &rest)
		(self rePosn:)
	)
	
	(method (setPri)
		(super setPri: &rest)
		(self rePosn:)
	)
	
	(method (createBitmap &tmp temp0 [temp1 3] theBack theFore theSkip theNOffX theBorder [temp9 2] temp11 temp12 temp13 theBitmap)
		(if (= theBitmap bitmap)
			(DeleteScreenItem self)
			(Bitmap 1 bitmap)
		)
		(if oDEdit (oDEdit dispose:) (= oDEdit 0))
		(= temp0 (IntArray new: 4))
		(KText 0 (temp0 data?) (KArray 9 text) font maxWidth 1)
		(= temp11 (+ (temp0 at: 2) 1))
		(= temp12 (+ (temp0 at: 3) 1))
		(= nWidth (+ temp11 border border))
		(= nHeight (- (+ temp12 border border) nLeading))
		(if (> nMinWidth nWidth)
			(= temp13 (/ (- nMinWidth nWidth) 2))
			(= nWidth nMinWidth)
		else
			(= temp13 0)
		)
		(temp0 dispose:)
		(= theFore fore)
		(= theBack back)
		(= theSkip skip)
		(if (!= vTile -1)
			(TiledBitmap init: vTile nWidth nHeight 0 bTileBorder)
			(= bitmap (TiledBitmap getBitmap: 1))
			(= nOffX (+ (TiledBitmap nOffsetX?) border))
			(= nOffY (+ (TiledBitmap nOffsetY?) border))
			(= theNOffX (+ (TiledBitmap nLeftBorderWidth?) border))
			(= theBorder
				(+ (TiledBitmap nRightBorderWidth?) border)
			)
			(= nWidth (TiledBitmap nWidth?))
			(= nHeight (TiledBitmap nHeight?))
		else
			(= bitmap
				(Bitmap 0 nWidth nHeight theSkip theBack 640 480 1)
			)
			(Bitmap 5 bitmap 0 0 (- nWidth 1) (- nHeight 1) theBack)
			(= theNOffX (= nOffY (= nOffX border)))
			(= theBorder border)
		)
		(cond 
			((== mode 1) (= nOffX (+ nOffX temp13)))
			((== mode 0) (= nOffX theNOffX))
			((== mode 2) (= nOffX (- nWidth (+ theBorder temp11))))
		)
		(= oDEdit (DEdit new:))
		(oDEdit
			x: (+ x nOffX)
			y: (+ y nOffY)
			font: font
			fore: theFore
			back: 255
			skip: 255
			borderColor: 255
			text: (KArray 8 (KArray 9 text))
			width: nMaxChars
			setPri: (+ priority 1)
			setSize:
		)
		(if plane (oDEdit init: (plane getMainCast:)))
		(if theBitmap (AddScreenItem self))
	)
	
	(method (setString param1)
		(if text (proc64896_7 text))
		(if (and argc param1)
			(= text (Str with: (KArray 9 param1)))
		else
			(= text (Str with: {_}))
		)
		(self createBitmap:)
	)
	
	(method (draw)
		(self createBitmap:)
	)
	
	(method (rePosn)
		(if oDEdit
			(oDEdit
				posn: (+ x nOffX) (+ y nOffY)
				setPri: (+ priority 1)
			)
			(UpdateScreenItem oDEdit)
		)
	)
	
	(method (getText)
		(return
			(cond 
				(oDEdit (return (Str with: (oDEdit text?))))
				(text (return (Str with: text)))
				(else (return 0))
			)
		)
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
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oClickNotify 0
		nClickMethod 756
		oHeldNotify 0
		nHeldMethod 757
		bIsHighlighted 0
		bImAHog 0
		bEnabled 1
		text 0
		fore 0
		back 254
		skip 254
		dimmed 0
		font 0
		mode 1
		border 3
		maxWidth 0
		nMinWidth 0
		vTileOff -1
		vTileOn -1
		bTileBorder 1
		nLeading 2
		nWidth 0
		nHeight 0
		oPrivateText 0
		bmpOff 0
		bmpOn 0
		verb 0
		seq 1
		module -1
	)
	
	(method (init)
		(if (== module -1) (= module curRoomNum))
		(if text (text dispose:))
		(= text (Str newWith: 100 {}))
		(if
		(not (Message 0 module noun verb case seq (text data?)))
			(MonoOut
				{cannot find message: %d %d %d %d %d}
				module
				noun
				verb
				case
				seq
			)
			(text
				format: {msg: %d %d %d %d %d} module noun verb case seq
			)
		)
		(super init: &rest)
	)
)

(class DismissButton of PushButton
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
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oClickNotify 0
		nClickMethod 756
		oHeldNotify 0
		nHeldMethod 757
		bIsHighlighted 0
		bImAHog 0
		bEnabled 1
		value 0
		bDefault 0
		bCancel 1
	)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler registerHandsOffActive: self)
		(if (or bDefault bCancel)
			(gOEventHandler registerKeyHandler: self)
		)
	)
	
	(method (dispose)
		(gOEventHandler unregisterKeyHandler: self)
		(gOEventHandler unregisterHandsOffActive: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_ESCAPE)
				bCancel
			)
			(event claimed: 1)
			(self doSelect:)
			(return 1)
		)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_RETURN)
				bDefault
			)
			(event claimed: 1)
			(self doSelect:)
			(return 1)
		)
		(return (super handleEvent: event &rest))
	)
	
	(method (doSelect)
		(if (plane respondsTo: #value) (plane value: value))
		(plane dispose:)
	)
)

(class DismissTextButton of TextButton
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
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oClickNotify 0
		nClickMethod 756
		oHeldNotify 0
		nHeldMethod 757
		bIsHighlighted 0
		bImAHog 0
		bEnabled 1
		text 0
		fore 0
		back 254
		skip 254
		dimmed 0
		font 0
		mode 1
		border 3
		maxWidth 0
		nMinWidth 0
		vTileOff -1
		vTileOn -1
		bTileBorder 1
		nLeading 2
		nWidth 0
		nHeight 0
		oPrivateText 0
		bmpOff 0
		bmpOn 0
		value 0
		bDefault 0
		bCancel 1
	)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler registerHandsOffActive: self)
		(if (or bDefault bCancel)
			(gOEventHandler registerKeyHandler: self)
		)
	)
	
	(method (dispose)
		(gOEventHandler unregisterKeyHandler: self)
		(gOEventHandler unregisterHandsOffActive: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_ESCAPE)
				bCancel
			)
			(event claimed: 1)
			(self doSelect:)
			(return 1)
		)
		(if
			(and
				(== (event type?) evKEYBOARD)
				(== (event message?) KEY_RETURN)
				bDefault
			)
			(event claimed: 1)
			(self doSelect:)
			(return 1)
		)
		(return (super handleEvent: event &rest))
	)
	
	(method (doSelect &tmp temp0)
		(if (plane respondsTo: #value) (plane value: value))
		(plane dispose:)
	)
)
