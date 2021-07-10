;;; Sierra Script 1.0 - (do not remove this comment)
(script# 45)
(include sci.sh)
(use Main)
(use Intrface)
(use LoadMany)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	netBeachRm 0
)

(local
	local0
	local1
	local2
)
(instance netBeachRm of Rm
	(properties
		picture 55
		north 70
		east 46
		south 53
		west 53
	)
	
	(method (init)
		(super init:)
		(self setRegions: 305)
		(LoadMany 128 152 455 155 54)
		(ego init:)
		(HandsOn)
		(switch prevRoomNum
			(53
				(ego
					illegalBits: -32768
					posn: 10 135
					loop: 0
					setMotion: MoveTo 320 140
				)
			)
			(46
				(ego
					illegalBits: -32768
					posn: 285 (ego y?)
					loop: 1
					setMotion: MoveTo -5 (ego y?)
				)
			)
			(else 
				(ego
					illegalBits: -32768
					posn: 285 100
					loop: 1
					setMotion: MoveTo -5 (ego y?)
				)
			)
		)
		(if (not ((inventory at: 4) ownedBy: 55))
			(net
				init:
				illegalBits: 0
				ignoreHorizon: 1
				ignoreActors: 0
				posn: 226 -20
				setScript: fishing 0 (Random 40 120)
			)
			(line
				init:
				illegalBits: 0
				ignoreHorizon: 1
				ignoreActors: 1
				posn: 225 -39
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(and (Said 'look>') (Said '[<around][/room][/water]')) (Print 45 0))
		)
	)
)

(instance DV-3X of View
	(properties
		view 152
	)
)

(instance net of Act
	(properties
		view 455
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
			(Said 'bind,conceal,adjust,drop,park/vehicle,diver') (if (ego has: 6) (Print 45 1) else (Print 45 2)))
			((Said '/net>')
				(if local2
					(cond 
						((Said 'look[<at]') (Print 45 3))
						((Said 'look[<in]') (if (== (net cel?) 1) (Print 45 4) else (Print 45 5)))
						((Said 'get/net') (Print 45 6))
						((Said 'open/net') (Print 45 7))
					)
				else
					(Print 45 8)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance line of Act
	(properties
		view 455
		loop 1
	)
)

(instance fishing of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles register))
			(1
				(line setMotion: MoveTo 225 57)
				(= local2 1)
				(net setCel: 0 setMotion: MoveTo 226 86 self)
			)
			(2
				(= local0 1)
				(if (== local1 1)
					((inventory at: 4) moveTo: 55)
					(= local1 0)
				)
				(= cycles 200)
			)
			(3
				(= local0 0)
				(line setMotion: MoveTo 225 -49)
				(net setMotion: MoveTo 226 -20 self)
			)
			(4
				(= local2 0)
				(= cycles (Random 300 700))
			)
			(5 (self changeState: 1))
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(cond 
			((super handleEvent: event))
			((Said 'adjust/bottle')
				(cond 
					((not local2) (Print 45 9))
					(
						(not
							(if
								(and
									(< 100 (ego x?))
									(< (ego x?) 300)
									(< 0 (ego y?))
								)
								(< (ego y?) 120)
							)
						)
						(Print 45 10)
					)
					((and local0 (ego has: 4)) (ego setScript: putBottle) (self dispose:))
					((ego has: 4) (Print 45 11))
					(else (Print 45 12))
				)
			)
		)
	)
)

(instance putBottle of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 45 13)
				(ego ignoreActors: 1 illegalBits: 0)
				(cond 
					((and (< (ego x?) 227) (< 85 (ego y?))) (self changeState: 1))
					((and (< (ego x?) 227) (< (ego y?) 86)) (self changeState: 4))
					((and (< 226 (ego x?)) (< 85 (ego y?))) (self changeState: 2))
					(else (self changeState: 3))
				)
			)
			(1
				(ego setMotion: MoveTo 180 104 self)
			)
			(2
				(ego setMotion: MoveTo 247 104 self)
			)
			(3
				(ego setMotion: MoveTo 248 73 self)
			)
			(4
				(ego setMotion: MoveTo 195 73 self)
			)
			(5
				(ego setMotion: MoveTo 195 96 self)
			)
			(6
				(ego setMotion: MoveTo 226 96 self)
			)
			(7
				(ego
					view: 152
					viewer: 0
					setLoop: 5
					setCel: 0
					cycleSpeed: 1
					setCycle: End self
				)
				(if (ego has: 6)
					(DV-3X
						init:
						view: 155
						setLoop: 8
						setCel: 2
						setPri: (ego priority?)
						ignoreActors:
						posn: (+ (ego x?) 28) (- (ego y?) 3)
					)
				)
			)
			(8
				(ego setLoop: 2 setCel: 0 setCycle: End self)
			)
			(9
				(net setCel: 1)
				(ego setCycle: Beg self)
				(= local1 1)
				(theGame changeScore: 1)
				(ego put: 4)
			)
			(10
				(line setMotion: MoveTo 225 -49)
				(net setMotion: MoveTo 226 -20)
				(ego setLoop: 5 setCel: 4 setCycle: Beg self)
			)
			(11
				(if (ego has: 6)
					(DV-3X dispose:)
					(ego
						view: 54
						loop: 0
						setLoop: -1
						cel: 5
						setCycle: Walk
						cycleSpeed: 2
						ignoreActors: 0
						illegalBits: -32768
					)
				else
					(ego
						view: 154
						loop: 0
						setLoop: -1
						cel: 5
						setCycle: Walk
						cycleSpeed: 2
						ignoreActors: 0
						illegalBits: -32768
					)
				)
				(= local0 0)
				(net setScript: fishing 0 300)
				(HandsOn)
				(= cycles 30)
			)
			(12
				(= local2 0)
				(self dispose:)
			)
		)
	)
)
