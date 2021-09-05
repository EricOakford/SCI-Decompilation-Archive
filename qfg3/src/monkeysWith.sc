;;; Sierra Script 1.0 - (do not remove this comment)
(script# 701)
(include game.sh)
(use Main)
(use Target)
(use TellerIcon)
(use EgoDead)
(use OccasionalCycle)
(use PolyPath)
(use Polygon)
(use Motion)
(use Actor)
(use System)

(public
	monkeysWith 0
	monkeysEnter 1
	manuInCage 2
	openCage 3
	manuLeave 4
	manuEncounter2 5
	cage 6
)

(local
	local0
	[newActor 10]
	local11
	local12
	local13
	local14 = [100 130 160 190 220 115 145 175 215 245]
	local52_2_2 = [155 147 173 135 170 162 119 133 140 167]
	local52_2 = [0 26 68 69 67 999]
	local49_2 = [0 66 -36 31 999]
	local52_3 = [0 100 999 999]
	local49_2_2 = [0 76 999]
	[local52_2_2_2 6]
	local58
)
(instance monkeysWith of Script
	(method (doit)
		(if (not (Random 0 30)) (ShakeScreen 1))
		(super doit:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(= temp0 0)
				(while (< temp0 10)
					((= [newActor temp0] (Actor new:))
						view: 985
						loop: (Random 0 3)
						moveSpeed: 0
						cycleSpeed: 0
						signal: 16384
						posn: [local14 temp0] [local52_2_2 temp0]
						setCycle: Walk
						init:
					)
					(++ temp0)
				)
				(= cycles 2)
			)
			(1
				(ShakeScreen 1)
				(messager say: 5 6 70 0 self 700)
			)
			(2
				(= temp0 0)
				(while (< temp0 10)
					([newActor temp0]
						setScript: (monkeyExit new:) 0 [newActor temp0]
					)
					(++ temp0)
				)
				(= seconds 4)
			)
			(3 (HandsOn) (= seconds 5))
			(4 (self dispose:))
		)
	)
)

(instance manuEnter of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(register
					setMotion: PolyPath [local14 9] [local52_2_2 9] self
				)
			)
			(1 (= seconds 3))
			(2
				(if local11
					(ego solvePuzzle: 212 3 1)
					(messager say: 5 6 77 0 self 700)
				else
					(messager say: 5 6 78 0 self 700)
				)
			)
			(3
				(curRoom newRoom: prevRoomNum)
			)
		)
	)
)

(instance monkeysEnter of Script
	(properties)
	
	(method (init theClient theCaller theRegister param4)
		(= lastTicks gameTime)
		(if (>= argc 1)
			((= client theClient) script: self)
			(if (>= argc 2)
				(= caller theCaller)
				(if (>= argc 3)
					(= register theRegister)
					(if (>= argc 4) (= local11 param4))
				)
			)
		)
		(= state (- start 1))
		(self cue:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 160 156 self)
				(= temp0 0)
				(while (< temp0 10)
					((= [newActor temp0] (Actor new:))
						view: 985
						loop: (Random 0 3)
						moveSpeed: 0
						cycleSpeed: 0
						x: (if (Random 0 1) -50 else 350)
						y: (Random 100 500)
						signal: 16384
						setCycle: Walk
						init:
					)
					(if (== temp0 9)
						([newActor temp0] setScript: manuEnter 0 [newActor temp0])
					else
						([newActor temp0]
							setMotion: PolyPath [local14 temp0] [local52_2_2 temp0]
						)
					)
					(++ temp0)
				)
			)
			(1
				(Face ego 160 200)
				(self dispose:)
			)
		)
	)
)

(instance monkeyExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(register
					setMotion:
						PolyPath
						(if (Random 0 1) -50 else 350)
						(Random 100 500)
						self
				)
			)
			(1 (register dispose:))
		)
	)
)

(instance manuInCage of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(cage
					init:
					approachX: 154
					approachY: 153
					approachVerbs: V_DO 20 12
					signal: ignrAct
				)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 160 133 178 152 110 152 95 133
							yourself:
						)
				)
				(manu700 init: cycleSpeed: 18 setCycle: Forward)
				(self dispose:)
			)
		)
	)
)

