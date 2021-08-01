;;; Sierra Script 1.0 - (do not remove this comment)
(script# 552)
(include sci.sh)
(use Main)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	spiderRegion 0
)

(instance spiderRegion of Rgn
	(properties)
	
	(method (init)
		(super init: &rest)
		(spider
			setLoop: 10
			cycleSpeed: 2
			ignoreActors: 1
			ignoreHorizon: 1
			init:
		)
	)
)

(instance spider of Actor
	(properties
		x -100
		y -100
		view 452
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(if
			(or
				(and
					(Btst 18)
					(or
						(and
							(not (ego has: 6))
							(!= ((inventory at: 6) owner?) 200)
						)
						(and
							(not (ego has: 17))
							(!= ((inventory at: 17) owner?) 24)
						)
					)
				)
				(and (not (Btst 56)) (== global312 0))
			)
			(cond 
				((and (== curRoomNum 24) (== prevRoomNum 25)) (HandsOff) (self setScript: plantEats))
				((and (== curRoomNum 26) (== prevRoomNum 22))
					(plantEats register: 1)
					(HandsOff)
					(self setScript: plantEats)
				)
				((& (Random 0 99) $0001) (self setScript: spiderKill))
			)
		)
	)
)

(instance spiderKill of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (spider mover?)
			(DrawCel
				452
				11
				0
				(spider x?)
				(- (spider y?) 14)
				(spider priority?)
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 10))
			(1
				(if (not (curRoom script?))
					(HandsOff)
					(spider
						posn: (ego x?) 0
						setCycle: Fwd
						setPri: (+ (ego priority?) 1)
						setMotion: MoveTo (ego x?) (- (ego y?) 33) self
					)
					(theMusic2 number: 785 loop: 1 play:)
				else
					(-- state)
					(= cycles 1)
				)
			)
			(2 (= seconds 3))
			(3
				(cls)
				(theMusic2 stop:)
				(= deathMessage 822)
				(EgoDead 452 12)
			)
		)
	)
)

(instance plantEats of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (User canControl:) (HandsOff))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 30))
			(1
				(theMusic2 number: 785 loop: 1 play:)
				(plant1 init: cycleSpeed: 5 setCycle: End self)
				(ego moveSpeed: 2)
				(if register
					(ego setMotion: MoveTo 185 98)
					(plant1 loop: register x: 260 y: 85)
					(plant2 loop: register x: 255 y: 100)
				else
					(ego setMotion: MoveTo 100 165)
				)
			)
			(2
				(if (not register)
					(ego
						setMotion: MoveTo (- (ego x?) 10) (- (ego y?) 5) self
					)
				else
					(= cycles 1)
				)
			)
			(3
				(plant2 init: cycleSpeed: 5 setCycle: End self)
			)
			(4
				(if register
					(ego setMotion: MoveTo (ego x?) (- (ego y?) 3))
				)
				(plant1 setCycle: CT 4 -1 self)
			)
			(5
				((ego head?) hide:)
				(ego
					normal: 0
					view: 465
					y: (+ (ego y?) 1)
					setLoop: 2
					cel: 0
					setMotion: 0
					cycleSpeed: 2
					setCycle: End self
				)
			)
			(6 (= cycles 15))
			(7
				(ego
					normal: 1
					view: 0
					cycleSpeed: 0
					setCycle: KQ5SyncWalk
					setLoop: -1
				)
				((ego head?) show:)
				(if register
					(plant2 setCycle: CT 4 -1 self)
					(ego setMotion: MoveTo (+ (ego x?) 18) (+ (ego y?) 4))
				else
					(plant2 setCycle: CT 3 -1 self)
					(ego setMotion: MoveTo (ego x?) (+ (ego y?) 8))
				)
			)
			(8
				((ego head?) hide:)
				(ego
					normal: 0
					view: 465
					y: (+ (ego y?) 1)
					setLoop: 3
					cel: 0
					setMotion: 0
				)
				(= seconds 4)
			)
			(9
				(theMusic2 stop:)
				(= deathMessage 822)
				(EgoDead 253)
			)
		)
	)
)

(instance plant1 of Prop
	(properties
		x 170
		y 155
		view 465
	)
)

(instance plant2 of Prop
	(properties
		x 143
		y 175
		view 465
	)
)
