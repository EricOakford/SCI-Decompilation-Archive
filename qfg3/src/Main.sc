;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm") (include "28.shm")
(use GloryWindow)
(use EgoDead)
(use Print)
(use Messager)
(use PMouse)
(use IconBar)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use DCIcon)
(use Timer)
(use GControl)
(use Sound)
(use Motion)
(use File)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Glory 0
	HaveMem 1
	HandsOff 2
	HandsOn 3
	Bset 4
	Bclr 5
	Btst 6
	Dummy1 7
	Rand300 8
	SaveTheCursor 9
	RestoreTheCursor 10
	NextDay 11
	Face 12
	DontMove 13
	AutoTarget 14
	CyclePalette 15
	DisableIcons 16
	textIcon 17
	mainIconBar 20
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
	cuees
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  HAND_CURSOR
	userFont
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont
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
	unused_7
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
	useObstacles =  1
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
	egoGait
	dongle =  1234
	machineSpeed
	cSound
	yesNoTimer
	lastRestTime
	lastRestDay
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
	Clock
	Night
	Day
	timeODay
	oldSysTime
	heroType
	gGOwnerMoveSpeed
	global127
	global128
	global129
	global130
	global131
	numColors
	numVoices
	stamCounter =  20
	healCounter =  15
	freeMeals
	lostSleep
	global138 =  9
	global139 =  12
	oldScore
	timeToEat
	zapPower
	global143
	targetAngles =  180
	global145
	global146 =  45
	global147 =  90
	global148 =  135
	global149 =  180
	global150 =  225
	global151 =  270
	global152 =  315
	global153
	global154
	global155
	monsterHealth
	userName
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
	manaCounter =  20
	globalSound
	disabledIcons
	debugging
	oldCurX
	oldCurY
	oldIcon
	global205
	global206
	startingRoom
	isHandsOff
	arcadeDifficulty =  2
	reversalTimer
	oldStats
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
	egoStats
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
		gTheEgoStats
		global265
		global266
		gEgoMaxHealth
		gEgoMaxStamina
		gEgoMaxMana
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
		gGGGOwnerMaxStamina_3
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
	skillTicks
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
	chestInventory
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
	paladinPoints
	origHeroType
	panoEgoX
	panoEgoY
	monsterNum
	global366
	spellCost =  2
		global368 =  2
		global369 =  3
		global370 =  3
		global371 =  3
		global372 =  4
		global373 =  5
		global374 =  5
		global375 =  6
		global376 =  7
		global377 =  8
		global378 =  8
		global379 =  5
		global380 =  10
		global381 =  3
		global382 =  3
		global383 =  3
		global384 =  3
		global385 =  3
	projX
	projY
	global388
	global389
	global390
	global391
	global392
	gNewList
	global394
	global395
	dispelPotionDay
	global397
	global398
	disabledActions
	gCurrentDay_10
	global401
	global402
	global403 =  1
	paladinStat
	global405 =  4
	global406
	global407
	global408
	survivorDay
	commons
	global411 =  1
	gMonster
	gWarriorObj
	gGGClientModNum_2
	gGOwnerX_3
	gGOwnerY_3
	global417
	global418
	gCalledBy
	global420
	global421
	honeyTreeX
	honeyTreeY
	numDinars
	wrestledDay
	global426
	gGGClientModNum_2_2
	gGOwnerX_4
	gGOwnerY_4
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
	palVaryInfo
	global450
	haramiNight
	gCurrentDay_5
	global453
	global454
	global455
	gCurrentDay_6
	soundFx
	gNewCollect
	global459
	controlRet
	global461
	global462
	global463
	gCurrentDay_7
	totalEncumbrance
	global466
	gGloryWindow_2
	honeyBirdRoom
	qg3Controls
	dayStoleMagicDrum
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
	gameFlags
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
	global589
	global590
	global591
	global592
	global593
	global594
	global595
	global596
	global597
	global598
	global599
	global600
	global601
	global602
	global603
	global604
	global605
	global606
	global607
	global608
	global609
	global610
	global611
	global612
	global613
	global614
	global615
	global616
	global617
	global618
	global619
	global620
	global621
	global622
	global623
	global624
	global625
	global626
	global627
	global628
	global629
	global630
	global631
	global632
	global633
	global634
	global635
	global636
	global637
	global638
	global639
	global640
	global641
	global642
	global643
	global644
	global645
	global646
	global647
	global648
	global649
	global650
	global651
	global652
	global653
	global654
	global655
	global656
	global657
	global658
	global659
	global660
	global661
	global662
	global663
	global664
	global665
	global666
	global667
	global668
	global669
	global670
	global671
	global672
	global673
	global674
	global675
	global676
	global677
	global678
	global679
	global680
	global681
	global682
	global683
	global684
	global685
	global686
	global687
	global688
	global689
	global690
	global691
	global692
	global693
	global694
	global695
	global696
	global697
	global698
	global699
	global700
)
(procedure (HaveMem howMuch)
	(return (> (MemoryInfo LargestPtr) howMuch))
)

(procedure (HandsOff)
	(= isHandsOff TRUE)
	(SaveTheCursor)
	(User canControl: FALSE canInput: FALSE)
	(= disabledIcons 0)
	(theIconBar eachElementDo: #perform checkIcon)
	(theIconBar disable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ACTIONS
		ICON_CAST
		ICON_USEIT
		ICON_INVENTORY
	)
	(if (not (HaveMouse))
		(= oldCurX mouseX)
		(= oldCurY mouseY)
		(theGame setCursor: waitCursor TRUE 310 185)
	else
		(theGame setCursor: waitCursor TRUE)
	)
)

(procedure (HandsOn except &tmp [temp0 31])
	(= isHandsOff FALSE)
	(User canControl: TRUE canInput: TRUE)
	(theIconBar enable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ACTIONS
		ICON_CAST
		ICON_USEIT
		ICON_INVENTORY
		ICON_CONTROL
	)
	(RestoreTheCursor)
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: ICON_USEIT)
	)
	(if (not (HaveMouse))
		(theGame
			setCursor: ((theIconBar curIcon?) cursor?) TRUE oldCurX oldCurY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
	)
	(if (> argc 0)
		(theIconBar disable: except &rest)
	)
)

(procedure (Bset flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bclr flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Dummy1)
)

(procedure (Rand300)
	(return (+ 1 (/ (Random 0 2999) 10)))
)

(procedure (SaveTheCursor)
	(if
		(and
			(not (IsObject oldIcon))
			(IsObject (theIconBar curIcon?))
		)
		(= oldIcon (theIconBar curIcon?))
	)
)

