;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include sci.sh)
(use Main)
(use GloryRm)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Grooper)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm440 0
)

(local
	local0
	local1
	local2
	local3
	local4
)
(instance rm440 of GloryRm
	(properties
		noun 1
		picture 440
		south 580
	)
	
	(method (init)
		(theMusic number: 440 setLoop: -1 play:)
		(ego init: setScaler: Scaler 120 62 171 47)
		(water1 setCycle: Fwd setPri: 0 approachVerbs: 4 24 init:)
		(water2 setCycle: Fwd setPri: 0 approachVerbs: 4 24 init:)
		(water3 setCycle: Fwd setPri: 0 approachVerbs: 4 24 init:)
		(waterfall setCycle: Fwd setPri: 0 init:)
		(if (not (Btst 189)) (tree init: approachVerbs: 4 25))
		(if (Btst 199)
			(grass init: approachVerbs: 4 25 14 setPri: 1)
			(bonsai init: approachVerbs: 4 25 14 setPri: 169)
		else
			(hole init: approachVerbs: 4 25 14)
			(dirtPile init: approachVerbs: 4 25 14)
		)
		(lanternFront init: approachVerbs: 4)
		(lanternBack init: approachVerbs: 4)
		(fruitTree init: approachVerbs: 4 25)
		(isleFlowers init: approachVerbs: 4)
		(waterfallFeat init:)
		(bridge init:)
		(nearBridge init:)
		(flowers2 init:)
		(flowers3 init:)
		(pathWay init:)
		(pond init: approachVerbs: 4 24)
		(if (!= prevRoomNum 110)
			(self setScript: sEnter)
			(if Night
				(= local1 1)
				(lightBack init: setCycle: Fwd approachVerbs: 4)
				(= local0 1)
				(lightFront
					init:
					setLoop: 1 1
					setCycle: Fwd
					approachVerbs: 4
				)
			)
		else
			(self setScript: sFromDream)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						619
						489
						276
						489
						276
						176
						224
						176
						224
						162
						282
						158
						282
						148
						163
						161
						69
						147
						47
						130
						104
						90
						134
						86
						171
						100
						198
						85
						179
						74
						237
						66
						227
						47
						198
						47
						158
						56
						90
						78
						107
						86
						93
						89
						34
						129
						40
						142
						59
						151
						169
						169
						205
						489
						-300
						489
						-300
						-300
						619
						-300
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(return
			(cond 
				((== theVerb 104) (self setScript: sSleepScr))
				((== theVerb 81) (self setScript: sDetect))
				((== theVerb 87)
					(theGame handsOff:)
					(self setScript: (ScriptID 37) 0 fruitTree tulipFeat)
				)
				((== theVerb 11)
					(frontBank init:)
					(backBank init:)
					(walkHandler add: frontBank backBank)
					(theGame handsOff:)
					(if (> (ego y?) 100)
						(self setScript: sToWater 0 1)
					else
						(self setScript: sToWater 0 0)
					)
				)
				((OneOf theVerb 86 93 79 88) (messager say: 1 86 0) (return 1))
				(else (super doVerb: theVerb))
			)
		)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
	
	(method (notify param1)
		(switch param1
			(-2
				(Bclr 149)
				(messager say: 2 6 16)
				(curRoom setScript: sToBank)
			)
		)
	)
)

(instance sToBank of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bclr 149)
				(if register
					(ego setMotion: MoveTo 160 147 self)
				else
					(ego setMotion: MoveTo 172 111 self)
				)
			)
			(1
				(walkHandler delete: frontBank backBank)
				((curRoom obstacles?) dispose:)
				(curRoom
					obstacles: 0
					addObstacle:
						((Polygon new:)
							type: 2
							init:
								619
								489
								276
								489
								276
								176
								224
								176
								224
								162
								282
								158
								282
								148
								163
								161
								69
								147
								47
								130
								104
								90
								134
								86
								171
								100
								198
								85
								179
								74
								237
								66
								227
								47
								198
								47
								158
								56
								90
								78
								107
								86
								93
								89
								34
								129
								40
								142
								59
								151
								169
								169
								205
								489
								-300
								489
								-300
								-300
								619
								-300
							yourself:
						)
				)
				(glideShadow dispose:)
				(if register
					(ego normalize: 2 setMotion: MoveTo 160 161 self)
				else
					(ego z: 0 normalize: 3 setMotion: MoveTo 171 100 self)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sToWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(ego setMotion: PolyPath 160 161 self)
				else
					(ego setMotion: PolyPath 171 100 self)
				)
			)
			(1
				(if
				(and (fountain script?) (== (sTulip state?) 1))
					(sTulip cue:)
				)
				(walkHandler add: frontBank backBank)
				((curRoom obstacles?) dispose:)
				(curRoom
					obstacles: 0
					addObstacle:
						((Polygon new:)
							type: 3
							init:
								114
								103
								135
								103
								157
								107
								165
								111
								489
								111
								206
								104
								226
								111
								243
								111
								243
								105
								255
								105
								270
								109
								293
								115
								266
								136
								168
								149
								139
								145
								86
								118
							yourself:
						)
				)
				(if register
					(ego setMotion: MoveTo 160 147 self)
				else
					(ego normalize: 3 setMotion: MoveTo 172 111 self)
				)
			)
			(2
				(self setScript: (ScriptID 12) self)
			)
			(3
				(Bset 149)
				(ego
					setLoop: -1
					setLoop: Grooper
					setCycle: Walk
					view: 5
					z: 5
				)
				(ego loop: (if register 3 else 2))
				(glideShadow init:)
				(theGame handsOn:)
				(theIconBar disable: 2 4 5 6 7)
				(self dispose:)
			)
		)
	)
)

