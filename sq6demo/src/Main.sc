;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "0.shm")
(use SQInvItem)
(use SQIconItem)
(use DButton)
(use Plane)
(use String)
(use Print)
(use Dialog)
(use Messager)
(use Talker)
(use PMouse)
(use IconBar)
(use PolyPath)
(use Polygon)
(use StopWalk)
(use Cursor)
(use Timer)
(use Sound)
(use Game)
(use Invent)
(use User)
(use System)

(public
	SQ6 0
	Bset 1
	Bclr 2
	Btst 3
	Face 4
	proc0_5 5
	proc0_6 6
	proc0_7 7
	RandTime 8
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
	egoBlindSpot
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
	screenWidth =  320
	screenHeight =  200
	lastScreenX =  319
	lastScreenY =  199
	debugging
	gameCode =  1234
	global102
	numVoices
	theMusic
	theMusic2
	isWindows
	theWalkCursor
	theLookCursor
	theDoCursor
	theTalkCursor
	theDefaultCursor
	exitCursor
	exitNCursor
	exitSCursor
	exitECursor
	exitWCursor
	exitNoneCursor
	theHelpCursor
	selectedRoom
	global120
	oldCurIcon
	showTitle
	gGSQIconbarCurIcon
	targetAngles
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
	egoSpeed
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
	endGameFlags
	startingRoom
)
(procedure (Bset flagEnum)
	(|= [gameFlags (/ flagEnum 16)] (>> $8000 (mod flagEnum 16)))
)

(procedure (Bclr flagEnum)
	(&= [gameFlags (/ flagEnum 16)] (~ (>> $8000 (mod flagEnum 16))))
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj)
	(= obj 0)
	(if actor2
		(= theX (+ (actor2 x?) (actor2 faceXOffset:)))
		(= theY (+ (actor2 y?) (actor2 faceYOffset:)))
		(if (== argc 3) (= obj both))
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4) (= obj whoToCue))
	)
	(= ang1To2
		(GetAngle
			(+ (actor1 x?) (actor1 faceXOffset:))
			(+ (actor1 y?) (actor1 faceYOffset:))
			theX
			theY
		)
	)
	(actor1 setHeading: ang1To2 obj)
)

(procedure (proc0_5 param1 param2 param3)
	(param1 at: param2 param3)
	(param1 at: (+ param2 1) (>> param3 $0008))
)

(procedure (proc0_6 param1 param2)
	(return
		(+
			(& (param1 at: param2) $00ff)
			(<< (param1 at: (+ param2 1)) $0008)
		)
	)
)

(procedure (proc0_7 param1 param2 param3 &tmp i temp1)
	(if (or (< argc 2) (== param2 0))
		(= param2 1)
	)
	(if (or (== argc 0) (== param1 1))
		(for ((= i 100)) (> i 0) ((-= i param2))
			(Palette PalIntensity 0 79 i)
			(Palette PalIntensity 87 256 i)
			(cast doit:)
			(FrameOut)
		)
	else
		(for ((= i 0)) (< i 100) ((+= i param2))
			(Palette PalIntensity 0 79 i)
			(Palette PalIntensity 87 256 i)
			(cast doit:)
			(FrameOut)
		)
	)
	(if (== param1 1)
		(Palette PalIntensity 0 79 0)
		(Palette PalIntensity 87 256 0)
	)
	(if (and (== argc 3) param3)
		(param3 cue:)
	)
)

(procedure (RandTime param1 param2)
	(return
		(+
			param1
			(mod
				(+ (* $6255 (GetTime)) $3619)
				(+ (- param2 param1) 1)
			)
		)
	)
)

(class SQSound of Sound
	(method (play callerOrVolume)
		(if argc
			(= client callerOrVolume)
		else
			(= client 0)
		)
		(if (not (sounds contains: self))
			(self init:)
		)
		(if (not loop)
			(= loop 1)
		)
		(DoSound SndPlay self 0)
	)
)

(instance gSound1 of SQSound
	(properties
		flags mNOPAUSE
	)
)

(instance gSound2 of SQSound
	(properties
		flags mNOPAUSE
	)
)

