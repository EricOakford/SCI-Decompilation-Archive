;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include sci.sh)
(use Procs)
(use ShiversSound)
(use Movie)
(use DText)
(use Plane)
(use String)
(use Array)
(use Print)
(use Feature)
(use Ego)
(use Sound)
(use File)
(use Game)
(use User)
(use Actor)
(use System)

(public
	SHIVERS 0
)

(local
	ego
	theGame
	curRoom
	thePlane
	quit
	cast
	regions
	timers
	sounds
	inventory
	planes
	curRoomNum
	prevRoomNum
	newRoomNum
	debugOn
	score
	possibleScore
	textCode
	cuees
	theCursor
	normalCursor
	waitCursor
	userFont =  1
	smallFont =  4
	lastEvent
	eventMask =  32767
	bigFont =  1
	version
	autoRobot
	curSaveDir
	numCD
	perspective
	features
	panels
	useSortedFeatures
	unused_6
	overlays =  -1
	doMotionCue
	systemPlane
	saveFileSelText
	unused_8
	unused_2
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
	useObstacles =  1
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
	extMouseHandler
	talkers
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
	altPolyList
	screenWidth =  320
	screenHeight =  200
	lastScreenX =  319
	lastScreenY =  199
	global100
	global101
	global102
	gNewCast
	global104
	global105
	global106
	global107
	global108
	global109 =  100
	gExitFeature
	gSound1
	gSound2
	gSound3
	gSound4
	global115
	global116
	global117
	global118 =  6220
	global119 =  212
	global120 =  7112
	global121
	global122 =  8100
	global123
	global124 =  8490
	global125
	global126 =  9420
	global127 =  217
	global128 =  9760
	global129
	global130 =  11310
	global131
	global132 =  12181
	global133
	global134 =  14080
	global135
	global136 =  16420
	global137
	global138 =  19220
	global139 =  202
	global140 =  20553
	global141
	global142 =  21070
	global143
	global144 =  22190
	global145
	global146 =  23550
	global147
	global148 =  24320
	global149
	global150 =  24380
	global151
	global152 =  25050
	global153
	global154 =  29080
	global155
	global156 =  30420
	global157
	global158 =  31310
	global159
	global160 =  32570
	global161
	global162 =  -30426
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
	global187 =  15
	global188 =  178
	global189 =  253
	global190 =  178
	global191 =  36
	global192 =  173
	global193 =  232
	global194 =  173
	global195 =  57
	global196 =  169
	global197 =  211
	global198 =  169
	global199 =  78
	global200 =  166
	global201 =  190
	global202 =  166
	global203 =  99
	global204 =  163
	global205 =  169
	global206 =  163
	global207
	gNewCast_3
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
	global321 =  -1
	global322 =  1
	global323 =  3
	global324 =  5
	global325 =  11
	global326 =  9
	global327 =  7
	gCurColor
	global329 =  1
	global330 =  2
	global331
	global332 =  1
	global333 =  2
	global334
	global335 =  2
	global336 =  3
	global337
	global338 =  1
	global339 =  3
	gGEventX
	gGEventY
	global342
	gGGModNum
	global344
	gNewCast_2
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
	global401
	global402
	global403
	global404
	global405
	global406
	global407
	global408
	global409
	global410
	global411
	global412
	global413
	global414
	global415
	global416
	global417
	global418
	global419
	global420
	global421
	global422
	global423
	global424
	global425
	global426
	global427
	global428
	global429
	global430
	global431
	global432
	global433
	global434
	global435
	global436
	global437
	global438
	global439
	global440
	global441
	global442
	global443
	global444
	global445
	global446
	global447
	global448
	global449
	global450
	global451
	global452
	global453
	global454
	global455
	global456
	global457
	global458
	global459
	global460
	global461
	global462
	global463
	global464
	global465
	global466
	global467
	global468
	global469
	global470
	global471
	global472
	global473
	global474
	global475
	global476
	global477
	global478
	global479
	global480
	global481
	global482
	global483
	global484
	global485
	global486
	global487
	global488
	global489
	global490
	global491
	global492
	global493
	global494
	global495
	global496
	global497
	global498
	global499
	global500
	global501
	global502
	global503
	global504
	global505
	global506
	global507
	global508
	global509
	global510
	global511
	global512
	global513
	global514
	global515
	global516
	global517
	global518
	global519
	global520
	global521
	global522
	global523
	global524
	global525
	global526
	global527
	global528
	global529
	global530
	global531
	global532
	global533
	global534
	global535
	global536
	global537
	global538
	global539
	global540
	global541 =  1
	global542 =  2
	global543 =  3
	global544
	global545 =  1
	global546
	global547
	global548
	global549
	global550
	global551
	gSound5
	gSound6
)
(instance sound1 of ShiversSound
	(properties)
)

