;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64922)
(include sci.sh)
(use Main)
(use Array)
(use System)


(class Dialog of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		text 0
		font 0
		theItem 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		ticks 0
		caller 0
		plane 0
		eatTheMice 0
		lastTicks 0
		mouseHiliting 0
		margin 4
		modeless 0
		onScreen 0
		makeBorder 1
		tl1 0
		tl2 0
		tl3 0
		tl4 0
	)
	
	(method (init param1 &tmp temp0)
		(= temp0 (if argc param1 else (+ (GetHighPlanePri) 1)))
		(plane
			priority: temp0
			init: nsLeft nsTop nsRight nsBottom
			addCast: self
		)
		(self eachElementDo: #init self)
	)
	
	(method (doit param1 &tmp temp0 temp1 temp2 temp3)
		(asm
			lsg      tickOffset
			pushi    0
			callk    GetTime,  0
			add     
			sag      gameTime
			ldi      0
			sat      temp2
			pToa     theItem
			bnt      code_0105
			pushi    #hilite
			pushi    1
			pushi    0
			send     6
code_0105:
			lap      argc
			bnt      code_0111
			lap      param1
			bnt      code_0111
			lap      param1
			jmp      code_011c
code_0111:
			pushi    #firstTrue
			pushi    2
			pushi    369
			pushi    1
			self     8
code_011c:
			aTop     theItem
			pushi    0
			callk    FrameOut,  0
			pToa     theItem
			bnt      code_012f
			pushi    #hilite
			pushi    1
			pushi    1
			send     6
code_012f:
			pToa     theItem
			not     
			bnt      code_0141
			lag      eatMice
			aTop     eatTheMice
			pushi    0
			callk    GetTime,  0
			aTop     lastTicks
			jmp      code_0145
code_0141:
			ldi      0
			aTop     eatTheMice
code_0145:
			ldi      0
			sat      temp1
code_0149:
			lat      temp1
			not     
			bnt      code_020b
			lsg      tickOffset
			pushi    0
			callk    GetTime,  0
			add     
			sag      gameTime
			pushi    #eachElementDo
			pushi    1
			pushi    370
			self     6
			pushi    #localize
			pushi    1
			pTos     plane
			pushi    #new
			pushi    0
			class    Event
			send     4
			send     6
			sat      temp0
			pToa     eatTheMice
			bnt      code_01a9
			dpToa    eatTheMice
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_0194
			pushi    #type
			pushi    1
			pushi    0
			lat      temp0
			send     6
code_0194:
			pTos     lastTicks
			pushi    0
			callk    GetTime,  0
			eq?     
			bnt      code_01a2
			jmp      code_0194
			jmp      code_0194
code_01a2:
			pushi    0
			callk    GetTime,  0
			aTop     lastTicks
code_01a9:
			pToa     mouseHiliting
			bnt      code_01bf
			pushi    #eachElementDo
			pushi    4
			pushi    106
			lofsa    checkHiliteCode
			push    
			pushSelf
			lst      temp0
			self     12
code_01bf:
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			bnt      code_01d4
			pushi    #handleEvent
			pushi    1
			lst      temp0
			self     6
			sat      temp1
code_01d4:
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			pushi    #check
			pushi    0
			self     4
			sat      temp3
			bt       code_01ef
			pTos     modeless
			ldi      0
			ne?     
			bnt      code_0203
code_01ef:
			lat      temp3
			bnt      code_020b
			pTos     modeless
			ldi      0
			ne?     
			bnt      code_020b
			pushi    #dispose
			pushi    0
			self     4
			jmp      code_020b
code_0203:
			pushi    0
			callk    FrameOut,  0
			jmp      code_0149
code_020b:
			lat      temp1
			ret     
		)
	)
	
	(method (dispose &tmp theCaller)
		(self eachElementDo: #dispose release:)
		(DeleteLine tl1 (self plane?))
		(DeleteLine tl2 (self plane?))
		(DeleteLine tl3 (self plane?))
		(DeleteLine tl4 (self plane?))
		(= tl4 (= tl3 (= tl2 (= tl1 0))))
		(plane deleteCast: self)
		(= theItem 0)
		(= theCaller caller)
		(super dispose:)
		(if theCaller (theCaller cue:))
	)
	
	(method (advance &tmp temp0 dialogFirst)
		(if theItem
			(theItem hilite: 0)
			(= dialogFirst (self contains: theItem))
			(repeat
				(if (not (= dialogFirst (self next: dialogFirst)))
					(= dialogFirst (self first:))
				)
				(= theItem (NodeValue dialogFirst))
				(if (& (theItem state?) $0001) (break))
			)
			(theItem hilite: 1)
			(if (not (HaveMouse))
				(theGame
					setCursor:
						theCursor
						1
						(+
							(theItem nsLeft?)
							(/ (- (theItem nsRight?) (theItem nsLeft?)) 2)
						)
						(- (theItem nsBottom?) 3)
				)
			)
		)
	)
	
	(method (retreat &tmp temp0 dialogLast)
		(if theItem
			(theItem hilite: 0)
			(= dialogLast (self contains: theItem))
			(repeat
				(if (not (= dialogLast (self prev: dialogLast)))
					(= dialogLast (self last:))
				)
				(= theItem (NodeValue dialogLast))
				(if (& (theItem state?) $0001) (break))
			)
			(theItem hilite: 1)
			(if (not (HaveMouse))
				(theGame
					setCursor:
						theCursor
						1
						(+
							(theItem nsLeft?)
							(/ (- (theItem nsRight?) (theItem nsLeft?)) 2)
						)
						(- (theItem nsBottom?) 3)
				)
			)
		)
	)
	
	(method (move param1 param2)
		(= nsLeft (+ nsLeft param1))
		(= nsTop (+ nsTop param2))
		(= nsRight (+ nsRight param1))
		(= nsBottom (+ nsBottom param2))
		(if onScreen
			(if (< nsLeft 0)
				(= nsRight (+ nsRight (- 0 nsLeft)))
				(= nsLeft 0)
			)
			(if (> nsRight 320)
				(= nsLeft (+ nsLeft (- 320 nsRight)))
				(= nsRight 320)
			)
			(if (< nsTop 0)
				(= nsBottom (+ nsBottom (- 0 nsTop)))
				(= nsTop 0)
			)
			(if (> nsBottom 200)
				(= nsTop (+ nsTop (- 200 nsBottom)))
				(= nsBottom 200)
			)
		)
		(if plane
			(plane setRect: nsLeft nsTop nsRight nsBottom 1)
		)
	)
	
	(method (moveTo param1 param2)
		(self move: (- param1 nsLeft) (- param2 nsTop))
	)
	
	(method (center param1 &tmp [temp0 2] thePlaneLeft thePlaneTop thePlaneRight thePlaneBottom curRoomPlane)
		(cond 
			(argc
				(= thePlaneLeft (param1 left:))
				(= thePlaneTop (param1 top?))
				(= thePlaneRight (param1 right:))
				(= thePlaneBottom (param1 bottom?))
			)
			((or (not curRoom) (not (curRoom plane?)))
				(= thePlaneLeft (thePlane left:))
				(= thePlaneTop (thePlane top?))
				(= thePlaneRight (thePlane right:))
				(= thePlaneBottom (thePlane bottom?))
			)
			((= curRoomPlane (curRoom plane?))
				(= thePlaneLeft (curRoomPlane left:))
				(= thePlaneTop (curRoomPlane top?))
				(= thePlaneRight (curRoomPlane right:))
				(= thePlaneBottom (curRoomPlane bottom?))
			)
			(else (return))
		)
		(self
			moveTo:
				(+
					thePlaneLeft
					(/
						(- (- thePlaneRight thePlaneLeft) (- nsRight nsLeft))
						2
					)
				)
				(+
					thePlaneTop
					(/
						(- (- thePlaneBottom thePlaneTop) (- nsBottom nsTop))
						2
					)
				)
		)
	)
	
	(method (setSize &tmp dialogFirst temp1 temp2)
		(= temp2 (IntArray with: 0 0 0 0))
		(if text
			(TextSize (temp2 data?) text font 0)
			(= nsLeft (temp2 at: 0))
			(= nsTop (temp2 at: 1))
			(= nsRight (temp2 at: 2))
			(= nsBottom (temp2 at: 3))
		else
			(= nsBottom (= nsRight (= nsTop (= nsLeft 0))))
		)
		(temp2 dispose:)
		(= dialogFirst (self first:))
		(while dialogFirst
			(if
			(< ((= temp1 (NodeValue dialogFirst)) nsLeft?) nsLeft)
				(= nsLeft (temp1 nsLeft?))
			)
			(if (< (temp1 nsTop?) nsTop) (= nsTop (temp1 nsTop?)))
			(if (> (temp1 nsRight?) nsRight)
				(= nsRight (temp1 nsRight?))
			)
			(if (> (temp1 nsBottom?) nsBottom)
				(= nsBottom (temp1 nsBottom?))
			)
			(= dialogFirst (self next: dialogFirst))
		)
		(= nsRight (+ nsRight margin))
		(= nsBottom (+ nsBottom margin))
		(if plane (plane setRect: nsLeft nsTop nsRight nsBottom))
		(cond 
			((not plane))
			(tl1 (self updateBox:))
			(else (self doBox:))
		)
	)
	
	(method (handleEvent event &tmp theTheItem eventType eventMessage)
		(if (& (event type?) evMENUSTART)
			(switch (event message?)
				(JOY_DOWN
					(event type: 4 message: 20480)
				)
				(JOY_UP
					(event type: 4 message: 18432)
				)
				(JOY_LEFT
					(event type: 4 message: 19200)
				)
				(JOY_RIGHT
					(event type: 4 message: 19712)
				)
			)
		)
		(= eventType (event type?))
		(= eventMessage (event message?))
		(if
		(= theTheItem (self firstTrue: #handleEvent event))
			(if (not (theTheItem checkState: 2))
				(if theItem (theItem hilite: 0))
				((= theItem theTheItem) hilite: 1)
				(theTheItem doit:)
				(= theTheItem 0)
			else
				(return theTheItem)
			)
		else
			(= eventType (event type?))
			(= eventMessage (event message?))
			(= theTheItem 0)
			(cond 
				(
					(and
						(or
							(== eventType evMENUHIT)
							(and
								(== eventType evKEYBOARD)
								(== eventMessage KEY_RETURN)
							)
						)
						theItem
						(theItem checkState: 1)
					)
					(= theTheItem theItem)
					(event claimed: 1)
				)
				(
					(and
						(== eventType evKEYBOARD)
						(== eventMessage KEY_ESCAPE)
					)
					(event claimed: 1)
					(= theTheItem -1)
				)
				(
					(and
						(not (self firstTrue: #checkState 1))
						(or
							(and
								(== eventType evKEYBOARD)
								(== eventMessage KEY_RETURN)
							)
							(OneOf eventType 1 32)
						)
					)
					(event claimed: 1)
					(= theTheItem -2)
				)
				(
					(and
						(== eventType evKEYBOARD)
						(OneOf eventMessage 9 19712 20480)
					)
					(event claimed: 1)
					(self advance:)
				)
				(
					(and
						(== eventType evKEYBOARD)
						(OneOf eventMessage 3840 19200 18432)
					)
					(event claimed: 1)
					(self retreat:)
				)
			)
		)
		(return theTheItem)
	)
	
	(method (check)
		(return (if ticks (return (> (- gameTime ticks) 0)) else 0))
	)
	
	(method (doBox &tmp temp0 temp1 temp2 temp3)
		(if makeBorder
			(= temp0 ((self plane?) top?))
			(= temp1 ((self plane?) bottom?))
			(= temp2 ((self plane?) right:))
			(= temp3 ((self plane?) left:))
			(= tl1 (AddLine (self plane?) 0 0 temp2 0 200 0 0 0 1))
			(= tl2
				(AddLine (self plane?) 0 temp1 temp2 temp1 200 0 0 0 1)
			)
			(= tl3
				(AddLine
					(self plane?)
					temp2
					temp0
					temp2
					temp1
					200
					0
					0
					0
					1
				)
			)
			(= tl4 (AddLine (self plane?) 0 0 0 temp1 200 0 0 0 1))
		)
	)
	
	(method (updateBox &tmp temp0 temp1 temp2 temp3)
		(if makeBorder
			(= temp0 ((self plane?) top?))
			(= temp1 ((self plane?) bottom?))
			(= temp2 ((self plane?) right:))
			(= temp3 ((self plane?) left:))
			(UpdateLine tl1 (self plane?) 0 0 temp2 0 200 0 0 0 1)
			(UpdateLine
				tl2
				(self plane?)
				0
				temp1
				temp2
				temp1
				200
				0
				0
				0
				1
			)
			(UpdateLine
				tl3
				(self plane?)
				temp2
				temp0
				temp2
				temp1
				200
				0
				0
				0
				1
			)
			(UpdateLine tl4 (self plane?) 0 0 0 temp1 200 0 0 0 1)
		)
	)
)

(instance checkHiliteCode of Code
	(properties)
	
	(method (doit param1 param2 param3)
		(if
			(and
				(& (param1 state?) $0001)
				(param1 onMe: param3)
				(not (& (param1 state?) $0008))
			)
			((param2 theItem?) hilite: 0)
			(param2 theItem: param1)
			(param1 hilite: 1)
		)
	)
)

(class Controls of List
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
	)
	
	(method (draw)
		(self eachElementDo: #setSize)
		(self eachElementDo: #draw)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return 0))
		(if
			(and
				(= temp0 (self firstTrue: #handleEvent event))
				(not (temp0 checkState: 2))
			)
			(temp0 doit:)
			(= temp0 0)
		)
		(return temp0)
	)
)
