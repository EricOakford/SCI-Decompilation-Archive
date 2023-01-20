;;; Sierra Script 1.0 - (do not remove this comment)
(script# 103)
(include sci.sh)
(use Main)
(use Intrface)
(use Arcada)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm103 0
)

(local
	[theButtons 10]
	[cartCode 11]
	local21
	[local22 17] = [185 180 175 170 165 160 155 150 145 140 135 130 125 120 115 -1]
)
(procedure (localproc_000e)
	(if ((ScriptID 700 0) saveBits?)
		(Display 103 0 108 ((ScriptID 700 0) saveBits?))
		((ScriptID 700 0) saveBits: 0)
	)
)

(procedure (localproc_0048)
	(oneBut init: @theButtons stopUpd:)
	(twoBut init: @theButtons)
	(threeBut init: @theButtons)
	(fourBut init: @theButtons)
	(fiveBut init: @theButtons)
	(sixBut init: @theButtons)
	(sevenBut init: @theButtons)
	(eightBut init: @theButtons stopUpd:)
	(nineBut init: @theButtons stopUpd:)
	(zeroBut init: @theButtons stopUpd:)
	(enterBut init: whoToCue: EnterScript stopUpd:)
	(quitBut init: whoToCue: lowerPad stopUpd:)
	(hand
		init:
		hide:
		x: (+ (keyPad x?) 50)
		y: (+ (keyPad y?) 20)
	)
)

(procedure (localproc_0124)
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

(instance rm103 of Rm
	(properties
		picture 103
	)
	
	(method (init)
		(Load rsPIC 102)
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
		(self setRegions: 700)
		(super init:)
		(myCast add:)
		(if (!= (theMusic number?) 355)
			(theMusic2 number: 314 loop: -1 play:)
		)
		(egoHead init:)
		(egoArm init:)
		(exitRoom init:)
		(if (ArcadaCheck 551 16)
			((ScriptID 700 0)
				rFlag1: (& ((ScriptID 700 0) rFlag1?) $ffef)
			)
			((ScriptID 700 0)
				rFlag1: (| ((ScriptID 700 0) rFlag1?) $0020)
			)
			(droid init: stopUpd:)
			(droidArm init: stopUpd:)
		)
		(features
			add: monitor console controlPanel lights egoFeat buttons
			eachElementDo: #init
		)
		(HandsOn)
	)
	
	(method (dispose)
		(myCast release: dispose:)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(if (cast contains: keyPad)
					(Print 103 1)
				else
					(Print 103 2)
				)
			)
			(4
				(switch theItem
					(0
						(if (cast contains: keyPad)
							(Print 103 3)
						else
							(super doVerb: theVerb theItem &rest)
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance myCast of List
	(properties)
)

(instance getTheCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1 (curRoom newRoom: 3))
		)
	)
)

(instance returnToComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cast eachElementDo: #show)
				(if (cast contains: droidArm)
					(theMusic2 number: 353 loop: -1 play:)
				)
				(curRoom drawPic: 103 10)
				(= cycles 2)
			)
			(1 (self dispose:))
		)
	)
)

(instance lookAtMonitor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 stop: loop: 0)
				(cast eachElementDo: #hide)
				(curRoom drawPic: 2)
				(curRoom overlay: 102)
				(= cycles 3)
			)
			(1
				(self setScript: raisePad self)
			)
			(2 (self dispose:))
		)
	)
)

(instance egoGrabCart of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(droidArm setCycle: CT 1 1 self)
			)
			(1
				(egoArm
					setLoop: 4
					cel: 0
					setPri: (+ (droidArm priority?) 1)
					setCycle: CT 2 1 self
				)
			)
			(2
				(soundFx number: 303 loop: 1 play:)
				((ScriptID 700 0)
					rFlag1: (& ((ScriptID 700 0) rFlag1?) $ffdf)
				)
				(droidArm setCycle: End self)
				(egoArm setCycle: End)
				(ego get: 0)
			)
			(3
				(client setScript: getTheCart)
			)
		)
	)
)

