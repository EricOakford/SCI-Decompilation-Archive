;;; Sierra Script 1.0 - (do not remove this comment)
(script# 86)
(include game.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use Door)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm086 0
)

(local
	[local0 36] = [131 0 185 134 155 132 143 127 96 129 77 143 86 149 89 155 231 154 229 148 198 137 215 132 199 130 144 0 319 0 319 189 0 189]
	local36
	[local37 9] = [1005 60 100 5 8 26 19 24 32]
)
(instance rm086 of KQ5Room
	(properties
		picture 86
	)
	
	(method (init)
		(super init:)
		(addToPics add: smokeOut1 smokeOut2 doit:)
		(self setFeatures: stairs ratHole room addObstacle: poly1)
		(torch setCycle: Forward init:)
		(door init:)
		(if (== prevRoomNum 85)
			(theMusic number: 66 loop: -1 vol: 127 play:)
			(HandsOff)
			(ego
				normal: 0
				view: 818
				loop: 1
				posn: 150 128
				cycleSpeed: 3
				observeControl: 16384
				setCycle: Forward
				init:
			)
			((ego head?) hide:)
			(if
				(or
					(== ((inventory at: 8) owner?) 6)
					(== ((inventory at: 16) owner?) 6)
					(== ((inventory at: 19) owner?) 6)
					(== ((inventory at: 5) owner?) 6)
				)
				(self setScript: rescue)
			else
				(self setScript: yourStuck)
			)
		else
			(theMusic number: 787 loop: -1 play:)
			(if (== ((inventory at: 20) owner?) 86)
				(rope init: stopUpd:)
			)
			(++ local36)
			(ego posn: 179 108 init:)
		)
		(poly1 points: @local0 size: 18)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((and (& (ego onControl: 0) $4000) (Btst 80)) (HandsOff) (self setScript: exitRoom))
		)
	)
	
	(method (dispose)
		(ego ignoreControl: 16384)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance walkingDead of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 60))
			(1
				(cls)
				(= deathMessage 741)
				(EgoDead)
			)
		)
	)
)

(instance getRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 150 138 self)
			)
			(1
				(SpeakAudio 9054)
				((ego head?) hide:)
				(ego normal: 0 view: 56 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(SolvePuzzle 2)
				(rope dispose:)
				(ego get: 20 setCycle: BegLoop self)
			)
			(3
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					loop: 7
					cel: 1
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance useHammer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 175 110 self)
			)
			(1
				(SpeakAudio 736)
				(= cycles 1)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 822
					loop: 0
					cel: 0
					cycleSpeed: 2
					posn: 168 109
					setCycle: EndLoop self
				)
			)
			(3
				(theAudio number: 8113 loop: 1 play: self)
			)
			(4
				(ego cel: 0 setCycle: EndLoop self)
			)
			(5
				(theAudio number: 8113 loop: 1 play: self)
			)
			(6
				(ego cel: 0 setCycle: EndLoop self)
			)
			(7
				(theAudio number: 8113 loop: 1 play: self)
			)
			(8
				(ego cel: 0 setCycle: EndLoop self)
			)
			(9
				(theAudio number: 8113 loop: 1 play: self)
			)
			(10
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					posn: 175 110
					loop: 11
					cycleSpeed: 0
					cel: 4
					ignoreControl: 16384
				)
				(Bset 80)
				((ego head?) show:)
				(= cycles 3)
			)
			(11
				(cls)
				(SolvePuzzle 4)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance exitRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (> (ego y?) 111)
					(ego setMotion: PolyPath 182 110 self)
				else
					(= cycles 1)
				)
			)
			(1 (door setCycle: EndLoop self))
			(2
				(ego setMotion: MoveTo 173 99 self)
			)
			(3
				(cls)
				(HandsOn)
				(client setScript: 0)
				(curRoom newRoom: 28)
			)
		)
	)
)

(instance yourStuck of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 55))
			(1
				(SpeakAudio 740 0 1)
				(= seconds 7)
			)
			(2
				(cls)
				(= deathMessage 742)
				(EgoDead)
			)
		)
	)
)

