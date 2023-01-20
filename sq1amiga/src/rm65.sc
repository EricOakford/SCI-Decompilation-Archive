;;; Sierra Script 1.0 - (do not remove this comment)
(script# 65)
(include sci.sh)
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
(procedure (localproc_0055)
	(= saveBits
		(Display
			65
			0
			dsCOORD
			100
			42
			dsCOLOR
			colLYellow
			dsALIGN
			0
			dsFONT
			2
			dsSAVEPIXELS
		)
	)
)

(instance rm65 of SQRoom
	(properties
		lookStr {This is the control keypad, which is used to input instructions to the Star Generator.}
		picture 65
		style $0007
	)
	
	(method (init &tmp temp0)
		(LoadMany
			132
			358
			340
			341
			342
			343
			344
			345
			346
			347
			348
			349
			350
			351
			352
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
		(myCast add:)
		(HandsOn)
		(theIconBar disable: 0 4 5)
		(localproc_0055)
	)
	
	(method (dispose)
		(myCast release: dispose:)
		(super dispose: &rest)
	)
)

(instance myCast of List
	(properties)
)

(class FastHand3 of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
	)
	
	(method (doit &tmp userCurEvent userCurEventX userCurEventY)
		(if
			(not
				(& ((= userCurEvent (User curEvent?)) type?) $0007)
			)
			(userCurEvent localize:)
			(= userCurEventX (userCurEvent x?))
			(= userCurEventY (userCurEvent y?))
			(if
				(and
					(InRect 123 60 198 136 userCurEventX userCurEventY)
					(not (curRoom script?))
					(== (theIconBar curIcon?) (theIconBar at: 2))
				)
				(self internalizeEvent:)
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (== theCursor 69)
			(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
		)
		(super dispose:)
	)
	
	(method (internalizeEvent &tmp temp0 temp1 temp2)
		(asm
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			sat      temp0
			pushi    #add
			pushi    1
			pushSelf
			lofsa    myCast
			send     6
			pushi    #setCursor
			pushi    2
			pushi    69
			pushi    1
			lag      theGame
			send     8
			pushi    #show
			pushi    0
			pushi    4
			pushi    1
			lst      temp1
			pushi    3
			pushi    1
			lst      temp2
			self     16
code_021c:
			pushi    #type
			pushi    1
			pushi    0
			pushi    40
			pushi    1
			pushi    0
			pushi    64
			pushi    1
			pushi    0
			pushi    3
			pushi    1
			pushi    0
			pushi    4
			pushi    1
			pushi    0
			pushi    76
			pushi    1
			pushi    0
			pushi    141
			pushi    1
			pushi    0
			lat      temp0
			send     42
			pushi    2
			pushi    32767
			lst      temp0
			callk    GetEvent,  4
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			pushi    #x
			pushi    0
			lat      temp0
			send     4
			sat      temp1
			pushi    #y
			pushi    0
			lat      temp0
			send     4
			sat      temp2
			pushi    6
			pushi    123
			pushi    60
			pushi    198
			pushi    136
			lst      temp1
			push    
			calle    InRect,  12
			not     
			bnt      code_027f
			jmp      code_032e
			jmp      code_021c
code_027f:
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      5
			and     
			bnt      code_02c1
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			bt       code_02b4
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      1
			and     
			bnt      code_021c
			pushi    #modifiers
			pushi    0
			lat      temp0
			send     4
			not     
			bnt      code_021c
code_02b4:
			pushi    #handleEvent
			pushi    1
			lst      temp0
			class    User
			send     6
			jmp      code_021c
code_02c1:
			lat      temp1
			aTop     x
			lat      temp2
			aTop     y
			pushi    #register
			pushi    0
			lofsa    keyFlashScript
			send     4
			bnt      code_02e6
			pushi    #doit
			pushi    0
			lofsa    keyFlashScript
			send     4
			pushi    #eachElementDo
			pushi    1
			pushi    60
			lag      sounds
			send     6
code_02e6:
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			dup     
			lofsa    quitScript
			eq?     
			bnt      code_02fd
			jmp      code_032e
			jmp      code_0316
code_02fd:
			dup     
			lofsa    EnterScript
			eq?     
			bnt      code_0316
			pushi    #doit
			pushi    0
			lofsa    EnterScript
			send     4
			pushi    #eachElementDo
			pushi    1
			pushi    60
			lag      sounds
			send     6
code_0316:
			toss    
			pushi    2
			pushi    #elements
			pushi    0
			lofsa    myCast
			send     4
			push    
			pushi    0
			callk    Animate,  4
			pushi    #doit
			pushi    0
			super    View,  4
			jmp      code_021c
code_032e:
			pushi    #setCursor
			pushi    2
			pushi    #cursor
			pushi    0
			pushi    #curIcon
			pushi    0
			lag      theIconBar
			send     4
			send     4
			push    
			pushi    1
			lag      theGame
			send     8
			pushi    #hide
			pushi    0
			self     4
			pushi    #delete
			pushi    1
			pushSelf
			lofsa    myCast
			send     6
			ret     
		)
	)
)

(instance hand of FastHand3
	(properties
		view 502
		cel 2
		priority 15
		signal $0010
	)
)

(class PadButton of Prop
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 0
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		description 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 165
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		palette 0
		cycleSpeed 6
		script 0
		cycler 0
		timer 0
		detailLevel 0
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
				(evKEYBOARD
					(cond 
						(
							(and
								(== (event message?) KEY_RETURN)
								(InRect nsLeft nsTop nsRight nsBottom event)
							)
							(event claimed: 1)
							(self flash:)
						)
						((== (event message?) keyEquiv) (event claimed: 1) (self flash:))
					)
				)
				(evMOUSEBUTTON
					(if (MousedOn self event)
						(event claimed: 1)
						(self flash:)
					)
				)
			)
		else
			(event claimed: 1)
		)
	)
	
	(method (cue)
		(if strToCat
			(if (>= (StrLen theString) 4) (StrCpy theString {}))
			(StrCat theString strToCat)
			(if saveBits2 (Display 65 1 108 saveBits2))
			(= saveBits2
				(Display
					theString
					dsCOORD
					161
					42
					dsCOLOR
					colLED
					dsALIGN
					0
					dsFONT
					2
					dsSAVEPIXELS
				)
			)
		)
	)
	
	(method (flash)
		(if (not (curRoom script?))
			(self setScript: keyFlashScript self tone)
		)
	)
)

