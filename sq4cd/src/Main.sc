;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use brain)
(use ColorInit)
(use RegionPath)
(use SQEgo)
(use Sq4Dialog)
(use Sq4Narrator)
(use Print)
(use Sync)
(use PMouse)
(use BordWind)
(use IconBar)
(use RandCyc)
(use Feature)
(use StopWalk)
(use DCIcon)
(use Timer)
(use Grooper)
(use Sound)
(use Game)
(use Invent)
(use System)

(public
	sq4 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	IsObjectOnControl 5
	Btst 6
	Bset 7
	Bclr 8
	EgoHeadMove 9
	EgoDead 10
	SolvePuzzle 11
	DoDisplay 12
	Face 13
	ShowStatus 14
	proc0_15 15
	VerbFail 16
	Babble 17
	VGAOrEGA 18
	waitC 19
)

(local
	ego
	theGame
	curRoom
	unused_1		;was originally speed, which became global 199 for CD version
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
	textCode
	cuees
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	version
	unused_3
	curSaveDir
	unused_4
	perspective
	features
	unused_5
	useSortedFeatures
	unused_6
	overlays =  -1
	doMotionCue
	systemWindow
	unused_7
	unused_8
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
	cDAudio
	fastCast
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
	music
	dongle =  1234
	curRegionPath
	global103 =  1	;unused
	global104		;unused
	numColors
	numVoices
	startingRoom
	global108		;unused
	global109		;unused
	mallRoomVisits	;number of times ego has been in a room at the mall
	global111		;unused
	theEgoHead
	theStopGroop
	gameFlags
		global115
		global116
		global117
		global118
		global119
		global120
	endGameFlags
	timeCodeEstros	;array
		global123
		global124
		global125
		global126
	endTimeCodeEstros
	timeWarpEntries	;when first entering the Timepod, you must input 2 complete entries,
					; the second of which will take you to Estros.
	;Interface color globals
	myLowlightColor
	myTopBordColor
	myTextColor
	myTextColor2
	myTextColor3
	myTextColor4
	myTextColor5
	myTextColor6
	myTextColor7
	myTextColor8
	myTextColor9
	myTextColor10
	myTextColor11
	myTextColor12
	myTextColor13
	myTextColor14
	myTextColor15
	myTextColor16
	myTextColor17
	myTextColor18
	myTextColor19
	myTextColor20
	myTextColor21
	myTextColor22
	myTextColor23
	globalSound
	myInsideColor
	myBotBordColor
	myRgtBordColor
	myBackColor
	buckazoids =  59
	heldBox	;the item you took from Software Excess' bargain bin
	myLftBordColor
	laptopUses
	hiddenHints
		global164
		global165
		global166
		global167
	revealedHints
	buckazoidsInATM =  2001
	laptopPlug
	roomTeleports
	monolithBurgerEarnings	;amount of buckazoids earned while working at Monolith Burger.
							; Once you have made at least 34 buckazoids, you can no longer work there.
	timeCodeXenon =  10
		global174
		global175
		global176
		global177
	endTimeCodeXenon
	timeCodeMall
		global180
		global181
		global182
		global183
	endTimeCodeMall
	beamSetting
	deathView
	deathMessage
	currentEra
	zondraTextColor
	global190	;unused
	msAstroChickenPlays	;after playing Ms. Astro Chicken 5 times, the machine will break down
	oldMouseX	;unused in CD version
	oldMouseY	;unused in CD version
	global194	;unused
	oldCurIcon
	oldCanControl
	oldCanInput
	disabledIcons
	speed =  6
	formatTimeStopped
	babbleCase
)
(procedure (NormalEgo theLoop theView theStoppedView &tmp stoppedView)
	(= stoppedView 0)
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1)
			(ego view: theView)
			(if (> argc 2) (= stoppedView theStoppedView))
		)
	)
	(if (not stoppedView)
		(= stoppedView 4)
	)
	(ego
		normal: 1
		moveHead: 1
		setLoop: -1
		setLoop: stopGroop
		setPri: -1
		setMotion: 0
		setCycle: StopWalk stoppedView
		setStep: 3 2
		illegalBits: 0
		ignoreActors: 0
		setSpeed: speed
	)
)

