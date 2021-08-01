;;; Sierra Script 1.0 - (do not remove this comment)
(script# 995)
(include sci.sh)
(use Main)
(use Intrface)
(use IconBar)
(use Window)
(use System)


(local
	numCols
)
(class InvI of IconI
	(properties
		view 0
		loop 0
		cel 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		state $0000
		cursor 999
		type $4000
		message 4
		modifiers $0000
		signal $0000
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		description 0
		owner 0
		script 0
		value 0
	)
	
	(method (show &tmp [temp0 4])
		(DrawCel view loop cel nsLeft nsTop -1)
	)
	
	(method (highlight param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(= temp4
			(if (and argc param1) highlightColor else lowlightColor)
		)
		(= temp0 (- nsTop 2))
		(= temp1 (- nsLeft 2))
		(= temp2 (+ nsBottom 1))
		(= temp3 (+ nsRight 1))
		(Graph grDRAW_LINE temp0 temp1 temp0 temp3 temp4 -1 -1)
		(Graph grDRAW_LINE temp0 temp3 temp2 temp3 temp4 -1 -1)
		(Graph grDRAW_LINE temp2 temp3 temp2 temp1 temp4 -1 -1)
		(Graph grDRAW_LINE temp2 temp1 temp0 temp1 temp4 -1 -1)
		(Graph
			grUPDATE_BOX
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
				(not (& signal $0004))
			else
				0
			)
		)
	)
	
	(method (ownedBy param1)
		(return (== owner param1))
	)
	
	(method (moveTo theOwner)
		(= owner theOwner)
		(if (and value (= theOwner ego))
			(theGame changeScore: value)
			(= value 0)
		)
		(return self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Printf 995 0 description))
		)
	)
)

