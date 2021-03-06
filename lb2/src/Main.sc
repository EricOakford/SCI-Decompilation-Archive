;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use LBIconItem)
(use LBRoom)
(use LBEgo)
(use Intrface)
(use Print)
(use Messager)
(use Talker)
(use Sync)
(use PMouse)
(use IconBar)
(use Osc)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Timer)
(use Grooper)
(use Window)
(use Sound)
(use Game)
(use Invent)
(use System)

(public
	LB2 0
	IsObjectOnControl 1
	Btst 2
	Bset 3
	Bclr 4
	Face 5
	DftDoVerb 6
	DisableIcons 7
	InFirstPerson 8
	lb2Win 9
	TriggerEvent 10
	WhichLanguage 11
	proc0_12 12
	StartAct2 13
	StartAct3 14
	StartAct4 15
	StartAct5 16
	StartAct6 17
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
	theStopGroop
	gameCode =  1234
	theMusic
	theMusic2
	global104
	numColors
	numVoices
	transferRoom =  100
	oldCanControl
	oldCanInput
	debugging
	global111
	usPhone
	intPhone
	hintPhone
	global115
	disabledIcons
	global117
	global118
	global119
	global120
	theCurIcon
	gTheNewDButtonValue
	currentAct
	triggeredEvents
	global125
	global126
	global127
	global128
	global129
	exitHandler
	global131 =  1
	global132 =  1
	global133 =  1
	global134 =  1
	global135 =  1
	global136
	global137
	global138
	global139
	global140
	global141
	global142
	global143
	global144
	deathReason
	global146
	global147
	global148
	global149
	numSnakeOil =  4
	global151
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
	myInsideColor
	global169
	global170
	global171
	myTopBordColor
	myBotBordColor
	global174
	global175
	myBackColor
	myLowlightColor
	global178
	global179
	global180
	global181
	global182
	global183
	global184
	global185
	gameFlags
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
	global293
	global294
	global295
	global296
	global297
	global298
	global299
	global300
	global301
	global302
	global303
	global304
	global305
	global306
	global307
	global308
	global309
	global310
	global311
	global312
	global313
	global314
	global315
	global316
	global317
	global318
	global319
	global320
	global321
	global322
	global323
	global324
	global325
	global326
	global327
	global328
	global329
	global330
	global331
	global332
	global333
	global334
	global335
	global336
	global337
	global338
	global339
	global340
	global341
	global342
	global343
	global344
	global345
	global346
	global347
	global348
	global349
	global350
	global351
	global352
	global353
	global354
	global355
	global356
	global357
	global358
	global359
	global360
	global361
	global362
	global363
	global364
	global365
	global366
	global367
	global368
	global369 =  6
	global370
	global371
	global372
	global373
	global374
	global375
	global376
	global377
	global378
	global379
	global380
	global381
	global382
	global383
	global384
	global385
	global386
	global387
	global388
	global389
	global390
	global391
	global392
	global393
	global394
	global395
	global396
	global397
	global398
	global399
)
(procedure (IsObjectOnControl obj theControl)
	(return
		(if (& (obj onControl: origin) theControl)
			(return TRUE)
		else
			FALSE
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

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj)
	(= obj 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3)
			(= obj both)
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4)
			(= obj whoToCue)
		)
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject obj) obj else 0)
	)
)

(procedure (DftDoVerb theNoun theVerb)
	(cond 
		((OneOf theVerb V_DO V_WALK V_WATERGLASS)
			(messager say: NULL theVerb NULL 0 0 0)
		)
		((OneOf theVerb V_ASK V_LOOK V_MAGNIFIER)
			(messager say: NULL theVerb NULL (Random 1 2) 0 0)
		)
		((== theVerb V_TALK)
			(messager say: NULL theVerb NULL (Random 1 4) 0 0)
		)
		(else
			(messager say: NULL V_PRAGFAIL NULL 0 0 0)
		)
	)
)

(procedure (DisableIcons &tmp i)
	(user canControl: oldCanControl canInput: oldCanInput)
	(for ((= i 0)) (< i 8) ((++ i))
		(if (& disabledIcons (>> $8000 i))
			(theIconBar disable: i)
		)
	)
)

