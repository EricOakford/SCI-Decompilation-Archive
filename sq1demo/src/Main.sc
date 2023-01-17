;;; Sierra Script 1.0 - (do not remove this comment)
(script# 0)
(include game.sh)
(use Intrface)
(use ColorInit)
(use SlideIcon)
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
	NoResponse 4
	theSound 5
	sqSound 6
	sqSound2 7
)

(local
	ego										;pointer to ego
	theGame									;ID of the Game instance
	curRoom									;ID of current room
	speed				=	6				;number of ticks between animations
	quit									;when TRUE, quit game
	cast									;collection of actors
	regions									;set of current regions
	timers									;list of timers in the game
	sounds									;set of sounds being played
	inventory								;set of inventory items in game
	addToPics								;list of views added to the picture
	curRoomNum								;current room number
	prevRoomNum								;previous room number
	newRoomNum								;number of room to change to
	debugOn									;generic debug flag -- set from debug menu
	score									;the player's current score
	possibleScore							;highest possible score
	showStyle			=	IRISOUT			;style of picture showing
	aniInterval								;# of ticks it took to do the last animation cycle
	theCursor								;the number of the current cursor
	normalCursor		=	ARROW_CURSOR	;number of normal cursor form
	waitCursor			=	HAND_CURSOR		;cursor number of "wait" cursor
	userFont			=	USERFONT		;font to use for Print
	smallFont			=	4		;small font for save/restore, etc.
	lastEvent								;the last event (used by save/restore game)
	modelessDialog							;the modeless Dialog known to User and Intrface
	bigFont				=	USERFONT		;large font
	version				=	0				;pointer to 'incver' version string
											;***WARNING***  Must be set in room 0
											; (usually to {x.yyy    } or {x.yyy.zzz})
	locales									;set of current locales
	curSaveDir								;address of current save drive/directory string
	aniThreshold		=	10
	perspective								;player's viewing angle:
											;	 degrees away from vertical along y axis
	features								;locations that may respond to events
	sortedFeatures							;above+cast sorted by "visibility" to ego
	useSortedFeatures	=	FALSE			;enable cast & feature sorting?
	egoBlindSpot		=	0				;used by sortCopy to exclude 
											;actors behind ego within angle 
											;from straight behind. 
											;Default zero is no blind spot
	overlays			=	-1
	doMotionCue								;a motion cue has occurred - process it
	systemWindow							;ID of standard system window
	demoDialogTime		=	3				;how long Prints stay up in demo mode
	currentPalette							;
	modelessPort		
	sysLogPath								;used for system standard logfile path	
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
	endSysLogPath					;uses 20 globals
	gameControls		
	ftrInitializer		
	doVerbCode			
	firstSaidHandler				;will be the first to handle said events
	useObstacles		=	TRUE	;will Ego use PolyPath or not?
	theMenuBar						;points to TheMenuBar or Null	
	theIconBar						;points to TheIconBar or Null	
	mouseX				
	mouseY				
	keyDownHandler					;our EventHandlers, get called by the game
	mouseDownHandler	
	directionHandler	
	gameCursor			
	lastVolume			
	pMouse				=	NULL	;pointer to a Pseudo-Mouse, or NULL
	theDoits			=	NULL	;list of objects to get doits done every cycle
	eatMice				=	60		;how many ticks minimum that a window stays up	
	user				=	NULL	;pointer to specific applications User
	syncBias						;; globals used by sync.sc (will be removed shortly)
	theSync				
	cDAudio				
	fastCast			
	inputFont			=	SYSFONT	;font used for user type-in

	tickOffset			

	;globals 88-99 are unused
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
	sGroop
	global102
	global103
	global104
	colBlack
	colWhite
	colDRed
	colLRed
	colVLRed
	colDYellow
	colYellow
	colLYellow
	colLGreen
	colVLGreen
	colDBlue
	colMagenta
	colCyan
	global118
	global119
	colBlue
	colDGreen
	global122
	colLBlue
	colVLBlue
	colGray1
	colGray2
	colGray3
	colGray4
	colGray5
	colLCyan
	colLMagenta
	;the remaining globals appear to be unused
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
(procedure (HandsOff &tmp saveIcon)
	(User canControl: FALSE canInput: FALSE)
	(= saveIcon (theIconBar curIcon?))
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
	(theIconBar curIcon: saveIcon)
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

(procedure (NoResponse &tmp oldCur event theTime t l b r saveBits)
	(if (User canInput:)
		(= oldCur (theGame setCursor: 69 TRUE))
		(= t (- ((= event (User curEvent?)) y?) 5))
		(= l (- (event x?) 6))
		(= b (+ (event y?) 6))
		(= r (+ (event x?) 6))
		(= saveBits (Graph GSaveBits t l b r (| VMAP PMAP)))
		(DrawCel 942 0 0 l t 15)
		(Animate (cast elements?) FALSE)
		(= theTime (GetTime))
		(while (< (Abs (- theTime (GetTime))) 40)
			(breakif
				(OneOf ((= event (Event new:)) type?) keyDown mouseDown)
			)
			(event dispose:)
		)
		(if (IsObject event)
			(event dispose:)
		)
		(Graph GRestoreBits saveBits)
		(Graph GReAnimate t l b r)
		(theGame setCursor: oldCur)
	)
)

(instance egoObj of Ego)

(instance rogerGrooper of GradualLooper)

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

(instance MH of EventHandler)

(instance KH of EventHandler)

(class DH of EventHandler
	(properties
		cursorInc 2
	)
	
	(method (handleEvent event &tmp evtX evtY)
		(cond 
			((super handleEvent: event))
			(
				(and
					(!= (self indexOf: (theIconBar curIcon?)) ICON_WALK)
					(User canInput:)
				)
				(= evtX (event x?))
				(= evtY (event y?))
				(cond 
					((& (event modifiers?) shiftDown)
						(if (> (Abs cursorInc) 2)
							(= cursorInc 2)
						)
					)
					((< cursorInc 20)
						(= cursorInc 20)
					)
				)
				(switch (event message?)
					(dirN
						(-= evtY cursorInc)
					)
					(dirNE
						(+= evtX cursorInc)
						(-= evtY cursorInc)
					)
					(dirE
						(+= evtX cursorInc)
					)
					(dirSE
						(+= evtX cursorInc)
						(+= evtY cursorInc)
					)
					(dirS
						(+= evtY cursorInc)
					)
					(dirSW
						(-= evtX cursorInc)
						(+= evtY cursorInc)
					)
					(dirW
						(-= evtX cursorInc)
					)
					(dirNW
						(-= evtX cursorInc)
						(-= evtY cursorInc)
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
	(method (init)
		(ColorInit)
		(SysWindow color: 0 back: 46)
		(= systemWindow SysWindow)
		(super init:)
		(= ego egoObj)
		(theSound owner: self)
		(= sGroop rogerGrooper)
		(User
			alterEgo: ego
			verbMessager: 0
			canControl: FALSE
			canInput: FALSE
		)
		(buckazoid owner: ego)
		(= doVerbCode DoVerbCode)
		(= ftrInitializer FtrInit)
		((= mouseDownHandler MH) add:)
		((= keyDownHandler KH) add:)
		((= directionHandler DH) add:)
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
		(iconInvSel message: (if (HaveMouse) SHIFTTAB else TAB))
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
		(LoadMany FALSE POLYGON POLYPATH SIGHT)
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
		cursor ARROW_CURSOR
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
			(verbLook
				(Printf 0 0)
			)
		)
	)
)

(instance iconWalk of IconItem
	(properties
		view vIcons
		loop 0
		cel 0
		cursor 6
		message 1
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for walking.}
		maskView vIcons
		maskLoop 14
		maskCel 1
		lowlightColor 7
	)
)

(instance iconLook of IconItem
	(properties
		view vIcons
		loop 1
		cel 0
		cursor 19
		message verbLook
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for looking.}
		maskView vIcons
		maskLoop 14
		maskCel 1
		lowlightColor 7
	)
)

(instance iconDo of IconItem
	(properties
		view vIcons
		loop 2
		cel 0
		cursor 20
		message verbDo
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for doing.}
		maskView vIcons
		maskLoop 14
		lowlightColor 7
	)
)

(instance iconTalk of IconItem
	(properties
		view vIcons
		loop 3
		cel 0
		cursor 7
		message verbTalk
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for talking.}
		maskView vIcons
		maskLoop 14
		maskCel 3
		lowlightColor 7
	)
)

(instance iconUse of IconItem
	(properties
		view vIcons
		loop 4
		cel 0
		cursor ARROW_CURSOR
		message verbUse
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon selects the current inventory item.}
		maskView vIcons
		maskLoop 14
		maskCel 4
		lowlightColor 7
	)
)

(instance iconInvSel of IconItem
	(properties
		view vIcons
		loop 5
		cel 0
		cursor ARROW_CURSOR
		type nullEvt
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the inventory window.}
		maskView vIcons
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
		view vIcons
		loop 10
		cel 0
		cursor 30
		message verbSmell
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for smelling.}
		maskView vIcons
		maskLoop 14
		lowlightColor 7
	)
)

