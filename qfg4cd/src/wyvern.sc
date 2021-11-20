;;; Sierra Script 1.0 - (do not remove this comment)
(script# 835)
(include sci.sh)
(use Main)
(use SView)
(use combat)
(use Motion)
(use System)

(public
	wyvern 0
	egoScript 1
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
)
(instance wyvern of SActor
	(properties
		x 273
		y 148
		view 836
		signal $4000
		cycleSpeed 16
		explodeX 40
	)
	
	(method (init)
		(super init: &rest)
		(switch arcadeLevel
			(1 (= local1 40) (= local2 65))
			(2 (= local1 20) (= local2 60))
			(else 
				(= local1 5)
				(= local2 30)
			)
		)
		(if (!= arcadeLevel 3)
			(switch heroType
				(2
					(= global176 7)
					(= global177 3)
					(= global178 5)
					(= global179 7)
				)
				(1
					(= global176 1)
					(= global177 9)
					(= global178 1)
				)
				(else 
					(= global176 10)
					(= global177 3)
					(= global178 5)
				)
			)
		)
		(= local4 (- 13 (/ (+ [egoStats 1] [egoStats 2]) 100)))
		(= local5 0)
		(wSpell init: gPuzzleBar)
		(wBloodDrop init: gPuzzleBar)
		(self cue: setScript: wyvernCombat)
	)
	
	(method (cue)
		(self setLoop: 0 1 cel: 0 cycleSpeed: 16 setCycle: Fwd)
	)
	
	(method (getHurt param1)
		(switch param1
			(0
				(self setScript: hurtBySpell)
			)
			(2 (self setScript: hurtMyself))
		)
	)
)

(instance wyvernCombat of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= ticks (Random local1 local2))
			)
			(1
				(SetNowSeen wyvern)
				(SetNowSeen gSActor)
				(cond 
					(
						(<=
							(= temp0 (- (wyvern nsLeft?) (gSActor nsRight?)))
							0
						)
						(cond 
							((< (wyvern nsRight?) 340) (self setScript: moveBack self))
							((Random 0 1) (self setScript: doLSlash self))
							(else (self setScript: doRSlash self))
						)
					)
					(local0 (= ticks (/ 25 arcadeLevel)))
					((< monsterHealth 80)
						(cond 
							((< (wyvern nsRight?) 340) (self setScript: moveBack self))
							((< temp0 16)
								(switch (Random 0 5)
									(0
										(self setScript: tailAttack self)
									)
									(1
										(self setScript: doRSlash self)
									)
									(2
										(if (not (wSpell active?))
											(self setScript: castLSpell self)
										else
											(self setScript: doRSlash self)
										)
									)
									(3
										(self setScript: doRSlash self)
									)
									(else 
										(self setScript: doLSlash self)
									)
								)
							)
							(
							(and (Random 0 arcadeLevel) (not (wSpell active?))) (self setScript: castLSpell self))
							(else (self cue:))
						)
					)
					((< (wyvern nsRight?) 340)
						(if (< temp0 16)
							(switch (Random 0 3)
								(0
									(self setScript: doLSlash self)
								)
								(1
									(self setScript: doRSlash self)
								)
								(else 
									(self setScript: tailAttack self)
								)
							)
						else
							(switch (Random 0 5)
								(0 (self cue:))
								(1
									(if (not (wSpell active?))
										(self setScript: castLSpell self)
									else
										(self cue:)
									)
								)
								(2
									(if (not (wSpell active?))
										(self setScript: castLSpell self)
									else
										(self cue:)
									)
								)
								(3
									(if (> arcadeLevel 1)
										(self setScript: castLSpell self)
									else
										(self setScript: moveForward self (gSActor nsLeft?))
									)
								)
								(4
									(if (> arcadeLevel 2)
										(self setScript: castLSpell self)
									else
										(self setScript: moveForward self (gSActor nsLeft?))
									)
								)
								(else 
									(self setScript: moveForward self (gSActor nsLeft?))
								)
							)
						)
					)
					((< temp0 16)
						(switch (Random 0 4)
							(0
								(self setScript: tailAttack self)
							)
							(1
								(self setScript: doLSlash self)
							)
							(2
								(self setScript: doLSlash self)
							)
							(else 
								(self setScript: doRSlash self)
							)
						)
					)
					(else
						(switch (Random 0 3)
							(0
								(self setScript: moveForward self (gSActor nsLeft?))
							)
							(else 
								(if (not (wSpell active?))
									(self setScript: castLSpell self)
								else
									(self cue:)
								)
							)
						)
					)
				)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance moveForward of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(SetNowSeen wyvern)
		(SetNowSeen gSActor)
		(if
			(and
				(wyvern mover?)
				(<= (wyvern nsLeft?) (gSActor nsRight?))
			)
			(= local3 1)
			(wyvern setMotion: 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local3 0)
				(wyvern
					setLoop: 9 1
					cycleSpeed: 2
					moveSpeed: 1
					xStep: 7
					setLoop: 0 1
					setMotion: ShotTo register (wyvern y?) self
				)
			)
			(1
				(if
				(and (> arcadeLevel 1) local3 (Random 0 arcadeLevel))
					(switch (Random 0 3)
						(0
							(self setScript: tailAttack self)
						)
						(1
							(self setScript: doLSlash self)
						)
						(2
							(self setScript: doLSlash self)
						)
						(else 
							(self setScript: doRSlash self)
						)
					)
				else
					(wyvern cue:)
					(self dispose:)
				)
			)
			(2
				(wyvern cue:)
				(self dispose:)
			)
		)
	)
)

