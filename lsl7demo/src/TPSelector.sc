;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64865)
(include sci.sh)
(use Main)
(use PushButton)
(use GenDialog)
(use Plane)
(use String)
(use Array)
(use Actor)
(use System)


(class TPSelector of View
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
		nItems 0
		oDataArray 0
		nHeight 0
		nWidth 0
		oSelectNotify 0
		nSelectMethod 755
		nSelectValue 0
		oDoubleNotify 0
		nDoubleMethod 0
		nDoubleValue 0
		nClickTime 0
	)
	
	(method (init &tmp [temp0 2] temp2)
		(= nClickTime (- gameTime 30))
		(= bitmap (Bitmap 0 1 1 255 255 640 480))
		(Bitmap 5 bitmap 0 0 0 0 255)
		(= listPlane
			((Plane new:)
				name: {DSPlane}
				priority: (+ (GetHighPlanePri) 1)
				picture: -2
				init: 0 0 1 1
				yourself:
			)
		)
		(= first 0)
		(super init: &rest)
		(self setSize:)
		(if textList (textList eachElementDo: #init listPlane))
		(= temp2 0)
		(if (and upButton (!= upButton -1))
			(upButton init: plane)
		)
		(if (and downButton (!= downButton -1))
			(downButton init: plane)
		)
		(if temp2 (temp2 dispose:) (= temp2 0))
		(self draw:)
	)
	
	(method (dispose)
		(if textList (textList dispose:))
		(if oDataArray (oDataArray dispose:))
		(if bitmap (Bitmap 1 bitmap))
		(if listPlane (listPlane dispose:))
		(if upButton (upButton dispose:))
		(if downButton (downButton dispose:))
		(= nItems
			(= oDataArray
				(= textList
					(= listPlane (= upButton (= downButton (= bitmap 0))))
				)
			)
		)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(event globalize:)
		(if (listPlane onMe: event)
			(self forceCursor: (ScriptID 64006 0))
			(event claimed: 1)
			(event localize: listPlane)
			(if
				(and
					(== (event type?) evMOUSEBUTTON)
					(= temp0 (textList firstTrue: #onMe event))
				)
				(if
					(and
						(== (= temp1 (textList indexOf: temp0)) current)
						(< (- (GetTime) nClickTime) 30)
					)
					(self doDouble:)
				else
					(= nClickTime (GetTime))
					(self doSelect: temp1)
				)
			)
		else
			(self forceCursor: 0)
		)
		(event localize: plane)
		(event claimed?)
	)
	
	(method (posn)
		(super posn: &rest)
		(if listPlane
			(listPlane posn: x y plane)
			(UpdatePlane listPlane)
		)
	)
	
	(method (delete param1 &tmp temp0)
		(if
		(or (not argc) (< param1 0) (> param1 (- nItems 1)))
			(MonoOut {delete out of range in TPSelector})
			(return)
		)
		(= temp0 (textList at: param1))
		(textList delete: temp0)
		(temp0 dispose:)
		(if (> nItems (+ param1 1))
			(oDataArray
				move: param1 (+ param1 1) (- nItems (+ param1 1))
			)
		)
		(-- nItems)
		(if (== current nItems)
			(= first (Max 0 (- first 1)))
			(self doSelect: (- current 1))
		else
			(self doSelect: current)
		)
	)
	
	(method (setText param1 &tmp temp0 [temp1 2] temp3 temp4)
		(if (not param1)
			(if textList (textList dispose:) (= textList 0))
			(if oDataArray (oDataArray dispose:) (= oDataArray 0))
			(= nItems 0)
			(return)
		)
		(if param1
			(= temp3 (Str newWith: 200 {}))
			(= temp0 0)
			(while (and (< temp0 argc) (< (textList size:) 250))
				(temp3 copy: [param1 temp0])
				(self addItem: temp3)
				(++ temp0)
			)
			(temp3 dispose:)
			(if (not length) (= length argc))
		)
	)
	
	(method (getText theTheCurrent &tmp theCurrent)
		(if (not argc)
			(= theCurrent current)
		else
			(= theCurrent theTheCurrent)
		)
		(if
		(or (< theCurrent 0) (> theCurrent (- nItems 1)))
			(MonoOut {getText out of range in TPSelector})
			(return 0)
		)
		(return (Str with: ((textList at: theCurrent) text?)))
	)
	
	(method (scrollDown param1 &tmp temp0 temp1)
		(= first (+ first (= temp0 (if argc param1 else 1))))
		(if (> first (- nItems length))
			(= first (- nItems length))
		)
		(if (< current first)
			(self doSelect: first)
		else
			(self draw:)
		)
	)
	
	(method (scrollUp param1 &tmp temp0 temp1 temp2)
		(= first (- first (= temp0 (if argc param1 else 1))))
		(if (< first 0) (= first 0))
		(if
			(>
				current
				(= temp2 (- (+ first (Min length nItems)) 1))
			)
			(self doSelect: temp2)
		else
			(self draw:)
		)
	)
	
	(method (getSelectNum)
		(return current)
	)
	
	(method (getData param1)
		(if
		(or (not argc) (< param1 0) (> param1 (- nItems 1)))
			(MonoOut {getData out of range in TPSelector})
			(return)
		)
		(oDataArray at: param1)
	)
	
	(method (setData param1 param2)
		(if
		(or (< argc 2) (< param1 0) (> param1 (- nItems 1)))
			(MonoOut {setData out of range in TPSelector})
			(return)
		)
		(oDataArray at: param1 param2)
	)
	
	(method (addItem param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
		(if (or (not argc) (not param1))
			(MonoOut {Invalid call of addItem in TPSelector})
		)
		(if (< argc 2) (= temp1 0) else (= temp1 param2))
		(if (or (< argc 3) (== param3 -2))
			(= temp0 (- nItems 1))
		else
			(= temp0 param3)
		)
		(if (< argc 4) (= temp3 1) else (= temp3 param4))
		(if (or (< temp0 -1) (> temp0 (- nItems 1)))
			(MonoOut {addItem out of range in TPSelector})
			(return)
		)
		(if (not textList)
			(= textList ((Cast new:) name: {DSList} add: yourself:))
			(= oDataArray
				((IntArray new:) name: {DataArray} yourself:)
			)
		)
		(= temp2
			((TextItem new:)
				font: font
				text: (KArray 8 (KArray 9 param1))
				nMinWidth: width
				maxWidth: 640
				mode: 0
				yourself:
			)
		)
		(if listPlane (temp2 init: listPlane))
		(if (== temp0 -1)
			(textList addToFront: temp2)
		else
			(textList addAfter: (textList at: temp0) temp2)
		)
		(if (< (++ temp0) nItems)
			(oDataArray move: (+ temp0 1) temp0 (- nItems temp0))
		)
		(oDataArray at: temp0 temp1)
		(++ nItems)
		(if temp3 (self draw:))
	)
	
	(method (getWidth)
		(listPlane getWidth:)
	)
	
	(method (getHeight)
		(listPlane getHeight:)
	)
	
	(method (setSize &tmp temp0 textListNextNode temp2 newIntArray)
		(= newIntArray (IntArray new:))
		(= temp0 0)
		(if (not width)
			(= textListNextNode (KList 3 (textList elements?)))
			(while textListNextNode
				(textList nextNode: (KList 6 textListNextNode))
				(= temp2 (KList 8 textListNextNode))
				(KText 0 (newIntArray data?) (temp2 text?) font 0)
				(= temp0 (Max (newIntArray at: 2) temp0))
				(= textListNextNode (textList nextNode?))
			)
		)
		(KText 0 (newIntArray data?) {M} font 0)
		(= textHeight (+ (newIntArray at: 3) 2))
		(newIntArray dispose:)
		(= nWidth (Max width temp0))
		(= nHeight (* textHeight length))
		(if listPlane
			(listPlane
				setRect: x y (- (+ x nWidth) 1) (- (+ y nHeight) 1)
			)
		)
	)
	
	(method (draw &tmp temp0 temp1 temp2 temp3 temp4)
		(if (and textList (textList size:))
			(= temp3 (textList at: current))
			(= temp0 (KList 3 (textList elements?)))
			(= temp4 0)
			(while temp0
				(= temp1 (KList 6 temp0))
				(= temp2 (KList 8 temp0))
				(temp2
					posn: 0 (* (- temp4 first) textHeight)
					fore: (if (== temp3 temp2) back else fore)
					createBitmap:
				)
				(UpdateScreenItem temp2)
				(= temp0 temp1)
				(++ temp4)
			)
		)
	)
	
	(method (doSelect theTheCurrent &tmp theCurrent temp1)
		(if (not argc)
			(= theCurrent current)
		else
			(= theCurrent theTheCurrent)
		)
		(if
		(or (< theCurrent 0) (> theCurrent (- nItems 1)))
			(= theCurrent current)
		)
		(= current theCurrent)
		(= temp1 (- (+ first (Min length nItems)) 1))
		(if (< current first) (= first current))
		(if (> current temp1)
			(= first (+ (- current length) 1))
		)
		(self draw:)
		(if oSelectNotify
			(Eval oSelectNotify nSelectMethod self nSelectValue)
		)
	)
	
	(method (find param1 &tmp temp0 temp1 temp2 temp3)
		(if (or (not argc) (not param1))
			(MonoOut {illegal call of find in TPSelector})
			(return -1)
		)
		(= temp3 -1)
		(if (and textList (textList size:))
			(= temp1 (Str with: param1))
			(= temp0 0)
			(while (< temp0 nItems)
				(if
					(and
						(= temp2 (textList at: temp0))
						(temp2 text?)
						(temp1 compare: (temp2 text?))
					)
					(= temp3 temp0)
					(break)
				)
				(++ temp0)
			)
			(proc64896_7 temp1)
		)
		(return temp3)
	)
	
	(method (findData param1 &tmp temp0 [temp1 2] temp3)
		(if (not argc)
			(MonoOut {illegal call of findData in TPSelector})
			(return -1)
		)
		(= temp3 -1)
		(if (and oDataArray (oDataArray size:))
			(= temp0 0)
			(while (< temp0 nItems)
				(if (== param1 (oDataArray at: temp0))
					(= temp3 temp0)
					(break)
				)
				(++ temp0)
			)
		)
		(return temp3)
	)
	
	(method (doDouble)
		(if oDoubleNotify
			(Eval oDoubleNotify nDoubleMethod self nDoubleValue)
		)
	)
	
	(method (canScrollUp)
		(return (if (and nItems (> first 0)) (return 1) else (return 0)))
	)
	
	(method (canScrollDown)
		(return
			(if (and nItems (< first (- nItems length)))
				(return 1)
			else
				(return 0)
			)
		)
	)
)
