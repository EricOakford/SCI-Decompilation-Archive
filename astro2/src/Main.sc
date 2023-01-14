;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use ColorInit)
(use RegionPath)
(use SQEgo)
(use PMouse)
(use SlideIcon)
(use BordWind)
(use IconBar)
(use RandCyc)
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
	sq4 0
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
	SteppedFullyOn 15
	NoResponse 16
	Babble 17
	FindColor 18
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
	syncBias						;; globals used by sync.sc (will be removed shortly)
	theSync				
	cDAudio				
	fastCast			
	inputFont			=	SYSFONT	;font used for user type-in

	tickOffset			

	howFast				 ;; measurment of how fast a machine is		
	;globals 88-99 are unused
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
	curReg
	noCursor =  TRUE
	cursorType
	numColors
	numVoices
	restartRoom
	global108
	global109
	mallRoomVisits
	global111
	eHead
	sGrooper
	gameFlags
		global115
		global116
		global117
		global118
		global119
		global120
	endGameFlags
	timeCodeEstros
		global123
		global124
		global125
		global126
	endTimeCodeEstros
	timeWarpEntries
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
	buckazoids =  59
	heldBox
	colGray5
	laptopUses
	hiddenHints
		global164
		global165
		global166
		global167
	endHiddenHints
	buckazoidsInATM =  2001
	laptopPlug
	roomTeleports
	monolithBurgerEarnings
	timeCodeXenon =  10
		global174
		global175
		global176
		global177
	endTimeCodeXenon
	timeCodeMall
		global180
		global181
		global182
		global183
	endTimeCodeMall
	beamSetting
	deathView
	deathMessage
	currentEra
	colLCyan
	colLMagenta
	msAstroChickenPlays
	saveCursorX
	saveCursorY
	global194
)
(procedure (NormalEgo theLoop theView sView &tmp stopView)
	(= stopView 0)
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1)
			(ego view: theView)
			(if (> argc 2)
				(= stopView sView)
			)
		)
	)
	(if (not stopView)
		(= stopView 4)
	)
	(ego
		normal: TRUE
		moveHead: TRUE
		setLoop: -1
		setLoop: stopGroop
		setPri: -1
		setMotion: FALSE
		setCycle: StopWalk stopView
		setStep: 3 2
		illegalBits: 0
		ignoreActors: FALSE
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
)

(procedure (HandsOff &tmp saveIcon)
	(User canControl: 0 canInput: 0)
	(ego setMotion: 0)
	(= saveIcon (theIconBar curIcon?))
	(theIconBar disable: 0 1 2 3 4 5 6 7)
	(theIconBar curIcon: saveIcon)
	(if (not (HaveMouse))
		(= saveCursorX ((User curEvent?) x?))
		(= saveCursorY ((User curEvent?) y?))
		(theGame setCursor: waitCursor 1 304 172)
	else
		(theGame setCursor: waitCursor 1)
	)
	(if pMouse (pMouse stop:))
)

(procedure (HandsOn)
	(User canControl: 1 canInput: 1)
	(theIconBar enable: 0 1 2 3 4 5 6 7)
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
	(if argc (= temp0 param1) else (= temp0 4))
	((= eHead egoHead) init: ego view: temp0 cycleSpeed: 24)
)

(procedure (EgoDead theDeathView theDeathMessage)
	(if (> argc 0)
		(= deathView theDeathView)
		(if (OneOf (ego view?) 373 374 993)
			(if (== deathView 0) (= deathView 7))
			(if (== deathView 8) (= deathView 9))
		)
	else
		(= deathView 0)
	)
	(if (> argc 1)
		(= deathMessage theDeathMessage)
	else
		(= deathMessage 0)
	)
	(curRoom newRoom: 900)
)

(procedure (SolvePuzzle param1 param2)
	(if (not (Btst param1))
		(theGame changeScore: param2)
		(Bset param1)
		(pointsSound play:)
	)
)

