;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use LLEgo)
(use PMouse)
(use GControl)
(use IconBar)
(use PrintD)
(use Feature)
(use StopWalk)
(use DCIcon)
(use Window)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use System)

(public
	LSL1 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	Btst 6
	Bset 7
	Bclr 8
	deathIcon 24
	icon0 25
	WhichLanguage 26
	proc0_27 27
	TimePrint 28
	proc0_29 29
	CheckLoans 30
	proc0_31 31
	DontMove 32
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
	narrator
	msgType
	messager
	prints
	walkHandler
	textSpeed =  2
		global95
		global96
		global97
		global98
	lastSysGlobal
	theMusic
	gameCode =  1234
	theMusic2
	global103
	global104 =  1
	global105
	numColors
	numVoices
	global108
	global109
	global110
	gameFlags
	global112
	global113
	global114
	global115
	global116
	global117
	global118
	saveCursorX
	saveCursorY
	global121
	global122
	global123
	global124
	global125
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
	global137
	global138
	global139
	global140
	global141
	myHighlightColor
	global143
	global144
	global145
	global146
	global147
	myBackColor
	outstandingLoans
	global150
	global151 =  160
	global152 =  160
	global153
	oldSysTime
	global155
	global156
	global157
	dollars =  100
	debugging
	global160
	global161
	global162
	pokerJackpot
	global164 =  10
	global165 =  807
	global166 =  1
	theMusic3
	cIcon
	global169 =  4
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
	global211
	global212
	global213
	global214
	global215
	global216
	global217
	global218
	global219
	global220
	global221
	global222
	global223
	global224
	global225
	global226
	global227
	global228
	global229
	global230
	global231
	global232
	global233
	global234
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
	global250
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
	resumeFont
	global294
	global295
	global296
	global297
	global298
	global299
	global300
)
(procedure (NormalEgo theLoop theView theStoppedView &tmp stopView)
	(= stopView 0)
	(ego view: 800)
	(if (> argc 0)
		(ego loop: theLoop)
		(if (> argc 1)
			(ego view: theView)
			(if (> argc 2)
				(= stopView theStoppedView)
			)
		)
	)
	(if (not stopView)
		(= stopView 809)
	)
	(ego
		normal: TRUE
		moveHead: TRUE
		setLoop: -1
		setPri: -1
		setMotion: 0
		setCycle: StopWalk stopView
		setStep: 3 2
		illegalBits: cWHITE
		ignoreActors: FALSE
		userSpeed:
	)
)

