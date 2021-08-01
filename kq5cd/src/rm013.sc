;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
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
	rm013 0
)

(local
	local0
	local1
	local2
	[local3 18] = [10 113 240 113 260 120 260 128 209 128 170 134 107 134 107 128 10 129]
	[local21 8] = [0 161 103 176 108 189 0 189]
	[local29 8] = [319 189 173 189 166 163 319 163]
	[local37 8] = [0 0 319 0 319 102 0 102]
	[local45 8] = [127 117 143 117 143 125 127 125]
	local53
	gEgoIllegalBits
	local55
	[local56 9] = [1003 10 62 4 11 25 23 31 31]
	[local65 9] = [1013 170 63 4 9 26 23 27 26]
	[local74 9] = [1000 55 70 4 11 24 19 23 30]
	[local83 6] = [1 3049 0 960]
)
(instance rm013 of KQ5Room
	(properties
		picture 13
		east 9
		south 12
		west 14
	)
	
	(method (init)
		(super init:)
		(= global320 69)
		(= global321 137)
		(= global325 3053)
		(= gEgoIllegalBits (ego illegalBits?))
		(ego illegalBits: -32768 ignoreHorizon: 1)
		(if (!= (theMusic number?) 24)
			(theMusic number: 24 loop: -1 play:)
		)
		(if (== ((inventory at: 6) owner?) 200)
			(if (== ((inventory at: 34) owner?) 13)
				(tambourine init:)
				(glint init: setScript: glimmer)
				(self addObstacle: poly5)
			)
			(self setFeatures: grassRoom)
		else
			(theMusic2 number: 803 loop: -1 vol: 127 playBed:)
			(Load rsVIEW 344)
			(chair init: stopUpd:)
			(body setCycle: Fwd cycleSpeed: 3 init:)
			(oxHead cycleSpeed: 2 init:)
			(if (== (theGame detailLevel:) 3)
				(oxTail init: setScript: oxScript)
			else
				(oxTail init: stopUpd:)
			)
			(coals setCycle: Fwd init:)
			(barrel init:)
			(shovel init:)
			(camp init:)
			(wagon init:)
			(ox init: stopUpd:)
			(addToPics add: barrel shovel camp wagon doit:)
			(self
				setFeatures: camp barrel shovel wagon
				addObstacle: poly1
			)
		)
		(poly1 points: @local3 size: 9)
		(poly2 points: @local21 size: 4)
		(poly3 points: @local29 size: 4)
		(poly4 points: @local37 size: 4)
		(poly5 points: @local45 size: 4)
		(self addObstacle: poly2 poly3 poly4)
		(self setRegions: 202)
		(ego normal: 1 view: 0 setPri: -1 init:)
		(switch prevRoomNum
			(west
				(ego posn: -20 129 loop: 0)
				(HandsOff)
				(self setScript: relievedScript)
			)
			(east
				(ego posn: 315 125)
				(self setScript: (ScriptID 202 1))
			)
			(south
				(ego posn: 133 180)
				(self setScript: (ScriptID 202 1))
			)
			(else 
				(ego posn: 187 127)
				(HandsOff)
				(self setScript: egoWalk)
			)
		)
	)
	
	(method (doit)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= local0 (self edgeToRoom: (ego edgeHit?)))
				)
				(if (== local0 (curRoom west?))
					(self setScript: goingToDesert)
				else
					((ScriptID 202 2) register: (ego edgeHit?))
					(self setScript: (ScriptID 202 2))
				)
			)
		)
	)
	
	(method (dispose)
		(ego illegalBits: gEgoIllegalBits)
		(theMusic fade:)
		(theMusic2 fade:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance glimmer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(glint setCycle: End)
				(-- state)
				(= seconds 10)
			)
		)
	)
)

(instance warnScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(proc762_1 @local74 3054)
				(client setScript: 0)
			)
		)
	)
)

(instance relievedScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(ego setMotion: MoveTo 4 129 self)
			)
			(2
				(proc762_0 @local56 @local74 @local83 self)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance standScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(body dispose:)
				(chair view: 346 loop: 4 cel: 0 setCycle: End self)
			)
			(1
				(chair view: 346 loop: 0 cel: 1 stopUpd:)
				(gypsy
					setCycle: Walk
					setLoop: 0
					illegalBits: 0
					init:
					setMotion: MoveTo 187 128 self
				)
			)
			(2
				(gypsy setLoop: 2 cel: 0 setCycle: End self)
			)
			(3
				(cond 
					((not local2)
						(++ local2)
						(if (ego has: 27)
							(proc762_1 @local65 9040 self)
						else
							(proc762_1 @local65 9036 self)
						)
					)
					((ego has: 27) (proc762_1 @local65 9040 self))
					(else (proc762_1 @local65 9037 self))
				)
			)
			(4
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance sitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(gypsy
					setCycle: Walk
					setLoop: 1
					illegalBits: 0
					setMotion: MoveTo 214 127 self
				)
			)
			(1
				(gypsy dispose:)
				(chair
					view: 346
					loop: 4
					cel: (- (NumCels chair) -1)
					setCycle: Beg self
				)
			)
			(2
				(body setCycle: Fwd cycleSpeed: 3 init:)
				(chair
					loop: 0
					cel: 0
					setPri: (- (body priority?) 1)
					forceUpd:
				)
				(client setScript: 0)
			)
		)
	)
)

