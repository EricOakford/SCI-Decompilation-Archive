;;; Sierra Script 1.0 - (do not remove this comment)
(script# 72)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Feature)
(use RandCyc)
(use LoadMany)
(use Jump)
(use Motion)
(use System)

(public
	rm072 0
)

(local
	sp1X =  282
	sp1Y =  56
	moveToX =  291
	moveToY =  62
	[local4 2] = [277 62]
	newX =  195
	newY =  54
	local8 =  325
	local9 =  72
)
(instance rm072 of SQRoom
	(properties
		picture 72
	)
	
	(method (init &tmp theJump)
		(HandsOff)
		(LoadMany VIEW 73 72)
		(Load SOUND 109)
		(switch prevRoomNum
			(45
				(if (Btst fPoliceLanded) (Load SOUND 73) else (Load SOUND 77))
				(HandsOff)
				(self setScript: shipLeaves)
			)
			(90
				(LoadMany SOUND 123 811)
				(if (Btst fPoliceArrive)
					(ship x: 267 y: 45 init:)
					(gear x: 267 y: 45 cel: 5 init:)
					(manhole setMotion: MoveTo 117 144 manhole)
					(globalSound number: 123 loop: 1 vol: 127 flags: 0 play:)
					(ego z: 1000 init:)
					(HandsOff)
					(manholeScript start: 19)
					(self setScript: manholeScript)
				else
					(= theJump Jump)
					(Load SOUND 72)
					(HandsOff)
					(self setScript: manholeScript)
				)
			)
			(60
				(LoadMany SOUND 123 811)
				(if (Btst fPoliceArrive)
					(ship x: 267 y: 45 init:)
					(gear x: 267 y: 45 cel: 5 init:)
					(manhole setMotion: MoveTo 117 144 manhole)
					(globalSound number: 123 loop: 1 vol: 127 flags: 0 play:)
					(ego z: 1000 init:)
					(HandsOff)
					(manholeScript start: 19)
					(self setScript: manholeScript)
				else
					(= theJump Jump)
					(Load SOUND 72)
					(HandsOff)
					(self setScript: manholeScript)
				)
			)
			(else 
				(LoadMany SOUND 75 76)
				(Bset fExitedSewers)
				(= theJump Jump)
				(HandsOff)
				(Bclr fPoliceLanded)
				(self setScript: xenonLanding)
			)
		)
		(theGreenBldg init:)
		(theWhiteBldg init:)
		(theManhole init:)
		(manhole init:)
		(manholeFeature init:)
		(theRoom init:)
		(rogersHead init:)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(if
		(and (== (curRoom script?) manholeScript) (ego mover?))
			(HandsOff)
			(curRoom setScript: goto40Script)
		)
	)
	
	(method (dispose)
		(DisposeScript JUMP)
		(super dispose:)
	)
	
	(method (newRoom newRoomNumber)
		(switch newRoomNumber
			(40 (globalSound fade:))
			(90 (music fade:))
		)
		(if (== newRoomNumber 90) (Bset fPoliceArrive) else (Bclr fPoliceArrive))
		(super newRoom: newRoomNumber)
		(ego z: 0)
	)
)

(instance manholeScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (== state 2) (>= (music prevSignal?) 128))
			(self cue:)
		)
		(if
		(and (== state 5) (>= (music prevSignal?) 129))
			(self cue:)
		)
		(if
		(and (== state 10) (>= (music prevSignal?) 130))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 3))
			(1
				(HandsOff)
				(music number: 72 loop: 1 vol: 127 flags: 0 playBed:)
				(manhole setMotion: MoveTo 117 144 self)
				(globalSound number: 123 loop: 1 vol: 127 flags: 0 play:)
			)
			(2
				(HandsOff)
				(if
					(or
						(< (music prevSignal?) 1)
						(> (music prevSignal?) 128)
					)
					(self cue:)
				)
			)
			(3
				(manhole stopUpd:)
				(ego z: 1000 init:)
				(globalSound number: 109 loop: 1 flags: mNOPAUSE play:)
				(ship init: setMotion: MoveTo 267 24)
				(dust init: cycleSpeed: 18 setCycle: EndLoop self)
			)
			(4
				(dust loop: 1 cycleSpeed: 12 setCycle: RandCycle)
				(ship setMotion: MoveTo 267 24 self)
			)
			(5
				(if
					(or
						(< (music prevSignal?) 1)
						(> (music prevSignal?) 129)
					)
					(self cue:)
				)
			)
			(6
				(ship setMotion: MoveTo (ship x?) 45)
				(gear
					posn: (ship x?) (ship y?)
					init:
					setCycle: EndLoop
					setMotion: MoveTo (ship x?) 45
				)
				(= cycles 1)
			)
			(7
				(dust cycleSpeed: 6 setCycle: RandCycle)
				(= cycles 4)
			)
			(8
				(ship setCel: 0)
				(= cycles 25)
			)
			(9 (= cycles 30))
			(10
				(dust dispose:)
				(globalSound fade:)
				(if
					(or
						(< (music prevSignal?) 1)
						(> (music prevSignal?) 130)
					)
					(self cue:)
				)
			)
			(11
				(HandsOn)
				(sp2 init:)
				(sp3 init:)
				(sp4 init:)
				(sp1
					posn: sp1X sp1Y
					setLoop: 3
					init:
					setMotion: JumpTo moveToX moveToY self
				)
			)
			(12
				(sp1
					setStep: 2 2
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo newX newY
				)
				(= cycles 6)
			)
			(13
				(sp2
					posn: sp1X sp1Y
					setLoop: 3
					setMotion: JumpTo moveToX moveToY self
				)
			)
			(14
				(sp2
					setStep: 2 2
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo newX newY
				)
				(= cycles 10)
			)
			(15
				(sp3
					posn: sp1X sp1Y
					setMotion: JumpTo moveToX moveToY self
				)
			)
			(16
				(sp4
					posn: sp1X sp1Y
					setMotion: JumpTo moveToX moveToY self
				)
				(sp3
					setStep: 2 2
					setCycle: Walk
					setMotion: MoveTo local8 local9
				)
			)
			(17
				(sp4
					setStep: 2 2
					setCycle: Walk
					setMotion: MoveTo local8 local9 self
				)
			)
			(18 (= seconds 15))
			(19 (= seconds 10))
			(20
				(narrator say: 1)
				(manhole
					yStep: 2
					setMotion: MoveTo (manhole x?) (+ (manhole y?) 8)
				)
				(= seconds 3)
			)
			(21
				(manhole
					setMotion: MoveTo (manhole x?) (+ (manhole y?) 12) self
				)
				(= seconds 3)
			)
			(22
				(HandsOff)
				(narrator say: 2)
				(manhole
					yStep: 12
					setMotion: MoveTo (manhole x?) 188 self
				)
			)
			(23
				(globalSound number: 811 loop: 1 vol: 127 flags: 0 play:)
				(= cycles 1)
			)
			(24 (curRoom newRoom: 90))
		)
	)
)

