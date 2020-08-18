;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include "64994.shm")
(use PQEgo)
(use SwatSaveDlg)
(use SwatInterface)
(use SwatRestoreDlg)
(use ControlLED)
(use Styler)
(use DButton)
(use DText)
(use Plane)
(use String)
(use Print)
(use Messager)
(use Talker)
(use PolyPath)
(use Polygon)
(use Cursor)
(use Grooper)
(use Sound)
(use Save)
(use Game)
(use System)

(public
	Swat 0
	TestSpeed 1
	InterfacePlane 2
	Btst 3
	Bset 4
	Bclr 5
	InventoryPlane 6
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
	screenWidth =  320
	screenHeight =  200
	lastScreenX =  319
	lastScreenY =  199
	theGrooper
	dongle =  1234
	debugging
	global103
	memoryAmount
	numVoices
	theInterfacePlane
	theInterfaceCast
	global108
	theInterface
	global110 =  1
	theHotSpot
	hotSpotList
	hotSpotCursor
	global114
	theInventoryPlane
	theInventoryCast
	invCursor
	saveDirFile
	global119
	handSigCursor
	doorEntryCursor
	global122
	global123
	global124
	gScript
	theMusic
	soundFx
	activeRobot
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
	gameFlags
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
	global250 =  -1
)
(procedure (TestSpeed &tmp [temp0 5])
)

