;;; Sierra Script 1.0 - (do not remove this comment)
(script# 570)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use EgoDead)
(use ForestView)
(use PolyPath)
(use Polygon)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm570 0
)

(local
	theX
	theY
	local2
	local3
	local4
	gTheObj_2CycleSpeed
	local6
	local7
)
(instance rm570 of GloryRm
	(properties
		picture 420
		horizon 70
		north 569
		east 574
		topX 174
		rightY 136
	)
	
	(method (init)
		(= local6 0)
		(= theX 49)
		(= theY 134)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						76
						179
						130
						179
						153
						167
						181
						167
						190
						160
						240
						160
						279
						142
						319
						142
						319
						189
						0
						189
						0
						0
						154
						0
						154
						72
						178
						83
						158
						103
						117
						103
						97
						96
						70
						96
						50
						86
						31
						86
						31
						93
						65
						114
						130
						114
						130
						128
						55
						128
						55
						143
						95
						143
						95
						150
						48
						154
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 0 319 131 301 131 219 115 239 105 213 98 169 105 195 79 195 0
					yourself:
				)
		)
		(stalk cel: (stalk lastCel:) init:)
		(self setRegions: 50)
		(super init: &rest)
		(curRoom
			addPoly:
				((Polygon new:)
					init: 32 132 82 111 119 111 167 128 215 113 319 150 319 189 0 189
					yourself:
				)
				60
		)
		(atp1 init: setPri: 180)
		(atp2 init: setPri: 148)
		(atp3 init:)
		(atp4 init: setPri: 30)
		(bridge init: setPri: 116)
		(bush init: approachVerbs: 4)
		(stream1 setPri: 30 setCycle: Fwd init:)
		(stream2 setPri: 30 setCycle: Fwd init:)
		(stream3 setPri: 30 setCycle: Fwd init:)
		(stream4 setPri: 30 setCycle: Fwd init:)
		(streamMat init:)
		(if (== heroType 3) (messager say: 2 6 11))
		(if (or (Btst 380) (< [egoStats 17] 150))
			(theGame save: 1)
		)
	)
	
	(method (dispose)
		(streamMat dispose:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(87
				(theGame handsOff:)
				(if (cast contains: branch)
					(self setScript: (ScriptID 37) branch branch)
				else
					((ScriptID 50) doVerb: theVerb)
				)
			)
			(55
				(curRoom setScript: sThrowChick)
			)
			(11
				(if (streamMat onMe: (ego x?) (ego y?))
					(messager say: 2 6 14)
				else
					(messager say: 9 0 12)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance atp1 of ForestView
	(properties
		x 4
		y 169
		view 422
	)
)

(instance atp2 of ForestView
	(properties
		x 4
		y 47
		view 423
	)
)

(instance atp3 of ForestView
	(properties
		x 229
		y 76
		view 424
		loop 2
		cel 1
	)
)

(instance atp4 of ForestView
	(properties
		x 121
		y 131
		view 424
		cel 3
	)
)

(instance bridge of ForestView
	(properties
		x 306
		y 148
		view 424
		cel 4
	)
)

(instance stream1 of ForestView
	(properties
		x 69
		y 73
		view 420
		loop 2
		signal $4001
		detailLevel 2
	)
)

(instance stream2 of ForestView
	(properties
		x 133
		y 102
		view 420
		loop 4
		signal $4001
		detailLevel 2
	)
)

(instance stream3 of ForestView
	(properties
		x 290
		y 126
		view 420
		loop 6
		signal $4001
		detailLevel 2
	)
)

(instance stream4 of ForestView
	(properties
		x 264
		y 188
		view 424
		loop 4
		signal $4001
		detailLevel 2
	)
)

(instance bush of TargActor
	(properties
		noun 3
		view 860
		signal $5000
	)
	
	(method (init)
		(self setPri: 115)
		(= x theX)
		(= y theY)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit: &rest)
		(= theX x)
		(= theY y)
		(if
			(and
				(not local6)
				(!= (bush script?) sClaw)
				(<
					(GetDistance
						(ego x?)
						(ego y?)
						(+ theX 44)
						(- theY 18)
					)
					50
				)
				(!= (curRoom script?) sMoveBack)
			)
			(if (not (cast contains: claw))
				(claw init:)
			else
				(claw show:)
			)
			(bush setScript: sClaw)
		)
	)
	
	(method (doVerb theVerb)
		(if (and (== theVerb 87) (not local2))
			(messager say: 3 87 8)
			(return 1)
		)
		(return
			(if
				(or
					(not (Message msgSIZE 570 noun theVerb 0 1))
					(OneOf theVerb 86 88 93 21 37)
				)
				(if (OneOf theVerb 21 37) (stalk setScript: sPeek))
				((ScriptID 50) doVerb: theVerb)
			else
				(switch theVerb
					(55 (curRoom doVerb: theVerb))
					(19
						(curRoom setScript: sThrowFood 0 19)
					)
					(16
						(curRoom setScript: sThrowFood 0 16)
					)
					(else  (super doVerb: theVerb))
				)
			)
		)
	)
	
	(method (getHurt param1)
		(cond 
			((== param1 88)
				(if
				(and (stalk script?) (== ((stalk script?) state?) 0))
					((stalk script?) ticks: 0 cue:)
				)
				(bush setScript: sShakeLoose 0 param1)
			)
			((== param1 21)
				(if
				(and (stalk script?) (== ((stalk script?) state?) 0))
					((stalk script?) ticks: 0 cue:)
				)
				(bush setScript: sShakeLoose 0 param1)
			)
			((== param1 37)
				(if
				(and (stalk script?) (== ((stalk script?) state?) 0))
					((stalk script?) ticks: 0 cue:)
				)
				(bush setScript: sShakeLoose 0 param1)
			)
			((OneOf param1 86 93) (messager say: noun param1 0))
		)
	)
)

(instance stalk of Prop
	(properties
		noun 3
		view 861
		signal $4000
	)
	
	(method (init)
		(= x (+ theX 44))
		(= y theY)
		(super init: &rest)
	)
	
	(method (doit)
		(= x (+ theX 44))
		(= y theY)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(bush doVerb: theVerb &rest)
	)
)

(instance claw of Prop
	(properties
		noun 3
		x 93
		y 116
		view 861
		loop 2
		signal $4001
	)
	
	(method (init)
		(= x (+ theX 44))
		(= y (- theY 18))
		(super init: &rest)
	)
)

(instance branch of Actor
	(properties
		noun 4
		x 90
		y 102
		view 861
		loop 4
		signal $4000
	)
	
	(method (init)
		(= x (+ theX 41))
		(= y (+ theY -32))
		(super init: &rest)
	)
	
	(method (dispose)
		(if (== (curRoom script?) (ScriptID 27))
			((ScriptID 27) caller: 0)
		)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== x (+ theX 41))
					(messager say: 4 4 10)
				else
					(curRoom setScript: sGetBranch)
				)
			)
			(87 (curRoom doVerb: theVerb))
			(-87
				(branch hide:)
				(bush setScript: 0)
				(if local4 (bush setScript: sMoveBack))
			)
			(else 
				(if
					(or
						(not (Message msgSIZE 570 3 theVerb 0 1))
						(OneOf theVerb 86 88 93 87 21 37 33)
					)
					((ScriptID 50) doVerb: theVerb)
				else
					(super doVerb: theVerb)
				)
			)
		)
	)
	
	(method (cue)
		(messager say: 3 87 7)
		(ego get: 30 1 solvePuzzle: 429 6)
		(= local2 0)
		(self dispose:)
	)
)

