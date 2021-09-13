;;; Sierra Script 1.0 - (do not remove this comment)
(script# 19)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm19 0
)

(local
	[local0 2]
)
(instance rm19 of Room
	(properties
		picture 19
		horizon 106
		north 15
		south 23
		west 23
	)
	
	(method (init)
		(Load VIEW 254)
		(super init:)
		(addToPics add: aView1 doit:)
		(aChairlift
			setLoop: 0
			setCycle: Forward
			cycleSpeed: 2
			isExtra: 1
			init:
		)
		(aRollerCoaster
			setLoop: 3
			setCel: 255
			init:
			hide:
			setScript: coasterScript
		)
		(aFlags
			setLoop: 4
			setCycle: Forward
			cycleSpeed: 4
			isExtra: 1
			init:
		)
		(aMonorail
			setLoop: 1
			setPri: 2
			setStep: 4 1
			ignoreHorizon:
			illegalBits: 0
			init:
			setScript: monoScript
		)
		(cond 
			((== prevRoomNum 0)
				(ego posn: 318 160)
			)
			((== prevRoomNum 15)
				(ego posn: 306 108)
			)
			((== prevRoomNum 20)
				(ego posn: 318 160)
			)
			((== prevRoomNum 23)
				(ego posn: 5 187)
			)
		)
		(NormalEgo)
		(ego init:)
		(self setRegions: CITY setScript: rm19Script)
	)
)

(instance rm19Script of Script
	(method (doit)
		(super doit:)
		(if (== EAST (ego edgeHit?))
			(if (< (ego y?) 159)
				(curRoom newRoom: 15)
			else
				(curRoom newRoom: 20)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look<over,below/brick,fence')
			(Print 19 0)
		)
		(if (Said '/brick,fence')
			(Print 19 1)
		)
		(if (Said 'look[/airport]')
			(Print 19 2)
			(Print 19 3)
		)
	)
)

(instance monoScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 21))
			)
			(1
				(aMonorail posn: -59 52 setMotion: MoveTo 380 52 self)
			)
			(2
				(aMonorail posn: -945 52)
				(self changeState: 0)
			)
		)
	)
)

(instance coasterScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 2 10))
			)
			(1
				(aRollerCoaster cel: 0 show: setCycle: EndLoop self)
			)
			(2
				(aRollerCoaster hide:)
				(self changeState: 0)
			)
		)
	)
)

(instance aView1 of PicView
	(properties
		y 31
		x 144
		view 254
		loop 2
	)
)

(instance aChairlift of Prop
	(properties
		y 15
		x 60
		view 254
	)
)

(instance aRollerCoaster of Prop
	(properties
		y 13
		x 149
		view 254
	)
)

(instance aFlags of Prop
	(properties
		y 14
		x 239
		view 254
	)
)

(instance aMonorail of Actor
	(properties
		y 52
		x -940
		view 254
		signal ignrAct
	)
)
