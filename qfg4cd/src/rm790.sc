;;; Sierra Script 1.0 - (do not remove this comment)
(script# 790)
(include sci.sh)
(use Main)
(use GloryRm)
(use Intrface)
(use String)
(use Print)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm790 0
)

(local
	local0
	local1
	local2
	gTheObj_2MoveSpeed
	gTheObj_2CycleSpeed
	local5
	local6
	local7
	local8
)
(instance rm790 of GloryRm
	(properties
		noun 14
		picture 790
		west 800
	)
	
	(method (init)
		(if debugging
			(if (== (= local0 (GetNumber {Event #?})) 1)
				(= prevRoomNum 710)
			)
		else
			(= local0
				(cond 
					((== prevRoomNum 710) 1)
					((== prevRoomNum 630) 2)
					(else 0)
				)
			)
		)
		(switch prevRoomNum
			(710
				((ScriptID 7 4) init: 4 45)
				(= Night 1)
				(= Day 1)
				(Bclr 373)
				(aCaveMouth setCel: 0)
				(aKatrina
					view: 792
					setLoop: 2 1
					setCel: 0
					posn: 97 181
					setPri: 190
					init:
				)
				(ego posn: 247 88 ignoreActors: 1)
				(= local7 0)
			)
			(630
				(Palette palSET_FLAG 0 255 100)
				(aKatrina
					view: 637
					setLoop: 0 1
					setCel: 0
					posn: 144 135
					init:
				)
				(ego setLoop: 3 1 setCel: 0 posn: 255 157)
				(vAdAvis init:)
				(= local7 8)
				(Bset 101)
			)
			(else  (ego posn: 4 176))
		)
		(= local1 0)
		(curRoom addObstacle: (roomPoly init: yourself:))
		(ego init: normalize: setScaler: Scaler 96 39 147 105)
		(super init: &rest)
		(aCaveMouth
			signal: (| (aCaveMouth signal?) $0001)
			init:
			approachVerbs: 4
		)
		(if (and (not (ego has: 25)) (not (Btst 354)))
			(vDarkOneSign init:)
		else
			(fDarkOneSign init:)
		)
		(fMouthSign init:)
		(fBoneSign init:)
		(fBloodSign init:)
		(fBreathSign init:)
		(fSenseSign init:)
		(fHeartSign init:)
		(fEssenceSign init:)
		(fCrack init:)
		(fArchway init:)
		(fIsland init:)
		(fStepStones init:)
		(fCaveMouth init:)
		(fGoo init:)
		(fMain init:)
		(fFirst init:)
		(fSecond init:)
		(fThird init:)
		(fFourth init:)
		(fFifth init:)
		(fSixth init:)
		(fSeventh init:)
		(fEight init:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(curRoom setScript: sComeIn)
	)
	
	(method (dispose)
		(keyDownHandler delete: rm790)
		(mouseDownHandler delete: rm790)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event modifiers?))
				(OneOf (event type?) 4 1 32)
				(== ((theIconBar getCursor:) view?) 940)
			)
			(cond 
				((and (fMain onMe: event) (!= local1 0)) (event claimed: 1) (fMain doVerb: 4))
				((and (fFirst onMe: event) (!= local1 1)) (event claimed: 1) (fFirst doVerb: 4))
				((and (fSecond onMe: event) (!= local1 2)) (event claimed: 1) (fSecond doVerb: 4))
				((and (fThird onMe: event) (!= local1 3)) (event claimed: 1) (fThird doVerb: 4))
				((and (fFourth onMe: event) (!= local1 4)) (event claimed: 1) (fFourth doVerb: 4))
				((and (fFifth onMe: event) (!= local1 5)) (event claimed: 1) (fFifth doVerb: 4))
				((and (fSixth onMe: event) (!= local1 6)) (event claimed: 1) (fSixth doVerb: 4))
				((and (fSeventh onMe: event) (!= local1 7)) (event claimed: 1) (fSeventh doVerb: 4))
				((and (fEight onMe: event) (!= local1 8)) (event claimed: 1) (fEight doVerb: 4))
				(else (event claimed: 0) (super handleEvent: event))
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(87
				(theGame handsOff:)
				(self setScript: (ScriptID 37) 0 vDarkOneSign)
			)
			(11 (messager say: 0 11 0))
			(else  (super doVerb: theVerb))
		)
	)
	
	(method (newRoom n)
		(if (== n 800) (theMusic fade: 0))
		(super newRoom: n &rest)
	)
)

(instance sComeIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(switch prevRoomNum
					(710
						(curRoom setScript: sEnterFrom710)
					)
					(630
						(curRoom setScript: sFrom630)
					)
					(else 
						(ego setMotion: PolyPath 40 159 self)
					)
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sEnterFrom710 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 229 112 self)
			)
			(1
				(aCaveMouth setCel: 0 setCycle: End self)
			)
			(2 (= ticks 90))
			(3 (messager say: 2 6 8 0 self))
			(4
				(if (== heroType 3)
					(messager say: 2 6 4 0 self)
				else
					(self cue:)
				)
			)
			(5
				(aCaveMouth setPri: 86)
				(= local1 8)
				(= local2 1)
				(= local5 1)
				(= local7 8)
				(ego setScript: sDoTheMouth self)
			)
			(6
				(= local5 0)
				(ego normalize:)
				(= ticks 6)
			)
			(7 (messager say: 1 6 1 0 self))
			(8
				(aKatrina
					setLoop: 1 1
					setCel: 0
					setCycle: Walk
					setMotion: MoveTo -30 (aKatrina y?) self
				)
			)
			(9
				(ego normalize:)
				(= local1 0)
				(aKatrina dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sFrom630 of Script
	(properties)
	
	(method (changeState newState &tmp newStr temp1 newStr_2)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(aKatrina setCycle: CT 5 1 self)
			)
			(2
				(aKatrina setCel: 5 setCycle: CT 0 -1 self)
			)
			(3 (messager say: 1 6 2 0 self))
			(4
				(ego ignoreActors: 1)
				(aCaveMouth setPri: 75 setCel: 5 setCycle: CT 0 -1 self)
			)
			(5 (= ticks 90))
			(6
				(= newStr (Str new:))
				(= newStr_2 (Str new:))
				(Message msgGET 790 1 6 3 1 (newStr_2 data?))
				(Message msgGET 790 1 6 3 2 (newStr data?))
				(newStr_2 cat: (userName data?))
				(newStr_2 cat: (newStr data?))
				(Print addText: (newStr_2 data?) init:)
				(newStr dispose:)
				(newStr_2 dispose:)
				(= seconds 2)
			)
			(7 (messager say: 1 6 5 0 self))
			(8
				(= local1 2)
				(= local5 1)
				(= local7 8)
				(ego setScript: sDoTheMouth self)
			)
			(9
				(ego normalize:)
				(vAdAvis setCycle: End)
				(aKatrina setCycle: End self)
			)
			(10
				(aKatrina hide: dispose:)
				(vAdAvis hide: dispose:)
				(= ticks 30)
			)
			(11
				(ego setMotion: MoveTo 250 91 self)
			)
			(12
				(ego solvePuzzle: 452 15)
				(curRoom newRoom: 710)
			)
		)
	)
)

(instance sOozeFingers of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== local1 0)
					(ego setMotion: PolyPath 164 163 self)
				else
					(self cue:)
				)
			)
			(1
				(ego
					view: 4
					setLoop: 0 1
					setCel: 0
					posn: (- (ego x?) 6) (- (ego y?) 1)
					setCycle: End self
				)
			)
			(2 (messager say: 4 4 0 0 self))
			(3 (ego setCycle: Beg self))
			(4
				(ego posn: (+ (ego x?) 6) (+ (ego y?) 1) normalize:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetSomeGoo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== local1 0)
					(ego setMotion: PolyPath 164 163 self)
				else
					(self cue:)
				)
			)
			(1
				(ego
					view: 4
					setLoop: 0 1
					setCel: 0
					posn: (- (ego x?) 6) (- (ego y?) 1)
					setCycle: End self
				)
			)
			(2
				(messager say: 4 24 0 0 self)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego
					use: 9
					posn: (+ (ego x?) 6) (+ (ego y?) 1)
					get: 11
					solvePuzzle: 430 6
					normalize:
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGetTheDarkSign of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(fDarkOneSign init:)
				(Bset 354)
				(if (!= local1 0)
					(= local2 1)
					(= local5 1)
					(= local7 0)
					(ego setScript: sDoTheMouth sGetTheDarkSign)
				else
					(self cue:)
				)
			)
			(1
				(= local1 0)
				(= local5 0)
				(ego setMotion: PolyPath 63 169 self)
			)
			(2
				(ego
					view: 4
					posn: 57 168
					setLoop: 0 1
					setCel: 0
					setCycle: CT 2 1 self
				)
			)
			(3 (messager say: 3 4 7 0 self))
			(4
				(ego setCel: 2 setCycle: CT 0 -1 self)
			)
			(5
				(ego get: 25 solvePuzzle: 404 6 normalize:)
				(vDarkOneSign hide: dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sLookAtThings of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local1 0)
					(self cue:)
				else
					(= local2 1)
					(= local5 1)
					(= local7 0)
					(self setScript: sDoTheMouth self)
				)
			)
			(1
				(= local5 0)
				(ego setMotion: PolyPath 42 155 self)
			)
			(2 (Face ego 249 111 self))
			(3
				(switch local8
					(0 (messager say: 6 1 0 0 self))
					(1 (messager say: 7 1 0 0 self))
					(2 (messager say: 8 1 0 0 self))
					(3 (messager say: 9 1 0 0 self))
					(4
						(messager say: 10 1 0 0 self)
					)
					(5
						(messager say: 11 1 0 0 self)
					)
					(6
						(messager say: 12 1 0 0 self)
					)
					(7 (messager say: 3 4 0 0 self))
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sDoTheMouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2MoveSpeed (ego moveSpeed?))
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(switch local1
					(0
						(if (or (!= (ego x?) 174) (!= (ego y?) 162))
							(ego setMotion: PolyPath 174 162 self)
						else
							(self cue:)
						)
					)
					(4
						(cond 
							(local2
								(if (or (!= (ego x?) 200) (!= (ego y?) 145))
									(ego setMotion: PolyPath 200 145 self)
								else
									(self cue:)
								)
							)
							((or (!= (ego x?) 183) (!= (ego y?) 131)) (ego setMotion: PolyPath 183 131 self))
							(else (self cue:))
						)
					)
					(8
						(if (or (!= (ego x?) 223) (!= (ego y?) 111))
							(ego setMotion: MoveTo 223 111 self)
						else
							(self cue:)
						)
					)
					(else  (self cue:))
				)
			)
			(1
				(ego cycleSpeed: 8 moveSpeed: 8)
				((curRoom obstacles?) dispose:)
				(curRoom obstacles: 0)
				(= ticks 6)
			)
			(2 (= ticks 12))
			(3
				(switch local1
					(0
						(ego changeGait: 1 setMotion: MoveTo 212 162 self)
						(= local1 1)
					)
					(1
						(if local2
							(ego changeGait: 1 setMotion: MoveTo 164 163 self)
							(= local1 0)
						else
							(ego changeGait: 1 setMotion: MoveTo 251 156 self)
							(= local1 2)
						)
					)
					(2
						(if local2
							(ego changeGait: 1 setMotion: MoveTo 212 163 self)
							(= local1 1)
						else
							(ego changeGait: 1 setMotion: MoveTo 235 146 self)
							(= local1 3)
						)
					)
					(3
						(if local2
							(ego changeGait: 1 setMotion: MoveTo 251 156 self)
							(= local1 2)
						else
							(ego changeGait: 1 setMotion: MoveTo 200 145 self)
							(= local1 4)
						)
					)
					(4
						(if local2
							(ego changeGait: 1 setMotion: MoveTo 235 146 self)
							(= local1 3)
						else
							(ego changeGait: 1 setMotion: MoveTo 196 122 self)
							(= local1 5)
						)
					)
					(5
						(if local2
							(= local1 4)
							(ego changeGait: 1 setMotion: MoveTo 210 128 self)
						else
							(ego changeGait: 1 setMotion: MoveTo 210 118 self)
							(= local1 6)
						)
					)
					(6
						(if local2
							(= local1 5)
							(ego changeGait: 1 setMotion: MoveTo 196 122 self)
						else
							(ego changeGait: 1 setMotion: MoveTo 200 115 self)
							(= local1 7)
						)
					)
					(7
						(if local2
							(= local1 6)
							(ego changeGait: 1 setMotion: MoveTo 210 118 self)
						else
							(= local1 8)
							(ego changeGait: 1 setMotion: MoveTo 223 111 self)
						)
					)
					(8
						(ego changeGait: 1 setMotion: MoveTo 200 115 self)
						(= local1 7)
					)
				)
			)
			(4
				(switch (ego loop?)
					(6
						(ego changeGait: 0 normalize: 4)
					)
					(7
						(ego changeGait: 0 normalize: 5)
					)
					(8
						(ego changeGait: 0 normalize: 6)
					)
					(9
						(ego changeGait: 0 normalize: 7)
					)
				)
				(if (== local1 4)
					(if local2
						(ego setMotion: MoveTo 200 145 self)
					else
						(ego setMotion: MoveTo 183 131 self)
					)
				else
					(= seconds 1)
				)
			)
			(5
				(cond 
					((== local1 local7) (self cue:))
					((and (== local1 8) (not local2)) (self cue:))
					((and (== local1 0) local2) (self cue:))
					(else (self changeState: 2))
				)
			)
			(6
				(switch local1
					(0
						(curRoom addObstacle: (roomPoly init: yourself:))
					)
					(1
						(curRoom addObstacle: (firstPoly init: yourself:))
					)
					(2
						(curRoom addObstacle: (secondPoly init: yourself:))
					)
					(3
						(curRoom addObstacle: (thirdPoly init: yourself:))
					)
					(4
						(curRoom addObstacle: (fourthPoly init: yourself:))
					)
					(5
						(curRoom addObstacle: (fifthPoly init: yourself:))
					)
					(6
						(curRoom addObstacle: (sixthPoly init: yourself:))
					)
					(7
						(curRoom addObstacle: (seventhPoly init: yourself:))
					)
					(8
						(curRoom addObstacle: (eightPoly init: yourself:))
					)
				)
				(self cue:)
			)
			(7
				(if local6
					(messager say: 5 4 0 0 self)
				else
					(self cue:)
				)
			)
			(8
				(= local6 0)
				(= local2 0)
				(ego
					changeGait: 0
					moveSpeed: gTheObj_2MoveSpeed
					cycleSpeed: gTheObj_2CycleSpeed
				)
				(if (not local5) (theGame handsOn:))
				(self dispose:)
			)
		)
	)
)

