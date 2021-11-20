;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerObj)
(use TellerIcon)
(use EgoDead)
(use Scaler)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm300 0
)

(local
	local0
	gTheObj_2CycleSpeed
	local2
	theTimeZone
)
(instance rm300 of GloryRm
	(properties
		noun 26
		picture 300
	)
	
	(method (init)
		(= local0
			(cond 
				((and (not (Btst 37)) (not (Btst 30))) 1)
				(
					(and
						(Btst 37)
						(not Night)
						(>= Day 5)
						(not (Btst 67))
						(not (Btst 48))
						(not (Btst 39))
					)
					4
				)
				(
					(and
						(not (Btst 43))
						(ego has: 19)
						(== ((inventory at: 19) state?) 3)
					)
					10
				)
				(
					(and
						(Btst 37)
						(Btst 67)
						(not Night)
						(not (Btst 39))
						(not (Btst 40))
						(not (Btst 48))
					)
					5
				)
				(
					(and
						(Btst 37)
						Night
						(not (Btst 48))
						(not (Btst 39))
						(not (Btst 40))
					)
					6
				)
				(
				(and (not (Btst 37)) (Btst 30) (not (Btst 47))) 2)
				((and (not (Btst 37)) (Btst 47)) 3)
				(
				(and (or (Btst 48) (Btst 39)) (not (Btst 40))) 7)
				((and (Btst 37) (not (Btst 40)) (>= Day 5)) 8)
				(
				(and (Btst 45) (not (Btst 68)) (> Day gCurrentDay_4)) 9)
			)
		)
		(theMusic number: 300 setLoop: -1 play:)
		(if (Btst 70)
			(ego init: view: 352 loop: 0 setCel: 10 x: 270 y: 155)
		else
			(ego
				init:
				normalize:
				posn: 335 184
				setScaler: Scaler 122 50 189 87
			)
		)
		(curRoom
			addObstacle:
				(cond 
					((>= timeODay 4)
						((Polygon new:)
							type: 3
							init:
								90
								128
								90
								140
								143
								140
								143
								146
								115
								154
								66
								154
								48
								142
								35
								147
								26
								147
								23
								128
								13
								128
								0
								141
								0
								189
								302
								189
								308
								184
								304
								179
								289
								179
								245
								135
								145
								135
								169
								112
								139
								112
								106
								128
							yourself:
						)
					)
					((not (Btst 32))
						((Polygon new:)
							type: 3
							init:
								90
								128
								90
								140
								142
								140
								142
								146
								115
								154
								66
								154
								0
								149
								0
								189
								302
								189
								308
								184
								304
								179
								290
								178
								274
								162
								212
								162
								219
								145
								255
								145
								245
								135
								145
								135
								169
								112
								139
								112
								122
								128
							yourself:
						)
					)
					(else
						((Polygon new:)
							type: 3
							init:
								90
								128
								90
								140
								141
								140
								141
								147
								115
								154
								66
								154
								52
								160
								0
								160
								0
								189
								302
								189
								308
								184
								304
								179
								289
								179
								245
								135
								145
								135
								169
								112
								139
								112
								124
								128
							yourself:
						)
					)
				)
		)
		(self
			addPoly:
				((Polygon new:)
					init: 243 139 258 160 237 172 222 148
					yourself:
				)
				120
				((Polygon new:)
					init: 136 110 179 111 163 129 125 116
					yourself:
				)
				50
		)
		(cond 
			((Btst 32)
				(if (<= timeODay 3)
					(burgoMeister
						init:
						posn: 40 154
						loop: 1
						approachX: 72
						approachY: 166
						approachVerbs: 4 2
						setScript: sBurgoIncidental
					)
				)
			)
			((<= timeODay 3)
				(burgoMeister
					init:
					approachX: 205
					approachY: 172
					approachVerbs: 4 2
					setCycle: End
				)
				(Bset 32)
			)
		)
		(shield init: approachVerbs: 4)
		(if (Btst 43) (shield setCel: 1))
		(bedroomDoor init: approachVerbs: 4)
		(chair init: approachVerbs: 4)
		(desk init: approachVerbs: 4 42 28 80)
		(cellDoor init: approachVerbs: 4 29 42 28 80)
		(stairs init: approachVerbs: 4)
		(spears init: approachVerbs: 4)
		(burgoWindow init: approachVerbs: 4)
		(mainDoor init: approachVerbs: 4)
		(if (Btst 70) (mainDoor cel: 0))
		(if (OneOf local0 4 5 6)
			(gypsy init: approachVerbs: 4 2 29 42 28 80)
		)
		(super init: &rest)
		(heroTeller
			init:
				ego
				300
				25
				128
				(switch local0
					(1 16)
					(2 17)
					(4 19)
					(5 20)
					(3 18)
					(6 13)
					(7 21)
					(9 23)
					(else  22)
				)
		)
		(if (OneOf local0 4 5)
			(ego
				actions: (myDist init: gypsy 60 burgoMeister 60 yourself:)
			)
		)
		(if (Btst 70)
			(self setScript: sEnterWin)
		else
			(self setScript: sEnterScr)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(!= theTimeZone timeODay)
				(== (= theTimeZone timeODay) 0)
			)
			(curRoom setScript: sCaughtByBurgo)
		)
	)
	
	(method (dispose)
		(DisposeScript 9)
		(heroTeller dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(81
				(if Night
					(cond 
						(
						(and (cast contains: gypsy) (== (shield cel?) 0)) (messager say: 0 81 61))
						((cast contains: gypsy) (messager say: 0 81 54))
						((== (shield cel?) 0) (messager say: 0 81 60))
						(else (messager say: 0 81 0))
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
	
	(method (newRoom)
		(theMusic fade: 0)
		(super newRoom: &rest)
	)
	
	(method (leaveRoom)
		(if (cast contains: burgoMeister)
			(switch local0
				(1
					(Bset 30)
					(messager say: 15 6 17)
				)
				(2
					(Bset 47)
					(messager say: 15 6 18)
				)
				(3 (messager say: 15 6 20))
				(4 (messager say: 15 6 22))
				(5 (DailyMsg 15 6 24))
				(7 (messager say: 15 6 29))
				(8 (messager say: 15 6 31))
				(9 (messager say: 15 6 33))
				(10 (messager say: 15 6 35))
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
				(= seconds 2)
			)
			(1
				(ego setMotion: MoveTo 279 184 self)
			)
			(2
				(mainDoor setCycle: Beg self)
			)
			(3
				(theMusic2 number: 961 loop: 1 play:)
				(= seconds 1)
			)
			(4
				(switch local0
					(1
						(messager say: 15 6 14 0 self)
					)
					(2
						(messager say: 15 6 15 0 self)
					)
					(4
						(Bset 67)
						(messager say: 15 6 21 0 self)
					)
					(5 (DailyMsg 15 6 19 self))
					(7
						(messager say: 15 6 25 0 self)
					)
					(8
						(messager say: 15 6 30 0 self)
					)
					(9
						(Bset 68)
						(messager say: 15 6 32 0 self)
					)
					(else  (DailyMsg 15 6 19 self))
				)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(Bset 358)
				(Bclr 70)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego cycleSpeed: 9 setCycle: Beg self)
			)
			(2
				(ego
					normalize: 6
					x: 267
					y: 155
					cycleSpeed: gTheObj_2CycleSpeed
					setScaler: Scaler 122 50 189 87
					setMotion: MoveTo 249 163 self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLetGypsyOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego view: 31 setLoop: 1 1 cel: 0 setCycle: CT 2 1 self)
			)
			(1
				(messager say: 11 6 6 0 self)
			)
			(2
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(openDoor init:)
				(gypsy
					z: 0
					posn: 122 117
					setPri: -1
					setLoop: 6 1
					setCycle: Walk
					setScaler: Scaler 118 77 181 112
				)
				(ego setCycle: Beg self)
			)
			(3
				(messager say: 12 6 7 0 self)
			)
			(4
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								90
								128
								90
								140
								142
								140
								142
								146
								115
								154
								66
								154
								48
								142
								37
								145
								18
								145
								18
								128
								13
								128
								0
								141
								0
								189
								302
								189
								308
								184
								304
								179
								290
								178
								274
								162
								212
								162
								219
								145
								255
								145
								245
								135
								145
								135
								156
								124
								130
								124
								130
								128
							yourself:
						)
				)
				(ego normalize: 1)
				(Palette palSET_FLAG 66 85 50)
				(gypsy setMotion: MoveTo 150 141 self)
				(Face ego 160 289 self)
			)
			(5 0)
			(6
				(gypsy setMotion: MoveTo 256 157 self)
			)
			(7
				(gypsy
					view: 301
					setLoop: 3 1
					cel: 0
					x: 264
					y: 155
					setCycle: End self
				)
			)
			(8
				(Bset 39)
				(gypsy dispose:)
				(ego addHonor: 200 solvePuzzle: 515 6 4 normalize: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOutMainDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom leaveRoom:)
				(= cycles 1)
			)
			(1
				(mainDoor setCycle: End self)
			)
			(2
				(theMusic2 number: 960 loop: 1 play:)
				(ego setMotion: MoveTo 335 184 self)
			)
			(3
				(theGame handsOn:)
				(curRoom newRoom: 260)
			)
		)
	)
)

(instance sOutWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 70)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 352
					loop: 0
					cel: 0
					x: 270
					y: 155
					cycleSpeed: 9
					setCycle: End self
				)
			)
			(1
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(curRoom newRoom: 260)
			)
		)
	)
)

