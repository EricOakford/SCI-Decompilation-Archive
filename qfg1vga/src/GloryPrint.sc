;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_PRINT)
(include game.sh)
(use Main)
(use Print)

(public
	CenterPrint 0
	TimePrint 1
	proc815_2 2
)

(procedure (CenterPrint)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(Print
		addText: &rest
		mode: teJustCenter
		init:
	)
	(DisposeScript GLORY_PRINT)
)

(procedure (TimePrint numSeconds)
	(if modelessDialog
		(modelessDialog dispose:)
	)
	(Print
		addText: &rest
		mode: teJustCenter
		ticks: (* numSeconds 60)
		init:
	)
	(DisposeScript GLORY_PRINT)
)

(procedure (proc815_2)
	(CenterPrint &rest)
)
