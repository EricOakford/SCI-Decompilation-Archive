;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh) (include menu.sh)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	LSL3 0
	NormalEgo 1
	NormalActor 2
	HandsOff 3
	HandsOn 4
	cls 5
	Ok 6
	ItIs 7
	YouAre 8
	GoodIdea 9
	NotClose 10
	AlreadyTook 11
	DontHave 12
	NotifyScript 13
	HaveMem 14
	proc0_15 15
	SetOrchidTimer 16
	proc0_17 17
	proc0_18 18
	Bset 19
	Bclr 20
	Btoggle 21
	Btst 22
	CheckItemOwner 23
	SetItemOwner 24
	SetPrintTime 25
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
	waitCursor =  HAND_CURSOR
	userFont =  USERFONT
	smallFont =  4
	lastEvent
	modelessDialog
	bigFont =  USERFONT
	volume =  12
	version =  {LSL3}
	locales
	curSaveDir
		global31
		global32
		global33
		global34
		global35
		global36
		global37
		global38
		global39
		global40
		global41
		global42
		global43
		global44
		global45
		global46
		global47
		global48
		global49
	aniThreshold =  10
	perspective
	features
	sortedFeatures
	useSortedFeatures
	demoScripts
	egoBlindSpot
	overlays =  -1
	doMotionCue
	systemWindow
	demoDialogTime =  3
	currentPalette
	modelessPort
		global63
		global64
		global65
		global66
		global67
		global68
		global69
		global70
		global71
		global72
		global73
		global74
		global75
		global76
		global77
		global78
		global79
		global80
		global81
		global82
		global83
		global84
		global85
		global86
		global87
		global88
		global89
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
	currentStatus
	currentEgoView
	gameSeconds
	gameMinutes
	gameHours
	oldSysTime
	roomSeconds
	global108
	orchidSeconds
	orchidMinutes
	gameFlags ;16 * 7 = 102 flags
		global112
		global113
		global114
		global115
		global116
		global117
	currentEgo
	egoName
	global120
	global121
	global122
	machineSpeed
	filthLevel
	global125
	global126
	global127
	bambooStalksSeen
	oldScore
	dollars
	music
	tawniState
	programControl
	newspaperState
	lawyerState
	vendorView
	oldSpeed
	gGEgoX
	gGEgoY
	showroomState
	myTextColor
	myBackColor
	gADoor
	playingAsPatti
	soundFX
	printTime
	lockerNum1
	lockerNum2
	lockerNum3
	
	;amount of exercise needed to become fit
	requiredPoundsPumped
	requiredLegCurls
	requiredPullUps
	requiredBarPulls
	
	larryBuffed
	gGEgoLoop
	oldStatus
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
	filthStr
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
	expletiveStr
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
	expletive
	introductoryPhrase
	minutesBetweenReminders
	autoSaveTimer
	secondsBetweenReminders
	global224
	global225
	global226
	global227
	global228
	global229
	global230
	global231
	global232
)
(procedure (NormalEgo theLoop theView)
	(HandsOn)
	(ego edgeHit: 0)
	(switch argc
		(0
			(NormalActor ego (ego loop?)
				(if (> argc 1) theView else currentEgoView)
			)
		)
		(1 (NormalActor ego theLoop
				(if (> argc 1) theView else currentEgoView)
			)
		)
		(2
			(NormalActor ego theLoop theView)
		)
	)
)

(procedure (NormalActor theActor theLoop theView)
	(if (> argc 1) (theActor loop: theLoop))
	(if (> argc 2) (theActor view: theView))
	(theActor
		setLoop: -1
		setPri: -1
		setStep: 3 2
		setCycle: Walk
		illegalBits: cWHITE
		cycleSpeed: 0
		moveSpeed: 0
		ignoreActors: FALSE
	)
)

(procedure (HandsOff)
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(ego setMotion: 0)
)

(procedure (cls)
	(if modelessDialog
		(modelessDialog dispose:)
	)
)

(procedure (Ok)
	(Print 0 170)
)

(procedure (ItIs)
	(Print 0 171)
)

(procedure (YouAre)
	(Print 0 172)
)

(procedure (GoodIdea)
	(Print 0 173)
)

(procedure (NotClose)
	(Print 0 174)
)

(procedure (AlreadyTook)
	(Print 0 175)
)

(procedure (DontHave)
	(Print 0 176)
)

