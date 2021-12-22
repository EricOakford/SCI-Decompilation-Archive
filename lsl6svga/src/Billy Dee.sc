;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1230)
(include sci.sh)
(use LarryTalker)
(use Actor)

(public
	proc1230_0 0
	proc1230_1 1
	proc1230_2 2
	proc1230_3 3
	proc1230_4 4
	proc1230_5 5
	proc1230_6 6
	proc1230_7 7
	proc1230_8 8
	proc1230_9 9
	proc1230_10 10
	proc1230_11 11
	proc1230_12 12
	proc1230_13 13
	proc1230_14 14
	proc1230_15 15
	proc1230_16 16
	Billy_Dee 17
)

(procedure (proc1230_0)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_1)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_2)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_3)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_4)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_5)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_6)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_7)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_8)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_9)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_10)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_11)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_12)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_13)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_14)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_15)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1230_16)
	(super init: tMouth tBust tEyes)
)

(instance Billy_Dee of LarryTalker
	(properties
		showTitle 1
		back 46
		view 98
		cel 1
		textX -195
		textY 10
		winPosn 2
		name "Billy Dee"
	)
	
	(method (init)
		(super init: tMouth tBust tEyes)
	)
)

(instance tBust of Prop
	(properties
		x 2
		view 1230
		loop 1
	)
)

(instance tMouth of Prop
	(properties
		x 10
		y 21
		view 1230
	)
)

(instance tEyes of Prop
	(properties
		x 11
		y 18
		view 1230
		loop 2
	)
)
