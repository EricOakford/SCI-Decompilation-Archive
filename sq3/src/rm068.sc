;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm068 0
)

(instance rm068 of Room
	(properties
		picture 68
		horizon 85
		north 64
		west 67
	)
	
	(method (init &tmp [temp0 50])
		(HandsOn)
		(self setRegions: ORTEGA)
		(Load VIEW 90)
		(spew1 init:)
		(super init:)
		(TheMenuBar draw:)
		(StatusLine enable:)
		(switch prevRoomNum
			(64 (ego init:))
			(67
				(ego
					posn: 2 (if (< (ego y?) 87) 87 else (ego y?))
					init:
				)
				(ego posn: 2 (if (> (ego y?) 185) 185 else (ego y?)))
			)
		)
	)
	
	(method (doit &tmp egoOnControl)
		(super doit:)
		(if (== (curRoom script?) 0)
			(cond 
				(
					(or
						(== (= egoOnControl (ego onControl:)) 3)
						(== egoOnControl 2)
					)
					(ego setPri: 7 posn: (ego x?) (+ (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 5) (== egoOnControl 7))
					(ego setPri: 5 posn: (+ (ego x?) 12) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 9) (== egoOnControl 13))
					(ego setPri: 3 posn: (+ (ego x?) 10) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 17) (== egoOnControl 49))
					(ego setPri: 7 posn: (ego x?) (- (ego y?) 2))
					(= fallingIntoLava TRUE)
				)
				((== egoOnControl 33)
					(ego setPri: 9 posn: (ego x?) (+ (ego y?) 2))
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
	)
	
	(method (newRoom newRoomNumber)
		(if (== script 0)
			(if forceBeamDestroyed (++ ortegaPostBeamRooms))
			(super newRoom: newRoomNumber)
		)
	)
)

(instance SpewScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1 (spew1 setCycle: EndLoop self))
			(2
				(spew2 init: setCycle: EndLoop self)
				(spew1 setCel: 0)
			)
			(3
				(spew2 dispose:)
				(self changeState: 0)
			)
		)
	)
)

(instance spew1 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 90
			setLoop: 3
			setCel: 0
			cycleSpeed: 1
			posn: 90 21
			setScript: SpewScript
			ignoreActors:
		)
	)
)

(instance spew2 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 90
			setLoop: 4
			setCel: 0
			cycleSpeed: 1
			posn: 174 0
			ignoreActors:
		)
	)
)