(instance sound2 of ShiversSound
	(properties)
)

(instance sound3 of ShiversSound
	(properties)
)

(instance sound4 of ShiversSound
	(properties)
)

(instance sound5 of ShiversSound
	(properties)
)

(instance sound6 of ShiversSound
	(properties)
)

(class ShiversRoom of Room
	(properties
		scratch 0
		script 0
		number 0
		modNum -1
		noun 0
		case 0
		timer 0
		keep 0
		initialized 0
		picture -1
		plane 0
		style $ffff
		exitStyle -1
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		purge 2000
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		edgeN 40
		edgeE 319
		edgeW 0
		edgeS 189
		movie 0
		invView 0
	)
	
	(method (init &tmp temp0)
		(theGame handsOn:)
		(super init: &rest)
	)
	
	(method (dispose)
		(if movie (movie dispose:))
		(super dispose: &rest)
	)
	
	(method (drawPic pic)
		(if (== style -1) (= style 0))
		(super drawPic: pic &rest)
	)
	
	(method (initRoom param1 param2 param3 param4)
		(if (!= global106 0)
			(= global346
				((ShiversRoomItem new:)
					view: global106
					loop: (curRoom invView?)
					cel: 0
					x: (/ (+ param1 param2) 2)
					y: param4
					setScale:
					scaleX: (/ (* 128 (- param4 param3)) 75)
					scaleY: (/ (* 128 (- param4 param3)) 75)
					nsLeft: param1
					nsTop: param3
					nsRight: param2
					nsBottom: param4
					init:
					yourself:
				)
			)
		)
	)
	
	(method (drawText param1 param2 param3 param4)
		(textBox dispose:)
		(if (> argc 1)
			(if (> argc 3)
				(textBox x: param2 y: param3 fore: param4 init: param1)
			else
				(textBox x: param2 y: param3 fore: 181 init: param1)
			)
		else
			(textBox init: param1)
		)
		(textBox text: (param1 data?) setSize: 263)
		(UpdateScreenItem textBox)
		(FrameOut)
	)
	
	(method (eraseText)
		(textBox dispose:)
		(FrameOut)
	)
	
	(method (playAVI param1 param2 &tmp temp0 temp1 [temp2 3])
		(if movie (movie dispose:))
		(if (proc951_5 38)
			(= temp0 27)
			(= temp1 7)
		else
			(= temp0 91)
			(= temp1 47)
		)
		(= movie
			((Movie new:)
				posn: temp0 temp1
				open: param1
				client: self
				yourself:
			)
		)
		(if (and (> argc 1) param2) (movie caller: param2))
		(if (proc951_5 38) (movie putDouble:))
		(movie play:)
	)
	
	(method (playVMD param1 param2 &tmp temp0 temp1 [temp2 3])
		(if movie (movie dispose:))
		(if (proc951_5 38)
			(= temp0 54)
			(= temp1 16)
		else
			(= temp0 182)
			(= temp1 102)
		)
		(= movie
			((VmdMovie new:) open: param1 client: self yourself:)
		)
		(if (and (> argc 1) param2) (movie caller: param2))
		(if (proc951_5 38)
			(movie put: temp0 temp1 5 setWaitEvent: 7)
		else
			(movie put: temp0 temp1 4 setWaitEvent: 7)
		)
		(movie close:)
	)
)

