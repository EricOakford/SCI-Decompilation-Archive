;;; Sierra Script 1.0 - (do not remove this comment)
(script# 350)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Array)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm350 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	gTheObj_2CycleSpeed
	local7
	local8 =  4
	local9
	local10
)
(procedure (localproc_012e param1 &tmp temp0 temp1)
	(if param1
		(if numWeights
			(= temp0 0)
			(while (< temp0 numWeights)
				(cond 
					((OneOf temp0 0 1 6 7) (= temp1 0))
					((OneOf temp0 2 3) (= temp1 1))
					(else (= temp1 2))
				)
				(local2
					at:
						temp0
						((weight new:)
							init:
							cel: temp1
							x: (local3 at: temp0)
							y: (local4 at: temp0)
							setPri: 0
							ignoreActors:
							approachVerbs: 4
							yourself:
						)
				)
				(++ temp0)
			)
		)
	else
		(= local7 0)
		(Bclr 271)
		((local2 at: (- numWeights 1)) dispose:)
		((local2 at: (- numWeights 2)) dispose:)
		(= numWeights
			(cond 
				((< exerciseSteps 20) 6)
				((< exerciseSteps 30) 4)
				((< exerciseSteps 40) 2)
				((< exerciseSteps 50) 0)
				(else 0)
			)
		)
	)
	(= local8 (- 4 (/ numWeights 2)))
)

(instance rm350 of GloryRm
	(properties
		picture 350
		east 250
		rightY 162
	)
	
	(method (init)
		(ego normalize: init:)
		(if (== prevRoomNum 340)
			(ego
				view: 352
				setLoop: 0 1
				cel: 10
				posn: 248 86
				setScale: 0
			)
		else
			(= local0 272)
			(= local1 161)
			(ego posn: 335 162 setScale: 0)
		)
		(= local2 (IDArray new: 8))
		(= local3 (IntArray with: 93 110 101 98 103 93 107 99))
		(= local4
			(IntArray with: 133 132 135 138 138 135 128 126)
		)
		(theMusic number: 350 setLoop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						319
						180
						319
						189
						0
						189
						0
						0
						319
						0
						319
						145
						288
						145
						275
						152
						163
						147
						163
						139
						147
						138
						147
						142
						83
						142
						83
						138
						5
						146
						15
						180
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 23 157 58 147 96 147 96 171 23 171
					yourself:
				)
		)
		(if (Btst 169)
			(emptyCase init: approachVerbs: 4)
		else
			(swordCase init: approachVerbs: 4)
		)
		(if (Btst 190) (rope init: approachVerbs: 4))
		(if
			(and
				(!= heroType 1)
				(not (Btst 525))
				(not (Btst 475))
				(not (Btst 507))
			)
			(ropeGrapnel init: approachVerbs: 4)
		)
		(secretDoor
			init:
			loop: (if (Btst 181) 3 else 2)
			approachVerbs: 4
		)
		(localproc_012e 1)
		(stepperRope init: approachVerbs: 4)
		(weightsOnFloor1 init: approachVerbs: 4)
		(weightsOnFloor2 init: approachVerbs: 4)
		(floorHole init: approachVerbs: 4)
		(floorHole2 init: approachVerbs: 4)
		(mooseHead init: approachVerbs: 4)
		(moose2 init: approachVerbs: 4)
		(wallCrack1 init: approachVerbs: 4)
		(wallCrack2 init: approachVerbs: 4)
		(wallCrack3 init: approachVerbs: 4)
		(bigRing init: approachVerbs: 4)
		(pulleys init: approachVerbs: 4)
		(hooks init: approachVerbs: 4)
		(pen init: approachVerbs: 4)
		(painting1 init: approachVerbs: 4)
		(painting2 init: approachVerbs: 4)
		(desk init: approachVerbs: 4)
		(book init: approachVerbs: 4)
		(stepper init: approachVerbs: 4)
		(ceilingCrack init: approachVerbs: 4)
		(ewer1 init: approachVerbs: 4)
		(ewer2 init: approachVerbs: 4)
		(bookShelf init: approachVerbs: 4)
		(tableCorner init: approachVerbs: 4)
		(pillar init: approachVerbs: 4)
		(ceilingHole init: approachVerbs: 4)
		(super init: &rest)
		(if (== prevRoomNum 340)
			(self setScript: sClimbDown)
		else
			(self setScript: sEnterScr)
		)
	)
	
	(method (dispose)
		(local2 dispose:)
		(local3 dispose:)
		(local4 dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(104 (messager say: 23 6 50))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom)
		(theMusic fade: 0)
		(super newRoom: &rest)
	)
)

