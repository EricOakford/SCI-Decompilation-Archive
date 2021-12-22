;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1808)
(include sci.sh)
(use LarryTalker)
(use Actor)

(public
	proc1808_0 0
	proc1808_1 1
	proc1808_2 2
	proc1808_3 3
	proc1808_4 4
	proc1808_5 5
	proc1808_6 6
	proc1808_7 7
	proc1808_8 8
	proc1808_9 9
	proc1808_10 10
	proc1808_11 11
	proc1808_12 12
	proc1808_13 13
	proc1808_14 14
	Kenny 15
)

(procedure (proc1808_0)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_1)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_2)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_3)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_4)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_5)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_6)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_7)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_8)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_9)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_10)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_11)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_12)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_13)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1808_14)
	(super init: tMouth tBust tEyes)
)

(instance Kenny of LarryTalker
	(properties
		x 10
		y 10
		talkWidth 120
		showTitle 1
		back 68
		view 98
		cel 1
		textX 105
		textY 10
		winPosn 2
	)
	
	(method (init)
		(super init: tMouth tBust tEyes)
	)
)

(instance tBust of View
	(properties
		x 2
		view 1241
		loop 1
	)
)

(instance tEyes of Prop
	(properties
		x 10
		y 14
		view 1241
		loop 3
	)
)

(instance tMouth of Prop
	(properties
		x 10
		y 18
		view 1241
	)
)
