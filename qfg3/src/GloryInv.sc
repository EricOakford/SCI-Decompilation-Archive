;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_INV) ;16
(include game.sh) (include "16.shm")
(use Main)
(use GloryWindow)
(use Print)
(use IconBar)
(use Window)
(use Invent)
(use System)

(public
	gloryInv 0
	pageCode 1
	invLook 2
)

(local
	local0
	curInventory
	[local2 2]
	theGameSetCursor
	local5
)
(procedure (localproc_015c &tmp i temp1)
	(= temp1 -32768)
	(= i 0)
	(while (<= i 10)
		(if (& disabledIcons temp1)
			(theIconBar disable: i)
		)
		(= temp1 (>> temp1 $0001))
		(++ i)
	)
)

(class NumInvItem of InvItem
	(properties
		signal IMMEDIATE
		modNum GLORY_INV
		amount 0
		realOwner 0
		mustKeep 0
	)
	
	(method (init)
		(super init: &rest)
		(= cursor Cursor)
	)
	
	(method (select)
		(if (== curInventory ego)
			(if (super select: &rest)
				(Cursor view: view loop: loop cel: cel)
			)
		else
			(self doVerb: V_DROP (inventory indexOf: self))
			(inventory highlightedIcon: invSelect)
		)
	)
	
	(method (doVerb theVerb &tmp temp0 temp1 i [amtBuf 20] [str 60])
		(Message MsgGet GLORY_INV noun V_LOOK NULL 1 @amtBuf)
		(= i (inventory indexOf: self))
		(cond 
			((== curRoomNum 310)
				(= temp0 0)
			)
			((== curRoomNum 430)
				(= temp0 14)
			)
			(else
				(= temp0 14)
			)
		)
		(= temp1
			(if (>= i iDaggers) (< i iVine)
			else
				0
			)
		)
		(return
			(switch theVerb
				(V_LOOK
					(cond 
						((== curInventory ego)
							(if (> amount 1)
								(Message MsgGet GLORY_INV NULL theVerb C_MULTIPLE_ITEM 1 @str)
								(Print addTextF: @str @amtBuf amount value)
							else
								(Message MsgGet GLORY_INV NULL theVerb C_SINGLE_ITEM 1 @str)
								(Print addTextF: @str @amtBuf value)
							)
						)
						(
							(or
								(not temp1)
								(and
									(== [chestInventory (+ (- i 10) temp0)] 1)
									(== curRoomNum 310)
								)
								(and
									(== [chestInventory (+ (- i 10) temp0)] 1)
									(== curRoomNum 430)
								)
							)
							(Message MsgGet GLORY_INV NULL theVerb C_CHEST_SINGLE 1 @str)
							(Print addTextF: @str @amtBuf)
						)
						(else
							(Message MsgGet GLORY_INV NULL theVerb C_CHEST_MULTIPLE 1 @str)
							(Print
								addTextF:
									@str
									@amtBuf
									(if (not temp1)
										amount
									else
										[chestInventory (+ (- i 10) temp0)]
									)
							)
						)
					)
					(Print init:)
					(Print addText: noun theVerb NULL C_SINGLE_ITEM 0 0 GLORY_INV init:)
				)
				(73
					(if mustKeep
						(if (== i iMagicDrum)
							(Print addText: NULL theVerb C_MUSTKEEP_DRUM 1 0 0 GLORY_INV init:)
						else
							(Message MsgGet GLORY_INV NULL theVerb C_MUSTKEEP 1 @str)
							(Print addTextF: @str @amtBuf init:)
						)
					else
						(if (== curInventory ego)
							(if (> amount 1)
								(-- amount)
								(Message MsgGet GLORY_INV NULL theVerb C_DROP_MULTIPLE 1 @str)
								(Print addTextF: @str @amtBuf init:)
								(self roomGets:)
							else
								(if (== (theIconBar curInvIcon?) self)
									(theIconBar disable: ICON_USEIT curInvIcon: 0)
								)
								(Message MsgGet GLORY_INV NULL theVerb C_DROP_SINGLE 1 @str)
								(Print addTextF: @amtBuf init:)
								(self
									realOwner: curRoomNum
									loseItem:
									roomGets:
									checkPage:
								)
							)
							(if
								(and
									(< (+ (ego wtCarried:) value) (ego maxLoad:))
									(Btst fOverloaded)
								)
								(Bclr fOverloaded)
								(ego setMotion: 0 changeGait: -1 FALSE)
							)
							(return 1)
						else
							(ego get: i 1)
							(theIconBar curInvIcon: self enable: 7)
							(inventory curIcon: 0)
							(if (and (>= i iDaggers) (< i iVine))
								(if (== [chestInventory (+ (- i 10) temp0)] 1)
									(= [chestInventory (+ (- i 10) temp0)] 0)
									(Message MsgGet GLORY_INV 0 theVerb 9 1 @str)
									(Print addTextF: @amtBuf init:)
								else
									(-- [chestInventory (+ (- i 10) temp0)])
									(Message MsgGet GLORY_INV NULL theVerb 10 1 @str)
									(Print addTextF: @amtBuf init:)
								)
							else
								(Message MsgGet GLORY_INV NULL theVerb 9 1 @str)
								(Print addTextF: @amtBuf init:)
							)
							(= owner 0)
							(self checkPage:)
						)
						(return TRUE)
					)
				)
				(else 
					(if (== curInventory ego)
						(Print addText: NULL NULL C_NOTHING_HAPPENS 1 0 0 GLORY_INV init:)
					else
						(self doVerb: V_DROP (inventory indexOf: self))
					)
				)
			)
		)
	)
	
	(method (loseItem)
		(self owner: curRoomNum realOwner: curRoomNum amount: 0)
	)
	
	(method (roomGets &tmp temp0)
		(if (not (OneOf curRoomNum 310 430)) (return))
		(cond 
			((== curRoomNum 310)
				(= temp0 0)
			)
			((== curRoomNum 430)
				(= temp0 14)
			)
			(else
				(= temp0 14)
			)
		)
		(if
			(and
				(>= (inventory indexOf: self) 10)
				(< (inventory indexOf: self) 24)
			)
			(++
				[chestInventory
				(- (+ (inventory indexOf: self) temp0) 10)]
			)
		else
			(self loseItem:)
		)
	)
	
	(method (checkPage &tmp i temp1)
		(if (== local5 2)
			(= temp1 0)
			(= i 0)
			(while (< i iLastInvItem)
				(if
				(== ((inventory at: i) owner?) curInventory)
					(= temp1 1)
					(= i 48)
				)
				(++ i)
			)
			(inventory hide:)
			(if temp1
				(if (> i iLastInvItem)
					(inventory show: curInventory selectIcon: invSelect)
				else
					(= local0 1)
					(invPageUp select:)
				)
			)
		else
			(inventory hide:)
			(pageCode init: curInventory)
		)
	)
)

