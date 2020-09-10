;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Save)
(use Game)
(use User)
(use Menu)
(use System)

(public
	emc 0
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
	version =  {statusCode}
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
	demoDialogTime
	currentPalette
		global62
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
	vaporCalcCued
)
(instance statusCode of Code

	(method (doit strg)
		(Format strg 0 0)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance emc of Game
	(properties)
	
	(method (init)
		(= systemWindow (SysWindow new:))
		(super init:)
		(= ego egoObj)
		(User
			alterEgo: ego
			canInput: FALSE
			canControl: FALSE
			blocks: 0
			y: 155
		)
		(TheMenuBar init:)
		(StatusLine code: statusCode)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(= vaporCalcCued FALSE)
		(self newRoom: 1)
	)
)