(instance moveBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wyvern
					setLoop: 9 1
					cycleSpeed: 2
					moveSpeed: 1
					xStep: 7
					setLoop: 0 1
					setMotion: ShotTo (+ (wyvern x?) 14) (wyvern y?) self
				)
			)
			(1
				(wyvern cue:)
				(self dispose:)
			)
		)
	)
)

(instance doRSlash of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(SetNowSeen wyvern)
		(if
			(and
				register
				(or
					(gSActor
						onMe: (+ (wyvern nsLeft?) 1) (+ (wyvern nsTop?) 29)
					)
					(and
						(== state 1)
						(| (SetNowSeen gSActor) $0001)
						(<= (wyvern nsLeft?) (gSActor nsRight?))
					)
				)
			)
			(= register 0)
			(wBloodDrop
				x: (+ (wyvern nsLeft?) 1)
				y: (+ (wyvern nsTop?) 19)
				setPri: (+ (gSActor priority?) 1)
				show:
				setCycle: End wBloodDrop
			)
			(gSActor getHurt: 8 setScript: sleapFace)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wyvern
					cycleSpeed: 6
					setLoop: 2 1
					cel: 0
					setCycle: CT 3 1 self
				)
			)
			(1 (= ticks 10))
			(2 (wyvern setCycle: End self))
			(3
				(wyvern cue:)
				(self dispose:)
			)
		)
	)
)

(instance doLSlash of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(SetNowSeen wyvern)
		(if
			(and
				register
				(or
					(gSActor
						onMe: (+ (wyvern nsLeft?) 1) (+ (wyvern nsTop?) 22)
					)
					(gSActor
						onMe: (+ (wyvern nsLeft?) 2) (+ (wyvern nsTop?) 35)
					)
					(and
						(== state 1)
						(| (SetNowSeen gSActor) $0001)
						(<= (wyvern nsLeft?) (gSActor nsRight?))
					)
				)
			)
			(= register 0)
			(wBloodDrop
				x: (+ (wyvern nsLeft?) 1)
				y: (+ (wyvern nsTop?) 22)
				setPri: (+ (gSActor priority?) 1)
				show:
				setCycle: End wBloodDrop
			)
			(gSActor getHurt: 6 setScript: sleapFace)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wyvern
					cycleSpeed: 6
					setLoop: 3 1
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(1 (= ticks 10))
			(2 (wyvern setCycle: End self))
			(3
				(wyvern cue:)
				(self dispose:)
			)
		)
	)
)

(instance castLSpell of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(wyvern
					cycleSpeed: 6
					setLoop: 5 1
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(1
				(SetNowSeen gSActor)
				(SetNowSeen wyvern)
				(if
					(and
						(not (& global192 $0002))
						(or
							(gSActor
								onMe: (+ (wyvern nsLeft?) 1) (+ (wyvern nsTop?) 49)
							)
							(gSActor
								onMe: (+ (wyvern nsLeft?) 18) (+ (wyvern nsTop?) 47)
							)
						)
					)
					(gSActor setScript: burnEgo getHurt: 6)
					(wyvern cue:)
					(self dispose:)
				else
					(wSpell
						x: (- (wyvern nsLeft?) 20)
						y: (= temp0 (+ (wyvern nsTop?) 49))
						setLoop: 4 1
						myTarget: gSActor
						setCycle: Fwd
						setPri: (+ (gSActor priority?) 1)
						show:
						setMotion: ShotTo -5 temp0 wSpell
					)
					(wyvern setCycle: End)
				)
				(= ticks 45)
			)
			(2
				(wyvern cue:)
				(self dispose:)
			)
		)
	)
)

