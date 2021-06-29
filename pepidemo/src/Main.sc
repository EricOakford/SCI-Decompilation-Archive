;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use Intrface)
(use TwistyWindow)
(use TwistyIconItem)
(use TwistyInsetWindow)
(use TWEgo)
(use TWRoom)
(use Print)
(use Messager)
(use PAvoid)
(use Talker)
(use PMouse)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Window)
(use Sound)
(use File)
(use Game)
(use User)
(use System)

(public
	twisty 0
	Dummy_1 1
	HaveMem 2
	LogIt 3
	Bset 4
	Btst 5
	Bclr 6
	Face 7
	statusCode 9
	dictionary 10
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
	cDAudio
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
	theMusic
	gameCode =  1234
	numColors
	numVoices
	global104
	global105
	global106
	global107
	debugging
	global109
	global110
	global111
	global112
	theMusic2
	global114 =  1
	global115
	global116
	global117
	global118
	myBotBordColor
	myLftBordColor
	global121
	myLowlightColor
	myTopBordColor
	myRgtBordColor
	myTextColor
	myHighlightColor
	global127
	global128
	global129
	myBackColor
	global131
	gTheNewDButtonValue
	global133
	gameFlags
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
	global186
	global187
	global188
	global189
	global190
	global191
	gPepIcon5
	gameAct =  1
	global194
	global195
	global196
	speed =  6
	exitHandler
	global199
	global200
	isHandsOff
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
	aboutQuote =  12
	global214
	global215
	gGCursorNumber
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
)
(procedure (Dummy_1)
)

(procedure (HaveMem howMuch)
	(return (u> (MemoryInfo FreeHeap) howMuch))
)

(procedure (LogIt obj theVerb &tmp [nameBuf 40] [roomBuf 10] [verbBuf 10])
	(StrCpy @verbBuf
		(switch theVerb
			(V_DO {Do})
			(V_HELP {Help})
			(V_LOOK {Look})
			(V_TALK {Talk})
			(V_WALK {Walk})
			;(V_HELP {Help})
			(V_NOSE {NOSE})
			(V_EXIT {Exit})
			(V_PAW {PAW})
			(V_TEETH {TEETH})
			(V_TRIVIA {TRIVIA})
			(else  {UNKNOWN VERB})
		)
	)
	(Format @roomBuf 0 1 curRoomNum)
	(Format @nameBuf 0 2 (obj name?) @verbBuf mouseX mouseY)
	(File name: @roomBuf writeString: @nameBuf {\0D\n} close:)
)

(procedure (Bset)
	(manageFlags 0 &rest)
)

(procedure (Btst)
	(manageFlags 1 &rest)
)

(procedure (Bclr)
	(manageFlags 2 &rest)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj temp4)
	(= obj 0)
	(= temp4 0)
	(if (IsObject actor2)
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (> argc 2)
			(if (IsObject both)
				(= obj both)
			else
				(= temp4 both)
			)
			(if (== argc 4) (= obj whoToCue))
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= obj whoToCue))
	)
	(if temp4 (Face actor2 actor1))
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1
		setHeading: ang1To2 (if (IsObject obj) obj else 0)
	)
)

(procedure (manageFlags param1 flagEnum &tmp temp0 temp1 temp2 temp3)
	(asm
		ldi      0
		sat      temp0
code_0b3f:
		lst      temp0
		lsp      argc
		ldi      1
		sub     
		lt?     
		bnt      code_0bad
		lat      temp0
		lapi     flagEnum
		sat      temp1
		push    
		ldi      16
		div     
		sat      temp2
		pushi    1
		lst      temp1
		ldi      16
		mod     
		shl     
		sat      temp3
		lsp      param1
		dup     
		ldi      1
		eq?     
		bnt      code_0b6d
		jmp      code_0bad
		jmp      code_0ba8
code_0b6d:
		dup     
		ldi      2
		eq?     
		bnt      code_0b82
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		bnot    
		and     
		push    
		lat      temp2
		sagi     gameFlags
		jmp      code_0ba8
code_0b82:
		dup     
		ldi      0
		eq?     
		bnt      code_0b96
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		or      
		push    
		lat      temp2
		sagi     gameFlags
		jmp      code_0ba8
code_0b96:
		dup     
		ldi      3
		eq?     
		bnt      code_0ba8
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		xor     
		push    
		lat      temp2
		sagi     gameFlags
code_0ba8:
		toss    
		+at      temp0
		jmp      code_0b3f
code_0bad:
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		and     
		ret     
	)
)

