;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm") (include "28.shm")
(use EgoDead)
(use ConfirmPrompt)
(use Plane)
(use String)
(use Array)
(use Print)
(use Messager)
(use Talker)
(use PMouse)
(use IconBar)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use DCIcon)
(use Timer)
(use Sound)
(use Motion)
(use File)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	Glory 0
	CantUseMagic 1
	Bset 2
	Bclr 3
	Btst 4
	CantUseMagic_5 5
	Rand300 6
	SaveTheCursor 7
	RestoreTheCursor 8
	NextDay 9
	Face 10
	DontMove 11
	AutoTarget 12
	CyclePalette_13 13
	DisableIcons 14
	CantUseMagic_15 15
	CyclePalette 16
	DailyMsg 17
	MaxStat 18
	CantUseMagic_19 19
	CantUseMagic_20 20
	statusCode 21
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
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	eventMask =  allEvents
	bigFont =  USERFONT
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
	useObstacles =  TRUE
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
	msgType =  TEXT_MSG
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
	gameCode =  1234
	machineSpeed
	theMusic
	yesNoTimer
	lastRestTime
	lastRestDay
	gGTheObj_2X
	gGTheObj_2Y
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
	Day =  1
	timeODay
	oldSysTime
	heroType
	gGTheObj_2CycleSpeed
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
	battleResult
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
	gCombatSpell_2
	global182
	global183
	global184
	global185
	global186
	global187
	gCombatSpell
	global189
	gXStep
	combatSpeed
	global192
	gCombatSpell_3
	gTeller
	gSActor
	global196
	global197
	manaCounter =  20
	theMusic2
	disabledIcons
	debugging
	oldCurX
	oldCurY
	oldCurs
	oldStats
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
	egoStats
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
	skillTicks
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
	dreamNum
	egoMoveSpeed
	currentTalker
	global348
	global349
	global350
	songList
	global352
	global353
	global354
	global355
	global356
	global357
	cursorCycleCel
	global359
	global360
	paladinPoints
	origHeroType
	global363
	global364
	global365
	global366
	spellCost =  2
		spCostDetect =  2
		spCostTrigger =  3
		spCostDazzle =  3
		spCostZap =  3
		spCostCalm =  4
		spCostFlame =  5
		spCostFetch =  5
		spCostForce =  6
		spCostLevitate =  7
		spCostReversal =  8
		spCostJuggle =  8
		spCostStaff =  5
		spCostLightning =  10
		spCostFrostbite =  15
		spCostRitual =  20
		spCostInvisibility =  6
		spCostAura =  8
		spCostProtect =  7
		spCostResist =  10
		spCostGlide =  10
	global388
	global389
	global390
	global391
	global392
	disabledActions
	paladinStat
	kopeks
	global396
	global397
	global398
	global399
	global400
	global401
	restTime
	theMusic3
	global404
	global405
	hungerCounter
	totalEncumbrance
	global408
	global409
	global410
	global411
	global412
	gPuzzleBar
	gCurrentDay_4
	gCurrentDay_2
	gCurrentDay_5
	global417
	stovichState
	piotyrVisits
	piotyrDay =  100
	global421
	castleDoorDifficulty =  200
	global423
	global424
	gCurrentDay_12
	gCurrentDay_7
	global427
	global428
	global429
	global430
	gCurrentDay_8
	nextTalker
	defaultCycles =  6
	healFormula
	cureFormula
	rehydrateFormula
	global437
	isHandsOff
	arcadeLevel =  2
	reversalTimer
	projX
	projY
	global443
	userAlterEgo
	global445
	gCurrentDay_9
	gCurrentDay_10
	global448
	auraTimer
	numWeights =  8
	exerciseSteps
	global452
	protectTimer
	resistTimer
	global455
	global456
	global457
	global458
	geasDay
	global460 =  1
	global461
	gABad2Health
	gABad3Health
	gABad4Health
	global465
	global466 =  400
	global467
	global468
	gCurrentDay_6
	global470
	global471
	global472
	gCurrentDay_11
	global474
	gGGNumber
	gRChernovyX
	gRChernovyY
	global478
	poisonLevel
	global480 =  300
	gGABad1Health =  300
	gFShore
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
)
(procedure (CantUseMagic)
	(if (OneOf curRoomNum 710 720 740 750 760 770)
		(messager say: N_CANT_CAST_IN_CAVE NULL C_CANT_CAST 0 0 0)
	else
		(messager say: N_PRAGFAIL NULL C_CANT_CAST 0 0 0)
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

(procedure (CantUseMagic_5)
	;this is redundant, and does not seem to be used
	(if (OneOf curRoomNum 710 720 740 750 760 770)
		(messager say: N_CANT_CAST_IN_CAVE NULL C_CANT_CAST 0 0 0)
	else
		(messager say: N_PRAGFAIL NULL C_CANT_CAST 0 0 0)
	)
)

(procedure (Rand300)
	(return (+ 1 (/ (Random 0 2999) 10)))
)

(procedure (SaveTheCursor)
	(if (not (OneOf ((User curEvent?) message?) V_OPEN V_TRIGGER V_FETCH))
		(if (theIconBar curIcon?)
			(= oldCurs (theIconBar curIcon?))
		)
	else
		(= oldCurs 0)
	)
)

(procedure (RestoreTheCursor)
	(if oldCurs
		(theIconBar curIcon: oldCurs)
		(theGame setCursor: (theIconBar getCursor:))
		(= oldCurs 0)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: ICON_CAST))
				(not (theIconBar curInvIcon?))
			)
			(theIconBar advanceCurIcon:)
		)
	)
)

(procedure (NextDay)
	(Bclr fMetBurgomeisterToday)
	(Bclr fGeasWarning)
	(Bclr fGotManaFruit)
	(Bclr fDoneExercisingToday)
	(Bclr fMetAnnaTonight)
	(if (Btst fIgorMissing)
		(if (Btst fLastDayToSaveIgor)
			(if (not (Btst fIgorSaved))
				(Bset fIgorDead)
			)
		else
			(Bset fLastDayToSaveIgor)
		)
	)
	(return (++ Day))
)

(procedure (Face actor1 actor2 both whoToCue)
	(actor1
		setHeading:
			(GetAngle (actor1 x?) (actor1 y?) actor2 both)
			(if (== argc 4) whoToCue else 0)
	)
)

(procedure (DontMove)
	(theGame handsOn:)
	(theIconBar disable: ICON_WALK ICON_DO ICON_ACTIONS ICON_CAST ICON_USEIT ICON_INVENTORY)
)

(procedure (AutoTarget theX theY)
	(= projX 0)
	(= projY 0)
	(if argc
		(= projX theX)
		(if (> argc 1)
			(= projY theY)
		)
	)
)

