;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include sci.sh)
(use TPSound)
(use GenDialg)
(use NewGame)
(use NewUser)
(use PArray)
(use Plane)
(use String)
(use System)

(public
	Torin 0
	oBackgroundPlane 1
	oLogoPlane 2
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
	gVoMyHelp
	global108 =  1
	global109 =  25
	global110 =  25
	global111 =  1
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
	global202 =  2
	music1
	sound1
	global205 =  240
	global206 =  60
	gLoop
	global208
	global209
	global210
	global211
	global212
	global213
	global214
	global215
	gHoldTime
	global217
	global218
	global219
	global220
	global221
	global222
	global223
	global224
	global225 =  1
	global226
	gCurVolume =  40
	gSaveThis =  60
	global229 =  100
	global230
	global231
	global232
	global233
	gGLastVerbX
	gGLastVerbY
	global236
	global237
	saveCurX
	saveCurY
	gToTorinPullsOutMeat
	gToArchivistCU
	global242
	global243
	global244
	gToSmetana
	isHandsOff
	gToLeenahReactsToLocket
	global248
	global249
	global250
	global251
	global252 =  1
	global253 =  2
	global254 =  3
	global255 =  4
	global256 =  5
	global257 =  6
	global258
	global259
	global260
	global261
	global262
	global263
	global264
	global265
	gToVisceraMeat
	gToTripeMeat
	global268 =  2510
	global269 =  1
	global270 =  2510
	global271 =  1
	gFore =  234
	gBack =  210
	global274 =  234
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
	gToSam
	gToMax
	gToVederPillow
	global293
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

(instance oLogoPlane of Plane
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

(instance Torin of NewGame
	(properties)
	
	(method (init &tmp [temp0 6] temp6 [temp7 2])
		(= saveCurX 0)
		(= saveCurY 0)
		(theGame handsOff:)
		(= screenHeight 480)
		(= screenWidth 640)
		(= lastScreenX 639)
		(= lastScreenY 479)
		(if
		(not (= global288 (MakeMessageText 0 0 1 1 0)))
			(= global288 (Str with: {Continue}))
		)
		(= userFont 999)
		(= smallFont 999)
		(= bigFont 2510)
		(= inputFont 999)
		(DoAudio 12 0)
		(= systemPlane (Plane new:))
		((= user oUser) alterEgo: (= ego (ScriptID 64007 0)))
		(super init:)
		(oLogoPlane
			picture: -4536
			priority: 800
			init: 0 0 639 479
		)
		(FrameOut)
		(thePlane setRect: 4 4 635 319)
		((= music1 oMusic1) owner: self flags: 1)
		((= sound1 oSound1) owner: self flags: 5)
		(= gNewStr (Str new:))
		((= gNewStr_2 (Str new:)) copy: curSaveDir)
		(= global108 1)
		(= approachCode oApproachCode)
		(= gVerb 1)
		((ScriptID 64017 0) init:)
		((= gOEventHandler oEventHandler)
			init:
			scrolled: (ScriptID 64010 0)
			scriptId: (ScriptID 64010 1)
		)
		(self handsOnCode: oHandsOnCode)
		(self handsOffCode: oHandsOffCode)
		((= global100 (PArray new: 0))
			add: 1 -5527 80 -5179 75 -5176
		)
		(if
			(and
				(thePlane nScreenSizeX?)
				(!= (thePlane nScreenSizeX?) features)
			)
			((thePlane nScreenSizeX?) dispose:)
			(thePlane nScreenSizeX: features)
		)
		(oBackgroundPlane
			picture: -2
			priority: 0
			init:
				(thePlane left:)
				(thePlane top?)
				(thePlane right:)
				(thePlane bottom?)
		)
		((= messager (ScriptID 64032 1))
			nGoingTo: 1
			nPointLeft: (ScriptID 64004 0)
			init:
		)
		((ScriptID 64002 0) init:)
		(= temp6 0)
		(while (< temp6 9)
			(= [gHoldTime temp6] -1)
			(= [gLoop temp6] (& temp6 $0003))
			(++ temp6)
		)
		(= newRoomNum -4536)
	)
	
	(method (changeScore delta)
		((ScriptID 64002 3) registerDefaultHandler: delta)
	)
)

(instance oHandsOffCode of Code
	(properties)
	
	(method (doit param1 &tmp userCanControl temp1)
		(= userCanControl (user canControl:))
		(= temp1 (if (not argc) else (== param1 1)))
		(user canControl: 0 canInput: 0)
		(if (!= theCursor waitCursor)
			(theGame setCursor: waitCursor)
		)
		(if (and userCanControl temp1)
			(= saveCurX mouseX)
			(= saveCurY mouseY)
			(SetCursor 415 363)
			(= isHandsOff 1)
		)
	)
)

(instance oHandsOnCode of Code
	(properties)
	
	(method (doit)
		(user canControl: 1 canInput: 1)
		(if
			(and
				isHandsOff
				(< (Abs (- mouseX 415)) 12)
				(< (Abs (- mouseY 363)) 12)
			)
			(SetCursor saveCurX saveCurY)
		)
		(= isHandsOff 0)
	)
)

(instance oApproachCode of Code
	(properties)
	
	(method (doit param1)
		(return
			(switch param1
				(1 (return 1))
				(else  (return -32768))
			)
		)
	)
)
