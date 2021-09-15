;;; Sierra Script 1.0 - (do not remove this comment)
(script# LSL3)
(include game.sh) (include menu.sh)
(use Intrface)
(use LoadMany)
(use Sound)
(use Save)
(use Motion)
(use Game)
(use Invent)
(use User)
(use File)
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
	AddViewToPic 15
	SetOrchidTimer 16
	LogIt 17
	LogPragFail 18
	Bset 19
	Bclr 20
	Btoggle 21
	Btst 22
	InRoom 23
	PutInRoom 24
	PrintDelay 25
)

(local
	ego									;pointer to ego
	theGame								;ID of the Game instance
	curRoom								;ID of current room
	speed =  6							;number of ticks between animations
	quit								;when TRUE, quit game
	cast								;collection of actors
	regions								;set of current regions
	timers								;list of timers in the game
	sounds								;set of sounds being played
	inventory							;set of inventory items in game
	addToPics							;list of views added to the picture
	curRoomNum							;current room number
	prevRoomNum							;previous room number
	newRoomNum							;number of room to change to
	debugOn								;generic debug flag -- set from debug menu
	score								;the player's current score
	possibleScore						;highest possible score
	showStyle	=		IRISOUT			;style of picture showing
	aniInterval							;# of ticks it took to do the last animation cycle
	theCursor							;the number of the current cursor
	normalCursor =		ARROW_CURSOR	;number of normal cursor form
	waitCursor	 =		HAND_CURSOR		;cursor number of "wait" cursor
	userFont	 =		USERFONT		;font to use for Print
	smallFont	 =		4				;small font for save/restore, etc.
	lastEvent							;the last event (used by save/restore game)
	modelessDialog						;the modeless Dialog known to User and Intrface
	bigFont		=		USERFONT		;large font
	volume		=		12				;sound volume
	version		=		{x.yyy.zzz}		;pointer to 'incver' version string			
	locales								;set of current locales
	[curSaveDir 20]						;address of current save drive/directory string
	aniThreshold	=	10
	perspective							;player's viewing angle:
										;	 degrees away from vertical along y axis
	features							;locations that may respond to events
	sortedFeatures          			;above+cast sorted by "visibility" to ego
	useSortedFeatures					;enable cast & feature sorting?
	demoScripts							;add to curRoomNum to find room demo script
	egoBlindSpot						;used by sortCopy to exclude
										;actors behind ego within angle 
										;from straight behind. 
										;Default zero is no blind spot
	overlays	=		-1
	doMotionCue							;a motion cue has occurred - process it
	systemWindow						;ID of standard system window
	demoDialogTime	=	3				;how long Prints stay up in demo mode
	currentPalette
	modelessPort
	;globals 63-99 are unused
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
	;globals 100 and above are for game use
	debugging				;debug mode enabled
	currentStatus			;current ego status
	currentEgoView			;ego's current view
	gameSeconds				;elapsed seconds
	gameMinutes				;elapsed minutes
	gameHours				;elapsed hours
	oldSysTime				;previous value of system's real-time clock
	roomSeconds				;elapsed seconds in current room (resets to 0 on room change)
	musicLoop				;saved value of music loop state
	orchidSeconds			;seconds before the orchids wilt
	orchidMinutes			;minutes before the orchids wilt
	gameFlags				;16 * 7 = 102 flags
		global112
		global113
		global114
		global115
		global116
		global117
	currentEgo				;are you playing as Larry or Patti?
	egoName					;what's your name?
	global120				;unused
	global121				;unused
	global122				;unused
	machineSpeed			;used by the speed tester to test how fast the system is
							; and used in determining game speed.
	filthLevel				;the higher the setting, the stronger some language is
	global125				;unused
	global126				;unused
	global127				;unused
	bambooStalksSeen		;number of times ego "looked" at a bamboo stalk
	oldScore				;score on the previous cycle. This
							; allows for gradual incrementation on the status line
	dollars					;how much money ego has
	music					;pointer for global music object
	tawniState				;Tawni's current state
	programControl			;if TRUE, disable control on the next cycle
	newspaperState			;current state of the newspaper
	lawyerState				;current state of Suzi
	vendorView				;view of the vendor hawking his goods
	saveSpeed				;saved speed during certain sequences
	saveEgoX				;saved x coord of ego
	saveEgoY				;saved y coord of ego
	showroomState			;current state of showroom and Cherri
	myTextColor				;color of text in message boxes
	myBackColor				;color of message boxes
	theDoor					;pointer for backstage door
	playingAsPatti			;are you now playing as Patti?
	soundFX					;pointer for global sound FX object
	printTime				;seconds to display Print
	lockerNum1				;randomized locker number 1
	lockerNum2				;randomized locker number 2
	lockerNum3				;randomized locker number 3
	
	;amount of exercise needed to become fit
	requiredPoundsPumped
	requiredLegCurls
	requiredPullUps
	requiredBarPulls
	
	larryBuffed				;Larry's all buffed up!
	saveEgoLoop				;saved loop of ego
	oldStatus				;saved value of currentStatus		
	global157				;unused
	global158				;unused
	global159				;unused
	global160				;unused
	global161				;unused
	global162				;unused
	global163				;unused
	global164				;unused
	global165				;unused
	global166				;unused
	global167				;unused
	global168				;unused
	global169				;unused
	filthStr				;buffer for filth ranking, this is part of an array			
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
		global199			;end of filthStr
	expletiveStr			;buffer for expletive, this is part of an array
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
		global218			;end of expletiveStr
	expletive				;current expletive
	introductoryPhrase		;"My name is Larry; Larry Laffer." Was this going to be changeable?
	minutesBetweenReminders	;total minutes between save reminders
	autoSaveTimer			;remaining time before save reminder
	secondsBetweenReminders	;total seconds between save reminders
	
	;these globals are used by the QA logger
	QANoteBuf
		global225
		global226
		global227
		global228
	noteFileNameBuf
		global230
		global231
	noteNum
)
(procedure (NormalEgo theLoop theView)
	;normalizes ego's animation
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
	;normalizes an actor's animation
	(if (> argc 1)
		(theActor loop: theLoop)
	)
	(if (> argc 2)
		(theActor view: theView)
	)
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
	;disable ego control
	(User canControl: FALSE canInput: FALSE)
	(ego setMotion: 0)
)

