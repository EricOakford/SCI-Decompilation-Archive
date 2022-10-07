;;; Sierra Script 1.0 - (do not remove this comment)
(script# 108)
(include sci.sh)
(use Main)
(use KQ6Room)
(use System)

(public
	rm108 0
)

(instance rm108 of KQ6Room
	(properties
		picture 98
		autoLoad 0
	)
	
	(method (init)
		(theGame handsOff:)
		(theIconBar disable: height: -100 activateHeight: -100)
		(directionHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(super init: &rest)
		(self setScript: sShowMovieWin)
	)
	
	(method (handleEvent)
		(ShowMovie 3)
		(ShowMovie 6)
		(theIconBar disable: height: 0 activateHeight: 0)
		(theGame restart: 1)
	)
	
	(method (newRoom)
		(directionHandler delete: self)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(theIconBar disable: height: 0 activateHeight: 0)
		(super newRoom: &rest)
	)
)

(instance sShowMovieWin of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 10))
			(1
				(if (not (ShowMovie 0 {toon.avi}))
					(ShowMovie 1 0 0)
					(ShowMovie 2 0 self)
				else
					(= cycles 1)
				)
			)
			(2 (ShowMovie 6) (= cycles 1))
			(3 (curRoom newRoom: 200))
		)
	)
)
