;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13000)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Set)
(use Script)
(use Plane)
(use Print)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	roBog 0
)

(local
	local0
	local1
	local2
	local3
	local4
	theTheSel_16 =  8
	theGSel_57
)
(instance foBranch of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= y (= x 200))
		(= approachX 171)
		(= approachY 271)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						178
						71
						231
						38
						283
						29
						338
						44
						370
						43
						390
						29
						399
						42
						427
						48
						443
						57
						450
						56
						467
						74
						501
						69
						546
						73
						548
						79
						508
						85
						490
						81
						475
						90
						462
						86
						449
						74
						426
						68
						416
						59
						390
						52
						385
						47
						374
						55
						348
						55
						348
						67
						322
						87
						317
						82
						319
						55
						294
						45
						262
						45
						235
						57
						207
						99
						201
						126
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(if local1
			(ego setScript: LOOKUP_ERROR)
		else
			(ego setScript: LOOKUP_ERROR)
		)
	)
)

(instance foGround of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 3
					init: 144 314 213 256 130 238 2 309
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance foRoot of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						230
						173
						266
						179
						295
						161
						338
						151
						357
						174
						426
						213
						428
						236
						377
						241
						351
						282
						312
						250
						294
						262
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
)

(instance soClimbToRoot of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 171 271 self)
			)
			(1
				(theGame handsOff:)
				(ego setHeading: 45 self)
			)
			(2
				(ego hide:)
				(LOOKUP_ERROR
					view: 13000
					loop: 2
					cel: 0
					posn: 167 273
					init:
					setCycle: End self
				)
			)
			(3
				(= local1 1)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				((ScriptID 64899 3) dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soJumpDownFromRoot of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR
					view: 13000
					loop: 4
					cel: 0
					posn: 410 204
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(ego posn: 171 271 oPanner: 1 -5436 5 show:)
				(theGame handsOn:)
				(LOOKUP_ERROR dispose:)
				(if (not ((ScriptID 64017 0) test: 10))
					(poEgoHangs init:)
					(LOOKUP_ERROR init:)
				)
				((ScriptID 64899 3) init:)
				(= local1 0)
				(self dispose:)
			)
		)
	)
)

(instance foBranchWhileHanging of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						178
						71
						231
						38
						283
						29
						338
						44
						370
						43
						390
						29
						399
						42
						427
						48
						443
						57
						450
						56
						467
						74
						501
						69
						546
						73
						548
						79
						508
						85
						490
						81
						475
						90
						462
						86
						449
						74
						426
						68
						416
						59
						390
						52
						385
						47
						374
						55
						348
						55
						348
						67
						322
						87
						317
						82
						319
						55
						294
						45
						262
						45
						235
						57
						207
						99
						201
						126
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(ego setScript: LOOKUP_ERROR)
	)
)

(instance soClimbBranchFromRoot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13000
					posn: 410 204
					loop: 3
					cel: 0
					init:
					setCycle: End self
				)
			)
			(1
				((ScriptID 64018 0) hide:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR dispose:)
				((ScriptID 64899 3) init:)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR
					view: 13001
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soClimbBranch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: LOOKUP_ERROR self)
			)
			(1
				(theGame handsOff:)
				(ego hide:)
				(LOOKUP_ERROR
					posn: 167 273
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(2
				((ScriptID 64018 0) hide:)
				(LOOKUP_ERROR dispose:)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR
					view: 13001
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poClimbsTree of Prop
	(properties
		view 13000
	)
)

(instance foBog of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= x 384)
		(= y 252)
		(= approachX 188)
		(= approachY 268)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 364 262 631 266 631 313 352 315 366 300 356 279
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(LOOKUP_ERROR doVerb: &rest)
	)
)

(instance soGoForBog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego nSaveTime: LOOKUP_ERROR self)
			)
			(1
				(Prints LOOKUP_ERROR)
				(Prints LOOKUP_ERROR)
				(self dispose:)
			)
		)
	)
)

(instance foClimbDown of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setRect: 0 252 200 316)
		(self setSpeedFraction: (ScriptID 64006 8))
	)
	
	(method (doVerb)
		(curRoom setScript: 'LOOKUP_ERROR')
	)
)

