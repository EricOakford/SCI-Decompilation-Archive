;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Procs)
(use Intrface)
(use GControl)
(use BordWind)
(use IconBar)
(use LoadMany)
(use DCIcon)
(use Sound)
(use Game)
(use Invent)
(use User)
(use System)

(public
	Brain 0
	VGAOrEGA 1
	VerbFail 2
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
	useObstacles =  1
	theMenuBar
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
	cDAudio
	fastCast
	inputFont
	tickOffset
	howFast
	gameTime
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
	cSound
	dongle =  1234
	global102
	global103
	global104
	numColors
	numVoices
	global107
	global108
	global109
	global110
	global111
	global112
	global113
	arcadeLevel
	numCoins =  1
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
	gIcon2
	global164
	global165
	global166
	global167
	global168
	global169
	global170 =  -1
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
	global300
)
(procedure (VGAOrEGA vga ega)
	(if (< vga 0) (= vga 0))
	(if (> vga 255) (= vga 255))
	(if (< ega 0) (= ega 0))
	(if (> ega 15) (= ega 15))
	(return (if (Btst fIsVGA) vga else ega))
)

(procedure (VerbFail)
)

(instance cMusic of Sound
	(properties
		number 1
		priority 12
	)
)

(instance cMusic2 of Sound
	(properties
		number 1
		priority 13
	)
)

(instance statLn of Code

	(method (doit strg)
		(Format strg 0 0
			(switch arcadeLevel
				(0 {Major})
				(1 {Medium})
				(2 {Hard})
			)
		)
	)
)

(instance sq4KeyDownHandler of EventHandler)

(instance sq4DirectionHandler of EventHandler)

(instance sq4MouseDownHandler of EventHandler)

(instance Brain of Game
	
	(method (init)
		(= systemWindow BorderWindow)
		(= useSortedFeatures FALSE)
		(= doVerbCode BrainVerbCode)
		(StatusLine code: statLn)
		(super init:)
		(StrCpy @sysLogPath {})
		(= ftrInitializer sq4FtrInit)
		((= keyDownHandler sq4KeyDownHandler) add:)
		((= mouseDownHandler sq4MouseDownHandler) add:)
		((= directionHandler sq4DirectionHandler) add:)
		((= theIconBar IconBar)
			curIcon: icon1
			useIconItem: icon4
			init:
		)
		(= ego Ego)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: 0
			canInput: 0
			x: -1
			y: 150
		)
		(self setCursor: 50 TRUE 1000 1000)
		(= possibleScore 200)
		(= userFont 4)
		(= version {x.yyy})
		(= numVoices (DoSound NumVoices))
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(Bclr fIsVGA)
		else
			(Bset fIsVGA)
		)
		(cMusic owner: self init:)
		(cMusic2 owner: self init:)
		(= cSound cMusic)
		(theIconBar
			add: icon1 icon2 icon4 icon5 icon6 icon8 icon9
			eachElementDo: #init
			helpIconItem: icon9
		)
		(icon5 message: (if (HaveMouse) 3840 else 9))
		(= gIcon2 icon2)
		(Inventory
			add:
				coin
				paper
				certificate
				token
				clockKey
				timeCard1
				timeCard2
				timeCard3
				dataCard
				tangrams
				truthHead
				liarHead
				confusedHead
				grabberHand
				magnetHand
				forkHand
				letterCounter
				rubyKey
				robotRat
				battery
				cheese
				invLook
				invHand
				invSelect
				invHelp
				ok
			window: InsetWindow
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
		(= gameControls controlBox)
		(gameControls
			window: gcWin
			add:
				iconOk
				(detailSlider
					theObj: self
					selector: 293
					topValue: 3
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: 383
					topValue: 15
					bottomValue: 0
					yourself:
				)
				(iconSave
					theObj: self
					selector: #save
					yourself:
				)
				(iconRestore
					theObj: self
					selector: #restore
					yourself:
				)
				(iconRestart
					theObj: self
					selector: #restart
					yourself:
				)
				(iconQuit
					theObj: self
					selector: #quitGame
					yourself:
				)
				(iconAbout
					theObj: aboutCode
					selector: #doit
					yourself:
				)
				iconHelp
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(coin owner: ego)
		(HandsOff)
		(self newRoom: 1)
	)
	
	(method (startRoom roomNum)
		(self setCursor: 50 TRUE 1000 1000)
		(LoadMany FALSE 10 POLYGON 15 800)
		(if
			(and
				(u> (MemoryInfo FreeHeap) (+ 10 (MemoryInfo LargestPtr)))
				(HighPrint 0 2
					#button {Who cares} 0
					#button {Debug} 1
				)
			)
			(SetDebug)
		)
		(if debugOn
			(SetDebug)
		)
		(StatusLine disable:)
		(ScriptID 982)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp eType eMsg)
		(= eType (event type?))
		(= eMsg (event message?))
		(cond 
			((== eType mouseDown)
				(self quitGame:)
			)
			(
				(and
					(== eType keyDown)
					(OneOf eMsg 13 27 17 3)
				)
				(self quitGame:)
			)
		)
		(event claimed: TRUE)
	)
	
	(method (quitGame)
		(sounds pause: TRUE)
		(HandsOn 1)
		(self setCursor: ARROW_CURSOR TRUE)
		(super
			quitGame:
				(HighPrint 0 1
					#button {Quit for now} 1
					#button {Keep looking} 0
					#icon brainIcon
				)
		)
		(self setCursor: 50 TRUE)
		(HandsOff)
		(sounds pause: FALSE)
	)
)

