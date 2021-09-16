;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room41 0
)

(local
	thisControl
	i
)
(instance waves of List)

(instance wave1 of Prop
	(properties)
)

(instance wave2 of Prop)

(instance wave3 of Prop)

(instance Room41 of Room
	(properties
		picture 41
	)
	
	(method (init)
		(= north 35)
		(= south 32)
		(= east 31)
		(= west 40)
		(= horizon 80)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(self setRegions: GENESTA WATER BEACH GULL)
		(wave1
			isExtra: TRUE
			view: 664
			loop: 0
			cel: 3
			posn: 150 125
			setPri: 0
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 1
			init:
		)
		(wave2
			isExtra: TRUE
			view: 664
			loop: 1
			cel: 2
			posn: 112 165
			setPri: 0
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 1
			init:
		)
		(wave3
			isExtra: TRUE
			view: 664
			loop: 2
			cel: 3
			posn: 37 175
			setPri: 0
			setCycle: Forward
			ignoreActors:
			cycleSpeed: 1
			init:
		)
		(waves add: wave1 wave2 wave3)
		(wave1 setScript: waveActions_a)
		(switch prevRoomNum
			(38
				(ego x: 19 y: 99)
			)
			(35
				(cond 
					((== (ego view?) 2)
						(ego posn: 117 106)
					)
					((== (ego view?) 5)
						(ego posn: 186 112)
					)
					((== (ego view?) 6)
						(ego posn: 186 112)
					)
					((== (ego view?) 7)
						(ego posn: 201 112)
					)
					((== (ego view?) 8)
						(ego posn: 237 112)
					)
					(else
						(ego posn: 182 112)
					)
				)
			)
			(31
				(ego posn: 318 110)
			)
			(32
				(ego x: 138 y: 188)
			)
			(40
				(ego posn: 1 (ego y?))
			)
		)
		(ego xStep: 3 yStep: 2 init:)
		((ego viewer?) doit:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(& (= thisControl (ego onControl:)) cBROWN)
				(or (<= (ego heading?) 90) (>= (ego heading?) 270))
			)
			(curRoom newRoom: 35)
		)
		(if (& thisControl cRED)
			(curRoom newRoom: 38)
		)
	)
	
	(method (dispose)
		(waves dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event type?) saidEvent)
				(or
					(Said 'look/island')
					(Said 'look/around')
					(Said 'look/room')
					(Said 'look[<around][/noword]')
				)
			)
			(Print 41 0)
		)
	)
)

(instance waveActions_a of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(for ((= i 0)) (< i (waves size?)) ((++ i))
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
				(waveActions_a changeState: 1)
			)
		)
	)
)

(instance waveActions_b of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(for ((= i 0)) (< i (waves size?)) ((++ i))
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
				)
				(= i 0)
				(if howFast
					(self changeState: 1)
				else
					(waves eachElementDo: #addToPic)
				)
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
				(waveActions_b changeState: 1)
			)
		)
	)
)
