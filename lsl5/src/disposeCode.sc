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
			DOOR PRINTD TALKER FILE MOVEFWD PCYCLE
			FORCOUNT MOVECYC REVERSE RANDCYC DIALER CHARGER WRITEFTR
		)
		(DisposeScript DISPOSE)
	)
)
