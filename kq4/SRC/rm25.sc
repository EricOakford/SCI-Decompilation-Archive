;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room25 0
)
(synonyms
	(brook brook brook)
)

(local
	local0
	waterControl
	saveViewer
	i
	aRipple1
	aRipple2
)
(instance wave1 of Prop)

(instance wave2 of Prop)

(instance waves of List)

(instance Room25 of Room
	(properties
		picture 25
	)
	
	(method (init)
		(= north 19)
		(= south 1)
		(= east 26)
		(= west 31)
		(= horizon 84)
		(= isIndoors FALSE)
		(= inCinematic FALSE)
		(ego edgeHit: 0)
		(super init:)
		(if isNightTime
			(curRoom overlay: 125)
		)
		(self setRegions: GULL WATER BEACH MEADOW)
		(Load VIEW 2)
		(Load VIEW 5)
		(Load VIEW 6)
		(Load VIEW 7)
		(Load VIEW 8)
		(Load VIEW 21)
		(wave1
			isExtra: TRUE
			view: 666
			loop: 3
			cel: 3
			posn: 177 74
			setPri: 0
			ignoreActors:
			setCycle: Forward
			cycleSpeed: 1
			init:
		)
		(wave2
			isExtra: TRUE
			view: 666
			loop: 4
			cel: 3
			posn: 164 116
			setPri: 0
			ignoreActors:
			setCycle: Forward
			cycleSpeed: 1
			init:
		)
		(= aRipple1 (Prop new:))
		(= aRipple2 (Prop new:))
		(aRipple1
			isExtra: TRUE
			view: 666
			loop: 6
			cel: 1
			posn: 254 182
			setPri: 0
			ignoreActors:
			setCycle: Forward
			init:
		)
		(aRipple2
			isExtra: TRUE
			view: 666
			loop: 7
			cel: 1
			posn: 226 164
			setPri: 0
			ignoreActors:
			setCycle: Forward
			init:
		)
		(waves add: wave1 wave2)
		(wave1 setScript: waveActions)
		(if (or (== prevRoomNum 222) (== (ego view?) 2))
			(= currentStatus egoNormal)
		)
		(switch currentStatus
			(egoNormal
				(switch prevRoomNum
					(1
						(if (== currentStatus egoSwimming)
							(ego posn: 60 188)
						else
							(ego posn: 217 188)
						)
					)
					(19
						(if (> (ego x?) 265)
							(ego posn: 239 (+ horizon (ego yStep?) 1))
						else
							(ego x: 185 y: (+ horizon (ego yStep?) 1))
						)
					)
					(26
						(if (<= (ego y?) horizon)
							(ego posn: 318 (+ horizon 1))
						else
							(ego posn: 318 (ego y?))
						)
					)
					(31
						(ego posn: 1 (ego y?))
					)
					(else 
						(ego posn: 229 125 setMotion: 0 loop: 1)
					)
				)
			)
			(egoInShallowWater
				(switch prevRoomNum
					(1
						(ego x: 126 y: 188)
					)
					(19
						(ego x: 166 y: (+ horizon (ego yStep?) 1))
					)
					(26
						(ego posn: 318 (ego y?))
					)
				)
			)
			(egoInKneeDeepWater
				(switch prevRoomNum
					(1
						(ego x: 126 y: 188)
					)
					(19
						(ego x: 157 y: (+ horizon (ego yStep?) 1))
					)
					(26
						(ego posn: 318 (ego y?))
					)
				)
			)
			(egoInWaistDeepWater
				(switch prevRoomNum
					(1
						(ego x: 126 y: 188)
					)
					(19
						(ego x: 146 y: (+ horizon (ego yStep?) 1))
					)
					(26
						(ego posn: 318 (ego y?))
					)
				)
			)
			(egoSwimming
				(switch prevRoomNum
					(1
						(ego x: 48 y: 188)
					)
					(19
						(ego posn: 88 (+ horizon (ego yStep?) 1))
					)
					(31
						(if (< (ego y?) horizon)
							(ego y: (+ horizon (ego yStep?) 1))
						)
					)
				)
			)
		)
		(ego xStep: 3 yStep: 2 init:)
		(HandsOn)
		(= saveViewer (ego viewer?))
	)
	
	(method (dispose)
		(waves dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/brook')
								(Print 25 0)
							)
							((Said '/grass')
								(Print 25 1)
							)
							((Said '[<around][/around,room,beach]')
								(Print 25 2)
							)
						)
					)
					((or (Said 'drink[/water]') (Said 'get/drink'))
						(if (ego inRect: 185 150 319 180)
							(cond 
								((!= currentStatus egoNormal)
									(Print 25 3)
								)
								(
									(or
										(& (= waterControl (IsObjectOnControl ego 12)) cGREEN)
										(& waterControl cLCYAN)
										(& waterControl cBLUE)
										(& waterControl cLBLUE)
									)
									(= oldEgoScript (ego script?))
									(ego setScript: riverActions)
								)
								(else
									(Print 25 4)
								)
							)
						else
							(event claimed: FALSE)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance riverActions of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= saveViewer (ego viewer?))
				(ego viewer: 0 view: 21 cel: 255 setCycle: EndLoop self)
			)
			(1
				(Timer setReal: self 5)
				(= globalPrint (Print 25 3 #at -1 10 #dispose))
			)
			(2
				(ego setCycle: BegLoop self)
			)
			(3
				(cls)
				(HandsOn)
				(ego view: 2 setCycle: Walk)
				(ego script: oldEgoScript viewer: saveViewer)
			)
		)
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