(instance sOpenTulip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Face ego (tulip x?) (tulip y?) self)
			)
			(1
				(= local2 1)
				(tulip setCel: 0 setLoop: 4 1 setCycle: End self)
			)
			(2
				(tulip setLoop: 5 1 setCycle: Fwd)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDetect of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 81 0 0 self)
			)
			(1
				(frontSpark init: setCycle: Fwd)
				(backSpark init: setCycle: Fwd)
				(treeSpark init: setPri: 45 setCycle: Fwd)
				(waterSpark init: setCycle: Fwd)
				(= seconds 6)
			)
			(2
				(frontSpark dispose:)
				(backSpark dispose:)
				(treeSpark dispose:)
				(waterSpark dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTrigger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Face ego (register x?) (register y?) self)
			)
			(1
				(cond 
					((== register pond)
						(if
						(and (not (fountain script?)) (not [egoStats 38]))
							(if (not (cast contains: fountain)) (fountain init:))
							(fountain setScript: sTulip)
							(self dispose:)
						else
							(messager say: 2 6 18)
							(theGame handsOn:)
							(self dispose:)
						)
					)
					((== register lanternFront)
						(if local0
							(= local0 0)
							(lightFront loop: 0 setCycle: 0 dispose:)
							(theGame handsOn:)
							(self dispose:)
						else
							(= local0 1)
							(lightFront init: setCycle: End self)
						)
					)
					(else
						(if local1
							(= local1 0)
							(lightBack setCycle: 0 dispose:)
						else
							(= local1 1)
							(lightBack init: setCycle: Fwd)
						)
						(theGame handsOn:)
						(self dispose:)
					)
				)
			)
			(2
				(lightFront setLoop: 1 1 setCycle: Fwd)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTulip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fountain setLoop: 0 1 setCycle: End self)
			)
			(1
				(theGame handsOn:)
				(if (not (features contains: tulipFeat))
					(tulipFeat init:)
				)
				(fountain setLoop: 1 1 setCycle: Fwd)
				(tulip init: setLoop: 2 1 setCel: 0 setCycle: Fwd)
				(= seconds 20)
			)
			(2
				(if local2
					(= local2 0)
					(tulip
						setLoop: (if [egoStats 38] 3 else 4) 1
						setCel: 4
						setCycle: Beg self
					)
				else
					(self cue:)
				)
			)
			(3
				(tulip dispose:)
				(fountain setLoop: 0 1 setCel: 10 setCycle: Beg fountain)
				(self dispose:)
			)
		)
	)
)

(instance sSleepScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 118 83 self)
			)
			(1
				(ego
					view: 35
					setLoop: 0 1
					setCel: 0
					posn: (+ (ego x?) 30) (- (ego y?) 2)
					setCycle: End self
				)
			)
			(2 (= seconds 5))
			(3
				(if (<= dreamNum 10)
					(++ dreamNum)
					(curRoom newRoom: 110)
				else
					(curRoom setScript: sFromDream)
				)
			)
		)
	)
)

