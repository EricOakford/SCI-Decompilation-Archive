;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTDART)
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
	CastDart 0
	dartScript 1
)

(local
	dartTarg
	thisX
	thisY
	dartTargX
	dartTargY
	savSpeed
	savSignal
	savPriority
	savIllegalBits
	projSound
	wasHandsOn
	hurtEgo
	dartReflected
)
(procedure (CastDart atWhat whoCares onX onY &tmp pushX pushY projectile evt)
	(if projObj
		(return projObj)
	)
	(= global194 100)
	(if (>= argc 3)
		(= thisX onX)
		(= thisY onY)
	else
		(= thisX ((= evt (Event new:)) x?))
		(= thisY (+ (evt y?) 24))
		(evt dispose:)
	)
	(if (not isHandsOff)
		(= wasHandsOn TRUE)
	)
	(LoadMany SOUND (SoundFX 33) (SoundFX 45))
	(Load VIEW 522)
	(if atWhat
		(Face ego atWhat)
		(= dartTargX (+ (atWhat x?) (atWhat targDeltaX?)))
		(= dartTargY (+ (atWhat y?) (atWhat targDeltaY?)))
		(= pushX (- dartTargX (ego x?)))
		(= pushY (- dartTargY (- (ego y?) 25)))
		(while
			(and
				(< westEdge dartTargX)
				(< dartTargX eastEdge)
				(< westEdge dartTargY)
				(< dartTargY southEdge)
			)
			(+= dartTargX pushX)
			(+= dartTargY pushY)
		)
		(if
			(and
				(atWhat isKindOf: TargActor)
				(not
					(TrySkill MAGIC 0 (- 50 (/ (ego distanceTo: atWhat) 5)))
				)
			)
			(if (< dartTargY 0)
				(+= dartTargY (Random 30 100))
			else
				(+= dartTargY (Random 30 100))
			)
		)
	else
		(SkillUsed THROW (/ [egoStats AGIL] 10))
		(= dartTargX thisX)
		(= dartTargY thisY)
	)
	((= projSound (Sound new:))
		number: (SoundFX 33)
		priority: 15
		init:
	)
	(= dartTarg atWhat)
	((= projectile (Actor new:))
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
		setScript: dartScript (if (>= argc 2) whoCares else 0) atWhat
	)
	(return TRUE)
)

(instance dartScript of Script
	(method (doit &tmp temp0)
		(if (IsObject dartTarg)
			(= dartTargX (+ (dartTarg x?) (dartTarg targDeltaX?)))
			(= dartTargY (+ (dartTarg y?) (dartTarg targDeltaY?)))
		)
		(super doit:)
	)
	
	(method (dispose)
		(projSound dispose:)
		(= projObj 0)
		(if wasHandsOn
			(HandsOn)
		)
		(NormalEgo)
		(ego
			loop: (if (ego loop?) loopSW else loopSE)
			priority: savPriority
			illegalBits: savIllegalBits
			signal: savSignal
			cycleSpeed: savSpeed
		)
		(LoadCurIcon)
		(super dispose:)
		(DisposeScript CASTDART)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= projObj client)
				(= dartReflected FALSE)
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(= savSpeed (ego cycleSpeed?))
				(HandsOff)
				(if (ego onMe: dartTargX (- dartTargY 34))
					(= hurtEgo TRUE)
				)
				(ego
					setMotion: 0
					setHeading: (if (<= dartTargX (ego x?)) 225 else 135) self
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
				(if hurtEgo
					(= dartTargX (ego x?))
					(= dartTargY (ego y?))
				)
				(projSound play:)
				(client
					posn:
						(if (ego loop?) (- (ego x?) 25) else (+ (ego x?) 25))
						(ego y?)
					show:
					setCycle: Forward
					setMotion: MoveTo dartTargX dartTargY self
				)
			)
			(3
				(if (and (Btst fKoboldProtected) (not dartReflected))
					(-= state 1)
					(= dartReflected TRUE)
					(client setMotion: MoveTo (ego x?) (ego y?) self)
				else
					(projSound stop: number: (SoundFX 45) play:)
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
						(messager say: N_ROCK NULL NULL 2 0 SPELLS)
					)
				)
				(cond 
					((IsObject register)
						(register getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
					)
					((and dartReflected (> [egoStats HEALTH] 0))
						(TakeDamage 100)
					)
				)
				(client dispose:)
			)
		)
	)
)
