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
(use Intrface)
(use Sound)
(use SortCopy)
(use Motion)
(use Menu)
(use Actor)
(use System)


(define	INPUTLEN		45)
(define INBUFSIZE		23) ;(define	INBUFSIZE	(+ (/ INPUTLEN 2) 1))

(local
	[inputLine INBUFSIZE]
	inputLen
)

(class User kindof Object
	(properties
		alterEgo 0					;the object ID of the Ego which User controls
		canInput 0					;can the User type input?
		controls 0					;boolean -- does User control alterEgo at present?
		echo SPACEBAR				;character to echo last input line
		prevDir 0					;previous direction in which alterEgo was moving
		prompt "Enter input"		;prompt for input window
		inputLineAddr 0			;address of User's input line
		x	-1							; upper/left
		y	-1							; of user window
		blocks	TRUE				; stops sounds by default
		mapKeyToDir TRUE			;map keys to dirs?
	)
	
;;;	(methods
;;;		canControl					;specifies whether user controls alterEgo
;;;		getInput						;collects input from the user
;;;		said							;passes parsed user input to handleEvent methods
;;;		handleEvent
;;;	)
	
	(method (init inLine length)
		(= inputLineAddr (if argc inLine else @inputLine))
		(= inputLen (if (== argc 2) length else INPUTLEN))
	)
	
	
	(method (doit &tmp event evType dir)
		;; See if there is an event.  If none, just return.  Otherwise
		;; pass the event to other objects in the game to see if they
		;; want it.
		
		(if ((= event (Event new:)) type?)
			(= lastEvent event)
			(= evType (event type?))
			(if mapKeyToDir (MapKeyToDir event))
			(if TheMenuBar (TheMenuBar handleEvent: event evType))
			(GlobalToLocal event)
			(if (not (event claimed?))
				(theGame handleEvent: event evType)
			)
			(if
				(and
					controls
					(not (event claimed?))
					(cast contains: alterEgo)
				)
				(alterEgo handleEvent: event evType)
			)
			(if
				(and
					canInput
					(not (event claimed?))
					(== (event type?) keyDown)
					(or
						(== (event message?) echo)
						(and
							(<= SPACEBAR (event message?))
							(<= (event message?) 127)
						)
					)
					(self getInput: event)
					(Parse @inputLine event)
				)
				(event type: 128)
				(self said: event)
			)
		)
		(event dispose:)
		(= lastEvent 0)
	)	
	
	(method (getInput event &tmp oldPause ret)
		;; Put up the input window and collect a line of input from the user.
		
		; if this is NOT a key event we zero out the inputLine
		(if (!= (event type?) keyDown)
			(= inputLine 0)
		)
		
		;If this is not the echo character, replace the previous input
		;line with the character which was passed.
		(if (!= (event message?) echo)
			(Format @inputLine USER 0 (event message?))
		)
		
		;Let the user edit the input line.
		(= oldPause (Sound pause: blocks))
		(= ret (GetInput @inputLine inputLen prompt #at: x y))
		(Sound pause: oldPause)
		(return ret)
	)
	
	
	(method (canControl value)
		;; Doing a (User canControl:FALSE) prevents the user from controlling
		;; the alterEgo using the mouse, arrow keys, etc.  (User canControl:TRUE)
		;; reinstates user control.
		
		(if argc
			(= controls value)
			(= prevDir 0)
		)
		(return controls)
	)
	
	
	(method (said event)
		;; Pass a said event parsed from user input to the various elements of
		;; the game.
		
		(if useSortedFeatures
			(SortedAdd alterEgo sortedFeatures cast features)
		else
			(sortedFeatures add: cast features)
		)
		
		(if TheMenuBar 
			(sortedFeatures addToFront: TheMenuBar)	;menu gets said first
		)
																	;then cast and features
		(sortedFeatures 
			addToEnd:	theGame,		;then room, regions, locales and game last
			handleEvent: event
			release:
		)
		
		;If the event was not claimed by anyone, invoke pragmaFail: to let
		;the user know that it was not understood.
		(if (and (== (event type?) saidEvent) (not (event claimed?)))
			(theGame pragmaFail: @inputLine)
		)
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
				((<= y (curRoom horizon?))
					NORTH
				)
				((>= x eastEdge)
					EAST
				)
				((>= y southEdge)
					SOUTH
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
	
	(method (put what recipient)
		;; Put an item of Ego's inventory into the inventory of 'recipient'.
		;; If recipient is not present, put item into limbo (-1 owner).
		
		(if (self has:what)
			((inventory at:what) moveTo:(if (== argc 1) -1 else recipient))
		)
	)
	
	(method (has what &tmp theItem)
		;; Return TRUE if Ego has 'what' in inventory.
		
		(= theItem (inventory at:what))
		(return (and theItem (theItem ownedBy:self)))
	)
	
	(method (handleEvent event theEvType 
			&tmp 
			evType 
			dir
			)
		
		(= evType (if (>= argc 2) theEvType else (event type?)))
		
		(if (not (super handleEvent: event))	;event not claimed
			(switch (event type?)
				(mouseDown
					(if (not (& (event modifiers?) shiftDown))
						(self setMotion: MoveTo (event x?) (event y?))
						(User prevDir: 0)
						(event claimed: TRUE)
					)
				)
				(direction
					
					(= dir (event message?))
					
					;Pressing the cursor key which started a motion a second
					;time should stop ego.
					(if (and
							(== evType keyDown)			;it's a key
							(== dir (User prevDir:))		;same dir as before
							(IsObject mover)				;ego is moving
						)
						(= dir dirStop)
					)
					
					;In the case of a keyDown event, keep the previous
					;direction, so we know what key stops ego.
					(User prevDir:
						(if (== evType keyDown) dir else dirStop)
						;dir
					)
					
					;Set ego's direction.
					(self setDirection:dir)
					(event claimed:TRUE)
				)
			)
		)
		(return (event claimed?))
	)
)
