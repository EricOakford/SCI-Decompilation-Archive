;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use PQEgo)
(use ColorInit)
(use PMouse)
(use GControl)
(use BordWind)
(use IconBar)
(use PolyPath)
(use Polygon)
(use DCIcon)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	pq3 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	EgoDead 5
	SolvePuzzle 6
	EgoHeadMove 7
	VerbFail 8
	Bset 9
	Btst 10
	Bclr 11
	proc0_12 12
	proc0_13 13
	Face 14
	Speak 15
	AdvanceTime 16
	VGAOrEGA 17
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
	syncBias
	theSync
	cDAudio
	fastCast
	inputFont
	tickOffset
	howFast
	gameTime
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
	gameCode
	numColors
	numVoices
	startingRoom
	currentHour
	currentMinutes
	currentSeconds
	debugging
	global109
	global110
	global111
	global112
	global113
	global114
	saveCursorX =  -1
	saveCursorY =  -1
	oldSysTime
	mapTextColor
	myTextColor
	myInsideColor
	myBotBordColor
	myRgtBordColor
	myBackColor
	myLftBordColor
	myTopBordColor
	global126
	global127
	global128
	global129
	global130
	global131
	global132
	global133
	global134
	global135
	global136
	myHighlightColor
	mapBackColor
	global139
	global140
	global141
	global142
	global143
	global144
	global145
	global146
	global147
	global148
	global149
	global150
	gameFlags
		global152
		global153
		global154
		global155
		global156
		global157
		global158
		global159
		global160
		global161
		global162
		global163
		global164
		global165
		global166
		global167
)
(procedure (NormalEgo theView theLoop)
	(if (> argc 0)
		(if (!= theView -1) (ego view: theView))
		(if (and (> argc 1) (!= theLoop -1))
			(ego loop: theLoop)
		)
	)
	(ego
		normal: TRUE
		moveHead: TRUE
		setLoop: -1
		setLoop: stopGroop
		setPri: -1
		setMotion: 0
		setCycle: Walk
		setStep: 3 2
		illegalBits: 0
		ignoreActors: 0
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
)

(procedure (HandsOff &tmp oldCurIcon)
	(User canControl: FALSE canInput: FALSE)
	(if (not argc) (ego setMotion: 0))
	(= oldCurIcon (theIconBar curIcon?))
	(theIconBar disable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ITEM
		ICON_INVENTORY
		ICON_NOTEBOOK
	)
	(theIconBar curIcon: oldCurIcon)
	(if (not (HaveMouse))
		(= saveCursorX ((User curEvent?) x?))
		(= saveCursorY ((User curEvent?) y?))
		(theGame setCursor: waitCursor TRUE 304 172)
	else
		(theGame setCursor: waitCursor TRUE)
	)
	(if pMouse (pMouse stop:))
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(theIconBar enable: 
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ITEM
		ICON_INVENTORY
		ICON_NOTEBOOK
	)
	(if (and (not (HaveMouse)) (!= saveCursorX -1))
		(theGame
			setCursor: ((theIconBar curIcon?) cursor?) TRUE saveCursorX saveCursorY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	(= saveCursorX (= saveCursorY -1))
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
)

(procedure (EgoDead)
)

(procedure (SolvePuzzle)
)

(procedure (EgoHeadMove)
	(egoHead init: ego view: (ego view?) cycleSpeed: 24)
)

(procedure (VerbFail &tmp list [temp1 2] oldCur event ticks [temp6 5])
	(= oldCur (theGame setCursor: 69 TRUE))
	(= event (User curEvent?))
	(redX
		x: (event x?)
		y: (+ 300 (event y?))
		z: 300
		show:
	)
	(redXList add: redX)
	(Animate (redXList elements?) TRUE)
	(Animate (cast elements?) FALSE)
	(= ticks (GetTime))
	(while (< (Abs (- ticks (GetTime))) 40)
		(breakif
			(OneOf ((= event (Event new:)) type?) keyDown mouseDown)
		)
		(event dispose:)
	)
	(if (IsObject event) (event dispose:))
	(redX hide: posn: 1000 -1000)
	(Animate (redXList elements?) TRUE)
	(redXList delete: redX)
	(theGame setCursor: oldCur)
)

(procedure (Bset)
	(manageFlags 0 &rest)
)

(procedure (Btst)
	(manageFlags 1 &rest)
)

(procedure (Bclr)
	(manageFlags 2 &rest)
)

(procedure (proc0_12)
)

(procedure (proc0_13)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj temp4)
	(= obj 0)
	(= temp4 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (> argc 2)
			(if (IsObject both)
				(= obj both)
			else
				(= temp4 both)
			)
			(if (== argc 4)
				(= obj whoToCue)
			)
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4)
			(= obj whoToCue)
		)
	)
	(if temp4
		(Face actor2 actor1)
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject obj) obj else 0)
	)
)

