;;; Sierra Script 1.0 - (do not remove this comment)
(script# 311)
(include game.sh)
(use Main)
(use Intrface)
(use tunisia)
(use EgoDead)
(use Approach)
(use GoToSaid)
(use Chase)
(use Sight)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	guardfReg 0
)

(local
	egoX
	egoY
	egoLoop
	theSeconds
	local4
	local5
)
(instance guardfReg of Region
	
	(method (init)
		(super init:)
		(Load VIEW 472)
		(guard setAvoider: Avoider setCycle: Walk init: hide:)
		(cond 
			(
				(and
					(== curRoomNum 75)
					(ego has: iMap)
					(not ((inventory at: iMap) loop?))
				)
				(= theSeconds 30)
				(guard setScript: hiddenScript)
			)
			((and (== curRoomNum 74) (== prevRoomNum 75))
				(= theSeconds 20)
				(guard setScript: roomChaseScript)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(= egoLoop (ego loop?))
			)
			((== curRoomNum 76)
				(if (and (ego has: iMap) ((inventory at: iMap) loop?))
					(= theSeconds 10)
				else
					(= theSeconds 40)
				)
				(if (not howFast)
					(*= theSeconds 2)
				)
				(guard setScript: roomChaseScript)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(= egoLoop (ego loop?))
			)
			(else
				(if (== curRoomNum 73)
					(= theSeconds 2)
				)
				(guard setScript: roomChaseScript)
				(= egoX (ego x?))
				(= egoY (ego y?))
				(= egoLoop (ego loop?))
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep (OneOf roomNum 73 74 75 76))
		(= initialized FALSE)
		(super newRoom: &rest)
	)
)

(instance hiddenScript of Script

	(method (doit)
		(super doit: &rest)
		(if
			(and
				(> state 0)
				(not (CantBeSeen ego guard 100))
				(!= (ego onControl: origin) cBLUE)
			)
			(guard setScript: rm75ChaseScript)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 12))
			(1
				(guard
					view: 472
					show:
					posn: 328 139
					setMotion: MoveTo 277 139 self
				)
			)
			(2 (= seconds 3))
			(3
				(guard setMotion: MoveTo 263 153 self)
			)
			(4
				(guard setMotion: MoveTo 152 153 self)
				((guard avoider?) offScreenOK: 1)
			)
			(5
				(guard setMotion: MoveTo 62 243 self)
			)
			(6 (= seconds 18))
			(7 (self init:))
		)
	)
)

(instance rm75ChaseScript of Script

	(method (doit)
		(super doit: &rest)
		(if (== (ego onControl: origin) cGREEN)
			(guard setScript: rm75FoundScript)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard setMotion: Chase ego 20 self)
			)
			(1
				(if (and (ego has: iFish) (not (ego has: iCapsule)))
					(client setScript: caughtWithFishScript)
				else
					(client setScript: caughtEgoScript)
				)
			)
		)
	)
)

(instance rm75FoundScript of Script
	
	(method (doit)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not register)
					(Print 311 0)
					(HandsOff)
					(= local4 (= register 1))
				)
				(= cycles 1)
			)
			(1
				(cond 
					((guard inRect: 121 150 319 209)
						(guard setMotion: MoveTo 120 153 self)
					)
					((guard inRect: 94 143 120 153)
						(guard setMotion: MoveTo 110 139 self)
					)
					((guard inRect: 54 176 121 209)
						(guard setMotion: MoveTo 130 176 self)
					)
					((and (ego inRect: 67 154 120 175) (> (ego x?) 100))
						(guard setMotion: MoveTo 110 139 self)
					)
					((guard inRect: 121 143 181 149)
						(guard setMotion: MoveTo 110 135 self)
					)
					((guard inRect: 236 128 319 148)
						(guard setMotion: MoveTo 307 127 self)
					)
					((guard inRect: 287 110 319 127)
						(guard setMotion: MoveTo 286 115 self)
					)
					((guard inRect: 226 110 286 127)
						(guard setMotion: MoveTo 225 115 self)
					)
					(else (guard setMotion: Chase ego 20 self)
						(= register 2)
					)
				)
			)
			(2
				(if (== register 2)
					(if (and (ego has: iFish) (not (ego has: iCapsule)))
						(client setScript: caughtWithFishScript)
					else
						(client setScript: caughtEgoScript)
					)
				else
					(self init:)
				)
			)
		)
	)
)

