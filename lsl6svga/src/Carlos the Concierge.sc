;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1592)
(include sci.sh)
(use LarryTalker)
(use Actor)

(public
	proc1592_0 0
	proc1592_1 1
	proc1592_2 2
	proc1592_3 3
	proc1592_4 4
	proc1592_5 5
	proc1592_6 6
	proc1592_7 7
	proc1592_8 8
	Carlos_the_Concierge 9
)

(procedure (proc1592_0)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_1)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_2)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_3)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_4)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_5)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_6)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_7)
	(super init: tMouth tBust tEyes)
)

(procedure (proc1592_8)
	(super init: tMouth tBust tEyes)
)

(instance Carlos_the_Concierge of LarryTalker
	(properties
		showTitle 1
		view 98
		cel 1
		winPosn 2
		name "Carlos the Concierge"
	)
	
	(method (init)
		(super init: tMouth tBust tEyes)
	)
)

(instance tBust of View
	(properties
		x 2
		view 1592
		loop 1
	)
)

(instance tEyes of Prop
	(properties
		x 11
		y 15
		view 1592
		loop 2
	)
)

(instance tMouth of Prop
	(properties
		x 7
		y 20
		view 1592
	)
)