(instance sGetShield of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 15 6 34 0 self)
			)
			(1
				(shield setCel: 1)
				(Bset 43)
				(ego addHonor: 250)
				(ego solvePuzzle: 529 15 8)
				((inventory at: 18) state: 1)
				((burgoMeister actions?) dispose:)
				((ego actions?) dispose:)
				(= cycles 2)
			)
			(2
				(heroTeller init: ego 300 25 128 24)
				(burgoTeller init: burgoMeister 300 25 131 24)
				(= cycles 2)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBurgoIncidental of Script
	(properties)
	
	(method (doit)
		(if (and (ego mover?) (!= state 4) (!= state 0))
			(cond 
				((< (ego x?) 48) (burgoMeister setCel: 0))
				((< (ego x?) 115) (burgoMeister setCel: 1))
				(else (burgoMeister setCel: 2))
			)
			(= state 0)
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 0 (= seconds 3))
			(1
				1
				(burgoMeister setLoop: 1 1)
				(= seconds (Random 2 8))
			)
			(2
				2
				(burgoMeister
					setCel:
						(if (== (burgoMeister cel?) 3)
							0
						else
							(+ (burgoMeister cel?) 1)
						)
				)
				(if (== (++ local2) 35) (= state 3))
				(= cycles 1)
			)
			(3 3 (self changeState: 0))
			(4
				4
				(= local2 (= state 0))
				(burgoMeister setLoop: 2 1 setCycle: End self)
			)
		)
	)
)

