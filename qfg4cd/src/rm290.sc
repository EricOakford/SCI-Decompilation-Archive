;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerObj)
(use TellerIcon)
(use Intrface)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Reverse)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm290 0
)

(local
	local0
	local1
	eventNum
	local3
	local4
	gTheObj_2CycleSpeed
	gTheObj_2MoveSpeed
	local7
	local8
	local9
)
(instance rm290 of GloryRm
	(properties
		noun 2
		picture 290
		horizon 84
		south 563
		topX 184
		bottomX 125
	)
	
	(method (init)
		(theGame handsOff:)
		(Load rsSCRIPT -567)
		(if debugging
			(= eventNum (GetNumber {Event #?}))
			(if (OneOf eventNum 1 2 3 4)
				(= Night 1)
				(= local9 1)
				(if (== eventNum 3) (Bset 80) (= prevRoomNum 563))
				(if (== eventNum 4) (Bset 81))
			)
		else
			(= eventNum
				(cond 
					(
						(and
							(not Night)
							(== prevRoomNum 563)
							(or (Btst 39) (Btst 40))
							(not (Btst 310))
						)
						5
					)
					(
						(and
							Night
							(not (Btst 79))
							(> Day 2)
							(not (Btst 115))
						)
						1
					)
					((and Night (== prevRoomNum 563) (Btst 81)) 4)
					((and Night (Btst 80) (== prevRoomNum 563)) 3)
					(
						(and
							Night
							(Btst 79)
							(not (Btst 82))
							(>= Day (+ gCurrentDay_5 3))
							(not (Btst 115))
							(Btst 399)
						)
						2
					)
					(else 0)
				)
			)
		)
		(ego
			view:
				(if
					(and
						(or (== eventNum 3) (== eventNum 4))
						(== prevRoomNum 563)
					)
					1
				else
					0
				)
			init:
			ignoreActors: (if Night 0 else 1)
			setScaler: Scaler 98 60 189 85
			normalize:
		)
		(if (OneOf eventNum 1 2)
			(if (== eventNum 1)
				(heroTeller init: ego 290 5 128 16)
			else
				(heroTeller init: ego 290 5 128 17)
			)
			(ego actions: (myDist init: aKatrina 60 yourself:))
		)
		(fGate1 init: approachVerbs: 4)
		(fPump1 init: approachVerbs: 4)
		(fPump2 init: approachVerbs: 4)
		(fPump3 init: approachVerbs: 4)
		(fPump4 init: approachVerbs: 4)
		(fScarecrow init: approachVerbs: 4)
		(fCorn1 init: approachVerbs: 4)
		(fCorn2 init: approachVerbs: 4)
		(fCorn3 init: approachVerbs: 4)
		(fGully init: approachVerbs: 4)
		(fBridge1 init: approachVerbs: 4)
		(fBridge2 init: approachVerbs: 4)
		(fRocks1 init: approachVerbs: 4)
		(fRocks2 init: approachVerbs: 4)
		(fStump init: approachVerbs: 4)
		(fTreeTop init: approachVerbs: 4)
		(fTree2 init: approachVerbs: 4)
		(if Night
			(gateTeller init: fGate1 290 5 125)
		else
			(= north 270)
		)
		(if (== prevRoomNum 340)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							82
							189
							0
							189
							0
							1
							619
							1
							619
							489
							230
							189
							184
							137
							158
							136
							159
							112
							205
							124
							219
							124
							220
							119
							201
							118
							195
							118
							181
							114
							180
							102
							199
							101
							214
							90
							180
							91
							200
							66
							184
							68
							174
							89
							150
							90
							110
							100
							86
							113
							40
							100
							31
							125
							57
							126
							58
							154
						yourself:
					)
			)
		)
		(if Night
			((pPumpkin1 new:)
				setLoop: 0 1
				posn: 183 135
				approachX: 153
				approachY: 136
				init:
				approachVerbs: 4
				ignoreActors: 1
			)
			((pPumpkin1 new:)
				setLoop: 1 1
				posn: 205 152
				approachX: 176
				approachY: 144
				init:
				approachVerbs: 4
				ignoreActors: 1
			)
			((pPumpkin1 new:)
				setLoop: 2 1
				posn: 263 142
				approachX: 183
				approachY: 153
				init:
				approachVerbs: 4
				ignoreActors: 1
			)
			((pPumpkin1 new:)
				setLoop: 3 1
				posn: 231 161
				approachX: 199
				approachY: 164
				init:
				approachVerbs: 4
				ignoreActors: 1
			)
			(switch eventNum
				(1
					(aKatrina
						init:
						setScaler: Scaler 98 60 189 85
						ignoreActors:
						approachVerbs: 4 2
					)
					(= gCurrentDay_5 Day)
					(Bset 79)
					(katrinaTeller init: aKatrina 290 5 127 16)
					(katrinaTeller2 init: aKatrina 290 5 138 15)
				)
				(2
					(aKatrina
						init:
						setScaler: Scaler 98 60 189 85
						approachVerbs: 4 2
						ignoreActors:
					)
					(Bclr 399)
					(Bset 82)
					(katrinaTeller init: aKatrina 290 5 127 17)
					(katrinaTeller2 init: aKatrina 290 5 138 15)
				)
			)
			(cond 
				((Btst 81)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									-300
									186
									-300
									-300
									619
									-300
									619
									188
									238
									188
									180
									137
									149
									133
									149
									117
									173
									118
									207
									124
									211
									122
									180
									116
									182
									104
									212
									86
									203
									85
									489
									71
									183
									72
									183
									83
									129
									94
									87
									111
									47
									96
									26
									106
									26
									138
									60
									149
									77
									186
								yourself:
							)
					)
				)
				((Btst 80)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									-300
									489
									-300
									-300
									619
									-300
									619
									489
									231
									489
									184
									137
									163
									136
									151
									130
									151
									118
									166
									98
									200
									101
									213
									90
									193
									91
									162
									90
									151
									90
									108
									101
									87
									113
									40
									100
									31
									125
									57
									126
									58
									154
									83
									489
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
									-300
									489
									-300
									-300
									619
									-300
									619
									489
									231
									489
									184
									137
									163
									136
									157
									124
									157
									105
									181
									102
									200
									101
									213
									90
									193
									91
									162
									90
									151
									90
									108
									101
									87
									113
									40
									100
									31
									125
									57
									126
									58
									154
									83
									489
								yourself:
							)
					)
				)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							-300
							489
							-300
							-300
							619
							-300
							619
							489
							238
							489
							180
							137
							149
							133
							149
							117
							173
							118
							207
							124
							211
							122
							180
							116
							182
							104
							190
							83
							188
							71
							183
							66
							179
							72
							183
							83
							87
							111
							47
							96
							26
							106
							26
							138
							60
							149
							80
							489
						yourself:
					)
			)
		)
		(if (not Night)
			(vOpenDoor
				ignoreActors:
				init:
				setPri: 80
				approachVerbs: 4
			)
		else
			(= local9 1)
		)
		(fGate2 init: approachVerbs: 4)
		(fTowers init: approachVerbs: 4)
		(self
			addPoly:
				((Polygon new:)
					init:
						82
						145
						102
						152
						127
						155
						157
						161
						160
						169
						169
						171
						244
						181
						238
						189
						69
						189
						53
						157
						64
						166
						87
						165
						69
						158
						57
						156
						55
						146
						98
						161
						134
						169
						126
						161
						85
						151
						74
						145
					yourself:
				)
				50
		)
		(super init: &rest)
		(cond 
			((cast contains: aKatrina) (theMusic number: 110 setLoop: -1 play:))
			(
				(and
					(not Night)
					(not (== prevRoomNum 270))
					(not (Btst 80))
					(not (Btst 81))
				)
				(theMusic number: 558 setLoop: -1 play:)
			)
			(
				(and
					(not (== prevRoomNum 270))
					(not (Btst 80))
					(not (Btst 81))
				)
				(theMusic number: 557 setLoop: -1 play:)
			)
			(
			(and (not Night) (not (Btst 80)) (not (Btst 81)))
				(if (not (Btst 372))
					(Bset 372)
					(theMusic number: 290 setLoop: 1 1 play: self)
				else
					(theMusic number: 558 setLoop: -1 play:)
				)
			)
		)
		(switch prevRoomNum
			(563
				(ego posn: 265 240)
				(= local0 180)
				(if (Btst 80) (= local1 160) else (= local1 181))
				(ego setScript: sEnter)
			)
			(340
				(ego setScript: sThiefEnter)
			)
			(else 
				(= local0 130)
				(= local1 108)
				(if (not Night)
					(ego posn: 188 89)
					(ego setScript: sEnter)
				else
					(ego posn: 1000 1000)
					(switch global423
						(1 (ego setScript: sEnterClimb))
						(2
							(ego setScript: sEnterLevitate)
						)
						(else  (theGame handsOn:))
					)
				)
			)
		)
		(if (or (Btst 80) (Btst 81)) (theGame save: 1))
	)
	
	(method (doit)
		(if
			(and
				(== (curRoom script?) (ScriptID 31 1))
				(>= (ego z?) 60)
			)
			(curRoom setScript: sLevitating)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(if (or (Btst 80) (Btst 81)) (theMusic2 fade: 0))
		(if (and (!= theMusic 557) (!= theMusic 558))
			(theMusic fade:)
		)
		(theMusic client: 0)
		(DisposeScript -567)
		(DisposeScript 9)
		(heroTeller dispose:)
		(if (OneOf eventNum 1 2)
			(katrinaTeller dispose:)
			(katrinaTeller2 dispose:)
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(89
				(if local9
					((ScriptID 31 0) init: 179 89 180 0 2)
				else
					(messager say: 4 4 41)
				)
			)
			(87
				(if (ego has: 27)
					(messager say: 3 4 5)
				else
					(self setScript: (ScriptID 37) 0 fCorn1)
				)
			)
			(-87 (messager say: 3 87 0 0))
			(81
				(if Night
					(messager say: 2 81 4)
				else
					(messager say: 2 81 3)
				)
			)
			(1
				(if Night
					(messager say: 2 1 4)
				else
					(messager say: 2 1 3)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (cue)
		(if (== (theMusic number?) 290)
			(theMusic number: 558 setLoop: -1 play:)
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (Btst 80) (Btst 81))
					(theMusic2 number: 103 loop: -1 play:)
				)
				(ego setMotion: PolyPath local0 local1 self)
			)
			(1
				(if (Btst 80)
					(ego setScript: sTimerScript)
				else
					(switch eventNum
						(1
							(self setScript: sKatrinaGreets)
						)
						(2
							(self setScript: sKatrinaGreets)
						)
						(5 (self setScript: sGypsy))
						(else 
							(if (and Night (not (Btst 36)) (Btst 81))
								(self setScript: sAmbushed)
							else
								(theGame handsOn:)
								(self dispose:)
							)
						)
					)
				)
			)
		)
	)
)

