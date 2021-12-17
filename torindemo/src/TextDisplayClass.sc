;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64004)
(include sci.sh)
(use Main)
(use ScrollBar)
(use oMessager)
(use List)
(use PushButton)
(use Plane)
(use String)
(use System)

(public
	oTextWindow 0
)

(class TextDisplayClass of Obj
	(properties
		scratch 0
		nMusic 0
		nPointLeft 0
		nPointTo 0
		oAvoiding 0
		fore 1
		back 0
		skip 0
		font 0
		curULY 0
		bAvoid 0
		nLastX 0
		nLastY 0
		ioMine 0
		x 0
		y 0
		oMusic 0
		oSound 0
		bExcusedSelf 0
		addScrollView 0
	)
	
	(method (init param1 &tmp temp0 temp1 temp2)
		(= oMusic (TextButton new:))
		(= bExcusedSelf (PushButton new:))
		(= oSound (TextButton new:))
		(= temp0 (Str new: 1000))
		(= temp1 (nMusic curPrayer: nPointLeft temp0))
		(= temp2 (Str with: (proc64032_0 temp1)))
		(oSound
			y: -500
			priority: 10
			fixPriority: 1
			text: temp2
			fore: fore
			back: back
			skip: skip
			font: font
			curULY: curULY
			bScrollable: oAvoiding
			removeItem: nMusic
			ioMine: ioMine
			curSlot: nPointLeft
			init: param1
		)
		(bExcusedSelf
			y: -500
			priority: 10
			fixPriority: 1
			view: bAvoid
			loop: nLastX
			cel: nLastY
			removeItem: nMusic
			ioMine: ioMine
			curSlot: nPointLeft
			init: param1
		)
		(oMusic
			y: -500
			priority: 10
			fixPriority: 1
			text: temp0
			fore: fore
			back: back
			skip: skip
			font: font
			curULY: curULY
			bScrollable: (- nPointTo (+ oAvoiding curULY 25))
			removeItem: nMusic
			ioMine: ioMine
			curSlot: nPointLeft
			init: param1
		)
		(= addScrollView
			(Max
				(+
					(oSound addScrollView?)
					(CelHigh
						(bExcusedSelf view?)
						(bExcusedSelf loop?)
						(bExcusedSelf cel:)
					)
				)
				(oMusic addScrollView?)
			)
		)
		(super init:)
		(self posn: x y)
	)
	
	(method (dispose)
		(oMusic dispose:)
		(oSound dispose:)
		(bExcusedSelf dispose:)
		(super dispose: &rest)
	)
	
	(method (posn theX theY &tmp theTheX temp1)
		(= theX (+ theX 5))
		(= theY (+ theY 5))
		(= theTheX theX)
		(bExcusedSelf posn: theTheX theY)
		(= temp1
			(/
				(-
					(CelHigh
						(bExcusedSelf view?)
						(bExcusedSelf loop?)
						(bExcusedSelf cel:)
					)
					(oSound addScrollView?)
				)
				2
			)
		)
		(= theTheX
			(+
				theTheX
				1
				(CelWide
					(bExcusedSelf view?)
					(bExcusedSelf loop?)
					(bExcusedSelf cel:)
				)
			)
		)
		(oSound posn: theTheX (+ temp1 theY))
		(= theTheX (+ theTheX 1 oAvoiding))
		(oMusic posn: theTheX theY)
		(= x theX)
		(= y theY)
	)
)

(class TextSaverClass of Obj
	(properties
		scratch 0
		unloadSound 0
		nSound 0
		raiseHands 0
		lowerHands 0
		bLeftHandOut 0
		addScrollView 0
		x 0
		y 0
		bRightHandOut 0
	)
	
	(method (dispose)
		(if bRightHandOut (bRightHandOut dispose:))
	)
)

