;;; Sierra Script 1.0 - (do not remove this comment)
(script# 410)
(include sci.sh)
(use Main)
(use Procs)
(use ExitFeature)
(use PFollow)
(use Osc)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use StopWalk)
(use Timer)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	neJackson 0
)

(instance neJackson of Rm
	(properties
		modNum 401
		noun 8
		picture 410
		style $000a
		horizon 25
		south 430
		west 420
		vanishingY -30000
	)
	
	(method (init)
		(super init:)
		(self setRegions: 401)
		(Load rsPIC 410)
		(Load rsVIEW 902)
		(Load rsVIEW 409)
		(Load rsVIEW 410)
		(Load rsVIEW 411)
		(Load rsVIEW 412)
		(Load rsVIEW 414)
		(ScriptID 941)
		(theMusic1
			number: 410
			loop: -1
			play:
			setVol: 0
			fade: 80 5 10 0
		)
		(ego
			view: 902
			setLoop: (ScriptID 401 2)
			setStep: 2 1
			setCycle: StopWalk -1
			init:
			state: 2
		)
		(switch prevRoomNum
			(420
				(ego posn: 5 (ego y?) setMotion: PolyPath 20 (ego y?))
				(if (Btst 101) ((ScriptID 401 1) posn: 5 (ego y?)))
			)
			(430
				(cond 
					((< (ego x?) 136) (ego x: 136))
					((> (ego x?) 304) (ego x: 300))
				)
				(ego
					posn: (ego x?) 140
					setMotion: PolyPath (ego x?) 130
				)
				(if (Btst 101) ((ScriptID 401 1) posn: (ego x?) 140))
			)
			(else  (ego posn: 160 100))
		)
		(if (and (Btst 101) (== currentDay 1))
			((ScriptID 401 1)
				code: specialMimeCode
				setCycle: StopWalk 422
				setMotion: PFollow ego 15
				setLoop: -1
				setPri: -1
				init:
			)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						21
						0
						103
						34
						105
						53
						108
						68
						115
						85
						126
						91
						126
						90
						189
						306
						181
						306
						136
						295
						113
						285
						97
						271
						84
						244
						66
						216
						54
						165
						38
						133
						31
						100
						28
						23
						28
						11
						21
					yourself:
				)
		)
		(statue init:)
		(bush init:)
		(tree init:)
		(drummer init: cycleSpeed: 12 setCycle: RandCycle)
		(if (not isDemo)
			(easel init:)
			(juggler init: cycleSpeed: 6 setCycle: Fwd)
			(artist init: cycleSpeed: 100 setCycle: RandCycle)
		)
		(trashCan init:)
		(lamp init:)
		(fence1 init:)
		(fence2 init:)
		(bench init:)
		(northPath init:)
		(sidewalk init:)
		(northExit init:)
		(southExit init:)
		(westExit init:)
		(User canControl: 1 canInput: 1)
	)
	
	(method (doit)
		(if
		(and isDemo (not script) (ego inRect: 0 21 15 30))
			(self setScript: sGabeExits)
		)
		(super doit:)
	)
	
	(method (dispose)
		(UnLoad 129 410)
		(UnLoad 128 902)
		(UnLoad 128 409)
		(UnLoad 128 410)
		(UnLoad 128 411)
		(UnLoad 128 412)
		(UnLoad 128 414)
		(DisposeScript 941)
		(DisposeScript 939)
		(DisposeScript 21)
		(DisposeScript 932)
		(if modelessDialog (modelessDialog dispose:))
		(super dispose:)
		(theMusic1 fade:)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
			1
		else
			(super doVerb: theVerb)
		)
	)
	
	(method (reflectPosn param1 param2 &tmp temp0)
		(switch param2
			(1
				(= temp0 (param1 x?))
				(param1 y: 136)
			)
			(4
				(param1 x: (- 319 (param1 xStep?)))
			)
		)
	)
)

(instance sGabeExits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego ignoreHorizon: 1 setMotion: MoveTo (ego x?) 20 self)
			)
			(1
				(messager say: 13 13 8 0 0 401)
				(ego setMotion: MoveTo (ego x?) 35 self)
			)
			(2
				(ego ignoreHorizon: 0)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAnnoyDrummer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mimeTimer setReal: mimeTimer 20)
				((ScriptID 401 1)
					view: 422
					setSpeed: 6
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setMotion: PolyPath 122 136 self
				)
			)
			(1
				((ScriptID 401 1)
					setSpeed: 12
					view: 436
					setCel: 0
					setLoop: 0
					setCycle: End self
				)
			)
			(2
				((ScriptID 401 1) setCel: 0 setLoop: 1 setCycle: Fwd)
				(= seconds 4)
			)
			(3
				((ScriptID 401 1)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(4
				((ScriptID 401 1) setCel: 0 setLoop: 3 setCycle: Osc)
				(= seconds 4)
			)
			(5
				((ScriptID 401 1)
					setLoop: 2
					setCel: 6
					setCycle: End self
				)
			)
			(6
				(self changeState: (Random 2 3))
			)
		)
	)
)

