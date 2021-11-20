;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use Intrface)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Reverse)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm650 0
)

(local
	local0
	local1
	gTheObj_2CycleSpeed
	pDoorPriority
)
(instance rm650 of GloryRm
	(properties
		picture 650
		east 620
		rightY 118
	)
	
	(method (init)
		(if debugging
			(= local0 (GetNumber {Event #?}))
		else
			(= local0
				(cond 
					((Btst 115) 4)
					((not Night) 1)
					((and Night (not (Btst 143))) 2)
					((and Night (Btst 143)) 3)
					(else 0)
				)
			)
		)
		(ego
			x: 293
			y: 117
			setLoop: 1 1
			init:
			setScaler: Scaler 100 98 189 0
			ignoreActors: 1
			normalize:
		)
		(if
			(or
				(and (Btst 144) (== heroType 1) (> [egoStats 35] 0))
				(and (!= heroType 1) (Btst 144))
			)
			(= local1 1)
		)
		(if (Btst 143)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							320
							102
							267
							128
							225
							130
							189
							130
							177
							136
							129
							137
							114
							130
							77
							131
							63
							122
							38
							128
							16
							124
							0
							139
							0
							0
							320
							0
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 319 110 319 189 0 189 0 155 288 152 291 125
						yourself:
					)
			)
		else
			(pDoor init:)
			(fFloor init:)
			(if (not (Btst 145))
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								301
								101
								286
								107
								281
								114
								288
								124
								319
								115
								319
								189
								0
								189
								0
								0
								319
								0
								319
								93
							yourself:
						)
				)
			)
		)
		(if (!= local0 4) (aToby init: ignoreActors: 1))
		(vEntrance init:)
		(if (not Night)
			(vCoffin init: ignoreActors: 1)
		else
			(pRtCandle setCycle: Fwd init:)
			(pLtCandle setCycle: Fwd init:)
			(fireSound play:)
		)
		(switch local0
			(1
				(Bset 140)
				(curRoom setScript: sStayOut)
			)
			(2
				(pTanya init:)
				(tanyaTeller init: pTanya 650 3 151 2)
				(heroTeller init: ego 650 3 128 2)
				(tobyTeller init: aToby 650 3 126 2)
				(curRoom setScript: sComeIn)
			)
			(3
				(pTanya setLoop: 0 1 init:)
				(tanyaTeller init: pTanya 650 3 151 9)
				(heroTeller init: ego 650 3 128 9)
				(tobyTeller init: aToby 650 3 126 9)
				(curRoom setScript: sComeIn)
			)
			(4
				(curRoom setScript: sComeOnIn)
			)
		)
		(pDoor init: setCel: 0 setCycle: End)
		(super init: &rest)
		(FrameOut)
		(theMusic number: 650 setLoop: -1 play:)
		(fWindow init:)
		(fSnowGlobe init:)
		(fDancer init:)
		(fClock init:)
		(fSteps init:)
		(fPeasantDoll init:)
		(fDresser init:)
		(fClown init:)
		(fElephant init:)
		(fCastle1 init:)
		(fCastle2 init:)
		(fTheatre init:)
		(fPolkaHorse init:)
		(fDoor init:)
		(fCandelabra1 init:)
		(fCandelabra2 init:)
		(fCoffin init:)
		(fDrapery init:)
		(fRug init:)
		(theGame save: 1)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> (ego x?) 310)
			(curRoom newRoom: (curRoom east?))
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((Btst 115)
				(if (== theVerb 4)
					(messager say: 27 4 47 0 self)
				else
					(super doVerb: theVerb)
				)
			)
			((== theVerb 4)
				(if (not (Btst 143))
					(messager say: 27 4 42 0 self)
				else
					(messager say: 27 4 43 0 self)
				)
			)
			((== theVerb 37)
				(if (== (ego has: 5) 1)
					(messager say: 28 6 46)
				else
					(Bset 145)
					(if (Btst 143)
						(curRoom setScript: sNotNice)
					else
						(curRoom setScript: sStayOut)
					)
				)
			)
			((OneOf theVerb 21 83 86 88 79 93 82)
				(Bset 145)
				(if (Btst 143)
					(curRoom setScript: sNotNice)
				else
					(curRoom setScript: sStayOut)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
)

(instance sComeIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(if (== heroType 3)
					(messager say: 28 6 45 0 self)
				else
					(= seconds 2)
				)
			)
			(2
				(if (not (Btst 143))
					(aToby setLoop: 1 1 setCel: 0 setCycle: CT 8 1 self)
				else
					(self cue:)
				)
			)
			(3
				(if (not (Btst 143))
					(aToby setPri: 141 setCel: 9 setCycle: End)
					(pDoor setCycle: CT 2 -1 self)
				else
					(self cue:)
				)
			)
			(4
				(if (not (Btst 143))
					(closeDoorSound play:)
					(pDoor cycleSpeed: 1 setCycle: CT 0 -1 self)
				else
					(self cue:)
				)
			)
			(5
				(pDoor cycleSpeed: 6)
				(= seconds 3)
			)
			(6
				(if (not (Btst 143))
					(aToby setPri: 110)
					(pDoor setCel: 2 setCycle: End self)
				else
					(self cue:)
				)
			)
			(7
				(cond 
					((Btst 145) (curRoom setScript: sNotNice))
					((Btst 142) (messager say: 1 6 8 0 self))
					((Btst 143) (Bset 142) (messager say: 1 6 7 0 self))
					((Btst 141) (messager say: 1 6 4 0 self))
					(else (Bset 141) (curRoom setScript: sWhoseThere))
				)
			)
			(8
				(if (Btst 143)
					(ego setMotion: MoveTo 288 135 self)
				else
					(self cue:)
				)
			)
			(9
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(ego setMotion: MoveTo 288 135 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTobyClosesDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					setLoop: 1 1
					setCycle: Rev
					setMotion: MoveTo (+ (ego x?) 5) (ego y?) self
				)
			)
			(1
				(ego normalize:)
				(aToby setLoop: 1 1 setCel: 0 setCycle: CT 7 1 self)
			)
			(2
				(aToby setCel: 7 setCycle: CT 13 1 self)
				(pDoor setCycle: CT 2 -1 self)
			)
			(3
				(aToby setPri: 141)
				(closeDoorSound play:)
				(pDoor cycleSpeed: 1 setCycle: CT 0 -1 self)
			)
			(4
				(pDoor cycleSpeed: 6 setCycle: End self)
			)
			(5 (messager say: 1 6 4 0 self))
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sNotNice of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 145)
				(messager say: 1 6 3 0 self)
			)
			(1
				(pDoor setPri: 152)
				(ego setPri: 163 setMotion: PolyPath 170 139 self)
			)
			(2
				(pDoor ignoreActors: 1)
				(vEntrance ignoreActors: 1)
				(aToby ignoreActors: 1)
				(pDoor setCel: 2 cycleSpeed: 1 setCycle: Beg self)
				(aToby setPri: 119)
			)
			(3
				(pDoor cycleSpeed: 6)
				(pTanya
					view: 652
					setLoop: 0 1
					setCel: 0
					posn: 152 118
					setCycle: End self
				)
			)
			(4
				(pTanya setLoop: 1 1 setCel: 0 setCycle: End self)
			)
			(5
				(ego
					illegalBits: 0
					ignoreActors: 1
					ignoreHorizon: 1
					setPri: 163
					setMotion: MoveTo 168 137 self
				)
			)
			(6
				(messager say: 1 6 44 0 self)
			)
			(7 (EgoDead 3 650))
		)
	)
)

