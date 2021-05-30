;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
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
	KQ4 0
	smallBase 1
	IsObjectOnControl 2
	Face 3
	timer1 4
	timer2 5
	timer3 6
	NormalEgo 7
	HandsOff 8
	HandsOn 9
	NotifyScript 10
	SetEgoView 11
	HaveMem 12
	NotClose 13
	AlreadyTook 14
	SeeNothing 15
	CantDo 16
	DontHave 17
	RedrawCast 18
	proc0_19 19
	LanternIsOn 20
	cls 21
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
	version =  {newInvItem}
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
	;62-99 are unused
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
	isNightTime
	isIndoors
	dwarfHouseState
	global103
	global104
	currentStatus
	global106
	global107
	oldHorizon
	gamePhase
	frogPrinceState
	global111
	choppedScaryTree
	timesUsedShovel
	oldCrownState
	cleaningUpHouse
	fishermanState
	global117
	whereIsMinstrel
	minstrel
	timedMessage
	ogre
	dwarfBouncesEgo
	unicornState
	unicornRoom
	unicorn
	trollAttacks
	dead		;if TRUE, bring up death message
	shakespeareBookKnown
	lookedAtStrangeMansionPainting
	hiddenMansionLatchKnown
	mansionSecretDoorOpen
	laidDownBoard
	ghostRoomNum
	mansionPhase
	ghostWandering
	global136
	global137
	gNewPropX
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
	thisTime
	oldSysTime
	gameSeconds
	gameMinutes
	gameHours
	global161
	global162
	ogressIsHome
	oldEgoScript
	ogreState
	ogreCameHome
	enteredOgreKitchen
	global168
	lolotteAlive
	ropeLadderLowered
	global171
	pan
	ogreY
	cryptDoorState
	henchChasingEgo
	ateSoup
	dwarfTableClean
	global178
	global179
	gNewProp
	oldEgoView
	gotItem
	swallowedByWhale
	timesTalkedToMinstrel
	oldEgoBaseSetter
	numZombies
	global187
	nightRoom
	noWearCrown
	global190
	shotUnicorn
	global192
	playedOrgan
	global194
	logEntries
	writingLog
	minuteMetFisherman
	secondMetFisherman
	hourMetFisherman
	woreFrogCrown
	putBoardOverSwamp
	debugMenu
	crow
	isHandsOff
	inCutscene
	talkedToFishermanWife
	witchesTossedScarab
	scarabX
	scarabY
	ogreFrontDoorOpen
	hourLastMetPan
	minutesLastMetPan
	hourLastMetMinstrel
	minutesLastMetMinstrel
	debugging
	global216
	blewWhistle
	gotPandorasBox
	global219
	unlockedLolotteDoor
	global221
	machineSpeed
	howFast
	inCinematic		;this is set in the intro and ending cinematics
	ghostHaunts
	introScript
	lolotteDoorOpen
	str				;all subsequent globals are part of this array
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
)
(procedure (IsObjectOnControl obj ctrl)
	(if (< argc 2) (= ctrl 5))
	(switch (obj loop?)
		(0
			(OnControl
				CMAP
				(obj x?)
				(obj y?)
				(+ (obj x?) ctrl)
				(+ (obj y?) 1)
			)
			(return)
		)
		(1
			(OnControl
				CMAP
				(- (obj x?) ctrl)
				(obj y?)
				(obj x?)
				(+ (obj y?) 1)
			)
			(return)
		)
		(2
			(OnControl
				CMAP
				(obj x?)
				(obj y?)
				(+ (obj x?) 1)
				(+ (obj y?) ctrl)
			)
			(return)
		)
		(3
			(OnControl
				CMAP
				(obj x?)
				(- (obj y?) ctrl)
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
	(= oldCrownState noWearCrown)
	(= noWearCrown TRUE)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(ego setMotion: 0)
	(= isHandsOff FALSE)
	(= noWearCrown oldCrownState)
)

(procedure (NotifyScript script &tmp i)
	(= i (ScriptID script))
	(i notify: &rest)
)

(procedure (SetEgoView)
	(return (if (== (ego view?) 2) else (== (ego view?) 4)))
)

(procedure (HaveMem memSize)
	(return (> (MemoryInfo FreeHeap) memSize))
)

(procedure (NotClose)
	(Print 0 130)
)

(procedure (AlreadyTook)
	(Print 0 131)
)

(procedure (SeeNothing)
	(Print 0 132)
)

(procedure (CantDo)
	(Print 0 133)
)

(procedure (DontHave)
	(Print 0 134)
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (proc0_19 param1 param2)
	(param1 loop: param2 changeState:)
)

(procedure (LanternIsOn newState &tmp oldState)
	(= oldState (liteState state?))
	(if argc (liteState changeState: newState))
	(return oldState)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(class newInvItem of InvItem
	
	(method (showSelf)
		(Print 0 0
			#title name
			#icon view loop cel
		)
	)
)

(instance statusCode of Code

	(method (doit strg)
		(Format strg 0 1 score possibleScore
			{ KQ\n__The Perils of Rosella}
		)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance getItemMusic of Sound
	(properties
		number 48
		priority 2
		owner -1
	)
)

(instance tweet of Sound
	(properties
		number 76
		priority -1
		owner -1
	)
)

(instance KQ4 of Game

	(method (init)
		(= systemWindow SysWindow)
		(super init:)
		(= ego egoObj)
		(User alterEgo: ego)
		(= aniThreshold 7)
		(= global221 0)
		(Inventory
			add:
				Silver_Flute
				Diamond_Pouch
				Talisman
				Lantern__unlit_
				Pandora_s_Box
				Gold_Ball
				Witches__Glass_Eye
				Obsidian_Scarab
				Peacock_Feather
				Lute
				Small_Crown
				Frog
				Silver_Baby_Rattle
				Gold_Coins
				Cupid_s_Bow
				Shovel
				Axe
				Fishing_Pole
				Shakespeare_Book
				Worm
				Skeleton_Key
				Golden_Bridle
				Board
				Bone
				Dead_Fish
				Magic_Fruit
				Sheet_Music
				Silver_Whistle
				Locket
				Medal
				Toy_Horse
				Glass_Bottle
				Gold_Key
				Magic_Hen
				Rose
				Note
		)
		(= showStyle HSHUTTER)
		(= userFont (= bigFont USERFONT))
		(= lolotteAlive TRUE)
		(= version {1.006.004})
		(liteState init: Lantern__unlit_)
		(TheMenuBar init:)
		(getItemMusic init:)
		(tweet init:)
		(= whereIsMinstrel (Random 1 3))
		(User canInput: FALSE canControl: FALSE echo: SPACEBAR)
		(= inCutscene TRUE)
		(StatusLine code: statusCode)
		(= possibleScore 230)
		(= fishermanState fisherGoneFishing)
		(= oldSysTime 0)
		(= currentStatus egoNormal)
		(= gameHours 8)
		(ego view: 2 x: 100 y: 120)
		(if (GameIsRestarting)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(self newRoom: 99)
			(= userFont bigFont)
		else
		(self newRoom: 700) ;Set to 700 to skip the CP -- Kawa
		;(self newRoom: 701)
		)
	)
	
	(method (doit)
		(cond 
			((and inCinematic (!= global221 2))
				(= global221 2)
				(self setCursor: 666 (HaveMouse))
			)
			((and (== global221 2) (not inCinematic))
				(self setCursor: normalCursor (HaveMouse))
				(= global221 0)
			)
			((and (== (User controls?) FALSE) (== global221 0))
				(= global221 1)
				(self setCursor: normalCursor TRUE)
			)
			((and (== global221 1) (== (User controls?) 1))
				(self setCursor: normalCursor (HaveMouse))
				(= global221 0)
			)
		)
		(if dead
			(sounds eachElementDo: #dispose)
			((Sound new:) number: 49 play:)
			(self setCursor: normalCursor (HaveMouse))
			(if (HaveMem DeathSize)
				(repeat
					(switch
						(Print 0 2
							#icon 100 0 0
							#mode teJustCenter
							#title {Roberta says:}
							#button {__Restore__} 1
							#button {__Restart__} 2
							#button {___Quit___} 3
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
			else
				(Print 0 3)
				(theGame restart:)
			)
		else
			(if gotItem
				(= gotItem FALSE)
				(getItemMusic play:)
			)
			(if (!= (= thisTime (GetTime TRUE)) oldSysTime)
				(= oldSysTime thisTime)
				(if (>= (= gameSeconds (+ gameSeconds 4)) 60)
					(++ gameMinutes)
					(-= gameSeconds 60)
					(if (and (== gameHours 31) (== gameMinutes 59))
						(curRoom setScript: (ScriptID 302 0))	;Time Over
					)
					(if (and (== gameHours 20) (== (mod gameMinutes 15) 0))
						(Print 0 4)
					)
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
		(= userFont bigFont)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(SetMenu soundI p_text
			(if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(super replay:)
	)
	
	(method (newRoom roomNum)
		(if
			(or
				isHandsOff
				(and (not inCutscene) (== (User canControl:) FALSE))
			)
			(return)
		)
		;is it time to turn to night?
		(if (and (== isNightTime FALSE) (== isIndoors 0))
			(if
				(and
					(not (if (< 30 roomNum) (< roomNum 77)))
					(< roomNum 300)
					(or
						(and (< 20 gameHours) (< gameHours 30))
						(and
							(>= gamePhase getPandoraBox)
							(ego has: iObsidianScarab)
							(ego has: iMagicFruit)
							(< gameHours 30)
						)
					)
				)
				(= isNightTime TRUE)
				(= nightRoom roomNum)
				(if (< gameHours 21)
					(= gameHours 21)
					(= gameMinutes 0)
				)
				(= roomNum 697)
			)
		)
		(super newRoom: roomNum)
	)
	
	(method (startRoom roomNum &tmp region)
		(if (and global216 (HaveMem 1200))
			(= global216 0)
			((= debugMenu (ScriptID 801)) init:)
		)
		(DisposeScript AVOIDER)
		(if debugOn
			(= debugOn FALSE)
			(SetDebug)
		)
		(if
			(= region
				(switch roomNum
					(68 HAUNTED_HOUSE)
					(120 INTRO)
					(54 DWARF_HOUSE)
					(49 OGRE_HOUSE)
					(92 LOLOTTE)
					(71 TROLL_CAVE)
					(73 TROLL_CAVE)
					(55 DWARF_MINE)
					(else  0)
				)
			)
			((ScriptID region) init:)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2 temp3 [temp4 50])
		(asm
			lag      debugging
			bnt      code_07e7
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_07e7
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_07e7
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      3
			and     
			bnt      code_0746
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    7
			pushi    5
			lea      @temp4
			push    
			pushi    0
			pushi    10
			pushi    #x
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			callk    Format,  10
			push    
			pushi    67
			pushi    150
			pushi    100
			pushi    33
			pushi    999
			pushi    88
			calle    Print,  14
			sat      temp2
code_0718:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp3
			send     4
			ne?     
			bnt      code_0735
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			jmp      code_0718
code_0735:
			pushi    #dispose
			pushi    0
			lat      temp2
			send     4
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			jmp      code_07da
code_0746:
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      4
			and     
			bnt      code_07b7
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_075c:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp3
			send     4
			ne?     
			bnt      code_07ad
			pushi    197
			pushi    #-info-
			pushi    #x
			pushi    0
			lat      temp3
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp3
			send     4
			push    
			ldi      10
			sub     
			push    
			pushi    211
			pushi    1
			pushi    0
			pushi    #alterEgo
			pushi    0
			class    User
			send     4
			send     14
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    0
			callk    Animate,  4
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			jmp      code_075c
code_07ad:
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
			jmp      code_07da
code_07b7:
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      8
			and     
			bnt      code_07da
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #showSelf
			pushi    0
			pushi    #alterEgo
			pushi    0
			class    User
			send     4
			send     4
code_07da:
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_07e7
			ldi      1
			ret     
code_07e7:
			lsg      curRoomNum
			lag      newRoomNum
			eq?     
			bnt      code_07f7
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Game,  6
code_07f7:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      128
			eq?     
			bnt      code_1bc6
			pushi    1
			lofsa    'get/mem'
			push    
			callk    Said,  2
			bnt      code_0819
			pushi    #showMem
			pushi    0
			lag      theGame
			send     4
code_0819:
			lag      debugging
			bnt      code_08d6
			pushi    1
			lofsa    'enter/night'
			push    
			callk    Said,  2
			bnt      code_08a5
			ldi      1
			sag      ogressIsHome
			ldi      3
			sag      gamePhase
			ldi      1
			sag      mansionSecretDoorOpen
			pushi    #get
			pushi    1
			pushi    3
			lag      ego
			send     6
			pushi    #get
			pushi    1
			pushi    16
			lag      ego
			send     6
			pushi    #get
			pushi    1
			pushi    15
			lag      ego
			send     6
			pushi    #get
			pushi    1
			pushi    25
			lag      ego
			send     6
			pushi    #get
			pushi    1
			pushi    14
			lag      ego
			send     6
			ldi      1
			sag      isNightTime
			pushi    #loop
			pushi    1
			pushi    1
			pushi    7
			pushi    1
			pushi    0
			pushi    #at
			pushi    1
			pushi    14
			class    Inventory
			send     6
			send     12
			ldi      1
			sag      lolotteAlive
			ldi      21
			sag      gameHours
			ldi      1
			sag      gameMinutes
			ldi      5
			sag      frogPrinceState
			ldi      65535
			sag      whereIsMinstrel
			ldi      99
			sag      unicornState
			ldi      99
			sag      unicornRoom
			pushi    2
			pushi    0
			pushi    11
			calle    Print,  4
			jmp      code_08d6
code_08a5:
			pushi    1
			lofsa    'tp'
			push    
			callk    Said,  2
			bnt      code_08d6
			ldi      0
			sag      str
			pushi    7
			lofsa    {TP to:}
			push    
			pushi    67
			pushi    65535
			pushi    20
			pushi    41
			lea      @str
			push    
			pushi    6
			calle    Print,  14
			pushi    1
			lea      @str
			push    
			callk    ReadNumber,  2
			sag      newRoomNum
code_08d6:
			pushi    1
			lofsa    'ass'
			push    
			callk    Said,  2
			bt       code_08f7
			pushi    1
			lofsa    '/ass'
			push    
			callk    Said,  2
			bt       code_08f7
			pushi    1
			lofsa    '//ass'
			push    
			callk    Said,  2
			bnt      code_0902
code_08f7:
			pushi    2
			pushi    0
			pushi    12
			calle    Print,  4
			jmp      code_1bc6
code_0902:
			pushi    1
			lofsa    'blow/whistle'
			push    
			callk    Said,  2
			bnt      code_0935
			pushi    #has
			pushi    1
			pushi    27
			lag      ego
			send     6
			bnt      code_092d
			pushi    #play
			pushi    0
			lofsa    tweet
			send     4
			pushi    2
			pushi    0
			pushi    13
			calle    Print,  4
			jmp      code_1bc6
code_092d:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0935:
			pushi    1
			lofsa    'open,(look<in)/bottle'
			push    
			callk    Said,  2
			bt       code_094b
			pushi    1
			lofsa    'get/letter'
			push    
			callk    Said,  2
			bnt      code_09a0
code_094b:
			pushi    #has
			pushi    1
			pushi    35
			lag      ego
			send     6
			bnt      code_0963
			pushi    2
			pushi    0
			pushi    14
			calle    Print,  4
			jmp      code_1bc6
code_0963:
			pushi    #has
			pushi    1
			pushi    31
			lag      ego
			send     6
			bnt      code_0998
			pushi    2
			pushi    0
			pushi    15
			calle    Print,  4
			pushi    #cel
			pushi    1
			pushi    0
			pushi    #at
			pushi    1
			pushi    31
			class    Inventory
			send     6
			send     6
			ldi      1
			sag      gotItem
			pushi    #get
			pushi    1
			pushi    35
			lag      ego
			send     6
			jmp      code_1bc6
code_0998:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_09a0:
			pushi    1
			lofsa    'drink/bottle'
			push    
			callk    Said,  2
			bnt      code_09cb
			pushi    #has
			pushi    1
			pushi    31
			lag      ego
			send     6
			bnt      code_09c3
			pushi    2
			pushi    0
			pushi    16
			calle    Print,  4
			jmp      code_1bc6
code_09c3:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_09cb:
			pushi    1
			lofsa    'break,hit,chop/branch,forest,arm,branch'
			push    
			callk    Said,  2
			bt       code_09e1
			pushi    1
			lofsa    'swing,wave/ax'
			push    
			callk    Said,  2
			bnt      code_0a01
code_09e1:
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			bnt      code_09f9
			pushi    2
			pushi    0
			pushi    17
			calle    Print,  4
			jmp      code_1bc6
code_09f9:
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_0a01:
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			bnt      code_0a24
			pushi    1
			lofsa    'chop,hit'
			push    
			callk    Said,  2
			bnt      code_0a24
			pushi    2
			pushi    0
			pushi    18
			calle    Print,  4
			jmp      code_1bc6
code_0a24:
			pushi    1
			lofsa    'detach,(get<off)/dress'
			push    
			callk    Said,  2
			bt       code_0a45
			pushi    1
			lofsa    'undress'
			push    
			callk    Said,  2
			bt       code_0a45
			pushi    1
			lofsa    'get/undressed'
			push    
			callk    Said,  2
			bnt      code_0a50
code_0a45:
			pushi    2
			pushi    0
			pushi    19
			calle    Print,  4
			jmp      code_1bc6
code_0a50:
			pushi    1
			lofsa    'play,bounce/ball'
			push    
			callk    Said,  2
			bnt      code_0a7b
			pushi    #has
			pushi    1
			pushi    5
			lag      ego
			send     6
			bnt      code_0a73
			pushi    2
			pushi    0
			pushi    20
			calle    Print,  4
			jmp      code_1bc6
code_0a73:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0a7b:
			pushi    1
			lofsa    'dig'
			push    
			callk    Said,  2
			bnt      code_0aa9
			pushi    #has
			pushi    1
			pushi    15
			lag      ego
			send     6
			bnt      code_0a9e
			pushi    2
			pushi    0
			pushi    17
			calle    Print,  4
			jmp      code_1bc6
code_0a9e:
			pushi    2
			pushi    0
			pushi    21
			calle    Print,  4
			jmp      code_1bc6
code_0aa9:
			pushi    1
			lofsa    'launch'
			push    
			callk    Said,  2
			bnt      code_0b2a
			pushi    #has
			pushi    1
			pushi    14
			lag      ego
			send     6
			not     
			bnt      code_0acd
			pushi    2
			pushi    0
			pushi    22
			calle    Print,  4
			jmp      code_1bc6
code_0acd:
			pushi    #loop
			pushi    0
			pushi    #at
			pushi    1
			pushi    14
			class    Inventory
			send     6
			send     4
			push    
			ldi      2
			ge?     
			bnt      code_0aed
			pushi    2
			pushi    0
			pushi    23
			calle    Print,  4
			jmp      code_1bc6
code_0aed:
			lag      noWearCrown
			not     
			bnt      code_0b22
			pushi    0
			call     SetEgoView,  0
			bnt      code_0b22
			pushi    1
			pushi    850
			call     HaveMem,  2
			bnt      code_0b22
			pushi    #script
			pushi    0
			lag      ego
			send     4
			sag      oldEgoScript
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    305
			pushi    0
			callk    ScriptID,  4
			push    
			lag      ego
			send     6
			jmp      code_1bc6
code_0b22:
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_0b2a:
			pushi    1
			lofsa    'polish>'
			push    
			callk    Said,  2
			bnt      code_0be8
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			not     
			bnt      code_0b64
			pushi    1
			lofsa    '/me'
			push    
			callk    Said,  2
			bnt      code_0b59
			pushi    2
			pushi    0
			pushi    24
			calle    Print,  4
			jmp      code_0bdd
code_0b59:
			pushi    2
			pushi    0
			pushi    25
			calle    Print,  4
			jmp      code_0bdd
code_0b64:
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			not     
			bnt      code_0b82
			pushi    0
			call     DontHave,  0
			jmp      code_0bdd
code_0b82:
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			dup     
			ldi      2
			eq?     
			bnt      code_0b9e
			pushi    2
			pushi    0
			pushi    26
			calle    Print,  4
			jmp      code_0bdc
code_0b9e:
			dup     
			ldi      3
			eq?     
			bnt      code_0bb0
			pushi    2
			pushi    0
			pushi    27
			calle    Print,  4
			jmp      code_0bdc
code_0bb0:
			dup     
			ldi      6
			eq?     
			bnt      code_0bc2
			pushi    2
			pushi    0
			pushi    28
			calle    Print,  4
			jmp      code_0bdc
code_0bc2:
			dup     
			ldi      7
			eq?     
			bnt      code_0bd4
			pushi    2
			pushi    0
			pushi    29
			calle    Print,  4
			jmp      code_0bdc
code_0bd4:
			pushi    2
			pushi    0
			pushi    30
			calle    Print,  4
code_0bdc:
			toss    
code_0bdd:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_1bc6
code_0be8:
			pushi    1
			lofsa    'kiss>'
			push    
			callk    Said,  2
			bnt      code_0c5d
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lat      temp1
			not     
			bnt      code_0c16
			pushi    2
			pushi    0
			pushi    31
			calle    Print,  4
			jmp      code_1bc6
code_0c16:
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			not     
			bnt      code_0c37
			pushi    2
			pushi    0
			pushi    32
			calle    Print,  4
			jmp      code_1bc6
code_0c37:
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			ldi      2
			eq?     
			bnt      code_0c52
			pushi    2
			pushi    0
			pushi    33
			calle    Print,  4
			jmp      code_1bc6
code_0c52:
			pushi    2
			pushi    0
			pushi    31
			calle    Print,  4
			jmp      code_1bc6
code_0c5d:
			pushi    1
			lofsa    'wish>'
			push    
			callk    Said,  2
			bnt      code_0ccd
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			not     
			bnt      code_0c81
			pushi    2
			pushi    0
			pushi    34
			calle    Print,  4
			jmp      code_0cc2
code_0c81:
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			not     
			bnt      code_0c9f
			pushi    0
			call     CantDo,  0
			jmp      code_0cc2
code_0c9f:
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			ldi      2
			eq?     
			bnt      code_0cba
			pushi    2
			pushi    0
			pushi    35
			calle    Print,  4
			jmp      code_0cc2
code_0cba:
			pushi    2
			pushi    0
			pushi    36
			calle    Print,  4
code_0cc2:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_1bc6
code_0ccd:
			pushi    1
			lofsa    'bait/hook,pole'
			push    
			callk    Said,  2
			bt       code_0ce3
			pushi    1
			lofsa    'place/earthworm/hook,pole'
			push    
			callk    Said,  2
			bnt      code_0d3d
code_0ce3:
			pushi    #has
			pushi    1
			pushi    19
			lag      ego
			send     6
			bnt      code_0d32
			pushi    #has
			pushi    1
			pushi    17
			lag      ego
			send     6
			bnt      code_0d32
			pushi    2
			pushi    0
			pushi    37
			calle    Print,  4
			pushi    #moveTo
			pushi    1
			pushi    666
			pushi    #at
			pushi    1
			pushi    19
			class    Inventory
			send     6
			send     6
			pushi    #loop
			pushi    1
			pushi    1
			pushi    #at
			pushi    1
			pushi    17
			class    Inventory
			send     6
			send     6
			pushi    #changeScore
			pushi    1
			pushi    1
			lag      theGame
			send     6
			jmp      code_1bc6
code_0d32:
			pushi    2
			pushi    0
			pushi    38
			calle    Print,  4
			jmp      code_1bc6
code_0d3d:
			pushi    #has
			pushi    1
			pushi    24
			lag      ego
			send     6
			bnt      code_0d8a
			pushi    1
			lofsa    '/fish>'
			push    
			callk    Said,  2
			bnt      code_0d8a
			pushi    1
			lofsa    'smell'
			push    
			callk    Said,  2
			bnt      code_0d6d
			pushi    2
			pushi    0
			pushi    39
			calle    Print,  4
			ldi      1
			jmp      code_0d82
code_0d6d:
			pushi    1
			lofsa    'eat'
			push    
			callk    Said,  2
			bnt      code_0d8a
			pushi    2
			pushi    0
			pushi    40
			calle    Print,  4
			ldi      1
code_0d82:
			bnt      code_0d8a
			ldi      1
			jmp      code_1bc6
code_0d8a:
			pushi    1
			lofsa    'eat/earthworm'
			push    
			callk    Said,  2
			bnt      code_0db5
			pushi    #has
			pushi    1
			pushi    19
			lag      ego
			send     6
			bnt      code_0dad
			pushi    2
			pushi    0
			pushi    41
			calle    Print,  4
			jmp      code_1bc6
code_0dad:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0db5:
			pushi    1
			lofsa    'eat/bone'
			push    
			callk    Said,  2
			bnt      code_0de0
			pushi    #has
			pushi    1
			pushi    23
			lag      ego
			send     6
			bnt      code_0dd8
			pushi    2
			pushi    0
			pushi    42
			calle    Print,  4
			jmp      code_1bc6
code_0dd8:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0de0:
			pushi    1
			lofsa    'eat/fruit'
			push    
			callk    Said,  2
			bnt      code_0e22
			pushi    #has
			pushi    1
			pushi    25
			lag      ego
			send     6
			bnt      code_0e1a
			pushi    2
			pushi    0
			pushi    43
			calle    Print,  4
			pushi    #put
			pushi    2
			pushi    25
			pushi    999
			lag      ego
			send     8
			pushi    #changeScore
			pushi    1
			pushi    65526
			lag      theGame
			send     6
			jmp      code_1bc6
code_0e1a:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0e22:
			pushi    #has
			pushi    1
			pushi    28
			lag      ego
			send     6
			bnt      code_0e45
			pushi    1
			lofsa    'dennis,(place<on)/locket'
			push    
			callk    Said,  2
			bnt      code_0e45
			pushi    2
			pushi    0
			pushi    44
			calle    Print,  4
			jmp      code_1bc6
code_0e45:
			pushi    #has
			pushi    1
			pushi    29
			lag      ego
			send     6
			bnt      code_0e68
			pushi    1
			lofsa    'dennis,(place<on)/badge'
			push    
			callk    Said,  2
			bnt      code_0e68
			pushi    2
			pushi    0
			pushi    45
			calle    Print,  4
			jmp      code_1bc6
code_0e68:
			pushi    1
			lofsa    'dennis,(place<on)/amulet'
			push    
			callk    Said,  2
			bnt      code_0e92
			pushi    #has
			pushi    1
			pushi    2
			lag      ego
			send     6
			bnt      code_0e8a
			pushi    2
			pushi    0
			pushi    46
			calle    Print,  4
			jmp      code_1bc6
code_0e8a:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0e92:
			pushi    1
			lofsa    'dennis,(place<on)/crown'
			push    
			callk    Said,  2
			bnt      code_0efa
			pushi    #has
			pushi    1
			pushi    10
			lag      ego
			send     6
			not     
			bnt      code_0eb3
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0eb3:
			lag      noWearCrown
			bnt      code_0ec3
			pushi    2
			pushi    0
			pushi    47
			calle    Print,  4
			jmp      code_1bc6
code_0ec3:
			pushi    1
			pushi    1400
			call     HaveMem,  2
			bnt      code_0ef2
			pushi    0
			call     SetEgoView,  0
			bnt      code_0ef2
			pushi    #script
			pushi    0
			lag      ego
			send     4
			sag      oldEgoScript
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    301
			pushi    0
			callk    ScriptID,  4
			push    
			lag      ego
			send     6
			jmp      code_1bc6
code_0ef2:
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_0efa:
			pushi    1
			lofsa    'read/letter'
			push    
			callk    Said,  2
			bnt      code_0f3e
			pushi    #has
			pushi    1
			pushi    35
			lag      ego
			send     6
			not     
			bnt      code_0f1b
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0f1b:
			pushi    1
			pushi    2000
			call     HaveMem,  2
			bnt      code_0f36
			pushi    #cue
			pushi    0
			pushi    2
			pushi    306
			pushi    1
			callk    ScriptID,  4
			send     4
			jmp      code_1bc6
code_0f36:
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_0f3e:
			pushi    1
			lofsa    'play/flute'
			push    
			callk    Said,  2
			bnt      code_0fa5
			pushi    #has
			pushi    1
			pushi    0
			lag      ego
			send     6
			not     
			bnt      code_0f5e
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0f5e:
			lag      noWearCrown
			bnt      code_0f6e
			pushi    2
			pushi    0
			pushi    48
			calle    Print,  4
			jmp      code_1bc6
code_0f6e:
			pushi    0
			call     SetEgoView,  0
			bnt      code_0f9d
			pushi    1
			pushi    800
			call     HaveMem,  2
			bnt      code_0f9d
			pushi    #script
			pushi    0
			lag      ego
			send     4
			sag      oldEgoScript
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    304
			pushi    0
			callk    ScriptID,  4
			push    
			lag      ego
			send     6
			jmp      code_1bc6
code_0f9d:
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_0fa5:
			pushi    1
			lofsa    'play/lute'
			push    
			callk    Said,  2
			bnt      code_100a
			pushi    #has
			pushi    1
			pushi    9
			lag      ego
			send     6
			not     
			bnt      code_0fc6
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_0fc6:
			lag      noWearCrown
			bnt      code_0fd3
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_0fd3:
			pushi    0
			call     SetEgoView,  0
			bnt      code_1002
			pushi    1
			pushi    800
			call     HaveMem,  2
			bnt      code_1002
			pushi    #script
			pushi    0
			lag      ego
			send     4
			sag      oldEgoScript
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    303
			pushi    0
			callk    ScriptID,  4
			push    
			lag      ego
			send     6
			jmp      code_1bc6
code_1002:
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_100a:
			pushi    1
			lofsa    'play,shake/rattle'
			push    
			callk    Said,  2
			bnt      code_1035
			pushi    #has
			pushi    1
			pushi    12
			lag      ego
			send     6
			bnt      code_102d
			pushi    2
			pushi    0
			pushi    49
			calle    Print,  4
			jmp      code_1bc6
code_102d:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_1035:
			pushi    1
			lofsa    'unlatch/door'
			push    
			callk    Said,  2
			bnt      code_1070
			pushi    #has
			pushi    1
			pushi    20
			lag      ego
			send     6
			bt       code_105a
			pushi    #has
			pushi    1
			pushi    32
			lag      ego
			send     6
			bnt      code_1065
code_105a:
			pushi    2
			pushi    0
			pushi    50
			calle    Print,  4
			jmp      code_1bc6
code_1065:
			pushi    2
			pushi    0
			pushi    51
			calle    Print,  4
			jmp      code_1bc6
code_1070:
			pushi    1
			lofsa    '/music>'
			push    
			callk    Said,  2
			bnt      code_10c5
			pushi    1
			lofsa    'read,open'
			push    
			callk    Said,  2
			bnt      code_10a8
			pushi    #has
			pushi    1
			pushi    26
			lag      ego
			send     6
			bnt      code_109e
			pushi    2
			pushi    0
			pushi    52
			calle    Print,  4
			jmp      code_10a3
code_109e:
			pushi    0
			call     DontHave,  0
code_10a3:
			ldi      1
			jmp      code_10bd
code_10a8:
			pushi    1
			lofsa    'play'
			push    
			callk    Said,  2
			bnt      code_10c5
			pushi    2
			pushi    0
			pushi    53
			calle    Print,  4
			ldi      1
code_10bd:
			bnt      code_10c5
			ldi      1
			jmp      code_1bc6
code_10c5:
			pushi    #has
			pushi    1
			pushi    30
			lag      ego
			send     6
			bnt      code_10f0
			pushi    1
			lofsa    'play/horse'
			push    
			callk    Said,  2
			bnt      code_10f0
			pushi    2
			pushi    0
			pushi    54
			calle    Print,  4
			pushi    2
			pushi    0
			pushi    55
			calle    Print,  4
			jmp      code_1bc6
code_10f0:
			pushi    1
			lofsa    '/book>'
			push    
			callk    Said,  2
			bnt      code_119b
			pushi    1
			lofsa    'close'
			push    
			callk    Said,  2
			bnt      code_1140
			pushi    #has
			pushi    1
			pushi    18
			lag      ego
			send     6
			not     
			bnt      code_111f
			pushi    2
			pushi    0
			pushi    56
			calle    Print,  4
			jmp      code_113b
code_111f:
			lag      global154
			bnt      code_1133
			pushi    2
			pushi    0
			pushi    46
			calle    Print,  4
			ldi      0
			sag      global154
			jmp      code_113b
code_1133:
			pushi    2
			pushi    0
			pushi    57
			calle    Print,  4
code_113b:
			ldi      1
			jmp      code_1193
code_1140:
			pushi    1
			lofsa    'read'
			push    
			callk    Said,  2
			bnt      code_1191
			pushi    #has
			pushi    1
			pushi    18
			lag      ego
			send     6
			not     
			bnt      code_1164
			pushi    2
			pushi    0
			pushi    58
			calle    Print,  4
			jmp      code_118c
code_1164:
			pushi    1
			pushi    2000
			call     HaveMem,  2
			bnt      code_1187
			pushi    #changeState
			pushi    1
			pushi    2
			pushi    1
			pushi    30
			callk    Random,  4
			push    
			pushi    2
			pushi    306
			pushi    0
			callk    ScriptID,  4
			send     6
			jmp      code_118c
code_1187:
			pushi    0
			call     CantDo,  0
code_118c:
			ldi      1
			jmp      code_1193
code_1191:
			ldi      0
code_1193:
			bnt      code_119b
			ldi      1
			jmp      code_1bc6
code_119b:
			pushi    1
			lofsa    'smell/rose'
			push    
			callk    Said,  2
			bnt      code_11c6
			pushi    #has
			pushi    1
			pushi    34
			lag      ego
			send     6
			bnt      code_11be
			pushi    2
			pushi    0
			pushi    59
			calle    Print,  4
			jmp      code_1bc6
code_11be:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_11c6:
			pushi    1
			lofsa    'get,detach/thorn'
			push    
			callk    Said,  2
			bnt      code_11f1
			pushi    #has
			pushi    1
			pushi    34
			lag      ego
			send     6
			bnt      code_11e9
			pushi    2
			pushi    0
			pushi    60
			calle    Print,  4
			jmp      code_1bc6
code_11e9:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_11f1:
			pushi    1
			lofsa    'lay[/!*]'
			push    
			callk    Said,  2
			bt       code_1212
			pushi    1
			lofsa    'lay,get,rob/egg'
			push    
			callk    Said,  2
			bt       code_1212
			pushi    1
			lofsa    'command/chicken'
			push    
			callk    Said,  2
			bnt      code_1232
code_1212:
			pushi    #has
			pushi    1
			pushi    33
			lag      ego
			send     6
			bnt      code_122a
			pushi    2
			pushi    0
			pushi    61
			calle    Print,  4
			jmp      code_1bc6
code_122a:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_1232:
			pushi    1
			lofsa    'converse/chicken'
			push    
			callk    Said,  2
			bnt      code_126b
			pushi    #has
			pushi    1
			pushi    33
			lag      ego
			send     6
			bnt      code_1263
			pushi    8
			pushi    0
			pushi    62
			pushi    80
			lofsa    {Magic Hen}
			push    
			pushi    82
			pushi    431
			pushi    0
			pushi    0
			calle    Print,  16
			jmp      code_1bc6
code_1263:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_126b:
			pushi    1
			lofsa    'eat/chicken'
			push    
			callk    Said,  2
			bnt      code_1296
			pushi    #has
			pushi    1
			pushi    33
			lag      ego
			send     6
			bnt      code_128e
			pushi    2
			pushi    0
			pushi    63
			calle    Print,  4
			jmp      code_1bc6
code_128e:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_1296:
			pushi    1
			lofsa    'eat'
			push    
			callk    Said,  2
			bnt      code_12ac
			pushi    2
			pushi    0
			pushi    64
			calle    Print,  4
			jmp      code_1bc6
code_12ac:
			pushi    1
			lofsa    '/chandelier,lantern[<oil]>'
			push    
			callk    Said,  2
			bnt      code_1347
			pushi    1
			lofsa    '>,ignite,(turn<on)'
			push    
			callk    Said,  2
			bnt      code_130c
			pushi    #has
			pushi    1
			pushi    3
			lag      ego
			send     6
			not     
			bnt      code_12db
			pushi    2
			pushi    0
			pushi    65
			calle    Print,  4
			jmp      code_1307
code_12db:
			pushi    0
			call     LanternIsOn,  0
			bnt      code_12ee
			pushi    2
			pushi    0
			pushi    66
			calle    Print,  4
			jmp      code_1307
code_12ee:
			pushi    0
			call     SetEgoView,  0
			bnt      code_12ff
			pushi    1
			pushi    1
			call     LanternIsOn,  2
			jmp      code_1307
code_12ff:
			pushi    2
			pushi    0
			pushi    67
			calle    Print,  4
code_1307:
			ldi      1
			jmp      code_133f
code_130c:
			pushi    1
			lofsa    'extinguish,(turn<off)'
			push    
			callk    Said,  2
			bnt      code_1347
			pushi    #has
			pushi    1
			pushi    3
			lag      ego
			send     6
			bnt      code_133d
			pushi    0
			call     LanternIsOn,  0
			bnt      code_1335
			pushi    1
			pushi    0
			call     LanternIsOn,  2
			jmp      code_133d
code_1335:
			pushi    2
			pushi    0
			pushi    68
			calle    Print,  4
code_133d:
			ldi      1
code_133f:
			bnt      code_1347
			ldi      1
			jmp      code_1bc6
code_1347:
			pushi    #has
			pushi    1
			pushi    6
			lag      ego
			send     6
			bnt      code_1394
			pushi    1
			lofsa    '/eye>'
			push    
			callk    Said,  2
			bnt      code_1394
			pushi    1
			lofsa    'look<through,in'
			push    
			callk    Said,  2
			bnt      code_1377
			pushi    2
			pushi    0
			pushi    69
			calle    Print,  4
			ldi      1
			jmp      code_138c
code_1377:
			pushi    1
			lofsa    'break'
			push    
			callk    Said,  2
			bnt      code_1394
			pushi    2
			pushi    0
			pushi    70
			calle    Print,  4
			ldi      1
code_138c:
			bnt      code_1394
			ldi      1
			jmp      code_1bc6
code_1394:
			pushi    #has
			pushi    1
			pushi    7
			lag      ego
			send     6
			bnt      code_13b7
			pushi    1
			lofsa    'dennis,place/charm'
			push    
			callk    Said,  2
			bnt      code_13b7
			pushi    2
			pushi    0
			pushi    71
			calle    Print,  4
			jmp      code_1bc6
code_13b7:
			pushi    1
			lofsa    'hop'
			push    
			callk    Said,  2
			bnt      code_13cd
			pushi    2
			pushi    0
			pushi    34
			calle    Print,  4
			jmp      code_1bc6
code_13cd:
			pushi    1
			lofsa    'kill'
			push    
			callk    Said,  2
			bnt      code_13e3
			pushi    2
			pushi    0
			pushi    72
			calle    Print,  4
			jmp      code_1bc6
code_13e3:
			pushi    1
			lofsa    'rob'
			push    
			callk    Said,  2
			bnt      code_13f9
			pushi    2
			pushi    0
			pushi    73
			calle    Print,  4
			jmp      code_1bc6
code_13f9:
			pushi    1
			lofsa    'hit'
			push    
			callk    Said,  2
			bnt      code_140f
			pushi    2
			pushi    0
			pushi    74
			calle    Print,  4
			jmp      code_1bc6
code_140f:
			pushi    1
			lofsa    'climb'
			push    
			callk    Said,  2
			bnt      code_1425
			pushi    2
			pushi    0
			pushi    75
			calle    Print,  4
			jmp      code_1bc6
code_1425:
			pushi    1
			lofsa    'laugh'
			push    
			callk    Said,  2
			bnt      code_143b
			pushi    2
			pushi    0
			pushi    76
			calle    Print,  4
			jmp      code_1bc6
code_143b:
			pushi    1
			lofsa    'throw'
			push    
			callk    Said,  2
			bnt      code_1451
			pushi    2
			pushi    0
			pushi    16
			calle    Print,  4
			jmp      code_1bc6
code_1451:
			pushi    1
			lofsa    'converse'
			push    
			callk    Said,  2
			bnt      code_1488
			pushi    2
			pushi    1
			pushi    2
			callk    Random,  4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_1475
			pushi    2
			pushi    0
			pushi    77
			calle    Print,  4
			jmp      code_1484
code_1475:
			dup     
			ldi      2
			eq?     
			bnt      code_1484
			pushi    2
			pushi    0
			pushi    78
			calle    Print,  4
code_1484:
			toss    
			jmp      code_1bc6
code_1488:
			pushi    1
			lofsa    'close/door'
			push    
			callk    Said,  2
			bnt      code_149e
			pushi    2
			pushi    0
			pushi    79
			calle    Print,  4
			jmp      code_1bc6
code_149e:
			pushi    1
			lofsa    'listen'
			push    
			callk    Said,  2
			bnt      code_14b4
			pushi    2
			pushi    0
			pushi    80
			calle    Print,  4
			jmp      code_1bc6
code_14b4:
			pushi    1
			lofsa    'sit'
			push    
			callk    Said,  2
			bnt      code_14ca
			pushi    2
			pushi    0
			pushi    81
			calle    Print,  4
			jmp      code_1bc6
code_14ca:
			pushi    1
			lofsa    'smell'
			push    
			callk    Said,  2
			bnt      code_14e0
			pushi    2
			pushi    0
			pushi    82
			calle    Print,  4
			jmp      code_1bc6
code_14e0:
			pushi    1
			lofsa    'open,(look<in)>'
			push    
			callk    Said,  2
			bnt      code_164e
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			pushi    1
			lofsa    '[/!*]'
			push    
			callk    Said,  2
			bnt      code_150b
			pushi    2
			pushi    0
			pushi    83
			calle    Print,  4
			jmp      code_1643
code_150b:
			lat      temp1
			not     
			bnt      code_1521
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    0
			call     CantDo,  0
			jmp      code_1643
code_1521:
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			not     
			bnt      code_153f
			pushi    0
			call     DontHave,  0
			jmp      code_1643
code_153f:
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_1563
			pushi    6
			pushi    0
			pushi    84
			pushi    82
			pushi    401
			pushi    0
			pushi    0
			calle    Print,  12
			jmp      code_1642
code_1563:
			dup     
			ldi      3
			eq?     
			bnt      code_1575
			pushi    2
			pushi    0
			pushi    85
			calle    Print,  4
			jmp      code_1642
code_1575:
			dup     
			ldi      12
			eq?     
			bnt      code_1587
			pushi    2
			pushi    0
			pushi    86
			calle    Print,  4
			jmp      code_1642
code_1587:
			dup     
			ldi      13
			eq?     
			bnt      code_1599
			pushi    2
			pushi    0
			pushi    87
			calle    Print,  4
			jmp      code_1642
code_1599:
			dup     
			ldi      28
			eq?     
			bnt      code_15ab
			pushi    2
			pushi    0
			pushi    88
			calle    Print,  4
			jmp      code_1642
code_15ab:
			dup     
			ldi      4
			eq?     
			bnt      code_1624
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      2
			ne?     
			bnt      code_15ce
			pushi    #view
			pushi    0
			lag      ego
			send     4
			push    
			ldi      4
			ne?     
			bt       code_15e0
code_15ce:
			pushi    1
			pushi    1
			callk    MemoryInfo,  2
			push    
			ldi      4500
			lt?     
			bt       code_15e0
			lag      noWearCrown
			bnt      code_15eb
code_15e0:
			pushi    2
			pushi    0
			pushi    89
			calle    Print,  4
			jmp      code_1642
code_15eb:
			pushi    #loop
			pushi    1
			pushi    0
			lag      ego
			send     6
			pushi    2
			lsg      ego
			pushi    30
			call     IsObjectOnControl,  4
			sag      global168
			push    
			ldi      32768
			and     
			bnt      code_1611
			pushi    2
			pushi    0
			pushi    90
			calle    Print,  4
			jmp      code_1642
code_1611:
			pushi    #setScript
			pushi    1
			pushi    2
			pushi    307
			pushi    0
			callk    ScriptID,  4
			push    
			lag      ego
			send     6
			jmp      code_1642
code_1624:
			dup     
			ldi      18
			eq?     
			bnt      code_163a
			pushi    2
			pushi    0
			pushi    91
			calle    Print,  4
			ldi      1
			sag      global154
			jmp      code_1642
code_163a:
			pushi    2
			pushi    0
			pushi    92
			calle    Print,  4
code_1642:
			toss    
code_1643:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_1bc6
code_164e:
			pushi    1
			lofsa    'look>'
			push    
			callk    Said,  2
			bnt      code_1980
			pushi    1
			lofsa    '/me'
			push    
			callk    Said,  2
			bnt      code_166f
			pushi    2
			pushi    0
			pushi    93
			calle    Print,  4
			jmp      code_1bc6
code_166f:
			pushi    1
			lofsa    '/letter'
			push    
			callk    Said,  2
			bnt      code_16ba
			pushi    #has
			pushi    1
			pushi    35
			lag      ego
			send     6
			bnt      code_169a
			pushi    6
			pushi    0
			pushi    94
			pushi    82
			pushi    435
			pushi    0
			pushi    0
			calle    Print,  12
			jmp      code_1bc6
code_169a:
			pushi    #has
			pushi    1
			pushi    31
			lag      ego
			send     6
			bnt      code_16b2
			pushi    2
			pushi    0
			pushi    95
			calle    Print,  4
			jmp      code_1bc6
code_16b2:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_16ba:
			pushi    1
			lofsa    '/key'
			push    
			callk    Said,  2
			bnt      code_17ac
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			pushi    1
			lofsa    '/*<gold'
			push    
			callk    Said,  2
			bnt      code_1701
			pushi    #has
			pushi    1
			pushi    32
			lag      ego
			send     6
			bnt      code_16f6
			pushi    #showSelf
			pushi    0
			pushi    #at
			pushi    1
			pushi    32
			class    Inventory
			send     6
			send     4
			jmp      code_1bc6
code_16f6:
			pushi    2
			pushi    0
			pushi    96
			calle    Print,  4
			jmp      code_1bc6
code_1701:
			pushi    1
			lofsa    '/*<skeleton'
			push    
			callk    Said,  2
			bnt      code_1735
			pushi    #has
			pushi    1
			pushi    20
			lag      ego
			send     6
			bnt      code_172a
			pushi    #showSelf
			pushi    0
			pushi    #at
			pushi    1
			pushi    20
			class    Inventory
			send     6
			send     4
			jmp      code_1bc6
code_172a:
			pushi    2
			pushi    0
			pushi    96
			calle    Print,  4
			jmp      code_1bc6
code_1735:
			pushi    1
			lofsa    '/*[<!*]'
			push    
			callk    Said,  2
			bnt      code_1bc6
			pushi    #has
			pushi    1
			pushi    20
			lag      ego
			send     6
			bnt      code_1765
			pushi    #has
			pushi    1
			pushi    32
			lag      ego
			send     6
			bnt      code_1765
			pushi    2
			pushi    0
			pushi    97
			calle    Print,  4
			jmp      code_1bc6
code_1765:
			pushi    #has
			pushi    1
			pushi    20
			lag      ego
			send     6
			bnt      code_1783
			pushi    #showSelf
			pushi    0
			pushi    #at
			pushi    1
			pushi    20
			class    Inventory
			send     6
			send     4
			jmp      code_1bc6
code_1783:
			pushi    #has
			pushi    1
			pushi    32
			lag      ego
			send     6
			bnt      code_17a1
			pushi    #showSelf
			pushi    0
			pushi    #at
			pushi    1
			pushi    32
			class    Inventory
			send     6
			send     4
			jmp      code_1bc6
code_17a1:
			pushi    2
			pushi    0
			pushi    98
			calle    Print,  4
			jmp      code_1bc6
code_17ac:
			pushi    1
			lofsa    '/moon,moon'
			push    
			callk    Said,  2
			bnt      code_17e2
			lag      isIndoors
			bnt      code_17c7
			pushi    2
			pushi    0
			pushi    99
			calle    Print,  4
			jmp      code_1bc6
code_17c7:
			lag      isNightTime
			bnt      code_17d7
			pushi    2
			pushi    0
			pushi    100
			calle    Print,  4
			jmp      code_1bc6
code_17d7:
			pushi    2
			pushi    0
			pushi    100
			calle    Print,  4
			jmp      code_1bc6
code_17e2:
			pushi    1
			lofsa    '/cloud'
			push    
			callk    Said,  2
			bnt      code_1818
			lag      isIndoors
			bnt      code_17fd
			pushi    2
			pushi    0
			pushi    101
			calle    Print,  4
			jmp      code_1bc6
code_17fd:
			lag      isNightTime
			bnt      code_180d
			pushi    2
			pushi    0
			pushi    102
			calle    Print,  4
			jmp      code_1bc6
code_180d:
			pushi    2
			pushi    0
			pushi    103
			calle    Print,  4
			jmp      code_1bc6
code_1818:
			pushi    1
			lofsa    '/wall'
			push    
			callk    Said,  2
			bnt      code_182e
			pushi    2
			pushi    0
			pushi    104
			calle    Print,  4
			jmp      code_1bc6
code_182e:
			pushi    1
			lofsa    '<in/bottle'
			push    
			callk    Said,  2
			bnt      code_1872
			pushi    #has
			pushi    1
			pushi    31
			lag      ego
			send     6
			not     
			bnt      code_184f
			pushi    0
			call     CantDo,  0
			jmp      code_1bc6
code_184f:
			pushi    #has
			pushi    1
			pushi    35
			lag      ego
			send     6
			bnt      code_1867
			pushi    2
			pushi    0
			pushi    105
			calle    Print,  4
			jmp      code_1bc6
code_1867:
			pushi    2
			pushi    0
			pushi    106
			calle    Print,  4
			jmp      code_1bc6
code_1872:
			pushi    1
			lofsa    '/dirt,dirt'
			push    
			callk    Said,  2
			bt       code_1888
			pushi    1
			lofsa    '<down'
			push    
			callk    Said,  2
			bnt      code_1893
code_1888:
			pushi    2
			pushi    0
			pushi    107
			calle    Print,  4
			jmp      code_1bc6
code_1893:
			pushi    1
			lofsa    '<up'
			push    
			callk    Said,  2
			bt       code_18a9
			pushi    1
			lofsa    '/sky'
			push    
			callk    Said,  2
			bnt      code_18d4
code_18a9:
			lag      isIndoors
			bnt      code_18b9
			pushi    2
			pushi    0
			pushi    108
			calle    Print,  4
			jmp      code_1bc6
code_18b9:
			lag      isNightTime
			bnt      code_18c9
			pushi    2
			pushi    0
			pushi    109
			calle    Print,  4
			jmp      code_1bc6
code_18c9:
			pushi    2
			pushi    0
			pushi    110
			calle    Print,  4
			jmp      code_1bc6
code_18d4:
			pushi    #has
			pushi    1
			pushi    17
			lag      ego
			send     6
			bnt      code_190c
			pushi    #loop
			pushi    0
			pushi    #at
			pushi    1
			pushi    17
			class    Inventory
			send     6
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_190c
			pushi    1
			lofsa    '/earthworm'
			push    
			callk    Said,  2
			bnt      code_190c
			pushi    2
			pushi    0
			pushi    111
			calle    Print,  4
			jmp      code_1bc6
code_190c:
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			bnt      code_193f
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			push    
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			bnt      code_1937
			pushi    #showSelf
			pushi    0
			lat      temp1
			send     4
			jmp      code_1bc6
code_1937:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_193f:
			pushi    1
			lofsa    '/troll,bard,dwarf,pan,giant,goon,person,man'
			push    
			callk    Said,  2
			bt       code_1960
			pushi    1
			lofsa    '/hag,woman,fairies,genesta,lolotte,giantess,woman'
			push    
			callk    Said,  2
			bt       code_1960
			pushi    1
			lofsa    '/unicorn,bird,bulldog,fisherman,whale'
			push    
			callk    Said,  2
			bnt      code_196b
code_1960:
			pushi    2
			pushi    0
			pushi    112
			calle    Print,  4
			jmp      code_1bc6
code_196b:
			pushi    2
			pushi    800
			pushi    4
			calle    Print,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_1bc6
code_1980:
			pushi    1
			lofsa    'use>'
			push    
			callk    Said,  2
			bnt      code_19e7
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			not     
			bnt      code_19a4
			pushi    2
			pushi    0
			pushi    113
			calle    Print,  4
			jmp      code_19dc
code_19a4:
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			not     
			bnt      code_19c2
			pushi    0
			call     DontHave,  0
			jmp      code_19dc
code_19c2:
			pushi    1
			pushi    4
			lea      @str
			push    
			pushi    0
			pushi    114
			pushi    #name
			pushi    0
			lat      temp1
			send     4
			push    
			callk    Format,  8
			push    
			calle    Print,  2
code_19dc:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_1bc6
code_19e7:
			pushi    1
			lofsa    'deliver>'
			push    
			callk    Said,  2
			bnt      code_1a66
			pushi    1
			lofsa    '/*[/!*]'
			push    
			callk    Said,  2
			bnt      code_1a08
			pushi    2
			pushi    0
			pushi    115
			calle    Print,  4
			jmp      code_1bc6
code_1a08:
			pushi    1
			lofsa    '[/!*]'
			push    
			callk    Said,  2
			bnt      code_1a1e
			pushi    2
			pushi    0
			pushi    116
			calle    Print,  4
			jmp      code_1bc6
code_1a1e:
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			bnt      code_1a53
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			push    
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			not     
			bnt      code_1a48
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_1a48:
			pushi    2
			pushi    0
			pushi    117
			calle    Print,  4
			jmp      code_1bc6
code_1a53:
			pushi    2
			pushi    0
			pushi    118
			calle    Print,  4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_1bc6
code_1a66:
			pushi    1
			lofsa    'get>'
			push    
			callk    Said,  2
			bnt      code_1b33
			pushi    1
			lofsa    '/water'
			push    
			callk    Said,  2
			bnt      code_1a87
			pushi    2
			pushi    0
			pushi    119
			calle    Print,  4
			jmp      code_1bc6
code_1a87:
			pushi    1
			lofsa    '[/!*]'
			push    
			callk    Said,  2
			bnt      code_1a9d
			pushi    2
			pushi    0
			pushi    120
			calle    Print,  4
			jmp      code_1bc6
code_1a9d:
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			not     
			bnt      code_1abe
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    2
			pushi    0
			pushi    121
			calle    Print,  4
			jmp      code_1bc6
code_1abe:
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			bnt      code_1b0b
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_1aec
			pushi    0
			call     AlreadyTook,  0
			jmp      code_1b07
code_1aec:
			dup     
			ldi      13
			eq?     
			bnt      code_1afe
			pushi    2
			pushi    0
			pushi    122
			calle    Print,  4
			jmp      code_1b07
code_1afe:
			pushi    2
			pushi    800
			pushi    0
			calle    Print,  4
code_1b07:
			toss    
			jmp      code_1bc6
code_1b0b:
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			dup     
			ldi      3
			eq?     
			bnt      code_1b27
			pushi    2
			pushi    0
			pushi    123
			calle    Print,  4
			jmp      code_1b2f
code_1b27:
			pushi    2
			pushi    0
			pushi    124
			calle    Print,  4
code_1b2f:
			toss    
			jmp      code_1bc6
code_1b33:
			pushi    1
			lofsa    'show>'
			push    
			callk    Said,  2
			bnt      code_1b73
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			bnt      code_1b73
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			lst      temp1
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			bnt      code_1b6b
			pushi    2
			pushi    0
			pushi    125
			calle    Print,  4
			jmp      code_1bc6
code_1b6b:
			pushi    0
			call     DontHave,  0
			jmp      code_1bc6
code_1b73:
;enable debug mode
			pushi    1
			lofsa    'overtime/nosleep'
			push    
			callk    Said,  2
			bnt      code_1b8e
			ldi      1
			sag      debugging
			ldi      1
			sag      global216
			pushi    0
			call     SeeNothing,  0
			jmp      code_1bc6
code_1b8e:
			pushi    #saidMe
			pushi    0
			lag      inventory
			send     4
			sat      temp1
			bnt      code_1bc6
			pushi    #has
			pushi    1
			pushi    #indexOf
			pushi    1
			push    
			lag      inventory
			send     6
			push    
			lag      ego
			send     6
			not     
			bnt      code_1bba
			pushi    0
			call     DontHave,  0
			ldi      1
			jmp      code_1bc1
code_1bba:
			pushi    0
			call     CantDo,  0
			ldi      1
code_1bc1:
			bnt      code_1bc6
			ldi      1
code_1bc6:
			ret     
		)
	)
	
	(method (wordFail word)
		(Print (Format @str 0 5 word))
	)
	
	(method (syntaxFail)
		(Print 0 6)
	)
	
	(method (pragmaFail)
		(switch (Random 1 3)
			(1 (Print 0 7))
			(2 (Print 0 8))
			(3 (Print 0 9))
		)
	)
)

(instance Silver_Flute of newInvItem
	(properties
		said '/flute'
		owner 201
		view 413
		name "Silver Flute"
	)
)

(instance Diamond_Pouch of newInvItem
	(properties
		said '/(pouch[<diamond]),diamond'
		view 401
		name "Diamond Pouch"
	)
)

(instance Talisman of newInvItem
	(properties
		said '/amulet,amulet'
		owner 82
		view 411
	)
)

(instance Lantern__unlit_ of newInvItem
	(properties
		said '/lantern,chandelier'
		owner 56
		view 428
		name "Lantern (unlit)"
	)
)

(instance Pandora_s_Box of newInvItem
	(properties
		said '/box'
		owner 69
		view 425
		name "Pandora's Box"
	)
)

(instance Gold_Ball of newInvItem
	(properties
		said '/ball'
		owner 21
		view 400
		name "Gold Ball"
	)
)

(instance Witches__Glass_Eye of newInvItem
	(properties
		said '/eye'
		owner 57
		view 423
		name "Witches' Glass Eye"
	)
)

(instance Obsidian_Scarab of newInvItem
	(properties
		said '/charm'
		owner 57
		view 403
		name "Obsidian Scarab"
	)
)

(instance Peacock_Feather of newInvItem
	(properties
		said '/feather'
		owner 888
		view 429
		name "Peacock Feather"
	)
)

(instance Lute of newInvItem
	(properties
		said '/lute'
		owner 203
		view 414
	)
)

(instance Small_Crown of newInvItem
	(properties
		said '/crown'
		owner 200
		view 402
		name "Small Crown"
	)
)

(instance Frog of newInvItem
	(properties
		said '/frog'
		owner 15
		view 432
	)
)

(instance Silver_Baby_Rattle of newInvItem
	(properties
		said '/rattle'
		owner 88
		view 405
		name "Silver Baby Rattle"
	)
)

(instance Gold_Coins of newInvItem
	(properties
		said '/gold,(bag[<gold]),(bag[<gold,bag])'
		owner 88
		view 406
		name "Gold Coins"
	)
)

(instance Cupid_s_Bow of newInvItem
	(properties
		said '/arrow[<cupid]'
		owner 202
		view 415
		name "Cupid's Bow"
	)
)

(instance Shovel of newInvItem
	(properties
		said '/shovel'
		owner 66
		view 420
	)
	
	(method (showSelf)
		(Print 0 0
			#title (if (self loop?) {Broken Shovel} else {Shovel})
			#icon view loop cel
		)
	)
	
	(method (ownedBy state)
		(if loop
			(= name {Broken Shovel})
		else
			(= name {Shovel})
		)
		(super ownedBy: state)
	)
)

(instance Axe of newInvItem
	(properties
		said '/ax'
		owner 48
		view 418
	)
)

(instance Fishing_Pole of newInvItem
	(properties
		said '/pole[<fish]'
		owner 204
		view 421
		name "Fishing Pole"
	)
	
	(method (showSelf)
		(Print 0 0
			#title (if (self loop?) {Baited Fishing Pole} else {Fishing Pole})
			#icon view loop cel
		)
	)
	
	(method (ownedBy state)
		(if loop
			(= name {Baited Fishing Pole})
		else
			(= name {Fishing Pole})
		)
		(super ownedBy: state)
	)
)

(instance Shakespeare_Book of newInvItem
	(properties
		said '/book[<shakespeare]'
		owner 67
		view 416
		name "Shakespeare Book"
	)
)

(instance Worm of newInvItem
	(properties
		said '/earthworm'
		owner 206
		view 433
	)
)

(instance Skeleton_Key of newInvItem
	(properties
		said '/key<skeleton'
		owner 58
		view 424
		name "Skeleton Key"
	)
)

(instance Golden_Bridle of newInvItem
	(properties
		said '/bit'
		owner 43
		view 426
		name "Golden Bridle"
	)
)

(instance Board of newInvItem
	(properties
		said '/board'
		owner 70
		view 430
	)
)

(instance Bone of newInvItem
	(properties
		said '/bone'
		owner 71
		view 419
	)
)

(instance Dead_Fish of newInvItem
	(properties
		said '/fish[<dead]'
		owner 95
		view 422
		name "Dead Fish"
	)
)

(instance Magic_Fruit of newInvItem
	(properties
		said '/fruit'
		owner 78
		view 412
		name "Magic Fruit"
	)
)

(instance Sheet_Music of newInvItem
	(properties
		said '/music'
		owner 63
		view 417
		name "Sheet Music"
	)
)

(instance Silver_Whistle of newInvItem
	(properties
		said '/whistle'
		owner 207
		view 427
		name "Silver Whistle"
	)
)

(instance Locket of newInvItem
	(properties
		said '/locket'
		owner 88
		view 404
	)
)

(instance Medal of newInvItem
	(properties
		said '/badge'
		owner 88
		view 407
	)
)

(instance Toy_Horse of newInvItem
	(properties
		said '/toy,(horse[<toy])'
		owner 88
		view 408
		name "Toy Horse"
	)
)

(instance Glass_Bottle of newInvItem
	(properties
		said '/bottle'
		owner 44
		view 434
		cel 1
		name "Glass Bottle"
	)
)

(instance Gold_Key of newInvItem
	(properties
		said '/key<gold'
		owner 83
		view 410
		name "Gold Key"
	)
)

(instance Magic_Hen of newInvItem
	(properties
		said '/chicken'
		owner 48
		view 431
		name "Magic Hen"
	)
)

(instance Rose of newInvItem
	(properties
		said '/rose'
		description {What a beautiful red rose! What's this?! Why, there is a little gold key attached to this rose!}
		owner 85
		view 409
	)
	
	(method (showSelf)
		(if loop
			(Print 0 126 #icon view loop cel)
		else
			(Print 0 127 #icon view loop cel)
		)
	)
)

(instance Note of newInvItem
	(properties
		said '/letter'
		owner 999
		view 435
	)
)

(instance liteState of Script
	(properties
		state 0
	)
	
	(method (init theClient)
		(= client theClient)
		(client script: self)
	)
	
	(method (changeState newState)
		(if (!= state newState)
			(switch (= state newState)
				(FALSE
					(Print 0 128)
					(client
						description: {The lantern is off.\nThe oil lantern looks well-used.}
						name: {Lantern (unlit)}
						loop: 0
					)
				)
				(TRUE
					(Print 0 129)
					(client
						description: {The lantern is on.\nThe oil lantern looks well-used.}
						name: {Lantern (lit)}
						loop: 1
					)
				)
			)
		)
	)
)

(instance smallBase of Code

	(method (doit theActor)
		(theActor brTop: (- (theActor y?) (theActor yStep?)))
		(theActor brLeft: (- (theActor x?) (/ (theActor xStep?) 2)))
		(theActor brBottom: (theActor y?))
		(theActor brRight: (+ (theActor x?) (/ (theActor xStep?) 2)))
	)
)

(instance timer1 of Timer)

(instance timer2 of Timer)

(instance timer3 of Timer)
