;;; Sierra Script 1.0 - (do not remove this comment)
(script# 818)
(include sci.sh)
(use Main)
(use System)


(class DScript of Script
	(properties
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		save1 0
		DTimer 10
		counter 0
		clrByKey 1
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
		(if clrByKey
			(cond 
				((and save1 (<= counter 0))
					(event claimed: 1)
					(self restore:)
					(= seconds 0)
					(= cycles 1)
				)
				(save1 (event claimed: 1))
				((super handleEvent: event))
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (restore)
		(if save1
			(Display 818 0 108 save1)
			(= save1 0)
			(= counter DTimer)
		)
	)
)