(instance sCaughtByBurgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1 (EgoDead 114 0 0 0 912))
		)
	)
)

(instance heroTeller of Teller
	(properties)
	
	(method (sayMessage)
		(switch iconValue
			(34
				(self clean:)
				(curRoom setScript: sGetShield)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super
			showCases:
				34
				(if (and (not (Btst 43)) (ego has: 19))
					(== ((inventory at: 19) state?) 3)
				else
					0
				)
		)
	)
)

(instance bedroomTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (cast contains: burgoMeister)
					(messager say: 1 4 112)
				else
					(super doVerb: theVerb)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(11
				(if (OneOf local0 4 5 6)
					(EgoDead 54 300 970 1)
				else
					(EgoDead 111 300 970 1)
				)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
)

(instance myDist of DistObj
	(properties)
	
	(method (doVerb theVerb)
		(switch (super doVerb: theVerb)
			(gypsy
				(heroTeller verb: 140)
				(heroTeller doVerb: theVerb)
			)
			(else 
				(heroTeller verb: 182)
				(heroTeller doVerb: theVerb)
			)
		)
	)
)

(instance burgoTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 65 0))
	)
	
	(method (sayMessage)
		(switch iconValue
			(55 (ego get: 61) (Bset 183))
		)
		(super sayMessage: &rest)
	)
	
	(method (showCases)
		(super
			showCases:
				55
				(if (OneOf local0 2 3) (not (Btst 183)) else 0)
				56
				(if (OneOf local0 2 3) (Btst 183) else 0)
		)
	)
)

