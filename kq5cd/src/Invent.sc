;;; Sierra Script 1.0 - (do not remove this comment)
(script# INVENT)
(include game.sh)
(use Main)
(use Intrface)
(use IconBar)
(use Window)
(use System)


(local
	numCols
)
(class InvItem of IconItem
	(properties
		name "InvI"
		view 0
		loop 0
		cel 0
		nsTop 0
		cursor ARROW_CURSOR
		message verbUse
		signal 0
		description 0
		owner 0
		script 0
		value 0
	)
	
	(method (show &tmp iconNo iX tmpX celWide)
		(DrawCel view loop cel nsLeft nsTop -1)
	)
	
	(method (highlight tOrF &tmp t l b r sColor)
		(= sColor
			(if (and argc tOrF) highlightColor else lowlightColor)
		)
		(= t (- nsTop 2))
		(= l (- nsLeft 2))
		(= b (+ nsBottom 1))
		(= r (+ nsRight 1))
		(Graph GDrawLine t l t r sColor -1 -1)
		(Graph GDrawLine t r b r sColor -1 -1)
		(Graph GDrawLine b r b l sColor -1 -1)
		(Graph GDrawLine b l t l sColor -1 -1)
		(Graph
			GShowBits
			(- nsTop 2)
			(- nsLeft 2)
			(+ nsBottom 2)
			(+ nsRight 2)
			1
		)
	)
	
	(method (onMe theObjOrX)
		(return
			(if (super onMe: theObjOrX)
				(not (& signal DISABLED))
			else
				0
			)
		)
	)
	
	(method (ownedBy id)
		(return (== owner id))
	)
	
	(method (moveTo id)
		(= owner id)
		(if (and value (= id ego))
			(theGame changeScore: value)
			(= value 0)
		)
		(return self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook (Printf INVENT 0 description))
		)
	)
)