(instance tailAttack of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(SetNowSeen wyvern)
		(if
			(and
				register
				(not (& global192 $0002))
				(or
					(gSActor
						onMe: (+ (wyvern nsLeft?) 1) (+ (wyvern nsTop?) 102)
					)
					(and
						(== state 1)
						(| (SetNowSeen gSActor) $0001)
						(<= (wyvern nsLeft?) (gSActor nsRight?))
					)
				)
			)
			(= register 0)
			(if (not local5)
				(Bset 14)
				(= local5 1)
				(= poisonLevel (+ poisonLevel 50))
				(UpdateScreenItem ((ScriptID 40 5) cel: 1 yourself:))
			)
			(wBloodDrop
				x: (+ (wyvern nsLeft?) 1)
				y: (+ (wyvern nsTop?) 102)
				setPri: (+ (gSActor priority?) 1)
				show:
				setCycle: End wBloodDrop
			)
			(gSActor getHurt: 4 setScript: sleapFace)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wyvern
					cycleSpeed: 6
					setLoop: 6 1
					cel: 0
					setCycle: CT 2 1 self
				)
				(proc810_13 1 986)
			)
			(1 (= ticks 10))
			(2 (wyvern setCycle: End self))
			(3
				(wyvern cue:)
				(self dispose:)
			)
		)
	)
)

(instance hurtMyself of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wyvern cycleSpeed: 6 setLoop: 1 1 cel: 0)
				(if (< (wyvern x?) 250)
					(wyvern
						setCycle: End
						setMotion: ShotTo (+ (wyvern x?) 15) (wyvern y?) self
					)
				else
					(wyvern setCycle: End self)
				)
				(proc810_13 1 837)
			)
			(1
				(cond 
					((Btst 386)
						(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
						(self dispose:)
					)
					((> arcadeLevel 1)
						(if (Random 0 arcadeLevel)
							(switch (Random 0 4)
								(0
									(self setScript: tailAttack self)
								)
								(1
									(self setScript: doLSlash self)
								)
								(2
									(self setScript: doLSlash self)
								)
								(else 
									(self setScript: doRSlash self)
								)
							)
						else
							(self cue:)
						)
					)
					(else (self cue:))
				)
			)
			(2
				(wyvern cue:)
				(client setScript: wyvernCombat)
			)
		)
	)
)

(instance hurtBySpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wyvern
					cycleSpeed: 6
					setLoop: 8 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 1 837)
			)
			(1
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
				else
					(wyvern cue:)
				)
				(client setScript: wyvernCombat)
			)
		)
	)
)

(instance burnEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(gPuzzleBar noHands: 1)
				(VibrateMouse 2 1 1)
				(gSActor
					view: (gSActor typeView?)
					moveSpeed: 0
					xStep: 4
					setLoop: 3 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 0 911)
				(if (> (gSActor x?) 45)
					(gSActor
						setMotion: ShotTo (- (gSActor x?) 20) (gSActor y?)
					)
				)
			)
			(1 (= ticks 20))
			(2
				(gSActor setMotion: 0)
				(= local0 0)
				(gPuzzleBar noHands: 0)
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
				else
					(gSActor cue:)
				)
				(self dispose:)
			)
		)
	)
)

(instance getSpellHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(gPuzzleBar noHands: 1)
				(wSpell setLoop: 5 1 cel: 0 setCycle: CT 2 1 self)
			)
			(1
				(VibrateMouse 2 1 1)
				(wSpell setCycle: End wSpell)
				(gSActor
					view: (gSActor typeView?)
					moveSpeed: 0
					xStep: 4
					setLoop: 3 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 0 911)
				(if (> (gSActor x?) 45)
					(gSActor
						setMotion: MoveTo (- (gSActor x?) 20) (gSActor y?)
					)
				)
			)
			(2 (= ticks 20))
			(3
				(= local0 0)
				(gPuzzleBar noHands: 0)
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
				else
					(gSActor cue:)
				)
				(self dispose:)
			)
		)
	)
)

(instance sleapFace of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gPuzzleBar noHands: 1)
				(VibrateMouse 3 1 1)
				(gSActor
					view: (gSActor typeView?)
					moveSpeed: 0
					xStep: 4
					setLoop: 2 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 0 911)
				(if (> (gSActor x?) 45)
					(gSActor
						setMotion: ShotTo (- (gSActor x?) 12) (gSActor y?)
					)
				)
			)
			(1 (= ticks 10))
			(2
				(gSActor setMotion: 0)
				(gPuzzleBar noHands: 0)
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
				else
					(gSActor cue:)
				)
				(self dispose:)
			)
		)
	)
)

