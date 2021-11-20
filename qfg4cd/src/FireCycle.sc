;;; Sierra Script 1.0 - (do not remove this comment)
(script# 360)
(include sci.sh)
(use Main)
(use GloryRm)
(use TargFeature)
(use EgoDead)
(use Array)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm360 0
)

(local
	local0
	local1
	local2
	gTheObj_2CycleSpeed
	local4
	local5 =  120
	theTheVerb
	theGGGTheObj_2CycleSpeed
	local8
	local9
	local10
)
(instance rm360 of GloryRm
	(properties
		picture 360
	)
	
	(method (init)
		(ego init: setScaler: Scaler 135 72 189 129)
		(= local9 (IDArray new: 8))
		(= local10
			(IntArray
				with: 64 143 78 153 75 156 70 160 66 166 62 173 59 180 59 181
			)
		)
		(cond 
			((== prevRoomNum 780) (ego posn: 319 137 normalize: 1))
			((Btst 328)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					posn: 234 47
					setSpeed: 15
					setCycle: Walk
				)
			)
			(else (ego posn: 144 127 normalize: 2 setPri: 0))
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						49
						151
						83
						151
						83
						155
						51
						168
						19
						168
						14
						173
						14
						188
						111
						188
						120
						167
						190
						167
						210
						187
						319
						187
						319
						162
						286
						149
						294
						149
						294
						144
						274
						144
						249
						136
						249
						129
						220
						129
						206
						143
						151
						143
						151
						138
						137
						138
						137
						143
						49
						143
					yourself:
				)
		)
		(if
		(and (not (Btst 200)) [egoStats 8] [egoStats 9])
			(blackBird init: approachVerbs: 1)
		)
		(if (not (Btst 138)) (domovoi init: setPri: 167))
		(if (Btst 362)
			(brokenGlass1 init: setPri: 160)
			(brokenGlass2 init:)
		)
		(secretDoor init:)
		(if (== prevRoomNum 780)
			(secretDoor cel: (secretDoor lastCel:) setPri: 17)
		else
			(secretDoor hide:)
		)
		(hector init:)
		(mainDoor init:)
		(bench init: approachVerbs: 4)
		(table init: approachVerbs: 4)
		(cabinet init: approachVerbs: 4 80 28 42)
		(chandelier1 init: approachVerbs: 4)
		(chandelier2 init: approachVerbs: 4)
		(leftWindow init: approachVerbs: 4)
		(rightWindow init:)
		(gargoyle1 init: approachVerbs: 4)
		(gargoyle2 init: approachVerbs: 4)
		(door init: approachVerbs: 4)
		(alcove init: approachVerbs: 4)
		(wrongHolder init: approachVerbs: 4)
		(doorTrigger init: approachVerbs: 4)
		(fireplace init: approachVerbs: 4)
		(theMouth init: approachVerbs: 4)
		(bones init: approachVerbs: 4)
		(blood init: approachVerbs: 4)
		(cloud init: approachVerbs: 4)
		(heart init: approachVerbs: 4)
		(beetle1 init: approachVerbs: 4)
		(beetle2 init: approachVerbs: 4)
		(scorpion init: approachVerbs: 4)
		(curtain init: approachVerbs: 4)
		(hexStand init: approachVerbs: 4)
		(rug init: approachVerbs: 4)
		(super init: &rest)
		(cond 
			((== prevRoomNum 780) (self setScript: sFromBasement))
			((Btst 328) (self setScript: sClimbDown))
			(else (self setScript: sEnterRoom))
		)
		(theGame save: 1)
	)
	
	(method (dispose)
		(local10 dispose:)
		(local9 dispose:)
		(super dispose:)
	)
	
	(method (newRoom n)
		(theMusic fade: 0)
		(super newRoom: n &rest)
	)
)

(instance sOpenSecretDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 31 loop: 0 cel: 0 setCycle: CT 1 1 self)
			)
			(1
				(ego solvePuzzle: 435 6)
				(secretDoor show: setPri: 17 setCycle: End self)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego normalize: 6 setMotion: MoveTo 292 140 self)
			)
			(4
				(ego setMotion: MoveTo 319 137 self)
			)
			(5 (curRoom newRoom: 780))
		)
	)
)

