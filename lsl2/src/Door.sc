;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)


(class Door of Prop
	(properties
		cycleSpeed 1
		entranceTo -1
		locked 0
		openSnd 0
		closeSnd 0
		doorState 0
		doorCtrl 2
		doorBlock 16384
		roomCtrl 4
		code 0
		illegalBits $0000
		force 0
		notify 0
		msgLook {Yep; it's a door.}
		msgLookLock {And... it's locked.}
		msgLocked {This door is locked.}
		msgExcept {...except it's locked!}
		msgFunny {Funny. No response.}
		msgCloser {Just walk near it.}
	)
	
	(method (init)
		(if (== prevRoomNum entranceTo) (= doorState 2))
		(if (== doorState 0)
			(= cel 0)
			(ego observeControl: doorBlock)
		else
			(= cel (- (NumCels self) 1))
			(= locked 0)
			(ego ignoreControl: doorBlock)
		)
		(super init:)
		(self stopUpd: ignoreActors:)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (> roomCtrl 0) (& (ego onControl:) roomCtrl))
			(curRoom newRoom: entranceTo)
		)
	)
	
	(method (handleEvent event)
		(if
			(or
				(!= (event type?) saidEvent)
				(== curRoomNum 33)
				(== curRoomNum 15)
				(event claimed?)
			)
			(return)
		)
		(cond 
			((Said 'open/door') (self open:))
			((Said 'close/door') (self close:))
			((Said 'look/door') (Print msgLook) (if locked (Print msgLookLock)))
			((Said 'bang/door') (Print msgFunny))
		)
	)
	
	(method (cue)
		(= doorState (if (== doorState 3) 0 else 2))
		(if (== doorState 2)
			(ego ignoreControl: doorBlock)
		else
			(ego observeControl: doorBlock)
		)
		(self stopUpd:)
		(if notify (notify cue:) (= notify 0))
	)
	
	(method (open)
		(cond 
			(
			(and (not force) (!= (ego onControl: 1) doorCtrl)) (PrintNotCloseEnough))
			(locked (Print msgLocked))
			((or (== doorState 1) (== doorState 2)) (PrintItIs))
			(else
				(= doorState 1)
				(self setCycle: EndLoop self)
				(if openSnd (openSnd doit:))
			)
		)
	)
	
	(method (close)
		(cond 
			(
			(and (not force) (!= (ego onControl: 1) doorCtrl)) (PrintNotCloseEnough))
			(locked (Print msgLocked))
			((or (== doorState 3) (== doorState 0)) (PrintItIs))
			((& (ego onControl:) doorBlock) (if (> filthLevel 4) (Print 3 0) else (Print 3 1)))
			(else
				(= doorState 3)
				(self setCycle: BegLoop self)
				(if closeSnd (closeSnd doit:))
			)
		)
	)
)

(class AutoDoor of Door
	
	(method (init)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(code (if (code doit: self) (self open:) else (self close:)))
			((& (ego onControl:) doorCtrl) (self open:))
			(else (self close:))
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'open/door') (Print msgCloser) (if locked (Print msgExcept)))
			((Said 'close/door') (Print msgCloser) (if locked (Print msgExcept)))
			((Said 'look/door') (Print msgLook) (if locked (Print msgLookLock)))
			((Said 'bang/door') (Print msgFunny))
		)
	)
	
	(method (open)
		(if
		(and (not locked) (!= doorState 1) (!= doorState 2))
			(= doorState 1)
			(self setCycle: EndLoop self)
			(if openSnd (openSnd doit:))
		)
	)
	
	(method (close)
		(if (and (!= doorState 3) (!= doorState 0))
			(= doorState 3)
			(self setCycle: BegLoop self)
			(if closeSnd (closeSnd doit:))
		)
	)
)
