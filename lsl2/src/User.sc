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
(include game.sh) (include sortcopy.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Menu)
(use System)

(define	INPUTLEN		45)
(define	INBUFSIZE		23)	;;(+ (/ INPUTLEN 2) 1)

(local
	[inputLine INBUFSIZE]
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
	)

;;;	(methods
;;;		canControl					;specifies whether user controls alterEgo
;;;		getInput						;collects input from the user
;;;		said							;passes parsed user input to handleEvent methods
;;;	)


	(method (doit &tmp event evType dir)
		;; See if there is an event.  If none, just return.  Otherwise
		;; pass the event to other objects in the game to see if they
		;; want it.

		(= event (Event new:))
		(if (event type?)
			(= lastEvent event)

			;Convert key events to direction events, if appropriate, but
			;remember what kind of event it was.
			(= evType (event type?))
			(MapKeyToDir event)

			;Give the event to the menu first.
			(if TheMenuBar 
				(TheMenuBar handleEvent: event)
			)

			;Correct y coord for current grafPort.
			(GlobalToLocal event)
	
			;Pass the Event to the game.
			(theGame handleEvent: event)

			;If the event is not claimed, give it to either ego (in the case
			;of direction/mouseDown events) or the entire cast.
			(if (not (event claimed?))
				(switch (event type?)
					(mouseDown
						(if (and controls (IsObject alterEgo))
							(alterEgo setMotion:MoveTo (event x?) (event y?))
							(= prevDir 0)
							(event claimed:TRUE)
						)
					)
					(direction
						(if (and controls (IsObject alterEgo))
							(= dir (event message?))
	
							;Pressing the cursor key which started a motion a second
							;time should stop ego.
							(if
								(and
									(== evType keyDown)				;it's a key
									(== dir prevDir)					;same dir as before
									(IsObject (alterEgo mover?))	;ego is moving
								)
									(= dir 0)
							)
	
							;In the case of a keyDown event, keep the previous
							;direction, so we know what key stops ego.
							(= prevDir (if (== evType keyDown) dir else 0))
	
							;Set ego's direction.
							(alterEgo setDirection:dir)
							(event claimed:TRUE)
						)
					)
					(else
						(cast eachElementDo:#handleEvent: event)
					)
				)
			)

			; If nobody wants this event AND it is a key down
			; get a line of input and Parse it
			; and see if anybody wants THAT.
			(if
				(and
					(== (event type?) keyDown)
					(not (event claimed?))
					(or
						(== (event message?) echo)				; the echo char?
						(<= SPACEBAR (event message?) 127)	; only a typeable character
					)
				)

				;Return if input is disallowed
				(if (and
					canInput
					(self getInput:event)
					(Parse @inputLine event)
					)
					;; We were able to parse the input.  
					;; Create a said event, and see
					;; if someone wants it.
					(event type:saidEvent)
					(self said:event)
				)
			)
		)
	
		;Finally, dispose of the event.
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
		(= inputLineAddr @inputLine)
		(= ret (GetInput @inputLine INPUTLEN prompt #at: x y))
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

		(if TheMenuBar 
			(TheMenuBar handleEvent: event)
		)
		
		;(sortedCpy sortedCast cast)
		;(sortedCpy sortedFeatures features)

		(sortedCast eachElementDo:#handleEvent: event)
		(sortedFeatures eachElementDo:#handleEvent: event)
		
		(theGame handleEvent: event)

		;If the event was not claimed by anyone, invoke pragmaFail: to let
		;the user know that it was not understood.
		(if (and (== (event type?) saidEvent) (not (event claimed?)))
			(theGame pragmaFail: @inputLine)
		)
	)
)