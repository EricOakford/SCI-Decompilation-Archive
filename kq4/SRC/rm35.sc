;;; Sierra Script 1.0 - (do not remove this comment)
(script# 35)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room35 0
)

(local
	i
)
(instance waves of List
	(properties)
)

(instance wave1 of Prop
	(properties)
)

(instance wave2 of Prop
	(properties)
)

(instance wave3 of Prop
	(properties)
)

(instance Room35 of Room
	(properties
		picture 35
	)
	
	(method (init)
		(if isNightTime (= picture 135))
		(= east (= north 31))
		(= west 34)
		(= horizon 80)
		(= isIndoors FALSE)
		(= oldHorizon horizon)
		(ego edgeHit: 0)
		(super init:)
		(self setRegions: GENESTA WATER BEACH GULL)
		(wave1
			isExtra: TRUE
			view: 662
			loop: 0
			cel: 0
			posn: 110 123
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave2
			isExtra: TRUE
			view: 662
			loop: 1
			cel: 0
			posn: 166 142
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave3
			isExtra: TRUE
			view: 662
			loop: 2
			cel: 0
			posn: 228 185
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(waves add: wave1 wave2 wave3)
		(wave1 setScript: waveActions)
		(switch prevRoomNum
			(38 (ego x: 93 y: 187))
			(41
				(cond 
					((== (ego view?) 5) (ego x: 236 y: 187))
					((== (ego view?) 6) (ego x: 266 y: 187))
					((== (ego view?) 7) (ego x: 289 y: 187))
					((== (ego view?) 8) (ego x: 310 y: 187))
					(else (ego x: 212 y: 183))
				)
				((ego viewer?) doit:)
			)
			(31
				(if (<= (ego y?) horizon)
					(ego posn: (ego x?) (+ horizon 2))
				else
					(ego posn: 317 (ego y?))
				)
			)
			(34 (ego x: 1))
		)
		(ego xStep: 3 yStep: 2 init:)
	)
	
	(method (doit)
		(cond 
			((>= (ego y?) 189)
				(if (< (ego x?) 125)
					(= newRoomNum 38)
				else
					(= newRoomNum 41)
				)
			)
			((>= (ego x?) 319) (= newRoomNum 31))
			((<= (ego x?) 0) (= newRoomNum 34))
			((<= (ego y?) horizon) (= newRoomNum 31))
		)
	)
	
	(method (dispose)
		(waves dispose: delete:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) saidEvent)
				(Said 'look[<around][/room,island]')
			)
			(Print 35 0)
		)
	)
)

(instance waveActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= i 0)
				(while (< i (waves size?))
					((View new:)
						view: ((waves at: i) view?)
						loop: ((waves at: i) loop?)
						cel: 0
						setPri: 0
						ignoreActors:
						x: ((waves at: i) x?)
						y: ((waves at: i) y?)
						init:
						addToPic:
						yourself:
					)
					(++ i)
				)
				(= i 0)
				(self changeState: 1)
			)
			(1
				((waves at: i) cel: 0 show: setCycle: EndLoop self)
			)
			(2
				((waves at: i) hide:)
				(if (< i (- (waves size?) 1))
					(++ i)
				else
					(= i 0)
				)
				(waveActions changeState: 1)
			)
		)
	)
)
