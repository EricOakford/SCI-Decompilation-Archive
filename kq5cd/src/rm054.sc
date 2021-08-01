;;; Sierra Script 1.0 - (do not remove this comment)
(script# 54)
(include sci.sh)
(use Main)
(use Intrface)
(use CodeCue)
(use n770)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm054 0
)

(local
	[local0 12] = [317 189 223 147 168 116 168 0 319 0 319 189]
	[local12 14] = [0 189 0 0 164 0 164 120 117 122 91 144 13 189]
	[local26 8] = [123 125 168 125 189 144 105 144]
	local34
	[local35 9] = [1000 173 8 4 11 24 19 23 30]
	[local44 9] = [1003 115 113 4 11 25 23 31 31]
	[local53 6] = [0 3086 1 7033]
	[local59 12] = [0 3087 1 7034 0 3088 1 7035 0 3089]
)
(instance rm054 of KQ5Room
	(properties
		picture 54
		south 53
	)
	
	(method (init)
		(super init:)
		(if (not (Btst 55))
			(= global325 3083)
			(= global320 208)
			(= global321 56)
			(self setRegions: 202)
			(if (not (Btst 99)) (ego setScript: conversation))
		)
		(self setRegions: 550 setFeatures: wall island)
		(grate init: stopUpd:)
		(if (== ((inventory at: 30) owner?) 54)
			(grate loop: 5 posn: 174 126 setCel: 8)
			(poker init: stopUpd:)
		)
		(if (not (Btst 55))
			(ego view: 0 posn: 156 186 init:)
			(self setScript: (ScriptID 202 1))
		else
			(ego view: 0 posn: 156 246 init:)
			(self setScript: enterIn)
		)
		(if
			(and
				(!= ((inventory at: 30) owner?) 54)
				(not (ego has: 30))
			)
			(= global345 163)
			(= global346 182)
			(= global347 271)
			(= global348 171)
			((ScriptID 550 3) init:)
		)
		(poly1 points: @local0 size: 6)
		(poly2 points: @local12 size: 7)
		(poly3 points: @local26 size: 4)
		(self addObstacle: poly1 poly2 poly3)
	)
	
	(method (doit &tmp temp0)
		(if (not (== script falling))
			(if (<= (ego y?) 139)
				(ego priority: 8 signal: (| (ego signal?) $0010))
			else
				(ego priority: -1 signal: (& (ego signal?) $ffef))
			)
		)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (and (== temp0 53) (not (Btst 55)))
					((ScriptID 202 2) register: 3)
					(self setScript: (ScriptID 202 2))
				else
					(curRoom newRoom: temp0)
				)
			)
			((& (ego onControl: 0) $2000) (HandsOff) (self setScript: falling))
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance enterIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 156 186 self)
			)
			(1 (HandsOn) (self dispose:))
		)
	)
)

(instance conversation of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if local34
			(theGame setCursor: waitCursor)
			(User canControl: 0 canInput: 0)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local34 1)
				(Bset 99)
				(= seconds 6)
			)
			(1
				(proc770_0 @local44 ego)
				(proc762_0 @local35 @local44 @local53 self)
			)
			(2
				(= local34 0)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance falling of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (cast contains: (ScriptID 550 3))
					((ScriptID 550 3) setScript: 0 setMotion: 0)
				)
				(if (not (Btst 55))
					(proc762_1 @local35 3032 self)
				else
					(= cycles 1)
				)
			)
			(1
				(theAudio number: 7053 play:)
				(theMusic3 number: 60 loop: 1 play:)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 68
					setLoop: 1
					cel: 0
					setCycle: End self
					cycleSpeed: 1
					setPri: 1
					illegalBits: 0
				)
			)
			(2
				(ego
					yStep: 8
					setMotion: MoveTo (- (ego x?) 20) 230 self
				)
			)
			(3 (= seconds 3))
			(4
				(= deathMessage 412)
				(HandsOn)
				(EgoDead)
			)
		)
	)
)

