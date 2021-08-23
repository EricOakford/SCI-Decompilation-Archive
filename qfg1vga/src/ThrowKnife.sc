;;; Sierra Script 1.0 - (do not remove this comment)
(script# THROWKNIFE)
(include game.sh) (include "558.shm")
(use Main)
(use Procs)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	ThrowKnife 0
)

(local
	local0
	theGEgoX
	theGEgoY
	oldEgoX
	oldEgoY
	oldCycleSpeed
	oldSignal
	oldPriority
	oldIllBits
	knifeSound
	wasHandsOn
	hurtEgo
)
(procedure (ThrowKnife obj theX theY &tmp temp0 temp1 knife evt)
	(if gClient
		(return TRUE)
	)
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(if (!= curRoomNum daggerRoom)
		(= daggerRoom curRoomNum)
		(= missedDaggers 0)
	)
	(return
		(if (not (ego has: iDagger))
			(messager say: N_THROWDAGGER NULL NULL 1 0 SPELLS)
			(DisposeScript THROWKNIFE)
			(return 0)
		else
			(if (>= argc 2)
				(= theGEgoX theX)
				(= theGEgoY theY)
			else
				(= theGEgoX ((= evt (Event new:)) x?))
				(= theGEgoY (+ (evt y?) 25))
				(evt dispose:)
			)
			((inventory at: iDagger) dumpIt: TRUE)
			(LoadMany SOUND (SoundFX 31) (SoundFX 29))
			(if obj
				(Face ego obj)
				(= oldEgoX (+ (obj x?) (obj targDeltaX?)))
				(= oldEgoY (+ (obj y?) (obj targDeltaY?)))
				(= temp0 (- oldEgoX (+ (ego x?) 15)))
				(= temp1 (- oldEgoY (- (ego y?) 40)))
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
			((= knifeSound (Sound new:))
				number: (SoundFX 31)
				priority: 15
				init:
			)
			(= local0 obj)
			((= knife (Actor new:))
				view: 524
				setLoop: 2
				setCel: 0
				illegalBits: 0
				ignoreActors:
				ignoreHorizon:
				z: 35
				setStep: 30 20
				init:
				hide:
				setScript: knifeScript 0 obj
			)
			(return 1)
		)
	)
)

(instance knifeScript of Script
	(properties)
	
	(method (doit &tmp temp0)
		(if (IsObject local0)
			(= oldEgoX (+ (local0 x?) (local0 targDeltaX?)))
			(= oldEgoY (+ (local0 y?) (local0 targDeltaY?)))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= gClient 0)
		(knifeSound dispose:)
		(if wasHandsOn
			(HandsOn)
		)
		(NormalEgo)
		(ego
			loop: (if (not (ego loop?)) 0 else 1)
			priority: oldPriority
			illegalBits: oldIllBits
			signal: oldSignal
			cycleSpeed: oldCycleSpeed
		)
		(if (IsObject register)
			(register getHurt: (+ 5 (/ [egoStats 0] 10)))
		)
		(super dispose:)
		(DisposeScript 101)
	)
	
	(method (changeState newState param2)
		(switch (= state newState)
			(0
				(= gClient client)
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldCycleSpeed (ego cycleSpeed?))
				(HandsOff)
				(if (ego onMe: oldEgoX (- oldEgoY 35)) (= hurtEgo 1))
				(ego
					setMotion: 0
					setHeading: (if (<= oldEgoX (ego x?)) 270 else 90)
				)
				(= ticks 30)
			)
			(1
				(ego
					view: 524
					setLoop: (if (== (ego loop?) 0) 0 else 1)
					cel: 0
					cycleSpeed: 8
					setCycle: CycleTo 7 1 self
				)
				(++ missedDaggers)
			)
			(2
				(if hurtEgo (= oldEgoX (ego x?)) (= oldEgoY (ego y?)))
				(knifeSound play:)
				(= param2
					(cond 
						((< (= param2 (Abs (- oldEgoX (ego x?)))) 20) 15)
						((< param2 30) 20)
						((< param2 50) 25)
						((< param2 80) 30)
						(else 35)
					)
				)
				(client
					posn:
						(if (== (ego loop?) 1)
							(- (ego x?) param2)
						else
							(+ (ego x?) param2)
						)
						(ego y?)
					setLoop: (+ (ego loop?) 2)
					setCycle: Forward
					setMotion: MoveTo oldEgoX oldEgoY self
				)
				(ego setCycle: EndLoop)
				(= ticks 6)
			)
			(3 (client show:))
			(4
				(client hide:)
				(if hurtEgo
					(if (not hurtEgo) (-- missedDaggers) (++ hitDaggers))
					(knifeSound stop: number: (SoundFX 29) play: self)
				else
					(self cue:)
				)
			)
			(5
				(if hurtEgo
					(if (not (TakeDamage 50))
						(EgoDead C_DIE_HURT_SELF)
					else
						(messager say: N_THROWDAGGER NULL NULL 2 0 SPELLS)
					)
				)
				(client dispose:)
			)
		)
	)
)