(class Inventory of IconBar
	(properties
		name "Inv"
		activateHeight 0
		normalHeading {Roger is carrying:}
		heading 0
		empty {nothing!}
		curScore {Score: %d out of %d}
		showScore 1
		iconBarInvItem 0
		okButton 0
		selectIcon 0
	)
	
	(procedure (DrawInvWindow whom selection &tmp
			numOwned tallestInv widestInv numIcons tallestIcon iconBarWidth
			cWide cHigh node obj invW invH iTop iLeft iBottom iRight numRows atX atY firstX i)
		(= numOwned
			(= tallestInv
				(= widestInv
					(= numIcons
						(= tallestIcon
							(= iconBarWidth 0)
						)
					)
				)
			)
		)
		(= node (inventory first:))
		(while node
			(cond 
				(
				((= obj (NodeValue node)) isKindOf: InvItem)
					(if (obj ownedBy: whom)
						(obj signal: (& (obj signal?) (~ DISABLED)))
						(++ numOwned)
						(if
							(>
								(= cWide
									(CelWide (obj view?) (obj loop?) (obj cel?))
								)
								widestInv
							)
							(= widestInv cWide)
						)
						(if
							(>
								(= cHigh
									(CelHigh (obj view?) (obj loop?) (obj cel?))
								)
								tallestInv
							)
							(= tallestInv cHigh)
						)
					else
						(obj signal: (| (obj signal?) DISABLED))
					)
				)
				((not (& (obj signal?) DISABLED))
					(++ numIcons)
					(= iconBarWidth
						(+
							iconBarWidth
							(CelWide (obj view?) (obj loop?) (obj cel?))
						)
					)
					(if
						(>
							(= cHigh
								(CelHigh (obj view?) (obj loop?) (obj cel?))
							)
							tallestIcon
						)
						(= tallestIcon cHigh)
					)
				)
			)
			(= node (inventory next: node))
		)
		(if (not numOwned)
			(Printf INVENT 1 normalHeading empty)
			(return)
		)
		(if (> (* (= numRows (Sqrt numOwned)) numRows) numOwned)
			(-- numRows)
		)
		(if (> numRows 3) (= numRows 3))
		(if (< (* numRows (= numCols (/ numOwned numRows))) numOwned)
			(++ numCols)
		)
		(= invW (Max (+ 4 iconBarWidth) (* numCols (+ 4 widestInv))))
		(= iTop
			(/
				(- 190 (= invH (+ (* numRows (+ 4 tallestInv)) tallestIcon)))
				2
			)
		)
		(= iLeft (/ (- 320 invW) 2))
		(= iBottom (+ iTop invH))
		(= iRight (+ iLeft invW))
		(if (inventory window?)
			((inventory window?)
				top: iTop
				left: iLeft
				right: iRight
				bottom: iBottom
				open:
			)
		)
		(= i numCols)
		(if numOwned
			(= atY
				(+
					2
					(if ((inventory window?) respondsTo: #yOffset)
						((inventory window?) yOffset?)
					else
						0
					)
				)
			)
			(= firstX
				(= atX
					(+
						4
						(if ((inventory window?) respondsTo: #xOffset)
							((inventory window?) xOffset?)
						else
							0
						)
					)
				)
			)
			(= node (inventory first:))
			(while node
				(if
					(and
						(not
							(&
								((= obj (NodeValue node)) signal?)
								$0004
							)
						)
						(obj isKindOf: InvItem)
					)
					(if (not (& (obj signal?) $0080))
						(obj
							nsLeft:
								(+
									atX
									(/
										(-
											widestInv
											(= cWide
												(CelWide (obj view?) (obj loop?) (obj cel?))
											)
										)
										2
									)
								)
							nsTop:
								(+
									atY
									(/
										(-
											tallestInv
											(= cHigh
												(CelHigh (obj view?) (obj loop?) (obj cel?))
											)
										)
										2
									)
								)
						)
						(obj
							nsRight: (+ (obj nsLeft?) cWide)
							nsBottom: (+ (obj nsTop?) cHigh)
						)
						(if (-- i)
							(= atX (+ atX widestInv))
						else
							(= i numCols)
							(= atY (+ atY tallestInv))
							(= atX firstX)
						)
					else
						(= atX (obj nsLeft?))
						(= atY (obj nsTop?))
					)
					(obj show:)
					(if (== obj selection)
						(theGame setCursor: invCursor TRUE)
						(obj highlight:)
					)
				)
				(= node (inventory next: node))
			)
		)
		(= atX
			(/
				(-
					(-
						((inventory window?) right?)
						((inventory window?) left?)
					)
					iconBarWidth
				)
				2
			)
		)
		(= invH
			(-
				((inventory window?) bottom?)
				((inventory window?) top?)
			)
		)
		(= atY 32767)
		(= node (inventory first:))
		(while node
			(if
				(and
					(not
						((= obj (NodeValue node)) isKindOf: InvItem)
					)
					(not (& (obj signal?) DISABLED))
				)
				(= cWide
					(CelWide (obj view?) (obj loop?) (obj cel?))
				)
				(= cHigh
					(CelHigh (obj view?) (obj loop?) (obj cel?))
				)
				(if (not (& (obj signal?) $0080))
					(if (== atY 32767) (= atY (- invH cHigh)))
					(obj
						nsLeft: atX
						nsTop: atY
						nsBottom: invH
						nsRight: (+ atX cWide)
					)
				)
				(= atX (+ (obj nsLeft?) cWide))
				(= atY (obj nsTop?))
				(obj signal: (& (obj signal?) $fffb) show:)
			)
			(= node (inventory next: node))
		)
	)
	
	
	(method (init)
		(= inventory self)
		(= heading normalHeading)
	)
	
	(method (doit &tmp thisIcon event eType [temp3 3] eO oldPort keyInvoked)
		(while ((= event (Event new:)) type?)
			(event dispose:)
		)
		(event dispose:)
		(= event 0)
		(while (and (= event (Event new:)) (& state IB_ACTIVE))
			(= keyInvoked FALSE)
			(event localize:)
			(if
				(and
					curIcon
					(not (event modifiers?))
					(!= curIcon selectIcon)
					(or
						(== (event type?) mouseDown)
						(and
							(== (event type?) keyDown)
							(== (event message?) ENTER)
							(= keyInvoked TRUE)
						)
						(and (== (event type?) joyDown) (= keyInvoked TRUE))
					)
					(or
						(!= curIcon helpIconItem)
						(& (helpIconItem signal?) TRANSLATOR)
					)
				)
				(event type: userEvent message: (curIcon message?))
			)
			(MapKeyToDir event)
			(cond 
				(
					(and
						(== (= eType (event type?)) mouseDown)
						(event modifiers?)
					)
					(self advanceCurIcon:)
					(event claimed: TRUE)
				)
				(
					(and
						(== eType nullEvt)
						(= thisIcon (self firstTrue: #onMe event))
						(!= thisIcon highlightedIcon)
					)
					(self highlight: thisIcon)
				)
				(
					(or
						(== eType mouseDown)
						(and (== eType keyDown) (== (event message?) ENTER))
						(== eType joyDown)
					)
					(if
						(and
							(IsObject highlightedIcon)
							(self select: highlightedIcon (== eType mouseDown))
						)
						(if (== highlightedIcon okButton) (break))
						(if (== highlightedIcon helpIconItem)
							(if (!= (highlightedIcon cursor?) -1)
								(theGame setCursor: (helpIconItem cursor?))
							)
							(if helpIconItem
								(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
							)

						else
							(= curIcon highlightedIcon)
							(theGame setCursor: (curIcon cursor?) TRUE)
						)
					)
				)
				((& eType direction)
					(switch (event message?)
						(dirE (self advance:))
						(dirW (self retreat:))
						(dirN (self retreat: numCols))
						(dirS (self advance: numCols))
						(dirStop
							(if (& eType DISABLED) (self advanceCurIcon:))
						)
					)
				)
				((== eType keyDown)
					(switch (event message?)
						(TAB (self advance:))
						(SHIFTTAB (self retreat:))
					)
				)
				(
					(and
						(== eType userEvent)
						(= thisIcon (self firstTrue: #onMe event))
					)
					(if (== (event message?) verbHelp)
						(if (and thisIcon (thisIcon helpStr?))
							(DoAudio Play (thisIcon helpStr?))
						)
						(helpIconItem signal: (& (helpIconItem signal?) $ffef))
						(theGame setCursor: normalCursor)
					else
						(if (== thisIcon okButton) (break))
						(cond 
							((not (thisIcon isKindOf: InvItem))
								(if (self select: thisIcon (not keyInvoked))
									(= curIcon thisIcon)
									(theGame setCursor: (curIcon cursor?) TRUE)
								)
							)
							(curIcon
								(if (systemWindow respondsTo: #eraseOnly)
									(= eO (systemWindow eraseOnly?))
									(systemWindow eraseOnly: TRUE)
								)
								(if (curIcon isKindOf: InvItem)
									(thisIcon
										doVerb: (curIcon message?) (self indexOf: curIcon)
									)
								else
									(thisIcon doVerb: (event message?))
								)
								(if (systemWindow respondsTo: #eraseOnly)
									(systemWindow eraseOnly: eO)
								)
							)
						)
					)
				)
			)
			(event dispose:)
		)
		(event dispose:)
		(self hide:)
	)
	
	(method (showSelf who)
		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(if (theIconBar height?)
			(theIconBar hide:)
		)
		(if (not window)
			(= window (SysWindow new:))
		)
		(if (window window?)
			(window dispose:) (= window 0)
		)
		(if (not okButton)
			(= okButton (NodeValue (self first:)))
		)
		(= curIcon NULL)
		(theGame setCursor: (selectIcon cursor?))
		(self show: (if argc who else ego) doit:)
	)
	
	(method (show who)
		(= state (| state IB_ACTIVE))
		(DrawInvWindow
			(if argc who else ego)
			(theIconBar curIcon?)
		)
	)
	
	(method (hide)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(= state (& state (~ IB_ACTIVE)))
		)
		(if window (window dispose:))
		(if
		(and (IsObject curIcon) (curIcon isKindOf: InvItem))
			(if (not (theIconBar curInvIcon?))
				(theIconBar enable: (theIconBar useIconItem?))
			)
			(theIconBar
				curIcon:
					((theIconBar useIconItem?)
						cursor: (curIcon cursor?)
						yourself:
					)
				curInvIcon: curIcon
			)
			(if (curIcon cursor?)
				(theGame setCursor: (curIcon cursor?) TRUE)
			)
		)
	)
	
	(method (advance amount &tmp theIcon toMove highlightedNo nextIcon)
		(= toMove (if argc amount else 1))
		(= nextIcon
			(+ toMove (= highlightedNo (self indexOf: highlightedIcon)))
		)
		(repeat
			(= theIcon
				(self
					at: (if (<= nextIcon size) nextIcon else (mod nextIcon (- size 1)))
				)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(if (not (& (theIcon signal?) DISABLED)) (break))
			(++ nextIcon)
		)
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
		(self highlight: theIcon)
	)
	
	(method (retreat amount &tmp theIcon toMove highlightedNo nextIcon)
		(= toMove (if argc amount else 1))
		(= nextIcon
			(- (= highlightedNo (self indexOf: highlightedIcon)) toMove)
		)
		(repeat
			(= theIcon (self at: nextIcon))
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(if (not (& (theIcon signal?) DISABLED)) (break))
			(-- nextIcon)
		)
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
		(self highlight: theIcon)
	)
	
	(method (advanceCurIcon &tmp theIcon)
		(= theIcon curIcon)
		(while
			((= theIcon
				(self at: (mod (+ (self indexOf: theIcon) 1) size))
			)
				isKindOf: InvItem
			)
		)
		(= curIcon theIcon)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
	
	(method (ownedBy id)
		(self firstTrue: #ownedBy id)
	)
)
