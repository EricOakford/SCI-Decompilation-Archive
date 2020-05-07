;;; Sierra Script 1.0 - (do not remove this comment)
(script# CASTLE) ;807
(include game.sh)
(use Main)
(use Game)

(public
	Castle 0
)

(local
	flagstoneMessage
)
(instance Castle of Locale
	(properties)
	
	(method (handleEvent event &tmp [temp0 54])
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((super handleEvent: event))
					((Said 'look>')
						(cond 
							((Said '/castle') (HighPrint 807 0))
							((Said '/tower') (HighPrint 807 1))
							((Said '/gate,gatehouse') (HighPrint 807 2))
							((Said '/portcullis') (HighPrint 807 3))
							((Said '/wall,parapet,parapet,aisle') (HighPrint 807 4))
							((Said '/courtyard') (HighPrint 807 5))
							((Said '/flagstone')
								(switch flagstoneMessage
									(0
										(HighPrint 807 6)
										(++ flagstoneMessage)
									)
									(1
										(HighPrint 807 7)
										(++ flagstoneMessage)
									)
									(2
										(HighPrint 807 8)
										(++ flagstoneMessage)
									)
									(3
										(HighPrint 807 9)
										(++ flagstoneMessage)
									)
									(4
										(HighPrint 807 10)
										(= flagstoneMessage 0)
									)
								)
							)
						)
					)
				)
			)
		)
	)
)
