;;; Sierra Script 1.0 - (do not remove this comment)
(script# DELAYEVT)
(include game.sh)
(use Approach)
(use Actor)
(use User)
(use System)

;NOTE: This script has been commented out, as it is
; incompatible with SCI1.0.

;;;(class DelayedEvent of Event
;;;	(properties
;;;		client NULL
;;;	)
;;;	
;;;	(method (new whoCares optEvnt &tmp event)
;;;		(= event (super new:))
;;;		(if argc
;;;			(event client: whoCares)
;;;			(if (>= argc 2)
;;;				(event
;;;					type: (optEvnt type?)
;;;					message: (optEvnt message?)
;;;					modifiers: (optEvnt modifiers?)
;;;					y: (optEvnt y?)
;;;					x: (optEvnt x?)
;;;					claimed: (optEvnt claimed?)
;;;				)
;;;			else
;;;				(GetEvent allEvents event)
;;;			)
;;;		)
;;;		(return event)
;;;	)
;;;	
;;;	(method (cue)
;;;		(if client (client handleEvent: self))
;;;		(self dispose:)
;;;	)
;;;)
;;;
;;;(class GoToDlyEvt of DelayedEvent
;;;	
;;;	(method (new)
;;;;;;		(= b-i1 (User description?))
;;;;;;		(= b-i2 (User noun?))
;;;		(User canInput: FALSE canControl: FALSE)
;;;		(super new: &rest)
;;;	)
;;;	
;;;	(method (cue)
;;;		(User canInput: TRUE canControl: TRUE)
;;;		(Parse (User inputLineAddr?) self)
;;;		(super cue: &rest)
;;;	)
;;;)
;;;