;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh) (include "0.shm")
(use Main)
(use AnmScrwn)
(use IconBar)
(use Tutorial)
(use Actor)
(use System)

;Intentionally not commented out so that the script won't compile
EO:
;This script has an issue where bringing up the control panel then exiting it
;causes the icon bar to stop working. We'd better find a way to fix this...

(public
	SQIconbar 0
	SQIconItem 1
)

(class SQIconItem of IconItem
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle ftrDefault
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal RELVERIFY
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		type userEvent
		message -1
		modifiers $0000
		mainView 0
		mainLoop 0
		mainCel 0
		maskView 0
		maskLoop 0
		maskCel 0
		cursorView -1
		cursorLoop -1
		cursorCel -1
		highlightColor 0
		lowlightColor 0
		helpVerb 0
		object 0
		selector 0
	)
	
	(method (select param1 &tmp newEvent temp1 theGameScript)
		(return
			(cond 
				((& signal DISABLED) 0)
				((and argc param1 (& signal notUpd))
					(= cel (= temp1 1))
					(UpdateScreenItem self)
					(FrameOut)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(newEvent localize: plane)
						(cond 
							((self onMe: newEvent)
								(if (not temp1)
									(= cel (= temp1 1))
									(UpdateScreenItem self)
									(FrameOut)
								)
							)
							(temp1
								(= cel (= temp1 0))
								(inventory highlightedIcon: 0)
								(UpdateScreenItem self)
								(FrameOut)
							)
						)
						(newEvent dispose:)
					)
					(newEvent dispose:)
					(if
						(and
							(= theGameScript (theGame script?))
							(theGameScript isKindOf: Tutorial)
						)
						(cond 
							(
								(and
									(== (theGameScript nextItem?) self)
									(!=
										(theGameScript nextAction?)
										((theIconBar helpIconItem?) message?)
									)
								)
								(theGameScript cue:)
							)
							((not temp1) (return 0))
							(else (theGameScript report:) (return 0))
						)
					)
					temp1
				)
				(else
					(if
						(and
							(= theGameScript (theGame script?))
							(theGameScript isKindOf: Tutorial)
						)
						(if
							(and
								(== (theGameScript nextItem?) self)
								(!=
									(theGameScript nextAction?)
									((theIconBar helpIconItem?) message?)
								)
							)
							(theGameScript cue:)
						else
							(theGameScript report:)
							(return 0)
						)
					)
					1
				)
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal IB_ACTIVE)) (return))
		(= cel (* 1 (if argc param1 else 0)))
		(UpdateScreenItem self)
	)
)

