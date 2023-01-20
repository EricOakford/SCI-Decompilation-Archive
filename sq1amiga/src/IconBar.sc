;;; Sierra Script 1.0 - (do not remove this comment)
(script# ICONBAR)
(include game.sh)
(include menu.sh)
(use Main)
(use Intrface)
(use System)

(define MAXINC	10)

(class IconItem kindof Object
	;;; An IconItem is a cel that is drawn on the icon bar
	(properties
		-info- $8004		;(| CLASSBIT NODISPLAY)
		name 			"IconI"
		view 			-1				;picture of the icon in bar
		loop 			-1
		cel  	 		-1
		nsLeft		0
		nsTop			-1
		nsRight		0
		nsBottom		0
		state 		NULL
		cursor		-1				;picture of the icon as cursor
		type			userEvent
		message		-1
		modifiers	0
		signal		RELVERIFY
		helpStr		NULL
		maskView		0
		maskLoop		0
		maskCel		0
		highlightColor	0
		lowlightColor	0
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

	(method (select relVer &tmp event whichCel)
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
						(if (self onMe:event)
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
					whichCel
				)
				(else
					TRUE
				)
			)
		)
	)
)
(instance ibEvent of Event)
(class IconBar kindof Set
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
;;;	)

	(properties
		height		0
		underBits	0
		oldMouseX	0
		oldMouseY	0
		curIcon 		0 ; the icon that corresponds to the currently selected verb/cursor
		highlightedIcon 0 ; the icon with the cursor and selector box on it
		prevIcon		0
		curInvIcon	0
		useIconItem	0
		helpIconItem 0
		port			0
		window		0
		state			OPENIFONME
		activateHeight 0
		y				0
	)

	(method (noClickHelp &tmp event lastIcon thisIcon [tmpStr 100] oldPort eO)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(= eO (systemWindow eraseOnly?))
		(systemWindow eraseOnly: TRUE)
		(while (not ((= event (Event new:)) type?))
			(event name:{nchEvent})
			(if (not (self isMemberOf: IconBar))
				(event localize:)
			)
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and 
							(!= thisIcon lastIcon)
							(thisIcon helpStr?)
						)
						(= lastIcon thisIcon)
						(Format @tmpStr	(thisIcon helpStr?))
						(Print @tmpStr #dispose:)
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
			(= event NULL)
		)
		(systemWindow eraseOnly: eO)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(SetPort oldPort)
		;(event globalize:)
		(if (helpIconItem onMe: event)
			(if (IsObject event) (event dispose:))
		else
			(self dispatchEvent: event)
		)
	)

	(method (handleEvent event 
			&tmp keyInvoked evType oldCursor oldCurIcon newCursor	oldInvIcon newEvent
		)
		(event localize:)
		(cond 
			;; don't open if disabled
			((& state DISABLED))
			((or
				;; opened because of mouse location
					(and 
						(not (= evType (event type?)))
						(& state OPENIFONME)
						(<= -10 (event y?) height)
						(<= 0 (event x?) SCRNWIDE)
						(not (= keyInvoked FALSE))
					)
					;; opened from esc key
					(and
						(== evType keyDown)
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
					(if (and 
							(== oldCurIcon curIcon)
							(== oldInvIcon curInvIcon)
						)
						oldCursor
					else
						(curIcon cursor?)
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
						  	((= newEvent (Event new:))	x?)
							(Max (newEvent y?) (+ 1 height))
					)
					(newEvent dispose:)
				)
				(self hide:)
			)
			((& evType keyDown)
				(switch (event message?)
					(ENTER
			  			(if (IsObject curIcon)
							(event
			  					type:		(curIcon type?),
					  			message: (curIcon message?)
			  				)
						)
					)
					(INSERT
						(self swapCurIcon:)
						(event claimed:TRUE)
					)
					(dirStop
						(if (& (event type?) direction)
							(self	advanceCurIcon:)
							(event claimed:TRUE)
						)
					)
				)
			)
			((& evType mouseDown)
				(cond
					; let user cycle through the cursors for
					; all non-IMMEDIATE Icons.
					((& (event modifiers?) shiftDown)
						(self advanceCurIcon:)
						(event claimed:TRUE)
					)
					; swap between last non-walk cursor and walk
					((& (event modifiers?) ctrlDown)
						(self swapCurIcon:)
						(event claimed:TRUE)
					)
					; let curIcon condition the Event
					((IsObject curIcon)
						(event
							type:		(curIcon type?),
							message: (curIcon message?)
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
					(if (IsObject [theIconNumber	i])
						[theIconNumber	i]
					else
						(self at:[theIconNumber	i])
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
					(if (IsObject [theIconNumber	i])
						[theIconNumber	i]
					else
						(self at:[theIconNumber	i])
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
		(=	height (CelHigh ((= theIcon (self at:0)) view?) (theIcon loop?) (theIcon cel?))) 
		;** prepare to draw the icon bar as it currently exists.
		(= port (GetPort))	;saving current port
		(SetPort -1)
		;** save the portion of the picture	under the bar
		(= underBits (Graph GSaveBits y 0 (+ y height) SCRNWIDE VMAP))
		(= pnv (PicNotValid))
		; make sure	DrawCel doesn't ShowBits
		; so that IconBar appears as a whole, not as individual Icons
		(PicNotValid TRUE)
		(for	((= theX 0)(= theY y)(= node (FirstNode elements)))	
			node
			((= node nextNode))
			(= nextNode (NextNode node))			
			(if (not (IsObject (= obj (NodeValue node)))) (return))
			; if nsRect not set yet
			(if (<= (obj nsRight?) 0)
				(obj show:theX theY)
				(= theX (obj nsRight?))
			else
				(obj show:)
			)
		)
		(if curInvIcon
			(if (ego has:(inventory indexOf: curInvIcon))
				(= theX 
					(+
						; add offset
						(/ 
							; half each side
							(- 
								; difference in widths
								(- (useIconItem nsRight?) (useIconItem nsLeft?))
								(CelWide (curInvIcon view?) (+ (curInvIcon loop?) 1) (curInvIcon cel?))
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
								(CelHigh (curInvIcon view?) (+ (curInvIcon loop?) 1) (curInvIcon cel?))
							)
							2
						)
						(useIconItem nsTop?)
					)
				)
				(DrawCel	
					(curInvIcon view?) 
					(+ (curInvIcon loop?) 1)
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
		(self	highlight:curIcon)
	)

	(method (hide &tmp node nextNode obj)
		(if (& state IB_ACTIVE)
			(sounds pause:FALSE)
			(&= state (~ IB_ACTIVE))
      	(if helpIconItem
				(helpIconItem signal: (& (helpIconItem signal?) (~ TRANSLATOR)))
			)
			;; tell all	icons that they are no longer active
			(for	
				((= node (FirstNode elements)))	
				node
				((= node nextNode))
				(= nextNode (NextNode node))			

				(if (not (IsObject (= obj (NodeValue node)))) (return))
				((= obj (NodeValue node))
					signal: (& (obj signal?) (~ IB_ACTIVE))
				)
			)
			;** hide the icon bar
			(Graph GRestoreBits underBits)
			(Graph GShowBits y 0 (+ y height) SCRNWIDE VMAP)
			(Graph GReAnimate y 0 (+ y height) SCRNWIDE)
			(SetPort port)		;restore to original port
			(= height activateHeight)
		)
	)
	

	(method (doit)
		(while (& state IB_ACTIVE)
			(ibEvent
	 			type: 	 0,			
				message:  0,		
				modifiers:0,		
				y: 		 0,				
				x: 		 0,				
				claimed:  0,		
				port:		 0
			)
			(GetEvent allEvents ibEvent)
			(Wait 1)	;; here for the dongle test
			(if (== (ibEvent type?) joyDown)
				(ibEvent 
					type: keyDown,
					message: 
						(if (& (ibEvent modifiers?) shiftDown)
							ESC
						else
							ENTER
						),
					modifiers:0
				)
			)
			(ibEvent localize:)
			(if (and
					(or 
						(== (ibEvent type?) mouseDown)
						(and
							(== (ibEvent type?) keyDown)
							(== (ibEvent message?) ENTER)
						)
					)
					(and 
						(IsObject	helpIconItem)
						(& (helpIconItem signal?) TRANSLATOR)
					)
				)
				(ibEvent
					type:		userEvent,
					message:	(helpIconItem message?)
				)
			)
			(MapKeyToDir ibEvent)
			; break if someone claimed the event
			(if (self dispatchEvent: ibEvent)
				(break)
			)

		)
;		(if (IsObject event) (event dispose:))
	)

	(method (dispatchEvent event  
		&tmp evtY evtX evtType evtMsg thisIcon evtClaimed thePort
		)
		
		(= evtX 		(event x?))
		(= evtY 		(event y?))
		(= evtType 	(event type?))
		(= evtMsg	(event message?))
		(= evtClaimed (event claimed?))
		(= thisIcon (self firstTrue:#onMe event))
 		(event dispose:)
	
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
							(self highlight:thisIcon)
						)
					)
				)
				(mouseDown
					(if (and thisIcon	(self select:thisIcon TRUE))
						(if (== thisIcon helpIconItem)
							(if (thisIcon cursor?)
								(theGame	setCursor: (thisIcon cursor?))
							)
							(if (& state NOCLICKHELP)
								(self noClickHelp:)
							else
								(helpIconItem signal:(| (helpIconItem signal?) TRANSLATOR))
							)
						else
						 	(= evtClaimed (& (thisIcon signal?) HIDEBAR))
						)
					)
				)
				(keyDown
					(switch evtMsg
						(ESC
							(= evtClaimed TRUE)
						)
						(ENTER
							(if (not thisIcon)
								(= thisIcon	highlightedIcon)
							)
							(cond
								((and thisIcon (== thisIcon helpIconItem))
									(if (!= (thisIcon cursor?)	-1)
										(theGame	setCursor: (thisIcon cursor?))
									)
									(if helpIconItem
										(helpIconItem signal:(| (helpIconItem signal?) TRANSLATOR))
									)						
								)
								((IsObject thisIcon)
									(self select:thisIcon)
									(= evtClaimed (& (thisIcon signal?) HIDEBAR))
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
				(userEvent
					(if (== evtMsg verbHelp)
						(if (and thisIcon (thisIcon helpStr?))
							(= thePort (GetPort))
							(Printf "%s" (thisIcon helpStr?))
							(SetPort thePort)
						)
						(if helpIconItem
							(helpIconItem signal:(& (helpIconItem signal?) (~ TRANSLATOR)))
						)
						(theGame setCursor: ARROW_CURSOR)
					)
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
			 	(self at:(mod (+ i (self indexOf: highlightedIcon)) size))
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self first:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
		)
		(self highlight:theIcon (& state IB_ACTIVE))
	)

	(method (retreat &tmp theIcon i)
		(for 	((= i 1)) 
				(<= i size)
				((= i (mod (+ i 1) size)))	

			(= theIcon
			 	(self at:(mod (- (self indexOf: highlightedIcon) i) size))
			)
			(if (not (IsObject theIcon))
				(= theIcon (NodeValue (self last:)))
			)
			(breakif (not (& (theIcon signal?) DISABLED)))
		)
			
		(self highlight:theIcon (& state IB_ACTIVE))
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

	(method (advanceCurIcon &tmp theIcon i)
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
		(theGame setCursor:(curIcon cursor?) TRUE)
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
		(theGame setCursor:(curIcon cursor?) TRUE)
	)
)
