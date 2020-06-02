;;; Sierra Script 1.0 - (do not remove this comment)
(script# MAIN) ;0
(include game.sh) (include "999.shm") (include "814.shm")
(use GameInit)
(use CastFlame)
(use CastDagger)
(use CastRock)
(use GloryWindow)
(use Procs)
(use StartARoom)
(use Print)
(use Messager)
(use PMouse)
(use SlideIcon)
(use IconBar)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Timer)
(use Grooper)
(use GControl)
(use Sound)
(use Motion)
(use Game)
(use User)
(use System)

(public
	Glory 0
	stopGroop 1
	HandsOff 2
	HandsOn 3
	Bset 5
	Bclr 6
	Btst 7
	mainIconBar 8
	statusCode 9
	gcWin 10
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
	egoGait
	dongle =  1234
	isHandsOff
	egoX
	egoY
	debugging
	cSound
	daySheriffBreakIn
	dayLOLBreakIn
	dayCursedByBabaYaga
	exploringTown
	yesNoTimer
	lastRestTime
	lastRestDay
	monsterDistX
	monsterDistY
	Clock
	Night
	Day
	timeODay
	barNote
	oldSysTime
	heroType
	egoSpeed
	startingRoom
	egoStats
		gIntell
		gAgil
		gVit
		gLuck
		gWeap
		gParry
		gDodge
		global133
		global134
		global135
		global136
		gMagic
		global138
		gHealth
		gStamina
		gMana
		global142
		global143
		global144
		global145
		global146
		global147
		global148
		global149
	skillTicks
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
	lockPickBonus
	spellCost
		spCostOpen =  2
		spCostDetect =  2
		spCostTrigger =  3
		spCostDazzle =  3
		spCostZap =  3
		spCostCalm =  4
		spCostFlame =  5
		spCostFetch =  5
		spellCostEnd
	global186
	global187
	global188
	global189
	global190
	spellMask
	theBuyDialog
	wareList
	global194
	numColors
	numVoices
	stamCounter =  20
	healCounter =  15
	freeMeals
	ghostOilTimer
	oldStats
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
	zapPower
	monsterDazzle
	targetAngles =  180
		global232
		global233 =  45
		global234 =  90
		global235 =  135
		global236 =  180
		global237 =  225
		global238 =  270
		global239 =  315
	numFlowers
	numMushrooms
	numWater
	brigandHead
	numBrigands
	egoCanFight
	masterDay =  -1
	lostSleep
	totalDagNabItBet
	thievesPassword
	missedDaggers
	hitDaggers
	daggerRoom
	sameColor =  42
	changeColor =  54
	shieldRoom
	mandrakeDay =  -3
	dftStatusCode
	wizGameSpellTime
	wizAskedSpells
	koboldIllBits =  cWHITE
	dayKoboldAwakened =  -1
	bucks
	theKobold
	koboldCycles
	ogreDay
	ogreTime
	fightingKoboldStart
	fightingKobold
	dartsBonus
	statusBarView
	global271
	global272
	hutState
	babaState
	deathMusic =  26
	numApples
	nestState
	numGoblins
	monsterNum
	monsterHealth
	brunoTimer
	koboldHealth
	koboldEvade
	damageToKobold
	damageToKoboldFlame
	egoKoboldBattleLoop
	enter67
	mountainSign
	global289
	gameFlags
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
	invDropped
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
	ogreX =  160
	ogreY =  120
	ogreHealth =  93
	ogreDeathDay =  1000
	brutusHealth
	manaCounter =  5
	spareSound
	magesMazePlayCount
	disabledIcons
	global419
	oldMouseX
	oldMouseY
	oldIcon
	global423
	oldScore
	disabledActions
	gClient
	targetDaggers
	userName
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
	nightPalette
	global452
)
(procedure (HandsOff)
	(if isHandsOff (return) (DisposeScript PROCS))
	(= isHandsOff TRUE)
	(SaveTheCursor)
	(User canControl: FALSE canInput: FALSE)
	(= egoSpeed speed)
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
		(= oldMouseX mouseX)
		(= oldMouseY mouseY)
		(theGame setCursor: waitCursor TRUE 310 185)
	else
		(theGame setCursor: waitCursor TRUE)
	)
)

