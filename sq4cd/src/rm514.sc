;;; Sierra Script 1.0 - (do not remove this comment)
(script# 514)
(include game.sh)
(use Main)
(use Intrface)
(use rm541)
(use brain)
(use SQRoom)
(use Sq4Feature)
(use MoveCyc)
(use PolyPath)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use System)

(public
	rm514 0
)

(local
	local0 =  26229
	[keyPadX 4] = [13 23 33 50]
	[keyPadY 4] = [23 33 43 55]
	[buttons 7]
	nums
	[doorCycle 29] = [1 0 161 77 1 0 161 82 1 0 161 87 1 0 161 92 1 0 161 97 1 0 161 102 1 0 161 109 -32768]
)
(procedure (DisplayKeyPad)
	(Display
		{8888888888}
		p_at (+ (keyPad x?) 18) (+ (keyPad y?) 9)
		p_color myLowlightColor
		p_mode teJustLeft
		p_font 30
	)
)

(procedure (localproc_01af param1 param2 param3)
	(param1
		nsLeft: (param1 x?)
		nsTop: (param1 y?)
		nsRight: (+ (param1 x?) param2)
		nsBottom: (+ (param1 y?) param3)
	)
)

(instance rm514 of SQRoom
	(properties
		picture 514
		style FADEOUT
		east 515
		lookStr 1
	)
	
	(method (init)
		(LoadMany VIEW 500 502)
		(LoadMany SOUND 102 124 154 155 101)
		(ego init:)
		(alarm1 init:)
		(alarm2 init:)
		(alarm3 init:)
		(if (not (Btst fOpenComputerDoor))
			(smallKeyPad init:)
			(doorTop init:)
			(doorBottom init:)
		else
			(chamber init: addToPic:)
		)
		(self setRegions: BRAIN)
		(super init:)
		(brain
			makePolygon:
				0 0 319 0 319 113 178 113 173 78 165 78 165 70
				152 70 152 78 140 78 136 126 319 126 319 189 0 189
		)
		(self setScript: enterScript 0 (== prevRoomNum 515))
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(script)
			((IsObjectOnControl ego cRED) (curRoom newRoom: 544))
			((IsObjectOnControl ego cCYAN) (brain exitDir: 90))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance alarm1 of Sq4Prop
	(properties
		x 251
		y 70
		view 505
		lookStr 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance alarm2 of Sq4Prop
	(properties
		x 152
		y 77
		z 60
		view 505
		loop 1
		priority 9
		signal (| ignrAct ignrHrz)
		lookStr 3
	)
	
	(method (doit)
		(super doit:)
		(if (and cycler (== cel 0))
			(globalSound number: 101 loop: 1 play:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance alarm3 of Sq4Prop
	(properties
		x 85
		y 61
		view 505
		loop 2
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance enterDown of Sq4Prop
	(properties
		view 502
		loop 1
		cel 11
		priority 15
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 3])
		)
	)
)

(instance doorTop of Sq4Prop
	(properties
		x 139
		y 9
		view 500
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance doorBottom of Sq4Prop
	(properties
		x 161
		y 72
		view 500
		loop 1
		priority 2
		signal (| ignrAct fixPriOn)
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (onMe)
		(return
			(if (super onMe: &rest)
				(not (smallKeyPad onMe: &rest))
			else
				FALSE
			)
		)
	)
)

(instance lightTop of Sq4Prop
	(properties
		x 159
		y 30
		view 500
		loop 3
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lightBottom of Sq4Prop
	(properties
		x 159
		y 75
		view 500
		loop 4
		signal ignrAct
		lookStr 6
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chamber of Sq4View
	(properties
		x 161
		y 76
		view 500
		loop 2
		priority 2
		signal (| ignrAct fixPriOn)
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance droid of SecurityDroid
	(properties
		x 320
		y 122
		z 44
		view 506
		cycleSpeed 12
		room 514
		level 2
	)
	
	(method (init)
		(= body droidBody)
		(super init: &rest)
		(self setCycle: Forward)
	)
	
	(method (doit &tmp temp0)
		(super doit:)
		(self setPri: 13)
		(if script
		else
			(= temp0 (self distanceTo: ego))
			(cond 
				((== attacks 3) (self attack: 1))
				((< temp0 50) (self attack: 1))
				((< temp0 100) (self attack: 0))
			)
		)
	)
)

(instance droidBody of Sq4Prop
	(properties
		view 506
		loop 1
	)
	
	(method (doit)
		(super doit:)
		(self
			x: (droid x?)
			y: (droid y?)
			setPri: (droid priority?)
			signal: (| (droid signal?) ignrAct)
		)
	)
)

(instance smallKeyPad of Sq4Feature
	(properties
		x 160
		y 56
		nsTop 52
		nsLeft 154
		nsBottom 61
		nsRight 166
		sightAngle 90
		lookStr 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(curRoom setScript: keyPadScript)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
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
				(not (& signal delObj))
				(not (& (userCurEvent type?) (| direction mouseDown mouseUp keyDown)))
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
				(theGame setCursor: 69 TRUE)
				(self show: x: userCurEventX y: temp2)
			else
				(theGame setCursor: normalCursor TRUE)
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
		cel 1
		priority 8
	)
	
	(method (init)
		(super init: &rest)
		(self signal: 273)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(User mapKeyToDir: FALSE)
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
		(enterDown init: hide:)
		(hand
			init:
			hide:
			x: (+ (keyPad x?) 50)
			y: (+ (keyPad y?) 20)
		)
		(self doit:)
		(theIconBar curIcon: (theIconBar at: ICON_LOOK))
		(super init:)
	)
	
	(method (doit &tmp temp0)
		(asm
			lag      pMouse
			bnt      code_0840
			pushi    #joyInc
			pushi    1
			pushi    2
			send     6
code_0840:
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
			bnt      code_086e
			pushi    #contains
			pushi    1
			push    
			lag      theDoits
			send     6
			bnt      code_086e
			pushi    #doit
			pushi    0
			lag      pMouse
			send     4
code_086e:
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
			bnt      code_0896
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      mouseDownHandler
			send     6
			jmp      code_08ad
code_0896:
			pushi    #type
			pushi    0
			lat      temp0
			send     4
			push    
			ldi      4
			and     
			bnt      code_08ad
			pushi    #handleEvent
			pushi    1
			lst      temp0
			lag      keyDownHandler
			send     6
code_08ad:
			pTos     signal
			ldi      32768
			and     
			bnt      code_08b7
			jmp      code_08f9
code_08b7:
			lag      pMouse
			bnt      code_08c3
			pushi    #handleEvent
			pushi    1
			lst      temp0
			send     6
code_08c3:
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
			bnt      code_0840
			ldi      0
			sag      doMotionCue
			pushi    #eachElementDo
			pushi    1
			pushi    246
			lag      cast
			send     6
			jmp      code_0840
code_08f9:
			lag      pMouse
			bnt      code_0905
			pushi    #joyInc
			pushi    1
			pushi    5
			send     6
code_0905:
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
		(enterDown dispose:)
		(hand dispose:)
		(theIconBar enable:)
	)
	
	(method (handleEvent event)
		(if (MousedOn self event) (event claimed: TRUE))
	)
)

(class KeyPadButton of Sq4Feature	;not in class table, but is used
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
			(++ nums)
			(Display theString
				p_at (+ (keyPad x?) 18 (* (- maxLen (StrLen theString)) 4)) (+ (keyPad y?) 9)
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

(instance enterBut of Sq4Feature
	(properties)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToEnd: self)
		(self
			x: (+ (keyPad x?) [keyPadX 3])
			y: (+ (keyPad y?) [keyPadY 0])
		)
		(localproc_01af self 8 33)
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
						(== (event type?) keyDown)
						(== (event message?) ENTER)
					)
					(MousedOn self event)
				)
				(event claimed: TRUE)
				(enterDown setScript: (Clone keyFlashScript) 0 864)
				(= temp0 (ReadNumber @buttons))
				(= temp2 (StrLen @buttons))
				(= buttons 0)
				(keyPad dispose:)
				(if (== temp0 local0)
					(curRoom setScript: openDoorScript)
				else
					(curRoom setScript: alarmScript)
				)
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
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 3])
		)
		(localproc_01af self 19 8)
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
						(== (event type?) keyDown)
						(OneOf (event message?) 88 (- speechEvent mouseUp))
					)
					(MousedOn self event)
					(InRect nsLeft nsTop nsRight nsBottom event)
				)
				(event claimed: TRUE)
				(= buttons 0)
				(keyPad dispose:)
			)
		)
	)
)