(instance iconTaste of IconItem
	(properties
		view vIcons
		loop 11
		cel 0
		cursor 31
		message verbTaste
		signal (| HIDEBAR RELVERIFY)
		helpStr {This icon is for tasting.}
		maskView vIcons
		maskLoop 14
		maskCel 1
		lowlightColor 7
	)
)

(instance iconControl of IconItem
	(properties
		view vIcons
		loop 7
		cel 0
		cursor ARROW_CURSOR
		signal (| HIDEBAR RELVERIFY IMMEDIATE)
		helpStr {This icon brings up the control panel.}
		maskView vIcons
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
		view vIcons
		loop 9
		cel 0
		cursor 29
		message verbHelp
		signal (| RELVERIFY IMMEDIATE)
		helpStr {This icon allows you to find out what the other icons do.}
		maskView vIcons
		maskLoop 14
		lowlightColor 7
	)
)

(instance DoVerbCode of Code
	(method (doit theVerb theObj &tmp objDesc)
		(= objDesc 11)
		(= objDesc (theObj description?))
		(switch theVerb
			(verbLook
				(if (theObj facingMe: ego)
					(if (theObj lookStr?)
						(Print (theObj lookStr?))
					else
						(Printf 0 1 objDesc)
					)
				)
			)
			(verbTalk
				(Printf 0 2 objDesc)
			)
			(verbDo
				(Printf 0 3 objDesc)
			)
			(verbUse
				(Printf 0 4 objDesc)
			)
			(verbSmell
				(switch (Random 0 2)
					(0 (Print 0 5))
					(1 (Print 0 6))
					(2 (Print 0 7))
				)
			)
			(verbTaste
				(Print 0 8)
			)
			(else
				(NoResponse)
			)
		)
	)
)

