;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Procs)
(use Tactor)
(use TInv)
(use TScripts)
(use Intrface)
(use CDActor)
(use PMouse)
(use GControl)
(use BordWind)
(use IconBar)
(use LoadMany)
(use DCIcon)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Tales 0
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
	isHandsOff
	gameCode =  1234
	global102
	numColors =  16
	theJackStory
	theCindStory
	theBremenStory
	theSnowStory
	theBeautyStory
	theStorySet
	userName
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
	egoTravelCode
	friendTravelCode
	theBookEnd
	global129 =	[4 220 240 250 300]
	global134 =	[4 150 200 260 210]
	egoView =  1
	gameState
	global141
	selectedStory
	gGCindStory
	gGJackStory
	global145
	theTalkerIcon
	iconBarActive
	global148
	global149
	theMusic
	theMusic2
	talkerCoords =	[
		48 34 51 25 49 19 42 34 44 25 43 23 42 32
		44 23 43 20 43 31 45 24 44 19 44 30 47 23
		45 18 42 30 46 24 43 16 29 34 29 25 28 19
		36 34 35 25 34 23 34 32 35 23 34 20 34 31
		34 24 33 19 33 30 32 23 32 18 33 30 34 24
		33 16
				]
	global224
	walkSound
	theWalkMusic
	global227
	global228
	numVoices =  1
	global230
	theZapCursor
	demoPrintRet
	demoPrintCued
	global234 =  2
	global235
	global236
	global237
	global238
	global239
	global240
	global241
	global242
	global243
	global244
	global245
	global246
	global247
	global248
	global249
	gameFlags
		global251
		global252
		global253
		global254
		global255
		global256
		global257
		global258
		global259
		global260
		global261
		global262
		global263
		global264
		global265
		global266
		global267
		global268
		global269
		global270
		global271
		global272
		global273
		global274
		global275
		global276
		global277
		global278
		global279
		global280
		global281
		global282
		global283
		global284
		global285
		global286
		global287
		global288
		global289
		global290
		global291
		global292
		global293
		global294
		global295
		global296
		global297
		global298
		global299
		global300
)
(procedure (SelectStory theStory)
	(cond 
		((theStory selected?)
			(Print 0 8)
		)
		((theStory selectCode?)
			((theStory selectCode?) doit:)
		)
		(else
			(Print 0 9)
		)
	)
	(storyControls hide:)
)

