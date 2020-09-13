;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use ColorInit)
(use BordWind)
(use PrintD)
(use LoadMany)
(use Sound)
(use Window)
(use Game)
(use User)

(public
	ll5 0
	DoDisplay 1
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
	gameTime
	narrator
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
	numColors
	numVoices
	global104
	isVGA
	global106
	global107
	global108
	global109
	global110
	myTextColor
	myRgtBordColor
	myTopBordColor
	myBackColor
	myTextColor2
	myTextColor3
	myTextColor4
	myTextColor5
	myTextColor6
	myTextColor7
	myTextColor8
)
(procedure (DoDisplay args &tmp theMode theFont theWidth theX theY theForeColor i)
	(= theX 1)
	(= theY [args 1])
	(= theMode 0)
	(= theFont 2510)
	(= theWidth 318)
	(= theForeColor myTextColor6)
	(for ((= i 1)) (< i argc) ((++ i))
		(switch [args i]
			(#mode
				(= theMode
					[args (++ i)]
				)
			)
			(#font
				(= theFont
					[args (++ i)]
				)
			)
			(#width
				(= theWidth
					[args (++ i)]
				)
			)
			(#at
				(= theX
					[args (++ i)]
				)
				(= theY [args (++ i)])
				(if (== theWidth 318)
					(= theWidth
						(- 320 theX)
					)
				)
			)
			(#color
				(= theForeColor [args (++ i)])
			)
		)
	)
	(Display [args 0]
		p_at (+ theX 1) (+ theY 1)
		p_color myTextColor
		p_width theWidth
		p_mode theMode
		p_font theFont
	)
	(Display [args 0]
		p_at theX theY
		p_color theForeColor
		p_width theWidth
		p_mode theMode
		p_font theFont
	)
)

(instance egoObj of Ego)

(instance demoMusic of Sound)

(instance ll5 of Game
	
	(method (init &tmp [temp0 6])
		(LoadMany PICTURE pDemoOpening1 pDemoOpening2)
		(LoadMany CURSOR 20 69 ARROW_CURSOR)
		(LoadMany FONT SYSFONT 2510)
		(ColorInit)
		(super init:)
		(self setCursor: 69 TRUE 330 200)
		(= ego egoObj)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: FALSE
			canInput: FALSE
		)
		((= theMusic demoMusic) owner: self init:)
		(= waitCursor HAND_CURSOR)
		(= version {x.yyy})
		(= numVoices (DoSound NumVoices))
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(= isVGA FALSE)
		else
			(= isVGA TRUE)
		)
		(= systemWindow ll5Win)
		(ll5Win
			color: myTextColor6
			back: myBackColor
			topBordColor: myTopBordColor
			lftBordColor: myTopBordColor
			rgtBordColor: myRgtBordColor
			botBordColor: myRgtBordColor
		)
		(self newRoom: 1)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(PrintD {Exit the demo?}
					#new
					#button {Oops!__Lemme see more!} FALSE
					#new
					#button {Yeah, I gotta place an order!} TRUE
				)
				(theGame quitGame:)
			)
		)
	)
)

(instance ll5Win of BorderWindow)