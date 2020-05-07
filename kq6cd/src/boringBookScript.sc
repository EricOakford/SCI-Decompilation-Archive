;;; Sierra Script 1.0 - (do not remove this comment)
(script# 88)
(include sci.sh)
(use Main)
(use LoadMany)
(use Sound)
(use Motion)
(use System)

(public
	boringBookScript 0
)

(instance boringBookScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= seconds 2)
			)
			(1
				(ego
					normal: 0
					view: 903
					cel: 0
					setLoop: 2
					cycleSpeed: 5
					setCycle: End self
				)
			)
			(2
				(ego cel: 0 setLoop: 0 setCycle: End self)
			)
			(3
				(ego setLoop: 1 setCycle: Fwd)
				(= seconds 4)
			)
			(4
				(messager say: 1 42 0 1 self 0)
			)
			(5
				(messager say: 1 42 0 2 self 0)
			)
			(6
				(ego setLoop: 2 lastCel: setCycle: Beg self)
			)
			(7 (= seconds 1))
			(8
				(localMusic loop: 1 number: 961 play:)
				(ego setLoop: 3 cycleSpeed: 10 setCycle: End self)
			)
			(9
				(ego setCel: 0 setCycle: CT 5 1 self)
			)
			(10 (= cycles 15))
			(11 (ego setCycle: Beg self))
			(12 (= cycles 15))
			(13
				(ego reset: 2)
				(= cycles 10)
			)
			(14
				(messager say: 1 42 0 3 self 0)
			)
			(15
				(messager say: 1 42 0 4 self 0)
			)
			(16
				(theGame handsOn:)
				(localMusic stop: dispose:)
				(self dispose:)
				(LoadMany 0 88)
			)
		)
	)
)

(instance localMusic of Sound
	(properties)
)
