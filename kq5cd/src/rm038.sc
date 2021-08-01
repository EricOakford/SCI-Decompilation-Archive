;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include sci.sh)
(use Main)
(use Intrface)
(use Waters)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm038 0
)

(local
	[local0 16] = [192 147 281 115 288 32 71 137 9 83 140 33 41 32 11 110]
	local16
	local17
	newActor
	newActor_2
	newActor_3
	[theX 3] = [161 54 80]
	[local24 26] = [194 189 234 180 145 180 149 175 202 175 200 167 138 168 137 164 188 159 191 150 150 150 319 0 319 189]
	[local50 16] = [0 189 0 0 149 149 128 153 91 155 110 173 136 175 133 189]
	local66
	local67
)
(instance rm038 of KQ5Room
	(properties
		picture 38
		south 36
	)
	
	(method (init)
		(super init:)
		(self setFeatures: cave)
		(features eachElementDo: #init)
		(if (> (theGame detailLevel:) 0)
			(theAudio doNotStop: 1 number: 7054 loop: -1 play:)
			(water init:)
		else
			(theAudio doNotStop: 1 number: 7054 loop: -1 play:)
			(water init: stopUpd:)
		)
		(theMusic number: 112 loop: -1 vol: 127 play:)
		(ego view: 10 posn: 160 186 init:)
		(if (> (theGame detailLevel:) 0)
			(glint cycleSpeed: 1 init: setScript: sparkle)
		else
			(glint init: stopUpd:)
		)
		(if (not (ego has: 21)) (crystal init: stopUpd:))
		(if (== (theGame detailLevel:) 3) (waterLeft init:))
		((= newActor (Actor new:))
			view: 561
			posn: 295 -2
			yStep: 4
			setLoop: 2
			setPri: 1
			ignoreActors: 1
			ignoreHorizon: 1
			illegalBits: 0
			setMotion: MoveTo 295 111
			init:
		)
		((= newActor_2 (Actor new:))
			view: 561
			yStep: 4
			setLoop: 2
			setPri: 1
			ignoreActors: 1
			ignoreHorizon: 1
			illegalBits: 0
			init:
			hide:
		)
		((= newActor_3 (Actor new:))
			view: 561
			yStep: 4
			setLoop: 2
			setPri: 1
			ignoreActors: 1
			ignoreHorizon: 1
			illegalBits: 0
			setMotion: MoveTo 295 111
			init:
			hide:
		)
		(poly1 points: @local24 size: 13)
		(poly2 points: @local50 size: 8)
		(self addObstacle: poly1 poly2)
	)
	
	(method (doit &tmp temp0)
		(if
			(and
				(not (newActor mover?))
				(== (theGame detailLevel:) 3)
			)
			(newActor x: 295 y: 0 setMotion: MoveTo 295 111)
		)
		(if
			(and
				(> (newActor y?) 34)
				(not (newActor_2 mover?))
				(== (theGame detailLevel:) 3)
			)
			(newActor_2 x: 295 y: 0 setMotion: MoveTo 295 111 show:)
		)
		(if
			(and
				(> (newActor y?) 70)
				(not (newActor_3 mover?))
				(== (theGame detailLevel:) 3)
			)
			(newActor_3 x: 295 y: 0 setMotion: MoveTo 295 111 show:)
		)
		(cond 
			(script (script doit:))
			(
				(and
					(ego edgeHit?)
					(= temp0 (self edgeToRoom: (ego edgeHit?)))
				)
				(curRoom newRoom: temp0)
			)
		)
	)
	
	(method (dispose)
		(theMusic fade:)
		(DisposeScript 401)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance sparkle of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (== (theMusic prevSignal?) 10)
			(theMusic prevSignal: 0)
			(self init:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 (Random 0 7))
				(client
					show:
					x: [local0 (* temp0 2)]
					y: [local0 (+ (* temp0 2) 1)]
					setCycle: End self
				)
			)
			(1 (client hide:))
		)
	)
)

