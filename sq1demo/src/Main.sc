;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use ColorInit)
(use GControl)
(use BordWind)
(use IconBar)
(use LoadMany)
(use Grooper)
(use Window)
(use Sound)
(use Game)
(use Invent)
(use User)
(use System)

(public
	SQ1Demo 0
	HandsOff 1
	HandsOn 2
	PromptQuit 3
	VerbFail 4
	theSound 5
	sqSound 6
	sqSound2 7
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
	cDAudio
	fastCast
	inputFont
	tickOffset
	howFast
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
	music
	egoGrooper
	global102
	global103
	global104
	myTextColor
	myTextColor2
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
)
(procedure (HandsOff &tmp theIcon)
	(User canControl: FALSE canInput: FALSE)
	(= theIcon (theIconBar curIcon?))
	(theIconBar disable: 
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_SMELL
		ICON_TASTE
		ICON_ITEM
		ICON_INVENTORY
	)
	(theIconBar curIcon: theIcon)
	(theGame setCursor: waitCursor TRUE)
)

(procedure (HandsOn)
	(User canControl: TRUE canInput: TRUE)
	(theIconBar enable: 
		ICON_WALK
		ICON_LOOK
		ICON_DO
		ICON_TALK
		ICON_SMELL
		ICON_TASTE
		ICON_ITEM
		ICON_INVENTORY
	)
	(theGame setCursor: ((theIconBar curIcon?) cursor?))
)

(procedure (PromptQuit)
	(= quit
		(Print 0 11
			#title {If it's time to split...}
			#button {Quit} 1
			#button {Don't Quit} 0
		)
	)
)

(procedure (VerbFail &tmp oldCur event temp2 temp3 temp4 temp5 temp6 underBits)
	(if (User canInput:)
		(= oldCur (theGame setCursor: 69 TRUE))
		(= temp3 (- ((= event (User curEvent?)) y?) 5))
		(= temp4 (- (event x?) 6))
		(= temp5 (+ (event y?) 6))
		(= temp6 (+ (event x?) 6))
		(= underBits (Graph GSaveBits temp3 temp4 temp5 temp6 3))
		(DrawCel 942 0 0 temp4 temp3 15)
		(Animate (cast elements?) FALSE)
		(= temp2 (GetTime))
		(while (< (Abs (- temp2 (GetTime))) 40)
			(breakif
				(OneOf ((= event (Event new:)) type?) keyDown mouseDown)
			)
			(event dispose:)
		)
		(if (IsObject event) (event dispose:))
		(Graph GRestoreBits underBits)
		(Graph GReAnimate temp3 temp4 temp5 temp6)
		(theGame setCursor: oldCur)
	)
)

(instance egoObj of Ego
	(properties)
)

(instance rogerGrooper of GradualLooper
	(properties)
)

(instance theSound of Sound
	(properties
		number 1
	)
)

(instance sqSound of Sound
	(properties
		number 1
		priority 5
	)
)

(instance sqSound2 of Sound
	(properties
		number 1
		priority 6
	)
)

(instance MouseDownHandler of EventHandler
	(properties
		name "MH"
	)
)

(instance KeyDownHandler of EventHandler
	(properties
		name "KH"
	)
)

(class DirectionHandler of EventHandler
	(properties
		name "DH"
		cursorInc 2
	)
	
	(method (handleEvent event &tmp evtX evtY)
		(cond 
			((super handleEvent: event))
			(
				(and
					(!= (self indexOf: (theIconBar curIcon?)) 0)
					(User canInput:)
				)
				(= evtX (event x?))
				(= evtY (event y?))
				(cond 
					((& (event modifiers?) shiftDown) (if (> (Abs cursorInc) 2) (= cursorInc 2)))
					((< cursorInc 20) (= cursorInc 20))
				)
				(switch (event message?)
					(dirN
						(= evtY (- evtY cursorInc))
					)
					(dirNE
						(= evtX (+ evtX cursorInc))
						(= evtY (- evtY cursorInc))
					)
					(dirE
						(= evtX (+ evtX cursorInc))
					)
					(dirSE
						(= evtX (+ evtX cursorInc))
						(= evtY (+ evtY cursorInc))
					)
					(dirS
						(= evtY (+ evtY cursorInc))
					)
					(dirSW
						(= evtX (- evtX cursorInc))
						(= evtY (+ evtY cursorInc))
					)
					(dirW
						(= evtX (- evtX cursorInc))
					)
					(dirNW
						(= evtX (- evtX cursorInc))
						(= evtY (- evtY cursorInc))
					)
					(dirStop
						(event claimed: FALSE)
						(return)
					)
				)
				(theGame setCursor: theCursor TRUE evtX evtY)
				(event claimed: TRUE)
				(return)
			)
		)
	)
)

