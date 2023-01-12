;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	INTRFACE.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Bob Heitman
;;;;
;;;;	Classes which implement the user interface of SCI.
;;;;
;;;;	Classes:
;;;;		MenuBar
;;;;		Dialog
;;;;		Item
;;;;		DItem
;;;;		DText
;;;;		DIcon
;;;;		DButton
;;;;		DEdit
;;;;		DSelector
;;;;		Controls
;;;;
;;;;	Procedures:
;;;;		Print
;;;;		ShowView
;;;;		GetInput
;;;;		GetNumber
;;;;		Printf
;;;;		StillDown

(script# INTRFACE)
(include game.sh)
(include language.sh)
(use Main)
(use System)

;;;(procedure
;;;	Print
;;;	ShowView
;;;	GetInput
;;;	GetNumber
;;;	Printf
;;;	StillDown
;;;	MousedOn
;;;)

(public
	Print			0
	ShowView		1
	GetInput		2
	GetNumber	3
	Printf		4
	MousedOn		5
)



(procedure (StillDown &tmp event ret)
	;;; Return true if there is no mouse up in queue.

	(= event (Event new:))
	(= ret (!= (event type?) mouseUp))
	(event dispose:)
	(return ret)
)

(procedure (MousedOn obj event)
	(return
		(and
			(< (obj nsLeft?) (event x?) (obj nsRight?))
			(< (obj nsTop?)  (event y?) (obj nsBottom?))
		)
	)
)

;(procedure (MousedOn obj event theMods distance)
;	(return
;		(cond
;			((or
;				(!= (event type) mouseDown)
;				(and
;					(obj respondsTo:#signal)
;					(& (obj signal?) actorHidden)
;				)
;			 )
;				FALSE
;			)
;			((and
;				(>= argc 3)
;				theMods
;				(== (& (event modifiers) theMods) 0)
;			 )
;				FALSE
;			)
;			((obj respondsTo:#nsLeft)
;				(InRect 
;					(obj nsLeft?) (obj nsTop?) (obj nsRight?) (obj nsBottom?)
;					event
;				)
;			)
;;			((obj respondsTo:#x)
;;				(<= 
;;					(GetDistance 
;;						(event x?)
;;						(event y?)
;;						(obj x?) 
;;						(- (obj y?) (obj z?))	;apparent y-coordinate
;;					) 
;;					(if (>= argc 4) distance else 10)
;;				)
;;			)
;		)
;	)
;)

(class MenuBar kindof Object
	;;; The menubar is a two dimensional array maintained solely by the kernel.
	;;; This class, along with some explicit kernel calls, implements an
	;;; interface to the kernel menubar.

	(properties
		name 0			;we don't want to waste storage on this object's name
		state 0			;is the menu bar enabled?
	)


;;;	(methods
;;;		draw				;draw the menubar
;;;		hide				;hide the menubar
;;;		handleEvent		;send an Event to the menubar
;;;		add				;add a menu to the menubar
;;;	)


	(method (draw)
		;; Draw the menubar as it currently exists.
		(= state TRUE)
		(DrawMenuBar TRUE)
	)


	(method (hide)
		;; Hide the area of the menu.
		(DrawMenuBar FALSE)
	)


	(method (add)
		;; Add a menu to menu bar.
		(AddMenu &rest)
	)


	(method (handleEvent event &tmp retVal oldRepeat)
		;; Respond to user events (if enabled).
		(= retVal 0)
		(if state
			(= oldRepeat (Joystick JoyRepeat 30))
			(= retVal (MenuSelect event &rest))
			(Joystick JoyRepeat oldRepeat)
		)
		(return retVal)
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
;;;		enable				; set/reset active bit in state
;;;		select				; set/reset selected bit in state
;;;		handleEvent			; determine if user input is yours
;;;		check					; check event x/y in rectangle
;;;		track					; select confirmation
;;;		doit					; instantiated for each subclass/control
;;;		setSize		; instantiated in each type of item
;;;		move			; relative move
;;;		moveTo		; absolute move
;;;		draw			; draw self in proper manner
;;;		setMark		; set default marking
;;;		isType		; return equality of type and argument
;;;		checkState	; do a bit test of argument
;;;		cycle			; do something cyclic
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
					; said your name
					(and (== evtType saidEvent) (Said said))

					; pressed your key
					(and (== evtType keyDown) (== (event message?) key))

					; clicked in box
					(and (== evtType mouseDown) (self check: event))
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

		(+= nsRight  h)
		(+= nsLeft  h)
		(+= nsTop  v)
		(+= nsBottom  v)
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
		type dText
		text 0					;the text in the field
		font USERFONT			;font to use for print text
		mode teJustLeft		;possible alignment of text
									;	teJustLeft		left justified
									;	teJustRight		right justified
									;	teJustCenter	center each line
	)


	(method (new &tmp newText)
		(return ((super new:) font: userFont, yourself:))
	)

	(method (setSize w &tmp [r 4])
		;; If w arg is present it is the fixed width of the text rectangle.

		(TextSize @[r 0] text font (if argc w else 0) {\r----------\r}) ;LANGSEPARATOR)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft [r 3]))
	)
)




(class DIcon 	kindof DItem
	;;; Icons are simply a view/loop/cel combination created by the view
	;;; editor VE.  They are generally not selectable.

	(properties
		type dIcon
		view 0			; view number
		loop 0			; loop number
		cel 0				; cel number
	)

	(method (setSize &tmp [r 4])
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
)


(define	BMOD 16)		; width equalizer for buttons

(class DButton	kindof DItem
	;;; Buttons are selectable items which a user clicks in with the mouse
	;;; or selects with the TAB key and ENTER in order to execute an action.

	(properties
		type dButton
		state (| dActive dExit)
		text 0							;text displayed inside button
		font SYSFONT					;should usally be left as the system font
	)


	(method (setSize &tmp [r 4])
;		(define	BMOD 16)		; width equalizer for buttons

		(TextSize @[r 0] text font 0 NULL)

		; a button box is one pixel larger all around
		(+= [r 2] 2)
		(+= [r 3] 2)
		(= nsBottom (+ nsTop [r 2]))
		(= [r 3] (* (/ (+ [r 3] (- BMOD 1)) BMOD) BMOD))
		(= nsRight (+ [r 3] nsLeft))
	)
)




(class DEdit	kindof DItem
	;;; A text field which is editable by the user.

	(properties
		type dEdit
		state dActive
		text 0				;default text when the edit item is drawn
		font SYSFONT		;this is often changed to a user font
		max 0					;maximum number of characters allowed in field
		cursor 0				;cursor position in field
	)


	(method (track evt)
		(EditControl self evt)
		(return self)				;used to return 0, see Corey
	)


	(method (setSize &tmp [r 4])
		;; Size and set cursor position to the end of the text.

		; box is as sized by max * width of an "M"
		(= font inputFont)
		(TextSize @[r 0] {M} font 0 NULL)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft (/ (* [r 3] max 3) 4)))
		(= cursor (StrLen text))
	)

)




