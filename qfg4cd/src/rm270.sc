;;; Sierra Script 1.0 - (do not remove this comment)
(script# 270)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use GloryTalker)
(use Intrface)
(use Scaler)
(use Osc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Reverse)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm270 0
	sSleepScr 1
	piotyrTalker 2
)

(local
	eventNum
	gTheObj_2CycleSpeed
	local2
)
(instance rm270 of GloryRm
	(properties
		picture 270
		north 260
		east 280
		west 260
	)
	
	(method (init)
		(if debugging
			(= eventNum (GetNumber {Event #?}))
		else
			(= eventNum
				(cond 
					((and Night (== prevRoomNum 650)) 1)
					(
						(and
							Night
							(== heroType 3)
							(== piotyrVisits 0)
							(!= piotyrDay Day)
						)
						2
					)
					(
						(and
							Night
							(== heroType 3)
							(not (Btst 117))
							(!= piotyrDay Day)
						)
						3
					)
					(
						(and
							Night
							(== heroType 3)
							(!= piotyrDay Day)
							(not (Btst 62))
							(Btst 117)
						)
						4
					)
					(
						(and
							Night
							(== heroType 3)
							(!= piotyrDay Day)
							(Btst 62)
							(not (Btst 115))
							(not (Btst 43))
						)
						5
					)
					(
						(and
							Night
							(== heroType 3)
							(!= piotyrDay Day)
							(not (Btst 115))
						)
						6
					)
					(
						(and
							Night
							(== heroType 3)
							(!= piotyrDay Day)
							(Btst 115)
						)
						7
					)
					(else 0)
				)
			)
		)
		(theGame handsOff:)
		(ego setScaler: Scaler 126 49 189 47)
		(super init:)
		(if (!= prevRoomNum 110)
			(theMusic number: 790 setLoop: -1 play:)
		)
		(ego init: normalize:)
		(= global423 0)
		(if (and Night (not (== prevRoomNum 110)))
			(= local2 1)
			(fSouth init:)
			(gateTeller init: fSouth 270 9 125)
		else
			(curRoom south: 290)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 167 55 166 87 189 0 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 257 189 273 167 319 167 319 189
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 239 93 236 105 189 122 93 108 88 93
					yourself:
				)
		)
		(if (not (Btst 115))
			(aStaff init: approachVerbs: 4 ignoreActors: setPri: 108)
		)
		(fFlowers init: approachVerbs: 4)
		(fMound init: approachVerbs: 4)
		(fArchWay init: approachVerbs: 4)
		(fTreeKnot init: approachVerbs: 4)
		(fGrass init: approachVerbs: 4)
		(fTreeLeft init: approachVerbs: 4)
		(fTreeRight init: approachVerbs: 4)
		(fBuildings init: approachVerbs: 4)
		(((ScriptID 6 0) new:)
			x: 28
			y: 67
			nsLeft: 0
			nsTop: 0
			nsBottom: 87
			nsRight: 168
			approachX: 91
			approachY: 85
			init:
		)
		((specialEFtr2 new:)
			x: 180
			y: 67
			nsLeft: 168
			nsTop: 0
			nsBottom: 87
			nsRight: 319
			approachX: 251
			approachY: 85
			init:
		)
		(ego setScript: sComeOnIn)
	)
	
	(method (doit)
		(if (and Night (not local2))
			(= local2 1)
			(fSouth init:)
			(gateTeller init: fSouth 270 9 125)
		)
		(super doit:)
	)
	
	(method (dispose)
		(theMusic fade: 0)
		(DisposeScript -597)
		(DisposeScript -545)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(85
					(messager say: 2 6 27)
					(return 1)
				)
				(86
					(messager say: 0 86 0)
					(return 1)
				)
				(88
					(messager say: 0 88 0)
					(return 1)
				)
				(79
					(messager say: 0 79 0)
					(return 1)
				)
				(95
					(messager say: 2 6 27)
					(return 1)
				)
				(91
					(messager say: 2 6 27)
					(return 1)
				)
				(93
					(messager say: 0 93 0)
					(return 1)
				)
				(80
					(messager say: 2 6 27)
					(return 1)
				)
				(98
					(messager say: 2 6 27)
					(return 1)
				)
				(83
					(messager say: 0 83 0)
					(return 1)
				)
				(90
					(messager say: 2 6 27)
					(return 1)
				)
				(84
					(messager say: 0 84 0)
					(return 1)
				)
				(87
					(messager say: 2 6 27)
					(return 1)
				)
				(82
					(messager say: 0 82 0)
					(return 1)
				)
				(92
					(messager say: 2 6 27)
					(return 1)
				)
				(97
					(messager say: 2 6 27)
					(return 1)
				)
				(96
					(messager say: 2 6 27)
					(return 1)
				)
				(11
					(messager say: 2 6 27)
					(return 1)
				)
				(102
					(messager say: 2 6 27)
					(return 1)
				)
				(94
					(if Night
						(curRoom setScript: sStaffAnimates)
					else
						(messager say: 2 6 27)
					)
					(return 1)
				)
				(89
					(if Night
						(= global423 2)
						(if (ego castSpell: 29)
							(curRoom setScript: sClimbTheGate)
						else
							(return 0)
						)
					else
						(messager say: 2 6 27)
					)
					(return 1)
				)
				(104
					(curRoom setScript: (ScriptID 270 1))
				)
				(81
					(if (Btst 115)
						(messager say: 0 81 26)
					else
						(messager say: 8 81 0 0)
					)
				)
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance sClimbTheTree of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global423 1)
				(messager say: 13 4 11 0 self)
			)
			(1 (curRoom newRoom: 290))
		)
	)
)