(instance aKatrina of Actor
	(properties
		noun 1
		x 11
		y 162
		priority 86
		fixPriority 1
		view 792
		cel 6
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self origStep: 1027 setScaler: Scaler 96 39 147 105)
	)
)

(instance aCaveMouth of Prop
	(properties
		noun 5
		x 251
		y 110
		priority 86
		fixPriority 1
		view 790
		cel 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(80
				(ego trySkill: 20)
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(theGame handsOn:)
				(curRoom setScript: 0)
				(messager say: 5 80 0 0)
			)
			(4
				(if (!= local1 8)
					(= local7 8)
					(= local6 1)
					(curRoom setScript: sDoTheMouth)
				else
					(messager say: 5 4 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance vAdAvis of Prop
	(properties
		x 113
		y 117
		view 677
		loop 2
		scaleSignal $0001
	)
	
	(method (init)
		(super init: &rest)
		(self setScaler: Scaler 96 39 147 105)
	)
)

(instance vDarkOneSign of View
	(properties
		noun 3
		sightAngle 180
		x 76
		y 254
		z 100
		priority 159
		fixPriority 1
		view 790
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(= local7 0)
				(curRoom setScript: sGetTheDarkSign)
			)
			(87 (curRoom doVerb: theVerb))
			(-87
				(ego get: 25)
				(fDarkOneSign init:)
				(vDarkOneSign dispose:)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fMouthSign of Feature
	(properties
		noun 6
		nsLeft 68
		nsTop 112
		nsRight 86
		nsBottom 128
		sightAngle 180
		x 77
		y 120
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 0)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fBoneSign of Feature
	(properties
		noun 7
		nsLeft 63
		nsTop 86
		nsRight 77
		nsBottom 100
		sightAngle 180
		x 70
		y 93
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 1)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fBloodSign of Feature
	(properties
		noun 8
		nsLeft 54
		nsTop 65
		nsRight 70
		nsBottom 76
		sightAngle 180
		x 62
		y 70
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 2)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fBreathSign of Feature
	(properties
		noun 9
		nsLeft 46
		nsTop 48
		nsRight 58
		nsBottom 59
		sightAngle 180
		x 52
		y 53
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 3)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fSenseSign of Feature
	(properties
		noun 10
		nsLeft 29
		nsTop 55
		nsRight 39
		nsBottom 66
		sightAngle 180
		x 34
		y 60
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 4)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fHeartSign of Feature
	(properties
		noun 11
		nsLeft 23
		nsTop 68
		nsRight 37
		nsBottom 81
		sightAngle 180
		x 30
		y 74
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 5)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fEssenceSign of Feature
	(properties
		noun 12
		nsLeft 19
		nsTop 86
		nsRight 33
		nsBottom 100
		sightAngle 180
		x 26
		y 93
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 6)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fDarkOneSign of Feature
	(properties
		noun 3
		nsLeft 71
		nsTop 136
		nsRight 92
		nsBottom 157
		sightAngle 180
		x 71
		y 236
		z 100
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(= local8 7)
			(curRoom setScript: sLookAtThings)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fCrack of Feature
	(properties
		noun 13
		nsLeft 19
		nsTop 101
		nsRight 36
		nsBottom 118
		sightAngle 180
		x 27
		y 109
	)
)

(instance fArchway of Feature
	(properties
		noun 16
		nsLeft 15
		nsTop 32
		nsRight 109
		nsBottom 156
		sightAngle 180
		x 62
		y 32
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				init:
					13
					113
					22
					76
					32
					54
					37
					51
					37
					38
					45
					32
					58
					31
					75
					46
					78
					53
					87
					60
					98
					97
					110
					156
					92
					161
					66
					155
					67
					107
					61
					83
					50
					65
					46
					85
					47
					103
					55
					116
					49
					123
					33
					122
				yourself:
			)
		)
	)
)

(instance fIsland of Feature
	(properties
		noun 14
		nsLeft 134
		nsTop 116
		nsRight 238
		nsBottom 156
		sightAngle 180
		x 186
		y 244
		z 112
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					133
					133
					161
					125
					229
					124
					241
					130
					237
					137
					210
					141
					213
					149
					204
					152
					187
					152
					159
					147
				yourself:
			)
		)
	)
)

(instance fStepStones of Feature
	(properties
		noun 15
		nsLeft 187
		nsTop 140
		nsRight 269
		nsBottom 175
		sightAngle 180
		x 228
		y 157
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					190
					159
					208
					156
					224
					158
					215
					143
					222
					139
					237
					139
					249
					144
					269
					153
					268
					163
					239
					163
					229
					172
					199
					174
				yourself:
			)
		)
	)
)

