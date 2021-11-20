;;; Sierra Script 1.0 - (do not remove this comment)
(script# 510)
(include sci.sh)
(use Main)
(use GloryRm)
(use TellerIcon)
(use EgoDead)
(use TextIcon)
(use Array)
(use Scaler)
(use IconBar)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm510 0
)

(local
	local0
	local1
	local2
	local3
	local4
	gTheObj_2CycleSpeed
	local6
)
(procedure (localproc_017a)
	(RemapColors 2 254 60)
	(RemapColors 1 253 112 175 62)
)

(procedure (localproc_0b85 param1 &tmp temp0)
	(= temp0 5)
	(while (> temp0 0)
		(local2 at: temp0 (local2 at: (- temp0 1)))
		(-- temp0)
	)
	(local2 at: 0 param1)
	(if
		(and
			(== (local1 at: 0) (local2 at: 0))
			(== (local1 at: 1) (local2 at: 1))
			(== (local1 at: 2) (local2 at: 2))
			(== (local1 at: 3) (local2 at: 3))
			(== (local1 at: 4) (local2 at: 4))
			(== (local1 at: 5) (local2 at: 5))
		)
		(crestPuzzle setScript: sCrest)
	)
)

(instance rm510 of GloryRm
	(properties
		noun 17
		picture 510
	)
	
	(method (init)
		(theGame handsOn:)
		(super init: &rest)
		(Bset 373)
		(ego
			init:
			normalize:
			x: 160
			y: 120
			setScaler: Scaler 122 50 189 87
			changeGait:
		)
		(switch prevRoomNum
			(500
				(ego x: 103 y: 71)
				(curRoom setScript: sFrom500)
			)
			(662
				(ego x: 318 y: 110)
				(curRoom setScript: sFrom662)
			)
			(else  (ego x: 160 y: 170))
		)
		(guy1 init: approachVerbs: 4 1)
		(guy2 init: approachVerbs: 4 1)
		(guy3 init: approachVerbs: 4 1)
		(if (Btst 253)
			(guy3 view: 511 loop: 2 cel: 0)
			(if (Btst 252) (guy3 loop: 1 cel: 5))
		)
		(guy4 init: approachVerbs: 4 1)
		(guy5 init: approachVerbs: 4 42 29 28)
		(crestF init: approachVerbs: 4 1)
		(relief1F init: approachVerbs: 4 1)
		(relief1Teller init: relief1F 510 21 155 7)
		(relief2F init: approachVerbs: 4 1)
		(relief2Teller init: relief2F 510 21 155 8)
		(relief3F init: approachVerbs: 4 1)
		(relief3Teller init: relief3F 510 21 155 9)
		(relief4F init: approachVerbs: 4 1)
		(relief4Teller init: relief4F 510 21 155 10)
		(relief5F init: approachVerbs: 4 1)
		(relief5Teller init: relief5F 510 21 155 11)
		(reaper1F init: approachVerbs: 4 1)
		(reaper2F init: approachVerbs: 4 1)
		(reaper3F init: approachVerbs: 4 1)
		(scythe1F init:)
		(scythe2F init:)
		(scythe3F init:)
		(grate init: approachVerbs: 4 1)
		(windowFeat init:)
		(exitReaper init: setPri: 116 approachVerbs: 4 1)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						49
						179
						291
						179
						308
						157
						308
						137
						256
						132
						215
						145
						145
						128
						110
						128
						110
						123
						125
						101
						111
						68
						101
						69
						114
						104
						104
						121
						91
						121
						73
						115
						27
						118
						27
						137
						48
						137
						48
						155
						23
						155
					yourself:
				)
		)
		(aDoorMat init:)
		(localproc_017a)
		(torchEff init: setScaler: ego setCycle: RandCycle)
		(theMusic number: 510 setLoop: -1 play:)
	)
	
	(method (doit)
		(if
		(and (aDoorMat onMe: (ego x?) (ego y?)) (not script))
			(curRoom setScript: sCantExit)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(aDoorMat dispose:)
		(super dispose:)
	)
	
	(method (newRoom n)
		(Bclr 373)
		(ego changeGait: egoGait)
		(if (== n 500)
			(ego solvePuzzle: 448 6)
		else
			(ego solvePuzzle: 447 6)
		)
		(RemapColors 0)
		(RemapColors 2 254 60)
		(super newRoom: n)
	)
)

