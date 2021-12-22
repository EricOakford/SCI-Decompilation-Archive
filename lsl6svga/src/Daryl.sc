;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1809)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryTalker)
(use Actor)

(public
	proc1809_0 0
	proc1809_1 1
	proc1809_2 2
	proc1809_3 3
	proc1809_4 4
	proc1809_5 5
	proc1809_6 6
	proc1809_7 7
	Daryl 8
)

(procedure (proc1809_0)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(procedure (proc1809_1)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(procedure (proc1809_2)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(procedure (proc1809_3)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(procedure (proc1809_4)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(procedure (proc1809_5)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(procedure (proc1809_6)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(procedure (proc1809_7)
	(if (== curRoomNum 850)
		(if (Btst 95)
			(tMouth view: 850 loop: 3 x: 159 y: 22)
		else
			(tMouth view: 850 loop: 5 x: 165 y: 15)
		)
		(super init: 0 0 tMouth)
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 120)
	else
		(= PROPERTY-ACCESS-IN-NON-METHOD -158)
		(= PROPERTY-ACCESS-IN-NON-METHOD 150)
		(super init:)
	)
)

(instance Daryl of LarryTalker
	(properties
		talkWidth 100
		showTitle 1
		back 65
		view 98
		fullFace 1
	)
	
	(method (init)
		(if (== curRoomNum 850)
			(if (Btst 95)
				(tMouth view: 850 loop: 3 x: 159 y: 22)
			else
				(tMouth view: 850 loop: 5 x: 165 y: 15)
			)
			(super init: 0 0 tMouth)
			(= textX -158)
			(= talkWidth 120)
		else
			(= textX -158)
			(= talkWidth 150)
			(super init:)
		)
	)
)

(instance tMouth of Prop
	(properties
		view 850
		loop 5
	)
)
