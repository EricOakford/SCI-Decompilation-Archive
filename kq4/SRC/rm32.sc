;;; Sierra Script 1.0 - (do not remove this comment)
(script# 32)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)

(public
	Room32 0
)

(local
	ripple1
	ripple2
	ripple3
	ripple4
	palaceDoor
)
(instance Room32 of Room
	(properties
		picture 32
	)
	
	(method (init)
		(Load VIEW 9)
		(= north 40)
		(= west (= east (= south 31)))
		(= horizon 114)
		(super init:)
		(self setRegions: GULL OCEAN)
		(= ripple1 (Prop new:))
		(= ripple2 (Prop new:))
		(= ripple3 (Prop new:))
		(= ripple4 (Prop new:))
		((= palaceDoor (View new:))
			view: 613
			loop: 2
			cel: 0
			posn: 163 62
			setPri: 2
			init:
			stopUpd:
		)
		(ripple1
			view: 660
			loop: 0
			cel: 1
			posn: 79 81
			ignoreActors:
			setPri: 4
			setCycle: Forward
			cycleSpeed: 3
			init:
		)
		(ripple2
			view: 660
			loop: 1
			cel: 2
			posn: 118 92
			ignoreActors:
			setPri: 5
			setCycle: Forward
			cycleSpeed: 3
			init:
		)
		(ripple3
			view: 660
			loop: 2
			cel: 0
			posn: 214 93
			ignoreActors:
			setPri: 5
			setCycle: Forward
			cycleSpeed: 3
			init:
		)
		(ripple4
			view: 660
			loop: 3
			cel: 3
			posn: 266 86
			ignoreActors:
			setPri: 5
			setCycle: Forward
			cycleSpeed: 3
			init:
		)
		(if isNightTime (= picture 132))
		(if (>= (ego x?) 319) (ego x: 1))
		(if (<= (ego x?) 0) (ego x: 318))
		(switch prevRoomNum
			(41
				(ego posn: 226 (+ horizon 1))
			)
			(40
				(ego posn: 164 (+ horizon 1))
			)
			(39
				(ego posn: 100 (+ horizon 1))
			)
		)
		(if (< (ego y?) horizon)
			(ego y: (+ horizon (ego yStep?) 1))
		)
		(ego view: 9 setCycle: Forward init:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if
			(and (== (event type?) saidEvent) (Said 'look>'))
				(cond 
					((Said '/tamir') (Print 32 0))
					((Said '/island') (Print 32 1))
					((Said '/castle') (Print 32 2))
					((Said '/fish') (Print 32 3))
					((Said '[<around][/room]') (Print 32 4))
				)
			else
				FALSE
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (== (ego edgeHit?) NORTH)
			(cond 
				((< (ego x?) 115) (super newRoom: 39))
				((> (ego x?) 198) (super newRoom: 41))
				(else (super newRoom: 40))
			)
		else
			(super newRoom: newRoomNumber)
		)
	)
)
