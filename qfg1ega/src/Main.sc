;;; Sierra Script 1.0 - (do not remove this comment)
(script# MAIN) ;MAIN = 0
(include game.sh)
(use HQInit)
(use Drink)
(use Eat)
(use Sleep)
(use Rest)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
(use CastCalm)
(use CastOpen)
(use CastDazz)
(use Intrface)
(use StartARoom)
(use StopWalk)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use System)

(public
	Glory 0
	EgoDead 1
	RedrawCast 2
	HaveMem 3
	cls 4
	HandsOff 5
	HandsOn 6
	MouseClaimed 7
	SoundFX 8
	Bset 9
	Bclr 10
	Btst 11
	NextDay 12
	Dummy1 13
	NormalEgo 14
	NotClose 15
	AlreadyDone 16
	DontHave 17
	CantDo 18
	HighPrint 19
	CenterPrint 20
	TimePrint 21
	ShowTime 22
	CanPickLocks 23
	Face 24
	GiveMoney 25
	FixTime 26
	PromptQuit 27
	UseMana 28
	UseStamina 29
	TrySkill 30
	SkillUsed 31
	SolvePuzzle 32
	;procedure 33 does not exist
	;procedure 34 does not exist
	Rand100 35
	TakeDamage 36
	MaxMana 37
	MaxStamina 38
	MaxHealth 39
	MaxLoad 40
	CastSpell 41
	ChangeGait 42
	EatMeal 43
)