(instance fCaveMouth of Feature
	(properties
		noun 5
		nsLeft 215
		nsTop 50
		nsRight 270
		nsBottom 109
		sightAngle 180
		x 242
		y 79
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(80
				(ego trySkill: 20)
				(= projX ((User curEvent?) x?))
				(= projY ((User curEvent?) y?))
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(theGame handsOn:)
				(curRoom setScript: 0)
				(messager say: 5 80 0 0)
			)
			(4
				(if (!= local1 8)
					(= local6 1)
					(= local7 8)
					(curRoom setScript: sDoTheMouth)
				else
					(messager say: 5 4 0 0)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fGoo of Feature
	(properties
		noun 4
		nsTop 91
		nsRight 269
		nsBottom 189
		sightAngle 180
		x 228
		y 157
		z 91
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					0
					133
					68
					133
					122
					129
					131
					118
					103
					108
					96
					92
					150
					91
					140
					107
					180
					114
					241
					118
					276
					101
					300
					107
					271
					134
					271
					143
					319
					147
					319
					189
					188
					189
					153
					181
					187
					160
					115
					152
					58
					144
					1
					144
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(24
				(curRoom setScript: sGetSomeGoo)
			)
			(4
				(curRoom setScript: sOozeFingers)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance fMain of Feature
	(properties
		noun 14
		nsTop 148
		nsRight 174
		nsBottom 189
		sightAngle 180
		y 250
		z 104
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 0 143 29 144 32 149 182 159 180 167 154 172 148 183 187 188 0 189
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 0)
			(switch local1
				(0 0)
				(else 
					(= local2 1)
					(curRoom setScript: sDoTheMouth)
				)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fFirst of Feature
	(properties
		noun 15
		nsLeft 196
		nsTop 159
		nsRight 215
		nsBottom 163
		sightAngle 180
		x 196
		y 260
		z 100
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 206 156 210 157 230 161 229 173 201 175 194 170 190 159
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 1)
			(if (!= local1 1)
				(if (> local1 1) (= local2 1) else (= local2 0))
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fSecond of Feature
	(properties
		noun 15
		nsLeft 232
		nsTop 152
		nsRight 263
		nsBottom 155
		sightAngle 319
		x 232
		y 253
		z 100
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 228 153 236 149 254 149 268 152 270 156 268 159 268 163 235 163
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 2)
			(if (!= local1 2)
				(if (> local1 2) (= local2 1) else (= local2 0))
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fThird of Feature
	(properties
		noun 15
		nsLeft 220
		nsTop 142
		nsRight 246
		nsBottom 145
		sightAngle 180
		x 220
		y 252
		z 110
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 237 140 248 144 248 149 235 149 228 152 220 151 216 147 217 141
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 3)
			(if (!= local1 3)
				(if (> local1 3) (= local2 1) else (= local2 0))
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fFourth of Feature
	(properties
		noun 14
		nsLeft 151
		nsTop 127
		nsRight 224
		nsBottom 143
		sightAngle 180
		x 151
		y 254
		z 120
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					133
					132
					150
					128
					150
					125
					226
					124
					237
					127
					237
					133
					237
					140
					216
					142
					213
					147
					206
					153
					188
					153
					133
					136
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 4)
			(if (!= local1 4)
				(if (> local1 4) (= local2 1) else (= local2 0))
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fFifth of Feature
	(properties
		noun 15
		nsLeft 187
		nsTop 121
		nsRight 207
		nsBottom 123
		sightAngle 180
		x 187
		y 251
		z 130
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 187 124 183 122 189 119 201 119 208 121 208 123
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 5)
			(if (!= local1 5)
				(if (> local1 5) (= local2 1) else (= local2 0))
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fSixth of Feature
	(properties
		noun 15
		nsLeft 202
		nsTop 117
		nsRight 209
		nsBottom 119
		sightAngle 180
		x 202
		y 267
		z 150
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 200 118 200 116 206 115 216 115 218 119 213 120 208 122
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 6)
			(if (!= local1 6)
				(if (> local1 6) (= local2 1) else (= local2 0))
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fSeventh of Feature
	(properties
		noun 15
		nsLeft 192
		nsTop 112
		nsRight 204
		nsBottom 115
		sightAngle 180
		x 192
		y 263
		z 150
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 200 117 191 117 189 114 193 112 202 112 209 114
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 7)
			(if (!= local1 7)
				(if (> local1 7) (= local2 1) else (= local2 0))
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fEight of Feature
	(properties
		noun 15
		nsLeft 202
		nsTop 105
		nsRight 248
		nsBottom 114
		sightAngle 180
		x 202
		y 258
		z 150
	)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 196 109 215 100 256 106 249 113 217 115 208 111 197 110
				yourself:
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 4)
			(= local7 8)
			(if (!= local1 8)
				(= local2 0)
				(curRoom setScript: sDoTheMouth)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance roomPoly of Polygon
	(properties
		type $0002
	)
	
	(method (init)
		(super
			init:
				0
				150
				0
				0
				319
				0
				319
				189
				159
				189
				130
				184
				138
				172
				134
				165
				169
				162
				168
				160
				115
				158
				113
				166
				84
				169
				66
				166
				53
				153
				17
				153
				17
				146
				7
				146
		)
	)
)

(instance firstPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 196 160 203 163 215 162 226 161 208 159)
	)
)

(instance secondPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 232 153 241 155 258 155 263 154 253 152)
	)
)

(instance thirdPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init: 220 142 227 145 237 145 246 144 235 142 221 142
		)
	)
)

(instance fourthPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init: 141 134 184 144 209 147 205 138 233 135 226 129 224 127 169 128
		)
	)
)

(instance fifthPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 187 121 195 123 207 121)
	)
)

(instance sixthPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 202 117 209 119 215 117)
	)
)

(instance seventhPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super init: 192 113 194 115 204 114 200 112)
	)
)

(instance eightPoly of Polygon
	(properties
		type $0003
	)
	
	(method (init)
		(super
			init: 202 108 209 110 215 112 232 114 248 111 245 106 221 107 213 105
		)
	)
)
