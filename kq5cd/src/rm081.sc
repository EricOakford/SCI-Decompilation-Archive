;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use Polygon)
(use LoadMany)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm081 0
)

(local
	[local0 14] = [319 189 0 189 127 155 203 174 273 161 275 122 319 120]
	[local14 14] = [319 81 276 97 236 88 145 111 97 124 0 0 319]
)
(instance rm081 of KQ5Room
	(properties
		picture 81
		east 24
		west 82
	)
	
	(method (init)
		(super init:)
		(cond 
			((not (Btst 45)) (theMusic number: 87 loop: -1 playBed:))
			((!= (theMusic number?) 4) (theMusic number: 4 loop: -1 playBed:))
		)
		(LoadMany 128 792 0 468)
		(self
			setFeatures: hole_tree theRoom
			addObstacle: poly1 poly2
		)
		(if (== prevRoomNum 82)
			(HandsOff)
			(self setScript: enterFromHole)
		else
			(ego posn: 310 100 init: loop: 1 setStep: 3 2)
		)
		(if (not (Btst 45))
			(Bset 45)
			(HandsOff)
			(self setScript: wavingElf)
		)
		(poly1 points: @local0 size: 7)
		(poly2 points: @local14 size: 7)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((= temp0 (self edgeToRoom: (ego edgeHit?))) (curRoom newRoom: temp0))
			((& (ego onControl: 1) $1000)
				(HandsOff)
				(if (== ((inventory at: 33) owner?) 83)
					((ScriptID 763) doit:)
				)
				(self setScript: enterHole)
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
		(ego view: 0 illegalBits: -32768 setLoop: -1 setStep: 3 2)
	)
)

(instance enterHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) hide:)
				(ego
					view: 792
					normal: 0
					illegalBits: 0
					setLoop: 4
					cel: 0
					cycleSpeed: 1
					setCycle: End self
				)
			)
			(1
				(ego
					posn: (- (ego x?) 3) (- (ego y?) 17)
					setLoop: 1
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					moveSpeed: 1
					setMotion: MoveTo 20 129 self
				)
			)
			(2
				(ego moveSpeed: (theGame egoMoveSpeed?))
				(curRoom newRoom: (curRoom west?))
				(self dispose:)
			)
		)
	)
)

(instance enterFromHole of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					init:
					view: 792
					normal: 0
					illegalBits: 0
					setLoop: 2
					posn: 20 129
					setCycle: KQ5SyncWalk
					moveSpeed: 2
					setMotion: MoveTo 115 129 self
				)
				((ego head?) hide:)
			)
			(1
				(ego
					loop: 5
					cel: 3
					posn: (+ (ego x?) 3) (+ (ego y?) 17)
					cycleSpeed: 1
					setCycle: Beg self
				)
			)
			(2
				((ego head?) show:)
				(ego
					view: 0
					setLoop: 0
					setMotion: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					moveSpeed: (theGame egoMoveSpeed?)
					setStep: 3 2
					illegalBits: -32768
					normal: 1
					ignoreActors: 0
				)
				(= cycles 1)
			)
			(3
				(ego setLoop: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance wavingElf of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(elf init: cycleSpeed: 1 setCycle: Fwd)
				(= cycles 15)
			)
			(1 (SpeakAudio 5095 self))
			(2
				(cls)
				(elf
					view: 468
					setLoop: 10
					cycleSpeed: 0
					setCycle: Walk
					setMotion: MoveTo 30 139 self
				)
			)
			(3
				(HandsOn)
				(elf dispose:)
				(self dispose:)
			)
		)
	)
)

(instance elf of Actor
	(properties
		x 117
		y 135
		view 796
		loop 1
		signal $0800
	)
)

(instance hole_tree of RFeature
	(properties
		name "hole&tree"
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 708)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance theRoom of RFeature
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
					(SpeakAudio 709)
					(event claimed: 1)
				)
			)
		)
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
