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
	ego									;pointer to ego
	theGame								;ID of the Game instance
	curRoom								;ID of current room
	speed =  6							;number of ticks between animations
	quit								;when TRUE, quit game
	cast								;collection of actors
	regions								;set of current regions
	timers								;list of timers in the game
	sounds								;set of sounds being played
	inventory							;set of inventory items in game
	addToPics							;list of views added to the picture
	curRoomNum							;current room number
	prevRoomNum							;previous room number
	newRoomNum							;number of room to change to
	debugOn								;generic debug flag -- set from debug menu
	score								;the player's current score
	possibleScore						;highest possible score
	showStyle	=		IRISOUT			;style of picture showing
	aniInterval							;# of ticks it took to do the last animation cycle
	theCursor							;the number of the current cursor
	normalCursor =		ARROW_CURSOR	;number of normal cursor form
	waitCursor	 =		HAND_CURSOR		;cursor number of "wait" cursor
	userFont	 =		USERFONT		;font to use for Print
	smallFont	 =		4				;small font for save/restore, etc.
	lastEvent							;the last event (used by save/restore game)
	modelessDialog						;the modeless Dialog known to User and Intrface
	bigFont		=		USERFONT		;large font
	volume		=		12				;sound volume
	version		=		{x.yyy.zzz}		;pointer to 'incver' version string			
	locales								;set of current locales
	[curSaveDir 20]						;address of current save drive/directory string
	aniThreshold	=	10
	perspective							;player's viewing angle:
										;	 degrees away from vertical along y axis
	features							;locations that may respond to events
	sortedFeatures          			;above+cast sorted by "visibility" to ego
	useSortedFeatures					;enable cast & feature sorting?
	demoScripts							;add to curRoomNum to find room demo script
	egoBlindSpot						;used by sortCopy to exclude
										;actors behind ego within angle 
										;from straight behind. 
										;Default zero is no blind spot
	overlays	=		-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	demoDialogTime	=	3				;how long Prints stay up in demo mode
	currentPalette
	modelessPort
	;globals 63-99 are unused
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
	;globals 100 and above are for game use.
	; Most of them are unused unless stated otherwise.
	global100
	global101
	theQueuedSound		;pointer for QueuedSound
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
	howFast		;machine speed level (0 = slow, 1 = medium, 2 = fast)
	global133
	global134	;these two globals are set in the control panel room,
	global135	; but are never used
	global136
	global137
	globalSound	;pointer for global music object
	numVoices	;number of voices supported by sound driver
	numColors	;number of colors supported by graphics driver
	global141 =  1
	global142
	global143
	global144
	debugging	;debug mode enabled
	global146
	global147
	global148
	global149
	isHandsOff	;ego can't be controlled
)
(procedure (HandsOff)
	;disable ego control
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (NormalEgo)
	;normalizes ego's animation
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		setStep: 3 2
		ignoreActors: FALSE
		looper: 0
	)
)

(procedure (SoundFX soundNum)
	;deals with machine-dependent sound files.
	; low-res sounds have resource numbers in the 200-range,
	; while standard res sounds have resource numbers in the 000-range
	(return
		;4 or less voices for PC Speaker and Tandy, 12 voices for CMS
		(if (or (< numVoices 4) (== numVoices 12))
			(+ soundNum 200)
		else ;for Adlib and MT-32
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
	;draw the status line
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
	(method (init)
		;set up the game's objects and globals
		(SysWindow color: vBLACK back: vLCYAN)
		(= numColors (Graph GDetect))
		(= systemWindow SysWindow)
		(super init:)
		(= numVoices (DoSound NumVoices))
		(= ego egoObj)
		(User
			alterEgo: ego
			blocks: 0
			y: 155
		)
		(= showStyle HSHUTTER)
		(TheMenuBar init: hide:)
		(StatusLine code: statusCode enable:)
		(HandsOff)
		(if debugging
			(self setCursor: normalCursor (HaveMouse) 300 170)
		else
			(self setCursor: normalCursor FALSE 350 200)
		)
		((= globalSound iceGlobalSound)
			init:
			owner: self
		)
		;now go to the speed tester
		(self newRoom: SPEED)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
	)
	
	(method (startRoom roomNum &tmp temp0)
		;clean up after a room change
		(if modelessDialog
			(modelessDialog dispose:)
		)
		;if memory is fragmented and debugging is on, bring up a warning and the internal debugger
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
		;only allow input if debugging is enabled
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
