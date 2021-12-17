;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64004)
(include sci.sh)
(use Main)
(use ScrollBar)
(use oMessager)
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
		oTextWindowThingy 0
		oTextSaver 0
		displayWidth 0
		whoWidth 0
		mode 0
		fore 1
		back 0
		skip 0
		font 0
		border 0
		speakerView 0
		speakerLoop 0
		speakerCel 0
		nSelectMethod 0
		x 0
		y 0
		oText 0
		oWho 0
		oSpeaker 0
		nHeight 0
	)
	
	(method (init param1 &tmp temp0 temp1 temp2)
		(= oText (TextButton new:))
		(= oSpeaker (PushButton new:))
		(= oWho (TextButton new:))
		(= temp0 (Str new: 1000))
		(= temp1
			(oTextWindowThingy getMessage: oTextSaver temp0)
		)
		(= temp2 (Str with: (proc64032_0 temp1)))
		(oWho
			y: -500
			priority: 10
			fixPriority: 1
			text: temp2
			fore: fore
			back: back
			skip: skip
			font: font
			border: border
			maxWidth: whoWidth
			oSelectNotify: oTextWindowThingy
			nSelectMethod: nSelectMethod
			nSelectValue: oTextSaver
			init: param1
		)
		(oSpeaker
			y: -500
			priority: 10
			fixPriority: 1
			view: speakerView
			loop: speakerLoop
			cel: speakerCel
			oSelectNotify: oTextWindowThingy
			nSelectMethod: nSelectMethod
			nSelectValue: oTextSaver
			init: param1
		)
		(oText
			y: -500
			priority: 10
			fixPriority: 1
			text: temp0
			mode: mode
			fore: fore
			back: back
			skip: skip
			font: font
			border: border
			maxWidth: (- displayWidth (+ whoWidth border 25))
			oSelectNotify: oTextWindowThingy
			nSelectMethod: nSelectMethod
			nSelectValue: oTextSaver
			init: param1
		)
		(= nHeight
			(Max
				(+
					(oWho nHeight?)
					(CelHigh
						(oSpeaker view?)
						(oSpeaker loop?)
						(oSpeaker cel?)
					)
				)
				(oText nHeight?)
			)
		)
		(super init:)
		(self posn: x y)
	)
	
	(method (dispose)
		(oText dispose:)
		(oWho dispose:)
		(oSpeaker dispose:)
		(super dispose: &rest)
	)
	
	(method (show)
		(oText show:)
		(oSpeaker show:)
		(oWho show:)
	)
	
	(method (posn theX theY &tmp theTheX temp1)
		(= theX (+ theX 5))
		(= theY (+ theY 5))
		(= theTheX theX)
		(oSpeaker posn: theTheX theY)
		(= temp1
			(/
				(-
					(CelHigh
						(oSpeaker view?)
						(oSpeaker loop?)
						(oSpeaker cel?)
					)
					(oWho nHeight?)
				)
				2
			)
		)
		(= theTheX
			(+
				theTheX
				1
				(CelWide
					(oSpeaker view?)
					(oSpeaker loop?)
					(oSpeaker cel?)
				)
			)
		)
		(oWho posn: theTheX (+ temp1 theY))
		(= theTheX (+ theTheX 1 whoWidth))
		(oText posn: theTheX theY)
		(= x theX)
		(= y theY)
	)
	
	(method (hide)
		(oText hide:)
		(oSpeaker hide:)
		(oWho hide:)
	)
)

