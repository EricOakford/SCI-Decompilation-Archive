;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Print)
(use PMouse)
(use Window)
(use Ego)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Card 0
)

(local
	ego								  	;pointer to ego
	theGame							  	;ID of the Game instance
	curRoom							  	;ID of current room
	unused_1		
	quit							  	;when TRUE, quit game
	cast							  	;collection of actors
	regions							  	;set of current regions
	timers							  	;list of timers in the game
	sounds							  	;set of sounds being played
	inventory						  	;set of inventory items in game
	addToPics						  	;list of views added to the picture
	curRoomNum						  	;current room number
	prevRoomNum						  	;previous room number
	newRoomNum						  	;number of room to change to
	debugOn							  	;generic debug flag -- set from debug menu
	score							  	;the player's current score
	possibleScore					  	;highest possible score
	textCode							;code that handles interactive text
	cuees							  	;list of who-to-cues for next cycle
	theCursor						  	;the number of the current cursor
	normalCursor	=	ARROW_CURSOR	;number of normal cursor form
	waitCursor		=	HAND_CURSOR 	;cursor number of "wait" cursor
	userFont		=	USERFONT	  	;font to use for Print
	smallFont		=	4 			  	;small font for save/restore, etc.
	lastEvent						  	;the last event (used by save/restore game)
	modelessDialog					  	;the modeless Dialog known to User and Intrface
	bigFont			=	USERFONT	  	;large font
	version			=	0			  	;pointer to 'incver' version string
										;	WARNING!  Must be set in room 0
										;	(usually to {x.yyy    } or {x.yyy.zzz})
	unused_3
	curSaveDir							;address of current save drive/directory string
	unused_4
	perspective							;player's viewing angle: degrees away
										;	from vertical along y axis
	features							;locations that may respond to events
	unused_5
	useSortedFeatures	=	FALSE		;enable cast & feature sorting?
	unused_6
	overlays			=	-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	unused_7
	unused_8
	modelessPort
	[sysLogPath	20]						;-used for system standard logfile path	
	endSysLogPath						;/		(uses 20 globals)
	gameControls						;pointer to instance of game controls
	ftrInitializer						;pointer to code that gets called from
										;	a feature's init
	doVerbCode							;pointer to code that gets invoked if
										;	no feature claims a user event
	approachCode						;pointer to code that translates verbs
										;	into bits
	useObstacles	=	TRUE			;will Ego use PolyPath or not?
	unused_9
	theIconBar							;points to TheIconBar or Null	
	mouseX								;-last known mouse position
	mouseY								;/
	keyDownHandler						;-our EventHandlers, get called by game
	mouseDownHandler					;/
	directionHandler					;/
	speechHandler						;a special handler for speech events
	lastVolume
	pMouse			=	NULL			;pointer to a Pseudo-Mouse, or NULL
	theDoits		=	NULL			;list of objects to get doits each cycle
	eatMice			=	60				;how many ticks before we can mouse
	user			=	NULL			;pointer to specific applications User
	syncBias							;-globals used by sync.sc
	theSync								;/		(will be removed shortly)
	unused_10
	fastCast							;list of talkers on screen
	inputFont		=	SYSFONT			;font used for user type-in
	tickOffset							;used to adjust gameTime after restore
	howFast								;measurment of how fast a machine is
	gameTime							;ticks since game start
	narrator							;pointer to narrator (normally Narrator)
	msgType			=	TEXT_MSG		;type of messages used
	messager							;pointer to messager (normally Messager)
	prints								;list of Print's on screen
	walkHandler							;list of objects to get walkEvents
	textSpeed		=	2				;time text remains on screen
	altPolyList							;list of alternate obstacles
	;globals 96-99 are unused
	global96
	global97
	global98
	lastSysGlobal
	;globals 100 and above are for game use
	global100	;unused
	gameCode =  1234
	global102	;unused
	theMusic1	;pointer for music
	theMusic2	;pointer for sound effects
	debugging	;debug mode enabled
	global106	;unused
	global107	;unused
	snowflakeSpeed
	;This array is used to draw the text at the end
	lettersView =  [
		310 0 46 60
		310 1 54 79
		310 2 54 79
		310 3 57 78
		310 4 75 71
		310 5 95 62
		310 6 117 51
		310 7 145 45
		310 8 143 46
		310 9 53 121
		310 10 65 91
		310 11 77 115
		310 12 81 116
		310 13 95 100
		310 14 111 97
		310 15 120 90
		311 0 137 85
		311 1 148 88
		311 2 143 117
		311 3 160 78
		311 4 195 56
		311 5 111 99
		]
	lettersIndex
	lettersX =  5
	lettersY =  -14
	;end of letters globals
	cardDone	;once we're at the end, set up everything again
	;201-250 are unused
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
)
(procedure (SetUpPic thePic &tmp i)
	(if (not cardDone)
		(for ((= i 99)) (>= i 0) ((-= i 2))
			(Palette PALIntensity 0 255 i)
			(Game doit:)
		)
	)
	(= cardDone FALSE)
	(DrawPic thePic PLAIN TRUE)
	(Animate)
)