(instance sGypsy of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 310)
				(aDavy
					view: 465
					setLoop: 0 1
					init:
					setCycle: Walk
					setMotion: MoveTo 52 189 self
				)
			)
			(1
				(aDavy setCel: 9 setCycle: 0)
				(ego setMotion: PolyPath 80 177 self)
			)
			(2
				(messager say: 20 6 55 0 self)
			)
			(3
				(sndChanges play:)
				(aDavy
					view: 463
					setLoop: 4 1
					setCel: 0
					posn: 53 189
					setCycle: End self
				)
			)
			(4
				(aDavy
					setLoop: 1 1
					setCel: 0
					posn: 57 189
					setCycle: Fwd
					setMotion: MoveTo -25 189 self
				)
			)
			(5
				(aDavy setCycle: 0 dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLevitating of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: (ego x?) 33)
				(= cycles 6)
			)
			(1
				(ego
					view: 7
					setLoop: 4 1
					setCel: 0
					y: 15
					setCycle: End self
				)
			)
			(2
				(ego castSpell: 29)
				(if (OneOf eventNum 3 4)
					(Bset 81)
					((aNecrotaur1 new:)
						posn: 83 230
						init:
						setPri: 64
						cycleSpeed: 0
						moveSpeed: 0
						setCycle: Walk
						setMotion: PolyPath 167 98 self
					)
					((aNecrotaur1 new:)
						posn: 115 230
						init:
						setPri: 64
						cycleSpeed: 3
						moveSpeed: 3
						setCycle: Walk
						setMotion: PolyPath 130 102
					)
					((aNecrotaur1 new:)
						posn: 164 230
						init:
						setPri: 64
						setCycle: Walk
						setMotion: PolyPath 131 139
					)
				else
					(self cue:)
				)
			)
			(3
				(ego
					view: 17
					setLoop: 2 1
					setCel: 5
					setPri: 0
					setMotion: MoveTo (+ (ego x?) 10) (+ (ego y?) 25) self
				)
			)
			(4 (curRoom newRoom: 270))
		)
	)
)

