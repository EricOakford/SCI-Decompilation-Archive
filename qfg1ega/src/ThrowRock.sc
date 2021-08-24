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
	whoTarget
	distX
	distY
	targetX
	targetY
	isHitTarget
	egoSignal
	egoPriority
	egoIllegalBits
	hitSound
)
(procedure (ThrowRock target &tmp temp0 temp1 newAct_2)
	(return
		(if (not (ego has: iRock))
			(HighPrint 102 0)
			;You do not have any rocks.
			(DisposeScript 102)
			(return FALSE)
		else
			(ego use: iRock 1)
			(Load SOUND (SoundFX 58))
			(if target
				(Face ego target)
				(= targetX (+ (target x?) (target targDeltaX?)))
				(= targetY (+ (target y?) (target targDeltaY?)))
				(= temp0 (- targetX (ego x?)))
				(= temp1 (- targetY (- (ego y?) 34)))
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
				;CI: why call SkillUsed directly, instead of TrySkill
				(SkillUsed THROW (/ [egoStats AGIL] tryStatThrowing))
				(= targetX (if (& (ego loop?) $0001) -10 else 330))
				(= targetY (Random 20 80))
			)
			(= whoTarget target)
			((= newAct_2 (Actor new:))
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
				setScript: rockScript 0 target
			)
			(return TRUE)
		)
	)
)

(instance rockScript of Script
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
;;; 			bnt      code_020e
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
;;; code_020e:
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
;;; 			bnt      code_0296
;;; 			pushi    65521
;;; 			lal      distX
;;; 			lt?     
;;; 			bnt      code_024b
;;; 			pprev   
;;; 			ldi      15
;;; 			lt?     
;;; 			bnt      code_024b
;;; 			pushi    65521
;;; 			lal      distY
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
;;; 			sal      isHitTarget
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
		(if (and isHitTarget (IsObject register))
			(register getHurt: TRUE)
		)
		(super dispose:)
		(DisposeScript 102)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= egoSignal (ego signal?))
				(= egoPriority (ego priority?))
				(= egoIllegalBits (ego illegalBits?))
				(HandsOff)
				(ego
					view: vEgoThrowing
					cycleSpeed: 1
					setLoop: (if (& (ego loop?) $0001) 2 else 3)
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
					setMotion: MoveTo targetX targetY
				)
			)
			(2
				(client hide:)
				(if isHitTarget
					(hitSound play: self)
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