(procedure (HandsOn &tmp temp0)
	(asm
		lag      isHandsOff
		bnt      code_1468
		ldi      0
		sag      isHandsOff
		pushi    #setSpeed
		pushi    1
		lsg      egoSpeed
		lag      theGame
		send     6
		ldi      6
		sag      egoSpeed
		pushi    #canControl
		pushi    1
		pushi    1
		pushi    347
		pushi    1
		pushi    1
		class    User
		send     12
code_1468:
		pushi    #enable
		pushi    9
		pushi    1
		pushi    2
		pushi    3
		pushi    4
		pushi    5
		pushi    6
		pushi    7
		pushi    8
		pushi    9
		lag      theIconBar
		send     22
		pushi    14
		pushi    #x
		pushi    #signal
		pushi    0
		lag      ego
		send     4
		push    
		ldi      4096
		or      
		push    
		lag      ego
		send     6
		pushi    0
		calle    RestoreTheCursor,  0
		pushi    #curInvIcon
		pushi    0
		lag      theIconBar
		send     4
		not     
		bnt      code_14b2
		pushi    #disable
		pushi    1
		pushi    7
		lag      theIconBar
		send     6
code_14b2:
		ldi      12
		lagi     egoStats
		not     
		bt       code_14e5
		ldi      17
		sat      temp0
code_14be:
		lst      temp0
		ldi      24
		le?     
		bnt      code_14e1
		pushi    #knows
		pushi    1
		lst      temp0
		lag      ego
		send     6
		bnt      code_14dd
		ldi      1
		ret     
		pushi    1
		pushi    814
		callk    DisposeScript,  2
code_14dd:
		+at      temp0
		jmp      code_14be
code_14e1:
		not     
		bnt      code_14ef
code_14e5:
		pushi    #disable
		pushi    1
		pushi    6
		lag      theIconBar
		send     6
code_14ef:
		pushi    #curInvIcon
		pushi    0
		lag      theIconBar
		send     4
		not     
		bnt      code_1519
		pushi    #curIcon
		pushi    0
		lag      theIconBar
		send     4
		push    
		pushi    #at
		pushi    1
		pushi    7
		lag      theIconBar
		send     6
		eq?     
		bnt      code_1519
		pushi    #advanceCurIcon
		pushi    0
		lag      theIconBar
		send     4
code_1519:
		pushi    0
		callk    HaveMouse,  0
		not     
		bnt      code_1542
		pushi    #setCursor
		pushi    4
		pushi    #cursor
		pushi    0
		pushi    #curIcon
		pushi    0
		lag      theIconBar
		send     4
		send     4
		push    
		pushi    1
		lsg      oldMouseX
		lsg      oldMouseY
		lag      theGame
		send     12
		jmp      code_1559
code_1542:
		pushi    #setCursor
		pushi    2
		pushi    #cursor
		pushi    0
		pushi    #curIcon
		pushi    0
		lag      theIconBar
		send     4
		send     4
		push    
		pushi    1
		lag      theGame
		send     8
code_1559:
		ret     
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

(class HQEgo of Ego
	(properties
		view 0
		name "ego"
	)
	
	(method (doVerb theVerb)
		(if
			(OneOf theVerb
				V_FLAME V_RATIONS V_DAGGER V_LOCKPICK V_ROCK V_HEALING V_MANA V_VIGOR
				V_DISENCHANT V_GHOSTOIL V_FRUIT V_VEGETABLES V_ACORN V_FAIRYDUST V_WATER V_MUSHROOM
			)
			((ScriptID EGOSEZ) doit: theVerb)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (get what howMany &tmp obj num oldNum newWeight [str 60])
		(= obj ((ScriptID GLORYINV 0) at: what))
		(= num (if (== argc 1) 1 else howMany))
		(= oldNum (obj amount?))
		(cond 
			(
				(>
					(= newWeight
						(+
							(WtCarried)
							(/ (+ 59 (* num (obj weight?))) 60)
						)
					)
					(MaxLoad)
				)
				(if (not (Btst fOverloaded))
					(Message MsgGet SYSTEM N_CUE NULL NULL 1 @str)
					(Print addText: @str init:)
					(Bset fOverloaded)
				)
			)
			((Btst fOverloaded)
				(Bclr fOverloaded)
				(Message MsgGet SYSTEM N_CUE NULL C_NO_LONGER_OVERLOADED 1 @str)
				(Print addText: @str init:)
			)
		)
		(if (< (= num (+ num oldNum)) 0)
			(= num 0)
			(if (== (theIconBar curInvIcon?) obj)
				(theIconBar disable: (theIconBar useIconItem?))
			)
		)
		(if (and (<= num 0) (== (theIconBar curInvIcon?) obj))
			(theIconBar disable: (theIconBar useIconItem?))
		)
		(obj amount: num)
		(return (- num oldNum))
	)
	
	(method (has what)
		((inventory at: what) amount?)
	)
	
	(method (use what howMany &tmp num oldNum obj)
		(= obj (inventory at: what))
		(if
			(>
				(= num (if (== argc 1) 1 else howMany))
				(obj amount?)
			)
			(= num (obj amount?))
		)
		(self get: what (- num))
		(if
			(and
				(== what iMushroom)
				(not ((inventory at: iMushroom) amount?))
			)
			(Bclr fHaveToadstools)
		)
		(if
			(and
				(not (obj amount?))
				(== (theIconBar curInvIcon?) obj)
			)
			(theIconBar curInvIcon: 0)
			(if
				(and
					(not (user canControl:))
					(== oldIcon (theIconBar at: ICON_USEIT))
				)
				(= oldIcon (theIconBar at: ICON_WALK))
				(theIconBar curIcon: ICON_WALK)
			else
				(ChangeTheCursor ICON_WALK)
			)
		)
		(return num)
	)
	
	(method (knows what)
		(return [egoStats what])
	)
	
	(method (learn what howWell &tmp num)
		(= num (if (== argc 1) 5 else howWell))
		(if
		(and [egoStats MAGIC] (> num [egoStats what]))
			(= [egoStats what] num)
			(= spellMask
				(|
					spellMask
					(switch (- what OPEN)
						(0 SPELL_OPEN)
						(1 SPELL_DETECT)
						(2 SPELL_TRIGGER)
						(3 SPELL_DAZZLE)
						(4 SPELL_ZAP)
						(5 SPELL_CALM)
						(6 SPELL_FLAMEDART)
						(7 SPELL_FETCH)
					)
				)
			)
			(theIconBar enable: ICON_CAST)
		)
		(return [egoStats what])
	)
)

(instance stopGroop of GradualLooper
	(properties)
	
	(method (doit)
		(if
			(and
				(IsObject (ego cycler?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego view: ((ego cycler?) vWalking?))
		)
		(super doit: &rest)
	)
)

(instance statusCode of Code
	(properties)
	
	(method (doit roomNum &tmp [statusBuf 50] [scoreBuf 50])
		(if
			(not
				(OneOf roomNum
					LOGOROOM SPEED INTRO CHARSEL CHALLOC CHARSHEET NOTICE NOTICE2
					32 340 ARENA 171 172
				)
			)
			(if (or (== roomNum ENDGAME) (== roomNum ENDGAME2))
				(Message MsgGet SYSTEM N_STATUS_LINE_END NULL NULL 1 @statusBuf)
			else
				(Message MsgGet SYSTEM N_STATUS_LINE NULL NULL 1 @statusBuf)
			)
			(Format @scoreBuf @statusBuf score)
			(DrawStatus @scoreBuf 71 0)
		)
	)
)

(class qg1Timer of Timer
	(properties
		code 0
	)
	
	(method (cue)
		(if code
			(code doit:)
			(= code 0)
		)
	)
)

(class Actions of Code
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance KH of EventHandler)

(instance MH of EventHandler)

(instance DH of EventHandler)

(instance hSW of GloryWindow)

(instance qg1Walkers of EventHandler)

(instance qg1Messager of Messager
	
	(method (say)
		(Print mode: teJustCenter)
		(super say: &rest)
	)
	
	(method (sayNext)
		(Print mode: teJustCenter)
		(super sayNext: &rest)
	)
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					(NARRATOR narrator)
					(BORIS narrator)
					(BAKER narrator)
					(FAIRY1 (ScriptID 170 1))
					(FAIRY2 (ScriptID 170 2))
					(FAIRY3 (ScriptID 170 3))
					(FAIRY4 (ScriptID 170 4))
					(FAIRY5 (ScriptID 170 5))
					(ABDULLA (ScriptID 301 3))
					(BABA (ScriptID 21 1))
					(BARTENDER (ScriptID 342 0))
					(BARON (ScriptID 141 1))
					(BEGGAR (ScriptID 333 1))
					(BERNIE (ScriptID 141 2))
					(BERNIE171 (ScriptID 171 1))
					(BRAUGGI (ScriptID 58 1))
					(BRUTUS (ScriptID 73 1))
					(BRUNO (ScriptID 73 2))
					(BRUNO65 (ScriptID 65 1))
					(BUTCHER narrator)
					(CHIEFTHIEF (ScriptID 332 1))
					(CRUSHER narrator)
					(22 narrator)
					(DRYAD (ScriptID 76 1))
					(ELSA97 (ScriptID 97 1))
					(ERASMUS (ScriptID 31 1))
					(FENRUS (ScriptID 31 2))
					(FOX (ScriptID 67 1))
					(GARGOYLE (ScriptID 29 1))
					(HEALER (ScriptID 55 1))
					(HEINRICH (ScriptID 53 1))
					(HERMIT (ScriptID 83 1))
					(HILDE (ScriptID 320 1))
					(KARL (ScriptID 37 1))
					(MASTER (ScriptID 39 4))
					(MEEP (ScriptID 160 0))
					(STOOGE (ScriptID 95 1))
					(SHAMEEN (ScriptID 301 1))
					(SHEMA (ScriptID 301 2))
					(SHERIFF (ScriptID 300 1))
					(SHOPKEEPER (ScriptID 322 1))
					(SKULL (ScriptID 22 1))
					(SLINK (ScriptID 334 1))
					(STABLEMAN narrator)
					(WOLFGANG (ScriptID 311 1))
					(YORICK (ScriptID 96 4))
					(YORICK97 (ScriptID 97 2))
					(ZARA (ScriptID 314 1))
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(instance Glory of Game
	(properties)
	
	(method (init &tmp egoSW versionFile)
		StopWalk
		PolyPath
		Polygon
		(ScriptID GLORY_INIT)
		(ScriptID SIGHT)
		(= systemWindow hSW)
		(= ego HQEgo)
		(= egoSW StopWalk)
		(super init:)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileClose versionFile)
		(= ftrInitializer hq1FtrInit)
		(= doVerbCode hq1DoVerbCode)
		(= approachCode qg1ApproachCode)
		(= messager qg1Messager)
		(= gameControls GameControls)
		((= keyDownHandler KH) add:)
		((= mouseDownHandler MH) add:)
		((= directionHandler DH) add:)
		((= walkHandler qg1Walkers) add:)
		(Format @sysLogPath 0 0)
		((ScriptID GLORYINV 0) init:)
		(HaveMem 999)
		(= pMouse PseudoMouse)
		(= numVoices (DoSound NumVoices))
		((= cSound longSong)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= spareSound longSong2)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= theIconBar mainIconBar)
			init:
			disable: ICON_USEIT iconLeft iconRight
			curIcon: iconLook
		)
		(gameControls
			window: gcWin
			add:
				(detailSlider
					theObj: self
					selector: #detailLevel
					yStep: 3
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: #masterVolume
					yStep: 3
					yourself:
				)
				(speedSlider
					theObj: self
					selector: #setSpeed
					yStep: 3
					yourself:
				)
				(iconSave
					theObj: self
					selector: #save
					yourself:
				)
				(iconRestore
					theObj: self
					selector: #restore
					yourself:
				)
				(iconRestart
					theObj: self
					selector: #restart
					yourself:
				)
				(iconQuit
					theObj: self
					selector: #quitGame
					yourself:
				)
				(iconAbout
					theObj: (ScriptID ABOUT 0)
					selector: #doit
					yourself:
				)
				iconGCHelp
				iconOk
			eachElementDo: #highlightColor -1
			eachElementDo: #lowlightColor -1
			helpIconItem: iconGCHelp
			curIcon: iconSave
			state: NOCLICKHELP
		)
		(GameStartRoom)
	)
	
	(method (doit &tmp thisTime)
		(super doit:)
		(if
			(and
				(Btst fInMainGame)
				(!= oldSysTime (= thisTime (GetTime SYSTIME1)))
			)
			(= oldSysTime thisTime)
			(++ Clock)
			(if (and (>= Day 7) (& Clock 1))
				(++ Clock)
			)
			(if (>= Clock GAMEDAY)
				(= Clock 0)
				(NextDay)
			)
			(if (== egoGait MOVE_SNEAK)
				(SkillUsed STEALTH 1)
			)
			(if
				(and
					(Btst fBabaCurse)
					(> Day dayCursedByBabaYaga)
					(not fastCast)
					(> Clock 750)
				)
				(HandsOff)
				(Bclr fBabaCurse)
				(messager say: N_CUE NULL C_BABA_CURSE 0 self SYSTEM)
			)
			(if brunoTimer
				(-- brunoTimer)
			)
			(if (not (mod Clock GAMEHOUR))
				(switch Clock
					(300
						(= timeODay TIME_NOTYETDAWN)
					)
					(750
						(= Night FALSE)
						(= timeODay TIME_DAWN)
						(Bclr fStableClean)
						(PalVary PALVARYREVERSE 150)
					)
					(1050
						(EatMeal)
					)
					(1200
						(= timeODay TIME_MIDMORNING)
					)
					(1650
						(= timeODay TIME_MIDDAY)
					)
					(2100
						(= timeODay TIME_MIDAFTERNOON)
					)
					(2400
						(EatMeal)
					)
					(2550
						(= timeODay TIME_SUNSET)
					)
					(3000
						(= Night TRUE)
						(PalVary PALVARYSTART (curRoom picture?) 150)
						(if nightPalette
							(PalVary PALVARYTARGET nightPalette)
						)
						(= timeODay TIME_NIGHT)
					)
					(3450
						(Bset fFatigued)
						(= timeODay TIME_MIDNIGHT)
					)
				)
			)
			(if (and (Btst fFatigued) (not fastCast))
				(Bclr fFatigued)
				(if (== (++ lostSleep) 1)
					(messager say: N_PROCS NULL NULL 8 0 PROCS)
				else
					(messager say: N_PROCS NULL NULL 9 0 PROCS)
				)
			)
			(if (Btst fGhostOil)
				(switch (-- ghostOilTimer)
					(24
						(if fastCast
							(= ghostOilTimer 30)
						else
							(messager say: N_CUE NULL NULL 2 0 SYSTEM)
						)
					)
					(0
						(if fastCast
							(= ghostOilTimer 5)
						else
							(Bclr fGhostOil)
							(messager say: N_CUE NULL NULL 3 0 SYSTEM)
						)
					)
				)
			)
			(if (not (-- stamCounter))
				(if fastCast
					(= stamCounter 5)
				else
					(= stamCounter 20)
					(cond 
						((or (Btst fStarving) (> lostSleep 1))
							(UseStamina 5)
						)
						((== egoGait MOVE_RUN)
							(UseStamina 2)
						)
						((and (not (Btst fHungry)) (not lostSleep))
							(UseStamina -1)
						)
					)
				)
				(if (not (-- manaCounter))
					(= manaCounter 5)
					(UseMana -1)
				)
				(if (not (-- healCounter))
					(= healCounter 15)
					(TakeDamage -1)
				)
			)
		)
	)
	
	(method (replay)
		(InitGlobals)
		(if (== curRoomNum 32)
			(Bclr fHideCursor)
			(theGame setCursor: 942 TRUE)
			(Bset fHideCursor)
		)
		(statusCode doit: curRoomNum)
		(super replay:)
	)
	
	(method (newRoom n)
		(HandsOn)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp [scriptNum 10] [debugNum 10])
		(SaveTheCursor)
		(theGame setCursor: waitCursor TRUE)
		(StartARoom roomNum)
		(Message MsgGet SYSTEM N_CUE NULL C_SCRIPT_NUM 1 @scriptNum)
		(Format @debugNum @scriptNum DEBUG)
		(if (FileIO fileExists @debugNum)
			(if
				(and
					(u> (MemoryInfo FreeHeap) (+ 10 (MemoryInfo LargestPtr)))
					(Print
						addText: N_CUE NULL NULL 9 0 0 SYSTEM
						addButton: 0 N_CUE NULL NULL 10 0 20 SYSTEM
						addButton: 1 N_CUE NULL NULL 11 30 40 SYSTEM
						init:
					)
				)
				(SetDebug)
			)
			((ScriptID DEBUG) init:)
			(if
				(not
					(OneOf roomNum
						SPEED INTRO CHARSEL CHALLOC CHARSHEET NOTICE NOTICE2
						32 340
						ARENA
						171 172
						ENDGAME ENDGAME2 CHARSAVE
					)
				)
				(Bset fInMainGame)
			)
		)
		(if
			(OneOf roomNum
				11 12 17 18 19 23 24 25 26 27 33
				34 35 36 42 43 44 51 52 56 57 61
				62 63 69 71 72 74 75 79 80 81 85
				86 92
			)
			(ScriptID ENCOUNTER)
		)
		StopWalk
		Cycle
		(super startRoom: roomNum)
		(if (not isHandsOff)
			(RestoreTheCursor)
			(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		)
		(statusCode doit: roomNum)
	)
	
	(method (restore)
		(super restore:)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (save)
		(super save:)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (handleEvent event &tmp [temp0 2] [scriptNum 10] [debugNum 10])
		(if (== (event type?) mouseUp)
			(mouseDownHandler handleEvent: event)
		)
		(if (event claimed?) (return TRUE))
		(switch (event type?)
			(keyDown
				(switch (event message?)
					(TAB
						(if
							(not
								(if (or isHandsOff (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
								else
									(& (theIconBar state?) DISABLED)
								)
							)
							((ScriptID GLORYINV 1) init:)
						)
					)
					(SHIFTTAB
						(if
							(not
								(if (or isHandsOff (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
								else
									(& (theIconBar state?) DISABLED)
								)
							)
							((ScriptID GLORYINV 1) init:)
						)
					)
					(`^q
						(theGame quitGame:)
						(event claimed: TRUE)
					)
					(`^s
						(if (not (& (theIconBar state?) DISABLED))
							(cond 
								((not (HaveMem 2000))
									(messager say: N_CUE NULL NULL 4 0 SYSTEM)
								)
								((not isHandsOff)
									(theGame setCursor: theCursor FALSE)
									(Bset fHideCursor)
									((ScriptID CHARSHEET) doit:)
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
							(not
								(if (& (theIconBar state?) DISABLED)
								else
									(& ((theIconBar at: ICON_CONTROL) signal?) DISABLED)
								)
							)
							(if fastCast
								(return fastCast)
							)
							(theGame save:)
							(event claimed: TRUE)
						)
					)
					(`#7
						(if
							(not
								(if (& (theIconBar state?) DISABLED)
								else
									(& ((theIconBar at: ICON_CONTROL) signal?) DISABLED)
								)
							)
							(if fastCast
								(return fastCast)
							)
							(theGame restore:)
							(event claimed: TRUE)
						)
					)
					(`#9
						(theGame restart:)
						(event claimed: TRUE)
					)
					(`+
						(if (User controls?)
							(theGame setSpeed: (Max 1 (- speed 1)))
						)
					)
					(`-
						(if (User controls?)
							(theGame setSpeed: (+ speed 1))
						)
					)
					(`=
						(if (User controls?)
							(theGame setSpeed: 6)
						)
					)
					;logger code commented out, now in DEBUG
;;;					(`@n
;;;						((ScriptID LOGGER) doit: @sysLogPath 0)
;;;						(return
;;;							(event claimed: TRUE)
;;;						)
;;;					)
					(else 
						(Message MsgGet SYSTEM N_CUE NULL C_SCRIPT_NUM 1 @scriptNum)
						(Format @debugNum @scriptNum DEBUG)
						(if (FileIO fileExists @debugNum)
							((ScriptID DEBUG) handleEvent: event)
						)
					)
				)
			)
			(mouseDown
				(Message MsgGet SYSTEM N_CUE NULL C_SCRIPT_NUM 1 @scriptNum)
				(Format @debugNum @scriptNum DEBUG)
				(if (FileIO fileExists @debugNum)
					((ScriptID DEBUG) handleEvent: event)
				)
			)
		)
		(return
			(if (not (event claimed?))
				(super handleEvent: event)
			else
				FALSE
			)
		)
	)
	
	(method (setSpeed newSpeed)
		(if argc
			(= speed newSpeed)
			(if (User controls?)
				(ego cycleSpeed: newSpeed moveSpeed: newSpeed)
			)
		)
		(ego moveSpeed?)
	)
	
	(method (setCursor cursorObj showIt theX theY &tmp oldCurObj)
		(return
			(if (not (Btst fHideCursor))
				(= oldCurObj theCursor)
				(if argc
					(if (IsObject cursorObj)
						((= theCursor cursorObj) init:)
					else
						(SetCursor (= theCursor cursorObj) 0 0)
					)
				)
				(if (and (> argc 1) (not showIt))
					(SetCursor INVIS_CURSOR 0 0)
				)
				(if (> argc 2)
					(SetCursor theX theY)
				)
				(return oldCurObj)
			else
				0
			)
		)
	)
	
	(method (cue)
		(EgoDead C_DIE_BABA_CURSE C_DIE_BABA_CURSE_TITLE)
	)
	
	(method (quitGame)
		(super quitGame:
			(Print
				font: userFont
				mode: teJustCenter
				width: 180
				addText: N_CUE NULL C_QUITTING 1 0 0 SYSTEM
				addButton: 1 N_CUE NULL C_QUITTING 3 75 15 SYSTEM
				addButton: 0 N_CUE NULL C_QUITTING 4 60 35 SYSTEM
				init:
			)
		)
	)
	
	(method (pragmaFail)
		((ScriptID PRAGFAIL) doit:)
	)
)

(instance checkIcon of Code
	(properties)
	
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

(instance gcWin of GloryWindow
	(properties)
	
	(method (open &tmp t l b r theColor theMaps thePri [str 15] [scoreBuf 15])
		(= thePri -1)
		(self
			top: (/ (- 200 (+ (CelHigh 995 1 1) 6)) 2)
			left: (/ (- 320 (+ 149 (CelWide 995 0 1))) 2)
			bottom:
				(+
					(CelHigh 995 1 1)
					6
					(/ (- 200 (+ (CelHigh 995 1 1) 6)) 2)
				)
			right:
				(+
					149
					(CelWide 995 0 1)
					(/ (- 320 (+ 149 (CelWide 995 0 1))) 2)
				)
			priority: thePri
		)
		(super open:)
		(DrawCel 995 1 0 95 28 thePri)
		(DrawCel 995 1 0 131 28 thePri)
		(DrawCel 995 1 0 167 28 thePri)
		(DrawCel 995 0 4 64 (- 24 (+ (CelHigh 995 0 4) 3)) thePri)
		(DrawCel 995 0 3 100 (- 24 (+ (CelHigh 995 0 4) 3)) thePri)
		(DrawCel 995 0 2 136 (- 24 (+ (CelHigh 995 0 4) 3)) thePri)
		(= r (+ (= t (+ 34 (CelHigh 995 0 1))) 10))
		(= b
			(+
				(= l (+ 4 (CelWide 995 1 1)))
				(-
					(+ 149 (CelWide 995 0 1))
					(+ 4 (CelWide 995 1 1) 10)
				)
			)
		)
		(= theColor 0)
		(= theMaps 1)
		(Graph GFillRect t l (+ r 1) (+ b 1) theMaps theColor thePri)
		(Graph GShowBits t l (+ r 1) (+ b 1) 1)
		(Message MsgGet SYSTEM N_SCORE NULL NULL 1 @str)
		(Format @scoreBuf @str score possibleScore)
		(DrawCel 995 0 5 (+ 4 (CelWide 995 1 1) 8) (+ 34 (CelHigh 995 0 1) 2) thePri)
		(Display @scoreBuf
			p_font 999
			p_color 52
			p_at (+ (CelWide 995 0 5) 4 (CelWide 995 1 1) 13) (+ 34 (CelHigh 995 0 1) 2 1)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 67
		nsTop 24
		signal FIXED_POSN
		noun N_DETAIL
		modNum SYSTEM
		helpVerb V_HELP
		sliderView 995
		bottomValue 1
		topValue 5
	)
	
	(method (doit newDetail)
		(if argc
			(theGame detailLevel: newDetail)
		)
		(theGame detailLevel:)
	)
)

(instance volumeSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 103
		nsTop 24
		signal FIXED_POSN
		noun N_VOLUME
		modNum SYSTEM
		helpVerb V_HELP
		sliderView 995
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 139
		nsTop 24
		signal FIXED_POSN
		noun N_SPEED
		modNum SYSTEM
		helpVerb V_HELP
		sliderView 995
		bottomValue 15
		topValue 1
	)
	
	(method (show)
		(if (not (User controls?))
			(= signal (| FIXED_POSN DISABLED))
			(= sliderLoop 10)
		else
			(= sliderLoop 0)
			(= signal FIXED_POSN)
		)
		(super show: &rest)
	)
	
	(method (mask)
	)
	
	(method (move)
		(if (User controls?)
			(super move: &rest)
		)
	)
)

(instance iconSave of ControlIcon
	(properties
		view 995
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_SAVE
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 995
		loop 3
		cel 0
		nsLeft 8
		nsTop 23
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTORE
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 995
		loop 4
		cel 0
		nsLeft 8
		nsTop 40
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTART
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 995
		loop 5
		cel 0
		nsLeft 8
		nsTop 57
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_QUIT
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 995
		loop 6
		cel 0
		nsLeft 8
		nsTop 74
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_ABOUT
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconGCHelp of IconItem
	(properties
		view 995
		loop 7
		cel 0
		nsLeft 8
		nsTop 91
		cursor 949
		message V_HELP
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		noun N_CONTROL_HELP
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconOk of IconItem
	(properties
		view 995
		loop 8
		cel 0
		nsLeft 8
		nsTop 108
		cursor 949
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_OK
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance hq1DoVerbCode of Code
	(properties)
	
	(method (doit theVerb theObj &tmp oldCurIcon index [str 50] evt)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(return
			(if
				(or
					(and
						(User controls?)
						(or
							(== theVerb V_FLAME)
							(== theVerb V_SWORD)
							(== theVerb V_DAGGER)
							(theObj facingMe: ego)
						)
					)
					(not (User controls?))
				)
				(switch theVerb
					(V_WALK
						((User curEvent?) claimed: FALSE)
					)
					(V_LOOK
						(messager say: N_CUE NULL NULL 5 0 SYSTEM)
					)
					(V_TALK
						(messager say: N_CUE NULL NULL 6 0 SYSTEM)
					)
					(V_DO
						(messager say: N_CUE NULL NULL 7 0 SYSTEM)
					)
					(V_FLAME
						(CastFlame 0 0
							((= evt (Event new:)) x?)
							(+ (evt y?) 24)
						)
						(evt dispose:)
					)
					(else 
						(if
						(= oldCurIcon (theIconBar curInvIcon?))
							(switch (= index (inventory indexOf: oldCurIcon))
								(iRock
									(CastRock 0)
									(return TRUE)
								)
								(iDagger
									(CastDagger 0)
								)
								(else 
									(messager say: N_CUE NULL NULL 8 0 SYSTEM)
								)
							)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance hq1FtrInit of Code
	(properties)
	
	(method (doit theObj &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 40)
		)
		(if (== (theObj actions?) ftrDefault) (theObj actions: 0))
		(cond 
			((or (theObj x?) (theObj y?) (theObj z?)))
			(
			(not (IsObject (= temp0 (theObj onMeCheck?))))
				(theObj
					x: (/ (+ (theObj nsLeft?) (theObj nsRight?)) 2)
				)
				(theObj y: (theObj nsTop?))
			)
			(else
				(= temp8 (= temp9 0))
				(= temp6 (= temp7 32767))
				(= temp2 (temp0 points?))
				(= temp1 0)
				(= temp3 (* 4 (temp0 size?)))
				(while (< temp1 temp3)
					(= temp4 (Memory MReadWord (+ temp2 temp1)))
					(= temp5 (Memory MReadWord (+ temp2 temp1 2)))
					(if (<= temp4 temp6) (= temp6 temp4))
					(if (<= temp5 temp7) (= temp7 temp5))
					(if (>= temp4 temp8) (= temp8 temp4))
					(if (>= temp5 temp9) (= temp9 temp5))
					(= temp1 (+ temp1 4))
				)
				(theObj x: (/ (+ temp6 temp8) 2))
				(theObj y: temp7)
			)
		)
		(if
			(and
				(not (theObj approachX?))
				(not (theObj approachY?))
			)
			(theObj approachX: (theObj x?) approachY: (theObj y?))
		)
	)
)

(instance longSong of Sound)

(instance longSong2 of Sound)

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
		(iconHelp view: 990 loop: 9)
	)
	
	(method (handleEvent event)
		(if (> (ego z?) 900)
			(event claimed: FALSE)
		else
			(super handleEvent: event &rest)
		)
		(if (Btst fFlag361)
			((ScriptID GLORYINV 1) init:)
		)
	)
	
	(method (show)
		(RestoreTheCursor)
		(super show: &rest)
	)
	
	(method (swapCurIcon &tmp temp0)
		(cond 
			((& state DISABLED) (return))
			(
				(and
					(!= curIcon iconWalk)
					(not (& (iconWalk signal?) DISABLED))
				)
				(= prevIcon curIcon)
				(= curIcon iconWalk)
			)
			(
			(and prevIcon (not (& (prevIcon signal?) DISABLED))) (= curIcon prevIcon))
		)
		(theGame setCursor: (curIcon cursor?) TRUE)
	)
)

(instance iconLeft of IconItem
	(properties
		view 990
		loop 12
		cel 1
		cursor INVIS_CURSOR
		maskView 990
		maskLoop 12
		modNum SYSTEM
	)
	
	(method (show)
		(super show: -5 0)
	)
	
	(method (select)
		(return FALSE)
	)
)

(instance iconRight of IconItem
	(properties
		view 990
		loop 13
		cel 1
		cursor INVIS_CURSOR
		maskView 990
		maskLoop 13
		modNum SYSTEM
	)
	
	(method (select)
		(return FALSE)
	)
)

(instance iconWalk of IconItem
	(properties
		view 990
		loop 0
		cel 0
		cursor 940
		type (| userEvent walkEvent)
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 14
		noun N_WALK
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconLook of IconItem
	(properties
		view 990
		loop 1
		cel 0
		cursor 941
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 14
		noun N_LOOK
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconDo of IconItem
	(properties
		view 990
		loop 2
		cel 0
		cursor 942
		message V_DO
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 14
		noun N_DO
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconTalk of IconItem
	(properties
		view 990
		loop 3
		cel 0
		cursor 943
		message V_TALK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 14
		noun N_TALK
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconActions of IconItem
	(properties
		view 990
		loop 10
		cel 0
		cursor ARROW_CURSOR
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 14
		noun N_ACTION
		modNum SYSTEM
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(SaveTheCursor)
				(theIconBar hide:)
				((ScriptID ACTIONBAR) init: show:)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconCast of IconItem
	(properties
		view 990
		loop 11
		cel 0
		message 0
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 14
		noun N_CAST
		modNum SYSTEM
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(if
					(OneOf curRoomNum
						300 301 302 310 311 313 314 318 320
						321 322 330 331 332 333 334 340
					)
					(theIconBar hide:)
					(messager say: N_NO_CAST_IN_TOWN NULL NULL 0 0 SYSTEM)
					(return TRUE)
				else
					(if (!= ((theIconBar curIcon?) cursor?) 948)
						(SaveTheCursor)
					)
					(theIconBar hide:)
					((ScriptID SPELLS) init: showSelf:)
					(return TRUE)
				)
			else
				FALSE
			)
		)
	)
)

(instance iconUseIt of IconItem
	(properties
		view 990
		loop 4
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 14
		maskCel 1
		noun N_USEIT
		modNum SYSTEM
		helpVerb V_HELP
	)
	
	(method (select relVar &tmp event whichCel oldCurIcon temp3 temp4)
		(return
			(cond 
				((& signal DISABLED) 0)
				((and argc relVar (& signal RELVERIFY))
					(if
					(= oldCurIcon (theIconBar curInvIcon?))
						(= temp3
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide
											(oldCurIcon view?)
											(+ (oldCurIcon loop?) 1)
											(oldCurIcon cel?)
										)
									)
									2
								)
								nsLeft
							)
						)
						(= temp4
							(+
								(theIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh
											(oldCurIcon view?)
											(+ (oldCurIcon loop?) 1)
											(oldCurIcon cel?)
										)
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if
					(= oldCurIcon (theIconBar curInvIcon?))
						(DrawCel
							(oldCurIcon view?)
							(+ 1 (oldCurIcon loop?))
							(oldCurIcon cel?)
							temp3
							temp4
							-1
						)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					(while (!= ((= event (Event new:)) type?) 2)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if
									(= oldCurIcon (theIconBar curInvIcon?))
										(DrawCel
											(oldCurIcon view?)
											(+ 1 (oldCurIcon loop?))
											(oldCurIcon cel?)
											temp3
											temp4
											-1
										)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if
								(= oldCurIcon (theIconBar curInvIcon?))
									(DrawCel
										(oldCurIcon view?)
										(+ 1 (oldCurIcon loop?))
										(oldCurIcon cel?)
										temp3
										temp4
										-1
									)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if
						(= oldCurIcon (theIconBar curInvIcon?))
							(DrawCel
								(oldCurIcon view?)
								(+ 1 (oldCurIcon loop?))
								(oldCurIcon cel?)
								temp3
								temp4
								-1
							)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					)
					whichCel
				)
				(else 1)
			)
		)
	)
)

(instance iconInventory of IconItem
	(properties
		view 990
		loop 5
		cel 0
		cursor ARROW_CURSOR
		type $0000
		message 0
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 14
		noun N_INVENTORY
		modNum SYSTEM
		helpVerb V_HELP
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				((ScriptID GLORYINV 1) init:)
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance iconControlPanel of IconItem
	(properties
		view 990
		loop 7
		cel 0
		cursor ARROW_CURSOR
		message 9
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 14
		noun N_CONTROL
		modNum SYSTEM
		helpVerb V_HELP
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			(gameControls window: gcWin show:)
		)
	)
)

(instance iconHelp of IconItem
	(properties
		view 990
		loop 9
		cel 0
		cursor 949
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 14
		noun N_ICON_HELP
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance invCursor of Cursor
	(properties
		view 950
	)
)

(instance qg1ApproachCode of Code
	(properties)
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK 1)
			(V_TALK 2)
			(V_WALK 4)
			(V_DO 8)
			(V_MONEY 16)
			(else  -32768)
		)
	)
)