(instance sToGully of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 6 2 0 self)
			)
			(1
				(ego setMotion: PolyPath 139 120 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTimerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(= seconds (/ 20 arcadeLevel))
			)
			(1
				(if (not (curRoom script?))
					(curRoom setScript: sChaseEgo)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sChaseEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0 ignoreActors:)
				((aNecrotaur1 new:)
					posn: 150 189
					init:
					setPri: 196
					setCycle: Walk
					setMotion: PolyPath (- (ego x?) 15) (ego y?)
				)
				((aNecrotaur1 new:)
					posn: 115 189
					init:
					setPri: 196
					setCycle: Walk
					setMotion: PolyPath (ego x?) (ego y?) self
				)
				(Bclr 80)
				(Bset 81)
			)
			(1
				(if (> (ego y?) 89)
					(pAdavis
						init:
						setScaler: Scaler 92 76 189 87
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(2
				(if (> (ego y?) 89)
					(messager say: 14 6 12 0 self)
				else
					(self cue:)
				)
			)
			(3
				(if (> (ego y?) 89)
					(cast eachElementDo: #hide)
					(UpdatePlane
						((curRoom plane?) back: 0 picture: -1 yourself:)
					)
					(= seconds 2)
				else
					(self cue:)
				)
			)
			(4
				(if (< (ego y?) 89)
					(curRoom newRoom: 270)
				else
					(curRoom newRoom: 670)
				)
			)
		)
	)
)

(instance sAmbushed of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego ignoreActors: setMotion: MoveTo local0 local1 self)
			)
			(1
				((aNecrotaur1 new:)
					setCel: 0
					posn: -55 189
					init:
					setPri: 207
					setCycle: Walk
					setMotion: PolyPath (- (ego x?) 10) 180
				)
				((aNecrotaur1 new:)
					setCel: 0
					posn: 215 230
					init:
					setPri: 207
					setCycle: Walk
					setMotion: PolyPath (ego x?) (ego y?) self
				)
				((aNecrotaur1 new:)
					setCel: 0
					posn: 375 189
					init:
					setPri: 207
					setCycle: Walk
					setMotion: PolyPath (+ (ego x?) 10) 171
				)
				(Bclr 81)
			)
			(2
				(pAdavis
					init:
					setScaler: Scaler 92 76 189 87
					setCycle: End self
				)
			)
			(3
				(messager say: 14 6 12 0 self)
			)
			(4
				(cast eachElementDo: #hide)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(= seconds 2)
			)
			(5 (curRoom newRoom: 670))
		)
	)
)

