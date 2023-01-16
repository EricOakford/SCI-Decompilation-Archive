;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use ColorInit)
(use RegionPath)
(use SQEgo)
(use PMouse)
(use SlideIcon)
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
	SteppedOn 5
	Btst 6
	Bset 7
	Bclr 8
	InitEgoHead 9
	EgoDead 10
	SolvePuzzle 11
	DoDisplay 12
	Face 13
	DoStatus 14
	SteppedFullyOn 15
	NoResponse 16
	Babble 17
	FindColor 18
)

(local
	ego										;pointer to ego
	theGame									;ID of the Game instance
	curRoom									;ID of current room
	speed				=	6				;number of ticks between animations
	quit									;when TRUE, quit game
	cast									;collection of actors
	regions									;set of current regions
	timers									;list of timers in the game
	sounds									;set of sounds being played
	inventory								;set of inventory items in game
	addToPics								;list of views added to the picture
	curRoomNum								;current room number
	prevRoomNum								;previous room number
	newRoomNum								;number of room to change to
	debugOn									;generic debug flag -- set from debug menu
	score									;the player's current score
	possibleScore							;highest possible score
	showStyle			=	IRISOUT			;style of picture showing
	aniInterval								;# of ticks it took to do the last animation cycle
	theCursor								;the number of the current cursor
	normalCursor		=	ARROW_CURSOR	;number of normal cursor form
	waitCursor			=	HAND_CURSOR		;cursor number of "wait" cursor
	userFont			=	USERFONT		;font to use for Print
	smallFont			=	4		;small font for save/restore, etc.
	lastEvent								;the last event (used by save/restore game)
	modelessDialog							;the modeless Dialog known to User and Intrface
	bigFont				=	USERFONT		;large font
	version				=	0				;pointer to 'incver' version string
											;***WARNING***  Must be set in room 0
											; (usually to {x.yyy    } or {x.yyy.zzz})
	locales									;set of current locales
	curSaveDir								;address of current save drive/directory string
	aniThreshold		=	10
	perspective								;player's viewing angle:
											;	 degrees away from vertical along y axis
	features								;locations that may respond to events
	sortedFeatures							;above+cast sorted by "visibility" to ego
	useSortedFeatures	=	FALSE			;enable cast & feature sorting?
	egoBlindSpot		=	0				;used by sortCopy to exclude 
											;actors behind ego within angle 
											;from straight behind. 
											;Default zero is no blind spot
	overlays			=	-1
	doMotionCue								;a motion cue has occurred - process it
	systemWindow							;ID of standard system window
	demoDialogTime		=	3				;how long Prints stay up in demo mode
	currentPalette							;
	modelessPort		
	sysLogPath								;used for system standard logfile path	
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
	endSysLogPath					;uses 20 globals
	gameControls		
	ftrInitializer		
	doVerbCode			
	firstSaidHandler				;will be the first to handle said events
	useObstacles		=	TRUE	;will Ego use PolyPath or not?
	theMenuBar						;points to TheMenuBar or Null	
	theIconBar						;points to TheIconBar or Null	
	mouseX				
	mouseY				
	keyDownHandler					;our EventHandlers, get called by the game
	mouseDownHandler	
	directionHandler	
	gameCursor			
	lastVolume			
	pMouse				=	NULL	;pointer to a Pseudo-Mouse, or NULL
	theDoits			=	NULL	;list of objects to get doits done every cycle
	eatMice				=	60		;how many ticks minimum that a window stays up	
	user				=	NULL	;pointer to specific applications User
	syncBias						;; globals used by sync.sc (will be removed shortly)
	theSync				
	cDAudio				
	fastCast			
	inputFont			=	SYSFONT	;font used for user type-in

	tickOffset			

	howFast				 ;; measurment of how fast a machine is		
	;globals 88-99 are unused
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
	theMusic
	global101
	curReg
	noCursor =  TRUE
	cursorType
	numColors
	numVoices
	restartRoom
	global108
	global109
	mallRoomVisits
	global111
	eHead
	sGrooper
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
	colBlack
	colWhite
	colDRed
	colLRed
	colVLRed
	colDYellow
	colYellow
	colLYellow
	colLGreen
	colVLGreen
	colDBlue
	colMagenta
	colCyan
	colLED
	colLaser
	myTextColor14
	myTextColor15
	myTextColor16
	myTextColor17
	myTextColor18
	colBlue
	colDGreen
	global151
	colLBlue
	colVLBlue
	theMusic2
	colGray1
	colGray2
	colGray3
	colGray4
	buckazoids =  59
	heldBox
	colGray5
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
	colLCyan
	colLMagenta
	msAstroChickenPlays
	saveCursorX
	saveCursorY
	global194
)
(procedure (NormalEgo theLoop theView sView &tmp stopView)
	(= stopView 0)
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1)
			(ego view: theView)
			(if (> argc 2)
				(= stopView sView)
			)
		)
	)
	(if (not stopView)
		(= stopView 4)
	)
	(ego
		normal: TRUE
		moveHead: TRUE
		setLoop: -1
		setLoop: stopGroop
		setPri: -1
		setMotion: FALSE
		setCycle: StopWalk stopView
		setStep: 3 2
		illegalBits: 0
		ignoreActors: FALSE
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
)

