;;; Sierra Script 1.0 - (do not remove this comment)
(script# 10100)
(include sci.sh)
(use Main)
(use TorinEgo)
(use ScrollExit)
(use TPRoom)
(use TPScript)
(use TPSound)
(use CueMe)
(use ScrollView)
(use Events)
(use ExitFeature)
(use Print)
(use Scaler)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	roForestScroll 0
)

(local
	local0
	local1
	local2 =  9
	theGSel_57
	local4
	local5
	local6
	local7
	local8
)
(procedure (localproc_02be param1 param2 param3 &tmp temp0 temp1)
	(if (> argc 2) (= temp1 param3) else (= temp1 0))
	(if (> param1 1614) (= temp0 2) else (= temp0 0))
	(if (== local0 temp0)
		(ego setMotion: PolyPath param1 param2 temp1)
		(return)
	)
	(switch temp0
		(0
			(LOOKUP_ERROR
				setScript:
					(LOOKUP_ERROR setNoScore: param1 unSet: param2 yourself:)
					temp1
			)
		)
		(2
			(LOOKUP_ERROR
				setScript:
					(LOOKUP_ERROR setNoScore: param1 unSet: param2 yourself:)
					temp1
			)
		)
	)
)

(instance poNull of Prop
	(properties)
)

(class soCrossTreeL of TPScript
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		oMessageList 0
		nTextWidth 0
		createDisplay 1
		setNoScore 0
		unSet 0
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local0 1)
					(self setScript: LOOKUP_ERROR self)
				else
					(self setScript: LOOKUP_ERROR self)
				)
			)
			(1
				(theGame handsOn:)
				(ego setMotion: PolyPath setNoScore unSet self)
			)
			(2 (self dispose:))
		)
	)
)

(class soCrossTreeR of TPScript
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		oMessageList 0
		nTextWidth 0
		createDisplay 1
		setNoScore 0
		unSet 0
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local0 1)
					(self setScript: LOOKUP_ERROR self)
				else
					(self setScript: LOOKUP_ERROR self)
				)
			)
			(1
				(theGame handsOn:)
				(ego setMotion: PolyPath setNoScore unSet self)
			)
			(2 (self dispose:))
		)
	)
)

(instance poTorinSwings of Prop
	(properties
		priority 320
		fixPriority 1
		view 10112
		cycleSpeed 8
	)
)

(instance soGoThroughTreeR of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 1393 324 self)
			)
			(1
				(theGame handsOff:)
				(ego setMotion: MoveTo 1393 324 self)
				((ScriptID 64018 0) setTile: 1434 301 0)
			)
			(2
				(ego setHeading: 45 self)
				((curRoom plane?) sitNSpin: 1298 0 self 1 10 10)
				(ego bMouseDown: 0)
			)
			(3)
			(4
				(ego hide:)
				(LOOKUP_ERROR
					loop: 0
					cel: 0
					posn: 1630 322
					init:
					setCycle: CT 10 1 self
				)
			)
			(5
				(LOOKUP_ERROR setCycle: End self)
				((ScriptID 64018 0) hide:)
				(LOOKUP_ERROR
					loop: 0
					cel: 0
					posn: 1429 304
					init:
					setCycle: End LOOKUP_ERROR
				)
			)
			(6
				((curRoom plane?) sitNSpin: 1614 0 self 1 10 10)
			)
			(7
				(= local0 2)
				(ego posn: 1858 245)
				(ego setScaler: Scaler 100 87 302 246)
				(ego setLoop: 4)
				(ego show:)
				(ego setAvoider: 0)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soGoThroughTreeL of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 1767 239 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
				((curRoom plane?) sitNSpin: 1298 0 self 1 10 10)
				(ego bMouseDown: 0)
				((ScriptID 64018 0) setTile: 1822 280 0)
			)
			(2)
			(3
				(ego hide:)
				(LOOKUP_ERROR
					loop: 2
					cel: 0
					posn: 1404 303
					init:
					setCycle: CT 10 1 self
				)
			)
			(4
				(LOOKUP_ERROR setCycle: End self)
				((ScriptID 64018 0) hide:)
				(LOOKUP_ERROR
					loop: 1
					cel: 0
					posn: 1822 280
					init:
					setCycle: End LOOKUP_ERROR
				)
			)
			(5
				((curRoom plane?) sitNSpin: 1052 0 self 1 10 10)
			)
			(6
				(= local0 0)
				(ego posn: 1402 308)
				(ego setScale: 0)
				(ego setLoop: 5)
				(ego show:)
				(ego setAvoider: 0)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oBoogleEndRight of CueMe
	(properties)
	
	(method (cue)
		(LOOKUP_ERROR dispose:)
		((ScriptID 64018 0) posn: 1855 281)
		((ScriptID 64018 0) setLoop: 6)
		((ScriptID 64018 0) show:)
		((ScriptID 64018 0) bSwing: 1)
	)
)

