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
	AGIDisplay1 1
	AGIDisplay2 2
	SCIDisplay 3
	VGAOrEGA 4
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
	music
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
	myTextColor
	myTextColor2
	myTextColor3
	myTextColor4
	myTextColor5
	myTextColor6
	myTextColor7
	myTextColor8
	myTextColor9
	saveBits1
	saveBits2
	saveBits3
	myTextColor10
	myTextColor11
	myTextColor12
	myBotBordColor
	myRgtBordColor
	myBackColor
	myLftBordColor
	myTopBordColor
	globalSound
)
(procedure (AGIDisplay1 theString &tmp theMode theFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 0 0 p_restore saveBits)
			(Display 0 0 p_restore saveBits2)
		else
			(= theX 1)
			(= theY [theString 1])
			(= theMode teJustCenter)
			(= theFont 312)
			(= theWidth 318)
			(= theForeColor myTextColor7)
			(= theBackColor myTextColor4)
			(= i 2)
			(while (< i argc)
				(switch [theString i]
					(#mode
						(= theMode [theString (++ i)])
					)
					(#font
						(= theFont [theString (++ i)])
					)
					(#width
						(= theWidth [theString (++ i)])
					)
					(#at
						(= theX [theString (++ i)])
						(= theY [theString (++ i)])
					)
					(#color
						(= theForeColor [theString (++ i)])
					)
					(#back
						(= theBackColor [theString (++ i)])
					)
				)
				(++ i)
			)
			(= saveBits2
				(Display
					[theString 0]
					p_at (+ theX 1) (+ theY 1)
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theFont
					p_save
				)
			)
			(= saveBits
				(Display
					[theString 0]
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

(procedure (AGIDisplay2 theString &tmp theMode theFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 0 0 p_restore saveBits1)
			(Display 0 0 p_restore saveBits3)
		else
			(= theX 1)
			(= theY [theString 1])
			(= theMode teJustCenter)
			(= theFont 312)
			(= theWidth 318)
			(= theForeColor myTextColor7)
			(= theBackColor myTextColor4)
			(= i 2)
			(while (< i argc)
				(switch [theString i]
					(#mode
						(= theMode [theString (++ i)])
					)
					(#font
						(= theFont [theString (++ i)])
					)
					(#width
						(= theWidth [theString (++ i)])
					)
					(#at
						(= theX [theString (++ i)])
						(= theY [theString (++ i)])
					)
					(#color
						(= theForeColor [theString (++ i)])
					)
					(#back
						(= theBackColor [theString (++ i)])
					)
				)
				(++ i)
			)
			(= saveBits3
				(Display
					[theString 0]
					p_at (+ theX 1) (+ theY 1)
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theFont
					p_save
				)
			)
			(= saveBits1
				(Display
					[theString 0]
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

(procedure (SCIDisplay theString &tmp theMode theFont theWidth theX theY theForeColor theBackColor i)
	(= theX 1)
	(= theY [theString 1])
	(= theMode teJustCenter)
	(= theFont 312)
	(= theWidth 318)
	(= theForeColor myTextColor7)
	(= theBackColor myTextColor4)
	(= i 2)
	(while (< i argc)
		(switch [theString i]
			(#mode
				(= theMode [theString (++ i)])
			)
			(#font
				(= theFont [theString (++ i)])
			)
			(#width
				(= theWidth [theString (++ i)])
			)
			(#at
				(= theX [theString (++ i)])
				(= theY [theString (++ i)])
			)
			(#color
				(= theForeColor [theString (++ i)])
			)
			(#back
				(= theBackColor [theString (++ i)])
			)
		)
		(++ i)
	)
	(Display
		[theString 0]
		p_at (+ theX 1) (+ theY 1)
		p_color theBackColor
		p_width theWidth
		p_mode theMode
		p_font theFont
	)
	(Display
		[theString 0]
		p_at theX theY
		p_color theForeColor
		p_width theWidth
		p_mode theMode
		p_font theFont
	)
)

(procedure (VGAOrEGA vga ega)
	(if (< vga 0) (= vga 0))
	(if (> vga 255) (= vga 255))
	(if (< ega 0) (= ega 0))
	(if (> ega 15) (= ega 15))
	(return vga)
)

(instance egoObj of Ego
	(properties)
)

(instance ll1 of Game
	(properties)
	
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
		((= music longSong) owner: self init:)
		((= globalSound longSong2) owner: self init:)
		(= waitCursor HAND_CURSOR)
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
			color: myTextColor
			back: (VGAOrEGA myBackColor myTextColor12)
			topBordColor: myTopBordColor
			lftBordColor: (VGAOrEGA myLftBordColor myTextColor7)
			rgtBordColor: (VGAOrEGA myRgtBordColor myBotBordColor)
			botBordColor: myBotBordColor
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

(instance ll1Win of BorderWindow
	(properties)
)

(instance longSong of Sound
	(properties)
)

(instance longSong2 of Sound
	(properties)
)
