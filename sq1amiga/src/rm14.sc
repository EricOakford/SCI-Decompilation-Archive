;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include sci.sh)
(use Main)
(use Intrface)
(use Osc)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm14 0
)

(local
	powerOn
	crashedIntoKerona
	local2
	local3
	i
	[keronaPodPosn 45] = [0 0 0 1 290 79 2 280 84 3 270 87 4 262 93 5 254 98 6 246 99 7 236 104 8 224 112 9 215 116 10 206 122 11 197 128 12 188 134 14 181 138 -1 -1 -1]
	[camelotPodPosn 24] = [0 0 0 3 218 106 1 211 112 5 199 115 6 186 115 2 175 120 1 165 127 -1 -1 -1]
	gasTimer =  180
	local75
	local76
)
(procedure (localproc_000e)
	(theMusic2 number: 317 loop: 1 play:)
)

(procedure (localproc_0020)
	(if (cast contains: padInset)
		(AutoNavButton dispose:)
		(DontPressButton dispose:)
		(PowerButton dispose:)
		(padInset dispose:)
	)
	(Animate (cast elements?) 0)
)

(instance rm14 of Rm
	(properties
		picture 13
	)
	
	(method (init)
		(LoadMany 129 16 17 38 37 313 213)
		(LoadMany 128 138 113 137 238 117 169)
		(LoadMany
			132
			326
			334
			336
			335
			332
			405
			400
			402
			401
			807
			808
			328
			325
			337
		)
		(super init:)
		(features
			add:
				joystick
				egoSuit
				seatBeltFeat
				PodWindow
				Chair
				ControlPanel
				Dials
				ButtonPad
			eachElementDo: #init
		)
		(survivalKit init: stopUpd:)
		(= powerOn 1)
		(cockpitSound number: 326 loop: -1 flags: 1 init: play:)
		(theMusic number: 367 loop: -1 play:)
		(ledLights init: setCycle: Fwd)
		(screen init: setCycle: Fwd)
		(theMusic2 pause: 0)
		(head init: stopUpd:)
		(arm init: stopUpd:)
		(seatbelt1 init: stopUpd:)
		(seatbelt2 init: stopUpd:)
		(HandsOn)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not script)
				(!= local75 (= local76 (GetTime 1)))
			)
			(= local75 local76)
			(switch (-- gasTimer)
				(-30 (Print 14 0))
				(-60 (Print 14 1))
				(-90 (Print 14 2))
				(-120
					(Print 14 3)
					(self setScript: outOfGas)
				)
			)
		)
	)
	
	(method (dispose)
		(cockpitSound loop: 0 fade:)
		(soundFx loop: 0 stop:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: 0))
				(or
					(== (event message?) JOY_UP)
					(& (event type?) evJOYSTICK)
				)
			)
			(if
			(and crashedIntoKerona (cast contains: seatbelt2))
				(Print 14 4)
			else
				(Print 14 5)
			)
			(event claimed: 1)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(if crashedIntoKerona (Print 14 6) else (Print 14 7))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(theMusic2 flags: 0)
		(super newRoom: n)
	)
	
	(method (drawPic pic)
		(if (== pic 13)
			(cast eachElementDo: #show)
		else
			(cast eachElementDo: #hide)
		)
		(super drawPic: pic &rest)
	)
)

(instance outOfGas of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(curRoom drawPic: 99 10)
				(cast eachElementDo: #dispose)
				(= cycles 1)
			)
			(2 (EgoDead 948 0 0 14 8))
		)
	)
)

(instance buttonInset of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (cast contains: padInset))
					(features addToFront: AutoNavButton)
					(features addToFront: DontPressButton)
					(features addToFront: PowerButton)
					(padInset init:)
				else
					(Print 14 9)
				)
				(= cycles 1)
			)
			(1 (self dispose:))
		)
	)
)