(instance oBoogleEndLeft of CueMe
	(properties)
	
	(method (cue)
		(LOOKUP_ERROR dispose:)
		((ScriptID 64018 0) posn: 1432 306)
		((ScriptID 64018 0) setLoop: 7)
		((ScriptID 64018 0) show:)
		((ScriptID 64018 0) bSwing: 1)
	)
)

(instance poBoogleCrosses of Prop
	(properties
		view 10113
	)
)

(instance soJumpLeftToTree of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 1767 239 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
				((curRoom plane?) sitNSpin: 1298 0 self 1 10 10)
				(ego bMouseDown: 0)
			)
			(2)
			(3
				(ego hide:)
				(LOOKUP_ERROR
					loop: 3
					cel: 0
					posn: 1404 303
					init:
					setCycle: End self
				)
			)
			(4
				(= local0 1)
				(ego setAvoider: LOOKUP_ERROR)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soJumpRightToTree of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 1393 324 self)
			)
			(1
				(theGame handsOff:)
				(ego setMotion: MoveTo 1393 324 self)
			)
			(2
				(ego setHeading: 45 self)
				((curRoom plane?) sitNSpin: 1298 0 self 1 10 10)
				(ego bMouseDown: 0)
			)
			(3)
			(4
				(ego hide:)
				(LOOKUP_ERROR
					loop: 1
					cel: 0
					posn: 1630 322
					init:
					setCycle: End self
				)
			)
			(5
				(= local0 1)
				(ego setAvoider: LOOKUP_ERROR)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soJumpToLeftBank of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom plane?) sitNSpin: 1298 0 self 1 10 10)
				(ego bMouseDown: 0)
			)
			(1
				(LOOKUP_ERROR
					loop: 2
					cel: 13
					posn: 1404 303
					setCycle: End self
				)
				(if (> ((ScriptID 64018 0) x?) 1614)
					((ScriptID 64018 0) hide:)
					(LOOKUP_ERROR
						loop: 1
						cel: 0
						posn: 1822 280
						init:
						setCycle: End LOOKUP_ERROR
					)
				)
			)
			(2
				((curRoom plane?) sitNSpin: 1052 0 self 1 10 10)
			)
			(3
				(= local0 0)
				(ego posn: 1402 308)
				(ego setScale: 0)
				(ego setLoop: 5)
				(ego show:)
				(ego setAvoider: 0)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soJumpToRightBank of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((curRoom plane?) sitNSpin: 1298 0 self 1 10 10)
				(ego bMouseDown: 0)
			)
			(1
				(LOOKUP_ERROR
					loop: 0
					cel: 21
					posn: 1630 322
					setCycle: End self
				)
				(if (< ((ScriptID 64018 0) x?) 1614)
					((ScriptID 64018 0) hide:)
					(LOOKUP_ERROR
						loop: 0
						cel: 0
						posn: 1429 304
						init:
						setCycle: End LOOKUP_ERROR
					)
				)
			)
			(2
				((curRoom plane?) sitNSpin: 1614 0 self 1 10 10)
			)
			(3
				(= local0 2)
				(ego setScaler: Scaler 100 87 302 246)
				(ego posn: 1858 245)
				(ego setLoop: 4)
				(ego show:)
				(ego setAvoider: 0)
				(LOOKUP_ERROR dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(class ApproachFaceHandle of TPScript
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		oMessageList 0
		nTextWidth 0
		createDisplay 0
		setNoScore 0
		unSet 0
		verb 0
		nBendLight 0
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be setNoScore unSet self)
			)
			(1
				(if nBendLight
					(ego oCuee: nBendLight self)
				else
					(self cue:)
				)
			)
			(2
				(self doVerb: verb)
				(self dispose:)
			)
		)
	)
	
	(method (doVerb)
	)
)

