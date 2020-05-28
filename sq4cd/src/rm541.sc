;;; Sierra Script 1.0 - (do not remove this comment)
(script# 541)
(include game.sh)
(use Main)
(use Intrface)
(use brain)
(use MoveToCoords)
(use SQRoom)
(use Sq4Feature)
(use PolyPath)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use System)

(public
	rm541 0
)

(local
	[local0 3] = [9 20 31]
	[buttonX 3] = [7 17 27]
	[buttonY 5] = [6 16 26 36 48]
	local11
	[buttons 4]
)
(procedure (DisplayKeyPad)
	(Display
		{888}
		p_at (+ (keyPad x?) 54) (+ (keyPad y?) [local0 local11])
		p_color myLowlightColor
		p_mode teJustLeft
		p_font 30
	)
)

(procedure (localproc_01db param1 param2 param3)
	(param1
		nsLeft: (param1 x?)
		nsTop: (param1 y?)
		nsRight: (+ (param1 x?) param2)
		nsBottom: (+ (param1 y?) param3)
	)
)

(instance rm541 of SQRoom
	(properties
		picture 541
		north 525
		south 540
		vanishingX 0
		vanishingY -30
	)
	
	(method (init &tmp temp0 temp1 temp2)
		(LoadMany VIEW 516 502 517 515)
		(Load SOUND
			854 855 856 857 858 859 860
			861 862 863 885 153
		)
		(ego init:)
		(self setRegions: BRAIN)
		(super init:)
		(music number: 885 loop: -1 vol: 127 playBed:)
		(brain
			makePolygon: 0 0 33 0 215 189 0 189
			makePolygon: 40 0 318 0 319 189 241 189 219 165 192 157
			level: 2
		)
		(theGame setCursor: 69 TRUE)
		(smallKeyPad init:)
		(= temp1 0)
		(if (>= (= temp2 beamSetting) 4096)
			(= temp1 1)
			(= temp2 (- temp2 4096))
		)
		(if temp1
			(smoke0 setCel: 3 init: stopUpd:)
			(smoke1 setCel: 3 init: stopUpd:)
			(smoke2 setCel: 3 init: stopUpd:)
		)
		(= temp0 (mod temp2 16))
		(beam0 cycleSpeed: 1 setLoop: temp1 setCel: temp0 init:)
		(= temp0 (mod (= temp2 (/ temp2 16)) 16))
		(beam1 cycleSpeed: 1 setLoop: temp1 setCel: temp0 init:)
		(= temp0 (= temp2 (/ temp2 16)))
		(beam2 cycleSpeed: 1 setLoop: temp1 setCel: temp0 init:)
		(switch prevRoomNum
			(north
				(self setScript: enterScript 0 1)
			)
			(else 
				(self setScript: enterScript 0 3)
			)
		)
		(roomFeature init:)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((== ((inventory at: iCigar) state?) 1)
				(self setScript: smokeScript)
				((inventory at: iCigar) state: 0)
			)
			((IsObjectOnControl ego cCYAN) (brain exitDir: (if (> (ego y?) 100) 135 else 315)))
		)
	)
	
	(method (newRoom newRoomNumber)
		(if
			(and
				(== newRoomNumber (curRoom south?))
				(brain formatting?)
			)
			(EgoDead iconDEAD deathLEAVEFORMATTING)
		else
			(music fade:)
			(if (cast contains: smoke0)
				(= beamSetting 4096)
			else
				(= beamSetting 0)
			)
			(= beamSetting
				(+
					(= beamSetting
						(+
							(= beamSetting (+ beamSetting (* (beam2 cel?) 256)))
							(* (beam1 cel?) 16)
						)
					)
					(beam0 cel?)
				)
			)
			(super newRoom: newRoomNumber)
		)
	)
)

(instance smallKeyPad of Sq4Feature
	(properties
		x 248
		y 147
		nsTop 136
		nsLeft 240
		nsBottom 158
		nsRight 256
		sightAngle 90
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: startKeyPadScript)
			)
			(V_LOOK
				(curRoom setScript: startKeyPadScript)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance beam0 of Sq4Prop
	(properties
		x 190
		y 157
		sightAngle 90
		view 516
		cel 9
		signal ignrAct
	)
	
	(method (init)
		(super init: &rest)
		(if loop (= noun 0) else (= noun 0))
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (curRoom script?))
				(!= cel 7)
				(<
					(GetDistance (self x?) (self y?) (ego x?) (ego y?))
					5
				)
			)
			(curRoom setScript: egoFryScript)
		)
	)
	
	(method (cue)
		(rotate stop:)
		(super cue:)
		(theIconBar enable: ICON_WALK)
		(self setScript: 0)
	)
)

