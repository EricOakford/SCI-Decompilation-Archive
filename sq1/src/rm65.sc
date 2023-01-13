;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use LoadMany)
(use Sound)
(use User)
(use Actor)
(use System)

(public
	rm65 0
)

(local
	saveBits
	[theButtons 4]
	saveBits2
	local6
)
(procedure (DisplayEnterCode)
	(= saveBits
		(Display 65 0
			p_at 100 42
			p_color colLYellow
			p_mode teJustLeft
			p_font 2
			p_save
		)
	)
)

(instance rm65 of SQRoom
	(properties
		lookStr {This is the control keypad, which is used to input instructions to the Star Generator.}
		picture 65
		style IRISOUT
	)
	
	(method (init &tmp temp0)
		(LoadMany SOUND
			358 340 341 342 343 344 345 346 347 348 349
			350 351 352
		)
		(oneBut init: @theButtons)
		(twoBut init: @theButtons)
		(threeBut init: @theButtons)
		(fourBut init: @theButtons)
		(fiveBut init: @theButtons)
		(sixBut init: @theButtons)
		(sevenBut init: @theButtons)
		(eightBut init: @theButtons)
		(nineBut init: @theButtons)
		(enterBut init: whoToCue: EnterScript)
		(quitBut init: whoToCue: quitScript)
		(hand init: hide: x: 150 y: 70)
		(super init:)
		(HandsOn)
		(theIconBar disable: ICON_WALK ICON_SMELL ICON_TASTE)
		(DisplayEnterCode)
	)
)

(instance hand of View
	(properties
		view 502
		cel 2
		priority 15
		signal fixPriOn
	)
	
	(method (doit &tmp event evtX evtY)
		(if
			(not
				(& ((= event (User curEvent?)) type?) (| mouseDown mouseUp keyDown))
			)
			(event localize:)
			(= evtX (event x?))
			(= evtY (event y?))
			(cond 
				((InRect 123 60 198 136 evtX evtY)
					(theGame setCursor: 69 TRUE)
					(self show: x: evtX y: evtY)
				)
				((== theCursor 69)
					(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
					(self hide:)
				)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (== theCursor 69)
			(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		)
		(super dispose: &rest)
	)
)

(class PadButton of Prop
	(properties
		view 165
		cycleSpeed 6
		theString 0
		strToCat 0
		keyEquiv 0
		maxLen 8
		tone 0
		whoToCue 0
	)
	
	(method (init theTheString)
		(super init:)
		(self setPri: 0)
		(= theString theTheString)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (User controls?)
			(switch (event type?)
				(keyDown
					(cond 
						(
							(and
								(== (event message?) ENTER)
								(InRect nsLeft nsTop nsRight nsBottom event)
							)
							(event claimed: TRUE)
							(self flash:)
						)
						((== (event message?) keyEquiv) (event claimed: TRUE) (self flash:))
					)
				)
				(mouseDown
					(if (MousedOn self event)
						(event claimed: TRUE)
						(self flash:)
					)
				)
			)
		else
			(event claimed: TRUE)
		)
	)
	
	(method (flash)
		(self setScript: (Clone keyFlashScript) 0 tone)
		(if strToCat
			(if (>= (StrLen theString) 4) (StrCpy theString {}))
			(StrCat theString strToCat)
			(if saveBits2 (Display 65 1 p_restore saveBits2))
			(= saveBits2
				(Display theString
					p_at 161 42
					p_color colLED
					p_mode teJustLeft
					p_font 2
					p_save
				)
			)
		)
	)
)

(instance enterBut of PadButton
	(properties
		x 160
		y 73
		cel 1
		signal ignrAct
		tone 351
	)
)

(instance quitBut of PadButton
	(properties
		x 248
		y 169
		view 502
		loop 3
		signal ignrAct
		tone 352
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(and
						(== (event type?) keyDown)
						(OneOf (event message?) 88 120)
					)
					(MousedOn self event)
					(InRect nsLeft nsTop nsRight nsBottom event)
				)
				(event claimed: TRUE)
				(self flash:)
			)
		)
	)
)

(instance oneBut of PadButton
	(properties
		x 136
		y 99
		loop 1
		cel 1
		signal ignrAct
		strToCat {1}
		keyEquiv 49
		tone 341
	)
)

(instance twoBut of PadButton
	(properties
		x 161
		y 99
		loop 2
		cel 1
		signal ignrAct
		strToCat {2}
		keyEquiv 50
		tone 342
	)
)

