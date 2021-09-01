;;; Sierra Script 1.0 - (do not remove this comment)
(script# rSpittingSpirea) ;16
(include game.sh)
(use Main)
(use LoadMany)
(use Chase)
(use Sound)
(use Jump)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm16 0
	flyingSeed 1
	rock 2
)

(local
	[theFlower 4]
	spitCued
	waitingToThrow
	local6
	local7
	slashCount
	castingFetch
	threwARock
	triedFetch
	rockHit
	lassoWaiting
	local14
	local15
)
(procedure (AlreadyKilledFlower)
	(HighPrint 16 0)
)

(procedure (AlreadyGotSeed)
	(HighPrint 16 1)
)

(procedure (SpireaPickUpSeed)
	(cond 
		((Btst fFlowersInactive) (HighPrint 16 2))
		((== spireaStatus 0) (ego setScript: (ScriptID 292 0)))
		(else (HighPrint 16 3))
	)
)

(procedure (localproc_0059)
	(if (!= ([theFlower 0] loop?) 3)
		([theFlower 0] setLoop: 2 setCycle: EndLoop)
	)
	(if (!= ([theFlower 1] loop?) 3)
		([theFlower 1] setLoop: 2 setCycle: EndLoop)
	)
	(if (!= ([theFlower 2] loop?) 3)
		([theFlower 2] setLoop: 2 setCycle: EndLoop)
	)
	(if (!= ([theFlower 3] loop?) 3)
		([theFlower 3] setLoop: 2 setCycle: EndLoop)
	)
)

(procedure (localproc_00de)
	([theFlower 0] stopUpd:)
	([theFlower 1] stopUpd:)
	([theFlower 2] stopUpd:)
	([theFlower 3] stopUpd:)
)

(instance flyingSeed of Actor
	(properties
		view rSpittingSpirea
		illegalBits $0000
	)
)

(instance magicLasso of Actor
	(properties
		view vEgoMagicFetch
		illegalBits $0000
	)
)

(instance rock of Actor
	(properties
		view vEgoThrowing
		illegalBits $0000
	)
)

(instance spitSound of Sound
	(properties
		number 18
		priority 3
	)
)

(instance gulpSound of Sound
	(properties
		number 27
		priority 3
	)
)