(procedure (InFirstPerson tOrF)
	(if tOrF
		(theIconBar
			delete: icon0
			addToFront:
				(icon10
					init:
					highlightColor: myHighlightColor
					lowlightColor: myLowlightColor
					yourself:
				)
		)
		(if (== (theIconBar curIcon?) icon0)
			(theIconBar curIcon: icon10 walkIconItem: 0)
			(theGame setCursor: (icon10 cursor?))
		)
	else
		(theIconBar
			delete: icon10
			addToFront: (icon0 init: yourself:)
		)
		(if (== (theIconBar curIcon?) icon10)
			(theIconBar curIcon: icon0 walkIconItem: icon0)
			(theGame setCursor: (icon0 cursor?))
		)
	)
)

(procedure (TriggerEvent event param2)
	(&= event $00ff)
	(return
		(if (and (> argc 1) param2)
			(== (- event 1) (& triggeredEvents (- event 1)))
		else
			(& triggeredEvents event)
		)
	)
)

(procedure (WhichLanguage french spanish german italian english)
	(switch (theGame printLang?)
		(FRENCH french)
		(SPANISH spanish)
		(GERMAN german)
		(ITALIAN italian)
		(else english)
	)
)

(procedure (proc0_12)
	(WhichLanguage 1026 1040 1051 1050 995)
)

(procedure (StartAct2)
	(Bset 7)
	(Bset 8)
	(Bset 9)
	(Bset 24)
	(Bset 26)
	(Bset 27)
	(Bset 28)
	(Bset 29)
	(Bset 34)
	(Bset 43)
	((ScriptID 21 0) doit: 791)
	((ScriptID 21 0) doit: 263)
	((ScriptID 21 0) doit: 264)
	((ScriptID 21 0) doit: 265)
	((ScriptID 21 0) doit: 266)
	((ScriptID 21 0) doit: 267)
	((ScriptID 21 0) doit: 268)
	((ScriptID 21 0) doit: 269)
	((ScriptID 21 0) doit: 270)
	((ScriptID 21 0) doit: 271)
	((ScriptID 21 0) doit: 272)
	((ScriptID 21 1) doit: 518)
	((ScriptID 21 0) doit: 520)
	(ego wearingGown: TRUE)
	(ego get: -1 22)
	(ego get: -1 6)
)

(procedure (StartAct3)
	(StartAct2)
	(Bset 1)
	(Bset 25)
	(Bset 23)
	((ScriptID 21 0) doit: 797)
	(ego get: -1 28)
	(ego get: -1 21)
)

(procedure (StartAct4)
	(StartAct3)
	(= global129 13)
	(Bset 2)
	(Bset 3)
	(Bset 4)
	(Bset 22)
	(Bset 31)
	(Bset 33)
	(Bset 35)
	(Bset 36)
	(Bset 37)
	(Bset 40)
	(Bset 42)
	(Bset 49)
	((ScriptID 21 0) doit: 790)
	((ScriptID 21 0) doit: 789)
	((ScriptID 21 0) doit: 787)
	((ScriptID 21 0) doit: 783)
	((ScriptID 21 0) doit: 788)
	((ScriptID 21 0) doit: 798)
	((ScriptID 21 0) doit: 802)
	((ScriptID 21 0) doit: 779)
	((ScriptID 21 0) doit: 777)
	((ScriptID 21 0) doit: 776)
	((ScriptID 21 0) doit: 778)
	((ScriptID 21 0) doit: 1025)
	((ScriptID 21 0) doit: 1030)
	(ego get: -1 20 18 14 19 29 33 10 8 7 9 11)
)

(procedure (StartAct5 &tmp i)
	(StartAct4)
	(Bset 72)
	(Bset 5)
	(Bset 6)
	(Bset 62)
	((ScriptID 21 0) doit: 794)
	((ScriptID 21 0) doit: 785)
	((ScriptID 21 0) doit: 786)
	((ScriptID 21 0) doit: 799)
	((ScriptID 21 0) doit: 796)
	((ScriptID 21 0) doit: 795)
	((ScriptID 21 0) doit: 781)
	((ScriptID 21 0) doit: 800)
	((ScriptID 21 0) doit: 782)
	((ScriptID 21 0) doit: 780)
	(ego put: 6 0 1 3 4 5 8 9 18 23 32)
	((ScriptID 21 1) doit: 775)
	((ScriptID 21 1) doit: 769)
	((ScriptID 21 1) doit: 770)
	((ScriptID 21 1) doit: 772)
	((ScriptID 21 1) doit: 773)
	((ScriptID 21 1) doit: 774)
	((ScriptID 21 1) doit: 777)
	((ScriptID 21 1) doit: 778)
	((ScriptID 21 1) doit: 787)
	((ScriptID 21 1) doit: 792)
	((ScriptID 21 1) doit: 801)
	(for ((= i 1)) (< i 27) ((++ i))
		((ScriptID 21 0) doit: (+ i 1088))
	)
	(ego get: -1 25 16 17 30 27 26 12 31 13)
)

