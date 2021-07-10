;;; Sierra Script 1.0 - (do not remove this comment)
(script# 828)
(include game.sh)
(use Main)

(public
	SolvePuzzle 0
)

(procedure (SolvePuzzle param1 param2 param3 pValue)
	(if (not (& (param1 param2?) param3))
		(theGame changeScore: pValue)
		(param1 param2: (| (param1 param2?) param3))
	)
	(DisposeScript 828)
)
