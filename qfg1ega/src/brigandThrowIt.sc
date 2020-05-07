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
	local0
	[local1 3] = [-9 -2 3]
	[local4 3] = [-17 -16 -16]
	[local7 3] = [8 9 9]
	[local10 5] = [-18 -16 -3 1 6]
	[local15 5] = [-31 -29 -28 -27 -29]
	[local20 5] = [-1 1 2 3 1]
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
	(properties)
	
	(method (doit)
		(if
			(and
				(not local0)
				(or
					(Btst OPENING_LEADER_DOOR)
					(== (ego onControl: origin) cYELLOW)
					((ScriptID 96 9) script?)
				)
			)
			(= local0 1)
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
				(Bset FLAG_271)
				(User canInput: FALSE)
				(if (Btst PULLED_CHAIN)
					(switch ((ScriptID 96 5) cel?)
						(0
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [local10 0])
									(+ ((ScriptID 96 5) y?) [local15 0])
							)
						)
						(1
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [local10 1])
									(+ ((ScriptID 96 5) y?) [local15 1])
							)
						)
						(2
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [local10 2])
									(+ ((ScriptID 96 5) y?) [local15 2])
							)
						)
						(3
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [local10 3])
									(+ ((ScriptID 96 5) y?) [local15 3])
							)
						)
						(4
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [local10 4])
									(+ ((ScriptID 96 5) y?) [local15 4])
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
									(+ ((ScriptID 96 5) x?) [local1 0])
									(+ ((ScriptID 96 5) y?) [local4 0])
							)
						)
						(1
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [local1 1])
									(+ ((ScriptID 96 5) y?) [local4 1])
							)
						)
						(2
							(poof
								posn:
									(+ ((ScriptID 96 5) x?) [local1 2])
									(+ ((ScriptID 96 5) y?) [local4 2])
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
				(if (Btst PULLED_CHAIN)
					(switch ((ScriptID 96 5) cel?)
						(0
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local10 0])
									(+ ((ScriptID 96 5) y?) [local20 0])
							)
						)
						(1
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local10 1])
									(+ ((ScriptID 96 5) y?) [local20 1])
							)
						)
						(2
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local10 2])
									(+ ((ScriptID 96 5) y?) [local20 2])
							)
						)
						(3
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local10 3])
									(+ ((ScriptID 96 5) y?) [local20 3])
							)
						)
						(4
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local10 4])
									(+ ((ScriptID 96 5) y?) [local20 4])
							)
						)
					)
				else
					(switch ((ScriptID 96 5) cel?)
						(0
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local1 0])
									(+ ((ScriptID 96 5) y?) [local7 0])
							)
						)
						(1
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local1 1])
									(+ ((ScriptID 96 5) y?) [local7 1])
							)
						)
						(2
							(thingThrown
								posn:
									(+ ((ScriptID 96 5) x?) [local1 2])
									(+ ((ScriptID 96 5) y?) [local7 2])
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
				(Bset FLAG_260)
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
				(if (not (Btst PULLED_CHAIN))
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
				(Bclr FLAG_260)
				(NormalEgo)
				(Face ego (ScriptID 96 5))
				(User canInput: 1)
				(= cycles 2)
			)
			(6
				(HandsOn)
				(Bclr FLAG_271)
				(client setScript: 0)
			)
			(7
				(if (and (not (Btst PULLED_CHAIN)) (Btst OPENING_LEADER_DOOR))
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
				(Bclr FLAG_271)
				(client setScript: 0)
			)
		)
	)
)
