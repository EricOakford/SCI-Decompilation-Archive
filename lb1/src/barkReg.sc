;;; Sierra Script 1.0 - (do not remove this comment)
(script# 400)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Game)
(use System)

(public
	barkReg 0
)

(instance barkReg of Region
	
	(method (init)
		(super init:)
		(self setScript: barking)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance barking of Script

	(method (changeState newState &tmp oldPause)
		(switch (= state newState)
			(0
				(= seconds (Random 18 30))
			)
			(1
				(= cycles 1)
			)
			(2
				(if (!= curRoomNum 16)
					(if (== curRoomNum 23)
						(= oldPause (Sound pause: TRUE))
					)
					(Print 400 0)
					(if (== curRoomNum 23)
						(Sound pause: oldPause)
					)
				)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)