(instance sMimeLeaves of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= ticks 60)
			)
			(1
				((ScriptID 401 1)
					view: 422
					ignoreHorizon: 1
					setSpeed: 6
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setMotion: PolyPath 5 35 self
				)
			)
			(2
				((ScriptID 401 1) setMotion: MoveTo -20 35 self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance drummer of Prop
	(properties
		x 95
		y 138
		noun 1
		view 410
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(11
				(messager say: noun theVerb 2)
			)
			(10
				(messager say: noun theVerb 2)
			)
			(7
				(messager say: noun theVerb 2)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance juggler of Prop
	(properties
		x 243
		y 120
		view 411
		cel 15
	)
)

(instance whisper of Prop
	(properties
		x 105
		y 138
		noun 2
		view 414
		cel 6
	)
)

(instance artist of Prop
	(properties
		x 66
		y 60
		noun 3
		view 412
		loop 3
		priority 2
		signal $0010
	)
)

(instance statue of View
	(properties
		x 2
		y 84
		noun 14
		modNum 401
		view 409
		priority 14
		signal $5030
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance bush of View
	(properties
		x 42
		y 57
		noun 11
		view 409
		cel 1
		priority 4
		signal $5030
	)
)

(instance tree of View
	(properties
		x 159
		y 128
		noun 11
		view 409
		cel 2
		priority 15
		signal $5030
	)
)

(instance easel of View
	(properties
		x 66
		y 60
		view 412
		loop 8
		priority 2
		signal $4030
	)
)

(instance trashCan of Feature
	(properties
		x 298
		y 97
		noun 12
		modNum 401
		nsTop 90
		nsLeft 290
		nsBottom 105
		nsRight 307
		sightAngle 40
		approachX 287
		approachY 103
		approachDist 57
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance lamp of Feature
	(properties
		x 300
		y 62
		noun 4
		modNum 401
		nsTop 40
		nsLeft 292
		nsBottom 85
		nsRight 308
		sightAngle 40
		approachX 262
		approachY 85
		approachDist 125
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fence1 of Feature
	(properties
		x 46
		y 106
		noun 3
		modNum 401
		nsTop 97
		nsLeft 28
		nsBottom 115
		nsRight 65
		sightAngle 40
		approachX 99
		approachY 108
		approachDist 104
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance fence2 of Feature
	(properties
		x 76
		y 122
		noun 3
		modNum 401
		nsTop 101
		nsLeft 66
		nsBottom 143
		nsRight 87
		sightAngle 40
		approachX 99
		approachY 108
		approachDist 89
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance bench of Feature
	(properties
		x 313
		y 137
		noun 1
		modNum 401
		nsTop 130
		nsLeft 308
		nsBottom 145
		nsRight 319
		sightAngle 40
		approachX 286
		approachY 131
		approachDist 104
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance sidewalk of Feature
	(properties
		noun 9
		modNum 401
	)
	
	(method (init)
		(super init:)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init:
						13
						23
						127
						24
						222
						50
						275
						80
						312
						130
						311
						141
						184
						141
						186
						132
						262
						132
						258
						114
						232
						89
						189
						68
						134
						51
						75
						43
						15
						42
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance northPath of Feature
	(properties
		noun 13
		modNum 401
	)
	
	(method (init)
		(super init:)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 21 22 21 37 1 37 1 22
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf
				theVerb
				43
				42
				66
				32
				28
				31
				15
				34
				18
				22
				46
				5
				50
				37
				19
				20
				23
				33
				4
				21
				56
				44
				17
				26
				41
				61
				49
				67
				48
				24
				64
				14
				39
				65
				62
				60
				30
				45
				3
				51
				35
				16
				40
				59
				36
				38
			)
			(messager say: noun 0 0 0 0 modNum)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance northExit of ExitFeature
	(properties
		nsTop 21
		nsBottom 30
		nsRight 15
		cursor 961
		exitDir 1
	)
)

(instance westExit of ExitFeature
	(properties
		nsTop 31
		nsBottom 107
		nsRight 4
		cursor 962
		exitDir 4
	)
)

(instance southExit of ExitFeature
	(properties
		nsTop 142
		nsLeft 88
		nsBottom 145
		nsRight 314
		cursor 964
		exitDir 3
	)
)

(instance specialMimeCode of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(Btst 101)
					((ScriptID 401 1) inRect: 87 125 120 140)
				)
				(Bclr 101)
				((ScriptID 401 1) setScript: sAnnoyDrummer)
			)
			(
				(and
					(not (Btst 101))
					(== ((ScriptID 401 1) script?) sAnnoyDrummer)
					(ego inRect: 122 120 140 140)
				)
				(Bset 101)
				(mimeTimer dispose:)
				((ScriptID 401 1)
					view: 422
					setSpeed: (ego moveSpeed?)
					setScript: 0
					setCycle: StopWalk 422
					setMotion: PFollow ego 15
					setLoop: -1
					setPri: -1
				)
			)
		)
	)
)

(instance mimeTimer of Timer
	(properties)
	
	(method (cue)
		(messager say: 1 13 3 1)
		((ScriptID 401 1) setScript: sMimeLeaves)
	)
)