(instance EnterScript of Script
	(properties)
	
	(method (dispose)
		(StrCpy @theButtons {})
		(super dispose:)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(localproc_000e)
				(if (not (StrLen @theButtons))
					(Print 103 4)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(1
				(switch ((ScriptID 700 0) cartNumber?)
					(1 (StrCpy @cartCode {BHCA}))
					(2 (StrCpy @cartCode {BCHE}))
					(3 (StrCpy @cartCode {BHBH}))
					(4 (StrCpy @cartCode {FCFD}))
					(5 (StrCpy @cartCode {HDCH}))
					(6 (StrCpy @cartCode {GECB}))
					(7 (StrCpy @cartCode {EEJF}))
					(8 (StrCpy @cartCode {CAED}))
					(9 (StrCpy @cartCode {BACI}))
					(10 (StrCpy @cartCode {EFGI}))
					(11 (StrCpy @cartCode {IBED}))
					(12 (StrCpy @cartCode {GFDJ}))
					(13 (StrCpy @cartCode {JAAI}))
					(14 (StrCpy @cartCode {DDHF}))
					(15 (StrCpy @cartCode {IJBD}))
					(16 (StrCpy @cartCode {ICCH}))
					(17 (StrCpy @cartCode {DGFJ}))
					(18 (StrCpy @cartCode {FCJG}))
					(19 (StrCpy @cartCode {FIFI}))
					(20 (StrCpy @cartCode {AJJG}))
				)
				(if
					(and
						(not (ArcadaCheck 551 32))
						(not (ego has: 0))
						(== (StrCmp @theButtons @cartCode) 0)
					)
					(HandsOff)
					(= cycles 1)
					(theGame setCursor: waitCursor 1 160 190)
				else
					(self changeState: 4)
				)
			)
			(2
				(soundFx number: 305 loop: 1 play: self)
			)
			(3
				(Print 103 5)
				((ScriptID 700 0)
					rFlag1: (| ((ScriptID 700 0) rFlag1?) $0010)
				)
				(curRoom newRoom: 3)
			)
			(4
				(soundFx number: 306 loop: 1 play:)
				(= cycles 5)
			)
			(5
				(if (== (StrCmp @theButtons @cartCode) 0)
					(Print 103 6)
				else
					(switch (Random 0 4)
						(0 (Print 103 6))
						(1 (Print 103 7))
						(2 (Print 103 8))
						(3 (Print 103 9))
						(4 (Print 103 10))
					)
				)
				(if
					(and
						(not (ego has: 0))
						(> selfDestructTimer 30)
						(< (= selfDestructTimer (- selfDestructTimer 120)) 30)
					)
					(= selfDestructTimer 30)
				)
				(self dispose:)
			)
		)
	)
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
				(if (!= [local22 (++ local21)] -1)
					(-- state)
					(keyPad y: [local22 local21] forceUpd:)
				)
				(= cycles 1)
			)
			(2
				(keyPad signal: (| (keyPad signal?) $0100) stopUpd:)
				(soundFx stop:)
				(localproc_0048)
				(HandsOn)
				(theIconBar disable: 0 3 4 5)
				(self dispose:)
			)
		)
	)
)

