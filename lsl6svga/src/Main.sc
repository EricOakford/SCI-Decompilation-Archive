;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include sci.sh)
(use n076)
(use fileScr)
(use LL6InvItem)
(use ButtonBar)
(use LarryTalker)
(use ScrollerWindow)
(use DButton)
(use DText)
(use Plane)
(use String)
(use Print)
(use Messager)
(use Talker)
(use Scaler)
(use IconBar)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Cursor)
(use Timer)
(use Grooper)
(use Sound)
(use File)
(use Game)
(use User)
(use Actor)
(use System)

(public
	LSL6 0
	controlSet 1
	proc0_2 2
	icon0 3
	icon1 4
	icon2 5
	icon3 6
	icon4 7
	icon5 8
	icon6 9
	icon7 10
	iconExit 11
	sTimer 12
	talkerSet 13
	dialSet 14
	globalSound1 15
	globalSound2 16
	scrollBar 17
	scrollBarUpIcon 18
	scrollBarDownIcon 19
	textScroller 20
	larry6Title 21
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
	screenWidth
	screenHeight
	lastScreenX
	lastScreenY
	global100
	global101 =  1234
	theMusic
	theMusic2
	numVoices
	global105
	global106
	global107
	global108
	global109
	global110
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
	gameFlags
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
	gGButtonBarCurIcon
	gGEgoCycleSpeed_2 =  6
	global168
	global169
	gLarryRoom
	global171 =  1
	global172
	global173
	global174
	global175
	global176 =  -1
	global177
	global178
	global179
	gGEgoX
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
	gGTheGameCursor
	global196
	global197
	global198
	global199
	global200
	global201
	controlPlane
	talkerPlane
	gGraphMenuBar
	global205
	curTalkerCast
	global207
	global208
	global209
	global210
	gGUserControls
	gGUserInput
	gGButtonBarGetCursor
	gNewStr_3
	gNewStr_4
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
)
(procedure (proc0_2)
	(if (cast contains: (ScriptID 825 1))
		((ScriptID 825 1) dispose:)
	)
	(if global205 (proc79_7))
	(if cuees
		(cuees eachElementDo: #dispose)
		(cuees release:)
	)
	(super newRoom: &rest)
	(if
		(not
			(if (or (Btst 53) (Btst 35) (Btst 23))
			else
				(OneOf prevRoomNum 100 110 120 130 140)
			)
		)
		((ScriptID 825 1)
			init:
			baseSetter: (ScriptID 825 3)
			setMotion: (ScriptID 825 0)
		)
	)
)

(instance globalSound1 of Sound
	(properties
		flags $0001
	)
)

(instance globalSound2 of Sound
	(properties
		flags $0001
	)
)

(instance scoreSfx of Sound
	(properties
		flags $0001
	)
)

(instance funFx of Sound
	(properties
		flags $0001
	)
)

(instance lKeyDownHandler of EventHandler
	(properties)
)

(instance lMouseDownHandler of EventHandler
	(properties)
)

(instance lDirectionHandler of EventHandler
	(properties)
)

(instance lWalkHandler of EventHandler
	(properties)
)

(instance controlSet of Cast
	(properties)
)

(instance talkerSet of Cast
	(properties)
)

(instance dialSet of Cast
	(properties)
)

(class LSL6 of Game
	(properties
		scratch 0
		script 0
		printLang 1
		_detailLevel 3
		panelObj 0
		panelSelector 0
		handsOffCode 0
		handsOnCode 0
		isHandsOff 0
		controlsVisible 0
	)
	
	(method (init &tmp [temp0 3])
		(ScriptID 64982)
		DText
		DButton
		Polygon
		PolyPath
		LarryNarrator
		Talker
		RandCycle
		Scaler
		Timer
		Grooper
		(= theCursor theGameCursor)
		(= normalCursor theArrowCursor)
		(= waitCursor theWaitCursor)
		(= msgType 3)
		((= systemPlane (Plane new:)) name: {systemPlane})
		(super init: &rest)
		(= userFont 2308)
		(= bigFont 4115)
		(= smallFont 2308)
		(= textSpeed 9)
		(= eatMice 2)
		(= global177 (Str new: 100))
		(= version (Str new: 20))
		(version open: 1 read: version 11 dispose:)
		(= messager larryMessager)
		(= handsOnCode larryHandsOn)
		(= handsOffCode larryHandsOff)
		(= narrator LarryNarrator)
		(= approachCode larryApproachCode)
		(= doVerbCode larryDoVerbCode)
		((= keyDownHandler lKeyDownHandler) add:)
		((= mouseDownHandler lMouseDownHandler) add:)
		((= directionHandler lDirectionHandler) add:)
		((= walkHandler lWalkHandler) add:)
		((ScriptID 71 0) init:)
		(DisposeScript 71)
		((= theIconBar ButtonBar)
			add:
				(icon0 setCursor: cIcon0 yourself:)
				(icon1 setCursor: cIcon1 yourself:)
				(icon2 setCursor: cIcon2 yourself:)
				(icon3 setCursor: cIcon3 yourself:)
				(icon4 setCursor: cIcon4 yourself:)
				(icon6 setCursor: cIcon5 yourself:)
				icon5
			x: 72
			y: 2
			eachElementDo: #mainView 981
			eachElementDo: #maskView 981
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor 27
			curIcon: icon0
			walkIconItem: icon0
			showInvAfter: icon6
			init:
			disable:
		)
		(if global100
			(SetCursor 200 70)
			(self newRoom: (__proc911_1 1))
		else
			(self newRoom: 100)
		)
	)
	
	(method (play)
		(= theGame self)
		(if (or (IsHiRes) (Platform 2))
			(= curSaveDir (Str new:))
			(= sysLogPath (Str new:))
			(curSaveDir data: (GetSaveDir))
			(self init:)
			(while (not quit)
				(self doit:)
			)
			(= global182 (Str new: 255))
			(if (not (Btst 97))
				(Message 0 0 7 0 0 (Random 1 15) (global182 data?))
			else
				(Message 0 0 7 0 0 16 (global182 data?))
			)
			(SetQuitStr (global182 data?))
		else
			(= global182 (Str new: 255))
			(Message 0 0 13 0 0 1 (global182 data?))
			(SetQuitStr (global182 data?))
		)
	)
	
	(method (replay &tmp temp0 temp1 temp2 temp3 planesNextNode temp5 temp6)
		(= planesNextNode (FirstNode (planes elements?)))
		(while planesNextNode
			(planes nextNode: (NextNode planesNextNode))
			(= temp1 (NodeValue planesNextNode))
			(AddPlane temp1)
			(= temp5 (FirstNode ((temp1 casts?) elements?)))
			(while temp5
				((temp1 casts?) nextNode: (NextNode temp5))
				(= temp2 (NodeValue temp5))
				(= temp6 (FirstNode (temp2 elements?)))
				(while temp6
					(temp2 nextNode: (NextNode temp6))
					(if
					(& ((= temp3 (NodeValue temp6)) -info-?) $0010)
						(AddScreenItem temp3)
					)
					(= temp6 (temp2 nextNode?))
				)
				(= temp5 ((temp1 casts?) nextNode?))
			)
			(= planesNextNode (planes nextNode?))
		)
		(global208
			kWindow: (ScrollWindow 0 global208 (global208 maxItems?))
		)
		(ScrollWindow 19 (global208 kWindow?) global258)
		(ScrollWindow 8 (global208 kWindow?))
		(if global210
			(global208 show:)
			(= global210 0)
			(messager say: 0 0 24 1 0 0)
			((ScriptID 0 17) pageUp:)
		)
		(if (theIconBar contains: (ScriptID 0 11))
			(AddScreenItem (ScriptID 0 3))
		)
		(if lastEvent (lastEvent dispose:))
		(theGame setCursor: waitCursor 1)
		(= temp0
			(if (not (OneOf (curRoom style?) -1 15 16 17 18))
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
		(= tickOffset (- gameTime (GetTime)))
		(= global176 -1)
		(LarryNarrator curText: 0)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (newRoom)
		(if (cast contains: (ScriptID 825 1))
			((ScriptID 825 1) dispose:)
		)
		(if global205 (proc79_7))
		(if cuees
			(cuees eachElementDo: #dispose)
			(cuees release:)
		)
		(super newRoom: &rest)
		(if
			(not
				(if (or (Btst 53) (Btst 35) (Btst 23))
				else
					(OneOf prevRoomNum 100 110 120 130 140)
				)
			)
			((ScriptID 825 1)
				init:
				baseSetter: (ScriptID 825 3)
				setMotion: (ScriptID 825 0)
			)
		)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		((ScriptID 80) doit: roomNum)
		(if debugOn (SetDebug))
		(regions addToFront: (= curRoom (ScriptID roomNum)))
		(Purge (curRoom purge?))
		(cond 
			(
				(or
					(curRoom showControls?)
					(and
						(not (theGame controlsVisible?))
						(not (curRoom noControls?))
					)
				)
				(theGame drawControls:)
			)
			(
			(and (curRoom noControls?) (theGame controlsVisible?)) (theGame hideControls:))
		)
		(curRoom init:)
		(CueObj client: 0 state: 0)
		(= global178 0)
	)
	
	(method (restart &tmp temp0)
		(if (talkers size:) (messager cue: 1))
		(if (Print dialog?) ((Print dialog?) dispose:))
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 230 105)
		(if
			(==
				(Print
					font: userFont
					width: 110
					addTitle: 6 0 4 1 0
					addText: 6 0 0 1 50 3 0
					addButton: 200 6 0 3 1 50 35 0
					addButton: 100 6 0 2 1 140 35 0
					addIcon: 1909 0 0 2 2
					init:
				)
				100
			)
			(if (curRoom inset?) ((curRoom inset?) dispose:))
			(PalVary 3)
			(global208 hide:)
			(theGame setCursor: waitCursor 1)
			(timers eachElementDo: #dispose)
			(timers eachElementDo: #delete)
			(theMusic stop: dispose:)
			(theMusic2 stop: dispose:)
			(features eachElementDo: #dispose release:)
			(cast eachElementDo: #dispose)
			(theDoits release:)
			(PalCycle 4)
			(RemapColors 0)
			(global186 dispose:)
			(global177 dispose:)
			(global190 dispose:)
			(gameFlags dispose:)
			((ScriptID 92 3) view: 1900 loop: 1 dispose:)
			(theIconBar hide:)
			(inventory
				hide:
				curIcon: 0
				delete: (ScriptID 85 5)
				eachElementDo: #owner 0
				eachElementDo: #state 0
			)
			(controlPlane dispose:)
			(talkerPlane dispose:)
			((ScriptID 0 13) dispose:)
			((ScriptID 0 1) dispose:)
			((ScriptID 914 0) dispose:)
			(= controlsVisible 0)
			((ScriptID 88 0) dispose:)
			(UpdatePlane
				(thePlane setRect: 0 0 320 200 drawPic: -1 yourself:)
			)
			(= temp0 100)
			(while (<= temp0 260)
				(if (!= temp0 208) (= [ego temp0] 0))
				(++ temp0)
			)
			((ScriptID 71 0) init:)
			(DisposeScript 71)
			(= score 0)
			((ScriptID 88 0) update:)
			(if (cast contains: (ScriptID 825 1))
				((ScriptID 825 1) setMotion: 0 dispose:)
			)
			((ScriptID 825 0) completed: 1 value: -1 initialized: 0)
			(= curRoomNum 820)
			(= isHandsOff 1)
			(= gGButtonBarCurIcon (ScriptID 0 3))
			(theGame handsOn: newRoom: 200)
		else
			(theGame setCursor: gGButtonBarGetCursor)
		)
	)
	
	(method (restore &tmp theTheCursor)
		(cond 
			((or (Print dialog?) (talkers size:)))
			(
				(and
					(not (OneOf curRoomNum 100 110 120))
					(or (Btst 86) (& (gGraphMenuBar state?) $0004))
				)
				(Print
					width: 150
					font: userFont
					addTitle: 0 0 23 1 0
					addText: 0 0 19 1 0 0 0
					init:
				)
			)
			(else
				(= theTheCursor theCursor)
				(super restore: &rest)
				(theGame setCursor: theTheCursor)
			)
		)
	)
	
	(method (save param1 &tmp newStr newStr_2 temp2)
		(= gGTheGameCursor theCursor)
		(cond 
			(
				(and
					(or (talkers size:) (Print dialog?))
					(Print dialog?)
				)
			)
			(
			(or (Btst 86) (& (gGraphMenuBar state?) $0004))
				(Print
					width: 150
					font: userFont
					addTitle: 0 0 23 1 0
					addText: 0 0 15 1 0 0 0
					init:
				)
			)
			((== curRoomNum 740) (messager say: 0 0 20 0 0 0))
			((and argc (== param1 1))
				(if (or (curRoom script?) isHandsOff)
					((ScriptID 0 12) setReal: theGame 10)
				else
					(theGame setCursor: normalCursor)
					(switch
						(Print
							addTitle: 8 0 4 1 0
							addText: 8 0 0 1 50 1 0
							addIcon: 972 0 0 0 0
							addButton: 1 8 0 2 1 50 33 0
							addButton: 0 8 0 3 1 85 33 0
							addButton: 2 8 0 6 1 125 33 0
							init: revertCursor
						)
						(1
							(super save: &rest)
							(if (!= ((ScriptID 0 12) seconds?) -1)
								((ScriptID 0 12) setReal: theGame 0 global183)
							)
							(theGame setCursor: gGTheGameCursor)
						)
						(2
							(proc76_0)
							(theGame setCursor: gGTheGameCursor)
						)
					)
				)
				(messager say: 0 0 24 1 0 0)
				(return)
			)
			((and argc (== param1 0) (!= global176 -1))
				(curSaveDir copy: global190)
				(if (not (ValidPath (KString 9 curSaveDir)))
					(= newStr (Str new:))
					(= newStr_2 (Str new:))
					(Message 0 0 12 0 16 1 (newStr_2 data?))
					(newStr format: newStr_2 global190)
					(Print
						addText: newStr
						addButtonBM: -1 0 0 1 {OK} 0 30
						init:
					)
					(newStr dispose:)
					(newStr_2 dispose:)
					(return)
				)
				(theGame setCursor: waitCursor)
				(if (= temp2 (global208 isVisible?))
					(global208 hide:)
					(= global210 1)
				)
				(= global258 (ScrollWindow 18 (global208 kWindow?)))
				(ScrollWindow 17 (global208 kWindow?))
				(if
					(not
						(SaveGame
							name
							global176
							(global177 data?)
							(KString 9 version)
						)
					)
					(global208
						kWindow: (ScrollWindow 0 global208 (global208 maxItems?))
					)
					(ScrollWindow 19 (global208 kWindow?) global258)
					(ScrollWindow 8 (global208 kWindow?))
					(if temp2 (global208 show:) (= global210 0))
					(Print
						addText: 12 0 17 1 0 0 0
						addButton: 1 12 0 18 1 0 40 0
						init:
					)
					(return)
				else
					(global208
						kWindow: (ScrollWindow 0 global208 (global208 maxItems?))
					)
					(ScrollWindow 19 (global208 kWindow?) global258)
					(ScrollWindow 8 (global208 kWindow?))
					(if temp2 (global208 show:) (= global210 0))
					(= newStr (Str new:))
					(Message 0 0 12 0 0 1 (newStr data?))
					(Print
						addTitle: 12 0 4 1 0
						addTextF: newStr global177
						init:
					)
					(newStr dispose:)
				)
				(if (!= ((ScriptID 0 12) seconds?) -1)
					((ScriptID 0 12) setReal: theGame 0 global183)
				)
				(messager say: 0 0 24 1 0 0)
			)
			(else
				(super save:)
				(if (!= ((ScriptID 0 12) seconds?) -1)
					((ScriptID 0 12) setReal: theGame 0 global183)
				)
				(messager say: 0 0 24 1 0 0)
			)
		)
		(theGame setCursor: gGTheGameCursor)
	)
	
	(method (changeScore delta param2)
		(return
			(if (or (<= argc 1) (not (Bset param2)))
				(cond 
					((and (>= argc 1) (> delta 0) (< delta 10)) (scoreSfx number: 1 loop: 1 play:))
					((and (>= argc 1) (> delta 9)) (scoreSfx number: 2 loop: 1 play:))
				)
				(super changeScore: delta &rest)
				(if (< score 0) (= score 0))
				1
			else
				0
			)
		)
	)
	
	(method (handleEvent event &tmp temp0 temp1)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_0a04
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			ret     
code_0a04:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      4
			eq?     
			bnt      code_0daa
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      6
			eq?     
			bnt      code_0a2a
			ldi      2
			sag      global178
			jmp      code_0da6
code_0a2a:
			dup     
			ldi      12800
			eq?     
			bnt      code_0a75
			pushi    #size
			pushi    0
			lag      talkers
			send     4
			bt       code_0a46
			pushi    #dialog
			pushi    0
			class    Print
			send     4
			bnt      code_0a46
code_0a46:
			not     
			bnt      code_0da6
			lag      global100
			bt       code_0a59
			pushi    1
			pushi    111
			calle    Btst,  2
			bnt      code_0da6
code_0a59:
			pushi    4
			lofsa    {Current free memory: %u kBytes\nMax available: %u kBytes\nmachineSpeed: %d}
			push    
			pushi    1
			pushi    0
			callk    MemoryInfo,  2
			push    
			lsg      global260
			lsg      howFast
			calle    Printf,  8
			jmp      code_0da6
code_0a75:
			dup     
			ldi      8448
			eq?     
			bnt      code_0ad7
			lag      global100
			not     
			bnt      code_0aab
			lsg      global192
			ldi      65532
			eq?     
			bnt      code_0a98
			ldi      0
			sag      global192
			ldi      2
			sag      global178
			ldi      1
			sag      global189
			jmp      code_0da6
code_0a98:
			lsg      global192
			ldi      0
			ge?     
			bnt      code_0aa6
			ldi      65535
			sag      global192
			jmp      code_0da6
code_0aa6:
			-ag      global192
			jmp      code_0da6
code_0aab:
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			pushi    #handleEvent
			pushi    1
			lsp      event
			pushi    1
			pushi    911
			callk    ScriptID,  2
			send     6
			pushi    #dispose
			pushi    0
			pushi    1
			pushi    911
			callk    ScriptID,  2
			send     4
			jmp      code_0da6
code_0ad7:
			dup     
			ldi      3
			eq?     
			bnt      code_0aea
			pushi    #select
			pushi    0
			lofsa    icon7
			send     4
			jmp      code_0da6
code_0aea:
			dup     
			ldi      20
			eq?     
			bnt      code_0b28
			pToa     controlsVisible
			not     
			bnt      code_0af8
			jmp      code_0da6
code_0af8:
			pushi    1
			pushi    105
			calle    Btst,  2
			bnt      code_0b15
			pushi    #show
			pushi    0
			lag      global208
			send     4
			pushi    1
			pushi    105
			calle    Bclr,  2
			jmp      code_0da6
code_0b15:
			pushi    #hide
			pushi    0
			lag      global208
			send     4
			pushi    1
			pushi    105
			calle    Bset,  2
			jmp      code_0da6
code_0b28:
			dup     
			ldi      17
			eq?     
			bnt      code_0b42
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #quitGame
			pushi    0
			lag      theGame
			send     4
			jmp      code_0da6
code_0b42:
			dup     
			ldi      4096
			eq?     
			bnt      code_0b5d
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			pushi    #quitGame
			pushi    0
			lag      theGame
			send     4
			jmp      code_0da6
code_0b5d:
			dup     
			ldi      15360
			eq?     
			bnt      code_0bac
			pushi    #masterVolume
			pushi    0
			lag      theGame
			send     4
			bnt      code_0b7e
			pushi    #masterVolume
			pushi    1
			pushi    0
			self     6
			ldi      0
			sag      global194
			jmp      code_0ba0
code_0b7e:
			lsg      numVoices
			ldi      1
			ge?     
			bnt      code_0b94
			pushi    #masterVolume
			pushi    1
			pushi    15
			self     6
			ldi      13
			sag      global194
			jmp      code_0ba0
code_0b94:
			pushi    #masterVolume
			pushi    1
			pushi    1
			self     6
			ldi      1
			sag      global194
code_0ba0:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0da6
code_0bac:
			dup     
			ldi      43
			eq?     
			bnt      code_0be3
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    351
			pushi    #x
			pushi    2
			pushi    1
			pushi    #moveSpeed
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			sub     
			push    
			calle    Max,  4
			sag      gGEgoCycleSpeed_2
			push    
			lag      ego
			send     6
			jmp      code_0da6
code_0be3:
			dup     
			ldi      45
			eq?     
			bnt      code_0c10
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    351
			pushi    #x
			pushi    #moveSpeed
			pushi    0
			lag      ego
			send     4
			push    
			ldi      1
			add     
			sag      gGEgoCycleSpeed_2
			push    
			lag      ego
			send     6
			jmp      code_0da6
code_0c10:
			dup     
			ldi      61
			eq?     
			bnt      code_0c33
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    #setSpeed
			pushi    1
			ldi      6
			sag      gGEgoCycleSpeed_2
			push    
			lag      ego
			send     6
			jmp      code_0da6
code_0c33:
			dup     
			ldi      15616
			eq?     
			bnt      code_0c4d
			pushi    #save
			pushi    1
			pushi    0
			self     6
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0da6
code_0c4d:
			dup     
			ldi      16128
			eq?     
			bnt      code_0c66
			pushi    #save
			pushi    0
			self     4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0da6
code_0c66:
			dup     
			ldi      16640
			eq?     
			bnt      code_0c7f
			pushi    #restore
			pushi    0
			self     4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0da6
code_0c7f:
			dup     
			ldi      17152
			eq?     
			bnt      code_0cb9
			lsg      curRoomNum
			ldi      620
			eq?     
			not     
			bnt      code_0cad
			pushi    #inset
			pushi    0
			lag      curRoom
			send     4
			not     
			bnt      code_0cad
			pushi    #restart
			pushi    0
			self     4
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0da6
code_0cad:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0da6
code_0cb9:
			dup     
			ldi      15104
			eq?     
			bnt      code_0cde
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    #number
			pushi    1
			pushi    576
			pushi    51
			pushi    0
			lofsa    funFx
			send     10
			jmp      code_0da6
code_0cde:
			dup     
			ldi      15872
			eq?     
			bnt      code_0d03
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    #number
			pushi    1
			pushi    516
			pushi    51
			pushi    0
			lofsa    funFx
			send     10
			jmp      code_0da6
code_0d03:
			dup     
			ldi      16384
			eq?     
			bnt      code_0d28
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    #number
			pushi    1
			pushi    631
			pushi    51
			pushi    0
			lofsa    funFx
			send     10
			jmp      code_0da6
code_0d28:
			dup     
			ldi      16896
			eq?     
			bnt      code_0d4d
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    #number
			pushi    1
			pushi    633
			pushi    51
			pushi    0
			lofsa    funFx
			send     10
			jmp      code_0da6
code_0d4d:
			dup     
			ldi      17408
			eq?     
			bnt      code_0d70
			pushi    #canControl
			pushi    0
			lag      user
			send     4
			bnt      code_0da6
			pushi    #number
			pushi    1
			pushi    41
			pushi    51
			pushi    0
			lofsa    funFx
			send     10
			jmp      code_0da6
code_0d70:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lag      global100
			bnt      code_0da6
			pushi    #claimed
			pushi    1
			pushi    0
			lap      event
			send     6
			pushi    #handleEvent
			pushi    1
			lsp      event
			pushi    1
			pushi    911
			callk    ScriptID,  2
			send     6
			pushi    #dispose
			pushi    0
			pushi    1
			pushi    911
			callk    ScriptID,  2
			send     4
code_0da6:
			toss    
			jmp      code_0e26
code_0daa:
			dup     
			ldi      1
			eq?     
			bnt      code_0e26
			lag      global100
			bnt      code_0e26
			pushi    #modifiers
			pushi    0
			lap      event
			send     4
			push    
			ldi      8
			and     
			bnt      code_0e26
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
code_0dce:
			pushi    2
			pushi    #type
			pushi    0
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp0
			send     4
			ne?     
			bnt      code_0e1d
			pushi    325
			pushi    #y
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      10
			sub     
			push    
			pushi    316
			pushi    1
			pushi    0
			pushi    #alterEgo
			pushi    0
			class    User
			send     4
			send     14
			pushi    0
			callk    FrameOut,  0
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			jmp      code_0dce
code_0e1d:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
code_0e26:
			toss    
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_0e36
			ldi      1
			jmp      code_0e67
code_0e36:
			pToa     script
			bnt      code_0e49
			pushi    #handleEvent
			pushi    1
			lsp      event
			send     6
			bnt      code_0e49
			ldi      1
			jmp      code_0e67
code_0e49:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			ldi      16384
			and     
			bnt      code_0e67
			pushi    #pragmaFail
			pushi    1
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			self     6
code_0e67:
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			ret     
		)
	)
	
	(method (quitGame param1 &tmp theTheCursor)
		(= theTheCursor theCursor)
		(theGame setCursor: normalCursor 1)
		(SetCursor 230 105)
		(if
			(or
				(and argc param1)
				(Print
					font: userFont
					width: 110
					addTitle: 5 0 4 1 0
					addText: 5 0 0 1 50 3 0
					addButton: 0 5 0 3 1 50 35 0
					addButton: 1 5 0 2 1 135 35 0
					addIcon: 1908 0 0 2 2
					init:
				)
			)
			(= quit 1)
		)
		(theGame setCursor: theTheCursor)
	)
	
	(method (pragmaFail param1)
		(if (User canInput:)
			(if (== (approachCode doit: param1) -32768)
				(= param1 15)
			)
			(messager say: 0 param1 0 1 0 0)
		)
	)
	
	(method (drawControls)
		(if controlsVisible (return))
		(= controlsVisible 1)
		(UpdateScreenItem
			((View new:)
				view: 960
				cel: 0
				posn: 0 0
				init: controlSet
				yourself:
			)
		)
		(UpdateScreenItem
			(larry6Title
				view: 960
				loop: 3
				cel: 0
				posn: 82 8
				setPri: 201
				init: talkerSet
				yourself:
			)
		)
		(UpdateScreenItem
			((View new:)
				view: 960
				cel: 1
				posn: 160 0
				init: controlSet
				yourself:
			)
		)
		(UpdateScreenItem
			((View new:)
				view: 960
				loop: 1
				posn: 0 0
				init: talkerSet
				yourself:
			)
		)
		(UpdateScreenItem
			((View new:)
				view: 960
				loop: 1
				cel: 1
				posn: 160 0
				init: talkerSet
				yourself:
			)
		)
		(UpdatePlane
			(talkerPlane
				priority: (+ (GetHighPlanePri) 1)
				yourself:
			)
		)
		(UpdatePlane
			(controlPlane
				priority: (+ (GetHighPlanePri) 1)
				yourself:
			)
		)
		((ScriptID 88 0) show: update:)
		(theIconBar enable: show:)
		(UpdatePlane
			((theIconBar plane?) picture: -2 yourself:)
		)
		(inventory
			state: (| (inventory state?) $0020)
			show: ego
		)
		(UpdateScreenItem
			((ScriptID 92 3) setPri: 200 init: talkerSet yourself:)
		)
		(gGraphMenuBar show:)
		(if (not (Btst 105)) (textScroller show:))
	)
	
	(method (hideControls)
		(if (not controlsVisible) (return))
		(if (not (Btst 105)) (textScroller hide:))
		((ScriptID 88 0) hide:)
		(theIconBar hide:)
		(inventory hide:)
		(gGraphMenuBar hide:)
		(talkerSet eachElementDo: #dispose)
		(talkerPlane priority: -1)
		(UpdatePlane talkerPlane)
		(controlPlane priority: -1)
		(UpdatePlane controlPlane)
		(controlSet eachElementDo: #dispose)
		(= controlsVisible 0)
	)
)

(instance larryDoVerbCode of Code
	(properties)
	
	(method (doit param1 param2)
		(cond 
			(
				(and
					(not (OneOf param1 4 1 2 5 6))
					(Message
						0
						(param2 modNum?)
						(param2 noun?)
						15
						(param2 case?)
						1
					)
				)
				(messager
					say: (param2 noun?) 15 (param2 case?) 0 0 (param2 modNum?)
				)
			)
			(
				(Message
					0
					(param2 modNum?)
					(param2 noun?)
					0
					(param2 case?)
					1
				)
				(messager
					say: (param2 noun?) 0 (param2 case?) 0 0 (param2 modNum?)
				)
			)
			((not (curRoom doVerb: param1)) (theGame pragmaFail: param1))
		)
	)
)

(instance larryHandsOn of Code
	(properties)
	
	(method (doit &tmp theIconBarCurIcon)
		(if (not (theGame isHandsOff?)) (return))
		(theGame isHandsOff: 0)
		(User canControl: 1 canInput: 1)
		(theIconBar
			enableIcon: icon1 icon2 icon3 icon4 icon6 (theIconBar at: 0)
		)
		(if gLarryRoom (theIconBar enableIcon: icon5))
		(if gGButtonBarCurIcon
			(theIconBar curIcon: gGButtonBarCurIcon)
			(= gGButtonBarCurIcon 0)
		else
			(theIconBar curIcon: (theIconBar at: 0))
		)
		(if (== (theIconBar getCursor:) -1)
			(theIconBar advanceCurIcon:)
		)
		(= theIconBarCurIcon (theIconBar curIcon?))
		(if
			(and
				(theIconBarCurIcon isKindOf: LL6InvItem)
				(!= (theIconBarCurIcon owner?) ego)
			)
			(theIconBar curIcon: (theIconBar at: 0))
			(if (!= (theIconBar at: 0) (ScriptID 0 3))
				(theIconBar advanceCurIcon:)
			)
		)
		(theIconBar show:)
		(theGame setCursor: ((theIconBar curIcon?) getCursor:))
	)
)

(instance larryHandsOff of Code
	(properties)
	
	(method (doit)
		(if (theGame isHandsOff?) (return))
		(theGame isHandsOff: 1)
		(if (not argc) (ego setMotion: 0))
		(if ((theIconBar curIcon?) isKindOf: LL6InvItem)
			(theIconBar curIcon: (theIconBar at: 0))
		)
		(= gGButtonBarCurIcon (theIconBar curIcon?))
		(theIconBar disableIcon: icon1 icon2 icon3 icon4 icon6)
		(if (not gLarryRoom) (theIconBar disableIcon: icon5))
		(theIconBar disableIcon: (theIconBar at: 0))
		(User canControl: 0 canInput: 0)
		(if (& (theIconBar state?) $0020) (theIconBar show:))
		(theGame setCursor: waitCursor)
	)
)

(instance theGameCursor of Cursor
	(properties
		view 998
		cel 7
	)
)

(instance theWaitCursor of Cursor
	(properties
		view 998
		cel 8
	)
)

(instance theArrowCursor of Cursor
	(properties
		view 998
		cel 7
	)
)

(instance revertCursor of Script
	(properties)
	
	(method (cue)
		(if (== theCursor normalCursor)
			(theGame setCursor: gGTheGameCursor)
		)
	)
)

(instance larryMessager of Messager
	(properties)
	
	(method (dispose)
		(sounds pause: 0)
		(super dispose:)
	)
	
	(method (sayNext)
		(sounds pause: 1)
		(super sayNext: &rest)
	)
	
	(method (findTalker param1 &tmp temp0)
		(proc79_8 param1)
		(switch param1
			(1 (ScriptID 1800 1))
			(2 (ScriptID 1801 2))
			(3 (ScriptID 1802 3))
			(7 (ScriptID 610 1))
			(11 (ScriptID 1803 11))
			(6 (ScriptID 1804 6))
			(21 (ScriptID 1805 21))
			(13 (ScriptID 1806 13))
			(18 (ScriptID 1807 18))
			(15 (ScriptID 1808 15))
			(8 (ScriptID 1809 8))
			(22 (ScriptID 1810 22))
			(12 (ScriptID 1811 12))
			(10 (ScriptID 1812 10))
			(17 (ScriptID 1230 17))
			(16 (ScriptID 1813 16))
			(20 (ScriptID 1242 20))
			(25 (ScriptID 1146 25))
			(26 (ScriptID 1814 26))
			(23 (ScriptID 1815 23))
			(24 (ScriptID 1144 24))
			(14 (ScriptID 1816 14))
			(28 (ScriptID 1817 28))
			(29 (ScriptID 1818 29))
			(30 (ScriptID 1819 30))
			(31 (ScriptID 1820 31))
			(32 (ScriptID 1821 32))
			(27 (ScriptID 1807 27))
			(5 (ScriptID 1822 5))
			(4 (ScriptID 1822 4))
			(33 (ScriptID 1823 33))
			(34 (ScriptID 1824 34))
			(35 (ScriptID 1825 35))
			(98 (ScriptID 1826 98))
			(9 (ScriptID 1592 9))
			(else  narrator)
		)
	)
)

(instance larryApproachCode of Code
	(properties)
	
	(method (doit param1)
		(switch param1
			(1 1)
			(2 2)
			(3 4)
			(4 8)
			(5 16)
			(6 32)
			(else  -32768)
		)
	)
)

(instance icon0 of IconI
	(properties
		type $5000
		message 3
		mainView 981
		maskView 981
		maskCel 2
	)
)

(instance icon1 of IconI
	(properties
		message 1
		mainView 981
		mainLoop 1
		maskView 981
		maskLoop 1
		maskCel 2
	)
)

(instance icon2 of IconI
	(properties
		message 4
		mainView 981
		mainLoop 2
		maskView 981
		maskLoop 2
		maskCel 2
	)
)

(instance icon3 of IconI
	(properties
		message 5
		mainView 981
		mainLoop 3
		maskView 981
		maskLoop 3
		maskCel 2
	)
)

(instance icon4 of IconI
	(properties
		message 2
		mainView 981
		mainLoop 4
		maskView 981
		maskLoop 4
		maskCel 2
	)
)

(instance icon5 of IconI
	(properties
		signal $0007
		type $0000
		message 0
		mainView 981
		mainLoop 7
		maskView 981
		maskLoop 7
		maskCel 2
	)
	
	(method (select &tmp theGLarryRoom)
		(if gLarryRoom
			(theIconBar disableIcon: self show:)
			(= theGLarryRoom gLarryRoom)
			(= gLarryRoom 0)
			(theGLarryRoom cue:)
		)
	)
)

(instance icon6 of IconI
	(properties
		message 6
		mainView 981
		mainLoop 5
		maskView 981
		maskLoop 5
		maskCel 2
	)
)

(instance icon7 of IconI
	(properties
		signal $0003
		type $0000
		message 0
		mainView 981
		mainLoop 6
		maskView 981
		maskLoop 6
		maskCel 2
	)
	
	(method (select &tmp theTheCursor)
		(return
			(if (super select: &rest)
				(if (and global110 (== curRoomNum 330))
					(messager say: 0 0 21 1 0 0)
				else
					(= theTheCursor theCursor)
					(theGame setCursor: normalCursor)
					((ScriptID 94) init:)
					(theGame setCursor: theTheCursor)
				)
				1
			else
				0
			)
		)
	)
)

(instance iconExit of IconI
	(properties
		signal $0003
		type $0000
		message 0
		mainView 981
		mainLoop 6
		maskView 981
		maskLoop 6
		maskCel 2
	)
	
	(method (init param1)
		(= message (if argc param1 else 0))
		(theIconBar
			prevIcon: 0
			delete: (ScriptID 0 3)
			addToFront: self
		)
		(super init: theIconBar)
		(theIconBar show:)
		(cond 
			((not (theGame isHandsOff?))
				(if (== (theIconBar curIcon?) (ScriptID 0 3))
					(theIconBar curIcon: (ScriptID 0 4))
				)
				(theIconBar enableIcon: self show:)
				(theGame setCursor: ((theIconBar curIcon?) getCursor:))
			)
			((== gGButtonBarCurIcon (ScriptID 0 3)) (= gGButtonBarCurIcon (ScriptID 0 4)))
		)
	)
	
	(method (dispose)
		(super dispose:)
		(theIconBar enableIcon: (ScriptID 0 11))
		(theIconBar
			delete: (ScriptID 0 11)
			addToFront: (ScriptID 0 3)
			curIcon: (ScriptID 0 3)
			highlightedIcon: 0
			show:
		)
		(if (not (theGame isHandsOff?))
			(theGame setCursor: ((theIconBar curIcon?) getCursor:))
		)
		(= gGButtonBarCurIcon (ScriptID 0 3))
	)
	
	(method (select)
		(return
			(if (and (super select: &rest) message)
				(message cue:)
				1
			else
				0
			)
		)
	)
)

(instance cIcon0 of Cursor
	(properties
		view 998
	)
)

(instance cIcon1 of Cursor
	(properties
		view 998
		cel 1
	)
)

(instance cIcon2 of Cursor
	(properties
		view 998
		cel 2
	)
)

(instance cIcon3 of Cursor
	(properties
		view 998
		cel 3
	)
)

(instance cIcon4 of Cursor
	(properties
		view 998
		cel 4
	)
)

(instance cIcon5 of Cursor
	(properties
		view 998
		cel 5
	)
)

(instance sTimer of Timer
	(properties)
	
	(procedure (localproc_1f7b)
		(= client 0)
		(if
			(or
				(curRoom script?)
				(not (prints isEmpty:))
				(not (talkers isEmpty:))
				(== curRoomNum 740)
			)
			(= seconds 5)
		else
			(theGame save: 1)
		)
	)
	
	
	(method (doit &tmp theLastTime)
		(cond 
			((== client self) 0)
			((!= cycleCnt -1) (if (not (-- cycleCnt)) (localproc_1f7b)))
			((!= seconds -1)
				(if (!= lastTime (= theLastTime (GetTime 1)))
					(= lastTime theLastTime)
					(if (not (-- seconds)) (localproc_1f7b))
				)
			)
			((> (- gameTime ticks) 0) (localproc_1f7b))
		)
	)
	
	(method (delete)
		(if (and (not client) global183)
			(self setReal: theGame 0 global183)
		)
	)
)

(instance scrollBarUpIcon of IconI
	(properties
		x 2
		y 1
		priority 15
		fixPriority 1
		mainView 963
		mainLoop 1
	)
)

(instance scrollBarDownIcon of IconI
	(properties
		x 2
		y 35
		priority 15
		fixPriority 1
		mainView 963
		mainLoop 2
	)
)

(instance scrollBar of WindowScrollBar
	(properties
		x 4
		y 6
		priority 14
		fixPriority 1
		view 963
		thumbView 963
		thumbCel 1
		minPosn 6
		maxPosn 32
	)
)

(instance textScroller of ScrollerWindow
	(properties
		fore 0
		back 61
		font 2308
		borderColor 61
		nsLeft 40
		nsTop 157
		nsRight 269
		nsBottom 195
		maxItems 50
	)
	
	(method (init)
		(super init: &rest)
		(UpdateScreenItem
			((scroller thumb?) setPri: 15 yourself:)
		)
	)
)

(instance version of File
	(properties)
)

(instance larry6Title of View
	(properties)
)
