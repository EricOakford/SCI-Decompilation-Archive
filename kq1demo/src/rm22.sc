;;; Sierra Script 1.0 - (do not remove this comment)
(script# 22)
(include game.sh)
(use Main)
(use LoadMany)
(use DPath)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm22 0
)

(instance rm22 of Room
	(properties
		picture 22
		style IRISOUT
	)
	
	(method (init)
		(LoadMany VIEW 52 3 160 33 249 0)
		(LoadMany SOUND 54 55 63)
		((ScriptID 0 6) fade:)
		(super init:)
		(ego
			init:
			view: 52
			setLoop: 2
			setCycle: Walk
			moveSpeed: 0
			xStep: 9
			illegalBits: 0
			posn: 134 90
		)
		(HandsOff)
		(bucket init:)
		(condor
			illegalBits: 0
			ignoreActors: TRUE
			ignoreHorizon: TRUE
			init:
		)
		(curRoom setScript: gotoBucket)
	)
)

(instance gotoBucket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 44 90 self)
			)
			(1
				(DisplayNewGraphics)
				((ScriptID 0 5) number: 63 loop: 1 play: self)
				(ego view: 3 loop: 3 cel: 0)
				(= cycles 3)
			)
			(2
				(bucket hide:)
				(ego
					view: 249
					x: 44
					y: 89
					xStep: 3
					yStep: 2
					setLoop: -1
					loop: 1
					setCycle: Forward
				)
			)
			(3
				(self dispose:)
				(curRoom setScript: startWalking)
			)
		)
	)
)

(instance startWalking of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if
		(and (> (ego y?) 138) (!= (condor script?) swoopEast))
			(condor setScript: swoopEast)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				((ScriptID 0 6) number: 55 loop: -1 play:)
				(self cue:)
			)
			(2
				(ego view: 0 posn: 44 90 loop: 2 setCycle: Walk)
				(bucket show:)
				(= cycles 4)
			)
			(3
				(ego setMotion: DPath 70 119 132 140 self)
			)
			(4
				(ego view: 3 cycleSpeed: 2 setCycle: EndLoop self)
			)
			(5 (NormalEgo) (ego loop: 2))
		)
	)
)

(instance swoopEast of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(condor
					posn: -30 10
					setPri: 8
					loop: 0
					cel: 1
					xStep: 8
					yStep: 3
					setCycle: Forward
					setMotion: MoveTo 124 72 self
				)
			)
			(1
				(condor
					setCycle: Forward
					cycleSpeed: 2
					setMotion: MoveTo 132 72 self
				)
			)
			(2
				(curRoom setScript: birdRide)
				(condor cycleSpeed: 1 setMotion: MoveTo 348 0 self)
			)
			(3 (curRoom newRoom: 80))
		)
	)
)

(instance birdRide of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(ego posn: (condor x?) (+ (condor y?) 60))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: 33
					loop: 0
					setCycle: (if (!= howFast 0) Forward else 0)
					setMotion: 0
					ignoreHorizon: 1
					ignoreActors: 1
					illegalBits: 0
					setPri: 7
				)
			)
		)
	)
)

(instance condor of Actor
	(properties
		x -52
		y 52
		view 160
		cel 3
		priority 8
		signal fixPriOn
	)
)

(instance bucket of View
	(properties
		x 44
		y 89
		view 249
		signal stopUpdOn
	)
)
