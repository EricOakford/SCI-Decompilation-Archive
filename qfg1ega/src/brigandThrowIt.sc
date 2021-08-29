;;; Sierra Script 1.0 - (do not remove this comment)
(script# 229)
(include game.sh)
(use Main)
(use Chase)
(use Jump)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	throwIt 0
)

(local
	throwCued
	yorickX = [-9 -2 3]
	yorickY = [-17 -16 -16]
	thingY = [8 9 9]
	yorickX2 = [-18 -16 -3 1 6]
	yorickY2 = [-31 -29 -28 -27 -29]
	thingY2 = [-1 1 2 3 1]
)
(instance poof of Prop
	(properties
		view vYorick
		loop 7
	)
)

(instance thingThrown of Actor
	(properties
		z 30
		yStep 10
		view vYorick
		xStep 15
	)
)

(instance throwIt of Script
	(method (doit)
		(if
			(and
				(not throwCued)
				(or
					(Btst fOpeningDoor)
					(== (ego onControl: origin) cYELLOW)
					((ScriptID 96 9) script?)
				)
			)
			(= throwCued TRUE)
			(= cycles 0)
			(self changeState: 7)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 229)
		(DisposeScript CHASE)
		(DisposeScript JUMP)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset fYorickThrows)
				(User canInput: FALSE)
				(if (Btst fPulledChain)
					(switch ((ScriptID 96 5) cel?)
						(0
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 0])
									(+ ((ScriptID 96 5) y?) [yorickY2 0])
							)
						)
						(1
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 1])
									(+ ((ScriptID 96 5) y?) [yorickY2 1])
							)
						)
						(2
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 2])
									(+ ((ScriptID 96 5) y?) [yorickY2 2])
							)
						)
						(3
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 3])
									(+ ((ScriptID 96 5) y?) [yorickY2 3])
							)
						)
						(4
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 4])
									(+ ((ScriptID 96 5) y?) [yorickY2 4])
							)
						)
					)
				else
					((ScriptID 96 5)
						setLoop: 2
						setCel: ((ScriptID 96 6) cel?)
						stopUpd:
					)
					((ScriptID 96 6) setCel: 3)
					(switch ((ScriptID 96 5) cel?)
						(0
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX 0])
									(+ ((ScriptID 96 5) y?) [yorickY 0])
							)
						)
						(1
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX 1])
									(+ ((ScriptID 96 5) y?) [yorickY 1])
							)
						)
						(2
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX 2])
									(+ ((ScriptID 96 5) y?) [yorickY 2])
							)
						)
					)
				)
				(poof
					setPri: (+ ((ScriptID 96 5) priority?) 2)
					ignoreActors:
					init:
					setCycle: CycleTo 3 1 self
				)
			)
			(1
				(if (Btst fPulledChain)
					(switch ((ScriptID 96 5) cel?)
						(0
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 0])
									(+ ((ScriptID 96 5) y?) [thingY2 0])
							)
						)
						(1
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 1])
									(+ ((ScriptID 96 5) y?) [thingY2 1])
							)
						)
						(2
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 2])
									(+ ((ScriptID 96 5) y?) [thingY2 2])
							)
						)
						(3
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 3])
									(+ ((ScriptID 96 5) y?) [thingY2 3])
							)
						)
						(4
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX2 4])
									(+ ((ScriptID 96 5) y?) [thingY2 4])
							)
						)
					)
				else
					(switch ((ScriptID 96 5) cel?)
						(0
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX 0])
									(+ ((ScriptID 96 5) y?) [thingY 0])
							)
						)
						(1
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX 1])
									(+ ((ScriptID 96 5) y?) [thingY 1])
							)
						)
						(2
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [yorickX 2])
									(+ ((ScriptID 96 5) y?) [thingY 2])
							)
						)
					)
				)
				(thingThrown
					setLoop: 8
					setCel: (Random 0 5)
					setPri: (ego priority?)
					ignoreActors:
					illegalBits: 0
					init:
					setMotion: Chase ego 20 self
				)
				(poof setCycle: EndLoop)
			)
			(2
				(HandsOff)
				(thingThrown
					setPri: (- (ego priority?) 1)
					setMotion: Chase ego 0 self
				)
			)
			(3
				(Bset fRollingOut)
				(ego
					view: vEgoJesterRoom
					loop: (if (or (== (ego loop?) 1) (== (ego loop?) 3))
						7
					else
						6
					)
					cel: 1
				)
				((ScriptID 96 16)
					number: (SoundFX 86)
					loop: 1
					priority: 2
					play:
				)
				(TakeDamage 1)
				(cond 
					((> (ego x?) ((ScriptID 96 5) x?))
						(if (and (< (ego x?) 313) (< (ego y?) 186))
							(ego posn: (+ (ego x?) 6) (+ (ego y?) 3))
						)
					)
					((and (> (ego x?) 6) (< (ego y?) 186)) (ego posn: (- (ego x?) 6) (+ (ego y?) 3)))
				)
				(if (not (Btst fPulledChain))
					((ScriptID 96 6) setCel: 1)
					((ScriptID 96 5) setLoop: 0 setCel: 0 stopUpd:)
				)
				(poof dispose:)
				(= cycles 1)
			)
			(4
				(if (> (ego x?) ((ScriptID 96 5) x?))
					(thingThrown
						setPri: (cond 
							((> (ego y?) 138) 11)
							((> (ego y?) 115) 8)
							(else 7)
						)
						setMotion: JumpTo (- (ego x?) (Random 30 60)) 210 self
					)
				else
					(thingThrown
						setMotion: JumpTo (+ (ego x?) (Random 30 60)) 210 self
					)
				)
			)
			(5
				(thingThrown dispose:)
				(HandsOn)
				(Bclr fRollingOut)
				(NormalEgo)
				(Face ego (ScriptID 96 5))
				(User canInput: TRUE)
				(= cycles 2)
			)
			(6
				(HandsOn)
				(Bclr fYorickThrows)
				(client setScript: 0)
			)
			(7
				(if (and (not (Btst fPulledChain)) (Btst fOpeningDoor))
					((ScriptID 96 6) setCel: 3)
					((ScriptID 96 5)
						setLoop: 4
						cel: 0
						cycleSpeed: 1
						setCycle: Forward
					)
				)
				(poof dispose:)
				(thingThrown dispose:)
				(User canInput: TRUE)
				(= cycles 2)
			)
			(8
				(HandsOn)
				(Bclr fYorickThrows)
				(client setScript: 0)
			)
		)
	)
)