(procedure (Btst flagEnum)
	(return
		(&
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bset flagEnum &tmp oldState)
	(= oldState (Btst flagEnum 1))
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
	(return oldState)
)

(procedure (Bclr flagEnum &tmp oldState)
	(= oldState (Btst flagEnum))
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
	(return oldState)
)

(procedure (CaptureBMP &tmp bmpFile temp1 temp2 temp3 temp4 temp5 temp6 temp7)
	(= temp6 0)
	(= temp1 (String format: {%03d.BMP} curRoomNum))
	(= bmpFile (String new: 13))
	(= temp3 (String new: 60))
	(= temp4 (String format: {%d} curRoomNum))
	(Font 1 640 480)
	(if
		(Print
			addTitle: {Output File Name:}
			addEdit: bmpFile 12 0 0 temp1
			init:
		)
		(FrameOut)
		(Dummy (bmpFile data?))
		(if (FileIO FileExists (bmpFile data?))
			(Printf {%03d.BMP SUCCESSFULLY CREATED} curRoomNum)
		else
			(Printf {ERROR CREATING %03d.BMP} curRoomNum)
		)
	)
	(FrameOut)
)

(instance BackMusic of Sound)

(instance FxSound of Sound)

(instance InterfacePlane of Plane)

(instance InterfaceCast of Cast)

(instance InventoryPlane of Plane
	
	(method (onMe theObjOrX theY &tmp oX oY)
		(if (== argc 1)
			(= oX (theObjOrX x?))
			(= oY (theObjOrX y?))
		else
			(= oX theObjOrX)
			(= oY theY)
		)
		(return
			(if
				(and
					(<= inLeft oX)
					(<= oX inRight)
					(<= inTop oY)
				)
				(<= oY inBottom)
			else
				FALSE
			)
		)
	)
)

(instance InventoryCast of Cast)

(instance theWaitCursor of Cursor
	(properties
		view INVIS_CURSOR
	)
)

(instance theNormalCursor of Cursor
	(properties
		view INVIS_CURSOR
	)
)

(instance theInvCursor of Cursor
	(properties
		view ARROW_CURSOR
	)
)

(instance theHandSigCursor of Cursor
	(properties
		view ARROW_CURSOR
	)
)

(instance theDoorEntryCursor of Cursor
	(properties
		view ARROW_CURSOR
	)
)

(instance PoliceQuestEgo of PQEgo)

(instance theHotspotCursor of Cursor)

(instance theHotspotList of Set)

(class Swat of Game
	(properties
		isHandsOn FALSE
		currentSpeed 6
		oldCurs 0
		speedRating 0
	)
	
	(method (init &tmp [temp0 9] versionFile [temp10 2])
		(ScriptID SIGHT)
		DText
		DButton
		Print
		Polygon
		PolyPath
		(= screenWidth 320)
		(= screenHeight 200)
		(super init:)
		((ScriptID 901 0) doit:)
		(Styler init: changeDivisions: 2)
		(= ftrInitializer SwatFtrInitializer)
		((= theMusic BackMusic)
			owner: self
			flags: mNOPAUSE
			init:
		)
		((= soundFx FxSound)
			flags: (| mNOPAUSE mLOAD_AUDIO)
			owner: self
			init:
		)
		(= systemPlane (Plane new:))
		((= theInterfaceCast InterfaceCast) add:)
		((= theInterfacePlane InterfacePlane)
			init: 0 149 319 189
			priority: 2
			addCast: theInterfaceCast
		)
		(theInterfaceCast plane: theInterfacePlane)
		(= theInterface SwatInterface)
		((= theInventoryCast InventoryCast) add:)
		((= theInventoryPlane InventoryPlane)
			init:
				(+ (theInterfacePlane left:) 53)
				(+ (theInterfacePlane top?) 8)
				(+ (theInterfacePlane left:) 262)
				(+ (theInterfacePlane top?) 33)
			priority: 3
			picture: -2
			addCast: theInventoryCast
		)
		(theInventoryCast plane: theInventoryPlane)
		((ScriptID 910) doit:)
		(theInterface setInvSet:)
		((= saveDirFile (String new:))
			format: {%s} (curSaveDir data?)
		)
		(= version {xx.yyy.zzz})
		(if (!= (= versionFile (FileIO FileOpen {version} 1)) -1)
			(FileIO FileFGets version 11 versionFile)
		)
		(FileIO FileClose versionFile)
		(= ego PQEgo)
		(= theCursor theNormalCursor)
		(= normalCursor theNormalCursor)
		(= waitCursor theWaitCursor)
		(= invCursor theInvCursor)
		(= oldCurs normalCursor)
		(= handSigCursor theHandSigCursor)
		(= doorEntryCursor theDoorEntryCursor)
		(= hotSpotCursor theHotspotCursor)
		(= hotSpotList (theHotspotList add: yourself:))
		(= theGrooper GradualLooper)
		(= doVerbCode pqDoVerbCode)
		(= approachCode pqApproachCode)
		(= messager pqMessager)
		(= narrator Narrator)
		(= isHandsOn FALSE)
		(= memoryAmount (MemoryInfo MemLargest))
		(= speedRating (TestSpeed))
		(= debugging TRUE)
		(self newRoom: 8)
	)
	
	(method (play)
		(= theGame self)
		(= curSaveDir (String new:))
		(= sysLogPath (String new:))
		(curSaveDir data: (Save 0 2))
		(= normalCursor theNormalCursor)
		(= waitCursor theWaitCursor)
		(self handsOff: init:)
		(while (not quit)
			(self doit:)
		)
		((Styler pFadeArray?) dispose:)
	)
	
	(method (replay &tmp theStyle aPlane aList obj node1 node2 node3 [temp7 3])
		(FrameOut)
		(= node1 (List LFirstNode (planes elements?)))
		(while node1
			(planes nextNode: (List LNextNode node1))
			(= aPlane (List LNodeValue node1))
			(AddPlane aPlane)
			(= node2 (List LFirstNode ((aPlane casts?) elements?)))
			(while node2
				((aPlane casts?) nextNode: (List LNextNode node2))
				(= aList (List LNodeValue node2))
				(= node3 (List LFirstNode (aList elements?)))
				(while node3
					(aList nextNode: (List LNextNode node3))
					(if (& ((= obj (List LNodeValue node3)) -info-?) IN_SILIST)
						(AddScreenItem obj)
					)
					(= node3 (aList nextNode?))
				)
				(= node2 ((aPlane casts?) nextNode?))
			)
			(= node1 (planes nextNode?))
		)
		(if lastEvent
			(lastEvent dispose:)
		)
		(theGame setCursor: waitCursor TRUE)
		(= theStyle
			(if (not (OneOf (curRoom style?) -1 SHOW_SCROLL_LEFT SHOW_SCROLL_RIGHT SHOW_SCROLL_UP SHOW_SCROLL_DOWN))
				(curRoom style?)
			else
				SHOW_PLAIN
			)
		)
		(cond 
			((and (not (user canControl:)) (not (user canInput:)))
				(theGame setCursor: waitCursor)
			)
			((and theIconBar (theIconBar curIcon?))
				(theGame setCursor: (theIconBar getCursor:))
			)
			(else
				(theGame setCursor: normalCursor)
			)
		)
		(StatusLine doit:)
		(DoSound SndRestore)
		(Sound pause: FALSE)
		(= tickOffset (- gameTime (GetTime)))
		(repeat
			(if quit (break))
			(self doit:)
		)
	)
	
	(method (newRoom n &tmp temp0)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(super startRoom: roomNum)
		(RemapColors RemapByPct 236 90)
		(RemapColors RemapByPct 237 80)
		(RemapColors RemapByPct 238 70)
		(RemapColors RemapByPct 239 60)
		(RemapColors RemapByPct 240 50)
		(RemapColors RemapByPct 241 40)
		(RemapColors RemapByPct 242 30)
		(RemapColors RemapByPct 243 20)
		(RemapColors RemapByPct 244 10)
	)
	
	(method (restore theSave &tmp num oldCur buf1 buf2 str aPlane aList obj bit node1 node2 node3)
		(= buf1 (String new:))
		(= buf2 (String new:))
		(= str (String new:))
		(if (not (FileIO FileValidPath (String StrGetData curSaveDir)))
			(Message MsgGet GAME N_INVALID_DIR NULL NULL 1 (buf1 data?))
			(str format: buf1 curSaveDir)
			(Print
				font: 999
				fore: 0
				back: (Palette PalMatch 127 127 127)
				addText: str
				addButtonBM: -1 0 0 1 {OK} 0 30
				init:
			)
			(GetDirectory curSaveDir)
		)
		(Load RES_FONT smallFont)
		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)
		(if
			(!=
				(= num
					(if argc theSave else (SwatRestoreDlg doit: &rest))
				)
				-1
			)
			(= node1 (List LNextNode (planes elements?)))
			(while node1
				(planes nextNode: (List LNextNode node1))
				(= aPlane (List LNodeValue node1))
				(= node2 (List LFirstNode ((aPlane casts?) elements?)))
				(while node2
					((aPlane casts?) nextNode: (List LNextNode node2))
					(= aList (List LNodeValue node2))
					(= node3 (List LFirstNode (aList elements?)))
					(while node3
						(aList nextNode: (List LNextNode node3))
						(if
							(= bit
								(& ((= obj (List LNodeValue node3)) -info-?) IN_SILIST)
							)
							(DeleteScreenItem obj)
							(obj -info-: (| (obj -info-?) bit))
						)
						(= node3 (aList nextNode?))
					)
					(= node2 ((aPlane casts?) nextNode?))
				)
				(DeletePlane aPlane)
				(= node1 (planes nextNode?))
			)
			(self setCursor: waitCursor TRUE)
			(if (Save 0 SGCheckSaveGame name num (String StrGetData version))
				(self getDisc: (CD GetSaveCD))
				(Save 0 SGRestore name num version)
			else
				(= node1 (List LFirstNode (planes elements?)))
				(while node1
					(planes nextNode: (List LNextNode node1))
					(= aPlane (List LNodeValue node1))
					(AddPlane aPlane)
					(= node2 (List LFirstNode ((aPlane casts?) elements?)))
					(while node2
						((aPlane casts?) nextNode: (List LNextNode node2))
						(= aList (List LNodeValue node2))
						(= node3 (List LFirstNode (aList elements?)))
						(while node3
							(aList nextNode: (List LNextNode node3))
							(if (& ((= obj (List LNodeValue node3)) -info-?) IN_SILIST)
								(AddScreenItem obj)
							)
							(= node3 (aList nextNode?))
						)
						(= node2 ((aPlane casts?) nextNode?))
					)
					(= node1 (planes nextNode?))
				)
				(Message MsgGet GAME N_WRONG_INTERP NULL NULL 1 (buf1 data?))
				(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 (buf2 data?))
				(Print
					font: 999
					fore: 0
					back: (Palette PalMatch 127 127 127)
					addText: (buf1 data?)
					addButtonBM: -1 0 0 1 (buf2 data?) 0 40
					init:
				)
				(self setCursor: oldCur (HaveMouse))
			)
		)
		(Sound pause: FALSE)
		(DisposeScript 909)
		(buf1 dispose:)
		(buf2 dispose:)
		(str dispose:)
	)
	
	(method (save &tmp comment num oldCur buf1 buf2 str temp6)
		(= comment (String new:))
		(if (not (FileIO FileValidPath (String StrGetData curSaveDir)))
			(= str (String new:))
			(= buf1 (String new:))
			(Message MsgGet GAME N_INVALID_DIR NULL NULL 1 (buf1 data?))
			(str format: buf1 curSaveDir)
			(Print
				font: 999
				fore: 0
				back: (Palette PalMatch 127 127 127)
				addText: str
				addButtonBM: -1 0 0 1 {OK} 0 30
				init:
			)
			(GetDirectory curSaveDir)
			(str dispose:)
			(buf1 dispose:)
		)
		(Load RES_FONT smallFont)
		(= oldCur (self setCursor: normalCursor))
		(Sound pause: TRUE)
		(= num (SwatSaveDlg doit: comment))
		(if (theGame darkCast:)
			(theGame darkScreen: 0)
		)
		(switch num
			(-1)
			(-2
				(= buf1 (String new:))
				(= buf2 (String new:))
				(Message MsgGet GAME N_CAT_READ_ERROR NULL NULL 1 (buf1 data?))
				(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 (buf2 data?))
				(if
					(Print
						font: 999
						fore: 0
						back: (Palette PalLoad 127 127 127)
						addText: buf1
						addButtonBM: SAVE 0 0 0 buf2 0 40
						addButtonBM: SAVE 2 0 1 {Change Dir} 55 40
						init:
					)
					(GetDirectory curSaveDir)
				)
				(buf1 dispose:)
				(buf2 dispose:)
			)
			(else 
				(if numCD
					(self getDisc: numCD)
				)
				(= oldCur (self setCursor: waitCursor TRUE))
				(if
					(not
						(Save 0 SGSave name num (comment data?) (String StrGetData version))
					)
					(= buf1 (String new:))
					(= buf2 (String new:))
					(Message MsgGet GAME N_SAVE_ERROR NULL NULL 1 (buf1 data?))
					(Message MsgGet GAME N_OK_BUTTON NULL NULL 1 (buf2 data?))
					(Print
						font: 999
						fore: 0
						back: (Palette PalLoad 127 127 127)
						addText: buf1
						addButtonBM: -1 0 0 1 buf2 0 40
						init:
					)
					(buf1 dispose:)
					(buf2 dispose:)
				)
				(self setCursor: oldCur (HaveMouse))
			)
		)
		(Sound pause: FALSE)
		(comment dispose:)
	)
	
	(method (handleEvent event)
		(if (== (event type?) keyDown)
			(if (OneOf (event message?) ESC `@x `^q)
				(= quit TRUE)
			)
			(if (== (event message?) `@b)
				(CaptureBMP)
			)
		)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((user canControl:)
					(if (and script (script handleEvent: event))
						(return TRUE)
					else
						(if (& (event type?) userEvent) (self pragmaFail:))
						(return TRUE)
					)
				)
			)
		)
	)
	
	(method (masterVolume newVol)
		(if argc
			(DoAudio AudVolume (* newVol 9))
			(DoSound SndMasterVol newVol)
		else
			(DoSound SndMasterVol)
		)
	)
	
	(method (handsOff)
		(if isHandsOn
			(= isHandsOn FALSE)
			(user canControl: FALSE canInput: FALSE)
			(= oldCurs (theGame setCursor: waitCursor TRUE))
		)
	)
	
	(method (handsOn)
	)
	
	(method (queryQuit &tmp temp0)
		(= quit TRUE)
	)
	
	(method (controlPanel &tmp temp0)
		(= temp0 (proc913_0))
		(DisposeScript 913)
		(if temp0
			(switch temp0
				(1
					(theGame panelObj: theGame panelSelector: #save)
				)
				(2
					(theGame panelObj: theGame panelSelector: #restore)
				)
			)
		)
	)
)

(instance pqDoVerbCode of Code
	
	(method (doit)
		(theGame pragmaFail:)
	)
)

(instance pqApproachCode of Code
	
	(method (doit)
		(return -1)
	)
)

(instance pqMessager of Messager
	
	(method (findTalker &tmp theTalker)
		(return narrator)
	)
)

(instance doUpdateCode of Code
	
	(method (doit obj)
		(if (not (& (obj signal?) viewHidden))
			(UpdateScreenItem obj)
		)
	)
)

(instance SwatFtrInitializer of Code
	(properties)
	
	(method (doit obj)
		(if (obj respondsTo: #signal)
			(obj signal: (| (obj signal?) (| ignrHrz ignrAct skipCheck canUpdate)))
		)
		(obj sightAngle: 360)
	)
)
