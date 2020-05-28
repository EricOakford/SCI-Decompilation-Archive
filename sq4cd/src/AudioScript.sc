;;; Sierra Script 1.0 - (do not remove this comment)
(script# AUDSCRIPT);765
(include game.sh)
(use Main)
(use System)


(class AudioScript of Script
	(properties
		waitForCue 0
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				waitForCue
				theSync
				(u>= (& (theSync prevCue?) $fff0) (& waitForCue $fff0))
			)
			(= cycles 1)
			(= waitForCue 0)
		)
	)
)
