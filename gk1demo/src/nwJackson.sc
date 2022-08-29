;;; Sierra Script 1.0 - (do not remove this comment)
(script# 420)
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
	nwJackson 0
	sMimeStuff 1
)

(instance nwJackson of Rm
	(properties
		modNum 401
		noun 8
		picture 410
		style $400a
		horizon 25
		east 410
		south 440
		vanishingY -30000
	)
	
	(method (init)
		(self setRegions: 401)
		(theMusic2
			number: 420
			loop: -1
			play:
			setVol: 0
			fade: 80 5 10 0
		)
		(Load rsPIC 410)
		(Load rsVIEW 409)
		(Load rsVIEW 902)
		(Load rsVIEW 427)
		(ScriptID 941)
		(ego
			view: 902
			setLoop: (ScriptID 401 2)
			setCycle: StopWalk -1
			init:
			state: 2
		)
		(switch prevRoomNum
			(410
				(ego
					posn: 315 (ego y?)
					setMotion: PolyPath 300 (ego y?)
				)
				(if (Btst 101) ((ScriptID 401 1) posn: 319 (ego y?)))
			)
			(440
				(if (> (ego x?) 225) (ego x: 225))
				(if (< (ego x?) 20) (ego x: 20))
				(ego
					posn: (ego x?) 140
					setMotion: PolyPath (ego x?) 130
				)
				(if (Btst 101) ((ScriptID 401 1) posn: (ego x?) 185))
			)
			(else  (ego posn: 160 100))
		)
		(if (and (Btst 101) (== currentDay 1))
			((ScriptID 401 1)
				view: 422
				code: specialMimeCode
				setCycle: StopWalk 422
				setMotion: PFollow ego 15
				setLoop: -1
				setPri: -1
				init:
			)
		)
		(if (and (not (Btst 101)) (== currentDay 1))
			((ScriptID 401 1)
				view: 422
				code: specialMimeCode
				posn: 245 63
				init:
				setScript: sMimeStuff
			)
		)
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						309
						20
						297
						28
						277
						27
						224
						28
						190
						31
						158
						37
						120
						48
						81
						64
						52
						81
						33
						100
						13
						132
						13
						189
						229
						189
						229
						121
						237
						117
						249
						116
						260
						111
						262
						108
						284
						104
						319
						103
						319
						20
					yourself:
				)
				((Polygon new:)
					type: 2
					init:
						88
						87
						129
						87
						129
						82
						151
						82
						151
						94
						163
						94
						163
						111
						138
						111
						138
						97
						103
						97
						103
						109
						88
						109
					yourself:
				)
		)
		(bush init:)
		(statue init:)
		(if (< currentDay 3)
			(slide init: setCycle: RandCycle)
			(accordian init: setCycle: Fwd)
			(washboard init: setCycle: Fwd)
			(jug init: setCycle: RandCycle)
		)
		(lamp init:)
		(trash init:)
		(bench init:)
		(sidewalk init:)
		(fence init:)
		(northExit init:)
		(southExit init:)
		(eastExit init:)
		(User canControl: 1 canInput: 1)
	)
	
	(method (doit)
		(if
		(and isDemo (not script) (ego inRect: 308 21 319 30))
			(self setScript: sGabeExits)
		)
		(super doit:)
	)
	
	(method (dispose)
		(UnLoad 129 410)
		(UnLoad 128 409)
		(UnLoad 128 902)
		(UnLoad 128 427)
		(DisposeScript 941)
		(DisposeScript 939)
		(DisposeScript 932)
		(DisposeScript 21)
		(if modelessDialog (modelessDialog dispose:))
		(theMusic2 fade:)
		(super dispose:)
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
			(1 (param1 y: 136))
			(2
				(param1 x: (+ 0 (param1 xStep?)))
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

(instance sAnnoyCajons of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(mimeTimer setReal: mimeTimer 20)
				((ScriptID 401 1)
					view: 422
					ignoreHorizon: 1
					ignoreActors: 1
					setSpeed: 6
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setMotion: PolyPath 122 118 self
				)
			)
			(1
				((ScriptID 401 1)
					setSpeed: 12
					view: 4271
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
				((ScriptID 401 1) setLoop: 2 setCel: 0 setCycle: Fwd)
				(= seconds 4)
			)
			(4
				((ScriptID 401 1) setCel: 0 setLoop: 3 setCycle: Osc)
				(= seconds 4)
			)
			(5
				(self changeState: (Random 2 4))
			)
		)
	)
)

