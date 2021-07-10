;;; Sierra Script 1.0 - (do not remove this comment)
(script# 306)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use EgoDead)
(use Approach)
(use GoToSaid)
(use LoadMany)
(use Chase)
(use Grooper)
(use Sight)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	guardcReg 0
	caughtEgoScript 1
	guard 2
)

(local
	egoX
	egoY
	egoLoop
	theSeconds
)
(instance guardcReg of Region
	
	(method (init)
		(super init:)
		(LoadMany VIEW 172 772 472 471 572)
		(guard
			illegalBits: -32704
			setAvoider: Avoider
			setCycle: Walk
			init:
			hide:
		)
		(if (not theSeconds)
			(= theSeconds 20)
		)
		(cond 
			(
				(and
					(not (& (tunisia flags?) $0010))
					(OneOf curRoomNum 71 72)
				)
				(guard setScript: wrongBeachScript)
			)
			(
				(and
					(not (& (tunisia flags?) $0010))
					(!= (ego view?) 84)
				)
				(tunisia flags: (| (tunisia flags?) $0010))
				(guard setScript: rm70ChaseScript)
			)
			(else
				(guard setScript: roomChaseScript)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(= egoLoop (ego loop?))
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep (OneOf roomNum 70 71 72))
		(= initialized 0)
		(super newRoom: &rest)
	)
)

(instance wrongBeachScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard
					view: 172
					show:
					posn: 10 80
					setStep: 6 6
					setMotion: MoveTo 155 80 self
				)
			)
			(1
				(if (== curRoomNum 72)
					(guard setMotion: MoveTo 175 80 self)
				else
					(= cycles 1)
				)
			)
			(2
				(self setScript: disMountScript self)
			)
			(3
				(guard setMotion: MoveTo (+ (ego x?) 30) (ego y?) self)
			)
			(4
				(guard
					setMotion: MoveTo (- (guard x?) 2) (guard y?) self
				)
			)
			(5
				(self setScript: arabicScript self)
			)
			(6
				(Print 306 0)
				(ego heading: 270)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 6)
			)
			(7
				(ego view: 471 loop: 1 setCycle: EndLoop self)
				(guard view: 572 loop: 1 setCycle: EndLoop self)
			)
			(8)
			(9
				(ego
					setCycle: Walk
					setLoop: (+ (ego loop?) 2)
					setMotion: MoveTo (- (ego x?) 60) (ego y?) self
				)
				(guard
					setCycle: Walk
					setLoop: (+ (guard loop?) 2)
					setMotion: MoveTo (- (guard x?) 60) (guard y?)
				)
			)
			(10 (EgoDead 970 0 0 306 1))
		)
	)
)

(instance rm70ChaseScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 80))
			(1
				(guard
					view: 172
					show:
					posn: 336 84
					observeControl: 64
					setStep: 6 6
					setMotion: MoveTo 237 84 self
				)
			)
			(2
				(self setScript: disMountScript self)
			)
			(3
				(guard illegalBits: -32704 setMotion: Chase ego 40 self)
			)
			(4
				(self setScript: arabicScript self)
			)
			(5
				(self setScript: caughtEgoScript)
			)
		)
	)
)

(instance roomChaseScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= theSeconds (- (= seconds theSeconds) 2))
			)
			(1
				(guard
					view: 472
					show:
					illegalBits: -32704
					posn: egoX egoY
					setLoop: GradualLooper
					loop: egoLoop
					setMotion: Chase ego 40 self
				)
			)
			(2
				(self setScript: arabicScript self)
			)
			(3
				(self setScript: caughtEgoScript)
			)
		)
	)
)

(instance caughtEgoScript of Script

	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cond 
					((and (ego has: iFish) (not (ego has: iCapsule)))
						(Print 306 2)
						(= cycles 1)
					)
					((== (ego view?) 71)
						(Print 306 0)
						(= cycles 1)
					)
					((ego has: iIDCard)
						(client setScript: hasIDScript)
					)
					(else
						(Print 306 3)
						(= cycles 1)
					)
				)
			)
			(1
				(EgoDead 970 0 0 306 1)
			)
		)
	)
)

(instance hasIDScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 306 4)
				(= cycles 1)
			)
			(1
				(Print 306 5)
				(Print 306 6)
				(guardcReg setScript: guardLeaveScript)
				(HandsOn)
			)
		)
	)
)

(instance guardLeaveScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch curRoomNum
					(70
						(guard
							setMotion:
								Approach
								(if (< (guard y?) 90)
									(ScriptID 70 2)
								else
									(ScriptID 70 1)
								)
								5
								self
						)
					)
					(71
						(guard
							setMotion:
								Approach
								(if (> (guard y?) 87)
									(ScriptID 71 2)
								else
									(ScriptID 71 1)
								)
								5
								self
						)
					)
					(72
						(guard
							setMotion:
								Approach
								(if (> (guard y?) 91)
									(ScriptID 72 2)
								else
									(ScriptID 72 1)
								)
								5
								self
						)
					)
				)
			)
			(1
				(guard dispose:)
				(self dispose:)
			)
		)
	)
)

(instance arabicScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== register 0)
					(HandsOff)
					(ego
						heading: (GetAngle (ego x?) (ego y?) (guard x?) (guard y?))
					)
					((ego looper?) doit: ego (ego heading?))
				)
				(= cycles 6)
			)
			(1
				(if (== register 0)
					(guard
						heading: (GetAngle (guard x?) (guard y?) (ego x?) (ego y?))
					)
					((guard looper?) doit: guard (guard heading?))
				)
				(= cycles 6)
			)
			(2
				(if (== register 0)
					(proc310_1 guard)
				)
				(= seconds 1)
			)
			(3
				(if (== register 0)
					(proc310_2)
					(Print 306 7)
				)
				(= cycles 2)
			)
			(4
				(proc310_1 guard)
				(= seconds 1)
			)
			(5
				(proc310_2)
				(proc310_1 ego)
				(= seconds 1)
			)
			(6
				(proc310_2)
				(= cycles 1)
				(if (<= (++ register) 3)
					(self init:)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance disMountScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard view: 772 cel: 0 illegalBits: 0 setCycle: EndLoop self)
			)
			(1
				(camel
					view: 772
					loop: (+ (guard loop?) 2)
					cel: 0
					posn: (guard x?) (guard y?)
					init:
				)
				(guard
					view: 472
					setLoop: GradualLooper
					setCycle: Walk
					setStep: 3 2
					posn: (camel x?) (+ (camel y?) 2)
				)
				(self dispose:)
			)
		)
	)
)

(instance guard of Actor
	(properties
		view 172
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/guard,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (or (& signal hideActor) (IsOffScreen self))
							(Print 306 8)
						else
							(Print 306 9)
							(event claimed: TRUE)
						)
					)
					((or (& signal hideActor) (IsOffScreen self))
						(event claimed: FALSE)
					)
					((Said 'address')
						(Print 306 10)
					)
					((Said 'kiss,fuck,hug')
						(Print 306 11)
					)
					((Said 'kill,shoot')
						(if (ego has: iTranquilizerGun)
							(Print 306 12)
						else
							(Print 306 13)
						)
					)
					((Said 'fight,kick,hit')
						(Print 306 14)
					)
					((Said 'greet')
						(Print 306 15)
					)
				)
			)
		)
	)
)

(instance camel of View

	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/camel]>')
				(cond 
					((IsOffScreen self))
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(Print 306 16)
					)
				)
			)
		)
	)
)
