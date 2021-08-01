;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use Reverse)
(use RFeature)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm031 0
)

(local
	local0
	local1
	local2
	local3
	local4
	local5
	local6
	theActor
	local8
	local9
	[local10 10] = [0 0 78 0 78 122 20 132 0 142]
	[local20 10] = [281 117 298 128 282 138 248 139 227 117]
	[local30 10] = [204 0 319 0 319 122 277 112 204 112]
	[local40 12] = [223 152 242 152 262 159 264 189 206 189 205 158]
	[local52 8] = [42 150 107 136 75 189 42 189]
	[local60 8] = [54 55 56 57 55 60 58 59]
	[local68 9] = [1000 216 73 4 11 24 19 23 30]
)
(instance rm031 of KQ5Room
	(properties
		picture 31
		east 32
	)
	
	(method (init)
		(super init:)
		(= global320 238)
		(= global321 146)
		(if (not (Btst 34))
			(self setRegions: 202)
			(= global325 3062)
		)
		(self
			setFeatures: step1 step2 step3 path31a path31b path31c log rope waterFall
		)
		(switch prevRoomNum
			(30
				(ego normal: 0 view: 478 posn: 55 213 setLoop: 0 init:)
				((ego head?) hide:)
				(= local0 0)
				(HandsOff)
				(self setScript: climbIn)
				(theMusic2 number: 54 loop: 1 play: 100)
			)
			(else 
				(ego
					view: 10
					posn: 312 156
					illegalBits: 0
					init:
					setPri: 11
				)
				(= local0 8)
				(theMusic2 number: 59 loop: 1 play:)
			)
		)
		(step4 init: stopUpd:)
		(step5 init: stopUpd:)
		(step6 init: stopUpd:)
		(poly1 points: @local10 size: 5)
		(poly2 points: @local20 size: 5)
		(poly3 points: @local30 size: 5)
		(poly4 points: @local40 size: 6)
		(poly5 points: @local52 size: 4)
		(self addObstacle: poly1 poly2 poly3 poly4 poly5)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(if (not (Btst 34))
					(self setScript: (ScriptID 202 2))
				else
					(curRoom newRoom: temp0)
				)
			)
			((& (= temp1 (ego onControl: 1)) $2000) (HandsOff) (self setScript: falling))
			((& temp1 $4000) (self setScript: walkNorth))
			((ego inRect: 231 114 253 119) (self setScript: walkSouth))
		)
	)
	
	(method (dispose)
		(DisposeScript 969)
		(DisposeScript 991)
		(ego setPri: -1)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom)
		(ego illegalBits: -32768)
		(super newRoom: &rest)
	)
)

