;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use LLEgo)
(use PMouse)
(use GControl)
(use IconBar)
(use Feature)
(use StopWalk)
(use DCIcon)
(use Grooper)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	LSL1 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	IsObjectOnControl 5
	Btst 6
	Bset 7
	Bclr 8
	Btoggle 9
	EgoDead 10
	SolvePuzzle 11
	Face 12
	proc0_14 14
	Babble 15
	EgoHeadMove 17
	GameHour 18
	GameMinutes 19
	GameSeconds 20
	ShowDeathIcon 21
	CheckItemOwner 22
	spraySound 23
	deathIcon 24
	icon0 25
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
	howFast
	globalSound
	global103
	global104 =  1
	global105
	numColors
	numVoices
	startingRoom
	global109
	theStopGroop
	gameFlags
		global112
		global113
		global114
		global115
		global116
		global117
		global118
	oldMouseX
	oldMouseY
	theEgoHead
	global122
	myTextColor
	myTextColor2
	myTextColor3
	myTextColor4
	myTextColor5
	myTextColor6
	myTextColor7
	myTextColor8
	myTextColor9
	myTextColor10
	myTextColor11
	myTextColor12
	myTextColor13
	myTextColor14
	myTextColor15
	myTextColor16
	myTextColor17
	myInsideColor
	myScoreColor
	myHighlightColor
	myLowlightColor
	myTopBordColor
	myBotBordColor
	myLftBordColor
	myRgtBordColor
	myBackColor
	global149
	global150
	global151 =  160
	global152 =  160
	currentTime
	oldSysTime
	spraySeconds
	global156
	sprayCount
	dollars
	debugging
	global160
	currentHotelFloor
	tvChannel
	global163
	cabFareMin =  10
	deathView =  807
	deathLoop =  1
	soundFx
	cIcon
	egoSpeed =  4
	str1
		global171
		global172
		global173
		global174
		global175
		global176
		global177
		global178
		global179
		global180
	str2
		global182
		global183
		global184
		global185
		global186
		global187
		global188
		global189
		global190
		global191
		global192
		global193
		global194
		global195
		global196
		global197
		global198
		global199
		global200
		global201
		global202
		global203
		global204
		global205
		global206
		global207
		global208
		global209
		global210
	str3
		global212
		global213
		global214
		global215
		global216
		global217
		global218
		global219
		global220
	str4
		global222
		global223
		global224
		global225
		global226
		global227
		global228
		global229
	str5
		global231
		global232
		global233
		global234
		global235
		global236
		global237
		global238
		global239
	str6
		global241
		global242
		global243
		global244
		global245
		global246
		global247
		global248
		global249
	str7
		global251
		global252
		global253
		global254
		global255
		global256
		global257
		global258
		global259
	str8
		global261
		global262
		global263
		global264
		global265
		global266
		global267
		global268
		global269
	str9
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
	giantFont
	editFont
)
(procedure (NormalEgo theLoop theView theStoppedView &tmp stoppedView)
	(= stoppedView 0)
	(ego view: 800)
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1)
			(ego view: theView)
			(if (> argc 2) (= stoppedView theStoppedView))
		)
	)
	(if (not stoppedView) (= stoppedView 809))
	(ego
		normal: TRUE
		moveHead: TRUE
		setLoop: -1
		setLoop: stopGroop
		setPri: -1
		setMotion: 0
		setCycle: StopWalk stoppedView
		setStep: 3 2
		illegalBits: cWHITE
		ignoreActors: 0
		userSpeed:
	)
)

