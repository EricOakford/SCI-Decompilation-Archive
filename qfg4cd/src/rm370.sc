;;; Sierra Script 1.0 - (do not remove this comment)
(script# 370)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm370 0
)

(local
	local0 =  125
	local1 =  180
	local2
	local3
	theHealFormula
	local5
	local6
)
(instance rm370 of GloryRm
	(properties
		picture 370
		west 380
		leftY 179
	)
	
	(method (init)
		(= local2
			(cond 
				((== global430 0) 1)
				((and (== gCurrentDay_8 Day) (== global430 1)) 2)
				(
				(and (== global430 1) (not (== gCurrentDay_8 Day))) 3)
				((and (== gCurrentDay_8 Day) (== global430 2)) 4)
				(
				(and (not (== gCurrentDay_8 Day)) (== global430 2)) 5)
				((and (== gCurrentDay_8 Day) (== global430 3)) 6)
				(
				(and (not (== gCurrentDay_8 Day)) (== global430 3)) 7)
				((and (== gCurrentDay_8 Day) (== global430 4)) 8)
				(
				(and (not (== gCurrentDay_8 Day)) (== global430 4)) 9)
				((and (== gCurrentDay_8 Day) (== global430 5)) 10)
				(
				(and (not (== gCurrentDay_8 Day)) (== global430 5)) 11)
				((and (== gCurrentDay_8 Day) (== global430 6)) 12)
				(
				(or (not (== gCurrentDay_8 Day)) (>= global430 6)) 13)
			)
		)
		(theMusic number: 370 setLoop: -1 play:)
		(ego init: normalize: setScaler: Scaler 122 50 189 87)
		(ego posn: -15 180)
		(onceIncidental init: posn: 1000 1000)
		(seqIncidental init: posn: 1000 1000)
		(cranium init:)
		(cond 
			((OneOf local2 4 7 8 10)
				(cranium posn: 111 171)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								244
								184
								244
								164
								177
								180
								87
								180
								87
								175
								60
								175
								-300
								175
								-300
								-300
								619
								-300
								619
								489
								-300
								489
								-300
								184
							yourself:
						)
				)
			)
			((== local2 9)
				(cranium posn: 111 171 loop: 1)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								244
								184
								244
								164
								177
								180
								87
								180
								87
								175
								60
								175
								-300
								175
								-300
								-300
								619
								-300
								619
								489
								-300
								489
								-300
								184
							yourself:
						)
				)
			)
			(else
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								244
								184
								244
								165
								177
								180
								66
								180
								66
								174
								110
								161
								139
								161
								139
								157
								110
								157
								38
								175
								-300
								175
								-300
								-300
								619
								-300
								619
								489
								-300
								489
								-300
								184
							yourself:
						)
				)
			)
		)
		(slab
			init:
			cel: (if (OneOf local2 4 7 8 9 10) 1 else 0)
		)
		(fire setCycle: Fwd init:)
		(table init: approachVerbs: 4)
		(skull init: approachVerbs: 4)
		(books init: approachVerbs: 4)
		(rayGun init: approachVerbs: 4)
		(boiler init: approachVerbs: 4)
		(boilerChain init: approachVerbs: 4)
		(eyeBeaker init: approachVerbs: 4)
		(hearth init: approachVerbs: 4)
		(tower init: approachVerbs: 4)
		(bottles init: approachVerbs: 4)
		(experiment init: approachVerbs: 4)
		(globes init: approachVerbs: 4)
		(still init: approachVerbs: 4)
		(gargoyle init: approachVerbs: 4)
		(super init: &rest)
		(onceIncidental setScript: onceAnim)
		(seqIncidental setScript: seqAnim)
		(self setScript: sEnterScr)
	)
	
	(method (dispose)
		(if cuees (cuees dispose:) (= cuees 0))
		(super dispose:)
	)
	
	(method (newRoom)
		(theMusic fade: 0)
		(super newRoom: &rest)
	)
	
	(method (leaveRoom)
		(if (OneOf local2 1 3 5 7 9 11 13)
			(++ global430)
			(= gCurrentDay_8 Day)
		)
		(switch local2
			(1 (messager say: 1 6 3))
			(3 (messager say: 1 6 12))
			(5 (messager say: 1 6 13))
			(7 (messager say: 1 6 14))
			(9 (messager say: 1 6 15))
			(11 (messager say: 1 6 16))
			(12 (DailyMsg 1 6 17))
			(else  (DailyMsg 1 6 19))
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
				(if (OneOf local2 4 7 8 9 10) (= local0 89))
				(ego setMotion: MoveTo local0 local1 self)
			)
			(2
				(heroTeller
					init:
						ego
						370
						24
						128
						(switch local2
							(1 16)
							(2 16)
							(3 18)
							(4 18)
							(5 19)
							(6 19)
							(7 20)
							(8 20)
							(9 21)
							(10 21)
							(11 22)
							(12 22)
							(13 23)
						)
				)
				(if (== local2 9)
					(= cycles 1)
				else
					(cranium setCycle: End self)
				)
			)
			(3
				(switch local2
					(1
						(ego solvePuzzle: 410 6)
						(messager say: 1 6 2 0 self)
					)
					(3 (messager say: 1 6 7 0 self))
					(5 (messager say: 1 6 8 0 self))
					(7 (messager say: 1 6 9 0 self))
					(9
						(messager say: 1 6 10 0 self)
					)
					(11
						(messager say: 1 6 11 0 self)
					)
					(13 (DailyMsg 1 6 18 self))
					(else  (DailyMsg 1 6 1 self))
				)
			)
			(4
				(switch local2
					(3
						(client setScript: sCraniumZapped)
					)
					(7
						(client setScript: sFrankieAlive)
					)
					(9
						(client setScript: sFrankieReveal)
					)
					(else  (= cycles 1))
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCraniumZapped of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 1)
			)
			(1
				(theMusic2 number: 371 play:)
				(craniumZap init: setCycle: CT 13 1 self)
			)
			(2
				(++ local3)
				(craniumZap setCycle: CT 9 -1 self)
			)
			(3
				(craniumZap setCycle: CT 13 1 self)
			)
			(4
				(if (not (>= local3 3)) (= state (- state 3)))
				(= cycles 1)
			)
			(5
				(craniumZap hide:)
				(= seconds 3)
			)
			(6
				(messager say: 1 6 63 0 self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFrankieAlive of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 10)
			)
			(1
				(handWave init: setCycle: Fwd)
				(= cycles 72)
			)
			(2
				(messager say: 1 6 64 0 self)
			)
			(3 (= cycles 10))
			(4
				(handWave hide:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFrankieReveal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cranium
					posn: 166 148
					loop: 7
					cel: 0
					setPri: 172
					setCycle: End self
				)
				(slab hide:)
			)
			(1 (messager say: 1 6 6 0 self))
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance onceAnim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0 (= cycles (Random 1 120)))
			(1
				1
				(= state (Random 1 5))
				(= cycles 1)
			)
			(2
				2
				(= register (+ register 1))
				(onceIncidental
					view: 370
					loop: 0
					cel: 0
					posn: 212 188
					cycleSpeed: 6
					setPri: 200
				)
				(= state 6)
				(= cycles 1)
			)
			(3
				3
				(= register (+ register 1))
				(onceIncidental
					view: 370
					loop: 1
					cel: 0
					posn: 212 188
					cycleSpeed: 8
					setPri: 200
				)
				(= state 6)
				(= cycles 1)
			)
			(4
				4
				(= register (+ register 1))
				(onceIncidental
					view: 370
					loop: 2
					cel: 1
					posn: 307 124
					cycleSpeed: 6
				)
				(= state 6)
				(= seconds 2)
			)
			(5
				5
				(= register (+ register 2))
				(onceIncidental
					view: 370
					loop: 6
					cel: 0
					posn: 248 134
					cycleSpeed: 8
					setPri: 100
				)
				(= state 6)
				(= cycles 1)
			)
			(6
				6
				(= register (+ register 2))
				(onceIncidental
					view: 377
					loop: 0
					cel: 0
					posn: 49 169
					cycleSpeed: 6
					setPri: 200
				)
				(= state 6)
				(= cycles 1)
			)
			(7
				7
				(if (>= register 3)
					(= register 0)
					(onceIncidental setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(8
				8
				(onceIncidental setPri: -1 posn: 1000 1000)
				(self changeState: 0)
			)
		)
	)
)

