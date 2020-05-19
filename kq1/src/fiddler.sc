;;; Sierra Script 1.0 - (do not remove this comment)
(script# 782)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use System)

(public
	fiddler 0
	fiddleSound 1
)

(instance fiddler of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(fiddleSound number: 42 loop: 1 init: play:)
				(ego
					view: (if (Btst fLittleEgo) 32 else 55)
					cycleSpeed: 1
					setCycle: Forward
				)
				(= seconds 2)
			)
			(1
				(Print 782 0)
				(= seconds 4)
			)
			(2
				(fiddleSound fade:)
				(cond 
					((== curRoomNum 79)
						(Print 782 1)
					)
					((and (== curRoomNum 58) (Btst fGiantTired))
						(Print 782 2)
					)
					(else
						(Print 782 3)
					)
				)
				(HandsOn)
				(NormalEgo)
				(ego loop: loopS)
				(self dispose:)
			)
		)
	)
)

(instance fiddleSound of Sound
	(properties
		priority 10
	)
)
