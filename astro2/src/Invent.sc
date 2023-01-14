;;; Sierra Script 1.0 - (do not remove this comment)
(script# INVENT)
(include game.sh)
(use Main)
(use Intrface)
(use Window)
(use IconBar)
(use System)

(local 
	numCols 
)
(include menu.sh)
;;;(procedure 
;;;	DrawInvWindow
;;;)

(class InvItem kindof IconItem
	;;; An InvItem is something which can be owned by an object in an
	;;; adventure game.

	(properties
		-info- 		$8004		;(| CLASSBIT NODISPLAY)
		name 			"InvI"	;my literal name
		description 0			;long text description
		owner 		0			;who owns this item
		view 			0			;picture of the item
		loop 			0
		cel 			0
		nsLeft		0
		nsTop			0
		nsRight		0
		nsBottom		0
		state			0
		message		verbUse
		cursor		ARROW_CURSOR ;cursor # (mouse cursor representation of item)
		script 		0			;a script that can control the item
		signal		0
		value			0
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
				(super onMe:event)
				(not (& signal DISABLED))
			)
		)
	)

	(method (doVerb theVerb inv)
		(switch theVerb
			(verbLook
				(Printf INVENT 0 description)
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
				(= id ego)
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
		normalHeading		"Roger is carrying:"
		heading				0
		empty 				"nothing!"
		curScore 			"Score: %d out of %d"
		showScore			TRUE
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
;;;	)

	(procedure (DrawInvWindow whom selection 
						&tmp 
						numOwned tallestInv 	widestInv 
						numIcons tallestIcon iconBarWidth
						cWide	cHigh	node obj	invW invH 
						iTop iLeft iBottom iRight
						numRows atX atY firstX i
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
		(for 	((= node (inventory first:)))
				node
				((= node (inventory next: node)))
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
			(Printf INVENT 1 normalHeading empty)
			(return)
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
		(if (inventory window?)
			((inventory	window?)
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
					(if ((inventory window?) respondsTo: #yOffset)
						((inventory window?) yOffset:)	
					; else 0
					)
				)
			)
			(= firstX
				(= atX
					(+ 4
						(if ((inventory window?) respondsTo: #xOffset)
							((inventory window?) xOffset:)	
						; else 0
						)
					)
				)
			)
			;** draw active items
			(for 	((= node (inventory first:)))
				node
				((= node (inventory next: node)))

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
						(theGame setCursor: 5 TRUE)
						(obj highlight:)
					)
				)
			)
		)
		(= atX	(/ (- (- ((inventory window?) right?) ((inventory window?) left?)) iconBarWidth) 2))
		(= invH (- ((inventory window?) bottom?) ((inventory window?) top?)))
		(= atY $7fff)
		(for 	((= node (inventory first:)))
				node
				((= node (inventory next: node)))
			(if (not ((= obj (NodeValue node)) isKindOf: InvItem))
				(= cWide (CelWide (obj view?)(obj loop?)(obj cel?)))
				(= cHigh (CelHigh (obj view?)(obj loop?)(obj cel?)))
				(if (not (& (obj signal?) FIXED_POSN))
					(if (== atY $7fff)
						(= atY (- invH cHigh))
					) 
					(obj
						nsLeft:atX,
						nsTop:atY,
						nsBottom:invH ,
						nsRight:(+ atX cWide)
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
	)
	(method (init)

		;set global var to the Inventory	set
		(= inventory self)
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
		(theGame setCursor: (selectIcon cursor?))
		(self 
			show:(if argc who else ego),
			doit:
		)
	)

	(method (show who &tmp pnv)
		(= pnv (PicNotValid))
		(PicNotValid FALSE)
		(|= state IB_ACTIVE)
		(DrawInvWindow	(if argc who else ego) (theIconBar curIcon?))
		(PicNotValid pnv)
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
	(procedure (FindIcon hIcon y1 y2 &tmp thisIcon onMeEvent theY theX)
		(= theX (+ (/ (- (hIcon nsRight?) (hIcon nsLeft?))	2)	(hIcon nsLeft?)))
		(for ((= theY y1)) (>= (Abs (- theY y2)) 4) ((if (< y1 y2) (+= theY 4) else (-= theY 4)))
			(if (= thisIcon 
					(self firstTrue:#onMe:
						((= onMeEvent (Event new:)) 
							x:	theX, 
							y:	theY
							yourself:
						)
					)
				)
				(onMeEvent dispose:)
				(return thisIcon)
			)
			(onMeEvent dispose:)
		)
	)
	(method (doit 
		&tmp thisIcon event evtType evtMsg node newIcon eO oldPort keyInvoked
		)
		;; eat all events in queue
		(while ((= event (Event new:)) type?)
			(event dispose:)
		)
		(event dispose:)
		(= event 0)
		(while (& state IB_ACTIVE)
			(invEvent
				type: 		0,			
				message: 	0,		
				modifiers: 	0,		
				y: 			0,				
				x: 			0,				
				claimed: 	0,		
				port:			0
			)
			(GetEvent allEvents invEvent)
			(= mouseX (invEvent x?))
			(= mouseY (invEvent y?))
			(= keyInvoked FALSE)
			(invEvent localize:)
			; if curIcon use it as an event conditioner
			(if (and
					curIcon
					(not (invEvent modifiers?))
					(!= curIcon selectIcon)
					(or 
						(== (invEvent type?) mouseDown) 
						(and
							(== (invEvent type?) keyDown) 
							(== (invEvent message?) ENTER)
							(= keyInvoked TRUE)
						)
						(and
							(== (invEvent type?) joyDown)
							(= keyInvoked TRUE)
						)
					)
				)
				(if (or 
						(!= curIcon	helpIconItem)
						(& (helpIconItem signal?) TRANSLATOR)
					)
					(invEvent
						type: userEvent,
						message: (curIcon message?)
					)
				)
			)
			(MapKeyToDir invEvent)
			(= evtType (invEvent type?))
			(cond 
				((and (== evtType mouseDown) (invEvent modifiers?))
				  	(self advanceCurIcon:)
					(invEvent claimed:TRUE)
				)
				;; highlight the appropriate icon
				((and 
						(== evtType nullEvt)	
						(= thisIcon (self firstTrue:#onMe invEvent))
						(!= thisIcon highlightedIcon)
					)
					(self highlight: thisIcon)
				)
				;; selection was made
				((or 
						(== evtType mouseDown)
						(and
							(== evtType keyDown)
							(== (invEvent message?) ENTER)
						)
						(== evtType joyDown)

					)
					(cond
						((and 
								(IsObject highlightedIcon)
								(self select: highlightedIcon (== evtType mouseDown))
							)
							(cond
								((== highlightedIcon okButton)
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
									(theGame setCursor: (curIcon cursor?) TRUE)
								)
							)
						)
					)
				)
				;; advance or retreat from dir keys or joyStick?
				((& evtType direction)
					(switch (invEvent message?)
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
							(if (& evtType keyDown)
								(self advanceCurIcon:)
							)
						)
					)
				)
				((== evtType keyDown)
					(switch (invEvent message?)
						(TAB
							(self advance:)
						)
						(SHIFTTAB
							(self retreat:)
						)
					)
				)
				((== evtType userEvent)
					(if (= thisIcon (self firstTrue:#onMe invEvent))
						(cond
							((== (invEvent message?) verbHelp)
								(if (and thisIcon (thisIcon helpStr?))
									(if (systemWindow respondsTo: #eraseOnly)
										(= eO (systemWindow eraseOnly?))
										(systemWindow eraseOnly: TRUE)
										(Printf INVENT 0 (thisIcon helpStr?))
										(systemWindow eraseOnly: eO)
									else
										(Printf INVENT 0 (thisIcon helpStr?))
									)
								)
;								(if helpIconItem
									(helpIconItem signal:(& (helpIconItem signal?)	(~ TRANSLATOR)))
;								)
								(theGame setCursor: ARROW_CURSOR)
							)
							((== thisIcon okButton)
								(break)
							)
							((not (thisIcon isKindOf:InvItem))
								(if (self select: thisIcon (not keyInvoked))
									(= curIcon thisIcon)
									(theGame setCursor: (curIcon cursor?) TRUE)
									(if (== thisIcon helpIconItem)
;;;										(if (& state NOCLICKHELP)
;;;											(self noClickHelp:)
;;;										else
											(helpIconItem signal:(| (helpIconItem signal?) TRANSLATOR))
;;;										)
									)
								)
							)
							(curIcon
							 	(if (systemWindow respondsTo: #eraseOnly)
									(= eO (systemWindow eraseOnly?))
									(systemWindow eraseOnly: TRUE)
								)	
								(if (curIcon isKindOf: InvItem)
									(thisIcon doVerb: (curIcon message?) (self indexOf:curIcon))
								else
									(thisIcon doVerb: (invEvent message?))
								)
							 	(if (systemWindow respondsTo: #eraseOnly)
									(systemWindow eraseOnly: eO)
								)
							)
						)
					)
				)
					
			)
			(invEvent dispose:)
		)
		(invEvent dispose:)
		(self hide:)
	)

	(method (hide)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(&= state (~ IB_ACTIVE))
		)
		(if window	 
		  	(window dispose:)
		)
		(if (and (IsObject curIcon) (curIcon isKindOf: InvItem))
			;; if we had no curInvItem, enable the use since we now have one
			(if (not (theIconBar curInvIcon?))
				(theIconBar enable:(theIconBar useIconItem?))
			)
			;; set the useIcon window to the current inv item
			(theIconBar 
				curIcon:
					((theIconBar useIconItem?) 
						cursor:(curIcon cursor?)
						yourself:
					),
;				enable:useIcon,
				curInvIcon:curIcon
			)
			
		)
			(if (curIcon cursor?)
				(theGame setCursor: (curIcon cursor?) TRUE)
			)
	)

	(method (advanceCurIcon &tmp theIcon)
		(= theIcon curIcon)
		(while ((= theIcon (self at:(mod (+ (self indexOf:theIcon) 1) size))) isKindOf: InvItem)	
		)
		(= curIcon theIcon)
		(theGame setCursor:(curIcon cursor?) TRUE)
	)

)
(instance invEvent of Event)
