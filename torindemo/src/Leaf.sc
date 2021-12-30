;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50400)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use TPSound)
(use ExitFeature)
(use Plane)
(use Talker)
(use Scaler)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)

(public
	proc50400_0 0
	proc50400_1 1
)

(procedure (proc50400_0)
	(super init: &rest)
	(self setVisibleRange: 1)
	(= PROPERTY-ACCESS-IN-NON-METHOD
		(| PROPERTY-ACCESS-IN-NON-METHOD $1000)
	)
)

(procedure (proc50400_1)
	(asm
		bnot    
		pushi    16
		pushi    1
		pushi    0
		_line_   117
		pushi    331
		pushi    2
		pushi    375
		pushi    232
		_line_   118
		pushi    142
		pushi    0
		_line_   119
		pushi    228
		pushi    2
		class    End
		push    
		pushSelf
		lofsa    poTorin
		send     38
		jmp      code_0533
		dup     
		_line_   122
		ldi      3
		eq?     
		bnt      code_0533
		_line_   123
		pushi    #initThumb
		pushi    1
		lofsa    oWormCUPlane
		push    
		lag      curRoom
		send     6
		_line_   124
		pushi    #dispose
		pushi    0
		self     4
code_0533:
		toss    
		_line_   127
		ret     
	)
)

(instance foSlopeExit of ExitFeature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setSpeedFraction: (ScriptID 64006 4))
		(self setRect: 0 0 15 316)
	)
	
	(method (doVerb)
		(ego setMotion: PolyPath 0 300 self)
	)
	
	(method (cue)
		(curRoom newRoom: -14936)
	)
)

(instance poMrsPlant of Prop
	(properties
		x 312
		y 190
		view -15133
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soGoToPlantCU)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soGoToPlantCU of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 375 232 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(curRoom initThumb: oPlantCUPlane)
			)
		)
	)
)

(instance foWormPlants of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 303 192 383 219)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soGoToWormCU)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soGoToWormCU of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 375 232 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 315 self)
			)
			(2
				(ego hide:)
				(poTorin
					view: -15133
					loop: 6
					cel: 0
					posn: 375 232
					init:
					setCycle: End self
				)
			)
			(3
				(curRoom initThumb: oWormCUPlane)
				(self dispose:)
			)
		)
	)
)

(instance soExitWormCU of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom arrowDown: oWormCUPlane)
				(poTorin
					view: -15133
					loop: 6
					cel: 11
					posn: 375 232
					setCycle: Beg self
				)
			)
			(1
				(poTorin dispose:)
				(ego show:)
				(self dispose:)
			)
		)
	)
)

(instance foDawburrs of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 28 155 128 232)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(ego setScript: soTakeDawburr)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance soTakeDawburr of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 78 232 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 0 self)
			)
			(2
				(theSound lThumbLoop: -15032)
				(self cue:)
			)
			(3
				(ego get: ((ScriptID 64001 0) get: 61))
				((ScriptID 64017 0) set: 126)
				(foDawburrs dispose:)
				(theGame handsOn:)
			)
		)
	)
)

(instance poTorin of Prop
	(properties)
)

(instance poBoogle of Prop
	(properties)
)

(instance poCop of Prop
	(properties)
)

(instance soEnterFirstTime of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poTorin
					view: -15135
					loop: 0
					cel: 0
					posn: 232 197
					init:
					setCycle: End self
				)
			)
			(1
				(theSound lThumbLoop: -15135)
				(poTorin loop: 1 cel: 0 posn: 239 208 setCycle: End self)
			)
			(2
				(poTorin loop: 2 cel: 0 setCycle: End self)
			)
			(3
				(theSound lThumbLoop: -15134 self)
			)
			(4
				(theSound lThumbLoop: -15133)
				(poTorin loop: 3 cel: 0 setCycle: End self)
			)
			(5 (messager say: 0 0 9 1 self))
			(6
				(poTorin
					view: -15134
					loop: 0
					cel: 0
					posn: 228 246
					setCycle: End self
				)
			)
			(7
				(poBoogle
					view: -15134
					loop: 2
					cel: 0
					posn: 232 248
					init:
					setCycle: End
				)
				(poTorin loop: 1 cel: 0 posn: 276 241 setCycle: End self)
			)
			(8
				(poCop
					view: -15134
					loop: 3
					cel: 0
					posn: 317 240
					init:
					setCycle: End self
				)
			)
			(9
				(poTorin loop: 4 cel: 0 setCycle: End self)
				(poCop loop: 5 cel: 0 setCycle: End self)
			)
			(10)
			(11
				(messager sayRange: 0 0 9 2 8 self)
			)
			(12
				(poTorin dispose:)
				(poCop loop: 6 cel: 0 setCycle: End self)
			)
			(13
				(poCop dispose:)
				(curRoom newRoom: -15036)
			)
		)
	)
)