(procedure (RestoreTheCursor)
	(if (IsObject oldIcon)
		(theIconBar curIcon: oldIcon)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(= oldIcon 0)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: ICON_USEIT))
				(not (theIconBar curInvIcon?))
			)
			(theIconBar advanceCurIcon:)
		)
	)
)

(procedure (NextDay)
	(Bclr fFlag31)
	(Bclr fVisitedBazaar)
	(return (++ Day))
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY i)
	(= i 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3) (= i both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= i whoToCue))
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject i) i else 0)
	)
)

(procedure (DontMove)
	(HandsOn)
	(theIconBar disable:
		ICON_WALK
		ICON_DO
		ICON_ACTIONS
		ICON_CAST
		ICON_USEIT
		ICON_INVENTORY
	)
)

(procedure (AutoTarget theX theY)
	(= projX 0)
	(= projY 0)
	(if argc
		(= projX theX)
		(if (> argc 1) (= projY theY))
	)
)

(procedure (CyclePalette)
	(if (not (Btst fCharSheetActive))
		(Palette PALCycle &rest)
	)
)

(procedure (DisableIcons &tmp i n)
	(= n -32768)
	(for ((= i 0)) (<= i 10) ((++ i))
		(if (& disabledIcons n)
			(theIconBar disable: i)
		)
		(= n (>> n $0001))
	)
)

(procedure (localproc_1380 &tmp temp0 [temp1 400] [temp401 21] temp422 temp423)
	(= temp0 (GetSaveFiles (theGame name?) @temp1 @temp401))
	(= temp422 1)
	(repeat
		(= temp423 0)
		(while (< temp423 temp0)
			(breakif (== temp422 [temp401 temp423]))
			(++ temp423)
		)
		(if (== temp423 temp0) (break))
		(++ temp422)
	)
	(return temp422)
)

(procedure (AutoSave param1 param2 &tmp [temp0 400] [temp400 21] temp421 temp422 newFile temp424)
	(= temp421
		(GetSaveFiles (theGame name?) @temp0 @temp400)
	)
	((= newFile (File new:))
		name: (DeviceInfo 7 param2 (theGame name?))
		open: 2
	)
	(= temp424 2570)
	(= temp422 0)
	(while (< temp422 temp421)
		(if (!= temp422 param1)
			(newFile write: @[temp400 temp422] 2)
			(newFile writeString: @[temp0 (* temp422 18)])
			(newFile write: @temp424 1)
		)
		(++ temp422)
	)
	(= temp424 -1)
	(newFile write: @temp424 2 close: dispose:)
	(DeviceInfo 8 param2 (theGame name?) [temp400 param1])
	(FileIO fileUnlink param2)
	(DisposeScript FILE)
)

(instance qg3KDHandler of EventHandler)

(instance qg3MDHandler of EventHandler)

(instance qg3DirHandler of EventHandler)

(instance qg3Walkers of EventHandler)