(instance egoObj of Body
	(properties
		name {ego}
		description {cute little kid}
		lookStr {Why, it's YOU!}
		talkerID 1
	)
	
	(method (init)
		((= talkerObj egoTalkObj)
			setUp: egoBust egoEyes egoMouth
		)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if (<= x 10)
			(= edgeHit WEST)
		)
		(if (>= x 310)
			(= edgeHit EAST)
		)
		(if (>= y 166)
			(= edgeHit SOUTH)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(Print 0 0)
			)
			(verbTalk
				(if (IsObject doCode)
					(doCode doit: theVerb)
				else
					(Print 0 1)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance egoTalkObj of TalkerObj
	(properties
		x -1
		y 87
		nsTop 10
		nsLeft 110
	)
	
	(method (setUp &tmp temp0 temp1)
		(super setUp: &rest)
		(= view (+ egoView 10))
		(if (== facingDir dirW)
			(= temp0 36)
		else
			(= temp0 0)
		)
		(= temp1 (* egoView 6))
		(mouth
			nsLeft: [talkerCoords (+ temp1 temp0)]
			nsTop: [talkerCoords (+ temp1 temp0 1)]
			view: view
		)
		(eyes
			nsLeft: [talkerCoords (+ temp1 temp0 2)]
			nsTop: [talkerCoords (+ temp1 temp0 3)]
			view: view
		)
		(bust
			nsLeft: [talkerCoords (+ temp1 temp0 4)]
			nsTop: [talkerCoords (+ temp1 temp0 5)]
			view: view
		)
	)
)

(instance egoBust of View
	(properties
		loop 6
	)
)

(instance egoEyes of Prop
	(properties
		loop 4
		cycleSpeed 36
	)
)

(instance egoMouth of Prop
	(properties
		loop 2
		cycleSpeed 12
	)
)

(instance BookEnd of Tactor
	(properties
		description {Bookend}
		view 762
		talkerID 19
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(if (IsObject doCode)
					(doCode doit: theVerb)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
	
	(method (setUp)
		((= talkerObj bookEndTalkObj)
			setUp: bookEndBust bookEndEyes bookEndMouth
		)
	)
)

(instance bookEndTalkObj of TalkerObj
	(properties
		x -1
		y 97
		nsTop 10
		nsLeft 108
		view 761
	)
)

(instance bookEndBust of View
	(properties
		nsTop 10
		nsLeft 42
		view 761
		loop 6
	)
)

(instance bookEndEyes of Prop
	(properties
		nsTop 16
		nsLeft 44
		view 761
		loop 4
		cycleSpeed 36
	)
)

(instance bookEndMouth of Prop
	(properties
		nsTop 28
		nsLeft 42
		view 761
		loop 2
		cycleSpeed 12
	)
)

(instance MH of EventHandler)

(instance KH of EventHandler)

(instance DH of EventHandler)

(instance Tales of Game
	(properties
		egoMoveSpeed 6
	)
	
	(method (init &tmp versionFile [temp1 20])
		(SysWindow color: 65 back: 58)
		(= systemWindow SysWindow)
		(= global89 0)
		(InitGlobals)
		(= gameState 0)
		(super init:)
		(StrCpy @sysLogPath {})
		(InitUserName)
		(= ego egoObj)
		(= theBookEnd BookEnd)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: 0
			canInput: 0
		)
		(LoadMany SCRIPT
			PROCS PCHASE POLYPATH POLYGON SIGHT PAVOID SMOOPER OSC MAP
		)
		(= showStyle HSHUTTER)
		(= doVerbCode DoVerbCode)
		(= ftrInitializer FtrInit)
		(= pMouse PseudoMouse)
		((= keyDownHandler KH) add:)
		((= directionHandler DH) add:)
		((= mouseDownHandler MH) add:)
		(= useSortedFeatures FALSE)
		(self setCursor: theCursor TRUE 0 0)
		(= waitCursor 69)
		(= possibleScore 100)
		(= userFont 82)
		(globalMusic owner: self init:)
		(globalMusic2 owner: self init:)
		(= theMusic globalMusic)
		(= theMusic2 globalMusic2)
		(= theWalkMusic walkMusic)
		(= theZapCursor zapCursor)
		(= version {x.yyy____})
		(= numVoices (DoSound NumVoices))
		(= numColors (Graph GDetect))
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 9 versionFile)
		(FileIO fileClose versionFile)
		((= theIconBar IconBar)
			add: iconDo iconLook iconUse iconBook iconMap iconControl iconWhat
			eachElementDo: #init
			curIcon: iconDo
			useIconItem: iconUse
			helpIconItem: iconWhat
			state: (| (IconBar state?) NOCLICKHELP)
		)
		(GameControls
			window: gcWindow
			add:
				(iconSave
					theObj: self
					selector: #save
					yourself:
				)
				iconOk
				(iconAbout
					theObj: aboutCode
					selector: #doit
					yourself:
				)
				(iconQuit
					theObj: self
					selector: #quitGame
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: #masterVolume
					topValue: 15
					bottomValue: 0
					yStep: 2
					yourself:
				)
				(dummyIcon
					nsBottom: (+ (CelHigh 947 1 1) 6)
					nsRight: (- (+ 124 (CelWide 947 0 1)) 8)
					yourself:
				)
		)
		(= versionFile 100)
		(HandsOn)
		(self newRoom: versionFile)
	)
	
	(method (doit)
		(if (and iconBarActive (GameIsRestarting))
			(DrawIconScroll)
			(theIconBar show: disable:)
		)
		(super doit: &rest)
		(if demoPrintCued
			(Sound pause: TRUE)
			(switch
				(= demoPrintRet
					(Print
						{Would you like to watch\nthe demo or quit?\n}
						#icon godmother 0 0
						#at 30 60
						#new
						#button {Watch it} 0
						#new
						#button {Quit} 1
					)
				)
				(0
					(Sound pause: FALSE)
				)
				(1
					(= quit TRUE)
				)
			)
			(theGame setCursor: 69 FALSE 1000 1000)
			(= demoPrintCued FALSE)
			((User curEvent?) type: nullEvt)
		)
		(if ((User curEvent?) type?)
			(theGame setCursor: 80 TRUE 248 144)
			(= demoPrintCued TRUE)
		)
	)
	
	(method (startRoom roomNum)
		(User canInput: FALSE canControl: FALSE)
		(self setCursor: 69 TRUE)
		(if pMouse
			(pMouse stop:)
		)
		(if (storyControls size?)
			(storyControls dispose:)
		)
		(LoadMany FALSE
			POLYGON SIGHT MOVEFWD STOPWALK TIMER PAVOID FORCOUNT MAP
		)
		(if
			(and
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				(Print 0 2
					#button {Debug} 1
				)
			)
			(SetDebug)
		)
		(= global148 0)
		(= iconBarActive (not (OneOf roomNum 1 100 230 360 500)))
		(super startRoom: roomNum)
		(if iconBarActive
			(theIconBar show: enable:)
		else
			(theIconBar hide: disable:)
		)
		(theGame
			setCursor: ((theIconBar curIcon?) cursor?) TRUE 160 87
		)
	)
	
	(method (restore &tmp wB wC)
		(= wB (systemWindow back?))
		(= wC (systemWindow color?))
		(systemWindow back: 59 color: 66)
		(super restore: &rest)
		(systemWindow
			back: wB
			color: wC
		)
	)
	
	(method (save)
	)
	
	(method (quitGame)
		(PromptQuit)
	)
)

(class FairyIcon of IconItem
	(properties
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		highlightColor 5
		lowlightColor 69
	)
	
	(method (highlight tOrF param2 &tmp t l b r sColor)
		(if (not (& signal IB_ACTIVE)) (return))
		(= sColor
			(cond 
				((>= argc 2) param2)
				((and argc tOrF) highlightColor)
				(else lowlightColor)
			)
		)
		(= t (+ nsTop 1))
		(= l (+ nsLeft 1))
		(= b (- nsBottom 2))
		(= r (- nsRight 3))
		(Graph GDrawLine t l t r sColor -1 -1)
		(Graph GDrawLine t r b r sColor -1 -1)
		(Graph GDrawLine b r b l
			(if (and argc tOrF) sColor else (- sColor 2))
			-1
			-1
		)
		(Graph GDrawLine b l t l sColor -1 -1)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
	)
)

(instance iconBook of FairyIcon
	(properties
		view 900
		loop 6
		cel 0
		cursor 80
		helpStr {Click here to choose the title of this story.}
		maskView 900
		maskLoop 6
	)
	
	(method (select &tmp [temp0 3] temp3)
		(if (= temp3 (super select: &rest))
			(theIconBar state: (| (theIconBar state?) DISABLED))
			(if (storyControls size?)
				(storyControls show:)
			else
				(storyControls init: show:)
			)
			(theIconBar state: (& (theIconBar state?) (~ DISABLED)))
		)
		(return temp3)
	)
)

(instance iconMap of FairyIcon
	(properties
		view 900
		loop 5
		cel 0
		cursor 80
		message verbLook
		helpStr {Click here to look at the map.}
		maskView 900
		maskLoop 5
	)
	
	(method (select)
		(if (super select: &rest)
			(HandsOff)
			(curRoom setScript: (ScriptID MAP 0))
			(theIconBar curIcon: iconDo)
			(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		)
		(return TRUE)
	)
)

(instance iconDo of FairyIcon
	(properties
		view 900
		loop 1
		cel 0
		nsLeft 42
		nsTop 0
		nsRight 77
		nsBottom 26
		cursor 80
		message verbTalk
		signal  (| RELVERIFY HIDEBAR FIXED_POSN)
		helpStr {Click here to do something or talk to someone.}
		maskView 900
		maskLoop 1
	)
)

(instance iconLook of FairyIcon
	(properties
		view 900
		loop 2
		cel 0
		cursor 19
		message verbLook
		signal (| HIDEBAR RELVERIFY)
		helpStr {Click here to look at something.}
		maskView 900
		maskLoop 2
	)
)

(instance iconUse of FairyIcon
	(properties
		view 900
		loop 4
		cel 0
		cursor 80
		message verbUse
		helpStr {Click here to find out what you are carrying.}
		maskView 900
		maskLoop 4
	)
	
	(method (select relVer &tmp event whichCel thisIcon theLoop theView theCel)
		(return
			(cond 
				((& signal DISABLED) 0)
				((and argc relVer (& signal RELVERIFY))
					(= whichCel 1)
					(self highlight: 1 9)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(self highlight: 1 9)
								)
							)
							(whichCel
								(self highlight: 0)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if whichCel
						(self highlight: 0)
						(if (== (= thisIcon (theIconBar curInvIcon?)) 0)
							(Print 0 3)
						else
							(= theLoop (thisIcon loop?))
							(= theView (thisIcon view?))
							(= theCel (thisIcon cel?))
							(if (== theView 800)
								(= theLoop 0)
							else
								(++ theLoop)
							)
							(repeat
								(if
									(HighPrint (thisIcon description?)
										#window SysWindow
										#icon theView theLoop theCel
									)
									(break)
								)
							)
						)
					)
					whichCel
				)
				(else 1)
			)
		)
	)
)

(instance iconControl of FairyIcon
	(properties
		view 900
		loop 3
		cel 0
		cursor 80
		helpStr {Click here to end the game.}
		maskView 900
		maskLoop 3
	)
	
	(method (select)
		(if (super select:)
			(GameControls show:)
		)
		(return FALSE)
	)
)

(instance iconWhat of FairyIcon
	(properties
		view 900
		loop 7
		cel 0
		cursor 29
		message verbHelp
		helpStr {Click here to find out what things in the menu do.}
		maskView 900
		maskLoop 7
	)
)

(instance DoVerbCode of Code

	(method (doit theVerb theObj &tmp objDesc underBits theX theY l t r b)
		(cond 
			((== theVerb verbTalk)
				((User curEvent?) claimed: FALSE)
			)
			((and (= objDesc (theObj description?)) (== theVerb verbLook))
				(if (and (theObj facingMe: ego) (theObj lookStr?))
					(= theX (theObj x?))
					(Measure userFont objDesc)
					(if (< theX 160)
						(= theX (+ theX 20))
					else
						(= theX (- theX (+ global227 20)))
					)
					(= theY (theObj y?))
					(if (> (theObj y?) 140)
						(= theY 140)
					)
					(if (< (theObj y?) 35)
						(= theY 35)
					)
					(= l (- theX 5))
					(= r (+ theX 8 global227))
					(= t (- theY 35))
					(= b (+ theY -27 global228))
					(= underBits (Graph GSaveBits t l b r VMAP))
					(Graph GFillRect t l b r VMAP
						(SysWindow back?)
						-1
						-1
					)
					(Graph GShowBits t l b r VMAP)
					(Display objDesc
						p_at theX (- theY 30)
						p_font userFont
						p_color
						(SysWindow color?)
					)
					(LowPrint (theObj lookStr?))
					(Graph GRestoreBits underBits)
					(Graph GShowBits t l b r VMAP)
				)
			)
			(else
				(VerbFail)
			)
		)
	)
)

(instance FtrInit of Code

	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 90)
		)
		(if (== (obj actions?) ftrDefault)
			(obj actions: 0)
		)
	)
)

