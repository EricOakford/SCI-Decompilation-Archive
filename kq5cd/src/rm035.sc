;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm035 0
)

(local
	local0
	[local1 34] = [0 0 319 0 319 59 277 59 228 66 188 66 178 72 185 79 216 83 217 87 152 87 74 95 43 107 40 130 79 165 40 173 0 174]
	[local35 16] = [319 75 251 75 244 83 183 74 185 70 207 70 251 67 319 65]
	[local51 10] = [319 87 271 87 250 80 252 77 319 77]
	[local61 10] = [319 93 246 93 261 87 265 90 319 90]
	[local71 18] = [319 120 170 120 159 129 78 129 78 109 146 96 242 93 244 96 319 96]
	[local89 12] = [319 140 193 143 184 133 165 129 170 123 319 123]
	[local101 10] = [319 189 114 189 127 168 189 145 319 143]
	[local111 8] = [0 189 0 180 39 177 56 189]
	[local119 9] = [1028 90 42 3 9 26 24 32 32]
	[local128 9] = [1000 233 56 4 11 24 19 23 30]
	[local137 9] = [1023 65 86 2 8 23 24 21 29]
	[local146 6] = [0 7023 1 5107]
)
(instance rm035 of KQ5Room
	(properties
		picture 35
		horizon 60
		north 36
		south 40
	)
	
	(method (init)
		(super init:)
		(HandsOn)
		(self setFeatures: cave path35)
		(= global320 248)
		(= global321 112)
		(if (Btst 57)
			(= global325 3057)
			(self setRegions: 202)
		)
		(if (Btst 27) (= local0 1))
		(wolf init:)
		(switch prevRoomNum
			(39
				(ego
					normal: 1
					view: 10
					cycleSpeed: 0
					moveSpeed: (theGame egoMoveSpeed?)
					setLoop: -1
					setStep: 3 2
					setPri: -1
					setCycle: KQ5SyncWalk
					posn: 105 145
					init:
				)
				(if (!= ((inventory at: 2) owner?) 36)
					(self setScript: goGetYeti)
				else
					(Bset 57)
					(ego posn: 85 175)
					(wolf posn: 43 180)
				)
			)
			(92
				(ego
					normal: 1
					view: 10
					cycleSpeed: 0
					moveSpeed: (theGame egoMoveSpeed?)
					setLoop: -1
					setStep: 3 2
					setPri: -1
					setCycle: KQ5SyncWalk
					posn: 105 145
					init:
				)
				(if (!= ((inventory at: 2) owner?) 36)
					(self setScript: goGetYeti)
				else
					(Bset 57)
					(ego posn: 85 175)
					(wolf posn: 43 180)
				)
			)
			(682
				(ego
					normal: 1
					view: 10
					cycleSpeed: 0
					moveSpeed: (theGame egoMoveSpeed?)
					setLoop: -1
					setStep: 3 2
					setPri: -1
					setCycle: KQ5SyncWalk
					posn: 105 145
					init:
				)
				(if (!= ((inventory at: 2) owner?) 36)
					(self setScript: goGetYeti)
				else
					(Bset 57)
					(ego posn: 85 175)
					(wolf posn: 43 180)
				)
			)
			(36
				(if (!= ((inventory at: 2) owner?) 36)
					(HandsOff)
					(yeti setLoop: 2 setCycle: Walk init:)
					(ego
						normal: 0
						view: 540
						setStep: 2 1
						setLoop: 1
						setCycle: KQ5SyncWalk
						posn: 250 61
						init:
					)
					(self setScript: killEgo)
					(theMusic2 number: 110 loop: -1 vol: 127 play:)
				else
					(HandsOn)
					(ego view: 16 posn: 241 66 init:)
					(if (Btst 57) (wolf posn: 43 180))
				)
				((ego head?) hide:)
			)
			(40
				(ego normal: 1 view: 10 cycleSpeed: 0 posn: 99 186 init:)
				(wolf posn: 43 180)
			)
			(else 
				(ego view: 16 posn: 241 65 init:)
				((ego head?) hide:)
			)
		)
		(if
			(and
				(!= ((inventory at: 2) owner?) 36)
				(!= prevRoomNum 36)
			)
			(theMusic2 number: 117 loop: -1 play:)
		)
		(tail
			x: (wolf x?)
			y: (+ (wolf y?) 5)
			setCycle: Fwd
			cycleSpeed: 3
			init:
			setScript: swish
		)
		(poly1 points: @local1 size: 17)
		(poly2 points: @local35 size: 8)
		(poly3 points: @local51 size: 5)
		(poly4 points: @local61 size: 5)
		(poly5 points: @local71 size: 9)
		(poly6 points: @local89 size: 6)
		(poly7 points: @local101 size: 5)
		(poly8 points: @local111 size: 4)
		(self
			addObstacle: poly1 poly2 poly3 poly4 poly5 poly6 poly7 poly8
		)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			((== script falling) 0)
			((& (= temp1 (ego onControl: 1)) $0008)
				(ego normal: 1 view: 16 setStep: 2 1)
				((ego head?) hide:)
			)
			((& temp1 $1000)
				(ego normal: 1 view: 12 setStep: 3 2)
				((ego head?) show:)
			)
			((& temp1 $0002) (ego normal: 1 view: 10))
			((& temp1 $0004) (ego normal: 1 view: 12))
		)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (Btst 57)
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				)
			)
			((& (= temp1 (ego onControl: 1)) $4000)
				(if
					(and
						(== ((inventory at: 2) owner?) 36)
						(== prevRoomNum 39)
					)
					(proc762_1 @local128 3065 36 0)
				else
					(curRoom newRoom: 36)
				)
			)
			((& temp1 $2000) (self setScript: falling))
			((& temp1 $0100)
				(ego
					priority: (- (ego priority?) 1)
					signal: (| (ego signal?) $0010)
				)
				(self setScript: falling)
			)
			(
				(and
					(or
						(and (< (ego x?) 127) (> (ego y?) 150))
						(> (ego y?) 170)
					)
					(!= ((inventory at: 2) owner?) 36)
				)
				(HandsOff)
				(self setScript: goBack)
			)
			(
			(and (Btst 57) (< (ego x?) 78) (> (ego y?) 150)) (HandsOff) (self setScript: goBack2))
			(
				(and
					(not (Btst 57))
					(< (ego x?) 127)
					(> (ego y?) 150)
					(== ((inventory at: 2) owner?) 36)
				)
				(HandsOff)
				(self setScript: doneYeti)
			)
		)
	)
	
	(method (dispose)
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

(instance killEgo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 228 66)
				(yeti setMotion: MoveTo 231 66 self)
			)
			(1
				(ego hide:)
				(yeti setLoop: 3 cel: 0 setCycle: End self)
			)
			(2
				(yeti setLoop: 4 cel: 0 setCycle: End self)
			)
			(3
				(yeti setLoop: 5 cel: 0 setCycle: End self)
			)
			(4
				(yeti
					setLoop: 6
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 275 62 self
				)
			)
			(5
				(theMusic2 stop:)
				(= cycles 3)
			)
			(6
				(= deathMessage 470)
				(EgoDead 544)
			)
		)
	)
)

