;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm032 0
)

(local
	local0
	local1
	[local2 8] = [0 130 47 130 47 189 0 189]
	[local10 12] = [92 131 137 128 177 179 152 189 50 189 50 130]
	[local22 18] = [0 0 319 0 319 188 225 188 187 172 168 130 180 123 139 108 0 108]
	local40
	[local41 18] = [1028 5 70 3 9 26 24 32 32 1000 75 60 4 11 24 19 23 30]
)
(instance rm032 of KQ5Room
	(properties
		picture 32
		south 33
		west 31
	)
	
	(method (init)
		(= global320 101)
		(= global321 92)
		(super init:)
		(if (Btst 34)
			(++ local0)
		else
			(self setRegions: 202)
			(wolf posn: -80 122 setCycle: Walk init:)
			(theMusic2 number: 829 loop: 1 play:)
		)
		(switch prevRoomNum
			(south
				(ego view: 10 posn: 202 184 init:)
				(= local40 1)
			)
			(else 
				(ego view: 10 posn: 10 128 init:)
			)
		)
		(self
			setFeatures: path32 halfDome area mountains
			addObstacle: poly1 poly2 poly3
		)
		(poly1 points: @local2 size: 4)
		(poly2 points: @local10 size: 6)
		(poly3 points: @local22 size: 9)
	)
	
	(method (doit &tmp temp0 temp1)
		(cond 
			(script (script doit:))
			(
			(and (not (Btst 16)) (not local1) (>= (ego x?) 30)) (proc0_29 444) (= local1 1))
			(
			(and (not (Btst 16)) local1 (>= (ego x?) 105)) (curRoom setScript: hungerDeath))
			((not local0) (++ local0) (self setScript: catchCedric))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
			((& (ego onControl: 0) $2000) (self setScript: sliding))
			((& (= temp1 (ego onControl: 1)) $4000) (self setScript: climbUp))
			((& temp1 $0038) (HandsOff) (self setScript: falling))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			(else
				(switch (event message?)
					(JOY_DOWNRIGHT
						(if (MousedOn ego event)
							(switch (inventory indexOf: (theIconBar curInvIcon?))
								(29
									(if (not local40)
										(event claimed: 1)
										(ego setScript: useSled)
									)
								)
								(28 (event claimed: 0))
								(else 
									(proc0_29 442)
									(event claimed: 1)
								)
							)
						)
					)
				)
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
					view: 76
					setLoop: 0
					signal: 16384
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					setPri:
						(cond 
							((& (ego onControl: 1) $0008) 1)
							((& (ego onControl: 1) $0010) 3)
							(else 13)
						)
					illegalBits: 0
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230
				)
				(theAudio number: 7053 play:)
				(= cycles 1)
				(theMusic4 number: 83 loop: 1 play: self)
			)
			(1)
			(2 (= seconds 3))
			(3
				(= deathMessage 412)
				(EgoDead)
			)
		)
	)
)

(instance sliding of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 510
					setLoop: 0
					cel: 0
					cycleSpeed: 4
					setCycle: End self
				)
			)
			(1
				(theMusic2 number: 830 loop: 1 play:)
				(ego
					setLoop: 1
					cel: 0
					x: (- (ego x?) 10)
					y: (- (ego y?) 10)
					setCycle: Fwd
					setPri: 5
					yStep: 8
					xStep: 8
					illegalBits: 0
					setMotion: MoveTo 202 182 self
				)
			)
			(2
				(ego setLoop: 2 cel: 0 y: 184 setCycle: End self)
			)
			(3
				(= local40 1)
				(HandsOn)
				(ego
					normal: 1
					view: 10
					setLoop: -1
					cycleSpeed: 0
					illegalBits: -32768
					setStep: 3 2
					setCycle: KQ5SyncWalk
					setPri: -1
				)
				((ego head?) show:)
				(client setScript: 0)
			)
		)
	)
)

(instance climbUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 512
					cel: 0
					posn: (- (ego x?) 15) (- (ego y?) 37)
					setCycle: End self
					illegalBits: 0
					cycleSpeed: 3
				)
			)
			(1
				(= local40 0)
				(HandsOn)
				(ego
					normal: 1
					view: 10
					setLoop: -1
					illegalBits: -32768
					posn: (- (ego x?) 9) (- (ego y?) 13)
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setPri: -1
				)
				((ego head?) show:)
				(client setScript: 0)
			)
		)
	)
)

(instance catchCedric of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 15 (ego y?) self)
			)
			(1
				(wolf
					setLoop: 0
					setStep: 4 4
					setCycle: Walk
					cycleSpeed: 1
					setMotion: MoveTo 23 122 self
				)
			)
			(2
				(wolf setMotion: MoveTo 43 122 self)
				(proc0_29 3075 0 1)
			)
			(3
				(wolf
					setLoop: 1
					cel: 0
					setMotion: MoveTo 69 122
					setCycle: CT 3 1 self
				)
			)
			(4
				(gPolyList15 dispose:)
				(wolf cel: 3 setMotion: MoveTo 200 122 setCycle: End self)
			)
			(5
				(Bset 34)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 502
					loop: 4
					cel: 0
					setCycle: End self
				)
			)
			(6
				(theMusic2 number: 830 loop: 1 play:)
				(wolf
					cycleSpeed: 0
					setStep: 7 7
					setLoop: 2
					cel: 0
					setCycle: End self
				)
			)
			(7
				(wolf
					cycleSpeed: 0
					setStep: 4 4
					setLoop: 3
					cel: 0
					setMotion: MoveTo 227 225 self
				)
			)
			(8
				(cls)
				(proc762_1 @local41 7021 self)
			)
			(9
				(ego normal: 1 view: 10 loop: 0 setCycle: KQ5SyncWalk)
				((ego head?) show:)
				(= gPolyList15 0)
				(cls)
				(wolf dispose:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance hungerDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= seconds 2))
			(1
				((ego head?) hide:)
				(ego
					view: 508
					normal: 0
					setLoop: 0
					cycleSpeed: 3
					setMotion: 0
				)
				(= seconds 2)
			)
			(2 (ego setCycle: End self))
			(3 (= seconds 2))
			(4
				(= deathMessage 443)
				(EgoDead 267)
			)
		)
	)
)

(instance useSled of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 29 32)
				(SolvePuzzle 5)
				(HandsOff)
				(ego setMotion: MoveTo 120 117 self)
			)
			(1
				(= gPolyList15 100)
				(curRoom newRoom: 33)
			)
		)
	)
)

(instance path32 of RFeature
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
					(proc0_29 439)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance halfDome of RFeature
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
					(proc0_29 440)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance mountains of RFeature
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
					(proc0_29 441)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wolf of Actor
	(properties
		view 502
	)
)

(instance area of RFeature
	(properties
		nsTop 108
		nsBottom 137
		nsRight 195
	)
	
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
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(29
							(if (not local40)
								(event claimed: 1)
								(ego setScript: useSled)
							)
						)
					)
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
	(properties)
)
