;;; Sierra Script 1.0 - (do not remove this comment)
(script# 757)
(include sci.sh)
(use BertaWindow)


(class KQ5InvWindow of BertaWindow
	(properties
		top 0
		left 0
		bottom 0
		right 0
		color 0
		back 15
		priority -1
		window 0
		type $0000
		title 0
		brTop 0
		brLeft 0
		brBottom 190
		brRight 320
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		eraseOnly 0
		lineColor 0
	)
	
	(method (open &tmp temp0)
		(super open:)
		(= temp0 (GetPort))
		(SetPort 0)
		(Graph grUPDATE_BOX lsTop lsLeft lsBottom lsRight 1)
		(SetPort temp0)
	)
)
