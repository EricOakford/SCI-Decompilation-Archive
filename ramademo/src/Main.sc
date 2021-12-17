;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use HotCursor)
(use RamaInterface)
(use Styler)
(use Plane)
(use String)
(use Array)
(use Print)
(use WalkTalk)
(use Ego)
(use Sound)
(use Save)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Rama 0
	proc0_1 1
	proc0_2 2
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
	debugging
	dongle =  1234
	global102
	global103
	global104
	global105
	global106
	global107
	global108 =  160
	global109 =  153
	gRamaInterface_2
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
	global127 =  200
	global128
	global129
	gTopPlane
	global131
	gGNewStr
	global133
	gPlane_2
	global135
	gRamaInterface
	global137 =  1
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
	global152 =  2
	global153
	global154 =  5
	global155
	global156 =  2
	global157
	global158
	global159
	global160
	global161
	global162 =  1139
	global163
	global164
	gGRobot
	global166 =  -1
	global167 =  -1
	global168 =  -1
	global169 =  -1
	global170 =  -1
	global171 =  -1
	global172
	gPrefFileReadWord
	gPrefFileReadWord_2
	gPrefFileReadWord_3
	gRoomCast
	global177
	gRobot
	gX
	gY
	gWalkieTalkieSetNowSeen
	gPriority
	gCaller
	gHoldOnLastCel
	gPlane_4
	gClient
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
)

;EO: The procedures contained duplicate code from the game's init:.
;Since this prevents compilation, the code has been removed.
(procedure (proc0_1 &tmp temp0 temp1)
)

(procedure (proc0_2 &tmp temp0 temp1)
)

