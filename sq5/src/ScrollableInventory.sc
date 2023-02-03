;;; Sierra Script 1.0 - (do not remove this comment)
(script# SCROLLINV)
(include game.sh)
(use Main)
(use Print)
(use Invent)
(use System)


(class ScrollableInventory of Inventory
	(properties
		normalHeading -1
		empty -1
		curPos 0
		dispAmount 12
		items 0
		numCols 6
		numRows 2
		scrollAmount 6
		firstThru 1
		upIcon 0
		downIcon 0
	)

	
	(method (dispose)
		(if (IsObject items)
			(items dispose:)
			(= items 0)
		)
		(super dispose: &rest)
	)
	
	(method (hide)
		(if (IsObject items)
			(items dispose:)
			(= items 0)
		)
		(= firstThru 1)
		(super hide: &rest)
	)
	
	(method (advance amount &tmp theIcon toMove highlightedNo nextIcon)

		(= toMove (if argc amount else 1))
		(= nextIcon
			(mod
				(+ toMove (= highlightedNo (self indexOf: highlightedIcon)))
				size
			)
		)
		(repeat
			(= theIcon (self at: nextIcon))
			(if
				(and
					(IsObject theIcon)
					(not (& (theIcon signal?) DISABLED))
					(or
						(> (theIcon nsLeft?) -1)
						(not (theIcon isKindOf: InvItem))
					)
				)
				(break)
			)
			(= nextIcon (mod (+ nextIcon 1) size))
		)
		(self highlight: theIcon 1)
	)
	
	(method (retreat amount &tmp theIcon toMove highlightedNo nextIcon)
		;EO: this has been freshly decompiled. It should be tested.
		(= toMove (if argc amount else 1))
		(if
			(<
				(= nextIcon
					(- (= highlightedNo (self indexOf: highlightedIcon)) toMove)
				)
				0
			)
			(= nextIcon (- size 1))
		)
		(repeat
			(= theIcon (self at: nextIcon))
			(if
				(and
					(IsObject theIcon)
					(not (& (theIcon signal?) DISABLED))
					(or
						(> (theIcon nsLeft?) -1)
						(not (theIcon isKindOf: InvItem))
					)
				)
			)
			(break)
		)
		(self highlight: theIcon 1)
	)
	
	(method (drawInvWindow whom selection
						&tmp numOwned tallestInv widestInv numIcons tallestIcon iconBarWidth
						cWide cHigh node obj invW invH iTop iLeft iBottom iRight theNumRows atX atY i
						theCurPos invWin)
		(= theCurPos
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
		)
		(if firstThru
			(if (IsObject items)
				(items dispose:)
				(= items 0)
			)
			(= items (Set new:))
		)
		(= node (self first:))
		(while node
			(if
				((= obj (NodeValue node))
					isKindOf: InvItem
				)
				(if (obj ownedBy: whom)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(items add: obj)
					(obj nsLeft: -5 nsRight: -5 nsTop: -5 nsBottom: -5)
					(if (> (= cWide (CelWide (obj view?) (obj loop?) (obj cel?))) widestInv)
						(= widestInv cWide)
					)
					(if (> (= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?))) tallestInv)
						(= tallestInv cHigh)
					)
				else
					(obj signal: (| (obj signal?) DISABLED))
				)
			else
				(++ numIcons)
				(= iconBarWidth
					(+ iconBarWidth (CelWide (obj view?) (obj loop?) (obj cel?)))
				)
				(if (> (= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?))) tallestIcon)
					(= tallestIcon cHigh)
				)
			)
			(= node
				(self next: node)
			)
		)
		(if (not (items size?))
			(if (and (<= 0 normalHeading) (<= 0 empty))
				(Print addText: empty 0 0 0 0 0 normalHeading init:)
			else
				(Prints {You'll get nothing and like it!})
			)
			(if (IsObject items)
				(items dispose:)
			)
			(return FALSE)
		)
		(= numOwned
			(if (< (items size?) dispAmount)
				(items size?)
			else
				dispAmount
			)
		)
		(= invW (Max (+ 4 iconBarWidth) (* numCols (+ 4 widestInv))))
		(= iTop (/ (- 190 (= invH (* numRows (+ 4 tallestInv)))) 2))
		(= iLeft (/ (- 320 invW) 2))
		(= iBottom (+ iTop invH))
		(= iRight (+ iLeft invW))
		(if (= invWin (self window?))
			(invWin
				top: iTop
				left: iLeft
				right: iRight
				bottom: iBottom
				open: (not firstThru)
			)
		)
		(= i numCols)
		(if numOwned
			(= atX
				(+ 2
					(if (invWin respondsTo: #yOffset)
						(invWin yOffset?)
					else
						0
					)
				)
			)
			(= atY
				(= theNumRows
					(+
						4
						(if (invWin respondsTo: #xOffset)
							(invWin xOffset?)
						else
							0
						)
					)
				)
			)
			(= theCurPos curPos)
			(while
				(and
					(< theCurPos (+ curPos dispAmount))
					(< theCurPos (items size?))
				)
				(if
					(not
						(& ((= obj (items at: theCurPos)) signal?) FIXED_POSN)
					)
					(obj
						nsLeft:
							(+
								theNumRows
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
								atX
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
						(= theNumRows (+ theNumRows widestInv))
					else
						(= i numCols)
						(= atX (+ atX tallestInv))
						(= theNumRows atY)
					)
				else
					(= theNumRows (obj nsLeft?))
					(= atX (obj nsTop?))
				)
				(obj show:)
				(if (== obj selection) (obj highlight:))
				(++ theCurPos)
			)
		)
		(= theNumRows (/ (- (- (invWin right?) (invWin left?) ) iconBarWidth) 2))
		(= invH (- (invWin bottom?) (invWin top?)))
		(= atX $7FFF)
		(if firstThru
			(= node (self first:))
			(while node
				(if
					(not
						((= obj (NodeValue node))
							isKindOf: InvItem
						)
					)
					(= cWide (CelWide (obj view?) (obj loop?) (obj cel?)))
					(= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?)))
					(if (not (& (obj signal?) FIXED_POSN))
						(if (== atX $7FFF) (= atX (- invH cHigh)))
						(obj
							nsLeft: theNumRows
							nsTop: atX
							nsBottom: (+ atX cHigh)
							nsRight: (+ theNumRows cWide)
						)
					)
					(= theNumRows (+ (obj nsLeft?) cWide))
					(= atX (obj nsTop?))
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(obj show:)
				)
				(= node
					(self next: node)
				)
			)
		)
		(if (not curPos)
			(upIcon signal: (| (upIcon signal?) DISABLED))
		else
			(upIcon signal: (& (upIcon signal?) (~ DISABLED)))
		)
		(if (>= curPos (- (items size?) dispAmount))
			(downIcon signal: (| (downIcon signal?) DISABLED))
		else
			(downIcon signal: (& (downIcon signal?) (~ DISABLED)))
		)
		(upIcon show:)
		(downIcon show:)
		(return TRUE)
	)
	
	(method (scroll param1)
		(cond 
			((and argc (> 0 param1))
				(if (< (= curPos (- curPos scrollAmount)) 0)
					(= curPos 0)
				)
			)
			(
				(>
					(= curPos (+ curPos scrollAmount))
					(- size dispAmount)
				)
				(= curPos (- size dispAmount))
			)
		)
		(= firstThru 0)
		(selectIcon select:)
		(self show: ego)
	)
)
