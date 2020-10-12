;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use LLRoom)
(use Larry)
(use Patti)
(use Intrface)
(use PMouse)
(use GControl)
(use IconBar)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use DCIcon)
(use Timer)
(use Grooper)
(use Window)
(use Sound)
(use Motion)
(use File)
(use Game)
(use Invent)
(use User)
(use System)

(public
	LSL5 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	IsObjectOnControl 5
	Btst 6
	Bset 7
	Bclr 8
	CheckTapeState 9
	SolvePuzzle 10
	Face 11
	WriteDialog 12
	SetFFRoom 13
	TimePrint 14
	MouseClaimed 15
	DisableIcons 16
	StartTimer 17
	Say 18
	gcWin 20
	ll5Win 21
	InFirstPerson 22
	SaveTheCursor 23
	RestoreTheCursor 24
	DoDisplay 25
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
	smallFont =  1
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
	theStopGroop
	dongle =  1234
	theMusic
	theMusic2
	global104
	numColors
	numVoices
	transferRoom =  100
	boardwalkRoom
	oldIconX
	oldIconY
	debugging
	intPhone
	global113
	global114
	global115
	global116
	global117
	global118
	global119
	global120
	global121
	myShadowColor
	global123
	global124
	global125
	global126
	global127
	myDisplayColor
	global129
	gLowlightColor_4
	global131
	global132
	gLowlightColor_6
	gLowlightColor_5
	global135
	HDCdisplayColor
	gLowlightColor_3
	global138
	myInsideColor
	global140
	myHighlightColor
	global142
	myBordColor
	myLowlightColor
	myLowlightColor2
	global146
	myBackColor
	global148
	global149
	batteryStrength
	FFRoom
	bucks
	silverDollars
	gGCastFirst
	textSpeed
	larryDreamNum
	global157
	FFScript
	global159
	currentCity
	global161
	pattiDreamNum
	pokerJackpot
	gBlondeX
	gBlondeLoop
	gRedHeadX
	gRedHeadLoop
	numQuarters
	disabledIcons
	globalTimer
	KRAPCombination
	gameState
	resumeFont
	giantFont
	giantFont2
	limoDestination
	seatNum
	global178
	playingAsLarry
	talkersOnScreen
	buildDate
	usPhone
	cameraTimer
	cameraSeconds
	oldCurIcon
	gameFlags
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
		global198
		global199
)
(procedure (NormalEgo theEgo)
	(switch theEgo
		(LARRY
			(= ego Larry)
			(= playingAsLarry TRUE)
			(User alterEgo: ego)
			(ego setUpInv:)
		)
		(PATTI
			(= ego Patti)
			(= playingAsLarry FALSE)
			(User alterEgo: ego)
			(ego setUpInv:)
		)
	)
)

(procedure (HandsOff)
	(SaveTheCursor)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= disabledIcons 0)
	(theIconBar eachElementDo: #perform checkIcon)
	(theIconBar disable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ZIPPER
		ICON_ITEM
		ICON_INVENTORY	
	)
	(if (not (HaveMouse))
		(= oldIconX mouseX)
		(= oldIconY mouseY)
		(theGame setCursor: waitCursor TRUE 310 185)
	else
		(theGame setCursor: waitCursor TRUE)
	)
)

(procedure (HandsOn)
	(User canControl: 1 canInput: 1)
	(theIconBar enable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ZIPPER
		ICON_ITEM
		ICON_INVENTORY	
		ICON_CONTROL
	)
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: ICON_ITEM)
	)
	(RestoreTheCursor)
	(if (not (HaveMouse))
		(theGame
			setCursor: ((theIconBar curIcon?) cursor?) TRUE oldIconX oldIconY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
	)
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
)

