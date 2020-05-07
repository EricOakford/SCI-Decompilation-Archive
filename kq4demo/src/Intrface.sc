;;; Sierra Script 1.0 - (do not remove this comment)
(script# 255)
(include game.sh)
(use Main)
(use System)

(public
	Print 0
	ShowView 1
	GetInput 2
	GetNumber 3
	Printf 4
)

(local
	modelessPort
	[storage 20]
)
(procedure (Print param1 &tmp newDialog newDText newDIcon newDEdit temp4 temp5 temp6 temp7 temp8 theModelessDialog temp10 temp11 [newDButton 6] temp18 temp19 temp20 [temp21 1000])
	(= temp11 (GetPort))
	(= temp6 (= temp7 -1))
	(= theModelessDialog
		(= temp8
			(= temp18 (= newDIcon (= newDEdit (= temp19 0))))
		)
	)
	((= newDialog (Dialog new:))
		window: systemWindow
		name: {PrintD}
	)
	(= newDText (DText new:))
	(cond 
		((u< [param1 0] 1000) (GetFarText [param1 0] [param1 1] @temp21) (= temp5 2))
		([param1 0] (StrCpy @temp21 [param1 0]) (= temp5 1))
		(else (= temp21 0) (= temp5 0))
	)
	(newDText
		text: @temp21
		moveTo: 4 4
		font: userFont
		setSize:
	)
	(newDialog add: newDText)
	(= temp5 temp5)
	(while (< temp5 argc)
		(switch [param1 temp5]
			(#mode
				(++ temp5)
				(newDText mode: [param1 temp5])
			)
			(#font
				(++ temp5)
				(newDText font: [param1 temp5] setSize: temp8)
			)
			(#width
				(= temp8 [param1 (++ temp5)])
				(newDText setSize: temp8)
			)
			(#time
				(++ temp5)
				(newDialog time: [param1 temp5])
			)
			(#title
				(++ temp5)
				(newDialog text: [param1 temp5])
			)
			(#at
				(= temp6 [param1 (++ temp5)])
				(= temp7 [param1 (++ temp5)])
			)
			(#draw
				(Animate (cast elements?) 0)
			)
			(#edit
				(++ temp5)
				((= newDEdit (DEdit new:)) text: [param1 temp5])
				(++ temp5)
				(newDEdit max: [param1 temp5] setSize:)
			)
			(#button
				((= [newDButton temp19] (DButton new:))
					text: [param1 (++ temp5)]
					value: [param1 (++ temp5)]
					setSize:
				)
				(= temp18 (+ temp18 ([newDButton temp19] nsRight?) 4))
				(++ temp19)
			)
			(#icon
				(if (not newDIcon) (= newDIcon (DIcon new:)))
				(newDIcon
					view: [param1 (+ temp5 1)]
					loop: [param1 (+ temp5 2)]
					cel: [param1 (+ temp5 3)]
					setSize:
				)
				(= temp5 (+ temp5 3))
			)
			(#dispose
				(if modelessDialog (modelessDialog dispose:))
				(= theModelessDialog newDialog)
			)
			(#window
				(++ temp5)
				(newDialog window: [param1 temp5])
			)
		)
		(++ temp5)
	)
	(if newDIcon
		(newDIcon moveTo: 4 4)
		(newDText
			moveTo: (+ 4 (newDIcon nsRight?)) (newDText nsTop?)
		)
		(newDialog add: newDIcon)
	)
	(newDialog setSize:)
	(if newDEdit
		(newDEdit
			moveTo: (newDText nsLeft?) (+ 4 (newDText nsBottom?))
		)
		(newDialog add: newDEdit setSize:)
	)
	(= temp20
		(if (> temp18 (newDialog nsRight?))
			4
		else
			(- (newDialog nsRight?) temp18)
		)
	)
	(= temp5 0)
	(while (< temp5 temp19)
		([newDButton temp5] moveTo: temp20 (newDialog nsBottom?))
		(newDialog add: [newDButton temp5])
		(= temp20 (+ 4 ([newDButton temp5] nsRight?)))
		(++ temp5)
	)
	(newDialog setSize: center:)
	(if (and newDIcon (not (StrLen @temp21)))
		(newDIcon
			moveTo:
				(/
					(-
						(- (newDialog nsRight?) (newDialog nsLeft?))
						(- (newDIcon nsRight?) (newDIcon nsLeft?))
					)
					2
				)
				4
		)
	)
	(newDialog
		moveTo:
			(if (== -1 temp6) (newDialog nsLeft?) else temp6)
			(if (== -1 temp7) (newDialog nsTop?) else temp7)
	)
	(newDialog
		open: (if (newDialog text?) 4 else 0) (if theModelessDialog 15 else -1)
	)
	(if theModelessDialog
		(= modelessPort (GetPort))
		(SetPort temp11)
		(return (= modelessDialog theModelessDialog))
	)
	(if
		(and
			(= temp10 (newDialog firstTrue: #checkState 1))
			(not (newDialog firstTrue: #checkState 2))
		)
		(temp10 state: (| (temp10 state?) $0002))
	)
	(if (== (= temp4 (newDialog doit: temp10)) -1)
		(= temp4 0)
	)
	(= temp5 0)
	(while (< temp5 temp19)
		(if (== temp4 [newDButton temp5])
			(= temp4 (temp4 value?))
			(break)
		)
		(++ temp5)
	)
	(if (not (newDialog theItem?)) (= temp4 1))
	(newDialog dispose:)
	(return temp4)
)

(procedure (ShowView param1 param2 param3 param4)
	(Print param1 #icon param2 param3 param4 &rest)
)

(procedure (GetInput param1 param2 param3 &tmp [temp0 4])
	(if
		(Print
			(if (>= argc 3) param3 else {})
			#edit
			param1
			param2
			&rest
		)
		(StrLen param1)
	)
)

(procedure (GetNumber param1 param2 &tmp [temp0 40])
	(= temp0 0)
	(if (> argc 1) (Format @temp0 255 0 param2))
	(return
		(if (GetInput @temp0 5 param1)
			(ReadNumber @temp0)
		else
			-1
		)
	)
)

(procedure (Printf &tmp [temp0 500])
	(Format @temp0 &rest)
	(Print @temp0)
)

(procedure (localproc_0014 &tmp newEvent temp1)
	(= temp1 (!= ((= newEvent (Event new:)) type?) 2))
	(newEvent dispose:)
	(return temp1)
)

(class MenuBar of Object
	(properties
		state $0000
	)
	
	(method (draw)
		(= state 1)
		(DrawMenuBar 1)
	)
	
	(method (hide)
		(DrawMenuBar 0)
	)
	
	(method (handleEvent event)
		(return (if state (MenuSelect event &rest) else 0))
	)
	
	(method (add)
		(AddMenu &rest)
	)
)

(class DItem of Object
	(properties
		type $0000
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
	)
	
	(method (doit)
		(return value)
	)
	
	(method (enable param1)
		(if param1
			(= state (| state $0001))
		else
			(= state (& state $fffe))
		)
	)
	
	(method (select param1)
		(if param1
			(= state (| state $0008))
		else
			(= state (& state $fff7))
		)
		(self draw:)
	)
	
	(method (handleEvent event &tmp temp0 eventType temp2)
		(if (event claimed?) (return 0))
		(= temp0 0)
		(if
			(and
				(& state $0001)
				(or
					(and
						(== (= eventType (event type?)) 128)
						(Said said)
					)
					(and
						(== eventType evKEYBOARD)
						(== (event message?) key)
					)
					(and (== eventType evMOUSEBUTTON) (self check: event))
				)
			)
			(event claimed: 1)
			(= temp0 (self track: event))
		)
		(return temp0)
	)
	
	(method (check param1)
		(return
			(if
				(and
					(>= (param1 x?) nsLeft)
					(>= (param1 y?) nsTop)
					(< (param1 x?) nsRight)
				)
				(< (param1 y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (track param1 &tmp temp0 temp1)
		(return
			(if (== 1 (param1 type?))
				(= temp1 0)
				(repeat
					(= param1 (Event new: -32768))
					(GlobalToLocal param1)
					(if (!= (= temp0 (self check: param1)) temp1)
						(HiliteControl self)
						(= temp1 temp0)
					)
					(param1 dispose:)
					(breakif (not (localproc_0014)))
				)
				(if temp0 (HiliteControl self))
				(return temp0)
			else
				(return self)
			)
		)
	)
	
	(method (setSize)
	)
	
	(method (move param1 param2)
		(= nsRight (+ nsRight param1))
		(= nsLeft (+ nsLeft param1))
		(= nsTop (+ nsTop param2))
		(= nsBottom (+ nsBottom param2))
	)
	
	(method (moveTo param1 param2)
		(self move: (- param1 nsLeft) (- param2 nsTop))
	)
	
	(method (draw)
		(DrawControl self)
	)
	
	(method (isType param1)
		(return (== type param1))
	)
	
	(method (checkState param1)
		(return (& state param1))
	)
)

(class DText of DItem
	(properties
		type $0002
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		text 0
		font 1
		mode 0
	)
	
	(method (new &tmp temp0)
		((super new:) font: userFont yourself:)
	)
	
	(method (setSize param1 &tmp [temp0 4])
		(TextSize @[temp0 0] text font (if argc param1 else 0))
		(= nsBottom (+ nsTop [temp0 2]))
		(= nsRight (+ nsLeft [temp0 3]))
	)
)

(class DIcon of DItem
	(properties
		type $0004
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		view 0
		loop 0
		cel 0
	)
	
	(method (setSize &tmp [temp0 4])
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
)

(class DButton of DItem
	(properties
		type $0001
		state $0003
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		text 0
		font 0
	)
	
	(method (setSize &tmp [temp0 4])
		(TextSize @[temp0 0] text font)
		(= [temp0 2] (+ [temp0 2] 2))
		(= [temp0 3] (+ [temp0 3] 2))
		(= nsBottom (+ nsTop [temp0 2]))
		(= [temp0 3] (* (/ (+ [temp0 3] 15) 16) 16))
		(= nsRight (+ [temp0 3] nsLeft))
	)
)

(class DEdit of DItem
	(properties
		type $0003
		state $0001
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		text 0
		font 0
		max 0
		cursor 0
	)
	
	(method (track param1)
		(EditControl self param1)
		(return self)
	)
	
	(method (setSize &tmp [temp0 4])
		(TextSize @[temp0 0] {M} font)
		(= nsBottom (+ nsTop [temp0 2]))
		(= nsRight (+ nsLeft (/ (* [temp0 3] max 3) 4)))
		(= cursor (StrLen text))
	)
)

(class DSelector of DItem
	(properties
		type $0006
		state $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		key 0
		said 0
		value 0
		font 0
		x 20
		y 6
		text 0
		cursor 0
		lsTop 0
		mark 0
	)
	
	(method (handleEvent event &tmp temp0 [temp1 3] temp4 [temp5 4])
		(if (event claimed?) (return 0))
		(if (== evJOYSTICK (event type?))
			(event type: 4)
			(switch (event message?)
				(JOY_DOWN
					(event message: 20480)
				)
				(JOY_UP (event message: 18432))
				(else  (event type: 64))
			)
		)
		(= temp0 0)
		(switch (event type?)
			(evKEYBOARD
				(event claimed: 1)
				(switch (event message?)
					(KEY_NUMPAD7 (self retreat: 50))
					(KEY_NUMPAD1 (self advance: 50))
					(KEY_PAGEUP
						(self advance: (- y 1))
					)
					(KEY_PAGEDOWN
						(self retreat: (- y 1))
					)
					(KEY_NUMPAD2 (self advance: 1))
					(KEY_UP (self retreat: 1))
					(else  (event claimed: 0))
				)
			)
			(evMOUSEBUTTON
				(if (self check: event)
					(event claimed: 1)
					(cond 
						((< (event y?) (+ nsTop 10))
							(repeat
								(self retreat: 1)
								(breakif (not (localproc_0014)))
							)
						)
						((> (event y?) (- nsBottom 10))
							(repeat
								(self advance: 1)
								(breakif (not (localproc_0014)))
							)
						)
						(else
							(TextSize @[temp5 0] {M} font)
							(if
								(>
									(= temp4 (/ (- (event y?) (+ nsTop 10)) [temp5 2]))
									mark
								)
								(self advance: (- temp4 mark))
							else
								(self retreat: (- mark temp4))
							)
						)
					)
				)
			)
		)
		(return
			(if (and (event claimed?) (& state $0002))
				self
			else
				0
			)
		)
	)
	
	(method (setSize &tmp [temp0 4])
		(TextSize @[temp0 0] {M} font)
		(= nsBottom (+ nsTop 20 (* [temp0 2] y)))
		(= nsRight (+ nsLeft (/ (* [temp0 3] x 3) 4)))
		(= lsTop (= cursor text))
		(= mark 0)
	)
	
	(method (indexOf param1 &tmp theText temp1)
		(= theText text)
		(= temp1 0)
		(return
			(while (< temp1 300)
				(if (== 0 (StrLen theText)) (return -1))
				(if (not (StrCmp param1 theText)) (return temp1))
				(= theText (+ theText x))
				(++ temp1)
			)
		)
	)
	
	(method (at param1)
		(return (+ text (* x param1)))
	)
	
	(method (advance param1 &tmp temp0)
		(= temp0 0)
		(while (and param1 (StrAt cursor x))
			(= temp0 1)
			(= cursor (+ cursor x))
			(if (< (+ mark 1) y)
				(++ mark)
			else
				(= lsTop (+ lsTop x))
			)
			(-- param1)
		)
		(if temp0 (self draw:))
	)
	
	(method (retreat param1 &tmp temp0)
		(= temp0 0)
		(while (and param1 (!= cursor text))
			(= temp0 1)
			(= cursor (- cursor x))
			(if mark (-- mark) else (= lsTop (- lsTop x)))
			(-- param1)
		)
		(if temp0 (self draw:))
	)
)

(class Dialog of List
	(properties
		elements 0
		size 0
		text 0
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		timer 0
		busy 0
	)
	
	(method (doit param1 &tmp temp0 temp1 temp2 temp3 temp4)
		(asm
			ldi      0
			sat      temp0
			ldi      1
			aTop     busy
			pToa     theItem
			bnt      code_08e1
			pushi    #select
			pushi    1
			pushi    0
			send     6
code_08e1:
			lap      argc
			bnt      code_08f0
			lap      param1
			bnt      code_08f0
			lap      param1
			jmp      code_08f9
code_08f0:
			pushi    #firstTrue
			pushi    2
			pushi    146
			pushi    1
			self     8
code_08f9:
			aTop     theItem
			pToa     theItem
			bnt      code_0907
			pushi    #select
			pushi    1
			pushi    1
			send     6
code_0907:
			pToa     theItem
			not     
			bnt      code_091a
			ldi      60
			sat      temp3
			pushi    0
			callk    GetTime,  0
			sat      temp4
			jmp      code_091e
code_091a:
			ldi      0
			sat      temp3
code_091e:
			ldi      0
			sat      temp2
code_0922:
			lat      temp2
			not     
			bnt      code_09a2
			pushi    1
			pushi    #new
			pushi    0
			class    Event
			send     4
			sat      temp1
			push    
			callk    GlobalToLocal,  2
			lat      temp3
			bnt      code_0969
			-at      temp3
			pushi    #type
			pushi    0
			lat      temp1
			send     4
			push    
			ldi      1
			eq?     
			bnt      code_0953
			pushi    #type
			pushi    1
			pushi    0
			lat      temp1
			send     6
code_0953:
			lst      temp4
			pushi    0
			callk    GetTime,  0
			eq?     
			bnt      code_0963
			jmp      code_0953
			jmp      code_0953
code_0963:
			pushi    0
			callk    GetTime,  0
			sat      temp4
code_0969:
			pushi    #handleEvent
			pushi    1
			lst      temp1
			self     6
			sat      temp2
			pushi    #dispose
			pushi    0
			lat      temp1
			send     4
			pToa     timer
			bnt      code_0983
			pushi    #doit
			pushi    0
			send     4
code_0983:
			lst      temp2
			ldi      65535
			eq?     
			bt       code_0991
			pToa     busy
			not     
			bnt      code_0922
code_0991:
			ldi      0
			sat      temp2
			pushi    2
			pTos     theItem
			pushi    0
			callk    EditControl,  4
			jmp      code_09a2
			jmp      code_0922
code_09a2:
			ldi      0
			aTop     busy
			lat      temp2
			ret     
		)
	)
	
	(method (dispose)
		(if (== self modelessDialog)
			(SetPort modelessPort)
			(= modelessDialog 0)
			(= modelessPort 0)
		)
		(if window (window dispose:))
		(= window 0)
		(if timer (timer dispose: delete:))
		(= theItem 0)
		(super dispose:)
	)
	
	(method (open param1 param2)
		(if (and (PicNotValid) cast)
			(Animate (cast elements?) 0)
		)
		(= window (window new:))
		(window
			top: nsTop
			left: nsLeft
			bottom: nsBottom
			right: nsRight
			title: text
			type: param1
			priority: param2
			open:
		)
		(if time (Timer setReal: self time))
		(self draw:)
	)
	
	(method (draw)
		(self eachElementDo: #draw)
	)
	
	(method (cue)
		(if (not busy) (self dispose:) else (= busy 0))
	)
	
	(method (advance &tmp temp0 dialogFirst)
		(if theItem
			(theItem select: 0)
			(= dialogFirst (self contains: theItem))
			(repeat
				(if (not (= dialogFirst (self next: dialogFirst)))
					(= dialogFirst (self first:))
				)
				(= theItem (NodeValue dialogFirst))
				(if (& (theItem state?) $0001) (break))
			)
			(theItem select: 1)
		)
	)
	
	(method (retreat &tmp temp0 dialogLast)
		(if theItem
			(theItem select: 0)
			(= dialogLast (self contains: theItem))
			(repeat
				(if (not (= dialogLast (self prev: dialogLast)))
					(= dialogLast (self last:))
				)
				(= theItem (NodeValue dialogLast))
				(if (& (theItem state?) $0001) (break))
			)
			(theItem select: 1)
		)
	)
	
	(method (move param1 param2)
		(= nsRight (+ nsRight param1))
		(= nsLeft (+ nsLeft param1))
		(= nsTop (+ nsTop param2))
		(= nsBottom (+ nsBottom param2))
	)
	
	(method (moveTo param1 param2)
		(self move: (- param1 nsLeft) (- param2 nsTop))
	)
	
	(method (center)
		(self
			moveTo:
				(+
					(window brLeft?)
					(/
						(-
							(- (window brRight?) (window brLeft?))
							(- nsRight nsLeft)
						)
						2
					)
				)
				(+
					(window brTop?)
					(/
						(-
							(- (window brBottom?) (window brTop?))
							(- nsBottom nsTop)
						)
						2
					)
				)
		)
	)
	
	(method (setSize &tmp dialogFirst temp1 [theNsTop 4])
		(if text
			(TextSize @[theNsTop 0] text 0 -1)
			(= nsTop [theNsTop 0])
			(= nsLeft [theNsTop 1])
			(= nsBottom [theNsTop 2])
			(= nsRight [theNsTop 3])
		else
			(= nsRight (= nsBottom (= nsLeft (= nsTop 0))))
		)
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
		(= nsRight (+ nsRight 4))
		(= nsBottom (+ nsBottom 4))
		(self moveTo: 0 0)
	)
	
	(method (handleEvent event &tmp theTheItem)
		(if
			(or
				(event claimed?)
				(== (event type?) evNULL)
				(and
					(!= evMOUSEBUTTON (event type?))
					(!= evKEYBOARD (event type?))
					(!= evJOYSTICK (event type?))
					(!= evJOYDOWN (event type?))
				)
			)
			(EditControl theItem event)
			(return 0)
		)
		(if
		(= theTheItem (self firstTrue: #handleEvent event))
			(EditControl theItem 0)
			(cond 
				((theTheItem isType: 3)
					(if theItem (theItem select: 0))
					((= theItem theTheItem) select: 1)
					(= theTheItem 0)
				)
				((not (theTheItem checkState: 2)) (theTheItem doit:) (self advance:) (= theTheItem 0))
			)
		else
			(= theTheItem 0)
			(cond 
				(
					(and
						(or
							(== (event type?) evJOYDOWN)
							(and
								(== evKEYBOARD (event type?))
								(== KEY_RETURN (event message?))
							)
						)
						theItem
						(theItem checkState: 1)
					)
					(= theTheItem theItem)
					(EditControl theItem 0)
					(event claimed: 1)
				)
				(
					(or
						(and
							(not (self firstTrue: #checkState 1))
							(or
								(and
									(== evKEYBOARD (event type?))
									(== KEY_RETURN (event message?))
								)
								(== evMOUSEBUTTON (event type?))
								(== evJOYDOWN (event type?))
							)
						)
						(and
							(== evKEYBOARD (event type?))
							(== KEY_ESCAPE (event message?))
						)
					)
					(event claimed: 1)
					(= theTheItem -1)
				)
				(
					(and
						(== evKEYBOARD (event type?))
						(== KEY_TAB (event message?))
					)
					(event claimed: 1)
					(self advance:)
				)
				(
					(and
						(== evKEYBOARD (event type?))
						(== KEY_SHIFTTAB (event message?))
					)
					(event claimed: 1)
					(self retreat:)
				)
				(else (EditControl theItem event))
			)
		)
		(return theTheItem)
	)
)

(class Controls of List
	(properties
		elements 0
		size 0
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
