;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use Arcada)
(use RandCyc)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm13 0
)

(local
	powerOn
	local1
	wearingSeatbelt
)
(procedure (localproc_000e)
	(buttonSound number: 317 loop: 1 play:)
)

(procedure (RemovePadInset)
	(if (cast contains: padInset)
		(AutoNavButton dispose:)
		(DontPressButton dispose:)
		(PowerButton dispose:)
		(padInset dispose:)
	)
	(Animate (cast elements?) FALSE)
)

(instance rm13 of Room
	(properties
		lookStr {The inside of the Arcada's escape pod is not exactly packed with luxurious appointments. However, when it comes to saving one's posterior, the pod's as good as a Rolls.}
		picture 13
	)
	
	(method (init)
		(ego view: 0)
		(= currentFloor 1)
		(Load PICTURE 213)
		(LoadMany VIEW 216 113)
		(LoadMany SOUND 805 326 804 333 370)
		(self setRegions: ARCADA)
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
		(ledLights init:)
		(survivalKit init: stopUpd:)
		(screen init:)
		(if (Btst fBayDoorsOpen)
			(curRoom overlay: 413)
		else
			(curRoom overlay: 513)
		)
		(if (ArcadaCheck 553 1)
			(= powerOn TRUE)
			(cockpitSound number: 326 loop: -1 flags: mNOPAUSE play:)
			(ledLights setCycle: Forward)
			(screen setCycle: Forward)
		else
			(ledLights hide:)
			(screen hide:)
		)
		(head init: stopUpd:)
		(arm init: stopUpd:)
		(seatbelt1 init: stopUpd:)
		(HandsOn)
	)
	
	(method (dispose)
		(= currentFloor 2)
		(cockpitSound loop: 0 fade:)
		(soundFx loop: 0 stop: number: 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
				(or
					(== (event message?) dirN)
					(& (event type?) direction)
				)
			)
			(cond 
				((not wearingSeatbelt) (Print 13 0) (curRoom newRoom: 12))
				(wearingSeatbelt (Print 13 1))
			)
			(event claimed: TRUE)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(super doVerb: theVerb &rest)
	)
	
	(method (newRoom n)
		(if (and (== n 12) (!= (music number?) 355))
			(music fade:)
		)
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
					(Print 13 2)
				)
				(= cycles 1)
			)
			(1 (self dispose:))
		)
	)
)

