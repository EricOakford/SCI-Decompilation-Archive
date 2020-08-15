;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	EGO.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Unknown
;;;;	Updated:	Brian K. Hughes
;;;;
;;;;	An Ego is an Actor that can be controlled by a User.  "ego" is a
;;;;	global variable that points to the instance of Ego used in a room.
;;;;
;;;;	Classes:
;;;;		Ego


(script#	EGO)
(include game.sh)
(use Main)
(use PolyPath)
(use Motion)
(use Actor)


(class Ego kindof Actor
	(properties
		edgeHit 	0				;edge of screen (or horizon) which the Ego has hit
									;	(NORTH, SOUTH, EAST, WEST)
	)
;;;	(methods
;;;		get					;get an object into the Ego's inventory
;;;		put					;put an object in Ego's inventory somewhere else
;;;		has					;does Ego have the object in inventory?
;;;	)
	
	(method (init)
		(super init:)
		(|= signal ignrHrz)
		
		;Set cycling to walk.
		(if (not cycler)
			(self setCycle: Walk)
		)

		;Set the approachObj bit so he will try to approach all the time
		(|= state approachObj)
	)

	(method (facingMe)
		; We override this method so that clicking on ego will not cause him to
		;	try to face himself
		(return TRUE)
	)
	
	(method (doit)
		(super doit:)
		;If the Ego has moved either above the horizon or past a screen edge,
		;set the edgeHit to the appropriate edge.
		(= edgeHit
			(cond 
				((<= x (curRoom edgeW?)) WEST)
				((>= x (curRoom edgeE?)) EAST)
				((>= y (curRoom edgeS?)) SOUTH)
				((<= y (curRoom horizon?)) NORTH)
				(else 0)
			)
		)
	)
	
	(method (get what &tmp i)
		;; Put a number of items into Ego's inventory.
		
		(for	((= i 0))
				(< i argc)
				((++ i))
			((inventory at: [what i]) moveTo: self)
		)
	)
	
	(method (put what recipient &tmp theItem)
		;; Put an item of Ego's inventory into the inventory of 'recipient'.
		;; If recipient is not present, put item into limbo (-1 owner).
		
		(if (self has: what)
			((= theItem (inventory at: what))
				moveTo: (if (== argc 1) -1 else recipient)
			)
			(if (and theIconBar
						(== (theIconBar curInvIcon?) theItem)
				)
				(theIconBar	
					curInvIcon: NULL,
					disableIcon:
						((theIconBar	useIconItem?)
							setCursor:		normalCursor
							yourself:
						)
				)
			)
		)
	)
	
	(method (has what &tmp theItem)
		;; Return TRUE if Ego has 'what' in inventory.
		
		(= theItem (inventory at: what))
		(return
			(and 	theItem
					(theItem ownedBy: self)
			)
		)
	)

	(method (handleEvent event &tmp dir eType eMsg)
		(= eType (event type?))
		(= eMsg	(event message?))

		(cond

			; First, see if we have a script that wants the event

			((and	script
					(script handleEvent: event)
				)
				TRUE
			)

			; See if user can't control

			((not (user canControl?)))

			; See if it's a direction event

			((& eType direction)
				(= dir eMsg)

				; don't claim dirStop
				(if (and (== dir dirStop)
							(& eType keyDown)
						)
					(return (event claimed?))
				)

				; Pressing the same key that started a motion again should stop
				(if (and	(& eType keyDown)				;it's a key
							(== dir (user prevDir?))		;same dir as before
							mover								;ego is moving
						)
					(= dir dirStop)
				)
				
				; In the case of a keyDown event, keep the previous
				;	direction, so we know what key stops ego.
				(user prevDir: dir)

		      ;Set ego's direction.
				(self setDirection: dir)
				(event claimed: TRUE)
			)

			((& eType userEvent)
				(if (& eType walkEvent)
					(switch useObstacles
						(FALSE
							(self setMotion: MoveTo (event x?) (+ (event y?) z))
						)
						(1
							(self setMotion: PolyPath (event x?) (+ (event y?) z))
						)
						(2
							(self setMotion: PolyPath (event x?) (+ (event y?) z) NULL FALSE)
						)
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