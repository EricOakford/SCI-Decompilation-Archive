;;; Sierra Script 1.0 - (do not remove this comment)
(script# 480)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerObj)
(use TellerIcon)
(use EgoDead)
(use GloryTalker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use ForCount)
(use Reverse)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm480 0
	boneTalker 1
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	[local6 2] = [500 500]
	local8
)
(instance rm480 of GloryRm
	(properties
		picture 480
		east 593
	)
	
	(method (init)
		(if (and (== prevRoomNum 490) (== global348 4))
			(= global348 5)
		)
		(theGame handsOff:)
		(if (and (== global348 1) (ego has: 26))
			(= global348 2)
		)
		(theHut init: approachVerbs: 4 45)
		(pestle init: setPri: 107 approachVerbs: 4 47 24)
		(mortar init: approachVerbs: 4 47 24)
		(theBody init: setPri: 138 approachVerbs: 4 2)
		(boneHead init: approachVerbs: 4 2 44)
		(theHat
			init:
			setCel: (if (Btst 151) 1 else 0)
			setPri: 170
			approachVerbs: 4 2 44
		)
		(heroTeller
			init:
				ego
				480
				28
				128
				(switch global348
					(0 1)
					(1 1)
					(2 18)
					(3 19)
					(4 20)
					(5 22)
					(6 24)
					(else  26)
				)
		)
		(boneTeller
			init:
				theHat
				480
				28
				169
				(switch global348
					(0 1)
					(1 1)
					(2 18)
					(3 19)
					(4 20)
					(5 22)
					(6 24)
					(else  26)
				)
		)
		(hutPerch init: approachVerbs: 4 45)
		(laserSkull1 init:)
		(gateSkull init:)
		(laserSkull2 init:)
		(threeSkulls init: approachVerbs: 4 46)
		(fourSkulls init: approachVerbs: 4 46)
		(treeTop init:)
		(treeTrunk init:)
		(eggRock init:)
		(exitArea init:)
		(cliff init:)
		(if (== prevRoomNum 593)
			(self setScript: from593Scr)
		else
			(= local3 1)
			(self setScript: from490Scr)
		)
		(super init: &rest)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						247
						115
						284
						138
						284
						148
						261
						167
						220
						175
						131
						175
						131
						159
						214
						141
						214
						130
						195
						113
						205
						96
						153
						96
						136
						103
						103
						105
						103
						109
						166
						116
						162
						133
						119
						151
						53
						139
						33
						118
						33
						112
						112
						87
						69
						73
						69
						61
						36
						61
						70
						87
						70
						92
						12
						109
						12
						126
						38
						158
						59
						186
						264
						186
						301
						168
						311
						140
						268
						114
						268
						0
						319
						0
						319
						189
						0
						189
						0
						0
						247
						0
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 149 100 191 100 191 108 149 108
					yourself:
				)
		)
		(curRoom
			addPoly:
				((Polygon new:)
					init:
						205
						137
						191
						119
						199
						86
						221
						89
						236
						143
						319
						152
						319
						189
						241
						189
						202
						160
					yourself:
				)
				50
		)
		(if (not (Btst 151))
			(= local1
				((Polygon new:)
					init: 63 189 0 0 0 151 134 150 133 171
					yourself:
				)
			)
		)
		(theMusic number: 491 setLoop: -1 play:)
		(ego actions: (heroDist init: theHat 100 yourself:))
		(if (== prevRoomNum 593) (theGame save: 1))
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and debugging (!= local4 global348))
			(Bset 336)
			(= local4 global348)
		)
		(cond 
			(
				(and
					(> (ego x?) 236)
					(< (ego y?) 116)
					(!= (self script?) sToForest)
					(!= (self script?) from593Scr)
				)
				(theGame handsOff:)
				(self setScript: sToForest)
			)
			(
				(and
					(& (ego scaleSignal?) $0001)
					(ego inRect: 120 129 226 137)
					(!= (curRoom script?) from490Scr)
				)
				(ego setScale:)
			)
			(
				(and
					(not (ego scaler?))
					(ego inRect: 120 138 226 146)
					(!= (curRoom script?) from490Scr)
				)
				(ego setScaler: Scaler 113 45 176 61)
			)
		)
		(cond 
			(script)
			((and (not global348) (< (ego x?) 230)) (Bset 150) (= global348 1) (messager say: 5 6 13))
			(
				(and
					(< (ego x?) 230)
					(not local3)
					(OneOf global348 2 6 7 8)
				)
				(= local3 1)
				(switch global348
					(2 (messager say: 5 6 14))
					(6 (messager say: 5 6 18))
					(else  (DailyMsg 5 6 19))
				)
			)
			(
				(and
					(not (Btst 151))
					(or (== global348 1) (== global348 0))
					(< (ego x?) 142)
					(not local2)
				)
				(= local2 1)
				(ego setMotion: 0)
				(curRoom setScript: sWarnDanger)
			)
			(
				(and
					(not (Btst 151))
					(not (cast contains: theLaser))
					(< (ego x?) 142)
				)
				(theLaser
					init:
					signal: (| (theLaser signal?) $0001)
					setCel: 0
					setLoop: 6 1
					setCycle: CT 7 1
				)
			)
			(
			(and (cast contains: theLaser) (> (ego x?) 142)) (theLaser setCycle: Beg theLaser))
			(
				(and
					(not (Btst 151))
					(local1 onMe: (ego x?) (ego y?))
				)
				(curRoom setScript: zapEgo)
			)
			((and (> (ego x?) 236) (< (ego y?) 116)) (theGame handsOff:) (self setScript: sToForest))
			(
				(or
					(and
						(< (ego y?) 69)
						(<= global348 3)
						(<= (theHut x?) 69)
						(not (theHut mover?))
					)
					(and
						(not (Btst 432))
						(>= global348 5)
						(<= (theHut x?) 69)
						(not (theHut mover?))
					)
				)
				(theHut
					signal: (| (theHut signal?) $0001)
					setLoop: 5 1
					setCycle: Fwd
					setMotion: MoveTo 124 (theHut y?) theHut
				)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								247
								115
								284
								138
								284
								148
								261
								167
								220
								175
								131
								175
								131
								159
								214
								141
								214
								130
								195
								113
								205
								96
								153
								96
								136
								103
								103
								105
								103
								109
								166
								116
								162
								133
								119
								151
								53
								139
								33
								118
								33
								112
								112
								87
								69
								73
								69
								61
								94
								56
								94
								47
								78
								47
								57
								52
								6
								54
								6
								58
								36
								61
								70
								87
								70
								92
								12
								109
								12
								126
								38
								158
								59
								186
								264
								186
								301
								168
								311
								140
								268
								114
								268
								0
								319
								0
								319
								189
								0
								189
								0
								0
								247
								0
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 149 100 191 100 191 108 149 108
							yourself:
						)
				)
			)
			(
				(and
					(>= global348 4)
					(<= (theHut x?) 69)
					(< (ego y?) 62)
				)
				(curRoom setScript: sInHut)
			)
		)
	)
	
	(method (dispose)
		(if (== global348 5) (= global348 6))
		(if local1 (local1 dispose:))
		(heroTeller dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(86
				(= local5 1)
				(curRoom setScript: (ScriptID 32) self 86)
			)
			(88
				(= local5 1)
				(curRoom setScript: (ScriptID 32) self 88)
			)
			(93
				(= local5 1)
				(curRoom setScript: (ScriptID 32) self 93)
			)
			(79
				(= local5 1)
				(curRoom setScript: (ScriptID 32) self 79)
			)
			(81 (messager say: 0 81 0))
		)
	)
	
	(method (cue)
		(if local5
			(EgoDead 73 480 970 1)
		else
			(theLaser setLoop: 7 1 setCel: 0 setCycle: End theLaser)
		)
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (sayMessage)
		(switch iconValue
			(83 (Bset 336))
			(74 (= local8 1))
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				74
				(if
				(and (not (Btst 336)) (OneOf global348 0 1 2))
					(Btst 159)
				else
					0
				)
				83
				(if
				(and (not (Btst 336)) (OneOf global348 0 1 2))
					(Btst 159)
				else
					0
				)
				75
				local8
				76
				local8
				77
				local8
		)
	)
)