(instance swish of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tail setCycle: End)
				(= seconds (Random 3 8))
			)
			(1
				(tail setCycle: Beg)
				(= seconds (Random 3 8))
				(= state -1)
			)
		)
	)
)

(instance goGetYeti of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 10))
			(1
				(proc762_1 @local137 5105 self)
			)
			(2
				(HandsOn)
				(theIconBar enable: 0 1 2 3)
				(client setScript: 0)
			)
		)
	)
)

(instance goBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tail hide:)
				(wolf loop: 11 cel: 4 setCycle: Beg self)
			)
			(1
				(proc0_29 468)
				(ego setMotion: PolyPath 105 145 self)
			)
			(2
				(cls)
				(wolf setCycle: End self)
			)
			(3
				(wolf loop: 15)
				(tail show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance goBack2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(tail hide:)
				(wolf loop: 11 cel: 4 setCycle: Beg self)
			)
			(1
				(proc0_29 469)
				(ego setMotion: PolyPath 85 (ego y?) self)
			)
			(2 (wolf setCycle: End self))
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance doneYeti of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc762_1 @local137 5108)
				(tail hide:)
				(wolf loop: 11 cel: 4 setCycle: Beg self)
			)
			(1
				(wolf setLoop: 0 setCycle: Walk setMotion: MoveTo -40 178)
				(ego setMotion: PolyPath -25 175 self)
			)
			(2 (curRoom newRoom: 39))
		)
	)
)

(instance heyBudIWon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 113 151 self)
			)
			(1
				(Face ego wolf 5)
				(= cycles 2)
			)
			(2
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance nowHoldOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 105 145 self)
			)
			(1
				(Face ego wolf 5)
				(= cycles 3)
			)
			(2
				(= local0 1)
				(proc762_0 @local119 @local137 @local146 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
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
					view:
					(switch (ego view?)
						(16 82)
						(12 78)
						(else  76)
					)
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					setPri: (if (< (ego y?) 80) 0 else 1)
					illegalBits: 0
					ignoreActors: 1
				)
				(theMusic stop:)
				(theMusic2 stop:)
				(theAudio number: 7053 play:)
				(theMusic3 number: 83 loop: 1 vol: 127 priority: 15 play:)
			)
			(1
				(ego
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230 self
				)
			)
			(2 (= seconds 3))
			(3
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance yeti of Actor
	(properties
		x 266
		y 64
		view 540
		loop 1
	)
)

(instance tail of Prop
	(properties
		x 81
		y 177
		view 856
		loop 14
	)
)

(instance wolf of Actor
	(properties
		x 81
		y 172
		view 856
		loop 15
	)
	
	(method (init)
		(super init: &rest)
		(self stopUpd:)
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
					(cond 
						((Btst 57) (proc0_29 466))
						((!= ((inventory at: 2) owner?) 36) (proc0_29 468))
						(else (proc0_29 460))
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (!= ((inventory at: 2) owner?) 36)
						(proc0_29 464)
						(event claimed: 1)
					)
				)
				(JOY_DOWN
					(cond 
						((!= ((inventory at: 2) owner?) 36)
							(if (Btst 27)
								(proc0_29 465)
							else
								(Bset 27)
								(HandsOff)
								(curRoom setScript: nowHoldOn)
							)
						)
						((Btst 57) (proc0_29 466))
						(else (HandsOff) (curRoom setScript: heyBudIWon))
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(28 (event claimed: 0))
						(else 
							(proc0_29 467)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance cave of RFeature
	(properties
		nsTop 39
		nsLeft 226
		nsBottom 61
		nsRight 308
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
					(if (Btst 57) (proc0_29 461) else (proc0_29 462))
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setMotion: PolyPath 257 64)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance path35 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 463)
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
	(properties)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties)
)

(instance poly5 of Polygon
	(properties
		type $0002
	)
)

(instance poly6 of Polygon
	(properties)
)

(instance poly7 of Polygon
	(properties
		type $0002
	)
)

(instance poly8 of Polygon
	(properties
		type $0002
	)
)