(instance xenonLanding of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(manhole stopUpd:)
				(= seconds 2)
			)
			(1
				(globalSound number: 109 loop: 1 vol: 100 flags: 0 play:)
				(ship init: setMotion: MoveTo 267 24)
				(dust init: cycleSpeed: 18 setCycle: EndLoop self)
			)
			(2
				(music number: 75 loop: 1 vol: 127 flags: 0 play:)
				(dust loop: 1 cycleSpeed: 12 setCycle: Forward)
				(ship setMotion: MoveTo 267 24 self)
			)
			(3 (= cycles 20))
			(4
				(ship setMotion: MoveTo (ship x?) 45)
				(gear
					posn: (ship x?) (ship y?)
					init:
					setCycle: EndLoop
					setMotion: MoveTo (ship x?) 45
				)
				(= cycles 1)
			)
			(5 (= cycles 4))
			(6
				(ship setCel: 0)
				(= cycles 25)
			)
			(7
				(globalSound fade:)
				(= cycles 30)
				(music number: 76 loop: 1 vol: 127 flags: 1 playBed:)
			)
			(8
				(dust dispose:)
				(= cycles 8)
			)
			(9
				(sp1
					init:
					setLoop: 3
					setStep: 2 2
					moveSpeed: speed
					cycleSpeed: (* speed 2)
					setCycle: Walk
					x: newX
					y: newY
					setMotion: MoveTo moveToX moveToY
				)
				(= cycles 12)
			)
			(10
				(sp2
					init:
					setLoop: 3
					setStep: 2 2
					moveSpeed: speed
					cycleSpeed: (* speed 2)
					setCycle: Walk
					x: newX
					y: newY
					setMotion: MoveTo moveToX moveToY self
				)
			)
			(11
				(sp1
					moveSpeed: 1
					setLoop: 2
					setMotion: JumpTo sp1X (- sp1Y 4) sp1
				)
				(= cycles 12)
			)
			(12
				(sp2
					moveSpeed: 1
					setLoop: 2
					setMotion: JumpTo sp1X (- sp1Y 4) sp2
				)
				(= cycles 1)
			)
			(13
				(sp3
					init:
					x: local8
					y: local9
					setLoop: 2
					setStep: 2 2
					moveSpeed: speed
					cycleSpeed: (* speed 2)
					setCycle: Walk
					setMotion: MoveTo moveToX moveToY
				)
				(= cycles 12)
			)
			(14
				(sp4
					init:
					x: local8
					y: local9
					setLoop: 2
					setStep: 2 2
					setCycle: Walk
					moveSpeed: speed
					cycleSpeed: (* speed 2)
					setMotion: MoveTo moveToX moveToY self
				)
			)
			(15
				(sp3
					moveSpeed: 1
					setMotion: JumpTo sp1X (- sp1Y 4) sp3
				)
				(= cycles 12)
			)
			(16
				(sp4
					moveSpeed: 1
					setMotion: JumpTo sp1X (- sp1Y 4) sp4
				)
				(= cycles 12)
			)
			(17 (music fade: self) 0)
			(18
				(globalSound
					number: 802
					loop: -1
					vol: 127
					flags: 1
					playBed:
				)
				(curRoom newRoom: 45)
			)
		)
	)
)

