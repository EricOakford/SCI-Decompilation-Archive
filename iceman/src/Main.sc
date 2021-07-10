;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use Grooper)
(use Mouser)
(use Sound)
(use Save)
(use Game)
(use Invent)
(use User)
(use Menu)
(use System)

(public
	iceMan 0
	HandsOff 1
	HandsOn 2
	cls 3
	HaveMem 4
	SoundFX 5
	proc0_6 6
	proc0_7 7
	proc0_8 8
	proc0_9 9
	proc0_10 10
	proc0_11 11
	proc0_12 12
	proc0_13 13
	proc0_14 14
	proc0_15 15
	proc0_16 16
	proc0_17 17
	proc0_18 18
	proc0_19 19
	proc0_20 20
	proc0_21 21
	proc0_22 22
	proc0_23 23
	proc0_24 24
	proc0_25 25
	proc0_26 26
	proc0_27 27
	proc0_28 28
	proc0_29 29
	Ok 30
	ItIs 31
	YouAre 32
	NotNow 33
	NotClose 34
	AlreadyTook 35
	SeeNothing 36
	CantDo 37
	DontHave 38
	DontNeedTo 39
	CantSee 40
	BadIdea 41
	CantAfterAll 42
)

(local
	ego									;pointer to ego
	theGame								;ID of the Game instance
	curRoom								;ID of current room
	speed =  6							;number of ticks between animations
	quit								;when TRUE, quit game
	cast								;collection of actors
	regions								;set of current regions
	timers								;list of timers in the game
	sounds								;set of sounds being played
	inventory							;set of inventory items in game
	addToPics							;list of views added to the picture
	curRoomNum							;current room number
	prevRoomNum							;previous room number
	newRoomNum							;number of room to change to
	debugOn								;generic debug flag -- set from debug menu
	score								;the player's current score
	possibleScore						;highest possible score
	showStyle	=		IRISOUT			;style of picture showing
	aniInterval							;# of ticks it took to do the last animation cycle
	theCursor							;the number of the current cursor
	normalCursor =		ARROW_CURSOR	;number of normal cursor form
	waitCursor	 =		HAND_CURSOR		;cursor number of "wait" cursor
	userFont	 =		USERFONT		;font to use for Print
	smallFont	 =		4				;small font for save/restore, etc.
	lastEvent							;the last event (used by save/restore game)
	modelessDialog						;the modeless Dialog known to User and Intrface
	bigFont		=		USERFONT		;large font
	volume		=		12				;sound volume
	version		=		{x.yyy.zzz}		;pointer to 'incver' version string			
	locales								;set of current locales
	[curSaveDir 20]						;address of current save drive/directory string
	aniThreshold	=	10
	perspective							;player's viewing angle:
										;	 degrees away from vertical along y axis
	features							;locations that may respond to events
	sortedFeatures          			;above+cast sorted by "visibility" to ego
	useSortedFeatures					;enable cast & feature sorting?
	demoScripts							;add to curRoomNum to find room demo script
	egoBlindSpot						;used by sortCopy to exclude
										;actors behind ego within angle 
										;from straight behind. 
										;Default zero is no blind spot
	overlays	=		-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	demoDialogTime	=	3				;how long Prints stay up in demo mode
	currentPalette
	modelessPort
	;globals 63-99 are unused
		global63
		global64
		global65
		global66
		global67
		global68
		global69
		global70
		global71
		global72
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
	;globals 100 and above are for game use
	global100
	gamePhase
	theQueuedSound
	numFlares
	keyDownHandler
	mouseDownHandler
	directionHandler
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
	global118
	global119
	global120
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
	howFast
	global133
	gLastM
	gLastH
	global136
	global137
	globalSound
	numVoices
	numColors
	global141 =  1
	global142
	earRingState
	startingRoom
)
(procedure (HandsOff)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(theGame setCursor: waitCursor TRUE)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(ego setMotion: 0)
	(theGame setCursor: normalCursor (HaveMouse))
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo LargestPtr) howMuch))
)

(procedure (SoundFX soundNum)
	(return
		(if (or (< numVoices 4) (== numVoices 12))
			(+ soundNum 200)
		else
			soundNum
		)
	)
)

(procedure (proc0_6)
)

(procedure (proc0_7)
)

(procedure (proc0_8)
)

(procedure (proc0_9)
)

(procedure (proc0_10)
)

(procedure (proc0_11)
)

(procedure (proc0_12)
)

(procedure (proc0_13)
)

(procedure (proc0_14)
)

(procedure (proc0_15)
)

(procedure (proc0_16)
)

(procedure (proc0_17)
)

(procedure (proc0_18)
)

(procedure (proc0_19)
)

(procedure (proc0_20)
)

(procedure (proc0_21)
)

(procedure (proc0_22)
)

(procedure (proc0_23)
)

(procedure (proc0_24)
)

(procedure (proc0_25)
)

(procedure (proc0_26)
)

(procedure (proc0_27)
)

(procedure (proc0_28)
)

(procedure (proc0_29)
)