(instance oMoonView of ScrollView
	(properties
		view 10100
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 3)
	)
)

(instance oBackView1 of ScrollView
	(properties
		view 10101
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 4)
	)
)

(instance oBackView2 of ScrollView
	(properties
		x 632
		view 10102
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 4)
	)
)

(instance foLeafExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
		(= nsTop 134)
		(= nsBottom 211)
		(= nsLeft 882)
		(= nsRight 928)
	)
	
	(method (doVerb)
		(localproc_02be 890 216 self)
	)
	
	(method (cue)
		(curRoom newRoom: 12000)
	)
)

(instance foGuardHouseExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 5))
		(= onMeCheck
			((Polygon new:)
				init: 3160 312 2880 312 2682 235 2682 0 3160 0
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soExitToGuardHouse of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be 2724 252 self)
			)
			(1
				((curRoom plane?) sitNSpin: 2528 0 self 1 10 10)
				(ego bMouseDown: 0)
			)
			(2
				(theGame handsOff:)
				(curRoom
					setScript:
						((WalkBehindHill new:)
							setNoScore: 2800
							unSet: 400
							priority: 5
							heading: 45
							lastNoun: self
							yourself:
						)
				)
			)
			(3
				(ego
					setScale:
					scaleX: 24
					scaleY: 24
					setPri: 12
					posn: 2800 320
					setMotion: MoveTo 2924 302 self
				)
			)
			(4 (curRoom newRoom: 15800))
		)
	)
)

(instance soWalkInFromGuardHouse of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 2760 360 loop: 2)
				((curRoom plane?) fadeRel: 2528 0)
				(theGame handsOff:)
				(curRoom
					setScript:
						((WalkBehindHill new:)
							setNoScore: 2724
							unSet: 252
							priority: 5
							heading: 180
							lastNoun: self
							yourself:
						)
				)
			)
			(1
				(ego setPri: -1)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foBogExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 1))
		(self setRect: 1883 122 1924 190)
	)
	
	(method (doVerb)
		(localproc_02be 1900 200 self)
	)
	
	(method (cue)
		(curRoom newRoom: 13000)
	)
)

(instance foFahrmanExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 6))
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 2 314 2 0 450 0 450 259 278 312
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soExitToFahrman of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be 392 278 self)
			)
			(1
				(theGame handsOff:)
				(curRoom
					setScript:
						((WalkBehindHill new:)
							setNoScore: 360
							unSet: 360
							priority: 5
							heading: 0
							lastNoun: self
							yourself:
						)
				)
			)
			(2 (curRoom newRoom: 11000))
		)
	)
)

(instance soWalkInFromFahrman of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego posn: 360 360 loop: 2)
				(theGame handsOff:)
				(curRoom
					setScript:
						((WalkBehindHill new:)
							setNoScore: 392
							unSet: 278
							priority: 5
							heading: 180
							lastNoun: self
							yourself:
						)
				)
			)
			(1
				(ego setPri: -1)
				(ego setMotion: MoveTo 420 285 self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foCityExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 2))
		(= nsTop 297)
		(= nsBottom 316)
		(= nsLeft 396)
		(= nsRight 538)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soExitToCity of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be 450 370 self)
			)
			(1
				(theGame handsOff:)
				(ego setMotion: MoveTo 450 370 self)
			)
			(2
				(curRoom newRoom: 16000)
				(self dispose:)
			)
		)
	)
)

(instance voSquareRoot of View
	(properties
		x 2241
		y 276
		view 10120
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 2)
	)
	
	(method (doVerb theVerb)
		(curRoom
			setScript: (LOOKUP_ERROR verb: theVerb nBendLight: self yourself:)
		)
	)
)

(instance soHandleRoot of ApproachFaceHandle
	(properties
		setNoScore 2236
		unSet 252
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 7 1 0 0))
			(2
				(ego setScript: LOOKUP_ERROR)
			)
		)
	)
)

