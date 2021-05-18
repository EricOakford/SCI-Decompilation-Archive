;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include game.sh)
(use Main)
(use Intrface)
(use Wander)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	killrudy 0
)

(local
	local0
	theCycles
	local2
	local3
	local4
	eyeLoop
)
(procedure (localproc_0056)
	(HandsOff)
	(TheMenuBar state: FALSE)
	(fight cycles: 0 seconds: 0)
	(Duo setLoop: 0)
	(ego
		view: 52
		loop: (if (< (ego x?) (Duo x?)) 0 else 1)
		cel: 0
		setCycle: EndLoop fight
	)
)

(procedure (localproc_00b0 &tmp temp0 rudyY)
	(= temp0 (+ (Rudy x?) 7))
	(= rudyY (Rudy y?))
	(TalkingHead posn: temp0 (- rudyY 44) init:)
	(RightArm posn: (- temp0 4) (- rudyY 32) init:)
	(LeftArm
		setLoop:
		(switch local4
			(1 8)
			(else  7)
		)
		posn: (+ temp0 4) (- rudyY 37)
		init:
	)
	(Rudy
		view: 385
		setLoop: 3
		setCel: 1
		posn: temp0 rudyY
		setScript: RudyTalking
		stopUpd:
	)
)

(procedure (Measure &tmp [str 500])
	(GetFarText &rest @str)
	(if (< (= theCycles (/ (StrLen @str) 2)) 20)
		(= theCycles 20)
	)
)

(procedure (TalkPrint)
	(if local2
		(Measure &rest)
		(Mouth setScript: cycleMouth)
	)
	(Print &rest
		#at 15 10
		#font 4
		#mode teJustCenter
		#draw
		#width 280
		#dispose
	)
)

(procedure (localproc_01ab)
	(DrawPic 282 IRISIN TRUE currentPalette)
	(DrawPic local0 IRISOUT FALSE currentPalette)
	(cast eachElementDo: #hide)
	(Head show:)
	(Mouth show:)
	(Eye setScript: BigEye show:)
	(features dispose:)
)

(procedure (localproc_01f8)
	(DrawPic curRoomNum IRISOUT)
	(addToPics doit:)
	(cast eachElementDo: #show)
	(Head hide:)
	(Mouth hide:)
	(Eye setScript: 0 hide:)
	((gGate downID?) hide:)
	((gGate upID?) hide:)
)

(procedure (localproc_024f param1)
	(Head cel: param1)
	(Eye loop: (+ 2 (* param1 3)) cel: 0)
	(= eyeLoop (Eye loop?))
	(Mouth loop: (+ 3 (* param1 3)) cel: 1)
	(= local2 1)
	(localproc_01ab)
)

(instance myMusic of Sound)

(instance arena of Cage)

(instance killrudy of Region
	
	(method (init)
		(super init:)
		(Load FONT 4)
		(LoadMany PICTURE 182 282 382)
		(LoadMany VIEW 52 182 301 308 314 380 383 385 395)
		(LoadMany SOUND 24 25 31 34 56)
		(= global195 768)
		(Head init: hide:)
		(Eye init: hide:)
		(Mouth init: hide:)
		(if (!= [global368 0] 1)
			(arena left: 95 right: 213 bottom: 144 top: 124 init:)
			(Duo observeBlocks: arena init:)
		else
			(Bset 33)
			(HandsOff)
			(TheMenuBar state: FALSE)
			(Rudy view: 385 loop: 2 posn: 130 126)
			(Rudy cel: (Rudy lastCel:) init:)
			(Colonel view: 385 loop: 1 posn: 133 128)
			(Colonel cel: (Colonel lastCel:) stopUpd: init:)
		)
		(= [global368 0] 0)
		(self setScript: fight)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 976)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(super handleEvent: event)
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'examine>')
					(if (Said '/rudolph,colonel')
						(Print 290 0)
					)
				)
				((Said 'converse')
					(Print 290 1)
				)
				(
					(or
						(Said 'load/derringer')
						(Said 'attach,load/bullet/derringer<in,in')
					)
					(cond 
						(gunIsLoaded
							(Print 290 2)
						)
						((ego has: iDerringer)
							(if (ego has: iBullet)
								(Ok)
								(ego put: iBullet 99)
								(= gunIsLoaded TRUE)
							else
								(Print 290 3)
							)
						)
						(else
							(Print 290 4)
						)
					)
				)
				((Said '*[/*]')
					(Print 290 5)
				)
			)
		)
	)
	
	(method (newRoom n)
		(= saveDisabled FALSE)
		(TheMenuBar state: TRUE)
		(super newRoom: n)
	)
)