(instance wSpell of CombatSpell
	(properties
		signal $4000
		xStep 10
		moveSpeed 0
	)
	
	(method (init)
		(= gCombatSpell_3 self)
		(super init: &rest)
		(self cue:)
	)
	
	(method (cue)
		(self hide:)
	)
	
	(method (toDamage &tmp temp0 temp1 temp2)
		(= temp1 0)
		(self setMotion: 0)
		(if
			(or
				(and (< y 110) (& global192 $0001))
				(and (> y 110) (& global192 $0002))
			)
			(= temp1 1)
		)
		(= temp2
			(if
			(and (== (gSActor view?) 45) (== (gSActor loop?) 9))
				(if (< (= temp0 (/ [egoStats 17] 4)) 15) 15 else temp0)
			else
				6
			)
		)
		(if resistTimer
			(= temp2 (/ (* temp2 [egoStats 39]) 400))
		)
		(if temp1
			(gSActor getHurt: (/ temp2 5))
			(self setLoop: 5 1 cel: 0 setCycle: End self)
		else
			(gSActor getHurt: temp2 setScript: getSpellHit)
		)
	)
)

(instance wBloodDrop of SActor
	(properties
		view 26
		loop 10
	)
	
	(method (init)
		(super init: &rest)
		(self cue:)
	)
	
	(method (cue)
		(self hide:)
	)
)

