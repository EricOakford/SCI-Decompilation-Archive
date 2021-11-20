;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
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
(use DPath)
(use Reverse)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm460 0
)

(local
	eventNum
	local1
	local2
	local3
	local4
	gTheObj_2MoveSpeed
	gTheObj_2CycleSpeed
	theGGGTheObj_2CycleSpeed
)
(instance rm460 of GloryRm
	(properties
		noun 24
		picture 460
		south 588
		bottomX 250
	)
	
	(method (init)
		(theGame handsOff:)
		(Load rsSCRIPT -572)
		(if debugging
			(if (== (= eventNum (GetNumber {Event #?})) 4)
				(= prevRoomNum 470)
			)
		else
			(= eventNum
				(cond 
					(
						(and
							(or (Btst 39) (Btst 40))
							(== prevRoomNum 588)
							(not (Btst 263))
						)
						3
					)
					((and (== prevRoomNum 470) (not (Btst 264))) 4)
					(
					(and (Btst 263) (not (Btst 265)) (== prevRoomNum 588)) 5)
					((and (Btst 265) (== prevRoomNum 588)) 6)
					(
						(and
							(not (Btst 39))
							(== prevRoomNum 588)
							(not (Btst 262))
							(not (Btst 37))
						)
						1
					)
					((and (not (Btst 39)) (== prevRoomNum 588)) 2)
					(else 0)
				)
			)
		)
		(switch prevRoomNum
			(470
				(= local1 63)
				(= local2 113)
				(ego posn: 107 81)
			)
			(else 
				(= local1 250)
				(= local2 186)
				(ego posn: 229 210)
			)
		)
		(ego init: normalize: setScaler: Scaler 122 67 189 80)
		(if (not (== eventNum 4))
			(theMusic number: 460 setLoop: -1 play:)
		)
		(switch eventNum
			(1
				(heroTeller init: ego 460 29 128 1)
				(aDavy view: 465 setLoop: 1 1 init: setCycle: Fwd)
				(daveyTeller init: aDavy 460 29 176 1)
				(Bset 262)
				(curRoom setScript: sEvent1)
			)
			(2
				(heroTeller init: ego 460 29 128 27)
				(Bset 262)
				(curRoom setScript: sEvent2)
			)
			(3
				(Bset 263)
				(Bset 310)
				(curRoom setScript: sEvent3)
			)
			(4
				(theMusic number: 471 setLoop: -1 play:)
				(if (not Night) ((ScriptID 7 4) init: 21))
				(Bset 264)
				(aDancer init: setScaler: Scaler 122 67 189 80)
				(aDavy
					view: 465
					setLoop: 2 1
					posn: 271 128
					setCel: 6
					init:
				)
				(pFiddler
					init:
					cycleSpeed: 10
					ignoreActors: 1
					setCycle: Fwd
				)
				((aWolf new:)
					view: 464
					setLoop: 2 1
					setCel: 0
					posn: 75 153
					init:
				)
				((aWolf new:)
					view: 464
					setLoop: 2 1
					setCel: 0
					posn: 116 162
					setPri: 152
					init:
				)
				((aWolf new:)
					view: 464
					setLoop: 3 1
					setCel: 1
					posn: 230 150
					setPri: 152
					init:
				)
				((aWolf new:)
					view: 464
					setLoop: 2 1
					setCel: 1
					posn: 241 130
					setPri: 152
					init:
				)
				(curRoom setScript: sEvent4)
			)
			(5
				(Bset 265)
				(heroTeller init: ego 460 29 128 3)
				(aDavy view: 463 setLoop: 1 1 posn: 319 126 init:)
				(daveyTeller init: aDavy 460 29 176 3)
				(curRoom setScript: sEvent5)
			)
			(6
				(heroTeller init: ego 460 29 128 28)
				(dvWolf
					view: 464
					setLoop: 2 1
					setCel: 0
					posn: 0 94
					setPri: 196
					init:
				)
				(wolfTeller init: dvWolf 460 29 176 28)
				(curRoom setScript: sEvent6)
			)
			(else 
				(curRoom setScript: sComeOnIn)
			)
		)
		(if
			(and
				(or
					(and
						(not (Btst 39))
						(== prevRoomNum 588)
						(not (Btst 262))
						(not (Btst 37))
					)
					(not (Btst 39))
				)
				(!= prevRoomNum 470)
			)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: -300 489 -300 -300 619 -300 619 489 292 489 272 171 243 172 190 489
						yourself:
					)
			)
		else
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							55
							120
							42
							136
							15
							143
							80
							164
							111
							164
							167
							165
							239
							152
							243
							172
							191
							489
							-300
							489
							-300
							-300
							619
							-300
							619
							489
							292
							489
							264
							169
							276
							155
							295
							143
							288
							129
							219
							119
							174
							105
							131
							108
							97
							111
							81
							116
							76
							114
							80
							107
							74
							105
							66
							107
							52
							109
						yourself:
					)
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						111
						113
						136
						113
						143
						117
						224
						124
						226
						137
						198
						143
						156
						146
						127
						143
						110
						132
					yourself:
				)
		)
		(if Night
			(pFire setCycle: Fwd init: ignoreActors: 1 setCycle: Fwd)
		)
		(super init: &rest)
		(fBarrel init: approachVerbs: 4)
		(fFirePit init: approachVerbs: 4)
		(fThinTree init: approachVerbs: 4)
		(fBox1 init: approachVerbs: 4)
		(fRocks1 init: approachVerbs: 4)
		(fRocks2 init: approachVerbs: 4)
		(fBushes init: approachVerbs: 4)
		(fBox2 init: approachVerbs: 4)
		(fBlueWindow init: approachVerbs: 4)
		(fRedWagon init:)
		(fRamp init: approachVerbs: 4)
		(fBlueWagon init: approachVerbs: 4)
		(fGreenWindow init: approachVerbs: 4)
		(fGreenWagon init:)
		(fStripedWagon init:)
		(fChimney init: approachVerbs: 4)
		(theGame handsOff:)
		(if (== prevRoomNum 588) (theGame save: 1))
	)
	
	(method (dispose)
		(DisposeScript -572)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 85)
				(if (Btst 39)
					(messager say: 0 85 0 0)
				else
					(messager say: 0 85 42)
				)
			)
			(
			(OneOf theVerb 83 87 86 88 79 93 80 21 157 92 36 37 82 84)
				(switch eventNum
					(1 (EgoDead 1 0 972 1))
					(2 (EgoDead 3 0 976 1))
					(5 (EgoDead 2 0 976 1))
					(6 (EgoDead 2 0 976 1))
					(else  0)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
	
	(method (newRoom n)
		(if (== n 588) (theMusic fade: 0))
		(super newRoom: n &rest)
	)
)

(instance sComeOnIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo local1 local2 self)
			)
			(1
				(ego normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLeave of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 29 128 35 1 self)
			)
			(1
				(ego setMotion: MoveTo 238 218 self)
			)
			(2 (curRoom newRoom: 588))
		)
	)
)

