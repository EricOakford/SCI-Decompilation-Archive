;;; Sierra Script 1.0 - (do not remove this comment)
(script# 100)
(include game.sh)
(use Main)
(use Target)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	CastDart 0
)

(local
	dartTarg
	thisX
	thisY
	dartTargX
	dartTargY
	gotHit
	savSignal
	savPriority
	savIllegalBits
	projSound
)
(procedure (CastDart atWhat &tmp pushX pushY projectile)
	(LoadMany SOUND (SoundFX 33) (SoundFX 45))
	(Load VIEW vEgoMagicFlameDart)
	(if atWhat
		(Face ego atWhat)
		(= dartTargX (+ (atWhat x?) (atWhat targDeltaX?)))
		(= dartTargY (+ (atWhat y?) (atWhat targDeltaY?)))
		(= pushX (- dartTargX (ego x?)))
		(= pushY (- dartTargY (- (ego y?) 20)))
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
		;CI: NOTE: This is probably a bug. Much of this code was copy/pasted from the ThrowRocks script, and this was likely missed.
		; it doesn't make sense to increase your throwing skill by casting Flame Dart.
		(SkillUsed THROW (/ [egoStats AGIL] tryStatThrowing))
		(= dartTargX (if (== (ego loop?) 1) -10 else 330))
		(= dartTargY (Random 20 80))
	)
	((= projSound (Sound new:))
		number: (SoundFX 33)
		priority: 15
		init:
	)
	(= dartTarg atWhat)
	((= projectile (Actor new:))
		view: vEgoMagicFlameDart
		setLoop: 2
		setCel: 0
		illegalBits: 0
		ignoreActors:
		ignoreHorizon:
		z: 20
		posn: (ego x?) (- (ego y?) 6)
		setStep: 12 8
		init:
		hide:
		setScript: dartScript 0 atWhat
	)
	(return TRUE)
)

(instance dartScript of Script
	;CI: This is a manual decompilation of the asm, which could not be auto-decompiled by the version of SCICompanion I used.
	;EO: I made corrections.
	(method (doit &tmp hitFlag)
		(if (IsObject dartTarg)
			(= dartTargX (+ (dartTarg x?) (dartTarg targDeltaX?)))
			(= dartTargY (+ (dartTarg y?) (dartTarg targDeltaY?)))
		)
		(= thisX (- dartTargX (client x?)))
		(= thisY (- dartTargY (client y?)))

		(if (and
				(== state 1)
				(or
					(= hitFlag (and (< -15 thisX 15)  (< -15 thisY 15)))
					(not (client mover?))
					;(or (not (< 0 (client x?) 319)) (not (< 0 (client y?) 189)))
				)
			)
			(= gotHit (and register	hitFlag))
			(self cue:)
		else
			(super doit:)
		)
	)
		
;;; 	(method (doit &tmp temp0)
;;; 		(asm
;;; 			pushi    1
;;; 			lsl      dartTarg
;;; 			callk    IsObject,  2
;;; 			bnt      code_021d
;;; 			pushi    #x
;;; 			pushi    0
;;; 			lal      dartTarg
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaX
;;; 			pushi    0
;;; 			lal      dartTarg
;;; 			send     4
;;; 			add     
;;; 			sal      dartTargX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			lal      dartTarg
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaY
;;; 			pushi    0
;;; 			lal      dartTarg
;;; 			send     4
;;; 			add     
;;; 			sal      dartTargY
;;; code_021d:
;;; 			pushi    #x
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      dartTargX
;;; 			sub     
;;; 			sal      thisX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      dartTargY
;;; 			sub     
;;; 			sal      thisY
;;; 			pTos     state
;;; 			ldi      1
;;; 			eq?     
;;; 			bnt      code_02b3
;;; 			pushi    65511
;;; 			lal      thisX
;;; 			lt?     
;;; 			bnt      code_025a
;;; 			pprev   
;;; 			ldi      25
;;; 			lt?     
;;; 			bnt      code_025a
;;; 			pushi    65511
;;; 			lal      thisY
;;; 			lt?     
;;; 			bnt      code_025a
;;; 			pprev   
;;; 			ldi      25
;;; 			lt?     
;;; code_025a:
;;; 			sat      temp0
;;; 			bt       code_02a2
;;; 			pushi    #mover
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			not     
;;; 			bt       code_02a2
;;; 			pushi    4
;;; 			dup     
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			pushi    #y
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lsl      dartTargX
;;; 			lsl      dartTargY
;;; 			callk    GetDistance,  8
;;; 			push    
;;; 			pushi    25
;;; 			pushi    4
;;; 			dup     
;;; 			pushi    0
;;; 			lag      ego
;;; 			send     4
;;; 			push    
;;; 			pushi    #y
;;; 			pushi    0
;;; 			lag      ego
;;; 			send     4
;;; 			push    
;;; 			lsl      dartTargX
;;; 			lsl      dartTargY
;;; 			callk    GetDistance,  8
;;; 			add     
;;; 			gt?     
;;; 			bnt      code_02b3
;;; code_02a2:
;;; 			pToa     register
;;; 			bnt      code_02a9
;;; 			lat      temp0
;;; code_02a9:
;;; 			sal      gotHit
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_02b9
;;; code_02b3:
;;; 			pushi    #doit
;;; 			pushi    0
;;; 			super    Script,  4
;;; code_02b9:
;;; 			ret     
;;; 		)
;;; 	)
	
	(method (dispose)
		(projSound dispose:)
		(HandsOn)
		(if (and gotHit (IsObject register))
			(register getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
		)
		(super dispose:)
		(DisposeScript 100)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoMagicFlameDart
					setLoop: (if (or (== (ego loop?) 1) (== (ego loop?) 3))
						1
					else
						0
					)
					cel: 0
					setCycle: CycleTo 5 1 self
				)
			)
			(1
				(ego setCycle: EndLoop)
				(projSound play:)
				(client
					x: (if (== (ego loop?) 1)
						(- (ego x?) 19)
					else
						(+ (ego x?) 19)
					)
					show:
					setCycle: Forward
					setMotion: MoveTo dartTargX dartTargY
				)
			)
			(2
				(if gotHit
					(projSound stop: number: (SoundFX 45) play:)
					(client setLoop: 3 cel: 0 setMotion: 0 setCycle: EndLoop self)
				else
					(client hide:)
					(= cycles 1)
				)
			)
			(3
				(NormalEgo)
				(ego
					priority: savPriority
					illegalBits: savIllegalBits
					signal: savSignal
				)
				(client dispose:)
			)
		)
	)
)
