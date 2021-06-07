;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use Sound)
(use Jump)
(use Motion)
(use File)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	SQ3 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	CantReach 5
	AlreadyDone 6
	SeeNothing 7
	CantDo 8
	DontHave 9
	RedrawCast 10
	proc0_11 11
	cls 12
	CheckItemOwner 13
	SetItemOwner 14
	IsObjectOnControl 15
	EgoDead 17
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
	version =  {statusCode}
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
	global118
	global119
	global120
	global121 =  1000
	global122
	global123
	global124
	startingRoom
	global126
	global127
	global128
	global129
	global130
	global131
	global132
	global133
	global134
	global135 =  1
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
	global149 =  2
	global150
	global151
	global152
	music
	global154
	isHandsOff
	global156
	global157
	global158
	global159 =  1
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
	decodedMessage
	global171
	global172
	global173
	global174 =  12
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
	certainDeath
	global189
	deathView
	deathLoop
	deathCel
	saveDisabled
	global194
	global195
	global196
	dead
	thisTime
	oldSysTime
	debugging
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
	global214 =  75
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
	gameSeconds
	gameMinutes
	gameHours
	global229
	oldCursor
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
	vaporCalcOn
	vaporCalcOnScreen
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
	theInvItem
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
	invStr
	global403
	global404
	global405
	global406
	global407
	global408
	global409
	global410S
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
	global551
	global552
	global553
	global554
	global555
	global556
	global557
	global558
	global559
	global560
	global561
	global562
	global563
	global564
	global565
	global566
	global567
	global568
	global569
	global570
	global571
	global572
	global573
	global574
	global575
	global576
	global577
	global578
	global579
	global580
	global581
	global582
	global583
	global584
	global585
	global586
	global587
	global588
	astroChickenPlays
	astroChickenScore
	global591
	global592
	global593
	global594
	global595
	global596
	global597
	numColors
	global599
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
		setStep: 3 2
		looper: 0
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: 0
	)
)

(procedure (HandsOff)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= isHandsOff TRUE)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(= isHandsOff FALSE)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (CantReach)
	(Print 0 7)
)

(procedure (AlreadyDone)
	(Print 0 8)
)

(procedure (SeeNothing)
	(Print 0 9)
)

(procedure (CantDo)
	(Print 0 10)
)

(procedure (DontHave)
	(Print 0 11)
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (proc0_11 param1 param2)
	(param1 loop: param2 changeState:)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (CheckItemOwner item owner)
	(return
		(==
			((inventory at: item) owner?)
			(if (== argc 1) curRoomNum else owner)
		)
	)
)

(procedure (SetItemOwner item owner)
	((inventory at: item)
		owner: (if (== argc 1) curRoomNum else owner)
	)
)

(procedure (IsObjectOnControl obj event)
	(if (< argc 2) (= event 5))
	(OnControl
		(- (obj x?) event)
		(- (obj y?) event)
		(+ (obj x?) event)
		(+ (obj y?) event)
	)
)

(procedure (EgoDead theView theLoop theCel theDeath)
	(HandsOff)
	(= dead TRUE)
	(if (not theView)
		(= deathView 901)
	else
		(= deathView theView)
	)
	(= deathLoop theLoop)
	(= deathCel theCel)
	(= certainDeath theDeath)
)

