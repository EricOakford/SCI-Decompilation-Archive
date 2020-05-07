;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1001)
(include sci.sh)
(use Main)
(use Kq6Talker)
(use Kq6Procs)
(use Actor)
(use System)

(public
	Alex 2
)

(instance Alex of Kq6Talker
	(properties
		view 890
		raveName {ALEX}
		winPosn 0
	)
	
	(method (init)
		(cond 
			((and (Btst 59) (!= curRoomNum 850))
				(self cel: 1 x: 5 y: 5 textX: 76 textY: 8 talkWidth: 213)
				(super init: 0 0 0 &rest)
			)
			(
			(and (== (curRoom curPic?) 165) (== curRoomNum 740))
				(self
					cel: 1
					x: 156
					y: 71
					textX: -120
					textY: 70
					talkWidth: 130
				)
				(super init: 0 tEyes740 tMouth740 &rest)
			)
			(
				(and
					(== curRoomNum 406)
					(!= (ego view?) 900)
					(not (Btst 1))
				)
				(self cel: 1 x: 5 y: 5 textX: 1 textY: 8 talkWidth: 213)
				(super init: 0 0 0 &rest)
			)
			((== curRoomNum 150)
				(self
					cel: 1
					x: 219
					y: 45
					talkWidth: 153
					textX: -70
					textY: 56
					keepWindow: 0
				)
				(super init: 0 tEyes150 tMouth150 &rest)
			)
			((== curRoomNum 370)
				(self
					cel: 1
					x: 145
					y: 50
					talkWidth: 230
					textX: -95
					textY: -44
					keepWindow: 0
				)
				(super init: 0 0 tBigHead &rest)
			)
			((== curRoomNum 380)
				(self
					view: 3812
					loop: 1
					x: 208
					y: 86
					textX: -188
					textY: -76 talkWidth 230
					keepWindow: 0
				)
				(super init: 0 0 tSideOfMouth &rest)
			)
			((== curRoomNum 690)
				(self
					view: 2002
					loop: 0
					cel: 1
					x: 129
					y: 125
					textX: 32
					textY: -35
					talkWidth: 140
				)
				(super init: 0 0 tMouth690 &rest)
			)
			((Btst 102)
				(self
					cel: 1
					x: 176
					y: 60
					textX: 20
					textY: -40
					talkWidth: 100
				)
				(tEyes view: 7832 loop: 12 cel: 0 nsLeft: 0 nsTop: 0)
				(tMouth view: 7832 loop: 11 cel: 0 nsLeft: 0 nsTop: 6)
				(super init: 0 tEyes tMouth &rest)
			)
			((Btst 99)
				(self
					cel: 1
					x: 44
					y: 41
					textX: -10
					textY: 71
					talkWidth: 100
				)
				(tEyes view: 161 loop: 4 cel: 0 nsLeft: 0 nsTop: 0)
				(tMouth view: 161 loop: 3 cel: 0 nsLeft: 2 nsTop: 10)
				(super init: 0 tEyes tMouth &rest)
			)
			((Btst 150)
				(self cel: 0 x: 5 y: 5 textX: 78 textY: 8 talkWidth: 213)
				(tEyes view: 890 loop: 2 nsTop: 27 nsLeft: 27)
				(super init: tBust tEyes 0 &rest)
			)
			(else
				(if
					(or
						(OneOf curRoomNum 750 290 220 260)
						(and
							(== (ego view?) 900)
							(or
								(OneOf (ego loop?) 1 5 7)
								(and (== (ego loop?) 9) (OneOf (ego cel?) 1 5 7))
							)
						)
					)
					(= winPosn 1)
				else
					(= winPosn 0)
				)
				(self cel: 0 x: 5 y: 5 textX: 78 textY: 8 talkWidth: 213)
				(tEyes view: 890 loop: 2 nsTop: 27 nsLeft: 27)
				(tMouth view: 890 loop: 1 nsTop: 33 nsLeft: 26)
				(super init: tBust tEyes tMouth &rest)
			)
		)
	)
)

(instance tSideOfMouth of Prop
	(properties
		view 381
		loop 3
	)
)

(instance tBigHead of Prop
	(properties
		view 374
		loop 4
	)
)

(instance tBust of Prop
	(properties
		view 890
	)
)

(instance tEyes of Prop
	(properties
		nsTop 27
		nsLeft 27
		view 890
		loop 2
	)
)

(instance tMouth of Prop
	(properties
		nsTop 33
		nsLeft 26
		view 890
		loop 1
	)
)

(instance tMouth150 of Prop
	(properties
		view 150
		loop 2
	)
)

(instance tEyes150 of Prop
	(properties
		nsTop -6
		nsLeft 4
		view 150
		loop 9
	)
)

(instance tMouth740 of Prop
	(properties
		view 165
		loop 1
	)
)

(instance tEyes740 of Prop
	(properties
		nsTop -8
		nsLeft -1
		view 165
	)
)

(instance tMouth690 of Prop
	(properties
		view 691
		loop 8
	)
)
