;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use ColorInit)
(use Smopper)
(use SQEgo)
(use ScrollableInventory)
(use ScrollInsetWindow)
(use DColorButton)
(use SpeakWindow)
(use Print)
(use Dialog)
(use Messager)
(use Talker)
(use PMouse)
(use Scaler)
(use BordWind)
(use IconBar)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Timer)
(use Grooper)
(use Sound)
(use Game)
(use User)
(use System)

(public
	SQ5 0
	Btst 1
	Bset 2
	Bclr 3
	DisableIcons 4
	IsObjectOnControl 5
	NormalEgo 6
	Face 8
	EgoDead 9
	SolvePuzzle 10
	proc0_11 11
	WhichLanguage 12
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
	cDAudio
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
	debugging
	dongle =  1234
	numColors
	numVoices
	global104 =  100
	global105
	theStopGroop
	global107
	oldCurIcon
	oldCanControl
	oldCanInput
	disabledIcons
	global112
	eurekaCurLocation
	curTestQuestion
	testScore
	theSpeakWindow
	gSq5Win_2
	global118
	deathReason
	theMusic1
	theMusic2
	curTalker
	egoSpeed
	global124
	global125
	global126
	global127
	global128
	global129
	global130
	global131
	WDShipState
	goliathFloor
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
	myTextColor
	global152
	global153
	global154
	global155
	global156
	global157
	global158
	myBackColor
	myLowlightColor
	currentEgoView =  1
	gRegister
	global163
	global164
	global165
	global166
	global167
	global168
	global169
	wd40State
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
	gameFlags
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
	endGameFlags
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

(procedure (IsObjectOnControl theObj theControl)
	(return
		(if (& (theObj onControl: origin) theControl)
			(return TRUE)
		else
			FALSE
		)
	)
)

(procedure (NormalEgo theView theLoop)
	(if (and (> argc 0) (!= theView -1))
		(ego view: theView)
		(if (and (> argc 1) (!= theLoop -1))
			(ego loop: theLoop)
		)
	else
		(ego view: currentEgoView)
		(if (and (> argc 1) (!= theLoop -1))
			(ego loop: theLoop)
		)
	)
	(if (ego looper?) ((ego looper?) dispose:))
	(ego
		setStep: 5 2
		illegalBits: 0
		ignoreActors: 0
		setSpeed: egoSpeed
		signal: (| (ego signal?) skipCheck)
		heading:
			(switch (ego loop?)
				(loopE 90)
				(loopW 270)
				(loopS 180)
				(loopN 0)
				(loopSE 135)
				(loopSW 225)
				(loopNE 45)
				(loopNW 315)
			)
	)
	(ego
		setLoop: -1
		setLoop: stopGroop
		setCycle: egoStopWalk -1 2 0
		setPri: -1
		setMotion: 0
		state: (| (ego state?) startUpdOn)
	)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY temp3 temp4)
	(= temp3 0)
	(= temp4 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (> argc 2)
			(if (IsObject both)
				(= temp3 both)
			else
				(= temp4 both)
			)
			(if (== argc 4) (= temp3 whoToCue))
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= temp3 whoToCue))
	)
	(if temp4 (Face actor2 actor1))
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject temp3) temp3 else 0)
	)
)

(procedure (EgoDead theReason)
	(if (not argc)
		(= deathReason 1)
	else
		(= deathReason theReason)
	)
	(curRoom newRoom: 20)
)

(procedure (SolvePuzzle flagEnum points)
	(if (not (Btst flagEnum))
		(= score (+ score points))
		(sq5StatusLineCode doit:)
		(Bset flagEnum)
		(rm0Sound
			priority: 15
			number: 1000
			loop: 1
			flags: mNOPAUSE
			play:
		)
	)
)

