;;; Sierra Script 1.0 - (do not remove this comment)
(script# MAIN) ;0
(include game.sh)
(include "999.shm") (include "815.shm")
(include "120.shm")
(use GameInit)
(use CastDart)
(use ThrowKnife)
(use ThrowRock)
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
	ego								  	;pointer to ego
	theGame							  	;ID of the Game instance
	curRoom							  	;ID of current room
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
	version			=	0			  	;pointer to 'incver' version string
										;	WARNING!  Must be set in room 0
										;	(usually to {x.yyy    } or {x.yyy.zzz})
	locales								;set of current locales
	curSaveDir							;address of current save drive/directory string
	aniThreshold	=	10
	perspective							;player's viewing angle:
										;	 degrees away from vertical along y axis
	features							;locations that may respond to events
	sortedFeatures          			;above+cast sorted by "visibility" to ego
	useSortedFeatures					;enable cast & feature sorting?
	egoBlindSpot						;used by sortCopy to exclude
										;actors behind ego within angle 
										;from straight behind. 
										;Default zero is no blind spot
	overlays			=	-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	demoDialogTime	=	3				;how long Prints stay up in demo mode
	currentPalette
	modelessPort
	[sysLogPath	20]						;-used for system standard logfile path	
	endSysLogPath						;/		(uses 20 globals)
	gameControls						;pointer to instance of game controls
	ftrInitializer						;pointer to code that gets called from
										;	a feature's init
	doVerbCode							;pointer to code that gets invoked if
										;	no feature claims a user event
	approachCode						;pointer to code that translates verbs
										;	into bits
	useObstacles	=	TRUE			;will Ego use PolyPath or not?
	theMenuBar
	theIconBar							;points to TheIconBar or Null	
	mouseX								;-last known mouse position
	mouseY								;/
	keyDownHandler						;-our EventHandlers, get called by game
	mouseDownHandler					;/
	directionHandler					;/
	speechHandler						;a special handler for speech events
	lastVolume
	pMouse			=	NULL			;pointer to a Pseudo-Mouse, or NULL
	theDoits		=	NULL			;list of objects to get doits each cycle
	eatMice			=	60				;how many ticks before we can mouse
	user			=	NULL			;pointer to specific applications User
	syncBias							;-globals used by sync.sc
	theSync								;/
	cDAudio
	fastCast							;list of talkers on screen
	inputFont		=	SYSFONT			;font used for user type-in
	tickOffset							;used to adjust gameTime after restore
	howFast								;measurment of how fast a machine is
	gameTime							;ticks since game start
	narrator							;pointer to narrator (normally Narrator)
	msgType			=	TEXT_MSG		;type of messages used
	messager							;pointer to messager (normally Messager)
	prints								;list of Print's on screen
	walkHandler							;list of objects to get walkEvents
	textSpeed		=	2				;time text remains on screen
	altPolyList							;list of alternate obstacles
	;globals 96-99 are unused
	global96
	global97
	global98
	lastSysGlobal
	;globals 100 and above are for game use	
	egoGait					;0 = walk, 1 = run, 2 = sneak
	gameCode =  1234
	isHandsOff
	egoX
	egoY
	debugging
	cSound					;music object, current playing music?
	daySheriffBreakIn		;this is the game day that you broke into the Sheriff's house.
							;After this day, the door will be barred, and you can no longer break in.
	dayLOLBreakIn			;this is the game day that you broke into the Little Old Lady's house.
							;After this day, the door willbe barred, and you can no longer break in
	dayCursedByBabaYaga		;this is the game day that Baba Yaga placed her curse on you.  
							;If you don't bring her the mandrake root before the time is up, you will die.
	exploringTown			;This is only ever set to FALSE (and never read)
							;And even then, it's only referenced outside the sheriff and lol house, 
							;when leaving to another part of town.
	yesNoTimer				;a countdown used by several actors when they are waiting for a response
	lastRestTime		
	lastRestDay		
	monsterDistX
	monsterDistY
	Clock
	Night
	Day
	timeODay				;time of day in game (morning, noon, etc)
	barNote					;Note being passed at the bar
	oldSysTime
	heroType				;The Character Class. 0 = Fighter, 1 = Magic User or 2 = Thief
	egoSpeed
	startingRoom
	egoStats				;hero's skills (25 variable array)
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
		global149			;end of egoStats
	skillTicks				;skillTicks (25 variable array)
		global151			;	determines when the skill is next increased 
		global152			;	When skills are used (either by TrySkill, SkillUsed, or StatCheck)
		global153			;	the skillTicks increase by an amount (either directly entered for SkillUsed and StatCheck, or inciredtly for TrySkill)
		global154			;	When skillTicks goes above egoStats, then skillTicks resets, and egoStat increases by a random amount betweeen 1 and 3	
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
		global174			;end of skillTicks
	lockPickBonus			;lock picking bonus (LockPick gives you 10 bonus, Tool Kit gives you 35 bonus)
	;these 8 are part of an array
	spellCost 	  		=	2 	;SpellMPUsage (Open)
		spCostDetect	=	2 	;SpellMPUsage (Detect Magic)
		spCostTrigger	=	3 	;SpellMPUsage (Trigger)
		spCostDazzle	=	3 	;SpellMPUsage (Dazzle)
		spCostZap		=	3 	;SpellMPUsage (Zap)
		spCostCalm		=	4 	;SpellMPUsage (Calm)
		spCostFlame		=	5 	;SpellMPUsage (Flame Dart)
		spCostFetch		=	5 	;SpellMPUsage (Fetch)
		spellCostEnd
	fastEgo						;double's ego's walking speed (option removed in 1.200)
	magesMazeButtonIndex
	magesMazeCommand
	global189					;unused
	global190					;unused
	spellMask					;checks which spells will be in the spell inventory
	theBuyDialog				;global pointer for buy dialog
	wareList					;list of available wares in buy dialog
	global194					;? set to 100 when casting Flame Dart
	numColors
	numVoices
	stamCounter =  STAM_RATE
	healCounter =  HEAL_RATE
	freeMeals					;As the game progresses through the day, this gets reduced first. If this is 0, then you eat a ration.
	ghostOilTimer				;number of game minutes left until the undead unguent wears off
	oldStats					;(25 variable array) - These are the value of skills as last seen on the character sheet.
		global202				; Changed skills will be shown in red.
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
	endStats				;end of oldStats
	global227				;unused
	global228				;unused
	zapPower			;the extra damage incurred by a zap-charged weapon
	monsterDazzle			;number of animation cycles until the monster attacks again, in the arena
		
	;array of 9 variables, corresponding to direction event messages
	targetAngles 	=  [180 0 45 90 135 180 225 270 315]
	numFlowers				;times sold flowers to the healer
	numMushrooms			;times sold mushrooms to the healer
	numWater				;has the hero given flying water to the healer?
	brigandHead				;which view to use for the current brigand in combat (not used in VGA)
	numBrigands				;how many brigands is the hero fighting at once?
	egoCanFight				;during an arena battle, can the hero fight, or is he in the middle of something?
	masterDay =  -1			;when did the hero last fight the weapon master
	lostSleep				;how many days has the hero gone without sleeping?
	totalDagNabItBet		;CI: ?? just a guess, could be something else, but is related to DagNabIt somehow.
	thievesPassword			; (unused in VGA)
	missedDaggers			;EO: Daggers thrown on ground
	hitDaggers				;EO: Daggers thrown in enemy
	daggerRoom				;the last room the hero dropped daggers into. He can pick up all his daggers in this room.
	sameColor =  SAME_COLOR	;The colour of stats on the Character Screen
	changeColor =  CHANGE_COLOR	;The colour of stats that have changed since last viewing, on the Character Screen
	shieldRoom				;the room hero dropped his shield in. He can pick it up later.
	mandrakeDay =  -3		;the day hero last pulled the Mandrake Root
	dftStatusCode			;default Status Bar refresh code. i.e. Quest for Glory I [%d of 500]
	;wizard Erasmus variables
	wizGameSpellTime		;time left for Fetch and Flame Dart in the wizard game.
	wizAskedSpells			;What spells has Erasmus asked that you already know?

	koboldIllBits =  cWHITE	;Kobold cave-related
	dayKoboldAwakened =  -1	;The day the Kobold was last awakened
	bucks					;Number of silvers on the monster in the current room
	theKobold				;global variable holding the kobold TargetActor
	koboldCycles			;not actually used for anything.
	ogreDay					;the day the Ogre was last fought
	ogreTime				;the time zone the Ogre was last fought
	fightingKoboldStart		;the fight with the kobold has begun!
	fightingKobold			;True if in battle with Kobold?
	dartsBonus				;used for DagNabIt
	statusBarView			;the View to use to display battle status
	global271				;unused
	ghostCount
	hutState				;0, 1, 2, 3, 4 as possible values.
	babaState				;0, 1, 2, 3, 4 as possible values.
	deathMusic =  26
	numApples				;The number of apples given to Frost Giant
	nestState				;The state of the next outside the Healer's hut (0 = in tree, 1 = on ground, 2 = burnt)
	numGoblins				;The number of dead goblins in the Goblin Ambush
	monsterNum				;EO: Monster in the current room
	monsterHealth			;HP of the current fighting monster
	brunoTimer				;this counts down the game seconds after Bruno has left the target range.  
							; If you don't wait long enough, you'll encounter him!
	;kobold variables
	koboldHealth
	koboldEvade				;the threshold to which the kobold avoids getting hit (lower values means less chance of getting hit)
	damageToKobold			;how much damage will the kobold take if he gets hit?
	damageToKoboldFlame		;how much damage from a flame dart will the kobold take?
	egoKoboldBattleLoop		;EO: used for 1.200
	enter67					;number of visits to room 67; this determines the appearance of the fox and Earl Sinclair.
	mountainSign			;what the signs at Mount Zauberberg say
	global289				;unused
	gameFlags				;(50 variable arroy) - all event flags in the game
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
	invDropped			;dropped inventory (start of 50 variable array; 40 are used)
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
	ogreHealth =  93				;ogre's current HP (CI: looks like his max used to 93, but was upped to 112 later on.)
	ogreDeathDay =  1000			;day defeated Ogre
	brutusHealth					;HP for Brutus in target range
	manaCounter =  MANA_RATE		;MP Countdown
	spareSound						;music playing in the arenas during a battle
	magesMazePlayCount				;The number of times the hero has played Mage's Maze
	disabledIcons					;which icons are disabled
	global419						;unused
	saveCursorX
	saveCursorY
	theCurIcon
	totalInvItems
	oldScore
	disabledActions					;which action icons are disabled
	projObj						;object ID for projectile
	targetDaggers					;how many daggers are in the target
	userName						;(40 variable array) - name of the user, as supplied by the user when starting a new game.
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
	nightPalette					;number of room's nighttime palette
	global452						;unused
)
(procedure (HandsOff)
	;disable ego control
	(if isHandsOff
		(return)
		(DisposeScript PROCS)
	)
	(= isHandsOff TRUE)
	(SaveCurIcon)
	(User
		canControl: FALSE
		canInput: FALSE
	)
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
		(= saveCursorX mouseX)
		(= saveCursorY mouseY)
		(theGame setCursor: waitCursor TRUE 310 185)
	else
		(theGame setCursor: waitCursor TRUE)
	)
)