(instance rm16 of Room
	(properties
		picture 16
		style WIPELEFT
		east 17
		south 24
	)
	
	(method (init)
		(LoadMany VIEW rSpittingSpirea vEgoSwordSpirea vEgoClimbing vEgoThrowing)
		(if (ego knows: FETCH)
			(Load VIEW vEgoMagicFetch)
		)
		(LoadMany SCRIPT 291 292)
		(LoadMany SOUND (SoundFX 18) (SoundFX 27))
		(super init:)
		(directionHandler add: self)
		(spitSound number: (SoundFX 18) init:)
		(gulpSound number: (SoundFX 27) init:)
		(StatusLine enable:)
		(if (or Night (Btst fGotSeed))
			(Bset fFlowersInactive)
		)
		(= local7 2)
		(NormalEgo)
		(ego init:)
		(rock
			setLoop: 4
			setStep: 70 30
			posn: 0 1000
			hide:
			ignoreActors:
			setCycle: Forward
			init:
		)
		(switch prevRoomNum
			(17
				(ego posn: 318 165 setMotion: MoveTo 275 165)
			)
			(else 
				(ego posn: 170 188 setMotion: MoveTo 170 175)
			)
		)
		(if (Btst fKilledFlower3)
			(flower0 setLoop: 3 cel: 4)
		)
		(if (Btst fKilledFlower1)
			(flower1 setLoop: 3 cel: 4)
		)
		(if (Btst fKilledFlower2)
			(flower3 setLoop: 3 cel: 4)
		)
		((= [theFlower 0] flower0) init: stopUpd:)
		((= [theFlower 1] flower1) init: stopUpd:)
		((= [theFlower 2] flower2) init: stopUpd:)
		((= [theFlower 3] flower3) init: stopUpd:)
		(addToPics
			add: leaf0 leaf1 leaf2 leaf3
			eachElementDo: #init
			doit:
		)
		(if
			(and
				(not (Btst fKilledFlower1))
				(not (Btst fKilledFlower2))
				(not (Btst fKilledFlower3))
			)
			(flyingSeed
				setLoop: 4
				setPri: 5
				ignoreActors:
				posn: 0 1000
				hide:
				setCycle: Forward
				init:
				setScript: spitIt
			)
		)
	)
	
	(method (doit)
		(if spitCued
			(flyingSeed setScript: spitIt)
		)
		(super doit:)
	)
	
	(method (dispose)
		(Bset fBeenIn16)
		(Bclr fFlowersInactive)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp spell)
		(switch (event type?)
			(direction
				(if (== spireaStatus 1)
					(switch (event message?)
						(dirW
							(ego setScript: (ScriptID 291 2))
						)
						(dirS
							(ego setScript: (ScriptID 291 2))
						)
						(dirE
							(= spireaStatus 2)
							(ego setScript: (ScriptID 291 3))
						)
						(dirN
							(= spireaStatus 2)
							(ego setScript: (ScriptID 291 3))
						)
					)
				else
					(event claimed: FALSE)
				)
			)
			(saidEvent
				(cond 
					((Said 'look<for/boulder,brick')
						(SpireaPickUpSeed)
					)
					((Said 'look>')
						(cond 
							((Said '/north,west')
								(HighPrint 16 4)
							)
							((Said '/south,east')
								(HighPrint 16 5)
							)
							((Said '[<at,around][/!*,forest]')
								(HighPrint 16 6)
								(if (not (Btst fFlowersInactive))
									(HighPrint 16 7)
								)
							)
							((Said '/cliff,boulder')
								(HighPrint 16 8)
							)
							((Said '[<down][/ground,needle,moss,grass]')
								(HighPrint 16 9)
							)
							((Said '[<up][/sky,cloud,star]')
								(if Night
									(HighPrint 16 10)
								else
									(HighPrint 16 11)
								)
							)
							((Said '/birch,tree')
								(HighPrint 16 12)
							)
							((Said '/seed')
								(cond 
									((ego has: iSeed)
										(event claimed: FALSE)
									)
									((Btst fFlowersInactive)
										(HighPrint 16 13)
									)
									(else
										(HighPrint 16 14)
									)
								)
							)
							((Said '/leaf')
								(HighPrint 16 15)
							)
						)
					)
					(
						(or
							(Said 'chop,beat,fight,kill[/plant,flower,pod]')
							(Said 'use,draw/blade')
						)
						(switch spireaStatus
							(1
								(HighPrint 16 16)
								(ego setScript: (ScriptID 291 2))
							)
							(3
								(HighPrint 16 3)
							)
							(4
								(HighPrint 16 3)
							)
							(0
								(cond 
									(
										(not
											(if
												(or
													(& (ego onControl: origin) cLRED)
													(& (ego onControl: origin) cYELLOW)
												)
											else
												(& (ego onControl: origin) cLMAGENTA)
											)
										)
										(NotClose)
									)
									((ego has: iSword)
										(ego setScript: smashIt)
										(Bset fFlowersInactive)
									)
									(else
										(HighPrint 16 17)
									)
								)
							)
						)
					)
					((Said 'climb,get<down')
						(cond 
							((and (> spireaStatus 1) (== seedTarget 0))
								(HighPrint 16 18)
							)
							((>= spireaStatus 1)
								(ego setScript: (ScriptID 292 3))
							)
							(else
								(HighPrint 16 19)
							)
						)
					)
					((Said 'climb[<up][/cliff,boulder]')
						(cond 
							((>= spireaStatus 1)
								(HighPrint 16 20)
							)
							((Btst fGotSeed)
								(AlreadyGotSeed)
							)
							((TrySkill CLIMB 35 0)
								(ego setScript: (ScriptID 291 1))
							)
							(else
								(ego setScript: (ScriptID 291 0))
							)
						)
					)
					((Said 'capture[/seed]')
						(cond 
							((Btst fFlowersInactive)
								(HighPrint 16 21)
							)
							((Btst fGotSeed)
								(AlreadyGotSeed)
							)
							((== spireaStatus 1)
								(= spireaStatus 2)
								(ego setScript: (ScriptID 291 3))
							)
							((or (== spireaStatus 2) (== spireaStatus 3))
								(HighPrint 16 22)
							)
							(else
								(HighPrint 16 23)
							)
						)
					)
					((Said '(lockpick<up),find,search>')
						(cond 
							((Said '/boulder,brick')
								(SpireaPickUpSeed)
							)
							((Said '/seed')
								(if (Btst fGotSeed)
									(AlreadyDone)
								else
									(HighPrint 16 24)
								)
							)
						)
					)
					((Said 'throw>')
						(cond 
							((Said '/boulder,brick')
								(cond 
									((Btst fGotSeed)
										(AlreadyGotSeed)
									)
									((Btst fFlowersInactive)
										(HighPrint 16 25)
									)
									((== spireaStatus 0)
										(if (ego has: iRock)
											(if (not threwARock)
												(HighPrint 16 26)
											)
											(ego setScript: throwIt)
										else
											(HighPrint 16 27)
										)
									)
									(else (HighPrint 16 3))
								)
							)
							((Said '/dagger') (HighPrint 16 28))
							((Said '/*') (HighPrint 16 29))
						)
					)
					((Said 'cast>')
						(switch (= spell (SaidSpell event))
							(OPEN
								(cond 
									((not (CastSpell spell)))
									((Btst fGotSeed)
										(AlreadyGotSeed)
									)
									((!= (ego script?) 0)
										(HighPrint 16 3)
									)
									((!= spireaStatus 0)
										(HighPrint 16 30)
										(ego setScript: (ScriptID 291 2))
									)
									((< [egoStats OPEN] 35)
										(HighPrint 16 31)
									)
									(else
										(Bset fFlowersInactive) 
										(ego setScript: openUp)
									)
								)
							)
							(FETCH
								(cond 
									((not (CastSpell spell)))
									((Btst fGotSeed)
										(AlreadyGotSeed)
									)
									((Btst fFlowersInactive)
										(HighPrint 16 32)
									)
									((== spireaStatus 0)
										(if (not triedFetch)
											(HighPrint 16 33)
										)
										(= castingFetch TRUE)
										(ego setScript: throwIt)
									)
									(else
										(HighPrint 16 30)
										(ego setScript: (ScriptID 291 2))
									)
								)
							)
							(FLAMEDART
								(if (not (Btst fGotSeed))
									(HighPrint 16 34)
								else
									(HighPrint 16 35)
								)
							)
							(else  (event claimed: FALSE))
						)
					)
					((Said 'get>')
						(cond 
							((Said '/seed')
								(cond 
									((Btst fGotSeed) (AlreadyDone))
									((Btst fFlowersInactive) (HighPrint 16 36))
									(else (HighPrint 16 37))
								)
							)
							((Said '/plant,flower,pod,leaf') (HighPrint 16 38))
							((Said '/boulder') (SpireaPickUpSeed))
						)
					)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance spitIt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= spitCued FALSE)
				(= seconds (Random 3 5))
			)
			(1
				(if (Btst fFlowersInactive)
					(self changeState: 8)
				else
					(= seedInPlant seedTarget)
					(if (== local14 (= seedTarget (Random 0 3)))
						(while (== local14 seedTarget)
							(if (<= (++ local15) 3)
								(= local15 0)
								(break)
							)
							(if (== seedTarget 1)
								(= seedTarget 2)
							else
								(= seedTarget 1)
							)
						)
					else
						(= local15 0)
					)
					(= local14 seedTarget)
					([theFlower seedInPlant]
						setLoop: 1
						startUpd:
						setCycle: EndLoop self
					)
					(if (and (!= seedTarget 3) (!= seedInPlant seedTarget) waitingToThrow)
						(throwIt cue:)
					)
				)
			)
			(2
				(flyingSeed
					show:
					yStep: 10
					posn:
						(switch seedInPlant
							(0 142)
							(1 35)
							(2 66)
							(3 228)
						)
						(switch seedInPlant
							(0 60)
							(1 85)
							(2 19)
							(3 70)
						)
				)
				(if lassoWaiting
					(lassoSeed cue:)
				else
					(self cue:)
				)
			)
			(3
				(spitSound play:)
				(flyingSeed
					setMotion: MoveTo
						(flyingSeed x?)
						(-
							(flyingSeed y?)
							(if (and (== seedInPlant seedTarget) (!= seedInPlant 2))
								30
							else
								5
							)
						)
						self
				)
				([theFlower seedInPlant] setCycle: BegLoop)
			)
			(4
				(flyingSeed
					yStep: 2
					setMotion: JumpTo
						(switch seedTarget
							(0 142)
							(1 35)
							(2 66)
							(3 228)
						)
						(switch seedTarget
							(0
								(if (== spireaStatus 3) 48 else 60)
							)
							(1 85)
							(2 19)
							(3 70)
						)
						self
				)
			)
			(5
				(if (and (== spireaStatus 3) (== seedTarget 0))
					(ego setScript: (ScriptID 292 2))
				else
					(self cue:)
				)
			)
			(6
				([theFlower seedTarget] setLoop: 2 setCycle: EndLoop)
				(flyingSeed
					yStep: 6
					setMotion: MoveTo (flyingSeed x?) (+ (flyingSeed y?) 17) self
				)
			)
			(7
				(gulpSound play:)
				(flyingSeed hide:)
				([theFlower seedTarget] setCycle: BegLoop self)
			)
			(8
				([theFlower seedTarget] stopUpd:)
				([theFlower seedInPlant] stopUpd:)
				(flyingSeed setScript: 0)
				(if (or (not (Btst fFlowersInactive)) (not local6))
					(= spitCued TRUE)
				)
			)
		)
	)
)

