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
	knTarg
	thisX
	thisY
	knTargX
	knTargY
	savSpeed
	savSignal
	savPriority
	savIllegalBits
	projSound
	wasHandsOn
	hurtEgo
)
(procedure (ThrowKnife atWhat onX onY &tmp pushX pushY projectile evt)
	(if projObj
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
			(messager say: N_DAGGER NULL NULL 1 0 SPELLS)
			(DisposeScript THROWKNIFE)
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
			((inventory at: iDagger) dumpIt: TRUE)	;so ego can pick it back up
			(LoadMany SOUND (SoundFX 31) (SoundFX 29))
			(if atWhat
				(Face ego atWhat)
				(= knTargX (+ (atWhat x?) (atWhat targDeltaX?)))
				(= knTargY (+ (atWhat y?) (atWhat targDeltaY?)))
				(= pushX (- knTargX (+ (ego x?) 15)))
				(= pushY (- knTargY (- (ego y?) 40)))
				(while
					(and
						(< westEdge knTargX)
						(< knTargX eastEdge)
						(< westEdge knTargY)
						(< knTargY southEdge)
					)
					(+= knTargX pushX)
					(+= knTargY pushY)
				)
				(if
					(not
						(TrySkill THROW 0 (- 50 (/ (ego distanceTo: atWhat) 5)))
					)
					(if (< knTargY 0)
						(+= knTargY (Random 30 100))
					else
						(-= knTargY (Random 30 100))
					)
				)
			else
				(SkillUsed THROW (/ [egoStats AGIL] 10))
				(= knTargX thisX)
				(= knTargY thisY)
			)
			((= projSound (Sound new:))
				number: (SoundFX 31)
				priority: 15
				init:
			)
			(= knTarg atWhat)
			((= projectile (Actor new:))
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
				setScript: knifeScript 0 atWhat
			)
			(return TRUE)
		)
	)
)

(instance knifeScript of Script
	(method (doit &tmp temp0)
		(if (IsObject knTarg)
			(= knTargX (+ (knTarg x?) (knTarg targDeltaX?)))
			(= knTargY (+ (knTarg y?) (knTarg targDeltaY?)))
		)
		(super doit:)
	)
	
	(method (dispose)
		(= projObj 0)
		(projSound dispose:)
		(if wasHandsOn
			(HandsOn)
		)
		(NormalEgo)
		(ego
			loop: (if (not (ego loop?)) 0 else 1)
			priority: savPriority
			illegalBits: savIllegalBits
			signal: savSignal
			cycleSpeed: savSpeed
		)
		(if (IsObject register)
			(register getHurt: (+ 5 (/ [egoStats STR] 10)))
		)
		(super dispose:)
		(DisposeScript 101)
	)
	
	(method (changeState newState param2)
		(switch (= state newState)
			(0
				(= projObj client)
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(= savSpeed (ego cycleSpeed?))
				(HandsOff)
				(if (ego onMe: knTargX (- knTargY 35))
					(= hurtEgo TRUE)
				)
				(ego
					setMotion: 0
					setHeading: (if (<= knTargX (ego x?)) 270 else 90)
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
				(if hurtEgo
					(= knTargX (ego x?)) (= knTargY (ego y?))
				)
				(projSound play:)
				(= param2
					(cond 
						((< (= param2 (Abs (- knTargX (ego x?)))) 20) 15)
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
					setMotion: MoveTo knTargX knTargY self
				)
				(ego setCycle: EndLoop)
				(= ticks 6)
			)
			(3
				(client show:)
			)
			(4
				(client hide:)
				(if hurtEgo
					(if (not hurtEgo)
						(-- missedDaggers)
						(++ hitDaggers)
					)
					(projSound stop: number: (SoundFX 29) play: self)
				else
					(self cue:)
				)
			)
			(5
				(if hurtEgo
					(if (not (TakeDamage 50))
						(EgoDead C_DIE_HURT_SELF)
					else
						(messager say: N_DAGGER NULL NULL 2 0 SPELLS)
					)
				)
				(client dispose:)
			)
		)
	)
)
