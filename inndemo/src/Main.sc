;;; Sierra Script 1.0 - (do not remove this comment)
;**************************************************************
;***
;***	DEMO.SC--
;***
;***	  This is a one disk demo that contains all the 
;***	  major apps available on INN.
;***
;***													 by
;***													  Robert W. Lindsley
;***														  12/5/93
;**************************************************************

(script#	DEMO)
(include game.sh)
(use DemoWin)
(use Print)
(use Ego)
(use Sound)
(use Game)
(use User)
(use System)

(public
	Demo 0
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
	[sysLogPath 20]
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
	msgType =  1
	messager
	prints
	walkHandler
	textSpeed =  2
	[altPolyList 4]
	lastSysGlobal
	myTextColor
	myBackColor
	isVGA
	debugging
	statusLine
	soundFx
	theMusic
	globalSound
)
(class Demo kindof Game
;;;	(methods
;;;		handsOn
;;;		handsOff
;;;	)

	
	(method (handleEvent evt &tmp mD)
		(= mD FALSE)
  		(curRoom handleEvent:	evt) ;see if the forward or b\w buttons want it
		(if (or (== (evt type?) keyDown) (== (evt type?) mouseDown))
			(if modelessDialog
				(= mD TRUE)													
			)
			(if modelessDialog (modelessDialog dispose:)) ;Cls
			(if (or
					(== (evt type?) mouseDown) 
					(== (evt message?) SPACEBAR) 
					(== (evt message?) ENTER)
				)
				(if (and mD (curRoom script?))
					((curRoom script?) seconds:	0)
					((curRoom script?) cycles:	1)
					(evt claimed:	TRUE)
				)
			else
				(SetCursor TRUE)
				(= quit
					(Print
						addText:		{Would you like to quit the INN demo?},
						addButton:	1 {_Yes_} 80 20,
						addButton:	0 {_No_} 120 20,
						init:
					)
				)
				(SetCursor FALSE)
				(evt claimed:	TRUE)
			)
		)
	)

	(method (setCursor cursorObj tOrF theX theY &tmp oldCurObj moveToX moveToY)
		;this is the same as the original setCursor method
		;but, the cursors are objects now.
		;For reference, see cursor.sc

		(= oldCurObj theCursor)
		(= theCursor cursorObj)
		(if (> argc 2)
			(= moveToX (if (< theX 0) 0 else theX))  ;this will fix off-screen cursor problem
			(= moveToY (if (< theY 0) 0 else theY))
			(SetCursor moveToX moveToY)
		)
		(if (IsObject cursorObj)
			(if argc
	  			((= theCursor cursorObj) init:)
			)
			(cursorObj init:)
		else
  			(SetCursor cursorObj 0 0)
		)
		(return oldCurObj)
	)
	(method (handsOff)
		(user canInput: TRUE, canControl: FALSE)
		(self setCursor: waitCursor TRUE)
	)
	(method (handsOn)
		(user canInput: TRUE, canControl: TRUE)
		(self setCursor: normalCursor TRUE)
	)
	(method (init)
		(= user User)
		(= ego demoEgo)
		(if (and (FileIO fileExists {selector}) (FileIO fileExists {c}))
			(= debugging TRUE)
		)
		(= userFont 1)
		(user alterEgo:	ego)
		(super init:)
		(= theMusic musicSound)
		(= globalSound theGlobalSound)
		(= soundFx soundEffects)
		(theMusic
			owner:	self,
			flags:	mNOPAUSE,
			init:
		)
		(globalSound
			owner:	self,
			flags:	mNOPAUSE,
			init:
		)
		(soundFx
			owner:	self,
			flags:	mNOPAUSE,
			init:
		)

		(= systemWindow DemoWin) 
		(= inventory 0)
		(= waitCursor theWaitCursor)
		(keyDownHandler addToFront:	self)
		(mouseDownHandler addToFront:	self)
		(DisposeScript INVENT)
		(self 
			setCursor:	(theWaitCursor posn: 300 180, yourself:),
			handsOff:,
			newRoom:		TITLE
		)
	)
)


(instance demoEgo of Ego
	(properties
		view		0
		loop		0
	)
)

(instance theWaitCursor of Cursor
	(properties
		view				996
		loop				0
		cel				0
	)
)

(instance theGlobalSound of Sound)
(instance musicSound of Sound)
(instance soundEffects of Sound)