(procedure (Speak)
)

(procedure (AdvanceTime)
)

(procedure (VGAOrEGA vga ega)
	(if (< vga 0) (= vga 0))
	(if (> vga 255) (= vga 255))
	(if (< ega 0) (= ega 0))
	(if (> ega 15) (= ega 15))
	(return (if (Btst fIsVGA) vga else ega))
)

(procedure (manageFlags what flagEnum &tmp temp0 temp1 temp2 temp3)
	(asm
		ldi      0
		sat      temp0
code_0a76:
		lst      temp0
		lsp      argc
		ldi      1
		sub     
		lt?     
		bnt      code_0aeb
		lat      temp0
		lapi     flagEnum
		sat      temp1
		push    
		ldi      16
		div     
		sat      temp2
		pushi    1
		lst      temp1
		ldi      16
		mod     
		shl     
		sat      temp3
		lsp      what
		dup     
		ldi      1
		eq?     
		bnt      code_0aa5
		jmp      code_0aeb
		jmp      code_0ae5
code_0aa5:
		dup     
		ldi      2
		eq?     
		bnt      code_0abc
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		bnot    
		and     
		push    
		lat      temp2
		sagi     gameFlags
		jmp      code_0ae5
code_0abc:
		dup     
		ldi      0
		eq?     
		bnt      code_0ad2
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		or      
		push    
		lat      temp2
		sagi     gameFlags
		jmp      code_0ae5
code_0ad2:
		dup     
		ldi      3
		eq?     
		bnt      code_0ae5
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		xor     
		push    
		lat      temp2
		sagi     gameFlags
code_0ae5:
		toss    
		+at      temp0
		jmp      code_0a76
code_0aeb:
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		and     
		ret     
	)
)