(instance sGetWeights of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego view: 4 setLoop: 0 1 cel: 0 setCycle: End self)
			)
			(1
				(localproc_012e 0)
				(messager say: 26 4 15 0 self)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego normalize:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(ego setMotion: MoveTo local0 local1 self)
			)
			(2
				(if (not (Btst 438)) (ego solvePuzzle: 438 6))
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBustCase of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego trySkill: 0 300)
				(= cycles 2)
			)
			(1
				(messager say: 28 164 6 0 self)
			)
			(2
				(ego
					view: 31
					loop: 0
					cel: 0
					posn: 175 146
					setCycle: CT 2 1 self
				)
			)
			(3
				(emptyCase
					view: 350
					loop: 1
					cel: 0
					init:
					setCycle: End self
				)
				(ego setCycle: CT 1 -1 self)
			)
			(4 0)
			(5 (ego setCycle: CT 2 1 self))
			(6
				(Bset 168)
				(Bset 169)
				(emptyCase loop: 0 cel: 0)
				((= temp0 (inventory at: 19)) state: 1 loop: 0 cel: 0)
				(ego get: 19)
				(temp0 amount: 1)
				(ego setCycle: CT 0 -1 self)
			)
			(7
				(ego normalize: 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 68 140 self)
			)
			(1
				(Face ego (bigRing x?) (bigRing y?) self)
			)
			(2 (= cycles 8))
			(3
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(Bset 190)
				(ego use: 16)
				(ego
					view: 8
					loop: 0
					cel: 0
					posn: 75 140
					cycleSpeed: 8
					setCycle: CT 3 1 self
				)
			)
			(4 (= cycles 8))
			(5
				(ego view: 8 loop: 0 cel: 0 setCycle: End self)
			)
			(6
				(rope
					view: 8
					loop: 6
					cel: 0
					init:
					cycleSpeed: 8
					setCycle: End self
				)
				(ego view: 8 loop: 7 cel: 0 setCycle: End self)
			)
			(7 0)
			(8
				(rope approachVerbs: 4)
				(ego
					posn: 68 140
					normalize: 0
					cycleSpeed: gTheObj_2CycleSpeed
				)
				(= gTheObj_2CycleSpeed 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHkPuzzle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 86 0) init: show: dispose:)
				(= cycles 1)
			)
			(1
				(DisposeScript 86)
				(if (Btst 181)
					(theGame handsOff:)
					(secretDoor setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(2
				(if (Btst 181) (secretDoor loop: 3) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance sClimbBookcase of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					posn: 245 148
					setSpeed: 10
					useSkill: 11 200
					useStamina: 10
					setCycle: Walk
					setMotion: MoveTo 246 97 self
				)
			)
			(1
				(ego
					view: 352
					setLoop: -1
					loop: 0
					cel: 0
					posn: 248 86
					setSpeed: gTheObj_2CycleSpeed
					setCycle: End self
				)
			)
			(2
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(curRoom newRoom: 340)
			)
		)
	)
)

