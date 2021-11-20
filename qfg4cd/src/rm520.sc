;;; Sierra Script 1.0 - (do not remove this comment)
(script# 520)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use GloryTalker)
(use Intrface)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm520 0
	rusalkaTalkerYoung 1
	rusalkaTalkerOld 2
)

(local
	local0
	gTheObj_2MoveSpeed
	gTheObj_2CycleSpeed
	local3
	gTheObj_2CycleSpeed_2
)
(instance rm520 of GloryRm
	(properties
		noun 1
		picture 520
		horizon 70
		north 551
		topX 225
	)
	
	(method (init)
		(if debugging
			(= local0 (GetNumber {Event #?}))
		else
			(= local0
				(cond 
					((Btst 117) 7)
					(
					(and (== heroType 3) (Btst 228) (not (Btst 117))) 6)
					(
						(and
							(== heroType 3)
							(Btst 227)
							(Btst 313)
							(not (Btst 228))
						)
						5
					)
					(
						(and
							(Btst 116)
							(Btst 312)
							(not (Btst 227))
							(== heroType 3)
						)
						4
					)
					((and (Btst 320) (not (Btst 116))) 3)
					((Btst 312) 2)
					((not (Btst 312)) 1)
					(else 0)
				)
			)
		)
		(ego
			init:
			posn: 197 67
			normalize:
			setPri: 58
			setScaler: Scaler 130 47 189 56
		)
		(super init: &rest)
		(theMusic number: 520 loop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						0
						14
						195
						87
						158
						105
						151
						104
						181
						87
						176
						86
						65
						121
						103
						123
						109
						125
						100
						131
						154
						132
						169
						128
						187
						135
						248
						133
						251
						135
						279
						123
						252
						115
						271
						105
						228
						96
						245
						90
						214
						84
						199
						114
						189
						115
						200
						88
						319
						0
						319
						189
						0
						189
					yourself:
				)
		)
		(fBigTree init: approachVerbs: 4)
		(fFlowers init: approachVerbs: 4)
		(fSmallRock init: approachVerbs: 4)
		(fBigRock init: approachVerbs: 4)
		(fLilyPads init: approachVerbs: 4)
		(fReflection init: approachVerbs: 4)
		(fTrees init: approachVerbs: 4)
		(fSky init: approachVerbs: 4)
		(fLake1 init: approachVerbs: 4)
		(fLake2 init: approachVerbs: 4)
		(curRoom setScript: sComeOnIn)
		(theGame save: 1)
	)
	
	(method (dispose)
		(theMusic fade: 0)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(return
			(if
			(OneOf theVerb 83 87 86 88 79 11 91 93 21 36 37 82 84)
				(cond 
					(local3 (aRusalka setScript: sGoUnder))
					((== theVerb 11) (messager say: 0 11 0) (return 1))
					(else (messager say: 1 37 0 0) (return 1))
				)
			else
				(super doVerb: theVerb)
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
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					moveSpeed: 6
					cycleSpeed: 6
					setMotion: MoveTo 232 81 self
				)
			)
			(1
				(ego setPri: 64 setMotion: MoveTo 230 82 self)
			)
			(2
				(ego setPri: 80 setMotion: MoveTo 214 83 self)
			)
			(3
				(ego setLoop: 2 1 setMotion: MoveTo 212 79 self)
			)
			(4
				(ego
					setPri: 82
					setLoop: 2 1
					setMotion: MoveTo 207 93 self
				)
			)
			(5
				(ego setMotion: MoveTo 198 86 self)
			)
			(6
				(ego setPri: 119 setMotion: MoveTo 166 124 self)
			)
			(7
				(ego
					moveSpeed: gTheObj_2MoveSpeed
					cycleSpeed: gTheObj_2CycleSpeed
					normalize:
				)
				(= ticks 6)
			)
			(8
				(if (!= local0 7)
					(self setScript: sRusalkaUp)
				else
					(self cue:)
				)
			)
			(9
				(switch local0
					(1 (curRoom setScript: sEvent1))
					(2 (curRoom setScript: sEvent2))
					(3 (curRoom setScript: sEvent3))
					(4 (curRoom setScript: sEvent4))
					(5 (curRoom setScript: sEvent5))
					(6 (curRoom setScript: sEvent6))
					(else 
						(theGame handsOn:)
						(self dispose:)
					)
				)
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
				(aRusalka setScript: 0)
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					setMotion: 0
					moveSpeed: 6
					cycleSpeed: 6
					setMotion: MoveTo 198 86 self
				)
			)
			(1
				(ego
					setPri: 82
					setLoop: 3 1
					setMotion: MoveTo 207 93 self
				)
			)
			(2
				(ego setLoop: -1 setMotion: MoveTo 212 79 self)
			)
			(3
				(ego setPri: 80 setMotion: MoveTo 214 83 self)
			)
			(4
				(ego setMotion: MoveTo 230 82 self)
			)
			(5
				(ego setPri: 64 setMotion: MoveTo 232 81 self)
			)
			(6
				(ego setPri: 53 setMotion: MoveTo 207 73 self)
			)
			(7
				(ego
					moveSpeed: gTheObj_2MoveSpeed
					cycleSpeed: gTheObj_2CycleSpeed
				)
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance sRusalkaUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic number: 521 setLoop: -1 play:)
				(= local3 1)
				(if (== heroType 3)
					(messager say: 12 6 2 0 self)
				else
					(self cue:)
				)
			)
			(1
				(sndWater play:)
				(if (== local0 6) (aRusalka view: 525))
				(aRusalka init: setCycle: End self)
			)
			(2
				(sndWater stop:)
				(sComeOnIn cue:)
			)
		)
	)
)