(instance gypsyTeller of Teller
	(properties
		title 1
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 92 0))
	)
)

(instance gypsy of Actor
	(properties
		noun 12
		approachX 153
		approachY 124
		x 115
		y 122
		z 50
		fixPriority 1
		view 300
		loop 1
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(gypsyTeller
			init: self 300 25 139
			(switch local0
				(4 19)
				(5 20)
				(6 13)
			)
		)
	)
	
	(method (doVerb theVerb)
		(cellDoor doVerb: theVerb &rest)
	)
)

(instance openDoor of View
	(properties
		approachX 153
		approachY 124
		x 119
		y 118
		z 50
		priority 1
		fixPriority 1
		view 300
		signal $4000
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 1) (messager say: 6 1 9))
			((== theVerb 4) (messager say: 6 4 9))
			((== theVerb 29)
				(if (ego has: 63)
					(messager say: 6 29 9)
				else
					(messager say: 6 29 8)
				)
			)
			((OneOf theVerb 42 28) (messager say: 6 4 9))
			((== theVerb 80) (messager say: 6 80 9))
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance shield of View
	(properties
		noun 2
		sightAngle 180
		x 71
		y 87
		view 300
		loop 4
		signal $4000
	)
)

(instance burgoMeister of Prop
	(properties
		noun 15
		x 248
		y 161
		view 301
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(if (Btst 32)
			(self setPri: 130)
		else
			(self setPri: 161)
		)
		(burgoTeller
			init:
				burgoMeister
				300
				25
				131
				(switch local0
					(1 16)
					(2 17)
					(4 19)
					(3 18)
					(5 20)
					(7 21)
					(9 23)
					(else  22)
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(36
				(if
					(and
						(not (Btst 43))
						(== ((inventory at: 19) state?) 3)
					)
					(curRoom setScript: sGetShield)
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

(instance mainDoor of Prop
	(properties
		noun 10
		nsLeft 293
		nsTop 71
		nsRight 319
		nsBottom 187
		sightAngle 180
		approachX 279
		approachY 184
		x 305
		y 90
		priority 53
		fixPriority 1
		view 300
		loop 5
		cel 4
		signal $4001
	)
	
	(method (init)
		(super init: &rest)
		(if (cast contains: burgoMeister)
			(= heading
				(((ScriptID 49 0) new:)
					init:
						((Polygon new:)
							type: 1
							init: 316 175 316 189 306 189 292 175
							yourself:
						)
						6
						0
						4
						sOutMainDoor
					yourself:
				)
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sOutMainDoor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance bedroomDoor of Feature
	(properties
		noun 1
		nsLeft 5
		nsTop 67
		nsRight 30
		nsBottom 125
		sightAngle 180
		x 17
		y 96
	)
	
	(method (init)
		(super init: &rest)
		(bedroomTeller init: self 300 25 137 1)
	)
)

(instance chair of Feature
	(properties
		noun 3
		nsLeft 54
		nsTop 88
		nsRight 70
		nsBottom 141
		sightAngle 180
		x 62
		y 114
	)
)

(instance desk of Feature
	(properties
		noun 4
		nsLeft 72
		nsTop 114
		nsRight 128
		nsBottom 152
		sightAngle 180
		approachX 90
		approachY 139
		x 100
		y 150
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 4)
				(if (cast contains: burgoMeister)
					(super doVerb: theVerb &rest)
				else
					(messager say: 11 6 59)
				)
			)
			((OneOf theVerb 28 42)
				(cond 
					((cast contains: burgoMeister) (messager say: 4 4 0))
					((Btst 71) (messager say: 4 42 3))
					((== (ego trySkill: 9 275) 1)
						(ego get: 63 get: 0 7 addHonor: -100)
						(= kopeks (+ kopeks 15))
						(Bset 71)
						(if (== local0 6)
							(messager say: 4 42 5)
						else
							(Bset 10)
							(messager say: 4 42 4)
						)
					)
					(else (messager say: 4 42 2))
				)
			)
			((== theVerb 80)
				(cond 
					((cast contains: burgoMeister) (messager say: 4 4 0))
					((Btst 71) (messager say: 4 42 3))
					((ego castSpell: 20)
						(= projX 85)
						(= projY 120)
						(curRoom setScript: (ScriptID 13 0) 0 self)
					)
				)
			)
			((== theVerb -80)
				(curRoom setScript: 0)
				(theGame handsOn:)
				(ego get: 63 get: 0 7 addHonor: -100)
				(= kopeks (+ kopeks 15))
				(Bset 71)
				(if (== local0 6)
					(messager say: 4 42 5)
				else
					(Bset 10)
					(messager say: 4 42 4)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance cellDoor of Feature
	(properties
		noun 6
		nsLeft 116
		nsTop 71
		nsRight 166
		nsBottom 129
		sightAngle 180
		approachX 153
		approachY 124
		x 141
		y 100
	)
	
	(method (doVerb theVerb)
		(cond 
			((OneOf theVerb 28 42)
				(if (== (ego trySkill: 9 300) 1)
					(if (== local0 6)
						(curRoom setScript: sLetGypsyOut)
					else
						(messager say: 6 4 113)
					)
				else
					(messager say: 4 42 2)
				)
			)
			((== theVerb 29)
				(if (ego has: 63)
					(if (== local0 6)
						(messager say: 6 29 10)
						(curRoom setScript: sLetGypsyOut)
					else
						(messager say: 6 4 113)
					)
				else
					(messager say: 6 29 8)
				)
			)
			((== theVerb 80)
				(if (ego castSpell: 20)
					(= projX 121)
					(= projY 96)
					(curRoom setScript: (ScriptID 13 0) 0 self)
				)
			)
			((== theVerb -80)
				(if (== local0 6)
					(messager say: 6 80 10)
					(curRoom setScript: sLetGypsyOut)
				else
					(messager say: 6 4 113)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance stairs of Feature
	(properties
		noun 7
		nsTop 123
		nsRight 29
		nsBottom 143
		sightAngle 180
		x 14
		y 133
	)
)

(instance spears of Feature
	(properties
		noun 8
		nsLeft 188
		nsTop 58
		nsRight 233
		nsBottom 131
		sightAngle 180
		approachX 213
		approachY 136
		x 210
		y 94
	)
)

(instance burgoWindow of Feature
	(properties
		noun 9
		nsLeft 261
		nsTop 76
		nsRight 285
		nsBottom 141
		sightAngle 180
		approachX 267
		approachY 155
		x 273
		y 108
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (cast contains: burgoMeister)
					(messager say: 11 6 58)
				else
					(curRoom setScript: sOutWin)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