(instance sClimbRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(Bset 50)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					loop: 0
					cel: 0
					setPri: 170
					setSpeed: 10
					posn: 139 154
					setCycle: End self
				)
			)
			(1
				1
				(ego useSkill: 11 160)
				(ego useStamina: 10)
				(if (< [egoStats 11] 200) (= state 11))
				(ego loop: 1 cel: 0 posn: 139 137 setCycle: End self)
			)
			(2
				2
				(ego cel: 0 posn: 139 122 setCycle: End self)
			)
			(3
				3
				(if
				(and [egoStats 9] [egoStats 8] (not (Btst 181)))
					(if (Btst 170)
						(self changeState: 7)
					else
						(= local9 1)
						(ceilingMark
							init:
							posn: 138 25
							moveSpeed: 0
							setStep: 10 10
							setLoop: 0 1
							setScaler: Scaler 100 5 98 24
							setMotion: MoveTo 65 96 self
						)
					)
				else
					(= cycles 1)
				)
			)
			(4
				4
				(if local9
					(Bset 170)
					(theGame handsOn:)
					(narrator y: 10)
					(messager say: 24 1 22 0 self)
				else
					(messager say: 29 4 46 0 self)
				)
			)
			(5
				5
				(if local9
					(narrator y: -1)
					(keyDownHandler addToFront: ceilingMark)
					(mouseDownHandler addToFront: ceilingMark)
				else
					(= cycles 1)
				)
			)
			(6
				6
				(if local9
					(theGame handsOff:)
					(keyDownHandler delete: ceilingMark)
					(mouseDownHandler delete: ceilingMark)
					(ceilingMark setMotion: MoveTo 138 25 self)
				else
					(= cycles 1)
				)
			)
			(7
				7
				(if (and local9 (cast contains: ceilingMark))
					(= local9 0)
					(ceilingMark dispose:)
				)
				(ego setCycle: Beg self)
			)
			(8
				8
				(ego cel: 5 posn: 139 137 setCycle: Beg self)
			)
			(9
				9
				(ego cel: 5 posn: 139 154 setCycle: Beg self)
			)
			(10
				10
				(ego loop: 0 cel: 4 setCycle: Beg self)
			)
			(11
				11
				(ego normalize: 3 setSpeed: gTheObj_2CycleSpeed)
				(= gTheObj_2CycleSpeed 0)
				(theGame handsOn:)
				(Bclr 50)
				(self dispose:)
			)
			(12
				12
				(= state 7)
				(messager say: 29 4 21 0 self)
			)
		)
	)
)

(instance sLookUnderDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 157 144 self)
			)
			(1 (ego setHeading: 90 self))
			(2
				(ego
					view: 4
					loop: 0
					cel: 0
					posn: 151 144
					setPri: 30
					setCycle: End self
				)
			)
			(3
				(Bset 50)
				(deskMark
					init:
					moveSpeed: 0
					setStep: 10 10
					setLoop: 1 1
					setPri: 31
					setScaler: Scaler 5 100 121 2
					setMotion: MoveTo 154 121 self
				)
			)
			(4
				(deskMark setPri: 196 setMotion: MoveTo 65 2 self)
			)
			(5
				(keyDownHandler addToFront: deskMark)
				(mouseDownHandler addToFront: deskMark)
				(theGame handsOn:)
			)
			(6
				(theGame handsOff:)
				(keyDownHandler delete: deskMark)
				(mouseDownHandler delete: deskMark)
				(deskMark setMotion: MoveTo 154 121 self)
			)
			(7
				(Bset 172)
				(deskMark setPri: 31 setMotion: MoveTo 179 121 self)
			)
			(8 (ego setCycle: Beg self))
			(9
				(deskMark dispose:)
				(ego normalize: 0 posn: 157 144)
				(theGame handsOn:)
				(Bclr 50)
				(self dispose:)
			)
		)
	)
)

