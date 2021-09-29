;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use myWindow)
(use TurnLooper)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Follow)
(use Grooper)
(use Sound)
(use Save)
(use Motion)
(use File)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	kq1 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	CantReach 4
	DontHave 6
	RedrawCast 7
	SetItemOwner 8
	LogIt 9
	Bset 10
	Bclr 11
	Btst 12
	ShowDeadGoat 13
	MouseClaimed 14
	Face 15
	EgoDead 16
	proc0_17 17
	proc0_18 18
	SolvePuzzle 19
	BucketState 20
	gameSound 21
	CantDo 22
	backSound 23
	CheckHowFast 24
	PlayBackgroundMusic 25
	FadeBackgroundMusic 26
	PebbleCount 27
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
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	volume =  12
	version
	locales
	curSaveDir
		global31
		global32
		global33
		global34
		global35
		global36
		global37
		global38
		global39
		global40
		global41
		global42
		global43
		global44
		global45
		global46
		global47
		global48
		global49
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	demoScripts
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime =  3
	currentPalette
	modelessPort
	sysLogPath
		global64
		global65
		global66
		global67
		global68
		global69
		global70
		global71
	endSysLogPath
		global73
		global74
		global75
		global76
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
	machineSpeed
	howFast
	startingRoom
	isHandsOff
	numColors
	global105
	oldCursor
	global107
	global108
	global109
	global110
	global111
	global112
	global113
	global114
	global115
	global116
	global117
	debugging
	roomWithDeadGoat
	deadGoatX
	deadGoatY
	deadGoatLoop
	theCarrier
	haloTimer
	menaceWaiting =  801
	theMenace
	global127
	theGoat
	roomWithLiveGoat =  11
	egoInWater
	roomWithBeanstalk
	deadGiantX =  -1
	deadGiantY =  -1
	invisibleRingTimer =  2400
	gnomeNameGuesses
	swimTimer
	oldIllegalBits
	menaceInRoom
	global139
	numPebbles
	global141
	global142
	global143
	global144
	global145
	global146
	global147
	global148
	global149
	gameFlags
		global151
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
		global168
		global169
		global170
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
		global181
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
)
(procedure (NormalEgo &tmp stoppedView mini)
	(= mini (Btst fLittleEgo))
	(= stoppedView
		(cond 
			((Btst fInvisible)
				(if mini 39 else 37)
			)
			(mini 7)
			(else 2)
		)
	)
	(ego
		view: (cond 
			((Btst fInvisible) (if mini 38 else 36))
			(mini 4)
			(else 0)
		)
		setLoop: -1
		setPri: -1
		setMotion: 0
		setStep: (if mini 2 else 3) 2
		looper: GradualLooper
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: 0
		setCycle: StopWalk stoppedView
	)
	(Load VIEW stoppedView)
)

(procedure (HandsOff)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(theGame setCursor: HAND_CURSOR TRUE)
	(TheMenuBar state: FALSE)
	(= isHandsOff TRUE)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(theGame setCursor: ARROW_CURSOR (HaveMouse))
	(TheMenuBar state: TRUE)
	(= isHandsOff FALSE)
)

(procedure (CantReach)
	(Print 0 131)
)

(procedure (DontHave)
	(Print 0 132)
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (SetItemOwner item owner)
	((inventory at: item)
		owner: (if (== argc 1) curRoomNum else owner)
	)
)

(procedure (LogIt what why &tmp [str 80])
	(= str 0)
	(logFile
		name: @sysLogPath
		writeString: (Format @str 0 130 curRoomNum why what)
		close:
	)
)

