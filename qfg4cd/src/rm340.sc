;;; Sierra Script 1.0 - (do not remove this comment)
(script# 340)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm340 0
)

(local
	local0
	local1
	local2
	local3
	gTheObj_2CycleSpeed
	theSafeTeller
	local6
	local7
)
(instance rm340 of GloryRm
	(properties
		picture 340
	)
	
	(method (init)
		(= local3
			(cond 
				((not (Btst 306)) 1)
				((not (Btst 254)) 2)
				((not (Btst 307)) 3)
				((not (Btst 244)) 4)
				((Btst 244) 5)
				(else 0)
			)
		)
		(ego
			init:
			normalize:
			setScale: 0
			setPri: 130
			view: 341
			setLoop: 5
			cel: 6
			x: 40
			y: 136
		)
		(theMusic number: 340 setLoop: -1 play:)
		(if (and (not (Btst 244)) (OneOf local3 3 4))
			(chiefThief init: setPri: 53 approachVerbs: 4 2 37 36 66)
		)
		(if (== local3 3)
			(chiefThief posn: 307 117 setCycle: Fwd)
		)
		(if (Btst 241) (book init: approachVerbs: 4 1))
		(leftDoor
			init:
			x: (if (Btst 243) 17 else 48)
			setPri: 20
			approachVerbs: 4 41
		)
		(rightDoor
			init:
			x: (if (Btst 243) 109 else 83)
			setPri: 20
			approachVerbs: 4 41
		)
		(torch1 init: setPri: 55 setCycle: Fwd)
		(torch2 init: setPri: 55 setCycle: Fwd)
		(barrel init: setPri: 120 setLoop: 2 1 approachVerbs: 4)
		(if (Btst 254)
			(secretPassage init: approachVerbs: 4)
		else
			(secretDoor init: setPri: 64)
		)
		(frame1 init: approachVerbs: 4 42 28)
		(frame2 init: setPri: 127 approachVerbs: 4 42 28)
		(mainFrame init: setPri: 120 approachVerbs: 4)
		(if (== local3 2)
			(= local7
				(cond 
					((not (Btst 243)) 1)
					((not (Btst 242)) 2)
					((not (Btst 248)) 3)
					((not (Btst 512)) 4)
					((not (Btst 513)) 5)
					(else 0)
				)
			)
			(note init: setPri: 121 approachVerbs: 4)
		)
		(chair init: approachVerbs: 4)
		(desk init: approachVerbs: 4 28)
		(safe init: approachVerbs: 4)
		(stone init: approachVerbs: 4 1)
		(ladder init: approachVerbs: 4)
		(sconce1 init: approachVerbs: 4)
		(sconce2 init: approachVerbs: 4)
		(bookshelf init: approachVerbs: 4 1)
		(floorGrate init: approachVerbs: 4)
		(drain init: approachVerbs: 4)
		(steps1 init: approachVerbs: 4)
		(steps2 init: approachVerbs: 4)
		(barrels1 init: approachVerbs: 4)
		(barrels2 init: approachVerbs: 4)
		(crack1 init: approachVerbs: 4)
		(crack2 init: approachVerbs: 4)
		(pillar init: approachVerbs: 4)
		(theDoits add: steps1)
		(theDoits add: steps2)
		(cond 
			((not (Btst 243))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								117
								134
								69
								134
								69
								137
								38
								137
								26
								149
								65
								149
								65
								171
								199
								157
								225
								176
								208
								183
								208
								189
								319
								189
								319
								163
								282
								153
								260
								158
								238
								154
								280
								131
								314
								137
								314
								128
								273
								128
								249
								124
								249
								122
								227
								122
								260
								128
								223
								153
								211
								148
								211
								136
								192
								130
								117
								130
							yourself:
						)
				)
			)
			((not (Btst 254))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								117
								134
								91
								134
								91
								112
								95
								112
								95
								110
								88
								110
								88
								103
								52
								103
								52
								112
								69
								112
								69
								137
								38
								137
								26
								149
								65
								149
								65
								171
								206
								156
								227
								177
								208
								183
								208
								189
								319
								189
								319
								163
								282
								153
								260
								158
								238
								154
								280
								131
								314
								137
								314
								128
								273
								128
								249
								124
								249
								122
								227
								122
								260
								128
								223
								153
								211
								148
								211
								136
								192
								130
								117
								130
							yourself:
						)
				)
			)
			(else
				(secritExit init:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								117
								134
								91
								134
								91
								112
								95
								112
								95
								110
								88
								110
								88
								103
								52
								103
								52
								112
								69
								112
								69
								137
								38
								137
								26
								149
								65
								149
								65
								171
								206
								156
								227
								177
								208
								183
								208
								189
								319
								189
								319
								163
								282
								153
								260
								158
								238
								154
								280
								131
								314
								137
								314
								128
								273
								128
								273
								124
								319
								124
								319
								122
								227
								122
								260
								128
								223
								153
								211
								148
								211
								136
								192
								130
								117
								130
							yourself:
						)
				)
			)
		)
		(super init: &rest)
		(if (OneOf local3 3 4)
			(heroTeller
				init: ego 340 35 128
				(switch local3
					(3 33)
					(4 34)
				)
			)
		)
		(self setScript: sEnterScr)
		(theGame save: 1)
	)
	
	(method (dispose)
		(theDoits delete: steps1)
		(theDoits delete: steps2)
		(DisposeScript 341)
		(super dispose: &rest)
	)
	
	(method (newRoom n)
		(theMusic fade: 0)
		(super newRoom: n &rest)
	)
	
	(method (notify)
		(messager say: 0 8 0)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego cycleSpeed: 9 setCycle: Beg self)
			)
			(1
				(ego
					x: 43
					y: 137
					normalize: 1
					cycleSpeed: gTheObj_2CycleSpeed
					setScaler: Scaler 135 77 189 103
					setHeading: 90 self
				)
			)
			(2
				(ego setMotion: MoveTo 68 144 self)
			)
			(3
				(if (== local3 1)
					(Bset 306)
					(ego solvePuzzle: 508 6 4)
					(Bset 508)
				)
				(= cycles 1)
			)
			(4
				(if (== local3 1)
					(messager say: 32 6 62 0 self)
				else
					(= cycles 1)
				)
			)
			(5
				(if (and (== heroType 3) (== local3 1))
					(messager say: 32 6 61 0 self)
				else
					(= cycles 1)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoorSlide of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 31
					loop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(1
				(if (== (leftDoor x?) 17)
					(Bclr 243)
					(= local0 48)
					(= local1 83)
				else
					(Bset 243)
					(= local0 17)
					(= local1 109)
				)
				(leftDoor
					setLoop:
					setCel: 0
					setMotion: MoveTo local0 120 self
				)
				(rightDoor
					setLoop:
					setCel: 1
					setMotion: MoveTo local1 120 self
				)
				(ego setCycle: Beg self)
			)
			(2 0)
			(3 0)
			(4
				(ego cycleSpeed: gTheObj_2CycleSpeed normalize: 7)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								117
								134
								91
								134
								91
								112
								95
								112
								95
								110
								88
								110
								88
								103
								52
								103
								52
								112
								69
								112
								69
								137
								38
								137
								26
								149
								65
								149
								65
								171
								206
								156
								227
								177
								208
								183
								208
								189
								319
								189
								319
								163
								282
								153
								260
								158
								238
								154
								280
								131
								314
								137
								314
								128
								273
								128
								249
								124
								249
								122
								227
								122
								260
								128
								223
								153
								211
								148
								211
								136
								192
								130
								117
								130
							yourself:
						)
				)
				(= cycles 2)
			)
			(5
				(messager say: 39 41 110 0 self)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetOffStairs of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					(register
						(if (OneOf (ego loop?) 7 3 6 0)
							(ego setMotion: MoveTo 270 129 self)
						else
							(ego setMotion: MoveTo 231 154 self)
						)
					)
					((and (OneOf (ego loop?) 6 3 7 1) (Btst 509)) (ego setMotion: MoveTo 79 111 self))
					(else (ego setMotion: MoveTo 80 136 self))
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenDesk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 310 self)
			)
			(1
				(messager say: 12 28 25 0 self)
			)
			(2
				(drawer init: setPri: 174 setCycle: End self)
			)
			(3
				(if (Btst 247)
					(= cycles 1)
				else
					(self setScript: sPoisonTrap self)
				)
			)
			(4
				(book init: approachVerbs: 4 1)
				(= local6 1)
				(Bset 241)
				(ego
					solvePuzzle: 512 2 4
					get: 24
					get: 5 3
					drop: 13
					useSkill: 9 400
				)
				(Bset 512)
				(messager say: 12 28 24 0 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sPoisonTrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gasTrap init: setPri: 200 setCycle: End self)
			)
			(1
				(Bset 14)
				(= poisonLevel (+ poisonLevel 20))
				(gasTrap loop: 2 setCycle: End self)
			)
			(2
				(messager say: 32 6 106 0 self)
			)
			(3
				(if (ego takeDamage: 30)
					(gasTrap hide:)
					(= cycles 1)
				else
					(EgoDead 3 0 974 1 912)
				)
			)
			(4 (self dispose:))
		)
	)
)

