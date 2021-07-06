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
			JUMP CHASE WANDER PCHASE OSC NAMEFIND
			REVERSE SORT COUNT DPATH FORCOUNT MOVEFWD
			RANDCYC STOPWALK MOVECYC FILE LLROOM PRINTD
			WRITEFTR BLOCK
		)
		(DisposeScript DEBUG)
		(DisposeScript DISPOSE)
	)
)