(procedure (IsObjectOnControl obj control)
	(return
		(if (& (obj onControl: origin) control)
			(return TRUE)
		else
			FALSE
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

(procedure (CheckTapeState theState theItem &tmp i)
	(if (== theState tapeERASED)
		((Inventory at: theItem)
			state: theState
			name: {An Erased Videotape}
		)
	else
		(= i (mod ((Inventory at: iCamcorder) state?) 100))
		((Inventory at: i)
			state: theState
			name:
				(switch theState
					(tapeMICHELLE
						{Michelle Milken}
					)
					(tapeLANA
						{Lana Luscious}
					)
					(tapeCHICHI
						{Chi Chi Lambada})
					
				)
		)
	)
)

(procedure (SolvePuzzle points flagEnum)
	(if (and (> argc 1)
			(Bset flagEnum))
			(= points 0)
		)
	(if points
		(theGame changeScore: points)
		(if (> points 0)
			(pointsSound play:)
		)
	)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj)
	(= obj 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3)
			(= obj both)
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= obj whoToCue))
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject obj) obj else 0)
	)
)

(procedure (WriteDialog theVerb theItem &tmp [str1 40] [nameBuf 10] [verbBuf 10])
	(StrCpy
		@verbBuf
		(switch theVerb
			(verbWalk
				{Walk}
			)
			(verbLook
				{Look}
			)
			(verbDo
				{Do}
			)
			(verbTalk
				{Talk}
			)
			(verbZipper
				{Zipper}
			)
			(else
				{UNKNOWN VERB}
			)
		)
	)
	(Format @nameBuf 0 23 curRoomNum)
	(Format @str1 0 24
		(theVerb name?)
		@verbBuf
		((User curEvent?) x?)
		((User curEvent?) y?)
	)
	(File
		name: @nameBuf
		writeString: @str1 {\0D\n}
		close:
	)
)

(procedure (SetFFRoom roomNum scriptObj)
	(if (not roomNum)
		(= FFRoom 0)
		(= FFScript 0)
		(theIconBar disable: ICON_SKIP)
	else
		(= FFRoom roomNum)
		(if (and (> argc 1) (== roomNum 1000))
			(= FFScript scriptObj)
		)
		(theIconBar enable: ICON_SKIP)
	)
)

(procedure (TimePrint theString moreStuff &tmp [str 300] numSeconds oldX oldY)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(if (not (HaveMouse))
		(= oldX mouseX)
		(= oldY (- mouseY 10))
		(theGame setCursor: theCursor TRUE 500 500)
	)
	(if (u< theString 1000)
		(GetFarText theString moreStuff @str)
		(= numSeconds
			(Max 3 (/ (* textSpeed (StrLen @str)) 120))
		)
		(Print @str
			#time numSeconds &rest
		)
	else
		(= numSeconds
			(Max 3 (/ (* textSpeed (StrLen theString)) 120))
		)
		(Print theString
			#time numSeconds
			moreStuff &rest
		)
	)
	(if (not (HaveMouse))
		(theGame setCursor: theCursor TRUE oldX oldY)
	)
	(return TRUE)
)

(procedure (MouseClaimed obj eMsg &tmp event)
	(if (< argc 2)
		(= eMsg mouseDown)
	)
	(= event
		((Event new:)
			type: userEvent
			message: eMsg
			x: (obj x?)
			y: (obj y?)
		)
	)
	(obj handleEvent: event)
	(event dispose:)
)

(procedure (DisableIcons &tmp i)
	(HandsOn)
	(= i 0)
	(while (< i 8)
		(if (& disabledIcons (>> $8000 i))
			(theIconBar disable: i)
		)
		(++ i)
	)
)

(procedure (StartTimer theTime theKind theTimer param4)
	(if (or (< argc 3) (== theTimer 0))
		(= theTimer globalTimer)
	)
	(if (> argc 3)
		(globalTimer code: param4)
	)
	(cond 
		((== theKind 1)
			(globalTimer setCycle: theTimer theTime)
		)
		((== theKind 0)
			(globalTimer set: theTimer theTime)
		)
		(else
			(globalTimer setReal: theTimer theTime)
		)
	)
)

