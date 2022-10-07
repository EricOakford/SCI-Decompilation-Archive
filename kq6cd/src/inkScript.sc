;;; Sierra Script 1.0 - (do not remove this comment)
(script# 92)
(include sci.sh)
(use Main)
(use Kq6Procs)
(use LoadMany)
(use Motion)
(use System)

(public
	inkScript 0
)

(instance inkScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 151)
				(= cycles 1)
			)
			(1
				(messager say: 1 83 0 1 self 0)
			)
			(2 (= seconds 2))
			(3
				(ego
					normal: 0
					view: 906
					setCel: 0
					setLoop: 0
					cycleSpeed: 10
					setCycle: CycleTo 4 1 self
				)
			)
			(4
				(messager say: 1 83 0 2 self 0)
			)
			(5 (ego setCycle: EndLoop self))
			(6
				(messager say: 1 83 0 3 self 0)
			)
			(7
				(ego setCel: 0 setLoop: 1)
				(= seconds 4)
			)
			(8
				(messager say: 1 83 0 4 self 0)
			)
			(9 (= seconds 2))
			(10 (ego setCycle: EndLoop self))
			(11
				(messager say: 1 83 0 5 self 0)
			)
			(12
				(Bset 116)
				(theGame handsOn:)
				(ego reset: 2)
				(self dispose:)
				(LoadMany 0 92)
			)
		)
	)
)
