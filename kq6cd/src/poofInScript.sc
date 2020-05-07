;;; Sierra Script 1.0 - (do not remove this comment)
(script# 89)
(include sci.sh)
(use Main)
(use Scaler)
(use Sound)
(use Motion)
(use System)

(public
	poofInScript 0
)

(instance poofInScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 15)
			)
			(1
				(theGame handsOff:)
				(if (not (ego x?)) (ego posn: 200 127))
				(localMusic2 loop: 1 number: 947 play:)
				(ego
					view: 207
					init:
					cycleSpeed: 10
					normal: 0
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(2
				(ego
					normal: 0
					cycleSpeed: 10
					view: 207
					setLoop: 2
					cel: 0
					lastCel:
					setCycle: Beg self
				)
			)
			(3
				(theGame handsOn:)
				(ego reset:)
				(localMusic2 stop: dispose:)
				(self dispose:)
				(if (== curRoomNum 450)
					(ego setScale: Scaler 100 30 126 70)
				)
				(DisposeScript 89)
			)
		)
	)
)

(instance localMusic2 of Sound
	(properties)
)