(instance fight of Script

	(method (changeState newState &tmp colonelX colonelY)
		(switch (= state newState)
			(0
				(User canInput: FALSE)
				(if (Btst 33)
					(= state 3)
				)
				(= cycles 4)
			)
			(1
				(User canInput: TRUE canControl: FALSE)
				(Print 290 0)
				(myMusic number: 24 loop: -1 play:)
				(Duo setLoop: 1 setCycle: Forward setMotion: Wander 10)
				(= seconds 10)
			)
			(2
				(HandsOff)
				(TheMenuBar state: FALSE)
				(Duo setLoop: 0 setMotion: 0 setCycle: EndLoop self)
			)
			(3
				(Colonel
					view: 385
					loop: 1
					cel: 0
					posn: (- (Duo x?) 2) (Duo y?)
					setCycle: EndLoop
					init:
				)
				(Rudy
					view: 385
					loop: 2
					cel: 0
					posn: (+ (Duo x?) 2) (Duo y?)
					cycleSpeed: 2
					setCycle: EndLoop self
					init:
				)
				(Duo dispose:)
			)
			(4
				(myMusic number: 56 loop: -1 play:)
				(LeftArm setLoop: 7)
				(localproc_00b0)
				(if (Btst 33)
					(Print 290 10)
					(= cycles 1)
				else
					(= cycles 14)
				)
			)
			(5
				(= local0 182)
				(TheMenuBar state: TRUE)
				(localproc_024f 0)
				(if (Btst 33)
					(TalkPrint 290 11)
				else
					(TalkPrint 290 12)
				)
				(= seconds 10)
			)
			(6
				(TalkPrint 290 13)
				(= state 36)
				(= seconds 10)
			)
			(7 (localproc_0056))
			(8
				(myMusic number: 25 loop: 1 play:)
				(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: EndLoop)
				(Colonel
					view: 301
					loop: 0
					posn: (Duo x?) (Duo y?)
					setPri: 9
					init:
				)
				(Rudy
					view: 383
					cel: 0
					loop: 1
					posn: (Duo x?) (Duo y?)
					setPri: 8
					setCycle: EndLoop self
					init:
				)
				(Duo setMotion: 0 ignoreBlocks: arena hide:)
			)
			(9
				(Rudy stopUpd:)
				(myMusic number: 34 loop: 1 play:)
				(ego view: 0 loop: 3 setCycle: Walk)
				(Print 290 14)
				(= cycles 1)
			)
			(10
				(= seconds (= cycles 0))
				(Colonel
					setStep: 2 2
					cycleSpeed: 1
					moveSpeed: 1
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 10) self
				)
			)
			(11
				(= colonelX (Colonel x?))
				(= colonelY (Colonel y?))
				(Duo
					view: 314
					loop: 0
					posn: colonelX colonelY
					show:
					stopUpd:
				)
				(Colonel
					view: 314
					loop: 1
					cel: 0
					posn: colonelX (- colonelY 22)
					cycleSpeed: 1
					setCycle: EndLoop self
				)
			)
			(12
				(TheMenuBar state: TRUE)
				(= local0 382)
				(localproc_024f 1)
				(= cycles 10)
			)
			(13
				(myMusic number: 31 loop: -1 play:)
				(TalkPrint 290 15)
				(= seconds 18)
			)
			(14
				(TalkPrint 290 16)
				(= seconds 12)
			)
			(15
				(cls)
				(= local2 0)
				(localproc_01f8)
				(Colonel setScript: coloTalking)
				(= cycles 10)
			)
			(16
				(TalkPrint 290 17)
				(= seconds 18)
			)
			(17
				(TalkPrint 290 18)
				(= seconds 12)
			)
			(18
				(cls)
				(= local2 1)
				(localproc_01ab)
				(= cycles 10)
			)
			(19
				(TalkPrint 290 19)
				(= seconds 12)
			)
			(20
				(TalkPrint 290 20)
				(= seconds 12)
			)
			(21
				(cls)
				(= local2 0)
				(localproc_01f8)
				(= cycles 10)
			)
			(22
				(TalkPrint 290 21)
				(= seconds 12)
			)
			(23
				(cls)
				(myMusic fade:)
				(= cycles 21)
			)
			(24 
				(curRoom newRoom: 784)
			)
			(25 (localproc_0056))
			(26
				(= local4 1)
				(myMusic number: 25 loop: 1 play:)
				(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: EndLoop)
				(Rudy
					view: 380
					cel: 0
					loop: 2
					posn: (+ (Duo x?) 2) (Duo y?)
					setPri: 9
					init:
				)
				(Colonel
					view: 308
					cel: 0
					loop: 0
					posn: (- (Duo x?) 2) (Duo y?)
					setPri: 8
					setCycle: EndLoop self
					init:
				)
				(Duo dispose:)
			)
			(27
				(Colonel stopUpd:)
				(myMusic number: 34 loop: 1 play:)
				(= seconds 1)
			)
			(28
				(Rudy
					ignoreActors: 1
					setCycle: Walk
					setMotion: MoveTo (- (Colonel x?) 5) (- (Colonel y?) 16) self
				)
				(ego view: 0 loop: 3 setCycle: 0)
			)
			(29
				(Rudy view: 393 loop: 0 cel: 0 setCycle: EndLoop self)
			)
			(30 (= seconds 3))
			(31 (Rudy setCycle: BegLoop self))
			(32
				(myMusic number: 56 loop: -1 play:)
				(LeftArm setLoop: 8)
				(localproc_00b0)
				(TalkPrint 290 22)
				(= seconds 4)
			)
			(33
				(TheMenuBar state: 1)
				(cls)
				(= local0 182)
				(localproc_024f 0)
				(= cycles 10)
			)
			(34
				(TalkPrint 290 23)
				(Mouth setCycle: Forward)
				(= seconds 16)
			)
			(35
				(TalkPrint 290 24)
				(= seconds 5)
			)
			(36
				(TalkPrint 290 25)
				(= seconds 5)
			)
			(37
				(cls)
				(= local2 0)
				(localproc_01f8)
				(= cycles 10)
			)
			(38
				(TalkPrint 290 26)
				(= seconds 10)
			)
			(39
				(TalkPrint 290 27)
				(= seconds 10)
			)
			(40
				(TalkPrint 290 28)
				(= seconds 10)
			)
			(41
				(cls)
				(= local2 1)
				(localproc_01ab)
				(= cycles 10)
			)
			(42
				(TalkPrint 290 29)
				(= seconds 10)
			)
			(43
				(TalkPrint 290 30)
				(= seconds 10)
			)
			(44
				(myMusic fade:)
				(if local4
					(TalkPrint 290 31)
					(= seconds 4)
				else
					(= cycles 10)
				)
			)
			(45
				(cls)
				(curRoom newRoom: 785)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if (event claimed?) (return))
		(super handleEvent: event)
		(if (== (event type?) saidEvent)
			(cond 
				(
					(or
						(Said 'shoot,kill/rudolph')
						(Said 'shoot/derringer/rudolph')
					)
					(cond 
						(gunIsLoaded
							(if (== state 1)
								(= state 6)
								(= cycles 1)
								(= gunIsLoaded FALSE)
							else
								(Print 290 6)
							)
						)
						((ego has: iDerringer)
							(Print 290 7)
						)
						(else
							(Print 290 8)
						)
					)
				)
				(
					(or
						(Said 'shoot,kill/colonel')
						(Said 'shoot/derringer/colonel')
					)
					(cond 
						(gunIsLoaded
							(if (== state 1)
								(= state 24)
								(= gunIsLoaded FALSE)
								(= cycles 1)
							else
								(Print 290 6)
							)
						)
						((ego has: iDerringer)
							(Print 290 7)
						)
						(else
							(Print 290 8)
						)
					)
				)
				((Said 'shoot/derringer')
					(Print 290 9)
				)
			)
		)
	)
)