(instance sEvent1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo local1 local2 self)
			)
			(1
				(ego normalize:)
				(messager say: 25 6 18 1 self)
			)
			(2
				(if (== heroType 3)
					(messager say: 25 6 19 1 self)
				else
					(= ticks 30)
				)
			)
			(3
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 5) self)
			)
			(4
				(aDavy
					setPri: 130
					setLoop: 1 1
					setMotion: MoveTo 269 144 self
				)
				((aWolf new:)
					posn: 319 110
					setLoop: 1 1
					init:
					setPri: 130
					setCycle: Walk
					setMotion: MoveTo 264 140
				)
			)
			(5
				(aDavy setLoop: 2 1 setCycle: 0)
				((aWolf new:)
					posn: 176 173
					setLoop: 0 1
					init:
					setPri: 152
					setCycle: Walk
					setMotion: MoveTo 170 156
				)
				((aWolf new:)
					posn: 136 186
					setLoop: 0 1
					init:
					setPri: 152
					setCycle: Walk
					setMotion: MoveTo 223 152 self
				)
			)
			(6
				((aWolf new:)
					posn: 29 178
					setLoop: 0 1
					setPri: 152
					init:
					setCycle: Walk
					setMotion: MoveTo 103 154 self
				)
			)
			(7
				(messager say: 12 6 12 1 self)
			)
			(8
				(wolfTeller init: aWolf 460 29 176 26)
				(ego solvePuzzle: 442 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo local1 local2 self)
			)
			(1
				(ego normalize:)
				(if (== heroType 3)
					(messager say: 25 6 22 1 self)
				else
					(= ticks 30)
				)
			)
			(2
				(ego setMotion: PolyPath (ego x?) (- (ego y?) 2) self)
			)
			(3
				((aWolf new:)
					posn: 22 178
					setPri: 141
					setLoop: 0 1
					ignoreActors: 1
					init:
					setCycle: Walk
					setMotion: MoveTo 77 147
				)
				((aWolf new:)
					posn: 109 220
					setLoop: 0 1
					setPri: 152
					ignoreActors: 1
					init:
					setCycle: Walk
					setMotion: MoveTo 109 165
				)
				((aWolf new:)
					posn: 176 173
					setPri: 152
					setLoop: 0 1
					ignoreActors: 1
					init:
					setCycle: Walk
					setMotion: MoveTo 248 154
				)
				((aWolf new:)
					posn: 332 139
					setLoop: 1 1
					setPri: 130
					ignoreActors: 1
					init:
					setCycle: Walk
					setMotion: MoveTo 255 128 self
				)
				((aWolf new:)
					posn: 0 160
					setLoop: 0 1
					ignoreActors: 1
					init:
					setCycle: Walk
					setMotion: MoveTo 41 163
				)
				((aWolf new:)
					posn: 360 189
					ignoreActors: 1
					setLoop: 1 1
					init:
					setPri: 152
					setCycle: Walk
					setMotion: MoveTo 315 170
				)
			)
			(4
				(wolfTeller init: aWolf 460 29 176 27)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego setMotion: MoveTo local1 local2 self)
			)
			(1
				(ego normalize:)
				(ego solvePuzzle: 442 2)
				(aDavy
					view: 465
					posn: 0 153
					setLoop: 0 1
					init:
					setPri: 163
					setCycle: Walk
					setMotion: MoveTo 110 151 self
				)
			)
			(2
				(aDavy setMotion: MoveTo 178 150 self)
			)
			(3
				(messager say: 12 6 14 0 self)
			)
			(4
				(aDavy setLoop: 1 1 setMotion: MoveTo 98 148 self)
			)
			(5
				(aDavy setLoop: 7 1 setMotion: MoveTo 60 117 self)
			)
			(6
				(aDavy setLoop: 4 1 setCel: 0)
				(ego
					moveSpeed: 6
					cycleSpeed: 6
					setMotion: DPath 253 172 235 146 173 151 108 154 80 138 self
				)
			)
			(7
				(ego
					moveSpeed: gTheObj_2MoveSpeed
					cycleSpeed: gTheObj_2CycleSpeed
				)
				(curRoom newRoom: 470)
			)
		)
	)
)