(procedure (Say whom theString moreStuff &tmp dWD whoCares args [str 200])
	(cond 
		((u< theString 1000)
			(GetFarText theString moreStuff @str)
			(= args 2)
		)
		(theString
			(StrCpy @str theString) (= args 1)
		)
		(else
			(= str 0)
			(= args 0)
		)
	)
	(= dWD (= whoCares 0))
	(if (== whom ego)
		(if (> args 1)
			(TimePrint @str #title {You} &rest)
		else
			(TimePrint @str #title {You} moreStuff &rest)
		)
	else
		(= args args)
		(while (< args argc)
			(switch [theString args]
				(108
					(= dWD 1)
				)
				(139
					(= whoCares [theString (++ args)])
				)
			)
			(++ args)
		)
		(if (whom underBits?)
			(whom say: @str 0 0 dWD whoCares)
		else
			(whom init: say: @str 0 0 dWD whoCares)
		)
	)
)

(procedure (InFirstPerson yes)
	(if yes
		(icon0 loop: 15 cursor: 6)
	else
		(icon0 loop: 0 cursor: 0)
	)
	(if (== (theIconBar curIcon?) icon0)
		(theGame setCursor: (icon0 cursor?))
	)
)

(procedure (SaveTheCursor)
	(if (not oldCurIcon)
		(= oldCurIcon (theIconBar curIcon?))
	)
)

(procedure (RestoreTheCursor)
	(if oldCurIcon
		(theIconBar curIcon: oldCurIcon)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(= oldCurIcon 0)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: ICON_ITEM))
				(not (theIconBar curInvIcon?))
			)
			(theIconBar advanceCurIcon:)
		)
	)
)

(procedure (DoDisplay theY theColor &tmp savePort)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(= savePort (GetPort))
	(SetPort 0)
	(Display &rest
		101 teJustCenter
		p_color theColor
		p_font
		giantFont2
		p_width 318
		p_at 1 (- 89 (/ (* 14 theY) 2))
	)
	(SetPort savePort)
)

(instance longSong of Sound)

(instance longSong2 of Sound)

(instance hotSound of Sound)

(class WrapMusic of Object
	(properties
		firstSound 0
		lastSound 0
	)
	
	(method (init)
		(super init:)
		(theMusic number: firstSound setLoop: 1 play: self)
	)
	
	(method (cue &tmp soundNum soundVol)
		(if (== (theMusic prevSignal?) -1)
			(= soundNum (theMusic number?))
			(= soundVol (theMusic vol?))
			(if (> (++ soundNum) lastSound)
				(= soundNum firstSound)
			)
			(theMusic
				number: soundNum
				play: soundVol self
			)
		)
	)
)