(instance seqAnim of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0 (= seconds (Random 1 3)))
			(1
				1
				(= state
					(switch (Random 1 5)
						(1 1)
						(2 4)
						(3 7)
						(4 9)
						(5 12)
					)
				)
				(= cycles 1)
			)
			(2
				2
				(seqIncidental
					view: 377
					loop: 4
					cel: 0
					posn: 15 58
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(3
				3
				(seqIncidental
					view: 377
					loop: 5
					cel: 0
					posn: 15 58
					setCycle: End self
				)
			)
			(4
				4
				(seqIncidental
					plane: 377
					loop: 6
					cel: 0
					posn: 22 2
					setCycle: End self
				)
			)
			(5
				5
				(seqIncidental
					view: 370
					loop: 3
					cel: 0
					posn: 305 50
					setCycle: End self
				)
			)
			(6
				6
				(seqIncidental
					view: 370
					loop: 4
					cel: 0
					posn: 304 24
					setCycle: End self
				)
			)
			(7
				(seqIncidental
					plane: 370
					loop: 5
					cel: 0
					posn: 278 7
					setCycle: End self
				)
			)
			(8
				(seqIncidental
					view: 377
					loop: 2
					cel: 0
					posn: 219 119
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(9
				9
				(seqIncidental
					plane: 377
					loop: 3
					cel: 0
					posn: 219 119
					setCycle: End self
				)
			)
			(10
				10
				(seqIncidental
					view: 372
					loop: 0
					cel: 0
					posn: 74 74
					setPri: 200
					setCycle: End self
				)
			)
			(11
				11
				(seqIncidental
					view: 372
					loop: 1
					cel: 0
					posn: 74 74
					setCycle: End self
				)
			)
			(12
				12
				(seqIncidental
					plane: 372
					loop: 2
					cel: 0
					posn: 74 74
					setCycle: End self
				)
			)
			(13
				13
				(seqIncidental
					view: 373
					loop: 0
					cel: 0
					posn: 107 34
					setCycle: End self
				)
			)
			(14
				(seqIncidental
					plane: 373
					loop: 1
					cel: 0
					posn: 107 34
					setCycle: End self
				)
			)
			(15
				15
				(seqIncidental posn: 1000 1000 setPri: -1 cycleSpeed: 6)
				(self changeState: 0)
			)
		)
	)
)

(instance heroTeller of Teller
	(properties)
)

(instance craniumTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 69 0))
	)
	
	(method (sayMessage)
		(switch iconValue
			(23
				(ego learn: 40 100 solvePuzzle: 498 2 2)
				(Bset 498)
			)
			(44
				(Bset 184)
				(= gCurrentDay_9 Day)
				(= theHealFormula healFormula)
			)
			(46
				(ego get: 3 1)
				(= gCurrentDay_9 Day)
			)
			(47
				(= gCurrentDay_9 Day)
				(= theHealFormula healFormula)
			)
			(48
				(Bset 197)
				(= gCurrentDay_10 Day)
				(= theHealFormula cureFormula)
			)
			(50
				(ego get: 2 1)
				(= gCurrentDay_10 Day)
			)
			(51
				(= gCurrentDay_10 Day)
				(= theHealFormula cureFormula)
			)
			(53
				(= theHealFormula rehydrateFormula)
			)
			(52
				(= theHealFormula rehydrateFormula)
			)
			(58 (ego get: 9))
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				99
				(not (Btst 45))
				23
				(if [egoStats 12] (not [egoStats 40]) else 0)
				44
				(not (Btst 184))
				46
				(if (and (Btst 184) (Btst 193))
					(!= gCurrentDay_9 Day)
				else
					0
				)
				45
				(if (and (Btst 184) (not (Btst 193)))
					(== gCurrentDay_9 Day)
				else
					0
				)
				47
				(if (and (Btst 184) (not (Btst 193)))
					(!= Day gCurrentDay_9)
				else
					0
				)
				48
				(not (Btst 197))
				50
				(if (and (Btst 197) (Btst 194))
					(!= gCurrentDay_10 Day)
				else
					0
				)
				49
				(if (and (Btst 197) (not (Btst 194)))
					(== Day gCurrentDay_10)
				else
					0
				)
				51
				(if (and (Btst 197) (not (Btst 194)))
					(!= Day gCurrentDay_10)
				else
					0
				)
				52
				(Btst 196)
				53
				(if
					(and
						(>= local2 5)
						(not (Btst 198))
						(not (ego has: 32))
						(not ((inventory at: 32) chestAmout?))
						(not (Btst 440))
						(not (Btst 196))
					)
					(not (Btst 195))
				else
					0
				)
				54
				(if (Btst 195) (not (Btst 198)) else 0)
		)
	)
	
	(method (cue)
		(if (not (curRoom script?))
			(if (OneOf iconValue 44 47 48 51 53 52)
				(talker hide:)
				(curRoom setScript: delayMsg)
			else
				(super cue:)
			)
		else
			(= theHealFormula 0)
			((curRoom script?) dispose:)
			(DisposeScript 88)
			(super cue:)
		)
	)
)

