;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
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
	fpWin 8
	proc0_9 9
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
	useObstacles =  1
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
	msgType =  1
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
	global101 =  6
	theMusic1
	theMusic2
	global104
	numColors
	numVoices
	transferRoom
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
	global119
	currentAct =  1
	myScoreColor
	deathReason
	boxTalkCount =  1
	global124
	oldCurIcon
	gTheNewDButtonValue
	global127
	global128
	global129
	global130
	global131
	global132
	global133
	myBackColor =  34
	saloonMusic =  673
	global136
	global137
	sheriffTalkCount
	samTalkCount
	global140
	global141 =  250
	global142
	global143
	global144
	global145
	global146
	syncInterval
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
	endGameFlags
)
(procedure (IsObjectOnControl theObj theControl)
	(return
		(if (& (theObj onControl: origin) theControl)
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

(procedure (VerbFail theObj theVerb &tmp [str 120])
	(cond 
		((OneOf theVerb V_DO V_WALK V_TALK V_LOOK)
			(messager say: NULL theVerb NULL 0 0 0)
		)
		((Message MsgGet curRoomNum (theObj noun?) V_COMBINE NULL 1 @str)
			(messager say: (theObj noun?) V_COMBINE NULL 0 0 curRoomNum)
		)
		((Message MsgGet (theObj modNum?) (theObj noun?) V_COMBINE NULL 1 @str)
			(messager say: (theObj noun?) V_COMBINE NULL 0 0 (theObj modNum?))
		)
		(else
			(messager say: NULL V_COMBINE NULL 0 0 0)
		)
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

(procedure (proc0_9 param1 param2 param3 &tmp temp0 temp1)
	(if (or (< argc 2) (== param2 0)) (= param2 1))
	(if (or (== argc 0) (== param1 1))
		(= temp0 100)
		(while (> temp0 0)
			(Palette PALIntensity 0 256 temp0)
			(= temp0 (- temp0 param2))
		)
		(Palette PALIntensity 0 256 0)
	else
		(Palette PALIntensity 0 256 0)
		(= temp0 0)
		(while (< temp0 100)
			(Palette PALIntensity 0 256 temp0)
			(= temp0 (+ temp0 param2))
		)
	)
	(if (and (== argc 3) param3) (param3 cue:))
)

(class FPSound of Sound
	
	(method (play)
		(if
			(and
				(not (if (<= 0 (- number 2000)) (<= (- number 2000) 999)))
				(<= (DoSound NumVoices) 11)
			)
			(= number (+ (mod number 1000) 1000))
		)
		(super play: &rest)
	)
)

(instance eventTimer of Timer
	
	(method (cue)
		(if (not (curRoom script?))
			(messager say: NULL NULL C_MOVE_IT 0 0 0)
		)
	)
)

(instance gameMusic1 of FPSound
	(properties
		flags mNOPAUSE
	)
)

(instance gameMusic2 of FPSound
	(properties
		flags mNOPAUSE
	)
)

(class PointsSound of FPSound
	(properties
		flags mNOPAUSE
		number 2141
		changedIBState 0
		theAudCount 0
	)
	
	(method (check)
		(if handle (DoSound UpdateCues self))
		(if (or signal (!= theAudCount (DoAudio MuteSound)))
			(= prevSignal signal)
			(= signal 0)
			(if (IsObject client)
				(client cue: self)
			)
			(if changedIBState
				(= changedIBState FALSE)
				(user canInput: TRUE)
			)
			(self dispose:)
		)
	)
)

(instance oneSound of FPSound
	(properties
		flags mNOPAUSE
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

(instance exitCursor of Cursor
	(properties
		view 6
	)
)

(instance gunCursor of Cursor
	(properties
		view 10
	)
)

(class FP of Game
	
	(method (init &tmp [temp0 22])
		Print
		DButton
		StopWalk
		Polygon
		Timer
		PolyPath
		FPRoom
		ego
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
		((ScriptID FPINV 0) init:)
		(= normalCursor walkCursor)
		(= doVerbCode fpDoVerbCode)
		(= ftrInitializer fpFtrInit)
		(= approachCode fpApproachCode)
		(= theStopGroop stopGroop)
		(= messager fpMessager)
		((= altPolyList (List new:)) name: {altPolys} add:)
		(= pMouse PseudoMouse)
		((= theMusic1 gameMusic1) owner: self init:)
		((= theMusic2 gameMusic2) owner: self init:)
		((= theIconBar IconBar)
			add: icon0 icon1 icon2 icon3 icon4 icon6 icon7 icon8 icon9 icon5
			eachElementDo: #init
			eachElementDo: #highlightColor 37
			eachElementDo: #lowlightColor 33
			curIcon: icon0
			useIconItem: icon6
			helpIconItem: icon9
			walkIconItem: icon0
			disable: ICON_USEIT ICON_SKIP
			disable:
			state: (| OPENIFONME NOCLICKHELP)
		)
		(theIconBar disable: ICON_SCORE)
		(if (FileIO fileExists {10.scr})	;no such script in resource file
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(= debugging TRUE)	;added to always enable debug features
		(cond 
			((GameIsRestarting)
				(MemorySegment MS_RESTORE_TO @transferRoom)
			)
			((FileIO fileExists {29.scr})	;no such script resource
				(= transferRoom 29)
			)
			(else
				(= transferRoom 28)
			)
		)
		(theIconBar enable:)
		(= ego FPEgo)
		(user alterEgo: ego canControl: FALSE canInput: FALSE)
		(ego get: -1 iMoney)
		(self newRoom: transferRoom)
	)
	
	(method (replay &tmp temp0)
		(= systemWindow fpWin)
		(= normalCursor walkCursor)
		(super replay: &rest)
	)
	
	(method (newRoom n)
		(theGame setCursor: waitCursor)
		(pMouse stop:)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(if (and (IsObject fastCast)
				(fastCast elements?)
			)
			(fastCast eachElementDo: #dispose 1)
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
	
	(method (startRoom roomNum &tmp theTimer i [temp2 2])
		((ScriptID DISPOSE) doit: roomNum)
		(= i 0)
		(while (< i (timers size?))
			(timers delete: (= theTimer (timers at: 0)))
			(timers add: theTimer)
			(++ i)
		)
		(cond 
			(
				(or
					(and (>= global119 7) (<= global119 9))
					(> global119 21)
					(Btst 1)
				)
				(Bclr 59)
			)
			((not (Btst 59))
				(Bset 59)
				(Bset 71)
			)
		)
		;EO: Added for debug support
		(if (and debugging (not (OneOf roomNum 100)))
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
		(if (== (theIconBar curIcon?)
				(theIconBar at: ICON_USEIT)
			)
			(theIconBar curIcon: (theIconBar at: ICON_WALK))
		)
		(if (OneOf roomNum 200 210 220 230 570 260)
			(eventTimer setReal: eventTimer 105)
		)
	)
	
	(method (restart)
		(if argc
			(theGame setCursor: HAND_CURSOR TRUE 304 172)
			(curRoom style: IRISIN drawPic: 780)
			(cast eachElementDo: #hide)
			(Animate (cast elements?) FALSE)
			(MemorySegment MS_SAVE_FROM @transferRoom 2)
			(super restart:)
		else
			(= normalCursor ((theIconBar curIcon?) cursor?))
			(theGame setCursor: ARROW_CURSOR TRUE 124 115)
			(if
				(Print
					font: userFont
					addTitle: N_RESTART NULL C_RESTART_TITLE 1 0
					addText: N_RESTART NULL C_RESTART_MSG 1 0 3 0
					addButton: 1 N_RESTART NULL C_RESTART_YES 1 90 35 0
					addButton: 0 N_RESTART NULL C_RESTART_NO 1 50 35 0
					init:
				)
				(PalVary PALVARYKILL)
				(Palette PALIntensity 0 256 100)
				(theGame setCursor: HAND_CURSOR TRUE 304 172)
				(curRoom style: IRISIN drawPic: 780)
				(cast eachElementDo: #hide)
				(Animate (cast elements?) FALSE)
				(MemorySegment MS_SAVE_FROM @transferRoom 2)
				(SetVideoMode 0)
				(super restart:)
			else
				(theGame setCursor: normalCursor)
			)
		)
	)
	
	(method (restore)
		(messager say: NULL NULL C_CANT_RESTORE 0 0 0)
	)
	
	(method (save)
		(messager say: NULL NULL C_CANT_SAVE 0 0 0)
	)
	
	(method (handleEvent event &tmp oldCur temp1)
		(if (OneOf curRoomNum 200 210 220 230 570 260)
			(eventTimer setReal: eventTimer 105)
		)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(`^q
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(theGame quitGame:)
								(event claimed: TRUE)
							)
						)
						(`@x
							(= quit TRUE)
							(event claimed: TRUE)
						)
						(`^c
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(= oldCur normalCursor)
								(= normalCursor ARROW_CURSOR)
								(theGame showControls:)
								(= normalCursor oldCur)
								(if (== (= temp1 ((theIconBar curIcon?) cursor?)) ARROW_CURSOR)
									(theGame setCursor: waitCursor)
								else
									(theGame setCursor: temp1)
								)
								(event claimed: TRUE)
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
							(if
								(not
									(if (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED)
									else
										(& (theIconBar state?) DISABLED)
									)
								)
								(if fastCast (return fastCast))
								(theGame save:)
								(event claimed: TRUE)
							)
						)
						(`#7
							(if
								(not
									(if (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED)
									else
										(& (theIconBar state?) DISABLED)
									)
								)
								(if fastCast (return fastCast))
								(theGame restore:)
								(event claimed: TRUE)
							)
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
								(event claimed: TRUE)
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
		(theIconBar disable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_USEIT
			ICON_INVENTORY
		)
		(if (not (HaveMouse))
			(theGame setCursor: INVIS_CURSOR)
		else
			(theGame setCursor: waitCursor)
		)
	)
	
	(method (handsOn allHands)
		(user canControl: TRUE canInput: TRUE)
		(theIconBar enable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_USEIT
			ICON_INVENTORY
		)
		(if (not (curRoom inset:))
			(theIconBar enable: ICON_CONTROL)
		)
		(if (and argc allHands)
			(DisableIcons)
		)
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
		(eventTimer setReal: eventTimer 105)
	)
	
	(method (points pValue pFlag pSound)
		(if (and (> argc 1) (!= pFlag 0) (Bset pFlag))
			(= pValue 0)
		)
		(if pValue
			(theGame changeScore: pValue)
			(if (user canInput:)
				(PointsSound changedIBState: 1)
				(user canInput: FALSE)
			)
			(PointsSound play: (if (> argc 2) pSound else 0))
			(Wait 2)
			(PointsSound theAudCount: (DoAudio 13))
		)
	)
	
	(method (showControls &tmp temp0)
		((ScriptID FPCONTROLS 0) init: show: dispose:)
	)
	
	(method (showAbout)
		(messager say: NULL NULL C_ABOUT 0 0 0)
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
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 9
		maskCel 2
		noun N_SKIP
		helpVerb V_HELP
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
	
	(method (show &tmp [str 7] [len 4])
		(super show: &rest)
		(Format @str 0 0 score possibleScore)
		(TextSize @len @str myScoreColor 0)
		(Display
			@str
			p_color 25
			p_font myScoreColor
			p_at (+ (- nsLeft 3) (/ (- 50 [len 3]) 2)) (+ nsTop 16)
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
	
	(method (select relVer &tmp event whichCel theIcon theX theY)
		(return
			(cond 
				((& signal DISABLED) 0)
				((and argc relVer (& signal RELVERIFY))
					(if
					(= theIcon (theIconBar curInvIcon?))
						(= theX
							(+
								(/
									(-
										(- nsRight nsLeft)
										(CelWide (theIcon view?) (- (theIcon loop?) 1) (theIcon cel?))
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
										(CelHigh (theIcon view?) (- (theIcon loop?) 1) (theIcon cel?))
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
					(if (= theIcon (theIconBar curInvIcon?))
						(DrawCel (theIcon view?) (- (theIcon loop?) 1) (theIcon cel?) theX theY -1)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= event (Event new:)) type?) mouseUp)
						(event localize:)
						(cond 
							((self onMe: event)
								(if (not whichCel)
									(DrawCel view loop (= whichCel 1) nsLeft nsTop -1)
									(if (= theIcon (theIconBar curInvIcon?))
										(DrawCel (theIcon view?) (- (theIcon loop?) 1) (theIcon cel?) theX theY -1)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
								)
							)
							(whichCel
								(DrawCel view loop (= whichCel 0) nsLeft nsTop -1)
								(if
								(= theIcon (theIconBar curInvIcon?))
									(DrawCel (theIcon view?) (- (theIcon loop?) 1) (theIcon cel?) theX theY -1)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
							)
						)
						(event dispose:)
					)
					(event dispose:)
					(if (== whichCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if
						(= theIcon (theIconBar curInvIcon?))
							(DrawCel (theIcon view?) (- (theIcon loop?) 1) (theIcon cel?) theX theY -1)
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
		cursor 999
		type nullEvt
		message 0
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
				((ScriptID FPINV 2) doit: ego)
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
		noun N_CONTROL
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
		message V_GAME
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun 10
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

(instance icon11 of IconItem
	(properties
		view 990
		loop 12
		cel 0
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 9
		noun N_DO
		helpVerb V_HELP
	)
	
	(method (init)
		(= cursor gunCursor)
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
				(| disabledIcons (>> FORCE (theIconBar indexOf: theIcon)))
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
		(if (== (theObj actions?) ftrDefault)
			(theObj actions: 0)
		)
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
					(SYSMSG narrator)
					(FREDDY (ScriptID tlkFreddy FREDDY))
					(SRINI (ScriptID tlkSrini SRINI))
					(DOC (ScriptID tlkDoc DOC))
					(SAM_ANDREAS (ScriptID tlkSamAndreas SAM_ANDREAS))
					(SMITHIE (ScriptID tlkSmithie SMITHIE))
					(SHERIFF (ScriptID tlkSheriff SHERIFF))
					(WILLY (ScriptID tlkWilly WILLY))
					(BILLY (ScriptID tlkBilly BILLY))
					(AL (ScriptID tlkAl AL))
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(instance fpApproachCode of Code
	(properties)
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK 1)
			(V_TALK 2)
			(V_WALK 4)
			(V_DO 8)
			(8 16)
			(else  -32768)
		)
	)
)

(instance fpWin of SysWindow
	(properties
		type wCustom
	)
	
	(method (open &tmp savePort theLoop saveTop saveLeft saveRight)
		(switch currentAct
			(1 (= theLoop 0))
			(2
				(if (Btst 1)
					(= theLoop 2)
				else
					(= theLoop 1)
				)
			)
			(3 (= theLoop 3))
			(4 (= theLoop 4))
			(5 (= theLoop 4))
		)
		(= lsLeft (- (- left 3) 15))
		(= lsTop (- (- top 3) (if title 25 else 15)))
		(= lsRight (+ right 3 15))
		(= lsBottom
			(Max (+ bottom 3) (+ lsTop (CelHigh 994 theLoop 0) 3))
		)
		(= priority 15)
		(super open:)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GFillRect top left bottom right 3 myBackColor 15)
		(if title (= top (- top 10)))
		(Graph
			GDrawLine
			(- top 1)
			(- left 1)
			(- top 1)
			right
			17
			15
		)
		(Graph
			GDrawLine
			(- top 1)
			(- left 1)
			bottom
			(- left 1)
			17
			15
		)
		(Graph GDrawLine bottom (- left 1) bottom right 17 15)
		(Graph GDrawLine (- top 1) right bottom right 17 15)
		(Graph
			GDrawLine
			(- top 2)
			(- left 2)
			(- top 2)
			(+ right 1)
			19
			15
		)
		(Graph
			GDrawLine
			(- top 2)
			(- left 2)
			(+ bottom 1)
			(- left 2)
			19
			15
		)
		(Graph
			GDrawLine
			(+ bottom 1)
			(- left 2)
			(+ bottom 1)
			(+ right 1)
			19
			15
		)
		(Graph
			GDrawLine
			(- top 2)
			(+ right 1)
			(+ bottom 1)
			(+ right 1)
			19
			15
		)
		(Graph
			GDrawLine
			(- top 3)
			(- left 3)
			(- top 3)
			(+ right 2)
			16
			15
		)
		(Graph
			GDrawLine
			(- top 3)
			(- left 3)
			(+ bottom 2)
			(- left 3)
			16
			15
		)
		(Graph
			GDrawLine
			(+ bottom 2)
			(- left 3)
			(+ bottom 2)
			(+ right 2)
			16
			15
		)
		(Graph
			GDrawLine
			(- top 3)
			(+ right 2)
			(+ bottom 2)
			(+ right 2)
			16
			15
		)
		(Graph
			GShowBits
			(- top 3)
			(- left 3)
			(+ bottom 3)
			(+ right 3)
			1
		)
		(switch currentAct
			(1
				(= saveLeft (+ lsLeft 2))
				(= saveRight (- (- lsRight 15) 14))
				(= saveTop lsTop)
			)
			(2
				(if (Btst 1)
					(= saveLeft lsLeft)
					(= saveRight (- (- lsRight 15) 13))
					(= saveTop (+ lsTop 2))
				else
					(= saveLeft (+ lsLeft 8))
					(= saveRight (- (- lsRight 15) 14))
					(= saveTop (+ lsTop 4))
				)
			)
			(3
				(= saveLeft (+ lsLeft 6))
				(= saveRight (- (- lsRight 15) 40))
				(= saveTop (+ lsTop 11))
			)
			(4
				(= saveLeft (+ lsLeft 7))
				(= saveRight (- (- lsRight 15) 14))
				(= saveTop (+ lsTop 8))
			)
			(5
				(= saveLeft (+ lsLeft 7))
				(= saveRight (- (- lsRight 15) 14))
				(= saveTop (+ lsTop 8))
			)
		)
		(DrawCel 994 theLoop 0 saveLeft saveTop -1)
		(DrawCel 994 theLoop 1 saveRight saveTop -1)
		(SetPort savePort)
	)
)