(instance sBarrelMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(if (and (== (barrel x?) 116) (not (Btst 254)))
					(self changeState: 6)
				else
					(= cycles 1)
				)
			)
			(1
				1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 4
					loop: (if register 1 else 0)
					cel: 0
					x: 153
					y: 130
					cycleSpeed: 8
					setCycle: CT 2 1 self
				)
			)
			(2
				2
				(if register
					(messager say: 6 4 18 0 self)
				else
					(messager say: 6 4 19 0 self)
				)
			)
			(3
				3
				(barrel setLoop:)
				(if register
					(barrel setMotion: MoveTo 132 148 self)
				else
					(barrel setMotion: MoveTo 116 148 self)
				)
			)
			(4 4 (ego setCycle: Beg self))
			(5
				5
				(ego
					normalize:
					cycleSpeed: gTheObj_2CycleSpeed
					posn: (if register 149 else 159) 131
				)
				(if register (= state 11))
				(= cycles 1)
			)
			(6
				(messager say: 28 6 14 0 self)
			)
			(7
				7
				(theGame handsOn:)
				(if ((ScriptID 341 0) init: show: dispose:)
					(= cycles 1)
				else
					(self changeState: 12)
				)
			)
			(8
				8
				(theGame handsOff:)
				(Bset 254)
				(ego solvePuzzle: 513 2 4)
				(Bset 513)
				(chiefThief
					init:
					setPri: 53
					setLoop: 0 1
					setCycle: Fwd
					approachVerbs: 4 2 37 36 66
				)
				(heroTeller init: ego 340 35 128 1)
				(secretDoor setMotion: MoveTo 236 52 self)
			)
			(9
				9
				(secretDoor setMotion: MoveTo 294 60 self)
			)
			(10
				10
				(secretDoor dispose:)
				(secretPassage init:)
				(chiefThief setMotion: MoveTo 307 117 self)
			)
			(11
				11
				(messager say: 10 6 21 0 self)
			)
			(12
				12
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDisarmTrap of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(Bclr 182)
				(if (== (CueObj client?) frame1)
					(messager say: 35 175 50 0 self)
				else
					(messager say: 35 175 50 0 self)
				)
			)
			(1
				1
				(theGame handsOn:)
				(if ((ScriptID 648 0) init: show: dispose:)
					(DisposeScript 648)
					(if (== (CueObj client?) frame1)
						(Bset 308)
					else
						(Bset 309)
					)
					(= state 3)
					(messager say: 32 6 108 0 self)
				else
					(DisposeScript 648)
					(if (Btst 182)
						(explosion init: setPri: 128 setCycle: End self)
					else
						(self changeState: 4)
					)
				)
			)
			(2
				2
				(theGame handsOff:)
				(explosion dispose:)
				(messager say: 16 4 3 0 self)
			)
			(3
				3
				(if (ego takeDamage: 5)
					(= cycles 1)
				else
					(EgoDead 3 0 974 1 912)
				)
			)
			(4
				4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenSafe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(if
					(or
						(and (== (CueObj client?) frame1) (Btst 308))
						(and (== (CueObj client?) frame2) (Btst 309))
					)
					(self changeState: 3)
				else
					(explosion init: setPri: 128 setCycle: End self)
				)
			)
			(1
				1
				(explosion dispose:)
				(messager say: 16 4 3 0 self)
			)
			(2
				2
				(if (not (ego takeDamage: 30))
					(EgoDead 3 0 974 1 912)
				else
					(= cycles 1)
				)
			)
			(3
				3
				(if (== (CueObj client?) frame1)
					(Bset 366)
					(= global154 (+ global154 6))
					(ego get: 5 get: 2 useSkill: 250)
					(messager say: 16 42 25 0 self)
				else
					(= global154 (+ global154 5))
					(Bset 367)
					(ego get: 5 get: 3 useSkill: 250)
					(messager say: 17 42 25 0 self)
				)
			)
			(4
				4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoSafe of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== register 3)
					(ego setMotion: PolyPath 63 103 self)
				else
					(= cycles 1)
				)
			)
			(1
				(switch register
					(1 (= cycles 1))
					(2
						(messager say: 22 4 45 0 self)
					)
					(3
						(ego use: 46)
						(Bset 240)
						(messager say: 22 1 47 0 self)
					)
				)
			)
			(2
				(theGame handsOn:)
				(self setScript: (ScriptID 89 0) self)
			)
			(3
				(theGame handsOff:)
				(if (Btst 248)
					(ego get: 5 5 get: 13 useSkill: 400)
					(messager say: 22 4 46 0 self)
				else
					(= cycles 2)
				)
			)
			(4
				(DisposeScript 89)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sChangeThief of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ego actions?) dispose:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				((chiefThief actions?) dispose:)
				(chiefThief x: 284 y: 117 setLoop: 2 1 setCycle: End self)
			)
			(1
				(chiefThief view: 345 loop: 0 cel: 0 posn: 280 119)
				(secritExit init:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								117
								134
								91
								134
								91
								112
								95
								112
								95
								110
								88
								110
								88
								103
								52
								103
								52
								112
								69
								112
								69
								137
								38
								137
								26
								149
								65
								149
								65
								171
								206
								156
								227
								177
								208
								183
								208
								189
								319
								189
								319
								163
								282
								153
								260
								158
								238
								154
								280
								131
								314
								137
								314
								128
								273
								128
								273
								124
								319
								124
								319
								122
								227
								122
								260
								128
								223
								153
								211
								148
								211
								136
								192
								130
								117
								130
							yourself:
						)
				)
				(heroTeller init: ego 340 35 128 5)
				(chiefTeller init: chiefThief 340 35 174 34)
				(ego use: 43 solvePuzzle: 514 2 4)
				(Bset 514)
				(Bset 307)
				(= cycles 2)
			)
			(2
				(messager say: 10 6 15 0 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClawEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(chiefThief setLoop: 1 1 setCycle: End self)
			)
			(1
				(ego view: 43 loop: 2 cel: 0 setCycle: End self)
			)
			(2 (EgoDead 1 0 960 1 912))
		)
	)
)

