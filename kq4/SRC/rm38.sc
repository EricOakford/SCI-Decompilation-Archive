;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include game.sh)
(use Main)
(use Intrface)
(use Extra)
(use Motion)
(use Game)
(use Actor)

(public
	Room38 0
)

(local
	ripple1
	ripple2
	bird1
	bird2
)
(instance Room38 of Room
	(properties
		picture 38
	)
	
	(method (init)
		(= north 35)
		(= south 41)
		(= west 37)
		(= horizon 74)
		(= isIndoors FALSE)
		(if isNightTime (= picture 138))
		(super init:)
		(self setRegions: 505)
		(ego view: 2 xStep: 3 yStep: 2 init:)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(= bird1 (Extra new:))
		(= bird2 (Extra new:))
		(ripple1
			view: 662
			loop: 3
			cel: 0
			posn: 199 74
			setPri: 0
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(ripple2
			view: 662
			loop: 4
			cel: 3
			posn: 303 98
			setPri: 0
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(bird1
			view: 341
			loop: 2
			posn: 243 37
			minPause: 4
			maxPause: 60
			pauseCel: 1
			minCycles: 10
			maxCycles: 40
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(bird2
			view: 341
			loop: 3
			pauseCel: 1
			minPause: 4
			maxPause: 60
			minCycles: 10
			maxCycles: 50
			posn: 214 26
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(cond 
			((== prevRoomNum 35) (ego x: 196 y: 78))
			((== prevRoomNum 41) (ego x: 159 y: 187))
		)
	)
	
	(method (dispose)
		(DisposeScript EXTRA)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'look>')
					(cond 
						((Said '/bush') (Print 38 0))
						((Said '/beach') (Print 38 1))
						((Said '/dirt') (Print 38 2))
						((Said '/grass') (Print 38 3))
						((Said '/flora') (Print 38 4))
						((Said '/blossom') (Print 38 5))
						((Said '/forest') (Print 38 6))
						((Said '/garden') (Print 38 7))
						((Said '/path') (Print 38 8))
						((Said '/castle') (Print 38 9))
						((Said '/ocean') (Print 38 10))
						((Said '/bird') (Print 38 11))
						((Said '/monument,dolphin') (Print 38 12))
						((Said '[<around][/island,room]') (Print 38 13))
					)
				)
				((Said 'converse/bird') (Print 38 14))
				((Said 'capture,get/bird') (Print 38 15))
				((Said 'kiss/bird') (Print 38 16))
				((Said 'get/blossom') (Print 38 17))
			)
		)
	)
)
