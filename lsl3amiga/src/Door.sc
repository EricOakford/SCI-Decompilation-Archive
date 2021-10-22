;;; Sierra Script 1.0 - (do not remove this comment)
(script# DOOR)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)


(class Door of Prop
	(properties
		cycleSpeed 1
		entranceTo 0
		locked 0
		openSnd 0
		closeSnd 0
		doorState 0
		doorCtrl cBLUE
		roomCtrl cGREEN
		doorBlock cYELLOW
		code 0
		illegalBits NULL
		force 0
		notify 0
	)
	
	(method (init)
		(if (and prevRoomNum (== prevRoomNum entranceTo))
			(= doorState doorOpen)
		)
		(if (== doorState doorClosed)
			(= cel 0)
			(ego observeControl: doorBlock)
		else
			(= cel (- (NumCels self) 1))
			(= locked FALSE)
			(ego ignoreControl: doorBlock)
		)
		(super init:)
		(self stopUpd: ignoreActors:)
	)
	
	(method (doit)
		(super doit:)
		(if (!= doorState doorOpen)
			(ego observeControl: doorBlock)
		else
			(ego ignoreControl: doorBlock)
		)
		(if (and roomCtrl entranceTo (& (ego onControl:) roomCtrl))
			(curRoom newRoom: entranceTo)
		)
	)
	
	(method (cue)
		(= doorState (if (== doorState doorClosing) doorClosed else doorOpen))
		(if (== doorState doorOpen)
			(ego ignoreControl: doorBlock)
		else
			(ego observeControl: doorBlock)
		)
		(self stopUpd:)
		(if notify
			(notify cue:)
			(= notify 0)
		)
	)
	
	(method (open)
		(cond 
			((and (not force) (!= (ego onControl: origin) doorCtrl))
				(NotClose)
			)
			(locked
				(Print 50 0)
			)
			((or (== doorState doorOpening) (== doorState doorOpen))
				(ItIs)
			)
			(else
				(= doorState doorOpening)
				(self setCycle: EndLoop self)
				(if openSnd
					(openSnd play:)
				)
			)
		)
	)
	
	(method (close)
		(cond 
			((and (not force) (!= (ego onControl: origin) doorCtrl))
				(NotClose)
			)
			(locked
				(Print 50 0)
			)
			((or (== doorState doorClosing) (== doorState doorClosed))
				(ItIs)
			)
			((& (ego onControl:) doorBlock)
				(if (> filthLevel 1)
					(Print 50 1)
				else
					(Print 50 2)
				)
			)
			(else
				(= doorState doorClosing)
				(self setCycle: BegLoop self)
				(if closeSnd
					(closeSnd play:)
				)
			)
		)
	)
)
