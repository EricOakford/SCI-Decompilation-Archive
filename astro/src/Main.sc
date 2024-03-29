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
	NotClose 5
	AlreadyDone 6
	SeeNothing 7
	CantDo 8
	DontHave 9
	RedrawCast 10
	ChangeSoundState 11
	cls 12
	InRoom 13
	PutInRoom 14
	IsObjectOnControl 15
	EgoDead 17
)

(local
	ego									;pointer to ego
	theGame								;ID of the Game instance
	curRoom								;ID of current room
	speed =  6							;number of ticks between animations
	quit								;when TRUE, quit game
	cast								;collection of actors
	regions								;set of current regions
	timers								;list of timers in the game
	sounds								;set of sounds being played
	inventory							;set of inventory items in game
	addToPics							;list of views added to the picture
	curRoomNum							;current room number
	prevRoomNum							;previous room number
	newRoomNum							;number of room to change to
	debugOn								;generic debug flag -- set from debug menu
	score								;the player's current score
	possibleScore						;highest possible score
	showStyle	=		IRISOUT			;style of picture showing
	aniInterval							;# of ticks it took to do the last animation cycle
	theCursor							;the number of the current cursor
	normalCursor =		ARROW_CURSOR	;number of normal cursor form
	waitCursor	 =		HAND_CURSOR		;cursor number of "wait" cursor
	userFont	 =		USERFONT		;font to use for Print
	smallFont	 =		4				;small font for save/restore, etc.
	lastEvent							;the last event (used by save/restore game)
	modelessDialog						;the modeless Dialog known to User and Intrface
	bigFont		=		USERFONT		;large font
	volume		=		12				;sound volume
	version		=		{x.yyy.zzz}		;pointer to 'incver' version string			
	locales								;set of current locales
	[curSaveDir 20]						;address of current save drive/directory string
	aniThreshold	=	10
	perspective							;player's viewing angle:
										;	 degrees away from vertical along y axis
	features							;locations that may respond to events
	sortedFeatures          			;above+cast sorted by "visibility" to ego
	useSortedFeatures					;enable cast & feature sorting?
	demoScripts							;add to curRoomNum to find room demo script
	egoBlindSpot						;used by sortCopy to exclude
										;actors behind ego within angle 
										;from straight behind. 
										;Default zero is no blind spot
	overlays	=		-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	demoDialogTime	=	3				;how long Prints stay up in demo mode
	currentPalette
	;globals 62-99 are unused
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
		;globals 100 and above are for game use
	programControl			;if TRUE, don't allow player control
	orderedBigBelcherCombo	;ego ordered Big Belcher Combo, and he'll throw up afterwards
	global102				;used in Aluminum Mallard
	haggledWithFester		;ego haggled with Fester Blatz over the gem
	global104				;used in Phleebhut
	egoInvisible			;if TRUE, ego is invisible thanks to the belt
	gPEventMessage
	gGEgoX_5
	gGEgoY_4
	global109
	terminatorState			;current state of the Terminator
	terminator				;pointer for Terminator object
	gGEgoX_4
	gGEgoY_3
	notifyCountdown			;time before notification
	global115				;unused
	global116				;unused
	roomWithDeadTerminator	;room where the Terminator was killed
	visitedPhleebhut		;been on Phleebhut
	sawScorpion				;saw the scorpion on Phleebhut
	beltState				;current state of the invisibility belt
	beltTimer =  1000		;time left of the invisibility belt
	pestulonGuardState		;current state of the Pestulon guards
	machineSpeed			;used by the speed tester to test how fast the system is
							; and used in determining game speed. (used in conjunction with howFast)
	howFast					;machine speed level (0 = slow, 1 = medium, 2 = fast)
	startingRoom			;room to start the game in
	wearingBelt				;ego is wearing the invisibility belt
	terminatorRemains		;pointer for Terminator remains
	theBelt					;pointer for invisibility belt object
	foundScumSoft			;ego found ScumSoft on Pestulon
	wearingChickenHat		;ego is wearing the Astro-Chicken hat
	ratStoleReactor			;the rat stole back the reactor. He won't do this again.
	global132				;? used for grabber
	climbedOutOfReactorRoom	;made it out of the reactor room. You can re-enter.
	global134				;related to getting the ladder, but is never set
	motivatorState	=	motivatorONFLOOR	;current state of warp drive motivator
	shipRepairLevel							;when this is at 4, the Aluminum Mallard is ready to go.
	global137				;unused
	global138				;unused
	global139				;unused
	global140				;unused
	global141				;unused
	global142				;unused
	global143				;unused
	global144				;unused
	global145				;unused
	global146				;unused
	gGGGNorth				;related to the warp drive motivator and grabber
	grabberState			;current state of the grabber
	roomWithMotivator =  2	;current room with warp drive motivator
	searchedPilotSeat		;searched the pilot's seat, got buckazoids
	sawTerminator			;saw the Terminator in Phleebhut
	visitedPhleebhutStore	;visited Fester Blatz's store
	theMusic				;pointer for music object
	buckazoids				;number of buckazoids in hand
	isHandsOff				;ego can't be controlled
	global156				;unused
	global157				;checked in room 6, but is never set
	enterpriseLeftMonolithBurger	;The U.S.S. Enterprise left Monolith Burger
	inCartoon =  TRUE		;if TRUE, the game is running a cutscene
	grabberRect				;rectangle of grabber
	global161
	gGEgoY_5
	global163
	global164
	global165
	global166
	global167
	global168
	examinedMallard			;examined the Aluminum Mallard
	decodedMessage			;decoded the secret message in Astro Chicken
	fryToDeathTimer			;time before dying on Ortega
	rockSankInLava			;the rock has sunk into the lava
	global173				;unused
	shipShieldHealth =  12	;amount of health the Mallard's shields have
	global175
	global176
	global177
	twoGuysOnBoard			;saved the Two Guys, and now they're on board
	global179
	fallingIntoLava			;ego is falling into the lava
	global181
	wearingUnderwear		;ego is wearing the ThermoWeave shorts, and won't burn
	steppedOnUnstableRock	;ego stepped on the unstable rock
	ortegaEarthquakeWarning	;warned of Ortega earthquake
	badWordCount			;times said profanity in parser
	motivatorKnown			;ego knows the ship needs a warp drive motivator
	global187				;unused
	certainDeath			;message to play when ego dies
	teleportRoom			;room to teleport to in debug
	deathView				;death icon view
	deathLoop				;death icon loop
	deathCel				;death icon cel
	saveDisabled			;if TRUE, can't save
	ladderOnGround			;ladder fell on the ground
	global195				;unused
	global196				;unused
	dead					;if TRUE, bring up death message
	thisTime				;current system time
	oldSysTime				;previous system time
	debugging				;debug enabled
	grabberX				;x coord of grabber
	grabberY				;y coord of grabber
	sittingInCockpit		;ego is sitting in the pilot's seat
	global204				;unused
	global205
	shieldActivated			;if TRUE< the ship's shield is on
	global206
	global207
	global208
	global209
	shipLocation
	global211
	global212
	scanningSector
	currentSector =  75
	global215
	global216
	selectedSector
	global218
	global219
	global220
	global221
	global222
	global223
	global224
	global225
	gameSeconds				;elapsed seconds
	gameMinutes				;elapsed minutes
	gameHours				;elapsed hour
	global229				;unused
	oldCursor				;saved cursor
	enteredMallard			;has entered the Aluminum Mallard
	elmoAtDesk				;Elmo is at his desk
	scumSoftAlerted			;ScumSoft has been alerted to an intruder
	global234				;unused
	global235				;unused
	shadowDroidX			;x coord of droid shadow
	shadowDroidY			;y coord of droid shadow
	bridgeExtended			;bridge has been extended
	enteredConveyorBucket	;ego entered the conveyor bucket
	global240				;unused
	jumpedOntoRailing		;ego grabbed the railing
	scumSoftAnnouncement	;ScumSoft made its announcement
	global243
	monolithBurgerBill		;total owed for Monolith Burger order
	gGEgoX_3
	gGEgoY_2
	global247
	global248
	mealHasDecoderRing		;does the meal have a decoder ring?
	global250
	vaporCalcCued			;VaporCalc cued to appear
	calcOn		;VaporCalc is on screen
	ortegaWorkersLeft		;Ortega workers have left the planet
	lookedAtForceBeam		;ego saw the force beam
	global255
	forceBeamDestroyed		;Force beam has been destroyed. Pestulon can now be visited.
	shakeTimer				;time between screen shakes
	deathMessage			;buffer for death message
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
	deathTitle			;buffer for death title			
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
	theInvItem			;current inventory item
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
	astroChickenPlays	;number of times you played Astro Chicken
	astroChickenScore	;top score on Astro Chicken
	global591
	global592
	ortegaPostBeamRooms	;number of room transitions after Ortega's force beam is destroyed.
						; if you take too long to leave the planet, you'll be killed by the earthquakes.
	global594
	global595			;appears to be unused
	climbedDownBattlebot
	enteredScumSoftHQ
	numColors			;number of colors supported by driver
	global599			;appears to be unused
)
(procedure (NormalEgo theLoop theView)
	;normalizes ego's animation
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1)
			(ego view: theView)
		)
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
		ignoreActors: FALSE
	)
)