(procedure (HandsOff &tmp theIconBarCurIcon)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(= theIconBarCurIcon (theIconBar curIcon?))
	(theIconBar disable:
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_ZIPPER
		ICON_TASTE
		ICON_ITEM
		ICON_INVENTORY
	)
	(theIconBar curIcon: theIconBarCurIcon)
	(if (not (HaveMouse))
		(= saveCursorX ((User curEvent?) x?))
		(= saveCursorY ((User curEvent?) y?))
		(theGame setCursor: waitCursor)
		(SetCursor 310 180)
	else
		(SetCursor waitCursor 0 0)
	)
	(if pMouse
		(pMouse stop:)
	)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(if (!= curRoomNum 100)
		(theIconBar enable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_ZIPPER
			ICON_TASTE
			ICON_ITEM
			ICON_INVENTORY
		)
	)
	(theIconBar
		height: -100
		activateHeight: -100
		curIcon: (theIconBar at: ICON_DO)
		disable:
			ICON_TALK
			ICON_ITEM
			ICON_INVENTORY
			ICON_ZIPPER
			ICON_WALK
			ICON_HELP
			ICON_TASTE
			ICON_CONTROL
	)
	(if (not (HaveMouse))
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(SetCursor saveCursorX saveCursorY)
	else
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	(ego userSpeed:)
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
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

(procedure (WhichLanguage german spanish french italian english)
	(switch (theGame printLang?)
		(GERMAN german)
		(SPANISH spanish)
		(FRENCH french)
		(ITALIAN italian)
		(else  english)
	)
)

(procedure (proc0_27)
	(WhichLanguage 1026 1040 1051 1051 852)
)

(procedure (TimePrint theString moreStuff &tmp [str 300] numSeconds oldCur)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(if (not (HaveMouse))
		(= oldCur (theGame setCursor: 5))
	)
	(if (u< theString 1000)
		(GetFarText theString moreStuff @str)
		(= numSeconds
			(Max 3 (/ (* textSpeed (StrLen @str)) 120))
		)
		(Print @str #time numSeconds &rest)
	else
		(= numSeconds
			(Max 3 (/ (* textSpeed (StrLen theString)) 120))
		)
		(Print theString #time numSeconds moreStuff &rest)
	)
	(if (not (HaveMouse))
		(theGame setCursor: oldCur)
	)
	(return TRUE)
)

(procedure (proc0_29 &tmp [temp0 200])
	(asm
		lsg      outstandingLoans
		ldi      1
		gt?     
		bnt      code_1236
		lsg      dollars
		ldi      50
		sub     
		push    
		lsg      outstandingLoans
		ldi      100
		mul     
		gt?     
		bnt      code_1236
		lsg      curRoomNum
		ldi      100
		eq?     
		bnt      code_1236
code_11b6:
		pushi    8
		pushi    0
		pushi    24
		pushi    78
		lofsa    {Yes}
		push    
		pushi    1
		pushi    78
		lofsa    {No}
		push    
		pushi    2
		calle    Print,  16
		push    
		dup     
		ldi      1
		eq?     
		bnt      code_1201
		pushi    1
		lofsa    {Before payback}
		push    
		call     CheckLoans,  2
		lsg      dollars
		lsg      outstandingLoans
		ldi      100
		mul     
		sub     
		sag      dollars
		ldi      0
		sag      outstandingLoans
		pushi    1
		lofsa    {After payback}
		push    
		call     CheckLoans,  2
		pushi    2
		pushi    0
		pushi    25
		calle    Print,  4
		jmp      code_129f
		jmp      code_1230
code_1201:
		dup     
		ldi      2
		eq?     
		bnt      code_1230
		pushi    2
		pushi    0
		pushi    26
		calle    Print,  4
		pushi    #play
		pushi    0
		lofsa    loseSound
		send     4
		lsg      dollars
		ldi      100
		sub     
		sag      dollars
		-ag      outstandingLoans
		pushi    2
		pushi    0
		pushi    27
		calle    Print,  4
		pushi    0
		call     CheckLoans,  0
		jmp      code_129f
code_1230:
		toss    
		jmp      code_11b6
		jmp      code_129f
code_1236:
		lag      outstandingLoans
		bnt      code_129f
		lsg      dollars
		ldi      200
		ge?     
		bnt      code_129f
		pushi    #play
		pushi    0
		lofsa    loseSound
		send     4
		lsg      dollars
		ldi      100
		sub     
		sag      dollars
		-ag      outstandingLoans
		pushi    2
		pushi    0
		pushi    28
		calle    Print,  4
		pushi    6
		lea      @temp0
		push    
		pushi    0
		pushi    29
		lsg      outstandingLoans
		dup     
		ldi      100
		mul     
		push    
		pushi    10
		lag      outstandingLoans
		sub     
		push    
		callk    Format,  12
		pushi    9
		lea      @temp0
		push    
		pushi    27
		pushi    1
		pushi    77
		lofsa    {Loan Shark's Office}
		push    
		pushi    32
		class    SysWindow
		push    
		pushi    78
		lofsa    { Done_}
		push    
		calle    Print,  18
		pushi    3
		pushi    0
		pushi    30
		lsg      dollars
		calle    Printf,  6
code_129f:
		ret     
	)
)

(procedure (CheckLoans param1 &tmp [str 200])
	(if (not argc)
		(Format @str 0 32
			outstandingLoans
			(* outstandingLoans 100)
			(- 10 outstandingLoans)
		)
	else
		(Format @str 0 33
			param1
			outstandingLoans
			(* outstandingLoans 100)
			(- 10 outstandingLoans)
		)
	)
	(Print @str
		#mode teJustCenter
		#title {Loan Shark's Office}
		#window SysWindow
		#button { Done_}
	)
)

(procedure (proc0_31)
	(theMusic
		number: 310
		setVol: (if (!= curRoomNum 100) 40 else 20)
		loop: -1
		play:
	)
)

(procedure (DontMove newCursor)
	(User canControl: FALSE canInput: TRUE)
	(if argc (SetCursor newCursor 0 0))
)

(instance egoObj of LLEgo
	(properties
		name {ego}
		sightAngle 180
		view 800
	)
)

(instance pointsSound of Sound
	(properties
		flags mNOPAUSE
		number 821
	)
)

(instance babbleIcon of DCIcon
	
	(method (init)
		((= cycler (Forward new:)) init: self)
	)
)

(instance ll1KDHandler of EventHandler)

(instance ll1MDHandler of EventHandler)

(instance ll1DirHandler of EventHandler)

(instance LSL1 of Game
	
	(method (init &tmp [temp0 7])
		(= systemWindow ll1Win)
		(= version {x.yyy})
		(super init:)
		(= doVerbCode ll1DoVerbCode)
		(= ftrInitializer ll1FtrInit)
		((= keyDownHandler ll1KDHandler) add:)
		((= mouseDownHandler ll1MDHandler) add:)
		((= directionHandler ll1DirHandler) add:)
		(= pMouse PseudoMouse)
		(= ego egoObj)
		(User alterEgo: ego)
		((ScriptID 816 0) init:)
		((= theMusic longSong)
			init:
			owner: self
			flags: mNOPAUSE
		)
		((= theMusic2 longSong2)
			init:
			owner: self
			flags: mNOPAUSE
		)
		((= theMusic3 longSong3)
			init:
			owner: self
			flags: mNOPAUSE
		)
		((= theIconBar IconBar)
			add:
				(icon0 cursor: walkingIcon yourself:)
				(icon1 cursor: lookingIcon yourself:)
				(icon2 cursor: doingIcon yourself:)
				(icon3 cursor: talkingIcon yourself:)
				(icon6 cursor: zipperIcon yourself:)
				(icon7 cursor: tasterSmellerIcon yourself:)
				(icon4 cursor: invItemIcon yourself:)
				(icon5 cursor: invIcon yourself:)
				(icon8 cursor: controlIcon yourself:)
				(icon9 cursor: abouterIcon yourself:)
			eachElementDo: #init
			eachElementDo: #highlightColor myHighlightColor
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
		)
		(icon5 message: (if (HaveMouse) SHIFTTAB else TAB))
		(HandsOff)
		(gcWin color: 0 back: myBackColor)
		((= gameControls budgetControls)
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
				(iconSave
					theObj: theGame
					selector: #save
					yourself:
				)
				(iconRestore
					theObj: theGame
					selector: #restore
					yourself:
				)
				(iconRestart
					theObj: theGame
					selector: #restart
					yourself:
				)
				(iconQuit
					theObj: theGame
					selector: #quitGame
					yourself:
				)
				(iconAbout
					theObj: (ScriptID 811 0)
					selector: #doit
					yourself:
				)
				iconHelp
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #lowlightColor myBackColor
			helpIconItem: iconHelp
			curIcon: iconSave
		)
		(theIconBar
			height: -100
			activateHeight: -100
			enable:
			curIcon: icon0
			disable: 0 3 6 7 4 8 5
		)
		(Bclr fRanOutOfMoney)
		(DontMove waitCursor)
		(self newRoom: 100)
	)
	
	(method (doit &tmp thisTime)
		(if (!= oldSysTime (= thisTime (GetTime SYSTIME1)))
			(= oldSysTime thisTime)
			(if (and (< dollars 1) (not (Btst fGambling)))
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(if (and (not (Btst fRanOutOfMoney)) (== curRoomNum 100))
					(Bset fRanOutOfMoney)
					(Wait 5)
					(Print 0 1)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (startRoom roomNum)
		(if pMouse
			(pMouse stop:)
		)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		((ScriptID DISPOSE) doit: roomNum)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(`#1
							(requestHelp init:)
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
						)
						(`#4
							(CheckLoans)
							(event claimed: TRUE)
						)
						(`#6
							(CheckLoans)
							(event claimed: TRUE)
						)
						(`@x
							(= quit TRUE)
							(event claimed: TRUE)
						)
						(`^q
							(theGame quitGame:)
							(event claimed: TRUE)
						)
						(`^c
							(theGame setCursor: ARROW_CURSOR TRUE)
							(icon8 select:)
						)
					)
				)
			)
		)
	)
	
	(method (setCursor form &tmp oldCur)
		(= oldCur theCursor)
		(= theCursor form)
		(return (if (IsObject form) (form init:) oldCur else 0))
	)
	
	(method (quitGame)
		(if (!= curRoomNum 100)
			(babbleIcon
				view: 853
				loop: 0
				cycleSpeed: (* (+ howFast 1) 4)
			)
			(theGame setCursor: ARROW_CURSOR TRUE)
			(Animate (cast elements?) FALSE)
			(super
				quitGame:
					(Print 0 0
						#button {Stop Whining} 1
						#button {Oh, All Right} 0
						#icon babbleIcon
						#title {Don't give up. It's still early!}
					)
			)
		)
	)
	
	(method (pragmaFail &tmp [temp0 2])
		(if (User canInput:))
	)
)

(instance speedORama of Code

	(method (doit)
		(ego moveSpeed?)
	)
)

(instance deathIcon of DCIcon
	
	(method (init)
		(if cIcon
			((= cycler (EndLoop new:)) init: self)
		else
			((= cycler (Forward new:)) init: self)
		)
	)
)

(instance theDefaultFeature of Feature)

(instance icon0 of IconItem
	(properties
		view 850
		loop 0
		cel 0
		cursor 100
		message 7
		signal (| HIDEBAR RELVERIFY)
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: global145)
		(super init:)
	)
)

(instance icon1 of IconItem
	(properties
		view 850
		loop 1
		cel 0
		cursor 101
		message 1
		signal (| HIDEBAR RELVERIFY)
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: global147)
		(super init:)
	)
)

(instance icon2 of IconItem
	(properties
		view 850
		loop 2
		cel 0
		cursor 102
		message 2
		signal (| HIDEBAR RELVERIFY)
		maskView 850
		maskLoop 14
	)
	
	(method (init)
		(self lowlightColor: global138)
		(super init:)
	)
)

(instance icon3 of IconItem
	(properties
		view 850
		loop 3
		cel 0
		cursor 103
		message 4
		signal (| HIDEBAR RELVERIFY)
		maskView 850
		maskLoop 14
		maskCel 3
	)
	
	(method (init)
		(self lowlightColor: global131)
		(super init:)
	)
)

(instance icon4 of IconItem
	(properties
		view 850
		loop 4
		cel 0
		cursor 999
		message 5
		signal (| HIDEBAR RELVERIFY)
		maskView 850
		maskLoop 14
		maskCel 4
	)
	
	(method (init)
		(self lowlightColor: global138)
		(super init:)
	)
)

(instance icon5 of IconItem
	(properties
		view 850
		loop 5
		cel 0
		cursor 999
		message 0
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 850
		maskLoop 14
		maskCel 2
	)
	
	(method (init)
		(self lowlightColor: global134)
		(super init:)
	)
	
	(method (select)
		(if (super select: &rest)
			(Inventory showSelf: ego)
		)
	)
)

(instance icon6 of IconItem
	(properties
		view 850
		loop 10
		cel 0
		cursor 104
		message 8
		signal (| HIDEBAR RELVERIFY)
		maskView 850
		maskLoop 14
	)
	
	(method (init)
		(self lowlightColor: global135)
		(super init:)
	)
)

(instance icon7 of IconItem
	(properties
		view 850
		loop 11
		cel 0
		cursor 105
		message 6
		signal (| HIDEBAR RELVERIFY)
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: global147)
		(super init:)
	)
)

(instance icon8 of IconItem
	(properties
		view 850
		loop 7
		cel 0
		cursor 999
		message 8
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 850
		maskLoop 14
		maskCel 1
	)
	
	(method (init)
		(self lowlightColor: global145)
		(super init:)
	)
	
	(method (select)
		(if (super select:)
			(theIconBar hide:)
			(gameControls show:)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 850
		loop 9
		cel 0
		cursor 150
		message 6
		signal (| RELVERIFY IMMEDIATE)
		maskView 850
		maskLoop 14
	)
	
	(method (init)
		(self lowlightColor: global131)
		(super init:)
	)
)

(instance ll1DoVerbCode of Code

	(method (doit theVerb obj &tmp theDesc temp1)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(if (obj facingMe: ego)
			(switch theVerb
				(verbWalk 0)
				(verbTalk
					(Printf 0 2 theDesc)
				)
				(verbDo
					(Printf 0 3)
				)
				(verbZipper
					(Print 0 4)
				)
				(verbTaste
					(Print 0 5)
				)
			)
		)
	)
)

(instance longSong of Sound)

(instance longSong2 of Sound)

(instance longSong3 of Sound)

(instance ll1FtrInit of Code
	
	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 40)
		)
		(if (== (obj actions?) ftrDefault)
			(obj actions: 0)
		)
	)
)

