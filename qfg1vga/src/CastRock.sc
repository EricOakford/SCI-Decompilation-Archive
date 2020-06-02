;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTROCK)
(include game.sh) (include "558.shm")
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	CastRock 0
)

(local
	local0
	theGEgoX
	theGEgoY
	oldEgoX
	oldEgoY
	local5
	oldSignal
	oldPriority
	oldCycleSpeed
	oldMoveSpeed
	oldIllBits
	rockSound
	wasHandsOn
	hurtEgo
)
(procedure (CastRock obj theX theY &tmp temp0 temp1 rock evt [temp4 20])
	(if gClient (return gClient))
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(return
		(if (not (ego has: iRock))
			(messager say: N_THROWROCK NULL NULL 1 0 SPELLS)
			(DisposeScript CASTROCK)
			(return FALSE)
		else
			(if (>= argc 2)
				(= theGEgoX theX)
				(= theGEgoY theY)
			else
				(= theGEgoX ((= evt (Event new:)) x?))
				(= theGEgoY (+ (evt y?) 25))
				(evt dispose:)
			)
			(ego use: iRock 1)
			(Load SOUND (SoundFX 58))
			(if obj
				(Face ego obj)
				(= oldEgoX (+ (obj x?) (obj targDeltaX?)))
				(= oldEgoY (+ (obj y?) (obj targDeltaY?)))
				(= temp0 (- oldEgoX (ego x?)))
				(= temp1 (- oldEgoY (- (ego y?) 34)))
				(while
					(and
						(< 0 oldEgoX)
						(< oldEgoX 319)
						(< 0 oldEgoY)
						(< oldEgoY 189)
					)
					(= oldEgoX (+ oldEgoX temp0))
					(= oldEgoY (+ oldEgoY temp1))
				)
				(if
					(not
						(TrySkill THROW 0 (- 50 (/ (ego distanceTo: obj) 5)))
					)
					(if (< oldEgoY 0)
						(= oldEgoY (+ oldEgoY (Random 30 100)))
					else
						(= oldEgoY (- oldEgoY (Random 30 100)))
					)
				)
			else
				(SkillUsed THROW (/ [egoStats AGIL] 10))
				(= oldEgoX theGEgoX)
				(= oldEgoY theGEgoY)
			)
			(= local0 obj)
			((= rock (Actor new:))
				view: 510
				setLoop: 4
				setCel: 0
				illegalBits: 0
				ignoreActors:
				ignoreHorizon:
				z: 35
				setStep: 25 18
				init:
				hide:
				setScript: rockScript 0 obj
			)
			(return 1)
		)
	)
)

(instance rockScript of Script
	(properties)
	
	(method (doit)
		(if (IsObject local0)
			(= oldEgoX (+ (local0 x?) (local0 targDeltaX?)))
			(= oldEgoY (+ (local0 y?) (local0 targDeltaY?)))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= gClient 0)
		(if (IsObject rockSound)
			(rockSound dispose:)
		)
		(if wasHandsOn
			(HandsOn)
		)
		(NormalEgo)
		(ego
			loop: (if (== (ego loop?) 2) 1 else 0)
			priority: oldPriority
			illegalBits: oldIllBits
			signal: oldSignal
			cycleSpeed: oldCycleSpeed
			moveSpeed: oldMoveSpeed
		)
		(if (IsObject register)
			(register getHurt: 1)
		)
		(super dispose:)
		(DisposeScript CASTROCK)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= gClient client)
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldCycleSpeed (ego cycleSpeed?))
				(= oldMoveSpeed (ego moveSpeed?))
				(HandsOff)
				(if (ego onMe: oldEgoX (- oldEgoY 35)) (= hurtEgo 1))
				(ego
					setMotion: 0
					setHeading: (if (<= oldEgoX (ego x?)) 270 else 90) self
				)
			)
			(1
				(ego
					view: 510
					setLoop: (if (== (ego loop?) 0) 3 else 2)
					cel: 0
					cycleSpeed: 8
					setCycle: CycleTo 3 1 self
				)
				((= rockSound (Sound new:))
					number: (SoundFX 58)
					priority: 15
					init:
				)
			)
			(2
				(if hurtEgo (= oldEgoX (ego x?)) (= oldEgoY (ego y?)))
				(= temp0
					(cond 
						((< (= temp0 (Abs (- oldEgoX (ego x?)))) 20) 10)
						((< temp0 30) 15)
						((< temp0 50) 20)
						((< temp0 80) 25)
						(else 30)
					)
				)
				(client
					posn:
						(if (== (ego loop?) 2)
							(- (ego x?) temp0)
						else
							(+ (ego x?) temp0)
						)
						(ego y?)
					setCycle: Forward
					setMotion: MoveTo oldEgoX oldEgoY self
				)
				(ego setCycle: EndLoop)
				(= ticks 6)
			)
			(3
				(client show:)
			)
			(4
				(client hide:)
				(if (or local5 hurtEgo)
					(rockSound play: self)
				else
					(self cue:)
				)
			)
			(5
				(if hurtEgo
					(if (not (TakeDamage 1))
						(EgoDead C_DIE_HURT_SELF)
					else
						(messager say: N_THROWROCK NULL NULL 2 0 SPELLS)
					)
				)
				(client dispose:)
			)
		)
	)
)