(procedure (HandsOff &tmp oldCurIcon)
	(User canControl: 0 canInput: 0)
	(ego setMotion: 0)
	(= oldCurIcon (theIconBar curIcon?))
	(theIconBar disable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ITEM
		ICON_INVENTORY
		ICON_ZIPPER
		ICON_TASTE
	)
	(theIconBar curIcon: oldCurIcon)
	(if (not (HaveMouse))
		(= oldMouseX ((User curEvent?) x?))
		(= oldMouseY ((User curEvent?) y?))
		(theGame setCursor: waitCursor TRUE 310 180)
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
		ICON_ZIPPER
		ICON_TASTE
	)
	(if (not (theIconBar curInvIcon?))
		(theIconBar disable: ICON_ITEM)
	)
	(if (not (HaveMouse))
		(theGame
			setCursor: ((theIconBar curIcon?) cursor?) TRUE oldMouseX oldMouseY
		)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	(ego userSpeed:)
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
)

(procedure (IsObjectOnControl theObj theControl)
	(return
		(if (& (theObj onControl: origin) theControl) (return TRUE) else FALSE)
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
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
	(return oldState)
)

(procedure (Btoggle flagEnum)
	(if (Btst flagEnum) (Bclr flagEnum) else (Bset flagEnum))
)

;;;(procedure (EgoDead theString &tmp [temp0 80])
;;;	(asm
;;;		pushi    1
;;;		lea      @temp0
;;;		push    
;;;		&rest    theString
;;;		callk    Format,  2
;;;		pushi    #eachElementDo
;;;		pushi    1
;;;		pushi    151
;;;		lag      sounds
;;;		send     6
;;;		pushi    #number
;;;		pushi    1
;;;		pushi    190
;;;		pushi    97
;;;		pushi    1
;;;		pushi    127
;;;		pushi    6
;;;		pushi    1
;;;		pushi    1
;;;		pushi    102
;;;		pushi    1
;;;		pushi    1
;;;		pushi    42
;;;		pushi    0
;;;		lag      theMusic
;;;		send     28
;;;		pushi    5
;;;		pushi    1
;;;		lsg      deathView
;;;		pushi    6
;;;		pushi    1
;;;		lsg      deathLoop
;;;		pushi    214
;;;		pushi    1
;;;		lsg      howFast
;;;		ldi      1
;;;		add     
;;;		push    
;;;		ldi      4
;;;		mul     
;;;		push    
;;;		lofsa    deathIcon
;;;		send     18
;;;		pushi    #setCursor
;;;		pushi    2
;;;		pushi    999
;;;		pushi    1
;;;		lag      theGame
;;;		send     8
;;;		pushi    2
;;;		pushi    #elements
;;;		pushi    0
;;;		lag      cast
;;;		send     4
;;;		push    
;;;		pushi    0
;;;		callk    Animate,  4
;;;code_131a:
;;;		pushi    16
;;;		lea      @temp0
;;;		push    
;;;		pushi    30
;;;		pushi    1
;;;		pushi    81
;;;		lofsa    {Restore}
;;;		push    
;;;		pushi    1
;;;		pushi    81
;;;		lofsa    {Restart}
;;;		push    
;;;		pushi    2
;;;		pushi    81
;;;		lofsa    {____Quit____}
;;;		push    
;;;		pushi    3
;;;		pushi    82
;;;		lofsa    deathIcon
;;;		push    
;;;		pushi    80
;;;		lea      @str1
;;;		push    
;;;		calle    Print,  32
;;;		push    
;;;		dup     
;;;		ldi      1
;;;		eq?     
;;;		bnt      code_135b
;;;		pushi    #restore
;;;		pushi    0
;;;		lag      theGame
;;;		send     4
;;;		jmp      code_137b
;;;code_135b:
;;;		dup     
;;;		ldi      2
;;;		eq?     
;;;		bnt      code_136d
;;;		pushi    #restart
;;;		pushi    0
;;;		lag      theGame
;;;		send     4
;;;		jmp      code_137b
;;;code_136d:
;;;		dup     
;;;		ldi      3
;;;		eq?     
;;;		bnt      code_137b
;;;		ldi      1
;;;		sag      quit
;;;		jmp      code_137f
;;;code_137b:
;;;		toss    
;;;		jmp      code_131a
;;;code_137f:
;;;		ret     
;;;	)
;;;)

(procedure (EgoDead &tmp [deadBuf 80])
	(Format @deadBuf &rest)
	(sounds eachElementDo: #stop)
	(theMusic number: 190 vol: 127 loop: 1 flags: mNOPAUSE play:)
	(deathIcon
		view: deathView
		loop: deathLoop
		cycleSpeed: (* (+ howFast 1) 4)
	)
	(theGame setCursor: ARROW_CURSOR TRUE)
	(Animate (cast elements?) FALSE)
	(repeat
		(switch
			(Print @deadBuf
				#mode teJustCenter
				#button {Restore} 1
				#button {Restart} 2
				#button {____Quit____} 3
				#icon deathIcon
				#title @str1
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

(procedure (SolvePuzzle flagEnum points)
	(if (not (Btst flagEnum))
		(theGame changeScore: points)
		(Bset flagEnum)
		(pointsSound play:)
	)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY temp3)
	(= temp3 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3) (= temp3 both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= temp3 whoToCue))
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject temp3) temp3 else 0)
	)
)

(procedure (proc0_14 theObj theControl)
	;EO: this seems to be a duplicate of IsObjectOnControl
	(return
		(if (== (theObj onControl:) theControl) (return TRUE) else FALSE)
	)
)

(procedure (Babble theView theString moreStuff &tmp [str 500])
	(if (u< theString 1000)
		(GetFarText theString moreStuff @str)
	else
		(StrCpy @str theString)
	)
	(babbleIcon
		view: theView
		cycleSpeed: (* (+ howFast 1) 4)
	)
	(if (u< theString 1000)
		(Print @str &rest #icon babbleIcon 0 0)
	else
		(Print @str moreStuff &rest #icon babbleIcon 0 0)
	)
)

(procedure (EgoHeadMove theHead &tmp headView)
	(= headView 0)
	(if argc (= headView theHead) else (= headView 809))
	((= theEgoHead egoHead)
		init: ego
		view: headView
		cycleSpeed: 24
	)
)

(procedure (GameHour &tmp temp0)
	(return
		(if (> (= temp0 (>> currentTime $000c)) 9)
			(- temp0 10)
		else
			(+ temp0 2)
		)
	)
)

(procedure (GameMinutes)
	(return (& (>> currentTime $0006) $003f))
)

(procedure (GameSeconds)
	(return (& currentTime $003f))
)

(procedure (ShowDeathIcon theView theLoop theIcon)
	(Load SOUND 190)
	(Load VIEW (= deathView theView))
	(= deathLoop theLoop)
	(if (> argc 2) (= cIcon theIcon))
)

(procedure (CheckItemOwner item owner &tmp ret)
	(if (> argc 1)
		(= ret owner)
	else
		(= ret curRoomNum)
	)
	(return (== ((inventory at: item) owner?) ret))
)

(instance egoObj of LLEgo
	(properties
		name "ego"
		description {you}
		sightAngle 180
		lookStr {You are wearing the latest in fashion -- if you consider 1973 late!}
		view 800
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbTalk (Print 0 0))
			(verbDo
				(if (Btst fWearingLubber)
					(Bclr fWearingLubber)
					(ego put: iLubber 0)
					(SolvePuzzle fRemoveLubber 1)
					(Print 0 1)
				else
					(Print 0 2)
				)
			)
			(verbZipper (Print 0 3))
			(verbTaste
				(cond 
					((Btst fSmellsLikeDogPiss) (Print 0 4))
					((Random 0 1) (Print 0 5))
					(else (Print 0 6))
				)
			)
			(verbUse
				(switch theItem
					(iWallet (Print 0 7))
					(iBreathSpray
						(cond 
							((curRoom script?) (Print 0 8))
							(
							(and (!= (ego view?) 800) (!= (ego view?) 809)) (Print 0 8))
							(else (curRoom setScript: (ScriptID SPRAY 0)))
						)
					)
					(iWatch
						(Print 0 9)
						(Print 0 10 #at -1 140)
					)
					(iApple (Print 0 11) (ego put: iApple 0))
					(iRing (Print 0 12) (Print 0 13))
					(iWhiskey
						(ego put: iWhiskey 110)
						(= spraySeconds 10)
						(Print 0 14)
						(Print 0 15 #at -1 140)
					)
					(iRemoteControl (Print 0 16))
					(iRose (Print 0 17))
					(iLubber
						(if (Btst fWearingLubber)
							(Bclr fWearingLubber)
							(ego put: iLubber 0)
							(SolvePuzzle fRemoveLubber 1)
							(Print 0 1)
						else
							(Print 0 18)
						)
					)
					(iCandy (Print 0 19) (ego put: iCandy 0))
					(iPocketKnife (Print 0 20))
					(iWine
						(ego put: iWine 510)
						(= spraySeconds 15)
						(Print 0 21)
						(Print 0 22 #at -1 140)
					)
					(iHammer (Print 0 23))
					(iPills
						(Print 0 24)
						(Print 0 25)
						(Print 0 26)
						(ShowDeathIcon 103 0 1)
						(Format @str1 0 27)
						(EgoDead 0 28)
					)
					(iRibbon (Print 0 29))
					(else  (super doVerb: theVerb))
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance egoHead of Head
	(properties
		description {your head}
		view 809
	)
	
	(method (doVerb theVerb theItem)
		(if modelessDialog (modelessDialog dispose:))
		(switch theVerb
			(verbLook (Print 0 30))
			(verbDo (Print 0 31))
			(else 
				(ego doVerb: theVerb theItem)
			)
		)
	)
)

(instance pointsSound of Sound
	(properties
		flags mNOPAUSE
		number 821
	)
)

(instance spraySound of Sound
	(properties
		flags mNOPAUSE
		number 820
	)
)

(instance stopGroop of GradualLooper
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
		((= cycler (Forward new:)) init: self)
	)
)

(instance ll1KDHandler of EventHandler
	(properties)
)

(instance ll1MDHandler of EventHandler
	(properties)
)

(instance ll1DirHandler of EventHandler
	(properties)
)

(instance LSL1 of Game
	(properties)
	
	(method (init &tmp [temp0 7])
		(= theStopGroop stopGroop)
		(= systemWindow ll1Win)
		(= version {x.yyy})
		(super init:)
		(= doVerbCode ll1DoVerbCode)
		(= ftrInitializer ll1FtrInit)
		((= keyDownHandler ll1KDHandler) add:)
		((= mouseDownHandler ll1MDHandler) add:)
		((= directionHandler ll1DirHandler) add:)
		(= pMouse PseudoMouse)
		((= ego egoObj) _head: (= theEgoHead egoHead))
		((ego _head?) client: ego)
		((ScriptID LLINIT 0) init:)
		(UnLoad SCRIPT LLINIT)
		((= theMusic longSong) init: owner: self flags: mNOPAUSE)
		((= globalSound longSong2) init: owner: self flags: mNOPAUSE)
		((= soundFx longSong3) init: owner: self flags: mNOPAUSE)
		((ScriptID 814 0) init:)
		((= theIconBar IconBar)
			invLoop: 0
			add: icon0 icon1 icon2 icon3 icon6 icon7 icon4 icon5 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor myHighlightColor
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
		)
		(icon5 message: (if (HaveMouse) SHIFTTAB else TAB))
		(gcWin color: 0 back: myBackColor)
		(GameControls
			window: gcWin
			add:
				iconOk
				(detailSlider
					theObj: theGame
					selector: #detailLevel
					topValue: 3
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: theGame
					selector: #masterVolume
					topValue: (if (> numVoices 1) 15 else 1)
					bottomValue: 0
					yourself:
				)
				(speedSlider
					theObj: speedORama
					selector: #doit
					topValue: 0
					bottomValue: 5
					yourself:
				)
				(iconSave theObj: theGame selector: #save yourself:)
				(iconRestore theObj: theGame selector: #restore yourself:)
				(iconRestart theObj: theGame selector: #restart yourself:)
				(iconQuit theObj: theGame selector: #quitGame yourself:)
				(iconAbout
					theObj: (ScriptID ABOUT 0)
					selector: #doit
					yourself:
				)
				iconHelp
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #lowlightColor myBackColor
			helpIconItem: iconHelp
			curIcon: iconSave
		)
		(if (GameIsRestarting)
			(= startingRoom 100)
		else
			(= startingRoom 710)
		)
		(theDefaultFeature init:)
		(self newRoom: SPEED)
	)
	
	(method (doit)
		;keep the time going
		(if (!= oldSysTime (GetTime SYSTIME1))
			(= oldSysTime (GetTime SYSTIME1))
			(if
				(and
					(== (& (++ currentTime) $003c) 60)
					(==
						(&
							(= currentTime
								(& (= currentTime (+ currentTime 64)) $ffc0)
							)
							$0f00
						)
						3840
					)
					(==
						(&
							(= currentTime
								(& (= currentTime (+ currentTime 4096)) $f03f)
							)
							$d000
						)
						-12288
					)
				)
				(= currentTime 4096)
			)
			;decrease breath spray timer, unless in the endgame
			(if
				(and
					(> spraySeconds 1)
					(!= curRoomNum 710)
					(!= curRoomNum 720)
					(!= curRoomNum 190)
					(not (Btst fFollowingEve))
					(== (-- spraySeconds) 1)
				)
				;have bad breath
				(= spraySeconds 0)
				(Bset fMouthSmellsBad)
				(if (not (Btst fSprayDone))
					(if modelessDialog (modelessDialog dispose:))
					(switch (Random 1 5)
						(1 (Print 0 42))
						(2 (Print 0 43))
						(3 (Print 0 44))
						(4 (Print 0 45))
						(5 (Print 0 46))
					)
				)
			)
			;no money left, kill ego off
			(if (and (< dollars 1) (not (Btst fGambling)))
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(Print 0 47)
				(ShowDeathIcon 807 1)
				(Format @str1 0 48)
				(EgoDead 0 49)
			)
		)
		(super doit: &rest)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		(if modelessDialog
			(modelessDialog dispose:)
		)
		((ScriptID DISPOSE) doit: roomNum)
		(UnLoad SCRIPT DISPOSE)
		(if
			;sidewalk rooms
			(OneOf roomNum 100 300 400 500 600)
			(ScriptID rgSidewalk)
		)
		(if
			;close-up areas; replace Walk with Exit
			(OneOf roomNum 505 385 355 615 200 250 260 371)
			(icon0 loop: 15 cursor: 106)
		else
			(icon0 loop: 0 cursor: 100)
		)
		(if (== (theIconBar curIcon?) icon0)
			(theGame setCursor: (icon0 cursor?) TRUE)
		)
		(CueObj client: 0 theVerb: verbNone theInvItem: 0)
		(super startRoom: roomNum)
		(if (cast contains: ego)
			(if
				(and
					(ego normal?)
					(not ((ego cycler?) isKindOf: StopWalk))
				)
				(ego setCycle: StopWalk 809)
			)
			(if (not (ego looper?)) (ego setLoop: stopGroop))
			(EgoHeadMove (egoHead view?))
		)
	)
	
	(method (restart)
		(curRoom style: IRISIN drawPic: 720)
		(cast eachElementDo: #hide)
		(super restart:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if
				(or
					(== curRoomNum 710)
					(== curRoomNum 720)
					(== curRoomNum 803)
				)
				(return (event claimed: TRUE))
			else
				(switch (event type?)
					(keyDown
						(switch (event message?)
							(`#7
								(theGame restore:)
								(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
								(event claimed: TRUE)
							)
							(`^q
								(theGame quitGame:)
								(event claimed: TRUE)
							)
							(`+
								(if (> (theGame egoMoveSpeed?) 0)
									(theGame egoMoveSpeed: (- (theGame egoMoveSpeed?) 1))
									(if (User canControl:)
										(ego egoSpeed: (theGame egoMoveSpeed?))
									)
								)
							)
							(`-
								(if (< (theGame egoMoveSpeed?) 15)
									(theGame egoMoveSpeed: (+ (theGame egoMoveSpeed?) 1))
									(if (User canControl:)
										(ego egoSpeed: (theGame egoMoveSpeed?))
									)
								)
							)
							(`=
								(theGame egoMoveSpeed: egoSpeed)
								(if (User canControl:)
									(ego egoSpeed: (theGame egoMoveSpeed?))
								)
							)
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
							(`^c
								(theGame setCursor: ARROW_CURSOR TRUE)
								(icon8 select:)
							)
							(`#2
								(cond 
									((theGame masterVolume:) (theGame masterVolume: 0))
									((> numVoices 1) (theGame masterVolume: 15))
									(else (theGame masterVolume: 1))
								)
								(event claimed: TRUE)
							)
							(`#5
								(theGame save:)
								(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
								(event claimed: TRUE)
							)
						)
					)
				)
			)
		)
	)
	
	(method (quitGame)
		(babbleIcon
			view: 853
			loop: 0
			cycleSpeed: (* (+ howFast 1) 4)
		)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(Animate (cast elements?) FALSE)
		(super
			quitGame:
				(Print 0 41
					#button {Stop Whining} 1
					#button {Oh, All Right} 0
					#icon babbleIcon
					#title {Don't give up. It's still early!}
				)
		)
	)
	
	(method (pragmaFail &tmp theVerb theObj)
		(if (User canInput:)
			(= theVerb ((User curEvent?) message?))
			(if (IconBar curInvIcon?)
				(= theObj
					((IconBar curInvIcon?) description?)
				)
			else
				(= theObj
					(theDefaultFeature description?)
				)
			)
			(if modelessDialog (modelessDialog dispose:))
			(switch theVerb
				(verbLook (Print 0 32))
				(verbTalk (Print 0 33))
				(verbDo (Print 0 34))
				(verbUse
					(switch theObj
						(((Inventory at: iRibbon) description?)
							(Print 0 35)
						)
						(((Inventory at: iPocketKnife) description?)
							(Print 0 36)
							(Print 0 37 #at -1 140)
						)
						(else 
							(Printf 0 38 theObj)
						)
					)
				)
				(verbZipper
					(cond 
						((curRoom script?) (Print 0 8))
						(
						(and (!= (ego view?) 800) (!= (ego view?) 809)) (Print 0 8))
						(else (Print 0 39))
					)
				)
				(verbTaste (Print 0 40))
			)
		)
	)
)

(instance speedORama of Code
	(properties)
	
	(method (doit newSpeed)
		(if argc
			(theGame egoMoveSpeed: newSpeed)
			(if (User canControl:)
				(ego egoSpeed: (theGame egoMoveSpeed?))
			)
		)
		(theGame egoMoveSpeed?)
	)
)

(instance deathIcon of DCIcon
	(properties)
	
	(method (init)
		(if cIcon
			((= cycler (EndLoop new:)) init: self)
		else
			((= cycler (Forward new:)) init: self)
		)
	)
)

(instance theDefaultFeature of Feature
	(properties
		description {it}
	)
)

(instance icon0 of IconItem
	(properties
		view 850
		loop 0
		cel 0
		cursor 100
		message verbWalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to move your character.}
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: myBotBordColor)
		(super init:)
	)
)

(instance icon1 of IconItem
	(properties
		view 850
		loop 1
		cel 0
		cursor 101
		message verbLook
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to look at things.}
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: myRgtBordColor)
		(super init:)
	)
)

(instance icon2 of IconItem
	(properties
		view 850
		loop 2
		cel 0
		cursor 102
		message verbDo
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to do things.}
		maskView 850
		maskLoop 14
	)
	
	(method (init)
		(self lowlightColor: myTextColor16)
		(super init:)
	)
)

(instance icon3 of IconItem
	(properties
		view 850
		loop 3
		cel 0
		cursor 103
		message verbTalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {Use this icon to talk to other characters.}
		maskView 850
		maskLoop 14
		maskCel 3
	)
	
	(method (init)
		(self lowlightColor: myTextColor9)
		(super init:)
	)
)

(instance icon4 of IconItem
	(properties
		view 850
		loop 4
		cel 0
		cursor ARROW_CURSOR
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {Select this icon to use the current inventory object.}
		maskView 850
		maskLoop 14
		maskCel 4
	)
	
	(method (init)
		(self lowlightColor: myTextColor16)
		(super init:)
	)
)

(instance icon5 of IconItem
	(properties
		view 850
		loop 5
		cel 0
		cursor ARROW_CURSOR
		message verbNone
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Use this icon to bring up the inventory window.}
		maskView 850
		maskLoop 14
		maskCel 2
	)
	
	(method (init)
		(self lowlightColor: myTextColor12)
		(super init:)
	)
	
	(method (select)
		(if (super select: &rest) (Inventory showSelf: ego))
	)
)

(instance icon6 of IconItem
	(properties
		view 850
		loop 10
		cel 0
		cursor 104
		message verbZipper
		signal (| HIDEBAR RELVERIFY)
		helpStr {Larry! You know what this is for!}
		maskView 850
		maskLoop 14
	)
	
	(method (init)
		(self lowlightColor: myTextColor13)
		(super init:)
	)
)

(instance icon7 of IconItem
	(properties
		view 850
		loop 11
		cel 0
		cursor 105
		message verbTaste
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for tasting and smelling.}
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: myRgtBordColor)
		(super init:)
	)
)

(instance icon8 of IconItem
	(properties
		view 850
		loop 7
		cel 0
		cursor ARROW_CURSOR
		message verbShowControls
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the control panel.}
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: myBotBordColor)
		(super init:)
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
		view 850
		loop 9
		cel 0
		cursor 150
		message verbHelp
		signal (| RELVERIFY IMMEDIATE)
		helpStr {This icon tells you about other icons.}
		maskView 850
		maskLoop 14
	)
	
	(method (init)
		(self lowlightColor: myTextColor9)
		(super init:)
	)
)

(instance ll1DoVerbCode of Code
	(properties)
	
	(method (doit theVerb theObj theItem &tmp theObjDescription theItemDescription)
		(if (theObj description?)
			(= theObjDescription (theObj description?))
		else
			(= theObjDescription
				(theDefaultFeature description?)
			)
		)
		(if (inventory at: theItem)
			(= theItemDescription
				((inventory at: theItem) description?)
			)
		else
			(= theItemDescription
				(theDefaultFeature description?)
			)
		)
		(if modelessDialog (modelessDialog dispose:))
		(if (theObj facingMe: ego)
			(switch theVerb
				(verbLook
					(if (theObj lookStr?)
						(Print (theObj lookStr?))
					else
						(Printf 0 50 theObjDescription)
					)
				)
				(verbTalk
					(Printf 0 51 theObjDescription)
				)
				(verbDo
					(Printf 0 52 theObjDescription)
				)
				(verbUse
					(switch theItemDescription
						(((inventory at: iRibbon) description?)
							(Printf 0 53 theObjDescription)
						)
						(((inventory at: iPocketKnife) description?)
							(Print 0 54)
						)
						(else 
							(Printf 0 55 theItemDescription theObjDescription)
						)
					)
				)
				(verbZipper
					(Printf 0 56 theObjDescription)
				)
				(verbTaste
					(Printf 0 57 theObjDescription)
				)
			)
		)
	)
)

(instance longSong of Sound
	(properties)
)

(instance longSong2 of Sound
	(properties)
)

(instance longSong3 of Sound
	(properties)
)

(instance ll1FtrInit of Code
	(properties)
	
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 90)
		)
		(if (== (theObj actions?) ftrDefault) (theObj actions: 0))
	)
)

(instance ll1Win of Window
	(properties)
)

(instance gcWin of Window
	(properties)
	
	(method (open &tmp [temp0 14] [scoreBuf 15] [scoreRect 4])
		(self
			top: (/ (- 200 (+ (CelHigh 852 1 1) 6)) 2)
			left: (/ (- 320 (+ 151 (CelWide 852 0 1))) 2)
			bottom:
				(+
					(CelHigh 852 1 1)
					6
					(/ (- 200 (+ (CelHigh 852 1 1) 6)) 2)
				)
			right:
				(+
					151
					(CelWide 852 0 1)
					(/ (- 320 (+ 151 (CelWide 852 0 1))) 2)
				)
			priority: 15
		)
		(super open:)
		(DrawCel
			852
			0
			5
			(+
				(/
					(-
						(- (+ 151 (CelWide 852 0 1)) (+ 4 (CelWide 852 1 1)))
						(CelWide 852 0 5)
					)
					2
				)
				4
				(CelWide 852 1 1)
			)
			6
			15
		)
		(DrawCel 852 1 1 4 3 15)
		(DrawCel 852 1 0 94 38 15)
		(DrawCel 852 1 0 135 38 15)
		(DrawCel 852 0 4 63 (- 37 (+ (CelHigh 852 0 4) 3)) 15)
		(DrawCel 852 0 3 101 (- 37 (+ (CelHigh 852 0 4) 3)) 15)
		(DrawCel 852 0 2 146 (- 37 (+ (CelHigh 852 0 4) 3)) 15)
		(Format @scoreBuf 0 58 score possibleScore)
		(TextSize @scoreRect @scoreBuf 999 0)
		(Display @scoreBuf
			p_font userFont
			p_color myScoreColor
			p_at
			(+
				10
				(CelWide 852 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 852 0 1))
							(+ 10 (CelWide 852 1 1) 6)
						)
						[scoreRect 3]
					)
					2
				)
			)
			(+ 46 (CelHigh 852 0 1) 3)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 852
		loop 0
		cel 1
		nsLeft 67
		nsTop 37
		signal FIXED_POSN
		helpStr {Lower this if game play seems sluggish. Raise it to increase the amount of background animation.}
		sliderView 852
		topValue 3
	)
)

