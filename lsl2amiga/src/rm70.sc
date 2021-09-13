;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm70 0
)

(instance theSound of Sound
	(properties
		number 1
	)
)

(instance rm70 of Rm
	(properties
		picture 70
		horizon 1
	)
	
	(method (init)
		(Load rsVIEW 170)
		(Load rsSOUND 1)
		(theSound init:)
		(super init:)
		(aSparkle setPri: 15 init: hide:)
		(aBigEgo setPri: 14 illegalBits: 0 init:)
		(aBigEgoBottom init:)
		(ego setMotion: 0)
		(User canControl: 0 canInput: 1)
		(= currentStatus 20)
		(self setRegions: 700 setScript: rm70Script)
	)
)

(instance rm70Script of Script
	(properties)
	
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (ego has: 17)) (= seconds 10))
			)
			(1
				(Print 70 8)
				(Print 70 9)
				(= seconds 4)
			)
			(2
				(Print 70 10)
				(Print 70 11)
				(= currentStatus 1001)
			)
			(3
				(User canInput: 0)
				(aBigEgo cycleSpeed: 2 setLoop: 1 setCycle: End self)
			)
			(4
				(aBigEgo cycleSpeed: 1 setLoop: 4 setCycle: Fwd)
				(= cycles 40)
			)
			(5
				(theGame changeScore: 8)
				(Print 70 12 #at -1 15 #width 280)
				(Print 70 13 #at -1 20)
				(aBigEgoBottom dispose:)
				(theSound play:)
				(aBigEgo
					setStep: 1 10
					setLoop: 2
					cel: 0
					setCycle: End
					setMotion: MoveTo (aBigEgo x?) 255 self
				)
			)
			(6
				(aSparkle show: setCycle: End self)
			)
			(7
				(aSparkle dispose:)
				(curRoom newRoom: 71)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(or (!= (event type?) evSAID) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/parachute') (Print 70 0))
			(if (Said '[/airport,branch,palm,forest,bush]')
				(Print 70 1)
				(Print 70 2)
			)
		)
		(if (Said 'swing') (Print 70 3))
		(if
			(Said
				'drain,(get<off),free,free,open,jerk/cord,parachute,buckle'
			)
			(Print 70 4)
		)
		(if (Said 'hop,carry,climb') (Print 70 5))
		(if
			(or
				(Said 'apply/gun')
				(Said 'cut/parachute,buckle,bathing')
			)
			(Print 70 6)
			(if (ego has: 17)
				(self changeState: 3)
			else
				(Print 70 7)
			)
		)
	)
)

(instance aBigEgoBottom of View
	(properties
		y 133
		x 120
		view 170
		cel 1
	)
)

(instance aSparkle of Prop
	(properties
		y 123
		x 120
		view 170
		loop 3
	)
)

(instance aBigEgo of Act
	(properties
		y 103
		x 120
		view 170
	)
)
