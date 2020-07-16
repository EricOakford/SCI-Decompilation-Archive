;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use BordWind)
(use Sound)
(use Save)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	ARTHUR 0
	RedrawCast 1
	cls 2
	HandsOff 3
	WindowCorners 4
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
	version =  {continuousMusic}
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
	demoDialogTime
	currentPalette
	modelessPort
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
	isHandsOff
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
	global100
	debugging
	global102
	global103
	howFast
	global105
	global106
	global107
	global108
	global109
	numColors
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
	global343
	global344
	global345
	global346
	global347
	global348
	global349
	global350
	global351
	global352
	global353
	global354
	global355
	global356
	global357
	global358
	global359
	global360
	global361
	global362
	global363
	global364
	global365
	global366
	global367
	global368
	global369
	global370
	global371
	global372
	global373
	global374
	global375
	global376
	global377
	global378
	global379
	global380
	global381
	global382
	global383
	global384
	global385
	global386
	global387
	global388
	global389
	global390
	global391
	global392
	global393
	global394
	global395
	global396
	global397
	global398
	global399
	global400
)
(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (HandsOff)
	(= isHandsOff TRUE)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (WindowCorners param1 &tmp theLoop cel1 cel2 cel3 cel4)
	(= theLoop (/ (& param1 $00f0) 16))
	(= cel2
		(= cel3 (= cel4 (= cel1 (& param1 $000f))))
	)
	(switch (& param1 $0f00)
		(256 (++ cel3) (++ cel4))
		(512 (++ cel2) (++ cel3))
		(1024
			(++ cel2)
			(= cel3 (+ cel3 2))
		)
	)
	(corner1 loop: theLoop cel: cel1)
	(corner2 loop: theLoop cel: cel2)
	(corner3 loop: theLoop cel: cel3)
	(corner4 loop: theLoop cel: cel4)
	(addToPics add: corner1 corner2 corner3 corner4)
)

(instance continuousMusic of Sound)

(instance music of Sound
	(properties
		priority 10
	)
)

(instance statusCode of Code

	(method (doit strg)
		(Format strg 0 0)
	)
)

(instance egoObj of Ego
	(properties
		name {ego}
	)
)

(instance corner1 of PicView
	(properties
		view 550
		priority 15
	)
)

(instance corner2 of PicView
	(properties
		y 170
		view 550
		priority 15
	)
)

(instance corner3 of PicView
	(properties
		x 295
		view 550
		cel 1
		priority 15
	)
)

(instance corner4 of PicView
	(properties
		y 170
		x 295
		view 550
		cel 1
		priority 15
	)
)

(instance BorderedWindow of bordWindow)

(instance ARTHUR of Game
	
	(method (init &tmp [temp0 21])
		(= debugging TRUE)
		(SysWindow color: vBLACK back: vWHITE)
		(= systemWindow bordWindow)
		(= numColors (Graph GDetect))
		(super init:)
		(= ego egoObj)
		(User alterEgo: ego blocks: 0 y: 155)
		(= showStyle HSHUTTER)
		(= debugging TRUE)
		(TheMenuBar init: draw: hide:)
		(StatusLine code: statusCode enable:)
		(HandsOff)
		(DoSound ChangeVolume 12)
		(if debugging
			(self setCursor: normalCursor (HaveMouse) 300 170)
		else
			(self setCursor: normalCursor 0 350 200)
		)
		(self newRoom: 99)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(systemWindow color: vWHITE back: vBLACK)
	)
	
	(method (newRoom n)
		(cls)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp [temp0 53])
		(DisposeScript AVOIDER)
		(DisposeScript 919)
		(DisposeScript TIMER)
		(DisposeScript FOLLOW)
		(if
			(and
				debugging
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				(Print 0 1 #button {Debug} 1)
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
		(Load VIEW 550)
		(if debugging
			(curRoom setLocales: DEBUG)
		)
	)
	
	(method (handleEvent event &tmp [temp0 55])
		(super handleEvent: event)
		(if (event claimed?) (return))
	)
)
