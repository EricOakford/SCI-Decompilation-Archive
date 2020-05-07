;;; Sierra Script 1.0 - (do not remove this comment)
(script# 101)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	KnifeCast 0
)

(local
	whoTarget
	distX
	distY
	targetX
	targetY
	isHitTarget
	egoSignal
	egoPriority
	egoIllegalBits
	newSound
)
(procedure (KnifeCast target &tmp temp0 temp1 newAct_2)
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
			(if target
				(Face ego target)
				(= targetX (+ (target x?) (target targDeltaX?)))
				(= targetY (+ (target y?) (target targDeltaY?)))
				(= temp0 (- targetX (+ (ego x?) 15)))
				(= temp1 (- targetY (- (ego y?) 40)))
				(while
					(and
						(< westEdge targetX)
						(< targetX eastEdge)
						(< 0 targetY)
						(< targetY southEdge)
					)
					(= targetX (+ targetX temp0))
					(= targetY (+ targetY temp1))
				)
				(if
					(not
						(TrySkill THROW 0 (- 50 (/ (ego distanceTo: target) 5)))
					)
					(if (< targetY 0)
						(= targetY (+ targetY (Random 30 100)))
					else
						(= targetY (- targetY (Random 30 100)))
					)
				)
			)
			(if (not target)
				(SkillUsed THROW (/ [egoStats AGIL] tryStatThrowing))
				(= targetX (if (& (ego loop?) $0001) -10 else 330))
				(= targetY (Random 20 80))
			)
			((= newSound (Sound new:))
				number: (SoundFX 31)
				priority: 15
				init:
			)
			(= whoTarget target)
			((= newAct_2 (Actor new:))
				view: vEgoThrowDagger
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
				setScript: knifeScript 0 target
			)
			(return TRUE)
		)
	)
)

(instance knifeScript of Script
	(properties)

	;CI: This is a manual decompilation of the asm, which could not be auto-decompiled by the version of SCICompanion I used.
	(method (doit &tmp tmpHitTarget)
		(if (IsObject whoTarget)
			(= targetX (+ (whoTarget x?) (whoTarget targDeltaX?)))
			(= targetY (+ (whoTarget y?) (whoTarget targDeltaY?)))
		)
		(= distX (- targetX (client x?)))
		(= distY (- targetY (client y?)))

		(cond
			(	(and (== state 1) 
					 (or (= tmpHitTarget ;if we're within 15 pixels of the target, consider it a hit.
							(and (< -15 distX) (< distX 15)
								 (< -15 distY) (< distY 15)
							)
						 )	;And if it's a hit, we advance the script to the next state.
						(not (client mover?))	; or if the projectile has stopped moving.
						(not (and (< 0 (client x?)) (< (client x?) 319)))	;or the projectile is offscreen left or right
						(not (and (< 0 (client y?)) (< (client y?) 189)))	;or the projectile is offscreen top or bottom.
					 )
				)
				(if (not register) ;register is the target object. if there is noobject, then set the HitFlag to FALSE.
					(= tmpHitTarget FALSE)
				)
				(= isHitTarget tmpHitTarget)
				(self cue:)
			)
			(else
				(super doit:)
			)
		)
	)
		
;;; 	(method (doit &tmp temp0)
;;; 		(asm
;;; 			pushi    1
;;; 			lsl      whoTarget
;;; 			callk    IsObject,  2
;;; 			bnt      code_0250
;;; 			pushi    #x
;;; 			pushi    0
;;; 			lal      whoTarget
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaX
;;; 			pushi    0
;;; 			lal      whoTarget
;;; 			send     4
;;; 			add     
;;; 			sal      targetX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			lal      whoTarget
;;; 			send     4
;;; 			push    
;;; 			pushi    #targDeltaY
;;; 			pushi    0
;;; 			lal      whoTarget
;;; 			send     4
;;; 			add     
;;; 			sal      targetY
;;; code_0250:
;;; 			pushi    #x
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      targetX
;;; 			sub     
;;; 			sal      distX
;;; 			pushi    #y
;;; 			pushi    0
;;; 			pToa     client
;;; 			send     4
;;; 			push    
;;; 			lal      targetY
;;; 			sub     
;;; 			sal      distY
;;; 			pTos     state
;;; 			ldi      1
;;; 			eq?     
;;; 			bnt      code_02d8
;;; 			pushi    65521
;;; 			lal      distX
;;; 			lt?     
;;; 			bnt      code_028d
;;; 			pprev   
;;; 			ldi      15
;;; 			lt?     
;;; 			bnt      code_028d
;;; 			pushi    65521
;;; 			lal      distY
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
;;; 			sal      isHitTarget
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
		(newSound dispose:)
		(HandsOn)
		(if (and isHitTarget (IsObject register))
			(register getHurt: (+ 5 (/ [egoStats STR] 10)))
		)
		(super dispose:)
		(DisposeScript 101)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoSignal (ego signal?))
				(= egoPriority (ego priority?))
				(= egoIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoThrowDagger
					setLoop: (if (& (ego loop?) $0001) 1 else 0)
					cel: 0
					setCycle: CycleTo 9 1 self
				)
				(++ missedDaggers)
			)
			(1
				(ego setCycle: EndLoop)
				(newSound play:)
				(client
					x: (if (== (ego loop?) 1)
						(- (ego x?) 15)
					else
						(+ (ego x?) 15)
					)
					show:
					setLoop: (+ (ego loop?) 2)
					setMotion: MoveTo targetX targetY
				)
			)
			(2
				(client hide:)
				(if isHitTarget
					(-- missedDaggers)
					(++ hitDaggers)
					(newSound stop: number: (SoundFX 29) play: self)
				else
					(= cycles 1)
				)
			)
			(3
				(NormalEgo)
				(ego
					priority: egoPriority
					illegalBits: egoIllegalBits
					signal: egoSignal
				)
				(client dispose:)
			)
		)
	)
)