(instance sWhoseThere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setPri: 152)
				(if (Btst 140)
					(messager say: 1 6 2 0 self)
				else
					(messager say: 1 6 1 0 self)
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sStayOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1 (= seconds 2))
			(2
				(aToby
					setPri: 141
					setLoop: 1 1
					setCel: 0
					setCycle: CT 8 1 self
				)
			)
			(3
				(closeDoorSound play:)
				(aToby setCel: 8 setCycle: End self)
				(pDoor cycleSpeed: 1 setCycle: Beg self)
			)
			(4
				(if (not (Btst 145))
					(pDoor setCel: 0 cycleSpeed: 6 setCycle: CT 4 1 self)
				else
					(self cue:)
				)
			)
			(5 (= seconds 2))
			(6
				(if (not (Btst 145))
					(aToby setCel: 0 setCycle: CT 8 1 self)
				else
					(self cue:)
				)
			)
			(7
				(closeDoorSound play:)
				(if (not (Btst 145))
					(aToby setCel: 8 setCycle: End self)
				)
				(pDoor setCel: 4 cycleSpeed: 1 setCycle: CT 0 -1 self)
			)
			(8
				(pDoor cycleSpeed: 6)
				(= seconds 3)
			)
			(9
				(if (== local0 1)
					(messager say: 28 6 2 0 self)
				else
					(self cue:)
				)
			)
			(10 (curRoom newRoom: 620))
		)
	)
)