(local
	;system-defined global variables (largely unchanged from game to game)
	ego									;0			;pointer to ego
	theGame								;1			;ID of the Game instance
	curRoom								;2			;ID of current room
	speed	=	6						;3			;number of ticks between animations
	quit								;4			;when TRUE, quit game
	cast								;5			;collection of actors
	regions								;6			;set of current regions
	timers								;7			;list of timers in the game
	sounds								;8			;set of sounds being played
	inventory							;9			;set of inventory items in game (not used in this game)
	addToPics							;10			;list of views added to the picture
	curRoomNum							;11			;current room number
	prevRoomNum							;12			;previous room number
	newRoomNum							;13			;number of room to change to
	debugOn								;14			;generic debug flag -- set from debug menu
	score								;15			;the player's current score
	possibleScore						;16			;highest possible score
	showStyle	=	IRISOUT				;17			;style of picture showing
	aniInterval							;18			;# of ticks it took to do the last animation cycle
	theCursor							;19			;the number of the current cursor
	normalCursor	=	ARROW_CURSOR	;20			;number of normal cursor form
	waitCursor	=	HAND_CURSOR			;21			;cursor number of "wait" cursor
	userFont	=	USERFONT			;22			;font to use for Print
	smallFont	=	4					;23 		;small font for save/restore, etc.
	lastEvent							;24			;the last event (used by save/restore game)
	modelessDialog						;25			;the modeless Dialog known to User and Intrface
	bigFont	=	USERFONT				;26		 	;large font
	volume	=	12						;27			;sound volume
	version	= {x.yyy.zzz}				;28			;pointer to 'incver' version string
	locales								;29			;set of current locales
	curSaveDir							;30			;address of current save drive/directory string
	;globals 31-49 are part of the curSaveDir array
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
	aniThreshold	=	10			;50
	perspective						;51				;player's viewing angle:
														;	 degrees away from vertical along y axis
	features						;52				;locations that may respond to events
	sortedFeatures						;53				;above+cast sorted by "visibility" to ego
	useSortedFeatures	=	FALSE		;54				;enable cast & feature sorting?
	demoScripts							;55				;add to curRoomNum to find room demo script
	egoBlindSpot	=	0				;56				;used by sortCopy to exclude 
														;actors behind ego within angle 
														;from straight behind. 
														;Default zero is no blind spot
	overlays	=	-1					;57
	doMotionCue							;58				;a motion cue has occurred - process it
	systemWindow						;59				; ID of standard system window
	demoDialogTime	=	3				;60				;how long Prints stay up in demo mode
	currentPalette						;61				;
	modelessPort						;62
	;globals 63-99 are unused
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
	
	;game-defined global variables
	egoGait				;0 = walk, 1 = run, 2 = sneak
	debugging	=	FALSE	;debug mode starts disabled
	global102				;unused
	global103				;unused
	isHandsOff				
	egoX
	egoY
	machineSpeed			;used to test how fast the system is
							;and used in determining detail level. (used in conjunction with howFast)
	cSound					;music object, current playing music?
	global109				;unused
	daySheriffBreakIn		;this is the game day that you broke into the Sheriff's house.
							;After this day, the door will be barred, and you can no longer break in.
	dayLOLBreakIn			;this is the game day that you broke into the Little Old Lady's house.
							;After this day, the door willbe barred, and you can no longer break in
	dayCursedByBabaYaga		;this is the game day that Baba Yaga placed her curse on you.  
							;If you don't bring her the mandrake root before the time is up, you will die.
	exploringTown			;This is only ever set to FALSE (and never read)
							;And even then, it's only referenced outside the sheriff and lol house, 
							;when leaving to another part of town..
	yesNoTimer				;a countdown used by several actors when they are waiting for a response
	lastRestTime		
	lastRestDay		
	monsterDistX
	monsterDistY
	global119				;unused
	global120				;unused
	global121				;unused
	global122				;unused
	global123				;unused
	global124				;unused
	global125				;unused
	global126				;unused
	global127				;unused
	global128				;unused
	global129				;unused
	Clock
	Night
	Day
	timeODay				;time of day in game (morning, noon, etc)
	barNote					;Note being passed at the bar
	oldSysTime
	heroType				;The Character Class. 0 = Fighter, 1 = Magic User or 2 = Thief
	howFast					;detail level (0 = slow, 1 = medium, 2 = fast, 3 = fastest)
	transferRoom			;room to be transferred to after the speed test
	egoStats				;hero's skills (25 variable array)
		gIntell
		gAgil
		gVit
		gLuck
		gWeap
		gParry
		gDodge
		global147
		global148
		global149
		global150
		gMagic
		global152
		gHealth
		gStamina
		gEgoMagicSkill
		global156
		global157
		global158
		gGGStrength
		global160
		global161
		global162
		global163			;end of egoStats
	skillTicks				;skillTicks (25 variable array)
		global165			;	determines when the skill is next increased 
		global166			;	When skills are used (either by TrySkill, SkillUsed, or StatCheck)
		global167			;	the skillTicks increase by an amount (either directly entered for SkillUsed and StatCheck, or inciredtly for TrySkill)
		global168			;	When skillTicks goes above egoStats, then skillTicks resets, and egoStat increases by a random amount betweeen 1 and 3
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
	lastSkillTick			;end of skillTicks
	lockPickBonus	;lock picking bonus (LockPick gives you 10 bonus, Tool Kit gives you 35 bonus)
	;these 10 are part of an array. The last two are unused.
	spellCost 	  		=	2 	;SpellMPUsage (Open)
		spCostDetect	=	2 	;SpellMPUsage (Detect Magic)
		spCostTrigger	=	3 	;SpellMPUsage (Trigger)
		spCostDazzle	=	3 	;SpellMPUsage (Dazzle)
		spCostZap		=	3 	;SpellMPUsage (Zap)
		spCostCalm		=	4 	;SpellMPUsage (Calm)
		spCostFlame		=	5 	;SpellMPUsage (Flame Dart)
		spCostFetch		=	5 	;SpellMPUsage (Fetch)
		global198				;unused
		global199				;unused
	spellCostEnd				;end of spell table
	fastEgo						;double's ego's walking speed (option removed in 1.200)
	magesMazeButtonIndex
	magesMazeCommand
	global204				;unused
	global205				;unused
	global206				;unused
	global207				;unused
	global208				;unused
	global209				;unused
	global210				;unused
	numColors
	numVoices
	stamCounter =  STAM_RATE
	healCounter =  HEAL_RATE
	global215				;unused
	keyDownHandler			;-our EventHandlers, get called by game
	mouseDownHandler		;-our EventHandlers, get called by game
	directionHandler	;-our EventHandlers, get called by game
	freeMeals			;As the game progresses through the day, this gets reduced first. If this is 0, then you eat a ration.
	ghostOilTimer		;number of game minutes left until the undead unguent wears off
	global221			;unused
	oldStats			;(25 variable array) - These are the value of skills as last seen on the character sheet. Changed skills will be shown in red.
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
	endStats			;end of oldStats
	global247				;unused
	global248				;unused
	global249				;unused
	zapPower			;the extra damage incurred by a zap-charged weapon
	monsterDazzle			;number of animation cycles until the monster attacks again, in the arena
		
	;array of 9 variables, corresponding to direction event messages
	targetAngles 	=  [180 0 45 90 135 180 225 270 315]
	numFlowers				;times sold flowers to the healer
	numMushrooms			;times sold mushrooms to the healer
	numWater				;has the hero given flying water to the healer?
	brigandHead				;which view to use for the current brigand in combat.
	numBrigands				;how many brigands is the hero fighting at once?
	egoCanFight				;during an arena battle, can the hero fight, or is he in the middle of something?
	masterDay =  -1			;when did the hero last fight the weapon master
	lostSleep				;how many days has the hero gone without sleeping?
	totalDagNabItBet		;CI: ?? just a guess, could be something else, but is related to DagNabIt somehow.
	thievesPassword
	missedDaggers			;EO: Daggers thrown on ground
	hitDaggers				;EO: Daggers thrown in enemy
	daggerRoom		;the last room the hero dropped daggers into. He can pick up all his daggers in this room.
	sameColor 		=  vLBLUE	;The colour of stats on the Character Screen
	changeColor 	=  vLRED	;The colour of stats that have changed since last viewing, on the Character Screen
	shieldRoom		;the room hero dropped his shield in. He can pick it up later.
	mandrakeDay =  -3		;the day hero last pulled the Mandrake Root
	dftStatusCode			;default Status Bar refresh code. i.e. Quest for Glory I [%d of 500]
	;wizard Erasmus variables
	wizGameSpellTime		;time left for Fetch and Flame Dart in the wizard game.
	wizAskedSpells			;What spells has Erasmus asked that you already know?
	wizAskedSpells2			;(second part of the above. What spells?)
	
	koboldIllBits =  cWHITE	;Kobold cave-related
	dayKoboldAwakened =  -1	;The day the Kobold was last awakened
	bucks					;Number of silvers on the monster in the current room
	theKobold				;global variable holding the kobold TargetActor
	koboldCycles			;not actually used for anything.
	ogreDay					;the day the Ogre was last fought
	ogreTime				;the time zone the Ogre was last fought
	fightingKoboldStart		;the fight with the kobold has begun!
	fightingKobold			;True if in battle with Kobold?
	global291				;unused
	global292				;unused
	global293				;unused
	global294				;unused
	global295				;unused
	global296				;unused
	global297				;unused
	global298				;unused
	global299				;unused
	dartsBonus				;used for DagNabIt
	global301				;unused
	statusBarView			;the View to use to display battle status
	combatColor				;Hit Points Font Colour, during Arena Battles.
	global304				;unused
	hadABattle				;used in Arena (set but never referenced)
	global306				;unused
	global307				;unused
	global308				;unused
	global309				;unused
	erasmusTalking			;musn't interrupt the wizard when he's talking.
	fenrusTalking			;nor his familiar.
	enableErasmusTeaCountdown ;there is a silent countdown to when the wizard takes another sip of tea. When enabled, the countdown continues.
	erasmusTellingJoke		;is Erasmus in the act of telling a joke?
	saidYesToErasmus		;said yes to whatever question the wizard asked.
	koboldStatus			;what is the kobold doing? 0 = asleep, ... 4 = dead.
	global316				;EO: unused in 1.200
	global317				;EO: unused in 1.200
	global318				;EO: unused in 1.200
	global319				;EO: unused in 1.200
	ghostCount				;ghosts and graveyard related.
	global321				;EO: unused in 1.200
	global322				;EO: unused in 1.200
	hutState				;0, 1, 2, 3, 4 as possible values.
	babaState				;0, 1, 2, 3, 4 as possible values.
	deathMusic =  26
	spireaStatus			;spitting seed related
	seedTarget				;which plant is the seed *going* to?
	seedInPlant				;which plant has the seed? (0, 1, 2, 3)
	numApples				;The number of apples given to Frost Giant
	nestState				;The state of the next outside the Healer's hut (0 = in tree, 1 = on ground, 2 = burnt)
	numGoblins				;The number of dead goblins in the Goblin Ambush
	monsterNum				;EO: Monster in the current room
	monsterHealth			;HP of the current fighting monster
	brunoTimer			;this counts down the game seconds after Bruno has left the target range.  
							;If you don't wait long enough, you'll encounter him!
	oldScore			;The game score last viewed on the Character Sheet
	;Bar variables
	drinkOrdered 			;(0 = nothing, 1 = ale, 2 = Troll's sweat, 3 = Dragon's Breath)
	drinkInHand				;(0 = nothing, 1 = ale, 2 = Troll's sweat, 3 = Dragon's Breath)
	numberOfAlesDrunk
	global339				;unused
	;Inn Variables
	foodOrdered				;ordered food... 0 = nothing, 1 = ordered, 2 = at table, 3 = finished
	teaOrdered				;ordered drink... 0 = nothing, 1 = ordered, 2 = at table, 3 = finished
	serveFoodCountdown		;this counts down the cycles after ordering food until it is served.
	;kobold variables
	koboldHealth
	koboldEvade				;the threshold to which the kobold avoids getting hit (lower values means less chance of getting hit)
	damageToKobold			;how much damage will the kobold take if he gets hit?
	damageToKoboldFlame		;how much damage from a flame dart will the kobold take?
	global347				;unused
	egoKoboldBattleLoop		;EO: used for 1.200
	global349				;unused
	gameFlags	;(50 variable arroy) - all event flags in the game
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
	endGameFlags
	userName	;(40 variable array) - name of the user, as supplied by the user when starting a new game.
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
	endUserName
	invNum		;inventory quantities (start of 50 variable array; 42 are used)
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
		global482			;end of used array
		global483			;unused
		global484			;unused
		global485			;unused
		global486			;unused
		global487			;unused
		global488			;unused
		global489			;unused
		global490			;end of Inventory array
	invDropped			;dropped inventory (start of 50 variable array; 42 are used)
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
		global533			;end of used array
		global534			;unused
		global535			;unused
		global536			;unused
		global537			;unused
		global538			;unused
		global539			;unused
		global540			;unused
	invWeight	;inventory weight in quarks (start of 41 variable array)
		wisilver		=	1
		wigold			= 	1
		wifood			=	20
		wimandrake		=	30
		wikey			=	15
		wisword			=	420
		widagger		=	120
		wileather		=	1200
		wishield		=	720
		wipaper			=	1
		wifruit			=	15
		wivegetables	=	30
		wigem			=	6
		wivase			=	30
		wicandelabra	=	180
		wimusicbox		=	45
		wicandlesticks	=	60
		wipearls		=	30
		wiring			=	10
		wiseed			=	60
		wirock			=	30
		wiflowers		=	1
		wipick			=	5
		wikit			=	30
		wilicense		=	2
		wiflask			=	10
		wifur			=	3
		widust			=	10
		wiwater			=	40
		wimushroom		=	10
		wiclaw			=	20
		wibeard			=	60
		wichainmail		=	2100
		wiheal			=	40
		wimana			=	40
		wivigor			=	40
		wiheroism		=	40	;unused
		widispel		=	40
		wighostoil		=	40
		wimirror		=	30
		wiacorn			=	3
	ogreX =  160
	ogreY =  120
	ogreHealth =  93				;ogre's current HP (CI: looks like his max used to 93, but was upped to 112 later on.)
	ogreDeathDay =  1000			;day defeated Ogre
	global587						;unused
	global588						;unused
	brutusHealth					;HP for Brutus in target range
	dayLastPlayedCribbage =  1000	;day last played Cribbage with Henry
	manaCounter =  5		;MP Countdown
	spareSound						;music playing in the arenas during a battle
	magesMazePlayCount				;The number of times the hero has played Mage's Maze
	global594						;unused
)