(instance lowerPad of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(localproc_0124)
				(keyPad signal: (& (keyPad signal?) $feff))
				(= local21 15)
				(soundFx number: 358 loop: 1 play:)
				(= cycles 1)
			)
			(1
				(if (>= (-- local21) 0)
					(-- state)
					(keyPad y: [local22 local21] forceUpd:)
				else
					(keyPad dispose:)
				)
				(= cycles 1)
			)
			(2
				(= theButtons 0)
				(keyPad dispose:)
				(soundFx stop:)
				(theIconBar enable:)
				(HandsOn)
				(client setScript: returnToComputer)
			)
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
				(and (client whoToCue?) (not (curRoom script?)))
					(curRoom setScript: (client whoToCue?))
				)
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance pushButtons of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(egoArm setLoop: 3 cel: 0 setCycle: Fwd)
				(= cycles (Random 10 20))
			)
			(1
				(Print 103 11)
				(egoArm setCycle: Beg)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoHead of Prop
	(properties
		x 99
		y 67
		lookStr {This is your head. (Didn't you learn this stuff a real long time ago?)}
		view 203
		loop 1
	)
)

(instance egoArm of Prop
	(properties
		x 113
		y 78
		lookStr {This is your arm.}
		view 203
		loop 3
	)
	
	(method (init)
		(super init: &rest)
		(self stopUpd:)
	)
)

(instance exitRoom of Prop
	(properties
		x 281
		y 183
		description {exitRoom}
		lookStr {This button allows you to exit this screen.}
		view 502
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(self cel: 1)
				(Animate (cast elements?) 0)
				(Animate (cast elements?) 0)
				(Animate (cast elements?) 0)
				(curRoom newRoom: 3)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance droidArm of Prop
	(properties
		description {droid arm}
		lookStr {You see a data cartridge clasped in the droid's manipulator.}
		view 203
		loop 6
	)
	
	(method (init)
		(self x: (- (droid x?) 21) y: (- (droid y?) 37))
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(SolvePuzzle 5 130)
				(curRoom setScript: egoGrabCart)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance droid of View
	(properties
		x 238
		y 70
		description {droid}
		lookStr {You see a data cartridge clasped in the droid's manipulator.}
		view 203
		loop 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 103 12))
			(4 (Print 103 13))
			(5 (Print 103 14))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance monitor of Feature
	(properties
		description {cracked monitor}
		onMeCheck $0004
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(curRoom setScript: lookAtMonitor)
			)
			(3
				(curRoom setScript: lookAtMonitor)
			)
			(4
				(if (== theItem 0)
					(Print 103 3)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance console of Feature
	(properties
		description {data computer}
		onMeCheck $0040
		lookStr {This is the Data Retrieval console.}
	)
)

(instance controlPanel of Feature
	(properties
		description {control panel}
		onMeCheck $0002
		lookStr {This is the control panel for the Data Retrieval console.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: lookAtMonitor)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lights of Feature
	(properties
		description {lights}
		onMeCheck $0010
		lookStr {These are some highly decorative illumination devices for the console.}
	)
)

(instance egoFeat of Feature
	(properties
		description {yourself}
		onMeCheck $0008
		lookStr {It's you and you're darned handsome if you do say so yourself.}
	)
)

(instance buttons of Feature
	(properties
		description {buttons}
		onMeCheck $0080
		lookStr {Buttons reside here. You hate buttons. They confuse you so.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: pushButtons)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beep of Sound
	(properties)
)

(class FastHand of View
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
					(not (curRoom script?))
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
code_132e:
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
			bnt      code_1392
			jmp      code_1441
			jmp      code_132e
code_1392:
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      5
			and     
			bnt      code_13d4
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			bt       code_13c7
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      1
			and     
			bnt      code_132e
			pushi    #modifiers
			pushi    0
			lat      temp0
			send     4
			not     
			bnt      code_132e
code_13c7:
			pushi    #handleEvent
			pushi    1
			lst      temp0
			class    User
			send     6
			jmp      code_132e
code_13d4:
			lat      temp1
			aTop     x
			lat      temp2
			aTop     y
			pushi    #register
			pushi    0
			lofsa    keyFlashScript
			send     4
			bnt      code_13f9
			pushi    #doit
			pushi    0
			lofsa    keyFlashScript
			send     4
			pushi    #eachElementDo
			pushi    1
			pushi    60
			lag      sounds
			send     6
code_13f9:
			pushi    #script
			pushi    0
			lag      curRoom
			send     4
			push    
			dup     
			lofsa    lowerPad
			eq?     
			bnt      code_1410
			jmp      code_1441
			jmp      code_1429
code_1410:
			dup     
			lofsa    EnterScript
			eq?     
			bnt      code_1429
			pushi    #doit
			pushi    0
			lofsa    EnterScript
			send     4
			pushi    #eachElementDo
			pushi    1
			pushi    60
			lag      sounds
			send     6
code_1429:
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
			jmp      code_132e
code_1441:
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

(instance hand of FastHand
	(properties)
)

(instance keyPad of View
	(properties
		x 238
		y 185
		z -20
		description {keypad}
		lookStr {Enter a Code Sequence to retrieve Data cartridges.}
		view 502
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: 2)
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
				(localproc_000e)
				(StrCat theString strToCat)
				((ScriptID 700 0)
					saveBits:
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
		(if (not (curRoom script?))
			(self setScript: keyFlashScript self tone)
		)
	)
)

(instance enterBut of KeyPadButton
	(properties
		x 270
		y 139
		description {Enter}
		lookStr {This button confirms entry of the code you keyed-in and begins the search for the corresponding cartridge.}
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
		view 502
		loop 1
		cel 9
		signal $4000
		strToCat {J}
		tone 350
	)
)
