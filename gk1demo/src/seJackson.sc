;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include sci.sh)
(use Main)
(use Procs)
(use ExitFeature)
(use PFollow)
(use RangeOsc)
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
	seJackson 0
)

(local
	copOnScreen
)
(instance seJackson of Rm
	(properties
		modNum 401
		noun 8
		picture 430
		style $000a
		horizon 30
		north 410
		south 200
		west 440
		vanishingY -30000
	)
	
	(method (init)
		(theMusic1
			number: 410
			loop: -1
			play:
			setVol: 0
			fade: 80 5 10 0
		)
		(self setRegions: 401)
		(Load rsPIC 430)
		(Load rsVIEW 902)
		(Load rsVIEW 409)
		(Load rsVIEW 430)
		(Load rsVIEW 432)
		(ScriptID 941)
		(if (Btst 66) (walkHandler add: cop copHead radio bike))
		(ego
			view: 902
			setLoop: (ScriptID 401 2)
			setStep: 2 1
			setCycle: StopWalk -1
			init:
			state: 2
		)
		(switch prevRoomNum
			(440
				(ego posn: 5 (ego y?) setMotion: PolyPath 20 (ego y?))
				(if (Btst 101) ((ScriptID 401 1) posn: 5 (ego y?)))
			)
			(410
				(if (> (ego x?) 304) (ego x: 300))
				(ego posn: (ego x?) 31 setMotion: PolyPath (ego x?) 40)
				(if (Btst 101) ((ScriptID 401 1) posn: (ego x?) 31))
			)
			(else 
				(ego posn: 13 140 setMotion: PolyPath 13 130)
			)
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
						90
						1
						90
						25
						82
						31
						72
						36
						43
						44
						27
						47
						0
						47
						0
						147
						23
						146
						24
						140
						123
						140
						154
						139
						189
						129
						227
						116
						259
						99
						283
						76
						299
						52
						306
						30
						306
						1
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 156 48 179 40 180 37 202 38 207 43 216 46 221 57 148 57
					yourself:
				)
		)
		(bush init:)
		(tree init:)
		(guitar setCycle: RandCycle init:)
		(harmonica setCycle: RandCycle init:)
		(spoons setCycle: Fwd init:)
		(switch currentDay
			(1
				(if (Btst 66)
					(copHead init: approachVerbs: 10 11)
					(radio init:)
					(cop init: approachVerbs: 10 11 setScript: sCopStuff)
					(bike init: setScript: sRadio)
					(curRoom
						addObstacle:
							((Polygon new:)
								type: 2
								init: 28 126 77 126 78 131 26 131
								yourself:
							)
					)
				)
			)
		)
		(lamp init:)
		(trash init:)
		(sidewalk init:)
		(fence init:)
		(westExit init:)
		(northExit init:)
		(southExit init:)
		(User canControl: 1 canInput: 1)
	)
	
	(method (doit)
		(if
			(and
				(== currentDay 1)
				(Btst 66)
				(not copOnScreen)
				(not script)
				(or (ego inRect: 0 41 3 135) (ego inRect: 87 21 312 35))
			)
			(self setScript: sGabeExits)
		)
		(super doit:)
	)
	
	(method (dispose)
		(UnLoad 129 430)
		(UnLoad 128 902)
		(UnLoad 128 409)
		(UnLoad 128 430)
		(UnLoad 128 432)
		(if (Btst 66)
			(walkHandler delete: cop copHead radio bike)
		)
		(DisposeScript 941)
		(DisposeScript 939)
		(DisposeScript 932)
		(DisposeScript 938)
		(DisposeScript 21)
		(if (and (== (ego edgeHit?) 3) (Btst 101))
			(Bclr 101)
		)
		(if modelessDialog (modelessDialog dispose:))
		(theMusic1 fade:)
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
				(ego setMotion: 0)
				(cop setScript: sCopReturns self)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sAnnoyBlues of Script
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
					setMotion: PolyPath 175 65 self
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

(instance sCopStuff of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(copHead hide:)
				(cond 
					((< (= temp0 (Random 1 100)) 75) (self changeState: 1))
					((and (< 76 temp0) (< temp0 90)) (self changeState: 3))
					((and (< 91 temp0) (< temp0 95)) (self changeState: 3))
					(else (self changeState: 5))
				)
			)
			(1
				(copHead setCycle: Fwd show:)
				(cop setLoop: 5 setCel: 0)
				(= seconds 3)
			)
			(2 2 (self changeState: 0))
			(3
				(cop setLoop: 1 setCel: 0 setCycle: End self)
			)
			(4 (self changeState: 0))
			(5
				(cop setLoop: 2 setCel: 0 setCycle: End self)
			)
			(6 (self changeState: 0))
			(7
				(cop setLoop: 4 setCel: 0 setCycle: End self)
			)
			(8 (= seconds 3))
			(9 (cop setCycle: Beg self))
			(10 (self changeState: 0))
		)
	)
)

