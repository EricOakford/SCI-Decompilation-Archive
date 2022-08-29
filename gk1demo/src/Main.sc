;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use GKIconItem)
(use Procs)
(use GKInv)
(use GKEgo)
(use Intrface)
(use Print)
(use Dialog)
(use Messager)
(use PMouse)
(use BordWind)
(use IconBar)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Sound)
(use Game)
(use Invent)
(use User)
(use System)

(public
	GK 0
)

(local
	ego
	theGame
	curRoom
	unused_1
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
	textCode
	cuees
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	version
	unused_3
	curSaveDir
	unused_4
	perspective
	features
	unused_5
	useSortedFeatures
	unused_6
	overlays =  -1
	doMotionCue
	systemWindow
	unused_7
	unused_8
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
	unused_9
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
	unused_10
	fastCast
	inputFont
	tickOffset
	howFast
	gameTime
	narrator
	msgType =  TEXT_MSG
	messager
	prints
	walkHandler
	textSpeed =  2
	altPolyList
		global96
		global97
		global98
	lastSysGlobal
	egoLooper
	gameCode =  1234
	theMusic1
	theMusic2
	global104
	numColors
	numVoices
	global107 =  100
	global108
	global109
	debugging
	global111
	global112
	global113
	global114
	egoMoveSpeed =  6
	global116
	global117
	global118
	global119
	global120
	global121
	global122
	currentDay
	global124
	global125
	global126
	gameFlags
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
	global142
	global143
	global144
	global145
	global146
	global147
	global148
	global149
	global150
	myLowlightColor
	global152
	global153
	global154
	global155
	global156
	myHighlightColor
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
	interrogateSubject
	global179
	currentTalker
	isDemo
	questionsAsked
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
	gGk1Exits
	theSound1
	theSound2
	global209
	gNewList
	theSound3
)
(instance takeCursor of Cursor
	(properties
		view 958
	)
)

(instance openCursor of Cursor
	(properties
		view 958
		loop 2
	)
)

(instance walkCursor of Cursor
	(properties
		view 958
		loop 4
	)
)

(instance lookCursor of Cursor
	(properties
		view 958
		loop 5
	)
)

(instance operCursor of Cursor
	(properties
		view 958
		loop 1
	)
)

(instance moveCursor of Cursor
	(properties
		view 958
		loop 3
	)
)

(instance askCursor of Cursor
	(properties
		view 958
		loop 6
	)
)

(instance talkCursor of Cursor
	(properties
		view 958
		loop 7
	)
)

(instance helpCursor of Cursor
	(properties
		view 958
		loop 8
	)
)

(instance gkKDHandler of EventHandler)

(instance gkMDHandler of EventHandler)

(instance gkDirHandler of EventHandler)

(instance gkWalkHandler of EventHandler)

