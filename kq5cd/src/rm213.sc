;;; Sierra Script 1.0 - (do not remove this comment)
(script# 213)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Follow)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm213 0
)

(local
	local0
	local1
	[local2 10] = [171 119 229 123 233 127 164 127 148 124]
	[local12 34] = [199 48 194 6 110 6 98 58 142 111 85 112 68 108 64 112 36 113 11 107 0 109 0 0 319 0 319 120 278 115 242 112 185 115]
)
(procedure (localproc_000e param1 param2 param3)
	(if (< param2 param1) (= param2 param1))
	(if (> param2 param3) (= param2 param3))
	(return param2)
)

(instance rm213 of KQ5Room
	(properties
		picture 213
		horizon 98
		north 214
		east 212
		south 15
		west 212
	)
	
	(method (init)
		(super init:)
		(ego view: 2 setPri: -1 setStep: 2 1 init:)
		(theMusic number: 2 loop: -1 vol: 127 play:)
		(theMusic2 number: 3 loop: -1 vol: 127 play:)
		(switch prevRoomNum
			(214
				(ego
					loop: 2
					posn: (localproc_000e 141 (ego x?) 181) 114
				)
				(ego setMotion: MoveTo (ego x?) (+ (ego y?) 1))
			)
			(15 (ego posn: 160 186))
			(212
				(ego posn: (ego x?) (localproc_000e 121 (ego y?) 200))
			)
			(else  (ego posn: 160 160))
		)
		(LoadMany 128 364 366 378 376)
		(cond 
			(
			(and (Btst 52) (not (Btst 32)) (== prevRoomNum 214))
				(ego posn: gGEgoX gGEgoY)
				(Bset 32)
				(HandsOff)
				(guard1 init: setScript: rideOut)
				(guard2 init: setScript: rideOut2)
			)
			((not (Btst 32)) (guard1 init: setScript: rideIn))
		)
		(self setFeatures: pond sands temple rock cliff)
		(poly1 points: @local2 size: 5)
		(poly2 points: @local12 size: 17)
		(self addObstacle: poly1 poly2)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((not (Btst 43)) (Bset 43) (SolvePuzzle 3))
			((= temp0 (self edgeToRoom: (ego edgeHit?)))
				(++ gPolyList15)
				(switch (ego edgeHit?)
					(3 (++ global315))
					(4 (++ global314))
					(2 (-- global314))
					(1 0)
				)
				(HandsOn)
				(curRoom newRoom: temp0)
			)
			((& (ego onControl: 0) $4000)
				(HandsOn)
				(theMusic fade:)
				(theMusic2 fade:)
				(curRoom newRoom: north)
			)
			((== local0 2)
				(theMusic fade:)
				(theMusic2 fade:)
				(= gGEgoX (ego x?))
				(= gGEgoY (ego y?))
				(Bset 52)
				(curRoom newRoom: north)
			)
		)
	)
	
	(method (dispose)
		(if theMusic (theMusic fade:))
		(if theMusic3 (theMusic3 fade:))
		(DisposeScript 971)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance getDrink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 188 130 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 45
					loop: 3
					cel: 0
					cycleSpeed: 3
					setCycle: End self
				)
			)
			(2
				(ego loop: (+ (ego loop?) 4) cel: 0 setCycle: End self)
			)
			(3 (= cycles 20))
			(4
				(ego cel: 0 setCycle: End self)
			)
			(5
				(ego loop: (- (ego loop?) 4) cel: 3 setCycle: Beg self)
			)
			(6
				(= gPolyList15 0)
				(proc0_29 311)
				(ego
					normal: 1
					view: 2
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(7
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance rideIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 20))
			(1
				(if (curRoom script?) (-- state))
				(= cycles 1)
			)
			(2
				(proc0_29 767)
				(theMusic2 stop:)
				(theMusic number: 47 loop: 1 vol: 127 playBed:)
				(= seconds 8)
			)
			(3
				(HandsOff)
				(if (not (& (ego onControl: 1) $0002))
					(LoadMany 128 380)
					(curRoom setScript: attack)
				else
					(= cycles 1)
				)
			)
			(4
				(SolvePuzzle 2)
				(guard2 init: setScript: rideIn2)
				(guard1
					setStep: 6 6
					setCycle: Walk
					setMotion: MoveTo 125 125 self
				)
			)
			(5
				(guard1
					posn: 135 119
					cel: 0
					setLoop: 1
					setMotion: MoveTo 142 115
					setCycle: End self
				)
			)
			(6
				(guard1
					view: 366
					setLoop: 0
					cel: 0
					setStep: 3 10
					setMotion: MoveTo (+ (guard1 x?) 10) 121
					setCycle: End self
				)
			)
			(7
				(++ local0)
				(client setScript: 0)
			)
		)
	)
)

