;;; Sierra Script 1.0 - (do not remove this comment)
(script# 757)
(include game.sh)
(use BertaWindow)


(class KQ5InvWindow of BertaWindow
	
	(method (open &tmp oldPort)
		(super open:)
		(= oldPort (GetPort))
		(SetPort 0)
		(Graph GShowBits lsTop lsLeft lsBottom lsRight VMAP)
		(SetPort oldPort)
	)
)