(class ll5Timer of Timer
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

(instance camcorderTimer of Timer
	(properties
		seconds 0
	)
	
	(method (doit)
		(if (>= ((Inventory at: iCamcorder) state?) 100) (super doit:))
	)
	
	(method (cue &tmp obj)
		(TimePrint 0 0)
		(= obj (Inventory at: iCamcorder))
		(obj state: (- (obj state?) 100))
	)
)

(class Actions of Code
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance stopGroop of GradualLooper)

(instance quitIcon of DCIcon
	
	(method (init)
		((= cycler (Forward new:)) init: self)
	)
)

(instance ll5KDHandler of EventHandler)

(instance ll5MDHandler of EventHandler)

(instance ll5DirHandler of EventHandler)

(instance LSL5 of Game
	
	(method (init &tmp [temp0 6] temp6 versionFile)
		(= theStopGroop stopGroop)
		StopWalk
		Timer
		Polygon
		PolyPath
		LLRoom
		IconBar
		Inventory
		(ScriptID SIGHT)
		(super init:)
		((ScriptID LL5INIT 0) init:)
		(UnLoad SCRIPT LL5INIT)
		(= doVerbCode ll5DoVerbCode)
		(= ftrInitializer ll5FtrInit)
		((= keyDownHandler ll5KDHandler) add:)
		((= mouseDownHandler ll5MDHandler) add:)
		((= directionHandler ll5DirHandler) add:)
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
		(= globalTimer ll5Timer)
		(= cameraTimer camcorderTimer)
		(= version {x.yyy.zzz})
		(= buildDate {mm/dd/yy})
		(= usPhone {991-999-9999})
		(= intPhone {992-999-9999})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileFGets buildDate 20 versionFile)
		(FileIO fileFGets usPhone 20 versionFile)
		(FileIO fileFGets intPhone 20 versionFile)
		(FileIO fileClose versionFile)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon4 icon5 icon6 icon7 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			curIcon: icon0
			useIconItem: icon6
			helpIconItem: icon9
			disable: ICON_ITEM
			disable:
			state: (| OPENIFONME NOCLICKHELP)
		)
		(icon7 message: (if (HaveMouse) SHIFTTAB else TAB))
		(GameControls
			window: gcWin
			add:
				iconOk
				detailSlider
				(volumeSlider theObj: self selector: #masterVolume yourself:)
				(speedSlider theObj: self selector: #setSpeed yourself:)
				textSlider
				(iconSave theObj: self selector: #save yourself:)
				(iconRestore theObj: self selector: #restore yourself:)
				(iconRestart theObj: self selector: #restart yourself:)
				(iconQuit theObj: self selector: #quitGame yourself:)
				(iconAbout
					theObj: (ScriptID ABOUT 0)
					selector: #doit
					yourself:
				)
				iconHelp
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor gLowlightColor_5
			helpIconItem: iconHelp
			curIcon: iconSave
			state: NOCLICKHELP
		)
		(= temp6 23)
		(SetFFRoom 0)
		(if (GameIsRestarting)
			(MemorySegment 1 @transferRoom)
		)
		(= ego Larry)
		(= playingAsLarry TRUE)
		(User
			alterEgo: ego
			verbMessager: NULL
			canControl: FALSE
			canInput: FALSE
		)
		(Inventory release:)
		(ego setUpInv:)
		(self newRoom: transferRoom)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			;if camcorder charger is plugged into a wall socket, charge the battery
			((== ((Inventory at: iCamcorder) owner?) (Inventory at: iCharger))
				(= batteryStrength (Min 2000 (= batteryStrength (+ batteryStrength 3))))
			)
			((>= ((Inventory at: iCamcorder) state?) 100)
				(= batteryStrength (Max 0 (-- batteryStrength)))
			)
		)
	)
	
	(method (newRoom)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(= cameraSeconds 0)
		(if (cameraTimer client?)
			(= cameraSeconds (cameraTimer seconds?))
			(cameraTimer client: 0)
		)
		(if (and (IsObject fastCast) (fastCast elements?))
			(fastCast eachElementDo: #dispose 1)
		)
		(theIconBar disable:)
		(if (> FFRoom 1000)
			(switch FFRoom
				(1155
					(Bset fAfterCoffee)
				)
				(1480
					(Bset fSkippedEnding)
				)
			)
			(= FFRoom (- FFRoom 1000))
			(super newRoom: &rest)
		else
			(super newRoom: &rest)
		)
	)
	
	(method (startRoom roomNum)
		((ScriptID DISPOSE) doit: roomNum)
		(if (OneOf FFRoom roomNum 1000) (SetFFRoom 0))
		(cond 
			((== roomNum 200)
				(Inventory release:)
				(ego setUpInv:)
			)
			(
				(OneOf roomNum
					160 170 180 190 100 110 120 130 140 150
					155 250 258 260 270 280 290 295 310 320
					385 390 205 500 510 520 525 530 535 700
					710 720 730 740 750 760 780 790 900 905
					910 915 920
				)
				(if (not playingAsLarry)
					(DisposeScript PATTI)
					(Inventory release:)
					(theIconBar curInvIcon: 0)
					(theIconBar curIcon: (theIconBar at: ICON_WALK))
					(NormalEgo LARRY)
				)
			)
			(playingAsLarry
				(DisposeScript LARRY)
				(Inventory release:)
				(theIconBar curInvIcon: 0)
				(theIconBar curIcon: (theIconBar at: ICON_WALK))
				(NormalEgo PATTI)
			)
		)
		(if (OneOf roomNum 160 170 180 190)
			(ScriptID rgHollywood)
		)
		(if (and debugging (not (OneOf roomNum 200 460)))
			((ScriptID DEBUG 0) init:)
			(= debugging FALSE)
		)
		(theIconBar enable:)
		(super startRoom: roomNum)
		(if cameraSeconds
			(cameraTimer
				setReal: cameraTimer cameraSeconds
			)
		)
		(if
			(and
				(ego cycler?)
				(not (ego looper?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego setLoop: stopGroop)
		)
	)
	
	(method (restart)
		(curRoom style: IRISIN drawPic: 1)
		(cast eachElementDo: #hide)
		(MemorySegment 0 @transferRoom 2)
		(super restart:)
	)
	
	(method (handleEvent event)
		(if (== (event type?) mouseUp)
			(mouseDownHandler handleEvent: event)
		else
			(super handleEvent: event)
		)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(if
						(and
							(not (OneOf curRoomNum 200 460 700 760 660 390 535 320))
							(DoSound NumDACs)
						)
						(switch (event message?)
							(`#1
								(hotSound number: 482 play:)
							)
							(`#3
								(hotSound number: 483 play:)
							)
							(`#4
								(hotSound number: 484 play:)
							)
							(`#6
								(hotSound number: 851 play:)
							)
							(`#8
								(hotSound number: 526 play:)
							)
							(`#9
								(hotSound number: 161 play:)
							)
							(`#a	;F10
								(hotSound number: 892 play:)
							)
						)
					)
					(switch (event message?)
						(TAB
							(if (not (& (icon7 signal?) DISABLED))
								(ego showInv:)
							)
						)
						(SHIFTTAB
							(if (not (& (icon7 signal?) DISABLED))
								(ego showInv:)
							)
						)
						(`^q
							(theGame quitGame:)
							(event claimed: TRUE)
						)
						(`^c
							(theIconBar hide:)
							(GameControls show:)
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
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(theGame save:)
								(event claimed: TRUE)
							)
						)
						(`#7
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(theGame restore:)
								(event claimed: TRUE)
							)
						)
						(`+
							(if (User controls?)
								(theGame setSpeed: (Max 0 (- (theGame egoMoveSpeed?) 1)))
							)
						)
						(`-
							(if (User controls?)
								(theGame setSpeed: (+ (theGame egoMoveSpeed?) 1))
							)
						)
						(`=
							(if (User controls?)
								(theGame setSpeed: 6)
							)
						)
					)
				)
			)
		)
	)
	
	(method (setSpeed theSpeed)
		(if argc
			(ego cycleSpeed: theSpeed moveSpeed: theSpeed)
			(self egoMoveSpeed: theSpeed)
		)
		(ego moveSpeed?)
	)
	
	(method (quitGame &tmp [str 10] [jackpotBuf 8] memFile)
		(quitIcon view: 992 loop: 1 cycleSpeed: 9)
		(if
			(Print 0 21
				#title {So You're Finally Leaving?}
				#button {Okay, you two. Have fun!} 1
				#button {Hey! Back to work!} 0
				#icon quitIcon 0 0
			)
			(if
				((= memFile (File new:))
					name: {MEMORY.DRV}
					open: 1
				)
				(memFile readString: @str 20 close:)
			)
			(if (memFile open: 2)
				(Format @jackpotBuf 0 22 pokerJackpot)
				(memFile
					writeString: @str
					writeString: {\n}
					writeString: @jackpotBuf
					close:
				)
			)
			(memFile dispose:)
			(super quitGame: TRUE)
		)
	)
	
	(method (pragmaFail &tmp evt index [str 30])
		(if (and modelessDialog (not talkersOnScreen))
			(modelessDialog dispose:)
			(return TRUE)
		)
		(return
			(if (User canInput:)
				(switch (= evt ((User curEvent?) message?))
					(verbLook
						(TimePrint 0 1)
					)
					(verbTalk
						(TimePrint 0 2)
					)
					(verbDo
						(TimePrint 0 3)
					)
					(verbZipper
						(cond 
							((curRoom script?)
								(TimePrint 0 4)
							)
							((and (!= (ego view?) 550) (!= (ego view?) 570))
								(TimePrint 0 4))
							(playingAsLarry
								(TimePrint 0 5)
							)
							(else
								(TimePrint 0 6)
							)
						)
					)
					(verbUse
						(= index (Inventory indexOf: (theIconBar curInvIcon?)))
						(if playingAsLarry
							(switch index
								(iCamcorder
									(TimePrint 0 7)
									(TimePrint 0 8 67 -1 185)
								)
								(iNapkin
									(TimePrint 0 9)
								)
								(iGoldCard
									(TimePrint 0 10)
								)
								(iChange
									(TimePrint 0 11)
								)
								(iMoney
									(TimePrint 0 12)
								)
								(iMatchbook
									(TimePrint 0 13)
								)
								(iDoily
									(TimePrint 0 14)
								)
								(else 
									(Format @str 0 15 ((Inventory at: index) description?))
									(TimePrint @str)
								)
							)
						else
							(switch index
								(iBiazFax
									(TimePrint 0 16)
									(TimePrint 0 17 67 -1 185)
								)
								(iChampagne
									(TimePrint 0 18)
								)
								(iGoldRecord
									(TimePrint 0 19)
								)
								(iHammerFax
									(TimePrint 0 16)
									(TimePrint 0 17 67 -1 185)
								)
								(iHooterShooter
									(TimePrint 0 20)
								)
								(else 
									(Format @str 0 15 ((Inventory at: index) description?))
									(TimePrint @str)
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

(instance pointsSound of Sound
	(properties
		flags mNOPAUSE
		number 10
	)
)

(instance icon0 of IconItem
	(properties
		view 990
		loop 0
		cel 0
		cursor 0
		message verbWalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to move your character.}
		maskView 990
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
	
	(method (select &tmp evt)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(if (== cursor 6)
					((= evt (Event new:)) type: mouseDown message: verbWalk)
					(if (not (mouseDownHandler handleEvent: evt))
						(regions handleEvent: evt)
					)
					(evt dispose:)
				)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon1 of IconItem
	(properties
		view 990
		loop 1
		cel 0
		cursor 1
		message 2
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to look at things.}
		maskView 990
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(= lowlightColor myLowlightColor2)
		(super init:)
	)
)

(instance icon2 of IconItem
	(properties
		view 990
		loop 2
		cel 0
		cursor 2
		message verbDo
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to do things.}
		maskView 990
		maskLoop 14
	)
	
	(method (init)
		(= lowlightColor gLowlightColor_3)
		(super init:)
	)
)

(instance icon3 of IconItem
	(properties
		view 990
		loop 3
		cel 0
		cursor 3
		message verbTalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to talk to other characters.}
		maskView 990
		maskLoop 14
		maskCel 3
	)
	
	(method (init)
		(= lowlightColor gLowlightColor_4)
		(super init:)
	)
)

(instance icon4 of IconItem
	(properties
		view 990
		loop 10
		cel 0
		cursor 5
		message verbZipper
		signal (| HIDEBAR RELVERIFY)
		helpStr {Say! Don't you know what your zipper is for?}
		maskView 990
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(= lowlightColor gLowlightColor_5)
		(super init:)
	)
)

(instance icon5 of IconItem
	(properties
		view 990
		loop 11
		cel 0
		cursor 999
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon lets you "Fast Forward" to the next interactive portion of the game.}
		maskView 990
		maskLoop 14
	)
	
	(method (init)
		(= lowlightColor myLowlightColor2)
		(super init:)
	)
	
	(method (select)
		(return
			(if (and FFRoom (super select: &rest))
				(theIconBar hide:)
				(if
					(Print 0 25
						#title {Fast Forward}
						#button {Yes} 1
						#button {Oops} 0
					)
					(if (== FFRoom 1000)
						(if (IsObject FFScript)
							(FFScript cue:)
							(SetFFRoom 0)
						else
							(Print 0 26)
						)
					else
						(curRoom newRoom: FFRoom)
						(= FFRoom (+ FFRoom 1000))
					)
				)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon6 of IconItem
	(properties
		view 990
		loop 4
		cel 0
		cursor 999
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {Select this icon to use your current inventory object.}
		maskView 990
		maskLoop 14
		maskCel 4
	)
	
	(method (init)
		(= lowlightColor gLowlightColor_3)
		(super init:)
	)
	
	(method (select relVer &tmp evt whichCel obj theX theY)
		(return
			(cond 
				((& signal DISABLED) FALSE)
				((and argc relVer (& signal RELVERIFY))
					(if
					(= obj (theIconBar curInvIcon?))
						(= theX
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide (obj view?) (+ (obj loop?) 1) (obj cel?))
									)
									2
								)
								nsLeft
							)
						)
						(= theY
							(+
								(theIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh (obj view?) (+ (obj loop?) 1) (obj cel?))
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if (= obj (theIconBar curInvIcon?))
						(DrawCel (obj view?) (+ 1 (obj loop?)) (obj cel?) theX theY -1)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= evt (Event new:)) type?) mouseUp)
						(evt localize:)
						(cond 
							((self onMe: evt)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if (= obj (theIconBar curInvIcon?))
										(DrawCel (obj view?) (+ 1 (obj loop?)) (obj cel?) theX theY -1)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if (= obj (theIconBar curInvIcon?))
									(DrawCel (obj view?) (+ 1 (obj loop?)) (obj cel?) theX theY -1)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						)
						(evt dispose:)
					)
					(evt dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if (= obj (theIconBar curInvIcon?))
							(DrawCel (obj view?) (+ 1 (obj loop?)) (obj cel?) theX theY -1)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					)
					whichCel
				)
				(else TRUE)
			)
		)
	)
)

(instance icon7 of IconItem
	(properties
		view 990
		loop 5
		cel 0
		cursor ARROW_CURSOR
		type nullEvt
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Use this icon to bring up your inventory window.}
		maskView 990
		maskLoop 14
		maskCel 2
	)
	
	(method (init)
		(= lowlightColor gLowlightColor_6)
		(super init:)
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			(ego showInv:)
		)
	)
)

(instance icon8 of IconItem
	(properties
		view 990
		loop 7
		cel 0
		cursor 999
		message 8
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the control panel.}
		maskView 990
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			(GameControls show:)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 990
		loop 9
		cel 0
		cursor 9
		message verbHelp
		signal (| RELVERIFY IMMEDIATE)
		helpStr {To learn about the other icons, first click here, then pass the question mark over the other icons.}
		maskView 990
		maskLoop 14
	)
	
	(method (init)
		(= lowlightColor gLowlightColor_4)
		(super init:)
	)
)

(instance checkIcon of Code

	(method (doit thisIcon)
		(if
			(and
				(thisIcon isKindOf: IconItem)
				(& (thisIcon signal?) DISABLED)
			)
			(= disabledIcons
				(| disabledIcons (>> $8000 (theIconBar indexOf: thisIcon)))
			)
		)
	)
)

(instance ll5DoVerbCode of Code
	
	(method (doit theVerb theObj &tmp objDesc invDesc [str 100])
		(= objDesc (theObj description?))
		(switch theVerb
			(verbWalk
				((User curEvent?) claimed: FALSE)
			)
			(2
				(if (theObj lookStr?)
					(TimePrint (theObj lookStr?))
				else
					(Format @str 0 27 objDesc)
					(TimePrint @str)
				)
			)
			(5
				(Format @str 0 28 objDesc)
				(TimePrint @str)
			)
			(3
				(Format @str 0 29 objDesc)
				(TimePrint @str)
			)
			(4
				(= invDesc ((theIconBar curInvIcon?) description?))
				(Format @str 0 30 invDesc objDesc)
				(TimePrint @str)
			)
			(10
				(Format @str 0 31 objDesc)
				(TimePrint @str)
			)
			(else 
				(WriteDialog theObj theVerb)
			)
		)
	)
)

(instance ll5FtrInit of Code
	
	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 90)
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

(instance ll5Win of SysWindow)

(instance gcWin of SysWindow
	
	(method (open &tmp wMap savePort t l b r topColor theMaps [temp8 4] thePri temp10 [str 15] [len 4])
		(= thePri -1)
		(self
			top: (/ (- 200 (+ (CelHigh 995 1 1) 6)) 2)
			left: (/ (- 320 (+ 191 (CelWide 995 0 1))) 2)
			bottom: (+ (CelHigh 995 1 1) 6 (/ (- 200 (+ (CelHigh 995 1 1) 6)) 2))
			right: (+ 191 (CelWide 995 0 1) (/ (- 320 (+ 191 (CelWide 995 0 1))) 2))
			priority: thePri
		)
		(super open:)
		(DrawCel 995 0 6
			(+
				(/
					(-
						(- (+ 191 (CelWide 995 0 1)) (+ 4 (CelWide 995 1 1)))
						(CelWide 995 0 6)
					)
					2
				)
				4
				(CelWide 995 1 1)
			)
			6
			thePri
		)
		(DrawCel 995 1 1 4 3 thePri)
		(DrawCel 995 1 0 94 38 thePri)
		(DrawCel 995 1 0 135 38 thePri)
		(DrawCel 995 1 0 175 38 thePri)
		(DrawCel 995 0 4 63 (- 37 (+ (CelHigh 995 0 4) 3)) thePri)
		(DrawCel 995 0 3 101 (- 37 (+ (CelHigh 995 0 4) 3)) thePri)
		(DrawCel 995 0 2 146 (- 37 (+ (CelHigh 995 0 4) 3)) thePri)
		(DrawCel 995 0 5 186 (- 37 (+ (CelHigh 995 0 4) 3)) thePri)
		(= r (+ (= t (+ 46 (CelHigh 995 0 1))) 13))
		(= b
			(+
				(= l (+ 10 (CelWide 995 1 1)))
				(-
					(+ 191 (CelWide 995 0 1))
					(+ 10 (CelWide 995 1 1) 6)
				)
			)
		)
		(= topColor 0)
		(= theMaps 1)
		(Graph GFillRect t l (+ r 1) (+ b 1) theMaps topColor thePri)
		(Graph GShowBits t l (+ r 1) (+ b 1) VMAP)
		(Format @str 0 32 score possibleScore)
		(TextSize @len @str 999 0)
		(Display @str
			p_font 999
			p_color global127
			p_at
			(+
				10
				(CelWide 995 1 1)
				(/
					(-
						(-
							(+ 191 (CelWide 995 0 1))
							(+ 10 (CelWide 995 1 1) 6)
						)
						[len 3]
					)
					2
				)
			)
			(+ 46 (CelHigh 995 0 1) 3)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 67
		nsTop 37
		signal FIXED_POSN
		helpStr {Raise this to increase the amount of background animation. Lower it if game play seems sluggish.}
		sliderView 995
		bottomValue 1
		topValue 5
	)
	
	(method (doit theLevel)
		(if argc (theGame detailLevel: theLevel))
		(theGame detailLevel:)
	)
)

(instance volumeSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 107
		nsTop 37
		signal FIXED_POSN
		helpStr {This adjusts the volume on some sound boards and synthesizers.}
		sliderView 995
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 147
		nsTop 37
		signal FIXED_POSN
		helpStr {This adjusts Larry and Patti's speed, within the limits of your computer's capabilities.}
		sliderView 995
		bottomValue 15
	)
	
	(method (show)
		(if (not (User controls?))
			(= signal (| FIXED_POSN DISABLED))
			(= sliderLoop 9)
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

(instance textSlider of Slider
	(properties
		view 995
		loop 0
		cel 1
		nsLeft 187
		nsTop 37
		signal FIXED_POSN
		helpStr {Lower this slide to make text remain on the screen longer. Raise it if you are a fast reader.}
		sliderView 995
		bottomValue 24
		topValue 1
	)
	
	(method (doit theLevel)
		(if argc (= textSpeed theLevel))
		(return textSpeed)
	)
)

(instance iconSave of ControlIcon
	(properties
		view 995
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Use this to save the current state of your game. When you later select Restore, everything will be exactly as it is now.}
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 995
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {This restores a game you saved earlier.}
	)
	
	(method (select)
		(if (< curRoomNum 160)
			(curRoom newRoom: 155)
		else
			(super select: &rest)
		)
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 995
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Use this to restart the game from the very beginning.}
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 995
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Use this to leave the game.}
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 995
		loop 6
		cel 0
		nsLeft 8
		nsTop 86
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Here's where you learn more than you care to know about the creators of this game.}
	)
)

(instance iconHelp of IconItem
	(properties
		view 995
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 9
		message verbHelp
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr {To learn about the other items in this window, first click here, then pass the question mark over the other items.}
	)
)

(instance iconOk of IconItem
	(properties
		view 995
		loop 8
		cel 0
		nsLeft 8
		nsTop 106
		cursor 9
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Use this to exit this menu and resume game play.}
	)
)