(instance rideIn2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard2
					setStep: 6 6
					setCycle: Walk
					setMotion: MoveTo 125 135 self
				)
			)
			(1
				(guard2
					posn: 135 129
					cel: 0
					setLoop: 1
					setMotion: MoveTo 140 125
					setCycle: End self
				)
			)
			(2
				(guard2
					view: 378
					setLoop: 0
					cel: 0
					setStep: 3 10
					setMotion: MoveTo (+ (guard2 x?) 10) 130
					setCycle: End self
				)
			)
			(3
				(++ local0)
				(client setScript: 0)
			)
		)
	)
)

(instance rideOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic3 number: 47 loop: 1 vol: 127 playBed:)
				(guard1
					view: 366
					setLoop: 1
					cel: 0
					setStep: 1 1
					posn: 143 110
					setMotion: MoveTo 143 145
					setCycle: End self
				)
			)
			(1
				(guard1
					view: 364
					posn: (+ (guard1 x?) 6) (+ (guard1 y?) 8)
					setStep: 6 6
					cel: 0
					setLoop: 2
					setMotion: MoveTo 125 145
					setCycle: End self
				)
			)
			(2
				(guard1
					setLoop: 3
					posn: (- (guard1 x?) 7) (+ (guard1 y?) 9)
					setCycle: Walk
					setMotion: MoveTo -60 (guard1 y?) self
				)
			)
			(3 (theMusic3 fade: self))
			(4
				(User canInput: 1)
				(client setScript: 0)
			)
		)
	)
)

(instance rideOut2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(guard2
					view: 378
					setLoop: 6
					cel: 0
					setStep: 1 1
					posn: 141 115
					setMotion: MoveTo 141 155
					setCycle: End self
				)
			)
			(1
				(guard2
					view: 376
					posn: (+ (guard2 x?) 6) (+ (guard2 y?) 8)
					setStep: 6 6
					cel: 0
					setLoop: 2
					setMotion: MoveTo 123 155
					setCycle: End self
				)
			)
			(2
				(guard2
					setCycle: Walk
					posn: (- (guard2 x?) 7) (+ (guard2 y?) 9)
					setLoop: 3
					setMotion: MoveTo -60 160 self
				)
			)
			(3
				(theMusic3 fade:)
				(client setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance attack of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (<= (Abs (- (guard2 x?) (ego x?))) 3)
			((ego head?) hide:)
			(ego
				normal: 0
				view: 380
				loop: 3
				cel: 0
				cycleSpeed: 2
				setCycle: End
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc0_29 5901 0 1)
				(guard2 setStep: 6 6 setCycle: Walk init:)
				(if (< (ego y?) 132)
					(guard2
						setMotion: MoveTo (- (ego x?) 70) (+ (ego y?) 10) self
					)
				else
					(guard2
						setMotion: MoveTo (- (ego x?) 70) (- (ego y?) 5) self
					)
				)
				(guard1
					posn: -80 125
					setStep: 6 6
					setCycle: Walk
					ignoreActors: 1
					setMotion: Follow guard2 30
					init:
				)
			)
			(1
				(guard2
					view: 380
					setCycle: End self
					setMotion: MoveTo (+ (guard2 x?) 300) (guard2 y?) self
				)
			)
			(2
				(guard2 setCycle: Walk view: 376 setLoop: 0)
			)
			(3
				(cls)
				(= deathMessage 768)
				(EgoDead)
			)
		)
	)
)

(instance sands of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (& (OnControl 4 (event x?) (event y?)) $0040))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 764)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance temple of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (& (OnControl 4 (event x?) (event y?)) $0200))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 770)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance rock of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0002))
					(not (& (OnControl 4 (event x?) (event y?)) $0010))
				)
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 771)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 774)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cliff of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 772)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 775)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance pond of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (& (OnControl 4 (event x?) (event y?)) $0400))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 773)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cond 
						(
						(and (== (guard1 script?) rideIn) (rideIn state?)) (proc0_29 776))
						((not local1) (++ local1) (HandsOff) (curRoom setScript: getDrink))
						(else (proc0_29 777))
					)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance guard1 of Actor
	(properties
		x -60
		y 120
		view 364
	)
)

(instance guard2 of Actor
	(properties
		x -80
		y 130
		view 376
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