(class TwistySound of Sound
	(properties
		change 0
		nextSong 0
		loopTwice 0
		holdVal 0
	)
	
	(method (check)
		(if handle
			(DoSound UpdateCues self)
		)
		(if signal
			(= prevSignal signal)
			(= signal 0)
			(if change
				(= change 0)
				(self play: vol client)
			else
				(if (IsObject client)
					(client cue: self)
				)
				(if (and nextSong (== prevSignal -1))
					(= number nextSong)
					(= nextSong 0)
					(self setLoop: -1 play: vol)
				)
				(if (== prevSignal 126)
					(if loopTwice
						(= loopTwice FALSE)
						(self play: vol)
						(if holdVal
							(self hold: holdVal)
						)
					else
						(= loopTwice TRUE)
					)
				)
			)
		)
	)
	
	(method (changeTo soundNum whoCares)
		(= number soundNum)
		(if (> argc (= change TRUE))
			(= client whoCares)
		)
	)
)

(instance longSong of TwistySound
	(properties
		flags mNOPAUSE
	)
)

(instance longSong2 of TwistySound
	(properties
		flags mNOPAUSE
	)
)

(instance kDHandler of EventHandler)

(instance dirHandler of EventHandler)

(instance mDHandler of EventHandler)

(instance wHandler of EventHandler)

(instance exHandler of EventHandler)