(instance oneBut of KeyPadButton
	(properties
		strToCat {1}
		keyEquiv 49
		maxLen 10
		tone 854
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 0])
		)
		(localproc_01af self 8 8)
	)
)

(instance twoBut of KeyPadButton
	(properties
		strToCat {2}
		keyEquiv 50
		maxLen 10
		tone 855
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 1])
			y: (+ (keyPad y?) [keyPadY 0])
		)
		(localproc_01af self 8 8)
	)
)

(instance threeBut of KeyPadButton
	(properties
		strToCat {3}
		keyEquiv 51
		maxLen 10
		tone 856
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 2])
			y: (+ (keyPad y?) [keyPadY 0])
		)
		(localproc_01af self 8 8)
	)
)

(instance fourBut of KeyPadButton
	(properties
		strToCat {4}
		keyEquiv 52
		maxLen 10
		tone 857
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 1])
		)
		(localproc_01af self 8 8)
	)
)

(instance fiveBut of KeyPadButton
	(properties
		strToCat {5}
		keyEquiv 53
		maxLen 10
		tone 858
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 1])
			y: (+ (keyPad y?) [keyPadY 1])
		)
		(localproc_01af self 8 8)
	)
)

(instance sixBut of KeyPadButton
	(properties
		strToCat {6}
		keyEquiv 54
		maxLen 10
		tone 859
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 2])
			y: (+ (keyPad y?) [keyPadY 1])
		)
		(localproc_01af self 8 8)
	)
)

