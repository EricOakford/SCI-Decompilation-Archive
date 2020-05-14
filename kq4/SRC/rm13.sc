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
	local2
	local3
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

(instance waves of List
	(properties)
)

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
		(if isNightTime (curRoom overlay: 113))
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
		(= local3
			(+
				(* (- gameHours hourLastMetMinstrel) 60)
				(- gameMinutes minutesLastMetMinstrel)
			)
		)
		(if
		(and ((Inventory at: iLute) ownedBy: 203) (>= local3 3))
			(= whereIsMinstrel
				(/ (= whereIsMinstrel (Random 1 30)) 10)
			)
		)
		(if (== whereIsMinstrel 1)
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
		(if (== prevRoomNum 14) (= currentStatus egoNormal))
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
			(if
			(and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					((Said '/grass') (Print 13 0))
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
							((cast contains: minstrel) (event claimed: FALSE))
							((== fishermanState fisherGoneFishing) (Print 13 3))
							(else (Print 13 4))
						)
					)
					((Said '[<around][/room]') (Print 13 5))
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (cast contains: minstrel)
			(= hourLastMetMinstrel gameHours)
			(= minutesLastMetMinstrel gameMinutes)
		)
		(super newRoom: newRoomNumber)
	)
)

(instance waveActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local2 0)
				(while (< local2 (waves size?))
					((View new:)
						view: ((waves at: local2) view?)
						loop: ((waves at: local2) loop?)
						cel: 0
						setPri: 0
						ignoreActors:
						x: ((waves at: local2) x?)
						y: ((waves at: local2) y?)
						init:
						addToPic:
						yourself:
					)
					(++ local2)
				)
				(= local2 0)
				(self changeState: 1)
			)
			(1
				((waves at: local2) cel: 255 show: setCycle: EndLoop self)
			)
			(2
				((waves at: local2) hide:)
				(if (< local2 (- (waves size?) 1))
					(++ local2)
				else
					(= local2 0)
				)
				(waveActions changeState: 1)
			)
		)
	)
)
