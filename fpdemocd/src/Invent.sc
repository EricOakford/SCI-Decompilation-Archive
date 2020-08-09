;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INVENT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Unkown
;;;;	Updated:
;;;;		Brian K. Hughes
;;;;		July 24, 1992
;;;;
;;;;	Classes:
;;;;		InvItem
;;;;		Inventory


(script# INVENT)
(include game.sh)
(use Main)
(use Print)
(use IconBar)
(use Tutorial)
(use Window)
(use System)


(local 
	numCols 
)

(class InvItem kindof IconItem
	;;; An InvItem is something which can be owned by an object in an
	;;; adventure game.

	(properties
		-info- 		$8004		;(| CLASSBIT NODISPLAY)
		name 			"InvI"	;my literal name
		owner 		0			;who owns this item
		view 			0			;picture of the item
		loop 			0
		cel 			0
		nsLeft		0
		nsTop			0
		nsRight		0
		nsBottom		0
		state			0
		cursor		ARROW_CURSOR ;cursor # (mouse cursor representation of item)
		script 		0			;a script that can control the item
		signal		0
		value			0
		message		NULL		;verb this item gives
		noun			NULL		;noun we respond to
		modNum		-1			;module from which we get our messages
	)

;;;	(methods
;;;		onMe					;select icon if event's x/y is in icon's rectangle
;;;		highlight			;draw/erase selector box
;;;		select				;process item selection
;;;		ownedBy				;return TRUE if owned by given object
;;;		drawSelf				;display this item
;;;		moveTo				;change ownership of this item
;;;		showSelf				;show and identify item
;;;		doit					;manipulate item
;;;		useit					;use one inventory item on another
;;;		changeState			;go to new state in item's script
;;;		doVerb				;handle verbs
;;;	)


	(method (onMe event)
		(return
			(and
				(super onMe: event)
				(not (& signal DISABLED))
			)
		)
	)

	(method (doVerb theVerb &tmp tut)
		(if (== modNum -1)
			(= modNum curRoomNum)
		)

		; If we're not a Print-only game and if there is a message, tell
		;	the messager to go for it

		(if (and	msgType
					(Message MsgGet modNum noun theVerb NULL 1)
				)
			(messager say: noun theVerb NULL NULL NULL modNum)
		)

		; If there's a tutorial running, take care of it
		(if (and	(= tut (theGame script?))
					(tut isKindOf: Tutorial)
			)
			(cond
				((!= (tut nextItem?) self)
					(tut report: self)
				)
				((!= (tut nextAction?) theVerb)
					(tut report: theVerb)
				)
				(else
					(tut cue:)
				)
			)
		)
	)

	(method (highlight  tOrF &tmp t l b r sColor)
		(if (== highlightColor -1)(return))
		(= sColor (if (and argc tOrF) highlightColor else lowlightColor))
		;** Draw or erase selector box based on setting of selected state
		(= t (- nsTop 2))
		(= l (- nsLeft 2))
		(= b (+ nsBottom 1))
		(= r (+ nsRight 1))

		(Graph GDrawLine t l t r  sColor -1 -1)
		(Graph GDrawLine t r b r  sColor -1 -1)
		(Graph GDrawLine b r	b l  sColor -1 -1)
		(Graph GDrawLine b l t l  sColor -1 -1)
		(Graph GShowBits (- nsTop 2) (- nsLeft 2) (+ nsBottom 2) (+ nsRight 2) VMAP)
	)

	(method (show &tmp iconNo iX tmpX celWide)
		(DrawCel	view loop cel nsLeft nsTop -1)
	)

	(method (ownedBy id)
		;** return TRUE if this item owned by passed object ID
		(return (== owner id))
	)
	

	(method (moveTo id)
		;** set item's "owner" to passed object ID
		(= owner id)
		(if (and
				value
				(== id ego)
			)
			(theGame changeScore: value)
			(= value 0)
		)
		(return self)
	)

)

(class Inventory kindof IconBar
	;;; This is the set of all inventory items in the game.

	(properties
		name 					"Inv"
		normalHeading		"You are carrying:"
		heading				0
		empty 				"nothing!"
		highlightedIcon 	0
		curIcon				0
		window				0
		iconBarInvItem 	0
		okButton				NULL
		selectIcon			NULL
	)

;;;	(methods
;;;		showSelf			;display inventory owned by an object
;;;		ownedBy			;return InvItem owned by an object
;;;		highlight
;;;		select
;;;		advance
;;;		retreat
;;;		drawInvWindow
;;;	)

	(method (drawInvWindow whom selection 
						&tmp 
						numOwned tallestInv 	widestInv 
						numIcons tallestIcon iconBarWidth
						cWide	cHigh	node obj	invW invH 
						iTop iLeft iBottom iRight
						numRows atX atY firstX i invWin
						[buffer 50]
			  	)

		;** init positioning vars
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

		;** search inventory items for those owned by this character 
		;** and find the dimensions and numbers for determining the
		;** window size
		(for 	((= node (self first:)))
				node
				((= node (self next: node)))
			(if ((= obj (NodeValue node)) isKindOf: InvItem)
				(if	(obj ownedBy:whom)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(++ numOwned)
					(if (> (= cWide (CelWide (obj view?)(obj loop?)(obj cel?)))	widestInv)
						(= widestInv cWide)
					)
					(if (> (= cHigh (CelHigh (obj view?)(obj loop?)(obj cel?)))	tallestInv)
						(= tallestInv cHigh)
					)
				else
					(obj signal: (| (obj signal?) DISABLED))
				)
			else	;; some other kind of icon
				(++ numIcons)
				(+= iconBarWidth (CelWide (obj view?)(obj loop?)(obj cel?)))
				(if (> (= cHigh (CelHigh (obj view?)(obj loop?)(obj cel?)))	tallestIcon)
					(= tallestIcon cHigh)
				)
			)
		)
		(if (not numOwned)
			(Print
				addTextF:	{%s %s} normalHeading empty,
				init:
			)
			(return FALSE)
		)
		(= numRows (Sqrt numOwned))
		;; I want the truncated Square root, not the rounded
		(if (> (* numRows numRows)	numOwned)
			(-- numRows)
		)
		;;HOOK
		(if (> numRows 3) (= numRows 3))
		(= numCols (/ numOwned numRows))
		(if (< (* numRows numCols) numOwned)
			(++ numCols)
		)
		(= invW (Max (+ 4 iconBarWidth) (* numCols (+ 4 widestInv))))
		(= invH (* numRows (+ 4 tallestInv)))
		(=	iTop (/ (- 190 invH) 2))
		(=	iLeft (/ (- 320 invW) 2))
		(= iBottom (+ iTop invH))
		(= iRight (+ iLeft invW))
		(if (= invWin (self window?))
			(invWin
				top:		iTop,
				left:		iLeft,
				right:	iRight,
				bottom:	iBottom,
				open:
			)
		)
		(= i numCols)
		;** check to see if there is something to draw
		(if numOwned

			;** compute window position of first item
			(= atY 
				(+ 2
					(if (invWin respondsTo: #yOffset)
						(invWin yOffset:)	
					; else 0
					)
				)
			)
			(= firstX
				(= atX
					(+ 4
						(if (invWin respondsTo: #xOffset)
							(invWin xOffset:)	
						; else 0
						)
					)
				)
			)
			;** draw active items
			(for 	((= node (self first:)))
				node
				((= node (self next: node)))

			;** see if this item is active
				(if (and
						(not (& ((= obj (NodeValue node)) signal?) DISABLED))
						(obj isKindOf: InvItem)
					)

					;** compute window position of variable position items
					(if (not (& (obj signal?) FIXED_POSN))
						(obj
							nsLeft:
								(+ atX 
									(/ 
										(- widestInv 
											(= cWide (CelWide (obj view?)(obj loop?)(obj cel?)))
										) 
										2
									)
								),
							nsTop:		
								(+ atY 
									(/ 
										(- tallestInv 
											(= cHigh (CelHigh (obj view?)(obj loop?)(obj cel?)))
										) 
										2
									)
								)
						)
						(obj
							nsRight: (+ (obj nsLeft?) cWide),
							nsBottom: (+ (obj nsTop?) cHigh)
						)
						;** see if a line of items is complete
						(if (-- i)
							(+= atX widestInv)
	  					else
							;** compute window position of next line
							(= i numCols)
							(+= atY tallestInv)
							(= atX firstX)
						)
					else
						;** set position of fixed position item
			 			(= atX	(obj nsLeft?))
			 			(= atY	(obj nsTop?))
					)

					;** draw the item	in the window
					(obj 	show:)
					;** see if item is the current selection and the window was
					;** invoked from the keyboard
					(if (== obj selection)
						;** draw selector box around item and set current node to item
						(obj highlight:)
					)
				)
			)
		)
		(= atX	(/ (- (- (invWin right?) (invWin left?)) iconBarWidth) 2))
		(= invH (- (invWin bottom?) (invWin top?)))
		(= atY $7fff)
		(for 	((= node (self first:)))
				node
				((= node (self next: node)))
			(if (not ((= obj (NodeValue node)) isKindOf: InvItem))
				(obj nsTop: 0)
				(= cWide (CelWide (obj view?)(obj loop?)(obj cel?)))
				(= cHigh (CelHigh (obj view?)(obj loop?)(obj cel?)))
				(if (not (& (obj signal?) FIXED_POSN))
					(if (== atY $7fff)
						(= atY (- invH cHigh))
					) 
					(obj
						nsLeft:		atX,
						nsTop:		atY,
						nsBottom:	invH ,
						nsRight:		(+ atX cWide) 
					)
				)
				(= atX (+ (obj nsLeft?) cWide))
				(= atY (obj nsTop?))
				(obj
					signal:	(& (obj signal?) (~ DISABLED)),
					show:
				)
			)
		)
		(return TRUE)
	)


	(method (init)
		(= heading normalHeading)
	)


	(method (ownedBy whom)
		
		;** Return the first item in inventory which is owned by `whom'
		(return (self firstTrue: #ownedBy: whom))
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
			(window dispose:)
			(= window 0)
		)
		(if (not okButton)
			(= okButton (NodeValue (self first?)))
		)
		(= curIcon NULL)
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
		(|= state IB_ACTIVE)
		(if (not (= diw (self drawInvWindow:	(if argc who else ego) (theIconBar curIcon?))))
			(&= state (~ IB_ACTIVE))
		)
		(PicNotValid pnv)
		(return diw)
	)

	(method (advance amount &tmp theIcon toMove highlightedNo nextIcon)
		(= toMove (if argc amount else 1))
		(= highlightedNo (self indexOf: highlightedIcon))
		(= nextIcon (+ toMove highlightedNo))
		(repeat
			(= theIcon
		 		(self at:
					(if (<= nextIcon size)
						nextIcon
					else
						(mod nextIcon (- size 1))
					)
				)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(if (not (& (theIcon signal?) DISABLED))
				(break)
			else
				(++ nextIcon)
			)
		)
		(self highlight:theIcon TRUE)
	)

	(method (retreat amount &tmp theIcon toMove highlightedNo nextIcon)
		(= toMove (if argc amount else 1))
		(= highlightedNo (self indexOf: highlightedIcon))
		(= nextIcon (- highlightedNo toMove))
		(repeat
			(= theIcon
		 		(self at: nextIcon)
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(if (not (& (theIcon signal?) DISABLED))
				(break)
			else
				(-- nextIcon)
			)
		)
		(self highlight:theIcon TRUE)
	)
	
	(procedure (FindIcon hIcon y1 y2 &tmp thisIcon theY theX)
		(= theX (+ (/ (- (hIcon nsRight?) (hIcon nsLeft?))	2)	(hIcon nsLeft?)))
		(for 	((= theY y1))
				(>= (Abs (- theY y2)) 4)
				((if (< y1 y2) (+= theY 4) else (-= theY 4)))
			(if (= thisIcon
					(self firstTrue: #onMe:
						(((user curEvent?) new:)
							x:			theX, 
							y:			theY, 
							yourself:
						)
					)
				)
				(return thisIcon)
			)
		)
	)

	(method (doit &tmp thisIcon event eType eMsg eMod node newIcon eO
							oldPort keyInvoked [buffer 50] tut
		)
		;; eat all events in queue
		(while ((= event ((user curEvent?) new:)) type?))

		(while (& state IB_ACTIVE)
			(= event ((user curEvent?) new:))
			(= mouseX 	(event x?))
			(= mouseY 	(event y?))
			(= eType 	(event type?))
			(= eMsg 		(event message?))
			(= eMod 		(event modifiers?))
			(= keyInvoked FALSE)
			(event localize:)

			; if curIcon use it as an event conditioner
			(if (and
					curIcon
					(not eMod)
					(!= curIcon selectIcon)
					(or 
						(== eType mouseDown) 
						(and
							(== eType keyDown) 
							(== eMsg ENTER)
							(= keyInvoked TRUE)
						)
						(and
							(== eType joyDown)
							(= keyInvoked TRUE)
						)
					)
				)
				(if (or 
						(!= curIcon	helpIconItem)
						(& (helpIconItem signal?) TRANSLATOR)
					)
					(event
						type: 	userEvent,		;BKH Should this be helpEvent?
						message: (curIcon message?)
					)
				)
			)

			(MapKeyToDir event)

			; In case of a direction event, refresh the temps
			(= eType (event type?))
			(= eMsg 	(event message?))

			; So we can get cues from Messager & such
			(if cuees
				(cuees eachElementDo: #doit)
			)

			; If there's a tutorial running, give it a doit
			(if (and	(= tut (theGame script?))
						(tut isKindOf: Tutorial)
				)
				(tut doit:)
			)

			(cond
				(fastCast
					(fastCast handleEvent: event)
				)

				((and (== eType mouseDown) eMod)
				  	(self advanceCurIcon:)
					(event claimed: TRUE)
				)

				;; highlight the appropriate icon
				((and 
						(== eType nullEvt)	
						(= thisIcon (self firstTrue: #onMe event))
						(!= thisIcon highlightedIcon)
					)
					(self highlight: thisIcon)
				)

				;; selection was made
				((or 
						(== eType mouseDown)
						(and
							(== eType keyDown)
							(== eMsg ENTER)
						)
						(== eType joyDown)

					)
					(cond
						((and 
								(IsObject highlightedIcon)
								(self select: highlightedIcon (== eType mouseDown))
							)
							(cond
								((== highlightedIcon okButton)
									(event claimed: TRUE)
									(break)
								)
								((== highlightedIcon helpIconItem)
									(if (!= (highlightedIcon cursor?) -1)
										(theGame setCursor: (helpIconItem cursor?))
									)
									(cond
										((& state NOCLICKHELP)
											(self noClickHelp:)
										)
										(helpIconItem
											(helpIconItem signal:(| (helpIconItem signal?) TRANSLATOR))
										)
									)
								)
								(else
									(= curIcon highlightedIcon)
									(theGame setCursor: (curIcon cursor?))
								)
							)
						)
					)
				)

				;; advance or retreat from dir keys or joyStick?
				((& eType direction)
					(switch eMsg
						(dirE
							(self advance:)
						)
						(dirW
							(self retreat:)
						)
						(dirN
							(if (and 
									highlightedIcon 
									(= thisIcon 
										(FindIcon highlightedIcon (- (highlightedIcon nsTop?) 1) 0)
									)
								)
								(self highlight: thisIcon TRUE)
							else
								(self retreat:)
							)
						)
						(dirS
							(if (and 
									highlightedIcon 
									(= thisIcon 
										(FindIcon highlightedIcon (+ (highlightedIcon nsBottom?) 1) (window bottom?))
									)
								)
								(self highlight: thisIcon TRUE)
							else
								(self advance:)
							)
						)
						(dirStop
							(if (& eType keyDown)
								(self advanceCurIcon:)
							)
						)
					)
				)

				((== eType keyDown)
					(switch eMsg
						(TAB
							(self advance:)
						)
						(SHIFTTAB
							(self retreat:)
						)
						(ESC
							(break)
						)
					)
				)

				((& eType userEvent)
					(if (= thisIcon (self firstTrue: #onMe event))
						(cond
							((& eType helpEvent)
								(if (and thisIcon (thisIcon noun?))
									(if (Message MsgGet (thisIcon modNum?) (thisIcon noun?) (thisIcon helpVerb?) NULL 1 @buffer)
										(if (systemWindow respondsTo: #eraseOnly)
											(= eO (systemWindow eraseOnly?))
											(systemWindow eraseOnly: TRUE)
											(Prints @buffer)
											(systemWindow eraseOnly: eO)
										else
											(Prints @buffer)
										)
									)
								)
;								(if helpIconItem
									(helpIconItem signal: (& (helpIconItem signal?)	(~ TRANSLATOR)))
;								)
								(theGame setCursor: ARROW_CURSOR)
							)
							((== thisIcon okButton)
								(event claimed: TRUE)
								(break)
							)
							((not (thisIcon isKindOf: InvItem))
								(if (self select: thisIcon (not keyInvoked))
									(= curIcon thisIcon)
									(theGame setCursor: (curIcon cursor?))
									(if (== thisIcon helpIconItem)
										(if (& state NOCLICKHELP)
											(self noClickHelp:)
										else
											(helpIconItem signal:(| (helpIconItem signal?) TRANSLATOR))
										)
									)
								)
							)
							(curIcon
							 	(if (systemWindow respondsTo: #eraseOnly)
									(= eO (systemWindow eraseOnly?))
									(systemWindow eraseOnly: TRUE)
								)	
								(if (curIcon isKindOf: InvItem)
									(thisIcon doVerb: (curIcon message?))
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
		)
		(self hide:)
	)
	

	(method (hide &tmp theCurs)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(&= state (~ IB_ACTIVE))
		)
		(if window	 
		  	(window dispose:)
		)
		(if (and (IsObject curIcon)
					(curIcon isKindOf: InvItem)
				)
			;; if we had no curInvIcon, enable the use since we now have one
			(if (not (theIconBar curInvIcon?))
				(theIconBar enable: (theIconBar useIconItem?))
			)
			;; set the useIcon window to the current inv item
			(theIconBar
				curIcon:
					((theIconBar useIconItem?) 
						cursor: (curIcon cursor?)
						yourself:
					),
				curInvIcon: curIcon
			)
			(if (= theCurs ((theIconBar curIcon?) cursor?))
				(theGame	setCursor: theCurs)
			)
		)
	)
)