(instance SQ1Demo of Game
	(properties)
	
	(method (init)
		(ColorInit)
		(SysWindow color: 0 back: 46)
		(= systemWindow SysWindow)
		(super init:)
		(= ego egoObj)
		(theSound owner: self)
		(= egoGrooper rogerGrooper)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: 0
			canInput: 0
		)
		(buckazoid owner: ego)
		(= doVerbCode DoVerbCode)
		(= ftrInitializer FtrInit)
		((= mouseDownHandler MouseDownHandler) add:)
		((= keyDownHandler KeyDownHandler) add:)
		((= directionHandler DirectionHandler) add:)
		(= waitCursor 901)
		(= userFont 4)
		(= version {x.yyy})
		((= theIconBar IconBar)
			add:
				iconWalk
				iconLook
				iconDo
				iconTalk
				iconSmell
				iconTaste
				iconUse
				iconInvSel
				iconControl
				iconWhat
			eachElementDo: #init
			curIcon: iconWalk
			useIconItem: iconUse
			helpIconItem: iconWhat
			disable:
		)
		(iconInvSel message: (if (HaveMouse) 3840 else 9))
		(HandsOff)
		(GameControls
			window: gcWindow
			add:
				iconOk
				(detailSlider
					theObj: self
					selector: #detailLevel
					topValue: 3
					bottomValue: 0
					yourself:
				)
				(volumeSlider
					theObj: self
					selector: #masterVolume
					topValue: 1
					bottomValue: 0
					yourself:
				)
				(speedSlider
					theObj: self
					selector: #setSpeed
					topValue: 1
					bottomValue: 15
					yourself:
				)
				(iconSave theObj: self selector: #save yourself:)
				(iconRestore theObj: self selector: #restore yourself:)
				(iconRestart theObj: self selector: #restart yourself:)
				(iconQuit theObj: self selector: #quitGame yourself:)
				(iconAbout theObj: aboutCode selector: #doit yourself:)
				iconHelp
			helpIconItem: iconHelp
			curIcon: iconRestore
		)
		(Inventory
			init:
			add: buckazoid invLook invHand invSelect invHelp ok
			window: InsetWindow
			helpIconItem: invHelp
			selectIcon: invSelect
			okButton: ok
		)
		(self newRoom: 1)
	)
	
	(method (startRoom roomNum &tmp temp0)
		(LoadMany FALSE POLYPATH POLYGON SIGHT)
		(HandsOn)
		(super startRoom: roomNum)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(switch (event type?)
			(keyDown
				(keyDownHandler handleEvent: event)
				(if
					(and
						(not (event claimed?))
						(== (event message?) `^q)
					)
					(PromptQuit)
				)
			)
			(mouseDown
				(mouseDownHandler handleEvent: event)
			)
		)
	)
)

(instance ok of IconItem
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		cursor 999
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {Select this Icon to close this window.}
		lowlightColor 7
	)
)

(instance invLook of IconItem
	(properties
		view 901
		loop 2
		cel 0
		cursor 19
		message verbLook
		helpStr {Select this Icon and then click on an inventory item to get a description of it.}
		lowlightColor 7
	)
)

(instance invHand of IconItem
	(properties
		view 901
		loop 0
		cel 0
		cursor 20
		message verbDo
		helpStr {This allows you to do something to an item.}
		lowlightColor 7
	)
)

(instance invHelp of IconItem
	(properties
		view 901
		loop 1
		cel 0
		cursor 29
		message verbHelp
		lowlightColor 7
	)
)

(instance invSelect of IconItem
	(properties
		view 901
		loop 4
		cel 0
		cursor ARROW_CURSOR
		helpStr {This allows you to select an item.}
		lowlightColor 7
	)
)

(instance buckazoid of InvItem
	(properties
		view 700
		cursor 1
		signal IMMEDIATE
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbLook (Printf 0 0))
		)
	)
)

(instance iconWalk of IconItem
	(properties
		view 900
		loop 0
		cel 0
		cursor 6
		message verbWalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for walking.}
		maskView 900
		maskLoop 14
		maskCel 1
		lowlightColor 7
	)
)

(instance iconLook of IconItem
	(properties
		view 900
		loop 1
		cel 0
		cursor 19
		message verbLook
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for looking.}
		maskView 900
		maskLoop 14
		maskCel 1
		lowlightColor 7
	)
)

(instance iconDo of IconItem
	(properties
		view 900
		loop 2
		cel 0
		cursor 20
		message verbDo
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for doing.}
		maskView 900
		maskLoop 14
		lowlightColor 7
	)
)

(instance iconTalk of IconItem
	(properties
		view 900
		loop 3
		cel 0
		cursor 7
		message verbTalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for talking.}
		maskView 900
		maskLoop 14
		maskCel 3
		lowlightColor 7
	)
)

(instance iconUse of IconItem
	(properties
		view 900
		loop 4
		cel 0
		cursor 999
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon selects the current inventory item.}
		maskView 900
		maskLoop 14
		maskCel 4
		lowlightColor 7
	)
)

(instance iconInvSel of IconItem
	(properties
		view 900
		loop 5
		cel 0
		cursor 999
		type NULL
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the inventory window.}
		maskView 900
		maskLoop 14
		maskCel 2
		lowlightColor 7
	)
	
	(method (select)
		(if (super select:) (Inventory showSelf: ego))
	)
)

(instance iconSmell of IconItem
	(properties
		view 900
		loop 10
		cel 0
		cursor 30
		message verbSmell
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for smelling.}
		maskView 900
		maskLoop 14
		lowlightColor 7
	)
)

(instance iconTaste of IconItem
	(properties
		view 900
		loop 11
		cel 0
		cursor 31
		message verbTaste
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for tasting.}
		maskView 900
		maskLoop 14
		maskCel 1
		lowlightColor 7
	)
)

(instance iconControl of IconItem
	(properties
		view 900
		loop 7
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the control panel.}
		maskView 900
		maskLoop 14
		maskCel 1
		lowlightColor 7
	)
	
	(method (select)
		(if (super select:)
			(theIconBar hide:)
			(GameControls show:)
		)
	)
)

(instance iconWhat of IconItem
	(properties
		view 900
		loop 9
		cel 0
		cursor 29
		message 6
		signal (| RELVERIFY IMMEDIATE)
		helpStr {This icon allows you to find out what the other icons do.}
		maskView 900
		maskLoop 14
		lowlightColor 7
	)
)

(instance DoVerbCode of Code
	(properties)
	
	(method (doit theVerb theObj &tmp desc)
		(= desc 11)
		(= desc (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj facingMe: ego)
					(if (theObj lookStr?)
						(Print (theObj lookStr?))
					else
						(Printf 0 1 desc)
					)
				)
			)
			(verbTalk (Printf 0 2 desc))
			(verbDo (Printf 0 3 desc))
			(verbUse (Printf 0 4 desc))
			(verbSmell
				(switch (Random 0 2)
					(0 (Print 0 5))
					(1 (Print 0 6))
					(2 (Print 0 7))
				)
			)
			(verbTaste (Print 0 8))
			(else  (VerbFail))
		)
	)
)

(instance FtrInit of Code
	(properties)
	
	(method (doit theOvj)
		(if (== (theOvj sightAngle?) ftrDefault)
			(theOvj sightAngle: 90)
		)
		(if (== (theOvj actions?) ftrDefault) (theOvj actions: 0))
	)
)

(instance gcWindow of BorderWindow
	(properties)
	
	(method (open &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 i [str 15] [len 4])
		(self
			top: (/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
			left: (/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
			bottom:
				(+
					(CelHigh 947 1 1)
					6
					(/ (- 200 (+ (CelHigh 947 1 1) 6)) 2)
				)
			right:
				(+
					151
					(CelWide 947 0 1)
					(/ (- 320 (+ 151 (CelWide 947 0 1))) 2)
				)
			priority: 15
			back: 7
			color: 4
			topBordColor: 8
			lftBordColor: 8
			rgtBordColor: 0
			botBordColor: 0
		)
		(super open:)
		(DrawCel 947 1 1 4 3 15)
		(DrawCel 947 1 0 94 16 15)
		(DrawCel 947 1 0 135 16 15)
		(DrawCel 947 0 4 63 (- 15 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 3 101 (- 15 (+ (CelHigh 947 0 4) 3)) 15)
		(DrawCel 947 0 2 146 (- 15 (+ (CelHigh 947 0 4) 3)) 15)
		(= temp5 (+ (= temp2 (+ 25 (CelHigh 947 0 1))) 30))
		(= temp4
			(+
				(= temp3 (+ 10 (CelWide 947 1 1)))
				(-
					(+ 151 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= temp7 15)
		(= temp1 3)
		(= temp6 3)
		(Graph
			GFillRect
			temp2
			temp3
			(+ temp5 1)
			(+ temp4 1)
			temp6
			0
			temp7
		)
		(= temp2 (- temp2 temp1))
		(= temp3 (- temp3 temp1))
		(= temp4 (+ temp4 temp1))
		(= temp5 (+ temp5 temp1))
		(Graph
			GFillRect
			temp2
			temp3
			(+ temp2 temp1)
			temp4
			temp6
			7
			temp7
		)
		(Graph
			GFillRect
			(- temp5 temp1)
			temp3
			temp5
			temp4
			temp6
			8
			temp7
		)
		(= i 0)
		(while (< i temp1)
			(Graph
				GDrawLine
				(+ temp2 i)
				(+ temp3 i)
				(- temp5 (+ i 1))
				(+ temp3 i)
				7
				-1
				-1
			)
			(Graph
				GDrawLine
				(+ temp2 i)
				(- temp4 (+ i 1))
				(- temp5 (+ i 1))
				(- temp4 (+ i 1))
				7
				-1
				-1
			)
			(++ i)
		)
		(Graph
			GShowBits
			temp2
			temp3
			(+ temp5 1)
			(+ temp4 1)
			1
		)
		(Format @str 0 9 score possibleScore)
		(TextSize @len @str 999 0)
		(Display @str
			p_font 999
			p_color 8
			p_at
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[len 3]
					)
					2
				)
			)
			(+ 25 (CelHigh 947 0 1) 3)
		)
		(Format @str 0 10 version)
		(TextSize @len @str 999 0)
		(Display @str
			p_font 999
			p_color 8
			p_at
			(+
				10
				(CelWide 947 1 1)
				(/
					(-
						(-
							(+ 151 (CelWide 947 0 1))
							(+ 10 (CelWide 947 1 1) 6)
						)
						[len 3]
					)
					2
				)
			)
			(+ 25 (CelHigh 947 0 1) 3 16)
		)
	)
)

(instance detailSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 67
		nsTop 15
		signal FIXED_POSN
		helpStr {Raises and lowers the level of graphics detail.}
		sliderView 947
		topValue 3
	)
)

(instance volumeSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 107
		nsTop 15
		signal FIXED_POSN
		helpStr {Adjusts sound volume.}
		sliderView 947
		topValue 15
	)
)

(instance speedSlider of Slider
	(properties
		view 947
		loop 0
		cel 1
		nsLeft 147
		nsTop 15
		signal FIXED_POSN
		helpStr {Adjusts the speed of the game's animation (within the limits of your computer's capability).}
		sliderView 947
		bottomValue 15
	)
)

(instance iconSave of ControlIcon
	(properties
		view 947
		loop 2
		cel 0
		nsLeft 8
		nsTop 6
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr {Saves your current game.}
		lowlightColor 8
	)
)

(instance iconRestore of ControlIcon
	(properties
		view 947
		loop 3
		cel 0
		nsLeft 8
		nsTop 26
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr {Restores a previously saved game.}
		lowlightColor 8
	)
)

(instance iconRestart of ControlIcon
	(properties
		view 947
		loop 4
		cel 0
		nsLeft 8
		nsTop 46
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr {Restarts the Game.}
		lowlightColor 8
	)
)

(instance iconQuit of ControlIcon
	(properties
		view 947
		loop 5
		cel 0
		nsLeft 8
		nsTop 66
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Exits the game.}
		lowlightColor 8
	)
)

(instance iconAbout of ControlIcon
	(properties
		view 947
		loop 6
		cel 0
		nsLeft 8
		nsTop 86
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Information about the game.}
		lowlightColor 8
	)
)

(instance iconHelp of IconItem
	(properties
		view 947
		loop 7
		cel 0
		nsLeft 34
		nsTop 86
		cursor 70
		message 6
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE)
		lowlightColor 8
	)
)

(instance iconOk of IconItem
	(properties
		view 947
		loop 8
		cel 0
		nsLeft 8
		nsTop 106
		cursor 70
		signal (| VICON FIXED_POSN RELVERIFY IMMEDIATE HIDEBAR)
		helpStr {Exits this menu.}
		lowlightColor 8
	)
)

(instance aboutCode of Code
	(properties)
	
	(method (doit)
		(Print 0 12)
	)
)