(instance showKerona of Script
	(properties)
	
	(method (doit)
		(if local2 (Palette 6 208 254 1))
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cockpitSound setVol: 50)
				(soundFx number: 334 loop: -1 play: hold: 1)
				(tempProp init: posn: 90 36 setCycle: CT 2 1 self)
			)
			(1
				(tempProp setCycle: End tempProp)
				(hologram init: cycleSpeed: 8 setCycle: End self)
			)
			(2
				(theMusic number: 336 loop: -1 play:)
				(= cycles 3)
			)
			(3
				(= local2 1)
				(hologram loop: 8 cel: 0 cycleSpeed: 8 setCycle: Fwd)
				(= seconds 10)
			)
			(4
				(= local2 0)
				(hologram loop: 7 cel: 5 cycleSpeed: 8 setCycle: Beg self)
			)
			(5
				(theMusic stop: loop: 0)
				(soundFx number: 335 loop: 1 play:)
				(tempProp show: setCycle: Beg self)
			)
			(6
				(soundFx stop:)
				(UnLoad 132 335)
				(UnLoad 132 334)
				(UnLoad 132 336)
				(tempProp dispose:)
				(hologram dispose:)
				(= cycles 25)
			)
			(7 (self dispose:))
		)
	)
)

(instance doHyperspace of Script
	(properties)
	
	(method (doit)
		(Palette 6 208 254 -1)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom overlay: 213 7)
				(= seconds register)
			)
			(1
				(= register 0)
				(self dispose:)
			)
		)
	)
)

(instance autoNavScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 1) (== (theMusic prevSignal?) 10))
			(theMusic prevSignal: 0)
			(= cycles 3)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(sounds eachElementDo: #stop)
				(self setScript: showKerona self)
			)
			(1
				(cockpitSound number: 326 loop: 0 stop:)
				(theMusic number: 332 loop: 1 play:)
			)
			(2
				(self setScript: doHyperspace self 10)
			)
			(3
				(curRoom drawPic: 17 10)
				(escapePod
					init:
					view: 116
					loop: 2
					posn: 320 -10
					setStep: 3 2
					cel: 0
					cycleSpeed: 5
					moveSpeed: 1
					setCycle: Fwd
					setMotion: MoveTo -90 151 self
				)
				(survivalKit dispose:)
			)
			(4
				(curRoom setScript: watchPodLand)
			)
		)
	)
)

(instance watchPodLand of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 5) (== (theMusic prevSignal?) 10))
			(theMusic prevSignal: 0)
			(= cycles 1)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(LoadMany 132 400 402 405)
				(curRoom drawPic: 16 -32759)
				(kerona
					init:
					stopUpd:
					setPri: 0
					ignoreActors: 1
					addToPic:
				)
				(escapePod
					show:
					posn: 331 106
					setCycle: Fwd
					setMotion: MoveTo 222 151 self
				)
			)
			(1
				(escapePod
					setLoop: 5
					cel: 0
					setMotion: MoveTo 201 159
					setCycle: End self
				)
			)
			(2
				(escapePod
					setLoop: 6
					cel: 0
					setCycle: End self
					setMotion: MoveTo 135 168
				)
			)
			(3
				(escapePod hide:)
				(= cycles 2)
			)
			(4
				(theMusic fade:)
				(curRoom drawPic: 38 10)
				(kerona dispose:)
				(tempProp dispose:)
				(addToPics
					add: back1 back2 back3 back4 back5
					eachElementDo: #setPri 0
					eachElementDo: #init
					doit:
				)
				(= cycles 13)
			)
			(5
				(theMusic number: 400 loop: 1 play: 127)
				(= seconds 20)
			)
			(6
				(= seconds 0)
				(theMusic2 number: 402 loop: 1 play:)
				(escapePod
					show:
					view: 138
					posn: 329 36
					illegalBits: 0
					setStep: 10 6
					setLoop: 0
					setCel: 0
					moveSpeed: 1
					setMotion: MoveTo 299 70 self
				)
				(= register (+ 1 (* howFast 2)))
			)
			(7
				(if (!= [keronaPodPosn (= i (+ i 3))] -1)
					(-- state)
					(if (== i 3) (theMusic stop:))
					(escapePod
						setCel: [keronaPodPosn i]
						x:
							[keronaPodPosn (+
								(if
									(or
										(== [keronaPodPosn i] 4)
										(== [keronaPodPosn i] 9)
										(== [keronaPodPosn i] 12)
									)
									(theMusic2 number: 405 loop: 1 play:)
									(theMusic stop:)
								)
								1
							)]
						y: [keronaPodPosn (+ i 2)]
					)
				)
				(= cycles register)
			)
			(8
				(curRoom setScript: crashedPod)
			)
		)
	)
)

