;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1807)
(include sci.sh)
(use LarryTalker)
(use Actor)

(public
	proc1807_0 0
	proc1807_1 1
	proc1807_2 2
	proc1807_3 3
	proc1807_4 4
	proc1807_5 5
	proc1807_6 6
	proc1807_7 7
	proc1807_8 8
	proc1807_9 9
	proc1807_10 10
	proc1807_11 11
	proc1807_12 12
	proc1807_13 13
	proc1807_14 14
	proc1807_15 15
	proc1807_16 16
	proc1807_17 17
	Gary_a 18
	proc1807_19 19
	proc1807_20 20
	proc1807_21 21
	proc1807_22 22
	proc1807_23 23
	proc1807_24 24
	proc1807_25 25
	proc1807_26 26
	Gary_b 27
)

(procedure (proc1807_0)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_1)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_2)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_3)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_4)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_5)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_6)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_7)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_8)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_9)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_10)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_11)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_12)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_13)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_14)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_15)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_16)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_17)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_19)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_20)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_21)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_22)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_23)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_24)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_25)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1807_26)
	(super init: tMouth tBust tEyes)
)

(instance Gary_a of LarryTalker
	(properties
		showTitle 1
		back 63
		view 98
		cel 1
		winPosn 2
	)
	
	(method (init)
		(super init: tMouth tBust tEyes)
	)
)

(instance Gary_b of LarryTalker
	(properties
		showTitle 1
		back 19
		view 98
		winPosn 2
	)
	
	(method (init)
		(super init: 0 tBust)
	)
)

(instance tBust of View
	(properties
		x 2
		view 1311
		loop 1
	)
)

(instance tMouth of Prop
	(properties
		x 9
		y 20
		view 1311
	)
)

(instance tEyes of Prop
	(properties
		x 12
		y 16
		view 1311
		loop 2
	)
)
