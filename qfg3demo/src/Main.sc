;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use PMouse)
(use Print)
(use Polygon)
(use Window)
(use Sound)
(use Game)
(use User)
(use System)

(public
	Glory 0
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
	version
	locales
	curSaveDir
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime
	currentPalette
	modelessPort
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
	useObstacles =  TRUE
	theMenuBar
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
	cDAudio
	fastCast
	inputFont
	tickOffset
	howFast
	gameTime
	narrator
	msgType
	messager
	prints
	walkHandler
	textSpeed =  2
	altPolyList
	global96
	global97
	global98
	lastSysGlobal
	global100
	dongle =  1234
	global102
	music
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
	global183
	global184
	global185
	global186
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
	soundFx
	global200
	debugging
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
)
(instance qg3KDHandler of EventHandler)

(instance qg3MDHandler of EventHandler)

(instance qg3DirHandler of EventHandler)

(instance egoObj of Ego
	(properties
		name "hero"
	)
)

(instance Glory of Game
	
	(method (init &tmp temp0 versionFile [temp2 2])
		(= debugging TRUE)
		(= systemWindow SysWindow)
		Polygon
		(super init:)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileClose versionFile)
		(= pMouse PseudoMouse)
		((= music longSong) owner: self flags: mNOPAUSE init:)
		((= soundFx longSong2) owner: self flags: mNOPAUSE init:)
		(= ego egoObj)
		(User alterEgo: ego canControl: FALSE canInput: FALSE)
		(= msgType TEXT_MSG)
		(= showStyle HSHUTTER)
		(= useSortedFeatures TRUE)
		((= keyDownHandler qg3KDHandler) add:)
		((= mouseDownHandler qg3MDHandler) add:)
		((= directionHandler qg3DirHandler) add:)
		(= userFont 300)
		(= smallFont 999)
		(= bigFont 300)
		(if (HaveMouse)
			(= eatMice 6)
			(theGame setCursor: normalCursor TRUE)
		else
			(theGame setCursor: normalCursor TRUE 304 174)
		)
		(self newRoom: 130)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(if
			(and
				(!= (- (MemoryInfo FreeHeap) 2) (MemoryInfo LargestPtr))
				(Prints {Memory fragmented.})
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
	)
	
	(method (restart)
		(curRoom style: IRISIN drawPic: 0)
		(cast eachElementDo: #dispose)
		(super restart:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(`^q
							(= quit TRUE)
						)
						(`^c
							(= quit TRUE)
						)
						(`^x
							(= quit TRUE)
						)
						(`^z
							(= quit TRUE)
						)
						(`@q
							(= quit TRUE)
						)
						(`@c
							(= quit TRUE)
						)
						(`@x
							(= quit TRUE)
						)
						(`@z
							(= quit TRUE)
						)
						(else
							(return TRUE)
						)
					)
				)
			)
		)
	)
	
	(method (setCursor form showIt theX theY &tmp oldCurObj)
		(= oldCurObj theCursor)
		(if argc
			(if (IsObject form)
				((= theCursor form) init:)
			else
				(SetCursor (= theCursor form) 0 0)
			)
		)
		(if (and (> argc 1) (not showIt))
			(SetCursor 996 0 0)
		)
		(if (> argc 2)
			(SetCursor theX theY)
		)
		(return oldCurObj)
	)
	
	(method (quitGame)
		(Prints {Quit})
		(super quitGame: TRUE)
	)
)

(instance longSong of Sound)

(instance longSong2 of Sound)
