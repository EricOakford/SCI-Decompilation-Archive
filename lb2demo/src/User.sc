;;; Sierra Script 1.0 - (do not remove this comment)
(script# USER)
(include game.sh)
(use Main)
(use PolyPath)
(use Motion)
(use Actor)
(use System)


(instance uEvt of Event)

(class User of Object
	(properties
		alterEgo 0
		input 0
		controls 0
		prevDir 0
		x -1
		y -1
		mapKeyToDir 1
		curEvent 0
	)
	
	(method (init)
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
		(if argc
			(= controls value)
			(= prevDir 0)
		)
		(return controls)
	)
	
	(method (handleEvent event &tmp eType eMsg eMod temp3)
		(= mouseX (event x?))
		(= mouseY (event y?))
		(= eType (event type?))
		(= eMod (event modifiers?))
		(if eType
			(= lastEvent event)
			(if mapKeyToDir (MapKeyToDir event))
			(if (== eType joyDown)
				(= eType keyDown)
				(= eMsg
					(if (& eMod shiftDown) ESC else ENTER)
				)
				(= eMod 0)
				(event
					type: eType
					message: eMsg
					modifiers: eMod
				)
			)
			(if (and prints (prints handleEvent: event)) (return TRUE))
			(event localize:)
			(= eType (event type?))
			(= eMsg (event message?))
			(cond 
				((& eType direction)
					(cond 
						(
							(and
								directionHandler
								(directionHandler handleEvent: event)
							)
							(return 1)
						)
						(
							(and
								theIconBar
								(== (theIconBar curIcon?) (theIconBar walkIconItem?))
								alterEgo
								controls
								(cast contains: alterEgo)
								(alterEgo handleEvent: event)
							)
							(return TRUE)
						)
						(
							(and
								pMouse
								input
								(!= eMsg dirStop)
								(pMouse handleEvent: event)
							)
							(return TRUE)
						)
					)
				)
				(
					(and
						(& eType keyDown)
						keyDownHandler
						(keyDownHandler handleEvent: event)
					)
					(return 1)
				)
				(
					(and
						(& eType (| mouseDown mouseUp))
						mouseDownHandler
						(mouseDownHandler handleEvent: event)
					)
					(return TRUE)
				)
			)
		)
		(if theIconBar (theIconBar handleEvent: event))
		(= eType (event type?))
		(= eMsg (event message?))
		(if (and input (& eType userEvent))
			(cond 
				(
					(and
						(& eType walkEvent)
						walkHandler
						(walkHandler handleEvent: event)
					)
					(return TRUE)
				)
				(
					(and
						(& eType walkEvent)
						(cast contains: alterEgo)
						(alterEgo handleEvent: event)
					)
					(return TRUE)
				)
				(useSortedFeatures
					(OnMeAndLowY init:)
					(cast eachElementDo: #perform OnMeAndLowY event)
					(features eachElementDo: #perform OnMeAndLowY event)
					(if
						(and
							(OnMeAndLowY theObj?)
							((OnMeAndLowY theObj?) handleEvent: event)
						)
						(return TRUE)
					)
				)
				((cast handleEvent: event) (return TRUE))
				((features handleEvent: event) (return TRUE))
			)
			(if
				(and
					(not (event claimed?))
					(regions handleEvent: event)
				)
				(return TRUE)
			)
		)
		(if eType
			(cond 
				((theGame handleEvent: event) (return TRUE))
				((and prints (prints handleEvent: event)) (return TRUE))
			)
		)
		(return 0)
	)
	
	(method (canInput theInput)
		(if argc (= input theInput))
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
		(if (not cycler)
			(self setCycle: Walk)
		)
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
	
	(method (handleEvent event &tmp dir eType eMsg)
		(= eType (event type?))
		(= eMsg (event message?))
		(cond 
			((and script
					(script handleEvent: event)
					)
					TRUE
			)
			((& eType direction)
				(if
					(and
						(== (= dir eMsg) dirStop)
						(& eType keyDown)
					)
					(event claimed?)
					(return)
				)
				(if
					(and
						(& eType keyDown)
						(== dir (user prevDir?))
						(IsObject mover)
					)
					(= dir dirStop)
				)
				(user prevDir: dir)
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
							(self
								setMotion: PolyPath (event x?) (+ (event y?) z) 0 0
							)
						)
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
				(theIconBar
					curInvIcon: NULL
					disable: ((theIconBar useIconItem?) cursor: ARROW_CURSOR yourself:)
				)
			)
		)
	)
	
	(method (has what &tmp theItem)
		(if (= theItem (inventory at: what))
			(theItem ownedBy: self)
		)
	)
	
	(method (setSpeed newSpeed)
		(if argc (= moveSpeed (= cycleSpeed newSpeed)))
		(return moveSpeed)
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