(procedure (Ok)
	(Print 0 12 #time 2)
)

(procedure (ItIs)
	(Print 0 13)
)

(procedure (YouAre)
	(Print 0 14)
)

(procedure (NotNow)
	(Print 0 15)
)

(procedure (NotClose)
	(Print 0 16)
)

(procedure (AlreadyTook)
	(Print 0 17)
)

(procedure (SeeNothing)
	(Print 0 18)
)

(procedure (CantDo)
	(Print 0 19)
)

(procedure (DontHave)
	(Print 0 20)
)

(procedure (DontNeedTo)
	(Print 0 21)
)

(procedure (CantSee)
	(Print 0 22)
)

(procedure (BadIdea)
	(Print 0 23)
)

(procedure (CantAfterAll)
	(Print 0 24)
)

(instance iceKeyDownHandler of EventHandler)

(instance iceDirectionHandler of EventHandler)

(instance iceMouseDownHandler of MouseDownHandler)

(instance iceMouseSays of Code
	
	(method (doit what event)
		(Parse {look} event)
	)
)

(instance iceMan of Game
	
	(method (init &tmp temp0)
		(= systemWindow iceWindow)
		(super init:)
		(= numColors (Graph GDetect))
		(= numVoices (DoSound NumVoices))
		(= version {x.yyy})
		((= globalSound iceGlobalSound) init: owner: self)
		((= keyDownHandler iceKeyDownHandler) add:)
		((= mouseDownHandler iceMouseDownHandler)
			add: cast features
			shiftParser: iceMouseSays
		)
		((= directionHandler iceDirectionHandler) add:)
		(User alterEgo: (= ego egoObj))
		(= possibleScore 300)
		(TheMenuBar init: draw: hide:)
		(StatusLine code: statusCode disable:)
		(inventory add: Envelope Microfilm ID_Card)
		(self setCursor: theCursor TRUE 304 172)
		(= startingRoom (if (GameIsRestarting) 2 else 100))
		(self newRoom: 99)
	)
	
	(method (replay)
		(SetMenu soundI
			p_text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(super replay:)
	)
	
	(method (newRoom)
		(super newRoom: &rest)
		(if (or (User canInput?) (User controls?))
			(self setCursor: normalCursor (HaveMouse))
		else
			(self setCursor: waitCursor TRUE)
		)
	)
	
	(method (startRoom roomNum)
		(ego observeControl: cWHITE)
		((ScriptID 818) doit: roomNum)
		(if debugOn (SetDebug))
		((ScriptID 819) doit: roomNum)
		(super startRoom: roomNum &rest)
		(if
			(and
				(cast contains: ego)
				(not (ego looper?))
				(not (OneOf roomNum 8 15 16 27 39 40))
			)
			(ego setLoop: GradualLooper)
		)
		(if (and (cast contains: ego) (not (ego view?)))
			(ego view: 200)
		)
	)
	
	(method (handleEvent event &tmp i [temp1 3])
		(switch (event type?)
			(keyDown
				(keyDownHandler handleEvent: event)
			)
			(mouseDown
				(mouseDownHandler handleEvent: event)
			)
			(direction
				(directionHandler handleEvent: event)
			)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'quit[<!*][/!*][/!*]')
						(= quit TRUE)
					)
					((Said 'holler')
						(Print 0 0)
					)
					((Said 'break')
						(DontNeedTo)
					)
					((Said 'eat>')
						(cond 
							((Said '/*')
								(BadIdea)
							)
							((Said '[/!*]')
								(Print 0 1)
							)
						)
					)
					((Said 'ask/[/!*]')
						(Print 0 2)
					)
					((Said 'examine>')
						(if (Said '[/!*]')
							(Print 0 3)
						else
							(event claimed: TRUE)
							(SeeNothing)
						)
					)
					((Said 'look<up,down')
						(SeeNothing)
					)
					((Said 'walk')
						(Print 0 4)
					)
					((Said 'address')
						(Print 0 5)
					)
					((Said 'whistle')
						(Print 0 6)
					)
					((Said 'thank[/ya]')
						(Print 0 7)
					)
					(
						(and
							(Said '/*>')
							(= i (inventory saidMe:))
						)
						(event claimed: FALSE)
						(cond 
							((i ownedBy: ego)
								(cond 
									((Said 'look[<at]')
										(i showSelf:)
									)
									((Said '*')
										(CantDo)
									)
								)
							)
							((i ownedBy: curRoomNum)
								(if (Said 'get')
									(CantDo)
								else
									(DontHave)
								)
							)
							(else
								(CantSee)
							)
						)
						(event claimed: TRUE)
					)
					((Said 'get,give')
						(CantDo)
					)
					((Said 'look[<at]')
						(SeeNothing)
					)
				)
				(if (and (not (event claimed?)) (>= argc 2))
					(CantAfterAll)
				)
			)
		)
	)
)

(instance statusCode of Code

	(method (doit strg)
		(Format strg 0 8 score possibleScore)
	)
)

(instance egoObj of Ego
	(properties
		name {ego}
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event &rest))
			((Said 'look//*<for')
				(Print 0 9)
			)
		)
	)
)

(instance Envelope of InvItem
	(properties
		said '/envelope'
		view 321
		value 1
	)
)

(instance Microfilm of InvItem
	(properties
		said '/film'
		view 313
		loop 1
		value 5
	)
)

(instance ID_Card of InvItem
	(properties
		said '/id,card[<id]'
		view 300
		value 1
		name "ID Card"
	)
	
	(method (showSelf)
		(if (== view 372)
			(Print 0 10
				#icon view loop cel
			)
		else
			(Print 0 11
				#icon view loop cel
			)
		)
	)
)

(instance iceWindow of SysWindow
	(properties
		top 20
	)
)

(instance iceGlobalSound of Sound
	(properties
		number 1
	)
)