(procedure (StartAct6)
	(StartAct5)
	(Bset 10)
	((ScriptID 21 0) doit: 784)
	((ScriptID 21 0) doit: 803)
	(ego get: -1 34 15)
)

(instance gameMusic1 of Sound)

(instance gameMusic2 of Sound)

(class WrapMusic of List
	(properties
		wrapSound 0
		currentSound 0
		loopIt 0
		vol 127
		paused 0
	)
	
	(method (init theLoopIt)
		(Sounds eachElementDo: #check)
		(if (not wrapSound)
			(= wrapSound theMusic)
		)
		(= loopIt theLoopIt)
		(= currentSound 0)
		(self add: &rest cue:)
	)
	
	(method (dispose tOrF)
		(wrapSound client: 0)
		(if (and argc tOrF)
			(super dispose:)
		else
			(self release:)
		)
	)
	
	(method (cue &tmp theLoop soundNum temp2)
		(cond 
			((OneOf (wrapSound prevSignal?) -1 0)
				(= theLoop 1)
				(cond 
					((and (== loopIt -1) (== currentSound (- size 1)))
						(= theLoop -1)
					)
					((== currentSound size)
						(switch loopIt
							(1 (= currentSound 0))
							(else 
								(self release: dispose:)
								(return)
							)
						)
					)
				)
				(if (> (= soundNum (self at: currentSound)) 1000)
					(-= soundNum 1000)
					(= temp2 1)
				else
					(= temp2 0)
				)
				(wrapSound
					number: soundNum
					setLoop: theLoop
					flags: (if temp2 mNOPAUSE else mNOPAUSE)
					play: vol self
				)
				(++ currentSound)
			)
			(paused
				(wrapSound pause:)
			)
			(else
				(= vol (wrapSound vol?))
			)
		)
	)
	
	(method (pause tOrF)
		(if (IsObject wrapSound)
			(if (and argc (not tOrF))
				(= paused FALSE)
				(wrapSound pause: 0 fade: vol 5 5 0)
			else
				(= paused TRUE)
				(wrapSound fade: 0 5 5 0)
			)
		)
	)
)

(class Actions of Code

	(method (doVerb)
		(return FALSE)
	)
)

(instance stopGroop of GradualLooper)

(instance walkCursor of Cursor
	(properties
		view 0
	)
)

(instance lookCursor of Cursor
	(properties
		view 1
	)
)

(instance doCursor of Cursor
	(properties
		view 2
	)
)

(instance talkCursor of Cursor
	(properties
		view 3
	)
)

(instance askCursor of Cursor
	(properties
		view 4
	)
)

(instance exitCursor of Cursor
	(properties
		view 6
	)
)

(instance lb2KDH of EventHandler)

(instance lb2MDH of EventHandler)

(instance lb2DH of EventHandler)

(instance lb2WH of EventHandler)

(instance lb2Exits of EventHandler)

(class LB2 of Game
	
	(method (init &tmp [temp0 22])
		Dialog
		Sync
		Print
		StopWalk
		Polygon
		PolyPath
		Timer
		LBRoom
		ego
		IconBar
		Inventory
		LBIconItem
		(ScriptID SIGHT)
		Narrator
		Oscillate
		(super init:)
		(= version {x.yyy.zzz})
		(= usPhone {800-326-6654})
		(= intPhone {0734-303171})
		(= hintPhone {900-370-5583})
		((ScriptID LBINIT 0) init:)
		(DisposeScript LBINIT)
		(DoAudio Rate 22050)
		(= msgType CD_MSG)
		((ScriptID LBINV 0) init:)
		(= normalCursor walkCursor)
		(= doVerbCode lb2DoVerbCode)
		(= ftrInitializer lb2FtrInit)
		(= approachCode lb2ApproachCode)
		(= theStopGroop stopGroop)
		(= messager lb2Messager)
		((= keyDownHandler lb2KDH) add:)
		((= mouseDownHandler lb2MDH) add:)
		((= directionHandler lb2DH) add:)
		((= walkHandler lb2WH) add:)
		((= exitHandler lb2Exits) add:)
		((= altPolyList (List new:)) name: {altPolys} add:)
		(mouseDownHandler addToFront: lb2Exits)
		(keyDownHandler addToFront: lb2Exits)
		(= pMouse PseudoMouse)
		(WrapMusic add:)
		((= theMusic gameMusic1) owner: self flags: mNOPAUSE init:)
		((= theMusic2 gameMusic2) owner: self flags: mNOPAUSE init:)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon4 icon6 icon7 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor myHighlightColor
			eachElementDo: #lowlightColor myLowlightColor
			curIcon: icon0
			useIconItem: icon6
			helpIconItem: icon9
			walkIconItem: icon0
			disable: ICON_ITEM
			disable:
			state: (| OPENIFONME NOCLICKHELP)
		)
		(if (GameIsRestarting)
			(MemorySegment MS_RESTORE_TO @transferRoom)
		else
			(= transferRoom 28)
		)
		(if (FileIO fileExists {10.scr})
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(theIconBar enable:)
		(= ego LBEgo)
		(user alterEgo: ego canControl: FALSE canInput: FALSE)
		(self newRoom: transferRoom)
	)
	
	(method (doit &tmp obj sel)
		(if
			(and
				(exitHandler size?)
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
			)
			(exitHandler eachElementDo: #doit)
		)
		(if panelObj
			(= obj panelObj)
			(= sel panelSelector)
			(= panelObj (= panelSelector 0))
			(Eval obj sel)
		)
		(super doit:)
	)
	
	(method (replay &tmp temp0)
		(= systemWindow lb2Win)
		(= normalCursor walkCursor)
		(if (and (OneOf curRoomNum 330 335) (== currentAct 2))
			(Palette PALIntensity 0 255 60)
		else
			(Palette PALIntensity 0 255 100)
		)
		(super replay: &rest)
	)
	
	(method (newRoom n)
		(theGame setCursor: waitCursor)
		(pMouse stop:)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(if (and (IsObject fastCast) (fastCast elements?))
			(fastCast eachElementDo: #dispose 1)
		)
		(if (exitHandler size?)
			(exitHandler eachElementDo: #dispose)
		)
		(narrator
			x: -1
			y: -1
			disposeWhenDone: TRUE
			talkWidth: 0
			keepWindow: TRUE
			modeless: FALSE
			showTitle: FALSE
			name: {Narrator}
		)
		(theIconBar disable:)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp node i [temp2 2])
		((ScriptID DISPOSE) doit: roomNum)
		(for ((= i 0)) (< i (timers size?)) ((++ i))
			(timers delete: (= node (timers at: 0)))
			(timers add: node)
		)
		(for ((= i 0)) (< i (WrapMusic size?)) ((++ i))
			(WrapMusic delete: (= node (WrapMusic at: 0)))
			(WrapMusic add: node)
		)
		(if
			(and
				(OneOf roomNum
					335 340 350 355 360 370 400 420 500 510 520 525 530
					540 550 560 565 430 435 440 448 450 454 455 456 460
					480 490 521 600 610 620 630 640 650 666 660 700 710
					715 720 730 740
				)
				(!= currentAct 5)
			)
			(ScriptID 90)
		)
		(if
			(and
				(OneOf roomNum 335 340 350 355 360 370 400)
				(== currentAct 2)
			)
			(ScriptID 93)
		)
		(if (OneOf roomNum 280 210 260 300)
			(ScriptID 91)
		)
		(if
			(and
				(== currentAct 5)
				(OneOf roomNum
					420 430 435 440 448 450 454
					460 480 490 660
				)
			)
			(ScriptID 94)
		)
		(if
			(OneOf roomNum
				100 105 110 120 140 150 155 160
				180 190 220
			)
			(ScriptID 92)
		)
		(if (and debugging (not (OneOf roomNum 100)))
			((ScriptID 10 0) init:)
		)
		(theIconBar enable:)
		(super startRoom: roomNum)
		(if
			(and
				(ego cycler?)
				(not (ego looper?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego setLoop: stopGroop)
		)
		(if (== (theIconBar curIcon?) (theIconBar at: ICON_ITEM))
			(theIconBar curIcon: (theIconBar at: ICON_WALK))
		)
	)
	
	(method (restart)
		(curRoom style: IRISIN drawPic: 780)
		(cast eachElementDo: #hide)
		(Animate (cast elements?) FALSE)
		(MemorySegment 0 @transferRoom 2)
		(super restart:)
	)
	
	(method (restore &tmp oldWin oldCur newCur newEventHandler)
		(= newEventHandler (EventHandler new:))
		(for ((= newCur 0)) (< newCur (addToPics size?)) ((++ newCur))
			(newEventHandler add: (addToPics at: newCur))
		)
		(DrawPic 780 IRISOUT)
		(cast eachElementDo: #hide)
		(Animate 0)
		(= oldCur normalCursor)
		(= normalCursor ARROW_CURSOR)
		(= oldWin systemWindow)
		(= systemWindow SysWindow)
		(super restore: &rest)
		(DrawPic (curRoom picture?) PLAIN)
		(cast eachElementDo: #show)
		(for ((= newCur 0)) (< newCur (newEventHandler size?)) ((++ newCur))
			(addToPics add: (newEventHandler at: newCur))
		)
		(newEventHandler release: dispose:)
		(addToPics doit:)
		(Animate (cast elements?) FALSE)
		(= systemWindow oldWin)
		(= normalCursor oldCur)
		(if (== (= newCur ((theIconBar curIcon?) cursor?)) ARROW_CURSOR)
			(theGame setCursor: waitCursor)
		else
			(theGame setCursor: newCur)
		)
	)
	
	(method (save &tmp oldWin oldCur newCur)
		(= oldCur normalCursor)
		(= normalCursor ARROW_CURSOR)
		(= oldWin systemWindow)
		(= systemWindow SysWindow)
		(super save: &rest)
		(= systemWindow oldWin)
		(= normalCursor oldCur)
		(if (== (= newCur ((theIconBar curIcon?) cursor?)) ARROW_CURSOR)
			(theGame setCursor: waitCursor)
		else
			(theGame setCursor: newCur)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(TAB
							(if (not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
								(if fastCast (return fastCast))
								(ego showInv:)
								(event claimed: TRUE)
							)
						)
						(SHIFTTAB
							(if (not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
								(if fastCast (return fastCast))
								(ego showInv:)
								(event claimed: TRUE)
							)
						)
						(`^q
							(theGame quitGame:)
							(event claimed: TRUE)
						)
						(`^c
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(theGame showControls:)
							)
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
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(if fastCast (return fastCast))
								(theGame save:)
								(event claimed: TRUE)
							)
						)
						(`#7
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(if fastCast (return fastCast))
								(theGame restore:)
								(event claimed: TRUE)
							)
						)
						(`+
							(if (user controls?)
								(= global369 (Max 0 (-- global369)))
								(ego setSpeed: global369)
							)
						)
						(`-
							(if (user controls?)
								(++ global369)
								(ego setSpeed: global369)
							)
						)
						(`=
							(if (user controls?)
								(ego setSpeed: 6)
							)
						)
					)
				)
			)
		)
	)
	
	(method (setCursor form showIt theX theY &tmp oldCur)
		(= oldCur theCursor)
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
		(return oldCur)
	)
	
	(method (quitGame)
		(if
			(Print
				addText: N_QUIT NULL NULL 1 0 0 0
				addIcon: 992 0 0 0 25
				addButton: 1 N_QUIT NULL C_YES 1 140 67 0
				addButton: 0 N_QUIT NULL C_NO 1 140 87 0
				saveCursor: TRUE
				init:
			)
			(super quitGame: TRUE)
		)
	)
	
	(method (pragmaFail)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(if (user canInput:)
			(messager say: NULL ((user curEvent?) message?))
		)
	)
	
	(method (handsOff)
		(if (not theCurIcon)
			(= theCurIcon (theIconBar curIcon?))
		)
		(= oldCanControl (user canControl:))
		(= oldCanInput (user canInput:))
		(user canControl: FALSE canInput: FALSE)
		(ego setMotion: 0)
		(= disabledIcons NULL)
		(theIconBar eachElementDo: #perform checkIcon)
		(theIconBar curIcon: (theIconBar at: ICON_CONTROL))
		(theIconBar disable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_ASK
			ICON_ITEM
			ICON_INVENTORY
		)
		(if (not (HaveMouse))
			(theGame setCursor: INVIS_CURSOR)
		else
			(theGame setCursor: waitCursor)
		)
	)
	
	(method (handsOn param1)
		(user canControl: TRUE canInput: TRUE)
		(theIconBar enable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_ASK
			ICON_ITEM
			ICON_INVENTORY
		)
		(if (not (curRoom inset:))
			(theIconBar enable: ICON_CONTROL)
		)
		(if
			(OneOf curRoomNum
				310 420 454 500 520 525 550
				560 620 630 640 700 730 740
			)
			(theIconBar disable: 7)
		)
		(if (and argc param1) (DisableIcons))
		(if (not (theIconBar curInvIcon?))
			(theIconBar disable: 5)
		)
		(if
			(and
				theCurIcon
				(or
					(!= theCurIcon icon10)
					(== (theIconBar at: 0) icon10)
				)
			)
			(theIconBar curIcon: theCurIcon)
			(theGame setCursor: (theCurIcon cursor?))
			(if
				(and
					(== (theIconBar curIcon?) (theIconBar at: 5))
					(not (theIconBar curInvIcon?))
				)
				(theIconBar advanceCurIcon:)
			)
		)
		(= theCurIcon 0)
		(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
	)
	
	(method (points pValue pFlag)
		(if (and (> argc 1) (Bset pFlag))
			(= pValue 0)
		)
		(if pValue
			(theGame changeScore: pValue)
		)
	)
	
	(method (showControls &tmp temp0)
		((ScriptID 24 0) init: show: dispose:)
	)
	
	(method (showAbout)
		(if
			(or
				(== currentAct 2)
				(OneOf curRoomNum
					435 440 450 454 455 520 521 525
					550 560 565 620 630 650 700 710
					715 720
				)
			)
			(messager say: N_NO_MEMORY NULL NULL 0 0 0)
		else
			((ScriptID 13 0) doit:)
		)
	)
)

(instance icon0 of IconItem
	(properties
		view 990
		loop 0
		cel 0
		type (| userEvent walkEvent)
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun N_WALK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor walkCursor)
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

(instance icon1 of IconItem
	(properties
		view 990
		loop 1
		cel 0
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun N_LOOK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor lookCursor)
		(super init:)
	)
)

(instance icon2 of IconItem
	(properties
		view 990
		loop 2
		cel 0
		message V_DO
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun N_DO
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor doCursor)
		(super init:)
	)
)

(instance icon3 of IconItem
	(properties
		view 990
		loop 3
		cel 0
		message V_TALK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun N_TALK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor talkCursor)
		(super init:)
	)
)

(instance icon4 of IconItem
	(properties
		view 990
		loop 4
		cel 0
		message V_ASK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun N_ASK
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor askCursor)
		(super init:)
	)
)

(instance icon6 of IconItem
	(properties
		view 990
		loop 5
		cel 0
		cursor ARROW_CURSOR
		message NULL
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		maskCel 1
		noun N_ITEM
		helpVerb V_HELP
	)
	
	(method (select relVer &tmp event whichCel thisIcon theX theY)
		(return
			(cond 
				((& signal DISABLED) 0)
				((and argc relVer (& signal RELVERIFY))
					(if (= thisIcon (theIconBar curInvIcon?))
						(= theX
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide
											(thisIcon view?)
											(+ (thisIcon loop?) 1)
											(thisIcon cel?)
										)
									)
									2
								)
								nsLeft
							)
						)
						(= theY
							(+
								(theIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh
											(thisIcon view?)
											(+ (thisIcon loop?) 1)
											(thisIcon cel?)
										)
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if (= thisIcon (theIconBar curInvIcon?))
						(DrawCel
							(thisIcon view?)
							(+ 1 (thisIcon loop?))
							(thisIcon cel?)
							theX
							theY
							-1
						)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if (= thisIcon (theIconBar curInvIcon?))
										(DrawCel
											(thisIcon view?)
											(+ 1 (thisIcon loop?))
											(thisIcon cel?)
											theX
											theY
											-1
										)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if (= thisIcon (theIconBar curInvIcon?))
									(DrawCel
										(thisIcon view?)
										(+ 1 (thisIcon loop?))
										(thisIcon cel?)
										theX
										theY
										-1
									)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if (= thisIcon (theIconBar curInvIcon?))
							(DrawCel
								(thisIcon view?)
								(+ 1 (thisIcon loop?))
								(thisIcon cel?)
								theX
								theY
								-1
							)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					)
					whichCel
				)
				(else TRUE)
			)
		)
	)
)

(instance icon7 of IconItem
	(properties
		view 990
		loop 6
		cel 0
		cursor ARROW_CURSOR
		type NULL
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 9
		noun N_INVENTORY
		helpVerb V_HELP
	)
	
	(method (show)
		(= loop (if (ego wearingGown?) 11 else 6))
		(super show: &rest)
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(ego showInv:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon8 of IconItem
	(properties
		view 990
		loop 7
		cel 0
		cursor ARROW_CURSOR
		message V_BASEBALL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 9
		noun N_CONTROLS
		helpVerb V_HELP
	)
	
	(method (select)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(theGame showControls:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 990
		loop 8
		cel 0
		cursor 9
		type helpEvent
		message V_HELP
		signal (| RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 9
		noun N_HELP
		helpVerb V_HELP
	)
)

(instance icon10 of IconItem
	(properties
		view 990
		loop 10
		cel 0
		message V_EXIT
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun N_EXIT
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor exitCursor)
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

(instance checkIcon of Code
	
	(method (doit theIcon)
		(if
			(and
				(theIcon isKindOf: IconItem)
				(& (theIcon signal?) DISABLED)
			)
			(= disabledIcons
				(|
					disabledIcons
					(>> $8000 (theIconBar indexOf: theIcon))
				)
			)
		)
	)
)

(instance lb2DoVerbCode of Code

	(method (doit theVerb theNoun)
		(DftDoVerb theNoun theVerb)
	)
)

(instance lb2FtrInit of Code
	
	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 40)
		)
		(if (== (obj actions?) ftrDefault)
			(obj actions: 0)
		)
		(if
			(and
				(not (obj approachX?))
				(not (obj approachY?))
			)
			(obj approachX: (obj x?) approachY: (obj y?))
		)
	)
)

(instance lb2Messager of Messager
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					(NARRATOR narrator)
					(BARTENDER
						(ScriptID 310 22)
					)
					(BEAR
						(ScriptID 1904 20)
					)
					(BIFF
						(ScriptID 260 33)
					)
					(BOB
						(ScriptID 1903 14)
					)
					(BOUNCER
						(ScriptID 300 17)
					)
					(BUM
						(ScriptID 1901 16)
					)
					(CORONER
						(if (== curRoomNum 750)
							(ScriptID 750 21)
						else
							(ScriptID 1899 21)
						)
					)
					(COUNTESS
						(ScriptID 1884 29)
					)
					(CRODFOLLER
						(if (Btst 30)
							(ScriptID 230 7)
						else
							(ScriptID 1896 7)
						)
					)
					(DAD
						(ScriptID 1880 1)
					)
					(DRUNK
						(ScriptID 280 41)
					)
					(ERNIE
						(if (== curRoomNum 355)
							(ScriptID 355 23)
						else
							(ScriptID 1893 23)
						)
					)
					(FLAPPER
						(ScriptID 1906 8)
					)
					(HEIMLICH
						(ScriptID 1889 18)
					)
					(IRMGAARD
						(ScriptID 1900 15)
					)
					(LAURA
						(cond 
							((Btst 30)
								(ScriptID 230 2)
							)
							((== curRoomNum 155)
								(ScriptID 155 2)
							)
							((== curRoomNum 220)
								(ScriptID 220 2)
							)
							((== curRoomNum 330)
								(ScriptID 330 2)
							)
							((and (== curRoomNum 355) (not (Btst 91)))
								(ScriptID 355 2)
							)
							((and (== curRoomNum 710) (== (curRoom picture?) 716))
								(ScriptID 710 2)
							)
							(else
								(ScriptID 1881 2)
							)
						)
					)
					(LOFAT
						(ScriptID 1895 4)
					)
					(LUIGI
						(ScriptID 1907 24)
					)
					(MAN3
						(if (== curRoomNum 230)
							(ScriptID 230 37)
						)
					)
					(MAN2
						(if (== curRoomNum 230)
							(ScriptID 230 37)
						)
					)
					(MAN1
						(if (== curRoomNum 230)
							(ScriptID 230 37)
						)
					)
					(OLYMPIA
						(ScriptID 1892 25)
					)
					(ORILEY
						(cond 
							((== curRoomNum 295)
								(ScriptID 295 19)
							)
							((== curRoomNum 770)
								(ScriptID 770 19)
							)
							(else
								(ScriptID 1888 19)
							)
						)
					)
					(PIANO_PLAYER
						(ScriptID 310 30)
					)
					(PIPPIN
						(ScriptID 1882 10)
					)
					(RAMESES
						(if (and (== curRoomNum 710) (== (curRoom picture?) 716))
							(ScriptID 710 27)
						else
							(ScriptID 1891 27)
						)
					)
					(REX
						(ScriptID 480 39)
					)
					(ROCCO
						(ScriptID 1902 13)
					)
					(SAM
						(if (== curRoomNum 220)
							(ScriptID 220 3)
						else
							(ScriptID 1894 3)
						)
					)
					(SARGEANT
						(ScriptID 1897 5)
					)
					(SINGER
						(ScriptID 310 31)
					)
					(STEVE
						(cond 
							((== curRoomNum 240)
								(ScriptID 240 12)
							)
							((== curRoomNum 330)
								(ScriptID 330 12)
							)
							((== curRoomNum 775)
								(ScriptID 775 12)
							)
							(else
								(ScriptID 1887 12)
							)
						)
					)
					(STINKY
						(ScriptID 260 32)
					)
					(TUBBY
						(ScriptID 260 34)
					)
					(TUTSMITH
						(ScriptID 1883 9)
					)
					(WANDERER
						(ScriptID 290 38)
					)
					(WATNEY
						(ScriptID 1886 11)
					)
					(YVETTE
						(ScriptID 1885 28)
					)
					(ZIGGY
						(ScriptID 1890 6)
					)
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(instance lb2ApproachCode of Code
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK $0001)
			(V_TALK $0002)
			(V_WALK $0004)
			(V_DO $0008)
			(V_ASK $0010)
			(V_EXIT $0020)
			(V_MAGNIFIER $0040)
			(V_WATERGLASS $0080)
			(else  $8000)
		)
	)
)

(instance lb2Win of SysWindow
	(properties
		type wCustom
	)
	
	(method (open &tmp thePort theLoop)
		(cond 
			((OneOf curRoomNum 280 210 330 240 260 300)
				(= theLoop 0)
			)
			(
				(OneOf curRoomNum
					210 220 230 260 270 280
					290 295 300 310 320
				)
				(= theLoop 1)
			)
			(
				(OneOf curRoomNum
					100
					105
					110
					120
					140
					150
					155
					160
					180
					190
					220
					335
					340
					350
					355
					360
					370
					400
				)
				(= theLoop 2)
			)
			(
			(OneOf curRoomNum 460 660 700 710 715 720 730 740) (= theLoop 4))
			(
				(OneOf
					curRoomNum
					335
					340
					350
					355
					360
					370
					400
					420
					500
					510
					520
					525
					530
					540
					550
					560
					565
					430
					435
					440
					448
					450
					454
					455
					456
					460
					480
					490
					521
					600
					610
					620
					630
					640
					650
					666
					660
					700
					710
					715
					720
					730
					740
				)
				(= theLoop 3)
			)
			(else (= theLoop 4))
		)
		(= lsLeft (- left (/ (CelWide 994 theLoop 0) 2)))
		(= lsTop (- top (if title 19 else 10)))
		(= lsRight (+ right (/ (CelWide 994 theLoop 0) 2)))
		(= lsBottom (Max (+ bottom 3) (+ lsTop (CelHigh 994 theLoop 0) 3)))
		(= priority 15)
		(super open:)
		(= thePort (GetPort))
		(SetPort 0)
		(Graph GFillRect top left bottom right 3 myBackColor 15)
		(Graph
			GDrawLine
			(- top 1)
			(- left 1)
			(- top 1)
			right
			global151
			15
		)
		(Graph
			GDrawLine
			(- top 1)
			(- left 1)
			bottom
			(- left 1)
			global151
			15
		)
		(Graph
			GDrawLine
			bottom
			(- left 1)
			bottom
			right
			global151
			15
		)
		(Graph
			GDrawLine
			(- top 1)
			right
			bottom
			right
			global151
			15
		)
		(Graph GShowBits top left bottom right VMAP)
		(Graph
			GShowBits
			lsTop
			lsLeft
			(+ lsTop (CelHigh 994 theLoop 0))
			(+ lsLeft (CelWide 994 theLoop 0))
			VMAP
		)
		(Graph
			GShowBits
			lsTop
			(- lsRight (CelWide 994 theLoop 0))
			(+ lsTop (CelHigh 994 theLoop 0))
			lsRight
			VMAP
		)
		(DrawCel 994 theLoop 0 (+ lsLeft 1) (+ lsTop 1) -1)
		(DrawCel
			994
			theLoop
			1
			(- (- lsRight (CelWide 994 theLoop 0)) 1)
			(+ lsTop 1)
			-1
		)
		(SetPort thePort)
	)
)