(procedure (NotifyScript i)
	(= i (ScriptID i))
	(i notify: &rest)
)

(procedure (HaveMem howMuch)
	(return
		(if (> (MemoryInfo FreeHeap) howMuch)
			(return TRUE)
		else
			(Print 0 57)
			(return FALSE)
		)
	)
)

(procedure (proc0_15 param1)
	(if param1
		((View new:)
			view: (param1 view?)
			loop: (param1 loop?)
			cel: (param1 cel?)
			setPri: (param1 priority?)
			posn: (param1 x?) (param1 y?)
			addToPic:
		)
		(param1 posn: (param1 x?) (+ 1000 (param1 y?)))
	)
)

(procedure (SetOrchidTimer minutes seconds param3)
	(= orchidMinutes minutes)
	(= orchidSeconds (* 10 (+ param3 (* seconds 60))))
)

(procedure (proc0_17 &tmp [temp0 70])
)

(procedure (proc0_18 &tmp [temp0 50])
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

(procedure (Btoggle flagEnum) ;this procedure doesn't seem to be used
	(= [gameFlags (/ flagEnum 16)]
		(^
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Btst flagEnum)
	(return
		(if
			(&
				[gameFlags (/ flagEnum 16)]
				(>> $8000 (mod flagEnum 16))
			)
			TRUE
		else
			FALSE
		)
	)
)

(procedure (CheckItemOwner item owner)
	(return
		(==
			((inventory at: item) owner?)
			(if (< argc 2) curRoomNum else owner)
		)
	)
)

(procedure (SetItemOwner item owner)
	((inventory at: item)
		owner: (if (< argc 2) curRoomNum else owner)
	)
)

(procedure (SetPrintTime seconds)
	;sets the time a message is displayed, based on its length.
	(return (+ 3 (/ (StrLen seconds) printTime)))
)

(instance LSL3 of Game
	(properties)
	
	(method (init &tmp startingRoom)
		((= systemWindow theWindow)
			color: (= myTextColor vBLUE)
			back: (= myBackColor vWHITE)
		)
		(super init:)
		(= introductoryPhrase {"My name is Larry; Larry Laffer."})
		(= version {1.021})
		(= volume 15)
		(DoSound ChangeVolume volume)
		(StatusLine code: statusCode)
		(TheMenuBar init:)
		(scoreSound owner: self init:)
		((= music theMusic) owner: self init:)
		((= soundFX theSoundFX) owner: self init:)
		(User alterEgo: (= ego egoObj) blocks: 0 y: 150)
		(if (HaveMouse)
			(theGame setCursor: normalCursor TRUE)
		else
			(theGame setCursor: normalCursor TRUE 304 174)
		)
		(Load FONT (= bigFont SYSFONT))
		(Load FONT (= userFont USERFONT))
		(Load FONT (= smallFont 4))
		(Load FONT 999)
		(Load CURSOR normalCursor)
		(Load CURSOR waitCursor)
		(Load CURSOR 666)
		(Load CURSOR 992)
		(Inventory
			add:
				Nothing
				Credit_Card
				Ginsu_Knife
				Granadilla_Wood_
				Native_Grass
				Soap-On-A-Rope
				A_Twenty_Dollar_Bill
				Land_Deed
				Beach_Towel
				Spa_Keycard
				Divorce_Decree
				some_Orchids
				Penthouse_Key
				Bottle_of_Wine_
				Panties
				Pantyhose
				Bra
				Dress
				Magic_Marker
				Coconuts
				Marijuana
		)
		(if (GameIsRestarting)
			(= startingRoom 290)
		else
			(= startingRoom 120)
		)
		(self newRoom: startingRoom)
	)
	
	(method (doit &tmp [str 50])
		(super doit:)
		(if programControl (User canControl: FALSE canInput: FALSE))
		(if (!= oldSysTime (= oldSysTime (GetTime TRUE)))
			(if (>= (++ gameSeconds) 60)
				(= gameSeconds 0)
				(if (>= (++ gameMinutes) 60)
					(= gameMinutes 0)
					(++ gameHours)
				)
			)
			(++ roomSeconds)
			(if
				(and
					(< score 20)
					(> gameMinutes 19)
					(> roomSeconds 8)
					(< roomSeconds 69)
					(User canControl:)
					(== gameSeconds 1)
				)
				(++ gameSeconds)
				(Print 0 0)
				(Print 0 1 #at -1 144)
			)
			(if
				(and
					(not (Btst fAutoSaveDisabled))
					minutesBetweenReminders
					(>= (++ secondsBetweenReminders) 60)
				)
				(= secondsBetweenReminders 0)
				(if (>= (++ autoSaveTimer) minutesBetweenReminders)
					(= autoSaveTimer 0)
					(if
						(Print
							(Format @str 0 2
								minutesBetweenReminders
								(if (== minutesBetweenReminders 1) {} else {s})
							)
							#title {AutoSave\05 Warning!}
							#icon 52 0 0
							#font bigFont
							#button {Save Now!} 1
							#button {Screw it.} 0
							#at -1 10
						)
						(theGame save:)
					)
				)
			)
		)
		(if (> oldScore score)
			(if (> machineSpeed 39)
				(-- oldScore)
			else
				(= oldScore score)
			)
			(StatusLine doit:)
		)
		(if (< oldScore score)
			(if (> machineSpeed 39)
				(++ oldScore)
			else
				(= oldScore score)
			)
			(StatusLine doit:)
		)
		(cond 
			((Btst fCursorHidden)
				(self setCursor: 666 TRUE)
			)
			((== (User controls?) FALSE)
				(if (User canInput?)
					(self setCursor: 992 (HaveMouse))
				else
					(self setCursor: waitCursor (HaveMouse))
				)
			)
			(else
				(self setCursor: normalCursor (HaveMouse))
			)
		)
		(return
			(if (and orchidMinutes orchidSeconds)
				(-- orchidSeconds)
				else 0
			)
		)
	)
	
	(method (replay)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(SetMenu soundI
			#text (if (DoSound SoundOn) {Turn Off} else {Turn On})
		)
		(super replay:)
	)
	
	(method (newRoom roomNum theStyle)
		(if
			(or
				(and
					(not
						(OneOf roomNum
							200 203 210 213 216 220 230 235 240
							245 250 253 300 305 310
						)
					)
					(OneOf curRoomNum
						200 203 210 213 216 220 230 235 240 245
						250 253 300 305 310
					)
				)
				(and
					(not (OneOf roomNum 400 410 415 416 420 460))
					(OneOf curRoomNum 400 410 415 416 420 460)
				)
				(and
					(not (OneOf roomNum 360 370 375 380 390))
					(OneOf curRoomNum 360 370 375 380 390)
				)
				(and
					(not (OneOf roomNum 510 520 523 540 550))
					(OneOf curRoomNum 510 520 523 540 550)
				)
				(and
					(not (OneOf roomNum 610 620 630 640 650))
					(OneOf curRoomNum 610 620 630 640 650)
				)
			)
			(music fade:)
		)
		(Bclr fSaveDisabled)
		(Bclr fCursorHidden)
		(cls)
		(Load FONT bigFont)
		(Load FONT userFont)
		(Load FONT smallFont)
		(Load FONT 999)
		(Load CURSOR normalCursor)
		(Load CURSOR waitCursor)
		(Load CURSOR 666)
		(Load CURSOR 992)
		(super newRoom: roomNum)
		(= roomSeconds 0)
		(if (< argc 2)
			(= showStyle (Random 0 5))
		else
			(= showStyle theStyle)
		)
	)
	
	(method (startRoom roomNum)
		(LoadMany FALSE FILE JUMP EXTRA WINDOW TIMER FOLLOW REVERSE DCICON 21 50 51)
		(DisposeScript LOADMANY)
		(if debugOn
			(= debugOn FALSE)
			(SetDebug)
		)
		(soundFX stop: number: 1)
		(super startRoom: roomNum &rest)
		(if
		(and (not (OneOf roomNum 530 260 420)) debugging)
			(curRoom setLocales: DEBUG)
		)
		(if (Btst fQAEnabled)
			(curRoom setLocales: DEBUG_22)
		)
		(cond 
			(
				(OneOf roomNum
					200 203 210 213 216 220 230
					235 240 245 250 253 300 305
					310
				)
				(curRoom setLocales: VILLAGE)
			)
			((OneOf roomNum 360 370 375 380 390) (curRoom setLocales: FATCITY))
			((OneOf roomNum 400 410 415 416 420 460) (curRoom setLocales: CASINO))
			((OneOf roomNum 510 520 523 540 550) (curRoom setLocales: JUNGLE))
			((OneOf roomNum 610 620 630 640 650) (curRoom setLocales: STUDIO))
		)
		(if (or (== currentStatus egoSHOWGIRL) (== currentEgoView 708))
			(ego baseSetter: NormalBase)
		)
	)
	
	(method (changeScore delta)
		(= score (+ score delta))
		(if (> delta 0)
			(scoreSound playMaybe:)
		)
	)
	
	(method (handleEvent event &tmp [temp0 2] i [temp3 3] [temp6 50])
		(super handleEvent: event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'ascot/backdrop')
				(if (= debugging (^ debugging TRUE))
					(Print 0 14)
				else
					(Print 0 15)
				)
			)
			(
			(or (Said 'caress/ginsu') (Said 'sharpen/ginsu'))
				(cond 
					((not (ego has: iGinsuKnife)) (DontHave))
					((== (Ginsu_Knife view?) 21) (ItIs))
					((!= curRoomNum 250) (Print 0 16))
				)
			)
			(
			(or (Said 'backdrop/*/bottle') (Said 'fill/bottle')) (Print 0 17))
			((Said '(drain<out),drain/beer,bottle')
				(cond 
					((not (ego has: iBottleOfWine)) (DontHave))
					((not playingAsPatti) (Print 0 18))
					((== (Bottle_of_Wine_ view?) 28) (Print 0 19))
					(else
						(Bottle_of_Wine_ view: 28)
						(Format (Bottle_of_Wine_ name?) 0 20)
						(Print 0 21)
					)
				)
			)
			((Said 'carve,carve>')
				(cond 
					((not (ego has: iGinsuKnife)) (Print 0 22))
					((== (Ginsu_Knife view?) 2) (Print 0 23))
					((Said '[/!*]') (Print 0 24))
					((Said '/blade') (Print 0 25))
					((not (Said '/carving,granadilla')) (Print 0 26))
					((not (ego has: iWood)) (Print 0 27))
					(
						(or
							(== (Granadilla_Wood_ view?) 22)
							(== (Granadilla_Wood_ view?) 34)
						)
						(Print 0 28)
					)
					((or (== currentStatus egoNORMAL) (== currentStatus egoNATIVE)) (Ok) (theGame setScript: (ScriptID 43)))
					(else (GoodIdea))
				)
				(event claimed: TRUE)
			)
			(
				(or
					(Said 'use/flower/lei<make')
					(Said 'weave,make/flower,lei')
				)
				(cond 
					((not (ego has: iOrchids)) (Print 0 29))
					((== (some_Orchids view?) 26) (Print 0 30))
					((!= currentStatus egoNORMAL) (GoodIdea))
					(else (Ok) (theGame setScript: (ScriptID 42)))
				)
			)
			(
			(Said 'wear,(alter<in),(backdrop<on)/flower,lei')
				(cond 
					((not (ego has: iOrchids)) (Print 0 31))
					((!= (some_Orchids view?) 26) (Print 0 32))
					((!= currentStatus egoNORMAL) (GoodIdea))
					(else (Print 0 33))
				)
			)
			(
				(or
					(Said 'use/blade/skirt<make')
					(Said 'weave,make/blade,skirt')
				)
				(cond 
					((not (ego has: iGrass)) (Print 0 34))
					((== (Native_Grass view?) 23) (Print 0 35))
					((!= currentStatus egoNORMAL) (GoodIdea))
					(else (Ok) (theGame setScript: (ScriptID 44)))
				)
			)
			(
				(or
					(Said 'get/naked')
					(Said '(get<off),drain/cloth,cloth')
				)
				(Print 0 36)
			)
			((Said 'alter/cloth,cloth') (Print 0 37))
			(
			(Said 'wear,(alter<in),(backdrop<on)/blade,skirt')
				(cond 
					((not (ego has: iGrass)) (Print 0 31))
					((not (== (Native_Grass view?) 23)) (Print 0 38))
					(else (Print 0 39))
				)
			)
			((and (ego has: iSoap) (Said 'use/soap')) (Print 0 40))
			(
				(or
					(Said 'unknownnumber/')
					(Said '/unknownnumber')
					(Said '//unknownnumber')
				)
				(Print 0 41)
			)
			(
				(and
					(ego has: iDivorceDecree)
					(not (ego has: iSpaKeycard))
					(Said 'look,look/decree,document,document')
				)
				(ego get: iSpaKeycard)
				(theGame changeScore: 100)
				(Print 0 42)
			)
			(
				(and
					(ego has: iSpaKeycard)
					(or
						(Said 'look<back/keycard,card')
						(Said 'look/back/keycard,card')
						(Said 'drag/keycard,card')
						(Said 'look/combination/keycard,card')
					)
				)
				(Ok)
				(theGame setScript: (ScriptID 45))
			)
			((Said 'count>')
				(= i (inventory saidMe:))
				(cond 
					((Said '[/!*]') (Print 0 43))
					((not i) (event claimed: TRUE) (Printf 0 44 (Random 10 999)))
					((!= (inventory indexOf: i) iMoney) (Print 0 45))
					((not (i ownedBy: ego)) (Print 0 46))
					(else (Printf 0 47 dollars))
				)
			)
			((or (Said 'give/bill,buck') (Said 'bribe'))
				(cond 
					((not (ego has: iMoney)) (Print 0 48))
					((== (A_Twenty_Dollar_Bill view?) 24)
						(Print 0 49 #icon (A_Twenty_Dollar_Bill view?) 0 0)
						(Print 0 50 #at -1 144)
					)
					((== (A_Twenty_Dollar_Bill view?) 25)
						(Print 0 51 #icon (A_Twenty_Dollar_Bill view?) 0 0)
						(Print 0 52)
					)
					(else
						(Print 0 53 #icon (A_Twenty_Dollar_Bill view?) 0 0)
						(Print 0 54)
					)
				)
			)
			(
				(and
					(ego has: iGrass)
					(== (Native_Grass view?) 23)
					(or
						(Said 'get/dress')
						(Said 'get<dress')
						(Said
							'wear,alter,(get<off),drain,(backdrop<on)/skirt,cloth,cloth'
						)
					)
				)
				(Print 0 55)
			)
			((and (ego has: iBra) (Said 'backdrop//(bra)>'))
				(= i (inventory saidMe:))
				(cond 
					((Said '[/!*]') (Print 0 56))
					((not i) (event claimed: TRUE) (Print 0 57))
					((not (i ownedBy: ego)) (DontHave))
					(else (Print 0 58 #icon 16 0 0) (Print 0 59 #at -1 144))
				)
			)
			(
				(and
					(ego has: iGinsuKnife)
					(or (Said 'use/ginsu') (Said 'attack'))
				)
				(Print 0 60)
			)
			((Said 'open,(look<in)>')
				(= i (inventory saidMe:))
				(cond 
					((Said '[/!*]') (Print 0 61))
					((not i) (event claimed: TRUE) (Print 0 57))
					((not (i ownedBy: ego)) (Print 0 62))
					(else
						(switch (inventory indexOf: i)
							(iOrchids (Print 0 63 #icon 11 0 0))
							(iBottleOfWine
								(switch (Bottle_of_Wine_ view?)
									(28 (Print 0 64 #icon 28 0 0))
									(29 (Print 0 65 #icon 29 0 0))
									(else 
										(Print 0 66 #icon 13 0 0)
									)
								)
							)
							(iPanties (Print 0 67 #icon 14 0 0))
							(iPantyhose (Print 0 68 #icon 15 0 0))
							(iBra (Print 0 69 #icon 16 0 0))
							(iDress (Print 0 70 #icon 17 0 0))
							(iCoconuts (Print 0 71 #icon 19 0 0))
							(else  (Print 0 62) (proc0_18))
						)
					)
				)
			)
			((Said 'hello') (Print 0 72))
			((or (Said '/bye') (Said 'bye')) (Print 0 15))
			((Said 'thank') (Print 0 73))
			((Said 'knock')
				(Print 0 74)
				(Print (Format @temp6 0 75 currentEgo) #at -1 144)
			)
			((Said 'attack') (Print 0 60))
			(
				(or
					(Said 'go/bathroom')
					(Said 'leak')
					(Said 'get/leak')
				)
				(if playingAsPatti
					(Print 0 76)
					(Print 0 77 #at -1 144)
				else
					(Print 0 78)
				)
			)
			((Said 'climb>')
				(cond 
					((Said '/wall,building') (Print 0 79))
					(playingAsPatti (Print 0 80))
					(else (Print 0 81))
				)
				(event claimed: 1)
			)
			((or (Said '//larry') (Said '/larry'))
				(cond 
					((not playingAsPatti) (Print 0 82))
					((< curRoomNum 590) (Print 0 83))
					(else (Print 0 84))
				)
			)
			((Said 'jump,dance') (Print 0 85))
			((Said 'sing') (Print 0 86))
			((Said 'delay') (Print 0 87))
			((Said 'pick') (Print 0 88))
			((Said 'holler') (Print 0 89))
			((Said 'rob') (Print 0 90))
			((or (Said 'n') (Said 'affirmative')) (Ok))
			((Said 'borrow') (Print 0 91))
			((Said 'cheat') (Print 0 92) (Print 0 93 #at -1 144) (= quit 1))
			((Said '(backdrop<on),wear>')
				(cond 
					((Said '[/!*]') (Print 0 94))
					((= i (inventory saidMe:))
						(cond 
							(
							(not (ego has: (inventory indexOf: i))) (DontHave))
							(
								(or
									(== i 16)
									(== i 17)
									(== i 14)
									(== i 15)
								)
								(YouAre)
							)
							(else (Print 0 95))
						)
					)
					(else (Print 0 96) (event claimed: 1))
				)
			)
			((Said 'backdrop>')
				(cond 
					((Said '[/!*]') (Print 0 97))
					((= i (inventory saidMe:))
						(if
						(not (ego has: (inventory indexOf: i)))
							(DontHave)
						else
							(Print 0 98)
						)
					)
					(else (Print 0 99) (event claimed: 1))
				)
			)
			((Said 'throw>')
				(cond 
					((Said '[/!*]') (Print 0 100))
					((= i (inventory saidMe:))
						(if
						(not (ego has: (inventory indexOf: i)))
							(DontHave)
						else
							(Print 0 101)
						)
					)
					(else (Print 0 102) (event claimed: 1))
				)
			)
			((Said 'smell')
				(Print 0 103)
				(cond 
					((Btst fNotShower)
						(Print 0 104)
					)
					((Btst fNotUseSoap)
						(Print 0 105)
					)
					((Btst fNotUseDeodorant)
						(Print 0 106)
					)
				)
			)
			((Said 'whistle') (Print 0 107))
			((Said 'ask/job') (Print 0 108) (Print 0 109 #at -1 144))
			(
				(and
					playingAsPatti
					(or (Said '/arnold') (Said '//arnold'))
				)
				(Print 0 110)
			)
			((Said 'laugh') (Print 0 111))
			((Said 'eat') (Print 0 112))
			((Said 'lie,lie') (Print 0 113))
			((Said 'aid/i,self') (Print 0 114))
			((Said 'aid') (Print 0 115))
			((Said 'explore>')
				(if (Said '/cloth,panties,entertainer,larry')
					(Print 0 116)
					(inventory showSelf: ego)
				else
					(event claimed: 1)
					(Print 0 117)
				)
			)
			((or (Said '/bang/ya') (Said 'bang/ya')) (Print 0 118))
			((Said 'bang/*') (Print 0 119))
			(
			(or (Said 'caress/i,larry,self') (Said 'jack')) (Print 0 120))
			((Said 'caress,caress,embrace,look/clit') (Printf 0 121 currentEgo))
			((Said 'eat/babe') (Print 0 122))
			(
			(or (Said 'look<in/blouse') (Said 'look<up/skirt')) (Print 0 123))
			((Said 'drink') (Print 0 124))
			((or (Said 'use/key') (Said 'unbolt')) (Print 0 125))
			((or (Said '/book') (Said '//book')) (Print 0 126))
			((Said 'hear') (Print 0 127))
			((Said 'embrace') (Print 0 128))
			(
				(or
					(Said 'eat,bang/i')
					(Said 'crap,leak,bang,fart')
					(Said '/clit,crap,leak,bang,fart,asshole')
					(Said '//clit,crap,leak,bang,fart,asshole')
				)
				(Print 0 129)
			)
			((or (Said '/hell') (Said '//hell')) (Print 0 130))
			((Said 'look>')
				(cond 
					(
					(Said '/self,larry,entertainer,i,blouse,cloth,panties')
						(if (not playingAsPatti)
							(Print 0 131)
						else
							(Print 0 132)
						)
					)
					((and (== (ego view?) 718) (Said '/sunglass')) (Print 0 133))
					((Said '/ankle,sandal')
						(if (== (ego view?) 718)
							(Print 0 134)
						else
							(Print 0 135)
						)
					)
					((Said '/bush,palm') (Print 0 136))
					((Said '/man,babe,couple') (Print 0 137))
					((Said '/wall,building') (Print 0 138))
					((Said '/carpet,down') (Print 0 139))
					((Said '/air,ceiling') (Print 0 140))
					((Said '/boob') (if playingAsPatti (Print 0 141) else (Print 0 142)))
					((Said '/ass') (if playingAsPatti (Print 0 143) else (Print 0 144)))
					((Said '<in/cup') (Print 0 145))
					((= i (inventory saidMe:))
						(if (i ownedBy: ego)
							(i showSelf:)
						else
							(Print 0 62)
						)
					)
					(else
						(switch (Random 42 44)
							(42 (Print 0 146))
							(43 (Print 0 147))
							(44 (Print 0 62))
						)
						(event claimed: 1)
						(proc0_18)
					)
				)
			)
			(
			(or (Said 'use,buy/bill,tips,buck') (Said 'buy')) (if (ego has: 6) (Print 0 148) else (Print 0 149)))
			((Said 'use>')
				(= i (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((Said '[/!*]') (Print 0 150))
					((not i) (Print 0 57))
					((not (i ownedBy: ego)) (DontHave))
					(else (Print 0 151) (proc0_18))
				)
				(event claimed: 1)
			)
			((Said 'give>')
				(= i (inventory saidMe:))
				(event claimed: 0)
				(cond 
					((Said '/*[/!*]') (Print 0 152))
					((Said '[/!*]') (Print 0 153))
					((not i) (Print 0 154))
					((not (i ownedBy: ego)) (DontHave))
					(else (Print 0 155) (proc0_18))
				)
				(event claimed: TRUE)
			)
			((Said 'get<down') (Print 0 156))
			((Said 'get<up') (Print 0 157))
			((Said 'get>')
				(cond 
					((Said '[/!*]') (Print 0 158))
					(
						(and
							(= i (inventory saidMe:))
							(i ownedBy: ego)
						)
						(Print 0 159)
					)
					((== i iWood) (Print 0 160))
					(else
						(switch (Random 33 35)
							(33 (Print 0 161))
							(34 (Print 0 162))
							(35 (Print 0 163))
						)
						(proc0_18)
					)
				)
				(event claimed: TRUE)
			)
			((= i (inventory saidMe:))
				(if (not (i ownedBy: ego))
					(DontHave)
				else
					(Print 0 164)
				)
			)
			((Said 'address>')
				(if (Said '[/!*]')
					(Print 0 152)
				else
					(Print 0 165)
					(Print 0 109 #at -1 144)
					(event claimed: TRUE)
				)
			)
			(
				(or
					(Said '/clit,crap,leak,bang,asshole,boob,ass,asshole')
					(Said '//clit,crap,leak,bang,asshole,boob,ass,asshole')
				)
				(Print 0 166)
			)
			(
			(or (Said '//babe') (Said '/babe/') (Said '/babe')) (Print 0 167))
		)
	)
	
	(method (wordFail word &tmp [str 50])
		(switch (Random 0 4)
			(0
				(Print (Format @str 0 3 word))
			)
			(1
				(Print (Format @str 0 4 word))
			)
			(2
				(Print (Format @str 0 5 word))
			)
			(3
				(Print (Format @str 0 6 word))
			)
			(else 
				(Print (Format @str 0 7 word))
			)
		)
	)
	
	(method (syntaxFail &tmp [str 40])
		(switch (Random 0 2)
			(0 (Print 0 8))
			(1 (Print 0 9))
			(else  (Print 0 10))
		)
	)
	
	(method (pragmaFail &tmp [str 40])
		(switch (Random 0 2)
			(0 (Print 0 11))
			(1 (Print 0 12))
			(else  (Print 0 13))
		)
	)
)

(class Iitem of InvItem
	
	(method (showSelf)
		(Print 30 view
			#title name
			#icon view 0 0
		)
	)
)

(instance Nothing of Iitem
	(properties)
)

(instance Credit_Card of Iitem
	(properties
		view 1
		name "Credit Card"
	)
	
	(method (saidMe)
		(return
			(if (ego has: iSpaKeycard)
				(return FALSE)
			else
				(return (Said '/card[<credit]'))
			)
		)
	)
)

(instance Ginsu_Knife of Iitem
	(properties
		said '/ginsu'
		view 2
		name "Ginsu Knife"
	)
)

(instance Granadilla_Wood_ of Iitem
	(properties
		view 3
		name "Granadilla Wood_"
	)
	
	(method (saidMe)
		(if (== view 22)
			(Said '/granadilla,carving')
			(return)
		else
			(Said '/granadilla')
			(return)
		)
	)
)

(instance Native_Grass of Iitem
	(properties
		owner 230
		view 4
		name "Native Grass"
	)
	
	(method (saidMe)
		(if (== view 23)
			(Said '/skirt')
			(return)
		else
			(Said '/blade')
			(return)
		)
	)
)

(instance Soap-On-A-Rope of Iitem
	(properties
		said '/soap'
		owner 253
		view 5
	)
)

(instance A_Twenty_Dollar_Bill of Iitem
	(properties
		view 6
		name "A Twenty Dollar Bill"
	)
	
	(method (saidMe)
		(cond 
			((== view 24) (Said '/buck,500,500') (return))
			((== view 25) (Said '/buck,tips,jar,43') (return))
			(else (Said '/buck,20,bill,20') (return))
		)
	)
)

(instance Land_Deed of Iitem
	(properties
		said '/deed'
		view 7
		name "Land Deed"
	)
)

(instance Beach_Towel of Iitem
	(properties
		said '/towel'
		view 8
		name "Beach Towel"
	)
)

(instance Spa_Keycard of Iitem
	(properties
		said '/keycard,card'
		view 9
		name "Spa Keycard"
	)
)

(instance Divorce_Decree of Iitem
	(properties
		said '/decree,decree'
		view 10
		name "Divorce Decree"
	)
)

(instance some_Orchids of Iitem
	(properties
		owner 235
		view 11
		name "some Orchids"
	)
	
	(method (saidMe)
		(if (== view 26)
			(Said '/flower,lei')
			(return)
		else
			(Said '/flower')
			(return)
		)
	)
)

(instance Penthouse_Key of Iitem
	(properties
		said '/key'
		owner 450
		view 12
		name "Penthouse Key"
	)
)

(instance Bottle_of_Wine_ of Iitem
	(properties
		view 13
		name "Bottle of Wine_"
	)
	
	(method (saidMe)
		(cond 
			((== view 28) (Said '/bottle') (return))
			((== view 29) (Said '/bottle,water') (return))
			(else (Said '/bottle,beer') (return))
		)
	)
)

(instance Panties of Iitem
	(properties
		said '/panties'
		owner 484
		view 14
	)
)

(instance Pantyhose of Iitem
	(properties
		said '/hose'
		owner 484
		view 15
	)
)

(instance Bra of Iitem
	(properties
		said '/bra'
		owner 484
		view 16
	)
)

(instance Dress of Iitem
	(properties
		said '/dress'
		owner 484
		view 17
	)
)

(instance Magic_Marker of Iitem
	(properties
		said '/marker'
		view 18
		name "Magic Marker"
	)
)

(instance Coconuts of Iitem
	(properties
		said '/coconut'
		owner 530
		view 19
	)
)

(instance Marijuana of Iitem
	(properties
		said '/dope,dope'
		owner 530
		view 20
	)
	
	(method (saidMe)
		(if (== view 27)
			(Said '/dope,hemp')
			(return)
		else
			(Said '/dope')
			(return)
		)
	)
)

(instance statusCode of Code
	(properties)
	
	(method (doit strg)
		(Format strg 0 168
			oldScore 0 169
			(if playingAsPatti
				{Passionate Patti}
			else
				{Leisure Suit Larry 3}
			)
		)
	)
)

(instance egoObj of Ego
	(properties
		name "ego"
		y 1111
		view 700
	)
)

(instance scoreSound of Sound
	(properties
		number 1
		priority 10
	)
)

(instance theMusic of Sound
	(properties
		number 1
	)
)

(instance theSoundFX of Sound
	(properties
		number 1
		priority 5
	)
)

(instance theWindow of SysWindow
	(properties)
	
	(method (open)
		(if (< (Graph GDetect) 9)
			(if (or (< color 7) (== color 8))
				(= color vBLACK)
				(= back vWHITE)
			else
				(= color vWHITE)
				(= back vBLACK)
			)
		)
		(super open:)
	)
)

(instance NormalBase of Code
	(properties)
	
	(method (doit &tmp temp0)
		(if (== curRoomNum 253) (= temp0 22) else (= temp0 10))
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) (ego yStep?)))
		(ego brLeft: (- (ego x?) temp0))
		(ego brRight: (+ (ego x?) temp0))
	)
)