(instance sFromBasement of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic number: 360 setLoop: -1 play:)
				(ego setMotion: MoveTo 292 140 self)
			)
			(1
				(ego setMotion: MoveTo 275 160 self)
			)
			(2
				(secretDoor setCycle: Beg self)
			)
			(3
				(if (Btst 41) (= next sEgoRuns))
				(secretDoor hide:)
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sSetFire of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 41)
				(ego addHonor: 500)
				(if register
					(= cycles 1)
				else
					(ego setMotion: PolyPath 216 152 self)
				)
			)
			(1
				(if register
					(= cycles 1)
				else
					(ego
						view: 4
						loop: 0
						cel: 0
						posn: 213 150
						setCycle: End self
					)
				)
			)
			(2
				(messager say: 26 6 9 0 self)
			)
			(3
				(if register (= cycles 1) else (ego setCycle: Beg self))
			)
			(4
				(if (not register) (ego posn: 216 152))
				(ego normalize: 0 setScript: sEgoRuns)
				(local9
					at: 0 ((fire new:) init: setCycle: CT 2 1 self yourself:)
				)
			)
			(5
				((local9 at: 0) setCycle: CT 1 -1 self)
			)
			(6
				((local9 at: 0) setCycle: CT 2 1 self)
			)
			(7
				(if (not local0) (= state (- state 2)))
				(= cycles 1)
			)
			(8
				((local9 at: 0) setCycle: FireCycle 999 1 self)
			)
			(9
				(local9
					at:
						1
						((fire new:)
							loop: 5
							posn: 161 145
							init:
							setCycle: FireCycle 999 1 self
							yourself:
						)
				)
			)
			(10
				(local9
					at:
						2
						((fire new:)
							loop: 6
							posn: 94 154
							init:
							setCycle: FireCycle 999 1 self
							yourself:
						)
				)
			)
			(11
				(local9
					at:
						3
						((fire new:)
							loop: 2
							posn: 79 164
							init:
							setCycle: FireCycle 999 1 self
							yourself:
						)
				)
			)
			(12
				(local9
					at:
						4
						((fire new:)
							loop: 3
							posn: 79 164
							init:
							setCycle: FireCycle 999 1 self
							yourself:
						)
				)
			)
			(13
				(local9
					at:
						5
						((fire new:)
							loop: 1
							posn: 96 187
							init:
							setCycle: FireCycle 999 1 self
							yourself:
						)
				)
			)
			(14 (curRoom newRoom: 250))
		)
	)
)

(instance sEgoRuns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local0 1)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(if (mainDoor heading?)
					((mainDoor heading?) dispose:)
					(mainDoor heading: 0)
				)
				(= cycles 1)
			)
			(1
				(ego changeGait: 1)
				(if (Btst 328)
					(sClimbWall register: 0)
					(= next sClimbWall)
				else
					(= next sExitRoom)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(theMusic number: 360 setLoop: -1 play:)
				(ego solvePuzzle: 434 6)
				(ego setPri: -1 setMotion: MoveTo 144 149 self)
			)
			(2
				(mainDoor setCycle: Beg self)
			)
			(3
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 2)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sExitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 145 143 self)
			)
			(1
				(Bclr 328)
				(ego setHeading: 0 self)
			)
			(2
				(theMusic2 number: 960 loop: 1 play:)
				(mainDoor setCycle: End self)
			)
			(3
				(ego setPri: 0 setMotion: MoveTo 144 127 self)
			)
			(4
				(if theGGGTheObj_2CycleSpeed
					(ego changeGait: theGGGTheObj_2CycleSpeed)
				)
				(curRoom newRoom: 250)
			)
		)
	)
)

