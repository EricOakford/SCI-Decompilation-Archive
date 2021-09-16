;;; Sierra Script 1.0 - (do not remove this comment)
(script# 13)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	Room13 0
)

(local
	aSmoke
	aFisher
	i
	meetTime
)
(instance wave1 of Prop)

(instance wave2 of Prop)

(instance wave3 of Prop)

(instance waves of List)

(instance minBlock of Block
	(properties
		top 115
		left 262
		bottom 121
		right 266
	)
)

(instance Room13 of Room
	(properties
		picture 13
	)
	
	(method (init)
		(= north 7)
		(= south 19)
		(= east 14)
		(= west 31)
		(= horizon 80)
		(= isIndoors FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime
			(curRoom overlay: 113)
		)
		(wave1
			isExtra: TRUE
			view: 665
			loop: 3
			cel: 2
			posn: 133 91
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave2
			isExtra: TRUE
			view: 665
			loop: 4
			cel: 3
			posn: 126 120
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(wave3
			isExtra: TRUE
			view: 665
			loop: 5
			cel: 1
			posn: 127 149
			setPri: 0
			ignoreActors:
			cycleSpeed: 3
			init:
		)
		(waves add: wave1 wave2 wave3)
		(wave1 setScript: waveActions)
		(= meetTime
			(+
				(* (- gameHours hourLastMetMinstrel) 60)
				(- gameMinutes minutesLastMetMinstrel)
			)
		)
		(if (and ((Inventory at: iLute) ownedBy: 203) (>= meetTime 3))
			(= whereIsMinstrel
				(/ (= whereIsMinstrel (Random 1 30)) 10)
			)
		)
		(if (== whereIsMinstrel minstrel13)
			((= minstrel (Actor new:))
				view: 174
				loop: 2
				setCel: 0
				illegalBits: 0
				posn: 264 118
				init:
			)
			(ego observeBlocks: minBlock)
		)
		(self setRegions: WATER MINSTREL BEACH GULL MEADOW)
		(if (and (== fishermanState fisherGoneFishing) (not isNightTime))
			((= aFisher (View new:))
				view: 534
				setLoop: 0
				setCel: 0
				posn: 65 54
				init:
				addToPic:
			)
		)
		(= aSmoke (Prop new:))
		(aSmoke
			view: 625
			isExtra: TRUE
			posn: 173 29
			setLoop: 1
			setCycle: Forward
			cycleSpeed: 2
			init:
		)
		(if (== prevRoomNum 14)
			(= currentStatus egoNormal)
		)
		(switch currentStatus
			(egoNormal
				(switch prevRoomNum
					(19
						(if (> (ego x?) 233)
							(ego x: 278 y: 188)
						else
							(ego x: 205 y: 188)
						)
					)
					(7
						(if (> (ego x?) 208)
							(ego x: 194 y: (+ horizon 2))
						else
							(ego posn: 148 (+ horizon 2))
						)
					)
					(14
						(if (< (ego y?) horizon)
							(ego x: 318 y: (+ horizon 2))
						else
							(ego x: 318)
						)
					)
				)
			)
			(egoInShallowWater
				(switch prevRoomNum
					(19 (ego x: 88 y: 188))
					(7
						(ego x: 133 y: (+ horizon 2))
					)
				)
			)
			(egoInKneeDeepWater
				(switch prevRoomNum
					(19 (ego x: 88 y: 188))
					(7
						(ego x: 124 y: (+ horizon 2))
					)
				)
			)
			(egoInWaistDeepWater
				(switch prevRoomNum
					(19 (ego x: 45 y: 188))
					(7
						(ego x: 108 y: (+ horizon 2))
					)
				)
			)
			(egoSwimming
				(switch prevRoomNum
					(19 (ego x: 16 y: 188))
					(7
						(if (> (ego x?) 75)
							(ego x: 75 y: (+ horizon 2))
						else
							(ego y: (+ horizon (ego yStep?) 1))
						)
					)
					(31
						(if (<= (ego y?) horizon)
							(ego x: 1 y: (+ horizon 2))
						else
							(ego x: 1)
						)
					)
				)
			)
		)
		(ego init:)
	)
	
	(method (dispose)
		(waves dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					((Said '/grass')
						(Print 13 0)
					)
					((Said '/dock')
						(if (== fishermanState fisherGoneFishing)
							(Print 13 1)
						else
							(Print 13 2)
						)
					)
					((Said '/cottage') (Print 13 2))
					((Said '/fisherman,man,woman,person')
						(cond 
							((cast contains: minstrel)
								(event claimed: FALSE)
							)
							((== fishermanState fisherGoneFishing)
								(Print 13 3)
							)
							(else
								(Print 13 4)
							)
						)
					)
					((Said '[<around][/room]')
						(Print 13 5)
					)
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom n)
		(if (cast contains: minstrel)
			(= hourLastMetMinstrel gameHours)
			(= minutesLastMetMinstrel gameMinutes)
		)
		(super newRoom: n)
	)
)

(instance waveActions of Script
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
				((waves at: i) cel: 255 show: setCycle: EndLoop self)
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
