;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
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
	gameTime			
	;globals 89-99 are unused
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
	gameCode =  1234	;was zapCount, but this global is needed for the interpreter now
	curReg				;is set in Deltaur region, but is never used
	noCursor =  TRUE	;unused
	cursorType			;unused
	numColors
	numVoices
	restartRoom
	global108			;unused
	global109			;unused
	global110			;unused
	global111			;unused
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
	;color globals
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
	myTextColor14	;These colors are initialized
	myTextColor15	; in ColorInit, but they're never
	myTextColor16	; used.
	myTextColor17	;
	myTextColor18	;
	colBlue
	colDGreen
	global151		;unused
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
	zapCount	;was unused in DOS, now used in Amiga
	droidX
	droidY
	buckazoids =  3
	currentFloor =  1
	global167		;unused
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
		(= stopView 60)
	)
	(ego
		normal: TRUE
		moveHead: TRUE
		setLoop: -1
		setLoop: stopGroop
		setPri: -1
		setMotion: FALSE
		setCycle: StopWalk stopView
		setStep: 4 2
		illegalBits: 0
		ignoreActors: FALSE
		ignoreHorizon: TRUE
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
)

(procedure (HandsOff &tmp saveIcon)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= saveIcon (theIconBar curIcon?))
	(theIconBar disable:
		ICON_INVENTORY
		ICON_ITEM
		ICON_TASTE
		ICON_SMELL
		ICON_TALK
		ICON_DO
		ICON_LOOK
		ICON_WALK
	)
	(theIconBar curIcon: saveIcon)
	(if (not (HaveMouse))
		(= saveCursorX ((User curEvent?) x?))
		(= saveCursorY ((User curEvent?) y?))
		(theGame setCursor: waitCursor TRUE 310 180)
	else
		(theGame setCursor: waitCursor TRUE)
	)
	(if pMouse
		(pMouse stop:)
	)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(theIconBar enable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_SMELL
		ICON_TASTE
		ICON_ITEM
		ICON_INVENTORY
	)
	(ego
		moveSpeed: (theGame egoMoveSpeed?)
		cycleSpeed: (theGame egoMoveSpeed?)
	)
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: ICON_ITEM)
	)
	(if (not (HaveMouse))
		(theGame setCursor:
			((theIconBar curIcon?) cursor?) TRUE saveCursorX saveCursorY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
)

(procedure (SteppedOn who color)
	(return
		(if (& (who onControl: origin) color)
			(return TRUE)
			else FALSE
		)
	)
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bset flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(|= [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(&= [gameFlags (/ flagEnum 16)] (~ (>> $8000 (mod flagEnum 16))))
	(return oldState)
)

(procedure (InitEgoHead headView &tmp hView)
	(= hView 0)
	(if argc (= hView headView) else (= hView 60))
	((= eHead egoHead)
		init: ego
		view: hView
		cycleSpeed: 40
	)
)

(procedure (EgoDead view loop cel &tmp dView dLoop dCel [str 300])
	(sounds eachElementDo: #stop)
	(if argc
		(= dView view)
		(= dLoop loop)
		(= dCel cel)
		(Format @str &rest)
	else
		(= dView 944)
		(= dLoop 0)
		(= dCel 0)
		(Format @str 0 29)
	)
	(theMusic
		number: 900
		vol: 127
		loop: 1
		flags: mNOPAUSE
		play:
	)
	(sq1 setCursor: normalCursor TRUE)
	(repeat
		(switch
			(Print @str
				#mode teJustCenter
				#button {Restore} 1
				#button {Restart} 2
				#button {____Quit____} 3
				#icon dView dLoop dCel
			)
			(1
				(theGame restore:)
			)
			(2
				(theGame restart:)
			)
			(3
				(= quit TRUE)
				(break)
			)
		)
	)
)

(procedure (SolvePuzzle pVal pFlag)
	(if (not (Btst pFlag))
		(theGame changeScore: pVal)
		(Bset pFlag)
		(pointsSound play:)
	)
)

(procedure (DoDisplay args &tmp
		theMode theForeFont theBackFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 0 30
				p_restore
				[args 0]
			)
			(if (not (HaveMouse))
				(theGame setCursor: oldCursor TRUE)
			)
		else
			(= theX
				(= theY -1)
			)
			(= theMode teJustLeft)
			(= theForeFont 68)
			(= theBackFont 69)
			(= theWidth SCRNWIDE)
			(= theForeColor colWhite)
			(= theBackColor colBlack)
			(for ((= i 1)) (< i argc) ((++ i))
				(switch [args i]
					(#mode
						(= theMode
							[args (++ i)]
						)
					)
					(#font
						(= theBackFont
							(+
								(= theForeFont
									[args (++ i)]
								)
								1
							)
						)
					)
					(#width
						(= theWidth
							[args (++ i)]
						)
					)
					(#at
						(= theX
							[args (++ i)]
						)
						(= theY
							[args (++ i)]
						)
					)
					(#color
						(= theForeColor [args (++ i)])
					)
					(#back
						(= theBackColor
							[args (++ i)]
						)
					)
				)
			)
			(if (not (HaveMouse))
				(= oldCursor theCursor)
				(theGame setCursor: 69 TRUE)
			)
			(= i
				(Display [args 0]
					p_at theX theY
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theBackFont
					p_save
				)
			)
			(Display [args 0]
				p_at theX theY
				p_color theForeColor
				p_width theWidth
				p_mode theMode
				p_font theForeFont
			)
			(return i)
		)
	)
)

(procedure (Face who theObjOrX theY whoCares &tmp theHeading lookX looKY whoToCue)
	(= whoToCue 0)
	(if (IsObject theObjOrX)
		(= lookX (theObjOrX x?))
		(= looKY (theObjOrX y?))
		(if (== argc 3)
			(= whoToCue theY)
		)
	else
		(= lookX theObjOrX)
		(= looKY theY)
		(if (== argc 4)
			(= whoToCue whoCares)
		)
	)
	(= theHeading (GetAngle (who x?) (who y?) lookX looKY))
	(who setHeading: theHeading (if (IsObject whoToCue) whoToCue else 0))
)

(procedure (DoStatus strg)
	(StrCpy @strg {__Space Quest I - The Sarien Encounter})
	(DrawStatus @strg 0 (FindColor colGray4 colGray1 colGray1))
)

(procedure (NoResponse &tmp list [temp1 2] evt theTime [temp5 5])
	(= oldCursor (theGame setCursor: 69 TRUE))
	(= evt (User curEvent?))
	(redX
		x: (evt x?)
		y: (+ 300 (evt y?))
		z: 300
		show:
	)
	((= list (List new:)) add: redX)
	(Animate (list elements?) TRUE)
	(Animate (cast elements?) FALSE)
	(= theTime (GetTime))
	(while (< (Abs (- theTime (GetTime))) 40)
		(breakif
			(OneOf ((= evt (Event new:)) type?) keyDown mouseDown)
		)
		(evt dispose:)
	)
	(if (IsObject evt) (evt dispose:))
	(redX hide: posn: 1000 -1000)
	(Animate (list elements?) TRUE)
	(list delete: redX dispose:)
	(theGame setCursor: oldCursor)
)

(procedure (Babble theView msgS msgO &tmp [buffer 500])
	(if (u< msgS 1000)
		(GetFarText msgS msgO @buffer)
	else
		(StrCpy @buffer msgS)
	)
	(babbleIcon
		view: theView
		cycleSpeed: (* (+ howFast 1) 4)
	)
	(if (u< msgS 1000)
		(Print @buffer &rest #icon babbleIcon 0 0)
	else
		(Print @buffer msgO &rest #icon babbleIcon 0 0)
	)
)

(procedure (FindColor col256 col16 col32)
	;32-color parameter added for Amiga
	(if (< col256 0)
		(= col256 0)
	)
	(if (> col256 255)
		(= col256 255)
	)
	(if (< col16 0)
		(= col16 0)
	)
	(if (and (> col16 15) (== numColors 16))
		(= col16 15)
	)
	(if (> col16 31)
		(= col16 31)
	)
	(if (< col32 0)
		(= col32 0)
	)
	(if (> col32 31)
		(= col32 31)
	)
	(if (== numColors 256)
		(return col256)
	)
	(if (== numColors 16)
		(return col16)
	)
	(return (if (== numColors 32) (return col32) else 0))
)

(procedure (LogIt theVerb theObj theItem &tmp [path 30] [tmpPath 30] logHandle [str 100] [temp161 19] [temp180 30] [temp210 20])
	(= oldCursor (theGame setCursor: 69 TRUE))
	(if (not (StrLen @sysLogPath))
		(StrCpy @tmpPath {})
		(GetInput @tmpPath 30
			{Enter drive & directory for new response log...}
		)
		(StrCpy @sysLogPath @tmpPath)
	)
	(StrCpy @path @sysLogPath)
	(StrCat @path {newresp.log})
	(if (IsObject theObj)
		(StrCpy @temp180 (theObj name?))
	else
		(StrCpy @temp180 {Default Response})
	)
	(Format @str 0 31
		curRoomNum
		@temp180
		((theIconBar curIcon?) helpStr?)
	)
	(if (== theVerb verbUse)
		(StrCat @str ((theIconBar curInvIcon?) name?))
		(StrCat
			@str
			(Format @temp161 {\0D\n(switch theItem (%d} theItem)
		)
		(StrCat @str {\0D\n})
	)
	(StrCpy @temp210 {})
	(if (== theVerb verbUse)
		(StrCpy @temp210 {verbUse})
	else
		(switch ((theIconBar curIcon?) cursor?)
			(19
				(StrCpy @temp210 {verbLook})
			)
			(20
				(StrCpy @temp210 {verbDo})
			)
			(7
				(StrCpy @temp210 {verbTalk})
			)
			(30
				(StrCpy @temp210 {verbSmell})
			)
			(31
				(StrCpy @temp210 {verbTaste})
			)
		)
	)
	(Format @temp161 0 32 @temp210)
	(StrCat @str @temp161)
	(StrCat @str {\t\t\t(Print\0D\n})
	(StrCat @str {\t\t\t\t"})
	(= logHandle (FileIO fileOpen @path fAppend))
	(FileIO fileFPuts logHandle @str)
	(repeat
		(= str 0)
		(GetInput @str 50 {doVerb message:})
		(if (== (StrLen @str) 0)
			(FileIO fileFPuts logHandle {\0D\n\t\t\t\t"\0D\n})
			(FileIO fileFPuts logHandle {\t\t\t)\0D\n})
			(FileIO fileFPuts logHandle {\t\t)\0D\n})
			(FileIO fileFPuts logHandle {\t\t\0D\n\0D\n})
			(FileIO fileClose logHandle)
			(break)
		)
		(FileIO fileFPuts logHandle @str)
		(FileIO fileFPuts logHandle {\0D\n\t\t\t\t})
	)
	(theGame setCursor: oldCursor)
)


(instance egoObj of SQEgo
	(properties
		name "ego"
		description {Roger Wilco}
		sightAngle 180
		lookStr {It's you, Roger Wilco, janitor sub-extraordinaire.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(Print 0 0)
			)
			(verbDo
				(Print 0 1)
			)
			(verbTaste
				(Print 0 2)
			)
			(verbSmell
				(Print 0 3)
			)
			(verbUse
				(switch theItem
					(iDehydratedWater
						(cond 
							((curRoom script?)
								(Print 0 4)
							)
							((OneOf curRoomNum 37 18 19 20 21 22 23 24 25 26 27)
								(curRoom setScript: (ScriptID KERONA 4))
							)
							(else
								(Print 0 5)
							)
						)
					)
					(iGrenade
						(Print 0 6)
					)
					(iJetpack
						(if (== curRoomNum 51)
							(Print 0 7)
						else
							(Print 0 8)
						)
					)
					(iKnife
						(Print 0 9)
					)
					(else 
						(switch (Random 1 5)
							(1
								(Print 0 10)
							)
							(2
								(Print 0 11)
							)
							(3
								(Print 0 12)
							)
							(4
								(Print 0 13)
							)
							(5
								(Print 0 14)
							)
						)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
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

(instance longSong of Sound)

(instance longSong2 of Sound)

(instance invSound of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance soundEffects of Sound
	(method (check)
		(DoSound UpdateCues self)
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(cond 
				((> (self loop?) 1)
					(self loop: (- (self loop?) 1))
					(DoSound PlaySound self 0)
				)
				((IsObject client)
					(client cue: self)
				)
			)
		)
	)
)

(instance pointsSound of Sound
	(properties
		flags mNOPAUSE
		number 901
	)
)

(instance stopGroop of GradualLooper
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
	(method (init)
		((= cycler (RandCycle new:)) init: self 20)
	)
)

(instance sq1KeyDownHandler of EventHandler)

(instance sq1MouseDownHandler of EventHandler)

(instance sq1DirectionHandler of EventHandler)

(instance sq1 of Game
	(method (init &tmp temp0)
		(= debugging FALSE)
		(ColorInit)
		(= systemWindow sq1Win)
		(= sGrooper stopGroop)
		(= deltaurSector (Random 1 20))
		(= useSortedFeatures TRUE)
		(= spiderPoly (SpiderList add:))
		StopWalk
		Polygon
		PolyPath
		SQRoom
		IconBar
		Inventory
		(ScriptID SIGHT)
		RandCycle
		(LoadMany CURSOR 6 997 ARROW_CURSOR 19 30 HAND_CURSOR 7 31)	;Amiga addition
		(super init: &rest)
		(StrCpy @sysLogPath {})
		(= doVerbCode sq1DoVerbCode)
		(= ftrInitializer sq1FtrInit)
		((= keyDownHandler sq1KeyDownHandler) add:)
		((= mouseDownHandler sq1MouseDownHandler) add:)
		((= directionHandler sq1DirectionHandler) add:)
		(= pMouse PseudoMouse)
		(self egoMoveSpeed: 5 setSpeed: 0)
		((= ego egoObj)
			_head: (= eHead egoHead)
			moveSpeed: (self egoMoveSpeed?)
			cycleSpeed: (self egoMoveSpeed?)
		)
		(eHead client: ego)
		(User
			canControl: FALSE
			canInput: FALSE
			alterEgo: ego
		)
		((= theMusic longSong)
			owner: self
			init:
			flags: mNOPAUSE
		)
		((= theMusic2 longSong2)
			owner: self
			init:
		)
		(= soundFx soundEffects)
		(= version {x.yyy})
		(= waitCursor 997)
		(= possibleScore 201)
		(= userFont 4)
		(= numVoices (DoSound NumVoices))
		(= numColors (Graph GDetect))
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
		(icon5 message: (if (HaveMouse) SHIFTTAB else TAB))
		(Inventory
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
				;Keronian_Ale	;unused
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
					selector: #detailLevel
					topValue: 3
					yStep: (- 3 howFast)
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: #masterVolume
					topValue: (if (> numVoices 1) 15 else 1)
					bottomValue: 0
					yStep: (- 3 howFast)
					yourself:
				)
				(speedSlider
					theObj: speedORama
					selector: #doit
					yStep: (- 3 howFast)
					yourself:
				)
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
					theObj: (ScriptID ABOUT 0)
					selector: #doit
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
		(self newRoom: SPEED)
	)
	
	(method (replay)
		(Palette PALIntensity 0 255 100)
		(super replay:)
	)
	
	(method (startRoom roomNum)
		(if pMouse
			(pMouse stop:)
		)
		(sounds eachElementDo: #perform soundReset)
		((ScriptID DISPOSE) doit: roomNum)
		(if
			(and
				debugging
				logging
				(!= (- (MemoryInfo FreeHeap) 2) (MemoryInfo LargestPtr))
				(Print 0 17 #button {Who cares} 0 #button {Debug} 1)
			)
			(SetDebug)
		)
		(redX init: hide: setPri: 15 posn: 1000 -1000)
		(if debugOn
			(SetDebug)
		)
		(cond 
			((OneOf roomNum 3 4 5 6 7 8 9 10 11 12 13 103)
				Elevator
				(ScriptID ARCADA)
			)
			((OneOf roomNum 37 18 19 20 21 22 23 24 25 26 27)
				(ScriptID KERONA)
			)
			((OneOf roomNum 54 55 57 58 59 60 61 62 63 64 65 66 67)
				Elevator
				RegionPath
				(ScriptID DELTAUR)
				(= curReg DELTAUR)
			)
			(else 0)
		)
		(if
			(OneOf roomNum
				3 35 40 41 42 43 45 46 58 59 60 61
				62 63 64 66 68
			)
			RandCycle
		)
		(if
			(and debugging (not (OneOf roomNum 999)))
			((ScriptID DISPOSE) init:)
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
	
	(method (restart tOrF)
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(if
			(or
				(and argc (== tOrF 0))
				(Print 0 16
					#button {Of course I'm sure!} 1
					#button {Changed My Mind.\nLet's Play!} 0
					#icon babbleIcon 0 0
				)
			)
			(super restart:)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(TAB
							(if (not (& (icon5 signal?) DISABLED))
								(Inventory showSelf: ego)
							)
						)
						(SHIFTTAB
							(if (not (& (icon5 signal?) DISABLED))
								(Inventory showSelf: ego)
							)
						)
						(`^q
							(theGame quitGame:)
							(event claimed: TRUE)
						)
						(`#1
							(GameControls show:)
						)
						(`#2
							(cond 
								((theGame masterVolume:)
									(theGame masterVolume: 0)
								)
								((> numVoices 1)
									(theGame masterVolume: 15)
								)
								(else
									(theGame masterVolume: 1)
								)
							)
							(event claimed: TRUE)
						)
						(`#5
							(theGame save:)
							(event claimed: TRUE)
						)
						(`#7
							(theGame restore:)
							(event claimed: TRUE)
						)
						(`#9
							(theGame restart:)
							(event claimed: TRUE)
						)
						(`@n
							(if (HaveMem 1536)
								((ScriptID LOGGER) doit: @sysLogPath 0)
							else
								(Print 0 18)
							)
							(event claimed: TRUE)
						)
						(else 
							(if debugging
								((ScriptID DEBUG) handleEvent: event)
							)
						)
					)
				)
				(mouseDown
					(if debugging
						((ScriptID DEBUG) handleEvent: event)
					)
				)
			)
		)
	)
	
	(method (quitGame)
		(babbleIcon view: 946 cycleSpeed: (* (+ howFast 1) 4))
		(super
			quitGame:
				(Print 0 15
					#button {Let me out of here!\n(Quit)} 1
					#button {I don't know WHAT\nI was thinking.\nLet's Play!} 0
					#icon babbleIcon 0 0
				)
		)
	)
	
	(method (pragmaFail)
		(if (User canInput:)
			(if (and debugging (Btst fLogging))
				(LogIt)
			else
				(NoResponse)
			)
		)
	)

	;EO: NRS speed fix which was moved from GAME.SC
;;;	(method (doit)
;;;		(switch curRoomNum
;;;			(SPEED)	;don't apply slowdown at speed tester
;;;			(1
;;;				(Wait 1)
;;;			)
;;;			(else
;;;				(Wait 2)
;;;			)
;;;		)
;;;		(super doit:)
;;;	)	

)

(instance soundReset of Code
	(method (doit soundNum)
		(if (and (== (soundNum prevSignal?) -1) (soundNum number?))
			(soundNum number: 0)
		)
	)
)

(instance speedORama of Code
	(method (doit newSpeed)
		(if argc
			(theGame egoMoveSpeed: newSpeed)
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
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray1)
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
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray1)
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
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray2)
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
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray2)
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
			highlightColor: colBlack
			lowlightColor: (FindColor colGray4 colGray1 colGray2)
		)
		(super init:)
	)
)

(class RInvItem of InvItem
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(if (== (Inventory at: theItem) self)
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
		signal IMMEDIATE
		description {It's a cartridge from the Arcada's Data Archive.}
		owner 3
	)
)

(instance keyCard of RInvItem
	(properties
		view 550
		cursor 2
		signal IMMEDIATE
		description {This keycard fits an electronic lock someplace on the Arcada.}
		owner 5
	)
)

(instance Gadget of RInvItem
	(properties
		view 552
		cursor 3
		signal IMMEDIATE
		description {This is some sort of gadget. You're not sure what it does exactly, but it has a switch.}
		owner 11
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((curRoom script?)
						(Print 0 20)
					)
					((Btst fGadgetOn)
						(Bclr fGadgetOn)
						(invSound number: 902 loop: 1 flags: 1 play:)
						(Print 0 21)
					)
					(else
						(Bset fGadgetOn)
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
		signal IMMEDIATE
		description {It's the cone shaped tip of a stalagmite.}
		owner 30
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((curRoom script?)
						(Print 0 23)
					)
					((== cel 1)
						(Print 0 24)
						(Bclr fRockHasGoo)
						(ego get: iPlant)
						(self cel: 0)
						(Inventory hide: showSelf: ego)
					)
					(else
						(Print 0 25)
					)
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
		signal IMMEDIATE
		description {A Survival Kit}
		owner 18
		name "Survival Kit"
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbUse
				(cond 
					((curRoom script?)
						(Print 0 20)
					)
					((Btst fOpenedKit)
						(Print 0 26)
					)
					(else
						(Bset fOpenedKit)
						(Print 0 27)
						(ego get: iDehydratedWater)
						(ego get: iKnife)
						(Inventory hide: showSelf: ego)
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
		signal IMMEDIATE
		description {It's a Xenon Army knife}
	)
)

(instance Dehydrated_Water of RInvItem
	(properties
		view 555
		cursor 8
		signal IMMEDIATE
		description {The can label says "Pelvitron Dehydrated Water (H2) - All you add is air! Makes 10 gallons! Caution - Do not attempt to open or rupture container! Misuse could result in personal injury and/or flash flooding."}
		name "Dehydrated Water"
	)
)

(instance Broken_Glass of RInvItem
	(properties
		view 556
		cursor 9
		signal IMMEDIATE
		description {It's that highly reflective piece of broken cockpit glass.}
		owner 18
		name "Broken Glass"
	)
)

(instance Orat_Part of RInvItem
	(properties
		view 558
		cursor 11
		signal IMMEDIATE
		description {This cute little item is an Orat part - you're not sure what part though.}
		owner 28
		name "Orat Part"
	)
)

(instance Skimmer_Key of RInvItem
	(properties
		view 559
		cursor 12
		signal IMMEDIATE
		description {This key operates the skimmer.}
		owner 35
		name "Skimmer Key"
	)
)

(instance buckazoid of RInvItem
	(properties
		view 560
		cursor 13
		signal IMMEDIATE
		description {buckazoid}
	)
	
	(method (show)
		(= view (if (>= buckazoids 3) 560 else 574))
		(super show: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(Printf 0 28
					buckazoids
					(if (== buckazoids 1) {.} else {s.})
				)
			)
			;Amiga addition
			(verbUse
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
		signal IMMEDIATE
		description {Ummm! Keronian Ale!}
		owner 43
		name "Keronian Ale"
	)
)

(instance Jetpack of RInvItem
	(properties
		view 562
		cursor 14
		signal IMMEDIATE
		description {This is a used jetpack. Real used.}
		owner 41
	)
)

(instance Pulseray_Laser_Pistol of RInvItem
	(properties
		view 563
		cursor 15
		signal IMMEDIATE
		description {It's a pulseray laser pistol. Remember, this isn't a play toy!}
		owner 58
		name "Pulseray Laser Pistol"
	)
)

(instance Grenade of RInvItem
	(properties
		view 564
		cursor 16
		signal IMMEDIATE
		description {You have a small innocuous looking grenade.}
		owner 58
	)
)

(instance Remote of RInvItem
	(properties
		view 565
		cursor 17
		signal IMMEDIATE
		description {This is a small single function remote control.}
		owner 64
	)
)

(instance Widget of RInvItem
	(properties
		view 570
		cursor 18
		signal IMMEDIATE
		description {It's a genuine Widget. You're not sure what it does but it's heavy, it looks cool, and it might be magnetic. Please keep this away from the game disks!}
		owner 7
	)
)

(instance Plant of RInvItem
	(properties
		view 571
		cursor 21
		signal IMMEDIATE
		description {This is simply a piece of sticky, stinking, rotting, plant.}
		owner 19
	)
)

(instance Bar_Coupon of RInvItem
	(properties
		view 572
		cursor 22
		signal IMMEDIATE
		description {Hey kids! This bar coupon is good for 5 bucakzoids and a free Keronian Ale!}
		name "Bar Coupon"
	)
)

(instance Droids-B-Us_coupon of RInvItem
	(properties
		view 573
		cursor 23
		signal IMMEDIATE
		description {This coupon gives you a 20% discount at a Droids-B-Us near you! How helpful! Those suckers have the highest droid prices in this universe.}
		name "Droids-B-Us coupon"
	)
)

(instance Sarien_ID_Card of RInvItem
	(properties
		view 569
		cursor 24
		signal IMMEDIATE
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
		maskLoop 14
		maskCel 1
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
		maskLoop 14
		maskCel 1
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
		maskLoop 14
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
		maskLoop 14
		maskCel 3
	)
)

(instance icon4 of IconItem
	(properties
		view 900
		loop 4
		cel 0
		cursor ARROW_CURSOR
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {This window displays the current inventory item.}
		maskView 900
		maskLoop 14
		maskCel 4
	)
)

(instance icon5 of IconItem
	(properties
		view 900
		loop 5
		cel 0
		cursor ARROW_CURSOR
		type nullEvt
		message verbNone
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the inventory window.}
		maskView 900
		maskLoop 14
		maskCel 2
	)
	
	(method (select)
		(if (super select:)
			(Inventory showSelf: ego)
		)
	)
)

(instance icon6 of IconItem
	(properties
		view 900
		loop 10
		cel 0
		cursor 30
		message verbSmell
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for smelling.}
		maskView 900
		maskLoop 14
	)
)

(instance icon7 of IconItem
	(properties
		view 900
		loop 11
		cel 0
		cursor 31
		message verbTaste
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for tasting.}
		maskView 900
		maskLoop 14
		maskCel 1
	)
)

(instance icon8 of IconItem
	(properties
		view 900
		loop 7
		cel 0
		cursor 999
		message verbShowControl
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
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

(instance icon9 of IconItem
	(properties
		view 900
		loop 9
		cel 0
		cursor 29
		message verbHelp
		signal (| RELVERIFY IMMEDIATE)
		helpStr {This icon tells you about other icons.}
		maskView 900
		maskLoop 14
	)
)

(instance sq1DoVerbCode of Code
	(method (doit theVerb theObj theItem &tmp objDesc)
		(= objDesc (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj facingMe: ego)
					(cond 
						((theObj lookStr?)
							(Print (theObj lookStr?))
						)
						((not (Btst fLogging))
							(NoResponse)
						)
						(else
							(LogIt theVerb theObj)
						)
					)
				)
			)
			(else 
				(if (not (Btst fLogging))
					(NoResponse)
				else
					(LogIt theVerb theObj theItem)
				)
			)
		)
	)
)

(instance sq1FtrInit of Code
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 90)
		)
		(if (== (theObj actions?) ftrDefault)
			(theObj actions: 0)
		)
	)
)

(instance sq1Win of BorderWindow
	(method (dispose)
		(super dispose: &rest)
		(if (not (HaveMouse))
			(theGame setCursor: oldCursor TRUE)
		)
	)
	
	(method (open)
		(if (not (HaveMouse))
			(= oldCursor theCursor)
			(theGame setCursor: 69 TRUE)
		)
		(super open: &rest)
	)
)

(instance invWin of InsetWindow)

(instance gcWin of BorderWindow
	(method (open &tmp
			temp0 theBevelWid t l r b theColor theMaps bottomColor topColor leftColor rightColor
			thePri i [str 15] [len 4])
		(= thePri -1)
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
			priority: thePri
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
			6
			thePri
		)
		(DrawCel 947 1 1 4 3 thePri)
		(DrawCel 947 1 0 94 38 thePri)
		(DrawCel 947 1 0 135 38 thePri)
		(DrawCel 947 0 4 63
			(- 37 (+ (CelHigh 947 0 4) 3))
			thePri
		)
		(DrawCel 947 0 3 101
			(- 37 (+ (CelHigh 947 0 4) 3))
			thePri
		)
		(DrawCel 947 0 2 146
			(- 37 (+ (CelHigh 947 0 4) 3))
			thePri
		)
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
		(= theColor colBlack)
		(= bottomColor colGray2)
		(= rightColor (FindColor colGray3 colGray2 colGray2))
		(= leftColor (FindColor colGray5 colWhite colWhite))
		(= topColor colWhite)
		(= theBevelWid 3)
		(= theMaps 1)
		(Graph GFillRect t l (+ b 1) (+ r 1) theMaps theColor thePri)
		(= t (- t theBevelWid))
		(= l (- l theBevelWid))
		(= r (+ r theBevelWid))
		(= b (+ b theBevelWid))
		(Graph GFillRect t l (+ t theBevelWid) r theMaps bottomColor thePri)
		(Graph GFillRect (- b theBevelWid) l b r theMaps topColor thePri)
		(for ((= i 0)) (< i theBevelWid) ((++ i))
			(Graph GDrawLine (+ t i) (+ l i) (- b (+ i 1)) (+ l i) rightColor thePri -1)
			(Graph GDrawLine (+ t i) (- r (+ i 1)) (- b (+ i 1)) (- r (+ i 1)) leftColor thePri -1)
		)
		(Graph GShowBits t l (+ b 1) (+ r 1) 1)
		(Format @str 0 33 score possibleScore)
		(TextSize @len @str 999 0)
		(Display @str
			p_font 999
			p_color (FindColor colGray5 colWhite colWhite)
			p_at
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[len 3]
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
		signal FIXED_POSN
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
		signal FIXED_POSN
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
		signal FIXED_POSN
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
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
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
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
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
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
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
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
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
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Information about the game.}
	)
)

(instance iconHelp of IconItem
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 70
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
		nsTop 106
		cursor 70
		message 9
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Exits this menu.}
	)
)

(instance SpiderList of List)