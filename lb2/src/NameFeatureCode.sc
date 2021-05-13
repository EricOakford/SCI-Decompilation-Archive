;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include sci.sh)
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use Invent)
(use User)
(use System)

(public
	dInvD 0
	dCastD 1
	showFeatureCode 2
)

(local
	newDButton
	local1
	theGForm
)
(instance dInvD of Dialog
	(properties)
	
	(method (init &tmp temp0 temp1 temp2 temp3 newDText inventoryFirst temp6)
		(= temp2 (= temp0 (= temp1 4)))
		(= temp3 0)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(= temp6 (NodeValue inventoryFirst))
			(++ temp3)
			(if (temp6 isKindOf: InvI)
				(self
					add:
						((= newDText (DText new:))
							value: temp6
							text: (temp6 name?)
							nsLeft: temp0
							nsTop: temp1
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
			)
			(if
				(>
					(= temp1
						(+ temp1 (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					140
				)
				(= temp1 4)
				(= temp0 (+ temp0 temp2 10))
				(= temp2 0)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {Outta here!}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton nsRight?))) nsBottom
		)
		(newDButton
			move: (- (newDButton nsLeft?) (newDButton nsRight?)) 0
		)
		(self add: newDButton setSize: center:)
		(return temp3)
	)
	
	(method (doit &tmp theNewDButton)
		(self init:)
		(self open: 4 15)
		(= theNewDButton newDButton)
		(repeat
			(if
				(or
					(not (= theNewDButton (super doit: theNewDButton)))
					(== theNewDButton -1)
					(== theNewDButton newDButton)
				)
				(break)
			)
			(ego get: -1 (Inv indexOf: (theNewDButton value?)))
		)
		(self eachElementDo: #dispose 1 dispose:)
	)
	
	(method (handleEvent event &tmp eventMessage eventType)
		(= eventMessage (event message?))
		(switch (= eventType (event type?))
			(4
				(switch eventMessage
					(KEY_UP (= eventMessage 3840))
					(KEY_NUMPAD2 (= eventMessage 9))
				)
			)
			(64
				(switch eventMessage
					(JOY_UP
						(= eventMessage 3840)
						(= eventType 4)
					)
					(JOY_DOWN
						(= eventMessage 9)
						(= eventType 4)
					)
				)
			)
		)
		(event type: eventType message: eventMessage)
		(super handleEvent: event)
	)
)

(instance dCastD of Dialog
	(properties)
	
	(method (init &tmp temp0 temp1 temp2 temp3 newDText castFirst temp6)
		(= temp2 (= temp0 (= temp1 4)))
		(= temp3 0)
		(= castFirst (cast first:))
		(while castFirst
			(= temp6 (NodeValue castFirst))
			(++ temp3)
			(self
				add:
					((= newDText (DText new:))
						value: temp6
						text: (temp6 name?)
						nsLeft: temp0
						nsTop: temp1
						state: 3
						font: smallFont
						setSize:
						yourself:
					)
			)
			(if
			(< temp2 (- (newDText nsRight?) (newDText nsLeft?)))
				(= temp2 (- (newDText nsRight?) (newDText nsLeft?)))
			)
			(if
				(>
					(= temp1
						(+ temp1 (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					100
				)
				(= temp1 4)
				(= temp0 (+ temp0 temp2 10))
				(= temp2 0)
			)
			(= castFirst (cast next: castFirst))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {exit}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton nsRight?))) nsBottom
		)
		(newDButton
			move: (- (newDButton nsLeft?) (newDButton nsRight?)) 0
		)
		(self add: newDButton setSize: center:)
		(return temp3)
	)
	
	(method (doit &tmp theNewDButton temp1)
		(self init:)
		(self open: 4 15)
		(= theNewDButton newDButton)
		(repeat
			(if
				(or
					(not (= theNewDButton (super doit: theNewDButton)))
					(== theNewDButton -1)
					(== theNewDButton newDButton)
				)
				(break)
			)
			(= gTheNewDButtonValue (theNewDButton value?))
		)
		(self dispose:)
	)
	
	(method (handleEvent event &tmp eventMessage eventType)
		(= eventMessage (event message?))
		(switch (= eventType (event type?))
			(4
				(switch eventMessage
					(KEY_UP (= eventMessage 3840))
					(KEY_NUMPAD2 (= eventMessage 9))
				)
			)
			(64
				(switch eventMessage
					(JOY_UP
						(= eventMessage 3840)
						(= eventType 4)
					)
					(JOY_DOWN
						(= eventMessage 9)
						(= eventType 4)
					)
				)
			)
		)
		(event type: eventType message: eventMessage)
		(super handleEvent: event)
	)
)

(instance showFeatureCode of Code
	(properties)
	
	(method (doit param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11)
		(if param2
			(= temp0 (param1 brTop?))
			(= temp1 (param1 brLeft?))
			(= temp3 (param1 brBottom?))
			(= temp2 (param1 brRight?))
			(Graph grDRAW_LINE temp0 temp1 temp0 temp2 global159)
			(Graph grDRAW_LINE temp3 temp1 temp3 temp2 global159)
			(Graph grDRAW_LINE temp0 temp1 temp3 temp1 global159)
			(Graph grDRAW_LINE temp0 temp2 temp3 temp2 global159)
		else
			(= temp0 (param1 nsTop?))
			(= temp1 (param1 nsLeft?))
			(= temp3 (param1 nsBottom?))
			(= temp2 (param1 nsRight?))
			(Graph grDRAW_LINE temp0 temp1 temp0 temp2 global151)
			(Graph grDRAW_LINE temp3 temp1 temp3 temp2 global151)
			(Graph grDRAW_LINE temp0 temp1 temp3 temp1 global151)
			(Graph grDRAW_LINE temp0 temp2 temp3 temp2 global151)
		)
		(= temp4 (- (param1 y?) 1))
		(= temp5 (- (param1 x?) 1))
		(= temp7 (+ (param1 y?) 1))
		(= temp6 (+ (param1 x?) 1))
		(Graph grDRAW_LINE temp4 temp5 temp4 temp6 global162)
		(Graph
			grDRAW_LINE
			(+ temp4 1)
			temp5
			(+ temp4 1)
			temp6
			global162
		)
		(Graph grDRAW_LINE temp7 temp5 temp7 temp6 global162)
		(= temp8 (Min temp0 temp4))
		(= temp9 (Min temp1 temp5))
		(= temp11 (Max temp3 temp7))
		(= temp10 (Max temp2 temp6))
		(Graph
			grREDRAW_BOX
			temp8
			temp9
			(+ temp11 1)
			(+ temp10 1)
		)
	)
)

(class NameFeatureCode of Code
	(properties)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(theGame setCursor: 999)
		(self doit:)
	)
	
	(method (doit &tmp onMeAndLowYTheObj [temp1 40] newEvent [temp42 10])
		(while
		(not (self handleEvent: (= newEvent (Event new:))))
			(newEvent localize:)
			(OnMeAndLowY init:)
			(features eachElementDo: #perform OnMeAndLowY newEvent)
			(cast eachElementDo: #perform OnMeAndLowY newEvent)
			(if (= onMeAndLowYTheObj (OnMeAndLowY theObj?))
				(cond 
					(
						(and
							(onMeAndLowYTheObj onMeCheck?)
							(!= (onMeAndLowYTheObj onMeCheck?) 26505)
						)
						(Format @temp42 25 0 (onMeAndLowYTheObj onMeCheck?))
					)
					((onMeAndLowYTheObj respondsTo: #view)
						(Format
							@temp42
							25
							1
							(onMeAndLowYTheObj view?)
							(onMeAndLowYTheObj loop?)
							(onMeAndLowYTheObj cel?)
						)
					)
					(else (Format @temp42 25 2 {}))
				)
				(DrawStatus
					(Format
						@temp1
						25
						3
						(newEvent x?)
						(newEvent y?)
						(onMeAndLowYTheObj name?)
						((onMeAndLowYTheObj -super-?) name?)
						@temp42
					)
				)
			)
			(newEvent dispose:)
		)
		(newEvent dispose:)
		(self dispose:)
	)
	
	(method (dispose)
		(DrawStatus 0)
		(DrawStatus {_} global151 0)
		(if
			(Print
				addText: {Erase outlines?}
				addButton: 0 {No} 0 12
				addButton: 1 {Yes} 40 12
				init:
			)
			(DrawPic (curRoom picture?) dpOPEN_NO_TRANSITION)
			(if addToPics (addToPics doit:))
		)
		(keyDownHandler delete: self)
		(theDoits delete: self)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(== (event type?) evKEYBOARD)
					(== (event message?) KEY_ESCAPE)
				)
				(event claimed: 1)
				(return 1)
			else
				0
			)
		)
	)
)

(class JustifyText of Obj
	(properties
		lastX 0
		lastY 0
		unders 0
		font 2510
		color 0
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(directionHandler addToFront: self)
		(theDoits addToFront: self)
		(= local1 0)
		(GetInput
			@local1
			50
			{Enter text (after this, get help with '?')}
		)
		(theIconBar curIcon: (theIconBar at: 5))
		(= theGForm theCursor)
		(theGame setCursor: 69 0)
		(self doit: 1)
	)
	
	(method (doit param1 &tmp temp0 temp1)
		(asm
			lap      argc
			bnt      code_0b03
			lap      param1
			bnt      code_0b03
			ldi      0
			aTop     lastY
			aTop     lastX
code_0b03:
			pushi    #x
			pushi    0
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			send     4
			sat      temp0
			pushi    #y
			pushi    0
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			send     4
			sat      temp1
			lst      temp0
			pToa     lastX
			ne?     
			bt       code_0b2e
			lst      temp1
			pToa     lastY
			ne?     
			bnt      code_0b6e
code_0b2e:
			pToa     unders
			bnt      code_0b3e
			pushi    4
			pushi    25
			pushi    4
			pushi    108
			push    
			callk    Display,  8
code_0b3e:
			pushi    11
			lea      @local1
			push    
			pushi    100
			lst      temp0
			lst      temp1
			pushi    102
			pToa     color
			lsgi     global151
			pushi    103
			bnt      code_0b58
			lag      global151
			jmp      code_0b5a
code_0b58:
			lag      myHighlightColor
code_0b5a:
			push    
			pushi    105
			pTos     font
			pushi    107
			callk    Display,  22
			aTop     unders
			lat      temp0
			aTop     lastX
			lat      temp1
			aTop     lastY
code_0b6e:
			ret     
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(theDoits delete: self)
		(theGame setCursor: theGForm 1)
		(UnLoad 133 unders)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 eventX eventY eventType eventMessage eventModifiers [temp6 25])
		(= eventX (event x?))
		(= eventY (event y?))
		(= eventType (event type?))
		(= eventMessage (event message?))
		(= eventModifiers (event modifiers?))
		(cond 
			((== eventType evKEYBOARD)
				(switch eventMessage
					(KEY_ESCAPE
						(Display 25 4 108 unders)
						(self dispose:)
					)
					(KEY_RETURN
						(self showCoord: event)
					)
					(JOY_DOWNLEFT
						(switch font
							(1207 (= font 2107))
							(2107 (= font 2108))
							(2108 (= font 2407))
							(2407 (= font 2510))
							(2510 (= font 4115))
							(4115 (= font 999))
							(999 (= font 0))
							(else 
								(= font (GetNumber {Font Number:}))
							)
						)
						(theGame setCursor: theCursor 0 eventX eventY)
						(self doit: 1)
					)
					(14
						(= temp6 0)
						(GetInput @temp6 50 {Enter text (then get help with `?')})
						(if temp6
							(StrCpy @local1 @temp6)
							(= lastX (= lastY 0))
							(self doit: 1)
						)
					)
					(JOY_RIGHT
						(if (> (++ color) 15) (= color 0))
						(self doit: 1)
					)
					(KEY_QUESTION
						(Prints
							{Move text with mouse or direction keys\nSHIFT + arrows for fine adjustment\n\nENTER or click shows text position\n\nCtrl-F (shift click) changes font\nCtrl-N to enter new text\nCtrl-C (control click) changes color\nESC aborts}
						)
					)
				)
				(event claimed: 1)
			)
			((== eventType evMOUSEBUTTON)
				(cond 
					((& eventModifiers emSHIFT) (event type: 4 message: 6) (self handleEvent: event))
					((& eventModifiers emCTRL) (event type: 4 message: 3) (self handleEvent: event))
					(else (self showCoord: event) (event claimed: 1))
				)
			)
			((& eventType evJOYSTICK)
				(= temp0 (if (& eventModifiers emSHIFT) 1 else 10))
				(if (OneOf eventMessage 3 2 4)
					(= eventX (+ eventX temp0))
				)
				(if (OneOf eventMessage 7 8 6)
					(= eventX (- eventX temp0))
				)
				(if (OneOf eventMessage 8 1 2)
					(= eventY (- eventY temp0))
				)
				(if (OneOf eventMessage 6 5 4)
					(= eventY (+ eventY temp0))
				)
				(event claimed: 1)
				(theGame setCursor: theCursor 0 eventX eventY)
				(self doit:)
			)
		)
	)
	
	(method (showCoord param1 &tmp [temp0 20])
		(Print
			addTextF: @temp0 {Position: %d, %d} (param1 x?) (param1 y?)
			init:
		)
		(self dispose:)
	)
)
