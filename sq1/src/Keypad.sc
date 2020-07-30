;;; Sierra Script 1.0 - (do not remove this comment)
(script# KEYPAD) ;400
(include game.sh)
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
(procedure (RestoreBits)
	(if saveBits
		(Display 400 0
			p_restore saveBits
		)
		(= saveBits 0)
	)
)

(procedure (InitButtons)
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
	(hand
		init:
		hide:
		x: (+ (keyPad x?) 50)
		y: (+ (keyPad y?) 20)
	)
)

(procedure (DisposeButtons)
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
				(InitButtons)
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_TALK ICON_SMELL ICON_TASTE)
				(client register: 1)
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
				(keyPad signal: (& (keyPad signal?) $feff))
				(= local21 15)
				(DisposeButtons)
				(soundFx number: 358 loop: 1 play:)
				(= cycles 3)
			)
			(1
				(if (>= (-- local21) 0)
					(-- state)
					(keyPad y: [local23 local21] forceUpd:)
				else
					(keyPad dispose:)
				)
				(= cycles 1)
			)
			(2
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
				(RestoreBits)
				(cond 
					((not (StrLen @buttons)) (Print 400 1) (self dispose:))
					((< (StrLen @buttons) 4) (Print 400 2) (self dispose:))
					(else (= cycles 1))
				)
			)
			(1
				(switch deltaurSector
					(1 (StrCpy @sectorNumber {GFCB}))
					(2 (StrCpy @sectorNumber {DICJ}))
					(3 (StrCpy @sectorNumber {JBEI}))
					(4 (StrCpy @sectorNumber {EAEB}))
					(5 (StrCpy @sectorNumber {IIBA}))
					(6 (StrCpy @sectorNumber {EGHD}))
					(7 (StrCpy @sectorNumber {CBBJ}))
					(8 (StrCpy @sectorNumber {HFFH}))
					(9 (StrCpy @sectorNumber {ICDE}))
					(10 (StrCpy @sectorNumber {BIAA}))
					(11 (StrCpy @sectorNumber {GHJB}))
					(12 (StrCpy @sectorNumber {HAAD}))
					(13 (StrCpy @sectorNumber {DAIB}))
					(14 (StrCpy @sectorNumber {DDDG}))
					(15 (StrCpy @sectorNumber {BACH}))
					(16 (StrCpy @sectorNumber {ABHC}))
					(17 (StrCpy @sectorNumber {GJDH}))
					(18 (StrCpy @sectorNumber {CAHC}))
					(19 (StrCpy @sectorNumber {BGBB}))
					(20 (StrCpy @sectorNumber {IJFE}))
				)
				(cond 
					((== (StrCmp @buttons @sectorNumber) 0)
						(Print 400 3)
						(= shipDestination shipDELTAUR)
						((ScriptID 49 2) setScript: lowerPad)
						(= cycles 1)
					)
					((== (++ wrongCodeCount) 3)
						(= shipDestination shipWRONGSECTOR)
						(Print 400 3)
						((ScriptID 49 2) setScript: lowerPad)
						(= cycles 1)
					)
					(else (Print 400 4) (= ticks 1))
				)
			)
			(2 (self dispose:))
		)
	)
)

(instance keyFlashScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setPri: 11)
				(if register (beep number: 341 play:) (= register 0))
				(= cycles 2)
			)
			(1
				(client setPri: 0)
				(= cycles 1)
			)
			(2
				(if (client whoToCue?)
					(client setScript: (client whoToCue?))
				else
					(self dispose:)
				)
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

(class KeyPadButton400 of Prop
	(properties
		name "KeyPadButton"
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
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
			(verbDo (self flash:))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (flash)
		(self setScript: (Clone keyFlashScript) 0 tone)
		(if (and strToCat (< (StrLen theString) maxLen))
			(RestoreBits)
			(StrCat theString strToCat)
			(= saveBits
				(Display theString
					p_at (+ (keyPad x?) 5) (+ (- (keyPad y?) 10) 20)
					p_color myTextColor12
					p_mode teJustLeft
					p_font 30
					p_save
				)
			)
		)
	)
)

(instance beep of Sound
	(properties)
)

(instance hand of View
	(properties
		onMeCheck $0000
		view 502
		cel 2
		priority 15
		signal fixPriOn
	)
	
	(method (doit &tmp event evtX evtY)
		(= event (User curEvent?))
		(if
			(and
				(not (& signal delObj))
				(not (& (event type?) (| mouseDown mouseUp keyDown)))
			)
			(event localize:)
			(= evtX (event x?))
			(= evtY (event y?))
			(cond 
				(
					(and
						(InRect 224 115 295 187 evtX evtY)
						(== (theIconBar curIcon?) (theIconBar at: ICON_DO))
					)
					(theGame setCursor: 69 TRUE)
					(self show: x: evtX y: evtY)
				)
				((not (& signal actorHidden))
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
		(super dispose:)
	)
)

(instance keyPad of View
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
		(theIconBar disable: ICON_WALK)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(HandsOn)
	)
)

(instance enterBut of KeyPadButton400
	(properties
		x 270
		y 139
		description {Enter}
		lookStr {This button confirms entry of the code you keyed-in and begins the search for the corresponding cartridge.}
		view 502
		loop 1
		cel 11
		signal ignrAct
		tone 351
	)
)

(instance quitBut of KeyPadButton400
	(properties
		x 248
		y 169
		description {Off}
		lookStr {Press this button if you want to turn the keypad off.}
		view 502
		loop 1
		cel 12
		signal ignrAct
		tone 352
	)
)

(instance oneBut of KeyPadButton400
	(properties
		x 238
		y 139
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		signal ignrAct
		strToCat {A}
		tone 341
	)
)

(instance twoBut of KeyPadButton400
	(properties
		x 248
		y 139
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 1
		signal ignrAct
		strToCat {B}
		tone 342
	)
)

(instance threeBut of KeyPadButton400
	(properties
		x 258
		y 139
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 2
		signal ignrAct
		strToCat {C}
		tone 343
	)
)

(instance fourBut of KeyPadButton400
	(properties
		x 238
		y 149
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 3
		signal ignrAct
		strToCat {D}
		tone 344
	)
)

(instance fiveBut of KeyPadButton400
	(properties
		x 248
		y 149
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 4
		signal ignrAct
		strToCat {E}
		tone 345
	)
)

(instance sixBut of KeyPadButton400
	(properties
		x 258
		y 149
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 5
		signal ignrAct
		strToCat {F}
		tone 346
	)
)

(instance sevenBut of KeyPadButton400
	(properties
		x 238
		y 159
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 6
		signal ignrAct
		strToCat {G}
		tone 347
	)
)

(instance eightBut of KeyPadButton400
	(properties
		x 248
		y 159
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 7
		signal ignrAct
		strToCat {H}
		tone 348
	)
)

(instance nineBut of KeyPadButton400
	(properties
		x 258
		y 159
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 8
		signal ignrAct
		strToCat {I}
		tone 349
	)
)

(instance zeroBut of KeyPadButton400
	(properties
		x 238
		y 169
		description {button}
		lookStr {These buttons allow you to enter a code into the computer.}
		view 502
		loop 1
		cel 9
		signal ignrAct
		strToCat {J}
		tone 350
	)
)