(instance sClimbTheGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== global423 1) (ego useSkill: 11 200))
				(ego setMotion: MoveTo (ego x?) 210 self)
			)
			(1 (curRoom newRoom: 290))
		)
	)
)

(instance sSleepScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 141 134 self)
			)
			(1
				(ego
					view: 35
					setLoop: 0 1
					setCel: 0
					posn: 171 132
					setCycle: End self
				)
			)
			(2
				(= [egoStats 19] (ego maxMana:))
				(= [egoStats 18] (ego maxStamina:))
				(= [egoStats 17] (ego maxHealth:))
				(if (< dreamNum 10)
					(++ dreamNum)
					(curRoom newRoom: 110)
				else
					((ScriptID 7 4) init: 7 51)
					(= seconds 6)
				)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego view: 0 posn: 141 134 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTo290Night of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 6 10 0 self)
			)
			(1
				(ego
					setLoop: 2 1
					setCycle: Rev
					setMotion: PolyPath (ego x?) (- (ego y?) 6) self
				)
			)
			(2
				(ego normalize:)
				(gateTeller doVerb: 4)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTo260 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 26 47 self)
			)
			(1 (curRoom newRoom: 260))
		)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch prevRoomNum
					(650
						(ego setLoop: 6 1 posn: 140 150)
						(pToby init:)
						(pTanya init:)
						(= ticks 12)
					)
					(290
						(ego posn: 164 242 setMotion: MoveTo 162 163 self)
					)
					(260
						(ego posn: 91 85 setMotion: MoveTo 60 102 self)
					)
					(280
						(ego posn: 251 85 setMotion: MoveTo 251 102 self)
					)
					(110
						(ego view: 35 setLoop: 4 1 posn: 171 132)
						(self setScript: sAfterTheDream)
					)
					(else 
						(ego posn: 91 164)
						(= ticks 12)
					)
				)
			)
			(1
				(cond 
					((== prevRoomNum 650) (curRoom setScript: sChildSacrifice))
					((OneOf eventNum 2 3 4 5 6 7) (curRoom setScript: sEvent2))
					(else (theGame handsOn:) (self dispose:))
				)
			)
		)
	)
)

