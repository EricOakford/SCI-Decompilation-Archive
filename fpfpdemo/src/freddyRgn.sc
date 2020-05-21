;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgFreddy) ;90
(include game.sh)
(use Main)
(use Timer)
(use Game)
(use System)

(public
	freddyRgn 0
)

(instance freddyRgn of Region
	
	(method (init)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(eventTimer setReal: eventTimer 120)
		(super init:)
	)
	
	(method (handleEvent)
		(eventTimer setReal: eventTimer 120)
		(if timeOver
			(= timeOver FALSE)
			(curRoom newRoom: 105)
		)
		(super handleEvent: Event)
	)
	
	(method (newRoom n)
		(= keep (OneOf n 220 230 250 270 600 670))
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(= initialized FALSE)
	)
)

(instance eventTimer of Timer
	
	(method (setReal)
		(freddyRgn timer: self)
		(super setReal: &rest)
	)
	
	(method (cue)
		(= timeOver TRUE)
		(curRoom newRoom: 105)
	)
)