(instance threeBut of PadButton
	(properties
		x 186
		y 99
		loop 3
		cel 1
		signal ignrAct
		strToCat {3}
		keyEquiv 51
		tone 343
	)
)

(instance fourBut of PadButton
	(properties
		x 136
		y 116
		loop 4
		cel 1
		signal ignrAct
		strToCat {4}
		keyEquiv 52
		tone 344
	)
)

(instance fiveBut of PadButton
	(properties
		x 161
		y 116
		loop 5
		cel 1
		signal ignrAct
		strToCat {5}
		keyEquiv 53
		tone 345
	)
)

(instance sixBut of PadButton
	(properties
		x 186
		y 116
		loop 6
		cel 1
		signal ignrAct
		strToCat {6}
		keyEquiv 54
		tone 346
	)
)

(instance sevenBut of PadButton
	(properties
		x 136
		y 133
		loop 7
		cel 1
		signal ignrAct
		strToCat {7}
		keyEquiv 55
		tone 347
	)
)

(instance eightBut of PadButton
	(properties
		x 161
		y 133
		loop 8
		cel 1
		signal ignrAct
		strToCat {8}
		keyEquiv 56
		tone 348
	)
)

(instance nineBut of PadButton
	(properties
		x 186
		y 133
		loop 9
		cel 1
		signal ignrAct
		strToCat {9}
		keyEquiv 57
		tone 349
	)
)

(instance EnterScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(if (not (StrLen @theButtons))
					(self dispose:)
				else
					(= cycles 3)
				)
			)
			(1
				(HandsOff)
				(Display 65 1 p_restore saveBits2)
				(Display 65 1 p_restore saveBits)
				(= temp0 (ReadNumber @theButtons))
				(= theButtons 0)
				(if (== temp0 selfDestructCode) (= register 1) (Bset fStartedSelfDestruct))
				(= cycles 30)
			)
			(2
				(self setScript: flashText self (if register 1 else 0))
			)
			(3
				(if (not register)
					(HandsOn)
					(theIconBar disable: ICON_WALK ICON_SMELL ICON_TASTE)
					(DisplayEnterCode)
					(self dispose:)
				else
					(= register 0)
					(= cycles 9)
				)
			)
			(4 (curRoom newRoom: 64))
		)
	)
)

(instance flashText of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(= local6 0)
	)
	
	(method (dispose)
		(= saveBits2 0)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(if (not (Btst fSelfDestructPoints))
						(theMusic2 number: 626 loop: -1 play:)
					)
					(= saveBits2
						(Display 65 2
							p_at 107 42
							p_color colLYellow
							p_mode teJustLeft
							p_font 2
							p_save
						)
					)
					(SolvePuzzle 10 fSelfDestructPoints)
				else
					(theMusic2 number: 521 loop: 1 play:)
					(= saveBits2
						(Display 65 3
							p_at 111 42
							p_color colLYellow
							p_mode teJustLeft
							p_font 2
							p_save
						)
					)
				)
				(= cycles 15)
			)
			(1
				(if (not register) (theMusic2 stop:))
				(Display 65 1 p_restore saveBits2)
				(= cycles 6)
			)
			(2
				(if (<= (++ local6) 3)
					(self changeState: 0)
				else
					(= local6 0)
					(self changeState: 3)
				)
			)
			(3
				(if register
					(theMusic2 number: 619 loop: 1 play:)
					(= saveBits2
						(Display 65 4
							p_at 125 42
							p_color colLYellow
							p_mode teJustLeft
							p_font 2
							p_save
						)
					)
					(= cycles 15)
				else
					(self dispose:)
				)
			)
			(4
				(Display 65 1 p_restore saveBits2)
				(= cycles 6)
			)
			(5
				(if (<= (++ local6) 3)
					(self changeState: 3)
				else
					(= register 0)
					(self dispose:)
				)
			)
		)
	)
)

(instance beep of Sound
	(properties)
)

(instance keyFlashScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setPri: 11)
				(if register
					(beep number: register play:)
					(= register 0)
				)
				(= cycles 6)
			)
			(1
				(client setPri: 0)
				(= cycles 3)
			)
			(2
				(if (client whoToCue?)
					(curRoom setScript: (client whoToCue?))
				)
				(client setScript: 0)
			)
		)
	)
)

(instance quitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom newRoom: 64)
			)
		)
	)
)