(instance beam1 of Sq4Prop
	(properties
		x 151
		y 117
		sightAngle 90
		view 516
		cel 5
		signal ignrAct
	)
	
	(method (init)
		(super init: &rest)
		(if loop (= noun 0) else (= noun 0))
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (curRoom script?))
				(!= cel 7)
				(<
					(GetDistance (self x?) (self y?) (ego x?) (ego y?))
					5
				)
			)
			(curRoom setScript: egoFryScript)
		)
	)
	
	(method (cue)
		(rotate stop:)
		(super cue:)
		(theIconBar enable: 0)
		(self setScript: 0)
	)
)

(instance beam2 of Sq4Prop
	(properties
		x 110
		y 77
		sightAngle 90
		view 516
		cel 13
		signal ignrAct
	)
	
	(method (init)
		(super init: &rest)
		(if loop (= noun 0) else (= noun 0))
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (curRoom script?))
				(!= cel 7)
				(<
					(GetDistance (self x?) (self y?) (ego x?) (ego y?))
					5
				)
			)
			(curRoom setScript: egoFryScript)
		)
	)
	
	(method (cue)
		(rotate stop:)
		(super cue:)
		(theIconBar enable: ICON_WALK)
		(self setScript: 0)
		(if
			(and
				(== cel 7)
				(== (beam0 cel?) 7)
				(== (beam1 cel?) 7)
			)
			(SolvePuzzle fBeamsAligned 15)
		)
	)
)

(instance smoke0 of Sq4Prop
	(properties
		x 191
		y 122
		view 517
		priority 1
		signal ignrAct
		cycleSpeed 18
	)
)

(instance smoke1 of Sq4Prop
	(properties
		x 168
		y 94
		view 517
		loop 1
		priority 1
		signal ignrAct
		cycleSpeed 18
	)
)

(instance smoke2 of Sq4Prop
	(properties
		x 130
		y 54
		view 517
		loop 2
		priority 1
		signal ignrAct
		cycleSpeed 18
	)
)

(instance blinker of Sq4Prop
	(properties
		view 502
		loop 2
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doit)
		(super doit:)
		(self
			x: (+ (keyPad x?) 53)
			y: (+ (keyPad y?) [local0 local11] 4)
		)
	)
)

(instance hand of Sq4View
	(properties
		view 502
		cel 2
		priority 15
		signal fixPriOn
	)
	
	(method (doit &tmp userCurEvent userCurEventX temp2)
		(= userCurEvent (User curEvent?))
		(if
			(and
				(not (& signal $8000))
				(not (& (userCurEvent type?) $0047))
			)
			(GlobalToLocal userCurEvent)
			(= userCurEventX (userCurEvent x?))
			(= temp2 (+ (userCurEvent y?) 10))
			(if
				(InRect
					(keyPad x?)
					(keyPad y?)
					(+
						(keyPad x?)
						(CelWide (keyPad view?) (keyPad loop?) (keyPad cel?))
					)
					(+
						(keyPad y?)
						(CelHigh (keyPad view?) (keyPad loop?) (keyPad cel?))
					)
					userCurEventX
					temp2
				)
				(theGame setCursor: 69 1)
				(self show: x: userCurEventX y: temp2)
			else
				(theGame setCursor: 852 1)
				(self hide:)
			)
		)
		(super doit:)
	)
)

