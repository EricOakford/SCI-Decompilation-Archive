;;; Sierra Script 1.0 - (do not remove this comment)
(script# 110)
(include sci.sh)
(use Main)
(use Waters)
(use Door)
(use KQ5Room)
(use Sync)
(use RandCyc)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	hermitRoom 0
)

(local
	[theX 18] = [277 113 80 278 154 80 277 188 80 400 0 80 400 0 80 400 0 80]
	local18 =  1
)
(instance hermitRoom of KQ5Room
	(properties
		picture 46
		style $000a
	)
	
	(method (init)
		(Load rsSCRIPT 941)
		(LoadMany 128 624 626 628 623 625 26)
		(Load rsSCRIPT 941)
		(theMusic number: 7 loop: -1 play:)
		(ego init: hide:)
		((ego head?) hide:)
		(hermit init: hide:)
		(HandsOff)
		(super init:)
		(if (== gGNumber_2 46)
			(movingBoat init: ignoreActors:)
			(sail
				posn: (- (movingBoat x?) 10) (movingBoat y?)
				setCycle: Forward
				ignoreActors:
				cycleSpeed: 30
				init:
			)
		)
		(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
		(chimney setCycle: Forward cycleSpeed: 3 init:)
		(if (not (Btst 55))
			(= cedricX 100)
			(= cedricY 140)
			(curRoom setRegions: 202)
			(globalCedric setScript: myRotate)
		)
		(hermit
			view: 628
			setLoop: 1
			cel: 0
			cycleSpeed: 2
			posn: 148 146
			setPri: 10
			init:
		)
		(self setScript: cartoon2)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(DisposeScript 401)
		(super dispose:)
	)
)

(instance myRotate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(globalCedric view: 138 loop: 2 setCycle: EndLoop self)
			)
			(1
				(globalCedric
					view: 137
					loop: 8
					cycleSpeed: 50
					setCycle: RandCycle
				)
			)
			(2 (client setScript: 0))
		)
	)
)

(instance egoHeadMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) loop: (Random 4 6))
				(-- state)
				(= cycles 5)
			)
		)
	)
)

