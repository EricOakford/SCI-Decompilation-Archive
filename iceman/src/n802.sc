;;; Sierra Script 1.0 - (do not remove this comment)
(script# 802)
(include game.sh)
(use Main)
(use Extra)
(use System)

(public
	proc802_0 0
	proc802_1 1
	GiveExtraControl 2
	InRoom 3
)

(procedure (proc802_0 param1 param2 &tmp temp0 temp1)
	(= temp0 (if (>= argc 2) param2 else 5))
	(= temp1 (param1 heading?))
	(OnControl
		4
		(+ (param1 x?) (SinMult temp1 temp0))
		(- (param1 y?) (CosMult temp1 temp0))
	)
)

(procedure (proc802_1 param1 param2)
	(param1 loop: param2 changeState:)
)

(procedure (GiveExtraControl param1)
	(cast eachElementDo: #perform extraControl param1)
)

(procedure (InRoom what where)
	(return
		(==
			((inventory at: what) owner?)
			(if (>= argc 2) curRoomNum else where)
		)
	)
)

(instance extraControl of Code

	(method (doit param1 param2)
		(if (param1 isKindOf: Extra)
			(if param2
				(param1 startExtra:)
			else
				(param1 stopExtra:)
			)
		)
	)
)
