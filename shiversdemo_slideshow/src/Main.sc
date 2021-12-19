;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include sci.sh)
(use Procs)
(use ShiversSound)
(use Movie)
(use DText)
(use Plane)
(use String)
(use Feature)
(use Ego)
(use Sound)
(use File)
(use Game)
(use User)
(use Actor)
(use System)

(public
	SHIVERS 0
)

(local
	ego
	theGame
	curRoom
	thePlane
	quit
	cast
	regions
	timers
	sounds
	inventory
	planes
	curRoomNum
	prevRoomNum
	newRoomNum
	debugOn
	score
	possibleScore
	textCode
	cuees
	theCursor
	normalCursor
	waitCursor
	userFont =  1
	smallFont =  4
	lastEvent
	eventMask =  32767
	bigFont =  1
	version
	autoRobot
	curSaveDir
	numCD
	perspective
	features
	panels
	useSortedFeatures
	unused_6
	overlays =  -1
	doMotionCue
	systemPlane
	saveFileSelText
	unused_8
	unused_2
	sysLogPath
	global43
	global44
	global45
	global46
	global47
	global48
	global49
	global50
	global51
	global52
	global53
	global54
	global55
	global56
	global57
	global58
	global59
	global60
	global61
	endSysLogPath
	gameControls
	ftrInitializer
	doVerbCode
	approachCode
	useObstacles =  1
	unused_9
	theIconBar
	mouseX
	mouseY
	keyDownHandler
	mouseDownHandler
	directionHandler
	speechHandler
	lastVolume
	pMouse
	theDoits
	eatMice =  60
	user
	syncBias
	theSync
	extMouseHandler
	talkers
	inputFont
	tickOffset
	howFast
	gameTime
	narrator
	msgType =  1
	messager
	prints
	walkHandler
	textSpeed =  2
	altPolyList
	screenWidth =  320
	screenHeight =  200
	lastScreenX =  319
	lastScreenY =  199
	global100 =  1
	transferRoom
	global102
	gNewCast
	global104
	global105
	global106
	global107
	global108
	global109 =  100
	gExitFeature
	gSound1
	gSound2
	gSound3
	gSound4
	global115
	global116
	global117
	global118 =  6220
	global119 =  212
	global120 =  7000
	global121
	global122 =  8000
	global123
	global124 =  8620
	global125
	global126 =  9000
	global127
	global128 =  9760
	global129
	global130 =  11310
	global131
	global132 =  12170
	global133
	global134 =  14080
	global135
	global136 =  16420
	global137
	global138 =  19220
	global139 =  202
	global140 =  20000
	global141
	global142 =  21000
	global143
	global144 =  22190
	global145
	global146 =  23550
	global147
	global148 =  24000
	global149
	global150 =  29000
	global151
	global152 =  30420
	global153
	global154 =  31000
	global155
	global156 =  32000
	global157
	global158 =  -30536
	global159
	global160
	global161
	global162
	global163
	global164
	global165
	global166
	global167
	global168
	global169
	global170
	global171
	global172
	global173
	global174
	global175
	global176
	global177
	global178
	global179
	global180
	global181
	global182
	global183 =  15
	global184 =  178
	global185 =  253
	global186 =  178
	global187 =  36
	global188 =  173
	global189 =  232
	global190 =  173
	global191 =  57
	global192 =  169
	global193 =  211
	global194 =  169
	global195 =  78
	global196 =  166
	global197 =  190
	global198 =  166
	global199 =  99
	global200 =  163
	global201 =  169
	global202 =  163
	global203
	gNewCast_4
	global205
	global206
	global207
	gNewCast_3
	global209
	global210
	global211
	global212
	global213
	global214
	global215
	global216
	global217
	global218
	global219
	global220
	global221
	global222
	global223
	global224
	global225
	global226
	global227
	global228
	global229
	global230
	global231
	global232
	global233
	global234
	global235
	global236
	global237
	global238
	global239
	global240
	global241
	global242
	global243
	global244
	global245
	global246
	global247
	global248
	global249
	global250
	global251
	global252
	global253
	global254
	global255
	global256
	global257
	global258
	global259
	global260
	global261
	global262
	global263
	global264
	global265
	global266
	global267
	global268
	global269
	global270
	global271
	global272
	global273
	global274
	global275
	global276
	global277
	global278
	global279
	global280
	global281
	global282
	global283
	global284
	global285
	global286
	global287
	global288
	global289
	global290
	global291
	global292
	global293
	global294
	global295
	global296
	global297
	global298
	global299
	gNewSet
	global301
	global302
	global303
	global304
	global305
	global306
	global307
	global308
	global309
	global310
	global311
	global312
	global313
	global314
	global315
	global316
	global317 =  -1
	global318 =  1
	global319 =  1
	global320 =  1
	global321 =  1
	global322 =  1
	global323 =  1
	global324
	global325
	global326
	global327
	gCurColor
	global329
	global330 =  2
	global331
	global332 =  1
	global333 =  3
	global334 =  4
	global335 =  1
	gMouseX
	gMouseY
	global338
	global339
	gGEventX
	gGEventY
	global342
	gGGModNum
	global344
	gNewCast_2
	global346
	global347
	global348
	global349
	global350
	global351
	global352
	global353
	global354
	global355
	global356
	global357
	global358
	global359
	global360
	global361
	global362
	global363
	global364
	global365
	global366
	global367
	global368
	global369
	global370
	global371
	global372
	global373
	global374
	global375
	global376
	global377
	global378
	global379
	global380
	global381
	global382
	global383
	global384
	global385
	global386
	global387
	global388
	global389
	global390
	global391
	global392
	global393
	global394
	global395
	global396
	global397
	global398
	global399
	global400
	global401
	global402
	global403
	global404
	global405
	global406
	global407
	global408
	global409
	global410
	global411
	global412
	global413
	global414
	global415
	global416
	global417
	global418
	global419
	global420
	global421
	global422
	global423
	global424
	global425
	global426
	global427
	global428
	global429
	global430
	global431
	global432
	global433
	global434
	global435
	global436
	global437
	global438
	global439
	global440
	global441
	global442
	global443
	global444
	global445
	global446
	global447
	global448
	global449
	global450
	global451
	global452
	global453
	global454
	global455
	global456
	global457
	global458
	global459
	global460
	global461
	global462
	global463
	global464
	global465
	global466
	global467
	global468
	global469
	global470
	global471
	global472
	global473
	global474
	global475
	global476
	global477
	global478
	global479
	global480
	global481
	global482
	global483
	global484
	global485
	global486
	global487
	global488
	global489
	global490
	global491
	global492
	global493
	global494
	global495
	global496
	global497
	global498
	global499
	global500
	global501
	global502
	global503
	global504
	global505
	global506
	global507
	global508
	global509
	global510
	global511
	global512
	global513
	global514
	global515
	global516
	global517
	global518
	global519
	global520
	global521
	global522
	global523
	global524
	global525
)
(instance sound1 of ShiversSound
	(properties)
)