(instance SHIVERS of Game
	(properties)
	
	(method (init)
		(= global186 1)
		(Font 1 640 480)
		(= systemPlane (Plane new:))
		(= user User)
		(= score 0)
		(= version {x.yyy.zzz})
		(user alterEgo: (Ego new:))
		(user init:)
		(= global528 (Str new: 9))
		(super init: &rest)
		(= sounds SoundManager)
		(= directionHandler shiversDirection)
		(= gNewCast (Cast new:))
		(= global102
			((Plane new:)
				picture: -2
				priority: 10
				init: 0 0 320 200
				addCast: gNewCast
				yourself:
			)
		)
		(gNewCast plane: global102)
		(= gNewCast_2 (Cast new:))
		(= global344
			((Plane new:)
				picture: -2
				priority: 30
				init: 0 0 320 200
				addCast: gNewCast_2
				yourself:
			)
		)
		(gNewCast_2 plane: global344)
		(= gNewCast_3 (Cast new:))
		(= global207
			((Plane new:)
				picture: -2
				priority: 45
				init: 27 133 290 200
				addCast: gNewCast_3
				yourself:
			)
		)
		(gNewCast_3 plane: global207)
		(SetPalStyleRange 180 236)
		(thePlane setRect: 27 7 290 149 priority: 20)
		(DoAudio 12 0)
		((= gSound1 sound1) channelNum: 1 owner: self init:)
		((= gSound2 sound2) channelNum: 2 owner: self init:)
		((= gSound3 sound3) channelNum: 3 owner: self init:)
		((= gSound4 sound4) channelNum: 4 owner: self init:)
		((= gSound5 sound5) channelNum: 5 owner: self init:)
		((= gSound6 sound6) channelNum: 6 owner: self init:)
		(= global101 900)
		(self newRoom: 942)
	)
	
	(method (doit)
		(cond 
			((user canControl:)
				(if (== theCursor waitCursor)
					(theGame setCursor: normalCursor)
					(if gExitFeature
						(= theCursor (gExitFeature cursorCel?))
						(SetCursor 999 0 theCursor)
					)
				)
			)
			((!= theCursor waitCursor) (theGame setCursor: waitCursor))
		)
		(if
		(and (== global550 1) (not (curRoom script?)))
			(= global550 0)
			(theGame save:)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (replay &tmp temp0 temp1 temp2 temp3 planesNextNode temp5 temp6)
		(MonoOut {REPLAY#%d} curRoomNum)
		(= planesNextNode (KList 3 (planes elements?)))
		(while planesNextNode
			(planes nextNode: (KList 6 planesNextNode))
			(= temp1 (KList 8 planesNextNode))
			(AddPlane temp1)
			(= temp5 (KList 3 ((temp1 casts?) elements?)))
			(while temp5
				((temp1 casts?) nextNode: (KList 6 temp5))
				(= temp2 (KList 8 temp5))
				(= temp6 (KList 3 (temp2 elements?)))
				(while temp6
					(temp2 nextNode: (KList 6 temp6))
					(if (& ((= temp3 (KList 8 temp6)) -info-?) $0010)
						(AddScreenItem temp3)
					)
					(= temp6 (temp2 nextNode?))
				)
				(= temp5 ((temp1 casts?) nextNode?))
			)
			(= planesNextNode (planes nextNode?))
		)
		(if
			(or
				(== curRoomNum 993)
				(== curRoomNum 994)
				(== curRoomNum 927)
				(== curRoomNum 991)
				(== curRoomNum 910)
			)
			(curRoom newRoom: gGGModNum)
		)
		(if lastEvent (lastEvent dispose:))
		(theGame setCursor: waitCursor 1)
		(= temp0
			(if (not (OneOf (curRoom style?) -1 16 17 18 19))
				(curRoom style?)
			else
				0
			)
		)
		(cond 
			(
			(and (not (user canControl:)) (not (user canInput:))) (theGame setCursor: waitCursor))
			((and theIconBar (theIconBar curIcon?)) (theGame setCursor: (theIconBar getCursor:)))
			(else (theGame setCursor: normalCursor))
		)
		(SL doit:)
		(DoSound sndNOP)
		(Sound pause: 0)
		(gSound1 pause: 0)
		(gSound2 pause: 0)
		(gSound3 pause: 0)
		(gSound4 pause: 0)
		(gSound5 pause: 0)
		(gSound6 pause: 0)
		(= gExitFeature 0)
		(= tickOffset (- gameTime (GetTime)))
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (startRoom roomNum &tmp temp0 temp1)
		(= temp0 0)
		(while (< temp0 23)
			(breakif (== [global118 (* temp0 2)] roomNum))
			(++ temp0)
		)
		(if (< temp0 23)
			(= global106 [global118 (+ (* temp0 2) 1)])
			(= global185 1)
		else
			(= global106 0)
			(= global185 0)
		)
		(if (== global100 1)
			(if (< roomNum 0)
				(= temp1 (/ (= temp0 (+ roomNum -32768)) 10))
				(= temp0 (+ (mod temp0 10) 8))
				(= temp1 (+ temp1 (/ temp0 10) 3276))
				(= temp0 (mod temp0 10))
				(MonoOut {Room = %d%d} temp1 temp0)
			else
				(MonoOut {Room = %d} roomNum)
			)
		)
		(if (!= global105 0) (theDoits add: global108))
		(theDoits add: gSound1)
		(theDoits add: gSound2)
		(theDoits add: gSound3)
		(theDoits add: gSound4)
		(theDoits add: gSound5)
		(theDoits add: gSound6)
		(if
			(and
				(< 0 prevRoomNum)
				(< prevRoomNum 990)
				(or (>= roomNum 990) (< roomNum 0))
			)
			((ScriptID 950 0) init:)
			(if (!= global105 0)
				(self setScript: (ScriptID 950 13))
			)
		)
		(if
			(and
				(or (>= prevRoomNum 990) (< prevRoomNum 0))
				(< 0 roomNum)
				(< roomNum 950)
			)
			((ScriptID 950 0) dispose:)
		)
		(= theCursor 0)
		(SetCursor 999 0 0)
		(normalCursor show:)
		(super startRoom: roomNum)
	)
	
	(method (restore param1 &tmp [temp0 4] temp4 temp5 temp6 temp7 planesNextNode temp9 temp10)
		(MonoOut {RESTORE#%d} param1)
		(= planesNextNode (KList 3 (planes elements?)))
		(while planesNextNode
			(planes nextNode: (KList 6 planesNextNode))
			(= temp4 (KList 8 planesNextNode))
			(= temp9 (KList 3 ((temp4 casts?) elements?)))
			(while temp9
				((temp4 casts?) nextNode: (KList 6 temp9))
				(= temp5 (KList 8 temp9))
				(= temp10 (KList 3 (temp5 elements?)))
				(while temp10
					(temp5 nextNode: (KList 6 temp10))
					(if
						(= temp7
							(& ((= temp6 (KList 8 temp10)) -info-?) $0010)
						)
						(DeleteScreenItem temp6)
						(temp6 -info-: (| (temp6 -info-?) temp7))
					)
					(= temp10 (temp5 nextNode?))
				)
				(= temp9 ((temp4 casts?) nextNode?))
			)
			(DeletePlane temp4)
			(= planesNextNode (planes nextNode?))
		)
		(if
		(SaveGame 3 {SHIVER} param1 (KString 9 version))
			(self getDisc: (CD 1))
			(SaveGame 1 {SHIVER} param1 version)
		else
			(= planesNextNode (KList 3 (planes elements?)))
			(while planesNextNode
				(planes nextNode: (KList 6 planesNextNode))
				(= temp4 (KList 8 planesNextNode))
				(AddPlane temp4)
				(= temp9 (KList 3 ((temp4 casts?) elements?)))
				(while temp9
					((temp4 casts?) nextNode: (KList 6 temp9))
					(= temp5 (KList 8 temp9))
					(= temp10 (KList 3 (temp5 elements?)))
					(while temp10
						(temp5 nextNode: (KList 6 temp10))
						(if
						(& ((= temp6 (KList 8 temp10)) -info-?) $0010)
							(AddScreenItem temp6)
						)
						(= temp10 (temp5 nextNode?))
					)
					(= temp9 ((temp4 casts?) nextNode?))
				)
				(= planesNextNode (planes nextNode?))
			)
			(theGame setCursor: normalCursor)
			(switch
				(Print
					addBitmap: 928 3 0
					addButtonBM: 928 4 0 0 {} 135 100
					addButtonBM: 928 5 0 1 {} 85 100
					init:
				)
				(0)
				(1 (deleteGame doit: param1))
			)
		)
	)
	
	(method (save &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(asm
			pushi    2
			lofsa    {SAVE#%s}
			push    
			pushi    #data
			pushi    0
			lag      global528
			send     4
			push    
			callk    MonoOut,  4
			pushi    5
			pushi    0
			lofsa    {SHIVER}
			push    
			lsg      global539
			pushi    #data
			pushi    0
			lag      global528
			send     4
			push    
			pushi    2
			pushi    9
			lsg      version
			callk    KString,  4
			push    
			callk    SaveGame,  10
			sat      temp0
			not     
			bnt      code_0c85
			pushi    #setCursor
			pushi    1
			lsg      normalCursor
			lag      theGame
			send     6
			pushi    #addBitmap
			pushi    3
			pushi    928
			pushi    6
			pushi    0
			pushi    426
			pushi    7
			pushi    928
			pushi    2
			pushi    0
			pushi    0
			lofsa    {LOOKUP\_ERROR}
			push    
			pushi    105
			pushi    53
			pushi    142
			pushi    0
			class    Print
			send     32
			push    
			dup     
			ldi      0
			eq?     
			bnt      code_0c81
			ret     
code_0c81:
			toss    
			jmp      code_0dc9
code_0c85:
			pushi    #format
			pushi    3
			lofsa    {%s%d.SG}
			push    
			lsg      curSaveDir
			lsg      global539
			class    Str
			send     10
			sat      temp1
			pushi    #new
			pushi    1
			pushi    20
			class    Str
			send     6
			sat      temp4
			pushi    #format
			pushi    2
			lofsa    {%s\0D\n}
			push    
			pushi    #data
			pushi    0
			lag      global528
			send     4
			push    
			class    Str
			send     8
			sat      temp4
			lag      global349
			bnt      code_0d45
			lsg      score
			ldi      0
			eq?     
			bnt      code_0ce3
			pushi    #format
			pushi    2
			lofsa    {%-3d000}
			push    
			lsg      global349
			class    Str
			send     8
			sat      temp3
			jmp      code_0d56
code_0ce3:
			pushi    0
			lag      score
			lt?     
			bnt      code_0d08
			pprev   
			ldi      10
			lt?     
			bnt      code_0d08
			pushi    #format
			pushi    3
			lofsa    {%-3d00%d}
			push    
			lsg      global349
			lsg      score
			class    Str
			send     10
			sat      temp3
			jmp      code_0d56
code_0d08:
			pushi    9
			lag      score
			lt?     
			bnt      code_0d2d
			pprev   
			ldi      100
			lt?     
			bnt      code_0d2d
			pushi    #format
			pushi    3
			lofsa    {%-3d0%-2d}
			push    
			lsg      global349
			lsg      score
			class    Str
			send     10
			sat      temp3
			jmp      code_0d56
code_0d2d:
			pushi    #format
			pushi    3
			lofsa    {%-3d%-3d}
			push    
			lsg      global349
			lsg      score
			class    Str
			send     10
			sat      temp3
			jmp      code_0d56
code_0d45:
			pushi    #format
			pushi    2
			lofsa    {%-6d}
			push    
			lsg      score
			class    Str
			send     8
			sat      temp3
code_0d56:
			pushi    #strip
			pushi    1
			pushi    115
			lat      temp3
			send     6
			pushi    #new
			pushi    0
			class    File
			send     4
			sat      temp2
			pushi    #name
			pushi    1
			pushi    #data
			pushi    0
			lat      temp1
			send     4
			push    
			lat      temp2
			send     6
			pushi    #open
			pushi    1
			pushi    2
			pushi    517
			pushi    1
			pushi    #data
			pushi    0
			lat      temp4
			send     4
			push    
			pushi    517
			pushi    1
			pushi    #data
			pushi    0
			lat      temp3
			send     4
			push    
			pushi    521
			pushi    0
			lat      temp2
			send     22
			pushi    #dispose
			pushi    0
			lat      temp2
			send     4
			pushi    #dispose
			pushi    0
			lat      temp1
			send     4
			pushi    #dispose
			pushi    0
			lat      temp4
			send     4
			pushi    #dispose
			pushi    0
			lat      temp3
			send     4
code_0dc9:
			lat      temp0
			ret     
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(if (== (event type?) evKEYBOARD)
			(switch (event message?)
				(KEY_ALT_v
					(Prints {Version number 1.02})
					(event claimed: 1)
				)
				(KEY_ALT_s
					(if (or (< curRoomNum 0) (> curRoomNum 1000))
						(= global550 1)
						(event claimed: 1)
					)
				)
				(KEY_ALT_q
					(= quit 1)
					(event claimed: 1)
				)
			)
		)
		(return (super handleEvent: event))
	)
)

(class ShiversInvItem of Prop
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 57
		y 185
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
	)
	
	(method (init)
		(super init: gNewCast_2)
		(self setPri: 254 plane: global344)
		(UpdatePlane global344)
		(theDoits add: self)
		(self setScript: (ScriptID 950 13))
	)
	
	(method (doit)
		(if (and (== global184 1) (!= curRoomNum 992))
			(if
				(and
					(< 0 mouseX)
					(< mouseX 320)
					(< 0 mouseY)
					(< mouseY 200)
				)
				(normalCursor hide:)
				(self x: mouseX y: mouseY show:)
			else
				(self hide:)
				(normalCursor show:)
			)
			(if (self isNotHidden:) (UpdateScreenItem self))
		)
	)
	
	(method (dispose)
		(gNewCast_2 delete: self)
		(self setScript: (ScriptID 950 14))
		(theDoits delete: self)
		(super dispose: &rest)
	)
)

(class ShiversRoomItem of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 20
		fixPriority 1
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
	)
	
	(method (init)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(self isNotHidden:)
				(!= global184 1)
				(user canControl:)
			)
			(theGame handsOff:)
			(event claimed: 1)
			(sounds stop: 15014)
			(sounds play: 15014 0 106 0)
			(= temp2 global105)
			(MonoOut {prev: %d} temp2)
			(if (and (<= 200 global106) (<= global106 219))
				(proc951_16 (- global106 200))
			)
			(if (== (Abs (- global105 global106)) 10)
				(= global105 (+ (Max global105 global106) 10))
				(= global106 0)
			else
				(= temp1 global105)
				(= global105 global106)
				(= global106 temp1)
			)
			(= temp0 0)
			(while (< temp0 23)
				(breakif (== [global118 (* temp0 2)] curRoomNum))
				(++ temp0)
			)
			(= [global118 (+ (* temp0 2) 1)] global106)
			(if (!= global106 0)
				(self view: global106 loop: (curRoom invView?) setScale:)
				(UpdateScreenItem self)
			else
				(mouseDownHandler delete: self)
				(theGame handsOn:)
				(self dispose:)
			)
			(if (== temp2 0)
				(= global108
					((ShiversInvItem new:)
						view: global105
						loop: 0
						init:
						yourself:
					)
				)
			else
				(global108 view: global105)
				(UpdateScreenItem global108)
			)
			(theGame handsOn:)
		)
		(super handleEvent: event &rest)
	)
)

(class ShiversProp of Prop
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $0021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
	)
	
	(method (init)
		(mouseDownHandler add: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(user canControl:)
				(!= global184 1)
			)
			(event claimed: 1)
			(self doVerb:)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb)
	)
)

(class PotSpot of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
	)
	
	(method (init)
		(mouseDownHandler add: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp temp0)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(== global184 1)
				(== global106 0)
				(user canControl:)
			)
			(theGame handsOff:)
			(event claimed: 1)
			(sounds play: 15030 0 90 0)
			(normalCursor show:)
			(= global184 0)
			(= global346
				((ShiversRoomItem new:)
					view: global105
					loop: (curRoom invView?)
					cel: 0
					x: (/ (+ (self nsLeft?) (self nsRight?)) 2)
					y: (self nsBottom?)
					setScale:
					scaleX: (/ (* 128 (- (self nsBottom?) (self nsTop?))) 75)
					scaleY: (/ (* 128 (- (self nsBottom?) (self nsTop?))) 75)
					nsLeft: (self nsLeft?)
					nsTop: (self nsTop?)
					nsRight: (self nsRight?)
					nsBottom: (self nsBottom?)
					init:
					yourself:
				)
			)
			(= global106 global105)
			(= global105 0)
			(global108 dispose:)
			(= global108 0)
			(= temp0 0)
			(while (< temp0 23)
				(breakif (== [global118 (* temp0 2)] curRoomNum))
				(++ temp0)
			)
			(= [global118 (+ (* temp0 2) 1)] global106)
			(theGame handsOn:)
		)
		(super handleEvent: event &rest)
	)
)

