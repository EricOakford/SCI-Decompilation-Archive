;;; Sierra Script 1.0 - (do not remove this comment)
(script# 881)
(include game.sh)
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use Inset)
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
	oldCur
)
(instance dInvD of Dialog
	
	(method (init &tmp l t temp2 i theText node obj)
		(= temp2 (= l (= t MARGIN)))
		(= i 0)
		(= node (inventory first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= theText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: l
							nsTop: t
							state: 3
							font: smallFont
							setSize:
							yourself:
						)
				)
			)
			(if
			(< temp2 (- (theText nsRight?) (theText nsLeft?)))
				(= temp2 (- (theText nsRight?) (theText nsLeft?)))
			)
			(if
				(>
					(= t
						(+ t (- (theText nsBottom?) (theText nsTop?)) 1)
					)
					140
				)
				(= t 4)
				(= l (+ l temp2 10))
				(= temp2 0)
			)
			(= node (inventory next: node))
		)
		(= window systemWindow)
		(self setSize:)
		(= newDButton (DButton new:))
		(newDButton
			text: {All Done!}
			setSize:
			moveTo: (- nsRight (+ 4 (newDButton nsRight?))) nsBottom
		)
		(newDButton
			move: (- (newDButton nsLeft?) (newDButton nsRight?)) 0
		)
		(self add: newDButton setSize: center:)
		(return i)
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
			(ego get: (inventory indexOf: (theNewDButton value?)))
		)
		(self eachElementDo: #dispose 1 dispose:)
	)
	
	(method (handleEvent event &tmp eMsg eType)
		(= eMsg (event message?))
		(switch (= eType (event type?))
			(keyDown
				(switch eMsg
					(UPARROW
						(= eMsg SHIFTTAB)
					)
					(DOWNARROW
						(= eMsg TAB))
				)
			)
			(direction
				(switch eMsg
					(dirN
						(= eMsg SHIFTTAB)
						(= eType keyDown)
					)
					(dirS
						(= eMsg TAB)
						(= eType keyDown)
					)
				)
			)
		)
		(event type: eType message: eMsg)
		(super handleEvent: event)
	)
)