(instance ll1Win of Window)

(instance gcWin of Window
	
	(method (open &tmp [temp0 14] [scoreBuf 25] [scoreRect 4])
		(self
			top: (/ (- 200 (+ (CelHigh (proc0_27) 1 1) 6)) 2)
			left:
				(/
					(-
						320
						(+
							147
							(CelWide (proc0_27) 0 1)
							4
							(WhichLanguage 0 12 0 0 0)
						)
					)
					2
				)
			bottom:
				(+
					(CelHigh (proc0_27) 1 1)
					6
					(/ (- 200 (+ (CelHigh (proc0_27) 1 1) 6)) 2)
				)
			right:
				(+
					147
					(CelWide (proc0_27) 0 1)
					4
					(WhichLanguage 0 12 0 0 0)
					(/
						(-
							320
							(+
								147
								(CelWide (proc0_27) 0 1)
								4
								(WhichLanguage 0 12 0 0 0)
							)
						)
						2
					)
				)
			priority: 15
		)
		(super open:)
		(DrawCel
			(proc0_27)
			0
			5
			(+
				(/
					(-
						(-
							(+
								147
								(CelWide (proc0_27) 0 1)
								4
								(WhichLanguage 0 12 0 0 0)
							)
							(+ 4 (CelWide (proc0_27) 1 1))
						)
						(CelWide (proc0_27) 0 5)
					)
					2
				)
				4
				(CelWide (proc0_27) 1 1)
			)
			6
			15
		)
		(DrawCel (proc0_27) 1 1 4 3 15)
		(DrawCel (proc0_27) 1 0 94 38 15)
		(DrawCel (proc0_27) 1 0 135 38 15)
		(DrawCel
			(proc0_27)
			0
			4
			63
			(- 37 (+ (CelHigh (proc0_27) 0 4) 3))
			15
		)
		(DrawCel
			(proc0_27)
			0
			3
			(- 107 (WhichLanguage 6 9 6 6 6))
			(- 37 (+ (CelHigh (proc0_27) 0 4) 3))
			15
		)
		(DrawCel
			(proc0_27)
			0
			2
			(- 147 (WhichLanguage 12 8 1 1 1))
			(- 37 (+ (CelHigh (proc0_27) 0 4) 3))
			15
		)
		(Format @scoreBuf 0 6 score possibleScore)
		(TextSize @scoreRect @scoreBuf 999 0)
		(Display
			@scoreBuf
			p_font userFont
			p_color global141
			p_at
			(+
				10
				(CelWide (proc0_27) 1 1)
				(/
					(-
						(-
							(-
								(+
									147
									(CelWide (proc0_27) 0 1)
									4
									(WhichLanguage 0 12 0 0 0)
								)
								(+ 10 (CelWide (proc0_27) 1 1) 6)
							)
							(WhichLanguage 0 20 0 0 0)
						)
						[scoreRect 3]
					)
					2
				)
			)
			(+ 46 (CelHigh (proc0_27) 0 1) 3)
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
		sliderView 852
		topValue 3
	)
	
	(method (show)
		(= sliderView (proc0_27))
		(= view (proc0_27))
		(super show: &rest)
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
		sliderView 852
		topValue 15
	)
	
	(method (show)
		(= sliderView (proc0_27))
		(= view (proc0_27))
		(super show: &rest)
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
		sliderView 852
		bottomValue 15
	)
	
	(method (show)
		(= sliderView (proc0_27))
		(= view (proc0_27))
		(super show: &rest)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (proc0_27))
		(super show: &rest)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (proc0_27))
		(super show: &rest)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (proc0_27))
		(super show: &rest)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (proc0_27))
		(super show: &rest)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (proc0_27))
		(super show: &rest)
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
		message 6
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (proc0_27))
		(super show: &rest)
	)
)