(instance chicken of View
	(properties
		noun 7
		x 195
		y 120
		view 862
		signal $4000
	)
)

(instance rock of Actor
	(properties
		view 46
		loop 4
		signal $4001
	)
)

(instance sClaw of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego x?) (bush x?))
					(claw setLoop: 3 1)
				else
					(claw setLoop: 2 1)
				)
				(claw
					signal: (| (claw signal?) $0001)
					show:
					setCel: 0
					x: (- (ego x?) 20)
					y: (- theY 13)
					setCycle: End self
				)
				(if (> (ego y?) (- theY 14))
					(claw setPri: (+ (bush priority?) 1))
				else
					(claw setPri: (bush priority?))
					(if (< (ego x?) (+ theX 61)) (claw y: (- theY 29)))
				)
				(bush
					setLoop: 4 1
					cycleSpeed: (* defaultCycles 3)
					signal: (| (bush signal?) $0001)
					setCycle: Fwd
				)
			)
			(1
				(bush setCycle: 0 signal: (& (bush signal?) $fffe))
				(claw hide:)
				(if
					(<
						(GetDistance
							(ego x?)
							(ego y?)
							(+ theX 44)
							(- theY 18)
						)
						50
					)
					(ego takeDamage: 50)
					(if (<= [egoStats 17] 0)
						(= local6 1)
						(EgoDead 1 570 43 End 912)
					else
						(messager say: 2 6 2 0 self)
					)
				else
					(self cue:)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance sShakeLoose of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(not (ego has: 30))
						(not (& ((inventory at: 28) maskCel?) $0001))
						(not (Btst 179))
					)
					(++ local2)
				)
				(bush
					setLoop: 4 1
					signal: (| (bush signal?) $0001)
					cycleSpeed: (/ defaultCycles 2)
					setCycle: Fwd
				)
				(= ticks 60)
			)
			(1
				(if (== local2 1)
					(branch
						signal: (| (branch signal?) $0001)
						init:
						setPri: (+ (bush priority?) 1)
						setMotion: JumpTo (+ theX 36) (+ theY 1) self
					)
				else
					(self cue:)
				)
				(bush setCycle: 0 signal: (& (bush signal?) $fffe))
			)
			(2
				(branch signal: (& (branch signal?) $fffe))
				(if (== local2 1)
					(switch register
						(21
							(messager say: 3 21 0 0 self)
						)
						(88
							(messager say: 3 21 0 0 self)
						)
						(else  (self cue:))
					)
				else
					(self cue:)
				)
			)
			(3
				(if local4
					(bush setScript: sMoveBack)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sPeek of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(stalk
					signal: (| (stalk signal?) $0001)
					setCycle: Beg self
				)
			)
			(1
				(stalk hide:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowChick of Script
	(properties)
	
	(method (doit)
		(= theX (bush x?))
		(= theY (bush y?))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setMotion: PolyPath 195 120 self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego
					view: 4
					setLoop: 3 1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
			)
			(3
				(chicken init: setPri: (- (bush priority?) 1))
				(ego drop: 36 1 setCycle: Beg self)
			)
			(4
				(ego normalize: 1 setMotion: PolyPath 285 137 self)
				(if
					(and
						(cast contains: stalk)
						(stalk script?)
						(== ((stalk script?) state?) 0)
					)
					((stalk script?) ticks: 0 cue:)
				)
				(bush
					signal: (| (bush signal?) $0001)
					setLoop: 0 1
					setCycle: Fwd
					setSpeed: defaultCycles
					setMotion: MoveTo 165 133 self
				)
			)
			(5
				(chicken dispose:)
				(ego setSpeed: gGTheObj_2CycleSpeed setHeading: 270)
			)
			(6
				(bush
					setLoop: 0 1
					setCycle: 0
					setCel: 0
					signal: (& (bush signal?) $fffe)
				)
				(= local4 1)
				(Bset 261)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								76
								179
								130
								179
								153
								167
								181
								167
								190
								160
								240
								160
								291
								142
								319
								142
								319
								189
								0
								189
								0
								0
								154
								0
								154
								72
								178
								83
								158
								103
								117
								103
								97
								96
								70
								96
								50
								86
								31
								86
								31
								93
								60
								108
								32
								108
								54
								129
								55
								143
								95
								143
								95
								150
								48
								154
							yourself:
						)
						((Polygon new:)
							type: 2
							init:
								319
								0
								319
								130
								295
								130
								255
								122
								248
								127
								114
								127
								114
								113
								162
								113
								195
								79
								195
								0
							yourself:
						)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetBranch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gGTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setMotion: PolyPath (branch x?) (branch y?) self)
			)
			(1 (ego setHeading: 270 self))
			(2
				(ego
					view: 4
					setLoop: 3 1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
			)
			(3
				(branch dispose:)
				(= local2 0)
				(messager say: 4 4 0 0 self)
			)
			(4
				(ego get: 30 1 solvePuzzle: 429 6 setCycle: Beg self)
			)
			(5
				(ego setSpeed: gGTheObj_2CycleSpeed normalize: 1)
				(if local4
					(curRoom setScript: sMoveBack 0 1)
				else
					(theGame handsOn:)
					(self dispose:)
				)
			)
		)
	)
)