(instance sKillChief of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 244)
				(= [egoStats 14] 0)
				(if (ego actions?) ((ego actions?) dispose:))
				(if (chiefThief actions?)
					((chiefThief actions?) dispose:)
				)
				(chiefThief setLoop: 1 1 setCycle: End self)
			)
			(1
				(messager say: 32 6 64 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetKnob of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setHeading: 90 self)
			)
			(1
				(ego view: 4 setLoop: 0 1 cel: 0 setCycle: End self)
			)
			(2
				(if
					(or
						(ego has: 46)
						(Btst 240)
						((inventory at: 46) chestAmout?)
					)
					(messager say: 15 4 27 0 self)
				else
					(ego get: 46)
					(messager say: 15 4 28 0 self)
				)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLookMark of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(= cycles 1)
				else
					(ego setHeading: 90 self)
				)
			)
			(1
				(if register
					(= cycles 1)
				else
					(ego
						view: 4
						setLoop: (if register 1 else 0) 1
						cel: 0
						setCycle: End self
					)
				)
			)
			(2
				(if register
					(= cycles 1)
				else
					(messager say: 14 4 0 0 self)
				)
			)
			(3
				(= register
					(if register
						(safeMark
							init:
							posn: 59 90
							moveSpeed: 0
							setStep: 16 16
							setLoop: 0 1
							setPri: 196
							setScaler: Scaler 5 100 90 3
							setMotion: MoveTo 16 77 self
							yourself:
						)
					else
						(grateMark
							init:
							posn: 302 142
							moveSpeed: 0
							setStep: 16 16
							setLoop: 1 1
							setPri: 196
							setScaler: Scaler 5 100 143 7
							setMotion: MoveTo 62 7 self
							yourself:
						)
					)
				)
			)
			(4
				(if (== register safeMark)
					(safeMark setMotion: MoveTo 128 3 self)
				else
					(= cycles 1)
				)
			)
			(5
				(keyDownHandler addToFront: register)
				(mouseDownHandler addToFront: register)
				(theGame handsOn:)
				(Bset 50)
			)
			(6
				(mouseDownHandler delete: register)
				(keyDownHandler delete: register)
				(Bclr 50)
				(theGame handsOff:)
				(if (== register safeMark)
					(safeMark setMotion: MoveTo 16 77 self)
				else
					(grateMark setMotion: MoveTo 302 142 self)
				)
			)
			(7
				(if (== register safeMark)
					(safeMark setMotion: MoveTo 59 90 self)
				else
					(= cycles 1)
				)
			)
			(8
				(register dispose:)
				(if (== register safeMark)
					(= cycles 1)
				else
					(ego setCycle: Beg self)
				)
			)
			(9
				(= register 0)
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 341
					setLoop: 5 1
					cel: 0
					x: 40
					y: 136
					cycleSpeed: 9
					setPri: 130
					setScale: 0
					setCycle: End self
				)
			)
			(1
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(curRoom newRoom: 350)
			)
		)
	)
)