(procedure (CyclePalette_13)
	;this appears to be the same as CyclePalette, but is unused
	(cond 
		(
			(OneOf curRoomNum
				551 552 553 554 555 556
				557 558 559 560 561 562
				563 564 565 566 567 568
				569 570 571 572 573 574
				575 576 577 578 579 580
				581 582 583 584 585 586
				587 588 589 590 591 592
				593 250 260 270 280 290
				300 440 460 480 500 520
				600 790 800 810
			)
			(cond 
				((and (< 770 Clock) (< Clock 871))
					(if (== Clock 771)
						(PalVary PalVaryKill)
					)
					(PalVary PalVaryStart
						(curRoom picture?) 0
						(- 870 Clock)
					)
				)
				(
				(and (< 2600 Clock) (< Clock 2701))
					(PalVary PalVaryStart
						(+ (curRoom picture?) 1) 0
						(- Clock 2600)
					)
				)
				(
				(and (< 2700 Clock) (< Clock 2801))
					(if (== Clock 2701)
						(PalVary PalVaryMergeSource (+ (curRoom picture?) 1))
					)
					(PalVary PalVaryStart
						(curRoom picture?)
						0
						(- Clock 2700)
					)
				)
				(
					(or
						(and (< 2800 Clock) (< Clock 3600))
						(and (<= 0 Clock) (<= Clock 771))
					)
					(PalVary PalVaryStart (curRoom picture?) 0 100)
				)
			)
		)
		((not (OneOf curRoomNum 180 320))
			(PalVary PalVaryKill)
		)
	)
)

(procedure (DisableIcons &tmp i theIcon)
	(= theIcon $8000)
	(for ((= i 0)) (<= i 10) ((++ i))
		(if (& disabledIcons theIcon)
			(theIconBar disable: i)
		)
		(= theIcon (>> theIcon $0001))
		(++ i)
	)
)

(procedure (CantUseMagic_15)
	;this is redundant, and does not seem to be used
	(if (OneOf curRoomNum 710 720 740 750 760 770)
		(messager say: N_CANT_CAST_IN_CAVE NULL C_CANT_CAST 0 0 0)
	else
		(messager say: N_PRAGFAIL NULL C_CANT_CAST 0 0 0)
	)
)

(procedure (CyclePalette)
	(cond 
		(
			(OneOf curRoomNum
				551 552 553 554 555 556
				557 558 559 560 561 562
				563 564 565 566 567 568
				569 570 571 572 573 574
				575 576 577 578 579 580
				581 582 583 584 585 586
				587 588 589 590 591 592
				593 250 260 270 280 290
				300 440 460 480 500 520
				600 790 800 810
			)
			(cond 
				((and (< 770 Clock) (< Clock 871))
					(PalVary PalVaryKill)
					(PalVary PalVaryStart
						(curRoom picture?) 0
						(- 870 Clock)
					)
				)
				((and (< 2600 Clock) (< Clock 2701))
					(PalVary PalVaryStart
						(+ (curRoom picture?) 1) 0
						(- Clock 2600)
					)
				)
				(
				(and (< 2700 Clock) (< Clock 2801))
					(PalVary PalVaryMergeSource (+ (curRoom picture?) 1))
					(PalVary PalVaryStart
						(curRoom picture?) 0
						(- Clock 2700)
					)
				)
				(
					(or
						(and (< 2800 Clock) (< Clock 4000))
						(and (<= 0 Clock) (<= Clock 771))
					)
					(PalVary PalVaryMergeSource (+ (curRoom picture?) 1))
					(PalVary PalVaryStart (curRoom picture?) 0 100)
				)
				((not (OneOf curRoomNum 180 320))
					(PalVary PalVaryKill)
				)
			)
		)
		((== curRoomNum 320)
			(PalVary PalVaryStart 320 0)
		)
	)
)

(procedure (DailyMsg n v c s m &tmp theMod i)
	(= i 0)
	(= theMod
		(if (> argc 4) m
		else
			curRoomNum)
		)
	(while (Message MsgSize theMod n v c (++ i))
		0
	)
	(messager say: n v c
			(Random 1 (- i 1))
			(if (> argc 3) s else 0)
			theMod
	)
)

(procedure (MaxStat what &tmp tmpStat)
	(switch what
		(HEALTH
			(= tmpStat (ego maxHealth:))
		)
		(STAMINA
			(= tmpStat (ego maxStamina:))
		)
		(MANA
			(= tmpStat (ego maxMana:))
		)
	)
	(return
		(if (> [egoStats what] 0)
			(-
				60
				(/ (* 60 (/ (* [egoStats what] 80) tmpStat)) 80)
			)
		else
			60
		)
	)
)

(procedure (CantUseMagic_19)
	;this is redundant, and does not seem to be used
	(if (OneOf curRoomNum 710 720 740 750 760 770)
		(messager say: N_CANT_CAST_IN_CAVE NULL C_CANT_CAST 0 0 0)
	else
		(messager say: N_PRAGFAIL NULL C_CANT_CAST 0 0 0)
	)
)

(procedure (CantUseMagic_20)
	;this is redundant, and does not seem to be used
	(if (OneOf curRoomNum 710 720 740 750 760 770)
		(messager say: N_CANT_CAST_IN_CAVE NULL C_CANT_CAST 0 0 0)
	else
		(messager say: N_PRAGFAIL NULL C_CANT_CAST 0 0 0)
	)
)

(procedure (localproc_2134 &tmp temp0 newStr temp2 temp3 temp4)
	(= newStr (String new:))
	(= temp2 (IntArray new: 21))
	(if
		(<
			(= temp0
				(GetSaveFiles
					(theGame name?)
					(newStr data?)
					(temp2 data?)
				)
			)
			0
		)
		(return 0)
	)
	(= temp3 0)
	(repeat
		(= temp4 0)
		(while (< temp4 temp0)
			(breakif (== temp3 (temp2 at: temp4)))
			(++ temp4)
		)
		(if (== temp4 temp0) (break))
		(++ temp3)
	)
	(newStr dispose:)
	(temp2 dispose:)
	(return temp3)
)

(procedure (localproc_21c5 param1 param2 param3 param4 &tmp temp0 temp1 newFile temp3 newStr)
	(= newStr (String new:))
	(MakeSaveCatName (newStr data?) (theGame name?))
	((= newFile (File new:)) name: (newStr data?) open: 2)
	(= temp1 0)
	(while (< temp1 param3)
		(if (!= temp1 param1)
			(= temp3 (param4 at: temp1))
			(newStr
				at: 0 (& temp3 $00ff)
				at: 1 (& (>> temp3 $0008) $00ff)
				at: 2 0
			)
			(newFile write: (newStr data?) 2)
			(= temp0 (String new: 36))
			(temp0 copyToFrom: 0 param2 (* temp1 36) 36)
			(newFile write: (temp0 data?) 36)
			(temp0 dispose:)
			(= temp0 0)
		)
		(++ temp1)
	)
	(newStr at: 0 255 at: 1 255)
	(newFile write: (newStr data?) 2 close: dispose:)
	(MakeSaveFileName
		(newStr data?)
		(theGame name?)
		(param4 at: param1)
	)
	(FileIO FileUnlink (newStr data?))
	(newStr dispose:)
)

