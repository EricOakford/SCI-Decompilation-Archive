;;; Sierra Script 1.0 - (do not remove this comment)
(script# AUTODOOR)
(include game.sh)
(use Main)
(use Door)
(use Motion)


(class AutoDoor of Door
	(method (init)
		(super init:)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(code
				(if (code doit: self)
					(self open:)
				else
					(self close:)
				)
			)
			((& (ego onControl:) doorCtrl)
				(self open:)
			)
			(else
				(self close:)
			)
		)
	)
	
	(method (open)
		(if (and (not locked) (!= doorState doorOpening) (!= doorState doorOpen))
			(= doorState doorOpening)
			(self setCycle: EndLoop self)
			(if openSnd
				(openSnd play:)
			)
		)
	)
	
	(method (close)
		(if (and (!= doorState doorClosing) (!= doorState doorClosed))
			(= doorState doorClosing)
			(self setCycle: BegLoop self)
			(if closeSnd
				(closeSnd play:)
			)
		)
	)
)