(instance sGetCard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 31
					loop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(1
				(ego get: 23 solvePuzzle: 509 2 4)
				(Bset 509)
				(mainFrame setCel: 1)
				(= cycles 1)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego cycleSpeed: gTheObj_2CycleSpeed normalize: 7)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance posterTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(if (== iconValue 40)
			(self clean:)
			(curRoom setScript: sGetCard)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				33
				(== local7 1)
				41
				(== local7 2)
				38
				(== local7 3)
				35
				(== local7 4)
				34
				(== local7 5)
				40
				(not (Btst 509))
		)
	)
)

(instance deskTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(if (Btst 241)
			(client doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(76
				(self clean:)
				(curRoom setScript: sOpenDesk)
			)
			(75
				(Bset 247)
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
				75
				(if (and (not (Btst 247)) (not (Btst 241)))
					(Btst 242)
				else
					0
				)
				76
				(if (not (Btst 241)) (ego has: 13) else 0)
		)
	)
)

(instance safeTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== ((CueObj client?) cel?) 0)
					((CueObj client?) setCel: 1)
					(messager say: 40 4 37)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(50
				(self clean:)
				(curRoom setScript: sDisarmTrap)
			)
			(49
				(self clean:)
				(if
					(or
						(and (Btst 366) (== (CueObj client?) frame1))
						(and (Btst 367) (== (CueObj client?) frame2))
					)
					(ego useSkill: 9 100)
					(messager say: 16 4 27)
				else
					(curRoom setScript: sOpenSafe)
				)
			)
			(78
				(self clean:)
				((CueObj client?) setCel: 0)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(asm
			pushi    768
			pushi    4
			pushi    50
			pushi    1
			pushi    242
			callb    Btst,  2
			bnt      code_1f43
			pushi    #has
			pushi    1
			pushi    24
			lag      ego
			send     6
			bnt      code_1f43
			pushi    #client
			pushi    0
			class    CueObj
			send     4
			push    
			lofsa    frame1
			eq?     
			bnt      code_1f1e
			pushi    1
			pushi    308
			callb    Btst,  2
			not     
			bnt      code_1f1e
			pushi    1
			pushi    366
			callb    Btst,  2
			not     
			bt       code_1f43
code_1f1e:
			pushi    #client
			pushi    0
			class    CueObj
			send     4
			push    
			lofsa    frame2
			eq?     
			bnt      code_1f43
			pushi    1
			pushi    309
			callb    Btst,  2
			not     
			bnt      code_1f43
			pushi    1
			pushi    367
			callb    Btst,  2
			not     
code_1f43:
			push    
			pushi    49
			pushi    #has
			pushi    1
			pushi    24
			lag      ego
			send     6
			bnt      code_1f71
			pushi    #client
			pushi    0
			class    CueObj
			send     4
			push    
			lofsa    frame1
			eq?     
			bt       code_1f71
			pushi    #client
			pushi    0
			class    CueObj
			send     4
			push    
			lofsa    frame2
			eq?     
code_1f71:
			push    
			super    Teller,  12
			ret     
		)
	)
)

