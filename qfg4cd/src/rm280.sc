;;; Sierra Script 1.0 - (do not remove this comment)
(script# 280)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm280 0
)

(local
	local0
	gTheObj_2Y
	local2
	local3
	gTheObj_2CycleSpeed
)
(instance rm280 of GloryRm
	(properties
		picture 280
		horizon 90
		north 250
		south 270
		west 260
		topX 128
	)
	
	(method (init)
		(= local3
			(cond 
				((== gCurrentDay_4 0) 1)
				(
				(and (>= Day (+ gCurrentDay_4 1)) (not (Btst 46))) 2)
				((and (>= Day (+ gCurrentDay_4 1)) (Btst 46)) 3)
				(else 0)
			)
		)
		(Bclr 26)
		(if (not (OneOf prevRoomNum 250 260))
			(theMusic number: 250 setLoop: -1 play:)
		)
		(ego init: normalize: setScaler: Scaler 122 50 189 87)
		(switch prevRoomNum
			(250
				(ego posn: 121 90)
				(= local0 125)
				(= gTheObj_2Y 98)
			)
			(260
				(ego x: 8)
				(= local0 (+ (ego x?) 20))
				(= gTheObj_2Y (ego y?))
			)
			(270
				(ego posn: 92 240)
				(= local0 92)
				(= gTheObj_2Y 175)
			)
			(380
				(ego loop: 5 posn: 260 115 setPri: 0)
			)
			(390
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					setLoop: 3 1
					cel: 0
					posn: 72 90
					setSpeed: 15
					setScaler: Scaler 122 83 189 126
					setCycle: Walk
				)
			)
			(else  (ego posn: 87 165))
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 122 0 123 113 82 124 66 124 54 127 75 127 75 146 24 151 0 160
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						619
						189
						163
						189
						163
						176
						278
						176
						289
						143
						260
						143
						260
						132
						244
						132
						244
						124
						237
						124
						237
						131
						216
						131
						148
						123
						148
						115
						165
						115
						128
						87
						128
						0
						619
						0
					yourself:
				)
		)
		(self
			addPoly:
				((Polygon new:)
					init: 97 87 137 87 180 104 175 120 82 120 103 113
					yourself:
				)
				50
				((Polygon new:)
					init: 319 130 319 189 102 189 155 182 209 141 256 149 303 130
					yourself:
				)
				75
				((Polygon new:)
					init: 205 128 288 131 275 139 214 139 161 150 70 188 24 169 126 136
					yourself:
				)
				120
		)
		(lowWall init:)
		(rope init: setPri: 34 approachVerbs: 4)
		(oldManDoor init: setPri: 17 approachVerbs: 4 42 28)
		(oldManWin init: approachVerbs: 4)
		(oldManBrick init: approachVerbs: 4)
		(craniumDoor init: setPri: 17 approachVerbs: 4 42 28)
		(craniumWin1 init: approachVerbs: 4)
		(craniumWin2 init:)
		(craniumSign init:)
		(nose init:)
		(mustache init:)
		(lantern1 init:)
		(lantern2 init:)
		(tree init:)
		(archWay init:)
		(if Night (lamp1 init:) (lamp2 init:))
		(if
			(and
				(not (Btst 45))
				(!= prevRoomNum 390)
				(<= timeODay 3)
			)
			((ScriptID 84 0)
				init:
				approachVerbs: 4 2
				setScaler: Scaler 111 75 189 108
				setScript: (ScriptID 84 1)
			)
			(heroTeller
				init: ego 280 13 128
				(switch local3
					(1 14)
					(2 15)
					(else  16)
				)
			)
			(oldManTeller
				init:
					(ScriptID 84 0)
					280
					13
					136
					(switch local3
						(1 14)
						(2 15)
						(else  16)
					)
			)
		)
		(super init: &rest)
		(cond 
			((== prevRoomNum 380) (self setScript: sOutCraniumDoor))
			((== prevRoomNum 390) (self setScript: sClimbOutOldMans))
			((OneOf prevRoomNum 250 260 270) (self setScript: sEnterScr))
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (== (curRoom script?) (ScriptID 31 1))
			(ego setScaler: Scaler 122 83 189 126)
			(Palette palSET_FLAG 66 85 100)
			(if (>= (ego z?) 20) (self setScript: sLevIntoOldMans))
		)
	)
	
	(method (dispose)
		(DisposeScript 82)
		(DisposeScript 84)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(89
				(if
					(and
						(>= timeODay 4)
						(not (cast contains: (ScriptID 84 0)))
					)
					((ScriptID 31 0) init: 96 120 100 0)
				else
					(messager say: 12 6 34)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(if (OneOf n 390 370 270) (theMusic fade: 0))
		(super newRoom: n &rest)
	)
)

(instance sEnterScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath local0 gTheObj_2Y self)
			)
			(1
				(switch local3
					(2 (Bset 46) (= cycles 1))
					(else  (= cycles 1))
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOutCraniumDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(theMusic2 number: 960 loop: 1 play:)
				(craniumDoor setCycle: End self)
			)
			(2
				(ego setPri: -1 setMotion: MoveTo 230 137 self)
			)
			(3
				(craniumDoor setCycle: Beg self)
			)
			(4
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 2)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoBellPuzzle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				((ScriptID 82 0) init: show: dispose:)
				(DisposeScript 82)
				(= cycles 3)
			)
			(1
				1
				(theGame handsOff:)
				(if (Btst 26)
					(theMusic2 number: 960 loop: 1 play:)
					(craniumDoor setCycle: End self)
				else
					(= state 5)
					(ego setPri: -1 setMotion: MoveTo 230 137 self)
				)
			)
			(2
				2
				(ego setPri: 51 setMotion: MoveTo 260 115 self)
			)
			(3
				3
				(ego setPri: 0)
				(craniumDoor setCycle: Beg self)
			)
			(4
				4
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 2)
			)
			(5 5 (curRoom newRoom: 380))
			(6
				6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sNaughtyFlash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(naughtyProp init: setCycle: End self)
			)
			(1
				(naughtyProp setCycle: Beg self)
			)
			(2
				(naughtyProp dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sHandOnBrick of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 87 124 self)
			)
			(1 (ego loop: 7) (= cycles 1))
			(2
				(oldManBrick setCycle: End self)
			)
			(3
				(messager say: 12 6 39 0 self)
			)
			(4
				(oldManBrick setCycle: Beg self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTellOfAnna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								(- (ego x?) 7)
								(- (ego y?) 5)
								(+ (ego x?) 7)
								(- (ego y?) 5)
								(+ (ego x?) 7)
								(+ (ego y?) 5)
								(- (ego x?) 7)
								(+ (ego y?) 5)
							yourself:
						)
				)
				(= cycles 2)
			)
			(1
				(messager say: 13 128 17 0 self)
			)
			(2
				(if (ego actions?) ((ego actions?) dispose:))
				(Bset 45)
				((ScriptID 84 0)
					setScript: 0
					view: 284
					setLoop: -1
					setCycle: Walk
					ignoreActors: 1
					setMotion: PolyPath 100 195 self
				)
			)
			(3
				((ScriptID 84 0) setMotion: MoveTo 100 252 self)
			)
			(4
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= cycles 2)
			)
			(5
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init: 0 0 122 0 123 113 82 124 66 124 54 127 75 127 75 146 24 151 0 160
							yourself:
						)
						((Polygon new:)
							type: 2
							init:
								319
								189
								163
								189
								163
								176
								278
								176
								289
								143
								260
								143
								260
								132
								244
								132
								244
								124
								237
								124
								237
								131
								216
								131
								148
								123
								148
								115
								165
								115
								128
								87
								128
								0
								319
								0
							yourself:
						)
				)
				((ScriptID 84 0) hide:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sClimbInOldMans of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					setLoop: 3
					cel: 0
					setSpeed: 15
					setScaler: Scaler 122 83 189 126
					setCycle: Walk
					setMotion: MoveTo 72 90 self
				)
			)
			(1
				(Bset 70)
				(messager say: 13 134 5 0 self)
			)
			(2
				(ego setSpeed: gTheObj_2CycleSpeed)
				(curRoom newRoom: 390)
			)
		)
	)
)