(instance boneTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker boneTalker)
	)
	
	(method (showCases)
		(super showCases: 94 (not (Btst 179)))
	)
)

(instance boneTalker of GloryTalker
	(properties
		x 2
		talkWidth 130
		view 486
		textX 160
		textY 10
	)
	
	(method (init)
		(if (Btst 151)
			(super init: boneMouth boneBust boneEyes boneFrame &rest)
		else
			(super init: boneMouth 0 boneEyes boneFrame &rest)
		)
	)
)

(instance boneFrame of View
	(properties
		x 2
		y -1
		view 486
	)
)

(instance boneBust of Prop
	(properties
		y 3
		priority 255
		view 486
		loop 1
	)
)

(instance boneMouth of Prop
	(properties
		x 65
		y 99
		view 486
		loop 2
	)
)

(instance boneEyes of Prop
	(properties
		x 102
		y 76
		view 486
		loop 3
	)
)

(instance from490Scr of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 1)
			)
			(1
				(ego
					view: 161
					setLoop: 2
					setScaler: Scaler 113 45 176 61
					posn: 208 186
					init:
				)
				(ego cel: (ego lastCel:) setCycle: Beg self)
			)
			(2
				(ego setLoop: 1)
				(ego cel: (ego lastCel:) setCycle: Beg self)
			)
			(3
				(ego setLoop: 0)
				(ego cel: (ego lastCel:) setCycle: Beg self)
			)
			(4
				(ego normalize: setHeading: 315)
				(= cycles 12)
			)
			(5
				(switch global348
					(5
						(messager say: 5 6 17 0 self)
					)
					(7
						(messager say: 5 6 20 0 self)
						(= global348 8)
						(= local3 1)
					)
					(8
						(DailyMsg 5 6 21 self)
						(= local3 1)
					)
					(else  (self cue:))
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sInHut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (not (theHut cel?))
					(theHut
						signal: (| (theHut signal?) $0001)
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(1 (ego setHeading: 0 self))
			(2 (curRoom newRoom: 490))
		)
	)
)