(instance chiefTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 103 0))
	)
	
	(method (clean)
		(super clean:)
		(if (not (Btst 307)) ((ScriptID 103 1) dispose:))
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (showCases)
		(super showCases: 100 (Btst 389) 101 (Btst 71))
	)
)

(instance secretDoor of Actor
	(properties
		sightAngle 180
		x 232
		y 54
		view 343
		signal $4001
		xStep 2
	)
)

(instance barrel of Actor
	(properties
		noun 6
		approachX 159
		approachY 131
		x 132
		y 148
		z 50
		view 340
		loop 2
		signal $4001
		moveSpeed 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((Btst 254) (messager say: 6 4 27))
					((Btst 241)
						(if (== x 116)
							(curRoom setScript: sBarrelMove 0 1)
						else
							(curRoom setScript: sBarrelMove 0 0)
						)
					)
					(else (messager say: 6 4 104))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance leftDoor of Actor
	(properties
		noun 39
		sightAngle 180
		approachX 105
		approachY 136
		x 48
		y 120
		z 70
		view 340
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 243)
					(messager say: 39 1 19)
				else
					(messager say: 39 1 109)
				)
			)
			(4
				(if (Btst 243)
					(messager say: 39 4 19)
				else
					(messager say: 39 4 109)
				)
			)
			(41
				(curRoom setScript: sDoorSlide)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rightDoor of Actor
	(properties
		noun 39
		sightAngle 180
		approachX 105
		approachY 136
		x 83
		y 120
		z 70
		view 340
		cel 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(leftDoor doVerb: theVerb &rest)
	)
)

