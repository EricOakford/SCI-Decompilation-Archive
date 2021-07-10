;;; Sierra Script 1.0 - (do not remove this comment)
(script# 824)
(include game.sh)
(use Main)

(public
	InitAllFeatures 0
)

(procedure (InitAllFeatures &tmp node i)
	(= node (FirstNode (addToPics elements?)))
	(while (and node (IsObject (= i (NodeValue node))))
		(features add: i)
		(= node (NextNode node))
	)
	(DisposeScript 824)
)
