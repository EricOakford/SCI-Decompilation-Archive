;;; Sierra Script 1.0 - (do not remove this comment)
(script# EGODEAD)
(include game.sh) (include "26.shm")
(use Main)
(use DText)
(use Plane)
(use String)
(use Array)
(use IconBar)
(use Actor)
(use System)

(public
	EgoDead 0
)

(local
	deathMessage
	deathRoom
	deathView
	local3
	deathCycler =  1
	selectedRestore
)
(procedure (EgoDead reason roomNum deadView theCycler &tmp [temp0 2])
	(theGame handsOff:)
	(Palette PalIntensity 66 85 100)
	(cast eachElementDo: #perform (ScriptID ZCODE 0) 1)
	((ScriptID 0 21) doit: 100)
	(UpdatePlane
		((curRoom plane?) back: 0 picture: -1 yourself:)
	)
	(if (>= argc 1)
		(= deathMessage reason)
		(if (>= argc 2)
			(if roomNum
				(= deathRoom roomNum)
			else
				(= deathRoom curRoomNum)
			)
			(if (>= argc 3)
				(= deathView deadView)
				(if (>= argc 4)
					(= deathCycler theCycler)
				)
			else
				(= deathView 937)
			)
		else
			(= deathRoom curRoomNum)
			(= deathView 937)
		)
	else
		(= deathView 937)
		(= deathRoom EGODEAD)
		(= deathMessage C_GENERIC)
	)
	(deathIconView view: deathView)
	(sounds eachElementDo: #fade 0 1 5 1)
	(DeathControls init: show: dispose:)
)

(class DeathIcon of IconItem
	(properties
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
		((DeathControls deathCast?) add: self)
		(= plane deathPlane)
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
		(+= nsTop 5)
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
					setPri: 252
					init: (DeathControls deathCast?)
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
					setPri: 253
					init: (DeathControls deathCast?)
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
					init: (DeathControls deathCast?)
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
			bnt      code_0379
			lap      param1
			bnt      code_0379
			pTos     signal
			ldi      1
			and     
			bnt      code_0379
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_02fa:
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
			bnt      code_035f
			pushi    #localize
			pushi    1
			lofsa    deathPlane
			push    
			lat      temp0
			send     6
			pushi    #onMe
			pushi    1
			lst      temp0
			self     6
			bnt      code_0348
			lat      temp1
			not     
			bnt      code_02fa
			ldi      1
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_02fa
code_0348:
			lat      temp1
			bnt      code_02fa
			ldi      0
			sat      temp1
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
			jmp      code_02fa
code_035f:
			lst      temp1
			ldi      1
			eq?     
			bnt      code_0375
			ldi      0
			aTop     cel
			pushi    1
			pushSelf
			callk    UpdateScreenItem,  2
			pushi    0
			callk    FrameOut,  0
code_0375:
			lat      temp1
			jmp      code_037b
code_0379:
			ldi      1
code_037b:
			bnt      code_0399
			pushi    41
			pushi    #x
			pushi    #state
			pushi    0
			lofsa    DeathControls
			send     4
			push    
			ldi      65503
			and     
			push    
			lofsa    DeathControls
			send     6
			ldi      1
			ret     
			jmp      code_039c
code_0399:
			ldi      0
			ret     
code_039c:
			ret     
		)
	)
	
	(method (highlight tOrF)
		(text2 setPri: 0)
		(hiText setPri: 254)
		(= cel (if tOrF 2 else 0))
		(UpdateScreenItem self)
		(UpdateScreenItem text2)
		(UpdateScreenItem hiText)
		(FrameOut)
	)
)

(class DeathControls of IconBar
	(properties
		state NULL
		deathCast 0
	)
	
	(method (init &tmp temp0 oldCur)
		(= selectedRestore FALSE)
		(if (not deathCast)
			(= deathCast (Cast new:))
		else
			(SetDebug deathCast)
		)
		(= plane deathPlane)
		(deathPlane
			priority: (+ (GetHighPlanePri) 1)
			picture: -2
			init: 35 25 285 175 35 25 285 175
			setBitmap: 935 0 0 1
			addCast: deathCast
		)
		(self add: deathIconView)
		(deathIconTitle text: (String new:))
		(Message MsgGet deathRoom NULL V_DIE deathMessage 2
			((deathIconTitle text?) data?)
		)
		(self add: deathIconTitle)
		(deathIconText text: (String new:))
		(Message MsgGet deathRoom NULL V_DIE deathMessage 1
			((deathIconText text?) data?)
		)
		(self add: deathIconText)
		(deathIconRestore text: (String new:))
		(Message MsgGet EGODEAD N_DEATH NULL C_RESTORE 1
			((deathIconRestore text?) data?)
		)
		(self add: deathIconRestore)
		(deathIconRestart text: (String new:))
		(Message MsgGet EGODEAD N_DEATH NULL C_RESTART 1
			((deathIconRestart text?) data?)
		)
		(self add: deathIconRestart)
		(deathIconQuit text: (String new:))
		(Message MsgGet EGODEAD N_DEATH NULL C_QUIT 1
			((deathIconQuit text?) data?)
		)
		(self add: deathIconQuit)
		(self eachElementDo: #init self)
		((= oldCur (theIconBar getCursor:))
			view: 999
			loop: 0
			cel: 0
		)
		(theGame setCursor: oldCur)
		(theMusic number: 106 setLoop: 1 play:)
	)
	
	(method (doit &tmp event eType eMsg eMod temp4)
		(while
			(and
				(& state IB_ACTIVE)
				(= event ((user curEvent?) new:))
			)
			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(event localize: plane)
			(= eType (event type?))
			(= eMsg (event message?))
			(= eMod (event modifiers?))
			(if cuees (cuees eachElementDo: #doit))
			(if (== eType joyDown)
				(= eType keyDown)
				(= eMsg (if (& eMod shiftDown) ESC else ENTER))
				(= eMod 0)
				(event type: eType message: eMsg modifiers: eMod)
			)
			(MapKeyToDir event)
			(breakif (self dispatchEvent: event))
		)
	)
	
	(method (dispose)
		(plane deleteCast: self dispose:)
		(= deathCast (= plane 0))
		(if elements (DisposeList elements))
		(= size (= elements 0))
		((deathIconTitle text?) dispose:)
		((deathIconText text?) dispose:)
		(theMusic stop: signal: 0)
		(if selectedRestore
			(if (theGame restore:)
				(self init: show: dispose:)
			else
				(DisposeClone self)
			)
		)
	)
	
	(method (show &tmp temp0 temp1 theNextNode temp3 temp4)
		(sounds pause:)
		(|= state IB_ACTIVE)
		(plane addCast: deathCast)
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
	
	(method (dispatchEvent event &tmp [temp0 2] eventType eventMessage theHighlightedIcon eventClaimed theHighlightedIconSignal temp7 temp8)
		(= eventType (event type?))
		(= eventMessage (event message?))
		(= eventClaimed (event claimed?))
		(if
		(= theHighlightedIcon (self firstTrue: #onMe event))
			(= theHighlightedIconSignal (theHighlightedIcon signal?))
			(= temp7 (== theHighlightedIcon helpIconItem))
		)
		(if
			(and
				(== (deathIconView view?) 976)
				(== (deathIconView cel?) (deathIconView lastCel:))
			)
			(deathIconView cel: 0)
		)
		(if
			(and
				deathCycler
				(!= (deathIconView cel?) (deathIconView lastCel:))
				(== (++ local3) 4)
			)
			(= local3 0)
			(deathIconView setCel: (+ (deathIconView cel?) 1))
			(UpdateScreenItem deathIconView)
			(FrameOut)
		)
		(if (& eventType direction)
			(switch eventMessage
				(dirE
					(self advance:)
				)
				(dirS
					(self advance:)
				)
				(dirW
					(self retreat:)
				)
				(dirN
					(self retreat:)
				)
			)
		else
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
		)
		(return eventClaimed)
	)
)

(instance deathPlane of Plane
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

(instance deathIconView of DeathIcon
	(properties
		nsLeft 16
		nsTop 82
		x 40
		y 80
		priority 55
		view 937
		loop 0
	)
	
	(method (dispose)
		(DeleteScreenItem self)
		(DisposeClone self)
	)
	
	(method (show)
		(= x (+ nsLeft 20))
		(= y (+ nsTop 20))
		(= loop (= cel 0))
		(UpdateScreenItem self)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance deathIconTitle of DeathIcon
	(properties
		nsLeft 70
		nsTop 10
		x 8
		y 2
		priority 55
	)
	
	(method (init)
	)
	
	(method (show &tmp temp0 temp1 temp2)
		(= temp1 (IntArray with: 0 0 0 0))
		(TextSize (temp1 data?) (text data?) 300 0)
		(= nsRight (+ nsLeft (temp1 at: 2) 24))
		(= nsTop (+ nsTop 5))
		(= nsBottom (+ nsTop (temp1 at: 3) 10))
		(= text1
			((myDText new:)
				posn: 25 (+ nsTop 3)
				text: (text data?)
				font: 300
				fore: 92
				back: 254
				skip: 254
				setSize: 230
				setPri: 254
				init: (DeathControls deathCast?)
				yourself:
			)
		)
		(= text2
			((myDText new:)
				posn: 24 (+ nsTop 2)
				text: (text data?)
				font: 300
				fore: 86
				back: 254
				skip: 254
				setSize: 230
				setPri: 255
				init: (DeathControls deathCast?)
				yourself:
			)
		)
		(temp1 dispose:)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance deathIconText of DeathIcon
	(properties
		nsLeft 70
		nsTop 23
		x 8
		y 2
		priority 55
	)
	
	(method (init)
	)
	
	(method (show &tmp temp0 temp1)
		(= temp1 (IntArray with: 0 0 0 0))
		(TextSize (temp1 data?) (text data?) 0 0)
		(= nsRight (+ nsLeft (temp1 at: 2) 24))
		(= nsTop (+ nsTop 5))
		(= nsBottom (+ nsTop (temp1 at: 3) 10))
		(= x (+ nsLeft 17))
		(= y (+ nsTop 15))
		(= cel 0)
		(= text1
			((myDText new:)
				posn: (+ nsLeft 1) (+ nsTop 9)
				text: (text data?)
				font: 0
				fore: 92
				back: 254
				skip: 254
				setSize: 155
				setPri: 254
				init: (DeathControls deathCast?)
				yourself:
			)
		)
		(= text2
			((myDText new:)
				posn: nsLeft (+ nsTop 8)
				text: (text data?)
				font: 0
				fore: 86
				back: 254
				skip: 254
				setSize: 155
				setPri: 255
				init: (DeathControls deathCast?)
				yourself:
			)
		)
		(temp1 dispose:)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance deathIconRestore of DeathIcon
	(properties
		nsLeft 20
		nsTop 105
		x 8
		y 2
		priority 55
	)
	
	(method (select)
		(= selectedRestore 1)
		(DeathControls state: (& (DeathControls state?) $ffdf))
	)
)

(instance deathIconRestart of DeathIcon
	(properties
		nsLeft 90
		nsTop 105
		x 8
		y 2
		priority 55
	)
	
	(method (select)
		(DeathControls state: (& (DeathControls state?) $ffdf))
		(Bset 382)
	)
)

(instance deathIconQuit of DeathIcon
	(properties
		nsLeft 160
		nsTop 105
		x 8
		y 2
		priority 55
	)
	
	(method (select)
		(= quit 1)
		(DeathControls state: (& (DeathControls state?) $ffdf))
	)
)

(instance myDText of DText	
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