(instance enterBut of PadButton
	(properties
		x 160
		y 73
		cel 1
		signal $4000
		tone 351
	)
)

(instance quitBut of PadButton
	(properties
		x 248
		y 169
		view 502
		loop 3
		signal $4000
		tone 352
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(and
						(== (event type?) evKEYBOARD)
						(OneOf (event message?) 88 120)
					)
					(MousedOn self event)
					(InRect nsLeft nsTop nsRight nsBottom event)
				)
				(event claimed: 1)
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
		signal $4000
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
		signal $4000
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
		signal $4000
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
		signal $4000
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
		signal $4000
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
		signal $4000
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
		signal $4000
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
		signal $4000
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
		signal $4000
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
					(HandsOff)
					(= cycles 3)
				)
			)
			(1
				(Display 65 1 108 saveBits2)
				(Display 65 1 108 saveBits)
				(= temp0 (ReadNumber @theButtons))
				(= theButtons 0)
				(if (== temp0 selfDestructCode)
					(= register 1)
					(Bset 53)
				)
				(= cycles 30)
			)
			(2
				(self setScript: flashText self (if register 1 else 0))
			)
			(3
				(if (not register)
					(HandsOn)
					(theIconBar disable: 0 4 5)
					(localproc_0055)
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
					(if (not (Btst 172))
						(theMusic2 number: 626 loop: -1 play:)
					)
					(= saveBits2
						(Display
							65
							2
							dsCOORD
							107
							42
							dsCOLOR
							colLYellow
							dsALIGN
							0
							dsFONT
							2
							dsSAVEPIXELS
						)
					)
					(SolvePuzzle 10 172)
				else
					(theMusic2 number: 521 loop: 1 play:)
					(= saveBits2
						(Display
							65
							3
							dsCOORD
							111
							42
							dsCOLOR
							colLYellow
							dsALIGN
							0
							dsFONT
							2
							dsSAVEPIXELS
						)
					)
				)
				(= cycles 15)
			)
			(1
				(if (not register) (theMusic2 stop:))
				(Display 65 1 108 saveBits2)
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
						(Display
							65
							4
							dsCOORD
							125
							42
							dsCOLOR
							colLYellow
							dsALIGN
							0
							dsFONT
							2
							dsSAVEPIXELS
						)
					)
					(= cycles 15)
				else
					(self dispose:)
				)
			)
			(4
				(Display 65 1 108 saveBits2)
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
				(User canInput: 0)
				(client setPri: 11)
				(Animate (cast elements?) 0)
				(if register (beep number: register play:))
				(= cycles 3)
			)
			(1
				(client setPri: 0)
				(= cycles 2)
			)
			(2
				(if (client whoToCue?)
					(curRoom setScript: (client whoToCue?))
				)
				(= register 0)
				(User canInput: 1)
				(client setScript: 0)
			)
		)
	)
)

(instance quitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 4))
			(1 (curRoom newRoom: 64))
		)
	)
)
