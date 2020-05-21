;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm") (include "10.shm")
(use FPInv)
(use FPRoom)
(use FPEgo)
(use Print)
(use Dialog)
(use Messager)
(use Talker)
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
	FP 0
	IsObjectOnControl 1
	Btst 2
	Bset 3
	Bclr 4
	Face 5
	VerbFail 6
	DisableIcons 7
	fpWin 10
	fpApproachCode 12
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
	dongle =  1234
	theMusic
	soundFx
	global104
	numColors
	numVoices
	startingRoom =  210
	oldCanControl
	oldCanInput
	debugging
	buildDate
	usPhone
	intPhone
	disabledIcons
	global115
	global116
	global117
	global118
	timeOver
	global120 =  1
	scoreFont
	global122
	global123
	global124
	oldCurIcon
	gTheNewDButtonValue
	global127
	global128
	ingredientsInCan
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
)
(procedure (IsObjectOnControl theObj theControl)
	(return
		(if (& (theObj onControl: origin) theControl)
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
		(if (== argc 3) (= obj both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= obj whoToCue))
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject obj) obj else 0)
	)
)

(procedure (VerbFail param1 theVerb)
	(if (OneOf theVerb V_DO V_WALK V_TALK V_LOOK)
		(messager say: NULL theVerb NULL NULL NULL NULL)
	else
		(messager say: NULL V_USEIT NULL NULL NULL NULL)
	)
)