(instance sevenBut of KeyPadButton
	(properties
		strToCat {7}
		keyEquiv 55
		maxLen 10
		tone 860
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 2])
		)
		(localproc_01af self 8 8)
	)
)

(instance eightBut of KeyPadButton
	(properties
		strToCat {8}
		keyEquiv 56
		maxLen 10
		tone 861
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 1])
			y: (+ (keyPad y?) [keyPadY 2])
		)
		(localproc_01af self 8 8)
	)
)

(instance nineBut of KeyPadButton
	(properties
		strToCat {9}
		keyEquiv 57
		maxLen 10
		tone 862
	)
	
	(method (init)
		(super init: &rest)
		(self
			x: (+ (keyPad x?) [keyPadX 2])
			y: (+ (keyPad y?) [keyPadY 2])
		)
		(localproc_01af self 8 8)
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
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 0])
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
			x: (+ (keyPad x?) [keyPadX 1])
			y: (+ (keyPad y?) [keyPadY 0])
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
			x: (+ (keyPad x?) [keyPadX 2])
			y: (+ (keyPad y?) [keyPadY 0])
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
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 1])
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
			x: (+ (keyPad x?) [keyPadX 1])
			y: (+ (keyPad y?) [keyPadY 1])
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
			x: (+ (keyPad x?) [keyPadX 2])
			y: (+ (keyPad y?) [keyPadY 1])
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
			x: (+ (keyPad x?) [keyPadX 0])
			y: (+ (keyPad y?) [keyPadY 2])
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
			x: (+ (keyPad x?) [keyPadX 1])
			y: (+ (keyPad y?) [keyPadY 2])
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
			x: (+ (keyPad x?) [keyPadX 2])
			y: (+ (keyPad y?) [keyPadY 2])
		)
	)
)

(instance enterScript of Script
	(properties)
	
	(method (changeState newState &tmp theX theY polyX polyY)
		(switch (= state newState)
			(0
				(HandsOff)
				(if register
					(= theX 318)
					(= theY 122)
					(= polyX 288)
					(= polyY 122)
				else
					(= theX 164)
					(= theY 75)
					(= polyX 164)
					(= polyY 100)
					(music setVol: 127)
					(globalSound setVol: 127)
				)
				(ego
					setPri: -1
					posn: theX theY
					setMotion: PolyPath polyX polyY self
				)
			)
			(1
				(HandsOn)
				(if (cast contains: doorTop) (ego observeControl: cBLUE))
				(SolvePuzzle fEnterProgrammingChamber 10)
				(self dispose:)
			)
		)
	)
)

(instance openDoorScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SolvePuzzle fOpenComputerDoor 10)
				(globalSound number: 124 loop: 1 vol: 127 play: self)
			)
			(1
				(globalSound number: 154 loop: -1 play:)
				(doorTop cycleSpeed: 12 setCycle: EndLoop)
				(doorBottom
					setPri: 2
					cycleSpeed: 12
					setCycle: MoveCycle @doorCycle self
				)
				(smallKeyPad dispose:)
			)
			(2
				(globalSound number: 155 loop: 1 play:)
				(doorTop dispose:)
				(doorBottom dispose:)
				(lightTop init: cycleSpeed: 12 setCycle: EndLoop)
				(lightBottom init: cycleSpeed: 12 setCycle: EndLoop self)
			)
			(3
				(HandsOn)
				(chamber init: addToPic:)
				(lightTop dispose:)
				(lightBottom dispose:)
				(ego ignoreControl: cBLUE)
				(Bset fOpenComputerDoor)
				(self dispose:)
			)
		)
	)
)

(instance keyPadScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (> (ego distanceTo: smallKeyPad) 30)
					(ego setMotion: PolyPath 164 77 self)
				else
					(= cycles 1)
				)
			)
			(1
				(HandsOn)
				(keyPad init:)
				(self dispose:)
			)
		)
	)
)

(instance alarmScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(alarm1 setCycle: Forward)
				(alarm2 setCycle: Forward)
				(alarm3 setCycle: Forward)
				(= seconds 5)
			)
			(1
				(droid init: setMotion: PolyPath (ego x?) (ego y?))
				(self dispose:)
			)
		)
	)
)