(instance delayMsg of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
				((ScriptID 88 0) init: theHealFormula show: dispose:)
					(= register 1)
				else
					(= register 0)
				)
				((craniumTeller talker?) showAgain:)
				(= cycles 1)
			)
			(1
				(if register
					(cond 
						((OneOf (craniumTeller iconValue?) 44 47)
							(ego get: 3 1)
							(Bset 193)
							(ego solvePuzzle: 411 2)
							(messager say: 25 6 55 0 craniumTeller)
						)
						((OneOf (craniumTeller iconValue?) 48 51)
							(ego get: 2 1)
							(Bset 194)
							(ego solvePuzzle: 412 2)
							(messager say: 25 6 55 0 craniumTeller)
						)
						((OneOf (craniumTeller iconValue?) 53 52)
							(Bset 195)
							(Bclr 196)
							(ego solvePuzzle: 413 2)
							(messager say: 25 6 106 0 craniumTeller)
						)
						(else (craniumTeller cue:))
					)
				else
					(if (OneOf (craniumTeller iconValue?) 53 52)
						(Bset 196)
					)
					(messager say: 25 6 107 0 craniumTeller)
				)
			)
		)
	)
)

(instance slab of View
	(properties
		noun 2
		x 166
		y 148
		priority 172
		fixPriority 1
		view 374
		signal $4000
	)
	
	(method (doVerb theVerb)
		(table doVerb: theVerb)
	)
)

