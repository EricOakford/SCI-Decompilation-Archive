;;; Sierra Script 1.0 - (do not remove this comment)
(script# 102)
(include game.sh)
(use Main)
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
	savIllegalBits
	hitSound
)
(procedure (ThrowRock atWhat &tmp pushX pushY projectile)
	(return
		(if (not (ego has: iRock))
			(HighPrint 102 0)
			;You do not have any rocks.
			(DisposeScript 102)
			(return FALSE)
		else
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
						(< 0 rockTargY)
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
			)
			(if (not atWhat)
				;CI: why call SkillUsed directly, instead of TrySkill
				(SkillUsed THROW (/ [egoStats AGIL] tryStatThrowing))
				(= rockTargX (if (& (ego loop?) 1) -10 else 330))
				(= rockTargY (Random 20 80))
			)
			(= rockTarg atWhat)
			((= projectile (Actor new:))
				view: vEgoThrowing
				setLoop: 4
				setCel: 0
				illegalBits: 0
				ignoreActors:
				ignoreHorizon:
				z: 20
				posn: (- (ego x?) 10) (- (ego y?) 12)
				setStep: 20 8
				init:
				hide:
				setPri: 15
				setScript: rockScript 0 atWhat
			)
			(return TRUE)
		)
	)
)

(instance rockScript of Script
	;CI: This is a manual decompilation of the asm, which could not be auto-decompiled by the version of SCICompanion I used.
	;EO: I made corrections
	(method (doit &tmp hitFlag)
		(if (IsObject rockTarg)
			(= rockTargX (+ (rockTarg x?) (rockTarg targDeltaX?)))
			(= rockTargY (+ (rockTarg y?) (rockTarg targDeltaY?)))
		)
		(= thisX (- rockTargX (client x?)))
		(= thisY (- rockTargY (client y?)))

		(if (and
				(== state 1)
				(or
					(= hitFlag (and (< -15 thisX 15)  (< -15 thisY 15)))
					(not (client mover?))
					(or (not (< 0 (client x?) 319)) (not (< 0 (client y?) 189)))
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
;;; 			lsl      rockTarg
;;; 			callk    IsObject,  2
;;; 			bnt      code_020e
;;; 			pushi    #x
;;; 			pushi    0
;;; 			lal      rockTarg
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaX
;;; 			pushi    0
;;; 			lal      rockTarg
;;; 			send     4
;;; 			add     
;;; 			sal      rockTargX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			lal      rockTarg
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaY
;;; 			pushi    0
;;; 			lal      rockTarg
;;; 			send     4
;;; 			add     
;;; 			sal      rockTargY
;;; code_020e:
;;; 			pushi    #x
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      rockTargX
;;; 			sub     
;;; 			sal      thisX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      rockTargY
;;; 			sub     
;;; 			sal      thisY
;;; 			pTos     state
;;; 			ldi      1
;;; 			eq?     
;;; 			bnt      code_0296
;;; 			pushi    65521
;;; 			lal      thisX
;;; 			lt?     
;;; 			bnt      code_024b
;;; 			pprev   
;;; 			ldi      15
;;; 			lt?     
;;; 			bnt      code_024b
;;; 			pushi    65521
;;; 			lal      thisY
;;; 			lt?     
;;; 			bnt      code_024b
;;; 			pprev   
;;; 			ldi      15
;;; 			lt?     
;;; code_024b:
;;; 			sat      temp0
;;; 			bt       code_0285
;;; 			pushi    #mover
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			not     
;;; 			bt       code_0285
;;; 			pushi    0
;;; 			pushi    #x
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			lt?     
;;; 			bnt      code_026c
;;; 			pprev   
;;; 			ldi      319
;;; 			lt?     
;;; code_026c:
;;; 			not     
;;; 			bt       code_0285
;;; 			pushi    0
;;; 			pushi    #y
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			lt?     
;;; 			bnt      code_0281
;;; 			pprev   
;;; 			ldi      189
;;; 			lt?     
;;; code_0281:
;;; 			not     
;;; 			bnt      code_0296
;;; code_0285:
;;; 			pToa     register
;;; 			bnt      code_028c
;;; 			lat      temp0
;;; code_028c:
;;; 			sal      gotHit
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_029c
;;; code_0296:
;;; 			pushi    #doit
;;; 			pushi    0
;;; 			super    Script,  4
;;; code_029c:
;;; 			ret     
;;; 		)
;;; 	)
	
	(method (dispose)
		(hitSound dispose:)
		(HandsOn)
		(if (and gotHit (IsObject register))
			(register getHurt: TRUE)
		)
		(super dispose:)
		(DisposeScript 102)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoThrowing
					cycleSpeed: 1
					setLoop: (if (& (ego loop?) 1) 2 else 3)
					cel: 0
					setCycle: CycleTo 3 1 self
				)
				((= hitSound (Sound new:))
					number: (SoundFX 58)
					priority: 15
					init:
				)
			)
			(1
				(ego setCycle: EndLoop)
				(client
					x: (if (== (ego loop?) 2)
						(- (ego x?) 10)
					else
						(+ (ego x?) 10)
					)
					show:
					setCycle: Forward
					setMotion: MoveTo rockTargX rockTargY
				)
			)
			(2
				(client hide:)
				(if gotHit
					(hitSound play: self)
				else
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
