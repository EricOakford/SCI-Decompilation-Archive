;;; Sierra Script 1.0 - (do not remove this comment)
(script# 34)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use CDActor)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm034 0
)

(local
	local0
	local1
	local2
	[local3 5] = [2 0 3 1 2]
	[local8 8] = [175 173 175 189 31 189 31 187]
	[local16 8] = [159 151 210 151 209 156 158 156]
	[local24 12] = [0 0 119 145 74 159 36 159 29 166 0 171]
	local36
	[local37 9] = [1024 163 65 4 10 28 20 33 27]
	[local46 18] = [1028 150 77 3 9 26 24 32 32 1028 116 62 3 9 26 24 32 32]
	[local64 10] = [0 950 1 951 0 952 1 953]
	[local74 8] = [1 955 0 956 1 957]
)
(instance rm034 of KQ5Room
	(properties
		picture 34
		horizon 125
		north 92
		south 33
		west 33
	)
	
	(method (init)
		(super init:)
		(ego view: 12 posn: 7 173)
		(= local2 1)
		(self setFeatures: path34 aCastle)
		(if
			(and
				(!= ((inventory at: 19) owner?) 34)
				(!= ((inventory at: 2) owner?) 34)
			)
			(eagleHead setPri: 13 init: hide:)
			(eagle init: stopUpd: setScript: eagleScript)
			(theMusic2 number: 12 loop: 1 play:)
		)
		(ego init:)
		(poly1 points: @local8 size: 4)
		(poly2 points: @local16 size: 4)
		(poly3 points: @local24 size: 6)
		(self addObstacle: poly1 poly2 poly3)
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
			(
				(and
					(& (= temp1 (ego onControl: 0)) $0200)
					(== local2 1)
				)
				(HandsOff)
				(= local2 2)
				(self setScript: walkOver)
			)
			((& temp1 $2000)
				(HandsOff)
				(ego priority: 13 signal: (| (ego signal?) $0010))
				(self setScript: falling)
			)
			((& temp1 $4000)
				(HandsOff)
				(ego priority: 11 signal: (| (ego signal?) $0010))
				(self setScript: falling)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance duneLooper of Script
	(properties)
	
	(method (doit)
		(ego loop: [local3 (/ (+ (ego heading?) 13) 90)])
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 78
					setLoop: 0
					cel: 0
					setCycle: End self
					illegalBits: 0
				)
			)
			(1
				(theAudio number: 7053 play:)
				(ego
					yStep: 8
					setMotion: MoveTo (+ (ego x?) 20) 230 self
				)
			)
			(2 (= seconds 3))
			(3
				(cls)
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance walkOver of Script
	(properties)
	
	(method (changeState newState &tmp theGameEgoMoveSpeed)
		(switch (= state newState)
			(0
				(ego
					illegalBits: 0
					looper: duneLooper
					setMotion: MoveTo (ego x?) 195 self
					setPri: 4
				)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 16
					setStep: 2 1
					looper: MyLooper
					setMotion: MoveTo 119 140 self
				)
			)
			(2
				(theMusic2 number: 91 loop: -1 play:)
				(wolf1
					init:
					setPri: 3
					setStep: 2 1
					setCycle: Walk
					moveSpeed: 1
					setMotion: MoveTo (- (ego x?) 5) (ego y?) self
				)
				(wolf2
					init:
					setStep: 2 1
					setCycle: Walk
					moveSpeed: 1
					setMotion: MoveTo (+ (ego x?) 5) (ego y?) self
				)
				(ego
					view: 538
					loop: 8
					cel: 0
					cycleSpeed: 2
					setPri: -1
					setCycle: End self
				)
			)
			(3)
			(4)
			(5
				(= theGameEgoMoveSpeed (theGame egoMoveSpeed?))
				(wolf1
					setLoop: 2
					moveSpeed: theGameEgoMoveSpeed
					setMotion: MoveTo 141 118
				)
				(wolf2
					setLoop: 2
					moveSpeed: theGameEgoMoveSpeed
					setMotion: MoveTo 151 118
				)
				(ego
					view: 16
					moveSpeed: theGameEgoMoveSpeed
					setCycle: Fwd
					cycleSpeed: 0
					loop: 3
					setMotion: MoveTo 147 118 self
				)
			)
			(6
				(SolvePuzzle 2)
				(curRoom newRoom: 682)
			)
		)
	)
)

(instance talkEagleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 165 154 self)
			)
			(1 (= seconds 2))
			(2
				(proc762_0 @local46 @local37 @local64 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance eagleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eagleHead hide:)
				(eagle loop: 1 cel: 0 cycleSpeed: 3 setCycle: End)
				(= state (Random -1 0))
				(= seconds (Random 5 15))
			)
			(1
				(eagle stopUpd:)
				(eagleHead show: setCycle: End)
				(theAudio number: 8864 loop: 1 play:)
				(eagle loop: 2 cel: 0)
				(= state (Random -1 0))
				(= seconds (Random 5 15))
			)
		)
	)
)

