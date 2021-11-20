;;; Sierra Script 1.0 - (do not remove this comment)
(script# 855)
(include sci.sh)
(use Main)
(use SView)
(use combat)
(use Motion)
(use System)

(public
	horror 0
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
(instance horror of SActor
	(properties
		x 225
		y 140
		view 856
		signal $5000
		cycleSpeed 10
		explodeX 65
	)
	
	(method (init)
		(super init: &rest)
		(split init: gPuzzleBar)
		(= local4 0)
		(switch arcadeLevel
			(1
				(= local0 55)
				(= local1 75)
				(= local3 30)
			)
			(2
				(= local0 45)
				(= local1 55)
				(= local3 65)
			)
			(else 
				(= local0 20)
				(= local1 35)
				(= local3 125)
			)
		)
		(if (!= arcadeLevel 3)
			(switch heroType
				(2
					(= global176 3)
					(= global177 7)
					(= global178 10)
					(= global179 7)
				)
				(1
					(= global176 0)
					(= global177 10)
					(= global178 10)
				)
				(else 
					(= global176 5)
					(= global177 7)
					(= global178 10)
				)
			)
		)
		(= local5 (- 13 (/ (+ [egoStats 1] [egoStats 2]) 100)))
		(self cue: setScript: horrorCombat)
	)
	
	(method (cue)
		(self
			view: 856
			setLoop: 0 1
			cel: 0
			y: 140
			cycleSpeed: 10
			setCycle: Fwd
		)
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

(instance horrorCombat of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= ticks (Random local0 local1))
			)
			(1
				(SetNowSeen horror)
				(SetNowSeen gSActor)
				(= temp0 (- (horror nsLeft?) (gSActor nsRight?)))
				(cond 
					(
						(and
							(gCombatSpell active?)
							(not global189)
							(< (- (horror nsLeft?) (gCombatSpell x?)) 60)
						)
						(self setScript: wipeSpell self)
					)
					(local2
						(switch arcadeLevel
							(1 (= ticks 60))
							(2 (= ticks 30))
							(else  (= ticks 20))
						)
					)
					((> temp0 25)
						(if (not (split active?))
							(if (and (< local4 local3) (Random 0 arcadeLevel))
								(self setScript: splitAttack self)
							else
								(switch (Random 0 2)
									(0
										(self setScript: attackLeft self)
									)
									(1
										(self setScript: attackRight self)
									)
									(else  (self cue:))
								)
							)
						else
							(switch (Random 0 2)
								(0 (self cue:))
								(1
									(self setScript: attackRight self)
								)
								(else 
									(self setScript: attackLeft self)
								)
							)
						)
					)
					(else
						(switch (Random 0 6)
							(0 (self cue:))
							(1
								(self setScript: attackRight self)
							)
							(2
								(self setScript: attackLeft self)
							)
							(3
								(if (> arcadeLevel 1)
									(if (< local4 local3)
										(self setScript: splitAttack self)
									else
										(self setScript: attackLeft self)
									)
								else
									(self cue:)
								)
							)
							(4
								(if (== arcadeLevel 3)
									(if (< local4 local3)
										(self setScript: splitAttack self)
									else
										(self setScript: attackLeft self)
									)
								else
									(self cue:)
								)
							)
							(5
								(self setScript: attackRight self)
							)
							(else 
								(if (< local4 local3)
									(self setScript: splitAttack self)
								else
									(self setScript: attackRight self)
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

(instance wipeSpell of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				register
				(| (SetNowSeen horror) $0001)
				(or
					(gCombatSpell
						onMe: (+ (horror nsLeft?) 9) (+ (horror nsTop?) 24)
					)
					(gCombatSpell
						onMe: (+ (horror nsLeft?) 0) (+ (horror nsTop?) 30)
					)
				)
			)
			(= register 0)
			(gCombatSpell cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(horror
					cycleSpeed: 1
					setLoop: 5 1
					cel: 0
					setCycle: CT 3 1 self
				)
				(proc810_13 1 671)
			)
			(1 (horror setCycle: End self))
			(2
				(= register 0)
				(horror cue:)
				(self dispose:)
			)
		)
	)
)

(instance attackLeft of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(SetNowSeen horror)
		(if
			(and
				(or
					(gSActor
						onMe: (+ (horror nsLeft?) 6) (+ (horror nsTop?) 6)
					)
					(gSActor
						onMe: (+ (horror nsLeft?) 1) (+ (horror nsTop?) 36)
					)
				)
				register
			)
			(= register 0)
			(gSActor getHurt: 15 setScript: getBitHit)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 1)
				(horror
					cycleSpeed: 1
					setLoop: 6 1
					cel: 0
					setCycle: CT 5 1 self
				)
				(proc810_13 1 853)
			)
			(1
				(horror cel: 6)
				(= ticks 10)
			)
			(2
				(horror cue:)
				(self dispose:)
			)
		)
	)
)

(instance attackRight of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(SetNowSeen horror)
		(if
			(and
				(or
					(gSActor
						onMe: (+ (horror nsLeft?) 9) (+ (horror nsTop?) 24)
					)
					(gSActor
						onMe: (+ (horror nsLeft?) 0) (+ (horror nsTop?) 30)
					)
				)
				register
			)
			(= register 0)
			(gSActor getHurt: 15 setScript: getBitHit)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 1)
				(horror
					setLoop: 5 1
					cycleSpeed: 1
					cel: 0
					setCycle: CT 5 1 self
				)
				(proc810_13 1 853)
			)
			(1
				(horror cel: 6)
				(= ticks 10)
			)
			(2
				(horror cue:)
				(self dispose:)
			)
		)
	)
)