(procedure (HandsOn &tmp i)
	;enable ego control
	(if isHandsOff
		(= isHandsOff FALSE)
		(theGame setSpeed: egoSpeed)
		(= egoSpeed 6)
		(User canControl: TRUE canInput: TRUE)
	)
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
	(ego signal: (| (ego signal?) fixedCel))
	(LoadCurIcon)
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: ICON_USEIT)
	)
	(if 
		(or
			(not [egoStats MAGIC]) ;no magic, no cast icon
			(not
				;does ego know any spells?
				(for ((= i OPEN)) (<= i FETCH) ((++ i))
					(if (ego knows: i)
						(return TRUE)
						(DisposeScript PROCS)
					)
				)
			)
		)
		(theIconBar disable: ICON_CAST)
	)
	(if
		(and
			(not (theIconBar curInvIcon?))
			(== (theIconBar curIcon?) (theIconBar at: ICON_USEIT))
		)
		(theIconBar advanceCurIcon:)
	)
	(return
		(if (not (HaveMouse))
			(theGame
				setCursor: ((theIconBar curIcon?) cursor?) TRUE saveCursorX saveCursorY
			)
		else
			(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
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

(class HQEgo of Ego
	;this is a special subclass of Ego to handle the
	; Quest for Glory specific attributes the hero has 
	; i.e. skill management, magic, etc.
	(properties
		view vEgo
		noun N_EGO
		modNum EGOSEZ
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
		(if (< (+= num oldNum) 0)
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
					(== theCurIcon (theIconBar at: ICON_USEIT))
				)
				(= theCurIcon (theIconBar at: ICON_WALK))
				(theIconBar curIcon: ICON_WALK)
			else
				(SetCurIcon ICON_WALK)
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
			(|= spellMask
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
			(theIconBar enable: ICON_CAST)
		)
		(return [egoStats what])
	)
)

(instance stopGroop of GradualLooper
	;ego's GradualLooper
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
	;draw the status line
	(method (doit roomNum &tmp [statusBuf 50] [scoreBuf 50])
		(if
			(not
				;don't draw the status line in these rooms
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
					;all talkers in the game
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
					(STOOGE (ScriptID 95 1))	;just a guess, but there's no such talker in that room
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
	(method (init &tmp egoSW versionFile)
		;set up the game's objects and globals
		StopWalk
		PolyPath
		Polygon
		(ScriptID HQINIT)
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
			;if we've started the game, and time has passed since the last loop,
			;run the regular cycle stuff
			(and
				(Btst fInMainGame)
				(!= oldSysTime (= thisTime (GetTime SYSTIME1)))
			)
			(= oldSysTime thisTime)
			(++ Clock)
			;if it's passed day 7, then time passes twice as fast.			
			(if (and (>= Day 7) (& Clock 1))
				(++ Clock)
			)
			;if it's passed 3600, change to the next day.
			(if (>= Clock GAMEDAY)
				(= Clock 0)
				(NextDay)
			)
			;if ego's sneaking, use the skill a bit
			(if (== egoGait MOVE_SNEAK)
				(SkillUsed STEALTH 1)
			)
			;if ego's cursed, and time is up, time to die!
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
			;if Bruno has just left the target range, count down the seconds until he's out of range.
			(if brunoTimer
				(-- brunoTimer)
			)
			;if we advance to a new hour, change timeODay
			(if (not (mod Clock GAMEHOUR))
				(switch Clock
					(300
						;not yet dawn
						(= timeODay TIME_NOTYETDAWN)
					)
					(750
						;day is dawning
						(= Night FALSE)
						(= timeODay TIME_DAWN)
						(Bclr fStableClean)
						(PalVary PALVARYREVERSE 150)
					)
					(1050
						;time for breakfast
						(EatMeal)
					)
					(1200
						;mid-morning
						(= timeODay TIME_MIDMORNING)
					)
					(1650
						;midday
						(= timeODay TIME_MIDDAY)
					)
					(2100
						;midafternoon
						(= timeODay TIME_MIDAFTERNOON)
					)
					(2400
						;time for dinner
						(EatMeal)
					)
					(2550
						;sunset approaches
						(= timeODay TIME_SUNSET)
					)
					(3000
						;night is still young
						(= Night TRUE)
						(PalVary PALVARYSTART (curRoom picture?) 150)
						(if nightPalette
							(PalVary PALVARYTARGET nightPalette)
						)
						(= timeODay TIME_NIGHT)
					)
					(3450
						;middle of the night
						(Bset fFatigued)
						(= timeODay TIME_MIDNIGHT)
					)
				)
			)
			;getting tired from lack of sleep
			(if (and (Btst fFatigued) (not fastCast))
				(Bclr fFatigued)
				(if (== (++ lostSleep) 1)
					(messager say: N_PROCS NULL NULL 8 0 PROCS)
				else
					(messager say: N_PROCS NULL NULL 9 0 PROCS)
				)
			)
			;if ego has used the Undead Unguent, we should decrease its timer.
			(if (Btst fGhostOil)
				(switch (-- ghostOilTimer)
					(24	;getting close, so print a warning
						(if fastCast
							(= ghostOilTimer 30)
						else
							(messager say: N_CUE NULL NULL 2 0 SYSTEM)
						)
					)
					(0
						;oil has worn off
						(if fastCast
							(= ghostOilTimer 5)
						else
							(Bclr fGhostOil)
							(messager say: N_CUE NULL NULL 3 0 SYSTEM)
						)
					)
				)
			)
			;every 20 game seconds, ego gets refreshed in Stamina.
			(if (not (-- stamCounter))
				(if fastCast
					(= stamCounter 5)
				else
					(= stamCounter STAM_RATE)
					(cond 
						;if the hero's starving, or has gone more than 1 day without sleep, reduce SP by 5
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
		(SaveCurIcon)
		(theGame setCursor: waitCursor TRUE)
		(StartARoom roomNum) ;Most of the startRoom method was moved into its own script.
		(Message MsgGet SYSTEM N_CUE NULL C_SCRIPT_NUM 1 @scriptNum)
		(Format @debugNum @scriptNum DEBUG)
		;if debugging and memory is fragmented, bring up a warning and the internal debugger
		; WARNING: The debugger is not in the interpreter, so using it crashes the game!
		(if (or debugging (FileIO fileExists @debugNum))
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
			;all rooms where you can encounter a monster
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
			(LoadCurIcon)
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
						(if (or debugging (FileIO fileExists @debugNum))
							((ScriptID DEBUG) handleEvent: event)
						)
					)
				)
			)
			(mouseDown
				(Message MsgGet SYSTEM N_CUE NULL C_SCRIPT_NUM 1 @scriptNum)
				(Format @debugNum @scriptNum DEBUG)
				(if (or debugging (FileIO fileExists @debugNum))
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
		;cue death from Baba Yaga's curse
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
	(method (doit theIcon)
		(if
			(and
				(theIcon isKindOf: IconItem)
				(& (theIcon signal?) DISABLED)
			)
			(|= disabledIcons (>> $8000 (theIconBar indexOf: theIcon)))
		)
	)
)

(instance gcWin of GloryWindow
	(method (open &tmp t l b r theColor theMaps thePri [str 15] [scoreBuf 15])
		(= thePri -1)
		(self
			top: (/ (- SCRNHIGH (+ (CelHigh vControlIcons 1 1) 6)) 2)
			left: (/ (- SCRNWIDE (+ CONTROL_WIDTH (CelWide vControlIcons 0 1))) 2)
			bottom:
				(+
					(CelHigh vControlIcons 1 1) 6
					(/ (- SCRNHIGH (+ (CelHigh vControlIcons 1 1) 6)) 2)
				)
			right:
				(+
					CONTROL_WIDTH
					(CelWide vControlIcons 0 1)
					(/ (- SCRNWIDE (+ CONTROL_WIDTH (CelWide vControlIcons 0 1))) 2)
				)
			priority: thePri
		)
		(super open:)
		
		(DrawCel vControlIcons 1 0 INDICATOR_X INDICATOR_Y thePri)
		(DrawCel vControlIcons 1 0 (+ INDICATOR_X 36) 28 thePri)
		(DrawCel vControlIcons 1 0 (+ INDICATOR_X 72) 28 thePri)
		
		;detail header
		(DrawCel vControlIcons 0 4 HEADER_X (- 24 (+ (CelHigh vControlIcons 0 4) 3)) thePri)
		
		;volume header
		(DrawCel vControlIcons 0 3 (+ HEADER_X 36) (- 24 (+ (CelHigh vControlIcons 0 4) 3)) thePri)
		
		;speed header
		(DrawCel vControlIcons 0 2 (+ HEADER_X 72) (- 24 (+ (CelHigh vControlIcons 0 4) 3)) thePri)
		(= r (+ (= t (+ 34 (CelHigh vControlIcons 0 1))) 10))
		(= b
			(+
				(= l (+ 4 (CelWide vControlIcons 1 1)))
				(-
					(+ CONTROL_WIDTH (CelWide vControlIcons 0 1))
					(+ 4 (CelWide vControlIcons 1 1) 10)
				)
			)
		)
		(= theColor 0)
		(= theMaps 1)
		(Graph GFillRect t l (+ r 1) (+ b 1) theMaps theColor thePri)
		(Graph GShowBits t l (+ r 1) (+ b 1) 1)
		(Message MsgGet SYSTEM N_SCORE NULL NULL 1 @str)
		(Format @scoreBuf @str score possibleScore)
		(DrawCel vControlIcons 0 5 (+ 4 (CelWide vControlIcons 1 1) 8) (+ 34 (CelHigh vControlIcons 0 1) 2) thePri)
		(Display @scoreBuf
			p_font 999
			p_color 52
			p_at (+ (CelWide vControlIcons 0 5) 4 (CelWide vControlIcons 1 1) 13) (+ 34 (CelHigh vControlIcons 0 1) 2 1)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view vControlIcons
		loop 0
		cel 1
		nsLeft SLIDER_X
		nsTop SLIDER_Y
		signal FIXED_POSN
		noun N_DETAIL
		modNum SYSTEM
		helpVerb V_HELP
		sliderView vControlIcons
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
		view vControlIcons
		loop 0
		cel 1
		nsLeft (+ SLIDER_X 36)
		nsTop SLIDER_Y
		signal FIXED_POSN
		noun N_VOLUME
		modNum SYSTEM
		helpVerb V_HELP
		sliderView vControlIcons
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view vControlIcons
		loop 0
		cel 1
		nsLeft (+ SLIDER_X 72)
		nsTop SLIDER_Y
		signal FIXED_POSN
		noun N_SPEED
		modNum SYSTEM
		helpVerb V_HELP
		sliderView vControlIcons
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
		view vControlIcons
		loop 2
		cel 0
		nsLeft CONTROL_BUTTON_X
		nsTop CONTROL_BUTTON_Y
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_SAVE
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconRestore of ControlIcon
	(properties
		view vControlIcons
		loop 3
		cel 0
		nsLeft CONTROL_BUTTON_X
		nsTop (+ CONTROL_BUTTON_Y 17)
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTORE
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconRestart of ControlIcon
	(properties
		view vControlIcons
		loop 4
		cel 0
		nsLeft CONTROL_BUTTON_X
		nsTop (+ CONTROL_BUTTON_Y 34)
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_RESTART
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconQuit of ControlIcon
	(properties
		view vControlIcons
		loop 5
		cel 0
		nsLeft CONTROL_BUTTON_X
		nsTop (+ CONTROL_BUTTON_Y 51)
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_QUIT
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconAbout of ControlIcon
	(properties
		view vControlIcons
		loop 6
		cel 0
		nsLeft CONTROL_BUTTON_X
		nsTop (+ CONTROL_BUTTON_Y 68)
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_ABOUT
		modNum SYSTEM
		helpVerb V_HELP
	)
)

(instance iconGCHelp of IconItem
	(properties
		view vControlIcons
		loop 7
		cel 0
		nsLeft CONTROL_BUTTON_X
		nsTop (+ CONTROL_BUTTON_Y 85)
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
		view vControlIcons
		loop 8
		cel 0
		nsLeft CONTROL_BUTTON_X
		nsTop (+ CONTROL_BUTTON_Y 102)
		cursor 949
		message 10
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		noun N_OK
		modNum SYSTEM
		helpVerb V_HELP
	)
)


(instance hq1DoVerbCode of Code
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
						(CastDart 0 0
							((= evt (Event new:)) x?)
							(+ (evt y?) 24)
						)
						(evt dispose:)
					)
					(else 
						(if (= oldCurIcon (theIconBar curInvIcon?))
							(switch (= index (inventory indexOf: oldCurIcon))
								(iRock
									(ThrowRock 0)
									(return TRUE)
								)
								(iDagger
									(ThrowKnife 0)
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
		(if (Btst fPickItUp)
			((ScriptID GLORYINV 1) init:)
		)
	)
	
	(method (show)
		(LoadCurIcon)
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
			((and prevIcon (not (& (prevIcon signal?) DISABLED)))
				(= curIcon prevIcon)
			)
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
				(SaveCurIcon)
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
						(SaveCurIcon)
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
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK $0001)
			(V_TALK $0002)
			(V_WALK $0004)
			(V_DO $0008)
			(V_MONEY $0010)
			(else  $8000)
		)
	)
)