(instance rescue of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (and (== state 5) (== (rat cel?) 1))
			(theMusic3 number: 881 loop: 1 vol: 127 play:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 55))
			(1
				(SpeakAudio 740 0 1)
				(= seconds 7)
			)
			(2
				(cls)
				(theMusic number: 62 loop: 1 play:)
				(rat
					setCycle: Walk
					setStep: 2 2
					moveSpeed: 1
					setMotion: MoveTo 117 132 self
					init:
				)
			)
			(3
				(rat setLoop: 2 cycleSpeed: 2 setCycle: Forward)
				(= cycles 15)
			)
			(4
				(rat
					setLoop: 0
					cycleSpeed: 0
					setCycle: Walk
					ignoreActors: 1
					setMotion: MoveTo 131 126 self
				)
			)
			(5
				(rat setLoop: 2 setCycle: Forward)
				(= cycles 100)
			)
			(6
				(rat setCycle: 0)
				(ego view: 820 loop: 0 cel: 0 setCycle: EndLoop self)
				(theAudio number: 8884 loop: 1 play:)
			)
			(7
				(rope init:)
				(ego
					normal: 1
					view: 0
					loop: 1
					cel: 7
					cycleSpeed: 0
					x: (- (ego x?) 6)
					y: (+ (ego y?) 5)
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(8
				(theMusic4 number: 63 loop: -1 vol: 127 play:)
				(= cycles 1)
			)
			(9 (= cycles 1))
			(10
				(proc762_1 @local37 5104 self)
			)
			(11 (cls) (= cycles 1))
			(12
				(rat
					setLoop: 1
					setCycle: Walk
					ignoreActors: 1
					setMotion: MoveTo 58 133 self
				)
				(theMusic4 fade:)
			)
			(13
				(theMusic number: 787 loop: -1 play:)
				(if (not (ego has: 22)) (door setScript: walkingDead))
				(rat dispose:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance moveScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1 (= cycles 10))
			(2 (client setCycle: BegLoop self))
			(3
				(= state -1)
				(= seconds (Random 3 10))
			)
		)
	)
)

(instance stairs of RFeature
	(properties
		nsTop 38
		nsLeft 162
		nsBottom 120
		nsRight 209
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 729)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance ratHole of RFeature
	(properties
		nsTop 116
		nsLeft 72
		nsBottom 131
		nsRight 88
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 730)
					(event claimed: 1)
				)
				(verbDo
					(SpeakAudio 734)
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
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 731)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance rat of Actor
	(properties
		x 60
		y 133
		view 816
		illegalBits $0000
	)
)

(instance door of Door
	(properties
		x 161
		y 100
		view 818
		loop 2
		priority 1
		signal $4011
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 729)
					(event claimed: 1)
				)
				(verbDo
					(if (not local36)
						(SpeakAudio 735)
					else
						(HandsOff)
						(curRoom setScript: exitRoom)
					)
					(event claimed: 1)
				)
				(verbUse
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(1
							(SpeakAudio 737)
							(event claimed: 1)
						)
						(22
							(if (not local36)
								(HandsOff)
								(++ local36)
								(curRoom setScript: useHammer)
							else
								(SpeakAudio 738)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(SpeakAudio 739)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance rope of Prop
	(properties
		x 148
		y 137
		view 820
		loop 2
		priority 1
		signal $4011
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 732)
					(event claimed: 1)
				)
				(verbDo
					(HandsOff)
					(curRoom setScript: getRope)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance torch of Prop
	(properties
		x 81
		y 72
		view 818
		loop 3
		cycleSpeed 2
		detailLevel 3
	)
)

(instance hands of Prop
	(properties
		x 74
		y 93
		view 223
		loop 2
		cel 3
		priority 12
		signal $4010
	)
)

(instance theMouth of Prop
	(properties
		x 73
		y 69
		view 223
		loop 3
		cel 2
		priority 12
		signal $4010
		cycleSpeed 3
	)
)

(instance theEyes of Prop
	(properties
		x 73
		y 63
		view 223
		loop 4
		cel 2
		priority 13
		signal $4010
	)
)

(instance eyebrows of Prop
	(properties
		x 73
		y 57
		view 223
		loop 5
		cel 1
		priority 13
		signal $4010
	)
)

(instance face of Prop
	(properties
		x 75
		y 110
		view 223
		priority 11
		signal $4010
	)
)

(instance cloud of Prop
	(properties
		x 75
		y 82
		view 214
		priority 10
		signal $4010
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance smokeOut1 of PicView
	(properties
		x 187
		y 3
		view 292
		loop 3
	)
)

(instance smokeOut2 of PicView
	(properties
		x 294
		y 7
		view 292
		loop 4
	)
)