(class Glory of Game
	
	(method (init &tmp egoSW [temp1 3])
		(Bset fHideCursor)
		(= systemPlane Plane)
		(= userName (String new: 40))
		((= ego (ScriptID GLORY_EGO 0)) view: 0)
		(Actor origStep: 1027)
		(View signal: ignrAct)
		(= machineSpeed 2)
		(= egoSW StopWalk)
		Timer
		Polygon
		PolyPath
		(super init:)
		(waitCursor view: 945 loop: 1 init:)
		(self setCursor: normalCursor)
		(statusPlane init:)
		(= doVerbCode gloryDoVerbCode)
		(= ftrInitializer gloryFtrInit)
		(= approachCode gloryApproachCode)
		(= messager gloryMessager)
		(= pMouse PseudoMouse)
		((= theMusic longSong)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= theMusic2 longSong2)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= theMusic3 longSong3)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= theIconBar (ScriptID GLORY_ICONS 0)) init:)
		((ScriptID GLORY_INV 0) init:)
		((ScriptID GLORY_MAGIC 0) init:)
		((ScriptID GLORY_INIT) init:)
	)
	
	(method (doit &tmp temp0 thisTime egoMover temp3)
		(super doit:)
		(if
			(and
				(or (!= mouseX oldCurX) (!= mouseY oldCurY))
				(== (Platform) PlatDos)
				(not (Btst fHideCursor))
				(user canControl:)
			)
			(switch (IconBarCursor view?)
				(940
					(if (> (++ cursorCycleCel) 15)
						(= cursorCycleCel 0)
					)
					(IconBarCursor setCel: cursorCycleCel)
				)
				(941
					(if (> (++ cursorCycleCel) 15)
						(= cursorCycleCel 0)
					)
					(IconBarCursor setCel: cursorCycleCel)
				)
				(942
					(if (> (++ cursorCycleCel) 15)
						(= cursorCycleCel 0)
					)
					(IconBarCursor setCel: cursorCycleCel)
				)
				(943
					(if (> (++ cursorCycleCel) 15)
						(= cursorCycleCel 0)
					)
					(IconBarCursor setCel: cursorCycleCel)
				)
			)
		)
		(= oldCurX mouseX)
		(= oldCurY mouseY)
		(if (and (Btst fInMainGame) (!= oldSysTime (= temp0 (GetTime SysTime1))))
			(= oldSysTime temp0)
			(if (not (Btst fFlag7))
				(= thisTime Clock)
				(if (>= (++ Clock) GAMEDAY)
					(= Clock 0)
					(NextDay)
				)
				(if (< (mod Clock GAMEHOUR) (mod thisTime GAMEHOUR))
					((ScriptID TIME 4) init:)
				)
				(if
					(and
						(not (Btst fGeasWarning))
						(< 2700 Clock)
						(< Clock 2801)
					)
					(if
						(and
							(>= Day (+ geasDay 4))
							(!= geasDay 0)
						)
						(DailyMsg N_CUE V_DOIT C_GEAS 0 0)
					)
					(Bset fGeasWarning)
				)
			)
			(CyclePalette)
			(if (and (< 820 Clock) (< Clock 2780))
				(= Night FALSE)
			else
				(= Night TRUE)
			)
			(if (and (not Night) (ego has: iWillowisp))
				(theMusic3 number: 912 play:)
				(ego drop: iWillowisp)
				(ego get: iFlask)
				(ego addHonor: -1000)
				(messager say: N_CUE V_DOIT C_WISP_DIES 0 0 0)
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
			(if (<= (-= hungerCounter 1) 0)
				(= hungerCounter 100)
				(if (Btst fStarving)
					(ego eatMeal:)
				)
			)
			(if (<= (-= stamCounter 1) 0)
				(= stamCounter 20)
				(cond 
					((or (> lostSleep 1) (Btst fOverloaded))
						(ego useStamina: 5)
						(if (Btst fOverloaded)
							(ego useStamina: (>> totalEncumbrance 7))
							(ego useSkill: STR 2)
						)
					)
					(
						(and
							(= egoMover (ego mover?))
							(== egoGait MOVE_RUN)
						)
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
						(if (or (> lostSleep 1) (Btst fStarving))
						else
							(Btst fPoisoned)
						)
					)
					(ego takeDamage: -1)
				)
				(if (Btst fPoisoned)
					(if (> poisonLevel 0)
						(-= poisonLevel 3)
						(cond 
							(
								(and
									(not (ego takeDamage: 3))
									(== newRoomNum curRoomNum)
								)
								(EgoDead C_DEATH_POISON GLORY_EGO 977 1)
							)
							((< [egoStats HEALTH] (>> (ego maxHealth:) 3))
								(messager say: N_HERO NULL C_POISON_WARNING 0 0 GLORY_EGO)
							)
						)
					else
						(= poisonLevel 0)
						(Bclr fPoisoned)
						((ScriptID 0 21) doit: curRoomNum)
					)
				)
			)
			(if (Btst fReversal)
				(switch (-- reversalTimer)
					(0
						(Bclr fReversal)
						(statusCode doit:)
						(messager say: NULL NULL C_REVERSAL_GONE 0 0 0)
					)
					(10
						(messager say: NULL NULL C_REVERSAL_GOING 0 0 0)
					)
				)
			)
			(if (and (Btst 149) (<= (-- global448) 0))
				(= global448 3)
				(if (not (ego useMana: 1))
					(curRoom notify: -2)
				)
			)
			(if
				(and
					(> auraTimer 0)
					(<= (-= auraTimer 10) 0)
				)
				(= auraTimer 0)
				(statusCode doit:)
				(messager say: NULL NULL C_AURA_GONE 0 0 0)
			)
			(if
				(and
					(> protectTimer 0)
					(<= (-= protectTimer 10) 0)
				)
				(if (Btst fHonorShield)
					(= protectTimer 1)
				else
					(= protectTimer 0)
					(statusCode doit:)
					(messager say: NULL NULL C_PROTECT_GONE 0 0 0)
				)
			)
			(if (and (> resistTimer 0) (<= (-- resistTimer) 0))
				(= resistTimer 0)
				(statusCode doit:)
				(messager say: NULL NULL C_RESIST_GONE 0 0 0)
			)
		)
		(if (Btst fRestartingGame)
			(Bclr fRestartingGame)
			(self restart: TRUE)
		)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(theGame setCursor: waitCursor TRUE)
		((ScriptID STARTAROOM 0) init: roomNum)
		(DisposeScript STARTAROOM)
		StopWalk
		Cycle
		(if (FileIO FileExists {18.scr})
			((ScriptID DEBUG 0) init:)
		)
		(if (not isHandsOff)
			(RestoreTheCursor)
			(theGame setCursor: IconBarCursor TRUE)
		)
		(if debugOn
			(SetDebug)
		)
		(super startRoom: roomNum)
		(statusCode doit: roomNum)
	)
	
	(method (restart &tmp i whichSkill temp2)
		(if (and (not argc) (not (ConfirmPrompt 0 NULL NULL C_RESTART 1)))
			(return)
		)
		(= doMotionCue FALSE)
		(theMusic stop:)
		(theMusic2 stop:)
		(theMusic3 stop:)
		(= curRoomNum 100)
		(statusCode doit: 100)
		(UpdatePlane
			((curRoom plane?) back: 0 picture: -1 yourself:)
		)
		(if (talkers size:)
			(messager cue: 1)
		)
		(PalCycle 4)
		(RemapColors 0)
		(features eachElementDo: #dispose release:)
		(cast eachElementDo: #dispose)
		(timers eachElementDo: #delete)
		(theDoits release:)
		(curRoom dispose:)
		(FrameOut)
		(= whichSkill (ScriptID GLORY_MAGIC 0))
		(for ((= i 0)) (< i TRIGGER) ((++ i))
			((whichSkill at: i) owner: 0)
		)
		(for ((= i 0)) (< i iLastInvItem) ((++ i))
			((inventory at: i)
				owner: 0
				state: 0
				amount: 0
				maskView: 0
				maskLoop: 0
				maskCel: 0
			)
		)
		(if (not (& ((= temp2 (ScriptID GLORY_ICONS 1)) signal?) $0008))
			(temp2 hide:)
		)
		(theIconBar curIcon: (theIconBar at: ICON_LOOK))
		(theIconBar advanceCurIcon:)
		(userName dispose:)
		(for ((= i 100)) (< i 699) ((++ i))
			(= [ego i] 0)
		)
		(= gameCode 1234)
		(= Day 1)
		(= stamCounter 20)
		(= healCounter 15)
		(= global138 9)
		(= global139 12)
		(= [targetAngles 0] 180)
		(= [targetAngles 1] 0)
		(= [targetAngles 2] 45)
		(= [targetAngles 3] 90)
		(= [targetAngles 4] 135)
		(= [targetAngles 5] 180)
		(= [targetAngles 6] 225)
		(= [targetAngles 6] 270)
		(= [targetAngles 6] 315)
		(= manaCounter 20)
		(= spellCost 2)
		(= spCostDetect 2)
		(= spCostTrigger 3)
		(= spCostDazzle 3)
		(= spCostZap 3)
		(= spCostCalm 4)
		(= spCostFlame 5)
		(= spCostFetch 5)
		(= spCostForce 6)
		(= spCostLevitate 7)
		(= spCostReversal 8)
		(= spCostJuggle 8)
		(= spCostStaff 5)
		(= spCostLightning 10)
		(= spCostFrostbite 15)
		(= spCostRitual 20)
		(= spCostInvisibility 6)
		(= spCostAura 8)
		(= spCostProtect 7)
		(= spCostResist 10)
		(= spCostGlide 10)
		(= piotyrDay 100)
		(= castleDoorDifficulty 150)
		(= arcadeLevel 2)
		(= global460 1)
		(= numWeights 8)
		(= defaultCycles 6)
		(= global466 400)
		(= userName (String new: 40))
		((= theMusic longSong)
			owner: self
			flags: mNOPAUSE
		)
		((= theMusic2 longSong2)
			owner: self
			flags: mNOPAUSE
		)
		((= theMusic3 longSong3)
			owner: self
			flags: mNOPAUSE
		)
		(= curRoom 0)
		((ScriptID GLORY_INIT) init:)
		(DisposeScript GLORY_INIT)
		(ego get: iPurse)
	)
	
	(method (save param1 &tmp
				newStr temp1 newStr_6 newStr_3 newStr_4
				temp5 newStr_2 newStr_5 newStr_5Size temp9 temp10 temp11
				temp12 temp13 newEvent temp15)
		(return
			(if (and (== argc 1) (== param1 1))
				(Bclr 380)
				(if (not (Btst fAutoSave)) (return FALSE))
				(= newStr (String new:))
				(= newStr_2 (String new:))
				(= temp1 (IntArray new: 21))
				(= newStr_3 (String new:))
				(= newStr_4 (String new:))
				(= newStr_5 (String new:))
				(= newStr_6 (String new:))
				(= temp9
					(GetSaveFiles name (newStr data?) (temp1 data?))
				)
				(Message MsgGet 0 0 0 16 1 (newStr_5 data?))
				(= temp11 0)
				(= temp10 -1)
				(= newStr_5Size (newStr_5 size:))
				(= temp12 0)
				(while (< temp12 temp9)
					(newStr_6
						copyToFrom: 0 newStr (* temp12 36) (+ newStr_5Size 1)
					)
					(if (= temp15 (newStr_6 compare: newStr_5))
						(= temp10 temp12)
						(break)
					)
					(++ temp12)
				)
				(if (>= temp10 0)
					(localproc_21c5 temp10 newStr temp9 temp1)
					(= temp9
						(GetSaveFiles name (newStr data?) (temp1 data?))
					)
					(while
						(and
							(> temp9 0)
							(not (CheckFreeSpace (curSaveDir data?)))
						)
						(localproc_21c5 (- temp9 1) newStr temp9 temp1)
						(= temp10
							(-
								(= temp9
									(GetSaveFiles name (newStr data?) (temp1 data?))
								)
								1
							)
						)
					)
					(if (CheckFreeSpace (curSaveDir data?))
						(Message MsgGet 0 0 0 16 1 (newStr_3 data?))
						(= temp13 (localproc_2134))
						(if
						(not (SaveGame name temp13 (newStr_3 data?) version))
							(Print addText: 0 0 17 1 0 0 0 init:)
							(newStr dispose:)
							(temp1 dispose:)
							(newStr_5 dispose:)
							(newStr_3 dispose:)
							(newStr_4 dispose:)
							(return (newStr_6 dispose:))
						else
							(if (not (if argc param1))
								(Print addText: 0 0 18 1 0 0 0 init:)
							)
							(= temp9
								(GetSaveFiles
									(theGame name?)
									(newStr data?)
									(temp1 data?)
								)
							)
						)
					else
						(Print addText: 0 0 19 1 0 0 0 init:)
						(newStr dispose:)
						(newStr_2 dispose:)
						(newStr_5 dispose:)
						(newStr_3 dispose:)
						(newStr_4 dispose:)
						(return (newStr_6 dispose:))
					)
				else
					(while
						(and
							(> temp9 0)
							(or
								(not (CheckFreeSpace (curSaveDir data?)))
								(>= temp9 20)
							)
						)
						(localproc_21c5 (- temp9 1) newStr temp9 temp1)
						(= temp9
							(GetSaveFiles
								(theGame name?)
								(newStr data?)
								(temp1 data?)
							)
						)
					)
					(Message MsgGet 0 0 0 16 1 (newStr_3 data?))
					(= temp13 (localproc_2134))
					(if
						(not
							(SaveGame
								name
								temp13
								(newStr_3 data?)
								(KString 9 version)
							)
						)
						(Print addText: 0 0 17 1 0 0 0 init:)
						(newStr dispose:)
						(temp1 dispose:)
						(newStr_5 dispose:)
						(newStr_3 dispose:)
						(newStr_4 dispose:)
						(return (newStr_6 dispose:))
					else
						(if (not (if argc param1))
							(Print addText: 0 0 18 1 0 0 0 init:)
						)
						(= temp9
							(GetSaveFiles
								(theGame name?)
								(newStr data?)
								(temp1 data?)
							)
						)
					)
				)
				(while ((= newEvent (Event new:)) type?)
					(newEvent dispose:)
				)
				(newEvent dispose:)
				(newStr dispose:)
				(newStr_5 dispose:)
				(temp1 dispose:)
				(newStr_3 dispose:)
				(newStr_4 dispose:)
				(newStr_6 dispose:)
			else
				(super save:)
			)
		)
	)
	
	(method (handleEvent event &tmp
					invCurView invCurLoop invCurCel invCurIcon invI theIcon
					curView curLoop curCel theCur)
		(cond 
			((== (event type?) keyDown)
				(switch (event message?)
					(TAB
						(if
							(and
								(User canInput:)
								(User canControl:)
								(not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
							)
							(if (talkers size:) (return (talkers size:)))
							(inventory showSelf: ego)
							(if
								(and
									(= invCurIcon (inventory curIcon?))
									(invCurIcon isKindOf: InvItem)
								)
								(= invCurView (invCurIcon view?))
								(= invCurLoop (invCurIcon loop?))
								(= invCurCel (invCurIcon cel?))
								(= invI (ScriptID GLORY_ICONS 1))
								((= theIcon (ScriptID GLORY_ICONS 2))
									cursorView: invCurView
								)
								(theIcon cursorLoop: invCurLoop)
								(theIcon cursorCel: invCurCel)
								(invI
									view: invCurView
									loop: invCurLoop
									cel: invCurCel
									show:
								)
								(UpdateScreenItem invI)
								(self
									setCursor:
										(IconBarCursor
											view: invCurView
											loop: invCurLoop
											cel: invCurCel
											yourself:
										)
								)
							)
							(event claimed: TRUE)
						)
					)
					(`^q
						(if
							(and
								(User canInput:)
								(User canControl:)
								(not (Btst fCantSave))
							)
							(theGame quitGame:)
							(event claimed: TRUE)
						)
					)
					(22
						(if
							(and
								(User canInput:)
								(User canControl:)
								(not (Btst fCantSave))
							)
							((ScriptID CHARSHEET) doit:)
							(DisposeScript CHARSHEET)
						)
					)
					(`^c
						(if
							(and
								(not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(not (Btst fCantSave))
							)
							(= theCur (theIconBar getCursor:))
							(= curView (theCursor view?))
							(= curLoop (theCursor loop?))
							(= curCel (theCursor cel?))
							(self
								setCursor: (theCur view: 999 loop: 0 cel: 0 yourself:)
							)
							((ScriptID GLORY_CONTROLS) init: show: dispose:)
							(DisposeScript GLORY_CONTROLS)
							(DisposeScript GLORY_ABOUT)
							(self
								setCursor:
									(theCur
										view: curView
										loop: curLoop
										cel: curCel
										yourself:
									)
							)
						)
					)
					(`#2
						(cond 
							((theGame masterVolume:)
								(theGame masterVolume: 0)
							)
							((> numVoices 1)
								(theGame masterVolume: 15)
							)
							(else
								(theGame masterVolume: 1)
							)
						)
						(event claimed: TRUE)
					)
					(`#5
						(if
							(and
								(not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(not (Btst fCantSave))
							)
							(if (talkers size:)
								(return (talkers size:))
							)
							(theGame save:)
							(event claimed: TRUE)
						)
					)
					(`#7
						(if
							(and
								(not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(not (Btst fCantSave))
							)
							(if (talkers size:)
								(return (talkers size:))
							)
							(theGame restore:)
							(event claimed: TRUE)
						)
					)
					(`#9
						(if
							(and
								(User canInput:)
								(User canControl:)
								(not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
							)
							(if (talkers size:)
								(return (talkers size:))
							)
							(theGame restart:)
							(event claimed: TRUE)
						)
					)
					(`+
						(if (user controls?)
							(= egoMoveSpeed (Max 0 (-- egoMoveSpeed)))
							(ego cycleSpeed: egoMoveSpeed moveSpeed: egoMoveSpeed)
						)
					)
					(`-
						(if (user controls?)
							(++ egoMoveSpeed)
							(ego cycleSpeed: egoMoveSpeed moveSpeed: egoMoveSpeed)
						)
					)
					(`=
						(if (user controls?)
							(ego cycleSpeed: 6 moveSpeed: 6)
						)
					)
				)
			)
			((== (event type?) mouseUp)
				(mouseDownHandler handleEvent: event)
			)
			(else
				(super handleEvent: event)
			)
		)
		(return
			(if (event claimed?)
				(return TRUE)
			else
				FALSE
			)
		)
	)
	
	(method (setCursor)
		(return
			(if (not (Btst fHideCursor))
				(return (super setCursor: &rest))
			else
				(return theCursor)
			)
		)
	)
	
	(method (quitGame)
		(if (ConfirmPrompt 0 NULL NULL C_QUIT 1)
			(= quit TRUE)
		)
	)
	
	(method (detailLevel the_detailLevel)
		(if argc
			(planes
				eachElementDo: #checkDetail (= _detailLevel the_detailLevel)
			)
		)
		(return _detailLevel)
	)
	
	(method (pragmaFail &tmp theVerb)
		(if (User canInput:)
			(switch (= theVerb ((User curEvent?) message?))
				(V_LOOK
					(messager say: N_PRAGFAIL V_LOOK NULL 0 0 0)
				)
				(V_DO
					(messager say: N_PRAGFAIL V_DO NULL 0 0 0)
				)
				(V_TALK
					(messager say: N_PRAGFAIL V_TALK NULL 0 0 0)
				)
				(V_SLEEP
					(if
						(OneOf curRoomNum
							440 270 330 350 740 730 530 535
							541 542 543 545
						)
						(curRoom doVerb: V_SLEEP)
					else
						(messager say: N_PRAGFAIL NULL C_CANT_SLEEP 0 0 0)
					)
				)
				(V_FLAME
					(if
						(OneOf curRoomNum
							440 340 360 551 552 553 554 555
							556 557 558 559 560 561 562 563
							564 565 566 567 568 569 570 571
							572 573 574 575 576 577 578 579
							580 581 582 583 584 585 586 587
							588 589 590 591 592 593 270 545
							600 650 670 680 625 750 780 460
							530 535 541 542 543 545 520 800
							632 740 710 730
						)
						(curRoom doVerb: V_FLAME)
					else
						(CantUseMagic)
					)
				)
				(V_FORCEBOLT
					(if
						(OneOf curRoomNum
							440 340 360 551 552 553 554 555
							556 557 558 559 560 561 562 563
							564 565 566 567 568 569 570 571
							572 573 574 575 576 577 578 579
							580 581 582 583 584 585 586 587
							588 589 590 591 592 593 270 545
							600 650 670 680 625 460 530 535
							541 542 543 545 520 632 710 730
							740 750 770 800
						)
						(curRoom doVerb: V_FORCEBOLT)
					else
						(CantUseMagic)
					)
				)
				(V_LIGHTNING
					(if
						(OneOf curRoomNum
							270 340 360 440 545 551 552 553
							554 555 556 557 558 559 560 561
							562 563 564 565 566 567 568 569
							570 571 572 573 574 575 576 577
							578 579 580 581 582 583 584 585
							586 587 588 589 590 591 592 593
							600 650 670 680 625 460 530 535
							541 542 543 545 520 632 740 710
							750 760 730 770 800
						)
						(curRoom doVerb: V_LIGHTNING)
					else
						(CantUseMagic)
					)
				)
				(V_OPEN
					(if
						(OneOf curRoomNum
							250 260 270 340 440 500 510 593
							641 643 660 610 662 670 600 661
							662 780 460 470 790 620 621 622
							623 624 625 626 627 629 630 631
							632 640 642 644 633 634 645 663
							680 300
						)
						(curRoom doVerb: V_OPEN)
					else
						(CantUseMagic)
					)
				)
				(81
					(if
						(OneOf curRoomNum
							250 270 290 300 320 340 360 780
							440 480 510 545 579 580 593 600
							800 720 740 750 770 730 530 535
							541 542 543 545
						)
						(curRoom doVerb: 81)
					else
						(messager say: N_PRAGFAIL NULL C_CANT_CAST 0 0 0)
					)
				)
				(V_TRIGGER
					(if
						(OneOf curRoomNum
							270 290 340 440 460 520 580 593
							600 641 643 650 750 800
						)
						(curRoom doVerb: V_TRIGGER)
					else
						(CantUseMagic)
					)
				)
				(V_DAZZLE
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 270 340 460 520 545
							600 625 650 670 710 730 750 770
						)
						(curRoom doVerb: V_DAZZLE)
					else
						(CantUseMagic)
					)
				)
				(V_CALM
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 270 340 460 545 710
							750 730 632
						)
						(curRoom doVerb: V_CALM)
					else
						(CantUseMagic)
					)
				)
				(V_FETCH
					(cond 
						(
							(OneOf curRoomNum
								270 340 440 460 470 551 552
								553 554 555 556 557 558 559
								560 561 562 563 564 565 566
								567 568 569 570 571 572 573
								574 575 576 577 578 579 580
								581 582 583 584 585 586 587
								588 589 590 591 592 593 670
								710 750 800 530 535 541 542
								543 545 790 290
							)
							(curRoom doVerb: V_FETCH)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_BAD_POSITION 1 0 0)
						)
						(else
							(ego setScript: (ScriptID CASTFETCH))
						)
					)
				)
				(V_LEVITATE
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 250 260 270 280 290
							330 340 600 710 720 800 740 750
							730
						)
						(curRoom doVerb: V_LEVITATE)
					else
						(CantUseMagic)
					)
				)
				(V_STAFF
					(if (OneOf curRoomNum 270 340 460 670 740 750 730)
						(curRoom doVerb: V_STAFF)
					else
						(CantUseMagic)
					)
				)
				(V_REVERSAL
					(if (OneOf curRoomNum 270 340 460 750)
						(curRoom doVerb: V_REVERSAL)
					else
						(CantUseMagic)
					)
				)
				(V_RITUAL
					(if (OneOf curRoomNum 270 340 750)
						(curRoom doVerb: V_RITUAL)
					else
						(CantUseMagic)
					)
				)
				(V_JUGGLE
					(if
						(OneOf curRoomNum
							270 340 390 520 530 535 541 542
							543 545 600 632 630 670 680 750
							770 800
						)
						(curRoom doVerb: V_JUGGLE)
					else
						(CantUseMagic)
					)
				)
				(V_HEAL
					(messager say: N_PRAGFAIL V_HEAL C_CANT_HEAL 0 0 0)
				)
				(V_DAGGER
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 600 650 670 680 625
							632 460 520 800 740 730
						)
						(curRoom doVerb: V_DAGGER)
					else
						(messager say: N_PRAGFAIL NULL C_CANT_DO 0 0 0)
					)
				)
				(V_ROCK
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 535 600 650 670 680
							625 632 460 520 800 740 730 545
						)
						(curRoom doVerb: V_ROCK)
					else
						(messager say: N_PRAGFAIL NULL C_CANT_DO 0 0 0)
					)
				)
				(V_GRAPNEL
					(if (OneOf curRoomNum 0)
						(curRoom doVerb: V_GRAPNEL)
					else
						(messager say: N_PRAGFAIL NULL C_CANT_DO 0 0 0)
					)
				)
				(V_JUMP
					(messager say: N_PRAGFAIL NULL C_CANT_JUMP 0 0 0)
				)
				(else 
					(messager say: N_PRAGFAIL NULL C_NOTHING_HAPPENS 0 0 0)
				)
			)
		)
	)
	
	(method (handsOff)
		(if (not isHandsOff)
			(= isHandsOff TRUE)
			(SaveTheCursor)
		)
		(user canControl: FALSE canInput: FALSE)
		(= disabledIcons NULL)
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
	
	(method (handsOn allHands &tmp temp0)
		(= isHandsOff FALSE)
		(user canControl: TRUE canInput: TRUE)
		(theIconBar enable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_ACTIONS
			ICON_CAST
			ICON_USEIT
			ICON_INVENTORY
		)
		(RestoreTheCursor)
		(if (not (theIconBar curInvIcon?))
			(theIconBar disable: ICON_USEIT)
		)
		(if (not (HaveMouse))
			(theGame
				setCursor: (theIconBar getCursor:) TRUE oldCurX oldCurY
			)
		else
			(theGame setCursor: (theIconBar getCursor:) TRUE)
		)
		(if (> argc 0)
			(theIconBar disable: allHands &rest)
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

(instance gloryDoVerbCode of Code
	
	(method (doit theVerb &tmp modBuf)
		(= modBuf (String new:))
		(modBuf format: {%d.msg} curRoomNum)
		(if
			(and
				(FileIO FileExists (modBuf data?))
				(Message MsgSize curRoomNum NULL theVerb 0 1)
			)
			(messager say: NULL theVerb NULL 0 0 curRoomNum)
		else
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
							440 270 330 350 740 730 530 535
							541 542 543 545
						)
						(curRoom doVerb: V_SLEEP)
					else
						(messager say: N_PRAGFAIL NULL C_CANT_SLEEP 0 0 0)
					)
				)
				(V_FLAME
					(if
						(OneOf curRoomNum
							440 340 360 551 552 553 554 555
							556 557 558 559 560 561 562 563
							564 565 566 567 568 569 570 571
							572 573 574 575 576 577 578 579
							580 581 582 583 584 585 586 587
							588 589 590 591 592 593 270 545
							600 650 670 680 625 750 780 460
							530 535 541 542 543 545 520 800
							632 740 710 730
						)
						(curRoom doVerb: V_FLAME)
					else
						(CantUseMagic)
					)
				)
				(V_FORCEBOLT
					(if
						(OneOf curRoomNum
							440 340 360 551 552 553 554 555
							556 557 558 559 560 561 562 563
							564 565 566 567 568 569 570 571
							572 573 574 575 576 577 578 579
							580 581 582 583 584 585 586 587
							588 589 590 591 592 593 270 545
							600 650 670 680 625 460 530 535
							541 542 543 545 520 632 710 730
							740 750 770 800
						)
						(curRoom doVerb: V_FORCEBOLT)
					else
						(CantUseMagic)
					)
				)
				(V_LIGHTNING
					(if
						(OneOf curRoomNum
							270 340 360 440 545 551 552 553
							554 555 556 557 558 559 560 561
							562 563 564 565 566 567 568 569
							570 571 572 573 574 575 576 577
							578 579 580 581 582 583 584 585
							586 587 588 589 590 591 592 593
							600 650 670 680 625 460 530 535
							541 542 543 545 520 632 740 710
							750 760 730 770 800
						)
						(curRoom doVerb: V_LIGHTNING)
					else
						(CantUseMagic)
					)
				)
				(V_OPEN
					(if
						(OneOf curRoomNum
							250 260 270 340 440 500 510 593
							641 643 660 610 662 670 600 661
							662 780 460 470 790 620 621 622
							623 624 625 626 627 629 630 631
							632 640 642 644 633 634 645 663
							680 300
						)
						(curRoom doVerb: V_OPEN)
					else
						(CantUseMagic)
					)
				)
				(V_DETECT
					(if
						(OneOf curRoomNum
							250 270 290 300 320 340 360 780
							440 480 510 545 579 580 593 600
							800 720 740 750 770 730 530 535
							541 542 543 545
						)
						(curRoom doVerb: V_DETECT)
					else
						(messager say: N_VERB_GENERIC NULL C_CANT_CAST_THAT_HERE 0 0 0)
					)
				)
				(V_TRIGGER
					(if
						(OneOf curRoomNum
							270 290 340 440 460 520 580 593
							600 641 643 650 750 800
						)
						(curRoom doVerb: V_TRIGGER)
					else
						(CantUseMagic)
					)
				)
				(V_DAZZLE
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 270 340 460 520 545
							600 625 650 670 710 730 750 770
						)
						(curRoom doVerb: V_DAZZLE)
					else
						(CantUseMagic)
					)
				)
				(V_CALM
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 270 340 460 545 710
							750 730 632
						)
						(curRoom doVerb: V_CALM)
					else
						(CantUseMagic)
					)
				)
				(V_FETCH
					(cond 
						(
							(OneOf curRoomNum
								270 340 440 460 470 551 552
								553 554 555 556 557 558 559
								560 561 562 563 564 565 566
								567 568 569 570 571 572 573
								574 575 576 577 578 579 580
								581 582 583 584 585 586 587
								588 589 590 591 592 593 670
								710 750 800 530 535 541 542
								543 545 790 290
							)
							(curRoom doVerb: V_FETCH)
						)
						((> (ego view?) 5)
							(messager say: NULL NULL C_BAD_POSITION 1 0 0)
						)
						(else
							(ego setScript: (ScriptID CASTFETCH))
						)
					)
				)
				(V_LEVITATE
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 250 260 270 280 290
							330 340 600 710 720 800 740 750
							730
						)
						(curRoom doVerb: V_LEVITATE)
					else
						(CantUseMagic)
					)
				)
				(V_STAFF
					(if (OneOf curRoomNum 270 340 460 670 740 750 730)
						(curRoom doVerb: V_STAFF)
					else
						(CantUseMagic)
					)
				)
				(V_REVERSAL
					(if (OneOf curRoomNum 270 340 460 750)
						(curRoom doVerb: V_REVERSAL)
					else
						(CantUseMagic)
					)
				)
				(V_JUGGLE
					(if
						(OneOf curRoomNum
							270 340 390 520 530 535 541 542
							543 545 600 632 630 670 680 750
							770 800
						)
						(curRoom doVerb: V_JUGGLE)
					else
						(CantUseMagic)
					)
				)
				(V_HEAL
					(messager say: N_VERB_GENERIC V_HEAL C_NOTHING_TO_HEAL 0 0 0)
				)
				(V_DAGGER
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 600 650 670 680 625
							632 460 520 800 740 730
						)
						(curRoom doVerb: V_DAGGER)
					else
						(messager say: N_VERB_GENERIC NULL C_CANT_DO 0 0 0)
					)
				)
				(V_GRAPNEL
					(if (OneOf curRoomNum 0)
						(curRoom doVerb: V_GRAPNEL)
					else
						(messager say: N_VERB_GENERIC NULL C_CANT_DO 0 0 0)
					)
				)
				(V_ROCK
					(if
						(OneOf curRoomNum
							551 552 553 554 555 556 557 558
							559 560 561 562 563 564 565 566
							567 568 569 570 571 572 573 574
							575 576 577 578 579 580 581 582
							583 584 585 586 587 588 589 590
							591 592 593 535 600 650 670 680
							625 632 460 520 800 740 730 545
						)
						(curRoom doVerb: V_ROCK)
					else
						(messager say: N_VERB_GENERIC NULL C_CANT_DO 0 0 0)
					)
				)
				(V_JUMP
					(messager say: N_PRAGFAIL NULL C_CANT_JUMP 0 0 0)
				)
				(else 
					(messager say: N_VERB_GENERIC NULL C_DIDNT_DO_ANY_GOOD 0 0 0)
				)
			)
		)
		(modBuf dispose:)
	)
)

(instance gloryFtrInit of Code

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

(instance gloryApproachCode of Code
	
	(method (doit theVerb)
		(return
			(cond 
				((== theVerb V_LOOK) $0001)
				((== theVerb V_TALK) $0002)
				((== theVerb V_WALK) $0004)
				((== theVerb V_DO) $0008)
				((and (<= V_PURSE theVerb) (<= theVerb V_KNOB)) $0010)
				((and (<= V_OPEN theVerb) (<= theVerb V_LIGHTNING)) $0020)
				((== theVerb -1) -1)
				(else $8000)
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
	
	(method (new theNoun theVerb theCase theSeq theTalker theMod &tmp obj)
		((= obj (Clone self))
			said: 0
			argCount: argc
			noun: theNoun
			verb: theVerb
			case: theCase
		)
		(if (> argc 2)
			(obj seq: theSeq)
			(if (> argc 3)
				(obj who: theTalker)
				(if (> argc 4)
					(obj module: theMod)
				)
			)
		)
		(theDoits add: obj)
	)
	
	(method (doit)
		(if (and (not said) (not ((ScriptID MESSAGER 1) size:)))
			(= said TRUE)
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

(instance gloryMessager of Messager

	(method (dispose)
		(super dispose: &rest)
		(= currentTalker 0)
		(= nextTalker 0)
		(Bclr fFlag165)
		(if (not (Btst fInPuzzle))
			(Bclr fCantSave)
			(if (Btst fSomeoneTalking)
				(Bclr fSomeoneTalking)
				(statusCode doit:)
			)
		)
	)
	
	(method (say)
		(if (== newRoomNum curRoomNum)
			(= nextTalker 0)
			(if ((ScriptID MESSAGER 1) size:)
				(MessObj new: &rest)
			else
				(Bclr fFlag165)
				(super say: &rest)
			)
		)
	)
	
	(method (findTalker who &tmp theTalker)
		(return
			(if
				(= theTalker
					(switch who
						(NARRATOR narrator)
						(AVIS (ScriptID AVIS_TALKER))
						(BABA (ScriptID 490 1))
						(BONEHEAD (ScriptID 480 1))
						(BURGOMEISTER (ScriptID BURGO_TALKER))
						(CRANIUM (ScriptID CRANIUM_TALKER))
						(ANNA (ScriptID ANNA_TALKER))
						(BELLA (ScriptID BELLA_TALKER))
						(TANYA (ScriptID TANYA_TALKER))
						(TANYA_VAMPIRE (ScriptID TANYA_VAMP_TALKER))
						(DOMOVOI (ScriptID DOMOVOI_TALKER))
						(HANS (ScriptID HANS_TALKER))
						(FRANZ (ScriptID FRANZ_TALKER))
						(IVAN (ScriptID IVAN_TALKER))
						(GNOME (ScriptID GNOME_TALKER))
						(GYPSY (ScriptID GYPSY_TALKER))
						(IGOR (ScriptID IGOR_TALKER))
						(INNKEEPER (ScriptID INNKEEPER_TALKER))
						(KATRINA (ScriptID KATRINA_TALKER))
						(KATRINA_HOOD (ScriptID KATRINA_HOOD_TALKER))
						(KATRINA_RED (ScriptID KATRINA_RED_TALKER))
						(KATRINA_BLACK (ScriptID KATRINA_BLACK_TALKER))
						(GATEKEEPER (ScriptID GATEKEEPER_TALKER))
						(LESHY (ScriptID LESHY_TALKER))
						(NIKOLAI (ScriptID NIKOLAI_TALKER))
						(OLGA (ScriptID OLGA_TALKER))
						(PIOTYR (ScriptID 270 2))
						(GOON1 (ScriptID 629 1))
						(GOON2 (ScriptID 629 2))
						(RUSALKA_YOUNG (ScriptID 520 1))
						(RUSALKA_OLD (ScriptID 520 2))
						(TOBY (ScriptID TOBY_TALKER))
						(TOBY_NICE (ScriptID TOBY_NICE_TALKER))
						(QUEEN (ScriptID 580 1))
						(ERANA (ScriptID 730 1))
						(CHIEF (ScriptID CHIEF_TALKER))
						(DAVY (ScriptID 470 1))
						(MAGDA (ScriptID 470 2))
						(ERASMUS (ScriptID ERASMUS_TALKER))
						(FENRUS (ScriptID FENRUS_TALKER))
						(NIKOLAI_GHOST (ScriptID NIKOLAI_GHOST_TALKER))
						(else  narrator)
					)
				)
				(if (theTalker isKindOf: Talker)
					(if (and currentTalker (!= currentTalker theTalker))
						(= nextTalker currentTalker)
					)
					(= currentTalker theTalker)
				)
				(return theTalker)
			else
				(super findTalker: who)
			)
		)
	)
)

(class GlorySong of Sound
	(properties
		numSongs 0
	)
	
	(method (check)
		(if handle (DoSound SndUpdateCues self))
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(if client
				(client cue: self)
			)
			(if (and numSongs (== prevSignal -1))
				(self
					number: [songList (Random 0 (- numSongs 1))]
					setLoop: 1
					play:
				)
			)
		)
	)
	
	(method (doSongs soundNum &tmp i)
		(= numSongs argc)
		(if numSongs
			(for ((= i 0)) (< i argc) ((++ i))
				(= [songList i] [soundNum i])
			)
			(self
				number: [songList (Random 0 (- numSongs 1))]
				setLoop: 1
				play:
			)
		else
			(self setLoop: -1)
		)
	)
)

(instance longSong of GlorySong)

(instance longSong2 of GlorySong)

(instance longSong3 of GlorySong)

(instance statHealth of View
	(properties
		x 20
		view 907
		loop 2
	)
)

(instance statStam of View
	(properties
		x 100
		view 907
		loop 2
	)
)

(instance statMana of View
	(properties
		x 180
		view 907
		loop 2
	)
)

(instance statManaBack of View
	(properties
		x 171
		y 1
		view 907
		loop 1
	)
)

(instance statReverse of View
	(properties
		x 250
		view 907
	)
)

(instance statZap of View
	(properties
		x 264
		view 907
		cel 1
	)
)

(instance statResist of View
	(properties
		x 278
		view 907
		cel 2
	)
)

(instance statAura of View
	(properties
		x 292
		view 907
		cel 3
	)
)

(instance statProtect of View
	(properties
		x 306
		view 907
		cel 4
	)
)

(instance statusBitmap of View
	(properties
		view 907
		loop 6
	)
)

(instance statusPlane of Plane
	
	(method (init &tmp statCast)
		(self priority: (+ (GetHighPlanePri) 1))
		(super init: 0 0 0 0 -1 -1 -1 -1)
		(self addCast: (= statCast (Cast new:)))
		(statusBitmap
			view: 907
			loop: 3
			cel: 0
			posn: 0 0
			setPri: (+ priority 1)
			init: statCast
		)
		(statHealth setPri: (+ priority 2) init: statCast)
		(statStam setPri: (+ priority 2) init: statCast)
		(statManaBack setPri: (+ priority 2) init: statCast hide:)
		(statMana setPri: (+ priority 3) init: statCast hide:)
		(statReverse setPri: (+ priority 2) init: statCast)
		(statZap setPri: (+ priority 2) init: statCast)
		(statResist setPri: (+ priority 2) init: statCast)
		(statAura setPri: (+ priority 2) init: statCast)
		(statProtect setPri: (+ priority 2) init: statCast)
	)
)

(instance statusCode of Code
	
	(method (doit roomNum &tmp statNum)
		(if (not argc)
			(= roomNum curRoomNum)
		)
		(if
			(OneOf roomNum
				4 52 54 100 101 110
				140 160 180 810 475
			)
			(statusPlane setInsetRect: -1 -1 -1 -1)
			(UpdatePlane statusPlane)
		else
			(if (Btst fInPuzzle) (return))
			(statusPlane setRect: 0 0 319 9)
			(= statNum (MaxStat HEALTH))
			(statHealth
				cel: (if (Btst fPoisoned) 1 else 0)
				x: (- 20 statNum)
				setInsetRect: statNum 0 59 9
			)
			(UpdateScreenItem statHealth)
			(= statNum (MaxStat STAMINA))
			(statStam x: (- 100 statNum) setInsetRect: statNum 0 59 9)
			(UpdateScreenItem statStam)
			(if
				(and
					[egoStats MAGIC]
					[egoStats MANA]
					(/ (+ [egoStats INT] [egoStats MANA] [egoStats MANA]) 3)
				)
				(= statNum (MaxStat MANA))
				(statMana
					x: (- 180 statNum)
					setInsetRect: statNum 0 59 9
					show:
				)
				(statManaBack show:)
				(UpdateScreenItem statMana)
				(UpdateScreenItem statManaBack)
			)
			(if (Btst fReversal)
				(statReverse show:)
			else
				(statReverse hide:)
			)
			(if zapPower
				(statZap show:)
			else
				(statZap hide:)
			)
			(if resistTimer
				(statResist show:)
			else
				(statResist hide:)
			)
			(if auraTimer
				(statAura show:)
			else
				(statAura hide:)
			)
			(if protectTimer
				(statProtect show:)
			else
				(statProtect hide:)
			)
			(UpdatePlane statusPlane)
		)
	)
)