(instance walkNorth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 494
					normal: 0
					illegalBits: 0
					setLoop: 5
					cel: 0
					cycleSpeed: 1
					setPri: 8
					setCycle: End self
					setMotion: JumpTo 271 123 self
				)
				((ego head?) hide:)
			)
			(1)
			(2
				(ego
					view: 10
					normal: 1
					setLoop: -1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setMotion: MoveTo 242 111 self
				)
				((ego head?) show:)
			)
			(3
				(ego
					view: 494
					normal: 0
					setLoop: 5
					cel: 0
					setPri: -1
					cycleSpeed: 1
					setCycle: End self
					setMotion: JumpTo 226 115 self
				)
				((ego head?) hide:)
			)
			(4)
			(5
				(ego
					view: 10
					normal: 1
					setLoop: -1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					illegalBits: 0
					setPri: 7
				)
				((ego head?) show:)
				(= local0 7)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance walkSouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 494
					normal: 0
					illegalBits: 0
					cel: 0
					setLoop: 2
					cycleSpeed: 1
					setPri: 8
					setCycle: End self
					setMotion: JumpTo 242 111 self
				)
				((ego head?) hide:)
			)
			(1)
			(2
				(ego
					normal: 1
					view: 10
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					illegalBits: 0
					setMotion: MoveTo 271 123 self
				)
				((ego head?) show:)
			)
			(3
				(ego
					view: 494
					normal: 0
					setLoop: 2
					cel: 0
					setPri: -1
					cycleSpeed: 1
					setCycle: End self
					setMotion: JumpTo 284 152 self
				)
				((ego head?) hide:)
			)
			(4)
			(5
				(ego
					view: 10
					normal: 1
					setPri: 11
					setLoop: -1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					illegalBits: -32768
				)
				((ego head?) show:)
				(= local0 8)
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
				(if (not (Btst 34))
					(proc762_1 @local68 3058 self)
				else
					(= cycles 1)
				)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 78
					setLoop: (if (ego inRect: 207 117 233 123) 1 else 0)
					cel: 0
					setCycle: End self
					illegalBits: 0
				)
				(if (<= (ego y?) 138)
					(ego setPri: 5)
				else
					(ego setPri: 10)
				)
				(theAudio number: 7053 play: self)
				(theMusic2 number: 83 loop: 1 play:)
			)
			(2
				(ego
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230 self
				)
			)
			(3 (= seconds 3))
			(4
				(cls)
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance jumping of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(cond 
					((== local0 0) (ego illegalBits: 0 setMotion: MoveTo 89 132 self))
					((== local0 7) (ego setPri: -1 setMotion: MoveTo 217 116 self))
					(else (= cycles 3))
				)
			)
			(1
				(if (and (not (Btst 34)) (not local5))
					(proc762_1 @local68 3063)
					(++ local5)
				)
				(cond 
					((== local1 7) (= local6 0))
					((== local1 0) (= local6 1))
					((and (< local0 4) (> local1 3))
						(if (< (ego x?) local2)
							(= local6 2)
						else
							(= local6 3)
						)
					)
					((and (< local1 4) (> local0 3))
						(if (< (ego x?) local2)
							(= local6 4)
						else
							(= local6 5)
						)
					)
					((< (ego x?) local2) (= local6 0))
					(else (= local6 1))
				)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 494
					looper: 0
					cycleSpeed: 0
					setLoop: local6
					cel: 0
					setCycle: End self
					setMotion: JumpTo local2 local3 self
					illegalBits: 0
				)
			)
			(2)
			(3
				(cls)
				(if (or local9 (== local1 100))
					(++ state)
					(++ state)
					(= seconds 1)
				else
					(= local0 local1)
					(theMusic3 number: [local60 local1] loop: -1 play: 100)
					(ego
						setLoop: (+ (ego loop?) 6)
						cel: 0
						setCycle: End self
						cycleSpeed: 2
					)
				)
			)
			(4
				(theMusic3 fade:)
				(ego
					normal: 1
					view: 10
					setLoop: -1
					loop: (if (mod (ego loop?) 2) 1 else 0)
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					illegalBits: -32768
				)
				(if (== local0 7) (ego setPri: 7))
				((ego head?) show:)
				(= cycles 3)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
			(6
				(theMusic3 number: 790 loop: 1 play: 80 self)
				(if (!= local1 100)
					(theActor
						view: 497
						setLoop: local8
						cel: 0
						setCycle: End
						yStep: 8
						setMotion: MoveTo (theActor x?) (+ (theActor y?) 100) self
					)
				else
					(= cycles 5)
				)
				(ego
					view: 497
					cel: 0
					setLoop: (if (mod (ego loop?) 2) 5 else 4)
					setCycle: End
					yStep: 8
					setPri: 9
					setMotion: MoveTo (ego x?) (+ (ego y?) 100) self
				)
			)
			(7
				(theAudio number: 7053 loop: 1 play: self)
			)
			(8)
			(9)
			(10
				(= deathMessage 412)
				(cls)
				(EgoDead)
			)
		)
	)
)

