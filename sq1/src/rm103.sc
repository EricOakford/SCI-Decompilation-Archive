;;; Sierra Script 1.0 - (do not remove this comment)
(script# 103)
(include game.sh)
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
(procedure (RestoreBits)
	(if ((ScriptID 700 0) saveBits?)
		(Display 103 0 p_restore ((ScriptID 700 0) saveBits?))
		((ScriptID 700 0) saveBits: 0)
	)
)

(procedure (InitButtons)
	(oneBut init: @theButtons)
	(twoBut init: @theButtons)
	(threeBut init: @theButtons)
	(fourBut init: @theButtons)
	(fiveBut init: @theButtons)
	(sixBut init: @theButtons)
	(sevenBut init: @theButtons)
	(eightBut init: @theButtons)
	(nineBut init: @theButtons)
	(zeroBut init: @theButtons)
	(enterBut init: whoToCue: EnterScript)
	(quitBut init: whoToCue: lowerPad)
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

(instance rm103 of Room
	(properties
		picture 103
	)
	
	(method (init)
		(Load PICTURE 102)
		(LoadMany SOUND
			358 340 341 342 343 344 345 346 347
			348 349 350 351 352
		)
		(self setRegions: ARCADA)
		(super init:)
		(if (!= (theMusic number?) 355)
			(theMusic2 number: 314 loop: -1 play:)
		)
		(egoHead init:)
		(egoArm init:)
		(exitRoom init:)
		(if (ArcadaCheck 553 16)
			((ScriptID ARCADA 0)
				rFlag1: (& ((ScriptID 700 0) rFlag1?) $ffef)
			)
			((ScriptID ARCADA 0)
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
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(if (cast contains: keyPad)
					(Print 103 1)
				else
					(Print 103 2)
				)
			)
			(verbUse
				(switch theItem
					(iCartridge
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
				(curRoom drawPic: 103 FADEOUT)
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
				(droidArm setCycle: CycleTo 1 1 self)
			)
			(1
				(egoArm
					setLoop: 4
					cel: 0
					setPri: (+ (droidArm priority?) 1)
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(soundFx number: 303 loop: 1 play:)
				((ScriptID 700 0)
					rFlag1: (& ((ScriptID ARCADA 0) rFlag1?) $ffdf)
				)
				(droidArm setCycle: EndLoop self)
				(egoArm setCycle: EndLoop)
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
				(RestoreBits)
				(if (not (StrLen @theButtons))
					(Print 103 4)
					(self dispose:)
				else
					(= cycles 1)
				)
			)
			(1
				(switch ((ScriptID ARCADA 0) cartNumber?)
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
						(not (ArcadaCheck 553 32))
						(not (ego has: iCartridge))
						(== (StrCmp @theButtons @cartCode) 0)
					)
					(= cycles 1)
					0
				else
					(self changeState: 4)
				)
			)
			(2
				(soundFx number: 305 loop: 1 play: self)
			)
			(3
				(Print 103 5)
				((ScriptID ARCADA 0)
					rFlag1: (| ((ScriptID ARCADA 0) rFlag1?) $0010)
				)
				(curRoom newRoom: 3)
			)
			(4
				(soundFx number: 306 loop: 1 play: self)
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
						(not (ego has: iCartridge))
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
				(keyPad signal: (| (keyPad signal?) staticView) stopUpd:)
				(soundFx stop:)
				(InitButtons)
				(HandsOn)
				(theIconBar disable: ICON_WALK ICON_TALK ICON_SMELL ICON_TASTE)
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
				(DisposeButtons)
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
				(client setPri: 11)
				(if register
					(beep number: register play:)
					(= register 0)
				)
				(= cycles 2)
			)
			(1
				(client setPri: 0)
				(= cycles 1)
			)
			(2
				(if
				(and (client whoToCue?) (not (curRoom script?)))
					(curRoom setScript: (client whoToCue?))
				)
				(client setScript: 0)
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
				(egoArm setLoop: 3 cel: 0 setCycle: Forward)
				;(= cycles (Random 10 20))
				(= cycles (Random 30 60))	;EO: added from NRS speed patch
			)
			(1
				(Print 103 11)
				(egoArm setCycle: BegLoop)
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
		cycleSpeed 6	;EO: added from NRS speed patch		
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
			(verbDo
				(self cel: 1)
				(Animate (cast elements?) FALSE)
				(Animate (cast elements?) FALSE)
				(Animate (cast elements?) FALSE)
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
			(verbDo
				(SolvePuzzle 5 fGetCartridge)
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
		view 203
		loop 5
	)
)

(instance monitor of Feature
	(properties
		description {cracked monitor}
		onMeCheck FARCHECK
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
		onMeCheck NEARCHECK
		lookStr {This is the control panel for the Data Retrieval console.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
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
		onMeCheck (| ISNOTHIDDEN NEARCHECK)
		lookStr {These are some highly decorative illumination devices for the console.}
	)
)

(instance egoFeat of Feature
	(properties
		description {yourself}
		onMeCheck ISNOTHIDDEN
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
			(verbDo
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

(instance hand of View
	(properties
		onMeCheck $0000
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
				(
					(and
						(InRect 224 115 295 187 evtX evtY)
						(not (curRoom script?))
						(== (theIconBar curIcon?) (theIconBar at: ICON_DO))
					)
					(theGame setCursor: 69 TRUE)
					(self show: x: evtX y: evtY)
				)
				((not (& signal $0080))
					(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
					(self hide:)
				)
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

(class KeyPadButton103 of Prop
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
		(if (not (curRoom script?))
			(self setScript: (Clone keyFlashScript) 0 tone)
			(if (and strToCat (< (StrLen theString) maxLen))
				(RestoreBits)
				(StrCat theString strToCat)
				((ScriptID ARCADA 0)
					saveBits:
						(Display theString
							p_at (+ (keyPad x?) 5) (+ (- (keyPad y?) 10) 20)
							p_color colLED
							p_mode teJustLeft
							p_font 30
							p_save
						)
				)
			)
		)
	)
)

(instance enterBut of KeyPadButton103
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

(instance quitBut of KeyPadButton103
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

(instance oneBut of KeyPadButton103
	(properties
		x 238
		y 139
		view 502
		loop 1
		signal ignrAct
		strToCat {A}
		tone 341
	)
)

(instance twoBut of KeyPadButton103
	(properties
		x 248
		y 139
		view 502
		loop 1
		cel 1
		signal ignrAct
		strToCat {B}
		tone 342
	)
)

(instance threeBut of KeyPadButton103
	(properties
		x 258
		y 139
		view 502
		loop 1
		cel 2
		signal ignrAct
		strToCat {C}
		tone 343
	)
)

(instance fourBut of KeyPadButton103
	(properties
		x 238
		y 149
		view 502
		loop 1
		cel 3
		signal ignrAct
		strToCat {D}
		tone 344
	)
)

(instance fiveBut of KeyPadButton103
	(properties
		x 248
		y 149
		view 502
		loop 1
		cel 4
		signal ignrAct
		strToCat {E}
		tone 345
	)
)

(instance sixBut of KeyPadButton103
	(properties
		x 258
		y 149
		view 502
		loop 1
		cel 5
		signal ignrAct
		strToCat {F}
		tone 346
	)
)

(instance sevenBut of KeyPadButton103
	(properties
		x 238
		y 159
		view 502
		loop 1
		cel 6
		signal ignrAct
		strToCat {G}
		tone 347
	)
)

(instance eightBut of KeyPadButton103
	(properties
		x 248
		y 159
		view 502
		loop 1
		cel 7
		signal ignrAct
		strToCat {H}
		tone 348
	)
)

(instance nineBut of KeyPadButton103
	(properties
		x 258
		y 159
		view 502
		loop 1
		cel 8
		signal ignrAct
		strToCat {I}
		tone 349
	)
)

(instance zeroBut of KeyPadButton103
	(properties
		x 238
		y 169
		view 502
		loop 1
		cel 9
		signal ignrAct
		strToCat {J}
		tone 350
	)
)