(instance soChopRoot of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_02be 2203 274 self)
			)
			(1 (ego setHeading: 135 self))
			(2
				(ego hide:)
				(LOOKUP_ERROR cel: 0 loop: 0 init: setCycle: CT 18 1 self)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR setCycle: CT 24 1 self)
			)
			(4
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR setCycle: End self)
			)
			(5
				(ego posn: 2181 273)
				(ego setLoop: 6)
				(((ScriptID 64001 0) get: 0) moveTo: -3)
				(ego show:)
				(LOOKUP_ERROR dispose:)
				((ScriptID 64017 0) set: 18)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(= local0 2)
		(ego posn: 2181 273)
		(ego setLoop: 6)
		(((ScriptID 64001 0) get: 0) moveTo: -3)
		(ego show:)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR dispose:)
		((ScriptID 64017 0) set: 18)
		(theGame handsOn:)
		(self dispose:)
	)
	
	(method (sayMessage)
		(= local0 2)
		(ego posn: 2203 274)
		(ego show:)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR dispose:)
		((ScriptID 64017 0) unlockAudio: 18)
		(ego setScript: self)
	)
)

(instance poTorinChops of Prop
	(properties
		x 2203
		y 274
		view 10107
	)
)

(instance soPickUpRoot of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego get: ((ScriptID 64001 0) get: 5))
				((ScriptID 64017 0) set: 5)
				(LOOKUP_ERROR dispose:)
				(self dispose:)
			)
		)
	)
)

(instance voCutRoot of View
	(properties
		x 2308
		y 238
		view 10120
		loop 1
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance foBerryBushes of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 545 177 565 156 587 165 593 182 579 205 571 207 565 183
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 4)
			(messager say: 8 1 6)
		else
			(curRoom
				setScript: (LOOKUP_ERROR nBendLight: self yourself:)
			)
		)
	)
)

(instance soPickUpBerries of ApproachFaceHandle
	(properties
		createDisplay 1
		setNoScore 597
		unSet 261
	)
	
	(method (doVerb)
		(messager say: 8 1 0)
		(ego get: ((ScriptID 64001 0) get: 4))
		((ScriptID 64017 0) set: 4)
	)
)

(instance foLRazorVines1 of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= onMeCheck
			((Polygon new:)
				init: 764 38 880 38 880 223 764 223
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(= local6 942)
		(= local7 263)
		(= local8 110)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foLRazorVines2 of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					909
					221
					936
					201
					946
					159
					927
					157
					909
					129
					893
					92
					914
					52
					913
					32
					938
					1
					971
					12
					981
					31
					976
					53
					998
					66
					999
					103
					979
					121
					973
					152
					978
					177
					964
					223
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(= local6 1048)
		(= local7 262)
		(= local8 110)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foLRazorVines3 of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					1939
					221
					1977
					198
					1973
					182
					1937
					162
					1943
					131
					1940
					98
					1957
					59
					1960
					23
					1986
					22
					1996
					45
					1981
					64
					2033
					93
					2046
					132
					2032
					162
					2028
					204
					1996
					228
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(= local6 1999)
		(= local7 236)
		(= local8 100)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foLRazorVines4 of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					2154
					224
					2148
					170
					2093
					150
					2085
					130
					2130
					107
					2127
					59
					2142
					42
					2143
					5
					2196
					3
					2174
					34
					2217
					70
					2206
					123
					2220
					151
					2188
					183
					2215
					224
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(= local6 2180)
		(= local7 239)
		(= local8 100)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance poTouchRazorVines of Prop
	(properties
		x 880
		y 242
		view 10108
	)
)

(instance soTouchRazorVines of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be local6 local7 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					nCurPosY: local8
					posn: local6 local7
					cel: 0
					init:
					setCycle: End self
				)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(ego show:)
				(messager say: 1 1 0 0 self)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poSlugs of Prop
	(properties
		x 1026
		y 79
		view 10106
		cycleSpeed 0
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 248)
		(self setCycle: LOOKUP_ERROR)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
		(and (== cel 0) ((ScriptID 64017 0) test: 14))
			(if ((ScriptID 64017 0) test: 16)
				(if (not (LOOKUP_ERROR script?))
					(LOOKUP_ERROR setScript: 'LOOKUP_ERROR')
				)
			else
				((ScriptID 64017 0) set: 15)
			)
		)
	)
)