(instance sKatrinaWalksAway of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 5)
			)
			(1
				(heroTeller dispose:)
				(ego actions: (myDist init: aKatrina 60 yourself:))
				(if (== eventNum 1)
					(messager say: 15 6 18 0 self)
				else
					(messager say: 15 6 21 0 self)
				)
			)
			(2
				(aKatrina
					setLoop: 1 1
					setCel: 0
					setCycle: Fwd
					setMotion: MoveTo 1 (aKatrina y?) self
				)
			)
			(3
				(if (== eventNum 1)
					(messager say: 1 6 22 0 self)
				else
					(messager say: 1 6 25 0 self)
				)
				(aKatrina actions: 0 setCycle: 0 hide: dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sKatrinaGreets of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 5)
			)
			(1
				(theGame handsOff:)
				(if (== heroType 3)
					(messager say: 1 6 35 0 self)
				else
					(self cue:)
				)
			)
			(2
				(theGame handsOff:)
				(if (== eventNum 1)
					(messager say: 15 6 13 0 self)
				else
					(messager say: 15 6 19 0 self)
				)
			)
			(3
				(theGame handsOff:)
				(= seconds 1)
			)
			(4
				(theGame handsOff:)
				(if (not (curRoom script?)) (self cue:))
			)
			(5
				(theGame handsOff:)
				(if (< [egoStats 17] (ego maxHealth:))
					(sndChanges play:)
					(ego get: 3)
					(if (== eventNum 1)
						(messager say: 15 6 14 0 self)
					else
						(messager say: 15 6 20 0 self)
					)
				else
					(self cue:)
				)
			)
			(6
				(if [egoStats 12]
					(if (== eventNum 2)
						(self cue:)
					else
						(messager say: 15 6 15 0 self)
					)
				else
					(self changeState: 11)
				)
			)
			(7
				(ego setMotion: PolyPath 63 136 self)
			)
			(8
				(if (== eventNum 2)
					(self cue:)
				else
					(messager say: 15 6 16 0 self)
				)
			)
			(9
				(if (== eventNum 2)
					(self cue:)
				else
					(ego
						learn: 34 100
						solvePuzzle: 497 6 2 2
						setCycle: Rev
						setMotion: MoveTo (- (ego x?) 10) (ego y?) self
					)
				)
			)
			(10
				(if (== eventNum 2)
					(self cue:)
				else
					(messager say: 15 6 17 0 self)
				)
			)
			(11
				(ego setCycle: 0 normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sThiefEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 120))
			(1
				(ego posn: 222 124 setMotion: MoveTo 193 124 self)
			)
			(2
				(ego setMotion: PolyPath 138 120 self)
			)
			(3
				(ego setMotion: PolyPath 132 151 self)
			)
			(4
				(= local0 0)
				(= local1 0)
				(switch eventNum
					(1
						(self setScript: sKatrinaGreets)
					)
					(2
						(self setScript: sKatrinaGreets)
					)
					(5 (self setScript: sGypsy))
					(else 
						(if (Btst 81)
							(self setScript: sAmbushed)
						else
							(theGame handsOn:)
							(self dispose:)
						)
					)
				)
			)
		)
	)
)

(instance sGetCorn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath local0 local1 self)
			)
			(1
				(ego view: 31 loop: 0 setCycle: End self)
			)
			(2 (ego get: 27) (= ticks 30))
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize:)
				(messager say: 3 4 6 0 self)
			)
			(5
				(theGame handsOn:)
				(= local0 0)
				(= local1 0)
				(self dispose:)
			)
		)
	)
)