(instance giveCoinSit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(body dispose:)
				(chair view: 346 loop: 4 cel: 0 setCycle: End self)
				(ego setMotion: PolyPath 200 132 self)
			)
			(1)
			(2
				((ego head?) hide:)
				(ego
					normal: 0
					view: 24
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(chair view: 346 loop: 0 cel: 1 setPri: -1 stopUpd:)
				(gypsy
					init:
					cel: 0
					cycleSpeed: 2
					x: 224
					setLoop: 3
					setPri: -1
					illegalBits: 0
					setCycle: End self
				)
			)
			(4
				(gypsy setLoop: 4 cel: 0 setCycle: End self)
			)
			(5
				(if local55
					(proc762_1 @local65 9038)
				else
					(proc762_1 @local65 9039)
				)
				(ego
					normal: 1
					view: 0
					setCycle: Walk
					cycleSpeed: 0
					ignoreControl: 2
					setMotion: MoveTo 187 127 self
				)
				((ego head?) show:)
			)
			(6 (curRoom newRoom: 680))
		)
	)
)

(instance giveCoinStand of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 187 137 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 24
					loop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(2
				(gypsy
					cel: 0
					setLoop: 3
					illegalBits: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(3
				(gypsy setLoop: 4 cel: 0 setCycle: End self)
			)
			(4
				(if local55
					(proc762_1 @local65 9038)
				else
					(proc762_1 @local65 9039)
				)
				(gypsy
					setCycle: Walk
					setLoop: 1
					illegalBits: 0
					cycleSpeed: 0
					setMotion: MoveTo 200 127 self
				)
			)
			(5
				(ego
					normal: 1
					view: 0
					setCycle: Walk
					cycleSpeed: 0
					ignoreControl: 2
					setMotion: MoveTo 187 127 self
				)
				((ego head?) show:)
			)
			(6 (curRoom newRoom: 680))
		)
	)
)

(instance oxScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (< (Random 1 100) 10) (oxTail setCycle: Beg))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(oxHead loop: 1 setCycle: End)
				(= seconds 5)
			)
			(1 (oxHead setCycle: Beg self))
			(2
				(oxHead loop: 5 cel: 0 setCycle: CT 1 1)
				(theAudio number: 8880 loop: 1 play:)
				(= cycles 30)
			)
			(3
				(oxHead setCycle: Fwd)
				(= state -1)
				(= seconds (Random 5 20))
			)
		)
	)
)

(instance egoWalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo (ego x?) 146 self)
			)
			(1
				(HandsOn)
				(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
				(client setScript: 0)
			)
		)
	)
)

