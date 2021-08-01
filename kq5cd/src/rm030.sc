;;; Sierra Script 1.0 - (do not remove this comment)
(script# 30)
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
	rm030 0
)

(local
	[local0 8] = [23 177 41 173 63 172 41 179]
	[local8 19] = [0 0 319 0 319 167 180 167 135 153 112 159 75 159 52 164 0 163]
	gEgoView
	local28
	[local29 9] = [1000 27 66 4 11 24 19 23 30]
)
(instance rm030 of KQ5Room
	(properties
		picture 30
		east 31
		west 29
	)
	
	(method (init)
		(super init:)
		(= global320 63)
		(= global321 132)
		(= global325 3059)
		(self
			setFeatures: rock cliff path30 ledge
			setRegions: 202
		)
		(Load rsSOUND 892)
		(branch init: stopUpd:)
		(if (== ((inventory at: 20) owner?) 30)
			(rope
				view: 479
				posn: 131 24
				loop: 4
				cel: (- (NumCels rope) 1)
				init:
				stopUpd:
			)
		)
		(if (== ((inventory at: 20) owner?) 31)
			(rope cel: (- (NumCels rope) 1) init: stopUpd:)
		)
		(switch prevRoomNum
			(31
				(ego
					normal: 1
					view: 488
					posn: 127 23
					loop: 0
					setCel: 16
					cycleSpeed: 5
					setPri: 10
					init:
				)
				(if (IsObject (ego head?)) ((ego head?) hide:))
				(rope hide:)
				(self setScript: climbDownScript)
			)
			(29
				(ego view: (if (Btst 15) 12 else 106) posn: 6 171 init:)
				(self setScript: (ScriptID 202 1))
			)
			(else 
				(ego view: (if (Btst 15) 12 else 106) posn: 6 171 init:)
			)
		)
		(poly1 points: @local0 size: 4)
		(poly2 points: @local8 size: 9)
		(self addObstacle: poly1 poly2)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				((ScriptID 202 2) register: (ego edgeHit?))
				(self setScript: (ScriptID 202 2))
			)
			((& (ego onControl: 0) $2000) (HandsOff) (self setScript: falling))
		)
	)
	
	(method (dispose)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
			((MousedOn ego event)
				(switch (event message?)
					(JOY_DOWNRIGHT
						(switch (inventory indexOf: (theIconBar curInvIcon?))
							(26
								(if (not (Btst 15))
									(if (not (Btst 48)) (Bset 48) (SolvePuzzle 4))
									(Bset 15)
									(proc0_29 415)
									(ego view: 12)
									((ego head?) show:)
									(event claimed: 1)
								)
							)
							(28 (event claimed: 0))
							(else 
								(if (not (Btst 15)) (proc0_29 416) (event claimed: 1))
							)
						)
					)
				)
			)
		)
	)
)