(instance keyPad of Sq4View
	(properties
		x 220
		y 40
		view 502
	)
	
	(method (init)
		(super init: &rest)
		(self signal: 257)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(User mapKeyToDir: 0)
		(theIconBar disable:)
		(oneBut init: @buttons who: oneDown)
		(twoBut init: @buttons who: twoDown)
		(threeBut init: @buttons who: threeDown)
		(fourBut init: @buttons who: fourDown)
		(fiveBut init: @buttons who: fiveDown)
		(sixBut init: @buttons who: sixDown)
		(sevenBut init: @buttons who: sevenDown)
		(eightBut init: @buttons who: eightDown)
		(nineBut init: @buttons who: nineDown)
		(zeroBut init: @buttons who: zeroDown)
		(enterBut init:)
		(quitBut init:)
		(oneDown init: hide:)
		(twoDown init: hide:)
		(threeDown init: hide:)
		(fourDown init: hide:)
		(fiveDown init: hide:)
		(sixDown init: hide:)
		(sevenDown init: hide:)
		(eightDown init: hide:)
		(nineDown init: hide:)
		(zeroDown init: hide:)
		(enterDown init: hide:)
		(hand
			init:
			hide:
			x: (+ (keyPad x?) 50)
			y: (+ (keyPad y?) 20)
		)
		(blinker init: setCycle: Forward)
		(self doit:)
	)
	
	(method (doit &tmp temp0)
		(asm
			lag      pMouse
			bnt      code_0a59
			pushi    #joyInc
			pushi    1
			pushi    2
			send     6
code_0a59:
			pushi    #new
			pushi    0
			pushi    #curEvent
			pushi    0
			lag      user
			send     4
			send     4
			sat      temp0
			sag      lastEvent
			pushi    #localize
			pushi    0
			lat      temp0
			send     4
			lag      pMouse
			bnt      code_0a87
			pushi    #contains
			pushi    1
			push    
			lag      theDoits
			send     6
			bnt      code_0a87
			pushi    #doit
			pushi    0
			lag      pMouse
			send     4
code_0a87:
			pushi    1
			lst      temp0
			callk    MapKeyToDir,  2
			pushi    3
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			pushi    1
			pushi    256
			calle    OneOf,  6
			bnt      code_0aaf
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      mouseDownHandler
			send     6
			jmp      code_0ac6
code_0aaf:
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			bnt      code_0ac6
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      keyDownHandler
			send     6
code_0ac6:
			pTos     signal
			ldi      32768
			and     
			bnt      code_0ad0
			jmp      code_0b12
code_0ad0:
			lag      pMouse
			bnt      code_0adc
			pushi    #handleEvent
			pushi    1
			lst      temp0
			send     6
code_0adc:
			lsg      tickOffset
			pushi    0
			callk    GetTime,  0
			add     
			sag      gameTime
			pushi    #eachElementDo
			pushi    1
			pushi    174
			lag      sounds
			send     6
			pushi    2
			pushi    #elements
			pushi    0
			lag      cast
			send     4
			push    
			pushi    1
			callk    Animate,  4
			lag      doMotionCue
			bnt      code_0a59
			ldi      0
			sag      doMotionCue
			pushi    #eachElementDo
			pushi    1
			pushi    246
			lag      cast
			send     6
			jmp      code_0a59
code_0b12:
			lag      pMouse
			bnt      code_0b1e
			pushi    #joyInc
			pushi    1
			pushi    5
			send     6
code_0b1e:
			ret     
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(User mapKeyToDir: TRUE)
		(theGame setCursor: ((theIconBar curIcon?) cursor?) TRUE)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
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
		(oneDown dispose:)
		(twoDown dispose:)
		(threeDown dispose:)
		(fourDown dispose:)
		(fiveDown dispose:)
		(sixDown dispose:)
		(sevenDown dispose:)
		(eightDown dispose:)
		(nineDown dispose:)
		(zeroDown dispose:)
		(enterDown dispose:)
		(blinker dispose:)
		(hand dispose:)
		(HandsOn)
	)
)

(class KeyPadButton of Sq4Feature
	(properties
		theString 0
		strToCat 0
		keyEquiv 0
		maxLen 0
		who 0
		tone 0
	)
	
	(method (init theTheString)
		(super init:)
		(= theString theTheString)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (doit)
		(who setScript: (Clone keyFlashScript) 0 tone)
		(if (< (StrLen theString) maxLen)
			(DisplayKeyPad)
			(StrCat theString strToCat)
			(Display theString
				p_at
				(+ (keyPad x?) 54 (* (- maxLen (StrLen theString)) 4))
				(+ (keyPad y?) [local0 local11])
				p_color myTextColor12
				p_mode teJustLeft
				p_font 30
			)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((& (event type?) keyDown)
				(if (== (event message?) keyEquiv)
					(event claimed: TRUE)
					(self doit:)
				)
			)
			(
				(and
					(== (event type?) mouseDown)
					(InRect nsLeft nsTop nsRight nsBottom event)
				)
				(event claimed: TRUE)
				(self doit:)
			)
			(
				(and
					(== (event type?) joyDown)
					(not (event modifiers?))
					(InRect nsLeft nsTop nsRight nsBottom event)
				)
				(event claimed: TRUE)
				(self doit:)
			)
		)
	)
)

(instance beep of Sound
	(properties)
)

(instance rotate of Sound
	(properties
		number 153
	)
)

(instance keyFlashScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client show:)
				(beep number: register play:)
				(= cycles 2)
			)
			(1 (client hide: setScript: 0))
		)
	)
)