(instance gcWindow of BorderWindow
	
	(method (open &tmp [temp0 28])
		(self
			top: (/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
			left: (/ (- 320 (- (+ 124 (CelWide 947 0 1)) 8)) 2)
			bottom:
				(+
					(CelHigh 947 1 1)
					6
					(/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
				)
			right:
				(+
					(- (+ 124 (CelWide 947 0 1)) 8)
					(/ (- 320 (- (+ 124 (CelWide 947 0 1)) 8)) 2)
				)
			priority: 15
			back: 59
			color: 59
			topBordColor: 54
			lftBordColor: 54
			rgtBordColor: 0
			botBordColor: 0
		)
		(super open:)
		(DrawCel 947 1 1 4 3 15)
		(DrawCel 947 1 0 107 16 15)
		(DrawCel 947 0 3 76 (- 15 (+ (CelHigh 947 0 4) 3)) 15)
	)
)

(instance volumeSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 80
		nsTop 15
		signal FIXED_POSN
		helpStr {Adjusts sound volume.}
		sliderView 947
		topValue 15
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		signal $01c3
		helpStr {Saves your current game.}
		highlightColor 5
		lowlightColor 58
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Exits the game.}
		highlightColor 5
		lowlightColor 58
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		nsLeft 8
		nsTop 46
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Information about the game.}
		highlightColor 5
		lowlightColor 58
	)
)

