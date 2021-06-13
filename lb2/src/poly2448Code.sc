;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2448)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2448Code 0
	pts2448 1
)

(local
	pts = [
		80 189
		0 189
		0 0
		319 0
		319 189
		222 189
		222 141
		195 138
		195 131
		185 129
		168 115
		146 79
		135 115
		125 123
		74 123
		72 126
		113 126
		80 141
		]
)
(instance poly2448Code of Code
	
	(method (doit obj)
		(obj add: (poly2448a init: yourself:))
	)
)

(instance poly2448a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 18)
		(= points @pts)
	)
)

(instance pts2448 of MuseumPoints
	(properties
		midPtX 170
		midPtY 130
		northPtX 145
		northPtY 105
		southPtX 170
		southPtY 220
	)
)