(instance sClimbTheGate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local7
					(ego setMotion: PolyPath 162 92 self)
				else
					(ego setMotion: PolyPath 187 92 self)
				)
			)
			(1
				(cond 
					(local7 (ego useSkill: 11 200) (messager say: 4 33 0 0 self))
					(local9
						(ego useSkill: 11 200)
						(if (> [egoStats 11] 200)
							(messager say: 5 125 10 0 self)
						else
							(messager say: 4 4 7 0 self)
						)
					)
					(else (messager say: 4 4 3 0 self))
				)
			)
			(2
				(if local7
					(self setScript: sClimbOverGrapnel)
				else
					(ego view: 7 setLoop: 3 1 setCel: 0 setCycle: Fwd)
					(= ticks 10)
				)
			)
			(3
				(if (> [egoStats 11] 200)
					(= gTheObj_2MoveSpeed (ego moveSpeed?))
					(= gTheObj_2CycleSpeed (ego cycleSpeed?))
					(ego
						setCycle: Fwd
						cycleSpeed: 4
						moveSpeed: 4
						setPri: 97
						setMotion: MoveTo 188 38 self
					)
				else
					(ego normalize:)
					(theGame
						setCursor: ((theIconBar getCursor:)
							view: 942
							loop: 0
							cel: 0
							yourself:
						)
					)
					(theGame handsOn:)
					(self dispose:)
				)
			)
			(4
				(ego
					setLoop: 4 1
					setCel: 0
					posn: 187 14
					moveSpeed: gTheObj_2MoveSpeed
					setCycle: CT 4 1 self
				)
			)
			(5
				(if (OneOf eventNum 3 4)
					(Bset 81)
					((aNecrotaur1 new:)
						posn: 75 230
						init:
						setPri: 64
						cycleSpeed: 0
						moveSpeed: 0
						setCycle: Walk
						setMotion: PolyPath 167 98 self
					)
					((aNecrotaur1 new:)
						posn: 115 230
						init:
						setPri: 207
						cycleSpeed: 3
						moveSpeed: 3
						setCycle: Walk
						setMotion: PolyPath 130 102
					)
					((aNecrotaur1 new:)
						posn: 164 230
						init:
						setPri: 207
						setCycle: Walk
						setMotion: PolyPath 131 139
					)
				else
					(self cue:)
				)
			)
			(6
				(ego setCel: 4 setCycle: End self)
			)
			(7
				(ego cycleSpeed: gTheObj_2CycleSpeed)
				(curRoom newRoom: 270)
			)
		)
	)
)

(instance sClimbOverGrapnel of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local7 0)
				(Face ego 168 45 self)
			)
			(1
				(ego view: 8 setLoop: 0 1 posn: 171 91 setCycle: End self)
			)
			(2
				(ego setLoop: 7 1 setCel: 0 posn: 171 91 setCycle: End)
				(pProp
					view: 8
					setLoop: 6 1
					setCel: 0
					posn: 185 41
					setPri: 75
					setScaler: Scaler 98 60 189 85
					init:
					setCycle: End self
				)
			)
			(3
				(vRope init:)
				(ego normalize: setPri: 97 setMotion: MoveTo 183 88 self)
			)
			(4
				(ego
					view: 7
					setLoop: 0 1
					setCel: 0
					posn: 190 86
					ignoreActors: 1
					ignoreHorizon: 1
					setCycle: End self
				)
			)
			(5
				(ego setLoop: 1 1 setCel: 0 y: 86 setCycle: End self)
			)
			(6
				(ego setCel: 0 x: 192 y: 76 setCycle: End self)
			)
			(7
				(ego setCel: 0 y: 65 setCycle: End self)
			)
			(8
				(ego setCel: 0 x: 193 y: 55 setCycle: End self)
			)
			(9
				(ego setCel: 0 y: 14 setLoop: 4 1 setCycle: End self)
			)
			(10
				(if (OneOf eventNum 3 4)
					(Bset 81)
					((aNecrotaur1 new:)
						posn: 75 230
						init:
						setPri: 64
						cycleSpeed: 0
						moveSpeed: 0
						setCycle: Walk
						setMotion: PolyPath 167 98 self
					)
					((aNecrotaur1 new:)
						posn: 115 230
						init:
						setPri: 207
						cycleSpeed: 3
						moveSpeed: 3
						setCycle: Walk
						setMotion: PolyPath 130 102
					)
					((aNecrotaur1 new:)
						posn: 164 230
						init:
						setPri: 207
						setCycle: Walk
						setMotion: PolyPath 131 139
					)
				else
					(self cue:)
				)
			)
			(11 (curRoom newRoom: 270))
		)
	)
)