(procedure (HandsOff)
	(if (not oldCurIcon)
		(= oldCurIcon (theIconBar curIcon?))
	)
	(= oldCanControl (user canControl:))
	(= oldCanInput (user canInput:))
	(user canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= disabledIcons 0)
	(theIconBar eachElementDo: #perform checkIcon)
	(theIconBar curIcon: (theIconBar at: ICON_CONTROL))
	(theIconBar disable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_SMELL
		ICON_TASTE
		ICON_ITEM
		ICON_INVENTORY
	)
	(if (not (HaveMouse))
		(theGame setCursor: INVIS_CURSOR)
	else
		(theGame setCursor: waitC)
	)
)

(procedure (HandsOn &tmp temp0)
	(user canControl: TRUE canInput: TRUE)
	(theIconBar enable: 
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_SMELL
		ICON_TASTE
		ICON_ITEM
		ICON_INVENTORY
	)
	(if (and argc temp0) (DisableIcons))
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: ICON_ITEM)
	)
	(if oldCurIcon
		(theIconBar curIcon: oldCurIcon)
		(theGame setCursor: (oldCurIcon cursor?))
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: ICON_ITEM))
				(not (theIconBar curInvIcon?))
			)
			(theIconBar advanceCurIcon:)
		)
	)
	(= oldCurIcon 0)
	(theGame setCursor: ((theIconBar curIcon?) cursor?))
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
)

(procedure (IsObjectOnControl theObj theControl)
	(return
		(if (& (theObj onControl: origin) theControl)
			(return TRUE)
			else FALSE)
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

(procedure (Bset flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
	(return oldState)
)

(procedure (EgoHeadMove theHead &tmp headView)
	(= headView 0)
	(if argc
		(= headView theHead)
	else
		(= headView 4)
	)
	((= theEgoHead egoHead)
		init: ego
		view: headView
		cycleSpeed: 150
	)
)

(procedure (EgoDead theView theReason)
	(if (> argc 0)
		(= deathView theView)
		(if (OneOf (ego view?) 373 374 993)
			(if (== deathView 0) (= deathView 7))
			(if (== deathView 8) (= deathView 9))
		)
	else
		(= deathView 0)
	)
	(if (> argc 1)
		(= deathMessage theReason)
	else
		(= deathMessage deathGENERIC)
	)
	(curRoom newRoom: EGODEAD)
)

(procedure (SolvePuzzle flagEnum points)
	(if (not (Btst flagEnum))
		(theGame changeScore: points)
		(Bset flagEnum)
		(pointsSound play:)
	)
)

(procedure (DoDisplay theString &tmp
		theMode theForeFont theBackFont theWidth theX theY theForeColor theBackColor ret)
	(return
		(if (== argc 1)
			(Display 0 0 p_restore [theString 0])
		else
			(= theX
				(= theY -1)
			)
			(= theMode 0)
			(= theForeFont 68)
			(= theBackFont 69)
			(= theWidth -1)
			(= theForeColor myTopBordColor)
			(= theBackColor 0)
			(= ret 1)
			(while (< ret argc)
				(switch [theString ret]
					(#mode
						(= theMode
							[theString (++ ret)]
						)
					)
					(#font
						(= theBackFont
							(+
								(= theForeFont
									[theString (++ ret)]
								)
								1
							)
						)
					)
					(#width
						(= theWidth
							[theString (++ ret)]
						)
					)
					(#at
						(= theX
							[theString (++ ret)]
						)
						(= theY
							[theString (++ ret)]
						)
					)
					(#color
						(= theForeColor [theString (++ ret)])
					)
					(#back
						(= theBackColor
							[theString (++ ret)]
						)
					)
				)
				(++ ret)
			)
			(= ret
				(Display
					[theString 0]
					p_at theX theY
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theBackFont
					p_save
				)
			)
			(Display
				[theString 0]
				p_at theX theY
				p_color theForeColor
				p_width theWidth
				p_mode theMode
				p_font theForeFont
			)
			(return ret)
		)
	)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY temp3)
	(= temp3 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3) (= temp3 both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= temp3 whoToCue))
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject temp3) temp3 else 0)
	)
)

