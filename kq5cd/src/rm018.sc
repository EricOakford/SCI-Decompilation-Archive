;;; Sierra Script 1.0 - (do not remove this comment)
(script# 18)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm018 0
)

(local
	local0
	[local1 19] = [302 67 288 82 231 148 152 121 119 171 117 126 98 105 46 118 7 119]
	[local20 52] = [3 69 3 130 12 135 39 128 50 116 68 119 76 128 86 115 78 106 93 100 116 104 154 96 164 104 257 81 193 51 152 62 112 62 108 61 76 45 79 0 319 0 319 189 0 189 0 36 38 36 38 64]
)
(instance rm018 of KQ5Room
	(properties
		picture 18
	)
	
	(method (init)
		(super init:)
		(HandsOn)
		(self setFeatures: treasure)
		(glint cycleSpeed: 1 setScript: sparkle init:)
		(ego
			view: 0
			illegalBits: -32768
			posn: 76 71
			cycleSpeed: 0
			setStep: 3 2
			normal: 1
			init:
		)
		(bottle init: stopUpd:)
		(coin init: setScript: gleem)
		(door init: setScript: closeDoor stopUpd:)
		(poly1 points: @local20 size: 26)
		(self addObstacle: poly1)
		(theMusic number: 45 loop: -1 play:)
	)
	
	(method (doit &tmp temp0)
		(cond 
			(script (script doit:))
			((& (ego onControl: 0) $4000) (curRoom newRoom: 214))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance getCoin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(ego setMotion: PolyPath 130 69 self)
			)
			(1
				(cls)
				(SpeakAudio 328)
				((ego head?) hide:)
				(ego normal: 0 view: 56 loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(SolvePuzzle 2)
				(coin dispose:)
				(ego
					normal: 1
					get: 11
					view: 0
					setCycle: KQ5SyncWalk
					loop: 7
					cel: 1
				)
				((ego head?) show:)
				(= cycles 3)
			)
			(3
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance sparkle of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= temp0 (Random 0 8))
				(client
					loop: (Random 1 7)
					x: [local1 (* temp0 2)]
					y: [local1 (+ (* temp0 2) 1)]
					setCycle: EndLoop self
				)
			)
			(1
				(= state -1)
				(= seconds (Random 3 8))
			)
		)
	)
)

(instance gleem of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(client stopUpd:)
				(= state -1)
				(= seconds (Random 3 8))
			)
		)
	)
)

(instance getLoot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 119 104 self)
			)
			(1
				((ego head?) hide:)
				(ego
					normal: 0
					view: 398
					loop: 10
					cel: 0
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(2
				(ego loop: 11 setCycle: Forward)
				(= cycles 50)
			)
			(3
				(if (< (closeDoor state?) 1)
					(closeDoor changeState: 1)
				)
				(ego loop: 12 cel: 0 setCycle: EndLoop)
			)
		)
	)
)

(instance closeDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((and (HaveMouse) (== howFast 0)) (= seconds 60))
					((and (HaveMouse) (!= howFast 0)) (= seconds 12))
					(else (= seconds 60))
				)
			)
			(1
				(if (curRoom script?)
					(-- state)
					(= cycles 1)
				else
					(SpeakAudio 330 0 1)
					(= seconds 4)
				)
			)
			(2
				(cls)
				(theAudio number: 8018 loop: 1 play:)
				(client
					setPri: (- (ego priority?) 1)
					setCycle: BegLoop self
				)
				(HandsOff)
				(if (not (& (ego onControl: 1) $2000))
					(ego observeControl: 8192)
				else
					(ego setMotion: MoveTo 36 42)
				)
			)
			(3
				(client stopUpd:)
				(= seconds 3)
			)
			(4
				(= deathMessage 331)
				(EgoDead 258)
			)
		)
	)
)

(instance getBottle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(ego setMotion: PolyPath 120 64 self)
			)
			(1
				(cls)
				(SpeakAudio 329)
				((ego head?) hide:)
				(ego
					normal: 0
					view: 398
					loop: 9
					cel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(SolvePuzzle 2)
				(bottle dispose:)
				(ego get: 6 setCycle: EndLoop self)
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

(instance door of Prop
	(properties
		x 67
		y 64
		view 398
		loop 8
		cel 13
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
					(SpeakAudio 332)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bottle of View
	(properties
		x 129
		y 60
		view 398
		loop 13
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
					(SpeakAudio 325)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getBottle)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance glint of Prop
	(properties
		view 398
		priority 15
		signal $4010
		detailLevel 3
	)
)

(instance coin of Prop
	(properties
		x 117
		y 60
		view 398
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
					(SpeakAudio 326)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(HandsOff)
					(curRoom setScript: getCoin)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance treasure of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 2 (event x?) (event y?)) $4000))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 327)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9055)
					(HandsOff)
					(ego setScript: getLoot)
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