(instance smashIt of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle f10KillFlower -10)
				(cond 
					((& (ego onControl: origin) cLRED)
						(if (Btst fKilledFlower1)
							(AlreadyKilledFlower)
						else
							(HandsOff)
							(= local7 1)
							(ego illegalBits: 0 setMotion: MoveTo 57 132 self)
						)
					)
					((& (ego onControl: origin) cYELLOW)
						(if (Btst fKilledFlower2)
							(AlreadyKilledFlower)
						else
							(HandsOff)
							(= local7 3)
							(ego illegalBits: 0 setMotion: MoveTo 226 140 self)
						)
					)
					((& (ego onControl: origin) cLMAGENTA)
						(if (Btst fKilledFlower3)
							(AlreadyKilledFlower)
						else
							(HandsOff)
							(= local7 0)
							(ego illegalBits: 0 setMotion: MoveTo 137 132 self)
						)
					)
				)
			)
			(1
				(ego
					view: vEgoSwordSpirea
					setLoop: (if (== local7 1) 2 else 0)
					cel: 0
				)
				([theFlower 1] setPri: (if (== local7 1) 15 else -1))
				(self cue:)
			)
			(2 (ego setCycle: EndLoop self))
			(3
				(++ slashCount)
				(ego
					setLoop: (if (== local7 1) 3 else 1)
					setCycle: EndLoop self
				)
			)
			(4
				(if (== slashCount 2)
					(switch local7
						(0
							([theFlower 0]
								setLoop: 3
								cel: 0
								cycleSpeed: 1
								setCycle: EndLoop
							)
							(Bset fKilledFlower3)
						)
						(1
							([theFlower 1]
								setLoop: 3
								cel: 0
								cycleSpeed: 1
								setCycle: EndLoop
							)
							(Bset fKilledFlower1)
						)
						(3
							([theFlower 3]
								setLoop: 3
								cel: 0
								cycleSpeed: 1
								setCycle: EndLoop
							)
							(Bset fKilledFlower2)
						)
					)
					(if (== local7 seedTarget) (flyingSeed show:))
				)
				(if (> slashCount 3)
					(= slashCount 0)
					(= cycles 4)
				else
					(self changeState: 3)
				)
			)
			(5
				(ego setLoop: (if (== local7 1) 2 else 0) cel: 3)
				(= cycles 2)
			)
			(6 (ego setCycle: BegLoop self))
			(7
				(ego
					loop: (if (== local7 1) 1 else 3)
					posn: (if (!= local7 1) (+ (ego x?) 10) else (ego x?)) (ego y?)
				)
				(NormalEgo)
				(if
					(and
						(== local7 seedTarget)
						(not rockHit)
						(not (Btst fGotSeed))
					)
					(= cycles 3)
				else
					(HandsOn)
					(ego setScript: 0)
				)
				(= local7 2)
			)
			(8
				(HighPrint 16 39)
				(if (== ([theFlower 1] priority?) 15)
					([theFlower 1] setPri: 1)
				)
				(= cycles 2)
			)
			(9
				(flyingSeed
					setMotion: MoveTo (flyingSeed x?) (+ (flyingSeed y?) 15) self
				)
			)
			(10
				(flyingSeed dispose:)
				(= cycles 2)
			)
			(11
				(HighPrint 16 40)
				(SolvePuzzle f16GetSeed 8)
				(ego get: iSeed setScript: 0)
				(Bset fGotSeed)
				(HandsOn)
			)
		)
	)
)