(instance volumeSlider of Slider
	(properties
		view 852
		loop 0
		cel 1
		nsLeft 107
		nsTop 37
		signal FIXED_POSN
		helpStr {This adjusts the volume on some sound boards and synthesizers.}
		sliderView 852
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 852
		loop 0
		cel 1
		nsLeft 147
		nsTop 37
		signal FIXED_POSN
		helpStr {This adjusts Larry's speed, within the limits of your computer's capabilities.}
		sliderView 852
		bottomValue 15
	)
)

(instance iconSave of ControlIcon
	(properties
		view 852
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Use this to save the current state of your game. When you later select Restore, everything will be exactly as it is now.}
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 852
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {This restores a game you saved earlier.}
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 852
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Use this to restart the game from the very beginning.}
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 852
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Use this to leave the game.}
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 852
		loop 6
		cel 0
		nsLeft 8
		nsTop 86
		message 9
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Here's where you learn more than you care to know about this game.}
	)
)

(instance iconHelp of IconItem
	(properties
		view 852
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 150
		message verbHelp
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr {This helps explain the other icons.}
	)
)

(instance iconOk of IconItem
	(properties
		view 852
		loop 8
		cel 0
		nsLeft 8
		nsTop 106
		cursor ARROW_CURSOR
		message verbHideControls
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR) 
		helpStr {Use this to exit this menu and resume game play.}
	)
)

(class Person of Actor
	(properties
		complained 0
	)
	
	(method (doit)
		(super doit:)
		(cond 
			((Btst fToiletPaperOnShoe)
				(if (< (self distanceTo: ego) 30)
					(Bclr fToiletPaperOnShoe)
					(if modelessDialog (modelessDialog dispose:))
					(Print 0 59)
					(Print 0 60)
				)
			)
			((Btst fSmellsLikeDogPiss)
				(if (< (self distanceTo: ego) 30)
					(Bclr fSmellsLikeDogPiss)
					(if modelessDialog (modelessDialog dispose:))
					(Print 0 4)
				)
			)
			(
				(and
					(not spraySeconds)
					(Btst fMouthSmellsBad)
					(not complained)
					(< (self distanceTo: ego) 30)
				)
				(self complained: TRUE)
				(if modelessDialog (modelessDialog dispose:))
				(Print 0 61)
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if modelessDialog (modelessDialog dispose:))
				(Print 0 62)
			)
			(verbUse
				(Printf 0 63 ((Inventory at: theItem) description?) description)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)
