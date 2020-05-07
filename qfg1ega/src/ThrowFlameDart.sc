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
	FlameCast 0
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
(procedure (FlameCast target &tmp temp0 temp1 newAct_2)
	(LoadMany SOUND (SoundFX 33) (SoundFX 45))
	(Load VIEW vEgoMagicFlameDart)
	(if target
		(Face ego target)
		(= targetX (+ (target x?) (target targDeltaX?)))
		(= targetY (+ (target y?) (target targDeltaY?)))
		(= temp0 (- targetX (ego x?)))
		(= temp1 (- targetY (- (ego y?) 20)))
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
			(and
				(target isKindOf: TargActor)
				(not
					(TrySkill MAGIC 0 (- 50 (/ (ego distanceTo: target) 5)))
				)
			)
			(if (< targetY 0)
				(= targetY (+ targetY (Random 30 100)))
			else
				(= targetY (- targetY (Random 30 100)))
			)
		)
	else
		;CI: NOTE: This is probably a bug. Much of this code was copy/pasted from the ThrowRocks script, and this was likely missed.
		; it doesn't make sense to increase your throwing skill by casting Flame Dart.
		(SkillUsed THROW (/ [egoStats AGIL] tryStatThrowing))
		(= targetX (if (== (ego loop?) 1) -10 else 330))
		(= targetY (Random 20 80))
	)
	((= hitSound (Sound new:))
		number: (SoundFX 33)
		priority: 15
		init:
	)
	(= whoTarget target)
	((= newAct_2 (Actor new:))
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
		setScript: dartScript 0 target
	)
	(return TRUE)
)

(instance dartScript of Script
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
							(and (< -25 distX) (< distX 25)
								 (< -25 distY) (< distY 25)
							)
						 )	;And if it's a hit, we advance the script to the next state.
						(not (client mover?))	; or if the projectile has stopped moving.
						;(not (and (< 0 (client x?)) (< (client x?) 319)))	;or the projectile is offscreen left or right
						;(not (and (< 0 (client y?)) (< (client y?) 189)))	;or the projectile is offscreen top or bottom.
						(> 25
							(+  (GetDistance (client x?) (client y?) targetX targetY)
								(GetDistance (ego x?) (ego y?) targetX targetY)
							)
						)
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
;;; 			bnt      code_021d
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
;;; code_021d:
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
;;; 			bnt      code_02b3
;;; 			pushi    65511
;;; 			lal      distX
;;; 			lt?     
;;; 			bnt      code_025a
;;; 			pprev   
;;; 			ldi      25
;;; 			lt?     
;;; 			bnt      code_025a
;;; 			pushi    65511
;;; 			lal      distY
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
;;; 			lsl      targetX
;;; 			lsl      targetY
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
;;; 			lsl      targetX
;;; 			lsl      targetY
;;; 			callk    GetDistance,  8
;;; 			add     
;;; 			gt?     
;;; 			bnt      code_02b3
;;; code_02a2:
;;; 			pToa     register
;;; 			bnt      code_02a9
;;; 			lat      temp0
;;; code_02a9:
;;; 			sal      isHitTarget
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
		(hitSound dispose:)
		(HandsOn)
		(if (and isHitTarget (IsObject register))
			(register getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
		)
		(super dispose:)
		(DisposeScript 100)
	)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(= egoSignal (ego signal?))
				(= egoPriority (ego priority?))
				(= egoIllegalBits (ego illegalBits?))
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
				(hitSound play:)
				(client
					x: (if (== (ego loop?) 1)
						(- (ego x?) 19)
					else
						(+ (ego x?) 19)
					)
					show:
					setCycle: Forward
					setMotion: MoveTo targetX targetY
				)
			)
			(2
				(if isHitTarget
					(hitSound stop: number: (SoundFX 45) play:)
					(client setLoop: 3 cel: 0 setMotion: 0 setCycle: EndLoop self)
				else
					(client hide:)
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
