;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use TPSound)
(use GenDialog)
(use NewGame)
(use Events)
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
	global106
	gVoMyHelp
	global108 =  1
	global109 =  25
	global110 =  25
	global111 =  1
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
	global202 =  2
	theMusic
	theSound
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
	continueText
	global289
	gToSam
	gToMax
	gToVederPillow
	global293
)
(instance oUser of NewUser)

(instance oEventHandler of NewEventHandler)

(instance oBackgroundPlane of Plane)

(instance oLogoPlane of Plane)

(instance oMusic1 of TPSound
	(properties
		type mNOPAUSE
	)
)

(instance oSound1 of TPSound)

(instance Torin of NewGame
	(method (init &tmp [temp0 6] i [temp7 2])
		(= saveCurX 0)
		(= saveCurY 0)
		(theGame handsOff:)
		(= screenHeight 480)
		(= screenWidth 640)
		(= lastScreenX 639)
		(= lastScreenY 479)
		(if (not (= continueText (MakeMessageText NULL NULL C_CONTINUE 1 0)))
			(= continueText (String with: {Continue}))
		)
		(= userFont 999)
		(= smallFont 999)
		(= bigFont 2510)
		(= inputFont 999)
		(DoAudio AudMixCheck 0)
		(= systemPlane (Plane new:))
		((= user oUser) alterEgo: (= ego (ScriptID 64007 0)))
		(super init:)
		(oLogoPlane
			picture: 61000
			priority: 800
			init: 0 0 639 479
		)
		(FrameOut)
		(thePlane setRect: 4 4 635 319)
		((= theMusic oMusic1)
			owner: self
			flags: mNOPAUSE
		)
		((= theSound oSound1)
			owner: self
			flags: (| mNOPAUSE mLOAD_AUDIO)
		)
		(= gNewStr (String new:))
		((= gameDir (String new:)) copy: curSaveDir)
		(= global108 1)
		(= approachCode oApproachCode)
		(= gVerb V_DO)
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
		(for ((= i 0)) (< i 9) ((++ i))
			(= [gHoldTime i] -1)
			(= [gLoop i] (& i $0003))
		)
		(= newRoomNum -4536)
	)
	
	(method (changeScore delta)
		((ScriptID 64002 3) registerDefaultHandler: delta)
	)
)

(instance oHandsOffCode of Code
	(method (doit allHands &tmp userCanControl temp1)
		(= userCanControl (user canControl:))
		(= temp1 (if (not argc) else (== allHands 1)))
		(user canControl: 0 canInput: 0)
		(if (!= theCursor waitCursor)
			(theGame setCursor: waitCursor)
		)
		(if (and userCanControl temp1)
			(= saveCurX mouseX)
			(= saveCurY mouseY)
			(SetCursor 415 363)
			(= isHandsOff TRUE)
		)
	)
)

(instance oHandsOnCode of Code
	(method (doit)
		(user canControl: TRUE canInput: TRUE)
		(if
			(and
				isHandsOff
				(< (Abs (- mouseX 415)) 12)
				(< (Abs (- mouseY 363)) 12)
			)
			(SetCursor saveCurX saveCurY)
		)
		(= isHandsOff FALSE)
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