(class SQIconbar of IconBar
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		underBits 0
		oldMouseX 0
		oldMouseY 0
		curIcon 0
		highlightedIcon 0
		prevIcon 0
		curInvIcon 0
		useIconItem 0
		helpIconItem 0
		walkIconItem 0
		plane 0
		state OPENIFONME
		y 0
		iconCast 0
		iconSettings 0
	)
	
	(method (init)
		((= theIconBar self)
			add:
				walkIcon0
				lookIcon1
				doIcon2
				talkIcon3
				invIcon4
				helpIcon5
				controlIcon6
				useIcon7
			curIcon: walkIcon0
			walkIconItem: walkIcon0
			helpIconItem: helpIcon5
			useIconItem: useIcon7
			state: (| (SQIconbar state?) (| NOCLICKHELP RELVERIFY))
		)
		(useIcon7 signal: (| (useIcon7 signal?) DISABLED))
		(super init:)
		(= iconCast (Cast new:))
		(plane
			addCast: iconCast
			setBitmap: 951 9 0
			priority: 10
			setRect: 0 139 320 200
		)
		(curInvView init: iconCast hide:)
		(= showTitle
			(textScroller
				init: scrollBar scrollBarUpIcon scrollBarDownIcon
				yourself:
			)
		)
	)
	;EO: Can somebody translate this to SCI? I think it's causing the icon bar to stop working
	;after bringing up the control panel.
	(method (doit &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(asm
code_0a39:
			pTos     state
			ldi      32
			and     
			bnt      code_0c1f
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp0
			bnt      code_0c1f
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			sat      temp1
			pushi    #message
			pushi    0
			lat      temp0
			send     4
			sat      temp2
			pushi    #modifiers
			pushi    0
			lat      temp0
			send     4
			sat      temp3
			lsg      tickOffset
			pushi    0
			callk    GetTime,  0
			add     
			sag      gameTime
			pushi    0
			callk    FrameOut,  0
			lag      cuees
			bnt      code_0a90
			pushi    #eachElementDo
			pushi    1
			pushi    69
			send     6
code_0a90:
			pushi    #script
			pushi    0
			lag      theGame
			send     4
			sat      temp4
			bnt      code_0ab3
			pushi    #isKindOf
			pushi    1
			class    Tutorial
			push    
			lat      temp4
			send     6
			bnt      code_0ab3
			pushi    #doit
			pushi    0
			lat      temp4
			send     4
code_0ab3:
			lst      temp1
			ldi      32
			eq?     
			bnt      code_0ae4
			ldi      4
			sat      temp1
			lst      temp3
			ldi      3
			and     
			bnt      code_0ac9
			ldi      27
			jmp      code_0acb
code_0ac9:
			ldi      13
code_0acb:
			sat      temp2
			ldi      0
			sat      temp3
			pushi    #type
			pushi    1
			lst      temp1
			pushi    49
			pushi    1
			lst      temp2
			pushi    72
			pushi    1
			push    
			lat      temp0
			send     18
code_0ae4:
			pushi    #localize
			pushi    1
			pTos     plane
			lat      temp0
			send     6
			lst      temp1
			ldi      1
			eq?     
			bt       code_0b05
			lst      temp1
			ldi      4
			eq?     
			bnt      code_0b2c
			lst      temp2
			ldi      13
			eq?     
			bnt      code_0b2c
code_0b05:
			pToa     helpIconItem
			bnt      code_0b2c
			pushi    #signal
			pushi    0
			send     4
			push    
			ldi      16
			and     
			bnt      code_0b2c
			pushi    #type
			pushi    1
			pushi    24576
			pushi    49
			pushi    1
			pushi    #message
			pushi    0
			pToa     helpIconItem
			send     4
			push    
			lat      temp0
			send     12
code_0b2c:
			pushi    1
			lst      temp0
			callk    MapKeyToDir,  2
			pushi    #canInput
			pushi    0
			lag      user
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_0b9a
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      17
			ge?     
			bnt      code_0b7d
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      267
			lt?     
			bnt      code_0b7d
			lsg      theCursor
			lag      normalCursor
			ne?     
			bnt      code_0b9a
			pushi    #highlight
			pushi    1
			pTos     curIcon
			self     6
			pushi    #setCursor
			pushi    1
			lsg      normalCursor
			lag      theGame
			send     6
			jmp      code_0b9a
code_0b7d:
			lsg      theCursor
			pushi    #getCursor
			pushi    0
			self     4
			ne?     
			bnt      code_0b9a
			pushi    #setCursor
			pushi    1
			pushi    #getCursor
			pushi    0
			self     4
			push    
			lag      theGame
			send     6
code_0b9a:
			pushi    #dispatchEvent
			pushi    1
			lst      temp0
			self     6
			bnt      code_0ba6
code_0ba6:
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      showTitle
			send     6
			lag      autoRobot
			bnt      code_0bbb
			pushi    #doit
			pushi    0
			send     4
code_0bbb:
			pushi    #doit
			pushi    0
			lag      cast
			send     4
			pushi    0
			callk    FrameOut,  0
			lag      doMotionCue
			bnt      code_0bdc
			ldi      0
			sag      doMotionCue
			pushi    #eachElementDo
			pushi    1
			pushi    255
			lag      cast
			send     6
code_0bdc:
			pushi    #script
			pushi    0
			lag      theGame
			send     4
			bnt      code_0bf6
			pushi    #doit
			pushi    0
			pushi    #script
			pushi    0
			lag      theGame
			send     4
			send     4
code_0bf6:
			pushi    #eachElementDo
			pushi    1
			pushi    69
			lag      regions
			send     6
			pushi    #doit
			pushi    0
			lag      theDoits
			send     4
			lsg      newRoomNum
			lag      curRoomNum
			ne?     
			bnt      code_0a39
			pushi    #newRoom
			pushi    1
			lsg      newRoomNum
			lag      theGame
			send     6
			jmp      code_0a39
code_0c1f:
			ret     
		)
	)
	
	(method (dispose)
		(super dispose:)
		(showTitle dispose:)
	)
	
	(method (show &tmp temp0 theY node temp3)
		(= state (| state IB_ACTIVE))
		(plane priority: 10)
		(UpdatePlane plane)
		(= temp0 0)
		(= theY y)
		(= node (List LFirstNode elements))
		(while node
			(= nextNode (List LNextNode node))
			(if
			(<= ((= temp3 (List LNodeValue node)) nsRight?) 0)
				(temp3 show: temp0 theY)
				(= temp0 (temp3 nsRight?))
			else
				(temp3 show:)
			)
			(= node nextNode)
		)
		(if curInvIcon (self showInvItem:))
		(self highlight: curIcon)
		(showTitle show:)
	)
	
	(method (handleEvent event &tmp temp0 eventType temp2 temp3 theTheCursor theCurIcon theCurInvIcon)
		(= eventType (event type?))
		(cond 
			((& state DISABLED))
			(
				(and
					(not eventType)
					(& state OPENIFONME)
					(self shouldOpen: event)
					(not (= temp0 0))
				)
				(= theTheCursor theCursor)
				(= theCurIcon curIcon)
				(= theCurInvIcon curInvIcon)
				(self show:)
				(self doit:)
				(if
				(and (< (event y?) 17) (>= (event x?) 267))
					(= temp3
						(if (or (user canControl:) (user canInput:))
							(self getCursor:)
						else
							waitCursor
						)
					)
				)
				(if
				(and (< (event y?) 17) (>= (event x?) 267))
					(theGame setCursor: temp3 1)
				)
				(self hide:)
			)
			((& eventType mouseDown)
				(cond 
					((& (event modifiers?) shiftDown)
						(self advanceCurIcon: show: highlight: curIcon hide:)
						(event claimed: 1)
					)
					((& (event modifiers?) ctrlDown)
						(if (user canControl:)
							(self swapCurIcon: show: highlight: curIcon hide:)
						)
						(event claimed: TRUE)
					)
					(curIcon
						(event
							type: (curIcon type?)
							message:
								(if (== curIcon useIconItem)
									(curInvIcon message?)
								else
									(curIcon message?)
								)
						)
					)
				)
			)
		)
	)
	
	(method (hide param1 &tmp node temp1)
		(if (& state IB_ACTIVE)
			(= state (& state $ffdf))
			(if (and argc param1) (sounds pause: 0))
			(self highlight: curIcon)
			(= node (List LFirstNode elements))
			(while node
				(= nextNode (List LNextNode node))
				(= temp1 (List LNodeValue node))
				(temp1 signal: (& (temp1 signal?) $ffdf))
				(= node nextNode)
			)
			(if
				(and
					(not (& state NOCLICKHELP))
					helpIconItem
					(& (helpIconItem signal?) TRANSLATOR)
				)
				(helpIconItem signal: (& (helpIconItem signal?) $ffef))
			)
			(if (and argc param1)
				(plane priority: -1)
				(showTitle hide:)
			)
			(UpdatePlane plane)
		)
	)
	
	(method (advance &tmp temp0 temp1)
		(= temp1 1)
		(while (<= temp1 size)
			(breakif
				(not
					(&
						((= temp0
							(self
								at: (mod (+ temp1 (self indexOf: highlightedIcon)) size)
							)
						)
							signal?
						)
						DISABLED
					)
				)
			)
			(= temp1 (mod (+ temp1 1) size))
		)
		(self highlight: temp0)
	)
	
	(method (retreat &tmp temp0 temp1)
		(= temp1 1)
		(while (<= temp1 size)
			(breakif
				(not
					(&
						((= temp0
							(self
								at: (mod (- (self indexOf: highlightedIcon) temp1) size)
							)
						)
							signal?
						)
						DISABLED
					)
				)
			)
			(= temp1 (mod (+ temp1 1) size))
		)
		(self highlight: temp0)
	)
	
	(method (highlight theHighlightedIcon &tmp temp0)
		(if (not (& (theHighlightedIcon signal?) DISABLED))
			(if highlightedIcon (highlightedIcon highlight: 0))
			((= highlightedIcon theHighlightedIcon) highlight: 1)
		)
	)
	
	(method (swapCurIcon &tmp temp0)
		(cond 
			((& state DISABLED) (return))
			((theIconBar contains: exitIcon8) (return))
			(
				(and
					(!= curIcon (= temp0 (List LNodeValue (self first:))))
					(not (& (temp0 signal?) DISABLED))
				)
				(= prevIcon curIcon)
				(= curIcon (List LNodeValue (self first:)))
			)
			(
			(and prevIcon (not (& (prevIcon signal?) DISABLED))) (= curIcon prevIcon))
		)
		(theGame setCursor: (self getCursor:))
	)
	
	(method (disableIcon param1 &tmp temp0 temp1)
		(if argc
			(= temp0 0)
			(while (< temp0 argc)
				(= temp1 [param1 temp0])
				(temp1 signal: (| (temp1 signal?) DISABLED))
				(cond 
					((== temp1 curIcon) (self advanceCurIcon:))
					((== temp1 highlightedIcon) (self advance:))
				)
				(++ temp0)
			)
			(self show:)
		else
			(= state (| state DISABLED))
		)
	)
	
	(method (enableIcon param1 &tmp temp0 temp1)
		(if argc
			(= temp0 0)
			(while (< temp0 argc)
				(= temp1 [param1 temp0])
				(temp1 signal: (& (temp1 signal?) $fffb))
				(++ temp0)
			)
		else
			(= state (& state $fffb))
		)
		(self show:)
	)
	
	(method (shouldOpen param1)
		(if (== (param1 type?) 0) (plane onMe: param1))
	)
	
	(method (shouldClose param1)
		(param1 globalize:)
		(return
			(if (== (param1 type?) 0)
				(not (plane onMe: param1))
			else
				0
			)
		)
	)
	
	(method (showInvItem param1 &tmp temp0 temp1)
		(= temp0
			(+
				(/
					(-
						(- (useIconItem nsRight?) (useIconItem nsLeft?))
						(CelWide
							(curInvIcon view?)
							(curInvIcon loop?)
							(curInvIcon cel?)
						)
					)
					2
				)
				(useIconItem nsLeft?)
			)
		)
		(= temp1
			(+
				y
				(/
					(-
						(- (useIconItem nsBottom?) (useIconItem nsTop?))
						(CelHigh
							(curInvIcon view?)
							(curInvIcon loop?)
							(curInvIcon cel?)
						)
					)
					2
				)
				(useIconItem nsTop?)
			)
		)
		(curInvView
			view: (curInvIcon mainView?)
			loop: (curInvIcon mainLoop?)
			cel:
				(if (and argc param1)
					(+ (curInvIcon mainCel?) 1)
				else
					(curInvIcon mainCel?)
				)
			x: temp0
			y: temp1
			show:
		)
		(UpdateScreenItem curInvView)
	)
	
	(method (clearInvItem)
		(curInvView view: 951 loop: 8 cel: 0 x: 268 y: 16 show:)
		(UpdateScreenItem curInvView)
	)
	
	(method (setupExit param1)
		(if param1
			(theIconBar
				delete: walkIcon0
				addToFront: exitIcon8
				highlightedIcon: 0
			)
			(DeleteScreenItem walkIcon0)
			(exitIcon8 init: self)
		else
			(theIconBar
				delete: exitIcon8
				addToFront: walkIcon0
				highlightedIcon: 0
			)
			(DeleteScreenItem exitIcon8)
			(walkIcon0 init: self)
			(theIconBar enable: 0)
		)
	)
	
	(method (updateSettings &tmp temp0)
		(= temp0 (= iconSettings 0))
		(while (<= temp0 7)
			(if (& ((theIconBar at: temp0) signal?) DISABLED)
				(= iconSettings (| iconSettings (>> FIXED_POSN temp0)))
			)
			(++ temp0)
		)
	)
	
	(method (returnFirstAvail param1 &tmp temp0)
		(if (not argc) (= param1 0))
		(= temp0 param1)
		(while (<= temp0 7)
			(if (not (& iconSettings (>> FIXED_POSN temp0)))
				(theIconBar at: temp0)
				(return)
			)
			(++ temp0)
		)
	)
)

