;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use DCIcon)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	CB1 0
	Say 1
	Face 2
	HandsOff 3
	HandsOn 4
	Bset 5
	Bclr 6
	Btst 7
	HaveMem 8
	NotClose 9
	AlreadyTook 10
	SeeNothing 11
	CantDo 12
	DontHave 13
	RedrawCast 14
	cls 15
	AlreadyOpen 16
	AlreadyClosed 17
	DontSee 18
	EgoDead 19
	FirstEntry 20
	LoadMany 21
	Ok 22
	DaddySays 23
	CantTalk2People 24
	ParseName 25
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
	version
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
	gCurRoomNum_3
	global107
	enteredCelieHouse
	global109
	global110
	global111 =  42
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
	gCurRoomNum
	deadGuests
	global124
	global125
	global126
	global127
	cIcon
	deathLoop
	deathCel
	gMyMusic
	cyclingIcon
	global133
	global134
	global135
	numCrackers =  7
	lanternIsLit
	tombDoorState
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
	visitedFlags
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
	fifiSleeping
	bellOnGround
	currentAct
	global166
	global167
	global168
	global169
	global170
	whichItem
	global172
	global173
	global174
	global175
	saidsAnchor
	global177
	global178
	gCurRoomNum_2
	gCurRoomNum_4
	global181
	gotItem
	cSound
	oiledBell
	gunIsLoaded
	gameHours =  6
	gameMinutes =  3
	global188
	global189
	saveDisabled
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
	theTalker
	global214
	menuBarInitialized
	global216
	global217
	mouseDownHandler
	theInvItem
	global220
	savedCursor
	machineSpeed
	howFast
	haveInvItem
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
	gDoor
	gMySound
	gBdoor
	gDoor_2
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
	gGate
	gJeevesX
	gJeevesY
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
	gameFlags
	global441
	global442
	global443
	global444
	global445
	gCellar
	getItemSound
)
(procedure (Say whom &tmp theView [buffer 500])
	(if
		(and
			(== theTalker talkFIFI)
			(== currentAct 4)
			(== curRoomNum 74)
		)
		(= theView 928)
	else
		(= theView (+ theTalker 899))
	)
	(= cyclingIcon whom)
	(GetFarText &rest @buffer)
	(myIcon
		view: theView
		cycleSpeed: 8
		count: (+ (/ (StrLen @buffer) 18) 1))
	(Print &rest #icon myIcon 0 0)
	(= cyclingIcon 0)
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
		(Face actor2 actor1)
	)
)

(procedure (HandsOff)
	(manageHands FALSE)
)

(procedure (HandsOn)
	(manageHands TRUE)
)

(procedure (Bset flagEnum)
	(manageFlags 0 flagEnum)
)

(procedure (Bclr flagEnum)
	(manageFlags 2 flagEnum)
)

(procedure (Btst flagEnum)
	(manageFlags 1 flagEnum)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (NotClose)
	(Print 0 9)
)

(procedure (AlreadyTook)
	(Print 0 10)
)

(procedure (SeeNothing)
	(Print 0 13)
)

(procedure (CantDo)
	(Print 0 14)
)

(procedure (DontHave)
	(Print 0 15)
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (AlreadyOpen)
	(Print 0 11)
)

(procedure (AlreadyClosed)
	(Print 0 12)
)

(procedure (DontSee)
	(Print 0 16)
)

