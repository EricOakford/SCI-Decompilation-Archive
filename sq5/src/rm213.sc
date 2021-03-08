;;; Sierra Script 1.0 - (do not remove this comment)
(script# 213)
(include sci.sh)
(use Main)
(use eureka)
(use Osc)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm213 0
)

(instance rm213 of Rm
	(properties
		picture 131
		style $000a
	)
	
	(method (init)
		(curRoom setRegions: 210)
		(eurekaShip init: cel: 0)
		(blob init: setCycle: Fwd)
		(eureka damaged: 1)
		(curRoom setScript: sSuckBlob)
		(super init:)
	)
)

(instance sSuckBlob of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 3))
			(1
				(eurekaShip setCycle: End self)
			)
			(2
				(beatrbar init: setCycle: Fwd)
				(thruster init: setCycle: Osc)
				(= ticks 90)
			)
			(3
				(blob
					setLoop: 1
					cel: 0
					x: 84
					y: 36
					cycleSpeed: 15
					setCycle: End self
				)
				(theMusic1 number: 228 setLoop: -1 play:)
			)
			(4
				(curRoom newRoom: 201)
				(self dispose:)
			)
		)
	)
)

(instance eurekaShip of Prop
	(properties
		x 18
		y 38
		view 710
		loop 2
		cel 2
		priority 4
		signal $4010
	)
)

(instance blob of Prop
	(properties
		x 157
		y 19
		view 710
		cel 4
		priority 5
		signal $4010
		cycleSpeed 10
	)
	
	(method (init)
		(theMusic1 number: 249 setLoop: -1 play:)
		(super init: &rest)
	)
)

(instance thruster of Prop
	(properties
		x 15
		y 52
		view 710
		loop 3
		cel 1
		priority 5
		signal $4010
	)
)

(instance beatrbar of Prop
	(properties
		x 38
		y 23
		view 710
		loop 4
		cel 3
		priority 5
		signal $4010
		cycleSpeed 2
	)
)
