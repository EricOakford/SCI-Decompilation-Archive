;;; Sierra Script 1.0 - (do not remove this comment)
;********************************************************************
;***
;*** DEMOWIN.SC
;***	  This is the window that Print points to for the demo.
;***	       --Robert W. Lindsley
;***
;********************************************************************

(script#	DEMOWIN)
(include game.sh)
(use Window)


(class DemoWin kindof Window
	(properties
		type				81
		back				255
		color				0
		front				0
		priority			-1
	)
	(method (open)
		(super open:	&rest)
	)
)