(instance soCatchSlugs of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego setMotion: 0)
				(LOOKUP_ERROR loop: 1 cel: 0 setCycle: End self)
			)
			(1 (ego setHeading: 90 self))
			(2
				(messager say: 10 15 7 2 self)
			)
			(3
				((ScriptID 64017 0) set: 17)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(ego setScript: LOOKUP_ERROR)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oSlugCycler of Cycle
	(properties)
	
	(method (doit &tmp cycleNextCel)
		(if (< (= cycleNextCel (self nextCel:)) 0)
			(= cycleNextCel 0)
		)
		(if (!= cycleNextCel (client cel:))
			(client cel: cycleNextCel)
		)
	)
	
	(method (nextCel &tmp temp0)
		(= temp0 (GetDistance 1036 252 (ego x?) (ego y?)))
		(return (- (- clientLastCel 1) (/ (Max (- temp0 90) 0) 4)))
	)
)

(instance foSlugTree of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 1006 0 1060 247)
		(= x 1036)
		(= y 240)
		(self setVisibleRange: 1 11)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: 'LOOKUP_ERROR')
			)
			(11
				(ego setScript: LOOKUP_ERROR)
			)
			(15
				(ego setScript: LOOKUP_ERROR)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poTorinJumps of Prop
	(properties
		x 991
		y 268
		view 10109
	)
)

(instance soTryToGetSlugs of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be 995 266 self)
			)
			(1 (ego setHeading: 45 self))
			(2
				(theGame handsOff:)
				(ego hide:)
				(LOOKUP_ERROR cel: 0 loop: 0 init: setCycle: End self)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(ego posn: 1013 261 show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance foSlugTrapLoc of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 960 242 1002 264)
		(= x 960)
		(= y 256)
		(self setVisibleRange: 11)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(15
				(ego setScript: LOOKUP_ERROR)
			)
			(11
				(ego setScript: LOOKUP_ERROR)
			)
		)
	)
)

(instance poPutsScum of Prop
	(properties
		x 1003
		y 249
		view 10110
	)
)

(instance soBoogleWaitForScum of TPScript
	(properties
		createDisplay 0
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64018 0)
					bSwing: 0
					setMotion: MoveTo 792 294 self
				)
			)
			(1
				((ScriptID 64018 0) setHeading: 90)
			)
			(2
				((ScriptID 64018 0) bSwing: 1)
				(self dispose:)
			)
		)
	)
)

(instance soPutScum of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(localproc_02be 1003 249 self)
			)
			(1
				(ego setHeading: 215 self)
				((curRoom plane?) sitNSpin: 687 0 self 1 10 10)
				(ego bMouseDown: 0)
				((ScriptID 64018 0) setScript: LOOKUP_ERROR)
			)
			(2)
			(3
				(ego hide:)
				(LOOKUP_ERROR loop: 0 cel: 0 init: setCycle: End self)
			)
			(4
				(ego put: ((ScriptID 64001 0) get: 7) 10100)
				(LOOKUP_ERROR init:)
				((ScriptID 64017 0) set: 14)
				(LOOKUP_ERROR loop: 1 cel: 0 setCycle: End self)
			)
			(5
				(LOOKUP_ERROR cycleSpeed: 7 setCycle: Beg self)
			)
			(6
				(LOOKUP_ERROR loop: 2 cel: 0 setCycle: CT 8 1 self)
			)
			(7
				(LOOKUP_ERROR setCycle: End self)
				(LOOKUP_ERROR cycleSpeed: 3 setCycle: End)
			)
			(8
				(LOOKUP_ERROR dispose:)
				(ego posn: 1189 265 setLoop: 5 show:)
				(LOOKUP_ERROR setCycle: LOOKUP_ERROR)
				(LOOKUP_ERROR setVisibleRange: 15)
				(LOOKUP_ERROR setVisibleRange: 15)
				(LOOKUP_ERROR cue:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
	
	(method (rememberMessage)
		(= local0 0)
		(LOOKUP_ERROR init:)
		(ego put: ((ScriptID 64001 0) get: 7) 10100)
		((ScriptID 64017 0) set: 14)
		(LOOKUP_ERROR dispose:)
		(ego posn: 1189 265 setLoop: 5 show:)
		(LOOKUP_ERROR setCycle: LOOKUP_ERROR)
		(LOOKUP_ERROR setVisibleRange: 15)
		(LOOKUP_ERROR setVisibleRange: 15)
		(LOOKUP_ERROR changeState: 2)
		(theGame handsOn:)
		(self dispose:)
	)
	
	(method (sayMessage)
		(= local0 0)
		(ego posn: 1003 249 setLoop: 5 show:)
		(LOOKUP_ERROR dispose:)
		(LOOKUP_ERROR setCycle: LOOKUP_ERROR)
		(ego get: ((ScriptID 64001 0) get: 7))
		(LOOKUP_ERROR dispose:)
		((ScriptID 64017 0) unlockAudio: 14)
		(ego setScript: self)
	)
)

(instance soPutBoogleBox of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be 870 248 self)
				((curRoom plane?) sitNSpin: 600 0 self 1 10 10)
			)
			(1)
			(2
				(ego oCuee: 'LOOKUP_ERROR' self)
			)
			(3
				(theGame handsOff:)
				(((ScriptID 64001 1) get: 1) moveTo: -3)
				(messager say: 10 15 7 1 self)
			)
			(4
				((ScriptID 64018 0) setTile: 934 248 self)
			)
			(5
				((ScriptID 64018 0) bSwing: 0 setLoop: 4 hide:)
				(LOOKUP_ERROR init:)
				((ScriptID 64017 0) set: 16)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance voMoatScumOnGround of View
	(properties
		approachX 920
		approachY 242
		x 1003
		y 249
		view 10122
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 15)
		(self setPri: 20)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 10 1 0 0))
			(15
				(ego setScript: LOOKUP_ERROR)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance poBoogleBoxOverScum of Prop
	(properties
		x 934
		y 248
		view 10111
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(theGame handsOff:)
		(= loop (= cel 0))
		(self setCycle: End (ScriptID 64020 0))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: LOOKUP_ERROR)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance soBoogleGetsUp of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 64017 0) clear: 16)
				(LOOKUP_ERROR cel: 0 loop: 2 setCycle: End self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				((ScriptID 64018 0) show:)
				(self dispose:)
			)
		)
	)
)

