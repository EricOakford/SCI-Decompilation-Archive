;;; Sierra Script 1.0 - (do not remove this comment)
(script# 908)
(include sci.sh)
(use Main)
(use LoadMany)
(use Motion)
(use System)

(public
	eggScript 0
)

(local
	egoCel
)
(instance eggScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 1 19 0 1 self 0)
			)
			(1 (= seconds 3))
			(2
				(= egoCel (ego cel?))
				(ego
					normal: 0
					view: 907
					cel: 0
					setLoop: 0
					cycleSpeed: 10
					setCycle: End self
				)
			)
			(3
				(messager say: 1 19 0 2 self 0)
			)
			(4
				(messager say: 1 19 0 3 self 0)
			)
			(5
				(ego put: 10 reset: egoCel)
				(theGame handsOn:)
				(self dispose:)
				(LoadMany 0 908)
			)
		)
	)
)