(class SQ6 of Game
	(method (init &tmp versionFile quitStr quitSeq)
		(while (Message MsgSize 0 N_QUITSTR NULL NULL quitSeq)
			(++ quitSeq)
		)
		(= quitStr (String new:))
		(Message MsgGet 0 N_QUITSTR NULL NULL
			(RandTime 1 (- quitSeq 1))
			(quitStr data?)
		)
		(SetQuitStr (quitStr data?))
		(quitStr dispose:)
		(Font FSetFontRes 640 480)
		Print
		DButton
		StopWalk
		Polygon
		PolyPath
		Timer
		IconBar
		Inventory
		Narrator
		(super init: &rest)
		(thePlane setRect: 0 0 319 138 priority: 4)
		((= altPolyList (List new:)) name: {altPolys} add:)
		(= msgType (| CD_MSG TEXT_MSG))
		(= ego (ScriptID SQ6EGO 0))
		(User
			alterEgo: ego
			canControl: TRUE
			canInput: FALSE
			mapKeyToDir: FALSE
		)
		(= systemPlane Plane)
		(= eatMice 6)
		(= doVerbCode sq6DVC)
		(= approachCode pApproachCode)
		(= pMouse PseudoMouse)
		(= messager sq6Messager)
		(= useSortedFeatures TRUE)
		(= numVoices (DoSound SndNumVoices))
		(= possibleScore 999)
		(= userFont 70)
		(= version {x.yyy.zzz})
		(= versionFile (FileIO FileOpen {version} 1))
		(FileIO FileFGets version 10 versionFile)
		(FileIO FileClose versionFile)
		(= egoSpeed 4)
		(= waitCursor theWaitCursor)
		(= normalCursor arrowCursor)
		(= theWalkCursor walkCursor)
		(= theDoCursor doCursor)
		(= theLookCursor lookCursor)
		(= theTalkCursor talkCursor)
		(= theHelpCursor helpCursor)
		(= exitCursor theExitCursor)
		(= exitNCursor theExitNCursor)
		(= exitSCursor theExitSCursor)
		(= exitECursor theExitECursor)
		(= exitWCursor theExitWCursor)
		(= exitNoneCursor theExitNoneCursor)
		(= theDefaultCursor defaultCursor)
		(self setCursor: waitCursor TRUE 160 120)
		(= mouseX 160)
		(= mouseY 80)
		((= theMusic gSound1) owner: self init:)
		((= theMusic2 gSound2) owner: self init:)
		(if (FileIO FileExists {classes})
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(if (== (Platform GetPlatType) PlatWin)	;EO: Platform was 4, but this is clearly GetPlatType, which is 0 in KERNEL.SH
			(= isWindows TRUE)
		)
		(SQIconbar init:)
		((ScriptID 32 0) init:)
		(SQInventory init:)
		(if debugging
			(= startingRoom 24)
		else
			(= startingRoom 100)
			(Bset fInIntro)
		)
		(self
			handsOffCode: sq6HandsOffCode
			handsOnCode: sq6HandsOnCode
			newRoom: 33
		)
		(Dialog mouseHiliting: TRUE)
		((= narrator Narrator)
			x: 15
			y: 160
			talkWidth: 225
			font: userFont
			fore: 82
			back: 0
		)
		(Print fore: 84 back: 86)
		(SetPalStyleRange 80 86)
	)
	
	(method (startRoom roomNum)
		(if pMouse
			(pMouse stop:)
		)
		(if debugging
			((ScriptID DEBUG 0) init:)
		)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(if (== (event type?) keyDown)
			(switch (event message?)
				(TAB
					(if (not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
						((theIconBar at: ICON_INVENTORY) select:)
						(event claimed: TRUE)
					)
				)
				(SHIFTTAB
					(if (not (& ((theIconBar at: ICON_INVENTORY) signal?) DISABLED))
						((theIconBar at: ICON_INVENTORY) select:)
						(event claimed: TRUE)
					)
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
				;EO: These have been commented out because the game is missing the required
				;save/restore dialog view, causing it to crash.
;;;				(`#5
;;;					(self save:)
;;;					(event claimed: TRUE)
;;;				)
;;;				(`#7
;;;					(self restore:)
;;;					(event claimed: TRUE)
;;;				)
				(`+
					(if (user controls?)
						(ego
							setSpeed: (= egoSpeed (Max 0 (- (ego moveSpeed?) 1)))
						)
					)
				)
				(`-
					(if (and (user controls?) (< egoSpeed 11))
						(ego setSpeed: (= egoSpeed (+ (ego moveSpeed?) 1)))
					)
				)
				(`=
					(if (user controls?)
						(ego setSpeed: 6)
					)
				)
			)
		)
		(cond 
			((event claimed?) TRUE)
			((and script (script handleEvent: event)) 1)
			((& (event type?) userEvent)
				(if (user canInput:)
					(sq6DVC doit: (event message?))
				else
					(theGame pragmaFail:)
				)
			)
		)
		(return (event claimed?))
	)
	
	(method (pragmaFail &tmp oldCur theTime)
		(if (not (if (user controls?) (user input?)))
			(= oldCur (self setCursor: theFailCursor))
			(for ((= theTime 0)) (< theTime 15000) ((++ theTime))
			)
			(self setCursor: oldCur)
		)
	)
	
	(method (showControls)
		((ScriptID SQ6CONTROLS 0) showSelf:)
	)
	
	(method (showAbout)
		(Prints
			{If you want to know MORE about SQ6,\n
			BUY THE GAME!}
		)
	)
	
	(method (masterMusicVolume newVol)
		(if argc
			(DoSound SndMasterVol newVol)
		else
			(DoSound SndMasterVol)
		)
	)
	
	(method (masterAudioVolume newVol)
		(if argc
			(DoAudio AudVolume newVol)
		else
			(DoAudio AudVolume)
		)
	)
	
	(method (muteMusic newVol)
		(if argc
			(DoSound SndMasterVol newVol)
		else
			(DoSound SndMasterVol 9)
		)
	)
)

(instance pApproachCode of Code
	(method (doit theVerb)
		(switch theVerb
			(V_LOOK $0001)
			(V_TALK $0002)
			(V_WALK $0004)
			(V_DO $0008)
			(7 $0010)
			(8 $0020)
			(9 $0040)
			(10 $0080)
			(6 $4000)
			(else  $8000)
		)
	)
)

(instance sq6DVC of Code
	(method (doit theVerb)
		(cond 
			((== theVerb V_LOOK)
				(messager say: NULL V_LOOK NULL (RandTime 1 3) 0 0)
			)
			((== theVerb V_DO)
				(messager say: NULL V_DO NULL (RandTime 1 3) 0 0)
			)
			((== theVerb V_TALK)
				(messager say: NULL V_TALK NULL (RandTime 1 3) 0 0)
			)
			((not (OneOf theVerb V_WALK V_LOOK V_DO V_TALK V_HELP 11 5 7 9 10 8))
				(messager say: NULL V_ITEM NULL (RandTime 1 3) 0 0)
			)
			(else
				(theGame pragmaFail:)
			)
		)
	)
)

(instance sq6HandsOffCode of Code
	(method (doit curX curY)
		(if (not oldCurIcon)
			(= oldCurIcon (theIconBar curIcon?))
		)
		(User canControl: FALSE canInput: FALSE)
		(theIconBar
			updateSettings:
			curIcon: (theIconBar at: ICON_CONTROL)
			disable:
				ICON_WALK
				ICON_LOOK
				ICON_DO
				ICON_TALK
				ICON_INVENTORY
				ICON_HELP
				ICON_CONTROL
		)
		(if
			(and
				(theIconBar curInvIcon?)
				(not (& ((theIconBar at: ICON_USE) signal?) DISABLED))
			)
			(theIconBar disable: ICON_USE)
		)
		(if argc
			(theGame setCursor: theWaitCursor TRUE curX curY)
		else
			(theGame setCursor: theWaitCursor TRUE)
		)
		(if ego
			(ego setMotion: 0)
		)
	)
)

(instance sq6HandsOnCode of Code
	(method (doit curX curY &tmp temp0)
		(User canControl: TRUE canInput: TRUE)
		(theIconBar enable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_INVENTORY
			ICON_HELP
			ICON_CONTROL
		)
		(if (theIconBar curInvIcon?)
			(theIconBar enable: ICON_USE)
			((theIconBar at: ICON_USE) highlight:)
		)
		(if oldCurIcon
			(theIconBar
				curIcon: oldCurIcon
				highlight: oldCurIcon
			)
		)
		(= oldCurIcon 0)
		(if argc
			(theGame
				setCursor: ((theIconBar curIcon?) getCursor:) TRUE curX curY
			)
		else
			(theGame
				setCursor: ((theIconBar curIcon?) getCursor:) TRUE
			)
		)
		(if ego
			(ego setSpeed: egoSpeed)
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

(instance walkCursor of Cursor
	(properties
		view 953
		loop 2
	)
)

(instance lookCursor of Cursor
	(properties
		view 953
		loop 1
	)
)

(instance doCursor of Cursor
	(properties
		view 953
	)
)

(instance talkCursor of Cursor
	(properties
		view 953
		loop 3
	)
)

(instance helpCursor of Cursor
	(properties
		view 953
		loop 4
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

(instance theFailCursor of Cursor
	(properties
		view 954
		loop 5
	)
)

(instance theExitCursor of Cursor
	(properties
		view 954
		loop 4
	)
)

(instance theExitSCursor of Cursor
	(properties
		view 954
		loop 3
	)
)

(instance theExitNCursor of Cursor
	(properties
		view 954
		loop 2
	)
)

(instance theExitECursor of Cursor
	(properties
		view 954
		loop 1
	)
)

(instance theExitWCursor of Cursor
	(properties
		view 954
	)
)

(instance theExitNoneCursor of Cursor
	(properties
		view 954
		loop 5
	)
)

(instance sq6Messager of Messager
	(method (dispose)
		(theGame setCursor: scratch)
		(= scratch 0)
		(super dispose: &rest)
	)
	
	(method (say)
		(= scratch theCursor)
		(theGame setCursor: waitCursor)
		(super say: &rest)
	)
	
	(method (sayRange)
		(= scratch theCursor)
		(theGame setCursor: waitCursor)
		(super sayRange: &rest)
	)
	
	(method (findTalker who &tmp theTalker)
		(if
			(= theTalker
				(switch who
					;These are all the same talker, Roger.
					(ROGER
						(= targetAngles dirStop)
						(ScriptID SQ6EGO ROGER)
					)
					(4
						(= targetAngles dirStop)
						(ScriptID SQ6EGO ROGER)
					)
					(7
						(= targetAngles dirN)
						(ScriptID SQ6EGO ROGER)
					)
					(5
						(= targetAngles dirNE)
						(ScriptID SQ6EGO ROGER)
					)
					(6
						(= targetAngles dirE)
						(ScriptID SQ6EGO ROGER)
					)
					(else  narrator)
				)
			)
			(return)
		else
			(super findTalker: who)
		)
	)
)
