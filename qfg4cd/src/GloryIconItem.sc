;;; Sierra Script 1.0 - (do not remove this comment)
(script# 58)
(include sci.sh)
(use Main)
(use String)
(use Print)
(use IconBar)
(use Cursor)
(use Invent)


(procedure (localproc_0b0b param1 param2 param3 &tmp temp0 temp1 temp2)
	(= temp2
		(+
			(/ (- (param1 nsRight?) (param1 x?)) 2)
			(param1 x?)
		)
	)
	(= temp1 param2)
	(while (>= (Abs (- temp1 param3)) 4)
		(if
			(= temp0
				(self
					firstTrue: #onMe (((user curEvent?) new:) x: temp2 y: temp1 yourself:)
				)
			)
			(return)
		)
		(if (< param2 param3)
			(= temp1 (+ temp1 4))
		else
			(= temp1 (- temp1 4))
		)
	)
)

(class GloryIconItem of IconI
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum 0
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
		signal $0001
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
		highlightColor 0
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
	)
	
	(method (ownedBy)
		(return 0)
	)
)

(class SliderIcon of GloryIconItem
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum 0
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
		signal $0001
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
		highlightColor 0
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
		theSlider 0
	)
	
	(method (updateSlider)
	)
)

(class NumInvItem of InvI
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum 16
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
		view 0
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0002
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
		message 0
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
		highlightColor 0
		lowlightColor 0
		helpVerb 1
		object 0
		selector 0
		owner 0
		script 0
		value 0
		amount 0
		chestAmout 0
	)
	
	(method (init)
		(= lowlightColor -1)
		(super init: &rest)
		(self setCursor: Cursor)
	)
	
	(method (doVerb theVerb &tmp newStr newStr_2)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(cond 
			((== theVerb 1)
				(if (> amount 1)
					(Message msgGET 16 0 theVerb 1 1 (newStr_2 data?))
					(Message msgGET 16 noun theVerb 0 1 (newStr data?))
					(Print
						addTextF: (newStr_2 data?) (newStr data?) amount value
						y: 165
						init:
					)
				else
					(Message msgGET 16 0 theVerb 2 1 (newStr_2 data?))
					(Message msgGET 16 noun theVerb 0 1 (newStr data?))
					(Print
						addTextF: (newStr_2 data?) (newStr data?) value
						y: 165
						init:
					)
				)
				(Print addText: noun theVerb 0 2 0 0 16 y: 165 init:)
			)
			((== theVerb 4) (super doVerb: theVerb))
			(
			(Message msgGET 16 noun theVerb 0 1 (newStr data?)) (Print addText: (newStr data?) y: 165 init:))
			(else (Print addText: 0 0 11 1 0 0 16 y: 165 init:))
		)
		(newStr dispose:)
		(newStr_2 dispose:)
	)
	
	(method (select)
		(cond 
			((== (inventory owner?) ego)
				(if (super select: &rest)
					(Cursor view: view loop: loop cel: cel)
				)
			)
			((super select: &rest) (Cursor view: view loop: loop cel: cel))
		)
	)
	
	(method (highlight)
	)
	
	(method (moveTo param1)
		(self
			signal: (| (self signal?) $0004)
			nsLeft: 0
			nsRight: 0
			nsTop: 0
			nsBottom: 0
		)
		(super moveTo: param1)
	)
	
	(method (cue)
		(inventory update:)
	)
	
	(method (roomGets &tmp temp0 temp1)
		(if (== curRoomNum 330)
			(++ chestAmout)
			(-- amount)
			(if (not amount) (= owner 0))
			(= temp1 (ScriptID 36 1))
			(temp1 signal: (| (temp1 signal?) $0008))
			(DeleteScreenItem temp1)
		)
	)
	
	(method (loseItem)
		(self owner: curRoomNum amount: 0)
	)
)