(instance sClimbOutOldMans of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 75 127 self)
			)
			(1
				(Bclr 70)
				(ego
					normalize: 3
					setSpeed: gTheObj_2CycleSpeed
					setMotion: MoveTo 99 135 self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLevIntoOldMans of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego y: (- (ego y?) 20))
				(Bset 70)
				(curRoom north: 0)
				(= cycles 2)
			)
			(1 (curRoom newRoom: 390))
		)
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(= gCurrentDay_4 Day)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(if (== iconValue 17)
			(self clean:)
			(curRoom setScript: sTellOfAnna)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 17 (Btst 339))
	)
)

(instance oldManTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 64 0))
	)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(= gCurrentDay_4 Day)
		)
		(return 1)
	)
)

(instance doorTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(36
				(if (== (ego trySkill: 9 250) 1)
					(super sayMessage: 12 6 38 &rest)
				else
					(super sayMessage: &rest)
				)
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
			pushi    35
			pushi    #has
			pushi    1
			pushi    24
			lag      ego
			send     6
			bt       code_0e6a
			pushi    #has
			pushi    1
			pushi    13
			lag      ego
			send     6
			bnt      code_0e83
code_0e6a:
			pushi    #contains
			pushi    1
			pushi    2
			pushi    84
			pushi    0
			callk    ScriptID,  4
			push    
			lag      cast
			send     6
			bt       code_0e83
			lsg      timeODay
			ldi      3
			le?     
code_0e83:
			push    
			pushi    36
			pushi    #has
			pushi    1
			pushi    24
			lag      ego
			send     6
			bt       code_0ea0
			pushi    #has
			pushi    1
			pushi    13
			lag      ego
			send     6
			bnt      code_0eba
code_0ea0:
			pushi    #contains
			pushi    1
			pushi    2
			pushi    84
			pushi    0
			callk    ScriptID,  4
			push    
			lag      cast
			send     6
			not     
			bnt      code_0eba
			lsg      timeODay
			ldi      4
			ge?     
code_0eba:
			push    
			super    Teller,  12
			ret     
		)
	)
)

