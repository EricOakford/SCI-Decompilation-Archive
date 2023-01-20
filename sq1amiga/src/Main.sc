;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include sci.sh)
(use Intrface)
(use ColorInit)
(use RegionPath)
(use SQRoom)
(use SQEgo)
(use Elevator)
(use PMouse)
(use SlideIcon)
(use BordWind)
(use IconBar)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use DCIcon)
(use Grooper)
(use Sound)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	sq1 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	SteppedOn 5
	Btst 6
	Bset 7
	Bclr 8
	InitEgoHead 9
	EgoDead 10
	SolvePuzzle 11
	DoDisplay 12
	Face 13
	DoStatus 14
	NoResponse 15
	Babble 16
	FindColor 17
	SpiderList 18
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
	showStyle =  7
	aniInterval
	theCursor
	normalCursor =  999
	waitCursor =  20
	userFont =  1
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  1
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
	firstSaidHandler
	useObstacles =  1
	theMenuBar
	theIconBar
	mouseX
	mouseY
	keyDownHandler
	mouseDownHandler
	directionHandler
	gameCursor
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
	gameCode =  1234
	curReg
	noCursor =  1
	cursorType
	numColors
	numVoices
	restartRoom
	global108
	global109
	global110
	global111
	spiderPoly
	oldCursor
	saveCursorX
	saveCursorY
	eHead
	sGrooper
	gameFlags
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
	colBlack
	colWhite
	colDRed
	colLRed
	colVLRed
	colDYellow
	colYellow
	colLYellow
	colLGreen
	colVLGreen
	colDBlue
	colMagenta
	colCyan
	colLED
	colLaser
	myTextColor14
	myTextColor15
	myTextColor16
	myTextColor17
	myTextColor18
	colBlue
	colDGreen
	global151
	colLBlue
	colVLBlue
	theMusic2
	colGray1
	colGray2
	colGray3
	colGray4
	colGray5
	colLCyan
	colLMagenta
	zapCount
	droidX
	droidY
	buckazoids =  3
	currentFloor =  1
	global167
	scientistState
	alesDrunk
	bandInBar
	enter41
	selfDestructTimer
	sarienFloor
	slotsCel
	insertedBuckazoids
	buckazoidsInDust =  12
	soundFx
	spiderTimer =  2000
	spiderX
	spiderY
	spiderRoom
	bridgeCrossings
	thirstTimer =  7000
	selfDestructCode
	logging
	deltaurSector
	debugging
	sittingAtBar
	shipDestination
)
(procedure (NormalEgo param1 param2 param3 &tmp temp0)
	(= temp0 0)
	(if (> argc 0)
		(ego loop: param1)
		(if (> argc 1)
			(ego view: param2)
			(if (> argc 2) (= temp0 param3))
		)
	)
	(if (not temp0) (= temp0 60))
	(ego
		normal: 1
		moveHead: 1
		setLoop: -1
		setLoop: stopGroop
		setPri: -1
		setMotion: 0
		setCycle: StopWalk temp0
		setStep: 4 2
		illegalBits: 0
		ignoreActors: 0
		ignoreHorizon: 1
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
)

(procedure (HandsOff &tmp theIconBarCurIcon)
	(User canControl: 0 canInput: 0)
	(ego setMotion: 0)
	(= theIconBarCurIcon (theIconBar curIcon?))
	(theIconBar disable: 7 6 5 4 3 2 1 0)
	(theIconBar curIcon: theIconBarCurIcon)
	(if (not (HaveMouse))
		(= saveCursorX ((User curEvent?) x?))
		(= saveCursorY ((User curEvent?) y?))
		(theGame setCursor: waitCursor 1 310 180)
	else
		(theGame setCursor: waitCursor 1)
	)
	(if pMouse (pMouse stop:))
)

(procedure (HandsOn)
	(User canControl: 1 canInput: 1)
	(theIconBar enable: 0 1 2 3 4 5 6 7)
	(ego
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: 6)
	)
	(if (not (HaveMouse))
		(theGame
			setCursor: ((theIconBar curIcon?) cursor?) 1 saveCursorX saveCursorY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
)

(procedure (HaveMem param1)
	(return (u> (MemoryInfo 1) param1))
)

(procedure (SteppedOn param1 param2)
	(return (if (& (param1 onControl: 1) param2) (return 1) else 0))
)

(procedure (Btst param1)
	(return
		(&
			[gameFlags (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
)

(procedure (Bset param1 &tmp temp0)
	(= temp0 (Btst param1))
	(= [gameFlags (/ param1 16)]
		(|
			[gameFlags (/ param1 16)]
			(>> $8000 (mod param1 16))
		)
	)
	(return temp0)
)

(procedure (Bclr param1 &tmp temp0)
	(= temp0 (Btst param1))
	(= [gameFlags (/ param1 16)]
		(&
			[gameFlags (/ param1 16)]
			(~ (>> $8000 (mod param1 16)))
		)
	)
	(return temp0)
)

(procedure (InitEgoHead param1 &tmp temp0)
	(= temp0 0)
	(if argc (= temp0 param1) else (= temp0 60))
	((= eHead egoHead) init: ego view: temp0 cycleSpeed: 40)
)

(procedure (EgoDead param1 param2 param3 param4 &tmp temp0 temp1 temp2 [temp3 300])
	(asm
		pushi    #eachElementDo
		pushi    1
		pushi    154
		lag      sounds
		send     6
		lap      argc
		bnt      code_1cd4
		lap      param1
		sat      temp0
		lap      param2
		sat      temp1
		lap      param3
		sat      temp2
		pushi    1
		lea      @temp3
		push    
		&rest    param4
		callk    Format,  2
		jmp      code_1ced
code_1cd4:
		ldi      944
		sat      temp0
		ldi      0
		sat      temp1
		ldi      0
		sat      temp2
		pushi    3
		lea      @temp3
		push    
		pushi    0
		pushi    29
		callk    Format,  6
code_1ced:
		pushi    #number
		pushi    1
		pushi    900
		pushi    97
		pushi    1
		pushi    127
		pushi    6
		pushi    1
		pushi    1
		pushi    102
		pushi    1
		pushi    1
		pushi    42
		pushi    0
		lag      theMusic
		send     28
		pushi    #setCursor
		pushi    2
		lsg      normalCursor
		pushi    1
		lofsa    sq1
		send     8
code_1d13:
		pushi    16
		lea      @temp3
		push    
		pushi    30
		pushi    1
		pushi    81
		lofsa    {Restore}
		push    
		pushi    1
		pushi    81
		lofsa    {Restart}
		push    
		pushi    2
		pushi    81
		lofsa    {____Quit____}
		push    
		pushi    3
		pushi    82
		lst      temp0
		lst      temp1
		lst      temp2
		calle    Print,  32
		push    
		dup     
		ldi      1
		eq?     
		bnt      code_1d50
		pushi    #restore
		pushi    0
		lag      theGame
		send     4
		jmp      code_1d6f
code_1d50:
		dup     
		ldi      2
		eq?     
		bnt      code_1d61
		pushi    #restart
		pushi    0
		lag      theGame
		send     4
		jmp      code_1d6f
code_1d61:
		dup     
		ldi      3
		eq?     
		bnt      code_1d6f
		ldi      1
		sag      quit
		jmp      code_1d73
code_1d6f:
		toss    
		jmp      code_1d13
code_1d73:
		ret     
	)
)

(procedure (SolvePuzzle param1 param2)
	(if (not (Btst param2))
		(theGame changeScore: param1)
		(Bset param2)
		(pointsSound play:)
	)
)

(procedure (DoDisplay theTheColWhite &tmp theTheTheColWhite theTheTheColWhite_2 temp2 theTheTheColWhite_3 theTheTheColWhite_4 theTheTheColWhite_5 theColWhite theColBlack temp8)
	(return
		(if (== argc 1)
			(Display 0 30 108 [theTheColWhite 0])
			(if (not (HaveMouse)) (theGame setCursor: oldCursor 1))
		else
			(= theTheTheColWhite_4 (= theTheTheColWhite_5 -1))
			(= theTheTheColWhite 0)
			(= theTheTheColWhite_2 68)
			(= temp2 69)
			(= theTheTheColWhite_3 320)
			(= theColWhite colWhite)
			(= theColBlack colBlack)
			(= temp8 1)
			(while (< temp8 argc)
				(switch [theTheColWhite temp8]
					(30
						(= theTheTheColWhite [theTheColWhite (++ temp8)])
					)
					(33
						(= temp2
							(+
								(= theTheTheColWhite_2 [theTheColWhite (++ temp8)])
								1
							)
						)
					)
					(70
						(= theTheTheColWhite_3 [theTheColWhite (++ temp8)])
					)
					(67
						(= theTheTheColWhite_4 [theTheColWhite (++ temp8)])
						(= theTheTheColWhite_5 [theTheColWhite (++ temp8)])
					)
					(28
						(= theColWhite [theTheColWhite (++ temp8)])
					)
					(29
						(= theColBlack [theTheColWhite (++ temp8)])
					)
				)
				(++ temp8)
			)
			(if (not (HaveMouse))
				(= oldCursor theCursor)
				(theGame setCursor: 69 1)
			)
			(= temp8
				(Display
					[theTheColWhite 0]
					dsCOORD
					theTheTheColWhite_4
					theTheTheColWhite_5
					dsCOLOR
					theColBlack
					dsWIDTH
					theTheTheColWhite_3
					dsALIGN
					theTheTheColWhite
					dsFONT
					temp2
					dsSAVEPIXELS
				)
			)
			(Display
				[theTheColWhite 0]
				dsCOORD
				theTheTheColWhite_4
				theTheTheColWhite_5
				dsCOLOR
				theColWhite
				dsWIDTH
				theTheTheColWhite_3
				dsALIGN
				theTheTheColWhite
				dsFONT
				theTheTheColWhite_2
			)
			(return temp8)
		)
	)
)

(procedure (Face param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3)
	(= temp3 0)
	(if (IsObject param2)
		(= temp1 (param2 x?))
		(= temp2 (param2 y?))
		(if (== argc 3) (= temp3 param3))
	else
		(= temp1 param2)
		(= temp2 param3)
		(if (== argc 4) (= temp3 param4))
	)
	(= temp0
		(GetAngle (param1 x?) (param1 y?) temp1 temp2)
	)
	(param1
		setHeading: temp0 (if (IsObject temp3) temp3 else 0)
	)
)

(procedure (DoStatus param1)
	(StrCpy @param1 {__Space Quest I - The Sarien Encounter})
	(DrawStatus
		@param1
		0
		(FindColor colGray4 colGray1 colGray1)
	)
)

(procedure (NoResponse &tmp newList [temp1 2] userCurEvent temp4 [temp5 5])
	(= oldCursor (theGame setCursor: 69 1))
	(= userCurEvent (User curEvent?))
	(redX
		x: (userCurEvent x?)
		y: (+ 300 (userCurEvent y?))
		z: 300
		show:
	)
	((= newList (List new:)) add: redX)
	(Animate (newList elements?) 1)
	(Animate (cast elements?) 0)
	(= temp4 (GetTime))
	(while (< (Abs (- temp4 (GetTime))) 40)
		(breakif
			(OneOf ((= userCurEvent (Event new:)) type?) 4 1)
		)
		(userCurEvent dispose:)
	)
	(if (IsObject userCurEvent) (userCurEvent dispose:))
	(redX hide: posn: 1000 -1000)
	(Animate (newList elements?) 1)
	(newList delete: redX dispose:)
	(theGame setCursor: oldCursor)
)

(procedure (Babble param1 param2 param3 &tmp [temp0 500])
	(if (u< param2 1000)
		(GetFarText param2 param3 @temp0)
	else
		(StrCpy @temp0 param2)
	)
	(babbleIcon
		view: param1
		cycleSpeed: (* (+ howFast 1) 4)
	)
	(if (u< param2 1000)
		(Print @temp0 &rest 82 babbleIcon 0 0)
	else
		(Print @temp0 param3 &rest 82 babbleIcon 0 0)
	)
)

(procedure (FindColor param1 param2 param3)
	(if (< param1 0) (= param1 0))
	(if (> param1 255) (= param1 255))
	(if (< param2 0) (= param2 0))
	(if (and (> param2 15) (== numColors 16))
		(= param2 15)
	)
	(if (> param2 31) (= param2 31))
	(if (< param3 0) (= param3 0))
	(if (> param3 31) (= param3 31))
	(if (== numColors 256) (return param1))
	(if (== numColors 16) (return param2))
	(return (if (== numColors 32) (return param3) else 0))
)

(procedure (localproc_20ae param1 param2 param3 &tmp [temp0 30] [temp30 30] temp60 [temp61 100] [temp161 19] [temp180 30] [temp210 20])
	(= oldCursor (theGame setCursor: 69 1))
	(if (not (StrLen @sysLogPath))
		(StrCpy @temp30 {})
		(GetInput
			@temp30
			30
			{Enter drive & directory for new response log...}
		)
		(StrCpy @sysLogPath @temp30)
	)
	(StrCpy @temp0 @sysLogPath)
	(StrCat @temp0 {newresp.log})
	(if (IsObject param2)
		(StrCpy @temp180 (param2 name?))
	else
		(StrCpy @temp180 {Default Response})
	)
	(Format
		@temp61
		0
		31
		curRoomNum
		@temp180
		((theIconBar curIcon?) helpStr?)
	)
	(if (== param1 4)
		(StrCat @temp61 ((theIconBar curInvIcon?) name?))
		(StrCat
			@temp61
			(Format @temp161 {\0D\n(switch theItem (%d} param3)
		)
		(StrCat @temp61 {\0D\n})
	)
	(StrCpy @temp210 {})
	(if (== param1 4)
		(StrCpy @temp210 {verbUse})
	else
		(switch ((theIconBar curIcon?) cursor?)
			(19
				(StrCpy @temp210 {verbLook})
			)
			(20 (StrCpy @temp210 {verbDo}))
			(7 (StrCpy @temp210 {verbTalk}))
			(30
				(StrCpy @temp210 {verbSmell})
			)
			(31
				(StrCpy @temp210 {verbTaste})
			)
		)
	)
	(Format @temp161 0 32 @temp210)
	(StrCat @temp61 @temp161)
	(StrCat @temp61 {\t\t\t(Print\0D\n})
	(StrCat @temp61 {\t\t\t\t"})
	(= temp60 (FileIO 0 @temp0 0))
	(FileIO 6 temp60 @temp61)
	(repeat
		(= temp61 0)
		(GetInput @temp61 50 {doVerb message:})
		(if (== (StrLen @temp61) 0)
			(FileIO 6 temp60 {\0D\n\t\t\t\t"\0D\n})
			(FileIO 6 temp60 {\t\t\t)\0D\n})
			(FileIO 6 temp60 {\t\t)\0D\n})
			(FileIO 6 temp60 {\t\t\0D\n\0D\n})
			(FileIO 1 temp60)
			(break)
		)
		(FileIO 6 temp60 @temp61)
		(FileIO 6 temp60 {\0D\n\t\t\t\t})
	)
	(theGame setCursor: oldCursor)
)

(instance ego of SQEgo
	(properties
		description {Roger Wilco}
		sightAngle 180
		lookStr {It's you, Roger Wilco, janitor sub-extraordinaire.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(5 (Print 0 0))
			(3 (Print 0 1))
			(11 (Print 0 2))
			(12 (Print 0 3))
			(4
				(switch theItem
					(5
						(cond 
							((curRoom script?) (Print 0 4))
							(
							(OneOf curRoomNum 37 18 19 20 21 22 23 24 25 26 27) (curRoom setScript: (ScriptID 704 4)))
							(else (Print 0 5))
						)
					)
					(13 (Print 0 6))
					(11
						(if (== curRoomNum 51) (Print 0 7) else (Print 0 8))
					)
					(4 (Print 0 9))
					(else 
						(switch (Random 1 5)
							(1 (Print 0 10))
							(2 (Print 0 11))
							(3 (Print 0 12))
							(4 (Print 0 13))
							(5 (Print 0 14))
						)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoHead of Head
	(properties
		description {Roger Wilco}
		lookStr {This is your brain take-out container.}
		view 60
	)
	
	(method (doVerb theVerb theItem)
		(ego doVerb: theVerb theItem)
	)
)

(instance longSong of Sound
	(properties)
)

(instance longSong2 of Sound
	(properties)
)

(instance invSound of Sound
	(properties
		flags $0001
	)
)

(instance soundEffects of Sound
	(properties)
	
	(method (check)
		(DoSound 17 self)
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(cond 
				((> (self loop?) 1)
					(self loop: (- (self loop?) 1))
					(DoSound sndVOLUME self 0)
				)
				((IsObject client) (client cue: self))
			)
		)
	)
)

(instance pointsSound of Sound
	(properties
		flags $0001
		number 901
	)
)

(instance stopGroop of Grooper
	(properties)
	
	(method (doit)
		(if
			(and
				(IsObject (ego cycler?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego view: ((ego cycler?) vWalking?))
		)
		(super doit: &rest)
	)
)

(instance babbleIcon of DCIcon
	(properties)
	
	(method (init)
		((= cycler (RandCycle new:)) init: self 20)
	)
)

(instance sq1KeyDownHandler of EventHandler
	(properties)
)

(instance sq1MouseDownHandler of EventHandler
	(properties)
)

(instance sq1DirectionHandler of EventHandler
	(properties)
)

(instance sq1 of Game
	(properties)
	
	(method (init &tmp temp0)
		(= debugging 0)
		(ColorInit)
		(= systemWindow sq1Win)
		(= sGrooper stopGroop)
		(= deltaurSector (Random 1 20))
		(= useSortedFeatures 1)
		(= spiderPoly (SpiderList add:))
		StopWalk
		Polygon
		PolyPath
		SQRoom
		IconBar
		Inv
		(ScriptID 982)
		RandCycle
		(LoadMany 136 6 997 999 19 30 20 7 31)
		(super init: &rest)
		(StrCpy @sysLogPath {})
		(= doVerbCode sq1DoVerbCode)
		(= ftrInitializer sq1FtrInit)
		((= keyDownHandler sq1KeyDownHandler) add:)
		((= mouseDownHandler sq1MouseDownHandler) add:)
		((= directionHandler sq1DirectionHandler) add:)
		(= pMouse PseudoMouse)
		(self egoMoveSpeed: 5 setSpeed: 0)
		((= ego ego)
			_head: (= eHead egoHead)
			moveSpeed: (self egoMoveSpeed?)
			cycleSpeed: (self egoMoveSpeed?)
		)
		(eHead client: ego)
		(User canControl: 0 canInput: 0 alterEgo: ego)
		((= theMusic longSong) owner: self init: flags: 1)
		((= theMusic2 longSong2) owner: self init:)
		(= soundFx soundEffects)
		(= version {x.yyy})
		(= waitCursor 997)
		(= possibleScore 201)
		(= userFont 4)
		(= numVoices (DoSound sndDISPOSE))
		(= numColors (Graph grGET_COLOURS))
		(sq1Win
			color: colBlack
			back: (FindColor colGray4 colGray1 colGray2)
			topBordColor: colWhite
			lftBordColor: (FindColor colGray5 colWhite colWhite)
			rgtBordColor: (FindColor colGray3 colGray2 colGray1)
			botBordColor: (FindColor colGray2 colGray2 colGray1)
		)
		(gcWin
			color: colBlack
			back: (FindColor colGray4 colGray1 colGray2)
			topBordColor: colWhite
			lftBordColor: (FindColor colGray5 colWhite colWhite)
			rgtBordColor: (FindColor colGray3 colGray2 colGray2)
			botBordColor: colGray2
		)
		(invWin
			topBordHgt: 4
			botBordHgt: 27
			color: colBlack
			priority: -1
			back: (FindColor colGray2 colGray1 colGray2)
			topBordColor: (FindColor colGray4 colWhite colWhite)
			lftBordColor: (FindColor colGray3 colWhite colWhite)
			rgtBordColor: (FindColor colGray1 colGray2 colGray1)
			botBordColor: (FindColor colGray1 colGray2 colGray1)
			insideColor: (FindColor colGray1 colGray2 colBlack)
			topBordColor2: (FindColor colBlack colBlack colGray1)
			lftBordColor2: (FindColor colBlack colBlack colGray1)
			botBordColor2: (FindColor colGray4 colWhite colWhite)
			rgtBordColor2: (FindColor colGray5 colBlack colWhite)
		)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor colBlack
			eachElementDo: #lowlightColor (FindColor colGray4 colGray1 colGray2)
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
		)
		(icon5 message: (if (HaveMouse) 3840 else 9))
		(Inv
			init:
			add:
				Cartridge
				keyCard
				Gadget
				Survival_Kit
				Knife
				Dehydrated_Water
				Broken_Glass
				Rock
				Orat_Part
				Skimmer_Key
				buckazoid
				Jetpack
				Pulseray_Laser_Pistol
				Grenade
				Remote
				Widget
				Plant
				Bar_Coupon
				Droids-B-Us_coupon
				Sarien_ID_Card
				invLook
				invHand
				invSelect
				invHelp
				ok
			eachElementDo: #highlightColor (FindColor colBlack colBlack colGray2)
			eachElementDo: #lowlightColor (FindColor colGray1 colGray2 colBlack)
			eachElementDo: #init
			window: invWin
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
		(GameControls
			window: gcWin
			add:
				iconOk
				(detailSlider
					theObj: self
					selector: 292
					topValue: 3
					yStep: (- 3 howFast)
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: 380
					topValue: (if (> numVoices 1) 15 else 1)
					bottomValue: 0
					yStep: (- 3 howFast)
					yourself:
				)
				(speedSlider
					theObj: speedORama
					selector: 60
					yStep: (- 3 howFast)
					yourself:
				)
				(iconSave theObj: self selector: 78 yourself:)
				(iconRestore theObj: self selector: 79 yourself:)
				(iconRestart theObj: self selector: 104 yourself:)
				(iconQuit theObj: self selector: 103 yourself:)
				(iconAbout
					theObj: (ScriptID 811 0)
					selector: 60
					yourself:
				)
				iconHelp
			eachElementDo: #highlightColor colBlack
			eachElementDo: #lowlightColor (FindColor colGray3 colGray2 colGray1)
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(buckazoid owner: ego)
		(= restartRoom (if (GameIsRestarting) 4 else 1))
		(self newRoom: 803)
	)
	
	(method (replay)
		(Palette 4 0 255 100)
		(super replay:)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		(sounds eachElementDo: #perform soundReset)
		((ScriptID 801) doit: roomNum)
		(if
			(and
				debugging
				logging
				(!= (- (MemoryInfo 1) 2) (MemoryInfo 0))
				(Print 0 17 #button {Who cares} 0 #button {Debug} 1)
			)
			(SetDebug)
		)
		(redX init: hide: setPri: 15 posn: 1000 -1000)
		(if debugOn (SetDebug))
		(cond 
			((OneOf roomNum 3 4 5 6 7 8 9 10 11 12 13 103) Elevator (ScriptID 700))
			((OneOf roomNum 37 18 19 20 21 22 23 24 25 26 27) (ScriptID 704))
			(
			(OneOf roomNum 54 55 57 58 59 60 61 62 63 64 65 66 67) Elevator RegionPath (ScriptID 703) (= curReg 703))
			(else 0)
		)
		(if
			(OneOf
				roomNum
				3
				35
				40
				41
				42
				43
				45
				46
				58
				59
				60
				61
				62
				63
				64
				66
				68
			)
			RandCycle
		)
		(if (and debugging (not (OneOf roomNum 999)))
			((ScriptID 800) init:)
		)
		(super startRoom: roomNum)
		(if (cast contains: ego)
			(if
				(and
					(ego normal?)
					(not ((ego cycler?) isKindOf: StopWalk))
				)
				(ego
					setCycle:
						StopWalk
						(switch (ego view?)
							(0 60)
							(1 61)
							(416 65)
							(2 62)
						)
				)
			)
			(if (not (ego looper?)) (ego setLoop: stopGroop))
			(InitEgoHead (egoHead view?))
		)
	)
	
	(method (restart param1)
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(if
			(or
				(and argc (== param1 0))
				(Print
					0
					16
					#button
					{Of course I'm sure!}
					1
					#button
					{Changed My Mind.\nLet's Play!}
					0
					#icon
					babbleIcon
					0
					0
				)
			)
			(super restart:)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(return
			(switch (event type?)
				(evKEYBOARD
					(switch (event message?)
						(KEY_TAB
							(if (not (& (icon5 signal?) $0004))
								(Inv showSelf: ego)
							)
						)
						(KEY_SHIFTTAB
							(if (not (& (icon5 signal?) $0004))
								(Inv showSelf: ego)
							)
						)
						(KEY_CONTROL
							(theGame quitGame:)
							(event claimed: 1)
						)
						(KEY_F1 (GameControls show:))
						(KEY_F2
							(cond 
								((theGame masterVolume:) (theGame masterVolume: 0))
								((> numVoices 1) (theGame masterVolume: 15))
								(else (theGame masterVolume: 1))
							)
							(event claimed: 1)
						)
						(KEY_F5
							(theGame save:)
							(event claimed: 1)
						)
						(KEY_F7
							(theGame restore:)
							(event claimed: 1)
						)
						(KEY_F9
							(theGame restart:)
							(event claimed: 1)
						)
						(KEY_ALT_n
							(if (HaveMem 1536)
								((ScriptID 952) doit: @sysLogPath 0)
							else
								(Print 0 18)
							)
							(event claimed: 1)
						)
						(else 
							(if debugging ((ScriptID 800) handleEvent: event))
						)
					)
				)
				(evMOUSEBUTTON
					(if debugging ((ScriptID 800) handleEvent: event))
				)
			)
		)
	)
	
	(method (quitGame)
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(super
			quitGame:
				(Print
					0
					15
					#button
					{Let me out of here!\n(Quit)}
					1
					#button
					{I don't know WHAT\nI was thinking.\nLet's Play!}
					0
					#icon
					babbleIcon
					0
					0
				)
		)
	)
	
	(method (pragmaFail)
		(if (User canInput:)
			(if (and debugging (Btst 0))
				(localproc_20ae)
			else
				(NoResponse)
			)
		)
	)
)

(instance soundReset of Code
	(properties)
	
	(method (doit param1)
		(if
		(and (== (param1 prevSignal?) -1) (param1 number?))
			(param1 number: 0)
		)
	)
)

(instance speedORama of Code
	(properties)
	
	(method (doit param1)
		(if argc
			(theGame egoMoveSpeed: param1)
			(if (User canControl:)
				(ego
					moveSpeed: (theGame egoMoveSpeed?)
					cycleSpeed: (theGame egoMoveSpeed?)
				)
			)
		)
		(theGame egoMoveSpeed?)
	)
)

(instance ok of IconI
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		cursor 999
		signal $0043
		helpStr {Select this Icon to close this window.}
	)
	
	(method (init)
		(self
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray1)
		)
		(super init:)
	)
)

(instance invLook of IconI
	(properties
		view 901
		loop 2
		cel 0
		cursor 19
		message 2
		helpStr {Select this Icon then select an inventory item you'd like a description of.}
	)
	
	(method (init)
		(self
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray1)
		)
		(super init:)
	)
)

(instance invHand of IconI
	(properties
		view 901
		loop 0
		cel 0
		cursor 20
		message 3
		helpStr {This allows you to do something to an item.}
	)
	
	(method (init)
		(self
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray2)
		)
		(super init:)
	)
)

(instance invHelp of IconI
	(properties
		view 901
		loop 1
		cel 0
		cursor 29
		message 6
	)
	
	(method (init)
		(self
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray2)
		)
		(super init:)
	)
)

(instance invSelect of IconI
	(properties
		view 901
		loop 4
		cel 0
		cursor 999
		helpStr {This allows you to select an item.}
	)
	
	(method (init)
		(self
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray2)
		)
		(super init:)
	)
)

(class RInvItem of InvI
	(properties
		view 0
		loop 0
		cel 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		state $0000
		cursor 999
		type $4000
		message 4
		modifiers $0000
		signal $0000
		helpStr 0
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		description 0
		owner 0
		script 0
		value 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4
				(if (== (Inv at: theItem) self)
					(Print 0 19)
				else
					(Print view theItem 0)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance Cartridge of RInvItem
	(properties
		view 551
		cursor 1
		signal $0002
		description {It's a cartridge from the Arcada's Data Archive.}
		owner 3
	)
)

(instance keyCard of RInvItem
	(properties
		view 550
		cursor 2
		signal $0002
		description {This keycard fits an electronic lock someplace on the Arcada.}
		owner 5
	)
)

(instance Gadget of RInvItem
	(properties
		view 552
		cursor 3
		signal $0002
		description {This is some sort of gadget. You're not sure what it does exactly, but it has a switch.}
		owner 11
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(cond 
					((curRoom script?) (Print 0 20))
					((Btst 48)
						(Bclr 48)
						(invSound number: 902 loop: 1 flags: 1 play:)
						(Print 0 21)
					)
					(else
						(Bset 48)
						(invSound number: 902 loop: 1 flags: 1 play:)
						(Print 0 22)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance Rock of RInvItem
	(properties
		view 557
		cel 1
		cursor 10
		signal $0002
		description {It's the cone shaped tip of a stalagmite.}
		owner 30
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(cond 
					((curRoom script?) (Print 0 23))
					((== cel 1)
						(Print 0 24)
						(Bclr 70)
						(ego get: 16)
						(self cel: 0)
						(Inv hide: showSelf: ego)
					)
					(else (Print 0 25))
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance Survival_Kit of RInvItem
	(properties
		view 553
		cursor 4
		signal $0002
		description {A Survival Kit}
		owner 18
		name "Survival Kit"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(cond 
					((curRoom script?) (Print 0 20))
					((Btst 61) (Print 0 26))
					(else
						(Bset 61)
						(Print 0 27)
						(ego get: 5)
						(ego get: 4)
						(Inv hide: showSelf: ego)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance Knife of RInvItem
	(properties
		view 554
		cursor 5
		signal $0002
		description {It's a Xenon Army knife}
	)
)

(instance Dehydrated_Water of RInvItem
	(properties
		view 555
		cursor 8
		signal $0002
		description {The can label says "Pelvitron Dehydrated Water (H2) - All you add is air! Makes 10 gallons! Caution - Do not attempt to open or rupture container! Misuse could result in personal injury and/or flash flooding."}
		name "Dehydrated Water"
	)
)

(instance Broken_Glass of RInvItem
	(properties
		view 556
		cursor 9
		signal $0002
		description {It's that highly reflective piece of broken cockpit glass.}
		owner 18
		name "Broken Glass"
	)
)

(instance Orat_Part of RInvItem
	(properties
		view 558
		cursor 11
		signal $0002
		description {This cute little item is an Orat part - you're not sure what part though.}
		owner 28
		name "Orat Part"
	)
)

(instance Skimmer_Key of RInvItem
	(properties
		view 559
		cursor 12
		signal $0002
		description {This key operates the skimmer.}
		owner 35
		name "Skimmer Key"
	)
)

(instance buckazoid of RInvItem
	(properties
		view 560
		cursor 13
		signal $0002
		description {buckazoid}
	)
	
	(method (show)
		(= view (if (>= buckazoids 3) 560 else 574))
		(super show: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(Printf
					0
					28
					buckazoids
					(if (== buckazoids 1) {.} else {s.})
				)
			)
			(4
				(= view 560)
				(super doVerb: theVerb theItem)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance Keronian_Ale of RInvItem
	(properties
		view 561
		cursor 25
		signal $0002
		description {Ummm! Keronian Ale!}
		owner 43
		name "Keronian Ale"
	)
)

(instance Jetpack of RInvItem
	(properties
		view 562
		cursor 14
		signal $0002
		description {This is a used jetpack. Real used.}
		owner 41
	)
)

(instance Pulseray_Laser_Pistol of RInvItem
	(properties
		view 563
		cursor 15
		signal $0002
		description {It's a pulseray laser pistol. Remember, this isn't a play toy!}
		owner 58
		name "Pulseray Laser Pistol"
	)
)

(instance Grenade of RInvItem
	(properties
		view 564
		cursor 16
		signal $0002
		description {You have a small innocuous looking grenade.}
		owner 58
	)
)

(instance Remote of RInvItem
	(properties
		view 565
		cursor 17
		signal $0002
		description {This is a small single function remote control.}
		owner 64
	)
)

(instance Widget of RInvItem
	(properties
		view 570
		cursor 18
		signal $0002
		description {It's a genuine Widget. You're not sure what it does but it's heavy, it looks cool, and it might be magnetic. Please keep this away from the game disks!}
		owner 7
	)
)

(instance Plant of RInvItem
	(properties
		view 571
		cursor 21
		signal $0002
		description {This is simply a piece of sticky, stinking, rotting, plant.}
		owner 19
	)
)

(instance Bar_Coupon of RInvItem
	(properties
		view 572
		cursor 22
		signal $0002
		description {Hey kids! This bar coupon is good for 5 bucakzoids and a free Keronian Ale!}
		name "Bar Coupon"
	)
)

(instance Droids-B-Us_coupon of RInvItem
	(properties
		view 573
		cursor 23
		signal $0002
		description {This coupon gives you a 20% discount at a Droids-B-Us near you! How helpful! Those suckers have the highest droid prices in this universe.}
		name "Droids-B-Us coupon"
	)
)

(instance Sarien_ID_Card of RInvItem
	(properties
		view 569
		cursor 24
		signal $0002
		description {This is a Sarien ID card. The name on the card is Butston Freem. You wonder if this is a common Sarien name (and if it is, you're glad you're not Sarien).}
		owner 57
		name "Sarien ID Card"
	)
)

(instance redX of View
	(properties
		view 903
	)
)

(instance icon0 of IconI
	(properties
		view 900
		loop 0
		cel 0
		cursor 6
		message 1
		signal $0041
		helpStr {This icon is for walking.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
)

(instance icon1 of IconI
	(properties
		view 900
		loop 1
		cel 0
		cursor 19
		message 2
		signal $0041
		helpStr {This icon is for looking.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
)

(instance icon2 of IconI
	(properties
		view 900
		loop 2
		cel 0
		cursor 20
		message 3
		signal $0041
		helpStr {This icon is for doing.}
		maskView 900
		maskLoop 14
	)
)

(instance icon3 of IconI
	(properties
		view 900
		loop 3
		cel 0
		cursor 7
		message 5
		signal $0041
		helpStr {This icon is for talking.}
		maskView 900
		maskLoop 14
		maskCel 3
	)
)

(instance icon4 of IconI
	(properties
		view 900
		loop 4
		cel 0
		cursor 999
		message 4
		signal $0041
		helpStr {This window displays the current inventory item.}
		maskView 900
		maskLoop 14
		maskCel 4
	)
)

(instance icon5 of IconI
	(properties
		view 900
		loop 5
		cel 0
		cursor 999
		type $0000
		message 0
		signal $0043
		helpStr {This icon brings up the inventory window.}
		maskView 900
		maskLoop 14
		maskCel 2
	)
	
	(method (select)
		(if (super select:) (Inv showSelf: ego))
	)
)

(instance icon6 of IconI
	(properties
		view 900
		loop 10
		cel 0
		cursor 30
		message 12
		signal $0041
		helpStr {This icon is for smelling.}
		maskView 900
		maskLoop 14
	)
)

(instance icon7 of IconI
	(properties
		view 900
		loop 11
		cel 0
		cursor 31
		message 11
		signal $0041
		helpStr {This icon is for tasting.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
)

(instance icon8 of IconI
	(properties
		view 900
		loop 7
		cel 0
		cursor 999
		message 8
		signal $0043
		helpStr {This icon brings up the control panel.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
	
	(method (select)
		(if (super select:)
			(theIconBar hide:)
			(GameControls show:)
		)
	)
)

(instance icon9 of IconI
	(properties
		view 900
		loop 9
		cel 0
		cursor 29
		message 6
		signal $0003
		helpStr {This icon tells you about other icons.}
		maskView 900
		maskLoop 14
	)
)

(instance sq1DoVerbCode of Code
	(properties)
	
	(method (doit param1 param2 param3 &tmp temp0)
		(= temp0 (param2 description?))
		(switch param1
			(2
				(if (param2 facingMe: ego)
					(cond 
						((param2 lookStr?) (Print (param2 lookStr?)))
						((not (Btst 0)) (NoResponse))
						(else (localproc_20ae param1 param2))
					)
				)
			)
			(else 
				(if (not (Btst 0))
					(NoResponse)
				else
					(localproc_20ae param1 param2 param3)
				)
			)
		)
	)
)

(instance sq1FtrInit of Code
	(properties)
	
	(method (doit param1)
		(if (== (param1 sightAngle?) 26505)
			(param1 sightAngle: 90)
		)
		(if (== (param1 actions?) 26505) (param1 actions: 0))
	)
)

(instance sq1Win of BorderWindow
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(if (not (HaveMouse)) (theGame setCursor: oldCursor 1))
	)
	
	(method (open)
		(if (not (HaveMouse))
			(= oldCursor theCursor)
			(theGame setCursor: 69 1)
		)
		(super open: &rest)
	)
)

(instance invWin of InsetWindow
	(properties)
)

(instance gcWin of BorderWindow
	(properties)
	
	(method (open &tmp temp0 temp1 temp2 temp3 temp4 temp5 theColBlack temp7 theColGray2 theColWhite temp10 temp11 temp12 temp13 [temp14 15] [temp29 4])
		(= temp12 -1)
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
			priority: temp12
		)
		(super open:)
		(DrawCel
			947
			0
			5
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
			6
			temp12
		)
		(DrawCel 947 1 1 4 3 temp12)
		(DrawCel 947 1 0 94 38 temp12)
		(DrawCel 947 1 0 135 38 temp12)
		(DrawCel
			947
			0
			4
			63
			(- 37 (+ (CelHigh 947 0 4) 3))
			temp12
		)
		(DrawCel
			947
			0
			3
			101
			(- 37 (+ (CelHigh 947 0 4) 3))
			temp12
		)
		(DrawCel
			947
			0
			2
			146
			(- 37 (+ (CelHigh 947 0 4) 3))
			temp12
		)
		(= temp5 (+ (= temp2 (+ 46 (CelHigh 947 0 1))) 13))
		(= temp4
			(+
				(= temp3 (+ 10 (CelWide 947 1 1)))
				(-
					(+ 151 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= theColBlack colBlack)
		(= theColGray2 colGray2)
		(= temp11 (FindColor colGray3 colGray2 colGray2))
		(= temp10 (FindColor colGray5 colWhite colWhite))
		(= theColWhite colWhite)
		(= temp1 3)
		(= temp7 1)
		(Graph
			grFILL_BOX
			temp2
			temp3
			(+ temp5 1)
			(+ temp4 1)
			temp7
			theColBlack
			temp12
		)
		(= temp2 (- temp2 temp1))
		(= temp3 (- temp3 temp1))
		(= temp4 (+ temp4 temp1))
		(= temp5 (+ temp5 temp1))
		(Graph
			grFILL_BOX
			temp2
			temp3
			(+ temp2 temp1)
			temp4
			temp7
			theColGray2
			temp12
		)
		(Graph
			grFILL_BOX
			(- temp5 temp1)
			temp3
			temp5
			temp4
			temp7
			theColWhite
			temp12
		)
		(= temp13 0)
		(while (< temp13 temp1)
			(Graph
				grDRAW_LINE
				(+ temp2 temp13)
				(+ temp3 temp13)
				(- temp5 (+ temp13 1))
				(+ temp3 temp13)
				temp11
				temp12
				-1
			)
			(Graph
				grDRAW_LINE
				(+ temp2 temp13)
				(- temp4 (+ temp13 1))
				(- temp5 (+ temp13 1))
				(- temp4 (+ temp13 1))
				temp10
				temp12
				-1
			)
			(++ temp13)
		)
		(Graph
			grUPDATE_BOX
			temp2
			temp3
			(+ temp5 1)
			(+ temp4 1)
			1
		)
		(Format @temp14 0 33 score possibleScore)
		(TextSize @temp29 @temp14 999 0)
		(Display
			@temp14
			dsFONT
			999
			dsCOLOR
			(FindColor colGray5 colWhite colWhite)
			dsCOORD
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[temp29 3]
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
		signal $0080
		helpStr {Raises and lowers the level of graphics detail.}
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
		signal $0080
		helpStr {Adjusts sound volume.}
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
		signal $0080
		helpStr {Adjusts the speed of the your character's animation (within the limits of your computer's capability).}
		sliderView 947
		bottomValue 15
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		message 9
		signal $01c3
		helpStr {Saves your current game.}
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 947
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		message 9
		signal $01c3
		helpStr {Restores a previously saved game.}
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 947
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		message 9
		signal $01c3
		helpStr {Restarts the Game.}
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		message 9
		signal $01c3
		helpStr {Exits the game.}
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		nsLeft 8
		nsTop 86
		message 9
		signal $01c3
		helpStr {Information about the game.}
	)
)

(instance iconHelp of IconI
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 70
		message 6
		signal $0183
	)
)

(instance iconOk of IconI
	(properties
		view 947
		loop 8
		cel 0
		nsLeft 8
		nsTop 106
		cursor 70
		message 9
		signal $01c3
		helpStr {Exits this menu.}
	)
)

(instance SpiderList of List
	(properties)
)
