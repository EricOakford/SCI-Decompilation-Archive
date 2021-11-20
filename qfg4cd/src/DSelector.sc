;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64913)
(include sci.sh)
(use Main)
(use DButton)
(use DText)
(use DItem)
(use Plane)
(use String)
(use Array)
(use System)


(class DSelector of Class_64916_0
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
		signal $0000
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
		key 0
		value 0
		object 0
		selector 0
		font 0
		length 0
		width 100
		textHeight 0
		first 0
		current 0
		listPlane 0
		textList 0
		fore 0
		back 255
		slider 0
		knob 0
		upButton 0
		downButton 0
	)
	
	(method (init param1 &tmp temp0 temp1 newStr)
		(= temp0
			((DText new:)
				fore: 0
				back: 0
				skip: 0
				font: font
				text: {_}
				setSize:
				yourself:
			)
		)
		(= bitmap
			(CreateTextBitmap
				0
				(+ (- (temp0 nsRight?) (temp0 nsLeft?)) 1)
				(+ (- (temp0 nsBottom?) (temp0 nsTop?)) 1)
				temp0
			)
		)
		(DisposeClone temp0)
		(= listPlane
			((Plane new:)
				name: {DSPlane}
				priority: (+ (GetHighPlanePri) 1)
				yourself:
			)
		)
		(= first 0)
		(super init: param1 &rest)
		(listPlane
			init: 0 0 (+ (- nsRight nsLeft) 1) (+ (- nsBottom nsTop) 1)
			back: back
			posn: nsLeft nsTop plane
			addCast: textList
		)
		(textList eachElementDo: #init textList)
		(if upButton (upButton init: param1))
		(if downButton (downButton init: param1))
		(= upButton (DButton new:))
		(= newStr (Str new:))
		(Message msgGET -546 39 0 0 1 (newStr data?))
		(upButton
			font: 0
			text: (String 8 (newStr data?))
			view: -546
			loop: 4
			cel: 0
			object: self
			selector: 401
			state: 1
			setSize:
			init: param1
			yourself:
		)
		(Message msgGET -546 40 0 0 1 (newStr data?))
		(= downButton
			((DButton new:)
				font: 0
				text: (String 8 (newStr data?))
				view: -546
				loop: 4
				cel: 0
				setSize:
				object: self
				selector: 400
				state: 1
				init: param1
				yourself:
			)
		)
		(self draw:)
		(newStr dispose:)
	)
	
	(method (dispose)
		(textList dispose:)
		(if bitmap (Bitmap 1 bitmap) (= bitmap 0))
		(listPlane dispose:)
		(upButton dispose:)
		(downButton dispose:)
		(= textList
			(= listPlane (= upButton (= downButton (= bitmap 0))))
		)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (== (event type?) evMOUSEBUTTON)
			(event globalize:)
			(if (listPlane onMe: event)
				(event localize: listPlane)
				(if (= temp0 (textList firstTrue: #onMe event))
					(= current (textList indexOf: temp0))
					(self draw:)
				)
			)
			(event localize: plane)
		)
		(return (if (event claimed?) (return self) else 0))
	)
	
	(method (setSize &tmp temp0 textListNextNode temp2 newIntArray)
		(= newIntArray (IntArray new:))
		(= temp0 0)
		(if (not width)
			(= textListNextNode (FirstNode (textList elements?)))
			(while textListNextNode
				(textList nextNode: (NextNode textListNextNode))
				(= temp2 (NodeValue textListNextNode))
				(TextSize (newIntArray data?) (temp2 text?) font 0)
				(= temp0 (Max (newIntArray at: 2) temp0))
				(= textListNextNode (textList nextNode?))
			)
		)
		(TextSize (newIntArray data?) {M} font 0)
		(= textHeight (+ (newIntArray at: 3) 2))
		(newIntArray dispose:)
		(= nsTop (= nsLeft 0))
		(= nsRight (Max width temp0))
		(= nsBottom (- (* textHeight length) 4))
	)
	
	(method (draw &tmp temp0 temp1 temp2 temp3 temp4)
		(if (textList size:)
			(= temp3 (textList at: current))
			(= temp0 (FirstNode (textList elements?)))
			(= temp4 0)
			(while temp0
				(= temp1 (NextNode temp0))
				(= temp2 (NodeValue temp0))
				(temp2
					moveTo: 2 (+ 1 (* (- first temp4) -1 textHeight))
					fore: (if (== temp3 temp2) back else fore)
					back: (if (== temp3 temp2) fore else back)
					draw:
				)
				(UpdateScreenItem temp2)
				(= temp0 temp1)
				(++ temp4)
			)
		)
		(if saveFileSelText
			(saveFileSelText dispose:)
			(= saveFileSelText 0)
		)
		(if
			(and
				(>= (- (textList size:) 1) current)
				(>= current 0)
			)
			(= saveFileSelText
				(Str with: ((textList at: current) text?))
			)
		)
	)
	
	(method (updatePlane &tmp temp0 temp1 temp2)
		(listPlane
			posn: (listPlane left:) (listPlane top?) plane
		)
		(UpdatePlane listPlane)
		(= temp0
			(-
				(+
					(/ (- (listPlane bottom?) (listPlane top?)) 2)
					(listPlane top?)
				)
				(plane top?)
			)
		)
		(= temp1 (+ (- (listPlane right:) (plane left:)) 4))
		(= temp2
			(CelHigh
				(upButton view?)
				(upButton loop?)
				(upButton cel?)
			)
		)
		(upButton moveTo: temp1 (- temp0 (+ temp2 4)))
		(UpdateScreenItem upButton)
		(downButton moveTo: temp1 (+ temp0 4))
		(UpdateScreenItem downButton)
	)
	
	(method (setText param1 &tmp temp0 [temp1 2] newStr)
		(if (and (not param1) textList)
			(textList dispose:)
			(= textList 0)
		)
		(if (not textList)
			(= textList ((Cast new:) name: {DSList} add: yourself:))
		)
		(if param1
			(= newStr (Str new:))
			(= temp0 0)
			(while (and (< temp0 argc) (< (textList size:) 250))
				(newStr copy: [param1 temp0])
				(textList
					add:
						((SelectorDText new:)
							font: font
							text: (String 8 (newStr data?))
							setSize: (- width 4)
							yourself:
						)
				)
				(++ temp0)
			)
			(newStr dispose:)
			(if (not length) (= length argc))
			(if listPlane
				(listPlane addCast: textList)
				(textList eachElementDo: #init textList)
			)
		)
	)
	
	(method (getText)
		(String 8 ((textList at: current) text?))
	)
	
	(method (scrollDown param1 &tmp temp0)
		(= temp0 (if argc param1 else 1))
		(if
		(>= (= current (+ current param1)) (textList size:))
			(= current (- (textList size:) 1))
		)
		(if (>= current (+ first length))
			(= first (- current (- length 1)))
		)
		(self draw:)
	)
	
	(method (scrollUp param1 &tmp temp0)
		(= temp0 (if argc param1 else 1))
		(if (< (= current (- current param1)) 0)
			(= current 0)
		)
		(if (< current first) (= first current))
		(self draw:)
	)
)

(class SelectorDText of DText
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
		signal $0000
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
		type $0002
		key 0
		value 0
		object 0
		selector 0
		textLeft 0
		textTop 0
		textRight 0
		textBottom 0
		text 0
		mode 0
		fore 0
		back 254
		skip 254
		font 1
		borderColor -1
		dimmed 0
		rects 0
	)
	
	(method (setSize theNsRight)
		(super setSize: theNsRight)
		(= textRight
			(- (= nsRight theNsRight) (- textLeft nsLeft))
		)
	)
)