(class CA of Cycle
	(properties
		celCnt 0
	)
	
	(method (init param1 param2 theCaller)
		(super init: param1)
		(= celCnt (if (< param2 1) 1 else param2))
		(if (== argc 3) (= caller theCaller))
	)
	
	(method (doit &tmp cANextCel clientLastCel)
		(= clientLastCel (client lastCel:))
		(= cANextCel (self nextCel:))
		(client
			cel: (if (> cANextCel clientLastCel) 0 else cANextCel)
		)
		(-- celCnt)
		(if (not celCnt) (self cycleDone:))
	)
	
	(method (cycleDone)
		(= completed TRUE)
		(if caller (= doMotionCue TRUE) else (self motionCue:))
	)
)

(instance enterBut of Sq4Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 3])
		)
		(localproc_01db self 19 8)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(cond 
			((super handleEvent: event))
			(
				(or
					(and
						(& (event type?) keyDown)
						(== (event message?) ENTER)
					)
					(and
						(== (event type?) mouseDown)
						(InRect nsLeft nsTop nsRight nsBottom event)
					)
					(and
						(== (event type?) joyDown)
						(not (event modifiers?))
						(InRect nsLeft nsTop nsRight nsBottom event)
					)
				)
				(event claimed: TRUE)
				(enterDown setScript: (Clone keyFlashScript) 0 864)
				(DisplayKeyPad)
				(= temp0 (ReadNumber @buttons))
				(= temp2 (StrLen @buttons))
				(= buttons 0)
				(if (or (> temp0 360) (< temp0 1) (< temp2 3))
					0
				else
					(theIconBar disable:)
					(= temp1 (/ temp0 12))
					(switch local11
						(0
							(if (not temp1)
								(beam0 cue:)
							else
								(rotate play:)
								(beam0 setCycle: CA temp1 beam0)
							)
						)
						(1
							(if (not temp1)
								(beam1 cue:)
							else
								(rotate play:)
								(beam1 setCycle: CA temp1 beam1)
							)
						)
						(2
							(if (not temp1)
								(beam2 cue:)
							else
								(rotate play:)
								(beam2 setCycle: CA temp1 beam2)
							)
						)
					)
				)
				(if (> (++ local11) 2) (= local11 0))
			)
		)
	)
)

(instance quitBut of Sq4Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 4])
		)
		(localproc_01db self 19 8)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(beep number: 865 play:)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(or
					(and
						(& (event type?) keyDown)
						(OneOf (event message?) 88 120)
					)
					(MousedOn self event)
					(and
						(== (event type?) joyDown)
						(not (event modifiers?))
						(InRect nsLeft nsTop nsRight nsBottom event)
					)
				)
				(event claimed: TRUE)
				(= buttons 0)
				(keyPad dispose:)
				(theIconBar enable:)
			)
		)
	)
)

(instance oneBut of KeyPadButton
	(properties
		strToCat {1}
		keyEquiv 49
		maxLen 3
		tone 854
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 0])
		)
		(localproc_01db self 8 8)
	)
)

(instance twoBut of KeyPadButton
	(properties
		strToCat {2}
		keyEquiv 50
		maxLen 3
		tone 855
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 1])
			y: (+ (keyPad y?) [buttonY 0])
		)
		(localproc_01db self 8 8)
	)
)

(instance threeBut of KeyPadButton
	(properties
		strToCat {3}
		keyEquiv 51
		maxLen 3
		tone 856
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 0])
		)
		(localproc_01db self 8 8)
	)
)

(instance fourBut of KeyPadButton
	(properties
		strToCat {4}
		keyEquiv 52
		maxLen 3
		tone 857
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 1])
		)
		(localproc_01db self 8 8)
	)
)

(instance fiveBut of KeyPadButton
	(properties
		strToCat {5}
		keyEquiv 53
		maxLen 3
		tone 858
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 1])
			y: (+ (keyPad y?) [buttonY 1])
		)
		(localproc_01db self 8 8)
	)
)

