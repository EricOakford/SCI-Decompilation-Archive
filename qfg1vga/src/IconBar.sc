;;; Sierra Script 1.0 - (do not remove this comment)
(script# ICONBAR)
(include game.sh)
(use Main)
(use Print)
(use System)


(class IconItem of Object
	(properties
		name "IconI"
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state NULL
		cursor -1
		type userEvent
		message -1
		modifiers $0000
		signal RELVERIFY
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum 0
		helpVerb 0
	)
	
	(method (show iX iY &tmp iconNo uISize iconSpace leftEdge topEdge celWide pnv)
		(= signal (| signal $0020))
		(if argc
			(= nsRight
				(+ (= nsLeft iX) (CelWide view loop cel))
			)
			(= nsBottom
				(+ (= nsTop iY) (CelHigh view loop cel))
			)
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if (& signal DISABLED) (self mask:))
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select relVar &tmp event whichCel)
		(return
			(cond 
				((& signal DISABLED) 0)
				((and argc relVar (& signal RELVERIFY))
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					)
					whichCel
				)
				(else 1)
			)
		)
	)
	
	(method (highlight tOrF &tmp t l b r sColor)
		(if
		(or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(= sColor
			(if (and argc tOrF) highlightColor else lowlightColor)
		)
		(= t (+ nsTop 2))
		(= l (+ nsLeft 2))
		(= b (- nsBottom 3))
		(= r (- nsRight 4))
		(Graph GDrawLine t l t r sColor -1 -1)
		(Graph GDrawLine t r b r sColor -1 -1)
		(Graph GDrawLine b r b l sColor -1 -1)
		(Graph GDrawLine b l t l sColor -1 -1)
		(Graph GShowBits (- nsTop 2) (- nsLeft 2) nsBottom (+ nsRight 3) VMAP)
	)
	
	(method (onMe obj)
		(return
			(if
				(and
					(>= (obj x?) nsLeft)
					(>= (obj y?) nsTop)
					(<= (obj x?) nsRight)
				)
				(<= (obj y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (mask)
		(DrawCel maskView maskLoop maskCel
			(+
				nsLeft
				(/
					(-
						(CelWide view loop cel)
						(CelWide maskView maskLoop maskCel)
					)
					2
				)
			)
			(+
				nsTop
				(/
					(-
						(CelHigh view loop cel)
						(CelHigh maskView maskLoop maskCel)
					)
					2
				)
			)
			-1
		)
	)
)

(class IconBar of Set
	(properties
		height 0
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
		port 0
		window 0
		state OPENIFONME
		activateHeight 0
		y 0
	)
	
	(method (doit &tmp event eType eMsg eMod)
		(while
		(and (& state IB_ACTIVE) (= event (Event new:)))
			(= eType (event type?))
			(= eMsg (event message?))
			(= eMod (event modifiers?))
			(Wait 1)
			(if (== eType joyDown)
				(= eType keyDown)
				(= eMsg
					(if (& eMod shiftDown) ESC else ENTER)
				)
				(= eMod 0)
				(event
					type: eType
					message: eMsg
					modifiers: eMod
				)
			)
			(event localize:)
			(if
				(and
					(or
						(== eType mouseDown)
						(and (== eType keyDown) (== eMsg ENTER))
					)
					(IsObject helpIconItem)
					(& (helpIconItem signal?) TRANSLATOR)
				)
				(event
					type: (| userEvent helpEvent)
					message: (helpIconItem message?)
				)
			)
			(MapKeyToDir event)
			(breakif (self dispatchEvent: event))
		)
	)
	
	(method (handleEvent event &tmp keyInvoked eType oldCursor oldCurIcon oldInvIcon newEvent)
		(event localize:)
		(= eType (event type?))
		(cond 
			((& state DISABLED))
			(
				(or
					(and
						(not eType)
						(& state OPENIFONME)
						(<= -10 (event y?))
						(<= (event y?) height)
						(<= 0 (event x?))
						(<= (event x?) SCRNWIDE)
						(not (= keyInvoked FALSE))
					)
					(and
						(== eType keyDown)
						(or
							(== (event message?) ESC)
							(== (event message?) DELETE)
						)
						(= keyInvoked TRUE)
					)
				)
				(event globalize:)
				(= oldMouseX (event x?))
				(= oldMouseY (event y?))
				(= oldCursor theCursor)
				(= oldCurIcon curIcon)
				(= oldInvIcon curInvIcon)
				(self show:)
				(theGame setCursor: normalCursor)
				(if keyInvoked
					(theGame
						setCursor:
							theCursor
							TRUE
							(+
								(curIcon nsLeft?)
								(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
							)
							(- (curIcon nsBottom?) 3)
					)
				)
				(self doit:)
				(if keyInvoked
					(theGame
						setCursor: (curIcon cursor?) TRUE oldMouseX oldMouseY
					)
				else
					(theGame
						setCursor:
							(curIcon cursor?)
							TRUE
							((= newEvent (Event new:)) x?)
							(Max (newEvent y?) (+ 1 height))
					)
					(newEvent dispose:)
					
				)
				(self hide:)
			)
			((& eType keyDown)
				(switch (event message?)
					(ENTER
						(if (IsObject curIcon)
							(event
								type: (curIcon type?)
								message:
									(if (== curIcon useIconItem)
										(curInvIcon message?)
									else
										(curIcon message?)
									)
							)
						)
					)
					(INSERT
						(if (user canControl:) (self swapCurIcon:))
						(event claimed: TRUE)
					)
					(dirStop
						(if (& (event type?) direction)
							(self advanceCurIcon:)
							(event claimed: TRUE)
						)
					)
				)
			)
			((& eType mouseDown)
				(cond 
					((& (event modifiers?) shiftDown) (self advanceCurIcon:) (event claimed: TRUE))
					((& (event modifiers?) ctrlDown)
						(if (user canControl:) (self swapCurIcon:))
						(event claimed: TRUE)
					)
					((IsObject curIcon)
						(event
							type: (curIcon type?)
							message:
								(if (== curIcon useIconItem)
									(curInvIcon message?)
								else
									(curIcon message?)
								)
						)
					)
				)
			)
		)
	)
	
	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(sounds pause:)
		(= state (| state IB_ACTIVE))
		(theGame setCursor: ARROW_CURSOR TRUE)
		(= height
			(CelHigh
				((= theIcon (self at: 0)) view?)
				(theIcon loop?)
				(theIcon cel?)
			)
		)
		(= port (GetPort))
		(SetPort -1)
		(= underBits (Graph GSaveBits y 0 (+ y height) SCRNWIDE VMAP))
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(= theX 0)
		(= theY y)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(if (<= (obj nsRight?) 0)
				(obj show: theX theY)
				(= theX (obj nsRight?))
			else
				(obj show:)
			)
			(= node nextNode)
		)
		(if curInvIcon
			;(if (ego has: (inventory indexOf: curInvIcon))
				(= theX
					(+
						(/
							(-
								(- (useIconItem nsRight?) (useIconItem nsLeft?))
								(CelWide
									(curInvIcon view?)
									(+ (curInvIcon loop?) 1)
									(curInvIcon cel?)
								)
							)
							2
						)
						(useIconItem nsLeft?)
					)
				)
				(= theY
					(+
						y
						(/
							(-
								(- (useIconItem nsBottom?) (useIconItem nsTop?))
								(CelHigh
									(curInvIcon view?)
									(+ (curInvIcon loop?) 1)
									(curInvIcon cel?)
								)
							)
							2
						)
						(useIconItem nsTop?)
					)
				)
				(DrawCel
					(curInvIcon view?)
					(+ (curInvIcon loop?) 1)
					(curInvIcon cel?)
					theX
					theY
					-1
				)
				(if (& (useIconItem signal?) DISABLED)
					(useIconItem mask:)
				)
			;EO: Unfortunately, we can't make this NULL
			; for this particular game, as it causes problems
			; with the money pouch if ego has no silvers.
			;)
			;else
			;	(= curInvIcon 0)
			;)
		)
		(PicNotValid pnv)
		(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
		(self highlight: curIcon)
	)
	
	(method (hide &tmp node nextNode obj)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(= state (& state (~ IB_ACTIVE)))
			(= node (FirstNode elements))
			(while node
				(= nextNode (NextNode node))
				(if (not (IsObject (= obj (NodeValue node))))
					(return)
				)
				((= obj (NodeValue node))
					signal: (& (obj signal?) (~ IB_ACTIVE))
				)
				(= node nextNode)
			)
			(Graph GRestoreBits underBits)
			(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
			(Graph GReAnimate y 0 (+ y height) SCRNWIDE)
			(SetPort port)
			(= height activateHeight)
		)
	)
	
	(method (advance &tmp theIcon i)
		(= i 1)
		(while (<= i size)
			(= theIcon
				(self
					at: (mod (+ i (self indexOf: highlightedIcon)) size)
				)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
			(= i (mod (+ i 1) size))
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)
	
	(method (retreat &tmp theIcon i)
		(= i 1)
		(while (<= i size)
			(= theIcon
				(self
					at: (mod (- (self indexOf: highlightedIcon) i) size)
				)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
			(= i (mod (+ i 1) size))
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)
	
	(method (select theIcon relVer)
		(return
			(if (theIcon select: (if (>= argc 2) relVer))
				(if (not (& (theIcon signal?) IMMEDIATE))
					(= curIcon theIcon)
				)
				TRUE
			else
				FALSE
			)
		)
	)
	
	(method (highlight theIcon posnCursor &tmp sColor)
		(if (not (& (theIcon signal?) DISABLED))
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight: FALSE)
			)
			((= highlightedIcon theIcon) highlight: TRUE)
		)
		(if (and (>= argc 2) posnCursor)
			(theGame
				setCursor:
					theCursor
					TRUE
					(+
						(theIcon nsLeft?)
						(/
							(-
								(theIcon nsRight?)
								(theIcon nsLeft?)
							)
							2
						)
					)
					(- (theIcon nsBottom?) 3)
			)
		)
	)
	
	(method (swapCurIcon &tmp firstIcon)
		(cond 
			((& state DISABLED) (return))
			(
				(and
					(!= curIcon (= firstIcon (NodeValue (self first:))))
					(not (& (firstIcon signal?) DISABLED))
				)
				(= prevIcon curIcon)
				(= curIcon (NodeValue (self first:)))
			)
			(
			(and prevIcon (not (& (prevIcon signal?) DISABLED))) (= curIcon prevIcon))
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
	
	(method (advanceCurIcon &tmp theIcon i newIcon)
		(if (& state DISABLED) (return))
		(= theIcon curIcon)
		(= i 0)
		(while
			(&
				((= theIcon
					(self at: (mod (+ (self indexOf: theIcon) 1) size))
				)
					signal?
				)
				(| DISABLED IMMEDIATE)
			)
			(if (> i (+ 1 size)) (return) else (++ i))
		)
		(= curIcon theIcon)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
	
	(method (dispatchEvent event
			&tmp evtY evtX evtType evtMsg thisIcon evtClaimed
			thePort [buffer 50] theCurs theSignal isHelp)
		(= evtX (event x?))
		(= evtY (event y?))
		(= evtType (event type?))
		(= evtMsg (event message?))
		(= evtClaimed (event claimed?))
		(if
		(= thisIcon (self firstTrue: #onMe event))
			(= theCurs (thisIcon cursor?))
			(= theSignal (thisIcon signal?))
			(= isHelp (== thisIcon helpIconItem))
		)
		(event dispose:)
		(if (& evtType direction)
			(switch evtMsg
				(dirE (self advance:))
				(dirW (self retreat:))
			)
		else
			(switch evtType
				(nullEvt
					(cond 
						(
							(not
								(if
									(and
										(<= 0 evtY)
										(<= evtY (+ y height))
										(<= 0 evtX)
									)
									(<= evtX 320)
								)
							)
							(if
								(and
									(& state OPENIFONME)
									(or
										(not (IsObject helpIconItem))
										(not (& (helpIconItem signal?) TRANSLATOR))
									)
								)
								(= oldMouseY 0)
								(= evtClaimed TRUE)
							)
						)
						(
							(and
								thisIcon
								(!= thisIcon highlightedIcon)
							)
							(= oldMouseY 0)
							(self highlight: thisIcon)
						)
					)
				)
				(mouseDown
					(if
						(and
							thisIcon
							(self select: thisIcon TRUE)
						)
						(if isHelp
							(if theCurs
								(theGame setCursor: theCurs)
							)
							(if (& state NOCLICKHELP)
								(self noClickHelp:)
							else
								(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
							)
						else
							(= evtClaimed (& theSignal HIDEBAR))
						)
						(thisIcon doit:)
					)
				)
				(keyDown
					(switch evtMsg
						(ESC (= evtClaimed TRUE))
						(DELETE (= evtClaimed TRUE))
						(ENTER
							(if (not thisIcon)
								(= thisIcon highlightedIcon)
							)
							(cond 
								(
									(and
										thisIcon
										(== thisIcon helpIconItem)
									)
									(if (!= theCurs -1)
										(theGame setCursor: theCurs)
									)
									(if helpIconItem
										(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
									)
								)
								((IsObject thisIcon)
									(self select: thisIcon)
									(= evtClaimed (& theSignal HIDEBAR))
								)
							)
						)
						(SHIFTTAB (self retreat:))
						(TAB (self advance:))
					)
				)
				((| userEvent helpEvent)
					(if
					(and thisIcon (thisIcon noun?))
						(= thePort (GetPort))
						(if
							(Message
								MsgGet
								(thisIcon modNum?)
								(thisIcon noun?)
								(thisIcon helpVerb?)
								0
								1
								@buffer
							)
							(Prints @buffer)
						)
						(SetPort thePort)
					)
					(if helpIconItem
						(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
					)
					(theGame setCursor: ARROW_CURSOR)
				)
			)
		)
		(return evtClaimed)
	)
	
	(method (disable theIconNumber &tmp i thisIcon)
		(if argc
			(= i 0)
			(while (< i argc)
				(= thisIcon
					(if (IsObject [theIconNumber i])
						[theIconNumber i]
					else
						(self at: [theIconNumber i])
					)
				)
				(thisIcon signal: (| (thisIcon signal?) DISABLED))
				(cond 
					((== thisIcon curIcon) (self advanceCurIcon:))
					((== thisIcon highlightedIcon) (self advance:))
				)
				(++ i)
			)
		else
			(= state (| state DISABLED))
		)
	)
	
	(method (enable theIconNumber &tmp i thisIcon)
		(if argc
			(= i 0)
			(while (< i argc)
				(= thisIcon
					(if (IsObject [theIconNumber i])
						[theIconNumber i]
					else
						(self at: [theIconNumber i])
					)
				)
				(thisIcon signal: (& (thisIcon signal?) (~ DISABLED)))
				(++ i)
			)
		else
			(= state (& state (~ DISABLED)))
		)
	)
	
	(method (noClickHelp &tmp event lastIcon thisIcon oldPort eO)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(= eO (systemWindow eraseOnly?))
		(systemWindow eraseOnly: TRUE)
		(while (not ((= event (Event new:)) type?))
			(if (not (self isMemberOf: IconBar))
				(event localize:)
			)
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon) (thisIcon helpVerb?))
						(= lastIcon thisIcon)
						(if modelessDialog (modelessDialog dispose:))
						(Print
							font: userFont
							width: 250
							addText: (thisIcon noun?) (thisIcon helpVerb?) 0 1 0 0 (thisIcon modNum?)
							modeless: TRUE
							init:
						)
						(SetPort oldPort)
					)
				)
				(modelessDialog
					(modelessDialog dispose:)
				)
				(else
					(= lastIcon 0)
				)
			)
			(event dispose:)
		)
		(systemWindow eraseOnly: eO)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(if modelessDialog (modelessDialog dispose:))
		(SetPort oldPort)
		(if (not (helpIconItem onMe: event))
			(self dispatchEvent: event)
		else
			(event dispose:)
		)
	)
	
	(method (findIcon param1 &tmp temp0 temp1)
		(= temp0 0)
		(while (< temp0 argc)
			(if
			(== ((= temp1 (self at: temp0)) message?) param1)
				(return temp1)
			)
			(++ temp0)
		)
		(return 0)
	)
)
