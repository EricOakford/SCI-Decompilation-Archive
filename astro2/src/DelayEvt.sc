;;; Sierra Script 1.0 - (do not remove this comment)
(script# DELAYEVT)
(include game.sh)
(use Approach)
(use Actor)
(use User)
(use System)


(class DelayedEvent kindof Event
	(properties
		client	NULL
	)
;;;	(methods
;;;		cue
;;;	)

	(method (new whoCares optEvnt &tmp event)
		(= event (super new:))
		(if argc
			(event client: whoCares)
			(if (>= argc 2)
				(event 
					type: 		(optEvnt type?),		
					message: 	(optEvnt message?),	
					modifiers: 	(optEvnt modifiers?),
					y: 			(optEvnt y?),			
					x: 			(optEvnt x?),			
					claimed: 	(optEvnt claimed?)	
				)
			else
				(GetEvent allEvents event)
			)
		)
		(return event)
	)

	(method (cue)
		(if client (client handleEvent: self))
		(self	dispose:)
	)
)


(class GoToDlyEvt of  DelayedEvent
	(properties
		uCanControl	FALSE
		uCanInput	FALSE
	)

	(method (new)
		(= uCanControl	(User controls?))
		(= uCanInput	(User input?))
		(User 
			canControl:FALSE, 
			canInput:FALSE
		)
		(super new:&rest)
	)

	(method (cue)
		(User 
			canControl:	uCanControl,
			canInput:	uCanInput
		)
		(Parse (User inputLineAddr?) self)
		(super cue:&rest)
	)
)