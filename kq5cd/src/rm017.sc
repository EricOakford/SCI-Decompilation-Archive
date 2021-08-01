;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Chase)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm017 0
)

(local
	local0
	local1
	local2
	[local3 8] = [165 111 188 111 188 116 164 116]
	[local11 36] = [0 189 0 7 319 7 319 189 192 189 193 152 243 151 241 137 220 136 207 129 220 120 208 108 150 103 129 112 93 123 95 135 129 151 139 189]
	[local47 8] = [130 112 154 112 154 124 94 123]
)
(instance rm017 of KQ5Room
	(properties
		picture 17
		south 216
	)
	
	(method (init)
		(super init:)
		(self setFeatures: tent)
		(theMusic number: 48 loop: -1 play:)
		(LoadMany 128 392 394)
		(if (Btst 32)
			(if (== ((inventory at: 7) owner?) 17)
				(staff init: stopUpd:)
				(glint init: setScript: glimmer)
			)
			(zzzz init: setScript: snore)
			(bandit init: cycleSpeed: 5 setCycle: Fwd)
			(self addObstacle: poly3)
		)
		(ego
			view: 0
			illegalBits: -32768
			setStep: 3 2
			posn: 174 173
			init:
		)
		(poly1 points: @local3 size: 4)
		(poly2 points: @local11 size: 18)
		(poly3 points: @local47 size: 4)
		(self setScript: walkInScript)
		(self addObstacle: poly1 poly2)
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
			(and (& (= temp1 (ego onControl: 0)) $0002) (Btst 32)) (HandsOff) (self setScript: wakeUp))
			((& temp1 $4000) (curRoom newRoom: 216))
			((and (< local2 750) (Btst 32)) (++ local2))
			((and (not (== howFast 0)) (== local2 750)) (HandsOff) (self setScript: wakeUp))
		)
	)
	
	(method (dispose)
		(DisposeScript 972)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
	
	(method (newRoom n)
		(theMusic fade:)
		(super newRoom: n)
	)
)

(instance walkInScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 180 148 self)
			)
			(1
				(HandsOn)
				(if (and (Btst 32) (Btst 92))
					(self setScript: wakeUp)
				else
					(client setScript: 0)
				)
			)
		)
	)
)

(instance wakeUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: 0)
				(Face ego bandit 5)
				(theAudio stop:)
				(zzzz dispose:)
				(theMusic number: 129 loop: 1 vol: 127 play:)
				(bandit
					loop: 1
					cycleSpeed: 0
					cel: 0
					ignoreActors: 1
					setCycle: End self
				)
				(ego illegalBits: 0 ignoreActors: 1)
			)
			(1
				(SpeakAudio 5900)
				(if (> (ego distanceTo: bandit) 25)
					(bandit
						view: 392
						setStep: 4 4
						setLoop: -1
						setCycle: Walk
						illegalBits: 0
						setMotion: Chase ego 25 self
					)
				else
					(= cycles 1)
				)
			)
			(2
				(bandit view: 394)
				(Face bandit ego 5)
				(= cycles 2)
			)
			(3
				(bandit view: 394 cel: 0 cycleSpeed: 2 setCycle: End self)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 394
					setLoop: (+ (bandit loop?) 4)
					cel: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(4 (SpeakAudio 7772 0 1))
			(5 (= seconds 3))
			(6
				(= deathMessage 318)
				(EgoDead)
			)
		)
	)
)

(instance getStaff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: PolyPath 143 109 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 396
					loop: 0
					cel: 0
					setAvoider: 0
					cycleSpeed: 1
					setCycle: CT 1 1 self
				)
			)
			(2
				(ego get: 7)
				(glint setScript: 0 dispose:)
				(ego setCycle: End self)
			)
			(3
				(SolvePuzzle 2)
				(staff dispose:)
				(SpeakAudio 319)
				(ego
					normal: 1
					view: 0
					setCycle: KQ5SyncWalk
					cycleSpeed: 0
					loop: -1
					cel: 7
				)
				((ego head?) show:)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance glimmer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (glint setCycle: End self))
			(1
				(glint stopUpd:)
				(= seconds 10)
			)
			(2 (self init:))
		)
	)
)

(instance snore of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theAudio number: 7060 loop: 1 play: self)
				(zzzz setCycle: End self)
			)
			(1)
			(2 (= seconds 2))
			(3 (self init:))
		)
	)
)

(instance glint of Prop
	(properties
		x 136
		y 68
		view 390
		loop 3
		cycleSpeed 2
	)
)

(instance staff of View
	(properties
		x 140
		y 105
		view 390
		loop 2
		signal $4001
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
					(SpeakAudio 323)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getStaff)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance zzzz of Prop
	(properties
		x 100
		y 105
		view 390
		loop 4
		cycleSpeed 3
		detailLevel 3
	)
	
	(method (setScript)
		(if (== (theGame detailLevel:) 3)
			(super setScript: &rest)
		)
	)
)

(instance bandit of Actor
	(properties
		x 118
		y 120
		view 390
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
					(SpeakAudio 324)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (not local1)
						(++ local1)
						(SpeakAudio 320)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance tent of RFeature
	(properties
		nsTop 39
		nsLeft 65
		nsBottom 151
		nsRight 253
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
					(if (cast contains: bandit)
						(SpeakAudio 321)
					else
						(SpeakAudio 322)
					)
					(event claimed: 1)
				)
				(JOY_DOWN
					(if (cast contains: bandit)
						(SpeakAudio 9104)
						(curRoom setScript: wakeUp)
						(event claimed: 1)
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
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)