(instance soClimbBackDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self setScript: LOOKUP_ERROR self)
			)
			(1
				(LOOKUP_ERROR
					view: 13001
					loop: 1
					cel: 0
					posn: 342 170
					setCycle: End self
				)
			)
			(2
				(curRoom arrowDown: LOOKUP_ERROR)
				((ScriptID 64018 0) show:)
				(LOOKUP_ERROR
					posn: 167 273
					loop: 1
					cel: 0
					init:
					setCycle: End self
				)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(ego posn: 169 272 oPanner: 1 -5436 5 show:)
				(self dispose:)
			)
		)
	)
)

(instance soTieLeg of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((ScriptID 64017 0) test: 20)
					(MonoOut LOOKUP_ERROR)
					(self dispose:)
					(return)
				)
				(if (not ((ScriptID 64017 0) test: 19))
					(self setScript: LOOKUP_ERROR self)
				else
					(self setScript: LOOKUP_ERROR self)
				)
			)
			(1
				((ScriptID 64017 0) set: 20)
				(self dispose:)
			)
		)
	)
)

(instance soTieLegFromZero of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13002
					loop: 0
					cel: 0
					posn: 342 170
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR loop: 1 cel: 0 setCycle: End self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTieLegFromBranch of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local0
					(LOOKUP_ERROR
						view: 13002
						loop: 7
						cel: 0
						posn: 342 170
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(1
				(if local0
					(LOOKUP_ERROR loop: 2 cel: 16 setCycle: End self)
				else
					(LOOKUP_ERROR loop: 2 cel: 0 setCycle: End self)
				)
				(= local0 0)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTieBranch of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((ScriptID 64017 0) test: 19)
					(MonoOut LOOKUP_ERROR)
					(self dispose:)
					(return)
				)
				(if (not ((ScriptID 64017 0) test: 20))
					(self setScript: LOOKUP_ERROR self)
				else
					(self setScript: LOOKUP_ERROR self)
				)
			)
			(1
				((ScriptID 64017 0) set: 19)
				(self dispose:)
			)
		)
	)
)

(instance soTieBranchFromZero of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13002
					loop: 0
					cel: 0
					posn: 342 170
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR loop: 3 cel: 0 setCycle: End self)
				(= local0 1)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTieBranchFromLeg of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13002
					loop: 4
					cel: 0
					setCycle: End self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soUntieLeg of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((ScriptID 64017 0) test: 19)
					(self setScript: LOOKUP_ERROR self)
				else
					(self setScript: LOOKUP_ERROR self)
				)
			)
			(1
				((ScriptID 64017 0) clear: 20)
				(self dispose:)
			)
		)
	)
)

(instance soUntieLegLeaveBranch of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13002
					loop: 2
					cel: (LOOKUP_ERROR lastCel:)
					setCycle: Beg self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soUntieLegAndPutAway of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13002
					loop: 6
					cel: 0
					setCycle: End self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soUntieBranch of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((ScriptID 64017 0) test: 20)
					(self setScript: LOOKUP_ERROR self)
				else
					(self setScript: LOOKUP_ERROR self)
				)
			)
			(1
				((ScriptID 64017 0) clear: 19)
				(self dispose:)
			)
		)
	)
)

(instance soUntieBranchAndPutAway of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if local0
					(self setScript: LOOKUP_ERROR self)
				else
					(self cue:)
				)
			)
			(1
				(LOOKUP_ERROR
					view: 13002
					loop: 5
					cel: 0
					setCycle: End self
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soPullUpRope of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR
					view: 13002
					loop: 7
					cel: 0
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR loop: 2 cel: 16 setCycle: Beg self)
			)
			(2
				(= local0 0)
				(self dispose:)
			)
		)
	)
)

(instance soUntieBranchLeaveLeg of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13002
					loop: 4
					cel: (LOOKUP_ERROR lastCel:)
					setCycle: Beg self
				)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soUntieSelf of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if ((ScriptID 64017 0) test: 19)
					(self setScript: LOOKUP_ERROR self)
				else
					(self cue:)
				)
			)
			(1
				(if ((ScriptID 64017 0) test: 20)
					(self setScript: LOOKUP_ERROR self)
				else
					(self cue:)
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance foBranchCU of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					190
					184
					231
					202
					245
					195
					267
					168
					288
					158
					331
					170
					362
					159
					377
					164
					422
					146
					413
					154
					379
					172
					327
					182
					294
					177
					245
					216
					239
					241
					220
					253
					208
					247
					206
					224
					178
					219
				yourself:
			)
		)
		(self setVisibleRange: 4)
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 19)
			(curRoom setScript: LOOKUP_ERROR)
		else
			(curRoom setScript: LOOKUP_ERROR)
		)
	)
)

