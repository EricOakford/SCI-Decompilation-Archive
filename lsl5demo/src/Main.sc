;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use ColorInit)
(use BordWind)
(use PrintD)
(use LoadMany)
(use Sound)
(use Game)
(use User)

(public
	ll5 0
	DoDisplay 1
)

(local
	ego										;pointer to ego
	theGame									;ID of the Game instance
	curRoom									;ID of current room
	speed				=	6				;number of ticks between animations
	quit									;when TRUE, quit game
	cast					W				;collection of actors
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
	;globals 81-99 are unused
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
	colBlack
	colDBlue
	colLBlue
	colGray
	colGreen
	colRed
	colMagenta
	colYellow
	colWhite
	colLMagenta
	colBrown
)
(procedure (DoDisplay args &tmp theMode theFont theWidth theX theY theForeColor i)
	(= theX 1)
	(= theY [args 1])
	(= theMode teJustLeft)
	(= theFont 2510)
	(= theWidth 318)
	(= theForeColor colWhite)
	(for ((= i 1)) (< i argc) ((++ i))
		(switch [args i]
			(#mode
				(= theMode [args (++ i)])
			)
			(#font
				(= theFont [args (++ i)])
			)
			(#width
				(= theWidth [args (++ i)])
			)
			(#at
				(= theX [args (++ i)])
				(= theY [args (++ i)])
				(if (== theWidth 318)
					(= theWidth (- 320 theX))
				)
			)
			(#color
				(= theForeColor [args (++ i)])
			)
		)
	)
	(Display [args 0]
		p_at (+ theX 1) (+ theY 1)
		p_color colBlack
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
		(LoadMany PICTURE 111 112)
		(LoadMany CURSOR HAND_CURSOR 69 ARROW_CURSOR)
		(LoadMany FONT 0 2510)
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
		((= theMusic demoMusic)
			owner: self
			init:
		)
		(= waitCursor 997)
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
			color: colWhite
			back: colGray
			topBordColor: colLBlue
			lftBordColor: colLBlue
			rgtBordColor: colDBlue
			botBordColor: colDBlue
		)
		(self newRoom: 1)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(PrintD {Exit the demo?}
					#new
					#button {Oops!__Lemme see more!} 0
					#new
					#button {Yeah, I gotta place an order!} 1
				)
				(theGame quitGame:)
			)
		)
	)
)

(instance ll5Win of BorderWindow)
