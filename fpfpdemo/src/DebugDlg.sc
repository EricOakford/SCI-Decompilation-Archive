;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUGDLG) ;25
(include game.sh)
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
	[local1 2]
)
(instance dInvD of Dialog
	(properties)
	
	(method (init &tmp theX theY temp2 i newDText node obj)
		(= temp2 (= theX (= theY MARGIN)))
		(= i 0)
		(= node (inventory first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= newDText (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: theX
							nsTop: theY
							state: 3
							font: 10
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
					(= theY
						(+ theY (- (newDText nsBottom?) (newDText nsTop?)))
					)
					140
				)
				(= theY 4)
				(= theX (+ theX temp2 5))
				(= temp2 0)
			)
			(= node (inventory next: node))
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
			(ego get: -1 (Inventory indexOf: (theNewDButton value?)))
		)
		(self eachElementDo: #dispose 1 dispose:)
	)
	
	(method (handleEvent event &tmp eMsg eType)
		(= eMsg (event message?))
		(switch (= eType (event type?))
			(keyDown
				(switch eMsg
					(UPARROW
						(= eMsg 3840)
					)
					(DOWNARROW
						(= eMsg 9)
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

(instance dCastD of Dialog
	(properties)
	
	(method (init &tmp theX theY temp2 i newDText node obj)
		(= temp2 (= theX (= theY MARGIN)))
		(= i 0)
		(= node (cast first:))
		(while node
			(= obj (NodeValue node))
			(++ i)
			(self
				add:
					((= newDText (DText new:))
						value: obj
						text: (obj name?)
						nsLeft: theX
						nsTop: theY
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
					(= theY
						(+ theY (- (newDText nsBottom?) (newDText nsTop?)) 1)
					)
					100
				)
				(= theY 4)
				(= theX (+ theX temp2 10))
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
	(properties)
	
	(method (doit obj param2 &tmp t l b r temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11)
		(if param2
			(= t (obj brTop?))
			(= l (obj brLeft?))
			(= r (obj brBottom?))
			(= b (obj brRight?))
			(Graph GDrawLine t l t b 40)
			(Graph GDrawLine r l r b 40)
			(Graph GDrawLine t l r l 40)
			(Graph GDrawLine t b r b 40)
		else
			(= t (obj nsTop?))
			(= l (obj nsLeft?))
			(= r (obj nsBottom?))
			(= b (obj nsRight?))
			(Graph GDrawLine t l t b 0)
			(Graph GDrawLine r l r b 0)
			(Graph GDrawLine t l r l 0)
			(Graph GDrawLine t b r b 0)
		)
		(= temp4 (- (obj y?) 1))
		(= temp5 (- (obj x?) 1))
		(= temp7 (+ (obj y?) 1))
		(= temp6 (+ (obj x?) 1))
		(Graph GDrawLine temp4 temp5 temp4 temp6 44)
		(Graph GDrawLine (+ temp4 1) temp5 (+ temp4 1) temp6 44)
		(Graph GDrawLine temp7 temp5 temp7 temp6 44)
		(= temp8 (Min t temp4))
		(= temp9 (Min l temp5))
		(= temp11 (Max r temp7))
		(= temp10 (Max b temp6))
		(Graph GReAnimate temp8 temp9 (+ temp11 1) (+ temp10 1))
	)
)

(class NameFeatureCode of Code
	(properties)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(theGame setCursor: 999)
		(self doit:)
	)
	
	(method (doit &tmp onMeAndLowYTheObj [str 40] evt [temp42 10])
		(while
		(not (self handleEvent: (= evt (Event new:))))
			(evt localize:)
			(OnMeAndLowY init:)
			(features eachElementDo: #perform OnMeAndLowY evt)
			(cast eachElementDo: #perform OnMeAndLowY evt)
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
						@str
						25
						3
						(evt x?)
						(evt y?)
						(onMeAndLowYTheObj name?)
						((onMeAndLowYTheObj -super-?) name?)
						@temp42
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
		(DrawStatus {_} 0 0)
		(if
			(Print
				addText: {Erase outlines?}
				addButton: 0 {No} 0 12
				addButton: 1 {Yes} 40 12
				init:
			)
			(DrawPic (curRoom picture?) PLAIN)
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
					(== (event type?) keyDown)
					(== (event message?) ESC)
				)
				(event claimed: TRUE)
				(return TRUE)
			else
				0
			)
		)
	)
)