(procedure (SetUpPalette &tmp i)
	(for ((= i 0)) (<= i 100) ((+= i 2))
		(Palette PALIntensity 0 255 i)
		(Game doit:)
	)
)

(class RandTime of Forward
	(properties
		cycleCnt 0
		completed 0
		;EO: These were incorrect due to lack of a selector table.
		setOnMeCheck 10
		scaleDir 20
	)
	
	(method (init theObj theSetOnMeCheck theScaleDir)
		(if argc
			(= client theObj)
		)
		(if (> argc 1)
			(= setOnMeCheck theSetOnMeCheck)
			(= scaleDir theScaleDir)
		)
		(= cycleCnt gameTime)
		(= completed FALSE)
	)
	
	(method (nextCel)
		(return
			(if (< (Abs (- gameTime cycleCnt)) (client cycleSpeed?))
				(client cel?)
			else
				(= cycleCnt gameTime)
				(client cycleSpeed: (Random setOnMeCheck scaleDir))
				(+ (client cel?) cycleDir)
			)
		)
	)
)

(instance cardKDHandler of EventHandler)

(instance cardMDHandler of EventHandler)

(instance cardDirHandler of EventHandler)

(instance Card of Game
	(method (init &tmp [temp0 4])
		(super init:)
		((= theMusic1 longSong)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= theMusic2 longSong2)
			owner: self
			flags: mNOPAUSE
			init:
		)
		(User
			alterEgo: Ego
			canControl: FALSE
			canInput: FALSE
		)
		(= debugging FALSE)
		(= systemWindow SysWindow)
		(= pMouse PseudoMouse)
		(= msgType TEXT_MSG)
		((= keyDownHandler cardKDHandler) add:)
		((= mouseDownHandler cardMDHandler) add:)
		((= directionHandler cardDirHandler) add:)
		(SetCursor 0)
		(prop1 init: hide:)
		(prop2 init: hide:)
		(prop3 init: hide:)
		(prop4 init: hide:)
		(prop5 init: hide:)
		(prop6 init: hide:)
		(prop7 init: hide:)
		(prop8 init: hide:)
		(letters init: hide:)
		(self setScript: cardScript 0 100)
	)
	
	(method (play)
		(= theGame self)
		(self init:)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(if
			(and
				(!= (- (MemoryInfo FreeHeap) 2) (MemoryInfo LargestPtr))
				(Prints {Memory fragmented.})
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event
				&tmp savePort node underbits i t l r b numCols numRows evt obj [str 75])
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(ESC
							(= quit TRUE)
						)
						(`^q
							(= quit TRUE)
						)
						(`^c
							(= quit TRUE)
						)
						(`^x
							(= quit TRUE)
						)
						(`^z
							(= quit TRUE)
						)
						(`@q
							(= quit TRUE)
						)
						(`@c
							(= quit TRUE)
						)
						(`@x
							(= quit TRUE)
						)
						(`@z
							(= quit TRUE)
						)
						(else 
							(if debugging
								(switch (event message?)
									(`@y
										(= savePort (GetPort))
										(SetPort 0)
										(= numCols 5)
										(= numRows 16)
										(= t 15)
										(= l 80)
										(= b (+ t (* 34 numCols)))
										(= r (+ l (* 10 numRows)))
										(= underbits (Graph GSaveBits t l b r 1))
										(Graph GFillRect t l b r 1 255)
										(for ((= i 0)) (< i 256) ((++ i))
											(Graph GFillRect
												(+ t numCols (* numCols (/ i 8)))
												(+ l numRows (* 16 (mod i 8)))
												(+ t numCols numCols (* numCols (/ i 8)))
												(+ l numRows numRows (* numRows (mod i 8)))
												VMAP
												i
											)
										)
										(Graph GShowBits t l b r VMAP)
										(repeat
											(if
												(or
													(== ((= evt (Event new:)) type?) mouseDown)
													(== (evt type?) keyDown)
												)
												(break)
											)
											(evt dispose:)
										)
										(evt dispose:)
										(Graph GRestoreBits underbits)
										(Graph GShowBits t l b r VMAP)
										(SetPort savePort)
									)
									(`@a
										(= node (cast first:))
										(while node
											(= obj (NodeValue node))
											(Format
												@str
												{name: %s\n
												class: %s\n
												view: %d\n
												loop: %d\n
												cel: %d\n
												posn: %d %d %d\n
												heading: %d\n
												pri: %d\n
												signal: $%x\n
												illBits: $%x\n}
												(obj name?)
												((obj -super-?) name?)
												(obj view?)
												(obj loop?)
												(obj cel?)
												(obj x?)
												(obj y?)
												(obj z?)
												(obj heading?)
												(obj priority?)
												(obj signal?)
												(if
													(or
														(== (obj -super-?) Actor)
														(== (obj -super-?) Ego)
													)
													(obj illegalBits?)
												else
													-1
												)
											)
											(Print
												addText: @str
												addIcon: (obj view?) (obj loop?) (obj cel?) 120 0
												init:
											)
											(= node (cast next: node))
										)
									)
									(else
										(return TRUE)
									)
								)
							else
								(return TRUE)
							)
						)
					)
				)
			)
		)
	)
)

