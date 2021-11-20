;;; Sierra Script 1.0 - (do not remove this comment)
(script# 850)
(include sci.sh)
(use Main)
(use SView)
(use combat)
(use Motion)
(use System)

(public
	wraith 0
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
(instance wraith of SActor
	(properties
		x 273
		y 158
		view 851
		signal $4000
		cycleSpeed 16
		explodeX 20
	)
	
	(method (init)
		(super init: &rest)
		(wSpell init: gPuzzleBar)
		(if (Btst 381)
			(= local3 3)
		else
			(= local3 (/ 30 arcadeLevel))
		)
		(if
			(OneOf
				prevRoomNum
				551
				552
				553
				554
				555
				556
				557
				558
				559
				560
				561
				562
				563
				564
				565
				566
				567
				568
				569
				570
				571
				572
				573
				574
				575
				576
				577
				578
				579
				580
				581
				582
				583
				584
				585
				586
				587
				588
				589
				590
				591
				592
				593
			)
			(cRocks init: gPuzzleBar)
		)
		(switch arcadeLevel
			(1 (= local0 60) (= local1 80))
			(2 (= local0 25) (= local1 45))
			(else 
				(= local0 15)
				(= local1 30)
			)
		)
		(if (!= arcadeLevel 3)
			(switch heroType
				(2
					(= global176 3)
					(= global177 4)
					(= global178 7)
					(= global179 5)
				)
				(1
					(= global176 0)
					(= global177 10)
					(= global178 10)
				)
				(else 
					(= global176 7)
					(= global177 2)
					(= global178 5)
				)
			)
		)
		(= local5 (- 13 (/ (+ [egoStats 1] [egoStats 2]) 100)))
		(= local4
			(if (not (ego has: 37)) (not auraTimer) else 0)
		)
		(self cue: setScript: wraithCombat)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(if (and local4 (not (-- local3)))
			(if (Btst 381)
				(= local3 3)
			else
				(= local3 (/ 30 arcadeLevel))
			)
			(if
			(>= (= [egoStats 17] (- [egoStats 17] arcadeLevel)) 0)
				(= temp0 (proc810_1 17))
				(UpdateScreenItem
					((ScriptID 40 5)
						x: (- temp0 94)
						setInsetRect: (- 104 temp0) 0 104 2
						yourself:
					)
				)
			else
				(= battleResult 1)
				(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
			)
		)
	)
	
	(method (cue)
		(self
			view: 851
			y: 148
			setLoop: 0 1
			cel: 0
			cycleSpeed: 16
			setCycle: Fwd
		)
	)
	
	(method (getHurt param1)
		(switch param1
			(0
				(if (or (!= (wraith attackCode?) 23) global189)
					(self setScript: hurtBySpell)
				)
			)
			(2
				(self setScript: hurtByDagger)
			)
		)
	)
)

(instance cRocks of SView
	(properties
		x 280
		y 140
		view 851
		loop 9
	)
	
	(method (doVerb)
		(global185 doVerb:)
	)
)

(instance hurtByDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wraith setLoop: 1 1 cel: 0 setCycle: End self)
				(proc810_13 1 853)
			)
			(1
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
					(self dispose:)
				else
					(wraith cue:)
					(client setScript: wraithCombat)
				)
			)
		)
	)
)

(instance hurtBySpell of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wraith
					cycleSpeed: 3
					setLoop: 7 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 1 853)
			)
			(1
				(if (Btst 386)
					(gPuzzleBar state: (& (gPuzzleBar state?) $ffdf))
					(self dispose:)
				else
					(wraith cue:)
					(client setScript: wraithCombat)
				)
			)
		)
	)
)

(instance blockIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wraith
					attackCode: 23
					cycleSpeed: 1
					setLoop: 2 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 1 942)
			)
			(1
				(if (gCombatSpell active?)
					(self changeState: 0)
				else
					(wraith attackCode: 0 cue:)
					(self dispose:)
				)
			)
		)
	)
)