(instance sEnterClimb of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(theGame handsOff:)
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 7
					setLoop: 5 1
					setCel: 0
					posn: 187 14
					setPri: 50
					setCycle: End self
				)
			)
			(2
				(ego cycleSpeed: 8 moveSpeed: 8 setCycle: Beg self)
			)
			(3
				(ego
					setLoop: 3 1
					setCel: 0
					posn: (ego x?) (+ (ego y?) 38)
					setCycle: Fwd
					setMotion: MoveTo 187 92 self
				)
			)
			(4
				(ego
					cycleSpeed: gTheObj_2CycleSpeed
					moveSpeed: gTheObj_2MoveSpeed
					normalize:
				)
				(switch eventNum
					(1
						(self setScript: sKatrinaGreets)
					)
					(2
						(self setScript: sKatrinaGreets)
					)
					(else 
						(if (Btst 81)
							(self setScript: sAmbushed)
						else
							(theGame handsOn:)
							(self dispose:)
						)
					)
				)
			)
		)
	)
)

(instance sEnterLevitate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 17
					setLoop: 1 1
					setCel: 5
					posn: 187 14
					setPri: 75
					setCycle: 0
					setMotion: MoveTo 179 93 self
				)
			)
			(1
				(ego normalize: useMana: 10)
				(switch eventNum
					(1
						(self setScript: sKatrinaGreets)
					)
					(2
						(self setScript: sKatrinaGreets)
					)
					(else 
						(if (Btst 81)
							(self setScript: sAmbushed)
						else
							(theGame handsOn:)
							(self dispose:)
						)
					)
				)
			)
		)
	)
)

(instance sHeroBye of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1
				(if (== eventNum 1)
					(messager say: 16 128 58 0 self)
				else
					(messager say: 17 128 59 0 self)
				)
			)
			(2
				(self setScript: sKatrinaWalksAway)
			)
		)
	)
)

(instance aDavy of Actor
	(properties
		x -20
		y 189
		priority 185
		fixPriority 1
		view 463
	)
)

(instance aNecrotaur1 of Actor
	(properties
		x 75
		y 230
		priority 152
		fixPriority 1
		view 870
		loop 3
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self
			ignoreActors: 1
			setScaler: Scaler 80 72 144 115
			setCycle: StopWalk 870
		)
	)
)

(instance aKatrina of Actor
	(properties
		noun 15
		approachX 63
		approachY 136
		x 37
		y 134
		view 792
		loop 2
	)
	
	(method (doVerb theVerb &tmp [temp0 2])
		(cond 
			(
				(OneOf
					theVerb
					85
					83
					81
					87
					86
					88
					79
					102
					91
					89
					93
					80
					90
					94
					92
					82
					84
					95
					96
					97
					98
					11
				)
				(messager say: 1 6 23)
			)
			((== theVerb 37)
				(if (== (ego has: 5) 1)
					(messager say: 1 6 60)
				else
					(messager say: 1 6 24)
				)
			)
			((OneOf theVerb 4 36 21) (messager say: 1 6 24))
			(else (super doVerb: theVerb))
		)
	)
)

(instance pAdavis of Prop
	(properties
		noun 14
		x 20
		y 186
		view 677
	)
)

(instance pProp of Prop
	(properties)
)

(instance vRope of View
	(properties
		x 189
		y 59
		priority 75
		fixPriority 1
		view 50
		cel 2
	)
)

(instance pPumpkin1 of View
	(properties
		noun 7
		sightAngle 180
		approachX 153
		approachY 136
		x 183
		y 135
		view 291
		cel 5
	)
	
	(method (doVerb theVerb)
		(fPump1 doVerb: theVerb)
	)
)

(instance vOpenDoor of View
	(properties
		noun 4
		sightAngle 180
		approachX 166
		approachY 92
		x 165
		y 45
		priority 80
		fixPriority 1
		view 290
	)
	
	(method (doVerb theVerb)
		(fGate1 doVerb: theVerb)
	)
)

