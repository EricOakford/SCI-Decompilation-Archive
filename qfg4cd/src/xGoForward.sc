;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include sci.sh)
(use Main)
(use SView)
(use combat)
(use Motion)
(use User)
(use System)

(public
	xToCastSpell 0
	xSlash 1
	xForward 2
	xBackward 3
	xDuck 4
	xdoSpell 5
	xParryLow 6
	xGoForward 7
	xGoBackward 8
)

(instance xGoForward of Script
	(properties)
	
	(method (init)
		(= register 0)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(SetNowSeen global185)
		(SetNowSeen gSActor)
		(cond 
			(
				(or
					(OneOf ((gPuzzleBar combatEvent?) type?) 2 8 64)
					(and
						(& ((gPuzzleBar combatEvent?) type?) $0200)
						(>= ((gPuzzleBar combatEvent?) roll?) 0)
					)
				)
				(= register 1)
			)
			((>= (gSActor nsRight?) (global185 nsLeft?))
				(if (gSActor cel?) (gSActor x: (+ (gSActor x?) 17)))
				(gSActor cue:)
				(self dispose:)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(<
						(+ (gSActor x?) (gSActor width:))
						(- (global185 x?) (global185 width:))
					)
					(gSActor
						view: (gSActor typeView?)
						loop: 1
						cel: 0
						cycleSpeed: 6
						attackCode: 0
						setCycle: End self
					)
				else
					(gSActor cue:)
					(self dispose:)
				)
			)
			(1
				(gSActor setLoop: 0 x: (+ (gSActor x?) 20))
				(if register
					(gSActor cue:)
					(self dispose:)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance xGoBackward of Script
	(properties)
	
	(method (init)
		(= register 0)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(or
					(OneOf ((User curEvent?) type?) 2 8 64)
					(and
						(& ((gPuzzleBar combatEvent?) type?) $0200)
						(<= ((gPuzzleBar combatEvent?) roll?) 0)
					)
				)
				(= register 1)
			)
			((<= (gSActor x?) 35) (gSActor cue:) (self dispose:))
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (gSActor x?) 25)
					(gSActor
						view: (gSActor typeView?)
						loop: 1
						cel: 4
						x: (- (gSActor x?) 30)
						attackCode: 1
						cycleSpeed: 6
						setCycle: Beg self
					)
				else
					(self dispose:)
				)
			)
			(1
				(if register
					(gSActor cue:)
					(self dispose:)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance xToCastSpell of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(switch state
			(0
				(if
				(OneOf ((gPuzzleBar combatEvent?) type?) 2 8 64)
					(gSActor cue:)
					(self dispose:)
				)
			)
			(1
				(cond 
					(
					(OneOf ((gPuzzleBar combatEvent?) type?) 2 8 64) (self cue:))
					(
						(or
							(> global186 38)
							(< [egoStats 19] 0)
							(< [egoStats 18] 0)
						)
						(self cue:)
					)
					(else
						(-- [egoStats 18])
						(= temp0 (proc810_1 18))
						(UpdateScreenItem
							((ScriptID 40 3)
								x: (- temp0 46)
								setInsetRect: (- 80 temp0) 0 80 2
								yourself:
							)
						)
						((ScriptID 40 1)
							x: (- ((ScriptID 40 1) x?) 3)
							setInsetRect: 0 0 (= global186 (+ global186 3)) 2
						)
						((ScriptID 40 2)
							x: (+ ((ScriptID 40 2) x?) 3)
							setInsetRect: (- 40 global186) 0 40 2
						)
						(UpdateScreenItem (ScriptID 40 1))
						(UpdateScreenItem (ScriptID 40 2))
					)
				)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(gSActor
					view: (gSActor typeView?)
					loop: 11
					cel: 0
					attackCode: 21
				)
				(= ticks 20)
				(= global189 0)
			)
			(1 0)
			(2
				(if (>= global186 38)
					(= global165 (/ global165 3))
					(= global189 1)
				else
					(= global165 (- global165 (/ (+ global186 1) 4)))
				)
				(gSActor cue:)
				((ScriptID 40 1) x: 159 setInsetRect: 0 0 0 0)
				((ScriptID 40 2) x: 119 setInsetRect: 0 0 0 -1)
				(UpdateScreenItem (ScriptID 40 1))
				(UpdateScreenItem (ScriptID 40 2))
				(gCombatSpell
					myTarget: global185
					setLoop:
						(switch register
							((ScriptID 40 9)
								(if (>= global186 38) 8 else 3)
							)
							(else 
								(if (>= global186 38) 6 else 0)
							)
						)
						1
					cel: 0
					x: (+ (gSActor x?) 55)
					y: (= temp0 (- (gSActor y?) 35))
					setPri: (+ (gSActor priority?) 5)
					setCycle: Fwd
					setMotion: ShotTo 330 temp0 gCombatSpell
					show:
				)
				(= global186 0)
				(self dispose:)
			)
		)
	)
)

(instance xSlash of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(SetNowSeen gSActor)
		(if
			(and
				register
				(or
					(global185
						onMe: (+ (gSActor nsLeft?) global182) (+ (gSActor nsTop?) global183)
					)
					(and
						(== state 1)
						(| (SetNowSeen global185) $0001)
						(>= (gSActor nsRight?) (global185 nsLeft?))
					)
				)
			)
			(++ global167)
			(= register 0)
			((ScriptID 810 3)
				x: (+ (gSActor nsLeft?) global182)
				y: (+ (gSActor nsTop?) global183)
				cel: 0
				setCycle: End (ScriptID 810 3)
				setPri: (+ (global185 priority?) 2)
				show:
			)
			(global185 getHurt: 2)
			(gSActor dedectPts: (proc810_2))
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= register 1)
				(gSActor
					view: (gSActor typeView?)
					setLoop: 7 1
					cel: 0
					cycleSpeed: 5
					attackCode: 4
					setCycle: CT 3 1 self
				)
				(proc810_13 0 901)
			)
			(1 (= ticks 10))
			(2 (gSActor setCycle: End self))
			(3
				(= [egoStats 18] (- [egoStats 18] (Random 5 15)))
				(= temp0 (proc810_1 18))
				(UpdateScreenItem
					((ScriptID 40 3)
						x: (- temp0 46)
						setInsetRect: (- 80 temp0) 0 80 2
						yourself:
					)
				)
				(gSActor cue:)
				(self dispose:)
			)
		)
	)
)