(class Rama of Game
	(properties
		oldCursor 0
	)
	
	(method (init &tmp temp0 temp1)
		(= screenWidth 640)
		(= screenHeight 480)
		(super init:)
		(= _detailLevel 0)
		(thePlane
			setRect: 0 0 639 479
			setInsetRect: 0 0 639 479
			priority: 2
		)
		(= systemPlane Plane)
		(= ego (Ego new:))
		(= temp0 (String newWith: 128 curSaveDir))
		(= curSaveDir (SaveGame SGGetSaveDir))
		(= gGNewStr (String format: {%s} curSaveDir))
		(= ftrInitializer RamaFtrInitializer)
		(temp0 cat: {10.scr})
		(if (FileIO FileExists (temp0 data?))
			(= debugging 1)
		else
			(= debugging 0)
		)
		(temp0 dispose:)
		(if (== (Platform) 2) (= global105 1))
		(Font 1 630 450)
		(= userFont 2207)
		(= smallFont 2207)
		(Print fore: 0 back: 255)
		(Print font: userFont)
		(User alterEgo: ego canControl: 1 canInput: 0)
		(Lock 128 600 1)
		(Lock 143 90 1)
		(Lock 128 201 1)
		(Lock 128 202 1)
		(Lock 128 203 1)
		(self newGame:)
	)
	
	(method (play)
		(super play: &rest)
		(gGNewStr dispose:)
	)
	
	(method (replay &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6)
		(asm
			_line_   474
			_file_   {filename}
			_line_   480
			pushi    2
			pushi    3
			pushi    #elements
			pushi    0
			lag      planes
			send     4
			push    
			callk    KList,  4
			sat      temp4
code_0c65:
			_line_   481
			lat      temp4
			bnt      code_0d77
			_line_   482
			_line_   483
			pushi    #nextNode
			pushi    1
			pushi    2
			pushi    6
			push    
			callk    KList,  4
			push    
			lag      planes
			send     6
			_line_   484
			pushi    2
			pushi    8
			lst      temp4
			callk    KList,  4
			sat      temp1
			_line_   485
			pushi    1
			push    
			callk    AddPlane,  2
			_line_   488
			pushi    2
			pushi    3
			pushi    #elements
			pushi    0
			pushi    #casts
			pushi    0
			lat      temp1
			send     4
			send     4
			push    
			callk    KList,  4
			sat      temp5
code_0cb8:
			_line_   489
			lat      temp5
			bnt      code_0d69
			_line_   490
			_line_   491
			pushi    #nextNode
			pushi    1
			pushi    2
			pushi    6
			push    
			callk    KList,  4
			push    
			pushi    #casts
			pushi    0
			lat      temp1
			send     4
			send     6
			_line_   492
			pushi    2
			pushi    8
			lst      temp5
			callk    KList,  4
			sat      temp2
			_line_   495
			pushi    2
			pushi    3
			pushi    #elements
			pushi    0
			send     4
			push    
			callk    KList,  4
			sat      temp6
code_0d00:
			_line_   496
			lat      temp6
			bnt      code_0d54
			_line_   497
			_line_   498
			pushi    #nextNode
			pushi    1
			pushi    2
			pushi    6
			push    
			callk    KList,  4
			push    
			lat      temp2
			send     6
			_line_   499
			pushi    2
			pushi    8
			lst      temp6
			callk    KList,  4
			sat      temp3
			_line_   500
			pushi    #-info-
			pushi    0
			send     4
			push    
			ldi      16
			and     
			bnt      code_0d47
			_line_   501
			pushi    1
			lst      temp3
			callk    AddScreenItem,  2
code_0d47:
			pushi    #nextNode
			pushi    0
			lat      temp2
			send     4
			sat      temp6
			jmp      code_0d00
code_0d54:
			pushi    #nextNode
			pushi    0
			pushi    #casts
			pushi    0
			lat      temp1
			send     4
			send     4
			sat      temp5
			jmp      code_0cb8
code_0d69:
			pushi    #nextNode
			pushi    0
			lag      planes
			send     4
			sat      temp4
			jmp      code_0c65
code_0d77:
			_line_   508
			lag      lastEvent
			bnt      code_0d88
			_line_   509
			pushi    #dispose
			pushi    0
			send     4
code_0d88:
			_line_   513
			pushi    #setCursor
			pushi    2
			lsg      waitCursor
			pushi    1
			lag      theGame
			send     8
			_line_   514
			_line_   515
			lag      curRoom
			bnt      code_0dcb
			pushi    6
			pushi    #style
			pushi    0
			send     4
			push    
			pushi    65535
			pushi    16
			pushi    17
			pushi    18
			pushi    19
			calle    OneOf,  12
			not     
			bnt      code_0dcb
			_line_   516
			pushi    #style
			pushi    0
			lag      curRoom
			send     4
			jmp      code_0dd3
code_0dcb:
			_line_   517
			_line_   518
			ldi      0
code_0dd3:
			sat      temp0
			_line_   523
			_line_   525
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			not     
			bnt      code_0e17
			_line_   526
			pushi    #canInput
			pushi    0
			lag      user
			send     4
			not     
			bnt      code_0e17
			_line_   527
			_line_   528
			pushi    #setCursor
			pushi    1
			lofsa    ramanCursor
			push    
			lag      theGame
			send     6
			_line_   529
			pushi    #handsOff
			pushi    0
			lag      theGame
			send     4
			jmp      code_0e2a
code_0e17:
			_line_   531
			_line_   532
			pushi    #setCursor
			pushi    1
			lofsa    ramanCursor
			push    
			lag      theGame
			send     6
code_0e2a:
			_line_   537
			pushi    #doit
			pushi    0
			class    StatusLine
			send     4
			_line_   540
			pushi    1
			pushi    2
			callk    DoSound,  2
			_line_   541
			pushi    #pause
			pushi    1
			pushi    0
			class    Sound
			send     6
			_line_   544
			lsg      gameTime
			pushi    0
			callk    GetTime,  0
			sub     
			sag      tickOffset
			_line_   547
			pushi    #saveRobot
			pushi    1
			pushi    0
			self     6
			_line_   550
code_0e66:
			lag      quit
			not     
			bnt      code_0e76
			_line_   551
			pushi    #doit
			pushi    0
			self     4
			jmp      code_0e66
code_0e76:
			_line_   554
			pushi    #dispose
			pushi    0
			lag      gGNewStr
			send     4
			_line_   555
			ret     
		)
	)
	
	(method (newRoom n &tmp [temp0 5] temp5 curRoomExitStyle)
		(if debugging ((ScriptID 10 0) dispose:))
		(if (talkers size:) (messager cue: 1))
		(if
			(and
				curRoom
				(!= (= curRoomExitStyle (curRoom exitStyle?)) -1)
			)
			(Styler
				plane: (curRoom plane?)
				back:
					(cond 
						((& curRoomExitStyle $0100) 0)
						((& curRoomExitStyle $0200) 7)
						(else 0)
					)
				style: (& curRoomExitStyle $00ff)
				doit:
			)
			(FrameOut)
			(Styler back: -1)
		)
		(PalCycle 4)
		(RemapColors 0)
		(features eachElementDo: #dispose release:)
		(cast eachElementDo: #dispose)
		(timers eachElementDo: #delete)
		(regions eachElementDo: #perform Ramaroom_DNKR release:)
		(theDoits release:)
		(= prevRoomNum curRoomNum)
		(= curRoomNum n)
		(= newRoomNum n)
		(DisposeScript 100)
		(if newRoomNum
			(NewRoom newRoomNum)
			(self startRoom: curRoomNum)
		)
		(if global133 (global133 init:))
		(while ((= temp5 (Event new: 3)) type?)
			(temp5 dispose:)
		)
		(temp5 dispose:)
	)
	
	(method (restore &tmp temp0 temp1 newStr newStr_2 newStr_3 temp5 temp6 temp7 temp8 planesNextNode temp10 temp11)
		(= newStr (String new:))
		(= newStr_2 (String new:))
		(= newStr_3 (String new:))
		(if (not (FileIO 19 (KArray 9 curSaveDir)))
			(newStr_3 format: newStr curSaveDir)
			(Print
				font: 999
				fore: 0
				back: (Palette PalMatch 127 127 127)
				addText: newStr_3
				addButtonBM: -1 0 0 1 {OK} 0 30
				init:
			)
		)
		(Load RES_FONT smallFont)
		(ScriptID 64990)
		(self saveRobot: 1)
		(= temp1 (self setCursor: normalCursor))
		(Sound pause: 1)
		(if (!= (= temp0 (Restore doit: &rest)) -1)
			(= planesNextNode (KList 3 (planes elements?)))
			(while planesNextNode
				(planes nextNode: (KList 6 planesNextNode))
				(= temp5 (KList 8 planesNextNode))
				(= temp10 (KList 3 ((temp5 casts?) elements?)))
				(while temp10
					((temp5 casts?) nextNode: (KList 6 temp10))
					(= temp6 (KList 8 temp10))
					(= temp11 (KList 3 (temp6 elements?)))
					(while temp11
						(temp6 nextNode: (KList 6 temp11))
						(if
							(= temp8
								(& ((= temp7 (KList 8 temp11)) -info-?) $0010)
							)
							(DeleteScreenItem temp7)
							(temp7 -info-: (| (temp7 -info-?) temp8))
						)
						(= temp11 (temp6 nextNode?))
					)
					(= temp10 ((temp5 casts?) nextNode?))
				)
				(DeletePlane temp5)
				(= planesNextNode (planes nextNode?))
			)
			(self setCursor: waitCursor 1)
			(if (SaveGame 3 name temp0 (KArray 9 version))
				(self getDisc: (CD 1))
				(SaveGame 1 name temp0 version)
			else
				(= planesNextNode (KList 3 (planes elements?)))
				(while planesNextNode
					(planes nextNode: (KList 6 planesNextNode))
					(= temp5 (KList 8 planesNextNode))
					(AddPlane temp5)
					(= temp10 (KList 3 ((temp5 casts?) elements?)))
					(while temp10
						((temp5 casts?) nextNode: (KList 6 temp10))
						(= temp6 (KList 8 temp10))
						(= temp11 (KList 3 (temp6 elements?)))
						(while temp11
							(temp6 nextNode: (KList 6 temp11))
							(if (& ((= temp7 (KList 8 temp11)) -info-?) $0010)
								(AddScreenItem temp7)
							)
							(= temp11 (temp6 nextNode?))
						)
						(= temp10 ((temp5 casts?) nextNode?))
					)
					(= planesNextNode (planes nextNode?))
				)
				(Print
					font: 999
					fore: 0
					back: (Palette PalMatch 127 127 127)
					addText: (newStr data?)
					addButtonBM: -1 0 0 1 (newStr_2 data?) 0 40
					init:
				)
			)
		)
		(self setCursor: temp1 (HaveMouse))
		(Sound pause: 0)
		(self saveRobot: 0)
		(newStr dispose:)
		(newStr_2 dispose:)
		(newStr_3 dispose:)
	)
	
	(method (save &tmp newStr temp1 temp2 newStr_3 newStr_4 newStr_2)
		(= newStr (String new:))
		(if (not (FileIO 19 (KArray 9 curSaveDir)))
			(= newStr_2 (String new:))
			(= newStr_3 (String new:))
			(newStr_2 format: newStr_3 curSaveDir)
			(Print
				font: 999
				fore: 0
				back: (Palette PalMatch 127 127 127)
				addText: newStr_2
				addButtonBM: -1 0 0 1 {OK} 0 30
				init:
			)
			(newStr_2 dispose:)
			(newStr_3 dispose:)
		)
		(Load RES_FONT smallFont)
		(ScriptID 64990)
		(= temp2 (self setCursor: normalCursor))
		(Sound pause: 1)
		(self saveRobot: 1)
		(switch (= temp1 (Save doit: newStr))
			(-1)
			(-2
				(= newStr_3 (String new:))
				(= newStr_4 (String new:))
				(Print
					font: 999
					fore: 0
					back: (Palette PalMatch 127 127 127)
					addText: newStr_3
					addButtonBM: -546 0 0 0 newStr_4 0 40
					addButtonBM: -546 2 0 1 {Change Dir} 55 40
					init:
				)
				(newStr_3 dispose:)
				(newStr_4 dispose:)
			)
			(else 
				(if numCD (self getDisc: numCD))
				(self setCursor: waitCursor 1)
				(if
					(not
						(SaveGame 0 name temp1 (newStr data?) (KArray 9 version))
					)
					(= newStr_3 (String new:))
					(= newStr_4 (String new:))
					(Print
						font: 999
						fore: 0
						back: (Palette PalMatch 127 127 127)
						addText: newStr_3
						addButtonBM: -1 0 0 1 newStr_4 0 40
						init:
					)
					(newStr_3 dispose:)
					(newStr_4 dispose:)
				)
			)
		)
		(self setCursor: temp2 (HaveMouse))
		(Sound pause: 0)
		(self saveRobot: 0)
		(newStr dispose:)
	)
	
	(method (handsOff)
		(super handsOff:)
		(if (theCursor isDirCursor:)
			(theCursor setNormalCursor:)
		)
		(theCursor hide:)
	)
	
	(method (handsOn)
		(super handsOn:)
		(theCursor show:)
	)
	
	(method (saveRobot param1 &tmp temp0)
		(cond 
			((or (not argc) param1)
				(if gRobot
					(if autoRobot
						(= temp0 (IntArray new: 4))
						(= gWalkieTalkieSetNowSeen
							(Max
								0
								(Min (Robot 11) (- (Robot 2 (temp0 data?)) 1))
							)
						)
						(temp0 dispose:)
						(autoRobot terminate: caller: 0 dispose: 0)
						(= autoRobot (= gGRobot 0))
					else
						(WalkieTalkie terminate:)
					)
				)
			)
			(gRobot
				(= gGRobot gRobot)
				(if global187
					(WalkieTalkie
						doRobot:
							gRobot
							gX
							gY
							gWalkieTalkieSetNowSeen
							gCaller
							gClient
							gPriority
							gHoldOnLastCel
					)
				else
					(WalkieTalkie
						showFrame: gRobot gWalkieTalkieSetNowSeen gX gY gPriority gPlane_4
					)
				)
			)
		)
	)
	
	(method (newGame)
		(thePlane
			setRect: 0 10 1024 768
			setInsetRect: 0 10 639 479
		)
		(RamaInterface init:)
		(= gRamaInterface RamaInterface)
		(= curRoomNum 0)
		(= newRoomNum 0)
		(Lock 129 60 1)
		(ramanCursor init: ramanNormalCursor)
		(= oldCursor (theGame setCursor: ramanCursor))
		(= newRoomNum 12)
	)
	
	(method (autoSave param1 param2 &tmp temp0 temp1 temp2 theCurSaveDir)
		(= theCurSaveDir curSaveDir)
		(= curSaveDir gGNewStr)
		(= temp1 (if (< argc 2) 0 else param2))
		(cond 
			((or (not argc) param1)
				(= temp0 (String with: {Rama AutoSave SaveGame}))
				(self saveRobot: 1)
				(if numCD (self getDisc: numCD))
				(= temp2 (self setCursor: waitCursor 1))
				(SaveGame 0 {auto} temp1 (temp0 data?) (KArray 9 version))
				(Sound pause: 0)
				(self saveRobot: 0)
				(self setCursor: temp2 (HaveMouse))
				(temp0 dispose:)
			)
			((SaveGame 3 {auto} temp1 (KArray 9 version)) (SaveGame 1 {auto} temp1 version))
		)
		(= curSaveDir theCurSaveDir)
	)
)

(instance RamaFtrInitializer of Code
	(properties)
	
	(method (doit param1)
		(if (and curRoomNum (not (param1 plane?)))
			(param1 plane: gTopPlane)
		)
	)
)

(instance ramanNormalCursor of View
	(properties
		view 600
		cel 9
	)
)

(instance ramanCursor of HotCursor
	(properties)
)

(instance Ramaroom_DNKR of Code
	(properties
		name "Ramaroom DNKR"
	)
	
	(method (doit param1)
		(if (not (param1 keep?)) (param1 dispose:))
	)
)