(instance relief1Teller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(Bclr 51)
		(if (== iconValue 7)
			(Bclr 51)
			(curRoom setScript: sOpenRelief1)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super
			showCases: 8 (if (== heroType 2) (Btst 242) else 0)
		)
	)
)

(instance relief2Teller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(Bclr 51)
		(cond 
			((== iconValue 12) (Bclr 51) (curRoom setScript: sSearchRelief2))
			((== iconValue 11) (Bclr 51) (curRoom setScript: sOpenRelief2))
			(else (super sayMessage: &rest))
		)
	)
	
	(method (showCases)
		(curRoom setScript: 0)
		(super
			showCases:
				12
				(if (and (== heroType 2) (Btst 242))
				else
					(== heroType 3)
				)
		)
	)
)

(instance relief3Teller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(Bclr 51)
		(if (== iconValue 15)
			(Bclr 51)
			(curRoom setScript: sOpenRelief3)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super
			showCases: 16 (if (== heroType 2) (Btst 242) else 0)
		)
	)
)

(instance relief4Teller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(Bclr 51)
		(if (== iconValue 19)
			(Bclr 51)
			(curRoom setScript: sOpenRelief4)
		else
			(super sayMessage: &rest)
		)
	)
	
	(method (showCases)
		(super
			showCases: 20 (if (== heroType 2) (Btst 242) else 0)
		)
	)
)

(instance relief5Teller of Teller
	(properties
		actionVerb 4
	)
	
	(method (sayMessage)
		(Bclr 51)
		(cond 
			((== iconValue 24)
				(Bclr 51)
				(if (> (ego trySkill: 9 250) 0)
					(curRoom setScript: sTo662 0 1)
				else
					(messager say: 11 42 49)
				)
			)
			((== iconValue 26) (Bclr 51) (curRoom setScript: sTo662 1))
			(else (super sayMessage: &rest))
		)
	)
	
	(method (showCases)
		(super
			showCases:
				25
				(if (== heroType 2) (Btst 242) else 0)
				24
				(ego has: 24)
				26
				(ego has: 62)
		)
	)
)

(instance guy1 of Prop
	(properties
		noun 7
		approachX 56
		approachY 118
		x 55
		y 113
		view 510
		loop 5
		signal $4000
	)
	
	(method (doVerb theVerb)
		(relief1F doVerb: theVerb)
	)
)

(instance guy2 of Prop
	(properties
		noun 8
		approachX 76
		approachY 116
		x 83
		y 112
		view 510
		loop 4
		signal $4000
	)
	
	(method (doVerb theVerb)
		(relief2F doVerb: theVerb)
	)
)

(instance axe of Prop
	(properties
		x 83
		y 112
		view 511
		signal $4001
	)
)

(instance guy3 of Prop
	(properties
		noun 9
		approachX 155
		approachY 139
		x 174
		y 132
		view 510
		signal $4000
	)
	
	(method (doVerb theVerb)
		(relief3F doVerb: theVerb)
	)
)

(instance guy4 of Prop
	(properties
		noun 10
		approachX 251
		approachY 135
		x 242
		y 128
		view 510
		loop 1
		signal $4000
	)
	
	(method (doVerb theVerb)
		(relief4F doVerb: theVerb)
	)
)

(instance guy5 of Prop
	(properties
		noun 11
		approachX 268
		approachY 140
		x 284
		y 130
		view 510
		loop 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(relief5F doVerb: theVerb)
	)
)

(instance exitReaper of Prop
	(properties
		noun 4
		approachX 117
		approachY 128
		x 119
		y 129
		view 510
		loop 3
		signal $5000
	)
	
	(method (doVerb theVerb)
		(reaper1F doVerb: theVerb)
	)
)

