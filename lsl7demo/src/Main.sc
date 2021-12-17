;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include sci.sh)
(use oMenuPopupPlane)
(use TPSound)
(use oMessager)
(use n64036)
(use oInvPlane)
(use L7Talk)
(use NewGame)
(use NewUser)
(use GenDialog)
(use PArray)
(use Plane)
(use String)
(use File)
(use System)

(public
	L7 0
	oBackgroundPlane 1
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
	gVerb
	gInventItem
	global103
	global104
	gOEventHandler
	global106
	gOScrollPlane
	gOFileReadWord_7 =  1
	global109 =  25
	global110 =  25
	gOFileReadWord_9 =  1
	global112 =  40
	global113 =  -1
	gNewStr
	gNewStr_2
	gDisabledPlanes
	gOPlaneStack
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
	gOMusic1
	gOSound1
	global205 =  240
	global206 =  60
	gNewStr_3
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
	gOFileReadWord_3 =  65
	gOFileReadWord_4 =  60
	gOFileReadWord_5 =  100
	global230
	global231
	global232
	global233
	global234
	global235
	global236
	global237
	gGPEventX
	gGPEventY
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
	global268 =  2510
	global269 =  1
	global270 =  2510
	global271 =  2
	gFore
	gBack =  79
	global274
	global275 =  -5524
	global276 =  -5523
	global277 =  -5522
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
	gOFileReadWord_6
	global295
	gOFileReadWord =  1
	global297
	global298
	global299
	global300
	global301
	gPlaneLeft =  -1
	gPlaneTop =  -1
	gLeft =  -1
	gTop =  -1
	global306
	global307
	global308
	global309
	global310
	global311
	global312
	global313
	gOFileReadWord_2 =  1
	global315
	global316 =  20
	global317
	global318
	global319
	global320
	global321
	global322
	global323
	global324
	global325 =  1
	global326
	global327
	gOFileReadWord_10
	global329 =  1
	gToLarry
	global331
	gToThygh
	global333
	global334
	gToDewmi
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
)
(procedure (localproc_05ff &tmp temp0)
	(if (not gOFileReadWord_6)
		(switch (Platform 0)
			(2
				(= gOFileReadWord_6
					(GetSierraProfileInt
						{Config}
						{VideoSpeed}
						gOFileReadWord_6
					)
				)
			)
			(1
				(= temp0 (Str new: 100))
				(GetConfig {VideoSpeed} temp0)
				(= gOFileReadWord_6 (/ (temp0 asInteger:) 5))
				(temp0 dispose:)
			)
			(0
				(= temp0 (Str new: 100))
				(GetConfig {VideoSpeed} temp0)
				(= gOFileReadWord_6 (temp0 asInteger:))
				(temp0 dispose:)
			)
			(else  (= gOFileReadWord_6 0))
		)
		(WritePrefs)
	)
)

(instance oUser of NewUser
	(properties)
)

(instance oEventHandler of NewEventHandler
	(properties)
)

(instance oBackgroundPlane of Plane
	(properties)
)

(instance oTestFile of File
	(properties)
)

(instance oMusic1 of TPSound
	(properties
		type $0001
	)
)

(instance oSound1 of TPSound
	(properties)
)

(instance L7 of NewGame
	(properties)
	
	(method (init &tmp temp0 temp1 temp2)
		(= gGPEventX 0)
		(= gGPEventY 0)
		(theGame handsOff:)
		(= screenHeight 480)
		(= screenWidth 640)
		(= lastScreenX 639)
		(= lastScreenY 479)
		(= perspective 60)
		(= userFont 999)
		(= smallFont 999)
		(= bigFont 2510)
		(= inputFont 999)
		(DoAudio 12 0)
		(= systemPlane (Plane new:))
		((= user oUser) alterEgo: (= ego (ScriptID 64007 0)))
		(super init:)
		((ScriptID 0 1)
			picture: -2
			priority: 0
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		(if
			(and
				(thePlane oMyFeatures?)
				(!= (thePlane oMyFeatures?) features)
			)
			((thePlane oMyFeatures?) dispose:)
			(thePlane oMyFeatures: features)
		)
		((= gOMusic1 oMusic1) owner: self flags: 1)
		((= gOSound1 oSound1) owner: self flags: 5)
		(= gNewStr (Str new:))
		((= gNewStr_2 (Str new:)) copy: curSaveDir)
		(if
		(not (= global288 (MakeMessageText 0 0 3 1 14)))
			(= global288 (Str with: {Continue}))
		)
		(ReadPrefs)
		(localproc_05ff)
		(= approachCode oApproachCode)
		(= gVerb 2)
		((ScriptID 64017 0) init:)
		((= gOEventHandler oEventHandler)
			init:
			registerKeyHandler: (ScriptID 64010 0)
			registerGlobalHandler: (ScriptID 64010 1)
		)
		(proc64040_0)
		(proc64032_2)
		((= messager (ScriptID 64032 1)) init:)
		(= msgType 2)
		(proc64036_0)
		(self
			handsOnCode: oHandsOnCode
			handsOffCode: oHandsOffCode
		)
		((= global100 (PArray new: 0)) add: 2 -5527 1 -5527)
		(= gVerb 1)
		(proc64037_1)
		((ScriptID 64038 0) init:)
		((ScriptID 64000 0) init:)
		(if (FileIO 10 {classes})
			(= global106 1)
		else
			(= temp0 (Str newWith: 100 {_}))
			(GetConfig {TorinDebug} temp0)
			(temp0 lower:)
			(if (== (= temp1 (temp0 at: 0)) 116) (= global106 1))
			(temp0 dispose:)
		)
		(if (FileIO 10 {autotp}) (= global243 1))
		(if global106 ((ScriptID 64014 0) init:))
		(= temp0 (Str newWith: 100 {_}))
		(GetConfig {LeakDump} temp0)
		(temp0 lower:)
		(if (== (= temp1 (temp0 at: 0)) 116) (= global202 1))
		(temp0 dispose:)
		(= temp0 (Str newWith: 100 {_}))
		(GetConfig {StartRoom} temp0)
		(= newRoomNum (temp0 asInteger:))
		(temp0 dispose:)
		(if (!= newRoomNum 0) (return))
		(= newRoomNum 50)
	)
	
	(method (changeScore delta)
		(global310 addScore: delta)
	)
)

(instance oHandsOffCode of Code
	(properties)
	
	(method (doit param1 &tmp userCanControl temp1)
		(= userCanControl (user canControl:))
		(= temp1 (if argc param1 else 0))
		(user canControl: 0 canInput: 0)
		(if (!= theCursor waitCursor)
			(theGame setCursor: waitCursor)
		)
		(if userCanControl
			(if (ScriptID 64000 0) ((ScriptID 64000 0) disable:))
			(gOEventHandler killAllEventHogs:)
		)
		(if temp1
			(= gGPEventX mouseX)
			(= gGPEventY mouseY)
			(SetCursor 415 363)
			(= global245 1)
		)
	)
)

(instance oHandsOnCode of Code
	(properties)
	
	(method (doit)
		(user canControl: 1 canInput: 1)
		(if (ScriptID 64000 0) ((ScriptID 64000 0) enable:))
		(if
			(and
				global245
				(< (Abs (- mouseX 415)) 12)
				(< (Abs (- mouseY 363)) 12)
			)
			(SetCursor gGPEventX gGPEventY)
		)
		(= global245 0)
	)
)

(instance oApproachCode of Code
	(properties)
	
	(method (doit param1)
		(return
			(switch param1
				(2 (return 1))
				(else  (return -32768))
			)
		)
	)
)