(class GK of Game
	(properties
		keepBar 0
		isHandsOn TRUE
		oldCurIcon 0
		currentSpeed 6
		barUp 0
	)
	
	(method (init &tmp [temp0 9] versionFile)
		(ScriptID SIGHT)
		DText
		DButton
		Print
		BorderWindow
		IconBar
		Inventory
		Polygon
		PolyPath
		(ScriptID GKEGO)
		(ScriptID GKNARRATOR)
		(ScriptID TIMER)
		(Load RES_VIEW 959)
		(= gameCode 1234)
		(super init:)
		((ScriptID COLORINIT 0) doit:)
		((ScriptID GKINIT 0) doit:)
		(gkMusic1
			type: soundMUSIC
			owner: self
			musicVolume: 80
			flags: mNOPAUSE
			init:
		)
		(gkMusic2
			type: soundMUSIC
			owner: self
			musicVolume: 80
			flags: mNOPAUSE
			init:
		)
		(gkSound1
			type: soundSFX
			flags: mNOPAUSE
			owner: self
			init:
		)
		(gkSound2
			type: soundSFX
			flags: mNOPAUSE
			owner: self
			init:
		)
		(gkSound3
			type: soundSFX
			flags: mNOPAUSE
			owner: self
			init:
		)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileClose versionFile)
		(= ego GKEgo)
		(= theCursor takeCursor)
		(= normalCursor ARROW_CURSOR)
		(= egoLooper (ScriptID GKEGO 1))
		(= pMouse PseudoMouse)
		(= doVerbCode gkDoVerbCode)
		(= approachCode gkApproachCode)
		(= messager gkMessager)
		(= narrator (ScriptID GKNARRATOR 0))
		((= keyDownHandler gkKDHandler) add:)
		((= mouseDownHandler gkMDHandler) add:)
		((= walkHandler gkWalkHandler) add:)
		((= directionHandler gkDirHandler) add:)
		(= theSound1 gkSound1)
		(= theSound2 gkSound2)
		(= theSound3 gkSound3)
		(= theMusic1 gkMusic1)
		(= theMusic2 gkMusic2)
		(= isHandsOn FALSE)
		(SetCursor 0 0 155 319)
		((= theIconBar (ScriptID GKICONBAR 0))
			add:
				icon2
				icon0
				icon1
				icon3
				icon4
				icon5
				icon6
				icon7
				icon8
				icon9
				icon10
				icon11
				icon12
				icon13
			eachElementDo: #init
			eachElementDo: #lowlightColor 2
			eachElementDo: #highlightColor myHighlightColor
			curIcon: icon3
			useIconItem: icon12
			walkIconItem: icon2
			helpIconItem: icon5
			activateHeight: 20
			state: (| OPENIFONME NOCLICKHELP)
			disable: icon12
		)
		(theIconBar disable:)
		(GKInventory init:)
		(self newRoom: 17)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(self setCursor: waitCursor TRUE init:)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (replay &tmp theStyle thePort)
		(if lastEvent
			(lastEvent dispose:)
		)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(cast eachElementDo: #perform RestoreUpdate)
		(theGame setCursor: waitCursor TRUE)
		(= theStyle
			(if (not (OneOf (curRoom style?) -1 SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN))
				(curRoom style?)
			else
				PLAIN
			)
		)
		(DrawPic (curRoom curPic?) theStyle TRUE)
		(if (!= overlays -1)
			(DrawPic overlays PLAIN FALSE)
		)
		(addToPics doit:)
		(if (IsObject gNewList)
			(gNewList eachElementDo: #show)
		)
		(cond 
			((and (not (user canControl:)) (not (user canInput:)))
				(theGame setCursor: waitCursor)
			)
			((and theIconBar (theIconBar curIcon?))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
			(else
				(theGame setCursor: normalCursor)
			)
		)
		(if
			(and
				(not (OneOf curRoomNum 50))
				(not (& (theIconBar state?) DISABLED))
			)
			(self keepIconBar:)
		else
			(= thePort (GetPort))
			(SetPort -1)
			(Graph GFillRect MINTOP MINLEFT 10 (- MAXRIGHT 1) 1 myLowlightColor -1 -1)
			(Graph GShowBits MINTOP MINLEFT 10 (- MAXRIGHT 1) 1)
			(SetPort thePort)
		)
		(DoSound RestoreSound)
		(sounds pause: FALSE)
		(= tickOffset (- gameTime (GetTime)))
		(if (OneOf curRoomNum 200 205)
			((ScriptID curRoomNum 1) eachElementDo: #show)
		)
		(SetCursor 0 0 155 319)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (newRoom n &tmp i)
		(narrator dispose:)
		(pMouse stop:)
		(if (!= n 17)
			(for ((= i 100)) (>= i 0) ((-= i 5))
				(Palette PALIntensity 0 255 i)
				(Wait 1)
			)
		)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp temp0 i [temp2 2])
		((ScriptID DISPOSE 0) doit:)
		(for ((= i 0)) (< i (timers size?)) ((++ i))
			(timers delete: (= temp0 (timers at: 0)))
			(timers add: temp0)
		)
		(cond 
			((OneOf roomNum 50)
				(theIconBar erase: disable:)
			)
			((!= roomNum 17)
				(theGame handsOn:)
			)
		)
		(if (OneOf roomNum 410 420 430 440)
			(ScriptID rgPark)
			((ScriptID roomNum) setRegions: rgPark)
		)
		(if (OneOf roomNum 300 310 320 302)
			(ScriptID 301)
			((ScriptID roomNum) setRegions: 301)
		)
		(super startRoom: roomNum)
		(if
			(and
				(ego cycler?)
				(not (ego looper?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego setLoop: (ScriptID GKEGO 1))
		)
		(if (OneOf prevRoomNum 50)
			(theIconBar enable:)
		)
		(if
			(and
				(not (OneOf roomNum 50))
				(not (& (theIconBar state?) DISABLED))
			)
			(self keepIconBar:)
		)
	)
	
	(method (restart &tmp oldCur)
		(= oldCur (self setCursor: ARROW_CURSOR TRUE))
		(if
			(Print
				font: smallFont
				addText: NULL NULL C_ASKRESTART 1 30 0 0
				addButton: 1 NULL NULL C_ASKRESTART 2 0 30 0
				addButton: 0 NULL NULL C_ASKRESTART 3 0 50 0
				addIcon: 997 0 0 0 0
				init:
			)
			(super restart:)
		else
			(self setCursor: oldCur TRUE)
		)
	)
	
	(method (restore &tmp oldCur)
		(= oldCur (theGame setCursor: ARROW_CURSOR TRUE))
		(SetCursor -2)
		(super restore:)
		(SetCursor 0 0 155 319)
		(theGame setCursor: oldCur)
		(Print font: smallFont)
	)
	
	(method (save &tmp oldCur)
		(= oldCur (theGame setCursor: ARROW_CURSOR TRUE))
		(SetCursor -2)
		(super save:)
		(SetCursor 0 0 155 319)
		(theGame setCursor: oldCur)
		(Print font: smallFont)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(if (and debugging (== (event type?) keyDown))
			(switch (event message?)
				(TAB
					(if (not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
						(if fastCast
							(return fastCast)
						)
						(GKInventory showSelf: 1)
						(event claimed: 1)
					)
				)
				(SHIFTTAB
					(if (not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
						(if fastCast
							(return fastCast)
						)
						(GKInventory showSelf: TRUE)
						(event claimed: TRUE)
					)
				)
				(`^q
					(theGame quitGame:)
					(event claimed: TRUE)
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
					(if (not (& ((theIconBar at: ICON_CONTROLS) signal?) DISABLED))
						(if fastCast
							(return fastCast)
						)
						(theGame save:)
						(event claimed: TRUE)
					)
				)
				(`#7
					(if (not (& ((theIconBar at: ICON_CONTROLS) signal?) DISABLED))
						(if fastCast
							(return fastCast)
						)
						(theGame restore:)
						(event claimed: TRUE)
					)
				)
				(`+
					(if (user controls?)
						(= egoMoveSpeed (Max 0 (-- egoMoveSpeed)))
						(ego setSpeed: egoMoveSpeed)
					)
				)
				(`-
					(if (user controls?)
						(++ egoMoveSpeed)
						(ego setSpeed: egoMoveSpeed)
					)
				)
				(`=
					(if (user controls?)
						(= egoMoveSpeed 6)
						(ego setSpeed: 6)
					)
				)
				(else 
					((ScriptID DEBUG) handleEvent: event)
					((ScriptID DEBUG) dispose:)
					(DisposeScript DEBUG)
				)
			)
		)
		(cond 
			((event claimed?) (return TRUE))
			((and (& (event type?) userEvent) (User canControl:))
				(self pragmaFail: (event message?))
				(event claimed: TRUE)
			)
		)
		(return (event claimed?))
	)
	
	(method (setCursor form showIt theX theY &tmp oldCur)
		(= oldCur theCursor)
		(if (not (Btst fUsingMagnifyingGlass))
			(if argc
				(if (IsObject form)
					((= theCursor form) init:)
				else
					(SetCursor (= theCursor form) 0 0)
				)
			)
			(if (and (> argc 1) (not showIt))
				(SetCursor INVIS_CURSOR 0 0)
			)
			(if (> argc 2)
				(if (< theX 0) (= theX 0))
				(if (< theY 0) (= theY 0))
				(SetCursor theX theY)
			)
		)
		(return oldCur)
	)
	
	(method (quitGame &tmp oldCur)
		(= oldCur (self setCursor: ARROW_CURSOR TRUE))
		(if
			(Print
				font: smallFont
				addText: NULL NULL C_ASKQUIT 1 30 10 0
				addButton: 1 NULL NULL C_ASKQUIT 2 0 30 0
				addButton: 0 NULL NULL C_ASKQUIT 3 0 50 0
				addIcon: 997 0 0 0 0
				init:
			)
			(super quitGame:)
		else
			(self setCursor: oldCur TRUE)
		)
	)
	
	(method (pragmaFail theVerb)
		(cond 
			((and msgType (curRoom noun?) (user canInput:))
				(cond 
					((Message MsgGet curRoomNum (curRoom noun?) theVerb NULL 1)
						(messager say: (curRoom noun?) theVerb NULL 0 0 curRoomNum)
					)
					((OneOf theVerb V_OPERATE V_OPEN V_TAKE V_ASK V_TALK V_MOVE V_LOOK V_WALK)
						(messager say: NULL theVerb NULL 0 0 0)
					)
					((Message MsgGet curRoomNum (curRoom noun?) NULL NULL 1)
						(messager say: (curRoom noun?) NULL NULL 0 0 curRoomNum))
				)
			)
			((user canInput:)
				(messager say: NULL theVerb NULL 0 0 0)
			)
		)
	)
	
	(method (handsOff)
		(if isHandsOn
			(= isHandsOn FALSE)
			(if (not argc)
				(ego setMotion: 0)
			)
			(if (not oldCurIcon)
				(= oldCurIcon (theIconBar curIcon?))
			)
			(= global116 0)
			(theIconBar disable:
					ICON_TAKE
					ICON_OPEN
					ICON_WALK
					ICON_LOOK
					ICON_INVENTORY
					ICON_OPERATE
					ICON_MOVE
					ICON_ASK
					ICON_TALK
					ICON_RECORDER
					ICON_USEITEM
			)
			(User canControl: FALSE canInput: FALSE)
			(theGame setCursor: HAND_CURSOR TRUE)
			(if pMouse
				(pMouse stop:)
			)
		)
	)
	
	(method (handsOn &tmp temp0)
		(if (not isHandsOn)
			(= isHandsOn TRUE)
			(User canControl: TRUE canInput: TRUE)
			(if (IsObject oldCurIcon)
				(theIconBar curIcon: oldCurIcon)
			)
			(= oldCurIcon 0)
			(theIconBar enable:
				ICON_TAKE
				ICON_OPEN
				ICON_WALK
				ICON_LOOK
				ICON_INVENTORY
				ICON_OPERATE
				ICON_MOVE
				ICON_ASK
				ICON_TALK
				ICON_RECORDER
				ICON_USEITEM
			)
			(if (not (theIconBar curInvIcon?))
				(theIconBar disable: icon12)
			)
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
		)
	)
	
	(method (keepIconBar)
		(if keepBar
			(theIconBar draw:)
			(= barUp TRUE)
		else
			(= barUp FALSE)
			(theIconBar erase:)
		)
	)
	
	(method (showAbout)
		(self setScript: (ScriptID GKABOUT 2))
	)
)

(instance icon0 of GKIconItem
	(properties
		view 960
		loop 0
		cel 0
		nsLeft 52
		nsTop 3
		nsRight 72
		nsBottom 17
		message V_TAKE
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskCel 2
		noun N_TAKE
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor takeCursor)
		(= topIcon (= bottomIcon icon6))
		(= leftIcon icon5)
		(= rightIcon icon1)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon1 of GKIconItem
	(properties
		view 960
		loop 1
		cel 0
		nsLeft 72
		nsTop 3
		nsRight 94
		nsBottom 17
		message V_OPEN
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskLoop 1
		maskCel 2
		noun N_OPEN
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor openCursor)
		(= topIcon (= bottomIcon icon7))
		(= leftIcon icon0)
		(= rightIcon icon2)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon2 of GKIconItem
	(properties
		view 960
		loop 4
		cel 0
		nsLeft 97
		nsTop 3
		nsRight 117
		nsBottom 16
		type (| userEvent walkEvent)
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskLoop 4
		maskCel 2
		noun N_WALK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor walkCursor)
		(= topIcon (= bottomIcon icon8))
		(= leftIcon icon1)
		(= rightIcon icon3)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon3 of GKIconItem
	(properties
		view 960
		loop 5
		cel 0
		nsLeft 117
		nsTop 3
		nsRight 139
		nsBottom 16
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskLoop 5
		maskCel 2
		noun N_LOOK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor lookCursor)
		(= topIcon (= bottomIcon icon9))
		(= leftIcon icon2)
		(= rightIcon icon4)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon4 of GKIconItem
	(properties
		view 960
		loop 8
		cel 0
		nsLeft 142
		nsTop 3
		nsRight 163
		nsBottom 16
		cursor ARROW_CURSOR
		type NULL
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 960
		maskLoop 8
		maskCel 2
		noun N_INVENTORY
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon (= bottomIcon icon10))
		(= leftIcon icon3)
		(= rightIcon icon12)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(GKInventory showSelf: TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon5 of GKIconItem
	(properties
		view 960
		loop 10
		cel 0
		nsLeft 253
		nsTop 0
		nsRight 276
		nsBottom 16
		type helpEvent
		message 68
		signal (| RELVERIFY IMMEDIATE)
		maskView 960
		maskLoop 10
		maskCel 2
		noun N_HELP
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor helpCursor)
		(= topIcon (= bottomIcon icon11))
		(= leftIcon icon12)
		(= rightIcon icon0)
		(super init:)
	)
)

(instance icon6 of GKIconItem
	(properties
		view 960
		loop 2
		cel 0
		nsLeft 52
		nsTop 17
		nsRight 73
		nsBottom 29
		message V_OPERATE
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskLoop 2
		maskCel 2
		noun N_OPERATE
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor operCursor)
		(= topIcon (= bottomIcon icon0))
		(= leftIcon icon11)
		(= rightIcon icon7)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon7 of GKIconItem
	(properties
		view 960
		loop 3
		cel 0
		nsLeft 73
		nsTop 17
		nsRight 94
		nsBottom 29
		message V_MOVE
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskLoop 3
		maskCel 2
		noun N_MOVE
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor moveCursor)
		(= topIcon (= bottomIcon icon1))
		(= leftIcon icon6)
		(= rightIcon icon8)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon8 of GKIconItem
	(properties
		view 960
		loop 6
		cel 0
		nsLeft 97
		nsTop 16
		nsRight 118
		nsBottom 29
		message V_ASK
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskLoop 6
		maskCel 2
		noun N_ASK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor askCursor)
		(= topIcon (= bottomIcon icon2))
		(= leftIcon icon7)
		(= rightIcon icon9)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon9 of GKIconItem
	(properties
		view 960
		loop 7
		cel 0
		nsLeft 118
		nsTop 16
		nsRight 139
		nsBottom 29
		message V_TALK
		signal (| HIDEBAR RELVERIFY)
		maskView 960
		maskLoop 7
		maskCel 2
		noun N_TALK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor talkCursor)
		(= topIcon (= bottomIcon icon3))
		(= leftIcon icon8)
		(= rightIcon icon10)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon10 of GKIconItem
	(properties
		view 960
		loop 9
		cel 0
		nsLeft 142
		nsTop 16
		nsRight 163
		nsBottom 29
		cursor ARROW_CURSOR
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 960
		maskLoop 9
		maskCel 2
		noun N_RECORDER
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon (= bottomIcon icon4))
		(= leftIcon icon9)
		(= rightIcon icon12)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(if (not isDemo)
					(if (FileIO fileExists {recorder.dat})
						((ScriptID TAPE_PLAYER 0) init: show: dispose:)
					else
						(Prints {No Conversations have been Recorded.})
					)
				else
					(messager say: N_RECORDER_DEMO NULL C_NO_RECORDER_IN_DEMO 0 0 0)
				)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon11 of GKIconItem
	(properties
		view 960
		loop 11
		cel 0
		nsLeft 253
		nsTop 16
		nsRight 274
		nsBottom 29
		cursor ARROW_CURSOR
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 960
		maskLoop 11
		maskCel 2
		noun N_CONTROLS
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon (= bottomIcon icon5))
		(= leftIcon icon12)
		(= rightIcon icon6)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				((ScriptID GK_CONTROLS 0) init: show: dispose:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon12 of GKIconItem
	(properties
		view 959
		loop 2
		cel 0
		nsLeft 172
		nsTop 0
		nsRight 217
		nsBottom 32
		cursor ARROW_CURSOR
		message NULL
		signal (| HIDEBAR RELVERIFY)
		maskView 959
		maskLoop 2
		maskCel 2
		noun N_USEITEM
		helpVerb V_HELP
	)
	
	(method (init)
		(= topIcon (= bottomIcon self))
		(= leftIcon icon10)
		(= rightIcon icon11)
		(super init:)
	)
	
	(method (select &tmp temp0)
		(return
			(if (and (theIconBar curInvIcon?) (super select: &rest))
				(theIconBar hide:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight tOrF &tmp t l b r sColor)
		(if
			(or
				(not (& signal $0020))
				(== highlightColor -1)
				(not (theIconBar curInvIcon?))
			)
			(return)
		)
		(= sColor
			(if (and argc tOrF) highlightColor else lowlightColor)
		)
		(= t (+ nsTop 2))
		(= l (+ nsLeft 2))
		(= b (- nsBottom 3))
		(= r (- nsRight 4))
		(Graph GDrawLine t l t r sColor -1 -1)
		(Graph GDrawLine t r b r sColor -1 -1)
		(Graph GDrawLine b r b l sColor -1 -1)
		(Graph GDrawLine b l t l sColor -1 -1)
		(Graph GShowBits (- nsTop 2) (- nsLeft 2) nsBottom (+ nsRight 3) VMAP)
	)
	
	(method (mask)
		(if (theIconBar curInvIcon?)
			(super mask:)
		)
	)
)

(instance icon13 of IconItem
	(properties
		view 959
		loop 3
		cel 0
		nsLeft 223
		nsTop 0
		nsRight 247
		nsBottom 32
		signal DISABLED
		noun 12
		helpVerb V_HELP
	)
	
	(method (mask)
	)
)

(instance gkDoVerbCode of Code

	(method (doit theVerb theObj)
		(if
			(and
				msgType
				(theObj noun?)
				(not (OneOf theVerb V_OPERATE V_OPEN V_TAKE V_ASK V_TALK V_MOVE V_LOOK V_WALK))
				(Message MsgGet curRoomNum (theObj noun?) NULL NULL 1)
			)
			(messager say: (theObj noun?) NULL NULL 0 0 curRoomNum)
		else
			(theGame pragmaFail: theVerb)
		)
	)
)

(instance gkApproachCode of Code
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK $0001)
			(V_TALK $0002)
			(V_WALK $0004)
			(V_MOVE $0008)
			(V_OPEN $0010)
			(V_TAKE $0020)
			(V_OPERATE $0040)
			(else  $8000)
		)
	)
)

(instance gkMessager of Messager
	
	(method (findTalker who &tmp theTalker)
		(= theTalker narrator)
		(= currentTalker who)
		(if (== curRoomNum 50)
			(= theTalker
				(switch who
					(GABRIEL (ScriptID tlkEgo))
					(GRACE (ScriptID tlkGrace))
					(SARGE (ScriptID tlkSarge))
					(else  narrator)
				)
			)
		)
		(if theTalker
			(return)
		else
			(super findTalker: who)
		)
	)
)

(class GKSound of Sound
	(properties
		type 0
		musicVolume 127
		soundVolume 127
	)
	
	(method (play)
		(super
			play: (if (== type soundMUSIC) musicVolume else soundVolume) &rest
		)
	)
)

(instance gkMusic1 of GKSound)

(instance gkMusic2 of GKSound)

(instance gkSound1 of GKSound)

(instance gkSound2 of GKSound)

(instance gkSound3 of GKSound)

(instance RestoreUpdate of Code
	(properties
		name "RU"
	)
	
	(method (doit obj &tmp sigBits)
		(if (obj underBits?)
			(|= sigBits stopUpdOn)
			(&= sigBits (~ notUpd))
			(obj underBits: 0 signal: sigBits)
		)
	)
)

(class RestoreCel of Object
	(properties
		view 0
		loop 0
		cel 0
		nsL 0
		nsT 0
		priority 0
	)
	
	(method (init theView)
		(= view [theView 0])
		(= loop [theView 1])
		(= cel [theView 2])
		(= nsL [theView 3])
		(= nsT [theView 4])
		(= priority [theView 5])
		(if (not (IsObject gNewList))
			(= gNewList (List new:))
		)
		(gNewList add: self)
		(return self)
	)
	
	(method (show)
		(DrawCel view loop cel nsL nsT priority)
	)
)
