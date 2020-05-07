;;; Sierra Script 1.0 - (do not remove this comment)
(script# CARTOON) ;910
(include game.sh)
(use Main)
(use System)


(class CartoonScript of Script
	(properties
		countDown 0
		time 10
	)
	
	(method (init)
		(= countDown time)
		(super init: &rest)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit)
		(if modelessDialog
			(user canInput: TRUE)
			(if (> countDown 0) (-- countDown))
		)
		(super doit:)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				modelessDialog
				(or
					(and
						(== (event type?) keyDown)
						(== (event message?) ENTER)
					)
					(== (event type?) mouseDown)
				)
			)
			(event claimed: TRUE)
			(if (not countDown)
				(user canInput: FALSE)
				(modelessDialog seconds: 0 dispose:)
				(= countDown time)
			)
		)
		(return TRUE)
	)
)
