;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRFACE)
(include game.sh)
(use Main)
(use System)

(public
	Print 0
	ShowView 1
	GetInput 2
	GetNumber 3
	Printf 4
	MousedOn 5
)

(define	BMOD 16)
(define	MAXBUTTONS	5)

(procedure (Print args
		&tmp theDialog textI iconI editI ret i atX atY fixWidth keepIt default curPort
		[buttons 6] buttonWide buttonsUsed butAtX [buffer 1001] temp1022 newDText temp1024
		temp1025 theGamePrintLang theGameSubtitleLang temp1028 temp1029 temp1030 temp1031 temp1032)
	(= temp1028 0)
	(= temp1029 0)
	(= atX (= atY -1))
	(= theGamePrintLang (theGame printLang?))
	(= theGameSubtitleLang (theGame subtitleLang?))
	(= temp1032
		(= keepIt
			(= fixWidth
				(= buttonWide
					(= iconI
						(= editI (= newDText (= temp1030 (= buttonsUsed 0))))
					)
				)
			)
		)
	)
	((= theDialog (Dialog new:))
		window: systemWindow
		name: {PrintD}
	)
	(cond 
		((u< [args 0] 1000) (GetFarText [args 0] [args 1] @buffer) (= i 2))
		([args 0] (StrCpy @buffer [args 0]) (= i 1))
		(else (= buffer 0) (= i 0))
	)
	(= temp1022 0)
	(while (StrAt @buffer temp1022)
		(if
			(and
				(== (StrAt @buffer temp1022) 14848)
				(== (StrAt @buffer (+ 1 temp1022)) 74)
			)
			(theGame printLang: ENGLISH subtitleLang: JAPANESE)
			(kernel_123 @buffer @buffer {#J})	;EO: this should be StrSplit, but it is not in VOCAB.999
			(theGame
				printLang: theGamePrintLang
				subtitleLang: theGameSubtitleLang
			)
			(StrAt @buffer temp1022 0)
			(if (OneOf JAPANESE theGamePrintLang theGameSubtitleLang)
				((= newDText (DText new:))
					text: (+ @buffer 2 temp1022)
					font: 900
					name: {jDText}
				)
			)
		)
		(++ temp1022)
	)
	((= textI (DText new:))
		text: @buffer
		font: userFont
	)
	(= temp1024
		(if (and newDText (== theGamePrintLang JAPANESE))
			newDText
		else
			textI
		)
	)
	(= temp1025
		(cond 
			((== theGameSubtitleLang 81) newDText)
			(newDText
				(if theGameSubtitleLang
					textI
				else
					(textI dispose:)
					(= textI 0)
				)
			)
		)
	)
	(temp1024 moveTo: MARGIN MARGIN setSize:)
	(theDialog add: temp1024 setSize:)
	(if temp1025
		(temp1025
			setSize:
			moveTo: (temp1024 nsLeft?) (+ MARGIN (temp1024 nsBottom?))
		)
		(theDialog add: temp1025 setSize:)
	)
	(= i i)
	(while (< i argc)
		(switch [args i]
			(#mode
				(++ i)
				(if (and textI (not temp1025))
					(textI mode: [args i])
				)
			)
			(#font
				(++ i)
				(if textI
					(textI font: [args i] setSize: fixWidth)
				)
			)
			(#width
				(= temp1028 1)
				(= fixWidth [args (++ i)])
				(temp1024 setSize: fixWidth)
				(if temp1025
					(temp1025
						setSize: fixWidth
						moveTo: (temp1024 nsLeft?) (+ 4 (temp1024 nsBottom?))
					)
				)
			)
			(#time
				(++ i)
				(theDialog time: [args i])
			)
			(#title
				(++ i)
				(theDialog text: [args i])
			)
			(#at
				(= atX [args (++ i)])
				(= atY [args (++ i)])
			)
			(#draw
				(Animate (cast elements?) FALSE)
			)
			(#edit
				(++ i)
				((= editI (DEdit new:)) text: [args i])
				(++ i)
				(editI max: [args i] setSize:)
			)
			(#button
				((= [buttons buttonsUsed] (DButton new:))
					text: [args (++ i)]
					value: [args (++ i)]
					setSize:
				)
				(= buttonWide (+ buttonWide ([buttons buttonsUsed] nsRight?) 4))
				(++ buttonsUsed)
			)
			(#icon
				(= temp1029 1)
				(if (IsObject [args (+ i 1)])
					((= iconI ([args (+ i 1)] new:)) setSize:)
					(= i (+ i 1))
				else
					(= iconI (DIcon new:))
					(iconI
						view: [args (+ i 1)]
						loop: [args (+ i 2)]
						cel: [args (+ i 3)]
						setSize:
					)
					(= i (+ i 3))
				)
			)
			(#dispose
				(if
					(and
						(< (+ i 1) argc)
						(IsObject [args (+ i 1)])
					)
					(theDialog caller: [args (+ i 1)])
					(++ i)
				)
				(if (!= 0 #button)
					(if modelessDialog (modelessDialog dispose:))
					(= keepIt theDialog)
				)
			)
			(#window
				(++ i)
				(theDialog window: [args i])
			)
			(#first (= temp1032 1))
		)
		(++ i)
	)
	(if temp1032 (= keepIt 0))
	(if
		(and
			(not (if temp1028 else temp1029))
			(> (- (theDialog nsBottom?) (theDialog nsTop?)) 100)
		)
		(temp1024 setSize: 300)
		(if temp1025
			(temp1025
				setSize: 300
				moveTo: (temp1024 nsLeft?) (+ MARGIN (temp1024 nsBottom?))
			)
		)
	)
	(if iconI
		(iconI moveTo: MARGIN MARGIN)
		(if
		(or (== temp1024 newDText) (== temp1025 newDText))
			(= temp1030 (* 2 MARGIN))
		)
		(if (& (iconI state?) $0010)
			(temp1024
				moveTo: (+ MARGIN temp1030) (+ (iconI nsBottom?) (temp1024 nsTop?))
				setSize:
			)
		else
			(temp1024
				moveTo: (+ 4 (iconI nsRight?) temp1030) (temp1024 nsTop?)
				setSize:
			)
		)
		(if temp1025
			(temp1025
				moveTo: (temp1024 nsLeft?) (+ 4 (temp1024 nsBottom?))
			)
		)
		(theDialog add: iconI)
	)
	(theDialog setSize:)
	(if editI
		(editI
			moveTo:
				((if temp1025 else temp1024) nsLeft?)
				(+ 4 ((if temp1025 else temp1024) nsBottom?))
		)
		(theDialog add: editI setSize:)
	)
	(= butAtX
		(if (> buttonWide (theDialog nsRight?))
			4
		else
			(- (theDialog nsRight?) buttonWide)
		)
	)
	(= i 0)
	(while (< i buttonsUsed)
		([buttons i] moveTo: butAtX (theDialog nsBottom?))
		(theDialog add: [buttons i])
		(= butAtX (+ 4 ([buttons i] nsRight?)))
		(++ i)
	)
	(theDialog setSize: center:)
	(if
		(or
			(and iconI (& (iconI state?) $0010))
			(and iconI (not (StrLen @buffer)))
		)
		(iconI
			moveTo:
				(/
					(-
						(- (theDialog nsRight?) (theDialog nsLeft?))
						(- (iconI nsRight?) (iconI nsLeft?))
					)
					2
				)
				4
		)
	)
	(theDialog
		moveTo:
			(if (== -1 atX) (theDialog nsLeft?) else atX)
			(if (== -1 atY) (theDialog nsTop?) else atY)
	)
	(= curPort (GetPort))
	(theDialog
		open: (if (theDialog text?) 4 else 0) (if keepIt 15 else -1)
	)
	(if keepIt
		(= modelessPort (GetPort))
		(SetPort curPort)
		(return (= modelessDialog keepIt))
	else
		(sounds pause: 1)
	)
	(if
		(and
			(= default (theDialog firstTrue: #checkState 1))
			(not (theDialog firstTrue: #checkState 2))
		)
		(default state: (| (default state?) $0002))
	)
	(if (== (= ret (theDialog doit: default)) -1)
		(= ret 0)
	)
	(= i 0)
	(while (< i buttonsUsed)
		(if (== ret [buttons i])
			(= ret (ret value?))
			(break)
		)
		(++ i)
	)
	(if (not (theDialog theItem?)) (= ret 1))
	(theDialog dispose:)
	(sounds pause: 0)
	(return ret)
)

(procedure (ShowView txt v l c)
	(Print txt #icon v l c &rest)
)

(procedure (GetInput str maxLen prompt &tmp theDialog editI ret oldPause)
	(if
		(Print
			(if (>= argc 3) prompt else {})
			#edit str maxLen
			&rest
		)
		(StrLen str)
	)
)

(procedure (GetNumber string default &tmp [theLine 40])
	(= theLine 0)
	(if (> argc 1) (Format @theLine INTRFACE 0 default))
	(return
		(if (GetInput @theLine 5 string)
			(ReadNumber @theLine)
		else
			-1
		)
	)
)

(procedure (Printf &tmp [str 500])
	(Format @str &rest)
	(Print @str)
)

(procedure (MousedOn obj event)
	(return
		(if
			(and
				(< (obj nsLeft?) (event x?))
				(< (event x?) (obj nsRight?))
				(< (obj nsTop?) (event y?))
			)
			(< (event y?) (obj nsBottom?))
		else
			FALSE
		)
	)
)

(procedure (StillDown &tmp event ret)
	(= ret (!= ((= event (Event new:)) type?) mouseUp))
	(event dispose:)
	(return ret)
)


(class MenuBar of Object
	(properties
		state $0000
	)
	
	(method (draw)
		(= state TRUE)
		(DrawMenuBar TRUE)
	)
	
	(method (hide)
		(DrawMenuBar FALSE)
	)
	
	(method (handleEvent event &tmp retVal oldRepeat)
		(= retVal 0)
		(if state
			(= oldRepeat (Joystick JoyRepeat 30))
			(= retVal (MenuSelect event &rest))
			(Joystick JoyRepeat oldRepeat)
		)
		(return retVal)
	)
	
	(method (add)
		(AddMenu &rest)
	)
)

(class DItem of Object
	(properties
		type 0
		state 0
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
	
	(method (enable bool)
		(if bool
			(= state (| state dActive))
		else
			(= state (& state (~ dActive)))
		)
	)
	
	(method (select bool)
		(if bool
			(= state (| state dSelected))
		else
			(= state (& state (~ dSelected)))
		)
		(self draw:)
	)
	
	(method (handleEvent event &tmp ret evtType evt)
		(if (event claimed?) (return 0))
		(= ret 0)
		(if
			(and
				(& state dActive)
				(or
					(and (== (= evtType (event type?)) saidEvent) (Said said))
					(and
						(== evtType keyDown)
						(== (event message?) key)
					)
					(and (== evtType mouseDown) (self check: event))
				)
			)
			(event claimed: TRUE)
			(= ret (self track: event))
		)
		(return ret)
	)
	
	(method (check event)
		(return
			(if
				(and
					(>= (event x?) nsLeft)
					(>= (event y?) nsTop)
					(< (event x?) nsRight)
				)
				(< (event y?) nsBottom)
			else
				0
			)
		)
	)
	
	(method (track event &tmp in lastIn)
		(return
			(if (== 1 (event type?))
				(= lastIn 0)
				(repeat
					((= event (Event new: leaveIt)) localize:)
					(if (!= (= in (self check: event)) lastIn)
						(HiliteControl self)
						(= lastIn in)
					)
					(event dispose:)
					(breakif (not (StillDown)))
				)
				(if in (HiliteControl self))
				(return in)
			else
				(return self)
			)
		)
	)
	
	(method (setSize)
	)
	
	(method (move h v)
		(= nsRight (+ nsRight h))
		(= nsLeft (+ nsLeft h))
		(= nsTop (+ nsTop v))
		(= nsBottom (+ nsBottom v))
	)
	
	(method (moveTo h v)
		(self move: (- h nsLeft) (- v nsTop))
	)
	
	(method (draw)
		(DrawControl self)
	)
	
	(method (isType theType)
		(return (== type theType))
	)
	
	(method (checkState bit)
		(return (& state bit))
	)
	
	(method (cycle)
	)
)

(class DText of DItem
	(properties
		type dText
		text 0
		font USERFONT
		mode teJustLeft
	)
	
	(method (new &tmp newText)
		((super new:) font: userFont yourself:)
	)
	
	(method (setSize w &tmp [r 4])
		(TextSize
			@[r 0] text font (if argc w else 0) {\0D\n----------\0D\n}
		)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft [r 3]))
	)
)

(class DIcon of DItem
	(properties
		type dIcon
		view 0
		loop 0
		cel 0
	)
	
	(method (setSize &tmp [r 4])
		(= nsRight (+ nsLeft (CelWide view loop cel)))
		(= nsBottom (+ nsTop (CelHigh view loop cel)))
	)
)

(class DButton of DItem
	(properties
		type dButton
		state (| dActive dExit)
		text 0
		font SYSFONT
	)
	
	(method (setSize &tmp [r 4])
		(TextSize @[r 0] text font 0 0)
		(= [r 2] (+ [r 2] 2))
		(= [r 3] (+ [r 3] 2))
		(= nsBottom (+ nsTop [r 2]))
		(= [r 3] (* (/ (+ [r 3] (- BMOD 1)) BMOD) BMOD))
		(= nsRight (+ [r 3] nsLeft))
	)
)

(class DEdit of DItem
	(properties
		type dEdit
		state dActive
		text 0
		font 0
		max 0
		cursor 0
	)
	
	(method (track evt)
		(EditControl self evt)
		(return self)
	)
	
	(method (setSize &tmp [r 4])
		(= font inputFont)		
		(TextSize @[r 0] {M} font 0 0)
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft (/ (* [r 3] max 3) 4)))
		(= cursor (StrLen text))
	)
)

(class DSelector of DItem
	(properties
		type dSelector
		state 0
		font SYSFONT
		x 20
		y 6
		text 0
		cursor 0
		topString 0
		mark 0
	)
	
	(method (handleEvent event &tmp ret evtType evt newEvt i [r 4])
		(if (event claimed?) (return FALSE))
		(= ret 0)
		(switch (event type?)
			(keyDown
				(event
					claimed:
						(switch (event message?)
							(HOMEKEY (self retreat: 50))
							(ENDKEY (self advance: 50))
							(PAGEDOWN
								(self advance: (- y 1))
							)
							(PAGEUP
								(self retreat: (- y 1))
							)
							(DOWNARROW (self advance: 1))
							(UPARROW (self retreat: 1))
							(else  FALSE)
						)
				)
			)
			(mouseDown
				(if (self check: event)
					(event claimed: TRUE)
					(cond 
						((< (event y?) (+ nsTop 10))
							(repeat
								(self retreat: 1)
								(breakif (not (StillDown)))
							)
						)
						((> (event y?) (- nsBottom 10))
							(repeat
								(self advance: 1)
								(breakif (not (StillDown)))
							)
						)
						(else
							(TextSize @[r 0] {M} font 0 0)
							(if
								(>
									(= i (/ (- (event y?) (+ nsTop 10)) [r 2]))
									mark
								)
								(self advance: (- i mark))
							else
								(self retreat: (- mark i))
							)
						)
					)
				)
			)
		)
		(return (if (and (event claimed?) (& state dExit)) self else 0))
	)
	
	(method (setSize &tmp [r 4])
		(TextSize @[r 0] {M} font 0 0)
		(= nsBottom (+ nsTop 20 (* [r 2] y)))
		(= nsRight (+ nsLeft (/ (* [r 3] x 3) 4)))
		(= topString (= cursor text))
		(= mark 0)
	)
	
	(method (indexOf what &tmp ptr i)
		(= ptr text)
		(= i 0)
		(return
			(while (< i 300)
				(if (== 0 (StrLen ptr)) (return -1))
				(if (not (StrCmp what ptr)) (return i))
				(= ptr (+ ptr x))
				(++ i)
			)
		)
	)
	
	(method (at what)
		(return (+ text (* x what)))
	)
	
	(method (advance lines &tmp redraw)
		(if (not (StrAt cursor 0))
			(return (not (StrAt cursor 0)))
		)
		(= redraw FALSE)
		(while (and lines (StrAt cursor x))
			(= redraw TRUE)
			(= cursor (+ cursor x))
			(if (< (+ mark 1) y)
				(++ mark)
			else
				(= topString (+ topString x))
			)
			(-- lines)
		)
		(return (if redraw (self draw:) 1 else 0))
	)
	
	(method (retreat lines &tmp redraw)
		(= redraw FALSE)
		(while (and lines (!= cursor text))
			(= redraw TRUE)
			(= cursor (- cursor x))
			(if mark (-- mark) else (= topString (- topString x)))
			(-- lines)
		)
		(return (if redraw (self draw:) 1 else 0))
	)
)

(class Dialog of List
	(properties
		text 0
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		busy 0
		caller 0
		seconds 0
		lastSeconds 0
	)
	
	(method (doit def &tmp done event ret eatTheMice lastTick)
		(= done 0)
		(= busy 1)
		(self eachElementDo: #init)
		(if theItem (theItem select: FALSE))
		(= theItem
			(if (and argc def)
				def
			else
				(self firstTrue: #checkState dActive)
			)
		)
		(if theItem (theItem select: TRUE))
		(if (not theItem)
			(= eatTheMice eatMice)
			(= lastTick (GetTime))
		else
			(= eatTheMice 0)
		)
		(= ret 0)
		(while (not ret)
			(self eachElementDo: #cycle)
			(= event ((Event new:) localize:))
			(if eatTheMice
				(-- eatTheMice)
				(if (== (event type?) mouseDown) (event type: 0))
				(while (== lastTick (GetTime))
				)
				(= lastTick (GetTime))
			)
			(= ret (self handleEvent: event))
			(event dispose:)
			(self check:)
			(if (or (== ret -1) (not busy))
				(= ret 0)
				(EditControl theItem 0)
				(break)
			)
			(Wait 1)
		)
		(= busy 0)
		(return ret)
	)
	
	(method (dispose &tmp theCaller)
		(if (== self modelessDialog)
			(SetPort modelessPort)
			(= modelessDialog 0)
			(= modelessPort 0)
		)
		(if window (window dispose:))
		(= theItem (= window 0))
		(= theCaller caller)
		(super dispose:)
		(if theCaller (theCaller cue:))
	)
	
	(method (open wType pri)
		(if (and (PicNotValid) cast)
			(Animate (cast elements?) FALSE)
		)
		(= window (window new:))
		(window
			top: nsTop
			left: nsLeft
			bottom: nsBottom
			right: nsRight
			title: text
			type: wType
			priority: pri
			open:
		)
		(= seconds time)
		(self draw:)
	)
	
	(method (draw)
		(self eachElementDo: #draw)
	)
	
	(method (cue)
		(if (not busy) (self dispose:) else (= busy 0))
	)
	
	(method (advance &tmp obj node)
		(if theItem
			(theItem select: FALSE)
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self next: node)))
					(= node (self first:))
				)
				(= theItem (NodeValue node))
				(if (& (theItem state?) dActive) (break))
			)
			(theItem select: TRUE)
			(theGame
				setCursor:
					theCursor
					TRUE
					(+
						(theItem nsLeft?)
						(/ (- (theItem nsRight?) (theItem nsLeft?)) 2)
					)
					(- (theItem nsBottom?) 3)
			)
		)
	)
	
	(method (retreat &tmp obj node)
		(if theItem
			(theItem select: FALSE)
			(= node (self contains: theItem))
			(repeat
				(if (not (= node (self prev: node)))
					(= node (self last:))
				)
				(= theItem (NodeValue node))
				(if (& (theItem state?) dActive) (break))
			)
			(theItem select: TRUE)
			(theGame
				setCursor:
					theCursor
					TRUE
					(+
						(theItem nsLeft?)
						(/ (- (theItem nsRight?) (theItem nsLeft?)) 2)
					)
					(- (theItem nsBottom?) 3)
			)
		)
	)
	
	(method (move h v)
		(= nsRight (+ nsRight h))
		(= nsLeft (+ nsLeft h))
		(= nsTop (+ nsTop v))
		(= nsBottom (+ nsBottom v))
	)
	
	(method (moveTo h v)
		(self move: (- h nsLeft) (- v nsTop))
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
	
	(method (setSize &tmp node obj [r 4])
		(if text
			(TextSize @[r 0] text 0 -1 0)
			(= nsTop [r 0])
			(= nsLeft [r 1])
			(= nsBottom [r 2])
			(= nsRight [r 3])
		else
			(= nsRight (= nsBottom (= nsLeft (= nsTop 0))))
		)
		(= node (self first:))
		(while node
			(if
			(< ((= obj (NodeValue node)) nsLeft?) nsLeft)
				(= nsLeft (obj nsLeft?))
			)
			(if (< (obj nsTop?) nsTop) (= nsTop (obj nsTop?)))
			(if (> (obj nsRight?) nsRight)
				(= nsRight (obj nsRight?))
			)
			(if (> (obj nsBottom?) nsBottom)
				(= nsBottom (obj nsBottom?))
			)
			(= node (self next: node))
		)
		(= nsRight (+ nsRight 4))
		(= nsBottom (+ nsBottom 4))
		(self moveTo: 0 0)
	)
	
	(method (handleEvent event &tmp ret)
		(if (& (event type?) direction)
			(event type: keyDown)
			(switch (event message?)
				(dirS
					(event message: DOWNARROW)
				)
				(dirN
					(event message: UPARROW)
				)
				(dirW
					(event message: LEFTARROW)
				)
				(dirE
					(event message: RIGHTARROW)
				)
				(else  (event type: direction))
			)
		)
		(if
			(or
				(event claimed?)
				(== (event type?) nullEvt)
				(and
					(!= mouseDown (event type?))
					(!= keyDown (event type?))
					(!= direction (event type?))
					(!= joyDown (event type?))
				)
			)
			(EditControl theItem event)
			(return FALSE)
		)
		(if
		(= ret (self firstTrue: #handleEvent event))
			(EditControl theItem 0)
			(if (not (ret checkState: dExit))
				(if theItem (theItem select: FALSE))
				((= theItem ret) select: TRUE)
				(ret doit:)
				(= ret 0)
			)
		else
			(= ret 0)
			(cond 
				(
					(and
						(or
							(== (event type?) joyDown)
							(and
								(== keyDown (event type?))
								(== ENTER (event message?))
							)
						)
						theItem
						(theItem checkState: dActive)
					)
					(= ret theItem)
					(EditControl theItem 0)
					(event claimed: TRUE)
				)
				(
					(or
						(and
							(not (self firstTrue: #checkState dActive))
							(or
								(and
									(== keyDown (event type?))
									(== ENTER (event message?))
								)
								(== mouseDown (event type?))
								(== joyDown (event type?))
							)
						)
						(and
							(== keyDown (event type?))
							(== ESC (event message?))
						)
					)
					(event claimed: TRUE)
					(= ret -1)
				)
				(
					(and
						(IsObject theItem)
						(theItem isType: dEdit)
						(== (event type?) keyDown)
						(== (event message?) RIGHTARROW)
					)
					(if
					(>= (theItem cursor?) (StrLen (theItem text?)))
						(self advance:)
					else
						(EditControl theItem event)
					)
				)
				(
					(and
						(IsObject theItem)
						(theItem isType: dEdit)
						(== (event type?) keyDown)
						(== (event message?) LEFTARROW)
					)
					(if (<= (theItem cursor?) 0)
						(self retreat:)
					else
						(EditControl theItem event)
					)
				)
				(
					(and
						(== keyDown (event type?))
						(OneOf (event message?) TAB RIGHTARROW DOWNARROW)
					)
					(event claimed: TRUE)
					(self advance:)
				)
				(
					(and
						(== keyDown (event type?))
						(OneOf (event message?) SHIFTTAB UPARROW LEFTARROW)
					)
					(event claimed: TRUE)
					(self retreat:)
				)
				(else (EditControl theItem event))
			)
		)
		(return ret)
	)
	
	(method (check &tmp thisSeconds)
		(if
			(and
				seconds
				(!= lastSeconds (= thisSeconds (GetTime 1)))
			)
			(= lastSeconds thisSeconds)
			(if (not (-- seconds)) (self cue:))
		)
	)
)

(class Controls of List
	(properties)
	
	(method (draw)
		(self eachElementDo: #setSize)
		(self eachElementDo: #draw)
	)
	
	(method (handleEvent evt &tmp cont)
		(if (evt claimed?) (return 0))
		(if
			(and
				(= cont (self firstTrue: #handleEvent evt))
				(not (cont checkState: dExit))
			)
			(cont doit:)
			(= cont 0)
		)
		(return cont)
	)
)