(procedure (HandsOff &tmp saveIcon)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= saveIcon (theIconBar curIcon?))
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
	(theIconBar curIcon: saveIcon)
	(if (not (HaveMouse))
		(= saveCursorX ((User curEvent?) x?))
		(= saveCursorY ((User curEvent?) y?))
		(theGame setCursor: waitCursor TRUE 304 172)
	else
		(theGame setCursor: waitCursor TRUE)
	)
	(if pMouse
		(pMouse stop:)
	)
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
		(theGame setCursor:
			((theIconBar curIcon?) cursor?) TRUE saveCursorX saveCursorY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
)

(procedure (SteppedOn who color)
	(return
		(if (& (who onControl: origin) color)
			(return TRUE)
			else FALSE
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

(procedure (Bset flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(|= [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(&= [gameFlags (/ flagEnum 16)] (~ (>> $8000 (mod flagEnum 16))))
	(return oldState)
)

(procedure (InitEgoHead headView &tmp hView)
	(= hView 0)
	(if argc (= hView headView) else (= hView 4))
	((= eHead egoHead)
		init: ego
		view: hView
		cycleSpeed: 24
	)
)

(procedure (EgoDead view msg)
	(if (> argc 0)
		(= deathView view)
		(if (OneOf (ego view?) 373 374 993)
			(if (== deathView 0)
				(= deathView 7)
			)
			(if (== deathView 8)
				(= deathView 9)
			)
		)
	else
		(= deathView 0)
	)
	(if (> argc 1)
		(= deathMessage msg)
	else
		(= deathMessage 0)
	)
	(curRoom newRoom: EGODEAD)
)

(procedure (SolvePuzzle pVal pFlag)
	(if (not (Btst pFlag))
		(theGame changeScore: pVal)
		(Bset pFlag)
		(pointsSound play:)
	)
)

(procedure (DoDisplay args &tmp
		theMode theForeFont theBackFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 0 2
				 p_restore
				 [args 0]
			)
		else
			(= theX
				(= theY -1)
			)
			(= theMode teJustCenter)
			(= theForeFont 68)
			(= theBackFont 69)
			(= theWidth -1)
			(= theForeColor colWhite)
			(= theBackColor 0)
			(for ((= i 1)) (< i argc) ((++ i))
				(switch [args i]
					(#mode
						(= theMode [args (++ i)])
					)
					(#font
						(= theBackFont (+ (= theForeFont [args (++ i)]) 1))
					)
					(#width
						(= theWidth [args (++ i)])
					)
					(#at
						(= theX [args (++ i)])
						(= theY [args (++ i)])
					)
					(#color
						(= theForeColor [args (++ i)])
					)
					(#back
						(= theBackColor [args (++ i)])
					)
				)
			)
			(= i
				(Display [args 0]
					p_at theX theY
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theBackFont
					p_save
				)
			)
			(Display [args 0]
				p_at theX theY
				p_color theForeColor
				p_width theWidth
				p_mode theMode
				p_font theForeFont
			)
			(return i)
		)
	)
)

(procedure (Face who theObjOrX theY whoCares &tmp theHeading lookX looKY whoToCue)
	(= whoToCue 0)
	(if (IsObject theObjOrX)
		(= lookX (theObjOrX x?))
		(= looKY (theObjOrX y?))
		(if (== argc 3)
			(= whoToCue theY)
		)
	else
		(= lookX theObjOrX)
		(= looKY theY)
		(if (== argc 4)
			(= whoToCue whoCares)
		)
	)
	(= theHeading (GetAngle (who x?) (who y?) lookX looKY))
	(who setHeading: theHeading (if (IsObject whoToCue) whoToCue else 0))
)

(procedure (DoStatus strg &tmp theMyTextColor18 [str 25] [buffer 100] [len 4] temp130)
	(if (!= strg -1)
		(= currentEra strg)
	)
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
			(= theMyTextColor18 myTextColor18)
		)
	)
	(TextSize @len @str 0 -1)
	(StrCpy @buffer {\06})
	(= temp130 (/ (- 326 (- [len 3] [len 1])) 2))
	(while (> temp130 0)
		(StrCat @buffer {\06})
		(-- temp130)
	)
	(StrCat @buffer @str)
	(DrawStatus @buffer 0 (FindColor colGray4 colGray1))
)

(procedure (SteppedFullyOn who color)
	(return
		(if (== (who onControl:) color)
			(return TRUE)
			else FALSE
		)
	)
)

(procedure (NoResponse &tmp list [temp1 2] oldCursor evt theTime [temp6 5])
	(= oldCursor (theGame setCursor: 69 TRUE))
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
	(= theTime (GetTime))
	(while (< (Abs (- theTime (GetTime))) 40)
		(breakif
			(OneOf ((= evt (Event new:)) type?) keyDown mouseDown)
		)
		(evt dispose:)
	)
	(if (IsObject evt) (evt dispose:))
	(redX hide: posn: 1000 -1000)
	(Animate (list elements?) TRUE)
	(list delete: redX dispose:)
	(theGame setCursor: oldCursor)
)

(procedure (Babble theView msgS msgO &tmp [buffer 500])
	(if (u< msgS 1000)
		(GetFarText msgS msgO @buffer)
	else
		(StrCpy @buffer msgS)
	)
	(babbleIcon
		view: theView
		cycleSpeed: (* (+ howFast 1) 4)
	)
	(if (u< msgS 1000)
		(Print @buffer &rest #icon babbleIcon 0 0)
	else
		(Print @buffer msgO &rest #icon babbleIcon 0 0)
	)
)

(procedure (FindColor col256 col16)
	(if (< col256 0)
		(= col256 0)
	)
	(if (> col256 255)
		(= col256 255)
	)
	(if (< col16 0)
		(= col16 0)
	)
	(if (> col16 15)
		(= col16 15)
	)
	(return (if (Btst fIsVGA) col256 else col16))
)

(instance egoObj of SQEgo
	(properties
		name {ego}
		description {Roger Wilco}
		sightAngle 180
		lookStr {It's you. Roger Wilco, space guy.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(Print 0 0)
			)
			(verbDo
				(Print 0 1)
			)
			(verbTaste
				(Print 0 2)
			)
			(verbSmell
				(Print 0 3)
			)
			(verbUse
				(switch theItem
					(iCigar
						(Print 0 4)
					)
					(else
						(NoResponse)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
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
	(method (init)
		((= cycler (RandCycle new:)) init: self 20)
	)
)

(instance sq4KeyDownHandler of EventHandler)

(instance sq4MouseDownHandler of EventHandler)

(instance sq4DirectionHandler of EventHandler)

(instance sq4 of Game
	(method (init &tmp temp0)
		(= systemWindow sq4Win)
		(ColorInit)
		(= sGrooper stopGroop)
		(= useSortedFeatures TRUE)
		(super init:)
		(StrCpy @sysLogPath {})
		(= doVerbCode sq4DoVerbCode)
		(= ftrInitializer sq4FtrInit)
		((= keyDownHandler sq4KeyDownHandler) add:)
		((= mouseDownHandler sq4MouseDownHandler) add:)
		((= directionHandler sq4DirectionHandler) add:)
		(= pMouse PseudoMouse)
		(self
			egoMoveSpeed: 0
			setCursor: theCursor TRUE 304 172)
		((= ego egoObj)
			_head: (= eHead egoHead)
			moveSpeed: (self egoMoveSpeed?)
			cycleSpeed: (self egoMoveSpeed?)
		)
		((ego _head?)
			client: ego
		)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: FALSE
			canInput: FALSE
		)
		((= theMusic longSong)
			owner: self
			init:
		)
		((= theMusic2 longSong2)
			owner: self
			init:
		)
		(= waitCursor 997)
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
			back: (FindColor colGray4 colGray1)
			topBordColor: colWhite
			lftBordColor: (FindColor colGray5 colWhite)
			rgtBordColor: (FindColor colGray3 colGray2)
			botBordColor: colGray2
		)
		(gcWin
			color: 0
			back: (FindColor colGray4 colGray1)
			topBordColor: colWhite
			lftBordColor: (FindColor colGray5 colWhite)
			rgtBordColor: (FindColor colGray3 colGray2)
			botBordColor: colGray2
		)
		(invWin
			color: 0
			back: (FindColor colGray2 colGray1)
			topBordColor: (FindColor colGray4 colWhite)
			lftBordColor: (FindColor colGray3 colWhite)
			rgtBordColor: (FindColor colGray1 colGray2)
			botBordColor: (FindColor colBlack colGray2)
			insideColor: (FindColor colGray1 colGray2)
			topBordColor2: colBlack
			lftBordColor2: colBlack
			botBordColor2: (FindColor colGray4 colWhite)
			rgtBordColor2: (FindColor colGray5 colWhite)
		)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (FindColor colGray4 colGray1)
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
		)
		(icon5 message: (if (HaveMouse) SHIFTTAB else TAB))
		(Inventory
			init:
			add:
				buckazoid
				rope
				bomb
				rabbit
				battery
				jar
				paper_with_gum
				oxygen_tank
				hintbook
				pen
				atmCard
				plug
				cigar
				matches
				diskette
				laptop_computer
				invLook
				invHand
				invSelect
				invHelp
				ok
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (FindColor colGray1 colGray2)
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
					selector: #detailLevel
					topValue: 3
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: #masterVolume
					topValue: (if (> numVoices 1) 15 else 1)
					bottomValue: 0
					yourself:
				)
				(speedSlider
					theObj: self
					selector: #setSpeed
					topValue: 1
					bottomValue: 15
					yourself:
				)
				(iconSave
					theObj: self
					yourself:
				)
				(iconRestore
					theObj: self
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
				iconHelp
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (FindColor colGray3 colGray2)
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(buckazoid owner: ego)
		(= restartRoom 290)
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
					803 1 6 9 10 15 16 17 19 20 21
					59 119 120 150 321 329 330 335
					340 345 350 355 371 376 398 500
					505 510 514 515 520 525 531 541
					615
				)
			)
			((ScriptID NOSEPICK 0) doit:)
		)
	)
	
	(method (replay)
		(DoStatus -1)
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
		(if pMouse
			(pMouse stop:)
		)
		((ScriptID DISPOSE) doit: roomNum)
		(if debugOn
			(SetDebug)
		)
		(cond 
			(
				(OneOf roomNum
					370 371 375 376 380 381 385 386
					387 390 391 395 397 398 399 400
					405 406 410 411
				)
				RegionPath
				(ScriptID MALL)
				(= curReg MALL)
				(if (OneOf roomNum 405 406 410 411)
					(ScriptID INERTIA)
				)
			)
			((OneOf roomNum 25 30 35 40 45 50 55 60 65)
				RegionPath
				(ScriptID STREET)
				(= curReg STREET)
				(if (OneOf roomNum 25 30 35 40 45 50 55 60 65)
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
			(
				(OneOf roomNum
					150 500 505 510 514 515
					520 525 541 544 545
				)
				RegionPath
				(ScriptID BRAIN)
				(= curReg BRAIN)
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
					803 1 6 9 10 15 16 17 19 20 21 59
					119 120 150 321 329 330 335 340 345
					350 355 371 376 398 500 505 510 514
					515 520 525 531 541 615
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
			(if (not (ego looper?))
				(ego setLoop: stopGroop)
			)
			(InitEgoHead (egoHead view?))
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
		(super quitGame:
			(Print 0 5
				#button {Do something of redeeming\nsocial value (Quit)} 1
				#button {Changed My Mind.\nLet's Play!} 0
				#icon babbleIcon 0 0
			)
		)
	)
	
	(method (pragmaFail)
		(if (User canInput:)
			(NoResponse)
		)
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
			lowlightColor: (FindColor colGray4 colGray1)
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
			lowlightColor: (FindColor colGray4 colGray1)
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
			lowlightColor: (FindColor colGray4 colGray1)
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
			lowlightColor: (FindColor colGray4 colGray1)
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
			lowlightColor: (FindColor colGray4 colGray1)
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
					buckazoids
					(if (== buckazoids 1) {.} else {s.})
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
				(if (== cel 2)
					(Print 0 8)
				else
					(Print 0 9)
				)
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
					((curRoom script?)
						(Print 0 10)
					)
					((and (OneOf curRoomNum 397 398) (not (Btst fBoughtHintbook)))
						(Print 0 11)
					)
					((not (HaveMem HintSize))
						(Print 0 12)
					)
					(else
						(Inventory curIcon: (inventory at: iPen) hide:)
						(curRoom setScript: (ScriptID HINTBOOK 0))
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
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
					(state
						(Print 0 13)
					)
					(else
						(Print 0 14)
						(= state 1)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
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
			(verbLook
				(Print 0 15)
			)
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
					((not ((inventory at: iBattery) owner?))
						(Inventory hide:)
						(self view: 701 loop: 0 cel: 0)
						(Inventory show: ego)
					)
					(else
						(Print 0 16)
					)
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
			(else
				(super doVerb: theVerb)
			)
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

(instance oxygen_tank of InvItem
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

(instance laptop_computer of InvItem
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
					((== ((inventory at: iBattery) owner?) 1)
						(Print 0 19)
					)
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

(instance paper_with_gum of InvItem
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
				(if cel
					(Print 0 24)
				else
					(Print 0 25)
				)
			)
			(verbDo
				(if (not cel)
					(Inventory hide:)
					(self view: 700 loop: 2 cel: 1)
					(Inventory show: ego)
				)
			)
			(else
				(super doVerb: theVerb)
			)
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
		cursor ARROW_CURSOR
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
		cursor ARROW_CURSOR
		type nullEvt
		message verbNone
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the inventory window.}
		maskView 900
		maskLoop 14
		maskCel 2
	)
	
	(method (select)
		(if (super select:)
			(Inventory showSelf: ego)
		)
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
		cursor ARROW_CURSOR
		message ICON_CONTROL
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
	(method (doit theVerb theObj &tmp objDesc)
		(= objDesc (theObj description?))
		(switch theVerb
			(2
				(if (theObj facingMe: ego)
					(if (theObj lookStr?)
						(Print (theObj lookStr?))
					else
						(NoResponse)
					)
				)
			)
			(else
				(NoResponse)
			)
		)
	)
)

(instance sq4FtrInit of Code
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 90)
		)
		(if (== (theObj actions?) ftrDefault)
			(theObj actions: 0)
		)
	)
)

(instance sq4Win of BorderWindow)

(instance invWin of InsetWindow)

(instance gcWin of BorderWindow
	(method (open &tmp
			temp0 theBevelWid t l r b theColor theMaps bottomColor topColor leftColor rightColor
			thePri i [str 15] [len 4])
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
		(DrawCel 947 0 5
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
		(= b (+ (= t (+ 46 (CelHigh 947 0 1))) 13))
		(= r
			(+
				(= l (+ 10 (CelWide 947 1 1)))
				(-
					(+ 151 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= thePri 15)
		(= theColor 0)
		(= bottomColor colGray2)
		(= rightColor (FindColor colGray3 colGray2))
		(= leftColor (FindColor colGray5 colWhite))
		(= topColor colWhite)
		(= theBevelWid 3)
		(= theMaps 3)
		(Graph GFillRect t l (+ b 1) (+ r 1) theMaps theColor thePri)
		(-= t theBevelWid)
		(-= l theBevelWid)
		(+= r theBevelWid)
		(+= b theBevelWid)
		(Graph GFillRect t l (+ t theBevelWid) r theMaps bottomColor thePri)
		(Graph GFillRect (- b theBevelWid) l b r theMaps topColor thePri)
		(for ((= i 0)) (< i theBevelWid) ((++ i))
			(Graph GDrawLine (+ t i) (+ l i) (- b (+ i 1)) (+ l i) rightColor thePri -1)
			(Graph GDrawLine (+ t i) (- r (+ i 1)) (- b (+ i 1)) (- r (+ i 1)) leftColor thePri -1)
		)
		(Graph GShowBits t l (+ b 1) (+ r 1) 1)
		(Format @str 0 27 score possibleScore)
		(TextSize @len @str 999 0)
		(Display @str
			p_font 999
			p_color (FindColor colGray5 colWhite)
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
						[len 3]
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
		message verbHelp
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