(instance sound2 of ShiversSound
	(properties)
)

(instance sound3 of ShiversSound
	(properties)
)

(instance sound4 of ShiversSound
	(properties)
)

(class ShiversRoom of Room
	(properties
		scratch 0
		script 0
		number 0
		modNum -1
		noun 0
		case 0
		timer 0
		keep 0
		initialized 0
		picture -1
		plane 0
		style $ffff
		exitStyle -1
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		purge 2000
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		edgeN 40
		edgeE 319
		edgeW 0
		edgeS 189
		movie 0
		invView 0
	)
	
	(method (init &tmp temp0)
		(theGame handsOn:)
		(super init: &rest)
	)
	
	(method (dispose)
		(if movie (movie dispose:))
		(super dispose: &rest)
	)
	
	(method (drawPic pic)
		(if (== style -1) (= style 0))
		(super drawPic: pic &rest)
	)
	
	(method (initRoom param1 param2 param3 param4)
		(if (!= global106 0)
			(= global342
				((ShiversRoomItem new:)
					view: global106
					loop: (curRoom invView?)
					cel: 0
					x: (/ (+ param1 param2) 2)
					y: param4
					setScale:
					scaleX: (/ (* 128 (- param4 param3)) 75)
					scaleY: (/ (* 128 (- param4 param3)) 75)
					nsLeft: param1
					nsTop: param3
					nsRight: param2
					nsBottom: param4
					init:
					yourself:
				)
			)
		)
	)
	
	(method (drawText param1 param2 param3)
		(textBox dispose:)
		(if (> argc 1)
			(textBox x: param2 y: param3 init: param1)
		else
			(textBox init: param1)
		)
		(textBox text: (param1 data?) setSize: 263)
		(UpdateScreenItem textBox)
		(FrameOut)
		(textBox fore: 181)
	)
	
	(method (eraseText)
		(textBox dispose:)
		(FrameOut)
	)
	
	(method (playAVI param1 param2 &tmp temp0 temp1 [temp2 3])
		(if movie (movie dispose:))
		(if (proc951_5 39)
			(= temp0 27)
			(= temp1 7)
		else
			(= temp0 91)
			(= temp1 47)
		)
		(= movie
			((Movie new:)
				posn: temp0 temp1
				open: param1
				client: self
				yourself:
			)
		)
		(if (and (> argc 1) param2) (movie caller: param2))
		(if (proc951_5 39) (movie putDouble:))
		(movie play:)
	)
	
	(method (playVMD param1 param2 &tmp temp0 temp1 [temp2 3])
		(if movie (movie dispose:))
		(if (proc951_5 39)
			(= temp0 54)
			(= temp1 16)
		else
			(= temp0 182)
			(= temp1 102)
		)
		(= movie
			((VmdMovie new:) open: param1 client: self yourself:)
		)
		(if (and (> argc 1) param2) (movie caller: param2))
		(if (proc951_5 39)
			(movie put: temp0 temp1 5 setWaitEvent: 3)
		else
			(movie put: temp0 temp1 4 setWaitEvent: 3)
		)
		(movie close:)
	)
)

