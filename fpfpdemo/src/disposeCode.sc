;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSE) ;11
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
			;talker scripts
			tlkFreddy tlkSrini tlkDoc tlkSamAndreas tlkSmithie tlkMadame tlkWilly
			
			;others
			DOOR INSET FILE FPCONTROLS SLIDEICON PAVOID PCHASE SCALER DPATH
			30 919 PFOLLOW SCALETO SWING MOVEFWD FORCOUNT MOVECYC REVERSE
			RANDCYC RANGEOSC WRITEFTR
		)
		(DisposeScript DISPOSE)
	)
)