(instance sWarnDanger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 5 6 16 0 self)
			)
			(1
				(theLaser
					signal: (| (theLaser signal?) $0001)
					init:
					setCel: 0
					setLoop: 6 1
					setCycle: CT 7 1 self
				)
			)
			(2
				(theLaser signal: (& (theLaser signal?) $fffe))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance from593Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (ego moveSpeed?))
				(ego
					init:
					setLoop: 2 1
					setCycle: Fwd
					setPri: 64
					setScale:
					scaleX: 80
					scaleY: 80
					setSpeed: defaultCycles
					posn: 246 154
					setMotion: MoveTo 267 115 self
				)
			)
			(1
				(ego
					setScaler: Scaler 113 45 176 61
					setPri: -1
					normalize:
					setLoop: 2 1
					setMotion: PolyPath 271 122 self
				)
			)
			(2
				(ego normalize: setSpeed: register)
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(3
				(if (== heroType 3)
					(messager say: 17 6 16 0 self)
				else
					(self cue:)
				)
			)
			(4
				(ego solvePuzzle: 422 6)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sToForest of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register (ego moveSpeed?))
				(ego
					setLoop: 3 1
					setCycle: Fwd
					setPri: 64
					setScale:
					setSpeed: 6
					setMotion: MoveTo 246 154 self
				)
				(if (== global348 1)
					(= global348 2)
					(messager say: 5 6 12)
				)
			)
			(1
				(ego setSpeed: register)
				(Palette palSET_FLAG 66 85 100)
				(curRoom newRoom: 593)
			)
		)
	)
)

(instance sPushBody of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local0 0)
				(= register (ego cycleSpeed?))
				(ego
					view: 31
					setLoop: 1 1
					cycleSpeed: 6
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(theBody
					signal: (| (theBody signal?) $0001)
					setLoop: 3 1
					setCycle: End theBody
				)
				(ego setCycle: Beg self)
			)
			(2
				(ego normalize: 7 setSpeed: register)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance zapEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theLaser
					signal: (| (theLaser signal?) $0001)
					setCycle: End curRoom
				)
				(if (not (ego takeDamage: 10))
					(curRoom setScript: sEgoDies)
				else
					(ego
						setLoop: 1 1
						setMotion: JumpTo (+ (ego x?) 40) 178 self
					)
				)
			)
			(1
				(ego normalize: 1)
				(= ticks 100)
			)
			(2
				(messager say: 5 6 24)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEgoDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: 0 setHeading: 180 self)
			)
			(1
				(ego
					view: 995
					setLoop: 0 1
					setCel: 0
					cycleSpeed: defaultCycles
					setCycle: End self
				)
			)
			(2 (EgoDead 73 480 970 1))
		)
	)
)

