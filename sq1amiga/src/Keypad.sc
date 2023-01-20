;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include sci.sh)
(use Main)
(use rm103)
(use Intrface)
(use Sound)
(use User)
(use Actor)
(use System)

(public
	raisePad 0
	lowerPad 1
	enterScript 2
	keyFlashScript 3
	quitScript 4
)

(local
	[buttons 10]
	[sectorNumber 10]
	saveBits
	local21
	wrongCodeCount
	[local23 17] = [185 180 175 170 165 160 155 150 145 140 135 130 125 120 115 -1]
)
(procedure (localproc_001e)
	(if saveBits
		(Display 400 0 108 saveBits)
		(= saveBits 0)
	)
)

(procedure (localproc_0034)
	(oneBut init: @buttons)
	(twoBut init: @buttons)
	(threeBut init: @buttons)
	(fourBut init: @buttons)
	(fiveBut init: @buttons)
	(sixBut init: @buttons)
	(sevenBut init: @buttons)
	(eightBut init: @buttons)
	(nineBut init: @buttons)
	(zeroBut init: @buttons)
	(enterBut init: whoToCue: (ScriptID 400 2))
	(quitBut init: whoToCue: quitScript)
)

(procedure (localproc_00d2)
	(oneBut dispose:)
	(twoBut dispose:)
	(threeBut dispose:)
	(fourBut dispose:)
	(fiveBut dispose:)
	(sixBut dispose:)
	(sevenBut dispose:)
	(eightBut dispose:)
	(nineBut dispose:)
	(zeroBut dispose:)
	(enterBut dispose:)
	(quitBut dispose:)
	(hand dispose:)
)

(instance raisePad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor 1 160 100)
				(keyPad y: 185 ignoreActors: init:)
				(soundFx number: 358 loop: 1 play:)
				(= cycles 1)
			)
			(1
				(if (!= [local23 (++ local21)] -1)
					(-- state)
					(keyPad y: [local23 local21] forceUpd:)
				)
				(= cycles 1)
			)
			(2
				(keyPad signal: (| (keyPad signal?) $0101))
				(soundFx stop:)
				(localproc_0034)
				(HandsOn)
				(theIconBar disable: 0 3 4 5)
				(client register: 1)
				(= cycles 1)
			)
			(3
				(hand
					init:
					hide:
					x: (+ (keyPad x?) 50)
					y: (+ (keyPad y?) 20)
				)
				(self dispose:)
			)
		)
	)
)

(instance lowerPad of Script
	(properties)
	
	(method (dispose)
		((ScriptID 49 2) cycles: 1)
		(beep dispose:)
		(super dispose:)
		(DisposeScript 400)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theGame setCursor: waitCursor 1 160 190)
				(keyPad signal: (& (keyPad signal?) $feff))
				(= local21 15)
				(soundFx number: 358 loop: 1 play:)
				(= cycles 6)
			)
			(1
				(localproc_00d2)
				(= cycles 2)
			)
			(2
				(if (>= (-- local21) 0)
					(-- state)
					(keyPad y: [local23 local21] forceUpd:)
				else
					(keyPad dispose:)
				)
				(= cycles 1)
			)
			(3
				(= buttons 0)
				(soundFx stop:)
				(self dispose:)
			)
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (dispose)
		(StrCpy @buttons {})
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(localproc_001e)
				(theGame setCursor: waitCursor 1 160 100)
				(keyPad signal: (& (keyPad signal?) $feff))
				(= cycles 1)
			)
			(1
				(cond 
					((not (StrLen @buttons)) (Print 400 1) (self dispose:))
					((< (StrLen @buttons) 4) (Print 400 2) (self dispose:))
					(else (= cycles 1))
				)
			)
			(2
				(switch deltaurSector
					(1
						(StrCpy @sectorNumber {GFCB})
					)
					(2
						(StrCpy @sectorNumber {DICJ})
					)
					(3
						(StrCpy @sectorNumber {JBEI})
					)
					(4
						(StrCpy @sectorNumber {EAEB})
					)
					(5
						(StrCpy @sectorNumber {IIBA})
					)
					(6
						(StrCpy @sectorNumber {EGHD})
					)
					(7
						(StrCpy @sectorNumber {CBBJ})
					)
					(8
						(StrCpy @sectorNumber {HFFH})
					)
					(9
						(StrCpy @sectorNumber {ICDE})
					)
					(10
						(StrCpy @sectorNumber {BIAA})
					)
					(11
						(StrCpy @sectorNumber {GHJB})
					)
					(12
						(StrCpy @sectorNumber {HAAD})
					)
					(13
						(StrCpy @sectorNumber {DAIB})
					)
					(14
						(StrCpy @sectorNumber {DDDG})
					)
					(15
						(StrCpy @sectorNumber {BACH})
					)
					(16
						(StrCpy @sectorNumber {ABHC})
					)
					(17
						(StrCpy @sectorNumber {GJDH})
					)
					(18
						(StrCpy @sectorNumber {CAHC})
					)
					(19
						(StrCpy @sectorNumber {BGBB})
					)
					(20
						(StrCpy @sectorNumber {IJFE})
					)
				)
				(cond 
					((== (StrCmp @buttons @sectorNumber) 0)
						(Print 400 3)
						(= shipDestination 2)
						((ScriptID 49 2) setScript: lowerPad)
						(= cycles 1)
					)
					((== (++ wrongCodeCount) 3)
						(= shipDestination 1)
						(Print 400 3)
						((ScriptID 49 2) setScript: lowerPad)
						(= cycles 1)
					)
					(else (Print 400 4) (= cycles 1))
				)
			)
			(3 (self dispose:))
		)
	)
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
				(= cycles 1)
			)
			(1
				(client setPri: 0)
				(Animate (cast elements?) 0)
				(= cycles 1)
			)
			(2
				(User canInput: 1)
				(if
				(and (client whoToCue?) (not (keyPad script?)))
					(keyPad setScript: (client whoToCue?))
				)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance quitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 49 2) setScript: lowerPad)
				(self dispose:)
			)
		)
	)
)