(instance crestF of Feature
	(properties
		nsLeft 43
		nsTop 128
		nsRight 98
		nsBottom 150
		sightAngle 40
		approachX 92
		approachY 155
		x 75
		y 133
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 4 1)
			(cast eachElementDo: #perform (ScriptID 90 0) 1)
			(UpdatePlane
				((curRoom plane?) back: 0 picture: -1 yourself:)
			)
			(crestPuzzle init: show: dispose:)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance relief1F of Feature
	(properties
		noun 7
		nsLeft 42
		nsTop 76
		nsRight 64
		nsBottom 113
		sightAngle 180
		approachX 56
		approachY 118
		x 49
		y 92
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(relief1Teller doVerb: theVerb)
			)
			(80
				(= projX x)
				(= projY y)
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sOpenRelief1 0 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance relief2F of Feature
	(properties
		noun 8
		nsLeft 74
		nsTop 68
		nsRight 95
		nsBottom 116
		sightAngle 90
		approachX 76
		approachY 116
		x 88
		y 110
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (== heroType 3)
					(curRoom setScript: sRelief2Paladin)
				else
					(relief2Teller doVerb: theVerb)
				)
			)
			(80
				(= projX 88)
				(= projY 92)
				(curRoom setScript: sCastOpenRelief2)
			)
			(-80
				(curRoom setScript: sOpenRelief2 0 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance relief3F of Feature
	(properties
		noun 9
		nsLeft 164
		nsTop 63
		nsRight 195
		nsBottom 135
		sightAngle 180
		approachX 155
		approachY 139
		x 179
		y 99
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (not (Btst 252))
					(if (Btst 253)
						(curRoom setScript: sRelief3Dust)
					else
						(relief3Teller doVerb: theVerb)
					)
				else
					(messager say: 1 4 0)
				)
			)
			(80
				(= projX x)
				(= projY y)
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(if (Btst 253)
					(if (curRoom script?) (curRoom setScript: 0))
					(theGame handsOn:)
					(self doVerb: 4)
				else
					(curRoom setScript: sOpenRelief3 0 1)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance relief4F of Feature
	(properties
		noun 10
		nsLeft 226
		nsTop 60
		nsRight 248
		nsBottom 126
		sightAngle 180
		approachX 251
		approachY 135
		x 237
		y 83
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(relief4Teller doVerb: theVerb)
			)
			(80
				(= projX x)
				(= projY y)
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(curRoom setScript: sOpenRelief4 0 1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance relief5F of Feature
	(properties
		noun 11
		nsLeft 273
		nsTop 64
		nsRight 298
		nsBottom 132
		sightAngle 180
		approachX 268
		approachY 140
		x 285
		y 98
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(relief5Teller doVerb: theVerb)
			)
			(80
				(= projX x)
				(= projY y)
				(curRoom setScript: (ScriptID 13) 0 self)
			)
			(-80
				(messager say: 11 80 0)
				(if (curRoom script?)
					((curRoom script?) dispose:)
					(curRoom script: 0)
				)
				(theGame handsOn:)
			)
			(29
				(if (ego has: 62)
					(curRoom setScript: sTo662 0 3)
				else
					(messager say: 20 6 43)
				)
			)
			(42
				(if (> (ego trySkill: 9 250) 0)
					(curRoom setScript: sTo662 0 1)
				else
					(messager say: 11 42 49)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance reaper1F of Feature
	(properties
		noun 4
		nsLeft 111
		nsTop 70
		nsRight 140
		nsBottom 131
		sightAngle 180
		approachX 117
		approachY 128
		x 125
		y 100
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sShakeHands)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance reaper2F of Feature
	(properties
		heading 270
		noun 5
		nsLeft 18
		nsTop 60
		nsRight 39
		nsBottom 140
		sightAngle 180
		approachX 52
		approachY 148
		x 28
		y 100
	)
)

(instance reaper3F of Feature
	(properties
		heading 100
		noun 6
		nsLeft 299
		nsTop 82
		nsRight 319
		nsBottom 180
		sightAngle 180
		approachX 291
		approachY 179
		x 309
		y 131
	)
)

(instance scythe1F of Feature
	(properties
		noun 13
		nsLeft 99
		nsTop 58
		nsRight 124
		nsBottom 69
		sightAngle 180
		x 96
		y 56
	)
)

(instance scythe2F of Feature
	(properties
		noun 14
		nsLeft 32
		nsTop 38
		nsRight 67
		nsBottom 53
		sightAngle 180
		x 49
		y 45
	)
)

(instance scythe3F of Feature
	(properties
		noun 15
		nsLeft 270
		nsTop 35
		nsRight 314
		nsBottom 55
		sightAngle 180
		x 292
		y 45
	)
)

(instance grate of Feature
	(properties
		noun 23
		nsLeft 141
		nsTop 168
		nsRight 190
		nsBottom 189
		sightAngle 40
		approachX 165
		approachY 178
		x 165
		y 178
	)
)

(instance windowFeat of Feature
	(properties
		noun 22
		nsLeft 155
		nsTop 22
		nsRight 209
		nsBottom 42
		sightAngle 40
		x 182
		y 31
	)
)

(instance sCantExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 4 0 0 self)
			)
			(1
				(ego setMotion: MoveTo 120 100 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCrest of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (== state 2) (not (ego has: 62)))
			(if (== register 100)
				(localproc_017a)
				(self cue:)
			else
				(Palette palSET_FLAG 0 8 (= register (+ register 1)))
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(crestPuzzle noHands: 1)
				(theKey setLoop: 7 1 setCel: 0 setCycle: End self)
			)
			(1 (= ticks 30))
			(2
				(if (not (ego has: 62))
					(RemapColors 0)
					(Palette palSET_FLAG 0 8 0)
					(theKey x: 163 y: 70 setLoop: 8 1 cel: 0)
				else
					(= ticks 90)
				)
			)
			(3
				(if (not (ego has: 62))
					(ego get: 62 1)
					(messager say: 20 6 50)
				else
					(theGame
						setCursor: (IconBarCursor view: 940 loop: 0 cel: 0 yourself:)
					)
				)
				(local2 at: 5 0)
				(crestPuzzle state: (& (crestPuzzle state?) $ffdf))
			)
		)
	)
)

(instance crestPuzzle of PuzzleBar
	(properties)
	
	(method (init)
		((ScriptID 0 21) doit: 100)
		(= local6 1)
		(= local1 (IntArray with: 6 2 4 1 2 5))
		(= local2 (IntArray with: 0 0 0 0 0 0))
		(theKey setLoop: 7 1 setCel: 0 x: 178 y: 152)
		(self changeCursor: 999)
		(super init: &rest)
		(theKey init: puzzleCast)
	)
	
	(method (dispose)
		(local1 dispose:)
		(local2 dispose:)
		(local3 dispose:)
		(local4 dispose:)
		((ScriptID 0 21) doit: curRoomNum)
		(if script (script dispose:) (= script 0))
		(if puzzleCast (puzzleCast dispose:) (= puzzleCast 0))
		(plane deleteCast: self dispose:)
		(= plane 0)
		(if elements (DisposeList elements))
		(= size (= elements 0))
		(curRoom drawPic: (curRoom picture?) (curRoom style?))
		(cast eachElementDo: #perform (ScriptID 90 0) 0)
		(DisposeClone self)
	)
	
	(method (resetPuzzle)
		(= noHands 0)
		(= local3
			((Polygon new:)
				type: 0
				init:
					74
					36
					136
					37
					136
					17
					158
					9
					180
					18
					182
					38
					238
					35
					261
					57
					270
					91
					249
					126
					241
					143
					201
					162
					160
					169
					114
					159
					74
					141
					52
					98
					51
					73
					62
					47
				yourself:
			)
		)
		(= local4
			((Polygon new:)
				type: 0
				init: 198 56 198 119 159 143 159 56
				yourself:
			)
		)
		(self
			add: dummyIcon helpIcon red1 orange2 yellow3 green4 blue5 violet6
			eachElementDo: #init self
			eachElementDo: #show
		)
		(crestView init: puzzleCast)
	)
	
	(method (helpYou)
		(messager say: 24 9 0 local6 0)
		(return
			(if (== local6 5)
				(self setScript: sCrest)
			else
				(++ local6)
			)
		)
	)
	
	(method (setPlane)
		(= usePlane 0)
		(plane
			bitmap: 0
			priority: (+ (GetHighPlanePri) 1)
			init:
			setRect: 0 10 319 199
			addCast: puzzleCast
		)
	)
	
	(method (addIcons)
	)
)

(instance dummyIcon of TextIcon
	(properties
		view 512
		loop 6
		cel 0
	)
	
	(method (show)
	)
	
	(method (onMe theObjOrX theY)
		(return (not (local3 onMe: theObjOrX theY)))
	)
	
	(method (select)
		(theGame
			setCursor: (IconBarCursor view: 940 loop: 0 cel: 0 yourself:)
		)
		(crestPuzzle state: (& (crestPuzzle state?) $ffdf))
	)
	
	(method (highlight)
	)
)

(instance theKey of Prop
	(properties
		x 178
		y 152
		priority 10
		fixPriority 1
		view 512
		loop 7
	)
)

(instance red1 of TextIcon
	(properties
		nsLeft 114
		nsTop 55
		x 114
		y 55
		view 512
		value 1
	)
	
	(method (select &tmp temp0)
		(if (super select: &rest) (localproc_0b85 value))
	)
)

(instance crestView of View
	(properties
		x 51
		y 10
		view 512
		loop 9
	)
)

(instance orange2 of TextIcon
	(properties
		nsLeft 114
		nsTop 70
		x 114
		y 70
		view 512
		loop 1
		value 2
	)
	
	(method (select)
		(if (super select: &rest) (localproc_0b85 value))
	)
)

(instance yellow3 of TextIcon
	(properties
		nsLeft 114
		nsTop 84
		x 114
		y 84
		view 512
		loop 2
		value 3
	)
	
	(method (select)
		(if (super select: &rest) (localproc_0b85 value))
	)
)

(instance green4 of TextIcon
	(properties
		nsLeft 115
		nsTop 98
		x 115
		y 98
		view 512
		loop 3
		value 4
	)
	
	(method (select)
		(if (super select: &rest) (localproc_0b85 value))
	)
)

(instance blue5 of TextIcon
	(properties
		nsLeft 116
		nsTop 110
		x 116
		y 110
		view 512
		loop 4
		value 5
	)
	
	(method (select)
		(if (super select: &rest) (localproc_0b85 value))
	)
)

(instance violet6 of TextIcon
	(properties
		nsLeft 121
		nsTop 124
		x 121
		y 124
		view 512
		loop 5
		value 6
	)
	
	(method (select)
		(if (super select: &rest) (localproc_0b85 value))
	)
)

(instance helpIcon of TextIcon
	(properties
		x 159
		y 55
		view 512
		loop 6
		cel 0
	)
	
	(method (show)
	)
	
	(method (onMe theObjOrX theY)
		(return
			(if (local4 onMe: theObjOrX theY)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (select)
		(crestPuzzle helpYou:)
	)
	
	(method (highlight)
	)
)

(instance sShakeHands of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(ego
					view: 31
					setLoop: 0
					setCel: 0
					cycleSpeed: defaultCycles
					setCycle: End self
				)
			)
			(1
				(exitReaper
					signal: (| (exitReaper signal?) $0001)
					setCel: 0
					setCycle: End self
				)
			)
			(2 (messager say: 4 4 0 0 self))
			(3
				(exitReaper setCycle: Beg)
				(ego setCycle: Beg self)
			)
			(4
				(ego
					cycleSpeed: gTheObj_2CycleSpeed
					normalize:
					setMotion: PolyPath 120 100 self
				)
			)
			(5
				(exitReaper signal: (& (exitReaper signal?) $fffe))
				(ego setMotion: PolyPath 103 71 self)
			)
			(6 (curRoom newRoom: 500))
		)
	)
)

(instance sFrom500 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: MoveTo 120 100 self)
			)
			(1
				(ego setMotion: MoveTo 102 124 self)
			)
			(2
				(= cycles (+ (ego cycleSpeed?) 2))
			)
			(3
				(ego solvePuzzle: 446 6)
				(if (not (Btst 249))
					(Bset 249)
					(messager say: 20 6 42 0 self)
				else
					(self cue:)
				)
			)
			(4
				(if (and (== heroType 3) (not (Btst 250)))
					(messager say: 20 6 38 0 self)
				else
					(self cue:)
				)
			)
			(5
				(ego normalize: 5)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenRelief1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(theGame handsOff:)
				(if register
					(self cue:)
				else
					(ego
						view: 31
						setLoop: 1
						setCel: 0
						cycleSpeed: defaultCycles
						setCycle: End self
					)
				)
			)
			(1
				(guy1
					signal: (| (guy1 signal?) $0001)
					setCycle: End self
				)
			)
			(2
				(guy1 hide:)
				(if register
					(ego setMotion: PolyPath 56 118 self)
				else
					(ego setCycle: Beg self)
				)
			)
			(3
				(if (not register) (ego normalize: 7))
				(ego
					setPri: 138
					setSpeed: defaultCycles
					setMotion: MoveTo 46 102 self
				)
			)
			(4
				(ego hide:)
				(torchEff hide:)
				(guy1
					signal: (| (guy1 signal?) $0001)
					show:
					setCycle: Beg self
				)
			)
			(5
				(messager say: 20 6 40 0 self)
			)
			(6 (= ticks 180))
			(7
				(messager say: 20 6 39 0 self)
			)
			(8
				(guy4
					signal: (| (guy4 signal?) $0001)
					setCycle: End self
				)
			)
			(9
				(guy4 setPri: 1 signal: (& (guy4 signal?) $fffe))
				(ego
					x: 216
					y: 114
					show:
					setPri: -1
					setMotion: MoveTo 251 135 self
				)
				(torchEff show:)
			)
			(10
				(guy4
					signal: (| (guy4 signal?) $0001)
					setPri: -1
					setCycle: Beg self
				)
			)
			(11
				(guy4 signal: (& (guy4 signal?) $fffe))
				(= register 0)
				(ego setSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenRelief4 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(theGame handsOff:)
				(guy4
					signal: (| (guy4 signal?) $0001)
					setCycle: End self
				)
				(if (not register)
					(ego
						view: 31
						setLoop: 1
						setCel: 0
						cycleSpeed: defaultCycles
						setCycle: End self
					)
				)
			)
			(1
				(guy4 signal: (& (guy4 signal?) $fffe))
				(if register
					(ego setMotion: PolyPath 251 135 self)
				else
					(ego setCycle: Beg self)
				)
			)
			(2
				(if (not register) (ego normalize: 7))
				(guy4 setPri: 1)
				(ego
					setPri: 116
					setSpeed: defaultCycles
					setMotion: MoveTo 216 114 self
				)
			)
			(3
				(ego hide:)
				(guy4
					signal: (| (guy4 signal?) $0001)
					setPri: -1
					setCycle: Beg self
				)
			)
			(4
				(guy4 signal: (& (guy4 signal?) $fffe))
				(messager say: 20 6 40 0 self)
			)
			(5 (= ticks 180))
			(6
				(messager say: 20 6 39 0 self)
			)
			(7
				(guy1
					signal: (| (guy1 signal?) $0001)
					setCycle: End self
				)
			)
			(8
				(guy1 setPri: 1)
				(ego
					x: 46
					y: 102
					show:
					setPri: -1
					setMotion: MoveTo 56 118 self
				)
			)
			(9
				(guy1
					signal: (| (guy1 signal?) $0001)
					setPri: -1
					setCycle: Beg self
				)
			)
			(10
				(= register 0)
				(ego setSpeed: gTheObj_2CycleSpeed)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRelief2Paladin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 20 6 46 0 self)
			)
			(1 (relief2Teller doVerb: 4))
		)
	)
)

(instance sOpenRelief2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(theGame handsOff:)
				(guy2
					signal: (| (guy2 signal?) $0001)
					setCycle: End self
				)
			)
			(1
				(guy2 signal: (& (guy2 signal?) $fffe))
				(axe init: setCycle: End self)
			)
			(2
				(axe setCycle: Beg self)
				(if (not register)
					(ego
						view: 6
						setLoop: 1
						setCel: 0
						cycleSpeed: defaultCycles
						setCycle: End
					)
				)
			)
			(3
				(axe dispose:)
				(guy2
					signal: (| (guy2 signal?) $0001)
					setCycle: Beg self
				)
			)
			(4
				(guy2 signal: (& (guy2 signal?) $fffe))
				(if (not register)
					(if (< (ego takeDamage: 30) 1)
						(EgoDead 1 510 973 1 912)
					else
						(ego setLoop: 3 setCel: 0 setCycle: End self)
					)
				else
					(++ state)
					(messager say: 8 80 0 0 self)
				)
			)
			(5
				(messager say: 20 6 45 0 self)
			)
			(6
				(ego setSpeed: gTheObj_2CycleSpeed)
				(if (not register)
					(ego normalize: 5)
				else
					(ego normalize: 6)
				)
				(= register 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sCastOpenRelief2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 61 143 self)
			)
			(1
				(curRoom setScript: (ScriptID 13) 0 relief2F)
			)
		)
	)
)

