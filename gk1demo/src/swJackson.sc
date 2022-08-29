;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
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
	swJackson 0
)

(local
	talkedToTapper =  1
	timesTalkedToVendorBefore =  1
	timesTalkedToVendorAfter =  1
)
(instance swJackson of Rm
	(properties
		modNum 401
		noun 8
		picture 430
		style $400a
		horizon 30
		north 420
		east 430
		south 200
		vanishingY -30000
	)
	
	(method (init)
		(self setRegions: 401)
		(Load rsPIC 430)
		(Load rsVIEW 902)
		(Load rsVIEW 409)
		(Load rsVIEW 445)
		(ScriptID 941)
		(theMusic2
			number: 440
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
			(430
				(ego
					posn: 315 (ego y?)
					setMotion: PolyPath 300 (ego y?)
				)
				(if (Btst 101) ((ScriptID 401 1) posn: 319 (ego y?)))
			)
			(420
				(if (< (ego x?) 20) (ego x: 20))
				(ego posn: (ego x?) 31 setMotion: PolyPath (ego x?) 40)
				(if (Btst 101) ((ScriptID 401 1) posn: (ego x?) 31))
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
		(super init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						319
						46
						295
						46
						271
						43
						252
						38
						237
						32
						230
						26
						231
						1
						14
						1
						14
						47
						18
						63
						47
						93
						86
						125
						118
						140
						291
						140
						295
						144
						319
						144
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 94 58 112 58 112 49 133 49 133 57 160 57 160 82 134 82 134 75 94 75
					yourself:
				)
		)
		(bush init:)
		(tree init:)
		(trash init:)
		(lamps init:)
		(sidewalk init:)
		(fence init:)
		(northExit init:)
		(southExit init:)
		(eastExit init:)
		(if (and (not isDemo) (< currentDay 3))
			(luckyDogCart init:)
			(luckyDogVendor
				init:
				approachVerbs: 11 10 5
				setScript: sVendorStuff
			)
			(tapper
				init:
				approachVerbs: 11 33
				setCycle: Fwd
				setScript: sTapDance
			)
		)
		(if (< currentDay 3)
			(trumpet init: setCycle: RandCycle)
			(banjo init: setCycle: Fwd)
			(bass init: setCycle: RandCycle)
			(trombone init: setCycle: RandCycle)
		)
		(User canControl: 1 canInput: 1)
	)
	
	(method (dispose)
		(UnLoad 129 430)
		(UnLoad 128 902)
		(UnLoad 128 409)
		(UnLoad 128 445)
		(DisposeScript 941)
		(DisposeScript 939)
		(DisposeScript 932)
		(DisposeScript 21)
		(if (and (== (ego edgeHit?) 3) (Btst 101))
			(Bclr 101)
		)
		(if modelessDialog (modelessDialog dispose:))
		(theMusic2 dispose:)
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

(instance sVendorStuff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(luckyDogVendor setLoop: 0 setCycle: 0)
				(= seconds 10)
			)
			(1
				(luckyDogVendor setCel: 4 setCycle: Beg self)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance sTapDance of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(tapper setLoop: 3)
				(= cycles 12)
			)
			(1
				(cond 
					((< (= temp0 (Random 0 100)) 30) (tapper loop: 0))
					((and (< 31 temp0) (< temp0 60)) (tapper loop: 1))
					((and (< 61 temp0) (< temp0 90)) (tapper loop: 2))
					(else (self changeState: 0))
				)
				(= cycles 1)
			)
			(2
				(tapper setCycle: Fwd)
				(= seconds (Random 1 3))
			)
			(3 (self changeState: 1))
		)
	)
)

(instance sGiveHotDog of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 3 33 0 1 self)
			)
			(1
				(tapper hide:)
				(ego
					looper: 0
					view: 441
					setSpeed: 12
					setLoop: 5
					setCycle: 0
					setCel: 0
				)
				(messager say: 3 33 0 2 self)
			)
			(2 (ego setCycle: End self))
			(3
				(messager say: 3 33 0 3)
				(= ticks 60)
			)
			(4
				(Bset 111)
				(tapper show:)
				(ego normalize: 1 902)
				(ego setLoop: (ScriptID 401 2) setStep: 2 1)
				(theGame handsOn:)
			)
		)
	)
)

(instance sGetHotDog of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 4 5 0 1 self)
			)
			(1 (messager say: 4 5 0 2 self))
			(2 (messager say: 4 5 0 3 self))
			(3 (messager say: 4 5 0 4 self))
			(4 (messager say: 4 5 0 5 self))
			(5
				(luckyDogVendor
					setScript: 0
					setLoop: 1
					setCel: 0
					setCycle: End
				)
				(messager say: 4 5 0 6 self)
			)
			(6 (messager say: 4 5 0 7 self))
			(7 (messager say: 4 5 0 8 self))
			(8
				(luckyDogVendor setLoop: 3 setCel: 0 setCycle: End self)
			)
			(9 (messager say: 4 5 0 9 self))
			(10
				(messager say: 4 5 0 10 self)
				(luckyDogVendor setLoop: 2 setCel: 0 setCycle: End)
			)
			(11
				(luckyDogVendor setLoop: 1 setCel: 6 setCycle: Beg self)
			)
			(12
				(theGame handsOn:)
				(luckyDogVendor setScript: sVendorStuff)
				(self dispose:)
			)
		)
	)
)