(procedure (DoDisplay theTheColWhite &tmp theTheTheColWhite theTheTheColWhite_2 temp2 theTheTheColWhite_3 theTheTheColWhite_4 theTheTheColWhite_5 theColWhite theTheTheColWhite_6 temp8)
	(return
		(if (== argc 1)
			(Display 0 26 108 [theTheColWhite 0])
		else
			(= theTheTheColWhite_4 (= theTheTheColWhite_5 -1))
			(= theTheTheColWhite 0)
			(= theTheTheColWhite_2 68)
			(= temp2 69)
			(= theTheTheColWhite_3 -1)
			(= theColWhite colWhite)
			(= theTheTheColWhite_6 0)
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
						(= theTheTheColWhite_6 [theTheColWhite (++ temp8)])
					)
				)
				(++ temp8)
			)
			(= temp8
				(Display
					[theTheColWhite 0]
					dsCOORD
					theTheTheColWhite_4
					theTheTheColWhite_5
					dsCOLOR
					theTheTheColWhite_6
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

(procedure (DoStatus theCurrentEra &tmp theMyTextColor18 [temp1 25] [temp26 100] [temp126 4] temp130)
	(if (!= theCurrentEra -1) (= currentEra theCurrentEra))
	(StrCpy @temp1 {Space Quest_})
	(switch currentEra
		(1
			(StrCat @temp1 {\1B - The Sarien Encounter})
		)
		(3
			(StrCat @temp1 {\1C - The Pirates of Pestulon})
		)
		(4
			(StrCat @temp1 {\1A - Roger Wilco and The Time Rippers})
		)
		(10
			(StrCat @temp1 {\1E - Latex Babes of Estros})
		)
		(12
			(StrCat @temp1 {\1D - Vohaul's Revenge \1F})
			(= theMyTextColor18 myTextColor18)
		)
	)
	(TextSize @temp126 @temp1 0 -1)
	(StrCpy @temp26 {\06})
	(= temp130 (/ (- 326 (- [temp126 3] [temp126 1])) 2))
	(while (> temp130 0)
		(StrCat @temp26 {\06})
		(-- temp130)
	)
	(StrCat @temp26 @temp1)
	(DrawStatus @temp26 0 (FindColor colGray4 colGray1))
)

(procedure (SteppedFullyOn param1 param2)
	(return (if (== (param1 onControl:) param2) (return 1) else 0))
)

(procedure (NoResponse &tmp newList [temp1 2] temp3 userCurEvent temp5 [temp6 5])
	(= temp3 (theGame setCursor: 69 1))
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
	(= temp5 (GetTime))
	(while (< (Abs (- temp5 (GetTime))) 40)
		(breakif
			(OneOf ((= userCurEvent (Event new:)) type?) 4 1)
		)
		(userCurEvent dispose:)
	)
	(if (IsObject userCurEvent) (userCurEvent dispose:))
	(redX hide: posn: 1000 -1000)
	(Animate (newList elements?) 1)
	(newList delete: redX dispose:)
	(theGame setCursor: temp3)
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

(procedure (FindColor param1 param2)
	(if (< param1 0) (= param1 0))
	(if (> param1 255) (= param1 255))
	(if (< param2 0) (= param2 0))
	(if (> param2 15) (= param2 15))
	(return (if (Btst 21) param1 else param2))
)

(instance ego of SQEgo
	(properties
		description {Roger Wilco}
		sightAngle 180
		lookStr {It's you. Roger Wilco, space guy.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(5 (Print 0 0))
			(3 (Print 0 1))
			(10 (Print 0 2))
			(11 (Print 0 3))
			(4
				(switch theItem
					(12 (Print 0 4))
					(else  (NoResponse))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoHead of Head
	(properties
		description {Roger Wilco}
		lookStr {Roger Wilco, space guy.}
		view 4
	)
	
	(method (doVerb theVerb theItem)
		(ego doVerb: theVerb theItem)
	)
)

(instance longSong of Sound
	(properties
		number 1
	)
)

(instance longSong2 of Sound
	(properties
		number 1
	)
)

(instance pointsSound of Sound
	(properties
		flags $0001
		number 888
		priority 15
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

(instance sq4KeyDownHandler of EventHandler
	(properties)
)

(instance sq4MouseDownHandler of EventHandler
	(properties)
)

(instance sq4DirectionHandler of EventHandler
	(properties)
)

(instance sq4 of Game
	(properties)
	
	(method (init &tmp temp0)
		(= systemWindow sq4Win)
		(ColorInit)
		(= sGrooper stopGroop)
		(= useSortedFeatures 1)
		(super init:)
		(StrCpy @sysLogPath {})
		(= doVerbCode sq4DoVerbCode)
		(= ftrInitializer sq4FtrInit)
		((= keyDownHandler sq4KeyDownHandler) add:)
		((= mouseDownHandler sq4MouseDownHandler) add:)
		((= directionHandler sq4DirectionHandler) add:)
		(= pMouse PseudoMouse)
		(self egoMoveSpeed: 0 setCursor: theCursor 1 304 172)
		((= ego ego)
			_head: (= eHead egoHead)
			moveSpeed: (self egoMoveSpeed?)
			cycleSpeed: (self egoMoveSpeed?)
		)
		((ego _head?) client: ego)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: 0
			canInput: 0
		)
		((= theMusic longSong) owner: self init:)
		((= theMusic2 longSong2) owner: self init:)
		(= waitCursor 997)
		(= possibleScore 315)
		(= userFont 4)
		(= version {x.yyy})
		(= numVoices (DoSound sndDISPOSE))
		(if
			(and
				(>= (= numColors (Graph grGET_COLOURS)) 2)
				(<= numColors 16)
			)
			(Bclr 21)
		else
			(Bset 21)
		)
		(sq4Win
			color: 0
			back: (FindColor colGray4 colGray1)
			topBordColor: colWhite
			lftBordColor: (FindColor colGray5 colWhite)
			rgtBordColor: (FindColor colGray3 colGray2)
			botBordColor: colGray2
		)
		(gcWin
			color: 0
			back: (FindColor colGray4 colGray1)
			topBordColor: colWhite
			lftBordColor: (FindColor colGray5 colWhite)
			rgtBordColor: (FindColor colGray3 colGray2)
			botBordColor: colGray2
		)
		(invWin
			color: 0
			back: (FindColor colGray2 colGray1)
			topBordColor: (FindColor colGray4 colWhite)
			lftBordColor: (FindColor colGray3 colWhite)
			rgtBordColor: (FindColor colGray1 colGray2)
			botBordColor: (FindColor colBlack colGray2)
			insideColor: (FindColor colGray1 colGray2)
			topBordColor2: colBlack
			lftBordColor2: colBlack
			botBordColor2: (FindColor colGray4 colWhite)
			rgtBordColor2: (FindColor colGray5 colWhite)
		)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (FindColor colGray4 colGray1)
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
		)
		(icon5 message: (if (HaveMouse) 3840 else 9))
		(Inv
			init:
			add:
				buckazoid
				rope
				bomb
				rabbit
				battery
				jar
				paper_with_gum
				oxygen_tank
				hintbook
				pen
				atmCard
				plug
				cigar
				matches
				diskette
				laptop_computer
				invLook
				invHand
				invSelect
				invHelp
				ok
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (FindColor colGray1 colGray2)
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
					selector: 291
					topValue: 3
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: 381
					topValue: (if (> numVoices 1) 15 else 1)
					bottomValue: 0
					yourself:
				)
				(speedSlider
					theObj: self
					selector: 378
					topValue: 1
					bottomValue: 15
					yourself:
				)
				(iconSave theObj: self yourself:)
				(iconRestore theObj: self yourself:)
				(iconRestart theObj: self selector: 104 yourself:)
				(iconQuit theObj: self selector: 103 yourself:)
				(iconAbout
					theObj: (ScriptID 811 0)
					selector: 60
					yourself:
				)
				iconHelp
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (FindColor colGray3 colGray2)
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(buckazoid owner: ego)
		(= restartRoom 290)
		(theIconBar
			enable:
			disable: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5
		)
		(self newRoom: 290)
	)
	
	(method (doit)
		(super doit:)
		(if
			(not
				(OneOf
					curRoomNum
					803
					1
					6
					9
					10
					15
					16
					17
					19
					20
					21
					59
					119
					120
					150
					321
					329
					330
					335
					340
					345
					350
					355
					371
					376
					398
					500
					505
					510
					514
					515
					520
					525
					531
					541
					615
				)
			)
			((ScriptID 808 0) doit:)
		)
	)
	
	(method (replay)
		(DoStatus -1)
		(Palette 4 0 255 100)
		(super replay:)
	)
	
	(method (newRoom)
		(if (== ((inventory at: 12) state?) 1)
			(Print 0 6)
			((inventory at: 12) state: 0)
		)
		(super newRoom: &rest)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		((ScriptID 801) doit: roomNum)
		(if debugOn (SetDebug))
		(cond 
			(
				(OneOf
					roomNum
					370
					371
					375
					376
					380
					381
					385
					386
					387
					390
					391
					395
					397
					398
					399
					400
					405
					406
					410
					411
				)
				RegionPath
				(ScriptID 700)
				(= curReg 700)
				(if (OneOf roomNum 405 406 410 411) (ScriptID 809))
			)
			((OneOf roomNum 25 30 35 40 45 50 55 60 65)
				RegionPath
				(ScriptID 701)
				(= curReg 701)
				(if (OneOf roomNum 25 30 35 40 45 50 55 60 65)
					(ScriptID 705)
				)
			)
			((OneOf roomNum 75 80 85 90 95 100 105 110 115) (ScriptID 702))
			((OneOf roomNum 609 610 611 612 613 614 615 620) (ScriptID 706))
			((OneOf roomNum 299 300 305 306 310 315 320 298) (ScriptID 703))
			(
				(OneOf
					roomNum
					150
					500
					505
					510
					514
					515
					520
					525
					541
					544
					545
				)
				RegionPath
				(ScriptID 704)
				(= curReg 704)
			)
			((OneOf roomNum 1 6 9 10 15 16 17 19 20 21) (ScriptID 707))
			((OneOf roomNum 530 535 540) (ScriptID 709))
		)
		(if
			(not
				(OneOf
					roomNum
					803
					1
					6
					9
					10
					15
					16
					17
					19
					20
					21
					59
					119
					120
					150
					321
					329
					330
					335
					340
					345
					350
					355
					371
					376
					398
					500
					505
					510
					514
					515
					520
					525
					531
					541
					615
				)
			)
			(ScriptID 808)
		)
		(ScriptID 982)
		(super startRoom: roomNum)
		(if (cast contains: ego)
			(if
				(and
					(ego normal?)
					(not (OneOf roomNum 405 406 410 411))
					(not ((ego cycler?) isKindOf: StopWalk))
				)
				(ego setCycle: StopWalk 4)
			)
			(if (not (ego looper?)) (ego setLoop: stopGroop))
			(InitEgoHead (egoHead view?))
		)
		(redX init: hide: setPri: 15 posn: 1000 -1000)
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
					)
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
					5
					#button
					{Do something of redeeming\nsocial value (Quit)}
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
	)
	
	(method (pragmaFail)
		(if (User canInput:) (NoResponse))
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
			highlightColor: 0
			lowlightColor: (FindColor colGray4 colGray1)
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
			highlightColor: 0
			lowlightColor: (FindColor colGray4 colGray1)
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
			highlightColor: 0
			lowlightColor: (FindColor colGray4 colGray1)
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
			highlightColor: 0
			lowlightColor: (FindColor colGray4 colGray1)
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
			highlightColor: 0
			lowlightColor: (FindColor colGray4 colGray1)
		)
		(super init:)
	)
)

(instance buckazoid of InvI
	(properties
		view 700
		cursor 1
		signal $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(Printf
					0
					7
					buckazoids
					(if (== buckazoids 1) {.} else {s.})
				)
			)
		)
	)
)

(instance jar of InvI
	(properties
		view 700
		cel 1
		cursor 15
		signal $0002
		description {glass jar}
		owner 70
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (== cel 2) (Print 0 8) else (Print 0 9))
			)
		)
	)
)

(instance hintbook of InvI
	(properties
		view 700
		cel 4
		cursor 18
		signal $0002
		description {An SQ 4 hintbook.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(cond 
					((curRoom script?) (Print 0 10))
					(
					(and (OneOf curRoomNum 397 398) (not (Btst 31))) (Print 0 11))
					((not (HaveMem 6800)) (Print 0 12))
					(else
						(Inv curIcon: (inventory at: 9) hide:)
						(curRoom setScript: (ScriptID 708 0))
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance pen of InvI
	(properties
		view 700
		cel 5
		cursor 8
		signal $0002
		description {Yes, it's a Reveal-O-matic electric hint revealer.}
	)
)

(instance atmCard of InvI
	(properties
		view 700
		cel 6
		cursor 2
		signal $0002
		description {An AutoBucks Teller Machine card.}
	)
)

(instance plug of InvI
	(properties
		view 700
		cel 7
		cursor 5
		signal $0002
		description {A PocketPal\05 adaptor plug.}
	)
)

(instance cigar of InvI
	(properties
		view 700
		cel 8
		cursor 3
		signal $0002
		description {An obviously used stogie.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4
				(cond 
					((!= theItem 13) 0)
					(state (Print 0 13))
					(else (Print 0 14) (= state 1))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance matches of InvI
	(properties
		view 700
		cel 9
		cursor 4
		signal $0002
		description {A book of matches.}
	)
)

(instance diskette of InvI
	(properties
		view 700
		cel 10
		cursor 11
		signal $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2 (Print 0 15))
		)
	)
)

(instance rabbit of InvI
	(properties
		view 700
		cel 11
		cursor 9
		signal $0002
		description {cute bunny}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(cond 
					((!= view 700)
						(Inv hide:)
						(self view: 700 loop: 0 cel: 11)
						(Inv show: ego)
					)
					((not ((inventory at: 4) owner?))
						(Inv hide:)
						(self view: 701 loop: 0 cel: 0)
						(Inv show: ego)
					)
					(else (Print 0 16))
				)
			)
			(3
				(if (== view 700)
					0
				else
					(SolvePuzzle 67 3)
					(Print 0 17)
					(Inv hide:)
					(ego get: 4)
					(self view: 700 loop: 0 cel: 11)
					(Inv show: ego)
				)
			)
			(4
				(switch theItem
					(4
						(Print 0 18)
						(Inv hide:)
						(ego put: 4 0)
						(Inv curIcon: 0 show: ego)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance battery of InvI
	(properties
		view 700
		cel 12
		cursor 13
		signal $0002
		description {A battery.}
	)
)

(instance oxygen_tank of InvI
	(properties
		view 700
		cel 13
		cursor 12
		signal $0002
		description {An oxygen tank.}
		owner 335
		name "oxygen tank"
	)
)

(instance rope of InvI
	(properties
		view 700
		cel 14
		cursor 10
		signal $0002
		description {A crummy piece of rope.}
		owner 65
	)
)

(instance laptop_computer of InvI
	(properties
		view 700
		cel 15
		cursor 17
		signal $0002
		description {A handy Dandy PocketPal portable terminal.}
		owner 55
		name "laptop computer"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(cond 
					((!= view 700)
						(Inv hide:)
						(self view: 700 loop: 0 cel: 15)
						(Inv show: ego)
					)
					((== ((inventory at: 4) owner?) 1) (Print 0 19))
					(else
						(Inv hide:)
						(self view: 701 loop: 0 cel: 1)
						(Inv show: ego)
					)
				)
			)
			(3
				(if (== ((inventory at: 4) owner?) 1)
					(Print 0 20)
					(Inv hide:)
					(ego get: 4)
					(Inv show: ego)
				else
					(Print 0 21)
				)
			)
			(4
				(switch theItem
					(4
						(SolvePuzzle 72 3)
						(Print 0 22)
						(Inv hide:)
						(self view: 700 loop: 0 cel: 15)
						(ego put: 4 1)
						(Inv curIcon: 0 show: ego)
					)
					(11
						(SolvePuzzle 71 3)
						(Print 0 23)
						(Inv hide:)
						(ego put: 11 1)
						(Inv curIcon: 0 show: ego)
					)
				)
			)
		)
	)
)

(instance paper_with_gum of InvI
	(properties
		view 700
		loop 2
		cursor 21
		signal $0002
		description {It's Paper-wrapped gum - a Coarsegold dining delight.}
		owner 297
		name "paper with gum"
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if cel (Print 0 24) else (Print 0 25))
			)
			(3
				(if (not cel)
					(Inv hide:)
					(self view: 700 loop: 2 cel: 1)
					(Inv show: ego)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance bomb of InvI
	(properties
		view 700
		cel 3
		cursor 22
		signal $0002
		description {A piece of unstable ordnance.}
		owner 40
	)
)

(instance redX of View
	(properties
		view 942
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
		message 11
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
		message 10
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

(instance sq4DoVerbCode of Code
	(properties)
	
	(method (doit param1 param2 &tmp temp0)
		(= temp0 (param2 description?))
		(switch param1
			(2
				(if (param2 facingMe: ego)
					(if (param2 lookStr?)
						(Print (param2 lookStr?))
					else
						(NoResponse)
					)
				)
			)
			(else  (NoResponse))
		)
	)
)

(instance sq4FtrInit of Code
	(properties)
	
	(method (doit param1)
		(if (== (param1 sightAngle?) 26505)
			(param1 sightAngle: 90)
		)
		(if (== (param1 actions?) 26505) (param1 actions: 0))
	)
)

(instance sq4Win of BorderWindow
	(properties)
)

(instance invWin of InsetWindow
	(properties)
)

(instance gcWin of BorderWindow
	(properties)
	
	(method (open &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 theColGray2 theColWhite temp10 temp11 temp12 temp13 [temp14 15] [temp29 4])
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
			15
		)
		(DrawCel 947 1 1 4 3 15)
		(DrawCel 947 1 0 94 38 15)
		(DrawCel 947 1 0 135 38 15)
		(DrawCel 947 0 4 63 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 3 101 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 2 146 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
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
		(= temp12 15)
		(= temp6 0)
		(= theColGray2 colGray2)
		(= temp11 (FindColor colGray3 colGray2))
		(= temp10 (FindColor colGray5 colWhite))
		(= theColWhite colWhite)
		(= temp1 3)
		(= temp7 3)
		(Graph
			grFILL_BOX
			temp2
			temp3
			(+ temp5 1)
			(+ temp4 1)
			temp7
			temp6
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
		(Format @temp14 0 27 score possibleScore)
		(TextSize @temp29 @temp14 999 0)
		(Display
			@temp14
			dsFONT
			999
			dsCOLOR
			(FindColor colGray5 colWhite)
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
		helpStr {Adjusts the speed of the game's animation (within the limits of your computer's capability).}
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
		helpStr {Saves your current game (currently disabled).}
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
		helpStr {Restores a previously saved game (currently disabled).}
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
