;;; Sierra Script 1.0 - (do not remove this comment)
(script# 36)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room_36 0
)
(synonyms
	(lake pool lake)
)

(local
	swan
	local1
	ripple4
	ripple5
	ripple1
	ripple2
	ripple3
	ripple6
)
(instance Room_36 of Room
	(properties
		picture 36
		name "Room 36"
	)
	
	(method (init)
		(= north 33)
		(= south 39)
		(= east 37)
		(= horizon 82)
		(= isIndoors FALSE)
		(super init:)
		(self setRegions: GENESTA)
		(Load VIEW 345)
		(Load VIEW 21)
		(Load VIEW 2)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(= ripple3 (Prop new:))
		(ripple1
			isExtra: TRUE
			view: 663
			loop: 0
			cel: 1
			posn: 114 68
			setPri: 3
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(ripple2
			isExtra: TRUE
			view: 663
			loop: 1
			cel: 1
			posn: 23 81
			setPri: 4
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(ripple3
			isExtra: TRUE
			view: 663
			loop: 2
			cel: 3
			posn: 12 93
			setPri: 5
			setCycle: Forward
			cycleSpeed: 2
			ignoreActors:
			cycleSpeed: 2
			init:
		)
		(= ripple4 (Prop new:))
		(= ripple5 (Prop new:))
		(ripple4
			isExtra: TRUE
			view: 650
			loop: 5
			cel: 0
			posn: 156 118
			setPri: 8
			setCycle: Forward
			ignoreActors:
			init:
		)
		(ripple5
			isExtra: TRUE
			view: 650
			loop: 6
			cel: 3
			posn: 218 130
			setPri: 9
			setCycle: Forward
			ignoreActors:
			init:
		)
		(= ripple6 (Prop new:))
		(ripple6
			isExtra: TRUE
			view: 341
			loop: 4
			cel: 2
			posn: 84 30
			setPri: 0
			setCycle: Forward
			cycleSpeed: 1
			ignoreActors:
			init:
		)
		(ego view: 2 xStep: 3 yStep: 2 init:)
		(self setScript: Ego_drinking_water)
		(if isNightTime (= picture 136))
		(cond 
			((== prevRoomNum 33) (ego posn: 100 85))
			((== prevRoomNum 39) (ego x: 165 y: 186))
		)
		(= swan (Actor new:))
		(swan
			posn: 188 120
			view: 345
			setCycle: Walk
			moveSpeed: 2
			setMotion: Wander
			illegalBits: cWHITE
			xStep: 1
			yStep: 1
			init:
		)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'deliver>')
					(if (= inventorySaidMe (inventory saidMe:))
						(if (ego has: (inventory indexOf: inventorySaidMe))
							(Print 36 0)
						else
							(DontHave)
						)
					)
				)
				(
					(or
						(Said 'enter,capture/fish')
						(Said 'fish[/!*]')
						(Said 'cast/pole')
					)
					(Print 36 1)
				)
				((Said 'look>')
					(cond 
						((Said '<in/lake,water') (Print 36 2))
						((Said '<under/bridge') (Print 36 3))
						((Said '/beach') (Print 36 4))
						((Said '/bush') (Print 36 5))
						((Said '/dirt') (Print 36 6))
						((Said '/grass') (Print 36 7))
						((Said '/flora') (Print 36 8))
						((Said '/blossom') (Print 36 9))
						((Said '/forest') (Print 36 10))
						((Said '/garden') (Print 36 11))
						((Said '/lake,water') (Print 36 12))
						((Said '/bridge') (Print 36 13))
						((Said '/path') (Print 36 14))
						((Said '/bench') (Print 36 15))
						((Said '/castle') (Print 36 16))
						((Said '/ocean') (Print 36 17))
						((Said '/swan') (Print 36 18))
						((Said '/parrot,bird,cockatoo') (Print 36 19))
						((Said '[<around][/room,island]') (Print 36 20))
					)
				)
				((Said 'get,capture/bird,parrot,cockatoo') (Print 36 0))
				((Said 'converse/bird,parrot,cockatoo') (Print 36 21))
				((Said 'get/blossom') (Print 36 22))
				((Said 'wade,bathe,dive') (Print 36 23))
				((Said 'get/water') (Print 36 24))
				((or (Said 'drink') (Said 'get/drink'))
					(if
					(& (= local1 (IsObjectOnControl ego 15)) $0002)
						(Ego_drinking_water changeState: 1)
					else
						(Print 800 1)
					)
				)
				((Said 'sit') (Print 36 25))
				((Said 'converse/swan') (Print 36 26))
				((Said 'converse/parrot,bird,cockatoo') (Print 36 27))
				((Said 'get,capture/swan') (Print 36 28))
				((Said 'kiss') (Print 36 29))
			)
		)
	)
)

(instance Ego_drinking_water of Script
	(properties
		name "Ego drinking water"
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego setMotion: 0 view: 21 cel: 255 setCycle: EndLoop self)
			)
			(2
				(= underBits (Print 36 30 #at -1 10 #dispose))
				(Timer setReal: self 5)
			)
			(3 (ego setCycle: BegLoop self))
			(4
				(ego view: 2 setCycle: Walk)
				(cls)
				(HandsOn)
			)
		)
	)
)