(instance SHIVERS of Game
	(properties)
	
	(method (init)
		(= global182 1)
		(Font 1 640 480)
		(= systemPlane (Plane new:))
		(= user User)
		(= score 0)
		(= version {x.yyy.zzz})
		(user alterEgo: (Ego new:))
		(user init:)
		(= global524 (Str new: 9))
		(super init: &rest)
		(= sounds SoundManager)
		(= directionHandler shiversDirection)
		(= gNewCast (Cast new:))
		(= global102
			((Plane new:)
				picture: -2
				priority: 10
				init: 0 0 320 200
				addCast: gNewCast
				yourself:
			)
		)
		(gNewCast plane: global102)
		(= gGEventY (Cast new:))
		(= gGEventX
			((Plane new:)
				picture: -2
				priority: 30
				init: 0 0 320 200
				addCast: gGEventY
				yourself:
			)
		)
		(gGEventY plane: gGEventX)
		(= gNewCast_4 (Cast new:))
		(= global203
			((Plane new:)
				picture: -2
				priority: 45
				init: 27 133 290 200
				addCast: gNewCast_4
				yourself:
			)
		)
		(gNewCast_4 plane: global203)
		(SetPalStyleRange 180 236)
		(thePlane setRect: 27 7 290 149 priority: 20)
		(DoAudio 12 0)
		((= gSound1 sound1) channelNum: 1 owner: self init:)
		((= gSound2 sound2) channelNum: 2 owner: self init:)
		((= gSound3 sound3) channelNum: 3 owner: self init:)
		((= gSound4 sound4) channelNum: 4 owner: self init:)
		(= transferRoom 900)
		(self newRoom: 942)
	)
	
	(method (doit)
		(cond 
			((user canControl:)
				(if (== theCursor waitCursor)
					(theGame setCursor: normalCursor)
					(if gExitFeature
						(= theCursor (gExitFeature cursorCel?))
						(SetCursor 999 0 theCursor)
					)
				)
			)
			((!= theCursor waitCursor) (theGame setCursor: waitCursor))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (replay &tmp temp0 temp1 temp2 temp3 planesNextNode temp5 temp6)
		(theGame handsOff:)
		(= planesNextNode (KList 3 (planes elements?)))
		(while planesNextNode
			(planes nextNode: (KList 6 planesNextNode))
			(if
				(or
					(== ((= temp1 (KList 8 planesNextNode)) picture?) 993)
					(== (temp1 picture?) 994)
				)
				(temp1 picture: -1)
			)
			(AddPlane temp1)
			(if (!= (temp1 picture?) -1)
				(= temp5 (KList 3 ((temp1 casts?) elements?)))
				(while temp5
					((temp1 casts?) nextNode: (KList 6 temp5))
					(= temp2 (KList 8 temp5))
					(= temp6 (KList 3 (temp2 elements?)))
					(while temp6
						(temp2 nextNode: (KList 6 temp6))
						(if (& ((= temp3 (KList 8 temp6)) -info-?) $0010)
							(AddScreenItem temp3)
						)
						(= temp6 (temp2 nextNode?))
					)
					(= temp5 ((temp1 casts?) nextNode?))
				)
			)
			(= planesNextNode (planes nextNode?))
		)
		(if
			(or
				(== (curRoom picture?) 993)
				(== (curRoom picture?) 994)
			)
			(curRoom newRoom: global339)
		)
		(gSound1 pause: 0)
		(gSound2 pause: 0)
		(gSound3 pause: 0)
		(gSound4 pause: 0)
		(if lastEvent (lastEvent dispose:))
		(theGame handsOn:)
		(theGame setCursor: waitCursor 1)
		(= temp0
			(if (not (OneOf (curRoom style?) -1 16 17 18 19))
				(curRoom style?)
			else
				0
			)
		)
		(cond 
			(
			(and (not (user canControl:)) (not (user canInput:))) (theGame setCursor: waitCursor))
			((and theIconBar (theIconBar curIcon:)) (theGame setCursor: (theIconBar getCursor:)))
			(else (theGame setCursor: normalCursor))
		)
		(SL doit:)
		(DoSound sndNOP)
		(Sound pause: 0)
		(= tickOffset (- gameTime (GetTime)))
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (startRoom roomNum &tmp temp0 temp1)
		(= temp0 0)
		(while (< temp0 21)
			(breakif (== [global118 (* temp0 2)] roomNum))
			(++ temp0)
		)
		(if (< temp0 21)
			(= global106 [global118 (+ (* temp0 2) 1)])
			(= global181 1)
		else
			(= global106 0)
			(= global181 0)
		)
		(if (== global100 1)
			(if (< roomNum 0)
				(= temp1 (/ (= temp0 (+ roomNum -32768)) 10))
				(= temp0 (+ (mod temp0 10) 8))
				(= temp1 (+ temp1 (/ temp0 10) 3276))
				(= temp0 (mod temp0 10))
				(MonoOut {Room = %d%d} temp1 temp0)
			else
				(MonoOut {Room = %d} roomNum)
			)
		)
		(if (!= global105 0) (theDoits add: global108))
		(theDoits add: gSound1)
		(theDoits add: gSound2)
		(theDoits add: gSound3)
		(theDoits add: gSound4)
		(if
			(and
				(< 0 prevRoomNum)
				(< prevRoomNum 990)
				(or (>= roomNum 990) (< roomNum 0))
			)
			((ScriptID 950 0) init:)
			(if (!= global105 0)
				(self setScript: (ScriptID 950 13))
			)
		)
		(if
		(and (> prevRoomNum 989) (< 0 roomNum) (< roomNum 950))
			((ScriptID 950 0) dispose:)
		)
		(= theCursor 0)
		(SetCursor 999 0 0)
		(super startRoom: roomNum)
	)
	
	(method (restore param1)
		(if
		(SaveGame 3 {SHIVERS} param1 (KString 9 version))
			(SaveGame 1 {SHIVERS} param1 (KString 9 version))
		)
	)
	
	(method (save &tmp temp0 temp1 newFile temp3 temp4)
		(= temp0
			(SaveGame
				0
				{SHIVERS}
				global525
				(global524 data?)
				(KString 9 version)
			)
		)
		(= temp1 (Str format: {%s%d.SG} curSaveDir global525))
		(= temp4 (Str format: {%s\0D\n} (global524 data?)))
		(if gNewCast_2
			(= temp3 (Str format: {%-3d%-3d} gNewCast_2 score))
		else
			(= temp3 (Str format: {%-6d} score))
		)
		(temp3 strip: 115)
		(= newFile (File new:))
		(newFile name: (temp1 data?))
		(newFile
			open: 2
			writeString: (temp4 data?)
			writeString: (temp3 data?)
			close:
		)
		(newFile dispose:)
		(temp1 dispose:)
		(temp4 dispose:)
		(temp3 dispose:)
		(return temp0)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return (super handleEvent: event))
	)
)