(instance throwIt of Script
	(method (dispose)
		(= castingFetch 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setMotion: MoveTo (Random 242 258) (Random 160 175) self
				)
			)
			(1
				(ego
					view: (if castingFetch vEgoMagicFetch else vEgoThrowing)
					cycleSpeed: 1
					setLoop: (if castingFetch 0 else 2)
					cel: 0
				)
				(= waitingToThrow TRUE)
			)
			(2
				(= waitingToThrow FALSE)
				(if castingFetch
					(ego setCycle: EndLoop self)
				else
					(ego setCycle: CycleTo 4 1 self)
				)
			)
			(3
				(if castingFetch
					(magicLasso
						ignoreActors:
						posn: (+ (ego x?) 2) (- (ego y?) 36)
						setLoop: 4
						setStep: 20 10
						setCycle: Forward
						init:
					)
					(if (TrySkill FETCH 0 20)
						(magicLasso setScript: lassoSeed)
					else
						(magicLasso setScript: lassoFailed)
					)
					(ego setScript: 0)
					(= triedFetch 1)
				else
					(rock posn: (- (ego x?) 13) (- (ego y?) 34) show:)
					(ego setCycle: EndLoop self)
					(= threwARock TRUE)
					(if (TrySkill THROW 0 -10)
						(rock setScript: rockHitsIt)
					else
						(rock setScript: (ScriptID 292 1))
					)
				)
			)
			(4
				(ego use: iRock 1 loop: 1 setScript: 0)
				(NormalEgo)
			)
		)
	)
)

