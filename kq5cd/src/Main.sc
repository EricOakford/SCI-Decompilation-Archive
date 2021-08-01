;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use BertaWindow)
(use Audio)
(use KQCursor)
(use CDActor)
(use PMouse)
(use IconBar)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use DCIcon)
(use Timer)
(use RFeature)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	KQ5 0
	NormalEgo 1
	HandsOff 2
	HandsOn 3
	HaveMem 4
	RedrawCast 5
	cls 6
	Face 7
	CueEvent 8
	Bset 9
	Bclr 10
	Btoggle 11
	Btst 12
	DoDisplay 15
	proc0_18 18
	DebugTP 19
	VerbFail 21
	EgoHeadMove 24
	EgoDead 26
	SolvePuzzle 27
	Say 28
	SpeakAudio 29
	proc0_30 30
)

(local
	ego
	theGame
	curRoom
	speed =  6
	quit
	cast
	regions
	timers
	sounds
	inventory
	addToPics
	curRoomNum
	prevRoomNum
	newRoomNum
	debugOn
	score
	possibleScore
	showStyle =  IRISOUT
	aniInterval
	theCursor
	normalCursor =  ARROW_CURSOR
	waitCursor =  20
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	version
	locales
	curSaveDir
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime =  3
	currentPalette
	modelessPort
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
	theMenuBar
	theIconBar
	mouseX
	mouseY
	keyDownHandler
	mouseDownHandler
	directionHandler
	gameCursor
	lastVolume
	pMouse
	theDoits
	eatMice =  60
	user
	syncBias
	theSync
	cDAudio
	fastCast
	inputFont
	tickOffset
	howFast
	gameTime
	invCursor
		global90
		global91
		global92
		global93
		global94
		global95
		global96
		global97
		global98
	lastSysGlobal
	debugging
	global101 =  5
	isHandsOff
	global103 =  1
	global104
	numColors
	gGTheGameCursor
	global107
	global108
	global109
	gGEgoX
	gGEgoY
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
	gameFlags
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
	saveBits
	saveBits2
	theMusic3
	isVGA =  256
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
	global312 =  -1
	global313
	global314
	global315
	eatLambCount
	theMusic4
	global318
	global319
	global320
	global321
	globalCedric
	gPEventX
	gPEventY
	global325
	global326
	global327
	theAudio
	theMusic2
	deathMessage
	global331
	global332
	global333
	global334
	global335
	global336
	global337
	gGNumber_3
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
	gGNumber_2
	global362
	global363
	gTheHenchManX
	gTheHenchManY
	global366
	global367
	global368
	global369
	global370
	global371
	global372
	global373
	global374 =  1
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
	gMordObj
	global387
	gNewActorCloner
	gNewActorCloner_2
	gNewActorCloner_3
	gNewActorCloner_4
	gNewActorCloner_5
	global393
	global394 =  1
	global395
	crownCursor
	theMusic
	theEmptyBagCursor
	curSaveDisk
	global400
	global401
)
(procedure (NormalEgo theLoop theView)
	(cond 
		((>= argc 1)
			(ego loop: theLoop)
		)
		((== argc 2)
			(ego view: theView)
		)
	)
	(ego
		setLoop: -1
		setPri: -1
		setMotion: 0
		moveSpeed: (theGame egoMoveSpeed?)
		setCycle: KQ5SyncWalk
		cycleSpeed: (theGame egoMoveSpeed?)
		setStep: 3 2
		normal: 1
		illegalBits: cWHITE
		ignoreActors: 0
	)
)

