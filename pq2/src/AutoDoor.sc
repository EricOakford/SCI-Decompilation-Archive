;;; Sierra Script 1.0 - (do not remove this comment)
(script# AUTODOOR)
(include game.sh)
(use Main)
(use Motion)
(use Actor)


(class AutoDoor of Prop
	(properties
		doorControl cYELLOW
		entranceTo 0
		facingLoop 0
		locked FALSE
		doorState doorClosed
		illegalBits 0
	)
	
	(method (init)
		(= doorState
			(if (== prevRoomNum entranceTo)
				doorOpen
			else
				doorClosed
			)
		)
		(= cel
			(if (== doorState doorClosed) 0
			else (- (NumCels self) 1)
			)
		)
		(super init:)
		(self stopUpd: ignoreActors:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl: origin) doorControl)
				(or (== facingLoop -1) (== (ego loop?) facingLoop))
			)
			(self open:)
		else
			(self close:)
		)
	)
	
	(method (cue)
		(self stopUpd:)
		(= doorState
			(if (== doorState doorClosing)
				doorClosed
			else
				doorOpen
			)
		)
	)
	
	(method (open)
		(if (and (not locked) (!= doorState doorOpening) (!= doorState doorOpen))
			(= doorState doorOpening)
			(self setCycle: EndLoop self)
		)
	)
	
	(method (close)
		(if (and (!= doorState doorClosing) (!= doorState doorClosed))
			(= doorState doorClosing)
			(self setCycle: BegLoop self)
		)
	)
)