(procedure (proc0_11 &tmp port)
	(= port (GetPort))
	(SetPort -1)
	(Graph GFillRect 0 0 10 320 1 0 -1 -1)
	(Graph GShowBits 0 0 10 320 1)
	(SetPort port)
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

(instance rm0Sound of Sound
	(properties
		priority 15
	)
)

(instance sq5Music1 of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance sq5Music2 of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance stopGroop of GradualLooper)

(instance egoStopWalk of FiddleStopWalk)

(instance egoObj of SQEgo
	(properties
		name "ego"
	)
)

(instance sq5StatusLineCode of Code
	
	(method (doit &tmp [scoreBuf 50] [statusBuf 50] port)
		(= port (GetPort))
		(SetPort -1)
		(Graph GFillRect 0 0 10 320 1 5 -1 -1)
		(Graph GShowBits 0 0 10 320 1)
		(Message MsgGet 0 29 0 0 1 @scoreBuf)
		(Format @statusBuf {%s %d} @scoreBuf score)
		(Display @statusBuf p_at 4 1 p_font userFont p_color 6)
		(Display @statusBuf p_at 6 3 p_font userFont p_color 4)
		(Display @statusBuf p_at 5 2 p_font userFont p_color 0)
		(Graph GDrawLine 0 0 0 319 7 -1 -1)
		(Graph GDrawLine 0 0 9 0 6 -1 -1)
		(Graph GDrawLine 9 0 9 319 4 -1 -1)
		(Graph GDrawLine 0 319 9 319 3 -1 -1)
		(Graph GShowBits 0 0 10 319 1)
		(SetPort port)
	)
)

(instance sq5IconBar of IconBar
	
	(method (show)
		(if (IsObject curInvIcon)
			(curInvIcon loop: 2)
		)
		(super show:)
		(if (IsObject curInvIcon)
			(curInvIcon loop: 1)
		)
	)
	
	(method (hide)
		(super hide: &rest)
		(theGame setCursor: theCursor TRUE)
	)
	
	(method (noClickHelp &tmp event lastIcon thisIcon oldPort eO)
		(= lastIcon (= thisIcon 0))
		(= oldPort (GetPort))
		(= eO (systemWindow eraseOnly?))
		(systemWindow eraseOnly: TRUE)
		(while (not ((= event ((user curEvent?) new:)) type?))
			(if (not (self isMemberOf: IconBar)) (event localize:))
			(cond 
				((= thisIcon (self firstTrue: #onMe event))
					(if (and (!= thisIcon lastIcon) (thisIcon helpVerb?))
						(= lastIcon thisIcon)
						(if modelessDialog (modelessDialog dispose:))
						(Print
							font: userFont
							width: 250
							addText: (thisIcon noun?) (thisIcon helpVerb?) NULL 1 0 0 (thisIcon modNum?)
							modeless: TRUE
							init:
						)
						(Animate (cast elements?) FALSE)
						(SetPort oldPort)
					)
				)
				(modelessDialog (modelessDialog dispose:) (Animate (cast elements?) FALSE))
				(else (= lastIcon 0))
			)
			(event dispose:)
		)
		(systemWindow eraseOnly: eO)
		(theGame setCursor: ARROW_CURSOR TRUE)
		(if modelessDialog
			(modelessDialog dispose:)
			(Animate (cast elements?) FALSE)
		)
		(SetPort oldPort)
		(if (not (helpIconItem onMe: event))
			(self dispatchEvent: event)
		)
	)
)

(class SQ5 of Game
	
	(method (init &tmp [temp0 7] versionFile)
		Print
		DButton
		DColorButton
		SmoothStopper
		SQEgo
		StopWalk
		BorderWindow
		SpeakWindow
		Polygon
		PolyPath
		Timer
		IconBar
		ScrollInsetWindow
		ScrollableInventory
		(ScriptID SIGHT)
		RTRandCycle
		Scaler
		Narrator
		((ScriptID SQ5INV 0) init:)
		(super init:)
		(= ego egoObj)
		(User alterEgo: ego canControl: FALSE canInput: FALSE)
		(= msgType TEXT_MSG)
		(= useSortedFeatures TRUE)
		(= numVoices (DoSound NumVoices))
		(= possibleScore 5000)
		(= userFont 1605)
		(= egoSpeed 6)
		(= eatMice 30)
		(= textSpeed 2)
		(= numColors (Graph GDetect))
		(= theStopGroop stopGroop)
		(= pMouse PseudoMouse)
		(= global105 1)
		(ego setLoop: theStopGroop)
		(if (== numColors 256) (Bset fIsVGA))
		(TextFonts 1605 999 1005 1007 1008 2106 1307 2306 5220)
		(TextColors 0 15 26 31 34 52 63)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileClose versionFile)
		(ColorInit)
		(DisposeScript COLORINIT)
		(= narrator sQ5Narrator)
		(= systemWindow sq5Win)
		(= gSq5Win_2 sq5Win)
		(= messager testMessager)
		(= debugging TRUE)	;added to enable debug features
		(= theSpeakWindow (SpeakWindow new:))
		(systemWindow color: 0 back: myBackColor)
		(theGame setCursor: theCursor TRUE 304 172 detailLevel: 3)
		((= theMusic1 sq5Music1)
			number: 1
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= theMusic2 sq5Music2)
			number: 1
			owner: self
			flags: mNOPAUSE
			init:
		)
		(= theIconBar sq5IconBar)
		(theIconBar
			add: icon0 icon1 icon2 icon3 icon4 icon6 icon7 icon8 icon9
			eachElementDo: #init
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor 5
			curIcon: icon0
			useIconItem: icon6
			helpIconItem: icon9
			walkIconItem: icon0
			disable: ICON_ITEM
			state: (| OPENIFONME NOCLICKHELP)
			disable:
		)
		(= normalCursor ARROW_CURSOR)
		(= waitCursor INVIS_CURSOR)
		
		(= doVerbCode lb2DoVerbCode)
		(= ftrInitializer lb2FtrInit)
		(= approachCode lb2ApproachCode)
		(self newRoom: 100)
	)
	
	(method (doit)
		(if (GameIsRestarting)
			(if (OneOf curRoomNum 100 104 110 106 107)
				(proc0_11)
			else
				(sq5StatusLineCode doit:)
			)
			(if (== (= numColors (Graph GDetect)) 256)
				(Bset fIsVGA)
			)
		)
		(super doit: &rest)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (GetSaveDir))
		(if (not (GameIsRestarting))
			(GetCWD curSaveDir)
		)
		(self
			setCursor: waitCursor TRUE
			init:
		)
		(self
			setCursor: INVIS_CURSOR TRUE
		)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (startRoom roomNum &tmp [temp0 4])
		(if (OneOf roomNum 100 104 110 106 107)
			(proc0_11)
		else
			(sq5StatusLineCode doit:)
		)
		(if pMouse
			(pMouse stop:)
		)
		((ScriptID DISPOSE) doit: roomNum)
		(cond 
			((OneOf roomNum
				106 107 200 201 202 203 204 205 206 212 213 215
				222 225 226 228 230 240 250 280 290 295)
				(ScriptID rgEureka)
			)
			((OneOf roomNum
				110 115 117 119 121 122 123 125 127 132 133 135
				137 165 166 195
				)
				(ScriptID rgStarCon)
			)
			((OneOf roomNum 300 305 310 315 320 325 330 335)
				(ScriptID rgKiz)
			)
			((OneOf roomNum 500 510 520 530)
				(ScriptID rgSBar)
			)
			((OneOf roomNum 730 740 750 760 770 790)
				(ScriptID rgGenetix)
			)
		)
		(if debugging	;added for debug support
			((ScriptID DEBUG 0) init:)
		)
		(super startRoom: roomNum)
	)
	
	(method (restart &tmp tOrF oldCur)
		(= oldCur ((theIconBar curIcon?) cursor?))
		(theGame setCursor: ARROW_CURSOR)
		(if
			(= tOrF
				(Print
					font: userFont
					width: 75
					window: systemWindow
					mode: teJustCenter
					addText: N_RESTART V_LOOK NULL 1 0 0 0
					addColorButton: 1 N_RESTART V_LOOK NULL 2 0 40 0
					addColorButton: 0 N_RESTART V_LOOK NULL 3 0 50 0
					init:
				)
			)
			(super restart: &rest)
		else
			(theGame setCursor: oldCur)
		)
	)
	
	(method (restore &tmp [temp0 2])
		(super restore: &rest)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (save)
		(super save: &rest)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
	)
	
	(method (handleEvent event &tmp oldCurObj)
		(super handleEvent: event)
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
								(= oldCurObj theCursor)
								(inventory showSelf: ego)
								(theGame setCursor: oldCurObj TRUE)
								(event claimed: TRUE)
							)
						)
						(`^q
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(theGame quitGame:)
								(event claimed: TRUE)
							)
						)
						(`^c
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(= oldCurObj ((theIconBar curIcon?) cursor?))
								((ScriptID SQ5CONTROLS 0) doit:)
								(gameControls dispose:)
								(theGame setCursor: oldCurObj TRUE)
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
								(if fastCast
									(return fastCast)
								)
								(= oldCurObj theCursor)
								(theGame save:)
								(theGame setCursor: oldCurObj TRUE)
								(event claimed: TRUE)
							)
						)
						(`#7
							(if (not (& ((theIconBar at: ICON_CONTROL) signal?) DISABLED))
								(if fastCast
									(return fastCast)
								)
								(= oldCurObj theCursor)
								(theGame restore:)
								(theGame setCursor: oldCurObj TRUE)
								(event claimed: TRUE)
							)
						)
						(`+
							(if (user controls?)
								(= egoSpeed (ego moveSpeed?))
								(= egoSpeed (Max 0 (-- egoSpeed)))
								(ego setSpeed: egoSpeed)
							)
						)
						(`-
							(if (user controls?)
								(= egoSpeed (ego moveSpeed?))
								(++ egoSpeed)
								(ego setSpeed: egoSpeed)
							)
						)
						(`=
							(if (user controls?)
								(ego setSpeed: 6)
							)
						)
						(`@v
							(Print
								addText: {Version number:} 0 0
								addText: version 0 14
								init:
							)
						)
						(else
							(event claimed: FALSE)
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
		(if (and (> argc 1) (not showIt))
			(SetCursor INVIS_CURSOR 0 0)
		)
		(if (> argc 2)
			(SetCursor theX theY)
		)
		(return oldCurObj)
	)
	
	(method (quitGame &tmp tOrF oldCur)
		(= oldCur ((theIconBar curIcon?) cursor?))
		(theGame setCursor: ARROW_CURSOR)
		(if
			(= tOrF
				(Print
					font: userFont
					width: 75
					mode: teJustCenter
					addText: N_QUIT V_LOOK NULL 1 0 0 0
					addColorButton: 1 N_QUIT V_LOOK NULL 2 0 25 0
					addColorButton: 0 N_QUIT V_LOOK NULL 3 0 35 0
					init:
				)
			)
			(Print addText: N_QUIT V_LOOK NULL 4 0 0 0 init:)
			(super quitGame: &rest)
		else
			(theGame setCursor: oldCur)
		)
	)
	
	(method (pragmaFail)
		(if (User canControl:)
			(switch ((user curEvent?) message?)
				(V_DO
					(messager say: NULL V_DO NULL (Random 1 2) 0 0)
				)
				(V_TALK
					(messager say: NULL V_TALK NULL (Random 1 2) 0 0)
				)
				(V_COMMAND
					(messager say: NULL V_COMMAND NULL 0 0 0)
				)
				(else 
					(if (not (OneOf ((user curEvent?) message?) V_COMMAND V_LOOK))
						(messager say: NULL V_USEIT NULL (Random 2 3) 0 0)
					)
				)
			)
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
		(= disabledIcons 0)
		(theIconBar eachElementDo: #perform checkIcon)
		(theIconBar curIcon: (theIconBar at: ICON_CONTROL))
		(theIconBar disable:)
		(theIconBar disable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_ORDER
			ICON_ITEM
			ICON_INVENTORY
			ICON_CONTROL
		)
		(theGame setCursor: INVIS_CURSOR)
	)
	
	(method (handsOn param1)
		(theIconBar enable:)
		(user canControl: TRUE canInput: TRUE)
		(if (Btst fEgoIsFly)
			(theIconBar enable:
				ICON_WALK
				ICON_LOOK
				ICON_TALK
				ICON_CONTROL
			)
		else
			(theIconBar enable:
				ICON_WALK
				ICON_LOOK
				ICON_DO
				ICON_TALK
				ICON_ORDER
				ICON_ITEM
				ICON_INVENTORY
				ICON_CONTROL
			)
		)
		(if (and argc param1)
			(DisableIcons)
		)
		(if (not (theIconBar curInvIcon?))
			(theIconBar disable: ICON_ITEM)
		)
		(if oldCurIcon
			(theIconBar curIcon: oldCurIcon)
			(theGame setCursor: (oldCurIcon cursor?))
			(= oldCurIcon 0)
			(if
				(and
					(== (theIconBar curIcon?) (theIconBar at: ICON_ITEM))
					(not (theIconBar curInvIcon?))
				)
				(theIconBar advanceCurIcon:)
			)
		)
		(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		(= theCursor ((theIconBar curIcon?) cursor?))
	)
	
	(method (showAbout)
		((ScriptID ABOUT 0) doit:)
		(DisposeScript ABOUT)
	)
	
	(method (showControls &tmp oldCur)
		(= oldCur ((theIconBar curIcon?) cursor?))
		((ScriptID SQ5CONTROLS 0) doit:)
		(gameControls dispose:)
		(theGame setCursor: oldCur TRUE)
	)
)

(instance walkCursor of Cursor
	(properties
		view 980
	)
	
	(method (init)
		(cond 
			((Btst fEgoIsFly)
				(= loop 1)
			)
			((and (== curRoomNum 119) (== (ego view?) 136))
				(= loop 3)
			)
			(else
				(= loop 0)
			)
		)
		(super init: &rest)
	)
)

(instance icon0 of IconItem
	(properties
		view 990
		loop 0
		cel 0
		cursor 980
		type (| userEvent walkEvent)
		message V_WALK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 13
		noun N_WALK
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(= cursor walkCursor)
		(super init:)
	)
	
	(method (show)
		(cond 
			((Btst fEgoIsFly) (= loop 14))
			((and (== curRoomNum 119) (== (ego view?) 136))
				(= loop 15)
			)
			(else
				(= loop 0)
			)
		)
		(super show: &rest)
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
		cursor 981
		message V_LOOK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 13
		noun N_LOOK
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
)

(instance icon2 of IconItem
	(properties
		view 990
		loop 2
		cel 0
		cursor 982
		message V_DO
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 13
		noun N_DO
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
)

(instance icon3 of IconItem
	(properties
		view 990
		loop 3
		cel 0
		cursor 983
		message V_TALK
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 13
		maskCel 4
		noun N_TALK
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
)

(instance icon4 of IconItem
	(properties
		view 990
		loop 10
		cel 1
		cursor 984
		message V_COMMAND
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 13
		maskCel 4
		noun N_ORDER
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
)

(instance icon6 of IconItem
	(properties
		view 990
		loop 4
		cel 0
		cursor ARROW_CURSOR
		message NULL
		signal (| HIDEBAR RELVERIFY)
		maskView 990
		maskLoop 13
		maskCel 4
		noun N_USEIT
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
	
	(method (select relVer &tmp evt theCel thisIcon theX theY)
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
										(CelWide (thisIcon view?) 2 (thisIcon cel?))
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
										(CelHigh (thisIcon view?) 2 (thisIcon cel?)
										)
									)
									2
								)
								nsTop
							)
						)
					)
					(DrawCel view loop (= theCel 1) nsLeft nsTop -1)
					(if (= thisIcon (theIconBar curInvIcon?))
						(DrawCel (thisIcon view?) 2 (thisIcon cel?) theX theY -1)
					)
					(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
					(while (!= ((= evt (Event new:)) type?) mouseUp)
						(evt localize:)
						(cond 
							((self onMe: evt)
								(if (not theCel)
									(DrawCel view loop (= theCel 1) nsLeft nsTop -1)
									(if
									(= thisIcon (theIconBar curInvIcon?))
										(DrawCel
											(thisIcon view?)
											2
											(thisIcon cel?)
											theX
											theY
											-1
										)
									)
									(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
								)
							)
							(theCel
								(DrawCel view loop (= theCel 0) nsLeft nsTop -1)
								(if
								(= thisIcon (theIconBar curInvIcon?))
									(DrawCel
										(thisIcon view?)
										2
										(thisIcon cel?)
										theX
										theY
										-1
									)
								)
								(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
							)
						)
						(evt dispose:)
					)
					(evt dispose:)
					(if (== theCel 1)
						(DrawCel view loop 0 nsLeft nsTop -1)
						(if
						(= thisIcon (theIconBar curInvIcon?))
							(DrawCel
								(thisIcon view?)
								2
								(thisIcon cel?)
								theX
								theY
								-1
							)
						)
						(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
					)
					theCel
				)
				(else 1)
			)
		)
	)
)

(instance icon7 of IconItem
	(properties
		view 990
		loop 5
		cel 0
		cursor ARROW_CURSOR
		type nullEvt
		message NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 13
		noun N_INVENTORY
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
	)
	
	(method (select &tmp oldCurObj)
		(return
			(if (super select: &rest)
				(theIconBar hide:)
				(= oldCurObj theCursor)
				(inventory showSelf: ego)
				(theGame setCursor: oldCurObj TRUE)
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
		message V_USEIT
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 13
		noun N_CONTROL
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(super init:)
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
		loop 9
		cel 0
		cursor 989
		type helpEvent
		message 5
		signal (| RELVERIFY IMMEDIATE)
		maskView 990
		maskLoop 13
		noun N_HELP
		helpVerb V_HELP
	)
	
	(method (init)
		(= lowlightColor myLowlightColor)
		(if modelessDialog (modelessDialog dispose:))
		(super init:)
	)
)

(instance checkIcon of Code
	(properties)
	
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

(instance lb2DoVerbCode of Code
	(properties)
	
	(method (doit theVerb theObj)
		(if (User canControl:)
			(if (== theObj ego)
				(if (Message MsgSize 0 N_EGO theVerb NULL 1)
					(messager say: N_EGO theVerb NULL 0 0 0)
				else
					(messager say: 22 0 0 (Random 1 2) 0 0)
				)
			else
				(switch theVerb
					(V_DO
						(messager say: NULL V_DO NULL (Random 1 2) 0 0)
					)
					(V_TALK
						(messager say: NULL V_TALK NULL (Random 1 2) 0 0)
					)
					(V_COMMAND
						(messager say: NULL V_COMMAND NULL 1 0 0)
					)
					(else 
						(if (not (OneOf theVerb V_COMMAND V_LOOK))
							(messager say: NULL V_USEIT NULL (Random 2 3) 0 0)
						)
					)
				)
			)
		)
	)
)

(instance lb2FtrInit of Code
	(properties)
	
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 90)
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

(instance lb2ApproachCode of Code
	(properties)
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK 1)
			(V_TALK 2)
			(V_WALK 4)
			(V_DO 8)
			(V_SPIKE 16)
			(V_COMMAND 32)
			(V_ANTACID 64)
			(V_OXYGEN_TANK 128)
			(else  -32768)
		)
	)
)

(instance sq5Win of BorderWindow)

(instance sQ5Narrator of Narrator
	
	(method (init)
		(= font userFont)
		(self back: myBackColor)
		(super init: &rest)
	)
)

(instance testMessager of Messager
	
	(method (findTalker who &tmp theTalker)
		(= curTalker who)
		(if
			(= theTalker
				(switch who
					(NARRATOR
						(if (== curRoomNum 666)
							(ScriptID 666 1)
						else
							narrator
						)
					)
					(ALIEN
						(if (OneOf curRoomNum 201 206)
							(if (== curRoomNum 201)
								(ScriptID 209 18)
							else
								(ScriptID curRoomNum 18)
							)
						else
							(ScriptID tlkBea BEA)
						)
					)
					(BEA
						(if (OneOf curRoomNum 119 127 240 1041 660)
							(ScriptID curRoomNum 10)
						else
							(ScriptID tlkBea BEA)
						)
					)
					(CADETS
						(ScriptID 109 7)
					)
					(DAVE
						(ScriptID 125 1)
					)
					(CLIFFY
						(if (OneOf curRoomNum 530 730 666 240)
							(ScriptID curRoomNum 11)
						else
							(ScriptID tlkCliffy CLIFFY)
						)
					)
					(DANTE
						(ScriptID 127 3)
					)
					(BCRUISER_HELP
						(ScriptID 850 1)
					)
					(DROOLE
						(if (OneOf curRoomNum 201 520 1041)
							(ScriptID curRoomNum 12)
						else
							(ScriptID tlkDroole DROOLE)
						)
					)
					(DUMB
						(ScriptID 135 2)
					)
					(CRUMPELLA
						(if (== curRoomNum 801)
							(ScriptID 801 1)
						else
							(ScriptID 109 7)
						)
					)
					(SLEP
						(if (== curRoomNum 801)
							(ScriptID 801 2)
						else
							(ScriptID 109 7)
						)
					)
					(FLO
						(if
						(OneOf curRoomNum 201 760 520 620 640 660 1040 1041)
							(if (!= (curRoom curPic?) 110)
								(ScriptID curRoomNum 13)
							else
								(ScriptID tlkFlo FLO)
							)
						else
							(ScriptID tlkFlo FLO)
						)
					)
					(QUIRK
						(if (OneOf curRoomNum 104 119 127 201 206 520 850)
							(if (== curRoomNum 201)
								(ScriptID 209 14)
							else
								(ScriptID curRoomNum 14)
							)
						else
							(ScriptID tlkQuirk QUIRK)
						)
					)
					(3
						(ScriptID curRoomNum 14)
					)
					(QUIRK_119
						(if (== curRoomNum 119)
							(ScriptID 119 2)
						else
							(ScriptID curRoomNum 14)
						)
					)
					(ROGER_1040
						(ScriptID 1040 5)
					)
					(ROGER
						(if
							(OneOf curRoomNum
								104 119 125 135 165 201 228 230
								240 520 530 730 750 760 1041 666
								850 660 440 450 620 640
							)
							(if
								(and
									(OneOf curRoomNum 730 740 760 790)
									(not (Btst fEgoIsFly))
								)
								(ScriptID tlkRoger 19)
							else
								(ScriptID curRoomNum 15)
							)
						else
							(switch curRoomNum
								(510
									(ScriptID 510 1)
								)
								(else
									(ScriptID tlkRoger 19)
								)
							)
						)
					)
					(GENERIC_119
						(ScriptID 119 1)
					)
					(SMART
						(ScriptID 135 1)
					)
					(WD40
						(if (OneOf curRoomNum 201 999)
							(if (== curRoomNum 201)
								(if (== wd40State WD40repaired)
									(ScriptID tlkWD40 WD40)
								else
									(ScriptID 209 16)
								)
							else
								(ScriptID curRoomNum 16)
							)
						else
							(ScriptID tlkWD40 WD40)
						)
					)
					(NELO
						(if (OneOf curRoomNum 520)
							(ScriptID curRoomNum 17)
						else
							(ScriptID 1891 38)
						)
					)
					(PUKOID
						(if (OneOf curRoomNum 450 440)
							(ScriptID curRoomNum 19)
						else
							(ScriptID 1895 46)
						)
					)
					(STARCON_GUARD1
						(ScriptID 115 2)
					)
					(STARCON_GUARD2
						(ScriptID 117 2)
					)
					(SBAR_GUARD1
						(ScriptID 510 1)
					)
					(SBAR_GUARD2
						(ScriptID 510 1)
					)
					(SBARTENDER
						(ScriptID 520 1)
					)
					(SBAR_PATRON1
						(ScriptID 500 10)
					)
					(SBAR_PATRON2
						(ScriptID 500 10)
					)
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)