(instance iconOk of IconItem
	(properties
		view 947
		loop 8
		cel 0
		nsLeft 8
		nsTop 26
		cursor 29
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Exits this menu.}
		highlightColor 5
		lowlightColor 58
	)
)

(instance dummyIcon of IconItem
	
	(method (show)
	)
	
	(method (select)
	)
	
	(method (highlight)
	)
)

(instance aboutCode of Code
	
	(method (doit &tmp [str 300])
		(Print
			(Format @str 0 4 version)
			#mode teJustCenter
			#width 120
			#font 3
		)
		(Print 0 5
			#mode teJustCenter
			#width 120
			#font 3
		)
		(Print 0 6
			#mode teJustCenter
			#width 120
			#font 3
		)
		(Print 0 7
			#mode teJustCenter
			#width 120
			#font 3
		)
	)
)

(instance iconJack of CodeIcon
	(properties
		view 840
		loop 1
		cel 0
		nsLeft 1
		nsTop 61
		cursor 80
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY)
		helpStr {This icon is for selecting an ego.}
		maskView 840
		maskLoop 1
		maskCel 1
		highlightColor 5
		lowlightColor 58
	)
	
	(method (show)
		(super show: &rest)
		(= nsRight (+ nsLeft 177))
		(= nsBottom (+ nsTop 18))
	)
	
	(method (select)
		(if (super select: &rest)
			(SelectStory theJackStory)
		)
	)
)

