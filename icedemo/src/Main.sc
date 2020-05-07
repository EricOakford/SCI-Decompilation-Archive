;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use User)
(use Menu)
(use System)

(public
	iceDemo 0
	HandsOff 1
	NormalEgo 2
	SoundFX 3
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
	version =  {ego}
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
	global100
	global101
	theQueuedSound
	global103
	global104
	global105
	global106
	global107
	global108
	global109
	global110
	global111
	global112
	global113
	global114
	global115
	global116
	global117
	global118
	global119
	global120
	global121
	global122
	global123
	global124
	global125
	global126
	global127
	global128
	global129
	global130
	global131
	detailLevel
	global133
	global134
	global135
	global136
	global137
	globalSound
	numVoices
	numColors
	global141 =  1
	global142
	global143
	global144
	debugging
	global146
	global147
	global148
	global149
	isHandsOff
)
(procedure (HandsOff)
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (NormalEgo)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		setStep: 3 2
		ignoreActors: 0
		looper: 0
	)
)

(procedure (SoundFX soundNum)
	(return
		(if (or (< numVoices 4) (== numVoices 12))
			(+ soundNum 200)
		else
			soundNum
		)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance statusCode of Code
	(properties)
	
	(method (doit strg)
		(Format strg 0 0)
	)
)

(instance iceGlobalSound of Sound
	(properties
		number 1
	)
)

(instance iceDemo of Game
	(properties)
	
	(method (init)
		(SysWindow color: vBLACK back: vLCYAN)
		(= numColors (Graph GDetect))
		(= systemWindow SysWindow)
		(super init:)
		(= numVoices (DoSound NumVoices))
		(= ego egoObj)
		(User alterEgo: ego blocks: 0 y: 155)
		(= showStyle HSHUTTER)
		(TheMenuBar init: hide:)
		(StatusLine code: statusCode enable:)
		(HandsOff)
		(if debugging
			(self setCursor: normalCursor (HaveMouse) 300 170)
		else
			(self setCursor: normalCursor FALSE 350 200)
		)
		((= globalSound iceGlobalSound) init: owner: self)
		(self newRoom: SPEED)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(if debugging
			(if
				(and
					(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
					(Print 0 1
						#button {Debug} 1
					)
				)
				(SetDebug)
			)
			(User canInput: TRUE)
		)
		(NormalEgo)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event)
		(if debugging
			(if
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
				)
				(if (not (User canInput?))
					(event claimed: TRUE)
				else
					(cast eachElementDo: #handleEvent event)
					(if (event claimed?) (return))
				)
			)
			(super handleEvent: event)
			(if (event claimed?) (return))
			(switch (event type?)
				(keyDown
					((ScriptID DEBUG) handleEvent: event)
				)
				(mouseDown
					((ScriptID DEBUG) handleEvent: event)
				)
			)
		)
	)
)
