;;; Sierra Script 1.0 - (do not remove this comment)
(script# 290)
(include sci.sh)
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
	(TheMenuBar state: 0)
	(fight cycles: 0 115 0)
	(Duo setLoop: 0)
	(ego
		view: 52
		loop: (if (< (ego x?) (Duo x?)) 0 else 1)
		cel: 0
		setCycle: End fight
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

(procedure (localproc_014d &tmp [temp0 500])
	(GetFarText &rest @temp0)
	(if (< (= theCycles (/ (StrLen @temp0) 2)) 20)
		(= theCycles 20)
	)
)

(procedure (localproc_0174)
	(if local2
		(localproc_014d &rest)
		(Mouth setScript: cycleMouth)
	)
	(Print
		&rest
		#at
		15
		10
		#font
		4
		#mode
		1
		#draw
		#width
		280
		#dispose
	)
)

(procedure (localproc_01ab)
	(DrawPic 282 dpOPEN_EDGECENTER TRUE currentPalette)
	(DrawPic local0 dpOPEN_CENTEREDGE FALSE currentPalette)
	(cast eachElementDo: #hide)
	(Head show:)
	(Mouth show:)
	(Eye setScript: BigEye show:)
	(features dispose:)
)

(procedure (localproc_01f8)
	(DrawPic curRoomNum dpOPEN_CENTEREDGE)
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

(instance myMusic of Sound
	(properties)
)

(instance arena of Cage
	(properties)
)

(instance killrudy of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(LoadMany 129 182 282 382)
		(LoadMany 128 52 182 301 308 314 380 383 385 395)
		(LoadMany 132 24 25 31 34 56)
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
			(TheMenuBar state: 0)
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
		(if (== (event type?) evSAID)
			(cond 
				((Said 'examine>') (if (Said '/rudolph,colonel') (Print 290 0)))
				((Said 'converse') (Print 290 1))
				(
					(or
						(Said 'load/derringer')
						(Said 'attach,load/bullet/derringer<in,in')
					)
					(cond 
						(gunIsLoaded (Print 290 2))
						((ego has: 15)
							(if (ego has: 14)
								(Ok)
								(ego put: 14 99)
								(= gunIsLoaded 1)
							else
								(Print 290 3)
							)
						)
						(else (Print 290 4))
					)
				)
				((Said '*[/*]') (Print 290 5))
			)
		)
	)
	
	(method (newRoom n)
		(= saveDisabled 0)
		(TheMenuBar state: 1)
		(super newRoom: n)
	)
)

(instance fight of Script
	(properties)
	
	(method (changeState newState &tmp colonelX colonelY)
		(switch (= state newState)
			(0
				(User canInput: 0)
				(if (Btst 33) (= state 3))
				(= cycles 4)
			)
			(1
				(User canInput: 1 canControl: 0)
				(Print 290 0)
				(myMusic number: 24 loop: -1 play:)
				(Duo setLoop: 1 setCycle: Fwd setMotion: Wander 10)
				(= seconds 10)
			)
			(2
				(HandsOff)
				(TheMenuBar state: 0)
				(Duo setLoop: 0 setMotion: 0 setCycle: End self)
			)
			(3
				(Colonel
					view: 385
					loop: 1
					cel: 0
					posn: (- (Duo x?) 2) (Duo y?)
					setCycle: End
					init:
				)
				(Rudy
					view: 385
					loop: 2
					cel: 0
					posn: (+ (Duo x?) 2) (Duo y?)
					cycleSpeed: 2
					setCycle: End self
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
				(TheMenuBar state: 1)
				(localproc_024f 0)
				(if (Btst 33)
					(localproc_0174 290 11)
				else
					(localproc_0174 290 12)
				)
				(= seconds 10)
			)
			(6
				(localproc_0174 290 13)
				(= state 36)
				(= seconds 10)
			)
			(7 (localproc_0056))
			(8
				(myMusic number: 25 loop: 1 play:)
				(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: End)
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
					setCycle: End self
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
					setCycle: End self
				)
			)
			(12
				(TheMenuBar state: 1)
				(= local0 382)
				(localproc_024f 1)
				(= cycles 10)
			)
			(13
				(myMusic number: 31 loop: -1 play:)
				(localproc_0174 290 15)
				(= seconds 18)
			)
			(14
				(localproc_0174 290 16)
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
				(localproc_0174 290 17)
				(= seconds 18)
			)
			(17
				(localproc_0174 290 18)
				(= seconds 12)
			)
			(18
				(cls)
				(= local2 1)
				(localproc_01ab)
				(= cycles 10)
			)
			(19
				(localproc_0174 290 19)
				(= seconds 12)
			)
			(20
				(localproc_0174 290 20)
				(= seconds 12)
			)
			(21
				(cls)
				(= local2 0)
				(localproc_01f8)
				(= cycles 10)
			)
			(22
				(localproc_0174 290 21)
				(= seconds 12)
			)
			(23
				(cls)
				(myMusic fade:)
				(= cycles 21)
			)
			(24 (curRoom newRoom: 784))
			(25 (localproc_0056))
			(26
				(= local4 1)
				(myMusic number: 25 loop: 1 play:)
				(ego loop: (+ (ego loop?) 2) cel: 0 setCycle: End)
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
					setCycle: End self
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
				(Rudy view: 393 loop: 0 cel: 0 setCycle: End self)
			)
			(30 (= seconds 3))
			(31 (Rudy setCycle: Beg self))
			(32
				(myMusic number: 56 loop: -1 play:)
				(LeftArm setLoop: 8)
				(localproc_00b0)
				(localproc_0174 290 22)
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
				(localproc_0174 290 23)
				(Mouth setCycle: Fwd)
				(= seconds 16)
			)
			(35
				(localproc_0174 290 24)
				(= seconds 5)
			)
			(36
				(localproc_0174 290 25)
				(= seconds 5)
			)
			(37
				(cls)
				(= local2 0)
				(localproc_01f8)
				(= cycles 10)
			)
			(38
				(localproc_0174 290 26)
				(= seconds 10)
			)
			(39
				(localproc_0174 290 27)
				(= seconds 10)
			)
			(40
				(localproc_0174 290 28)
				(= seconds 10)
			)
			(41
				(cls)
				(= local2 1)
				(localproc_01ab)
				(= cycles 10)
			)
			(42
				(localproc_0174 290 29)
				(= seconds 10)
			)
			(43
				(localproc_0174 290 30)
				(= seconds 10)
			)
			(44
				(myMusic fade:)
				(if local4
					(localproc_0174 290 31)
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
		(if (== (event type?) evSAID)
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
								(= gunIsLoaded 0)
							else
								(Print 290 6)
							)
						)
						((ego has: 15) (Print 290 7))
						(else (Print 290 8))
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
								(= gunIsLoaded 0)
								(= cycles 1)
							else
								(Print 290 6)
							)
						)
						((ego has: 15) (Print 290 7))
						(else (Print 290 8))
					)
				)
				((Said 'shoot/derringer') (Print 290 9))
			)
		)
	)
)

(instance cycleMouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 0 setCycle: Fwd show:)
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
	(properties)
	
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Colonel
					loop: (Random 2 5)
					cel: 0
					cycleSpeed: (Random 2 4)
					setCycle: Fwd
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
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(TalkingHead setCycle: Fwd)
				(RightArm setCycle: Fwd)
				(LeftArm setCycle: Fwd)
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

(instance Rudy of Act
	(properties
		y 113
		x 114
		view 383
		signal $4000
	)
)

(instance Colonel of Act
	(properties
		y 113
		x 114
		view 308
		signal $4000
		illegalBits $0000
	)
)

(instance Duo of Act
	(properties
		y 129
		x 114
		view 395
		signal $4000
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
