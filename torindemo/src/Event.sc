;;; Sierra Script 1.0 - (do not remove this comment)
(script# EVENT)
(include game.sh)
(use System)


(class Event kindof Object
	;;; The Event class is the class of user input events (key presses,
	;;; mouse clicks, etc.)
	
	(properties
		type 			0 		;the type of event (Event types in system.sh)
		message 		0		;the value of the event (e.g. ASCII value of key)
		modifiers 	0		;modifiers of event (shiftDown, ctrlDown, altDown)
		x 				0		;x coord of mouse when event occurred
		y 				0		;y coord of mouse when event occurred
		z				0		;z coord of mouse		\
		yaw			0		;yaw value of mouse   \
		pitch			0		;pitch value of mouse  > 6-way mice only
		roll			0		;roll value of mouse  /
		claimed 		0		;TRUE if the event has been responded to
		plane			0		;current plane to which we are local (0 = global)
	)
;;;	(methods
;;;		new				; get cloned new event and call kernel to fill it in
;;;		localize			; make X/Y point into local coords
;;;		globalize		; make X/Y point into global coords
;;;	)
	
	(method (new mask &tmp event)
		(= event (super new:))
		(GetEvent
			(if argc	mask else allEvents)
			event
		)
		(return event)
	)

	(method (localize toPlane)
		;
		; Make the mouse coordinates relative to the plane specified

		(if plane
			(LocalToGlobal self plane)
		)
		(GlobalToLocal self (= plane toPlane))
		(return self)
	)

  	(method (globalize)
		;
		; Make the mouse coordinates global to the screen

		(if plane
			(LocalToGlobal self plane)
			(= plane 0)
		)
		(return self)
	)
)