(instance hammerCrystal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 137 152 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 562
					setLoop: 0
					cel: 0
					cycleSpeed: 2
					setCycle: CT 3 1 self
				)
			)
			(2
				(if local66 (ego setCycle: End self) else (= cycles 1))
			)
			(3
				(if local66
					(ego setLoop: 1 cycleSpeed: 1 setCycle: CT 4 1 self)
					(theAudio number: 8113 loop: 1 play:)
				else
					(ego setLoop: 5 cycleSpeed: 1 setCycle: Fwd)
				)
				(= seconds 5)
			)
			(4
				(if local66
					(ego setCycle: End self)
				else
					(ego setLoop: 0 cel: 3 setCycle: Beg self)
				)
			)
			(5
				(if local66
					(theAudio number: 8113 loop: 1 play:)
					(ego cel: (- (ego cel?) 1))
					(crystal setCycle: End self)
				else
					(ego
						view: 10
						normal: 1
						setLoop: -1
						cycleSpeed: 0
						illegalBits: -32768
						setCycle: KQ5SyncWalk
					)
					((ego head?) show:)
					(SpeakAudio 480)
					(HandsOn)
					(client setScript: 0)
				)
			)
			(6
				(crystal setStep: 15 setMotion: MoveTo 152 149 self)
				(theAudio number: 8085 loop: 1 play:)
			)
			(7 (ego setCycle: Beg self))
			(8
				(ego
					setLoop: 0
					cel: (- (NumCels ego) 1)
					setCycle: Beg self
				)
			)
			(9
				(ego
					view: 10
					normal: 1
					setLoop: -1
					cycleSpeed: 0
					illegalBits: 0
					setCycle: KQ5SyncWalk
					setMotion: MoveTo 148 149 self
				)
				((ego head?) show:)
			)
			(10
				((ego head?) hide:)
				(ego
					view: 562
					setLoop: 3
					cycleSpeed: 3
					normal: 0
					setCycle: End self
				)
			)
			(11
				(SpeakAudio 483)
				(SolvePuzzle 4)
				(crystal dispose:)
				(ego get: 21 setCycle: Beg self)
			)
			(12
				(theAudio doNotStop: 1 number: 7054 loop: -1 play:)
				(ego
					view: 10
					normal: 1
					setLoop: -1
					cycleSpeed: 0
					illegalBits: -32768
					setCycle: KQ5SyncWalk
				)
				((ego head?) show:)
				(= local66 0)
				(= cycles 3)
			)
			(13
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance glint of Prop
	(properties
		view 561
		loop 3
		priority 15
		signal $4010
		detailLevel 3
	)
)

(instance cave of RFeature
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
					(SpeakAudio 479)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (and (not (ego has: 21)) (not local16))
						(++ local16)
						(SpeakAudio 481)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance crystal of Actor
	(properties
		x 153
		y 138
		view 562
		loop 2
		signal $4000
		cycleSpeed 2
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
					(SpeakAudio 482)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local17)
						(++ local17)
						(curRoom setScript: hammerCrystal)
						(event claimed: 1)
					)
				)
				(JOY_DOWNRIGHT
					(event claimed: 1)
					(switch (inventory indexOf: (theIconBar curInvIcon?))
						(22
							(= local66 1)
							(curRoom setScript: hammerCrystal)
						)
						(28 (event claimed: 0))
						(else  (SpeakAudio 484))
					)
				)
			)
		)
	)
)

(instance waterLeft of Actor
	(properties
		x 24
		y 156
		view 561
		loop 1
		priority 1
		signal $6810
		cycleSpeed 1
		detailLevel 3
	)
	
	(method (doit)
		(super doit:)
		(if (== y 156)
			(self
				y: (Random 128 140)
				x: (Random 24 28)
				setMotion: MoveTo (self x?) 156
			)
		)
	)
)

(instance water of Waters
	(properties
		view 561
		priority 1
		signal $6030
		cycleSpeed 1
		detailLevel 3
		pos 0
		wfCels 6
	)
	
	(method (getLoop)
		(= x [theX pos])
		(= y [theX (++ pos)])
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