(instance FtrInit of Code
	(method (doit theObj)
		(if (== (theObj sightAngle?) ftrDefault)
			(theObj sightAngle: 90)
		)
		(if (== (theObj actions?) ftrDefault)
			(theObj actions: 0)
		)
	)
)

(instance gcWindow of BorderWindow
	(method (open &tmp temp0 theBevelWid t l r b theMaps thePri i [str 15] [len 4])
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
		(= b (+ (= t (+ 25 (CelHigh 947 0 1))) 30))
		(= r
			(+
				(= l (+ 10 (CelWide 947 1 1)))
				(-
					(+ 151 (CelWide 947 0 1))
					(+ 10 (CelWide 947 1 1) 6)
				)
			)
		)
		(= thePri 15)
		(= theBevelWid 3)
		(= theMaps 3)
		(Graph GFillRect t l (+ b 1) (+ r 1) theMaps 0 thePri)
		(-= t theBevelWid)
		(-= l theBevelWid)
		(+= r theBevelWid)
		(+= b theBevelWid)
		(Graph GFillRect t l (+ t theBevelWid) r theMaps 7 thePri)
		(Graph GFillRect (- b theBevelWid) l b r theMaps 8 thePri)
		(for ((= i 0)) (< i theBevelWid) ((++ i))
			(Graph GDrawLine (+ t i) (+ l i) (- b (+ i 1)) (+ l i) 7 -1 -1)
			(Graph GDrawLine (+ t i) (- r (+ i 1)) (- b (+ i 1)) (- r (+ i 1)) 7 -1 -1)
		)
		(Graph GShowBits t l (+ b 1) (+ r 1) 1)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
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
		message verbHelp
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
		signal (| VICON HIDEBAR FIXED_POSN RELVERIFY IMMEDIATE)
		helpStr {Exits this menu.}
		lowlightColor 8
	)
)

(instance aboutCode of Code
	(method (doit)
		(Print 0 12)
	)
)