(instance sRehydrateDomo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 138)
				(ego use: 32)
				(ego setMotion: PolyPath 59 177 self)
			)
			(1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					solvePuzzle: 440 6
					view: 31
					loop: 1
					cel: 0
					posn: 59 177
					cycleSpeed: 9
					setCycle: CT 2 1 self
				)
			)
			(2
				(ego setCycle: DomoCycle 0 -1 self)
			)
			(3 (= cycles 2))
			(4
				(ego
					view: 4
					loop: 1
					cel: 0
					posn: 63 176
					setCycle: DomoCycle 4 1 self
				)
			)
			(5 (ego setCycle: Beg self))
			(6 (domovoi setCycle: Beg self))
			(7
				(messager say: 23 6 10 0 self)
			)
			(8
				(domovoi dispose:)
				(ego normalize: 1 setSpeed: gTheObj_2CycleSpeed)
				(= seconds 2)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbWall of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(ego setMotion: PolyPath 234 128 self)
			)
			(1
				1
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(if (== (ego trySkill: 11 250) 1)
					(self changeState: 5)
				else
					(= cycles 1)
				)
			)
			(2
				2
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					setSpeed: 15
					setCycle: Walk
					setMotion: MoveTo 234 110 self
				)
			)
			(3
				3
				(ego
					setSpeed: 4
					setCycle: 0
					setMotion: MoveTo 234 128 self
				)
			)
			(4
				4
				(= state 17)
				(messager say: 26 6 6 0 self)
			)
			(5
				5
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					setSpeed: 15
					setCycle: Walk
					setMotion: MoveTo 234 47 self
				)
			)
			(6
				6
				(ego setMotion: MoveTo 221 47 self)
			)
			(7
				7
				(ego normalize: 1 setSpeed: 9)
				(if (== register 2)
					(self changeState: 11)
				else
					(= cycles 1)
				)
			)
			(8
				8
				(ego setMotion: MoveTo 198 44 self)
			)
			(9 9 (ego setHeading: 0 self))
			(10
				10
				(if theGGGTheObj_2CycleSpeed
					(ego changeGait: theGGGTheObj_2CycleSpeed)
				)
				(ego setSpeed: gTheObj_2CycleSpeed)
				(Bset 328)
				(curRoom newRoom: 250)
			)
			(11
				11
				(ego setMotion: MoveTo 166 38 self)
			)
			(12
				12
				(ego
					view: 4
					loop: 1
					cel: 0
					posn: 167 38
					setCycle: CT 3 1 self
				)
			)
			(13
				13
				(blackBird dispose:)
				(Bset 200)
				(ego get: 34 solvePuzzle: 506 6 4 setCycle: CT 0 -1 self)
			)
			(14
				14
				(ego normalize: 1 setHeading: 90 self)
			)
			(15
				15
				(ego setMotion: MoveTo 221 47 self)
			)
			(16
				(ego
					view: 7
					setLoop: 3 1
					plane: 0
					setSpeed: 15
					setCycle: Walk
					setMotion: MoveTo 234 47 self
				)
			)
			(17
				17
				(ego setMotion: MoveTo 234 128 self)
			)
			(18
				18
				(if (not local0)
					(ego normalize: 3 setSpeed: gTheObj_2CycleSpeed)
				else
					(ego hide:)
				)
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
				(= cycles 2)
			)
			(1
				(theMusic number: 360 setLoop: -1 play:)
				(ego setMotion: MoveTo 234 128 self)
				(ego solvePuzzle: 434 6)
			)
			(2
				(ego normalize: 3 setSpeed: gTheObj_2CycleSpeed)
				(= cycles 2)
			)
			(3 (ego setHeading: 180 self))
			(4
				(ego setMotion: MoveTo 234 138 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBustGlass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 20 4 13 0 self)
			)
			(1
				(brokenGlass1 init: setPri: 160)
				(brokenGlass2 init:)
				(Bset 362)
				(ego get: 3)
				(= cycles 2)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHectorAct of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(hector loop: 0 setCycle: End self)
			)
			(1
				(hector loop: 1 setCycle: Fwd)
				(= seconds 3)
			)
			(2
				(if register
					(hector setCycle: End self)
				else
					(messager say: 26 6 11 0 self)
				)
			)
			(3
				(if register
					(ego setMotion: MoveTo (ego x?) (ego y?))
					(hector
						x: 279
						y: 62
						setLoop: 2 1
						setPri: 221
						xStep: 4
						yStep: 6
						moveSpeed: 3
						setCycle: End
						setMotion: MoveTo (- (ego x?) 3) (- (ego y?) 40) self
					)
				else
					(= local4 1)
					(hector loop: 0 cel: 1 setCycle: Beg self)
					(ego setMotion: PolyPath 205 148 self)
				)
			)
			(4
				(if register (EgoDead 12 0 978 1 912) else 0)
			)
			(5 (ego setHeading: 90 self))
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKillHector of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register (theGame handsOff:))
				(hector loop: 0 setCycle: End self)
			)
			(1
				(hector loop: 1 setCycle: End self)
			)
			(2
				(hector loop: 0 cel: 1 setCycle: Beg self)
			)
			(3
				(if register
					(Bset 201)
					(ego addHonor: -100)
					(messager say: 26 6 19 0 self)
				else
					(messager say: 28 theTheVerb 0 0 self)
				)
			)
			(4
				(if (and register local8)
					(if (== local8 1)
						(ego get: 5)
						(messager say: 29 4 22)
					else
						(ego get: 5 local8)
						(messager say: 29 4 23)
					)
					(= local8 0)
				)
				(= cycles 1)
			)
			(5
				(if register (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance brokenGlass1 of View
	(properties
		sightAngle 180
		x 55
		y 127
		view 360
	)
)

(instance brokenGlass2 of View
	(properties
		sightAngle 180
		x 56
		y 127
		view 360
		cel 1
	)
)

(instance blackBird of TargProp
	(properties
		noun 24
		sightAngle 180
		approachX 144
		approachY 149
		x 145
		y 33
		z 10
		view 360
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sClimbWall 0 2)
			)
			(87
				(= projX 147)
				(= projY 25)
				(curRoom setScript: (ScriptID 37) 0 self)
			)
			(-87
				(Bset 200)
				(ego get: 34 solvePuzzle: 506 6 4)
				(self hide:)
				(messager say: 24 87 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance mainDoor of Prop
	(properties
		noun 14
		sightAngle 180
		approachX 145
		approachY 144
		x 146
		y 85
		view 360
		loop 3
	)
	
	(method (init)
		(if (and (not (Btst 328)) (== prevRoomNum 250))
			(= cel 7)
		)
		(super init: &rest)
		(if (not (Btst 328))
			(= heading
				(((ScriptID 49 0) new:)
					init:
						((Polygon new:)
							type: 1
							init: 153 136 153 142 135 142 135 136
							yourself:
						)
						6
						3
						7
						sExitRoom
					yourself:
				)
			)
		)
	)
	
	(method (dispose)
		(if (and (not (Btst 328)) heading) (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 328) (messager say: 14 4 5))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance secretDoor of Prop
	(properties
		noun 10
		sightAngle 180
		x 299
		y 142
		view 361
		signal $4001
	)
)

(instance domovoi of Prop
	(properties
		noun 23
		sightAngle 180
		x 59
		y 139
		view 366
		loop 2
		cel 10
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(51
				(curRoom setScript: sRehydrateDomo)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fire of Prop
	(properties
		x 241
		y 130
		view 362
		signal $4001
	)
)

(instance hector of TargActor
	(properties
		noun 28
		sightAngle 90
		x 288
		y 76
		view 368
		signal $4001
	)
	
	(method (doit)
		(if
			(and
				(not (Btst 201))
				(not (curRoom script?))
				(<= (GetDistance (ego x?) (ego y?) 307 137) 50)
			)
			(if local4
				(curRoom setScript: sHectorAct 0 1)
			else
				(curRoom setScript: sHectorAct 0 0)
			)
		)
		(super doit: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 40)
				(if (Btst 201)
					(messager say: 26 6 20)
				else
					(ego use: 22)
					(Bset 201)
					(super doVerb: theVerb &rest)
				)
			)
			((OneOf theVerb 37 86 88 79 93 21)
				(cond 
					((Btst 201) (messager say: 26 6 20))
					(
						(and
							(== theVerb 37)
							(== ((inventory at: 5) amount?) 1)
						)
						(messager say: 26 6 24)
					)
					(else
						(if (== theVerb 37) (++ local8))
						(curRoom
							setScript: (ScriptID 32) self (= theTheVerb theVerb)
						)
					)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (getHurt param1 param2)
		(if (<= (= local5 (- local5 param2)) 0)
			(self setScript: sKillHector 0 1)
		else
			(self setScript: sKillHector 0 0)
		)
	)
)

(instance bench of Feature
	(properties
		noun 22
		nsLeft 163
		nsTop 147
		nsRight 205
		nsBottom 189
		sightAngle 180
		x 184
		y 168
	)
)

(instance table of Feature
	(properties
		noun 21
		nsLeft 113
		nsTop 152
		nsRight 164
		nsBottom 189
		sightAngle 180
		x 138
		y 170
	)
)

(instance cabinet of Feature
	(properties
		noun 20
		nsLeft 19
		nsTop 92
		nsRight 68
		nsBottom 164
		sightAngle 180
		approachX 72
		approachY 165
		x 43
		y 128
	)
	
	(method (handleEvent event)
		(if (== (event message?) KEY_P)
			(= approachX 114)
			(= approachY 161)
		else
			(= approachX 72)
			(= approachY 165)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1)
				(cond 
					((Btst 362) (messager say: 20 1 1))
					((Btst 202) (messager say: 20 1 2))
					(else (messager say: 20 1 3))
				)
			)
			(
				(and
					(or (Btst 362) (Btst 202))
					(OneOf theVerb 42 4 80)
				)
				(messager say: 20 4 16)
			)
			((== theVerb 4)
				(if (> [egoStats 0] 300)
					(if (== heroType 3)
						(messager say: 20 4 14)
					else
						(curRoom setScript: sBustGlass)
					)
				else
					(messager say: 20 4 15)
				)
			)
			((OneOf theVerb 42 28)
				(ego trySkill: 9 get: 3)
				(Bset 202)
				(super doVerb: theVerb &rest)
			)
			((== theVerb 80)
				(if (ego castSpell: 20)
					(= projX 57)
					(= projY 114)
					(curRoom setScript: (ScriptID 13 0) 0 self)
				)
			)
			((== theVerb -80) (Bset 202) (ego get: 3) (messager say: 20 80))
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance chandelier1 of Feature
	(properties
		noun 19
		nsLeft 35
		nsRight 87
		nsBottom 27
		sightAngle 180
		x 61
		y 13
	)
)

(instance chandelier2 of Feature
	(properties
		noun 19
		nsLeft 235
		nsRight 295
		nsBottom 35
		sightAngle 180
		x 265
		y 17
	)
)

(instance leftWindow of Feature
	(properties
		noun 17
		nsLeft 86
		nsRight 112
		nsBottom 27
		sightAngle 180
		x 99
		y 13
	)
)

(instance rightWindow of Feature
	(properties
		noun 16
		nsLeft 183
		nsTop -1
		nsRight 212
		nsBottom 38
		sightAngle 180
		x 197
		y 18
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 328)
					(curRoom setScript: sClimbWall 0 1)
				else
					(messager say: 27 4 5)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance gargoyle1 of Feature
	(properties
		noun 15
		nsLeft 75
		nsTop 58
		nsRight 89
		nsBottom 73
		sightAngle 180
		x 82
		y 65
	)
)

(instance gargoyle2 of Feature
	(properties
		noun 15
		nsLeft 202
		nsTop 65
		nsRight 217
		nsBottom 78
		sightAngle 180
		x 209
		y 71
	)
)

(instance door of Feature
	(properties
		noun 14
		nsLeft 125
		nsTop 87
		nsRight 169
		nsBottom 129
		sightAngle 180
		x 147
		y 108
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 328)
					(messager say: 14 4 5)
				else
					(curRoom setScript: sExitRoom)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance alcove of Feature
	(properties
		noun 13
		nsLeft 144
		nsTop 19
		nsRight 152
		nsBottom 31
		sightAngle 180
		approachX 234
		approachY 128
		x 148
		y 25
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
				(and (not (Btst 200)) [egoStats 8] [egoStats 9])
					(curRoom setScript: sClimbWall 0 2)
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

(instance wrongHolder of Feature
	(properties
		noun 12
		nsLeft 268
		nsTop 118
		nsRight 278
		nsBottom 141
		sightAngle 180
		x 273
		y 129
	)
)

(instance doorTrigger of Feature
	(properties
		noun 11
		nsLeft 301
		nsTop 124
		nsRight 313
		nsBottom 150
		sightAngle 180
		approachX 289
		approachY 158
		x 307
		y 137
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 201) (curRoom setScript: sOpenSecretDoor))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance fireplace of Feature
	(properties
		noun 10
		nsLeft 266
		nsTop 83
		nsRight 314
		nsBottom 150
		sightAngle 180
		x 290
		y 116
	)
)

(instance theMouth of Feature
	(properties
		noun 9
		nsLeft 93
		nsTop 34
		nsRight 110
		nsBottom 45
		sightAngle 180
		x 101
		y 39
	)
)

(instance bones of Feature
	(properties
		noun 8
		nsLeft 122
		nsTop 36
		nsRight 139
		nsBottom 48
		sightAngle 180
		x 130
		y 42
	)
)

(instance blood of Feature
	(properties
		noun 7
		nsLeft 149
		nsTop 39
		nsRight 158
		nsBottom 49
		sightAngle 180
		x 153
		y 44
	)
)

(instance cloud of Feature
	(properties
		noun 6
		nsLeft 166
		nsTop 40
		nsRight 184
		nsBottom 50
		sightAngle 180
		x 175
		y 45
	)
)

(instance heart of Feature
	(properties
		noun 5
		nsLeft 192
		nsTop 43
		nsRight 207
		nsBottom 52
		sightAngle 180
		x 199
		y 47
	)
)

(instance beetle1 of Feature
	(properties
		noun 4
		nsLeft 53
		nsTop 70
		nsRight 75
		nsBottom 93
		sightAngle 180
		x 64
		y 81
	)
)

(instance beetle2 of Feature
	(properties
		noun 4
		nsLeft 224
		nsTop 73
		nsRight 244
		nsBottom 98
		sightAngle 180
		x 234
		y 85
	)
)

(instance scorpion of Feature
	(properties
		noun 3
		nsLeft 216
		nsTop 10
		nsRight 234
		nsBottom 40
		sightAngle 180
		x 225
		y 25
	)
)

(instance curtain of TargFeature
	(properties
		noun 2
		nsLeft 15
		nsRight 42
		nsBottom 87
		sightAngle 180
		x 28
		y 43
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 39 76)
			(if (and (Btst 138) (Btst 233))
				(messager say: 26 6 21)
			else
				(messager say: 2 39 0)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (getHurt)
		(curRoom setScript: sSetFire 0 1)
	)
)

(instance hexStand of Feature
	(properties
		noun 1
		nsLeft 273
		nsTop 47
		nsRight 290
		nsBottom 74
		sightAngle 180
		x 281
		y 60
	)
)

(instance rug of TargFeature
	(properties
		noun 25
		nsLeft 51
		nsTop 150
		nsRight 318
		nsBottom 189
		sightAngle 180
		x 184
		y 169
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 39 76)
				(if
					(and
						(Btst 138)
						(Btst 233)
						(or (ego has: 53) ((inventory at: 53) chestAmout?))
					)
					(curRoom setScript: sSetFire)
				else
					(super doVerb: theVerb &rest)
				)
			)
			((== theVerb 4)
				(if local8
					(if (== local8 1)
						(ego get: 5)
						(messager say: 29 4 22)
					else
						(ego get: 5 local8)
						(messager say: 29 4 23)
					)
					(= local8 0)
				else
					(super doVerb: theVerb)
				)
			)
			((== theVerb 86)
				(if
					(and
						(Btst 138)
						(Btst 233)
						(or (ego has: 53) ((inventory at: 53) chestAmout?))
					)
					(if (ego castSpell: 26)
						(= projX 232)
						(= projY 147)
						(ego setScript: (ScriptID 32) 0 86)
					)
				else
					(super doVerb: theVerb)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
	
	(method (getHurt)
		(curRoom setScript: sSetFire 0 1)
	)
)

(class FireCycle of CT
	(properties
		scratch 0
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
		clientLastCel 0
		endCel 0
		cueFlag 0
	)
	
	(method (doit &tmp fireCycleNextCel clientLastCel)
		(if (> endCel (= clientLastCel (client lastCel:)))
			(= endCel clientLastCel)
		)
		(= fireCycleNextCel (self nextCel:))
		(client
			cel:
				(cond 
					((> fireCycleNextCel clientLastCel) 0)
					((< fireCycleNextCel 0) clientLastCel)
					(else fireCycleNextCel)
				)
		)
		(if
			(and
				(== gameTime cycleCnt)
				(not cueFlag)
				(== endCel (client cel?))
			)
			(= cueFlag 1)
			(caller cue:)
		)
	)
	
	(method (nextCel)
		(return
			(if
			(< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
				(client cel?)
			else
				(= cycleCnt gameTime)
				(if (== (client cel?) (client lastCel:))
					(- (client cel?) 1)
				else
					(+ (client cel?) cycleDir)
				)
			)
		)
	)
)

(instance DomoCycle of CT
	(properties)
	
	(method (nextCel)
		(return
			(if
			(< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
				(return (client cel?))
			else
				(domovoi
					posn: (local10 at: local1) (local10 at: (+ local1 1))
				)
				(if (>= local1 8) (domovoi setPri: 238))
				(= local1 (+ local1 2))
				(= cycleCnt gameTime)
				(return (+ (client cel?) cycleDir))
			)
		)
	)
)