(class ShiversInvItem of Prop
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
		x 57
		y 185
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
		view -1
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
	)
	
	(method (init)
		(super init: gGEventY)
		(self setPri: 254 plane: gGEventX)
		(UpdatePlane gGEventX)
		(theDoits add: self)
		(self setScript: (ScriptID 950 13))
	)
	
	(method (doit)
		(if (and (== global180 1) (!= curRoomNum 992))
			(if
				(and
					(< 0 mouseX)
					(< mouseX 320)
					(< 0 mouseY)
					(< mouseY 200)
				)
				(normalCursor hide:)
				(self x: mouseX y: mouseY show:)
			else
				(self hide:)
				(normalCursor show:)
			)
			(if (self isNotHidden:) (UpdateScreenItem self))
		)
	)
	
	(method (dispose)
		(gGEventY delete: self)
		(self setScript: (ScriptID 950 14))
		(theDoits delete: self)
		(super dispose: &rest)
	)
)

(class ShiversRoomItem of View
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
		priority 20
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
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
	
	(method (init)
		(mouseDownHandler add: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(self isNotHidden:)
				(!= global180 1)
				(user canControl:)
			)
			(theGame handsOff:)
			(sounds play: 15014 0 32 0)
			(event claimed: 1)
			(= temp2 global105)
			(MonoOut {prev: %d} temp2)
			(if (and (<= 200 global106) (<= global106 219))
				(proc951_16 (- global106 200))
			)
			(if (== (Abs (- global105 global106)) 10)
				(= global105 (+ (Max global105 global106) 10))
				(= global106 0)
			else
				(= temp1 global105)
				(= global105 global106)
				(= global106 temp1)
			)
			(= temp0 0)
			(while (< temp0 21)
				(breakif (== [global118 (* temp0 2)] curRoomNum))
				(++ temp0)
			)
			(= [global118 (+ (* temp0 2) 1)] global106)
			(if (!= global106 0)
				(self view: global106 loop: (curRoom invView?) setScale:)
				(UpdateScreenItem self)
			else
				(mouseDownHandler delete: self)
				(self dispose:)
			)
			(if (== temp2 0)
				(= global108
					((ShiversInvItem new:)
						view: global105
						loop: 0
						init:
						yourself:
					)
				)
			else
				(global108 view: global105)
				(UpdateScreenItem global108)
			)
			(theGame handsOn:)
			(return (event claimed: 1))
		)
		(return 0)
	)
)