(instance liftGrate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 30 54)
				(HandsOff)
				(Bset 41)
				(ego normal: 0 setMotion: PolyPath 198 144 self)
			)
			(1
				((ego head?) hide:)
				(ego
					view: 672
					setLoop: 1
					posn: 198 144
					cycleSpeed: 3
					cel: 0
					setCycle: End self
				)
			)
			(2
				(theAudio number: 8132 loop: 1 play: self)
				(ego setLoop: 2 setCycle: Fwd)
			)
			(3
				(ego setLoop: 1 cel: 3 setCycle: Beg)
				(= seconds 4)
			)
			(4
				(ego
					view: 0
					setLoop: -1
					normal: 1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					setMotion: MoveTo 165 145 self
				)
				((ego head?) show:)
			)
			(5
				((ego head?) hide:)
				(ego
					normal: 0
					view: 672
					posn: 162 149
					cycleSpeed: 3
					setLoop: 3
					cel: 0
					setCycle: End self
				)
			)
			(6
				(ego setLoop: 4 cel: 0 setCycle: End self)
				(grate cycleSpeed: 3 loop: 5 setCycle: End)
			)
			(7
				(ego setLoop: 6 cel: 0 setCycle: End self)
			)
			(8
				(poker init:)
				(grate posn: 174 126)
				(RedrawCast)
				(ego setLoop: 7 cel: 0 setCycle: End self)
			)
			(9
				(ego setLoop: 8 cel: 0 setCycle: End self)
			)
			(10
				(ego
					view: 0
					setLoop: -1
					normal: 1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
				)
				(SolvePuzzle 4)
				((ego head?) show:)
				(= cycles 3)
			)
			(11
				(grate stopUpd:)
				(HandsOn)
				(Face ego grate 5)
				(client setScript: 0)
			)
		)
	)
)

(instance enterGrate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (Btst 55))
					(cond 
						((< (theGame detailLevel:) 3) (= cycles 1))
						((== (gPolyList15 cel?) 0) (gPolyList15 setScript: 0 setCycle: CT 1 1 self))
						((> (gPolyList15 cel?) 1) (gPolyList15 setScript: 0 setCycle: CT 1 -1 self))
						(else (= cycles 1))
					)
				else
					(= cycles 1)
				)
				(ego setMotion: PolyPath 165 145 self)
			)
			(1)
			(2
				(if (not (Btst 55))
					(proc770_0 @local44 ego)
					(proc762_0 @local35 @local44 @local59 self)
				else
					(= cycles 1)
				)
			)
			(3
				((ego head?) hide:)
				(ego
					normal: 0
					view: 672
					posn: 165 145
					cycleSpeed: 3
					cel: 3
					setLoop: 8
					setCycle: Beg self
				)
			)
			(4
				(ego setLoop: 9 cel: 0 setCycle: End self)
			)
			(5
				(HandsOn)
				(curRoom newRoom: 55)
			)
		)
	)
)

(instance pullGrate of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 169 145 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 672
					cycleSpeed: 3
					setLoop: 3
					cel: 0
					setCycle: End self
				)
			)
			(2 (proc0_29 571) (= cycles 1))
			(3 (ego setCycle: Beg self))
			(4
				(ego
					view: 0
					setLoop: -1
					normal: 1
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(5
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance grate of Prop
	(properties
		x 174
		y 125
		view 672
		priority 9
		signal $4011
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(and
					(not (MousedOn self event))
					(not (& (OnControl 4 (event x?) (event y?)) $0004))
				)
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(if (Btst 41) (proc0_29 566) else (proc0_29 567))
					(event claimed: 1)
				)
				(JOY_RIGHT
					(cond 
						(
							(and
								(cast contains: (ScriptID 550 3))
								(>= (((ScriptID 550 3) script?) state?) 1)
							)
							(proc0_29 9069)
						)
						((Btst 41) (HandsOff) (curRoom setScript: enterGrate))
						(else (HandsOff) (curRoom setScript: pullGrate))
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(30
							(curRoom setScript: liftGrate)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(if (== ((inventory at: 30) owner?) 54)
								(proc0_29 572)
							else
								(proc0_29 573)
							)
							(event claimed: 1)
						)
					)
				)
			)
		)
	)
)

(instance poker of Prop
	(properties
		x 166
		y 144
		view 672
		loop 10
		priority 10
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
				(JOY_UPRIGHT
					(proc0_29 568)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance wall of RFeature
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
					(proc0_29 569)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance island of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
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
					(proc0_29 570)
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
