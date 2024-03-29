;;; Sierra Script 1.0 - (do not remove this comment)
(script# ICONBAR)
(include game.sh)
(use Main)
(use Intrface)
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
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
	)
	
	(method (show iX iY &tmp iconNo uISize iconSpace leftEdge topEdge celWide pnv)
		(= signal (| signal IB_ACTIVE))
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
	
	(method (select relVer &tmp event whichCel)
		(return
			(cond 
				((& signal DISABLED) FALSE)
				((and argc relVer (& signal RELVERIFY))
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					)
					whichCel
				)
				(else TRUE)
			)
		)
	)
	
	(method (highlight tOrF &tmp t l b r sColor)
		(if (not (& signal IB_ACTIVE))
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
				FALSE
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

(instance ibEvent of Event)

(class IconBar of Set
	(properties
		height 10
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		port 0
		window 0
		state OPENIFONME
		activateHeight 10
		y 0
	)
	
	(method (doit)
		(while (& state IB_ACTIVE)
			(ibEvent
				type: 0
				message: 0
				modifiers: 0
				y: 0
				x: 0
				claimed: 0
				port: 0
			)
			(GetEvent allEvents ibEvent)
			(if (== (ibEvent type?) joyDown)
				(ibEvent
					type: keyDown
					message: (if (& (ibEvent modifiers?) shiftDown) ESC else ENTER)
					modifiers: 0
				)
			)
			(ibEvent localize:)
			(if
				(and
					(or
						(== (ibEvent type?) mouseDown)
						(and
							(== (ibEvent type?) keyDown)
							(== (ibEvent message?) ENTER)
						)
					)
					(IsObject helpIconItem)
					(& (helpIconItem signal?) TRANSLATOR)
				)
				(ibEvent type: userEvent message: (helpIconItem message?))
			)
			(MapKeyToDir ibEvent)
			(if (self dispatchEvent: ibEvent) (break))
		)
		(if (IsObject ibEvent)
			(ibEvent dispose:)
		)
	)

	
	(method (handleEvent event &tmp keyInvoked eType oldPort eX eY)
		(= oldPort (GetPort))
		(SetPort -1)
		(event localize:)
		(= eX (event x?))
		(= eY (event y?))
		(SetPort oldPort)
		(event localize:)
		(cond 
			((& state DISABLED))
			(
				(or
					(and
						(not (= eType (event type?)))
						(& state OPENIFONME)
						(<= 0 eY)
						(<= eY height)
						(<= 0 eX)
						(<= eX SCRNWIDE)
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
				(= oldMouseX (event x?))
				(= oldMouseY (event y?))
				(self show:)
				(if keyInvoked
					(if global400
						(theGame
							setCursor: theCursor TRUE
								(+
									(curIcon nsLeft?)
									(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
								)
								(- (curIcon nsBottom?) 13)
						)
					else
						(theGame setCursor: theCursor TRUE)
						(Intersections
							(+
								(curIcon nsLeft?)
								(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
							)
						)
					)
				)
				(self doit:)
				(if keyInvoked
					(if global400
						(theGame
							setCursor: (curIcon cursor?) TRUE oldMouseX oldMouseY
						)
					else
						(theGame setCursor: (curIcon cursor?) TRUE)
						(Intersections oldMouseX oldMouseY)
					)
				else
					(theGame setCursor: (curIcon cursor?) TRUE)
				)
				(self hide:)
			)
			((& eType keyDown)
				(switch (event message?)
					(ENTER
						(if (IsObject curIcon)
							(event
								type: (curIcon type?)
								message: (curIcon message?)
							)
						)
					)
					(INSERT
						(self swapCurIcon:)
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
					((& (event modifiers?) ctrlDown) (self swapCurIcon:) (event claimed: TRUE))
					((IsObject curIcon)
						(event
							type: (curIcon type?)
							message: (curIcon message?)
						)
					)
				)
			)
		)
	)
	
	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(sounds pause:)
		(= state (| state IB_ACTIVE))
		(theGame setCursor: normalCursor TRUE)
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
			(if (ego has: (inventory indexOf: curInvIcon))
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
			else
				(= curInvIcon 0)
			)
		)
		(PicNotValid pnv)
		(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
		(self highlight: curIcon)
		(theGame
			setCursor:
				theCursor
				(+
					(curIcon nsLeft?)
					(/ (- (curIcon nsLeft?) (curIcon nsRight?)) 2)
				)
		)
	)
	
	(method (hide &tmp node nextNode obj)
		(if (& state IB_ACTIVE)
			(if
				(if (IsObject helpIconItem)
					(not (& (helpIconItem signal?) TRANSLATOR))
				else
					1
				)
				(sounds pause: FALSE)
				(&= state (~ IB_ACTIVE))
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
		(if (& state IB_ACTIVE)
			(if global400
				(theGame
					setCursor: theCursor TRUE
						(+
							(theIcon nsLeft?)
							(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
						)
						(- (theIcon nsBottom?) 3)
				)
			else
				(theGame setCursor: theCursor TRUE)
				(Intersections
					(+
						(theIcon nsLeft?)
						(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
					)
					(- (theIcon nsBottom?) 3)
				)
			)
		)
		(self highlight: theIcon)
	)
	
	(method (retreat &tmp theIcon i)
		(= i 1)
		(while (<= i size)
			(= theIcon
				(self at: (- (self indexOf: highlightedIcon) i))
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
			(= i (mod (+ i 1) size))
		)
		(if (& state IB_ACTIVE)
			(if global400
				(theGame
					setCursor: theCursor TRUE
						(+
							(theIcon nsLeft?)
							(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
						)
						(- (theIcon nsBottom?) 3)
				)
			else
				(theGame
					setCursor: theCursor TRUE
						(+
							(theIcon nsLeft?)
							(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
						)
						(- (theIcon nsBottom?) 3)
				)
				(Intersections
					(+
						(theIcon nsLeft?)
						(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
					)
					(- (theIcon nsBottom?) 3)
				)
			)
		)
		(self highlight: theIcon)
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
	
	(method (highlight theIcon &tmp sColor)
		(if (not (& (theIcon signal?) DISABLED))
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight: FALSE)
			)
			((= highlightedIcon theIcon) highlight: TRUE)
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
	
	(method (advanceCurIcon &tmp theIcon i)
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
				$0006
			)
			(if (> i (+ 1 size)) (return) else (++ i))
		)
		(= curIcon theIcon)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
	
	(method (dispatchEvent event &tmp evtY evtX evtType evtMsg thisIcon evtClaimed thePort)
		(= evtX (event x?))
		(= evtY (event y?))
		(= evtType (event type?))
		(= evtMsg (event message?))
		(= evtClaimed (event claimed?))
		(= thisIcon (self firstTrue: #onMe event))
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
								(and (<= 0 evtY) (<= evtY height) (<= 0 evtX))
									(<= evtX SCRNWIDE)
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
						(if (== thisIcon helpIconItem)
							(if (thisIcon cursor?)
								(theGame setCursor: (thisIcon cursor?))
							)
							(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
						else
							(= evtClaimed (& (thisIcon signal?) HIDEBAR))
						)
					)
				)
				(keyDown
					(switch evtMsg
						(ESC (= evtClaimed TRUE))
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
									(if (!= (thisIcon cursor?) -1)
										(theGame setCursor: (thisIcon cursor?))
									)
									(if helpIconItem
										(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
									)
								)
								((IsObject thisIcon)
									(self select: thisIcon)
									(= evtClaimed (& (thisIcon signal?) HIDEBAR))
								)
							)
						)
						(SHIFTTAB (self retreat:))
						(TAB (self advance:))
					)
				)
				(userEvent
					(if (== evtMsg verbHelp)
						(if
						(and thisIcon (thisIcon helpStr?))
							(= thePort (GetPort))
							(DoAudio Play (thisIcon helpStr?))
							;(Printf ICONBAR 0 (thisIcon helpStr?))
							(SetPort thePort)
						)
						(if helpIconItem
							(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
						)
						(theGame setCursor: normalCursor)
					)
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
					((== thisIcon highlightedIcon) (self advance:))
					((== thisIcon curIcon) (self advanceCurIcon:))					
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
)