(class TextSaverClass of Obj
	(properties
		scratch 0
		aMod 0
		aNoun 0
		aVerb 0
		aCase 0
		aSeq 0
		nHeight 0
		x 0
		y 0
		oTextDisplay 0
	)
	
	(method (dispose)
		(if oTextDisplay (oTextDisplay dispose:))
		(= oTextDisplay 0)
		(super dispose: &rest)
	)
	
	(method (show)
		(if oTextDisplay (oTextDisplay show:))
	)
	
	(method (hide)
		(if oTextDisplay (oTextDisplay hide:))
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
		oMyFeatures 0
		fore 0
		skip 0
		dimmed 0
		font 0
		mode 0
		border 2
		nMaxMessages 20
		oMessageList 0
		nTextWidth 0
	)
	
	(method (init &tmp temp0)
		(super init: left top right bottom)
		(self setPri: -1)
		(textScrollBar
			object: self
			selector: 1038
			minPosn: 0
			maxPosn: 1
			init: self
		)
		(= nTextWidth
			(- (+ 1 (- right left)) (textScrollBar width:))
		)
		(textScrollBar posn: nTextWidth 0)
		(= oMessageList (List new:))
	)
	
	(method (dispose)
		(oMessageList dispose: &rest)
		(super dispose: &rest)
	)
	
	(method (show)
		(self setPri: 450)
		(oMessageList eachElementDo: #show)
	)
	
	(method (hide)
		(self setPri: -1)
		(oMessageList eachElementDo: #hide)
	)
	
	(method (scrolled)
		(self posnList: (- 0 (textScrollBar getPos:)))
	)
	
	(method (createDisplay param1 &tmp newTextDisplayClass)
		((= newTextDisplayClass (TextDisplayClass new:))
			oTextWindowThingy: self
			oTextSaver: param1
			displayWidth: nTextWidth
			whoWidth: 75
			mode: mode
			fore: fore
			back: back
			skip: skip
			font: font
			border: border
			speakerView: -5532
			speakerLoop: 21
			nSelectMethod: 1176
			init: self
		)
		(param1 nHeight: (newTextDisplayClass nHeight?))
		(param1 oTextDisplay: newTextDisplayClass)
	)
	
	(method (rememberMessage param1 param2 param3 param4 param5 &tmp newTextSaverClass textScrollBarMaxPosn)
		(if (>= (oMessageList size:) nMaxMessages)
			(= newTextSaverClass (oMessageList at: 0))
			(oMessageList delete: newTextSaverClass)
			(newTextSaverClass dispose:)
		)
		((= newTextSaverClass (TextSaverClass new:))
			aMod: param1
			aNoun: param2
			aVerb: param3
			aCase: param4
			aSeq: param5
			init:
		)
		(oMessageList addToEnd: newTextSaverClass)
		(self createDisplay: newTextSaverClass)
		(if (== priority -1) (newTextSaverClass hide:))
		(self updateScrollbar:)
		(= textScrollBarMaxPosn (textScrollBar maxPosn?))
		(textScrollBar setPos: textScrollBarMaxPosn)
		(= textScrollBarMaxPosn (- 0 textScrollBarMaxPosn))
		(self posnList: textScrollBarMaxPosn)
	)
	
	(method (getMessage param1 param2 &tmp temp0)
		(if param1
			(= temp0
				(Message
					0
					(param1 aMod?)
					(param1 aNoun?)
					(param1 aVerb?)
					(param1 aCase?)
					(param1 aSeq?)
					(param2 data?)
				)
			)
		else
			(= temp0 -1)
			(param2 with: {The unknown message})
		)
		(return temp0)
	)
	
	(method (sayMessage param1 &tmp temp0)
		(theGame handsOff:)
		(if param1
			(messager
				sayNoSave:
					(param1 aNoun?)
					(param1 aVerb?)
					(param1 aCase?)
					(param1 aSeq?)
					(ScriptID 64039 0)
					(param1 aMod?)
			)
		)
	)
	
	(method (posnList param1 &tmp oMessageListFirst temp1 temp2)
		(= temp2 (- bottom top))
		(= oMessageListFirst (oMessageList first:))
		(while oMessageListFirst
			(= temp1 (List 8 oMessageListFirst))
			(if
				(or
					(< (+ param1 (temp1 nHeight?)) 0)
					(> param1 temp2)
				)
				(if (temp1 oTextDisplay?)
					((temp1 oTextDisplay?) dispose:)
					(temp1 oTextDisplay: 0)
				)
			else
				(if (not (temp1 oTextDisplay?))
					(self createDisplay: temp1 (oMessageList indexOf: temp1))
				)
				((temp1 oTextDisplay?) posn: 0 param1)
			)
			(= param1 (+ param1 (temp1 nHeight?)))
			(= oMessageListFirst
				(oMessageList next: oMessageListFirst)
			)
		)
	)
	
	(method (updateScrollbar &tmp temp0 temp1 temp2 temp3)
		(= temp1 0)
		(= temp0 0)
		(while (< temp0 (oMessageList size:))
			(= temp1 (+ temp1 ((oMessageList at: temp0) nHeight?)))
			(++ temp0)
		)
		(= temp1 (- temp1 (- bottom top)))
		(= temp1 (Max 1 temp1))
		(= temp3 (* 3 (= temp2 (Max 1 (/ temp1 20)))))
		(textScrollBar
			setMinMax: 0 temp1
			setIncrement: temp2 temp3
		)
	)
)

(instance textScrollBar of ScrollBar
	(properties
		priority 16
		fixPriority 1
		view -5532
		loop 11
		vThumbView -5532
		lThumbLoop 14
		vDownArrowView -5532
		lDownArrowLoop 13
		vUpArrowView -5532
		lUpArrowLoop 12
	)
)

(instance oTextWindow of TextWindowClass
	(properties)
)
