;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INTRFACE.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	Classes which implement the user interface of SCI.  Note that some dialog
;;;;	classes (DIcon, DButton, DEdit, DSelector) have been moved out to DIALOG
;;;;	module to save space.
;;;;
;;;;	Classes:
;;;;		Dialog
;;;;		DItem
;;;;		DText
;;;;
;;;;	Procedures:
;;;;		StillDown
;;;;		GetNumber
;;;;		MousedOn


(script# INTRFACE)
(include game.sh)
(use Main)
(use Print)
(use System)

;;;(procedure
;;;	GetNumber
;;;	StillDown
;;;	MousedOn
;;;)

(public
	StillDown	0
	GetNumber	1
	MousedOn		2
)


(procedure (StillDown &tmp event ret)
	;;; Return true if there is no mouse up in queue.

	(= event (Event new:))
	(= ret (!= (event type?) mouseUp))
	(event dispose:)
	(return ret)
)


(procedure (GetNumber string default &tmp [theLine 40])
	(= theLine 0)
	(if (> argc 1)
		(Format @theLine {%d} default)
	)
	(return
		(if (GetInput @theLine 5 string)
			(ReadNumber @theLine)
		else
			-1
		)
	)
)

(procedure (MousedOn obj event)
	(return
		(and
			(< (obj nsLeft?) (event x?) (obj nsRight?))
			(< (obj nsTop?)  (event y?) (obj nsBottom?))
		)
	)
)


(class DItem kindof Object
	;;; The superclass of all items of control in the user interface.

	(properties
		name		0			; don't waste storage on a name string
		type 		0			; the type of this control
		state		0			; defined by each subclass
		nsTop		0			; visible rectangle
		nsLeft 	0			; in LOCAL coords
		nsBottom	0			; used to select
		nsRight	0			; control via a mouse click
		key		0			; key code associated with control
		said		0			; said spec associated with control
		value		0			; for programmers use
	)

;;;	(methods
;;;		enable			; set/reset active bit in state
;;;		select			; set/reset selected bit in state
;;;		handleEvent		; determine if user input is yours
;;;		check				; check event x/y in rectangle
;;;		track				; select confirmation
;;;		doit				; instantiated for each subclass/control
;;;		setSize			; instantiated in each type of item
;;;		move				; relative move
;;;		moveTo			; absolute move
;;;		draw				; draw self in proper manner
;;;		setMark			; set default marking
;;;		isType			; return equality of type and argument
;;;		checkState		; do a bit test of argument
;;;		cycle				; do something cyclic
;;;	)


	(method (enable bool)
		;; Enable/disable this control.
		(if bool
			(|= state dActive)
		else
			(&= state (~ dActive))
		)
	)

	(method (select bool)
		;; Select/deselect this control.
		(if bool
			(|= state dSelected)
		else
			(&= state (~ dSelected))
		)
		(self draw:)
	)

	(method (handleEvent event &tmp ret evtType evt)
		;; Return ID if this event is yours, else 0.

		(if (event claimed?) (return 0))

		; default to not selected
		(= ret 0)

		(if (& state dActive)
			; slight speed up effort
			(= evtType (event type?))

			(if
				(or
					; pressed your key
					(and 	(== evtType keyDown)
							(== (event message?) key)
					)

					; clicked in box
					(and 	(== evtType mouseDown)
							(self check: event)
					)
				)
	
				; this was us
				(event claimed: TRUE)
				(= ret (self track: event))
			)
		)			

		; return the result of our tests
		(return ret)
	)

	(method (check event)
		;; Return true if x/y/ in your rectangle.
		(return
			(and
				(>= (event x?) nsLeft)
				(>= (event y?) nsTop)
				(< (event x?) nsRight)
				(< (event y?) nsBottom)
			)
		)
	)

 	(method (track event &tmp in lastIn)
		;; Track control to confirm selection.
		;; NOTE: Only a mouseDown requires a mouse track.

		(if (== mouseDown (event type?))
			(= lastIn 0)
			(repeat
				(= event (Event new: leaveIt))
				(event localize:)
				(= in (self check: event))
				(if (!= in lastIn)
					(HiliteControl self)
					(= lastIn in)
				)
				(event dispose:)
				(breakif (not (StillDown)))
			)

			(if in
				(HiliteControl self)
			)
			(return in)
		else
			(return self)
		)
	)

	(method (isType theType)
		;; Return TRUE if this DItem is of type theType.

		(return (== type theType))
	)

	(method (checkState bit)
		(return (& state	bit))
	)

 	(method (doit)
		;; Default method is to return value.
		;; Will be superceded by user's instances.

		(return value)
	)

	(method (setSize)
		;; Set the item's rectangle.  Responsibility of subclasses.
	)

	(method (move h v)
		;; Move item BY h v.

		(+= nsRight  	h)
		(+= nsLeft  	h)
		(+= nsTop  		v)
		(+= nsBottom  	v)
	)

	(method (moveTo h v)
		;; Move item TO h v.

		(self move: (- h nsLeft) (- v nsTop))
	)

	(method (draw)
		;; Draw self per kernel definition.

		(DrawControl self)
	)

	; do something on each cycle through the dialog's doit
	(method (cycle)

	)
)



(class DText 	kindof DItem
	;;; A non-editable, generally non-selectable text field.

	(properties
		type	dText
		text	0					;the text in the field
		font	USERFONT			;font to use for print text
		mode	teJustLeft		;possible alignment of text
									;	teJustLeft		left justified
									;	teJustRight		right justified
									;	teJustCenter	center each line
		rects	NULL				;pointer to array of rectangle points
	)


	(method (dispose isNotPtr)
		(if (and	text
					(or	(not argc)
							(not isNotPtr)
					)
				)
			(Memory MDisposePtr (self text?))
		)
		(if rects
			(Memory MDisposePtr (self rects?))
		)
		(super dispose:)
	)

	(method (new &tmp newText)
		(return
			((super new:)
				font: 		userFont,
				yourself:
			)
		)
	)

	(method (setSize w &tmp [r 4])
		;; If w arg is present it is the fixed width of the text rectangle.

		(TextSize @[r 0] text font (if argc w else 0))
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft [r 3]))
	)

	(method (handleEvent event &tmp rect t l b r)
		;
		; Check for the existence of rectangles, indicating interactive text.
		;	If found, see if the event is in a rectangle, and if so pass that
		;	rectangle's number to any textCode that may exist.

 	 	(if (and	textCode
					rects
					(or	(OneOf (event type?) mouseDown joyDown)
							(and	(== (event type?) keyDown)
									(== (event message?) ENTER)
							)
					)
			)
 	 		(= rect -1)

 	 		(event
				globalize:	,
				claimed:		TRUE
			)
			(while (!= (WordAt rects (+ rect 1)) $7777)
				(= l (WordAt rects (++ rect)))
				(= t (WordAt rects (++ rect)))
				(= r (WordAt rects (++ rect)))
				(= b (WordAt rects (++ rect)))
				(if (and	(<= l (event x?) r)
							(<= t (event y?) b)
					)
					(textCode doit: (/ rect 4))
					(event
						type:		nullEvt,
						claimed: FALSE
					)
					(break)
				)
			)
		)
		(super handleEvent: event)
	)

	(method (draw)
		;
		; Only overridden so we can get the address to any rectangles
		;	that may have been created.

		(= rects (DrawControl self))
	)
)



