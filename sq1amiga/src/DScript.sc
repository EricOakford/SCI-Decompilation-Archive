;;; Sierra Script 1.0 - (do not remove this comment)
(script# DSCRIPT) ;818
(include game.sh)
(use Main)
(use System)


(class DScript of Script
	(properties
		save1 0
		DTimer 10
		counter 0
		clrByKey TRUE
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
		(if save1
			(-- counter)
		)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if clrByKey
			(cond 
				((and save1 (<= counter 0))
					(event claimed: TRUE)
					(self restore:)
					(= seconds 0)
					(= cycles 1)
				)
				(save1
					(event claimed: TRUE)
				)
				((super handleEvent: event))
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (restore)
		(if save1
			(Display 818 0 p_restore save1)
			(= save1 0)
			(= counter DTimer)
		)
	)
)