(instance climbDownScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gPolyList15
					view: 140
					loop: 2
					cel: 0
					cycleSpeed: 3
					setCycle: End
				)
				(ego setCycle: Beg self)
			)
			(1
				(rope show: stopUpd:)
				(ego
					normal: 1
					view: 479
					loop: 5
					setCel: 16
					posn: 127 157
					setCycle: Beg self
				)
			)
			(2
				(gPolyList15 setScript: (ScriptID 202 3))
				((ego head?) show:)
				(ego
					normal: 1
					view: (if (Btst 15) 12 else 106)
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setLoop: -1
					setPri: -1
					loop: 3
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance ropeOnBranch of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 15))
					(HandsOff)
					(curRoom setScript: freeze)
				else
					(self cue:)
				)
			)
			(1
				(ego setMotion: PolyPath 48 165 self)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 479
					cel: 0
					loop: 0
					cycleSpeed: 2
					setCycle: CT 6 1 self
				)
				(theAudio number: 8069 loop: 1 play:)
			)
			(3
				(ego
					normal: 1
					view: 12
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					loop: 11
					cel: 6
				)
				((ego head?) show:)
				(rope
					view: 479
					setLoop: 1
					posn: 79 21
					cycleSpeed: 1
					cel: 0
					setCycle: End self
					setMotion: MoveTo 82 35 self
					init:
				)
			)
			(4
				(rope stopUpd:)
				(cls)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance ropeOnRock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 15))
					(HandsOff)
					(curRoom setScript: freeze)
				else
					(self cue:)
				)
			)
			(1
				(if (and (not (Btst 14)) (not (Btst 16)))
					(Bset 14)
					(proc0_29 418)
				)
				(= cycles 1)
			)
			(2
				(ego setMotion: PolyPath 93 166 self)
			)
			(3
				(= gEgoView (ego view?))
				((ego head?) hide:)
				(ego
					normal: 0
					view: 479
					setAvoider: 0
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
				(theAudio number: 8069 loop: 1 play:)
			)
			(4
				(ego
					normal: 1
					view: gEgoView
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					loop: 11
					cel: 0
				)
				((ego head?) show:)
				(rope
					view: 479
					posn: 131 24
					loop: 4
					cycleSpeed: 1
					cel: 0
					setPri: 10
					setCycle: End self
					init:
				)
			)
			(5
				(rope stopUpd:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance climbRope of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(self register: (== ((inventory at: 20) owner?) 30))
				(if register
					(ego setMotion: PolyPath 127 160 self)
				else
					(ego setMotion: PolyPath 83 173 self)
				)
			)
			(1
				(if (not (Btst 15))
					(ego setScript: freeze)
				else
					(ego setMotion: MoveTo (ego x?) (- (ego y?) 3) self)
				)
			)
			(2
				((ego head?) hide:)
				(ego
					normal: 1
					view: 479
					loop: 5
					cel: 0
					setCycle: End self
				)
				(if (not (Btst 88)) (proc762_1 @local29 3061))
				(if (== ((inventory at: 20) owner?) 31)
					(branch setCycle: End)
				)
			)
			(3
				(if register
					(rope hide:)
					(ego
						normal: 0
						view: 488
						posn: 127 23
						loop: 0
						cel: 0
						cycleSpeed: 5
						setPri: 10
						setCycle: End self
					)
				else
					(rope hide:)
					(ego
						normal: 0
						view: 485
						posn: 84 38
						loop: 0
						cel: 0
						cycleSpeed: 5
						setPri: 10
						setCycle: End self
					)
				)
			)
			(4
				(cls)
				(if register
					(if (== (theGame detailLevel:) 3)
						(gPolyList15
							view: 140
							loop: 5
							cycleSpeed: 0
							setScript: 0
							setCycle: CT 7 1 self
						)
					else
						(= cycles 1)
					)
				else
					(ego
						loop: 1
						posn: 70 165
						cel: 0
						cycleSpeed: 3
						setPri: 10
						setCycle: End self
					)
					(branch loop: 3 cel: 0 cycleSpeed: 2 setCycle: End)
					(theAudio number: 8856 loop: 1 play: self)
				)
			)
			(5
				(if (not register)
					(theAudio number: 8892 loop: 1 play:)
				)
				(= cycles 1)
			)
			(6
				(if register
					(if (== (theGame detailLevel:) 3)
						(gPolyList15
							cycleSpeed: 1
							loop: 9
							cel: 0
							setCycle: CT 4 1 self
						)
					else
						(= cycles 1)
					)
				else
					(= seconds 5)
				)
			)
			(7
				(if register
					(if (not (Btst 88)) (Bset 88) (SolvePuzzle 5))
					(curRoom newRoom: 31)
				else
					(EgoDead)
				)
			)
		)
	)
)

(instance freeze of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (< (ego x?) 75)
					(ego setMotion: MoveTo 93 (ego y?) self)
				else
					(ego setMotion: MoveTo 93 (ego y?) self)
				)
			)
			(1
				((ego head?) hide:)
				(ego normal: 0 view: 501 cycleSpeed: 5 setCycle: End self)
			)
			(2 (= seconds 5))
			(3
				(= deathMessage 419)
				(EgoDead 245)
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
				(proc762_1 @local29 3060 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: (if (== (ego view?) 106) 70 else 78)
					setLoop: 0
					cel: 0
					cycleSpeed: 1
					setCycle: End self
					setPri: 5
					illegalBits: 0
				)
				(theAudio number: 7053 play:)
				(theMusic3 number: 83 loop: 1 vol: 127 priority: 15 play:)
			)
			(2
				(ego
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230 self
				)
			)
			(3 (= seconds 3))
			(4
				(= deathMessage 434)
				(EgoDead)
			)
		)
	)
)

(instance path30 of RFeature
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
					(proc0_29 421)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cliff of RFeature
	(properties
		nsTop 5
		nsBottom 147
		nsRight 139
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
					(proc0_29 422)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 431)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance ledge of RFeature
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
					(proc0_29 423)
					(proc0_29 424)
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
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0010))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(if (== ((inventory at: 20) owner?) 30)
						(proc0_29 425)
					else
						(proc0_29 426)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 432)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(20
							(ego put: 20 30)
							(HandsOff)
							(curRoom setScript: ropeOnRock)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance branch of Prop
	(properties
		x 65
		y 28
		view 479
		loop 2
		priority 8
		signal $0010
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
					(if (== ((inventory at: 20) owner?) 31)
						(proc0_29 427)
					else
						(proc0_29 428)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(proc0_29 433)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(20
							(ego put: 20 31)
							(HandsOff)
							(curRoom setScript: ropeOnBranch)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance rope of Actor
	(properties
		x 82
		y 35
		view 479
		loop 1
		priority 9
		signal $4010
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
					(if (== ((inventory at: 20) owner?) 30)
						(proc0_29 429)
					else
						(proc0_29 430)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: climbRope)
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