(instance sAnnoyCop of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if modelessDialog (modelessDialog dispose:))
				(ego setMotion: PolyPath 100 140 self)
				((ScriptID 401 1)
					view: 422
					setCycle: StopWalk 422
					setLoop: -1
					setPri: -1
					setSpeed: 6
					ignoreControl: -32768
					setMotion: PolyPath 89 115 self
				)
			)
			(1 0)
			(2
				(Face ego cop)
				(cop setLoop: 1 setCycle: Fwd)
				((ScriptID 401 1)
					view: 433
					setLoop: 0
					setSpeed: 12
					setCel: 0
					setCycle: End self
				)
			)
			(3
				((ScriptID 401 1)
					setLoop: 1
					setCel: 0
					setCycle: End self
				)
			)
			(4
				(copHead hide:)
				(cop view: 434 setLoop: 0 setCel: 0 setCycle: End)
				((ScriptID 401 1)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(5
				(cop setCycle: ROsc 5 7)
				((ScriptID 401 1) setLoop: 3 setCel: 0 setCycle: Osc)
				(if (not (Btst 108))
					(messager say: 6 13 11 1 self)
				else
					(messager say: 6 13 12 1)
					(self changeState: 9)
				)
			)
			(6
				((ScriptID 401 1) setCel: 0 setCycle: Beg self)
			)
			(7
				(messager say: 6 13 11 2 self)
				((ScriptID 401 1) setLoop: 1 setCel: 0 setCycle: Osc)
			)
			(8
				((ScriptID 401 1)
					setLoop: 2
					setCel: 0
					setCycle: End self
				)
			)
			(9
				((ScriptID 401 1)
					setLoop: 4
					setCel: 0
					setCycle: End self
				)
			)
			(10
				((ScriptID 401 1) setLoop: 5 setCel: 0 setCycle: End)
				(= ticks 120)
			)
			(11
				(cop setLoop: 2 setCel: 0 setCycle: End self)
			)
			(12
				(if (not (Btst 108))
					(ego getPoints: 124 1)
					(messager say: 6 13 11 3)
					(Bset 108)
				)
				(cop
					setLoop: 3
					setCel: 0
					setCycle: Fwd
					cycleSpeed: 6
					ignoreControl: -32768
					setMotion: MoveTo -30 (cop y?) self
				)
			)
			(13
				(= copOnScreen 0)
				(theGame handsOn:)
				(= seconds 20)
			)
			(14
				(if (!= (ego script?) sOperateRadio)
					(cop setScript: sCopReturns)
				)
			)
		)
	)
)

(instance sCopReturns of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= copOnScreen 1)
				(messager say: 11 0 33)
				(cop
					setLoop: 4
					setCel: 0
					setCycle: Fwd
					setSpeed: 6
					ignoreActors: 1
					ignoreControl: -32768
					setMotion: MoveTo 59 132 self
				)
			)
			(1
				(cop view: 432 setScript: sCopStuff)
				((ScriptID 401 1) setScript: 0)
			)
		)
	)
)

