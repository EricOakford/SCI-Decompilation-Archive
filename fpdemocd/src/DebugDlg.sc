;;; Sierra Script 1.0 - (do not remove this comment)
(script# DEBUGDLG)
(include game.sh)
(use Main)
(use Intrface)
(use Print)
(use Dialog)
(use Window)
(use Invent)
(use User)
(use System)

(public
	dInvDialog 0
	dCastDialog 1
	showFeatureCode 2
)

(local
	yesI
	theText
	oldCursor
)
(instance dInvDialog of Dialog
	(properties
		name "dInvD"
	)
	
	(method (init &tmp lastX lastY widest num el node obj)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(= widest (= lastX (= lastY MARGIN)))
		(= num 0)
		(for ((= node (inventory first:))) node ((= node (inventory next: node)))
			(= obj (NodeValue node))
			(++ num)
			(if (obj isKindOf: InvItem)
				(self
					add:
						((= el (DText new:))
							value: obj
							text: (obj name?)
							nsLeft: lastX
							nsTop: lastY
							state: (| dActive dExit)
							font: 1207
							setSize:
							yourself:
						)
				)
			)
			;Keep track of widest item.
			(if (< widest (- (el nsRight?) (el nsLeft?)))
				(=  widest (- (el nsRight?) (el nsLeft?)))
			)
			;Bump lastY by height of character this item.
			(+= lastY (+ (- (el nsBottom?) (el nsTop?)) 1))
			;Wrap to next column.
			(if (> lastY 140)
				(= lastY MARGIN)
				(+= lastX (+ widest 10))
				(= widest 0)
			)
		)
		(= window SysWindow)
		(self setSize:)
		(= yesI (DButton new:))
		(yesI
			font: 1207
			text: {Outta here!}
			setSize:
			moveTo:
				(- nsRight (+ MARGIN (yesI nsRight?)))
				(- nsBottom 17)
		)
		(yesI
			move: (- (yesI nsLeft?) (yesI nsRight?)) 0
		)
		(self add: yesI setSize: center:)
		(return num)
	)
	
	(method (doit &tmp el)
		(self init:)
		(self open:)
		(= el yesI)
		(repeat
			(= el (super doit: el))
			;These returns signal end of dialog
			(if (or (not el) (== el -1) (== el yesI))
				(break)
			)
			(ego get: -1 (inventory indexOf: (el value?)))
		)
		(self
			eachElementDo: #dispose 1
			dispose:
		)
		(theGame setCursor: 0 TRUE)
	)
	
	(method (handleEvent event &tmp msg typ)
		(= msg (event message?))
		(switch (= typ (event type?))
			(keyDown
				(switch msg
					(UPARROW
						(= msg SHIFTTAB)
					)
					(DOWNARROW
						(= msg TAB)
					)
				)
			)
			(direction
				(switch msg
					(dirN
						(= msg SHIFTTAB)
						(= typ keyDown)
					)
					(dirS
						(= msg TAB)
						(= typ keyDown)
					)
				)
			)
		)
		(event type: typ message: msg)
		(super handleEvent: event)
	)
)

(instance dCastDialog of Dialog
	(properties
		name "dCastD"
	)
	
	(method (init &tmp lastX lastY widest num el node obj)
		(= widest (= lastX (= lastY MARGIN)))
		(= num 0)
		(for ((= node (cast first:))) node ((= node (cast next: node)))
			(= obj (NodeValue node))
			(++ num)
			(self
				add:
					((= el (DText new:))
						value: obj
						text: (obj name?)
						nsLeft: lastX
						nsTop: lastY
						state: 3
						font: smallFont
						setSize:
						yourself:
					)
			)
			(if (< widest (- (el nsRight?) (el nsLeft?)))
				(= widest (- (el nsRight?) (el nsLeft?)))
			)
			(if
				(>
					(= lastY
						(+ lastY (- (el nsBottom?) (el nsTop?)) 1)
					)
					100
				)
				(= lastY MARGIN)
				(= lastX (+ lastX widest 10))
				(= widest 0)
			)
		)
		(= window systemWindow)
		(self setSize:)
		(= yesI (DButton new:))
		(yesI
			text: {exit}
			setSize:
			moveTo: (- nsRight (+ MARGIN (yesI nsRight?))) nsBottom
		)
		(yesI
			move: (- (yesI nsLeft?) (yesI nsRight?)) 0
		)
		(self add: yesI setSize: center:)
		(return num)
	)
	
	(method (doit &tmp el temp1)
		(self init:)
		(self open: wTitled 15)
		(= el yesI)
		(repeat
			(= el (super doit: el))
			;These returns signal end of dialog
			(if (or (not el) (== el -1) (== el yesI))
				(break)
			)
			(= gTheNewDButtonValue (el value?))
		)
		(self dispose:)
	)
	
	(method (handleEvent event &tmp msg typ)
		(= msg (event message?))
		(switch (= typ (event type?))
			(keyDown
				(switch msg
					(UPARROW
						(= msg SHIFTTAB)
					)
					(DOWNARROW
						(= msg TAB)
					)
				)
			)
			(direction
				(switch msg
					(dirN
						(= msg SHIFTTAB)
						(= typ keyDown)
					)
					(dirS
						(= msg TAB)
						(= typ keyDown)
					)
				)
			)
		)
		(event type: typ message: msg)
		(super handleEvent: event)
	)
)

(instance showFeatureCode of Code
	(method (doit obj param2 &tmp t l r b temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11)
		(if param2
			(= t (obj brTop?))
			(= l (obj brLeft?))
			(= b (obj brBottom?))
			(= r (obj brRight?))
			(Graph GDrawLine t l t r 40)
			(Graph GDrawLine b l b r 40)
			(Graph GDrawLine t l b l 40)
			(Graph GDrawLine t r b r 40)
		else
			(= t (obj nsTop?))
			(= l (obj nsLeft?))
			(= b (obj nsBottom?))
			(= r (obj nsRight?))
			(Graph GDrawLine t l t r 0)
			(Graph GDrawLine b l b r 0)
			(Graph GDrawLine t l b l 0)
			(Graph GDrawLine t r b r 0)
		)
		(= temp4 (- (obj y?) 1))
		(= temp5 (- (obj x?) 1))
		(= temp7 (+ (obj y?) 1))
		(= temp6 (+ (obj x?) 1))
		(Graph GDrawLine temp4 temp5 temp4 temp6 44)
		(Graph
			GDrawLine
			(+ temp4 1)
			temp5
			(+ temp4 1)
			temp6
			44
		)
		(Graph GDrawLine temp7 temp5 temp7 temp6 44)
		(= temp8 (Min t temp4))
		(= temp9 (Min l temp5))
		(= temp11 (Max b temp7))
		(= temp10 (Max r temp6))
		(Graph
			GReAnimate
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
	
	(method (doit &tmp obj [str 40] evt [buf 10])
		(while (not (self handleEvent: (= evt (Event new:))))
			(evt localize:)
			(OnMeAndLowY init:)
			(features eachElementDo: #perform OnMeAndLowY evt)
			(cast eachElementDo: #perform OnMeAndLowY evt)
			(if (= obj (OnMeAndLowY theObj?))
				(cond 
					(
						(and
							(obj onMeCheck?)
							(!= (obj onMeCheck?) ftrDefault)
						)
						(Format @buf 25 0 (obj onMeCheck?))
					)
					((obj respondsTo: #view)
						(Format @buf 25 1
							(obj view?) (obj loop?) (obj cel?)
						)
					)
					(else
						(Format @buf 25 2 {})
					)
				)
				(DrawStatus
					(Format @str
						25 3
						(evt x?)
						(evt y?)
						(obj name?)
						((obj -super-?) name?)
						@buf
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
		(= theText 0)
		(GetInput @theText 50 {Enter text (after this, get help with '?')})
		(theIconBar curIcon: (theIconBar at: ICON_USEIT))
		(= oldCursor theCursor)
		(theGame setCursor: 69 FALSE)
		(self doit: TRUE)
	)
	
	(method (doit clear &tmp theX theY)
		(if (and argc clear)
			(= lastX (= lastY 0))
		)
		(= theX ((User curEvent?) x?))
		(= theY ((User curEvent?) y?))
		(if (or (!= theX lastX) (!= theY lastY))
			(if unders
				(Display 25 4
					p_restore unders
				)
			)
			(= unders
				(Display @theText
					p_at theX theY
					p_color color
					p_back (if color 0 else 6)
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
		(theGame setCursor: oldCursor TRUE)
		(UnLoad MEMORY unders)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp inc theX theY eType eMsg eMods [str 25])
		(= theX (event x?))
		(= theY (event y?))
		(= eType (event type?))
		(= eMsg (event message?))
		(= eMods (event modifiers?))
		(cond 
			((== eType keyDown)
				(switch eMsg
					(ESC
						(Display 25 4
							p_restore unders
						)
						(self dispose:)
					)
					(ENTER
						(self showCoord: event)
					)
					(`^f
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
						(theGame setCursor: theCursor FALSE theX theY)
						(self doit: TRUE)
					)
					(`^n
						(= str 0)
						(GetInput @str 50
							{Enter text (then get help with `?')}
						)
						(if str
							(StrCpy @theText @str)
							(= lastX (= lastY 0))
							(self doit: TRUE)
						)
					)
					(`^c
						(if (> (++ color) 15)
							(= color 0)
						)
						(self doit: TRUE)
					)
					(`?
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
					((& eMods shiftDown)
						(event
							type: keyDown
							message: `^f
						)
						(self handleEvent: event)
					)
					((& eMods ctrlDown)
						(event
							type: keyDown
							message: `^c
						)
						(self handleEvent: event)
					)
					(else
						(self showCoord: event)
						(event claimed: TRUE)
					)
				)
			)
			((& eType direction)
				(= inc (if (& eMods shiftDown) 1 else 10))
				(if (OneOf eMsg dirE dirNE dirSE)
					(+= theX inc)
				)
				(if (OneOf eMsg dirW dirNW dirSW)
					(-= theX inc)
				)
				(if (OneOf eMsg dirNW dirN dirNE)
					(-= theY inc)
				)
				(if (OneOf eMsg dirSW dirS dirSE)
					(+= theY inc)
				)
				(event claimed: TRUE)
				(theGame setCursor: theCursor FALSE theX theY)
				(self doit:)
			)
		)
	)
	
	(method (showCoord event)
		(Print
			addTextF: {Position: %d, %d} (event x?) (event y?)
			init:
		)
		(self dispose:)
	)
)