(class Inv of IconBar
	(properties
		elements 0
		size 0
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
		state $0400
		activateHeight 10
		y 0
		normalHeading {Roger is carrying:}
		heading 0
		empty {nothing!}
		curScore {Score: %d out of %d}
		showScore 1
		iconBarInvItem 0
		okButton 0
		selectIcon 0
	)
	
	(procedure (localproc_01a2 param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 inventoryFirst temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 theNumCols)
		(= temp0
			(= temp1 (= temp2 (= temp3 (= temp4 (= temp5 0)))))
		)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(cond 
				(
				((= temp9 (NodeValue inventoryFirst)) isKindOf: InvI)
					(if (temp9 ownedBy: param1)
						(temp9 signal: (& (temp9 signal?) $fffb))
						(++ temp0)
						(if
							(>
								(= temp6
									(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
								)
								temp2
							)
							(= temp2 temp6)
						)
						(if
							(>
								(= temp7
									(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
								)
								temp1
							)
							(= temp1 temp7)
						)
					else
						(temp9 signal: (| (temp9 signal?) $0004))
					)
				)
				((not (& (temp9 signal?) $0004))
					(++ temp3)
					(= temp5
						(+
							temp5
							(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
						)
					)
					(if
						(>
							(= temp7
								(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
							)
							temp4
						)
						(= temp4 temp7)
					)
				)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
		(if (not temp0)
			(Printf 995 1 normalHeading empty)
			(return)
		)
		(if (> (* (= temp16 (Sqrt temp0)) temp16) temp0)
			(-- temp16)
		)
		(if (> temp16 3) (= temp16 3))
		(if
		(< (* temp16 (= numCols (/ temp0 temp16))) temp0)
			(++ numCols)
		)
		(= temp10 (Max (+ 4 temp5) (* numCols (+ 4 temp2))))
		(= temp12
			(/
				(- 190 (= temp11 (+ (* temp16 (+ 4 temp1)) temp4)))
				2
			)
		)
		(= temp13 (/ (- 320 temp10) 2))
		(= temp14 (+ temp12 temp11))
		(= temp15 (+ temp13 temp10))
		(if (inventory window?)
			((inventory window?)
				top: temp12
				left: temp13
				right: temp15
				bottom: temp14
				open:
			)
		)
		(= theNumCols numCols)
		(if temp0
			(= temp18
				(+
					2
					(if ((inventory window?) respondsTo: #yOffset)
						((inventory window?) yOffset?)
					else
						0
					)
				)
			)
			(= temp19
				(= temp17
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
			(= inventoryFirst (inventory first:))
			(while inventoryFirst
				(if
					(and
						(not
							(&
								((= temp9 (NodeValue inventoryFirst)) signal?)
								$0004
							)
						)
						(temp9 isKindOf: InvI)
					)
					(if (not (& (temp9 signal?) $0080))
						(temp9
							nsLeft:
								(+
									temp17
									(/
										(-
											temp2
											(= temp6
												(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
											)
										)
										2
									)
								)
							nsTop:
								(+
									temp18
									(/
										(-
											temp1
											(= temp7
												(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
											)
										)
										2
									)
								)
						)
						(temp9
							nsRight: (+ (temp9 nsLeft?) temp6)
							nsBottom: (+ (temp9 nsTop?) temp7)
						)
						(if (-- theNumCols)
							(= temp17 (+ temp17 temp2))
						else
							(= theNumCols numCols)
							(= temp18 (+ temp18 temp1))
							(= temp17 temp19)
						)
					else
						(= temp17 (temp9 nsLeft?))
						(= temp18 (temp9 nsTop?))
					)
					(temp9 show:)
					(if (== temp9 param2)
						(theGame setCursor: invCursor 1)
						(temp9 highlight:)
					)
				)
				(= inventoryFirst (inventory next: inventoryFirst))
			)
		)
		(= temp17
			(/
				(-
					(-
						((inventory window?) right?)
						((inventory window?) left?)
					)
					temp5
				)
				2
			)
		)
		(= temp11
			(-
				((inventory window?) bottom?)
				((inventory window?) top?)
			)
		)
		(= temp18 32767)
		(= inventoryFirst (inventory first:))
		(while inventoryFirst
			(if
				(and
					(not
						((= temp9 (NodeValue inventoryFirst)) isKindOf: InvI)
					)
					(not (& (temp9 signal?) $0004))
				)
				(= temp6
					(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
				)
				(= temp7
					(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
				)
				(if (not (& (temp9 signal?) $0080))
					(if (== temp18 32767) (= temp18 (- temp11 temp7)))
					(temp9
						nsLeft: temp17
						nsTop: temp18
						nsBottom: temp11
						nsRight: (+ temp17 temp6)
					)
				)
				(= temp17 (+ (temp9 nsLeft?) temp6))
				(= temp18 (temp9 nsTop?))
				(temp9 signal: (& (temp9 signal?) $fffb) show:)
			)
			(= inventoryFirst (inventory next: inventoryFirst))
		)
	)
	
	
	(method (init)
		(= inventory self)
		(= heading normalHeading)
	)
	
	(method (doit &tmp theCurIcon newEvent newEventType temp3 systemWindowEraseOnly [temp5 3] temp8)
		(while
		(and (= newEvent (Event new:)) (& state $0020))
			(= temp8 0)
			(newEvent localize:)
			(if
				(and
					curIcon
					(not (newEvent modifiers?))
					(!= curIcon selectIcon)
					(or
						(== (newEvent type?) 1)
						(and
							(== (newEvent type?) 4)
							(== (newEvent message?) 13)
							(= temp8 1)
						)
						(and (== (newEvent type?) 256) (= temp8 1))
					)
					(or
						(!= curIcon helpIconItem)
						(& (helpIconItem signal?) $0010)
					)
				)
				(newEvent type: 16384 message: (curIcon message?))
			)
			(MapKeyToDir newEvent)
			(cond 
				(
					(and
						(== (= newEventType (newEvent type?)) 1)
						(newEvent modifiers?)
					)
					(self advanceCurIcon:)
					(newEvent claimed: 1)
				)
				(
					(and
						(== newEventType 0)
						(= theCurIcon (self firstTrue: #onMe newEvent))
						(!= theCurIcon highlightedIcon)
					)
					(self highlight: theCurIcon)
				)
				(
					(or
						(== newEventType 1)
						(and (== newEventType 4) (== (newEvent message?) 13))
						(== newEventType 256)
					)
					(if
						(and
							(IsObject highlightedIcon)
							(self select: highlightedIcon (== newEventType 1))
						)
						(if (== highlightedIcon okButton) (break))
						(if (== highlightedIcon helpIconItem)
							(if (!= (highlightedIcon cursor?) -1)
								(theGame setCursor: (helpIconItem cursor?))
							)
							(if helpIconItem
								(helpIconItem signal: (| (helpIconItem signal?) $0010))
							)
						else
							(= curIcon highlightedIcon)
							(theGame setCursor: (curIcon cursor?) 1)
						)
					)
				)
				((& newEventType $0040)
					(switch (newEvent message?)
						(3 (self advance:))
						(7 (self retreat:))
						(1 (self retreat: numCols))
						(5 (self advance: numCols))
						(0
							(if (& newEventType $0004) (self advanceCurIcon:))
						)
					)
				)
				((== newEventType 4)
					(switch (newEvent message?)
						(9 (self advance:))
						(3840 (self retreat:))
					)
				)
				(
					(and
						(== newEventType 16384)
						(= theCurIcon (self firstTrue: #onMe newEvent))
					)
					(if (== (newEvent message?) 6)
						(if (and theCurIcon (theCurIcon helpStr?))
							(DoAudio 2 (theCurIcon helpStr?))
						)
						(helpIconItem signal: (& (helpIconItem signal?) $ffef))
						(theGame setCursor: normalCursor)
					else
						(if (== theCurIcon okButton) (break))
						(cond 
							((not (theCurIcon isKindOf: InvI))
								(if (self select: theCurIcon (not temp8))
									(= curIcon theCurIcon)
									(theGame setCursor: (curIcon cursor?) 1)
								)
							)
							(curIcon
								(if (systemWindow respondsTo: #eraseOnly)
									(= systemWindowEraseOnly (systemWindow eraseOnly?))
									(systemWindow eraseOnly: 1)
								)
								(if (curIcon isKindOf: InvI)
									(theCurIcon
										doVerb: (curIcon message?) (self indexOf: curIcon)
									)
								else
									(theCurIcon doVerb: (newEvent message?))
								)
								(if (systemWindow respondsTo: #eraseOnly)
									(systemWindow eraseOnly: systemWindowEraseOnly)
								)
							)
						)
					)
				)
			)
			(newEvent dispose:)
		)
		(newEvent dispose:)
		(self hide:)
	)
	
	(method (showSelf param1)
		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(if (theIconBar height?) (theIconBar hide:))
		(if (not window) (= window (SysWindow new:)))
		(if (window window?) (window dispose:) (= window 0))
		(if (not okButton)
			(= okButton (NodeValue (self first:)))
		)
		(= curIcon 0)
		(theGame setCursor: (selectIcon cursor?))
		(self show: (if argc param1 else ego) doit:)
	)
	
	(method (show param1)
		(= state (| state $0020))
		(localproc_01a2
			(if argc param1 else ego)
			(theIconBar curIcon?)
		)
	)
	
	(method (hide)
		(if (& state $0020)
			(sounds pause: 0)
			(= state (& state $ffdf))
		)
		(if window (window dispose:))
		(if
		(and (IsObject curIcon) (curIcon isKindOf: InvI))
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
				(theGame setCursor: (curIcon cursor?) 1)
			)
		)
	)
	
	(method (advance param1 &tmp temp0 temp1 temp2 temp3)
		(= temp1 (if argc param1 else 1))
		(= temp3
			(+ temp1 (= temp2 (self indexOf: highlightedIcon)))
		)
		(repeat
			(= temp0
				(self
					at: (if (<= temp3 size) temp3 else (mod temp3 (- size 1)))
				)
			)
			(if (not (IsObject temp0))
				(= temp0 (NodeValue (self first:)))
			)
			(if (not (& (temp0 signal?) $0004)) (break))
			(++ temp3)
		)
		(if global400
			(theGame
				setCursor:
					theCursor
					1
					(+
						(temp0 nsLeft?)
						(/ (- (temp0 nsRight?) (temp0 nsLeft?)) 2)
					)
					(- (temp0 nsBottom?) 3)
			)
		else
			(theGame setCursor: theCursor 1)
			(Intersections
				(+
					(temp0 nsLeft?)
					(/ (- (temp0 nsRight?) (temp0 nsLeft?)) 2)
				)
				(- (temp0 nsBottom?) 3)
			)
		)
		(self highlight: temp0)
	)
	
	(method (retreat param1 &tmp temp0 temp1 temp2 temp3)
		(= temp1 (if argc param1 else 1))
		(= temp3
			(- (= temp2 (self indexOf: highlightedIcon)) temp1)
		)
		(repeat
			(= temp0 (self at: temp3))
			(if (not (IsObject temp0))
				(= temp0 (NodeValue (self last:)))
			)
			(if (not (& (temp0 signal?) $0004)) (break))
			(-- temp3)
		)
		(if global400
			(theGame
				setCursor:
					theCursor
					1
					(+
						(temp0 nsLeft?)
						(/ (- (temp0 nsRight?) (temp0 nsLeft?)) 2)
					)
					(- (temp0 nsBottom?) 3)
			)
		else
			(theGame setCursor: theCursor 1)
			(Intersections
				(+
					(temp0 nsLeft?)
					(/ (- (temp0 nsRight?) (temp0 nsLeft?)) 2)
				)
				(- (temp0 nsBottom?) 3)
			)
		)
		(self highlight: temp0)
	)
	
	(method (advanceCurIcon &tmp theCurIcon)
		(= theCurIcon curIcon)
		(while
			((= theCurIcon
				(self at: (mod (+ (self indexOf: theCurIcon) 1) size))
			)
				isKindOf: InvI
			)
		)
		(= curIcon theCurIcon)
		(theGame setCursor: (curIcon cursor?) 1)
	)
	
	(method (ownedBy param1)
		(self firstTrue: #ownedBy param1)
	)
)