(instance sSearchRelief2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 20 6 41 0 self)
			)
			(1
				(guy2
					signal: (| (guy2 signal?) $0001)
					setCycle: End self
				)
			)
			(2
				(guy2 signal: (& (guy2 signal?) $fffe))
				(axe init: setCycle: CT 1 1 self)
			)
			(3
				(axe setCycle: End)
				(ego
					setMotion: JumpTo (- (ego x?) 18) (+ (ego y?) 12) self
				)
			)
			(4 (axe setCycle: Beg self))
			(5
				(axe dispose:)
				(guy2
					signal: (| (guy2 signal?) $0001)
					setCycle: Beg self
				)
			)
			(6
				(guy2 signal: (& (guy2 signal?) $fffe))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sOpenRelief3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(guy3
					signal: (| (guy3 signal?) $0001)
					setCycle: End self
				)
			)
			(1
				(guy3 view: 511 setLoop: 2 setCel: 5 setCycle: Beg self)
			)
			(2
				(guy3 signal: (& (guy3 signal?) $fffe))
				(Bset 253)
				(messager say: 20 6 44 0 self)
			)
			(3
				(= register 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sRelief3Dust of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(guy3
					signal: (| (guy3 signal?) $0001)
					setLoop: 1
					setCel: 0
					setCycle: End self
				)
			)
			(1
				(guy3 signal: (& (guy3 signal?) $fffe))
				(Bset 252)
				(messager say: 16 4 0 0 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sTo662 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= gTheObj_2CycleSpeed (ego cycleSpeed?))
				(theGame handsOff:)
				(if (== register 2)
					(self cue:)
				else
					(ego
						view: 31
						setLoop: 0
						setCel: 0
						cycleSpeed: defaultCycles
						setCycle: End self
					)
				)
			)
			(1
				(if (!= register 1)
					(messager say: 11 29 0 0 self)
				else
					(messager say: 21 155 24 0 self)
				)
			)
			(2
				(guy5
					signal: (| (guy5 signal?) $0001)
					setCycle: End self
				)
			)
			(3
				(guy5 signal: (& (guy5 signal?) $fffe))
				(ego normalize: 6)
				(if (== register 2)
					(ego ignoreActors: 1 setMotion: PolyPath 268 140 self)
				else
					(self cue:)
				)
			)
			(4
				(ego setMotion: MoveTo 300 120 self)
			)
			(5
				(if (or (!= (ego x?) 300) (!= (ego y?) 120))
					(ego setMotion: MoveTo 300 120 self)
				else
					(self cue:)
				)
			)
			(6
				(guy5
					signal: (| (guy5 signal?) $0001)
					setCycle: Beg self
				)
			)
			(7
				(guy5 signal: (& (guy5 signal?) $fffe))
				(ego setSpeed: gTheObj_2CycleSpeed)
				(curRoom newRoom: 662)
			)
		)
	)
)

(instance sFrom662 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(guy5
					signal: (| (guy5 signal?) $0001)
					setCycle: End self
				)
			)
			(1
				(ego setMotion: MoveTo 268 140 self)
			)
			(2 (guy5 setCycle: Beg self))
			(3
				(guy5 signal: (& (guy5 signal?) $fffe))
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance torchEff of Prop
	(properties
		view 775
	)
	
	(method (doit)
		(= x (ego x?))
		(= y (ego y?))
		(= z (+ (ego z?) 8))
		(super doit: &rest)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance aDoorMat of Polygon
	(properties)
	
	(method (init)
		(super init: 99 89 127 89 119 78 96 78)
	)
)