(instance escapeArcada of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom drawPic: 16 FADEOUT)
				(ledLights dispose:)
				(survivalKit dispose:)
				(screen dispose:)
				(globalSound pause:)
				(cockpitSound stop:)
				(if (> selfDestructTimer 30)
					(globalSound number: 370 loop: 0 play:)
				)
				(deltaurArm init:)
				(arcada init:)
				(deltaur init:)
				(star1 init: setCycle: Forward)
				(star2 init: setCycle: Forward)
				(star3 init: setCycle: Forward)
				(= cycles 3)
			)
			(1
				(UnLoad SOUND 326)
				(cockpitSound number: 804 loop: 1 play:)
				(deltaur
					illegalBits: 0
					setMotion: MoveTo (- (deltaur x?) 50) (- (deltaur y?) 120)
				)
				(deltaurArm
					setMotion: MoveTo (- (deltaurArm x?) 50) (- (deltaurArm y?) 120)
					setCycle: EndLoop
				)
				(= seconds 4)
			)
			(2
				(soundFx number: 333 loop: 1 play:)
				(escapePod
					init:
					illegalBits: 0
					setCycle: EndLoop
					setMotion: MoveTo -75 160
				)
				(= cycles 20)
			)
			(3
				(cockpitSound number: 805 loop: 1 play:)
				(arcada setCycle: EndLoop arcada)
				(= seconds 6)
			)
			(4 (curRoom newRoom: 14))
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
				(arm loop: 1 setCycle: CycleTo 1 1 self)
			)
			(3
				(arm loop: 2 cel: register)
				(= cycles 12)
			)
			(4
				(switch register
					(1
						(if (not powerOn)
							(Print 13 3)
						else
							(Print 13 4)
						)
					)
					(2
						(if (not powerOn)
							(= powerOn 1)
							((ScriptID ARCADA 0)
								rFlag1: (| ((ScriptID ARCADA 0) rFlag1?) $0001)
							)
							(ledLights show:)
							(screen show:)
							(if (not wearingSeatbelt)
								(soundFx number: 331 loop: 6 play:)
							)
							(globalSound number: 328 loop: 1 flags: mNOPAUSE play: hold: 1)
							(cockpitSound number: 326 loop: -1 flags: mNOPAUSE play:)
						else
							(soundFx loop: 0 stop:)
							(globalSound stop: hold: 0)
							(cockpitSound number: 326 loop: 0 stop:)
							(= powerOn 0)
							((ScriptID ARCADA 0)
								rFlag1: (| ((ScriptID ARCADA 0) rFlag1?) $fffe)
							)
							(ledLights hide:)
							(screen hide:)
						)
					)
					(else 
						(if (not powerOn)
							(Print 13 3)
						else
							(Print 13 5)
						)
					)
				)
				(= register 0)
				(= cycles 3)
			)
			(5
				(arm loop: 1 cel: 1 setCycle: BegLoop self)
			)
			(6
				(head cel: 0 forceUpd:)
				(arm loop: 1 cel: 0)
				(= cycles 2)
			)
			(7
				(arm stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance throttleUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if powerOn ((ScriptID ARCADA 0) inGame: 0))
				(if register
					(= register 0)
					(= state 2)
					(= seconds 1)
				else
					(arm loop: 1 setCycle: EndLoop self)
				)
			)
			(1
				(= state 2)
				(cond 
					((not powerOn) (Print 13 6) (= cycles 3))
					((not (Btst 10))
						((ScriptID ARCADA 0) inGame: 0)
						(globalSound number: 327 loop: 1 play: hold: 1)
						(curRoom setScript: crashThruDoors)
					)
					((not wearingSeatbelt)
						(EgoDead 948 0 0 13 7)
					)
					(else
						(= state 1)
						(UnLoad SOUND 328)
						(UnLoad SOUND 337)
						(UnLoad SOUND 325)
						(globalSound number: 327 loop: 1 flags: 1 play: hold: 1)
						(= cycles 60)
					)
				)
			)
			(2
				(Print 13 8)
				(SolvePuzzle 15 f13EscapeArcada)
				(curRoom setScript: escapeArcada)
				(= cycles 3)
			)
			(3 (arm setCycle: BegLoop self))
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance buckleUp of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(head cel: 1)
				(arm loop: 3 cel: 0 setCycle: CycleTo 2 1 self)
				(seatbelt1 setCycle: CycleTo 2 1)
			)
			(1
				(seatBeltSound number: 337 loop: 1 play:)
				(arm loop: 3 cel: 3 setCycle: CycleTo 5 1 self)
				(seatbelt1 cel: 3 setCycle: CycleTo 5 1)
			)
			(2
				(soundFx loop: 0 stop:)
				(seatBeltSound number: 325 loop: 1 play:)
				(head cel: 0 forceUpd:)
				(seatbelt1 cel: 6)
				(seatbelt2 init: setPri: 3)
				(= wearingSeatbelt TRUE)
				(= ticks 40)
			)
			(3
				(arm loop: 1 cel: 0)
				(HandsOn)
				(self dispose:)
			)
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
				(seatBeltSound number: 325 loop: 1 play: self)
			)
			(1
				(seatBeltSound number: 337 loop: 1 play:)
				(head cel: 1 forceUpd:)
				(arm setCycle: BegLoop self)
				(seatbelt1 cel: 5 setCycle: BegLoop)
				(seatbelt2 dispose:)
				(= wearingSeatbelt FALSE)
			)
			(2
				(seatBeltSound loop: 0 stop:)
				(head cel: 0 forceUpd:)
				(arm loop: 1 cel: 0)
				(seatbelt1 cel: 0 stopUpd:)
				(= cycles 6)
			)
			(3
				(if register
					(= register 0)
					(music fade: 127 25 10 0)
					(curRoom newRoom: 37)
				else
					(HandsOn)
					(self dispose:)
				)
			)
		)
	)
)

(instance crashThruDoors of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(curRoom drawPic: 14 -32759)
				(globalSound pause:)
				(cockpitSound stop:)
				(deltaurArm
					init:
					view: 114
					setLoop: 0
					setCel: 0
					posn: 201 76
					setPri: 12
					stopUpd:
				)
				(armsR init: stopUpd:)
				(buldge init:)
				(= cycles 1)
			)
			(1 (= seconds 2))
			(2
				(soundFx number: 361 loop: 1 play:)
				(buldge setPri: 11 cycleSpeed: 8 setCycle: EndLoop self)
			)
			(3 (= seconds 2))
			(4
				(soundFx number: 805 loop: 1 play:)
				(xplosion
					init:
					setPri: 13
					cycleSpeed: 4
					setCycle: CycleTo 5 1 self
				)
			)
			(5
				(xplosion setCycle: EndLoop self)
				(buldge posn: 167 164 setPri: 12 setLoop: 4 cel: 0)
			)
			(6
				(xplosion dispose:)
				(= seconds 3)
			)
			(7
				(EgoDead 945 0 0 13 9)
				(self dispose:)
			)
		)
	)
)