(procedure (HandsOn)
	;enable ego control
	(User canControl: TRUE canInput: TRUE)
	(ego setMotion: 0)
)

(procedure (cls)
	;clear modeless dialog from the screen
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
	;notify multiple scripts
	(= i (ScriptID i))
	(i notify: &rest)
)

(procedure (HaveMem howMuch)
	;check how much heap is available
	(return
		(if (> (MemoryInfo FreeHeap) howMuch)
			(return TRUE)
		else
			(Print 0 57)
			(return FALSE)
		)
	)
)

(procedure (AddViewToPic obj)
	;creates a new view object and makes it an addToPic
	(if obj
		((View new:)
			view: (obj view?)
			loop: (obj loop?)
			cel: (obj cel?)
			setPri: (obj priority?)
			posn: (obj x?) (obj y?)
			addToPic:
		)
		(obj posn: (obj x?) (+ 1000 (obj y?)))
	)
)

(procedure (SetOrchidTimer minutes seconds param3)
	;sets the time before the orchids wilt
	(= orchidMinutes minutes)
	(= orchidSeconds (* 10 (+ param3 (* seconds 60))))
)

(procedure (LogIt why &tmp [str 70])
	;code taken from demo
	(File
		name: {input.log}
		write:
			(Format @str
				"[r%3d %s v%3d %3dx/%3dy ES%-5d] %s\n"
				curRoomNum
				version
				(ego view?) (ego x?) (ego y?)
				currentStatus
				why
			)
		close:
	)
	
)

(procedure (LogPragFail &tmp [str 50])
	;code taken from demo
	(LogIt (Format @str "Lame response to \"%s\"" (User inputLineAddr?)))
)


