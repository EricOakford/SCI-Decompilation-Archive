;;; Sierra Script 1.0 - (do not remove this comment)
(script# ROBOT_MANAGER) ;810
(include game.sh)
(use Main)
(use System)


(class rmnScript of Script
	(properties
		save1 0
		DTimer 10
		counter 0
	)
	
	(method (init)
		(if (not (keyDownHandler contains: self))
			(keyDownHandler addToFront: self)
		)
		(if (not (mouseDownHandler contains: self))
			(mouseDownHandler addToFront: self)
		)
		(if (not (directionHandler contains: self))
			(directionHandler addToFront: self)
		)
		(= counter DTimer)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit:)
		(if save1 (-- counter))
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(cond 
			((and save1 (<= counter 0))
				(event claimed: TRUE)
				(self restore:)
				(= seconds 0)
				(= cycles 1)
			)
			(save1 (event claimed: TRUE))
			(else (super handleEvent: event))
		)
	)
	
	(method (restore)
		(if save1
			(Display 810 0 p_restore save1)
			(= save1 0)
			(= counter DTimer)
		)
	)
)
