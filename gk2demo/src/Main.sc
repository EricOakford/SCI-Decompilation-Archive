;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use GK2IconBar)
(use DButton)
(use Plane)
(use Print)
(use Dialog)
(use Messager)
(use Talker)
(use PMouse)
(use IconBar)
(use Cursor)
(use Timer)
(use Sound)
(use Game)
(use User)
(use System)

(public
	GK2Demo 0
	Bset 1
	Bclr 2
	Btst 3
	proc0_4 4
	proc0_5 5
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
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	eventMask =  allEvents
	bigFont =  USERFONT
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
	useObstacles =  TRUE
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
	msgType =  TEXT_MSG
	messager
	prints
	walkHandler
	textSpeed =  2
	altPolyList
		global96
		global97
		global98
	lastSysGlobal
	global100
	gameCode =  1234
	global102
	numVoices
	theMusic1
	theMusic2
	isWindows
	global107
	global108
	global109
	global110
	theDefaultCursor
	global112
	iconsOpen
	global114
	global115
	global116
	global117
	global118
	global119
	global120
	saveCurIcon
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

(procedure (proc0_4 param1 param2 whoToCue &tmp i temp1)
	(if (or (< argc 2) (== param2 0))
		(= param2 1)
	)
	(if (or (== argc 0) (== param1 1))
		(for ((= i 100)) (> i 0) ((-= i param2))
			(Palette PalIntensity 16 256 i)
			(cast doit:)
			(FrameOut)
		)
	else
		(for ((= i 0)) (< i 100) ((+= i param2))
			(Palette PalIntensity 16 256 i)
			(cast doit:)
			(FrameOut)
		)
	)
	(if (== param1 1)
		(Palette PalIntensity 16 256 0)
	)
	(if (and (== argc 3) whoToCue)
		(whoToCue cue:)
	)
)

(procedure (proc0_5 param1 param2 whoToCue &tmp i temp1)
	(if (or (< argc 2) (== param2 0))
		(= param2 1)
	)
	(if (or (== argc 0) (== param1 1))
		(for ((= i 100)) (> i 0) ((-= i param2))
			(Palette PalIntensity 1 256 i)
			(cast doit:)
			(FrameOut)
		)
	else
		(for ((= i 0)) (< i 100) ((+= i param2))
			(Palette PalIntensity 1 256 i)
			(cast doit:)
			(FrameOut)
		)
	)
	(if (== param1 1)
		(Palette PalIntensity 1 256 0)
	)
	(if (and (== argc 3) whoToCue)
		(whoToCue cue:)
	)
)

(instance gSound1 of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance gSound2 of Sound
	(properties
		flags mNOPAUSE
	)
)

(class GK2Demo of Game
	
	(method (init &tmp versionFile [temp1 2])
		(SetFontRes 640 480)
		Print
		DButton
		Timer
		IconBar
		Narrator
		(super init: &rest)
		(thePlane setRect: 0 0 319 199 priority: 4)
		(= msgType TEXT_MSG)
		(User canControl: FALSE canInput: FALSE)
		(= systemPlane Plane)
		(= eatMice 6)
		(= doVerbCode gk2DVC)
		(= approachCode pApproachCode)
		(= pMouse PseudoMouse)
		(= messager gk2Messager)
		(= useSortedFeatures TRUE)
		(= numVoices (DoSound SndNumVoices))
		(= possibleScore 999)
		(= userFont 70)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO FileOpen {version} 1))
		(FileIO FileFGets version 10 versionFile)
		(FileIO FileClose versionFile)
		(= waitCursor theWaitCursor)
		(= normalCursor arrowCursor)
		(= theDefaultCursor defaultCursor)
		(self setCursor: waitCursor TRUE 160 120)
		(= mouseX 160)
		(= mouseY 80)
		((= theMusic1 gSound1) owner: self init:)
		((= theMusic2 gSound2) owner: self init:)
		(if (== (Platform) PlatWin)
			(= isWindows TRUE)
		)
		(= iconsOpen FALSE)
		(GK2Iconbar init:)
		(self
			handsOffCode: gk2HandsOffCode
			handsOnCode: gk2HandsOnCode
			newRoom: 100
		)
		(Dialog mouseHiliting: 1)
		((= narrator Narrator)
			x: 15
			y: 160
			talkWidth: 225
			font: userFont
			fore: 82
			back: 0
		)
		(Print fore: 12 back: 86)
	)
	
	(method (startRoom roomNum)
		(if pMouse
			(pMouse stop:)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(if (== (event type?) keyDown)
			(switch (event message?)
				(`^q
					(theGame quitGame:)
					(event claimed: TRUE)
				)
				(`@x
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
			)
		)
		(cond 
			((event claimed?) TRUE)
			((and script (script handleEvent: event)) TRUE)
			((& (event type?) userEvent)
				(if (user canInput:)
					(gk2DVC doit: (event message?))
				else
					(theGame pragmaFail:)
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (pragmaFail &tmp oldCur i)
		(= oldCur (self setCursor: defaultCursor))
		(for ((= i 0)) (< i 15000) ((++ i)))
		(self setCursor: oldCur)
	)
)

(instance pApproachCode of Code
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK $0001)
			(V_TALK $0002)
			(V_WALK $0004)
			(V_DO $0008)
			(else  $0010)
		)
	)
)

(instance gk2DVC of Code
	
	(method (doit theVerb)
		(cond 
			((== theVerb V_LOOK)
				(messager say: NULL V_LOOK NULL 1 0 0)
			)
			((== theVerb V_DO)
				(messager say: NULL V_DO NULL 1 0 0)
			)
			((== theVerb V_TALK)
				(messager say: NULL V_TALK NULL 1 0 0)
			)
			(else
				(theGame pragmaFail:)
			)
		)
	)
)

(instance gk2HandsOffCode of Code

	(method (doit theX theY)
		(if (not saveCurIcon)
			(= saveCurIcon (theIconBar curIcon?))
		)
		(if argc
			(theGame setCursor: theWaitCursor TRUE theX theY)
		else
			(theGame setCursor: theWaitCursor TRUE)
		)
		(User canControl: FALSE canInput: FALSE)
		(if ego
			(ego setMotion: 0)
		)
	)
)

(instance gk2HandsOnCode of Code
	
	(method (doit theX theY &tmp temp0)
		(User canControl: TRUE canInput: TRUE)
		(if saveCurIcon
			(theIconBar
				curIcon: saveCurIcon
				highlight: saveCurIcon
			)
		)
		(= saveCurIcon 0)
		(if argc
			(theGame
				setCursor: ((theIconBar curIcon?) getCursor:) TRUE theX theY
			)
		else
			(theGame
				setCursor: ((theIconBar curIcon?) getCursor:) TRUE
			)
		)
	)
)

(instance defaultCursor of Cursor
	(properties
		view ARROW_CURSOR
	)
)

(instance arrowCursor of Cursor
	(properties
		view ARROW_CURSOR
	)
)

(instance useCursor of Cursor
	(properties
		view ARROW_CURSOR
	)
)

(instance theWaitCursor of Cursor
	(properties
		view HAND_CURSOR
	)
)

(instance gk2Messager of Messager
	
	(method (findTalker who &tmp theTalker)
		(switch who
			(else  narrator)
		)
		(if (= theTalker narrator)
			(return)
		else
			(super findTalker: who)
		)
	)
)