(instance sEvent1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 2 6 3 0 self))
			(1
				(aRusalka
					setLoop: 1 1
					setCel: 0
					posn: 96 189
					setCycle: End self
				)
			)
			(2
				(Bset 237)
				(heroTeller init: ego 520 20 128 13)
				(rusalkaTeller init: aRusalka 520 20 172 13)
				(theGame handsOn:)
				(aRusalka setScript: sRusalkaTimer)
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
				(messager say: 2 6 21 0 self)
			)
			(1
				(Bset 320)
				(heroTeller init: ego 520 20 128 14)
				(rusalkaTeller init: aRusalka 520 20 172 14)
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
				(DailyMsg 2 6 22 sEvent3 520)
			)
			(1
				(heroTeller init: ego 520 20 128 15)
				(rusalkaTeller init: aRusalka 520 20 172 15)
				(theGame handsOn:)
				(self dispose:)
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
				(messager say: 2 6 23 0 self)
			)
			(1
				(heroTeller init: ego 520 20 128 16)
				(rusalkaTeller init: aRusalka 520 20 172 16)
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
				(messager say: 2 6 24 0 self)
			)
			(1
				(heroTeller init: ego 520 20 128 17)
				(rusalkaTeller init: aRusalka 520 20 172 17)
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
				(messager say: 2 6 25 0 self)
			)
			(1
				(heroTeller init: ego 520 20 128 18)
				(rusalkaTeller init: aRusalka 520 20 172 18)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRusalkaTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks (Random 1 6)))
			(1 (= seconds 10))
			(2
				(if (or (Btst 147) (Btst 148))
					(self cue:)
				else
					(DailyMsg 2 6 5 sRusalkaTimer 520)
				)
			)
			(3 (self changeState: 0))
		)
	)
)

