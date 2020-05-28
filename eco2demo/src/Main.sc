;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use eco2Ego)
(use Eco2Talker)
(use Print)
(use Messager)
(use Talker)
(use PMouse)
(use IconBar)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Timer)
(use Grooper)
(use Window)
(use Flags)
(use Sound)
(use Game)
(use System)

(public
	Eco2 0
	IsObjectOnControl 1
	Btst 2
	Bset 3
	Bclr 4
	Face 5
	VerbFail 6
	proc0_7 7
	stopGroop 8
	eco2Cursor 9
	StartARoom 10
	EcorderSet 11
	EcorderTst 12
)

(local
	ego
	theGame
	curRoom
	speed
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
	transferRoom =  100
	dongle =  1234
	global102
	theMusic
	soundFx
	numVoices
	global106
	global107
	global108
	gTheNewDButton_2Value
	gameFlags
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
	ecorderFlags
	global125
	global126
	global127
	global128
	global129
	global130
	buildDate
	usPhone
	intPhone
	debugging
	gEH
	egoSpeed
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
	flagObject
	global151
	global152
	global153
	global154
)
(procedure (IsObjectOnControl obj ctrl)
	(return
		(if (& (obj onControl: origin) ctrl)
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

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY i)
	(= i 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3) (= i both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= i whoToCue))
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(cond 
		((> (Abs (- ang1To2 (ego heading?))) 23)
			(actor1
				setHeading: ang1To2 (if (IsObject i) i else 0)
			)
		)
		((IsObject i)
			(i cue:)
		)
	)
)

(procedure (VerbFail obj &tmp [str 60])
	(Printf
		{Clicking on the %s produces no response.}
		(obj name?)
	)
)

(procedure (proc0_7 roomNum)
	;this seems to be a duplicate of StartARoom
	(if
		(and
			(u> (MemoryInfo FreeHeap) (+ 10 (MemoryInfo LargestPtr)))
			(Print
				addText: 14 0 0 5 0 0 SYSTEM
				addButton: 0 14 0 0 6 0 16 SYSTEM
				addButton: 1 14 0 0 7 0 50 SYSTEM
				init:
			)
		)
		(SetDebug)
	)
	(if debugging
		((ScriptID DEBUG) init:)
	)
	;(super startRoom: roomNum)	;can't use this in a procedure
	(if
		(and
			(ego cycler?)
			(not (ego looper?))
			((ego cycler?) isKindOf: StopWalk)
		)
		(ego setLoop: stopGroop)
	)
)

(procedure (StartARoom roomNum)
	(if
		(and
			(u> (MemoryInfo FreeHeap) (+ 10 (MemoryInfo LargestPtr)))
			(Print
				addText: 14 0 0 5 0 0 SYSTEM
				addButton: 0 14 0 0 6 0 16 SYSTEM
				addButton: 1 14 0 0 7 0 50 SYSTEM
				init:
			)
		)
		(SetDebug)
	)
	(if debugging
		((ScriptID DEBUG) init:)
	)
	;(super startRoom: roomNum)	;can't use this in a procedure
	(if
		(and
			(ego cycler?)
			(not (ego looper?))
			((ego cycler?) isKindOf: StopWalk)
		)
		(ego setLoop: stopGroop)
	)
)

