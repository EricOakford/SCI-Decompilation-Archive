;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1802)
(include sci.sh)
(use Main)
(use LarryTalker)
(use Actor)

(public
	proc1802_0 0
	proc1802_1 1
	proc1802_2 2
	Cavaricchi 3
)

(procedure (proc1802_0)
	(if (curRoom inset?)
		(= PROPERTY-ACCESS-IN-NON-METHOD 0)
		(= PROPERTY-ACCESS-IN-NON-METHOD 1)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
		(= PROPERTY-ACCESS-IN-NON-METHOD 98)
		(= PROPERTY-ACCESS-IN-NON-METHOD 139)
		(= PROPERTY-ACCESS-IN-NON-METHOD 31)
		(tBust view: 98)
		(tMouth view: 460 loop: 0 x: 0 y: 11)
		(tEyes view: 460 loop: 2 x: 0 y: 0)
		(super init: tMouth tBust tEyes)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD 0)
		(= PROPERTY-ACCESS-IN-NON-METHOD 2)
		(= PROPERTY-ACCESS-IN-NON-METHOD 98)
		(if (== curRoomNum 370)
			(tBust view: 1461 loop: 1 y: 0 x: 2)
			(tEyes view: 1461 x: 11 y: 11)
			(tMouth view: 1461 x: 12 y: 16)
		else
			(tBust view: 1460 loop: 1 y: 0 x: 2)
			(tEyes view: 1460 x: 11 y: 11)
			(tMouth view: 1460 x: 12 y: 16)
		)
		(super init: tMouth tBust tEyes)
	)
)

(procedure (proc1802_1)
	(if (curRoom inset?)
		(= PROPERTY-ACCESS-IN-NON-METHOD 0)
		(= PROPERTY-ACCESS-IN-NON-METHOD 1)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
		(= PROPERTY-ACCESS-IN-NON-METHOD 98)
		(= PROPERTY-ACCESS-IN-NON-METHOD 139)
		(= PROPERTY-ACCESS-IN-NON-METHOD 31)
		(tBust view: 98)
		(tMouth view: 460 loop: 0 x: 0 y: 11)
		(tEyes view: 460 loop: 2 x: 0 y: 0)
		(super init: tMouth tBust tEyes)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD 0)
		(= PROPERTY-ACCESS-IN-NON-METHOD 2)
		(= PROPERTY-ACCESS-IN-NON-METHOD 98)
		(if (== curRoomNum 370)
			(tBust view: 1461 loop: 1 y: 0 x: 2)
			(tEyes view: 1461 x: 11 y: 11)
			(tMouth view: 1461 x: 12 y: 16)
		else
			(tBust view: 1460 loop: 1 y: 0 x: 2)
			(tEyes view: 1460 x: 11 y: 11)
			(tMouth view: 1460 x: 12 y: 16)
		)
		(super init: tMouth tBust tEyes)
	)
)

(procedure (proc1802_2)
	(if (curRoom inset?)
		(= PROPERTY-ACCESS-IN-NON-METHOD 0)
		(= PROPERTY-ACCESS-IN-NON-METHOD 1)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
		(= PROPERTY-ACCESS-IN-NON-METHOD 98)
		(= PROPERTY-ACCESS-IN-NON-METHOD 139)
		(= PROPERTY-ACCESS-IN-NON-METHOD 31)
		(tBust view: 98)
		(tMouth view: 460 loop: 0 x: 0 y: 11)
		(tEyes view: 460 loop: 2 x: 0 y: 0)
		(super init: tMouth tBust tEyes)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD 0)
		(= PROPERTY-ACCESS-IN-NON-METHOD 2)
		(= PROPERTY-ACCESS-IN-NON-METHOD 98)
		(if (== curRoomNum 370)
			(tBust view: 1461 loop: 1 y: 0 x: 2)
			(tEyes view: 1461 x: 11 y: 11)
			(tMouth view: 1461 x: 12 y: 16)
		else
			(tBust view: 1460 loop: 1 y: 0 x: 2)
			(tEyes view: 1460 x: 11 y: 11)
			(tMouth view: 1460 x: 12 y: 16)
		)
		(super init: tMouth tBust tEyes)
	)
)

(instance Cavaricchi of LarryTalker
	(properties
		x 139
		y 27
		talkWidth 120
		showTitle 1
		back 6
		view 98
		textX -143
		textY -10
	)
	
	(method (init)
		(if (curRoom inset?)
			(= winPosn 0)
			(= fullFace 1)
			(= talkWidth 120)
			(= view 98)
			(= x 139)
			(= y 31)
			(tBust view: 98)
			(tMouth view: 460 loop: 0 x: 0 y: 11)
			(tEyes view: 460 loop: 2 x: 0 y: 0)
			(super init: tMouth tBust tEyes)
		else
			(= fullFace 0)
			(= winPosn 2)
			(= view 98)
			(if (== curRoomNum 370)
				(tBust view: 1461 loop: 1 y: 0 x: 2)
				(tEyes view: 1461 x: 11 y: 11)
				(tMouth view: 1461 x: 12 y: 16)
			else
				(tBust view: 1460 loop: 1 y: 0 x: 2)
				(tEyes view: 1460 x: 11 y: 11)
				(tMouth view: 1460 x: 12 y: 16)
			)
			(super init: tMouth tBust tEyes)
		)
	)
)

(instance tBust of Prop
	(properties
		view 98
	)
)

(instance tMouth of Prop
	(properties
		view 460
	)
)

(instance tEyes of Prop
	(properties
		view 460
		loop 2
	)
)
