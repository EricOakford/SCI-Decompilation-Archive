;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DITEM.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1993
;;;;
;;;;	Author: 	Bob Heitman
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	The base class for all dialog items.
;;;;
;;;;	Classes:
;;;;		DItem


(script# DITEM)
(include game.sh)
(use Actor)
(use System)


(class DItem kindof View
	;;; The superclass of all items of control in the user interface.

	(properties
		name		0			; don't waste storage on a name string
		type 		0			; the type of this control
		key		0			; key code associated with control
		value		0			; for programmers use
		signal	0			; no special situations for us
		object	0			; object to be invoked when selected
		selector	0			; selector of object to be invoked when selected
	)

;;;	(methods
;;;		enable			; set/reset active bit in state
;;;		hilite			; set/reset selected bit in state
;;;		handleEvent		; determine if user input is yours
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
;;;		updatePlane		; update my plane, if any
;;;	)

	(method (updatePlane)
	)

	(method (enable bool)
		;; Enable/disable this control.
		(if bool
			(|= state dActive)
		else
			(&= state (~ dActive))
		)
	)

	(method (hilite bool)
		;
		; Show this control as selected or not

		(if bool
			(|= state dSelected)
			(= cel 1)
			(UpdateScreenItem self)
		else
			(&= state (~ dSelected))
			(= cel 0)
			(UpdateScreenItem self)
		)
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
					(and 	(& evtType keyDown)
							(== (event message?) key)
					)

					; clicked in box
					(and 	(& evtType mouseDown)
							(self onMe: event)
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

	(method (onMe event)
		;
		; Return true if x, y in our rectangle

		(return
			(and
				(<= nsLeft (event x?) nsRight)
				(<= nsTop (event y?) nsBottom)
			)
		)
	)

 	(method (track)
		;
		; Track control to confirm selection.  Does nothing in base class.
	)

	(method (isType theType)
		;; Return TRUE if this DItem is of type theType.

		(return (== type theType))
	)

	(method (checkState bit)
		(return (& state bit))
	)

 	(method (doit)
		; Default method is to invoke a method, if specified, and/or return
		; 	our value.
		; Will be superceded by user's instances.

		(if object
			(Eval object selector)
		)
		(return value)
	)

	(method (setSize)
		;; Set the item's rectangle.  Responsibility of subclasses.
	)

	(method (move h v)
		;; Move item BY h v.

		(+= x				h)
		(+= y				v)
		(+= nsLeft  	h)
		(+= nsTop  		v)
		(+= nsRight  	h)
		(+= nsBottom  	v)

	)

	(method (moveTo h v)
		;; Move item TO h v.

		(self move: (- h nsLeft) (- v nsTop))
	)

	; do something on each cycle through the dialog's doit
	(method (cycle)
		(if (& -info- GRAPH_UPD)
			(UpdateScreenItem self)
		)
	)

	(method (show)
		(if (and	bitmap
					(not (self isNotHidden:))
			)
			(AddScreenItem bitmap)
		)
	)

	(method (hide)
		(if (and bitmap
					(self isNotHidden:)
			)
			(DeleteScreenItem bitmap)
		)
	)
)