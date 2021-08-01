;;; Sierra Script 1.0 - (do not remove this comment)
(script# 83)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Reverse)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm083 0
)

(local
	[local0 38] = [299 112 255 123 274 46 249 123 240 123 229 129 123 132 181 5 319 5 319 153 271 153 271 144 312 144 313 125 267 136 268 147 255 147 255 131 306 118]
	[local38 16] = [319 189 0 189 0 0 39 0 84 126 18 128 62 168 319 168]
)
(instance rm083 of KQ5Room
	(properties
		picture 83
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 796)
		(HandsOff)
		(self
			setFeatures: ladder hole shoeWorkArea chasm otherWorkArea room
			addObstacle: poly1 poly2
		)
		(if
			(or
				(== ((inventory at: 33) owner?) 83)
				(== prevRoomNum 84)
			)
			(if (!= (theMusic number?) 90)
				(theMusic number: 90 loop: -1 vol: 127 playBed:)
			)
			(workers setLoop: 2 init:)
			(worker2 setLoop: 3 cycleSpeed: 2 setCycle: Fwd init:)
			(elf setLoop: 1 posn: 48 87 init:)
		else
			(theMusic number: 149 loop: -1 vol: 127 play:)
		)
		(switch prevRoomNum
			(82
				(ego
					init:
					view: 796
					setLoop: 8
					cel: 2
					ignoreActors: 1
					illegalBits: 0
					setStep: 2 1
					posn: 280 24
					normal: 0
				)
				((ego head?) hide:)
				(self setScript: enterRoom)
			)
			(84
				(ego
					init:
					view: 2
					setLoop: -1
					posn: 65 128
					setStep: 2 1
					normal: 1
				)
				((ego head?) show:)
				(elf posn: 44 130 setLoop: 7 cel: 0)
				(HandsOff)
				(self setScript: exitRoom)
			)
			(else 
				(ego
					init:
					view: 796
					setLoop: 15
					setStep: 2 1
					setCel: 5
					normal: 0
					x: 278
					y: 147
				)
				((ego head?) hide:)
				(HandsOff)
				(self setScript: enterFromHole)
			)
		)
		(poly1 points: @local0 size: 19)
		(poly2 points: @local38 size: 8)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
			((& (= temp1 (ego onControl: 1)) $2000) (HandsOff) (self setScript: dying))
			((& temp1 $4000) (HandsOff) (self setScript: crawlThroughHole))
			((& temp1 $1000) (HandsOff) (self setScript: climbUpLadder))
		)
	)
	
	(method (dispose)
		(DisposeScript 941)
		(DisposeScript 969)
		(super dispose:)
	)
)

(instance elfComesDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(elf cel: 0 cycleSpeed: 2 setCycle: Fwd)
				(= cycles 30)
			)
			(1
				(elf
					setLoop: 4
					cel: 0
					setCycle: Fwd
					setMotion: MoveTo 40 93 self
				)
			)
			(2
				(elf cel: 0 posn: 40 100 setLoop: 5 setCycle: End self)
			)
			(3
				(elf posn: 49 127 setLoop: 6 cel: 0 setCycle: End self)
			)
			(4 (elf cel: 0 setLoop: 7))
			(5
				(ego get: 33)
				(SolvePuzzle 2)
				(elf setCycle: End self)
			)
			(6 (= cycles 10))
			(7
				((ego head?) hide:)
				(ego
					normal: 0
					view: 796
					setLoop: 0
					setCycle: End self
					cycleSpeed: 3
				)
			)
			(8 (curRoom newRoom: 84))
		)
	)
)

(instance exitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego view: 796 normal: 0 setLoop: 0)
				(ego cel: (- (NumCels ego) 1))
				(= cycles 15)
			)
			(1 (ego setCycle: Beg self))
			(2
				(ego view: 2 normal: 1 setLoop: 1)
				((ego head?) show:)
				(elf loop: 1 cel: 0 setCycle: CT 6 1 self)
			)
			(3
				(elf
					view: 797
					loop: 3
					cel: 0
					cycleSpeed: 8
					setCycle: RandCycle
				)
				(proc0_29 5097 self)
			)
			(4
				(cls)
				(elf loop: 5 cel: 0 setCycle: Beg)
				(ego
					normal: 0
					view: 797
					setLoop: 1
					cel: 0
					cycleSpeed: 8
					setCycle: RandCycle
				)
				((ego head?) hide:)
				(talkingHead
					init:
					x: (ego x?)
					y: (- (ego y?) 26)
					cycleSpeed: 8
					setCycle: RandCycle
				)
				(proc0_29 5098 self)
			)
			(5
				(cls)
				(ego
					normal: 1
					view: 2
					setLoop: -1
					cycleSpeed: (theGame egoMoveSpeed?)
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 259 147 self
				)
				(talkingHead dispose:)
				((ego head?) show:)
			)
			(6
				((ego head?) hide:)
				(ego
					normal: 0
					view: 796
					loop: 11
					cel: 1
					setCycle: CT 4 1 self
				)
			)
			(7
				(ego loop: 12 cel: 0 setCycle: End self)
			)
			(8
				(ego loop: 13 setCycle: End self)
			)
			(9
				(elf cycleSpeed: 5 setCycle: End self)
				(ego cycleSpeed: 3 setCycle: Fwd)
			)
			(10
				(ego
					loop: 12
					cel: 2
					cycleSpeed: (theGame egoMoveSpeed?)
					setCycle: Beg self
				)
			)
			(11
				(elf setCycle: Beg self)
				(ego loop: 11 cel: 4 setCycle: CT 1 -1 self)
			)
			(12)
			(13
				(crawlThroughHole start: 1)
				(self setScript: crawlThroughHole)
			)
		)
	)
)