(procedure (ShowStatus theCurrentEra &tmp textColor [str 25] [titleBuf 100] [sizeBuf 4] i)
	(if (!= theCurrentEra -1) (= currentEra theCurrentEra))
	(StrCpy @str {Space Quest_})
	(switch currentEra
		(1
			(StrCat @str {\1B - The Sarien Encounter})
		)
		(3
			(StrCat @str {\1C - The Pirates of Pestulon})
		)
		(4
			(StrCat @str {\1A - Roger Wilco and The Time Rippers})
		)
		(10
			(StrCat @str {\1E - Latex Babes of Estros})
		)
		(12
			(StrCat @str {\1D - Vohaul's Revenge \1F})
			(= textColor myTextColor18)
		)
	)
	(TextSize @sizeBuf @str 0 -1)
	(StrCpy @titleBuf {\06})
	(= i (/ (- 326 (- [sizeBuf 3] [sizeBuf 1])) 2))
	(while (> i 0)
		(StrCat @titleBuf {\06})
		(-- i)
	)
	(StrCat @titleBuf @str)
	(DrawStatus @titleBuf 0 myBackColor)
)

(procedure (proc0_15 param1 param2)
	(return (if (== (param1 onControl:) param2) (return 1) else 0))
)

(procedure (VerbFail theVerb &tmp temp0)
	(cond 
		((== theVerb V_LOOK)
			(switch (= temp0 (Random 0 1))
				(0 (narrator modNum: 0 say: 41))
				(1
					(narrator modNum: 65 say: 20)
				)
			)
		)
		((== theVerb V_TALK)
			(switch (= temp0 (Random 0 3))
				(0
					(narrator modNum: 395 say: 4)
				)
				(1
					(narrator modNum: 500 say: 3)
				)
				(2
					(narrator modNum: 531 say: 11)
				)
				(3
					(narrator modNum: 545 say: 10)
				)
			)
		)
		((== theVerb V_SMELL)
			(switch (= temp0 (Random 0 3))
				(0
					(narrator modNum: 50 say: 15)
				)
				(1
					(narrator modNum: 55 say: 17)
				)
				(2
					(narrator modNum: 65 say: 13)
				)
				(3
					(narrator modNum: 371 say: 41)
				)
			)
		)
		((== theVerb V_TASTE)
			(switch (= temp0 (Random 0 3))
				(0 (narrator modNum: 40 say: 3))
				(1
					(narrator modNum: 371 say: 30)
				)
				(2
					(narrator modNum: 371 say: 37)
				)
				(3
					(narrator modNum: 530 say: 10)
				)
			)
		)
		((== theVerb V_DO)
			(switch (= temp0 (Random 0 2))
				(0
					(narrator modNum: 300 say: 2)
				)
				(1
					(narrator modNum: 371 say: 20)
				)
				(2
					(narrator modNum: 387 say: 29)
				)
			)
		)
		((== theVerb V_WALK) (narrator modNum: 0 say: 13))
		(else
			(switch (= temp0 (Random 0 2))
				(0 (narrator modNum: 0 say: 40))
				(1
					(narrator modNum: 50 say: 14)
				)
				(2 (narrator modNum: 70 say: 2))
			)
		)
	)
	(narrator modNum: -1)
)

(procedure (Babble theView theString moreStuff &tmp [str 500])
	(if (u< theString 1000)
		(GetFarText theString moreStuff @str)
	else
		(StrCpy @str theString)
	)
	(babbleIcon
		view: theView
		cycleSpeed: (* (+ howFast 1) 4)
	)
	(if (u< theString 1000)
		(Prints @str &rest #icon babbleIcon 0 0)
	else
		(Prints @str moreStuff &rest #icon babbleIcon 0 0)
	)
)

(procedure (VGAOrEGA vga ega)
	(if (< vga 0) (= vga 0))
	(if (> vga 255) (= vga 255))
	(if (< ega 0) (= ega 0))
	(if (> ega 15) (= ega 15))
	(return (if (Btst fIsVGA) vga else ega))
)

(procedure (DisableIcons &tmp i)
	(user canControl: oldCanControl canInput: oldCanInput)
	(= i 0)
	(while (< i 8)
		(if (& disabledIcons (>> FORCE i))
			(theIconBar disable: i)
		)
		(++ i)
	)
)

(instance egoObj of SQEgo
	(properties
		name {ego}
		sightAngle 180
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 1))
			(V_TALK (narrator modNum: 0 say: 2))
			(V_DO (narrator modNum: 0 say: 3))
			(V_TASTE (narrator modNum: 0 say: 4))
			(V_SMELL (narrator modNum: 0 say: 5))
			(V_CIGAR (narrator modNum: 0 say: 6))
			(else  (VerbFail theVerb))
		)
	)
)

(instance egoHead of Head
	(properties
		view 4
	)
	
	(method (doVerb theVerb theItem)
		(ego doVerb: theVerb theItem)
	)
)

(instance longSong of Sound
	(properties
		number 1
	)
)

(instance longSong2 of Sound
	(properties
		number 1
	)
)

(instance pointsSound of Sound
	(properties
		flags mNOPAUSE
		number 888
		priority 15
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

(instance babbleIcon of DCIcon
	(properties)
	
	(method (init)
		(super init: &rest)
		(if (== msgType CD_MSG)
			((= cycler (MouthSync new:))
				init: self 0 99 0 babbleCase 1
			)
			(DoAudio Play 0 99 0 babbleCase 1)
		else
			((= cycler RandCycle) init: self 20)
		)
	)
)

(instance sq4KeyDownHandler of EventHandler
	(properties)
)

(instance sq4MouseDownHandler of EventHandler
	(properties)
)

(instance sq4DirectionHandler of EventHandler
	(properties)
)

(class sq4 of Game
	(properties
		parseLang ENGLISH
		subtitleLang NULL
	)
	
	(method (init &tmp temp0)
		(= systemWindow sq4Win)
		(Sq4GlobalNarrator noun: NARRATOR)
		(= narrator Sq4GlobalNarrator)
		(ColorInit)
		(= theStopGroop stopGroop)
		(= useSortedFeatures TRUE)
		(ScriptID SQFEATURE)
		(super init:)
		(= msgType (if (= cDAudio TRUE) CD_MSG else TEXT_MSG))
		(DoAudio DACFound TRUE)
		(DoAudio Rate 11000)
		(StrCpy @sysLogPath {})
		(= doVerbCode sq4DoVerbCode)
		(= ftrInitializer sq4FtrInit)
		((= keyDownHandler sq4KeyDownHandler) add:)
		((= mouseDownHandler sq4MouseDownHandler) add:)
		((= directionHandler sq4DirectionHandler) add:)
		(= pMouse PseudoMouse)
		(= speed 6)
		(self setCursor: theCursor TRUE 304 172)
		((= ego egoObj)
			_head: (= theEgoHead egoHead)
			setSpeed: speed
		)
		((ego _head?) client: ego)
		(user alterEgo: ego canControl: FALSE canInput: FALSE)
		((= music longSong) owner: self init:)
		((= globalSound longSong2) owner: self init:)
		(= normalCursor walkCursor)
		(= possibleScore 315)
		(= userFont 4)
		(= version {x.yyy})
		(= numVoices (DoSound NumVoices))
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(Bclr fIsVGA)
		else
			(Bset fIsVGA)
		)
		(sq4Win
			color: 0
			back: myBackColor
			topBordColor: myTopBordColor
			lftBordColor: myLftBordColor
			rgtBordColor: myRgtBordColor
			botBordColor: myBotBordColor
		)
		(invWin
			color: 0
			back: myBotBordColor
			topBordColor: myBackColor
			lftBordColor: myRgtBordColor
			rgtBordColor: myInsideColor
			botBordColor: myLowlightColor
			insideColor: myInsideColor
			topBordColor2: myLowlightColor
			lftBordColor2: myLowlightColor
			botBordColor2: myBackColor
			rgtBordColor2: myLftBordColor
		)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor myBackColor
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
		)
		(icon5 message: (if (HaveMouse) 3840 else 9))
		(= inventory Inventory)
		(inventory
			init:
			add:
				buckazoid
				rope
				bomb
				rabbit
				battery
				jar
				gum
				tank
				hintbook
				pen
				atmCard
				plug
				cigar
				matches
				diskette
				laptop
				invLook
				invHand
				invSelect
				invHelp
				ok
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor myInsideColor
			eachElementDo: #init
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
		(buckazoid owner: ego)
		(= startingRoom (if (GameIsRestarting) 60 else 1))
		(theIconBar enable:)
		(self newRoom: SPEED)
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(not
				(OneOf curRoomNum
					SPEED 1 6 9 10 15 16 17 19 20 21 59
					119 120 150 321 329 330 335 340 345
					350 355 371 376 398 500 505 510 514
					515 520 525 531 541 615
				)
			)
			((ScriptID NOSEPICK 0) doit:)
		)
	)
	
	(method (play)
		(= theCursor (= waitCursor waitC))
		(super play: &rest)
	)
	
	(method (replay &tmp [temp0 21])
		(ShowStatus -1)
		(Palette PALIntensity 0 255 100)
		(super replay: &rest)
	)
	
	(method (newRoom newRoomNumber)
		(theGame setCursor: waitCursor)
		(pMouse stop:)
		(if modelessDialog (modelessDialog dispose:))
		(if (and (IsObject fastCast) (fastCast elements?))
			(fastCast eachElementDo: #dispose 1)
		)
		(if (== ((inventory at: iCigar) state?) 1)
			(narrator modNum: 0 say: 9)
			((inventory at: iCigar) state: 0)
		)
		(theIconBar disable:)
		(super newRoom: newRoomNumber)
	)
	
	(method (startRoom roomNum)
		((ScriptID DISPOSE) doit: roomNum)
		(cond 
			;the Galaxy Galleria
			(
				(OneOf roomNum
					370 371 375 376 380 381 385 386 387 390 391
					395 396 397 398 399 400 405 406 410 411 290
				)
				RegionPath
				(ScriptID MALL)
				(= curRegionPath MALL)
				(if (OneOf roomNum 405 406 410 411) (ScriptID INERTIA))	;for Skate-O-Rama
			)
			((OneOf roomNum 25 30 35 40 45 50 55 60 65)	;the streets of Xenon
				RegionPath
				(ScriptID STREET)
				(= curRegionPath STREET)
				(if (OneOf roomNum 25 30 35 40 45 50 55 60 65)	;the pink bunny is in these rooms
					(ScriptID BUNNY)
				)
			)
			((OneOf roomNum 75 80 85 90 95 100 105 110 115)
				(ScriptID SEWER)
			)
			((OneOf roomNum 609 610 611 612 613 614 615 620)
				(ScriptID ULENCE)
			)
			((OneOf roomNum 299 300 305 306 310 315 320 298)
				(ScriptID BUTTE)
			)
			((OneOf roomNum 150 500 505 510 514 515 520 525 541 544 545)
				RegionPath
				(ScriptID BRAIN)
				(= curRegionPath BRAIN)
			)
			((OneOf roomNum 1 6 9 10 15 16 17 19 20 21)
				(ScriptID INTRO)
			)
			((OneOf roomNum 530 535 540)
				(ScriptID LANDING)
			)
		)
		(if
			(not
				(OneOf roomNum
					803 1 6 9 10 15 16 17 19 20 21 59 119 120 150 321 329 330 335
					340 345 350 355 371 376 398 500 505 510 514 515 520 525 531 541 615
				)
			)
			(ScriptID NOSEPICK)
		)
		(ScriptID SIGHT)
		(theIconBar enable:)
		(super startRoom: roomNum)
		(if (cast contains: ego)
			(if
				(and
					(!= prevRoomNum 396)
					(ego normal?)
					(not (OneOf roomNum 405 406 410 411))	;Skate-o-Rama
					(not ((ego cycler?) isKindOf: StopWalk))
				)
				(ego setCycle: StopWalk 4)
			)
			(if
			(and (!= prevRoomNum 396) (not (ego looper?)))
				(ego setLoop: stopGroop)
			)
			(EgoHeadMove (egoHead view?))
		)
		(if (== (theIconBar curIcon?) (theIconBar at: ICON_ITEM))
			(theIconBar curIcon: (theIconBar at: ICON_WALK))
		)
	)
	
	(method (restart &tmp oldCur [msgBuf 70] [butBuf1 100] [butBuf2 70])
		(= oldCur (theGame setCursor: ARROW_CURSOR TRUE))
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(Message MsgGet 0 99 0 8 1 @msgBuf)
		(Message MsgGet 0 97 0 4 1 @butBuf1)
		(Message MsgGet 0 97 0 5 1 @butBuf2)
		(= babbleCase 8)
		(if
			(SQ4Print @msgBuf
				#button @butBuf1 1
				#button @butBuf2 0
				#icon babbleIcon 0 0
			)
			(super restart:)
		)
		(= babbleCase 0)
		(theGame setCursor: oldCur TRUE)
	)
	
	(method (save)
		(if
			(and
				(or
					(== curRoomNum 150)
					(== curRoomNum 151)
					(regions contains: BRAIN)
				)
				(> (brain formatting?) 0)
			)
			(= formatTimeStopped 1)
		)
		(super save: &rest)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(TAB
							(if (not (& (icon5 signal?) DISABLED))
								(inventory showSelf: ego)
							)
						)
						(SHIFTTAB
							(if (not (& (icon5 signal?) DISABLED))
								(inventory showSelf: ego)
							)
						)
						(`^q
							(theGame quitGame:)
							(event claimed: TRUE)
						)
						(`#2
							(cond 
								((theGame masterVolume:) (theGame masterVolume: 0))
								((> numVoices 1) (theGame masterVolume: 15))
								(else (theGame masterVolume: 1))
							)
							(event claimed: TRUE)
						)
						(`#5
							(theGame save:)
							(event claimed: TRUE)
						)
						(`#7
							(theGame restore:)
							(event claimed: TRUE)
						)
						(`#9
							(theGame restart:)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
	
	(method (setCursor cursorObj tOrF theX theY &tmp oldCurObj)
		(= oldCurObj theCursor)
		(if argc
			(if (IsObject cursorObj)
				((= theCursor cursorObj) init:)
			else
				(SetCursor (= theCursor cursorObj) 0 0)
			)
		)
		(if (and (> argc 1) (not tOrF)) (SetCursor 996 0 0))
		(if (> argc 2)
			(if (< theX 0) (= theX 0))
			(if (< theY 0) (= theY 0))
			(SetCursor theX theY)
		)
		(return oldCurObj)
	)
	
	(method (quitGame &tmp oldCur [msgBuf 70] [butBuf1 100] [butBuf2 70])
		(= oldCur (theGame setCursor: ARROW_CURSOR TRUE))
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(Message MsgGet 0 99 0 7 1 @msgBuf)
		(Message MsgGet 0 97 0 2 1 @butBuf1)
		(Message MsgGet 0 97 0 5 1 @butBuf2)
		(= babbleCase 7)
		(super
			quitGame: (SQ4Print @msgBuf
				#button @butBuf1 1
				#button @butBuf2 0
				#icon babbleIcon 0 0
			)
		)
		(= babbleCase 0)
		(theGame setCursor: oldCur TRUE)
	)
	
	(method (pragmaFail)
		(if (user canInput:) (VerbFail))
	)
	
	(method (showControls &tmp temp0 obj)
		((ScriptID SQGCONTROL 0) init:)
		(= obj (gameControls at: 3))
		(obj theObj: newSpeedCode selector: #doit)
		((ScriptID SQGCONTROL 0) show: dispose:)
	)
)

(instance newSpeedCode of Code
	(properties)
	
	(method (doit newSpeed &tmp node obj nextNode)
		(if argc
			(= speed newSpeed)
			(= node (FirstNode (cast elements?)))
			(while node
				(= nextNode (NextNode node))
				(if
					(or
						(not (IsObject (= obj (NodeValue node))))
						(not (obj respondsTo: #setSpeed))
					)
				else
					(obj setSpeed: newSpeed &rest)
				)
				(= node nextNode)
			)
		)
		(return speed)
	)
)

(instance lookCursor of Cursor
	(properties
		view 851
	)
)

(instance talkCursor of Cursor
	(properties
		view 853
	)
)

(instance doCursor of Cursor
	(properties
		view 852
	)
)

(instance walkCursor of Cursor
	(properties
		view 850
	)
)

(instance waitC of Cursor
	(properties
		view 972
	)
)

(instance helpCursor of Cursor
	(properties
		view 856
	)
)

(instance tasteCursor of Cursor
	(properties
		view 854
	)
)

(instance smellCursor of Cursor
	(properties
		view 855
	)
)

(instance ok of IconItem
	(properties
		view 901
		loop 3
		cel 0
		cursor 999
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		noun 98
		helpVerb 40
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: myBackColor)
		(super init:)
	)
)

(instance invLook of IconItem
	(properties
		view 901
		loop 2
		cel 0
		cursor 851
		message V_LOOK
		noun 98
		helpVerb 41
	)
	
	(method (init)
		(= cursor lookCursor)
		(self highlightColor: 0 lowlightColor: myBackColor)
		(super init:)
	)
)

(instance invHand of IconItem
	(properties
		view 901
		loop 0
		cel 0
		cursor 852
		message V_DO
		noun 98
		helpVerb 29
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: myBackColor)
		(super init:)
	)
)

(instance invHelp of IconItem
	(properties
		view 901
		loop 1
		cel 0
		cursor 856
		message V_HELP
		noun 98
		helpVerb 37
	)
	
	(method (init)
		(super init: &rest)
		(= cursor helpCursor)
		(self highlightColor: 0 lowlightColor: myBackColor)
	)
)

(instance invSelect of IconItem
	(properties
		view 901
		loop 4
		cel 0
		cursor 999
		noun 98
		helpVerb 30
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: myBackColor)
		(super init:)
	)
)

(instance buckazoid of InvItem
	(properties
		view 700
		cursor 951
		message V_BUCKAZOID
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(inventory hide:)
				((ScriptID 819 0) init:)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance jar of InvItem
	(properties
		view 700
		cel 1
		cursor 952
		message V_JAR
		signal IMMEDIATE
		owner 70
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (== cel 2)
					(narrator modNum: 0 say: 10)
				else
					(narrator modNum: 0 say: 11)
				)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance hintbook of InvItem
	(properties
		view 700
		cel 4
		cursor 955
		message V_HINTBOOK
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 12))
			(V_DO
				(cond 
					((curRoom script?) (narrator modNum: 0 say: 13))
					(
					(and (OneOf curRoomNum 397 398) (not (Btst fBoughtHintbook))) (narrator modNum: 0 say: 14))
					((not (HaveMem 6800)) (narrator modNum: 0 say: 15))
					(else
						(inventory curIcon: (inventory at: iPen) hide:)
						(curRoom setScript: (ScriptID 708 0))
					)
				)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance pen of InvItem
	(properties
		view 700
		cel 5
		cursor 956
		message V_PEN
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 16))
			(else  (VerbFail theVerb))
		)
	)
)

(instance atmCard of InvItem
	(properties
		view 700
		cel 6
		cursor 957
		message V_ATMCARD
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 17))
			(else  (VerbFail theVerb))
		)
	)
)

(instance plug of InvItem
	(properties
		view 700
		cel 7
		cursor 958
		message V_PLUG
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 18))
			(else  (VerbFail theVerb))
		)
	)
)

(instance cigar of InvItem
	(properties
		view 700
		cel 8
		cursor 959
		message V_CIGAR
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_MATCHES
				(if state
					(narrator modNum: 0 say: 20)
				else
					(narrator modNum: 0 say: 21)
					(= state 1)
				)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance matches of InvItem
	(properties
		view 700
		cel 9
		cursor 960
		message V_MATCHES
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 22))
			(else  (VerbFail theVerb))
		)
	)
)

(instance diskette of InvItem
	(properties
		view 700
		cel 10
		cursor 961
		message V_DISKETTE
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 23))
			(else  (VerbFail theVerb))
		)
	)
)

(instance rabbit of InvItem
	(properties
		view 700
		cel 11
		cursor 962
		message V_RABBIT
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((!= view 700)
						(inventory hide:)
						(self view: 700 loop: 0 cel: 11)
						(inventory show: ego)
					)
					((not ((inventory at: iBattery) owner?))
						(inventory hide:)
						(self view: 701 loop: 0 cel: 0)
						(inventory show: ego)
					)
					(else (narrator modNum: 0 say: 24))
				)
			)
			(4
				(if (== view 700)
					0
				else
					(SolvePuzzle fGotBattery 3)
					(narrator modNum: 0 say: 25)
					(inventory hide:)
					(ego get: iBattery)
					(self view: 700 loop: 0 cel: 11)
					(inventory show: ego)
				)
			)
			(12
				(narrator modNum: 0 say: 26)
				(inventory hide:)
				(ego put: iBattery 0)
				(inventory curIcon: 0 show: ego)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance battery of InvItem
	(properties
		view 700
		cel 12
		cursor 963
		message V_BATTERY
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 27))
			(else  (VerbFail theVerb))
		)
	)
)

(instance tank of InvItem
	(properties
		view 700
		cel 13
		cursor 964
		message V_TANK
		signal IMMEDIATE
		owner 335
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 28))
			(else  (VerbFail theVerb))
		)
	)
)

(instance rope of InvItem
	(properties
		view 700
		cel 14
		cursor 965
		message V_ROPE
		signal IMMEDIATE
		owner 65
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 29))
			(else  (VerbFail theVerb))
		)
	)
)

(instance laptop of InvItem
	(properties
		view 700
		cel 15
		cursor 966
		message V_LAPTOP
		signal IMMEDIATE
		owner 55
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(cond 
					((!= view 700)
						(inventory hide:)
						(self view: 700 loop: 0 cel: 15)
						(inventory show: ego)
					)
					((== ((inventory at: iBattery) owner?) 1) (narrator modNum: 0 say: 31))
					(else
						(inventory hide:)
						(self view: 701 loop: 0 cel: 1)
						(inventory show: ego)
					)
				)
			)
			(V_DO
				(if (== ((inventory at: iBattery) owner?) 1)
					(narrator modNum: 0 say: 32)
					(inventory hide:)
					(ego get: iBattery)
					(inventory show: ego)
				else
					(narrator modNum: 0 say: 33)
				)
			)
			(V_BATTERY
				(SolvePuzzle fBatteryInLaptop 3)
				(narrator modNum: 0 say: 34)
				(inventory hide:)
				(self view: 700 loop: 0 cel: 15)
				(ego put: iBattery 1)
				(inventory curIcon: 0 show: ego)
			)
			(V_PLUG
				(SolvePuzzle fPlugInLaptop 3)
				(narrator modNum: 0 say: 35)
				(inventory hide:)
				(ego put: iPlug 1)
				(inventory curIcon: 0 show: ego)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance gum of InvItem
	(properties
		view 700
		loop 2
		cursor 967
		message V_GUM
		signal IMMEDIATE
		owner 297
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if cel
					(narrator modNum: 0 say: 37)
				else
					(narrator modNum: 0 say: 38)
				)
			)
			(V_DO
				(if (not cel)
					(inventory hide:)
					(self view: 700 loop: 2 cel: 1 cursor: 968)
					(inventory show: ego)
				)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance bomb of InvItem
	(properties
		view 700
		cel 3
		cursor 954
		message V_BOMB
		signal IMMEDIATE
		owner 40
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator modNum: 0 say: 39))
			(else  (VerbFail theVerb))
		)
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
					(>> FORCE (theIconBar indexOf: theIcon))
				)
			)
		)
	)
)

(instance speakTimer of Timer
	(properties)
)

(instance icon0 of IconItem
	(properties
		view 900
		loop 0
		cel 0
		type (| userEvent walkEvent)
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		maskView 900
		maskLoop 14
		maskCel 1
		noun 98
		helpVerb 31
	)
	
	(method (init)
		(= cursor walkCursor)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon1 of IconItem
	(properties
		view 900
		loop 1
		cel 0
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskView 900
		maskLoop 14
		maskCel 1
		noun 98
		helpVerb 32
	)
	
	(method (init)
		(= cursor lookCursor)
		(super init:)
	)
)

(instance icon2 of IconItem
	(properties
		view 900
		loop 2
		cel 0
		message V_DO
		signal (| HIDEBAR RELVERIFY)
		maskView 900
		maskLoop 14
		noun 98
		helpVerb 33
	)
	
	(method (init)
		(= cursor doCursor)
		(super init:)
	)
)

(instance icon3 of IconItem
	(properties
		view 900
		loop 3
		cel 0
		message V_TALK
		signal (| HIDEBAR RELVERIFY)
		maskView 900
		maskLoop 14
		maskCel 3
		noun 98
		helpVerb 34
	)
	
	(method (init)
		(= cursor talkCursor)
		(super init:)
	)
)

(instance icon4 of IconItem
	(properties
		view 900
		loop 4
		cel 0
		cursor 999
		message NULL
		signal (| HIDEBAR RELVERIFY)
		maskView 900
		maskLoop 14
		maskCel 4
		noun 98
		helpVerb 35
	)
)

(instance icon5 of IconItem
	(properties
		view 900
		loop 5
		cel 0
		cursor 999
		type $0000
		message 0
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 900
		maskLoop 14
		maskCel 2
		noun 98
		helpVerb 28
	)
	
	(method (select)
		(if (super select:) (inventory showSelf: ego))
	)
)

(instance icon6 of IconItem
	(properties
		view 900
		loop 10
		cel 0
		message V_SMELL
		signal (| HIDEBAR RELVERIFY)
		maskView 900
		maskLoop 14
		noun 98
		helpVerb 36
	)
	
	(method (init)
		(= cursor smellCursor)
		(super init:)
	)
)

(instance icon7 of IconItem
	(properties
		view 900
		loop 11
		cel 0
		message V_TASTE
		signal (| HIDEBAR RELVERIFY)
		maskView 900
		maskLoop 14
		maskCel 1
		noun 98
		helpVerb 38
	)
	
	(method (init)
		(= cursor tasteCursor)
		(super init:)
	)
)

(instance icon8 of IconItem
	(properties
		view 900
		loop 7
		cel 0
		cursor 999
		message V_BUCKAZOID
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 900
		maskLoop 14
		maskCel 1
		noun 98
		helpVerb 39
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(theGame showControls:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 900
		loop 9
		cel 0
		type $0000
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		maskView 900
		maskLoop 14
		noun 98
		helpVerb 37
	)
	
	(method (init)
		(= cursor helpCursor)
		(super init:)
	)
)

(instance sq4DoVerbCode of Code
	(properties)
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK
				(if ((CueObj client?) facingMe: ego)
					(if ((CueObj client?) noun?)
						(narrator
							noun: ((CueObj client?) noun?)
							tVerb: V_LOOK
							say: 0
						)
						(narrator noun: 99 tVerb: 0)
					else
						(VerbFail theVerb)
					)
				)
			)
			(else  (VerbFail theVerb))
		)
	)
)

(instance sq4FtrInit of Code
	(properties)
	
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 90)
		)
		(if (== (theObj actions?) ftrDefault) (theObj actions: 0))
	)
)

(instance sq4Win of BorderWindow
	(properties)
)

(instance invWin of InsetWindow
	(properties)
)