(instance sDies of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local3
					(theGame handsOff:)
					(aRusalka setScript: 0)
					(= gTheObj_2CycleSpeed_2 (ego cycleSpeed?))
					(ego
						cycleSpeed: defaultCycles
						moveSpeed: defaultCycles
						setMotion: PolyPath 156 131 self
					)
				else
					(self dispose:)
				)
			)
			(1
				(ego
					view: 523
					posn: 152 132
					setLoop: 0 1
					setCel: 0
					setCycle: End self
				)
			)
			(2
				(if (== local0 6) (aRusalka view: 525))
				(aRusalka
					setLoop: 1 1
					setCel: 7
					ignoreActors: 1
					setMotion: MoveTo 104 181 self
				)
			)
			(3
				(ego hide:)
				(aRusalka
					view: (if (== local0 6) 525 else 521)
					setLoop: (if (== local0 6) 2 else 0) 1
					cel: 0
					posn: 100 179
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(4
				(if (== local0 6)
					(ego addHonor: 500)
					(messager say: 2 6 26 0 self)
				else
					(messager say: 2 4 5 0 self)
				)
			)
			(5
				(sndWater play:)
				(aRusalka
					setLoop: (if (== local0 6) 3 else 1) 1
					setCel: 0
					posn: 101 180
					setCycle: End self
				)
			)
			(6
				(sndWater stop:)
				(if (== local0 6)
					(aRusalka hide: dispose:)
					(ego setLoop: 1 1 setCel: 0 show: setCycle: End self)
				else
					(EgoDead 6 520)
				)
			)
			(7
				(ego setLoop: 2 1 setCel: 0 setCycle: End self)
			)
			(8
				(ego solvePuzzle: 531 15 8 8)
				(Bset 117)
				(ego normalize: setMotion: MoveTo 166 124 self)
			)
			(9
				(= local3 0)
				(heroTeller dispose:)
				(ego
					actions: 0
					cycleSpeed: gTheObj_2CycleSpeed_2
					moveSpeed: gTheObj_2CycleSpeed_2
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoUnder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local3 0)
				(sndWater play:)
				(messager say: 12 6 69 0 self)
			)
			(1
				(aRusalka setLoop: 0 1 cycleSpeed: 12 setCycle: Beg self)
			)
			(2
				(sndWater stop:)
				(ego addHonor: -50)
				(heroTeller dispose:)
				(aRusalka hide: dispose:)
				(theMusic number: 520 loop: -1 play:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEyesDoIt of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (self cue:))
			(1 (= seconds (Random 2 12)))
			(2
				(rusalkaEyesRoll setCel: 0 cycleSpeed: 12 setCycle: End)
				(= seconds 1)
			)
			(3
				(rusalkaEyesRoll setCel: 0 setCycle: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance aRusalka of Actor
	(properties
		noun 2
		x 96
		y 187
		view 522
		signal $5001
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (Btst 228)
					(messager say: 2 1 71)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(4
				(switch local0
					(1
						(if (not (Btst 312))
							(curRoom setScript: sDies)
						else
							(messager say: 2 4 1)
						)
					)
					(6
						(if (not (Btst 117)) (curRoom setScript: sDies) else 0)
					)
					(7 0)
					(else  (messager say: 2 4 1))
				)
			)
			(23
				(ego solvePuzzle: 444 6)
				(if (Btst 312)
					(ego addHonor: 5)
					(messager say: 2 23 68)
				else
					(aRusalka setScript: 0)
					(ego use: 8 1)
					(Bset 312)
					(rusalkaTeller dispose:)
					(heroTeller dispose:)
					(rusalkaTeller init: aRusalka 520 20 172 19)
					(heroTeller init: ego 520 20 128 19)
					(messager say: 2 23 0 0)
				)
			)
			(59
				(ego solvePuzzle: 444 6)
				(if (Btst 312)
					(ego addHonor: 5)
					(messager say: 2 59 68)
				else
					(aRusalka setScript: 0)
					(ego use: 40 1)
					(Bset 312)
					(rusalkaTeller dispose:)
					(heroTeller dispose:)
					(rusalkaTeller init: aRusalka 520 20 172 19)
					(heroTeller init: ego 520 20 128 19)
					(messager say: 2 59 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fBigTree of Feature
	(properties
		noun 3
		nsLeft 262
		nsRight 319
		nsBottom 102
		sightAngle 180
		approachX 271
		approachY 105
		x 290
		y 51
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 188 87 206 87 203 92 182 92
						yourself:
					)
					3
					6
					7
					sLeave
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fFlowers of Feature
	(properties
		noun 4
		nsLeft 220
		nsTop 78
		nsRight 297
		nsBottom 115
		sightAngle 180
		x 258
		y 96
	)
)

(instance fSmallRock of Feature
	(properties
		noun 5
		nsLeft 151
		nsTop 130
		nsRight 185
		nsBottom 144
		sightAngle 180
		x 168
		y 137
	)
)

(instance fBigRock of Feature
	(properties
		noun 6
		nsLeft 269
		nsTop 135
		nsRight 319
		nsBottom 171
		sightAngle 180
		x 294
		y 153
	)
	
	(method (init)
		(super init: &rest)
		(= heading
			(((ScriptID 49 0) new:)
				init:
					((Polygon new:)
						type: 1
						init: 103 126 170 126 167 133 96 133
						yourself:
					)
					2
					4
					5
					sDies
				yourself:
			)
		)
	)
	
	(method (dispose)
		(if heading (heading dispose:))
		(super dispose: &rest)
	)
)

(instance fLilyPads of Feature
	(properties
		noun 7
		nsTop 136
		nsRight 62
		nsBottom 187
		sightAngle 180
		x 31
		y 161
	)
)

(instance fReflection of Feature
	(properties
		noun 8
		nsTop 69
		nsRight 89
		nsBottom 101
		sightAngle 180
		x 44
		y 85
	)
)

(instance fTrees of Feature
	(properties
		noun 9
		nsTop 22
		nsRight 262
		nsBottom 57
		sightAngle 180
		approachX 167
		approachY 90
		x 131
		y 39
	)
)

(instance fSky of Feature
	(properties
		noun 10
		nsRight 262
		nsBottom 22
		sightAngle 180
		approachX 167
		approachY 90
		x 131
		y 11
	)
)

(instance fLake1 of Feature
	(properties
		noun 11
		nsTop 101
		nsRight 62
		nsBottom 136
		sightAngle 180
		x 31
		y 118
	)
)

(instance fLake2 of Feature
	(properties
		noun 11
		nsLeft 63
		nsTop 131
		nsRight 319
		nsBottom 189
		sightAngle 180
		x 191
		y 136
	)
	
	(method (init)
		(super init: &rest)
		(walkHandler addToFront: self)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 3)
			(if
				(and
					(== heroType 3)
					(Btst 228)
					(not (Btst 117))
					(not (Btst 117))
					(cast contains: aRusalka)
					(not (curRoom script?))
				)
				(curRoom setScript: sDies)
			else
				(ego
					setMotion: PolyPath ((User curEvent?) x?) ((User curEvent?) y?)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance rusalkaTeller of Teller
	(properties
		title 1
		actionVerb 2
	)
	
	(method (init)
		(super init: &rest)
		(if (== local0 6)
			(= talker rusalkaTalkerOld)
		else
			(= talker rusalkaTalkerYoung)
		)
	)
	
	(method (sayMessage)
		(switch iconValue
			(60 (ego get: 38))
		)
		(super sayMessage: &rest)
	)
)

(instance heroTeller of Teller
	(properties
		actionVerb 2
	)
)

(instance sndWater of Sound
	(properties
		number 982
	)
)

(instance rusalkaTalkerYoung of GloryTalker
	(properties
		x 124
		y 1
		talkWidth 150
		view 526
		textX -150
		textY 15
	)
	
	(method (init)
		(super
			init: rusalkaMouth 0 rusalkaEyes rusalkaYFrame &rest
		)
		(drip1 init: cycleSpeed: 12 setCycle: Fwd)
		(drip2 init: cycleSpeed: 12 setCycle: Fwd)
		(drip3 init: cycleSpeed: 12 setCycle: Fwd)
		(drip4 init: cycleSpeed: 12 setCycle: Fwd)
		(drip5 init: cycleSpeed: 12 setCycle: Fwd)
	)
	
	(method (dispose param1)
		(if (or (not argc) param1)
			(drip1 dispose:)
			(drip2 dispose:)
			(drip3 dispose:)
			(drip4 dispose:)
			(drip5 dispose:)
		)
		(super dispose: param1)
	)
)

(instance rusalkaYFrame of View
	(properties
		x 124
		y 1
		view 526
	)
)

(instance rusalkaMouth of Prop
	(properties
		x 233
		y 42
		view 526
		loop 1
	)
)

(instance rusalkaEyes of Prop
	(properties
		x 228
		y 24
		view 526
		loop 2
	)
)

(instance drip1 of Prop
	(properties
		x 205
		y 126
		view 526
		loop 3
	)
)

(instance drip2 of Prop
	(properties
		x 245
		y 119
		view 526
		loop 4
	)
)

(instance drip3 of Prop
	(properties
		x 212
		y 107
		view 526
		loop 5
	)
)

(instance drip4 of Prop
	(properties
		x 282
		y 107
		view 526
		loop 6
	)
)

(instance drip5 of Prop
	(properties
		x 293
		y 103
		view 526
		loop 7
	)
)

(instance rusalkaTalkerOld of GloryTalker
	(properties
		x 124
		y 1
		talkWidth 150
		view 527
		textX -150
		textY 5
	)
	
	(method (init)
		(super
			init: rusalkaMouthOld 0 rusalkaEyesOld rusalkaFrame &rest
		)
		(rusalkaDrip1 init: cycleSpeed: 12 setCycle: Fwd)
		(rusalkaDrip2 init: cycleSpeed: 12 setCycle: Fwd)
		(rusalkaDrip3 init: cycleSpeed: 12 setCycle: Fwd)
		(rusalkaDrip4 init: cycleSpeed: 12 setCycle: Fwd)
		(rusalkaEyesRoll init: setScript: sEyesDoIt)
	)
	
	(method (dispose param1)
		(if (or (not argc) param1)
			(rusalkaDrip1 dispose:)
			(rusalkaDrip2 dispose:)
			(rusalkaDrip3 dispose:)
			(rusalkaDrip4 dispose:)
			(rusalkaEyesRoll dispose:)
		)
		(super dispose: param1)
	)
)

(instance rusalkaFrame of View
	(properties
		x 124
		y 1
		view 527
	)
)

(instance rusalkaMouthOld of Prop
	(properties
		x 225
		y 43
		view 527
		loop 1
	)
)

(instance rusalkaEyesOld of Prop
	(properties
		x 218
		y 30
		view 527
		loop 2
	)
)

(instance rusalkaEyesRoll of Prop
	(properties
		x 216
		y 30
		view 527
		loop 3
	)
)

(instance rusalkaDrip1 of Prop
	(properties
		x 184
		y 123
		view 527
		loop 4
	)
)

(instance rusalkaDrip2 of Prop
	(properties
		x 294
		y 120
		view 527
		loop 5
	)
)

(instance rusalkaDrip3 of Prop
	(properties
		x 278
		y 118
		view 527
		loop 6
	)
)

(instance rusalkaDrip4 of Prop
	(properties
		x 226
		y 129
		view 527
		loop 7
	)
)
