;;; Sierra Script 1.0 - (do not remove this comment)
(script# 23)
(include game.sh) (include "23.shm")
(use Main)
(use DText)
(use Plane)
(use String)
(use Array)
(use IconBar)
(use User)
(use Actor)
(use System)

(public
	Teller 0
)

(local
	local0
	thiefSignResponse
	theTeller
	tellerCast
	tellCursorLoop
	tellCursorCel
	oldCanInput
	oldCanControl
)
(class TellerIcon of IconItem
	(properties
		nsTop 15
		view 935
		loop 5
		cel 0
		signal (| VICON RELVERIFY)
		highlightColor 255
		myHandle 0
		value 0
		myTeller 0
		text1 0
		text2 0
		highText 0
	)
	
	(method (init)
		(&= signal $fff7)
		(tellerCast add: self)
		(= plane tellerPlane)
		(AddScreenItem self)
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
	
	(method (dispose)
		(myHandle dispose:)
		(DeleteScreenItem self)
		(= highText (= text2 (= text1 (= myHandle 0))))
		(DisposeClone self)
	)
	
	(method (show &tmp temp0 len)
		(= len (IntArray with: 0 0 0 0))
		(TextSize
			(len data?)
			(myHandle data?)
			(myTeller font?)
			0
		)
		(= nsRight (+ nsLeft (len at: 2) 24))
		(+= nsTop 5)
		(= nsBottom (+ nsTop (len at: 3) 10))
		(= x (+ nsLeft 17))
		(= y (+ nsTop 15))
		(= cel 0)
		(UpdateScreenItem self)
		(if (not text1)
			(= text1
				((myDText new:)
					posn: 26 (+ nsTop 9)
					text: (myHandle data?)
					font: (myTeller font?)
					fore: 92
					back: 254
					skip: 254
					setSize:
					setPri: 253
					init: tellerCast
					yourself:
				)
			)
		)
		(if (not highText)
			(= highText
				((myDText new:)
					posn: 25 (+ nsTop 8)
					text: (myHandle data?)
					font: (myTeller font?)
					fore: 97
					back: 254
					skip: 254
					setSize:
					setPri: 0
					init: tellerCast
					yourself:
				)
			)
		)
		(if (not text2)
			(= text2
				((myDText new:)
					posn: 25 (+ nsTop 8)
					text: (myHandle data?)
					font: (myTeller font?)
					fore: 86
					back: 254
					skip: 254
					setSize:
					setPri: 254
					init: tellerCast
					yourself:
				)
			)
		)
		(len dispose:)
	)
	
	(method (onMe obj)
		(return
			(if
				(and
					(< nsLeft (obj x?))
					(< (obj x?) nsRight)
					(< nsTop (obj y?))
				)
				(< (obj y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (select relVer &tmp evt temp1 temp2)
		(if
			(if (and argc relVer (& signal RELVERIFY))
				(= cel (= temp1 1))
				(UpdateScreenItem self)
				(FrameOut)
				(while (!= ((= evt ((user curEvent:) new:)) type:) mouseUp)
					(evt localize: tellerPlane)
					(cond
						((self onMe: evt)
							(if (not temp1)
								(= cel (= temp1 1))
								(UpdateScreenItem self)
								(FrameOut)
							)
						)
						(temp1
							(= cel (= temp1 0))
							(UpdateScreenItem self)
							(FrameOut)
						)
					)
				)
				(if (== temp1 1)
					(= cel 0)
					(UpdateScreenItem self)
					(FrameOut)
				)
				temp1
			else
				1
			)
			(= temp2 (String copy: {Say Goodbye}))
			(if (myHandle compare: temp2)
				(= local0 1)
				(Bclr fHideCursor)
			)
			(temp2 dispose:)
			(myTeller iconValue: value)
			((myTeller theControls:)
				state: (& ((myTeller theControls:) state:) $ffdf)
			)
			(return TRUE)
		else
			(return FALSE)
		)
	)
	
	(method (highlight tOrF)
		(if tOrF
			(highText priority: 255)
			(= cel 2)
		else
			(highText priority: 0)
			(= cel 0)
		)
		(UpdateScreenItem highText)
		(UpdateScreenItem self)
		(FrameOut)
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
		(if theBitmap (Bitmap MapDispose theBitmap))
	)
)

(instance intList of List
	(method (dispose)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(DisposeClone self)
	)
)

(class Teller of Object
	(properties
		curNoun 0
		sayNoun 0
		verb 0
		modNum 0
		stack 0
		curList 0
		client 0
		iconValue 0
		theControls 0
		title 0
		window 0
		loopMenu 1
		tellCursor 0
		font 300
		rootNoun 0
		actionVerb 0
		nextTeller 0
		parentTeller 0
		talker 0
		thiefSign TRUE
	)
	
	(method (init obj m n v objNoun)
		(if
			(and
				((= client obj) actions?)
				((client actions?) isKindOf: Teller)
			)
			((client actions?) nextTeller: self)
			(= parentTeller (client actions?))
		else
			((= client obj) actions: self)
			(= parentTeller 0)
		)
		(if (> argc 1)
			(= modNum m)
			(if (> argc 2)
				(= sayNoun n)
				(if (> argc 3)
					(= verb v)
				)
			)
		else
			(= modNum curRoomNum)
		)
		((= stack (intList new:))
			addToFront:
				(if (== argc 5)
					(= rootNoun (= curNoun objNoun))
				else
					(= curNoun (client noun?))
					(= rootNoun (client noun?))
				)
		)
		(if (not actionVerb)
			(= actionVerb V_TALK)
		)
	)
	
	(method (dispose)
		(if stack (stack dispose:) (= stack 0))
		(cond 
			(nextTeller
				(if parentTeller
					(parentTeller nextTeller: nextTeller)
					(nextTeller parentTeller: parentTeller)
				)
			)
			(parentTeller (parentTeller nextTeller: 0))
			(client (client actions: 0))
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp theIconBarGetCursor)
		(cond 
			((== theVerb actionVerb)
				(= tellCursor (theCursor view?))
				(= tellCursorLoop (theCursor loop?))
				(= tellCursorCel (theCursor cel?))
				((= theIconBarGetCursor (theIconBar getCursor:))
					view: ARROW_CURSOR
					loop: 0
					cel: 0
				)
				(theGame setCursor: theIconBarGetCursor)
				(Bset fHideCursor)
				(Bset fCantSave)
				(= oldCanInput (User canInput:))
				(= oldCanControl (User canControl:))
				(User canControl: FALSE canInput: FALSE)
				(while (not (self respond:))
				)
				(if (not loopMenu)
					(theGame
						setCursor:
							(IconBarCursor
								view: tellCursor
								loop: tellCursorLoop
								cel: tellCursorCel
								yourself:
							)
					)
				)
			)
			((and nextTeller (nextTeller isKindOf: Teller))
				(nextTeller doVerb: theVerb)
			)
			(else (client doVerb: theVerb))
		)
		(return TRUE)
	)
	
	(method (respond &tmp objNoun temp1)
		(= iconValue 0)
		(self buildCaseList: showCases:)
		(if curList (curList dispose:) (= curList 0))
		(while (((user curEvent?) new:) type?)
		)
		(return
			(cond 
				((or (not iconValue) (== iconValue -999))
					(self clean:)
					(return TRUE)
				)
				((== iconValue 999)
					(FrameOut)
					(stack delete: curNoun)
					(= curNoun (stack at: 0))
					(self respond:)
				)
				(else
					(if (== iconValue -9999)
						(if thiefSignResponse
							(messager say: NULL V_THIEF_SIGN NULL 0 self modNum)
						else
							(messager say: N_THIEFSIGN NULL C_THIEF_SIGN 0 self 23)
						)
					else
						(self sayMessage:)
					)
					(if
						(and
							(Message MsgSize modNum curNoun verb iconValue 2)
							(>
								(= objNoun
									(Message MsgGetRefNoun modNum curNoun verb iconValue 2)
								)
								-1
							)
						)
						(= curNoun objNoun)
						(stack addToFront: curNoun)
					)
					(return TRUE)
				)
			)
		)
	)
	
	(method (sayMessage args)
		((User curEvent?) claimed: TRUE)
		(switch argc
			(0
				(messager say: sayNoun verb iconValue 0 self modNum)
			)
			(1
				(messager say: sayNoun verb [args 0] 0 self modNum)
			)
			(2
				(messager
					say: sayNoun [args 0] [args 1] 0 self modNum
				)
			)
			(else 
				(messager
					say: [args 0] [args 1] [args 2] 0 self modNum
				)
			)
		)
	)
	
	(method (buildCaseList &tmp i)
		(if curList (curList dispose:))
		(= curList (intList new:))
		(for ((= i 0)) (< i 150) ((++ i))
			(if (Message 0 modNum curNoun verb i (self getSeqNum:))
				(curList add: i)
			)
		)
	)
	
	(method (showCases param1 &tmp i temp1 newTellerIcon t temp4 theCase temp6 [temp7 3] msgExists theSeq temp12 temp13)
		(= tellerCast (Cast new:))
		(= theTeller self)
		(= temp13 0)
		(= temp12 150)
		(= temp1 0)
		(= t 12)
		(= temp6 0)
		(= newTellerIcon 0)
		(= theControls tellerControls)
		(if (not (theControls plane?))
			(theControls plane: tellerPlane)
		)
		(if title
			(+= t 15)
		)
		(for ((= i 0)) (< i (curList size:)) ((++ i))
			(= temp4 1)
			(= temp1 0)
			(while (and temp4 (< temp1 argc))
				(if
					(and
						(== (curList at: i) [param1 temp1])
						(not [param1 (+ temp1 1)])
					)
					(= temp4 0)
				)
				(+= temp1 2)
			)
			(if temp4
				(= temp13 1)
				(= theCase (curList at: i))
				(= theSeq (self getSeqNum: theCase))
				(if
					(not
						(= msgExists (Message MsgSize modNum curNoun verb theCase theSeq))
					)
					(break)
				)
				(= newTellerIcon (TellerIcon new:))
				(newTellerIcon myHandle: (String new: msgExists))
				(if (> (= msgExists (+ (* msgExists 7) 20)) temp12)
					(= temp12 msgExists)
				)
				(Message MsgGet modNum curNoun verb theCase theSeq
					((newTellerIcon myHandle?) data?)
				)
				(newTellerIcon value: theCase nsTop: t myTeller: self)
				(theControls add: newTellerIcon)
				(++ temp6)
				(+= t 15)
			)
		)
		(if (not newTellerIcon)
			(client doVerb: V_TALK)
			(theControls dispose:)
			(return -999)
		)
		(if (== client ego)
			(ego useSkill: COMM 10)
			(if
				(and
					(or [egoStats STEALTH] [egoStats PICK])
					thiefSign
					(== curNoun rootNoun)
				)
				(if (Message MsgSize modNum NULL V_THIEF_SIGN NULL 1)
					(= thiefSignResponse TRUE)
				else
					(= thiefSignResponse FALSE)
				)
				(= newTellerIcon (TellerIcon new:))
				(= msgExists (Message MsgSize TELLER NULL NULL C_THIEF_SIGN 1))
				(newTellerIcon myHandle: (String new: (+ msgExists 1)))
				(Message MsgGet TELLER NULL NULL C_THIEF_SIGN 1
					((newTellerIcon myHandle?) data?)
				)
				(newTellerIcon nsTop: t myTeller: self value: -9999)
				(theControls add: newTellerIcon)
				(+= t 15)
			)
		)
		((= newTellerIcon (TellerIcon new:))
			nsTop: t
			myTeller: self
		)
		(if (not (== curNoun rootNoun))
			(= msgExists (Message MsgSize TELLER NULL NULL C_SOMETHING_ELSE 1))
			(newTellerIcon myHandle: (String new: (+ msgExists 1)))
			(Message MsgGet TELLER NULL NULL C_SOMETHING_ELSE 1 ((newTellerIcon myHandle?) data?))
			(newTellerIcon value: 999)
		else
			(= msgExists (Message MsgSize TELLER NULL NULL C_ENOUGH_ALREADY 1))
			(newTellerIcon myHandle: (String new: (+ msgExists 1)))
			(Message MsgGet TELLER NULL NULL C_ENOUGH_ALREADY 1 ((newTellerIcon myHandle?) data?))
			(newTellerIcon value: -999)
		)
		(theControls add: newTellerIcon)
		((theControls plane?)
			left:
				(-
					(if talker
						(if (> (talker x?) 100) 10 else 150)
					else
						(- 160 (/ temp12 2))
					)
					10
				)
			top: (- 55 (* 7 temp6))
			right:
				(+
					(if talker
						(if (> (talker x?) 100) 150 else 310)
					else
						(+ 160 (/ temp12 2))
					)
					10
				)
			bottom: (- (+ 110 t) (* 7 temp6))
		)
		(if (and talker temp13)
			(if (not (Btst fSomeoneTalking))
				(Bset fSomeoneTalking)
				(cast eachElementDo: #perform (ScriptID 90 0) 1)
				(if (not (== curRoomNum 470))
					((ScriptID 0 21) doit: 100)
					(UpdatePlane
						((curRoom plane?) back: 0 picture: -1 yourself:)
					)
					(FrameOut)
				)
				(talker show:)
				(talkers delete: talker)
			)
			(= globalTeller self)
			(Bset fInPuzzle)
		)
		(return (theControls init: show: dispose:))
	)
	
	(method (getSeqNum)
		(return TRUE)
	)
	
	(method (cue)
		(if (!= (theCursor view?) ARROW_CURSOR)
			(= tellCursor (theCursor view?))
			(= tellCursorLoop (theCursor loop?))
			(= tellCursorCel (theCursor cel?))
			(theGame
				setCursor: (IconBarCursor view: ARROW_CURSOR loop: 0 cel: 0 yourself:)
			)
		)
		(if local0
			(= local0 0)
			(= globalTeller 0)
			(self clean:)
			(return)
		)
		(if loopMenu (self respond:) else (self clean:))
	)
	
	(method (clean)
		(Bclr fHideCursor)
		(Bclr fCantSave)
		(if oldCanInput (User canInput: TRUE))
		(if oldCanControl (User canControl: TRUE))
		(switch tellCursor
			(941
				(theIconBar curIcon: (theIconBar at: ICON_LOOK))
			)
			(942
				(theIconBar curIcon: (theIconBar at: ICON_DO))
			)
			(943
				(theIconBar curIcon: (theIconBar at: ICON_TALK))
			)
		)
		(theGame
			setCursor:
				(IconBarCursor
					view: tellCursor
					loop: tellCursorLoop
					cel: tellCursorCel
					yourself:
				)
		)
		(if talker
			(= globalTeller 0)
			(Bclr fInPuzzle)
			(talker dispose: 1)
			(if (not (== curRoomNum 470))
				((curRoom plane?)
					drawPic: (curRoom picture?) (curRoom style?)
				)
			)
			(if (Btst fSomeoneTalking)
				(Bclr fSomeoneTalking)
				(cast eachElementDo: #perform (ScriptID 90 0) 0)
				((ScriptID 0 21) doit:)
			)
		)
	)
)

(instance tellerControls of IconBar
	(properties
		state 0
	)
	
	(method (init)
		(plane
			priority: (+ (GetHighPlanePri) 1)
			picture: -2
			init:
			setInsetRect:
				(plane left:)
				(plane top?)
				(plane right:)
				(plane bottom?)
			setBitmap: 935 0 0 1
		)
		(self eachElementDo: #init self)
	)
	
	(method (doit &tmp evt eType eMsg eMod temp4)
		(while
			(and
				(& state IB_ACTIVE)
				(= evt ((user curEvent?) new:))
			)
			(= eType (evt type?))
			(= eMsg (evt message?))
			(= eMod (evt modifiers?))
			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(if (== eType joyDown)
				(= eType keyDown)
				(= eMsg (if (& eMod shiftDown) ESC else ENTER))
				(= eMod 0)
				(evt type: eType message: eMsg modifiers: eMod)
			)
			(evt localize: plane)
			(MapKeyToDir evt)
			(breakif (self dispatchEvent: evt))
		)
	)
	
	(method (dispose)
		(plane deleteCast: self dispose:)
		(= plane 0)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(DisposeClone self)
	)
	
	(method (show &tmp temp0 temp1 node temp3 str temp5)
		(sounds pause:)
		(|= state IB_ACTIVE)
		(plane addCast: tellerCast)
		(UpdatePlane plane)
		(if (theTeller title?)
			(= temp5 (Message MsgGet TELLER NULL NULL (theTeller title?) 1))
			(= str (String new: (+ temp5 1)))
			(Message MsgGet TELLER NULL NULL (theTeller title?) 1 (str data?))
			((myDText new:)
				posn: 16 21
				text: (str data?)
				font: (theTeller font?)
				fore: 92
				back: 254
				skip: 254
				setSize:
				init: tellerCast
			)
			((myDText new:)
				posn: 15 20
				text: (str data?)
				font: (theTeller font?)
				fore: 86
				back: 254
				skip: 254
				setSize:
				setPri: 255
				init: tellerCast
			)
		else
			(= str 0)
		)
		(= temp0 30)
		(= temp1 30)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (= temp3 (NodeValue node))) (return))
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
			(= node nextNode)
		)
		(self doit: hide:)
		(= theTeller 0)
		(= highlightedIcon (= curIcon 0))
		(if str (str dispose:))
	)
	
	(method (select thisIcon param2)
		(return
			(if (thisIcon select: (if (>= argc 2) param2))
				(if (not (& (thisIcon signal?) IMMEDIATE))
					(= curIcon thisIcon)
				)
				TRUE
			else
				FALSE
			)
		)
	)
	
	(method (highlight thisIcon param2)
		(if
			(and
				highlightedIcon
				(highlightedIcon isKindOf: TellerIcon)
			)
			(highlightedIcon highlight: 0)
		)
		(cond 
			((not (= highlightedIcon thisIcon)) 0)
			((& (thisIcon signal?) DISABLED)
				(= highlightedIcon 0)
			)
			(else
				(thisIcon highlight: 1)
				(if (and (>= argc 2) param2)
					(theGame
						setCursor: theCursor TRUE
							(+
								(thisIcon nsLeft?)
								(/
									(-
										(thisIcon nsRight?)
										(thisIcon nsLeft?)
									)
									2
								)
							)
							(- (thisIcon nsBottom?) 3)
					)
				)
			)
		)
	)
	
	(method (swapCurIcon)
	)
	
	(method (advanceCurIcon)
	)
	
	(method (dispatchEvent event &tmp eventY eventX eventType eventMessage theHighlightedIcon eventClaimed temp6)
		(= eventX (event x?))
		(= eventY (event y?))
		(= eventType (event type?))
		(= eventMessage (event message?))
		(= eventClaimed (event claimed?))
		(= theHighlightedIcon (self firstTrue: #onMe event))
		(if (& eventType direction)
			(switch eventMessage
				(dirE (self advance:))
				(dirS (self advance:))
				(dirW (self retreat:))
				(dirN (self retreat:))
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
					(if theHighlightedIcon
						(self select: theHighlightedIcon 1)
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
								(= eventClaimed (& temp6 $0040))
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

(instance tellerPlane of Plane
	(method (setBitmap theView theLoop theCel &tmp planeCast temp1 width temp3 height temp5 temp6 newView)
		(= planeCast (Cast new:))
		(self addCast: planeCast)
		((= newView (View new:))
			view: theView
			loop: theLoop
			cel: theCel
			posn: 0 0
			init: planeCast
		)
		(= width (CelWide theView theLoop theCel))
		(= height (CelHigh theView theLoop theCel))
		(= temp1 (+ (- right left) 1))
		(= temp3 (+ (- bottom top) 1))
		(if (< temp1 250)
			(= temp5 (/ (* temp1 128) width))
		else
			(= temp5 (* (/ (* (/ temp1 2) 128) width) 2))
		)
		(= temp6 (/ (* temp3 128) height))
		(newView scaleSignal: 1 scaleX: temp5 scaleY: temp6)
		(UpdateScreenItem newView)
	)
)
