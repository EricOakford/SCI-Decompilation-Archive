;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSE)
(include game.sh)
(use LoadMany)
(use System)

(public
	disposeCode 0
)

(instance disposeCode of Code
	(method (doit)
		(LoadMany FALSE
			STOPWALK GCONTROL INSET DEBUG RANDCYC GKINSET FILE WRITEFTR
		)
		(DisposeScript DISPOSE)
	)
)