(instance sGiveHat of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego drop: 26 1)
				(theGame handsOff:)
				(= register (ego cycleSpeed?))
				(ego
					view: 31
					setLoop: 1 1
					setCel: 0
					setSpeed: 6
					setCycle: End self
				)
			)
			(1
				(ego solvePuzzle: 426 6)
				(theHat setCel: 1 setPri: 170)
				(Bset 151)
				(local1 dispose:)
				(= local1 0)
				(= global348 3)
				((heroTeller stack?) dispose:)
				(heroTeller
					stack: ((intList new:) addToFront: 19)
					curNoun: 19
					rootNoun: 19
				)
				((boneTeller stack?) dispose:)
				(boneTeller
					stack: ((intList new:) addToFront: 19)
					curNoun: 19
					rootNoun: 19
				)
				(ego setCycle: Beg self)
			)
			(2
				(messager say: 5 6 22)
				(ego normalize: 7 setSpeed: register)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance dropCorn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego drop: 27 1)
				(theGame handsOff:)
				(ego view: 4 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(1
				(= global348 4)
				((heroTeller stack?) dispose:)
				(heroTeller
					stack: ((intList new:) addToFront: 20)
					curNoun: 20
					rootNoun: 20
				)
				((boneTeller stack?) dispose:)
				(boneTeller
					stack: ((intList new:) addToFront: 20)
					curNoun: 20
					rootNoun: 20
				)
				(localCorn x: 58 y: 49 init:)
				(ego setCycle: Beg self)
			)
			(2
				(ego
					view: 0
					setLoop: 6 1
					setCycle: Rev
					setMotion: MoveTo (- (ego x?) 10) (+ (ego y?) 10) self
				)
			)
			(3
				(ego normalize: 6)
				(theHut
					signal: (| (theHut signal?) $0001)
					setLoop: 5 1
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 54 56 self
				)
			)
			(4
				(theHut setLoop: 4 1 setCel: 0 setCycle: End self)
			)
			(5
				(theHut signal: (& (theHut signal?) $fffe))
				(ego solvePuzzle: 427 6)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								247
								115
								284
								138
								284
								148
								261
								167
								220
								175
								131
								175
								131
								159
								214
								141
								214
								130
								195
								113
								205
								96
								153
								96
								136
								103
								103
								105
								103
								109
								166
								116
								162
								133
								119
								151
								53
								139
								33
								118
								33
								112
								112
								87
								69
								73
								69
								61
								36
								61
								70
								87
								70
								92
								12
								109
								12
								126
								38
								158
								59
								186
								264
								186
								301
								168
								311
								140
								268
								114
								268
								0
								319
								0
								319
								189
								0
								189
								0
								0
								247
								0
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 149 100 191 100 191 108 149 108
							yourself:
						)
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGrindBones of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(pestle signal: (| (pestle signal?) $0001))
				(= register (ego cycleSpeed?))
				(ego
					view: 480
					setPri: (+ (ego priority?) 10)
					setLoop: 9 1
					setCel: 0
					setSpeed: 6
					setCycle: CT 1 1 self
				)
				(pestle hide:)
			)
			(1 (ego setCycle: End self))
			(2
				(ego
					setLoop: 10 1
					setCel: 0
					setCycle: ForwardCounter 3 self
				)
				(pestle
					show:
					signal: (| (pestle signal?) $0001)
					view: 480
					x: (ego x?)
					y: (ego y?)
					z: -3
					setLoop: 11 1
					setCel: 0
					cycleSpeed: (ego cycleSpeed?)
					setScale:
					setPri: 250
					show:
					setCycle: Fwd
				)
			)
			(3
				(pestle setCycle: 0 hide:)
				(ego setLoop: 9 1 setCel: 2 setCycle: Beg self)
			)
			(4
				(ego solvePuzzle: 431 2)
				(pestle
					setLoop: 8 1
					setCel: 0
					setPri: 107
					x: 153
					y: 122
					z: 20
					scaleX: 128
					scaleY: 128
					show:
				)
				(= cycles 3)
			)
			(5
				(messager say: 13 4 111 0 self)
			)
			(6
				(ego normalize: 4 setSpeed: register)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance placePie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (ego cycleSpeed?))
				(ego
					view: 4
					setLoop: 1 1
					setCel: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(1
				(ego solvePuzzle: 432 6)
				(thePie init:)
				(ego setCycle: Beg self)
			)
			(2
				(ego
					view: 0
					setLoop: 1 1
					setCycle: Rev
					setMotion: MoveTo (+ (ego x?) 15) (ego y?) self
				)
			)
			(3
				(ego normalize: 1)
				(theLaser
					init:
					signal: (| (theLaser signal?) $0001)
					setLoop: 7 1
					setCel: 5
					setCycle: CT 2 -1 self
				)
			)
			(4
				(thePie
					signal: (| (thePie signal?) $0001)
					setCycle: CT 9 1 self
				)
				((inventory at: 28)
					maskCel: (| ((inventory at: 28) maskCel?) $0008)
				)
			)
			(5
				(thePie setCycle: End self)
				(theLaser setCycle: CT 5 1 theLaser)
			)
			(6
				(thePie signal: (& (thePie signal?) $fffe))
				(ego setMotion: MoveTo 121 154 self)
			)
			(7
				(ego view: 4 setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(8
				(messager say: 17 6 29 0 self)
			)
			(9
				(thePie dispose:)
				(ego setCycle: Beg self)
			)
			(10
				(messager say: 5 6 25 0 self)
			)
			(11
				(if (== (theHut x?) 124)
					(theHut
						signal: (| (theHut signal?) $0001)
						setLoop: 5 1
						illegalBits: 0
						ignoreActors:
						setMotion: MoveTo 54 56 self
					)
				else
					(= ticks 1)
				)
			)
			(12
				(theHut
					setLoop: 4 1
					setCel: 0
					signal: (| (theHut signal?) $0001)
					setCycle: End
				)
				(ego
					normalize: 0
					cycleSpeed: register
					setMotion: PolyPath 55 61 self
				)
			)
			(13
				(= global348 7)
				(theHut signal: (& (theHut signal?) $fffe))
				(curRoom setScript: sInHut)
			)
		)
	)
)