(instance splitAttack of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(++ local4)
				(horror
					cycleSpeed: 6
					setLoop: 3 1
					cel: 0
					setCycle: CT 4 1 self
				)
				(proc810_13 1 857)
			)
			(1
				(SetNowSeen horror)
				(SetNowSeen gSActor)
				(if (< (horror nsLeft?) (gSActor nsRight?))
					(gSActor getHurt: 15 setScript: faceSplit)
					(horror setLoop: 8 1 cel: 0)
					(= ticks 10)
				else
					(split
						x: (- (horror nsLeft?) 50)
						y: (= temp0 (+ (horror nsTop?) 42))
						myTarget: gSActor
						cel: 0
						show:
						setPri: (+ (horror priority?) 5)
						setMotion: ShotTo -15 temp0 split
					)
					(horror setCycle: End self)
				)
			)
			(2
				(horror cue:)
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
				(horror setLoop: 1 1 cycleSpeed: 3 cel: 0)
				(proc810_13 1 905)
				(= ticks 20)
			)
			(1
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
					(self dispose:)
				else
					(switch arcadeLevel
						(1 (self cue:))
						(2
							(switch (Random 0 5)
								(0
									(self setScript: splitAttack self)
								)
								(1
									(self setScript: attackRight self)
								)
								(2
									(self setScript: attackLeft self)
								)
								(else  (self cue:))
							)
						)
						(else 
							(switch (Random 0 4)
								(0
									(self setScript: attackRight self)
								)
								(1
									(self setScript: attackLeft self)
								)
								(else 
									(self setScript: splitAttack self)
								)
							)
						)
					)
				)
			)
			(2
				(horror cue:)
				(client setScript: horrorCombat)
			)
		)
	)
)

(instance hurtBySpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(horror
					cycleSpeed: 3
					setLoop: 7 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 1 905)
			)
			(1
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
					(self dispose:)
				else
					(horror cue:)
					(client setScript: horrorCombat)
				)
			)
		)
	)
)

(instance getSplitHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gPuzzleBar noHands: 1)
				(VibrateMouse 3 2 1)
				(= local2 1)
				(split setCycle: End self)
			)
			(1
				(split cue:)
				(gSActor
					view: (gSActor typeView?)
					moveSpeed: 0
					xStep: 4
					setLoop: 3 1
					cel: 0
					setCycle: End self
				)
				(if (> (gSActor x?) 45)
					(gSActor
						setMotion: ShotTo (- (gSActor x?) 20) (gSActor y?)
					)
				)
				(proc810_13 0 904)
			)
			(2
				(ShakeScreen 2)
				(= ticks 20)
			)
			(3
				(gSActor setMotion: 0)
				(gPuzzleBar noHands: 0)
				(= local2 0)
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

(instance faceSplit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gPuzzleBar noHands: 1)
				(VibrateMouse 3 2 1)
				(= local2 1)
				(gSActor
					view: (gSActor typeView?)
					moveSpeed: 0
					xStep: 4
					setLoop: 3 1
					cel: 0
					setCycle: End self
				)
				(if (> (gSActor x?) 45)
					(gSActor
						setMotion: ShotTo (- (gSActor x?) 20) (gSActor y?)
					)
				)
				(proc810_13 0 904)
			)
			(1
				(ShakeScreen 2)
				(= ticks 20)
			)
			(2
				(gSActor setMotion: 0)
				(gPuzzleBar noHands: 0)
				(= local2 0)
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

(instance getBitHit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gPuzzleBar noHands: 1)
				(VibrateMouse 3 2 1)
				(gSActor
					view: (gSActor typeView?)
					moveSpeed: 0
					xStep: 4
					setLoop: 2 1
					cel: 0
					setCycle: End self
				)
				(if (> (gSActor x?) 45)
					(gSActor
						setMotion: ShotTo (- (gSActor x?) 20) (gSActor y?)
					)
				)
				(proc810_13 0 911)
			)
			(1 (= ticks 20))
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