(procedure (HandsOff)
	;Disable ego control
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= isHandsOff TRUE)
)

(procedure (HandsOn)
	;Enable ego control
	(User canControl: TRUE canInput: TRUE)
	(= isHandsOff FALSE)
)

(procedure (HaveMem howMuch)
	;check how much heap is available	
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (NotClose)
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
	;re-animate the cast without cycling	
	(Animate (cast elements?) FALSE)
)

(procedure (ChangeSoundState soundObj theLoop)
	;seems to change a sound object's loop and state
	(soundObj loop: theLoop changeState:)
)

(procedure (cls)
	;Clear modeless dialog from the screen	
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (InRoom what where)
	(return
		(==
			((inventory at: what) owner?)
			(if (== argc 1) curRoomNum else where)
		)
	)
)

(procedure (PutInRoom what where)
	((inventory at: what)
		owner: (if (== argc 1) curRoomNum else where)
	)
)

(procedure (IsObjectOnControl obj event)
	;check if an object is on a specific control
	(if (< argc 2)
		(= event (| keyDown mouseDown))
	)
	(OnControl
		(- (obj x?) event)
		(- (obj y?) event)
		(+ (obj x?) event)
		(+ (obj y?) event)
	)
)

(procedure (EgoDead theView theLoop theCel theDeath)
	;ego dies
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
	;draw the status line
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
		;set up the game's objects and globals
		(= numColors (kernel_112 GDetect)) ;EO: kernel function 112 should be Graph, but vocab.999 does not have that
		(= version {1.0V - 8/17/89})
		(= saveDisabled TRUE)
		(longSong owner: self init:)
		(= theMusic longSong)
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
	
	(method (doit &tmp haveMouse)
		(if
			(and
				(!= curRoomNum 890)
				(!= curRoomNum 1)
				(!= curRoomNum 155)
			)
			(= haveMouse (HaveMouse))
			(if (not global592)
				(cond 
					(inCartoon
						(= oldCursor 69)
					)
					((== (User controls?) FALSE)
						(= haveMouse TRUE)
						(= oldCursor waitCursor)
					)
					(else
						(= oldCursor normalCursor)
					)
				)
				(if (!= theCursor oldCursor)
					(self setCursor: oldCursor haveMouse)
				)
			)
		)
		(if (== vaporCalcCued TRUE)
			(= vaporCalcCued FALSE)
			(= calcOn TRUE)
			(calc init:)
		)
		(if dead
		;if ego died, bring up the death handler
		; (which was removed from this demo)			
			(= inCartoon FALSE)
			(theMusic
				number: (Random 23 24)
				loop: 1
				priority: 500
				play:
			)
		else	;end of deaths
			(= global219 0)
			(= global223 0)
			;let the game's clock tick
			(if (!= (= thisTime (GetTime SYSTIME1)) oldSysTime)
				(= oldSysTime thisTime)
				(+= gameSeconds 1)
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
		;clean up after a room change
		(DisposeScript AVOIDER)
		(if debugOn
			(= debugOn FALSE)
			(SetDebug)
		)
		;if memory is fragmented, bring up a warning and the internal debugger
		(if
			(and
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				debugging
				(Print 0 1
					#button {Debug} 1
				)
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp item saveBits evt [temp3 2] evtX evtY evtMod temp8 [str 50])
		(if (event claimed?) (return))
		(super handleEvent: event)
		(if (== calcOn TRUE)
			(event claimed: TRUE)
			(= calcOn FALSE)
			(calc dispose:)
		)
		(switch (event type?)
			(mouseDown
				(if debugging
					(= evtX (event x?))
					(= evtY (event y?))
					(cond 
						((== (= evtMod (event modifiers?)) 10)
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
										((< 300 evtX)
											(- evtX 40)
										)
										(else
											(- evtX 20)
										)
									)
									(if (< evtY 16)
										evtY
									else
										(- evtY 6)
									)
									#font 999
									#dispose
								)
							)
							(while (!= mouseUp ((= evt (Event new:)) type?))
								(evt dispose:)
							)
							(saveBits dispose:)
							(evt dispose:)
						)
						((& evtMod ctrlDown)
							(event claimed: TRUE)
							(while (!= mouseUp ((= evt (Event new:)) type?))
								((User alterEgo?)
									posn: (evt x?) (- (evt y?) 10)
									setMotion: 0
								)
								(Animate (cast elements?) FALSE)
								(evt dispose:)
							)
							(evt dispose:)
						)
						((& evtMod altDown)
							(event claimed: TRUE)
							((User alterEgo?) showSelf:)
						)
					)
				)
			)
			(keyDown
				(if (not debugging) (return))
				(switch (event message?)
					(`@z
						(if debugging
							(event claimed: TRUE)
							(= quit TRUE)
						)
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
							#icon (ego view?) (ego loop?) (ego cel?)
						)
					)
					(`@h
						(theGame showMem:)
						(event claimed: TRUE)
					)
					(`@r
						(Print (Format @str 0 3 curRoomNum))
					)
					(`@v
						(Show VMAP)
					)
					(`@p
						(Show PMAP)
					)
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
						(while (== 0 ((= evt (Event new: allEvents)) type?))
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
		;don't recognize a word
		(Print (Format @str 0 4 word))
	)
	
	(method (syntaxFail)
		;can't parse input
		(Print 0 5)
	)
	
	(method (pragmaFail &tmp [str 100])
		;no response to event
		(Print 0 6)
	)
)

(instance calc of Prop	
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