(class twisty of Game
	(properties
		saveCursorX -1
		saveCursorY -1
	)
	
	(method (init &tmp temp0 versionFile [debugBuf 20])
		Print
		(ScriptID TWINV)
		DText
		ego
		StopWalk
		PolyPath
		Polygon
		PAvoider
		TwistyInsetWindow
		TwistyWindow
		(ScriptID SIGHT)
		Narrator
		ADRoom
		(ScriptID 883)
		statusCode
		(super init:)
		((ScriptID 879 0) doit:)
		((ScriptID 894 0) init:)
		((ScriptID 888 0) init:)
		(systemWindow color: myTextColor back: myBackColor)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO fileOpen {version} 1))
		(FileIO fileFGets version 11 versionFile)
		(FileIO fileClose versionFile)
		(Format @debugBuf 0 0 880)
		(if (FileIO fileExists @debugBuf)
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(InitIconBars)
		(= ego (ScriptID TWEGO 0))
		(user alterEgo: (ScriptID TWEGO 0))
		(SetIconBar)
		((= keyDownHandler kDHandler) add:)
		((= mouseDownHandler mDHandler) add:)
		((= directionHandler dirHandler) add:)
		((= walkHandler wHandler) add:)
		(= pMouse TWPseudoMouse)
		((= exitHandler exHandler) add:)
		(mouseDownHandler addToFront: exHandler)
		(keyDownHandler addToFront: exHandler)
		(= doVerbCode twistyDoVerbCode)
		(= ftrInitializer twistyFtrInit)
		(= approachCode twistyApproachCode)
		((= narrator Narrator)
			font: userFont
			back: myBackColor
			color: myTextColor
		)
		(= msgType TEXT_MSG)
		(= messager twistyMessager)
		((= theMusic longSong) owner: self priority: 15 init:)
		((= theMusic2 longSong2) owner: self priority: 15 init:)
		(= normalCursor ((theIconBar at: 0) cursor?))
		(Bset 77)
		(= gameAct 0)
		(self handsOff: setCursor: ARROW_CURSOR TRUE newRoom: 100)
	)
	
	(method (doit)
		(if
			(and
				(exitHandler size?)
				(== (theIconBar curIcon?) (theIconBar walkIconItem?))
			)
			(exitHandler eachElementDo: #doit)
		)
		(super doit:)
	)
	
	(method (replay)
		(= systemWindow TwistyWindow)
		(= normalCursor gGCursorNumber)
		(statusCode doit:)
		(super replay:)
	)
	
	(method (newRoom n)
		(if (exitHandler size?)
			(exitHandler eachElementDo: #dispose)
		)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum)
		((ScriptID 2000 3)
			winX: 0
			winY: 0
			cSpeed: 6
			dontUpd: TRUE
			talkLoop: -1
			forceWidth: 0
			talkWidth: 0
			offX: 0
			offY: 0
		)
		((ScriptID 2004 0)
			winX: 0
			winY: 0
			cSpeed: 6
			dontUpd: TRUE
			talkLoop: -1
			forceWidth: 0
			talkWidth: 0
			offX: 0
			offY: 0
		)
		(narrator x: -1 y: -1)
		((ScriptID 895 0) normal: 1)
		((ScriptID 895 1) normal: 0)
		(if pMouse (pMouse stop:))
		((ScriptID 898) doit: roomNum)
		(if
			(and
				(> (- (MemoryInfo 1) 2) (MemoryInfo 0))
				(Print
					font: userFont
					addText: {Memory Fragmented}
					addButton: 0 {Who cares} 0 12
					addButton: 1 {Debug} 50 12
					init:
				)
			)
			(SetDebug)
		)
		(if debugOn (SetDebug))
		(statusCode doit:)
		(super startRoom: roomNum)
	)
	
	(method (restart)
		(= gGCursorNumber theCursor)
		(self setCursor: ARROW_CURSOR TRUE)
		(if (not argc)
			(if
				(Print
					font: userFont
					addText: {You want to start over?}
					addButton: 1 {RESTART} 10 20
					addButton: 0 {PLAY} 85 20
					init:
				)
				(Bset 77)
				(statusCode doit:)
				(super restart:)
			else
				(self setCursor: gGCursorNumber TRUE)
			)
		else
			(Bset 77)
			(statusCode doit:)
			(super restart:)
		)
	)
	
	(method (restore)
		(= gGCursorNumber normalCursor)
		(= normalCursor ARROW_CURSOR)
		((= systemWindow SysWindow) back: 41 color: 15)
		(super restore: &rest)
		(= normalCursor gGCursorNumber)
		(= systemWindow TwistyWindow)
		(theGame setCursor: normalCursor TRUE)
	)
	
	(method (save)
		(= gGCursorNumber normalCursor)
		(= normalCursor ARROW_CURSOR)
		((= systemWindow SysWindow) back: 41 color: 15)
		(super save: &rest)
		(= normalCursor gGCursorNumber)
		(= systemWindow TwistyWindow)
		(theGame setCursor: normalCursor TRUE)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(switch (event type?)
				(keyDown
					(switch (event message?)
						(TAB
							(if
								(and
									(not (Btst fCantSave))
									(not (& (gPepIcon5 signal?) DISABLED))
								)
								(gPepIcon5 select:)
							)
							(event claimed: TRUE)
						)
						(SHIFTTAB
							(if
								(and
									(not (Btst fCantSave))
									(not (& (gPepIcon5 signal?) DISABLED))
								)
								(gPepIcon5 select:)
							)
							(event claimed: TRUE)
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
							(if (and (not modelessDialog) (not (Btst fCantSave)))
								(theGame save:)
							)
							(event claimed: TRUE)
						)
						(`#7
							(if (and (not modelessDialog) (not (Btst fCantSave)))
								(theGame restore:)
							)
							(event claimed: TRUE)
						)
						(`#9
							(if (and (not modelessDialog) (not (Btst fCantSave)))
								(theGame restart:)
							)
							(event claimed: TRUE)
						)
						(`+
							(if (user controls?)
								(= speed (Max 0 (- (ego moveSpeed?) 1)))
								(ego setSpeed: speed)
							)
						)
						(`-
							(if (user controls?)
								(= speed (+ (ego moveSpeed?) 1))
								(ego setSpeed: speed)
							)
						)
						(`=
							(if (user controls?) (= speed 6) (ego setSpeed: speed))
						)
						(else 
							(if debugging
								((ScriptID 880) handleEvent: event)
								((ScriptID 880) dispose:)
							else
								(event claimed: FALSE)
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
		(if (and (> argc 1) (not showIt)) (SetCursor 996 0 0))
		(if (> argc 2) (SetCursor theX theY))
		(return oldCur)
	)
	
	(method (quitGame)
		(= gGCursorNumber theCursor)
		(self setCursor: ARROW_CURSOR TRUE)
		(super
			quitGame:
				(Print
					font: userFont
					addText: {Are You Sure?}
					addButton: 1 {QUIT} 0 12
					addButton: 0 {PLAY} 50 12
					init:
				)
		)
		(self setCursor: gGCursorNumber TRUE)
	)
	
	(method (pragmaFail)
		(if (User canInput:)
			(if (== ego (ScriptID 895 0))
				(messager say: NULL V_PRAGFAIL_PEP NULL 0 0 0)
			else
				(messager say: NULL V_PRAGFAIL_DOG NULL 0 0 0)
			)
			(LogIt theGame ((user curEvent?) message?))
		)
	)
	
	(method (handsOff &tmp theIconBarCurIcon)
		(if isHandsOff
			(return FALSE)
		else
			(= isHandsOff TRUE)
		)
		(User canControl: FALSE canInput: FALSE)
		(if (not argc)
			(ego setMotion: 0)
		)
		(= theIconBarCurIcon (theIconBar curIcon?))
		(if (== theIconBar (ScriptID 883 2))
			(theIconBar disable: 0 1 2 3 4 7 5 8 9)
		else
			(theIconBar disable: 0 1 2 3 4 5 8 6 8 9)
		)
		(theIconBar curIcon: theIconBarCurIcon)
		(if (not (HaveMouse))
			(= saveCursorX ((User curEvent?) x?))
			(= saveCursorY ((User curEvent?) y?))
			(theGame setCursor: waitCursor TRUE 304 172)
		else
			(theGame setCursor: waitCursor TRUE)
		)
		(return (if pMouse (pMouse stop:) else 0))
	)
	
	(method (handsOn)
		(= isHandsOff FALSE)
		(User canControl: TRUE canInput: TRUE)
		(if (== theIconBar (ScriptID 883 2))
			(theIconBar enable: 0 1 2 3 4 5 6 7 8 9)
		else
			(theIconBar enable: 0 1 2 3 4 5 6 7 8 9)
		)
		(if (not (theIconBar curInvIcon?))
			(if (== theIconBar (ScriptID 883 2))
				(theIconBar disable: 5)
			else
				(theIconBar disable: 6)
			)
		)
		(if (and (not (HaveMouse)) (!= saveCursorX -1))
			(theGame
				setCursor: ((theIconBar curIcon?) cursor?) TRUE saveCursorX saveCursorY
			)
		else
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
		)
		(= saveCursorX (= saveCursorY -1))
		(if (OneOf curRoomNum 120 135 140 150)
			(if (== theIconBar (ScriptID 883 2))
				(theIconBar disable: (theIconBar at: 8))
			else
				(theIconBar disable: (theIconBar at: 9))
			)
		)
	)
	
	(method (points pFlag pValue)
		(if (not (Btst pFlag))
			(+= score pValue)
			(Bset pFlag)
			(rm0Sound number: (+ pValue 935) loop: 1 flags: mNOPAUSE play:)
			(statusCode doit:)
		)
	)
	
	(method (setEgo theEgo_2 &tmp theEgo)
		(= theEgo 0)
		(if (cast contains: ego)
			(= theEgo ego)
			(cast delete: theEgo)
		)
		((= ego theEgo_2) forceUpd: startUpd:)
		(if (not (cast contains: ego)) (ego init:))
		(user alterEgo: ego)
		(if theEgo (cast addAfter: ego theEgo))
		(theIconBar curInvIcon: 0)
		(SetIconBar)
	)
)

(class Actions of Code
	
	(method (doVerb)
		(return FALSE)
	)
)

(instance twistyDoVerbCode of Code
	
	(method (doit theVerb obj &tmp [buffer 300] theNoun theCase theMod temp303 [temp304 400])
		(if (== theVerb V_WALK)
			((User curEvent?) claimed: FALSE)
		else
			(= theCase 0)
			(if (not (= theMod (obj modNum?)))
				(= theMod 0)
			)
			(switch obj
				((ScriptID 895 0)
					(= theNoun N_PEPPER)
					(if (== theVerb V_LOOK)
						(if (== ego (ScriptID 895 0))
							(= theCase C_LOOK_SELF)
						else
							(= theCase C_LOOK_PEP)
						)
					)
				)
				((ScriptID 895 1)
					(= theNoun N_LOCKJAW)
					(if (== theVerb V_LOOK)
						(if (== ego (ScriptID 895 0))
							(= theCase C_LOOK_DOG)
						else
							(= theCase C_LOOK_SELF)
						)
					)
				)
				(else 
					(= theNoun (obj noun?))
				)
			)
			(cond 
				((not (OneOf theVerb 7 6 86 30 85 89 84))
					(if (== ego (ScriptID 895 0))
						(= temp303 88)
						(if (== obj (ScriptID 895 1))
							(= temp303 90)
						)
					else
						(= temp303 90)
					)
					(cond 
						((Message MsgGet theMod theNoun theVerb theCase 1 @buffer)
							(messager say: theNoun theVerb theCase 0 0 theMod)
						)
						((Message MsgGet theMod theNoun temp303 theCase 1 @buffer)
							(messager say: theNoun temp303 theCase 0 0 theMod)
						)
						((== (curRoom modNum?) -1)
							(messager say: NULL temp303 theCase 0 0 0)
						)
						(
							(Message MsgGet (obj modNum?) (obj noun?) theVerb NULL 1 @buffer)
							(messager say: (obj noun?) theVerb NULL 1 0 (obj modNum?))
						)
						(
							(Message MsgGet (curRoom modNum?) (curRoom noun?) theVerb NULL 1 @buffer)
							(messager say: (curRoom noun?) theVerb NULL 1 0 (curRoom modNum?))
						)
						(
							(and
								(== (curRoom modNum?) curRoomNum)
								(Message MsgGet curRoomNum (curRoom noun?) theVerb 0 1 @buffer)
							)
							(messager say: (curRoom noun?) theVerb NULL 1 0 curRoomNum)
						)
						(else
							(messager say: NULL temp303 theCase NULL 0 0)
						)
					)
				)
				((Message MsgGet theMod theNoun theVerb theCase 1 @buffer)
					(messager say: theNoun theVerb theCase 0 0 theMod)
				)
				(
					(Message MsgGet curRoomNum (curRoom noun?) theVerb NULL 1 @buffer)
					(messager say: (curRoom noun?) theVerb NULL 1 0 curRoomNum)
				)
				((Message MsgGet 0 theNoun theVerb theCase 1 @buffer)
					(messager say: theNoun theVerb theCase 0 0 0)
				)
				((Message MsgGet 0 NULL theVerb theCase 1 @buffer)
					(messager say: NULL theVerb theCase 0 0 0)
				)
				(else
					(Prints {No Response})
					(LogIt obj theVerb)
				)
			)
		)
	)
)

(instance twistyFtrInit of Code
	
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

(instance twistyMessager of Messager
	
	(method (findTalker who &tmp theTalker)
		(= global194 who)
		(if
			(= theTalker
				(switch who
					(NARRATOR narrator)
					(91 (ScriptID curRoomNum 15))
					(45 (ScriptID 2000 3))
					(47 (ScriptID 2000 3))
					(PEPPER
						(ScriptID tlkPepper 3)
					)
					(1
						((ScriptID 2000 1) doit: who)
						(ScriptID 2000 0)
					)
					(12
						((ScriptID 2000 1) doit: who)
						(ScriptID 2000 0)
					)
					(23
						((ScriptID 2000 1) doit: who)
						(ScriptID 2000 0)
					)
					(49 (ScriptID 2000 3))
					(34 (ScriptID 2000 2))
					(50 (ScriptID 2004 0))
					(15 (ScriptID 2006 0))
					(16 (ScriptID curRoomNum 13))
					(2 (ScriptID curRoomNum 19))
					(3 (ScriptID 121 1))
					(4 (ScriptID 121 2))
					(5 (ScriptID 2012 0))
					(6 (ScriptID curRoomNum 4))
					(17 (ScriptID 2018 0))
					(18 (ScriptID 2018 1))
					(19 (ScriptID 2020 0))
					(20 (ScriptID curRoomNum 14))
					(51 (ScriptID 2020 0))
					(21 (ScriptID curRoomNum 1))
					(24 (ScriptID curRoomNum 5))
					(25 (ScriptID curRoomNum 6))
					(26 (ScriptID curRoomNum 1))
					(27 (ScriptID curRoomNum 2))
					(28 (ScriptID curRoomNum 3))
					(30 (ScriptID curRoomNum 6))
					(32 (ScriptID curRoomNum 10))
					(33 (ScriptID curRoomNum 9))
					(36 (ScriptID curRoomNum 4))
					(29 (ScriptID curRoomNum 5))
					(39 (ScriptID curRoomNum 11))
					(40 (ScriptID curRoomNum 12))
					(41 (ScriptID curRoomNum 2))
					(43 (ScriptID curRoomNum 2))
					(44 (ScriptID curRoomNum 2))
					(31 (ScriptID curRoomNum 7))
					(37 (ScriptID curRoomNum 8))
					(38 (ScriptID curRoomNum 3))
					(22 (ScriptID curRoomNum 1))
					(52 (ScriptID curRoomNum 2))
					(11 (ScriptID curRoomNum 18))
					(35 (ScriptID curRoomNum 2))
					(else  (ScriptID curRoomNum 1))
				)
			)
			(return)
		else
			(if debugging
				(Printf {Error in findTalker\ntalker num: %d} who)
			)
			(super findTalker: who)
		)
	)
)

(instance twistyApproachCode of Code
	
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK $0001)
			(V_TALK $0002)
			(V_WALK $0004)
			(V_DO $0008)
			(30 $0010)
			(86 $0020)
			(89 $0040)
			(84 $0080)
			(else  $0100)
		)
	)
)

(instance rm0Sound of Sound
	(properties
		priority 15
	)
)

(instance statusCode of Code
	
	(method (doit &tmp [scoreBuf 80] [statusBuf 80])
		(Message MsgGet 0 N_PRAGFAIL NULL C_SCORE 1 @scoreBuf)
		(Message MsgGet 0 N_PRAGFAIL NULL C_SCORE 2 @statusBuf)
		(Format @statusBuf @scoreBuf score)
		(if (not (Btst 77))
			(DrawStatus @statusBuf 21 0)
		else
			(DrawStatus @statusBuf 0 0)
		)
	)
)

(instance dictionary of Code

	(method (doit &tmp temp0 [temp1 1500] temp1501 newTwistyWindow temp1503 temp1504)
		(if
			(OneOf global215
				8 9 12 14 15 18 19 21 22 26 44 49 50
				55 57 58 68 71 72 74 75 83 97 100 103
			)
			(= temp1501 200)
			(= temp1503 80)
			(= temp1504 100)
		else
			(= temp1501 160)
			(= temp1503 90)
			(= temp1504 120)
		)
		(Message MsgPush)
		(= newTwistyWindow (TwistyWindow new:))
		(if
		(not (Message MsgGet 801 1 0 global215 1 @temp1))
			(Format @temp1 {ERROR: Vocab word not defined!})
		)
		(if (== global215 58)
			(theMusic2 number: 927 setLoop: 1 play:)
		)
		((Print new:)
			width: temp1501
			window: newTwistyWindow
			font: userFont
			x: temp1503
			y: temp1504
			addText: @temp1
			init:
		)
		(Message MsgPop)
		(UnLoad 143 801)
	)
)

(instance TWPseudoMouse of PseudoMouse
	
	(method (handleEvent event &tmp theIconBarCurIcon)
		(if (& (event type?) direction)
			(= theIconBarCurIcon (theIconBar curIcon?))
			(theIconBar curIcon: 0)
			(super handleEvent: event)
			(theIconBar curIcon: theIconBarCurIcon)
		)
	)
)
