;;; Sierra Script 1.0 - (do not remove this comment)
(script# DISPOSE) ;11
(include game.sh)
(use Main)
(use LoadMany)
(use System)

(public
	disposeCode 0
)

(instance disposeCode of Code
	(properties)
	
	(method (doit)
		(LoadMany FALSE
			tlkRoger 1881 tlkBea tlkQuirk tlkCliffy tlkDroole tlkFlo tlkWD40 1888 1889 1890
			ANIMDLG DOOR INSET FILE CONV SLIDEICON SCALETO PAVOID MOVEFWD OSC
			FORCOUNT MOVECYC REVERSE DPATH VELOCITY_MOVER PRITALK PFOLLOW WRITEFTR
		)
		(mouseDownHandler delete: (ScriptID DEBUG))
		(DisposeScript SQ5CONTROLS)
		(DisposeScript GCONTROL)
		(DisposeScript DEBUG)
		(DisposeScript DEBUG_INV)
		(DisposeScript GRAVMOVE)
		(DisposeScript DPATH)
		(DisposeScript DISPOSE)
	)
)
