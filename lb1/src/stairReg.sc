;;; Sierra Script 1.0 - (do not remove this comment)
(script# 211)
(include game.sh)
(use Main)
(use Intrface)
(use Game)

(public
	stairReg 0
)

(instance stairReg of Region
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(if (and (== (event type?) saidEvent) (Said 'examine>'))
			(cond 
				((Said '/stair')
					(cond 
						((== curRoomNum 37)
							(Print 211 0)
						)
						((== curRoomNum 76)
							(Print 211 1)
						)
						(else
							(Print 211 2)
						)
					)
				)
				((Said '/balcony')
					(Print 211 3)
				)
			)
		)
	)
)
