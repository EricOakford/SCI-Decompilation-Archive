;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1051)
(include sci.sh)
(use Main)
(use Kq6Talker)
(use Actor)

(public
	Tomato 50
)

(instance Tomato of Kq6Talker
	(properties)
	
	(method (init)
		(cond 
			(
				(and
					(== curRoomNum 470)
					(== ((inventory at: 49) owner?) curRoomNum)
				)
				(self
					view: 475
					loop: 7
					x: 259
					y: 123
					textX: -239
					textY: -117
					talkWidth: 213
				)
				(super init: 0 0 tSwampMater &rest)
			)
			((and (== curRoomNum 470) (curRoom script?))
				(self
					view: 474
					loop: 7
					x: 17
					y: 171
					textX: 3
					textY: -165
					talkWidth: 213
				)
				(super init: 0 0 tBumpMater &rest)
			)
			(
				(and
					(== curRoomNum 480)
					(== ((inventory at: 49) owner?) curRoomNum)
				)
				(self
					view: 4802
					loop: 1
					x: 122
					y: 141
					textX: -74
					textY: -130
					talkWidth: 213
				)
				(super init: 0 0 tGroundMater &rest)
			)
			(else
				(self
					view: 890
					loop: 0
					cel: 1
					x: 255
					y: 5
					textX: -209
					textY: 8
					talkWidth: 213
				)
				(super init: 0 0 0 &rest)
			)
		)
	)
)

(instance tBust of Prop
	(properties
		view 2004
		loop 1
	)
)

(instance tEyes of Prop
	(properties
		nsTop 21
		nsLeft 25
		view 2004
		loop 2
	)
)

(instance tMouth of Prop
	(properties
		nsTop 30
		nsLeft 25
		view 2004
	)
)

(instance tBumpMater of Prop
	(properties
		view 474
		loop 7
	)
)

(instance tSwampMater of Prop
	(properties
		view 475
		loop 7
	)
)

(instance tGroundMater of Prop
	(properties
		view 4802
		loop 1
	)
)
