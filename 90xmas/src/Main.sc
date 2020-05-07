;;; Sierra Script 1.0 - (do not remove this comment)
(script# MAIN)
(include game.sh)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Game)
(use User)
(use Menu)

(public
	Card 0
	HandsOff 1
	proc0_2 2
	proc0_3 3
	gameSound 4
	backSound 5
	saidSound 6
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
	volume =  12
	version
	locales
	curSaveDir
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
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	demoScripts
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime =  3
	currentPalette
	modelessPort
	sysLogPath
		global64
		global65
		global66
		global67
		global68
		global69
		global70
		global71
	endSysLogPath
	ftrInitializer
	doVerbCode
	firstSaidHandler
	useObstacles =  TRUE
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
	isHandsOff
	debugging
	global102
	global103
	numVoices
	global105
	global106
	global107
	numColors
)
(procedure (HandsOff)
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(theGame setCursor: waitCursor (HaveMouse) 350 200)
)

(procedure (proc0_2)
)

(procedure (proc0_3)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance gameSound of Sound
	(properties
		priority 5
	)
)

(instance backSound of Sound
	(properties)
)

(instance saidSound of Sound
	(properties
		priority 6
	)
)

(instance Card of Game
	(properties)
	
	(method (init &tmp [temp0 11])
		(= systemWindow SysWindow)
		(super init:)
		(= numVoices (DoSound NumVoices))
		(= ego egoObj)
		(= numColors (Graph GDetect))
		(User alterEgo: ego)
		(= showStyle IRISIN)
		(TheMenuBar init: draw: hide:)
		(User
			alterEgo: ego
			prompt: {What is your wish?}
			blocks: 0
			y: 160
		)
		(backSound owner: self init:)
		(HandsOff)
		(theGame setCursor: waitCursor TRUE)
		(theGame newRoom: TITLE)
	)
	
	(method (startRoom roomNum &tmp [temp0 12])
		(LoadMany FALSE MOVEFWD JUMP OSC PATH)
		(if
			(and
				debugging
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				(Print 0 0
					#button {Debug} 1
				)
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
	)
)
