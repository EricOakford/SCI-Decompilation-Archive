;;; Sierra Script 1.0 - (do not remove this comment)
(script# dmBird)
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
		picture rOutsideCave
		style IRISOUT
	)
	
	(method (init)
		(LoadMany VIEW
			vEgoBurned
			vEgoLookAround
			vCondor
			vEgoFlown
			vBucket
			vEgo
		)
		(LoadMany SOUND 54 55 63)
		((ScriptID 0 6) fade:)
		(super init:)
		(ego
			init:
			view: vEgoBurned
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 44 90 self)
			)
			(1
				(DisplayNewGraphics)
				((ScriptID 0 5) number: 63 loop: 1 play: self)
				(ego
					view: vEgoLookAround
					loop: 3
					cel: 0
				)
				(= cycles 3)
			)
			(2
				(bucket hide:)
				(ego
					view: vBucket
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

	(method (doit)
		(super doit:)
		(if
		(and (> (ego y?) 138) (!= (condor script?) swoopEast))
			(condor setScript: swoopEast)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= cycles 1)
			)
			(1
				((ScriptID 0 6) number: 55 loop: -1 play:)
				(self cue:)
			)
			(2
				(ego
					view: vEgo
					posn: 44 90
					loop: loopS
					setCycle: Walk
				)
				(bucket show:)
				(= cycles 4)
			)
			(3
				(ego setMotion: DPath 70 119 132 140 self)
			)
			(4
				(ego
					view: vEgoLookAround
					cycleSpeed: 2
					setCycle: EndLoop
					self
				)
			)
			(5
				(NormalEgo)
				(ego loop: loopS)
			)
		)
	)
)

(instance swoopEast of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(condor
					posn: -30 10
					setPri: 8
					loop: loopE
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
			(3
				(curRoom newRoom: rSky)
			)
		)
	)
)

(instance birdRide of Script
	
	(method (doit)
		(super doit: &rest)
		(ego posn: (condor x?) (+ (condor y?) 60))
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					view: vEgoFlown
					loop: 0
					setCycle: (if (!= howFast slow) Forward else 0)
					setMotion: 0
					ignoreHorizon: TRUE
					ignoreActors: TRUE
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
		view vCondor
		cel 3
		priority 8
		signal fixPriOn
	)
)

(instance bucket of View
	(properties
		x 44
		y 89
		view vBucket
		signal stopUpdOn
	)
)
