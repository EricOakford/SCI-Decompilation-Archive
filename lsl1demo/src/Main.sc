;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use ColorInit)
(use BordWind)
(use PrintD)
(use Sound)
(use Game)
(use User)

(public
	ll1 0
	DoDisplay1 1
	DoDisplay2 2
	DoDisplay3 3
	FindColor 4
)

(local
	ego										;pointer to ego
	theGame									;ID of the Game instance
	curRoom									;ID of current room
	speed				=	6				;number of ticks between animations
	quit									;when TRUE, quit game
	cast									;collection of actors
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
	lastSysGlobal	theMusic
	global101
	numColors
	numVoices
	global104
	isVGA
	global106
	global107
	global108
	global109
	saveBits
	colBlack
	colBlue
	colGreen
	colRed
	colMagenta
	colYellow
	colWhite
	colLGreen
	colDGreen
	saveBits1
	saveBits2
	saveBits3
	colDRed
	colDBlue
	colGray1
	colGray2
	colGray3
	colGray4
	colGray5
	colGray6
	theMusic2
)
(procedure (DoDisplay1 args &tmp theMode theFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 0 0 p_restore saveBits)
			(Display 0 0 p_restore saveBits2)
		else
			(= theX 1)
			(= theY [args 1])
			(= theMode teJustCenter)
			(= theFont 312)
			(= theWidth 318)
			(= theForeColor colWhite)
			(= theBackColor colRed)
			(for ((= i 2)) (< i argc) ((++ i))
				(switch [args i]
					(#mode
						(= theMode [args (++ i)])
					)
					(33
						(= theFont [args (++ i)])
					)
					(#width
						(= theWidth [args (++ i)])
					)
					(#at
						(= theX [args (++ i)])
						(= theY [args (++ i)])
					)
					(#color
						(= theForeColor [args (++ i)])
					)
					(#back
						(= theBackColor [args (++ i)])
					)
				)
			)
			(= saveBits2
				(Display [args 0]
					p_at (+ theX 1) (+ theY 1)
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theFont
					p_save
				)
			)
			(= saveBits
				(Display [args 0]
					p_at theX theY
					p_color theForeColor
					p_width theWidth
					p_mode theMode
					p_font theFont
					p_save
				)
			)
			(return i)
		)
	)
)

(procedure (DoDisplay2 args &tmp theMode theFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 0 0 p_restore saveBits1)
			(Display 0 0 p_restore saveBits3)
		else
			(= theX 1)
			(= theY [args 1])
			(= theMode teJustCenter)
			(= theFont 312)
			(= theWidth 318)
			(= theForeColor colWhite)
			(= theBackColor colRed)
			(for ((= i 2)) (< i argc) ((++ i))
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
					)
					(#color
						(= theForeColor [args (++ i)])
					)
					(#back
						(= theBackColor [args (++ i)])
					)
				)
			)
			(= saveBits3
				(Display [args 0]
					p_at (+ theX 1) (+ theY 1)
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theFont
					p_save
				)
			)
			(= saveBits1
				(Display [args 0]
					p_at theX theY
					p_color theForeColor
					p_width theWidth
					p_mode theMode
					p_font theFont
					p_save
				)
			)
			(return i)
		)
	)
)

(procedure (DoDisplay3 args &tmp theMode theFont theWidth theX theY theForeColor theBackColor i)
	(= theX 1)
	(= theY [args 1])
	(= theMode teJustCenter)
	(= theFont 312)
	(= theWidth 318)
	(= theForeColor colWhite)
	(= theBackColor colRed)
	(for ((= i 2)) (< i argc) ((++ i))
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
			)
			(#color
				(= theForeColor [args (++ i)])
			)
			(#back
				(= theBackColor [args (++ i)])
			)
		)
	)
	(Display [args 0]
		p_at (+ theX 1) (+ theY 1)
		p_color theBackColor
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

(procedure (FindColor col256 col16)
	(if (< col256 0)
		(= col256 0)
	)
	(if (> col256 255)
		(= col256 255)
	)
	(if (< col16 0)
		(= col16 0)
	)
	(if (> col16 15)
		(= col16 15)
	)
	(return col256)
)

(instance egoObj of Ego)

(instance ll1 of Game
	(method (init &tmp [temp0 6])
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
		((= theMusic longSong)
			owner: self
			init:
		)
		((= theMusic2 longSong2)
			owner: self
			init:
		)
		(= waitCursor 997)
		(= version {x.yyy})
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(= isVGA FALSE)
		else
			(= isVGA TRUE)
		)
		(= systemWindow ll1Win)
		(ll1Win
			color: colBlack
			back: (FindColor colGray4 colGray1)
			topBordColor: colGray6
			lftBordColor: (FindColor colGray5 colWhite)
			rgtBordColor: (FindColor colGray3 colGray2)
			botBordColor: colGray2
		)
		(self newRoom: 100)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(PrintD
					{Exit the demo?}
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

(instance ll1Win of BorderWindow)

(instance longSong of Sound)

(instance longSong2 of Sound)