(class HotSpot of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
	)
	
	(method (init)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
				(self onMe: event)
				(!= global184 1)
			)
			(event claimed: 1)
			(self doVerb:)
		)
		(super handleEvent: event &rest)
	)
	
	(method (doVerb)
	)
)

(class ExitFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft -999
		nsTop -999
		nsRight -999
		nsBottom -999
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x -999
		y -999
		z 0
		exitDir 0
		nextRoom 0
		cursorCel -999
	)
	
	(method (init param1)
		(self exitDir: param1 plane: thePlane)
		(mouseDownHandler add: self)
		(theDoits add: self)
		(cond 
			((== param1 1)
				(if (== nsLeft -999) (= nsLeft 244))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 264))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 1))
			)
			((== param1 2)
				(if (== nsLeft -999) (= nsLeft 0))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 20))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 2))
			)
			((== param1 6)
				(if (== nsLeft -999) (= nsLeft 244))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 264))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 6))
			)
			((== param1 7)
				(if (== nsLeft -999) (= nsLeft 0))
				(if (== nsTop -999) (= nsTop 0))
				(if (== nsRight -999) (= nsRight 20))
				(if (== nsBottom -999) (= nsBottom 143))
				(if (== cursorCel -999) (= cursorCel 7))
			)
			((or (== param1 9) (== param1 3)) (if (== cursorCel -999) (= cursorCel 3)))
			((== param1 4) (if (== cursorCel -999) (= cursorCel 4)))
			((== param1 5) (if (== cursorCel -999) (= cursorCel 5)))
			((and (== param1 8) (== cursorCel -999)) (= cursorCel 8))
		)
		(if (== cursorCel -999) (= cursorCel 0))
		(super init: &rest)
	)
	
	(method (doit &tmp temp0 temp1)
		(= temp0 (- mouseX 27))
		(= temp1 (- mouseY 7))
		(cond 
			((self onMe: temp0 temp1)
				(if
				(and (!= theCursor cursorCel) (== gExitFeature 0))
					(= theCursor cursorCel)
					(SetCursor 999 0 theCursor)
					(= gExitFeature self)
				)
			)
			(
			(and (== theCursor cursorCel) (== gExitFeature self))
				(= theCursor 0)
				(SetCursor 999 0 theCursor)
				(= gExitFeature 0)
			)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(theDoits delete: self)
		(= gExitFeature 0)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(event localize: thePlane)
		(if
			(and
				(& (event type?) evMOUSEBUTTON)
				(self onMe: event)
				(!= nextRoom 0)
				(user canControl:)
				(!= global184 1)
			)
			(theGame handsOff:)
			(event claimed: 1)
			(if (== global184 1)
				(global108 x: 57 y: 185 view: global105 show:)
				(UpdateScreenItem global108)
				(= global184 0)
			)
			(normalCursor show:)
			(curRoom newRoom: nextRoom)
		)
		(super handleEvent: event &rest)
	)
)