(instance handWave of Prop
	(properties
		noun 2
		x 166
		y 148
		priority 174
		fixPriority 1
		view 374
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(table doVerb: theVerb)
	)
)

(instance cranium of Prop
	(properties
		noun 1
		x 165
		y 164
		priority 165
		fixPriority 1
		view 375
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(craniumTeller
			init:
				self
				370
				24
				158
				(switch local2
					(1 16)
					(2 16)
					(3 18)
					(4 18)
					(5 19)
					(6 19)
					(7 20)
					(8 20)
					(9 21)
					(10 21)
					(11 22)
					(12 22)
					(13 23)
				)
		)
	)
	
	(method (doVerb theVerb)
		(if (and (== loop 7) (> mouseX 119))
			(table doVerb: theVerb)
		else
			(switch theVerb
				(26
					(ego use: 11 1)
					(if (and (Btst 195) (not (Btst 198)))
						(Bset 198)
						(ego get: 32)
						(messager say: 1 26 54)
					else
						(messager say: 1 26 56)
					)
				)
				(24
					(ego use: 9 1)
					(super doVerb: theVerb &rest)
				)
				(else 
					(super doVerb: theVerb &rest)
				)
			)
		)
	)
)

(instance craniumZap of Prop
	(properties
		x 171
		y 137
		priority 166
		fixPriority 1
		view 378
		signal $4001
	)
)

