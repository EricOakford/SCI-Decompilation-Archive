;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use Save)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	KQ4 0
	smallBase 1
	IsObjectOnControl 2
	Face 3
	timer1 4
	timer2 5
	timer3 6
	timer4 7
	timer5 8
	NormalEgo 9
	HandsOff 10
	HandsOn 11
	NotifyScript 12
	SetEgoView 13
	HaveMem 14
	RedrawCast 20
	proc0_21 21
	cls 23
)

(local
	ego
	theGame
	curRoom
	speed =  6
	quit
	cast
	regions
	timers
	sounds
	inventory
	addToPics
	curRoomNum
	prevRoomNum
	newRoomNum
	debugOn
	score
	possibleScore
	showStyle =  IRISOUT
	aniInterval
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	volume =  12
	version =  {ego}
	locales
	curSaveDir
		global31
		global32
		global33
		global34
		global35
		global36
		global37
		global38
		global39
		global40
		global41
		global42
		global43
		global44
		global45
		global46
		global47
		global48
		global49
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	demoScripts
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime
	currentPalette
		global62
		global63
		global64
		global65
		global66
		global67
		global68
		global69
		global70
		global71
		global72
		global73
		global74
		global75
		global76
		global77
		global78
		global79
		global80
		global81
		global82
		global83
		global84
		global85
		global86
		global87
		global88
		global89
		global90
		global91
		global92
		global93
		global94
		global95
		global96
		global97
		global98
		lastSysGlobal
	global100
	global101
	global102
	global103
	global104
	global105
	global106
	global107
	global108
	global109
	global110
	global111
	global112
	global113
	global114
	global115
	global116
	global117
	whereIsMinstrel
	global119
	global120
	global121
	global122
	global123
	global124
	global125
	global126
	dead
	global128
	global129
	global130
	global131
	global132
	global133
	global134
	global135
	global136
	global137
	global138
	global139
	global140
	global141
	global142
	global143
	global144
	global145
	global146
	global147
	global148
	global149
	global150
	global151
	global152
	global153
	global154
	global155
	global156
	global157
	global158
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
	lolotteAlive
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
	global183
	global184
	global185
	global186
	global187
	global188
	noWearCrown
	global190
	global191
	global192
	global193
	global194
	global195
	global196
	global197
	global198
	global199
	global200
	global201
	global202
	global203
	isHandsOff
	inCutscene
	global206
	global207
	global208
	global209
	global210
	global211
	global212
	global213
	global214
	debugging
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
	introScript
	global300
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
	global317
	global318
	global319
	global320
	global321
	global322
	global323
	global324
	global325
	global326
	global327
	global328
	global329
	global330
	global331
	global332
	global333
	global334
	global335
	global336
	global337
	global338
	global339
	global340
	global341
	global342
	global343
	global344
	global345
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
	global526
	global527
	global528
	global529
	global530
	global531
	global532
	global533
	global534
	global535
	global536
	global537
	global538
	global539
	global540
	global541
	global542
	global543
	global544
	global545
	global546
	global547
	global548
	global549
	global550
)
(procedure (IsObjectOnControl obj event)
	(if (< argc 2) (= event 5))
	(switch (obj loop?)
		(0
			(OnControl CMAP
				(obj x?)
				(obj y?)
				(+ (obj x?) event)
				(+ (obj y?) 1)
			)
			(return)
		)
		(1
			(OnControl CMAP
				(- (obj x?) event)
				(obj y?)
				(obj x?)
				(+ (obj y?) 1)
			)
			(return)
		)
		(2
			(OnControl CMAP
				(obj x?)
				(obj y?)
				(+ (obj x?) 1)
				(+ (obj y?) event)
			)
			(return)
		)
		(3
			(OnControl CMAP
				(obj x?)
				(- (obj y?) event)
				(+ (obj x?) 1)
				(obj y?)
			)
			(return)
		)
	)
)

(procedure (Face actor1 actor2)
	(DirLoop
		actor1
		(GetAngle
			(actor1 x?)
			(actor1 y?)
			(actor2 x?)
			(actor2 y?)
		)
	)
	(if (== argc 3)
		(DirLoop
			actor2
			(GetAngle
				(actor2 x?)
				(actor2 y?)
				(actor1 x?)
				(actor1 y?)
			)
		)
	)
)

