;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use Sound)
(use Save)
(use Game)
(use User)
(use Menu)
(use System)

(public
	Demo000 0
	HandsOff 1
	cls 2
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
	isHandsOff				;ego can't be controlled
	roomTimer				;remaining time before the next room
	numVoices				;Number of voices supported by sound driver
	endAnimationTimer		;remaining animation time in room 12 (Hero closeup)
	debugging				;debug mode enabled
	howFast					;machine speed level (0 = slow, 1 = medium, 2 = fast, 3 = fastest)
	globalMusic					;globalMusic object, current playing music
	numColors				;Number of colors supported by graphics driver
	machineSpeed			;used by the speed tester to test how fast the system is
							; and used in determining game speed. (used in conjunction with howFast)
	;the remaining globals are unused
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
)
(procedure (HandsOff)
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance statusCode of Code
	
	(method (doit strg)
		(Format strg 0 0)
	)
)

(instance GlobalSound of Sound
	(properties
		number sThemeSong
	)
)

(instance Demo000 of Game
	(method (init)
		;set up the game's objects and globals
		(= debugging TRUE) ;EO: Added to enable debug features
		(SysWindow color: vBLACK back: vLCYAN)
		(= numColors (Graph GDetect))
		(= systemWindow SysWindow)
		(super init:)
		(= numVoices (DoSound NumVoices))
		(= ego egoObj)
		(User alterEgo: ego)
		(= showStyle HSHUTTER)
		(TheMenuBar init: hide:)
		(StatusLine code: statusCode enable:)
		(if debugging
			(self setCursor: normalCursor (HaveMouse) 300 170)
		else
			(HandsOff)
			(self setCursor: normalCursor FALSE 350 200)
		)
		((= globalMusic GlobalSound)
			priority: 0
			init:
			owner: self
		)
		;now go to the speed tester
		(self newRoom: SPEED)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (startRoom roomNum &tmp temp0)
		;if memory is fragmented and debugging is on, bring up a warning and the internal debugger
		(if debugging
			(if
				(and
					(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
					(Print 0 1
						#button {Debug} 1
					)
				)
				(SetDebug)
			)
			(User canInput: TRUE)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event)
		;only allow input if debugging is enabled
		(if debugging
			(if
				(and
					(== (event type?) mouseDown)
					(& (event modifiers?) shiftDown)
				)
				(if (not (User canInput?))
					(event claimed: TRUE)
				else
					(cast eachElementDo: #handleEvent event)
					(if (event claimed?) (return))
				)
			)
			(super handleEvent: event)
			(if (event claimed?) (return))
			(switch (event type?)
				(keyDown
					((ScriptID DEBUG) handleEvent: event)
				)
				(mouseDown
					((ScriptID DEBUG) handleEvent: event)
				)
			)
		)
	)
)