(instance soPickUpBoogle of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_02be 920 242 self)
			)
			(1
				(ego oCuee: LOOKUP_ERROR self)
			)
			(2
				(LOOKUP_ERROR cel: 0 loop: 2 setCycle: End self)
			)
			(3
				(if ((ScriptID 64017 0) test: 17)
					(messager say: 11 1 9 0)
					(ego get: ((ScriptID 64001 0) get: 10))
					((ScriptID 64017 0) set: 13)
					(LOOKUP_ERROR dispose:)
					(LOOKUP_ERROR dispose:)
				)
				((ScriptID 64017 0) clear: 16)
				((ScriptID 64018 0)
					bSwing: 0
					posn: 934 248
					setLoop: 5
					show:
					bSwing: 1
				)
				(LOOKUP_ERROR dispose:)
				(self dispose:)
			)
		)
	)
)

(instance poSnails of Prop
	(properties
		x 1650
		y 102
		view 10115
	)
	
	(method (init)
		(self nCurPosY: 50)
		(super init: &rest)
		(= cycleSpeed 12)
		(self setCycle: RandCycle)
	)
)

(instance foSnailLedge of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 1631 83 1662 118)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soGoToSnails of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== local0 1) (self cue:))
				(if (== local0 2)
					(self setScript: LOOKUP_ERROR self)
				else
					(self setScript: LOOKUP_ERROR self)
				)
			)
			(1
				(LOOKUP_ERROR
					posn: 1404 303
					loop: 4
					cel: 0
					setCycle: End self
				)
			)
			(2 (= cycles 1))
			(3
				(curRoom newRoom: 14000)
				(self dispose:)
			)
		)
	)
)

(instance foRightTreeClimb of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 1613 129 1657 118 1718 192 1728 233 1783 249 1784 275 1611 225
				yourself:
			)
		)
	)
	
	(method (handleEvent)
		(switch local0
			(1
				(self setSpeedFraction: (ScriptID 64006 7))
			)
			(2
				(self setSpeedFraction: (ScriptID 64006 6))
			)
			(0 (self setSpeedFraction: 0))
		)
		(super handleEvent: &rest)
	)
	
	(method (doVerb)
		(switch local0
			(1
				(ego setScript: LOOKUP_ERROR)
			)
			(2
				(ego setScript: LOOKUP_ERROR)
			)
			(0 (Prints LOOKUP_ERROR))
		)
	)
)

