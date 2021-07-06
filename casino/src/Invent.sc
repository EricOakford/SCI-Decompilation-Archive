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
(procedure (FindIcon hIcon y1 y2 &tmp thisIcon newEvent theY theX)
	(= theX
		(+
			(/ (- (hIcon nsRight?) (hIcon nsLeft?)) 2)
			(hIcon nsLeft?)
		)
	)
	(= theY y1)
	(return
		(while (>= (Abs (- theY y2)) 4)
			(if
				(= thisIcon
					(self
						firstTrue:
							#onMe
							((= newEvent (Event new:)) x: theX y: theY yourself:)
					)
				)
				(newEvent dispose:)
				(return thisIcon)
			)
			(newEvent dispose:)
			(if (< y1 y2)
				(= theY (+ theY 4))
			else
				(= theY (- theY 4))
			)
		)
	)
)

(class InvItem of IconItem
	(properties
		name "InvI"
		view 0
		loop 0
		cel 0
		nsTop 0
		cursor ARROW_CURSOR
		message NULL
		signal 0
		owner 0
		script 0
		value 0
		noun 0
		modNum 0
	)
	
	(method (show &tmp iconNo iX tmpX celWide)
		(DrawCel view loop cel nsLeft nsTop -1)
	)
	
	(method (highlight tOrF &tmp t l b r sColor)
		(if (== highlightColor -1) (return))
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
	
	(method (onMe event)
		(return
			(if
				(super onMe: event) (not (& signal DISABLED))
			else FALSE
			)
		)
	)
	
	(method (ownedBy id)
		(return (== owner id))
	)
	
	(method (moveTo id)
		(= owner id)
		(if (and value (== id ego))
			(theGame changeScore: value)
			(= value 0)
		)
		(return self)
	)
	
	(method (doVerb theVerb)
		(if (not modNum) (= modNum curRoomNum))
		(if
		(and msgType (Message MsgGet modNum noun theVerb NULL 1))
			(messager say: noun theVerb NULL NULL NULL modNum)
		)
	)
)

(class Inventory of IconBar
	(properties
		name "Inv"
		normalHeading {You are carrying:}
		heading 0
		empty {nothing!}
		highlightedIcon 0
		curIcon 0
		window 0
		iconBarInvItem 0
		okButton NULL
		selectIcon NULL
	)
	
	(method (init)
		(= heading normalHeading)
	)
	
	(method (doit &tmp temp0 temp1 temp2 [temp3 3] temp6 temp7 temp8 [temp9 50])
		(asm
code_084a:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp1
			send     4
			bnt      code_0863
			pushi    #dispose
			pushi    0
			lat      temp1
			send     4
			jmp      code_084a
code_0863:
			pushi    #dispose
			pushi    0
			lat      temp1
			send     4
			ldi      0
			sat      temp1
code_086e:
			pTos     state
			ldi      32
			and     
			bnt      code_0cc4
			pushi    #type
			pushi    1
			pushi    0
			pushi    37
			pushi    1
			pushi    0
			pushi    61
			pushi    1
			pushi    0
			pushi    0
			pushi    1
			pushi    0
			pushi    1
			pushi    1
			pushi    0
			pushi    73
			pushi    1
			pushi    0
			pushi    147
			pushi    1
			pushi    0
			lofsa    invEvent
			send     42
			pushi    2
			pushi    32767
			lofsa    invEvent
			push    
			callk    GetEvent,  4
			pushi    #x
			pushi    0
			lofsa    invEvent
			send     4
			sag      mouseX
			pushi    #y
			pushi    0
			lofsa    invEvent
			send     4
			sag      mouseY
			ldi      0
			sat      temp8
			pushi    #localize
			pushi    0
			lofsa    invEvent
			send     4
			pToa     curIcon
			bnt      code_0948
			pushi    #modifiers
			pushi    0
			lofsa    invEvent
			send     4
			not     
			bnt      code_0948
			pTos     curIcon
			pToa     selectIcon
			ne?     
			bnt      code_0948
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      1
			eq?     
			bt       code_091e
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      4
			eq?     
			bnt      code_0909
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      13
			eq?     
			bnt      code_0909
			ldi      1
			sat      temp8
			bt       code_091e
code_0909:
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      256
			eq?     
			bnt      code_0948
			ldi      1
			sat      temp8
			bnt      code_0948
code_091e:
			pTos     curIcon
			pToa     helpIconItem
			ne?     
			bt       code_0932
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      16
			and     
			bnt      code_0948
code_0932:
			pushi    #type
			pushi    1
			pushi    16384
			pushi    37
			pushi    1
			pushi    #message
			pushi    0
			pToa     curIcon
			send     4
			push    
			lofsa    invEvent
			send     12
code_0948:
			pushi    1
			lofsa    invEvent
			push    
			callk    MapKeyToDir,  2
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			sat      temp2
			push    
			ldi      1
			eq?     
			bnt      code_097c
			pushi    #modifiers
			pushi    0
			lofsa    invEvent
			send     4
			bnt      code_097c
			pushi    #advanceCurIcon
			pushi    0
			self     4
			pushi    #claimed
			pushi    1
			pushi    1
			lofsa    invEvent
			send     6
			jmp      code_0cb9
code_097c:
			lst      temp2
			ldi      0
			eq?     
			bnt      code_09a4
			pushi    #firstTrue
			pushi    2
			pushi    207
			lofsa    invEvent
			push    
			self     8
			sat      temp0
			bnt      code_09a4
			push    
			pToa     highlightedIcon
			ne?     
			bnt      code_09a4
			pushi    #highlight
			pushi    1
			lst      temp0
			self     6
			jmp      code_0cb9
code_09a4:
			lst      temp2
			ldi      1
			eq?     
			bt       code_09c9
			lst      temp2
			ldi      4
			eq?     
			bnt      code_09c0
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      13
			eq?     
			bt       code_09c9
code_09c0:
			lst      temp2
			ldi      256
			eq?     
			bnt      code_0a56
code_09c9:
			pushi    1
			pTos     highlightedIcon
			callk    IsObject,  2
			bnt      code_0cb9
			pushi    179
			pushi    #view
			pTos     highlightedIcon
			lst      temp2
			ldi      1
			eq?     
			push    
			self     8
			bnt      code_0cb9
			pTos     highlightedIcon
			pToa     okButton
			eq?     
			bnt      code_09f0
			jmp      code_0cc4
			jmp      code_0cb9
code_09f0:
			pTos     highlightedIcon
			pToa     helpIconItem
			eq?     
			bnt      code_0a3f
			pushi    #cursor
			pushi    0
			pToa     highlightedIcon
			send     4
			push    
			ldi      65535
			ne?     
			bnt      code_0a15
			pushi    #setCursor
			pushi    1
			pushi    #cursor
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			lag      theGame
			send     6
code_0a15:
			pTos     state
			ldi      2048
			and     
			bnt      code_0a26
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_0cb9
code_0a26:
			pToa     helpIconItem
			bnt      code_0cb9
			pushi    14
			pushi    #x
			pushi    #signal
			pushi    0
			send     4
			push    
			ldi      16
			or      
			push    
			pToa     helpIconItem
			send     6
			jmp      code_0cb9
code_0a3f:
			pToa     highlightedIcon
			aTop     curIcon
			pushi    #setCursor
			pushi    1
			pushi    #cursor
			pushi    0
			pToa     curIcon
			send     4
			push    
			lag      theGame
			send     6
			jmp      code_0cb9
code_0a56:
			lst      temp2
			ldi      64
			and     
			bnt      code_0b0d
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			dup     
			ldi      3
			eq?     
			bnt      code_0a76
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0b09
code_0a76:
			dup     
			ldi      7
			eq?     
			bnt      code_0a85
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0b09
code_0a85:
			dup     
			ldi      1
			eq?     
			bnt      code_0aba
			pToa     highlightedIcon
			bnt      code_0ab1
			pushi    3
			push    
			pushi    #nsTop
			pushi    0
			send     4
			push    
			ldi      1
			sub     
			push    
			pushi    0
			call     FindIcon,  6
			sat      temp0
			bnt      code_0ab1
			pushi    #highlight
			pushi    2
			lst      temp0
			pushi    1
			self     8
			jmp      code_0b09
code_0ab1:
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0b09
code_0aba:
			dup     
			ldi      5
			eq?     
			bnt      code_0af6
			pToa     highlightedIcon
			bnt      code_0aee
			pushi    3
			push    
			pushi    #nsBottom
			pushi    0
			send     4
			push    
			ldi      1
			add     
			push    
			pushi    #bottom
			pushi    0
			pToa     window
			send     4
			push    
			call     FindIcon,  6
			sat      temp0
			bnt      code_0aee
			pushi    #highlight
			pushi    2
			lst      temp0
			pushi    1
			self     8
			jmp      code_0b09
code_0aee:
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0b09
code_0af6:
			dup     
			ldi      0
			eq?     
			bnt      code_0b09
			lst      temp2
			ldi      4
			and     
			bnt      code_0b09
			pushi    #advanceCurIcon
			pushi    0
			self     4
code_0b09:
			toss    
			jmp      code_0cb9
code_0b0d:
			lst      temp2
			ldi      4
			eq?     
			bnt      code_0b3d
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			dup     
			ldi      9
			eq?     
			bnt      code_0b2c
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0b39
code_0b2c:
			dup     
			ldi      3840
			eq?     
			bnt      code_0b39
			pushi    #retreat
			pushi    0
			self     4
code_0b39:
			toss    
			jmp      code_0cb9
code_0b3d:
			lst      temp2
			ldi      16384
			and     
			bnt      code_0cb9
			pushi    #firstTrue
			pushi    2
			pushi    207
			lofsa    invEvent
			push    
			self     8
			sat      temp0
			bnt      code_0cb9
			lst      temp2
			ldi      8192
			and     
			bnt      code_0be3
			lat      temp0
			bnt      code_0bc2
			pushi    #helpStr
			pushi    0
			send     4
			bnt      code_0bc2
			pushi    3
			lea      @temp9
			push    
			lofsa    {%s}
			push    
			pushi    #helpStr
			pushi    0
			lat      temp0
			send     4
			push    
			callk    Format,  6
			pushi    #respondsTo
			pushi    1
			pushi    226
			lag      systemWindow
			send     6
			bnt      code_0bb9
			pushi    #eraseOnly
			pushi    0
			lag      systemWindow
			send     4
			sat      temp6
			pushi    #eraseOnly
			pushi    1
			pushi    1
			lag      systemWindow
			send     6
			pushi    1
			lea      @temp9
			push    
			calle    Print,  2
			pushi    #eraseOnly
			pushi    1
			lst      temp6
			lag      systemWindow
			send     6
			jmp      code_0bc2
code_0bb9:
			pushi    1
			lea      @temp9
			push    
			calle    Print,  2
code_0bc2:
			pushi    14
			pushi    #x
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      65519
			and     
			push    
			pToa     helpIconItem
			send     6
			pushi    #setCursor
			pushi    1
			pushi    999
			lag      theGame
			send     6
			jmp      code_0cb9
code_0be3:
			lst      temp0
			pToa     okButton
			eq?     
			bnt      code_0bf0
			jmp      code_0cc4
			jmp      code_0cb9
code_0bf0:
			pushi    #isKindOf
			pushi    1
			class    InvItem
			push    
			lat      temp0
			send     6
			not     
			bnt      code_0c50
			pushi    #select
			pushi    2
			lst      temp0
			lat      temp8
			not     
			push    
			self     8
			bnt      code_0cb9
			lat      temp0
			aTop     curIcon
			pushi    #setCursor
			pushi    1
			pushi    #cursor
			pushi    0
			pToa     curIcon
			send     4
			push    
			lag      theGame
			send     6
			lst      temp0
			pToa     helpIconItem
			eq?     
			bnt      code_0cb9
			pTos     state
			ldi      2048
			and     
			bnt      code_0c3a
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_0cb9
code_0c3a:
			pushi    14
			pushi    #x
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      16
			or      
			push    
			pToa     helpIconItem
			send     6
			jmp      code_0cb9
code_0c50:
			pToa     curIcon
			bnt      code_0cb9
			pushi    #respondsTo
			pushi    1
			pushi    226
			lag      systemWindow
			send     6
			bnt      code_0c74
			pushi    #eraseOnly
			pushi    0
			lag      systemWindow
			send     4
			sat      temp6
			pushi    #eraseOnly
			pushi    1
			pushi    1
			lag      systemWindow
			send     6
code_0c74:
			pushi    #isKindOf
			pushi    1
			class    InvItem
			push    
			pToa     curIcon
			send     6
			bnt      code_0c92
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			pToa     curIcon
			send     4
			push    
			lat      temp0
			send     6
			jmp      code_0ca3
code_0c92:
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			lat      temp0
			send     6
code_0ca3:
			pushi    #respondsTo
			pushi    1
			pushi    226
			lag      systemWindow
			send     6
			bnt      code_0cb9
			pushi    #eraseOnly
			pushi    1
			lst      temp6
			lag      systemWindow
			send     6
code_0cb9:
			pushi    #dispose
			pushi    0
			lofsa    invEvent
			send     4
			jmp      code_086e
code_0cc4:
			pushi    #dispose
			pushi    0
			lofsa    invEvent
			send     4
			pushi    #hide
			pushi    0
			self     4
			ret     
		)
	)
	
	(method (showSelf who)
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
		(if (self show: (if argc who else ego))
			(self doit:)
		)
	)
	
	(method (show who &tmp pnv diw)
		(theGame setCursor:
			(if curIcon
				(curIcon cursor?)
			else
				(selectIcon cursor?)
			)
		)
		(= pnv (PicNotValid))
		(PicNotValid FALSE)
		(= state (| state IB_ACTIVE))
		(if (not (= diw (self drawInvWindow: (if argc who else ego) (theIconBar curIcon?))))
			(= state (& state (~ IB_ACTIVE)))
		)
		(PicNotValid pnv)
		(return diw)
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
			(if ((theIconBar curIcon?) cursor?)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
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
			(if (not (& (theIcon signal?) DISABLED))
				(break)
			)
			(++ nextIcon)
		)
		(self highlight: theIcon TRUE)
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
			(if (not (& (theIcon signal?) DISABLED))
				(break)
			)
			(-- nextIcon)
		)
		(self highlight: theIcon TRUE)
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
		(theGame setCursor: (curIcon cursor?))
	)

	
	(method (ownedBy id)
		(self firstTrue: #ownedBy id)
	)
	
	(method (drawInvWindow whom selection &tmp
			numOwned tallestInv widestInv numIcons tallestIcon iconBarWidth
			cWide cHigh node obj invW invH iTop iLeft iBottom iRight numRows
			atX atY firstX i invWin [buffer 50])
		(= numOwned
			(= tallestInv (= widestInv (= numIcons (= tallestIcon (= iconBarWidth 0)))))
		)
		(= node (self first:))
		(while node
			(if
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
			else
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
			(= node (self next: node))
		)
		(if (not numOwned)
			(Format @buffer {%s %s} normalHeading empty)
			(Print @buffer)
			(return 0)
		)
		(if (> (* (= numRows (Sqrt numOwned)) numRows) numOwned)
			(-- numRows)
		)
		(if (> numRows 3) (= numRows 3))
		(if
		(< (* numRows (= numCols (/ numOwned numRows))) numOwned)
			(++ numCols)
		)
		(= invW (Max (+ 4 iconBarWidth) (* numCols (+ 4 widestInv))))
		(= iTop
			(/ (- 190 (= invH (* numRows (+ 4 tallestInv)))) 2)
		)
		(= iLeft (/ (- 320 invW) 2))
		(= iBottom (+ iTop invH))
		(= iRight (+ iLeft invW))
		(if (= invWin (self window?))
			(invWin
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
					(if (invWin respondsTo: #yOffset)
						(invWin yOffset?)
					else
						0
					)
				)
			)
			(= firstX
				(= atX
					(+
						4
						(if (invWin respondsTo: #xOffset)
							(invWin xOffset:)
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
				(- (- (invWin right?) (invWin left?)) iconBarWidth)
				2
			)
		)
		(= invH (- (invWin bottom?) (invWin top?)))
		(= atY $7fff)
		(= node (self first:))
		(while node
			(if
			(not ((= obj (NodeValue node)) isKindOf: InvItem))
				(= cWide
					(CelWide (obj view?) (obj loop?) (obj cel?))
				)
				(= cHigh
					(CelHigh (obj view?) (obj loop?) (obj cel?))
				)
				(if (not (& (obj signal?) FIXED_POSN))
					(if (== atY $7fff) (= atY (- invH cHigh)))
					(obj
						nsLeft: atX
						nsTop: atY
						nsBottom: invH
						nsRight: (+ atX cWide)
					)
				)
				(= atX (+ (obj nsLeft?) cWide))
				(= atY (obj nsTop?))
				(obj signal: (& (obj signal?) (~ DISABLED)) show:)
			)
			(= node (self next: node))
		)
		(return TRUE)
	)
)

(instance invEvent of Event)