(instance xForward of Script
	(properties)
	
	(method (init)
		(= register 0)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(SetNowSeen global185)
		(SetNowSeen gSActor)
		(if
			(or
				(< global176 1)
				(>= (gSActor nsRight?) (global185 nsLeft?))
				(and
					gCombatSpell_3
					(gCombatSpell_3 active?)
					(< (- (gCombatSpell_3 x?) (gSActor nsRight?)) 45)
					(> (gCombatSpell_3 x?) (gSActor nsLeft?))
				)
			)
			(if (gSActor cel?) (gSActor x: (+ (gSActor x?) 17)))
			(gSActor cue:)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gSActor
					view: (gSActor typeView?)
					loop: 1
					cel: 0
					cycleSpeed: 6
					attackCode: 0
					setCycle: End self
				)
			)
			(1
				(gSActor setLoop: 0 x: (+ (gSActor x?) 20))
				(if register
					(gSActor cue:)
					(self dispose:)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance xBackward of Script
	(properties)
	
	(method (init)
		(= register 0)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if (<= (gSActor x?) 40)
			(gSActor cue:)
			(self dispose:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (gSActor x?) 25)
					(gSActor
						view: (gSActor typeView?)
						loop: 1
						cel: 4
						x: (- (gSActor x?) 30)
						attackCode: 1
						cycleSpeed: 6
						setCycle: Beg self
					)
				else
					(self dispose:)
				)
			)
			(1
				(if register
					(gSActor cue:)
					(self dispose:)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance xDuck of Script
	(properties)
	
	(method (init)
		(= register 1)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				gCombatSpell_3
				register
				(| (SetNowSeen gSActor) $0001)
				(or
					(not (gCombatSpell_3 active?))
					(< (gCombatSpell_3 x?) (gSActor nsLeft?))
				)
			)
			(= register 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gSActor
					view: (+ (gSActor typeView?) 1)
					setLoop: 6 1
					cel: 0
					attackCode: 6
				)
				(++ global168)
			)
			(1
				(gSActor cue:)
				(self dispose:)
			)
		)
	)
)

(instance xParryLow of Script
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
				(or
					(and gCombatSpell_3 (not (gCombatSpell_3 active?)))
					(and
						gCombatSpell_3
						(| (SetNowSeen gSActor) $0001)
						(< (gCombatSpell_3 x?) (gSActor nsLeft?))
					)
				)
			)
			(= global192 (& global192 $fffd))
			(= register 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global192 (| global192 $0002))
				(gSActor
					view: (+ (gSActor typeView?) 1)
					setLoop: (if (== (gSActor typeView?) 44) 3 else 1) 1
					cel: 0
					attackCode: 20
				)
				(++ global169)
			)
			(1
				(gSActor cue:)
				(= global192 (& global192 $fffd))
				(self dispose:)
			)
		)
	)
)