(instance sStepper of Script
	(properties)
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(switch heroType
					(3
						(if (not (Btst 527)) (ego solvePuzzle: 527 6 8))
					)
					(0
						(if (not (Btst 477)) (ego solvePuzzle: 477 2 1))
					)
				)
				(ego setPri: 162 setMotion: MoveTo 50 159 self)
			)
			(1
				1
				(theGame handsOn:)
				(cond 
					((Btst 204) (= local10 1) (messager say: 14 4 49 0 self))
					((Btst 271) (= local10 1) (messager say: 14 4 11 0 self))
					((>= exerciseSteps 50) (= local10 1) (messager say: 14 4 10 0 self))
					((< [egoStats 18] 65) (= local10 1) (messager say: 14 4 12 0 self))
					((< [egoStats 0] 200) (= local10 1) (messager say: 14 4 5 0 self))
					(
						(and
							(== (/ exerciseSteps 10) (- 5 (/ numWeights 2)))
							(not (mod exerciseSteps 10))
						)
						(= local10 1)
						(Bset 271)
						(Bset 204)
						(messager say: 14 4 49 0 self)
					)
					(else (++ local7) (= cycles 1))
				)
			)
			(2
				2
				(if local10
					(= local10 0)
					(self changeState: 6)
				else
					(= cycles 1)
				)
			)
			(3
				(ego
					view: 351
					setLoop: 3 1
					cel: 0
					posn: 50 159
					setPri: 160
					setSpeed: local7
					setCycle: Fwd
				)
				(weightsOnFloor1 cycleSpeed: local7 setCycle: Fwd)
				(weightsOnFloor2 cycleSpeed: local7 setCycle: Fwd)
				(stepperRope hide:)
				(= cycles (+ local7 10))
			)
			(4
				4
				(++ exerciseSteps)
				(ego useStamina: (+ (* local7 5) 12))
				(if (< [egoStats 0] 400) (++ [egoStats 0]))
				(ego setCycle: End self)
			)
			(5 5 (self changeState: 1))
			(6
				6
				(theGame handsOff:)
				(weightsOnFloor1 setCycle: 0)
				(weightsOnFloor2 setCycle: 0)
				(stepperRope show:)
				(ego
					normalize: 6
					setPri: 162
					setSpeed: gTheObj_2CycleSpeed
					setMotion: MoveTo 38 171 self
				)
			)
			(7
				7
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(and (OneOf (event type?) 4 32 1) (OneOf state 1 3 4))
			(self changeState: 6)
			(event claimed: 1)
			(return)
		else
			(super handleEvent: event &rest)
		)
	)
)

(instance sGetGrapnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch heroType
					(3 (ego solvePuzzle: 525 2 8))
					(0 (ego solvePuzzle: 475 2 1))
					(2 (ego solvePuzzle: 507 2 4))
				)
				(= cycles 2)
			)
			(1
				(ego
					view: 4
					loop: 1
					cel: 0
					posn: 155 142
					setCycle: End self
				)
			)
			(2
				(ropeGrapnel hide:)
				(ego get: 16 setCycle: Beg self)
			)
			(3
				(messager say: 27 4 17 0 self)
			)
			(4
				(ego normalize: posn: 149 142)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego cycleSpeed: 8 setCycle: Beg self)
			)
			(1
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					posn: 246 97
					setSpeed: 10
					setCycle: Walk
					setMotion: MoveTo 245 148 self
				)
			)
			(2
				(ego
					normalize: 3
					setSpeed: gTheObj_2CycleSpeed
					setHeading: 180 self
				)
			)
			(3
				(ego setMotion: MoveTo 245 160 self)
			)
			(4
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance swordTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(1
					(if (Btst 168)
						(messager say: 11 1 1)
						(return 1)
					else
						(messager say: 11 1 0)
						(return 1)
					)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(6
				(self clean:)
				(curRoom setScript: sBustCase)
			)
			(5
				(ego trySkill: 0 300)
				(super sayMessage: &rest)
			)
			(9
				(ego trySkill: 0 300)
				(super sayMessage: &rest)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				1
				(Btst 168)
				8
				(Btst 168)
				7
				(not (Btst 168))
				5
				(if
					(not
						(if (ego has: 19) (== ((inventory at: 19) state?) 3))
					)
					(if
						(not
							(if (ego has: 19) (== ((inventory at: 19) state?) 2))
						)
						(< [egoStats 0] 300)
					)
				else
					0
				)
				6
				(if
					(not
						(if (ego has: 19) (== ((inventory at: 19) state?) 2))
					)
					(if
						(and
							(not
								(if (ego has: 19) (== ((inventory at: 19) state?) 3))
							)
							(not (Btst 168))
						)
						(OneOf heroType 0 3)
					)
				else
					0
				)
				4
				(if
					(not
						(if (ego has: 19) (== ((inventory at: 19) state?) 2))
					)
					(if
						(not
							(if (ego has: 19) (== ((inventory at: 19) state?) 3))
						)
						(OneOf heroType 2 1)
					)
				else
					0
				)
				9
				(if
					(not
						(if (ego has: 19) (== ((inventory at: 19) state?) 3))
					)
					(if
						(not
							(if (ego has: 19) (== ((inventory at: 19) state?) 2))
						)
						(not (Btst 168))
					)
				else
					0
				)
				43
				(if
				(and (ego has: 19) (== ((inventory at: 19) state?) 2))
					(not (Btst 168))
				else
					0
				)
				47
				(if
				(and (ego has: 19) (== ((inventory at: 19) state?) 3))
					(not (Btst 168))
				else
					0
				)
		)
	)
)