(instance sFromDream of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Palette palSET_FLAG 0 255 100)
				(ego
					view: 35
					setLoop: 4 1
					setCel: 0
					x: 148
					y: 81
					setCycle: 0
				)
				(= [egoStats 19] (ego maxMana:))
				(= [egoStats 18] (ego maxStamina:))
				(= [egoStats 17] (ego maxHealth:))
				(= cycles 1)
			)
			(1
				((ScriptID 7 7) init: 6)
				(= seconds 5)
			)
			(2 (ego setCycle: End self))
			(3
				(ego posn: (ego x?) (- (ego y?) 2) normalize:)
				(cond 
					((OneOf dreamNum 1 2 3 4 5 7 8 9 10) (messager say: 2 6 2 1 self))
					((<= dreamNum 11) (messager say: 2 6 1 1 self))
					(else (self cue:))
				)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					posn: 239 225
					normalize: 7
					setMotion: MoveTo 189 175 self
				)
			)
			(1
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(2
				(if (Btst 188)
					(self cue:)
				else
					(Bset 188)
					(messager say: 2 6 3 0 self)
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetWater of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 158 152 self)
			)
			(1
				(= register (ego cycleSpeed?))
				(ego
					view: 4
					setLoop: 0 1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
			)
			(2
				(ego drop: 9 1 get: 10 1)
				(theIconBar disable: 6)
				(messager say: 2 6 20 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego normalize: 3 setSpeed: register setHeading: 180 self)
			)
			(5
				(ego setMotion: MoveTo 160 161 self)
			)
			(6
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
				(= register (ego cycleSpeed?))
				(ego
					view: 4
					setLoop: 2 1
					setCel: 0
					setSpeed: defaultCycles
					setCycle: End self
				)
			)
			(1
				(ego get: 40)
				(messager say: 7 4 0 0 self)
			)
			(2 (ego setCycle: Beg self))
			(3
				(ego setSpeed: register normalize: 2)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sBonsai of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego drop: 48 1)
				(theGame handsOff:)
				(ego setHeading: 135 self)
			)
			(1
				(= register (ego cycleSpeed?))
				(ego
					view: 4
					setCel: 0
					setLoop: 2 1
					cycleSpeed: defaultCycles
					setCycle: End self
				)
			)
			(2 (= ticks 120))
			(3
				(hole dispose:)
				(dirtPile dispose:)
				(grass approachVerbs: 4 25 14 init:)
				(bonsai
					init:
					approachVerbs: 4 25 14
					setPri: 169
					setCel: 0
				)
				(ego solvePuzzle: 417 6)
				(dirt
					signal: (| (dirt signal?) $0001)
					init:
					cycleSpeed: (* 2 defaultCycles)
					setCycle: End self
				)
			)
			(4
				(dirt dispose:)
				(= ticks 120)
				(messager say: 13 14 0 0 self)
			)
			(5 (= ticks 120))
			(6
				(bonsai
					signal: (| (bonsai signal?) $0001)
					cycleSpeed: (* 2 defaultCycles)
					setCycle: End self
				)
				(Bset 257)
				(Bset 199)
			)
			(7
				(bonsai signal: (& (bonsai signal?) $fffe))
				(ego setCycle: Beg self)
			)
			(8
				(ego setSpeed: register normalize: 4)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance water1 of Prop
	(properties
		noun 10
		approachX 160
		approachY 161
		x 128
		y 119
		view 440
		cel 3
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(pond doVerb: theVerb)
	)
)

(instance water2 of Prop
	(properties
		noun 10
		approachX 160
		approachY 161
		x 178
		y 131
		view 440
		loop 1
		cel 2
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(pond doVerb: theVerb)
	)
)

(instance water3 of Prop
	(properties
		noun 10
		approachX 160
		approachY 161
		x 236
		y 127
		view 440
		loop 2
		cel 1
		signal $4001
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(pond doVerb: theVerb)
	)
)

(instance waterfall of Prop
	(properties
		noun 8
		x 251
		y 108
		view 440
		loop 3
		signal $4001
		detailLevel 1
	)
	
	(method (doVerb theVerb)
		(waterfallFeat doVerb: theVerb)
	)
)

(instance fountain of Prop
	(properties
		noun 15
		x 150
		y 120
		view 442
		signal $4001
		detailLevel 1
	)
	
	(method (doVerb theVerb)
		(tulipFeat doVerb: theVerb)
	)
	
	(method (cue)
		(tulipFeat dispose:)
		(if local3
			(ego learn: 38 100)
			(ego solvePuzzle: 499 6 2)
			(messager say: 15 87 12)
		)
		(self dispose:)
	)
)

(instance tulip of Prop
	(properties
		noun 15
		x 150
		y 120
		view 442
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(tulipFeat doVerb: theVerb)
	)
)

(instance lightFront of Prop
	(properties
		noun 3
		x 52
		y 87
		view 443
		signal $4001
	)
	
	(method (doVerb theVerb)
		(lanternFront doVerb: theVerb)
	)
)

(instance lightBack of Prop
	(properties
		noun 4
		x 137
		y 45
		view 443
		loop 2
		signal $4001
	)
	
	(method (doVerb theVerb)
		(lanternBack doVerb: theVerb)
	)
)

(instance frontSpark of Prop
	(properties
		x 40
		y 62
		view 440
		loop 5
		signal $4001
	)
)

(instance backSpark of Prop
	(properties
		x 124
		y 24
		view 440
		loop 5
		signal $4001
	)
)

(instance treeSpark of Prop
	(properties
		x 208
		y 1
		view 440
		loop 6
		signal $4001
	)
)

(instance waterSpark of Prop
	(properties
		x 139
		y 113
		view 440
		loop 4
		signal $4001
	)
)

(instance tree of View
	(properties
		approachX 208
		approachY 49
		x 188
		y 7
		view 440
		loop 7
		cel 11
		signal $4000
	)
	
	(method (doVerb theVerb)
		(fruitTree doVerb: theVerb)
	)
)

(instance glideShadow of View
	(properties
		view 5
		loop 8
		signal $4001
	)
	
	(method (doit)
		(= x (ego x?))
		(= y (ego y?))
		(super doit: &rest)
	)
)

(instance grass of View
	(properties
		approachX 258
		approachY 165
		x 246
		y 143
		view 443
		loop 3
		signal $4000
	)
	
	(method (doVerb)
		(bonsai doVerb: &rest)
	)
)

(instance dirt of Prop
	(properties
		approachX 258
		approachY 165
		x 274
		y 175
		view 443
		loop 4
		signal $4000
	)
)

(instance bonsai of Prop
	(properties
		noun 16
		approachX 258
		approachY 165
		x 258
		y 167
		view 443
		loop 5
		cel 9
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(25
				(ego drop: 10 1)
				(ego get: 9 1)
				(if (not local4) (= local4 1) (ego addHonor: 5))
				(messager say: noun theVerb 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lanternFront of Feature
	(properties
		noun 3
		nsLeft 40
		nsTop 62
		nsRight 62
		nsBottom 102
		sightAngle 44
		x 51
		y 82
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (cast contains: lightFront)
					(messager say: noun theVerb 4)
				else
					(messager say: noun theVerb 5)
				)
			)
			(82
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 11) 0 self)
			)
			(-82
				(curRoom setScript: sTrigger 0 self)
			)
			(4
				(if (not (Btst 337))
					(Bset 337)
					(ego get: 0 30)
					(messager say: noun theVerb 0)
				else
					(messager say: noun theVerb 19)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance lanternBack of Feature
	(properties
		noun 4
		nsLeft 131
		nsTop 31
		nsRight 142
		nsBottom 52
		sightAngle 44
		x 136
		y 41
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (cast contains: lightBack)
					(messager say: noun theVerb 4)
				else
					(messager say: noun theVerb 5)
				)
			)
			(82
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 11) 0 self)
			)
			(-82
				(curRoom setScript: sTrigger 0 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fruitTree of Feature
	(properties
		noun 5
		nsLeft 192
		nsRight 254
		nsBottom 33
		sightAngle 44
		approachX 208
		approachY 49
		x 223
		y 16
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (cast contains: tree)
					(messager say: 5 1 6)
				else
					(messager say: 5 1 7)
				)
			)
			(4
				(if (cast contains: tree)
					(messager say: 5 4 6)
					(tree dispose:)
				else
					(messager say: 5 4 7)
				)
			)
			(25
				(ego drop: 10 1)
				(ego get: 9 1)
				(messager say: noun theVerb 0)
			)
			(87 (curRoom doVerb: theVerb))
			(-87
				(if (cast contains: tree)
					(Bset 189)
					(ego get: 1 1)
					(messager say: 5 87 6)
					(tree dispose:)
				else
					(messager say: 5 87 7)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance isleFlowers of Feature
	(properties
		noun 7
		nsLeft 198
		nsTop 62
		nsRight 245
		nsBottom 82
		sightAngle 44
		approachX 210
		approachY 72
		x 221
		y 72
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 2
					init: 186 70 214 64 229 57 245 62 249 68 239 77 217 81 187 76
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sGetFlowers)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance waterfallFeat of Feature
	(properties
		noun 8
		nsLeft 234
		nsTop 70
		nsRight 289
		nsBottom 106
		sightAngle 44
		x 261
		y 88
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 227 104 252 80 270 74 291 75 276 85 271 93 267 109
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance bridge of Feature
	(properties
		noun 9
		nsLeft 64
		nsTop 84
		nsRight 123
		nsBottom 115
		sightAngle 44
		x 93
		y 99
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 64 105 84 90 99 85 125 90 124 96 118 95 82 111
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance nearBridge of Feature
	(properties
		noun 11
		nsLeft 89
		nsTop 95
		nsRight 135
		nsBottom 127
		sightAngle 44
		x 112
		y 111
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 89 109 114 98 131 100 106 118 101 113
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance hole of Feature
	(properties
		noun 13
		nsLeft 248
		nsTop 163
		nsRight 298
		nsBottom 176
		sightAngle 44
		approachX 258
		approachY 165
		x 273
		y 169
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(14
				(curRoom setScript: sBonsai)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance dirtPile of Feature
	(properties
		noun 14
		nsLeft 269
		nsTop 149
		nsRight 309
		nsBottom 164
		sightAngle 44
		x 289
		y 156
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(14
				(curRoom setScript: sBonsai)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tulipFeat of Feature
	(properties
		noun 15
		nsLeft 135
		nsTop 85
		nsRight 163
		nsBottom 121
		sightAngle 44
		x 160
		y 124
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(cond 
					((== (tulip loop?) 2) (messager say: noun theVerb 11))
					(
					(or (== (tulip loop?) 4) (== (tulip loop?) 5)) (messager say: noun theVerb 12))
					(else (super doVerb: theVerb))
				)
			)
			(-87
				(if (and (cast contains: tulip) (not local2))
					(messager say: 2 6 21)
				else
					(= local3 1)
					(sTulip seconds: 0 cue:)
				)
			)
			(87
				(sTulip seconds: 100)
				(curRoom doVerb: theVerb)
			)
			(80
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(if (and (cast contains: tulip) (not local2))
					(curRoom setScript: sOpenTulip)
				else
					(messager say: 2 6 17)
					(if (== ((ScriptID 13) state?) 4)
						((ScriptID 13) dispose:)
						(theGame handsOn:)
					)
				)
			)
			(22
				(if (== ((ScriptID 11) state?) 4)
					((ScriptID 11) cue:)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance flowers2 of Feature
	(properties
		noun 6
		nsLeft 18
		nsTop 44
		nsRight 73
		nsBottom 77
		sightAngle 44
		x 45
		y 60
	)
)

(instance flowers3 of Feature
	(properties
		noun 6
		nsLeft 289
		nsTop 86
		nsRight 319
		nsBottom 114
		sightAngle 44
		x 304
		y 100
	)
)

(instance pathWay of Feature
	(properties
		noun 12
		nsLeft 21
		nsTop 107
		nsRight 252
		nsBottom 189
		sightAngle 44
		x 136
		y 148
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						188
						189
						175
						180
						146
						172
						107
						162
						84
						152
						71
						158
						34
						150
						24
						139
						24
						130
						65
						106
						84
						112
						60
						125
						58
						129
						59
						134
						71
						141
						91
						147
						143
						154
						191
						161
						226
						172
						243
						184
						250
						189
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance pond of Feature
	(properties
		noun 10
		nsLeft 95
		nsTop 91
		nsRight 298
		nsBottom 149
		sightAngle 44
		approachX 160
		approachY 161
		x 196
		y 120
	)
	
	(method (init)
		(self
			onMeCheck:
				((Polygon new:)
					type: 2
					init:
						108
						119
						132
						101
						183
						110
						202
						100
						228
						105
						291
						111
						297
						115
						265
						125
						249
						127
						239
						140
						205
						136
						199
						144
						168
						149
						140
						143
					yourself:
				)
		)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(82
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 11) 0 self)
			)
			(-82
				(cond 
					((not (fountain script?)) (curRoom setScript: sTrigger 0 self))
					((== ((ScriptID 11) state?) 4) ((ScriptID 11) cue:))
				)
			)
			(24
				(curRoom setScript: sGetWater)
			)
			(4
				(Bclr 14)
				(= poisonLevel 0)
				((ScriptID 0 21) doit: 440)
				(messager say: noun theVerb 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance frontBank of Feature
	(properties
		nsTop 155
		nsRight 319
		nsBottom 189
		sightAngle 44
		approachX 160
		approachY 147
		x 159
		z -172
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: sToBank 0 1)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)

(instance backBank of Feature
	(properties
		nsLeft 130
		nsTop 53
		nsRight 217
		nsBottom 95
		sightAngle 44
		approachX 172
		approachY 111
		x 173
		z -74
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: sToBank 0 0)
			)
			(else 
				(curRoom doVerb: theVerb)
			)
		)
	)
)