(procedure (HandsOff)
	(theGame setCursor: waitCursor TRUE)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
	(theIconBar disable:)
	(= isHandsOff TRUE)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(theIconBar enable:)
	(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
	(= isHandsOff FALSE)
)

(procedure (HaveMem howMuch)
	(return (> (MemoryInfo FreeHeap) howMuch))
)

(procedure (RedrawCast)
	(Animate (cast elements?) FALSE)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (Face actor1 actor2 both &tmp ang1To2)
	(EgoHeadMove
		actor1
		(= ang1To2
			(GetAngle
				(actor1 x?)
				(actor1 y?)
				(actor2 x?)
				(actor2 y?)
			)
		)
		both
	)
	(if (== argc 4)
		(Face actor2 actor1 both)
	)
)

(procedure (CueEvent obj eType eMsg eMod &tmp event)
	((= event (Event new:))
		type: eType
		message: eMsg
		modifiers: eMod
	)
	(if obj
		(obj handleEvent: event)
	else
		(KQ5 handleEvent: event)
	)
	(event dispose:)
)

(procedure (Bset)
	(manageFlags 0 &rest)
)

(procedure (Bclr)
	(manageFlags 2 &rest)
)

(procedure (Btoggle)
	(manageFlags 3 &rest)
)

(procedure (Btst)
	(manageFlags 1 &rest)
)

(procedure (DoDisplay theString theTop theLeft theColor &tmp theX theY sColor)
	(if saveBits
		(Display 0 3 p_restore saveBits2)
		(Display 0 3 p_restore saveBits)
		(= saveBits 0)
	)
	(if theString
		(= theY
			(if (and (> argc 1) (>= theTop 0))
				theTop
			else
				5
			)
		)
		(= theX
			(if (and (> argc 2) (>= theLeft 0))
				theLeft
			else
				5
			)
		)
		(= sColor
			(if (and (> argc 3) (>= theColor 0))
				theColor
			else
				0
			)
		)
		(= saveBits
			(Display theString
				p_width (- 260 theX)
				p_at theX theY
				p_mode teJustLeft
				p_font 9
				p_color sColor
				p_save
			)
		)
		(= saveBits2
			(Display theString
				p_width (- 260 theX)
				p_at theX theY
				p_mode teJustLeft
				p_font 8
				p_color 15
				p_save
			)
		)
	)
)

(procedure (proc0_18 param1 param2 theTime &tmp i)
	(for ((= i 0)) (<= i 100) ((+= i 5))
		(Palette 4 param1 param2 i)
		(Wait theTime)
	)
)

(procedure (DebugTP &tmp nRoom)
	(while (not (!= (= nRoom (GetNumber {Teleport to:})) -1)))
	(= global103 0)
	(theGame setCursor: (= gGTheGameCursor gameCursor) TRUE)
	(curRoom newRoom: nRoom)
)

(procedure (VerbFail &tmp oldCur event theTime)
	(if (User canInput:)
		(= oldCur (theGame setCursor: invCursor))
		(= global126 0)
		(signalView
			view: 942
			loop: 0
			cel: 10
			posn: mouseX (- (signalView y?) 10) (- (signalView y?) mouseY)
			forceUpd:
			show:
		)
		(Animate (cast elements?) FALSE)
		(= theTime (GetTime))
		(while (< (Abs (- theTime (GetTime))) 40)
			(breakif (OneOf ((= event (Event new:)) type?) keyDown mouseDown))
			(event dispose:)
		)
		(if (IsObject event)
			(event dispose:)
		)
		(signalView posn: 1000 1000 hide:)
		(theGame setCursor: oldCur TRUE)
	)
)

(procedure (EgoHeadMove param1 param2 param3 &tmp temp0)
	(if (not (& (param1 signal?) $0800))
		(if (or (< argc 3) (not (= temp0 param3)))
			(= temp0 (NumLoops param1))
		)
		(cond 
			((> temp0 8)
				(param1
					loop:
						(cond 
							((and (> param2 22) (< param2 68)) 6)
							((and (> param2 67) (< param2 113)) 0)
							((and (> param2 112) (< param2 158)) 4)
							((and (> param2 157) (< param2 203)) 2)
							((and (> param2 202) (< param2 248)) 5)
							((and (> param2 247) (< param2 293)) 1)
							((and (> param2 292) (< param2 338)) 7)
							(else 3)
						)
				)
			)
			((> temp0 4)
				(param1
					loop:
						(cond 
							((and (> param2 44) (< param2 136)) 0)
							((and (> param2 135) (< param2 225)) 2)
							((and (> param2 224) (< param2 316)) 1)
							(else 3)
						)
				)
			)
			(temp0
				(= param2
					(mod (+ (= param2 (- param2 (/ 180 temp0))) 360) 360)
				)
				(param1 loop: (/ param2 (/ 360 temp0)))
			)
		)
		(if (param1 respondsTo: #head) ((param1 head?) look:))
	)
)

(procedure (EgoDead theView theLoop theCycle theSpeed)
	(HandsOff)
	(theGame setCursor: arrowCursor)
	(if argc
		(Load VIEW theView)
	else
		(Load VIEW 248)
	)
	(deathIcon
		view: (if argc theView else 248)
		loop: (if argc theLoop else 0)
		cycler: (if (< argc 3) 0 else theCycle)
		cycleSpeed: (if (< argc 4) 30 else theSpeed)
	)
	(theMusic
		number: 19
		loop: 1
		vol: 127
		priority: 500
		playBed:
	)
	(DoAudio Play deathMessage)
	(repeat
		(switch
			(Print {}
				#width 220
				#icon deathIcon theLoop 0
				#mode teJustCenter
				#button {Restore} 1
				#button {Restart} 2
				#button {____Quit____} 3
			)
			(1
				(DoAudio Stop)
				(theGame restore:)
			)
			(2
				(DoAudio Stop)
				(theGame restart: 0)
			)
			(3
				(DoAudio Stop)
				(= quit TRUE)
				(break)
			)
		)
	)
)


(procedure (SolvePuzzle pValue)
	(rm0Sound priority: 15 number: 65 loop: 1 play:)
	(if argc
		(+= score pValue)
	)
)

(procedure (Say whom theString moreStuff &tmp temp0 [str 500])
	(if (u< theString 1000)
		(GetFarText theString moreStuff @str)
	else
		(StrCpy @str theString)
	)
	(talkerIcon
		view: whom
		cycler: Forward
		cycleSpeed: 6
		count: (+ (/ (StrLen @str) 20) 1)
		talker: 1
	)
	(if (u< theString 1000)
		(Print @str &rest 82 talkerIcon 0 0)
	else
		(Print @str moreStuff &rest
			#icon talkerIcon 0 0
		)
	)
)

(procedure (SpeakAudio param1 param2 &tmp temp0 temp1 temp2 temp3)
	(asm
		pushi    1
		pushi    3
		callk    DoAudio,  2
		lsp      argc
		ldi      3
		eq?     
		not     
		bnt      code_167a
		lag      global401
		not     
		bnt      code_167a
		ldi      1
		sag      global401
		pushi    #masterVolume
		pushi    0
		lag      theGame
		send     4
		sat      temp3
		pushi    #masterVolume
		pushi    0
		lag      theGame
		send     4
		push    
		ldi      4
		ge?     
		bnt      code_167a
		pushi    379
		pushi    #superClass
		lst      temp3
		ldi      4
		sub     
		push    
		lag      theGame
		send     6
code_167a:
		lsp      argc
		ldi      2
		eq?     
		bnt      code_16a6
		lsp      argc
		ldi      3
		eq?     
		not     
		bnt      code_16a6
		pushi    #theVol
		pushi    1
		lst      temp3
		pushi    150
		pushi    2
		lsp      param2
		pushi    2
		pushi    2
		lsp      param1
		callk    DoAudio,  4
		push    
		class    SpeakTimer
		send     14
		jmp      code_1773
code_16a6:
		lsp      argc
		ldi      3
		eq?     
		bnt      code_16b8
		pushi    2
		pushi    2
		lsp      param1
		callk    DoAudio,  4
		jmp      code_1773
code_16b8:
		pushi    #setCursor
		pushi    1
		lofsa    speakCursor
		push    
		lag      theGame
		send     6
		sat      temp0
		pushi    2
		pushi    2
		lsp      param1
		callk    DoAudio,  4
		push    
		ldi      2
		add     
		push    
		pushi    0
		callk    GetTime,  0
		add     
		sat      temp2
code_16d9:
		pushi    #type
		pushi    0
		pushi    #new
		pushi    0
		class    Event
		send     4
		sat      temp1
		send     4
		not     
		bt       code_1708
		pushi    #type
		pushi    0
		lat      temp1
		send     4
		push    
		ldi      2
		eq?     
		bt       code_1708
		pushi    #type
		pushi    0
		lat      temp1
		send     4
		push    
		ldi      512
		eq?     
		bnt      code_1737
code_1708:
		pushi    0
		callk    GetTime,  0
		push    
		lat      temp2
		lt?     
		bnt      code_1737
		pushi    1
		pushi    6
		callk    DoAudio,  2
		push    
		ldi      65535
		ne?     
		bnt      code_1737
		pushi    1
		lst      temp1
		callk    IsObject,  2
		bnt      code_16d9
		pushi    #dispose
		pushi    0
		lat      temp1
		send     4
		ldi      0
		sat      temp1
		jmp      code_16d9
code_1737:
		pushi    1
		lst      temp1
		callk    IsObject,  2
		bnt      code_174b
		pushi    #dispose
		pushi    0
		lat      temp1
		send     4
		ldi      0
		sat      temp1
code_174b:
		pushi    1
		pushi    3
		callk    DoAudio,  2
		lsg      global401
		ldi      1
		eq?     
		bnt      code_1769
		pushi    #masterVolume
		pushi    1
		lst      temp3
		lag      theGame
		send     6
		ldi      0
		sag      global401
code_1769:
		pushi    #setCursor
		pushi    1
		lst      temp0
		lag      theGame
		send     6
code_1773:
		ret     
	)
)

(procedure (proc0_30 theAudio theView theLoop theCel)
	(DoAudio Play theAudio)
	(Print {} #icon theView theLoop theCel &rest)
)

(procedure (manageFlags param1 param2 &tmp temp0 temp1 temp2 temp3)
	(asm
		ldi      0
		sat      temp0
code_1321:
		lst      temp0
		lsp      argc
		ldi      1
		sub     
		lt?     
		bnt      code_1396
		lat      temp0
		lapi     param2
		sat      temp1
		push    
		ldi      16
		div     
		sat      temp2
		pushi    1
		lst      temp1
		ldi      16
		mod     
		shl     
		sat      temp3
		lsp      param1
		dup     
		ldi      1
		eq?     
		bnt      code_1350
		jmp      code_1396
		jmp      code_1390
code_1350:
		dup     
		ldi      2
		eq?     
		bnt      code_1367
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		bnot    
		and     
		push    
		lat      temp2
		sagi     gameFlags
		jmp      code_1390
code_1367:
		dup     
		ldi      0
		eq?     
		bnt      code_137d
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		or      
		push    
		lat      temp2
		sagi     gameFlags
		jmp      code_1390
code_137d:
		dup     
		ldi      3
		eq?     
		bnt      code_1390
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		xor     
		push    
		lat      temp2
		sagi     gameFlags
code_1390:
		toss    
		+at      temp0
		jmp      code_1321
code_1396:
		lat      temp2
		lsgi     gameFlags
		lat      temp3
		and     
		ret     
	)
)

(class KQ5SyncWalk of SyncWalk
	
	(method (doit)
		(if (<= (client loop?) 3) (super doit:))
	)
)

(class KQEgo of Body
	(properties
		name {ego}
		head 0
		caller 0
		lookingDir 1
		normal TRUE
	)
	
	(method (init)
		(if (not cycler)
			(self setCycle: KQ5SyncWalk)
		)
		(super init: &rest)
	)
	
	(method (handleEvent event)
		(if
			(or
				(& (event type?) direction)
				(== (event type?) mouseDown)
				(and
					(== (event type?) userEvent)
					(== (event message?) verbWalk)
				)
			)
			(= gPEventX (event x?))
			(= gPEventY (event y?))
			(super handleEvent: event)
		)
		(if
			(or
				(event claimed?)
				(!= (event type?) userEvent)
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(if (Btst fHoldingCedric)
						(SpeakAudio 82)
					else
						(SpeakAudio 83)
					)
					(event claimed: TRUE)
				)
				(verbUse
					(cond 
						((== (inventory indexOf: (theIconBar curInvIcon?)) iHarp)
							(if (and (!= curRoomNum 90) (!= curRoomNum 85))
								(rm0Sound number: 52 loop: 1 play:)
								(SpeakAudio 136)
								(event claimed: TRUE)
							)
						)
						((== (inventory indexOf: (theIconBar curInvIcon?)) iCloak)
							(cond 
								((Btst fWearingCloak)
									(SpeakAudio 137)
									(event claimed: TRUE)
								)
								((not (OneOf curRoomNum 29 30))
									(SpeakAudio 138)
									(event claimed: TRUE)
								)
							)
						)
						((== (inventory indexOf: (theIconBar curInvIcon?)) iTambourine)
							(if
								(or
									(Btst fWearingCloak)
									(not (OneOf curRoomNum 29 30 85 2 90))
									(and (== curRoomNum 2) (Btst fSnakeGone))
								)
								(rm0Sound number: 50 loop: 1 play:)
								(SpeakAudio 139)
								(event claimed: TRUE)
							)
						)
						((== (inventory indexOf: (theIconBar curInvIcon?)) iHoney)
							(if (!= curRoomNum 24)
								(SpeakAudio 140)
								(event claimed: TRUE)
							)
						)
						((== (inventory indexOf: (theIconBar curInvIcon?)) iBottle)
							(curRoom newRoom: 208)
							(event claimed: TRUE)
						)
						(
							(OneOf
								(inventory indexOf: (theIconBar curInvIcon?))
								iPie iLamb iAmulet iHoney iCheese iWheel
								iElfShoes iWax iPuppet iHammer iLocket iHairPin
								iShoe iFish iCatFish
							)
							(switch (inventory indexOf: (theIconBar curInvIcon?))
								(iPie
									(Bset fHaveEaten)
									(SpeakAudio 141)
									(ego put: iPie 1)
									(event claimed: TRUE)
								)
								(iLamb
									(if (== (++ eatLambCount) 1)
										(SolvePuzzle 4)
										(Bset fHaveEaten)
										(SpeakAudio 142)
										((inventory at: iLamb) cel: 5)
										((inventory at: iLamb) cursor: halfLambCursor yourself:)
										(ego put: iLamb curRoomNum)
										(ego get: iLamb)
									else
										(SpeakAudio 143)
										(ego put: iLamb 1)
									)
									(event claimed: TRUE)
								)
								(iAmulet
									(if (Btst fWearingAmulet)
										(SpeakAudio 144)
									else
										(SpeakAudio 145)
										(rm0Sound priority: 15 number: 65 loop: 1 play:)
										(Bset fWearingAmulet)
									)
									(event claimed: TRUE)
								)
								(else 
									(if (or (Btst fWearingCloak) (not (OneOf curRoomNum 29 30)))
										(SpeakAudio
											(switch (inventory indexOf: (theIconBar curInvIcon?))
												(iCheese 146)
												(iWheel 147)
												(iElfShoes 148)
												(iPuppet 149)
												(iHammer 150)
												(iLocket 151)
												(iHairPin 152)
												(iShoe 153)
												(iWax 154)
												(else  155)
											)
										)
										(event claimed: TRUE)
									)
								)
							)
						)
					)
				)
				(verbTalk
					(if (Btst fHoldingCedric)
						(SpeakAudio 157)
					else
						(SpeakAudio 158)
					)
					(event claimed: TRUE)
				)
				(verbDo
					(if (Btst fHoldingCedric)
						(SpeakAudio 130)
						(event claimed: TRUE)
					else
						(event claimed: FALSE)
					)
				)
			)
		)
	)
	
	(method (setMotion)
		(self
			cycleSpeed: (theGame egoMoveSpeed?)
			moveSpeed: (theGame egoMoveSpeed?)
		)
		(super setMotion: &rest)
	)
)

(instance gControls of Controls)

(instance globalSound of Sound
	(properties
		flags mNOPAUSE
		priority 1
	)
)

(instance globalSound2 of Sound
	(properties
		flags mNOPAUSE
		priority 2
	)
)

(instance globalSound3 of Sound
	(properties
		flags mNOPAUSE
		priority 3
	)
)

(instance globalSound4 of Sound
	(properties
		flags mNOPAUSE
		priority 4
	)
)

(instance rm0Sound of Sound
	(properties
		flags mNOPAUSE
		priority 15
	)
)

(instance KQ5 of Game
	
	(method (init &tmp [str 270] [temp270 20])
		(= global394 1)
		(DoAudio DACFound 1)
		(DoAudio Rate 11025)
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(= isVGA FALSE)
		else
			(= isVGA TRUE)
		)
		(= systemWindow KQ5Window)
		(= doVerbCode kq5DoVerbCode)
		(= ftrInitializer FtrInit)
		(= deathMessage 159)
		(theGame egoMoveSpeed: 2 setSpeed: 1 masterVolume: 12)
		(= pMouse kQPMouse)
		(LoadMany SCRIPT LANGUAGE 756)
		Polygon
		PolyPath
		RFeature
		Timer
		(= theEmptyBagCursor emptyBagCursor)
		(super init:)
		(= version {1.000.052})
		(if (== (StrAt version 0) 120)
			(= debugging TRUE)
		else
			(= debugging FALSE)
		)
		(if (= curSaveDisk (DeviceInfo SaveDevice))
			(Format @temp270 0 0 curSaveDisk)
			(StrCpy curSaveDir @temp270)
		)
		(= showStyle 10)
		(User
			canControl: FALSE
			x: -1
			y: 150
			init: (User inputLineAddr?) 30
			alterEgo: ((= ego KQEgo) looper: MyLooper yourself:)
		)
		(= possibleScore 260)
		(= userFont 4)
		(globalSound owner: self init:)
		(globalSound2 owner: self init:)
		(globalSound3 owner: self init:)
		(globalSound4 owner: self init:)
		(= theMusic globalSound)
		(= theMusic2 globalSound2)
		(= theMusic3 globalSound3)
		(= theMusic4 globalSound4)
		(= theAudio globalAudio)
		(= invCursor theInvCursor)
		(= waitCursor speakCursor)
		(= normalCursor arrowCursor)
		(= crownCursor theCrownCursor)
		(= gameCursor theGameCursor)
		(DoSound SoundOn TRUE)
		(cls)
		((= theIconBar IconBar)
			add:
				(icon0 cursor: walkCursor yourself:)
				(icon1 cursor: eyeCursor yourself:)
				(icon2 cursor: handCursor yourself:)
				(icon3 cursor: talkCursor yourself:)
				(icon4 cursor: arrowCursor yourself:)
				(icon5 cursor: arrowCursor yourself:)
				(icon8 cursor: arrowCursor yourself:)
				(icon9 cursor: helpCursor yourself:)
			eachElementDo: #init
			eachElementDo: #highlightColor 1
			eachElementDo: #lowlightColor (if isVGA 19 else 7)
			curIcon: icon0
			useIconItem: icon4
			helpIconItem: icon9
			disable:
			disable: icon4
		)
		((ScriptID 758) init:)
		(= global103 0)
		(= gGTheGameCursor (= gameCursor 6))
		(self setCursor: theGameCursor)
		(= useObstacles TRUE)
		(if (GameIsRestarting)
			(= globalCedric 1)
		)
		(self newRoom: 99)
	)
	
	(method (doit &tmp egoLoop temp1 [temp2 2])
		(theAudio check:)
		(if
			(and
				(!=
					(= egoLoop (ego loop?))
					(= temp1 (- (NumLoops ego) 1))
				)
				(not (Btst 22))
				(not (ego avoider?))
				(ego normal?)
				(or (not (ego mover?)) (ego isBlocked:))
			)
			(ego
				loop: temp1
				cel: egoLoop
				setMotion: 0
				signal: (& (ego signal?) $fbff)
			)
		)
		(super doit:)
	)
	
	(method (newRoom n)
		(theAudio stop:)
		(if SpeakTimer
			(SpeakTimer dispose:)
		)
		(cls)
		(super newRoom: n)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(theMusic3 stop:)
		(theMusic4 stop:)
		(LoadMany FALSE
			CHASE WANDER 760 762
			SYNC TALKER RANDCYC 759
			SIGHT BLOCK 202 888
			889 764 928 767
			771
		)
		(if (== prevRoomNum 99)
			(if (== howFast 0)
				((ScriptID 755) init:)
			else
				((ScriptID 755 1) init:)
			)
		)
		(LoadMany CURSOR 6 7 8 9)
		(Load FONT 4)
		(if debugOn
			(= debugOn FALSE)
			(SetDebug)
		)
		(if
			(and
				(u> (MemoryInfo FreeHeap) (+ 20 (MemoryInfo LargestPtr)))
				(| $0001 (Palette 4 0 255 100))
				(Print 0 2
					#button {Who cares} 0
					#button {Debug} 1
				)
			)
			(SetDebug)
		)
		(super startRoom: roomNum)
		(if (not (Btst 33))
			(signalView init: posn: 1000 1000 setPri: 15 hide:)
		)
		(= global124 160)
		(= global125 105)
		(features eachElementDo: #init)
	)
	
	(method (restore)
		(DoAudio Stop)
		(super restore: &rest)
	)
	
	(method (save)
		(DoAudio Stop)
		(super save:)
	)
	
	(method (handleEvent event &tmp temp0 evt evtX evtY evtMod [temp5 278])
		(if (event claimed?) (return))
		(switch (event message?)
			(verbUse
				(cond 
					((== (inventory indexOf: (theIconBar curInvIcon?)) iWand)
						(if (Btst fWandRecharged)
							(SpeakAudio 134)
						else
							(SpeakAudio 135)
						)
						(event claimed: TRUE)
					)
					((== (inventory indexOf: (theIconBar curInvIcon?)) iMordackWand)
						(SpeakAudio 156)
						(event claimed: TRUE)
					)
				)
			)
		)
		(switch (event type?)
			((super handleEvent: event))
			(mouseDown
				(if (and (not (event claimed?)) debugging)
					(event localize:)
					(= evtX (event x?))
					(= evtY (event y?))
					(if (& (= evtMod (event modifiers?)) altDown)
						(event claimed: TRUE)
						(while (!= mouseUp ((= evt (Event new:)) type?))
							(evt localize:)
							((User alterEgo?)
								posn: (evt x?) (evt y?)
								setMotion: 0
							)
							(Animate (cast elements?) FALSE)
							(evt dispose:)
						)
						(evt dispose:)
					)
				)
			)
			(keyDown
				(if
					(and
						(not (event claimed?))
						(not global327)
						(User controls?)
					)
					(event claimed: TRUE)
					(switch (event message?)
						(TAB
							(inventory showSelf: ego)
						)
						(SHIFTTAB
							(if (HaveMouse)
								(inventory showSelf: ego)
							)
						)
						(`#1
							((ScriptID 753) doit:)
						)
						(else 
							(if debugging
								((ScriptID 889 0) doit: event)
							)
						)
					)
				)
			)
		)
	)
	
	(method (setCursor form &tmp oldCur)
		(= oldCur theCursor)
		(= theCursor form)
		(return
			(if global400
				(if (IsObject form)
					(form init: &rest)
					(return oldCur)
				)
			else
				(return
					(if (IsObject form)
						(form init:)
						oldCur
					else
						(super setCursor: &rest)
					)
				)
			)
		)
	)
	
	(method (quitGame)
		(DoAudio Play 9254)
		(super
			quitGame: (Print 0 1 #button {Yes} 1 #button {No} 0)
		)
		(DoAudio Stop)
	)
	
	(method (pragmaFail)
		(if (User canInput:)
			(VerbFail)
		)
	)
)

(class SpeakTimer of Timer
	(properties
		theVol 0
	)
	
	(procedure (localproc_14dc &tmp whoCares)
		(= whoCares client)
		(= client 0)
		(if (IsObject whoCares)
			(if (whoCares respondsTo: #timer)
				(whoCares timer: 0)
			)
			(if (whoCares respondsTo: #cue)
				(whoCares cue:)
			)
		)
	)
	
	
	(method (doit &tmp thisTime)
		(cond 
			((!= cycleCnt -1)
				(if (not (-- cycleCnt))
					(localproc_14dc)
				)
			)
			((!= seconds -1)
				(if (!= lastTime (= thisTime (GetTime SYSTIME1)))
					(= lastTime thisTime)
					(if (not (-- seconds))
						(localproc_14dc)
					)
				)
			)
			(
				(or
					(u< (+ ticksToDo lastTime) (GetTime))
					(and
						(u> lastTime (GetTime))
						(u> (+ ticksToDo lastTime) lastTime)
					)
				)
				(= global401 0)
				(theGame masterVolume: theVol)
				(localproc_14dc)
			)
		)
	)
)

(class SpeakTimeOut of TimeOut
	(properties
		timeLeft 0
		theVol 0
	)
	
	(method (doit)
		(if (== (DoAudio Loc) -1)
			(= global401 0)
			(theGame masterVolume: theVol)
			(theDoits delete: self)
		)
	)
	
	(method (set)
		(theDoits add: self)
	)
)

(instance signalView of View
	(properties
		view 942
	)
)

(instance icon0 of IconItem
	(properties
		view 942
		loop 0
		cel 2
		message verbWalk
		signal HIDEBAR
		helpStr 9229
		maskView 942
		maskCel 11
	)
)

(instance icon1 of IconItem
	(properties
		view 942
		loop 0
		cel 3
		message verbLook
		signal HIDEBAR
		helpStr 9230
		maskView 942
		maskCel 11
	)
)

(instance icon2 of IconItem
	(properties
		view 942
		loop 0
		cel 4
		message verbDo
		signal HIDEBAR
		helpStr 9231
		maskView 942
		maskCel 11
	)
)

(instance icon3 of IconItem
	(properties
		view 942
		loop 0
		cel 5
		message verbTalk
		signal HIDEBAR
		helpStr 9232
		maskView 942
		maskCel 11
	)
)

(instance icon4 of IconItem
	(properties
		view 942
		loop 0
		cel 6
		message verbUse
		signal HIDEBAR
		helpStr 9233
		maskView 942
		maskCel 11
	)
)

(instance icon5 of IconItem
	(properties
		view 942
		loop 0
		cel 7
		type nullEvt
		message verbNone
		signal (| HIDEBAR IMMEDIATE)
		helpStr 9234
		maskView 942
		maskCel 11
	)
	
	(method (select)
		(if (super select:)
			(inventory showSelf: ego)
		)
	)
)

(instance icon8 of IconItem
	(properties
		view 942
		loop 0
		cel 8
		message 8
		signal (| HIDEBAR IMMEDIATE)
		helpStr 9235
		maskView 942
		maskCel 11
	)
	
	(method (select)
		(if (super select:)
			(theIconBar hide:)
			(if (== howFast 0)
				((ScriptID 755) show:)
			else
				((ScriptID 755 1) show:)
			)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 942
		loop 0
		cel 9
		message verbHelp
		signal $0002
		helpStr 9236
		maskView 942
		maskCel 11
	)
)

(instance deathIcon of DCIcon)

(instance talkerIcon of DCIcon
	(properties
		state $0010
	)
)

(instance kq5DoVerbCode of Code
	
	(method (doit)
		(theGame pragmaFail:)
	)
)

(instance globalAudio of Audio)

(instance FtrInit of Code
	
	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 90)
		)
		(if (== (obj actions?) ftrDefault)
			(obj actions: 0)
		)
	)
)

(instance kQPMouse of PseudoMouse
	
	(method (handleEvent event &tmp temp0)
		(= temp0 ((theIconBar curIcon?) message?))
		((theIconBar curIcon?) message: verbNone)
		(super handleEvent: event)
		((theIconBar curIcon?) message: temp0)
	)
)

(instance arrowCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 7
	)
	
	(method (init)
		(if global400
			(self number: ARROW_CURSOR yourself:)
		)
		(super init: &rest)
	)
)

(instance theGameCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 5
		x 8
		y 9
	)
	
	(method (init)
		(if global400
			(self number: 456 yourself:)
		)
		(super init: &rest)
	)
)

(instance theInvCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 6
	)
	
	(method (init)
		(if global400
			(self number: 69 yourself:)
		)
		(super init: &rest)
	)
)

(instance walkCursor of KQCursor
	(properties
		view 942
		loop 1
		y 20
	)
	
	(method (init)
		(if global400
			(self number: 6 yourself:)
		)
		(super init: &rest)
	)
)

(instance eyeCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 1
		x 15
		y 12
	)
	
	(method (init)
		(if global400
			(self number: 7 yourself:)
		)
		(super init: &rest)
	)
)

(instance handCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 2
		x 14
		y 10
	)
	
	(method (init)
		(if global400
			(self number: 8 yourself:)
		)
		(super init: &rest)
	)
)

(instance talkCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 3
		x 10
		y 13
	)
	
	(method (init)
		(if global400
			(self number: 9 yourself:)
		)
		(super init: &rest)
	)
)

(instance helpCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 4
		x 10
		y 15
	)
	
	(method (init)
		(if global400
			(self number: 70 yourself:)
		)
		(super init: &rest)
	)
)

(instance speakCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 5
		x 8
		y 9
	)
	
	(method (init)
		(if global400
			(self number: 456 yourself:)
		)
		(super init: &rest)
	)
)

(instance theCrownCursor of KQCursor
	(properties
		view 942
		loop 1
		cel 8
		x 5
		y 5
	)
	
	(method (init)
		(if global400
			(self number: 997 yourself:)
		)
		(super init: &rest)
	)
)

(instance halfLambCursor of KQCursor
	(properties
		view 941
		loop 1
		cel 7
	)
	
	(method (init)
		(if global400
			(self number: 52 yourself:)
		)
		(super init: &rest)
	)
)

(instance emptyBagCursor of KQCursor
	(properties
		view 941
		loop 5
		cel 3
		x 9
		y 10
	)
	
	(method (init)
		(if global400
			(self number: 54 yourself:)
		)
		(super init: &rest)
	)
)
