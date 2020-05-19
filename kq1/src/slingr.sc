;;; Sierra Script 1.0 - (do not remove this comment)
(script# 784)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use System)

(public
	slingr 0
)

(instance slingr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego cel: 0 cycleSpeed: 1)
				(if (Btst fLittleEgo)
					(ego
						view: 40
						setCycle: CycleTo (if (< (ego loop?) 2) 7 else 5) 1 self
					)
				else
					(ego view: 41 setCycle: CycleTo 5 1 self)
				)
			)
			(1
				(slingSound init: play:)
				(if (Btst fLittleEgo)
					(ego
						cel: (if (< (ego loop?) 2) 8 else 6)
						setCycle: EndLoop
					)
				else
					(ego setCycle: EndLoop)
				)
				(= cycles 15)
			)
			(2
				(if
					(and
						(<= 56 curRoomNum)
						(<= curRoomNum 72)
						(not (OneOf curRoomNum 63 65))
					)
					(Print 784 0)
				else
					(Print 784 1)
				)
				(if numPebbles (PebbleCount))
				(HandsOn)
				(NormalEgo)
				(self dispose:)
			)
		)
	)
)

(instance slingSound of Sound
	(properties
		number 19
		priority 10
	)
)