(procedure (Bset flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bclr flagEnum)
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
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

(procedure (ShowDeadGoat)
	(cond 
		((== roomWithDeadGoat curRoomNum)
			(theGoat
				view: 166
				setLoop: deadGoatLoop
				setCel: (- (NumCels theGoat) 1)
				posn: deadGoatX deadGoatY
				init:
				stopUpd:
			)
		)
		(
			(and
				(== roomWithDeadGoat (curRoom west?))
				(> deadGoatX 315)
				(OneOf roomWithDeadGoat 1 2 83 10 11 15 16)
				(OneOf curRoomNum 1 2 83 10 11 15 16)
			)
			(theGoat
				view: 166
				setLoop: deadGoatLoop
				setCel: (- (NumCels theGoat) 1)
				posn: (- deadGoatX 320) deadGoatY
				init:
				stopUpd:
			)
		)
		(
			(and
				(== roomWithDeadGoat (curRoom east?))
				(< deadGoatX 5)
				(OneOf roomWithDeadGoat 1 2 83 10 11 15 16)
				(OneOf curRoomNum 1 2 83 10 11 15 16)
			)
			(theGoat
				view: 166
				setLoop: deadGoatLoop
				setCel: (- (NumCels theGoat) 1)
				posn: (+ deadGoatX 320) deadGoatY
				init:
				stopUpd:
			)
		)
	)
)

(procedure (MouseClaimed event left top right bottom)
	(return
		(if
			(and
				(== (event type?) mouseDown)
				(& (event modifiers?) shiftDown)
				(< left (event x?))
				(< (event x?) right)
				(< top (event y?))
			)
			(< (event y?) bottom)
		else
			0
		)
	)
)

(procedure (Face actor1 actor2 both &tmp temp0 ang1to2)
	(= temp0 (actor1 loop?))
	(if (== argc 2)
		(DirLoop
			actor1
			(GetAngle
				(actor1 x?)
				(actor1 y?)
				(actor2 x?)
				(actor2 y?)
			)
		)
		(= ang1to2
			(GetAngle
				(actor1 x?)
				(actor1 y?)
				(actor2 x?)
				(actor2 y?)
			)
		)
	else
		(DirLoop
			actor1
			(GetAngle (actor1 x?) (actor1 y?) actor2 both)
		)
		(= ang1to2
			(GetAngle (actor1 x?) (actor1 y?) actor2 both)
		)
	)
	(if (!= temp0 (actor1 loop?))
		(actor1 setHeading: ang1to2)
		(actor1 forceUpd:)
		(RedrawCast)
	)
	(actor1 setMotion: 0)
)

;;;(procedure (EgoDead message)			
;;;	(asm
;;;		pushi    0
;;;		call     HandsOff,  0
;;;		pushi    #fade
;;;		pushi    0
;;;		lofsa    backSound
;;;		send     4
;;;		pushi    #fade
;;;		pushi    0
;;;		lofsa    gameSound
;;;		send     4
;;;		pushi    1
;;;		pushi    100
;;;		callk    Wait,  2
;;;		pushi    #eachElementDo
;;;		pushi    1
;;;		pushi    #stop
;;;		lag      sounds
;;;		send     6
;;;		pushi    #number
;;;		pushi    1
;;;		pushi    2
;;;		pushi    0
;;;		pushi    2
;;;		callk    Random,  4
;;;		push    
;;;		dup     
;;;		ldi      0
;;;		eq?     
;;;		bnt      code_2773
;;;		ldi      49
;;;		jmp      code_2788
;;;code_2773:
;;;		dup     
;;;		ldi      1
;;;		eq?     
;;;		bnt      code_277f
;;;		ldi      28
;;;		jmp      code_2788
;;;code_277f:
;;;		dup     
;;;		ldi      2
;;;		eq?     
;;;		bnt      code_2788
;;;		ldi      3
;;;code_2788:
;;;		toss    
;;;		push    
;;;		pushi    #loop
;;;		pushi    1
;;;		pushi    1
;;;		pushi    #priority
;;;		pushi    1
;;;		pushi    15
;;;		pushi    9
;;;		pushi    0
;;;		pushi    #play
;;;		pushi    0
;;;		lofsa    backSound
;;;		send     26
;;;		pushi    #setCursor
;;;		pushi    2
;;;		lsg      normalCursor
;;;		pushi    1
;;;		lag      theGame
;;;		send     8
;;;code_27a9:
;;;		pushi    11
;;;		&rest    message
;;;		pushi    #width
;;;		pushi    250
;;;		pushi    #button
;;;		lofsa    {Restore}
;;;		push    
;;;		pushi    1
;;;		pushi    #button
;;;		lofsa    { Restart_}
;;;		push    
;;;		pushi    2
;;;		pushi    #button
;;;		lofsa    { Quit_}
;;;		push    
;;;		pushi    3
;;;		calle    Print,  22
;;;		push    
;;;		dup     
;;;		ldi      1
;;;		eq?     
;;;		bnt      code_27de
;;;		pushi    #restore
;;;		pushi    0
;;;		lag      theGame
;;;		send     4
;;;		jmp      code_27fe
;;;code_27de:
;;;		dup     
;;;		ldi      2
;;;		eq?     
;;;		bnt      code_27f0
;;;		pushi    #restart
;;;		pushi    0
;;;		lag      theGame
;;;		send     4
;;;		jmp      code_27fe
;;;code_27f0:
;;;		dup     
;;;		ldi      3
;;;		eq?     
;;;		bnt      code_27fe
;;;		ldi      1
;;;		sag      quit
;;;		jmp      code_2802
;;;code_27fe:
;;;		toss    
;;;		jmp      code_27a9
;;;code_2802:
;;;		ret     
;;;	)
;;;)

(procedure (EgoDead)
	;EO: this is a recreation, based on the above commented-out assembly code
	(HandsOff)
	(backSound fade:)
	(gameSound fade:)
	(Wait 100)
	(sounds eachElementDo: #stop)
	(backSound number:
		(switch (Random 0 2)
			(0 49)
			(1 28)
			(2 3)
		)
		loop: 1
		priority: 15
		play:
	)
	(theGame setCursor: normalCursor TRUE)
	(repeat
		(switch
			(Print &rest
				#width 250
				#button {Restore} 1
				#button {Restart} 2
				#button {Quit} 3
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

(procedure (proc0_17 param1 param2 param3)
	(return
		(cond 
			((<= param2 param3) param3)
			((>= param2 param1) param1)
			(else param2)
		)
	)
)

(procedure (proc0_18 param1 param2 param3)
	(return
		(if (and (<= param3 param2) (<= param2 param1))
			(if (> (- param1 param2) (- param2 param3))
				param3
			else
				param1
			)
		else
			param2
		)
	)
)

(procedure (SolvePuzzle flagEnum points)
	(if (not (Btst flagEnum))
		(Bset flagEnum)
		(kq1 changeScore: points)
	)
)

(procedure (BucketState bucketIsFull)
	(if bucketIsFull
		(_Empty_Water_Bucket_
			name: { Full Water Bucket_}
			loop: 1
			description: {This old wooden bucket is now filled with clear water.}
		)
		(if (not (Btst fDragonDoused)) (SolvePuzzle fFilledBucket 2))
		(Bset fWaterInBucket)
	else
		(_Empty_Water_Bucket_
			name: { Empty Water Bucket_}
			loop: 0
			description: {You are holding an empty wooden bucket.}
		)
		(Bclr fWaterInBucket)
		(if (not (Btst fDragonDoused)) (Bclr fFilledBucket) (kq1 changeScore: -2))
	)
)

(procedure (CantDo)
	(if (Random 0 1) (Print 0 133) else (Print 0 134))
)

(procedure (CheckHowFast level)
	(return (>= howFast (if argc level else 2)))
)

(procedure (PlayBackgroundMusic song)
	(if (!= song (backSound number?))
		(if (== song 98)
			(backSound loop: 1 priority: 14)
		else
			(backSound loop: -1)
		)
		(backSound number: song play:)
	)
)

(procedure (FadeBackgroundMusic)
	(if (!= 98 (backSound number?)) (backSound fade:))
)

(procedure (PebbleCount)
	(switch (-= numPebbles 1)
		(0
			((inventory at: iPebbles) owner: 4)
		)
		(1 (_Pebbles_ name: { Pebble_}))
		(else 
			(_Pebbles_ name: { Pebbles_})
		)
	)
)

(instance gameSound of Sound
	(properties
		number 1
		priority 5
	)
)

(instance backSound of Sound
	(properties
		number 1
	)
)

(instance versionFile of File
	(properties
		name "version"
	)
)

(instance kq1 of Game
	(method (init &tmp [temp0 11])
		(= version {0.000.001})
		(if (!= (versionFile open: 1) 0)
			(versionFile read: version 9 close:)
		)
		(= systemWindow myWindow)
		(super init:)
		(= ego egoObj)
		(= theGoat goat)
		(= theMenace menace)
		(backSound owner: self init:)
		(User alterEgo: ego blocks: 0 x: -1 y: 16)
		(= showStyle 100)
		(= debugging TRUE)
		(= numColors (Graph GDetect))
		(theGame setSpeed: 5)
		(= possibleScore 158)
		(TheMenuBar init: hide:)
		(StatusLine code: statusCode disable:)
		(HandsOn)
		(StopWalk init:)
		(TurnLooper init:)
		(= userFont 300)
		(inventory
			add:
				_Dagger_
				_Chest_
				_Carrot_
				_Key_
				_Note_
				_Magic_Ring_
				_Four-leaf_Clover_
				_Ceramic_Bowl_
				_Empty_Water_Bucket_
				_Pebbles_
				_Leather_Slingshot_
				_Pouch_
				_Sceptre_
				_Cheese_
				_Magic_Mirror_
				_Gold_Egg_
				_Magic_Shield_
				_Fiddle_
				_Walnut_
				_Mushroom_
				_Beans_
		)
		(Feature shiftClick: allEvents longRangeDist: 500)
		(Bset fInCartoon)
		(= startingRoom 86)
		(if (GameIsRestarting)
			(TheMenuBar draw:)
			(StatusLine enable:)
			(= startingRoom 1)
			(theGame newRoom: 777)
		else
			(TheMenuBar state: TRUE)
			(theGame newRoom: 777)
		)
	)
	
	(method (doit &tmp temp0)
		(asm
			pushi    0
			callk    HaveMouse,  0
			sat      temp0
			pushi    #number
			pushi    0
			lofsa    backSound
			send     4
			push    
			ldi      98
			eq?     
			bnt      code_0289
			pushi    #signal
			pushi    0
			lofsa    backSound
			send     4
			push    
			ldi      65535
			eq?     
			bnt      code_0289
			pushi    1
			pushi    51
			call     Btst,  2
			not     
			bnt      code_0289
			pushi    1
			pushi    51
			call     Bset,  2
code_0289:
			lag      haloTimer
			bnt      code_029b
			-ag      haloTimer
			not     
			bnt      code_029b
			pushi    2
			pushi    0
			pushi    0
			calle    Print,  4
code_029b:
			pushi    1
			pushi    1
			call     Btst,  2
			bnt      code_0304
			lag      invisibleRingTimer
			bnt      code_0304
			-ag      invisibleRingTimer
			not     
			bnt      code_0304
			pushi    1
			pushi    1
			call     Bclr,  2
			lsg      curRoomNum
			ldi      1
			eq?     
			bnt      code_02ee
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			bnt      code_02e0
			pushi    #has
			pushi    1
			pushi    14
			lag      ego
			send     6
			bnt      code_02e0
			pushi    #has
			pushi    1
			pushi    1
			lag      ego
			send     6
code_02e0:
			not     
			bnt      code_02ee
			pushi    2
			pushi    0
			pushi    1
			calle    Print,  4
			jmp      code_02f5
code_02ee:
			pushi    2
			pushi    0
			pushi    2
			calle    Print,  4
code_02f5:
			pushi    #put
			pushi    1
			pushi    5
			lag      ego
			send     6
			pushi    0
			call     NormalEgo,  0
code_0304:
			lag      global108
			not     
			bnt      code_034c
			lag      global107
			bnt      code_0316
			ldi      0
			sat      temp0
			jmp      code_033a
code_0316:
			pushi    #controls
			pushi    0
			class    User
			send     4
			push    
			ldi      0
			eq?     
			bnt      code_0330
			ldi      1
			sat      temp0
			lag      waitCursor
			sag      oldCursor
			jmp      code_033a
code_0330:
			pushi    0
			callk    HaveMouse,  0
			sat      temp0
			lag      normalCursor
			sag      oldCursor
code_033a:
			lsg      theCursor
			lag      oldCursor
			ne?     
			bnt      code_034c
			pushi    #setCursor
			pushi    2
			lsg      oldCursor
			lst      temp0
			self     8
code_034c:
			pushi    #doit
			pushi    0
			super    Game,  4
			ret     
		)
	)
	
	(method (replay)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(SetMenu soundI
			p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(super replay:)
	)
	
	(method (startRoom roomNum)
		(asm
			pushi    37
			pushi    0
			pushi    985
			pushi    982
			pushi    972
			pushi    988
			pushi    980
			pushi    978
			pushi    977
			pushi    975
			pushi    974
			pushi    971
			pushi    970
			pushi    969
			pushi    973
			pushi    966
			pushi    965
			pushi    964
			pushi    962
			pushi    956
			pushi    976
			pushi    959
			pushi    955
			pushi    949
			pushi    991
			pushi    986
			pushi    983
			pushi    611
			pushi    600
			pushi    608
			pushi    779
			pushi    784
			pushi    782
			pushi    781
			pushi    780
			pushi    615
			pushi    898
			pushi    899
			calle    LoadMany,  74
			lag      debugOn
			bnt      code_03d5
			ldi      0
			sag      debugOn
			pushi    0
			callk    SetDebug,  0
code_03d5:
			pushi    1
			pushi    1
			callk    MemoryInfo,  2
			push    
			pushi    20
			pushi    1
			pushi    0
			callk    MemoryInfo,  2
			add     
			ugt?    
			bnt      code_0403
			lag      debugging
			bnt      code_0403
			pushi    5
			pushi    0
			pushi    3
			pushi    #button
			lofsa    {Debug}
			push    
			pushi    1
			calle    Print,  10
			bnt      code_0403
			pushi    0
			callk    SetDebug,  0
code_0403:
			pushi    #startRoom
			pushi    1
			lsp      roomNum
			super    Game,  6
			lsg      prevRoomNum
			ldi      0
			eq?     
			bnt      code_042e
			pushi    1
			pushi    40
			call     Btst,  2
			not     
			bnt      code_042e
			pushi    #draw
			pushi    0
			class    MenuBar
			send     4
			pushi    #enable
			pushi    0
			class    StatusLine
			send     4
code_042e:
			pushi    #picAngle
			pushi    1
			pushi    50
			lag      curRoom
			send     6
			pushi    1
			pushi    2
			call     Btst,  2
			bnt      code_045f
			pushi    79
			lag      curRoomNum
			ge?     
			bnt      code_044d
			pprev   
			ldi      49
			ge?     
code_044d:
			not     
			bnt      code_045f
			pushi    #setRegions
			pushi    1
			pushi    600
			lag      curRoom
			send     6
			jmp      code_04d7
code_045f:
			lag      roomWithDeadGoat
			bnt      code_0469
			pushi    0
			call     ShowDeadGoat,  0
code_0469:
			pushi    40
			lsg      curRoomNum
			pushi    3
			pushi    4
			pushi    5
			pushi    6
			pushi    7
			pushi    8
			pushi    9
			pushi    12
			pushi    14
			pushi    15
			pushi    16
			pushi    17
			pushi    18
			pushi    19
			pushi    20
			pushi    23
			pushi    24
			pushi    26
			pushi    30
			pushi    31
			pushi    32
			pushi    33
			pushi    34
			pushi    36
			pushi    37
			pushi    38
			pushi    42
			pushi    45
			pushi    47
			pushi    56
			pushi    57
			pushi    59
			pushi    60
			pushi    61
			pushi    62
			pushi    70
			pushi    71
			pushi    72
			pushi    82
			calle    OneOf,  80
			bnt      code_04d7
			lsg      howFast
			ldi      1
			ge?     
			bnt      code_04d7
			pushi    #setLocales
			pushi    1
			pushi    611
			lag      curRoom
			send     6
code_04d7:
			pushi    4
			lsg      curRoomNum
			pushi    24
			pushi    31
			pushi    38
			calle    OneOf,  8
			bnt      code_0518
			pushi    #has
			pushi    1
			pushi    20
			lag      ego
			send     6
			bnt      code_0505
			pushi    #setRegions
			pushi    1
			pushi    606
			lag      curRoom
			send     6
			jmp      code_0518
code_0505:
			lsg      roomWithBeanstalk
			lag      curRoomNum
			eq?     
			bnt      code_0518
			pushi    #setRegions
			pushi    1
			pushi    607
			lag      curRoom
			send     6
code_0518:
			pushi    10
			lsg      curRoomNum
			pushi    56
			pushi    57
			pushi    58
			pushi    59
			pushi    60
			pushi    61
			pushi    62
			pushi    72
			pushi    82
			calle    OneOf,  20
			bnt      code_0542
			pushi    #setRegions
			pushi    1
			pushi    610
			lag      curRoom
			send     6
code_0542:
			lag      haloTimer
			bnt      code_0552
			pushi    #setRegions
			pushi    1
			pushi    616
			lag      curRoom
			send     6
code_0552:
			pushi    #loop
			pushi    1
			pushi    0
			lofsa    gameSound
			send     6
			pushi    #has
			pushi    1
			pushi    14
			lag      ego
			send     6
			bnt      code_05ae
			pushi    #has
			pushi    1
			pushi    1
			lag      ego
			send     6
			bnt      code_05ae
			pushi    #has
			pushi    1
			pushi    16
			lag      ego
			send     6
			bnt      code_05ae
			pushi    1
			pushi    51
			call     Btst,  2
			not     
			bnt      code_05ae
			lsg      curRoomNum
			ldi      70
			lt?     
			bt       code_059c
			lsg      curRoomNum
			ldi      83
			eq?     
			bnt      code_05ae
code_059c:
			lsg      curRoomNum
			ldi      53
			ne?     
			bnt      code_05ae
			pushi    1
			pushi    98
			call     PlayBackgroundMusic,  2
			jmp      code_06ab
code_05ae:
			pushi    12
			lsg      curRoomNum
			pushi    50
			pushi    66
			pushi    67
			pushi    68
			pushi    69
			pushi    73
			pushi    74
			pushi    75
			pushi    76
			pushi    77
			pushi    78
			calle    OneOf,  24
			bnt      code_05db
			pushi    1
			pushi    31
			call     PlayBackgroundMusic,  2
			jmp      code_06ab
code_05db:
			pushi    2
			lsg      curRoomNum
			pushi    63
			calle    OneOf,  4
			bnt      code_05f3
			pushi    1
			pushi    73
			call     PlayBackgroundMusic,  2
			jmp      code_06ab
code_05f3:
			pushi    27
			lsg      curRoomNum
			pushi    3
			pushi    9
			pushi    10
			pushi    11
			pushi    12
			pushi    13
			pushi    15
			pushi    16
			pushi    19
			pushi    21
			pushi    22
			pushi    24
			pushi    27
			pushi    28
			pushi    29
			pushi    30
			pushi    31
			pushi    35
			pushi    36
			pushi    38
			pushi    40
			pushi    44
			pushi    45
			pushi    46
			pushi    48
			pushi    95
			calle    OneOf,  54
			bnt      code_063d
			pushi    1
			pushi    2
			call     PlayBackgroundMusic,  2
			jmp      code_06ab
code_063d:
			pushi    9
			lsg      curRoomNum
			pushi    1
			pushi    2
			pushi    25
			pushi    26
			pushi    39
			pushi    41
			pushi    42
			pushi    83
			calle    OneOf,  18
			bnt      code_0662
			pushi    1
			pushi    52
			call     PlayBackgroundMusic,  2
			jmp      code_06ab
code_0662:
			pushi    6
			lsg      curRoomNum
			pushi    7
			pushi    32
			pushi    33
			pushi    34
			pushi    47
			calle    OneOf,  12
			bnt      code_0683
			pushi    1
			pushi    12
			call     PlayBackgroundMusic,  2
			jmp      code_06ab
code_0683:
			pushi    11
			lsg      curRoomNum
			pushi    4
			pushi    5
			pushi    6
			pushi    8
			pushi    17
			pushi    18
			pushi    20
			pushi    23
			pushi    37
			pushi    43
			calle    OneOf,  22
			bnt      code_06ab
			pushi    1
			pushi    68
			call     PlayBackgroundMusic,  2
code_06ab:
			pushi    #dispose
			pushi    0
			self     4
			ret     
		)
	)
	
	(method (handleEvent event &tmp i [temp1 3] evtX evtY evtMod [str 50])
		(if
			(and
				(== (event type?) mouseDown)
				(& (event modifiers?) shiftDown)
			)
			(if (not (User canInput:))
				(event claimed: TRUE)
			else
				(cast eachElementDo: #handleEvent event)
				(addToPics eachElementDo: #handleEvent event)
				(features eachElementDo: #handleEvent event)
			)
		)
		(if (== (event type?) saidEvent)
			(addToPics eachElementDo: #handleEvent event)
		)
		(if (event claimed?) (return))
		(super handleEvent: event)
		(if
			(or
				(== (event type?) mouseDown)
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
			)
			(if modelessDialog
				(event claimed: FALSE)
				(modelessDialog dispose:)
			else
				(event claimed: FALSE)
			)
		)
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look,check[<up][/sky]')
						(cond 
							((OneOf curRoomNum 70 71 roomWithBeanstalk) (Print 0 4))
							(
								(OneOf curRoomNum
									49 50 51 52 53 54 55 65 66 67 68 69
									73 74 75 76 77 78 79 90
								)
								(Print 0 5)
							)
							(else
								(Print 0 6)
							)
						)
					)
					((Said 'look,check<in/mirror')
						(if (or (ego has: iMagicMirror) (== curRoomNum 51))
							(Print 0 7)
						else
							(Print 0 8)
						)
					)
					((Said '/goat>')
						(cond 
							((Said 'look,check')
								(if (cast contains: theGoat)
									(Print 0 9)
								else
									(Print 0 10)
								)
							)
							((Said 'talk,speak')
								(if
									(or
										(== roomWithDeadGoat curRoomNum)
										(cast contains: theGoat)
									)
									(Print 0 11)
								else
									(Print 0 10)
								)
							)
							((Said 'get,take')
								(if
									(or
										(== roomWithDeadGoat curRoomNum)
										(cast contains: theGoat)
									)
									(Print 0 12)
								else
									(Print 0 10)
								)
							)
						)
					)
					((Said 'drop/pebble/shot')
						(Print 0 13)
					)
					((Said 'drop>')
						(if
							(and
								(= i (inventory firstTrue: #saidMe))
								(i ownedBy: ego)
							)
							(Print 0 14)
						else
							(event claimed: FALSE)
						)
					)
					((Said '/fish>')
						(if (OneOf curRoomNum 4 5 6 8 17 18 20 23 37 43)
							(cond 
								((Said 'eat,consume') (Print 0 15))
								((Said 'kill') (Print 0 16))
							)
						else
							(Print 0 17)
							(event claimed: TRUE)
						)
					)
					((Said '/nut>')
						(cond 
							((Said 'get,take')
								(if (ego has: iWalnut)
									(Print 0 18) else
									(event claimed: FALSE)
								)
							)
							((not (ego has: iWalnut))
								(DontHave)
								(event claimed: TRUE)
							)
							((Said 'open,open')
								(cond 
									((curRoom script?)
										(CantDo)
									)
									((Btst fOpenedWalnut)
										(Print 0 19)
									)
									(else
										(Print 0 20)
										(SolvePuzzle fOpenedWalnut 3)
										(_Walnut_
											name: { Gold Walnut_}
											loop: 1
											description:
												{When you open the walnut, you discover the nut inside is pure gold!}
										)
									)
								)
							)
							((Said 'eat,consume')
								(if (Btst fOpenedWalnut)
									(Print 0 21)
								else
									(Print 0 22)
								)
							)
							((Said 'bite')
								(if (ego has: iWalnut)
									(Print 0 23)
								else
									(Print 0 24)
								)
							)
							((Said 'look,check')
								(_Walnut_ showSelf:)
								(event claimed: TRUE)
							)
						)
					)
					((Said '/ring>')
						(cond 
							((not (ego has: iMagicRing))
								(DontHave)
								(event claimed: TRUE)
							)
							((or (Said 'wear') (Said 'drop<on'))
								(cond 
									((Btst fWearingRing)
										(Print 0 25)
									)
									((or (Btst fClimbing) (OneOf (ego view?) 8 19))
										(Print 0 26)
									)
									(else
										(Print 0 27)
										(Bset fWearingRing)
									)
								)
							)
							((Said 'rub')
								(cond 
									((or (Btst fClimbing) (OneOf (ego view?) 8 19))
										(Print 0 26)
									)
									((Btst fInvisible)
										(Print 0 28)
									)
									((not invisibleRingTimer)
										(Print 0 29)
									)
									((== (ego view?)
										(if (Btst fLittleEgo) 23 else 16))
											(Print 0 30)
										)
									((> egoInWater 0)
										(Print 0 31)
									)
									((Btst fWearingRing)
										(Print 0 32)
										(cond 
											((and (Btst fGoatFollows) (OneOf curRoomNum 25 39 41))
												(event claimed: TRUE)
												(theGoat setMotion: 0)
											)
											((Btst fGoatFollows)
												(Print 0 33)
												(Bclr fGoatFollows)
												(theGoat setMotion: 0)
											)
										)
										(Bset fInvisible)
										(NormalEgo)
									)
									(else (Print 0 34))
								)
							)
							((or (Said 'remove') (Said 'take<off'))
								(cond 
									((== (ego view?)(if (Btst fLittleEgo) 23 else 16))
										(CantDo)
									)
									((not (Btst fWearingRing))
										(Print 0 35)
									)
									((== (ego view?) 8)
										(Print 0 36)
									)
									(else
										(Print 0 37)
										(if (and (cast contains: theGoat) (ego has: iCarrot))
											(theGoat setMotion: Follow ego 60)
											(Bset fGoatFollows)
										)
										(Bclr fWearingRing)
										(Bclr fInvisible)
										(if
											(and
												(!= (ego view?) 61)
												(!= (ego view?) 249)
												(!= (ego view?) 13)
												(!= (ego view?) 14)
												(!= (ego view?) 6)
											)
											(NormalEgo)
										)
									)
								)
							)
							((Said 'get,take')
								(if (ego has: iMagicRing)
									(Print 0 18)
								else
									(event claimed: FALSE)
								)
							)
							((Said 'look,check')
								(_Magic_Ring_ showSelf:)
							)
						)
					)
					((Said '/egg>')
						(if (ego has: iGoldEgg)
							(cond 
								((Said 'eat,consume')
									(Print 0 38)
								)
								((Said 'open,open')
									(Print 0 39)
								)
								((Said 'crack')
									(Print 0 40)
								)
								((Said 'look,check')
									(_Gold_Egg_ showSelf:)
									(event claimed: TRUE)
								)
							)
						else
							(DontHave)
							(event claimed: TRUE)
						)
					)
					((Said 'smell,sniff/stew')
						(cond 
							((or (not (ego has: iCeramicBowl)) (not (Btst fBowlHasStew)))
								(DontHave)
								(event claimed: TRUE)
							)
							((Btst fAteStew)
								(Print 0 41)
							)
							(else
								(Print 0 42)
							)
						)
					)
					((Said 'eat,consume>')
						(cond 
							(egoInWater
								(event claimed: TRUE)
								(switch (Random 0 2)
									(0 (Print 0 43))
									(1 (Print 0 44))
									(2 (Print 0 45))
								)
							)
							((Btst fInvisible)
								(Print 0 46)
								(event claimed: TRUE)
							)
							((curRoom script?)
								(CantDo)
								(event claimed: TRUE)
							)
							((Said '/stew')
								(cond 
									((or (not (ego has: iCeramicBowl)) (not (Btst fBowlHasStew)))
										(DontHave)
										(event claimed: TRUE)
									)
									((Btst fAteStew)
										(Print 0 41)
									)
									(else
										(Print 0 47)
										(_Ceramic_Bowl_
											loop: 0
											description: {This large ceramic bowl is now empty.}
										)
										(Bclr fBowlHasStew)
										(kq1 changeScore: -2)
									)
								)
							)
							((Said '/mushroom')
								(cond 
									((ego has: iMushroom)
										(curRoom setScript: (ScriptID 779 0))
									)
									((Btst fAteMushroom)
										(Print 0 48)
									)
									(else
										(DontHave)
										(event claimed: TRUE)
									)
								)
							)
							((Said '/carrot')
								(cond 
									((Btst fGoatFollows)
										(Print 0 49)
									)
									((not (ego has: iCarrot))
										(if (Btst fAteCarrot)
											(Print 0 50)
										else
											(DontHave)
										)
									)
									(else
										(Print 0 51)
										(Bset fAteCarrot)
										(curRoom setScript: (ScriptID 781 0))
										(Bclr fPickedCarrot)
										(SetItemOwner iCarrot 15)
										(if (not (Btst fTrollDead))
											(theGame changeScore: -2)
										)
									)
								)
							)
							((Said '/bean')
								(if (not (ego has: iBeans))
									(if (Btst fAteBeans)
										(Print 0 52)
									else
										(DontHave)
									)
								else
									(Print 0 53)
									(Bset fAteBeans)
									(curRoom setScript: (ScriptID 781 0))
									(SetItemOwner iBeans 0)
									(theGame changeScore: -4)
								)
							)
							((Said '/cheese')
								(if (ego has: iCheese)
									(Print 0 54)
									(curRoom setScript: (ScriptID 781 0))
									(ego put: iCheese)
									(theGame changeScore: -2)
								else
									(DontHave)
								)
							)
							((Said '/*')
								(Print 0 55)
								(event claimed: TRUE)
							)
							(else
								(Print 0 56)
								(event claimed: TRUE)
							)
						)
					)
					((and (not (OneOf curRoomNum 63 80 27 22)) (Said '/bird'))
						(Print 0 57)
					)
					((Said 'stand')
						(if (OneOf (ego view?) 0 2 4 7 36 37 38 39)
							(Print 0 58)
						else
							(Print 0 59)
						)
					)
					((Said 'swim')
						(Print 0 60)
					)
					((Said 'dive')
						(Print 0 61)
					)
					((Said 'climb,scale<in')
						(Print 0 62)
					)
					((Said 'climb,scale')
						(cond 
							((== (ego view?) 14)
								(Print 0 63)
							)
							((== curRoomNum 73)
								(Print 0 64)
							)
							(else
								(Print 0 65)
							)
						)
					)
					((Said 'bury/bean')
						(if (not (ego has: iBeans))
							(DontHave)
						else
							(Print 0 66)
						)
					)
					(
						(or
							(Said 'open,open/bag[<leather]')
							(Said 'look,check<in/bag[<leather]')
							(Said 'look,check/diamond')
							(Said 'look,check/content')
						)
						(cond 
							((curRoom script?)
								(CantDo)
							)
							((not (ego has: iPouch))
								(DontHave)
							)
							(else
								(SolvePuzzle fOpenedPouch 3)
								(Print 0 67)
							)
						)
					)
					((or (Said 'fill[/bowl]') (Said 'say<fill'))
						(cond 
							((curRoom script?)
								(CantDo)
							)
							((Btst fBowlHasStew)
								(Print 0 68)
							)
							((not (ego has: iCeramicBowl))
								(event claimed: FALSE)
								(if (Said '/bowl')
									(DontHave)
								else
									(Print 0 69)
									(event claimed: TRUE)
								)
							)
							(else
								(gameSound number: 69 loop: 1 init: play:)
								(Print 0 70)
								(_Ceramic_Bowl_
									loop: 1
									description:
										{This large ceramic bowl is now filled with a savory beef stew.}
									said: '/bowl,stew'
								)
								(SolvePuzzle fBowlHasStew 2)
							)
						)
					)
					((Said 'open,open/chest')
						(if (ego has: iChest)
							(if (Btst fOpenedChest)
								(Print 0 71)
							else
								(Print 0 72)
								(Bset fOpenedChest)
							)
						else
							(DontHave)
						)
					)
					((Said 'fiddle,play/fiddle,jig')
						(cond 
							((or (not (OneOf (ego view?) 0 2 7 4)) (curRoom script?))
								(CantDo)
							)
							((Btst fInvisible)
								(Print 0 73)
							)
							((not (ego has: iFiddle))
								(DontHave)
							)
							(else
								(curRoom setScript: (ScriptID 782 0))
							)
						)
					)
					((Said 'throw/dagger,dagger')
						(cond 
							((not (ego has: iDagger))
								(if (Btst fTookDagger)
									(Print 0 74)
								else
									(DontHave)
								)
							)
							((curRoom script?)
								(CantDo)
							)
							((Btst fInvisible)
								(Print 0 73)
							)
							(egoInWater
								(Print 0 75)
							)
							(else
								(curRoom setScript: (ScriptID 780 0))
							)
						)
					)
					((Said 'use,throw,shoot/shot')
						(cond 
							((not (ego has: iSlingshot))
								(DontHave)
							)
							((not (ego has: iPebbles))
								(Print 0 76)
							)
							((curRoom script?)
								(CantDo)
							)
							((Btst fInvisible)
								(Print 0 77)
							)
							((> egoInWater 1)
								(Print 0 78)
							)
							(else
								(curRoom setScript: (ScriptID 784 0))
							)
						)
					)
					((Said 'throw/boulder,pebble')
						(if (and (ego has: iPebbles) numPebbles)
							(PebbleCount)
							(if (== numPebbles 0)
								(Print 0 79)
							else
								(Print 0 80)
							)
						else
							(DontHave)
						)
					)
					((Said 'throw/water')
						(cond 
							((and (ego has: iWaterBucket) (Btst fWaterInBucket))
								(Print 0 81)
							)
							((== (ego view?) 14)
								(Print 0 82)
							)
							((== (ego view?) 13)
								(Print 0 83)
							)
							(egoInWater
								(Print 0 84)
							)
							(else
								(Print 0 85)
							)
						)
					)
					((or (Said 'get,take/drink') (Said 'drink[/water]'))
						(cond 
							((== (ego view?) 14)
								(Print 0 86)
							)
							((== (ego view?) 13)
								(Print 0 87)
							)
							(egoInWater
								(Print 0 88)
							)
							((and (ego has: iWaterBucket) (Btst fWaterInBucket))
								(Print 0 89)
								(BucketState 0)
							)
							(else
								(Print 0 90)
							)
						)
					)
					((Said 'read,look,check/bowl')
						(if (ego has: iCeramicBowl)
							(_Ceramic_Bowl_ showSelf:)
						else
							(DontHave)
						)
					)
					((Said 'read,look,check/note')
						(if (ego has: iNote)
							(_Note_ showSelf:)
						else
							(DontHave)
						)
					)
					(
						(and
							(Said 'look,check>')
							(= i (inventory firstTrue: #saidMe))
						)
						(if (i ownedBy: ego)
							(i showSelf:)
						else
							(DontHave)
						)
					)
					((Said 'get,take/gold,coin[<gold]')
						(cond 
							((ego has: iChest)
								(Print 0 91)
							)
							((and (ego has: iWalnut) (Btst fOpenedWalnut))
								(Print 0 92)
							)
							((ego has: iGoldEgg)
								(Print 0 93)
							)
							(else
								(Print 0 94)
							)
						)
					)
					((and (== roomWithDeadGoat curRoomNum) (Said 'get,take/dagger'))
						(Print 0 95)
					)
					((Said 'get,take>')
						(cond 
							((and (= i (inventory firstTrue: #saidMe)) (i ownedBy: ego))
								(Print 0 18)
							)
							((Said '/*')
								(Print 0 96)
							)
							(else
								(Print 0 97)
								(event claimed: TRUE)
							)
						)
					)
					((Said 'look,check/beanstalk')
						(Print 0 98)
					)
					((Said 'look,check/edge')
						(if (ego has: iDagger)
							(Print 0 99)
						else
							(Print 0 100)
						)
					)
					((Said 'look,check')
						(Print 0 101)
					)
					((Said 'why')
						(Print 0 102)
					)
					((Said 'because')
						(Print 0 103)
					)
					((Said 'bitch[<*]')
						(event claimed: TRUE)
					)
					((Said 'excuse/i')
						(Print 0 104)
					)
					((Said 'what')
						(Print 0 105)
					)
					((Said 'how')
						(Print 0 106)
					)
					((Said 'who')
						(Print 0 107)
					)
					((Said 'when')
						(Print 0 108)
					)
					((Said 'use')
						(Print 0 109)
					)
					((Said 'smell,sniff')
						(Print 0 110)
					)
					((Said 'throw,give')
						(Print 0 111)
					)
					((Said 'pull')
						(Print 0 112)
					)
					((Said 'talk,speak/*')
						(Print 0 113)
					)
					((Said 'talk,speak')
						(Print 0 114)
					)
				)
			)
			(mouseDown
				(if (and debugging (User controls?))
					(= evtX (event x?))
					(= evtY (event y?))
					(if
						(not
							(if (& (= evtMod (event modifiers?)) shiftDown)
							else
								(& evtMod keyDown)
							)
						)
						(event claimed: TRUE)
						((User alterEgo?) setMotion: MoveTo evtX evtY)
					)
				)
			)
		)
	)
	
	(method (wordFail word input &tmp [str 100])
		(if (and (== curRoomNum 40) (Btst fGnomeAttention))
			(curRoom notify: word)
		else
			(Printf 0 115 word)
			(LogIt
				(Format @str 0 116 word input)
				{Unknown word}
			)
		)
	)
	
	(method (syntaxFail input)
		(Print 0 117)
		(LogIt input {Couldn't parse})
	)
	
	(method (pragmaFail input)
		(Print 0 118)
		(LogIt input {No response})
	)
)

(instance statusCode of Code
	(properties)
	
	(method (doit strg)
		(Format strg 0 119 score possibleScore
			0 120 {King's Quest I}
			0 120
		)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance goat of Actor
	(properties
		view 165
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(and (not (event claimed?)) (== (event type?) saidEvent))
			(if (Said 'look,check/goat')
				(self doVerb: verbLook)
			)
			(if (Said 'get,take/goat')
				(event claimed: FALSE)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook
				(cond 
					(roomWithDeadGoat
						(Print 0 121)
					)
					((== view 166)
						(Print 0 122)
					)
					((Btst fGoatFollows)
						(Print 0 123)
					)
					((not (Btst fGoatFriend))
						(if (& (theGoat onControl:) (| cLRED cLMAGENTA))
							(Print 0 124)
						else
							(Print 0 125)
						)
					)
					(else
						(Print 0 9)
					)
				)
			)
		)
	)
)

(instance menace of Actor
	(properties)
	
	(method (handleEvent event)
		(cond 
			((event claimed?)
				(return)
			)
			((& signal saidEvent)
				(event claimed: FALSE)
			)
			((super handleEvent: event)
				(return)
			)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== view 130)
				(= description {ogre})
				(super doVerb: theVerb &rest)
			)
			((== view 125)
				(= description {wolf})
				(super doVerb: theVerb &rest)
			)
			((OneOf view 120 121 123 124 126)
				(= description {troll})
				(if (Btst fTrollBlocksBridge)
					(Print 0 126)
				else
					(Print 0 127)
				)
			)
			((OneOf view 115 116 117 118 119)
				(= description {witch})
				(super doVerb: theVerb &rest)
			)
			((OneOf view 135 136)
				(= description {dwarf})
				(super doVerb: theVerb &rest)
			)
			(else
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance _Dagger_ of InvItem
	(properties
		said '/dagger'
		description {This is a fine silver dagger, with a very sharp edge!}
		view 501
		name " Dagger_"
	)
)

(instance _Chest_ of InvItem
	(properties
		said '/chest'
		description {This Magic chest, one of the three lost treasures of Daventry, is always filled with gold coins.}
		owner 58
		view 505
		name " Chest_"
	)
)

(instance _Carrot_ of InvItem
	(properties
		said '/carrot'
		description {This looks like an ordinary carrot.}
		view 517
		name " Carrot_"
	)
)

(instance _Key_ of InvItem
	(properties
		said '/key'
		description {This is a simple gold key.}
		view 508
		name " Key_"
	)
)

(instance _Note_ of InvItem
	(properties
		said '/note,message'
		description {There is a message written on the note: "Sometimes it is wise to think backwards."}
		view 513
		name " Note_"
	)
	
	(method (showSelf)
		(super showSelf:)
		(SolvePuzzle fReadNote 2)
	)
)

(instance _Magic_Ring_ of InvItem
	(properties
		said '/ring'
		description {Your hand tingles slightly as you look at this jewelled ring.}
		view 516
		name " Magic Ring_"
	)
)

(instance _Four-leaf_Clover_ of InvItem
	(properties
		said '/clover'
		description {You are holding a large four-leaf clover.}
		view 515
		name " Four-leaf Clover_"
	)
)

(instance _Ceramic_Bowl_ of InvItem
	(properties
		said '/bowl'
		description {Inscribed on the inside of this empty ceramic bowl is the word "Fill."}
		view 511
		name " Ceramic Bowl_"
	)
	
	(method (showSelf)
		(super showSelf:)
		(if (not (Btst fBowlHasStew)) (SolvePuzzle fReadBowl 1))
	)
)

(instance _Empty_Water_Bucket_ of InvItem
	(properties
		said '/bucket'
		description {You are holding an empty wooden bucket.}
		view 519
		name " Empty Water Bucket_"
	)
)

(instance _Pebbles_ of InvItem
	(properties
		said '/pebble'
		description {You have a pocketful of smooth, rounded pebbles.}
		view 510
		name " Pebbles_"
	)
	
	(method (showSelf &tmp [str 80])
		(if (== numPebbles 1)
			(Print (Format @str 0 128 numPebbles) #icon 510 0 0)
		else
			(Print
				(Format @str 0 129 numPebbles)
				#icon 510 (- numPebbles 1) 0
			)
		)
	)
)

(instance _Leather_Slingshot_ of InvItem
	(properties
		said '/shot,shot'
		description {This is a small but sturdy slingshot.}
		owner 62
		view 520
		name " Leather Slingshot_"
	)
)

(instance _Pouch_ of InvItem
	(properties
		said '/bag'
		description {You are holding a plain leather pouch.}
		view 500
		name " Pouch_"
	)
)

(instance _Sceptre_ of InvItem
	(properties
		said '/scepter'
		description {This jewelled sceptre belongs to the King of the Leprechauns.}
		view 504
		name " Sceptre_"
	)
)

(instance _Cheese_ of InvItem
	(properties
		said '/cheese'
		description {This is an extremely fragrant piece of Swiss cheese.}
		view 514
		name " Cheese_"
	)
)

(instance _Magic_Mirror_ of InvItem
	(properties
		said '/mirror'
		description {This is the magic mirror, one of the three treasures of Daventry.}
		view 503
		name " Magic Mirror_"
	)
)

(instance _Gold_Egg_ of InvItem
	(properties
		said '/egg'
		description {You are holding a lovely golden egg.}
		owner 63
		view 518
		name " Gold Egg_"
	)
)

(instance _Magic_Shield_ of InvItem
	(properties
		said '/shield'
		description {This is the magic shield, one of the three treasures of Daventry.}
		view 522
		name " Magic Shield_"
	)
)

(instance _Fiddle_ of InvItem
	(properties
		said '/fiddle'
		description {This is the woodcutter's old fiddle.}
		owner 79
		view 506
		name " Fiddle_"
	)
)

(instance _Walnut_ of InvItem
	(properties
		said '/nut'
		description {You are holding an ordinary walnut.}
		view 512
		name " Walnut_"
	)
)

(instance _Mushroom_ of InvItem
	(properties
		said '/mushroom'
		description {This is a small, unusual-looking mushroom.}
		view 507
		name " Mushroom_"
	)
)

(instance _Beans_ of InvItem
	(properties
		said '/bean'
		description {You are holding a handful of small beans.}
		view 509
		name " Beans_"
	)
)

(instance logFile of File
	(properties)
)

(instance kqWindow of SysWindow
	(properties
		top 20
	)
)
