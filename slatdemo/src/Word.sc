;;; Sierra Script 1.0 - (do not remove this comment)
(script# WORD)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Actor)


(class Word of Prop
	(properties
		anAudio 0
		aSound 0
	)
	
	(method (init)
		(super init:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (self onMe: event)
			(= aSound
				((Sound new:) number: anAudio play: yourself:)
			)
			(event claimed: TRUE)
			(self aniDoit:)
		)
	)
	
	(method (cue)
		(self setCycle: BegLoop)
		(if aSound
			(aSound dispose:)
			(= aSound 0)
		)
	)
	
	(method (aniDoit)
		(self setCycle: EndLoop self)
	)
)