(instance crashedPod of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 44))
			(1
				(curRoom drawPic: 37 10)
				(theMusic number: 401 loop: -1 play:)
				(theMusic2 number: 807 loop: -1 play:)
				(back1 dispose:)
				(back2 dispose:)
				(back3 dispose:)
				(back4 dispose:)
				(back5 dispose:)
				(escapePod dispose:)
				(glass init:)
				(smoke init: setCycle: Fwd)
				(door init:)
				(= cycles 12)
			)
			(2
				(soundFx number: 808 loop: 1 play:)
				(glass setCycle: Osc 1 self)
			)
			(3 (= seconds 3))
			(4
				(theMusic setVol: 70)
				(theMusic2 fade:)
				(curRoom drawPic: 13 10)
				(curRoom overlay: 313 100)
				(smoke dispose:)
				(door dispose:)
				(glass dispose:)
				(Print 14 10)
				(screen setCycle: 0)
				(ledLights setCycle: 0)
				(= crashedIntoKerona 1)
				(self dispose:)
				(HandsOn)
			)
		)
	)
)

(instance pushButton of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(BUTTON init:)
				(= cycles 20)
			)
			(1
				(AutoNavButton dispose:)
				(DontPressButton dispose:)
				(PowerButton dispose:)
				(padInset dispose:)
				(BUTTON dispose:)
				(head cel: 1 forceUpd:)
				(= cycles 1)
			)
			(2
				(arm loop: 1 setCycle: CT 1 1 self)
			)
			(3
				(arm loop: 2 cel: register)
				(= cycles 12)
			)
			(4
				(if (not crashedIntoKerona)
					(switch register
						(1
							(cond 
								(crashedIntoKerona (Print 14 11))
								((not powerOn) (Print 14 12))
								((<= gasTimer 0) (Print 14 13))
								((not crashedIntoKerona) (theMusic stop:) (self next: pressedWrongButton))
							)
						)
						(2
							(if (not powerOn)
								(= powerOn 1)
								(ledLights show:)
								(screen show:)
								(theMusic2 pause: 0)
								(cockpitSound number: 326 loop: -1 play:)
							else
								(cockpitSound number: 326 loop: 0 stop:)
								(= powerOn 0)
								(theMusic2 pause:)
								(ledLights hide:)
								(screen hide:)
							)
						)
						(else 
							(cond 
								((not powerOn) (Print 14 12))
								((<= gasTimer 0) (Print 14 13))
								(else
									(SolvePuzzle 2 139)
									(theMusic fade:)
									(self next: autoNavScript)
								)
							)
						)
					)
				else
					(Print 14 14)
				)
				(= register 0)
				(= cycles 3)
			)
			(5
				(arm loop: 1 cel: 1 setCycle: Beg self)
			)
			(6
				(head cel: 0 forceUpd:)
				(arm loop: 1 cel: 0 stopUpd:)
				(HandsOn)
				(= cycles 1)
			)
			(7 (self dispose:))
		)
	)
)

