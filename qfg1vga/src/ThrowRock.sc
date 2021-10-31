;;; Sierra Script 1.0 - (do not remove this comment)
(script# THROWROCK)
(include game.sh) (include "558.shm")
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	ThrowRock 0
)

(local
	rockTarg
	thisX
	thisY
	rockTargX
	rockTargY
	gotHit
	savSignal
	savPriority
	savCycleSpeed
	savMoveSpeed
	savIllegalBits
	projSound
	wasHandsOn
	hurtEgo
)
(procedure (ThrowRock atWhat onX onY &tmp pushX pushY projectile evt [temp4 20])
	(if projObj (return projObj))
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(return
		(if (not (ego has: iRock))
			(messager say: N_ROCK NULL NULL 1 0 SPELLS)
			(DisposeScript THROWROCK)
			(return FALSE)
		else
			(if (>= argc 2)
				(= thisX onX)
				(= thisY onY)
			else
				(= thisX ((= evt (Event new:)) x?))
				(= thisY (+ (evt y?) 25))
				(evt dispose:)
			)
			(ego use: iRock 1)
			(Load SOUND (SoundFX 58))
			(if atWhat
				(Face ego atWhat)
				(= rockTargX (+ (atWhat x?) (atWhat targDeltaX?)))
				(= rockTargY (+ (atWhat y?) (atWhat targDeltaY?)))
				(= pushX (- rockTargX (ego x?)))
				(= pushY (- rockTargY (- (ego y?) 34)))
				(while
					(and
						(< westEdge rockTargX)
						(< rockTargX eastEdge)
						(< westEdge rockTargY)
						(< rockTargY southEdge)
					)
					(+= rockTargX pushX)
					(+= rockTargY pushY)
				)
				(if
					(not
						(TrySkill THROW 0 (- 50 (/ (ego distanceTo: atWhat) 5)))
					)
					(if (< rockTargY 0)
						(+= rockTargY (Random 30 100))
					else
						(-= rockTargY (Random 30 100))
					)
				)
			else
				(SkillUsed THROW (/ [egoStats AGIL] 10))
				(= rockTargX thisX)
				(= rockTargY thisY)
			)
			(= rockTarg atWhat)
			((= projectile (Actor new:))
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
				setScript: rockScript 0 atWhat
			)
			(return TRUE)
		)
	)
)

(instance rockScript of Script
	(method (doit)
		(if (IsObject rockTarg)
			(= rockTargX (+ (rockTarg x?) (rockTarg targDeltaX?)))
			(= rockTargY (+ (rockTarg y?) (rockTarg targDeltaY?)))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= projObj 0)
		(if (IsObject projSound)
			(projSound dispose:)
		)
		(if wasHandsOn
			(HandsOn)
		)
		(NormalEgo)
		(ego
			loop: (if (== (ego loop?) loopS) loopW else loopE)
			priority: savPriority
			illegalBits: savIllegalBits
			signal: savSignal
			cycleSpeed: savCycleSpeed
			moveSpeed: savMoveSpeed
		)
		(if (IsObject register)
			(register getHurt: 1)
		)
		(super dispose:)
		(DisposeScript THROWROCK)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= projObj client)
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(= savCycleSpeed (ego cycleSpeed?))
				(= savMoveSpeed (ego moveSpeed?))
				(HandsOff)
				(if (ego onMe: rockTargX (- rockTargY 35))
					(= hurtEgo TRUE)
				)
				(ego
					setMotion: 0
					setHeading: (if (<= rockTargX (ego x?)) 270 else 90) self
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
				((= projSound (Sound new:))
					number: (SoundFX 58)
					priority: 15
					init:
				)
			)
			(2
				(if hurtEgo
					(= rockTargX (ego x?)) (= rockTargY (ego y?))
				)
				(= temp0
					(cond 
						((< (= temp0 (Abs (- rockTargX (ego x?)))) 20) 10)
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
					setMotion: MoveTo rockTargX rockTargY self
				)
				(ego setCycle: EndLoop)
				(= ticks 6)
			)
			(3
				(client show:)
			)
			(4
				(client hide:)
				(if (or gotHit hurtEgo)
					(projSound play: self)
				else
					(self cue:)
				)
			)
			(5
				(if hurtEgo
					(if (not (TakeDamage 1))
						(EgoDead C_DIE_HURT_SELF)
					else
						(messager say: N_ROCK NULL NULL 2 0 SPELLS)
					)
				)
				(client dispose:)
			)
		)
	)
)