(instance rope of View
	(properties
		noun 18
		sightAngle 180
		approachX 240
		approachY 125
		x 233
		y 124
		view 280
		loop 6
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sDoBellPuzzle)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lamp1 of View
	(properties
		sightAngle 180
		x 201
		y 79
		view 280
		loop 7
		signal $4000
	)
)

(instance lamp2 of View
	(properties
		sightAngle 180
		x 289
		y 72
		view 280
		loop 8
		signal $4000
	)
)

(instance oldManDoor of Prop
	(properties
		noun 2
		sightAngle 180
		approachX 64
		approachY 127
		x 43
		y 73
		view 280
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(doorTeller init: self 280 13 133)
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 28 42)
			(cond 
				((Btst 45) (messager say: 2 42 8))
				(
				(or (cast contains: (ScriptID 84 0)) (not Night)) (messager say: 2 42 1))
				(else (messager say: 2 42 2))
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance oldManWin of Prop
	(properties
		noun 3
		sightAngle 180
		approachX 75
		approachY 127
		x 78
		y 47
		fixPriority 1
		view 280
		loop 1
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if
					(or
						(and (Btst 45) Night)
						(and Night (not (cast contains: (ScriptID 84 0))))
					)
					(if (== (ego trySkill: 11 225) 1)
						(curRoom setScript: sClimbInOldMans)
					else
						(messager say: 12 6 9)
					)
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

(instance craniumDoor of Prop
	(properties
		noun 4
		sightAngle 180
		x 235
		y 69
		view 280
		loop 3
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 397)
					(Bset 26)
					(sDoBellPuzzle start: 1)
					(curRoom setScript: sDoBellPuzzle)
				else
					(curRoom setScript: sDoBellPuzzle)
				)
			)
			(28
				(self setScript: sNaughtyFlash)
			)
			(42
				(self setScript: sNaughtyFlash)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance naughtyProp of Prop
	(properties
		x 205
		y 51
		fixPriority 1
		view 280
		loop 4
		signal $4001
	)
)

(instance oldManBrick of Prop
	(properties
		sightAngle 180
		x 69
		y 91
		fixPriority 1
		view 280
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(self setScript: sHandOnBrick)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance craniumWin1 of Prop
	(properties
		noun 8
		sightAngle 180
		approachX 185
		approachY 127
		x 182
		y 41
		fixPriority 1
		view 280
		loop 5
		signal $4001
	)
)

(instance craniumWin2 of Feature
	(properties
		noun 8
		nsLeft 290
		nsTop 14
		nsRight 319
		nsBottom 42
		sightAngle 180
		approachX 288
		approachY 144
		x 304
		y 28
	)
	
	(method (doVerb theVerb)
		(craniumWin1 doVerb: theVerb &rest)
	)
)

(instance craniumSign of Feature
	(properties
		noun 9
		nsLeft 204
		nsTop 1
		nsRight 236
		nsBottom 27
		sightAngle 180
		x 220
		y 14
	)
)

(instance lowWall of Feature
	(properties
		noun 1
		nsLeft 192
		nsTop 157
		nsRight 319
		nsBottom 189
		sightAngle 180
		x 255
		y 173
	)
)

(instance nose of Feature
	(properties
		noun 5
		nsLeft 210
		nsTop 31
		nsRight 238
		nsBottom 51
		sightAngle 180
		x 224
		y 41
	)
)

(instance mustache of Feature
	(properties
		noun 6
		nsLeft 204
		nsTop 57
		nsRight 274
		nsBottom 71
		sightAngle 180
		x 239
		y 64
	)
)

(instance lantern1 of Feature
	(properties
		noun 7
		nsLeft 194
		nsTop 63
		nsRight 206
		nsBottom 80
		sightAngle 180
		x 200
		y 71
	)
)

(instance lantern2 of Feature
	(properties
		noun 7
		nsLeft 281
		nsTop 51
		nsRight 296
		nsBottom 73
		sightAngle 180
		x 288
		y 62
	)
)

(instance tree of Feature
	(properties
		noun 10
		nsTop -1
		nsRight 54
		nsBottom 145
		sightAngle 180
		x 27
		y 72
	)
)

(instance archWay of Feature
	(properties
		noun 11
		nsLeft 107
		nsTop 29
		nsRight 161
		nsBottom 102
		sightAngle 180
		x 134
		y 65
	)
)
