;;; Sierra Script 1.0 - (do not remove this comment)
(script# 205)
(include game.sh)
(use Intrface)
(use Game)

(public
	swampReg 0
)
(synonyms
	(bayou water)
	(get capture)
)

(instance swampReg of Region
	(properties)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					(
						(or
							(Said '/fish,alligator,turtle,frog>')
							(Said '//fish,alligator,turtle,frog>')
						)
						(event claimed: TRUE)
						(Print 205 0)
					)
					((Said 'examine>')
						(cond 
							((Said '[<around,at][/room,bayou,bayou]')
								(Print 205 1)
							)
							((Said '/oak')
								(Print 205 2)
							)
							((Said '/foliage')
								(Print 205 3)
							)
							((Said '/blossom')
								(Print 205 4)
							)
							((Said '/bush')
								(Print 205 5)
							)
							((Said '/moss')
								(Print 205 6)
							)
							((Said '/fog')
								(Print 205 7)
							)
						)
					)
					(
						(or
							(Said 'bathe,dive,wade')
							(Said 'cross,enter,go,hop,dive,(get<in)/bayou,brook')
						)
						(Print 205 8)
					)
					(
						(or
							(Said 'drink[/bayou[<bayou]]')
							(Said 'get/drink[/bayou]')
						)
						(Print 205 9)
					)
					((Said 'get>')
						(cond 
							((Said '/foliage')
								(Print 205 10)
							)
							((Said '/blossom')
								(Print 205 11)
							)
							((Said '/moss[<oak]')
								(Print 205 12)
							)
							((Said '/weed')
								(Print 205 13)
							)
							((Said '/bayou')
								(Print 205 14)
							)
						)
					)
					((Said 'climb/oak')
						(Print 205 15)
					)
				)
			else
				FALSE
			)
		)
	)
)