(instance roomChaseScript of Script
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== curRoomNum 75)
				(> state 0)
				(== (ego onControl: origin) cGREEN)
			)
			(guard setMotion: 0 setScript: rm75FoundScript)
		)
	)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(if (== theSeconds 0) (= theSeconds 8))
				(= theSeconds (- (= seconds theSeconds) 2))
			)
			(1
				(if (and (== curRoomNum 76) (> (ego y?) 154))
					(= temp0 330)
					(= temp1 170)
				else
					(= temp0 egoX)
					(= temp1 egoY)
				)
				(if
					(and
						(< (Abs (- (ego x?) temp0)) 25)
						(< (Abs (- (ego y?) temp1)) 25)
					)
					(= theSeconds (+ theSeconds 2))
					(self init:)
				else
					(guard
						show:
						loop: egoLoop
						posn: temp0 temp1
						setMotion: Chase ego 20 self
					)
				)
			)
			(2
				(if (and (ego has: iFish) (not (ego has: iCapsule)))
					(client setScript: caughtWithFishScript)
				else
					(client setScript: caughtEgoScript)
				)
			)
		)
	)
)

(instance caughtEgoScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== register 0) (InitArab guard))
				(= seconds 2)
			)
			(1
				(if (== register 0)
					(DisposeArab)
					(if (not local4) (Print 311 0))
					(ego
						heading: (GetAngle (ego x?) (ego y?) (guard x?) (guard y?))
					)
					((ego looper?) doit: ego (ego heading?))
				)
				(= cycles 2)
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
						(Print 311 1)
						(= cycles 1)
					)
					((and (ego has: iMap) ((inventory at: iMap) loop?))
						(Print 311 2)
						(= cycles 1)
					)
					((ego has: iIDCard)
						(client setScript: hasIDScript)
					)
					(else
						(Print 311 3)
						(= cycles 1)
					)
				)
			)
			(6
				(EgoDead 970 0 0 311 4)
			)
		)
	)
)

(instance caughtWithFishScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard setMotion: Chase ego 20 self)
			)
			(1
				(ego
					heading: (GetAngle (ego x?) (ego y?) (guard x?) (guard y?))
				)
				((ego looper?) doit: ego (ego heading?))
				(= cycles 2)
			)
			(2
				(InitArab guard)
				(= seconds 1)
			)
			(3
				(InitArab ego)
				(= seconds 1)
			)
			(4
				(Print 311 5)
				(EgoDead 970 0 0 311 4)
			)
		)
	)
)

(instance hasIDScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Print 311 6)
				(= cycles 1)
			)
			(1
				(Print 311 7)
				(Print 311 8)
				(guardfReg setScript: guardLeaveScript)
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
					(75
						(guard setMotion: Approach (ScriptID 75 1) 5 self)
					)
					(76
						(guard setMotion: Approach (ScriptID 76 1) 5 self)
					)
					(73
						(guard setMotion: Approach (ScriptID 73 1) 5 self)
					)
					(74
						(guard setMotion: Approach (ScriptID 74 1) 5 self)
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

(instance guard of Actor
	(properties
		view 472
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said '[/guard,man]>')
				(cond 
					((TurnIfSaid self event 'look[<at]/*'))
					((Said 'look[<at]')
						(if (or (& signal hideActor) (IsOffScreen self))
							(Print 311 9)
						else
							(Print 311 10)
							(event claimed: TRUE)
						)
					)
					((or (& signal hideActor) (IsOffScreen self))
						(event claimed: FALSE)
					)
					((Said 'address')
						(Print 311 11)
					)
					((Said 'kiss,fuck,hug')
						(Print 311 12)
					)
					((Said 'kill,shoot')
						(if (ego has: iTranquilizerGun)
							(Print 311 13)
						else
							(Print 311 14)
						)
					)
					((Said 'fight,kick,hit')
						(Print 311 15)
					)
					((Said 'greet')
						(Print 311 16)
					)
				)
			)
		)
	)
)
