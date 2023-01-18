;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	USER.SC
;;;;	(c) Sierra On-Line, Inc, 1988
;;;;
;;;;	Author: Jeff Stephenson
;;;;
;;;;	A User is an object which corresponds to the person playing the
;;;;	game and acts as the intermediary between the person and the
;;;;	other objects in the game.  In the current games there is only
;;;;	one User, and thus we use the class User rather than an instance
;;;;	of the class.
;;;;
;;;;	Classes:
;;;;		User


(script#	USER)
(include game.sh)
(use Main)
(use Motion)
(use PolyPath)
(use Actor)
(use System)

(define	INPUTLEN		45)
(define	INBUFSIZE	23) ;(+ (/ INPUTLEN 2) 1))

(local
	[inputLine INBUFSIZE]
	inputLen
)

(instance uEvt of Event)

(class User kindof Object
	(properties
		alterEgo 0					;the object ID of the Ego which User controls
		input 0						;can the User type input?
		controls 0					;boolean -- does User control alterEgo at present?
		echo SPACEBAR				;character to echo last input line
		prevDir 0					;previous direction in which alterEgo was moving
		prompt "Enter input"		;prompt for input window
		inputLineAddr 0			;address of User's input line
		x	-1							; upper/left
		y	-1							; of user window
		mapKeyToDir TRUE			;map keys to dirs?
		curEvent	NULL				;pointer to	instance of Event
		verbMessager NULL			;code that sets Event message on Said Events
	)
	
;;;	(methods
;;;		canControl					;specifies whether user controls alterEgo
;;;		getInput						;collects input from the user
;;;		said							;passes parsed user input to handleEvent methods
;;;		handleEvent
;;;		canInput						;made a method	(j.m.h.)
;;;	)
	
	(method (init inLine length)
		(= inputLineAddr (if argc inLine else @inputLine))
		(= inputLen (if (== argc 2) length else INPUTLEN))
		(= curEvent uEvt)
	)
	
	(method (canInput n)
		(if argc
;;;			(if pMouse (pMouse stop:))
			(= input n)
		)
		(return input)
	)

	(method (doit)
		;; See if there is an event.  If none, just return.  Otherwise
		;; pass the event to other objects in the game to see if they
		;; want it.
		
		(curEvent 
			type: 0			
			message: 0		
			modifiers: 0		
			y: 0				
			x: 0				
			claimed: 0		
			port:	0
		)
		(GetEvent allEvents curEvent)
		(self handleEvent: curEvent)
	)
	
	(method (handleEvent event &tmp evType dir)
		;** get current position of mouse cursor
		;; the iconbar needs to handle all (even null) events
		(= mouseX (event x?))
		(= mouseY (event y?))
		(if (event type?)
			(= lastEvent event)
			
			;Convert key events to direction events, if appropriate, but
			;remember what kind of event it was.
			(if mapKeyToDir
				(MapKeyToDir event)
			)
			(if (== (event type?) joyDown)
				(event 
					type: keyDown,
					message: 
						(if (& (event modifiers?) shiftDown)
						  	ESC
						else
							ENTER
						),
					modifiers:0
				)
			)

			(= evType (event type?))
			;Give the event to the menu first.
			(if theMenuBar 
				(theMenuBar handleEvent: event evType)
			)
			(event localize:)
			(cond
				;; could also have keyDown bit set
				((& (event type?) direction)
					(or
						(if pMouse
							(pMouse handleEvent: event)
						)
						(and
							directionHandler
							(directionHandler handleEvent: event)
						)
						(if (and alterEgo	controls)
							(alterEgo handleEvent: event)
						)
						(if theIconBar
							(theIconBar	handleEvent: event)
						)
					)
				)
				((== evType keyDown)
					(if keyDownHandler (keyDownHandler handleEvent: event))
				)
				((== evType mouseDown)
					(if mouseDownHandler	(mouseDownHandler handleEvent: event))
				)
			)
		)
		(if (not (event claimed?))
			(if theIconBar
				(theIconBar handleEvent: event)
;;;				(if (and
;;;					 	(== (event type?) userEvent)
;;;						(== (event message?) verbUse)
;;;						(not (theIconBar curInvIcon?))
;;;					)
;;;					(event claimed: TRUE)
;;;					(return)
;;;				)
			)
			(if (and (== (event type?)  userEvent)	input)
				(cond
					((and 
							(== (event message?) verbWalk) 
							controls 
							(alterEgo handleEvent: event)
						)
						TRUE
					)
					(useSortedFeatures
						(OnMeAndLowY init:)
						(cast 		eachElementDo: #perform: OnMeAndLowY event)
						(features	eachElementDo: #perform: OnMeAndLowY event)
						(if (OnMeAndLowY theObj?)
							((OnMeAndLowY theObj?) handleEvent: event)
						)
					)
					((cast 		handleEvent:event)
						TRUE
					)
					((features 	handleEvent:event)
						TRUE
					)
				)
				(cond
					((event claimed?)	TRUE)
					((regions handleEvent: event) TRUE)
				)
			)
			(if (and (event type?) (not (event claimed?)))
				(theGame handleEvent: event)
			)
		)
	)
	
	
	(method (getInput event &tmp ret)
;		;; Put up the input window and collect a line of input from the user.
;		
;		; if this is NOT a key event we zero out the inputLine
;		(if (!= (event type?) keyDown)
;			(= inputLine 0)
;		)
;		
;		;If this is not the echo character, replace the previous input
;		;line with the character which was passed.
;		(if (!= (event message?) echo)
;			(Format @inputLine "%c" (event message?))
;		)
;		
;		;Let the user edit the input line.
;		(if blocks
;			(Sound pause: TRUE)
;		)
;		(= ret (GetInput @inputLine inputLen prompt #at: x y))
;		(if blocks
;			(Sound pause: FALSE)
;		)
;		(return ret)
	)
	
	
	(method (canControl value)
		;; Doing a (User canControl:FALSE) prevents the user from controlling
		;; the alterEgo using the mouse, arrow keys, etc.  (User canControl:TRUE)
		;; reinstates user control.
		
		(if argc
;;;			(if pMouse (pMouse stop:))
			(= controls value)
			(= prevDir 0)
		)
		(return controls)
	)
	
	
	(method (said event &tmp theVerb)
;		;; Pass a said event parsed from user input to the various elements of
;		;; the game.
;		(event message:
;			(if verbMessager	(verbMessager doit:event))
;		)
;			
;		(if useSortedFeatures
;			(SortedAdd)
;		else
;			(sortedFeatures add: cast features)
;		)
;		
;		(if theMenuBar 
;			(sortedFeatures addToFront: theMenuBar)	;menu gets said second
;		)
;		(if firstSaidHandler 
;			(sortedFeatures addToFront: firstSaidHandler);
;		)															;then cast and features
;		(sortedFeatures 
;			addToEnd:	theGame,		;then room, regions, locales and game last
;			handleEvent:event,
;			release,
;		)
;		
;		;If the event was not claimed by anyone, invoke pragmaFail: to let
;		;the user know that it was not understood.
;		(if (and (== (event type?) saidEvent) (not (event claimed?)))
;			(theGame pragmaFail: @inputLine)
;		)
	)
)


(class Ego kindof Actor
	;;; An Ego is an Actor which can be controlled by a User.
	;;; "ego" is a global var that equals the base room instance of
	;;; the single User currently supported in the system.
	
	
	(properties
		edgeHit 0			;edge of screen (or horizon) which the Ego has hit
								;(NORTH, SOUTH, EAST, WEST)
		signal ignrHrz		;Egos ignore horizon so they can move above it to
								;set edgeHit
	)
	
;;;	(methods
;;;		get					;get an object into the Ego's inventory
;;;		put					;put an object in Ego's inventory somewhere else
;;;		has					;does Ego have the object in inventory?
;;;	)
	
	
	(method (init)
		(super init:)
		
		;Set cycling to walk.
		(if (not cycler)
			(self setCycle:Walk)
		)
	)
	
	
	(method (doit)
		(super doit:)
		
		;If the Ego has moved either above the horizon or past a screen edge,
		;set the edgeHit to the appropriate edge.
		(= edgeHit
			(cond
				((<= x westEdge)
					WEST
				)
				((>= x eastEdge)
					EAST
				)
				((>= y southEdge)
					SOUTH
				)
				((<= y (curRoom horizon?))
					NORTH
				)
				(else
					0
				)
			)
		)
	)
	
	
	(method (get what &tmp i)
		;; Put a number of items into Ego's inventory.
		
		(for	((= i 0))
			(< i argc)
			((++ i))
			
			((inventory at:[what i]) moveTo:self)
		)
	)
	
	(method (put what recipient &tmp theItem)
		;; Put an item of Ego's inventory into the inventory of 'recipient'.
		;; If recipient is not present, put item into limbo (-1 owner).
		(if (self has:what)
			((= theItem (inventory at:what)) moveTo:(if (== argc 1) -1 else recipient))
			(if (and theIconBar	(== (theIconBar curInvIcon?) theItem))
				((theIconBar curInvIcon: NULL useIconItem:) cursor: ARROW_CURSOR)
				(theGame setCursor: ARROW_CURSOR TRUE)

			)
		)
	)
	
	(method (has what &tmp theItem)
		;; Return TRUE if Ego has 'what' in inventory.
		
		(= theItem (inventory at:what))
		(return (and theItem (theItem ownedBy:self)))
	)
	
	(method (handleEvent event theEvType 
			&tmp 
			;evType 
			dir
			)
		
		;(= evType (if (>= argc 2) theEvType else (event type)))
		(if script (script handleEvent:event))
		(cond
			((or (event claimed?) (not (cast contains: self)))
				TRUE
			)
			((and (& (event type?) direction) (user controls?))
				(= dir (event message?))
				;; don't claim dirStop
				(if (and (== dir dirStop) (& (event type?) keyDown))
					(return (event claimed?))
				)
				;Pressing the cursor key which started a motion a second
				;time should stop ego.
				(if (and
						;(== evType keyDown)			;it's a key
						(== dir (user prevDir?))		;same dir as before
						(IsObject mover)				;ego is moving
					)
					(= dir dirStop)
				)
				
				;In the case of a keyDown event, keep the previous
				;direction, so we know what key stops ego.
				(user prevDir:
					;(if (== evType keyDown) dir else dirStop)
					dir
				)
		      ;Set ego's direction.
				(self setDirection: dir)
				(event claimed: TRUE)
			)
			((or (== (event type?) userEvent)(== (event type?) mouseDown))
				(if (and
						(or 
							(== (event message?) verbWalk)	
							(== (event type?) mouseDown)
						)
						(user controls?)
					)
					(self
						setMotion:
							(if useObstacles PolyPath else MoveTo)
							(event x?)
							(+ (event y?) z)
					)
					(user prevDir: 0)
					(event claimed: TRUE)
				else
					(super handleEvent: event)
				)
			)
			(else
				(super handleEvent: event)
			)
		)
		(return (event claimed?))
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
