;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSE)
(include game.sh)
(use LoadMany)
(use System)

(public
	disposeCode 0
)

(instance disposeCode of Code
	(properties)
	
	(method (doit)
		(LoadMany FALSE
			tlkPaBird tlkMaBird
			INSET FILE CONV GCONTROL SLIDEICON PAVOID PCHASE
			SCALER SCALETO DPATH TUTORIAL MOVEFWD OSC
			FORCOUNT MOVECYC RANDCYC REVERSE WRITEFTR
			DEBUG
		)
		(DisposeScript DISPOSE)
	)
)
