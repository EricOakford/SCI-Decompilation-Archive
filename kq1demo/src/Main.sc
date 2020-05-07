;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use myWindow)
(use LoadMany)
(use StopWalk)
(use Grooper)
(use Sound)
(use Game)
(use User)
(use Menu)
(use System)

(public
	Demo000 0
	HandsOff 1
	cls 2
	RedrawCast 3
	NormalEgo 4
	gameSound 5
	backSound 6
	DisplayOldGraphics 7
	DisplayNewGraphics 8
	RedrawPic 9
)

(local
	ego
	theGame
	curRoom
	speed =  6
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
	showStyle =  IRISOUT
	aniInterval
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	volume =  12
	version
	locales
	curSaveDir
		global31
		global32
		global33
		global34
		global35
		global36
		global37
		global38
		global39
		global40
		global41
		global42
		global43
		global44
		global45
		global46
		global47
		global48
		global49
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	demoScripts
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime =  3
	currentPalette
	modelessPort
	sysLogPath
		global64
		global65
		global66
		global67
		global68
		global69
		global70
		global71
	endSysLogPath
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
	isHandsOff
	global101
	numVoices
	global103
	debugging
	howFast
	global106
	numColors
	machineSpeed
	startingRoom
	global110
	shadowedTextX
	shadowedTextY
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
	(theGame setCursor: waitCursor (HaveMouse) 350 200)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (NormalEgo)
	(ego
		view: 0
		setLoop: -1
		setPri: -1
		setMotion: 0
		setStep: 3 2
		looper: GradualLooper
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: 0
		setCycle: StopWalk 2
	)
	(Load VIEW 2)
)

(procedure (DisplayOldGraphics theX theY &tmp i seconds theColor)
	(if (>= argc 1)
		(= shadowedTextX theX)
		(= shadowedTextY theY)
	else
		(= shadowedTextX 214)
		(= shadowedTextY 165)
	)
	(= i (if (!= howFast 0) 0 else 2))
	(while (< i 6)
		(= seconds 0)
		(while (< seconds 40)
			(-- seconds)
			(= seconds (+ seconds 2))
		)
		(if (< i 1) (= theColor vLGREY) else (= theColor vBLACK))
		(Display 0 2
			p_at shadowedTextX shadowedTextY
			p_font SYSFONT
			p_color theColor
		)
		(++ shadowedTextX)
		(++ shadowedTextY)
		(++ i)
	)
	(Display 0 2
		p_at (- shadowedTextX 1) (- shadowedTextY 1)
		p_color vWHITE
	)
	(= shadowedTextX 214)
	(= shadowedTextY 165)
)

(procedure (DisplayNewGraphics theX theY &tmp i seconds theColor)
	(if (< argc 1)
		(= shadowedTextX 214)
		(= shadowedTextY 165)
	else
		(= shadowedTextX theX)
		(= shadowedTextY theY)
	)
	(= theColor 0)
	(= i (if (!= howFast 0) 0 else 2))
	(while (< i 6)
		(= seconds 0)
		(while (< seconds 40)
			(-- seconds)
			(= seconds (+ seconds 2))
		)
		(Display 0 3
			p_at shadowedTextX shadowedTextY
			p_font SYSFONT
			p_color theColor
		)
		(++ shadowedTextX)
		(++ shadowedTextY)
		(= theColor (+ theColor 1))
		(++ i)
	)
	(Display 0 3
		p_at (- shadowedTextX 1) (- shadowedTextY 1)
		p_color vWHITE
	)
	(= shadowedTextX 214)
	(= shadowedTextY 165)
)

(procedure (RedrawPic)
	(curRoom drawPic: (curRoom picture?))
)

(instance egoObj of Ego
	(properties
		name "ego"
	)
)

(instance statusCode of Code
	(properties)
	
	(method (doit strg)
		(Format strg 0 0)
	)
)

(instance gameSound of Sound
	(properties
		number 39
		priority 5
	)
)

(instance backSound of Sound
	(properties
		number 39
	)
)

(instance Demo000 of Game
	(properties)
	
	(method (init &tmp [temp0 11])
		(= debugging FALSE)
		(LoadMany VIEW 657 2 0 500 699)
		(Load SCRIPT KQ_WINDOW)
		(= numColors (Graph GDetect))
		(= systemWindow myWindow)
		(super init:)
		(= numVoices (DoSound NumVoices))
		(= ego egoObj)
		(User alterEgo: ego)
		(= showStyle IRISIN)
		(DoSound ChangeVolume 8)
		(TheMenuBar init: draw: hide:)
		(StatusLine code: statusCode enable:)
		(= shadowedTextX 214)
		(= shadowedTextY 165)
		(backSound owner: self init:)
		(StopWalk init:)
		(HandsOff)
		(theGame setCursor: waitCursor TRUE)
		(= startingRoom 1)
		(self newRoom: SPEED)
	)
	
	(method (startRoom roomNum &tmp [temp0 12])
		(LoadMany FALSE
			AVOIDER SIGHT EXTRA TEXTRA RFEATURE GROOPER FOLLOW WANDER
			REVERSE TIMER DPATH FORCOUNT QSOUND TRACK JUMP ORBIT
			PATH NEW_WALK
		)
		(if
			(and
				debugging
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				(Print 0 1
					#button {Debug} 1
				)
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if
			(and
				(== (event type?) keyDown)
				(== (event message?) `@n)
			)
			(AddMenu { Debug_} {Area change.`@z})
		)
	)
)