(instance joystick of Feature
	(properties
		description {thruster}
		sightAngle 45
		onMeCheck $0020
		lookStr {This lever causes forward propulsion of the pod when powered-up.}
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(switch theVerb
			(verbDo
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
		onMeCheck ignrAct
		lookStr {There doesn't seem to be enough room.}
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
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
		(RemovePadInset)
		(switch theVerb
			(verbDo
				(cond 
					(wearingSeatbelt (curRoom setScript: unBuckle))
					((not wearingSeatbelt) (curRoom setScript: buckleUp))
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
		lookStr {Dials galore populate the instrument console of the pod cabin.}
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(super doVerb: theVerb &rest)
	)
)

(instance ControlPanel of Feature
	(properties
		description {ControlPanel}
		onMeCheck $0100
		lookStr {This is the pod's instrument console. Dials, gauges, and a couple of controls fill out the board.}
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(super doVerb: theVerb &rest)
	)
)

(instance Chair of Feature
	(properties
		description {Chair}
		onMeCheck $0400
		lookStr {This is the lucky escapee's seat.}
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(super doVerb: theVerb &rest)
	)
)

(instance PodWindow of Feature
	(properties
		description {Window}
		onMeCheck $0800
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(switch theVerb
			(verbLook
				(if (Btst fBayDoorsOpen)
					(Print 13 10)
				else
					(Print 13 11)
				)
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
		lookStr {This is a button pad.}
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb verbDo verbLook)
			(curRoom setScript: buttonInset)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance armsR of View
	(properties
		x 201
		y 76
		description {deltaur arms}
		lookStr {don't need this}
		view 114
	)
)

(instance survivalKit of View
	(properties
		x 295
		y 177
		description {survival kit}
		lookStr {The survival kit contains the basics for deep space survival.}
		view 113
		loop 10
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(switch theVerb
			(verbDo
				(Print 13 12)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance buldge of Prop
	(properties
		x 167
		y 154
		description {buldge}
		lookStr {don't need one.}
		view 214
		loop 2
	)
)

(instance xplosion of Prop
	(properties
		x 172
		y 152
		description {explosion}
		lookStr {don't need one.}
		view 214
		loop 3
	)
)

(instance ledLights of Prop
	(properties
		x 42
		y 112
		description {lights}
		lookStr {Nice lights, huh?}
		view 113
		loop 6
		cel 2
		cycleSpeed 8
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: RandCycle)
	)
)

(instance screen of Prop
	(properties
		x 108
		y 117
		description {radar}
		lookStr {This is radar for the Autonav system.}
		view 113
		loop 5
		cycleSpeed 8
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Forward)
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
		(RemovePadInset)
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
		(RemovePadInset)
		(super doVerb: theVerb &rest)
	)
)

(instance seatbelt1 of Prop
	(properties
		x 276
		y 26
		view 113
		loop 4
		signal ignrAct
		cycleSpeed 8
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
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
		signal ignrAct
	)
	
	(method (doVerb theVerb)
		(RemovePadInset)
		(seatBeltFeat doVerb: theVerb &rest)
	)
)

(instance star1 of Prop
	(properties
		x 155
		y 51
		description {star}
		lookStr {Stars glow from afar.}
		view 216
		loop 4
		cel 3
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		cycleSpeed 20
	)
)

(instance star2 of Prop
	(properties
		x 271
		y 114
		description { star}
		lookStr {Stars glow from afar.}
		view 216
		loop 4
		cel 1
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		cycleSpeed 14
	)
)

(instance star3 of Prop
	(properties
		x 105
		y 154
		description { star}
		lookStr {Stars glow from afar.}
		view 216
		loop 4
		cel 2
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		cycleSpeed 8
	)
)

(instance arcada of Prop
	(properties
		x 247
		y 100
		description {Arcada}
		lookStr {It's your former ship, the Arcada.}
		view 216
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 5
	)
	
	(method (cue)
		(self dispose:)
	)
)

(instance deltaur of Actor
	(properties
		x 253
		y 49
		description {Deltaur}
		lookStr {Deltaur lookStr}
		view 216
		loop 1
		signal (| ignrAct ignrHrz fixedLoop)
		xStep 1
	)
)

(instance deltaurArm of Actor
	(properties
		x 253
		y 49
		description { deltaur}
		lookStr {don't need this}
		view 216
		loop 2
		priority 12
		signal (| ignrAct ignrHrz fixedLoop fixedCel fixPriOn)
		cycleSpeed 5
	)
)

(instance escapePod of Actor
	(properties
		x 223
		y 70
		description {escape pod}
		lookStr {This is the escape pod. It is currently occupied by you.}
		yStep 12
		view 216
		loop 3
		signal (| ignrAct ignrHrz fixedLoop)
		cycleSpeed 5
		xStep 25
		moveSpeed 4
	)
)

(instance cockpitSound of Sound
	(properties
		number 326
	)
)

(instance buttonSound of Sound
	(properties)
)

(instance seatBeltSound of Sound
	(properties)
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
			(verbDo
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
			(verbLook
				(Print 13 13)
			)
			(verbDo
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
			(verbLook
				(Print 13 14)
			)
			(verbDo
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
		lookStr {Choose the appropriate button.}
		view 113
		loop 11
		signal (| ignrAct ignrHrz stopUpdOn)
	)
)

(instance BUTTON of View
	(properties
		view 113
		loop 11
		cel 1
		priority 5
		signal (| ignrAct ignrHrz fixPriOn)
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