(procedure (Bset flagEnum)
	;Set a boolean game flag
	(= [gameFlags (/ flagEnum 16)]
		(|
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Bclr flagEnum)
	;Clear a boolean game flag
	(= [gameFlags (/ flagEnum 16)]
		(&
			[gameFlags (/ flagEnum 16)]
			(~ (>> $8000 (mod flagEnum 16)))
		)
	)
)

(procedure (Btoggle flagEnum)
	;Toggle a boolean game flag.
	; This procedure doesn't seem to be used.
	(= [gameFlags (/ flagEnum 16)]
		(^
			[gameFlags (/ flagEnum 16)]
			(>> $8000 (mod flagEnum 16))
		)
	)
)

(procedure (Btst flagEnum)
	;Test a boolean game flag
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

(procedure (InRoom what where)
	;check whether an inventory object is in a room.
	; If no room is specified, it assumes the current room.
	(return
		(==
			((inventory at: what) owner?)
			(if (< argc 2) curRoomNum else where)
		)
	)
)

(procedure (PutInRoom what where)
	;put an inventory object in a room.
	; If no room is specified, it assumes the current room.
	((inventory at: what)
		owner: (if (< argc 2) curRoomNum else where)
	)
)

(procedure (PrintDelay seconds)
	;sets the time a message is displayed, based on its length.
	(return (+ 3 (/ (StrLen seconds) printTime)))
)

(instance LSL3 of Game
	(method (init &tmp startingRoom)
		;set up the game's objects and globals
		((= systemWindow theWindow)
			color: (= myTextColor vBLUE)
			back: (= myBackColor vWHITE)
		)
		(super init:)
		(Bset fQAEnabled)	;EO: Added for QA debug
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
		;set up the game's inventory
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
		;go to the starting room
		(self newRoom: startingRoom)
	)
	
	(method (doit &tmp [str 50])
		(super doit:)
		(if programControl
			(User canControl: FALSE canInput: FALSE)
		)
		;let the game's clock tick
		(if (!= oldSysTime (= oldSysTime (GetTime TRUE)))
			(if (>= (++ gameSeconds) 60)
				(= gameSeconds 0)
				(if (>= (++ gameMinutes) 60)
					(= gameMinutes 0)
					(++ gameHours)
				)
			)
			(++ roomSeconds)
			;if not much progress is made early on,
			; give the player a hint
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
				;remind the player to save the game
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
		;increment or decrement the score on the status line
		; when you get or lose points
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
		;countdown the time before the orchids wilt
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
					(not (OneOf roomNum
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
		;clean up after a room change
		(LoadMany FALSE
			FILE JUMP EXTRA WINDOW TIMER FOLLOW REVERSE
			DCICON CHANGE_SCRIPT DOOR AUTODOOR
		)
		(DisposeScript LOADMANY)
		(if debugOn
			(= debugOn FALSE)
			(SetDebug)
		)
		(soundFX stop: number: 1)
		(super startRoom: roomNum &rest)
		(if (and (not (OneOf roomNum 530 260 420)) debugging)
			(curRoom setLocales: DEBUG)
		)
		(if (Btst fQAEnabled)
			(curRoom setLocales: DEBUG_22)
		)
		(cond 
			;set the current region based on the new room
			(
				(OneOf roomNum
					200 203 210 213 216 220 230 235 240 245
					250 253 300 305 310
				)
				(curRoom setLocales: VILLAGE)
			)
			((OneOf roomNum 360 370 375 380 390)
				(curRoom setLocales: FATCITY)
			)
			((OneOf roomNum 400 410 415 416 420 460)
				(curRoom setLocales: CASINO)
			)
			((OneOf roomNum 510 520 523 540 550)
				(curRoom setLocales: JUNGLE)
			)
			((OneOf roomNum 610 620 630 640 650)
				(curRoom setLocales: STUDIO)
			)
		)
		(if (or (== currentStatus egoSHOWGIRL) (== currentEgoView 708))
			(ego baseSetter: NormalBase)
		)
	)
	
	(method (changeScore delta)
		(+= score delta)
		(if (> delta 0)
			(scoreSound playMaybe:)
		)
	)
	
	(method (handleEvent event &tmp [temp0 2] i [temp3 3] [str 50])
		(super handleEvent: event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		;start saidEvents
		(cond 
			((Said 'ascot/backdrop')
				(if (^= debugging TRUE)
					(Print 0 14)
				else
					(Print 0 15)
				)
			)
			((or (Said 'caress/ginsu') (Said 'sharpen/ginsu'))
				(cond 
					((not (ego has: iKnife))
						(DontHave)
					)
					((== (Ginsu_Knife view?) 21)
						(ItIs)
					)
					((!= curRoomNum 250)
						(Print 0 16)
					)
				)
			)
			((or (Said 'backdrop/anyword/bottle') (Said 'fill/bottle'))
				(Print 0 17)
			)
			((Said '(drain<out),drain/beer,bottle')
				(cond 
					((not (ego has: iWineBottle))
						(DontHave)
					)
					((not playingAsPatti)
						(Print 0 18)
					)
					((== (Bottle_of_Wine_ view?) 28)
						(Print 0 19)
					)
					(else
						(Bottle_of_Wine_ view: 28)
						(Format (Bottle_of_Wine_ name?) 0 20)
						(Print 0 21)
					)
				)
			)
			((Said 'carve,carve>')
				(cond 
					((not (ego has: iKnife))
						(Print 0 22)
					)
					((== (Ginsu_Knife view?) 2)
						(Print 0 23)
					)
					((Said '[/noword]')
						(Print 0 24)
					)
					((Said '/blade')
						(Print 0 25)
					)
					((not (Said '/carving,granadilla'))
						(Print 0 26)
					)
					((not (ego has: iWood))
						(Print 0 27)
					)
					((or (== (Granadilla_Wood_ view?) 22) (== (Granadilla_Wood_ view?) 34))
						(Print 0 28)
					)
					((or (== currentStatus egoNORMAL) (== currentStatus egoNATIVE))
						(Ok)
						(theGame setScript: (ScriptID CARVING))
					)
					(else
						(GoodIdea)
					)
				)
				(event claimed: TRUE)
			)
			(
				(or
					(Said 'use/flower/lei<make')
					(Said 'weave,make/flower,lei')
				)
				(cond 
					((not (ego has: iOrchids))
						(Print 0 29)
					)
					((== (some_Orchids view?) 26)
						(Print 0 30)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(Ok)
						(theGame setScript: (ScriptID LEIING))
					)
				)
			)
			(
			(Said 'wear,(alter<in),(backdrop<on)/flower,lei')
				(cond 
					((not (ego has: iOrchids))
						(Print 0 31)
					)
					((!= (some_Orchids view?) 26)
						(Print 0 32)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(Print 0 33)
					)
				)
			)
			((or (Said 'use/blade/skirt<make') (Said 'weave,make/blade,skirt'))
				(cond 
					((not (ego has: iGrass))
						(Print 0 34)
					)
					((== (Native_Grass view?) 23)
						(Print 0 35)
					)
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					(else
						(Ok)
						(theGame setScript: (ScriptID WEAVING))
					)
				)
			)
			((or (Said 'get/naked') (Said '(get<off),remove/cloth,suit'))
				(Print 0 36)
			)
			((Said 'alter/cloth,cloth')
				(Print 0 37)
			)
			((Said 'wear,(alter<in),(backdrop<on)/blade,skirt')
				(cond 
					((not (ego has: iGrass))
						(Print 0 31)
					)
					((not (== (Native_Grass view?) 23))
						(Print 0 38)
					)
					(else
						(Print 0 39)
					)
				)
			)
			((and (ego has: iSoap) (Said 'use/soap'))
				(Print 0 40)
			)
			((or (Said 'unknownnumber/') (Said '/unknownnumber') (Said '//unknownnumber'))
				(Print 0 41)
			)
			((and (ego has: iDivorceDecree) (not (ego has: iSpaKeycard)) (Said 'look,look/decree,document,paper'))
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
				(theGame setScript: (ScriptID LOCKER))
			)
			((Said 'count>')
				(= i (inventory saidMe:))
				(cond 
					((Said '[/noword]')
						(Print 0 43)
					)
					((not i)
						(event claimed: TRUE)
						(Printf 0 44 (Random 10 999))
					)
					((!= (inventory indexOf: i) iMoney)
						(Print 0 45)
					)
					((not (i ownedBy: ego))
						(Print 0 46)
					)
					(else
						(Printf 0 47 dollars)
					)
				)
			)
			((or (Said 'give/bill,buck') (Said 'bribe'))
				(cond 
					((not (ego has: iMoney))
						(Print 0 48)
					)
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
						(Said 'wear,alter,(get<off),drain,(put<on)/skirt,cloth,cloth')
					)
				)
				(Print 0 55)
			)
			((and (ego has: iBra) (Said 'backdrop//(bra)>'))
				(= i (inventory saidMe:))
				(cond 
					((Said '[/noword]')
						(Print 0 56)
					)
					((not i)
						(event claimed: TRUE)
						(Print 0 57)
					)
					((not (i ownedBy: ego))
						(DontHave)
					)
					(else
						(Print 0 58 #icon 16 0 0)
						(Print 0 59 #at -1 144)
					)
				)
			)
			((and (ego has: iKnife) (or (Said 'use/ginsu') (Said 'attack')))
				(Print 0 60)
			)
			((Said 'open,(look<in)>')
				(= i (inventory saidMe:))
				(cond 
					((Said '[/noword]')
						(Print 0 61)
					)
					((not i)
						(event claimed: TRUE)
						(Print 0 57)
					)
					((not (i ownedBy: ego))
						(Print 0 62)
					)
					(else
						(switch (inventory indexOf: i)
							(iOrchids
								(Print 0 63 #icon 11 0 0)
							)
							(iWineBottle
								(switch (Bottle_of_Wine_ view?)
									(28
										(Print 0 64 #icon 28 0 0)
									)
									(29
										(Print 0 65 #icon 29 0 0)
									)
									(else 
										(Print 0 66 #icon 13 0 0)
									)
								)
							)
							(iPanties
								(Print 0 67 #icon 14 0 0)
							)
							(iPantyhose
								(Print 0 68 #icon 15 0 0)
							)
							(iBra
								(Print 0 69 #icon 16 0 0)
							)
							(iDress
								(Print 0 70 #icon 17 0 0)
							)
							(iCoconuts
								(Print 0 71 #icon 19 0 0)
							)
							(else
								(Print 0 62)
								(LogPragFail)
							)
						)
					)
				)
			)
			((Said 'hello')
				(Print 0 72)
			)
			((or (Said '/bye') (Said 'bye'))
				(Print 0 15)
			)
			((Said 'thank')
				(Print 0 73)
			)
			((Said 'knock')
				(Print 0 74)
				(Print (Format @str 0 75 currentEgo) #at -1 144)
			)
			((Said 'attack')
				(Print 0 60)
			)
			((or (Said 'go/bathroom') (Said 'leak') (Said 'get/leak'))
				(if playingAsPatti
					(Print 0 76)
					(Print 0 77 #at -1 144)
				else
					(Print 0 78)
				)
			)
			((Said 'climb>')
				(cond 
					((Said '/wall,building')
						(Print 0 79)
					)
					(playingAsPatti
						(Print 0 80)
					)
					(else
						(Print 0 81)
					)
				)
				(event claimed: TRUE)
			)
			((or (Said '//larry') (Said '/larry'))
				(cond 
					((not playingAsPatti)
						(Print 0 82)
					)
					((< curRoomNum 590)
						(Print 0 83)
					)
					(else
						(Print 0 84)
					)
				)
			)
			((Said 'jump,dance')
				(Print 0 85)
			)
			((Said 'sing')
				(Print 0 86)
			)
			((Said 'delay')
				(Print 0 87)
			)
			((Said 'pick')
				(Print 0 88)
			)
			((Said 'holler')
				(Print 0 89)
			)
			((Said 'rob')
				(Print 0 90)
			)
			((or (Said 'n') (Said 'affirmative'))
				(Ok)
			)
			((Said 'borrow')
				(Print 0 91)
			)
			((Said 'cheat')
				(Print 0 92)
				(Print 0 93 #at -1 144)
				(= quit TRUE)
			)
			((Said '(backdrop<on),wear>')
				(cond 
					((Said '[/noword]')
						(Print 0 94)
					)
					((= i (inventory saidMe:))
						(cond 
							((not (ego has: (inventory indexOf: i)))
								(DontHave)
							)
							((or (== i iBra) (== i iDress) (== i iPanties) (== i iPantyhose))
								(YouAre)
							)
							(else
								(Print 0 95)
							)
						)
					)
					(else
						(Print 0 96)
						(event claimed: TRUE)
					)
				)
			)
			((Said 'backdrop>')
				(cond 
					((Said '[/noword]')
						(Print 0 97)
					)
					((= i (inventory saidMe:))
						(if
						(not (ego has: (inventory indexOf: i)))
							(DontHave)
						else
							(Print 0 98)
						)
					)
					(else
						(Print 0 99)
						(event claimed: TRUE)
					)
				)
			)
			((Said 'throw>')
				(cond 
					((Said '[/noword]')
						(Print 0 100)
					)
					((= i (inventory saidMe:))
						(if (not (ego has: (inventory indexOf: i)))
							(DontHave)
						else
							(Print 0 101)
						)
					)
					(else
						(Print 0 102)
						(event claimed: TRUE)
					)
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
			((Said 'whistle')
				(Print 0 107)
			)
			((Said 'ask/job')
				(Print 0 108)
				(Print 0 109 #at -1 144)
			)
			((and playingAsPatti (or (Said '/arnold') (Said '//arnold')))
				(Print 0 110)
			)
			((Said 'laugh')
				(Print 0 111)
			)
			((Said 'eat')
				(Print 0 112)
			)
			((Said 'lie,lie')
				(Print 0 113)
			)
			((Said 'aid/i,self')
				(Print 0 114)
			)
			((Said 'aid')
				(Print 0 115)
			)
			((Said 'explore>')
				(if (Said '/cloth,panties,entertainer,larry')
					(Print 0 116)
					(inventory showSelf: ego)
				else
					(event claimed: TRUE)
					(Print 0 117)
				)
			)
			((or (Said '/bang/ya') (Said 'bang/ya'))
				(Print 0 118)
			)
			((Said 'bang/anyword')
				(Print 0 119)
			)
			((or (Said 'caress/i,larry,self') (Said 'jack'))
				(Print 0 120)
			)
			((Said 'caress,caress,embrace,look/clit')
				(Printf 0 121 currentEgo)
			)
			((Said 'eat/babe')
				(Print 0 122)
			)
			((or (Said 'look<in/blouse') (Said 'look<up/skirt'))
				(Print 0 123)
			)
			((Said 'drink')
				(Print 0 124)
			)
			((or (Said 'use/key') (Said 'unbolt'))
				(Print 0 125)
			)
			((or (Said '/book') (Said '//book'))
				(Print 0 126)
			)
			((Said 'hear')
				(Print 0 127)
			)
			((Said 'embrace')
				(Print 0 128)
			)
			(
				(or
					(Said 'eat,bang/i')
					(Said 'crap,leak,bang,fart')
					(Said '/clit,crap,leak,bang,fart,asshole')
					(Said '//clit,crap,leak,bang,fart,asshole')
				)
				(Print 0 129)
			)
			((or (Said '/hell') (Said '//hell'))
				(Print 0 130)
			)
			((Said 'look>')
				(cond 
					((Said '/self,larry,entertainer,i,blouse,cloth,panties')
						(if (not playingAsPatti)
							(Print 0 131)
						else
							(Print 0 132)
						)
					)
					((and (== (ego view?) 718) (Said '/sunglass'))
						(Print 0 133)
					)
					((Said '/ankle,sandal')
						(if (== (ego view?) 718)
							(Print 0 134)
						else
							(Print 0 135)
						)
					)
					((Said '/bush,palm')
						(Print 0 136)
					)
					((Said '/man,babe,couple')
						(Print 0 137)
					)
					((Said '/wall,building')
						(Print 0 138)
					)
					((Said '/carpet,down')
						(Print 0 139)
					)
					((Said '/air,ceiling')
						(Print 0 140)
					)
					((Said '/boob')
						(if playingAsPatti
							(Print 0 141)
						else
							(Print 0 142)
						)
					)
					((Said '/ass')
						(if playingAsPatti
							(Print 0 143)
						else
							(Print 0 144)
						)
					)
					((Said '<in/cup')
						(Print 0 145)
					)
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
						(event claimed: TRUE)
						(LogPragFail)
					)
				)
			)
			((or (Said 'use,buy/bill,tips,buck') (Said 'buy'))
				(if (ego has: iMoney)
					(Print 0 148)
				else
					(Print 0 149)
				)
			)
			((Said 'use>')
				(= i (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((Said '[/noword]')
						(Print 0 150)
					)
					((not i)
						(Print 0 57)
					)
					((not (i ownedBy: ego))
						(DontHave)
					)
					(else
						(Print 0 151)
						(LogPragFail)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'give>')
				(= i (inventory saidMe:))
				(event claimed: FALSE)
				(cond 
					((Said '/anyword[/noword]')
						(Print 0 152)
					)
					((Said '[/noword]')
						(Print 0 153)
					)
					((not i)
						(Print 0 154)
					)
					((not (i ownedBy: ego))
						(DontHave)
					)
					(else
						(Print 0 155)
						(LogPragFail)
					)
				)
				(event claimed: TRUE)
			)
			((Said 'get<down')
				(Print 0 156)
			)
			((Said 'get<up')
				(Print 0 157)
			)
			((Said 'get>')
				(cond 
					((Said '[/noword]')
						(Print 0 158)
					)
					((and (= i (inventory saidMe:)) (i ownedBy: ego))
						(Print 0 159)
					)
					((== i iWood)
						(Print 0 160)
					)
					(else
						(switch (Random 33 35)
							(33 (Print 0 161))
							(34 (Print 0 162))
							(35 (Print 0 163))
						)
						(LogPragFail)
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
				(if (Said '[/noword]')
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
			((or (Said '//babe') (Said '/babe/') (Said '/babe'))
				(Print 0 167)
			)
		)
	)
	
	(method (wordFail word &tmp [str 50])
		;don't recognize a word
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
		;can't parse input
		(switch (Random 0 2)
			(0 (Print 0 8))
			(1 (Print 0 9))
			(else  (Print 0 10))
		)
	)
	
	(method (pragmaFail &tmp [str 40])
		;no response to input
		(switch (Random 0 2)
			(0 (Print 0 11))
			(1 (Print 0 12))
			(else  (Print 0 13))
		)
	)
)

(class Iitem of InvItem
	;this subclass allows item descriptions to be called
	;from TEXT.030 (item descriptions)
	(method (showSelf)
		(Print INVDESC view
			#title name
			#icon view 0 0
		)
	)
)

(instance Nothing of Iitem)	;dummy item to take up number 0

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
	;sound that plays when you get points
	(properties
		number 1
		priority 10
	)
)

(instance theMusic of Sound
	;music object
	(properties
		number 1
	)
)

(instance theSoundFX of Sound
	;sound FX object
	(properties
		number 1
		priority 5
	)
)

(instance theWindow of SysWindow
	;LSL3's custom window
	(method (open)
		(if (< (Graph GDetect) 9)
			(if (or (< color vLGREY) (== color vGREY))
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
	(method (doit &tmp theX)
		(if (== curRoomNum 253)
			(= theX 22)
		else
			(= theX 10)
		)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) (ego yStep?)))
		(ego brLeft: (- (ego x?) theX))
		(ego brRight: (+ (ego x?) theX))
	)
)
