;;; Sierra Script 1.0 - (do not remove this comment)
(script# 213)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	fireReg 0
)

(instance fireReg of Region
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (== (event type?) saidEvent)
			(cond 
				((Said 'examine>')
					(cond 
						((Said '<in/fireplace')
							(switch curRoomNum
								(31
									(Print 213 0)
								)
								(44
									(Print 213 0)
								)
								(42
									(Print 213 1)
								)
								(else 
									(if (< currentAct 4)
										(Print 213 2)
									else
										(Print 213 1)
									)
								)
							)
						)
						((Said '/fireplace')
							(Print 213 3)
						)
						((Said '/fire')
							(cond 
								((or (== curRoomNum 32) (== curRoomNum 34))
									(if (< currentAct 4)
										(Print 213 2)
									else
										(Print 213 1)
									)
								)
								((== curRoomNum 42)
									(Print 213 1)
								)
								(else
									(event claimed: FALSE)
								)
							)
						)
						((Said '/mantel')
							(if (== curRoomNum 34)
								(Print 213 4)
							else
								(Print 213 5)
							)
						)
					)
				)
				((Said 'get/fire')
					(if (or (== curRoomNum 32) (== curRoomNum 34))
						(Print 213 6)
					else
						(Print 213 7)
					)
				)
			)
		)
	)
)
