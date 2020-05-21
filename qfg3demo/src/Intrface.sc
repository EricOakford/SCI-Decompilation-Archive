;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTRFACE)
(include game.sh)
(use Main)
(use Print)
(use System)

(public
	StillDown 0
	GetNumber 1
	MousedOn 2
)

(procedure (StillDown &tmp event ret)
	(= ret (!= ((= event (Event new:)) type?) mouseUp))
	(event dispose:)
	(return ret)
)

(procedure (GetNumber string default &tmp [theLine 40])
	(= theLine 0)
	(if (> argc 1) (Format @theLine {%d} default))
	(return
		(if (GetInput @theLine 5 string)
			(ReadNumber @theLine)
		else
			-1
		)
	)
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
			0
		)
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
	
	(method (dispose isNotPtr)
		(if (and text (or (not argc) (not isNotPtr)))
			(Memory MDisposePtr (self text?))
		)
		(super dispose:)
	)
	
	(method (setSize w &tmp [r 4])
		(TextSize @[r 0] text font (if argc w else 0) {\0D\n----------\0D\n})
		(= nsBottom (+ nsTop [r 2]))
		(= nsRight (+ nsLeft [r 3]))
	)
)

(class Dialog of List
	(properties
		text 0
		font 0
		window 0
		theItem 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		time 0
		caller 0
		seconds 0
		lastSeconds 0
		eatTheMice 0
		lastTicks 0
	)
	
	(method (doit def &tmp event ret done)
		(= gameTime (+ tickOffset (GetTime)))
		(= done 0)
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
			(= lastTicks (GetTime))
		else
			(= eatTheMice 0)
		)
		(= ret 0)
		(while (not ret)
			(= gameTime (+ tickOffset (GetTime)))
			(self eachElementDo: #cycle)
			(= event ((Event new:) localize:))
			(if eatTheMice
				(-- eatTheMice)
				(if (== (event type?) mouseDown) (event type: 0))
				(while (== lastTicks (GetTime))
				)
				(= lastTicks (GetTime))
			)
			(= ret (self handleEvent: event))
			(event dispose:)
			(if (self check:) (break))
			(if (== ret -2)
				(= ret 0)
				(EditControl theItem 0)
				(break)
			)
			(Wait 1)
		)
		(return ret)
	)
	
	(method (dispose &tmp theCaller)
		(self eachElementDo: #dispose release:)
		(if (== self modelessDialog)
			(SetPort modelessPort)
			(= modelessDialog 0)
			(= modelessPort 0)
		)
		(if window (window dispose:) (= window 0))
		(= theItem 0)
		(= theCaller caller)
		(super dispose:)
		(if theCaller (theCaller cue:))
	)
	
	(method (open wtype pri)
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
			type: wtype
			priority: pri
			open:
		)
		(= seconds time)
		(self draw:)
	)
	
	(method (draw)
		(self eachElementDo: #draw)
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
			(TextSize @[r 0] text font -1 NULL)
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
		(= nsRight (+ nsRight MARGIN))
		(= nsBottom (+ nsBottom MARGIN))
		(self moveTo: 0 0)
	)
	
	(method (handleEvent event &tmp ret eType eMsg)
		(= eType (event type?))
		(= eMsg (event message?))
		(if (& eType direction)
			(switch eMsg
				(dirS
					(event type: keyDown message: DOWNARROW)
				)
				(dirN
					(event type: keyDown message: UPARROW)
				)
				(dirW
					(event type: keyDown message: LEFTARROW)
				)
				(dirE
					(event type: keyDown message: RIGHTARROW)
				)
			)
		)
		(if (= ret (self firstTrue: #handleEvent event))
			(EditControl theItem 0)
			(if (not (ret checkState: dExit))
				(if theItem (theItem select: FALSE))
				((= theItem ret) select: TRUE)
				(ret doit:)
				(= ret 0)
			else
				(return ret)
			)
		else
			(= ret 0)
			(cond 
				(
					(and
						(or
							(== eType joyDown)
							(and
								(== eType keyDown)
								(== eMsg ENTER)
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
					(and
						(== eType keyDown)
						(== eMsg ESC)
					)
					(event claimed: TRUE)
					(= ret -1)
				)
				(
					(and
						(not (self firstTrue: #checkState dActive))
						(or
							(and
								(== eType keyDown)
								(== eMsg ENTER)
							)
							(OneOf eType mouseDown joyDown)
						)
					)
					(event claimed: TRUE)
					(= ret -2)
				)
				(
					(and
						(IsObject theItem)
						(theItem isType: dEdit)
						(== eType keyDown)
						(== eMsg RIGHTARROW)
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
						(== eType keyDown)
						(OneOf eMsg TAB RIGHTARROW DOWNARROW)
					)
					(event claimed: 1)
					(self advance:)
				)
				(
					(and
						(== eType keyDown)
						(OneOf eMsg SHIFTTAB LEFTARROW UPARROW)
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
		(return
			(if
				(and
					seconds
					(!= lastSeconds (= thisSeconds (GetTime SYSTIME1)))
				)
				(= lastSeconds thisSeconds)
				(return (not (-- seconds)))
			else
				0
			)
		)
	)
)
