;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	ICONBAR.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The IconBar is a collection that manages a group of icons.
;;;;
;;;;	Classes:
;;;;		IconItem
;;;;		IconBar


(script# ICONBAR)
(include game.sh)
(use Main)
(use Print)
(use Cursor)
(use Tutorial)
(use Actor)
(use System)


(class IconItem kindof View
	;;; An IconItem is a cel that is drawn on the icon bar
	(properties
		-info- 			$8004			;(| CLASSBIT NODISPLAY)
		name 				"IconI"

		type				userEvent	; event type this icon produces
		message			-1				; which verb this icon produces
		modifiers		0
		signal			RELVERIFY
		mainView			0
		mainLoop			0
		mainCel			0
		maskView			0
		maskLoop			0
		maskCel			0
		cursorView		-1				; view of cursor or handle to Cursor
		cursorLoop		-1				; loop of cursor or -1
		cursorCel		-1				; cel of cursor or -1
		highlightColor	0
		lowlightColor	0
		helpVerb			0				; verb to use for help strings (normally HELP)
		object			0				; object to invoke if selected
		selector			0				; selector of object to invoke if selected
	)

;;; 	(methods
;;;		select
;;;		highlight
;;;		mask
;;;		setCursor
;;;		getCursor
;;;	)

	(method (init)
		;
		; Set the view, loop, and cel to the mainView, -Loop, and -Cel.

		(= view mainView)
		(= loop mainLoop)
		(= cel mainCel)
		(super init: &rest)
	)

	(method (show iX iY &tmp iconNo uISize iconSpace leftEdge topEdge celWide pnv)
		(if (& signal viewHidden)
			(super show:) ; so as not to invalidate normal View hide/show
		else
			(|= signal IB_ACTIVE)
			(if argc
				(self posn: iX iY)
				(SetNowSeen self)
			)

			(if (& signal DISABLED)
				(self mask:)
			else
				(= view mainView)
				(= loop mainLoop)
				(= cel mainCel)
				(UpdateScreenItem self)
			)
		)
	)

	(method (mask)
		(= view maskView)
		(= loop maskLoop)
		(= cel maskCel)
		(UpdateScreenItem self)
	)

	(method (highlight tOrF &tmp t l b r sColor)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		(= cel (* 2 (and argc tOrF)))
		(UpdateScreenItem self)
	)

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
					(while (!= ((= event	(Event new:)) type?)	mouseUp)
						(event localize: plane)
						(if (self onMe: event)
							(if (not whichCel)
								(= cel (= whichCel 1))
								(UpdateScreenItem self)
								(FrameOut)
							)
						else
							(if whichCel
								(= cel (= whichCel 0))
								(UpdateScreenItem self)
								(FrameOut)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(= cel 0)
						(UpdateScreenItem self)
						(FrameOut)
					)
					
					; If there's a tutorial running, take care of it
					(if (and	(= tut (theGame script?))
								(tut isKindOf: Tutorial)
						)
						(cond
							; We are selected and we're not help
							((and	(== (tut nextItem?) self)
									(!= (tut nextAction?) ((theIconBar helpIconItem?) message?))
								)
								(tut cue:)
							)

							; We are not selected
							((not whichCel)
								(return FALSE)
							)

							; Else, we are selected but wrong
							(else
								(tut report:)
								(return FALSE)
							)
						)
					)

					whichCel
				)
				(else
					; If there's a tutorial running, take care of it
					(if (and	(= tut (theGame script?))
								(tut isKindOf: Tutorial)
						)
						(if (and	(== (tut nextItem?) self)
									(!= (tut nextAction?) ((theIconBar helpIconItem?) message?))
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

	(method (setCursor viewOrObj theLoop theCel)
		;
		; Set the cursorView, -Loop, and -Cel properties to the appropriate
		;	values.  If only one argument is passed, it will be an object that
		;	will go into cursorView.  cursorLoop and cursorCel will be -1.

		(if (== argc 1)
			(= cursorView viewOrObj)
			(= cursorLoop -1)
			(= cursorCel -1)
		else
			(= cursorView viewOrObj)
			(= cursorLoop theLoop)
			(= cursorCel theCel)
		)
	)

	(method (getCursor)
		;
		; Return the cursor appropriate to this item, e.g. the cursor object
		;	or the IconBarCursor

		(return
			(if (== cursorLoop -1)
				cursorView
			else
				(IconBarCursor
					initialize: self,
					yourself:
				)
			)
		)
	)
)


(class IconBar kindof Set
	(properties
		underBits			0
		oldMouseX			0
		oldMouseY			0
		curIcon 				0 ; the currently selected icon
		highlightedIcon 	0 ; the icon with the cursor and selector box on it
		prevIcon				0
		curInvIcon			0
		useIconItem			0
		helpIconItem 		0
		walkIconItem		0
		plane					0
		state					OPENIFONME
		y						0
	)
;;;	(methods 
;;;		handleEvent
;;;		doit
;;;		show
;;;		hide
;;;		advance
;;;		retreat
;;;		select
;;;		highlight
;;;		swapCurIcon
;;;		advanceCurIcon
;;;		dispatchEvent
;;;		disable
;;;		disableIcon
;;;		enable
;;;		enableIcon
;;;		noClickHelp
;;;		findIcon
;;;		shouldOpen
;;;		shouldClose
;;;		getCursor
;;;	)

	(method (init &tmp obj)
		;
		; Create the plane if there isn't one already

		(if (not plane)
			(= plane (systemPlane new:))
		)
		(plane
			back:			0,
			priority: 	-1,
			init:			0 0 210 100,
			addCast:		self
		)

		; Init all the items with us as the list
		(self eachElementDo: #init: self)

		; Size the plane to exactly match the items
		(plane setSize:)
	)

	(method (dispose)
		;
		; Get rid of the plane

		(plane
			deleteCast: self,
			dispose:
		)
		(= plane 0)
	)

	(method (findIcon theVerb &tmp i thisIcon)
	 	(for 	((= i 0))
				(< i size)
				((++ i))
	 		(= thisIcon (self at: i))
	 		(if (== (thisIcon message?) theVerb)
				(return thisIcon)
			)
	 	)
		(return NULL)
	)

	(method (noClickHelp &tmp event lastIcon thisIcon dlg)
		(= lastIcon (= thisIcon (= dlg 0)))

		(while (not ((= event ((user curEvent?) new:)) type?))
;**			(if (not (self isMemberOf: IconBar))
			(event localize: plane)
;**			)
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon)
								(thisIcon helpVerb?)
						)
						(= lastIcon thisIcon)
						(if dlg
							(dlg dispose:)
							(= dlg 0)
							(FrameOut)
						)
						(Print
							font:			userFont,
							width:		250,
							addText:		(thisIcon noun?) (thisIcon helpVerb?) 
							            NULL 1 0 0 
											(if (== (thisIcon modNum?) -1) 0 else (thisIcon modNum?)),
							modeless:	DLG_MODELESS,
							init:
						)
						(= dlg (Print dialog?))
						(FrameOut)
					)
				)
				(dlg
					(dlg dispose:)
					(= dlg 0)
					(FrameOut)
				)	
				(else
					(= lastIcon 0)
				)
			)

			(event dispose:)
		)
		(event dispose:)

		(theGame setCursor: normalCursor TRUE)
		(if dlg
			(dlg dispose:)
			(FrameOut)
		)
;**		(if (not (helpIconItem onMe: event))
;**			(self dispatchEvent: event)
;**		)
	)

	(method (handleEvent event &tmp keyInvoked eType newEvent
										newCursor oldCursor oldCurIcon oldInvIcon)

		(if (& state IB_ACTIVE)
			(event localize: plane)
		)
		(= eType (event type?))

		(cond 

			; Don't open if disabled

			((& state DISABLED))

			; Open because of mouse location or ESC key
			((or
					(and 
						(not eType)
						(& state OPENIFONME)
						(self shouldOpen: event)
						(not (= keyInvoked FALSE))
					)
					(and
						(== eType keyDown)
						(or
							(== (event message?) ESC)
							(== (event message?) DELETE)
						)
						(= keyInvoked TRUE)
					)
				)
				; Save mouse location for key invoked icon selection
				(= oldMouseX (event x?))
				(= oldMouseY (event y?))
						
				(= oldCursor theCursor)
				(= oldCurIcon curIcon)
				(= oldInvIcon curInvIcon)

				(self show:)

				(theGame setCursor: normalCursor)

				(if keyInvoked
					(theGame 
						setCursor: 
							theCursor	
							TRUE
							(+ (curIcon nsLeft?) 
								(/ (- (curIcon nsRight?) (curIcon nsLeft?)) 2)
							) 
							(- (curIcon nsBottom?) 3)
					)
				)

				(self doit:)

				(= newCursor
					(if (or	(user canControl:)
								(user canInput:)
						)
						(self getCursor:)
					else
						waitCursor
					)
				)

				; The user selected an icon
				(if keyInvoked
					(theGame setCursor: newCursor TRUE oldMouseX oldMouseY)
				else
					(theGame 
						setCursor: 
							newCursor
							TRUE
						  	((event new:) x?)
							(Max (event y?) (+ 1 (plane bottom?)))
					)
				)
				(self hide:)
			)

			; Handle modeless keyDowns

			((& eType keyDown)
				(switch (event message?)
					(ENTER
						(cond
							; There's no current icon
							((not curIcon))

							; Either the current icon is the useIcon & there's a
							;	curInvIcon, or the current icon is sumpin' else
							((or	(!= curIcon useIconItem)
									curInvIcon
								)
								(event
									type:		(curIcon type?),
					  				message: (if (== curIcon useIconItem)
													(curInvIcon message?)
												else
													(curIcon message?)
												)
								)
							)

							; The current icon is the useIcon but there's no
							;	curInvIcon
							(else
							  	(event type: nullEvt)
							)
						)
					)
					(INSERT
						(if (user canControl:)
							(self swapCurIcon:)
						)
						(event claimed: TRUE)
					)
					(dirStop
						(if (& (event type?) direction)
							(self	advanceCurIcon:)
							(event claimed: TRUE)
						)
					)
				)
			)

			; Handle modeless mouseDowns

			((& eType mouseDown)
				(cond
					; Let user cycle through the cursors for
					;	all non-IMMEDIATE Icons
					((& (event modifiers?) shiftDown)
						(self advanceCurIcon:)
						(event claimed: TRUE)
					)
					; Swap between last non-walk cursor and walk
					((& (event modifiers?) ctrlDown)
						(if (user canControl:)
							(self swapCurIcon:)
						)
						(event claimed: TRUE)
					)
					; Let curIcon condition the Event
					(curIcon
						(event
							type:		(curIcon type?),
					  		message: (if (== curIcon useIconItem)
											(curInvIcon message:)
										else
											(curIcon message?)
										)
						)
					)
				)
			)
		)
	)

	(method (disable theIconNumber &tmp i)
		;
		; Disable icons specified by index (or the whole iconbar).
		; Note!  This method replaces the indeces on the stack with
		;	object IDs of the actual icons, then passes those parameters
		;	to the disableIcon: method.

		(if argc
			(for ((= i 0))	(< i argc) ((++ i))
				(= [theIconNumber i] (self at: [theIconNumber i]))
			)
			(self disableIcon: theIconNumber &rest)
		else
			(self disableIcon:)
		)
	)	

	(method (disableIcon theIcon &tmp i thisIcon)
		;
		; Disable icons specified by objectID (or the whole iconbar)

		(if argc
			; Turn off icons specified
			(for ((= i 0))	(< i argc) ((++ i))
				(= thisIcon [theIcon i])
				(thisIcon signal: (|	(thisIcon signal?) DISABLED))
				(cond
					((== thisIcon curIcon)
						(self advanceCurIcon:)
					)
					((== thisIcon highlightedIcon)
						(self advance:)
					)
				)
			)
		else
			; Turn off the whole bar
			(|= state DISABLED)
		)
	)	

	(method (enable theIconNumber &tmp i)
		;
		; Enable icons specified by index (or the whole iconbar).
		; Note!  This method replaces the indeces on the stack with
		;	object IDs of the actual icons, then passes those parameters
		;	to the enableIcon: method.

		(if argc
			(for ((= i 0))	(< i argc) ((++ i))
				(= [theIconNumber i] (self at: [theIconNumber i]))
			)
			(self enableIcon: theIconNumber &rest)
		else
			(self enableIcon:)
		)
	)

	(method (enableIcon theIcon &tmp i thisIcon)
		;
		; Enable icons specified by objectID (or the whole iconbar)

		(if argc
			; Turn on icons specified
			(for ((= i 0))	(< i argc) ((++ i))
				(= thisIcon [theIcon i])
				(thisIcon signal: (&	(thisIcon signal?) (~ DISABLED)))
			)
		else
			; Turn on the whole bar
			(&= state (~ DISABLED))
		)
	)

	(method (show &tmp theIcon pnv i theX theY node obj)
		(sounds pause:)
		(|= state IB_ACTIVE)
		(theGame setCursor: normalCursor TRUE)

		(plane priority: (+ (GetHighPlanePri) 1))
		(UpdatePlane plane)

		(for	((= theX 0) (= theY y) (= node (List LFirstNode elements)))	;EO: Was "KList"
				node
				((= node nextNode))
			(= nextNode (List LNextNode node))	;EO: Was "KList"		
			(= obj (List LNodeValue node)) ;EO: Was "KList"
			
			; If nsRect not set yet
			(if (<= (obj nsRight?) 0)
				(obj show: theX theY)
				(= theX (obj nsRight?))
			else
				(obj show:)
			)
		)

;		(if curInvIcon
;			(if (ego has: (inventory indexOf: curInvIcon))
;				(= theX
;					(+
;						; add offset
;						(/
;							; half each side
;							(-
;								; difference in widths
;								(- (useIconItem nsRight?) (useIconItem nsLeft?))
;								(CelWide (curInvIcon view?) (curInvIcon loop?) (curInvIcon cel?))
;							)
;							2
;						)
;						(useIconItem nsLeft?)
;					)
;				)
;				(= theY
;					(+
;						y
;						; add offset
;						(/
;							; half each side
;							(-
;								; difference in heights
;								(- (useIconItem nsBottom?) (useIconItem nsTop?))
;								(CelHigh (curInvIcon view?) (curInvIcon loop?) (curInvIcon cel?))
;							)
;							2
;						)
;						(useIconItem nsTop?)
;					)
;				)
;				(curInvIcon posn: theX theY)
;				(AddScreenItem curInvIcon)
;				(if (& (useIconItem signal?) DISABLED)
;					(useIconItem mask:)
;				)
;			else
;				; Clean up for sloppy programmers
;				(= curInvIcon NULL)
;			)
;		)
		; Show current Icon
		(self	highlight: curIcon)
	)

	(method (hide &tmp node obj)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(&= state (~ IB_ACTIVE))

			;; tell all	icons that they are no longer active
			(for 	((= node (List LFirstNode elements))) ;EO: Was "KList"
					node
					((= node nextNode))
				(= nextNode (List LNextNode node)) ;EO: Was "KList"
				(= obj (List LNodeValue node)) ;EO: Was "KList"
				(obj signal: (& (obj signal?) (~ IB_ACTIVE)))
			)

			; Turn off help, if applicable
			(if (and	(not (& state NOCLICKHELP))
						helpIconItem
						(& (helpIconItem signal?) TRANSLATOR)
				)
				(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
			)

			(plane priority: -1)
			(UpdatePlane plane)
		)
	)


	(method (doit &tmp event eType eMsg eMod tut)
		(while (and (& state IB_ACTIVE)
						(= event ((user curEvent?) new:))
					)
			(= eType (event type?))
			(= eMsg	(event message?))
			(= eMod	(event modifiers?))

			(= gameTime (+ tickOffset (GetTime)))
			(FrameOut)

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

			(if (== eType joyDown)
				(= eType keyDown)
				(= eMsg	(if (& eMod shiftDown) ESC else ENTER))
				(= eMod	0)

				(event
					type: 		eType,
					message: 	eMsg,
					modifiers:	eMod
				)
			)
			(event localize: plane)
			(if (and
					(or
						(== eType mouseDown)
						(and
							(== eType keyDown)
							(== eMsg ENTER)
						)
					)
					(and 
						helpIconItem
						(& (helpIconItem signal?) TRANSLATOR)
					)
				)
				(event
					type:		(| userEvent helpEvent),
					message:	(helpIconItem message?)
				)
			)

			(MapKeyToDir event)

			; break if someone claimed the event
			(breakif (self dispatchEvent: event))
		)
	)

	(method (dispatchEvent event
									&tmp evtY evtX evtType evtMsg thisIcon evtClaimed
									theSignal isHelp oldCur
		)
		
		(= evtX 		(event x?))
		(= evtY 		(event y?))
		(= evtType 	(event type?))
		(= evtMsg	(event message?))
		(= evtClaimed (event claimed?))
		(= thisIcon (self firstTrue: #onMe event))
		(if thisIcon
			(= theSignal	(thisIcon signal?))
			(= isHelp		(== thisIcon helpIconItem))
		)
	
		(if (& evtType direction)
			(switch evtMsg
				(dirE
					(self advance:)
				)
				(dirW
					(self retreat:)
				)
			)
		else
			(switch evtType
				(nullEvt
					(cond
						((self shouldClose: event)
							(if
								(and
									(& state OPENIFONME)
									(or 
										(not helpIconItem)
										(not (& (helpIconItem signal?) TRANSLATOR))
									)
								)
								(= oldMouseY 0) ; don't set old cursor posn
								(= evtClaimed TRUE)
							; don't break out if in help mode
							)
						)
						((and thisIcon	(!= thisIcon highlightedIcon))
							(= oldMouseY 0) ; don't set old cursor posn
							; turn on new highlight
							(self highlight: thisIcon)
						)
					)
				)
				(mouseDown
					(if (and thisIcon	(self select: thisIcon TRUE))
						(if isHelp
							(theGame	setCursor: (thisIcon getCursor:) TRUE) 
							(if (& state NOCLICKHELP)
								(self noClickHelp:)
							else
								(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
							)
						else
						 	(= evtClaimed (& theSignal HIDEBAR))
						)
						(if (thisIcon object?)
							(theGame
								panelObj:		(thisIcon object?),
								panelSelector:	(thisIcon selector?)
							)
						)
						(thisIcon doit:)
					)
				)
				(keyDown
					(switch evtMsg
						(ESC
							(= evtClaimed TRUE)
						)
						(DELETE
							(= evtClaimed TRUE)
						)
						(ENTER
							(if (not thisIcon)
								(= thisIcon	highlightedIcon)
							)
							(cond
								((and thisIcon (== thisIcon helpIconItem))
  									(theGame	setCursor: (self getCursor:))
									(if helpIconItem
										(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
									)						
								)
								((and thisIcon
										(self select: thisIcon)
									)
									(if (thisIcon object?)
										(theGame
											panelObj:		(thisIcon object?),
											panelSelector:	(thisIcon selector?)
										)
									)
									(thisIcon doit:)
									(= evtClaimed (& theSignal HIDEBAR))
								)
							)
						)
						(SHIFTTAB
							(self retreat:)
						)
						(TAB
							(self advance:)	
						)
					)
				)

				((| userEvent helpEvent)
				 	(if (and thisIcon
								(thisIcon helpVerb?)
							)
;						(if (not (HaveMouse))			;hide the cursor if you have no mouse - RWL
;							(= oldCur (theGame setCursor:	theCursor FALSE)
;						)
						(Print
							font:			userFont,
							width:		250,
							addText:		(thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?),
							init:
						)
						(if (not (HaveMouse))
							(theGame setCursor: oldCur)
						)
				 	)
				 	(if helpIconItem
				 		(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
				 	)
				 	(theGame setCursor: normalCursor)
				)
			)
		)
		(return evtClaimed)
	)

	(method (advance &tmp theIcon i)
		;
		; Change the highlighted icon to the next in the list, wrapping to
		;	the first if necessary

		(for 	((= i 1)) 
				(<= i size)
				((= i (mod (+ i 1) size)))	

			(= theIcon
			 	(self at: (mod (+ i (self indexOf: highlightedIcon)) size))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)

	(method (retreat &tmp theIcon i)
		;
		; Change the highlighted icon to the previous in the list, wrapping
		;	to the last if necessary

		(for 	((= i 1)) 
				(<= i size)
				((= i (mod (+ i 1) size)))	

			(= theIcon
			 	(self at: (mod (- (self indexOf: highlightedIcon) i) size))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
		)
			
		(self highlight: theIcon (& state IB_ACTIVE))
	)

	(method (select theIcon relVer)
		(return
			(if (theIcon select:(if (>= argc 2) relVer))
				(if (not (& (theIcon	signal?) IMMEDIATE))
					(= curIcon theIcon)
				)
				TRUE
			)
		)
	)

	(method (highlight theIcon posnCursor &tmp sColor)
		(if (not (& (theIcon signal?) DISABLED))
			(if highlightedIcon
				(highlightedIcon highlight: FALSE)	
			)
			(= highlightedIcon theIcon)
			(theIcon highlight: TRUE)
		)
		(if (and (>= argc 2) posnCursor)
			(theGame 
				setCursor: 
					theCursor	
					TRUE
					(+ 
						(theIcon nsLeft?) 
						(/ (- (theIcon nsRight?) (theIcon nsLeft?)) 2)
					) 
					(- (theIcon nsBottom?) 3)
			)
		)
	)

	(method (advanceCurIcon &tmp theIcon i newIcon)
		(if (& state DISABLED)
			(return)
		)
		(= theIcon curIcon)

		;; not DISABLED, but	IMMEDIATE
		(= i 0)
		(while (& ((= theIcon (self at:(mod (+ (self indexOf:theIcon) 1) size))) signal?) (| DISABLED IMMEDIATE))	
			(if (> i (+ 1 size))
				(return)
			else
				(++ i)
			)
		)
		(= curIcon theIcon)
		(theGame setCursor: (self getCursor:))
	)

	(method (swapCurIcon &tmp firstIcon)
		(cond
			((& state DISABLED)
				(return)
			)
			((and 
					(!= curIcon (= firstIcon (List LNodeValue (self first:)))) ;EO: Was "KList"
					(not (& (firstIcon signal?) DISABLED))
				)
				(= prevIcon curIcon)
				(= curIcon (List LNodeValue (self first:))) ;EO: Was "KList"
			)
			((and prevIcon	(not (& (prevIcon	signal?) DISABLED)))
				(= curIcon prevIcon)
			)
		)
		(theGame setCursor: (self getCursor:))
	)

	(method (shouldOpen event)
		;
		; Determine if the event matches the criteria for opening the iconbar

		(return
			(or
				(and	(== (event type?) nullEvt)
						(<= (event y?) 0)
				)
				(and	(== (event type?) keyDown)
						(OneOf (event message?) ESC DELETE)
				)
			)
		)
	)

	(method (shouldClose event)
		;
		; Determine if the event matches the criteria for closing the iconbar

		(return
			(or
				(and	(== (event type?) nullEvt)
						(> (event y?) (plane bottom?))
				)
				(and	(== (event type?) keyDown)
						(OneOf (event message?) ESC DELETE)
				)
			)
		)
	)

	(method (getCursor)
		;
		; Return the cursor appropriate for the current icon

		(return
			(if curIcon
				(curIcon getCursor:)
			else
				normalCursor
			)
		)
	)
)


(class IconBarCursor kindof Cursor
;;;	(methods
;;;		initialize			; set our view, loop, & cel from the curIcon
;;;	)
	(method (initialize theIcon)
		(= view (theIcon cursorView?))
		(= loop (theIcon cursorLoop?))
		(= cel (theIcon cursorCel?))
	)
)