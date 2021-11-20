;;; Sierra Script 1.0 - (do not remove this comment)
(script# 91)
(include sci.sh)
(use Main)
(use DText)
(use Plane)
(use String)
(use Array)
(use IconBar)
(use Actor)
(use System)

(public
	ConfirmPrompt 0
)

(local
	gCursorNumberView
	local1
)
(procedure (ConfirmPrompt param1 param2 param3 param4 param5 &tmp temp0 theCursorLoop theCursorCel)
	(= gCursorNumberView (theCursor view?))
	(= theCursorLoop (theCursor loop?))
	(= theCursorCel (theCursor cel?))
	(theGame
		setCursor: ((theIconBar getCursor:)
			view: 999
			loop: 0
			cel: 0
			yourself:
		)
	)
	(QControls
		init: param1 param2 param3 param4 param5
		show:
		dispose:
	)
	(theGame
		setCursor:
			((theIconBar getCursor:)
				view: gCursorNumberView
				loop: theCursorLoop
				cel: theCursorCel
				yourself:
			)
	)
	(QControls state: (& (QControls state?) $ffdf))
	(FrameOut)
	(return local1)
)

(class QIcon of IconI
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 15
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
		view 935
		loop 5
		cel 0
		bitmap 0
		yStep 2
		signal $0101
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
		type $4000
		message -1
		modifiers $0000
		mainView 0
		mainLoop 0
		mainCel 0
		maskView 0
		maskLoop 0
		maskCel 0
		cursorView -1
		cursorLoop -1
		cursorCel -1
		highlightColor 255
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
		text 0
		value 0
		text1 0
		text2 0
		hiText 0
	)
	
	(method (init &tmp temp0)
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
		(= signal (& signal $fff7))
		((QControls qCast?) add: self)
		(= plane qPlane)
		(AddScreenItem self)
	)
	
	(method (dispose)
		(text dispose:)
		(DeleteScreenItem self)
		(= hiText (= text2 (= text1 (= text 0))))
		(DisposeClone self)
	)
	
	(method (show &tmp temp0 temp1)
		(= temp1 (IntArray with: 0 0 0 0))
		(TextSize (temp1 data?) (text data?) 300 0)
		(= nsRight (+ nsLeft (temp1 at: 2) 24))
		(= nsBottom (+ nsTop (temp1 at: 3) 10))
		(= x (+ nsLeft 17))
		(= y (+ nsTop 15))
		(= cel 0)
		(UpdateScreenItem self)
		(if (not text1)
			(= text1
				((myDText new:)
					posn: (+ nsLeft 27) (+ nsTop 9)
					text: (text data?)
					font: 300
					fore: 92
					back: 254
					skip: 254
					setSize:
					setPri: 253
					init: (QControls qCast?)
					yourself:
				)
			)
		)
		(if (not text2)
			(= text2
				((myDText new:)
					posn: (+ nsLeft 26) (+ nsTop 8)
					text: (text data?)
					font: 300
					fore: 86
					back: 254
					skip: 254
					setSize:
					setPri: 254
					init: (QControls qCast?)
					yourself:
				)
			)
		)
		(if (not hiText)
			(= hiText
				((myDText new:)
					posn: (+ nsLeft 26) (+ nsTop 8)
					text: (text data?)
					font: 300
					fore: 86
					back: 254
					skip: 254
					setSize:
					setPri: 0
					init: (QControls qCast?)
					yourself:
				)
			)
		)
		(temp1 dispose:)
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(< nsLeft (theObjOrX x?))
					(< (theObjOrX x?) nsRight)
					(< nsTop (theObjOrX y?))
				)
				(< (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (select param1 &tmp temp0 temp1 temp2)
		(asm
			lap      argc
			bnt      code_0323
			lap      param1
			bnt      code_0323
			pTos     signal
			ldi      1
			and     
			bnt      code_0323
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_02a4:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp0
			send     4
			push    
			ldi      2
			ne?     
			bnt      code_0309
			pushi    #localize
			pushi    1
			lofsa    qPlane
			push    
			lat      temp0
			send     6
			pushi    #onMe
			pushi    1
			lst      temp0
			self     6
			bnt      code_02f2
			lat      temp1
			not     
			bnt      code_02a4
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_02a4
code_02f2:
			lat      temp1
			bnt      code_02a4
			ldi      0
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_02a4
code_0309:
			lst      temp1
			ldi      1
			eq?     
			bnt      code_031f
			ldi      0
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_031f:
			lat      temp1
			jmp      code_0325
code_0323:
			ldi      1
code_0325:
			bnt      code_0343
			pushi    41
			pushi    #x
			pushi    #state
			pushi    0
			lofsa    QControls
			send     4
			push    
			ldi      65503
			and     
			push    
			lofsa    QControls
			send     6
			ldi      1
			ret     
			jmp      code_0346
code_0343:
			ldi      0
			ret     
code_0346:
			ret     
		)
	)
	
	(method (highlight param1)
		(text2 setPri: 0)
		(hiText setPri: 254)
		(= cel (if param1 2 else 0))
		(UpdateScreenItem self)
		(UpdateScreenItem text2)
		(UpdateScreenItem hiText)
		(FrameOut)
	)
)

(class QControls of IconBar
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		walkIconItem 0
		plane 0
		state $0000
		y 0
		qCast 0
	)
	
	(method (init param1 param2 param3 param4 param5 &tmp temp0 theIconBarGetCursor)
		(= qCast (Cast new:))
		(qCast add:)
		(= plane qPlane)
		(qPlane
			priority: (+ (GetHighPlanePri) 1)
			picture: -2
			init: 60 60 260 140 60 60 260 140
			setBitmap: 935 0 0 1
			addCast: qCast
		)
		(qIconPrompt text: (Str new:))
		(Message
			msgGET
			param1
			param2
			param3
			param4
			param5
			((qIconPrompt text?) data?)
		)
		(self add: qIconPrompt)
		(qIconYes text: (Str new:))
		(Message msgGET 0 0 0 14 1 ((qIconYes text?) data?))
		(self add: qIconYes)
		(qIconNo text: (Str new:))
		(Message msgGET 0 0 0 15 1 ((qIconNo text?) data?))
		(self add: qIconNo)
		(self eachElementDo: #init self)
		((= theIconBarGetCursor (theIconBar getCursor:))
			view: 999
			loop: 0
			cel: 0
		)
		(theGame setCursor: theIconBarGetCursor)
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4)
		(while
			(and
				(& state $0020)
				(= temp0 ((user curEvent?) new:))
			)
			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(temp0 localize: plane)
			(= temp1 (temp0 type?))
			(= temp2 (temp0 message?))
			(= temp3 (temp0 modifiers?))
			(if cuees (cuees eachElementDo: #doit))
			(if (== temp1 32)
				(= temp1 4)
				(= temp2 (if (& temp3 $0003) 27 else 13))
				(= temp3 0)
				(temp0 type: temp1 message: temp2 modifiers: temp3)
			)
			(MapKeyToDir temp0)
			(breakif (self dispatchEvent: temp0))
		)
	)
	
	(method (dispose)
		(plane deleteCast: self dispose:)
		(= plane 0)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(DisposeClone self)
	)
	
	(method (show &tmp temp0 temp1 theNextNode temp3 temp4)
		(sounds pause:)
		(= state (| state $0020))
		(plane addCast: qCast)
		(UpdatePlane plane)
		(= temp0 30)
		(= temp1 30)
		(= theNextNode (FirstNode elements))
		(while theNextNode
			(= nextNode (NextNode theNextNode))
			(if (not (= temp3 (NodeValue theNextNode))) (return))
			(if
				(and
					(not (& (temp3 signal?) $0080))
					(<= (temp3 nsRight?) 0)
				)
				(temp3 show: temp0 temp1)
				(= temp0 (+ 20 (temp3 nsRight?)))
			else
				(temp3 show:)
			)
			(= theNextNode nextNode)
		)
		(self doit: hide:)
		(= highlightedIcon (= curIcon 0))
	)
	
	(method (select theCurIcon param2)
		(return
			(if (theCurIcon select: (if (>= argc 2) param2))
				(if (not (& (theCurIcon signal?) $0002))
					(= curIcon theCurIcon)
				)
				1
			else
				0
			)
		)
	)
	
	(method (highlight theHighlightedIcon param2)
		(if highlightedIcon (highlightedIcon highlight: 0))
		(cond 
			((not (= highlightedIcon theHighlightedIcon)) 0)
			((& (theHighlightedIcon signal?) $0004) (= highlightedIcon 0))
			(else
				(theHighlightedIcon highlight: 1)
				(if (and (>= argc 2) param2)
					(theGame
						setCursor:
							theCursor
							1
							(+
								(theHighlightedIcon nsLeft?)
								(/
									(-
										(theHighlightedIcon nsRight?)
										(theHighlightedIcon nsLeft?)
									)
									2
								)
							)
							(- (theHighlightedIcon nsBottom?) 3)
					)
				)
			)
		)
	)
	
	(method (swapCurIcon)
	)
	
	(method (advanceCurIcon)
	)
	
	(method (dispatchEvent event &tmp [temp0 2] eventType eventMessage temp4 eventClaimed temp6 temp7 temp8)
		(= eventType (event type?))
		(= eventMessage (event message?))
		(= eventClaimed (event claimed?))
		(if (= temp4 (self firstTrue: #onMe event))
			(= temp6 (temp4 signal?))
			(= temp7 (== temp4 helpIconItem))
		)
		(switch eventType
			(evNULL
				(if temp4
					(if (!= temp4 highlightedIcon)
						(= oldMouseY 0)
						(self highlight: temp4)
					)
				else
					(self highlight: 0)
				)
			)
			(evMOUSEBUTTON
				(if (and temp4 (self select: temp4 1))
					(= eventClaimed (& temp6 $0040))
				)
			)
		)
		(return eventClaimed)
	)
)

(instance qPlane of Plane
	(properties)
	
	(method (setBitmap param1 param2 param3 &tmp newCast temp1 temp2 temp3 temp4 temp5 temp6 newView)
		(= newCast (Cast new:))
		(self addCast: newCast)
		((= newView (View new:))
			view: param1
			loop: param2
			cel: param3
			posn: 0 0
			init: newCast
		)
		(= temp2 (CelWide param1 param2 param3))
		(= temp4 (CelHigh param1 param2 param3))
		(= temp1 (+ (- right left) 1))
		(= temp3 (+ (- bottom top) 1))
		(= temp5 (/ (* temp1 128) temp2))
		(= temp6 (/ (* temp3 128) temp4))
		(newView scaleSignal: 1 scaleX: temp5 scaleY: temp6)
		(UpdateScreenItem newView)
	)
)

(instance qIconPrompt of QIcon
	(properties
		nsLeft 15
		nsTop 0
		x 8
		priority 255
	)
	
	(method (init)
		((QControls qCast?) add: self)
		(= plane qPlane)
	)
	
	(method (show &tmp temp0 temp1)
		(= temp1 (IntArray with: 0 0 0 0))
		(TextSize (temp1 data?) (text data?) 300 0)
		(= nsRight (+ nsLeft (temp1 at: 2) 24))
		(= nsBottom (+ nsTop (temp1 at: 3) 10))
		(= x (+ nsLeft 17))
		(= y (+ nsTop 15))
		(= cel 0)
		(if (not text1)
			(= text1
				((myDText new:)
					posn: (+ nsLeft 1) (+ nsTop 9)
					text: (text data?)
					font: 300
					fore: 92
					back: 254
					skip: 254
					setSize: 160
					setPri: 254
					init: (QControls qCast?)
					yourself:
				)
			)
		)
		(if (not text2)
			(= text2
				((myDText new:)
					posn: nsLeft (+ nsTop 8)
					text: (text data?)
					font: 300
					fore: 86
					back: 254
					skip: 254
					setSize: 160
					setPri: 255
					init: (QControls qCast?)
					yourself:
				)
			)
		)
		(temp1 dispose:)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance qIconYes of QIcon
	(properties
		nsLeft 5
		nsTop 40
		nsRight 50
		nsBottom 50
		x 5
		y 50
		priority 55
	)
	
	(method (select)
		(= local1 1)
		(QControls state: (& (QControls state?) $ffdf))
	)
)

(instance qIconNo of QIcon
	(properties
		nsLeft 80
		nsTop 40
		nsRight 100
		nsBottom 50
		x 80
		y 50
		priority 55
	)
	
	(method (select)
		(= local1 0)
		(QControls state: (& (QControls state?) $ffdf))
	)
)

(instance myDText of DText
	(properties)
	
	(method (dispose &tmp planeCasts theBitmap)
		(= theBitmap 0)
		(if bitmap (= theBitmap bitmap) (= bitmap 0))
		(cast delete: self)
		(if (self isNotHidden:) (DeleteScreenItem self))
		((= planeCasts (plane casts?))
			eachElementDo: #delete self
		)
		(= plane 0)
		(DisposeClone self)
		(if theBitmap (Bitmap 1 theBitmap))
	)
)