(instance xdoSpell of Script
	(properties)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (== state 1)
			(SetNowSeen gSActor)
			(SetNowSeen global185)
			(if
				(or
					(> global186 38)
					(< [egoStats 19] 0)
					(< [egoStats 18] 0)
					(< (- (global185 nsLeft?) (gSActor nsRight?)) 25)
					(< global177 1)
					(and
						gCombatSpell_3
						(gCombatSpell_3 active?)
						(< (- (gCombatSpell_3 x?) (gSActor nsRight?)) 45)
						(> (gCombatSpell_3 x?) (gSActor nsLeft?))
					)
				)
				(self cue:)
			else
				(-- [egoStats 18])
				(= temp0 (proc810_1 18))
				(UpdateScreenItem
					((ScriptID 40 3)
						x: (- temp0 46)
						setInsetRect: (- 80 temp0) 0 80 2
						yourself:
					)
				)
				((ScriptID 40 1)
					x: (- ((ScriptID 40 1) x?) 3)
					setInsetRect: 0 0 (= global186 (+ global186 3)) 2
				)
				((ScriptID 40 2)
					x: (+ ((ScriptID 40 2) x?) 3)
					setInsetRect: (- 40 global186) 0 40 2
				)
				(UpdateScreenItem (ScriptID 40 1))
				(UpdateScreenItem (ScriptID 40 2))
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(gSActor
					view: (gSActor typeView?)
					loop: 11
					cel: 0
					attackCode: 21
				)
				(= ticks 20)
				(= global189 0)
			)
			(1
				(SetNowSeen gSActor)
				(SetNowSeen global185)
				(if
				(> (- (global185 nsLeft?) (gSActor nsLeft?)) 80)
					(if (proc810_12 global179)
						(= cycles 15)
					else
						(= cycles (Random 3 6))
					)
				else
					(= cycles (Random 2 4))
				)
			)
			(2
				(gSActor cue:)
				((ScriptID 40 1) x: 159 setInsetRect: 0 0 0 0)
				((ScriptID 40 2) x: 119 setInsetRect: 0 0 0 -1)
				(UpdateScreenItem (ScriptID 40 1))
				(UpdateScreenItem (ScriptID 40 2))
				(gCombatSpell
					myTarget: global185
					setLoop:
						(if register
							(= spellCost 10)
							(++ global172)
							(= global165 20)
							(gCombatSpell type: 26)
							(if (>= global186 38) 8 else 3)
						else
							(switch (Random 0 2)
								(0
									(cond 
										([egoStats 28]
											(= spellCost 12)
											(= global165 30)
											(gCombatSpell type: 28)
											(++ global173)
										)
										([egoStats 33]
											(= spellCost 8)
											(= global165 16)
											(gCombatSpell type: 33)
											(++ global174)
										)
										([egoStats 34]
											(= spellCost 8)
											(= global165 16)
											(gCombatSpell type: 34)
											(++ global175)
										)
									)
								)
								(1
									(cond 
										([egoStats 33]
											(= spellCost 8)
											(= global165 16)
											(gCombatSpell type: 33)
											(++ global174)
										)
										([egoStats 28]
											(= spellCost 12)
											(= global165 30)
											(gCombatSpell type: 28)
											(++ global173)
										)
										([egoStats 34]
											(= spellCost 8)
											(= global165 16)
											(gCombatSpell type: 34)
											(++ global175)
										)
									)
								)
								(else 
									(cond 
										([egoStats 34]
											(= spellCost 8)
											(= global165 16)
											(gCombatSpell type: 34)
											(++ global175)
										)
										([egoStats 33]
											(= spellCost 8)
											(= global165 16)
											(gCombatSpell type: 33)
											(++ global174)
										)
										([egoStats 28]
											(= spellCost 12)
											(= global165 30)
											(gCombatSpell type: 28)
											(++ global173)
										)
									)
								)
							)
							(if (>= global186 38) 6 else 0)
						)
						1
					cel: 0
					x: (+ (gSActor x?) 55)
					y: (= temp0 (- (gSActor y?) 35))
					setPri: (+ (gSActor priority?) 5)
					setCycle: Fwd
					setMotion: ShotTo 330 temp0 gCombatSpell
					show:
				)
				(if (>= global186 38)
					(= global165 (/ global165 3))
					(= global189 1)
				else
					(= global165 (- global165 (/ (+ global186 1) 4)))
				)
				(= global186 0)
				(self dispose:)
			)
		)
	)
)
