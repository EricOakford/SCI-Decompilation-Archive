;;; Sierra Script 1.0 - (do not remove this comment)
(script# DOOR)
(include game.sh)
(use Main)
(use PolyPath)
(use Sound)
(use Motion)
(use Actor)


(class Door of Prop
	(properties
		entranceTo 0
		locked FALSE
		openSnd 40
		closeSnd 41
		state doorClosed
		doubleDoor 0
		forceOpen 0
		caller 0
		moveToX 0
		moveToY 0
		enterType doorLeaveEgo
		exitType doorLeaveEgo
		closeScript 0
		openScript 0
	)
	
	(method (init)
		(self approachVerbs: verbDo)
		(if
			(or
				forceOpen
				(and prevRoomNum (== prevRoomNum entranceTo))
			)
			(= state doorOpen)
			(= signal (| signal ignrAct))
			(if doubleDoor
				(doubleDoor ignoreActors: TRUE)
			)
		)
		(if (== state doorClosed)
			(= cel 0)
			(if doubleDoor
				(doubleDoor setCel: 0)
			)
		else
			(= cel (- (NumCels self) 1))
			(if doubleDoor
				(doubleDoor setCel: 255)
			)
		)
		(super init:)
		(if (== state doorOpen)
			(if closeScript
				(self setScript: closeScript)
			else
				(switch enterType
					(doorWalkEgo
						(HandsOff)
						(ego
							posn: moveToX moveToY
							setMotion: PolyPath approachX approachY self
						)
					)
					(doorPlaceEgo
						(ego
							edgeHit: 0
							posn: approachX approachY
							setHeading: heading
						)
						(self close:)
					)
					(doorLeaveEgo
						(self close:)
					)
				)
			)
		else
			(self stopUpd:)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (== state doorOpen)
					(self close:)
				else
					(self open:)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(switch state
			(doorClosing
				(= state doorClosed)
				(self stopUpd: ignoreActors: FALSE)
				(if doubleDoor
					(doubleDoor ignoreActors: FALSE)
				)
				(if caller
					(caller cue:)
				)
			)
			(doorOpening
				(= state doorOpen)
				(self stopUpd: ignoreActors: TRUE)
				(if doubleDoor
					(doubleDoor ignoreActors: TRUE)
				)
				(if caller
					(caller cue:)
				)
				(if openScript
					(self setScript: openScript)
				else
					(switch exitType
						(doorWalkEgo
							(if (or moveToX moveToY)
								(ego
									illegalBits: 0
									setMotion: PolyPath moveToX moveToY self
								)
							)
						)
						(doorPlaceEgo
							(if (or moveToX moveToY)
								(ego setMotion: PolyPath moveToX moveToY self)
							)
						)
						(doorLeaveEgo
							(self cue:)
						)
					)
				)
			)
			(else 
				(cond 
					((and (== (ego x?) moveToX) (== (ego y?) moveToY))
						(if entranceTo
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
							(self close:)
						)
					)
					((and (== (ego x?) approachX) (== (ego y?) approachY))
						(self close:)
					)
				)
				(HandsOn)
			)
		)
	)
	
	(method (open)
		(if locked
			(TimePrint 21 0)
		else
			(HandsOff)
			(= state doorOpening)
			(self setCycle: EndLoop self)
			(if openSnd
				((Sound new:) number: openSnd play:)
			)
			(if doubleDoor
				(doubleDoor setCycle: EndLoop)
			)
		)
	)
	
	(method (close)
		(= state doorClosing)
		(self setCycle: BegLoop self)
		(if closeSnd
			((Sound new:) number: closeSnd play:)
		)
		(if doubleDoor
			(doubleDoor setCycle: BegLoop)
		)
	)
)
