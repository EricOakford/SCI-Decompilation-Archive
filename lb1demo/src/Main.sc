;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use DCIcon)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use User)
(use System)

(public
	MM1 0
	HandsOff 10
	HandsOn 11
	HaveMem 14	;added from full game, since the menu uses it
	RedrawCast 20
	cls 23
	DebugTP 35
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
	showStyle =  7
	aniInterval
	theCursor
	normalCursor =  999
	waitCursor =  997
	userFont =  1
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  1
	volume =  12
	version =  {myIcon}
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
	global111 =  42
	global112
	global113 =  5
	global114 =  9
	global115 =  18
	global116
	global117
	global118
	global119
	global120
	global121
	global122
	global123
	global124
	global125
	global126
	global127
	global128
	global129
	global130
	global131
	global132
	global133
	global134
	global135
	global136 =  7
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
	talkTimer
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
	cSound
	global184
	global185
	global186 =  7
	global187
	global188
	global189
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
	global204
	global205
	global206
	global207
	global208
	global209
	global210
	global211
	global212
	global213
	global214
	global215
	global216
	global217
	mouseDownHandler
	global219
	global220
	global221
	machineSpeed
	howFast
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
	menuIsOn
	global299
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
(procedure (HandsOff)
	(ToggleHands FALSE)
)

(procedure (HandsOn)
	(ToggleHands TRUE)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (DebugTP roomNum &tmp nRoom)
	(= nRoom (GetNumber {Teleport to room:}))
	(roomNum newRoom: (if (< nRoom 1) 1 else nRoom))
)

(procedure (ToggleHands tOrF)
	(User canControl: tOrF canInput: tOrF)
	(ego setMotion: 0)
)

(instance myIcon of DCIcon

	(method (init)
		(if global132
			((= cycler (Forward new:)) init: self)
		)
	)
)

(instance egoObj of Ego
	(properties
		name {ego}
	)
)

(instance conMusic of Sound
	(properties
		number 1
	)
)

(class myWindow of SysWindow
	(properties
		color vWHITE
		back vGREY
		type $0081
		underBits 0
	)
	
	(method (dispose)
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(Graph GReAnimate
			(- top 8)
			(- left 8)
			(+ bottom 8)
			(+ right 8)
		)
		(DisposeWindow window)
		(DisposeClone self)
	)
	
	(method (open &tmp wMap t l b r topRgt botRgt topLft botLft)
		(= topLft (CelHigh 657 0 0))
		(= topRgt (CelHigh 657 0 1))
		(= botRgt (CelHigh 657 1 0))
		(= botLft (CelWide 657 0 0))
		(SetPort 0)
		(= t (- top 8))
		(= l (- left 8))
		(= b (+ bottom 8))
		(= r (+ right 8))
		(= wMap VMAP)
		(if (!= priority -1)
			(= wMap (| wMap PMAP))
		)
		(= underBits (Graph GSaveBits t l b r wMap))
		(Graph GFillRect t l b r wMap back priority)
		(DrawCel 657 0 0 l t -1)
		(DrawCel 657 0 1 l (- b topRgt) -1)
		(DrawCel 657 1 0 (- r botRgt) t -1)
		(DrawCel 657 1 2 (- r botRgt) (- b topRgt) -1)
		(Graph GDrawLine t (+ l botLft) t (- r botLft) 31 -1 -1)
		(Graph GDrawLine (+ t 2) (+ l botLft) (+ t 2) (- r botLft) 31 -1 -1)
		(Graph GDrawLine (- b 1) (+ l botLft) (- b 1) (- r botLft) 31 -1 -1)
		(Graph GDrawLine (- b 3) (+ l botLft) (- b 3) (- r botLft) 31 -1 -1)
		(Graph GDrawLine (+ t topLft) l (- b topLft) l 31 -1 -1)
		(Graph GDrawLine (+ t topLft) (+ l 2) (- b topLft) (+ l 2) 31 -1 -1)
		(Graph GDrawLine (+ t topLft) (- r 1) (- b topLft) (- r 1) 31 -1 -1)
		(Graph GDrawLine (+ t topLft) (- r 3) (- b topLft) (- r 3) 31 -1 -1)
		(Graph GShowBits t l b r VMAP)
		(= type $81)
		(super open:)
	)
)

(instance mmMouseDownHandler of EventHandler)

(instance MM1 of Game
	
	(method (init)
		(= systemWindow myWindow)
		(if (< (Graph GDetect) 16)
			(myWindow color: vWHITE back: vBLACK)
		)
		(User alterEgo: (= ego egoObj))
		(super init:)
		((= mouseDownHandler mmMouseDownHandler) add:)
		(= global221 0)
		(= userFont
			(= bigFont USERFONT)
		)
		(= version {x.yyy.zzz})
		(conMusic owner: self init:)
		(= cSound conMusic)
		(ego view: 0 x: 100 y: 120)
		(if (GameIsRestarting)
			(self newRoom: 777)
			(= userFont bigFont)
		else
			(self newRoom: 777)
		)
	)
	
	(method (doit &tmp temp0)
		(if (> talkTimer 0)
			(-- talkTimer)
		)
		(super doit:)
	)
	
	(method (replay)
		(= userFont bigFont)
		(super replay:)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(DisposeScript RFEATURE)
		(mouseDownHandler release:)
		(mouseDownHandler add: cast features)
		(cls)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp [temp0 52])
		(super handleEvent: event)
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(`^q
						(= quit TRUE)
					)
				)
			)
			(mouseDown
				(mouseDownHandler handleEvent: event)
			)
		)
	)
)
