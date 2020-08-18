;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INVENT.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Unknown / SCI-32 Revision - Robert W. Lindsley
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The Inventory is a collection of items that the ego can carry.
;;;;
;;;;	Classes:
;;;;		InvItem
;;;;		Inventory

;EO: All instances of KList have been replaced with "List" to allow the script to compile.
;Restore the original name when SCICompanion recognizes SCI32 kernel calls.

(script# INVENT)
(include game.sh)
(use Main)
(use String)
(use Print)
(use IconBar)
(use Tutorial)


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

	(method (init)
		;
		; Overridden to set a default cursor if one isn't set yet

		(super init: &rest)
		(if (== cursorView -1)
			(self setCursor: mainView mainLoop mainCel)
		)
	)

	(method (onMe event)
		(return
			(and
				(not (& signal DISABLED))
				(super onMe: event)
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

	(method (highlight tOrF)
		;
		; Highlight ourselves to show we are the current icon

		(= cel (* 2 (and argc tOrF)))
		(UpdateScreenItem self)
	)

	(method (ownedBy who)
		;
		; Return TRUE if this item owned by 'who'

		(return (== owner who))
	)		  

	(method (moveTo who)
		;
		; Set item's owner to 'who'

		(= owner who)
		(if (and
				value
				(== who ego)
			)
			(theGame changeScore: value)
			(= value 0)
		)
		(return self)
	)

	(method (getCursor)
		;
		; Return the cursor appropriate to this item, e.g. the cursor object
		;	or the invCursor

		(return
			(if (== cursorLoop -1)
				cursorView
			else
				(invCursor
					initialize: self,
					yourself:
				)
			)
		)
	)
)


(class Inventory kindof IconBar
	;; This is the set of all inventory items in the game.

	(properties
		name 					"Inv"
		normalHeading		"You are carrying:"
		heading				0
		empty 				"nothing!"
		highlightedIcon 	0
		curIcon				0
		plane					0
		okButton				NULL
		selectIcon			NULL
		owner					NULL		; current owner for searches

		iconBottom			NULL		; The bottom of the icons (so we can posn items)
		iconRight			NULL		; The right of the icons

		planeTop				NULL		; These allow you to fix your
		planeBottom			NULL		; window size.  We do not
		planeLeft			NULL		; handle inventory items going off
		planeRight			NULL		; the edges.  Instead make it pageable.

		curIndex				0			; index in scrolling inventory
		numRow				0			; number of rows of items
		numCol				0			; number of columns of items
		rowMargin			0			; space between rows
		colMargin			0			; space between rows
		itemWide				0			;   
		itemHigh				0			;
		numIcon				0			; number of non-inventory icons
	)

;;;	(methods
;;;		showSelf			;; Display inventory owned by an object
;;;		ownedBy			;; Return InvItem owned by an object
;;;		highlight
;;;		select
;;;		advance
;;;		retreat
;;;		drawIcons
;;;		drawInvItems
;;;		carryingNothing
;;;		setCurIndex
;;;		setCurIndexTo
;;;	)

	(method (init &tmp node obj)
		(super init:)
		(for 	((= node (self first:)))
				node
				((= node (self next: node)))
			(if ((= obj (List LNodeValue node)) isKindOf: InvItem) ;EO: Was "KList"
				(DeleteScreenItem obj)
				(obj signal: (| (obj signal?) DISABLED))
			 else
			 	(++ numIcon)
			)
		)
		(self drawIcons:)
	)

	(method (drawIcons &tmp obj atX node cHigh cWide)
		;
		; Draw the icon items

		(= atX (= node (= obj (= cHigh (= cWide 0)))))

		; Go through the list and sort out the IconItems & set their posn
		(for 	((= node (self first:)))
				node
				((= node (self next: node)))

			; If the item is an IconItem, position it
			(if (not ((= obj (List LNodeValue node)) isKindOf: InvItem)) ;EO: Was "KList"
 				(= cWide (CelWide (obj view?) (obj loop?) (obj cel?)))
				(+= iconRight cWide)											;we need to get the final right of the icons
				(if (> (= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?))) iconBottom)
					(= iconBottom cHigh)
				)

				; If you want to position it, go ahead.  Otherwise, it's at the top.
				(if (not (& (obj signal?) FIXED_POSN))
					(obj
						x:				atX,
						y:				0,
						nsLeft:		atX,
						nsTop:		0,
						nsRight:		(+ atX cWide),
						nsBottom:	cHigh
					)
				)
				(= atX (+ (obj x?) cWide))
				(UpdateScreenItem obj)
			)
		)

		; RECOMPUTE THE SIZE OF SCROLLABLE INVENTORY
		(if numCol
			(= iconRight (* (+ itemWide colMargin) numCol))
		)
	)

	(method (drawInvItems &tmp atX atY obj node itemBottom cHigh cWide numIcons lastObj si curRow)
		;
		; Draw the inventory items & resize the plane
		(= itemBottom 0)
		(= atX 10)
		(= atY (+ iconBottom 15))

		(= curRow 0)

		; SKIP THE UPPER-INDEXING INVENTORY ITEMS
		(for ((= node (self first:)) (= si 0)) (< si curIndex) ( (++ si) (= node (self next: node)))
			(if (and ((= obj (List LNodeValue node)) isKindOf: InvItem) ;EO: Was "KList"
						(not (& (obj signal?) DISABLED))
				)
				(DeleteScreenItem obj)
				(obj signal: (| (obj signal?) DISABLED))
			)
		)

		(for 	((= node node)) node ((= node (self next: node)))
			(if (and
					((= obj (List LNodeValue node)) isKindOf: InvItem) ;EO: Was "KList"
					(== (obj owner?) owner)
				 )

		  		; Figure out tallest invitem for positioning next row
				(if (> (= cHigh (CelHigh (obj view?) (obj loop?) (obj cel?))) itemBottom)
					(= itemBottom cHigh)
				)

				(obj
					x:				atX,
					y:				atY,
					nsTop: 		atY,
					nsLeft:		atX,
					nsRight:		(+ atX (CelWide (obj view?) (obj loop?) (obj cel?))),
					nsBottom: 	(+ atY cHigh)
				)
				(if (& (obj signal?) DISABLED)
					(obj signal: (& (obj signal?) (~ DISABLED)))
					(AddScreenItem obj)
			 	else
					(UpdateScreenItem obj)
				)

				; POST-INCREMENT atX AND CHECK BOUNDARY
				(if 
					(>
						(+= atX (+ (CelWide (obj view?) (obj loop?) (obj cel?)) colMargin))
						iconRight
					)
					(= atX 10)
					(= atY (+ atY itemHigh rowMargin))
					(if (>= (++ curRow) numRow)
						(break)
					)
				)
			)
		)

		; DISABLE THE REST OF INVENTORY
		(if node
			(for 	((= node (self next: node))) node ((= node (self next: node)))
				(if (and 
				 		((= obj (List LNodeValue node)) isKindOf: InvItem) ;EO: Was "KList"
				 		(not (& (obj signal?) DISABLED))
				 	)
					(DeleteScreenItem obj)
					(obj signal: (| (obj signal?) DISABLED))
				)
			)
		)

		; We now have all the items in the plane, so let's resize it.
		(if numRow
			(plane
				setRect:		50 50 (+ 50 iconRight) (* (+ itemWide colMargin) numCol) TRUE
			)
		 else
			(plane 
				setRect:		50 50 (+ 50 iconRight) (+ 10 (+ atY iconBottom 5)) TRUE
			)
		)
	)

	(method (ownedBy whom &tmp node obj)
		;
		; Return the first item in inventory which is owned by `whom'

		(for	((= node (List LFirstNode elements))) ;EO: Was "KList"
				node
				((= node nextNode))
			(= nextNode (List LNextNode node))	;EO: Was "KList"
			(= obj (List LNodeValue node))	;EO: Was "KList"
			(if (and	(obj isKindOf: InvItem)
						(obj ownedBy: whom)
				)
				(return TRUE)
			)
		)
		(return FALSE)
	)

	(method (showSelf who)
		;
		; Entry point for inventory.  Show the plane, arrange the items
		;	within it, and process it.

		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(if (not okButton)
			(= okButton (List LNodeValue (self first?))) ;EO: Was "KList"
		)
		(= curIcon NULL)

		(= owner (if argc who else ego))

		; Check to see if there are no owned items
		(if (not (self ownedBy: owner))
			(self carryingNothing:)
			(return FALSE)
		)

		; Othewise, show us, process us, and put us away again
		(self
			show:,
			doit:,
			hide:
		)
	)

	(method (show)
		;
		;	Show the plane and the items

		(|= state IB_ACTIVE)
		(theGame setCursor: (self getCursor:))
		(plane priority: (+ (GetHighPlanePri) 1))
		(UpdatePlane plane)

		(self drawInvItems:)

		(return TRUE)
	)

	(method (hide &tmp theCurs node obj)
		;
		; Put the inventory away

		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(&= state (~ IB_ACTIVE))
		)

		; Tell all icons that they are no longer active
		(for 	((= node (List LFirstNode elements))) ;EO: Was "KList"
				node
				((= node nextNode))
			(= nextNode (List LNextNode node)) ;EO: Was "KList"
			(= obj (List LNodeValue node)) ;EO: Was "KList"
			(obj signal: (& (obj signal?) (~ IB_ACTIVE)))
			(if (and	(obj isKindOf: InvItem)
						(not (& (obj signal?) DISABLED))
				)
				(obj signal: (| (obj signal?) DISABLED))
				(DeleteScreenItem obj)
			)
		)

		;
		; We used to dispose the plane, but now we just put it in the background
		; by setting its' priority to -1.  We do this at the top so that we can
		; reset all the items without the user seeing it.

		(plane priority: -1)

		(UpdatePlane plane)

		;; Now we sort through the list and get rid of all the inv items.
		;; This is so we can reposition them the next time we come into
		;; inventory.

		(if (and curIcon
					(curIcon isKindOf: InvItem)
				)

			;; If we had no curInvIcon, enable the use since we now have one
			(if (not (theIconBar curInvIcon?))
				(theIconBar enableIcon: (theIconBar useIconItem?))
			)

			;; Set the use icon to the current inv item
			(theIconBar
				curIcon:
					((theIconBar useIconItem?) 
						cursorView:		(invCursor view?),
						cursorLoop:		(invCursor loop?),
						cursorCel:		(invCursor cel?)
						yourself:
					),
				curInvIcon: curIcon
			)
		)
	)

	(method (advance amount &tmp theIcon toMove highlightedNo nextIcon)
		;
		; Move the highlight to the next icon

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
			(if (not theIcon)
				(= theIcon (List LNodeValue (self first:))) ;EO: Was "KList"
			)
			(if (not (& (theIcon signal?) DISABLED))
				(break)
			else
				(++ nextIcon)
			)
		)
		(self highlight: theIcon TRUE)
	)

	(method (retreat amount &tmp theIcon toMove highlightedNo nextIcon)
		;
		; Move the highlight to the previous icon

		(= toMove (if argc amount else 1))
		(= highlightedNo (self indexOf: highlightedIcon))
		(= nextIcon (- highlightedNo toMove))
		(repeat
			(= theIcon
		 		(self at: nextIcon)
			)
			(if (not theIcon)
				(= theIcon (List LNodeValue (self last:))) ;EO: Was "KList"
			)
			(if (not (& (theIcon signal?) DISABLED))
				(break)
			else
				(-- nextIcon)
			)
		)
		(self highlight: theIcon TRUE)
	)
	
	(procedure (FindIcon hIcon y1 y2 &tmp thisIcon theY theX)
		;
		; Find the icon next to us visually

		(= theX (+ (/ (- (hIcon nsRight?) (hIcon x?))	2)	(hIcon x?)))
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
							oldPort keyInvoked buffer tut
		)
		(= buffer (String newWith: 100 {}))
		;; Eat all events in queue
		(while ((= event ((user curEvent?) new:)) type?))

		(while (& state IB_ACTIVE)
			(= event ((user curEvent?) new:))
			(= mouseX 	(event x?))
			(= mouseY 	(event y?))
			(= eType 	(event type?))
			(= eMsg 		(event message?))
			(= eMod 		(event modifiers?))
			(= keyInvoked FALSE)

			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)
			(event localize: plane)

			(if (narrator initialized?) ;the gabriel knight fix
				(narrator doit:)
				(narrator handleEvent: event)
				(continue)
			)

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
						type: 	userEvent,
						message: (curIcon message?)
					)
				)
			)

			(MapKeyToDir event)

			;; In case of a direction event, refresh the temps
			(= eType (event type?))
			(= eMsg 	(event message?))

			;; So we can get cues from Messager & such
			(if cuees
				(cuees eachElementDo: #doit)
			)

			;; If there's a tutorial running, give it a doit
			(if (and	(= tut (theGame script?))
						(tut isKindOf: Tutorial)
				)
				(tut doit:)
			)

			(cond
				((and (== eType mouseDown) eMod)
				  	(self advanceCurIcon:)
					(event claimed: TRUE)
				)

				; Highlight the appropriate icon
				((and 
						(== eType nullEvt)	
						(= thisIcon (self firstTrue: #onMe event))
						(!= thisIcon highlightedIcon)
					)
					(self highlight: thisIcon)
				)

				;; Selection was made
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
								highlightedIcon
								(self select: highlightedIcon (== eType mouseDown))
							)
							(cond
								((== highlightedIcon okButton)
									(event claimed: TRUE)
									(break)
								)
								((== highlightedIcon helpIconItem)
									(= curIcon highlightedIcon) 
									(theGame setCursor: (self getCursor:))
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
									(theGame setCursor: (self getCursor:))
								)
							)
						)
					)
				)

				;; Advance or retreat from dir keys or joyStick?
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
										(FindIcon highlightedIcon (+ (highlightedIcon nsBottom?) 1) (plane bottom?))
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
									(if (Message MsgGet
											(thisIcon modNum?)
											(thisIcon noun?)
											(thisIcon helpVerb?)
											NULL
											1
											(buffer data?)
										)
		 								(Prints buffer)
									)
								)
						 		(helpIconItem signal: (& (helpIconItem signal?)	(~ TRANSLATOR)))
								(theGame setCursor: normalCursor)
							)
							((== thisIcon okButton)
								(event claimed: TRUE)
								(break)
							)
							((not (thisIcon isKindOf: InvItem))
								(if (self select: thisIcon (not keyInvoked))
									(= curIcon thisIcon)
									(theGame setCursor: (self getCursor:))
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
								(if (curIcon isKindOf: InvItem)
									(thisIcon doVerb: (curIcon message?))
								else
									(thisIcon doVerb: (event message?))
								)
							)
						)
					)
				)
			)
		)
		(buffer dispose:)
	)
	
	(method (carryingNothing)
		;
		; Called when nothing in inventory belongs to the specified person.
		;	Nearly always overridden by game's inventory instance.

		(Print
			addTextF:	{%s %s} normalHeading empty,
			init:
		)
		(return FALSE)
	)

	(method (setCurIndex amount &tmp index theDir numOwned obj idx)
		;
		; Modify the curIndex by 'amount'.  Disabled items will not be counted.

		(= theDir (if (< amount 0) -1 else 1))

		; See if we have 'amount' items left in the list
		(= numOwned 0)
		(for	((= idx curIndex) (= numOwned 0))
				(and	(< numOwned (Abs amount))
						(< idx size)
						(>= idx 0)
				)
				()
			(+= idx theDir)
			(if (and	(<= 0 idx (- size 1))
;						(not (& ((= obj (self at: idx)) signal?) DISABLED))
						((= obj (self at: idx)) isKindOf: InvItem)
						(obj ownedBy: owner)
				)
				(++ numOwned)
			)
		)

		; If there's not enough items left in the list that match our
		;	qualifications, we can't scroll
		(if (< numOwned (Abs amount))
			(return)
		else
			(= curIndex idx)
		)

;		; Find the first owned node for bounds checking
;		(for	((= firstOwned 0))
;				(and	((= obj (self at: firstOwned)) isKindOf: InvItem)
;						(& (obj signal?) DISABLED)
;				)
;				((++ firstOwned))
;		)
;
;		; Find the last owned node for bounds checking
;		(for	((= lastOwned (- size 1)))
;				(and	((= obj (self at: lastOwned)) isKindOf: InvItem)
;						(& (obj signal?) DISABLED)
;				)
;				((-- lastOwned))
;		)

;		; FIND OUT NUMBER OF ACTIVE ITEMS
;		(for ( (= n 0) (= node (self first:)))	node ((= node (self next: node)))
;			(if (and 
;					((= obj (KList LNodeValue node)) isKindOf: InvItem)
;					(not (& (obj signal?) DISABLED))
;				 )
;				(++ n)
;			)
;		)
;
;		; NO PROCESS IF OUT OF BOUND
;		(if (or 
;				(< (+ curIndex amount) 0)
;				(and 
;					(== theDir 1)
;					(< (- n amount) 1)	 	; REQUIRES AT LEAST ONE ITEM 
;				)
;			 )
;			(return 0)
;		)

;		(= idx curIndex)
;		(for 	((= index 0))
;				(< index (Abs amount))
;				((++ index))
;
;			(+= curIndex theDir)
;			(while (and	(< curIndex (- size 1))
;							(not ((self at: curIndex) ownedBy: owner))
;					)
;				(+= curIndex theDir)
;			)
;		)

		(self drawInvItems:)
	)

	(method (setCurIndexTo index)
		;
		; Set the curIndex to 'index'.  curIndex will further be incremented
		;	until an enabled item is encountered.

		(= curIndex index)
		(while (not ((self at: curIndex) ownedBy: owner))
			(++ curIndex)
		)

		(self drawInvItems:)
	)
)


(instance invCursor of IconBarCursor)