(instance rockHitsIt of Script
	(method (doit)
		(if
			(and
				(not rockHit)
				(== (flyingSeed y?) 150)
				(== (rock y?) 160)
			)
			(= rockHit TRUE)
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(rock setMotion: Chase flyingSeed 5 self)
			)
			(1
				(Bset fFlowersInactive)
				(= spitCued FALSE)
				(flyingSeed setScript: 0 setMotion: 0)
				(rock
					setMotion: MoveTo (+ (flyingSeed x?) 3) (flyingSeed y?) self
				)
				(if (!= ([theFlower seedTarget] cel?) 0)
					([theFlower seedTarget] setCycle: BegLoop)
				)
			)
			(2
				(rock setMotion: JumpTo 150 160 self)
				(flyingSeed
					show:
					setStep: 3 15
					setMotion: MoveTo (flyingSeed x?) 150
				)
			)
			(3
				(= seedTarget 2)
				(= seedInPlant 2)
				(flyingSeed setMotion: 0 setCycle: 0 ignoreActors: 0)
				(rock hide:)
				(= cycles 2)
			)
			(4
				(localproc_0059)
				(= cycles 10)
			)
			(5
				(localproc_00de)
				(rock hide:)
				(= cycles 1)
			)
			(6
				(flyingSeed setCycle: 0 stopUpd:)
				(NormalEgo)
				(ego
					illegalBits: 0
					setMotion:
						MoveTo
						(if (< (flyingSeed x?) (ego x?))
							(+ (flyingSeed x?) 16)
						else
							(- (flyingSeed x?) 16)
						)
						149
						self
				)
			)
			(7
				(ego
					view: vEgoThrowing
					setLoop: (if (< (flyingSeed x?) (ego x?)) 1 else 0)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 8)
			)
			(8
				(HighPrint 16 40)
				(flyingSeed dispose:)
				(ego setCycle: BegLoop self)
			)
			(9
				(NormalEgo)
				(SolvePuzzle f16GetSeed 8)
				(ego get: iSeed setScript: 0)
				(Bset fGotSeed)
				(HandsOn)
				(rock dispose:)
			)
		)
	)
)

(instance lassoFailed of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fFlowersInactive)
				(= temp0
					(/ (- ([theFlower seedTarget] x?) (magicLasso x?)) 2)
				)
				(= temp1
					(/
						(- (- ([theFlower seedTarget] y?) (magicLasso y?)) 30)
						2
					)
				)
				(magicLasso
					setMotion:
						MoveTo
						(+ (magicLasso x?) temp0)
						(+ (magicLasso y?) temp1)
						self
				)
			)
			(1 (= seconds 3))
			(2
				(magicLasso dispose:)
				(Bclr fFlowersInactive)
				(= spitCued TRUE)
				(NormalEgo)
				(HandsOn)
				(HighPrint 16 41)
				(self dispose:)
			)
		)
	)
)