(instance foBogBelow of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(= onMeCheck
			((Set new:)
				add:
					((Polygon new:)
						type: 0
						init:
							62
							194
							34
							82
							71
							42
							121
							82
							182
							78
							406
							140
							315
							152
							267
							154
							233
							188
							183
							166
						yourself:
					)
					((Polygon new:)
						type: 0
						init:
							426
							155
							371
							191
							292
							194
							244
							248
							212
							266
							192
							233
							167
							227
							109
							272
							112
							302
							120
							310
							626
							311
							626
							243
						yourself:
					)
			)
		)
		(self setVisibleRange: 1)
	)
	
	(method (doVerb)
		(cond 
			(((ScriptID 64017 0) test: 20)
				(if ((ScriptID 64017 0) test: 19)
					(curRoom setScript: LOOKUP_ERROR)
				else
					(curRoom setScript: LOOKUP_ERROR)
				)
			)
			(((ScriptID 64017 0) test: 19)
				(if local0
					(curRoom setScript: LOOKUP_ERROR)
				else
					(curRoom setScript: LOOKUP_ERROR)
				)
			)
			(else (curRoom setScript: LOOKUP_ERROR))
		)
	)
)

(instance soBungieIntoBog of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13004
					loop: 0
					cel: 0
					posn: 342 170
					setCycle: End self
				)
			)
			(1
				(curRoom arrowDown: LOOKUP_ERROR)
				(curRoom initThumb: LOOKUP_ERROR)
				(LOOKUP_ERROR
					view: 13004
					loop: 1
					cel: 0
					posn: 432 44
					init:
					setCycle: End self
				)
			)
			(2
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR
					view: 13005
					loop: 0
					cel: 8
					posn: 432 44
					init:
				)
				(LOOKUP_ERROR init:)
				(ego get: ((ScriptID 64001 0) get: 11))
				((ScriptID 64017 0) set: 10)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				((ScriptID 64017 0) set: 21)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soDiveIntoBog of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					(((ScriptID 64017 0) test: 20) (= temp0 0))
					(((ScriptID 64017 0) test: 19) (= temp0 1))
					(else (= temp0 2))
				)
				(LOOKUP_ERROR hide:)
				(LOOKUP_ERROR
					view: 13006
					loop: temp0
					cel: 0
					posn: 342 170
					init:
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13000)
				(curRoom drawPic: 13000)
				(LOOKUP_ERROR cel: 0 init:)
				(if ((ScriptID 64017 0) test: 20)
					(= temp0 3)
				else
					(= temp0 4)
				)
				(LOOKUP_ERROR loop: temp0 init:)
				(= ticks 60)
			)
			(2
				(LOOKUP_ERROR setCycle: End self)
			)
			(3
				((ScriptID 64019 0) show: 0 42 8)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13001)
				(curRoom drawPic: 13001)
				(LOOKUP_ERROR show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soClimbDownRope of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR
					view: 13003
					loop: 0
					cel: 0
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR hide:)
				(curRoom picture: 13000)
				(curRoom drawPic: 13000)
				(LOOKUP_ERROR cel: 0 init:)
				(LOOKUP_ERROR loop: 1 cel: 0 init: setCycle: End self)
			)
			(2
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR init:)
				(theGame handsOn:)
			)
		)
	)
)

(instance soClimbRope of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(LOOKUP_ERROR loop: 2 cel: 0 setCycle: End self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13001)
				(curRoom drawPic: 13001)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR show: loop: 3 cel: 0 setCycle: End self)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance poEgoHangs of Prop
	(properties
		x 461
		y 75
		view 13003
	)
)

(instance poBoogleHoldsCard of Prop
	(properties
		x 345
		y 167
		view 13005
		loop 4
	)
)

(instance voTorinInMuck of View
	(properties
		x 463
		y 275
		view 13006
		loop 3
	)
)

(instance poTorin of Prop
	(properties)
)

(instance poEgoOnBranch of Prop
	(properties
		x 342
		y 170
		view 13001
	)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 4)
		(= signal (| signal $1000))
	)
	
	(method (doVerb)
		(if ((ScriptID 64017 0) test: 20)
			(self setScript: LOOKUP_ERROR)
		else
			(self setScript: LOOKUP_ERROR)
		)
	)
)

