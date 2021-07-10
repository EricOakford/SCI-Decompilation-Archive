;;; Sierra Script 1.0 - (do not remove this comment)
(script# 318)
(include game.sh)
(use Main)
(use Intrface)
(use subMarine)
(use SolvePuzzle)
(use System)

(public
	lookCyl 0
	measureCyl 1
)

(procedure (localproc_00da)
	(Print 318 2)
	(SolvePuzzle subMarine 406 32 5)
)

(procedure (CheckSubStatus)
	(Printf 318 3
		(switch (subMarine invStatus1?)
			(1 {3})
			(2 {4})
			(3 {6})
		)
	)
	(if (subMarine cylDiam?)
		(Printf 318 4
			(switch (subMarine cylDiam?)
				(1 {1"})
				(2 {1.5"})
				(3 {2"})
			)
		)
	)
	(if (subMarine holeSize?)
		(Printf 318 5
			(switch (subMarine holeSize?)
				(1 {1/32"})
				(2 {1/16"})
				(3 {1/8"})
				(4 {1/4"})
				(5 {1/2"})
				(6 {3/4"})
				(7 {1"})
			)
		)
	)
	(if (& (subMarine roomFlags?) $0004)
		(Print 318 6)
	else
		(Print 318 7)
	)
)

(instance lookCyl of Code
	
	(method (doit &tmp temp0)
		(if (not (ego has: iMetalCylinder))
			(Print 318 0)
		else
			((inventory at: iMetalCylinder) showSelf:)
		)
		(DisposeScript 318)
	)
)

(instance measureCyl of Code

	(method (doit &tmp temp0)
		(cond 
			((not (ego has: iMetalCylinder))
				(Print 318 0)
			)
			((not (ego has: iVernierCaliper))
				(Print 318 1)
			)
			((== (subMarine invStatus1?) 4)
				(localproc_00da)
			)
			(else
				(CheckSubStatus)
			)
		)
		(DisposeScript 318)
	)
)