(instance sRadio of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 15))
			(1
				(if
					(or
						modelessDialog
						(== ((ScriptID 401 1) script?) sAnnoyCop)
					)
					(-- state)
				else
					0
				)
				(= cycles 1)
			)
			(2
				(theSound1 number: 4312 play: self)
			)
			(3
				(theSound1 number: (Random 4301 4304) play: self)
			)
			(4
				(theSound1 number: 4313 play: self)
			)
			(5 (self changeState: 0))
		)
	)
)

(instance sOperateRadio of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: PolyPath 42 133 self)
			)
			(1
				(ego
					view: 435
					setCel: 0
					setLoop: 0
					setSpeed: 12
					setCycle: End self
				)
			)
			(2
				(messager say: 7 8 14 1 self)
			)
			(3
				(theSound1 number: 4312 play: self)
			)
			(4
				(theSound1 number: 4305 play: self)
			)
			(5
				(theSound1 number: 4313 play: self)
			)
			(6
				(theSound1 number: 4312 play: self)
			)
			(7
				(theSound1 number: 4309 play: self)
			)
			(8
				(theSound1 number: 4313 play: self)
			)
			(9
				(theSound1 number: 4312 play: self)
			)
			(10
				(theSound1 number: 4306 play: self)
			)
			(11
				(theSound1 number: 4313 play: self)
			)
			(12
				(theSound1 number: 4312 play: self)
			)
			(13
				(theSound1 number: 4310 play: self)
			)
			(14
				(theSound1 number: 4313 play: self)
			)
			(15
				(theSound1 number: 4312 play: self)
			)
			(16
				(theSound1 number: 4307 play: self)
			)
			(17
				(theSound1 number: 4313 play: self)
			)
			(18
				(theSound1 number: 4312 play: self)
			)
			(19
				(theSound1 number: 4311 play: self)
			)
			(20
				(theSound1 number: 4313 play: self)
			)
			(21
				(theSound1 number: 4312 play: self)
			)
			(22
				(theSound1 number: 4308 play: self)
			)
			(23
				(theSound1 number: 4313 play: self)
			)
			(24
				(ego setCycle: Beg self)
				(messager say: 7 8 14 9)
			)
			(25
				(= ticks 80)
				(cop setScript: sCopReturns)
			)
			(26
				(messager say: 7 8 14 10 self)
				(ego
					normalize: 1 902
					setLoop: (ScriptID 401 2)
					setStep: 2 1
				)
			)
			(27
				(theGame handsOn:)
				(messager say: 7 8 14 11)
				((ScriptID 401 1) setScript: 0)
				(self dispose:)
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
					setLoop: 4
					setCel: 0
					setCycle: End self
				)
			)
			(2
				((ScriptID 401 1)
					view: 422
					posn: 159 65
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

(instance cop of Actor
	(properties
		x 59
		y 132
		noun 6
		approachX 48
		approachY 142
		view 432
		loop 1
		cycleSpeed 12
	)
	
	(method (init)
		(= copOnScreen 1)
		(super init:)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 13)
			(if (and (== theVerb 13) (Btst 101) (Btst 64))
				(messager say: 6 13 10)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance guitar of Prop
	(properties
		x 191
		y 44
		noun 1
		view 430
		cel 6
		priority 2
		signal $4010
		cycleSpeed 12
	)
)

(instance spoons of Prop
	(properties
		x 177
		y 54
		noun 1
		view 430
		loop 5
		cel 14
		priority 2
		signal $4010
	)
)

(instance harmonica of Prop
	(properties
		x 213
		y 56
		noun 1
		view 430
		loop 3
		cel 4
		priority 2
		signal $4010
		cycleSpeed 12
	)
)

(instance copHead of Prop
	(properties
		x 58
		y 109
		noun 6
		approachX 48
		approachY 142
		view 432
		cel 5
		priority 9
		signal $4010
		cycleSpeed 120
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 13)
			(if (and (== theVerb 13) (Btst 101) (Btst 64))
				(messager say: 6 13 10)
			)
		else
			(super doVerb: theVerb)
		)
	)
)

(instance bush of View
	(properties
		x 40
		y 88
		noun 11
		modNum 401
		view 409
		loop 2
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

(instance tree of View
	(properties
		x 158
		y 39
		noun 11
		modNum 401
		view 409
		loop 2
		cel 1
		priority 2
		signal $4030
	)
	
	(method (doVerb theVerb)
		(cond 
			(
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
			)
			((== theVerb 7) (messager say: 11 7 0 0 0 401))
		)
	)
)

(instance bike of Prop
	(properties
		x 53
		y 130
		noun 5
		view 432
		loop 3
		signal $5000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (== ((ScriptID 401 1) script?) sAnnoyCop)
					(if (not (Btst 64)) (ego getPoints: 125 2) (Bset 64))
					(ego setScript: sOperateRadio)
				else
					(super doVerb: theVerb)
				)
			)
			(13
				(if (and (== theVerb 13) (Btst 101) (Btst 64))
					(messager say: 6 13 10)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance radio of Feature
	(properties
		x 52
		y 190
		noun 7
		nsTop 105
		nsLeft 36
		nsBottom 115
		nsRight 44
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(if (and (Btst 101) (Btst 64))
					(messager say: 6 13 10)
				)
			)
			(8
				(if copOnScreen
					(messager say: 7 8 13)
				else
					(if (not (Btst 64)) (ego getPoints: 125 2) (Bset 64))
					(ego setScript: sOperateRadio)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lamp of Feature
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
					init: 27 108 28 143 19 143 19 108
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 144 98 146 140 131 140 131 99
					yourself:
				)
				((Polygon new:)
					type: 0
					init: 296 27 310 27 310 64 296 64
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
					init: 0 22 79 22 73 28 37 38 0 38
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
					init: 36 40 68 30 124 30 115 38 92 52 55 60 11 60 11 40
					yourself:
				)
				((Polygon new:)
					type: 0
					init:
						116
						121
						198
						96
						255
						56
						267
						29
						300
						29
						297
						52
						273
						79
						220
						109
						149
						132
						114
						136
						82
						142
						59
						141
						59
						124
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(
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
			)
			(10
				(messager say: 8 11 0 1 0 401)
			)
			(11
				(messager say: 8 11 0 1 0 401)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance trash of Feature
	(properties
		x 300
		y 45
		noun 12
		modNum 401
		nsTop 40
		nsLeft 293
		nsBottom 51
		nsRight 307
		sightAngle 40
		approachX 289
		approachY 44
		approachDist 46
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(
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
			)
			(10
				(messager say: 8 11 0 1 0 401)
			)
			(11
				(messager say: 8 11 0 1 0 401)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance southExit of ExitFeature
	(properties
		nsTop 135
		nsBottom 145
		nsRight 20
		cursor 964
		exitDir 3
	)
)

(instance westExit of ExitFeature
	(properties
		nsTop 41
		nsBottom 135
		nsRight 3
		cursor 962
		exitDir 4
	)
)

(instance northExit of ExitFeature
	(properties
		nsTop 21
		nsLeft 87
		nsBottom 30
		nsRight 312
		cursor 961
		exitDir 1
	)
)

(instance specialMimeCode of Code
	(properties)
	
	(method (doit)
		(cond 
			(
				(and
					(Btst 101)
					((ScriptID 401 1) inRect: 164 45 231 60)
				)
				(Bclr 101)
				((ScriptID 401 1) setScript: sAnnoyBlues)
			)
			(
				(and
					(not (Btst 101))
					(== ((ScriptID 401 1) script?) sAnnoyBlues)
					(ego inRect: 164 65 184 80)
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
			(
				(and
					(Btst 101)
					copOnScreen
					((ScriptID 401 1) inRect: 57 111 90 150)
					(not (Btst 64))
				)
				(Bclr 101)
				(cop setScript: 0)
				((ScriptID 401 1) setScript: sAnnoyCop)
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