(instance iconCind of CodeIcon
	(properties
		view 840
		loop 2
		cel 0
		nsLeft 1
		nsTop 80
		cursor 80
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY)
		helpStr {This icon is for selecting an ego.}
		maskView 840
		maskLoop 2
		maskCel 1
		highlightColor 5
		lowlightColor 58
	)
	
	(method (show)
		(super show: &rest)
		(= nsRight (+ nsLeft 177))
		(= nsBottom (+ nsTop 18))
	)
	
	(method (select)
		(if (super select: &rest)
			(SelectStory theCindStory)
		)
	)
)

(instance iconSnow of CodeIcon
	(properties
		view 840
		loop 3
		cel 0
		nsLeft 1
		nsTop 99
		cursor 80
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY)
		helpStr {This icon is for selecting an ego.}
		maskView 840
		maskLoop 3
		maskCel 1
		highlightColor 5
		lowlightColor 58
	)
	
	(method (show)
		(super show: &rest)
		(= nsRight (+ nsLeft 177))
		(= nsBottom (+ nsTop 18))
	)
	
	(method (select)
		(if (super select: &rest)
			(SelectStory theSnowStory)
		)
	)
)

(instance iconBeauty of CodeIcon
	(properties
		view 840
		loop 4
		cel 0
		nsLeft 1
		nsTop 118
		cursor 80
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY)
		helpStr {This icon is for selecting an ego.}
		maskView 840
		maskLoop 4
		maskCel 1
		highlightColor 5
		lowlightColor 58
	)
	
	(method (show)
		(super show: &rest)
		(= nsRight (+ nsLeft 177))
		(= nsBottom (+ nsTop 18))
	)
	
	(method (select)
		(if (super select: &rest)
			(SelectStory theBeautyStory)
		)
	)
)