(instance split of CombatSpell
	(properties
		view 856
		loop 4
		signal $4000
		moveSpeed 0
	)
	
	(method (init)
		(= gCombatSpell_3 self)
		(super init: &rest)
		(self cue:)
	)
	
	(method (doit)
		(if (not (& signal $0008))
			(if mover (mover doit:))
			(if cycler (cycler doit:))
			(= xLast x)
			(= yLast y)
			(if (& -info- $0008) (UpdateScreenItem self))
			(if
				(and
					myTarget
					(or (myTarget onMe: x y) (myTarget onMe: (+ x 20) y))
				)
				(if (== ((= oldTarget myTarget) attackCode?) 23)
					(= myTarget 0)
					(self cue:)
				else
					(= myTarget 0)
					(self toDamage:)
				)
			)
		)
	)
	
	(method (cue)
		(if (not (horror cycler?)) (horror cue:))
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
				15
			)
		)
		(if temp1
			(gSActor getHurt: (/ temp2 5))
			(self setCycle: End self)
		else
			(gSActor getHurt: temp2 setScript: getSplitHit)
		)
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
			(0 (= ticks (Random 2 local5)))
			(1
				(SetNowSeen gSActor)
				(SetNowSeen horror)
				(= temp0 (- (horror nsLeft?) (gSActor nsRight?)))
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
							(split active?)
							(< (- (split x?) (gSActor nsRight?)) 40)
							(> (split x?) (gSActor nsLeft?))
						)
						(if (and (== global176 10) (> [egoStats 18] 7))
							(self setScript: (ScriptID 810 5) self)
						else
							(self setScript: (ScriptID 41 4) self)
						)
					)
					((> temp0 20)
						(cond 
							(
								(and
									(< temp0 40)
									(proc810_12 global176)
									(> [egoStats 18] 7)
								)
								(self setScript: (ScriptID 810 6) self)
							)
							(
							(and (proc810_12 global176) (> [egoStats 18] 30)) (self setScript: (ScriptID 41 2) self))
							(
								(and
									(proc810_12 global177)
									(> temp0 40)
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
					((> [egoStats 18] 7)
						(cond 
							((not (proc810_12 global176)) (self setScript: (ScriptID 41 1) self))
							((Random 0 3) (self setScript: (ScriptID 41 1) self))
							(else (self setScript: (ScriptID 810 7) self))
						)
					)
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
			(0 (= ticks (Random 2 local5)))
			(1
				(SetNowSeen gSActor)
				(SetNowSeen horror)
				(= temp0 (- (horror nsLeft?) (gSActor nsRight?)))
				(cond 
					(
						(and
							(proc810_12 global178)
							(or (< [egoStats 18] 2) (== global176 0))
							(> (gSActor nsLeft?) 20)
						)
						(self setScript: (ScriptID 41 3) self)
					)
					((< [egoStats 19] 1) (client setScript: fighterScript))
					((< temp0 10)
						(if (> [egoStats 18] 7)
							(cond 
								((not (proc810_12 global176)) (self setScript: (ScriptID 41 1) self))
								((Random 0 3) (self setScript: (ScriptID 41 1) self))
								(else (self setScript: (ScriptID 810 7) self))
							)
						else
							(self cue:)
						)
					)
					(
						(and
							(proc810_12 global178)
							(split active?)
							(< (- (split x?) (gSActor nsRight?)) 40)
							(> (split x?) (gSActor nsLeft?))
						)
						(self setScript: (ScriptID 41 4) self)
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
					((and (proc810_12 global176) (< global178 2)) (self setScript: (ScriptID 41 2) self))
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
			(0 (= ticks (Random 2 local5)))
			(1
				(SetNowSeen gSActor)
				(SetNowSeen horror)
				(= temp0 (- (horror nsLeft?) (gSActor nsRight?)))
				(cond 
					(
						(and
							(proc810_12 global178)
							(split active?)
							(< (- (split x?) (gSActor nsRight?)) 45)
							(> (split x?) (gSActor nsLeft?))
						)
						(self setScript: (ScriptID 41 4) self)
					)
					((< temp0 10)
						(if (> [egoStats 18] 7)
							(cond 
								((not (proc810_12 global176)) (self setScript: (ScriptID 41 1) self))
								((Random 0 3) (self setScript: (ScriptID 41 1) self))
								(else (self setScript: (ScriptID 810 7) self))
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
							(> [egoStats 18] 100)
							(> temp0 40)
							(proc810_12 global176)
							(proc810_12 global179)
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
							(proc810_12 global177)
							(> [egoStats 18] 5)
							(> [egoStats 19] 5)
							(> temp0 30)
							(not (gCombatSpell active?))
						)
						(if (or [egoStats 28] [egoStats 33] [egoStats 34])
							(self setScript: (ScriptID 41 5) self (Random 0 1))
						else
							(self setScript: (ScriptID 41 5) self 0)
						)
					)
					((and (proc810_12 global176) (< global178 2)) (self setScript: (ScriptID 41 2) self))
					(else (self cue:))
				)
			)
			(2 (self changeState: 0))
		)
	)
)
