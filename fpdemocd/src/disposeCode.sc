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
			tlkFreddy tlkSrini tlkDoc tlkSamAndreas tlkSmithie tlkSheriff
			tlkWilly tlkBilly tlkAl
			DOOR INSET FILE FPCONTROLS SLIDEICON PAVOID PCHASE SCALER DPATH
			30 919
			PFOLLOW SCALETO MOVEFWD FORCOUNT MOVECYC REVERSE RANDCYC
			OCC_CYC RANGEOSC WRITEFTR
		)
		(DisposeScript DISPOSE)
	)
)
