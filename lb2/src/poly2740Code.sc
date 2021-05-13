;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2740)
(include game.sh)
(use Polygon)
(use System)

(public
	poly2740Code 0
	proc2740_1 1
)

(local
	pts = [
		36 189
		0 189
		0 0
		319 0
		319 189
		48 189
		192 106
		294 106
		294 37
		272 37
		272 104
		248 104
		248 37
		220 37
		220 104
		184 104
		]
)
(procedure (proc2740_1)
)

(instance poly2740Code of Code

	(method (doit obj)
		(obj add: (poly2740a init: yourself:))
	)
)

(instance poly2740a of Polygon

	(method (init)
		(= type PBarredAccess)
		(= size 16)
		(= points @pts)
	)
)
