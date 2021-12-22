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
	[sysLogPath 10]						;used for system standard logfile path (uses 10 globals)
	ftrInitializer						;pointer to code that gets called from
										;a feature's init
	doVerbCode							;pointer to code that gets invoked if
										;no feature claims a user event
	firstSaidHandler		
	useObstacles =  TRUE				;will Ego use PolyPath or not?
	;globals 77-99 are unused
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
	;globals 100 and above are for game use
	isHandsOff				;ego can't be controlled
	debugging				;debug mode enabled
	global102				;unused
	global103				;unused
	numVoices				;Number of voices supported by sound driver
	global105				;unused
	global106				;unused
	global107				;unused
	numColors				;Number of colors supported by graphics driver
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
	;for sound effects
	(properties
		priority 5
	)
)

;
(instance backSound of Sound
	;for music
)

(instance saidSound of Sound
	;for director's voice
	(properties
		priority 6
	)
)

(instance Card of Game	
	(method (init &tmp [temp0 11])
		(= systemWindow SysWindow)
		(super init:)
		(= numVoices (DoSound NumVoices))
		(= ego egoObj)
		(= numColors (Graph GDetect))
		(User alterEgo: ego)
		(= showStyle IRISIN)
		(TheMenuBar
			init:
			draw:
			hide:
		)
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