(instance sGiveDoll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(fFloor dispose:)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(messager say: 1 6 5 0 self)
			)
			(1
				(curRoom
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								319
								102
								267
								128
								225
								130
								189
								130
								177
								136
								129
								137
								114
								130
								77
								131
								63
								122
								38
								128
								16
								124
								0
								139
								0
								0
								319
								0
							yourself:
						)
						((Polygon new:)
							type: 2
							init: 319 161 319 189 0 189 0 161
							yourself:
						)
				)
				(Bset 143)
				(ego solvePuzzle: 449 15)
				(aToby setPri: 106 setLoop: 0 1 setCycle: CT 10 1 self)
			)
			(2
				(aToby setCycle: 0)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(= pDoorPriority (pDoor priority?))
				(pDoor setPri: 110)
				(ego use: 33 setMotion: PolyPath 176 138 self)
			)
			(3
				(ego
					view: 31
					setCel: 0
					setLoop: 1 1
					cycleSpeed: 10
					setCycle: CT 2 1 self
				)
			)
			(4
				(sMagicSound play:)
				(ego setCel: 2 setCycle: Beg self)
			)
			(5
				(pDoor setPri: 108)
				(pTanya setLoop: 1 1 setCel: 9 setCycle: Beg self)
			)
			(6
				(heroTeller dispose:)
				(tanyaTeller dispose:)
				(tobyTeller dispose:)
				(ego cycleSpeed: gTheObj_2CycleSpeed normalize:)
				(self cue:)
			)
			(7
				(tanyaTeller init: pTanya 650 3 151 9)
				(heroTeller init: ego 650 3 128 9)
				(tobyTeller init: aToby 650 3 126 9)
				(messager say: 1 6 6 0 self)
			)
			(8
				(self dispose:)
				(theGame handsOn:)
			)
		)
	)
)

(instance sdoRoom270 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 3 128 41 1 self)
			)
			(1
				(messager say: 3 128 41 2 self)
			)
			(2
				(messager say: 3 128 41 3 self)
			)
			(3
				(messager say: 3 128 41 4 self)
			)
			(4
				(messager say: 3 128 41 5 self)
			)
			(5
				(messager say: 3 128 41 6 self)
			)
			(6
				(messager say: 3 128 41 7 self)
			)
			(7
				(messager say: 3 128 41 8 self)
			)
			(8
				(messager say: 3 128 41 9 self)
			)
			(9
				(theMusic doSongs: fade:)
				(curRoom newRoom: 270)
			)
		)
	)
)

