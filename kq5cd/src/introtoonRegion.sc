;;; Sierra Script 1.0 - (do not remove this comment)
(script# 769)
(include game.sh)
(use Main)
(use Game)
(use System)

(public
	introtoonRegion 0
)

(instance introtoonRegion of Region
	
	(method (init)
		((= mouseDownHandler myMouseDownHandler)
			addToFront: self
		)
		((= keyDownHandler myKeyDownHandler) addToFront: self)
		(super init: &rest)
	)
	
	(method (dispose)
		(myMouseDownHandler release: dispose:)
		(myKeyDownHandler release: dispose:)
		(= mouseDownHandler (= keyDownHandler 0))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(if (event type?)
			(event claimed: TRUE)
			(DoAudio Stop)
			(theGame restart: FALSE)
		)
	)
)

(instance myMouseDownHandler of EventHandler)

(instance myKeyDownHandler of EventHandler)
