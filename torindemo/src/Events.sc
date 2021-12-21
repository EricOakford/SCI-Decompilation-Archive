;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64892)
(include sci.sh)
(use Main)
(use TimePauser)
(use Set)
(use Cast)
(use DText)
(use Plane)
(use String)
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
	
	(method (doit)
		(FrameOut)
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
		(if (and cuees (not (TimePauser oThumb?)))
			(cuees eachElementDo: #doit)
			(if (cuees isEmpty:) (cuees dispose:) (= cuees 0))
		)
		(if (and script (not (TimePauser oThumb?)))
			(script doit:)
		)
		(if (not (TimePauser oThumb?))
			(regions eachElementDo: #doit)
		)
		(if (not (TimePauser oThumb?)) (theDoits doit:))
		(if (== newRoomNum curRoomNum) (user doit:))
		(timers eachElementDo: #delete)
		(SaveGame 8 0)
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
			_line_   181
			_file_   {filename}
			_line_   182
			_line_   183
			lsp      argc
			ldi      1
			gt?     
			bnt      code_03d4
			_line_   184
			lap      param2
			sat      temp3
			jmp      code_03de
code_03d4:
			_line_   185
			_line_   186
			ldi      0
			sat      temp3
code_03de:
			_line_   188
			lsp      argc
			ldi      2
			gt?     
			bnt      code_03f1
			_line_   189
			lap      param3
			sat      temp2
			jmp      code_03fb
code_03f1:
			_line_   190
			_line_   191
			ldi      0
			sat      temp2
code_03fb:
			_line_   195
			lat      temp3
			not     
			bnt      code_040f
			_line_   196
			pushi    #globalize
			pushi    0
			lap      event
			send     4
code_040f:
			_line_   198
			pushi    2
			pushi    3
			pTos     elements
			callk    KList,  4
			sat      temp0
code_041d:
			_line_   199
			lat      temp0
			bnt      code_0520
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			not     
			bnt      code_0520
			_line_   200
			_line_   201
			pushi    2
			pushi    6
			lst      temp0
			callk    KList,  4
			aTop     nextNode
			_line_   202
			pushi    2
			pushi    8
			lst      temp0
			callk    KList,  4
			sat      temp1
			_line_   205
			lat      temp3
			bnt      code_04a9
			_line_   206
			pushi    #respondsTo
			pushi    1
			pushi    0
			lat      temp1
			send     6
			bnt      code_049a
			_line_   207
			pushi    #nScreenOrgY
			pushi    0
			pushi    #plane
			pushi    0
			lat      temp1
			send     4
			send     4
			bnt      code_048f
			_line_   208
			pushi    #localize
			pushi    1
			pushi    #plane
			pushi    0
			lat      temp1
			send     4
			push    
			lap      event
			send     6
			jmp      code_04a9
code_048f:
			_line_   209
			_line_   210
			jmp      code_0519
			jmp      code_04a9
code_049a:
			_line_   212
			_line_   213
			pushi    #globalize
			pushi    0
			lap      event
			send     4
code_04a9:
			_line_   218
			lat      temp2
			bnt      code_04ef
			_line_   219
			pushi    #respondsTo
			pushi    1
			pushi    269
			lat      temp1
			send     6
			bnt      code_04ef
			pushi    #respondsTo
			pushi    1
			pushi    291
			lat      temp1
			send     6
			bnt      code_04ef
			_line_   220
			pushi    #onMe
			pushi    1
			lsp      event
			lat      temp1
			send     6
			bnt      code_04ef
			_line_   221
			pushi    #focus
			pushi    2
			lst      temp1
			lsp      event
			lag      gOEventHandler
			send     8
code_04ef:
			_line_   227
			pushi    #handleEvent
			pushi    1
			lsp      event
			lat      temp1
			send     6
			bnt      code_0519
			_line_   228
			lat      temp2
			bnt      code_0514
			_line_   229
			pushi    #currentPolygon
			pushi    1
			lst      temp1
			lag      gOEventHandler
			send     6
code_0514:
			_line_   231
			jmp      code_0520
code_0519:
			pToa     nextNode
			sat      temp0
			jmp      code_041d
code_0520:
			_line_   236
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
		currentIndex 0
		depressType 0
		finished 0
		addPolygon 0
		readPolygonsFromList 0
		savePolygons 0
		savePolygonsToRoom 1
		loadPolygons 0
		loadPolygonsFromRoom 0
		setFocus 0
		constructPolygon 0
		dropAnchor 0
		backwards 0
	)
	
	(method (dispose)
		(if loadPolygons
			(loadPolygons release:)
			(loadPolygons dispose:)
		)
		(if loadPolygonsFromRoom
			(loadPolygonsFromRoom release:)
			(loadPolygonsFromRoom dispose:)
		)
		(if setFocus (setFocus dispose:))
		(if dropAnchor (dropAnchor dispose:))
		(if constructPolygon (constructPolygon dispose:))
		(if backwards (backwards dispose:))
	)
	
	(method (setIncrement param1)
		(if (not loadPolygons)
			(= loadPolygons (NewHandlerList new:))
		)
		(loadPolygons add: param1)
	)
	
	(method (pageDown param1)
		(if loadPolygons (loadPolygons delete: param1))
	)
	
	(method (screenLocY param1)
		(if (not loadPolygonsFromRoom)
			(= loadPolygonsFromRoom (NewHandlerList new:))
		)
		(loadPolygonsFromRoom addToFront: param1)
	)
	
	(method (screenLocX param1)
		(if loadPolygonsFromRoom
			(loadPolygonsFromRoom delete: param1)
		)
	)
	
	(method (scriptId param1)
		(if (not setFocus) (= setFocus (NewHandlerList new:)))
		(setFocus addToFront: param1)
	)
	
	(method (bHasFF param1)
		(if setFocus (setFocus delete: param1))
	)
	
	(method (scrolled param1)
		(if (not constructPolygon)
			(= constructPolygon (NewHandlerList new:))
		)
		(constructPolygon addToFront: param1)
	)
	
	(method (pageUp param1)
		(if constructPolygon (constructPolygon delete: param1))
	)
	
	(method (bHasRew param1)
		(if (not dropAnchor)
			(= dropAnchor (NewHandlerList new:))
		)
		(dropAnchor addToFront: param1)
	)
	
	(method (bKillRew param1)
		(if dropAnchor (dropAnchor delete: param1))
	)
	
	(method (ff)
		(if readPolygonsFromList
			(addPolygon dispose:)
			(= savePolygons
				(= readPolygonsFromList (= addPolygon 0))
			)
		)
	)
	
	(method (nScreenSizeY param1)
		(if (== param1 readPolygonsFromList) (self ff:))
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
		(self focus: 0 event)
		(if
		(and setFocus (setFocus handleEvent: event 0 0))
			(return 1)
		)
		(if
			(and
				(& (event type?) $000c)
				constructPolygon
				(constructPolygon handleEvent: event 0 0)
			)
			(return 1)
		)
		(if (& (event type?) $000c) (return 0))
		(if
			(and
				loadPolygonsFromRoom
				(loadPolygonsFromRoom handleEvent: event 1 0)
			)
			(return 1)
		)
		(if (not (user canControl:))
			(if
			(and loadPolygons (loadPolygons handleEvent: event 1 1))
				(event claimed: 1)
			)
			(if
				(and
					(not (talkers isEmpty:))
					(not (TimePauser oThumb?))
				)
				(talkers
					eachElementDo: #doit
					firstTrue: #handleEvent event
				)
			)
			(if (not (event claimed?)) (self currentPolygon: 0))
			(return (event claimed?))
		)
		(if (and curRoom (curRoom inset?))
			(event localize: (cast plane?))
			(if (= temp3 (cast firstTrue: #onMe event))
				(self focus: temp3 event)
			)
			(if (= temp3 (features firstTrue: #onMe event))
				(self focus: temp3 event)
			)
			(self focus: 0 event)
			(if (= temp3 (cast firstTrue: #handleEvent event))
				(self currentPolygon: temp3)
				(return 1)
			)
			(if
			(= temp3 (features firstTrue: #handleEvent event))
				(self currentPolygon: temp3)
				(return 1)
			)
			(self currentPolygon: 0)
			(return 0)
		)
		(= planesFirst (planes first:))
		(while planesFirst
			(= temp1 (KList 8 planesFirst))
			(if (self rewind: temp1 event) (return 1))
			(= planesFirst (planes next: planesFirst))
		)
		(self currentPolygon: 0)
		(if
		(and dropAnchor (dropAnchor handleEvent: event 0 0))
			(return 1)
		)
		(return 0)
	)
	
	(method (rewind param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(asm
			_line_   535
			_file_   {filename}
			_line_   537
			pushi    #globalize
			pushi    0
			lap      param2
			send     4
			_line_   538
			pushi    #onMe
			pushi    1
			lsp      param2
			lap      param1
			send     6
			not     
			bnt      code_0aca
			_line_   539
			ldi      0
			ret     
code_0aca:
			_line_   543
			pushi    #priority
			pushi    0
			lap      param1
			send     4
			push    
			ldi      65535
			eq?     
			bt       code_0aea
			pushi    #priority
			pushi    0
			lap      param1
			send     4
			push    
			ldi      510
			eq?     
			bnt      code_0af0
code_0aea:
			_line_   544
			ldi      0
			ret     
code_0af0:
			_line_   550
			pushi    #localize
			pushi    1
			lsp      param1
			lap      param2
			send     6
			_line_   551
			pushi    #casts
			pushi    0
			lap      param1
			send     4
			sat      temp2
			_line_   553
			pToa     backwards
			bnt      code_0b30
			_line_   554
			pushi    #release
			pushi    0
			send     4
			_line_   555
			pushi    #dispose
			pushi    0
			pToa     backwards
			send     4
			_line_   556
			ldi      0
			aTop     backwards
code_0b30:
			_line_   559
			pushi    #first
			pushi    0
			lat      temp2
			send     4
			sat      temp4
code_0b3e:
			_line_   560
			lat      temp4
			bnt      code_0bf4
			_line_   561
			_line_   562
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp1
			_line_   564
			pushi    #first
			pushi    0
			send     4
			sat      temp3
code_0b62:
			_line_   565
			lat      temp3
			bnt      code_0be5
			_line_   566
			_line_   567
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp0
			_line_   569
			pushi    #onMe
			pushi    1
			lsp      param2
			send     6
			bnt      code_0bb4
			_line_   570
			pToa     savePolygonsToRoom
			bnt      code_0ba3
			_line_   571
			pushi    #setButtons
			pushi    1
			lst      temp0
			self     6
			_line_   572
			jmp      code_0bd6
			jmp      code_0bb4
code_0ba3:
			_line_   573
			_line_   574
			pushi    #focus
			pushi    2
			lst      temp0
			lsp      param2
			self     8
code_0bb4:
			_line_   578
			pushi    #handleEvent
			pushi    1
			lsp      param2
			lat      temp0
			send     6
			bnt      code_0bd6
			_line_   579
			pushi    #currentPolygon
			pushi    1
			lst      temp0
			self     6
			_line_   580
			ldi      1
			ret     
code_0bd6:
			pushi    #next
			pushi    1
			lst      temp3
			lat      temp1
			send     6
			sat      temp3
			jmp      code_0b62
code_0be5:
			pushi    #next
			pushi    1
			lst      temp4
			lat      temp2
			send     6
			sat      temp4
			jmp      code_0b3e
code_0bf4:
			_line_   586
			pushi    #nScreenSizeX
			pushi    0
			lap      param1
			send     4
			sat      temp5
			_line_   587
			pushi    #first
			pushi    0
			send     4
			sat      temp3
code_0c0e:
			_line_   588
			lat      temp3
			bnt      code_0c91
			_line_   589
			_line_   590
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp0
			_line_   592
			pushi    #onMe
			pushi    1
			lsp      param2
			send     6
			bnt      code_0c60
			_line_   593
			pToa     savePolygonsToRoom
			bnt      code_0c4f
			_line_   594
			pushi    #setButtons
			pushi    1
			lst      temp0
			self     6
			_line_   595
			jmp      code_0c82
			jmp      code_0c60
code_0c4f:
			_line_   596
			_line_   597
			pushi    #focus
			pushi    2
			lst      temp0
			lsp      param2
			self     8
code_0c60:
			_line_   601
			pushi    #handleEvent
			pushi    1
			lsp      param2
			lat      temp0
			send     6
			bnt      code_0c82
			_line_   602
			pushi    #currentPolygon
			pushi    1
			lst      temp0
			self     6
			_line_   603
			ldi      1
			ret     
code_0c82:
			pushi    #next
			pushi    1
			lst      temp3
			lat      temp5
			send     6
			sat      temp3
			jmp      code_0c0e
code_0c91:
			_line_   608
			pToa     savePolygonsToRoom
			bnt      code_0d01
			pToa     backwards
			bnt      code_0d01
			_line_   609
			pushi    #first
			pushi    0
			pToa     backwards
			send     4
			sat      temp3
			_line_   610
code_0caf:
			lat      temp3
			bnt      code_0d01
			_line_   611
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp0
			_line_   612
			pushi    #focus
			pushi    2
			push    
			lsp      param2
			self     8
			_line_   613
			pushi    #handleEvent
			pushi    1
			lsp      param2
			lat      temp0
			send     6
			bnt      code_0cf0
			_line_   614
			pushi    #currentPolygon
			pushi    1
			lst      temp0
			self     6
			_line_   615
			ldi      1
			ret     
code_0cf0:
			_line_   617
			pushi    #next
			pushi    1
			lst      temp3
			pToa     backwards
			send     6
			sat      temp3
			jmp      code_0caf
code_0d01:
			_line_   623
			ldi      0
			ret     
		)
	)
	
	(method (currentPolygon param1 &tmp temp0 temp1)
		(if param1
			(if
				(and
					(= temp0 (param1 setSpeedFraction:))
					(!= temp0 -1)
				)
				(if (!= theCursor temp0) (theGame setCursor: temp0))
			else
				(if (>= gVerb (global100 size:))
					(Prints LOOKUP_ERROR gVerb)
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
					((== temp0 -1) (if (!= (theCursor cel:) 0) (theCursor setCel: 0)))
					((!= (theCursor cel:) 1) (theCursor setCel: 1))
				)
			)
		else
			(if (>= gVerb (global100 size:))
				(Prints LOOKUP_ERROR gVerb)
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
			(if (theCursor cel:) (theCursor setCel: 0))
		)
	)
	
	(method (focus theReadPolygonsFromList param2 &tmp [temp0 3] temp3 theReadPolygonsFromListNoun)
		(if (or (not global111) (not (user canControl:)))
			(self ff:)
			(return)
		)
		(if (and param2 (== (param2 type?) 1))
			(self ff:)
			(return)
		)
		(= temp3 (Clone param2))
		(if (and readPolygonsFromList param2)
			(temp3 localize: (readPolygonsFromList plane?))
			(if (readPolygonsFromList onMe: temp3)
				(temp3 dispose:)
				(return)
			)
		)
		(if readPolygonsFromList
			(self ff:)
			(temp3 dispose:)
			(return)
		)
		(if
			(or
				(not theReadPolygonsFromList)
				(not param2)
				(not curRoom)
				(== (param2 plane?) (curRoom plane?))
			)
			(temp3 dispose:)
			(return)
		)
		(temp3 globalize:)
		(= theReadPolygonsFromListNoun
			(theReadPolygonsFromList noun?)
		)
		(if
			(and
				(not readPolygonsFromList)
				theReadPolygonsFromListNoun
				(Message 0 5 theReadPolygonsFromListNoun 6 0 1)
			)
			(if
				(or
					(not savePolygons)
					(!= (temp3 x?) currentIndex)
					(!= (temp3 y?) depressType)
				)
				(= savePolygons 1)
				(= finished (+ gameTime global112))
				(= currentIndex (temp3 x?))
				(= depressType (temp3 y?))
			)
			(if (>= gameTime finished)
				(= savePolygons 0)
				(= addPolygon
					((LOOKUP_ERROR new:)
						init: theReadPolygonsFromListNoun (temp3 x?) (temp3 y?)
						yourself:
					)
				)
				(= readPolygonsFromList theReadPolygonsFromList)
			)
		)
		(temp3 dispose:)
	)
	
	(method (setButtons param1 &tmp backwardsFirst temp1 temp2)
		(if (not backwards) (= backwards (Set new:)))
		(= backwardsFirst (backwards first:))
		(= temp1 0)
		(while backwardsFirst
			(= temp2 (KList 8 backwardsFirst))
			(if (> (param1 nScrollMaxX:) (temp2 nScrollMaxX:))
				(break)
			)
			(= temp1 temp2)
			(= backwardsFirst (backwards next: backwardsFirst))
		)
		(if temp1
			(backwards addAfter: temp1 param1)
		else
			(backwards addToFront: param1)
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
		fore 204
		back 212
		skip 0
		font 0
		borderColor 204
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
		nScreenSizeX 0
		oMyVerbHandlers 0
	)
	
	(method (init param1 param2 param3 &tmp newCast temp1 temp2 temp3)
		(super init: &rest)
		(= newCast (Cast new:))
		(self addCast: newCast)
		(= temp1 (Str newWith: 400 LOOKUP_ERROR))
		(Message 0 5 param1 6 0 1 (temp1 data?))
		(= oMyVerbHandlers
			((ToolHelpView new:)
				text: (KString 8 (temp1 data?))
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
		(if oMyVerbHandlers
			(oMyVerbHandlers dispose:)
			(= oMyVerbHandlers 0)
		)
		(super dispose: &rest)
	)
)
