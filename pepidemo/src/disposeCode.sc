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
			RANDCYC TIMER DPATH DIALOG FILE CONV JUMP
			FORCOUNT INSET MOVEFWD OSC SCALER RANDCYC 23 876
			tlkPepper tlkLockjaw tlkPugh tlkRichard tlkIma tlkPercy
			892 878 891 877 875 838 FOLLOW 874
			DCICON REVERSE
		)
		(DisposeScript DISPOSE)
	)
)