(instance statusCode of Code
	(properties)
	
	(method (doit strg)
		(Format strg 0 0)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance longSong of Sound
	(properties
		number 1
	)
)

(instance logFile of File)

(instance SQ3 of Game
	
	(method (init)
		(super init:)
		(= numColors (kernel_112 2)) ;EO: kernel function 112 should be Graph, but vocab.999 does not have that
		(= version {1.0V - 8/17/89})
		(= saveDisabled TRUE)
		(longSong owner: self init:)
		(= music longSong)
		(User blocks: 0 canControl: FALSE x: -1 y: 160)
		(= ego egoObj)
		(User alterEgo: ego)
		(StatusLine code: statusCode)
		(theGame setSpeed: 10)
		(TheMenuBar init:)
		(HandsOn)
		(= useSortedFeatures TRUE)
		(ScriptID SORTCOPY)
		(= possibleScore 738)
		(= userFont 300)
		(if (GameIsRestarting)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(= startingRoom 890)
			(self newRoom: 290)
		else
			(TheMenuBar state: TRUE)
			(= startingRoom 890)
			(self newRoom: 890)
		)
	)
	
	(method (doit &tmp temp0)
		(if
			(and
				(!= curRoomNum 890)
				(!= curRoomNum 1)
				(!= curRoomNum 155)
			)
			(= temp0 (HaveMouse))
			(if (not global592)
				(cond 
					(global159 (= oldCursor 69))
					((== (User controls?) FALSE) (= temp0 1) (= oldCursor waitCursor))
					(else (= oldCursor normalCursor))
				)
				(if (!= theCursor oldCursor)
					(self setCursor: oldCursor temp0)
				)
			)
		)
		(if (== vaporCalcOn TRUE)
			(= vaporCalcOn FALSE)
			(= vaporCalcOnScreen TRUE)
			(calc init:)
		)
		(if dead
			(= global159 0)
			(music
				number: (Random 23 24)
				loop: 1
				priority: 500
				play:
			)
		else
			(= global219 0)
			(= global223 0)
			(if (!= (= thisTime (GetTime SYSTIME1)) oldSysTime)
				(= oldSysTime thisTime)
				(= gameSeconds (+ gameSeconds 1))
				(= global219 1)
				(if (>= gameSeconds 60)
					(++ gameMinutes)
					(= gameSeconds 0)
					(= global223 1)
					(if (== gameMinutes 60)
						(++ gameHours)
						(= gameMinutes 0)
					)
				)
			)
		)
		(super doit:)
	)
	
	(method (replay)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(SetMenu soundI
			p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(super replay:)
	)
	
	(method (startRoom roomNum)
		(DisposeScript AVOIDER)
		(if debugOn (= debugOn FALSE) (SetDebug))
		(if
			(and
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				debugging
				(Print 0 1 #button {Debug} 1)
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp temp0 saveBits evt [temp3 2] evtX evtY evtMod temp8 [str 50])
		(if (event claimed?) (return))
		(super handleEvent: event)
		(if (== vaporCalcOnScreen TRUE)
			(event claimed: TRUE)
			(= vaporCalcOnScreen FALSE)
			(calc dispose:)
		)
		(switch (event type?)
			(mouseDown
				(if debugging
					(= evtX (event x?))
					(= evtY (event y?))
					(cond 
						(
						(== (= evtMod (event modifiers?)) 10)
							(event claimed: TRUE)
							((User alterEgo?) setMotion: JumpTo evtX evtY)
						)
						((& evtMod shiftDown)
							(event claimed: TRUE)
							(= saveBits
								(Print
									(Format @str 0 2 evtX evtY)
									#at
									(cond 
										((< evtX 20) evtX)
										((< 300 evtX) (- evtX 40))
										(else (- evtX 20))
									)
									(if (< evtY 16) evtY else (- evtY 6))
									#font
									999
									#dispose
								)
							)
							(while (!= 2 ((= evt (Event new:)) type?))
								(evt dispose:)
							)
							(saveBits dispose:)
							(evt dispose:)
						)
						((& evtMod ctrlDown)
							(event claimed: TRUE)
							(while (!= 2 ((= evt (Event new:)) type?))
								((User alterEgo?)
									posn: (evt x?) (- (evt y?) 10)
									setMotion: 0
								)
								(Animate (cast elements?) FALSE)
								(evt dispose:)
							)
							(evt dispose:)
						)
						((& evtMod altDown) (event claimed: TRUE) ((User alterEgo?) showSelf:))
					)
				)
			)
			(keyDown
				(if (not debugging) (return))
				(switch (event message?)
					(`@z
						(if debugging (event claimed: TRUE) (= quit TRUE))
					)
					(`@e
						(Print
							(Format
								@str
								{view: %d loop: %d cel: %d posn: %d %d pri: %d OnControl: $%x Origin on: $%x}
								(ego view?)
								(ego loop?)
								(ego cel?)
								(ego x?)
								(ego y?)
								(ego priority?)
								(ego onControl:)
								(ego onControl: origin)
							)
							#icon
							(ego view?)
							(ego loop?)
							(ego cel?)
						)
					)
					(`@h
						(theGame showMem:)
						(event claimed: TRUE)
					)
					(`@r
						(Print (Format @str 0 3 curRoomNum))
					)
					(`@v (Show VMAP))
					(`@p (Show PMAP))
					(`@y
						(= invStr 0)
						(GetInput @invStr 8 {Inv. Object})
						(= theInvItem (ReadNumber @invStr))
						(= invStr 0)
						(GetInput @invStr 12 {Owner})
						(if (not (StrCmp {ego} @invStr))
							((inventory at: theInvItem) moveTo: ego)
						else
							((inventory at: theInvItem)
								moveTo: (ReadNumber @invStr)
							)
						)
						(= invStr 0)
					)
					(`@c
						(Show CMAP)
						(Animate (cast elements?))
						(while
						(== 0 ((= evt (Event new: allEvents)) type?))
							(evt dispose:)
						)
						(evt dispose:)
						(Show VMAP)
					)
				)
			)
		)
	)
	
	(method (wordFail word &tmp [str 100])
		(Print (Format @str 0 4 word))
	)
	
	(method (syntaxFail)
		(Print 0 5)
	)
	
	(method (pragmaFail &tmp [str 100])
		(Print 0 6)
	)
)

(instance calc of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 27
			setLoop: 0
			setCel: 0
			ignoreActors: TRUE
			setPri: 15
			posn: 159 94
			stopUpd:
		)
	)
)