(instance oBranchPlane of Plane
	(properties
		picture 13001
		priority 20
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
		(LOOKUP_ERROR init: LOOKUP_ERROR)
		(LOOKUP_ERROR init: LOOKUP_ERROR)
		(LOOKUP_ERROR init: LOOKUP_ERROR)
	)
)

(instance poEgoBungies of Prop
	(properties)
)

(class poEgoHangingFromBranch of Prop
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
		x 432
		y 44
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
		view 13005
		loop 0
		cel 8
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
		computeTarget 1
	)
	
	(method (doit &tmp temp0 temp1 temp2 theCel)
		(if computeTarget
			(if (and local4 (> gameTime theGSel_57))
				(= theTheSel_16 (+ theTheSel_16 local4))
				(if (> (= temp0 (Abs (- theTheSel_16 8))) local3)
					(= local4 (- 0 local4))
					(= temp0 local3)
					(if (> theTheSel_16 8)
						(= theTheSel_16 (+ 8 local3))
					else
						(= theTheSel_16 (- 8 local3))
					)
				)
				(= temp2 (- 9 local3))
				(= theGSel_57 (+ gameTime (* (Max 1 temp0) temp2)))
			)
			(if (!= (= theCel theTheSel_16) cel) (= cel theCel))
		)
		(super doit: &rest)
	)
)

(instance voBigBag of View
	(properties
		x 368
		y 280
		view 13008
	)
)

(instance voLittleBag of View
	(properties
		x 455
		y 290
		view 13008
		loop 1
	)
)

(instance foSwingRight of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 447 65 563 43 543 79 576 156 586 196 480 224 457 148
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(switch local4
			(0
				(= local4 1)
				(= local3 1)
				(= theGSel_57 gameTime)
				(= theTheSel_16 8)
				(LOOKUP_ERROR dispose:)
			)
			(1
				(if (>= local3 8)
					(= local3 8)
				else
					(= local3 (+ local3 1))
				)
			)
			(-1
				(if (<= local3 1)
					(= local3 0)
					(= local4 0)
					(= theTheSel_16 8)
					(LOOKUP_ERROR init: LOOKUP_ERROR)
				else
					(= local3 (- local3 1))
				)
			)
		)
	)
)

(instance foSwingLeft of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 424 68 417 128 385 231 329 227 319 194 339 151 346 98
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(switch local4
			(0
				(= local4 -1)
				(= local3 1)
				(= theGSel_57 gameTime)
				(= theTheSel_16 8)
				(LOOKUP_ERROR dispose:)
			)
			(1
				(if (<= local3 1)
					(= local3 0)
					(= local4 0)
					(= theTheSel_16 8)
					(LOOKUP_ERROR init: LOOKUP_ERROR)
				else
					(= local3 (- local3 1))
				)
			)
			(-1
				(if (>= local3 8)
					(= local3 8)
				else
					(= local3 (+ local3 1))
				)
			)
		)
	)
)

(instance foBogInScreen3 of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(= onMeCheck
			((Polygon new:)
				type: 0
				init:
					2
					280
					26
					284
					123
					256
					208
					257
					318
					239
					387
					238
					407
					247
					438
					236
					466
					238
					605
					200
					628
					203
					630
					310
					515
					314
					255
					313
					2
					314
				yourself:
			)
		)
	)
	
	(method (doVerb)
		(if (== local3 0)
			(curRoom setScript: LOOKUP_ERROR)
		else
			(curRoom setScript: LOOKUP_ERROR)
		)
	)
)

(instance foBranchOverhead of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						247
						65
						293
						49
						358
						61
						385
						37
						407
						49
						458
						45
						521
						26
						523
						36
						436
						60
						405
						60
						379
						61
						367
						76
						362
						87
						346
						88
						332
						73
						293
						67
						250
						93
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(if (== local3 0)
			(curRoom setScript: LOOKUP_ERROR)
		else
			(curRoom setScript: LOOKUP_ERROR)
		)
	)
)

(instance poBoogleGetsBag of Prop
	(properties
		x 406
		y 203
		view 13007
	)
)

(instance foRope of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(if (== local2 1) (self setVisibleRange: 4))
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 427 63 439 63 442 130 430 131
					yourself:
				)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: LOOKUP_ERROR)
			)
			(1
				(curRoom setScript: LOOKUP_ERROR)
			)
		)
	)
	
	(method (setSpeedDirect)
		(return
			(if (== local3 0)
				(return (super setSpeedDirect: &rest))
			else
				(return 0)
			)
		)
	)
)