(instance brainIcon of DCIcon
	(properties
		view 902
		cycleSpeed 10
	)
)

(instance ok of IconItem
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		cursor 50
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Select this Icon to close this window.}
		lowlightColor 5
	)
)

(instance invLook of IconItem
	(properties
		view 901
		loop 2
		cel 0
		cursor 50
		message verbLook
		helpStr {Select this Icon then select an inventory item to get a description of.}
		lowlightColor 5
	)
)

(instance invHand of IconItem
	(properties
		view 901
		loop 0
		cel 0
		cursor 50
		message verbDo
		helpStr {This allows you to do something to an item.}
		lowlightColor 5
	)
)

(instance invHelp of IconItem
	(properties
		view 901
		loop 1
		cel 0
		cursor 50
		message verbHelp
		lowlightColor 5
	)
)

(instance invSelect of IconItem
	(properties
		view 901
		loop 4
		cel 0
		cursor 50
		helpStr {This allows you to select an item.}
		lowlightColor 5
	)
)

(instance coin of InvItem
	(properties
		view 700
		loop 1
		cel 2
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {hint coin}
		owner 380
	)
)

(instance paper of InvItem
	(properties
		view 700
		loop 1
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {scrap paper}
		owner 380
	)
)

(instance certificate of InvItem
	(properties
		view 700
		loop 1
		cel 1
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {certificate}
		owner 1
	)
)

(instance token of InvItem
	(properties
		view 384
		loop 3
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {token}
		owner 380
	)
)

(instance clockKey of InvItem
	(properties
		view 700
		loop 1
		cel 4
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {clock key}
		owner 1
	)
)

(instance timeCard1 of InvItem
	(properties
		view 700
		loop 1
		cel 5
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {time card}
		owner 1
	)
)

(instance timeCard2 of InvItem
	(properties
		view 700
		loop 1
		cel 5
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {time card}
		owner 1
	)
)

(instance timeCard3 of InvItem
	(properties
		view 700
		loop 1
		cel 5
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {time card}
		owner 1
	)
)

(instance dataCard of InvItem
	(properties
		view 700
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {data card}
		owner 1
	)
)

(instance tangrams of InvItem
	(properties
		view 700
		loop 1
		cel 6
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {tangrams}
		owner 1
	)
)

(instance truthHead of InvItem
	(properties
		view 700
		loop 1
		cel 7
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {truth head}
		owner 1
	)
)

(instance liarHead of InvItem
	(properties
		view 700
		loop 1
		cel 8
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {liar head}
		owner 1
	)
)

(instance confusedHead of InvItem
	(properties
		view 700
		loop 1
		cel 9
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {confused head}
		owner 1
	)
)

(instance grabberHand of InvItem
	(properties
		view 700
		loop 3
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {grabber hand}
		owner 1
	)
)

(instance magnetHand of InvItem
	(properties
		view 700
		loop 3
		cel 1
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {magnet hand}
		owner 1
	)
)

