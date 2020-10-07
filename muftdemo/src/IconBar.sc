;;; Sierra Script 1.0 - (do not remove this comment)
(script# ICONBAR)
(include game.sh)
(use Main)
(use Intrface)
(use User)
(use System)

;NOTE: This script has been slightly modified for this game.

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
		(if
		(or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
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
		port 0
		window 0
		state OPENIFONME
		activateHeight 0
		y 0
		stopSound 0
	)
	
	(method (doit &tmp event eType)
		(while
		(and (= event (Event new:)) (& state IB_ACTIVE))
		(Wait 1)
			(if (== (event type?) joyDown)
				(event
					type: keyDown
					message: (if (& (event modifiers?) shiftDown) ESC else ENTER)
					modifiers: 0
				)
			)
			(event localize:)
			(if
				(and
					(or
						(== (event type?) mouseDown)
						(and
							(== (event type?) keyDown)
							(== (event message?) ENTER)
						)
					)
					(IsObject helpIconItem)
					(& (helpIconItem signal?) TRANSLATOR)
				)
				(event type: userEvent message: (helpIconItem message?))
			)
			(MapKeyToDir event)
			(if (self dispatchEvent: event) (break))
		)
		(if (IsObject event) (event dispose:))
	)
	
	(method (handleEvent event &tmp keyInvoked eType oldCursor oldCurIcon newCursor oldInvIcon newEvent eventY)
		(event globalize:)
		(= eventY (event y?))
		(SetPort 0)
		(= port 0)		
		(event localize:)
		(cond 
			((& state DISABLED))
			(
				(or
					(and
						(not (= eType (event type?)))
						(& state OPENIFONME)
						(<= -10 (event y?))
						(<= eventY height)
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
				(= newCursor
					(if
						(and
							(== oldCurIcon curIcon)
							(== oldInvIcon curInvIcon)
						)
						oldCursor
					else
						(curIcon cursor?)
					)
				)
				(if keyInvoked
					(SetPort -1)
					(theGame setCursor: newCursor TRUE oldMouseX oldMouseY)
					(SetPort 0)
				else
					(theGame
						setCursor:
							newCursor
							TRUE
							((= newEvent (Event new:)) x?)
							(Max (newEvent y?) (+ 1 height))
					)
					(newEvent dispose:)
				)
				(SetPort port)
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
						(self swapCurIcon: event)
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
					((& (event modifiers?) ctrlDown) (self swapCurIcon: event))
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
		(if stopSound (sounds pause:))
		(= state (| state IB_ACTIVE))
		(if (User canControl:) (theGame setCursor: 80 TRUE))	;EO: Change is specific to Mixed-Up Fairy Tales
		(= height
			(CelHigh
				((= theIcon (self at: 0)) view?)
				(theIcon loop?)
				(theIcon cel?)
			)
		)
		(if (not port) (= port (GetPort)))
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
			else
				(obj show:)
			)
			(= theX (obj nsRight?))			
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
	)
	
	(method (hide &tmp node nextNode obj)
		(if (& state IB_ACTIVE)
			(if stopSound (sounds pause: FALSE))
			(= state (& state (~ IB_ACTIVE)))
		)
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
			(++ i)
		)
		(if (& state IB_ACTIVE)
			(theGame
				setCursor:
					theCursor
					1
					(+
						(theIcon nsLeft?)
						(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
					)
					(- (theIcon nsBottom?) 3)
			)
		)
		(self highlight: theIcon)
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
			(++ i)
		)
		(if (& state IB_ACTIVE)
			(theGame
				setCursor:
					theCursor
					1
					(+
						(theIcon nsLeft?)
						(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
					)
					(- (theIcon nsBottom?) 3)
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
	
	(method (highlight theIcon &tmp sColor curPort)
		(if (not (& (theIcon signal?) DISABLED))
			(= curPort (GetPort))
			(if (== self IconBar) (SetPort -1))			
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight: FALSE)
			)
			((= highlightedIcon theIcon) highlight: TRUE)
			(SetPort curPort)			
		)
	)
	
	(method (swapCurIcon nextIcon &tmp firstIcon)
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
			(else (= curIcon (= prevIcon (self at: 1))))			
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
		(nextIcon
			type: (curIcon type?)
			message: (curIcon message?)
		)
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
	
	(method (dispatchEvent event &tmp evtX evtY evtType evtMsg thisIcon evtClaimed thePort)
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
									(and
										(<= 0 evtY)
										(<= evtY (+ y height))
										(<= 0 evtX)
									)
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
							(if (& state NOCLICKHELP)
								(self noClickHelp:)
							else
								(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
							)
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
							(Printf ICONBAR 0 (thisIcon helpStr?))
							(SetPort thePort)
						)
						(if helpIconItem
							(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
						)
						(theGame setCursor: 80)	;EO: Change is specific to Mixed-Up Fairy Tales
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
	
	(method (noClickHelp &tmp event lastIcon thisIcon [str 100] oldPort)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(while (not ((= event (Event new:)) type?))
			(if (not (self isMemberOf: IconBar))
				(event localize:)
			)
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon) (thisIcon helpStr?))
						(= lastIcon thisIcon)
						(Format @str (thisIcon helpStr?))
						(Print @str #dispose)
						(SetPort oldPort)
					)
				)
				(modelessDialog (modelessDialog dispose:) (SetPort oldPort))
				(else (= lastIcon 0))
			)
			(event dispose:)
		)
		(event dispose:)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(if modelessDialog (modelessDialog dispose:))
		(SetPort oldPort)
	)	
)