(instance egoObj of Body
	(properties
		name "ego"
		description {Sony Bonds}
		sightAngle 180
		lookStr {*** It's you, Sonny.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
			)
			(verbUse
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoHead of Head
	(properties
		description {Sonny Bonds}
		lookStr {*** It's you, Sonny.}
	)
	
	(method (doVerb theVerb theItem)
		(ego doVerb: theVerb theItem)
	)
)

(instance stopGroop of GradualLooper
	(method (doit)
		(if (== (ego loop?) (- (NumLoops ego) 1))
			(ego loop: (ego cel?))
		)
		(super doit: &rest)
	)
)

(instance longSong of Sound)

(instance redX of View
	(properties
		view 942
	)
)

(instance redXList of List)

(instance kDHandler of EventHandler)

(instance dirHandler of EventHandler)

(instance mDHandler of EventHandler)

(instance pq3Win of BorderWindow)

(instance invWin of InsetWindow)

(instance pq3 of Game
	
	(method (init)
		(= systemWindow pq3Win)
		(= useSortedFeatures FALSE)
		(ColorInit)
		PolyPath
		Polygon
		(ScriptID SIGHT)
		(super init:)
		(StrCpy @sysLogPath {})
		(= speed 2)
		(= version {x.yyy.zzz})
		(if (== (StrAt version 0) 120)
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(= doVerbCode DoVerbCode)
		(= ftrInitializer FtrInit)
		((= keyDownHandler kDHandler) add:)
		((= mouseDownHandler mDHandler) add:)
		((= directionHandler dirHandler) add:)
		(= pMouse PseudoMouse)
		(= eatMice 5)
		(redXList add:)
		(self egoMoveSpeed: 2 setCursor: theCursor TRUE 304 172)
		((= ego egoObj)
			head: egoHead
			moveSpeed: (self egoMoveSpeed?)
			cycleSpeed: (self egoMoveSpeed?)
		)
		(User alterEgo: ego canControl: FALSE canInput: FALSE)
		(= possibleScore 6969)
		(= userFont 4)
		(= waitCursor HAND_CURSOR)
		(= currentHour 8)
		(= currentMinutes 0) 
		(= currentSeconds 0)
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(Bclr fIsVGA)
		else
			(Bset fIsVGA)
		)
		(pq3Win
			color: myTextColor
			back: (VGAOrEGA myBackColor myBotBordColor)
			topBordColor: myTopBordColor
			lftBordColor: (VGAOrEGA myLftBordColor myTopBordColor)
			rgtBordColor: (VGAOrEGA myRgtBordColor myInsideColor)
			botBordColor: (VGAOrEGA myBotBordColor myInsideColor)
		)
		(gcWin
			color: myTextColor
			back: (VGAOrEGA myBackColor myBotBordColor)
			topBordColor: myTopBordColor
			lftBordColor: (VGAOrEGA myLftBordColor myTopBordColor)
			rgtBordColor: (VGAOrEGA myRgtBordColor myInsideColor)
			botBordColor: (VGAOrEGA myBotBordColor myInsideColor)
		)
		(invWin
			color: myTextColor
			back: (VGAOrEGA myBotBordColor myInsideColor)
			topBordColor: (VGAOrEGA myBackColor myTopBordColor)
			lftBordColor: (VGAOrEGA myRgtBordColor myTopBordColor)
			rgtBordColor: (VGAOrEGA myInsideColor myBotBordColor)
			botBordColor: (VGAOrEGA myTextColor myBotBordColor)
			insideColor: (VGAOrEGA myInsideColor myBotBordColor)
			topBordColor2: myTextColor
			lftBordColor2: myTextColor
			botBordColor2: (VGAOrEGA myBackColor myTopBordColor)
			rgtBordColor2: (VGAOrEGA myLftBordColor myTopBordColor)
			botBordHgt: 25
		)
		(= numVoices (DoSound NumVoices))
		((= theMusic longSong) owner: self priority: 15 init:)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon4 icon5 icon8 icon7 icon9 icon6
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (VGAOrEGA myBackColor myInsideColor)
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
		)
		(icon5 message: (if (HaveMouse) SHIFTTAB else TAB))
		(theIconBar disable: ICON_HELP)
		(GameControls
			window: gcWin
			add:
				iconOk
				(detailSlider
					theObj: self
					selector: #detailLevel
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: #masterVolume
					yourself:
				)
				speedSlider
				(iconSave
					theObj: self
					selector: #save
					yourself:
				)
				(iconRestore
					theObj: self
					selector: #restore
					yourself:
				)
				(iconRestart
					theObj: self
					selector: #restart
					yourself:
				)
				(iconQuit
					theObj: self
					selector: #quitGame
					yourself:
				)
				(iconAbout
					theObj: self
					selector: #doit
					yourself:
				)
				iconHelp
			helpIconItem: iconHelp
			curIcon: iconRestore
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (VGAOrEGA myRgtBordColor myInsideColor)
		)
		(= howFast 2)
		(self newRoom: SPEED)
	)
	
	(method (doit &tmp thisTime)
		(super doit:)
		(if (!= oldSysTime (= thisTime (GetTime SYSTIME1)))
			(= oldSysTime thisTime)
			(if (> (++ currentSeconds) 59)
				(= currentSeconds 0)
				(if (> (++ currentMinutes) 59)
					(= currentMinutes 0)
					(if (> (++ currentHour) 12)
						(= currentHour 1)
					)
				)
			)
		)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		(mouseDownHandler delete: (ScriptID DEBUG))
		(DisposeScript RANDCYC)
		(if
			(and
				(!= (- (MemoryInfo FreeHeap) 2) (MemoryInfo LargestPtr))
				(Print 0 0
					#button {Who cares} 0
					#button {Debug} 1
				)
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
		(if (cast contains: ego)
			(if (not (ego looper?)) (ego setLoop: stopGroop))
			(EgoHeadMove)
		)
		(redX init: hide: setPri: 15 posn: 1000 -1000)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?)
			(return TRUE)
		)
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(`^q
							(theGame quitGame:)
							(event claimed: TRUE)
						)
					)
				)
			)
		)
	)
	
	(method (quitGame)
		(super
			quitGame: (Print 0 1 #button {quit} 1 #button {continue} 0)
		)
	)
	
	(method (pragmaFail)
		(if (User canInput:)
			(VerbFail)
		)
	)
)

(instance ok of IconItem
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Select this Icon to close this window.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invLook of IconItem
	(properties
		view 901
		loop 2
		cel 0
		cursor 19
		message verbLook
		helpStr {Select this Icon then select an inventory item you'd like a description of.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invHand of IconItem
	(properties
		view 901
		loop 0
		cel 0
		cursor 20
		message verbDo
		helpStr {This allows you to do something to an item.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invHelp of IconItem
	(properties
		view 901
		loop 1
		cel 0
		cursor 29
		message verbHelp
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance invSelect of IconItem
	(properties
		view 901
		loop 4
		cel 0
		cursor ARROW_CURSOR
		helpStr {This allows you to select an item.}
	)
	
	(method (init)
		(self
			highlightColor: 0
			lowlightColor: (VGAOrEGA myBackColor myInsideColor)
		)
		(super init:)
	)
)

(instance icon0 of IconItem
	(properties
		view 900
		loop 0
		cel 0
		cursor 6
		message verbWalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for walking.}
		maskView 900
		maskLoop 10
	)
)

(instance icon1 of IconItem
	(properties
		view 900
		loop 1
		cel 0
		cursor 19
		message verbLook
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for looking.}
		maskView 900
		maskLoop 10
	)
)

(instance icon2 of IconItem
	(properties
		view 900
		loop 2
		cel 0
		cursor 20
		message verbDo
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for doing.}
		maskView 900
		maskLoop 10
		maskCel 1
	)
)

(instance icon3 of IconItem
	(properties
		view 900
		loop 3
		cel 0
		cursor 7
		message verbTalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for talking.}
		maskView 900
		maskLoop 10
		maskCel 2
	)
)

(instance icon4 of IconItem
	(properties
		view 900
		loop 4
		cel 0
		cursor 999
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon shows the currently selected inventory item.}
		maskView 900
		maskLoop 10
		maskCel 3
	)
)

(instance icon5 of IconItem
	(properties
		view 900
		loop 5
		cel 0
		cursor ARROW_CURSOR
		type NULL
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon displays the inventory window.}
		maskView 900
		maskLoop 10
		maskCel 4
	)
	
	(method (select)
		(if (super select: &rest)
			(Inventory showSelf: ego)
		)
	)
)

(instance icon6 of IconItem
	(properties
		view 900
		loop 8
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {I'm not even an icon, i'm just here to take up space.}
		maskView 900
		maskLoop 8
	)
)

(instance icon7 of IconItem
	(properties
		view 900
		loop 6
		cel 0
		cursor 32
		message verbNotebook
		signal (| HIDEBAR RELVERIFY)
		helpStr {** enters the time into your notebook.}
		maskView 900
		maskLoop 10
		maskCel 1
	)
)

(instance icon8 of IconItem
	(properties
		view 900
		loop 7
		cel 0
		cursor ARROW_CURSOR
		message verbHelp
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon displays the control panel.}
		maskView 900
		maskLoop 10
	)
	
	(method (select)
		(if (super select: &rest)
			(theIconBar hide:)
			(GameControls show:)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 900
		loop 9
		cel 0
		cursor 29
		message 6
		signal (| RELVERIFY IMMEDIATE)
		helpStr {This icon tells you about other icons.}
		maskView 900
		maskLoop 10
		maskCel 1
	)
)

(instance DoVerbCode of Code
	(method (doit theVerb theObj &tmp objDesc)
		(= objDesc (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj facingMe: ego)
					(if (theObj lookStr?)
						(Print (theObj lookStr?))
					else
						(VerbFail)
					)
				)
			)
			(else
				(VerbFail)
			)
		)
	)
)

(instance FtrInit of Code
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 90)
		)
		(if (== (theObj actions?) ftrDefault)
			(theObj actions: 0)
		)
	)
)

(instance rm0Sound of Sound
	(properties
		priority 15
	)
)

(instance gcWin of BorderWindow
	(method (open &tmp
			theBevelWid t l r b theColor theMaps bottomColor topColor leftColor rightColor
			thePri i [scoreBuf 15] [scoreRect 4])
		(self
			top: (/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
			left: (/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
			bottom:
				(+
					(CelHigh 947 1 1)
					6
					(/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
				)
			right:
				(+
					151
					(CelWide 947 0 1)
					(/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
				)
			priority: 15
		)
		(super open:)
		(DrawCel 947 0 5
			(+
				(/
					(-
						(- (+ 151 (CelWide 947 0 1)) (+ 4 (CelWide 947 1 1)))
						(CelWide 947 0 5)
					)
					2
				)
				4
				(CelWide 947 1 1)
			)
			3
			15
		)
		(DrawCel 947 1 1 4 3 15)
		(DrawCel 947 1 0 94 38 15)
		(DrawCel 947 1 0 135 38 15)
		(DrawCel 947 0 4 63 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 3 101 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 2 146 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(Graph GShowBits 12 1 15 (+ 151 (CelWide 947 0 1)) 1)
		(= b (+ (= t (+ 46 (CelHigh 947 0 1))) 13))
		(= r
			(+
				(= l (+ 10 (CelWide 947 1 1)))
				(-
					(+ 151 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= thePri 15)
		(= theColor 0)
		(= bottomColor (VGAOrEGA myBotBordColor myInsideColor))
		(= rightColor (VGAOrEGA myRgtBordColor myInsideColor))
		(= leftColor (VGAOrEGA myLftBordColor myTopBordColor))
		(= topColor myTopBordColor)
		(= theBevelWid 3)
		(= theMaps 3)
		(Graph GFillRect t l (+ b 1) (+ r 1) theMaps theColor thePri)
		(= t (- t theBevelWid))
		(= l (- l theBevelWid))
		(= r (+ r theBevelWid))
		(= b (+ b theBevelWid))
		(Graph GFillRect t l (+ t theBevelWid) r theMaps bottomColor thePri)
		(Graph GFillRect (- b theBevelWid) l b r theMaps topColor thePri)
		(= i 0)
		(while (< i theBevelWid)
			(Graph GDrawLine (+ t i) (+ l i) (- b (+ i 1)) (+ l i) rightColor thePri -1)
			(Graph GDrawLine (+ t i)(- r (+ i 1)) (- b (+ i 1)) (- r (+ i 1)) leftColor thePri -1)
			(++ i)
		)
		(Graph GShowBits t l (+ b 1) (+ r 1) 1)
		(Format @scoreBuf 0 2 score possibleScore)
		(TextSize @scoreRect @scoreBuf 999 0)
		(Display
			@scoreBuf
			p_font 999
			p_color (VGAOrEGA myBackColor myTopBordColor)
			p_at
			(+ 10 (CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[scoreRect 3]
					)
					2
				)
			)
			(+ 46 (CelHigh 947 0 1) 3)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 67
		nsTop 37
		signal (| FIXED_POSN RELSEND)
		helpStr {The graphics detail level.}
		sliderView 947
		topValue 3
	)
)

(instance volumeSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 107
		nsTop 37
		signal FIXED_POSN
		helpStr {Overall sound volume.}
		sliderView 947
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 147
		nsTop 37
		signal (| FIXED_POSN RELSEND)
		helpStr {*** The speed the actors move.}
		sliderView 947
		bottomValue 15
	)
	
	(method (doit newSpeed)
		(if argc
			(theGame egoMoveSpeed: newSpeed)
			(ego
				moveSpeed: (theGame egoMoveSpeed?)
				cycleSpeed: (theGame egoMoveSpeed?)
			)
		)
		(theGame egoMoveSpeed?)
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Save your game?}
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 947
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Restore a previously saved game?}
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 947
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Restart the Game?}
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {*** Quit the game?}
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		nsLeft 34
		nsTop 106
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Information about the game.}
	)
)

(instance iconHelp of IconItem
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 8
		nsTop 106
		cursor 29
		message 6
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
	)
)

(instance iconOk of IconItem
	(properties
		view 947
		loop 8
		cel 0
		nsLeft 8
		nsTop 86
		message 8
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Quit this menu.}
	)
)

(instance talkerIcon of DCIcon)
