;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
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
	ego								;pointer to ego
	theGame							;ID of the Game instance
	curRoom							;ID of current room
	thePlane						;default plane
	quit							;when TRUE, quit game
	cast							;collection of actors
	regions							;set of current regions
	timers							;list of timers in the game
	sounds							;set of sounds being played
	inventory						;set of inventory items in game
	planes							;list of all active planes in the game
	curRoomNum						;current room number
	prevRoomNum						;previous room number
	newRoomNum						;number of room to change to
	debugOn							;generic debug flag -- set from debug menu
	score							;the player's current score
	possibleScore					;highest possible score
	textCode						;code that handles interactive text
	cuees							;list of who-to-cues for next cycle
	theCursor						;the number of the current cursor
	normalCursor					;number of normal cursor form
	waitCursor						;cursor number of "wait" cursor
	userFont	=	USERFONT		;font to use for Print
	smallFont	=	4 				;small font for save/restore, etc.
	lastEvent					  	;the last event (used by save/restore game)
	eventMask	=	allEvents	  	;event mask passed to GetEvent in (uEvt new:)
	bigFont	=		USERFONT	  	;large font
	version	=		0			  	;pointer to 'incver' version string
									;	WARNING!  Must be set in room 0
									;	(usually to {x.yyy    } or {x.yyy.zzz})
	autoRobot
	curSaveDir						;address of current save drive/directory string
	numCD	=	0					;number of current CD, 0 for file based
	perspective						;player's viewing angle: degrees away
									;	from vertical along y axis
	features						;locations that may respond to events
	panels	=	NULL				;list of game panels
	useSortedFeatures	=	FALSE	;enable cast & feature sorting?
	unused_6
	overlays	= -1
	doMotionCue						;a motion cue has occurred - process it
	systemPlane						;ID of standard system plane
	saveFileSelText					;text of fileSelector item that's selected.
	unused_8
	unused_2
	[sysLogPath 20]					;-used for system standard logfile path	
	endSysLogPath					;/		(uses 20 globals)
	gameControls					;pointer to instance of game controls
	ftrInitializer					;pointer to code that gets called from
													;	a feature's init
	doVerbCode						;pointer to code that gets invoked if
									;	no feature claims a user event
	approachCode					;pointer to code that translates verbs
									;	into bits
	useObstacles	=	TRUE		;will Ego use PolyPath or not?
	unused_9
	theIconBar						;points to TheIconBar or Null	
	mouseX							;-last known mouse position
	mouseY							;/
	keyDownHandler					;-our EventHandlers, get called by game
	mouseDownHandler				;/
	directionHandler				;/
	speechHandler					;a special handler for speech events
	lastVolume
	pMouse	=	NULL				;pointer to a Pseudo-Mouse, or NULL
	theDoits	=	NULL			;list of objects to get doits each cycle
	eatMice	=	60					;how many ticks before we can mouse
	user	=	NULL				;pointer to specific applications User
	syncBias						;-globals used by sync.sc
	theSync							;/		(will be removed shortly)
	extMouseHandler					;extended mouse handler
	talkers							;list of talkers on screen
	inputFont	=	SYSFONT			;font used for user type-in
	tickOffset						;used to adjust gameTime after restore
	howFast							;measurment of how fast a machine is
	gameTime						;ticks since game start
	narrator						;pointer to narrator (normally Narrator)
	msgType	=	TEXT_MSG			;type of messages used
	messager						;pointer to messager (normally Messager)
	prints							;list of Print's on screen
	walkHandler						;list of objects to get walkEvents
	textSpeed	=	2				;time text remains on screen
	altPolyList						;list of alternate obstacles
	screenWidth	=  320				; Coordinate System Parameters
	screenHeight =  200				;
	lastScreenX	=  319				;
	lastScreenY	=  199				;
	;globals > 99 are for game use
	global100
	gVerb
	gInventItem
	global103
	global104
	gOEventHandler
	debugging
	gOScrollPlane
	gOFileReadWord_7 =  1
	global109 =  25
	global110 =  25
	gOFileReadWord_9 =  1
	global112 =  40
	global113 =  -1
	gNewStr
	gameDir
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
(procedure (localproc_05ff &tmp str)
	(if (not gOFileReadWord_6)
		(switch (Platform GetPlatType)
			(PlatWin
				(= gOFileReadWord_6
					(GetSierraProfileInt
						{Config}
						{VideoSpeed}
						gOFileReadWord_6
					)
				)
			)
			(PlatDos
				(= str (String new: 100))
				(GetConfig {VideoSpeed} str)
				(= gOFileReadWord_6 (/ (str asInteger:) 5))
				(str dispose:)
			)
			(PlatMac
				(= str (String new: 100))
				(GetConfig {VideoSpeed} str)
				(= gOFileReadWord_6 (str asInteger:))
				(str dispose:)
			)
			(else
				(= gOFileReadWord_6 0)
			)
		)
		(WritePrefs)
	)
)

(instance oUser of NewUser)

(instance oEventHandler of NewEventHandler)

(instance oBackgroundPlane of Plane)

(instance oTestFile of File)

(instance oMusic1 of TPSound
	(properties
		type mNOPAUSE
	)
)

(instance oSound1 of TPSound)

(instance L7 of NewGame
	(method (init &tmp str temp1 temp2)
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
		(DoAudio AudMixCheck 0)
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
		((= gOMusic1 oMusic1)
			owner: self
			flags: mNOPAUSE
		)
		((= gOSound1 oSound1)
			owner: self
			flags: (| mNOPAUSE mLOAD_AUDIO)
		)
		(= gNewStr (String new:))
		((= gameDir (String new:)) copy: curSaveDir)
		(if (not (= global288 (MakeMessageText 0 0 3 1 14)))
			(= global288 (String with: {Continue}))
		)
		(ReadPrefs)
		(localproc_05ff)
		(= approachCode oApproachCode)
		(= gVerb V_DO)
		((ScriptID 64017 0) init:)
		((= gOEventHandler oEventHandler)
			init:
			registerKeyHandler: (ScriptID 64010 0)
			registerGlobalHandler: (ScriptID 64010 1)
		)
		(proc64040_0)
		(proc64032_2)
		((= messager (ScriptID 64032 1)) init:)
		(= msgType CD_MSG)
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
		(if (FileIO FileExists {classes})
			(= debugging TRUE)
		else
			(= str (String newWith: 100 {_}))
			(GetConfig {TorinDebug} str)
			(str lower:)
			(if (== (= temp1 (str at: 0)) 116)
				(= debugging TRUE)
			)
			(str dispose:)
		)
		(if (FileIO FileExists {autotp})
			(= global243 1)
		)
		(if debugging
			((ScriptID 64014 0) init:)
		)
		(= str (String newWith: 100 {_}))
		(GetConfig {LeakDump} str)
		(str lower:)
		(if (== (= temp1 (str at: 0)) 116) (= global202 1))
		(str dispose:)
		(= str (String newWith: 100 {_}))
		(GetConfig {StartRoom} str)
		(= newRoomNum (str asInteger:))
		(str dispose:)
		(if (!= newRoomNum 0) (return))
		(= newRoomNum 50)
	)
	
	(method (changeScore delta)
		(global310 addScore: delta)
	)
)

(instance oHandsOffCode of Code
	(method (doit allHands &tmp userCanControl temp1)
		(= userCanControl (user canControl:))
		(= temp1 (if argc allHands else 0))
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
	(method (doit theVerb)
		(return
			(switch theVerb
				(V_DO
					(return $0001)
				)
				(else
					(return $8000)
				)
			)
		)
	)
)