(instance foLeftTreeClimb of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 1331 187 1509 91 1608 107 1614 179 1607 214 1446 288 1313 256
				yourself:
			)
		)
	)
	
	(method (handleEvent)
		(switch local0
			(1
				(self setSpeedFraction: (ScriptID 64006 8))
			)
			(0
				(self setSpeedFraction: (ScriptID 64006 5))
			)
			(2 (self setSpeedFraction: 0))
		)
		(super handleEvent: &rest)
	)
	
	(method (doVerb)
		(switch local0
			(1
				(ego setScript: LOOKUP_ERROR)
			)
			(0
				(ego setScript: LOOKUP_ERROR)
			)
			(2 (Prints LOOKUP_ERROR))
		)
	)
)

(instance foCrossTree of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init: 1452 287 1600 223 1805 283 1817 308 1464 314
				yourself:
			)
		)
	)
	
	(method (handleEvent)
		(switch local0
			(1 (self setSpeedFraction: 0))
			(0
				(self setSpeedFraction: (ScriptID 64006 3))
			)
			(2
				(self setSpeedFraction: (ScriptID 64006 4))
			)
		)
		(super handleEvent: &rest)
	)
	
	(method (doVerb)
		(switch local0
			(1 (Prints LOOKUP_ERROR))
			(0
				(ego setScript: LOOKUP_ERROR)
			)
			(2
				(ego setScript: LOOKUP_ERROR)
			)
		)
	)
)

(instance oStayInTree of Code
	(properties)
	
	(method (doit)
		(if (ego mover?) (ego setMotion: 0))
	)
)

(instance oBrook of TPSound
	(properties)
)

(instance oWoodsScrollPlane of TorScrollPlane
	(properties
		priority 20
	)
	
	(method (nSeq)
		(AddPicAt self 10100 0 0)
		(AddPicAt self 10101 632 0)
		(AddPicAt self 10102 1264 0)
		(AddPicAt self 10103 1896 0)
		(AddPicAt self 10104 2528 0)
	)
	
	(method (oText)
		(self nInitPlaneY: LOOKUP_ERROR)
		(self nInitPlaneY: LOOKUP_ERROR)
		(self nInitPlaneY: LOOKUP_ERROR)
		(LOOKUP_ERROR talkerList: 0 0)
		(LOOKUP_ERROR kill: 0 1264)
		(LOOKUP_ERROR kill: 0 1264)
	)
)