(instance aToby of Actor
	(properties
		noun 29
		x 187
		y 125
		priority 106
		fixPriority 1
		view 650
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance pLtCandle of Prop
	(properties
		x 86
		y 70
		view 650
		loop 5
		detailLevel 2
	)
)

(instance pRtCandle of Prop
	(properties
		x 226
		y 68
		view 650
		loop 6
		detailLevel 2
	)
)

(instance pTanya of Prop
	(properties
		noun 1
		x 156
		y 116
		view 651
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(52
				(curRoom setScript: sGiveDoll)
			)
			(4 (curRoom doVerb: theVerb))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pDoor of Prop
	(properties
		noun 21
		x 284
		y 24
		priority 141
		fixPriority 1
		view 650
		loop 2
	)
	
	(method (doit)
		(if (and cycler (cycler isKindOf: End) (not cel))
			(openDoor stop: play:)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom newRoom: 620)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance vEntrance of View
	(properties
		noun 21
		x 285
		y 26
		priority 97
		fixPriority 1
		view 650
		loop 3
	)
)

(instance vCoffin of View
	(properties
		noun 24
		x 160
		y 97
		priority 40
		fixPriority 1
		view 650
		loop 4
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fWindow of Feature
	(properties
		noun 10
		nsTop 10
		nsRight 39
		nsBottom 64
		sightAngle 180
		x 19
		y 37
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fSnowGlobe of Feature
	(properties
		noun 11
		nsLeft 20
		nsTop 67
		nsRight 31
		nsBottom 80
		sightAngle 180
		x 25
		y 73
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fDancer of Feature
	(properties
		noun 12
		nsLeft 31
		nsTop 64
		nsRight 39
		nsBottom 78
		sightAngle 180
		x 35
		y 71
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fClock of Feature
	(properties
		noun 13
		nsLeft 59
		nsTop 25
		nsRight 72
		nsBottom 74
		sightAngle 180
		x 65
		y 49
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fSteps of Feature
	(properties
		noun 14
		nsLeft 126
		nsTop 111
		nsRight 183
		nsBottom 130
		sightAngle 180
		x 154
		y 120
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fPeasantDoll of Feature
	(properties
		noun 15
		nsTop 92
		nsRight 30
		nsBottom 125
		sightAngle 180
		x 15
		y 108
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fDresser of Feature
	(properties
		noun 16
		nsLeft 7
		nsTop 74
		nsRight 68
		nsBottom 114
		sightAngle 180
		x 37
		y 94
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fClown of Feature
	(properties
		noun 17
		nsLeft 33
		nsTop 135
		nsRight 79
		nsBottom 189
		sightAngle 180
		x 56
		y 162
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fElephant of Feature
	(properties
		noun 18
		nsTop 123
		nsRight 71
		nsBottom 177
		sightAngle 180
		x 35
		y 150
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCastle1 of Feature
	(properties
		noun 4
		nsLeft 71
		nsTop 117
		nsRight 100
		nsBottom 189
		sightAngle 180
		x 85
		y 153
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCastle2 of Feature
	(properties
		noun 4
		nsLeft 100
		nsTop 134
		nsRight 144
		nsBottom 189
		sightAngle 180
		x 122
		y 161
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fTheatre of Feature
	(properties
		noun 19
		nsLeft 227
		nsTop 141
		nsRight 292
		nsBottom 188
		sightAngle 180
		x 259
		y 164
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fPolkaHorse of Feature
	(properties
		noun 20
		nsLeft 283
		nsTop 119
		nsRight 319
		nsBottom 189
		sightAngle 180
		x 301
		y 154
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fDoor of Feature
	(properties
		noun 21
		nsLeft 272
		nsTop 23
		nsRight 304
		nsBottom 119
		sightAngle 180
		x 288
		y 71
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom newRoom: 620)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fFloor of Feature
	(properties
		nsLeft 257
		nsTop 103
		nsRight 292
		nsBottom 121
		sightAngle 180
		x 257
		y 103
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 275 103 292 103 286 121 268 121
						yourself:
					)
					1
					5
					4
					sTobyClosesDoor
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCandelabra1 of Feature
	(properties
		noun 22
		nsLeft 75
		nsTop 54
		nsRight 97
		nsBottom 90
		sightAngle 180
		x 86
		y 72
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCandelabra2 of Feature
	(properties
		noun 23
		nsLeft 215
		nsTop 53
		nsRight 238
		nsBottom 91
		sightAngle 180
		x 226
		y 72
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCoffin of Feature
	(properties
		noun 24
		nsLeft 109
		nsTop 86
		nsRight 203
		nsBottom 104
		sightAngle 180
		x 156
		y 95
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fDrapery of Feature
	(properties
		noun 25
		nsLeft 73
		nsRight 255
		nsBottom 107
		sightAngle 180
		x 164
		y 53
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fRug of Feature
	(properties
		noun 26
		nsLeft 100
		nsTop 131
		nsRight 259
		nsBottom 174
		sightAngle 180
		x 179
		y 152
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(curRoom doVerb: theVerb)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance tanyaTeller of Teller
	(properties
		title 1
		actionVerb 2
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 99 0))
	)
)

(instance heroTeller of Teller
	(properties
		actionVerb 2
	)
	
	(method (sayMessage)
		(switch iconValue
			(41
				(self clean:)
				(curRoom setScript: sdoRoom270)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(super showCases: 41 local1)
	)
)

(instance tobyTeller of Teller
	(properties
		title 1
		actionVerb 2
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 70 0))
	)
)

(instance fireSound of Sound
	(properties
		number 965
		loop -1
	)
)

(instance openDoor of Sound
	(properties
		number 972
	)
)

(instance closeDoorSound of Sound
	(properties
		number 973
	)
)

(instance sMagicSound of Sound
	(properties
		number 934
	)
)