(instance theHut of Actor
	(properties
		x 54
		y 56
		view 480
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(45 (hutPerch doVerb: 45))
				(4 (return 1))
				(else  (super doVerb: theVerb))
			)
		)
	)
	
	(method (cue)
		(if (== global348 3) (messager say: 5 6 23))
		(self
			setLoop: 4 1
			setCel: 0
			signal: (& (theHut signal?) $fffe)
		)
	)
)

(instance thePie of Prop
	(properties
		x 94
		y 152
		view 480
		cel 3
		signal $4000
	)
)

(instance theBody of Prop
	(properties
		noun 4
		approachX 284
		approachY 141
		x 272
		y 139
		z 35
		view 480
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sPushBody)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if local0
			(= local0 0)
			(self setLoop: 2 1 signal: (& (self signal?) $fffe))
		else
			(= local0 1)
			(self setCycle: End self)
		)
	)
)

(instance theHat of View
	(properties
		noun 5
		sightAngle 180
		approachX 190
		approachY 175
		x 167
		y 166
		z 30
		priority 170
		fixPriority 1
		view 480
		cel 1
		signal $4000
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 159)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(44
				(curRoom setScript: sGiveHat)
			)
			(46
				(if
					(and
						(not (& ((inventory at: 28) maskCel?) $0008))
						(& ((inventory at: 28) maskCel?) $0001)
						(& ((inventory at: 28) maskCel?) $0002)
						(& ((inventory at: 28) maskCel?) $0004)
					)
					(messager say: 5 6 15)
					(= global348 7)
					((heroTeller stack?) dispose:)
					(heroTeller
						stack: ((intList new:) addToFront: 26)
						curNoun: 26
						rootNoun: 26
					)
					((boneTeller stack?) dispose:)
					(boneTeller
						stack: ((intList new:) addToFront: 26)
						curNoun: 26
						rootNoun: 26
					)
				else
					(super doVerb: theVerb)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theLaser of Prop
	(properties
		x 118
		y 166
		view 480
		signal $4000
		cycleSpeed 1
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 159)
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance pestle of Prop
	(properties
		approachX 153
		approachY 103
		x 153
		y 122
		z 20
		view 480
		loop 8
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(else  (mortar doVerb: theVerb))
		)
	)
)

(instance localCorn of View
	(properties
		view 480
		cel 2
		signal $4000
	)
)

(instance mortar of Feature
	(properties
		noun 13
		nsLeft 155
		nsTop 82
		nsRight 184
		nsBottom 107
		sightAngle 180
		approachX 151
		approachY 103
		x 165
		y 105
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (super doVerb: theVerb))
			(2 (super doVerb: theVerb))
			(4
				(cond 
					((Btst 157) (Bclr 157) (Bset 158) (curRoom setScript: sGrindBones))
					((Btst 158) (messager say: 13 4 110))
					(else (messager say: 13 4 109))
				)
			)
			(47
				(ego drop: 29 1)
				(Bset 157)
				(messager say: 13 47 0)
			)
			(24
				(cond 
					((Btst 157) (messager say: 13 24 111))
					((Btst 158)
						(Bclr 158)
						(ego drop: 9 1)
						(ego get: 12)
						(messager say: 17 6 110)
					)
					(else (messager say: 13 24 109))
				)
			)
			(27 (messager say: 13 27 0))
			(45 (messager say: 13 45 0))
			(else  (messager say: 17 6 112))
		)
	)
)