(instance iconOk of IconItem
	(properties
		view 852
		loop 8
		cel 0
		nsLeft 8
		nsTop 106
		cursor 999
		message 9
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
	)
	
	(method (show)
		(= view (proc0_27))
		(super show: &rest)
	)
)

(instance requestHelp of Object
	
	(method (init param1 param2 saveColor saveBack)
		(= saveColor (systemWindow color?))
		(= saveBack (systemWindow back?))
		(theGame masterVolume: 0)
		(super init:)
		(self
			doit: param1 param2 saveColor saveBack
		)
	)
	
	(method (doit param1 param2)
		(repeat
			(switch
				(PrintD {Select...}
					#new
					#button {Game Help} 1
					#new
					#button {Credits} 2
					#new
					#button {Sierra} 3
					#new
					#button {Quit About} 4
				)
				(1
					(Print 0 7
						#title {Game Help}
						#mode teJustCenter
						#button {More_}
						#window cluckWindow
					)
					(Print 0 8
						#title {Game Help (Con't)}
						#mode teJustCenter
						#button {More_}
						#window cluckWindow
					)
					(Print 0 9
						#title {Game Help (Con't)}
						#mode teJustCenter
						#button {More_}
						#window cluckWindow
					)
				)
				(2
					(displayCredits doit:)
					(break)
				)
				(3
					(Print 0 10
						#title {Other great products...}
						#mode teJustCenter
						#button {More_}
						#window cluckWindow
					)
				)
				(4
					(break)
				)
				
			)
		)
		(systemWindow color: 0 back: 42)
		(theGame masterVolume: 13)

	)
)