(instance forkHand of InvItem
	(properties
		view 700
		loop 3
		cel 2
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {fork hand}
		owner 1
	)
)

(instance letterCounter of InvItem
	(properties
		view 700
		loop 3
		cel 3
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {letter counter}
		owner 380
	)
)

(instance rubyKey of InvItem
	(properties
		view 700
		loop 3
		cel 4
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {ruby key}
		owner 380
	)
)

(instance robotRat of InvItem
	(properties
		view 700
		loop 3
		cel 5
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {robot rat}
		owner 1
	)
)

(instance battery of InvItem
	(properties
		view 700
		loop 3
		cel 6
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {battery}
		owner 1
	)
)

(instance cheese of InvItem
	(properties
		view 700
		loop 3
		cel 7
		cursor 50
		signal IMMEDIATE
		lowlightColor 2
		description {cheese}
		owner 1
	)
)

(instance icon1 of IconItem
	(properties
		view 900
		loop 1
		cel 0
		cursor 50
		message verbLook
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for looking.}
		lowlightColor 5
	)
)

(instance icon2 of IconItem
	(properties
		view 900
		loop 2
		cel 0
		cursor 50
		message verbDo
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for doing.}
		lowlightColor 5
	)
)

(instance icon4 of IconItem
	(properties
		view 900
		loop 3
		cel 0
		state DISABLED
		cursor 50
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for current inv item.}
		lowlightColor 5
	)
)

(instance icon5 of IconItem
	(properties
		view 900
		loop 4
		cel 0
		cursor 50
		type NULL
		message verbNone
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the inv window.}
		lowlightColor 5
	)
	
	(method (select)
		(if (super select:)
			(Inventory showSelf: ego)
		)
	)
)

(instance icon6 of IconItem
	(properties
		view 900
		loop 0
		cel 0
		cursor 50
		type NULL
		message verbNone
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the inv window.}
		lowlightColor 5
	)
	
	(method (select &tmp curRoomSouth)
		(if (super select:)
			(if (= curRoomSouth (curRoom south?))
				(curRoom newRoom: curRoomSouth)
			else
				(HighPrint 0 3)
			)
		)
	)
)

(instance icon8 of IconItem
	(properties
		view 900
		loop 5
		cel 0
		cursor 50
		signal (| RELVERIFY IMMEDIATE)
		lowlightColor 5
	)
	
	(method (select)
		(if (super select:)
			(gameControls show:)
		)
	)
)

(instance icon9 of IconItem
	(properties
		view 900
		loop 6
		cel 0
		cursor 50
		message verbHelp
		signal (| RELVERIFY IMMEDIATE)
		lowlightColor 5
	)
)

(instance sq4FtrInit of Code
	
	(method (doit obj)
		(if (== (obj sightAngle?) ftrDefault)
			(obj sightAngle: 90)
		)
		(if (== (obj actions?) ftrDefault)
			(obj actions: 0)
		)
	)
)