(class ShiversProp of Prop
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
		view -1
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
	)
	
	(method (init)
		(mouseDownHandler add: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(user canControl:)
				(!= global180 1)
			)
			(event claimed: 1)
			(self doVerb:)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb)
	)
)

(class PotSpot of Feature
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
		(mouseDownHandler add: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp temp0)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(== global180 1)
				(== global106 0)
				(user canControl:)
			)
			(theGame handsOff:)
			(event claimed: 1)
			(normalCursor show:)
			(= global180 0)
			(= global342
				((ShiversRoomItem new:)
					view: global105
					loop: (curRoom invView?)
					cel: 0
					x: (/ (+ (self nsLeft?) (self nsRight?)) 2)
					y: (self nsBottom?)
					setScale:
					scaleX: (/ (* 128 (- (self nsBottom?) (self nsTop?))) 75)
					scaleY: (/ (* 128 (- (self nsBottom?) (self nsTop?))) 75)
					nsLeft: (self nsLeft?)
					nsTop: (self nsTop?)
					nsRight: (self nsRight?)
					nsBottom: (self nsBottom?)
					init:
					yourself:
				)
			)
			(= global106 global105)
			(= global105 0)
			(global108 dispose:)
			(= global108 0)
			(= temp0 0)
			(while (< temp0 21)
				(breakif (== [global118 (* temp0 2)] curRoomNum))
				(++ temp0)
			)
			(= [global118 (+ (* temp0 2) 1)] global106)
			(theGame handsOn:)
			(return 1)
		)
		(return 0)
	)
)