(instance sixBut of KeyPadButton
	(properties
		strToCat {6}
		keyEquiv 54
		maxLen 3
		tone 859
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 1])
		)
		(localproc_01db self 8 8)
	)
)

(instance sevenBut of KeyPadButton
	(properties
		strToCat {7}
		keyEquiv 55
		maxLen 3
		tone 860
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 2])
		)
		(localproc_01db self 8 8)
	)
)

(instance eightBut of KeyPadButton
	(properties
		strToCat {8}
		keyEquiv 56
		maxLen 3
		tone 861
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 1])
			y: (+ (keyPad y?) [buttonY 2])
		)
		(localproc_01db self 8 8)
	)
)

(instance nineBut of KeyPadButton
	(properties
		strToCat {9}
		keyEquiv 57
		maxLen 3
		tone 862
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 2])
		)
		(localproc_01db self 8 8)
	)
)

(instance zeroBut of KeyPadButton
	(properties
		strToCat {0}
		keyEquiv 48
		maxLen 3
		tone 863
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 3])
		)
		(localproc_01db self 8 8)
	)
)

(instance oneDown of Sq4Prop
	(properties
		view 502
		loop 1
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 0])
		)
	)
)

(instance twoDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 1
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 1])
			y: (+ (keyPad y?) [buttonY 0])
		)
	)
)

(instance threeDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 2
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 0])
		)
	)
)

(instance fourDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 3
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 1])
		)
	)
)

(instance fiveDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 4
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 1])
			y: (+ (keyPad y?) [buttonY 1])
		)
	)
)

(instance sixDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 5
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 1])
		)
	)
)

(instance sevenDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 6
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 2])
		)
	)
)

(instance eightDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 7
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 1])
			y: (+ (keyPad y?) [buttonY 2])
		)
	)
)

(instance nineDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 8
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 2])
		)
	)
)

(instance zeroDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 9
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 2])
			y: (+ (keyPad y?) [buttonY 3])
		)
	)
)

(instance enterDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 10
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [buttonX 0])
			y: (+ (keyPad y?) [buttonY 3])
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== register 1)
					(= temp0 47)
					(= temp1 7)
					(= temp2 33)
					(= temp3 135)
				else
					(= temp0 277)
					(= temp1 242)
					(= temp2 (- 189 (* (ego yStep?) 4)))
					(= temp3 315)
				)
				(ego
					illegalBits: 0
					x: temp0
					y: temp1
					setHeading: temp3
					setMotion: MoveToY temp2 self
				)
			)
			(1
				(ego illegalBits: -32768 setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance egoFryScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(beam0 setPri: 9)
				(beam1 setPri: 7)
				(beam2 setPri: 3)
				(ego view: 515 setLoop: 4 setCycle: Forward)
				(globalSound number: 139 vol: 127 loop: 1 play:)
				(= cycles 8)
			)
			(1
				(ego setLoop: 5 setCel: 0 cycleSpeed: 12 setCycle: EndLoop)
				(= seconds 5)
			)
			(2 (EgoDead 8))
		)
	)
)

(instance smokeScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 216 181 self)
			)
			(1
				(ego
					view: 515
					setLoop: 2
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop self)
				(globalSound number: 846 vol: 127 loop: 1 play:)
			)
			(3
				(smoke0 init: setCycle: EndLoop)
				(= cycles 2)
			)
			(4
				(NormalEgo 7 0)
				(beam0 setLoop: 1 noun: 0 lookStr: 1)
				(smoke1 init: setCycle: EndLoop)
				(= cycles 2)
			)
			(5
				(beam1 setLoop: 1 noun: 0 lookStr: 1)
				(smoke2 init: setCycle: EndLoop self)
			)
			(6
				(smoke0 stopUpd:)
				(smoke1 stopUpd:)
				(smoke2 stopUpd:)
				(beam2 setLoop: 1 noun: 0 lookStr: 1)
				(= cycles 6)
			)
			(7
				(narrator say: 3)
				(ego put: iCigar curRoomNum)
				(SolvePuzzle fBeamsRevealed 10)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance startKeyPadScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					setMotion: PolyPath (smallKeyPad x?) (smallKeyPad y?) self
				)
			)
			(1 (ego setHeading: 90 self))
			(2
				(HandsOn)
				(keyPad init:)
				(self dispose:)
			)
		)
	)
)

(instance roomFeature of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (>= beamSetting 4096)
					(narrator say: 4)
				else
					(narrator say: 5)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
