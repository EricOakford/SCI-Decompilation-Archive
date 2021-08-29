;;; Sierra Script 1.0 - (do not remove this comment)
(script# 101)
(include game.sh)
(use Main)
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
	gotHit
	savSignal
	savPriority
	savIllegalBits
	projSound
)
(procedure (ThrowKnife atWhat &tmp pushX pushY projectile)
	(if (!= curRoomNum daggerRoom)
		(= daggerRoom curRoomNum)
		(= missedDaggers 0)
	)
	(return
		(if (not (ego has: iDagger))
			(HighPrint 101 0)
			;You do not have any daggers.
			(DisposeScript 101)
			(return FALSE)
		else
			(ego use: iDagger 1)
			(Load SOUND (SoundFX 31))
			(Load SOUND (SoundFX 29))
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
						(< 0 knTargY)
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
			)
			(if (not atWhat)
				(SkillUsed THROW (/ [egoStats AGIL] tryStatThrowing))
				(= knTargX (if (& (ego loop?) 1) -10 else 330))
				(= knTargY (Random 20 80))
			)
			((= projSound (Sound new:))
				number: (SoundFX 31)
				priority: 15
				init:
			)
			(= knTarg atWhat)
			((= projectile (Actor new:))
				view: vEgoThrowingDagger
				setLoop: 2
				setCel: 0
				illegalBits: 0
				ignoreActors:
				ignoreHorizon:
				z: 20
				posn: (ego x?) (- (ego y?) 20)
				setStep: 12 8
				init:
				hide:
				setScript: knifeScript 0 atWhat
			)
			(return TRUE)
		)
	)
)

(instance knifeScript of Script
	;CI: This is a manual decompilation of the asm, which could not be auto-decompiled by the version of SCICompanion I used.
	;EO: I made corrections.
	(method (doit &tmp hitFlag)
		(if (IsObject knTarg)
			(= knTargX (+ (knTarg x?) (knTarg targDeltaX?)))
			(= knTargY (+ (knTarg y?) (knTarg targDeltaY?)))
		)
		(= thisX (- knTargX (client x?)))
		(= thisY (- knTargY (client y?)))

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
;;; 			lsl      knTarg
;;; 			callk    IsObject,  2
;;; 			bnt      code_0250
;;; 			pushi    #x
;;; 			pushi    0
;;; 			lal      knTarg
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaX
;;; 			pushi    0
;;; 			lal      knTarg
;;; 			send     4
;;; 			add     
;;; 			sal      knTargX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			lal      knTarg
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaY
;;; 			pushi    0
;;; 			lal      knTarg
;;; 			send     4
;;; 			add     
;;; 			sal      knTargY
;;; code_0250:
;;; 			pushi    #x
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      knTargX
;;; 			sub     
;;; 			sal      thisX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      knTargY
;;; 			sub     
;;; 			sal      thisY
;;; 			pTos     state
;;; 			ldi      1
;;; 			eq?     
;;; 			bnt      code_02d8
;;; 			pushi    65521
;;; 			lal      thisX
;;; 			lt?     
;;; 			bnt      code_028d
;;; 			pprev   
;;; 			ldi      15
;;; 			lt?     
;;; 			bnt      code_028d
;;; 			pushi    65521
;;; 			lal      thisY
;;; 			lt?     
;;; 			bnt      code_028d
;;; 			pprev   
;;; 			ldi      15
;;; 			lt?     
;;; code_028d:
;;; 			sat      temp0
;;; 			bt       code_02c7
;;; 			pushi    #mover
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			not     
;;; 			bt       code_02c7
;;; 			pushi    0
;;; 			pushi    #x
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			lt?     
;;; 			bnt      code_02ae
;;; 			pprev   
;;; 			ldi      319
;;; 			lt?     
;;; code_02ae:
;;; 			not     
;;; 			bt       code_02c7
;;; 			pushi    0
;;; 			pushi    #y
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			lt?     
;;; 			bnt      code_02c3
;;; 			pprev   
;;; 			ldi      189
;;; 			lt?     
;;; code_02c3:
;;; 			not     
;;; 			bnt      code_02d8
;;; code_02c7:
;;; 			pToa     register
;;; 			bnt      code_02ce
;;; 			lat      temp0
;;; code_02ce:
;;; 			sal      gotHit
;;; 			pushi    #cue
;;; 			pushi    0
;;; 			self     4
;;; 			jmp      code_02de
;;; code_02d8:
;;; 			pushi    #doit
;;; 			pushi    0
;;; 			super    Script,  4
;;; code_02de:
;;; 			ret     
;;; 		)
;;; 	)
	
	(method (dispose)
		(projSound dispose:)
		(HandsOn)
		(if (and gotHit (IsObject register))
			(register getHurt: (+ 5 (/ [egoStats STR] 10)))
		)
		(super dispose:)
		(DisposeScript 101)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= savSignal (ego signal?))
				(= savPriority (ego priority?))
				(= savIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoThrowingDagger
					setLoop: (if (& (ego loop?) $0001) 1 else 0)
					cel: 0
					setCycle: CycleTo 9 1 self
				)
				(++ missedDaggers)
			)
			(1
				(ego setCycle: EndLoop)
				(projSound play:)
				(client
					x: (if (== (ego loop?) 1)
						(- (ego x?) 15)
					else
						(+ (ego x?) 15)
					)
					show:
					setLoop: (+ (ego loop?) 2)
					setMotion: MoveTo knTargX knTargY
				)
			)
			(2
				(client hide:)
				(if gotHit
					(-- missedDaggers)
					(++ hitDaggers)
					(projSound stop: number: (SoundFX 29) play: self)
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