(instance longSong of Sound)

(instance longSong2 of Sound)

(instance cardScript of Script
	(method (doit)
		(if (< state 4)
			(Palette PALCycle 96 223 1)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState &tmp i)
		(switch (= state newState)
			(0
				(SetUpPic pHalfDome)
				(Load RES_VIEW pHalfDome)
				(theMusic1
					number: pHalfDome
					setLoop: 1
					play: self
				)
				(SetUpPalette)
			)
			(1 0)
			(2
				(prop1
					view: pHalfDome
					setLoop: 0
					setCel: 0
					x: 180
					y: 61
					show:
					cycleSpeed: 8
					setCycle: EndLoop
				)
			)
			(3 0)
			(4
				(SetUpPic pForest)
				(= snowflakeSpeed 0)
				(theMusic1
					number: register
					setLoop: 1
					play: self
					hold: 1
				)
				(prop1
					view: vMoon
					setLoop: 0
					setCel: 0
					x: 145
					y: 27
					cycleSpeed: 16
					show:
				)
				(SetUpPalette)
				(= seconds 4)
			)
			(5
				(prop2
					view: vReindeer
					setLoop: 0
					setCel: 0
					x: 207
					y: 134
					show:
					cycleSpeed: 9
					setCycle: CycleTo 4 1 self
				)
				(theMusic2 number: sFootstep setLoop: 1 play:)
			)
			(6
				(prop2 setCycle: EndLoop self)
				(prop3
					view: vReindeer
					setLoop: 1
					setCel: 0
					x: 219
					y: 98
					show:
					cycleSpeed: 5
					setCycle: EndLoop
				)
			)
			(7
				(prop1 setCycle: EndLoop self)
				(prop3 setCycle: EndLoop)
				(PalVary PALVARYSTART pForest 8)
			)
			(8
				(PalVary PALVARYNEWTIME 4)
				(= seconds 3)
			)
			(9
				(prop3 setCycle: EndLoop)
				(= seconds 2)
			)
			(10
				(prop3 setCycle: EndLoop)
				(= seconds 3)
			)
			(11
				(SetUpPic pCabinOutside)
				(PalVary PALVARYKILL)
				(prop1 hide:)
				(prop3
					view: pCabinOutside
					loop: 1
					cel: 1
					x: 160
					y: 70
					cycleSpeed: 10
					show:
					setCycle: RandTime
				)
				(prop4
					view: pCabinOutside
					loop: 2
					cel: 0
					x: 197
					y: 65
					cycleSpeed: 11
					show:
					setCycle: RandTime
				)
				(prop5
					view: pCabinOutside
					loop: 3
					cel: 1
					x: 227
					y: 65
					cycleSpeed: 12
					show:
					setCycle: RandTime
				)
				(prop6
					view: pCabinOutside
					loop: 4
					cel: 0
					x: 225
					y: 35
					show:
					setCycle: RandTime
				)
				(prop2
					view: pCabinOutside
					loop: 5
					x: 166
					y: 153
					cycleSpeed: 9
					show:
					setCycle: RandTime
				)
				(prop7
					view: pCabinOutside
					loop: 6
					x: 183
					y: 152
					cycleSpeed: 10
					show:
					setCycle: RandTime
				)
				(prop8
					view: pCabinOutside
					loop: 7
					cel: 1
					x: 224
					y: 154
					cycleSpeed: 11
					show:
					setCycle: RandTime
				)
				(SetUpPalette)
				(= seconds 4)
			)
			(12
				(prop1
					view: vSnowflakes
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					x: 47
					y: 27
					show:
					setCycle: EndLoop self
				)
			)
			(13
				(prop1 setLoop: 1 setCel: 1 x: 42 y: 22 setCycle: Forward)
				(= ticks (* (- 40 snowflakeSpeed) 2))
				(= snowflakeSpeed 11)
			)
			(14
				(prop1 cycleSpeed: snowflakeSpeed)
				(= ticks 70)
			)
			(15
				(if (-- snowflakeSpeed)
					(self changeState: 14)
				else
					(= ticks 100)
				)
			)
			(16
				(SetUpPic pCabinInside)
				(Load RES_VIEW vLogos)
				(Load RES_VIEW vLetters1)
				(Load RES_VIEW vLetters2)
				(theMusic2
					number: sFireCrackling
					setLoop: -1
					play:
				)
				(prop1
					view: pCabinInside
					setLoop: 0
					setCel: 1
					x: 81
					y: 98
					cycleSpeed: 11
					setCycle: RandTime
					show:
				)
				(prop2
					view: pCabinInside
					setLoop: 1
					setCel: 0
					x: 136
					y: 98
					cycleSpeed: 11
					setCycle: RandTime
					show:
				)
				(prop3
					view: pCabinInside
					setLoop: 2
					setCel: 1
					x: 244
					y: 135
					cycleSpeed: 8
					setCycle: RandTime
					show:
				)
				(prop4
					view: pCabinInside
					setLoop: 3
					setCel: 0
					x: 245
					y: 106
					cycleSpeed: 6
					setCycle: RandTime 4 8
					show:
				)
				(prop5
					view: pCabinInside
					setLoop: 4
					setCel: 3
					x: 143
					y: 51
					cycleSpeed: 8
					setCycle: Forward
					show:
				)
				(prop6
					view: vCabinWindow
					setLoop: 0
					setCel: 0
					x: 174
					y: 63
					cycleSpeed: 6
					setCycle: Forward
					show:
				)
				(prop7
					view: pCabinInside
					setLoop: 5
					setCel: 0
					x: 59
					y: 140
					cycleSpeed: 8
					setCycle: RandTime 6 12
					show:
				)
				(prop8 setCycle: 0 hide:)
				(SetUpPalette)
				(= ticks 240)
			)
			(17
				(prop6 setCycle: EndLoop self)
			)
			(18
				(prop6 setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(19
				(prop6 view: vCabinWindow2 setLoop: 0 setCel: 0 setCycle: Forward)
				(= ticks 120)
			)
			(20
				(for ((= i 99)) (> i 64) ((-= i 2))
					(Palette PALIntensity 16 71 i)
					(Palette PALIntensity 80 255 i)
					(Card doit:)
				)
				(= lettersIndex -4)
				(= ticks 60)
			)
			(21
				(= lastTicks 0)
				(+= lettersIndex 4)
				(letters
					view: [lettersView lettersIndex]
					loop: [lettersView (+ lettersIndex 1)]
					cel: 0
					x: (+ [lettersView (+ lettersIndex 2)] lettersX)
					y: (+ [lettersView (+ lettersIndex 3)] lettersY)
					z: 0
					cycleSpeed: 0
					priority: 13
					signal: (| (letters signal?) viewAdded fixPriOn)
					show:
					setCycle: EndLoop self
				)
			)
			(22
				(if (< lettersIndex 84)
					(self changeState: 21)
				else
					(= ticks 60)
				)
			)
			(23
				(prop8
					view: vLogos
					setLoop: 0
					setCel: 0
					x: 142
					y: 121
					setPri: 13
					show:
					cycleSpeed: 4
					setCycle: EndLoop
				)
				(= ticks 180)
			)
			(24
				(prop8 setCycle: BegLoop)
				(= ticks 120)
			)
			(25
				(prop8
					view: vLogos
					setLoop: 1
					setCel: 0
					x: 142
					y: 128
					show:
					setCycle: EndLoop
				)
				(= ticks 180)
			)
			(26
				(prop8 setCycle: BegLoop)
				(= ticks 120)
			)
			(27
				(prop8
					view: vLogos
					setLoop: 2
					setCel: 0
					x: 143
					y: 129
					show:
					setCycle: EndLoop
				)
				(= ticks 180)
			)
			(28
				(theMusic1 release:)
				(prop8 setCycle: BegLoop)
				(= ticks 120)
			)
			(29
				(prop8
					view: vLogos
					setLoop: 3
					setCel: 0
					x: 137
					y: 122
					show:
					setCycle: EndLoop
				)
				(= ticks 180)
			)
			(30
				(prop8 setCycle: BegLoop)
			)
			(31
				(for ((= i 50)) (>= i 0) ((-= i 2))
					(Palette PALIntensity 0 255 i)
					(Game doit:)
				)
				(= cardDone TRUE)
				(prop1 setCycle: 0 hide:)
				(prop2 setCycle: 0 hide:)
				(prop3 setCycle: 0 hide:)
				(prop4 setCycle: 0 hide:)
				(prop5 setCycle: 0 hide:)
				(prop6 setCycle: 0 hide:)
				(prop7 setCycle: 0 hide:)
				(prop8 setCycle: 0 hide: setPri: -1)
				(letters setCycle: 0 z: 1000)
				(theMusic2 stop:)
				(if (> (++ register) sCarolOfTheBells)
					(= register sXmasTree)
					(self init:)
				else
					(= seconds (= ticks (= cycles 0)))
					(UnLoad RES_VIEW pCabinOutside)
					(UnLoad RES_VIEW pCabinInside)
					(UnLoad RES_VIEW vLogos)
					(self changeState: 3 cue:)
				)
			)
		)
	)
)

(instance prop1 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance prop2 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance prop3 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance prop4 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance prop5 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance prop6 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance prop7 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance prop8 of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)

(instance letters of Prop
	(properties
		view pCabinOutside
		signal (| ignrAct ignrHrz)
	)
)
