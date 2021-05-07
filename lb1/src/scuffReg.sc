;;; Sierra Script 1.0 - (do not remove this comment)
(script# 401)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)

(public
	scuffReg 0
)

(local
	cuedIt
)
(instance scuffReg of Region
	
	(method (init)
		(super init:)
		(if
			(or
				(< prevRoomNum 31)
				(and (< prevRoomNum 41) (> curRoomNum 41))
			)
			(= cuedIt TRUE)
		)
		(self setScript: scuffing)
	)
	
	(method (dispose)
		(super dispose:)
	)
)

(instance scuffing of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(if cuedIt
					(= cycles 1)
					(= cuedIt FALSE)
				else
					(= seconds (Random 18 25))
				)
			)
			(1
				(= cycles 1)
			)
			(2
				(if (!= [global368 0] 1)
					(cond 
						((< curRoomNum 40)
							(Print 401 0)
						)
						((< curRoomNum 74)
							(Print 401 1)
						)
					)
				)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)