(class HotSpot of Feature
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
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
				(self onMe: event)
				(!= global180 1)
			)
			(event claimed: 1)
			(self doVerb:)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb)
	)
)

(class ExitFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft -999
		nsTop -999
		nsRight -999
		nsBottom -999
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x -999
		y -999
		z 0
		exitDir 0
		nextRoom 0
		cursorCel -999
	)
	
	(method (init param1)
		(self exitDir: param1 plane: thePlane)
		(mouseDownHandler add: self)
		(theDoits add: self)
		(cond 
			((== param1 1)
				(if (== nsLeft -999) (= nsLeft 244))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 264))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 1))
			)
			((== param1 2)
				(if (== nsLeft -999) (= nsLeft 0))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 20))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 2))
			)
			((== param1 6)
				(if (== nsLeft -999) (= nsLeft 244))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 264))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 6))
			)
			((== param1 7)
				(if (== nsLeft -999) (= nsLeft 0))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 20))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 7))
			)
			((or (== param1 9) (== param1 3)) (if (== cursorCel -999) (= cursorCel 3)))
			((== param1 4) (if (== cursorCel -999) (= cursorCel 4)))
			((== param1 5) (if (== cursorCel -999) (= cursorCel 5)))
			((and (== param1 8) (== cursorCel -999)) (= cursorCel 8))
		)
		(if (== cursorCel -999) (= cursorCel 0))
		(super init: &rest)
	)
	
	(method (doit &tmp temp0 temp1)
		(= temp0 (- mouseX 27))
		(= temp1 (- mouseY 7))
		(cond 
			((self onMe: temp0 temp1)
				(if
				(and (!= theCursor cursorCel) (== gExitFeature 0))
					(= theCursor cursorCel)
					(SetCursor 999 0 theCursor)
					(= gExitFeature self)
				)
			)
			(
			(and (== theCursor cursorCel) (== gExitFeature self))
				(= theCursor 0)
				(SetCursor 999 0 theCursor)
				(= gExitFeature 0)
			)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(theDoits delete: self)
		(= gExitFeature 0)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(!= nextRoom 0)
				(user canControl:)
				(!= global180 1)
			)
			(theGame handsOff:)
			(if (== global180 1)
				(global108 x: 57 y: 185 view: global105 show:)
				(UpdateScreenItem global108)
				(= global180 0)
			)
			(normalCursor show:)
			(curRoom newRoom: nextRoom)
		else
			(super handleEvent: event)
		)
	)
)

(instance textBox of DText
	(properties
		priority 255
		fixPriority 1
		fore 181
		back 0
		font 2510
	)
	
	(method (init param1)
		(self text: (param1 data?) setSize: 265)
		(super init: gNewCast_4)
	)
	
	(method (dispose)
		(if text (KString 4 text) (= text 0))
		(gNewCast_4 delete: self)
		(super dispose: &rest)
	)
)

(instance shiversDirection of EventHandler
	(properties)
	
	(method (handleEvent)
	)
)
