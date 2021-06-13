;;; Sierra Script 1.0 - (do not remove this comment)
(script# 2490)
(include game.sh)
(use MuseumPoints)
(use Polygon)
(use System)

(public
	poly2490Code 0
	pts2490 1
)

(local
	pts = [
		0 0
		319 0
		319 138
		223 130
		0 151
		]
)
(instance poly2490Code of Code
	
	(method (doit obj)
		(obj add: (poly2490a init: yourself:))
	)
)

(instance poly2490a of Polygon
	
	(method (init)
		(= type PBarredAccess)
		(= size 5)
		(= points @pts)
	)
)

(instance pts2490 of MuseumPoints
	(properties
		midPtX 125
		midPtY 160
		southPtX 125
		southPtY 250
	)
)