(instance shipLeaves of Script
	(properties)
	
	(method (doit &tmp [temp0 5])
		(gear x: (ship x?) y: (- (ship y?) 1))
		(if
		(and (== state 0) (== (globalSound prevSignal?) 128))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(manhole stopUpd:)
				(music number: 109 loop: 1 vol: 100 flags: 0 play:)
				(if (Btst fPoliceLanded)
					(globalSound
						number: 73
						loop: 1
						vol: 127
						flags: 0
						playBed:
					)
				else
					(globalSound
						number: 77
						loop: 1
						vol: 127
						flags: 1
						playBed:
					)
					(= cycles 30)
				)
				(ship x: 267 y: 45 moveSpeed: 1 init:)
				(dust loop: 1 init: cycleSpeed: 12 setCycle: Forward)
			)
			(1
				(if (not (Btst fExitedSewers)) (music fade:))
				(if (Btst fPoliceLanded) (globalSound hold: 1))
				(ship setMotion: MoveTo (ship x?) -10 self)
			)
			(2
				(dust loop: 0 cel: 9 cycleSpeed: 12 setCycle: BegLoop self)
				(if (Btst fExitedSewers) (globalSound number: 0 stop:))
			)
			(3
				(if (Btst fExitedSewers)
					(Bclr fExitedSewers)
					(music fade:)
					(curRoom newRoom: 60)
					(self dispose:)
				else
					(curRoom newRoom: 59)
					(self dispose:)
				)
			)
		)
	)
)

(instance sewerScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(manhole setMotion: MoveTo (manhole x?) 188 self)
			)
			(1
				(globalSound number: 811 loop: 1 vol: 127 flags: 0 play:)
				(= cycles 1)
			)
			(2 (curRoom newRoom: 90))
		)
	)
)

(instance manholeFeature of Sq4Feature
	(properties
		x 160
		y 186
		nsBottom 200
		nsRight 320
		onMeCheck $0002
		lookStr 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if (!= (curRoom script?) xenonLanding)
					(HandsOff)
					(curRoom setScript: sewerScript)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance manhole of Sq4Actor
	(properties
		x 117
		y 186
		yStep 4
		view 72
		loop 4
		signal fixedLoop
	)
	
	(method (cue)
		(HandsOn)
		(manhole stopUpd:)
	)
)

(instance ship of Sq4Actor
	(properties
		x 210
		y -6
		yStep 1
		view 72
		priority 4
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		xStep 1
		lookStr 4
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (== (curRoom script?) xenonLanding)
					(curRoom newRoom: 45)
				else
					(narrator say: 5)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance gear of Sq4Actor
	(properties
		yStep 1
		view 72
		loop 1
		priority 5
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		xStep 1
	)
)

(instance dust of Sq4Prop
	(properties
		x 213
		y 52
		view 73
		priority 3
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance sp1 of Sq4Actor
	(properties
		yStep 1
		view 72
		loop 2
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 1
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance sp2 of Sq4Actor
	(properties
		yStep 1
		view 72
		loop 2
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 1
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance sp3 of Sq4Actor
	(properties
		yStep 1
		view 72
		loop 3
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 1
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance sp4 of Sq4Actor
	(properties
		yStep 1
		view 72
		loop 3
		priority 6
		signal (| ignrAct fixedLoop fixPriOn)
		xStep 1
	)
	
	(method (cue)
		(super cue:)
		(self dispose:)
	)
)

(instance theGreenBldg of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $0010
		lookStr 6
	)
)

(instance theWhiteBldg of Sq4Feature
	(properties
		nsBottom 200
		nsRight 320
		onMeCheck $0020
		lookStr 7
	)
)

(instance theManhole of Sq4Feature
	(properties
		x 120
		y 187
		nsBottom 200
		nsRight 320
		onMeCheck $0040
		lookStr 8
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(manholeFeature doVerb: theVerb)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance rogersHead of Sq4Feature
	(properties
		x 120
		y 188
		nsBottom 189
		nsRight 319
		sightAngle 45
		onMeCheck $0004
		lookStr 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 10))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance theRoom of Sq4Feature
	(properties
		x 152
		nsBottom 189
		nsRight 319
		sightAngle 180
		lookStr 11
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(HandsOff)
				(curRoom setScript: goto40Script)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance goto40Script of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if (and (not (Btst fClimbOutManhole)) (== state 0)) (self cue:))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SolvePuzzle fClimbOutManhole 3)
				(globalSound fade:)
				(= seconds 4)
			)
			(1 (curRoom newRoom: 40))
		)
	)
)
