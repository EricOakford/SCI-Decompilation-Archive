;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use ColorInit)
(use RegionPath)
(use SQEgo)
(use PMouse)
(use GControl)
(use BordWind)
(use IconBar)
(use RandCyc)
(use StopWalk)
(use DCIcon)
(use Grooper)
(use Sound)
(use Game)
(use Invent)
(use User)
(use Actor)
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
	waitCursor =  20
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
	demoDialogTime =  3
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
	music
	dongle
	curRegionPath
	global103 =  1
	global104
	numColors
	numVoices
	startingRoom
	global108
	global109
	mallRoomVisits
	global111
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
	timeCodeEstros
		global123
		global124
		global125
		global126
	endTimeCodeEstros
	timeWarpEntries
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
	heldBox
	myLftBordColor
	laptopUses
	hiddenHints
		global164
		global165
		global166
		global167
	endHiddenHints
	buckazoidsInATM =  2001
	laptopPlug
	roomTeleports
	monolithBurgerEarnings
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
	global190
	msAstroChickenPlays
	oldMouseX
	oldMouseY
	global194
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
	(if (not stoppedView) (= stoppedView 4))
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
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
)

(procedure (HandsOff &tmp oldCurIcon)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= oldCurIcon (theIconBar curIcon?))
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
	(theIconBar curIcon: oldCurIcon)
	(if (not (HaveMouse))
		(= oldMouseX ((User curEvent?) x?))
		(= oldMouseY ((User curEvent?) y?))
		(theGame setCursor: waitCursor TRUE 304 172)
	else
		(theGame setCursor: waitCursor TRUE)
	)
	(if pMouse (pMouse stop:))
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
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
	(if (not (HaveMouse))
		(theGame
			setCursor: ((theIconBar curIcon?) cursor?) TRUE oldMouseX oldMouseY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
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
	(if argc (= headView theHead) else (= headView 4))
	((= theEgoHead egoHead)
		init: ego
		view: headView
		cycleSpeed: 24
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
			(Display 0 26 p_restore [theString 0])
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
	(DrawStatus @titleBuf 0 (VGAOrEGA myBackColor myInsideColor))
)

(procedure (proc0_15 param1 param2)
	(return (if (== (param1 onControl:) param2) (return 1) else 0))
)

(procedure (VerbFail &tmp list [temp1 2] cursor evt temp5 [temp6 5])
	(= cursor (theGame setCursor: 69 TRUE))
	(= evt (User curEvent?))
	(redX
		x: (evt x?)
		y: (+ 300 (evt y?))
		z: 300
		show:
	)
	((= list (List new:)) add: redX)
	(Animate (list elements?) TRUE)
	(Animate (cast elements?) FALSE)
	(= temp5 (GetTime))
	(while (< (Abs (- temp5 (GetTime))) 40)
		(breakif
			(OneOf ((= evt (Event new:)) type?) keyDown mouseDown)
		)
		(evt dispose:)
	)
	(if (IsObject evt) (evt dispose:))
	(redX hide: posn: 1000 -1000)
	(Animate (list elements?) TRUE)
	(list delete: redX dispose:)
	(theGame setCursor: cursor)
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
		(Print @str &rest #icon babbleIcon 0 0)
	else
		(Print @str moreStuff &rest #icon babbleIcon 0 0)
	)
)

(procedure (VGAOrEGA vga ega)
	(if (< vga 0) (= vga 0))
	(if (> vga 255) (= vga 255))
	(if (< ega 0) (= ega 0))
	(if (> ega 15) (= ega 15))
	(return (if (Btst fIsVGA) vga else ega))
)

(instance egoObj of SQEgo
	(properties
		name "ego"
		description {Roger Wilco}
		sightAngle 180
		lookStr {It's you. Roger Wilco, space guy.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk (Print 0 0))
			(verbDo (Print 0 1))
			(verbTaste (Print 0 2))
			(verbSmell (Print 0 3))
			(verbUse
				(switch theItem
					(iCigar (Print 0 4))
					(else  (VerbFail))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoHead of Head
	(properties
		description {Roger Wilco}
		lookStr {Roger Wilco, space guy.}
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
		((= cycler (RandCycle new:)) init: self 20)
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

(instance sq4 of Game
	(properties)
	
	(method (init &tmp temp0)
		(= systemWindow sq4Win)
		(ColorInit)
		(= theStopGroop stopGroop)
		(= useSortedFeatures TRUE)
		(super init:)
		(StrCpy @sysLogPath {})
		(= doVerbCode sq4DoVerbCode)
		(= ftrInitializer sq4FtrInit)
		((= keyDownHandler sq4KeyDownHandler) add:)
		((= mouseDownHandler sq4MouseDownHandler) add:)
		((= directionHandler sq4DirectionHandler) add:)
		(= pMouse PseudoMouse)
		(self egoMoveSpeed: 0 setCursor: theCursor TRUE 304 172)
		((= ego egoObj)
			_head: (= theEgoHead egoHead)
			moveSpeed: (self egoMoveSpeed?)
			cycleSpeed: (self egoMoveSpeed?)
		)
		((ego _head?) client: ego)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: FALSE
			canInput: FALSE
		)
		((= music longSong) owner: self init:)
		((= globalSound longSong2) owner: self init:)
		(= waitCursor HAND_CURSOR)
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
			back: (VGAOrEGA myBackColor myInsideColor)
			topBordColor: myTopBordColor
			lftBordColor: (VGAOrEGA myLftBordColor myTopBordColor)
			rgtBordColor: (VGAOrEGA myRgtBordColor myBotBordColor)
			botBordColor: myBotBordColor
		)
		(gcWin
			color: 0
			back: (VGAOrEGA myBackColor myInsideColor)
			topBordColor: myTopBordColor
			lftBordColor: (VGAOrEGA myLftBordColor myTopBordColor)
			rgtBordColor: (VGAOrEGA myRgtBordColor myBotBordColor)
			botBordColor: myBotBordColor
		)
		(invWin
			color: 0
			back: (VGAOrEGA myBotBordColor myInsideColor)
			topBordColor: (VGAOrEGA myBackColor myTopBordColor)
			lftBordColor: (VGAOrEGA myRgtBordColor myTopBordColor)
			rgtBordColor: (VGAOrEGA myInsideColor myBotBordColor)
			botBordColor: (VGAOrEGA myLowlightColor myBotBordColor)
			insideColor: (VGAOrEGA myInsideColor myBotBordColor)
			topBordColor2: myLowlightColor
			lftBordColor2: myLowlightColor
			botBordColor2: (VGAOrEGA myBackColor myTopBordColor)
			rgtBordColor2: (VGAOrEGA myLftBordColor myTopBordColor)
		)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (VGAOrEGA myBackColor myInsideColor)
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
		)
		(icon5 message: (if (HaveMouse) 3840 else 9))
		(Inventory
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
			eachElementDo: #lowlightColor (VGAOrEGA myInsideColor myBotBordColor)
			eachElementDo: #init
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
		(GameControls
			window: gcWin
			add:
				iconOk
				(detailSlider
					theObj: self
					selector: 291
					topValue: 3
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: 381
					topValue: (if (> numVoices 1) 15 else 1)
					bottomValue: 0
					yourself:
				)
				(speedSlider
					theObj: self
					selector: 378
					topValue: 1
					bottomValue: 15
					yourself:
				)
				(iconSave theObj: self yourself:)
				(iconRestore theObj: self yourself:)
				(iconRestart theObj: self selector: 104 yourself:)
				(iconQuit theObj: self selector: 103 yourself:)
				(iconAbout
					theObj: (ScriptID 811 0)
					selector: 60
					yourself:
				)
				iconHelp
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (VGAOrEGA myRgtBordColor myBotBordColor)
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(buckazoid owner: ego)
		(= startingRoom 290)
		(theIconBar
			enable:
			disable: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5
		)
		(self newRoom: 290)
	)
	
	(method (doit)
		(super doit:)
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
	
	(method (replay)
		(ShowStatus -1)
		(Palette PALIntensity 0 255 100)
		(super replay:)
	)
	
	(method (newRoom)
		(if (== ((inventory at: iCigar) state?) 1)
			(Print 0 6)
			((inventory at: iCigar) state: 0)
		)
		(super newRoom: &rest)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		((ScriptID 801) doit: roomNum)
		(if debugOn (SetDebug))
		(cond 
			(
				(OneOf roomNum
					370 371 375 376 380 381 385 386 387 390 391
					395 397 398 399 400 405 406 410 411
				)
				RegionPath
				(ScriptID MALL)
				(= curRegionPath MALL)
				(if (OneOf roomNum 405 406 410 411) (ScriptID INERTIA))
			)
			((OneOf roomNum 25 30 35 40 45 50 55 60 65)
				RegionPath
				(ScriptID STREET)
				(= curRegionPath STREET)
				(if (OneOf roomNum 25 30 35 40 45 50 55 60 65)
					(ScriptID BUNNY)
				)
			)
			((OneOf roomNum 75 80 85 90 95 100 105 110 115) (ScriptID SEWER))
			((OneOf roomNum 609 610 611 612 613 614 615 620) (ScriptID ULENCE))
			((OneOf roomNum 299 300 305 306 310 315 320 298) (ScriptID BUTTE))
			(
			(OneOf roomNum 150 500 505 510 514 515 520 525 541 544 545) RegionPath (ScriptID BRAIN) (= curRegionPath BRAIN))
			((OneOf roomNum 1 6 9 10 15 16 17 19 20 21) (ScriptID INTRO))
			((OneOf roomNum 530 535 540) (ScriptID LANDING))
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
		(super startRoom: roomNum)
		(if (cast contains: ego)
			(if
				(and
					(ego normal?)
					(not (OneOf roomNum 405 406 410 411))
					(not ((ego cycler?) isKindOf: StopWalk))
				)
				(ego setCycle: StopWalk 4)
			)
			(if (not (ego looper?)) (ego setLoop: stopGroop))
			(EgoHeadMove (egoHead view?))
		)
		(redX init: hide: setPri: 15 posn: 1000 -1000)
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
								(Inventory showSelf: ego)
							)
						)
						(SHIFTTAB
							(if (not (& (icon5 signal?) DISABLED))
								(Inventory showSelf: ego)
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
	
	(method (quitGame)
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(super
			quitGame:
				(Print 0 5
					#button {Do something of redeeming\nsocial value (Quit)} 1
					#button {Changed My Mind.\nLet's Play!} 0
					#icon babbleIcon 0 0
				)
		)
	)
	
	(method (pragmaFail)
		(if (User canInput:) (VerbFail))
	)
)

(instance ok of IconItem
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Select this Icon to close this window.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invLook of IconItem
	(properties
		view 901
		loop 2
		cel 0
		cursor 19
		message verbLook
		helpStr {Select this Icon then select an inventory item you'd like a description of.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invHand of IconItem
	(properties
		view 901
		loop 0
		cel 0
		cursor 20
		message verbDo
		helpStr {This allows you to do something to an item.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invHelp of IconItem
	(properties
		view 901
		loop 1
		cel 0
		cursor 29
		message verbHelp
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invSelect of IconItem
	(properties
		view 901
		loop 4
		cel 0
		cursor ARROW_CURSOR
		helpStr {This allows you to select an item.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance buckazoid of InvItem
	(properties
		view 700
		cursor 1
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Printf 0 7
					buckazoids (if (== buckazoids 1) {.} else {s.})
				)
			)
		)
	)
)

(instance jar of InvItem
	(properties
		view 700
		cel 1
		cursor 15
		signal IMMEDIATE
		description {glass jar}
		owner 70
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if (== cel 2) (Print 0 8) else (Print 0 9))
			)
		)
	)
)

(instance hintbook of InvItem
	(properties
		view 700
		cel 4
		cursor 18
		signal IMMEDIATE
		description {An SQ 4 hintbook.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(cond 
					((curRoom script?) (Print 0 10))
					(
					(and (OneOf curRoomNum 397 398) (not (Btst fBoughtHintbook))) (Print 0 11))
					((not (HaveMem 6800)) (Print 0 12))
					(else
						(Inventory curIcon: (inventory at: iPen) hide:)
						(curRoom setScript: (ScriptID HINTBOOK 0))
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pen of InvItem
	(properties
		view 700
		cel 5
		cursor 8
		signal IMMEDIATE
		description {Yes, it's a Reveal-O-matic electric hint revealer.}
	)
)

(instance atmCard of InvItem
	(properties
		view 700
		cel 6
		cursor 2
		signal IMMEDIATE
		description {An AutoBucks Teller Machine card.}
	)
)

(instance plug of InvItem
	(properties
		view 700
		cel 7
		cursor 5
		signal IMMEDIATE
		description {A PocketPal\05 adaptor plug.}
	)
)

(instance cigar of InvItem
	(properties
		view 700
		cel 8
		cursor 3
		signal IMMEDIATE
		description {An obviously used stogie.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(cond 
					((!= theItem iMatches) 0)
					(state (Print 0 13))
					(else (Print 0 14) (= state 1))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance matches of InvItem
	(properties
		view 700
		cel 9
		cursor 4
		signal IMMEDIATE
		description {A book of matches.}
	)
)

(instance diskette of InvItem
	(properties
		view 700
		cel 10
		cursor 11
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook (Print 0 15))
		)
	)
)

(instance rabbit of InvItem
	(properties
		view 700
		cel 11
		cursor 9
		signal IMMEDIATE
		description {cute bunny}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					((!= view 700)
						(Inventory hide:)
						(self view: 700 loop: 0 cel: 11)
						(Inventory show: ego)
					)
					((not ((inventory at: 4) owner?))
						(Inventory hide:)
						(self view: 701 loop: 0 cel: 0)
						(Inventory show: ego)
					)
					(else (Print 0 16))
				)
			)
			(verbDo
				(if (== view 700)
					0
				else
					(SolvePuzzle fGotBattery 3)
					(Print 0 17)
					(Inventory hide:)
					(ego get: iBattery)
					(self view: 700 loop: 0 cel: 11)
					(Inventory show: ego)
				)
			)
			(verbUse
				(switch theItem
					(iBattery
						(Print 0 18)
						(Inventory hide:)
						(ego put: iBattery 0)
						(Inventory curIcon: 0 show: ego)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance battery of InvItem
	(properties
		view 700
		cel 12
		cursor 13
		signal IMMEDIATE
		description {A battery.}
	)
)

(instance tank of InvItem
	(properties
		view 700
		cel 13
		cursor 12
		signal IMMEDIATE
		description {An oxygen tank.}
		owner 335
		name "oxygen tank"
	)
)

(instance rope of InvItem
	(properties
		view 700
		cel 14
		cursor 10
		signal IMMEDIATE
		description {A crummy piece of rope.}
		owner 65
	)
)

(instance laptop of InvItem
	(properties
		view 700
		cel 15
		cursor 17
		signal IMMEDIATE
		description {A handy Dandy PocketPal portable terminal.}
		owner 55
		name "laptop computer"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					((!= view 700)
						(Inventory hide:)
						(self view: 700 loop: 0 cel: 15)
						(Inventory show: ego)
					)
					((== ((inventory at: iBattery) owner?) 1) (Print 0 19))
					(else
						(Inventory hide:)
						(self view: 701 loop: 0 cel: 1)
						(Inventory show: ego)
					)
				)
			)
			(verbDo
				(if (== ((inventory at: iBattery) owner?) 1)
					(Print 0 20)
					(Inventory hide:)
					(ego get: iBattery)
					(Inventory show: ego)
				else
					(Print 0 21)
				)
			)
			(verbUse
				(switch theItem
					(iBattery
						(SolvePuzzle fBatteryInLaptop 3)
						(Print 0 22)
						(Inventory hide:)
						(self view: 700 loop: 0 cel: 15)
						(ego put: iBattery 1)
						(Inventory curIcon: 0 show: ego)
					)
					(iPlug
						(SolvePuzzle fPlugInLaptop 3)
						(Print 0 23)
						(Inventory hide:)
						(ego put: iPlug 1)
						(Inventory curIcon: 0 show: ego)
					)
				)
			)
		)
	)
)

(instance gum of InvItem
	(properties
		view 700
		loop 2
		cursor 21
		signal IMMEDIATE
		description {It's Paper-wrapped gum - a Coarsegold dining delight.}
		owner 297
		name "paper with gum"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(if cel (Print 0 24) else (Print 0 25))
			)
			(verbDo
				(if (not cel)
					(Inventory hide:)
					(self view: 700 loop: 2 cel: 1)
					(Inventory show: ego)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bomb of InvItem
	(properties
		view 700
		cel 3
		cursor 22
		signal IMMEDIATE
		description {A piece of unstable ordnance.}
		owner 40
	)
)

(instance redX of View
	(properties
		view 942
	)
)

(instance icon0 of IconItem
	(properties
		view 900
		loop 0
		cel 0
		cursor 6
		message verbWalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for walking.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
)

(instance icon1 of IconItem
	(properties
		view 900
		loop 1
		cel 0
		cursor 19
		message verbLook
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for looking.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
)

(instance icon2 of IconItem
	(properties
		view 900
		loop 2
		cel 0
		cursor 20
		message verbDo
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for doing.}
		maskView 900
		maskLoop 14
	)
)

(instance icon3 of IconItem
	(properties
		view 900
		loop 3
		cel 0
		cursor 7
		message verbTalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for talking.}
		maskView 900
		maskLoop 14
		maskCel 3
	)
)

(instance icon4 of IconItem
	(properties
		view 900
		loop 4
		cel 0
		cursor 999
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {This window displays the current inventory item.}
		maskView 900
		maskLoop 14
		maskCel 4
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
		helpStr {This icon brings up the inventory window.}
		maskView 900
		maskLoop 14
		maskCel 2
	)
	
	(method (select)
		(if (super select:) (Inventory showSelf: ego))
	)
)

(instance icon6 of IconItem
	(properties
		view 900
		loop 10
		cel 0
		cursor 30
		message verbSmell
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for smelling.}
		maskView 900
		maskLoop 14
	)
)

(instance icon7 of IconItem
	(properties
		view 900
		loop 11
		cel 0
		cursor 31
		message verbTaste
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for tasting.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
)

(instance icon8 of IconItem
	(properties
		view 900
		loop 7
		cel 0
		cursor 999
		message 8
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the control panel.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
	
	(method (select)
		(if (super select:)
			(theIconBar hide:)
			(GameControls show:)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 900
		loop 9
		cel 0
		cursor 29
		message verbHelp
		signal (| RELVERIFY IMMEDIATE)
		helpStr {This icon tells you about other icons.}
		maskView 900
		maskLoop 14
	)
)

(instance sq4DoVerbCode of Code
	(properties)
	
	(method (doit theVerb theObj &tmp temp0)
		(= temp0 (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj facingMe: ego)
					(if (theObj lookStr?)
						(Print (theObj lookStr?))
					else
						(VerbFail)
					)
				)
			)
			(else  (VerbFail))
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

(instance gcWin of BorderWindow
	(properties)
	
	(method (open &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 theMyBotBordColor theMyTopBordColor temp10 temp11 temp12 i [str 15] [sizeBuf 4])
		(self
			top: (/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
			left: (/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
			bottom:
				(+
					(CelHigh 947 1 1)
					6
					(/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
				)
			right:
				(+
					151
					(CelWide 947 0 1)
					(/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
				)
			priority: 15
		)
		(super open:)
		(DrawCel
			947
			0
			5
			(+
				(/
					(-
						(- (+ 151 (CelWide 947 0 1)) (+ 4 (CelWide 947 1 1)))
						(CelWide 947 0 5)
					)
					2
				)
				4
				(CelWide 947 1 1)
			)
			6
			15
		)
		(DrawCel 947 1 1 4 3 15)
		(DrawCel 947 1 0 94 38 15)
		(DrawCel 947 1 0 135 38 15)
		(DrawCel 947 0 4 63 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 3 101 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 2 146 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(= temp5 (+ (= temp2 (+ 46 (CelHigh 947 0 1))) 13))
		(= temp4
			(+
				(= temp3 (+ 10 (CelWide 947 1 1)))
				(-
					(+ 151 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= temp12 15)
		(= temp6 0)
		(= theMyBotBordColor myBotBordColor)
		(= temp11 (VGAOrEGA myRgtBordColor myBotBordColor))
		(= temp10 (VGAOrEGA myLftBordColor myTopBordColor))
		(= theMyTopBordColor myTopBordColor)
		(= temp1 3)
		(= temp7 3)
		(Graph
			GFillRect
			temp2
			temp3
			(+ temp5 1)
			(+ temp4 1)
			temp7
			temp6
			temp12
		)
		(= temp2 (- temp2 temp1))
		(= temp3 (- temp3 temp1))
		(= temp4 (+ temp4 temp1))
		(= temp5 (+ temp5 temp1))
		(Graph
			GFillRect
			temp2
			temp3
			(+ temp2 temp1)
			temp4
			temp7
			theMyBotBordColor
			temp12
		)
		(Graph
			GFillRect
			(- temp5 temp1)
			temp3
			temp5
			temp4
			temp7
			theMyTopBordColor
			temp12
		)
		(= i 0)
		(while (< i temp1)
			(Graph
				GDrawLine
				(+ temp2 i)
				(+ temp3 i)
				(- temp5 (+ i 1))
				(+ temp3 i)
				temp11
				temp12
				-1
			)
			(Graph
				GDrawLine
				(+ temp2 i)
				(- temp4 (+ i 1))
				(- temp5 (+ i 1))
				(- temp4 (+ i 1))
				temp10
				temp12
				-1
			)
			(++ i)
		)
		(Graph
			GShowBits
			temp2
			temp3
			(+ temp5 1)
			(+ temp4 1)
			1
		)
		(Format @str 0 27 score possibleScore)
		(TextSize @sizeBuf @str 999 0)
		(Display
			@str
			p_font
			999
			p_color
			(VGAOrEGA myLftBordColor myTopBordColor)
			p_at
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[sizeBuf 3]
					)
					2
				)
			)
			(+ 46 (CelHigh 947 0 1) 3)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 67
		nsTop 37
		signal FIXED_POSN
		helpStr {Raises and lowers the level of graphics detail.}
		sliderView 947
		topValue 3
	)
)

(instance volumeSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 107
		nsTop 37
		signal FIXED_POSN
		helpStr {Adjusts sound volume.}
		sliderView 947
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 147
		nsTop 37
		signal FIXED_POSN
		helpStr {Adjusts the speed of the game's animation (within the limits of your computer's capability).}
		sliderView 947
		bottomValue 15
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		message 9
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Saves your current game (currently disabled).}
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 947
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		message 9
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Restores a previously saved game (currently disabled).}
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 947
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		message 9
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Restarts the Game.}
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		message 9
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Exits the game.}
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		nsLeft 8
		nsTop 86
		message 9
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Information about the game.}
	)
)

(instance iconHelp of IconItem
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 70
		message 6
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
	)
)

(instance iconOk of IconItem
	(properties
		view 947
		loop 8
		cel 0
		nsLeft 8
		nsTop 106
		cursor 70
		message 9
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Exits this menu.}
	)
)
