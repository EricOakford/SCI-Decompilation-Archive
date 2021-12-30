;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64869)
(include sci.sh)
(use Plane)
(use Actor)
(use System)


(instance voTemp of View
	(properties)
)

(class TiledBitmap of Obj
	(properties
		scratch 0
		oMyScrollViews 1
		oMyNotifyObjs 0
		oMyNotifySelectors 0
		addScrollView 0
		scroll 0
		scrollToObj 0
		scrollToLoc 0
		panTo 0
		killPan 0
		addToNotifyList 0
		canScrollLeft 0
		canScrollRight 0
		getLocalX 0
		getLocalY 0
		isObjVisible 0
		isLocVisible 0
		addPics 0
		addViews 0
		bitmap 0
		vInventory -1
	)
	
	(method (init theVInventory param2 param3 theOMyNotifyObjs theOMyScrollViews)
		(super init: &rest)
		(if (< argc 3)
			(MonoOut {invalid init of TiledBitmap})
			(return)
		)
		(= vInventory theVInventory)
		(if (> argc 3) (= oMyNotifyObjs theOMyNotifyObjs))
		(if (> argc 4) (= oMyScrollViews theOMyScrollViews))
		(self lInventory: param2 param3)
	)
	
	(method (dispose)
		(if bitmap (Bitmap 1 bitmap) (= bitmap 0))
		(super dispose: &rest)
	)
	
	(method (lInventory theOMyNotifySelectors theAddScrollView &tmp theOMyScrollViews temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 theIsObjVisible theAddPics temp11)
		(if (< argc 2)
			(MonoOut {Invalid resize of TiledBitmap})
			(return)
		)
		(if (not (ResCheck 128 vInventory))
			(MonoOut {no view %hd for TiledBitmap} vInventory)
			(return)
		)
		(voTemp view: vInventory loop: 0 cel: 0)
		(if
		(or (< (= temp5 (NumLoops voTemp)) 1) (> temp5 2))
			(MonoOut {wrong number of loops view %hd} vInventory)
			(return)
		)
		(if (== temp5 1)
			(= theOMyScrollViews 0)
			(= temp6 (NumCels voTemp))
		else
			(= theOMyScrollViews oMyScrollViews)
			(= temp6 (NumCels voTemp))
			(voTemp loop: 1)
			(= temp7 (NumCels voTemp))
		)
		(if
		(or (!= temp6 1) (and theOMyScrollViews (!= temp7 8)))
			(MonoOut
				{Error: tiles view %hd should have 1 cel in loop 0, 8 cels in loop 1}
			)
			(return)
		)
		(= canScrollLeft (CelWide vInventory 0 0))
		(= canScrollRight (CelHigh vInventory 0 0))
		(if theOMyScrollViews
			(= getLocalX (CelHigh vInventory 1 0))
			(= getLocalY (CelWide vInventory 1 2))
			(= isObjVisible (CelWide vInventory 1 0))
			(= isLocVisible (CelWide vInventory 1 1))
			(= addPics (CelHigh vInventory 1 2))
			(= addViews (CelHigh vInventory 1 3))
			(if
				(or
					(!= canScrollRight getLocalX)
					(!= canScrollLeft getLocalY)
				)
				(= temp11 0)
			else
				(= temp11 1)
			)
			(= temp8 0)
			(if (!= getLocalX (CelHigh vInventory 1 1))
				(MonoOut {view %hd left/right borders don't match})
				(= temp8 1)
			)
			(if (!= getLocalY (CelWide vInventory 1 3))
				(MonoOut {view %hd top/bottom borders don't match})
				(= temp8 1)
			)
			(if
				(or
					(!= isObjVisible (CelWide vInventory 1 4))
					(!= addPics (CelHigh vInventory 1 4))
				)
				(MonoOut {view %hd TL corner wrong size})
				(= temp8 1)
			)
			(if
				(or
					(!= isLocVisible (CelWide vInventory 1 5))
					(!= addPics (CelHigh vInventory 1 5))
				)
				(MonoOut {view %hd TR corner wrong size})
				(= temp8 1)
			)
			(if
				(or
					(!= isObjVisible (CelWide vInventory 1 6))
					(!= addViews (CelHigh vInventory 1 6))
				)
				(MonoOut {view %hd BL corner wrong size})
				(= temp8 1)
			)
			(if
				(or
					(!= isLocVisible (CelWide vInventory 1 7))
					(!= addViews (CelHigh vInventory 1 7))
				)
				(MonoOut {view %hd BR corner wrong size})
				(= temp8 1)
			)
			(if temp8 (return))
		else
			(= getLocalX canScrollRight)
			(= getLocalY canScrollLeft)
			(= temp11 1)
			(= addViews
				(= addPics (= isLocVisible (= isObjVisible 0)))
			)
		)
		(switch oMyNotifyObjs
			(0
				(= scroll (/ theOMyNotifySelectors getLocalY))
				(if (!= theOMyNotifySelectors (* scroll getLocalY))
					(++ scroll)
				)
				(= scrollToObj (/ theAddScrollView getLocalX))
				(if (!= theAddScrollView (* scrollToObj getLocalX))
					(++ scrollToObj)
				)
			)
			(1
				(= temp1
					(- theOMyNotifySelectors (+ isObjVisible isLocVisible))
				)
				(= temp2 (- theAddScrollView (+ addPics addViews)))
				(if
				(or (< temp1 canScrollLeft) (< temp2 canScrollRight))
					(MonoOut {requested max size too small for tiles})
				)
				(= scroll (/ temp1 canScrollLeft))
				(= scrollToObj (/ temp2 canScrollRight))
			)
		)
		(if temp11
			(= scrollToLoc scroll)
			(= panTo scrollToObj)
		else
			(= temp1 (* scroll getLocalY))
			(= temp2 (* scrollToObj getLocalX))
			(= scrollToLoc (/ temp1 canScrollLeft))
			(if (!= temp1 (* scrollToLoc canScrollLeft))
				(++ scrollToLoc)
			)
			(= panTo (/ temp2 canScrollRight))
			(if (!= temp2 (* panTo canScrollRight)) (++ panTo))
		)
		(if theOMyScrollViews
			(= oMyNotifySelectors
				(+ isObjVisible (* getLocalY scroll) isLocVisible)
			)
			(= addScrollView
				(+ addPics (* getLocalX scrollToObj) addViews)
			)
		else
			(= oMyNotifySelectors theOMyNotifySelectors)
			(= addScrollView theAddScrollView)
		)
		(= killPan
			(/ (- oMyNotifySelectors theOMyNotifySelectors) 2)
		)
		(= addToNotifyList
			(/ (- addScrollView theAddScrollView) 2)
		)
		(if bitmap (Bitmap 1 bitmap) (= bitmap 0))
		(= bitmap
			(Bitmap 0 oMyNotifySelectors addScrollView 255 255)
		)
		(= theIsObjVisible isObjVisible)
		(= theAddPics addPics)
		(= temp3 0)
		(while (< temp3 panTo)
			(= temp4 0)
			(while (< temp4 scrollToLoc)
				(Bitmap
					3
					bitmap
					vInventory
					0
					0
					theIsObjVisible
					theAddPics
				)
				(= theIsObjVisible (+ theIsObjVisible canScrollLeft))
				(++ temp4)
			)
			(= theIsObjVisible isObjVisible)
			(= theAddPics (+ theAddPics canScrollRight))
			(++ temp3)
		)
		(if theOMyScrollViews
			(= theIsObjVisible 0)
			(= theAddPics 0)
			(Bitmap
				3
				bitmap
				vInventory
				1
				4
				theIsObjVisible
				theAddPics
			)
			(= theIsObjVisible (+ theIsObjVisible isObjVisible))
			(= temp3 0)
			(while (< temp3 scroll)
				(Bitmap
					3
					bitmap
					vInventory
					1
					2
					theIsObjVisible
					theAddPics
				)
				(= theIsObjVisible (+ theIsObjVisible getLocalY))
				(++ temp3)
			)
			(Bitmap
				3
				bitmap
				vInventory
				1
				5
				theIsObjVisible
				theAddPics
			)
			(= theIsObjVisible 0)
			(= theAddPics addPics)
			(= temp3 0)
			(while (< temp3 scrollToObj)
				(Bitmap
					3
					bitmap
					vInventory
					1
					0
					theIsObjVisible
					theAddPics
				)
				(= theAddPics (+ theAddPics getLocalX))
				(++ temp3)
			)
			(= theIsObjVisible
				(+ isObjVisible (* scroll getLocalY))
			)
			(= theAddPics addPics)
			(Bitmap
				5
				bitmap
				theIsObjVisible
				theAddPics
				(- (+ theIsObjVisible isLocVisible) 1)
				(- (+ theAddPics (* getLocalX scrollToObj)) 1)
				255
			)
			(= temp3 0)
			(while (< temp3 scrollToObj)
				(Bitmap
					3
					bitmap
					vInventory
					1
					1
					theIsObjVisible
					theAddPics
				)
				(= theAddPics (+ theAddPics getLocalX))
				(++ temp3)
			)
			(= theIsObjVisible 0)
			(= theAddPics (+ addPics (* getLocalX scrollToObj)))
			(Bitmap
				5
				bitmap
				theIsObjVisible
				theAddPics
				(- oMyNotifySelectors 1)
				(- (+ theAddPics addViews) 1)
				255
			)
			(Bitmap
				3
				bitmap
				vInventory
				1
				6
				theIsObjVisible
				theAddPics
			)
			(= theIsObjVisible (+ theIsObjVisible isObjVisible))
			(= temp3 0)
			(while (< temp3 scroll)
				(Bitmap
					3
					bitmap
					vInventory
					1
					3
					theIsObjVisible
					theAddPics
				)
				(= theIsObjVisible (+ theIsObjVisible getLocalY))
				(++ temp3)
			)
			(Bitmap
				3
				bitmap
				vInventory
				1
				7
				theIsObjVisible
				theAddPics
			)
		)
	)
	
	(method (cInventory param1 &tmp theBitmap)
		(= theBitmap bitmap)
		(if (and argc param1) (= bitmap 0))
		(return theBitmap)
	)
)

(class TiledView of View
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
		oMyNotifySelectors 0
		addScrollView 0
		killPan 0
		addToNotifyList 0
	)
	
	(method (init param1 param2 param3 param4)
		(cond 
			((< argc 2) (MonoOut {Invalid init of TiledView -- not enough params}))
			((== argc 2) (TiledBitmap init: view param1 param2))
			((== argc 3) (TiledBitmap init: view param1 param2 param3))
			((> argc 3) (TiledBitmap init: view param1 param2 param3 param4))
		)
		(= bitmap (TiledBitmap cInventory: 1))
		(= oMyNotifySelectors (TiledBitmap oMyNotifySelectors?))
		(= addScrollView (TiledBitmap addScrollView?))
		(= killPan (TiledBitmap killPan?))
		(= addToNotifyList (TiledBitmap addToNotifyList?))
		(super init: &rest)
	)
)

(class TiledPlane of Plane
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
		nScreenSizeX 0
		killPan 0
		addToNotifyList 0
		vCloseup 0
		vInventory -1
		oMyNotifyObjs 0
		oMyScrollViews 1
	)
	
	(method (init param1 param2 param3 param4 theVInventory theOMyNotifyObjs theOMyScrollViews &tmp [temp0 2])
		(if (< argc 5)
			(MonoOut {Not enough params to init of TiledPlane})
			(return)
		)
		(super init: param1 param2 param3 param4 &rest)
		(= vInventory theVInventory)
		(if (> argc 5) (= oMyNotifyObjs theOMyNotifyObjs))
		(if (> argc 6) (= oMyScrollViews theOMyScrollViews))
		(self setRect: param1 param2 param3 param4)
	)
	
	(method (dispose)
		(if vCloseup (vCloseup dispose:) (= vCloseup 0))
		(super dispose: &rest)
	)
	
	(method (setRect param1 param2 param3 param4 &tmp vCloseupOMyNotifySelectors vCloseupAddScrollView)
		(= vCloseupOMyNotifySelectors (+ (- param3 param1) 1))
		(= vCloseupAddScrollView (+ (- param4 param2) 1))
		(if
			(or
				(!= vCloseupOMyNotifySelectors (self findData:))
				(!= vCloseupAddScrollView (self doDouble:))
				(not vCloseup)
			)
			(if vCloseup (vCloseup dispose:))
			(= vCloseup (TiledView new:))
			(vCloseup name: {voBG} setPri: 0 view: vInventory)
			(vCloseup
				init:
					vCloseupOMyNotifySelectors
					vCloseupAddScrollView
					oMyNotifyObjs
					oMyScrollViews
					self
			)
			(= killPan (vCloseup killPan?))
			(= addToNotifyList (vCloseup addToNotifyList?))
			(= vCloseupOMyNotifySelectors
				(vCloseup oMyNotifySelectors?)
			)
			(= vCloseupAddScrollView (vCloseup addScrollView?))
		)
		(super
			setRect:
				left
				top
				(- (+ left vCloseupOMyNotifySelectors) 1)
				(- (+ top vCloseupAddScrollView) 1)
		)
	)
)