(instance textBox of DText
	(properties
		priority 255
		fixPriority 1
		fore 181
		back 0
		font 2510
	)
	
	(method (init param1)
		(self text: (param1 data?) setSize: 265)
		(super init: gNewCast_3)
	)
	
	(method (dispose)
		(if text (KString 4 text) (= text 0))
		(gNewCast_3 delete: self)
		(super dispose: &rest)
	)
)

(instance deleteGame of Code
	(properties)
	
	(method (doit param1 &tmp temp0 temp1 newStr newStr_2 newFile temp5 temp6 temp7 temp8 temp9)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= temp0 (IntArray new: 21))
		(= temp1
			(SaveGame 5 {SHIVER} (newStr data?) (temp0 data?))
		)
		(SaveGame 6 (newStr_2 data?) {SHIVER})
		((= newFile (File new:))
			name: (newStr_2 data?)
			open: 2
		)
		(= temp6 0)
		(while (< temp6 temp1)
			(if (!= (temp0 at: param1) (temp0 at: temp6))
				(= temp5 (temp0 at: temp6))
				(newStr_2
					at: 0 (& temp5 $00ff)
					at: 1 (& (>> temp5 $0008) $00ff)
					at: 2 0
				)
				(newFile write: (newStr_2 data?) 2)
				(= temp8 (Str new: 36))
				(temp8 copyToFrom: 0 newStr (* temp6 36) 36)
				(newFile write: (temp8 data?) 36)
				(temp8 dispose:)
			)
			(++ temp6)
		)
		(newStr_2 at: 0 255 at: 1 255)
		(newFile write: (newStr_2 data?) 2 close: dispose:)
		(SaveGame
			7
			(newStr_2 data?)
			{SHIVER}
			(temp0 at: param1)
		)
		(FileIO 4 (newStr_2 data?))
		(= temp8 (Str new: 200))
		(= temp8
			(Str format: {%s%d.SG} curSaveDir (temp0 at: param1))
		)
		(FileIO 4 (temp8 data?))
		(temp8 dispose:)
		(newStr_2 dispose:)
		(newStr dispose:)
	)
)

(instance shiversDirection of EventHandler
	(properties)
	
	(method (handleEvent)
	)
)