(instance cycleMouth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 0 setCycle: Forward show:)
				(= cycles theCycles)
			)
			(1
				(client cel: 0 setCycle: 0 setScript: 0)
				(self client: 0)
			)
		)
	)
)

(instance BigEye of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Eye setCel: 0 forceUpd:)
				(= seconds (Random 1 3))
			)
			(1
				(if (and (not local3) (== (Random 1 2) 1))
					(Eye setLoop: (- eyeLoop 1) setCel: 1 forceUpd:)
					(= local3 1)
					(= cycles 1)
				else
					(Eye setLoop: eyeLoop setCel: 1 forceUpd:)
					(= local3 0)
					(= seconds (Random 1 3))
				)
				(= state -1)
			)
		)
	)
)

(instance coloTalking of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(Colonel
					loop: (Random 2 5)
					cel: 0
					cycleSpeed: (Random 2 4)
					setCycle: Forward
				)
				(= cycles (Random 8 16))
			)
			(1
				(Colonel loop: 5)
				(= state -1)
				(= cycles (Random 4 8))
			)
		)
	)
)

(instance RudyTalking of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(TalkingHead setCycle: Forward)
				(RightArm setCycle: Forward)
				(LeftArm setCycle: Forward)
				(= cycles 1)
			)
			(1
				(TalkingHead loop: (Random 4 5))
				(RightArm cycleSpeed: (Random 6 8))
				(LeftArm cycleSpeed: (+ (RightArm cycleSpeed?) 2))
				(= state 0)
				(= seconds 1)
			)
		)
	)
)

(instance Rudy of Actor
	(properties
		y 113
		x 114
		view 383
		signal ignrAct
	)
)

(instance Colonel of Actor
	(properties
		y 113
		x 114
		view 308
		signal ignrAct
		illegalBits $0000
	)
)

(instance Duo of Actor
	(properties
		y 129
		x 114
		view 395
		signal ignrAct
		cycleSpeed 1
		illegalBits $0000
	)
)

(instance Head of Prop
	(properties
		y 133
		x 157
		view 182
	)
)

(instance Mouth of Prop
	(properties
		y 127
		x 148
		view 182
	)
)

(instance Eye of Prop
	(properties
		y 90
		x 142
		view 182
	)
)

(instance TalkingHead of Prop
	(properties
		view 385
		loop 4
		cycleSpeed 1
	)
)

(instance RightArm of Prop
	(properties
		view 385
		loop 6
	)
)

(instance LeftArm of Prop
	(properties
		view 385
	)
)
