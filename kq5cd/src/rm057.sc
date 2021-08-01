;;; Sierra Script 1.0 - (do not remove this comment)
(script# 57)
(include sci.sh)
(use Main)
(use Intrface)
(use castle)
(use CodeCue)
(use n770)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use Block)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm057 0
)

(local
	local0
	local1
	local2
	gEgoView
	gEgoCycleSpeed
	local5
	local6
	[local7 36] = [0 189 0 0 319 0 319 136 265 146 212 145 212 150 143 155 120 150 111 142 171 141 172 137 92 140 87 150 77 160 3 179 103 178 104 189]
	[local43 22] = [319 189 182 189 165 186 158 179 223 175 265 179 302 170 278 160 267 149 271 147 319 147]
	[local65 40] = [0 189 0 0 319 0 319 136 265 146 212 145 212 150 143 155 120 150 111 142 171 141 172 137 92 140 85 147 105 148 99 163 77 160 3 179 103 178 104 189]
	[local105 9] = [1012 35 76 4 9 31 26 29 32]
	[local114 9] = [1003 100 80 4 11 25 23 31 31]
	[local123 12] = [0 1055 1 1056 0 1057 1 1058 0 1059]
	[local135 18] = [0 1060 1 1061 0 1062 1 1063 0 1064 1 1065 0 1066 1 1067]
)
(instance rm057 of KQ5Room
	(properties
		picture 57
		south 56
	)
	
	(method (init)
		(self setRegions: 550)
		(Load rsVIEW 690)
		(Load rsVIEW 686)
		(Load rsVIEW 688)
		(= global357 249)
		(= global358 147)
		(= global355 46)
		(= global356 163)
		(ego init: yStep: 3)
		((ego head?)
			x: (ego x?)
			y: (ego y?)
			z: (CelHigh (ego view?) (ego loop?) (ego cel?))
			moveHead: 1
			show:
		)
		(princess
			setPri: 11
			setLoop: 0
			setCycle: Fwd
			cycleSpeed: 3
			posn: 70 152
			init:
			ignoreActors: 1
		)
		(if (Btst 91) (= local5 1))
		(cond 
			((== prevRoomNum south) (curRoom setScript: enterSouth))
			((Btst 111)
				(ego put: 25 57 posn: 112 153 setLoop: 1 setCel: 1)
				(princess
					posn: 80 151
					setLoop: 5
					setCel: 5
					cycleSpeed: 2
					setCycle: 0
				)
				((ego head?) setCel: -1 setLoop: 4 moveHead: 1)
				(Bclr 64)
				(theMusic number: 101 loop: -1 play:)
				(proc550_18)
				(Bclr 111)
			)
			((and (== prevRoomNum 683) (not (Btst 111)))
				(self style: 3)
				((ego head?) setCel: -1 setLoop: 4 moveHead: 1)
				(ego
					put: 25 57
					posn: 112 153
					setLoop: 7
					normal: 1
					setCel: 1
				)
				(princess setLoop: 5 setCel: 255 posn: 80 151)
				(proc550_18)
				((ego head?) setCel: -1 setLoop: 4 moveHead: 1)
				(Bclr 64)
				(theMusic number: 101 loop: -1 play:)
			)
			(else (curRoom setScript: enterEast))
		)
		(super init:)
		(fireplace
			cycleSpeed: 8
			setCycle: (if (> (theGame detailLevel:) 1) Fwd else 0)
			init:
		)
		(poly1 points: @local7 size: 18)
		(poly2 points: @local43 size: 11)
		(poly3 points: @local65 size: 20)
		(if (== ((inventory at: 25) owner?) 57)
			(self
				setFeatures: doorWay1 doorWay2 interior
				addObstacle: poly2 poly3
			)
		else
			(self
				setFeatures: doorWay1 doorWay2 interior
				addObstacle: poly1 poly2
			)
		)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			(
			(and (== global331 3) (ego inRect: 59 163 185 193))
				(= global349 238)
				(= global350 153)
				(= global351 225)
				(= global354 135)
				((ScriptID 550 7) init: setScript: (ScriptID 550 12))
			)
			(
				(and
					(not (== (princess loop?) 1))
					(not (princess script?))
					(== global331 4)
				)
				(princess setScript: goBack)
			)
			(
				(and
					(< (ego y?) 175)
					(not (== ((inventory at: 25) owner?) 57))
					(< (ego distanceTo: princess) 60)
					(not (== (princess loop?) 1))
					(not (princess script?))
				)
				(if (not local0)
					(princess setScript: scaredTalk)
				else
					(princess loop: 1 cel: 0 setCycle: End)
				)
			)
			(
				(and
					(== (princess loop?) 1)
					(not (princess script?))
					(> (ego distanceTo: princess) 80)
				)
				(princess setScript: scrubFloor)
			)
			((ego inRect: 256 140 281 148)
				(proc550_17)
				(ego illegalBits: 0 setScript: 0)
				(princess setScript: 0)
				(curRoom setScript: exitEast)
			)
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(proc550_17)
				(ego illegalBits: 0 setScript: 0)
				(princess setScript: 0)
				(curRoom setScript: exitSouth)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance giveLocketScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego put: 25 57)
				(proc550_17)
				(SolvePuzzle 4)
				(Bset 64)
				(= gEgoView (ego view?))
				(= gEgoCycleSpeed (ego cycleSpeed?))
				(ego illegalBits: 0 setMotion: PolyPath 108 153 self)
			)
			(1 (= cycles 2))
			(2
				((ScriptID 763) doit:)
				(= cycles 1)
			)
			(3 (curRoom newRoom: 683))
		)
	)
)