(class KeyPadButton of Prop
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
		description {button}
		sightAngle 26505
		actions 0
		onMeCheck $6789
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr {These buttons allow you to enter a code into the computer.}
		yStep 2
		view 0
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
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		detailLevel 0
		theString 0
		strToCat 0
		maxLen 4
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
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (self flash:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(if strToCat
			(if (< (StrLen theString) maxLen)
				(localproc_001e)
				(StrCat theString strToCat)
				(= saveBits
					(Display
						theString
						dsCOORD
						(+ (keyPad x?) 5)
						(+ (- (keyPad y?) 10) 20)
						dsCOLOR
						colLED
						dsALIGN
						0
						dsFONT
						30
						dsSAVEPIXELS
					)
				)
			else
				(Display
					theString
					dsCOORD
					(+ (keyPad x?) 5)
					(+ (- (keyPad y?) 10) 20)
					dsCOLOR
					colLED
					dsALIGN
					0
					dsFONT
					30
				)
			)
		)
	)
	
	(method (flash)
		(if (not script)
			(self setScript: keyFlashScript self tone)
		)
	)
)

(instance beep of Sound
	(properties)
)

(instance myCast of List
	(properties)
)

(class FastHand2 of View
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
		onMeCheck $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 26505
		lookStr 0
		yStep 2
		view 502
		loop 0
		cel 2
		priority 14
		underBits 0
		signal $0010
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
					(InRect 224 115 295 187 userCurEventX userCurEventY)
					(== (theIconBar curIcon?) (theIconBar at: 2))
				)
				(self internalEvent:)
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
	
	(method (internalEvent &tmp temp0 temp1 temp2)
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
code_0a54:
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
			pushi    224
			pushi    115
			pushi    295
			pushi    187
			lst      temp1
			push    
			calle    InRect,  12
			not     
			bnt      code_0ab8
			jmp      code_0b36
			jmp      code_0a54
code_0ab8:
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      5
			and     
			bnt      code_0afa
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			bt       code_0aed
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      1
			and     
			bnt      code_0a54
			pushi    #modifiers
			pushi    0
			lat      temp0
			send     4
			not     
			bnt      code_0a54
code_0aed:
			pushi    #handleEvent
			pushi    1
			lst      temp0
			class    User
			send     6
			jmp      code_0a54
code_0afa:
			lat      temp1
			aTop     x
			lat      temp2
			aTop     y
			pushi    #register
			pushi    0
			lofsa    keyFlashScript
			send     4
			bnt      code_0b1f
			pushi    #doit
			pushi    0
			lofsa    keyFlashScript
			send     4
			pushi    #eachElementDo
			pushi    1
			pushi    60
			lag      sounds
			send     6
code_0b1f:
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
			jmp      code_0a54
code_0b36:
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

(instance hand of FastHand2
	(properties)
)

(instance keyPad of Prop
	(properties
		x 238
		y 185
		z -20
		description {keypad}
		lookStr {Please enter a Navigational Code Sequence to allow course plotting.}
		view 502
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 7)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(theIconBar disable: 0)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(HandsOn)
	)
)

(instance enterBut of KeyPadButton
	(properties
		x 270
		y 139
		description {Enter}
		lookStr {This button confirms entry of the code you keyed-in.}
		view 502
		loop 1
		cel 11
		signal $4000
		tone 351
	)
)

(instance quitBut of KeyPadButton
	(properties
		x 248
		y 169
		description {Off}
		lookStr {Press this button if you want to turn the keypad off.}
		view 502
		loop 1
		cel 12
		signal $4000
		tone 352
	)
)

(instance oneBut of KeyPadButton
	(properties
		x 238
		y 139
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		signal $4000
		strToCat {A}
		tone 341
	)
)

(instance twoBut of KeyPadButton
	(properties
		x 248
		y 139
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 1
		signal $4000
		strToCat {B}
		tone 342
	)
)

(instance threeBut of KeyPadButton
	(properties
		x 258
		y 139
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 2
		signal $4000
		strToCat {C}
		tone 343
	)
)

(instance fourBut of KeyPadButton
	(properties
		x 238
		y 149
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 3
		signal $4000
		strToCat {D}
		tone 344
	)
)

(instance fiveBut of KeyPadButton
	(properties
		x 248
		y 149
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 4
		signal $4000
		strToCat {E}
		tone 345
	)
)

(instance sixBut of KeyPadButton
	(properties
		x 258
		y 149
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 5
		signal $4000
		strToCat {F}
		tone 346
	)
)

(instance sevenBut of KeyPadButton
	(properties
		x 238
		y 159
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 6
		signal $4000
		strToCat {G}
		tone 347
	)
)

(instance eightBut of KeyPadButton
	(properties
		x 248
		y 159
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 7
		signal $4000
		strToCat {H}
		tone 348
	)
)

(instance nineBut of KeyPadButton
	(properties
		x 258
		y 159
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 8
		signal $4000
		strToCat {I}
		tone 349
	)
)

(instance zeroBut of KeyPadButton
	(properties
		x 238
		y 169
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 9
		signal $4000
		strToCat {J}
		tone 350
	)
)