(instance sAnnoyJazz of Script
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
					setMotion: PolyPath 129 86 self
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
					view: 4201
					setLoop: 5
					setCel: 0
					setCycle: End self
				)
			)
			(2
				((ScriptID 401 1)
					view: 422
					posn: 145 86
					ignoreHorizon: 1
					setSpeed: 6
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setMotion: PolyPath 160 10 self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance luckyDogVendor of Actor
	(properties
		x 269
		y 53
		noun 4
		approachX 255
		approachY 57
		view 440
		cycleSpeed 12
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(ego get: 33)
				(ego put: 5)
				(curRoom setScript: sGetHotDog)
			)
			(11
				(cond 
					(
					(and (Btst 111) (== timesTalkedToVendorAfter 1)) (messager say: noun 11 6) (++ timesTalkedToVendorAfter))
					(
					(and (Btst 111) (> timesTalkedToVendorAfter 1)) (messager say: noun 11 7))
					(
					(and (not (Btst 111)) (== timesTalkedToVendorBefore 1)) (messager say: noun 11 8) (++ timesTalkedToVendorBefore))
					(
					(and (not (Btst 111)) (>= timesTalkedToVendorBefore 1)) (messager say: noun 11 9))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tapper of Actor
	(properties
		x 233
		y 58
		noun 3
		approachX 249
		approachY 57
		view 441
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(7
				(if (Btst 111)
					(messager say: noun 7 2)
				else
					(messager say: noun 7 3)
				)
			)
			(33
				(ego put: 33)
				(curRoom setScript: sGiveHotDog)
			)
			(11
				(cond 
					((Btst 111) (messager say: noun 11 2))
					((== talkedToTapper 1) (messager say: noun 11 4) (++ talkedToTapper))
					(else (messager say: noun 11 5))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance trumpet of Prop
	(properties
		x 132
		y 71
		noun 1
		view 445
		cel 5
		priority 3
		signal $4010
		cycleSpeed 24
	)
)

(instance banjo of Prop
	(properties
		x 118
		y 67
		noun 1
		view 445
		loop 2
		cel 1
		priority 3
		signal $4010
		cycleSpeed 12
	)
)

(instance bass of Prop
	(properties
		x 110
		y 73
		noun 1
		view 445
		loop 4
		cel 5
		priority 3
		signal $4010
		cycleSpeed 12
	)
)

(instance trombone of Prop
	(properties
		x 146
		y 78
		noun 1
		view 445
		loop 6
		cel 1
		priority 3
		signal $4010
		cycleSpeed 24
	)
)

(instance luckyDogCart of View
	(properties
		x 255
		y 29
		noun 10
		view 440
		loop 4
	)
)

(instance tree of View
	(properties
		x 109
		y 115
		noun 11
		view 409
		loop 3
		priority 15
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

(instance bush of View
	(properties
		x 269
		y 81
		noun 11
		view 409
		loop 3
		cel 1
		priority 6
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

(instance lamps of Feature
	(properties
		noun 4
		modNum 401
	)
	
	(method (init)
		(super init:)
		(self
			setOnMeCheck:
				2
				((Polygon new:)
					type: 0
					init: 22 29 22 64 13 64 13 29
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 189 100 189 140 175 140 175 100
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 302 109 302 141 291 141 291 109
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
					init: 319 21 319 37 269 33 240 22
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
					init: 234 24 264 35 314 39 315 61 275 60 228 51 200 32 200 23
					yourself:
				)
				((Polygon new:)
					type: 0
					init:
						53
						29
						69
						62
						108
						89
						163
						110
						226
						122
						300
						125
						289
						140
						235
						141
						173
						133
						84
						101
						24
						54
						24
						30
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

(instance trash of Feature
	(properties
		x 19
		y 46
		noun 12
		modNum 401
		nsTop 40
		nsLeft 13
		nsBottom 53
		nsRight 25
		sightAngle 40
		approachX 29
		approachY 48
		approachDist 37
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
		nsLeft 9
		nsBottom 30
		nsRight 234
		cursor 961
		exitDir 1
	)
)

(instance southExit of ExitFeature
	(properties
		nsTop 135
		nsLeft 301
		nsBottom 145
		nsRight 319
		cursor 964
		exitDir 3
	)
)

(instance eastExit of ExitFeature
	(properties
		nsTop 40
		nsLeft 313
		nsBottom 136
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
					((ScriptID 401 1) inRect: 85 64 163 84)
				)
				(Bclr 101)
				((ScriptID 401 1) setScript: sAnnoyJazz)
			)
			(
				(and
					(not (Btst 101))
					(== ((ScriptID 401 1) script?) sAnnoyJazz)
					(ego inRect: 115 86 145 90)
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
		(messager say: 1 13 1 1)
		((ScriptID 401 1) setScript: sMimeLeaves)
	)
)
