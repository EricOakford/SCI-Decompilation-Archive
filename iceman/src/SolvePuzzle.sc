;;; Sierra Script 1.0 - (do not remove this comment)
(script# 828)
(include game.sh)
(use Main)

(public
	SolvePuzzle 0
)

(procedure (SolvePuzzle region flags pFlag pValue)
	(if (not (& (region flags?) pFlag))
		(theGame changeScore: pValue)
		(region flags: (| (region flags?) pFlag))
	)
	(DisposeScript 828)
)
