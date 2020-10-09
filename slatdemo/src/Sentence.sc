;;; Sierra Script 1.0 - (do not remove this comment)
(script# SENTENCE)
(include game.sh)
(use Main)
(use Sound)
(use Actor)
(use System)


(class Sentence of EventHandler
	(properties
		view 0
		loop 0
		cel 0
		x 0
		y 0
		aSound 0
		anAudio 0
		aView 0
		signal stopUpdOn
	)
	
	(method (init)
		(= aView
			((View new:)
				view: view
				loop: loop
				cel: cel
				x: x
				y: y
				init:
				stopUpd:
				yourself:
			)
		)
		(keyDownHandler addToEnd: self)
		(mouseDownHandler addToEnd: self)
		(self eachElementDo: #init)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(if anAudio
			(anAudio dispose:)
			(= anAudio 0)
		)
		(aView dispose:)
		(= aView 0)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (aView onMe: event)
			(if anAudio
				(anAudio play:)
			else
				(= anAudio
					((Sound new:) number: aSound play: yourself:)
				)
			)
			(self eachElementDo: #aniDoit)
		else
			(event claimed: FALSE)
		)
	)
)
