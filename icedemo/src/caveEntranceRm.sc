;;; Sierra Script 1.0 - (do not remove this comment)
(script# SCUBA)
(include game.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Smooper)
(use Wander)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	caveEntranceRm 0
)

(procedure (AddRock theLoop theCel theX theY thePriority &tmp newPV)
	((= newPV (PicView new:))
		view: 56
		loop: theLoop
		cel: theCel
		x: theX
		y: theY
	)
	(if (> argc 4) (newPV priority: thePriority))
	(addToPics add: newPV)
)

(procedure (AddPlant theLoop theX theY thePriority)
	((Clone wavingPlant)
		loop: theLoop
		x: theX
		y: theY
		setPri: thePriority
		init:
	)
)

(instance caveEntranceRm of Room
	(properties
		picture 56
		style DISSOLVE
	)
	
	(method (init)
		(Load SOUND 60)
		(Load VIEW 55)
		(LoadMany SCRIPT SMOOPER WANDER)
		(super init:)
		(bubbles init:)
		(globalSound number: 60 priority: 1 loop: -1)
		(ego
			view: 54
			posn: -10 70
			illegalBits: 0
			ignoreActors: 1
			cycleSpeed: 2
			setLoop: -1
			setLoop: scubaLooper
			init:
		)
		(scubaLooper vNormal: 54 vChangeDir: 55)
		(torpedoRay init: setScript: rayScript setCycle: Walk)
		(air init: ignoreActors: TRUE setPri: 15)
		(gauge init: ignoreActors: TRUE setPri: 15 addToPic:)
		(fish1 init:)
		(fish2 init:)
		(schoolOfFish init:)
		(AddPlant 5 88 118 8)
		(AddPlant 6 26 119 8)
		(AddPlant 6 74 91 5)
		(AddPlant 7 271 126 8)
		(AddPlant 5 309 124 8)
		(AddPlant 5 285 85 5)
		(AddPlant 6 210 83 4)
		(AddRock 0 0 31 120 8)
		(AddRock 0 0 60 92)
		(AddRock 0 1 24 97)
		(AddRock 0 2 87 117)
		(AddRock 0 1 304 123)
		(AddRock 4 1 270 124)
		(addToPics doit:)
		(self setScript: swimAround)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript WANDER)
		(DisposeScript SMOOPER)
	)
)

(instance caveEntranceFeat of Feature
	(properties
		y 74
		x 48
	)
)

(instance egosBubbleScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(bubbles
					x:
						(switch (ego loop?)
							(0 (+ (ego x?) 8))
							(1 (- (ego x?) 8))
							(else  (ego x?))
						)
					y: (- (ego y?) 5)
					show:
					setPri: (ego priority?)
					setMotion: MoveTo (ego x?) 5 self
				)
			)
			(1
				(bubbles hide:)
				(self dispose:)
			)
		)
	)
)

(instance bubbles of Actor
	(properties
		yStep 3
		view 54
		loop 4
	)
	
	(method (init)
		(self
			setCycle: Walk
			setLoop: 4
			ignoreActors:
			illegalBits: 0
			setScript: egosBubbleScript
		)
		(super init:)
		(self hide:)
	)
)

(instance wavingPlant of Prop
	(properties
		view 56
		cycleSpeed 3
	)
	
	(method (init)
		(super init:)
		(self isExtra: TRUE setCycle: Forward)
	)
)

(instance fish1 of Actor
	(properties
		view 57
	)
	
	(method (init)
		(super init:)
		(self
			xStep: 1
			yStep: 1
			posn: (Random 20 300) (Random 100 150)
			cycleSpeed: 2
			setCycle: Walk
			setMotion: Wander 100
		)
	)
	
	(method (canBeHere)
		(if (super canBeHere:) (self inRect: -20 -20 340 210))
	)
)

(instance fish2 of Actor
	(properties
		view 67
	)
	
	(method (init)
		(super init:)
		(self
			xStep: 1
			yStep: 1
			cycleSpeed: 2
			setCycle: Walk
			posn: (Random 20 300) (Random 70 150)
			setMotion: Wander 100
		)
	)
	
	(method (canBeHere)
		(if (super canBeHere:) (self inRect: -20 -20 340 210))
	)
)

(instance scubaLooper of SmoothLooper
	(properties
		vNormal 54
		vChangeDir 55
	)
)

(instance schoolOfFish of Actor
	(properties
		view 267
	)
	
	(method (init)
		(super init:)
		(self
			posn: (Random 0 320) (Random 0 200)
			setMotion: Wander 100
			setCycle: Forward
		)
	)
	
	(method (canBeHere)
		(if (super canBeHere:) (self inRect: -20 -20 340 210))
	)
)

(instance torpedoRay of Actor
	(properties
		y 140
		x 259
		view 60
	)
	
	(method (init)
		(super init: &rest)
		(self ignoreControl: cWHITE ignoreActors: TRUE)
	)
)

(instance rayScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 125 130 self
				)
			)
			(1
				(client setCel: 0 setLoop: 4 setCycle: EndLoop self)
			)
			(2
				(client setLoop: 5 setCycle: EndLoop self)
			)
			(3
				(client
					setLoop: 3
					setCycle: Walk
					setMotion: MoveTo 259 140 self
				)
			)
			(4
				(client
					setLoop: 1
					setCel: 16
					cycleSpeed: 2
					setCycle: BegLoop self
				)
			)
			(5 (self dispose:))
		)
	)
)

(instance gauge of Prop
	(properties
		y 11
		x 22
		z 1
		view 54
		loop 5
		priority 15
	)
)

(instance air of Prop
	(properties
		y 8
		x 5
		z 2
		view 54
		loop 6
		cel 4
	)
)

(instance swimAround of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 159 48 self)
				(= cycles 10)
			)
			(1
				(Print 56 0 #at 25 154 #width 270 #dispose)
			)
			(2
				(globalSound play:)
				(bubbles setScript: egosBubbleScript)
				(ego setMotion: MoveTo 115 45 self)
			)
			(3
				(globalSound stop:)
				(ego looper: 0)
				(curRoom newRoom: 87)
			)
		)
	)
)
