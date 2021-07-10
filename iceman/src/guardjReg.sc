;;; Sierra Script 1.0 - (do not remove this comment)
(script# 312)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use EgoDead)
(use GoToSaid)
(use Chase)
(use Sight)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	guardjReg 0
)

(local
	theRegister
)
(instance guardjReg of Region
	
	(method (init)
		(super init:)
		(Load VIEW 472)
		(Load VIEW 476)
		(jeep
			setCycle: 0
			illegalBits: 0
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			init:
			hide:
		)
		(cond 
			((== curRoomNum 80)
				(if (tunisia bagBound?)
					(jeep setScript: 0)
				else
					(jeep setScript: fromEastScript)
				)
			)
			((OneOf curRoomNum 77 82)
				(straightScript seconds: 20)
				(jeep setScript: straightScript)
			)
			(else
				(fromWestScript seconds: 20)
				(jeep setScript: fromWestScript)
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep (OneOf roomNum 77 78 79 80 82 83))
		(= initialized FALSE)
		(super newRoom: &rest)
	)
)

(instance straightScript of Script
	
	(method (changeState newState &tmp egoX)
		(switch (= state newState)
			(0)
			(1
				(if (and (> 140 (ego y?)) (> (ego y?) 100))
					(= register 1)
					(HandsOff)
					(Print 312 0 #dispose #at 5 10)
					(if (< (ego y?) 120)
						(ego setMotion: MoveTo (ego x?) 99 self)
					else
						(ego setMotion: MoveTo (ego x?) 141 self)
					)
				else
					(= cycles 1)
				)
			)
			(2
				(if register
					(if (< (ego y?) 120)
						(ego heading: 180)
					else
						(ego heading: 0)
					)
					((ego looper?) doit: ego (ego heading?))
					(= cycles 5)
				else
					(= cycles 1)
				)
			)
			(3
				(cond 
					((< (ego x?) 30) (= egoX (+ (ego x?) 30)))
					((> (ego x?) 290) (= egoX (- (ego x?) 30)))
					(else (= egoX (ego x?)))
				)
				(jeep
					posn: 380 120
					view: 476
					setLoop: 0
					setCel: 0
					show:
					setCycle: 0
					setStep: 8 8
					setMotion: MoveTo egoX 120 self
				)
			)
			(4
				(cls)
				(jeep
					setStep: 6 6
					setMotion: MoveTo (- (jeep x?) 12) (jeep y?) self
				)
			)
			(5
				(jeep
					setStep: 4 4
					setMotion: MoveTo (- (jeep x?) 8) (jeep y?) self
				)
			)
			(6
				(jeep
					setStep: 2 2
					setMotion: MoveTo (- (jeep x?) 4) (jeep y?) self
				)
			)
			(7
				(jeep setStep: 0 0 loop: 4)
				(guard
					view: 476
					loop: 3
					cel: 0
					ignoreActors: TRUE
					posn: (jeep x?) (jeep y?)
					setPri: (jeep priority?)
					init:
					setCycle: EndLoop self
				)
			)
			(8
				(jeep ignoreActors: FALSE)
				(guard
					view: 472
					setPri: -1
					posn: (jeep x?) (- (jeep y?) 10)
					illegalBits: cWHITE
					ignoreActors: 0
					loop: 2
					setLoop: -1
					setCycle: Walk
					setStep: 3 2
					setAvoider: Avoider
					setMotion: Chase ego 20 self
				)
			)
			(9
				(self setScript: caughtEgoScript self)
			)
			(10
				(client setScript: driveAwayScript 0 -1)
			)
		)
	)
)

(instance fromWestScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(if (ego inRect: 0 120 106 168)
					(= register 1)
					(HandsOff)
					(Print 312 1 #dispose #at 5 10)
					(ego setMotion: MoveTo 178 (ego y?) self)
				else
					(= cycles 1)
				)
			)
			(2
				(if register
					(ego heading: 270)
					((ego looper?) doit: ego (ego heading?))
					(= cycles 5)
				else
					(= cycles 1)
				)
			)
			(3
				(jeep
					posn: -4 120
					view: 476
					setLoop: 1
					setCel: 0
					show:
					setStep: 8 8
					setMotion: MoveTo 60 120 self
				)
			)
			(4
				(cls)
				(jeep setStep: 4 4)
				(self setScript: uTurnScript self 1)
			)
			(5
				(jeep setLoop: 4)
				(guard
					view: 476
					loop: 3
					cel: 0
					ignoreActors: TRUE
					posn: (jeep x?) (jeep y?)
					setPri: (jeep priority?)
					init:
					setCycle: EndLoop self
				)
			)
			(6
				(jeep ignoreActors: 0)
				(guard
					view: 472
					posn: (+ (jeep x?) 6) (- (jeep y?) 2)
					illegalBits: cWHITE
					ignoreActors: FALSE
					setPri: -1
					loop: 2
					setLoop: -1
					setCycle: Walk
					setStep: 3 2
					setAvoider: Avoider
					setMotion: Chase ego 20 self
				)
			)
			(7
				(self setScript: caughtEgoScript self)
			)
			(8
				(client setScript: driveAwayScript 0 -1)
			)
		)
	)
)