(instance dCastD of Dialog
	
	(method (init &tmp l t temp2 i theText node obj)
		(= temp2 (= l (= t MARGIN)))
		(= i 0)
		(= node (cast first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(self
				add:
					((= theText (DText new:))
						value: obj
						text: (obj name?)
						nsLeft: l
						nsTop: t
						state: 3
						font: smallFont
						setSize:
						yourself:
					)
			)
			(if
			(< temp2 (- (theText nsRight?) (theText nsLeft?)))
				(= temp2 (- (theText nsRight?) (theText nsLeft?)))
			)
			(if
				(>
					(= t
						(+ t (- (theText nsBottom?) (theText nsTop?)) 1)
					)
					100
				)
				(= t 4)
				(= l (+ l temp2 10))
				(= temp2 0)
			)
			(= node (cast next: node))
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
		(return i)
	)
	
	(method (doit &tmp theNewDButton t)
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
	
	(method (handleEvent event &tmp eMsg eType)
		(= eMsg (event message?))
		(switch (= eType (event type?))
			(keyDown
				(switch eMsg
					(UPARROW
						(= eMsg SHIFTTAB)
					)
					(DOWNARROW
						(= eMsg TAB)
					)
				)
			)
			(direction
				(switch eMsg
					(dirN
						(= eMsg SHIFTTAB)
						(= eType keyDown)
					)
					(dirS
						(= eMsg TAB)
						(= eType keyDown)
					)
				)
			)
		)
		(event type: eType message: eMsg)
		(super handleEvent: event)
	)
)

(instance showFeatureCode of Code
	
	(method (doit obj param2 &tmp t l r b temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11)
		(cond 
			(param2
				(= t (obj brTop?))
				(= l (obj brLeft?))
				(= b (obj brBottom?))
				(= r (obj brRight?))
				(Graph GDrawLine t l t r myHighlightColor )
				(Graph GDrawLine b l b r myHighlightColor)
				(Graph GDrawLine t l b l myHighlightColor)
				(Graph GDrawLine t r b r myHighlightColor)
			)
			((not (obj isKindOf: Inset))
				(= t (obj nsTop?))
				(= l (obj nsLeft?))
				(= b (obj nsBottom?))
				(= r (obj nsRight?))
				(Graph GDrawLine t l t r global118)
				(Graph GDrawLine b l b r global118)
				(Graph GDrawLine t l b l global118)
				(Graph GDrawLine t r b r global118)
			)
		)
		(= temp4 (- (obj y?) 1))
		(= temp5 (- (obj x?) 1))
		(= temp7 (+ (obj y?) 1))
		(= temp6 (+ (obj x?) 1))
		(Graph GDrawLine temp4 temp5 temp4 temp6 global129)
		(Graph
			GDrawLine
			(+ temp4 1)
			temp5
			(+ temp4 1)
			temp6
			global129
		)
		(Graph GDrawLine temp7 temp5 temp7 temp6 global129)
		(= temp8 (Min t temp4))
		(= temp9 (Min l temp5))
		(= temp11 (Max b temp7))
		(= temp10 (Max r temp6))
		(Graph GReAnimate
			temp8
			temp9
			(+ temp11 1)
			(+ temp10 1)
		)
	)
)

(class NameFeatureCode of Code

	(method (init)
		(keyDownHandler addToFront: self)
		(theGame setCursor: ARROW_CURSOR)
		(self doit:)
	)
	
	(method (doit &tmp onMeAndLowYTheObj [temp1 40] evt [str 10])
		(while (not (self handleEvent: (= evt (Event new:))))
			(evt localize:)
			(OnMeAndLowY init:)
			(features eachElementDo: #perform OnMeAndLowY evt)
			(cast eachElementDo: #perform OnMeAndLowY evt)
			(if (= onMeAndLowYTheObj (OnMeAndLowY theObj?))
				(cond 
					(
						(and
							(not (onMeAndLowYTheObj isKindOf: Inset))
							(onMeAndLowYTheObj onMeCheck?)
							(!= (onMeAndLowYTheObj onMeCheck?) 26505)
						)
						(Format @str 881 0 (onMeAndLowYTheObj onMeCheck?))
					)
					((onMeAndLowYTheObj respondsTo: #view)
						(Format
							@str
							881
							1
							(onMeAndLowYTheObj view?)
							(onMeAndLowYTheObj loop?)
							(onMeAndLowYTheObj cel?)
						)
					)
					(else (Format @str 881 2 {}))
				)
				(DrawStatus
					(Format
						@temp1
						881
						3
						(evt x?)
						(evt y?)
						(onMeAndLowYTheObj name?)
						((onMeAndLowYTheObj -super-?) name?)
						@str
					)
				)
			)
			(evt dispose:)
		)
		(evt dispose:)
		(self dispose:)
	)
	
	(method (dispose)
		(DrawStatus 0)
		(DrawStatus {_} global118 0)
		(if
			(Print
				addText: {Erase outlines?}
				addButton: 0 {No} 0 12
				addButton: 1 {Yes} 40 12
				init:
			)
			(DrawPic (curRoom picture?) PLAIN)
			(if addToPics
				(addToPics doit:)
			)
		)
		(keyDownHandler delete: self)
		(theDoits delete: self)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(== (event type?) keyDown)
					(== (event message?) ESC)
				)
				(event claimed: TRUE)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(class JustifyText of Object
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
		(GetInput @local1 50
			{Enter text (after this, get help with '?')}
		)
		(theIconBar curIcon: gPepIcon5)
		(= oldCur theCursor)
		(theGame setCursor: 69 FALSE)
		(self doit: 1)
	)
	
	(method (doit param1 &tmp theX theY)
		(if (and argc param1) (= lastX (= lastY 0)))
		(= theX ((User curEvent?) x?))
		(= theY ((User curEvent?) y?))
		(if (or (!= theX lastX) (!= theY lastY))
			(if unders (Display 881 4 p_restore unders))
			(= unders
				(Display @local1
					p_at theX theY
					p_color global118
					p_back (if color global118 else myTextColor)
					p_font font
					p_save
				)
			)
			(= lastX theX)
			(= lastY theY)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(directionHandler delete: self)
		(theDoits delete: self)
		(theGame setCursor: oldCur TRUE)
		(UnLoad RES_MEMORY unders)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 evtX evtY eType eMsg eMod [temp6 25])
		(= evtX (event x?))
		(= evtY (event y?))
		(= eType (event type?))
		(= eMsg (event message?))
		(= eMod (event modifiers?))
		(cond 
			((== eType keyDown)
				(switch eMsg
					(ESC
						(Display 881 4 p_restore unders)
						(self dispose:)
					)
					(ENTER
						(self showCoord: event)
					)
					(6
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
						(theGame setCursor: theCursor 0 evtX evtY)
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
					(3
						(if (> (++ color) 15) (= color 0))
						(self doit: 1)
					)
					(`^?
						(Prints
							{Move text with mouse or direction keys\n
							SHIFT + arrows for fine adjustment\n\n
							ENTER or click shows text position\n\n
							Ctrl-F (shift click) changes font\n
							Ctrl-N to enter new text\n
							Ctrl-C (control click) changes color\n
							ESC aborts}
						)
					)
				)
				(event claimed: TRUE)
			)
			((== eType mouseDown)
				(cond 
					((& eMod shiftDown)
						(event type: keyDown message: 6)
						(self handleEvent: event)
					)
					((& eMod ctrlDown)
						(event type: keyDown message: 3)
						(self handleEvent: event)
					)
					(else
						(self showCoord: event)
						(event claimed: TRUE)
					)
				)
			)
			((& eType direction)
				(= temp0 (if (& eMod shiftDown) 1 else 10))
				(if (OneOf eMsg 3 2 4)
					(+= evtX temp0)
				)
				(if (OneOf eMsg 7 8 6)
					(+= evtX temp0)
				)
				(if (OneOf eMsg 8 1 2)
					(+= evtY temp0)
				)
				(if (OneOf eMsg 6 5 4)
					(+= evtY temp0)
				)
				(event claimed: TRUE)
				(theGame setCursor: theCursor FALSE evtX evtY)
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
