;;; Sierra Script 1.0 - (do not remove this comment)
(script# ANIMATION)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use User)
(use Actor)


(class Animation of Prop
	(properties
		anAudio 0
		whichCyc 0
		noLoops 0
		lastView 0
		aSound 0
	)
	
	(method (init)
		(super init:)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
	)
	
	(method (dispose)
		(if whichCyc (whichCyc dispose:))
		(if aSound (aSound dispose:) (= aSound 0))
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
		(and (self onMe: event) (== (User canInput:) 1))
			(theGame handsOff:)
			(event claimed: 1)
			(if whichCyc
				(self setCycle: whichCyc lastView noLoops self)
			else
				(self setCycle: EndLoop self)
			)
			(if anAudio
				(if aSound
					(aSound play:)
				else
					(= aSound
						((Sound new:) number: anAudio play: yourself:)
					)
				)
			)
		else
			(super handleEvent: event)
		)
	)
	
	(method (cue)
		(self stopUpd:)
	)
)