(instance egoScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch heroType
					(2
						(client setScript: thiefScript)
					)
					(1
						(client setScript: magicScript)
					)
					(else 
						(client setScript: fighterScript)
					)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance fighterScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= ticks (Random 2 local4)))
			(1
				(SetNowSeen gSActor)
				(SetNowSeen wyvern)
				(= temp0 (- (wyvern nsLeft?) (gSActor nsRight?)))
				(cond 
					(
						(and
							(proc810_12 global178)
							(or (< [egoStats 18] 2) (== global176 0))
							(> (gSActor nsLeft?) 20)
						)
						(self setScript: (ScriptID 41 3) self)
					)
					(
						(and
							(wSpell active?)
							(< (- (wSpell x?) (gSActor nsRight?)) 40)
							(> (wSpell x?) (gSActor nsLeft?))
						)
						(cond 
							((> [egoStats 18] 7)
								(cond 
									((< temp0 25)
										(cond 
											((not global178) (self cue:))
											((> global178 9) (self setScript: (ScriptID 41 6) self))
											(else (self setScript: (ScriptID 810 4) self))
										)
									)
									((< temp0 30)
										(cond 
											((proc810_12 global176) (self setScript: (ScriptID 810 7) self))
											((not global178) (self cue:))
											((> global178 7) (self setScript: (ScriptID 41 6) self))
											(else (self setScript: (ScriptID 810 4) self))
										)
									)
									((proc810_12 global176) (self setScript: (ScriptID 810 5) self))
									((not global178) (self cue:))
									((> global178 9) (self setScript: (ScriptID 41 6) self))
									(else (self setScript: (ScriptID 810 4) self))
								)
							)
							((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
							(else (self cue:))
						)
					)
					((> temp0 20)
						(cond 
							((and (< temp0 25) (> [egoStats 18] 7)) (self setScript: (ScriptID 810 7) self))
							((and (< temp0 40) (> [egoStats 18] 7)) (self setScript: (ScriptID 810 6) self))
							((proc810_12 global176) (self setScript: (ScriptID 41 2) self))
							(
								(and
									(proc810_12 global177)
									(> temp0 50)
									(> [egoStats 18] 5)
									(> [egoStats 19] 5)
									(not (gCombatSpell active?))
								)
								(if (or [egoStats 28] [egoStats 33] [egoStats 34])
									(self setScript: (ScriptID 41 5) self (Random 0 1))
								else
									(self setScript: (ScriptID 41 5) self 0)
								)
							)
							(else (self cue:))
						)
					)
					(
						(and
							(proc810_12 global177)
							(> temp0 50)
							(> [egoStats 18] 5)
							(> [egoStats 19] 5)
							(not (gCombatSpell active?))
						)
						(if (or [egoStats 28] [egoStats 33] [egoStats 34])
							(self setScript: (ScriptID 41 5) self (Random 0 1))
						else
							(self setScript: (ScriptID 41 5) self 0)
						)
					)
					((> [egoStats 18] 7)
						(if (Random 0 2)
							(self setScript: (ScriptID 41 1) self)
						else
							(self setScript: (ScriptID 810 7) self)
						)
					)
					((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
					(else (self cue:))
				)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance magicScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= ticks (Random 2 local4)))
			(1
				(SetNowSeen gSActor)
				(SetNowSeen wyvern)
				(= temp0 (- (wyvern nsLeft?) (gSActor nsRight?)))
				(cond 
					((< [egoStats 19] 1) (client setScript: fighterScript))
					((< temp0 10)
						(if (> [egoStats 18] 7)
							(if (proc810_12 global176)
								(self setScript: (ScriptID 41 1) self)
							else
								(self setScript: (ScriptID 810 7) self)
							)
						else
							(self cue:)
						)
					)
					(
						(and
							(proc810_12 global178)
							(or (< [egoStats 18] 2) (== global176 0))
							(> (gSActor nsLeft?) 20)
						)
						(self setScript: (ScriptID 41 3) self)
					)
					(
						(and
							(wSpell active?)
							(< (- (wSpell x?) (gSActor nsRight?)) 40)
							(> (wSpell x?) (gSActor nsLeft?))
							(proc810_12 global178)
							(> [egoStats 18] 7)
						)
						(if (> global178 9)
							(self setScript: (ScriptID 41 6) self)
						else
							(self setScript: (ScriptID 810 4) self)
						)
					)
					(
						(and
							(proc810_12 global177)
							(> [egoStats 18] 5)
							(> [egoStats 19] 5)
							(not (gCombatSpell active?))
						)
						(if (or [egoStats 28] [egoStats 33] [egoStats 34])
							(self setScript: (ScriptID 41 5) self (Random 0 1))
						else
							(self setScript: (ScriptID 41 5) self 0)
						)
					)
					((and (proc810_12 global176) (> temp0 20)) (self setScript: (ScriptID 41 2) self))
					(else (self cue:))
				)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance thiefScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (= ticks (Random 2 local4)))
			(1
				(SetNowSeen gSActor)
				(SetNowSeen wyvern)
				(= temp0 (- (wyvern nsLeft?) (gSActor nsRight?)))
				(cond 
					(
						(and
							(proc810_12 global178)
							(or (< [egoStats 18] 2) (== global176 0))
							(> (gSActor nsLeft?) 20)
						)
						(self setScript: (ScriptID 41 3) self)
					)
					(
						(and
							(proc810_12 global178)
							(wSpell active?)
							(< (- (wSpell x?) (gSActor nsRight?)) 40)
							(> (wSpell x?) (gSActor nsLeft?))
						)
						(if (> [egoStats 18] 7)
							(cond 
								((not global178) (self cue:))
								((> global178 9) (self setScript: (ScriptID 41 6) self))
								(else (self setScript: (ScriptID 810 4) self))
							)
						else
							(self setScript: (ScriptID 41 6) self)
						)
					)
					((< temp0 10)
						(cond 
							((> [egoStats 18] 7)
								(if (or (proc810_12 global176) (> global178 8))
									(self setScript: (ScriptID 41 1) self)
								else
									(self setScript: (ScriptID 810 7) self)
								)
							)
							((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
							(else (self cue:))
						)
					)
					(
						(and
							(> [egoStats 18] 100)
							(> temp0 30)
							(proc810_12 global179)
							(proc810_12 global176)
						)
						(= [egoStats 18] (- [egoStats 18] 35))
						(= temp1 (proc810_1 18))
						(UpdateScreenItem
							((ScriptID 40 3)
								x: (- temp1 46)
								setInsetRect: (- 80 temp1) 0 80 2
								yourself:
							)
						)
						(self setScript: (ScriptID 810 9) self)
					)
					(
						(and
							(> ((inventory at: 5) amount?) 1)
							(not (gCombatSpell_2 active?))
						)
						(= global187 16)
						(self setScript: (ScriptID 810 8) self)
					)
					(
						(and
							(> temp0 30)
							(proc810_12 global177)
							(> [egoStats 18] 5)
							(> [egoStats 19] 5)
							(not (gCombatSpell active?))
						)
						(if (or [egoStats 28] [egoStats 33] [egoStats 34])
							(self setScript: (ScriptID 41 5) self (Random 0 1))
						else
							(self setScript: (ScriptID 41 5) self 0)
						)
					)
					((and (proc810_12 global176) (> temp0 20)) (self setScript: (ScriptID 41 2) self))
					(else (self cue:))
				)
			)
			(2 (self changeState: 0))
		)
	)
)
