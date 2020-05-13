;;; Sierra Script 1.0 - (do not remove this comment)
(script# 60)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm060 0
)

(instance rm060 of Room
	(properties
		picture 60
		horizon 77
		east 61
		south 66
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(Load VIEW 96)
		(super init:)
		(bigBub init:)
		(smallBub init:)
		(switch prevRoomNum
			(61
				(ego posn: 317 (ego y?) init:)
			)
			(66
				(ego
					posn: (if (< (ego x?) 125) 125 else (ego x?)) 187
					init:
				)
			)
		)
		(TheMenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (doit &tmp egoOnControl [temp1 50])
		(super doit:)
		(if (not (curRoom script?))
			(cond 
				(
					(or
						(== (= egoOnControl (ego onControl:)) 3)
						(== egoOnControl 2)
						(== egoOnControl 7)
					)
					(ego setPri: 3 posn: (ego x?) (- (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 5) (== egoOnControl 13))
					(ego setPri: 5 posn: (- (ego x?) 6) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 9)
					(ego setPri: 7 posn: (ego x?) (- (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look/bubble') (Print 60 0))
	)
	
	(method (newRoom newRoomNumber)
		(if (not script)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance BigBubble of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 2)))
			(1
				(bigBub
					posn: (Random 14 320) (Random 132 194)
					setCycle: EndLoop self
				)
			)
			(2
				(bigBub setCel: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance SmallBubble of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(smallBub
					posn: (Random 143 320) (Random 81 106)
					setCycle: EndLoop self
				)
			)
			(2
				(smallBub setCel: 0)
				(self changeState: 0)
			)
		)
	)
)

(instance bigBub of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 96
			setLoop: 0
			setCel: 0
			setPri: 2
			setScript: BigBubble
			ignoreActors: 1
		)
	)
)

(instance smallBub of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 96
			setLoop: 1
			setCel: 0
			setPri: 2
			setScript: SmallBubble
			ignoreActors: 1
		)
	)
)
