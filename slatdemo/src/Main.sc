;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Print)
(use Messager)
(use Talker)
(use PMouse)
(use LoadMany)
(use Grooper)
(use Window)
(use Ego)
(use Sound)
(use Game)
(use User)
(use Actor)
(use System)

(public
	TheGame 0
	Bset 1
	Bclr 2
	Btst 3
	pSound 4
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
	global100
	gameCode =  1234
	theDemoSound
	global103 =  1
	global104 =  1
	global105 =  1
	curPage =  1
	buildDate
	usPhone
	intPhone
	global110
	global111 =  7
	theMusic
	numVoices
	numColors
	egoLooper
	global116
	global117 =  1
	global118 =  12
	arrowView
	global120
	global121
	global122
	global123 =  -1
	global124 =  175
	global125 =  280
	global126 =  360
	global127 =  470
	global128 =  590
	global129 =  680
	global130 =  785
	global131 =  865
	global132 =  975
	global133 =  1080
	global134 =  1165
	global135 =  1275
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
	gameFlags
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

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(instance demoSound of Sound)

(instance pSound of Sound
	(properties
		flags mNOPAUSE
		number 907
	)
)

(instance egoObj of Ego)

(instance TheGame of Game
	
	(method (init &tmp temp0 versionFile [str 121])
		(LoadMany RES_SCRIPT SIGHT)
		(super init:)
		(StrCpy @sysLogPath {})
		(= version {x.yyy.zzz})
		(= buildDate {mm/dd/yy})
		(= usPhone {991-999-9999})
		(= intPhone {992-999-9999})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileFGets usPhone 20 versionFile)
		(FileIO fileFGets intPhone 20 versionFile)
		(FileIO fileFGets usPhone 20 versionFile)
		(FileIO fileClose versionFile)
		(= systemWindow SysWindow)
		(= userFont 4)
		(Print font: userFont)
		((= narrator Narrator)
			font: userFont
			back: 11
			keepWindow: TRUE
		)
		(= msgType TEXT_MSG)
		(= useSortedFeatures TRUE)
		(= eatMice 30)
		(= textSpeed 2)
		(= pMouse PseudoMouse)
		(= numVoices (DoSound NumVoices))
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(Bclr fIsVGA)
		else
			(Bset fIsVGA)
		)
		(systemWindow color: global110 back: global111)
		(= egoLooper stopGroop)
		(self setCursor: ARROW_CURSOR TRUE 160 160)
		(= ego egoObj)
		(User alterEgo: ego canControl: FALSE canInput: FALSE)
		(= theDemoSound demoSound)
		(= messager gameMessager)
		((= mouseDownHandler MH) add:)
		((= keyDownHandler KH) add:)
		((= directionHandler DH) add:)
		((= theMusic pSound) owner: self init:)
		(keyDownHandler addToFront: TheGame)
		(theGame masterVolume: 15)
		(self newRoom: 812)
	)
	
	(method (startRoom roomNum)
		(if pMouse
			(pMouse stop:)
		)
		(rightArrow dispose: delete:)
		(leftArrow dispose: delete:)
		(stopButton dispose: delete:)
		(LoadMany FALSE
			INTER_ACTOR INTER_FEATURE INTER_PROP INTER_VIEW
			DIALOG SIGHT INTRFACE RANDCYC STOPWALK
			INCEND ENDBEG ANIMATION SENTENCE WORD WRITEFTR
		)
		(keyDownHandler delete: TheGame)
		(if (u> (MemoryInfo FreeHeap) (+ 10 (MemoryInfo LargestPtr)))
			(if modelessDialog
				(modelessDialog dispose:)
			)
			(Prints {Memory fragmented.})
			(SetDebug)
		)
		(keyDownHandler addToFront: TheGame)
		(if (not (OneOf curRoomNum 812 804 111 801 802 803))
			(= arrowView [global123 curRoomNum])
		else
			(= arrowView 9)
		)
		(if (not (OneOf curRoomNum 13 14))
			(stopButton view: arrowView init: stopUpd:)
			(leftArrow view: arrowView init: stopUpd:)
			(rightArrow view: arrowView init: stopUpd:)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(cond 
			(
				(and
					(== (event type?) keyDown)
					(== (event message?) `q)
				)
				(= quit TRUE)
			)
			(
				(and
					(or
						(== (event type?) keyDown)
						(== (event type?) mouseDown)
					)
					(== curRoomNum 812)
				)
				(event claimed: TRUE)
				(curRoom newRoom: 13)
			)
			(
				(and
					(or
						(== (event type?) keyDown)
						(== (event type?) mouseDown)
					)
					(== curRoomNum 13)
				)
				(event claimed: TRUE)
				(curRoom newRoom: 14)
			)
			(
				(and
					(== (theCursor cel?) 10)
					(or
						(== (event type?) keyDown)
						(== (event type?) mouseDown)
					)
					(== (User canInput:) TRUE)
				)
				(if (== (OnControl CMAP (event x?) (event y?)) 8)
					(event claimed: TRUE)
					(stopButton setScript: stopButtonScript)
				)
			)
			((FileIO fileExists {g})
				(event claimed: FALSE)
				((ScriptID 880) handleEvent: event)
				((ScriptID 880) dispose:)
				(DisposeScript 880)
			)
		)
		(return
			(super handleEvent: event)
		)
	)
	
	(method (setCursor form showIt theX theY &tmp temp0)
		(if (IsObject form)
			(theCursor init:)
		else
			((= theCursor cursorObj)
				view: 990
				loop: 0
				setCel:
					(switch form
						(ARROW_CURSOR 10)
						(SNAIL_CURSOR 11)
						(HAND_CURSOR 12)
						(INVIS_CURSOR 13)
						(else  form)
					)
				init:
			)
		)
		(if (and (> argc 1) (not showIt))
			((= theCursor cursorObj)
				view: 2
				loop: 10
				setCel: 14
				init:
			)
		)
		(if (> argc 2)
			(theCursor posn: theX theY)
		)
	)
	
	(method (handsOff)
		(super handsOff:)
		(user canInput: FALSE canControl: FALSE)
		(if (not (HaveMouse))
			(theGame setCursor: INVIS_CURSOR)
		else
			(theGame setCursor: waitCursor)
		)
	)
	
	(method (handsOn)
		(super handsOn:)
		(self setCursor: ARROW_CURSOR TRUE)
		(user canInput: TRUE)
		(user canControl: FALSE)
	)
)

(instance stopGroop of GradualLooper)

(instance MH of EventHandler)

(instance KH of EventHandler)

(instance DH of EventHandler)

(instance gameMessager of Messager
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					(NARRATOR narrator)
					(else  narrator)
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)

(instance cursorObj of Cursor
	(properties
		view 990
	)
)

(instance leftArrow of Prop
	(properties
		x 200
		y 308
	)
)

(instance rightArrow of Prop
	(properties
		x 200
		y 308
		loop 1
	)
)

(instance stopButton of Prop
	(properties
		x 158
		y 158
		loop 2
	)
)

(instance leftArrowScript of Script
	
	(method (changeState newState &tmp [temp0 10] temp10)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= temp10 curPage)
				(leftArrow view: arrowView setCel: 1)
				(= ticks 24)
			)
			(1
				(leftArrow setCel: 0)
				(= ticks 6)
			)
			(2
				(if (== curPage 1)
					(self cue:)
				else
					(-- curPage)
				)
				(if (not (== curRoomNum 801))
					(switch curPage
						(1
							(curRoom newRoom: 801)
						)
						(else 
							(curRoom newRoom: curPage)
						)
					)
				else
					(curRoom newRoom: 801)
				)
			)
		)
	)
)

(instance rightArrowScript of Script
	
	(method (changeState newState &tmp [temp0 10] temp10)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= temp10 curPage)
				(rightArrow view: arrowView setCel: 1)
				(= ticks 24)
			)
			(1
				(rightArrow setCel: 0)
				(= ticks 6)
			)
			(2
				(if (== curPage global118)
					(== curPage global118)
				else
					(++ curPage)
				)
				(if (not (== curRoomNum 801))
					(switch curRoomNum
						(12 (curRoom newRoom: 804))
						(else 
							(curRoom newRoom: curPage)
						)
					)
				else
					(curRoom newRoom: 801)
				)
			)
		)
	)
)

(instance stopButtonScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(stopButton view: arrowView setCel: 1)
				(= ticks 24)
			)
			(1
				(stopButton setCel: 0)
				(= ticks 6)
			)
			(2
				(curRoom newRoom: 14)
			)
		)
	)
)