(instance fPump1 of Feature
	(properties
		noun 7
		nsLeft 168
		nsTop 123
		nsRight 200
		nsBottom 134
		sightAngle 180
		approachX 153
		approachY 136
		x 184
		y 128
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if local9
				(messager say: 7 1 4)
			else
				(messager say: 7 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fPump2 of Feature
	(properties
		noun 7
		nsLeft 192
		nsTop 132
		nsRight 219
		nsBottom 154
		sightAngle 180
		approachX 176
		approachY 144
		x 205
		y 143
	)
	
	(method (doVerb theVerb)
		(fPump1 doVerb: theVerb)
	)
)

(instance fPump3 of Feature
	(properties
		noun 7
		nsLeft 231
		nsTop 162
		nsRight 257
		nsBottom 177
		sightAngle 180
		approachX 214
		approachY 177
		x 244
		y 169
	)
	
	(method (doVerb theVerb)
		(fPump1 doVerb: theVerb)
	)
)

(instance fPump4 of Feature
	(properties
		noun 7
		nsLeft 253
		nsTop 131
		nsRight 270
		nsBottom 140
		sightAngle 180
		approachX 176
		approachY 144
		x 261
		y 135
	)
	
	(method (doVerb theVerb)
		(fPump1 doVerb: theVerb)
	)
)

(instance fScarecrow of Feature
	(properties
		noun 8
		nsLeft 258
		nsTop 96
		nsRight 305
		nsBottom 141
		sightAngle 180
		approachX 183
		approachY 153
		x 281
		y 118
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if Night
				(messager say: 8 1 4)
			else
				(messager say: 8 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCorn1 of Feature
	(properties
		noun 3
		nsLeft 158
		nsTop 89
		nsRight 181
		nsBottom 124
		sightAngle 180
		approachX 148
		approachY 127
		x 169
		y 106
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if Night
					(messager say: 3 1 4)
				else
					(messager say: 3 1 3)
				)
			)
			(4
				(if (ego has: 27)
					(messager say: 3 4 5)
				else
					(= local0 148)
					(= local1 127)
					(curRoom setScript: sGetCorn)
				)
			)
			(87
				(if (ego has: 27)
					(messager say: 3 4 5)
				else
					(curRoom setScript: (ScriptID 37) 0 fCorn1)
				)
			)
			(-87 (messager say: 3 87 0 0))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fCorn2 of Feature
	(properties
		noun 3
		nsLeft 196
		nsTop 98
		nsRight 215
		nsBottom 133
		sightAngle 180
		approachX 179
		approachY 138
		x 205
		y 115
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (ego has: 27)
				(messager say: 3 4 5)
			else
				(= local0 179)
				(= local1 138)
				(curRoom setScript: sGetCorn)
			)
		else
			(fCorn1 doVerb: theVerb)
		)
	)
)

(instance fCorn3 of Feature
	(properties
		noun 3
		nsLeft 218
		nsTop 126
		nsRight 245
		nsBottom 166
		sightAngle 180
		approachX 207
		approachY 169
		x 231
		y 146
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(if (ego has: 27)
				(messager say: 3 4 5)
			else
				(= local0 207)
				(= local1 169)
				(curRoom setScript: sGetCorn)
			)
		else
			(fCorn1 doVerb: theVerb)
		)
	)
)

