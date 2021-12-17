;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64892)
(include sci.sh)
(use Main)
(use TimePauser)
(use DText)
(use Plane)
(use Print)
(use Game)
(use User)
(use System)


(class NewUser of User
	(properties
		scratch 0
		alterEgo 0
		input 0
		controls 0
		prevDir 0
		x -1
		y -1
		mapKeyToDir 0
		curEvent 0
	)
	
	(method (doit)
		(curEvent new:)
		(gOEventHandler handleEvent: curEvent)
	)
)

(class DoitChangedGame of Game
	(properties
		scratch 0
		script 0
		printLang 1
		_detailLevel 3
		panelObj 0
		panelSelector 0
		handsOffCode 0
		handsOnCode 0
	)
	
	(method (doit &tmp thePanelObj thePanelSelector)
		(FrameOut)
		(if panelObj
			(= thePanelObj panelObj)
			(= thePanelSelector panelSelector)
			(= panelObj (= panelSelector 0))
			(Eval thePanelObj thePanelSelector)
		)
		(if (!= newRoomNum curRoomNum)
			(self newRoom: newRoomNum)
		)
		(= gameTime (+ tickOffset (GetTime)))
		(sounds eachElementDo: #check)
		(timers eachElementDo: #doit)
		(if autoRobot (autoRobot doit:))
		(if (and curRoom (curRoom inset?))
			(cast doit:)
		else
			(planes eachElementDo: #doit)
		)
		(if doMotionCue
			(= doMotionCue 0)
			(cast eachElementDo: #motionCue)
		)
		(if (and cuees (not (TimePauser bIsPaused?)))
			(cuees eachElementDo: #doit)
			(if (cuees isEmpty:) (cuees dispose:) (= cuees 0))
		)
		(if (and script (not (TimePauser bIsPaused?)))
			(script doit:)
		)
		(if (not (TimePauser bIsPaused?))
			(regions eachElementDo: #doit)
		)
		(if (not (TimePauser bIsPaused?)) (theDoits doit:))
		(if (== newRoomNum curRoomNum) (user doit:))
		(timers eachElementDo: #delete)
	)
)

(class NewHandlerList of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
	)
	
	(method (handleEvent event param2 param3 &tmp temp0 temp1 temp2 temp3)
		(asm
			_line_   192
			_file_   {filename}
			_line_   193
			_line_   194
			lsp      argc
			ldi      1
			gt?     
			bnt      code_03fa
			_line_   195
			lap      param2
			sat      temp3
			jmp      code_0404
code_03fa:
			_line_   196
			_line_   197
			ldi      0
			sat      temp3
code_0404:
			_line_   199
			lsp      argc
			ldi      2
			gt?     
			bnt      code_0417
			_line_   200
			lap      param3
			sat      temp2
			jmp      code_0421
code_0417:
			_line_   201
			_line_   202
			ldi      0
			sat      temp2
code_0421:
			_line_   206
			lat      temp3
			not     
			bnt      code_0435
			_line_   207
			pushi    #globalize
			pushi    0
			lap      event
			send     4
code_0435:
			_line_   209
			pushi    2
			pushi    3
			pTos     elements
			callk    KList,  4
			sat      temp0
code_0443:
			_line_   210
			lat      temp0
			bnt      code_0546
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_0546
			_line_   211
			_line_   212
			pushi    2
			pushi    6
			lst      temp0
			callk    KList,  4
			aTop     nextNode
			_line_   213
			pushi    2
			pushi    8
			lst      temp0
			callk    KList,  4
			sat      temp1
			_line_   216
			lat      temp3
			bnt      code_04cf
			_line_   217
			pushi    #respondsTo
			pushi    1
			pushi    0
			lat      temp1
			send     6
			bnt      code_04c0
			_line_   218
			pushi    #isEnabled
			pushi    0
			pushi    #plane
			pushi    0
			lat      temp1
			send     4
			send     4
			bnt      code_04b5
			_line_   219
			pushi    #localize
			pushi    1
			pushi    #plane
			pushi    0
			lat      temp1
			send     4
			push    
			lap      event
			send     6
			jmp      code_04cf
code_04b5:
			_line_   220
			_line_   221
			jmp      code_053f
			jmp      code_04cf
code_04c0:
			_line_   223
			_line_   224
			pushi    #globalize
			pushi    0
			lap      event
			send     4
code_04cf:
			_line_   229
			lat      temp2
			bnt      code_0515
			_line_   230
			pushi    #respondsTo
			pushi    1
			pushi    270
			lat      temp1
			send     6
			bnt      code_0515
			pushi    #respondsTo
			pushi    1
			pushi    292
			lat      temp1
			send     6
			bnt      code_0515
			_line_   231
			pushi    #onMe
			pushi    1
			lsp      event
			lat      temp1
			send     6
			bnt      code_0515
			_line_   232
			pushi    #doToolHelp
			pushi    2
			lst      temp1
			lsp      event
			lag      gOEventHandler
			send     8
code_0515:
			_line_   238
			pushi    #handleEvent
			pushi    1
			lsp      event
			lat      temp1
			send     6
			bnt      code_053f
			_line_   239
			lat      temp2
			bnt      code_053a
			_line_   240
			pushi    #changeCursor
			pushi    1
			lst      temp1
			lag      gOEventHandler
			send     6
code_053a:
			_line_   242
			jmp      code_0546
code_053f:
			pToa     nextNode
			sat      temp0
			jmp      code_0443
code_0546:
			_line_   247
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			ret     
		)
	)
)

(class EventCode of Obj
	(properties
		scratch 0
	)
	
	(method (handleEvent)
	)
)

(class NewEventHandler of Obj
	(properties
		scratch 0
		nLastCursX 0
		nLastCursY 0
		nToolHelpTime 0
		oCurToolHelp 0
		oCurHelpItem 0
		bTimerStarted 0
		bSortedFeatures 1
		oHandsOffList 0
		oEventHogList 0
		oGlobalHandlers 0
		oKeyHandlers 0
		oDefaultHandlers 0
		oUnderMouseList 0
	)
	
	(method (dispose)
		(if oHandsOffList
			(oHandsOffList release:)
			(oHandsOffList dispose:)
		)
		(if oEventHogList
			(oEventHogList release:)
			(oEventHogList dispose:)
		)
		(if oGlobalHandlers (oGlobalHandlers dispose:))
		(if oDefaultHandlers (oDefaultHandlers dispose:))
		(if oKeyHandlers (oKeyHandlers dispose:))
		(if oUnderMouseList (oUnderMouseList dispose:))
	)
	
	(method (registerHandsOffActive param1)
		(if (not oHandsOffList)
			(= oHandsOffList (NewHandlerList new:))
		)
		(oHandsOffList add: param1)
	)
	
	(method (unregisterHandsOffActive param1)
		(if oHandsOffList (oHandsOffList delete: param1))
	)
	
	(method (registerEventHog param1)
		(if (not oEventHogList)
			(= oEventHogList (NewHandlerList new:))
		)
		(oEventHogList addToFront: param1)
	)
	
	(method (unregisterEventHog param1)
		(if oEventHogList (oEventHogList delete: param1))
	)
	
	(method (killAllEventHogs &tmp temp0 temp1)
		(while (and oEventHogList (oEventHogList size:))
			(if
			(== (= temp0 (KList 8 (oEventHogList first:))) temp1)
				(MonoOut
					{Warning: %s does not have effective stopHogging method.}
					(temp0 name?)
				)
				(return)
			)
			(if (temp0 respondsTo: #stopHogging)
				(temp0 stopHogging:)
			else
				(MonoOut
					{Warning: %s does not have stopHogging method.}
					(temp0 name?)
				)
				(return)
			)
			(= temp1 temp0)
		)
	)
	
	(method (registerGlobalHandler param1)
		(if (not oGlobalHandlers)
			(= oGlobalHandlers (NewHandlerList new:))
		)
		(oGlobalHandlers addToFront: param1)
	)
	
	(method (unregisterGlobalHandler param1)
		(if oGlobalHandlers (oGlobalHandlers delete: param1))
	)
	
	(method (registerKeyHandler param1)
		(if (not oKeyHandlers)
			(= oKeyHandlers (NewHandlerList new:))
		)
		(oKeyHandlers addToFront: param1)
	)
	
	(method (unregisterKeyHandler param1)
		(if oKeyHandlers (oKeyHandlers delete: param1))
	)
	
	(method (registerDefaultHandler param1)
		(if (not oDefaultHandlers)
			(= oDefaultHandlers (NewHandlerList new:))
		)
		(oDefaultHandlers addToFront: param1)
	)
	
	(method (unregisterDefaultHandler param1)
		(if oDefaultHandlers (oDefaultHandlers delete: param1))
	)
	
	(method (killToolHelp)
		(if oCurHelpItem
			(oCurToolHelp dispose:)
			(= bTimerStarted (= oCurHelpItem (= oCurToolHelp 0)))
		)
	)
	
	(method (notifyDispose param1)
		(if (== param1 oCurHelpItem) (self killToolHelp:))
	)
	
	(method (handleEvent event &tmp planesFirst temp1 temp2 temp3 temp4)
		(= mouseX (event x?))
		(= mouseY (event y?))
		(if
			(and
				(not (prints isEmpty:))
				(not (prints allTrue: #isModeless 2))
			)
			(prints
				eachElementDo: #doit
				firstTrue: #handleEvent event
			)
			(return 1)
		)
		(if (event claimed?) (return 1))
		(self doToolHelp: 0 event)
		(if
			(and
				oGlobalHandlers
				(oGlobalHandlers handleEvent: event 0 0)
			)
			(return 1)
		)
		(if
			(and
				(& (event type?) $000c)
				oKeyHandlers
				(oKeyHandlers handleEvent: event 0 0)
			)
			(return 1)
		)
		(if (& (event type?) $000c) (return 0))
		(if
			(and
				oEventHogList
				(oEventHogList handleEvent: event 1 0)
			)
			(return 1)
		)
		(if (not (user canControl:))
			(if
				(and
					oHandsOffList
					(oHandsOffList handleEvent: event 1 1)
				)
				(event claimed: 1)
			)
			(if
				(and
					(not (talkers isEmpty:))
					(not (TimePauser bIsPaused?))
				)
				(talkers
					eachElementDo: #doit
					firstTrue: #handleEvent event
				)
			)
			(if (not (event claimed?)) (self changeCursor: 0))
			(return (event claimed?))
		)
		(if (and curRoom (curRoom inset?))
			(event localize: (cast plane?))
			(if (= temp3 (cast firstTrue: #onMe event))
				(self doToolHelp: temp3 event)
			)
			(if (= temp3 (features firstTrue: #onMe event))
				(self doToolHelp: temp3 event)
			)
			(self doToolHelp: 0 event)
			(if (= temp3 (cast firstTrue: #handleEvent event))
				(self changeCursor: temp3)
				(return 1)
			)
			(if
			(= temp3 (features firstTrue: #handleEvent event))
				(self changeCursor: temp3)
				(return 1)
			)
			(self changeCursor: 0)
			(return 0)
		)
		(= planesFirst (planes first:))
		(while planesFirst
			(= temp1 (KList 8 planesFirst))
			(if (self handlePlaneEvent: temp1 event) (return 1))
			(= planesFirst (planes next: planesFirst))
		)
		(self changeCursor: 0)
		(if
			(and
				oDefaultHandlers
				(oDefaultHandlers handleEvent: event 0 0)
			)
			(return 1)
		)
		(return 0)
	)
	
	(method (handlePlaneEvent param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(asm
			_line_   571
			_file_   {filename}
			_line_   573
			pushi    #globalize
			pushi    0
			lap      param2
			send     4
			_line_   574
			pushi    #onMe
			pushi    1
			lsp      param2
			lap      param1
			send     6
			not     
			bnt      code_0b9e
			_line_   575
			ldi      0
			ret     
code_0b9e:
			_line_   579
			pushi    #priority
			pushi    0
			lap      param1
			send     4
			push    
			ldi      65535
			eq?     
			bt       code_0bbe
			pushi    #priority
			pushi    0
			lap      param1
			send     4
			push    
			ldi      510
			eq?     
			bnt      code_0bc4
code_0bbe:
			_line_   580
			ldi      0
			ret     
code_0bc4:
			_line_   586
			pushi    #localize
			pushi    1
			lsp      param1
			lap      param2
			send     6
			_line_   587
			pushi    #casts
			pushi    0
			lap      param1
			send     4
			sat      temp2
			_line_   589
			pToa     oUnderMouseList
			bnt      code_0c04
			_line_   590
			pushi    #release
			pushi    0
			send     4
			_line_   591
			pushi    #dispose
			pushi    0
			pToa     oUnderMouseList
			send     4
			_line_   592
			ldi      0
			aTop     oUnderMouseList
code_0c04:
			_line_   595
			pushi    #first
			pushi    0
			lat      temp2
			send     4
			sat      temp4
code_0c12:
			_line_   596
			lat      temp4
			bnt      code_0cc8
			_line_   597
			_line_   598
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp1
			_line_   600
			pushi    #first
			pushi    0
			send     4
			sat      temp3
code_0c36:
			_line_   601
			lat      temp3
			bnt      code_0cb9
			_line_   602
			_line_   603
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp0
			_line_   605
			pushi    #onMe
			pushi    1
			lsp      param2
			send     6
			bnt      code_0c88
			_line_   606
			pToa     bSortedFeatures
			bnt      code_0c77
			_line_   607
			pushi    #addUnderMouse
			pushi    1
			lst      temp0
			self     6
			_line_   608
			jmp      code_0caa
			jmp      code_0c88
code_0c77:
			_line_   609
			_line_   610
			pushi    #doToolHelp
			pushi    2
			lst      temp0
			lsp      param2
			self     8
code_0c88:
			_line_   614
			pushi    #handleEvent
			pushi    1
			lsp      param2
			lat      temp0
			send     6
			bnt      code_0caa
			_line_   615
			pushi    #changeCursor
			pushi    1
			lst      temp0
			self     6
			_line_   616
			ldi      1
			ret     
code_0caa:
			pushi    #next
			pushi    1
			lst      temp3
			lat      temp1
			send     6
			sat      temp3
			jmp      code_0c36
code_0cb9:
			pushi    #next
			pushi    1
			lst      temp4
			lat      temp2
			send     6
			sat      temp4
			jmp      code_0c12
code_0cc8:
			_line_   622
			pushi    #oMyFeatures
			pushi    0
			lap      param1
			send     4
			sat      temp5
			_line_   623
			pushi    #first
			pushi    0
			send     4
			sat      temp3
code_0ce2:
			_line_   624
			lat      temp3
			bnt      code_0d66
			_line_   625
			_line_   626
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp0
			_line_   628
			pushi    #onMe
			pushi    1
			lsp      param2
			send     6
			bnt      code_0d35
			_line_   629
			pToa     bSortedFeatures
			bnt      code_0d24
			_line_   630
			pushi    #addUnderMouse
			pushi    1
			lst      temp0
			self     6
			_line_   631
			jmp      code_0d57
			jmp      code_0d35
code_0d24:
			_line_   632
			_line_   633
			pushi    #doToolHelp
			pushi    2
			lst      temp0
			lsp      param2
			self     8
code_0d35:
			_line_   637
			pushi    #handleEvent
			pushi    1
			lsp      param2
			lat      temp0
			send     6
			bnt      code_0d57
			_line_   638
			pushi    #changeCursor
			pushi    1
			lst      temp0
			self     6
			_line_   639
			ldi      1
			ret     
code_0d57:
			pushi    #next
			pushi    1
			lst      temp3
			lat      temp5
			send     6
			sat      temp3
			jmp      code_0ce2
code_0d66:
			_line_   644
			pToa     bSortedFeatures
			bnt      code_0dd6
			pToa     oUnderMouseList
			bnt      code_0dd6
			_line_   645
			pushi    #first
			pushi    0
			pToa     oUnderMouseList
			send     4
			sat      temp3
			_line_   646
code_0d84:
			lat      temp3
			bnt      code_0dd6
			_line_   647
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp0
			_line_   648
			pushi    #doToolHelp
			pushi    2
			push    
			lsp      param2
			self     8
			_line_   649
			pushi    #handleEvent
			pushi    1
			lsp      param2
			lat      temp0
			send     6
			bnt      code_0dc5
			_line_   650
			pushi    #changeCursor
			pushi    1
			lst      temp0
			self     6
			_line_   651
			ldi      1
			ret     
code_0dc5:
			_line_   653
			pushi    #next
			pushi    1
			lst      temp3
			pToa     oUnderMouseList
			send     6
			sat      temp3
			jmp      code_0d84
code_0dd6:
			_line_   659
			ldi      0
			ret     
		)
	)
	
	(method (changeCursor param1 &tmp temp0 temp1)
		(if param1
			(if
			(and (= temp0 (param1 forceCursor:)) (!= temp0 -1))
				(if (!= theCursor temp0) (theGame setCursor: temp0))
			else
				(if (>= gVerb (global100 size:))
					(Prints {ERROR: no cursor for verb %d} gVerb)
					(= temp1 (global100 at: 0))
				else
					(= temp1 (global100 at: gVerb))
				)
				(if (!= theCursor normalCursor)
					(theGame setCursor: normalCursor)
				)
				(if (!= (theCursor view?) temp1)
					(theCursor setView: temp1)
				)
				(cond 
					((== temp0 -1) (if (!= (theCursor cel?) 0) (theCursor setCel: 0)))
					((!= (theCursor cel?) 1) (theCursor setCel: 1))
				)
			)
		else
			(if (>= gVerb (global100 size:))
				(Prints {ERROR: no cursor for verb %d} gVerb)
				(= temp1 (global100 at: 0))
			else
				(= temp1 (global100 at: gVerb))
			)
			(if (not (user canControl:))
				(if (!= theCursor waitCursor)
					(theGame setCursor: waitCursor)
				)
				(return)
			)
			(if (!= theCursor normalCursor)
				(theGame setCursor: normalCursor)
			)
			(if (!= (theCursor view?) temp1)
				(theCursor setView: temp1)
			)
			(if (theCursor cel?) (theCursor setCel: 0))
		)
	)
	
	(method (doToolHelp &tmp [temp0 5])
	)
	
	(method (addUnderMouse param1 &tmp oUnderMouseListFirst temp1 temp2)
		(if (not oUnderMouseList)
			(= oUnderMouseList (Set new:))
		)
		(= oUnderMouseListFirst (oUnderMouseList first:))
		(= temp1 0)
		(while oUnderMouseListFirst
			(= temp2 (KList 8 oUnderMouseListFirst))
			(if (> (param1 myPriority:) (temp2 myPriority:))
				(break)
			)
			(= temp1 temp2)
			(= oUnderMouseListFirst
				(oUnderMouseList next: oUnderMouseListFirst)
			)
		)
		(if temp1
			(oUnderMouseList addAfter: temp1 param1)
		else
			(oUnderMouseList addToFront: param1)
		)
	)
)

(class ToolHelpView of DText
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
		sightAngle 26505
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
		signal $0000
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
		type $0002
		key 0
		value 0
		object 0
		selector 0
		textLeft 0
		textTop 0
		textRight 0
		textBottom 0
		text 0
		mode 0
		fore 0
		back 64
		skip 255
		font 0
		borderColor 0
		dimmed 0
		rects 0
	)
)

(class ToolHelpPlane of Plane
	(properties
		scratch 0
		resX -1
		resY -1
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -2
		style $0000
		priority 510
		back 0
		bitmap 0
		casts 0
		mirrored 0
		oMyFeatures 0
		voMyHelp 0
	)
	
	(method (init param1 param2 param3 &tmp newCast temp1 temp2 temp3)
		(super init: &rest)
		(= newCast (Cast new:))
		(self addCast: newCast)
		(= voMyHelp
			((ToolHelpView new:)
				text: (KArray 8 (temp1 data?))
				setSize:
				init: newCast
				yourself:
			)
		)
		(temp1 dispose:)
		(self setSize:)
		(= param2 (+ param2 2))
		(= param3 (+ param3 25))
		(= temp2 (- right left))
		(= temp3 (- bottom top))
		(if (> (+ param2 temp2) lastScreenX)
			(= param2 (- lastScreenX temp2))
		)
		(if (> (+ param3 temp3) lastScreenY)
			(= param3 (- param3 (+ 20 temp3)))
		)
		(self
			setRect: param2 param3 (+ param2 temp2) (+ param3 temp2)
		)
		(UpdatePlane self)
	)
	
	(method (dispose)
		(if voMyHelp (voMyHelp dispose:) (= voMyHelp 0))
		(super dispose: &rest)
	)
)