(instance foCurlyBranch of Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(self setVisibleRange: 1)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						557
						89
						583
						58
						605
						86
						611
						108
						630
						86
						630
						125
						621
						143
						592
						151
						575
						138
						569
						106
					yourself:
				)
		)
	)
	
	(method (doVerb)
		(curRoom setScript: LOOKUP_ERROR)
	)
	
	(method (setSpeedDirect param1)
		(return
			(if (>= theTheSel_16 15)
				(return (super setSpeedDirect: param1))
			else
				(return 0)
			)
		)
	)
)

(instance soUntieWhileSwinging of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poEgoHangingFromBranch hide:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13000)
				(curRoom drawPic: 13000)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR cel: 0 init:)
				(LOOKUP_ERROR
					view: 13005
					loop: 3
					cel: 0
					posn: 469 63
					init:
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR setCycle: End self)
			)
			(2
				((ScriptID 64019 0) show: 0 42 7)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13002)
				(curRoom drawPic: 13002)
				(LOOKUP_ERROR init:)
				(poEgoHangingFromBranch show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soUntieWhileStationary of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poEgoHangingFromBranch hide:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13000)
				(curRoom drawPic: 13000)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR cel: 0 init:)
				(LOOKUP_ERROR
					view: 13004
					loop: 3
					cel: 0
					posn: 469 63
					init:
					setCycle: End self
				)
			)
			(1
				((ScriptID 64019 0) show: 0 42 10)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13002)
				(curRoom drawPic: 13002)
				(LOOKUP_ERROR init:)
				(poEgoHangingFromBranch show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soTryToClimbRope of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poEgoHangingFromBranch hide:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13000)
				(curRoom drawPic: 13000)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR cel: 0 init:)
				(LOOKUP_ERROR
					view: 13004
					loop: 2
					cel: 0
					posn: 469 63
					init:
					setCycle: End self
				)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(curRoom picture: 13002)
				(curRoom drawPic: 13002)
				(LOOKUP_ERROR init:)
				(poEgoHangingFromBranch show:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBoogleGetBag of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR cel: 0 init: setCycle: End self)
			)
			(1
				(LOOKUP_ERROR dispose:)
				(self dispose:)
			)
		)
	)
)

(instance soGrabBranch of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(poEgoHangingFromBranch
					computeTarget: 0
					setCycle: End self
				)
			)
			(1
				(poEgoHangingFromBranch loop: 1 cel: 0 setCycle: End self)
			)
			(2
				(poEgoHangingFromBranch dispose:)
				(curRoom arrowDown: 'LOOKUP_ERROR')
				(LOOKUP_ERROR cel: 0 init:)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR
					view: 13005
					loop: 2
					cel: 0
					posn: 590 175
					init:
					setCycle: End self
				)
			)
			(3
				(LOOKUP_ERROR dispose:)
				(ego put: ((ScriptID 64001 0) get: 1))
				(self setScript: LOOKUP_ERROR self)
			)
			(4
				(LOOKUP_ERROR
					view: 13000
					loop: 4
					cel: 0
					posn: 410 204
					init:
				)
				(= local1 1)
				(LOOKUP_ERROR init:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				(LOOKUP_ERROR dispose:)
				((ScriptID 64899 3) dispose:)
				((ScriptID 64017 0) set: 22)
				((ScriptID 64017 0) clear: 21)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oDanglePlane of Plane
	(properties
		picture 13002
		priority 20
	)
	
	(method (init &tmp temp0)
		(super
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
	)
)

(instance roBog of TPRoom
	(properties
		picture 13000
		south 10100
	)
	
	(method (init)
		(super init: &rest)
		(ego init: oPanner: posn: 76 295 loop: 3 nCurPosY: 80)
		((ScriptID 64018 0)
			posn: 72 290
			init:
			oPanner:
			nCurPosY: 80
		)
		(if (not ((ScriptID 64017 0) test: 10))
			(LOOKUP_ERROR init:)
		)
		(LOOKUP_ERROR init:)
		((ScriptID 64899 3) init:)
		(LOOKUP_ERROR init:)
		(theMusic pageSize: 13000)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init: 144 314 213 256 130 238 2 309
					yourself:
				)
		)
		(theGame handsOn:)
	)
	
	(method (intoPouch)
		(ego get: ((ScriptID 64001 0) get: 1))
	)
)