(instance cartoon2 of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(sail posn: (- (movingBoat x?) 10) (movingBoat y?))
		(if
		(and (== state 4) (== (theMusic3 prevSignal?) -1))
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 624
					posn: 120 148
					normal: 1
					ignoreActors: 1
					illegalBits: 0
					init:
					setPri: -1
				)
				(hermit
					view: 626
					setCycle: Walk
					cycleSpeed: 0
					setLoop: -1
					ignoreActors: 1
					posn: 90 148
					illegalBits: 0
					show:
					init:
				)
				(= cycles 1)
			)
			(1
				(hermit setMotion: MoveTo 175 148)
				(= cycles 30)
			)
			(2
				(ego
					show:
					setCycle: EndLoop self
					setLoop: 13
					setMotion: MoveTo 130 148 self
				)
			)
			(3
				((ego head?) show:)
				(ego
					show:
					view: 0
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 130 148 self
				)
			)
			(4
				(ego setLoop: 0 setMotion: MoveTo 160 160)
				(hermit view: 628 setLoop: 2)
				(arm
					posn: (- (hermit x?) 5) (- (hermit y?) 32)
					setPri: (+ (hermit priority?) 1)
					loop: 1
					setCycle: CycleTo 8 1
					init:
				)
				(theMusic3 number: 821 loop: 1 play:)
			)
			(5
				(arm setCycle: EndLoop)
				(hermit setLoop: 2)
				(= seconds 5)
			)
			(6
				(mermaid
					init:
					setLoop: 8
					cycleSpeed: 2
					posn: 275 155
					setCycle: EndLoop self
				)
			)
			(7
				(mermaid setLoop: 7 cycleSpeed: 3 setCycle: Forward)
				(theAudio stop:)
				(= cycles 1)
			)
			(8
				(hermit_mouth
					init:
					setPri: (+ (hermit priority?) 1)
					cycleSpeed: 2
					posn: (+ (hermit x?) 3) (- (hermit y?) 37)
				)
				(hermit_mouth setCycle: MouthSync 1147)
				(SpeakAudio 1147 self)
			)
			(9
				(hermit_mouth setCycle: 0)
				(cls)
				(= seconds 3)
			)
			(10
				(hermit_mouth setCycle: MouthSync 1148)
				(SpeakAudio 1148 self)
			)
			(11
				(hermit_mouth setCycle: 0)
				(cls)
				((ego head?) setCel: 1 setScript: egoHeadMove)
				(if (not (Btst 55))
					(SpeakAudio 1149 self)
				else
					(SpeakAudio 9101 self)
				)
			)
			(12
				((ego head?) setCel: 1 loop: 4 setScript: 0)
				(cls)
				(hermit_mouth setCycle: MouthSync 1151)
				(SpeakAudio 1151 self)
			)
			(13
				(hermit_mouth setCycle: 0)
				(cls)
				((ego head?) setCel: 1 setScript: egoHeadMove)
				(if (not (Btst 55))
					(SpeakAudio 1152 self)
				else
					(SpeakAudio 9102 self)
				)
			)
			(14
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				((ego head?) setCel: -1 setScript: 0)
				(cls)
				(mermaid setLoop: 10 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(15
				(ego
					view: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
					setPri: 14
					illegalBits: 0
					ignoreActors:
					normal: 0
					setMotion: MoveTo 180 164 self
				)
			)
			(16
				(ego setMotion: MoveTo 215 175 self)
				(sail setPri: 15)
			)
			(17
				((ego head?) hide:)
				(ego
					view: 615
					loop: 2
					cycleSpeed: 2
					setPri: 14
					cel: 0
					setCycle: EndLoop self
				)
			)
			(18
				(ego
					setLoop: 3
					posn: (+ (ego x?) 31) (ego y?)
					setCycle: Forward
				)
				(= seconds 2)
			)
			(19
				(ego
					posn: (ego x?) (- (ego y?) 18)
					setLoop: 0
					setCycle: EndLoop self
				)
				(hermit_mouth dispose:)
				(hermit
					view: 626
					setCycle: Walk
					setLoop: 1
					cycleSpeed: 0
					ignoreActors: 1
					setMotion: MoveTo 90 144
				)
				(arm hide:)
			)
			(20
				(ego setLoop: 1 cycleSpeed: 2 setCycle: EndLoop self)
				(movingBoat
					setStep: 1 1
					setMotion: MoveTo 400 (movingBoat y?)
				)
				(ego
					moveSpeed: (movingBoat moveSpeed?)
					setStep: 1 1
					setMotion: MoveTo 400 (ego y?)
				)
			)
			(21
				(if (not (hermit mover?))
					(door setCycle: BegLoop init:)
					(theAudio number: 8124 loop: 1 play: self)
				else
					(= cycles 1)
				)
			)
			(22
				(ego
					view: 618
					setPri: 14
					loop: 4
					posn: (+ (ego x?) 20) (+ (ego y?) 2)
				)
				(theAudio number: 7055 loop: -1 doNotStop: 1 play:)
				(= seconds 5)
			)
			(23
				(ego setMotion: 0)
				(movingBoat setMotion: 0)
				(sail setMotion: 0)
				(if (not (Btst 55))
					(globalCedric
						view: 138
						setLoop: 6
						cycleSpeed: 0
						setCycle: EndLoop self
					)
				else
					(= cycles 1)
				)
			)
			(24 (curRoom newRoom: 113))
		)
	)
)

(instance hermit of Actor
	(properties
		signal $0001
	)
)

(instance mermaid of Actor
	(properties
		view 624
		signal $0001
	)
)

(instance surf of Waters
	(properties
		view 623
		priority 1
		signal $6030
	)
	
	(method (doit)
		(super doit:)
		(if (or loop cel)
			(= local18 1)
		else
			(if local18 (= cycCnt 50))
			(= local18 0)
		)
	)
	
	(method (getLoop)
		(= x [theX pos])
		(= y [theX (++ pos)])
		(= cel [theX (++ pos)])
	)
	
	(method (saveLoop)
		(= [theX pos] cel)
		(++ pos)
	)
)

(instance movingBoat of Actor
	(properties
		x 300
		y 173
		view 618
		cel 1
		priority -1
		signal $0001
	)
)

(instance chimney of Prop
	(properties
		x 18
		y 70
		z 20
		view 625
		loop 2
		detailLevel 3
	)
)

(instance sail of Actor
	(properties
		z 15
		view 618
		loop 2
		detailLevel 3
	)
)

(instance door of Door
	(properties
		x 113
		y 147
		view 625
	)
)

(instance arm of Prop
	(properties
		view 628
	)
)

(instance hermit_mouth of Prop
	(properties
		view 624
		loop 15
	)
)