(instance grateMark of Actor
	(properties
		x 62
		y 7
		view 346
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

(instance safeMark of Actor
	(properties
		x 128
		y 3
		view 346
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

(instance chiefThief of Actor
	(properties
		noun 10
		sightAngle 180
		approachX 258
		approachY 126
		view 347
		signal $4001
	)
	
	(method (init)
		(if (<= local3 3)
			(= view 347)
			(= x 330)
			(= y 110)
		else
			(= view 345)
			(= x 280)
			(= y 119)
		)
		(super init: &rest)
		(chiefTeller
			init:
				self
				340
				35
				174
				(cond 
					((and (<= local3 2) (not (Btst 514))) 1)
					((<= local3 2) 5)
					((== local3 3) 33)
					(else 34)
				)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1)
				(cond 
					((Btst 244) (messager say: 10 1 113))
					((Btst 514) (messager say: 10 1 20))
					(else (messager say: 10 1 8))
				)
			)
			((== theVerb 4)
				(cond 
					((Btst 244) (messager say: 10 4 113))
					((Btst 514) (messager say: 10 4 20))
					(else (messager say: 10 4 8))
				)
			)
			((== theVerb 2)
				(if (Btst 244)
					(messager say: 10 2 113)
				else
					(super doVerb: theVerb &rest)
				)
			)
			((== theVerb 66) (curRoom setScript: sChangeThief))
			((OneOf theVerb 37 36)
				(cond 
					((Btst 244) (super doVerb: theVerb &rest))
					((== view 347) (curRoom setScript: sClawEgo))
					(else (curRoom setScript: sKillChief))
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance frame1 of Prop
	(properties
		noun 16
		sightAngle 180
		x 213
		y 65
		view 340
		loop 3
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(safeTeller init: self 340 35 175)
		(= theSafeTeller safeTeller)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(28
				(if (== ((CueObj client?) cel?) 1)
					(super doVerb: theVerb &rest)
				else
					(super doVerb: 4 &rest)
				)
			)
			(1
				(if (== ((CueObj client?) cel?) 1)
					(messager say: 40 1 111)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(42
				(cond 
					((== (CueObj client?) frame1)
						(if (Btst 366)
							(messager say: 16 42 107)
						else
							(if (== ((CueObj client?) cel?) 0)
								((CueObj client?) setCel: 1)
							)
							(self setScript: sOpenSafe)
						)
					)
					((Btst 367) (messager say: 17 42 107))
					(else
						(if (== ((CueObj client?) cel?) 0)
							((CueObj client?) setCel: 1)
						)
						(self setScript: sOpenSafe)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance frame2 of Prop
	(properties
		noun 17
		sightAngle 180
		approachX 312
		approachY 132
		x 298
		y 69
		view 340
		loop 4
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(= actions theSafeTeller)
	)
)

(instance mainFrame of Prop
	(properties
		noun 20
		approachX 157
		approachY 132
		x 166
		y 99
		view 341
		signal $4001
	)
	
	(method (init)
		(if (Btst 509) (= cel 1))
		(super init: &rest)
		(posterTeller init: self 340 35 173)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((and (not (Btst 509)) (== local3 2)) (messager say: 20 1 103))
					((not (Btst 509)) (messager say: 20 1 42))
					((== local3 2) (messager say: 20 1 43))
					(else (super doVerb: theVerb &rest))
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance explosion of Prop
	(properties
		view 341
		loop 4
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(if (== (CueObj client?) frame1)
			(= x 217)
			(= y 67)
		else
			(= x 303)
			(= y 77)
		)
	)
)

(instance gasTrap of Prop
	(properties
		x 140
		y 159
		view 344
		loop 1
		signal $4001
	)
)

(instance drawer of Prop
	(properties
		x 193
		y 150
		view 344
		signal $4001
	)
)

(instance torch1 of Prop
	(properties
		noun 26
		x 34
		y 66
		view 340
		loop 1
		signal $4001
	)
)

(instance torch2 of Prop
	(properties
		noun 26
		x 118
		y 67
		view 340
		loop 1
		signal $4001
	)
)

(instance note of View
	(properties
		noun 20
		x 153
		y 60
		view 341
		loop 1
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(= actions mainFrame)
	)
)

(instance book of View
	(properties
		noun 36
		approachX 131
		approachY 164
		x 108
		y 241
		z 100
		priority 174
		fixPriority 1
		view 344
		loop 3
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 4)
			(if (not (Btst 239)) (Bset 239))
			(messager say: 36 1 0)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance chair of Feature
	(properties
		noun 9
		nsLeft 150
		nsTop 137
		nsRight 178
		nsBottom 187
		sightAngle 180
		x 164
		y 162
	)
)

(instance desk of Feature
	(properties
		noun 12
		nsLeft 101
		nsTop 138
		nsRight 205
		nsBottom 187
		sightAngle 180
		approachX 205
		approachY 188
		x 153
		y 162
	)
	
	(method (init)
		(super init: &rest)
		(deskTeller init: self 340 35 166)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 241)
					(if local6
						(messager say: 12 1 72)
					else
						(= local6 1)
						(ego get: 5 3)
						(messager say: 12 1 73)
					)
				else
					(messager say: 12 1 26)
				)
			)
			(4
				(if local6
					(messager say: 12 1 72)
				else
					(= local6 1)
					(ego get: 5 3)
					(messager say: 12 1 73)
				)
			)
			(28
				(if (Btst 241)
					(messager say: 12 28 23)
				else
					(curRoom setScript: sOpenDesk)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance safe of Feature
	(properties
		noun 22
		nsLeft 55
		nsTop 72
		nsRight 71
		nsBottom 85
		sightAngle 180
		x 63
		y 78
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((Btst 248) (messager say: 22 1 44))
					((not (Btst 240)) (messager say: 22 1 45))
					(else (messager say: 22 1 47))
				)
			)
			(4
				(cond 
					((Btst 248) (messager say: 22 4 44))
					((not (Btst 240)) (curRoom setScript: sDoSafe 0 2))
					(else (curRoom setScript: sDoSafe 0 1))
				)
			)
			(78
				(curRoom setScript: sDoSafe 0 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance stone of Feature
	(properties
		nsLeft 49
		nsTop 87
		nsRight 79
		nsBottom 100
		sightAngle 180
		approachX 80
		approachY 110
		x 64
		y 93
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(curRoom setScript: sLookMark 0 1)
			)
			(4
				(curRoom setScript: sLookMark 0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ladder of Feature
	(properties
		noun 18
		nsLeft 21
		nsTop -1
		nsRight 35
		nsBottom 136
		sightAngle 180
		approachX 43
		approachY 137
		x 28
		y 130
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sClimbLadder)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance sconce1 of Feature
	(properties
		noun 26
		nsLeft 35
		nsTop 62
		nsRight 46
		nsBottom 90
		sightAngle 180
		x 40
		y 76
	)
)

(instance sconce2 of Feature
	(properties
		noun 26
		nsLeft 117
		nsTop 61
		nsRight 129
		nsBottom 92
		sightAngle 180
		x 123
		y 76
	)
)

(instance bookshelf of Feature
	(properties
		noun 8
		nsLeft 91
		nsTop 57
		nsRight 112
		nsBottom 114
		sightAngle 180
		approachX 87
		approachY 111
		x 101
		y 85
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1)
			(if (not (Btst 242))
				(Bset 242)
				(ego solvePuzzle: 511 2 4)
			)
			(Bset 511)
			(super doVerb: theVerb &rest)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance floorGrate of Feature
	(properties
		noun 15
		nsLeft 227
		nsTop 167
		nsRight 299
		nsBottom 189
		sightAngle 180
		approachX 226
		approachY 180
		x 263
		y 178
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetKnob)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance drain of Feature
	(properties
		noun 14
		nsLeft 292
		nsTop 142
		nsRight 318
		nsBottom 157
		sightAngle 180
		approachX 274
		approachY 163
		x 305
		y 149
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sLookMark 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance steps1 of Feature
	(properties
		noun 29
		nsLeft 54
		nsTop 114
		nsRight 111
		nsBottom 133
		sightAngle 180
		x 82
		y 123
	)
	
	(method (doit)
		(if
			(and
				(self onMe: ego)
				(== (ego mover?) 0)
				(not (curRoom script?))
			)
			(curRoom setScript: sGetOffStairs 0 0)
		)
	)
)

(instance steps2 of Feature
	(properties
		noun 29
		nsLeft 227
		nsTop 133
		nsRight 277
		nsBottom 151
		sightAngle 180
		x 252
		y 142
	)
	
	(method (doit)
		(if
			(and
				(self onMe: ego)
				(== (ego mover?) 0)
				(not (curRoom script?))
			)
			(curRoom setScript: sGetOffStairs 0 1)
		)
	)
)

(instance barrels1 of Feature
	(properties
		noun 7
		nsLeft 204
		nsTop 89
		nsRight 244
		nsBottom 119
		sightAngle 180
		x 224
		y 104
	)
)

(instance barrels2 of Feature
	(properties
		noun 7
		nsLeft 11
		nsTop 137
		nsRight 84
		nsBottom 188
		sightAngle 180
		x 47
		y 162
	)
)

(instance crack1 of Feature
	(properties
		noun 11
		nsLeft 34
		nsTop 10
		nsRight 62
		nsBottom 37
		sightAngle 180
		x 48
		y 23
	)
)

(instance crack2 of Feature
	(properties
		noun 11
		nsLeft 142
		nsTop 25
		nsRight 169
		nsBottom 51
		sightAngle 180
		x 155
		y 38
	)
)

(instance secretPassage of Feature
	(properties
		noun 27
		nsLeft 234
		nsTop 52
		nsRight 295
		nsBottom 123
		sightAngle 180
		x 264
		y 87
	)
)

(instance pillar of Feature
	(properties
		noun 19
		nsLeft 173
		nsTop 28
		nsRight 207
		nsBottom 119
		sightAngle 180
		x 190
		y 73
	)
)

(instance secritExit of Feature
	(properties
		nsLeft 311
		nsTop 119
		nsRight 319
		nsBottom 126
	)
	
	(method (init)
		(theDoits addToFront: self)
		(super init: &rest)
	)
	
	(method (doit)
		(if
		(and (self onMe: ego) (not (curRoom script?)))
			(curRoom newRoom: 290)
		)
	)
	
	(method (dispose)
		(theDoits delete: self)
		(super dispose: &rest)
	)
)