(instance bookTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(24
				(self clean:)
				(curRoom setScript: sLookUnderDesk)
			)
			(25
				(Bset 171)
				(ego solvePuzzle: 439 2)
				(super sayMessage: &rest)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				24
				(if (Btst 170) (not (Btst 181)) else 0)
				25
				(not (Btst 171))
		)
	)
)

(instance shelfTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(27
				(if (Btst 181)
					(self clean:)
					(curRoom setScript: sClimbBookcase)
				else
					(super sayMessage: &rest)
				)
			)
			(2
				(Bset 174)
				(switch heroType
					(3 (ego solvePuzzle: 526 6 8))
					(0 (ego solvePuzzle: 476 2 1))
				)
				(= [egoStats 11] (+ [egoStats 11] 100))
				(super sayMessage: &rest)
			)
			(35
				(Bset 173)
				(= [egoStats 0] (+ [egoStats 0] 20))
				(super sayMessage: &rest)
			)
			(33
				(Bset 175)
				(= [egoStats 12] (+ [egoStats 12] 25))
				(super sayMessage: &rest)
			)
			(31
				(Bset 176)
				(Bset 177)
				(super sayMessage: &rest)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				27
				(Btst 172)
				37
				(Btst 173)
				36
				(Btst 174)
				38
				(Btst 175)
				35
				(if (not (Btst 173)) (OneOf heroType 0 3) else 0)
				2
				(if (OneOf heroType 0 3) (== [egoStats 11] 0) else 0)
				33
				(if [egoStats 12] (not (Btst 175)) else 0)
		)
	)
)

(instance ropeTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(19
				(self clean:)
				(ego get: 16)
				(Bclr 190)
				(rope dispose:)
			)
			(2
				(if [egoStats 11]
					(self clean:)
					(curRoom setScript: sClimbRope)
				else
					(super sayMessage: &rest)
				)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
)

(instance ceilingMark of Actor
	(properties
		x 138
		y 25
		priority 196
		fixPriority 1
		view 354
		signal $4001
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if (OneOf (event type?) 1 32 4)
			((curRoom script?) cue:)
			(event claimed: 1)
			(return 1)
		)
		(return 0)
	)
)

(instance deskMark of Actor
	(properties
		x 179
		y 121
		fixPriority 1
		view 354
		loop 1
		signal $4001
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(if (OneOf (event type?) 1 32 4)
			((curRoom script?) cue:)
			(event claimed: 1)
			(return 1)
		)
		(return 0)
	)
)