(instance lassoSeed of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fFlowersInactive)
				(magicLasso
					setMotion: MoveTo
						([theFlower seedTarget] x?)
						(- ([theFlower seedTarget] y?) 30)
						self
				)
			)
			(1
				(Bclr fFlowersInactive)
				(= spitCued TRUE)
				(= lassoWaiting TRUE)
			)
			(2
				(flyingSeed
					setScript: 0
					setStep: 1 1
					setMotion: MoveTo (flyingSeed x?) (- (magicLasso y?) 2) self
				)
				(spitSound play:)
				([theFlower seedInPlant] setCycle: BegLoop)
			)
			(3
				(magicLasso
					setPri: 7
					setStep: 6 4
					setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 36) self
				)
				(flyingSeed
					setPri: 7
					setStep: 6 4
					setMotion: MoveTo (+ (ego x?) 2) (- (ego y?) 38)
				)
			)
			(4 (= seconds 3))
			(5
				(magicLasso hide:)
				(flyingSeed dispose:)
				(= seedTarget 2)
				(= seedInPlant 2)
				(ego setLoop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6
				(HighPrint 16 42)
				(Bset fGotSeed)
				(SolvePuzzle f16GetSeed 8)
				(ego get: iSeed loop: 1)
				(NormalEgo)
				(HandsOn)
				(= cycles 2)
			)
			(7
				(localproc_0059)
				(= cycles 10)
			)
			(8
				(localproc_00de)
				(magicLasso dispose:)
			)
		)
	)
)

(instance openUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo (ego x?) 145 self)
			)
			(1
				(ego view: vEgoMagicDetect setLoop: 0 cycleSpeed: 1 setCycle: EndLoop)
				(= cycles 25)
			)
			(2
				(localproc_0059)
				(flyingSeed
					setPri: (+ ([theFlower seedTarget] priority?) 1)
					posn: ([theFlower seedTarget] x?) (- ([theFlower seedTarget] y?) 15)
					show:
				)
				(= cycles 10)
			)
			(3
				(localproc_00de)
				(flyingSeed setMotion: MoveTo (flyingSeed x?) 145 self)
			)
			(4
				(flyingSeed setCycle: 0 stopUpd:)
				(NormalEgo)
				(ego
					illegalBits: 0
					setMotion:
						MoveTo
						(if (< (flyingSeed x?) (ego x?))
							(+ (flyingSeed x?) 16)
						else
							(- (flyingSeed x?) 16)
						)
						144
						self
				)
			)
			(5
				(ego
					view: vEgoThrowing
					setLoop: (if (< (flyingSeed x?) (ego x?)) 1 else 0)
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 8)
			)
			(6
				(HighPrint 16 40)
				(flyingSeed dispose:)
				(ego setCycle: BegLoop self)
			)
			(7
				(NormalEgo)
				(SolvePuzzle f16GetSeed 8)
				(ego get: iSeed setScript: 0)
				(Bset fGotSeed)
				(HandsOn)
			)
		)
	)
)

(instance leaf0 of PicView
	(properties
		y 93
		x 142
		view rSpittingSpirea
	)
)

(instance leaf1 of PicView
	(properties
		y 118
		x 35
		view rSpittingSpirea
	)
)

(instance leaf2 of PicView
	(properties
		y 58
		x 67
		view rSpittingSpirea
		cel 1
	)
)

(instance leaf3 of PicView
	(properties
		y 103
		x 230
		view rSpittingSpirea
	)
)

(instance flower0 of Prop
	(properties
		y 89
		x 142
		view rSpittingSpirea
		loop 1
		cycleSpeed 1
	)
)

(instance flower1 of Prop
	(properties
		y 114
		x 35
		view rSpittingSpirea
		loop 1
		cycleSpeed 1
	)
)

(instance flower2 of Prop
	(properties
		y 48
		x 66
		view rSpittingSpirea
		loop 1
		cycleSpeed 1
	)
)

(instance flower3 of Prop
	(properties
		y 99
		x 228
		view rSpittingSpirea
		loop 1
		cycleSpeed 1
	)
	
	(method (handleEvent event)
		(if
			(or
				(MouseClaimed flower0 event shiftDown)
				(MouseClaimed flower1 event shiftDown)
				(MouseClaimed flower2 event shiftDown)
				(MouseClaimed flower3 event shiftDown)
				(Said 'look/flower,plant,pod')
			)
			(event claimed: TRUE)
			(HighPrint 16 43)
			(if (or (Btst fKilledFlower1) (Btst fKilledFlower2) (Btst fKilledFlower3))
				(HighPrint 16 44)
			)
		)
	)
)