(instance wraithCombat of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= ticks (Random local0 local1))
			)
			(1
				(SetNowSeen wraith)
				(SetNowSeen gSActor)
				(= temp0 (- (wraith nsLeft?) (gSActor nsRight?)))
				(cond 
					((gCombatSpell active?)
						(cond 
							((> arcadeLevel 1)
								(switch (Random 0 4)
									(0
										(if (== arcadeLevel 3)
											(self setScript: blockIt self)
										else
											(self cue:)
										)
									)
									(1
										(self setScript: blockIt self)
									)
									(2
										(if (== arcadeLevel 3)
											(self setScript: blockIt self)
										else
											(self cue:)
										)
									)
									(3 (self cue:))
									(else 
										(self setScript: blockIt self)
									)
								)
							)
							((Random 0 4) (self cue:))
							(else (self setScript: blockIt self))
						)
					)
					(local2 (self cue:))
					((not (wSpell active?))
						(if (< temp0 5)
							(if (Random 0 arcadeLevel)
								(self setScript: throwEgo self)
							else
								(self cue:)
							)
						else
							(switch (Random 0 4)
								(0 (self cue:))
								(1
									(self setScript: castLSpell self)
								)
								(2
									(self setScript: castLSpell self)
								)
								(else 
									(self setScript: castHSpell self)
								)
							)
						)
					)
					(else (self cue:))
				)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance throwEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(wraith
					cycleSpeed: 6
					setLoop: 5 1
					cel: 0
					setCycle: End self
				)
			)
			(1
				(wraith cue:)
				(gPuzzleBar noHands: 1)
				(gSActor
					setScript: 0
					getHurt: 12
					view: (gSActor typeView?)
					moveSpeed: 0
					xStep: 10
					setLoop: 2 1
					cel: 0
					setCycle: End
				)
				(if (> (gSActor x?) 45)
					(gSActor
						setMotion: ShotTo (- (gSActor x?) 45) (gSActor y?) self
					)
				)
				(proc810_13 0 904)
			)
			(2
				(ShakeScreen 3)
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

(instance castHSpell of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(wraith
					cycleSpeed: 8
					setLoop: 3 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 1 852)
			)
			(1
				(wSpell
					setLoop: 1 1
					cel: 0
					myTarget: gSActor
					x: (- (wraith x?) 50)
					y: (= temp0 (- (wraith y?) 35))
					setPri: (+ (wraith priority?) 5)
					setCycle: Fwd
					setMotion: MoveTo 0 temp0 wSpell
					show:
				)
				(wraith cue:)
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
				(wraith
					cycleSpeed: 8
					setLoop: 4 1
					cel: 0
					setCycle: End self
				)
				(proc810_13 1 852)
			)
			(1
				(wSpell
					cycleSpeed: 3
					setLoop: 1 1
					cel: 0
					myTarget: gSActor
					x: (- (wraith x?) 50)
					y: (= temp0 (- (wraith y?) 60))
					setPri: (+ (wraith priority?) 5)
					setCycle: Fwd
					setMotion: MoveTo 0 temp0 wSpell
					show:
				)
				(wraith cue:)
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
				(gPuzzleBar noHands: 1)
				(= local2 1)
				(wSpell setLoop: 2 1 cel: 0 setCycle: CT 2 1 self)
			)
			(1
				(VibrateMouse 4 1 1)
				(wSpell setCycle: End wSpell)
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
				(proc810_13 0 911)
			)
			(2 (= ticks 20))
			(3
				(gSActor setMotion: 0)
				(= local2 0)
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
				8
			)
		)
		(if resistTimer
			(= temp2 (/ (* temp2 [egoStats 39]) 400))
		)
		(if temp1
			(gSActor getHurt: (/ temp2 5))
			(self setLoop: 2 1 cel: 0 setCycle: End self)
		else
			(gSActor getHurt: temp2 setScript: getSpellHit)
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
				(SetNowSeen wraith)
				(= temp0 (- (wraith nsLeft?) (gSActor nsRight?)))
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
							(> global178 1)
							(wSpell active?)
							(< (- (wSpell x?) (gSActor nsRight?)) 40)
							(> (wSpell x?) (gSActor nsLeft?))
						)
						(cond 
							((< temp0 25)
								(cond 
									((> (wSpell y?) 100)
										(cond 
											((> [egoStats 18] 7)
												(cond 
													((not global178) (self cue:))
													((> global178 9) (self setScript: (ScriptID 41 6) self))
													(else (self setScript: (ScriptID 810 4) self))
												)
											)
											((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
											(else (self cue:))
										)
									)
									((proc810_12 global178) (self setScript: (ScriptID 41 4) self))
									(else (self cue:))
								)
							)
							(
								(and
									(proc810_12 global176)
									(< temp0 5)
									(> [egoStats 18] 7)
									(> (wSpell y?) 100)
								)
								(self setScript: (ScriptID 810 7) self)
							)
							((> (wSpell y?) 100)
								(cond 
									((> [egoStats 18] 7) (self setScript: (ScriptID 810 5) self))
									((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
									(else (self cue:))
								)
							)
							(else (self setScript: (ScriptID 41 4) self))
						)
					)
					((and (> temp0 20) (proc810_12 global176))
						(cond 
							((and (< temp0 5) (> [egoStats 18] 7)) (self setScript: (ScriptID 810 7) self))
							((and (< temp0 40) (> [egoStats 18] 7)) (self setScript: (ScriptID 810 6) self))
							((> [egoStats 18] 10) (self setScript: (ScriptID 41 2) self))
							(
								(and
									(> [egoStats 19] 5)
									(proc810_12 global177)
									(> temp0 40)
									(> [egoStats 18] 5)
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
							(> [egoStats 19] 5)
							(proc810_12 global177)
							(> temp0 40)
							(> [egoStats 18] 5)
							(not (gCombatSpell active?))
						)
						(if (or [egoStats 28] [egoStats 33] [egoStats 34])
							(self setScript: (ScriptID 41 5) self (Random 0 1))
						else
							(self setScript: (ScriptID 41 5) self 0)
						)
					)
					((< temp0 10)
						(cond 
							((> [egoStats 18] 7)
								(if (or (proc810_12 global176) (> temp0 5))
									(self setScript: (ScriptID 41 1) self)
								else
									(self setScript: (ScriptID 810 7) self)
								)
							)
							(
								(and
									(wSpell active?)
									(< (- (wSpell x?) (gSActor nsRight?)) 40)
									(> (wSpell x?) (gSActor nsLeft?))
								)
								(if (> (wSpell y?) 100)
									(cond 
										((> [egoStats 18] 7) (self setScript: (ScriptID 810 5) self))
										((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
										(else (self cue:))
									)
								else
									(self setScript: (ScriptID 41 4) self)
								)
							)
							(else (self cue:))
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
				(SetNowSeen wraith)
				(= temp0 (- (wraith nsLeft?) (gSActor nsRight?)))
				(cond 
					((or (< [egoStats 19] 1) (< [egoStats 17] 50)) (client setScript: fighterScript))
					(
						(and
							(proc810_12 global178)
							(or (< [egoStats 18] 2) (== global176 0))
							(> (gSActor nsLeft?) 20)
						)
						(self setScript: (ScriptID 41 3) self)
					)
					((< temp0 10)
						(cond 
							((> [egoStats 18] 7)
								(if (or (proc810_12 global176) (> temp0 5))
									(self setScript: (ScriptID 41 1) self)
								else
									(self setScript: (ScriptID 810 7) self)
								)
							)
							(
								(and
									(wSpell active?)
									(< (- (wSpell x?) (gSActor nsRight?)) 40)
									(> (wSpell x?) (gSActor nsLeft?))
								)
								(if (> (wSpell y?) 100)
									(cond 
										((> [egoStats 18] 7)
											(cond 
												((not global178) (self cue:))
												((> global178 9) (self setScript: (ScriptID 41 6) self))
												(else (self setScript: (ScriptID 810 4) self))
											)
										)
										((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
										(else (self cue:))
									)
								else
									(self setScript: (ScriptID 41 4) self)
								)
							)
							(else (self cue:))
						)
					)
					(
						(and
							(> global178 1)
							(wSpell active?)
							(< (- (wSpell x?) (gSActor nsRight?)) 40)
							(> (wSpell x?) (gSActor nsLeft?))
						)
						(if (> (wSpell y?) 100)
							(cond 
								((> [egoStats 18] 7)
									(if (> global178 9)
										(self setScript: (ScriptID 41 6) self)
									else
										(self setScript: (ScriptID 810 4) self)
									)
								)
								((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
								(else (self cue:))
							)
						else
							(self setScript: (ScriptID 41 4) self)
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
			(0 (= ticks (Random 2 local5)))
			(1
				(SetNowSeen gSActor)
				(SetNowSeen wraith)
				(= temp0 (- (wraith nsLeft?) (gSActor nsRight?)))
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
							(> global178 1)
							(wSpell active?)
							(< (- (wSpell x?) (gSActor nsRight?)) 40)
							(> (wSpell x?) (gSActor nsLeft?))
						)
						(if (> (wSpell y?) 100)
							(cond 
								((> [egoStats 18] 7)
									(if (> global178 9)
										(self setScript: (ScriptID 41 6) self)
									else
										(self setScript: (ScriptID 810 4) self)
									)
								)
								((proc810_12 global178) (self setScript: (ScriptID 41 6) self))
								(else (self cue:))
							)
						else
							(self setScript: (ScriptID 41 4) self)
						)
					)
					((< temp0 10)
						(if (> [egoStats 18] 7)
							(if (or (proc810_12 global176) (> temp0 5))
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
					((and (proc810_12 global176) (> temp0 20)) (self setScript: (ScriptID 41 2) self))
					(else (self cue:))
				)
			)
			(2 (self changeState: 0))
		)
	)
)
