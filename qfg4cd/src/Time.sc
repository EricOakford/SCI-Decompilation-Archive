;;; Sierra Script 1.0 - (do not remove this comment)
(script# TIME)
(include game.sh)
(use Main)
(use EgoDead)
(use TextIcon)
(use DText)
(use Plane)
(use String)
(use Array)
(use IconBar)
(use Actor)
(use System)

(public
	EgoRests 0
	proc7_1 1
	doSleep 2
	showTime 3
	fixTime 4
	egoRests 5
	advanceTime 6
	egoSleeps 7
)

(local
	local0
	restChoice
	local2
	local3
	local4
	local5
	newCast
	cursorView
	cursorLoop
	cursorCel
)
(procedure (EgoRests theMin &tmp temp0 evt)
	(if (OneOf curRoomNum 330)
		(= restTime theMin)
		((= evt (Event new:)) type: mouseDown message: V_REST)
		(if (not (mouseDownHandler handleEvent: evt))
			(regions handleEvent: evt)
		)
		(evt dispose:)
	else
		(egoRests init: theMin 1)
	)
)

(procedure (proc7_1 theMin &tmp temp0 evt)
	(if (OneOf curRoomNum 330)
		(= restTime theMin)
		((= evt (Event new:)) type: mouseDown message: V_REST)
		(if (not (mouseDownHandler handleEvent: evt))
			(regions handleEvent: evt)
		)
		(evt dispose:)
	else
		(egoRests init: theMin 1)
	)
)

(instance doSleep of Code
	(method (init &tmp evt oldCur ret temp3 temp4 newCur)
		(= temp3 1)
		(= ret 0)
		(= oldCur ((theIconBar getCursor:) view?))
		(= local2 120)
		(sleepControls init: show: dispose:)
		((= newCur (theIconBar getCursor:))
			view: cursorView
			loop: cursorLoop
			cel: cursorCel
		)
		(theGame setCursor: newCur)
		(switch restChoice
			(1 (EgoRests 10))
			(2 (EgoRests 30))
			(3 (EgoRests 60))
			(4
				(if
					(OneOf curRoomNum
						440 270 330 350 740 730 530
						535 541 542 543 545
					)
					((= evt (Event new:)) type: mouseDown message: V_SLEEP)
					(= temp3 0)
					(if (not (mouseDownHandler handleEvent: evt))
						(regions handleEvent: evt)
					)
					(evt dispose:)
					(= ret TRUE)
				else
					(egoSleeps init: 7 0)
				)
			)
			(5
				(= ret TRUE)
			)
		)
		(return ret)
	)
)

(instance egoSleeps of Code
	(method (init theHour theMin &tmp sleptHours oldTime [str 60])
		(if
			(OneOf curRoomNum
				440 270 330 350 740
				730 530 535 541 542
				543 545
			)
			(= lostSleep 0)
			(= oldTime Clock)
			(switch argc
				(0
					(fixTime init: 5)
				)
				(1
					(fixTime init: theHour)
				)
				(else 
					(fixTime init: theHour theMin)
				)
			)
			(= sleptHours
				(/ (mod (- (+ Clock GAMEDAY) oldTime) GAMEDAY) GAMEHOUR)
			)
			(= [egoStats STAMINA] (ego maxStamina:))
			(if
				(and
					(not
						(ego takeDamage: (- (/ (* sleptHours (ego maxHealth:)) 16)))
					)
					(Btst fPoisoned)
				)
				(EgoDead 27 28 977 1 912)
				(return)
			)
			(= [egoStats MANA] (ego maxMana:))
			(if (and (> oldTime Clock) (Btst fGeasWarning))
				(NextDay)
			)
			(Bclr fReversal)
			(= reversalTimer 0)
			(= auraTimer 0)
			(= protectTimer 0)
			(= resistTimer 0)
			((ScriptID 0 21) doit:)
			(cond 
				((< sleptHours 2)
					(messager say: NULL NULL 12 1 0 TIME)
				)
				((< argc 3)
					(messager say: NULL NULL 3 1 0 TIME)
				)
			)
		else
			(messager say: 3 V_DOIT 11 0 0 TIME)
		)
	)
)

(instance showTime of Code
	(method (init &tmp whatDay evt oldTime)
		(= whatDay Day)
		(if (or (!= timeODay 6) (> Clock 500)) (++ whatDay))
		(if (>= (= oldTime Clock) 2780)
			(-= oldTime GAMEDAY)
			(= local5 1)
		)
		(if (<= oldTime 820)
			(= oldTime (+ oldTime 1800))
			(= local5 1)
		)
		(= local3 (/ (-= oldTime 1800) 16))
		(= local4 (Abs (/ oldTime 48)))
		(+= local3 81)
		(timePanel init: show: dispose:)
	)
)

(instance fixTime of Code
	(method (init theHour theMin &tmp oldTimeODay oldTime)
		(= oldTime Clock)
		(if (>= argc 1)
			(= Clock (* GAMEHOUR theHour))
			(= oldSysTime (GetTime SysTime1))
			(if (>= argc 2)
				(+= Clock (/ (* GAMEHOUR theMin) 60))
			)
		)
		(= oldTimeODay timeODay)
		(cond 
			((< (^ Clock 1) 300)
				(= timeODay TIME_MIDNIGHT))
			((< (^ Clock 1) 750)
				(= timeODay TIME_NOTYETDAWN)
			)
			((< (^ Clock 1) 1200)
				(= timeODay TIME_DAWN)
			)
			((< (^ Clock 1) 1650)
				(= timeODay TIME_MIDMORNING)
			)
			((< (^ Clock 1) 2100)
				(= timeODay TIME_MIDDAY)
			)
			((< (^ Clock 1) 2550)
				(= timeODay TIME_MIDAFTERNOON)
			)
			((< (^ Clock 1) 3000)
				(= timeODay TIME_SUNSET)
			)
			((< (^ Clock 1) 3450)
				(= timeODay TIME_NIGHT)
			)
			(else
				(= timeODay TIME_MIDNIGHT)
			)
		)
		(if (< Clock oldTime)
			(NextDay)
		)
		(CyclePalette)
		(if (and (== timeODay TIME_MIDNIGHT) (!= oldTimeODay TIME_MIDNIGHT))
			(if (== (++ lostSleep) 1)
				(messager say: NULL NULL 6 1 0 TIME)
			else
				(messager say: NULL NULL 7 1 0 TIME)
			)
		)
	)
)

(instance egoRests of Code
	(method (init theMin mess)
		(= lastRestDay Day)
		(= lastRestTime Clock)
		(ego
			useStamina: (- theMin)
			useMana: (- (/ theMin 5))
			takeDamage: (- (/ (+ theMin 5) 15))
		)
		(if mess
			(if (Btst fPoisoned)
				(messager say: 0 0 13 1 0 7)
			else
				(messager say: 0 0 9 1 0 7)
			)
		)
		(advanceTime init: 0 theMin)
	)
)

(instance advanceTime of Code
	(method (init addHours addMinutes &tmp newTime)
		(switch argc
			(1
				(= newTime (+ Clock (* GAMEHOUR addHours)))
			)
			(2
				(= newTime
					(+ Clock (* GAMEHOUR addHours) (/ (* GAMEHOUR addMinutes) 60))
				)
			)
		)
		(^= newTime 1)
		(if
			(or
				(and (< Clock 1100) (> newTime 1200))
				(and
					(< Clock 2500)
					(or (> newTime 2600) (< newTime Clock))
				)
			)
			(ego eatMeal:)
		)
		(fixTime
			init: (/ newTime GAMEHOUR) (/ (* (mod newTime GAMEHOUR) 60) GAMEHOUR)
		)
	)
)

(class SleepIcon of IconItem
	(properties
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
		(&= signal $fff7)
		(newCast add: self)
		(= plane sleepPlane)
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
		(= nsTop (+ nsTop 5))
		(= nsBottom (+ nsTop (temp1 at: 3) 10))
		(= x (+ nsLeft 17))
		(= y (+ nsTop 15))
		(= cel 0)
		(UpdateScreenItem self)
		(if (not text1)
			(= text1
				((myDText new:)
					posn: 26 (+ nsTop 9)
					text: (text data?)
					font: 300
					fore: 92
					back: 254
					skip: 254
					setSize:
					setPri: 253
					init: newCast
					yourself:
				)
			)
		)
		(if (not text2)
			(= text2
				((myDText new:)
					posn: 25 (+ nsTop 8)
					text: (text data?)
					font: 300
					fore: 86
					back: 254
					skip: 254
					setSize:
					setPri: 254
					init: newCast
					yourself:
				)
			)
		)
		(if (not hiText)
			(= hiText
				((myDText new:)
					posn: 25 (+ nsTop 8)
					text: (text data?)
					font: 300
					fore: 86
					back: 254
					skip: 254
					setSize:
					setPri: 0
					init: newCast
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
			bnt      code_0844
			lap      param1
			bnt      code_0844
			pTos     signal
			ldi      1
			and     
			bnt      code_0844
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_07c4:
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
			bnt      code_082a
			pushi    #localize
			pushi    1
			lofsa    sleepPlane
			push    
			lat      temp0
			send     6
			pushi    #onMe
			pushi    1
			lst      temp0
			self     6
			bnt      code_0813
			lat      temp1
			not     
			bnt      code_07c4
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_07c4
code_0813:
			lat      temp1
			bnt      code_07c4
			ldi      0
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_07c4
code_082a:
			lst      temp1
			ldi      1
			eq?     
			bnt      code_0840
			ldi      0
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_0840:
			lat      temp1
			jmp      code_0846
code_0844:
			ldi      1
code_0846:
			bnt      code_0864
			pushi    41
			pushi    #x
			pushi    #state
			pushi    0
			lofsa    sleepControls
			send     4
			push    
			ldi      65503
			and     
			push    
			lofsa    sleepControls
			send     6
			ldi      1
			ret     
			jmp      code_0867
code_0864:
			ldi      0
			ret     
code_0867:
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

(instance sleepControls of IconBar
	(properties
		state $0000
	)
	
	(method (init &tmp temp0 newCur)
		(= newCast (Cast new:))
		(= plane sleepPlane)
		(sleepPlane
			priority: (+ (GetHighPlanePri) 1)
			picture: -2
			init: 80 30 240 160
			setBitmap: 935 0 0 1
			addCast: newCast
		)
		(sleepIconPrompt text: (String new:))
		(Message
			MsgGet
			7
			2
			0
			1
			1
			((sleepIconPrompt text?) data?)
		)
		(self add: sleepIconPrompt)
		(sleepIcon10 text: (String new:))
		(Message MsgGet 7 2 0 0 1 ((sleepIcon10 text?) data?))
		(self add: sleepIcon10)
		(sleepIcon30 text: (String new:))
		(Message MsgGet 7 2 0 0 2 ((sleepIcon30 text?) data?))
		(self add: sleepIcon30)
		(sleepIcon60 text: (String new:))
		(Message MsgGet 7 2 0 0 3 ((sleepIcon60 text?) data?))
		(self add: sleepIcon60)
		(if Night
			(sleepIconMorn text: (String new:))
			(Message MsgGet 7 2 0 0 4 ((sleepIconMorn text?) data?))
			(self add: sleepIconMorn)
		)
		(sleepIconLater text: (String new:))
		(Message
			MsgGet
			7
			2
			0
			0
			5
			((sleepIconLater text?) data?)
		)
		(sleepIconLater nsTop: (if Night 85 else 70))
		(self add: sleepIconLater)
		(self eachElementDo: #init self)
		(= cursorView (theCursor view?))
		(= cursorLoop (theCursor loop?))
		(= cursorCel (theCursor cel?))
		((= newCur (theIconBar getCursor:))
			view: 999
			loop: 0
			cel: 0
		)
		(theGame setCursor: newCur)
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
		((sleepIconPrompt text?) dispose:)
		(CyclePalette)
		(DisposeClone self)
	)
	
	(method (show &tmp temp0 temp1 theNextNode temp3 [temp4 2])
		(sounds pause:)
		(= state (| state $0020))
		(plane addCast: newCast)
		(UpdatePlane plane)
		(= temp0 30)
		(= temp1 5)
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
	
	(method (dispatchEvent event &tmp [temp0 2] eventType eventMessage theHighlightedIcon eventClaimed theHighlightedIconSignal temp7 temp8)
		(= eventType (event type?))
		(= eventMessage (event message?))
		(= eventClaimed (event claimed?))
		(if
		(= theHighlightedIcon (self firstTrue: #onMe event))
			(= theHighlightedIconSignal (theHighlightedIcon signal?))
			(= temp7 (== theHighlightedIcon helpIconItem))
		)
		(switch eventType
			(nullEvt
				(if theHighlightedIcon
					(if (!= theHighlightedIcon highlightedIcon)
						(= oldMouseY 0)
						(self highlight: theHighlightedIcon)
					)
				else
					(self highlight: 0)
				)
			)
			(mouseDown
				(if
					(and
						theHighlightedIcon
						(self select: theHighlightedIcon 1)
					)
					(= eventClaimed (& theHighlightedIconSignal $0040))
				)
			)
			(keyDown
				(switch eventMessage
					(ESC (= eventClaimed 1))
					(DELETE (= eventClaimed 1))
					(ENTER
						(if (not theHighlightedIcon)
							(= theHighlightedIcon highlightedIcon)
						)
						(if
							(and
								theHighlightedIcon
								(self select: theHighlightedIcon)
							)
							(theHighlightedIcon doit:)
							(= eventClaimed (& theHighlightedIconSignal $0040))
						)
					)
					(SHIFTTAB (self retreat:))
					(TAB (self advance:))
				)
			)
		)
		(return eventClaimed)
	)
)

(instance sleepPlane of Plane
	(properties)
	
	(method (setBitmap param1 param2 param3 &tmp newCast_2 temp1 temp2 temp3 temp4 temp5 temp6 newView)
		(= newCast_2 (Cast new:))
		(self addCast: newCast_2)
		((= newView (View new:))
			view: param1
			loop: param2
			cel: param3
			posn: 0 0
			init: newCast_2
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

(instance sleepIconPrompt of SleepIcon
	(properties
		nsLeft 2
		nsTop 5
		x 8
		y 2
		priority 55
	)
	
	(method (init)
		(= nsTop 5)
		(= nsLeft 2)
	)
	
	(method (show &tmp temp0 temp1)
		(= temp1 (IntArray with: 0 0 0 0))
		(TextSize (temp1 data?) (text data?) 300 0)
		(= nsRight (+ nsLeft (temp1 at: 2) 24))
		(= nsBottom (+ nsTop (temp1 at: 3) 10))
		(= x (+ nsLeft 17))
		(= y (+ nsTop 15))
		(= cel 0)
		(= text1
			((myDText new:)
				posn: 26 (+ nsTop 9)
				text: (text data?)
				font: 300
				fore: 92
				back: 254
				skip: 254
				setSize:
				setPri: 254
				init: newCast
				yourself:
			)
		)
		(= text2
			((myDText new:)
				posn: 25 (+ nsTop 8)
				text: (text data?)
				font: 300
				fore: 86
				back: 254
				skip: 254
				setSize:
				setPri: 255
				init: newCast
				yourself:
			)
		)
		(temp1 dispose:)
	)
	
	(method (select)
		(return 0)
	)
	
	(method (highlight)
		(return 0)
	)
)

(instance sleepIcon10 of SleepIcon
	(properties
		nsLeft 2
		nsTop 25
		x 8
		y 2
		priority 55
	)
	
	(method (init)
		(= nsTop 25)
		(= nsLeft 2)
		(super init: &rest)
	)
	
	(method (select)
		(= restChoice 1)
		(sleepControls state: (& (sleepControls state?) $ffdf))
	)
)

(instance sleepIcon30 of SleepIcon
	(properties
		nsLeft 2
		nsTop 40
		x 8
		y 2
		priority 55
	)
	
	(method (init)
		(= nsTop 40)
		(= nsLeft 2)
		(super init: &rest)
	)
	
	(method (select)
		(= restChoice 2)
		(sleepControls state: (& (sleepControls state?) $ffdf))
	)
)

(instance sleepIcon60 of SleepIcon
	(properties
		nsLeft 2
		nsTop 55
		x 8
		y 2
		priority 55
	)
	
	(method (init)
		(= nsTop 55)
		(= nsLeft 2)
		(super init: &rest)
	)
	
	(method (select)
		(= restChoice 3)
		(sleepControls state: (& (sleepControls state?) $ffdf))
	)
)

(instance sleepIconMorn of SleepIcon
	(properties
		nsLeft 2
		nsTop 70
		x 8
		y 2
		priority 55
	)
	
	(method (init)
		(= nsTop 70)
		(= nsLeft 2)
		(super init: &rest)
	)
	
	(method (select)
		(= restChoice 4)
		(sleepControls state: (& (sleepControls state?) $ffdf))
	)
)

(instance sleepIconLater of SleepIcon
	(properties
		nsLeft 2
		nsTop 85
		x 8
		y 2
		priority 55
	)
	
	(method (init)
		(= nsLeft 2)
		(super init: &rest)
	)
	
	(method (select)
		(= restChoice 5)
		(sleepControls state: (& (sleepControls state?) $ffdf))
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

(instance timePanel of PuzzleBar
	(properties
		state $0000
	)
	
	(method (init &tmp temp0 temp1)
		(super init: &rest)
		(= temp1 0)
		(if local5 (= temp1 3))
		(self
			add:
				((timeIcon new:)
					nsLeft: 12
					x: 12
					nsTop: 6
					y: 6
					cel: 0
					loop: (+ 1 temp1)
					setPri: 250
					init: self
					yourself:
				)
		)
		(self
			add:
				((timeIcon new:)
					nsLeft: (+ local3 12)
					x: (+ local3 12)
					nsTop: (+ local4 6)
					y: (+ local4 6)
					cel: 0
					loop: (+ 2 temp1)
					setPri: 251
					init: self
					yourself:
				)
		)
		(self
			add:
				((timeIcon new:)
					nsLeft: 12
					x: 12
					nsTop: 6
					y: 6
					cel: 0
					loop: temp1
					setPri: 252
					init: self
					yourself:
				)
		)
		(self
			add:
				(daysIcon
					nsLeft: 12
					x: 12
					nsTop: 74
					y: 70
					cel: 0
					loop: 6
					init: self
					yourself:
				)
		)
	)
	
	(method (advance)
	)
	
	(method (dispatchEvent event)
		(return
			(if (OneOf (event type?) 1 4)
				(timePanel state: (& (timePanel state?) $ffdf))
				(return 1)
			else
				(super dispatchEvent: event)
			)
		)
	)
	
	(method (setPlane)
		(= usePlane 1)
		(plane
			priority: (+ (GetHighPlanePri) 1)
			init:
			bitmap: 0
			setRect: 72 40 258 132
			setBitmap: 935 0 0 1
			addCast: puzzleCast
		)
	)
	
	(method (addIcons)
	)
)

(instance timeIcon of TextIcon
	(properties
		view 938
		loop 6
	)
	
	(method (select)
		(timePanel state: (& (timePanel state?) $ffdf))
	)
	
	(method (highlight)
	)
)

(instance daysIcon of TextIcon
	(properties
		view 938
	)
	
	(method (show &tmp temp0 temp1 temp2)
		(UpdateScreenItem self)
		(= temp0 (String new: 30))
		(= temp1 (String new: 7))
		(Message MsgGet 7 0 0 10 1 (temp0 data?))
		(temp1 format: {%d} Day)
		(temp0 cat: temp1)
		(= text
			((myDText new:)
				x: 20
				y: 72
				text: (temp0 data?)
				font: 300
				fore: 92
				back: 254
				skip: 254
				setSize:
				setPri: 253
				init: (timePanel puzzleCast?)
				yourself:
			)
		)
		(= textType
			((myDText new:)
				x: 19
				y: 71
				text: (temp0 data?)
				font: 300
				fore: 0
				back: 254
				skip: 254
				setSize:
				setPri: 254
				init: (timePanel puzzleCast?)
				yourself:
			)
		)
		(temp0 dispose:)
		(temp1 dispose:)
	)
	
	(method (highlight)
	)
)
