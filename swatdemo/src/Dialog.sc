;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DIALOG.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The user interface dialog class and controls list.
;;;;
;;;;	Classes:
;;;;		Dialog
;;;;		Controls


(script# DIALOG)
(include game.sh)
(use Main)
(use Array)
(use System)


(class Dialog kindof Set

	;;; ACTIVE controls can be selected, INACTIVE ones can't.
	;;; EXIT controls return ID when selected.  NON-EXIT controls invoke DOIT.
	;;; All controls will show VISUAL evidence of selection.
	;;; Selection via a mouse click requires a track.

	(properties
		text 				0 				; title
		font				0
		theItem 			0				; objID of "current" item

		nsLeft 			0
		nsTop				0
		nsRight 			0
		nsBottom			0

		ticks				0				; number of ticks to wait before disposing
		caller 			NULL			; who to cue upon disposal
		plane				0				; ID of the plane we're in
		
		eatTheMice		0				; how long to wait before allowing events
		lastTicks		0				; used with eatTheMice
		mouseHiliting	FALSE			; the hiliting will track the mouse
		margin			4				; pixel margin around items
		modeless			DLG_MODAL
		onScreen			0				; if dialog should stay on screen
	)

;;;	(methods
;;;		open				; get a plane to live in
;;;		draw				; actually show on screen
;;;		cue				; respond to time expiration
;;;		advance			; advance to next in list
;;;		retreat			; retreat to previous in list
;;;		move				; relative move from current position
;;;		moveTo			; go to absolute position (upper/left)
;;;		center			; center in screen
;;;		setSize			; make me big enough
;;;		handleEvent		; respond to an event
;;;		check				; cue if time is up
;;;	)

	(method (init pri &tmp p)
		;
		; Create the plane & get ourselves on the screen

		; Figure out what priority to use
		(= p
			(if argc
				pri
			else
				(+ (GetHighPlanePri) 1)
			)
		)
		(plane
			priority: 	p,
			init: 		nsLeft nsTop nsRight nsBottom,
			addCast:		self
;			title: 		text,
		)

		;If ticks were passed by Print, set to dispose time -gtp
 		(if ticks (= ticks (+ ticks (GetTime) tickOffset)) )
		;Fixes bug which forced immediate timeouts for modeless prints


		(self eachElementDo: #init: self)
	)

	(method (doit def &tmp event ret done checked)
		;
		; Process the dialog:
		;
		;	- If there are no active items, any event exits
		;  - Pressing ESC returns 0
		;	- Only ACTIVE items may be selected
		;	- Pressing ENTER returns the currently selected object if it is EXIT
		;	- Tab/Shift-tab advances/retreats to the next/previous ACTIVE item

		(= gameTime (+ tickOffset (GetTime)))
		(= done 0)

		; If def is not passed or zero, we pick first active item, after first
		;	unmarking the current item (if any)
		(if theItem
			(theItem hilite: FALSE)
		)

		; The first active item will be either the 'def' passed, or the first
		;	active item
		(= theItem
			(if (and argc def)
				def
			else
				(self firstTrue: #checkState: dActive)
			)
		)

		(FrameOut)

		; Mark this item (if any)
		(if theItem
			(theItem hilite: TRUE)
		)

		; If there are no active items then we will eat events, so set up
		;	the variables that determine how long (based on global eatMice)
		(if (not theItem)
			(= eatTheMice eatMice)
			(= lastTicks (GetTime))
		else
			(= eatTheMice 0)
		)

		; Now get events and act upon them until we get a return code
		(= ret 0)
		(while (not ret)
			(= gameTime (+ tickOffset (GetTime)))

			; Call everyone's cycler
			(self eachElementDo: #cycle:)
		
			; Create an event...
			(= event ((Event new:) localize: plane))

			; Don't process the event if we're supposed to eat them (see above)
			(if eatTheMice
				(-- eatTheMice)
				(if (== (event type?) mouseDown)
					(event type: 0)
				)

				(while (== lastTicks (GetTime))
					(continue)
				)
				(= lastTicks (GetTime))
			)

			(if mouseHiliting
				(self eachElementDo: #perform checkHiliteCode self event)
			)

			; Everyone gets a shot at the event...
			(if (event type?)
				(= ret (self handleEvent: event))
			)

			; Get rid of the event we made
			(event dispose:)

			; If we're being timed, see if we should dispose yet
			(if (or	(= checked (self check:))
						(!= modeless DLG_MODAL)
				)
				(if (and checked (!= modeless DLG_MODAL))
					(self dispose:)
				)
				(break)
			)

			(FrameOut)
		)

		(return ret)
	)

	(method (check)
		;
		; Check to see if we can be disposed yet
			
		(if ticks
			(return (> (- gameTime ticks) 0))
		)
	)
		
	(method (dispose &tmp theCaller)
	 	;
		; Dispose of dialog & its stuff

		(self eachElementDo: #dispose:, release:)

		; Remove ourselves from the plane's cast list
		(plane deleteCast: self)

		; Clear the currently selected item
		(= theItem 0)

		; Cue someone if we've been told to
		(= theCaller caller)
		(super dispose:)
		(if theCaller
			(theCaller cue:)
		)
	)

	(method (advance &tmp obj node)
		;
		; Highlight next ACTIVE item in list

		(if theItem
			; clear current item
			(theItem hilite: FALSE)

			; we need the node value that we are
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self next: node)))
					(= node (self first:))
				)
				(= theItem (List LNodeValue node))

				; we break on next active item
				(if (& (theItem state?) dActive)
					(break)
				)
			)
					
			(theItem hilite: TRUE)
			(if (not (HaveMouse))
				(theGame setCursor: 
	  				theCursor
	  				TRUE
	  				(+ (theItem nsLeft?) (/ (- (theItem nsRight?) (theItem nsLeft?)) 2))
	  				(- (theItem nsBottom?) 3)
				)
			)
		)
	)

	(method (retreat &tmp obj node)
		;
		; Highlight previous ACTIVE item in list

		(if theItem
			; clear current item
			(theItem hilite: FALSE)

			; we need the node value that we are
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self prev: node)))
					(= node (self last:))
				)
				(= theItem (List LNodeValue node))

				; we break on next active item
				(if (& (theItem state?) dActive)
					(break)
				)
			)

			(theItem hilite: TRUE)
			(if (not (HaveMouse))
				(theGame setCursor:
					theCursor
					TRUE
					(+ (theItem nsLeft?) (/ (- (theItem nsRight?) (theItem nsLeft?)) 2))
					(- (theItem nsBottom?) 3)
				)
			)
		)
	)

	(method (handleEvent event &tmp ret eType eMsg)
		;
		; Respond to the passed event with:
		;		ID					- object that claimed event or active object when ENTERed
		;		RET_ESC			- ESC key pressed
		;		RET_NO_ACTIVE 	- no active items
		;		RET_NO_EVENT	- no event

		; If the event is a direction, remap it as a keyDown
		(if (& (event type?) direction)
			(switch (event message?)
				(dirS
					(event 
						type:	keyDown,
						message:DOWNARROW
					)
				)
				(dirN
					(event 
						type: keyDown,
						message:UPARROW
					)
				)
				(dirW
					(event
						type:	keyDown,
						message:	LEFTARROW
					)
				)
				(dirE
					(event
						type:	keyDown,
						message:	RIGHTARROW
					)
				)
			)
		)
		(= eType (event type?))
		(= eMsg  (event message?))

		; does this event belong to any in the list
		(if (= ret (self firstTrue: #handleEvent: event))

			; If NOT marked EXIT we doit: and advance to next
			(if (not (ret checkState: dExit))
				(if theItem
					(theItem hilite: FALSE)
				)
				((= theItem ret) hilite: TRUE)												

				; Send "doit" to object to perform any subclass-specific stuff
				(ret doit:)

				; don't report it
				(= ret RET_NO_EVENT)
			; Else we exit
			else
				(return ret)
			)
		else
			; Refresh our temps
			(= eType (event type?))
			(= eMsg  (event message?))

			; Check for standard conventions
			(= ret RET_NO_EVENT)
			(cond

				; ENTER key pressed and theItem is active, return object ID

				((and	(or	(== eType joyDown)
								(and 	(== eType keyDown)
										(== eMsg ENTER)
								)
						)
						theItem
						(theItem checkState: dActive)
				 	)
					(= ret theItem)
					(event claimed: TRUE)
				)

				; ESC key pressed, return RET_ESC

				((and	(== eType keyDown)
						(== eMsg ESC)
					)
					(event claimed: TRUE)
					(= ret RET_ESC)
				)

				; Any event exits if no active items, return RET_NO_ACTIVE

				((and	(not 	(self firstTrue: #checkState: dActive))
				 		(or	(and	(== eType keyDown)
				 						(== eMsg ENTER)
				 				)
				 				(OneOf eType mouseDown joyDown)
				 		)
					)
					(event claimed: TRUE)
					(= ret RET_NO_ACTIVE)
				)

				; If we pressed a TAB, right-arrow, or down-arrow, go to next item

				((and (== eType keyDown)
						(OneOf eMsg TAB RIGHTARROW DOWNARROW)
					)
					(event claimed: TRUE)
					(self advance:)

				)

				; If we pressed shift-TAB, left-arrow, or up-arrow, go to prev item

				((and (== eType keyDown)
						(OneOf eMsg SHIFTTAB LEFTARROW UPARROW)
					)
					(event claimed: TRUE)
					(self retreat:)
				)
			)
		)
		(return ret)
	)

	(method (move h v)
		;
		; Move dialog relative to current position

		(+= nsLeft  	h)
		(+= nsTop  		v)
		(+= nsRight		h)
		(+= nsBottom	v)

		;;; If should keep plane on screen, try to do so
		(if onScreen
			(if (< nsLeft    0)	
				(+= nsRight (- 0 nsLeft))
				(=  nsLeft 0)
			)
			(if (> nsRight lastScreenX)	
				(+= nsLeft  (- lastScreenX nsRight))
				(= nsRight lastScreenX)
			)
			(if (< nsTop      0)	
				(+= nsBottom (-   0 nsTop))
				(= nsTop     0)
			)
			(if (> nsBottom lastScreenY)
				(+= nsTop   (- lastScreenY nsBottom))
				(= nsBottom lastScreenY)
			)
		)

		(if plane
			(plane setRect: nsLeft nsTop nsRight nsBottom TRUE)
		)
	)

	(method (moveTo h v)
		;
		; Move dialog to absolute position

		(self move: (- h nsLeft) (- v nsTop))
	)

	(method (center onPlane &tmp theX theY l t r b p)
		;
		; Center the dialog in 'onPlane'.  If 'onPlane' is omitted,
		;	use the curRoom's plane if it exists, otherwise use 'thePlane.'

		(cond
			(argc
				(= l (onPlane left?))
				(= t (onPlane top?))
				(= r (onPlane right?))
				(= b (onPlane bottom?))
			)
			((or (not curRoom) (not (curRoom plane?)))
				(= l (thePlane left?))
				(= t (thePlane top?))
				(= r (thePlane right?))
				(= b (thePlane bottom?))
			)
			((= p (curRoom plane?))
				(= l (p left?))
				(= t (p top?))
				(= r (p right?))
				(= b (p bottom?))
			)
			(else
				(return)
			)
		)
		;clip l t r b to screen
		(if (< l 0) (= l 0) )
		(if (< t 0) (= t 0) )
		(if (> r lastScreenX) (= r lastScreenX) )
		(if (> b lastScreenY) (= b lastScreenY) )
		;center the dialog:
		(self moveTo:
			(+ l (/ (- (- r l) (- nsRight nsLeft)) 2))
			(+ t (/ (- (- b t) (- nsBottom nsTop)) 2))
		)	
	)

	(method (setSize &tmp node obj r)
		;
		; Determine the required dimensions to encompass all items

		(= r (IntArray with: 0 0 0 0))
		(if text
			; get textsize in the font without word wrapping
			(Text TextSize
				(r data?)
				text
				font
				0		;-1
			)
			(= nsLeft 	(r at: 0))
			(= nsTop 	(r at: 1))
			(= nsRight 	(r at: 2))
			(= nsBottom (r at: 3))
  		else
			(= nsLeft 	0)
			(= nsTop 	0)
			(= nsRight	0)
			(= nsBottom 0)
		)
		(r dispose:)
		(for	((= node (self first:)))
				node
				((= node (self next: node)))

			(= obj (List LNodeValue node))

			; compare to existing size and adjust if neccessary
			(if (< (obj nsLeft?) nsLeft)
				(= nsLeft (obj nsLeft?))
			)
			(if (< (obj nsTop?) nsTop)
				(= nsTop (obj nsTop?))
			)
			(if (> (obj nsRight?) nsRight )
				(= nsRight (obj nsRight?))
			)
			(if (> (obj nsBottom?) nsBottom)
				(= nsBottom (obj nsBottom?))
			)
		)

		; add a border of margin pixels.
		(+= nsRight margin)
		(+= nsBottom margin)
		(if plane
			(plane setRect: nsLeft nsTop nsRight nsBottom)
		)
	)
)


(instance checkHiliteCode of Code
	(method (doit who dlg event)
		(if (and	(& (who state?) dActive)
					(who onMe: event)
		  			(not (& (who state?) dSelected))
		 	)
			((dlg theItem?) hilite: FALSE)
			(dlg theItem: who)
			(who hilite: TRUE)
		)
	)
)



(class Controls kindof List

;;;	(methods
;;;		draw
;;;		handleEvent
;;;	)


	(method (draw)
		(self eachElementDo: #setSize:)
		(self eachElementDo: #draw:)
	)


	(method (handleEvent evt &tmp cont)
		;; Find and track an active control.

		(if (evt claimed?) (return 0))

		(if (= cont (self firstTrue: #handleEvent: evt))
			(if (not (cont checkState: dExit))
				(cont doit:)
				(= cont 0)
			)
		)
		(return cont)
	)
)
