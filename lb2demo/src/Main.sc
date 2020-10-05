;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use ColorInit)
(use PrintD)
(use LoadMany)
(use Window)
(use Sound)
(use Game)
(use User)
(use System)

(public
	lb2 0
	DoDisplay 1
	proc0_2 2
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
	music
	gameCode =  1234
	global102
	numColors
	numVoices
	global105
	isVGA
	global107
	global108
	global109
	global110
	myBordColor
	global112
	global113
	myBackColor
	global115
	global116
	global117
	global118
	myTextColor
	global120
	global121
	global122
)
(procedure (DoDisplay theString theMode theFont theWidth theX theY theForeColor theBackColor)
	(if (== theWidth 318)
		(= theWidth (- 320 theX))
	)
	(Display theString
		p_at (+ theX 1) (+ theY 1)
		p_color theBackColor
		p_width theWidth
		p_mode theMode
		p_font theFont
	)
	(Display theString
		p_at theX theY
		p_color theForeColor
		p_width theWidth
		p_mode theMode
		p_font theFont
	)
)

(procedure (proc0_2 theString theColor &tmp savePort)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(= savePort (GetPort))
	(SetPort 0)
	(Display &rest
		101 teJustCenter	;p_mode
		p_color theColor
		p_font 20071
		p_width 318
		p_at 1 (- 89 (/ (* 14 theString) 2))
	)
	(SetPort savePort)
)

(instance egoObj of Ego)

(instance demoMusic of Sound)

(instance lb2 of Game
	
	(method (init &tmp [temp0 6])
		(LoadMany PICTURE 780)
		(LoadMany FONT 20071 1151)
		(ColorInit)
		(= normalCursor INVIS_CURSOR)
		(super init:)
		(= ego egoObj)
		(User
			alterEgo: ego
			canControl: FALSE
			canInput: FALSE
		)
		((= music demoMusic)
			owner: self
			init:
		)
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
		(= systemWindow lb2Win)
		(lb2Win color: myTextColor back: myBackColor)
		(self newRoom: 1)
	)
	
	(method (startRoom roomNum)
		(super startRoom: roomNum &rest)
	)
	
	(method (handleEvent event)
		(if (OneOf (event type?) keyDown mouseDown)
			(self setCursor: ARROW_CURSOR)
			(theGame quitGame:
				(PrintD {Exit the demo?}
					#new
					#button {No, I want to see more blood...} 0
					#new
					#button {Yes, please, before someone else gets hurt.} 1
				)
			)
			(self setCursor: INVIS_CURSOR)
		)
	)
	
	(method (setCursor form showIt theX theY &tmp oldCur)
		(= oldCur theCursor)
		(if argc
			(SetCursor (= theCursor form) 0 0)
		)
		(if (and (> argc 1) (not showIt))
			(SetCursor INVIS_CURSOR 0 0)
		)
		(if (> argc 2)
			(SetCursor theX theY)
		)
		(return oldCur)
	)
)

(instance lb2Win of SysWindow
	
	(method (open &tmp savePort)
		(= type $80)
		(= lsLeft (- left (/ (CelWide 994 0 0) 2)))
		(= lsTop (- top 9))
		(= lsRight (+ right (/ (CelWide 994 0 0) 2)))
		(= lsBottom (Max (+ bottom 3) (+ lsTop (CelHigh 994 0 0) 3)))
		(= priority 15)
		(super open:)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GFillRect top left bottom right 3 myBackColor 15)
		(Graph GDrawLine (- top 1) (- left 1) (- top 1) right myBordColor 15)
		(Graph GDrawLine (- top 1) (- left 1) bottom (- left 1) myBordColor 15)
		(Graph GDrawLine bottom (- left 1) bottom right myBordColor 15)
		(Graph GDrawLine (- top 1) right bottom right myBordColor 15)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(DrawCel 994 0 0 (+ lsLeft 2) (+ lsTop 1) -1)
		(DrawCel 994 0 1
			(- (- lsRight (CelWide 994 0 0)) 2)
			(+ lsTop 1)
			-1
		)
		(DrawCel 994 0 2
			(+ lsLeft 2)
			(- (- lsBottom (CelHigh 994 0 0)) 1)
			-1
		)
		(DrawCel 994 0 3
			(- (- lsRight (CelWide 994 0 0)) 2)
			(- (- lsBottom (CelHigh 994 0 0)) 1)
			-1
		)
		(SetPort savePort)
	)
)