(instance sMoveBack of Script
	(properties)
	
	(method (doit)
		(= theX (bush x?))
		(= theY (bush y?))
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local4 0)
				(= gGTheObj_2CycleSpeed egoGait)
				(if (== (bush script?) sClaw) (bush setScript: 0))
				(claw hide:)
				(bush
					setLoop: 0 1
					setCycle: Fwd
					signal: (| (bush signal?) $0001)
					setSpeed: defaultCycles
					setMotion: MoveTo 49 134 self
				)
				(if register
					(theGame handsOff:)
					(ego setMotion: PolyPath 136 165 self)
				)
			)
			(1
				(if (not register) (self cue:))
			)
			(2
				(bush
					setLoop: 0 1
					setCycle: 0
					setCel: 0
					signal: (| (bush signal?) $0001)
				)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								76
								179
								130
								179
								153
								167
								181
								167
								190
								160
								240
								160
								279
								142
								319
								142
								319
								189
								0
								189
								0
								0
								154
								0
								154
								72
								178
								83
								158
								103
								117
								103
								97
								96
								70
								96
								50
								86
								31
								86
								31
								93
								65
								114
								130
								114
								130
								128
								55
								128
								55
								143
								95
								143
								95
								150
								48
								154
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 319 0 319 131 301 131 219 115 239 105 213 98 169 105 195 79 195 0
							yourself:
						)
				)
				(if register
					(ego setHeading: 0 self)
				else
					(= register 0)
					(self dispose:)
				)
			)
			(3
				(= register 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThrowFood of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= temp0
					(GetAngle (ego x?) (ego y?) (bush x?) (bush y?))
				)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 9
					setLoop:
						(cond 
							((< temp0 90) 2)
							((< temp0 180) 0)
							((< temp0 270) 1)
							(else 3)
						)
						1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
				(if (not (cast contains: stalk)) (stalk init:))
				(stalk setScript: sPeek)
			)
			(1
				(rock
					init:
					x: (ego x?)
					y: (- (ego y?) 25)
					setLoop: 4 1
					setStep: 8 6
					setMotion: MoveTo (bush x?) (- (bush y?) 30) self
				)
				(ego
					setSpeed: gTheObj_2CycleSpeed
					normalize: (+ (ego loop?) 4)
				)
			)
			(2
				(rock dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance streamMat of Polygon
	(properties)
	
	(method (init)
		(super init: 178 101 156 101 168 93 172 97 184 97 179 101)
	)
)