(procedure (NormalEgo theLoop theView)
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1) (ego view: theView))
	)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: 0
	)
	(User canControl: TRUE canInput: TRUE)
)

(procedure (HandsOff)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= isHandsOff TRUE)
	(= global114 noWearCrown)
	(= noWearCrown TRUE)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(ego setMotion: 0)
	(= isHandsOff FALSE)
	(= noWearCrown global114)
)

(procedure (NotifyScript script &tmp i)
	(= i (ScriptID script))
	(i notify: &rest)
)

(procedure (SetEgoView)
	(return (if (== (ego view?) 2) else (== (ego view?) 4)))
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (proc0_21 param1 param2)
	(param1 loop: param2 changeState:)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance KQ4 of Game
	(properties)
	
	(method (init)
		(= systemWindow SysWindow)
		(super init:)
		(= ego egoObj)
		(User alterEgo: ego)
		(= aniThreshold 7)
		(= showStyle HSHUTTER)
		(= userFont (= bigFont USERFONT))
		(= lolotteAlive TRUE)
		(TheMenuBar init:)
		(= whereIsMinstrel (Random 1 3))
		(User canInput: FALSE canControl: FALSE echo: SPACEBAR)
		(= inCutscene TRUE)
		(ego view: 2 x: 100 y: 120)
		(if (GameIsRestarting)
			(= userFont bigFont)
		else
			(self newRoom: 699)
		)
	)
	
	(method (doit)
		(if (== curRoomNum newRoomNum)
			(super doit:)
		)
	)
	
	(method (replay)
		(= userFont bigFont)
		(super replay:)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(or
				isHandsOff
				(and (not inCutscene) (== (User canControl:) FALSE))
			)
			(return)
		)
		(super newRoom: newRoomNumber)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(switch roomNum
			(else  0)
		)
		(if (= temp0 (DisposeScript AVOIDER))
			((ScriptID temp0) init:)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp [temp0 2] temp2 newEvent [buffer 50])
		(if
			(and
				debugging
				(not (event claimed?))
				(== (event type?) mouseDown)
			)
			(cond 
				((& (event modifiers?) shiftDown)
					(event claimed: TRUE)
					(= temp2
						(Print
							(Format @buffer 0 0 (event x?) (event y?))
							#at 150 100
							#font 999
							#dispose
						)
					)
					(while (!= 2 ((= newEvent (Event new:)) type?))
						(newEvent dispose:)
					)
					(temp2 dispose:)
					(newEvent dispose:)
				)
				((& (event modifiers?) ctrlDown)
					(event claimed: TRUE)
					(while (!= 2 ((= newEvent (Event new:)) type?))
						((User alterEgo?)
							posn: (newEvent x?) (- (newEvent y?) 10)
							setMotion: 0
						)
						(Animate (cast elements?) FALSE)
						(newEvent dispose:)
					)
					(newEvent dispose:)
				)
				((& (event modifiers?) altDown) (event claimed: TRUE) ((User alterEgo?) showSelf:))
			)
			(if (event claimed?) (return TRUE))
		)
		(return
			(if (== curRoomNum newRoomNum)
				(super handleEvent: event)
			else
				FALSE
			)
		)
	)
	
	(method (wordFail)
	)
	
	(method (syntaxFail)
	)
	
	(method (pragmaFail)
	)
)

(instance smallBase of Code
	(properties)
	
	(method (doit param1)
		(param1 brTop: (- (param1 y?) (param1 yStep?)))
		(param1 brLeft: (- (param1 x?) (/ (param1 xStep?) 2)))
		(param1 brBottom: (param1 y?))
		(param1
			brRight: (+ (param1 x?) (/ (param1 xStep?) 2))
		)
	)
)

(instance timer1 of Timer
	(properties)
)

(instance timer2 of Timer
	(properties)
)

(instance timer3 of Timer
	(properties)
)

(instance timer4 of Timer
	(properties)
)

(instance timer5 of Timer
	(properties)
)