(instance enterRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(ego
					setCycle: Rev
					cycleSpeed: 3
					setMotion: MoveTo 271 51 self
				)
			)
			(2
				(ego cycleSpeed: 3 setMotion: MoveTo 253 107 self)
			)
			(3
				(ego setLoop: 9 posn: 251 124 cel: 0 setCycle: End self)
			)
			(4
				(ego
					view: 2
					setStep: 2 1
					setCycle: KQ5SyncWalk
					normal: 1
					setLoop: -1
					setMotion: MoveTo (- (ego x?) 4) (- (ego y?) 0) self
				)
				((ego head?) show:)
			)
			(5
				(if (!= ((inventory at: 33) owner?) 83)
					(HandsOn)
					(ego
						illegalBits: -32768
						setMotion: MoveTo (- (ego x?) 5) (ego y?) self
					)
				else
					(proc0_29 5096 self)
					(elf setScript: elfComesDown)
				)
			)
			(6
				(if (!= ((inventory at: 33) owner?) 83)
					(self dispose:)
				else
					(ego setMotion: MoveTo 232 133 self)
				)
			)
			(7
				(cls)
				(ego setMotion: MoveTo 65 128 self)
			)
			(8 (elfComesDown cue:))
		)
	)
)

(instance dying of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 797
					normal: 0
					cel: 0
					setLoop: 0
					setMotion: 0
					setCycle: End
				)
				((ego head?) hide:)
				(theAudio number: 7053 play: self)
				(theMusic3 number: 83 loop: 1 priority: 15 vol: 127 play:)
			)
			(1 (ego dispose:) (= cycles 1))
			(2
				(if (< (Random 1 100) 50)
					(theAudio number: 8892 play: self)
				else
					(theAudio number: 8068 play: self)
				)
			)
			(3
				(= deathMessage 719)
				(EgoDead 248)
			)
		)
	)
)

(instance crawlThroughHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCel: -1 setMotion: PolyPath 259 147 self)
			)
			(1
				(cls)
				(ego
					normal: 0
					view: 796
					setLoop: 14
					cel: 0
					setCycle: CT 3 1 self
				)
				((ego head?) hide:)
			)
			(2
				(ego x: (+ (ego x?) 3) setCel: 5)
				(= cycles 4)
			)
			(3
				(ego x: (+ (ego x?) 3) setCel: 4)
				(= cycles 3)
			)
			(4
				(ego x: (+ (ego x?) 3) setCel: 5)
				(= cycles 4)
			)
			(5
				(theMusic fade:)
				(curRoom newRoom: 215)
			)
		)
	)
)

(instance climbUpLadder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 251 124 self)
			)
			(1
				((ego head?) hide:)
				(ego
					view: 796
					posn: 253 107
					setLoop: 8
					cel: 1
					setCycle: Fwd
					moveSpeed: 2
					setStep: 1 2
					setMotion: MoveTo 271 51 self
				)
			)
			(2
				(ego setMotion: MoveTo 278 28 self)
			)
			(3
				(ego setMotion: MoveTo 288 25 self)
			)
			(4
				(ego moveSpeed: (theGame egoMoveSpeed?))
				(theMusic fade:)
				(curRoom newRoom: 82)
			)
		)
	)
)

(instance enterFromHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setCel: 4 x: (- (ego x?) 3))
				(= cycles 2)
			)
			(1
				(ego setCel: 5 x: (- (ego x?) 3))
				(= cycles 2)
			)
			(2
				(ego setCel: 4 x: (- (ego x?) 3))
				(= cycles 2)
			)
			(3 (ego setCycle: Beg self))
			(4
				(ego view: 2 x: (- (ego x?) 8))
				(NormalEgo 1 2)
				((ego head?) show:)
				(= cycles 1)
			)
			(5
				(ego setStep: 2 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance workers of Prop
	(properties
		x 97
		y 82
		view 796
		cycleSpeed 1
	)
	
	(method (doit)
		(super doit:)
		(if (== (theMusic prevSignal?) 10)
			(theMusic prevSignal: 0)
			(self cel: 0 setCycle: End)
		)
	)
)

(instance worker2 of Prop
	(properties
		x 125
		y 81
		view 796
	)
)

(instance elf of Actor
	(properties
		x 40
		y 93
		view 796
		priority 9
		signal $4010
	)
)

(instance ladder of RFeature
	(properties
		nsTop 40
		nsLeft 244
		nsBottom 118
		nsRight 279
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 711)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: climbUpLadder)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hole of RFeature
	(properties
		nsTop 123
		nsLeft 267
		nsBottom 149
		nsRight 276
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 712)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: crawlThroughHole)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance shoeWorkArea of RFeature
	(properties
		nsTop 55
		nsLeft 14
		nsBottom 80
		nsRight 72
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 713)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 717)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance chasm of RFeature
	(properties
		nsTop 117
		nsLeft 69
		nsBottom 122
		nsRight 155
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 714)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance otherWorkArea of RFeature
	(properties
		nsTop 59
		nsLeft 91
		nsBottom 81
		nsRight 156
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 715)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 718)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance room of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 716)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance talkingHead of Prop
	(properties
		view 797
		loop 2
		priority 15
		signal $0010
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)