(instance pressedWrongButton of Script
	(properties)
	
	(method (init)
		(UnLoad 132 326)
		(UnLoad 132 334)
		(UnLoad 132 336)
		(UnLoad 132 335)
		(UnLoad 132 332)
		(UnLoad 132 405)
		(UnLoad 132 401)
		(UnLoad 132 807)
		(UnLoad 132 808)
		(UnLoad 132 328)
		(UnLoad 132 325)
		(UnLoad 132 337)
		(UnLoad 129 16)
		(UnLoad 129 17)
		(UnLoad 129 38)
		(UnLoad 129 37)
		(UnLoad 129 313)
		(UnLoad 129 213)
		(UnLoad 128 138)
		(UnLoad 128 113)
		(UnLoad 128 137)
		(UnLoad 128 238)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit)
		(if local3 (Palette 6 48 70 1))
		(super doit:)
		(if
		(and (== state 1) (== (theMusic prevSignal?) 10))
			(theMusic prevSignal: 0)
			(= cycles 1)
		)
		(if
		(and (== state 6) (== (theMusic prevSignal?) 10))
			(theMusic prevSignal: 0)
			(= cycles 1)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 14 15 #at 33 139 #dispose self)
				(LoadMany 132 359 371 400 402 362 356 411)
				(Load rsPIC 69)
				(ego init: hide:)
				(= register 1)
			)
			(1
				(sounds eachElementDo: #stop)
				(theMusic number: 359 loop: 1 play:)
				(= seconds 20)
			)
			(2
				(= seconds 0)
				(self setScript: doHyperspace self 10)
			)
			(3
				(curRoom drawPic: 69 10)
				(theMusic fade:)
				(cockpitSound stop:)
				(= cycles 1)
			)
			(4 (= seconds 2))
			(5
				(= local3 1)
				(soundFx number: 371 loop: 1 play:)
				(tempProp
					init:
					posn: 297 50
					view: 169
					setLoop: 2
					cel: 0
					cycleSpeed: 8
					setCycle: End self
				)
			)
			(6
				(theMusic number: 400 loop: 1 play:)
				(tempProp setLoop: 3 cel: 0 setCycle: Fwd)
			)
			(7
				(escapePod
					init:
					view: 169
					setLoop: 0
					setStep: 13 7
					posn: 288 63
					cycleSpeed: 5
					setCycle: Fwd
					setMotion: MoveTo 228 105 self
				)
			)
			(8
				(escapePod setLoop: 1 setPri: 4 setCycle: 0)
				(= ticks 1)
			)
			(9
				(if (!= [camelotPodPosn (= i (+ i 3))] -1)
					(-- state)
					(escapePod
						cel: [camelotPodPosn i]
						x:
							[camelotPodPosn (+
								(if (== [camelotPodPosn i] 1)
									(soundFx number: 362 loop: 1 play: 127)
									(theMusic number: 402 loop: 1 play:)
								)
								1
							)]
						y: [camelotPodPosn (+ i 2)]
					)
				)
				(= cycles 1)
			)
			(10
				(escapePod setCel: 1 setMotion: MoveTo 84 168 self)
			)
			(11
				(theMusic stop:)
				(soundFx number: 411 loop: 1 play: self)
				(ego
					show:
					posn: 81 158
					view: 169
					setLoop: 4
					cel: 0
					cycleSpeed: 7
					setPri: 6
					setCycle: End self
				)
			)
			(12 0)
			(13
				(theMusic2 fade:)
				(theMusic number: 356 loop: 1 play: self)
				(tempProp setLoop: 2 cel: 3 setCycle: Beg)
				(= seconds 4)
			)
			(14 (= local3 0))
			(15
				(Print 14 16)
				(Print 14 17)
				(Print 14 18)
				(EgoDead 948 0 0 14 19)
			)
		)
	)
	
	(method (handleEvent event)
		(if (and register (event type?))
			(if modelessDialog (modelessDialog dispose:))
			(= register 0)
			(event claimed: 1)
		)
	)
)

(instance throttleUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if register
					(= register 0)
					(= seconds (= state 1))
				else
					(arm loop: 1 setCycle: End self)
				)
			)
			(1
				(cond 
					(crashedIntoKerona (Print 14 11))
					((not powerOn) (Print 14 20))
					((not gasTimer) (Print 14 21))
					(else (Print 14 22))
				)
				(= cycles 3)
			)
			(2 (arm setCycle: Beg self))
			(3 (HandsOn) (self dispose:))
		)
	)
)

(instance unBuckle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(arm loop: 3 startUpd: cel: 5)
				(theMusic2 number: 325 loop: 1 play: self)
			)
			(1
				(theMusic2 number: 337 loop: 1 play:)
				(head cel: 1 forceUpd:)
				(arm setCycle: Beg self)
				(seatbelt1 cel: 5 setCycle: Beg)
				(seatbelt2 dispose:)
			)
			(2
				(theMusic2 loop: 0 stop:)
				(head cel: 0 forceUpd:)
				(arm loop: 1 cel: 0)
				(seatbelt1 cel: 0 stopUpd:)
				(= cycles 6)
			)
			(3
				(= register 0)
				(theMusic fade: 127 25 10 0)
				(curRoom newRoom: 37)
			)
		)
	)
)