(instance displayCredits of Code
	
	(method (doit)
		(theGame masterVolume: 0)
		(systemWindow color: 50 back: 42)
		(Print 0 12
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 13
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 14
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 15
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 16
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 17
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 18
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 19
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 20
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 21
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 22
			#title {Credits}
			#mode teJustCenter
			#button { More_}
			#window cluckWindow
		)
		(Print 0 23
			#title {Credits}
			#mode teJustCenter
			#button { Done_}
			#window cluckWindow
		)
		(theGame masterVolume: 12)
	)
)

(instance walkingIcon of Cursor
	(properties
		view 850
		cel 2
	)
)

(instance lookingIcon of Cursor
	(properties
		view 850
		loop 1
		cel 2
	)
)

(instance doingIcon of Cursor
	(properties
		view 850
		loop 2
		cel 2
	)
)

(instance talkingIcon of Cursor
	(properties
		view 850
		loop 3
		cel 2
	)
)

(instance invItemIcon of Cursor
	(properties
		view 850
		loop 4
	)
)

(instance invIcon of Cursor
	(properties
		view 850
		loop 5
	)
)

(instance zipperIcon of Cursor
	(properties
		view 850
		loop 10
		cel 2
	)
)

(instance tasterSmellerIcon of Cursor
	(properties
		view 850
		loop 11
		cel 2
	)
)

(instance controlIcon of Cursor
	(properties
		view 850
		loop 7
	)
)

(instance abouterIcon of Cursor
	(properties
		view 850
		loop 9
	)
)

(instance budgetControls of GameControls

	(method (hide)
		(if window (window dispose:))
		(if (& state IB_ACTIVE)
			(sounds pause: 0)
			(&= state (~ IB_ACTIVE))
		)
	)
)

(instance loseSound of Sound
	(properties
		flags mFIXEDPRI
		number 726
	)
)

(instance cluckWindow of SysWindow
	(properties
		back 42
	)
)
