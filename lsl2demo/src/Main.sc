;;; Sierra Script 1.0 - (do not remove this comment)
(script# LSL2)
(include game.sh) (include menu.sh)
(use Intrface)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	LSL2 0
	Face 1
	NormalEgo 2
	IsObjectOnControl 3
	AddViewToPic 4
	HandsOff 5
	HandsOn 6
	NotifyScript 7
	HaveMem 8
	RedrawCast 9
	proc0_10 10
	cls 11
	Ok 12
	ItIs 13
	YouAre 14
	NotNow 15
	NotClose 16
	AlreadyTook 17
	SeeNothing 18
	CantDo 19
	DontHave 20
	SetRegionTimer 21
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
	version =  {LSL2}
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
	debugging
	currentStatus
	currentEgoView
	henchView
	gameSeconds
	gameMinutes
	gameHours
	ranking
	rgSeconds
	rgMinutes
	gameState
	global111
	global112
	oldSysTime
	global114
	roomSeconds
	global116
	global117
	global118
	speedTestQA
	machineSpeed
	gotHaircutInCity
	gotOnklunk
	filthLevel
	boughtSunscreen
	sunscreenState
	lifeboatLeverPulled
	henchwomanIsHere
	metMama
	woreWigAtSea
	hairDyedBlonde
	gotHaircutAtResort
	resortMazeNextRoom
	stuffedBra
	resortMazeTimes
	talkedToMaitreD
	gaveFlowerToKrishna
	passedCustoms
	suitcaseBombState
	missedFlight
	airportEntranceMessage
	boreState
	wearingParachute
	emergencyExitState
	avoidedBees
	snakeState
	passedQuicksand
	passedPiranhaWater
	endGameState
	global149
	triteStr
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
	tritePhrase
	str
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
	;end of str array
	introductoryPhrase
	lookedThroughKnothole
	tookSwimInShipPool
	boardedLifeboat
	satInGreenRoom
	servedAtResortRestaurant
	lookedAtJogger
	appliedSunscreen
	lookedAtRosella
	appliedSunscreenAgain
	wornParachute	;EO: This was not in the list, but is referenced when Larry
					;puts on the parachute for the first time. As a result,
					;this originally resulted in the points not being awarded
					;when the game is played in ScummVM.
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

(procedure (NormalEgo theLoop)
	(if (> argc 0) (ego loop: theLoop))
	(ego
		view: currentEgoView
		setLoop: -1
		setPri: -1
		setMotion: 0
		setStep: 3 2
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: 0
	)
	(= currentStatus egoNORMAL)
	(User canControl: TRUE canInput: TRUE)
)

(procedure (IsObjectOnControl obj event)
	(if (< argc 2) (= event 5))
	(switch (obj loop?)
		(loopE
			(OnControl
				(obj x?)
				(obj y?)
				(+ (obj x?) event)
				(+ (obj y?) 1)
			)
			(return)
		)
		(loopW
			(OnControl
				(- (obj x?) event)
				(obj y?)
				(obj x?)
				(+ (obj y?) 1)
			)
			(return)
		)
		(loopS
			(OnControl
				(obj x?)
				(obj y?)
				(+ (obj x?) 1)
				(+ (obj y?) event)
			)
			(return)
		)
		(loopN
			(OnControl
				(obj x?)
				(- (obj y?) event)
				(+ (obj x?) 1)
				(obj y?)
			)
			(return)
		)
	)
)

(procedure (AddViewToPic obj)
	(if obj
		((View new:)
			view: (obj view?)
			loop: (obj loop?)
			cel: (obj cel?)
			priority: (obj priority?)
			posn: (obj x?) (obj y?)
			addToPic:
		)
		(obj posn: (obj x?) (+ 1000 (obj y?)))
	)
)

(procedure (HandsOff)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(ego setMotion: 0)
)

(procedure (NotifyScript i)
	(= i (ScriptID i))
	(i notify: &rest)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (proc0_10 theObj theLoop)
	(theObj loop: theLoop changeState:)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (Ok)
	(Print 0 10)
)

(procedure (ItIs)
	(Print 0 11)
)

(procedure (YouAre)
	(Print 0 12)
)

(procedure (NotNow)
	(Print 0 13)
)

(procedure (NotClose)
	(Print 0 14)
)

(procedure (AlreadyTook)
	(Print 0 15)
)

(procedure (SeeNothing)
	(Print 0 16)
)

(procedure (CantDo)
	(Print 0 17)
)

(procedure (DontHave)
	(Print 0 18)
)

(procedure (SetRegionTimer state minutes seconds)
	;This sets the current game state and allotted time.
	;If you don't leave the region in time, the game is over.
	(= gameState state)
	(= rgSeconds (* 10 (+ seconds (* minutes 60))))
)

(instance LSL2 of Game

	(method (init &tmp temp0)
		(= systemWindow (SysWindow new:))
		(systemWindow color: vBLACK back: vWHITE)
		(= ego egoObj)
		(User alterEgo: ego)
		(super init:)
		(= volume 15)
		(DoSound ChangeVolume volume)
		(StatusLine code: statusCode)
		(TheMenuBar init:)
		(scoreSnd init:)
		(deadSnd init:)
		(User echo: SPACEBAR blocks: 0)
		(= bigFont SYSFONT)
		(= possibleScore 500)
		(= currentEgoView 100)
		(= filthLevel 4)
		(= ranking {Novice})
		(= introductoryPhrase {"My name is Larry; Larry Laffer."})
		(= tritePhrase (Format @triteStr 0 0))
		(= version {x.yyy.zzz})
		(Load FONT smallFont)
		(Load FONT userFont)
		(Load FONT bigFont)
		(Load CURSOR normalCursor)
		(Load CURSOR waitCursor)
		(ego view: vEgo setCycle: Walk)
		(Inventory
			empty: {Your leisure suit is empty!}
		)
		(if (GameIsRestarting)
			(StatusLine disable:)
			(TheMenuBar hide:)
			(self newRoom: TITLE)
		else
			(self newRoom: TITLE)
		)
	)
	
	(method (doit &tmp thisTime)
		(super doit:)
		(if (!= oldSysTime (= thisTime (GetTime TRUE)))
			(= oldSysTime thisTime)
			(++ roomSeconds)
			(if (== 60 (++ gameSeconds))
				(= gameSeconds 0)
				(if (== 60 (++ gameMinutes))
					(= gameMinutes 0)
					(++ gameHours)
				)
			)
		)
		(if (and gameState (> rgSeconds 0))
			(-- rgSeconds)
		)
		(if (== currentStatus egoDYING)
			(curRoom setScript: dyingScript)
		)
	)
	
	(method (replay)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(SetMenu soundI
			#text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(super replay:)
	)
	
	(method (newRoom newRoomNumber)
		(DisposeScript JUMP)
		(DisposeScript EXTRA)
		(DisposeScript DOOR)
		(DisposeScript AIRPLANE_ACTOR)
		(DisposeScript BASS_SETTER)
		(= henchwomanIsHere FALSE)
		(= showStyle (Random 0 5))
		(= roomSeconds 0)
		(super newRoom: newRoomNumber)
		(if debugging
			(curRoom setLocales: DEBUG)
		)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(super startRoom: roomNum)
	)
	
	(method (changeScore delta)
		(cond 
			((> delta 25)
				(= ranking {Big Hero})
			)
			((> delta 0)
				(scoreSnd play:)
			)
		)
		(super changeScore: delta)
	)
	
	(method (handleEvent event &tmp temp0 i [temp2 3])
		(if (event claimed?) (return))
		(super handleEvent: event)
	)
	
	(method (wordFail word)
		(Print (Format @str 0 1 word))
	)
	
	(method (syntaxFail)
		(Print 0 2)
	)
	
	(method (pragmaFail)
		(if (<= filthLevel 4)
			(Print 0 3)
		else
			(Print 0 4)
		)
	)
)

(class Iitem of InvItem
	
	(method (showSelf)
		(Print INVDESC view
			#title name
			#icon view 0 0
		)
	)
)

(instance dyingScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= currentStatus egoDEAD)
				(HandsOff)
				(Load SOUND sDeath)
				(= seconds 3)
			)
			(1
				(sounds eachElementDo: #dispose)
				(deadSnd play:)
				(if
					(Print 0 5
						#title {Oh, no! Not again?!}
						#font bigFont
						#icon vBEDismay 0 0
						#button {Keep On Muddling} 0
						#button {Order A Hintbook} 1
					)
					(Print 0 6)
				)
				(repeat
					(switch
						(Print 0 7
							#title {Al says:}
							#font bigFont
							#button {Restore} 1
							#button {Restart} 2
							#button {__Quit__} 3
						)
						(1
							(theGame restore:)
						)
						(2
							(theGame restart:)
						)
						(3
							(= quit TRUE)
							(break)
						)
					)
				)
			)
		)
	)
)

(instance statusCode of Code
	
	(method (doit strg)
		(Format strg 0 8
			score possibleScore 0 9
			ranking 0 9
		)
	)
)

(instance scoreSnd of Sound
	(properties
		number sScore
		priority -10
		owner -1
	)
)

(instance deadSnd of Sound
	(properties
		number sDeath
		priority 255
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)
