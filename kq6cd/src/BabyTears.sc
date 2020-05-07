;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1039)
(include sci.sh)
(use Kq6Talker)
(use Kq6Procs)
(use Actor)

(public
	BabyTears 71
)

(instance BabyTears of Kq6Talker
	(properties
		view 4803
		talkWidth 213
	)
	
	(method (init)
		(if (Btst 59)
			(self loop: 7 x: 0 y: 135 textX: 10 textY: -125)
			(super init: tWimpBust 0 tWimpMouth &rest)
		else
			(self loop: 13 x: 44 y: 140 textX: -36 textY: -130)
			(super init: 0 0 tMouth &rest)
		)
	)
)

(instance tBust of Prop
	(properties
		view 4803
		loop 4
	)
)

(instance tMouth of Prop
	(properties
		view 4803
		loop 4
	)
)

(instance tWimpBust of Prop
	(properties
		view 4803
		loop 1
	)
)

(instance tWimpMouth of Prop
	(properties
		view 4803
		loop 1
	)
)