(instance boneHead of Feature
	(properties
		noun 5
		nsLeft 158
		nsTop 122
		nsRight 172
		nsBottom 139
		sightAngle 180
		approachX 190
		approachY 175
		x 165
		y 130
	)
	
	(method (doVerb theVerb)
		(theHat doVerb: theVerb)
	)
)

(instance laserSkull1 of Feature
	(properties
		noun 11
		nsLeft 17
		nsTop 84
		nsRight 27
		nsBottom 97
		sightAngle 180
		x 22
		y 90
	)
)

(instance gateSkull of Feature
	(properties
		noun 10
		nsLeft 42
		nsTop 97
		nsRight 50
		nsBottom 106
		sightAngle 180
		x 46
		y 101
	)
)

(instance laserSkull2 of Feature
	(properties
		noun 12
		nsLeft 147
		nsTop 127
		nsRight 158
		nsBottom 139
		sightAngle 180
		x 152
		y 133
	)
)

(instance threeSkulls of Feature
	(properties
		noun 15
		nsLeft 52
		nsTop 110
		nsRight 94
		nsBottom 129
		sightAngle 180
		approachX 121
		approachY 154
		x 73
		y 119
	)
	
	(method (doVerb theVerb)
		(if debugging
			(curRoom setScript: placePie)
		else
			(switch theVerb
				(46
					(if
						(and
							(not (& ((inventory at: 28) maskCel?) $0008))
							(& ((inventory at: 28) maskCel?) $0001)
							(& ((inventory at: 28) maskCel?) $0002)
							(& ((inventory at: 28) maskCel?) $0004)
						)
						(curRoom setScript: placePie)
					else
						(super doVerb: theVerb)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance fourSkulls of Feature
	(properties
		noun 9
		nsLeft 38
		nsTop 140
		nsRight 70
		nsBottom 169
		sightAngle 180
		approachX 121
		approachY 154
		x 54
		y 154
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 46)
			(threeSkulls doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance treeTop of Feature
	(properties
		noun 16
		nsLeft 90
		nsTop -1
		nsRight 319
		nsBottom 45
		sightAngle 180
		x 204
		y 22
	)
)

(instance treeTrunk of Feature
	(properties
		noun 16
		nsLeft 168
		nsTop 46
		nsRight 248
		nsBottom 103
		sightAngle 180
		x 208
		y 74
	)
)

(instance eggRock of Feature
	(properties
		noun 7
		nsLeft 209
		nsTop 94
		nsRight 248
		nsBottom 121
		sightAngle 180
		x 228
		y 107
	)
)

(instance exitArea of Feature
	(properties
		noun 8
		nsLeft 302
		nsTop 93
		nsRight 319
		nsBottom 157
		sightAngle 180
		x 310
		y 125
	)
)

(instance cliff of Feature
	(properties
		noun 6
		nsLeft 65
		nsTop 46
		nsRight 155
		nsBottom 69
		sightAngle 180
		x 110
		y 57
	)
)

(instance hutPerch of Feature
	(properties
		noun 30
		nsTop 46
		nsRight 104
		nsBottom 61
		sightAngle 40
		approachX 54
		approachY 57
		x 52
		y 53
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(45
					(curRoom setScript: dropCorn)
				)
				(4 (return 1))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance heroDist of DistObj
	(properties)
	
	(method (doVerb theVerb)
		(switch (super doVerb: theVerb)
			(theHat
				(heroTeller doVerb: theVerb)
			)
		)
	)
)

(instance intList of List
	(properties)
	
	(method (dispose)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(DisposeClone self)
	)
)
