;;; Sierra Script 1.0 - (do not remove this comment)
(script# 70)
(include game.sh)
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

(local
	aBigEgo
	aBigEgoBottom
	aSparkle
)
(instance theSound of Sound
	(properties
		number 1
	)
)

(instance rm70 of Room
	(properties
		picture 70
		horizon 1
	)
	
	(method (init)
		(Load VIEW 170)
		(Load SOUND 1)
		(theSound init:)
		(super init:)
		((= aSparkle (Prop new:))
			view: 170
			setLoop: 3
			setPri: 15
			posn: 120 123
			init:
			hide:
		)
		((= aBigEgo (Actor new:))
			view: 170
			posn: 120 103
			setPri: 14
			illegalBits: 0
			init:
		)
		((= aBigEgoBottom (View new:))
			view: 170
			cel: 1
			posn: 120 133
			init:
		)
		(ego setMotion: 0)
		(User canControl: FALSE canInput: TRUE)
		(= currentStatus egoSTUCKINTREE)
		(self setRegions: ISLAND setScript: rm70Script)
	)
)

(instance rm70Script of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not (ego has: iKnife))
					(= seconds 10)
				)
			)
			(1
				(Print 70 8)
				(Print 70 9)
				(= seconds 4)
			)
			(2
				(Print 70 10)
				(Print 70 11)
				(= currentStatus egoDYING)
			)
			(3
				(User canInput: FALSE)
				(aBigEgo cycleSpeed: 2 setLoop: 1 setCycle: EndLoop self)
			)
			(4
				(aBigEgo cycleSpeed: 1 setLoop: 4 setCycle: Forward)
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
					setCycle: EndLoop
					setMotion: MoveTo (aBigEgo x?) 255 self
				)
			)
			(6
				(aSparkle show: setCycle: EndLoop self)
			)
			(7
				(aSparkle dispose:)
				(curRoom newRoom: 71)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(if (Said 'look>')
			(if (Said '/parachute')
				(Print 70 0)
			)
			(if (Said '[/airport,branch,palm,forest,bush]')
				(Print 70 1)
				(Print 70 2)
			)
		)
		(if (Said 'swing')
			(Print 70 3)
		)
		(if (Said 'drain,(get<off),free,free,open,jerk/cord,parachute,buckle')
			(Print 70 4)
		)
		(if (Said 'hop,carry,climb')
			(Print 70 5)
		)
		(if
			(or
				(Said 'apply/knife')
				(Said 'cut/parachute,buckle,bathing')
			)
			(Print 70 6)
			(if (ego has: iKnife)
				(self changeState: 3)
			else
				(Print 70 7)
			)
		)
	)
)