(instance fGully of Feature
	(properties
		noun 9
		nsLeft 211
		nsTop 104
		nsRight 259
		nsBottom 138
		sightAngle 180
		approachX 171
		approachY 120
		x 235
		y 121
	)
	
	(method (init)
		(super init: &rest)
		(if (== prevRoomNum 340)
			(= heading
				(((ScriptID 49 0) new:)
					init:
						((Polygon new:)
							type: 1
							init: 215 114 204 123 163 121 171 113
							yourself:
						)
						0
						4
						6
						sToGully
					yourself:
				)
			)
		)
	)
	
	(method (dispose)
		(if (and (== prevRoomNum 340) heading)
			(heading dispose:)
		)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if Night
				(messager say: 9 1 4)
			else
				(messager say: 9 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fGate1 of Feature
	(properties
		noun 4
		nsLeft 168
		nsTop 45
		nsRight 213
		nsBottom 77
		sightAngle 180
		approachX 166
		approachY 92
		x 190
		y 61
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if local9
					(messager say: 4 1 4)
				else
					(messager say: 4 1 3)
				)
			)
			(33
				(if local9
					(if (== (ego trySkill: 11 150) 1)
						(= local7 1)
						(curRoom setScript: sClimbTheGate)
					else
						(messager say: 4 4 7)
					)
				else
					(messager say: 4 4 3)
				)
			)
			(4
				(if local9
					(if (== (ego trySkill: 11 150) 1)
						(curRoom setScript: sClimbTheGate)
					else
						(messager say: 4 4 7)
					)
				else
					(messager say: 4 4 3)
				)
			)
			(89
				(if local9
					(curRoom doVerb: theVerb)
				else
					(messager say: 4 4 3)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fGate2 of Feature
	(properties
		noun 4
		nsLeft 166
		nsTop 76
		nsRight 186
		nsBottom 91
		sightAngle 180
		approachX 166
		approachY 92
		x 176
		y 83
	)
	
	(method (init)
		(super init:)
		(if Night (= actions gateTeller))
	)
	
	(method (doVerb theVerb)
		(fGate1 doVerb: theVerb)
	)
)

(instance fBridge1 of Feature
	(properties
		noun 10
		nsLeft 79
		nsTop 71
		nsRight 160
		nsBottom 91
		sightAngle 180
		approachX 138
		approachY 97
		x 119
		y 81
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if Night
				(messager say: 10 1 4)
			else
				(messager say: 10 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fBridge2 of Feature
	(properties
		noun 10
		nsLeft 189
		nsTop 77
		nsRight 248
		nsBottom 114
		sightAngle 180
		approachX 190
		approachY 98
		x 218
		y 95
	)
	
	(method (doVerb theVerb)
		(fBridge1 doVerb: theVerb)
	)
)

(instance fTowers of Feature
	(properties
		noun 6
		nsLeft 103
		nsTop -1
		nsRight 278
		nsBottom 48
		sightAngle 180
		approachX 166
		approachY 92
		x 190
		y 23
	)
	
	(method (init)
		(super init:)
		(if Night (= actions gateTeller))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if Night
					(messager say: 6 1 4)
				else
					(messager say: 6 1 3)
				)
			)
			(33
				(if local9
					(fGate1 doVerb: theVerb)
					(= local7 1)
				else
					(messager say: 6 4 3)
				)
			)
			(4
				(if local9
					(if (== (ego trySkill: 11 200) 1)
						(curRoom setScript: sClimbTheGate)
					else
						(messager say: 4 4 7)
					)
				else
					(messager say: 4 4 3)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fRocks1 of Feature
	(properties
		noun 11
		nsLeft 32
		nsTop 76
		nsRight 78
		nsBottom 106
		sightAngle 180
		approachX 51
		approachY 104
		x 55
		y 91
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if Night
				(messager say: 11 1 4)
			else
				(messager say: 11 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fRocks2 of Feature
	(properties
		noun 11
		nsLeft 247
		nsTop 83
		nsRight 318
		nsBottom 126
		sightAngle 180
		approachX 183
		approachY 153
		x 282
		y 104
	)
	
	(method (doVerb theVerb)
		(fRocks1 doVerb: theVerb)
	)
)

(instance fStump of Feature
	(properties
		noun 12
		nsLeft 19
		nsTop 124
		nsRight 63
		nsBottom 188
		sightAngle 180
		approachX 77
		approachY 182
		x 41
		y 156
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (Btst 321)
					(messager say: 12 4 57)
				else
					(ego get: 0 5)
					(Bset 321)
					(messager say: 12 4 56)
				)
			)
			(82
				(ego trySkill: 22)
				(messager say: 12 82 0 0)
			)
			(1
				(if Night
					(messager say: 12 1 4)
				else
					(messager say: 12 1 3)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fTreeTop of Feature
	(properties
		noun 13
		nsTop -1
		nsRight 100
		nsBottom 46
		sightAngle 180
		approachX 77
		approachY 182
		x 50
		y 22
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if Night
				(messager say: 13 1 4)
			else
				(messager say: 13 1 3)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fTree2 of Feature
	(properties
		noun 13
		nsTop 46
		nsRight 34
		nsBottom 189
		sightAngle 212
		approachX 77
		approachY 185
		x 17
		y 117
	)
	
	(method (doVerb theVerb)
		(fTreeTop doVerb: theVerb)
	)
)

(instance gateTeller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(switch iconValue
			(10
				(self clean:)
				(cond 
					((== (ego trySkill: 11 200) 1) (curRoom setScript: sClimbTheGate))
					((== eventNum 3) (curRoom setScript: sClimbTheGate))
					(else (messager say: 4 4 7 &rest))
				)
			)
			(else  (super sayMessage:))
		)
	)
	
	(method (showCases)
		(super showCases: 10 (> [egoStats 11] 0))
	)
)

(instance katrinaTeller of Teller
	(properties
		title 1
		actionVerb 2
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 95 0))
	)
	
	(method (sayMessage)
		(if (== local3 3)
			(self clean:)
			(aKatrina setScript: sKatrinaWalksAway)
		else
			(++ local3)
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 28 (> [egoStats 12] 0))
	)
)

(instance katrinaTeller2 of Teller
	(properties
		title 1
		actionVerb 4
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 95 0))
	)
	
	(method (sayMessage)
		(if (== local3 3)
			(self clean:)
			(curRoom setScript: sKatrinaWalksAway)
		else
			(++ local3)
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super showCases: 28 (> [egoStats 12] 0))
	)
)

(instance heroTeller of Teller
	(properties
		actionVerb 2
	)
	
	(method (sayMessage)
		(switch iconValue
			(58
				(Bclr 51)
				(= gTeller 0)
				(curRoom setScript: sHeroBye)
			)
			(59
				(Bclr 51)
				(= gTeller 0)
				(curRoom setScript: sHeroBye)
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
			(aKatrina
				(heroTeller doVerb: theVerb)
			)
		)
	)
)

(instance sndChanges of Sound
	(properties
		number 934
	)
)