(instance joystick of Feature
	(properties
		description {thruster}
		sightAngle 45
		onMeCheck $0020
		lookStr {This lever causes forward propulsion of the pod when powered-up and in space.}
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(3
				(curRoom setScript: throttleUp)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance egoSuit of Feature
	(properties
		x 160
		y 100
		nsBottom 180
		nsRight 320
		description {Roger}
		sightAngle 45
		onMeCheck $4000
		lookStr {There doesn't seem to be enough room.}
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(super doVerb: theVerb &rest)
	)
)

(instance seatBeltFeat of Feature
	(properties
		x 293
		y 16
		nsTop -1
		nsLeft 270
		nsBottom 33
		nsRight 317
		description {seat belt}
		sightAngle 45
		onMeCheck $0040
		lookStr {The seatbelt is dummy-tested, which ought to suit you just fine.}
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(3
				(if crashedIntoKerona
					(curRoom setScript: unBuckle 0 1)
				else
					(Print 14 23)
				)
			)
			(2
				(if crashedIntoKerona
					(Print 14 24)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Dials of Feature
	(properties
		description {Dials}
		onMeCheck $0080
		lookStr {Dials galore populate the instrument console of the pod cabin. Too bad none of them work anymore.}
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(Printf
					14
					25
					(if crashedIntoKerona
						{ Too bad none of them work anymore.}
					else
						{_}
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ControlPanel of Feature
	(properties
		description {ControlPanel}
		onMeCheck $0100
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(Printf
					14
					26
					(if crashedIntoKerona
						{ Too bad none of them work anymore.}
					else
						{}
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance Chair of Feature
	(properties
		description {Chair}
		onMeCheck $0400
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(Printf
					14
					27
					(if crashedIntoKerona
						{ It might have been dampened slightly by that landing.}
					else
						{_}
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance PodWindow of Feature
	(properties
		description {Window}
		onMeCheck $0800
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(if crashedIntoKerona (Print 14 28) else (Print 14 29))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance ButtonPad of Feature
	(properties
		description {ButtonPad}
		onMeCheck $1016
		lookStr {On this button pad are the AUTONAV, POWER, and DON'T TOUCH buttons.}
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 3 2)
			(curRoom setScript: buttonInset)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance kerona of View
	(properties
		x 92
		y 189
		description {Kerona}
		lookStr {should be hands-off}
		view 117
		loop 1
	)
)

(instance survivalKit of View
	(properties
		x 295
		y 177
		description {survival kit}
		lookStr {Within the survival kit are the necessities for surviving a brief stay in a less-than-hospitable environment.}
		view 113
		loop 10
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(if crashedIntoKerona (Print 14 30) else (Print 14 31))
			)
			(3 (Print 14 32))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance back1 of View
	(properties
		x 142
		y 57
		description { mountain}
		lookStr {Mountains generously dappled with spires of unknown formational origin loom at your visual terminus.}
		view 238
		cel 1
	)
)

(instance back2 of View
	(properties
		x 59
		y 57
		description { mountain}
		lookStr {Mountains generously dappled with spires of unknown formational origin loom at your visual terminus.}
		view 238
		signal $4000
	)
)

(instance back3 of View
	(properties
		x 220
		y 57
		description { mountain}
		lookStr {Mountains generously dappled with spires of unknown formational origin loom at your visual terminus.}
		view 238
		loop 3
	)
)

(instance back4 of View
	(properties
		x 279
		y 56
		description { mountain}
		lookStr {Mountains generously dappled with spires of unknown formational origin loom at your visual terminus.}
		view 238
		loop 3
		cel 2
	)
)

(instance back5 of View
	(properties
		y 57
		description { mountain}
		lookStr {Mountains generously dappled with spires of unknown formational origin loom at your visual terminus.}
		view 238
		loop 3
		cel 2
	)
)

(instance ledLights of Prop
	(properties
		x 42
		y 112
		description {lights}
		view 113
		loop 6
		cel 2
		cycleSpeed 10
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(if crashedIntoKerona (Print 14 11) else (Print 14 33))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance screen of Prop
	(properties
		x 108
		y 117
		description {radar}
		lookStr {Simple radar.}
		view 113
		loop 5
		cycleSpeed 8
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Fwd)
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(switch theVerb
			(2
				(if crashedIntoKerona (Print 14 11) else (Print 14 34))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance arm of Prop
	(properties
		x 194
		y 86
		description { arm}
		lookStr {This is your arm.}
		view 113
		loop 1
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(super doVerb: theVerb &rest)
	)
)

(instance head of Prop
	(properties
		x 225
		y 63
		description { head}
		lookStr {This is your head. This is your head in a fishbowl.}
		view 113
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(super doVerb: theVerb &rest)
	)
)

(instance seatbelt1 of Prop
	(properties
		x 276
		y 26
		view 113
		loop 4
		cel 6
		signal $4000
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(seatBeltFeat doVerb: theVerb &rest)
	)
)

(instance seatbelt2 of Prop
	(properties
		x 194
		y 116
		z 30
		view 113
		loop 3
		cel 6
		priority 3
		signal $4010
	)
	
	(method (doVerb theVerb)
		(localproc_0020)
		(seatBeltFeat doVerb: theVerb &rest)
	)
)

(instance star1 of Prop
	(properties
		x 155
		y 51
		description { star}
		lookStr {star 1}
		view 216
		loop 4
		cel 3
		priority 1
		signal $5810
		cycleSpeed 20
	)
)

(instance star2 of Prop
	(properties
		x 271
		y 114
		description { star}
		lookStr {star 2}
		view 216
		loop 4
		cel 1
		priority 1
		signal $5810
		cycleSpeed 14
	)
)

(instance star3 of Prop
	(properties
		x 105
		y 154
		description { star}
		lookStr {don't need}
		view 216
		loop 4
		cel 2
		priority 1
		signal $5810
		cycleSpeed 16
	)
)

(instance smoke of Prop
	(properties
		x 195
		y 57
		description {smoke}
		lookStr {The spent drives of the pod vent fumes after the long escape from the Arcada.}
		view 137
		cycleSpeed 20
	)
)

(instance door of Prop
	(properties
		x 126
		y 98
		description {door}
		lookStr {This is the escape pod's hatch.}
		view 137
		loop 1
		priority 12
		signal $4010
		cycleSpeed 28
	)
)

(instance glass of Prop
	(properties
		x 96
		y 151
		description {glass}
		lookStr {glass}
		view 137
		loop 2
	)
)

(instance hologram of Prop
	(properties
		x 90
		y 36
		description { hologram}
		lookStr {hologram}
		view 113
		loop 7
		signal $6000
	)
)

(instance tempProp of Prop
	(properties
		view 113
		loop 9
		signal $6000
	)
	
	(method (init)
		(super init: &rest)
		(if (== view 116)
			(self
				x: (+ (escapePod x?) 37)
				y: (- (escapePod y?) 12)
			)
		)
	)
	
	(method (doit)
		(if (== view 116)
			(self
				x: (+ (escapePod x?) 37)
				y: (- (escapePod y?) 12)
			)
		)
		(super doit:)
	)
	
	(method (cue)
		(self hide:)
		(super cue:)
	)
)

(instance escapePod of Actor
	(properties
		x 223
		y 70
		description {escape pod}
		lookStr {escape pod}
		yStep 12
		view 216
		loop 3
		signal $6800
		cycleSpeed 5
		xStep 25
		moveSpeed 1
	)
)

(instance cockpitSound of Sound
	(properties
		number 326
	)
)

(instance AutoNavButton of Feature
	(properties
		x 62
		y 34
		nsTop 24
		nsLeft 54
		nsBottom 40
		nsRight 70
		description {Autonav button}
		sightAngle 0
		lookStr {This is the Autonav button. When operative it allows the pod to navigate to the closest habitable planet.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(curRoom setScript: pushButton 0 0)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance DontPressButton of Feature
	(properties
		x 86
		y 34
		nsTop 24
		nsLeft 80
		nsBottom 40
		nsRight 96
		description {button}
		sightAngle 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if crashedIntoKerona (Print 14 35) else (Print 14 36))
			)
			(3
				(curRoom setScript: pushButton 0 1)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance PowerButton of Feature
	(properties
		x 110
		y 34
		nsTop 24
		nsLeft 104
		nsBottom 40
		nsRight 120
		description {power button}
		sightAngle 0
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(Printf
					14
					37
					(if crashedIntoKerona
						{ Its service life has expired as a result of the rugged touch-down.}
					else
						{_}
					)
				)
			)
			(3
				(curRoom setScript: pushButton 0 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance padInset of View
	(properties
		x 87
		y 14
		description {button pad}
		lookStr {Please press the desired button.}
		view 113
		loop 11
		signal $6001
	)
)

(instance BUTTON of View
	(properties
		view 113
		loop 11
		cel 1
		priority 5
		signal $6010
	)
	
	(method (init)
		(localproc_000e)
		(self
			x: ((CueObj client?) x?)
			y: ((CueObj client?) y?)
		)
		(super init: &rest)
	)
)