(instance scrubFloor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(princess setCycle: Beg self)
			)
			(1
				(princess setLoop: 0 setCycle: Fwd)
				(= cycles 1)
			)
			(2 (client setScript: 0 cue:))
		)
	)
)

(instance goBack of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(princess setLoop: 1 cel: 0 setCycle: End)
			)
		)
	)
)

(instance scaredTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc550_17)
				(ego setMotion: 0)
				(theMusic number: 102 loop: -1 playBed: self)
				(Face ego princess 5)
				(if (!= (princess loop?) 1)
					(princess setLoop: 1 cel: 0 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(1
				(proc770_0 @local114 ego)
				(proc770_0 @local105 princess)
				(proc762_0 @local105 @local114 @local123 self)
			)
			(2
				(theMusic fade: 0 100 1 1)
				(cls)
				(theMusic number: 101 loop: -1 play:)
				(proc550_18)
				(= local0 1)
				(client setScript: 0)
			)
		)
	)
)

(instance friendTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc550_17)
				(ego setMotion: 0)
				(Face ego princess 5)
				(= cycles 2)
			)
			(1
				(proc770_0 @local114 ego)
				(proc770_0 @local105 princess)
				(proc762_0 @local105 @local114 @local135 self)
			)
			(2
				(cls)
				(proc550_18)
				(if (!= (princess loop?) 0)
					(princess
						setLoop: 8
						setCel: 0
						ignoreActors: 1
						cycleSpeed: 3
						setCycle: End self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(princess setLoop: 0 posn: 70 152 setScript: scrubFloor)
			)
		)
	)
)

(instance enterEast of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(proc550_17)
				(theMusic number: 101 loop: -1 play:)
				(ego
					setPri: 10
					posn: 309 141
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 244 151 self
					setLoop: 1
				)
			)
			(1
				(proc550_18)
				(client setScript: 0)
			)
		)
	)
)

(instance fireplace of Prop
	(properties
		x 134
		y 131
		view 686
		cycleSpeed 8
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
					(proc0_29 602)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local2)
						(++ local2)
						(proc0_29 609)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance interior of RFeature
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
					(proc0_29 603)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance enterSouth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(proc550_17)
				(theMusic number: 101 loop: -1 play:)
				(ego loop: 3 posn: 138 243 setMotion: MoveTo 138 186 self)
			)
			(1
				(proc550_18)
				(client setScript: 0)
			)
		)
	)
)

(instance princess of Actor
	(properties
		view 688
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
					(if (== ((inventory at: 25) owner?) 57)
						(proc0_29 604)
					else
						(switch (princess loop?)
							(0 (proc0_29 605))
							(1 (proc0_29 606))
						)
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (== ((inventory at: 25) owner?) 57)
						(proc0_29 610)
					else
						(switch (princess loop?)
							(0 (proc0_29 611))
							(1 (proc0_29 612))
						)
					)
					(event claimed: 1)
				)
				(JOY_DOWNRIGHT
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(25
							(curRoom setScript: giveLocketScript)
							(event claimed: 1)
						)
						(28 (event claimed: 0))
						(else 
							(if (== ((inventory at: 25) owner?) 57)
								(proc0_29 613)
							else
								(curRoom setScript: noThanks)
							)
							(event claimed: 1)
						)
					)
				)
				(JOY_DOWN
					(if (not (princess script?))
						(if (== ((inventory at: 25) owner?) 57)
							(switch (++ local5)
								(1
									(event claimed: 1)
									(Bset 91)
									(princess setScript: friendTalk)
								)
								(2
									(proc0_29 614)
									(event claimed: 1)
								)
								(else  (event claimed: 0))
							)
						else
							(++ local5)
							(if (and local0 (== local5 1)) (= local5 2))
							(switch local5
								(1
									(event claimed: 1)
									(ego setMotion: 0)
									(princess setScript: scaredTalk)
								)
								(else 
									(proc0_29 615)
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

(instance blockOne of Blk
	(properties
		top 137
		bottom 159
		right 80
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

(instance exitSouth of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 138 243 self)
			)
			(1
				(theMusic fade:)
				(curRoom newRoom: 56)
			)
		)
	)
)

(instance exitEast of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(proc550_17)
				(theMusic fade:)
				(ego illegalBits: 0 setMotion: MoveTo 309 141 self)
			)
			(1 (curRoom newRoom: 58))
		)
	)
)

(instance egoHeadMove of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ego head?) loop: (Random 4 6))
				(-- state)
				(= cycles 3)
			)
		)
	)
)

(instance doorWay1 of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $4000))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(proc0_29 607)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance doorWay2 of RFeature
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
					(proc0_29 608)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance noThanks of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(proc550_17)
				(if (< (ego x?) 82)
					(ego setMotion: PolyPath 114 161 self)
				else
					(self cue:)
				)
			)
			(1
				(Face ego princess 5)
				(if (!= (princess loop?) 1)
					(princess setLoop: 1 cel: 0 setCycle: End self)
				else
					(= cycles 1)
				)
			)
			(2
				(proc762_1 @local105 1072 self)
			)
			(3
				(if (> (ego distanceTo: princess) 80)
					(princess setScript: scrubFloor)
				)
				(proc550_18)
				(client setScript: 0)
			)
		)
	)
)