(instance gcWin of BorderWindow
	
	(method (open &tmp savePort temp1 t l r b temp6 temp7 temp8 temp9 temp10 temp11 thePri temp13 [scoreBuf 15] [scoreLen 4])
		(self
			top: (/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
			left: (/ (- 320 (+ 115 (CelWide 947 0 1))) 2)
			bottom:
				(+
					(CelHigh 947 1 1)
					6
					(/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
				)
			right:
				(+
					115
					(CelWide 947 0 1)
					(/ (- 320 (+ 115 (CelWide 947 0 1))) 2)
				)
			priority: 15
		)
		(super open:)
		(DrawCel 947 0 5
			(+
				(/
					(-
						(- (+ 115 (CelWide 947 0 1)) (+ 4 (CelWide 947 1 1)))
						(CelWide 947 0 5)
					)
					2
				)
				4
				(CelWide 947 1 1)
			)
			6
			15
		)
		(DrawCel 947 1 1 4 3 15)
		(DrawCel 947 1 0 94 38 15)
		(DrawCel 947 0 4 63 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 3 101 (- 37 (+ (CelHigh 947 0 4) 3)) 15)
		(= b (+ (= t (+ 46 (CelHigh 947 0 1))) 13))
		(= r
			(+
				(= l (+ 10 (CelWide 947 1 1)))
				(-
					(+ 115 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= thePri 15)
		(= temp6 0)
		(= temp8 global156)
		(= temp11 (VGAOrEGA global157 global156))
		(= temp10 (VGAOrEGA global161 global130))
		(= temp9 global130)
		(= temp1 3)
		(= temp7 3)
		(Graph GFillRect
			t
			l
			(+ b 1)
			(+ r 1)
			temp7
			temp6
			thePri
		)
		(= t (- t temp1))
		(= l (- l temp1))
		(= r (+ r temp1))
		(= b (+ b temp1))
		(Graph GFillRect
			t
			l
			(+ t temp1)
			r
			temp7
			temp8
			thePri
		)
		(Graph GFillRect
			(- b temp1)
			l
			b
			r
			temp7
			temp9
			thePri
		)
		(= temp13 0)
		(while (< temp13 temp1)
			(Graph
				GDrawLine
				(+ t temp13)
				(+ l temp13)
				(- b (+ temp13 1))
				(+ l temp13)
				temp11
				thePri
				-1
			)
			(Graph
				GDrawLine
				(+ t temp13)
				(- r (+ temp13 1))
				(- b (+ temp13 1))
				(- r (+ temp13 1))
				temp10
				thePri
				-1
			)
			(++ temp13)
		)
		(Graph GShowBits
			t
			l
			(+ b 1)
			(+ r 1)
			VMAP
		)
		(Format @scoreBuf 0 4 score possibleScore)
		(TextSize @scoreLen @scoreBuf 999 0)
		(Display @scoreBuf
			p_font 999
			p_color (VGAOrEGA global161 global130)
			p_at
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 115 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[scoreLen 3]
					)
					2
				)
			)
			(+ 46 (CelHigh 947 0 1) 3)
		)
	)
)

(instance controlBox of GameControls)

(instance detailSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 67
		nsTop 37
		signal FIXED_POSN
		helpStr {Raises and lowers the puzzle difficulty level.}
		sliderView 947
		bottomValue 1
		topValue 3
	)
)

(instance volumeSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 107
		nsTop 37
		signal FIXED_POSN
		helpStr {Adjusts sound volume.}
		sliderView 947
		topValue 15
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		signal $00c3
		helpStr {Saves your current game position.}
		lowlightColor 4
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 947
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		signal $00c3
		helpStr {Restores a previously saved game position}
		lowlightColor 4
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 947
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		signal $00c3
		helpStr {Restarts the Game.}
		lowlightColor 4
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		signal $00c3
		helpStr {Exits the game.}
		lowlightColor 4
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		nsLeft 8
		nsTop 86
		message 9
		signal $01c3
		helpStr {Information about the game.}
	)
)

(instance iconHelp of IconItem
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 50
		message verbHelp
		signal (| RELVERIFY IMMEDIATE)
		lowlightColor 4
	)
)

(instance iconOk of IconItem
	(properties
		view 947
		loop 8
		cel 0
		nsLeft 8
		nsTop 106
		cursor 50
		signal $00c3
		helpStr {Exits this menu.}
		lowlightColor 4
	)
)

(instance BrainVerbCode of Code
	
	(method (doit theVerb theObj theItem &tmp desc [str 100])
		(if (= desc (theObj description?))
			(switch theVerb
				(verbLook
					(if (theObj lookStr?)
						(HighPrint (theObj lookStr?))
					else
						(HighPrint (Format @str 0 5 desc))
					)
				)
				(verbUse
					(if (>= theItem 0)
						(HighPrint
							(Format @str 0 6
								((inventory at: theItem) description?)
								desc
							)
						)
					else
						(VerbFail)
					)
				)
				(verbDo
					(HighPrint (Format @str 0 7 desc))
				)
				(verbTalk
					(HighPrint (Format @str 0 8 desc))
				)
			)
		else
			(VerbFail)
		)
	)
)

(instance aboutCode of Code
	
	(method (doit &tmp [str 300])
		(Print
			(Format @str 0 9 version)
			#mode teJustCenter
			#width 120
		)
		(Print
			(Format @str 0 10)
			#mode teJustCenter
			#width 120
		)
		(Print
			(Format @str 0 11)
			#mode teJustCenter
			#width 120
		)
	)
)