(class TextWindowClass of Plane
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
		fore 0
		skip 0
		dimmed 0
		font 0
		mode 0
		curULY 2
		oLeftHand 20
		oRightHand 0
		bSinging 0
	)
	
	(method (init &tmp temp0)
		(super init: left top right bottom)
		(self setPri: -1)
		(textScrollBar
			object: self
			selector: 1074
			nBorderDist: 0
			oScrollPlane: 1
			init: self
		)
		(= bSinging
			(- (+ 1 (- right left)) (textScrollBar width:))
		)
		(textScrollBar posn: bSinging 0)
		(= oRightHand (List new:))
	)
	
	(method (dispose)
		(oRightHand dispose: &rest)
		(super dispose: &rest)
	)
	
	(method (show)
		(self setPri: 450)
	)
	
	(method (hide)
		(self setPri: -1)
	)
	
	(method (setPenalty)
		(self getTwoHigher: (- 0 (textScrollBar getPenalty:)))
	)
	
	(method (oSingCuee param1 &tmp newTextDisplayClass)
		((= newTextDisplayClass (TextDisplayClass new:))
			nMusic: self
			nPointLeft: param1
			nPointTo: bSinging
			oAvoiding: 75
			fore: fore
			back: back
			skip: skip
			font: font
			curULY: curULY
			bAvoid: -5532
			nLastX: 21
			ioMine: 1247
			init: self
		)
		(param1
			addScrollView: (newTextDisplayClass addScrollView?)
		)
		(param1 bRightHandOut: newTextDisplayClass)
	)
	
	(method (unselect param1 param2 param3 param4 param5 &tmp newTextSaverClass textScrollBarOScrollPlane)
		(if (>= (oRightHand size:) oLeftHand)
			(= newTextSaverClass (oRightHand at: 0))
			(oRightHand delete: newTextSaverClass)
			(newTextSaverClass dispose:)
		)
		((= newTextSaverClass (TextSaverClass new:))
			unloadSound: param1
			nSound: param2
			raiseHands: param3
			lowerHands: param4
			bLeftHandOut: param5
			init:
		)
		(oRightHand addToEnd: newTextSaverClass)
		(self oSingCuee: newTextSaverClass)
		(self switchWith:)
		(= textScrollBarOScrollPlane
			(textScrollBar oScrollPlane?)
		)
		(textScrollBar solvedThrough: textScrollBarOScrollPlane)
		(= textScrollBarOScrollPlane
			(- 0 textScrollBarOScrollPlane)
		)
		(self getTwoHigher: textScrollBarOScrollPlane)
	)
	
	(method (curPrayer param1 param2 &tmp temp0)
		(if param1
			(= temp0
				(Message
					msgGET
					(param1 unloadSound?)
					(param1 nSound?)
					(param1 raiseHands?)
					(param1 lowerHands?)
					(param1 bLeftHandOut?)
					(param2 data?)
				)
			)
		else
			(= temp0 -1)
			(param2 with: {The unknown message})
		)
		(return temp0)
	)
	
	(method (getOneHigher param1 &tmp temp0)
		(theGame handsOff:)
		(if param1
			(messager
				sing:
					(param1 nSound?)
					(param1 raiseHands?)
					(param1 lowerHands?)
					(param1 bLeftHandOut?)
					(ScriptID 64020 0)
					(param1 unloadSound?)
			)
		)
	)
	
	(method (getTwoHigher param1 &tmp oRightHandFirst temp1 temp2)
		(= temp2 (- bottom top))
		(= oRightHandFirst (oRightHand first:))
		(while oRightHandFirst
			(= temp1 (KList 8 oRightHandFirst))
			(if
				(or
					(< (+ param1 (temp1 addScrollView?)) 0)
					(> param1 temp2)
				)
				(if (temp1 bRightHandOut?)
					((temp1 bRightHandOut?) dispose:)
					(temp1 bRightHandOut: 0)
				)
			else
				(if (not (temp1 bRightHandOut?))
					(self oSingCuee: temp1 (oRightHand indexOf: temp1))
				)
				((temp1 bRightHandOut?) posn: 0 param1)
			)
			(= param1 (+ param1 (temp1 addScrollView?)))
			(= oRightHandFirst (oRightHand next: oRightHandFirst))
		)
	)
	
	(method (switchWith &tmp temp0 temp1 temp2 temp3)
		(= temp1 0)
		(= temp0 0)
		(while (< temp0 (oRightHand size:))
			(= temp1
				(+ temp1 ((oRightHand at: temp0) addScrollView?))
			)
			(++ temp0)
		)
		(= temp1 (- temp1 (- bottom top)))
		(= temp1 (Max 1 temp1))
		(= temp3 (* 3 (= temp2 (Max 1 (/ temp1 20)))))
		(textScrollBar
			setAllFlagsUpTo: 0 temp1
			getScore: temp2 temp3
		)
	)
)

(instance textScrollBar of ScrollBar
	(properties
		priority 16
		fixPriority 1
		view -5532
		loop 11
		oEScrollExit -5532
		oWScrollExit 14
		oVertHandle -5532
		oMyScrollPlane 13
		nHighBound -5532
		bXAxis 12
	)
)

(instance oTextWindow of TextWindowClass
	(properties)
)
