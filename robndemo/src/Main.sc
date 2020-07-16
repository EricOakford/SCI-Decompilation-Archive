;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use TapestryWindow)
(use PMouse)
(use GControl)
(use BordWind)
(use IconBar)
(use LoadMany)
(use Sound)
(use Game)
(use User)
(use System)

(public
	RH 0
	HandsOff 1
	HandsOn 2
	DoDisplay 3
	InitAddToPics 4
	Btst 5
	Bset 6
	Bclr 7
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
	speechHandler
	lastVolume
	pMouse
	theDoits
	eatMice =  60
	user
	syncBias
	theSync
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
	global100
	transferRoom
	isVGA
	global103
	global104
	numVoices
	numColors
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
	global117 =  1
	global118
	global119
	global120
	forestSound
	introSound
	loveSound
	bowSound
	cDAudio
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
	global300
)
(procedure (HandsOff)
	(User canControl: FALSE canInput: FALSE)
	(theGame setCursor: waitCursor TRUE 189 85)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(theGame setCursor: normalCursor (HaveMouse) 321 189)
)

(procedure (DoDisplay args &tmp theMode theBackFont theForeFont theWidth theX theY theForeColor theBackColor i)
	(return
		(if (== argc 1)
			(Display 0 2
				p_restore [args 0]
			)
		else
			(= theX (= theY -1))
			(= theMode teJustLeft)
			(= theBackFont 69)
			(= theForeFont 68)
			(= theWidth 320)
			(= theForeColor 127)
			(= theBackColor 126)
			(= i 1)
			(while (< i argc)
				(switch [args i]
					(#mode
						(= theMode [args (++ i)])
					)
					(#font
						(= theForeFont (+ (= theBackFont [args (++ i)]) 1))
					)
					(#width
						(= theWidth [args (++ i)])
					)
					(#at
						(= theX [args (++ i)])
						(= theY [args (++ i)])
					)
					(#color
						(= theForeColor [args (++ i)])
					)
					(#back
						(= theBackColor [args (++ i)])
					)
				)
				(++ i)
			)
			(= i
				(Display [args 0]
					p_at theX theY
					p_color theBackColor
					p_width theWidth
					p_mode theMode
					p_font theBackFont
					p_save
				)
			)
			(Display [args 0]
				p_at theX theY
				p_color theForeColor
				p_width theWidth
				p_mode theMode
				p_font theForeFont
			)
			(return i)
		)
	)
)

(procedure (InitAddToPics)
	(addToPics add: &rest eachElementDo: #init doit:)
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
	(= oldState (Btst flagEnum))
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

(instance egoObj of Ego
	(properties
		name {ego}
		description {Roger Hood}
		lookStr {*** It's Little Red Robin Hood!}
	)
)

(instance forestNoise of Sound
	(properties
		number 1
		priority 13
	)
)

(instance introSong of Sound
	(properties
		number 995
		priority 11
		loop -1
	)
)

(instance loveSong of Sound
	(properties
		number 998
		priority 11
	)
)

(instance bowSong of Sound
	(properties
		number 999
		priority 11
	)
)

(instance MH of EventHandler)

(instance KH of EventHandler)

(instance DH of EventHandler)

(instance RH of Game
	
	(method (init &tmp [temp0 21])
		(LoadMany SCRIPT 95 220 210 320 850 MOTION)
		(LoadMany TEXT 95 220 210 320)
		(LoadMany PICTURE 95 96 211 216 119 330)
		(LoadMany VIEW 95 216 217 124 326 331)
		(Load FONT SYSFONT)
		(LoadMany SOUND
			1 100 101 501 502 503
			504 505 506 507 508
			995 998 999
		)
		(= systemWindow TapestryWindow)
		(= useSortedFeatures FALSE)
		(super init:)
		(StrCpy @sysLogPath {})
		(= pMouse PseudoMouse)
		((= mouseDownHandler MH) add:)
		((= keyDownHandler KH) add:)
		((= directionHandler DH) add:)
		(= ego egoObj)
		((= forestSound forestNoise) owner: self)
		((= introSound introSong) owner: self)
		((= loveSound loveSong) owner: self)
		((= bowSound bowSong) owner: self)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: FALSE
			canInput: FALSE
		)
		(self setCursor: theCursor TRUE 304 172)
		(= waitCursor HAND_CURSOR)
		(= userFont SYSFONT)
		(= global117 0)
		(= version {0.004})
		(= cDAudio (DoSound NumDACs))
		(= numVoices (DoSound NumVoices))
		(if
			(and
				(>= (= numColors (Graph GDetect)) 2)
				(<= numColors 16)
			)
			(Bclr isVGA)
		else
			(Bset isVGA)
		)
		((= theIconBar IconBar)
			add: iconControl
			eachElementDo: #init
			curIcon: iconControl
			useIconItem: iconControl
		)
		(GameControls
			window: gcWindow
			add:
				iconOk
				(volumeSlider
					theObj: self
					selector: #masterVolume
					topValue: 15
					bottomValue: 0
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
			curIcon: iconQuit
		)
		(= transferRoom (if (GameIsRestarting) 95 else 95))
		(ScriptID POLYPATH)
		(self newRoom: 95)
	)
	
	(method (startRoom roomNum)
		(if pMouse (pMouse stop:))
		(LoadMany FALSE POLYPATH POLYGON STOPWALK)
		(HandsOn)
		(HandsOff)
		(super startRoom: roomNum)
		(self setCursor: normalCursor TRUE 321 189)
	)
)

(instance iconControl of IconItem
	(properties
		view 949
		loop 5
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		lowlightColor 5
	)
	
	(method (select)
		(if (super select:)
			(if global126
				(return)
			else
				(theIconBar hide:)
				(GameControls show:)
			)
		)
	)
)

(instance volumeSlider of Slider
	(properties
		view 949
		loop 6
		cel 1
		nsLeft 83
		nsTop 36
		signal FIXED_POSN
		helpStr {Adjusts overall sound volume.}
		sliderView 949
		sliderLoop 6
		topValue 15
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 949
		loop 1
		cel 0
		nsLeft 26
		nsTop 21
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Quits the demo.}
		lowlightColor 5
	)
)

(instance iconHelp of IconItem
	(properties
		view 949
		loop 2
		cel 0
		nsLeft 8
		nsTop 41
		cursor 6
		message 6
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {For what your doing.}
		lowlightColor 5
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 949
		loop 4
		cel 0
		nsLeft 8
		nsTop 21
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Information about the demo.}
		lowlightColor 5
	)
)

(instance iconOk of IconItem
	(properties
		view 949
		loop 3
		cel 0
		nsLeft 26
		nsTop 41
		cursor 6
		signal (| FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Exits this menu and resumes demo.}
		lowlightColor 5
	)
)

(instance gcWindow of BorderWindow
	
	(method (open)
		(self
			top: 45
			left: 103
			bottom: 150
			right: 218
			priority: 15
		)
		(super open:)
		(DrawCel 949 0 1 35 3 15)
		(DrawCel 949 0 0 4 18 15)
		(DrawCel 949 6 2 69 38 34)
		(DrawCel 949 0 2 69 18 15)
		(DrawCel 949 0 3 14 67 15)
		(DrawCel 949 0 4 13 79 15)
		(DrawCel 949 0 5 24 91 15)
	)
)

(instance aboutCode of Code
	
	(method (doit)
		(Printf 0 0 version)
		(Print 0 1
			#mode teJustCenter
			#width 230
		)
	)
)