(instance sEvent4 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theGGGTheObj_2CycleSpeed egoGait)
				(ego changeGait: 0 setMotion: MoveTo local1 local2)
				(aDancer setCycle: End self)
			)
			(1
				(messager say: 25 6 20 1 self)
			)
			(2
				(aDancer
					setLoop: 1 1
					setCel: 0
					setPri: 141
					setCycle: End self
				)
			)
			(3
				(aDancer
					setLoop: 0 1
					setCel: 0
					setPri: 108
					setCycle: End self
				)
			)
			(4
				(aDancer
					setLoop: 1 1
					setCel: 0
					setPri: 141
					setCycle: End self
				)
			)
			(5
				(messager say: 12 6 8 1 self)
			)
			(6
				(ego setMotion: PolyPath 158 112)
				(aDancer
					setLoop: 0 1
					setCel: 0
					setPri: 100
					setCycle: End self
				)
			)
			(7
				(if (not (ego mover?)) (ego setLoop: 2 1))
				(aDancer
					setLoop: 1 1
					setCel: 0
					setPri: 141
					setCycle: End self
				)
			)
			(8
				(if (not (ego mover?)) (ego setLoop: 3 1))
				(aDancer
					setLoop: 0 1
					setCel: 0
					setPri: 100
					setCycle: End self
				)
			)
			(9
				(if (not (ego mover?)) (ego setLoop: 2 1))
				(aDancer
					setLoop: 1 1
					setCel: 0
					setPri: 141
					setCycle: End self
				)
			)
			(10
				(if (not (ego mover?)) (ego setLoop: 3 1))
				(aDancer
					setLoop: 0 1
					setCel: 0
					setPri: 100
					setCycle: End self
				)
			)
			(11
				(if (not (ego mover?)) (ego setLoop: 2 1))
				(aDancer
					setLoop: 1 1
					setCel: 0
					setPri: 141
					setCycle: End self
				)
			)
			(12
				(ego
					setLoop: 1 1
					setCycle: Rev
					setMotion: PolyPath 192 109
				)
				(aDancer
					setLoop: 0 1
					setCel: 0
					setPri: 100
					setCycle: End self
				)
			)
			(13
				(ego setCycle: 0)
				(aDancer
					setLoop: 1 1
					setCel: 0
					setPri: 141
					setCycle: End self
				)
			)
			(14 (Face ego 60 106 self))
			(15
				(aDancer
					view: 464
					posn: 144 114
					setLoop: 4 1
					setCel: 0
					setPri: 100
					setCycle: End self
				)
			)
			(16
				(howlSound play:)
				(aDancer
					setLoop: 5 1
					posn: 166 113
					setCel: 0
					setCycle: End self
				)
			)
			(17
				(aDancer hide:)
				(ego
					view: 463
					setLoop: 5 1
					setCel: 0
					setPri: 118
					posn: 154 110
					setCycle: End self
				)
			)
			(18
				(= seconds 3)
				(theMusic fade:)
			)
			(19
				(cast eachElementDo: #hide)
				(UpdatePlane
					((curRoom plane?) back: 0 picture: -1 yourself:)
				)
				(= seconds 2)
			)
			(20
				(pFire dispose:)
				(aDavy dispose:)
				(aDancer dispose:)
				(pFiddler dispose:)
				(ego
					view: 35
					setLoop: 0 1
					setCel: 8
					setPri: -1
					posn: 213 155
					setCycle: 0
				)
				(messager say: 25 6 21 1 self)
			)
			(21
				(= [egoStats 18] (ego maxStamina:))
				(= [egoStats 19] (ego maxMana:))
				(= [egoStats 17] (ego maxHealth:))
				((ScriptID 0 21) doit:)
				((ScriptID 7 4) init: 7)
				(theMusic
					number: 460
					setLoop: -1
					play: 0
					fade: 127 10 10 0
				)
				(= seconds 4)
			)
			(22 (= ticks 30))
			(23
				(ego show:)
				(curRoom drawPic: 460)
				(= seconds 2)
			)
			(24
				(ego setCel: 8 setCycle: Beg self)
			)
			(25
				(ego normalize: changeGait: theGGGTheObj_2CycleSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent5 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo local1 local2 self)
			)
			(1
				(ego normalize:)
				(aDavy setCycle: Walk setMotion: MoveTo 280 132 self)
			)
			(2
				(aDavy
					view: 463
					setLoop: 3 1
					setCel: 0
					setCycle: End self
				)
			)
			(3
				(aDavy view: 465 setLoop: 2 1 setCel: 6)
				(= ticks 12)
			)
			(4
				(messager say: 12 6 13 1 self)
			)
			(5
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEvent6 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic2 number: 131 setLoop: 1 play: self)
				(ego setMotion: MoveTo local1 local2 self)
			)
			(1 0)
			(2
				(ego normalize:)
				(self cue:)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWolves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (or local3 (OneOf eventNum 3 4 5 6))
					(self dispose:)
				else
					(= local3 1)
					(switch eventNum
						(1
							(messager say: 12 6 15 1 self)
						)
						(2
							(messager say: 23 6 15 1 self)
						)
					)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance sToWagon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (or (== eventNum 4) (not local4))
					(self cue:)
				else
					(messager say: 12 6 11 1 self)
				)
			)
			(1 (curRoom newRoom: 470))
		)
	)
)