(instance sAfterTheDream of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 3)
			)
			(1
				(Palette palSET_FLAG 0 255 100)
				((ScriptID 7 4) init: 8)
				(= [egoStats 19] (ego maxMana:))
				(= [egoStats 18] (ego maxStamina:))
				(= [egoStats 17] (ego maxHealth:))
				(ego setCycle: 0)
				(= seconds 5)
			)
			(2
				(gateTeller dispose:)
				(fSouth actions: 0)
				(if (fSouth heading?)
					((fSouth heading?) dispose:)
					(fSouth heading: 0)
				)
				(theMusic
					number: 790
					setLoop: -1
					play: 0
					fade: 127 10 7 0
				)
				(= register (ego cycleSpeed?))
				(ego cycleSpeed: defaultCycles setCycle: End self)
			)
			(3
				(ego posn: 141 134 cycleSpeed: register normalize:)
				(if (OneOf dreamNum 7 9 10)
					(messager say: 2 6 8 0 self)
				else
					(messager say: 2 6 9 0 self)
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetFlowers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego view: 4 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(1
				(sndPullFlowers play:)
				(= seconds 3)
			)
			(2 (ego setCycle: Beg self))
			(3
				(messager say: 4 4 14 0 self)
			)
			(4
				(ego cycleSpeed: gTheObj_2CycleSpeed get: 40 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sStaffAnimates of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(sndMagic play:)
				(aStaff setLoop: 1 1 setCel: 0 setCycle: Osc 4 self)
			)
			(1
				(aStaff setLoop: 2 1 setCel: 0 setCycle: Osc 6 self)
			)
			(2
				(aStaff setLoop: 3 1 setCel: 0 setCycle: End self)
			)
			(3
				(if (> [egoStats 35] 0)
					(messager say: 2 6 20 0 self)
				else
					(self cue:)
				)
			)
			(4 (aStaff setCycle: Beg self))
			(5
				(aStaff setLoop: 0 1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sChildSacrifice of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic fade: 0)
				(theMusic2
					number: 110
					setLoop: -1
					play: 0
					fade: 127 10 7 0
				)
				((ScriptID 7 4) init: 2 15)
				(Bset 115)
				(= seconds 2)
			)
			(1 (messager say: 6 6 1 0 self))
			(2
				(if (> [egoStats 12] 5)
					(messager say: 2 6 20 0 self)
				else
					(self changeState: 5)
				)
			)
			(3
				(ego
					view: 14
					setLoop: 2 1
					setCel: 0
					posn: (+ (ego x?) 7) (ego y?)
					setCycle: End self
				)
			)
			(4 (ego setCycle: Beg self))
			(5
				(if (> [egoStats 12] 5)
					(ego normalize: setLoop: 6 1)
					(self cue:)
				else
					(messager say: 5 6 15 0 self)
				)
			)
			(6
				(sndMagic play:)
				(aStaff setLoop: 1 1 setCel: 0 setCycle: Osc 4 self)
			)
			(7
				(aStaff setLoop: 2 1 setCel: 0 setCycle: Osc 6 self)
			)
			(8
				(aStaff setLoop: 3 1 setCel: 0 setCycle: End self)
			)
			(9
				(messager say: 5 6 16 0 self)
			)
			(10
				(aStaff
					setLoop: 4 1
					setCel: 0
					setPri: 119
					ignoreActors: 1
					ignoreHorizon: 1
					setCycle: 0
					setMotion: MoveTo 212 59 self
				)
			)
			(11
				(messager sayRange: 5 6 17 1 2 self)
			)
			(12
				(pToby
					setLoop: 0 1
					setCel: 0
					cycleSpeed: 18
					setCycle: End self
				)
			)
			(13
				(messager sayRange: 5 6 17 3 9 self)
			)
			(14 (aStaff setCycle: End self))
			(15
				(aStaff setLoop: 5 1 setCel: 0 setCycle: CT 3 1 self)
			)
			(16
				(sndExplosive play:)
				(Palette palSET_FLAG 0 255 500)
				(Palette palSET_FLAG 0 255 100)
				(aStaff setCel: 4)
				(= ticks 12)
			)
			(17
				(pToby
					setLoop: 2 1
					setCel: 0
					cycleSpeed: 6
					setCycle: CT 4 1 self
				)
			)
			(18
				(sndLightning play:)
				(pLighting init: setCycle: Osc 1 self)
			)
			(19
				(sndLightning stop:)
				(pLighting hide:)
				(= seconds 2)
			)
			(20
				(pToby setCel: 4 setCycle: CT 7 1 self)
			)
			(21 (= seconds 5))
			(22
				(pToby hide: dispose:)
				(messager say: 5 6 18 0 self)
			)
			(23
				(aStaff setCel: 0 setMotion: JumpTo 167 149 self)
			)
			(24
				(sndThud play:)
				(ego normalize: setMotion: MoveTo 152 152 self)
			)
			(25
				(ego view: 4 setLoop: 0 1 setCel: 0 setCycle: End self)
			)
			(26
				(aStaff hide: dispose:)
				(ego setCycle: Beg self)
			)
			(27
				(if (!= heroType 1)
					(self cue:)
				else
					(messager say: 2 6 29 0 self)
				)
			)
			(28
				(ego normalize: setMotion: PolyPath 199 124 self)
			)
			(29
				(messager say: 6 6 19 0 self)
			)
			(30
				(cast eachElementDo: #hide)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(= seconds 2)
			)
			(31
				(messager say: 2 6 21 0 self)
			)
			(32
				(if (!= heroType 1)
					(ego get: 47)
				else
					(ego learn: 32 200)
				)
				(ego solvePuzzle: 450 25)
				(theMusic2 fade: 0)
				(= seconds 1)
			)
			(33 (curRoom newRoom: 330))
		)
	)
)

(instance sEvent2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 2 6 22 0 self)
			)
			(1
				(ego setMotion: PolyPath 91 164 self)
			)
			(2 (= seconds 3))
			(3
				(pPiotyr init: setCycle: End self)
			)
			(4 (Face ego 144 151 self))
			(5
				(ego normalize:)
				(= seconds 1)
			)
			(6
				(= piotyrDay Day)
				(++ piotyrVisits)
				(switch eventNum
					(2 (messager say: 1 6 1 0 self))
					(3
						(Bset 116)
						(messager say: 1 6 2 0 self)
					)
					(4 (messager say: 1 6 4 0 self))
					(5 (messager say: 1 6 5 0 self))
					(6 (messager say: 1 6 6 0 self))
					(7 (messager say: 1 6 7 0 self))
				)
			)
			(7 (pPiotyr setCycle: Beg self))
			(8
				(pPiotyr dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance aStaff of Actor
	(properties
		noun 5
		approachX 167
		approachY 126
		x 160
		y 34
		view 271
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				85
				86
				88
				79
				95
				91
				93
				80
				98
				83
				90
				84
				87
				82
				92
				97
				96
				11
				102
			)
			(curRoom doVerb: theVerb)
		else
			(switch theVerb
				(94
					(if Night
						(curRoom setScript: sStaffAnimates)
					else
						(messager say: 2 6 27)
					)
				)
				(2 (messager say: 5 126 0 0))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance pLighting of Prop
	(properties
		x 165
		y 123
		priority 163
		fixPriority 1
		view 272
		loop 1
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self setScaler: Scaler 85 70 120 81)
	)
)

(instance pToby of Prop
	(properties
		x 169
		y 123
		view 272
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self setScaler: Scaler 85 70 120 81)
	)
)