(instance gloryInv of Inventory
	(properties
		normalHeading 9
		empty 27
	)
	
	(method (init)
		((= inventory self)
			add:
				theRoyals
				theSword
				theFineDagger
				theFineSpear
				theChainmail
				theShield
				theGrapnel
				theToolkit
				theGuildCard
				theTinderbox
				theDaggers
				theCurePills
				theHealPills
				theManaPills
				theRations
				theWaterskin
				theDispell
				theFish
				theMeat
				theFruit
				theBeads
				theSkins
				theHorn
				theRocks
				theVine
				theOil
				theRope
				theGagGift
				thePin
				theHoney
				theFeather
				theAmulet
				theLeopard
				theBird
				theOpal
				theVineFruit
				theGem
				thePeaceWater
				theHeartGift
				theOrchid
				theRobe
				theBridge
				theEye
				theNote
				theWood
				theMagicSpear
				theMagicDrum
				invPageDown
				invPageUp
				invLook
				invSelect
				invDrop
				ok
				invHelp
				dummyIcon
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			eachElementDo: #init
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
			state: NOCLICKHELP
		)
		(ego get: iRoyals)
		(ego get: iRations)
	)
	
	(method (showSelf who &tmp temp0 temp1 cursorLoop cursorCel theIconBarCurInvIcon)
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
		(if (not local5) (= curIcon 0))
		(if (self show: (if argc who else ego))
			(if (not local5)
				(= cursorLoop (Cursor loop?))
				(= cursorCel (Cursor cel?))
				(= local5 1)
				(self doit:)
				(= local5 0)
				(cond 
					(
					(and (IsObject curIcon) (curIcon isKindOf: NumInvItem))
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
						(if (= temp1 ((theIconBar curIcon?) cursor?))
							(theGame setCursor: temp1)
						)
					)
					((IsObject (theIconBar curInvIcon?))
						(= theIconBarCurInvIcon (theIconBar curInvIcon?))
						(Cursor
							view: (theIconBarCurInvIcon view?)
							loop: (theIconBarCurInvIcon loop?)
							cel: (theIconBarCurInvIcon cel?)
						)
					)
					(else (Cursor loop: cursorLoop cel: cursorCel))
				)
				(= curIcon 0)
			else
				(while ((= temp0 ((user curEvent?) new:)) type?)
				)
			)
		)
	)
	
	(method (hide)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(= state (& state $ffdf))
		)
		(if window (window dispose:))
	)
	
	(method (advance &tmp temp0 temp1)
		(asm
			ldi      1
			sat      temp1
code_0ef4:
			lst      temp1
			pToa     size
			le?     
			bnt      code_0f4a
			pushi    64
			pushi    1
			lst      temp1
			pushi    #indexOf
			pushi    1
			pTos     highlightedIcon
			self     6
			add     
			push    
			pToa     size
			mod     
			push    
			self     6
			sat      temp0
			pushi    1
			push    
			callk    IsObject,  2
			not     
			bnt      code_0f27
			pushi    1
			pushi    #first
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			sat      temp0
code_0f27:
			lst      temp0
			lofsa    dummyIcon
			ne?     
			bnt      code_0f3d
			pushi    #signal
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_0f3d
code_0f3d:
			lst      temp1
			ldi      1
			add     
			push    
			pToa     size
			mod     
			sat      temp1
			jmp      code_0ef4
code_0f4a:
			pushi    218
			pushi    #view
			lst      temp0
			pTos     state
			ldi      32
			and     
			push    
			self     8
			ret     
		)
	)
	
	(method (retreat &tmp temp0 temp1)
		(asm
			ldi      1
			sat      temp1
code_0f5f:
			lst      temp1
			pToa     size
			le?     
			bnt      code_0fb6
			pushi    64
			pushi    1
			pushi    #indexOf
			pushi    1
			pTos     highlightedIcon
			self     6
			push    
			lat      temp1
			sub     
			push    
			pToa     size
			mod     
			push    
			self     6
			sat      temp0
			pushi    1
			push    
			callk    IsObject,  2
			not     
			bnt      code_0f93
			pushi    1
			pushi    #last
			pushi    0
			self     4
			push    
			callk    NodeValue,  2
			sat      temp0
code_0f93:
			lst      temp0
			lofsa    dummyIcon
			ne?     
			bnt      code_0fa9
			pushi    #signal
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			not     
			bnt      code_0fa9
code_0fa9:
			lst      temp1
			ldi      1
			add     
			push    
			pToa     size
			mod     
			sat      temp1
			jmp      code_0f5f
code_0fb6:
			pushi    218
			pushi    #view
			lst      temp0
			pTos     state
			ldi      32
			and     
			push    
			self     8
			ret     
		)
	)
	
	(method (noClickHelp)
		(super noClickHelp:)
		(theGame setCursor: 942 TRUE)
	)
	
	(method (drawInvWindow whom selection
						&tmp
						numOwned tallestInv widestInv
						numIcons tallestIcon iconBarWidth
						cWide cHigh node obj invH numCols
						invW iTop iLeft iBottom iRight numRows atX atY firstX i invWindow [str 50] [loadBuf 20])
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
		(= node (self first:))
		(while node
			(if ((= obj (NodeValue node)) isKindOf: InvItem)
				(if (obj ownedBy: whom)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(++ numOwned)
					(if
						(>
							(= cWide
								(CelWide (obj view?) (obj loop?) (obj cel?))
							)
							(+ 3 widestInv)
						)
						(= widestInv (- cWide 3))
					)
					(if
						(>
							(= cHigh
								(CelHigh (obj view?) (obj loop?) (obj cel?))
							)
							(+ 3 tallestInv)
						)
						(= tallestInv (- cHigh 3))
					)
				else
					(obj signal: (| (obj signal?) DISABLED))
				)
			else
				(++ numIcons)
				(= iconBarWidth (+ iconBarWidth (CelWide (obj view?) (obj loop?) (obj cel?))))
				(if (> (= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?))) tallestIcon)
					(= tallestIcon cHigh)
				)
			)
			(= node (self next: node))
		)
		(if (== (dummyIcon owner?) whom)
			(-- numOwned)
		)
		(if (not numOwned)
			(return FALSE)
		)
		(if (<= numOwned 6)
			(= numRows 1)
			(= numCols numOwned)
		else
			(if
				(and
					(> (= numRows (Sqrt numOwned)) 1)
					(>= (* numRows numRows) numOwned)
				)
				(-- numRows)
			)
			(if (> numRows 3) (= numRows 3))
			(if (<= (* numRows (= numCols (/ numOwned numRows))) numOwned)
				(++ numCols)
			)
			(if (> numRows 1)
				(cond 
					((> numCols 8) (= numCols 8))
					((< numCols 6) (= numCols 6))
				)
			)
			(if (>= (* numCols (- numRows 1)) numOwned) (-- numRows))
		)
		(= invW (Max (+ 4 iconBarWidth) (* numCols (+ 1 widestInv))))
		(= iTop
			(/ (- 190 (= invH (* numRows (+ 2 tallestInv)))) 2)
		)
		(= iLeft (/ (- 320 invW) 2))
		(= iBottom (+ iTop invH))
		(= iRight (+ iLeft invW))
		(if (= invWindow (self window?))
			(invWindow
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
					(if (invWindow respondsTo: #yOffset)
						(invWindow yOffset?)
					else
						0
					)
				)
			)
			(= firstX
				(= atX
					(+
						2
						(if (invWindow respondsTo: #xOffset)
							(invWindow xOffset?)
						else
							0
						)
					)
				)
			)
			(= node (self first:))
			(while node
				(if
					(and
						(not
							(& ((= obj (NodeValue node)) signal?) DISABLED)
						)
						(obj isKindOf: InvItem)
					)
					(if (not (& (obj signal?) FIXED_POSN))
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
					(if (== obj selection) (obj highlight:))
				)
				(= node (self next: node))
			)
		)
		(= atX
			(/
				(- (- (invWindow right?) (invWindow left?)) iconBarWidth)
				2
			)
		)
		(= invH (- (invWindow bottom?) (invWindow top?)))
		(= atY $7fff)
		(= node (self first:))
		(while node
			(if (not ((= obj (NodeValue node)) isKindOf: InvItem))
				(= cWide
					(CelWide (obj view?) (obj loop?) (obj cel?))
				)
				(= cHigh
					(CelHigh (obj view?) (obj loop?) (obj cel?))
				)
				(if (not (& (obj signal?) FIXED_POSN))
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
			(= node (self next: node))
		)
		(if (== whom ego)
			(Message MsgGet GLORY_INV NULL NULL C_MAX_WEIGHT 1 @loadBuf)
			(Display
				(Format @str @loadBuf (ego maxLoad:))
				p_font 999
				p_at (+ (/ invW 2) 12) (- invH 12)
				p_color (window color?)
			)
			(Message MsgGet GLORY_INV NULL NULL C_CARRIED_WEIGHT 1 @loadBuf)
			(Display
				(Format @str @loadBuf (ego wtCarried:))
				p_font 999
				p_at 2 (- invH 12)
				p_color (if (>= (ego wtCarried:) (ego maxLoad:)) 65 else (window color?))
			)
			(Graph GDrawLine
				(- invH 16)
				(+ (/ invW 2) 10)
				(- invH 6)
				(+ (/ invW 2) 10)
				58
				-1
				-1
			)
			(Graph GDrawLine
				(- invH 16)
				0
				(- invH 16)
				invW
				58
				-1
				-1
			)
			(Graph GShowBits (- invH 17) 0 invH invW 1)
		)
		(dummyIcon
			nsTop: 0
			nsLeft: 0
			nsRight: (window right?)
			nsBottom: (window bottom?)
			owner: whom
			signal: IMMEDIATE
		)
		(return TRUE)
	)
)

(instance invWin of GloryWindow
	(properties
		yOffset 28
	)
	
	(method (open)
		(invLook
			nsLeft: (- (/ (- (self right?) (self left?)) 2) 75)
		)
		(= bottom (+ bottom 15))
		(super open:)
	)
)

(instance pageCode of Code
	(properties)
	
	(method (init who &tmp i temp1 temp2)
		(if (not local5)
			(= theGameSetCursor (theGame setCursor:))
		)
		(= curInventory who)
		(inventory selectIcon: invSelect)
		(if (== curInventory ego)
			(inventory window: invWin)
			(invDrop noun: 5 loop: 6 signal: 1)
		else
			(if (== curRoomNum 310) (= temp2 0) else (= temp2 14))
			(invDrop noun: 3 loop: 8 signal: 3)
		)
		(invSelect message: -1)
		(= global205 0)
		(invPageUp owner: 0)
		(invPageDown owner: 0)
		(= i 0)
		(while (< i iLastInvItem)
			((inventory at: i) owner: 0)
			(if
				(and
					(or
						(== ((inventory at: i) realOwner?) curInventory)
						(and
							(!= curInventory ego)
							(>= i iDaggers)
							(< i iVine)
							[chestInventory (+ (- i 10) temp2)]
						)
					)
					(< (++ global205) 25)
				)
				(if (== global205 24) (= temp1 i))
				((inventory at: i) owner: curInventory)
			)
			(++ i)
		)
		(if (> global205 24)
			((inventory at: temp1) owner: 0)
			(invPageDown highlightColor: -1 owner: curInventory)
		)
		(inventory showSelf: curInventory)
	)
)

(instance dummyIcon of InvItem
	(properties
		view 932
		loop 5
		cursor 942
		signal IMMEDIATE
	)
	
	(method (show)
	)
	
	(method (select)
		(return FALSE)
	)
	
	(method (highlight)
	)
	
	(method (onMe theObjOrX)
		(return
			(if
				(and
					(>= (theObjOrX x?) nsLeft)
					(>= (theObjOrX y?) nsTop)
					(<= (theObjOrX x?) nsRight)
				)
				(<= (theObjOrX y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance invPageDown of InvItem
	(properties
		view 932
		loop 5
		cursor 942
		signal (| RELVERIFY IMMEDIATE)
		noun N_PAGEDOWN
		modNum GLORY_INV
		helpVerb V_HELP
	)
	
	(method (show)
		(super show:)
		(DrawCel 932 loop 2 (+ nsLeft (CelWide view loop cel)) nsTop -1)
	)
	
	(method (select &tmp i)
		(= i 0)
		(while (< i iLastInvItem)
			(if
			(== ((inventory at: i) realOwner?) curInventory)
				(if
				(== ((inventory at: i) owner?) curInventory)
					((inventory at: i) owner: 1)
				else
					((inventory at: i) owner: curInventory)
				)
			)
			(++ i)
		)
		(= local5 2)
		(invPageUp owner: curInventory highlightColor: -1)
		(invPageDown owner: 0)
		(inventory hide: showSelf: curInventory)
		(return FALSE)
	)
	
	(method (doVerb theVerb)
		(if (!= theVerb V_LOOK)
			(self select:)
		else
			(Print addText: noun V_LOOK NULL 1 0 0 GLORY_INV init:)
		)
	)
)

(instance invPageUp of InvItem
	(properties
		view 932
		loop 5
		cursor 942
		signal (| RELVERIFY IMMEDIATE)
		noun N_PAGEUP
		modNum GLORY_INV
		helpVerb V_HELP
	)
	
	(method (show)
		(super show:)
		(DrawCel 932 loop 2 (+ nsLeft (CelWide view loop cel)) nsTop -1)
	)
	
	(method (select &tmp i)
		(= i 0)
		(while (< i iLastInvItem)
			(if (== ((inventory at: i) realOwner?) curInventory)
				(if (== ((inventory at: i) owner?) 1)
					((inventory at: i) owner: curInventory)
				else
					((inventory at: i) owner: 0)
				)
			)
			(++ i)
		)
		(= local5 1)
		(inventory hide:)
		(invPageUp owner: 0)
		(if (not local0)
			(invPageDown owner: curInventory highlightColor: -1)
		)
		(= local0 0)
		(inventory showSelf: curInventory)
		(return 0)
	)
	
	(method (doVerb theVerb)
		(if (!= theVerb V_LOOK)
			(self select:)
		else
			(Print addText: noun V_LOOK NULL 1 0 0 GLORY_INV init:)
		)
	)
)

(instance invLook of IconItem
	(properties
		view 932
		loop 2
		cel 0
		cursor 941
		message V_LOOK
		signal (| FIXED_POSN RELVERIFY)
		noun N_LOOK
		modNum GLORY_INV
		helpVerb V_HELP
	)
	
	(method (highlight)
		(if (== (invDrop noun?) noun)
			(invDrop highlight: &rest)
		)
		(super highlight: &rest)
	)
)

(instance invSelect of IconItem
	(properties
		view 932
		loop 0
		cel 0
		cursor 942
		noun N_SELECT
		modNum GLORY_INV
		helpVerb V_HELP
	)
)

(instance invDrop of IconItem
	(properties
		view 932
		loop 6
		cel 0
		cursor 944
		message V_DROP
		noun N_DROP
		modNum GLORY_INV
		helpVerb V_HELP
	)
	
	(method (onMe)
		(return (if (== noun 3) (return 0) else (super onMe: &rest)))
	)
)

(instance invHelp of IconItem
	(properties
		view 932
		loop 1
		cel 0
		cursor 949
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		noun N_HELP
		modNum GLORY_INV
		helpVerb V_HELP
	)
	
	(method (show)
		(super show:)
		(DrawCel 932 7 0 (- (+ nsLeft (CelWide view loop cel)) 28) nsTop -1)
	)
)

(instance ok of IconItem
	(properties
		view 932
		loop 3
		cel 0
		cursor 942
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		noun N_OK
		modNum GLORY_INV
		helpVerb V_HELP
	)
)

(instance theRoyals of NumInvItem
	(properties
		view 905
		loop 4
		cel 3
		message V_DINARS
		value 2
		mustKeep TRUE
	)
	
	(method (doVerb theVerb &tmp [str 30] [dinarBuf 20] [royalBuf 20] [commonBuf 20] temp90 [wtBuf 20])
		(switch theVerb
			(1
				(Format @dinarBuf {Dinars: %d.} numDinars)
				(if (== message V_DINARS)
					(Print addText: @dinarBuf)
				else
					(= temp90 0)
					(if numDinars
						(Message MsgGet GLORY_INV NULL NULL C_DINARS 1 @dinarBuf)
						(Format @str @dinarBuf numDinars)
						(Print addText: @str)
					)
					(if amount
						(Message MsgGet GLORY_INV NULL NULL C_ROYALS 1 @royalBuf)
						(Format @str @royalBuf amount)
						(Print addText: @str 0 (= temp90 (+ temp90 10)))
					)
					(if commons
						(Message MsgGet GLORY_INV NULL C_COMMONS 1 @commonBuf)
						(Format @str @commonBuf commons)
						(Print addText: @str 0 (= temp90 (+ temp90 10)))
					)
					(if (or numDinars amount commons)
						(Message MsgGet GLORY_INV NULL NULL C_TOTAL_WEIGHT 1 @wtBuf)
						(Format @str @wtBuf (+ numDinars amount commons))
						(Print addText: @str 0 (= temp90 (+ temp90 15)))
					else
						(Print addText: NULL V_ROYALS C_NO_MONEY 1 0 0 GLORY_INV)
					)
				)
				(Print init:)
				(Print addText: 0 theVerb 27 1 0 0 GLORY_INV init:)
			)
			(73
				(Print addText: 0 73 GLORY_INV 1 0 0 GLORY_INV init:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theSword of NumInvItem
	(properties
		view 905
		loop 2
		;cel (if state 3 else 0)	;EO: This should have 2 different icons, but that is not a constant expression
		message V_SWORD
		noun N_SWORD
		value 420
		mustKeep TRUE
	)
	
	(method (doVerb theVerb &tmp [wtBuf 30] [str 160])
		(switch theVerb
			(V_LOOK
				(Message MsgGet GLORY_INV NULL theVerb C_SINGLE_ITEM 1 @str)
				(if state
					(Message MsgGet GLORY_INV NULL V_LOOK C_SWORD_MAGIC 1 @wtBuf)
				else
					(Message MsgGet GLORY_INV NULL V_LOOK C_SWORD_FINE 1 @wtBuf)
				)
				(Print addTextF: @str @wtBuf value init:)
				(if state
					(Print addText: NULL theVerb C_SWORD_MAGIC 2 0 0 GLORY_INV init:)
				else
					(Print addText: NULL theVerb C_SWORD_FINE 2 0 0 GLORY_INV init:)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance theFineDagger of NumInvItem
	(properties
		view 905
		loop 6
		cel 1
		message V_FINE_DAGGER
		noun N_FINE_DAGGER
		value 60
		mustKeep TRUE
	)
)

(instance theFineSpear of NumInvItem
	(properties
		view 905
		loop 10
		cel 4
		message V_FINE_SPEAR
		noun N_FINE_SPEAR
		value 240
		mustKeep TRUE	;EO: Added to prevent unwinnable game
	)
)

(instance theChainmail of NumInvItem
	(properties
		view 905
		loop 2
		cel 1
		message V_CHAINMAIL
		noun N_CHAINMAIL
		value 2100
		mustKeep TRUE
	)
)

(instance theShield of NumInvItem
	(properties
		view 905
		loop 2
		cel 2
		message V_SHIELD
		noun N_SHIELD
		value 720
		mustKeep TRUE
	)
)

(instance theGrapnel of NumInvItem
	(properties
		view 905
		loop 2
		cel 7
		message V_GRAPNEL
		noun N_GRAPNEL
		value 80
		mustKeep TRUE
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_ROPE)
			(theRope owner: 0 realOwner: 0)
			(= cel 8)
			(= state 1)
			(= noun N_ROPE_GRAPNEL)
			(inventory curIcon: self)
			(self select: checkPage:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theToolkit of NumInvItem
	(properties
		view 905
		loop 4
		cel 2
		message V_TOOLKIT
		noun N_TOOLKIT
		value 30
		mustKeep TRUE	;EO: Added to prevent unwinnable game
	)
)

(instance theGuildCard of NumInvItem
	(properties
		view 905
		loop 4
		cel 0	;it's blank
		message V_GUILDCARD
		noun N_GUILD_CARD
		value 2
	)
)

(instance theTinderbox of NumInvItem
	(properties
		view 905
		loop 6
		cel 0
		message N_TINDERBOX
		noun N_TINDERBOX
		value 5
		mustKeep TRUE
	)
)

(instance theDaggers of NumInvItem
	(properties
		view 905
		loop 4
		cel 1
		message V_DAGGER
		noun N_DAGGERS
		value 90
	)
)

(instance theCurePills of NumInvItem
	(properties
		view 905
		loop 0
		cel 5
		message V_CUREPILLS
		noun N_CURE_PILLS
		value 5
	)
)

(instance theHealPills of NumInvItem
	(properties
		view 905
		loop 0
		cel 6
		message V_HEALPILLS
		noun N_HEAL_PILLS
		value 5
	)
)

(instance theManaPills of NumInvItem
	(properties
		view 905
		loop 0
		cel 7
		message V_MANAPILLS
		noun N_MANA_PILLS
		value 5
	)
)

(instance theRations of NumInvItem
	(properties
		view 905
		loop 0
		cel 1
		message V_RATIONS
		noun N_RATIONS
		value 20
	)
)

(instance theWaterskin of NumInvItem
	(properties
		view 905
		loop 6
		cel 5
		message V_WATERSKIN
		noun N_WATERSKIN
		value 20
	)
)

(instance theDispell of NumInvItem
	(properties
		view 905
		loop 0
		cel 8
		message V_DISPEL
		noun N_DISPEL
		value 40
	)
)

(instance theFish of NumInvItem
	(properties
		view 905
		loop 4
		cel 6
		message V_FISH
		noun N_FISH
		value 20
	)
)

(instance theMeat of NumInvItem
	(properties
		view 905
		loop 4
		cel 7
		message V_MEAT
		noun N_MEAT
		value 20
	)
)

(instance theFruit of NumInvItem
	(properties
		view 905
		loop 4
		cel 8
		message V_FRUIT
		noun N_FRUIT
		value 20
	)
)

(instance theBeads of NumInvItem
	(properties
		view 905
		loop 6
		cel 3
		message V_BEADS
		noun N_BEADS
		value 10
	)
)

(instance theSkins of NumInvItem
	(properties
		view 905
		loop 10
		cel 3
		message V_ZEBRA_SKINS
		noun N_ZEBRA_SKINS
		value 120
	)
)

(instance theHorn of NumInvItem
	(properties
		view 905
		loop 10
		cel 2
		message V_HORN
		noun N_HORN
		value 40
	)
)

(instance theRocks of NumInvItem
	(properties
		view 905
		loop 0
		cel 2
		message V_ROCK
		noun N_ROCKS
		value 30
	)
)

(instance theVine of NumInvItem
	(properties
		view 905
		loop 10
		cel 8
		message V_VINE
		noun N_VINE
		value 30
	)
)

(instance theOil of NumInvItem
	(properties
		view 905
		loop 6
		cel 4
		message V_OIL
		noun N_OIL
		value 30
		mustKeep TRUE
	)
)

(instance theRope of NumInvItem
	(properties
		view 905
		loop 6
		cel 2
		message V_ROPE
		noun N_ROPE
		value 200
		mustKeep TRUE
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_GRAPNEL)
			(= realOwner (= owner 0))
			(inventory curIcon: theGrapnel)
			(theGrapnel noun: N_ROPE_GRAPNEL cel: 8 state: 1 select:)
			(self checkPage:)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theGagGift of NumInvItem
	(properties
		view 905
		loop 2
		cel 4
		message V_GAGGIFT
		noun N_GAG_GIFT
		value 30
		mustKeep TRUE
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(if (== cel 4)
					(cond 
						(((ScriptID curRoomNum) script?)
							(Print addText: N_OPEN_GIFT NULL C_OPEN_GIFT_LATER 0 0 0 GLORY_INV init:)
							(return FALSE)
						)
						((> (ego view?) 5) (Print addText: N_OPEN_GIFT NULL C_READ_WRAPPING_LATER 0 0 0 GLORY_INV init:)
							(return FALSE)
						)
						(else
							(= cel 6)
							(= value 5)
							(inventory curIcon: ok)
							(if (== (theIconBar curInvIcon?) self)
								(theIconBar curInvIcon: 0 disable: ICON_USEIT)
							)
							(inventory hide:)
							((ScriptID curRoomNum) setScript: (ScriptID 30))
							(return FALSE)
						)
					)
				else
					(inventory curIcon: ok)
					(inventory hide:)
					(if (== (theIconBar curInvIcon?) self)
						(theIconBar curInvIcon: 0 disable: ICON_USEIT advanceCurIcon:)
					)
					(HandsOff)
					(ego learn: JUGGLE solvePuzzle: fLearnJugglingLights 4 puzzleWIZARD)
					(messager say: N_OPEN_GIFT NULL C_LEARN_JUGGLING_LIGHTS 0 0 GLORY_INV)
					(self mustKeep: FALSE loseItem: realOwner: 0 owner: 0)
					(HandsOn)
					(localproc_015c)
				)
			else
				0
			)
		)
	)
	
	(method (doVerb theVerb &tmp [wtBuf 30] [str 160])
		(switch theVerb
			(V_LOOK
				(Message MsgGet GLORY_INV NULL theVerb C_SINGLE_ITEM 1 @str)
				(if (== cel 4)
					(Message MsgGet GLORY_INV N_GAG_GIFT V_LOOK C_UNOPENED 1 @wtBuf)
				else
					(Message MsgGet GLORY_INV N_GAG_GIFT V_LOOK C_UNWRAPPED 1 @wtBuf)
				)
				(Print addTextF: @str @wtBuf value init:)
				(if (== cel 4)
					(Print addText: N_GAG_GIFT V_LOOK C_UNOPENED 2 0 0 GLORY_INV init:)
				else
					(Print addText: N_GAG_GIFT V_LOOK C_UNWRAPPED 2 0 0 GLORY_INV init:)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance thePin of NumInvItem
	(properties
		view 905
		loop 0
		cel 3
		message V_PIN
		noun N_PIN
		value 2
	)
)

(instance theHoney of NumInvItem
	(properties
		view 905
		loop 4
		cel 4
		message V_HONEY
		noun N_HONEY
		value 20
		mustKeep TRUE
	)
)

(instance theFeather of NumInvItem
	(properties
		view 905
		loop 4
		cel 5
		message V_FEATHER
		noun N_FEATHER
		value 2
	)
)

(instance theAmulet of NumInvItem
	(properties
		view 905
		loop 6
		cel 6	;it's blank
		message V_AMULET
		noun N_AMULET
		value 10
	)
)

(instance theLeopard of NumInvItem
	(properties
		view 905
		loop 6
		cel 7
		message V_LEOPARD
		noun N_LEOPARD
		value 30
		mustKeep TRUE
	)
)

(instance theBird of NumInvItem
	(properties
		view 905
		loop 6
		cel 8
		message V_BIRD
		noun N_BIRD
		value 30
		mustKeep TRUE
	)
)

(instance theOpal of NumInvItem
	(properties
		view 905
		loop 8
		message V_OPAL
		noun N_OPAL
		value 6
		mustKeep TRUE
	)
)

(instance theVineFruit of NumInvItem
	(properties
		view 905
		loop 8
		cel 1
		message V_VINEFRUIT
		noun N_VINE_FRUIT
		value 20
		mustKeep TRUE
	)
)

(instance theGem of NumInvItem
	(properties
		view 905
		loop 8
		cel 2
		message V_GEM
		noun N_GEM
		value 6
		mustKeep TRUE
	)
)

(instance thePeaceWater of NumInvItem
	(properties
		view 905
		loop 8
		cel 4
		message V_PEACEWATER
		noun N_PEACE_WATER
		value 80
	)
)

(instance theHeartGift of NumInvItem
	(properties
		view 905
		loop 8
		cel 5
		message V_HEARTGIFT
		noun N_HEART_GIFT
		value 20
		mustKeep TRUE
	)
)

(instance theOrchid of NumInvItem
	(properties
		view 905
		loop 8
		cel 6
		message V_ORCHID
		noun N_ORCHID
		value 12
		mustKeep TRUE
	)
	
	(method (doVerb theVerb &tmp [wtBuf 30] [str 160])
		(switch theVerb
			(V_LOOK
				(Message MsgGet GLORY_INV NULL theVerb C_SINGLE_ITEM 1 @str)
				(if state
					(Message MsgGet GLORY_INV NULL V_LOOK C_ORCHID_GLOWING 1 @wtBuf)
				else
					(Message MsgGet GLORY_INV NULL V_LOOK C_ORCHID 1 @wtBuf)
				)
				(Print addTextF: @str @wtBuf value init:)
				(if state
					(Print addText: NULL theVerb C_ORCHID_GLOWING 2 0 0 GLORY_INV init:)
				else
					(Print addText: NULL theVerb C_ORCHID 2 0 0 GLORY_INV init:)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRobe of NumInvItem
	(properties
		view 905
		loop 10
		cel 5
		message V_ROBE
		noun N_ROBE
		value 30
		mustKeep TRUE
	)
)

(instance theBridge of NumInvItem
	(properties
		view 905
		loop 10
		cel 6
		message V_BRIDGE
		noun N_BRIDGE
		value 300
	)
)

(instance theEye of NumInvItem
	(properties
		view 905
		loop 10
		cel 7	;it's blank
		message V_EYE
		noun N_EYE
		value 6
		mustKeep TRUE
	)
)

(instance theNote of NumInvItem
	(properties
		view 905
		loop 0
		cel 0
		message V_NOTE
		noun N_NOTE
		value 2
	)
)

(instance theWood of NumInvItem
	(properties
		view 905
		loop 8
		cel 8
		message V_WOOD
		noun N_WOOD
		value 60
		mustKeep TRUE
	)
)

(instance theMagicSpear of NumInvItem
	(properties
		view 905
		loop 10
		message V_MAGIC_SPEAR
		noun N_MAGIC_SPEAR
		value 240
		mustKeep TRUE
	)
)

(instance theMagicDrum of NumInvItem
	(properties
		view 905
		loop 10
		cel 1
		message V_MAGICDRUM
		noun N_MAGIC_DRUM
		value 180
		mustKeep TRUE
	)
)
