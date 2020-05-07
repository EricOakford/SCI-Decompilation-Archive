;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1013)
(include sci.sh)
(use Main)
(use Kq6Talker)
(use Kq6Procs)
(use Actor)

(public
	Genie 29
)

(instance Genie of Kq6Talker
	(properties
		x 5
		y 5
		view 893
		talkWidth 213
		textX 76
		textY 8
	)
	
	(method (init &tmp theTMouthLo)
		(if (== curRoomNum 145)
			(self view: 1466 loop: 3 talkWidth: 175)
			(if (Btst 133)
				(self x: 173 y: 134 textX: -73 textY: -114)
				(= theTMouthLo tMouthLo)
			else
				(self x: 182 y: 68 textX: -107 textY: 20)
				(= theTMouthLo tMouthHi)
			)
			(super init: 0 0 theTMouthLo &rest)
		else
			(super init: tBust tEyes tMouth &rest)
		)
	)
)

(instance tBust of Prop
	(properties
		view 893
		loop 1
	)
)

(instance tEyes of Prop
	(properties
		nsTop 21
		nsLeft 18
		view 893
		loop 2
	)
)

(instance tMouth of Prop
	(properties
		nsTop 32
		nsLeft 16
		view 893
	)
)

(instance tMouthHi of Prop
	(properties
		view 1466
		loop 1
	)
)

(instance tMouthLo of Prop
	(properties
		view 1466
	)
)
