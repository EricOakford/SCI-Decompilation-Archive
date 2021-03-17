;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use IconBar)
(use Tutorial)
(use System)

(public
	GK2Iconbar 0
	GK2IconItem 1
)

(class GK2IconItem of IconItem
	
	(method (select relVer &tmp event whichCel tut)
		(return
			(cond 
				((& signal DISABLED)
					FALSE
				)
				((and argc relVer (& signal RELVERIFY))
					(= cel (= whichCel 1))
					(UpdateScreenItem self)
					(FrameOut)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize: plane)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(= cel (= whichCel 1))
									(UpdateScreenItem self)
									(FrameOut)
								)
							)
							(whichCel
								(= cel (= whichCel 0))
								(inventory highlightedIcon: 0)
								(UpdateScreenItem self)
								(FrameOut)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if
						(and
							(= tut (theGame script?))
							(tut isKindOf: Tutorial)
						)
						(cond 
							(
								(and
									(== (tut nextItem?) self)
									(!=
										(tut nextAction?)
										((theIconBar helpIconItem?) message?)
									)
								)
								(tut cue:)
							)
							((not whichCel)
								(return FALSE)
							)
							(else
								(tut report:)
								(return FALSE)
							)
						)
					)
					whichCel
				)
				(else
					(if
						(and
							(= tut (theGame script?))
							(tut isKindOf: Tutorial)
						)
						(if
							(and
								(== (tut nextItem?) self)
								(!=
									(tut nextAction?)
									((theIconBar helpIconItem?) message?)
								)
							)
							(tut cue:)
						else
							(tut report:)
							(return FALSE)
						)
					)
					TRUE
				)
			)
		)
	)
	
	(method (highlight tOrF)
		(if (not (& signal IB_ACTIVE))
			(return)
		)
		(= cel (* 1 (if argc tOrF else 0)))
		(UpdateScreenItem self)
	)
)

(class GK2Iconbar of IconBar
	(properties
		iconCast 0
	)
	
	(method (init)
		((= theIconBar self)
			add: useIcon7
			curIcon: useIcon7
			useIconItem: useIcon7
			state: (| (GK2Iconbar state?) (| NOCLICKHELP RELVERIFY))
		)
		(useIcon7 signal: (| (useIcon7 signal?) DISABLED))
		(super init:)
		(= iconCast (Cast new:))
		(plane
			addCast: iconCast
			setBitmap: 110 0 0
			priority: 10
			setRect: 0 143 320 200
		)
	)
	
	(method (show &tmp theX theY node obj)
		(|= state IB_ACTIVE)
		(plane priority: 10)
		(UpdatePlane plane)
		(= theX 0)
		(= theY y)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if
			(<= ((= obj (NodeValue node)) nsRight?) 0)
				(obj show: theX theY)
				(= theX (obj nsRight?))
			else
				(obj show:)
			)
			(= node nextNode)
		)
		(self highlight: curIcon)
	)
	
	(method (handleEvent event &tmp keyInvoked eType newEvent newCursur oldCursor oldCurIcon oldInvIcon)
		(= eType (event type?))
		(cond 
			((& state DISABLED))
			(
				(and
					(not eType)
					(& state OPENIFONME)
					(self shouldOpen: event)
					(not (= keyInvoked FALSE))
				)
				(= oldCursor theCursor)
				(= oldCurIcon curIcon)
				(= oldInvIcon curInvIcon)
				(self show:)
				(self doit:)
			)
			((& eType mouseDown)
				(cond 
					((& (event modifiers?) shiftDown)
						(event claimed: TRUE)
					)
					((& (event modifiers?) ctrlDown)
						(event claimed: TRUE)
					)
					(curIcon
						(event
							type: (curIcon type?)
							message: (curIcon message?)
						)
					)
				)
			)
		)
	)
	
	(method (hide theIcon &tmp node obj)
		(if (& state IB_ACTIVE)
			(if (and argc theIcon)
				(sounds pause: FALSE)
				(&= state (~ IB_ACTIVE))
			)
			(self highlight: curIcon)
			(= node (FirstNode elements))
			(while node
				(= nextNode (NextNode node))
				(= obj (NodeValue node))
				(obj signal: (& (obj signal?) (~ IB_ACTIVE)))
				(= node nextNode)
			)
			(if
				(and
					(not (& state NOCLICKHELP))
					helpIconItem
					(& (helpIconItem signal?) TRANSLATOR)
				)
				(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
			)
			(if (and argc theIcon)
				(plane priority: -1)
			)
			(UpdatePlane plane)
		)
	)
	
	(method (advance &tmp theIcon i)
		(for ((= i 1)) (<= i size) ((= i (mod (+ i 1) size)))
			(breakif
				(not
					(&
						((= theIcon
							(self
								at: (mod (+ i (self indexOf: highlightedIcon)) size)
							)
						)
							signal?
						)
						DISABLED
					)
				)
			)
		)
		(self highlight: theIcon)
	)
	
	(method (retreat &tmp theIcon i)
		(for ((= i 1)) (<= i size) ((= i (mod (+ i 1) size)))
			(breakif
				(not
					(&
						((= theIcon
							(self
								at: (mod (- (self indexOf: highlightedIcon) i) size)
							)
						)
							signal?
						)
						DISABLED
					)
				)
			)
		)
		(self highlight: theIcon)
	)
	
	(method (highlight theIcon &tmp sColor)
		(if (not (& (theIcon signal?) DISABLED))
			(if highlightedIcon
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
			((and prevIcon (not (& (prevIcon signal?) DISABLED)))
				(= curIcon prevIcon)
			)
		)
		(theGame setCursor: (self getCursor:))
	)
	
	(method (disableIcon theIcon &tmp i thisIcon)
		(if argc
			(for ((= i 0)) (< i argc) ((++ i))
				(= thisIcon [theIcon i])
				(thisIcon signal: (| (thisIcon signal?) DISABLED))
				(cond 
					((== thisIcon curIcon)
						(self advanceCurIcon:)
					)
					((== thisIcon highlightedIcon)
						(self advance:)
					)
				)	
			)
			(self show:)
		else
			(|= state DISABLED)
		)
	)
	
	(method (enableIcon theIcon &tmp i thisIcon)
		(if argc
			(for ((= i 0)) (< i argc) ((++ i))
				(= thisIcon [theIcon i])
				(thisIcon signal: (& (thisIcon signal?) (~ DISABLED)))
			)
		else
			(&= state (~ DISABLED))
		)
		(self show:)
	)
	
	(method (shouldOpen theIcon)
		(if (and iconsOpen (== (theIcon type?) nullEvt))
			(plane onMe: theIcon)
		)
	)
	
	(method (shouldClose theIcon)
		(theIcon globalize:)
		(return
			(cond 
				((not iconsOpen))
				((== (theIcon type?) nullEvt)
					(not (plane onMe: theIcon))
				)
			)
		)
	)
)

(instance useIcon7 of GK2IconItem
	(properties
		noun 1
		x 268
		y 16
		message NULL
		mainView 100
		mainLoop 3
		maskView 100
		maskLoop 3
		helpVerb 2
	)
	
	(method (init)
		(self setCursor: normalCursor)
		(super init: &rest)
	)
)