(instance talkerSet of Cast
	(properties)
)

(instance walkIcon0 of SQIconItem
	(properties
		noun N_WALK
		x 4
		y 5
		type (| walkEvent userEvent)
		message V_WALK
		mainView 951
		maskView 951
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: theWalkCursor)
		(super init: &rest)
	)
)

(instance lookIcon1 of SQIconItem
	(properties
		noun N_LOOK
		x 42
		y 5
		message V_LOOK
		mainView 951
		mainLoop 1
		maskView 951
		maskLoop 1
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: theLookCursor)
		(super init: &rest)
	)
)

(instance doIcon2 of SQIconItem
	(properties
		noun N_DO
		x 79
		y 5
		message V_DO
		mainView 951
		mainLoop 3
		maskView 951
		maskLoop 3
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: theDoCursor)
		(super init: &rest)
	)
)

(instance talkIcon3 of SQIconItem
	(properties
		noun N_TALK
		x 117
		y 5
		message V_TALK
		mainView 951
		mainLoop 2
		maskView 951
		maskLoop 2
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: theTalkCursor)
		(super init: &rest)
	)
)

(instance invIcon4 of SQIconItem
	(properties
		noun N_INVENTORY
		x 154
		y 5
		signal (| RELVERIFY IMMEDIATE)
		message 0
		mainView 951
		mainLoop 4
		maskView 951
		maskLoop 4
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: normalCursor)
		(super init: &rest)
	)
	
	(method (select &tmp [temp0 5])
		(return
			(if (super select: &rest)
				(if (not (inventory ownedBy: ego))
					(inventory carryingNothing:)
					(theGame doit:)
					(return TRUE)
				)
				(= gGSQIconbarCurIcon (theIconBar curIcon?))
				(if
					(and
						(theIconBar contains: walkIcon0)
						(not (& (walkIcon0 signal?) DISABLED))
					)
					(Bset WALK_ENABLED)
					(walkIcon0 signal: (| (walkIcon0 signal?) DISABLED) show:)
				)
				(if (not (& (lookIcon1 signal?) DISABLED))
					(Bset LOOK_ENABLED)
					(lookIcon1 signal: (| (lookIcon1 signal?) DISABLED) show:)
				)
				(if (not (& (doIcon2 signal?) DISABLED))
					(Bset DO_ENABLED)
					(doIcon2 signal: (| (doIcon2 signal?) DISABLED) show:)
				)
				(if (not (& (talkIcon3 signal?) DISABLED))
					(Bset TALK_ENABLED)
					(talkIcon3 signal: (| (talkIcon3 signal?) DISABLED) show:)
				)
				(if (not (& (helpIcon5 signal?) DISABLED))
					(Bset HELP_ENABLED)
					(helpIcon5 signal: (| (helpIcon5 signal?) DISABLED) show:)
				)
				(if (not (& (controlIcon6 signal?) DISABLED))
					(Bset CONTROL_ENABLED)
					(controlIcon6
						signal: (| (controlIcon6 signal?) DISABLED)
						show:
					)
				)
				(if
					(and
						(theIconBar contains: exitIcon8)
						(not (& (exitIcon8 signal?) DISABLED))
					)
					(Bset EXIT_ENABLED)
					(exitIcon8 signal: (| (exitIcon8 signal?) DISABLED) show:)
				)
				(Bset INVENTORY_ACTIVE)
				(inventory showSelf:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance helpIcon5 of SQIconItem
	(properties
		noun N_ICONHELP
		x 192
		y 5
		signal (| RELVERIFY IMMEDIATE)
		message 0
		mainView 951
		mainLoop 5
		maskView 951
		maskLoop 5
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: theHelpCursor)
		(super init: &rest)
	)
)

(instance controlIcon6 of SQIconItem
	(properties
		noun N_CONTROL
		x 229
		y 5
		signal (| RELVERIFY IMMEDIATE)
		message 0
		mainView 951
		mainLoop 6
		maskView 951
		maskLoop 6
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: normalCursor)
		(super init: &rest)
	)
	
	(method (select &tmp temp0)
		(return
			(if (super select: &rest)
				(theGame showControls:)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance useIcon7 of SQIconItem
	(properties
		noun N_USEITEM
		x 268
		y 16
		message 0
		mainView 951
		mainLoop 8
		maskView 951
		maskLoop 8
		helpVerb V_HELP
	)
	
	(method (init)
		(self setCursor: normalCursor)
		(super init: &rest)
	)
	
	(method (select &tmp temp0)
		(return
			(if (theIconBar curInvIcon?)
				(curInvView cel: 1)
				(UpdateScreenItem curInvView)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
	
	(method (highlight param1)
		(if (not (& signal IB_ACTIVE)) (return))
		(if
		(== (theIconBar curIcon?) (theIconBar useIconItem?))
			(curInvView cel: (* 1 (if argc param1 else 0)))
			(UpdateScreenItem curInvView)
		else
			(return)
		)
	)
)

(instance exitIcon8 of SQIconItem
	(properties
		noun N_EXITWINDOW
		x 4
		y 5
		signal (| RELVERIFY IMMEDIATE)
		message V_EXIT
		mainView 951
		mainLoop 7
		maskView 951
		maskLoop 7
		maskCel 2
		helpVerb V_HELP
	)
	
	(method (select)
		(theIconBar setupExit: 0)
		(if (== curRoomNum 460)
			(= selectedRoom 0)
			(curRoom newRoom: prevRoomNum)
		else
			((curRoom inset?) dispose:)
		)
	)
)

(instance scrollBarUpIcon of IconItem
	(properties
		priority 15
		fixPriority 1
		mainView 9511
		mainLoop 1
	)
)

(instance scrollBarDownIcon of IconItem
	(properties
		y 37
		priority 15
		fixPriority 1
		mainView 9511
		mainLoop 2
	)
)

(instance scrollBar of WindowScrollBar
	(properties
		x 1
		y 5
		priority 14
		fixPriority 1
		view 9511
		thumbView 9511
		thumbCel 1
		minPosn 5
		maxPosn 34
	)
)

(instance textScroller of ScrollerWindow
	(properties
		fore 82
		font 70
		borderColor 0
		nsLeft 6
		nsTop 157
		nsRight 254
		nsBottom 197
		maxItems 50
	)
	
	(method (init)
		(super init: &rest)
		(UpdateScreenItem
			((scroller thumb?) setPri: 15 yourself:)
		)
	)
)

(instance curInvView of View
	(properties
		view 951
		loop 8
	)
)