(class DSelector kindof DItem
	;;; Selectors are a list of text items which can be scrolled.  The user
	;;; selects one of the items either by clicking directly on the item
	;;; or by scrolling a high-lighted bar to the selection.

	(properties
		type dSelector
		state 0
		font 0
		x 20				; width of text item (in characters)
		y 6				; number of items displayed in selector
		text 	0			; the text items to be selected from
		cursor 0			; the currently selected item
		topString 0		; first line of text shown
		mark	0			; the LINE of selector that is selected
	)

;;;	(methods
;;;		indexOf			; return index of this string
;;;		at					; return ptr to this index
;;;		advance			; move selector bar up
;;;		retreat			; move selector bar up
;;;	)


	(method (indexOf what &tmp ptr i)
		;; Return index of this string OR -1.

		(= ptr text)
		(for ((= i 0)) (< i 300) ((++ i))

			; check for end of data
			(if (== 0 (StrLen ptr))
				(return -1)
			)
			(if (not (StrCmp what ptr))
				(return i)
			)
			(+= ptr x)
		)
	)


	(method (at what)
		;; Return pointer to this index OR 0.

		(return (+ text (* x what)))
	)


	(method (setSize &tmp [r 4])
		(TextSize @[r 0] {M} font 0 NULL)
		(= nsBottom (+ nsTop 20 (* [r 2] y)))
		(= nsRight (+ nsLeft (/ (* [r 3] x 3) 4)))
		(= topString (= cursor text))
		(= mark 0)
	)


	(method (retreat lines &tmp redraw)
		;; Retreat requested (or to top) lines.

		(= redraw FALSE)
		(while lines
			; are we at top?
			(if (!= cursor text)
				(= redraw TRUE)
				(-= cursor x)

				; do we scroll up?
				(if mark
					(-- mark)
				else
					(-= topString x)			
				)
				(-- lines)
			else
				(break)
			)
		)
		(return
			(if redraw
				(self draw:)
				TRUE
			;;else FALSE
			)
		)
	)


	(method (advance lines &tmp redraw)
		;; Advance requested (or to end) lines.

		; if empty, return
		(if (not (StrAt cursor 0))
			(return)
		)

		(= redraw FALSE)
		(while lines
			; is there another line?
			(if (StrAt cursor x)
				(= redraw TRUE)
				(+= cursor x)
	
				; do we scroll?
				(if (< (+ mark 1) y)
					(++ mark)
				else
					(+= topString x)
				)

				(-- lines)
			else
				(break)
			)

		)
		(return
			(if redraw
				(self draw:)
				TRUE
			;;else FALSE
			)
		)
	)


	(method (handleEvent event &tmp ret evtType evt newEvt i [r 4])
		;; Selectors are not really active so they always return 0,
		;; but they may claim the event.

		(if (event claimed?) (return 0))

		; remap some directions into arrows
;		(if (& (event type?) direction)
;			(event type:keyDown)
;			(switch (event message?)
;				(dirS
;					(event message:DOWNARROW)
;				)
;				(dirN
;					(event message:UPARROW)
;				)
;				(else
;					(event type:direction)
;				)
;			)
;		)				

		(= ret 0)
		(switch (event type?)
			(keyDown
				(event claimed:
					(switch (event message?)
						(HOMEKEY
							(self retreat: 50)
						)
						(ENDKEY
							(self advance: 50)
						)
						(PAGEDOWN
							(self advance: (- y 1))
						)
						(PAGEUP
							(self retreat: (- y 1))
						)
						(DOWNARROW
							(self advance: 1)
						)
						(UPARROW
							(self retreat: 1)
						)
						(else
							FALSE
						)
					)
				)
			)
			(mouseDown
				(if (self check: event)
					(event claimed:TRUE)

					; determine sub part
					(cond
						; top bar
						((< (event y?) (+ nsTop 10))
							(repeat
								(self retreat: 1)
								(breakif (not (StillDown)))
							)
						)

						; bottom bar
						((> (event y?) (- nsBottom 10))
							(repeat
								(self advance: 1)
								(breakif (not (StillDown)))
							)
						)

						; it is in the center
						(else
							; determine line height
							(TextSize @[r 0] {M} font 0 NULL)
							(= i (/ (- (event y?) (+ nsTop 10)) [r 2]))

							(if (> i mark)		
								; need to advance
								(self advance: (- i mark))
							else
								; need to retreat
								(self retreat: (- mark i))
							)
						)									
					)
				)
			)
		)			
		(return  (if (and (event claimed?) (& state dExit)) self  else 0))
	)
)