(instance sDontEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath local1 local2 self)
			)
			(1
				(ego normalize:)
				(messager say: 12 6 16 1 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance aDancer of Actor
	(properties
		noun 11
		x 158
		y 114
		view 462
		signal $4001
	)
)

(instance aDavy of Actor
	(properties
		noun 12
		x 319
		y 126
		view 463
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(if (== eventNum 1)
				(messager say: 12 1 42)
			else
				(messager say: 12 1 0 0)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance aWolf of Actor
	(properties
		noun 23
		priority 152
		fixPriority 1
		view 463
		signal $4001
	)
	
	(method (init)
		(super init:)
		(= actions wolfTeller)
	)
)

(instance dvWolf of Actor
	(properties
		noun 23
		priority 152
		fixPriority 1
		view 463
		signal $4001
	)
)

(instance pFiddler of Prop
	(properties
		noun 13
		x 60
		y 106
		priority 95
		fixPriority 1
		view 461
		cel 5
		detailLevel 2
	)
)

(instance pFire of Prop
	(properties
		noun 14
		approachX 143
		approachY 116
		x 141
		y 144
		priority 130
		fixPriority 1
		view 460
		cel 2
		signal $1001
		detailLevel 2
	)
)

(instance fBarrel of Feature
	(properties
		noun 4
		nsLeft 115
		nsTop 104
		nsRight 132
		nsBottom 123
		sightAngle 180
		approachX 143
		approachY 116
		x 123
		y 113
	)
)

(instance fFirePit of Feature
	(properties
		noun 15
		nsLeft 126
		nsTop 117
		nsRight 213
		nsBottom 138
		sightAngle 180
		x 169
		y 127
	)
)

(instance fThinTree of Feature
	(properties
		noun 22
		nsLeft 25
		nsRight 40
		nsBottom 130
		sightAngle 180
		approachX 53
		approachY 124
		x 32
		y 80
	)
)

(instance fBox1 of Feature
	(properties
		noun 7
		nsLeft 47
		nsTop 86
		nsRight 69
		nsBottom 102
		sightAngle 180
		x 58
		y 94
	)
)

(instance fRocks1 of Feature
	(properties
		noun 20
		nsTop 92
		nsRight 22
		nsBottom 129
		sightAngle 180
		approachX 90
		approachY 52
		x 11
		y 110
	)
)

(instance fRocks2 of Feature
	(properties
		noun 20
		nsTop 130
		nsRight 88
		nsBottom 170
		sightAngle 180
		approachX 90
		approachY 52
		x 44
		y 150
	)
)

(instance fBushes of Feature
	(properties
		noun 9
		nsLeft 73
		nsTop 146
		nsRight 229
		nsBottom 189
		sightAngle 180
		x 151
		y 167
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 223 167 277 167 280 176 221 176
						yourself:
					)
					3
					0
					6
					sWolves
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fBox2 of Feature
	(properties
		noun 8
		nsLeft 279
		nsTop 111
		nsRight 298
		nsBottom 130
		sightAngle 180
		x 288
		y 120
	)
)

(instance fRedWagon of Feature
	(properties
		noun 19
		nsTop 36
		nsRight 49
		nsBottom 121
		sightAngle 180
		x 24
		y 78
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(== theVerb 4)
				(!= eventNum 1)
				(!= eventNum 2)
				(cast contains: aDavy)
			)
			(= local1 59)
			(= local2 127)
			(curRoom setScript: sDontEnter)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fBlueWindow of Feature
	(properties
		noun 6
		nsLeft 143
		nsTop 47
		nsRight 179
		nsBottom 74
		sightAngle 180
		x 161
		y 60
	)
)

(instance fRamp of Feature
	(properties
		noun 18
		nsLeft 69
		nsTop 79
		nsRight 105
		nsBottom 111
		sightAngle 180
		approachX 82
		approachY 100
		x 87
		y 95
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 72 102 83 108 77 114 66 107
						yourself:
					)
					3
					7
					6
					sToWagon
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fBlueWagon of Feature
	(properties
		noun 5
		nsLeft 84
		nsTop 21
		nsRight 192
		nsBottom 94
		sightAngle 180
		x 138
		y 57
	)
)

(instance fGreenWindow of Feature
	(properties
		noun 17
		nsLeft 208
		nsTop 63
		nsRight 235
		nsBottom 85
		sightAngle 180
		x 221
		y 74
	)
)

(instance fGreenWagon of Feature
	(properties
		noun 16
		nsLeft 191
		nsTop 34
		nsRight 295
		nsBottom 112
		sightAngle 180
		x 243
		y 73
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(== theVerb 4)
				(!= eventNum 1)
				(!= eventNum 2)
				(cast contains: aDavy)
			)
			(= local1 183)
			(= local2 115)
			(curRoom setScript: sDontEnter)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fStripedWagon of Feature
	(properties
		noun 21
		nsLeft 295
		nsTop 50
		nsRight 319
		nsBottom 142
		sightAngle 180
		x 307
		y 96
	)
	
	(method (doVerb theVerb)
		(if
			(and
				(== theVerb 4)
				(!= eventNum 1)
				(!= eventNum 2)
				(cast contains: aDavy)
			)
			(= local1 280)
			(= local2 138)
			(curRoom setScript: sDontEnter)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fChimney of Feature
	(properties
		noun 10
		nsLeft 118
		nsTop 5
		nsRight 124
		nsBottom 23
		sightAngle 180
		x 121
		y 14
	)
)

(instance daveyTeller of Teller
	(properties
		actionVerb 2
	)
	
	(method (init)
		(super init: &rest)
		(= talker (ScriptID 92))
	)
	
	(method (showCases)
		(super
			showCases:
				6
				(== eventNum 1)
				3
				(== eventNum 1)
				8
				(== eventNum 5)
				9
				(== eventNum 5)
		)
	)
)

(instance wolfTeller of Teller
	(properties
		actionVerb 2
	)
	
	(method (showCases)
		(super
			showCases:
				23
				(== eventNum 1)
				24
				(== eventNum 2)
				25
				(== eventNum 2)
				26
				(== eventNum 2)
				27
				(== eventNum 6)
				28
				(== eventNum 6)
		)
	)
)

(instance heroTeller of Teller
	(properties
		actionVerb 2
	)
	
	(method (sayMessage)
		(switch iconValue
			(35
				(self clean:)
				(curRoom setScript: sLeave)
			)
			(else 
				(super sayMessage: &rest)
			)
		)
	)
	
	(method (showCases)
		(if (not (cast contains: aDavy)) (self thiefSign: 0))
		(super
			showCases: 7 (== eventNum 1) 10 (== eventNum 5) 29 (== eventNum 6)
		)
	)
)

(instance howlSound of Sound
	(properties
		number 131
	)
)