(instance iconBremen of CodeIcon
	(properties
		view 840
		loop 5
		cel 0
		nsLeft 1
		nsTop 137
		cursor 80
		signal (| VICON FIXED_POSN HIDEBAR RELVERIFY)
		helpStr {This icon is for selecting an ego.}
		maskView 840
		maskLoop 5
		maskCel 1
		highlightColor 5
		lowlightColor 58
	)
	
	(method (show)
		(super show: &rest)
		(= nsRight (+ nsLeft 177))
		(= nsBottom (+ nsTop 18))
	)
	
	(method (select)
		(if (super select: &rest)
			(SelectStory theBremenStory)
		)
	)
)

(instance storyControls of GameControls
	(properties
		stopSound 1
	)
	
	(method (init)
		(= window storyWindow)
		(self
			add: iconJack iconCind iconSnow iconBeauty iconBremen
		)
		(super init: &rest)
	)
	
	(method (show &tmp evt)
		(if
			(and
				(not (& (iconJack signal?) DISABLED))
				(== (theJackStory selected?) TRUE)
			)
			(iconJack signal: (| (iconJack signal?) DISABLED))
		)
		(if
			(and
				(== (theJackStory done?) TRUE)
				(!= (iconJack maskCel?) 2)
			)
			(iconJack maskCel: 2)
		)
		(if
			(and
				(not (& (iconCind signal?) DISABLED))
				(== (theCindStory selected?) TRUE)
			)
			(iconCind signal: (| (iconCind signal?) DISABLED))
		)
		(if
			(and
				(== (theCindStory done?) TRUE)
				(!= (iconCind maskCel?) 2)
			)
			(iconCind maskCel: 2)
		)
		(if
			(and
				(not (& (iconSnow signal?) DISABLED))
				(== (theSnowStory selected?) TRUE)
			)
			(iconSnow signal: (| (iconSnow signal?) DISABLED))
		)
		(if
			(and
				(== (theSnowStory done?) TRUE)
				(!= (iconSnow maskCel?) 2)
			)
			(iconSnow maskCel: 2)
		)
		(if
			(and
				(not (& (iconBeauty signal?) DISABLED))
				(== (theBeautyStory selected?) TRUE)
			)
			(iconBeauty signal: (| (iconBeauty signal?) DISABLED))
		)
		(if
			(and
				(== (theBeautyStory done?) TRUE)
				(!= (iconBeauty maskCel?) 2)
			)
			(iconBeauty maskCel: 2)
		)
		(if
			(and
				(not (& (iconBremen signal?) DISABLED))
				(== (theBremenStory selected?) TRUE)
			)
			(iconBremen signal: (| (iconBremen signal?) DISABLED))
		)
		(if
			(and
				(== (theBremenStory done?) TRUE)
				(!= (iconBremen maskCel?) 2)
			)
			(iconBremen maskCel: 2)
		)
		(while ((= evt (Event new:)) type?)
			(evt dispose:)
		)
		(evt dispose:)
		(super show: &rest)
		(while ((= evt (Event new:)) type?)
			(evt dispose:)
		)
		(evt dispose:)
	)
	
	(method (dispatchEvent event &tmp temp0)
		(= temp0
			(cond 
				((== (event type?) mouseDown))
				((== (event type?) keyDown) (== (event message?) ENTER))
			)
		)
		(super dispatchEvent: event &rest)
		(return
			(if temp0
				(return TRUE)
			else
				FALSE
			)
		)
	)
)

(instance storyWindow of SysWindow
	(properties
		top 11
		left 70
		bottom 169
		right 251
	)
	
	(method (open)
		(super open: &rest)
		(DrawCel 840 0 0 0 0 15)
	)
)

(instance globalMusic of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance globalMusic2 of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance walkMusic of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 6 setLoop: 1 play: self)
			)
			(1
				(theMusic2 number: 7 play: self)
			)
			(2
				(theMusic2 number: 11 play: self)
			)
			(3
				(theMusic2 number: 11 play: self)
			)
			(4
				(self changeState: 0)
			)
		)
	)
)

(instance zapCursor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				(theGame setCursor: 69 TRUE 304 172)
				(User canInput: FALSE canControl: FALSE)
				(self dispose:)
			)
		)
	)
)

(instance godmother of DCIcon
	(properties
		view 618
	)
	
	(method (init)
		((= cycler (BegLoop new:)) init: self)
	)
)
