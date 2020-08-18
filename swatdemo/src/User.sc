;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	USER.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: 	Jeff Stephenson
;;;;	Updated: Brian K. Hughes
;;;;
;;;;	A User is an object which corresponds to the person playing the
;;;;	game and acts as the intermediary between the person and the
;;;;	other objects in the game.  In the current games there is only
;;;;	one User, and thus we use the class User rather than an instance
;;;;	of the class.
;;;;
;;;;	Classes:
;;;;		User
;;;;		OnMeAndLowY
;NOTE: This script has been specially modified for SwAT

(script#	USER)
(include game.sh)
(use Main)
(use System)


(instance uEvt of Event
	(method (new)
		(= type (= message (= modifiers (= y (= x (= claimed (= plane 0)))))))
		(GetEvent eventMask self)
		(return self)
	)
)


(class User kindof Object
	(properties
		alterEgo 		0					;the object ID of the Ego which User controls
		input 			0			  		;can the User type input?
		controls 		0					;boolean -- does User control alterEgo at present?
		prevDir 			0					;previous direction in which alterEgo was moving
		x					-1					; upper/left
		y					-1					; of user window
		mapKeyToDir 	TRUE				;map keys to dirs?
		curEvent			NULL				;pointer to	instance of Event
	)
	
;;;	(methods
;;;		canControl					;specifies whether user controls alterEgo
;;;		handleEvent
;;;		canInput						;made a method	(j.m.h.)
;;;	)
	
	(method (init)
		(= curEvent uEvt)
	)
	
	(method (canInput n)
		(if argc (= input n))
		(return input)
	)

	(method (doit)
		;; See if there is an event.  If none, just return.  Otherwise
		;; pass the event to other objects in the game to see if they
		;; want it.
		
		(curEvent new:)
		(self handleEvent: curEvent)
	)
	
	(method (handleEvent event &tmp eType eMsg eMod dir aObj theFeatures theCast)

		; The path that the event follows is:
		;
		;	   Talkers
		;        ³
		;     Prints
		;        ³
		;	 Speech Handler
		;        ³
		;        ÃÄ[direction]ÄÄÄÄÄÄ directionHandler
		;        ³                    ÃÄ[walk]Ä ego
		;        ³                    pmouse
		;			³
		;        ÃÄ[keyDown]ÄÄÄÄÄÄÄÄ keyDownHandler
		;			³
		;        ÃÄ[mouseDown/Up]ÄÄÄ mouseDownHandler
		;			³
		;        ÃÄ[extended mouse]Ä mouseDownHandler
		;		   ³
		;     IconBar
		;        ³
		;        ÃÄ[walkEvent]ÄÄÄÄÄÄ walkHandler
		;        ³                    ego
		;        ³
		;      Cast ÄÄÄÄÄ¿
		;        ³        ÃÄ (sorted features uses both these with OnMeAndLowY)
		;     Features ÄÄÙ
		;        ³
		;     Regions
		;        ³
		;      Game

		; Get current position of mouse cursor
		(= mouseX (event x?))
		(= mouseY (event y?))

		; Save some things in temps so we don't have to send messages constantly
		(= eType (event type?))
		(= eMod	(event modifiers?))


		; If there are talkers in the talkers list, process them
		(if (not (talkers isEmpty:))
			; Process all semi-modeless prints in a loop
			(repeat
				(= gameTime (+ tickOffset (GetTime)))
				(talkers
					eachElementDo: #doit,
  					firstTrue: 		#handleEvent: event
				)
				(sounds eachElementDo: #check:)
				(FrameOut)
				(breakif (talkers allTrue: #isModeless: DLG_MODELESS))
				(curEvent new:)
			)
		)

		; If there are prints in the prints list, process them
		(if (not (prints isEmpty:))
			; Process all semi-modeless prints in a loop
			(repeat
				(= gameTime (+ tickOffset (GetTime)))
	  			(prints
					eachElementDo:	#doit,
					firstTrue: 		#handleEvent: event
				)
				(sounds eachElementDo: #check:)
				(FrameOut)
				(breakif (prints allTrue: #isModeless: DLG_MODELESS))
				(curEvent new:)	  ;TMD32 added
			)
		)

		;SWAT hotspot code
		(HotSpotLowY init:)
		(if
			(and
				input
				(hotSpotList size:)
				(or
					(hotSpotList eachElementDo: #perform HotSpotLowY event)
					(HotSpotLowY theObj?)
				)
			)
			(if (!= theHotSpot (HotSpotLowY theObj?))
				(if theHotSpot
					(theHotSpot setCursor: 0)
				)
				(= theHotSpot
					((HotSpotLowY theObj?) setCursor: 1 yourself:)
				)
			)
		else
			(if (and theHotSpot (== theCursor hotSpotCursor))
				(theHotSpot setCursor: 0)
			)
			(= theHotSpot 0)
		)

		; The event already has a type (we clicked, keyed, joyed, etc)
		(if eType
			(= lastEvent event)
			

			; Convert key events to direction events, if appropriate, but
			;	remember what kind of event it was

			(if (and	mapKeyToDir
						(!= eType keyUp)
				)
				(MapKeyToDir event)
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


;OLD			; Give the event to the menu first, if one exists
;OLD
;OLD			(if theMenuBar 
;OLD				(theMenuBar handleEvent: event eType)
;OLD			)

			; Give the event to the speechHandler first, if one exists
;;;			(if (and	speechHandler
;;;						(speechHandler handleEvent: event)
;;;					)
;;;				(return TRUE)
;;;			)

			(event localize: (cast plane?))
			(= eType (event type?))
			(= eMsg	(event message?))

			(cond

				; Check for speech event

;;;				((& eType speechEvent)
;;;					(cond
;;;						((and	(== eMsg speechNoun)
;;;								(or	(= aObj (cast 			firstTrue: #perform findNoun))
;;;										(= aObj (features 	firstTrue: #perform findNoun))
;;;								)
;;;							)
;;;							(aObj doVerb: ((theIconBar curIcon?) message?))
;;;						)
;;;						((= aObj (theIconBar findIcon: eMsg))
;;;							(theIconBar select: aObj)
;;;							(theGame setCursor: (aObj cursor?))
;;;						)
;;;					)
;;;				)

				; Check for direction event

				((& eType direction)
					(cond
						((and	directionHandler
								(directionHandler handleEvent: event)
							)
							(return TRUE)
						)
						((and	(or	(and	theIconBar
												(==
													(theIconBar curIcon?)
													(theIconBar walkIconItem?)
												)
										)
										(not theIconBar)
								)
								alterEgo
								controls
								(cast contains: alterEgo)
								(alterEgo handleEvent: event)
							)
							(return TRUE)
						)
						((and pMouse
								(or	(not (& eType keyDown))
										(!= eMsg dirStop)
								)
								(pMouse handleEvent: event)
							)
							(return TRUE)
						)
					)
					(event localize: (cast plane?))
				)

				; Check for keyDown

				((and	(& eType keyDown)
						keyDownHandler
						(keyDownHandler handleEvent: event)
					)
					(return TRUE)
				)

				; Check for mouseDown or mouseUp

				((and	(& eType (| mouseDown mouseUp))
						mouseDownHandler
						(mouseDownHandler handleEvent: event)
					)
					(return TRUE)
				)
			)
		)

		; If nobody above claimed the event, we do these - regardless of whether
		;	we had an event type or not (null events go directly to here)

		; Send the event to the extended mouse handler
		(if (and	extMouseHandler
					(extMouseHandler handleEvent: event)
			)
			(return TRUE)
		)

		; Send the event to the iconbar, which may convert it to a userEvent or
		;	a walkEvent

;;;		(if theIconBar
;;;			(theIconBar handleEvent: event)
;;;		)
		(if theInterface
			(theInterface handleEvent: event)
		)

		(if (event claimed?)
			(return TRUE)
		)

		;SWAT code
		(if (and global110 (& (event type?) mouseEvt))
			(event globalize:)
			(OnPlaneAndHighPri init:)
			(List LEachElementDo (planes elements?) 99 OnPlaneAndHighPri event)
			(if
				(not
					(= aObj (OnPlaneAndHighPri theObj?))
				)
				(= aObj thePlane)
			)
			(= theCast (aObj casts?))
			(= theFeatures (aObj theFtrs?))
		else
			(= aObj thePlane)
		)
		(if (== aObj thePlane)
			(= theCast cast)
			(= theFeatures features)
		)
		(event localize: aObj)


		; Refresh our temps...
		(= eType (event type?))
		(= eMsg	(event message?))


		; If we don't have a null event, we continue with the rest of the chain
		;	so long as user is allowing us to input

		(if (and	input (& eType userEvent))
			(cond

				; Do we have a walkEvent?  If so, send it the the walkers list

				((and	(& eType walkEvent)
						walkHandler
						(walkHandler handleEvent: event)
					)
					(return TRUE)
				)

				; Do we have a walkEvent?  If so, send it to ego

				((and	(& eType walkEvent)
						(theCast contains: alterEgo)
						controls
						(alterEgo handleEvent: event)
					)
					(return TRUE)
				)

				; Are we using sorted features?  If so, determine who gets the
				;	event regardless of which list he's in

				(useSortedFeatures
					(OnMeAndLowY init:)
					(if (not (theCast isKindOf: Cast))
						(theCast
							eachElementDo: #eachElementDo 99 OnMeAndLowY event
						)
					else
						(theCast eachElementDo: #perform OnMeAndLowY event)
					)
					(theFeatures eachElementDo: #perform OnMeAndLowY event)
					(if (and (OnMeAndLowY theObj?)
								((OnMeAndLowY theObj?) handleEvent: event)
							)
						(return TRUE)
					)
				)

				; We're not using sorted features, so give the cast a shot first

				((theCast handleEvent: event)
					(return TRUE)
				)

				; We're not using sorted features, so give the features a shot

				((theFeatures handleEvent: event)
					(return TRUE)
				)
			)

			; Nobody in the cast or features claimed it, so try
			;	the regions

			(if (and (not (event claimed?))
						(regions handleEvent: event)
					)
			 	(return TRUE)
			)
		)


		; Finally, give the game a shot at the event, even if User can't input

		(if eType
			(cond
				((theGame handleEvent: event)
					(return TRUE)
				)
			)
		)

		(return FALSE)
	)
	
	
	(method (canControl value)
		;; Doing a (User canControl: FALSE) prevents the user from controlling
		;; the alterEgo using the mouse, arrow keys, etc.  (User canControl: TRUE)
		;; reinstates user control.
		
		(if argc
			(= controls value)
			(= prevDir 0)
		)
		(return controls)
	)
)


(class OnMeAndLowY of Code
	(properties
		theObj	NULL
		lastY		-1
	)
;;;	(methods
;;;		init
;;;		doit
;;;	)

	(method (init)
		(= theObj NULL)
		(= lastY -1)
	)

	(method (doit thisObj event)
		(if (and 
				(thisObj	onMe: event)
				(> (thisObj y?) lastY)
			)
			(= theObj thisObj)
			(= lastY	 (thisObj y?))
		)
	)
)

;SWAT hotspot classes
(class HotSpotLowY of Code
	(properties
		theObj 0
		lastY -1
	)
	
	(method (init)
		(= theObj 0)
		(= lastY -1)
	)
	
	(method (doit thisObj event)
		(if
			(and
				(thisObj checkEvent: event)
				(> (thisObj y?) lastY)
			)
			(= lastY ((= theObj thisObj) y?))
		)
		(return 0)
	)
)

(class OnPlaneAndHighPri of Code
	(properties
		scratch 0
		theObj 0
		lastPri -1
	)
	
	(method (init)
		(= theObj thePlane)
		(= lastPri 0)
	)
	
	(method (doit thisObj event)
		(if
			(and
				(thisObj onMe: event)
				(> (thisObj priority?) lastPri)
			)
			(= lastPri ((= theObj thisObj) priority?))
		)
	)
)

(instance findNoun of Code
	(method (doit theObj theNoun)
		(return (== (theObj noun?) theNoun))
	)
)