(procedure (EgoDead theMod theString)
	(sounds eachElementDo: #dispose)
	((Sound new:) number: 100 play:)
	(= savedCursor ARROW_CURSOR)
	(theGame setCursor: ARROW_CURSOR TRUE)
	(repeat
		(switch
			(Print theMod theString
				#icon cIcon deathLoop deathCel
				#mode teJustCenter
				#width 240
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
				(= cyclingIcon 0)
				(break)
			)
		)
	)
)

(procedure (FirstEntry &tmp i temp1)
	(if (not (& visitedFlags $8000))
		(|= visitedFlags $8000)
		(= i (/ curRoomNum 16))
		(= temp1 (<< $0001 (mod curRoomNum 16)))
		(if (not (& [global148 i] temp1))
			(return (= [global148 i] (| [global148 i] temp1)))
		)
	)
	(return 0)
)

(procedure (LoadMany what which &tmp i theRes)
	(-= argc 2)
	(= i 0)
	(while (<= i argc)
		(= theRes [which i])
		(switch what
			(FALSE
				(DisposeScript theRes)
			)
			(142
				(Load VIEW (+ theRes 899))
			)
			(143
				(Load SCRIPT theRes)
				(Load TEXT theRes)
			)
			(else
				(Load what theRes)
			)
		)
		(++ i)
	)
)

(procedure (Ok)
	(Print 0 17)
)

(procedure (DaddySays)
	(= theTalker talkLAURA)
	(Say 1 &rest)
)

(procedure (CantTalk2People)
	(Print 0 18)
)

(procedure (ParseName who &tmp event)
	(StrCpy (User inputLineAddr?) {Look_})
	(StrCat (User inputLineAddr?) who)
	((= event (Event new:)) type: saidEvent)
	(Parse (User inputLineAddr?) event)
	(User said: event)
	(event dispose:)
)

(procedure (manageHands tOrF)
	(User canControl: tOrF canInput: tOrF)
	(ego setMotion: 0)
)

(procedure (localproc_0354 &tmp i)
	(= global172
		(= global178
			(= gCurRoomNum_2
				(= gCurRoomNum_4
					(= global118
						(= global116 (= global117 (= global173 0)))
					)
				)
			)
		)
	)
	(if (== (++ currentAct) 5) (Bset 47))
	(= global166 (& global166 (<< $0001 currentAct)))
	(= i 0)
	(while (< i 148)
		(= [global225 i] 0)
		(++ i)
	)
)

(procedure (manageFlags param1 flagEnum &tmp i temp1)
	(= i (/ flagEnum 16))
	(= temp1 (<< $0001 (mod flagEnum 16)))
	(return
		(switch param1
			(0
				(= [gameFlags i] (| [gameFlags i] temp1))
			)
			(1
				(return (& [gameFlags i] temp1))
			)
			(2
				(= [gameFlags i] (& [gameFlags i] (~ temp1)))
			)
		)
	)
)

(class newInvItem of InvItem
	
	(method (showSelf)
		(Print 0 0
			#title name 
			#icon view 0 0
		)
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
		(Graph GReAnimate (- top 8) (- left 8) (+ bottom 8) (+ right 8))
		(DisposeWindow window)
		(DisposeClone self)
	)
	
	(method (open &tmp mapSet t l b r botLftCorner topRgtCorner topLftCorner cornerWidth)
		(= topLftCorner (CelHigh 657 0 0))
		(= botLftCorner (CelHigh 657 0 1))
		(= topRgtCorner (CelHigh 657 1 0))
		(= cornerWidth (CelWide 657 0 0))
		(SetPort 0)
		(= t (- top 8))
		(= l (- left 8))
		(= b (+ bottom 8))
		(= r (+ right 8))
		(= mapSet VMAP)
		(if (!= priority -1)
			(|= mapSet PMAP)
		)
		(= underBits
			(Graph GSaveBits t l b r mapSet)
		)
		(Graph GFillRect t l b r mapSet back priority)
		(DrawCel 657 0 0 l t -1)
		(DrawCel 657 0 1 l (- b botLftCorner) -1)
		(DrawCel 657 1 0 (- r topRgtCorner) t -1)
		(DrawCel 657 1 2 (- r topRgtCorner) (- b botLftCorner) -1)
		(Graph GDrawLine t (+ l cornerWidth) t (- r cornerWidth) 31 -1 -1)
		(Graph GDrawLine (+ t 2) (+ l cornerWidth) (+ t 2) (- r cornerWidth) 31 -1 -1)
		(Graph GDrawLine (- b 1) (+ l cornerWidth) (- b 1) (- r cornerWidth) 31 -1 -1)
		(Graph GDrawLine (- b 3) (+ l cornerWidth) (- b 3) (- r cornerWidth) 31 -1 -1)
		(Graph GDrawLine (+ t topLftCorner) l (- b topLftCorner) l 31 -1 -1)
		(Graph GDrawLine (+ t topLftCorner) (+ l 2) (- b topLftCorner) (+ l 2) 31 -1 -1)
		(Graph GDrawLine (+ t topLftCorner) (- r 1) (- b topLftCorner) (- r 1) 31 -1 -1)
		(Graph GDrawLine (+ t topLftCorner) (- r 3) (- b topLftCorner) (- r 3) 31 -1 -1)
		(Graph GShowBits t l b r VMAP)
		(= type 129)
		(super open:)
	)
)

(instance MMSaidsAnchor of Prop)

(instance myIcon of DCIcon
	(properties
		cycleSpeed 6
	)
	
	(method (init)
		(if cyclingIcon
			((= cycler (Forward new:)) init: self)
		)
	)
)

(instance statusCode of Code
	
	(method (doit strg)
		(StrCpy strg 0 1)
	)
)

(instance egoObj of Ego
	(properties
		name {ego}
	)
)

(instance conMusic of Sound
	(properties
		number 93
	)
)

(instance getItemMusic of Sound
	(properties
		number 93
		priority 2
		owner -1
	)
)

(instance mmMouseDownHandler of EventHandler)

(instance CB1 of Game

	(method (init)
		(= saveDisabled TRUE)
		(= saidsAnchor MMSaidsAnchor)
		(= systemWindow myWindow)
		(if (< (Graph GDetect) 16)
			(systemWindow color: vWHITE back: vBLACK)
		)
		(User alterEgo: (= ego egoObj))
		(super init:)
		((= mouseDownHandler mmMouseDownHandler) add:)
		(= savedCursor ARROW_CURSOR)
		(Inventory
			add:
				necklace_
				monocle_
				lantern_
				oilcan_
				rolling_pin_
				skeleton_key_
				poker_
				crowbar_
				cigar_butt_
				broken_record_
				notebook___pencil_
				_7_crackers________
				soup_bone_
				valve_handle_
				bullet_
				derringer__
				matches_
				carrot_
				brass_key_
				diary_
				crank_
				cane_
				pouch_
				handkerchief_
		)
		(= global181 1)
		(= showStyle HSHUTTER)
		(= userFont (= bigFont USERFONT))
		(= version {1.000.046})
		(TheMenuBar init: draw: state: 0 hide:)
		(getItemMusic init:)
		(= getItemSound getItemMusic)
		(conMusic owner: self init:)
		(= cSound conMusic)
		(= global157 0)
		(SetCursor HAND_CURSOR 0 320 200)
		(ego view: 0 x: 100 y: 120)
		(if (GameIsRestarting)
			(= possibleScore 1)
			(SetCursor HAND_CURSOR 0 320 200)
			(TheMenuBar draw: state: TRUE hide:)
		)
		(self newRoom: 99)
	)
	
	(method (doit &tmp i egoView)
		(cond 
			(
				(and
					(not global101)
					(== (= egoView (ego view?)) 0)
					(not (ego avoider?))
					(or (not (ego mover?)) (ego isBlocked:))
					(== (ego x?) (ego xLast?))
					(== (ego y?) (ego yLast?))
				)
				(ego view: 11 setMotion: 0)
			)
			((and (== egoView 11) (ego mover?))
				(ego view: 0)
			)
		)
		(if gotItem
			(= gotItem FALSE)
			(getItemMusic play:)
		)
		(for ((= i 0)) (< i 5) ((++ i))
			(if (> [global368 i] 1) (-- [global368 i]))
		)
		(if (> talkTimer 0)
			(-- talkTimer)
		)
		(if (> global220 0)
			(-- global220)
		)
		(cond 
			((and (== (User controls?) FALSE) (== savedCursor ARROW_CURSOR))
				(= savedCursor HAND_CURSOR)
				(theGame setCursor: HAND_CURSOR TRUE))
			
			((and (== savedCursor HAND_CURSOR) (== (User controls?) TRUE))
				(= savedCursor ARROW_CURSOR)
				(theGame setCursor: ARROW_CURSOR TRUE)
			)
		)
		(super doit:)
	)
	
	(method (replay)
		(= userFont bigFont)
		(SetMenu soundI
			p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(TheMenuBar draw: hide:)
		(super replay:)
	)
	
	(method (startRoom roomNum &tmp saveScript i)
		(= global216 0)
		(LoadMany FALSE RFEATURE SAVE)
		(mouseDownHandler release:)
		(mouseDownHandler add: cast features)
		(HandsOn)
		(cls)
		(&= visitedFlags $7fff)
		(= global195 (= global208 (= global214 0)))
		(for ((= i 0)) (< i 11) ((++ i))
			(= [global377 i] 0)
		)
		(if (== global118 15)
			(localproc_0354)
		)
		(super startRoom: roomNum)
		(Load VIEW 911)
		(Load SCRIPT 413)
		(LoadMany 143 407 410)
		(cond 
			((or (< roomNum 31) (== roomNum 65))
				(curRoom setRegions: 208)
				(if (and (& deadGuests $0040) (!= roomNum 16))
					(curRoom setRegions: 400)
				)
			)
			(
				(or
					(< roomNum 51)
					(== roomNum 74)
					(== roomNum 73)
					(== roomNum 75)
					(== roomNum 76)
					(== roomNum 53)
					(== roomNum 63)
				)
				(if (and (& deadGuests $0040) (!= curRoomNum 74))
					(curRoom setRegions: 401)
				)
				(curRoom setRegions: 210)
			)
		)
		(= global216 1)
		(if
			(not
				(cond 
					(
						(or
							(== roomNum 36)
							(and
								(== roomNum 38)
								(== currentAct 0)
								(not (& global173 $0002))
							)
							(and (== roomNum 32) (< currentAct 4))
							(and (== roomNum 44) (== currentAct 0))
						)
					)
					((== roomNum 75) (& deadGuests $0040))
				)
			)
			(= saveScript (ScriptID SAVE))
		)
	)
	
	(method (handleEvent event &tmp [temp0 18])
		(if (event claimed?) (return))
		(switch (event type?)
			((super handleEvent: event))
			(mouseDown
				(if (not (event claimed?))
					(mouseDownHandler handleEvent: event)
				)
			)
			(saidEvent
				(if (and (not (event claimed?)) theInvItem)
					(MMSaidsAnchor setScript: (ScriptID 410 0))
					((MMSaidsAnchor script?) handleEvent: event)
				)
				(if (not (event claimed?))
					(MMSaidsAnchor setScript: (ScriptID 407 0))
					((MMSaidsAnchor script?) handleEvent: event)
				)
			)
		)
		(= theTalker 0)
	)
)

(instance necklace_ of newInvItem
	(properties
		said '/necklace'
		owner 99
		view 602
		name "necklace_"
	)
)

(instance monocle_ of newInvItem
	(properties
		said '/monocle'
		view 604
		name "monocle_"
	)
)

(instance lantern_ of newInvItem
	(properties
		said '/lantern'
		owner 69
		view 613
		name "lantern_"
	)
	
	(method (showSelf)
		(Print 0 0
			#icon 613 0 (if lanternIsLit 1 else 0)
		)
	)
)

(instance oilcan_ of newInvItem
	(properties
		said '/can'
		owner 61
		view 611
		name "oilcan_"
	)
)

(instance rolling_pin_ of newInvItem
	(properties
		said '/pin'
		owner 25
		view 626
		name "rolling pin_"
	)
)

(instance skeleton_key_ of newInvItem
	(properties
		said '/key<skeleton'
		owner 58
		view 620
		name "skeleton key_"
	)
)

(instance poker_ of newInvItem
	(properties
		said '/poker'
		owner 32
		view 627
		name "poker_"
	)
)

(instance crowbar_ of newInvItem
	(properties
		said '/crowbar'
		owner 61
		view 607
		name "crowbar_"
	)
)

(instance cigar_butt_ of newInvItem
	(properties
		said '/butt,butt'
		view 628
		name "cigar butt_"
	)
)

(instance broken_record_ of newInvItem
	(properties
		said '/record'
		owner 36
		view 625
		name "broken record_"
	)
)

(instance notebook___pencil_ of newInvItem
	(properties
		said '/notebook,pencil'
		owner 44
		view 601
		name "notebook & pencil_"
	)
)

(instance _7_crackers________ of newInvItem
	(properties
		said '/cracker,box'
		owner 53
		view 603
		name "7 crackers________"
	)
	
	(method (ownedBy)
		(switch numCrackers
			(0
				(Format (_7_crackers________ name?) 0 2 0 3)
				(_7_crackers________ view: 643)
			)
			(1
				(Format (_7_crackers________ name?) 0 4 numCrackers 0 5)
			)
			(else 
				(Format (_7_crackers________ name?) 0 4 numCrackers 0 6)
			)
		)
		(super ownedBy: &rest)
	)
)

(instance soup_bone_ of newInvItem
	(properties
		said '/bone'
		owner 35
		view 605
		name "soup bone_"
	)
)

(instance valve_handle_ of newInvItem
	(properties
		said '/valve,handle'
		owner 37
		view 606
		name "valve handle_"
	)
)

(instance bullet_ of newInvItem
	(properties
		said '/bullet'
		owner 65
		view 618
		name "bullet_"
	)
)

(instance derringer__ of newInvItem
	(properties
		said '/derringer'
		owner 31
		view 617
		name "derringer__"
	)
	
	(method (ownedBy)
		(if gunIsLoaded
			(Format (derringer__ name?) 0 2 0 7)
		else
			(Format (derringer__ name?) 0 2 0 8)
		)
		(super ownedBy: &rest)
	)
)

(instance matches_ of newInvItem
	(properties
		said '/match'
		owner 67
		view 614
		name "matches_"
	)
)

(instance carrot_ of newInvItem
	(properties
		said '/carrot'
		owner 59
		view 615
		name "carrot_"
	)
)

(instance brass_key_ of newInvItem
	(properties
		said '/key'
		owner 42
		view 619
		name "brass key_"
	)
)

(instance diary_ of newInvItem
	(properties
		said '/diary'
		owner 44
		view 616
		name "diary_"
	)
)

(instance crank_ of newInvItem
	(properties
		said '/control'
		owner 3
		view 621
		name "crank_"
	)
)

(instance cane_ of newInvItem
	(properties
		said '/cane'
		view 612
		name "cane_"
	)
)

(instance pouch_ of newInvItem
	(properties
		said '/diamond'
		owner 57
		view 622
		name "pouch_"
	)
)

(instance handkerchief_ of newInvItem
	(properties
		said '/handkerchief'
		owner 47
		view 630
		name "handkerchief_"
	)
)
