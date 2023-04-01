;;; Sierra Script 1.0 - (do not remove this comment)
(script# DOOR) ;800
(include game.sh)
(use Main)
(use Motion)
(use Actor)


(class Door of Prop
	(properties
		entranceTo 0
		locked FALSE
		openSnd 0
		closeSnd 0
		doorControl 0
		doorState 0
		facingLoop 0
		code 0
		illegalBits 0
	)
	
	(method (init)
		(= doorState (if (== prevRoomNum entranceTo) doorOpen else doorClosed))
		(= cel
			(if (== doorState doorClosed) 0 else (- (NumCels self) 1))
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
		(= doorState (if (== doorState doorClosing) 0 else doorOpen))
		(if (and (== doorState doorOpen) entranceTo)
			(curRoom newRoom: entranceTo)
		)
	)
	
	(method (open)
		(if (and (not locked) (!= doorState doorOpening) (!= doorState doorOpen))
			(= doorState doorOpening)
			(self setCycle: EndLoop self)
			(if openSnd
				(openSnd doit:)
			)
		)
	)
	
	(method (close)
		(if (and (!= doorState doorClosing) (!= doorState doorClosed))
			(= doorState doorClosing)
			(self setCycle: BegLoop self)
			(if closeSnd
				(closeSnd doit:)
			)
		)
	)
)