(class Dialog kindof List

	;;; ACTIVE controls can be selected, INACTIVE ones can't.
	;;; EXIT controls return ID when selected.  NON-EXIT controls invoke DOIT.
	;;; All controls will show VISUAL evidence of selection.
	;;; Selection via a mouse click requires a track.

	(properties
		text 				0 		; title
		font				0
		window 			0		; pointer to open window
		theItem 			0		; objID of "current" item
		nsTop				0
		nsLeft 			0
		nsBottom			0
		nsRight 			0
		time 				0
		caller 			NULL	; who to cue upon disposal
		
		seconds 			0		; the number of seconds to wait before changing state
		lastSeconds 	0		; private variable
		eatTheMice		0		; how long to wait before allowing events
		lastTicks		0		; used with eatTheMice
	)

;;;	(methods
;;;		open				; get a window to live in
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


	(method (open wtype pri)
		;
		; Create the window & get ourselves on the screen

		(if (and (PicNotValid) cast)
			(Animate (cast elements?) FALSE)
		)

		;; Get a new window for this dialog.
		; operate with a clone of provided window
		(= window (window new:))
		(window
			top: 			nsTop, 
			left:			nsLeft,
			bottom: 		nsBottom,
			right: 		nsRight,
			title: 		text,
			type: 		wtype,
			priority: 	pri,
			open:			
		)

		(= seconds time)
		(self draw:)
	)

	(method (draw)
		;
		; Draw contents of dialog

		(self eachElementDo: #draw:)
	)

	(method (doit def &tmp event ret done)
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

		(self eachElementDo: #init:)

		; If def is not passed or zero, we pick first active item, after first
		;	unmarking the current item (if any)
		(if theItem
			(theItem select: FALSE)
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

		; Mark this item (if any)
		(if theItem
			(theItem select: TRUE)
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
			(= event ((Event new:) localize:))

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

			; See if we've moved the selected item
			(self eachElementDo: #perform checkHiliteCode self event)

			; Everyone gets a shot at the event...
			(= ret (self handleEvent: event))

			; Get rid of the event we made
			(event dispose:)

			; If we're being timed, see if we should dispose yet
			(if (self check:)
				(break)
			)

			; If there are no active items, the user pressed the ENTER key, or
			;	clicked the mouse we return with no value
 			(if (== ret -2)
				(= ret 0)
				(EditControl theItem 0)
				(break)
			)
	
			(Wait 1)
		)

		(return ret)
	)

	(method (check &tmp thisSeconds)
		;
		; Check to see if we can be disposed yet
			
		(if seconds
			(= thisSeconds (GetTime SYSTIME1))
			(if (!= lastSeconds thisSeconds)
				(= lastSeconds thisSeconds)
				(return (not (-- seconds)))
			)
		)
	)
		
	(method (dispose &tmp theCaller)
	 	;
		; Dispose of dialog & its stuff

		(self eachElementDo: #dispose:, release:)

		; Set system global for the current modeless dialog to zero if it is us
		(if (== self modelessDialog)
			(SetPort modelessPort)
			(= modelessDialog 0)
			(= modelessPort 0)
		)

		; Get rid of the window (if any)
		(if window
			(window dispose:)
			(= window 0)
		)

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
			(theItem select: FALSE)

			; we need the node value that we are
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self next: node)))
					(= node (self first:))
				)
				(= theItem (NodeValue node))

				; we break on next active item
				(if (& (theItem state?) dActive)
					(break)
				)
			)
					
			(theItem select: TRUE)
			(theGame setCursor: 
	  			theCursor
	  			TRUE
	  			(+ (theItem nsLeft?) (/ (- (theItem nsRight?) (theItem nsLeft?)) 2))
	  			(- (theItem nsBottom?) 3)
			)
		)
	)

	(method (retreat &tmp obj node)
		;
		; Highlight previous ACTIVE item in list

		(if theItem
			; clear current item
			(theItem select: FALSE)

			; we need the node value that we are
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self prev: node)))
					(= node (self last:))
				)
				(= theItem (NodeValue node))

				; we break on next active item
				(if (& (theItem state?) dActive)
					(break)
				)
			)

			(theItem select: TRUE)
			(theGame setCursor:
				theCursor
				TRUE
				(+ (theItem nsLeft?) (/ (- (theItem nsRight?) (theItem nsLeft?)) 2))
				(- (theItem nsBottom?) 3)
			)
		)
	)

	(method (handleEvent event &tmp ret eType eMsg)
		;
		; Respond to the passed event with ID, -1, or -2

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
			(EditControl theItem 0)

			; If NOT marked EXIT we doit: and advance to next
			(if (not (ret checkState: dExit))
				(if theItem
					(theItem select: FALSE)
				)
				((= theItem ret) select: TRUE)												

				; Send "doit" to object to perform any subclass-specific stuff
				(ret doit:)

				; don't report it
				(= ret 0)
			; Else we exit
			else
				(return ret)	;(self dispose:)
			)
		else
			; Refresh our temps
			(= eType (event type?))
			(= eMsg  (event message?))

			; Check for standard conventions
			(= ret 0)
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
					(EditControl theItem 0)
					(event claimed: TRUE)
				)

				; ESC key pressed

				((and	(== eType keyDown)
						(== eMsg ESC)
					)
					(event claimed: TRUE)
					(= ret -1)
				)

				; Any event exits if no active items

				((and	(not 	(self firstTrue: #checkState: dActive))
				 		(or	(and	(== eType keyDown)
				 						(== eMsg ENTER)
				 				)
				 				(OneOf eType mouseDown joyDown)
				 		)
					)
					(event claimed: TRUE)
					(= ret -2)		
				)

				; If theItem is of dEdit and we pressed a right-arrow

				((and (IsObject theItem)
						(theItem isType: dEdit)	
						(== eType keyDown)
						(== eMsg  RIGHTARROW)
					)
					(if (>= (theItem cursor?) (StrLen (theItem text?)))
						(self advance:)
					else
						(EditControl theItem event)
					)
				)

				; If theItem is of dEdit and we pressed a left-arrow

				((and (IsObject theItem)
						(theItem isType: dEdit)	
						(== eType keyDown)
						(== eMsg  LEFTARROW)
					)
					(if (<= (theItem cursor?) 0)
						(self retreat:)
					else
						(EditControl theItem event)
					)
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

				(else
					(EditControl theItem event)
				)
			)
		)
		(return ret)
	)

	(method (move h v)
		;
		; Move dialog relative to current position

		(+= nsRight		h)
		(+= nsLeft  	h)
		(+= nsTop  		v)
		(+= nsBottom	v)
	)

	(method (moveTo h v)
		;
		; Move dialog to absolute position

		(self move: (- h nsLeft) (- v nsTop))
	)

	(method (center)
		;
		; Center the dialog on the screen

		(self moveTo:
			(+ (window brLeft?) (/ (- (- (window brRight?) (window brLeft?)) (- nsRight nsLeft)) 2))
			(+ (window brTop?) (/ (- (- (window brBottom?) (window brTop?)) (- nsBottom nsTop)) 2))
		)	
	)

	(method (setSize &tmp node obj [r 4])
		;
		; Determine the required dimensions to encompass all items

		(if text
			; get textsize in the font without word wrapping
			(TextSize @[r 0] text font -1 NULL)
			(= nsTop 	[r 0])
			(= nsLeft 	[r 1])
			(= nsBottom [r 2])
			(= nsRight 	[r 3])
		else
			(= nsTop 	0)
			(= nsLeft 	0)
			(= nsBottom 0)
			(= nsRight	0)
		)
		(for	((= node (self first:)))
				node
				((= node (self next: node)))

			(= obj (NodeValue node))

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

		; add a border of MARGIN pixels.
		(+= nsRight MARGIN)
		(+= nsBottom MARGIN)
		(self moveTo: 0 0)
	)
)


(instance checkHiliteCode of Code
	(method (doit who dlg event)
		(if (and	(& (who state?) dActive)
					(who check: event)
		  			(not (& (who state?) dSelected))
		 	)
			((dlg theItem?) select: FALSE)
			(dlg theItem: who)
			(who select: TRUE)
		)
	)
)
