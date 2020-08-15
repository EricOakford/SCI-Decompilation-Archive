;;; Sierra Script 1.0 - (do not remove this comment)
;;;;
;;;;	DOOR.SC
;;;;
;;;;	(c) Sierra On-Line, Inc, 1992
;;;;
;;;;	Author: 	Al Lowe & Brian K. Hughes
;;;;	Updated:
;;;;		Brian K. Hughes
;;;;		August 11, 1992
;;;;
;;;;	This is a general purpose door class
;;;;
;;;;	Classes:
;;;;		Door


(script# DOOR)
(include game.sh)
(use Main)
(use PolyPath)
(use Polygon)
(use Sound)
(use Motion)
(use Actor)


(class Door kindof Prop
	(properties
		entranceTo 		0					; room # beyond the door
		locked 			FALSE
		lockedCase		0					; case define for locked message
		openSnd 			0
		closeSnd 		0
		openVerb			0					; verb used to open/close/"do" the door
		listenVerb		0					; verb used to listen at door
		
		doorState		doorClosed
		doubleDoor		0					; ID of prop to be used as double door
		forceOpen		FALSE				; TRUE if door should be inited open
		forceClose		TRUE				; TRUE if door should close upon entry
	   caller			0					; who to cue when door done opening/closing
		moveToX			0					;\
		moveToY			0					;- where to move to when door is open
												;	Uses polypath, so ego could go around
												;	a corner.
		enterType		doorLeaveEgo
		exitType			doorLeaveEgo
		closeScript		0					; script before closing (if any)
		openScript		0					; script when done opening (if any)
		heading			0					; heading in which ego faces when placed
		doorPoly			0					; the polygon
		polyAdjust		5					; amount to adjust size of the polygon
	)

;;;	(methods
;;;		open
;;;		close
;;;		listen
;;;		createPoly
;;;	)

	(method (init)
		(self approachVerbs: openVerb listenVerb)

		; We're coming in through the door or we're forced open
		(if (or	forceOpen
					(and 	prevRoomNum
							(== prevRoomNum entranceTo)
						)
				)
			(= doorState doorOpen)
		)

		(super init:)

		(self createPoly:)

		(self ignoreActors: TRUE)
		(if (== doorState doorClosed)
			(= cel 0)
			(if doubleDoor
				(doubleDoor setCel: 0)
	 		)
		else
			(altPolyList delete: doorPoly)
			(= cel (- (NumCels self) 1))
			(if doubleDoor
				(doubleDoor setCel: (- (NumCels doubleDoor) 1))
			)
		)

		(if (== doorState doorOpen)		; we've just come through the door

			; We can intercept the default closing actions with a close script
			(if closeScript
				(self setScript: closeScript)
			else

				; See what to do with ego
				(switch enterType

					; We walk ego in through the open door
					(doorWalkEgo
						(ego
							posn:			moveToX moveToY,
							setMotion:	PolyPath approachX approachY self
						)
					)

					; We just put ego in front of the open door
					(doorPlaceEgo
						(ego
							edgeHit:		0,
							posn: 		approachX approachY,
							setHeading:	heading
						)
						(if forceClose
							(self close:)
						)
					)

					; We don't do anything with ego at all
					(doorLeaveEgo
						(if forceClose
							(self close:)
						)
					)
				)
			)
		)
	)

	(method (doVerb theVerb)
		(switch theVerb
			(openVerb
				(if (== doorState doorOpen)
					(self close:)
				else
					(self open:)
				)
			)
			(listenVerb
				(self listen:)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)


	(method (open)
		(if locked
			(if (== modNum -1)
				(= modNum curRoomNum)
			)
			(messager say: noun NULL lockedCase NULL NULL modNum)
		else
			(if (user controls?)
				(theGame handsOff:)	
			)
			(= doorState doorOpening)
			(self setCycle: EndLoop self)
			(if openSnd
				(doorSound
					number: openSnd, 
					play:
				)
			)
			(if doubleDoor
				(doubleDoor setCycle: EndLoop)
			)
		)
	)

	(method (close)
		(= doorState doorClosing)
		(self setCycle: BegLoop self)
		(if closeSnd
			(doorSound
				number: closeSnd, 
				play:
			)
		)
		(if doubleDoor
			(doubleDoor setCycle: BegLoop)
		)
	)

	(method (cue)
		(switch doorState

			; The door has finished closing
			(doorClosing
				(= doorState doorClosed)
				(altPolyList add: doorPoly)
				(if caller
					(caller cue:)
				)
				(if (not (user controls?))
					(theGame handsOn: TRUE)
				)
			)

			; The door has finished opening
			(doorOpening
				(= doorState doorOpen)
				(altPolyList delete: doorPoly)
				(if caller
					(caller cue:)
				)

				; We can intercept the default opening actions with an open script
				(if openScript
					(self setScript: openScript)
				else

					; See what to do with ego
					(switch exitType
						(doorWalkEgo
							(if (or moveToX moveToY)
								(ego
									illegalBits:	$0000,
									setMotion: 		PolyPath moveToX moveToY self
								)
							)
						)
						(doorPlaceEgo
							(if (or moveToX moveToY)
								(ego setMotion: PolyPath moveToX moveToY self)
							)
						)
						(doorLeaveEgo
							(if (not (user controls?))
								(theGame handsOn: TRUE)
							)
						)
					)
				)
			)

			; We've been cued by ego's mover (either coming in or going out)
			(else
				(cond

					; We're outside now
					((and	(== (ego x?) moveToX)
							(== (ego y?) moveToY)
						)

						(if entranceTo

							; We're either going to another room...
							(switch entranceTo
								((curRoom north?)
									(ego edgeHit: NORTH)
								)
								((curRoom south?)
									(ego edgeHit: SOUTH)
								)
								((curRoom west?)
									(ego edgeHit: WEST)
								)
								((curRoom east?)
									(ego edgeHit: EAST)
								)
							)
							(curRoom newRoom: entranceTo)
						else

							; ... or else just behind the door
							(cond
								(forceClose
									(self close:)
								)
								(caller
									(caller cue:)
								)
							)
						)
					)

					; We're inside now
					((and	(== (ego x?) approachX)
							(== (ego y?) approachY)
						)
						(cond
							(forceClose
								(self close:)
							)
							(caller
								(caller cue:)
							)
						)
					)
				)
			)
		)
 	)

	(method (listen)
	)

	(method (createPoly)
		(= doorPoly
			((Polygon new:)
				type:		PBarredAccess,
				yourself:
			)
		)
		(if argc
			(doorPoly init: &rest)
		else
			(doorPoly init: 	(- brLeft polyAdjust) (+ brBottom polyAdjust)
									(- brLeft polyAdjust) (- brTop polyAdjust)
									(+ brRight polyAdjust) (- brTop polyAdjust)
									(+ brRight polyAdjust) (+ brBottom polyAdjust)
			)
		)
		(altPolyList add: doorPoly)
	)

	(method (dispose)
		(altPolyList delete: doorPoly)
		(doorPoly dispose:)
		(super dispose:)
	)
)


;ษอออออออป
;บ       บ
;บ Sound บ
;บ       บ
;ศอออออออผ

(instance doorSound of Sound
	(properties
		flags		mNOPAUSE
		loop		1
	)
)