(instance sMimeStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 401 1)
					view: 420
					setCel: 0
					setSpeed: 12
					setLoop: 0
					setCycle: End self
				)
			)
			(1 (= seconds 4))
			(2
				((ScriptID 401 1) setCycle: Beg self)
			)
			(3 (= seconds 4))
			(4
				((ScriptID 401 1) setCycle: Beg self)
			)
			(5
				((ScriptID 401 1)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(6
				((ScriptID 401 1) setLoop: 3 setCel: 0 setCycle: Fwd)
				(= seconds 4)
			)
			(7
				((ScriptID 401 1) setLoop: 4 setCel: 0 setCycle: Fwd)
				(= seconds 4)
			)
			(8
				((ScriptID 401 1)
					setLoop: 2
					setCel: 8
					setCycle: Beg self
				)
			)
			(9 (= seconds 4))
			(10 (self changeState: 0))
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
					view: 4201
					setLoop: 5
					setCel: 0
					setCycle: End self
				)
			)
			(2
				((ScriptID 401 1)
					view: 422
					posn: 138 118
					ignoreHorizon: 1
					setSpeed: 6
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setMotion: PolyPath 245 63 self
				)
			)
			(3
				(theGame handsOn:)
				((ScriptID 401 1) setScript: sMimeStuff)
				(self dispose:)
			)
		)
	)
)

(instance mimeTimer of Timer
	(properties)
	
	(method (cue)
		(messager say: 1 13 1 1)
		((ScriptID 401 1) setScript: sMimeLeaves)
	)
)

(instance accordian of Prop
	(properties
		x 93
		y 106
		noun 1
		view 427
		loop 1
		cel 4
		priority 7
		signal $4010
		cycleSpeed 9
	)
)

(instance washboard of Prop
	(properties
		x 151
		y 108
		noun 1
		view 427
		loop 2
		cel 5
		priority 7
		signal $4010
		cycleSpeed 9
	)
)

(instance slide of Prop
	(properties
		x 137
		y 94
		noun 1
		view 427
		loop 4
		cel 12
		priority 6
		signal $4010
		cycleSpeed 9
	)
)

(instance jug of Prop
	(properties
		x 112
		y 97
		noun 1
		view 427
		loop 5
		cel 9
		priority 6
		signal $4010
		cycleSpeed 9
	)
)

(instance bush of View
	(properties
		x 279
		y 63
		noun 11
		modNum 401
		view 409
		loop 1
		priority 4
		signal $4030
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

(instance statue of View
	(properties
		x 313
		y 87
		noun 14
		modNum 401
		view 409
		loop 1
		cel 1
		priority 14
		signal $4030
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
		x 20
		y 63
		noun 4
		modNum 401
		nsTop 42
		nsLeft 14
		nsBottom 84
		nsRight 26
		sightAngle 40
		approachX 49
		approachY 92
		approachDist 140
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

(instance trash of Feature
	(properties
		x 18
		y 97
		noun 12
		modNum 401
		nsTop 89
		nsLeft 11
		nsBottom 106
		nsRight 26
		sightAngle 40
		approachX 30
		approachY 105
		approachDist 31
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
		x 4
		y 140
		noun 1
		modNum 401
		nsTop 135
		nsBottom 145
		nsRight 9
		sightAngle 40
		approachX 18
		approachY 137
		approachDist 116
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
						301
						41
						215
						45
						155
						58
						100
						81
						63
						109
						55
						131
						192
						131
						207
						110
						230
						101
						229
						143
						9
						143
						11
						126
						28
						97
						47
						79
						87
						56
						155
						32
						204
						25
						301
						25
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

(instance fence of Feature
	(properties
		noun 3
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
						319
						107
						291
						107
						275
						111
						240
						125
						259
						134
						319
						140
						318
						144
						238
						144
						231
						134
						234
						107
						269
						98
						318
						97
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

(instance southExit of ExitFeature
	(properties
		nsTop 140
		nsLeft 9
		nsBottom 145
		nsRight 231
		cursor 964
		exitDir 3
	)
)

(instance northExit of ExitFeature
	(properties
		nsTop 21
		nsLeft 308
		nsBottom 30
		nsRight 319
		cursor 961
		exitDir 1
	)
)

(instance eastExit of ExitFeature
	(properties
		nsTop 31
		nsLeft 316
		nsBottom 109
		nsRight 319
		cursor 963
		exitDir 2
	)
)

(instance specialMimeCode of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(Btst 101)
					((ScriptID 401 1) inRect: 83 76 170 114)
				)
				(Bclr 101)
				((ScriptID 401 1) setScript: sAnnoyCajons)
			)
			(
				(and
					(not (Btst 101))
					(== ((ScriptID 401 1) script?) sAnnoyCajons)
					(ego inRect: 112 118 132 128)
				)
				(Bset 101)
				(mimeTimer dispose:)
				((ScriptID 401 1)
					view: 422
					setScript: 0
					setCycle: StopWalk 422
					setMotion: PFollow ego 15
					setLoop: -1
					setPri: -1
				)
			)
			(
				(and
					(not (Btst 101))
					(== ((ScriptID 401 1) script?) sMimeStuff)
					(ego inRect: 229 50 270 65)
				)
				(Bset 101)
				((ScriptID 401 1)
					view: 422
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
