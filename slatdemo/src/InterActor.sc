;;; Sierra Script 1.0 - (do not remove this comment)
(script# INTER_ACTOR)
(include game.sh)
(use Main)
(use User)
(use Actor)


(class InterActor of Actor
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler addToEnd: self)
		(keyDownHandler addToEnd: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(if (and (== (User canInput:) TRUE) (self onMe: event))
			(self doVerb:)
		else
			(super handleEvent: event)
		)
	)
)