(instance stepperRope of View
	(properties
		noun 14
		approachX 38
		approachY 171
		x 53
		y 161
		priority 160
		fixPriority 1
		view 351
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sStepper)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ropeGrapnel of View
	(properties
		noun 27
		approachX 149
		approachY 142
		x 117
		y 139
		priority 38
		fixPriority 1
		view 350
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 27 1 17))
			(4
				(curRoom setScript: sGetGrapnel)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance weight of View
	(properties
		noun 26
		approachX 75
		approachY 138
		view 351
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((Btst 204) (messager say: 26 4 49))
					(
						(and
							(Btst 271)
							(> (/ exerciseSteps 10) local8)
							(not (mod exerciseSteps 10))
						)
						(curRoom setScript: sGetWeights)
					)
					(else (messager say: 26 4 10))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance emptyCase of Prop
	(properties
		noun 11
		sightAngle 180
		approachX 176
		approachY 146
		x 202
		y 111
		fixPriority 1
		view 350
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= actions (swordCase actions?))
	)
)

(instance rope of Prop
	(properties
		noun 29
		sightAngle 180
		approachX 140
		approachY 143
		x 129
		y 87
		priority 160
		fixPriority 1
		view 8
		loop 6
		cel 7
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(ropeTeller init: self 350 28 165)
	)
	
	(method (dispose)
		(ropeTeller dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 27 1 18))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance weightsOnFloor1 of Prop
	(properties
		noun 14
		approachX 38
		approachY 171
		x 59
		y 18
		priority 161
		fixPriority 1
		view 351
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sStepper)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance weightsOnFloor2 of Prop
	(properties
		noun 14
		approachX 38
		approachY 171
		x 76
		y 18
		priority 161
		fixPriority 1
		view 351
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sStepper)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance secretDoor of Prop
	(properties
		noun 12
		sightAngle 180
		approachX 245
		approachY 148
		x 251
		y 64
		view 350
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 181)
					(messager say: 12 1 48)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(4
				(if (Btst 181)
					(curRoom setScript: sClimbBookcase)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance floorHole of Feature
	(properties
		noun 2
		nsLeft 106
		nsTop 165
		nsRight 158
		nsBottom 172
		sightAngle 180
		x 132
		y 168
	)
)

(instance floorHole2 of Feature
	(properties
		noun 2
		nsLeft 21
		nsTop 180
		nsRight 138
		nsBottom 189
		sightAngle 180
		x 79
		y 184
	)
)

(instance mooseHead of Feature
	(properties
		noun 1
		nsLeft 11
		nsTop 41
		nsRight 54
		nsBottom 97
		sightAngle 180
		x 32
		y 69
	)
)

(instance moose2 of Feature
	(properties
		noun 1
		nsLeft 54
		nsTop 48
		nsRight 80
		nsBottom 65
		sightAngle 180
		x 67
		y 56
	)
)

(instance wallCrack1 of Feature
	(properties
		noun 3
		nsLeft 16
		nsTop 119
		nsRight 42
		nsBottom 134
		sightAngle 180
		x 29
		y 126
	)
)

(instance wallCrack2 of Feature
	(properties
		noun 3
		nsLeft 129
		nsTop 84
		nsRight 140
		nsBottom 109
		sightAngle 180
		x 134
		y 96
	)
)

(instance wallCrack3 of Feature
	(properties
		noun 3
		nsLeft 139
		nsTop 44
		nsRight 169
		nsBottom 70
		sightAngle 180
		x 154
		y 57
	)
)

(instance bigRing of Feature
	(properties
		noun 4
		nsLeft 130
		nsTop 30
		nsRight 154
		nsBottom 45
		sightAngle 180
		approachX 143
		approachY 159
		x 142
		y 87
		z 50
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(33
				(curRoom setScript: sThrowRope)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pulleys of Feature
	(properties
		noun 5
		nsLeft 45
		nsTop 10
		nsRight 79
		nsBottom 23
		sightAngle 180
		x 72
		y 33
	)
)

(instance hooks of Feature
	(properties
		noun 6
		nsLeft 290
		nsTop 111
		nsRight 306
		nsBottom 118
		sightAngle 180
		approachX 287
		approachY 154
		x 298
		y 114
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if [egoStats 9]
					(if (Btst 181)
						(messager say: 6 4 40)
					else
						(curRoom setScript: sHkPuzzle)
					)
				else
					(messager say: 6 4 0)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pen of Feature
	(properties
		noun 7
		nsLeft 204
		nsTop 103
		nsRight 212
		nsBottom 120
		sightAngle 180
		x 208
		y 111
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 171)
					(messager say: 7 4 44)
				else
					(Bset 171)
					(messager say: 7 4 25)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance painting1 of Feature
	(properties
		noun 9
		nsLeft 175
		nsTop 83
		nsRight 189
		nsBottom 99
		sightAngle 180
		x 182
		y 91
	)
)

(instance painting2 of Feature
	(properties
		noun 10
		nsLeft 184
		nsTop 64
		nsRight 196
		nsBottom 79
		sightAngle 180
		x 190
		y 71
	)
)

(instance swordCase of Feature
	(properties
		noun 11
		nsLeft 192
		nsTop 84
		nsRight 204
		nsBottom 110
		sightAngle 180
		approachX 176
		approachY 146
		x 198
		y 97
	)
	
	(method (init)
		(super init: &rest)
		(swordTeller init: self 350 28 164)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(171
				(cond 
					((Btst 168) (messager say: 11 171 1))
					((OneOf heroType 2 1) (messager say: 11 171 45))
					(
					(and (ego has: 19) (== ((inventory at: 19) state?) 2)) (messager say: 11 171 43))
					(
					(and (ego has: 19) (== ((inventory at: 19) state?) 3)) (messager say: 11 171 47))
					((< [egoStats 0] 300) (ego trySkill: 0 300) (messager say: 11 171 5))
					(else (curRoom setScript: sBustCase))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance desk of Feature
	(properties
		noun 13
		nsLeft 173
		nsTop 122
		nsRight 212
		nsBottom 146
		sightAngle 180
		x 192
		y 134
	)
	
	(method (init)
		(super init: &rest)
		(bookTeller init: self 350 28 166)
	)
)

(instance book of Feature
	(properties
		noun 8
		nsLeft 175
		nsTop 115
		nsRight 206
		nsBottom 122
		sightAngle 180
		x 190
		y 118
	)
	
	(method (init)
		(super init: &rest)
		(= actions bookTeller)
	)
)

(instance stepper of Feature
	(properties
		noun 14
		nsLeft 30
		nsTop 129
		nsRight 77
		nsBottom 169
		sightAngle 180
		approachX 38
		approachY 171
		x 53
		y 149
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sStepper)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ceilingCrack of Feature
	(properties
		noun 15
		nsLeft 110
		nsTop 7
		nsRight 121
		nsBottom 26
		sightAngle 180
		x 115
		y 16
	)
)

(instance ewer1 of Feature
	(properties
		noun 16
		nsLeft 224
		nsTop 161
		nsRight 294
		nsBottom 189
		sightAngle 180
		x 259
		y 175
	)
)

(instance ewer2 of Feature
	(properties
		noun 17
		nsLeft 284
		nsTop 131
		nsRight 319
		nsBottom 189
		sightAngle 180
		x 301
		y 160
	)
)

(instance bookShelf of Feature
	(properties
		noun 18
		nsLeft 210
		nsTop 63
		nsRight 290
		nsBottom 149
		sightAngle 180
		approachX 245
		approachY 148
		x 250
		y 106
	)
	
	(method (init)
		(super init: &rest)
		(shelfTeller init: self 350 28 163)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (>= local5 6) (= local5 1) else (++ local5))
				(messager say: 18 1 0 local5)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance tableCorner of Feature
	(properties
		noun 20
		nsLeft 166
		nsTop 166
		nsRight 224
		nsBottom 189
		sightAngle 180
		x 195
		y 177
	)
)

(instance pillar of Feature
	(properties
		noun 21
		nsLeft 99
		nsTop 48
		nsRight 137
		nsBottom 134
		sightAngle 180
		x 118
		y 91
	)
)

(instance ceilingHole of Feature
	(properties
		noun 22
		nsLeft 126
		nsRight 152
		nsBottom 13
		sightAngle 180
		x 139
		y 6
	)
)