(instance openCage of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bset 109)
				(if local0
					(ego setHeading: 270 self)
				else
					(= state 3)
					(self cue:)
				)
				(cage approachVerbs:)
			)
			(1
				(ego view: 4 loop: 1 cel: 0 setCycle: CycleTo 3 1 self)
			)
			(2 (= cycles 20))
			(3 (ego setCycle: BegLoop self))
			(4
				(ego normalize: setHeading: 100)
				(cage cel: 1)
				(globalSound number: 481 setLoop: 1 play:)
				(manu700
					setLoop: 2
					cel: 0
					setCycle: Walk
					cycleSpeed: 0
					moveSpeed: 0
					setMotion: MoveTo 350 160 self
				)
			)
			(5
				(ego solvePuzzle: 315 8)
				(= seconds 3)
			)
			(6
				(manu700
					setLoop: 3
					x: 330
					y: 34
					signal: (| (manu700 signal?) $0010)
					priority: 15
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 291 45 self
				)
			)
			(7
				(manu700
					loop: 4
					z: 100
					y: (+ (manu700 y?) 100)
					setCycle: OccasionalCycle manu700 1 50 100
				)
				(= [local52_2_2_2 0] @local52_2)
				(= [local58 0] @local49_2)
				(manuTell init: manu700 @local52_2 @local52_2_2_2)
				(egoTell init: ego @local49_2 @local58)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance manuEncounter2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(manu700
					init:
					setCycle: Walk
					x: 330
					y: 34
					signal: (| (manu700 signal?) $0010)
					priority: 15
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 291 45 self
					setLoop: 3
				)
			)
			(1
				(manu700
					z: 100
					y: (+ (manu700 y?) 100)
					loop: 4
					setCycle: OccasionalCycle manu700 1 50 100
				)
				(messager say: 5 6 9)
				(= [local58 0] @local52_3)
				(egoTell init: ego @local52_3 @local58)
				(= [local52_2_2_2 0] @local49_2_2)
				(manuTell init: manu700 @local49_2_2 @local52_2_2_2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance manuLead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Bclr 105)
				(= cycles 20)
			)
			(1
				(Bset 93)
				(manu700
					z: 0
					y: (- (manu700 y?) 100)
					setLoop: 2
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 340 34
				)
				(ego setMotion: PolyPath 350 160 self solvePuzzle: 316 3)
			)
		)
	)
)

(instance manuLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(manu700
					z: 0
					y: (- (manu700 y?) 100)
					setLoop: 2
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 340 34 self
				)
				(egoTell dispose:)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance manu700 of TargActor
	(properties
		x 131
		y 146
		noun 5
		view 987
		loop 1
		cel 3
		priority 7
		signal $6010
		cycleSpeed 1
		moveSpeed 0
	)
	
	(method (init)
		(self approachVerbs: 12 13 11)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 12 13 11)
			(EgoDead 7)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (getHurt)
		(EgoDead 7)
	)
)

(instance cage of View
	(properties
		x 133
		y 147
		noun 8
		view 987
	)
	
	(method (doVerb theVerb)
		(cond 
			((or (== theVerb 4) (== theVerb 20))
				(if (not (Btst 109))
					(= local0 1)
					(manu700 setScript: openCage)
				else
					(super doVerb: theVerb &rest)
				)
			)
			((== theVerb -75) (manu700 setScript: openCage))
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance egoTell of Teller
	(properties)
	
	(method (dispose)
		(if client (client actions: 0))
		(super dispose:)
	)
	
	(method (doChild param1)
		(return
			(cond 
				((== param1 -10) (ego setScript: manuLead) (return 1))
				((== param1 -36) (ego setScript: manuLeave))
				(else
					(++ local13)
					(messager say: 2 5 11 1 0 700)
					(if (< local13 3)
						(messager say: 2 5 11 (+ local13 1) 0 700)
					else
						(messager say: 2 5 11 5 0 700)
						(messager say: 2 5 11 6 0 700)
						(ego setScript: manuLead)
					)
					(return 0)
				)
			)
		)
	)
)

(instance manuTell of Teller
	(properties)
	
	(method (doDialog &tmp temp0)
		(= temp0 (super doDialog: &rest))
		(if (== monsterNum 8)
			(if (!= temp0 -999) (++ local12))
			(if (== local12 3) (client setScript: manuLeave))
		else
			(= [local52_3 1] -10)
			(= [local52_3 2] -11)
		)
		(return temp0)
	)
)
