;;; Sierra Script 1.0 - (do not remove this comment)
(script# INVENT)
(include game.sh)
(use Main)
(use Print)
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
	
	(method (doit &tmp thisIcon event eType [temp3 3] eO oldPort keyInvoked [buffer 50])
		(asm
code_0848:
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      event
			send     4
			bnt      code_0861
			pushi    #dispose
			pushi    0
			lat      event
			send     4
			jmp      code_0848
code_0861:
			pushi    #dispose
			pushi    0
			lat      event
			send     4
			ldi      0
			sat      event
code_086c:
			pTos     state
			ldi      32
			and     
			bnt      code_0cee
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
			sat      keyInvoked
			pushi    #localize
			pushi    0
			lofsa    invEvent
			send     4
			pToa     curIcon
			bnt      code_0946
			pushi    #modifiers
			pushi    0
			lofsa    invEvent
			send     4
			not     
			bnt      code_0946
			pTos     curIcon
			pToa     selectIcon
			ne?     
			bnt      code_0946
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      1
			eq?     
			bt       code_091c
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      4
			eq?     
			bnt      code_0907
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      13
			eq?     
			bnt      code_0907
			ldi      1
			sat      keyInvoked
			bt       code_091c
code_0907:
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      256
			eq?     
			bnt      code_0946
			ldi      1
			sat      keyInvoked
			bnt      code_0946
code_091c:
			pTos     curIcon
			pToa     helpIconItem
			ne?     
			bt       code_0930
			pushi    #signal
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			ldi      16
			and     
			bnt      code_0946
code_0930:
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
code_0946:
			pushi    1
			lofsa    invEvent
			push    
			callk    MapKeyToDir,  2
			pushi    #type
			pushi    0
			lofsa    invEvent
			send     4
			sat      eType
			lag      fastCast
			bnt      code_096b
			pushi    #handleEvent
			pushi    1
			lofsa    invEvent
			push    
			lag      fastCast
			send     6
			jmp      code_0ce3
code_096b:
			lst      eType
			ldi      1
			eq?     
			bnt      code_098e
			pushi    #modifiers
			pushi    0
			lofsa    invEvent
			send     4
			bnt      code_098e
			pushi    #advanceCurIcon
			pushi    0
			self     4
			pushi    #claimed
			pushi    1
			pushi    1
			lofsa    invEvent
			send     6
			jmp      code_0ce3
code_098e:
			lst      eType
			ldi      0
			eq?     
			bnt      code_09b6
			pushi    #firstTrue
			pushi    2
			pushi    218
			lofsa    invEvent
			push    
			self     8
			sat      thisIcon
			bnt      code_09b6
			push    
			pToa     highlightedIcon
			ne?     
			bnt      code_09b6
			pushi    #highlight
			pushi    1
			lst      thisIcon
			self     6
			jmp      code_0ce3
code_09b6:
			lst      eType
			ldi      1
			eq?     
			bt       code_09db
			lst      eType
			ldi      4
			eq?     
			bnt      code_09d2
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			ldi      13
			eq?     
			bt       code_09db
code_09d2:
			lst      eType
			ldi      256
			eq?     
			bnt      code_0a68
code_09db:
			pushi    1
			pTos     highlightedIcon
			callk    IsObject,  2
			bnt      code_0ce3
			pushi    178
			pushi    #view
			pTos     highlightedIcon
			lst      eType
			ldi      1
			eq?     
			push    
			self     8
			bnt      code_0ce3
			pTos     highlightedIcon
			pToa     okButton
			eq?     
			bnt      code_0a02
			jmp      code_0cee
			jmp      code_0ce3
code_0a02:
			pTos     highlightedIcon
			pToa     helpIconItem
			eq?     
			bnt      code_0a51
			pushi    #cursor
			pushi    0
			pToa     highlightedIcon
			send     4
			push    
			ldi      65535
			ne?     
			bnt      code_0a27
			pushi    #setCursor
			pushi    1
			pushi    #cursor
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			lag      theGame
			send     6
code_0a27:
			pTos     state
			ldi      2048
			and     
			bnt      code_0a38
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_0ce3
code_0a38:
			pToa     helpIconItem
			bnt      code_0ce3
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
			jmp      code_0ce3
code_0a51:
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
			jmp      code_0ce3
code_0a68:
			lst      eType
			ldi      64
			and     
			bnt      code_0b1f
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			dup     
			ldi      3
			eq?     
			bnt      code_0a88
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0b1b
code_0a88:
			dup     
			ldi      7
			eq?     
			bnt      code_0a97
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0b1b
code_0a97:
			dup     
			ldi      1
			eq?     
			bnt      code_0acc
			pToa     highlightedIcon
			bnt      code_0ac3
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
			sat      thisIcon
			bnt      code_0ac3
			pushi    #highlight
			pushi    2
			lst      thisIcon
			pushi    1
			self     8
			jmp      code_0b1b
code_0ac3:
			pushi    #retreat
			pushi    0
			self     4
			jmp      code_0b1b
code_0acc:
			dup     
			ldi      5
			eq?     
			bnt      code_0b08
			pToa     highlightedIcon
			bnt      code_0b00
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
			sat      thisIcon
			bnt      code_0b00
			pushi    #highlight
			pushi    2
			lst      thisIcon
			pushi    1
			self     8
			jmp      code_0b1b
code_0b00:
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0b1b
code_0b08:
			dup     
			ldi      0
			eq?     
			bnt      code_0b1b
			lst      eType
			ldi      4
			and     
			bnt      code_0b1b
			pushi    #advanceCurIcon
			pushi    0
			self     4
code_0b1b:
			toss    
			jmp      code_0ce3
code_0b1f:
			lst      eType
			ldi      4
			eq?     
			bnt      code_0b4f
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			dup     
			ldi      9
			eq?     
			bnt      code_0b3e
			pushi    #advance
			pushi    0
			self     4
			jmp      code_0b4b
code_0b3e:
			dup     
			ldi      3840
			eq?     
			bnt      code_0b4b
			pushi    #retreat
			pushi    0
			self     4
code_0b4b:
			toss    
			jmp      code_0ce3
code_0b4f:
			lst      eType
			ldi      16384
			and     
			bnt      code_0ce3
			pushi    #firstTrue
			pushi    2
			pushi    218
			lofsa    invEvent
			push    
			self     8
			sat      thisIcon
			bnt      code_0ce3
			lst      eType
			ldi      8192
			and     
			bnt      code_0c0d
			lat      thisIcon
			bnt      code_0bec
			pushi    #noun
			pushi    0
			send     4
			bnt      code_0bec
			pushi    7
			pushi    0
			pushi    #modNum
			pushi    0
			lat      thisIcon
			send     4
			push    
			pushi    #noun
			pushi    0
			lat      thisIcon
			send     4
			push    
			pushi    #helpVerb
			pushi    0
			lat      thisIcon
			send     4
			push    
			pushi    0
			pushi    1
			lea      @buffer
			push    
			callk    Message,  14
			bnt      code_0bec
			pushi    #respondsTo
			pushi    1
			pushi    236
			lag      systemWindow
			send     6
			bnt      code_0be1
			pushi    #eraseOnly
			pushi    0
			lag      systemWindow
			send     4
			sat      eO
			pushi    #eraseOnly
			pushi    1
			pushi    1
			lag      systemWindow
			send     6
			pushi    1
			lea      @buffer
			push    
			calle    Prints,  2
			pushi    #eraseOnly
			pushi    1
			lst      eO
			lag      systemWindow
			send     6
			jmp      code_0bec
code_0be1:
			pushi    1
			lea      @buffer
			push    
			calle    Prints,  2
code_0bec:
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
			jmp      code_0ce3
code_0c0d:
			lst      thisIcon
			pToa     okButton
			eq?     
			bnt      code_0c1a
			jmp      code_0cee
			jmp      code_0ce3
code_0c1a:
			pushi    #isKindOf
			pushi    1
			class    InvItem
			push    
			lat      thisIcon
			send     6
			not     
			bnt      code_0c7a
			pushi    #select
			pushi    2
			lst      thisIcon
			lat      keyInvoked
			not     
			push    
			self     8
			bnt      code_0ce3
			lat      thisIcon
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
			lst      thisIcon
			pToa     helpIconItem
			eq?     
			bnt      code_0ce3
			pTos     state
			ldi      2048
			and     
			bnt      code_0c64
			pushi    #noClickHelp
			pushi    0
			self     4
			jmp      code_0ce3
code_0c64:
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
			jmp      code_0ce3
code_0c7a:
			pToa     curIcon
			bnt      code_0ce3
			pushi    #respondsTo
			pushi    1
			pushi    236
			lag      systemWindow
			send     6
			bnt      code_0c9e
			pushi    #eraseOnly
			pushi    0
			lag      systemWindow
			send     4
			sat      eO
			pushi    #eraseOnly
			pushi    1
			pushi    1
			lag      systemWindow
			send     6
code_0c9e:
			pushi    #isKindOf
			pushi    1
			class    InvItem
			push    
			pToa     curIcon
			send     6
			bnt      code_0cbc
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			pToa     curIcon
			send     4
			push    
			lat      thisIcon
			send     6
			jmp      code_0ccd
code_0cbc:
			pushi    #doVerb
			pushi    1
			pushi    #message
			pushi    0
			lofsa    invEvent
			send     4
			push    
			lat      thisIcon
			send     6
code_0ccd:
			pushi    #respondsTo
			pushi    1
			pushi    236
			lag      systemWindow
			send     6
			bnt      code_0ce3
			pushi    #eraseOnly
			pushi    1
			lst      eO
			lag      systemWindow
			send     6
code_0ce3:
			pushi    #dispose
			pushi    0
			lofsa    invEvent
			send     4
			jmp      code_086c
code_0cee:
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
	
	(method (hide &tmp theCurs)
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
			(if (= theCurs ((theIconBar curIcon?) cursor?))
				(theGame setCursor: theCurs)
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
		(if (== curIcon helpIconItem)
			(curIcon signal: (| (curIcon signal?) TRANSLATOR))
		else
			(curIcon signal: (& (curIcon signal?) (~ TRANSLATOR)))
		)
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
			(Print
				addTextF: @buffer {%s %s} normalHeading empty
				init:
			)
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

(instance invEvent of Event
	(properties)
)