(instance climbIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gPolyList15
					view: 138
					loop: 2
					cel: 0
					cycleSpeed: 3
					setCycle: End
				)
				(ego cycleSpeed: 1 setMotion: MoveTo (ego x?) 189 self)
			)
			(1
				(gPolyList15 setScript: (ScriptID 202 3))
				(ego setLoop: 2 setCycle: 0 cel: 0 posn: 49 181)
				(= cycles 3)
			)
			(2
				(ego cel: 1 posn: 55 180)
				(= cycles 3)
			)
			(3
				(ego cel: 2 posn: 48 171)
				(= cycles 3)
			)
			(4
				(ego setLoop: 1 cel: 0 posn: 38 163 setCycle: End self)
			)
			(5
				(ego
					normal: 1
					view: 10
					cycleSpeed: 0
					setLoop: -1
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 25 138 self
				)
				((ego head?) show:)
			)
			(6
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance climbDownScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego illegalBits: 0 setMotion: PolyPath 38 163 self)
			)
			(1
				(ego setLoop: 0)
				(= cycles 1)
			)
			(2
				(ego setLoop: 3)
				(= cycles 1)
			)
			(3
				(ego
					normal: 0
					view: 478
					setLoop: 1
					setCel: 16
					setCycle: Beg self
				)
				((ego head?) hide:)
			)
			(4
				(ego setLoop: 2 setCycle: 0 cel: 2 posn: 48 171)
				(= cycles 3)
			)
			(5
				(ego cel: 1 posn: 55 180)
				(= cycles 3)
			)
			(6
				(ego cel: 0 posn: 49 181)
				(= cycles 3)
			)
			(7
				(gPolyList15
					view: 138
					loop: 8
					cel: 0
					cycleSpeed: 3
					setCycle: End
				)
				(ego
					setLoop: 0
					posn: 56 189
					cycleSpeed: 1
					setCycle: Rev
					setMotion: MoveTo 56 213 self
				)
			)
			(8 (curRoom newRoom: 30))
		)
	)
)

(instance path31a of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0004))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 435)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (or (== local0 1) (== local0 4))
						(= local2 86)
						(= local3 128)
						(= local1 0)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance path31b of RFeature
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
					(proc0_29 435)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if
					(or (== local0 6) (== local0 5) (== local0 4))
						(= local2 177)
						(= local3 134)
						(= local1 100)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance path31c of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0200))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 435)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (== local0 6)
						(if (not (Btst 78)) (Bset 78) (SolvePuzzle 2))
						(= local2 218)
						(= local3 114)
						(= local1 7)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance step1 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0040))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 436)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if
					(or (== local0 0) (== local0 2) (== local0 4))
						(= local2 112)
						(= local3 114)
						(= local1 1)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance step2 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0080))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 436)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if
						(or
							(== local0 1)
							(== local0 3)
							(== local0 4)
							(== local0 5)
						)
						(= local2 143)
						(= local3 110)
						(= local1 2)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance step3 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0100))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 436)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if
					(or (== local0 2) (== local0 6) (== local0 5))
						(= local2 173)
						(= local3 108)
						(= local1 3)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance step4 of Actor
	(properties
		x 131
		y 124
		view 497
		priority 6
		signal $4011
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
					(proc0_29 436)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if
					(or (== local0 1) (== local0 2) (== local0 5))
						(= local2 135)
						(= local3 118)
						(= local1 4)
						(= theActor self)
						(= local8 1)
						(if (< (Random 1 100) 50) (= local9 1))
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance step5 of Actor
	(properties
		x 162
		y 136
		view 497
		cel 1
		priority 6
		signal $4011
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
					(proc0_29 436)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if
						(or
							(== local0 6)
							(== local0 2)
							(== local0 3)
							(== local0 4)
						)
						(= local2 161)
						(= local3 119)
						(= local9 1)
						(= theActor self)
						(= local8 2)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance step6 of Actor
	(properties
		x 189
		y 134
		view 497
		cel 2
		priority 6
		signal $4011
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
					(proc0_29 436)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if
					(or (== local0 7) (== local0 3) (== local0 5))
						(= local2 189)
						(= local3 116)
						(= theActor self)
						(= local8 3)
						(if (Btst 34) (= local9 1))
						(= local1 6)
						(curRoom setScript: jumping)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance log of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0008))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 437)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance waterFall of RFeature
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
					(proc0_29 435)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance rope of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0400))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 438)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (== local0 0)
						(HandsOff)
						(curRoom setScript: climbDownScript)
					else
						(proc0_29 9074)
					)
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

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)

(instance poly5 of Polygon
	(properties
		type $0000
	)
)