(class Glory of Game
	
	(method (init &tmp theStopWalk versionFile [temp2 2])
		(= systemWindow GloryWindow)
		((= ego (ScriptID GLORY_EGO 0)) view: 0)
		(= showStyle -32761)
		(Actor origStep: 1027)
		(= machineSpeed 2)
		(= waitCursor 945)
		(= theStopWalk StopWalk)
		Timer
		Polygon
		PolyPath
		(super init:)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileClose versionFile)
		(= doVerbCode qg3DoVerbCode)
		(= ftrInitializer qg3FtrInit)
		(= approachCode qg3ApproachCode)
		(= messager qg3Messager)
		((= keyDownHandler qg3KDHandler) add:)
		((= mouseDownHandler qg3MDHandler) add:)
		((= directionHandler qg3DirHandler) add:)
		((= walkHandler qg3Walkers) add:)
		(= pMouse PseudoMouse)
		((= cSound longSong)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= globalSound longSong2)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= soundFx longSong3)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= theIconBar mainIconBar)
			init:
			disable: ICON_USEIT iconLeft iconRight
			curIcon: iconLook
		)
		((ScriptID GLORY_INV 0) init:)
		((ScriptID GLORY_INIT) init:)
		(Bset fFlag14)
	)
	
	(method (doit &tmp thisTime newHour egoMover)
		(super doit:)
		(if (Btst fInMainGame)
			(if (!= oldSysTime (= thisTime (GetTime SYSTIME1)))
				(= oldSysTime thisTime)
				(if (not (Btst fFlag7))
					(= newHour Clock)
					(if (>= (++ Clock) GAMEDAY)
						(= Clock 0)
						(NextDay)
					)
					(if (< (mod Clock GAMEHOUR) (mod newHour GAMEHOUR))
						((ScriptID TIME 4) init:)
					)
					(cond 
						((and (>= Clock 772) (Btst fEgoIsAsleep) (not (Btst fFlag31)))
							(PalVary PALVARYREVERSE 64)
							(Bclr fEgoIsAsleep)
							(Bset fNightPaletteDisabled)
						)
						((and (<= 2700 Clock) (not (Btst fEgoIsAsleep)))
							(Bset fFlag31)
							(PalVary PALVARYSTART curRoomNum 64)
							(Bset fNightPaletteDisabled)
							(Bset fEgoIsAsleep)
						)
					)
				)
				(cond 
					((and (> Clock 2750) (Btst fEgoIsAsleep))
						(= Night TRUE)
						(= currentPalette 1)
					)
					((and (> Clock 790) (not (Btst fEgoIsAsleep)))
						(= Night FALSE)
						(= currentPalette 0)
					)
				)
				(if
					;get ready for Day 3's meeting
					(and
						(not (Btst fRakeeshSworeOath))
						(not (Btst fHadMeeting))
						(>= Day 1)
						(>= Clock 2200)
						(not (curRoom script?))
						(not monsterHealth)
						(not (ego script?))
						(not (IsObject fastCast))
						[egoStats HEALTH]
						(not (OneOf curRoomNum 230 240 250 260))
						(!= curRoomNum 340)
						(!= curRoomNum 550)
					)
					(Bset fHadMeeting)
					((ScriptID TIME 4) init: 19)
					(messager say: N_CUE V_DOIT C_MEETING_TOMORROW 0 0 0)
					(if (== curRoomNum 310)
						(curRoom setScript: (ScriptID 310 1))
					else
						(globalSound fade:)
						(soundFx fade:)
						(curRoom newRoom: 310)
					)
				)
				(if (and (== egoGait MOVE_SNEAK) (ego mover?))
					(ego useSkill: STEALTH 2)
				)
				(cond 
					(
						(and
							(or
								(and (< 1100 Clock) (< Clock 1200))
								(and (< 2500 Clock) (< Clock 2600))
							)
							(not timeToEat)
						)
						(if (> Clock 2500)
							(= timeToEat 2650)
						else
							(= timeToEat 1250)
						)
						(ego eatMeal:)
					)
					((> Clock timeToEat)
						(= timeToEat FALSE)
					)
				)
				(if (<= (= global463 (- global463 global411)) 0)
					(= global463 100)
					(if (Btst fStarving)
						(ego eatMeal:)
					)
				)
				(if (<= (= stamCounter (- stamCounter global411)) 0)
					(= stamCounter 20)
					(cond 
						((or (> lostSleep 1) (Btst fOverloaded))
							(ego useStamina: 5)
							(if (Btst fOverloaded)
								(ego useStamina: (>> totalEncumbrance $0007))
								(ego useSkill: STR 2)
							)
						)
						((and (= egoMover (ego mover?)) (== egoGait MOVE_RUN))
							(ego useStamina: 4)
						)
						((and egoMover (== egoGait MOVE_SNEAK))
							(ego useStamina: 1)
						)
						(else
							(ego useStamina: -2)
						)
					)
				)
				(if (not (-- manaCounter))
					(= manaCounter 20)
					(ego useMana: -1)
				)
				(if (not (-- healCounter))
					(= healCounter 15)
					(if
						(not
							(if (or (> lostSleep 1) (Btst fStarving)) else (Btst fPoisoned))
						)
						(ego takeDamage: -1)
					)
					(if (Btst fPoisoned)
						(cond 
							((not (ego takeDamage: 3))
								(EgoDead C_DEATH_POISON C_POISONED)
							)
							((< [egoStats HEALTH] (>> (ego maxHealth:) $0003))
								(messager say: N_HERO NULL C_POISONED 0 0 GLORY_EGO)
							)
						)
					)
				)
			)
			(if (and (not (Btst fFlag7)) (Btst fReversal))
				(switch (-- reversalTimer)
					(0
						(Bclr fReversal)
						(messager say: NULL NULL C_REVERSAL_GONE 0 0 0)
					)
					(10
						(messager say: NULL NULL C_REVERSAL_GOING 0 0 0)
					)
				)
			)
		)
	)
	
	(method (newRoom &tmp [temp0 3] evt)
		(if (IsObject gNewCollect)
			(gNewCollect dispose:)
			(= gNewCollect 0)
		)
		(while (IsObject fastCast)
			(fastCast eachElementDo: #doit)
			(if
			(and ((= evt (Event new:)) type?) fastCast)
				(fastCast firstTrue: #handleEvent evt)
			)
			(evt dispose:)
			(theDoits doit:)
		)
		(super newRoom: &rest)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(SaveTheCursor)
		(theGame setCursor: waitCursor TRUE)
		((ScriptID STARTAROOM 0) init: roomNum)
		(DisposeScript STARTAROOM)
		(if
			(and
				(!= (- (MemoryInfo FreeHeap) 2) (MemoryInfo LargestPtr))
				(FileIO fileExists {18.scr})
				(messager say: NULL NULL C_MEMORY_FRAGMENT 0 0 0)
			)
			(SetDebug)
		)
		(if (OneOf roomNum 230 240 250 260)
			(ScriptID BAZAAR)
		)
		StopWalk
		Cycle
		(if (FileIO fileExists {18.scr})
			((ScriptID GLORY_DEBUG 0) init:)
		)
		(ego edgeHit: 0)
		(= debugging FALSE)
		(super startRoom: roomNum)
		(if (not isHandsOff)
			(RestoreTheCursor)
			(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		)
	)
	
	(method (restart)
		(curRoom style: IRISIN drawPic: 0)
		(cast eachElementDo: #dispose)
		(super restart:)
	)
	
	(method (save param1 &tmp [temp0 400] [temp400 21] [temp421 18] [temp439 41] [temp480 70] temp550 temp551 temp552 temp553 temp554 evt [temp556 30])
		(if (and (== argc 1) (== param1 1))
			(GetCWD @temp480)
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(= temp550
				(GetSaveFiles (theGame name?) @temp0 @temp400)
			)
			(Message MsgGet 0 NULL NULL C_AUTO_SAVE 1 @temp439)
			(= temp552 0)
			(= temp551 -1)
			(= temp553 0)
			(while (< temp553 temp550)
				(if
					(not
						(= temp552 (StrCmp @temp439 @[temp0 (* temp553 18)]))
					)
					(= temp551 temp553)
					(break)
				)
				(++ temp553)
			)
			(if (>= temp551 0)
				(AutoSave temp551 @temp439)
				(= temp550
					(GetSaveFiles (theGame name?) @temp0 @temp400)
				)
				(while (and (> temp550 0) (not (CheckFreeSpace @temp480)))
					(AutoSave
						(- temp550 1)
						@[temp0
						(* (- temp550 1) 18)]
					)
					(= temp550
						(GetSaveFiles (theGame name?) @temp0 @temp400)
					)
				)
				(if (CheckFreeSpace @temp480)
					(Message MsgGet 0 NULL NULL C_AUTO_SAVE 1 @temp421)
					(= temp554 (localproc_1380))
					(if (not (SaveGame name temp554 @temp421 version))
						(Message MsgGet 0 0 17 1 @temp556)
						(Print addTextF: @temp556 init:)
						(return)
					else
						(if (not (if argc param1))
							(Message MsgGet 0 0 18 1 @temp556)
							(Print addTextF: @temp556 init:)
						)
						(= temp550
							(GetSaveFiles (theGame name?) @temp0 @temp400)
						)
					)
				else
					(Message MsgGet 0 0 0 19 1 @temp556)
					(Print addTextF: @temp556 init:)
					(return)
				)
			else
				(while
					(and
						(> temp550 0)
						(or (not (CheckFreeSpace @temp480)) (>= temp550 20))
					)
					(AutoSave
						(- temp550 1)
						@[temp0
						(* (- temp550 1) 18)]
					)
					(= temp550
						(GetSaveFiles (theGame name?) @temp0 @temp400)
					)
				)
				(Message MsgGet 0 NULL NULL C_AUTO_SAVE 1 @temp421)
				(= temp554 (localproc_1380))
				(if (not (SaveGame name temp554 @temp421 version))
					(Message MsgGet 0 0 17 1 @temp556)
					(Print addTextF: @temp556 init:)
					(return)
				else
					(if (not (if argc param1))
						(Message MsgGet 0 0 18 1 @temp556)
						(Print addTextF: @temp556 init:)
					)
					(= temp550
						(GetSaveFiles (theGame name?) @temp0 @temp400)
					)
				)
			)
			(while ((= evt (Event new:)) type?)
				(evt dispose:)
			)
			(evt dispose:)
		else
			(super save: &rest)
		)
		(theGame setCursor: ((mainIconBar curIcon?) cursor?))
	)
	
	(method (handleEvent event)
		(if (== (event type?) mouseUp)
			(mouseDownHandler handleEvent: event)
		else
			(super handleEvent: event)
		)
		(return
			(if (event claimed?)
				(return TRUE)
				else
					FALSE
			)
		)
	)
	
	(method (setSpeed theSpeed)
		(if (and argc (User canControl:))
			(ego setSpeed: theSpeed)
			(super setSpeed: theSpeed)
		)
		(ego moveSpeed?)
	)
	
	(method (setCursor form showIt theX theY &tmp oldCur)
		(= oldCur theCursor)
		(if argc
			(if (IsObject form)
				((= theCursor form) init:)
			else
				(SetCursor (= theCursor form) 0 0)
			)
		)
		(if (and (> argc 1) (not showIt))
			(SetCursor INVIS_CURSOR 0 0)
		)
		(if (> argc 2)
			(SetCursor theX theY)
		)
		(return oldCur)
	)
	
	(method (quitGame &tmp icon oldCur)
		(= oldCur (theGame setCursor: ARROW_CURSOR))
		((= qg3Controls (GameControls new:))
			window:
				((GloryWindow new:)
					top: 30
					left: 65
					bottom: 120
					right: 256
					priority: 15
					yourself:
				)
		)
		((= icon (textIcon new: NULL NULL C_QUITTING 1 0))
			view: 935
			loop: 1
			cel: 0
			nsTop: 2
			nsLeft: 2
			modifiers: 1
		)
		(qg3Controls add: icon)
		((= icon (textIcon new: NULL NULL C_QUIT_YES 1 0))
			nsTop: 70
			nsLeft: 80
			cursor: 1
		)
		(qg3Controls add: icon)
		((= icon (textIcon new: NULL NULL C_QUIT_NO 1 0))
			nsTop: 70
			nsLeft: 145
			cursor: 2
		)
		(qg3Controls add: icon)
		((= icon (IconItem new:))
			nsTop: 30
			nsLeft: 20
			view: 937
			loop: 1
			cel: 0
			maskView: 937
			maskLoop: 1
			maskCel: 0
			signal: 132
		)
		(qg3Controls add: icon)
		(qg3Controls show: dispose:)
		(switch controlRet
			(1
				(= quit TRUE)
			)
			(2
				(theGame setCursor: oldCur)
			)
		)
	)
	
	(method (pragmaFail &tmp theVerb)
		(if (User canInput:)
			(= theVerb ((User curEvent?) message?))
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(switch theVerb
				(V_LOOK
					(messager say: N_PRAG_FAIL V_LOOK NULL NULL 0 0)
				)
				(V_DO
					(messager say: N_PRAG_FAIL V_DO NULL NULL 0 0)
				)
				(V_TALK
					(messager say: N_PRAG_FAIL V_TALK NULL NULL 0 0)
				)
				(V_SLEEP
					(if
						(OneOf curRoomNum
							150 160 170 180 310 390 400
							430 440 600 700 770 780 820
						)
						(curRoom doVerb: V_SLEEP)
					else
						(messager say: N_PRAG_FAIL NULL C_CANT_SLEEP_HERE 0 0 0)
					)
				)
				(V_FLAME
					(if
						(OneOf curRoomNum
							230 280 310 380 400 430 650
							700 810 820 851 852 853 854
						)
						(curRoom doVerb: V_FLAME)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_FORCEBOLT
					(if
						(OneOf curRoomNum
							0 230 280 310 380 400 430
							700 810 820 851 852 853 854
						)
						(curRoom doVerb: V_FORCEBOLT)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_LIGHTNING
					(if
						(OneOf curRoomNum
							0 230 280 310 380 400 430
							700 810 820 851 852 853 854
						)
						(curRoom doVerb: V_LIGHTNING)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_OPEN
					(if
						(OneOf curRoomNum
							230 310 380 430 450 640
							650 700 810 820 853
						)
						(curRoom doVerb: V_OPEN)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_DETECT
					(if
						(OneOf curRoomNum
							230 280 310 390 430
							650 770 780 810 853
						)
						(curRoom doVerb: V_DETECT)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_TRIGGER
					(if
						(OneOf curRoomNum
							230 280 310 400 430 650
							700 850 851 852 853 854
						)
						(curRoom doVerb: V_TRIGGER)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_DAZZLE
					(if
						(OneOf curRoomNum
							230 280 310 400 430 650
							700 851 852 853 854
						)
						(curRoom doVerb: V_DAZZLE)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_CALM
					(if
						(OneOf curRoomNum
							230 280 310 390 400 430 450 630
							650 700 820 850 851 852 853 854
						)
						(curRoom doVerb: V_CALM)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_FETCH
					(cond 
						(
							(OneOf curRoomNum
								230 280 310 380 430 640
								650 700 810
							)
							(curRoom doVerb: V_FETCH)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_BAD_MAGIC_POSITION 1 0 0)
						)
						(else
							(ego setScript: (ScriptID CASTFETCH))
						)
					)
				)
				(V_LEVITATE
					(if
						(OneOf curRoomNum
							280 310 400 430 650 700
							720 740 851 852 853 854
						)
						(curRoom doVerb: V_LEVITATE)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_STAFF
					(if
						(OneOf curRoomNum
							280 310 400 430
							650 700 850 853
						)
						(curRoom doVerb: V_STAFF)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_REVERSAL
					(if
						(OneOf curRoomNum
							280 310 400 430
							650 700 850 853
						)
						(curRoom doVerb: V_REVERSAL)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_JUGGLE
					(if
						(OneOf curRoomNum
							230 280 310 400
							430 650 700 853
						)
						(curRoom doVerb: V_JUGGLE)
					else
						(messager say: N_PRAG_FAIL NULL C_NO_MAGIC_HERE 0 0 0)
					)
				)
				(V_HEAL
					(messager say: N_PRAG_FAIL V_HEAL C_CANT_HEAL_THAT 0 0 0)
				)
				(V_DAGGER
					(if
						(OneOf curRoomNum
							230 400 460 700
						)
						(curRoom doVerb: V_DAGGER)
					else
						(messager say: N_PRAG_FAIL NULL C_CANT_DO_THAT_HERE 0 0 0)
					)
				)
				(V_ROCK
					(if
						(OneOf curRoomNum
							400 700
						)
						(curRoom doVerb: V_ROCK)
					else
						(messager say: N_PRAG_FAIL NULL C_CANT_DO_THAT_HERE 0 0 0)
					)
				)
				(V_FINE_SPEAR
					(if
						(OneOf curRoomNum
							380 810 851 852
						)
						(curRoom doVerb: V_FINE_SPEAR)
					else
						(messager say: N_PRAG_FAIL NULL C_CANT_DO_THAT_HERE 0 0 0)
					)
				)
				(V_MAGIC_SPEAR
					(if
						(OneOf curRoomNum
							380 851
						)
						(curRoom doVerb: V_MAGIC_SPEAR)
					else
						(messager say: N_PRAG_FAIL NULL C_CANT_DO_THAT_HERE 0 0 0)
					)
				)
				(V_GRAPNEL
					(if
						(OneOf curRoomNum
							0
						)
						(curRoom doVerb: V_GRAPNEL)
					else
						(messager say: N_PRAG_FAIL NULL C_CANT_DO_THAT_HERE 0 0 0)
					)
				)
				(else 
					(messager say: N_PRAG_FAIL NULL C_THINK_AGAIN 0 0 0)
				)
			)
		)
	)
	
	(method (showAbout)
		((ScriptID GLORY_ABOUT 0) doit:)
	)
)

(instance theIcon1 of DCIcon
	(properties
		cycleSpeed 16
	)
	
	(method (init)
		((= cycler (EndLoop new:)) init: self)
	)
)

(instance checkIcon of Code
	
	(method (doit theIcon)
		(if
			(and
				(theIcon isKindOf: IconItem)
				(& (theIcon signal?) DISABLED)
			)
			(= disabledIcons
				(|
					disabledIcons
					(>> $8000 (theIconBar indexOf: theIcon))
				)
			)
		)
	)
)

(instance qg3DoVerbCode of Code
	(properties)
	
	(method (doit theVerb)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(switch theVerb
			(V_WALK
				((User curEvent?) claimed: FALSE)
			)
			(V_LOOK
				(messager say: N_VERB_GENERIC V_LOOK NULL 0 0 0)
			)
			(V_DO
				(messager say: N_VERB_GENERIC V_DO NULL 0 0 0)
			)
			(V_TALK
				(messager say: N_VERB_GENERIC V_TALK NULL 0 0 0)
			)
			(V_SLEEP
				(if
					(OneOf curRoomNum
						150 160 170 180 310 390 400
						430 440 600 700 770 780 820
					)
					(curRoom doVerb: V_SLEEP)
				else
					(messager say: N_PRAG_FAIL NULL C_CANT_SLEEP_HERE 0 0 0)
				)
			)
			(V_FLAME
				(if
					(OneOf curRoomNum
						230 280 310 380 400 430 650
						700 810 820 851 852 853 854
					)
					(curRoom doVerb: V_FLAME)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_FORCEBOLT
				(if
					(OneOf curRoomNum
						0 230 280 310 380 400 430
						700 810 820 851 852 853 854
					)
					(curRoom doVerb: V_FORCEBOLT)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_LIGHTNING
				(if
					(OneOf curRoomNum
						0 230 280 310 380 400 430
						700 810 820 851 852 853 854
					)
					(curRoom doVerb: V_LIGHTNING)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_OPEN
				(if
					(OneOf curRoomNum
						230 310 380 430 450 640
						650 700 810 820 853
					)
					(curRoom doVerb: V_OPEN)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_DETECT
				(if
					(OneOf curRoomNum
						230 280 310 390 430
						650 770 780 810 853
					)
					(curRoom doVerb: V_DETECT)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_TRIGGER
				(if
					(OneOf curRoomNum
						230 280 310 400 430 650
						700 850 851 852 853 854
					)
					(curRoom doVerb: V_TRIGGER)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_DAZZLE
				(if
					(OneOf curRoomNum
						230 280 310 400 430 650
						700 851 852 853 854
					)
					(curRoom doVerb: V_DAZZLE)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_CALM
				(if
					(OneOf curRoomNum
						230 280 310 390 400 430 450
						630 650 700 820 850 851 852
						853 854
					)
					(curRoom doVerb: V_CALM)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_FETCH
				(cond 
					(
						(OneOf curRoomNum
							230 280 310 380 430
							640 650 700 810
						)
						(curRoom doVerb: V_FETCH)
					)
					((> (ego view?) 5)
						(messager say: NULL NULL C_BAD_MAGIC_POSITION 1 0 0)
					)
					(else
						(ego setScript: (ScriptID CASTFETCH))
					)
				)
			)
			(V_LEVITATE
				(if
					(OneOf curRoomNum
						280 310 400 430 650 700
						720 740 851 852 853 854
					)
					(curRoom doVerb: V_LEVITATE)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_STAFF
				(if
					(OneOf curRoomNum
						280 310 400 430 650 700 850 853
					)
					(curRoom doVerb: V_STAFF)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_REVERSAL
				(if
					(OneOf curRoomNum
						280 310 400 430 650 700 850 853
					)
					(curRoom doVerb: V_REVERSAL)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(86
				(if
					(OneOf curRoomNum
						230 280 310 400 430 650 700 853
					)
					(curRoom doVerb: 86)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
				)
			)
			(V_HEAL
				(messager say: N_VERB_GENERIC V_HEAL C_NO_NEED_TO_HEAL 0 0 0)
			)
			(V_DAGGER
				(if
					(OneOf curRoomNum
						230 400 460 700
					)
					(curRoom doVerb: V_DAGGER)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_DO_THAT_HERE 0 0 0)
				)
			)
			(V_GRAPNEL
				(if
					(OneOf curRoomNum
						0
					)
					(curRoom doVerb: V_GRAPNEL)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_DO_THAT_HERE 0 0 0)
				)
			)
			(V_ROCK
				(if (OneOf curRoomNum 400 700)
					(curRoom doVerb: V_ROCK)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_DO_THAT_HERE 0 0 0)
				)
			)
			(V_FINE_SPEAR
				(if (OneOf curRoomNum 380 810 851 852)
					(curRoom doVerb: V_FINE_SPEAR)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_DO_THAT_HERE 0 0 0)
				)
			)
			(V_MAGIC_SPEAR
				(if (OneOf curRoomNum 380 851)
					(curRoom doVerb: V_MAGIC_SPEAR)
				else
					(messager say: N_VERB_GENERIC NULL C_CANT_DO_THAT_HERE 0 0 0)
				)
			)
			(else 
				(messager say: N_VERB_GENERIC NULL C_DIDNT_DO_ANY_GOOD 0 0 0)
			)
		)
	)
)

(instance qg3FtrInit of Code
	
	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 40)
		)
		(if (== (obj actions?) ftrDefault)
			(obj actions: 0)
		)
		(if
			(and
				(not (obj approachX?))
				(not (obj approachY?))
			)
			(obj approachX: (obj x?) approachY: (obj y?))
		)
	)
)

(instance qg3ApproachCode of Code
	(properties)
	
	(method (doit theVerb)
		(return
			(cond 
				((== theVerb V_LOOK) 1)
				((== theVerb V_TALK) 2)
				((== theVerb V_WALK) 4)
				((== theVerb V_DO) 8)
				((and (<= 10 theVerb) (<= theVerb 59)) 16)
				((and (<= 75 theVerb) (<= theVerb 88)) 32)
				((== theVerb -1) -1)
				(else -32768)
			)
		)
	)
)

(class MessObj of Object
	(properties
		noun 0
		verb 0
		case 0
		seq 0
		who 0
		module 0
		said 0
		argCount 0
	)
	
	(method (new theNoun theVerb theCase theSeq theTalker theMod &tmp i)
		((= i (Clone self))
			argCount: argc
			noun: theNoun
			verb: theVerb
			case: theCase
		)
		(if (> argc 2)
			(i seq: theSeq)
			(if (> argc 3)
				(i who: theTalker)
				(if (> argc 4)
					(i module: theMod)
				)
			)
		)
		(theDoits add: i)
	)
	
	(method (doit)
		(if (and (not said) (not (messager talkerList?)))
			(= said 1)
			(switch argCount
				(3
					(messager say: noun verb case)
				)
				(4
					(messager say: noun verb case seq)
				)
				(5
					(messager say: noun verb case seq who)
				)
				(6
					(messager say: noun verb case seq who module)
				)
			)
			(self dispose:)
		)
	)
	
	(method (dispose)
		(theDoits delete: self)
		(super dispose:)
	)
)

(instance qg3Messager of Messager

	(method (say)
		(if (== newRoomNum curRoomNum)
			(if talkerList
				(MessObj new: &rest)
			else
				(super say: &rest)
			)
		)
	)
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					(NARRATOR
						narrator
					)
					(GUARD210_DAY_A
						(ScriptID 210 1)
					)
					(GUARD210_DAY_B
						(ScriptID 210 2)
					)
					(GUARD210_NIGHT_A
						(ScriptID 210 1)
					)
					(GUARD210_NIGHT_B
						(ScriptID 210 2)
					)
					(MONEYCHANGER
						(ScriptID 230 1)
					)
					(FRUIT_MERCHANT
						(ScriptID 232 1)
					)
					(LEATHER_MERCHANT
						(ScriptID 235 1)
					)
					(GUARD234
						(ScriptID 234 0)
					)
					(WEAPON_MERCHANT
						(ScriptID 245 0)
					)
					(HONEY_MERCHANT
						(ScriptID 247 0)
					)
					(OIL_MERCHANT
						(ScriptID 248 0)
					)
					(SANFORD
						(ScriptID 246 0)
					)
					(SON
						(ScriptID 246 1)
					)
					(GUARD240
						(ScriptID 241 2)
					)
					(BEAD_MERCHANT
						(ScriptID 250 1)
					)
					(FISH_MERCHANT
						(ScriptID 250 2)
					)
					(ROPE_MERCHANT
						(ScriptID 250 3)
					)
					(ANUBIS
						(ScriptID 360 1)
					)
					(DES
						(ScriptID 360 2)
					)
					(SALIM
						(ScriptID 290 1)
					)
					(SURVIVOR
						(ScriptID 300 1)
					)
					(INNKEEPER
						(ScriptID 300 2)
					)
					(GUARD320_DAY_A
						(ScriptID 320 1)
					)
					(GUARD320_DAY_B
						(ScriptID 320 2)
					)
					(GUARD320_NIGHT_A
						(ScriptID 320 3)
					)
					(GUARD320_NIGHT_B
						(ScriptID 320 4)
					)
					(RAJAH
						(ScriptID 330 1)
					)
					(SPEAKER
						(ScriptID 340 1)
					)
					(WARRIOR
						(ScriptID 340 2)
					)
					(57
						(ScriptID 340 3)
					)
					(MOTHER
						(ScriptID 340 4)
					)
					(HARAMI340
						(ScriptID 340 5)
					)
					(HARAMI240
						(ScriptID 241 1)
					)
					(SEKEMET
						(ScriptID 350 1)
					)
					(ELDER_DAY
						(ScriptID 58 0)
					)
					(ELDER_NIGHT
						(ScriptID 58 0)
					)
					(59
						(ScriptID 420 1)
					)
					(DEMON_A
						(ScriptID 820 1)
					)
					(DEMON_WIZARD
						(ScriptID 850 1)
					)
					(KREESHA
						(ScriptID 49 0)
					)
					(PRIESTESS
						(ScriptID 48 0)
					)
					(SHAMAN
						(ScriptID 43 0)
					)
					(JUDGE
						(ScriptID 44 0)
					)
					(UHURA
						(ScriptID 34 0)
					)
					(RAKEESH
						(ScriptID 35 0)
					)
					(JOHARI
						(ScriptID 36 0)
					)
					(YESUFU
						(ScriptID 39 0)
					)
					(HARAMI
						(ScriptID 40 0)
					)
					(LAIBON
						(ScriptID 42 0)
					)
					(MANU
						(ScriptID 41 0)
					)
					(STORYTELLER
						(ScriptID 53 0)
					)
					(LAUREL
						(ScriptID 401 1)
					)
					(HARDY
						(ScriptID 401 2)
					)
					(AARDVARK
						(ScriptID 402 1)
					)
					(AZIZA
						(ScriptID 110 1)
					)
					(SULTAN
						(ScriptID 120 1)
					)
					(AVIS
						(ScriptID 100 1)
					)
					(KATTA
						(ScriptID 260 1)
					)
					(KALB
						(ScriptID 260 2)
					)
					(AMULET_MERCHANT
						(ScriptID 260 3)
					)
					(CLOTH_MERCHANT
						(ScriptID 260 4)
					)
					(KREESHA285
						(ScriptID 285 1)
					)
					(RAKEESH285
						(ScriptID 285 2)
					)
					(REESHAKA
						(ScriptID 830 1)
					)
					(YESUFU490
						(ScriptID 490 1)
					)
					(REESHAKA_B
						(ScriptID 830 2)
					)
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(class GlorySong of Sound
	(properties
		change 0
		nextSong 0
		loopTwice 0
		holdVal 0
	)
	
	(method (check)
		(if handle
			(DoSound UpdateCues self)
		)
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(if change
				(= change 0)
				(self play: vol client)
			else
				(if (IsObject client)
					(client cue: self)
				)
				(if (and nextSong (== prevSignal -1))
					(= number nextSong)
					(= nextSong 0)
					(self setLoop: -1 play: vol)
				)
				(if (== prevSignal 126)
					(if loopTwice
						(= loopTwice FALSE)
						(self play: vol)
						(if holdVal (self hold: holdVal))
					else
						(= loopTwice TRUE)
					)
				)
			)
		)
	)
	
	(method (changeTo soundNum c)
		(= number soundNum)
		(if (> argc (= change 1))
			(= client c)
		)
	)
)

(instance longSong of GlorySong)

(instance longSong2 of GlorySong)

(instance longSong3 of GlorySong)

(instance mainIconBar of IconBar
	
	(method (init)
		(self
			add:
				iconLeft
				iconWalk
				iconLook
				iconDo
				iconTalk
				iconActions
				iconCast
				iconUseIt
				iconInventory
				iconControlPanel
				iconHelp
				iconRight
			eachElementDo: #init
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			useIconItem: iconUseIt
			helpIconItem: iconHelp
			walkIconItem: iconWalk
			state: (| OPENIFONME NOCLICKHELP)
		)
		(iconHelp view: 930 loop: 9)
	)
	
	(method (handleEvent event)
		(if (> (event y?) (self height?))
			(self hide:)
		)
		(super handleEvent: event)
	)
	
	(method (show &tmp theIcon pnv i theX theY node nextNode obj)
		(sounds pause:)
		(|= state IB_ACTIVE)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(= height
			(CelHigh
				((= theIcon (self at: 0)) view?)
				(theIcon loop?)
				(theIcon cel?)
			)
		)
		(= port (GetPort))
		(SetPort -1)
		(= underBits (Graph GSaveBits y 0 (+ y height) 320 1))
		(= pnv (PicNotValid))
		(PicNotValid 1)
		(= theX 0)
		(= theY y)
		(= node (FirstNode elements))
		(while node
			(= nextNode (NextNode node))
			(if (not (IsObject (= obj (NodeValue node))))
				(return)
			)
			(if (<= (obj nsRight?) 0)
				(obj show: theX theY)
				(= theX (obj nsRight?))
			else
				(obj show:)
			)
			(= node nextNode)
		)
		(if curInvIcon
			(if (ego has: (inventory indexOf: curInvIcon))
				(= theX
					(+
						(/
							(-
								(- (useIconItem nsRight?) (useIconItem nsLeft?))
								(CelWide (curInvIcon view?) (curInvIcon loop?) (curInvIcon cel?))
							)
							2
						)
						(useIconItem nsLeft?)
					)
				)
				(= theY
					(+
						y
						(/
							(-
								(- (useIconItem nsBottom?) (useIconItem nsTop?))
								(CelHigh (curInvIcon view?) (curInvIcon loop?) (curInvIcon cel?))
							)
							2
						)
						(useIconItem nsTop?)
					)
				)
				(DrawCel (curInvIcon view?) (curInvIcon loop?) (curInvIcon cel?) theX theY -1)
				(if (& (useIconItem signal?) DISABLED)
					(useIconItem mask:)
				)
			else
				(= curInvIcon NULL)
			)
		)
		(PicNotValid pnv)
		(Graph GShowBits y 0 (+ y height) 320 1)
		(self highlight: curIcon)
	)
	
	(method (hide)
		(super hide:)
		(if palVaryInfo
			(PalVary PALVARYPAUSE 0)
			(Bclr fFlag121)
			(= palVaryInfo 0)
		)
	)
	
	(method (swapCurIcon &tmp temp0)
		(cond 
			((& state DISABLED) (return))
			((and (!= curIcon iconWalk) (not (& (iconWalk signal?) DISABLED)))
				(= prevIcon curIcon)
				(= curIcon iconWalk)
			)
			((and prevIcon (not (& (prevIcon signal?) DISABLED)))
				(= curIcon prevIcon)
			)
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
	
	(method (noClickHelp)
		(super noClickHelp: &rest)
		(if (& (curIcon signal?) DISABLED)
			(self advanceCurIcon:)
		)
	)
)

(instance iconLeft of IconItem
	(properties
		view 930
		loop 12
		cel 1
		nsTop 0
		cursor 69
	)
	
	(method (show)
		(super show: -30 nsTop)
	)
	
	(method (select)
		(return FALSE)
	)
	
	(method (mask)
	)
)

(instance iconRight of IconItem
	(properties
		view 930
		loop 13
		cel 1
		cursor 69
	)
	
	(method (select)
		(return FALSE)
	)
	
	(method (mask)
	)
)

(instance iconWalk of IconItem
	(properties
		view 930
		loop 0
		cel 0
		cursor 940
		type (| userEvent walkEvent)
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		maskView 930
		maskLoop 14
		noun N_WALK
		helpVerb V_HELP
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(ego changeGait: MOVE_WALK TRUE)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconLook of IconItem
	(properties
		view 930
		loop 1
		cel 0
		cursor 941
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskView 930
		maskLoop 14
		noun N_LOOK
		helpVerb V_HELP
	)
)

(instance iconDo of IconItem
	(properties
		view 930
		loop 2
		cel 0
		cursor 942
		message V_DO
		signal (| HIDEBAR RELVERIFY)
		maskView 930
		maskLoop 14
		noun N_DO
		helpVerb V_HELP
	)
)

(instance iconTalk of IconItem
	(properties
		view 930
		loop 3
		cel 0
		cursor 943
		message V_TALK
		signal (| HIDEBAR RELVERIFY)
		maskView 930
		maskLoop 14
		noun N_TALK
		helpVerb V_HELP
	)
)

(instance iconActions of IconItem
	(properties
		view 930
		loop 10
		cel 0
		cursor 942
		message V_ACTIONS
		signal (| HIDEBAR IMMEDIATE)
		maskView 930
		maskLoop 14
		noun N_ACTIONS
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(SaveTheCursor)
				(theIconBar hide:)
				((ScriptID GLORY_ACTIONS) init: show:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconCast of IconItem
	(properties
		view 930
		loop 11
		cel 0
		message V_CAST
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 930
		maskLoop 14
		noun N_CAST
		helpVerb V_HELP
	)
	
	(method (select param1 &tmp temp0 temp1)
		(asm
			pushi    #select
			pushi    0
			&rest    param1
			super    IconItem,  4
			bnt      code_2953
			pushi    #cursor
			pushi    0
			pushi    #curIcon
			pushi    0
			lag      theIconBar
			send     4
			send     4
			push    
			ldi      948
			ne?     
			bnt      code_2847
			pushi    0
			call     SaveTheCursor,  0
code_2847:
			pushi    #hide
			pushi    0
			lag      theIconBar
			send     4
			ldi      12
			lagi     egoStats
			not     
			bnt      code_286b
			pushi    #say
			pushi    6
			pushi    26
			pushi    6
			pushi    27
			pushi    0
			pushi    0
			pushi    0
			lag      messager
			send     16
			jmp      code_294d
code_286b:
			pushi    12
			lsg      curRoomNum
			pushi    210
			pushi    240
			pushi    250
			pushi    260
			pushi    270
			pushi    290
			pushi    300
			pushi    320
			pushi    330
			pushi    340
			pushi    360
			calle    OneOf,  24
			bnt      code_28ae
			pushi    #say
			pushi    6
			pushi    26
			pushi    6
			pushi    24
			pushi    0
			pushi    0
			pushi    0
			lag      messager
			send     16
			jmp      code_294d
code_28ae:
			pushi    11
			lsg      curRoomNum
			pushi    410
			pushi    420
			pushi    440
			pushi    450
			pushi    460
			pushi    470
			pushi    475
			pushi    480
			pushi    485
			pushi    490
			calle    OneOf,  22
			bnt      code_28fc
			lsg      curRoomNum
			ldi      450
			eq?     
			bnt      code_28e3
			lag      Night
code_28e3:
			not     
			bnt      code_28fc
			pushi    #say
			pushi    6
			pushi    26
			pushi    6
			pushi    25
			pushi    0
			pushi    0
			pushi    0
			lag      messager
			send     16
			jmp      code_294d
code_28fc:
			ldi      0
			sat      temp1
code_2900:
			lst      temp1
			ldi      15
			lt?     
			bnt      code_291b
			pushi    19
			lat      temp1
			add     
			lagi     egoStats
			sat      temp0
			bnt      code_2917
			jmp      code_291b
code_2917:
			+at      temp1
			jmp      code_2900
code_291b:
			lat      temp0
			not     
			bnt      code_2936
			pushi    #say
			pushi    6
			pushi    26
			pushi    6
			pushi    26
			pushi    0
			pushi    0
			pushi    0
			lag      messager
			send     16
			jmp      code_294d
code_2936:
			pushi    #init
			pushi    0
			pushi    113
			pushi    0
			pushi    111
			pushi    0
			pushi    1
			pushi    21
			callk    ScriptID,  2
			send     12
			pushi    1
			pushi    21
			callk    DisposeScript,  2
code_294d:
			ldi      1
			ret     
			jmp      code_2956
code_2953:
			ldi      0
			ret     
code_2956:
			ret     
		)
	)
)

(instance iconUseIt of IconItem
	(properties
		view 930
		loop 4
		cel 0
		cursor 999
		message NULL
		signal (| HIDEBAR RELVERIFY)
		maskView 930
		maskLoop 14
		maskCel 1
		noun N_USEIT
		helpVerb V_HELP
	)
	
	(method (select relVer &tmp event whichCel thisIcon iconWidth iconHeight)
		(return
			(cond 
				((& signal DISABLED)
					FALSE
				)
				((and argc relVer (& signal RELVERIFY))
					(if (= thisIcon (theIconBar curInvIcon?))
						(= iconWidth
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide (thisIcon view?) (thisIcon loop?) (thisIcon cel?))
									)
									2
								)
								nsLeft
							)
						)
						(= iconHeight
							(+
								(theIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh (thisIcon view?) (thisIcon loop?) (thisIcon cel?))
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if (= thisIcon (theIconBar curInvIcon?))
						(DrawCel (thisIcon view?) (thisIcon loop?) (thisIcon cel?) iconWidth iconHeight -1)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if (= thisIcon (theIconBar curInvIcon?))
										(DrawCel (thisIcon view?) (thisIcon loop?) (thisIcon cel?) iconWidth iconHeight -1)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if (= thisIcon (theIconBar curInvIcon?))
									(DrawCel (thisIcon view?) (thisIcon loop?) (thisIcon cel?) iconWidth iconHeight -1)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if (= thisIcon (theIconBar curInvIcon?))
							(DrawCel (thisIcon view?) (thisIcon loop?) (thisIcon cel?) iconWidth iconHeight -1)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					)
					whichCel
				)
				(else TRUE)
			)
		)
	)
)

(instance iconInventory of IconItem
	(properties
		view 930
		loop 5
		cel 0
		cursor ARROW_CURSOR
		type NULL
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 930
		maskLoop 14
		noun 21
		helpVerb V_HELP
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				((ScriptID GLORY_INV 1) init: ego)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconControlPanel of IconItem
	(properties
		view 930
		loop 7
		cel 0
		cursor ARROW_CURSOR
		message V_HELP
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 930
		maskLoop 14
		noun N_CONTROL
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				((ScriptID GLORY_CONTROL) init: show: dispose:)
				(DisposeScript GLORY_CONTROL)
				(DisposeScript GLORY_ABOUT)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance iconHelp of IconItem
	(properties
		view 930
		loop 9
		cel 0
		cursor 949
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		maskView 930
		maskLoop 14
		noun N_HELP
		helpVerb V_HELP
	)
)

(instance textIcon of IconItem
	(properties
		view 935
		loop 2
		cel 0
	)
	
	(method (new n v c s modNum &tmp who temp1 temp2)
		(= who (Clone self))
		(if argc
			(= temp1
				(Message MsgSize modNum n v c s)
			)
			(who message: (Memory MNewPtr temp1))
			(Message MsgGet modNum n v c s (who message?))
		)
		(return who)
	)
	
	(method (dispose)
		(Memory MDisposePtr message)
		(super dispose:)
	)
	
	(method (show)
		(= nsRight (+ nsLeft (if (== loop 1) 0 else 60)))
		(= nsBottom (if (== loop 1) nsTop else (+ nsTop 15)))
		(DrawCel view loop cel nsLeft nsTop -1)
		(Display message
			p_at (if (== loop 1) nsLeft else (+ nsLeft 20)) (+ nsTop 2)
			p_font (if (== loop 1) 123 else SYSFONT)
			p_color 17
			p_mode modifiers
			p_width (if (== loop 1) 189 else 40)
		)
	)
	
	(method (select)
		(return
			(if (!= loop 1)
				(= controlRet cursor)
				(qg3Controls state: (& (qg3Controls state?) $ffdf))
			else
				(return 0)
			)
		)
	)
	
	(method (highlight param1 &tmp temp0)
		(if (!= loop 1)
			(if param1
				(DrawCel view loop 1 nsLeft nsTop -1)
				(= temp0 46)
			else
				(DrawCel view loop 0 nsLeft nsTop -1)
				(= temp0 17)
			)
			(Display message
				p_at (if (== loop 1) nsLeft else (+ nsLeft 20)) (+ nsTop 2)
				p_font (if (== loop 1) 123 else SYSFONT)
				p_color temp0
				p_mode modifiers
				p_width (if (== loop 1) 189 else 40)
			)
		)
	)
)