(instance roForestScroll of TPRoom
	(properties)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(= plane
			(LOOKUP_ERROR init: 3160 (thePlane doDouble:) yourself:)
		)
		(gOEventHandler scrolled: LOOKUP_ERROR)
		(ego init: oPanner: lCheck: LOOKUP_ERROR)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(LOOKUP_ERROR init:)
		(if (not ((ScriptID 64017 0) test: 13))
			(LOOKUP_ERROR init:)
			(LOOKUP_ERROR init:)
			(LOOKUP_ERROR init:)
		)
		(if
			(and
				((ScriptID 64017 0) test: 18)
				(not ((ScriptID 64017 0) test: 5))
			)
			(LOOKUP_ERROR init:)
		)
		(if
			(and
				((ScriptID 64017 0) test: 14)
				(not ((ScriptID 64017 0) test: 17))
			)
			(LOOKUP_ERROR init:)
			(LOOKUP_ERROR setVisibleRange: 15)
			(LOOKUP_ERROR setVisibleRange: 15)
		)
		(if (not ((ScriptID 64017 0) test: 18))
			(LOOKUP_ERROR init:)
		)
		(if (not ((ScriptID 64017 0) test: 4))
			(LOOKUP_ERROR init:)
		)
		(if (not ((ScriptID 64017 0) test: 6))
			(LOOKUP_ERROR init:)
			(LOOKUP_ERROR init:)
		)
		(theMusic pageSize: 10100)
		(LOOKUP_ERROR minPosn: 0 init: vThumbView: 10106)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						1454
						310
						1447
						293
						1386
						290
						1260
						261
						1291
						246
						1216
						239
						1155
						231
						1063
						255
						974
						252
						901
						221
						867
						221
						843
						244
						696
						244
						472
						259
						396
						283
						398
						311
					yourself:
				)
				((Polygon new:)
					type: 3
					init:
						1888
						303
						1933
						293
						1949
						255
						2143
						243
						2150
						270
						2228
						289
						2430
						247
						2616
						295
						2834
						300
						2692
						240
						2656
						249
						2528
						216
						2270
						199
						2222
						233
						2041
						232
						1927
						225
						1909
						198
						1800
						209
						1774
						213
						1744
						223
						1761
						231
						1796
						237
						1850
						251
						1825
						274
						1847
						282
					yourself:
				)
		)
		(theGame handsOn:)
		(switch prevRoomNum
			(11000
				((ScriptID 64018 0) posn: 460 285)
				(ego setScript: LOOKUP_ERROR)
				(= local0 0)
			)
			(16000
				(theGame handsOff:)
				(ego
					posn: 456 340
					setMotion: MoveTo 456 304 (ScriptID 64020 0)
				)
				((ScriptID 64018 0) posn: 456 300)
				(= local0 0)
			)
			(12000
				(ego posn: 882 227 loop: 2)
				((ScriptID 64018 0) posn: 882 250)
				(plane fadeRel: 632 0)
				(= local0 0)
			)
			(13000
				(ego posn: 1900 200 loop: 2)
				((ScriptID 64018 0) posn: 1910 220)
				(plane fadeRel: 1600 0)
				(= local0 2)
			)
			(15800
				(ego setScript: LOOKUP_ERROR)
				((ScriptID 64018 0) posn: 2680 250)
				(= local0 2)
			)
			(14000
				(ego posn: 1614 216)
				((ScriptID 64018 0) posn: 1858 245)
				(ego hide:)
				(LOOKUP_ERROR
					posn: 1630 322
					loop: 1
					cel: (LOOKUP_ERROR lastCel:)
					init:
				)
				(plane fadeRel: 1298 0)
				(= local0 1)
			)
			(else 
				(ego posn: 420 285)
				((ScriptID 64018 0) posn: 460 285)
				(= local0 0)
			)
		)
		(if (== local0 2)
			(ego setScaler: Scaler 100 87 270 246)
		else
			(ego setScale: 0)
		)
		((ScriptID 64018 0) init: oPanner:)
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit: &rest)
		(= temp1 (Abs (- 1600 (ego x?))))
		(LOOKUP_ERROR cThumbCel: (- 100 (/ temp1 16)))
		(if
			(and
				((ScriptID 64017 0) test: 16)
				(>
					(GetDistance
						(LOOKUP_ERROR x?)
						(LOOKUP_ERROR y?)
						(ego x?)
						(ego y?)
					)
					200
				)
			)
			(ego setScript: LOOKUP_ERROR)
		)
		(if local4
			(plane nInitCursorX: local2 0)
			(++ local5)
			(if (== (plane setMusic?) (plane relVolPercent?))
				(= temp0 (- gameTime theGSel_57))
				(Printf
					LOOKUP_ERROR
					temp0
					local5
					(/ (* local5 60) temp0)
				)
				(= local4 0)
				(plane fadeRel: 0 0)
			)
		)
	)
	
	(method (dispose)
		(gOEventHandler pageUp: LOOKUP_ERROR)
		(super dispose: &rest)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 1) get: 1))
		(ego get: ((ScriptID 64001 0) get: 7))
		((ScriptID 64017 0) set: 27)
		(ego get: ((ScriptID 64001 0) get: 0))
	)
)

(instance oScrollerWalkHandler of Code
	(properties)
	
	(method (doit param1 param2)
		(localproc_02be param1 param2)
	)
)

(instance oTempKeyHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp eventMessage)
		(if (not (& (event type?) $000c))
			(Prints LOOKUP_ERROR)
			(return 0)
		)
		(if (not (& (event type?) evKEYBOARD)) (return 0))
		(if
			(and
				(> (= eventMessage (event message?)) 48)
				(<= eventMessage KEY_9)
			)
			(= local2 (- eventMessage KEY_0))
			(event claimed: 1)
		)
		(if (== eventMessage KEY_CLEAR)
			(ego bMouseDown: 0)
			((curRoom plane?) fadeRel: 0 0)
			(= theGSel_57 gameTime)
			(= local5 0)
			(= local4 1)
			(event claimed: 1)
		)
		(return (event claimed?))
	)
)