(instance onceIncidental of Prop
	(properties
		fixPriority 1
		view 370
		signal $4001
	)
)

(instance seqIncidental of Prop
	(properties
		fixPriority 1
		view 370
		signal $4001
	)
)

(instance fire of Prop
	(properties
		noun 9
		x 95
		y 157
		fixPriority 1
		view 370
		loop 7
		signal $4001
		cycleSpeed 10
		detailLevel 2
	)
)

(instance table of Feature
	(properties
		noun 2
		nsLeft 124
		nsTop 134
		nsRight 179
		nsBottom 173
		sightAngle 180
		x 151
		y 173
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 1) (== (slab cel?) 1))
			(messager say: 2 1 89)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance skull of Feature
	(properties
		noun 3
		nsLeft 205
		nsTop 166
		nsRight 250
		nsBottom 189
		sightAngle 180
		x 227
		y 189
	)
)

(instance books of Feature
	(properties
		noun 4
		nsLeft 228
		nsTop 145
		nsRight 289
		nsBottom 189
		sightAngle 180
		x 258
		y 189
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (& msgType $0001)
					(= local5 1)
				else
					(= msgType (| msgType $0001))
				)
				(if (& msgType $0002)
					(= msgType (^ msgType $0002))
					(= local6 1)
				)
				(DailyMsg 4 1 0 0)
				(if (not local5) (= msgType (^ msgType $0001)))
				(if local6 (= msgType (| msgType $0002)))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rayGun of Feature
	(properties
		noun 5
		nsLeft 84
		nsTop 5
		nsRight 148
		nsBottom 89
		sightAngle 180
		x 116
		y 89
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						100
						30
						116
						28
						127
						31
						135
						43
						147
						89
						139
						91
						122
						70
						111
						67
						107
						57
						94
						51
						91
						40
						94
						35
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance boiler of Feature
	(properties
		noun 6
		nsLeft 197
		nsTop 8
		nsRight 251
		nsBottom 70
		sightAngle 180
		x 224
		y 70
	)
)

(instance boilerChain of Feature
	(properties
		noun 7
		nsLeft 191
		nsTop 29
		nsRight 204
		nsBottom 106
		sightAngle 180
		x 197
		y 106
	)
)

(instance eyeBeaker of Feature
	(properties
		noun 8
		nsLeft 291
		nsTop 95
		nsRight 319
		nsBottom 146
		sightAngle 180
		x 305
		y 146
	)
)

(instance hearth of Feature
	(properties
		noun 9
		nsLeft 70
		nsTop 119
		nsRight 92
		nsBottom 161
		sightAngle 180
		x 81
		y 161
	)
)

(instance tower of Feature
	(properties
		nsLeft 35
		nsTop 46
		nsRight 71
		nsBottom 148
		sightAngle 180
		x 53
		y 148
	)
)

(instance bottles of Feature
	(properties
		noun 11
		nsLeft 155
		nsTop 80
		nsRight 185
		nsBottom 93
		sightAngle 180
		x 170
		y 93
	)
)

(instance experiment of Feature
	(properties
		noun 12
		nsLeft 209
		nsTop 87
		nsRight 285
		nsBottom 136
		sightAngle 180
		x 247
		y 136
	)
)

(instance globes of Feature
	(properties
		noun 13
		nsLeft 262
		nsRight 319
		nsBottom 73
		sightAngle 180
		x 290
		y 73
	)
)

(instance still of Feature
	(properties
		noun 14
		nsLeft 2
		nsRight 115
		nsBottom 189
		sightAngle 180
		x 58
		y 189
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						0
						0
						40
						0
						39
						11
						17
						31
						19
						55
						28
						65
						33
						132
						82
						160
						81
						154
						91
						152
						102
						175
						110
						178
						117
						189
						0
						189
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance gargoyle of Feature
	(properties
		noun 15
		nsLeft 147
		nsTop 102
		nsRight 190
		nsBottom 122
		sightAngle 180
		x 168
		y 122
	)
)