(class QGInv of Inv
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
		state $0400
		y 0
		normalHeading 9
		heading 0
		empty 27
		okButton 0
		selectIcon 0
		owner 0
		iconBottom 0
		iconRight 0
		planeTop 0
		planeBottom 0
		planeLeft 0
		planeRight 0
		curIndex 0
		numRow 0
		numCol 0
		rowMargin 0
		colMargin 0
		itemWide 0
		itemHigh 0
		numIcon 0
		theSlider 0
		iconMargin 0
		invLeft 15
		totalRow 0
		currentRow 0
		interval 0
	)
	
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4 [temp5 4] temp9 temp10 temp11)
		(asm
			pushi    #newWith
			pushi    2
			pushi    100
			lofsa    {LOOKUP\_ERROR}
			push    
			class    Str
			send     8
			sat      temp10
code_0b9f:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp1
			send     4
			bnt      code_0bbb
			jmp      code_0b9f
code_0bbb:
			pTos     state
			ldi      32
			and     
			bnt      code_1003
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp1
			pushi    #x
			pushi    0
			send     4
			sag      mouseX
			pushi    #y
			pushi    0
			lat      temp1
			send     4
			sag      mouseY
			pushi    #type
			pushi    0
			lat      temp1
			send     4
			sat      temp2
			pushi    #message
			pushi    0
			lat      temp1
			send     4
			sat      temp3
			pushi    #modifiers
			pushi    0
			lat      temp1
			send     4
			sat      temp4
			ldi      0
			sat      temp9
			lsg      tickOffset
			pushi    0
			callk    GetTime,  0
			add     
			sag      gameTime
			pushi    0
			callk    FrameOut,  0
			pushi    #localize
			pushi    1
			pTos     plane
			lat      temp1
			send     6
			pToa     curIcon
			bnt      code_0c88
			lat      temp4
			not     
			bnt      code_0c88
			pTos     curIcon
			pToa     selectIcon
			ne?     
			bnt      code_0c88
			lst      temp2
			ldi      1
			eq?     
			bt       code_0c5c
			lst      temp2
			ldi      4
			eq?     
			bnt      code_0c4f
			lst      temp3
			ldi      13
			eq?     
			bnt      code_0c4f
			ldi      1
			sat      temp9
			bt       code_0c5c
code_0c4f:
			lst      temp2
			ldi      32
			eq?     
			bnt      code_0c88
			ldi      1
			sat      temp9
			bnt      code_0c88
code_0c5c:
			pTos     curIcon
			pToa     helpIconItem
			ne?     
			bt       code_0c71
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      16
			and     
			bnt      code_0c88
code_0c71:
			pushi    #type
			pushi    1
			pushi    16384
			pushi    49
			pushi    1
			pushi    #message
			pushi    0
			pToa     curIcon
			send     4
			push    
			lat      temp1
			send     12
code_0c88:
			pushi    1
			lst      temp1
			callk    MapKeyToDir,  2
			pushi    #type
			pushi    0
			lat      temp1
			send     4
			sat      temp2
			pushi    #message
			pushi    0
			lat      temp1
			send     4
			sat      temp3
			lag      cuees
			bnt      code_0cb0
			pushi    #eachElementDo
			pushi    1
			pushi    69
			send     6
code_0cb0:
			pushi    #script
			pushi    0
			lag      theGame
			send     4
			sat      temp11
			bnt      code_0cd3
			pushi    #isKindOf
			pushi    1
			class    18
			push    
			lat      temp11
			send     6
			bnt      code_0cd3
			pushi    #doit
			pushi    0
			lat      temp11
			send     4
code_0cd3:
			lst      temp2
			ldi      1
			eq?     
			bnt      code_0cf1
			lat      temp4
			bnt      code_0cf1
			pushi    #advanceCurIcon
			pushi    0
			self     4
			pushi    #claimed
			pushi    1
			pushi    1
			lat      temp1
			send     6
			jmp      code_0bbb
code_0cf1:
			lst      temp2
			ldi      0
			eq?     
			bnt      code_0d1a
			pushi    #firstTrue
			pushi    2
			pushi    277
			lst      temp1
			self     8
			sat      temp0
			bnt      code_0d1a
			push    
			pToa     highlightedIcon
			ne?     
			bnt      code_0d1a
			pushi    #highlight
			pushi    1
			lst      temp0
			self     6
			jmp      code_0bbb
code_0d1a:
			lst      temp2
			ldi      1
			eq?     
			bt       code_0d37
			lst      temp2
			ldi      4
			eq?     
			bnt      code_0d2f
			lst      temp3
			ldi      13
			eq?     
			bt       code_0d37
code_0d2f:
			lst      temp2
			ldi      32
			eq?     
			bnt      code_0dc4
code_0d37:
			pToa     highlightedIcon
			bnt      code_0bbb
			pushi    453
			pushi    #y
			push    
			lst      temp2
			ldi      1
			eq?     
			push    
			self     8
			bnt      code_0bbb
			pTos     highlightedIcon
			pToa     okButton
			eq?     
			bnt      code_0d63
			pushi    #claimed
			pushi    1
			pushi    1
			lat      temp1
			send     6
			jmp      code_1003
			jmp      code_0bbb
code_0d63:
			pTos     highlightedIcon
			pToa     helpIconItem
			eq?     
			bnt      code_0dac
			pushi    #setCursor
			pushi    2
			pushi    #getCursor
			pushi    0
			pToa     highlightedIcon
			send     4
			push    
			pushi    1
			lag      theGame
			send     8
			pTos     state
			ldi      2048
			and     
			bnt      code_0d91
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_0bbb
code_0d91:
			pToa     helpIconItem
			bnt      code_0bbb
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			send     4
			push    
			ldi      16
			or      
			push    
			pToa     helpIconItem
			send     6
			jmp      code_0bbb
code_0dac:
			pToa     highlightedIcon
			aTop     curIcon
			pushi    #setCursor
			pushi    1
			pushi    #getCursor
			pushi    0
			self     4
			push    
			lag      theGame
			send     6
			jmp      code_0bbb
code_0dc4:
			lst      temp2
			ldi      16
			and     
			bnt      code_0e82
			lst      temp3
			dup     
			ldi      3
			eq?     
			bnt      code_0dde
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0e7e
code_0dde:
			dup     
			ldi      7
			eq?     
			bnt      code_0dee
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0e7e
code_0dee:
			dup     
			ldi      1
			eq?     
			bnt      code_0e28
			pToa     highlightedIcon
			bnt      code_0e1e
			pushi    3
			push    
			pushi    #nsTop
			pushi    0
			send     4
			push    
			ldi      1
			sub     
			push    
			pushi    0
			call     localproc_0b0b,  6
			sat      temp0
			bnt      code_0e1e
			pushi    #highlight
			pushi    2
			lst      temp0
			pushi    1
			self     8
			jmp      code_0e7e
code_0e1e:
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0e7e
code_0e28:
			dup     
			ldi      5
			eq?     
			bnt      code_0e6a
			pToa     highlightedIcon
			bnt      code_0e61
			pushi    3
			push    
			pushi    #nsBottom
			pushi    0
			send     4
			push    
			ldi      1
			add     
			push    
			pushi    #bottom
			pushi    0
			pToa     plane
			send     4
			push    
			call     localproc_0b0b,  6
			sat      temp0
			bnt      code_0e61
			pushi    #highlight
			pushi    2
			lst      temp0
			pushi    1
			self     8
			jmp      code_0e7e
code_0e61:
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0e7e
code_0e6a:
			dup     
			ldi      0
			eq?     
			bnt      code_0e7e
			lst      temp2
			ldi      4
			and     
			bnt      code_0e7e
			pushi    #advanceCurIcon
			pushi    0
			self     4
code_0e7e:
			toss    
			jmp      code_0bbb
code_0e82:
			lst      temp2
			ldi      4
			eq?     
			bnt      code_0eb8
			lst      temp3
			dup     
			ldi      9
			eq?     
			bnt      code_0e9b
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0eb4
code_0e9b:
			dup     
			ldi      3840
			eq?     
			bnt      code_0eab
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0eb4
code_0eab:
			dup     
			ldi      27
			eq?     
			bnt      code_0eb4
			jmp      code_1003
code_0eb4:
			toss    
			jmp      code_0bbb
code_0eb8:
			lst      temp2
			ldi      16384
			and     
			bnt      code_0bbb
			pushi    #firstTrue
			pushi    2
			pushi    277
			lst      temp1
			self     8
			sat      temp0
			bnt      code_0bbb
			lst      temp2
			ldi      8192
			and     
			bnt      code_0f49
			lat      temp0
			bnt      code_0f26
			pushi    #noun
			pushi    0
			send     4
			bnt      code_0f26
			pushi    7
			pushi    0
			pushi    #modNum
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    #noun
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    #helpVerb
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    0
			pushi    1
			pushi    #data
			pushi    0
			lat      temp10
			send     4
			push    
			callk    Message,  14
			bnt      code_0f26
			pushi    1
			lst      temp10
			calle    Prints,  2
code_0f26:
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      65519
			and     
			push    
			pToa     helpIconItem
			send     6
			pushi    #setCursor
			pushi    1
			lsg      normalCursor
			lag      theGame
			send     6
			jmp      code_0bbb
code_0f49:
			lst      temp0
			pToa     okButton
			eq?     
			bnt      code_0f5f
			pushi    #claimed
			pushi    1
			pushi    1
			lat      temp1
			send     6
			jmp      code_1003
			jmp      code_0bbb
code_0f5f:
			pushi    #isKindOf
			pushi    1
			class    InvI
			push    
			lat      temp0
			send     6
			not     
			bnt      code_0fc6
			pushi    #select
			pushi    2
			lst      temp0
			lat      temp9
			not     
			push    
			self     8
			bnt      code_0bbb
			lat      temp0
			aTop     curIcon
			pushi    #setCursor
			pushi    1
			pushi    #getCursor
			pushi    0
			self     4
			push    
			lag      theGame
			send     6
			lst      temp0
			pToa     helpIconItem
			eq?     
			bnt      code_0bbb
			pTos     state
			ldi      2048
			and     
			bnt      code_0fae
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_0bbb
code_0fae:
			pushi    26
			pushi    #x
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      16
			or      
			push    
			pToa     helpIconItem
			send     6
			jmp      code_0bbb
code_0fc6:
			pToa     curIcon
			bnt      code_0bbb
			pushi    #isKindOf
			pushi    1
			class    InvI
			push    
			pToa     curIcon
			send     6
			bnt      code_0fee
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			pToa     curIcon
			send     4
			push    
			lat      temp0
			send     6
			jmp      code_0bbb
code_0fee:
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			lat      temp1
			send     4
			push    
			lat      temp0
			send     6
			jmp      code_0bbb
code_1003:
			pushi    #dispose
			pushi    0
			lat      temp10
			send     4
			ret     
		)
	)
	
	(method (show)
		(= state (| state $0020))
		(theGame setCursor: (self getCursor:))
		(plane priority: (+ (GetHighPlanePri) 1))
		(self drawInvItems:)
		(plane
			setRect:
				planeLeft
				planeTop
				(+ planeLeft iconRight)
				(+
					planeTop
					iconBottom
					iconMargin
					(* (+ itemHigh rowMargin) numRow)
				)
		)
		(UpdatePlane plane)
		(return 1)
	)
	
	(method (ownedBy &tmp theNextNode temp1)
		(if (== owner ego)
			(return 1)
		else
			(= theNextNode (FirstNode elements))
			(while theNextNode
				(= nextNode (NextNode theNextNode))
				(= temp1 (NodeValue theNextNode))
				(if
				(and (temp1 isKindOf: InvI) (temp1 chestAmout?))
					(return 1)
				)
				(= theNextNode nextNode)
			)
		)
		(return 0)
	)
	
	(method (drawIcons &tmp temp0 temp1 qGInvFirst theIconBottom temp4)
		(= qGInvFirst (= temp0 (= theIconBottom (= temp4 0))))
		(= temp1 0)
		(= qGInvFirst (self first:))
		(while qGInvFirst
			(if
			(not ((= temp0 (NodeValue qGInvFirst)) isKindOf: InvI))
				(= iconRight
					(+
						iconRight
						(= temp4
							(CelWide (temp0 view?) (temp0 loop?) (temp0 cel?))
						)
					)
				)
				(if
					(and
						(>
							(= theIconBottom
								(CelHigh (temp0 view?) (temp0 loop?) (temp0 cel?))
							)
							iconBottom
						)
						(not (& (temp0 signal?) $0080))
					)
					(= iconBottom theIconBottom)
				)
				(if (not (& (temp0 signal?) $0080))
					(temp0
						x: temp1
						y: 0
						nsLeft: temp1
						nsTop: 0
						nsRight: (+ temp1 temp4)
						nsBottom: theIconBottom
					)
				)
				(= temp1 (+ (temp0 x?) temp4))
				(UpdateScreenItem temp0)
			)
			(= qGInvFirst (self next: qGInvFirst))
		)
		(= iconRight (- iconRight 34))
	)
	
	(method (drawInvItems &tmp theInvLeft temp1 temp2 qGInvFirst temp4 temp5 temp6 temp7 temp8 temp9)
		(= temp4 0)
		(= theInvLeft invLeft)
		(= temp1 (+ iconBottom 15))
		(= temp9 (= temp8 0))
		(= qGInvFirst (self first:))
		(= temp7 0)
		(while (< temp7 curIndex)
			(if
				(and
					((= temp2 (NodeValue qGInvFirst)) isKindOf: InvI)
					(not (& (temp2 signal?) $0004))
				)
				(DeleteScreenItem temp2)
				(temp2 signal: (| (temp2 signal?) $0004))
			)
			(++ temp7)
			(= qGInvFirst (self next: qGInvFirst))
		)
		(if (== owner ego)
			(= qGInvFirst qGInvFirst)
			(while qGInvFirst
				(if
					(and
						((= temp2 (NodeValue qGInvFirst)) isKindOf: InvI)
						(temp2 amount?)
					)
					(if
						(>
							(= temp5
								(CelHigh (temp2 view?) (temp2 loop?) (temp2 cel?))
							)
							temp4
						)
						(= temp4 temp5)
					)
					(temp2
						x: theInvLeft
						y: temp1
						nsTop: temp1
						nsLeft: theInvLeft
						nsRight:
							(+
								theInvLeft
								(CelWide (temp2 view?) (temp2 loop?) (temp2 cel?))
							)
						nsBottom: (+ temp1 temp5)
					)
					(if (& (temp2 signal?) $0004)
						(temp2 signal: (& (temp2 signal?) $fffb))
						(AddScreenItem temp2)
					else
						(UpdateScreenItem temp2)
					)
					(if
						(or
							(>
								(= theInvLeft
									(+
										theInvLeft
										(CelWide (temp2 view?) (temp2 loop?) (temp2 cel?))
										colMargin
									)
								)
								iconRight
							)
							(>= (++ temp9) numCol)
						)
						(= temp9 0)
						(= theInvLeft invLeft)
						(= temp1 (+ temp1 itemHigh rowMargin))
						(if (>= (++ temp8) numRow) (break))
					)
				)
				(= qGInvFirst (self next: qGInvFirst))
			)
		else
			(= qGInvFirst qGInvFirst)
			(while qGInvFirst
				(if
					(and
						((= temp2 (NodeValue qGInvFirst)) isKindOf: InvI)
						(temp2 chestAmout?)
					)
					(if
						(>
							(= temp5
								(CelHigh (temp2 view?) (temp2 loop?) (temp2 cel?))
							)
							temp4
						)
						(= temp4 temp5)
					)
					(temp2
						x: theInvLeft
						y: temp1
						nsTop: temp1
						nsLeft: theInvLeft
						nsRight:
							(+
								theInvLeft
								(CelWide (temp2 view?) (temp2 loop?) (temp2 cel?))
							)
						nsBottom: (+ temp1 temp5)
					)
					(if (& (temp2 signal?) $0004)
						(temp2 signal: (& (temp2 signal?) $fffb))
						(AddScreenItem temp2)
					else
						(UpdateScreenItem temp2)
					)
					(if
						(or
							(>
								(= theInvLeft
									(+
										theInvLeft
										(CelWide (temp2 view?) (temp2 loop?) (temp2 cel?))
										colMargin
									)
								)
								iconRight
							)
							(>= (++ temp9) numCol)
						)
						(= temp9 0)
						(= theInvLeft invLeft)
						(= temp1 (+ temp1 itemHigh rowMargin))
						(if (>= (++ temp8) numRow) (break))
					)
				)
				(= qGInvFirst (self next: qGInvFirst))
			)
		)
		(if qGInvFirst
			(= qGInvFirst (self next: qGInvFirst))
			(while qGInvFirst
				(if
					(and
						((= temp2 (NodeValue qGInvFirst)) isKindOf: InvI)
						(not (& (temp2 signal?) $0004))
					)
					(DeleteScreenItem temp2)
					(temp2 signal: (| (temp2 signal?) $0004))
				)
				(= qGInvFirst (self next: qGInvFirst))
			)
		)
	)
	
	(method (setCurIndex param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(asm
			lsp      param1
			ldi      0
			lt?     
			bnt      code_0a92
			ldi      65535
			jmp      code_0a94
code_0a92:
			ldi      1
code_0a94:
			sat      temp1
			ldi      0
			sat      temp2
			pToa     curIndex
			sat      temp4
			ldi      0
			sat      temp2
code_0aa2:
			lst      temp2
			pushi    1
			lsp      param1
			callk    Abs,  2
			lt?     
			bnt      code_0af7
			lst      temp4
			pToa     size
			lt?     
			bnt      code_0af7
			lst      temp4
			ldi      0
			ge?     
			bnt      code_0af7
			lst      temp4
			lat      temp1
			add     
			sat      temp4
			pushi    0
			le?     
			bnt      code_0aa2
			pprev   
			pTos     size
			ldi      1
			sub     
			le?     
			bnt      code_0aa2
			pushi    #isKindOf
			pushi    1
			class    InvI
			push    
			pushi    #at
			pushi    1
			lst      temp4
			self     6
			sat      temp3
			send     6
			bnt      code_0aa2
			pushi    #ownedBy
			pushi    1
			pTos     owner
			lat      temp3
			send     6
			bnt      code_0aa2
			+at      temp2
			jmp      code_0aa2
code_0af7:
			lst      temp2
			pushi    1
			lsp      param1
			callk    Abs,  2
			lt?     
			bnt      code_0b06
			ret     
			jmp      code_0b0a
code_0b06:
			lat      temp4
			aTop     curIndex
code_0b0a:
			ret     
		)
	)
	
	(method (getInvNum &tmp qGInvFirst temp1 temp2)
		(= temp1 0)
		(if (== owner ego)
			(= qGInvFirst (self first:))
			(while qGInvFirst
				(if
					(and
						((= temp2 (NodeValue qGInvFirst)) isKindOf: InvI)
						(temp2 amount?)
					)
					(++ temp1)
				)
				(= qGInvFirst (self next: qGInvFirst))
			)
		else
			(= qGInvFirst (self first:))
			(while qGInvFirst
				(if
					(and
						((= temp2 (NodeValue qGInvFirst)) isKindOf: InvI)
						(temp2 chestAmout?)
					)
					(++ temp1)
				)
				(= qGInvFirst (self next: qGInvFirst))
			)
		)
		(if (= totalRow (/ temp1 numCol))
			(= interval (/ 59 totalRow))
		else
			(= interval 60)
		)
	)
)