(instance fromEastScript of Script

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= seconds 30))
			(1
				(if (ego inRect: 228 120 319 168)
					(= register TRUE)
					(HandsOff)
					(Print 312 0 #dispose #at 5 10)
					(ego setMotion: MoveTo 205 (ego y?) self)
				else
					(= cycles 1)
				)
			)
			(2
				(if register
					(ego heading: 90)
					((ego looper?) doit: ego (ego heading?))
					(= cycles 5)
				else
					(= cycles 1)
				)
			)
			(3
				(jeep view: 476 setLoop: 0 setCel: 0 show: setStep: 8 8)
				(jeep posn: 360 120 setMotion: MoveTo 270 120 self)
			)
			(4
				(cls)
				(jeep setStep: 4 4)
				(self setScript: uTurnScript self -1)
			)
			(5
				(jeep setLoop: 5)
				(guard
					view: 476
					loop: 2
					cel: 0
					ignoreActors: TRUE
					posn: (jeep x?) (jeep y?)
					setPri: (jeep priority?)
					init:
					setCycle: EndLoop self
				)
			)
			(6
				(jeep ignoreActors: FALSE)
				(guard
					view: 472
					posn: (- (jeep x?) 6) (- (jeep y?) 2)
					illegalBits: cWHITE
					ignoreActors: 0
					setPri: -1
					loop: 2
					setLoop: -1
					setCycle: Walk
					setStep: 3 2
					setAvoider: Avoider
					setMotion: Chase ego 20 self
				)
			)
			(7
				(self setScript: caughtEgoScript self)
			)
			(8
				(self setScript: driveAwayScript 0 1)
			)
		)
	)
)

(instance uTurnScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(jeep
					setMotion: MoveTo (+ (jeep x?) (* 6 register)) (+ (jeep y?) 6) self
				)
			)
			(1
				(jeep setCel: (+ (jeep cel?) 1))
				(cond 
					((== (jeep cel?) 4)
						(= theRegister register)
						(= register 0)
						(self init:)
					)
					((== (jeep cel?) 5)
						(= register (- (* theRegister 2)))
						(self init:)
					)
					((== (jeep cel?) 8)
						(self dispose:)
					)
					(else
						(self init:)
					)
				)
			)
		)
	)
)

(instance driveAwayScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard
					setMotion: MoveTo (+ (jeep x?) 6) (- (jeep y?) 12) self
				)
			)
			(1
				(guard
					setPri: (- (jeep priority?) 1)
					setAvoider: 0
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo (jeep x?) (jeep y?) self
				)
			)
			(2
				(guard
					view: 476
					setLoop: (if (== register 1) 2 else 3)
					setCel: 16
					setPri: (jeep priority?)
					ignoreActors: 1
					setCycle: BegLoop self
				)
			)
			(3
				(jeep dispose:)
				(guard
					view: 476
					ignoreActors: FALSE
					setLoop: (if (== register 1) 1 else 0)
					setCel: 0
					setStep: 2 2
					setMotion: MoveTo (+ (guard x?) (* 4 register)) (guard y?) self
				)
			)
			(4
				(guard
					setStep: 4 4
					setMotion: MoveTo (+ (guard x?) (* 8 register)) (guard y?) self
				)
			)
			(5
				(guard
					setStep: 6 6
					setMotion: MoveTo (+ (guard x?) (* 12 register)) (guard y?) self
				)
			)
			(6
				(guard
					setStep: 8 8
					setMotion: MoveTo (if (== register 1) 380 else -60) (guard y?) self
				)
			)
			(7
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance caughtEgoScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== register 0)
					(InitArab guard)
				)
				(= seconds 2)
			)
			(1
				(if (== register 0)
					(DisposeArab)
					(Print 312 2)
					(ego
						heading: (GetAngle (ego x?) (ego y?) (guard x?) (guard y?))
					)
					((ego looper?) doit: ego (ego heading?))
				)
				(= cycles 5)
			)
			(2
				(InitArab guard)
				(= seconds 1)
			)
			(3
				(DisposeArab)
				(InitArab ego)
				(= seconds 1)
			)
			(4
				(DisposeArab)
				(= cycles 1)
				(if (<= (++ register) 3) (self init:))
			)
			(5
				(cond 
					((== (ego view?) 71)
						(Print 312 3)
						(= cycles 1)
					)
					((and (ego has: iMap) ((inventory at: iMap) loop?))
						(Print 312 4)
						(= cycles 1)
					)
					((ego has: iIDCard)
						(Print 312 5)
						(Print 312 6)
						(Print 312 7)
						(HandsOn)
						(self dispose:)
					)
					(else
						(Print 312 8)
						(= cycles 1)
					)
				)
			)
			(6
				(EgoDead 970 0 0 312 9)
			)
		)
	)
)

(instance guard of Actor
	(properties
		view 476
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/guard,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (or (& signal hideActor) (IsOffScreen self))
							(Print 312 10)
						else
							(Print 312 11)
							(event claimed: TRUE)
						)
					)
					((or (& signal hideActor) (IsOffScreen self))
						(event claimed: FALSE)
					)
					((Said 'address')
						(Print 312 12)
					)
					((Said 'kiss,fuck,hug')
						(Print 312 13)
					)
					((Said 'kill,shoot')
						(if (ego has: iTranquilizerGun)
							(Print 312 14)
						else
							(Print 312 15)
						)
					)
					((Said 'fight,kick,hit')
						(Print 312 16)
					)
					((Said 'greet')
						(Print 312 17)
					)
				)
			)
		)
	)
)

(instance jeep of Actor
	(properties
		view 476
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/jeep,car]>')
				(cond 
					((IsOffScreen self))
					((TurnIfSaid self event 'look/*'))
					((Said 'look[<at]')
						(Print 312 18)
					)
				)
			)
		)
	)
)