;;;(procedure (EgoDead param1)
;;;	(asm
;;;		pushi    0
;;;		call     HandsOff,  0	;(HandsOff)
;;;		pushi    1
;;;		pushi    100
;;;		callk    Wait,  2		;(Wait 100)
;;;		pushi    #setCursor
;;;		pushi    2
;;;		lsg      normalCursor
;;;		pushi    TRUE
;;;		lag      theGame		;(theGame setCursor: normalCursor TRUE)
;;;		send     8
;;;		pushi    #eachElementDo
;;;		pushi    1
;;;		pushi    #stop
;;;		lag      sounds			;(sounds eachElementDo: #stop)
;;;		send     6
;;;		lag      deathMusic
;;;		bnt      code_0fc0
;;;		pushi    #number
;;;		pushi    1
;;;		push    
;;;		pushi    #priority
;;;		pushi    1
;;;		pushi    15
;;;		pushi    #init
;;;		pushi    0
;;;		pushi    #play
;;;		pushi    0
;;;		lofsa    music			;(music number: deathMusic priority: 15 init: play:)
;;;		send     20
;;;code_0fc0:
;;;		pushi    11
;;;		&rest    param1
;;;		pushi    #width
;;;		pushi    250
;;;		pushi    #button
;;;		lofsa    {Restore}
;;;		push    
;;;		pushi    1
;;;		pushi    #button
;;;		lofsa    { Restart_}
;;;		push    
;;;		pushi    2
;;;		pushi    #button
;;;		lofsa    { Quit_}
;;;		push    
;;;		pushi    3
;;;		calle    Print,  22		;(Print &rest #width 250 #button {Restore} 1 #button { Restart_} 2 #button { Quit_} 3)
;;;		push    
;;;		dup     
;;;		ldi      1
;;;		eq?     
;;;		bnt      code_0ff5
;;;		pushi    #restore
;;;		pushi    0
;;;		lag      theGame		;(theGame restore:)
;;;		send     4
;;;		jmp      code_1015
;;;code_0ff5:
;;;		dup     
;;;		ldi      2
;;;		eq?     
;;;		bnt      code_1007
;;;		pushi    #restart
;;;		pushi    0
;;;		lag      theGame		;(theGame restart:)
;;;		send     4
;;;		jmp      code_1015
;;;code_1007:
;;;		dup     
;;;		ldi      3
;;;		eq?     
;;;		bnt      code_1015
;;;		ldi      TRUE
;;;		sag      quit			;(= quit TRUE)
;;;		jmp      code_1019		;(break)
;;;EO: code_1015 and references to it had to be commented out. Then
;;; I compiled the script, redecompiled it (creating workable code)
;;; then touched it up.
;;;code_1015:
;;;		toss    
;;;		jmp      code_0fc0
;;;code_1019:
;;;		ret     
;;;	)
;;;)

(procedure (EgoDead)
	;stops all sounds, plays the death music, and gives the player a choice:
	;	Restore, Restart, Quit
	;EO: This procedure has been freshly decompiled, using a little trickery to
	; create workable code.
	(HandsOff)
	(Wait 100)
	(theGame setCursor: normalCursor TRUE)
	(sounds eachElementDo: #stop)
	(if deathMusic
		(music number: deathMusic priority: 15 init: play:)
	)
	(repeat
		(switch
			(Print &rest
				#width 250
				#button {Restore} 1
				#button { Restart_} 2
				#button { Quit_} 3
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

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo LargestPtr) howMuch))
)

(procedure (cls)
	; (clear screen) clears any modelessDialogs from the screan
	;
	
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (HandsOff)
	; Disable ego control
	;
	
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(theGame setCursor: waitCursor TRUE)
	(ego setMotion: 0)
)

(procedure (HandsOn)
	; Enable ego control
	;
	
	(= isHandsOff FALSE)
	(User canControl: TRUE canInput: TRUE)
	(theGame setCursor: normalCursor (HaveMouse))
)

(procedure (MouseClaimed obj event shifts)
	(return
		(if (MousedOn obj event shifts)
			(event claimed: TRUE)
			(return TRUE)
		else
			(return FALSE)
		)
	)
)

;songs are split into 4 channel (low-res) and greater than 4 channel (standard res).
;low-res songs have resource numbers in the 100-range,
;while standard res songs have resource numbers in the 000-range
(procedure (SoundFX soundNum)
	(return
		(if (> numVoices 4)
			(return soundNum)
		else
			(return (+ 100 soundNum))
		)
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

(procedure (NextDay)
	(++ Day)
	(Bclr fStableClean)
)

(procedure (Dummy1)
)

(procedure (NormalEgo)
	;Normalize ego animation
	(ChangeGait -1 FALSE)
	(ego
		setPri: -1
		setMotion: 0
		illegalBits: cWHITE
		ignoreHorizon:
		ignoreActors: FALSE
	)
)

(procedure (NotClose)
	(HighPrint 0 51)
	;You're not close enough.
)

(procedure (AlreadyDone)
	(HighPrint 0 52)
	;You've already done that.
)

(procedure (DontHave)
	(HighPrint 0 53)
	;You don't have it.
)

(procedure (CantDo)
	(HighPrint 0 49)
	;You can't do that now.
)

(procedure (HighPrint &tmp [printRect 4] [str 400])
	; Prints a message at the top of the screen (y: 12px)
	;
	
	(cls)
	(Format @str &rest)
	(TextSize @[printRect 0] @str userFont 0)
	(Print @str
		#at	-1 12
		#width (if (> [printRect 2] 24) 300 else 0)
		#mode teJustCenter
	)
)

(procedure (CenterPrint &tmp [printRect 4] [str 400])
	; Prints a message in the middle of the screen (y: 115px)
	;
	
	(Format @str &rest)
	(TextSize @[printRect 0] @str userFont 0)
	(Print @str
		#at -1 115
		#width (if (> [printRect 2] 24) 300 else 0)
		#mode teJustCenter
	)
)

(procedure (TimePrint seconds &tmp [printRect 4] [str 400])
	; Clears any existing messages and Prints a new message at the top of the screen 
	; that automatically dissapears after the specified number of seconds
	; 
	
	(cls)
	(Format @str &rest)
	(TextSize @[printRect 0] @str userFont 0)
	(Print @str
		#at -1 12
		#width (if (> [printRect 2] 24) 300 else 0)
		#mode teJustCenter
		#dispose
		#time seconds
	)
)

(procedure (ShowTime &tmp whatDay [str 30])
	;what time and day is it?
	(= whatDay Day)
	(if (or (!= timeODay TIME_MIDNIGHT) (> Clock 500))
		(++ whatDay)
	)
	(HighPrint
		(Format @str
			0 48 ;%s on day %d.
			297 (+ 40 timeODay) ;%s the name of the time of day, from text.297
			whatDay) ; %d the current day
	)
)

(procedure (CanPickLocks)
	;does ego have the tools and skill to pick locks?
	(if [egoStats PICK]
		(if (ego has: iLockPick)
			else (ego has: iThiefKit)
		)
	)
)

(procedure (Face actor1 actor2)
	(DirLoop actor1
		(GetAngle
			(actor1 x?)
			(actor1 y?)
			(actor2 x?)
			(actor2 y?)
		)
	)
	(if (== argc 3)
		(DirLoop actor2
			(GetAngle
				(actor2 x?)
				(actor2 y?)
				(actor1 x?)
				(actor1 y?)
			)
		)
	)
)

(procedure (GiveMoney itemPrice &tmp oldSilver oldGold)
	; if hero has enough money total (gold + silver), then 
	; hero's silver will be deducted first. If that's not enough to pay the cost, 
	; gold will be automatically converted to silver at 10:1 rate
	; Returns FALSE if hero doesn't have enough money, TRUE if the exchange was successful.
	;
	(= oldSilver [invNum iSilver])
	(= oldGold [invNum iGold])
	(if (< (+ oldSilver (* oldGold 10)) itemPrice)
		(return FALSE)
	)
	(-= oldSilver itemPrice)
	(while (< oldSilver 0)
		(-- oldGold)
		(+= oldSilver 10)
	)
	(= [invNum iSilver] oldSilver)
	(= [invNum iGold] oldGold)
	(return TRUE)
)


(procedure (FixTime newTime newMinutes &tmp oldTime)
	;set up the time of day
	(if (>= argc 1)
		(= Clock (* GAMEHOUR newTime))
		(= oldSysTime (GetTime SYSTIME1))
		(if (>= argc 2)
			(+= Clock (/ (* GAMEHOUR newMinutes) 60))
		)
	)
	(^= Clock 1)	;need an even number
	(= oldTime timeODay)
	(cond 
		((< Clock 300)
			(= timeODay TIME_MIDNIGHT)
		)
		((< Clock 750)
			(= timeODay TIME_NOTYETDAWN)
		)
		((< Clock 1200)
			(= timeODay TIME_DAWN)
		)
		((< Clock 1650)
			(= timeODay TIME_MIDMORNING)
		)
		((< Clock 2100)
			(= timeODay TIME_MIDDAY)
		)
		((< Clock 2550)
			(= timeODay TIME_MIDAFTERNOON)
		)
		((< Clock 3000)
			(= timeODay TIME_SUNSET)
		)
		((< Clock 3450)
			(= timeODay TIME_NIGHT)
		)
		(else
			(= timeODay TIME_MIDNIGHT)
		)
	)
	(if (> timeODay TIME_SUNSET)
		(= Night TRUE)
		(= currentPalette 1)
	else
		(= Night FALSE)
		(= currentPalette 0)
	)
	(if (and (== timeODay TIME_MIDNIGHT) (!= oldTime TIME_MIDNIGHT))
		(if (== (++ lostSleep) 1)
			(Print 0 54)
			; You are getting tired.
		else
			(Print 0 55)
			; You are exhausted from lack of sleep.
		)
	)
)

(procedure (PromptQuit)
	(= quit
		(Print 0 64
			#title {Giving up, huh?}
			#button {Quit} TRUE
			#button {Don't Quit} FALSE
			#icon vDeathScenes 1 4
		)
	)
	; How about a slice of quiche?
)

(procedure (UseMana pointsUsed)
	; Reduces hero's MP. No return value.
	; 
	
	(if [egoStats MAGIC]
		(if (< (-= [egoStats MANA] pointsUsed) 0)
			(= [egoStats MANA] 0)
		)
		(if (> [egoStats MANA] (MaxMana))
			(= [egoStats MANA] (MaxMana))
		)
		(if (> pointsUsed 0)
			;losing MP works out your Intelligence and your Magic (by the pointsUsed of MP / 5 and /2 respectively)
			(SkillUsed INT (/ pointsUsed 5))
			(SkillUsed MAGIC (/ pointsUsed 2))
		)
	)
)

(procedure (UseStamina pointsUsed &tmp foo)
	; Reduces hero's SP. No return value.
	;
	
	(if (> pointsUsed 0) 
		;losing SP works out your Vitality.
		(SkillUsed VIT (/ (+ pointsUsed 3) 4))
	)
	(cond 
		((< (= foo (-= [egoStats STAMINA] pointsUsed)) 0)
			;if you're out of SP, then we start reducing your HP.
			(TakeDamage (/ (- -3 [egoStats STAMINA]) 4))
			(= [egoStats STAMINA] 0)
			(if (not (Btst fWornOut))
				(Bset fWornOut)
				(HighPrint 0 56)
				;You are so exhausted that everything you do hurts.  Better get some rest.
			)
			(if (<= [egoStats HEALTH] 0)
				(EgoDead 0 57 
					#title {Death from Overwork}
					#icon vDeathScenes 1 4
				)
				;That was a little too much for you.  You collapse from exhaustion and die.
				
			)
		)
		;if we have > 4 SP, then remove the fWornOut flag.
		((> foo 4)
			(Bclr fWornOut)
			(if (> foo (MaxStamina))
				(= [egoStats STAMINA] (MaxStamina))
			)
		)
	)
)

(procedure (TrySkill skill threshold bonus &tmp skillValue difference extended ret)
	; Exercises a skill, while also reducing stamina, and exercising any complementary 
	; basic skills (i.e. strength, intelligence, agility, vitality, luck)
	;
	; Returns whether the skill attempt was a success or not. 
	; (success is determined either by the supplied threshold, or a random threshold from 1-100.
	;
	
	;if hero has a 0 in that skill, we can't even try anything.
	(if (not (= skillValue [egoStats skill]))
		(return FALSE)
	)
	
	;;if we've specified a bonus parameter, add it to our base skill level.
	(if (== argc 3)
		(+= skillValue bonus)
	)
	
	;if there's no specified threshold, then we pick one randomly between 1 and 100.
	;further, if it's a skill above one of the core 5 (STR, VIT, AGL, INT, LUCK), 
	;then it'll take stamina to try: either 1/10th the threshold amount, or a random number between 1-6
	(if threshold
		(if (>= skill WEAPON) (UseStamina (/ threshold 10)))
	else
		(if (>= skill WEAPON) (UseStamina (Random 1 6)))
		(= threshold (Rand100))
	)
	
	;if we get lucky, then we boost our skill value by 1-20 points.
	(if (>= (StatCheck LUCK 1) (Random 1 200))
		(+= skillValue (Random 1 20))
	)
	;if the threshold is below (or equal) to the skillvalue + bonus, then we return a success.
	;otherwise we'll be returning failure.
	(= ret (<= threshold skillValue))
	;how close to the threshold were we?
	;the theory being that if our skill level is close (over or under) to the threshold
	;the hero is learnign quicker, and so their skills will grow faster.
	(= difference (Abs (- threshold skillValue)))
	;reduce the difference down to (x + 10)/10
	(= difference
		(cond 
			((<= difference 10)
				2
			)
			((<= difference 30)
				4
			)
			((<= difference 50)
				;CI: TODO: this is an easy place to tweak skill growth.
				; if this was a lower number, skills would climb faster 
				; when you've already mastered all the challenges.
				6
			)
			(else 
				(return ret)
			)
		)
	)
	(= extended
		;if it's not a basic skill, then we have to also increase those by varying conditions.
		(cond 
			((== skill WEAPON)
				;weapon use requires strength and agility (2:2)/16
				(/ (+ (StatCheck AGIL 2) (StatCheck STR 2)) 16)) ;i.e. (2*AGIL + 2*STR)/16
			((or (== skill PARRY) (== skill DODGE) (== skill STEALTH))
				;dodge, parry, and stealth require strength and intelligence (3:1)/8
				(/ (+ (StatCheck STR 3) (StatCheck INT 1)) 8))
			((== skill PICK)
				;lock picking requires agility and intelligence (3:1)/4
				(/ (+ (StatCheck AGIL 3) (StatCheck INT 1)) 4))
			((or (== skill THROW) (== skill CLIMB))
				;throwing and climbing requires agility and strength (3:2)/5
				(/ (+ (StatCheck AGIL 3) (StatCheck STR 2)) 5))
			((>= skill OPEN)
				;magic spells require Magic and Intelligence
				(/ (+ (StatCheck MAGIC 4) (StatCheck INT 2)) 6))
			(else 
				; everything else would be the base skills, and magic.
				; CI: NOTE: technically experience, HP, SP, and MP could also be subject to this,
				; but that doesn't make logical sense, so it's best not to actually do that
				; in any code.
				10
			)
		)
	)
	(SkillUsed skill (/ extended difference))
	(return ret)
)

(procedure (SkillUsed skill amount)
	; increases the skillTicks by amount. When skillTicks surpasses egoStats, 
	; then those points are spent, increasing that skill by a random number from 1-3.
	; If the skill is increased, TRUE is returned, otherwise FALSE is returned.
	;
	
	;if the skill is a 0, it can never increase.
	(if (not [egoStats skill])
		(return FALSE)
	)
	
	;if amount is > than the current skill level, then set it to the current skill level
	(= amount (Abs amount))
	(if (> amount [egoStats skill])
		(= amount [egoStats skill])
	)
	;increase experience by amount/4
	(+= [egoStats EXPER] (/ amount 4))
	
	;increase skillTicks by amount
	(+= [skillTicks skill] amount)
	
	(if (>= [skillTicks skill] [egoStats skill] )
		;reduce skillTicks by the current egoStats
		;i.e. if skillTicks is 51 and egoStats is 50, then points becomes 1, and must climb up again to increase the stats
		(-= [skillTicks skill] [egoStats skill])
		;increase egoStats by a random amount from 1-3
		(+= [egoStats skill] (Random 1 3))
		;if we're above 100, cap it at 100
		(if (> [egoStats skill] 100)
			(= [egoStats skill] 100) ;Stats max out at 100.
		)
		;return TRUE, the skill was increased
		(return TRUE)
	)
	;skill was not increased.
	(return FALSE)
)

(procedure (SolvePuzzle flagEnum points charClass)
	; sets the specified flag, then adds the specified points to the totalScore.
	; if a heroType is specified, the flag and points will only be awarded if we're that heroType.
	;
	
	(if (and (>= argc 3) (!= heroType charClass))
		;if we're specifying a heroType (and this this hero ain't it), then nothing more to do.
		(return)
	)
	(if (not (Btst flagEnum))
		(Bset flagEnum)
		(theGame changeScore: points)
		;increase intelligence every time you solve an in-game puzzle (i.e. get game points)
		(SkillUsed INT points)
	)
)

(procedure (Rand100)
	;returns a random number from 1 to 100
	;
	(return (+ 1 (/ (Random 0 999) 10)))
)

(procedure (TakeDamage damage)
	; reduces ego's HP by the specified amount, 
	; and returns whether the hero is still alive or not.
	;
	
	(if (> damage 0) 
		;losing HP works out your Vitality skill
		(SkillUsed VIT (/ (+ damage 1) 2))
	)
	(if (< (-= [egoStats HEALTH] damage) 0)
		(= [egoStats HEALTH] 0)
	)
	(if (> [egoStats HEALTH] (MaxHealth))
		(= [egoStats HEALTH] (MaxHealth))
	)
	(return (> [egoStats HEALTH] 0))
)

(procedure (MaxMana &tmp egoMagic)
	; Maximum MP is (intelligence + 2*Magic) / 3
	;
	
	(return
		(if (= egoMagic [egoStats MAGIC])	;If player has any magic skill
			(return (/ (+ [egoStats INT] egoMagic egoMagic) 3))
		else
			(return 0)	;Otherwise, mana will always be at zero
		)
	)
)

(procedure (MaxStamina)
	; Maximum SP is (Agility + Vitality) * 2
	;
	
	(return (* (+ [egoStats AGIL] [egoStats VIT]) 2))
)

(procedure (MaxHealth &tmp tmpHealth)
	; Maximum HP is ((Strength + 2*Vitality) /3) * 2
	;
	
	(return
		(+
			(= tmpHealth (/ (+ [egoStats STR] [egoStats VIT] [egoStats VIT]) 3))
			tmpHealth
		)
	)
)

(procedure (MaxLoad)
	; Maximum weight (in lbs) carried is 40 + (Strength / 2)
	; 
	
	(return (+ 40 (/ [egoStats STR] 2)))
)

(procedure (CastSpell spellNum &tmp spellObj)
	;Try to cast a spell
	(cond 
		((not [egoStats MAGIC])
			(HighPrint 0 58))
			;You don't know how to cast spells.
		((not (ego knows: spellNum))
			(HighPrint 0 59))
			;You don't know that spell.
		((< [egoStats MANA] [spellCost (- spellNum OPEN)])
			(HighPrint 0 60))
			;You don't have enough Magic Points to cast that spell.
		(else
			(TrySkill spellNum 0)
			(UseMana [spellCost (- spellNum OPEN)])
			(return TRUE)
		)
	)
	(return FALSE)
)

(procedure (ChangeGait newGait gaitMsg &tmp theView)
	(if gaitMsg
		(cond 
			((not (User canControl:))
				(HighPrint 0 49)
				;You can't do that now.
				(return)
			)
			((== egoGait newGait)
				(HighPrint 0 50)
				;Go ahead. Just do it.
				(return)
			)
		)
	)
	(if (!= newGait -1)
		(= egoGait newGait)
	)
	(ego setLoop: -1 cycleSpeed: 0 moveSpeed: 0)
	(switch egoGait
		(MOVE_RUN
			(ego view: vEgoRunning setStep: 6 4 setCycle: egoSW vEgoStanding)
		)
		(MOVE_SNEAK
			(ego view: vEgoSneaking setStep: 2 1 setCycle: Walk)
		)
		(else 
			(ego view: vEgo setStep: 3 2 setCycle: egoSW vEgoStanding)
		)
	)
	(if fastEgo	;although the option was removed from the menu, the code is still present.
		(ego setStep: (* (ego xStep?) 2) (* (ego yStep?) 2))
	)
	;regardless of the walking method, if you're carrying to much
	; you walk the slowest increment possible.
	(if (Btst fOverloaded)
		(ego setStep: 1 1)
	)
)

(procedure (EatMeal)
	(cond 
		(freeMeals		;already eaten earlier
			(-- freeMeals)
		)
		([invNum iRations]	;we've got rations, so eat one
			(if (not (-- [invNum iRations]))
				(CenterPrint 0 61)
				;You just ate your last ration; you'd better get some more food soon.
			)
		)		
		((Btst fHungry)
			(Bset fStarving)
			(CenterPrint 0 62)
			;You're starving.  Better find some food *soon*!
			(TakeDamage 1)
			;CI: TODO: this is a potential bug. If the hero has only 1 HP, and skips a meal, 
			;he could die with no death message.
			;EO: Actually, the hero doesn't die at all. Seems the developers never put in a
			;special death messsage.
		)
		(else (Bset fHungry)
			(CenterPrint 0 63))
			;You're really getting hungry.
	)
)

(procedure (StatCheck statNum statMult)
	; Increases the StatPool by the multiplier amount,
	; then returns ego's skill multiplied by that multiplier.
	; (Only used by the TrySkill procedure)
	
	(SkillUsed statNum statMult)
	(return (* [egoStats statNum] statMult))
)

;Procedure from HQ1 that was removed from QFG1
;(procedure (ResetDroppedInventory &tmp i temp1)
;	(for ((= i 1)) (<= i NUM_INVITEMS) ((++ i))
;		(= [invDropped i] 0)
;	)
;)

(class HQEgo of Ego
	;this is a special subclass of Ego to handle the
	;Quest for Glory specific attributes the hero has 
	;i.e. the more complex inventory system, skill management, magic, etc.	
	(properties
		name "ego"
	)
	
	(method (get what howMany &tmp obj num oldNum newWeight)
		(= num (if (== argc 1) 1 else howMany))
		(= oldNum [invNum what])
		(cond 
			(
				(>
					(= newWeight
						(+
							(WtCarried)
							(/ (+ 59 (* num [invWeight what])) 60)
						)
					)
					(MaxLoad)
				)
				(if (not (Btst fOverloaded))
					(HighPrint 0 0)
					;You are carrying so much that you can hardly move.  You'd better drop something soon.
					(Bset fOverloaded)
					(ego setStep: 1 1)
				)
			)
			((Btst fOverloaded)
				(Bclr fOverloaded)
				(ChangeGait -1 FALSE))
		)
		(if (< (+= num oldNum) 0)
			(= num 0)
		)
		(= [invNum what] num)
		(return (- num oldNum))
	)
	
	(method (put what howMany &tmp obj num)
		(= num (if (== argc 1) 1 else howMany))
		(if (not (= num (self use: what num)))
			(return 0)
		)
		(+= [invDropped what] num)
		(return
			(if (not (ego has: iMushroom))
				(Bclr fHaveFaeryShrooms)
			else
				FALSE
			)
		)
	)
	
	(method (has what)
		(return [invNum what])
	)
	
	(method (use what howMany &tmp obj num oldNum)
		(if
			(>
				(= num (if (== argc 1) 1 else howMany))
				[invNum what]
			)
			(= num [invNum what])
		)
		(self get: what (- num))
		(if (and (== what iMushroom) (not [invNum iMushroom]))
			(Bclr fHaveFaeryShrooms)
		)
		(return num)
	)
	
	(method (drop what)
		(self put: what &rest)
	)
	
	(method (pickUp what howMany &tmp obj num index count some)
		(= num (if (== argc 1) 1 else howMany))
		(= count [invDropped what])
		(= some 0)
		(if count
			(= some
				(if (u< count num) count else num)
			)
			(self get: what some)
			(-= [invDropped what] some)
		)
		(return some)
	)
	
	(method (knows what)
		(return [egoStats what])
	)
	
	(method (learn what howWell &tmp obj num)
		(= num (if (== argc 1) 5 else howWell))
		(if (and [egoStats MAGIC] (> num [egoStats what]))
			(= [egoStats what] num)
		)
		(return [egoStats what])
	)
)

(instance egoBase of Code
	(method (doit theActor &tmp theX theY)
		(= theX (theActor x?))
		(= theY (+ 1 (theActor y?)))
		(theActor
			brTop: (- theY 2)
			brBottom: theY
			brLeft: (- theX 9)
			brRight: (+ theX 9)
		)
	)
)

(instance egoObj of HQEgo)

(instance contMusic of Sound
	(properties
		number 26
	)
)

(instance endBattle of Sound
	(properties
		number 26
		priority 10
	)
)

(instance music of Sound
	(properties
		number 26
		priority 10
	)
)

(instance statusCode of Code
	(method (doit str)
		(Format str 0 1 score)
		;   So You Want To Be A Hero  [score %d of 500]	 ;CI: HQ V1.000 - V1.105
		;   Quest for Glory I         [score %d of 500]  ;CI: QFG1 V1.200
	)
)

(instance keyHandler of EventHandler
	(method (handleEvent event)
		(if
			(and
				(not (super handleEvent: event))
				(== (event message?) ENTER)
			)
			(cls)
			(event claimed: TRUE)
		)
	)
)

(instance dirHandler of EventHandler)

(instance mouseHandler of EventHandler)

(instance hSW of SysWindow)

(instance Glory of Game
	
	(method (init &tmp egoStopWalk)
		(Load SCRIPT HQINIT)
		(= systemWindow hSW)
		((= ego egoObj) baseSetter: egoBase)
		(= version {1.200____})
		(= waitCursor HAND_CURSOR)
		(StatusLine code: (= dftStatusCode statusCode))
		(= egoStopWalk StopWalk)
		(super init:)
		((= keyDownHandler keyHandler) add:)
		((= mouseDownHandler mouseHandler) add:)
		((= directionHandler dirHandler) add:)
		(= numVoices (DoSound NumVoices))
		((= cSound contMusic)
			number: (SoundFX 26)
			owner: self
			init:
		)
		((= spareSound endBattle)
			number: (SoundFX 26)
			owner: self
			init:
		)
		(music
			number: (SoundFX 26)
			owner: self
			init:
		)
		(= deathMusic (SoundFX 26))
		(GameStartRoom)
	)
	
	(method (doit &tmp thisTime)
		(super doit:)
		(if
			;if we've started the game, and time has passed since the last loop,
			;run the regular cycle stuff
			(and
				(Btst fInMainGame)
				(!= oldSysTime (= thisTime (GetTime SYSTIME1)))
			)
			(= oldSysTime thisTime)
			(++ Clock)
			;if it's passed day 7, then time passes twice as fast.
			(if (and (>= Day 7) (& Clock $0001))
				(++ Clock)
			)
			;if it's passed 3600, change to the next day.
			(if (>= Clock GAMEDAY)
				(= Clock 0)
				(NextDay)
			)
			;if ego's cursed, and time is up, time to die!
			(if
				(and
					(Btst fBabaCurse)
					(> Day dayCursedByBabaYaga)
					(> Clock 750)
				)
				(HandsOff)
				(HighPrint 0 2)
				;Suddenly, you hear voices, seeming to come from everywhere at once.  They all sound like Baba Yaga, and they all say:
				;"Wheeeeeere's myyyyyy
				;Maaaandraaaaake Rooooooooooooot????"
				(EgoDead 0 3
					#icon vDeathScenes 1 2 
					#title {Curses!}
				)
				;Because you failed to meet Baba Yaga's DEADline, her curse turns you into a frog on the spot, and you are forced to live
				;out your years dodging Sauruses (Saurii?) with large feet.
			)
			;if Bruno has just left the target range, count down the seconds until he's out of range.
			(if brunoTimer
				(-- brunoTimer)
			)
			;eat a ration at noon and dinner.
			(if (or (== Clock 1100) (== Clock 2500))
				(EatMeal)
			)
			
			;if ego has used the Undead Unguent, we should decrease its timer.
			(if (Btst fGhostOil)
				(switch (-- ghostOilTimer)
					(24
						(HighPrint 0 4) ;getting close, so print a warning
						;The tingling sensation is wearing off.
					) 
					
					(0
						(Bclr fGhostOil)
						(HighPrint 0 5)
						;The tingling sensation is gone.
					)
				)
			)
			
			;every 20 game seconds, ego gets refreshed in Stamina.
			(if (not (-- stamCounter))
				(= stamCounter STAM_RATE)
				(cond 
					;if the hero's starving, or has gone more than 1 day without sleep, reduce SP by 1
					((or (Btst fStarving) (> lostSleep 1))
						(UseStamina 5)
					)
					;if the Hero's running, then reduce SP by 2
					((== egoGait MOVE_RUN)
						(UseStamina 2)
					)
					;if the hero's not getting hungry, and hasn't gone a day without sleep, then regain by 1
					((and (not (Btst fHungry)) (not lostSleep))
						(UseStamina -1)
					)
				)
				;mana gets refreshed once every 5 stamina refreshes
				(if (not (-- manaCounter))
					(= manaCounter MANA_RATE)
					(UseMana -1)
				)
				;health gets refreshed once every 15 stamina refreshes
				(if (not (-- healCounter))
					(= healCounter HEAL_RATE)
					(TakeDamage -1)
				)
			)
		)
	)
	
	(method (replay)
		(InitGlobals)
		(if (not (OneOf curRoomNum INTRO CHARSEL CHARALLOC SPEED ENDGAME))
			(TheMenuBar draw:)
			(StatusLine enable:)
		)
		(if (DoSound SoundOn)
			(SetMenu soundI p_value TRUE p_text {Turn off})
		else
			(SetMenu soundI p_value FALSE p_text {Turn on})
		)
		(super replay:)
	)
	
	(method (newRoom roomNum)
		(HandsOn)
		(super newRoom: roomNum)
		(self
			setCursor: (if isHandsOff waitCursor else normalCursor) (HaveMouse)
		)
	)
	
	(method (startRoom roomNum &tmp newRegion)
		(StartARoom roomNum) ;Most of the startRoom method was moved into its own script.
		(DisposeScript STARTAROOM)
		(if (u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
			(cond 
				(debugging
					(if (Print 0 6 #button {Debug} TRUE)
						; Memory fragmented.						
						(SetDebug)
					)
				)
				;out of memory problem. quit now by throwing quit/restart/restore dialog.
				((!= roomNum CHARSAVE)
					(EgoDead 0 7
						#title {Bitten by a program bug}
					;Suddenly, the deadly poison Fragmentation Bug leaps out of a crack in the system,
					;and injects you with its poison.  Alas, there is no cure, save to . . .
					)
				)
			)
		)
		(mouseDownHandler add: cast features)
		StopWalk
		Cycle
		(super startRoom: roomNum)
		;EO: Keep debugging enabled when changing rooms, so that "Memory Fragmented" errors can be diagnosed
;		(= debugging FALSE)
		(if debugging
			(curRoom setLocales: DEBUG)
		)
	)
	
	(method (handleEvent event &tmp i [temp1 4] num gotOne)
		(switch (event type?)
			(keyDown
				(keyDownHandler handleEvent: event)
			)
			(mouseDown
				(if
					(and
						(not isHandsOff)
						(not (mouseDownHandler handleEvent: event))
						(not (Random 0 4)) ;1 in 4 chance of displaying a random mouse click message.
						(& (event modifiers?) shiftDown)
					)
					(HighPrint 297 (Random 26 34))
					;random message about random pixels and nothing to see here stuff
				)
			)
			(mouseUp
				(cast handleEvent: event)
			)
			(direction
				(directionHandler handleEvent: event)
			)
			(joyDown
				(event type: keyDown message: ENTER)
				(keyDownHandler handleEvent: event)
			)
			(saidEvent
				(cond 
					((super handleEvent: event)) ;let rooms, regions, and locales claim the event first
					((or (Said 'quit') (Said 'done,done/game'))
						(PromptQuit)
					)
					(
						(or
							(Said 'kiss,boff,blow,crap,leak')
							(Said 'get/crap,leak')
							(Said 'eat/crap')
						)
						(HighPrint 297 (Random 0 8))
						;random message about manners
					)
					((Said 'get>')
						(cond 
							((== (= i (SaidInv event)) NULL)
								(HighPrint 0 8)
								;You can't get that.
							)
							((== i iShield)
								(if (== curRoomNum shieldRoom)
									(ego get: iShield)
									(HighPrint 0 9)
									;You retrieve your shield.
									(= shieldRoom 0)
								else
									(HighPrint 0 10)
									;There aren't any here.
								)
							)
							((and (== i iDagger) (== curRoomNum daggerRoom))
								(if (and monsterNum (> monsterHealth 0))
									(HighPrint 0 11)
									;You don't have time -- there's the slight matter of the monster that wants to eat you.
								else
									(= gotOne 0)
									(= daggerRoom 0)
									(if (or missedDaggers [invDropped iDagger])
										(= gotOne TRUE)
										(ego get: iDagger (+ missedDaggers [invDropped iDagger]))
										(HighPrint 0 12)
										;You pick up the loose daggers.
									)
									(if hitDaggers
										(= gotOne TRUE)
										(ego get: iDagger hitDaggers)
										(HighPrint 0 13)
										;You retrieve your knives from the dead monster's body, and carefully wipe them clean for reuse.
									)
									(= [invDropped iDagger]
										(= hitDaggers (= missedDaggers 0))
									)
									(if (not gotOne)
										(HighPrint 0 10)
									)
								)
							)
							((ego pickUp: i -1)
								(HighPrint 0 14)
								;Ok
							)
							(else
								(HighPrint 0 10)
								;There aren't any here.
							)
						)
					)
					((Said 'throw/dagger') 
						(ThrowKnife 0)
					)
					((Said 'throw/boulder')
						(ThrowRock 0)
					)
					((Said 'cast>')
						(cond 
							((not (= i (SaidSpell event))) 
								(HighPrint 0 15)
								;That isn't a known spell.
							)
							((CastSpell i)
								(switch i
									(OPEN
										(CastOpen)
										(HighPrint 0 16)
										;The spell has no effect.
									)
									(DETMAGIC
										(HighPrint 0 17)
										;You sense no magic in this area.
									)
									(DAZZLE
										(CastDazz)
									)
									(ZAP
										(= zapPower (+ 5 (/ [egoStats ZAP] 10)))
										(if (or (ego has: iSword) (ego has: iDagger))
											(HighPrint 0 18)
											;Your weapon is now magically charged.
										else
											(HighPrint 0 19)
											;You don't have a weapon to charge.
										)
									)
									(CALM
										(CastCalm)
									)
									(FLAMEDART
										(CastDart 0)
									)
									(else
										(HighPrint 0 16)
										;The spell has no effect.
									)
								)
							)
						)
					)
					((Said 'fight')
						(HighPrint 0 20)
						;Aggressive, aren't you?
					)
					((Said 'chat')
						(HighPrint 0 21)
						;No one responds.
					)
					((or (Said 'put<down>') (Said 'deposit>'))
						(cond 
							((not (= i (SaidInv event)))
								(HighPrint 0 22)
								;I don't know what you're trying to drop.
							)
							([invNum i]
								(if (== i iShield)
									(= shieldRoom curRoomNum)
									(ego use: iShield)
								else
									(ego drop: i 1)
								)
								(HighPrint 0 23)
								;Ok, you drop it.
								(if (not (ego has: iMushroom))
									(Bclr fHaveToadstools)
								)
							)
							(else
								(HighPrint 0 24)
								;You can't drop something you don't have.
							)
						)
					)
					((Said 'walk') 
						(ChangeGait MOVE_WALK TRUE)
					)
					((Said 'run')
						(ChangeGait MOVE_RUN TRUE)
					)
					((or (Said '[use]/stealth') (Said 'sneak'))
						(if (!= egoGait MOVE_SNEAK)
							(if (TrySkill STEALTH trySneak 0)
								(ChangeGait MOVE_SNEAK TRUE)
							else
								(HighPrint 0 25)
								;You're about as stealthy as the average Goon.
							)
						)
					)
					((Said 'unlock,lockpick/door,hasp')
						(HighPrint 0 26)
						;There's no point in trying that here.
					)
					((Said 'lockpick/nose')
						(if (CanPickLocks)
							(HighPrint 0 27)
							;You delicately insert the lockpick into your left nostril.
							(if (not (TrySkill PICK tryPickNose))
								(EgoDead 0 28
									#title {The surgeon general warns . . ._}
									#icon vDeathScenes 0 0
								)
								;Unfortunately, you push it in too far, causing yourself a cerebral hemorrhage.
								;Guess you should have practiced some more on less difficult locks.
							else
								(HighPrint 0 29)
								;Success! You now have an open nose.
							)
						else
							(HighPrint 0 30)
							;You don't have an appropriate tool.
						)
					)
					((Said 'ask')
						(HighPrint 0 31)
						;You get no response.
					)
					((Said 'buy')
						(HighPrint 0 32)
						;It's not for sale.
					)
					((Said 'eat>')
						(Eat event)
					)
					((Said '/ale<root<dazzle<razzle')
						;toggle the debug mode
						(if (^= debugging TRUE)
							(curRoom setLocales: DEBUG)
						)
					)
					((Said 'use,drink>')
						(DrinkPotion event 0)
					)
					((Said 'thank')
						(HighPrint 0 33)
						;You're welcome!
					)
					((Said '/sorry')
						(HighPrint 0 34)
						;That's OK.
					)
					((Said 'affirmative,n,please')
						(HighPrint 0 21)
						;No one responds.
					)
					((Said '/hello')
						(HighPrint 0 35)
						;Hi!
					)
					((Said '/bye')
						(HighPrint 0 36)
						;Bye!
					)
					((Said 'open/door')
						(HighPrint 0 37)
						;You're not close enough to a door.
					)
					((Said 'open/box,musicbox')
						(if (ego has: iMusicBox)
							(HighPrint 0 38)
							;You open the music box, which plays Beethoven's "Fur Elise."  After listening to it for a bit, you close the box.
						else
							(HighPrint 0 39)
							;You don't have a box to open.
							
						)
					)
					((Said 'knock')
						(HighPrint 0 40)
						;No one hears you knocking.
					)
					((Said 'sat')
						(HighPrint 0 41)
						;You don't need to.
					)
					((Said 'gave')
						(HighPrint 0 42)
						;You have no reason to do that.
					)
					((Said 'search[/!*,room,area]')
						(HighPrint 0 43)
						;You don't find anything interesting.
					)
					((Said 'look,read>')
						(= num
							(+
								[invNum (= i (SaidInv event))]
								[invDropped i]
							)
						)
						(cond 
							((not i) 
								(HighPrint 297 (Random 10 13))
								;you see nothing special, etc.
							)
							(num
								(HighPrint INVDESC (+ i i 1))
								;inventory item descriptions
							)
							(else (HighPrint 0 44)
								;You don't see any here.
							)
						)
					)
					((Said 'rest[/!*]')
						(EgoRests 10 TRUE)
					)
					((or (Said 'nap[/!*]') (Said 'go[<to]/nap'))
						(EgoSleeps 5 0)
					)
				)
			)
		)
	)
	
	(method (wordFail word)
		(Printf 0 45 word)
		;You will not need to use the word "%s" in this game.
	)
	
	(method (syntaxFail)
		(HighPrint 0 46)
		;Please try a different way of saying that.
	)
	
	(method (pragmaFail)
		(HighPrint 0 47)
		;I'm not sure what you're trying to do.
	)
)

(instance egoSW of StopWalk)
