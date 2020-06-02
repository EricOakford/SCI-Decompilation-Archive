;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTFLAME)
(include game.sh) (include "558.shm")
(use Main)
(use Target)
(use Procs)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	CastFlame 0
	dartScript 1
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
	newSound
	wasHandsOn
	hurtEgo
	local12
)
(procedure (CastFlame obj param2 theX theY &tmp temp0 temp1 dart evt)
	(if gClient (return gClient))
	(= global194 100)
	(if (>= argc 3)
		(= theGEgoX theX)
		(= theGEgoY theY)
	else
		(= theGEgoX ((= evt (Event new:)) x?))
		(= theGEgoY (+ (evt y?) 24))
		(evt dispose:)
	)
	(if (not isHandsOff)
		(= wasHandsOn 1)
	)
	(LoadMany SOUND (SoundFX 33) (SoundFX 45))
	(Load VIEW 522)
	(if obj
		(Face ego obj)
		(= oldEgoX (+ (obj x?) (obj targDeltaX?)))
		(= oldEgoY (+ (obj y?) (obj targDeltaY?)))
		(= temp0 (- oldEgoX (ego x?)))
		(= temp1 (- oldEgoY (- (ego y?) 25)))
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
			(and
				(obj isKindOf: TargActor)
				(not
					(TrySkill MAGIC 0 (- 50 (/ (ego distanceTo: obj) 5)))
				)
			)
			(if (< oldEgoY 0)
				(= oldEgoY (+ oldEgoY (Random 30 100)))
			else
				(= oldEgoY (- oldEgoY (Random 30 100)))
			)
		)
	else
		(SkillUsed 10 (/ [egoStats AGIL] 10))
		(= oldEgoX theGEgoX)
		(= oldEgoY theGEgoY)
	)
	((= newSound (Sound new:))
		number: (SoundFX 33)
		priority: 15
		init:
	)
	(= local0 obj)
	((= dart (Actor new:))
		view: 522
		setLoop: 2
		setCel: 0
		illegalBits: 0
		ignoreActors:
		ignoreHorizon:
		z: 34
		setStep: 12 8
		init:
		hide:
		setScript: dartScript (if (>= argc 2) param2 else 0) obj
	)
	(return TRUE)
)

(instance dartScript of Script
	
	(method (doit &tmp temp0)
		(if (IsObject local0)
			(= oldEgoX (+ (local0 x?) (local0 targDeltaX?)))
			(= oldEgoY (+ (local0 y?) (local0 targDeltaY?)))
		)
		(super doit:)
	)
	
	(method (dispose)
		(newSound dispose:)
		(= gClient 0)
		(if wasHandsOn
			(HandsOn)
		)
		(NormalEgo)
		(ego
			loop: (if (ego loop?) 5 else 4)
			priority: oldPriority
			illegalBits: oldIllBits
			signal: oldSignal
			cycleSpeed: oldCycleSpeed
		)
		(RestoreTheCursor)
		(super dispose:)
		(DisposeScript CASTFLAME)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gClient client)
				(= local12 0)
				(= oldSignal (ego signal?))
				(= oldPriority (ego priority?))
				(= oldIllBits (ego illegalBits?))
				(= oldCycleSpeed (ego cycleSpeed?))
				(HandsOff)
				(if (ego onMe: oldEgoX (- oldEgoY 34)) (= hurtEgo 1))
				(ego
					setMotion: 0
					setHeading: (if (<= oldEgoX (ego x?)) 225 else 135) self
				)
			)
			(1
				(theGame setCursor: waitCursor TRUE)
				(ego
					view: 522
					setLoop: (if (== (ego loop?) 4) 0 else 1)
					cel: 0
					cycleSpeed: 6
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(ego setCycle: EndLoop)
				(if hurtEgo (= oldEgoX (ego x?)) (= oldEgoY (ego y?)))
				(newSound play:)
				(client
					posn:
						(if (ego loop?) (- (ego x?) 25) else (+ (ego x?) 25))
						(ego y?)
					show:
					setCycle: Forward
					setMotion: MoveTo oldEgoX oldEgoY self
				)
			)
			(3
				(if (and (Btst fFlag281) (not local12))
					(= state (- state 1))
					(= local12 1)
					(client setMotion: MoveTo (ego x?) (ego y?) self)
				else
					(newSound stop: number: (SoundFX 45) play:)
					(client setLoop: 3 cel: 0 setMotion: 0 setCycle: EndLoop self)
				)
			)
			(4
				(= ticks 12)
			)
			(5
				(if hurtEgo
					(if (not (TakeDamage 100))
						(EgoDead C_DIE_FLAME_SELF)
					else
						(messager say: N_THROWROCK NULL NULL 2 0 SPELLS)
					)
				)
				(cond 
					((IsObject register)
						(register getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
					)
					((and local12 (> [egoStats HEALTH] 0))
						(TakeDamage 100)
					)
				)
				(client dispose:)
			)
		)
	)
)