(procedure (DisableIcons &tmp i)
	(user canControl: oldCanControl canInput: oldCanInput)
	(= i 0)
	(while (< i 8)
		(if (& disabledIcons (>> $8000 i))
			(theIconBar disable: i)
		)
		(++ i)
	)
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
	
	(method (dispose param1)
		(wrapSound client: 0)
		(if (and argc param1)
			(super dispose:)
		else
			(self release:)
		)
	)
	
	(method (cue &tmp temp0 temp1 temp2)
		(cond 
			((OneOf (wrapSound prevSignal?) -1 0)
				(= temp0 1)
				(cond 
					((and (== loopIt -1) (== currentSound (- size 1)))
						(= temp0 -1)
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
				(if (> (= temp1 (self at: currentSound)) 1000)
					(= temp1 (- temp1 1000))
					(= temp2 1)
				else
					(= temp2 0)
				)
				(wrapSound
					number: temp1
					setLoop: temp0
					flags: mNOPAUSE
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
	
	(method (pause param1)
		(if (IsObject wrapSound)
			(if (and argc (not param1))
				(= paused FALSE)
				(wrapSound pause: 0 fade: vol 5 5 0)
			else
				(= paused TRUE)
				(wrapSound fade: 0 5 5 0)
			)
		)
	)
)

(instance stopGroop of GradualLooper)

(instance walkCursor of Cursor)

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

(instance exitCursor of Cursor
	(properties
		view 6
	)
)

(class FP of Game
	(properties
		printLang NULL
	)
	
	(method (init &tmp [temp0 22])
		Print
		DButton
		StopWalk
		Polygon
		PolyPath
		Timer
		FPRoom
		FPEgo
		IconBar
		Inventory
		FPIconItem
		(ScriptID SIGHT)
		Narrator
		Oscillate
		(super init:)
		(= version {x.yyy.zzz})
		(= buildDate {991-999-9999})
		(= usPhone {9999-999999})
		(= intPhone {992-999-9999})
		((ScriptID FPINIT 0) init:)
		(DisposeScript FPINIT)
		((ScriptID 15 0) init:)
		(= normalCursor walkCursor)
		(= doVerbCode fpDoVerbCode)
		(= ftrInitializer fpFtrInit)
		(= approachCode fpApproachCode)
		(= theStopGroop stopGroop)
		(= messager fpMessager)
		((= altPolyList (List new:)) name: {altPolys} add:)
		(= pMouse PseudoMouse)
		(WrapMusic add:)
		((= theMusic gameMusic1) owner: self flags: 1 init:)
		((= soundFx gameMusic2) owner: self flags: 1 init:)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon4 icon6 icon7 icon8 icon9 icon5
			eachElementDo: #init
			eachElementDo: #highlightColor 37
			eachElementDo: #lowlightColor 33
			curIcon: icon0
			useIconItem: icon6
			helpIconItem: icon9
			walkIconItem: icon0
			disable: ICON_USEIT
			disable: icon4
			disable:
			state: (| OPENIFONME NOCLICKHELP)
		)
		(if (GameIsRestarting)
			(MemorySegment MS_RESTORE_TO @startingRoom)
		else
			(= startingRoom 28)
		)
		(if (FileIO fileExists {10.scr})
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(theIconBar enable:)
		(= ego FPEgo)
		(user alterEgo: ego canControl: FALSE canInput: FALSE)
		(self newRoom: startingRoom)
	)
	
	(method (replay &tmp temp0)
		(= systemWindow fpWin)
		(= normalCursor walkCursor)
		(if
		(and (OneOf curRoomNum 330 335) (== global120 2))
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
			(fastCast eachElementDo: #dispose TRUE)
		)
		(narrator
			x: -1
			y: -1
			disposeWhenDone: TRUE
			talkWidth: 0
			keepWindow: FALSE
			modeless: FALSE
			showTitle: FALSE
			name: {Narrator}
		)
		(theIconBar disable:)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp temp0 i [temp2 2])
		((ScriptID DISPOSE) doit: roomNum)
		(= i 0)
		(while (< i (timers size?))
			(timers delete: (= temp0 (timers at: 0)))
			(timers add: temp0)
			(++ i)
		)
		(= i 0)
		(while (< i (WrapMusic size?))
			(WrapMusic delete: (= temp0 (WrapMusic at: 0)))
			(WrapMusic add: temp0)
			(++ i)
		)
		(if
			(and
				(!= (- (MemoryInfo FreeHeap) 2) (MemoryInfo LargestPtr))
				(Print
					addText: N_FRAGMENTED NULL NULL 1 0 0 DEBUG
					addButton: FALSE N_FRAGMENTED NULL C_SOWHAT 1 0 12 DEBUG
					addButton: TRUE N_FRAGMENTED NULL C_DEBUG 1 70 12 DEBUG
					init:
				)
			)
			(SetDebug)
		)
		(if (OneOf roomNum 220 230 250 270 600 670)
			(ScriptID rgFreddy)
		)
		(if debugging
			((ScriptID DEBUG 0) init:)
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
		(if (== (theIconBar curIcon?) (theIconBar at: ICON_USEIT))
			(theIconBar curIcon: (theIconBar at: ICON_WALK))
		)
	)
	
	(method (restart)
		(= normalCursor ((theIconBar curIcon?) cursor?))
		(theGame setCursor: ARROW_CURSOR TRUE 156 111)
		(if
			(Print
				font: userFont
				addTitle: N_RESTART NULL 4 1 0
				addText: N_RESTART NULL 1 1 45 0 0
				addButton: TRUE N_RESTART NULL 5 1 5 20 0
				addButton: FALSE N_RESTART NULL 6 1 90 20 0
				init:
			)
			(curRoom style: IRISIN drawPic: 780)
			(cast eachElementDo: #hide)
			(Animate (cast elements?) FALSE)
			(MemorySegment MS_SAVE_FROM @startingRoom 2)
			(super restart:)
		else
			(self setCursor: normalCursor TRUE)
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
								(if fastCast
									(return fastCast)
								)
								(ego showInv:)
								(event claimed: TRUE)
							)
						)
						(SHIFTTAB
							(if (not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
								(if fastCast
									(return fastCast)
								)
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
						(`#9
							(if
								(not
									(if (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED)
									else
										(& (theIconBar state?) DISABLED)
									)
								)
								(theGame restart:)
							)
						)
						(`+
							(if (user controls?)
								(ego setSpeed: (Max 0 (- (ego moveSpeed?) 1)))
							)
						)
						(`-
							(if (user controls?)
								(ego setSpeed: (+ (ego moveSpeed?) 1))
							)
						)
						(`=
							(if (user controls?) (ego setSpeed: 6))
						)
					)
				)
			)
		)
	)
	
	(method (setCursor form showIt theX theY &tmp oldCurObj)
		(= oldCurObj theCursor)
		(if argc
			(if (IsObject form)
				((= theCursor form) init:)
			else
				(SetCursor (= theCursor form) 0 0)
			)
		)
		(if (and (> argc 1) (not showIt)) (SetCursor 996 0 0))
		(if (> argc 2)
			(if (< theX 0) (= theX 0))
			(if (< theY 0) (= theY 0))
			(SetCursor theX theY)
		)
		(return oldCurObj)
	)
	
	(method (quitGame)
		((ScriptID FPQUIT 0) init: show: dispose:)
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
		(if (not oldCurIcon)
			(= oldCurIcon (theIconBar curIcon?))
		)
		(= oldCanControl (user canControl:))
		(= oldCanInput (user canInput:))
		(user canControl: FALSE canInput: FALSE)
		(ego setMotion: 0)
		(= disabledIcons NULL)
		(theIconBar eachElementDo: #perform checkIcon)
		(theIconBar curIcon: (theIconBar at: ICON_CONTROL))
		(theIconBar disable: ICON_WALK ICON_LOOK ICON_DO ICON_TALK ICON_USEIT ICON_INVENTORY)
		(if (not (HaveMouse))
			(theGame setCursor: INVIS_CURSOR)
		else
			(theGame setCursor: waitCursor)
		)
	)
	
	(method (handsOn param1)
		(user canControl: TRUE canInput: TRUE)
		(theIconBar enable: ICON_WALK ICON_LOOK ICON_DO ICON_TALK ICON_USEIT ICON_INVENTORY)
		(if (not (curRoom inset:)) (theIconBar enable: 7))
		(if (and argc param1) (DisableIcons))
		(if (not (theIconBar curInvIcon?))
			(theIconBar disable: ICON_USEIT)
		)
		(if
			(and
				oldCurIcon
				(or
					(!= oldCurIcon icon10)
					(== (theIconBar at: ICON_WALK) icon10)
				)
			)
			(theIconBar curIcon: oldCurIcon)
			(theGame setCursor: (oldCurIcon cursor?))
			(if
				(and
					(== (theIconBar curIcon?) (theIconBar at: ICON_USEIT))
					(not (theIconBar curInvIcon?))
				)
				(theIconBar advanceCurIcon:)
			)
		)
		(= oldCurIcon 0)
		(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
	)
	
	(method (showControls &tmp temp0)
		((ScriptID FPCONTROLS 0) init: show: dispose:)
	)
	
	(method (showAbout)
		((ScriptID FPABOUT 0) doit:)
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
		cursor ARROW_CURSOR
		message NULL
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		maskCel 2
		noun N_SKIP
		helpVerb V_HELP
	)
	
	(method (select &tmp [temp0 5])
		(return FALSE)
	)
)

(instance icon5 of IconItem
	(properties
		view 990
		loop 11
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 11
		noun N_SCORE
		helpVerb V_HELP
	)
	
	(method (show &tmp [scoreBuf 7] [sizeRect 4])
		(super show: &rest)
		(Format @scoreBuf 0 0 score possibleScore)
		(TextSize @sizeRect @scoreBuf scoreFont 0)
		(Display @scoreBuf
			p_color 25
			p_font scoreFont
			p_at (+ (- nsLeft 3) (/ (- 50 [sizeRect 3]) 2)) (+ nsTop 16)
		)
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
		noun N_USEIT
		helpVerb V_HELP
	)
	
	(method (select relVer &tmp event whichCel theInvIcon iX iY)
		(return
			(cond 
				((& signal DISABLED) FALSE)
				((and argc relVer (& signal RELVERIFY))
					(if
					(= theInvIcon (theIconBar curInvIcon?))
						(= iX
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide
											(theInvIcon view?)
											(- (theInvIcon loop?) 1)
											(theInvIcon cel?)
										)
									)
									2
								)
								nsLeft
							)
						)
						(= iY
							(+
								(theIconBar y?)
								(/
									(-
										(- nsBottom nsTop)
										(CelHigh
											(theInvIcon view?)
											(- (theInvIcon loop?) 1)
											(theInvIcon cel?)
										)
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if
					(= theInvIcon (theIconBar curInvIcon?))
						(DrawCel
							(theInvIcon view?)
							(- (theInvIcon loop?) 1)
							(theInvIcon cel?)
							iX
							iY
							-1
						)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					(while (!= ((= event (Event new:)) type?) 2)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if
									(= theInvIcon (theIconBar curInvIcon?))
										(DrawCel
											(theInvIcon view?)
											(- (theInvIcon loop?) 1)
											(theInvIcon cel?)
											iX
											iY
											-1
										)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if
								(= theInvIcon (theIconBar curInvIcon?))
									(DrawCel
										(theInvIcon view?)
										(- (theInvIcon loop?) 1)
										(theInvIcon cel?)
										iX
										iY
										-1
									)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if
						(= theInvIcon (theIconBar curInvIcon?))
							(DrawCel
								(theInvIcon view?)
								(- (theInvIcon loop?) 1)
								(theInvIcon cel?)
								iX
								iY
								-1
							)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
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
		type $0000
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 9
		noun N_INVENTORY
		helpVerb V_HELP
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
		message V_HELP
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 9
		noun 7
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
		message 7
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
		(self highlightColor: 37 lowlightColor: 33)
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
				(| disabledIcons (>> $8000 (theIconBar indexOf: theIcon)))
			)
		)
	)
)

(instance fpDoVerbCode of Code
	
	(method (doit theVerb theObj)
		(VerbFail theObj theVerb)
	)
)

(instance fpFtrInit of Code
	
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 40)
		)
		(if (== (theObj actions?) ftrDefault) (theObj actions: 0))
		(if
			(and
				(not (theObj approachX?))
				(not (theObj approachY?))
			)
			(theObj approachX: (theObj x?) approachY: (theObj y?))
		)
	)
)

(instance fpMessager of Messager
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					(NARRATOR narrator)
					(FREDDY (ScriptID tlkFreddy FREDDY))
					(SRINI (ScriptID tlkSrini SRINI))
					(DOC (ScriptID tlkDoc DOC))
					(SAM_ANDREAS (ScriptID tlkSamAndreas SAM_ANDREAS))
					(SMITHIE (ScriptID tlkSmithie SMITHIE))
					(MADAME (ScriptID tlkMadame MADAME))
					(WILLY (ScriptID tlkWilly WILLY))
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(instance fpApproachCode of Code
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK 1)
			(V_TALK 2)
			(V_WALK 4)
			(V_DO 8)
			(V_EXIT 16)
			(else  -32768)
		)
	)
)

(instance fpWin of SysWindow
	(properties
		type wCustom
	)
	
	(method (open &tmp port theLoop)
		(= theLoop 0)
		(= lsLeft (- left (/ (CelWide 994 theLoop 0) 2)))
		(= lsTop (- top (if title 24 else 15)))
		(= lsRight (+ right (/ (CelWide 994 theLoop 0) 2)))
		(= lsBottom (Max (+ bottom 3) (+ lsTop (CelHigh 994 theLoop 0) 3)))
		(= priority 15)
		(super open:)
		(= port (GetPort))
		(SetPort 0)
		(Graph GFillRect top left bottom right 3 34 15)
		(if title (= top (- top 10)))
		(Graph GDrawLine (- top 1) (- left 1) (- top 1) right 17 15)
		(Graph GDrawLine (- top 1) (- left 1) bottom (- left 1) 17 15)
		(Graph GDrawLine bottom (- left 1) bottom right 17 15)
		(Graph GDrawLine (- top 1) right bottom right 17 15)
		(Graph GDrawLine (- top 2) (- left 2) (- top 2) (+ right 1) 19 15)
		(Graph GDrawLine (- top 2) (- left 2) (+ bottom 1) (- left 2) 19 15)
		(Graph GDrawLine (+ bottom 1) (- left 2) (+ bottom 1) (+ right 1) 19 15)
		(Graph GDrawLine (- top 2) (+ right 1) (+ bottom 1) (+ right 1) 19 15)
		(Graph GDrawLine (- top 3) (- left 3) (- top 3) (+ right 2) 16 15)
		(Graph GDrawLine (- top 3) (- left 3) (+ bottom 2) (- left 3) 16 15)
		(Graph GDrawLine (+ bottom 2) (- left 3) (+ bottom 2) (+ right 2) 16 15)
		(Graph GDrawLine (- top 3) (+ right 2) (+ bottom 2) (+ right 2) 16 15)
		(Graph GShowBits (- top 3) (- left 3) (+ bottom 3) (+ right 3) VMAP)
		(Graph GShowBits lsTop lsLeft (+ lsTop (CelHigh 994 theLoop 0)) (+ lsLeft (CelWide 994 theLoop 0)) VMAP)
		(Graph GShowBits lsTop (- lsRight (CelWide 994 theLoop 0)) (+ lsTop (CelHigh 994 theLoop 0)) lsRight VMAP)
		(if title
			(= top (+ top 10))
		)
		(if title
			(= lsTop (- lsTop 1))
		)
		(DrawCel 994 theLoop 0 (+ lsLeft 1) lsTop -1)
		(DrawCel 994 theLoop 1 (- (- lsRight (CelWide 994 theLoop 0)) 1) lsTop -1)
		(SetPort port)
	)
)
