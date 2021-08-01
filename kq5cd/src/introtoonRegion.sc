;;; Sierra Script 1.0 - (do not remove this comment)
(script# 769)
(include sci.sh)
(use Main)
(use Game)
(use System)

(public
	introtoonRegion 0
)

(instance introtoonRegion of Rgn
	(properties)
	
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
			(event claimed: 1)
			(DoAudio 3)
			(theGame restart: 0)
		)
	)
)

(instance myMouseDownHandler of EventHandler
	(properties)
)

(instance myKeyDownHandler of EventHandler
	(properties)
)