(instance pTanya of Prop
	(properties
		x 213
		y 122
		view 652
		loop 1
	)
)

(instance pPiotyr of Prop
	(properties
		x 144
		y 151
		view 273
	)
)

(instance fSouth of Feature
	(properties
		noun 3
		nsLeft 129
		nsTop 56
		nsRight 211
		nsBottom 75
		sightAngle 180
		x 170
		y 65
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 67 181 268 181 268 189 67 189
						yourself:
					)
					2
					4
					5
					sTo290Night
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fMound of Feature
	(properties
		noun 7
		nsLeft 129
		nsTop 56
		nsRight 211
		nsBottom 75
		sightAngle 180
		approachX 165
		approachY 109
		x 170
		y 75
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(OneOf
					theVerb
					85
					86
					88
					79
					95
					91
					93
					80
					98
					83
					90
					84
					87
					82
					92
					97
					96
					11
					102
				)
				(curRoom doVerb: theVerb)
			)
			((== theVerb 1)
				(if (Btst 115)
					(messager say: 7 1 26)
				else
					(messager say: 7 1 25)
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance fTreeLeft of Feature
	(properties
		noun 13
		sightAngle 40
		approachX 77
		approachY 180
		x 30
		y 95
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						0
						0
						160
						1
						116
						13
						80
						13
						61
						11
						49
						26
						43
						26
						29
						18
						17
						25
						9
						40
						18
						74
						25
						90
						18
						116
						29
						151
						67
						189
						0
						189
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(OneOf
					theVerb
					85
					86
					88
					79
					95
					91
					93
					80
					98
					83
					90
					84
					87
					82
					92
					97
					96
					11
					102
				)
				(curRoom doVerb: theVerb)
			)
			((OneOf theVerb 4 33)
				(if Night
					(if (== (ego trySkill: 11 200) 1)
						(curRoom setScript: sClimbTheTree)
					else
						(messager say: 13 4 0 0)
					)
				else
					(messager say: 13 4 28)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance fTreeRight of Feature
	(properties
		noun 14
		sightAngle 40
		approachX 256
		approachY 180
		x 285
		y 95
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						319
						2
						319
						189
						269
						189
						293
						152
						308
						106
						309
						86
						304
						67
						292
						46
						270
						40
						263
						20
						236
						26
						227
						22
						218
						9
						209
						9
						199
						2
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(OneOf
					theVerb
					85
					86
					88
					79
					95
					91
					93
					80
					98
					83
					90
					84
					87
					82
					92
					97
					96
					11
					102
				)
				(curRoom doVerb: theVerb)
			)
			((OneOf theVerb 4 33)
				(if Night
					(if (== (ego trySkill: 11 200) 1)
						(curRoom setScript: sClimbTheTree)
					else
						(messager say: 13 4 0 0)
					)
				else
					(messager say: 13 4 28)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance fFlowers of Feature
	(properties
		noun 4
		nsLeft 98
		nsTop 75
		nsRight 227
		nsBottom 114
		sightAngle 180
		approachX 159
		approachY 119
		x 162
		y 94
	)
	
	(method (doVerb theVerb)
		(cond 
			(
				(OneOf
					theVerb
					85
					86
					88
					79
					95
					91
					93
					80
					98
					83
					90
					84
					87
					82
					92
					97
					96
					11
					102
				)
				(curRoom doVerb: theVerb)
			)
			((== theVerb 4)
				(if (ego has: 40)
					(messager say: 4 4 13)
				else
					(curRoom setScript: sGetFlowers)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance fArchWay of Feature
	(properties
		noun 10
		nsLeft 10
		nsTop 26
		nsRight 30
		nsBottom 45
		sightAngle 180
		approachX 28
		approachY 52
		x 27
		y 33
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:) type: 1 init: 49 52 12 71 8 46 yourself:)
					1
					7
					5
					sTo260
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				85
				86
				88
				79
				95
				91
				93
				80
				98
				83
				90
				84
				87
				82
				92
				97
				96
				11
				102
			)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fTreeKnot of Feature
	(properties
		noun 11
		nsLeft 2
		nsTop 86
		nsRight 16
		nsBottom 107
		sightAngle 180
		x 9
		y 96
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				85
				86
				88
				79
				95
				91
				93
				80
				98
				83
				90
				84
				87
				82
				92
				97
				96
				11
				102
			)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fGrass of Feature
	(properties
		noun 12
		sightAngle 180
		approachX 250
		approachY 152
		approachDist 156
		x 93
		y 93
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						93
						93
						101
						105
						142
						111
						162
						115
						188
						115
						229
						104
						233
						93
						212
						73
						220
						72
						243
						78
						267
						92
						276
						105
						271
						117
						231
						137
						182
						148
						123
						146
						82
						133
						58
						118
						54
						99
						69
						85
						97
						74
						127
						70
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				85
				86
				88
				79
				95
				91
				93
				80
				98
				83
				90
				84
				87
				82
				92
				97
				96
				11
				102
			)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fBuildings of Feature
	(properties
		noun 15
		sightAngle 40
		approachX 250
		approachY 152
		approachDist 156
		x 158
		y 22
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						49
						27
						59
						12
						116
						14
						158
						1
						187
						1
						288
						49
						190
						58
						176
						51
						161
						53
						149
						52
						140
						58
						41
						48
						40
						26
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				85
				86
				88
				79
				95
				91
				93
				80
				98
				83
				90
				84
				87
				82
				92
				97
				96
				11
				102
			)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance specialEFtr2 of Feature
	(properties)
	
	(method (init)
		(if (not (curRoom exitList?))
			(curRoom exitList: (List new:))
		)
		((curRoom exitList?) add: self)
	)
	
	(method (doVerb theVerb)
		(curRoom doVerb: theVerb)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(if
				(and
					(<= nsLeft theObjOrX)
					(<= theObjOrX nsRight)
					(<= nsTop theY)
					(<= theY nsBottom)
				)
				(theGame handsOff:)
				(if (and approachX approachY)
					(curRoom north: 280)
					(ego
						setMotion: ((ScriptID 17) new:) approachX approachY
					)
				)
				(return 1)
			else
				0
			)
		)
	)
)

(instance gateTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (respond)
		(super respond: &rest)
		(if (or (not iconValue) (== iconValue -999))
			(sTo290Night cue:)
		)
		(return 1)
	)
	
	(method (sayMessage)
		(switch iconValue
			(11
				(if (== (ego trySkill: 11 200) 1)
					(= global423 1)
					(self clean:)
					(curRoom setScript: sClimbTheGate)
				else
					(messager say: 9 125 11)
					(self clean:)
					(sTo290Night cue:)
				)
			)
			(12
				(ego castSpell: 29)
				(= global423 2)
				(self clean:)
				(curRoom setScript: sClimbTheGate)
			)
			(else  (super sayMessage:))
		)
	)
	
	(method (showCases)
		(super
			showCases: 11 (> [egoStats 11] 5) 12 (> [egoStats 29] 5)
		)
	)
)

(instance piotyrTalker of GloryTalker
	(properties
		x 0
		y -2
		talkWidth 150
		view 296
		textX 140
		textY 20
	)
	
	(method (init)
		(super init: piotyrMouth 0 piotyrEyes piotyrFrame &rest)
	)
)

(instance piotyrFrame of View
	(properties
		y -2
		view 296
	)
)

(instance piotyrMouth of Prop
	(properties
		nsLeft 62
		nsTop 44
		x 62
		y 44
		view 296
		loop 1
	)
)

(instance piotyrEyes of Prop
	(properties
		nsLeft 63
		nsTop 38
		x 63
		y 38
		view 296
		loop 2
	)
)

(instance sndPullFlowers of Sound
	(properties
		number 967
	)
)

(instance sndMagic of Sound
	(properties
		number 932
	)
)

(instance sndExplosive of Sound
	(properties
		number 971
	)
)

(instance sndLightning of Sound
	(properties
		number 974
		loop -1
	)
)

(instance sndThud of Sound
	(properties
		number 964
	)
)

(instance sndMagic2 of Sound
	(properties
		number 934
	)
)

(instance sndDisappear of Sound
	(properties
		number 939
	)
)
