;;; Sierra Script 1.0 - (do not remove this comment)
(script# USER)
(include game.sh)
(use Main)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(define	INPUTLEN		45)
(define INBUFSIZE		23) ;(define	INBUFSIZE	(+ (/ INPUTLEN 2) 1))

(local
	[inputLine INBUFSIZE]
	inputLen
)
(instance uEvt of Event
	(properties)
)

(class User of Object
	(properties
		alterEgo 0
		input 0
		controls 0
		echo SPACEBAR
		prevDir 0
		prompt {Enter input}
		inputLineAddr 0
		x -1
		y -1
		mapKeyToDir TRUE
		curEvent NULL
		verbMessager NULL
	)
	
	(method (init inLine length)
		(= inputLineAddr (if argc inLine else @inputLine))
		(= inputLen (if (== argc 2) length else 45))
		(= curEvent uEvt)
	)
	
	(method (doit)
		(curEvent
			type: 0
			message: 0
			modifiers: 0
			y: 0
			x: 0
			claimed: 0
			port: 0
		)
		(GetEvent allEvents curEvent)
		(self handleEvent: curEvent)
	)
	
	(method (canControl value)
		(if argc (= controls value) (= prevDir 0))
		(return controls)
	)
	
	(method (getInput &tmp oldPause)
	)
	
	(method (said &tmp theVerb)
	)
	
	(method (handleEvent event &tmp eType dir)
		(= mouseX (event x?))
		(= mouseY (event y?))
		(if (event type?)
			(= lastEvent event)
			(if mapKeyToDir (MapKeyToDir event))
			(if (== (event type?) joyDown)
				(event
					type: keyDown
					message: (if (& (event modifiers?) shiftDown) ESC else ENTER)
					modifiers: 0
				)
			)
			(= eType (event type?))
			(if theMenuBar (theMenuBar handleEvent: event eType))
			(event localize:)
			(cond 
				((& (event type?) direction)
					(cond 
						(
							(or
								(and pMouse (pMouse handleEvent: event))
								(and
									directionHandler
									(directionHandler handleEvent: event)
								)
								(and alterEgo controls (alterEgo handleEvent: event))
							)
						)
						(theIconBar (theIconBar handleEvent: event))
					)
				)
				((== eType keyDown) (if keyDownHandler (keyDownHandler handleEvent: event)))
				(
				(and (== eType mouseDown) mouseDownHandler) (mouseDownHandler handleEvent: event))
			)
		)
		(if (not (event claimed?))
			(if theIconBar (theIconBar handleEvent: event))
			(if (and (== (event type?) userEvent) input)
				(cond 
					(
						(and
							(== (event message?) mouseDown)
							controls
							(alterEgo handleEvent: event)
						)
						TRUE
					)
					(useSortedFeatures
						(OnMeAndLowY init:)
						(cast eachElementDo: #perform OnMeAndLowY event)
						(features eachElementDo: #perform OnMeAndLowY event)
						(if (OnMeAndLowY theObj?)
							((OnMeAndLowY theObj?) handleEvent: event)
						)
					)
					((cast handleEvent: event) TRUE)
					((features handleEvent: event) TRUE)
				)
				(cond 
					((event claimed?) TRUE)
					((regions handleEvent: event) TRUE)
				)
			)
			(if (and (event type?) (not (event claimed?)))
				(theGame handleEvent: event)
			)
		)
	)
	
	(method (canInput n)
		(if argc (= input n))
		(return input)
	)
)

(class Ego of Actor
	(properties
		edgeHit 0
		signal ignrHrz
	)
	
	(method (init)
		(super init:)
		(if (not cycler) (self setCycle: Walk))
	)
	
	(method (doit)
		(super doit:)
		(= edgeHit
			(cond 
				((<= x westEdge) WEST)
				((>= x eastEdge) EAST)
				((>= y southEdge) SOUTH)
				((<= y (curRoom horizon?)) NORTH)
				(else 0)
			)
		)
	)
	
	(method (handleEvent event &tmp dir)
		(if script (script handleEvent: event))
		(cond 
			(
			(or (event claimed?) (not (cast contains: self))) 1)
			(
			(and (& (event type?) direction) (user controls?))
				(if
					(and
						(== (= dir (event message?)) dirStop)
						(& (event type?) keyDown)
					)
					(event claimed?)
					(return)
				)
				(if
					(and
						(== dir (user prevDir?))
						(IsObject mover)
					)
					(= dir dirStop)
				)
				(user prevDir: dir)
				(self setDirection: dir)
				(event claimed: TRUE)
			)
			(
				(or
					(== (event type?) userEvent)
					(== (event type?) mouseDown)
				)
				(if
					(and
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
			(else (super handleEvent: event))
		)
		(event claimed?)
	)
	
	(method (get what &tmp i)
		(= i 0)
		(while (< i argc)
			((inventory at: [what i]) moveTo: self)
			(++ i)
		)
	)
	
	(method (put what recipient &tmp theItem)
		(if (self has: what)
			((= theItem (inventory at: what))
				moveTo: (if (== argc 1) -1 else recipient)
			)
			(if
			(and theIconBar (== (theIconBar curInvIcon?) theItem))
				((theIconBar curInvIcon: 0 useIconItem:) cursor: ARROW_CURSOR)
				(theGame setCursor: ARROW_CURSOR TRUE)
			)
		)
	)
	
	(method (has what &tmp theItem)
		(if (= theItem (inventory at: what))
			(theItem ownedBy: self)
		)
	)
)

(class OnMeAndLowY of Code
	(properties
		theObj NULL
		lastY -1
	)
	
	(method (init)
		(= theObj NULL)
		(= lastY -1)
	)
	
	(method (doit thisObj event)
		(if
		(and (thisObj onMe: event) (> (thisObj y?) lastY))
			(= lastY ((= theObj thisObj) y?))
		)
	)
)