(procedure (EcorderSet flagEnum)
	(= [ecorderFlags (/ flagEnum 16)]
		(|
			[ecorderFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (EcorderTst flagEnum)
	(return
		(&
			[ecorderFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(instance gameSound1 of Sound)

(instance gameSound2 of Sound)

(class Eco2Actions of Code
	(properties
		name "Actions"
	)
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance stopGroop of GradualLooper)

(instance eco2Cursor of Cursor
	(properties
		view 960
	)
)

(instance KH of EventHandler)

(instance MH of EventHandler)

(instance DH of EventHandler)

(instance WH of EventHandler)

(instance EH of EventHandler)

(class Eco2 of Game
	
	(method (init &tmp [temp0 7])
		Flags
		Print
		StopWalk
		Polygon
		PolyPath
		Timer
		Room
		Eco2Ego
		Eco2Talker
		IconBar
		(ScriptID SIGHT)
		Narrator
		(super init:)
		(= normalCursor eco2Cursor)
		(= systemWindow eco2Win)
		(= version {x.yyy.zzz})
		(= buildDate {mm/dd/yy})
		(= usPhone {991-999-9999})
		(= intPhone {992-999-9999})
		((ScriptID ECO2INIT 0) init:)
		(DisposeScript ECO2INIT)
		(= ftrInitializer eco2FtrInit)
		(= theCursor eco2Cursor)
		(= messager eco2Messager)
		((= keyDownHandler KH) add:)
		((= mouseDownHandler MH) add:)
		((= directionHandler DH) add:)
		((= walkHandler WH) add:)
		((= gEH EH) add:)
		(= pMouse PseudoMouse)
		((= theMusic gameSound1)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= soundFx gameSound2)
			owner: self
			flags: mNOPAUSE
			init:
		)
		(user
			alterEgo: (= ego Eco2Ego)
			canControl: FALSE
			canInput: FALSE
		)
		(theGame setCursor: INVIS_CURSOR)
		(self newRoom: SPEED)
	)
	
	(method (replay)
		(super replay:)
	)
	
	(method (newRoom n &tmp [temp0 5])
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(if (and (IsObject fastCast) (fastCast elements?))
			(fastCast eachElementDo: #dispose 1)
		)
		(narrator
			x: -1
			y: -1
			caller: 0
			modNum: -1
			disposeWhenDone: TRUE
			ticks: 0
			talkWidth: 0
			keepWindow: FALSE
			modeless: FALSE
			font: userFont
			color: 57
			back: 68
			cueVal: 0
			initialized: FALSE
			showTitle: FALSE
			curVolume: 0
			saveCursor: 0
		)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp temp0 temp1 i temp3)
		((ScriptID DISPOSE) doit: roomNum)
		(= i 0)
		(while (< i (timers size?))
			(timers delete: (= temp0 (timers at: 0)))
			(timers add: temp0)
			(++ i)
		)
		(if
			(and
				debugging
				(u> (MemoryInfo FreeHeap) (+ 10 (MemoryInfo LargestPtr)))
				(Print
					addText: 14 0 0 5 0 0 SYSTEM
					addButton: 0 14 0 0 6 0 16 SYSTEM
					addButton: 1 14 0 0 7 0 50 SYSTEM
					init:
				)
			)
			(SetDebug)
		)
		(if debugging
			((ScriptID DEBUG) init:)
		)
		(super startRoom: roomNum)
		(if
			(and
				(ego cycler?)
				(not (ego looper?))
				((ego cycler?) isKindOf: StopWalk)
			)
			(ego setLoop: stopGroop)
		)
	)
	
	(method (restart)
		(curRoom style: IRISIN drawPic: 888)
		(cast eachElementDo: #hide)
		(super restart:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(if (== (event type?) keyDown)
			(switch (event message?)
				(`^q
					(theGame quitGame:)
					(event claimed: TRUE)
				)
			)
		)
		(return
			(super handleEvent: event)
		)
	)
	
	(method (setCursor form showIt theX theY &tmp oldCur)
		(= oldCur form)
		(= global153 0)
		(cond 
			((not (Btst 1))
				(if (IsObject form)
					(theCursor init:)
				else
					((= theCursor eco2Cursor)
						view: 960
						loop: 10
						cel:
						(switch form
							(999 5)
							(998 6)
							(997 8)
							(996 14)
							(else  form)
						)
						init:
					)
				)
			)
			((and (> argc 1) (not showIt))
				((= theCursor eco2Cursor)
					view: 960
					loop: 10
					cel: 14
					init:
				)
			)
		)
		(if (> argc 2)
			(theCursor posn: theX theY)
		)
		(return oldCur)
	)
	
	(method (pragmaFail)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(if
			(and
				(user canInput:)
				(not (OneOf ((user curEvent?) message?) 2 3))
			)
			(messager say: 14 ((user curEvent?) message?) 0 0 0 SYSTEM)
		)
	)
)

(instance eco2DoVerbCode of Code
	(properties)
	
	(method (doit theVerb)
		(if (and (user canInput:) (!= theVerb 2))
			(messager say: 14 theVerb 0 0 0 999)
		)
	)
)

(instance eco2FtrInit of Code
	(properties)
	
	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 90)
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

(instance eco2Messager of Messager
	
	(method (sayNext)
		(Print mode: teJustCenter)
		(super sayNext: &rest)
	)
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					(NARRATOR narrator)
					(PA_BIRD (ScriptID tlkPaBird))
					(MA_BIRD (ScriptID tlkMaBird))
					(ADAM (ScriptID tlkEgo))
					(else  narrator)
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(instance checkEcorderIcon of Code
	
	(method (doit param1 &tmp eco2CursorLoop eco2CursorCel)
		(= eco2CursorLoop (eco2Cursor loop?))
		(= eco2CursorCel (eco2Cursor cel?))
		(cond 
			((!= eco2CursorLoop 8))
			(
				(and
					(not global153)
					(== eco2CursorCel 8)
					(param1 onMe: mouseX (- mouseY 10))
				)
				(= global153 param1)
				(eco2Cursor cel: 10 init:)
			)
			(
				(and
					(== global153 param1)
					(== eco2CursorCel 10)
					(not (param1 onMe: mouseX (- mouseY 10)))
				)
				(= global153 0)
				(eco2Cursor cel: 8 init:)
			)
		)
	)
)

(instance writeEcorderData of Code
	
	(method (doit param1 param2 flagEnum)
		(return
			(if
			(and (== param2 55) (not (EcorderTst flagEnum)))
				(EcorderSet flagEnum)
				(messager say: 14 0 14 0 0 999)
				(eco2Cursor cel: 8 init:)
				(= global153 0)
				(return 1)
			else
				(return 0)
			)
		)
	)
)

(instance eco2Win of SysWindow
	(properties
		color 57
		back 68
	)
)