(instance soEnterSecondTime of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poTorin
					view: -15133
					loop: 1
					cel: 0
					posn: 301 241
					init:
					setCycle: End self
				)
			)
			(1
				(poTorin loop: 2 cel: 0 setCycle: End self)
			)
			(2
				(poTorin dispose:)
				(ego
					posn: 301 241
					init:
					oPanner:
					setLoop: 5
					setScaler: Scaler 100 78 312 231
				)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poPlantCU of Prop
	(properties
		x 304
		y 314
		priority 50
		fixPriority 1
		view -15133
		loop 7
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if ((ScriptID 64017 0) test: 109)
					(messager say: 1 1 8 0)
				else
					(switch global231
						(0
							(messager say: 1 1 3 0)
							(++ global231)
						)
						(1
							(messager say: 1 1 4 0)
							(++ global231)
						)
						(2
							(messager say: 1 1 5 0)
							(++ global231)
						)
						(3
							(messager say: 1 1 6 0)
							(++ global231)
						)
						(4 (messager say: 1 1 7 0))
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance voMrsPlantFace of View
	(properties
		x 304
		y 314
		priority 60
		fixPriority 1
		view -15133
		loop 8
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(poPlantCU doVerb: &rest)
	)
)

(instance toMrsPlant of Talker
	(properties
		x 304
		y 314
		view -15133
		loop 8
		priority 70
	)
	
	(method (init)
		(voMrsPlantFace hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voMrsPlantFace show:)
		(super dispose: &rest)
	)
)

(instance foPlantCUExit of CUExitFeature
	(properties)
	
	(method (doVerb)
		(curRoom arrowDown: oPlantCUPlane)
	)
)

(instance oPlantCUPlane of Plane
	(properties
		picture -15135
	)
	
	(method (init)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(theGame handsOff:)
		(poPlantCU init:)
		(voMrsPlantFace init:)
		(foPlantCUExit init:)
		(theGame handsOn:)
		(poPlantCU doVerb: 1)
	)
)

(instance foWormCUExit of CUExitFeature
	(properties)
	
	(method (doVerb)
		(curRoom setScript: soExitWormCU)
	)
)

(class Leaf of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1 53)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1 (messager say: 6 1 0 0))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foLeaf1 of Leaf
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						62
						273
						104
						265
						161
						234
						190
						226
						231
						236
						273
						258
						283
						272
						238
						286
						199
						301
						140
						301
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(53
				(voSap1 init:)
				((ScriptID 64017 0) set: 130)
				(poWorm1 BAD_SELECTOR:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foLeaf2 of Leaf
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 22 84 90 59 138 50 194 77 210 101 174 101 120 118 75 113
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(53
				(voSap2 init:)
				((ScriptID 64017 0) set: 131)
				(poWorm2 BAD_SELECTOR:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foLeaf3 of Leaf
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init: 315 71 375 33 412 37 463 68 509 70 462 99 411 108 364 100 338 77
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(53
				(voSap3 init:)
				((ScriptID 64017 0) set: 132)
				(poWorm3 BAD_SELECTOR:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance foLeaf4 of Leaf
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			onMeCheck:
				((Polygon new:)
					type: 0
					init:
						410
						201
						446
						154
						480
						142
						567
						172
						600
						173
						626
						158
						597
						204
						549
						228
						500
						229
						458
						210
						426
						203
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(53
				(voSap4 init:)
				((ScriptID 64017 0) set: 133)
				(poWorm4 BAD_SELECTOR:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(class Sap of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -15132
		loop 6
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
	)
)

(instance voSap1 of Sap
	(properties
		x 185
		y 280
	)
)

(instance voSap2 of Sap
	(properties
		x 117
		y 97
	)
)

(instance voSap3 of Sap
	(properties
		x 405
		y 86
		loop 7
	)
)

(instance voSap4 of Sap
	(properties
		x 507
		y 204
		loop 7
	)
)

(class Worm of Prop
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 500
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -15132
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		BAD_SELECTOR 0
		BAD_SELECTOR 0
		BAD_SELECTOR 2
		BAD_SELECTOR 4
		BAD_SELECTOR 8
	)
	
	(method (init)
		(super init: &rest)
		(= loop BAD_SELECTOR)
		(if BAD_SELECTOR (self BAD_SELECTOR:))
		(= cycleSpeed 6)
		(self setCycle: Fwd)
		(= signal (| signal $1000))
	)
	
	(method (doit &tmp temp0 temp1)
		(super doit: &rest)
		(= temp0 (- mouseX (plane left:)))
		(= temp1 (- mouseY (plane top?)))
		(cond 
			(
				(and
					(< (- nsLeft 50) temp0)
					(< temp0 (+ nsRight 50))
					(< (- nsTop 50) temp1)
					(< temp1 (+ nsBottom 50))
				)
				(if
				(and (!= loop BAD_SELECTOR) (!= loop BAD_SELECTOR))
					(= loop BAD_SELECTOR)
					(= cel 0)
					(= cycleSpeed 2)
					(self setCycle: End self)
				)
			)
			((== loop BAD_SELECTOR)
				(self show:)
				(= loop BAD_SELECTOR)
				(= cel 0)
				(= cycleSpeed 2)
				(self setCycle: End self)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if BAD_SELECTOR
					(ego get: ((ScriptID 64001 0) get: 44))
					((ScriptID 64017 0) set: 109)
					(messager say: 6 1 2 0)
					(ego setScript: soExitWormCU)
					(foWormPlants dispose:)
				else
					(messager say: 6 1 0 0)
				)
			)
		)
	)
	
	(method (cue)
		(if (== loop BAD_SELECTOR) (self hide:))
		(if (== loop BAD_SELECTOR)
			(= loop BAD_SELECTOR)
			(if BAD_SELECTOR (self BAD_SELECTOR:))
			(= cel 0)
			(= cycleSpeed 6)
			(self setCycle: Fwd)
		)
	)
	
	(method (BAD SELECTOR)
		(= BAD_SELECTOR 1)
		(self setVisibleRange: 1)
		(if (== loop BAD_SELECTOR)
			(= loop BAD_SELECTOR)
			(oWormSquelch init: vThumbView: -15130)
		)
	)
)

(instance poWorm1 of Worm
	(properties
		x 185
		y 280
	)
	
	(method (init param1)
		(asm
			_line_   768
			_file_   {filename}
			_line_   769
			pushi    #test
			pushi    1
			pushi    130
			pushi    2
			pushi    64017
			pushi    0
			callk    ScriptID,  4
			send     6
			bnt      code_1549
			_line_   770
			ldi      1
			aTop     BAD_SELECTOR
			_line_   771
			pushi    #init
			pushi    0
			lofsa    voSap1
			send     4
code_1549:
			_line_   773
			pushi    142
			pushi    0
			&rest    param1
			super    ,  32256
			mul     
			add     
			ret     
		)
	)
)

(instance poWorm2 of Worm
	(properties
		x 117
		y 97
	)
	
	(method (init param1)
		(asm
			shl     
			add     
			_file_   {filename}
			_line_   783
			pushi    #test
			pushi    1
			pushi    131
			pushi    2
			pushi    64017
			pushi    0
			callk    ScriptID,  4
			send     6
			bnt      code_1593
			_line_   784
			ldi      1
			aTop     BAD_SELECTOR
			_line_   785
			pushi    #init
			pushi    0
			lofsa    voSap2
			send     4
code_1593:
			_line_   787
			pushi    142
			pushi    0
			&rest    param1
			super    ,  32256
			or      
			add     
			ret     
		)
	)
)

(instance poWorm3 of Worm
	(properties
		x 405
		y 86
		BAD_SELECTOR 1
		BAD_SELECTOR 3
		BAD_SELECTOR 5
		BAD_SELECTOR 9
	)
	
	(method (init param1)
		(asm
			add     
			_file_   {filename}
			_line_   801
			pushi    #test
			pushi    1
			pushi    132
			pushi    2
			pushi    64017
			pushi    0
			callk    ScriptID,  4
			send     6
			bnt      code_15dd
			_line_   802
			ldi      1
			aTop     BAD_SELECTOR
			_line_   803
			pushi    #init
			pushi    0
			lofsa    voSap3
			send     4
code_15dd:
			_line_   805
			pushi    142
			pushi    0
			&rest    param1
			super    ,  32256
			ugt?    
			add     
			ret     
		)
	)
)

(instance poWorm4 of Worm
	(properties
		x 507
		y 204
		BAD_SELECTOR 1
		BAD_SELECTOR 3
		BAD_SELECTOR 5
		BAD_SELECTOR 9
	)
	
	(method (init)
		(super init: &rest)
		18435
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(theGame handsOff:)
		(foWormCUExit init:)
		(foLeaf1 init:)
		(poWorm1 init:)
		(foLeaf2 init:)
		(poWorm2 init:)
		(foLeaf3 init:)
		(poWorm3 init:)
		(foLeaf4 init:)
		(poWorm4 init:)
		(oWormMunch init: vThumbView: -15131)
		(FrameOut)
		(theGame handsOn:)
	)
)

(instance oWormMunch of TPSound
	(properties)
)

(instance oWormSquelch of TPSound
	(properties)
)

(instance oWormCUPlane of Plane
	(properties
		picture -12236
	)
	
	(method (init)
		48
		12336
		(~ $baad)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(theGame handsOff:)
		(foWormCUExit init:)
		(foLeaf1 init:)
		(poWorm1 init:)
		(foLeaf2 init:)
		(poWorm2 init:)
		(foLeaf3 init:)
		(poWorm3 init:)
		(foLeaf4 init:)
		(poWorm4 init:)
		(oWormMunch init: vThumbView: -15131)
		(FrameOut)
		(theGame handsOn:)
	)
	
	(method (dispose)
		48
		12336
		(~ $baad)
		(oWormMunch vThumbView: 0)
		(oWormSquelch vThumbView: 0)
		(super dispose: &rest)
	)
)

(instance roInPlanter of TPRoom
	(properties
		picture -15136
	)
	
	(method (init)
		48
		12336
		(~ $baad)
		(super init: &rest)
		(theMusic pageSize: -15136)
		(if
		(and (!= prevRoomNum -15236) (!= prevRoomNum -15036))
			((ScriptID 64017 0) set: 128)
			((ScriptID 64017 0) set: 129)
		)
		(if (== prevRoomNum -15236)
			((ScriptID 64017 0) clear: 128)
			((ScriptID 64017 0) clear: 129)
		)
		(if (== prevRoomNum -15036)
			((ScriptID 64017 0) set: 128)
			((ScriptID 64017 0) clear: 129)
		)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 4 257 4 310 465 311 545 247 542 195 440 216 292 240
					yourself:
				)
		)
		(poMrsPlant init:)
		(if (not ((ScriptID 64017 0) test: 126))
			(foDawburrs init:)
		)
		(if (not ((ScriptID 64017 0) test: 109))
			(foWormPlants init:)
		)
		(foSlopeExit init:)
		(if (not ((ScriptID 64017 0) test: 128))
			((ScriptID 64017 0) set: 128)
			(curRoom setScript: soEnterFirstTime)
			(return)
		)
		(if (not ((ScriptID 64017 0) test: 129))
			((ScriptID 64017 0) set: 129)
			(curRoom setScript: soEnterSecondTime)
			(return)
		)
		(ego init: oPanner: setScaler: Scaler 100 78 312 231)
		(theGame handsOn:)
		(switch prevRoomNum
			(-14936 (ego posn: 30 300))
			(else  (ego posn: 316 300))
		)
	)
	
	(method (setWander)
		48
		12336
		(~ $baad)
	)
	
	(method (intoPouch)
		48
		12336
		(~ $baad)
		(ego get: ((ScriptID 64001 0) get: 62))
	)
)