(instance feedEagle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 49 loop: -1 play:)
				(eagle stopUpd:)
				(ego setMotion: PolyPath 165 154 self)
			)
			(1
				(proc762_1 @local46 954 self)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 530
					loop: (if local36 9 else 8)
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(ego
					normal: 1
					view: 12
					loop: 7
					cel: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(eagleHead hide:)
				(eagle loop: 4 setCycle: End self)
				(if local36 (lamb loop: 10))
				(lamb init:)
				(theAudio number: 8864 loop: 1 play:)
			)
			(4
				(eagle loop: 5 setCycle: CT 2 1 self)
			)
			(5
				(eagle setCycle: End)
				(= cycles 25)
				(++ local1)
				(lamb cel: local1)
				(if (< local1 5) (= state (- state 2)))
			)
			(6
				(eagle loop: 7 setCycle: End self)
				(lamb dispose:)
			)
			(7
				(proc762_0 @local46 @local37 @local74 self)
			)
			(8
				(eagleHead dispose:)
				(eagleWing dispose:)
				(eagle setScript: 0)
				(eagle view: 532 loop: 0 cel: 0 setCycle: End self)
				(theAudio number: 8864 loop: 1 play:)
			)
			(9
				(eagle
					posn: 236 116
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo 360 (eagle y?) self
				)
			)
			(10
				(theMusic2 fade:)
				(eagle dispose:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance eagleTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(eagle view: 531 loop: 0 stopUpd:)
				(eagleHead
					view: 531
					loop: 1
					show:
					posn: 194 121
					cel: (Random (- (eagleHead cel?) 1) (+ (eagleHead cel?) 1))
					stopUpd:
				)
				(eagleWing
					cel: (Random (- (eagleWing cel?) 1) (+ (eagleWing cel?) 1))
					stopUpd:
				)
				(= cycles 2)
				(-- state)
			)
		)
	)
)

(instance path34 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(and
					(not (& (OnControl 4 (event x?) (event y?)) $0004))
					(not (& (OnControl 4 (event x?) (event y?)) $4000))
				)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 454)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance aCastle of RFeature
	(properties)
	
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
					(SpeakAudio 455)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setMotion: PolyPath 119 140)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance lamb of Prop
	(properties
		x 183
		y 137
		view 530
		loop 6
	)
)

(instance eagleWing of Prop
	(properties
		x 186
		y 128
		view 531
		loop 2
		signal $4000
	)
	
	(method (handleEvent event)
		(eagle handleEvent: event)
	)
)

(instance eagleHead of Prop
	(properties
		x 190
		y 121
		view 530
		loop 3
		signal $4000
	)
	
	(method (handleEvent event)
		(eagle handleEvent: event)
	)
)

(instance eagle of Actor
	(properties
		x 188
		y 137
		view 530
		signal $4000
		detailLevel 3
		illegalBits $0000
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
					(SpeakAudio 456)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 457)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(19
							(self setScript: 0)
							(SolvePuzzle 3)
							(ego put: 19 34)
							(HandsOff)
							(curRoom setScript: feedEagle)
							(event claimed: 1)
						)
						(2
							(self setScript: 0)
							(= local36 1)
							(ego put: 2 34)
							(HandsOff)
							(curRoom setScript: feedEagle)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 458)
							(event claimed: 1)
						)
					)
				)
				(JOY_DOWN
					(if local0
						(SpeakAudio 459)
						(event claimed: 1)
					else
						(= local0 1)
						(self setScript: talkEagleScript)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance wolf1 of Actor
	(properties
		x 160
		y 110
		view 538
		loop 5
		signal $6800
		illegalBits $0000
	)
)

(instance wolf2 of Actor
	(properties
		x 162
		y 115
		view 538
		loop 5
		signal $6800
		illegalBits $0000
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

(instance poly3 of Polygon
	(properties
		type $0002
	)
)
