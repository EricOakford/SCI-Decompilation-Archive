;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1047)
(include sci.sh)
(use Kq6Talker)
(use Kq6Procs)

(public
	Widow 46
)

(instance Widow of Kq6Talker
	(properties
		view 890
		cel 1
		talkWidth 213
		textX 0
	)
	
	(method (init)
		(if (Btst 59)
			(self x: 81 y: 13)
		else
			(self x: 80 y: 100)
		)
		(super init: 0 0 0 &rest)
	)
)
