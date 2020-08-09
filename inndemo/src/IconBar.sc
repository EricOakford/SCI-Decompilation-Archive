;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	ICONBAR.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	J. Mark Hood
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	Classes:
;;;;		IconItem
;;;;		IconBar


(script# ICONBAR)
(include game.sh)
(use Main)
(use Print)
(use Tutorial)
(use System)


(class IconItem kindof Object
	;;; An IconItem is a cel that is drawn on the icon bar
	(properties
		-info- 			$8004			;(| CLASSBIT NODISPLAY)
		name 				"IconI"
		view 				-1				; picture of the icon in bar
		loop 				-1
		cel  	 			-1
		nsLeft			0
		nsTop				-1
		nsRight			0
		nsBottom			0
		state 			NULL
		cursor			-1				; picture of the icon as cursor
		type				userEvent	; event type this icon produces
		message			-1				; which verb this icon produces
		modifiers		0
		signal			RELVERIFY
		maskView			0
		maskLoop			0
		maskCel			0
		highlightColor	0
		lowlightColor	0
		noun				0				;- Used for the help messages
		modNum			0				;/		(default module is room 0)
		helpVerb			0				; verb to use for help strings (normally HELP)
	)

;;; 	(methods
;;;		show
;;;		select
;;;		highlight
;;;		onMe
;;;		mask
;;;	)

	(method (show iX iY &tmp iconNo uISize iconSpace leftEdge topEdge celWide pnv)
		(|= signal IB_ACTIVE)
		(if argc
			(= nsLeft iX)
			(= nsRight (+ iX (CelWide view loop cel)))
			(= nsTop  iY)
			(= nsBottom (+ iY (CelHigh view loop cel)))
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
		)

		;** draw icon on menu bar
		(DrawCel	view loop cel nsLeft nsTop -1)
		(if (& signal DISABLED)
			(self mask:)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)

	(method (mask)
		(DrawCel	maskView maskLoop maskCel 
			(+ 
				nsLeft 
				(/ 
					(- (CelWide view loop cel)(CelWide maskView maskLoop maskCel))
					2
				)
			)
			(+
				nsTop
				(/
					(- (CelHigh view loop cel)(CelHigh maskView maskLoop maskCel))	
					2
				)
			)
			-1
		)
	)

	(method (onMe obj)
		(return
			(and
				(>= (obj x?) nsLeft)
				; comment if you wish events above top of picture to still work
				(>= (obj y?) nsTop)
				(<= (obj x?) nsRight)
				(<= (obj y?) nsBottom)
			)	
		)
	)

	(method (highlight  tOrF &tmp t l b r sColor)
		(if (or (not (& signal IB_ACTIVE)) (== highlightColor -1))
			(return)
		)
		
		;** Draw or erase selector box based on setting of selected state
		(= sColor (if (and argc tOrF) highlightColor else lowlightColor))
		(= t (+ nsTop 2))
		(= l (+ nsLeft 2))
		(= b (- nsBottom 3))
		(= r (- nsRight 4))

		(Graph GDrawLine t l t r  sColor -1 -1)
		(Graph GDrawLine t r b r  sColor -1 -1)
		(Graph GDrawLine b r	b l  sColor -1 -1)
		(Graph GDrawLine b l t l  sColor -1 -1)
		(Graph GShowBits (- nsTop 2) (- nsLeft 2) nsBottom (+ nsRight 3) VMAP)
	)

	(method (select relVer &tmp event whichCel tut)
		(return
			(cond
				((& signal DISABLED)
					FALSE
				)
				((and argc relVer (& signal RELVERIFY))
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= event	(Event new:)) type?)	mouseUp)
						(event localize:)
						(if (self onMe: event)
							(if (not whichCel)
								(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						else
							(if whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
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
)


(class IconBar kindof Set
	(properties
		height				0
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
		port					0
		window				0
		state					OPENIFONME
		activateHeight 	0
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
;;;		enable
;;;		noClickHelp
;;;		findIcon
;;;	)

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

	(method (noClickHelp &tmp event lastIcon thisIcon oldPort eO oldCur)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(= eO (systemWindow eraseOnly?))
		(systemWindow eraseOnly: TRUE)
		(while (not ((= event ((user curEvent?) new:)) type?))

			(if (not (self isMemberOf: IconBar))
				(event localize:)
			)

			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon)
								(thisIcon helpVerb?)
							)
						(= lastIcon thisIcon)
						(if modelessDialog
							(modelessDialog dispose:)
						)
						(Print
							font:			userFont,
							width:		250,
							addText:		(thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?),
							modeless:	TRUE,
							init:
						)
						(SetPort oldPort)
					)
				)
				(modelessDialog
					(modelessDialog dispose:)
				)	
				(else
					(= lastIcon 0)
				)
			)
			(event dispose:)
		)
		(systemWindow eraseOnly: eO)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(SetPort oldPort)
		(if (not (helpIconItem onMe: event))
			(self dispatchEvent: event)
		)
	)

	(method (handleEvent event &tmp keyInvoked eType newEvent
										newCursor oldCursor oldCurIcon oldInvIcon)

		(event localize:)
		(= eType (event type?))

		(cond 
			;; don't open if disabled
			((& state DISABLED))
			((or
				;; opened because of mouse location
					(and 
						(not eType)
						(& state OPENIFONME)
						(<= -10 (event y?) height)
						(<= 0 (event x?) SCRNWIDE)
						(not (= keyInvoked FALSE))
					)
					;; opened from esc key
					(and
						(== eType keyDown)
						(or
							(== (event message?) ESC)
							(== (event message?) DELETE)
						)
						(= keyInvoked TRUE)
					)
				)
				(event globalize:)
				; save mouse location for key invoked icon selection
				(= oldMouseX (event x?))
				(= oldMouseY (event y?))
						
				(= oldCursor theCursor)
				(= oldCurIcon curIcon)
				(= oldInvIcon curInvIcon)

				(self show:)

				(theGame setCursor: ARROW_CURSOR)

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
						(curIcon cursor?)
					else
						waitCursor
					)
				)

				;; the user selected an icon
				(if keyInvoked
					(theGame setCursor: newCursor TRUE oldMouseX oldMouseY)
				else
					(theGame 
						setCursor: 
							newCursor
							TRUE
						  	((event new:) x?)
							(Max (event y?) (+ 1 height))
					)
				)
				(self hide:)
			)
			((& eType keyDown)
				(switch (event message?)
					(ENTER
						(cond
							; There's no current icon
							((not (IsObject curIcon)))

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
			((& eType mouseDown)
				(cond
					; let user cycle through the cursors for
					; all non-IMMEDIATE Icons.
					((& (event modifiers?) shiftDown)
						(self advanceCurIcon:)
						(event claimed: TRUE)
					)
					; swap between last non-walk cursor and walk
					((& (event modifiers?) ctrlDown)
						(if (user canControl:)
							(self swapCurIcon:)
						)
						(event claimed: TRUE)
					)
					; let curIcon condition the Event
					((IsObject curIcon)
						(event
							type:		(curIcon type?),
					  		message: (if (== curIcon useIconItem)
											(curInvIcon message?)
										else
											(curIcon message?)
										)
						)
					)
				)
			)
		)
	)

	(method (disable theIconNumber &tmp i thisIcon)
		(if argc
			;; turn off icons specified
			(for ((= i 0))	(< i argc) ((++ i))
				(= thisIcon 
					(if (IsObject [theIconNumber i])
						[theIconNumber i]
					else
						(self at: [theIconNumber	i])
					)
				)
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
			;; turn off the whole bar
			(|= state DISABLED)
		)
	)	

	(method (enable theIconNumber &tmp i thisIcon)
		(if argc
			;; turn on icons specified
			(for ((= i 0))	(< i argc) ((++ i))
				(= thisIcon 
					(if (IsObject [theIconNumber i])
						[theIconNumber i]
					else
						(self at: [theIconNumber	i])
					)
				)
				(thisIcon signal: (&	(thisIcon signal?) (~ DISABLED)))
			)
		else
			;; turn on the whole bar
			(&= state (~ DISABLED))
		)
	)

	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(sounds pause:)
		(|= state IB_ACTIVE)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(=	height (CelHigh ((= theIcon (self at: 0)) view?) (theIcon loop?) (theIcon cel?)))

		; Prepare to draw the icon bar as it currently exists
		(= port (GetPort))
		(SetPort -1)

		; Save the portion of the picture under the bar  (set PicNotValid
		;	so DrawCel doesn't make IconBar appear as individual icons)
		(= underBits (Graph GSaveBits y 0 (+ y height) SCRNWIDE VMAP))
		(= pnv (PicNotValid))
		(PicNotValid TRUE)

		(for	((= theX 0) (= theY y) (= node (FirstNode elements)))	
				node
				((= node nextNode))
			(= nextNode (NextNode node))			
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			; if nsRect not set yet
			(if (<= (obj nsRight?) 0)
				(obj show: theX theY)
				(= theX (obj nsRight?))
			else
				(obj show:)
			)
		)

		(if curInvIcon
			(if (ego has: (inventory indexOf: curInvIcon))
				(= theX
					(+
						; add offset
						(/
							; half each side
							(-
								; difference in widths
								(- (useIconItem nsRight?) (useIconItem nsLeft?))
								(CelWide (curInvIcon view?) (curInvIcon loop?) (curInvIcon cel?))
							)
							2
						)
						(useIconItem nsLeft?)
					)
				)
				(= theY
					(+
						y
						; add offset
						(/
							; half each side
							(-
								; difference in heights
								(- (useIconItem nsBottom?) (useIconItem nsTop?))
								(CelHigh (curInvIcon view?) (curInvIcon loop?) (curInvIcon cel?))
							)
							2
						)
						(useIconItem nsTop?)
					)
				)
				(DrawCel	
					(curInvIcon view?) 
					(curInvIcon loop?)
					(curInvIcon cel?)
					theX
					theY
					-1
				)
				(if (& (useIconItem signal?) DISABLED)
					(useIconItem mask:)
				)
			else
				; clean up for sloppy programmers
				(= curInvIcon NULL)
			)
		)
		(PicNotValid pnv)
		; now show bits
		(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
		; and show current Icon
		(self	highlight: curIcon)
	)

	(method (hide &tmp node nextNode obj)
		(if (& state IB_ACTIVE)
			(sounds pause: FALSE)
			(&= state (~ IB_ACTIVE))

			;; tell all	icons that they are no longer active
			(for 	((= node (FirstNode elements)))
					node
					((= node nextNode))
				(= nextNode (NextNode node))

				(if (not (IsObject (= obj (NodeValue node))))
					(return)
				)
				((= obj (NodeValue node))
					signal: (& (obj signal?) (~ IB_ACTIVE))
				)
			)

			; Turn off help, if applicable
			(if (and	(not (& state NOCLICKHELP))
						(IsObject helpIconItem)
						(& (helpIconItem signal?) TRANSLATOR)
				)
				(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
			)

			;** hide the icon bar
			(Graph GRestoreBits underBits)
			(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
			(Graph GReAnimate y 0 (+ y height) SCRNWIDE)
			(SetPort port)		;restore to original port
			(= height activateHeight)
		)
	)


	(method (doit &tmp event eType eMsg eMod tut)
		(while (and (& state IB_ACTIVE)
						(= event ((user curEvent?) new:))
					)
			(= eType (event type?))
			(= eMsg	(event message?))
			(= eMod	(event modifiers?))

			(Wait 1)	;; here for the dongle test

			(= gameTime (+ tickOffset (GetTime)))

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
			(event localize:)
			(if (and
					(or
						(== eType mouseDown)
						(and
							(== eType keyDown)
							(== eMsg ENTER)
						)
					)
					(and 
						(IsObject helpIconItem)
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
									thePort [buffer 50] theCurs theSignal isHelp	oldCur
		)
		
		(= evtX 		(event x?))
		(= evtY 		(event y?))
		(= evtType 	(event type?))
		(= evtMsg	(event message?))
		(= evtClaimed (event claimed?))
		(= thisIcon (self firstTrue: #onMe event))
		(if thisIcon
			(= theCurs 		(thisIcon cursor?))
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
						((not
								(and
									(<= 0 evtY (+ y height))
									(<= 0 evtX SCRNWIDE)
								)
							)
							(if
								(and
									(& state OPENIFONME)
									(or 
										(not (IsObject helpIconItem))
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
							(if theCurs
								(theGame	setCursor: theCurs)
							)
							(if (& state NOCLICKHELP)
								(self noClickHelp:)
							else
								(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
							)
						else
						 	(= evtClaimed (& theSignal HIDEBAR))
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
									(if (!= theCurs -1)
										(theGame	setCursor: theCurs)
									)
									(if helpIconItem
										(helpIconItem signal: (| (helpIconItem signal?) TRANSLATOR))
									)						
								)
								((and (IsObject thisIcon)
										(self select: thisIcon)
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
						(if (not (HaveMouse))			;hide the cursor if you have no mouse - RWL
							(= oldCur (theGame setCursor:	INVIS_CURSOR))
						)
				 		(= thePort (GetPort))
						(Print
							font:			userFont,
							width:		250,
							addText:		(thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?),
							init:
						)
				 		(SetPort thePort)
						(if (not (HaveMouse))
							(theGame setCursor:	oldCur)
						)
				 	)
				 	(if helpIconItem
				 		(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
				 	)
				 	(theGame setCursor: ARROW_CURSOR)
				)
			)
		)
		(return evtClaimed)
	)

	(method (advance &tmp theIcon i)
		(for 	((= i 1)) 
				(<= i size)
				((= i (mod (+ i 1) size)))	

			(= theIcon
			 	(self at: (mod (+ i (self indexOf: highlightedIcon)) size))
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
		)
		(self highlight: theIcon (& state IB_ACTIVE))
	)

	(method (retreat &tmp theIcon i)
		(for 	((= i 1)) 
				(<= i size)
				((= i (mod (+ i 1) size)))	

			(= theIcon
			 	(self at: (mod (- (self indexOf: highlightedIcon) i) size))
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
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
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight:FALSE)	
			)
			(= highlightedIcon theIcon)
			(theIcon highlight:TRUE)
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
		(theGame setCursor: (curIcon cursor?) TRUE)
	)

	(method (swapCurIcon &tmp firstIcon)
		(cond
			((& state DISABLED)
				(return)
			)
			((and 
					(!= curIcon (= firstIcon (NodeValue (self first:)))) 
					(not (& (firstIcon signal?) DISABLED))
				)
				(= prevIcon curIcon)
				(= curIcon (NodeValue (self first:)))
			)
			((and prevIcon	(not (& (prevIcon	signal?) DISABLED)))
				(= curIcon prevIcon)
			)
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
)
