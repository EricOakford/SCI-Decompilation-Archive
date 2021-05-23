;;; Sierra Script 1.0 - (do not remove this comment)
(script# 207)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	fenceReg 0
)

(instance fenceReg of Region
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '/fence')
								(Print 207 0)
							)
							((Said '/archway')
								(cond 
									((or (== curRoomNum 28) (== curRoomNum 1))
										(Print 207 1)
									)
									((== curRoomNum 26)
										(Print 207 2)
									)
									(else
										(event claimed: FALSE)
									)
								)
							)
							((Said '/road')
								(Print 207 3)
							)
						)
					)
					((Said 'open/archway')
						(cond 
							((or (== curRoomNum 28) (== curRoomNum 1))
								(Print 207 4)
							)
							((== curRoomNum 26)
								(Print 207 2)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'close/archway')
						(cond 
							((or (== curRoomNum 28) (== curRoomNum 1))
								(Print 207 5)
							)
							((== curRoomNum 26)
								(Print 207 2)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'climb/fence')
						(Print 207 6)
					)
					((Said 'archway')
						(Print 207 7)
					)
				)
			else
				FALSE
			)
		)
	)
)