(class Dialog kindof List
	;;; ACTIVE controls can be selected, INACTIVE ones can't.
	;;; EXIT controls return ID when selected.  NON-EXIT controls invoke DOIT.
	;;; All controls will show VISUAL evidence of selection.
	;;; Selection via a mouse click requires a track.

	(properties
		text 0		; title
		elements	0	; list of items
		window 0		; pointer to open window
		theItem 0	; objID of "current" item
		nsTop	0
		nsLeft 0
		nsBottom	0
		nsRight 0
		time 0
		busy	0		; timer dispose lockout
		caller NULL	; who to cue upon disposal
		
		;; properties below allow us to do without timers. --Pablo
		seconds 0		;the number of seconds to wait before changing state
		lastSeconds 0	;private variable
	)

;;;	(methods
;;;		new			; get one of me
;;;		open			; get a window to live in
;;;		draw			; actually show on screen
;;;		doit			; get user input to me
;;;		cue			; respond to time expiration
;;;		dispose		; close dialog
;;;		advance		; advance to next in list
;;;		retreat		; retreat to previous in list
;;;		move			; relative move from current position
;;;		moveTo		; go to absolute position (upper/left)
;;;		center		; center in screen
;;;		setSize		; make me big enough
;;;		handleEvent	; respond to an event
;;;		check			; cue if time is up
;;;	)


	(method (open wtype pri)
		(if (and (PicNotValid) cast)
			(Animate (cast elements?) FALSE)
		)

		;; Get a new window for this dialog.
		; operate with a clone of provided window
		(= window (window new:))
		(window
			top: nsTop, 
			left:nsLeft,
			bottom: nsBottom,
			right: nsRight,
			title: text,
			type: wtype,
			priority: pri,
			open:
		)

		(= seconds time)
		;;(if time
		;;	(Timer setReal: self time)
		;;)

		; draw the items
		(self draw:)
	)


	(method (draw)
		;;; Draw contents of dialog.
		(self eachElementDo: #draw:)
	)




	(method (doit def &tmp done event ret eatTheMice lastTick)
		;; Do this dialog with default obj for RETURN.

		;; If there are NO active items then ANY event exits.
		;; Pressing ESC returns 0.
		;; Only ACTIVE items may be SELECTED.
		;; Only EXIT items are reported to caller.
		;; Pressing ENTER returns the currently SELECTED object IF it is EXIT.
		;; Pressing TAB advances to next ACTIVE item.
		;; Pressing SHIFT/TAB advances to previous ACTIVE item.

		(= done 0)

		; tell the timer we are busy
		(= busy 1)

		(self eachElementDo: #init:)

		; if def is not passed or zero, we pick first active item
		; unmark last default
		(if theItem
			(theItem select:FALSE)
		)
		(= theItem
			(if (and argc def)
				def
			else
				(self firstTrue: #checkState: dActive)
			)
		)

		; mark this item (if not NULL)
		(if theItem
			(theItem select:TRUE)
		)

		; if no active items we eat mouseDown for a one half second
		(if (not theItem)
			(= eatTheMice eatMice)
			(= lastTick (GetTime))
		else
			(= eatTheMice 0)
		)

		; get events and act upon them
		( = ret 0)
		(while (not ret)
;;;			(= gameTime (+ tickOffset (GetTime)))
			; call everyones cycler
			(self eachElementDo: #cycle:)
			
			; get an event and give everyone a shot at it
			(= event ((Event new:) localize:))

			(if eatTheMice
				(-- eatTheMice)
				(if (== (event type?) mouseDown)
					(event type:0)
				)

				(while (== lastTick (GetTime))
					(continue)
				)
				(= lastTick (GetTime))
			)

			(= ret (self handleEvent: event))

			; get rid of the event we got
			(event dispose:)

			(self check:)	;see if our time is up, if so dispose (maybe)


			; this is ESC or INACTIVE or TIMER said get unbusy
 			(if (or (== ret -1) (not busy))
				(= ret 0)
				(EditControl theItem 0)
				(break)
			)		
		
			(Wait 1)
		)					

		; tell the timer we can be killed
		(= busy 0)

		(return ret)
	)

	(method (check &tmp thisSeconds)
			
		;;following code replaces timer
		;;invoked from dialog doit for normal dialogs
		;;or from game doit for modelessDialogs
		;;--Pablo
		
		(if seconds
			(= thisSeconds (GetTime SYSTIME1))
			(if (!= lastSeconds thisSeconds)
				(= lastSeconds thisSeconds)
				(if (not (-- seconds))
					(self cue:)
				)
			)
		)
	)
		
	(method (cue)
		(if (not busy)
			(self dispose:)
		else
			(= busy 0)
		)
	)


	(method (dispose &tmp theCaller)
		;; Dispose of dialog and its window.

		;; set system global to zero if it is us
		(if (== self modelessDialog)
			(SetPort modelessPort)
			(= modelessDialog 0)
			(= modelessPort 0)
		)

		(if window
			(window dispose:)
		)
		(= window 0)

		;; dispose of any timer we might have
		;;(if timer
		;;	(timer dispose:, delete:)
		;;)

		;; clear the currently selected item
		(= theItem 0)

		;; this is clearly a kludge, but a dispose/delete 
		;; like actors etc. is unacceptable

		(= theCaller caller)
		(super dispose:)
		(if theCaller
;;;			(if (theCaller isKindOf: Script)
;;;				(theCaller cycles: 1)
;;;			else
				(theCaller cue:)
;;;			)
		)
	)


	(method (advance &tmp obj node)
		;; Private.  Move to next ACTIVE item in list.

		(if theItem
			;; clear this one
			(theItem select: FALSE)

			;; we need the node value that we are
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
			(theGame 
				setCursor: 
					theCursor	
					TRUE
					(+ 
						(theItem nsLeft?) 
						(/ 
							(- 
								(theItem nsRight?) 
								(theItem nsLeft?)
							) 
							2
						)
					) 
					(- (theItem nsBottom?) 3)
			)
		)
	)


	(method (retreat &tmp obj node)
		;; Private.  Move back one ACTIVE item.
		(if theItem
			; clear this one
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
			(theGame 
				setCursor: 
					theCursor	
					TRUE
					(+ 
						(theItem nsLeft?) 
						(/ 
							(- 
								(theItem nsRight?) 
								(theItem nsLeft?)
							) 
							2
						)
					) 
					(- (theItem nsBottom?) 3)
			)
		)
	)


	(method (handleEvent event &tmp ret)
		;; Respond to the passed event with ID or null.
		; is it unclaimed or NOT mine?
		(if (& (event type?) direction)
			(event type:keyDown)
			(switch (event message?)
				(dirS
					(event message:DOWNARROW)
				)
				(dirN
					(event message:UPARROW)
				)
				(dirW
					(event message:LEFTARROW)	
				)
				(dirE
					(event message:RIGHTARROW)	
				)
				(else
					(event type:direction)
				)
			)
		)
		(if
			(or
				(event claimed?)
				(== (event type?) nullEvt)
				(and
					(!= mouseDown (event type?)) 
					(!= keyDown (event type?)) 
					(!= direction (event type?))
					(!= joyDown (event type?))
				)
			)
			(EditControl theItem event)
			(return 0)
		)

		; does this event belong to any in the list
		(if (= ret (self firstTrue: #handleEvent: event))
			(EditControl theItem 0)

			; If NOT marked EXIT we doit: and advance to next
			(if (not (ret checkState: dExit))
				(if theItem
					(theItem select:FALSE)
				)
				((= theItem ret) select:TRUE)												

				; Send "doit" to object to perform any subclass-specific stuff
				(ret doit:)

				; don't report it
				(= ret 0)
			)
		else
			; check standard conventions
			(= ret 0)
			(cond
				; return KEY pressed and theItem is active
				((and
					(or
						(== (event type?) joyDown)
						(and
							(== keyDown (event type?))
							(== ENTER (event message?))
						)
					)
					theItem
					(theItem checkState: dActive)
				 )
					(= ret theItem)
					(EditControl theItem 0)
					(event claimed:TRUE)
				)

				; ESC or any key or click exits if no active items
				((or
					(and
						(not (self firstTrue: #checkState: dActive))
						(or 
							(and (== keyDown (event type?)) (== ENTER (event message?))) 
							(== mouseDown (event type?))
							(== joyDown (event type?))
						)
					)
					(and (== keyDown (event type?)) (== ESC (event message?)))
				)
						; claim this event
						(event claimed:TRUE)

						; we are out of here
						(= ret -1)		
				)
				((and 
						(IsObject theItem)
						(theItem isType: dEdit)	
						(== (event type?) keyDown)
						(== (event message?) RIGHTARROW)
					)
					(if (>= (theItem cursor?) (StrLen (theItem text?)))
						(self advance:)
					else
						(EditControl theItem event)
					)
				)
				((and 
						(IsObject theItem)
						(theItem isType: dEdit)	
						(== (event type?) keyDown)
						(== (event message?) LEFTARROW)
					)
					(if (<= (theItem cursor?) 0)
						(self retreat:)
					else
						(EditControl theItem event)
					)
				)
				((and 
						(== keyDown (event type?)) 
						(OneOf (event message?)
							TAB 
							RIGHTARROW
							DOWNARROW 
						)
					)
					; claim this event
					(event claimed:TRUE)
					(self advance:)
				)
				((and 
						(== keyDown (event type?)) 
						(OneOf (event message?)
							SHIFTTAB 
							LEFTARROW
							UPARROW 
						)
					)
					; claim this event
					(event claimed:TRUE)
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
		;; Move dialog BY h v.

		(+= nsRight  h)
		(+= nsLeft  h)
		(+= nsTop  v)
		(+= nsBottom  v)
	)


	(method (moveTo h v)
		;; Move dialog TO h v.

		(self move: (- h nsLeft) (- v nsTop))
	)


	(method (center)
		;; Center the dialog in the screen.

		(self moveTo:
			(+ (window brLeft?) (/ (- (- (window brRight?) (window brLeft?)) (- nsRight nsLeft)) 2))
			(+ (window brTop?) (/ (- (- (window brBottom?) (window brTop?)) (- nsBottom nsTop)) 2))
		)	
	)


	(method (setSize &tmp node obj [r 4])
		;; Determine the required dimensions to encompass all items.

		(if text
			; get textsize in font 0 without word wrapping
			(TextSize @[r 0] text 0 -1 NULL)
			(= nsTop [r 0])
			(= nsLeft [r 1])
			(= nsBottom [r 2])
			(= nsRight [r 3])
		else
			(= nsTop 0)
			(= nsLeft 0)
			(= nsBottom 0)
			(= nsRight 0)
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

		;Add a border of MARGIN pixels.
		(+= nsRight MARGIN)
		(+= nsBottom MARGIN)
		(self moveTo:0 0)
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




(procedure (GetInput str maxLen prompt &tmp theDialog editI ret oldPause)
	(return
		(and
			(Print
				(if (>= argc 3) prompt else {})
				#edit: str maxLen
				&rest
			)
			(StrLen str)
		)
	)
)




(procedure (ShowView txt v l c)
	(Print txt #icon: v l c &rest)
)


	(define	MAXBUTTONS	5)

(procedure	(Print args
						&tmp theDialog textI iconI editI
						ret i atX atY fixWidth keepIt default curPort
						[buttons 6] buttonWide buttonsUsed butAtX
						[buffer 1000]
						jStr k jKanjiROMTextI
						mainI subI mainL subL
						widthGiven iconGiven
						kShift tempMod
						dontDispose
				)

	(= widthGiven FALSE)
	(= iconGiven FALSE)
	
;;;	(define	MAXBUTTONS	5)

	; initialize tmps	
	(= atX (= atY -1))

	(= mainL (theGame printLang?))
	(= subL (theGame subtitleLang?))
  
	(= dontDispose
		(= keepIt 
			(= fixWidth 
				(= buttonWide 
					(= iconI 
						(= editI 
							(= jKanjiROMTextI
								(= kShift
									(= buttonsUsed 0)))))))))
	

;	(= [buttons 0] 
;	(= [buttons 1]
;	(= [buttons 2]
;	(= [buttons 3]
;	(= [buttons 4]
;	(= [buttons 5] 0))))))


	; get a dialog to work with and give it the system window class
	((= theDialog (Dialog new:))
		window: systemWindow,
		name: {PrintD}
	)

	; TEXT parameter may be far (module/message#)
	(cond
		((u< [args 0] 1000)
			(GetFarText [args 0] [args 1] @buffer)
			(= i 2)
		)
		([args 0]
			(StrCpy @buffer [args 0])
			(= i 1)
		)
		(else
			(= buffer 0)
			(= i 0)
		)
	)

	; Search for #J or #j in string to mark japanese portion if any
	(for ((= k 0)) (StrAt @buffer k) ((++ k))
		(if (and
				(== (StrAt @buffer k) 14848) ;`#)
				(== (StrAt @buffer (+ 1 k)) `J)	;kanji
			)
			;convert japanese to all-kanji before we lose #J, no-op otherwise
			(theGame printLang: ENGLISH, subtitleLang:JAPANESE)
			(StrSplit @buffer @buffer {#J})	
			(theGame printLang: mainL, subtitleLang:subL)
			
			(StrAt @buffer k 0)			; null terminate the "Western" portion
			
			(if (OneOf JAPANESE mainL subL)
				((= jKanjiROMTextI (DText new:))
					text: (+ @buffer 2 k),	; point to alternate language
					font: 900,
					name: {jDText}
				)
			)
		)
	)
	
	
	((= textI (DText new:))
		text:@buffer, 
		font: userFont
	)

	;; a main item is mandatory
	(= mainI
		(if (and jKanjiROMTextI (== mainL JAPANESE))
			jKanjiROMTextI else textI
		)
	)
	
	;; a subtitle item is optional
	(= subI
		(cond
			((== subL JAPANESE)	
				jKanjiROMTextI									;could be NULL
			)
			(jKanjiROMTextI 		;we must have kanji MAIN text item
				(if subL
					textI
				else
					(textI dispose:)	;to avoid fragments
					(= textI NULL)		;also setting subI to NULL
				)
			)
			;;(else NULL)
		)
	)
				
	(mainI
		moveTo: MARGIN MARGIN, 
		setSize:
	)
	(theDialog add: mainI,setSize:)
	(if subI
		(subI 
			setSize:
			moveTo:	(mainI nsLeft?)	(+ MARGIN (mainI nsBottom?))
		)
		(theDialog add:subI,setSize:)
	)
	; the rest of the items/messages passed are optional
	(for ((= i i)) (< i argc) ((++ i))
		(switch [args i]
			(#mode
				(++ i)
				(if (and textI (not subI)) 
					(textI mode: [args i]) ;so kanji is never centered
				)
			)
			(#font
				(++ i)
				(if textI (textI font: [args i], setSize:fixWidth))
			)

			; main message width
			(#width
				(= widthGiven TRUE)
				(++ i)
				(= fixWidth [args i])
				(mainI setSize:fixWidth)
				(if subI 
					(subI 
						setSize:	fixWidth,
						moveTo:	(mainI nsLeft?)	(+ MARGIN (mainI nsBottom?))
					)
				)
			)
			; time is in seconds
			(#time	
				(++ i)
				(theDialog time: [args i])
			)
			(#title
				(++ i)
				(theDialog text: [args i])
			)
			(#at
				(++ i)
				(= atX [args i])
				(++ i)
				(= atY [args i])
			)

			; animate the cast list first
			(#draw
				(Animate (cast elements?) FALSE)
			)				

			(#edit
				(++ i)
				((= editI (DEdit new:)) text: [args i])
				(++ i)
				(editI max: [args i], setSize:)
			)

			; add a button
			(#button
				((= [buttons buttonsUsed] (DButton new:)) text:[args (++ i)], value:[args (++ i)], setSize:)
				(+= buttonWide (+ ([buttons buttonsUsed] nsRight?) MARGIN))
				(++ buttonsUsed)
			)

			; add optional icon to list
			; if first arg is an Object we add it
			(#icon
				(= iconGiven TRUE)
				(if (IsObject [args (+ i 1)])
					(= iconI ([args (+ i 1)] new:))
					(iconI setSize:)
					(+= i 1)
				else
					(= iconI (DIcon new:))
					(iconI 
						view:[args (+ i 1)],
						loop:[args (+ i 2)],
						cel:[args (+ i 3)],
						setSize:
					)
					(+= i 3)
				)
			)

			; let user dispose of me
			(#dispose
				(if (and (< (+ i 1) argc) (IsObject [args (+ i 1)]))
					(theDialog caller:[args (+ i 1)])
					(++ i)
				)
				(if (!= ALTLANG JAPANESE)
					(if modelessDialog
						(modelessDialog dispose:)
					) 
					(= keepIt theDialog)
				)
			)
			(#window
				(++ i)
				(theDialog window: [args i])
			)
			(#first
				(= dontDispose TRUE)
			)
		)
	)

	(if dontDispose
		(= keepIt 0)
	)

	(if
		(and
			(not (or widthGiven iconGiven))			;if not #width argument or icon
;**			subL											;using subtitles
			(> (- (theDialog nsBottom?) (theDialog nsTop?)) 100)	;check height
		)
		(mainI setSize: 300)
		(if subI 
			(subI 
				setSize:	300,
				moveTo:	(mainI nsLeft?)	(+ MARGIN (mainI nsBottom?))
			)
		)
	)
	
	; optional icon added to upper left
	(if iconI
		(iconI moveTo: MARGIN MARGIN)
		(if (or (== mainI jKanjiROMTextI) (== subI jKanjiROMTextI))
			(= kShift 8)
		)
		(if (& (iconI state?) dIconTop)
			(mainI moveTo: (+ MARGIN kShift) (+ (iconI nsBottom?) (mainI nsTop?)),
				setSize:
			)
		else
			(mainI moveTo: (+ MARGIN (iconI nsRight?) kShift) (mainI nsTop?),
				setSize:
			)
		)
		(if subI
			(subI moveTo:(mainI nsLeft?) (+ MARGIN (mainI nsBottom?)))
		)
		(theDialog add: iconI)
	)

	; size the dialog window based on current items
	(theDialog setSize:)

	; if editI we add it right under text item
	(if editI
		(editI
			moveTo: 
				((if subI else mainI) nsLeft?)
				(+ MARGIN ((if subI else mainI) nsBottom?))
			;((or subI mainI) nsLeft?)
			;(+ MARGIN ((or subI mainI) nsBottom?))
		)
		; add edit item and resize the dialog
		(theDialog add:editI, setSize:)
	)

	; add all buttons requested to bottom of dialog
	; from left to right in order encountered
	(= butAtX
		(if (> buttonWide (theDialog nsRight?))
			MARGIN
		else
			(- (theDialog nsRight?) buttonWide)
		)
	)

	(for ((= i 0)) (< i buttonsUsed) ((++ i))
		([buttons i] moveTo: butAtX (theDialog nsBottom?))

		; add button
		(theDialog add: [buttons i])
		(= butAtX (+ MARGIN ([buttons i] nsRight?)))
	)

	; re-size and center the dialog
	(theDialog setSize:, center:)

	; if there's not text, but an icon, or icon is on the top, center the icon.
	(if
		(or
			(and iconI (& (iconI state?) dIconTop))
			(and iconI (not (StrLen @buffer)))
		)
		(iconI moveTo:
			(/
				(-
					(- (theDialog nsRight?) (theDialog nsLeft?))
					(-	(iconI nsRight?) (iconI nsLeft?))
				)
				2
			)

			MARGIN
		)
	)

	; if we got an at we move there
	(theDialog 
		moveTo:
		 (if (== -1 atX) (theDialog nsLeft?) else atX)
		 (if (== -1 atY) (theDialog nsTop?) else atY)
	)

	(= curPort (GetPort))

	(theDialog open: (if (theDialog text?) wTitled else 0) (if keepIt 15 else -1))

	; return theDialog object for user to work with
	(if keepIt
		(= modelessPort (GetPort))
		(SetPort curPort)

		(return (= modelessDialog keepIt))
	else
		(sounds pause: TRUE)
	)

	; get a default item (first active in the list)
	; if NO "exit" items in list make this it
	(if (= default (theDialog firstTrue: #checkState: dActive))
		(if (not (theDialog firstTrue: #checkState: dExit))
			(default state: (| (default state?) dExit))
		)
	)

	(= ret (theDialog doit: default))

	; if ESC has been pressed we turn it into a zero
	(if (== ret -1)
		(= ret FALSE)
	)
	
	; if any of the buttons was pressed we return the item's value property
	(for ((= i 0)) (< i buttonsUsed) ((++ i))
		(if (== ret [buttons i])
			(= ret (ret value?))
			(break)
		)
	)

	; if we had no active ITEMS we force a TRUE return
	(if (not (theDialog theItem?))
		(= ret TRUE)
	)

		; dispose of the dialog and all its elements
	(theDialog dispose:)
	(sounds pause: FALSE)
	(return ret)
)




(procedure	(GetNumber string default &tmp [theLine 40])
	(= theLine 0)
	(if (> argc 1)
		(Format @theLine INTRFACE 0 default)
	)
	(return
		(if (GetInput @theLine 5 string)
			(ReadNumber @theLine)
		else
			-1
		)
	)
)



(procedure (Printf &tmp [str 500])
	(Format @str &rest)
	(Print @str)
)
