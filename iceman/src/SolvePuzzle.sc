;;; Sierra Script 1.0 - (do not remove this comment)
(script# 828)
(include game.sh)
(use Main)

(public
	SolvePuzzle 0
)

(procedure (SolvePuzzle region rgFlags pFlag pValue)
	(if (not (& (region rgFlags?) pFlag))
		(theGame changeScore: pValue)
		(region rgFlags: (| (region rgFlags?) pFlag))
	)
	(DisposeScript 828)
)