(instance getTambourine of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 146 122 self)
			)
			(1
				((ego head?) hide:)
				(ego normal: 0 view: 56 loop: 3 cel: 0 setCycle: End self)
			)
			(2
				(tambourine dispose:)
				((curRoom obstacles?) delete: poly5)
				(glint setScript: 0 dispose:)
				(SolvePuzzle 2)
				(ego get: 34 setCycle: Beg self)
			)
			(3
				(SpeakAudio 291)
				(ego
					normal: 1
					view: 0
					setCycle: Walk
					cycleSpeed: 0
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

(instance wagon of RPicView
	(properties
		x 120
		y 125
		view 342
		signal $4000
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
					(SpeakAudio 283)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance door of Prop
	(properties
		view 342
		signal $4000
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
					(SpeakAudio 284)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(ego setMotion: PolyPath 186 120)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance oxHead of Prop
	(properties
		x 89
		y 112
		view 342
		loop 1
		priority 8
		signal $4010
		detailLevel 3
	)
	
	(method (doVerb)
		(ox doVerb: &rest)
	)
)

(instance oxTail of Prop
	(properties
		x 15
		y 145
		z 28
		view 342
		loop 2
		priority 8
		signal $4010
		cycleSpeed 2
		detailLevel 3
	)
	
	(method (doVerb)
		(ox doVerb: &rest)
	)
)

(instance ox of Prop
	(properties
		x 20
		y 83
		view 342
		loop 7
		detailLevel 3
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2 (SpeakAudio 285))
			(3 (SpeakAudio 292))
			(5 (SpeakAudio 295))
			(4
				(switch theItem
					(28 1)
					(else  (SpeakAudio 297))
				)
			)
		)
	)
)

(instance body of Prop
	(properties
		x 232
		y 127
		z 17
		view 346
		loop 1
		signal $4000
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not (Btst 51))
				(< (ego y?) 188)
				(not (curRoom script?))
			)
			(Bset 51)
			(curRoom setScript: warnScript)
		)
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
					(SpeakAudio 286)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 293)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(11
							(if (ego has: 27)
								(proc762_1 @local65 9040)
							else
								(ego put: 11 13)
								(SolvePuzzle 3)
								(HandsOff)
								(curRoom setScript: giveCoinSit)
							)
							(event claimed: 1)
						)
						(3
							(if (ego has: 27)
								(proc762_1 @local65 9040)
							else
								(= local55 1)
								(ego put: 3 13)
								(HandsOff)
								(curRoom setScript: giveCoinSit)
							)
							(event claimed: 1)
						)
						(4
							(proc762_1 @local65 9041)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
					)
				)
				(JOY_DOWN
					(SpeakAudio 296)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance chair of Prop
	(properties
		x 235
		y 127
		view 346
		signal $4000
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(ego inRect: 170 120 217 145)
				(not (cast contains: gypsy))
				(not (curRoom script?))
			)
			(HandsOff)
			(ego setMotion: 0)
			(curRoom setScript: standScript)
		)
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
				(JOY_DOWNRIGHT
					(if (not (cast contains: gypsy))
						(switch (inventory indexOf: (theIconBar curInvIcon?))
							(11
								(if (ego has: 27)
									(proc762_1 @local65 9040)
								else
									(ego put: 11 13)
									(SolvePuzzle 3)
									(HandsOff)
									(curRoom setScript: giveCoinSit)
								)
								(event claimed: 1)
							)
							(3
								(if (ego has: 27)
									(proc762_1 @local65 9040)
								else
									(= local55 1)
									(ego put: 3 13)
									(HandsOff)
									(curRoom setScript: giveCoinSit)
								)
								(event claimed: 1)
							)
							(28 (event claimed: 0))
							(4
								(proc762_1 @local65 9041)
								(event claimed: 1)
							)
							(else 
								(proc762_1 @local65 9042)
								(event claimed: 1)
							)
						)
					)
				)
				(JOY_DOWN
					(if
					(and (not (cast contains: gypsy)) (not local53))
						(SpeakAudio 296)
						(++ local53)
						(event claimed: 1)
					)
				)
				(JOY_UPRIGHT
					(if (not (cast contains: gypsy))
						(SpeakAudio 286)
						(event claimed: 1)
					)
				)
				(JOY_RIGHT
					(if (not (cast contains: gypsy))
						(SpeakAudio 293)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance gypsy of Actor
	(properties
		x 214
		y 127
		view 344
		cel 7
		signal $4000
		detailLevel 3
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(> (ego distanceTo: self) 50)
				(not (curRoom script?))
			)
			(curRoom setScript: sitDown)
		)
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
					(SpeakAudio 287)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 293)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(11
							(if (ego has: 27)
								(proc762_1 @local65 9040)
							else
								(SolvePuzzle 3)
								(ego put: 11 13)
								(HandsOff)
								(curRoom setScript: giveCoinStand)
							)
							(event claimed: 1)
						)
						(3
							(if (ego has: 27)
								(proc762_1 @local65 9040)
							else
								(= local55 1)
								(ego put: 3 13)
								(HandsOff)
								(curRoom setScript: giveCoinStand)
							)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(4
							(proc762_1 @local65 9041)
							(event claimed: 1)
						)
						(else 
							(proc762_1 @local65 9042)
							(event claimed: 1)
						)
					)
				)
				(JOY_DOWN
					(SpeakAudio 296)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance barrel of RPicView
	(properties
		x 121
		y 128
		view 342
		loop 3
		signal $4000
	)
)

(instance shovel of RPicView
	(properties
		x 134
		y 129
		view 342
		loop 3
		cel 1
		signal $4000
	)
)

(instance coals of Prop
	(properties
		x 152
		y 132
		z 7
		view 342
		loop 4
		cel 1
		signal $4000
		cycleSpeed 2
		detailLevel 3
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
					(SpeakAudio 288)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 294)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance camp of RPicView
	(properties
		x 152
		y 132
		view 342
		loop 3
		cel 2
		signal $4000
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
					(SpeakAudio 288)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 294)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance grassRoom of RFeature
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
					(SpeakAudio 289)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance glint of Prop
	(properties
		x 135
		y 120
		view 342
		loop 6
		signal $4011
		cycleSpeed 2
	)
)

(instance tambourine of Prop
	(properties
		x 135
		y 127
		view 342
		loop 3
		cel 4
		priority 3
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
					(SpeakAudio 290)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getTambourine)
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
		type $0002
	)
)

(instance goingToDesert of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego loop: 1)
				(RedrawCast)
				(proc762_1 @local74 3048 self)
			)
			(1
				(HandsOn)
				(curRoom newRoom: local0)
			)
		)
	)
)
