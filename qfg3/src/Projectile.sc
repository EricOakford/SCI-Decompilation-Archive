;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROJECTILE) ;32
(include game.sh)
(use Main)
(use Chase)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	project 0
)

(local
	egoLoop
	local1 = [20 -20 10 -10]
	[local5 4]
	local9 =  30
	local10 = [10 -10 -10 10]
	local14 = [-5 -5 -5 -5]
	local18
	egoHeading
	local20
	local21
	local22 = [0 1 2 3 0 1 0 1]
	local30 = [0 1 0 0 0 1 0 1]
	local38 = [2 3 6 7]
)
(instance project of Script

	(method (init actor whoCares theRegister param4)
		(= lastTicks gameTime)
		(if (>= argc 1)
			((= client actor) script: self)
			(if (>= argc 2)
				(= caller whoCares)
				(if (>= argc 3)
					(= register theRegister)
					(if (>= argc 4)
						(= local20 param4)
					else
						(= local20 0)
					)
				)
			)
		)
		(= state (- start 1))
		(self cue:)
	)
		
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(FindTarget
					((User curEvent?) x?)
					((User curEvent?) y?)
				)
				(Face ego projX projY self)
			)
			(1
				(if (or (== register 56) (== register 11))
					(FindTarget 194 90)
				)
				(if (== register 11) (= register 20))
				(= egoHeading (ego heading?))
				(= egoLoop (ego loop?))
				(ego setMotion: 0)
				(ego
					view:
						(cond 
							((< register 75) 9)
							(
								(==
									(if (and (> (ego view?) 17) (< (ego view?) 21)))
									1
								)
								18
							)
							(else 14)
						)
					loop:
						(cond 
							((and (<= 0 egoHeading) (<= egoHeading 85)) (= local18 2))
							((and (<= 86 egoHeading) (<= egoHeading 180)) (= local18 0))
							((and (<= 181 egoHeading) (<= egoHeading 274)) (= local18 1))
							(else (= local18 3))
						)
					setCel: 0
				)
				(cond 
					((< register 75) (ego setCycle: CycleTo 4 1 self))
					(
						(==
							(if (and (> (ego view?) 17) (< (ego view?) 21)))
							1
						)
						(ego setCycle: CycleTo 2 1 self)
					)
					(else (ego setCycle: EndLoop self))
				)
			)
			(2
				(if (OneOf register 33 20)
					(ego useSkill: 10 25)
					(ego useSkill: 0 2)
					(= temp0
						(Random 0 (>> (+ (- 300 [egoStats 10]) 4) $0002))
					)
					(= projX
						(+ projX (if (Random 0 1) (- 0 temp0) else temp0))
					)
					(= projY
						(+ projY (if (Random 0 1) (- 0 temp0) else temp0))
					)
				)
				((ProjObj new:)
					signal: 16
					priority: (ego priority?)
					type: register
					init:
				)
				(switch register
					(81
						(globalSound number: 13 setLoop: 1 play:)
					)
					(83
						(globalSound number: 943 setLoop: 1 play:)
					)
					(88
						(globalSound number: 11 setLoop: 1 play:)
					)
					(else 
						(globalSound number: 916 setLoop: 1 play:)
					)
				)
				(> (ego view?) 17)
				(if (< register 75)
					(ego setCycle: EndLoop self)
				else
					(ego setCycle: BegLoop self)
				)
			)
			(3
				;this was the undecompilable part
				(if (not (and (> (ego view?) 17) (< (ego view?) 21)))
					(ego normalize: egoLoop)
				else
					(ego
						view: 20
						loop: [local38 (ego loop?)]
						cel: (if (< [local38 (ego loop?)] 6) 4 else 5)
					)
				)
				(= cycles 2)
			)
			(4
				(if (not (Btst fCastingSpell))
					(HandsOn)
				)
				(self dispose:)
			)
		)
	)
)

(class ProjObj of Actor
	(properties
		type $0000
		cuedOnce 0
	)
	
	(method (init)
		(if (== type 20) (ego drop: 10 1))
		(if (== type 33) (ego drop: 23 1))
		(self
			view: (if (> type 75) 21 else 46)
			setLoop:
				(switch type
					(20 (+ [local30 egoLoop] 2))
					(33 4)
					(81 2)
					(83 13)
					(32 4)
					(16 6)
					(56 6)
					(else  4)
				)
			x:
				(+
					(ego x?)
					(/
						(*
							(ego scaleY?)
							(switch (ego view?)
								(14 [local1 (ego loop?)])
								(9 [local10 (ego loop?)])
								(18 [local1 (ego loop?)])
								(else  [local10 (ego loop?)])
							)
						)
						128
					)
				)
			y:
				(+
					(ego y?)
					(/
						(*
							(ego scaleY?)
							(switch (ego view?)
								(14 [local5 (ego loop?)])
								(9 [local14 (ego loop?)])
								(else  [local14 (ego loop?)])
							)
						)
						128
					)
				)
			setStep: 8 5
			z: (if (IsObject local20)
				(+ (local20 z?) local9)
			else
				local9
			)
			moveSpeed: 0
			ignoreActors: 1
			ignoreHorizon: 1
			illegalBits: 0
			setScale: -1 ego
			setCycle: (if (== type 56) 0 else Forward)
		)
		(if (IsObject local20)
			(self setMotion: Chase local20 0 self)
		else
			(self setMotion: MoveTo projX (+ projY local9) self)
		)
		(super init:)
		(SetNowSeen self)
	)
	
	(method (doit &tmp temp0 temp1 temp2)
		(super doit: &rest)
		(= temp2 (/ (- nsRight nsLeft) 2))
		(if
			(or
				(and
					(not cuedOnce)
					(IsObject gNewList)
					(= temp0
						(gNewList
							firstTrue: #onMe (+ nsLeft temp2) (+ nsTop temp2)
						)
					)
				)
				local21
			)
			(switch type
				(20
					(= temp1 (+ 8 (/ [egoStats 0] 20)))
				)
				(33
					(= temp1 (+ 5 (/ [egoStats 0] 20)))
				)
				(81
					(= temp1 (+ 10 (/ [egoStats 25] 10)))
				)
				(83
					(= temp1 (+ 10 (/ [egoStats 27] 20)))
				)
				(88
					(= temp1 (+ 12 (/ [egoStats 32] 10)))
				)
			)
			(cond 
				((IsObject local20) (local20 getHurt: type temp1))
				((IsObject temp0) (temp0 getHurt: type temp1))
			)
			(= local21 0)
			(self cue:)
		)
	)
	
	(method (cue)
		(if (not cuedOnce)
			(= cuedOnce TRUE)
			(if (IsObject local20)
				(= local21 1)
			)
			(self setScript: checkLoop self)
		else
			(self setMotion: 0 dispose:)
		)
	)
)

(instance checkLoop of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== (client view?) 21)
					(cond 
						((== (client loop?) 13)
							(client setLoop: 14 setMotion: 0 setCycle: EndLoop self)
							(globalSound number: 930 setLoop: 1 play: 127)
						)
						(
						(and (< -1 (client loop?)) (< (client loop?) 4))
							(client setLoop: 9 setMotion: 0 setCycle: EndLoop self)
							(globalSound number: 930 setLoop: 1 play: 127)
						)
						((< (client loop?) 6)
							(client setLoop: 10 setMotion: 0 setCycle: EndLoop self)
							(globalSound number: 930 setLoop: 1 play:)
						)
						(else (self cue:))
					)
				else
					(globalSound number: 921 setLoop: 1 play: 127)
					(self dispose:)
				)
			)
			(1
				(self dispose:)
			)
		)
	)
)
