;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm064 0
)

(instance rm064 of Room
	(properties
		picture 64
		south 68
		west 63
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
			(63
				(ego posn: 2 (ego y?) init:)
			)
			(68
				(ego posn: (ego x?) 187 init:)
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
					(ego setPri: 3 posn: (ego x?) (- (ego y?) 3))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 5) (== egoOnControl 7))
					(ego setPri: 3 posn: (+ (ego x?) 6) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 9) (== egoOnControl 13))
					(ego setPri: 5 posn: (+ (ego x?) 10) (ego y?))
					(= fallingIntoLava TRUE)
				)
				((or (== egoOnControl 17) (== egoOnControl 25))
					(ego setPri: 7 posn: (+ (ego x?) 6) (ego y?))
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
			posn: 112 21
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
			posn: 196 0
			ignoreActors:
		)
	)
)
