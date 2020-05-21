;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "999.shm")
(use Print)
(use MoveCyc)
(use StopWalk)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use User)

(public
	Glory 0
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
	demoDialogTime
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
	debugging
	dongle =  1234
	soundFx
	showSierraLogo =  TRUE
	demoRunning
	currentPic
)
(instance egoObj of Ego)

(instance SFX of Sound)

(instance Glory of Game
	
	(method (init &tmp [temp0 102])
		(super init:)
		(= ego egoObj)
		(User alterEgo: ego canControl: FALSE canInput: FALSE)
		((= systemWindow SysWindow) back: 4)
		(theGame setCursor: INVIS_CURSOR FALSE 300 300)
		(= demoRunning 1)
		((= soundFx SFX) init: owner: self flags: 0)
		(self newRoom: 100)
	)
	
	(method (startRoom roomNum)
		(if (u> (MemoryInfo FreeHeap) (+ 10 (MemoryInfo LargestPtr)))
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(Prints {Memory fragmented.})
			(SetDebug)
		)
		Print
		StopWalk
		MoveCycle
		Cycle
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event)
		(if (super handleEvent: event)
		else
			(if (HaveMouse)
				(= demoRunning FALSE)
				(theGame setCursor: ARROW_CURSOR TRUE)
			)
			(if (!= (event message?) leaveIt)
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(if
					(Print
						font: userFont
						mode: teJustCenter
						width: 180
						addText: N_DEMO NULL C_ASKQUIT 1 0 0 SYSTEM
						addText: N_DEMO NULL C_ASKQUIT 4 0 14 SYSTEM
						addButton: 0 N_DEMO NULL C_ASKQUIT 3 60 55 SYSTEM
						addButton: 1 N_DEMO NULL C_ASKQUIT 2 75 35 SYSTEM
						init:
					)
					(super quitGame: TRUE)
				else
					(theGame setCursor: INVIS_CURSOR FALSE)
					(= demoRunning TRUE)
				)
			else
				(theGame setCursor: INVIS_CURSOR FALSE)
				(= demoRunning TRUE)
			)
		)
	)
	
	(method (setCursor form showIt theX theY &tmp temp0)
		(if (not demoRunning)
			(if argc
				(if (IsObject form)
					((= theCursor form) init:)
				else
					(SetCursor (= theCursor form) 0 0)
				)
			)
			(if (and (> argc 1) (not showIt))
				(SetCursor INVIS_CURSOR 0 0)
			)
			(if (> argc 2)
				(SetCursor theX theY)
			)
		)
	)
)
